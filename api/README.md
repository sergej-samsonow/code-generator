API Übersicht
=============
Haupt Schnittstellen [ProducerAccess][1] und [WriterAccess][2]. Für WriterAccess 
werden zwei Implementierungen bereitgestellt [FileWriter][3] und [StdOutWriter][4]. 
Producer Paket stellt eine weitere Schnittstelle [Renderer][5] bereit. Aktuell 
verfügbares Parsermodel wird durch [ParsedBean][13] und [ParsedProperty][14] abgebildet.

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
[SCCodeConcatenator][7] und [SCMethodCodeCooncatenator][8] sind sozusagen "StringBuilder"
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
[SCNewLineAndIndentationFormat][9] ist eine Klasse welches die Art und Weise 
regelt wie Programmcode eingerückt wird und welche Zeichenkette als newline
verwendet werden soll.


##### SCRenderer
[SCRenderer][10] ist eine Basis Implementierung von [Renderer][5] Interface das intern
[SCCodeConcatenator][7] und [SCMethodCodeConcatenator][8] nutzt. Um diese Klasse
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
[SCRendererForPropertiesContainer][11] und [PropertiesContainer][12] stellen eine 
Erweiterung von [SCRenderer][10] an und sind speziel für die Models zugeschnitten
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
[7]: src/main/java/com/github/sergejsamsonow/codegenerator/api/producer/sc/SCCodeConcatenator.java
[8]: src/main/java/com/github/sergejsamsonow/codegenerator/api/producer/sc/SCMethodCodeConcatenator.java
[9]: src/main/java/com/github/sergejsamsonow/codegenerator/api/producer/sc/SCNewLineAndIndentationFormat.java
[10]: src/main/java/com/github/sergejsamsonow/codegenerator/api/producer/sc/SCRenderer.java
[11]: src/main/java/com/github/sergejsamsonow/codegenerator/api/producer/sc/SCRendererForPropertiesContainer.java
[12]: src/main/java/com/github/sergejsamsonow/codegenerator/api/producer/sc/PropertiesContainer.java
[13]: src/main/java/com/github/sergejsamsonow/codegenerator/api/parser/model/ParsedBean.java
[14]: src/main/java/com/github/sergejsamsonow/codegenerator/api/parser/model/ParsedProperty.java