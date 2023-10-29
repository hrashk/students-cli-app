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

/**
 * The last two annotations are a hack to make the integration tests work.
 * Even they do not allow testing the availability of the commands, which is left for the manual testing.
 */
@ShellTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {StudentsApplicationTests.TestConfig.class})
@EnableCommand(StudentsCommands.class)
class StudentsApplicationTests {

    private static final Duration TIMEOUT = Duration.ofSeconds(2);

    @Autowired
    ShellTestClient client;

    @Test
    void help() {
        ShellTestClient.InteractiveShellSession session = client
                .interactive()
                .run();

        waitForPrompt(session);

        enterCommand(session, "help");

        waitForStudentsAppCommands(session);
    }

    private static void waitForPrompt(ShellTestClient.InteractiveShellSession session) {
        await().atMost(TIMEOUT).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText("shell");
        });
    }

    private static void enterCommand(ShellTestClient.InteractiveShellSession session, String command) {
        session.write(session.writeSequence().text(command).carriageReturn().build());
    }

    private static void waitForStudentsAppCommands(ShellTestClient.InteractiveShellSession session) {
        await().atMost(TIMEOUT).untilAsserted(() -> {
            ShellScreenAssert assertion = ShellAssertions.assertThat(session.screen());
            Stream.of("Students App", "show:", "add:", "remove:", "erase:")
                    .forEach(assertion::containsText);
        });
    }

    @Configuration
    @ComponentScan
    public static class TestConfig {
    }
}
