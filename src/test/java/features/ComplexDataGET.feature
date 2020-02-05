Feature: ComplexDataGet
  Verify complex data

  @smoke
  Scenario: Verify GET operation for complex data
    Given I perform authentication operation for "/auth/login" with body
      | email                     | password |
      | sunnyvale121@gmail.com    | mataji21 |
    And I perform GET operation with path parameter for address "/location"
      | id |
      | 1  |
    Then I should see the street name as "1st street" for the "primary" address