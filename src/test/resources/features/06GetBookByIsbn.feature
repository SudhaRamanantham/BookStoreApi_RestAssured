@BookStoreApi_RestAssured_Sudha_Organized
Feature: Get Check book details by Isbn1

  @GetBookByIsbn1
  Scenario: Verify user able to get book details by Isbn1
    Given User launch baseUri, sets Authorization as bearer token "bearerToken" and query parameter
    When User enters valid endpoint "GetBookByIsbnEP" with query parameter and requestType "GET" request
    Then User should get successful 200 status code and "OK" status line
    