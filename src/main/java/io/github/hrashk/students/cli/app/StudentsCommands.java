package io.github.hrashk.students.cli.app;

import org.springframework.shell.command.annotation.Command;

@Command(group = "Students App")
public class StudentsCommands {

    private final StudentsList studentsList;

    public StudentsCommands(StudentsList studentsList) {
        this.studentsList = studentsList;
    }

    @Command(description = "list all students in the system")
    public String show() {
        return String.format("Showing %d students.", studentsList.size());
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
