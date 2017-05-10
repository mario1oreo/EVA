/*
 * Ma Xin
 * write by 2017.5.9  1:50:41
 */

package com.fosun.creepers.project.EVA.example.clientServer;

import akka.actor.ActorRef;
import akka.actor.PoisonPill;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/5/9.
 */
public class ServerActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private AtomicInteger instanceCounter = new AtomicInteger(0);


    @Override
    public void preStart() throws Exception {
        log.info("Starting ServerActor instance # " + instanceCounter.incrementAndGet() + ", hashconde # " + this.hashCode());
    }

    @Override
    public void postStop() throws Exception {
        log.info("Stoping ServerActor instance # " + instanceCounter.getAndDecrement() + ", hashconde # " + this.hashCode());
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof String) {
            log.info("serverActor : I get something from client @@@@@@@:" + message);
            getSender().tell(" #### serverActor get something!" + message, ActorRef.noSender());
        } else if (message instanceof PoisonPill) {
            log.info("I get something from client #:" + message + " ,then I will stop ServerActor");
            getContext().system().stop(getSelf());
        }
    }
}
