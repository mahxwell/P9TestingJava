package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class LigneEcritureComptableTest {

    @Test
    public void testToString() {

        LigneEcritureComptable line = new LigneEcritureComptable();


        line.setCompteComptable(null);
        line.setLibelle("testlib");

        BigDecimal bd = BigDecimal.valueOf(10);
        line.setCredit(bd);
        line.setDebit(bd);

        Assert.assertEquals(line.toString(),
                "LigneEcritureComptable{compteComptable=null, libelle='testlib', debit=10, credit=10}");
    }
}