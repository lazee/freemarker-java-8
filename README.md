# java.time support for FreeMarker

![build status](https://github.com/lazee/freemarker-java-8/workflows/Build%20project/badge.svg)

FJ8 (freemarker-java-8) is a Java library that adds support for the `java.time` api to FreeMarker. It is easy to add to your codebase, and very easy to use.

Basically this library allows you to format and print values from `java.time` classes within FreeMarker templates.
As a bonus you also get some nice comparison and conversion methods.

It is not a perfect implementation as FreeMarker
doesn’t support custom built-ins. Hopefully future versions of FreeMarker will add
native support, but it doesn't look promising (<http://freemarker.org/contribute.html>).

## Table of content

- [Installation](#installation)
- [Setup](#setup)
- [Upgrade guides](#upgrade-guides)
- [Usage](#usage)
  - [Formatting](#formatting)
    - [About pattern](#about-pattern)
    - [About zone](#about-zone)
    - [java.time.Clock](#user-content-ballot_box_with_check-javatimeclock)
    - [java.time.Duration](#user-content-ballot_box_with_check-javatimeduration)
    - [java.time.Instant](#user-content-ballot_box_with_check-javatimeinstant)
    - [java.time.LocalDate](#user-content-ballot_box_with_check-javatimelocaldate)
    - [java.time.LocalDateTime](#user-content-ballot_box_with_check-javatimelocaldatetime)
    - [java.time.LocalTime](#user-content-ballot_box_with_check-javatimelocaltime)
    - [java.time.MonthDay](#user-content-ballot_box_with_check-javatimemonthday)
    - [java.time.OffsetDateTime](#user-content-ballot_box_with_check-javatimeoffsetdatetime)
    - [java.time.OffsetTime](#user-content-ballot_box_with_check-javatimeoffsettime)
    - [java.time.Period](#user-content-ballot_box_with_check-javatimeperiod)
    - [java.time.Year](#user-content-ballot_box_with_check-javatimeyear)
    - [java.time.YearMonth](#user-content-ballot_box_with_check-javatimeyearmonth)
    - [java.time.ZonedDateTime](#user-content-ballot_box_with_check-javatimezoneddatetime)
    - [java.time.ZonedId](#user-content-ballot_box_with_check-javatimezonedid)
    - [java.time.ZonedOffset](#user-content-ballot_box_with_check-javatimezonedoffset)
  - [Comparison](#comparison)
    - [java.time.LocalDate](#user-content-ballot_box_with_check-javatimelocaldate-1)
    - [java.time.LocalDateTime](#user-content-ballot_box_with_check-javatimelocaldatetime-1)
    - [java.time.LocalTime](#user-content-ballot_box_with_check-javatimelocaltime-1)

## Installation

You need Java 8 or higher. Tested on Freemarker 2.3.31, and should at least work
fine for all 2.3.x versions.

### Maven

```xml
<dependency>
    <groupId>no.api.freemarker</groupId>
    <artifactId>freemarker-java8</artifactId>
    <version>2.1.0</version>
</dependency>
```

### Gradle

```gradle
implementation 'no.api.freemarker:freemarker-java8:2.1.0'
```

## Setup

FJ8 extends the [DefaultObjectWrapper](https://freemarker.apache.org/docs/api/freemarker/template/DefaultObjectWrapper.html) 
to add support for the java.time classes. All you need to do is to replace
the default object wrapper with the FJ8 implementation in your FreeMarker Configuration object.

```java
this.configuration = new Configuration(); // Or get the configuration from your framework like DropWizard or Spring Boot.
this.configuration.setObjectWrapper(new Java8ObjectWrapper(Configuration.VERSION_2_3_31));
```

### Spring setup

This is how you can add FJ8 to your FreeMarker configuration in Spring / Spring Boot.

```java
package com.example.demo;

import no.api.freemarker.java8.Java8ObjectWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Configuration
public class FreemarkerConfig implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        if (bean instanceof FreeMarkerConfigurer) {
            FreeMarkerConfigurer configurer = (FreeMarkerConfigurer) bean;
            configurer.getConfiguration().setObjectWrapper(new Java8ObjectWrapper(freemarker.template.Configuration.getVersion()));
        }
        return bean;
    }
}
```

You can also configure it via Spring boot properties like this:

```java
spring.freemarker.settings.object_wrapper=no.api.freemarker.java8.Java8ObjectWrapper(Configuration.VERSION_2_3_31)
```

This takes advantage of Freemarker Configuration [object builder expressions](https://freemarker.apache.org/docs/api/freemarker/template/Configuration.html#fm_obe)

## Upgrade guides

Upgrade information has moved to [UPGRADE.md](UPGRADE.md). Also look at [CHANGELOG.md](CHANGELOG.md) for changes in new versions.

## Usage

### Formatting java.time classes

All format methods uses the
[java.time.format.DateTimeFormatter](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html)
for formatting.

#### About pattern

Instead of customizing your own pattern in the format methods, you can use one of the predefined styles from:

- [`java.text.format.DateFormat`](https://docs.oracle.com/javase/tutorial/i18n/format/dateFormat.html) (DEFAULT, SHORT, MEDIUM, LONG, FULL)
- [`java.time.format.DateTimeFormatter`](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html) (Eg: ISO_OFFSET_DATE_TIME, follow link for more)
- Custom styles shipped with this library
  - LONG_DATE
  - LONG_DATETIME
  - LONG_TIME
  - MEDIUM_DATE
  - MEDIUM_DATETIME
  - MEDIUM_TIME
  - SHORT_DATE
  - SHORT_DATETIME
  - SHORT_TIME

##### Examples

```freemarker
${mydate.format('LONG_DATE')}
${mydate.format('ISO_OFFSET_DATE_TIME')}
${mydate.format('yyyy-MM-dd Z')}
```

When no pattern are given, each java.time class has a default pattern. This default can be overridden in the static
`DefaultFormatters` class, where each class has its own setter method:

```java
no.api.freemarker.java8.time.DefaultFormatters.setClockFormatter(DateTimeFormatter.ofPattern('yyyy MM dd HH:mm:ss'))
```

#### About zone

When a method takes `zone` as argument, it must be a valid [Java `ZoneId`](https://docs.oracle.com/javase/8/docs/api/java/time/ZoneId.html), like `Europe/Oslo` or `-07:00`.

The `zone` argument is only supported for the `java.time` classes where it makes sense.
When a zone is *not* explicitly given as argument, the formatter will by default use the zone found in the object itself, if it exists. If not, it will pick one based on the active zone strategy.

Th default behaviour can be changed if you like. Sometimes you might want all dates and times
to be converted into your local timezone.

`Java8ObjectMapper`now a second argument where you can choose one of four strategies for the time zone used when formatting a ZonedDateTime:

- **KeepingZoneStrategy** - (DEFAULT) Will use the zone from the objects themself. For objects without zone information, ZoneId.systemDefault() is used.
- **EnviromentZoneStrategy** - Will use the same time zone as Freemarker itself.
- **SystemZoneStrategy** - Will convert the time zone into ZoneId.systemDefault().
- **StaticZoneStrategy** - Allows you to explicitly set a ZoneId.

##### Examples

```java
this.objectWrapper = new Java8ObjectWrapper(VERSION_2_3_31, new KeepingZoneStrategy());
this.objectWrapper = new Java8ObjectWrapper(VERSION_2_3_31, new StaticZoneStrategy(ZoneId.of("Europe/Oslo")));
```

#### :ballot_box_with_check: java.time.Clock

Methods for formatting `java.time.Clock`.

##### Methods

- format()
- format(pattern)
- format(pattern, zone)

When no *pattern* is specified, `ISO_LOCAL_DATE_TIME` is used.

##### Examples

```freemarker
${myclock.format()}
${myclock.format('yyyy-MM-dd')}
${myclock.format('yyyy-MM-dd Z', 'Asia/Seoul')}
```

#### java.time.Duration

Provided access to a `java.time.Duration` object values.

##### Methods

- nano()
- seconds()

##### Examples

```freemarker
${myduration.seconds}
${myduration.nano}
```

#### :ballot_box_with_check: java.time.Instant

Methods for formatting `java.time.Instant`.

##### Methods

- format()
- format(pattern)
- format(pattern, zone)

When no *pattern* is specified, `ISO_LOCAL_DATE_TIME` is used.

##### Examples

```freemarker
${myinstant.format()}
${myzoneddatetime.format('yyyy-MM-dd')}
${myzoneddatetime.format('yyyy-MM-dd Z', 'Asia/Seoul')}
```

#### :ballot_box_with_check: java.time.LocalDate

Methods for formatting `java.time.LocalDate`.

##### Methods

- format()
- format(pattern)

##### Examples

```freemarker
${mylocaldate.format()}
${mylocaldate.format('yyyy MM dd')}
${mylocaldate.format('FULL_DATE')}
```

#### :ballot_box_with_check: java.time.LocalDateTime

Methods for formatting `java.time.LocalDateTime`. We also have methods for converting
a `LocalDateTime` into a `ZoneDateTime`.

##### Methods

- format()
- format(pattern)
- format(pattern)
- toZonedDateTime()
- toZonedDateTime(zone)

##### Examples

```freemarker
${mylocaldatetime.format()}
${mylocaldatetime.format('yyyy-MM-dd HH : mm : ss')}
${mylocaldatetime.format('MEDIUM_DATETIME')}
${mylocaldatetime.toZonedDateTime()}
${mylocaldatetime.toZonedDateTime('Asia/Seoul')}
${mylocaldatetime.toZonedDateTime('Asia/Seoul')}.format('yyyy-MM-dd HH : mm : ss Z')}
```

When no *pattern* is specified, `ISO_LOCAL_DATE_TIME` is used.

When no `zone` are given to `toZoneDateTime`, the ZoneStrategy are used to pick one for you.

#### :ballot_box_with_check: java.time.LocalTime

Methods for formatting `java.time.LocalTime`.

##### Methods

- format()
- format(pattern)

##### Examples

```freemarker
${mylocaltime.format()}
${mylocaltime.format('HH : mm : ss')}
${mylocaltime.format('SHORT_TIME')}
```

When no *pattern* is specified, `ISO_LOCAL_TIME` is used.

#### :ballot_box_with_check: java.time.MonthDay

Methods for formatting `java.time.MonthDay`.

##### Methods

- format()
- format(pattern)

##### Examples

```freemarker
${mymonthday.format()}
${mymonthday.format('MM dd')}
```

When no *pattern* is specified, `MM:dd` is used.

#### :ballot_box_with_check: java.time.OffsetDateTime

Methods for formatting `java.time.OffsetDateTime`.

##### Methods

- format()
- format(pattern)
- format(pattern, zone)

When no *pattern* is specified, `ISO_OFFSET_DATE_TIME` is used.

*Uses the normalized ZoneId from the Offset `myOffsetDateTime.getOffset().normalized()`*

##### Examples

```freemarker
${myoffsetdatetime.format()}
${myoffsetdatetime.format('yyyy MM dd HH mm ss')}
${myoffsetdatetime.format('FULL_DATETIME')}
${myoffsetdatetime.format('yyyy MM dd HH mm ss Z', 'Asia/Seoul')}
```

#### :ballot_box_with_check: java.time.OffsetTime

Methods for formatting `java.time.OffsetTime`.

##### Methods

- format()
- format(pattern)

##### Examples

```freemarker
${myoffsettime.format()}
${myoffsettime.format('HH mm ss')}
${myoffsettime.format('MEDIUM_TIME')}
```

When no *pattern* is specified, `ISO_OFFSET_TIME` is used.

#### :ballot_box_with_check: java.time.Period

Provides access to the values of the a Period object within your template.

##### Methods

- days()
- months()
- years()

##### Examples

```freemarker
${myperiod.days}
${myperiod.months}
${myperiod.years}
```

#### :ballot_box_with_check: java.time.Year

Methods for formatting `java.time.Year`.

##### Methods

- format()
- format(pattern)

##### Examples

```freemarker
${myyear.format()}
${myyear.format('yyyy')}
```

When no *pattern* is specified, `yyyy` is used.

#### :ballot_box_with_check: java.time.YearMonth

Methods for formatting `java.time.YearMonth`.

##### Methods

- format()
- format(pattern)

##### Examples

```freemarker
${myyear.format()}
${myyear.format('yyyy MM')}
```

When no *pattern* is specified, `yyyy-MM` is used.

#### :ballot_box_with_check: java.time.ZonedDateTime

Methods for formatting `java.time.ZonedDateTime`.

##### Methods

- format()
- format(pattern)
- format(pattern, zone)

##### Examples

```freemarker
${myzoneddatetime.format()}
${myzoneddatetime.format('yyyy-MM-dd HH mm s Z')}
${myzoneddatetime.format('yyyy-MM-dd HH mm s Z', 'Asia/Seoul')}
```

Example:

```java
new Java8ObjectWrapper(VERSION_2_3_31, new EnvironmentZoneStrategy());
// or
new Java8ObjectWrapper(VERSION_2_3_31, new StaticZoneStrategy(ZoneId.of("Europe/Oslo")));
```

#### :ballot_box_with_check: java.time.ZonedId

Methods for formatting ZoneId display name.

You can override the textstyle with one of these values:

- FULL
- FULL_STANDALONE
- SHORT
- SHORT_STANDALONE
- NARROW
- NARROW_STANDALONE

You can also override the locale, but Java only seems to have locale support for a few languages.

##### Methods

- format()
- format(textStyle)
- format(textstyle, locale)

#### Example

```freemarker
${myzoneid.format()}
${myzoneid.format('short')}
${myzoneid.format('short', 'no-NO')}
```

#### :ballot_box_with_check: java.time.ZonedOffset

Methods for formatting the ZoneOffset display name. You can override the textstyle with one of these values:

- FULL
- FULL_STANDALONE
- SHORT
- SHORT_STANDALONE
- NARROW
- NARROW_STANDALONE
 
 You can also override the locale, but Java only seems to have locale support for a few languages.

##### Methods

- format()
- format(textStyle)

##### Examples

```freemarker
${myzoneoffset.format()}
${myzoneoffset.format('short')}
```

### Comparison

#### :ballot_box_with_check: java.time.LocalDate

Can compare two LocalDate objects for equality.

##### Methods

- isEqual(localDate)
- isAfter(localDate)
- isBefore(localDate)

##### Examples

```freemarker
${localDate.isEqual(anotherlocalDate)}
${localDate.isAfter(anotherlocalDate)}
${localDate.isBefore(anotherlocalDate)}
```

#### :ballot_box_with_check: java.time.LocalDateTime

Can compare two LocalDateTime objects for equality.

##### Methods

- isEqual(localDateTime)
- isAfter(localDateTime)
- isBefore(localDateTime)

##### Examples

```freemarker
${localDateTime.isEqual(anotherlocalDateTime)}
${localDateTime.isAfter(anotherlocalDateTime)}
${localDateTime.isBefore(anotherlocalDateTime)}
```

#### :ballot_box_with_check: java.time.LocalTime

Can compare two LocalTime objects for equality.

##### Methods

- isEqual(localDateTime)
- isAfter(localDateTime)
- isBefore(localDateTime)

##### Examples

```freemarker
${localTime.isEqual(anotherlocalTime)}
${localTime.isAfter(anotherlocalTime)}
${localTime.isBefore(anotherlocalTime)}
```

### Manipulating time

#### :ballot_box_with_check: java.time.temporal.Temporal

Can create a new Temporal object with specified time difference from the original object, supporting

```freemarker
java.time.Instant, 
java.time.LocalDate, 
java.time.LocalDateTime, 
java.time.LocalTime, 
java.time.OffsetDateTime, 
java.time.OffsetTime, 
java.time.Year, 
java.time.YearMonth, 
java.time.ZonedDateTime
```

##### Methods

- plusSeconds(long)
- plusMinutes(long)
- plusDays(long)
- plusWeeks(long)
- plusMonths(long)
- plusYears(lone)

##### Examples

```freemarker
${localDateTime.plusMonths(1).plus.Hours(-2).plusMinutes(5).plusSeconds(30).format()}
```
