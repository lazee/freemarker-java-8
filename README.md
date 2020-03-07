# Java8 Date/Time Support For FreeMarker
![](https://github.com/lazee/freemarker-java-8/workflows/Build%20project/badge.svg)

This is a tiny Java library that adds basic support for the new Java 8
date/time api to FreeMarker. It is not a perfect solution as FreeMarker
doesn’t support adding custom built-ins. Hopefully FreeMarker will add
native support in the future, but there are no implementation being
worked on at the moment (<http://freemarker.org/contribute.html>).

The library has basic formatting support for all classes in the
java.time api introduced in Java 8, using the new
[java.time.format.DateTimeFormatter](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html).
We have also introduced som comparison methods.

Take a look at our feature test spec too see how it all works :
<https://github.com/lazee/freemarker-java-8/blob/master/src/test/resources/no/api/freemarker/java8/time/>

Recently this repository was moved from the Amedia organisation to my
private account on Github. The reason behind this is that I recently
left Amedia after 13 years (!) and that they let me take this project
with me. The package naming will however stay the same.

## Requirements

Java 8 or higher. Tested on Freemarker 2.3.23, but should at least work
fine for all 2.3.x versions. Please file an issue if you have problems
with other versions.

## Installation

freemarker-java-8 is deployed to the Maven Central Repository. You can
include the package in your Maven POM like this :

    <dependency>
        <groupId>no.api.freemarker</groupId>
        <artifactId>freemarker-java8</artifactId>
        <version>1.3.0</version>
    </dependency>

Make sure to replace the version with the current version found in the
pom.

## Usage

You need to configure FreeMarker with our package by adding the
`Java8ObjectWrapper`.

    this.configuration = new Configuration(); // Or get the configuration from your framework like DropWizard or Spring Boot.
    this.configuration.setObjectWrapper(new Java8ObjectWrapper(Configuration.VERSION_2_3_23));

# Usage within Spring

Within a Spring project you can add this configuration class to your
project:

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

*Thank you to Desson Ariawan for figuring this out in latest versions of
Spring Boot:
<https://www.dariawan.com/tutorials/spring/java-8-datetime-freemarker/>*

## java.time support

We had to cheat a little bit to add format methods to our date.time
classes. This is why you will see that our syntax differs from the
default FreeMarker built-ins.

All format methods uses the
[java.time.format.DateTimeFormatter](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html)
for formatting.

<table>
<caption>java.time template methods</caption>
<colgroup>
<col style="width: 25%" />
<col style="width: 25%" />
<col style="width: 25%" />
<col style="width: 25%" />
</colgroup>
<thead>
<tr class="header">
<th>java.time class</th>
<th>methods</th>
<th>comment</th>
<th>example</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p>Clock</p></td>
<td><p><code>format()</code></p></td>
<td><p>This is a simple implementation where format just prints the toString() value of the object.</p></td>
<td><p><code>${myclock.format()}</code></p></td>
</tr>
<tr class="even">
<td><p>Duration</p></td>
<td><p><code>nano(), seconds()</code></p></td>
<td><p>Gives access to the Duration values</p></td>
<td><p><code>${myduration.seconds}, ${myduration.nano}</code></p></td>
</tr>
<tr class="odd">
<td><p>Instant</p></td>
<td><p><code>format()</code></p></td>
<td><p>This is a simple implementation where format just prints the toString() value of the object.</p></td>
<td><p><code>${myinstant.format()}</code></p></td>
</tr>
<tr class="even">
<td><p>LocalDate</p></td>
<td><p><code>format(), format(pattern)</code></p></td>
<td><p>Allows you to print a LocalDate on a default pattern, by providing a custom pattern or a builtin format style.</p></td>
<td><p><code>${mylocaldate.format()}</code>, <code>${mylocaldate.format('yyyy MM dd')}</code> or <code>${mylocaldate.format('FULL_DATE')}</code></p></td>
</tr>
<tr class="odd">
<td><p>LocalDateTime</p></td>
<td><p><code>format(), format(pattern)</code></p></td>
<td><p>Allows you to print a LocalDateTime on a default pattern, by providing a custom pattern or a builtin format style.</p></td>
<td><p><code>${mylocaldatetime.format()}</code>, <code>${mylocaldatetime.format('yyyy-MM-dd HH : mm : ss')}</code> or <code>${mylocaldatetime.format('MEDIUM_DATETIME')}</code></p></td>
</tr>
<tr class="even">
<td><p>LocalTime</p></td>
<td><p><code>format(), format(pattern)</code></p></td>
<td><p>Allows you to print a LocalTime on a default pattern, by providing a custom pattern or a builtin format style.</p></td>
<td><p><code>${mylocaltime.format()}</code>, <code>${mylocaltime.format('HH : mm : ss')}</code> or <code>${mylocaltime.format('SHORT_TIME')}</code></p></td>
</tr>
<tr class="odd">
<td><p>MonthDay</p></td>
<td><p><code>format(), format(pattern)</code></p></td>
<td><p>Allows you to print a MonthDay on a default pattern or by providing a custom pattern.</p></td>
<td><p><code>${mymonthday.format()}</code> or <code>${mymonthday.format('MM dd')}</code></p></td>
</tr>
<tr class="even">
<td><p>OffsetDateTime</p></td>
<td><p><code>format(), format(pattern)</code></p></td>
<td><p>Allows you to print a OffsetDateTime on a default pattern, by providing a custom pattern or a builtin format style.</p></td>
<td><p><code>${myoffsetdatetime.format()}</code>, <code>${myoffsetdatetime.format('yyyy MM dd HH mm ss')}</code> or <code>${myoffsetdatetime.format('FULL_DATETIME')}</code></p></td>
</tr>
<tr class="odd">
<td><p>OffsetTime</p></td>
<td><p><code>format(), format(pattern)</code></p></td>
<td><p>Allows you to print a OffsetTime on a default pattern, by providing a custom pattern or a builtin format style.</p></td>
<td><p><code>${myoffsettime.format()}</code>, <code>${myoffsettime.format('HH mm ss')}</code> or <code>${myoffsettime.format('MEDIUM_TIME')}</code></p></td>
</tr>
<tr class="even">
<td><p>Period</p></td>
<td><p><code>days(), months(), years()</code></p></td>
<td><p>Gives access to the values of the Period object.</p></td>
<td><p><code>${myperiod.days}, ${myperiod.months}, ${myperiod.years}</code></p></td>
</tr>
<tr class="odd">
<td><p>Year</p></td>
<td><p><code>format(), format(pattern)</code></p></td>
<td><p>Allows you to print a Year on a default pattern or by providing a custom pattern.</p></td>
<td><p><code>${myyear.format()}</code> or <code>${myyear.format('yyyy')}</code></p></td>
</tr>
<tr class="even">
<td><p>YearMonth</p></td>
<td><p><code>format(), format(pattern)</code></p></td>
<td><p>Allows you to print a YearMonth on a default pattern or by providing a custom pattern.</p></td>
<td><p><code>${myyear.format()}</code> or <code>${myyear.format('yyyy MM')}</code></p></td>
</tr>
<tr class="odd">
<td><p>ZonedDateTime</p></td>
<td><p><code>format(), format(pattern), format(pattern, zoneId)</code></p></td>
<td><p>Allows you to print a YearMonth on a default pattern/timezone or by providing a custom pattern.</p></td>
<td><p><code>${myzoneddatetime.format()}</code> or <code>${myzoneddatetime.format('yyyy-MM-dd Z')}</code> or <code>${myzoneddatetime.format('yyyy-MM-dd Z', 'Asia/Seoul')}</code></p></td>
</tr>
<tr class="even">
<td><p>ZoneId</p></td>
<td><p><code>format(), format(textStyle), format(textstyle, locale)</code></p></td>
<td><p>Prints the ZoneId display name. You can override the textstyle with one of these values [FULL, FULL_STANDALONE, SHORT, SHORT_STANDALONE, NARROW and NARROW_STANDALONE]. You can also override the locale, but Java only seems to have locale support for a few languages.</p></td>
<td><p><code>${myzoneid.format()}</code> or <code>${myzoneid.format('short')}</code> or <code>${myzoneid.format('short', 'no-NO')}</code></p></td>
</tr>
<tr class="odd">
<td><p>ZoneOffset</p></td>
<td><p><code>format(), format(textStyle)</code></p></td>
<td><p>Prints the ZoneOffset display name. You can override the textstyle with one of these values [FULL, FULL_STANDALONE, SHORT, SHORT_STANDALONE, NARROW and NARROW_STANDALONE]. You can also override the locale, but Java only seems to have locale support for a few languages.</p></td>
<td><p>${myzoneoffset.format()}` or <code>${myzoneoffset.format('short')}</code> or `${myzoneoffset.format(<em>short</em>, <em>no-NO</em>)}</p></td>
</tr>
</tbody>
</table>

<table>
<caption>java.time comparison methods</caption>
<colgroup>
<col style="width: 25%" />
<col style="width: 25%" />
<col style="width: 25%" />
<col style="width: 25%" />
</colgroup>
<thead>
<tr class="header">
<th>java.time class</th>
<th>methods</th>
<th>comment</th>
<th>example</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><p>LocalDate</p></td>
<td><p><code>isEqual(&lt;LocalDate object&gt;), isAfter(&lt;LocalDate object&gt;), isBefore(&lt;LocalDate object&gt;)</code></p></td>
<td><p>Can compare two LocalDate objects for equality.</p></td>
<td><p><code>${localDate.isEqual(anotherlocalDate)} or ${localDate.isAfter(anotherlocalDate)} or ${localDate.isBefore(anotherlocalDate)}</code></p></td>
</tr>
<tr class="even">
<td><p>LocalDateTime</p></td>
<td><p><code>isEqual(&lt;LocalDateTime object&gt;), isAfter(&lt;LocalDateTime object&gt;), isBefore(&lt;LocalDateTime object&gt;)</code></p></td>
<td><p>Can compare two LocalDateTime objects for equality.</p></td>
<td><p><code>${localDateTime.isEqual(anotherlocalDateTime)} or ${localDateTime.isAfter(anotherlocalDateTime)} or ${localDateTime.isBefore(anotherlocalDateTime)}</code></p></td>
</tr>
<tr class="odd">
<td><p>LocalTime</p></td>
<td><p><code>isEqual(&lt;LocalTime object&gt;), isAfter(&lt;LocalTime object&gt;), isBefore(&lt;LocalTime object&gt;)</code></p></td>
<td><p>Can compare two LocalTime objects for equality.</p></td>
<td><p><code>${localTime.isEqual(anotherlocalTime)} or ${localTime.isAfter(anotherlocalTime)} or ${localTime.isBefore(anotherlocalTime)}</code></p></td>
</tr>
</tbody>
</table>
