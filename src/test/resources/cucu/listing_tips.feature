Feature: As a user I can list tips

    Scenario: user lists items
      Given the library has two tips in it
      When  user enters one to list the tips
      Then  printed list has items by "Fredrick Brooks" and "Martin Fowler"