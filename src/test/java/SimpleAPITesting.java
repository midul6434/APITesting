import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimpleAPITesting {

public String login() throws IOException {
    String url="http://localhost:8091/rest/auth/1/session";
    String payloads= new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/resources/Login.json")));
    RequestSpecification requestSpecification =  RestAssured.given().body(payloads);
    requestSpecification.contentType("application/json");
    Response response =  requestSpecification.post(url);

    String stringResponse =response.asString();
    JsonPath jsonPath = new JsonPath(stringResponse);
    String sessionID = jsonPath.get("session.value");
   return sessionID;
}
    @Test
    public void CreatIssue() throws IOException {
        SimpleAPITesting obj = new SimpleAPITesting();
        String sessionId = obj.login();
        String url ="http://localhost:8091/rest/api/2/issue/";

        String payload = new String(Files.readAllBytes(Paths.get("/Users/midul/IdeaProjects/APITesting/resources/CreateIssue.json")));
        RequestSpecification requestSpecification = RestAssured.given().body(payload);
        requestSpecification.contentType("application/json");
        requestSpecification.header("Cookie","JSESSIONID="+sessionId);
        Response response = requestSpecification.post(url);
        String stringResponse = response.asString();

        JsonPath jsonPath = new JsonPath(stringResponse);
        String actual=jsonPath.get("id");
        Assert.assertEquals(actual,"10107" );

    }
}
