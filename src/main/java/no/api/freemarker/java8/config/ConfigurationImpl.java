package no.api.freemarker.java8.config;

public class ConfigurationImpl implements Configuration {

    private TimeZoneStrategy defaultTimeZoneStrategy;

    ConfigurationImpl() {
        this.defaultTimeZoneStrategy = TimeZoneStrategy.SYSTEM; // Default to keep backward compatibility
    }

    void setDefaultTimeZoneStrategy(TimeZoneStrategy defaultTimeZoneStrategy) {
        this.defaultTimeZoneStrategy = defaultTimeZoneStrategy;
    }

    @Override
    public TimeZoneStrategy getDefaultTimeZoneStrategy() {
        return defaultTimeZoneStrategy;
    }
}
