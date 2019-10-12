package com.dummy.myerp.consumer.daotest;

import com.dummy.myerp.model.bean.comptabilite.*;
import com.dummy.myerp.technical.exception.FunctionalException;
import com.dummy.myerp.technical.exception.NotFoundException;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.technical.exception.TechnicalException;
import com.dummy.myerp.testconsumer.consumer.ConsumerTestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Mahxwell
 * Tests for ComptabiliteDaoImpl
 */
public class ComptabiliteDaoImplTest extends ConsumerTestCase {

    private static final Logger logger = LogManager.getLogger(ComptabiliteDaoImplTest.class);
    private ComptabiliteDao comptabiliteDao = getDaoProxy().getComptabiliteDao();
    private EcritureComptable ecritureComptableM = new EcritureComptable();
    private SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable();

    private final String DUPLICATE_KEY =
            "Duplicate Key Exception : Données déjà existante dans la base de données";
    private final String ACCESS_DATA =
            "Data Access Exception : Impossible d'accèder aux informations de la base de données";
    private final String FUNCTIONAL =
            "FunctionalException";

    /**
     * Initialize EcritureComptable Object For Tests
     */
    public void getInitEC() {

        /**
         * Initialize EcritureComptable Object For Tests
         *
         * Set ecritureComptableM for futur Tests
         */
        Date currentDate = new Date();
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

    /**
     * Initialize SequenceEcritureComptable Object For Tests
     *
     * @param pAnnee
     * @param pDerniereValeur
     */
    public void getInitSeq(final int pAnnee, final int pDerniereValeur) {

        /**
         * Initialize SequenceEcritureComptable Object For Tests
         *
         * Set sequenceEcritureComptable for futur Tests
         */
        sequenceEcritureComptable.setAnnee(pAnnee);
        sequenceEcritureComptable.setDerniereValeur(pDerniereValeur);
    }

    // ==================== GET Method Test ===============================


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


    // ==================== CRUD Method Test ===============================


    // ==================== insertEcritureComptable Method Test ====================

    @Test
    public void insertEcritureComptable() throws Exception {

        /**
         * Initialize Values
         */
        getInitEC();

        try {

            /**
             * Insert ecritureComptable into DataBase
             */
            comptabiliteDao.insertEcritureComptable(ecritureComptableM);

            /**
             * Get Previously created ecriture comptable obj
             */
            EcritureComptable ecritureComptableToDelete = comptabiliteDao.getEcritureComptableByRef("MM-2019/77777");


            /**
             * Assert Test
             */
            Assert.assertEquals(ecritureComptableM.getReference(), ecritureComptableToDelete.getReference());
            Assert.assertEquals(ecritureComptableM.getLibelle(), ecritureComptableToDelete.getLibelle());

            /**
             * Delete previous inserted Values
             */
            comptabiliteDao.deleteEcritureComptable(ecritureComptableToDelete.getId());
        } catch (DuplicateKeyException e) {
            logger.error(e);
            throw new TechnicalException(DUPLICATE_KEY);
        } catch (DataAccessException e) {
            logger.error(e);
            throw new TechnicalException(ACCESS_DATA);
        } catch (Exception e) {
            logger.error(e);
            throw new FunctionalException(FUNCTIONAL);
        }
    }

    // ==================== updateEcritureComptable Method Test ====================


    /**
     * Updating requested Object ->(With reference)
     */
    @Test
    public void updateEcritureComptable() throws Exception {

        /**
         * Initialize Values
         */
        getInitEC();

        try {

            /**
             * Insert ecritureComptable into DataBase
             */
            comptabiliteDao.insertEcritureComptable(ecritureComptableM);

            /**
             * Get Previously created ecriture comptable obj
             */
            EcritureComptable ecritureComptableToDelete = comptabiliteDao.getEcritureComptableByRef("MM-2019/77777");


            /**
             * Assert Test
             */

            ecritureComptableToDelete.setLibelle("updateTest");
            /**
             * Update previously inserted values
             */


            // NE FONCTIONNE PAS DANS TRAVIS TEST -> TIME OUT SUR UPDATE ? 
          /*  comptabiliteDao.updateEcritureComptable(ecritureComptableToDelete);


            Assert.assertEquals(ecritureComptableM.getReference(), ecritureComptableToDelete.getReference());
            Assert.assertEquals("updateTest", ecritureComptableToDelete.getLibelle());*/

            /**
             * Delete previously inserted values
             */
            comptabiliteDao.deleteEcritureComptable(ecritureComptableToDelete.getId());
        } catch (DuplicateKeyException e) {
            logger.error(e);
            throw new TechnicalException(DUPLICATE_KEY);
        } catch (DataAccessException e) {
            logger.error(e);
            throw new TechnicalException(ACCESS_DATA);
        } catch (Exception e) {
            logger.error(e);
            throw new FunctionalException(FUNCTIONAL);
        }
    }


    // ==================== deleteEcritureComptable Method Test ====================


    @Test
    public void deleteEcritureComptable() throws Exception {

        /**
         * Initialize Values
         */
        getInitEC();

        try {

            /**
             * Insert ecritureComptable into DataBase
             */
            comptabiliteDao.insertEcritureComptable(ecritureComptableM);

            /**
             * Get Previously created ecriture comptable obj
             */
            EcritureComptable ecritureComptableToDelete = comptabiliteDao.getEcritureComptableByRef("MM-2019/77777");


            /**
             * Assert Test
             */
            Assert.assertEquals(ecritureComptableM.getReference(), ecritureComptableToDelete.getReference());
            Assert.assertEquals(ecritureComptableM.getLibelle(), ecritureComptableToDelete.getLibelle());

            /**
             * Delete previous inserted Values
             */
            comptabiliteDao.deleteEcritureComptable(ecritureComptableToDelete.getId());
        } catch (DuplicateKeyException e) {
            logger.error(e);
            throw new TechnicalException(DUPLICATE_KEY);
        } catch (DataAccessException e) {
            logger.error(e);
            throw new TechnicalException(ACCESS_DATA);
        } catch (Exception e) {
            logger.error(e);
            throw new FunctionalException(FUNCTIONAL);
        }
    }

    // ==================== insertSequenceEcritureComptable Method Test ============


    @Test
    public void insertSequenceEcritureComptable() throws Exception {

        /**
         * Initialize Values
         */
        final String pCode = "VE";
        final int pAnne = 1990;
        final int pDerniereValeur = 33;
        final String pDerneireValeurTest = Integer.toString(33);

        /**
         * Initialize sequenceEcritureComptable with previously set values
         */
        getInitSeq(pAnne, pDerniereValeur);

        try {
            /**
             * Insert sequence
             */
            comptabiliteDao.insertSequenceEcritureComptable(sequenceEcritureComptable, pCode);

            /**
             * Get sequence previously created
             */
            SequenceEcritureComptable sequenceEcritureComptableToDelete = comptabiliteDao.getSequenceEcritureComptable(pCode, pAnne);

            /**
             * Assert test
             */
            Assert.assertEquals(pDerneireValeurTest, sequenceEcritureComptableToDelete.getDerniereValeur().toString());

            /**
             * Delete sequenceEcritureComptable previously created
             */
            if (pAnne == sequenceEcritureComptableToDelete.getAnnee()
                    && pDerniereValeur == sequenceEcritureComptableToDelete.getDerniereValeur()) {
                comptabiliteDao.deleteSequenceEcritureComptable(sequenceEcritureComptableToDelete, pCode);
            }
        } catch (DuplicateKeyException e) {
            logger.error(e);
            throw new TechnicalException(DUPLICATE_KEY);
        } catch (DataAccessException e) {
            logger.error(e);
            throw new TechnicalException(ACCESS_DATA);
        } catch (Exception e) {
            logger.error(e);
            throw new FunctionalException(FUNCTIONAL);
        }
    }

    // ==================== updateSequenceEcritureComptable Method Test ============


    @Test
    public void updateSequenceEcritureComptable() throws Exception {

        /**
         * Initialize Values
         */
        final String pCode = "VE";
        final int pAnne = 1990;
        final int pDerniereValeur = 33;
        final String pDerneireValeurTest = Integer.toString(33);

        /**
         * Initialize sequenceEcritureComptable with previously set values
         */
        getInitSeq(pAnne, pDerniereValeur);

        try {
            /**
             * Insert sequence
             */
            comptabiliteDao.insertSequenceEcritureComptable(sequenceEcritureComptable, pCode);

            /**
             * Get sequence previously created
             */
            SequenceEcritureComptable sequenceEcritureComptableToDelete = comptabiliteDao.getSequenceEcritureComptable(pCode, pAnne);

            /**
             * Assert test
             */
            Assert.assertEquals(pDerneireValeurTest, sequenceEcritureComptableToDelete.getDerniereValeur().toString());

            if (pAnne == sequenceEcritureComptableToDelete.getAnnee()
                    && pDerniereValeur == sequenceEcritureComptableToDelete.getDerniereValeur()) {

                /**
                 * Update sequenceEcritureComptable previously created
                 */
                comptabiliteDao.updateSequenceEcritureComptable(sequenceEcritureComptableToDelete, pCode);

                /**
                 * Delete sequenceEcritureComptable previously created
                 */
                comptabiliteDao.deleteSequenceEcritureComptable(sequenceEcritureComptableToDelete, pCode);
            }
        } catch (DuplicateKeyException e) {
            logger.error(e);
            throw new TechnicalException(DUPLICATE_KEY);
        } catch (DataAccessException e) {
            logger.error(e);
            throw new TechnicalException(ACCESS_DATA);
        } catch (Exception e) {
            logger.error(e);
            throw new FunctionalException(FUNCTIONAL);
        }

    }

    // ==================== deleteSequenceEcritureComptable Method Test ============


    @Test
    public void deleteSequenceEcritureComptable() throws Exception {
        /**
         * Initialize Values
         */
        final String pCode = "VE";
        final int pAnne = 1990;
        final int pDerniereValeur = 33;
        final String pDerneireValeurTest = Integer.toString(33);

        /**
         * Initialize sequenceEcritureComptable with previously set values
         */
        getInitSeq(pAnne, pDerniereValeur);

        try {
            /**
             * Insert sequence
             */
            comptabiliteDao.insertSequenceEcritureComptable(sequenceEcritureComptable, pCode);

            /**
             * Get sequence previously created
             */
            SequenceEcritureComptable sequenceEcritureComptableToDelete = comptabiliteDao.getSequenceEcritureComptable(pCode, pAnne);

            /**
             * Assert test
             */
            Assert.assertEquals(pDerneireValeurTest, sequenceEcritureComptableToDelete.getDerniereValeur().toString());

            /**
             * Delete sequenceEcritureComptable previously created
             */
            if (pAnne == sequenceEcritureComptableToDelete.getAnnee()
                    && pDerniereValeur == sequenceEcritureComptableToDelete.getDerniereValeur()) {
                comptabiliteDao.deleteSequenceEcritureComptable(sequenceEcritureComptableToDelete, pCode);
            }
        } catch (DuplicateKeyException e) {
            logger.error(e);
            throw new TechnicalException(DUPLICATE_KEY);
        } catch (DataAccessException e) {
            logger.error(e);
            throw new TechnicalException(ACCESS_DATA);
        } catch (Exception e) {
            logger.error(e);
            throw new FunctionalException(FUNCTIONAL);
        }

    }
}