/*
 * Copyright (c) 2015-2024 Jakob Vad Nielsen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package no.api.freemarker.java8.time;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import no.api.freemarker.java8.Java8ObjectWrapper;
import no.api.freemarker.java8.zone.*;
import org.junit.After;
import org.junit.Assert;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.time.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import static freemarker.template.Configuration.VERSION_2_3_34;

public class DateTimeStepdefs {

    private String template;

    private Object obj;

    private Object obj2;

    private final Configuration configuration;

    private Java8ObjectWrapper objectWrapper;

    public DateTimeStepdefs() {
        this.configuration = new Configuration(VERSION_2_3_34);
        this.objectWrapper = new Java8ObjectWrapper(VERSION_2_3_34, new KeepingZoneStrategy());
        this.configuration.setObjectWrapper(objectWrapper);
    }

    @After
    public void runAfterHooks() {
        setStrategy(new KeepingZoneStrategy());
    }

    private void setStrategy(ZoneStrategy strategy) {
        setStrategy(VERSION_2_3_34, strategy);
    }

    private void setStrategy(Version freemarkerVersion, ZoneStrategy strategy) {
        this.objectWrapper = new Java8ObjectWrapper(freemarkerVersion, strategy);
        this.configuration.setObjectWrapper(objectWrapper);
    }

    @Given("^ZonedDateTime object for \"([^\"]*)\"$")
    public void zoneddatetime_object_for_T_Europe_Paris(String arg1) {
        obj = ZonedDateTime.parse(arg1);
    }

    @Given("^an freemarker environment with locale set to \"([^\"]*)\"$")
    public void an_freemarker_environment_with_locate_set_to(String arg1) {
        configuration.setLocale(Locale.forLanguageTag(arg1));
    }

    @Given("^timezone set to \"([^\"]*)\"$")
    public void timezone_set_to(String arg1) {
        configuration.setTimeZone(TimeZone.getTimeZone(ZoneId.of(arg1)));
    }

    @Given("^a template \"([^\"]*)\"$")
    public void a_template_$_zoneid(String template) {
        this.template = template;
    }

    @Given("^a ZoneId object for '(.*?)'$")
    public void a_ZoneId_object_for_Europe_Oslo(String zone) {
        obj = ZoneId.of(zone);
    }

    @Given("^timezone strategy set to 'system'$")
    public void timezone_strategy_set_to_system() {
        setStrategy(new SystemZoneStrategy());
    }

    @Given("^timezone strategy set to 'keeping'$")
    public void timezone_strategy_set_to_keeping() {
        setStrategy(new KeepingZoneStrategy());
    }

    @Given("^timezone strategy set to 'environment'$")
    public void timezone_strategy_set_to_environment() {
        setStrategy(new EnvironmentZoneStrategy());
    }

    @Given("^timezone strategy set to 'static' with timezone \"([^\"]*)\"$")
    public void timezone_strategy_set_to_static_with_timezone(final String arg1) {
        setStrategy(new StaticZoneStrategy(ZoneId.of(arg1)));
    }

    @Given("^timezone strategy set to 'static' with freemarker version 2_3_34$")
    public void timezone_strategy_set_to_static_with_version(final String arg1) {
        setStrategy(Configuration.VERSION_2_3_34, new StaticZoneStrategy(ZoneId.of(arg1)));
    }

    @Given("^system timezone set to \"([^\"]*)\"$")
    public void system_timezone_set_to(final String arg1) {
        TimeZone.setDefault(TimeZone.getTimeZone(arg1));
    }

    @Then("^expect the template to return \"([^\"]*)\"$")
    public void expect_the_template_to_return(String res) throws Throwable {
        try (Writer out = process(res)) {
            if (!res.equals(out.toString())) {
                Assert.fail("Expected '" + res + "', but got '" + out + "'");
            }
        }
    }

    @Then("^expect the template to return \"([^\"]*)\" or \"([^\"]*)\"$")
    public void expect_the_template_to_return(String res, String res2) throws Throwable {
        try (Writer out = process(res)) {
            if (!res.equals(out.toString()) && !res2.equals(out.toString())) {
                Assert.fail("Expected '" + res + "' or '" + res2 + "', but got '" + out + "'");
            }
        }
    }

    @Then("^expect the template to return the current year$")
    public void expect_the_template_to_return_the_current_year() throws Throwable {
        String res = Year.now().toString();
        try (Writer out = process(res)) {
            if (!res.equals(out.toString())) {
                Assert.fail("Expected '" + res + "', but got '" + out + "'");
            }
        }
    }

    @Then("^expect the template to return true$")
    public void expect_the_template_to_true() throws Throwable {
        try (Writer out = process("true")) {
            if (!"true".equals(out.toString())) {
                Assert.fail("Expected 'true', but got '" + out + "'");
            }
        }
    }

    @Then("^expect the template to return false$")
    public void expect_the_template_to_false() throws Throwable {
        try (Writer out = process("false")) {
            if (!"false".equals(out.toString())) {
                Assert.fail("Expected 'false', but got '" + out + "'");
            }
        }
    }

    private Writer process(String res) throws IOException, TemplateException {
        Map<String, Object> map = new HashMap<>();
        map.put("obj", obj);
        if (obj2 != null) {
            map.put("obj2", obj2);
        }
        Template t = new Template("name", new StringReader(template), configuration);
        Writer out = new StringWriter();
        t.process(map, out);
        out.flush();
        if (res == null) {
            Assert.fail("No expected result in feature");
        }
        return out;
    }

    @Given("^YearMonth object for \"([^\"]*)\"$")
    public void yearmonth_object_for(String arg1) {
        obj = YearMonth.parse(arg1);
    }

    @Given("^Year object for \"([^\"]*)\"$")
    public void year_object_for(String arg1) {
        obj = Year.parse(arg1);
    }

    @Then("^expect UnsupportedTemporalTypeException$")
    public void expect_UnsupportedTemporalTypeException() {
        obj = Year.now();
    }

    @Given("^Clock object for \"([^\"]*)\"$")
    public void clock_object_for(String arg1) {
        obj = Clock.fixed(Instant.parse(arg1), configuration.getTimeZone().toZoneId());
    }

    @Given("^Duration object for \"([^\"]*)\"$")
    public void duration_object_for(String arg1) {
        obj = Duration.parse(arg1);
    }

    @Given("^Instant object for \"([^\"]*)\"$")
    public void instant_object_for(String arg1) {
        obj = Instant.parse(arg1);
    }

    @Given("^LocalDate object for \"([^\"]*)\"$")
    public void localdate_object_for(String arg1) {
        obj = LocalDate.parse(arg1);
    }

    @Given("^LocalDate object2 for \"([^\"]*)\"$")
    public void localdate_object2_for(String arg1) {
        obj2 = LocalDate.parse(arg1);
    }

    @Given("^LocalDateTime object for \"([^\"]*)\"$")
    public void localdatetime_object_for(String arg1) {
        obj = LocalDateTime.parse(arg1);
    }

    @Given("^LocalDateTime object2 for \"([^\"]*)\"$")
    public void localdatetime_object2_for(String arg1) {
        obj2 = LocalDateTime.parse(arg1);
    }

    @Given("^LocalTime object for \"([^\"]*)\"$")
    public void localtime_object_for(String arg1) {
        obj = LocalTime.parse(arg1);
    }

    @Given("^LocalTime object2 for \"([^\"]*)\"$")
    public void localtime_object2_for(String arg1) {
        obj2 = LocalTime.parse(arg1);
    }

    @Given("^MonthDay object for \"([^\"]*)\"$")
    public void monthday_object_for(String arg1) {
        obj = MonthDay.parse(arg1);
    }

    @Given("^OffsetDateTime object for \"([^\"]*)\"$")
    public void offsetdatetime_object_for(String arg1) {
        obj = OffsetDateTime.parse(arg1);
    }

    @Given("^OffsetTime object for \"([^\"]*)\"$")
    public void offsettime_object_for(String arg1) {
        obj = OffsetTime.parse(arg1);
    }

    @Given("^Period object for \"([^\"]*)\"$")
    public void period_object_for(String arg1) {
        obj = Period.parse(arg1);
    }

    @Given("^ZoneOffset object for \"([^\"]*)\"$")
    public void zoneoffset_object_for(Integer arg1) {
        obj = ZoneOffset.ofHours(arg1);
    }

}
