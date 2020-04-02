# AutomationApiV10PredictionGenericApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**postPredictionService**](AutomationApiV10PredictionGenericApi.md#postPredictionService) | **POST** /automation/api/v1.0/prediction/generic/ | Computes a new prediction


<a name="postPredictionService"></a>
# **postPredictionService**
> ModelResponse postPredictionService(payload)

Computes a new prediction

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AutomationApiV10PredictionGenericApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    AutomationApiV10PredictionGenericApi apiInstance = new AutomationApiV10PredictionGenericApi(defaultClient);
    ModelInvocation payload = new ModelInvocation(); // ModelInvocation | 
    try {
      ModelResponse result = apiInstance.postPredictionService(payload);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AutomationApiV10PredictionGenericApi#postPredictionService");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **payload** | [**ModelInvocation**](ModelInvocation.md)|  |

### Return type

[**ModelResponse**](ModelResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | Category successfully created. |  -  |

