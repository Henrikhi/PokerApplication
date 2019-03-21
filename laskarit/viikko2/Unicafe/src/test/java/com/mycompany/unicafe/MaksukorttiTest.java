package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }

    @Test
    public void kortinLataaminenToimii() {
        kortti.lataaRahaa(110);
        assertEquals("saldo: 11.10", kortti.toString());
    }

    @Test
    public void negatiivinenLatausEiTeeMitaan() {
        kortti.lataaRahaa(-1100);
        assertEquals("saldo: 10.0", kortti.toString());
    }

    @Test
    public void kortinRahanNostaminenOnnistuuMaara() {
        kortti.otaRahaa(450);
        assertEquals("saldo: 5.50", kortti.toString());
    }

    @Test
    public void kortinRahanNostaminenOnnistuuBoolean() {
        assertTrue(kortti.otaRahaa(450));
    }

    @Test
    public void kortiltaEiVoiNostaaLiikaaMaara() {
        kortti.otaRahaa(1500);
        assertEquals("saldo: 10.0", kortti.toString());
    }

    @Test
    public void kortiltaEiVoiNostaaLiikaaBoolean() {
        assertFalse(kortti.otaRahaa(1500));
    }
    
    @Test
    public void tarkistaSaldo() {
        assertEquals(kortti.saldo(), 1000);
    }
}
