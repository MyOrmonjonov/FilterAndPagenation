package org.example.taskfilereprojekt.repo;

import org.example.taskfilereprojekt.entity.Tasks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface TasksRepository extends JpaRepository<Tasks, Integer> {
    Page<Tasks> findByTitleContaining(String title, Pageable pageable);
    Page<Tasks> findByUserId(Integer userId, Pageable pageable);
    Page<Tasks> findByStatus(Boolean status, Pageable pageable);
    Page<Tasks> findByDate(LocalDateTime date, Pageable pageable);
    Page<Tasks> findAll(Specification<Tasks> spec, Pageable pageable);

    @Query("SELECT t FROM Tasks t WHERE " +
            "(:search IS NULL OR t.title LIKE %:search%) AND " +
            "(:userId IS NULL OR t.user.id = :userId) AND " +
            "(:status IS NULL OR t.status = :status) AND " +
            "(:date IS NULL OR t.date = :date)")
    Page<Tasks> findFilteredTasks(
            @Param("search") String search,
            @Param("userId") Integer userId,
            @Param("status") Boolean status,
            @Param("date") LocalDateTime date,
            Pageable pageable
    );
}