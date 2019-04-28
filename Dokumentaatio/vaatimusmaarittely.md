# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on huvikäyttöön tarkoitettu pelikonepokeri, eli oikeaa rahaa pelaamiseen ei tarvita. Sovellukseen kirjautunut pelaaja voi pelata viiden kortin pelikonepokeria.

## Käyttäjät

Sovelluksella on tällä hetkellä ainoastaan yksi käyttäjärooli eli _pelaaja_. Myöhemmin sovellukseen saatetaan lisätä _pääkäyttäjä_, joka voisi esimerkiksi tarkastella pelaajien pelitilastoja.

## Käyttöliittymäluonnos

Sovelluksessa on kirjautumisnäkymä eli etusivu, ja pelinäkymä. Pelinäkymä koostuu tavallisesta pelinäkymästä, sekä tuplausnäkymästä.

<img src="https://github.com/Henrikhi/ot-harjoitustyo/blob/master/Dokumentaatio/Tiedostoja/kirjautumisNakyma.png">

Etusivu: Yksinkertaisessa näkymässä on tekstilaatikot tunnukselle ja salasanalle, sekä kirjautumisnappi ja uuden käyttäjän luomis -nappi. Onnistuneesta kirjautumisesta siirrytään pelinäkymään.

<img src="https://github.com/Henrikhi/ot-harjoitustyo/blob/master/Dokumentaatio/Tiedostoja/peli1.png">

Pelinäkymä: Ruudun vasemmassa yläkulmassa näkyy pelaajan saldo (credits), sekä alhaalla on napit pelin lopettamiselle (stop), panoksen muuttamiselle (bet), voittojen keräämiselle (collect), voittojen tuplaamiselle (double), sekä pelin käynnistämiselle (play). Oikeassa alanurkassa on nappi rahan lisäämiselle koneeseen (Insert 2€). Keskellä ruutua on viisi kuvapuoli alaspäin käännettyä korttia. Lopettaminen kirjaa käyttäjän ulos ja vie etusivulle, muuten pelaaminen tapahtuu pelinäkymässä. Voitokkan kierroksen jälkeen pelaajalla on mahdollisuus tuplaamiseen, joka vie tuplausnäkymään.

<img src="https://github.com/Henrikhi/ot-harjoitustyo/blob/master/Dokumentaatio/Tiedostoja/tuplaus1.png">

Tuplaaminen: Ruudun alareuna ja yläreuna on kuten pelinäkymässä, mutta keskellä ruutua on pelikortti, joka on kuvapuoli alaspäin. Ruudussa on painikkeet "pieni" (low) ja "suuri" (high), joista painaminen aiheuttaa kortin kääntymisen kuvapuoli ylöspäin, jolloin kortin arvo ja maa tulevat esille. Mikäli pelaaja arvasi oikein (pieni = 1-6, suuri = 8-K), tuplautuu pelaajan edellisen kierroksen voitto, ja hän voi uudelleen valita, haluaako tuplata vai kerätä voitot talteen. Mustalla seiskalla häviää, punaisella seiskalla saa omat voittonsa takaisin. Tuplaamisen lopettaminen joko häviämällä tai voittojen talteen keräämisellä (collect) palauttaa pelaajan pelinäkymään.


## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi luoda järjestelmään käyttäjätunnuksen
  - käyttäjätunnuksen täytyy olla uniikki ja pituudeltaan vähintään 5-15 merkkiä pitkä
  - käyttäjätunnus ei voi alkaa tai loppua välilyöntiin
  - käyttäjätunnuksessa ei voi olla kahta tai useampaa perättäistä välilyöntiä
  - salasanan tulee olla 5-20 merkkiä
  - mikäli kirjautuminen ei onnistu jostain edellämainituista syistä, ruutuun ilmestyy virheteksti, joka kertoo virheen.  

- käyttäjä voi kirjautua järjestelmään
  - kirjautuminen onnistuu syöttämällä oikea käyttäjänimi ja salasana ja klikkaamalla "log in"
  - jos kirjautuminen ei onnistu, ruutuun ilmestyy virheteksti, joka kertoo, että käyttäjänimi tai salasana on väärä.

