Parser Komponenten
==================

Klassenübersicht
![Überblick](src/site/resources/parser.png)

##### SimpleParser
Einfaches Parser Beispiel parst Daten in ein Metamodel welches von den Producer 
weiterverarbeitet wird. Beispiel Eingabe Datei für diesen Parser kann man [hier][1] 
anschauen.

##### SoyParser
Parser für Extraktion von Informationen aus Google Closure Templates Dateien.
Beispiel:
```
    {namespace page}

    /**
     * Soy Documentation for Template.
     * @param message 
     * 
     * Bean : Person extends Entry
     * @param age  : Integer
     * @param name : String
     */
```
Damit Parser funktionieren kann muss ein Marker ``Bean : `` in Template 
Beschreibung enthalten sein in oberen Beispiel werden folgene Informationen
extrahiert:

1. Namespace page
2. Metatype Person welches von Entry abgeleitet wird beide liegen in Namespace page
3. Person Integer Property age
4. Person Integer Property name

Parameter message wird nicht als Teil des Metatype angesehen es werden nur 
Informationen nach dem Marker ``Bean : `` ausgewertet. Momentan wird ein Bean 
pro Template Beschreibung extrahiert es wurde nicht getestet was passiert 
wenn eine Beschreibung mehrere Bean Deklarationen enthalten sind.



[1]: src/test/resources/simple-parser-input.txt
