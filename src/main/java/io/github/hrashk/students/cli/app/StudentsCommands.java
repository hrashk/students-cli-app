package io.github.hrashk.students.cli.app;

import org.springframework.context.annotation.Bean;
import org.springframework.shell.Availability;
import org.springframework.shell.AvailabilityProvider;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;

import java.util.stream.Collectors;

@Command(group = "Students App")
public class StudentsCommands {

    public static final String NO_STUDENTS = "there are no students in the system";
    public static final String NEGATIVE_AGE = "Age must be positive";
    private final StudentsList studentsList;

    public StudentsCommands(StudentsList studentsList) {
        this.studentsList = studentsList;
    }

    @Command(description = "list all students in the system")
    @CommandAvailability(provider = "studentsAvailability")
    public String show() {
        return studentsList.getAll().stream()
                .map(Student::toString)
                .collect(Collectors.joining("\n"));
    }

    @Bean
    public AvailabilityProvider studentsAvailability() {
        return () -> {
            if (studentsList.isEmpty())
                return Availability.unavailable(NO_STUDENTS);
            else
                return Availability.available();
        };
    }

    @Command(description = "list all students in the system")
    public String add(String firstName, String lastName, int age) {
        if (age <= 0)
            return NEGATIVE_AGE;

        studentsList.add(firstName, lastName, age);
        return "";
    }

    @Command(description = "remove a student by id")
    @CommandAvailability(provider = "studentsAvailability")
    public String remove(int id) {
        return String.format("Student with id %d is removed.", id);
    }

    @Command(description = "delete all students from the system")
    @CommandAvailability(provider = "studentsAvailability")
    public String erase() {
        return "All students removed";
    }
}
