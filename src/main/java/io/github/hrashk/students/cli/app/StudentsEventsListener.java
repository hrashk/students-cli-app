package io.github.hrashk.students.cli.app;

import org.jline.terminal.Terminal;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StudentsEventsListener {

    private final Terminal terminal;

    public StudentsEventsListener(Terminal terminal) {
        this.terminal = terminal;
    }

    @EventListener
    public void studentAdded(StudentAddedEvent event) {
        terminal.writer().printf("Student %s %s was added%n", event.student().firstName(), event.student().lastName());
        terminal.writer().flush();
    }

    @EventListener
    public void studentRemoved(StudentRemovedEvent event) {
        terminal.writer().printf("Student id %d was removed%n", event.studentId());
        terminal.writer().flush();
    }
}
