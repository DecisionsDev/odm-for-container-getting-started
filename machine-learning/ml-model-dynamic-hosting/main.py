#!flask/bin/python

import numpy as np
import pandas as pd
import requests
from flask import Flask
from flask import request
from flask_restplus import Api, Resource, fields
from joblib import load
import uuid

#
# Model registering
#

modelDictionary = dict({
    'models': [
        {
            'path': "models/miniloandefault-rfc.joblib",
        },
        {
            'path': "models/miniloandefault-svm.joblib",
        },
        {
            'path': "models/miniloandefault-xgb.joblib",
        },
        {
            'path': "models/iris-svc.joblib",
        },
        {
            'path': "https://github.com/ODMDev/decisions-on-ml/blob/master/ml-model-creation/models/miniloandefault-rfc.joblib?raw=true"
        }
    ]
})

# todo
# Propagate the joblib metadata into the model management dictionary

#
# Flask
#

app = Flask(__name__)
api = Api(app)

ns = api.namespace('automation/api/v1.0/prediction/admin', description='administration')


@ns.route('/isAlive')  # Create a URL route to this resource
class HeartBeat(Resource):  # Create a RESTful resource
    def get(self):  # Create GET endpoint
        return {'answer': 'ok'}


modelAddition = api.model('ModelSpecification', {
    'name': fields.String(required=True, description="Name of the model", help="Name cannot be blank."),
    'version': fields.String(required=True, description="version of the model", help="Version cannot be blank."),
    'format': fields.String(required=True, description="format of the model", help="Format cannot be blank.")})

@ns.route("/models")
class Model(Resource):
    def get(self):
        """Returns the list of ML models."""
        return modelDictionary

    @api.expect(modelAddition)
    @api.response(201, 'Model successfully created.')
    def post(self):
        """Add a model."""

        try:
            json_dictionary = request.json
            model_path = json_dictionary['path']
            print(json_dictionary)

            # Model
            models = modelDictionary['models']
            models.append({'path': model_path})

            response_dictionary = {
                "model_path": model_path,
                "id": str(uuid.uuid4())
            }

            response_dictionary["status"] = "Ok"

            return response_dictionary
        except:
            return "KO"


ns = api.namespace('automation/api/v1.0/prediction/generic', description='run any ML models')

address_fields = {'line 1': fields.String(required=True, description="Name of the model", help="Name cannot be blank."),
                  'metrics': {
                        'rmse': fields.String(required=True, description="Name of the model", help="Name cannot be blank.")
                    }
        }

model_fields = api.model('ModelKeyDescriptor', {
    'name': fields.String,
    'version': fields.String,
    'format': fields.String
})

wild = fields.Wildcard(fields.String)
wildcard_fields = {'*': wild}

feature_fields = api.model('Feature', {
    'name': fields.String,
    'value': fields.String
})

modelInvocation = api.model('ModelInvocation', {
    'model': fields.Nested(model_fields),
    'features': fields.Wildcard(fields.String)
})

response_fields = api.model('ModelResponse', {
    'modelPath': fields.String,
    'id': fields.String,
    'prediction': fields.String,
    'probabilities': fields.Wildcard(fields.String)
})

@ns.route('/')
class PredictionService(Resource):
    @api.expect(modelInvocation)
    #@api.marshal_with(response_fields, code=201, description='computed prediction')
    @api.response(201, 'Category successfully created.', response_fields)
    def post(self):
        """Computes a new prediction."""

        try:
            json_dictionary = request.json
            print(json_dictionary)

            # Model
            json_model_dictionary = json_dictionary["model"]
            model_name = json_model_dictionary["name"]
            model_version = json_model_dictionary["version"]
            model_format = json_model_dictionary["format"]

            # Features
            json_payload_dictionary = json_dictionary["features"]

            if model_name.startswith('http'):  # means an externalized model
                # Remote read
                # 'https://github.com/ODMDev/decisions-on-ml/blob/master/docker-python-flask-sklearn-joblist-json/models/miniloandefault-rfc.joblib?raw=true')
                response = requests.get(model_name)
                # response.content  # ?
                # ToDo Complete the code
                dictionary = load(model_name)

            else:
                # Local read
                # Compose the model path
                model_path = 'models/' + model_name + '.' + 'joblib'  # Picking joblib file by default
                dictionary = load(model_path)

            # Access to the model metadata
            metadata_dictionary = dictionary["metadata"]

            # Introspect the signature
            signature_parameters = metadata_dictionary["signature"]
            parameter_names = []
            parameter_values = []
            for parameter in signature_parameters:
                print(parameter)
                name = parameter["name"]
                type = parameter["type"]
                value = float(json_payload_dictionary[name])
                parameter_values.append(value)
                parameter_names.append(name)
            # Local read
            loaded_model = dictionary['model']

            # Invocation
            invocation_method = metadata_dictionary["invocation"]
            algorithm = metadata_dictionary.get('algorithm', 'unknown')
            # predicted_class = -1
            # prediction_wrapper = 0

            response_dictionary = {
                "modelPath": model_path,
                "id": str(uuid.uuid4())
            }

            if invocation_method == 'predict':
                predicted_class = loaded_model.predict(
                    [parameter_values])
                # Assume an array of a single element to be cast in int
                found_class = predicted_class[0]
                response_dictionary['prediction'] = found_class.item()  # cast into int

            if invocation_method == 'predict_proba':
                # For XGBoost
                if algorithm == 'xgboost':
                    df_test = pd.DataFrame([parameter_values], columns=parameter_names)
                    prediction_wrapper_32 = loaded_model.predict_proba(df_test)

                    # float32 to 64 conversion
                    prediction_wrapper = np.float64(prediction_wrapper_32)
                # General case
                else:
                    prediction_wrapper = loaded_model.predict_proba(
                        [parameter_values])

                prediction = prediction_wrapper[0]

                # Needs to be generalized
                probabilities = {
                    "0": prediction[0],
                    "1": prediction[1]
                }

                response_dictionary["probabilities"] = probabilities

            # json_string = json.dumps(responseDictionary, indent=4)

            # print(responseDictionary)

            return response_dictionary

        except:
            return "KO"


if __name__ == '__main__':
    # Start a development server
    app.run(port=5000, host='127.0.0.1')
