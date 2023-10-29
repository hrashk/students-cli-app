package io.github.hrashk.students.cli.app;

import org.springframework.context.annotation.Bean;
import org.springframework.shell.Availability;
import org.springframework.shell.AvailabilityProvider;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;

import java.util.stream.Collectors;

@Command(group = "Students App")
public class StudentsCommands {

    public static final String NO_STUDENTS = "There are no students in the system";
    public static final String NEGATIVE_AGE = "Age must be positive";
    private final StudentsList studentsList;

    public StudentsCommands(StudentsList studentsList) {
        this.studentsList = studentsList;
    }

    @Command(description = "list all students in the system")
    @CommandAvailability(provider = "showAvailability")
    public String show() {
        return studentsList.getAll().stream()
                .map(Student::toString)
                .collect(Collectors.joining("\n"));
    }

    @Bean
    public AvailabilityProvider showAvailability() {
        return () -> {
            if (studentsList.isEmpty())
                return Availability.unavailable(NO_STUDENTS);
            else
                return Availability.available();
        };
    }

    @Command(description = "list all students in the system")
    public void add(String firstName, String lastName, int age) {
        studentsList.add(firstName, lastName, age);
    }

    @Command(description = "remove a student by id")
    public String remove(int id) {
        return String.format("Student with id %d is removed.", id);
    }

    @Command(description = "delete all students from the system")
    public String erase() {
        return "All students removed";
    }
}
