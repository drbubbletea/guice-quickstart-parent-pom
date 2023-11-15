package net.timeboxing.adapter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Aspect
public class AdaptAspect {

    private static final Logger LOG = LoggerFactory.getLogger(AdaptAspect.class);

    @Inject
    private Adapter adapter;
    @Around("call( * net.timeboxing.adapter.Adaptable.adaptTo(..))")
//    @Around(value = "target(net.timeboxing.adapter.Adaptable) && execution(* net.timeboxing.adapter.Adaptable+.adaptTo(..))")
    public Object around(ProceedingJoinPoint pjp) {
        LOG.debug("Around triggered for {}", pjp.getThis().getClass().getCanonicalName());
        int length = pjp.getArgs().length;
        Object caller = pjp.getTarget();
        Class<?> desiredClass = ((Class) pjp.getArgs()[0]);
        if (1 == length) {
            return adapter.adaptTo(caller, desiredClass, AdapterPurpose.class, AdapterPurpose.DEFAULT);
        } else if (2 == length) {
            return adapter.adaptTo(caller, desiredClass, (Class) pjp.getArgs()[1].getClass(), pjp.getArgs()[1]);
        } else if (3 == length) {
            return adapter.adaptTo(caller, desiredClass, (Class) pjp.getArgs()[1], pjp.getArgs()[2]);
        } else {
            throw new AdaptException("Unsupported number of arguments for adapter");
        }
    }
}
