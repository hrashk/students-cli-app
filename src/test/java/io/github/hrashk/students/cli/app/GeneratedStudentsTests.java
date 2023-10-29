package io.github.hrashk.students.cli.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.test.ShellTestClient;
import org.springframework.shell.test.autoconfigure.ShellTest;

@ShellTest(properties = "app.students.generate=true")
class GeneratedStudentsTests extends IntegrationScenario {

    @Autowired
    ShellTestClient client;

    @Test
    void showPrintsSomething() {
        var session = client.interactive().run();

        waitForPrompt(session);

        enterCommand(session, "show");

        waitForText(session, "first name");
    }
}
