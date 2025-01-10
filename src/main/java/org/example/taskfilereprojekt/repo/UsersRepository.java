package org.example.taskfilereprojekt.repo;

import org.example.taskfilereprojekt.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}