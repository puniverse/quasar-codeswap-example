package co.paralleluniverse.examples.quasar.codeswap;

import co.paralleluniverse.fibers.SuspendExecution;

public interface Counter {
    String count(String text) throws SuspendExecution;
}
