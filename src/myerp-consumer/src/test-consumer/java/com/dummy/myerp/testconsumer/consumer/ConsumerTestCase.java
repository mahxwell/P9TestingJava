package com.dummy.myerp.testconsumer.consumer;

import com.dummy.myerp.consumer.dao.contrat.DaoProxy;

public class ConsumerTestCase {

    static {
        SpringRegistry.init();
    }

    /** {@link DaoProxy} */
    private static final DaoProxy DAO_PROXY = SpringRegistry.getDaoProxy();


    // ==================== Constructeurs ====================
    /**
     * Constructeur.
     */
    public ConsumerTestCase() {
    }


    // ==================== Getters/Setters ====================
    public static DaoProxy getDaoProxy() {
        return DAO_PROXY;
    }
}
