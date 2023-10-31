package io.github.hrashk.students.cli.app;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.shell.command.annotation.EnableCommand;
import org.springframework.shell.test.ShellAssertions;
import org.springframework.shell.test.ShellScreenAssert;
import org.springframework.shell.test.ShellTestClient;
import org.springframework.shell.test.autoconfigure.ShellTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.time.Duration;
import java.util.stream.Stream;

import static org.awaitility.Awaitility.await;

@ShellTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@EnableCommand(StudentsCommands.class)
@TestPropertySource(properties = "app.students.generate=true")
class StudentsApplicationTests {
    static final Duration TIMEOUT = Duration.ofSeconds(2);

    /** {@code @ShellTest} does not scan for components, so we have to do it explicitly */
    @TestConfiguration
    @ComponentScan
    static class TestConfig {
    }

    @Autowired
    ShellTestClient client;

    @Test
    void help() {
        var session = client.interactive().run();

        waitForPrompt(session);

        enterCommand(session, "help");

        waitForStudentsAppCommands(session);
    }

    @Test
    void show() {
        var session = client.interactive().run();

        waitForPrompt(session);

        enterCommand(session, "show");

        waitForText(session, "first name");
    }

    static void waitForPrompt(ShellTestClient.InteractiveShellSession session) {
        waitForText(session, "shell");
    }

    static void waitForText(ShellTestClient.InteractiveShellSession session, String text) {
        await().atMost(TIMEOUT).untilAsserted(() -> ShellAssertions.assertThat(session.screen())
                .containsText(text));
    }

    static void checkTextDoesNotAppear(ShellTestClient.InteractiveShellSession session, String text) {
        await().during(TIMEOUT)
                .atMost(TIMEOUT.plus(Duration.ofSeconds(1)))
                .untilAsserted(() -> Assertions.assertThat(session.screen().lines())
                        .noneMatch(l -> l.contains(text)));
    }

    static void enterCommand(ShellTestClient.InteractiveShellSession session, String command) {
        session.write(session.writeSequence().text(command).carriageReturn().build());
    }

    static void waitForStudentsAppCommands(ShellTestClient.InteractiveShellSession session) {
        await().atMost(TIMEOUT).untilAsserted(() -> {
            ShellScreenAssert assertion = ShellAssertions.assertThat(session.screen());
            Stream.of("Students App", "show:", "add:", "remove:", "erase:")
                    .forEach(assertion::containsText);
        });
    }
}
