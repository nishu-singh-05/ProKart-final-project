package com.project.emailservice.repository;


import com.project.emailservice.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Ticket, Integer> {
}