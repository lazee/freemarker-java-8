# Change Log
## [freemarker-java8-3.0.0](https://github.com/lazee/freemarker-java-8/tree/freemarker-java8-3.0.0) (2024-08-24)

* Changed package group id to `no.gemino.freemarker` as the old `no.api.freemarker` no longer can be used without domain verification at Sonatype.
* Upgraded FreeMarker to 2.3.33
* Removed deprecated classes and methods

## [freemarker-java8-2.1.0](https://github.com/lazee/freemarker-java-8/tree/freemarker-java8-2.1.0) (2022-07-07)

* Upgraded dependencies for FreeMarker and Cucumber tests
* Deprecated old ZonedDateTimeStrategy interface and implementations. Introduced ZoneStrategy interface and implementations instead. New interface supports more than just ZonedDateTime.
* Introduced real format support for `Instant`, `Clock` and `OffsetDateTime` [\#33](https://github.com/lazee/freemarker-java-8/pull/33) [\#32](https://github.com/lazee/freemarker-java-8/pull/32)
* Introduced `asZoneDateTime` method for `LocalDateTime`
* Introduced support for overriding default formatters for all java.time objects [\#30](https://github.com/lazee/freemarker-java-8/pull/30)


## [freemarker-java8-2.0.0](https://github.com/lazee/freemarker-java-8/tree/freemarker-java8-2.0.0) (2020-03-10)

- Allow fall-through to default methods in the java.time classes (Thank you @mihxil)
- Proper handling of ZoneId for ZonedDateTime formatting (Breaking change in default behaviour)
- Github Actions build
- New time manipulation support by @jeffyjin [\#28](https://github.com/lazee/freemarker-java-8/pull/28)

## [freemarker-java8-1.2.0](https://github.com/lazee/freemarker-java-8/tree/freemarker-java8-1.2.0) (2017-08-08)

- Fixed formatter bug reported and fixed by @tifoha [](https://github.com/lazee/freemarker-java-8/commit/92d1e7d6f0310d946b516cb008479e5de427dca6)
- Added support for isEqual, isAfter and isBefore as suggested by @kingmaoam. [](https://github.com/lazee/freemarker-java-8/pull/10/files)

Note: As we had some issues with the 1.1.6 deployment, the changelig for this release also contains some changes from that release.

## [freemarker-java8-1.1.6](https://github.com/lazee/freemarker-java-8/tree/freemarker-java8-1.1.6) (2017-07-24)

- Added isEqual, isAfter and isBefore methods to adapters for LocalDate, LocalDateTime and Localtime (Code contributed by @kingmaoam)

## [freemarker-java8-1.1.5](https://github.com/lazee/freemarker-java-8/tree/freemarker-java8-1.1.5) (2016-12-19)

- OBS! Fixed bug that made the DateTimeFormatter use the default VM Locale instead of the Locale from the Freemarker configuration. [\#PR8](https://github.com/lazee/freemarker-java-8/pull/8).

## [freemarker-java8-1.1.4](https://github.com/lazee/freemarker-java-8/tree/freemarker-java8-1.1.4) (2016-12-15)

- ZoneOffsetAdapter is not used [\#6](https://github.com/lazee/freemarker-java-8/issues/6)


## [freemarker-java8-1.1.2](https://github.com/lazee/freemarker-java-8/tree/freemarker-java8-1.1.2) (2016-10-26)

**Closed issues:**

- wrong maven dependency  [\#5](https://github.com/lazee/freemarker-java-8/issues/5)
- Install error [\#3](https://github.com/lazee/freemarker-java-8/issues/3)
- Deploy to mavenCentral [\#1](https://github.com/lazee/freemarker-java-8/issues/1)

**Merged pull requests:**

- \#6 Fixed [\#7](https://github.com/lazee/freemarker-java-8/pull/7) ([lazee](https://github.com/lazee))
- Update installation documentation [\#4](https://github.com/lazee/freemarker-java-8/pull/4) ([mthmulders](https://github.com/mthmulders))
