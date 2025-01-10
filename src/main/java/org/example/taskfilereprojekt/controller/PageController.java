package org.example.taskfilereprojekt.controller;

import org.example.taskfilereprojekt.TaskSpecifications;
import org.example.taskfilereprojekt.entity.Tasks;
import org.example.taskfilereprojekt.entity.Users;
import org.example.taskfilereprojekt.repo.TasksRepository;
import org.example.taskfilereprojekt.repo.UsersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class PageController {

    private final TasksRepository taskRepository;
    private final UsersRepository userRepository;

    public PageController(TasksRepository taskRepository, UsersRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/")
    public String getTasksPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            Model model
    ) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize);


        // Dinamik Specification yaratish
        Specification<Tasks> spec = Specification.where(TaskSpecifications.hasTitle(search))
                .and(TaskSpecifications.hasUserId(userId))
                .and(TaskSpecifications.hasStatus(status))
                .and(TaskSpecifications.hasDate(date));

        // Ma'lumotlarni olish
        Page<Tasks> tasks = taskRepository.findAll(spec, pageable);
        List<Users> users = userRepository.findAll();
        // Paginatsiya parametrlari
        int totalPages = tasks.getTotalPages();
        int currentPage = tasks.getNumber();
        int startPage = Math.max(0, currentPage - 5);
        int endPage = Math.min(totalPages, startPage + 10);

        // Modelga qo'shish
        model.addAttribute("users", users);
        model.addAttribute("tasks", tasks);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("search", search);
        model.addAttribute("userId", userId);
        model.addAttribute("status", status);
        model.addAttribute("date", date != null ? date.toLocalDate() : null);


        return "page";
    }

}
