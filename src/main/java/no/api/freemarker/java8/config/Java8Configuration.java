package no.api.freemarker.java8.config;

import java.util.Objects;

import no.api.freemarker.java8.config.timezone.TimezoneStrategy;
import no.api.freemarker.java8.config.timezone.strategy.EnvironmentTimezoneStrategy;

public class Java8Configuration {

	private TimezoneStrategy timezoneStrategy;

	private Java8Configuration(final TimezoneStrategy timezoneStrategy) {
		assert timezoneStrategy != null : "timezoneStrategy is null";
		this.timezoneStrategy = timezoneStrategy;
	}

	public static Java8Configuration.Builder builder() {
		return new Builder();
	}

	public static Java8Configuration defaultConfiguration() {
		return new Builder().build(); // Builder has the defaults set
	}
	
	public Java8Configuration.Builder toBuilder() {
		return new Builder(timezoneStrategy);
	}

	public static class Builder {
		public static final TimezoneStrategy DEFAULT_TIMEZONE_STRATEGY = EnvironmentTimezoneStrategy.INSTANCE;

		private TimezoneStrategy timezoneStrategy = DEFAULT_TIMEZONE_STRATEGY;

		private Builder(final TimezoneStrategy timezoneStrategy) {
			assert timezoneStrategy != null : "timezoneStrategy is null";
			this.timezoneStrategy = timezoneStrategy;
		}

		private Builder() {
		}

		public Builder timezoneStrategy(final TimezoneStrategy timezoneStrategy) {
			this.timezoneStrategy = Objects.requireNonNull(timezoneStrategy, "timezoneStrategy");
			return this;
		}

		public Java8Configuration build() {
			return new Java8Configuration(timezoneStrategy);
		}
	}

	public void setTimezoneStrategy(final TimezoneStrategy timezoneStrategy) {
		this.timezoneStrategy = Objects.requireNonNull(timezoneStrategy, "timezoneStrategy");
	}

	public TimezoneStrategy getTimezoneStrategy() {
		return timezoneStrategy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((timezoneStrategy == null) ? 0 : timezoneStrategy.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Java8Configuration other = (Java8Configuration) obj;
		if (timezoneStrategy == null) {
			if (other.timezoneStrategy != null)
				return false;
		} else if (!timezoneStrategy.equals(other.timezoneStrategy))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Java8Configuration [timezoneStrategy=" + timezoneStrategy + "]";
	}
}
