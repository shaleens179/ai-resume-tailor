package com.example.ai_resume_tailor.controller;


import com.example.ai_resume_tailor.service.ResumeService;
import com.example.ai_resume_tailor.model.ResumeRequest;
import com.example.ai_resume_tailor.utils.PdfTextExtractor;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ai")
@CrossOrigin(origins = "*")
public class ResumeController {
    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping("/tailor-resume")
    public String tailorResume(@RequestBody ResumeRequest request) throws JsonProcessingException {

        return resumeService.analyzeResume(
                request.getResumeText(),
                request.getJobDescription()
        );
    }

    @PostMapping("/analyze")
    public String analyzeResume(@RequestBody ResumeRequest request) throws JsonProcessingException {
        return resumeService.analyzeResume(
                request.getResumeText(),
                request.getJobDescription()
        );
    }

    @PostMapping("/upload-resume")
    public String uploadResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("jobDescription") String jobDescription) throws Exception {

        String resumeText = PdfTextExtractor.extractText(file);

        return resumeService.analyzeResume(resumeText, jobDescription);
    }
}