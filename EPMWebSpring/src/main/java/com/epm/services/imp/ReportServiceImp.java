/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.epm.pojo.AccountStudent;
import com.epm.pojo.Activity;
import com.epm.pojo.Classes;
import com.epm.pojo.JoinActivity;
import com.epm.pojo.Score;
import com.epm.pojo.ScoreStudent;
import com.epm.pojo.Semester;
import com.epm.pojo.Student;
import com.epm.pojo.Term;
import com.epm.utils.StudentReportDTO;
import com.epm.repositories.AccountStudentRepository;
import com.epm.repositories.ActivityRepository;
import com.epm.repositories.ClassRepository;
import com.epm.repositories.JoinActivityRepository;
import com.epm.repositories.ScoreRepository;
import com.epm.repositories.ScoreStudentRepository;
import com.epm.repositories.SemesterRepository;
import com.epm.repositories.StudentRepository;
import com.epm.repositories.TermRepository;
import com.epm.services.ReportService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Win11
 */
@Service
public class ReportServiceImp implements ReportService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private AccountStudentRepository accountStudentRepo;

    @Autowired
    private JoinActivityRepository joinActivityRepo;

    @Autowired
    private ScoreStudentRepository scoreStudentRepo;

    @Autowired
    private ScoreRepository scoreRepo;

    @Autowired
    private ActivityRepository activityRepo;

    @Autowired
    private SemesterRepository semesterRepo;

    @Autowired
    private TermRepository termRepo;

    @Autowired
    private ClassRepository classRepo;

    @Override
    public List<StudentReportDTO> getStatsScoreDetail(int studentId) {
        List<StudentReportDTO> result = new ArrayList<>();

        Student student = studentRepo.findById(studentId);
        if (student == null) {
            return result;
        }

        AccountStudent accountStudent = accountStudentRepo.findByStudentId(studentId);
        if (accountStudent == null) {
            return result;
        }

        List<JoinActivity> joinActivities = joinActivityRepo.findByAccountStudentIdAndRollup(accountStudent.getId(), true);

        for (JoinActivity joinActivity : joinActivities) {
            List<ScoreStudent> scoreStudents = scoreStudentRepo.findByJoinActivityId(joinActivity.getId());

            for (ScoreStudent scoreStudent : scoreStudents) {
                Score score = scoreRepo.findById(scoreStudent.getScoreId().getId());
                if (score != null) {
                    Classes classes = classRepo.findById(studentId);
                    if (classes != null) {
                        Activity activity = activityRepo.findById(score.getActivityId().getId());
                        if (activity != null) {
                            Semester semester = semesterRepo.findById(activity.getSemesterId().getId());
                            if (semester != null) {
                                Term term = termRepo.findById(activity.getTermId().getId());
                                if (term != null) {
                                    result.add(new StudentReportDTO(student, semester, activity, score, term, classes));
                                }
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

}
