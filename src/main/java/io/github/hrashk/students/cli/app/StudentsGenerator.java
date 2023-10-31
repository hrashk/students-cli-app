package io.github.hrashk.students.cli.app;

import net.datafaker.Faker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationStartedEvent;
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

    /**
     * Strangely, the Ready event is fired only after exiting the shell when running from the command line.
     * The Started event is fired before the shell prompt is shown, so weare relying on it here.
     * Even more surprisingly, the integration test passes with either of the events...
     */
    @EventListener(ApplicationStartedEvent.class)
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
