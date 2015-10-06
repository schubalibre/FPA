Der GIT Spicker
===============
Alles noch sehr viel detaillierter nachzulesen auf https://git-scm.com/book/de/v1/


| git code        | Erklaerung |
| ------------- |:-------------:|
| $ git config --global user.name "John Doe"            | Git verwendet diese Information für jeden Commit, den Du anlegst, und sie ist unveränderlich in Deine Commits eingebaut! Damit sehen alle wer den Commit gemacht hat. --global steht für ALLE Projekt, ohne global gilt der Name nur für das Projekt. |
| $ git config --global user.email johndoe@example.com  |       |
| $ git init                                            | Erzeugt ein Unterverzeichnis .git, in dem alle relevanten Git Repository Daten enthalten sind. |
| $ git status                                          | Es zeigt euch die aktuelle Branch an, auf der ihr seid undden Zustand des Repository |
| $ git add main.java / .                               | Um eine neue Datei zur Versionskontrolle hinzuzufügen, verwendest Du den Befehl git add.|
| $ git commit -m "Story 182: Fix benchmarks for speed" | Legt ein Commit an, mit den Dateien zu dem Zeitpunkt an dem du sie geadded hast!!!!!
-m “text” ist eine schnell Version für die Commit Nachricht |
| $ git rm readme.txt                                   | löscht Datei aus dem Repository und von der Festplatte |
| $ git rm --cached readme.txt                          | löscht Datei nur aus dem Repository ( kann danach wieder neu geadded werden) |
| $ git log                                             | welche Änderungen wurden zuletzt vorgenommen (aktuellster commit steht oben) |
| $ gitk                                                | grafische Anzeige der Commit Historie |
| $ git commit --amend                                  | Wenn Du den letzten Commit korrigieren willst $ git commit -m 'initial commit' $ git add forgotten_file $ git commit --amend Diese drei Befehle legen einen einzigen neuen Commit an – der letzte Befehl ersetzt dabei das Ergebnis des ersten Befehls. |
| $ git reset HEAD file                                 | geaddete Dateien wieder aus dem Stage nehmen |
| $ git checkout -- benchmarks.rb                       | um die Änderungen an der Datei zu verwerfen |                          
