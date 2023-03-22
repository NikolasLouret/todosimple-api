package com.nikolaslouret.todosimple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nikolaslouret.todosimple.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    //! Query com JPQL
    // @Query(value = "SELECT task FROM Task task WHERE task.user.id = :id")
    // List<Task> findByUser_Id(@Param("id") Long id);

    //! Query SQL nativa
    // @Query(value = "SELECT * FROM task t WHERE t.user_id = :id", nativeQuery = true)
    // List<Task> findByUser_Id(@Param("id") Long id);
    
    List<Task> findByUser_Id(Long id);
}
