package com.dummy.myerp.model.bean.comptabilite;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Assert;
import org.junit.Test;


public class EcritureComptableTest {


    // ============ Initialize ================

    private LigneEcritureComptable createLigne(Integer pCompteComptableNumero, String pDebit, String pCredit) {
        BigDecimal vDebit = pDebit == null ? null : new BigDecimal(pDebit);
        BigDecimal vCredit = pCredit == null ? null : new BigDecimal(pCredit);
        String vLibelle = ObjectUtils.defaultIfNull(vDebit, BigDecimal.ZERO)
                .subtract(ObjectUtils.defaultIfNull(vCredit, BigDecimal.ZERO)).toPlainString();
        LigneEcritureComptable vRetour = new LigneEcritureComptable(new CompteComptable(pCompteComptableNumero),
                vLibelle,
                vDebit, vCredit);
        return vRetour;
    }

    // ============ Getters / Setters ================

    public EcritureComptable ecritureComptableInitializer() {

        EcritureComptable ecritureComptableM = new EcritureComptable();

        ecritureComptableM.getListLigneEcriture().add(this.createLigne(1, "200.50", "250"));
        ecritureComptableM.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
        ecritureComptableM.getListLigneEcriture().add(this.createLigne(2, null, "301"));
        ecritureComptableM.getListLigneEcriture().add(this.createLigne(2, "40", "100"));

        return ecritureComptableM;
    }

    @Test
    public void getId() {

        EcritureComptable ecritureComptable1 = ecritureComptableInitializer();
        EcritureComptable ecritureComptable2 = ecritureComptableInitializer();

        Assert.assertEquals(ecritureComptable1.getId(), ecritureComptable2.getId());
    }

    @Test
    public void setId() {

        EcritureComptable ecritureComptable1 = new EcritureComptable();
        EcritureComptable ecritureComptable2 = new EcritureComptable();

        ecritureComptable1.setId(12);
        ecritureComptable2.setId(12);

        Assert.assertEquals(ecritureComptable1.getId(), ecritureComptable2.getId());

    }

    @Test
    public void getJournal() {

        EcritureComptable ecritureComptable1 = ecritureComptableInitializer();
        EcritureComptable ecritureComptable2 = ecritureComptableInitializer();

        JournalComptable journalComptable1 = new JournalComptable("123", "777");
        JournalComptable journalComptable2 = new JournalComptable("123", "777");

        Assert.assertEquals(ecritureComptable1.getJournal(), ecritureComptable2.getJournal());
    }

    @Test
    public void setJournal() {

        EcritureComptable ecritureComptable1 = new EcritureComptable();
        EcritureComptable ecritureComptable2 = new EcritureComptable();

        JournalComptable journalComptable1 = new JournalComptable("123", "777");
        JournalComptable journalComptable2 = new JournalComptable("123", "777");


        ecritureComptable1.setJournal(journalComptable1);
        ecritureComptable2.setJournal(journalComptable2);

        Assert.assertEquals(ecritureComptable1.getJournal().toString(), ecritureComptable2.getJournal().toString());

    }

    @Test
    public void getReference() {

        EcritureComptable ecritureComptable1 = ecritureComptableInitializer();
        EcritureComptable ecritureComptable2 = ecritureComptableInitializer();

        Assert.assertEquals(ecritureComptable1.getReference(), ecritureComptable2.getReference());
    }

    @Test
    public void setReference() {

        EcritureComptable ecritureComptable1 = new EcritureComptable();
        EcritureComptable ecritureComptable2 = new EcritureComptable();

        ecritureComptable1.setReference("TestRef");
        ecritureComptable2.setReference("TestRef");

        Assert.assertEquals(ecritureComptable1.getReference(), ecritureComptable2.getReference());

    }

    @Test
    public void getDate() {

        EcritureComptable ecritureComptable1 = new EcritureComptable();
        EcritureComptable ecritureComptable2 = new EcritureComptable();

        Date date = new Date();

        ecritureComptable1.setDate(date);
        ecritureComptable2.setDate(date);

        Assert.assertEquals(ecritureComptable1.getDate().toString(), ecritureComptable2.getDate().toString());
    }

    @Test
    public void setDate() {

        EcritureComptable ecritureComptable1 = new EcritureComptable();
        EcritureComptable ecritureComptable2 = new EcritureComptable();

        Date date = new Date();

        ecritureComptable1.setDate(date);
        ecritureComptable2.setDate(date);

        Assert.assertEquals(ecritureComptable1.getDate().toString(), ecritureComptable2.getDate().toString());
    }

    @Test
    public void getLibelle() {

        EcritureComptable ecritureComptable1 = ecritureComptableInitializer();
        EcritureComptable ecritureComptable2 = ecritureComptableInitializer();

        Assert.assertEquals(ecritureComptable1.getLibelle(), ecritureComptable2.getLibelle());

    }


    // ============ Methods ==========================

    @Test
    public void isEquilibree() {
        EcritureComptable vEcriture;
        vEcriture = new EcritureComptable();

        vEcriture.setLibelle("Equilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "301"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "40", "7"));
        Assert.assertTrue(vEcriture.toString(), vEcriture.isEquilibree());

        vEcriture.getListLigneEcriture().clear();
        vEcriture.setLibelle("Non équilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "10", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "20", "1"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "30"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "1", "2"));
        Assert.assertFalse(vEcriture.toString(), vEcriture.isEquilibree());
    }

    @Test
    public void getTotalDebit() {

        EcritureComptable vEcriture = new EcritureComptable();

        vEcriture.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "301"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "40", "7"));

        BigDecimal vRetour = BigDecimal.ZERO;
        for (LigneEcritureComptable vLigneEcritureComptable : vEcriture.getListLigneEcriture()) {
            if (vLigneEcritureComptable.getDebit() != null) {
                vRetour = vRetour.add(vLigneEcritureComptable.getDebit());
            }
        }

        Assert.assertEquals(vEcriture.getTotalDebit(), vRetour);
    }

    @Test
    public void getTotalCredit() {

        EcritureComptable vEcriture = new EcritureComptable();

        vEcriture.getListLigneEcriture().add(this.createLigne(1, "200.50", "250"));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "301"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "40", "100"));

        BigDecimal vRetour = BigDecimal.ZERO;
        for (LigneEcritureComptable vLigneEcritureComptable : vEcriture.getListLigneEcriture()) {
            if (vLigneEcritureComptable.getCredit() != null) {
                vRetour = vRetour.add(vLigneEcritureComptable.getCredit());
            }
        }

        Assert.assertEquals(vEcriture.getTotalCredit(), vRetour);
    }
}
