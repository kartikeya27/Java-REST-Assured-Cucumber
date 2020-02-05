Feature:
  Verify different GET operations using Rest-Assured

  Scenario: Verify one author of the post
    Given I perform GET operation for "/posts"
    Then I should see the author name as "Kartik Bhatt"

  Scenario: Verify collection of the authors in the post
    Given I perform GET operation for "/posts"
    Then I should see the author names

  Scenario: Verify Parameter of Get
    Given I perform GET operation for "/posts"
    Then I should see verify GET Parameter

  @smoke
  Scenario: Verify GET operation with Bearer authentication token
    Given I perform authentication operation for "/auth/login" with body
      | email                     | password |
      | sunnyvale121@gmail.com    | mataji21 |
    Given I perform GET operation for "/posts/1"
    Then I should see the author name as "Kartik Bhatt"

  @smoke
  Scenario: Verify GET operation with Bearer authentication token for json schema validation
    Given I perform authentication operation for "/auth/login" with body
      | email                     | password |
      | sunnyvale121@gmail.com    | mataji21 |
    Given I perform GET operation for "/posts/1"
    Then I should see the author name as "Kartik Bhatt" with json validation


