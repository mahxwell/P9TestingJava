package com.dummy.myerp.consumer.daotest.RowMapper;

import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.JournalComptableRM;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class JournalComptableRMTest {

    /**
     * Using Mockito To Test Spring RowMapper
     */

    /**
     * Mock
     */
    @Mock
    private ResultSet resultSet;

    /**
     * Unit Test RowMapper
     *
     * @throws SQLException
     */
    @Test
    public void mapRow() throws SQLException {

        JournalComptableRM journalComptableRM = new JournalComptableRM();

        /**
         * Get Row Name and add value in it
         */
        Mockito.when(resultSet.getString("code")).thenReturn("TOTO");
        Mockito.when(resultSet.getString("libelle")).thenReturn("TestLib");

        JournalComptable journalComptable1 = journalComptableRM.mapRow(resultSet, 0);

        JournalComptable journalComptable2 = journalComptableRM.mapRow(resultSet, 0);

        /**
         * Assert Test Not Null for each Obj previously created
         */
        Assert.assertNotNull(journalComptable1);
        Assert.assertNotNull(journalComptable2);

        /**
         * Check if two previously obj are equal
         */
        Assert.assertEquals(journalComptable1.toString(), journalComptable2.toString());
    }
}