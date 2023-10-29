package io.github.hrashk.students.cli.app;

import net.datafaker.Faker;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@ConditionalOnProperty("app.students.generate")
public class StudentsGenerator implements InitializingBean {
    private final StudentsList studentsList;

    public StudentsGenerator(StudentsList studentsList) {
        this.studentsList = studentsList;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        var random = ThreadLocalRandom.current();
        Faker f = new Faker(random);

        int numberOfStudents = random.nextInt(7, 13);

        for (int idx = 0; idx < numberOfStudents; idx++) {
            int age = random.nextInt(13, 33);
            studentsList.add(f.name().firstName(), f.name().lastName(), age);
        }
    }
}
