Feature: Verify Petstore API
  User can add Pet, Update Pet details and Delete Pets

  Background:
    Given User Sets the find pet by status Get request with "available" status
    When the find pet by status end point is hi and valid response is received
    Then  Retrieve the Available PetID

  Scenario: Find the Pet details using Pet ID
    Given User sets the find pet by ID get request with Pet ID
    When the find pet endpoint is hit and valid response is received
    Then Verify the response received

  Scenario: Update the Pet Name using Pet ID
    Given User sets the Pet Name to be updated with the Pet ID
    When the update pet endpoint is hit and valid response is received
    Then Verify the pet details updated

  Scenario: Delete the pet details using Pet ID
    Given User sets the Pet details to be deleted with the Pet ID
    When the delete pet endpoint is hit and valid response is received
    Then Verify the pet details deleted