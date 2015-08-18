package no.api.freemarker.java8.time;

import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static no.api.freemarker.java8.time.DateTimeTools.METHOD_FORMAT;
import static no.api.freemarker.java8.time.DateTimeTools.METHOD_UNKNOWN_MSG;
import static no.api.freemarker.java8.time.DateTimeTools.createDateTimeFormatter;

public class ZonedDateTimeAdapter extends AbstractAdapter<ZonedDateTime> implements AdapterTemplateModel,
        TemplateScalarModel, TemplateHashModel {

    public ZonedDateTimeAdapter(ZonedDateTime obj) {
        super(obj);
    }

    public TemplateModel get(String s) throws TemplateModelException {
        if (METHOD_FORMAT.equals(s)) {
            return new ZonedDateTimeFormatter(getObject());
        }
        throw new TemplateModelException(METHOD_UNKNOWN_MSG + s);    }

    /* Formatter */

    public class ZonedDateTimeFormatter extends AbstractFormatter<ZonedDateTime> implements TemplateMethodModelEx {

        public ZonedDateTimeFormatter(ZonedDateTime obj) {
            super(obj);
        }

        public Object exec(List list) throws TemplateModelException {
            ZoneId zoneId = DateTimeTools.zoneIdLookup(list, 1);
            if (isDifferentTimeZoneRequested(zoneId)) {
                return getObject().withZoneSameInstant(zoneId).format(
                        createDateTimeFormatter(list, 0, DateTimeFormatter.ISO_ZONED_DATE_TIME));
            } else {
                return getObject().format(createDateTimeFormatter(list, 0, DateTimeFormatter.ISO_ZONED_DATE_TIME));
            }
        }

        private boolean isDifferentTimeZoneRequested(ZoneId zoneId) {
            return !getObject().getZone().equals(zoneId);
        }



    }

}
