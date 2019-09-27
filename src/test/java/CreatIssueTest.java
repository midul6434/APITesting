import RestAssuredCore.BaseAssertion;
import RestAssuredCore.BaseTest;
import RestAssuredCore.RESTCalls;
import Utlities.PayloadGenerator;
import Utlities.URL;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class CreatIssueTest {

    @Test
    public void CreatIssue(){
        String sessionI = BaseTest.doLogin();
        String payload = PayloadGenerator.generatePayLoadString("CreateIssue.json");
        String url = URL.getEndPoint("/rest/api/2/issue/");
        Response response = RESTCalls.POSTRequest(url,payload,sessionI);
        BaseAssertion.verifyStatusCode(response,201);

    }

    @Test
    public void GettingMessage(){
        String seesionId= BaseTest.doLogin();
        String payload = PayloadGenerator.generatePayLoadString("SubTask.json");
        String url = URL.getEndPoint("/rest/api/2/issue/");
        Response response=RESTCalls.POSTRequest(url,payload,seesionId);
        BaseAssertion.verifyResponseBody(response,"HTTP/1.1 400 ");
    }


}
