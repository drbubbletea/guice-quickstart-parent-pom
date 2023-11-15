package net.timeboxing.database;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Provider;
import javax.sql.DataSource;
import java.lang.annotation.Annotation;

public class DataSourceModule extends AbstractModule {

    private static final Logger LOG = LoggerFactory.getLogger(DataSourceModule.class);

    private final Class<? extends Annotation> dataSourceAnnotation;
    private final String jndiName;

    public DataSourceModule(Class<? extends Annotation> dataSource, String jndiName) {
        this.dataSourceAnnotation = dataSource;
        this.jndiName = jndiName;
    }

    @Override
    protected void configure() {
        super.configure();
        LOG.debug("Creating binding for {} with JNDI name '{}'", dataSourceAnnotation.getSimpleName(), jndiName);
        Provider<DataSource> dataSourceProvider = new JNDIDataSourceProvider(dataSourceAnnotation, jndiName);
        bind(DataSource.class).annotatedWith(dataSourceAnnotation).toProvider(dataSourceProvider);
        bind(Jdbi.class).annotatedWith(dataSourceAnnotation).toProvider(new JdbiProvider(dataSourceProvider)).in(Scopes.SINGLETON);
    }
}
