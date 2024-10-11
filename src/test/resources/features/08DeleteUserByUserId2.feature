@BookStoreApi_RestAssured_Sudha_Organized

Feature: Delete user account by userId

  @DeleteUserByUserId
  Scenario: Verify user able to get user details by userId
    Given User launch baseUri and sets Authorization as bearer token "bearertoken"
    When User enters valid endpoint "DeleteUserByUserIdEP" with UserId and requestType "DELETE" request
    #Then User get successful 200 status code and "OK" status line
    Then User should get successful 204 status code and "No Content" status line
  