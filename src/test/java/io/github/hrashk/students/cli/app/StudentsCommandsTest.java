package io.github.hrashk.students.cli.app;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentsCommandsTest {

    @Test
    void showNonEmptyList() {
        StudentsList students = TestData.sampleStudentsList();
        var commands = new StudentsCommands(students);
        assertThat(commands.show().split("\\n")).hasSize(6);
    }

    @Test
    void add() {
        StudentsList students = TestData.sampleStudentsList();
        int originalSize = students.size();
        var commands = new StudentsCommands(students);

        commands.add("Joanne", "Doe", 13);

        assertThat(students.size()).isEqualTo(originalSize + 1);
        assertStudentIdsAreUnique(students);
    }

    private static void assertStudentIdsAreUnique(StudentsList students) {
        assertThat(students.getAll().stream().map(Student::id).toList()).doesNotHaveDuplicates();
    }

    @Test
    void addingStudentWithNegativeAgeFails() {
        StudentsList students = TestData.sampleStudentsList();
        int originalSize = students.size();
        var commands = new StudentsCommands(students);

        String output = commands.add("Joanne", "Doe", -13);

        assertEquals(StudentsCommands.NEGATIVE_AGE, output);
        assertThat(students.size()).isEqualTo(originalSize);
    }

    @Test
    void addingStudentAfterRemovalGetsUniqueId() {
        StudentsList students = TestData.sampleStudentsList();
        int originalSize = students.size();
        var commands = new StudentsCommands(students);

        commands.remove(2);

        assertThat(students.size()).isEqualTo(originalSize - 1);
        assertStudentIdsAreUnique(students);

        commands.add("Joanne", "Doe", 13);
        assertStudentIdsAreUnique(students);
    }

    @Test
    void removingInvalidIdFails() {
        StudentsList students = TestData.sampleStudentsList();
        int originalSize = students.size();
        var commands = new StudentsCommands(students);

        String output = commands.remove(33);

        assertEquals(StudentsCommands.NOT_FOUND, output);
        assertThat(students.size()).isEqualTo(originalSize);
    }

    @Test
    void eraseNonEmptyList() {
        StudentsList students = TestData.sampleStudentsList();
        var commands = new StudentsCommands(students);

        commands.erase();

        assertThat(students.getAll()).isEmpty();
    }
}