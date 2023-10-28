package io.github.hrashk.students.cli.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentsListTest {

    @Test
    void studentsGetUniqueIds() {
        StudentsList list = TestData.sampleStudentsList();
        var uniqeIdsCount = list.getAll().stream().map(Student::id).count();
        assertEquals(list.size(), uniqeIdsCount);
    }
}