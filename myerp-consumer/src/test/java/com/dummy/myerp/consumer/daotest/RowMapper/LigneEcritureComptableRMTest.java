package com.dummy.myerp.consumer.daotest.RowMapper;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.LigneEcritureComptableRM;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class LigneEcritureComptableRMTest {

    /**
     * Using Mockito To Test Spring RowMapper
     */

    /**
     * Mock
     */
    @Mock
    private DaoProxy daoProxy;

    @Mock
    private static ComptabiliteDao comptabiliteDao;

    @Mock
    private ResultSet resultSet;

    /**
     * Set up for Mockito
     */
    @Before
    public void setUp() {
        ConsumerHelper.configure(daoProxy);
        Mockito.when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
    }

    /**
     * Unit Test RowMapper
     *
     * @throws SQLException
     */
    @Test
    public void mapRow() throws SQLException {

        LigneEcritureComptableRM ligneEcritureComptableRM = new LigneEcritureComptableRM();

        /**
         * Get Row Name and add value in it
         */
        Mockito.when(resultSet.getString("libelle")).thenReturn("TestLib");
        Mockito.when(resultSet.getBigDecimal("debit")).thenReturn(new BigDecimal(132.7));
        Mockito.when(resultSet.getBigDecimal("credit")).thenReturn(new BigDecimal(12421.88));

        LigneEcritureComptable ligneEcritureComptable1 = ligneEcritureComptableRM.mapRow(resultSet, 0);

        LigneEcritureComptable ligneEcritureComptable2 = ligneEcritureComptableRM.mapRow(resultSet, 0);


        /**
         * Assert Test Not Null for each Obj previously created
         */
        Assert.assertNotNull(ligneEcritureComptable1);
        Assert.assertNotNull(ligneEcritureComptable2);

        /**
         * Check if two previously obj are equal
         */
        Assert.assertEquals(ligneEcritureComptable1.toString(), ligneEcritureComptable2.toString());
    }
}