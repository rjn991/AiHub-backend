package com.arise.aihub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.web.bind.annotation.RestController;

@Entity
public class AiTool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long toolId;

    private String toolName;
    private String ToolUrl;
    private String ToolImgUrl;

    public Long getToolId() {
        return toolId;
    }

    public void setToolId(Long toolId) {
        this.toolId = toolId;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getToolUrl() {
        return ToolUrl;
    }

    public void setToolUrl(String toolUrl) {
        ToolUrl = toolUrl;
    }

    public String getToolImgUrl() {
        return ToolImgUrl;
    }

    public void setToolImgUrl(String toolImgUrl) {
        ToolImgUrl = toolImgUrl;
    }

    @Override
    public String toString() {
        return "AiTool{" +
                "toolId=" + toolId +
                ", toolName='" + toolName + '\'' +
                ", ToolUrl='" + ToolUrl + '\'' +
                ", ToolImgUrl='" + ToolImgUrl + '\'' +
                '}';
    }
}
