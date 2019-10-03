package com.dummy.myerp.business.impl.manager;

import java.math.BigDecimal;
import java.util.Date;

import com.dummy.myerp.testbusiness.business.BusinessTestCase;
import org.junit.Test;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;

public class ComptabiliteManagerImplTest extends BusinessTestCase {

    private ComptabiliteManagerImpl comptabiliteManager = new ComptabiliteManagerImpl();
    private EcritureComptable ecritureComptableM = new EcritureComptable();

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
         * Set ecritureComptable M for futur Tests
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

    // ==================== addReference Method Test ===============================

    @Test
    public void addReference() throws Exception {

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
    public void checkEcritureComptableUnitRG5Violation() throws Exception {

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

    // ==================== insertEcritureComptable Method Test ====================

/*    @Test
    public void insertEcritureComptable() throws Exception {

        *//**
         * Initialize ecritureComptableM with arguments
         *//*
        getInitEC("AC", "Achat",
                "Libelle", "AC-2020/00001",
                401, 666, 411, 666);

        comptabiliteManager.insertEcritureComptable(ecritureComptableM);

       // EcritureComptable ecritureComptableToDelete =
    }*/

    // ==================== updateEcritureComptable Method Test ====================

    // ==================== deleteEcritureComptable Method Test ====================

    // ==================== insertSequenceEcritureComptable Method Test ============

    // ==================== updateSequenceEcritureComptable Method Test ============


}
