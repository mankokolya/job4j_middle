package jsonparsing.pojo;

import java.time.LocalDate;
import java.util.Date;

public class DayPojo {
    private String name;
    private LocalDate date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
