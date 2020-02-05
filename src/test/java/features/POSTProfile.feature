Feature:
  Test POST operations using Rest-Assured

  Scenario: Verify Post Operation
   Given I perform POST operation for "/posts"

  Scenario: Verify Post Operation For Profile
    Given I perform POST operation for "/posts/{profileNo}/profile" with body
      | name   | profile |
      | Jacobs | 2       |
    Then I should see the body has name as "Jacob"