Feature: As a user I can add and list tips

    Scenario: user creates a tip
      Given user enters create to add tip
      When  "1", "def", "def", "def", "def", "def", "def", "def" are entered
      Then  tip is created

    Scenario: user lists items
      Given the library has two tips in it
      When  user enters one to list the tips
      Then  in the list printed by the app there are writers "Fredrick Brooks" and "Martin Fowler"

    Scenario: user adds a video
      Given user enters create to add tip
      When  "2", "Software Testing: Why, How, and What", "A guest lecture in ohtu", "Juha Viljanen", "https://www.youtube.com/watch?v=3ZRPtoU_nKQ", "testing, guest", "vierailuluento", "ohjelmistotuotanto" are entered
      When  user enters one to list the tips
      Then  after adding a certain type of item in the list printed by the app there is an item with the type "video"

    Scenario: user adds a blog
      Given user enters create to add tip
      When  "4", "How to Manage JavaSript Fatigue", "A blog post on JavaScript Fatigue", "Kim Maida", "https://auth0.com/blog/how-to-manage-javascript-fatigue/", "javascript, fatigue", "kiinnostavasta aiheesta", "full stack open" are entered
      When  user enters one to list the tips
      Then  after adding a certain type of item in the list printed by the app there is an item with the type "blog"

    Scenario: user adds a podcast
      Given user enters create to add tip
      When  "3", "Designing Data-Intensive Applications: Partioning", "An episode about software design", "Coding blocks", "https://www.codingblocks.net/podcast/designing-data-intensive-applications-partitioning/", "software design, architecture", "miten rakentaa ohjelmia", "none" are entered
      When  user enters one to list the tips
      Then  after adding a certain type of item in the list printed by the app there is an item with the type "podcast"
