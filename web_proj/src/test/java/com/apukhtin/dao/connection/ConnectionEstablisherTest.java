package com.apukhtin.dao.connection;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Vlad on 14.12.2015.
 */
public class ConnectionEstablisherTest {

    @Test
    public void testGetConnection() throws Exception {
        assertNotNull(ConnectionEstablisher.getConnection());
    }
}