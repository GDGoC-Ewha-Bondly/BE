package com.gdgoc.bondly.controller;

import com.gdgoc.bondly.dto.CalendarDto;
import com.gdgoc.bondly.dto.DailyReportDto;
import com.gdgoc.bondly.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/{date}")
    public ResponseEntity<DailyReportDto> getDailyReport(@PathVariable String date,
                                                         @RequestParam(value = "user_id", required = false) Long userId) {
        if (userId == null) {
            // 현재 로그인한 사용자의 user_id 가져오기
            // 테스트용 id 반환 -> jwt 로그인 구현 후 수정
            userId = 0L;
        }
        DailyReportDto dailyReportDto = reportService.getReportByDateAndId(date, userId);
        return ResponseEntity.ok(dailyReportDto);
    }

    @GetMapping("/{id}/{month}")
    public ResponseEntity<CalendarDto> getCalendar(@PathVariable String month,
                                                   @RequestParam(value = "user_id", required = false) Long userId) {
        if (userId == null) {
            // 현재 로그인한 사용자의 user_id 가져오기
            // 테스트용 id 반환 -> jwt 로그인 구현 후 수정
            userId = 0L;
        }
        CalendarDto calendarDto = reportService.getCalendarByMonthAndId(month, userId);
        return ResponseEntity.ok(calendarDto);
    }
}
