package org.oxyl.mapper;

import java.time.LocalDate;

public class MapperDate {

    public LocalDate stringtoLocalDate(String date) {
        LocalDate localDate = null;

        if (date != null && !date.isEmpty()) {
           return localDate = LocalDate.parse(date);
        }

        return localDate;
    }
}
