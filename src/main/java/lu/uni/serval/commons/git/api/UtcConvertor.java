package lu.uni.serval.commons.git.api;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class UtcConvertor implements Converter<LocalDate, Instant> {
    @Override
    public Instant convert(LocalDate value) {
        return value.atStartOfDay(ZoneOffset.UTC).toInstant();
    }
    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return typeFactory.constructType(LocalDate.class);
    }
    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return typeFactory.constructType(Instant.class);
    }
}
