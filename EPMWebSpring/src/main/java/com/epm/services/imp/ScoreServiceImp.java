/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.epm.pojo.Activity;
import com.epm.pojo.JoinActivity;
import com.epm.pojo.Score;
import com.epm.pojo.ScoreStudent;
import com.epm.pojo.Semester;
import com.epm.pojo.Term;
import com.epm.repositories.ActivityRepository;
import com.epm.repositories.JoinActivityRepository;
import com.epm.repositories.ScoreRepository;
import com.epm.repositories.ScoreStudentRepository;
import com.epm.repositories.SemesterRepository;
import com.epm.repositories.TermRepository;
import com.epm.services.ScoreService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MyLaptop
 */
@Service
public class ScoreServiceImp implements ScoreService {

    @Autowired
    private TermRepository termRepo;

    @Autowired
    private ActivityRepository activityRepo;

    @Autowired
    private ScoreRepository scoreRepo;

    @Autowired
    private ScoreStudentRepository scoreStudentRepo;

    @Autowired
    private SemesterRepository semesterRepo;

    @Autowired
    private JoinActivityRepository joinActivityRepo;

    @Override
    public Map<String, Integer> getTotalScoresByTerm(int accountStudentId) {
        Map<String, Integer> result = new HashMap<>();
        List<ScoreStudent> scoreStudents = scoreStudentRepo.findByAccountStudentId(accountStudentId);

        for (ScoreStudent scoreStudent : scoreStudents) {
            if (scoreStudent != null) {
                Score score = scoreRepo.findById(scoreStudent.getScoreId().getId());
                if (score != null) {
                    Activity activity = activityRepo.findById(score.getActivityId().getId());
                    if (activity != null) {
                        Term term = termRepo.findById(activity.getTermId().getId());
                        if (term != null) {
                            result.put(term.getName(), result.getOrDefault(term.getName(), 0) + score.getScoreValue());
                        }
                    }
                }
            }
        }

        return result;
    }

    @Override
    public int getTotalScores(int accountStudentId) {
        List<ScoreStudent> scoreStudents = scoreStudentRepo.findByAccountStudentId(accountStudentId);

        int totalScore = 0;
        for (ScoreStudent scoreStudent : scoreStudents) {
            Score score = scoreRepo.findById(scoreStudent.getScoreId().getId());
            if (score != null) {
                totalScore += score.getScoreValue();
            }
        }

        return totalScore;
    }

    @Override
    public List<Score> getScoresByAccountStudentIdAndSemester(int accountStudentId, int semesterId) {
        List<Activity> activities = activityRepo.findBySemesterId(semesterId);
        List<Score> scores = new ArrayList<>();

        Set<Integer> activityIds = activities.stream()
                .map(Activity::getId)
                .collect(Collectors.toSet());

        List<JoinActivity> joinActivities = joinActivityRepo.findByAccountStudentIdAndRollup(accountStudentId, true);

        for (JoinActivity joinActivity : joinActivities) {
            List<ScoreStudent> scoreStudents = scoreStudentRepo.findByJoinActivityId(joinActivity.getId());
            for (ScoreStudent scoreStudent : scoreStudents) {
                Score score = scoreRepo.findById(scoreStudent.getScoreId().getId());
                if (score != null && activityIds.contains(score.getActivityId().getId())) {
                    scores.add(score);
                }
            }
        }

        return scores;
    }

    @Override
    public List<Score> getScoresByStudentAndYear(int accountStudentId, String studyYear) {
        List<Semester> semesters = semesterRepo.getSemestersByStudyYear(studyYear);
        List<Score> scores = new ArrayList<>();

        for (Semester semester : semesters) {
            List<Score> semesterScores = getScoresByAccountStudentIdAndSemester(accountStudentId, semester.getId());
            scores.addAll(semesterScores);
        }

        return scores;
    }
}
