package io.github.hrashk.students.cli.app;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class StudentsList {
    private final List<String> students = new ArrayList<>();

    public Collection<String> getAll() {
        return Collections.unmodifiableList(students);
    }

    public int size() {
        return students.size();
    }
}
