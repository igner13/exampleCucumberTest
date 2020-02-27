Feature: Dummy rest api example - user creation


  @ApiTests
  Scenario Outline: Create user and update salary
    Given user name is: <userName>, salary is: <salary> and age is <age>
    And user is created in restapiexample system
    When salary is changed to <newSalary>
    Then user should be updated in restapiexample system
    Examples:
      | userName | salary | age | newSalary |
      | user1    | 1111   | 24  | 1122      |
      | user2    | 1111   | 26  | 999       |