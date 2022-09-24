package me.gicheol.calendar.example;

import lombok.Data;

@Data
public class CalendarForm {

    private Long id;

    private String title;

    private String start;

    private boolean isDel;

    private String searchDate;

}
