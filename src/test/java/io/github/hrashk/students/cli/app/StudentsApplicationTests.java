package io.github.hrashk.students.cli.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.command.annotation.EnableCommand;
import org.springframework.shell.test.ShellAssertions;
import org.springframework.shell.test.ShellScreenAssert;
import org.springframework.shell.test.ShellTestClient;
import org.springframework.shell.test.autoconfigure.ShellTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.time.Duration;
import java.util.stream.Stream;

import static org.awaitility.Awaitility.await;

@ShellTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
// the next two annotations are an ugly hack to make the integration tests work...
@ContextConfiguration(classes = {StudentsApplicationTests.TestConfig.class})
@EnableCommand(StudentsCommands.class)
class StudentsApplicationTests {

    @Autowired
    ShellTestClient client;

    @Test
    void help() {
        ShellTestClient.InteractiveShellSession session = client
                .interactive()
                .run();

        await().atMost(Duration.ofSeconds(2)).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText("shell");
        });

        session.write(session.writeSequence().text("help").carriageReturn().build());

        await().atMost(Duration.ofSeconds(2)).untilAsserted(() -> {
            ShellScreenAssert assertion = ShellAssertions.assertThat(session.screen());
            Stream.of("Students App", "show:")
                    .forEach(assertion::containsText);
        });
    }

    @Configuration
    @ComponentScan
    public static class TestConfig {
    }
}
