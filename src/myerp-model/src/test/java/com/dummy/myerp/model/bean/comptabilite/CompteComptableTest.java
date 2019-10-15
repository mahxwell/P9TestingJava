package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class CompteComptableTest {

    private static CompteComptable compteComptable;

    @Test
    public void IntializeCompteComptable() {
        CompteComptable compteComptable = new CompteComptable(123, "Test");

        CompteComptable compteComptable2 = new CompteComptable(123, "Test");

        Assert.assertEquals(compteComptable.toString(), compteComptable2.toString());
    }

    @Test
    public void getNumero() {
        CompteComptable compteComptable = new CompteComptable(123, "Test");
        CompteComptable compteComptable2 = new CompteComptable(123, "Test");

        final Integer numero1 = compteComptable.getNumero();
        final Integer numero2 = compteComptable2.getNumero();

        Assert.assertEquals(numero1, numero2);
    }

    @Test
    public void getLibelle() {
        CompteComptable compteComptable = new CompteComptable(123, "Test");
        CompteComptable compteComptable2 = new CompteComptable(123, "Test");

        final String libelle1 = compteComptable.getLibelle();
        final String libelle2 = compteComptable2.getLibelle();

        Assert.assertEquals(libelle1, libelle2);
    }


    @Test
    public void getByNumero() {

        CompteComptable compteComptable1 = new CompteComptable(123, "Test");
        CompteComptable compteComptable2 = new CompteComptable(456, "Test");
        CompteComptable compteComptable3 = new CompteComptable(789, "Test");

        List<CompteComptable> compteComptables = new ArrayList<>();

        compteComptables.add(compteComptable1);
        compteComptables.add(compteComptable2);
        compteComptables.add(compteComptable3);

        compteComptable = compteComptable.getByNumero(compteComptables, 456);

        Assert.assertEquals(compteComptable.toString(), compteComptable2.toString());
    }

    @Test
    public void testToString() {

        CompteComptable compte = new CompteComptable();

        compte.setLibelle("testlib");
        compte.setNumero(13);

        Assert.assertEquals(compte.toString(),
                "CompteComptable{numero=13, libelle='testlib'}");

    }

}