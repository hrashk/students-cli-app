package io.github.hrashk.students.cli.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = "app.students.generate=false")
@Import({StudentsList.class, StudentsGenerator.class})
class EmptyStudentsTests {

    @Autowired
    private StudentsList list;

    @Test
    void listIsEmpty() {
        assertThat(list.size()).isEqualTo(0);
    }
}
