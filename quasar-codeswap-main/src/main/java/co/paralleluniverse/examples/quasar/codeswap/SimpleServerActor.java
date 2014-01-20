package co.paralleluniverse.examples.quasar.codeswap;

import co.paralleluniverse.actors.ActorRef;
import co.paralleluniverse.actors.behaviors.ServerActor;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.Strand;

public class SimpleServerActor extends ServerActor<String, String, String> {
    private int counter;
    
    @Override
    protected String handleCall(ActorRef<?> from, Object id, String m) throws Exception, SuspendExecution {
        try {
            Strand.sleep(20);
            return "Response from a simple server: " + m + " " + (counter++);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
