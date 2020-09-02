package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SuppressWarnings({"rawtypes","unchecked"}) @Log4j
public class RestAssuredUtil {

    private static RequestSpecification httpRequest;
    private static FileReaderUtil fileReaderUtil=new FileReaderUtil();

    //-------------------------Private Methods--------------------------------------//

    /**
     * @Desc  Converts map to string
     * @param mapBody
     * @return
     * @throws JsonProcessingException
     */
    private static String convertMapToString(HashMap<String, String> mapBody) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(mapBody);
    }

    private static String baseURI(Properties prop, String key) {
        return fileReaderUtil.getProperty(prop,key);
    }

    /**
     * @Desc Set request header for authorization key and value
     * @param authKey
     * @return
     */
    private static Header setAuthorizationHeader(String authKey) {
        return new Header("Authorization",authKey);
    }


    //-------------------------Public Methods----------------------------------------//

    //---------------------------Bearer token auth methods----------------------------//

    /**
     * @Desc  Hit GET Request for given endpoint and authorization key
     * @param strEndpoint
     * @param authKey
     * @return Response
     */
    public static Response getRequest(String strEndpoint,String authKey) {

        LoggerUtil.logInfo("Hitting GET request for endpoint:"+strEndpoint,log);
        Header authorizationHeader = setAuthorizationHeader(authKey);
        Response response = given()
                .header(authorizationHeader)
                .when()
                .get(strEndpoint);

        return response; //returns response
    }

    /**
     * @Desc  Hit GET Request for given endpoint, authorization key and query parameters
     * @param strEndpoint
     * @param authKey
     * @param querParams
     * @return
     */
    public static Response getRequest(String strEndpoint, String authKey, Map<String, String> querParams)  {

        //Bearer token required by API
        Header authorizationHeader = setAuthorizationHeader(authKey);
        Response response = given()
                .header(authorizationHeader)
                .queryParams(querParams)
                .when()
                .get(strEndpoint);
        return response; //returns response
    }

    /**
     * @Desc  Hit POST Request for given endpoint, authorization key and query parameters
     * @param strEndpoint
     * @param authKey
     * @param querParams
     * @return
     */
    public static Response postRequest(String strEndpoint, String authKey, Map<String, String> querParams)  {

        Header authorizationHeader = setAuthorizationHeader(authKey);
        Response response = given()
                .header(authorizationHeader)
                .queryParams(querParams)
                .when()
                .post(strEndpoint);
        return response; //returns response
    }

    /**
     * @Desc  Hit POST Request for given endpoint, authorization key and Map as payload
     * @param strEndpoint
     * @param authKey
     * @param mapBody
     * @return
     * @throws JsonProcessingException
     */
    public static Response postRequest(ContentType contentType, String strEndpoint, String authKey, HashMap<String, String> mapBody) throws JsonProcessingException {

        Header authorizationHeader = setAuthorizationHeader(authKey);
        Response response = given()
                .header(authorizationHeader)
                .accept(contentType)
                .contentType(contentType)
                .body(convertMapToString(mapBody))
                .when()
                .post(strEndpoint);
        return response;
    }

    /**
     * @Desc  Hit POST Request for given endpoint, authorization key and String as payload
     * @param contentType
     * @param strEndpoint
     * @param authKey
     * @param payloadBody
     * @return
     */
    public static Response postRequest(ContentType contentType, String strEndpoint, String authKey, String payloadBody) {

        Header authorizationHeader = setAuthorizationHeader(authKey);
        Response response = given()
                .header(authorizationHeader)
                .accept(contentType)
                .contentType(contentType)
                .body(payloadBody)
                .when()
                .post(strEndpoint);
        return response;
    }


    //------------------------Basic Auth methods------------------------------------------//

    /**
     * @Desc  Hit GET Request for given endpoint, basic auth and without query parameters
     * @param baseURI
     * @param strEndpoint
     * @param username
     * @param password
     * @return
     */
    public static Response getResponse(String baseURI, String strEndpoint,String username, String password) {

        LoggerUtil.logInfo("Hitting GET request for endpoint:"+strEndpoint,log);

        Response response = given()
                .baseUri(baseURI)
                .auth()
                .preemptive()
                .basic(username, password)
                .when()
                .get(strEndpoint);

        return response; //returns response
    }

    /**
     * @Desc  Hit GET Request for given endpoint, basic auth and with query parameters
     * @param baseURI
     * @param strEndpoint
     * @param username
     * @param password
     * @param querParams
     * @return
     */
    public static Response getResponse(String baseURI, String strEndpoint, String username, String password, Map<String, String> querParams)  {

        Response response = given()
                .baseUri(baseURI)
                .auth()
                .preemptive()
                .basic(username, password)
                .queryParams(querParams)
                .when()
                .get(strEndpoint);
        return response; //returns response
    }

    /**
     * @Desc  Hit POST Request for given endpoint, basic auth and query parameters
     * @param baseURI
     * @param strEndpoint
     * @param username
     * @param password
     * @param querParams
     * @return
     */
    public static Response postResponse(String baseURI, String strEndpoint, String username, String password, Map<String, String> querParams)  {

        Response response = given()
                .baseUri(baseURI)
                .auth()
                .preemptive()
                .basic(username, password)
                .queryParams(querParams)
                .when()
                .post(strEndpoint);
        return response; //returns response

    }

    /**
     * @Desc  Hit POST Request for given endpoint, basic auth and with map object as payload
     * @param contentType
     * @param baseURI
     * @param strEndpoint
     * @param username
     * @param password
     * @param mapBody
     * @return
     * @throws JsonProcessingException
     */
    public static Response postResponse(ContentType contentType, String baseURI, String strEndpoint, String username, String password, HashMap<String, String> mapBody) throws JsonProcessingException {

        Response response = given()
                .baseUri(baseURI)
                .auth()
                .preemptive()
                .basic(username, password)
                .accept(contentType)
                .contentType(contentType)
                .body(convertMapToString(mapBody))
                .when()
                .post(strEndpoint);
        return response;
    }

    /**
     * @Desc  Hit POST Request for given endpoint, basic auth and String as payload
     * @param contentType
     * @param baseURI
     * @param strEndpoint
     * @param username
     * @param password
     * @param payloadBody
     * @return
     */
    public static Response postResponse(ContentType contentType, String baseURI, String strEndpoint, String username, String password, String payloadBody) {

        Response response = given()
                .baseUri(baseURI)
                .auth()
                .preemptive()
                .basic(username, password)
                .accept(contentType)
                .contentType(contentType)
                .body(payloadBody)
                .when()
                .post(strEndpoint);
        return response;
    }




    //-----------------validation methods----------------------------------------//


    public static void verifyNodevalue(Response res, String node, Object expectedNodeValue) {
        res.then().body(node, equalTo(expectedNodeValue));

    }

    public static boolean isNodePresent(Response res, String node) {

        JsonPath j = new JsonPath(res.asString());
        if (j.getJsonObject(node) == null) {
            return false;
        } else {
            return true;
        }
    }

    public static void assertNodePresent(Response res, String node) {
        Assert.assertEquals(isNodePresent(res, node), true);
    }

    public static void assertNodeNotPresent(String node, Response res) {
        Assert.assertEquals(isNodePresent(res, node), false);
    }

    public static Object getNodeValue(Response res, String node) {
        String json = res.asString();
        return JsonPath.with(json).get(node);
    }

    public static List getNodeValues(Response res, String node) {
        String json = res.asString();
        return JsonPath.with(json).get(node);
    }

    public static boolean isInList(List li, Object o) {

        return li.contains(o);
    }


}
