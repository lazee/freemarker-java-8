Feature: Test the date time functionality

    ### Clock ###
    Scenario: Test basic Clock use in template
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj}"
        And Clock object for "2007-12-03T10:15:30.00Z"
        Then expect the template to return "FixedClock[2007-12-03T10:15:30Z,Europe/Oslo]"

    ### Duration ###
    Scenario: Test basic Duration use in template
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj}"
        And Duration object for "PT15M"
        Then expect the template to return "PT15M"

    Scenario: Test nano Duration use in template
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.nano}"
        And Duration object for "PT15M"
        Then expect the template to return "0"

    Scenario: Test seconds Duration use in template
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.seconds}"
        And Duration object for "PT15M"
        Then expect the template to return "900"

    ### Instant ###
    Scenario: Test basic Instant use in template
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj}"
        And Instant object for "2007-12-03T10:15:30.00Z"
        Then expect the template to return "2007-12-03T10:15:30Z"

    ### LocalDate ###
    Scenario: Test basic LocalDate use in template
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj}"
        And LocalDate object for "2007-12-03"
        Then expect the template to return "2007-12-03"

    Scenario: Test LocalDate use in template with custom pattern
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.format('EEEE d MMMM yyyy')}"
        And LocalDate object for "2007-12-03"
        Then expect the template to return "mandag 3 desember 2007"

    Scenario: Test LocalDate use in template with custom pattern and german locale
        Given an freemarker environment with locale set to "de-DE"
        And timezone set to "Europe/Oslo"
        And a template "${obj.format('EEEE d MMMM yyyy')}"
        And LocalDate object for "2007-12-03"
        Then expect the template to return "Montag 3 Dezember 2007"

    Scenario: Test LocalDate use in template with custom pattern and arabic locale
        Given an freemarker environment with locale set to "ar-DZ"
        And timezone set to "Europe/Oslo"
        And a template "${obj.format('EEEE d MMMM yyyy')}"
        And LocalDate object for "2007-12-03"
        Then expect the template to return "الاثنين 3 ديسمبر 2007"

    ### LocalDateTime ###
    Scenario: Test basic LocalDateTime use in template
        Given an freemarker environment with locale set to "ar-DZ"
        And timezone set to "Europe/Oslo"
        And a template "${obj}"
        And LocalDateTime object for "2007-12-03T10:15:30"
        Then expect the template to return "2007-12-03T10:15:30"

    Scenario: Test basic LocalDateTime use in template with custom pattern
        Given an freemarker environment with locale set to "no-No"
        And timezone set to "Europe/Oslo"
        And a template "${obj.format('EEEE d MMMM yyyy HH:mm:ss')}"
        And LocalDateTime object for "2007-12-03T10:15:30"
        Then expect the template to return "mandag 3 desember 2007 10:15:30"

    Scenario: Test basic LocalDateTime use in template with custom pattern and romanian locale
        Given an freemarker environment with locale set to "ro-RO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.format('EEEE d MMMM yyyy HH:mm:ss')}"
        And LocalDateTime object for "2007-12-03T10:15:30"
        Then expect the template to return "luni 3 decembrie 2007 10:15:30"

    ### LocalTime ###
    Scenario: Test basic LocalTime use in template
        Given an freemarker environment with locale set to "no-No"
        And timezone set to "Europe/Oslo"
        And a template "${obj}"
        And LocalTime object for "23:44"
        Then expect the template to return "23:44"

    ### MonthDay ###
    Scenario: Test basic MonthDay use in template
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj}"
        And MonthDay object for "--10-14"
        Then expect the template to return "--10-14"

    Scenario: Test basic MonthDay use in template with custom pattern
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.format('MMMM')}"
        And MonthDay object for "--10-14"
        Then expect the template to return "oktober"

    Scenario: Test basic MonthDay use in template with custom pattern and french locale
        Given an freemarker environment with locale set to "fr-FR"
        And timezone set to "Europe/Oslo"
        And a template "${obj.format('MMMM')}"
        And MonthDay object for "--10-14"
        Then expect the template to return "octobre"

    ### OffsetDateTime ###
    Scenario: Test basic OffsetDateTime use in template
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj}"
        And OffsetDateTime object for "2007-12-03T10:15:30+01:00"
        Then expect the template to return "2007-12-03T10:15:30+01:00"

    Scenario: Test basic OffsetDateTime use in template with custom pattern
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.format('YYYY MMMM EEEE d HH:mm:ss Z')}"
        And OffsetDateTime object for "2007-12-03T10:15:30+01:00"
        Then expect the template to return "2007 desember mandag 3 10:15:30 +0100"

    Scenario: Test basic OffsetDateTime use in template with custom pattern and finnish locale
        Given an freemarker environment with locale set to "fi-FI"
        And timezone set to "Europe/Oslo"
        And a template "${obj.format('YYYY MMMM EEEE d HH:mm:ss Z')}"
        And OffsetDateTime object for "2007-12-03T10:15:30+01:00"
        Then expect the template to return "2007 joulukuuta maanantai 3 10:15:30 +0100"

    ### OffsetTime ###
    Scenario: Test basic OffsetTime use in template
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj}"
        And OffsetTime object for "23:44+01:00"
        Then expect the template to return "23:44+01:00"

    ### Period ###
    Scenario: Test basic Period use in template
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj}"
        And Period object for "P1Y2M3D"
        Then expect the template to return "P1Y2M3D"

    Scenario: Test days Period use in template
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.days}"
        And Period object for "P1Y2M3D"
        Then expect the template to return "3"

    Scenario: Test moths Period use in template
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.months}"
        And Period object for "P1Y2M3D"
        Then expect the template to return "2"

    Scenario: Test years Period use in template
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.years}"
        And Period object for "P1Y2M3D"
        Then expect the template to return "1"

    ### Year ###
    Scenario: Test basic Year use in template
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj}"
        And Year object for "2007"
        Then expect the template to return "2007"

    Scenario: Test Year use in template with custom pattern
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.format('yyyy')}"
        And Year object for "2007"
        Then expect the template to return "2007"

    Scenario: Test Year use in template with custom illegal pattern
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.format('yyyy mm')}"
        Then expect UnsupportedTemporalTypeException

    ### YearMonth ###
    Scenario: Test basic YearMonth use in template
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj}"
        And YearMonth object for "2007-11"
        Then expect the template to return "2007-11"

    Scenario: Test YearMonth use in template with custom pattern
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.format('yyyy - MM')}"
        And YearMonth object for "2007-11"
        Then expect the template to return "2007 - 11"

    ### ZonedDateTime ###
    Scenario: Test basic ZonedDateTime use in template.
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj}"
        And ZonedDateTime object for "2007-12-03T10:15:30+01:00[Europe/Paris]"
        Then expect the template to return "2007-12-03T10:15:30+01:00[Europe/Paris]"

    Scenario: Test that using a preset ZonedDateTime inside a template will return the default time zone
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.format()}"
        And ZonedDateTime object for "2007-12-03T16:15:30+07:00[Asia/Bangkok]"
        Then expect the template to return "2007-12-03T10:15:30+01:00[Europe/Oslo]"

    Scenario: Test that using a preset ZonedDateTime inside a template will return the default time zone when a pattern and zone id is given
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj.format('yyyy-MM-dd Z', 'Asia/Seoul')}"
        And ZonedDateTime object for "2007-12-03T16:15:30+07:00[Asia/Bangkok]"
        Then expect the template to return "2007-12-03 +0900"

    Scenario: Test that using a preset ZonedDateTime inside a template will return the default time zone when a pattern and zone id is given and german locale
        Given an freemarker environment with locale set to "de-DE"
        And timezone set to "Europe/Oslo"
        And a template "${obj.format('yyyy MMMM EEEE Z', 'Asia/Seoul')}"
        And ZonedDateTime object for "2007-12-03T16:15:30+07:00[Asia/Bangkok]"
        Then expect the template to return "2007 Dezember Montag +0900"

    ### ZoneId ###
    Scenario: Test basic ZoneId use in template
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj}"
        And a ZoneId object for 'Europe/Oslo'
        Then expect the template to return "Central European Time"

    Scenario: Test that using ZoneId inside a template will return the toString() value
        Given an freemarker environment with locale set to "fi-FI"
        And timezone set to "Europe/Oslo"
        And a template "${obj.format('short')}"
        And a ZoneId object for 'Europe/Oslo'
        Then expect the template to return "CET"


    ### ZoneOffset ###
    Scenario: Test basic ZoneOffset use in template
        Given an freemarker environment with locale set to "no-NO"
        And timezone set to "Europe/Oslo"
        And a template "${obj}"
        And ZoneOffset object for "2"
        Then expect the template to return "+02:00"
