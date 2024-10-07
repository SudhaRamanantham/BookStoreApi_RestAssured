@BookStoreApi_RestAssured_Sudha_Organized
Feature: Get created user details by userID

  @GetUserByUserId
  Scenario: Verify user able to get user details by userId
    Given User launch baseUri and sets Authorization as bearer token "bearertoken"
    When User enters valid endpoint "GetUserByUserIDEP" with UserId and requestType "GET" request
    Then User get successful 200 status code and "OK" status line
