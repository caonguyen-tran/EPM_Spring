/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.services.imp;

import com.epm.pojo.JoinActivity;
import com.epm.pojo.Score;
import com.epm.pojo.ScoreStudent;
import com.epm.pojo.Student;
import com.epm.pojo.User;
import com.epm.repositories.JoinActivityRepository;
import com.epm.repositories.ScoreRepository;
import com.epm.repositories.ScoreStudentRepository;
import com.epm.repositories.StudentRepository;
import com.epm.repositories.UserRepository;
import com.epm.services.ScoreStudentService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
@Service
public class ScoreStudentServiceImp implements ScoreStudentService {

    @Autowired
    private ScoreStudentRepository scoreStudentRepository;

    @Autowired
    private ScoreRepository scoreRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JoinActivityRepository joinActivityRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Override
    public ScoreStudent createScoreStudent(ScoreStudent scoreStudent) {
        return this.scoreStudentRepository.createScoreStudent(scoreStudent);
    }

    @Override
    public List<ScoreStudent> findAll() {
        return this.scoreStudentRepository.findAll();
    }

    @Override
    public ScoreStudent findById(int scoreStudentId) {
        return this.scoreStudentRepository.findById(scoreStudentId);
    }

    @Override
    public List<ScoreStudent> findByUserId(int userId) {
        return this.scoreStudentRepository.findByUserId(userId);
    }

    @Override
    public List<ScoreStudent> findByJoinActivityId(int joinActivityId) {
        return this.scoreStudentRepository.findByJoinActivityId(joinActivityId);
    }

    @Override
    public void save(ScoreStudent scoreStudent) {
        this.scoreStudentRepository.save(scoreStudent);
    }

    @Override
    public void loadCsv(MultipartFile file, int activityId) throws IOException {
        try ( BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
            for (CSVRecord csvRecord : csvParser) {
                String email = csvRecord.get("email");
                String proofJoining = csvRecord.get("proofJoining");
                String note = csvRecord.get("note");

                Student student = studentRepo.findStudentByEmail(email);
                if (student != null) {
                    User u = userRepo.findByStudentId(student.getId());
                    if (u != null) {
                        JoinActivity joinActivity = joinActivityRepo.findByUserAndActivity(u.getId(), activityId);
                        if (joinActivity != null) {
                            joinActivity.setProofJoining(proofJoining);
                            joinActivity.setRollup(true);
                            joinActivity.setNote(note);
                            joinActivityRepo.update(joinActivity);
                        } else {
                            Logger.getLogger(ScoreStudentServiceImp.class.getName()).log(Level.SEVERE, "Student didn't register the activity");
                            continue;
                        }

                        for (String header : csvParser.getHeaderMap().keySet()) {
                            if (header.startsWith("scoreName")) {
                                Score score = scoreRepo.getScoreByNameAndActivity(csvRecord.get(header), activityId);
                                if (score != null) {
                                    ScoreStudent scoreStudent = new ScoreStudent();
                                    scoreStudent.setJoinActivityId(joinActivity);
                                    scoreStudent.setScoreId(score);
                                    scoreStudentRepository.save(scoreStudent);
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            Logger.getLogger(ScoreStudentServiceImp.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
    }

}
