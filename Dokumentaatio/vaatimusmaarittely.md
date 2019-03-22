# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on huvikäyttöön tarkoitettu pelikonepokeri, eli oikeaa rahaa pelaamiseen ei tarvita. Sovellukseen kirjautunut pelaaja voi pelata viiden kortin pelikonepokeria.

## Käyttäjät

Alkuvaiheessa sovelluksella on ainoastaan yksi käyttäjärooli eli _pelaaja_. Myöhemmin sovellukseen saatetaan lisätä _pääkäyttäjä_, joka voisi esimerkiksi tarkastella pelaajien pelitilastoja.

## Käyttöliittymäluonnos

Sovelluksessa on ainakin etusivu- ja pelinäkymä. Mahdollisesti myös mahdollisuus tuplaamiseen, jolloin luodaan tuplaamisnäkymä.

Etusivu: Yksinkertaisessa näkymässä on tekstilaatikot tunnukselle ja salasanalle, sekä kirjautumisnappi ja uuden käyttäjän luomis -nappi. Kirjautumisesta siirrytään pelinäkymään.

Pelinäkymä: Ruudun alareunassa näkyy pelaajan saldo, sekä alhaalla on napit pelin aloittamiselle, lopettamiselle, rahan lisäämiselle ja mahdollisesti tuplaamiselle ja voittojen keräämiselle. Keskellä ruutua on tyhjää tilaa kortteja varten. Lopettaminen kirjaa käyttäjän ulos ja vie etusivulle, mahdollinen tuplaaminen kierroksen jälkeen vie tuplausnäkymään.

Tuplaaminen: Ruudun alareuna kuten pelinäkymässä, mutta keskelle ruutua tulee pelikortti, joka on kuvapuoli "alaspäin", eli kortin arvoa ja maata ei vielä näy. Ruutuun tulee painikkeet "pieni" ja "suuri", joista painaminen aiheuttaa kortin kääntymisen kuvapuoli ylöspäin, jolloin kortin arvo ja maa tulevat esille. Mikäli pelaaja arvasi oikein (pieni = 1-6, suuri = 8-K), tuplautuu pelaajan edellisen kierroksen voitto, ja hän voi uudelleen valita, haluaako tuplata vai kerätä voitot talteen. Tuplaamisen lopettaminen joko häviämällä tai voittojen talteen keräämisellä palauttaa pelaajan pelinäkymään.


## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi luoda järjestelmään käyttäjätunnuksen
  - käyttäjätunnuksen täytyy olla uniikki ja pituudeltaan vähintään 3 merkkiä

- käyttäjä voi kirjautua järjestelmään
  - kirjautuminen onnistuu syötettäessä olemassaoleva käyttäjätunnus kirjautumislomakkeelle
  - jos käyttäjää ei olemassa, ilmoittaa järjestelmä tästä

### Kirjautumisen jälkeen

- pelinäkymä käynnistyy

-pelaaja voi käynnistää pelin napista "pelaa" tai lopettaa napista "lopeta"
  - pelaaminen vähentää saldoa panoksen verran, ja luo viisi korttia näkyville, joista pelaaja voi kortteja klikkaamalla lukita haluamansa (0-5). Tässä vaiheessa peliä ei voi lopettaa, vaan pelaajan tulee painaa uudestaan "pelaa", kun on lukinnut korttinsa. Tämän jälkeen lukitsemattomat kortit korvataan uusilla korteilla, ja ruutuun ilmestyy onnitteluteksti ja voiton summa, tai pahoitteluteksti. Pelaaja voi kerätä voitot talteen tai tuplata. Tuplaaminen
  - lopettaminen kirjaa käyttäjän ulos ja palauttaa etusivunäkymän


## Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään ajan salliessa esim. seuraavilla toiminnallisuuksilla

- mahdollisestaan tuplaaminen
- mahdollistetaan panoksen määrän muuttaminen
- mahdollistetaan näkymä, jossa näkee paljon kullakin panoksella tulisi voittoja kustakin kädestä
- mahdollistetaan käyttäjätunnuksen nimen tai salasanan vaihtaminen
- mahdollistetaan käyttäjän poistaminen
