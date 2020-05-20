Linux compatible sudoku game with local database connection

Generate site reports (Main report in target/deploy):
mvn clean test site site:deploy

Start GUI:
mvn clean install
mvn -pl View javafx:run

Logs:
cat View/Sudoku.logs
