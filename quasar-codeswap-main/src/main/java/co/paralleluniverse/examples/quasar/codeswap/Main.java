package co.paralleluniverse.examples.quasar.codeswap;

import co.paralleluniverse.actors.ActorRef;
import co.paralleluniverse.actors.behaviors.ProxyServerActor;
import co.paralleluniverse.actors.behaviors.Server;
import co.paralleluniverse.actors.behaviors.ServerActor;

public class Main {
    public static void main(String[] args) throws Exception {
        ActorRef<Message> actor = new SimpleActor("simple").spawn();
        Server<String, String, String> server1 = new SimpleServerActor().spawn();
        Server<String, String, String> server2 = new ServerActor<>(new SimpleServerHandler()).spawn();
        Counter counter = (Counter) new ProxyServerActor(false, new CounterImpl()).spawn();
        
        for (int i = 0;; i++) {
            actor.send(new Message(i));
            System.out.println(server1.call("foo"));
            System.out.println(server2.call("bar"));
            System.out.println(counter.count("oh"));
            Thread.sleep(250);
        }
        //LocalActorUtil.join(actor);
    }
}
