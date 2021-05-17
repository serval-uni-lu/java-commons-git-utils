package lu.uni.serval.commons.git.api;

/*-
 * #%L
 * GIT Utils
 * %%
 * Copyright (C) 2020 - 2021 University of Luxembourg, Renaud RWEMALIKA
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
