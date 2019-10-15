package com.dummy.myerp.consumer.daotest.resultsethelper;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.EcritureComptableRM;
import com.dummy.myerp.consumer.db.helper.ResultSetHelper;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class GetDate extends ResultSetHelper {

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

    @Test
    public void getDate() throws SQLException {

        EcritureComptableRM ecritureComptableRM = new EcritureComptableRM();

        /**
         * Get Row Name and add value in it
         */
        Mockito.when(resultSet.getInt("id")).thenReturn(1);
        Mockito.when(resultSet.getString("journal_code")).thenReturn("ABCTEST");
        Mockito.when(resultSet.getString("reference")).thenReturn("TESTROW");
        Mockito.when(resultSet.getString("libelle")).thenReturn("TestLib");

        EcritureComptable ecritureComptable1 = ecritureComptableRM.mapRow(resultSet, 0);

        EcritureComptable ecritureComptable2 = ecritureComptableRM.mapRow(resultSet, 0);

        getDate(resultSet, "date");
    }
}
