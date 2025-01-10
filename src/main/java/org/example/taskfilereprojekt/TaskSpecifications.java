package org.example.taskfilereprojekt;
import org.example.taskfilereprojekt.entity.Tasks;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class TaskSpecifications {
    public static Specification<Tasks> hasTitle(String search) {
        return (root, query, criteriaBuilder) -> search == null || search.isEmpty()
                ? null
                : criteriaBuilder.like(root.get("title"), "%" + search + "%");
    }

    public static Specification<Tasks> hasUserId(Integer userId) {
        return (root, query, criteriaBuilder) -> userId == null
                ? null
                : criteriaBuilder.equal(root.get("user").get("id"), userId);
    }

    public static Specification<Tasks> hasStatus(Boolean status) {
        return (root, query, criteriaBuilder) -> status == null
                ? null
                : criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Tasks> hasDate(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> {
            LocalDateTime startOfDay = date.toLocalDate().atStartOfDay();
            LocalDateTime endOfDay = date.toLocalDate().atTime(23, 59, 59);
            return criteriaBuilder.between(root.get("date"), startOfDay, endOfDay);
        };
    }

}
