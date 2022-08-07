package com.example.iocexample.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import com.example.iocexample.pojo.ContactBook;
import org.springframework.context.ApplicationContext;

public class MyActor extends AbstractActor {
    private int no;
    private ContactBook contactBook;
    private static String ACTOR_BEAN_NAME = "my-actor";

    public static Props props(ApplicationContext appContext, int no, Object ... args) {
        return Props.create(MyActor.class,
                () -> {
                    MyActor myActor = (MyActor) Props.create(DIActorHelper.class, new Object[] { appContext, ACTOR_BEAN_NAME, args}).newActor();
                    myActor.no = no;
                    return myActor;
                });
    }

    @Override
    public void preStart() throws Exception {
        super.preStart();
        System.out.println("preStart() ...");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().build();
    }

    public void setContactBook(ContactBook contactBook) {
        this.contactBook = contactBook;
    }

}