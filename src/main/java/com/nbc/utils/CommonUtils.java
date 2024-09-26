package com.nbc.utils;

import com.nbc.core.RestClient;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CommonUtils extends RestClient{
    private static final Logger LOG = LogManager.getLogger(CommonUtils.class);

    /**
     * This method is used to verify the response status
     *
     * @param code -- response code that need to be verified
     */
    public void verifyStatusCode(Response response,int code) {
        try {
            Assert.assertEquals(code, response.getStatusCode());
            LOG.info("Response status code is verified");
        } catch (AssertionError e) {
            LOG.error("Response status code is wrong");
            throw new AssertionError(e.getMessage());
        }
    }

    /**
     * This method validates the schema of a json response
     *
     * @param schema -- expected json schema that need to be validated
     */
    public void validateJsonSchema(String schema) {
        InputStream petDetailsJsonSchema = null;
        try {
            petDetailsJsonSchema = new FileInputStream(PropertyReader.getProperty(RestClient.propertyFilePath, "schemaPath") + schema);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Assert.assertNotNull(petDetailsJsonSchema);
            response.then()
                    .assertThat().
                    body(JsonSchemaValidator.matchesJsonSchema(petDetailsJsonSchema));
            LOG.info(" Json schema is verified");
        } catch (AssertionError e) {
            LOG.error(" Json schema is wrong");
            throw new AssertionError(e.getMessage());
        }
    }

    /**
     * This method used to validate the field value from api response
     *
     * @param propertyName  - name of the field
     * @param propertyValue - value of the field
     */
    public void userValidatesThePropertyFromResponse(Response response,String propertyName, String propertyValue) {
        try {
            Assert.assertTrue(response.jsonPath().get(propertyName).toString().equalsIgnoreCase(propertyValue));
            LOG.info("{} from response is verified", propertyName);
        } catch (AssertionError e) {
            throw new RuntimeException(e);
        }
    }

}
