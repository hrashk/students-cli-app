package io.github.hrashk.students.cli.app;

import net.datafaker.Faker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@ConditionalOnProperty("app.students.generate")
public class StudentsGenerator {
    private final StudentsList studentsList;

    public StudentsGenerator(StudentsList studentsList) {
        this.studentsList = studentsList;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void generateRandomStudents() {
        var random = ThreadLocalRandom.current();
        Faker f = new Faker(random);

        int numberOfStudents = random.nextInt(7, 13);

        for (int idx = 0; idx < numberOfStudents; idx++) {
            int age = random.nextInt(13, 33);
            studentsList.add(f.name().firstName(), f.name().lastName(), age);
        }
    }
}
