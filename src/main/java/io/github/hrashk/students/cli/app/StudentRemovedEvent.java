package io.github.hrashk.students.cli.app;

public class StudentRemovedEvent {
    private final int studentId;

    public StudentRemovedEvent(int studentId) {
        this.studentId = studentId;
    }

    public int studentId() {
        return studentId;
    }
}
