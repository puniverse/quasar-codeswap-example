package co.paralleluniverse.examples.quasar.codeswap;

import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.Strand;

public class CounterImpl implements Counter {
    int counter;
    
    @Override
    public String count(String text) throws SuspendExecution {
        try {
            Strand.sleep(20);
            return "Response from a simple proxy server: " + text + " " + (counter++);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
