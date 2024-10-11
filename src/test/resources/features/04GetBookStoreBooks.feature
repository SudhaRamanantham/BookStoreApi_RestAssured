@BookStoreApi_RestAssured_Sudha_Organized
Feature: Book store books

  @GetBookStoreBooks
  Scenario: Verify user able to get books details from book store
    Given User launch baseUri and sets Authorization as bearer token "bearertoken"
    When User enters valid endpoint "GetStoreBooksEP" with requestType "GET" request
    Then User should get successful 200 status code and "OK" status line
    