package com.dummy.myerp.consumer.daotest.RowMapper;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.SequenceEcritureComptableRM;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
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
public class SequenceEcritureComptableRMTest {

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

        SequenceEcritureComptableRM rowmapper = new SequenceEcritureComptableRM();

        /**
         * Get Row Name and add value in it
         */
        Mockito.when(resultSet.getString("journal_code")).thenReturn("AC");
        Mockito.when(resultSet.getInt("annee")).thenReturn(7777);
        Mockito.when(resultSet.getInt("derniere_valeur")).thenReturn(6666);


        SequenceEcritureComptable sequence1 = rowmapper.mapRow(resultSet, 0);
        SequenceEcritureComptable sequence2 = rowmapper.mapRow(resultSet, 0);

        /**
         * Assert Test Not Null for each Obj previously created
         */
        Assert.assertNotNull(sequence1);
        Assert.assertNotNull(sequence2);

        /**
         * Check if two previously obj are equal
         */
        Assert.assertEquals(sequence1.toString(), sequence2.toString());

    }

}
