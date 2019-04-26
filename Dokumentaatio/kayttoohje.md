# Käyttöohje

Lataa tiedosto [PokerApp.jar](https://github.com/Henrikhi/ot-harjoitustyo/releases/tag/Viikko6)

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar PokerApp.jar
```

## Käyttäjän luominen ja kirjautuminen

Sovellus käynnistyy kirjautumisnäkymään:

<img src="https://github.com/Henrikhi/ot-harjoitustyo/blob/master/Dokumentaatio/Tiedostoja/kirjautumisNakyma.png">

Kirjautumisnäkymässä on kaksi tekstikenttää, joista toinen on käyttäjänimelle _account name_ ja toinen salasanalle _password_.
Mikäli käyttäjää ei ole vielä olemassa, voi käyttäjän luoda napista _create account_. Mikäli käyttäjä on olemassa, voi sille kirjautua napista _log in_.
Huom! _create account_ ei avaa uutta näkymää, jossa käyttäjä tehdään, vaan käyttää samoja tekstikenttiä kuin kirjautuessa.


## Pelaaminen

Onnistuneen kirjautumisen myötä siirrytään varsinaiseen pelinäkymään

<img src="https://github.com/Henrikhi/ot-harjoitustyo/blob/master/Dokumentaatio/Tiedostoja/peli1.png">

Pelinäkymässä vasemmalla ylhäällä lukee pelaajan saldo, eli credits. Keskellä ruutua on viisi pelikorttia (jotka on alussa kuvapuoli alaspäin),
ja ruudun alareunassa on kuusi eri nappia, joita klikkaamalla on seuraavat ominaisuudet:
* stop: peli lopetetaan, käyttäjä kirjataan ulos, pelaajan tiedot päivitetään
tietokantaan ja palataan kirjautumisnäkymään. 
* bet: panoksen määrää voi muuttaa.
* collect: voitokkaan pelikierroksen tai onnistuneen tuplauksen jälkeen voi voitot kerätä talteen, jolloin pelaajan saldo kasvaa voittojen verran.
* double: voitokkaan pelikierroksen tai onnistuneen tuplauksen jälkeen voi pelaaja yrittää onneaan voittojen tuplaamisella. Tämä vie [tuplausnäkymään](https://github.com/Henrikhi/ot-harjoitustyo/blob/master/Dokumentaatio/kayttoohje.md#Tuplaaminen).
* play: ensimmäinen tai toinen pelikierros käynnistyy.
* Insert 2€: pelaajan saldoa kasvatetaan kahdella eurolla, eli tämä kuvastaa kahden euron kolikon syöttämistä pelikoneeseen.

Ennen kunkin kierroksen alkua pelaajan on mahdollista vaihtaa panosta tai lopettaa peli. Kierrosten keskellä panosta ei voi vaihtaa, eikä peliä voi lopettaa.
Kun pelaajalla on krediittejä vähintään panoksen verran, voi pelin aloittaa painamalla _play_. Tällöin pelaajalle jaetaan viisi satunnaista korttia.

<img src="https://github.com/Henrikhi/ot-harjoitustyo/blob/master/Dokumentaatio/Tiedostoja/peli2.png">

Pelaaja voi "lukita" haluamansa kortit klikkaamalla niistä. Tällöin kortti pienenee lukituksen merkiksi. Lukitut kortit voi mielen muuttuessa vapauttaa klikkaamalla korttia uudelleen. Kun pelaaja on tyytyväinen, täytyy pelaajan mennä seuraavalle kierrokselle painamalla
uudestaan nappia _play_.

<img src="https://github.com/Henrikhi/ot-harjoitustyo/blob/master/Dokumentaatio/Tiedostoja/peli3.png">

Toisen kierroksen jälkeen tarkistetaan, tuliko pelaajalle voittoja. Mikäli pelaaja voitti, voi hän kerätä voittonsa napista _collect_, tai koittaa
onneaan voittojensa tuplaamisessa painamalla napista _double_. Mikäli voittoja ei tullut, voi pelaaja aloittaa uuden pelin napista _play_.

## Tuplaaminen

Mikäli onnistuneen kierroksen jälkeen pelaaja päättää koittaa onneaan voittojen tuplaamisessa, siirtyy hän tuplausnäkymään.

<img src="https://github.com/Henrikhi/ot-harjoitustyo/blob/master/Dokumentaatio/Tiedostoja/tuplaus1.png">

Tuplausnäkymässä pelaajan tulee arvata, onko ruudun keskellä oleva kortti suuri (8-K), vai pieni (A-6) valitsemalla nappi _high_ tai _low_.
Mikäli pelaaja arvasi oikein, hän voi kerätä nyt tuplautuneet voittonsa, tai yrittää tuplata voittoja uudelleen. Mikäli tuplaus meni pieleen,
pelaaja voi jatkaa pelaamista napista _play_, tai lopettaa napista _stop_.

<img src="https://github.com/Henrikhi/ot-harjoitustyo/blob/master/Dokumentaatio/Tiedostoja/tuplaus2.png">
Aina ei voi voittaa!
