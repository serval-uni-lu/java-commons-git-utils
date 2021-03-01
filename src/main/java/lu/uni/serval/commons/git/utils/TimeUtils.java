package lu.uni.serval.commons.git.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimeUtils {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_INSTANT.withLocale(Locale.FRANCE).withZone(ZoneOffset.UTC);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE.withLocale(Locale.FRANCE).withZone(ZoneOffset.UTC);

    public static String toDateString(Instant instant){
        return dateFormatter.format(instant);
    }

    public static String toDateTimeString(Instant instant){
        return dateTimeFormatter.format(instant);
    }

    public static Instant fromDateString(String date){
        return LocalDate.from(dateFormatter.parse(date)).atStartOfDay().toInstant(ZoneOffset.UTC);
    }

    public static Instant fromDateTimeString(String dateTime) {
        return dateTimeFormatter.parse(dateTime, Instant::from);
    }
}
