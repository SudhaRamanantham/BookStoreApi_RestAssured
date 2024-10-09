@BookStoreApi_RestAssured_Sudha_Organized
Feature: Create book store book for user with Isbn1

  @GetStoreBooks
  Scenario: Verify user able to create book for user
    Given User launch baseUri and sets Authorization as bearer token "bearertoken" with request body
    When User enters valid endpoint "CreateBookForUserEP" and requestType "POST" request
    Then User get successful 201 status code and "Created" status line
