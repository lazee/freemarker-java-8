package no.api.freemarker.java8.time;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import no.api.freemarker.java8.Java8ObjectWrapper;
import no.api.freemarker.java8.zone.KeepingZoneStrategy;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.time.Clock;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public class TemplateTest {

    public static final Version FMV = Configuration.VERSION_2_3_31;

    @Test
    public void testFull() throws IOException {
        // Create FreeMarker configuration
        Configuration cfg = new Configuration(FMV);
        // Override the default ObjectWrapper with the one from freemarker-java-8
        cfg.setObjectWrapper(new Java8ObjectWrapper(FMV, new KeepingZoneStrategy()));
        // Set class for template loading. In this case the test class itself
        cfg.setClassForTemplateLoading(this.getClass(), "/templates");
        cfg.setDefaultEncoding("UTF-8");

        // Load or full FreeMarker template tester
        Template template = cfg.getTemplate("full.ftl");
        
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("clock", Clock.system(ZoneId.of("Europe/Oslo")));

        try (StringWriter out = new StringWriter()) {
            template.process(templateData, out);
            System.out.println(out.getBuffer().toString());
            out.flush();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }
}
