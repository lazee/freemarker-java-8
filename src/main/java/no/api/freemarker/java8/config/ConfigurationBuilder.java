package no.api.freemarker.java8.config;

public class ConfigurationBuilder {

    private ConfigurationImpl configuration;

    private ConfigurationBuilder() {
        this.configuration = new ConfigurationImpl();
    }

    public static ConfigurationBuilder create() {
        return new ConfigurationBuilder();
    }

    public ConfigurationBuilder defaultTimeZoneStrategy(TimeZoneStrategy strategy) {
        this.configuration.setDefaultTimeZoneStrategy(strategy);
        return this;
    }

    public Configuration build() {
        return configuration;
    }
}
