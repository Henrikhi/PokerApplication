### Alustava arkkitehtuuri

<img src="https://github.com/Henrikhi/ot-harjoitustyo/blob/master/Dokumentaatio/Tiedostoja/alustavaArkkitehtuuri2.png">

### Päätoiminnallisuudet

Kuvataan seuraavaksi sekvenssikaaviona eräs sovelluksen toimintalogiikan päätoiminnallisuuksista.

#### play:n painaminen kahdesti eli ensimmäisellä kerralla viiden kortin jakaminen, ja toisella kerralla lukitsemattomien korttien vaihtaminen ja mahdollisten voittojen tarkistaminen

Kun pelaamisnäkymässä painetaan nappia "play", voi sovelluksen kontrolli esimerkiksi edetä seuraavasti: (riippuen mitä kortteja lukitaan ja kuinka paljon saldoa on jäljellä ym)


<img src="https://github.com/Henrikhi/ot-harjoitustyo/blob/master/Dokumentaatio/Tiedostoja/playClicked.png">

Käyttäjä painaa play, jonka jälkeen sovelluslogiikan puolella ajetaan metodit newRound ja playNewRound. PlayNewRound tyhjentää sovelluslogiikalla olevan listan korteista eli käden metodilla emptyHand(), joka lisää jokaisen poistettavan kortin takaisin pakkaan metodilla addToDeck(card). Tämän jälkeen sovelluslogiikan listaan lisätään viisi uutta korttia metodilla deal5(), joka kutsuu viidesti dealRandomCard() metodia, jotka palauttavat satunnaisen kortin. Tämä lista palautetaan sovelluslogiikalle getHand() metodin vastauksena. Tämän jälkeen sovelluslogiikka kysyy jokaisen viiden kortin toString() -metodia, ja päivittää pääsovelluksen (eli käyttöliittymän) PokerMainin taulukkoa cardButtons[] asettamalla korttipainikkeiden tekstiksi kortin toStringin metodilla cardButtons[i].setText(cardString). Tämän jälkeen sovelluslogiikka vielä selvittää kortin värin getColor(card)-metodilla, ja päivittää pääsovelluksen cardButtons[]-taulukon painikkeen tekstinväriä asianmukaisesti.

Kun tämän jälkeen käyttäjä painaa uudestaan play, kutsuu pääohjelma sovelluslogiikan metodia playContinueRound(), joka korvaa lukitsemattomat kortit uusilla korteilla replace(oldCard)-metodilla. Tämä perustuu taas aiemmin mainittuun dealRandomCard-metodiin. Kun mahdolliset korttien korvaamiset on suoritettu, muutetaan pääsovelluksen cardButtons[]-taulukon arvot samalla lailla kuin aiemmin. Tämän jälkeen sovelluslogiikka vielä kutsuu metodia checkHand(), joka tarkistaa mahdollisten voittojen arvon. Sekvenssikaaviossa Hand-luokassa suoritetaan alimetodeja, jotka yksi kerrallaan tarkistavat, onko kädessä värisuora, viitoset, neljä samaa, jne vai onko käden arvo nolla, eli kädessä ei ole edes yhtä korkeaa paria (10-A). Mikäli jokin näistä korttientarkistusalimetodeista, kuten twoPairs() palauttaa totuusarvon tosi, palautetaan sovelluslogiikalle korttien arvoa vastaava kerroin, kuten kahden parin tapauksessa kerroin 2. Sovelluslogiigan playContinueRound() on nyt suoritettu, ja palauttaa pääsovellukselle käden arvon kertoimen ja panoksen tulon, eli tässä tapauksessa palautetaan doublena 2 * "bet".
