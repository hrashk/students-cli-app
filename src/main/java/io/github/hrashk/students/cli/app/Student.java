package io.github.hrashk.students.cli.app;

import lombok.experimental.Accessors;

@lombok.Value
@Accessors(fluent = true)
public class Student {
    int id;
    String firstName;
    String lastName;
    int age;

    @Override
    public String toString() {
        return "%3d | %-10s | %-9s | %d".formatted(id, firstName, lastName, age);
    }
}
