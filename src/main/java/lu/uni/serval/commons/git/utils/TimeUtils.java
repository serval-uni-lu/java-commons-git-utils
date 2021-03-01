package lu.uni.serval.commons.git.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimeUtils {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_INSTANT.withLocale(Locale.FRANCE).withZone(ZoneOffset.UTC);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE.withLocale(Locale.FRANCE).withZone(ZoneOffset.UTC);

    private TimeUtils() {}

    public static String toIsoDateString(Instant instant){
        return dateFormatter.format(instant);
    }

    public static String toIsoDateTimeString(Instant instant){
        return dateTimeFormatter.format(instant);
    }

    public static Instant fromIsoDateString(String date){
        return LocalDate.from(dateFormatter.parse(date)).atStartOfDay().toInstant(ZoneOffset.UTC);
    }

    public static Instant fromIsoDateTimeString(String dateTime) {
        return dateTimeFormatter.parse(dateTime, Instant::from);
    }

    public static Instant toInstant(String date, DateTimeFormatter formatter){
        return LocalDateTime.parse(date, formatter).toInstant(ZoneOffset.UTC);
    }
}
