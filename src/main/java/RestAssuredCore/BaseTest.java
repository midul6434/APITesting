package RestAssuredCore;

import Utlities.PayloadGenerator;
import Utlities.TestUtils;
import Utlities.URL;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class BaseTest {
	
	private static Logger log = LogManager.getLogger(BaseTest.class.getName());
	
	public static String doLogin(){
		Response response;

		log.info("Starting Test Case : doLogin");

		String loginPayload = PayloadGenerator.generatePayLoadString("Login.json");
		
		String endPointURI = URL.getEndPoint("/rest/auth/1/session");

		response = RESTCalls.POSTRequest(endPointURI, loginPayload);
		log.info("Login Response Body: "+response.getBody().asString());

		String strResponse = TestUtils.getResposeString(response);
		JsonPath jsonRes = TestUtils.jsonParser(strResponse);
		String sessionID = jsonRes.getString("session.value");
		log.info("JIRA JSession ID : " + sessionID);
		return sessionID;
	}

}
