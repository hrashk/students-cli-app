package io.github.hrashk.students.cli.app;

import org.springframework.shell.command.annotation.Command;

import java.util.stream.Collectors;

@Command(group = "Students App")
public class StudentsCommands {

    public static final String NO_STUDENTS = "There are no students in the system";
    private final StudentsList studentsList;

    public StudentsCommands(StudentsList studentsList) {
        this.studentsList = studentsList;
    }

    @Command(description = "list all students in the system")
    public String show() {
        if (studentsList.isEmpty())
            return NO_STUDENTS;
        else
            return studentsList.getAll().stream()
                    .map(Student::toString)
                    .collect(Collectors.joining("\n"));
    }

    @Command(description = "list all students in the system")
    public String add(String firstName, String lastName, int age) {
        return String.format("Student %s added.", firstName);
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
