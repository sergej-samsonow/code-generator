code-generator [![Build state](https://travis-ci.org/sergej-samsonow/code-generator.svg)](https://travis-ci.org/sergej-samsonow/code-generator)
==============

Bunch of tools for code generation.
Fill free to translate this readme into english language

## Was ist das?
Das ist eine Kollektion von Werkzeugen für die Generierung von Programm Code.
Das ganze wurde dafür entworfen um eine „Metamodel“ - Datenstruktur 
Beschreibung wie z.B. 

```
Person
firstName : String
lastName : String
age : Integer
```
in eine konkrete Programmier-Sprache wie z.B. Java oder PHP zu übersetzen 
und dabei eine Menge Tipparbeit zu sparen.  

## Architektur
Aktuelle Architektur besteht in wesentlichen aus 3 folgenden Schichten

![Architektur](src/site/resources/architecture.png)

1. Parser -  extrahiert eine Datenstrukturbeschreibung aus eine "Metasprache"
   und übergibt diese an Producer Schicht. 
2. Producer - nimmt Datenstrukturbeschreibung entgegen und generiert daraus 
   Programmcode und und Teilpfad von Programmcodedatei für die Writer Schicht
3. Writer -  ist die letzte Schicht und ist für die Ausgabe von generierten
   Programmcode verantwortlich (Standardausgabe oder Dateisystem)

## Komponente:
* [code-generator-api](api) - Entwicklungs API
* [code-generator-simple-parser](simple-parser) - Beispiel Parser implementierung.
* [code-generator-pojo-producer](pojo-producer) - Plain old java objects code generator.
