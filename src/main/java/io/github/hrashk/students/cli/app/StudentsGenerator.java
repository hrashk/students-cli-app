package io.github.hrashk.students.cli.app;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty("app.students.generate")
public class StudentsGenerator implements InitializingBean {
    private final StudentsList studentsList;

    public StudentsGenerator(StudentsList studentsList) {
        this.studentsList = studentsList;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        studentsList.add("Holy", "Moly", 13);
    }
}
