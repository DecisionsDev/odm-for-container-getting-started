#
# This program builds a SVM model to predict a loan payment default.
# It reads a labelled dataset of loan payments, makes the model, measures its accuracy and performs unit tests.
# It ends by a serialization through models. The serialized model is then used by the main program that serves it.
#

import os
import pandas as pd
import numpy as np
import datetime
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import mean_squared_error
import xgboost as xgb
from sklearn.datasets import make_classification
import pickle

data = pd.read_csv('data/miniloan-decisions-default-1K.csv', sep=',', header=0)
data.head()
print("Number of records: " + str(data.count()))

from sklearn.model_selection import train_test_split
from sklearn import metrics

# creditScore,income,loanAmount,monthDuration,rate,yearlyReimbursement,paymentDefault
X = data[['creditScore', 'income', 'loanAmount', 'monthDuration', 'rate', 'yearlyReimbursement']]  # Features
y = data['paymentDefault']  # Label

data_dmatrix = xgb.DMatrix(data=X,label=y)

# Split dataset into training set and test set
# 70% training and 30% test
# deterministic split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=0)  # 70% training and 30% test

xg_reg = xgb.XGBClassifier()

xg_reg.fit(X_train,y_train)
preds = xg_reg.predict(X_test)

rmse = np.sqrt(mean_squared_error(y_test, preds))
print("RMSE: %f" % (rmse))

# Model Accuracy, how often is the classifier correct?
model = xg_reg
y_pred = model.predict(X_test)
#accuracy = metrics.accuracy_score(y_test, y_pred)
#print("Accuracy:", accuracy)

# Unit test
# Warning Tis function is not thread safe. Use xgb.copy
#data_dmatrix_test1 = xgb.DMatrix(data=X,label=y)
#prediction = model.predict_proba([397, 160982, 570189, 240, 0.07, 57195])  # expected 1 meaning default
#prediction = model.predict_proba(data_dmatrix_test1)  # expected 1 meaning default
#print("prediction with Random Forest Classifier: " + str(prediction) + " expect [1]")

names = ['creditScore', 'income', 'loanAmount', 'monthDuration', 'rate', 'yearlyReimbursement']

df_test = pd.DataFrame([[580, 66037, 168781, 120, 0.09, 16187]], columns=names)
prediction = model.predict_proba(df_test)  # expected 0
print("prediction with Random Forest Classifier: " + str(prediction) + " expect [0]")

# Test cut values for samples
df_test = pd.DataFrame([[400, 17500, 27500, 12, 0.05, 40000]], columns=names)
prediction = model.predict_proba(df_test)  # expected 0
print("prediction test 1 with Random Forest Classifier: " + str(prediction) + " expect [0]")

df_test = pd.DataFrame([[600, 80000, 400000, 120, 0.05, 75195]], columns=names)
prediction = model.predict_proba(df_test)  # expected 0
print("prediction test 2 with Random Forest Classifier: " + str(prediction) + " expect [0]")

df_test = pd.DataFrame([[500, 60000, 1000000, 120, 0.05, 75195]], columns=names)
prediction = model.predict_proba(df_test)  # expected 0
print("prediction test 3 with Random Forest Classifier: " + str(prediction) + " expect [0]")

creditScore = 397
income = 160982
loanAmount = 570189
monthDuration = 240
rate = 0.07
yearlyReimbursement = 57195

df_test = pd.DataFrame([[397, 160982, 570189, 240, 0.07, 57195]], columns=names)
prediction = model.predict_proba(df_test)
print("prediction with Random Forest Classifier: " + str(prediction) + " expect [0]")

#
# Model serialization
#

# Serialization with Pickle
modelFilePath = 'models/miniloandefault-xgb.pickle'
pickle.dump(model, open(modelFilePath, 'wb'))

#Testing deserialized model
loaded_model = pickle.load(open(modelFilePath, 'rb'))
prediction = loaded_model.predict_proba(df_test)
print("prediction with a model serialized with Pickle: " + str(prediction) + " expect [0]")

d = datetime.datetime.today()

toBePersisted = dict({
    'model': model,
    'metadata': {
        'name': 'loan payment default classification',
        'author': 'Pierre Feillet',
        'date': d,
        'metrics': {
            'rmse': rmse
        },
        'algorithm': 'xgboost',
        'kind': 'classification',
        'invocation': 'predict_proba',
        'signature': [
            {
                'name': "creditScore",
                'order': 0,
                'type': 'float'
            },
            {
                'name': "income",
                'order': 1,
                'type': 'float'
            },
            {
                'name': "loanAmount",
                'order': 2,
                'type': 'float'
            },
            {
                'name': "monthDuration",
                'order': 3,
                'type': 'float'
            },
            {
                'name': "rate",
                'order': 4,
                'type': 'float'
            },
            {
                'name': "yearlyReimbursement",
                'order': 5,
                'type': 'float'
            }
        ]
    },
    'outcome': {
        'probabilities': '[float]',
        'prediction': 'float'
    },
})

modelFilePath = 'models/miniloandefault-xgb.joblib'
from joblib import dump

dump(toBePersisted, modelFilePath)

# Testing deserialized model

from joblib import load

dictionary = load(modelFilePath)
loaded_model = dictionary['model']
df_test = pd.DataFrame([[397, 160982, 570189, 240, 0.07, 57195]], columns=names)
prediction = loaded_model.predict_proba(df_test)
print("prediction with a model serialized with Joblib: " + str(prediction) + " expect [1]")
