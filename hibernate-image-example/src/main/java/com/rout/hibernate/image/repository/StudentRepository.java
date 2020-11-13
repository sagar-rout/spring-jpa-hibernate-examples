package com.rout.hibernate.image.repository;

import com.rout.hibernate.image.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
