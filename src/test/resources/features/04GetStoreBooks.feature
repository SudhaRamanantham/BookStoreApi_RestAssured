@BookStoreApi_RestAssured_Sudha_Organized
Feature: Get book store books

  @GetStoreBooks
  Scenario: Verify user able to get books from book store
    Given User launch baseUri and sets Authorization as bearer token "bearertoken"
    When User enters valid endpoint "GetStoreBooksEP" with UserId and requestType "GET" request
    Then User get successful 200 status code and "OK" status line
