package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JournalComptableTest {

    private static JournalComptable journalComptable;

    @Test
    public void initializeJournalComptable() {

        JournalComptable journalComptable1 = new JournalComptable("Test", "Test");
        JournalComptable journalComptable2 = new JournalComptable("Test", "Test");

        Assert.assertEquals(journalComptable1.toString(), journalComptable2.toString());
    }

    @Test
    public void getCode() {
        JournalComptable journalComptable1 = new JournalComptable("Test", "Test");
        JournalComptable journalComptable2 = new JournalComptable("Test", "Test");

        Assert.assertEquals(journalComptable1.getCode(), journalComptable2.getCode());
    }

    @Test
    public void getLibelle() {

        JournalComptable journalComptable1 = new JournalComptable("Test", "Test");
        JournalComptable journalComptable2 = new JournalComptable("Test", "Test");

        Assert.assertEquals(journalComptable1.getLibelle(), journalComptable2.getLibelle());
    }

    @Test
    public void getByCode() {
        JournalComptable journalComptable1 = new JournalComptable("Test1", "Test");
        JournalComptable journalComptable2 = new JournalComptable("Test2", "Test");
        JournalComptable journalComptable3 = new JournalComptable("Test3", "Test");

        List<JournalComptable> journalComptables = new ArrayList<>();

        journalComptables.add(journalComptable1);
        journalComptables.add(journalComptable2);
        journalComptables.add(journalComptable3);

        journalComptable = journalComptable.getByCode(journalComptables, "Test2");

        Assert.assertEquals(journalComptable2.getCode(), journalComptable.getCode());
    }

    @Test
    public void testToString() {

        JournalComptable journal = new JournalComptable();
        journal.setLibelle("testlib");
        journal.setCode("ABC");

        Assert.assertEquals(journal.toString(),
                "JournalComptable{code='ABC', libelle='testlib'}");
    }
}