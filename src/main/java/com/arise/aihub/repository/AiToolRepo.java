package com.arise.aihub.repository;

import com.arise.aihub.model.AiTool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AiToolRepo extends JpaRepository<AiTool,Long> {
}
