package com.rout.hibernate.image;

import com.rout.hibernate.image.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HibernateImageExampleApplicationTests {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void contextLoads() {
        Assertions.assertThat(studentRepository).isNotNull();
    }


}
