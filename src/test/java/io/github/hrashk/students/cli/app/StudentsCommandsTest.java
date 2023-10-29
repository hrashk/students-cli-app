package io.github.hrashk.students.cli.app;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentsCommandsTest {

    @Test
    void showNonEmptyList() {
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
    void addingStudentWithNegativeAgeFails() {
        StudentsList students = TestData.sampleStudentsList();
        int originalSize = students.size();
        var commands = new StudentsCommands(students);

        String output = commands.add("Joanne", "Doe", -13);

        assertThat(students.size()).isEqualTo(originalSize);
        assertEquals(StudentsCommands.NEGATIVE_AGE, output);
    }

    @Test
    void remove() {
    }

    @Test
    void erase() {
    }
}