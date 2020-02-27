package implementation;

import enums.APIs;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static enums.Headers.COOKIE;
import static enums.Params.*;
import static io.restassured.RestAssured.given;
import static utils.PropertyReader.getAuthorizationHeader;
import static utils.PropertyReader.getBaseUrl;

public class UserCreationImpl {

    public HashMap<String, String> prepareRequestBody(String name, String salary, String age) {
        return new HashMap<String, String>() {
            {
                put(NAME.getParam(), name);
                put(SALARY.getParam(), salary);
                put(AGE.getParam(), age);
            }
        };
    }

    public RequestSpecification preparePostRequest(HashMap<String, String> body, APIs api) {
        return new RequestSpecBuilder()
                .setBaseUri(getBaseUrl())
                .setBasePath(api.getApi())
                .addHeader(COOKIE.getHeader(), getAuthorizationHeader())
                .setContentType(ContentType.JSON)
                .setBody(body)
                .build();
    }

    public RequestSpecification preparePutRequest(HashMap<String, String> body, APIs api, String userId) {
        return new RequestSpecBuilder()
                .setBaseUri(getBaseUrl())
                .setBasePath(api.getApi() + userId)
                .addHeader(COOKIE.getHeader(), getAuthorizationHeader())
                .setContentType(ContentType.JSON)
                .setBody(body)
                .build();
    }

    public RequestSpecification prepareGetRequest(APIs api, String userId) {
        return new RequestSpecBuilder()
                .setBaseUri(getBaseUrl())
                .setBasePath(api.getApi() + userId)
                .addHeader(COOKIE.getHeader(), getAuthorizationHeader())
                .setContentType(ContentType.JSON)
                .build();
    }

    public Response postRequest(RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .post()
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public Response putRequest(RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .put()
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public Response getRequest(RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}
