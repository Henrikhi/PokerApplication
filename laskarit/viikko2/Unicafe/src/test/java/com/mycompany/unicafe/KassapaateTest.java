package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    public KassapaateTest() {
    }

    @Before
    public void setUp() {
    }

    /*
    Tee testiluokka KassapaateTest ja tee testit jotka testaavat ainakin seuraavia asioita:

luodun kassapäätteen rahamäärä ja myytyjen 
    lounaiden määrä on oikea (rahaa 1000, lounaita myyty 0)
    
käteisosto toimii sekä edullisten että maukkaiden lounaiden osalta:
    
-jos maksu riittävä: kassassa oleva rahamäärä kasvaa lounaan
    hinnalla ja vaihtorahan suuruus on oikea
-jos maksu on riittävä: myytyjen lounaiden määrä kasvaa
-jos maksu ei ole riittävä: kassassa oleva rahamäärä ei muutu,
    kaikki rahat palautetaan vaihtorahana ja myytyjen lounaiden 
    määrässä ei muutosta

    seuraavissa testeissä tarvitaan myös Maksukorttia jonka oletetaan 
    toimivan oikein
korttiosto toimii sekä edullisten että maukkaiden lounaiden osalta:
-jos kortilla on tarpeeksi rahaa, veloitetaan summa kortilta ja
    palautetaan true
-jos kortilla on tarpeeksi rahaa, myytyjen lounaiden määrä kasvaa
-jos kortilla ei ole tarpeeksi rahaa, kortin rahamäärä ei muutu, 
    myytyjen lounaiden määrä muuttumaton ja palautetaan false
-kassassa oleva rahamäärä ei muutu kortilla ostettaessa
    
kortille rahaa ladattaessa kortin saldo muuttuu ja kassassa oleva
    rahamäärä kasvaa ladatulla summalla
    
Huomaat että kassapääte sisältää melkoisen määrän "copypastea".
    Nyt kun kassapäätteellä on automaattiset testit, on sen 
    rakennetta helppo muokata eli refaktoroida siistimmäksi 
    koko ajan kuitenkin varmistaen, että testit menevät läpi. 
    Tulemme tekemään refaktoroinnin myöhemmin kurssilla.
     */
}
