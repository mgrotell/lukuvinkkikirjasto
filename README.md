# lukuvinkkikirjasto ![Github Actions](https://github.com/msiivone/lukuvinkkikirjasto/actions/workflows/gradle.yml/badge.svg)

[Product backlog](https://docs.google.com/spreadsheets/d/12AyWmIQBzrVbD5ydoa-WGuoNIj_iCAdB56etHmK6v4w/edit#gid=1)

[(Vanha Product backlog)](https://github.com/msiivone/lukuvinkkikirjasto/projects/1)

[Definition of done](https://github.com/msiivone/lukuvinkkikirjasto/blob/main/dokumentaatio/definitionofdone.md)



## Ohjelman suoritus ohjeet
Ohjelma on testattu ja todettu toimivaksi Gradlen 7.2 versiolla.
1. Siirry komentorivillä projektin juureen.
2. Suorita projektin juuressa komento `./gradlew build` (Linux) tai `gradlew.bat build` (Windows). Jos edelliset komennot eivät toimi suorita komento `Gradlew build`.
3. Kun build on suoritettu onnistuneesti suorita komento `./gradlew` run tai jos edellinen komento ei toimi niin `gradle run`.

Nyt ohjelma käynnistyy komentoriville seuraavan näköisenä

![image](https://user-images.githubusercontent.com/67758940/144003407-97dc8b57-d7d4-41cc-88e7-ea4536811710.png)



__Syöte 1__ listaa kaikki lukuvinkit komentoriville:

![image](https://user-images.githubusercontent.com/67758940/144003748-651a78f0-0428-4ac3-be63-0eda355b3454.png)

Nyt lista on tyhjä koska ohjelman tallennustila alustetaan aina ohjelman käynnistyessä.

__Syöte 2__ mahdollistaa lukuvinkin lisäämisen ohjelmaan.
Täyttämällä ohjeiden mukaisesti sen haluamat tiedot kyseisetä lukuvinkistä saadaan lukuvinkki lisättyä muistiin.

Nyt kokeillaan uudestaan __Syötteellä 1__: 

![image](https://user-images.githubusercontent.com/67758940/144004813-5c598af6-87f9-4ec9-83e9-22dcb5a468b0.png)

__Syöte 0__ poistuu ohjelmasta
