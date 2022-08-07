package com.example.iocexample.actor;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import org.springframework.context.ApplicationContext;

public class DIActorHelper implements IndirectActorProducer {

    private ApplicationContext appContext;
    private String actorBeanName;
    private Object[] args;

    public DIActorHelper(ApplicationContext appContext, String actorBeanName, Object[] args) {
        this.appContext = appContext;
        this.actorBeanName = actorBeanName;
        this.args = args;
    }

//    public DIActorHelper(ApplicationContext appContext, String actorBeanName) {
//        this(appContext, actorBeanName, null);
//    }

    @Override
    public Actor produce() {
        return args == null ?
                (Actor) appContext.getBean(actorBeanName) :
                (Actor) appContext.getBean(actorBeanName, args);
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>)  appContext.getBean(actorBeanName);
    }
}
