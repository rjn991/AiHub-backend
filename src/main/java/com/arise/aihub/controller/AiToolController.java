package com.arise.aihub.controller;

import com.arise.aihub.model.AiTool;
import com.arise.aihub.repository.AiToolRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AiToolController {

    private final AiToolRepo aiToolRepo;

    public AiToolController(AiToolRepo aiToolRepo) {
        this.aiToolRepo = aiToolRepo;
    }


    @GetMapping("/")
    public List<AiTool> fetchTool() {
        return aiToolRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<AiTool> fetchToolById(@PathVariable("id") Long id) {
        return aiToolRepo.findById(id);
    }

    @PostMapping("/")
    public AiTool createTool(@RequestBody AiTool newTool) {
        return aiToolRepo.save(newTool);
    }

    @DeleteMapping("/{id}")
    public Optional<AiTool> deleteTool(@PathVariable("id") Long id) {
        Optional<AiTool> data = aiToolRepo.findById(id);
        if(data.isPresent()) {
            aiToolRepo.deleteById(id);
        }
        return data;
     }
}
