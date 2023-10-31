package io.github.hrashk.students.cli.app;

public class TestData {
    static StudentsList sampleStudentsList() {
        StudentsList students = new StudentsList();
        students.setPublisher(event -> {});
        addSampleData(students);
        return students;
    }

    static void addSampleData(StudentsList students) {
        students.add("Ivan", "Ivanov", 23);
        students.add("Maria", "Ivanovna", 31);
        students.add("Daria", "Petrovna", 29);
        students.add("Peter", "Petroff", 27);
    }
}
