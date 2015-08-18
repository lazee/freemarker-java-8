package no.api.freemarker.java8.time;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import freemarker.template.Configuration;
import freemarker.template.Template;
import no.api.freemarker.java8.Java8ObjectWrapper;
import org.junit.Assert;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class DateTimeStepdefs {

    private final Configuration configuration;

    private String template;

    private Object obj;

    public DateTimeStepdefs() {
        this.configuration = new Configuration();
        this.configuration.setObjectWrapper(new Java8ObjectWrapper(Configuration.VERSION_2_3_23));
    }

    @Given("^ZonedDateTime object for \"([^\"]*)\"$")
    public void zoneddatetime_object_for_T_Europe_Paris(String arg1) throws Throwable {
        obj = ZonedDateTime.parse(arg1);
    }

    @Given("^an freemarker environment with locale set to \"([^\"]*)\"$")
    public void an_freemarker_environment_with_locate_set_to(String arg1) throws Throwable {
        configuration.setLocale(Locale.forLanguageTag(arg1));
    }

    @Given("^timezone set to \"([^\"]*)\"$")
    public void timezone_set_to(String arg1) throws Throwable {
        configuration.setTimeZone(TimeZone.getTimeZone(ZoneId.of(arg1)));
    }

    @Given("^a template \"([^\"]*)\"$")
    public void a_template_$_zoneid(String template) throws Throwable {
        this.template = template;
    }

    @Given("^a ZoneId object for '(.*?)'$")
    public void a_ZoneId_object_for_Europe_Oslo(String zone) throws Throwable {
        obj = ZoneId.of(zone);
    }

    @Then("^expect the template to return \"([^\"]*)\"$")
    public void expect_the_template_to_return(String res) throws Throwable {
        Map<String, Object> map = new HashMap<>();
        map.put("obj", obj);
        Template t = new Template("name", new StringReader(template), configuration);
        Writer out = new StringWriter();
        t.process(map, out);
        out.flush();
        if (res == null) {
            Assert.fail("No expected result in feature");
        }
        if (!res.equals(out.toString())) {
            Assert.fail("Expected '" + res + "', but got '" + out.toString());
        }

    }

}
