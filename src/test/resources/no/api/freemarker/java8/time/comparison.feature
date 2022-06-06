Feature: Test the implemented comparison functions (isEqual, isBefore and isAfter)

    # isEqual

    Scenario: Test isEqual for LocalDate
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.isEqual(obj2)?c}"
        And LocalDate object for "2007-12-03"
        And LocalDate object2 for "2007-12-03"
        Then expect the template to return true

    Scenario: Test that is isEqual for LocalDate fails
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.isEqual(obj2)?c}"
        And LocalDate object for "2007-12-03"
        And LocalDate object2 for "2007-12-02"
        Then expect the template to return false

    Scenario: Test isEqual for LocalDateTime
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.isEqual(obj2)?c}"
        And LocalDateTime object for "2007-12-03T10:15:30"
        And LocalDateTime object2 for "2007-12-03T10:15:30"
        Then expect the template to return true

    Scenario: Test that is isEqual for LocalDateTime fails
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.isEqual(obj2)?c}"
        And LocalDateTime object for "2007-12-03T10:15:30"
        And LocalDateTime object2 for "2007-12-03T10:15:31"
        Then expect the template to return false

    Scenario: Test isEqual for LocalTime
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.isEqual(obj2)?c}"
        And LocalTime object for "23:44"
        And LocalTime object2 for "23:44"
        Then expect the template to return true

    Scenario: Test that is isEqual for LocalTime fails
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.isEqual(obj2)?c}"
        And LocalTime object for "23:44"
        And LocalTime object2 for "23:45"
        Then expect the template to return false

    # isAfter

    Scenario: Test isAfter for LocalDate
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.isAfter(obj2)?c}"
        And LocalDate object for "2017-12-04"
        And LocalDate object2 for "2007-12-03"
        Then expect the template to return true

    Scenario: Test that is isAfter for LocalDate fails
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.isAfter(obj2)?c}"
        And LocalDate object for "2007-12-01"
        And LocalDate object2 for "2007-12-02"
        Then expect the template to return false

    Scenario: Test isAfter for LocalDateTime
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.isAfter(obj2)?c}"
        And LocalDateTime object for "2007-12-04T10:15:30"
        And LocalDateTime object2 for "2007-12-03T10:15:30"
        Then expect the template to return true

    Scenario: Test that is isAfter for LocalDateTime fails
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.isAfter(obj2)?c}"
        And LocalDateTime object for "2007-12-01T10:15:30"
        And LocalDateTime object2 for "2007-12-03T10:15:31"
        Then expect the template to return false

    Scenario: Test isAfter for LocalTime
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.isAfter(obj2)?c}"
        And LocalTime object for "23:45"
        And LocalTime object2 for "23:44"
        Then expect the template to return true

    Scenario: Test that is isAfter for LocalTime fails
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.isAfter(obj2)?c}"
        And LocalTime object for "23:44"
        And LocalTime object2 for "23:45"
        Then expect the template to return false
        
    # isBefore

    Scenario: Test isBefore for LocalDate
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.isBefore(obj2)?c}"
        And LocalDate object for "2002-12-01"
        And LocalDate object2 for "2007-12-03"
        Then expect the template to return true

    Scenario: Test that is isBefore for LocalDate fails
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.isBefore(obj2)?c}"
        And LocalDate object for "2007-12-03"
        And LocalDate object2 for "2007-12-02"
        Then expect the template to return false

    Scenario: Test isBefore for LocalDateTime
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.isBefore(obj2)?c}"
        And LocalDateTime object for "2007-12-01T10:15:30"
        And LocalDateTime object2 for "2007-12-03T10:15:30"
        Then expect the template to return true

    Scenario: Test that is isBefore for LocalDateTime fails
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.isBefore(obj2)?c}"
        And LocalDateTime object for "2007-12-06T10:15:30"
        And LocalDateTime object2 for "2007-12-03T10:15:31"
        Then expect the template to return false

    Scenario: Test isBefore for LocalTime
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.isBefore(obj2)?c}"
        And LocalTime object for "22:41"
        And LocalTime object2 for "23:44"
        Then expect the template to return true

    Scenario: Test that is isBefore for LocalTime fails
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.isBefore(obj2)?c}"
        And LocalTime object for "23:54"
        And LocalTime object2 for "23:45"
        Then expect the template to return false

    Scenario: Test that instant is printed correctly with Freemarker 2_3_31
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.format('yyyy-MM-dd HH: mm : ss Z', 'Europe/Oslo')}"
        And Instant object for "2022-06-01T16:17:45Z"
        Then expect the template to return "2022-06-01 18: 17 : 45 +0200"