# TerMaCare

Das Projekt muss mit Gradle gebaut und ausgeführt werden.
Gradle muss nicht lokal installiert sein sondern wird im Projekt mitgeliefert.

Gradle wird von einer Kommandozeile ausgeführt, bei der das aktuelle Verzeichniss das Projektverzeichnis sein muss.

Für folgende Aufgaben müssen die entsprechenden Befehle ausgeführt werden:

Ziel              | Windows           | Linux
----------------- | ----------------- | -------------------
class-Dateien     | gradlew build     | ./gradlew build
Anwendung starten | gradlew run       | ./gradlew run
Jar bauen         | gradlew shadowJar | ./gradlew shadowJar

Die fertigen class Dateien befinden sich in build/classes.

Die fertige jar Datei befindet sich in build/libs.
Die jar-Datei, die auf -all.jar endet, ist die korrekte.

Um das Projekt mit einer IDE zu kompilieren, wird das Lombok-Plugin benötigt.