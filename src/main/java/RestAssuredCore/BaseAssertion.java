package RestAssuredCore;

import Utlities.TestUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;



public class BaseAssertion {
    private static Logger log = LogManager.getLogger(TestUtils.class.getName());



    public static void verifyTrue(boolean flag){
        Assert.assertTrue(flag);
    }

    public static void verifyFalse(boolean flag){
        Assert.assertFalse(flag);
    }

    public static void verifyStatusCode(Response response, int status){
        Assert.assertEquals(TestUtils.getStatusCode(response), status);
    }

    public static void verifyStatusMessage(Response response, String status){
        Assert.assertEquals(TestUtils.getStatusMessage(response), status);
    }
    public static void verifyResponseBody(Response response, String responseBody){
        Assert.assertEquals(TestUtils.getStatusMessage(response), responseBody);
    }
    public static void verifyResonseBodyByJsonPath(Response response, String jsonPath, String expectedKeyValue){

        JsonPath jsonPathEvaluator = response.jsonPath();

        String actualKeyValue  = jsonPathEvaluator.get(jsonPath);


        log.info("Actual Key Value received from Response:  " + actualKeyValue);


        Assert.assertEquals(actualKeyValue, expectedKeyValue, "Correct value received in the Response");

        log.info("Response Assertion Successful");

    }
    public static void verifyResponseHeader(Response response, String headerKey, String headerValue){

        log.info(response.header(headerKey).toString());

        Assert.assertEquals(response.header(headerKey).matches(headerValue), true);

        log.info("Header "+ headerKey + " = " + headerValue +" available");
    }

}

