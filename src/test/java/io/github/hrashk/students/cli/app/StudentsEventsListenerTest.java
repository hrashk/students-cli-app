package io.github.hrashk.students.cli.app;

import org.jline.terminal.Terminal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Injecting just the slice we need for testing.
 */
@ExtendWith(SpringExtension.class)
@Import({StudentsList.class, StudentsEventsListener.class})
class StudentsEventsListenerTest {

    @Autowired
    private StudentsList studentsList;

    @MockBean
    private Terminal terminal;

    private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    @BeforeEach
    void setUpTerminal() {
        PrintWriter writer = new PrintWriter(buffer);
        Mockito.when(terminal.writer()).thenReturn(writer);
    }

    @Test
    void addingStudentRaisesEvent() {
        studentsList.add("holy", "moly", 17);
        assertThat(buffer.toString()).contains("was added");
    }

    @Test
    void removingStudentRaisesEvent() {
        studentsList.add("sister", "poly", 23);
        studentsList.removeById(1);
        assertThat(buffer.toString()).contains("was removed");
    }

    @Test
    void clearingStudentRaisesMultipleEvents() {
        studentsList.add("sister", "poly", 23);
        studentsList.add("father", "james", 47);
        // the spring context is shared between tests, so we may get more than two students in the list
        int expected = studentsList.size();

        studentsList.clear();
        int actual = StringUtils.countOccurrencesOf(buffer.toString(), "was removed");

        assertThat(actual)
                .as("Number of students removed")
                .isEqualTo(expected);
    }
}
