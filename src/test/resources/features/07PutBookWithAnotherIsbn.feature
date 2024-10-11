@BookStoreApi_RestAssured_Sudha_Organized

Feature: Put Update book with another Isbn

  @PutBookWAnotherIsbn
  Scenario: Verify user able to update book with another Isbn
    Given User launch baseUri and sets Authorization as bearer token with request body of put request    
    When User enters valid endpoint "PutBookWAnotherIsbnEP" with another Isbn and requestType "PUT" request
    Then User should get successful 200 status code and "OK" status line
    