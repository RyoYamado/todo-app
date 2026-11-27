# Todo App

Java console task list for practical work 2 (GitHub Flow and CI/CD).

## Build and run

Windows: `gradlew.bat clean build`

Linux/macOS: `./gradlew clean build`

Run: `java -jar build/libs/todo-app-0.1.0.jar`

Commands (zero-based index): `add <task>`, `remove <index>`, `list`, `clear`, `done <index>`, `search <text>`, `exit`.

## Automation

- CI: push to main and pull requests, JUnit 5 tests and executable JAR. A successful main push creates a release.
- CD: Maven publication after successful main-push CI; uses the exact commit and version from CI.
- Nightly: daily at 02:00 UTC and manually, tests and build only.
- Versions contain the workflow run ID and attempt to avoid duplicate release tags or Maven versions.
- No runtime dependencies are needed, so the ordinary executable JAR is self-contained.

[Actions](../../actions) | [Releases](../../releases) | [Packages](../../packages)

Test reports: `build/reports/tests/test/index.html`.

## Windows paths containing Cyrillic

If Gradle cannot load the test class, run `powershell -ExecutionPolicy Bypass -File .\build-local.ps1` from the project directory. The script temporarily maps the project to a free drive letter, builds it and removes the mapping. No source files are moved.
