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
        StudentsList students = new StudentsList();
        students.add(new Student(1, "Ivan", "Ivanov", 23));
        students.add(new Student(2, "Maria", "Ivanovna", 31));
        students.add(new Student(3, "Daria", "Petrovna", 29));
        students.add(new Student(4, "Peter", "Petroff", 27));
        var commands = new StudentsCommands(students);
        assertThat(commands.show().split("\\n")).hasSize(4);
    }

    @Test
    void add() {
    }

    @Test
    void remove() {
    }

    @Test
    void erase() {
    }
}