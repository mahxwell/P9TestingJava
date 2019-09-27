package com.dummy.myerp.testconsumer.consumer;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Classe de test de l'initialisation du contexte Spring
 */
public class TestInitSpring {

    /**
     * Constructeur.
     */
    public TestInitSpring() {
        super();
    }


    /**
     * Teste l'initialisation du contexte Spring
     */
    @Test
    public void testInit() {
        SpringRegistry.init();
        assertNotNull(SpringRegistry.getDaoProxy());
    }
}
