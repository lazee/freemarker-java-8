package no.api.freemarker.java8.config;

import java.time.ZonedDateTime;
import java.util.Objects;

import no.api.freemarker.java8.Java8ObjectWrapper;
import no.api.freemarker.java8.config.timezone.TimezoneStrategy;
import no.api.freemarker.java8.config.timezone.strategy.KeepingTimezoneStrategy;

/**
 * Class providing the configuration for the {@link Java8ObjectWrapper}.
 * 
 * @author Fritz Lumnitz
 *
 */
public class Java8Configuration {

	/**
	 * The {@link TimezoneStrategy} used when formatting a {@link ZonedDateTime}.
	 */
	private TimezoneStrategy timezoneStrategy;

	private Java8Configuration(final TimezoneStrategy timezoneStrategy) {
		assert timezoneStrategy != null : "timezoneStrategy is null";
		this.timezoneStrategy = timezoneStrategy;
	}

	/**
	 * @return A new {@link Builder} instance. It has already all required
	 *         parameters set, according to the {@link #defaultConfiguration()}.
	 */
	public static Java8Configuration.Builder builder() {
		return new Builder();
	}

	/**
	 * @return A new default {@link Java8Configuration}.
	 */
	public static Java8Configuration defaultConfiguration() {
		return new Builder().build(); // Builder has the defaults set
	}

	/**
	 * @return A new {@link Builder} instance. It inherits all configuration values
	 *         of this instance.
	 */
	public Java8Configuration.Builder toBuilder() {
		return new Builder(timezoneStrategy);
	}

	/**
	 * Builder class for {@link Java8Configuration}.
	 * 
	 * @author Fritz Lumnitz
	 *
	 */
	public static class Builder {
		/**
		 * The default {@link TimezoneStrategy} used, when no one is explicitly set.
		 * Value is {@link KeepingTimezoneStrategy}.
		 */
		public static final TimezoneStrategy DEFAULT_TIMEZONE_STRATEGY = KeepingTimezoneStrategy.INSTANCE;

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
