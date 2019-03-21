package com.mycompany.unicafe;

public class Paaohjelma {

    public static void main(String[] args) {
        
        Maksukortti testiKortti = new Maksukortti(1000);
        System.out.println(testiKortti.toString());
        
        
        
        
        Kassapaate unicafeExactum = new Kassapaate();
        Maksukortti kortti = new Maksukortti(10000);
        
        unicafeExactum.syoEdullisesti(kortti);
        
        System.out.println( unicafeExactum.edullisiaLounaitaMyyty() );
        System.out.println(kortti);
    }
}
