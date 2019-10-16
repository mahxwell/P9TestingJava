package com.dummy.myerp.business.impl.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.dummy.myerp.model.bean.comptabilite.*;
import com.dummy.myerp.technical.exception.TechnicalException;
import com.dummy.myerp.testbusiness.business.BusinessTestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import com.dummy.myerp.technical.exception.FunctionalException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;

/**
 * @author Mahxwell
 * Tests for ComptabiliteManagerImpl
 */
public class ComptabiliteManagerImplTest extends BusinessTestCase {

    private static final Logger logger = LogManager.getLogger(ComptabiliteManagerImplTest.class);
    private ComptabiliteManagerImpl comptabiliteManager = new ComptabiliteManagerImpl();
    private EcritureComptable ecritureComptableM = new EcritureComptable();
    private SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable();

    private final String DUPLICATE_KEY =
            "Duplicate Key Exception : Données déjà existante dans la base de données";
    private final String ACCESS_DATA =
            "Data Access Exception : Impossible d'accèder aux informations de la base de données";
    private final String FUNCTIONAL =
            "FunctionalException";

    // ==================== INITIALIZE =============================================

    /**
     * Initialize EcritureComptable Object For Tests
     *
     * @param pCode
     * @param pLibelle
     * @param libelle
     * @param reference
     * @param pNumero1
     * @param value1
     * @param pNumero2
     * @param value2
     */
    public void getInitEC(final String pCode, final String pLibelle, final String libelle,
                          final String reference, final Integer pNumero1, final Integer value1,
                          final Integer pNumero2, final Integer value2) {

        /**
         * Initialize EcritureComptable Object For Tests
         *
         * Set ecritureComptableM for futur Tests
         */
        ecritureComptableM.setJournal(new JournalComptable(pCode, pLibelle));
        ecritureComptableM.setDate(new Date());
        ecritureComptableM.setLibelle(libelle);
        ecritureComptableM.setReference(reference);
        ecritureComptableM.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(pNumero1),
                null, new BigDecimal(value1),
                null));
        ecritureComptableM.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(pNumero2),
                null, null,
                new BigDecimal(value2)));
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

    // ==================== Getters and Setters Method Test ========================


    @Test
    public void getListComptable() {

        List<CompteComptable> compteComptables = comptabiliteManager.getListCompteComptable();

        List<CompteComptable> compteComptables2 = comptabiliteManager.getListCompteComptable();

        Assert.assertEquals(compteComptables.toString(), compteComptables2.toString());
    }

    @Test
    public void getListJournalComptable() {

        List<JournalComptable> journalComptables = comptabiliteManager.getListJournalComptable();

        List<JournalComptable> journalComptables2 = comptabiliteManager.getListJournalComptable();

        Assert.assertEquals(journalComptables.toString(), journalComptables2.toString());
    }

    @Test
    public void getListEcritureComptable() {

        List<EcritureComptable> ecritureComptables = comptabiliteManager.getListEcritureComptable();

        List<EcritureComptable> ecritureComptables2 = comptabiliteManager.getListEcritureComptable();

        Assert.assertEquals(ecritureComptables.toString(), ecritureComptables2.toString());
    }


    // ==================== addReference Method Test ===============================

    @Test
    public void addReference() throws Exception {

        /**
         * Initialize ecritureComptableM with arguments
         */
        getInitEC("AC", "Achat",
                "Libelle", "AC-2019/00001",
                401, 666, 411, 666);

        /**
         * Add set reference of ecritureComptableM
         */
        comptabiliteManager.addReference(ecritureComptableM);
    }

    @Test
    public void addReferenceExisting() throws Exception {

        /**
         * Initialize ecritureComptableM with arguments
         */
        getInitEC("AC", "Achat",
                "Libelle", "AC-2016/00001",
                401, 666, 411, 666);

        /**
         * Add set reference of ecritureComptableM
         */
        comptabiliteManager.addReference(ecritureComptableM);
    }


    // ==================== checkEcritureComptableUnit Method Test =================

    @Test
    public void checkEcritureComptableUnit() throws Exception {

        /**
         * Initialize ecritureComptableM with arguments
         */
        getInitEC("AC", "Achat",
                "Libelle", "AC-2019/00001",
                401, 666, 411, 666);

        /**
         * Check ecritureComptableM
         */
        comptabiliteManager.checkEcritureComptableUnit(ecritureComptableM);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitViolation() throws Exception {

        /**
         * Initialize ecritureComptableM with arguments
         */
        getInitEC("AC", "Achat",
                "Libelle", "YOLOLOLO",
                401, 666, 411, 666);

        /**
         * Error example with wrong reference
         */
        comptabiliteManager.checkEcritureComptableUnit(ecritureComptableM);
    }

    /**
     * Test for juste ONE ligneEcritureComptable
     *
     * @throws Exception
     */
    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitLigne() throws Exception {

        EcritureComptable ecritureComptable = new EcritureComptable();

        /**
         * Initialize EcritureComptable Object For Tests
         *
         * Set ecritureComptableM for futur Tests
         */
        ecritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        ecritureComptable.setDate(new Date());
        ecritureComptable.setLibelle("Libelle");
        ecritureComptable.setReference("YOLOLOLO");
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),
                null, new BigDecimal(666),
                null));

        comptabiliteManager.checkEcritureComptableUnit(ecritureComptable);

    }

    // ==================== checkEcritureComptableUnitRG2 Method Test ==============

    @Test
    public void checkEcritureComptableUnitRG2() throws Exception {

        /**
         * Initialize ecritureComptableM with arguments
         */
        getInitEC("AC", "Achat",
                "Libelle", "AC-2019/00001",
                401, 666, 411, 666);

        /**
         * Check ecritureComptable with Gestion Rule 2
         */
        comptabiliteManager.checkEcritureComptableUnit(ecritureComptableM);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG2Violation() throws Exception {

        /**
         * Initialize ecritureComptableM with arguments
         */
        getInitEC("AC", "Achat",
                "Libelle", "AC-2019/00001",
                401, 555, 411, 666);

        /**
         * Error in Gestion Rule 2 example
         */
        comptabiliteManager.checkEcritureComptableUnit(ecritureComptableM);
    }

    // ==================== checkEcritureComptableUnitRG3 Method Test ==============

    @Test
    public void checkEcritureComptableUnitRG3() throws Exception {

        /**
         * Initialize ecritureComptableM with arguments
         */
        getInitEC("AC", "Achat",
                "Libelle", "AC-2019/00001",
                401, 666, 411, 666);

        /**
         * Check ecritureComptable with Gestion Rule 3
         */
        comptabiliteManager.checkEcritureComptableUnit(ecritureComptableM);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG3Violation() throws Exception {

        /**
         * Initialize ecritureComptableM with arguments
         */
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));

        /**
         * Error in Gestion Rule 3 example
         */
        comptabiliteManager.checkEcritureComptableUnit(vEcritureComptable);
    }


    // ==================== checkEcritureComptableUnitRG5 Method Test ==============

    @Test
    public void checkEcritureComptableUnitRG5() throws Exception {

        /**
         * Initialize ecritureComptableM with arguments
         */
        getInitEC("AC", "Achat",
                "Libelle", "AC-2019/00001",
                401, 666, 411, 666);

        /**
         * Check ecritureComptable with Gestion Rule 5
         */
        comptabiliteManager.checkEcritureComptableUnit(ecritureComptableM);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG5Violation1() throws Exception {

        /**
         * Initialize ecritureComptableM with arguments
         */
        getInitEC("AC", "Achat",
                "Libelle", "OD-2019/00001",
                401, 666, 411, 666);

        /**
         * Error in Gestion Rule 5 example
         */
        comptabiliteManager.checkEcritureComptableUnit(ecritureComptableM);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG5Violation2() throws Exception {

        /**
         * Initialize ecritureComptableM with arguments
         */
        getInitEC("AC", "Achat",
                "Libelle", "AC-1998/00001",
                401, 666, 411, 666);

        /**
         * Error in Gestion Rule 5 example
         */
        comptabiliteManager.checkEcritureComptableUnit(ecritureComptableM);
    }

    // ==================== checkEcritureComptableContext Method Test ==============

    @Test
    public void checkEcritureComptableContext() throws Exception {

        /**
         * Initialize ecritureComptableM with arguments
         */
        getInitEC("AC", "Achat",
                "Libelle", "AC-2019/00001",
                401, 666, 411, 666);

        /**
         * Check ecritureComptableContext with ecritureComptableM
         */
        comptabiliteManager.checkEcritureComptableContext(ecritureComptableM);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableContextExisting() throws Exception {

        /**
         * Initialize ecritureComptableM with arguments
         */
        getInitEC("AC", "Achat",
                "Libelle", "AC-2016/00001",
                401, 666, 411, 666);

        /**
         * Check ecritureComptableContext with ecritureComptableM
         */
        comptabiliteManager.checkEcritureComptableContext(ecritureComptableM);
    }

    // ==================== CRUD Method Test =======================================

    // ==================== insertEcritureComptable Method Test ====================

    @Test
    public void insertEcritureComptable() throws Exception {

        /**
         * Initialize ecritureComptableM with arguments
         */
        getInitEC("AC", "Achat",
                "Libelle", "AC-2019/00001",
                401, 666, 411, 666);

        try {

            /**
             * Insert ecritureComptable into DataBase
             */
            comptabiliteManager.insertEcritureComptable(ecritureComptableM);

            /**
             * Get Previously created ecriture comptable obj
             */
            EcritureComptable ecritureComptableToDelete = comptabiliteManager.getEcritureComptableByRef("AC-2019/00001");


            /**
             * Assert Test
             */
            Assert.assertEquals(ecritureComptableM.getReference(), ecritureComptableToDelete.getReference());
            Assert.assertEquals(ecritureComptableM.getLibelle(), ecritureComptableToDelete.getLibelle());

            /**
             * Delete previous inserted Values
             */
            comptabiliteManager.deleteEcritureComptable(ecritureComptableToDelete.getId());
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

    @Test
    public void updateEcritureComptable() throws Exception {

        /**
         * Initialize ecritureComptableM with arguments
         */
        getInitEC("AC", "Achat",
                "Libelle", "AC-2019/00001",
                401, 666, 411, 666);


        try {

            /**
             * Insert ecritureComptable into DataBase
             */
            comptabiliteManager.insertEcritureComptable(ecritureComptableM);

            /**
             * Get Previously created ecriture comptable obj
             */
            EcritureComptable ecritureComptableToDelete = comptabiliteManager.getEcritureComptableByRef("AC-2019/00001");


            /**
             * Assert Test
             */

            ecritureComptableToDelete.setLibelle("updateTest");
            /**
             * Update previously inserted values
             */
            comptabiliteManager.updateEcritureComptable(ecritureComptableToDelete);


            Assert.assertEquals(ecritureComptableM.getReference(), ecritureComptableToDelete.getReference());
            Assert.assertEquals("updateTest", ecritureComptableToDelete.getLibelle());

            /**
             * Delete previously inserted values
             */
            comptabiliteManager.deleteEcritureComptable(ecritureComptableToDelete.getId());
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
         * Initialize ecritureComptableM with arguments
         */
        getInitEC("AC", "Achat",
                "Libelle", "AC-2019/00001",
                401, 666, 411, 666);

        try {

            /**
             * Insert ecritureComptable into DataBase
             */
            comptabiliteManager.insertEcritureComptable(ecritureComptableM);

            /**
             * Get Previously created ecriture comptable obj
             */
            EcritureComptable ecritureComptableToDelete = comptabiliteManager.getEcritureComptableByRef("AC-2019/00001");


            /**
             * Assert Test
             */
            Assert.assertEquals(ecritureComptableM.getReference(), ecritureComptableToDelete.getReference());
            Assert.assertEquals(ecritureComptableM.getLibelle(), ecritureComptableToDelete.getLibelle());

            /**
             * Delete previous inserted Values
             */
            comptabiliteManager.deleteEcritureComptable(ecritureComptableToDelete.getId());
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

        /**
         * Initialize sequenceEcritureComptable with previously set values
         */
        getInitSeq(pAnne, pDerniereValeur);

        try {
            /**
             * Insert sequenceEcritureComptable
             */
            comptabiliteManager.insertSequenceEcritureComptable(sequenceEcritureComptable, pCode);

            /**
             * Find sequenceEcritureComptable previously created
             */
            SequenceEcritureComptable sequenceEcritureComptableToDelete =
                    comptabiliteManager.getSequenceEcritureComptable(pCode, pAnne);

            /**
             * Delete sequenceEcritureComptable previously created
             */
            if (pAnne == sequenceEcritureComptableToDelete.getAnnee()
                    && pDerniereValeur == sequenceEcritureComptableToDelete.getDerniereValeur()) {
                comptabiliteManager.deleteSequenceEcritureComptable(sequenceEcritureComptableToDelete, pCode);
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

        /**
         * Initialize sequenceEcritureComptable with previously set values
         */
        getInitSeq(pAnne, pDerniereValeur);

        try {
            /**
             * Insert sequenceEcritureComptable
             */
            comptabiliteManager.insertSequenceEcritureComptable(sequenceEcritureComptable, pCode);

            /**
             * Find sequenceEcritureComptable previously created
             */
            SequenceEcritureComptable sequenceEcritureComptableToDelete =
                    comptabiliteManager.getSequenceEcritureComptable(pCode, pAnne);

            if (pAnne == sequenceEcritureComptableToDelete.getAnnee()
                    && pDerniereValeur == sequenceEcritureComptableToDelete.getDerniereValeur()) {

                /**
                 * Update sequenceEcritureComptable previously created
                 */
                comptabiliteManager.updateSequenceEcritureComptable(sequenceEcritureComptableToDelete, pCode);

                /**
                 * Delete sequenceEcritureComptable previously created
                 */
                comptabiliteManager.deleteSequenceEcritureComptable(sequenceEcritureComptableToDelete, pCode);
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

        /**
         * Initialize sequenceEcritureComptable with previously set values
         */
        getInitSeq(pAnne, pDerniereValeur);

        try {
            /**
             * Insert sequenceEcritureComptable
             */
            comptabiliteManager.insertSequenceEcritureComptable(sequenceEcritureComptable, pCode);

            /**
             * Find sequenceEcritureComptable previously created
             */
            SequenceEcritureComptable sequenceEcritureComptableToDelete =
                    comptabiliteManager.getSequenceEcritureComptable(pCode, pAnne);

            /**
             * Delete sequenceEcritureComptable previously created
             */
            if (pAnne == sequenceEcritureComptableToDelete.getAnnee()
                    && pDerniereValeur == sequenceEcritureComptableToDelete.getDerniereValeur()) {
                comptabiliteManager.deleteSequenceEcritureComptable(sequenceEcritureComptableToDelete, pCode);
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
