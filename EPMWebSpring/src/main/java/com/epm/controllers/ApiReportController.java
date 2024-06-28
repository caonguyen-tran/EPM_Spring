/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.pojo.Activity;
import com.epm.pojo.JoinActivity;
import com.epm.pojo.Score;
import com.epm.pojo.Semester;
import com.epm.pojo.Student;
import com.epm.pojo.User;
import com.epm.services.ActivityService;
import com.epm.services.ScoreService;
import com.epm.services.SemesterService;
import com.epm.services.StudentService;
import com.epm.services.UserService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Win11
 */
@RestController
@RequestMapping("/api/report")
public class ApiReportController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private SemesterService semesterService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/pdf/{studentId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generatePdfReport(@PathVariable int studentId,
            @RequestParam Map<String, String> params) throws IOException {

        Integer semesterId = null;
        Student s = this.studentService.findById(studentId);
        User u = this.userService.findByStudentId(studentId);

        try {
            semesterId = Integer.parseInt(params.get("semesterId"));
            Semester semester = this.semesterService.findById(semesterId);
            String yearStudy = semester.getYearStudy();
            List<Object[]> activities = this.activityService.getActivitiesJoined(u.getId(), semesterId, yearStudy);
            List<Object[]> scores = this.scoreService.getScoresWithTerm(u.getId(), semesterId, yearStudy);

            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                Document document = new Document();
                PdfWriter.getInstance(document, out);

                BaseFont baseFont = BaseFont.createFont("fonts/DejaVuSans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font font = new Font(baseFont, 12);

                document.open();

                String fullname = s.getLastname() + s.getFirstname();

                document.add(new Paragraph("Student Information", font));
                document.add(new Paragraph("Fullname: " + fullname, font));
                document.add(new Paragraph("Gender: " + s.getGender(), font));
                document.add(new Paragraph("Date of Birth: " + s.getDayOfBirth(), font));
                document.add(new Paragraph("Email: " + s.getEmail(), font));
                document.add(new Paragraph("Address: " + s.getAddress(), font));
                document.add(new Paragraph("Class: " + s.getClassId().getName(), font));
                document.add(new Paragraph("Faculty: " + s.getClassId().getFacultyId().getName(), font));
                document.add(new Paragraph("\n"));

                document.add(new Paragraph("Activities and Scores", font));
                document.add(new Paragraph("\n"));
                PdfPTable table = new PdfPTable(3);
                table.addCell(new Paragraph("Activity Name", font));
                table.addCell(new Paragraph("Registration Date", font));
                table.addCell(new Paragraph("Score", font));

                for (Object[] activityEntry : activities) {
                    Activity activity = (Activity) activityEntry[0];
                    JoinActivity joinActivity = (JoinActivity) activityEntry[3];

                    table.addCell(new Paragraph(activity.getName(), font));
                    table.addCell(new SimpleDateFormat("yyyy-MM-dd").format(joinActivity.getDateRegister()));

                    int totalScoreForActivity = 0;
                    for (Object[] scoreEntry : scores) {
                        JoinActivity scoreJoinActivity = (JoinActivity) scoreEntry[0];
                        Score score = (Score) scoreEntry[1];

                        if (joinActivity.getId() == scoreJoinActivity.getId()) {
                            totalScoreForActivity += score.getScoreValue();
                        }
                    }
                    table.addCell(new Paragraph(String.valueOf(totalScoreForActivity), font));
                }

                document.add(table);

                document.close();

                ByteArrayInputStream bis = new ByteArrayInputStream(out.toByteArray());

                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Disposition", "inline; filename=student_report.pdf");
                headers.setContentType(MediaType.APPLICATION_PDF);

                return ResponseEntity
                        .ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(new InputStreamResource(bis));
            } catch (DocumentException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).build();
            }
        } catch (NumberFormatException | NullPointerException e) {

            String yearStudy = params.get("yearStudy");
            List<Object[]> activities = this.activityService.getActivitiesJoined(u.getId(), 0, yearStudy);
            List<Object[]> scores = this.scoreService.getScoresWithTerm(u.getId(), 0, yearStudy);

            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                Document document = new Document();
                PdfWriter.getInstance(document, out);

                BaseFont baseFont = BaseFont.createFont("fonts/DejaVuSans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font font = new Font(baseFont, 12);

                document.open();

                String fullname = s.getLastname() + s.getFirstname();

                document.add(new Paragraph("Student Information", font));
                document.add(new Paragraph("Fullname: " + fullname, font));
                document.add(new Paragraph("Gender: " + s.getGender(), font));
                document.add(new Paragraph("Date of Birth: " + s.getDayOfBirth(), font));
                document.add(new Paragraph("Email: " + s.getEmail(), font));
                document.add(new Paragraph("Address: " + s.getAddress(), font));
                document.add(new Paragraph("Class: " + s.getClassId().getName(), font));
                document.add(new Paragraph("Faculty: " + s.getClassId().getFacultyId().getName(), font));
                document.add(new Paragraph("\n"));

                document.add(new Paragraph("Activities and Scores", font));
                document.add(new Paragraph("\n"));
                PdfPTable table = new PdfPTable(3);
                table.addCell(new Paragraph("Activity Name", font));
                table.addCell(new Paragraph("Registration Date", font));
                table.addCell(new Paragraph("Score", font));

                for (Object[] activityEntry : activities) {
                    Activity activity = (Activity) activityEntry[0];
                    JoinActivity joinActivity = (JoinActivity) activityEntry[3];

                    table.addCell(new Paragraph(activity.getName(), font));
                    table.addCell(new SimpleDateFormat("yyyy-MM-dd").format(joinActivity.getDateRegister()));

                    int totalScoreForActivity = 0;
                    for (Object[] scoreEntry : scores) {
                        JoinActivity scoreJoinActivity = (JoinActivity) scoreEntry[0];
                        Score score = (Score) scoreEntry[1];

                        if (joinActivity.getId() == scoreJoinActivity.getId()) {
                            totalScoreForActivity += score.getScoreValue();
                        }
                    }
                    table.addCell(new Paragraph(String.valueOf(totalScoreForActivity), font));
                }

                document.add(table);

                document.close();

                ByteArrayInputStream bis = new ByteArrayInputStream(out.toByteArray());

                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Disposition", "inline; filename=student_report.pdf");
                headers.setContentType(MediaType.APPLICATION_PDF);

                return ResponseEntity
                        .ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(new InputStreamResource(bis));
            } catch (DocumentException err) {
                e.printStackTrace();
                return ResponseEntity.status(500).build();
            }
        }

    }

    @GetMapping(value = "/csv/{studentId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> generateCsvReport(@PathVariable int studentId,
            @RequestParam Map<String, String> params) throws IOException {

        Integer semesterId = null;
        Student s = this.studentService.findById(studentId);
        User u = this.userService.findByStudentId(studentId);

        try {
            semesterId = Integer.parseInt(params.get("semesterId"));
            Semester semester = this.semesterService.findById(semesterId);
            String yearStudy = semester.getYearStudy();
            List<Object[]> activities = this.activityService.getActivitiesJoined(u.getId(), semesterId, yearStudy);
            List<Object[]> scores = this.scoreService.getScoresWithTerm(u.getId(), semesterId, yearStudy);

            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);

                CSVPrinter csvPrinter = new CSVPrinter(osw, CSVFormat.DEFAULT.withHeader(
                        "Student Information", "", "",
                        "Fullname", "Gender", "Date of Birth", "Email", "Address", "Class", "Faculty",
                        "", "Activities and Scores", "", "",
                        "Activity Name", "Registration Date", "Score"));

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                // Student Information
                csvPrinter.printRecord("Student Information");
                csvPrinter.printRecord("Fullname:", s.getLastname() + s.getFirstname());
                csvPrinter.printRecord("Gender:", s.getGender());
                csvPrinter.printRecord("Date of Birth:", s.getDayOfBirth());
                csvPrinter.printRecord("Email:", s.getEmail());
                csvPrinter.printRecord("Address:", s.getAddress());
                csvPrinter.printRecord("Class:", s.getClassId().getName());
                csvPrinter.printRecord("Faculty:", s.getClassId().getFacultyId().getName());

                // Activities and Scores
                csvPrinter.printRecord("");
                csvPrinter.printRecord("Activities and Scores");
                csvPrinter.printRecord("Activity Name", "Registration Date", "Score");

                for (Object[] activityEntry : activities) {
                    Activity activity = (Activity) activityEntry[0];
                    JoinActivity joinActivity = (JoinActivity) activityEntry[3];

                    int totalScoreForActivity = 0;
                    for (Object[] scoreEntry : scores) {
                        JoinActivity scoreJoinActivity = (JoinActivity) scoreEntry[0];
                        Score score = (Score) scoreEntry[1];

                        if (joinActivity.getId() == scoreJoinActivity.getId()) {
                            totalScoreForActivity += score.getScoreValue();
                        }
                    }
                    csvPrinter.printRecord(activity.getName(), dateFormat.format(joinActivity.getDateRegister()), totalScoreForActivity);
                }

                csvPrinter.flush();
                csvPrinter.close();

                ByteArrayResource resource = new ByteArrayResource(out.toByteArray());

                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=student_report.csv");

                return ResponseEntity.ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (NumberFormatException | NullPointerException e) {

            String yearStudy = params.get("yearStudy");
            List<Object[]> activities = this.activityService.getActivitiesJoined(u.getId(), 0, yearStudy);
            List<Object[]> scores = this.scoreService.getScoresWithTerm(u.getId(), 0, yearStudy);

            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);

                CSVPrinter csvPrinter = new CSVPrinter(osw, CSVFormat.DEFAULT.withHeader(
                        "Student Information", "", "",
                        "Fullname", "Gender", "Date of Birth", "Email", "Address", "Class", "Faculty",
                        "", "Activities and Scores", "", "",
                        "Activity Name", "Registration Date", "Score"));

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                // Student Information
                csvPrinter.printRecord("Student Information");
                csvPrinter.printRecord("Fullname:", s.getLastname() + s.getFirstname());
                csvPrinter.printRecord("Gender:", s.getGender());
                csvPrinter.printRecord("Date of Birth:", s.getDayOfBirth());
                csvPrinter.printRecord("Email:", s.getEmail());
                csvPrinter.printRecord("Address:", s.getAddress());
                csvPrinter.printRecord("Class:", s.getClassId().getName());
                csvPrinter.printRecord("Faculty:", s.getClassId().getFacultyId().getName());

                // Activities and Scores
                csvPrinter.printRecord("");
                csvPrinter.printRecord("Activities and Scores");
                csvPrinter.printRecord("Activity Name", "Registration Date", "Score");

                for (Object[] activityEntry : activities) {
                    Activity activity = (Activity) activityEntry[0];
                    JoinActivity joinActivity = (JoinActivity) activityEntry[3];

                    int totalScoreForActivity = 0;
                    for (Object[] scoreEntry : scores) {
                        JoinActivity scoreJoinActivity = (JoinActivity) scoreEntry[0];
                        Score score = (Score) scoreEntry[1];

                        if (joinActivity.getId() == scoreJoinActivity.getId()) {
                            totalScoreForActivity += score.getScoreValue();
                        }
                    }
                    csvPrinter.printRecord(activity.getName(), dateFormat.format(joinActivity.getDateRegister()), totalScoreForActivity);
                }

                csvPrinter.flush();
                csvPrinter.close();

                ByteArrayResource resource = new ByteArrayResource(out.toByteArray());

                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=student_report.csv");

                return ResponseEntity.ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);
            } catch (IOException err) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

}
