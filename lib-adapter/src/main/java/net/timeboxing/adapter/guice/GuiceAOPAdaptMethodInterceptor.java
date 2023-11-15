package net.timeboxing.adapter.guice;

import net.timeboxing.adapter.AdaptException;
import net.timeboxing.adapter.Adapter;
import net.timeboxing.adapter.AdapterPurpose;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class GuiceAOPAdaptMethodInterceptor implements MethodInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(GuiceAOPAdaptMethodInterceptor.class);

    @Inject
    private Adapter adapter;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        LOG.debug("Invoked");
        int length = invocation.getArguments().length;
        Object caller = invocation.getThis();
        Class<?> desiredClass = ((Class) invocation.getArguments()[0]);
        if (1 == length) {
         return adapter.adaptTo(caller, desiredClass, AdapterPurpose.class, AdapterPurpose.DEFAULT);
        } else if (2 == length) {
            return adapter.adaptTo(caller, desiredClass, (Class) invocation.getArguments()[1].getClass(), invocation.getArguments()[1]);
        } else if (3 == length) {
            return adapter.adaptTo(caller, desiredClass, (Class) invocation.getArguments()[1], invocation.getArguments()[2]);
        } else {
            throw new AdaptException("Unsupported number of arguments for adapter");
        }
    }
}
