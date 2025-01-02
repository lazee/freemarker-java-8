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

    public static final Version FMV = Configuration.VERSION_2_3_34;

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
