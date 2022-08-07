package com.example.springmybatis;

import akka.actor.AbstractActor;
import akka.actor.Props;

class MyActor extends AbstractActor {
    private Integer i;

    static Props props(Integer i) {
        return Props.create(MyActor.class, () -> new MyActor(i));
    }

    public MyActor(Integer i){
        this.i = i;
        System.out.println("MyActor");
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
}