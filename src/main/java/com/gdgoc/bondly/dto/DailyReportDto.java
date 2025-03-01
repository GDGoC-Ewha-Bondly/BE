package com.gdgoc.bondly.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class DailyReportDto {
    private final String date;
    private final Map<String, Integer> usage;
    private final String review;
    private final String insight;
}