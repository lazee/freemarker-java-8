package no.api.freemarker.java8.time;

import freemarker.template.AdapterTemplateModel;

import java.time.Clock;

/*
    https://docs.oracle.com/javase/8/docs/api/java/time/Clock.html
 */
public class ClockAdapter implements AdapterTemplateModel {

    private final Clock clock;

    public ClockAdapter(Clock clock) {
        this.clock = clock;
    }

    @Override
    public Object getAdaptedObject(Class aClass) {
        return clock.instant();
    }

}
