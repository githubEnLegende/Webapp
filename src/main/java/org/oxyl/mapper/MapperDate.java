package org.oxyl.mapper;

import java.time.LocalDate;
import org.springframework.stereotype.Component;


@Component
public class MapperDate {

    public MapperDate(){}

    public LocalDate stringtoLocalDate(String date) {
        LocalDate localDate = null;

        if (date != null && !date.isEmpty()) {
            return localDate = LocalDate.parse(date);
        }

        return localDate;
    }
}
