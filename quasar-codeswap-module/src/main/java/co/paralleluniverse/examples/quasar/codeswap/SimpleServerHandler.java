package co.paralleluniverse.examples.quasar.codeswap;

import co.paralleluniverse.actors.ActorRef;
import co.paralleluniverse.actors.behaviors.AbstractServerHandler;
import co.paralleluniverse.actors.behaviors.ServerHandler;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.Strand;

public class SimpleServerHandler extends AbstractServerHandler<String, String, String> implements ServerHandler<String, String, String> {
    private int counter;

    @Override
    public String handleCall(ActorRef<?> from, Object id, String m) throws SuspendExecution {
        try {
            Strand.sleep(20);
            return "Response from a simple, but improved, server handler: " + m + " " + (counter++);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
