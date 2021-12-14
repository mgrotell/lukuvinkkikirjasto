Feature: As a user I can add tips

  Scenario: user creates a tip
    Given user enters create to add tip
    When  "1", "def", "def", "def", "def", "def", "def", "def" are entered
    Then  tip is created

  Scenario: user adds a video
    Given user enters create to add tip
    And   "2", "def", "def", "def", "def", "def", "def", "def" are entered
    When  user enters one to list the tips
    Then  the app has stored an item that has the type "video"

  Scenario: user adds a blog
    Given user enters create to add tip
    And   "4", "def", "def", "def", "def", "def", "def", "def" are entered
    When  user enters one to list the tips
    Then  the app has stored an item that has the type "blog"

  Scenario: user adds a podcast
    Given user enters create to add tip
    And   "3", "def", "def", "def", "def", "def", "def", "def" are entered
    When  user enters one to list the tips
    Then  the app has stored an item that has the type "podcast"
