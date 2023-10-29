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
        int id = lastStudentId() + 1;
        students.add(new Student(id, firstName, lastName, age));
    }

    private int lastStudentId() {
        if (students.isEmpty())
            return 0;
        else
            return students.get(students.size() - 1).id();
    }

    public boolean contains(int studentId) {
        return students.stream().anyMatch(s -> s.id() == studentId);
    }

    public void removeById(int studentId) {
        students.removeIf(s -> s.id() == studentId);
    }
}
