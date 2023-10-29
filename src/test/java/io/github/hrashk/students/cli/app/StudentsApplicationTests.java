package io.github.hrashk.students.cli.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.test.ShellTestClient;
import org.springframework.shell.test.autoconfigure.ShellTest;

@ShellTest
class StudentsApplicationTests extends IntegrationScenario {

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
    void showPrintsNothing() {
        var session = client.interactive().run();

        waitForPrompt(session);

        enterCommand(session, "show");

        checkTextDoesNotAppear(session, "first name");
    }
}
