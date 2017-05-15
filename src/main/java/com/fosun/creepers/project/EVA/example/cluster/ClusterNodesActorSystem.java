/*
 * Ma Xin
 * write by 2017.5.9  1:21:59
 */

package com.fosun.creepers.project.EVA.example.cluster;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.kernel.Bootable;
import akka.remote.RemoteScope;
import com.fosun.creepers.project.EVA.example.clientServer.ClientActor;
import com.fosun.creepers.project.EVA.example.clientServer.ClientHeartBeatActor;
import com.fosun.creepers.project.EVA.example.clientServer.ServerActor;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Created by Administrator on 2017/5/9.
 */
public class ClusterNodesActorSystem implements Bootable  {

    private LoggingAdapter log = null;
    private ActorSystem system;
    private ActorRef actor;
    private ActorRef remoteActor;


    public ClusterNodesActorSystem() {


    }

    public void startup() {
        //maybe should to do something..
    }

    public void shutdown() {
        log.info("Sending PoisonPill to ServerActorSystem");
//        system.stop(remoteActor);
//        remoteActor.tell(PoisonPill.getInstance(),ActorRef.noSender());
        log.info("Shutting down the ClientActorSystem");
        system.terminate();
    }


    public void remoteActorTestCluster2552() {
        system = ActorSystem.create("metadataAkkaSystem",ConfigFactory.load("application-cluster.conf").getConfig("ServerSys2552"));
        log = Logging.getLogger(system, this);
//        log.info("Createing a reference to remote actor!  remoteActorTestCluster2552");
//        remoteActor = system.actorFor("akka.tcp://metadataAkkaSystem@127.0.0.1:2551/user/serverActor/");
//        log.info("remoteActor with hashCode #" + remoteActor.hashCode());
        actor = system.actorOf(Props.create(SimpleClusterListener.class),"clusterListener");
        actor.tell("Start - RemoteActorRef Creating Demo :0!", ActorRef.noSender());

    }

    public void remoteActorTestCluster2553() {
        system = ActorSystem.create("metadataAkkaSystem",ConfigFactory.load("application-cluster.conf").getConfig("ServerSys2553"));
        log = Logging.getLogger(system, this);
//        log.info("Createing a reference to remote actor!  remoteActorTestCluster2553");
//        remoteActor = system.actorFor("akka.tcp://metadataAkkaSystem@127.0.0.1:2551/user/serverActor/");
//        log.info("remoteActor with hashCode #" + remoteActor.hashCode());
        actor = system.actorOf(Props.create(SimpleClusterListener.class),"clusterListener");
        actor.tell("Start - RemoteActorRef Creating Demo :0!", ActorRef.noSender());

    }

    public void remoteActorTestCluster2554() {
        system = ActorSystem.create("metadataAkkaSystem",ConfigFactory.load("application-cluster.conf").getConfig("ServerSys2554"));
        log = Logging.getLogger(system, this);
//        log.info("Createing a reference to remote actor!  remoteActorTestCluster2554");
//        remoteActor = system.actorFor("akka.tcp://metadataAkkaSystem@127.0.0.1:2551/user/serverActor/");
//        log.info("remoteActor with hashCode #" + remoteActor.hashCode());
        actor = system.actorOf(Props.create(SimpleClusterListener.class),"clusterListener");
        actor.tell("Start - RemoteActorRef Creating Demo :0!", ActorRef.noSender());

    }

    public void remoteActorTestCluster2555() {
        system = ActorSystem.create("metadataAkkaSystem",ConfigFactory.load("application-cluster.conf").getConfig("ServerSys2555"));
        log = Logging.getLogger(system, this);
//        log.info("Createing a reference to remote actor!  remoteActorTestCluster2554");
//        remoteActor = system.actorFor("akka.tcp://metadataAkkaSystem@127.0.0.1:2551/user/serverActor/");
//        log.info("remoteActor with hashCode #" + remoteActor.hashCode());
        actor = system.actorOf(Props.create(SimpleClusterListener.class),"clusterListener");
        actor.tell("Start - RemoteActorRef Creating Demo :0!", ActorRef.noSender());

    }
    public static void main(String[] args) {
        ClusterNodesActorSystem cAS = new ClusterNodesActorSystem();
//        cAS.remoteActorHeartBeatDemo();
//        cAS.remoteActorDemo0();
//        cAS.remoteActorRefDemo1();
//        cAS.remoteActorRefDemo2();
//        cAS.remoteActorRefDemo3();
        cAS.remoteActorTestCluster2552();
        cAS.remoteActorTestCluster2553();
        cAS.remoteActorTestCluster2554();
        cAS.remoteActorTestCluster2555();
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cAS.shutdown();
    }
}
