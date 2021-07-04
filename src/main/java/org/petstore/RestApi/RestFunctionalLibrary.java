package org.petstore.RestApi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import org.apache.log4j.Logger;
import org.junit.Assert;

public class RestFunctionalLibrary {
    private static final Logger log = Logger.getLogger(RestFunctionalLibrary.class);
    private static final FilterableRequestSpecification request = (FilterableRequestSpecification) RestAssured.given();
    private static String sFunName = "";

    public void setBaseURI(String baseURI) {
        sFunName = "setBaseURI";
        log.info("Inside : setBaseURI");
        if (baseURI.isEmpty())
            Assert.fail(sFunName + "Empty Base URI Passed");
        log.info(sFunName + " : Setting Base URI as : " + baseURI);
        request.baseUri(baseURI);
        log.info(sFunName + " : Base URi set successfully ");
    }

    public void setApiKey(String apiKey) {
        sFunName = "setApiKey";
        log.info("Inside : setApiKey");
        request.queryParam("key", apiKey);
        log.info(sFunName + " : Key Added set successfully ");
    }

    public void setContentType(String contentType) {
        sFunName = "setContentType";
        log.info("Inside : setContentType");
        if (contentType.isEmpty())
            Assert.fail(sFunName + "Empty Content Type Passed");
        log.info(sFunName + " : Setting Content Type as : " + contentType);
        if (contentType.contains("JSON"))
            request.contentType(ContentType.JSON);
        if (contentType.contains("urlencoded"))
            request.contentType("application/x-www-form-urlencoded; charset=UTF-8");
        log.info(sFunName + " : ContentType set successfully ");
    }

    public Response getResource(String endPoint) {
        sFunName = "getResource";
        log.info("Inside : getResource");
        if (endPoint.isEmpty())
            Assert.fail(sFunName + "Empty Parameters Cannot be Passed");
        log.info(sFunName + " : Getting the Request");
        Response response = request.given().when().log().all().get(endPoint).then().assertThat().statusCode(200).extract().response();
        log.info(response.getContentType());
        log.info(sFunName + " : Response Type : " + response.asString());
        return response;
    }

    public void setParamsForFindPetByStatus(String status) {
        sFunName = "setParamsForFindPetByStatus";
        log.info("Inside : setParamsForFindPetByStatus");
        if (status.isEmpty())
            Assert.fail(sFunName + "Empty Parameters Cannot be Passed");
        request.queryParam("status", status);
        log.info(sFunName + " : Request Parameter Set");
    }

    public void removeQueryParams(String paramName) {
        sFunName = "removeQueryParams";
        log.info("Inside : removeQueryParams");
        request.removeQueryParam(paramName);
        log.info(sFunName + " : Query Parameter removed");
    }

    public void removePathParams(String paramName) {
        sFunName = "removePathParams";
        log.info("Inside : removePathParams");
        request.removePathParam(paramName);
        log.info(sFunName + " : Path Parameter removed");
    }

    public void removeFormParams(String paramName) {
        sFunName = "removeFormParams";
        log.info("Inside : removeFormParams");
        request.removeFormParam(paramName);
        log.info(sFunName + " : Path Parameter removed");
    }

    public void setPathParamsForFindPetByID(Long id) {
        sFunName = "setParamsForFindPetByID";
        log.info("Inside : setParamsForFindPetByID");
        request.pathParam("id", id);
        log.info(sFunName + " : Path Parameter Set");
    }

    public void setParamsForUpdatingPetDetailsUsingFormParam(String name, String status) {
        sFunName = "setParamsForUpdatingPetDetails";
        log.info("Inside : setParamsForUpdatingPetDetails");
        if (name.isEmpty() || status.isEmpty())
            Assert.fail(sFunName + "Empty Parameters Cannot be Passed");
        request.formParam("name", name);
        request.formParam("status", status);
        request.header("accept", "application/json");
        log.info(sFunName + " : Request Parameter Set");
    }

    public Response postResource(String endPoint) {
        sFunName = "postResource";
        log.info("Inside : postResource");
        if (endPoint.isEmpty())
            Assert.fail(sFunName + "Empty Parameters Cannot be Passed");
        log.info(sFunName + " : Getting the Request");
        log.info(request.getContentType());
        Response response = request.given().when().log().all().post(endPoint).then().extract().response();
        log.info(response.getContentType());
        log.info(sFunName + " : Response Type : " + response.asString());
        return response;
    }

    public Response deleteResource(String endPoint) {
        sFunName = "deleteResource";
        log.info("Inside : deleteResource");
        if (endPoint.isEmpty())
            Assert.fail(sFunName + "Empty Parameters Cannot be Passed");
        Response response = request.given().when().log().all().delete(endPoint).then().assertThat().statusCode(200).extract().response();
        log.info(response.getContentType());
        log.info(sFunName + " : Response Type : " + response.asString());
        return response;
    }
}
