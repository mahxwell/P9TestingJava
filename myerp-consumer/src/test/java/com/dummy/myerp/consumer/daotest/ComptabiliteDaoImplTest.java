package com.dummy.myerp.consumer.daotest;

import com.dummy.myerp.technical.exception.NotFoundException;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
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
         * Delete previous inserted Values
         */
        EcritureComptable ecritureBis = comptabiliteDao.getEcritureComptableByRef("MM-" + year + "/77777");

        comptabiliteDao.deleteEcritureComptable(ecritureBis.getId());

        /**
         * Assert Test
         */
        Assert.assertEquals(ecritureComptableM.getReference(), ecritureBis.getReference());
        Assert.assertEquals(ecritureComptableM.getLibelle(), ecritureBis.getLibelle());
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
        EcritureComptable ecriture = comptabiliteDao.getEcritureComptableByRef("NN-2019/9999");
        ecriture.setLibelle("updateTest");
        comptabiliteDao.updateEcritureComptable(ecriture);

        /**
         * Assert Test
         */
        Assert.assertEquals("updateTest", ecriture.getLibelle());

        /**
         * Delete Data in Data Base
         */
        comptabiliteDao.deleteEcritureComptable(ecriture.getId());
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
}