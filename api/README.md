API Übersicht
=============
Haupt Schnittstellen [ProducerAccess][1] und [WriterAccess][2]. Für WriterAccess 
werden zwei Implementierungen bereitgestellt [FileWriter][3] und [StdOutWriter][4]. 
Producer Paket stellt eine weitere Schnittstelle [Renderer][5] bereit.

![Überblick](src/site/resources/api.png)

## PartialRendererBasedProducer
[PartialRendererBasedProducer][6] arbeitet mit eine Liste von "Renderer" um Codegenerierung zu bewältigen 
(z.B. Renderer für Setter Methoden). Die  Ausgaben von einzelnen "Renderer"
werden dabei zu eine Gesamtausgabe zusammengefügt. Die überschreibende Klasse 
muss lediglich die Methoden transform und subpath bereitstellen.

* ```transform(P parsed) : D``` - Wandelt von Parser kommendes Model in eine 
Producer Model.
* ```subpath(D data) : String``` - Generiert Teilpfad String (wo befindet sich
die Datei) Teilpfad ist dabei relativ.

![Ablauf](src/site/resources/sequence-partial-renderer.png)

## SC – Renderer
Abkürzung sc steht für "string concateneator" was wiederum "die Art zu rendern"
impliziet es handelt sich also um ein Rendering System.

![Überblick](src/site/resources/sc-renderer.png)

##### SCCodeConcatenator und SCMethodCodeCooncatenator
SCCodeConcatenator und SCMethodCodeCooncatenator sind sozusagen "StringBuilder"
die auf Generierung von Programmcode angepasst wurden. 

In wesentlichen vereinfachen diese Klassen Arbeit mit Einrückungen und
Zeilenumbrüchen in bei Generierung von Programmcode außerdem stellen
alle String Erzeugung Methoden eine Variante in Form von 
"String template, Object ... data" da wo man eine Zeichenkette und 
eine Reihe von Daten weiter an String.format übergeben kann.

Methoden für die Programmcode Erzeugung:

```line, indentedLine, annotation, start, code, indentedCode, end, emptyNewLine,
addRaw```

Besondere Methoden sind:

* ```indent() : void``` - Erzeugt eine neu Instance SCCodeConcatenator mit eine 
Einrückung mehr relativ zu erzeugenden Instance.
* ```flush() : String``` - Gibt aktuellen buffer zurück und leert es gleichzeitig.


##### SCNewLineAndIndentationFormat
SCNewLineAndIndentationFormat ist eine Klasse welches die Art und Weise 
regelt wie Programmcode eingerückt wird und welche Zeichenkette als newline
verwendet werden soll.


##### SCRenderer
SCRenderer ist eine Basis Implementierung von Renderer Interface das intern
SCCodeConcatenator und SCMethodCodeCooncatenator nutzt. Um diese Klasse
zu nutzen muss man die Methoden modify() und render() überschreiben
(man kann beide oder nur eine davon überschreiben je nachdem was man gerade
braucht).  Ausserdem stellt diese Klasse die "code writer" (SCCodeConcatenator
und SCMethodCodeCooncatenator) und mit getData() stellt es Zugriff auf die Daten
bereit.

Die von SCRenderer erzeugten Instanzen von SCCodeConcatenator und 
SCMethodCodeCooncatenator nutzen die gleiche Instanz von StringBuilder die 
können also beide gleichzeitig genutzt werden.

Die flush() Methode von SCCodeConcatenator und SCMethodCodeCooncatenator
wird im SCRenderer am Ende der render(D data) Methode aufgerufen und
sollte in abgeleiteten Klassen nicht aufgerufen werden.


##### SCRendererForPropertiesContainer und ProprertiesContainer
SCRendererForPropertiesContainer und ProprertiesContainer stellen eine 
Erweiterung von SCRenderer an und sind speziel für die Models zugeschnitten
welches ein Liste mit Propertiese besitzt. Um dieses Helfer nutzen zu können
muss an den Renderer übergebendes Model PropertiesContainer Interface
implementieren danach kann kann man bei Bedarf die folgenden 3 Methoden
überschreiben

* ```writeCodeBeforPropertiesIteration() : void``` 
* ```writePropertyCode(X property) : void```
* ```writeCodeAfterProprertiesIteration() : void```

In wesentlichen stellt SCRendererForPropertiesContainer eine Iterateion über
Properties Liste da.

[1]: src/main/java/com/github/sergejsamsonow/codegenerator/api/ProducerAccess.java
[2]: src/main/java/com/github/sergejsamsonow/codegenerator/api/WriterAccess.java
[3]: src/main/java/com/github/sergejsamsonow/codegenerator/api/writer/FileWriter.java
[4]: src/main/java/com/github/sergejsamsonow/codegenerator/api/writer/StdOutWriter.java
[5]: src/main/java/com/github/sergejsamsonow/codegenerator/api/producer/Renderer.java
[6]: src/main/java/com/github/sergejsamsonow/codegenerator/api/producer/PartialRendererBasedProducer.java