/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 *
 * @author Win11
 */
public class CSVGenerator {

    public static String determineRanking(int totalScore) {
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

    public static ByteArrayInputStream generateCsvReport(List<StudentReportDTO> reports) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(null);

        try ( ByteArrayOutputStream out = new ByteArrayOutputStream();  OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);  CSVPrinter csvPrinter = new CSVPrinter(writer, format)) {

            writer.write('\ufeff');

            csvPrinter.printRecord("Fullname", "Class", "Semester", "Study Year", "Activity", "Score");

            int totalScore = 0;

            for (StudentReportDTO report : reports) {
                String fullname = report.getStudent().getLastname() + " " + report.getStudent().getFirstname();
                csvPrinter.printRecord(fullname, report.getClasses().getName(), report.getSemester().getName(), report.getSemester().getYearStudy(), report.getActivity().getName(),
                        report.getScore().getScoreValue());
                totalScore += report.getScore().getScoreValue();
            }

            csvPrinter.printRecord("Total Score", "", "", "", "", totalScore);
            csvPrinter.printRecord("Ranking", "", "", "", "", determineRanking(totalScore));

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate CSV report", e);
        }
    }
}
