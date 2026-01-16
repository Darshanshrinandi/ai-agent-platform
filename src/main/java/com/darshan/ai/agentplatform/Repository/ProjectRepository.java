package com.darshan.ai.agentplatform.Repository;

import com.darshan.ai.agentplatform.Entity.Project;
import com.darshan.ai.agentplatform.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Long> {


    List<Project> findByUserId(Long userId);

    Optional<Project> findByIdAndUserEmail(Long id, String email);

    Optional<Project> findByIdAndUserId(Long id, Long userId);



}
