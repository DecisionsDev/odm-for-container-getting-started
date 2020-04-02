package com.ibm.decisions.eml;

import java.util.HashMap;

// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.api.AutomationApiV10PredictionAdminApi;
import org.openapitools.client.api.AutomationApiV10PredictionGenericApi;
import org.openapitools.client.model.*;

public class MLServiceClientTest {
	
	private static MLServiceClient client = new MLServiceClient("http://localhost:5000");
	
	public static ModelResponse predictIris() {

		ModelKeyDescriptor modelDescriptor = new ModelKeyDescriptor();
		modelDescriptor.setName("iris-svc");
		modelDescriptor.setVersion("1.0");
		modelDescriptor.setFormat("joblib");
		
		HashMap<String, String> featureMap = new HashMap<String, String>();
		featureMap.put("sepal length", "5.1");
		featureMap.put("sepal width", "3.5");
		featureMap.put("petal length", "1.4");
		featureMap.put("petal width", "0.2");
		
		ModelResponse modelResponse = client.predict(modelDescriptor, featureMap);
		return modelResponse;
	}
	
	public static ModelResponse predictLoanDefault() {
		
		ModelKeyDescriptor modelDescriptor = new ModelKeyDescriptor();
		modelDescriptor.setName("miniloandefault-xgb");
		modelDescriptor.setVersion("1.0");
		modelDescriptor.setFormat("joblib");
		
		HashMap<String, String> featureMap = new HashMap<String, String>();
		featureMap.put("creditScore", "600");
		featureMap.put("income", "100000");
		featureMap.put("loanAmount", "20000");
		featureMap.put("monthDuration", "120");
		featureMap.put("rate", "0.06");
		featureMap.put("yearlyReimbursement", "1000");
		
		ModelResponse modelResponse = client.predict(modelDescriptor, featureMap);
		return modelResponse;
	}
	
	public static void main(String[] args) {
		
		client.admin();
		
		ModelResponse modelResponse = predictIris();
		System.out.println(modelResponse.getId() + " : " + modelResponse.getModelPath() + " : " + modelResponse.getPrediction() + " : " + modelResponse.getProbabilities());
		
		modelResponse = predictLoanDefault();
		System.out.println(modelResponse.getId() + " : " + modelResponse.getModelPath() + " : " + modelResponse.getPrediction() + " : " + modelResponse.getProbabilities());
	}
}
