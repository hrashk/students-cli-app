package io.github.hrashk.students.cli.app;

import jakarta.validation.constraints.Min;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.Availability;
import org.springframework.shell.AvailabilityProvider;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;

import java.util.stream.Collectors;

@Command(group = "Students App")
public class StudentsCommands {

    public static final String NO_STUDENTS = "there are no students in the system";
    public static final String NOT_FOUND = "Student not found";
    private final StudentsList studentsList;

    public StudentsCommands(StudentsList studentsList) {
        this.studentsList = studentsList;
    }

    @Command(description = "list all students in the system")
    @CommandAvailability(provider = "studentsAvailability")
    public String show() {
        String header = """
                 id | first name | last name | age
                ----------------------------------
                """;
        return header + studentsList.getAll().stream()
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
    public void add(String firstName, String lastName,
                    @Min(value = 1, message = "Age must be positive") int age) {
        studentsList.add(firstName, lastName, age);
    }

    @Command(description = "remove a student by id")
    @CommandAvailability(provider = "studentsAvailability")
    public String remove(int studentId) {
        if (studentsList.contains(studentId)) {
            studentsList.removeById(studentId);
            return "";
        } else
            return NOT_FOUND;
    }

    @Command(description = "delete all students from the system")
    @CommandAvailability(provider = "studentsAvailability")
    public String erase() {
        studentsList.clear();
        return "All students removed";
    }
}
