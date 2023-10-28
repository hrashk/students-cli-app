package io.github.hrashk.students.cli.app;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class StudentsList {
    private final List<Student> students = new ArrayList<>();

    public Collection<Student> getAll() {
        return Collections.unmodifiableList(students);
    }

    public int size() {
        return students.size();
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }

    public void add(String firstName, String lastName, int age) {
        int id = students.size() + 1;
        students.add(new Student(id, firstName, lastName, age));
    }
}
