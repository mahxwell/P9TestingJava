package com.dummy.myerp.consumer.daotest.RowMapper;

import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.CompteComptableRM;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;


@RunWith(MockitoJUnitRunner.class)
public class CompteComptableRMTest {

    /**
     * Using Mockito To Test Spring RowMapper
     */

    /**
     * Mock
     */
    @Mock
    private ResultSet resultSet;

    /**
     * Set up for Mockito
     */

    /**
     * Unit Test RowMapper
     *
     * @throws SQLException
     */
    @Test
    public void mapRow() throws SQLException {

        CompteComptableRM compteComptableRM = new CompteComptableRM();

        /**
         * Get Row Name and add value in it
         */
        Mockito.when(resultSet.getInt("numero")).thenReturn(1);
        Mockito.when(resultSet.getString("libelle")).thenReturn("testRowMapper");

        CompteComptable compteComptable1 = compteComptableRM.mapRow(resultSet, 0);

        CompteComptable compteComptable2 = compteComptableRM.mapRow(resultSet, 0);

        /**
         * Assert Test Not Null for each Obj previously created
         */
        Assert.assertNotNull(compteComptable1);
        Assert.assertNotNull(compteComptable2);

        /**
         * Check if two previously obj are equal
         */
        Assert.assertEquals(compteComptable1.toString(), compteComptable2.toString());
    }
}