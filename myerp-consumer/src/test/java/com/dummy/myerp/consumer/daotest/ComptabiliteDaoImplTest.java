package com.dummy.myerp.consumer.daotest;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.testconsumer.consumer.ConsumerTestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComptabiliteDaoImplTest extends ConsumerTestCase {


    private ComptabiliteDao comptabiliteDao = getDaoProxy().getComptabiliteDao();

    @Test
    public void getListCompteComptable() {

        String toto = comptabiliteDao.getListCompteComptable().toString();

        System.out.println(toto);
    }
}