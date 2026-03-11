package com.example.ai_resume_tailor.utils;


import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;

public class PdfTextExtractor {
    public static String extractText(MultipartFile file) throws IOException {

        PDDocument document = PDDocument.load(file.getInputStream());
        PDFTextStripper pdfStripper = new PDFTextStripper();

        String text = pdfStripper.getText(document);
        document.close();

        return text;
    }
}
