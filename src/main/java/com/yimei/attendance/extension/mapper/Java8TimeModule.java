package com.yimei.attendance.extension.mapper;

import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by joe on 12/13/14.
 */
public class Java8TimeModule extends SimpleModule {
    public Java8TimeModule() {
        this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        this.addSerializer(LocalDate.class, new LocalDateSerializer());

        this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        this.addDeserializer(LocalDate.class, new LocalDateDeserializer());
    }
}
