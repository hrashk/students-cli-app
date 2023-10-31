## Building the app

```bash
mvn clean package
```
will compile the app, run the unit tests and produce an uber jar in the target folder.

## Running from the uber-jar

```bash
java -jar target/students-cli-app-0.0.1-SNAPSHOT.jar
```
will run the app with empty list of students.

```bash
java -Dapp.students.generate -jar target/students-cli-app-0.0.1-SNAPSHOT.jar
```
will run it with some random students in the system.

## Available commands

The app allows adding and removing students in a list.
Enter the `help` command in the app to see the list of available commands with their short description.
For example, when running with the pre-generated students in the system, you may type the `show` command to see
what students were generated initially.

## Configuration

The app reads its configuration from the `src/main/resources/application.yml` file.
You may override any of the parameters from the command line using the `-D` flag similar to the examples above.

The following configuration parameters govern the behavior of the app

* `app.students.generate` - specifies whether some random students are generated on start-up. It's false by default.
