/*
 * Ma Xin
 * write by 2017.5.9  1:1:44
 */

package com.fosun.creepers.project.EVA.example.clientServer;


import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by Administrator on 2017/5/9.
 */
public class ClientActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef romete;

    public ClientActor(ActorRef romete) {
        this.romete = romete;
    }

    @Override
    public void onReceive(Object message) throws Throwable {

        if (message instanceof String) {
            if (((String) message).startsWith("Start")) {
                log.info("Sending message to server - message # Hi gay ,look at here!!!!" + message);
                romete.tell("Hi serverActor,i come from clientActor !!" + message, getSelf());
            } else {
                log.info("Message receive from Server --->>> " + message);
            }
        }
    }
}
