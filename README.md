Do wygenerowania polaczonch za soba raportow ze wszyskich projektow:
mvn clean site site:stage site:deploy 

Do odpalenia GUI:
mvn clean install
mvn -pl View compile
mvn -pl View javafx:run lub javafx:jlink   <====== do ogarniecia 

TODO z 8:
-Dao do osobnego projektu
-FileSudokuBoardDao jeden fileStream
-Dao wrzucic w t-r-w
