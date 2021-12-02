Feature: As I user I can add a tip in the library


    Scenario: User creates a tip
      Given User enters create to add tip
      When  "1", "def",  "def", "def", "def", "def", "def"  "def" are entered
      Then tip is created


