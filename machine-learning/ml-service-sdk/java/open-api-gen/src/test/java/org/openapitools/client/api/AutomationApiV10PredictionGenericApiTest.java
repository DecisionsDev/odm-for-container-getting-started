/*
 * API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.ModelInvocation;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for AutomationApiV10PredictionGenericApi
 */
@Ignore
public class AutomationApiV10PredictionGenericApiTest {

    private final AutomationApiV10PredictionGenericApi api = new AutomationApiV10PredictionGenericApi();

    
    /**
     * Computes a new prediction
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void postPredictionServiceTest() throws ApiException {
        ModelInvocation payload = null;
        api.postPredictionService(payload);

        // TODO: test validations
    }
    
}
