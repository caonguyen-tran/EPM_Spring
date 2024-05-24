/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Win11
 */
public class PDFGenerator {
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

    public static ByteArrayInputStream generatePdfReport(List<StudentReportDTO> reports) throws IOException {
        

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            BaseFont baseFont = BaseFont.createFont("fonts/DejaVuSans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(baseFont, 12);

            Paragraph title = new Paragraph("Student Report", font);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" ", font));

            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            String[] headers = {"Fullname", "Semester", "Study Year", "Activity", "Term", "Score"};
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Paragraph(header, font));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            int totalScore = 0;
            for (StudentReportDTO report : reports) {
                String fullname = report.getStudent().getLastname() + " " + report.getStudent().getFirstname();
                table.addCell(new Paragraph(fullname, font));
                table.addCell(new Paragraph(report.getClasses().getName(), font));
                table.addCell(new Paragraph(report.getSemester().getName(), font));
                table.addCell(new Paragraph(report.getSemester().getYearStudy(), font));
                table.addCell(new Paragraph(report.getActivity().getName(), font));
                table.addCell(new Paragraph(report.getTerm().getName(), font));
                table.addCell(new Paragraph(String.valueOf(report.getScore().getScoreValue()), font));
                totalScore += report.getScore().getScoreValue();
            }

            PdfPCell totalLabelCell = new PdfPCell(new Paragraph("Total Score", font));
            totalLabelCell.setColspan(5);
            totalLabelCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(totalLabelCell);

            PdfPCell totalScoreCell = new PdfPCell(new Paragraph(String.valueOf(totalScore), font));
            totalScoreCell.setColspan(2);
            totalScoreCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(totalScoreCell);
            
            PdfPCell rankingLabelCell = new PdfPCell(new Paragraph("Ranking", font));
            totalLabelCell.setColspan(5);
            totalLabelCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(rankingLabelCell);
            
            String ranking = determineRanking(totalScore);
            
            PdfPCell rankingValueCell = new PdfPCell(new Paragraph(ranking, font));
            totalScoreCell.setColspan(2);
            totalScoreCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(rankingValueCell);

            document.add(table);
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}
