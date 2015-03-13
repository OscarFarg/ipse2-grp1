# Eclipse + Subversion #

Om in eclipse gebruikt te maken van de Google Code SVN moeten de volgende stappen worden genomen.

## Installeer Subclipse ##

Start Eclipse (Galileo) op en ga naar het menu _'Help -> Install New Software...'_<br>
Klik in het <i>Install</i> venster op de knop <i>Add...</i><br>
Er verschijnt een venster waarin je de volgende gegevens invult:<br>

<b>Name:</b> <i>Subclipse 1.6.x (Eclipse 3.2+)</i><br>
<b>URL:</b> <i>http://subclipse.tigris.org/update_1.6.x</i><br>

Klik op <i>OK</i>.<br>
Klap het item <i>Subclipse</i> open en vink daar de volgende onderdelen aan:<br>
<i>Subclipse (Required)</i><br>
<i>SVNKit Client Adapter (Not required)</i><br>

Klik op <i>Next ></i>, <i>Next ></i> en <i>Finish</i>.<br>
De benodigde onderdelen worden nu gedownload.<br>
<br>
<br>
<h2>Project gegegens</h2>
Zorg dat je bent ingelogd met je google account en ga naar:<br>
<a href='https://code.google.com/hosting/settings'>https://code.google.com/hosting/settings</a><br>
Op deze pagina staat je GoogleCode.com Password. Dit wachtwoord is nodig als je bestanden wil wijzigen en toevoegen aan de repository<br>

Maak een nieuwe workspace.<br>
Klik op <i>File -> Import..</i><br>
In het nieuwe venster ga je naar <i>SVN</i> en kies je voor <i>Checkout project from SVN</i><br>
Kies 'Create a new repository location"<br>
Vul de locatie van dit project in.<br>
<i>https://ipse2-grp1.googlecode.com/svn/trunk/</i><br>

<h2>Commit</h2>
Als je gewijzigde code wil toevoegen aan het project rechter muisklik je op het gewijzigde bestand of map en kies je voor <i>Team -> Commit...</i><br>
Als er om een gebruikersnaam en wachtwoord gevraagd word vul je daar je Google Account naam en je GoogleCode.com wachtwoord in.<br>