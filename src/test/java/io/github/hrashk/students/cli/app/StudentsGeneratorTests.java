package io.github.hrashk.students.cli.app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * We need an actual application in order to test ApplicationEvents, so we're creating an app with a slice of
 * required beans in plain JUnit tests rather than relying on Spring Boot Test annotations.
 */
class StudentsGeneratorTests {
    @TestConfiguration
    @Import({StudentsList.class, StudentsGenerator.class})
    static class SampleApp {
    }

    @Test
    void listIsEmpty() {
        var ctx = runSampleApp(StudentsMode.EMPTY);

        StudentsList list = ctx.getBean(StudentsList.class);
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    void listIsNotEmpty() {
        var ctx = runSampleApp(StudentsMode.GENERATE);

        StudentsList list = ctx.getBean(StudentsList.class);
        assertThat(list.size()).isGreaterThan(5);
    }

    @AfterAll
    static void resetProperties() {
        System.setProperty("app.students.generate", "false");
    }

    /** Setting system property directly instead of yaml config files to avoid interference with other tests. */
    private static ConfigurableApplicationContext runSampleApp(StudentsMode mode) {
        mode.setSystemProperty();

        SpringApplication application = new SpringApplication(SampleApp.class);
        application.setWebApplicationType(WebApplicationType.NONE);

        return application.run();
    }

    enum StudentsMode {
        EMPTY("false"), GENERATE("true");

        private final String value;

        StudentsMode(String value) {
            this.value = value;
        }

        void setSystemProperty() {
            System.setProperty("app.students.generate", value);
        }
    }
}
