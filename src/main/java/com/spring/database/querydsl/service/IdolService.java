package com.spring.database.querydsl.service;

import com.spring.database.querydsl.dto.GroupAverageAge;
import com.spring.database.querydsl.repository.GroupRepository;
import com.spring.database.querydsl.repository.IdolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IdolService {

    private final GroupRepository groupRepository;

    // 그룹별 평균 나이를 조회
    public List<GroupAverageAge> average() {
        List<GroupAverageAge> averageAgeList = groupRepository.groupAverage();
        return averageAgeList;
    }


}
