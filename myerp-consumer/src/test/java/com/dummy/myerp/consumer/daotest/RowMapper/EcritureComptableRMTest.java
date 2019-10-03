package com.dummy.myerp.consumer.daotest.RowMapper;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.EcritureComptableRM;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;


@RunWith(MockitoJUnitRunner.class)
public class EcritureComptableRMTest {

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

        EcritureComptableRM ecritureComptableRM = new EcritureComptableRM();


        /**
         * Get Row Name and add value in it
         */
        Mockito.when(resultSet.getInt("id")).thenReturn(1);
        Mockito.when(resultSet.getString("journal_code")).thenReturn("ABCTEST");
        Mockito.when(resultSet.getString("reference")).thenReturn("TESTROW");
        Mockito.when(resultSet.getDate(null)).thenReturn(null);
        Mockito.when(resultSet.getString("libelle")).thenReturn("TestLib");

        EcritureComptable ecritureComptable1 = ecritureComptableRM.mapRow(resultSet, 0);

        EcritureComptable ecritureComptable2 = ecritureComptableRM.mapRow(resultSet, 0);


        /**
         * Assert Test Not Null for each Obj previously created
         */
        Assert.assertNotNull(ecritureComptable1);
        Assert.assertNotNull(ecritureComptable2);


        /**
         * Check if two previously obj are equal
         */
        Assert.assertEquals(ecritureComptable1.toString(), ecritureComptable2.toString());
    }
}