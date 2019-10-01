package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Test;

public class JournalComptableTest {

    @Test
    public void testToString() {

        JournalComptable journal = new JournalComptable();
        journal.setLibelle("testlib");
        journal.setCode("ABC");

        Assert.assertEquals(journal.toString(),
                "JournalComptable{code='ABC', libelle='testlib'}");
    }
}