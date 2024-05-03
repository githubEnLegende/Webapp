package org.oxyl.mapper;

import java.time.LocalDate;

public class MapperDate {

    private static MapperDate instance;

    public static MapperDate getInstance() {
        if (instance == null) {
            instance = new MapperDate();
        }
        return instance;
    }

    public LocalDate stringtoLocalDate(String date) {
        LocalDate localDate = null;

        if (date != null && !date.isEmpty()) {
            return localDate = LocalDate.parse(date);
        }

        return localDate;
    }
}
