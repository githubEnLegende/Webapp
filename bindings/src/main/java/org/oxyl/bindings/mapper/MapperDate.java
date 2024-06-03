package org.oxyl.bindings.mapper;

import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class MapperDate {

    public MapperDate() {
    }

    public LocalDate stringtoLocalDate(String date) {
        LocalDate localDate = null;

        if (date != null && !date.isEmpty()) {
            return localDate = LocalDate.parse(date);
        }

        return localDate;
    }

    public String localDateToString(LocalDate localDate) {
        if (localDate != null) {
            return localDate.toString();
        }
        return null;
    }
}
