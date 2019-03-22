package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate kassapaate;
    Maksukortti maksukortti;

    @Before
    public void setUp() {
        this.kassapaate = new Kassapaate();
        this.maksukortti = new Maksukortti(1000);
    }

    @Test
    public void alustusRahaOK() {
        assertEquals(kassapaate.kassassaRahaa(), 100000);
    }

    @Test
    public void alustusEdullisiaMyyty0() {
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 0);
    }

    @Test
    public void alustusMaukkaitaMyyty0() {
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 0);
    }

    @Test
    public void syoEdullisestiKorttiBooleanTrue() {
        assertTrue(kassapaate.syoEdullisesti(maksukortti));
    }

    @Test
    public void syoEdullisestiKorttiBooleanFalse() {
        Maksukortti kortti = new Maksukortti(10);
        assertFalse(kassapaate.syoEdullisesti(kortti));
    }

    @Test
    public void syoMaukkaastiKorttiBooleanTrue() {
        assertTrue(kassapaate.syoMaukkaasti(maksukortti));
    }

    @Test
    public void syoMaukkaastiKorttiBooleanFalse() {
        Maksukortti kortti = new Maksukortti(10);
        assertFalse(kassapaate.syoMaukkaasti(kortti));
    }

    @Test
    public void syoEdullisestiKorttiMaaraMuuttuu() {
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 1);
    }

    @Test
    public void syoEdullisestiKorttiMaaraEiMuutu() {
        Maksukortti kortti = new Maksukortti(10);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 0);
    }

    @Test
    public void syoMaukkaastiKorttiMaaraMuuttuu() {
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 1);
    }

    @Test
    public void syoMaukkaastiKorttiMaaraEiMuutu() {
        Maksukortti kortti = new Maksukortti(10);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 0);
    }

    @Test
    public void syoEdullisestiKassaTasaraha() {
        assertEquals(kassapaate.syoEdullisesti(240), 0);
    }

    @Test
    public void syoEdullisestiKassaVaihtorahaOK() {
        assertEquals(kassapaate.syoEdullisesti(260), 20);
    }

    @Test
    public void syoEdullisestiKassaLiianVahanRahaa() {
        assertEquals(kassapaate.syoEdullisesti(200), 200);
    }

    @Test
    public void syoEdullisestiKassaMaaraKasvaa() {
        kassapaate.syoEdullisesti(500);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 1);
    }

    @Test
    public void syoEdullisestiKassaMaara0() {
        kassapaate.syoEdullisesti(200);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 0);
    }

    @Test
    public void syoMaukkaastiKassaTasaraha() {
        assertEquals(kassapaate.syoMaukkaasti(400), 0);
    }

    @Test
    public void syoMaukkaastiKassaVaihtorahaOK() {
        assertEquals(kassapaate.syoMaukkaasti(4500), 4100);
    }

    @Test
    public void syoMaukkaastiKassaLiianVahanRahaa() {
        assertEquals(kassapaate.syoMaukkaasti(300), 300);
    }

    @Test
    public void syoMaukkaastiKassaMaaraKasvaa() {
        kassapaate.syoMaukkaasti(500);
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 1);
    }

    @Test
    public void syoMaukkaastiKassaMaara0() {
        kassapaate.syoEdullisesti(399);
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 0);
    }

    @Test
    public void kassaanEiVaikutaKorttimaksuEdullinen() {
        int kassaAlku = kassapaate.kassassaRahaa();
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(kassaAlku, kassapaate.kassassaRahaa());
    }

    @Test
    public void kassaanEiVaikutaKorttimaksuMaukas() {
        int kassaAlku = kassapaate.kassassaRahaa();
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(kassaAlku, kassapaate.kassassaRahaa());
    }

    @Test
    public void lataaRahaaOnnistuuKortti() {
        kassapaate.lataaRahaaKortille(maksukortti, 100);
        assertEquals(maksukortti.saldo(), 1100);
    }

    @Test
    public void lataaRahaaOnnistuuKassa() {
        kassapaate.lataaRahaaKortille(maksukortti, 100);
        assertEquals(kassapaate.kassassaRahaa(), 100100);
    }
    
    @Test
    public void lataaRahaaEiOnnistuKortti() {
        kassapaate.lataaRahaaKortille(maksukortti, -100);
        assertEquals(maksukortti.saldo(), 1000);
    }
    
    @Test
    public void lataaRahaaEiOnnistuKassa() {
        kassapaate.lataaRahaaKortille(maksukortti, -100);
        assertEquals(kassapaate.kassassaRahaa(), 100000);
    }

}
