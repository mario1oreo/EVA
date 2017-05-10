/*
 * Ma Xin
 * write by 2017.5.9  1:21:59
 */

package com.fosun.creepers.project.EVA.example.clientServer;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.kernel.Bootable;
import akka.remote.RemoteScope;
import com.typesafe.config.ConfigFactory;

import static java.lang.Thread.sleep;

/**
 * Created by Administrator on 2017/5/9.
 */
public class ClientActorSystem implements Bootable {

    private LoggingAdapter log = null;
    private ActorSystem system;
    private ActorRef actor;
    private ActorRef remoteActor;


    public ClientActorSystem() {
        system = ActorSystem.create("ClientSys", ConfigFactory.load().getConfig("ClientSys"));
        log = Logging.getLogger(system, this);
    }

    public void startup() {
        //maybe should to do something..
    }

    public void shutdown() {
        log.info("Sending PoisonPill to ServerActorSystem");
//        system.stop(remoteActor);
        remoteActor.tell(PoisonPill.getInstance(),ActorRef.noSender());
        log.info("Shutting down the ClientActorSystem");
        system.terminate();
    }

    public void remoteActorDemo0() {
        log.info("Createing a reference to remote actor!");
        remoteActor = system.actorFor("akka.tcp://ServerSys@127.0.0.1:2553/user/serverActor");
        log.info("ServerActor with hashCode #" + remoteActor.hashCode());
        actor = system.actorOf(Props.create(ClientActor.class, remoteActor));
        actor.tell("Start - RemoteActorRef Creating Demo :0!", ActorRef.noSender());
        try {
            sleep(1000); //模拟还要额外耗时5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void remoteActorRefDemo1() {
        log.info("Creating a actor using remote deployment mechanism");
        Address addr = new Address("akka.tcp", "ServerSys", "127.0.0.1", 2553);
        final ActorRef serverActor = system.actorOf(Props.create(ServerActor.class).withDeploy(new Deploy(new RemoteScope(addr))));
        log.info("serverActor with hashCode #" + serverActor.hashCode());
        actor = system.actorOf(Props.create(ClientActor.class, serverActor));
        actor.tell("Start - RemoteActor Creating Demo :1!", ActorRef.noSender());
        try {
            sleep(1000); //模拟还要额外耗时5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void remoteActorRefDemo2() {
        log.info("Creating a actor with remote deployment");
        Address addr = AddressFromURIString.parse("akka.tcp://ServerSys@127.0.0.1:2553");
        final ActorRef serverActor = system.actorOf(Props.create(ServerActor.class).withDeploy(new Deploy(new RemoteScope(addr))));
        log.info("serverActor with hashCode #" + serverActor.hashCode());
        actor = system.actorOf(Props.create(ClientActor.class, serverActor));
        actor.tell("Start - RemoteActor Creating Demo :2!", ActorRef.noSender());
        try {
            sleep(1000); //模拟还要额外耗时5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void remoteActorRefDemo3() {
        log.info("Creating a actor with remote deployment");
        final ActorRef serverActor = system.actorOf(Props.create(ServerActor.class), "remoteServerActor");
        log.info("serverActor with hashCode #" + serverActor.hashCode());
        actor = system.actorOf(Props.create(ClientActor.class, serverActor));
        actor.tell("Start - RemoteActor Creating Demo :3!", ActorRef.noSender());
        try {
            sleep(1000); //模拟还要额外耗时5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClientActorSystem cAS = new ClientActorSystem();
        cAS.remoteActorDemo0();
        cAS.remoteActorRefDemo1();
        cAS.remoteActorRefDemo2();
        cAS.remoteActorRefDemo3();
        try {
            sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cAS.shutdown();
    }
}
