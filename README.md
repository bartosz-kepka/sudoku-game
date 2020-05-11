Do wygenerowania polaczonch za soba poprawnych raportow ze wszyskich projektow:
mvn clean test site site:deploy
Nadrzedny raport w target/deploy

Do odpalenia GUI:
mvn clean install
mvn -pl View javafx:run

Logi:
cat /tmp/Sudoku.logs
cat /tmp/Sudoku.logs | grep 'INFO\|SEVERE'