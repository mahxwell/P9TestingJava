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
        LigneEcritureComptableRM ligneEcritureComptableRM = new LigneEcritureComptableRM();


        Mockito.when(resultSet.getInt("ecriture_id")).thenReturn(-1);
        Mockito.when(resultSet.getInt("ligne_id")).thenReturn(33);
        Mockito.when(resultSet.getInt("compte_comptable_numero")).thenReturn(666);
        Mockito.when(resultSet.getString("libelle")).thenReturn("TestLib");
        Mockito.when(resultSet.getBigDecimal("debit")).thenReturn(new BigDecimal(132.7));
        Mockito.when(resultSet.getBigDecimal("credit")).thenReturn(new BigDecimal(12421.88));

        LigneEcritureComptable ligneEcritureComptable1 = ligneEcritureComptableRM.mapRow(resultSet, 0);

        LigneEcritureComptable ligneEcritureComptable2 = ligneEcritureComptableRM.mapRow(resultSet, 0);


        Assert.assertNotNull(ligneEcritureComptable1);
        Assert.assertNotNull(ligneEcritureComptable2);

        Assert.assertEquals(ligneEcritureComptable1.toString(), ligneEcritureComptable2.toString());
    }
}