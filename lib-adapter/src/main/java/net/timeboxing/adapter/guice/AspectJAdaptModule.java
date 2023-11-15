package net.timeboxing.adapter.guice;

import com.google.inject.AbstractModule;
import net.timeboxing.adapter.AdaptAspect;
import org.aspectj.lang.Aspects;

public class AspectJAdaptModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AdaptAspect.class);
        requestInjection(Aspects.aspectOf(AdaptAspect.class));
    }
}
