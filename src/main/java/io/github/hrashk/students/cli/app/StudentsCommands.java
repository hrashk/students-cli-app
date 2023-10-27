package io.github.hrashk.students.cli.app;

import org.springframework.shell.command.annotation.Command;

@Command(group = "Students App")
public class StudentsCommands {

    private final StudentsList studentsList;

    public StudentsCommands(StudentsList studentsList) {
        this.studentsList = studentsList;
    }

    @Command(description = "holy moly sister poly")
    public String show() {
        return String.format("There are %d students.", studentsList.size());
    }
}
