package net.timeboxing.database;

import org.jdbi.v3.core.ConnectionFactory;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Provider;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbiProvider implements Provider<Jdbi> {

    private static final Logger LOG = LoggerFactory.getLogger(JdbiProvider.class);
    private final Provider<DataSource> dataSourceProvider;

    public JdbiProvider(Provider<DataSource> dataSourceProvider) {
        this.dataSourceProvider = dataSourceProvider;
    }

    @Override
    public Jdbi get() {
        LOG.debug("Getting JDBI instance");
        return Jdbi.create(new DataSourceProviderConnectionFactory(dataSourceProvider));
    }

    private class DataSourceProviderConnectionFactory implements ConnectionFactory {

        private final Provider<DataSource> dataSourceProvider;
        public DataSourceProviderConnectionFactory(Provider<DataSource> dataSourceProvider) {
            this.dataSourceProvider = dataSourceProvider;
        }

        @Override
        public Connection openConnection() throws SQLException {
            return dataSourceProvider.get().getConnection();
        }
    }
}
