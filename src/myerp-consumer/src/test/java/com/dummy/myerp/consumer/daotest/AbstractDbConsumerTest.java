package com.dummy.myerp.consumer.daotest;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.consumer.db.AbstractDbConsumer;
import com.dummy.myerp.consumer.db.DataSourcesEnum;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.Map;

public class AbstractDbConsumerTest extends AbstractDbConsumer {

    private static Map<DataSourcesEnum, DataSource> mapDataSource;

    @Test
    public void initializeDoaProxy() {
        ConsumerHelper.getDaoProxy();
    }

    @Test(expected = NullPointerException.class)
    public void configureDataSource() {
        configure(mapDataSource);
    }

    @Test
    public void consumerHelperTest() {
        ConsumerHelper consumerHelper = new ConsumerHelper() {
        };

        consumerHelper.toString();
    }
}
