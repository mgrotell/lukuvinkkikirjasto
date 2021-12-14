Feature: As a user I can search for tips

  Scenario: user can search for a book by Martin Fowler
    Given the library has two tips in it
    And   user chooses searchs tips
    And   user chooses search by author
    When  user enters search term "Martin Fowler"
    Then  prints out a list with items by "Martin Fowler" and no entries by "Fredrick Brooks"