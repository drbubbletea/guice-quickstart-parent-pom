package net.timeboxing.adapter.guice;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import net.timeboxing.adapter.AdaptMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Guice AOP support is provided to make testing easier. Primary expected use of this module is compile-time AspectJ.
 */
public class GuiceAOPAdaptModule extends AbstractModule {

    private static final Logger LOG = LoggerFactory.getLogger(GuiceAOPAdaptModule.class);
    @Override
    protected void configure() {
        LOG.info("Initializing");
        GuiceAOPAdaptMethodInterceptor interceptor = new GuiceAOPAdaptMethodInterceptor();
        requestInjection(interceptor);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(AdaptMethod.class), interceptor);
    }
}
