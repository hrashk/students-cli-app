package io.github.hrashk.students.cli.app;

import org.assertj.core.api.Assertions;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.command.annotation.EnableCommand;
import org.springframework.shell.test.ShellAssertions;
import org.springframework.shell.test.ShellScreenAssert;
import org.springframework.shell.test.ShellTestClient;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.time.Duration;
import java.util.stream.Stream;

import static org.awaitility.Awaitility.await;

/**
 * The last two annotations are a hack to make the integration tests work.
 * Even they do not allow testing the availability of the commands, which is left for the manual testing.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {IntegrationScenario.TestConfig.class})
@EnableCommand(StudentsCommands.class)
class IntegrationScenario {

    static final Duration TIMEOUT = Duration.ofSeconds(2);

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

    @Configuration
    @ComponentScan
    static class TestConfig {
    }
}
