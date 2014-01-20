package co.paralleluniverse.examples.quasar.codeswap;

import co.paralleluniverse.actors.Actor;
import co.paralleluniverse.actors.OnUpgrade;
import co.paralleluniverse.fibers.SuspendExecution;
import java.util.concurrent.TimeUnit;

public class SimpleActor extends Actor<Message, Void> {
    static String NAME = "GOGO";
    private int count;
    private int count2;
    private final Foo foo = new Foo();
    private final Runnable task = new MyTask();

    private class MyTask implements Runnable {
        private int ra;

        @Override
        public void run() {
            System.out.println("An actor's simple runnable: " + (count + 100) + " " + (ra++));
        }
    };

    public SimpleActor(String name) {
        super(name, null);
    }

    @Override
    protected Void doRun() throws InterruptedException, SuspendExecution {
        for (;;) {
            Message m = receive(500, TimeUnit.MILLISECONDS);
            if (m != null)
                System.out.println("Got message: " + m.num);
            System.out.println(NAME + " I am a simple actor - " + (count++) + ", " + (count2++) + ", foo:" + (foo.a++));
            task.run();
            checkCodeSwap();
        }
    }

    @OnUpgrade
    private void foo() {
        count2 = -10;
    }

    @Override
    protected void onCodeChange() {
        //count2 = count * 100;
    }

    static class Foo {
        int a;
    }
}
