Feature: Test the date time functionality

    Background:
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"

    ### Clock ###

    ### Duration ###

    ### Instant ###

    ### LocalDate ###

    ### LocalDateTime ###

    ### LocalTime ###

    ### MonthDay ###

    ### OffsetDateTime ###

    ### OffsetTime ###

    ### Period ###

    ### Year ###

    ### YearMonth ###

    ### ZonedDateTime ###

    Scenario: Test that using a preset ZonedDateTime inside a template will return the toString() value
        Given a template "${obj}"
        And ZonedDateTime object for "2007-12-03T10:15:30+01:00[Europe/Paris]"
        Then expect the template to return "2007-12-03T10:15:30+01:00[Europe/Paris]"

    Scenario: Test that using a preset ZonedDateTime inside a template will return the default time zone
        Given a template "${obj.format()}"
        And ZonedDateTime object for "2007-12-03T16:15:30+07:00[Asia/Bangkok]"
        Then expect the template to return "2007-12-03T10:15:30+01:00[Europe/Oslo]"

    Scenario: Test that using a preset ZonedDateTime inside a template will return the default time zone when a pattern and zone id is given
        Given a template "${obj.format('yyyy-MM-dd Z', 'Asia/Seoul')}"
        And ZonedDateTime object for "2007-12-03T16:15:30+07:00[Asia/Bangkok]"
        Then expect the template to return "2007-12-03 +0900"

    ### ZoneId ###

    Scenario: Test that using ZoneId inside a template will return the toString() value
        Given a template "${obj}"
        And a ZoneId object for 'Europe/Oslo'
        Then expect the template to return "Central European Time"

    Scenario: Test that using ZoneId inside a template will return the toString() value
        Given a template "${obj.format('short')}"
        And a ZoneId object for 'Europe/Oslo'
        Then expect the template to return "CET"


    ### ZoneOffset ###