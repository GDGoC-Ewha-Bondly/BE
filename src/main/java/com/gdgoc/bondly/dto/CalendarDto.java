package com.gdgoc.bondly.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CalendarDto {
    private final String month;
    private final List<RecordDto> records;

    @Getter
    @Builder
    public static class RecordDto {
        private final String date;
        private final boolean goalAchieved;
        private final boolean reviewWritten;
        private final boolean insightWritten;
        private final boolean quizCompleted;
    }
}