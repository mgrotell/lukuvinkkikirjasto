Feature: As a user I can add a tip in the library

    Scenario: user creates a tip
      Given user enters create to add tip
      When  "1", "def",  "def", "def", "def", "def", "def"  "def" are entered
      Then  tip is created

    Scenario: user lists items
      Given the library has two tips in it
      When  user enters one to list the tips
      Then  app lists the tips it has stored
