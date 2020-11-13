package com.rout.hibernate.image;

import com.rout.hibernate.image.entity.Student;
import com.rout.hibernate.image.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryIT {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @Rollback(value = false)
    public void persistImageInDatabase() throws Exception {
        // GIVEN
        final var imageFile = new ClassPathResource("developer_life.jpg").getFile();
        byte[] imageByte = new byte[(int) imageFile.length()];

        final Long id = 1L;
        final var student = new Student()
                .setName("Sagar Rout")
                .setId(id)
                .setProfilePicName(imageFile.getName());

        // try with resource to autoclose any resource
        try (FileInputStream fis = new FileInputStream(imageFile)) {
            fis.read(imageByte);
            student.setProfilePic(imageByte);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        // WHEN
        testEntityManager.persistAndFlush(student);

        // THEN
        final var readStudent = studentRepository.findById(id);
        Assertions.assertThat(readStudent.isPresent()).isEqualTo(true);
        Assertions.assertThat(readStudent.get().getProfilePicName()).isEqualTo("developer_life.jpg");
    }

    @Test
    public void readImageInDatabase() throws Exception {

        // GIVEN
        // Student with this id already in the persist in the above test case
        final Long id = 1L;

        // WHEN
        final var optionalStudent = studentRepository.findById(id);
        final var profilePicArray = optionalStudent.get().getProfilePic();


        final var profilePic = new File("developer_life.jpg");
        try (FileOutputStream fileOutputStream = new FileOutputStream(profilePic)) {
            fileOutputStream.write(profilePicArray);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        // THEN
        Assertions.assertThat(optionalStudent.isPresent()).isTrue();
    }
}
