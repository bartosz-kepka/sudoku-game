Do wygenerowania polaczonch za soba poprawnych raportow ze wszyskich projektow:
mvn clean site site:stage site:deploy 
Nadrzedny raport w target/reports

Do odpalenia GUI:
mvn clean install
mvn -pl View compile
mvn -pl View javafx:run lub javafx:jlink   <=== do ogarniecia 

TODO z 8:
-FileSudokuBoardDao jeden fileStream input i output jako atrybut <=== tylko jak xd 
