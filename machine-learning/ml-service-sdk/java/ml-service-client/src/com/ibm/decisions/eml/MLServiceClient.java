package com.ibm.decisions.eml;

import java.util.HashMap;

// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.api.AutomationApiV10PredictionAdminApi;
import org.openapitools.client.api.AutomationApiV10PredictionGenericApi;
import org.openapitools.client.model.*;

public class MLServiceClient {
	
	private ApiClient defaultClient;
	
	public MLServiceClient(String serviceUrl)  {
		defaultClient = Configuration.getDefaultApiClient();
		defaultClient.setBasePath(serviceUrl);
	}
	
	public void admin() {
		AutomationApiV10PredictionAdminApi apiInstance = new AutomationApiV10PredictionAdminApi(defaultClient);
		try {
			apiInstance.getHeartBeat();
		} catch (ApiException e) {
			System.err.println("Exception when calling AutomationApiV10PredictionAdminApi#getHeartBeat");
			System.err.println("Status code: " + e.getCode());
			System.err.println("Reason: " + e.getResponseBody());
			System.err.println("Response headers: " + e.getResponseHeaders());
			e.printStackTrace();
		}
	}		
	
	public ModelResponse predict(ModelKeyDescriptor modelDescriptor, HashMap<String, String> featureMap) {
		AutomationApiV10PredictionGenericApi api = new AutomationApiV10PredictionGenericApi(defaultClient);
		
		ModelInvocation modelInvocation = new ModelInvocation();
		
		modelInvocation.setModel(modelDescriptor);
		modelInvocation.setFeatures(featureMap);
		
		ModelResponse modelResponse = null;
		
		try {
			modelResponse = api.postPredictionService(modelInvocation);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return modelResponse;
	}
}
