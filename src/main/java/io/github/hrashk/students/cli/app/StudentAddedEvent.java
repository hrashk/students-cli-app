package io.github.hrashk.students.cli.app;

public class StudentAddedEvent {
    private final Student student;

    public StudentAddedEvent(Student student) {
        this.student = student;
    }

    public Student student() {
        return this.student;
    }
}