### Kirjautumisen jälkeen

- pelinäkymä käynnistyy
  - pelaaja voi vaihtaa panoksen määrää klikkaamalla nappia "bet". Panokset muuttuvat jokaisen klikkauksen jälkeen seuraavalla tavalla:
     0.2€ -> 0.4€ -> 0.6€ -> 0.8€ -> 1.0€ -> 2.0€ -> 0.2€
  - pelaaja voi käynnistää pelin napista "play" tai lopettaa napista "stop"
    - jos pelaajan panos on suurempi kuin hänen krediitit, mutta pelaaja koittaa pelata, ilmoittaa ohjelma hänelle tästä. Jos krediitit riittävät, niin pelaaminen vähentää saldoa panoksen verran, ja jakaa viisi korttia näkyville, joista pelaaja voi kortteja klikkaamalla lukita haluamansa (0-5). Tässä vaiheessa peliä ei voi lopettaa, vaan pelaajan tulee painaa uudestaan "pelaa", kun on lukinnut korttinsa. Tämän jälkeen lukitsemattomat kortit korvataan uusilla korteilla, ja ruutuun ilmestyy onnitteluteksti ja voiton summa, tai pahoitteluteksti, jos pelaaja ei voittanut mitään.
    - Jos pelaaja voitti, hän voi kerätä voitot talteen (collect) tai tuplata (double).
      - Voittojen kerääminen kasvattaa pelaajan saldoa kierroksen voittosummalla.
      - Tuplaaminen vie tuplausnäkymään.
    - Jos pelaaja ei voittanut mitään, hän voi jatkaa pelaamista, mikäli hänellä on vielä saldoa (credits) tallella.
      - Jos krediitit on vähissä, voi niitä helposti lisätä klikkaamalla "Insert 2 €, joka kasvattaa krediittejä kahdella eurolla".
    - lopettaminen (stop) kirjaa käyttäjän ulos, päivittää käyttäjän tiedot tietokantaan, ja palauttaa käyttäjän kirjautumisnäkymään.
 
 - tuplausnäkymässä pelaajan tulee arvata, onko kortti pieni (low) vai suuri (high). Arvauksen jälkeen kortti paljastetaan. Jos pelaaja arvasi oikein, tuplautuu hänen edelliskierroksen voitto. Mikäli pelaajan voitto kasvaa yli 50€, ei tuplausta voi jatkaa, vaan voitot talletetaan automaattisesti pelaajalle ja tästä ilmoitetaan hänelle. Mikäli voitot on alle 50€, pelaaja voi tuplata uudestaan (double) tai lopettaa painamalla "collect".
 
## Jatkokehitysideoita

- Tietokantaan talletetaan käyttäjän voittojen lisäksi hänen koneeseen syötetyn rahan määrä. Ohjelman tässä versiossa tämä tieto on hyödytön, mutta jatkoa ajatellen ohjelmaan voi tehdä tilastonäkymän, jossa näkyy pelaajan voitot sekä hänen koneeseen syötetyt rahat. Tällä tavoin pelaajan on mahdollista nähdä, kuinka paljon hän on oikeasti häviöllä tai voitolla. Lisäksi tämä mahdollistaisi myös pelikoneen tilaston näyttämisen, että kuinka paljon kaikki pelaajat tällä sovelluksella ovat voittaneet ja paljonko he ovat rahaa koneeseen syöttäneet.
- Sovellukseen voi jatkossa rakentaa erillisen näkymän, joka näyttäisi kuinka paljon voittoja tulisi tietystä kädestä kullakin panoksella. Tällä hetkelle pelaaja ei etukäteen tiedä, kuinka paljon hän voittaisi esimerkiksi 60 snt panoksella värisuorasta.
- Sovelluksen voi tehdä käyttäjäystävällisemmäksi mahdollistamalla käyttäjätunnuksen nimen tai salasanan vaihtamisen, tai käyttäjän poistamisen.
