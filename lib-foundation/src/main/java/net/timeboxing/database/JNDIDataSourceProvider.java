package net.timeboxing.database;

import javax.inject.Provider;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.lang.annotation.Annotation;

public class JNDIDataSourceProvider implements Provider<DataSource> {

    private final Class<? extends Annotation> dataSourceAnnotation;
    private final String jndiName;

    public JNDIDataSourceProvider(Class<? extends Annotation> dataSourceAnnotation, String jndiName) {
        this.dataSourceAnnotation = dataSourceAnnotation;
        this.jndiName = jndiName;
    }

    @Override
    public DataSource get() {
        try {
            return InitialContext.doLookup(jndiName);
        } catch (NamingException e) {
            throw new DataSourceNotFoundException(e);
        }
    }
}
