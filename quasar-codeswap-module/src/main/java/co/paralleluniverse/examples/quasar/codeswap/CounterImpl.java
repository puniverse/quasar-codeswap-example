package co.paralleluniverse.examples.quasar.codeswap;

import co.paralleluniverse.actors.Upgrade;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.Strand;

@Upgrade
public class CounterImpl implements Counter {
    int counter;
    
    @Override
    public String count(String text) throws SuspendExecution {
        try {
            Strand.sleep(20);
            return "Response from a simple, but improved, proxy server: " + text + " " + (counter++);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
