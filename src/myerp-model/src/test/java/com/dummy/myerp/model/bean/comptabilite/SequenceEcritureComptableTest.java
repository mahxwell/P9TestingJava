package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Test;

public class SequenceEcritureComptableTest {

    @Test
    public void testToString() {

        SequenceEcritureComptable sequence = new SequenceEcritureComptable();
        sequence.setAnnee(1998);
        sequence.setDerniereValeur(7);

        Assert.assertEquals(sequence.toString(),
                "SequenceEcritureComptable{annee=1998, derniereValeur=7}");
    }
}