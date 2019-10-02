package com.dummy.myerp.consumer.daotest;

import com.dummy.myerp.model.bean.comptabilite.*;
import com.dummy.myerp.technical.exception.NotFoundException;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.testconsumer.consumer.ConsumerTestCase;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class ComptabiliteDaoImplTest extends ConsumerTestCase {

    private ComptabiliteDao comptabiliteDao = getDaoProxy().getComptabiliteDao();
    private EcritureComptable ecritureComptableM = new EcritureComptable();
    private int year = 0;

    public void getInitEC() {
        Date currentDate = new Date();
        year = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault()).toLocalDate().getYear();
        ecritureComptableM.setJournal(new JournalComptable("OD", "Test"));
        ecritureComptableM.setReference("MM-2019/77777");
        ecritureComptableM.setDate(currentDate);
        ecritureComptableM.setLibelle("Test");

        ecritureComptableM.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(4456),
                "Test1", new BigDecimal(13), new BigDecimal(13)));
        ecritureComptableM.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(4457),
                "Test2", new BigDecimal(13), new BigDecimal(13)));
        ecritureComptableM.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(512),
                "Test3", new BigDecimal(13), new BigDecimal(13)));

    }


    @Test
    public void getListCompteComptable() {
        List<CompteComptable> compteComptables = comptabiliteDao.getListCompteComptable();

        /**
         * Assert Test
         */
        Assert.assertTrue(compteComptables.size() > 0);
    }


    @Test
    public void getListJournalComptable() {
        List<JournalComptable> journalComptables = comptabiliteDao.getListJournalComptable();

        /**
         * Assert Test
         */
        Assert.assertTrue(journalComptables.size() > 0);
    }


    @Test
    public void getListEcritureComptable() {
        List<EcritureComptable> ecritureComptables = comptabiliteDao.getListEcritureComptable();

        /**
         * Assert Test
         */
        Assert.assertTrue(ecritureComptables.size() > 0);
    }

    @Test
    public void getEcritureComptable() throws NotFoundException {
        EcritureComptable ecritureComptable = comptabiliteDao.getEcritureComptable(-5);

        /**
         * Assert Test
         */
        Assert.assertNotNull(ecritureComptable);
    }

    @Test
    public void getEcritureComptableByRef() throws NotFoundException {
        EcritureComptable ecritureComptable = comptabiliteDao.getEcritureComptableByRef("BQ-2016/00003");

        /**
         * Assert Test
         */
        Assert.assertNotNull(ecritureComptable);
    }

    @Test
    public void loadListLigneEcriture() {
        EcritureComptable ecritureComptable = new EcritureComptable();
        ecritureComptable.setId(-3);

        comptabiliteDao.loadListLigneEcriture(ecritureComptable);

        /**
         * Assert Test
         */
        Assert.assertEquals(2, ecritureComptable.getListLigneEcriture().size());
    }

    @Test
    public void insertEcritureComptable() throws NotFoundException {

        /**
         * Initialize Obj
         */
        getInitEC();

        comptabiliteDao.insertEcritureComptable(ecritureComptableM);

        /**
         * Get Previously created ecriture comptable obj
         */
        EcritureComptable ecritureComptable = comptabiliteDao.getEcritureComptableByRef("MM-" + year + "/77777");


        /**
         * Assert Test
         */
        Assert.assertEquals(ecritureComptableM.getReference(), ecritureComptable.getReference());
        Assert.assertEquals(ecritureComptableM.getLibelle(), ecritureComptable.getLibelle());

        /**
         * Delete previous inserted Values
         */
        comptabiliteDao.deleteEcritureComptable(ecritureComptable.getId());

    }


    @Test
    public void updateEcritureComptable() throws NotFoundException {

        /**
         * Initialize Obj
         */
        getInitEC();

        /**
         * Insert new Object in Data Base
         */
        ecritureComptableM.setReference("NN-2019/9999");
        comptabiliteDao.insertEcritureComptable(ecritureComptableM);


        /**
         * Updating requested Object ->(With reference)
         */
        EcritureComptable ecritureComptable = comptabiliteDao.getEcritureComptableByRef("NN-2019/9999");

        ecritureComptable.setLibelle("updateTest");
        comptabiliteDao.updateEcritureComptable(ecritureComptable);

        /**
         * Assert Test
         */
        Assert.assertEquals("updateTest", ecritureComptable.getLibelle());

        /**
         * Delete Data in Data Base
         */
        comptabiliteDao.deleteEcritureComptable(ecritureComptable.getId());
    }

    @Test
    public void deleteEcritureComptable() throws NotFoundException {

        /**
         * Initialize Obj
         */
        getInitEC();

        /**
         * Insert new Object in Data Base
         */
        comptabiliteDao.insertEcritureComptable(ecritureComptableM);

        /**
         * Finding Obj with references
         */
        EcritureComptable ecritureComptable = comptabiliteDao.getEcritureComptableByRef("MM-2019/77777");

        /**
         * Assert Test
         */
        Assert.assertNotNull(ecritureComptable);

        /**
         * Delete Data in Data Base
         */
        comptabiliteDao.deleteEcritureComptable(ecritureComptable.getId());
    }

    @Test
    public void getSequenceEcritureComptable() throws NotFoundException {

        /**
         * Get Two sequence rows
         */
        SequenceEcritureComptable sequence1 = comptabiliteDao.getSequenceEcritureComptable("AC", 2016);
        SequenceEcritureComptable sequence2 = comptabiliteDao.getSequenceEcritureComptable("AC", 2016);

        /**
         * Check if they are null
         */
        Assert.assertNotNull(sequence1);
        Assert.assertNotNull(sequence2);

        /**
         * Check if they are equal
         */
        Assert.assertEquals(sequence1.toString(), sequence2.toString());
    }

    @Test
    public void insertSequenceEcritureComptable() throws NotFoundException {

        SequenceEcritureComptable sequence = new SequenceEcritureComptable();

        /**
         * Set a sequence
         */
        sequence.setDerniereValeur(666);
        sequence.setAnnee(1998);

        /**
         * Insert sequence
         */
        comptabiliteDao.insertSequenceEcritureComptable(sequence, "VE");


        /**
         * Get sequence previously created
         */
        SequenceEcritureComptable sequence2 = comptabiliteDao.getSequenceEcritureComptable("VE", 1998);

        /**
         * Assert test
         */
        String test = "666";
        Assert.assertEquals(test, sequence2.getDerniereValeur().toString());

        /**
         * Delete sequence previously created
         */
        comptabiliteDao.deleteSequenceEcritureComptable(sequence2, "VE");
    }

    @Test
    public void updateSequenceEcritureComptable() throws NotFoundException {

        /**
         * get sequence
         */
        SequenceEcritureComptable sequence = comptabiliteDao.getSequenceEcritureComptable("VE", 2016);

        /**
         * Set sequence
         */
        sequence.setDerniereValeur(666);

        /**
         * Update sequence previously got
         */
        comptabiliteDao.updateSequenceEcritureComptable(sequence, "VE");


        /**
         * Get again same sequence
         */
        sequence = comptabiliteDao.getSequenceEcritureComptable("VE", 2016);

        /**
         * Assert test, check if modifications are done
         */
        String test = "666";
        Assert.assertEquals(test, sequence.getDerniereValeur().toString());


        /**
         * Put value back to original one (41)
         */
        sequence = comptabiliteDao.getSequenceEcritureComptable("VE", 2016);

        sequence.setDerniereValeur(41);
        comptabiliteDao.updateSequenceEcritureComptable(sequence, "VE");

    }

    @Test
    public void deleteSequenceEcritureComptable() throws NotFoundException {
        SequenceEcritureComptable sequence = new SequenceEcritureComptable();

        /**
         * Set a sequence
         */
        sequence.setDerniereValeur(666);
        sequence.setAnnee(1998);

        /**
         * Insert sequence
         */
        comptabiliteDao.insertSequenceEcritureComptable(sequence, "VE");


        /**
         * Get sequence previously created
         */
        SequenceEcritureComptable sequence2 = comptabiliteDao.getSequenceEcritureComptable("VE", 1998);

        /**
         * Assert test
         */
        String test = "666";
        Assert.assertEquals(test, sequence2.getDerniereValeur().toString());

        /**
         * Delete sequence previously created
         */
        comptabiliteDao.deleteSequenceEcritureComptable(sequence2, "VE");

    }
}