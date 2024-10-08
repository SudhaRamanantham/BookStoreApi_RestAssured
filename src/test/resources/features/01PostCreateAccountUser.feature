@BookStoreApi_RestAssured_Sudha_Organized
Feature: Post Create Account User

  @PostCreateAccountUser
  Scenario: Verify user able to create account
    Given User launch baseUri and sets valid username and password in the request body
    When User enters valid endpoint "CreateAccountUserEP" with requestType "POST" request
    Then User should get successful 201 status code and "Created" status line
