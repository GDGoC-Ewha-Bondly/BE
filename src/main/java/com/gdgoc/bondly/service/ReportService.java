package com.gdgoc.bondly.service;

import com.gdgoc.bondly.domain.SnsApp;
import com.gdgoc.bondly.domain.SnsInsight;
import com.gdgoc.bondly.domain.SnsReview;
import com.gdgoc.bondly.domain.SnsUsage;
import com.gdgoc.bondly.dto.CalendarDto;
import com.gdgoc.bondly.dto.DailyReportDto;
import com.gdgoc.bondly.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final SnsInsightRepository snsInsightRepository;
    private final SnsReviewRepository snsReviewRepository;
    private final SnsUsageRepository snsUsageRepository;
    private final SnsAppRepository snsAppRepository;
    private final UserRepository userRepository;
    private final QuizResponseRepository quizResponseRepository;

    public DailyReportDto getReportByDateAndId(String date, Long userId) {
        // 해당 날짜의 회고 내용 가져오기
        SnsReview review = snsReviewRepository.findByDateAndUserId(date, userId);

        // 해당 날짜의 시선 내용 가져오기
        SnsInsight insight = snsInsightRepository.findByDateAndUserId(date, userId);

        // 해당 날짜의 sns 사용시간 가져오기
        // usageList: sns 앱별 사용시간 리스트
        List<SnsUsage> usageList = snsUsageRepository.findAllByDateAndUserId(date, userId);
        Map<String, Integer> usageMap = usageList.stream()
                .collect(Collectors.toMap(
                        usage -> snsAppRepository.findById(usage.getAppId())
                                .map(SnsApp::getAppName)
                                .orElse("Unknown App"),
                        SnsUsage::getUsageTime
                ));

        return DailyReportDto.builder()
                .date(date)
                .usage(usageMap)
                .review(review.getReviewContent())
                .insight(insight.getInsightContent())
                .build();
    }

    public CalendarDto getCalendarByMonthAndId(String month, Long userId) {
        // 날짜별 활동 여부를 저장할 맵
        Map<String, CalendarDto.RecordDto.RecordDtoBuilder> recordMap = new HashMap<>();

        // 사용시간 목표 달성 여부 조회
        List<String> goalAchievedDates = snsUsageRepository.findDatesByMonthAndUserId(month, userId);
        for (String date : goalAchievedDates) {
            // 해당 날짜의 usage time 모두 조회 후 합산
            List<Integer> usageTimes = snsUsageRepository.findUsageTimesByDateAndUserId(date, userId);
            int totalUsageTime = usageTimes.stream().mapToInt(Integer::intValue).sum();

            // 사용자 목표 시간 조회
            int userGoal = userRepository.findUserGoalByUserId(userId);

            // 목표 달성 여부 판단
            boolean isGoalAchieved = totalUsageTime < userGoal;

            recordMap.computeIfAbsent(date, d -> CalendarDto.RecordDto.builder().date(d))
                    .goalAchieved(isGoalAchieved);
        }

        // 리뷰 작성 여부 조회
        List<String> reviewWrittenDates = snsReviewRepository.findDatesByMonthAndUserId(month, userId);
        for (String date : reviewWrittenDates) {
            recordMap.computeIfAbsent(date, d -> CalendarDto.RecordDto.builder().date(d))
                    .reviewWritten(true);
        }

        // 시선 작성 여부 조회
        List<String> insightWrittenDates = snsInsightRepository.findDatesByMonthAndUserId(month, userId);
        for (String date : insightWrittenDates) {
            recordMap.computeIfAbsent(date, d -> CalendarDto.RecordDto.builder().date(d))
                    .insightWritten(true);
        }

        // 퀴즈 완료 여부 조회
        List<String> quizCompletedDates = quizResponseRepository.findDatesByMonthAndUserId(month, userId);
        for (String date : quizCompletedDates) {
            recordMap.computeIfAbsent(date, d -> CalendarDto.RecordDto.builder().date(d))
                    .quizCompleted(true);
        }

        List<CalendarDto.RecordDto> records = recordMap.values().stream()
                .map(CalendarDto.RecordDto.RecordDtoBuilder::build)
                .collect(Collectors.toList());

        return CalendarDto.builder()
                .month(month)
                .records(records)
                .build();
    }
}