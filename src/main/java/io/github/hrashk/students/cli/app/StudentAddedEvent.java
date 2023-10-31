package io.github.hrashk.students.cli.app;

public class StudentAddedEvent {
    private final int studentId;

    public StudentAddedEvent(int studentId) {
        this.studentId = studentId;
    }

    public int studentId() {
        return this.studentId;
    }
}
