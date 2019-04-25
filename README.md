# Pelikonepokeri-sovellus

Sovelluksen avulla pelaaja voi pelata pelikonepokeria omalta tietokoneeltaan, vailla oikean rahan häviämisen pelkoa. Pelajan tulee tehdä uusi käyttäjä, tai kirjautua jo tehdyllä käyttäjällä.

## Dokumentaatio

[Käyttöohje](https://github.com/Henrikhi/ot-harjoitustyo/blob/master/Dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/Henrikhi/ot-harjoitustyo/blob/master/Dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/Henrikhi/ot-harjoitustyo/blob/master/Dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuuri](https://github.com/Henrikhi/ot-harjoitustyo/blob/master/Dokumentaatio/arkkitehtuuri.md)

## Releaset

* [Viikko 5](https://github.com/Henrikhi/ot-harjoitustyo/releases/tag/viikko5)
* [Viikko 6](https://github.com/Henrikhi/ot-harjoitustyo/releases/tag/viikko6)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _PokerApplication-1.0-SNAPSHOT.jar_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/Henrikhi/ot-harjoitustyo/blob/master/PokerApplication/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

