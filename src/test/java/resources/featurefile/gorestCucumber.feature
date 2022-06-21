Feature: testing gorest application

  Scenario Outline:
    When I create a new user by providing the information name "<name>"  gender "<gender>" email "<email>" status "<status>"
    And I verify that the user with id has been created
    And I updated a name of the user created
    And I verify that the user name has been updated
    And I delete user with same id
    Then I verify that the user has been deleted
    Examples:
      | name | gender | email             | status |
      | Ron  | male   | ronjira@gmail.com | Active |
