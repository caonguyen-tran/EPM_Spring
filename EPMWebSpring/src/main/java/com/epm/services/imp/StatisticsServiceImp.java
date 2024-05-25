/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.epm.pojo.JoinActivity;
import com.epm.pojo.Score;
import com.epm.pojo.Student;
import com.epm.pojo.User;
import com.epm.repositories.JoinActivityRepository;
import com.epm.repositories.ScoreRepository;
import com.epm.repositories.StudentRepository;
import com.epm.repositories.UserRepository;
import com.epm.services.StatisticsService;
import com.epm.utils.AchievementStatisticsDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Win11
 */
@Service
public class StatisticsServiceImp implements StatisticsService {

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JoinActivityRepository joinActivityRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public List<AchievementStatisticsDTO> getAchievementStatisticsByClass(int classId) {
        List<Student> students = (List<Student>) studentRepository.findByClassId(classId);
        Map<String, Integer> achievementMap = new HashMap<>();

        for (Student student : students) {
            User user = userRepository.findByStudentId(student.getId());
            if (user != null) {
                int totalScore = 0;
                List<JoinActivity> joinActivities = joinActivityRepository.findByUserIdAndRollup(user.getId(), true);

                for (JoinActivity joinActivity : joinActivities) {
                    List<Score> scores = scoreRepository.findByActivityId(joinActivity.getActivityId().getId());
                    totalScore += scores.stream().mapToInt(Score::getScoreValue).sum();
                }

                String achievement = determineAchievement(totalScore);
                achievementMap.put(achievement, achievementMap.getOrDefault(achievement, 0) + 1);
            }
        }

        return achievementMap.entrySet().stream()
                .map(entry -> new AchievementStatisticsDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private static String determineAchievement(int totalScore) {
        if (totalScore >= 90) {
            return "Xuất sắc";
        } else if (totalScore >= 80) {
            return "Giỏi";
        } else if (totalScore >= 70) {
            return "Khá";
        } else if (totalScore >= 60) {
            return "Trung bình";
        } else if (totalScore >= 50) {
            return "Yếu";
        } else {
            return "Kém";
        }
    }
}
