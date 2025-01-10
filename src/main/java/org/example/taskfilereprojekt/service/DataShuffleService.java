package org.example.taskfilereprojekt.service;

import org.example.taskfilereprojekt.entity.Tasks;
import org.example.taskfilereprojekt.repo.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DataShuffleService {

    @Autowired
    private TasksRepository tasksRepository;

    public void shuffleTasks() {
        // Barcha ma'lumotlarni o'qing
        List<Tasks> tasks = tasksRepository.findAll();

        // Ma'lumotlarni chalkashtiring
        Collections.shuffle(tasks);

        // Chalkashtirilgan ma'lumotlarni qaytadan saqlang
        tasksRepository.saveAll(tasks);

        System.out.println("Tasks chalkashtirildi!");
    }
}
