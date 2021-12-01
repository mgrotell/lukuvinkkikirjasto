Feature: As I user I can add a tip in the library

  Scenario: User adds a tip

    Given User enters 2 to add tip

    When user adds tip into the library

    Then the library should contain the added tip


  Scenario: User wants to see tips

    Given the tip library is intialized

    When user adds tip into the library

    Then the user should see the contents of library