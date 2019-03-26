# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on huvikäyttöön tarkoitettu pelikonepokeri, eli oikeaa rahaa pelaamiseen ei tarvita. Sovellukseen kirjautunut pelaaja voi pelata viiden kortin pelikonepokeria.

## Käyttäjät

Alkuvaiheessa sovelluksella on ainoastaan yksi käyttäjärooli eli _pelaaja_. Myöhemmin sovellukseen saatetaan lisätä _pääkäyttäjä_, joka voisi esimerkiksi tarkastella pelaajien pelitilastoja.

## Käyttöliittymäluonnos

Sovelluksessa on ainakin etusivu- ja pelinäkymä. Mahdollisesti myös mahdollisuus tuplaamiseen, jolloin luodaan tuplaamisnäkymä.
<img src="https://github.com/Henrikhi/ot-harjoitustyo/blob/master/Dokumentaatio/Tiedostoja/kayttoliittymakuva.png">

Etusivu: Yksinkertaisessa näkymässä on tekstilaatikot tunnukselle ja salasanalle, sekä kirjautumisnappi ja uuden käyttäjän luomis -nappi. Kirjautumisesta siirrytään pelinäkymään.

Pelinäkymä: Ruudun alareunassa näkyy pelaajan saldo, sekä alhaalla on napit pelin aloittamiselle, lopettamiselle, rahan lisäämiselle ja mahdollisesti tuplaamiselle ja voittojen keräämiselle. Keskellä ruutua on tyhjää tilaa kortteja varten. Lopettaminen kirjaa käyttäjän ulos ja vie etusivulle, mahdollinen tuplaaminen voitetun kierroksen jälkeen vie tuplausnäkymään. Muuten pelaaminen tapahtuu pelinäkymässä.

Tuplaaminen: Ruudun alareuna on kuten pelinäkymässä, mutta keskelle ruutua tulee pelikortti, joka on kuvapuoli "alaspäin", eli kortin arvoa ja maata ei vielä näy. Ruutuun tulee painikkeet "pieni" ja "suuri", joista painaminen aiheuttaa kortin kääntymisen kuvapuoli ylöspäin, jolloin kortin arvo ja maa tulevat esille. Mikäli pelaaja arvasi oikein (pieni = 1-6, suuri = 8-K), tuplautuu pelaajan edellisen kierroksen voitto, ja hän voi uudelleen valita, haluaako tuplata vai kerätä voitot talteen. Tuplaamisen lopettaminen joko häviämällä tai voittojen talteen keräämisellä palauttaa pelaajan pelinäkymään.


## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi luoda järjestelmään käyttäjätunnuksen
  - käyttäjätunnuksen täytyy olla uniikki ja pituudeltaan vähintään 5 merkkiä
  - salasanan tulee olla ainakin 8 merkkiä

- käyttäjä voi kirjautua järjestelmään
  - kirjautuminen onnistuu syöttämällä oikea käyttäjänimi ja salasana ja klikkaamalla "log in"
  - jos käyttäjää ei olemassa, tai salasana on väärä, ilmoittaa järjestelmä tästä

### Kirjautumisen jälkeen

- pelinäkymä käynnistyy

-pelaaja voi käynnistää pelin napista "play" tai lopettaa napista "stop"
  - pelaaminen vähentää saldoa panoksen verran, ja jakaa viisi korttia näkyville, joista pelaaja voi kortteja klikkaamalla lukita haluamansa (0-5). Tässä vaiheessa peliä ei voi lopettaa, vaan pelaajan tulee painaa uudestaan "pelaa", kun on lukinnut korttinsa. Tämän jälkeen lukitsemattomat kortit korvataan uusilla korteilla, ja ruutuun ilmestyy onnitteluteksti ja voiton summa, tai pahoitteluteksti, jos pelaaja ei voittanut mitään. Jos pelaaja voitti, hän voi kerätä voitot talteen (collect) tai tuplata (double). Tuplaaminen vie tuplausnäkymään. Jos pelaaja ei voittanut mitään, hän voi jatkaa pelaamista, mikäli hänellä on vielä saldoa (winnings) tallella. Jos krediitit on vähissä, voi niitä helposti lisätä klikkaamalla "insert coins".
  - tuplausnäkymässä pelaajan tulee arvata, onko kortti pieni (small) vai suuri (high). Arvauksen jälkeen kortti paljastetaan. Jos pelaaja arvasi oikein, tuplautuu hänen edelliskierroksen voitto. Tämän jälkeen pelaaja voi tuplata uudestaan (double) tai lopettaa painamalla "stop" tai "collect", jolloin hänet palautetaan pelinäkymään. Mikäli pelaaja häviää tuplauksen, palautetaan hänet pelinäkymään.
  - "collect" kasvattaa pelaajan saldoa kierroksen voittosummalla.
  - "insert coins" kasvattaa pelaajan saldoa tietyllä summalla, esimerkiksi kahdella eurolla.
  - lopettaminen (stop) kirjaa käyttäjän ulos ja palauttaa etusivunäkymän
  - Mikäli "bet"-nappi toiminnassa: kierroksen panosta voi muuttaa klikkaamalla "bet". Panokset voivat muuttua esimerkiksi seuraavalla tavalla:
  0.2€ -> 0.4€ -> 0.6€ -> 0.8€ -> 1.0€ -> 0.2€ jne.


## Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään ajan salliessa esim. seuraavilla toiminnallisuuksilla

- mahdollisestaan tuplaaminen
- mahdollistetaan panoksen määrän muuttaminen
- mahdollistetaan näkymä, jossa näkee paljon kullakin panoksella tulisi voittoja kustakin kädestä
- mahdollistetaan käyttäjätunnuksen nimen tai salasanan vaihtaminen
- mahdollistetaan käyttäjän poistaminen
