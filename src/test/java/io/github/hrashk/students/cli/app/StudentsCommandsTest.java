package io.github.hrashk.students.cli.app;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudentsCommandsTest {

    @Test
    void showEmpty() {
        StudentsList students = new StudentsList();
        var commands = new StudentsCommands(students);
        assertThat(commands.show()).isEqualTo(StudentsCommands.NO_STUDENTS);
    }

    @Test
    void showSome() {
        StudentsList students = TestData.sampleStudentsList();
        var commands = new StudentsCommands(students);
        assertThat(commands.show().split("\\n")).hasSize(4);
    }

    @Test
    void add() {
        StudentsList students = TestData.sampleStudentsList();
        int originalSize = students.size();
        var commands = new StudentsCommands(students);

        commands.add("Joanne", "Doe", 13);

        assertThat(students.size()).isEqualTo(originalSize + 1);
    }

    @Test
    void remove() {
    }

    @Test
    void erase() {
    }
}