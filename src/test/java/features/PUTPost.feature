Feature: PUTPost
  Test the PUT post operation

  @smoke
  Scenario: Verify PUT operation after POST
    Given I ensure to perform POST operation for "/posts" with body as
      | id | title                 | author             |
      | 6  | API Testing Framework | Execute Automation |
    And I perform PUT operation for "/posts/{postid}/"
      | id | title       | author             |
      | 6  | API Testing Guide | Execute Automation |
    And I perform GET operation with path parameter for "posts/{postid}"
      | postid |
      | 6      |
    Then I "should" see the body  with title as "API Testing Guide"