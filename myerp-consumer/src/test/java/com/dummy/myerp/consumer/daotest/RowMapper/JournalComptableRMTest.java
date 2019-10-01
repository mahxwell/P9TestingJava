package com.dummy.myerp.consumer.daotest.RowMapper;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.JournalComptableRM;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
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
public class JournalComptableRMTest {

    @Mock
    private DaoProxy daoProxy;

    @Mock
    private static ComptabiliteDao comptabiliteDao;

    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() {
        ConsumerHelper.configure(daoProxy);
        Mockito.when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
    }


    @Test
    public void mapRow() throws SQLException {

        JournalComptableRM journalComptableRM = new JournalComptableRM();

        Mockito.when(resultSet.getString("code")).thenReturn("TOTO");
        Mockito.when(resultSet.getString("libelle")).thenReturn("TestLib");

        JournalComptable journalComptable1 = journalComptableRM.mapRow(resultSet, 0);

        JournalComptable journalComptable2 = journalComptableRM.mapRow(resultSet, 0);


        Assert.assertNotNull(journalComptable1);
        Assert.assertNotNull(journalComptable2);

        Assert.assertEquals(journalComptable1.toString(), journalComptable2.toString());
    }
}