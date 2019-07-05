# Change Log

## [freemarker-java8-2.0.0](https://github.com/Munich-Airport/freemarker-java-8/tree/master) (TBD)
[Full Changelog](https://github.com/Munich-Airport/freemarker-java-8/compare/freemarker-java8-1.2.0...freemarker-java8-2.0.0)

- Moved from amedia to Munich Airport. New maven dependency:

      <groupId>com.munich-airport.freemarker</groupId>
      <artifactId>freemarker-java8</artifactId>
      <version>2.0.0</version>

- Added fallback to default bean model. All methods provided by the classes can now be used. For example: `${duration.plusMinutes(1).toMinutes()}`
  In addition all getters may be used as properties. For example `${duration.getSeconds()}` can be written as `${duration.seconds}`
- Added support for configuring the `Java8ObjectWrapper` using an instance of `com.munichairport.freemarker.java8.config.Java8Configuration`. 
- Added support for different timezone strategies when formatting a `ZonedDateTime` using `format()` or `format(pattern)`. Currently supported are:
  1. `EnvironmentTimezoneStrategy`: 
      Uses the `Environment.getCurrentEnvironment().getTimezone().toZoneId()` as ZoneId. The default behavior prior to 2.0.0.
  1. `KeepingTimezoneStragegy`: 
      Does not change the ZoneId of the input. Default behavior when creating a new `Java8Configuration`.
  1. `StaticTimezoneStrategy`:
      Changes all input ZoneIds to the statically configured.
  1. `SystemTimezoneStrategy`:
      Uses the system wide ZoneId.
- Added support to use builtin static `DateTimeFormatter` instances by defining the static field name. 
  For example: `${zonedDateTime.format('ISO_ZONED_DATE_TIME')}` formats the instance using the `DateTimeFormatter.ISO_ZONED_DATE_TIME` formatter.

## [freemarker-java8-1.2.0](https://github.com/amedia/freemarker-java-8/tree/freemarker-java8-1.2.0) (2017-08-08)
[Full Changelog](https://github.com/amedia/freemarker-java-8/compare/freemarker-java8-1.1.5...freemarker-java8-1.2.0)

- Fixed formatter bug reported and fixed by @tifoha (https://github.com/amedia/freemarker-java-8/commit/92d1e7d6f0310d946b516cb008479e5de427dca6)
- Added support for isEqual, isAfter and isBefore as suggested by @kingmaoam. (https://github.com/amedia/freemarker-java-8/pull/10/files)

Note: As we had some issues with the 1.1.6 deployment, the changelig for this release also contains some changes from that release. 


## [freemarker-java8-1.1.6](https://github.com/amedia/freemarker-java-8/tree/freemarker-java8-1.1.6) (2017-07-24)
[Full Changelog](https://github.com/amedia/freemarker-java-8/compare/freemarker-java8-1.1.5...freemarker-java8-1.1.6)

- Added isEqual, isAfter and isBefore methods to adapters for LocalDate, LocalDateTime and Localtime (Code contributed by @kingmaoam)

## [freemarker-java8-1.1.5](https://github.com/amedia/freemarker-java-8/tree/freemarker-java8-1.1.5) (2016-12-19)
[Full Changelog](https://github.com/amedia/freemarker-java-8/compare/freemarker-java8-1.1.4...freemarker-java8-1.1.5)

- OBS! Fixed bug that made the DateTimeFormatter use the default VM Locale instead of the Locale from the Freemarker configuration. [\#PR8](https://github.com/amedia/freemarker-java-8/pull/8).

## [freemarker-java8-1.1.4](https://github.com/amedia/freemarker-java-8/tree/freemarker-java8-1.1.4) (2016-12-15)
[Full Changelog](https://github.com/amedia/freemarker-java-8/compare/freemarker-java8-1.1.2...freemarker-java8-1.1.4)

- ZoneOffsetAdapter is not used [\#6](https://github.com/amedia/freemarker-java-8/issues/6)


## [freemarker-java8-1.1.2](https://github.com/amedia/freemarker-java-8/tree/freemarker-java8-1.1.2) (2016-10-26)

[Full Changelog](https://github.com/amedia/freemarker-java-8/compare/freemarker-java8-1.1.1...freemarker-java8-1.1.2)

**Closed issues:**

- wrong maven dependency  [\#5](https://github.com/amedia/freemarker-java-8/issues/5)
- Install error [\#3](https://github.com/amedia/freemarker-java-8/issues/3)
- Deploy to mavenCentral [\#1](https://github.com/amedia/freemarker-java-8/issues/1)

**Merged pull requests:**

- \#6 Fixed [\#7](https://github.com/amedia/freemarker-java-8/pull/7) ([lazee](https://github.com/lazee))
- Update installation documentation [\#4](https://github.com/amedia/freemarker-java-8/pull/4) ([mthmulders](https://github.com/mthmulders))



