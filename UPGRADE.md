# Upgrade guide

## Upgrade from 2.0 to 2.1

Replace old deprecated `ZonedDateTimeStrategy` instances with the equivalent `ZoneStrategy` objects in the same package.

## Upgrade from 1.3 to 2.0

The 2.0 release addresses two major issues reported by users ([#18](https://github.com/lazee/freemarker-java-8/issues/18)/[#16](https://github.com/lazee/freemarker-java-8/issues/16)). It also introduces a new feature for manipulating time ([\#28](https://github.com/lazee/freemarker-java-8/pull/28)).  

The upgrade itself is nothing else than changing the version in your build configuration (pom.xml or something else). However if you need to stick to the old behaviour on how time zones are treated when formatting ZonedDateTime objects, then you need to add a second argument to Java8ObjectWrapper upon initialization:

```java
configuration.setObjectWrapper(new Java8ObjectWrapper(VERSION_2_3_23, new EnvironmentTimeStrategy());
```

### Notice

Recently this repository was moved from the Amedia organisation to my
private account on Github. The reason behind this is that I recently
left Amedia after 13 years (!) and that they let me take this project
with me. The package naming will however stay the same.
