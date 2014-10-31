## PartialRendererBasedProducer
Arbeitet mit eine Liste von "Renderer" um Codegenerierung zu bewältigen 
(z.B. Renderer für Setter Methoden). Die  Ausgaben von einzelnen "Renderer"
werden dabei zu eine Gesamtausgabe zusammengefügt. Die überschreibende Klasse 
muss lediglich die Methoden transform und subpath bereitstellen.

* transform - Wandelt von Parser kommendes Model in eine von Producer Model.
* subpath - Generiert Teilpfad String (wo befindet sich die Datei) Teilpfad ist dabei relativ.

![Ablauf](src/site/resources/sequence-partial-renderer.png)