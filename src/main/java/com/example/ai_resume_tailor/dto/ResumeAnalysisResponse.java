package com.example.ai_resume_tailor.dto;
import java.util.List;

public class ResumeAnalysisResponse {

    private int atsScore;
    private List<String> missingSkills;
    private List<String> suggestions;
    private String improvedResume;

    public int getAtsScore() {
        return atsScore;
    }

    public void setAtsScore(int atsScore) {
        this.atsScore = atsScore;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }

    public String getImprovedResume() {
        return improvedResume;
    }

    public void setImprovedResume(String improvedResume) {
        this.improvedResume = improvedResume;
    }

}
