package com.dummy.myerp.consumer.daotest.RowMapper;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.CompteComptableRM;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
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
public class CompteComptableRMTest {


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
        CompteComptableRM compteComptableRM = new CompteComptableRM();

        Mockito.when(resultSet.getInt("numero")).thenReturn(1);
        Mockito.when(resultSet.getString("libelle")).thenReturn("testRowMapper");

        CompteComptable compteComptable1 = compteComptableRM.mapRow(resultSet, 0);

        CompteComptable compteComptable2 = compteComptableRM.mapRow(resultSet, 0);


/*        System.out.println(compteComptable1.toString());
        System.out.println(compteComptable2.toString());*/
        Assert.assertNotNull(compteComptable1);
        Assert.assertNotNull(compteComptable2);

        Assert.assertEquals(compteComptable1.toString(), compteComptable2.toString());
    }
}