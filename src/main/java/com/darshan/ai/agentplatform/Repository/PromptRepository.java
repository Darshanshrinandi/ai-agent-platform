package com.darshan.ai.agentplatform.Repository;

import com.darshan.ai.agentplatform.Entity.Project;
import com.darshan.ai.agentplatform.Entity.Prompt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromptRepository extends JpaRepository<Prompt,Long> {

    List<Prompt> findByProjectId(Long projectId);
}
