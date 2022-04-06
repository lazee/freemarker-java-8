# java.time support for FreeMarker
![](https://github.com/lazee/freemarker-java-8/workflows/Build%20project/badge.svg)

FJ8 (freemarker-java-8) is a Java library that adds java.time api support to FreeMarker. It is easy to add to your codebase, and very easy to use.

Basically this library allows you to format and print values from `java.time` classes within FreeMarker templates.
As a bonus you also get some comparison functions.

It is not a perfect solution as FreeMarker
doesn’t support custom built-ins. Hopefully future versions of FreeMarker will add
native support, but it doesn't look promising (<http://freemarker.org/contribute.html>).

Basically this library allows you to format java.time types within your templates, using the new
[java.time.format.DateTimeFormatter](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html).

# Table of content
* [Installation](#installation)
* [Setup](#setup)
* [Upgrade from 1.3 to 2.0](#upgrade-from-13-to-20)
* [Usage](#usage)
    + [Formatting](#formatting)
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
    + [Comparison](#comparison)
      - [java.time.LocalDate](#user-content-ballot_box_with_check-javatimelocaldate-1)
      - [java.time.LocalDateTime](#user-content-ballot_box_with_check-javatimelocaldatetime-1)
      - [java.time.LocalTime](#user-content-ballot_box_with_check-javatimelocaltime-1)
* [Notice](#notice)


# Installation

You need Java 8 or higher. FJ8 is tested on Freemarker 2.3.23, and should at least work
fine for all 2.3.x versions. 

## Maven

```xml
<dependency>
    <groupId>no.api.freemarker</groupId>
    <artifactId>freemarker-java8</artifactId>
    <version>2.0.0</version>
</dependency>
```

## Gradle

	implementation 'no.api.freemarker:freemarker-java8:2.0.0'

# Setup

FJ8 extends the [DefaultObjectWrapper](https://freemarker.apache.org/docs/api/freemarker/template/DefaultObjectWrapper.html) to add support for the java.time classes. All you need to do is to replace
the default object wrapper with the FJ8 implementation in your FreeMarker Configuration object.

```java
this.configuration = new Configuration(); // Or get the configuration from your framework like DropWizard or Spring Boot.
this.configuration.setObjectWrapper(new Java8ObjectWrapper(Configuration.VERSION_2_3_23));
```

## Spring setup

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

*Thanks to Desson Ariawan for the [example](https://www.dariawan.com/tutorials/spring/java-8-datetime-freemarker/)*

You can also configure it via Spring boot properties like this:

```
spring.freemarker.settings.object_wrapper=no.api.freemarker.java8.Java8ObjectWrapper(Configuration.VERSION_2_3_31)
```

This takes advantage of Freemarker Configuration [object builder expressions](https://freemarker.apache.org/docs/api/freemarker/template/Configuration.html#fm_obe)

*Thanks to [hercsoft](https://github.com/hercsoft) for letting us know*


# Upgrade from 1.3 to 2.0

The 2.0 release addresses two major issues reported by users ([#18](https://github.com/lazee/freemarker-java-8/issues/18)/[#16](https://github.com/lazee/freemarker-java-8/issues/16)). It also introduces a new feature for manipulating time ([\#28](https://github.com/lazee/freemarker-java-8/pull/28)).  

The upgrade itself is nothing else than changing the version in your build configuration (pom.xml or something else). However if you need to stick to the old behaviour on how time zones are treated when formatting ZonedDateTime objects, then you need to add a second argument to Java8ObjectWrapper upon initialization:

```java
configuration.setObjectWrapper(
	new Java8ObjectWrapper(VERSION_2_3_23, new EnvironmentTimeStrategy()
);
```


# Usage

## Formatting java.time classes
All format methods uses the
[java.time.format.DateTimeFormatter](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html)
for formatting.

### :ballot_box_with_check: java.time.Clock

This is a simple implementation where format just prints the toString() value of the object.

#### Methods

* format()
	
#### Example

	${myclock.format()}

### java.time.Duration

Gives access to the Duration values.

#### Methods

* nano()
* seconds()
	
#### Example

	${myduration.seconds}
	${myduration.nano}
	

### :ballot_box_with_check: java.time.Instant

This is a simple implementation where format just prints the toString() value of the object.

#### Methods

* format()
* format(pattern)
	
#### Example

	${myinstant.format()}

### :ballot_box_with_check: java.time.LocalDate

Allows you to print a LocalDate on a default pattern, by providing a custom pattern or a builtin format style.

#### Methods

* format()
	
#### Example

	${mylocaldate.format()}
	${mylocaldate.format('yyyy MM dd')}
	${mylocaldate.format('FULL_DATE')}

### :ballot_box_with_check: java.time.LocalDateTime

Allows you to print a LocalDateTime on a default pattern, by providing a custom pattern or a builtin format style.

#### Methods

* format()
* format(pattern)
	
#### Example

	${mylocaldatetime.format()}
	${mylocaldatetime.format('yyyy-MM-dd HH : mm : ss')}
	${mylocaldatetime.format('MEDIUM_DATETIME')}

### :ballot_box_with_check: java.time.LocalTime

Allows you to print a LocalTime on a default pattern, by providing a custom pattern or a builtin format style.

#### Methods

* format()
* format(pattern)
	
#### Example

	${mylocaltime.format()}
	${mylocaltime.format('HH : mm : ss')}
	${mylocaltime.format('SHORT_TIME')}

### :ballot_box_with_check: java.time.MonthDay

Allows you to print a MonthDay on a default pattern or by providing a custom pattern.

#### Methods

* format()
* format(pattern)
	
#### Example

	${mymonthday.format()}
	${mymonthday.format('MM dd')}

### :ballot_box_with_check: java.time.OffsetDateTime

Allows you to print a OffsetDateTime on a default pattern, by providing a custom pattern or a builtin format style.

#### Methods

* format()
* format(pattern)
	
#### Example

	${myoffsetdatetime.format()}
	${myoffsetdatetime.format('yyyy MM dd HH mm ss')}
	${myoffsetdatetime.format('FULL_DATETIME')}


### :ballot_box_with_check: java.time.OffsetTime

Allows you to print a OffsetTime on a default pattern, by providing a custom pattern or a builtin format style.

#### Methods

* format()
* format(pattern)
	
#### Example

	${myoffsettime.format()}
	${myoffsettime.format('HH mm ss')}
	${myoffsettime.format('MEDIUM_TIME')}

### :ballot_box_with_check: java.time.Period

Provides access to the values of the a Period object within your template.

#### Methods

* days()
* months()
* years()
	
#### Example

	${myperiod.days}
	${myperiod.months}
	${myperiod.years}

### :ballot_box_with_check: java.time.Year

Allows you to print a Year on a default pattern or by providing a custom pattern.

#### Methods

* format()
* format(pattern)
	
#### Example

	${myyear.format()}
	${myyear.format('yyyy')}

### :ballot_box_with_check: java.time.YearMonth

Allows you to print a YearMonth on a default pattern or by providing a custom pattern.

#### Methods

* format()
* format(pattern)
	
#### Example

	${myyear.format()}
	${myyear.format('yyyy MM')}

### :ballot_box_with_check: java.time.ZonedDateTime

Allows you to print a YearMonth on a default pattern/timezone or by providing a custom pattern.

#### Methods

* format()
* format(pattern)
* format(pattern, zone)

#### Example

	${myzoneddatetime.format()}
	${myzoneddatetime.format('yyyy-MM-dd Z')}
	${myzoneddatetime.format('yyyy-MM-dd Z', 'Asia/Seoul')}

#### Notice

When a zone is _not_ set, the formatter will use the zone found in the ZonedDateTime object itself. This behaviour can be changed if you want to. Scenarious where that might come in handy could be when you always wants to convert the timezone into your local timezone.

`Java8ObjectMapper`now takes a second argument where you can choose one of four strategies for 
the time zone used when formatting a ZonedDateTime:

* **EnviromentZonedDateTimeStrategy** - Will convert the time zone into the one currently set within Freemarker.
* **KeepingZonedDateTimeStrategy** - Will use the zone from the ZonedDateTime object itself (DEFAULT)
* **SystemZonedDateTimeStrategy** - Will convert the time zone into ZoneId.systemDefault().
* **StaticSystemZoneDateTimeStrategy** - Will use the time zone set when creating this strategy.

Example:

```java
new Java8ObjectWrapper(VERSION_2_3_23, new EnvironmentZonedDateTimeStrategy());
// or
new Java8ObjectWrapper(VERSION_2_3_23, new StaticZonedDateTimeStrategy(ZoneId.of("Europe/Oslo")));
```
	
### :ballot_box_with_check: java.time.ZonedId

Prints the ZoneId display name. You can override the textstyle with one of these values [FULL, FULL_STANDALONE, SHORT, SHORT_STANDALONE, NARROW and NARROW_STANDALONE]. You can also override the locale, but Java only seems to have locale support for a few languages.

#### Methods

* format()
* format(textStyle)
* format(textstyle, locale)	

### Example

	${myzoneid.format()}
	${myzoneid.format('short')}
	${myzoneid.format('short', 'no-NO')}

### :ballot_box_with_check: java.time.ZonedOffset

Prints the ZoneOffset display name. You can override the textstyle with one of these values [FULL, FULL_STANDALONE, SHORT, SHORT_STANDALONE, NARROW and NARROW_STANDALONE]. You can also override the locale, but Java only seems to have locale support for a few languages.</p></td>


#### Methods

* format()
* format(textStyle)	

#### Example

	${myzoneoffset.format()}
	${myzoneoffset.format('short')}


## Comparison


### :ballot_box_with_check: java.time.LocalDate

Can compare two LocalDate objects for equality.

#### Methods

* isEqual(localDate)
* isAfter(localDate)
* isBefore(localDate)

#### Example

	${localDate.isEqual(anotherlocalDate)}
	${localDate.isAfter(anotherlocalDate)}
	${localDate.isBefore(anotherlocalDate)}


### :ballot_box_with_check: java.time.LocalDateTime

Can compare two LocalDateTime objects for equality.

#### Methods

* isEqual(localDateTime)
* isAfter(localDateTime)
* isBefore(localDateTime)

#### Example

	${localDateTime.isEqual(anotherlocalDateTime)}
	${localDateTime.isAfter(anotherlocalDateTime)}
	${localDateTime.isBefore(anotherlocalDateTime)}

### :ballot_box_with_check: java.time.LocalTime

Can compare two LocalTime objects for equality.

#### Methods

* isEqual(localDateTime)
* isAfter(localDateTime)
* isBefore(localDateTime)

#### Example

	${localTime.isEqual(anotherlocalTime)}
	${localTime.isAfter(anotherlocalTime)}
	${localTime.isBefore(anotherlocalTime)}

## Manipulating time

### :ballot_box_with_check: java.time.temporal.Temporal

Can create a new Temporal object with specified time difference from the original object, supporting 

	java.time.Instant, 
	java.time.LocalDate, 
	java.time.LocalDateTime, 
	java.time.LocalTime, 
	java.time.OffsetDateTime, 
	java.time.OffsetTime, 
	java.time.Year, 
	java.time.YearMonth, 
	java.time.ZonedDateTime
 
#### Methods

* plusSeconds(<Long>)
* plusMinutes(<Long>)
* plusDays(<Long>)
* plusWeeks(<Long>)
* plusMonths(<Long>)
* plusYears(<Long>)
	
#### Example

	${localDateTime.plusMonths(1).plus.Hours(-2).plusMinutes(5).plusSeconds(30).format()}

## Notice


Recently this repository was moved from the Amedia organisation to my
private account on Github. The reason behind this is that I recently
left Amedia after 13 years (!) and that they let me take this project
with me. The package naming will however stay the same.

