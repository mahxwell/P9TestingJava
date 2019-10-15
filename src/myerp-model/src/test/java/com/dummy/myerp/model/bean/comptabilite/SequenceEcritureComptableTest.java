package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Test;

public class SequenceEcritureComptableTest {

    @Test
    public void initializeSequenceEcritureComptable() {

        SequenceEcritureComptable sequenceEcritureComptable1 = new SequenceEcritureComptable(2000, 33);
        SequenceEcritureComptable sequenceEcritureComptable2 = new SequenceEcritureComptable(2000, 33);


        Assert.assertEquals(sequenceEcritureComptable1.toString(), sequenceEcritureComptable2.toString());
    }

    @Test
    public void getAnnee() {
        SequenceEcritureComptable sequenceEcritureComptable1 = new SequenceEcritureComptable(2000, 33);
        SequenceEcritureComptable sequenceEcritureComptable2 = new SequenceEcritureComptable(2000, 33);


        Assert.assertEquals(sequenceEcritureComptable1.getAnnee(), sequenceEcritureComptable2.getAnnee());
    }

    @Test
    public void getDerniereValeur() {
        SequenceEcritureComptable sequenceEcritureComptable1 = new SequenceEcritureComptable(2000, 33);
        SequenceEcritureComptable sequenceEcritureComptable2 = new SequenceEcritureComptable(2000, 33);


        Assert.assertEquals(sequenceEcritureComptable1.getDerniereValeur(), sequenceEcritureComptable2.getDerniereValeur());
    }


    @Test
    public void testToString() {

        SequenceEcritureComptable sequence = new SequenceEcritureComptable();
        sequence.setAnnee(1998);
        sequence.setDerniereValeur(7);

        Assert.assertEquals(sequence.toString(),
                "SequenceEcritureComptable{annee=1998, derniereValeur=7}");
    }
}