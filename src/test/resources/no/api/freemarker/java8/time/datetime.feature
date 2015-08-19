Feature: Test the date time functionality

    Background:
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"

    ### Clock ###
    Scenario: Test basic Clock use in template
        Given a template "${obj}"
        And Clock object for "2007-12-03T10:15:30.00Z"
        Then expect the template to return "FixedClock[2007-12-03T10:15:30Z,Europe/Oslo]"


    ### Duration ###
    Scenario: Test basic Duration use in template
        Given a template "${obj}"
        And Duration object for "PT15M"
        Then expect the template to return "PT15M"


    ### Instant ###
    Scenario: Test basic Instant use in template
        Given a template "${obj}"
        And Instant object for "2007-12-03T10:15:30.00Z"
        Then expect the template to return "2007-12-03T10:15:30Z"


    ### LocalDate ###
    Scenario: Test basic LocalDate use in template
        Given a template "${obj}"
        And LocalDate object for "2007-12-03"
        Then expect the template to return "2007-12-03"


    ### LocalDateTime ###
    Scenario: Test basic LocalDateTime use in template
        Given a template "${obj}"
        And LocalDateTime object for "2007-12-03T10:15:30"
        Then expect the template to return "2007-12-03T10:15:30"


    ### LocalTime ###
    Scenario: Test basic LocalTime use in template
        Given a template "${obj}"
        And LocalTime object for "23:44"
        Then expect the template to return "23:44"


    ### MonthDay ###
    Scenario: Test basic MonthDay use in template
        Given a template "${obj}"
        And MonthDay object for "--03-14"
        Then expect the template to return "--03-14"


    ### OffsetDateTime ###
    Scenario: Test basic OffsetDateTime use in template
        Given a template "${obj}"
        And OffsetDateTime object for "2007-12-03T10:15:30+01:00"
        Then expect the template to return "2007-12-03T10:15:30+01:00"


    ### OffsetTime ###
    Scenario: Test basic OffsetTime use in template
        Given a template "${obj}"
        And OffsetTime object for "23:44+01:00"
        Then expect the template to return "23:44+01:00"


    ### Period ###
    Scenario: Test basic Period use in template
        Given a template "${obj}"
        And Period object for "P1Y2M3D"
        Then expect the template to return "P1Y2M3D"

    ### Year ###
    Scenario: Test basic Year use in template
        Given a template "${obj}"
        And Year object for "2007"
        Then expect the template to return "2007"

    Scenario: Test Year use in template with custom pattern
        Given a template "${obj.format('yyyy')}"
        And Year object for "2007"
        Then expect the template to return "2007"

    Scenario: Test Year use in template with custom illegal pattern
        Given a template "${obj.format('yyyy mm')}"
        Then expect UnsupportedTemporalTypeException

    ### YearMonth ###
    Scenario: Test basic YearMonth use in template
        Given a template "${obj}"
        And YearMonth object for "2007-11"
        Then expect the template to return "2007-11"

    Scenario: Test YearMonth use in template with custom pattern
        Given a template "${obj.format('yyyy - MM')}"
        And YearMonth object for "2007-11"
        Then expect the template to return "2007 - 11"

    ### ZonedDateTime ###

    Scenario: Test basic ZonedDateTime use in template.
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

    Scenario: Test basic ZoneId use in template
        Given a template "${obj}"
        And a ZoneId object for 'Europe/Oslo'
        Then expect the template to return "Central European Time"

    Scenario: Test that using ZoneId inside a template will return the toString() value
        Given a template "${obj.format('short')}"
        And a ZoneId object for 'Europe/Oslo'
        Then expect the template to return "CET"


    ### ZoneOffset ###

    Scenario: Test basic ZoneOffset use in template
        Given a template "${obj}"
        And ZoneOffset object for "2"
        Then expect the template to return "+02:00"
