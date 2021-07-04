package org.ebay.Steps;

import Utils.CSVUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.petstore.RestApi.RestFunctionalLibrary;

import java.util.List;

public class petstoreAPIVerification {
    private static final Logger log = Logger.getLogger(petstoreAPIVerification.class);
    private static final RestFunctionalLibrary rApi = new RestFunctionalLibrary();
    private Response response;
    private Long petID;

    @Given("^User Sets the find pet by status Get request with \"([^\"]*)\" status$")
    public void user_Sets_the_find_pet_by_status_Get_request_with_status(String status) throws Throwable {
        log.info("Set the findByStatus Api status to : " + status);
        rApi.setParamsForFindPetByStatus(status);
    }

    @When("^the find pet by status end point is hi and valid response is received$")
    public void the_find_pet_by_status_end_point_is_hi_and_valid_response_is_received() throws Throwable {
        log.info("Hit the findByStatus Api");
        response = rApi.getResource(CSVUtil.getTestData("statusEndPoint"));
        rApi.removeQueryParams("status");
        JsonPath jsonPathEvaluator = response.jsonPath();
        System.out.println(jsonPathEvaluator.getList("$"));
    }

    @Given("^User sets the find pet by ID get request with Pet ID$")
    public void user_sets_the_find_pet_by_ID_get_request_with_Pet_ID() throws Throwable {
        log.info("Set the pet Api with pet id : " + petID);
        rApi.setPathParamsForFindPetByID(petID);
    }

    @When("^the find pet endpoint is hit and valid response is received$")
    public void the_find_pet_endpoint_is_hit_and_valid_response_is_received() throws Throwable {
        log.info("Hit the pet Api");
        response = rApi.getResource(CSVUtil.getTestData("petEndPoint"));
        rApi.removePathParams("id");
    }

    @Then("^Retrieve the Available PetID$")
    public void retrieve_the_Available_PetID() throws Throwable {
        log.info("Retrieve and Store Pet Id");
        int i = 0;
        List<Long> petIDs = response.jsonPath().get("id");
        if (petIDs.size() <= 0) {
            log.info("Pet ID Not Found");
            Assert.fail();
        } else {
            petID = ((Number) petIDs.get(petIDs.size() - 1)).longValue();
            log.info("First Available pet ID  : " + petID);
        }
    }

    @Then("^Verify the response received$")
    public void verify_the_response_received() throws Throwable {
        log.info("Verifying the response received");
        JsonPath jsonPathEvaluator = response.jsonPath();
        Long petIDReturned = response.jsonPath().get("id");
        if (petIDReturned.equals(petID)) {
            log.info("Pet end point responded successfully for Pet ID : " + petIDReturned + " Pet ID Passed: " + petID);
        } else {
            Assert.fail("Pet ID is not returned correctly");
        }
    }


    @Given("^User sets the Pet Name to be updated with the Pet ID$")
    public void user_sets_the_Pet_Name_to_be_updated_with_the_Pet_ID() throws Throwable {
        log.info("set the name and status for the updating the pet");
        rApi.setPathParamsForFindPetByID(petID);
        rApi.setContentType("urlencoded");
        rApi.setParamsForUpdatingPetDetailsUsingFormParam(CSVUtil.getTestData("name"), CSVUtil.getTestData("status"));
    }

    @When("^the update pet endpoint is hit and valid response is received$")
    public void the_update_pet_endpoint_is_hit_and_valid_response_is_received() throws Throwable {
        log.info("Post the update pet request");
        response = rApi.postResource(CSVUtil.getTestData("petEndPoint"));
        rApi.removePathParams("id");
        rApi.removeFormParams("name");
        rApi.removeFormParams("status");
    }

    @Then("^Verify the pet details updated$")
    public void verify_the_pet_details_updated() throws Throwable {
        log.info("Verifying the Pet Details updated");
        JsonPath jsonPathEvaluator = response.jsonPath();
        String petIDReturned = response.jsonPath().get("message");
        log.info("Updated the Pet Details for the ID : " + petIDReturned);
        if (petIDReturned.equalsIgnoreCase(Long.toString(petID))) {
            log.info("Successfully update the Pet ID : " + petID);
        } else {
            log.info("Not Successfully updated the Pet ID : " + petID);
            Assert.fail();
        }
    }

    @Given("^User sets the Pet details to be deleted with the Pet ID$")
    public void user_sets_the_Pet_details_to_be_deleted_with_the_Pet_ID() throws Throwable {
        log.info("set the pet id for deletion");
        rApi.setPathParamsForFindPetByID(petID);
        rApi.setApiKey(CSVUtil.getTestData("key"));
    }

    @When("^the delete pet endpoint is hit and valid response is received$")
    public void the_delete_pet_endpoint_is_hit_and_valid_response_is_received() throws Throwable {
        log.info("Hit the delte end point");
        response = rApi.deleteResource(CSVUtil.getTestData("petEndPoint"));
        rApi.removePathParams("id");
    }

    @Then("^Verify the pet details deleted$")
    public void verify_the_pet_details_deleted() throws Throwable {
        log.info("Verify pet successfully deleted");
        JsonPath jsonPathEvaluator = response.jsonPath();
        String petIDReturned = response.jsonPath().get("message");
        log.info("Updated the Pet Details for the ID : " + petIDReturned);
        if (petIDReturned.equalsIgnoreCase(Long.toString(petID))) {
            log.info("Successfully deleted the Pet ID : " + petID);
        } else {
            log.info("Not Successfully deleted the Pet ID : " + petID);
            Assert.fail();
        }
    }
}
