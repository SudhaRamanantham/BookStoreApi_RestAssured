@BookStoreApi_RestAssured_Sudha_Organized
Feature: Post Generate Token

  @PostGenerateToken
  Scenario: Verify user able to generate token
    Given User launch baseUri and sets valid username and password in the request body
    When User enters valid endpoint "GenerateTokenEP" with requestType "POST" request
    Then User get successful 200 status code and "OK" status line
