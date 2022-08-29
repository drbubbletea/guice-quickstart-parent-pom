package net.timeboxing.database;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataSourceModuleTest {

    @Test
    public void canGetInMemoryJdbiAndQuery() {
        Injector injector = Guice.createInjector(new TestDataSourceModule());
        TestDAL testDAL = injector.getInstance(TestDAL.class);
        Assertions.assertEquals(1, testDAL.test());
    }
}
