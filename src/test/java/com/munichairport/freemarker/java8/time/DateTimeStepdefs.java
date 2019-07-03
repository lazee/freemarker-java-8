package com.munichairport.freemarker.java8.time;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.junit.Assert;

import com.munichairport.freemarker.java8.Java8ObjectWrapper;
import com.munichairport.freemarker.java8.config.Java8Configuration;
import com.munichairport.freemarker.java8.config.timezone.strategy.EnvironmentTimezoneStrategy;
import com.munichairport.freemarker.java8.config.timezone.strategy.KeepingTimezoneStrategy;
import com.munichairport.freemarker.java8.config.timezone.strategy.StaticTimezoneStrategy;
import com.munichairport.freemarker.java8.config.timezone.strategy.SystemTimezoneStrategy;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class DateTimeStepdefs {

    private final Configuration configuration;

    private final Java8Configuration java8Configuration;

    private List<Runnable> afterHooks;

    private String template;

    private Object obj;

    private Object obj2;

    public DateTimeStepdefs() {
        this.configuration = new Configuration(Configuration.VERSION_2_3_23);
        final Java8ObjectWrapper testee = new Java8ObjectWrapper(Configuration.VERSION_2_3_23);
        this.configuration.setObjectWrapper(testee);
        this.java8Configuration = testee.getConfiguration();
    }

    @Before
    public void before() {
        this.afterHooks = new LinkedList<>();
    }

    @After
    public void runAfterHooks() {
        this.afterHooks.forEach(Runnable::run);
    }

    @Given("^ZonedDateTime object for \"([^\"]*)\"$")
    public void zoneddatetime_object_for_T_Europe_Paris(final String arg1) throws Throwable {
        this.obj = ZonedDateTime.parse(arg1);
    }

    @Given("^an freemarker environment with locale set to \"([^\"]*)\"$")
    public void an_freemarker_environment_with_locate_set_to(final String arg1) throws Throwable {
        this.configuration.setLocale(Locale.forLanguageTag(arg1));
    }

    @Given("^timezone strategy set to 'system'$")
    public void timezone_strategy_set_to_system() throws Throwable {
        this.java8Configuration.setTimezoneStrategy(SystemTimezoneStrategy.INSTANCE);
    }

    @Given("^timezone strategy set to 'keeping'$")
    public void timezone_strategy_set_to_keeping() throws Throwable {
        this.java8Configuration.setTimezoneStrategy(KeepingTimezoneStrategy.INSTANCE);
    }

    @Given("^timezone strategy set to 'environment'$")
    public void timezone_strategy_set_to_environment() throws Throwable {
        this.java8Configuration.setTimezoneStrategy(EnvironmentTimezoneStrategy.INSTANCE);
    }

    @Given("^timezone strategy set to 'static' with timezone \"([^\"]*)\"$")
    public void timezone_strategy_set_to_static_with_timezone(final String arg1) throws Throwable {
        this.java8Configuration.setTimezoneStrategy(StaticTimezoneStrategy.of(ZoneId.of(arg1)));
    }

    @Given("^system timezone set to \"([^\"]*)\"$")
    public void system_timezone_set_to(final String arg1) throws Throwable {
        TimeZone.setDefault(TimeZone.getTimeZone(arg1));
        // Reset to default timezone afterwards
        this.afterHooks.add(() -> TimeZone.setDefault(null));
    }

    @Given("^timezone set to \"([^\"]*)\"$")
    public void timezone_set_to(final String arg1) throws Throwable {
        this.configuration.setTimeZone(TimeZone.getTimeZone(ZoneId.of(arg1)));
    }

    @Given("^a template \"([^\"]*)\"$")
    public void a_template_$_zoneid(final String template) throws Throwable {
        this.template = template;
    }

    @Given("^a ZoneId object for '(.*?)'$")
    public void a_ZoneId_object_for_Europe_Oslo(final String zone) throws Throwable {
        this.obj = ZoneId.of(zone);
    }

    @Then("^expect the template to return \"([^\"]*)\"$")
    public void expect_the_template_to_return(final String res) throws Throwable {
        final Writer out = process(res);
        if (!res.equals(out.toString())) {
            Assert.fail("Expected '" + res + "', but got '" + out.toString());
        }
    }

    @Then("^expect the template to return the current year$")
    public void expect_the_template_to_return_the_current_year() throws Throwable {
        final String res = Year.now().toString();
        final Writer out = process(res);
        if (!res.equals(out.toString())) {
            Assert.fail("Expected '" + res + "', but got '" + out.toString());
        }
    }

    @Then("^expect the template to return true$")
    public void expect_the_template_to_true() throws Throwable {
        final Writer out = process("true");
        if (!"true".equals(out.toString())) {
            Assert.fail("Expected 'true', but got '" + out.toString());
        }
    }

    @Then("^expect the template to return false$")
    public void expect_the_template_to_false() throws Throwable {
        final Writer out = process("false");
        if (!"false".equals(out.toString())) {
            Assert.fail("Expected 'false', but got '" + out.toString());
        }
    }

    private Writer process(final String res) throws IOException, TemplateException {
        final Map<String, Object> map = new HashMap<>();
        map.put("obj", this.obj);
        if (this.obj2 != null) {
            map.put("obj2", this.obj2);
        }
        final Template t = new Template("name", new StringReader(this.template), this.configuration);
        final Writer out = new StringWriter();
        t.process(map, out);
        out.flush();
        if (res == null) {
            Assert.fail("No expected result in feature");
        }
        return out;
    }

    @Given("^YearMonth object for \"([^\"]*)\"$")
    public void yearmonth_object_for(final String arg1) throws Throwable {
        this.obj = YearMonth.parse(arg1);
    }

    @Given("^Year object for \"([^\"]*)\"$")
    public void year_object_for(final String arg1) throws Throwable {
        this.obj = Year.parse(arg1);
    }

    @Then("^expect UnsupportedTemporalTypeException$")
    public void expect_UnsupportedTemporalTypeException() throws Throwable {
        this.obj = Year.now();
    }

    @Given("^Clock object for \"([^\"]*)\"$")
    public void clock_object_for(final String arg1) throws Throwable {
        this.obj = Clock.fixed(Instant.parse(arg1), this.configuration.getTimeZone().toZoneId());
    }

    @Given("^Duration object for \"([^\"]*)\"$")
    public void duration_object_for(final String arg1) throws Throwable {
        this.obj = Duration.parse(arg1);
    }

    @Given("^Instant object for \"([^\"]*)\"$")
    public void instant_object_for(final String arg1) throws Throwable {
        this.obj = Instant.parse(arg1);
    }

    @Given("^LocalDate object for \"([^\"]*)\"$")
    public void localdate_object_for(final String arg1) throws Throwable {
        this.obj = LocalDate.parse(arg1);
    }

    @Given("^LocalDate object2 for \"([^\"]*)\"$")
    public void localdate_object2_for(final String arg1) throws Throwable {
        this.obj2 = LocalDate.parse(arg1);
    }

    @Given("^LocalDateTime object for \"([^\"]*)\"$")
    public void localdatetime_object_for(final String arg1) throws Throwable {
        this.obj = LocalDateTime.parse(arg1);
    }

    @Given("^LocalDateTime object2 for \"([^\"]*)\"$")
    public void localdatetime_object2_for(final String arg1) throws Throwable {
        this.obj2 = LocalDateTime.parse(arg1);
    }

    @Given("^LocalTime object for \"([^\"]*)\"$")
    public void localtime_object_for(final String arg1) throws Throwable {
        this.obj = LocalTime.parse(arg1);
    }

    @Given("^LocalTime object2 for \"([^\"]*)\"$")
    public void localtime_object2_for(final String arg1) throws Throwable {
        this.obj2 = LocalTime.parse(arg1);
    }

    @Given("^MonthDay object for \"([^\"]*)\"$")
    public void monthday_object_for(final String arg1) throws Throwable {
        this.obj = MonthDay.parse(arg1);
    }

    @Given("^OffsetDateTime object for \"([^\"]*)\"$")
    public void offsetdatetime_object_for(final String arg1) throws Throwable {
        this.obj = OffsetDateTime.parse(arg1);
    }

    @Given("^OffsetTime object for \"([^\"]*)\"$")
    public void offsettime_object_for(final String arg1) throws Throwable {
        this.obj = OffsetTime.parse(arg1);
    }

    @Given("^Period object for \"([^\"]*)\"$")
    public void period_object_for(final String arg1) throws Throwable {
        this.obj = Period.parse(arg1);
    }

    @Given("^ZoneOffset object for \"([^\"]*)\"$")
    public void zoneoffset_object_for(final Integer arg1) throws Throwable {
        this.obj = ZoneOffset.ofHours(arg1);
    }

}
