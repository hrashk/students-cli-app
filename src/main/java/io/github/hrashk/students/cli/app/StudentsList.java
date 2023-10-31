package io.github.hrashk.students.cli.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class StudentsList {
    private final List<Student> students = new ArrayList<>();
    private ApplicationEventPublisher publisher;

    @Autowired
    void setPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

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
        publisher.publishEvent(new StudentAddedEvent(id));
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
        if (students.removeIf(s -> s.id() == studentId)) {
            publisher.publishEvent(new StudentRemovedEvent(studentId));
        }
    }

    public void clear() {
        List<Integer> ids = students.stream().map(Student::id).toList();

        students.clear();

        ids.forEach(id -> publisher.publishEvent(new StudentRemovedEvent(id)));
    }
}
