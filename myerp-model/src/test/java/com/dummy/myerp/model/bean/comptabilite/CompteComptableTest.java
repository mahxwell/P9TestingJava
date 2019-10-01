package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Test;


public class CompteComptableTest {

    @Test
    public void testToString() {

        CompteComptable compte = new CompteComptable();

        compte.setLibelle("testlib");
        compte.setNumero(13);

        Assert.assertEquals(compte.toString(),
                "CompteComptable{numero=13, libelle='testlib'}");

    }

}