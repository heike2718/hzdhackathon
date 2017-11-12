# hzdhackathon

## Zentralkomponente

Die Zentralkomponente sammelt und kumuliert Daten von beliebig vielen Sensoren. Hierzu steht folgende REST API zur Verfügung:

| http Methode | url | body | response | Zweck |
| ------ | ------ | ------- | -------- | ------ |
| GET | http://192.168.20.1:9200/hackrest/ping | - | text.plain | teste Verfügbarkeit
| POST | http://192.168.20.1:9200/hackrest/bild | {"id":"bildname","dauer": 25 } (wobei id (string) die ID des Sensor-Raspis ist und dauer (int) die Betrachtungsdauer in Sekunden) | text.plain (aktueller Preis) | sende Daten an die Zentralkomponente
| GET | http://192.168.20.1:9200/hackrest/report | - | text.html (Preisliste aller Bilder als html) | frage alle Preise ab
| GET | http://192.168.20.1:9200/hackrest/report/rembrand | - | text.plain (aktueller Preis des Bildes mit der id "rembrand" | frage einen einzelnen Bildpreis ab

Die Zentralkomponente registriert die sendenden Raspis automatisch beim ersten Empfang eines POST- Requests

### Start der Anwendung als Service

SSH-Verbindung aufbauen:

ssh pi@192.168.20.1 

(Passwort ist raspberry)

root werden:
sudo su

Kommando:

service hackathon start

Mittels 

tail -f /home/pi/microservices/hackathon/log/zentralkomponente.log

kann man die Logausgaben mitlaufen lassen.

### Stop der Anwendung

als root Kommando

service hackathon stop

### Konfiguration

Die Konfiguration erfolgt in der Datei 
/home/pi/microservices/hackathon/hackathon.yml.

Neben den anderen Einstellungen, die nicht geändert werden dürfen gibt es 2 fachliche Konfigurationsparameter für die Schnittstelle:
(bitte nur den Wert ändern, auf keinen Fall den Namen!)

| Parametername | Datentyp | Bedeutung | default |
| ------ | ------ | ------ | ------ |
| centProSekunde | Ganzzahl | Anzahl ct, um die sich der Preis eines Bildes je Sekunde Betrachtungsdauer erhöht | 5
| anfangspreisEuro | Ganzzahl | Startpreis in Euro | 100

Zum Ändern der Prameter muss der service gestopt und nach dem Ändern neu gestartet werden. Da Daten nicht persistiert werden, gehen sie dabei verloren.
