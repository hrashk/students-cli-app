package io.github.hrashk.students.cli.app;

public record Student(int id, String firstName, String lastName, int age) {
    @Override
    public String toString() {
        return "%3d | %-10s | %-9s | %d".formatted(id, firstName, lastName, age);
    }
}
