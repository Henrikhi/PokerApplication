# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkötestein JUnitilla sekä manuaalisesti koko perheen voimin sovellusta pelaamalla.

## Yksikkötestaus

### [sovelluslogiikka](https://github.com/Henrikhi/ot-harjoitustyo/tree/master/PokerApplication/src/main/java/poker/logic), [testit](https://github.com/Henrikhi/ot-harjoitustyo/tree/master/PokerApplication/src/test/java/poker/logic)

Sovelluslogiikan luokkaa [GameLogics](https://github.com/Henrikhi/ot-harjoitustyo/blob/master/PokerApplication/src/main/java/poker/logic/GameLogics.java) on testattu testiluokalla [GameLogicsTest](https://github.com/Henrikhi/ot-harjoitustyo/blob/master/PokerApplication/src/test/java/poker/logic/GameLogicsTest.java).
Testeissä on testattu osaa pelilogiikan metodeista kattavasti, kuten panoksen muuttamista ja tuplauksen toimivuutta. Muun muassa tietokantaan tietojen tallentamista ei ole testattu yksikkötesteillä, vaan tietokantatiedostoa on manuaalisesti tutkittu samalla, kun sovellusta on manuaalisesti testattu. Myös pelilogiikassa korttien klikkaamisen toimivuus on tarkistettu manuaalisesti, eikä tälle ole tehty testejä. Tämä johtuu siitä, että testiluokassa todettiin monimutkaiseksi luoda javafx-objekteja.
Sovelluslogiikan rivikattavuus on 47% ja haarautumakattavuus 62%.


### [kortit](https://github.com/Henrikhi/ot-harjoitustyo/tree/master/PokerApplication/src/main/java/poker/cards), [testit](https://github.com/Henrikhi/ot-harjoitustyo/tree/master/PokerApplication/src/test/java/poker/cards)

Kortteja koskevat luokat on tarkistettu yksikkötesteillä erittäin kattavasti. Muun muassa eri käsien arvojen testaaminen on suoritettu useilla esimerkeillä.
Kortteihin liittyvissä testeissä rivikattavuus on 98% ja haarautumakattavuus 86%.


### [tietokanta](https://github.com/Henrikhi/ot-harjoitustyo/tree/master/PokerApplication/src/main/java/poker/database), [testit](https://github.com/Henrikhi/ot-harjoitustyo/tree/master/PokerApplication/src/test/java/poker/database)

Kaikki tietokantaan liittyvä testaaminen on suoritettu manuaalisesti, eikä automaattisia testejä ole. Virheitä ei ole ilmennyt. Tietokantapakkauksessa on käyttäjälle luokka [User](https://github.com/Henrikhi/ot-harjoitustyo/blob/master/PokerApplication/src/main/java/poker/database/User.java), jolle on tehty kattavat yksikkötestit [täällä](https://github.com/Henrikhi/ot-harjoitustyo/blob/master/PokerApplication/src/test/java/poker/database/UserTest.java).
Käyttäjäluokan rivikattavuus on 100%, tietokantaluokan rivikattavuus vain 13%. Vaikka erillisiä testejä tietokantaluokkaan ei ole luotu, rivikattavuus on 13% nollan sijaan, koska varsinaisessa [Database.java](https://github.com/Henrikhi/ot-harjoitustyo/blob/master/PokerApplication/src/main/java/poker/database/Database.java)-tiedostossa eri metodeissa on heitetty poikkeuksia.

### Testauskattavuus

Käyttöliittymäluokkaa lukuunottamatta sovelluksen testauksen rivikattavuus on 74% ja haarautumakattavuus 80%

Testaamatta jäi sovelluslogiikan metodit, joissa tarvitaan javaFX-olioita, sekä kaikki tietokantaan liittyvät testit.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi

Sovellus on haettu ja sitä on testattu [käyttöohjeen](https://github.com/Henrikhi/ot-harjoitustyo/blob/master/Dokumentaatio/kayttoohje.md) kuvaamalla tavalla Windows 10 -käyttöjärjestelmän tietokoneella.

Sovellusta on testattu tietokantatiedoston _database.db_ kanssa, sekä ilman sitä. Mikäli tiedosto on ollut ajettavan jar-tiedoston kanssa samassa kansiossa, on sovellus osannut käyttää tietokannan tietoja sekä päivittää niitä. Mikäli tietokantatiedostoa ei sovelluksen käynnistyessä ollut, loi sovellus uuden tietokantatiedoston, kuten kuuluukin.

### Toiminnallisuudet

Kaikki [määrittelydokumentin](https://github.com/Henrikhi/ot-harjoitustyo/blob/master/Dokumentaatio/vaatimusmaarittely.md) ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. Kaikkien toiminnallisuuksien yhteydessä on syötekentät yritetty täyttää myös virheellisillä arvoilla kuten tyhjillä. Lisäksi sovellusta on testattu virheiden varalta klikkailemalla eri nappuloita silloin, kun niitä ei kuuluisi klikata. Esimerkiksi kesken kierroksen panosta ei voi vaihtaa, eikä voittoja voi kerätä useaan otteeseen.
