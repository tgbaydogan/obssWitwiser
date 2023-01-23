Feature: Create Test Session in Witwiser

  Scenario: Scenario1: Create Session
    Given open "chrome"
    Then go to "https://testing-app.witwiser.io/home"
    And type username as "witwises-admin" and password as "lLYVRayAHimpt9bJ"
    Then click login
    Then click session
    Then click create session
    Then fill in the fields
    Then click exam name
    Then click start date
    Then click today date
    Then click end date
    Then click finish date
    Then select assignment type
    Then select single assignment
    Then write candidate username as "Super Admin"
    Then create session


  Scenario: Scenario2: Solve Test Questions
    Given go to homepage
    Then start test
    Then click start test button
    Then solve questions
    Then finish test
    Then end test

