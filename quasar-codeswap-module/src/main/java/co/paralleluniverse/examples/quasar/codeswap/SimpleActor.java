package co.paralleluniverse.examples.quasar.codeswap;

import co.paralleluniverse.actors.Actor;
import co.paralleluniverse.actors.OnUpgrade;
import co.paralleluniverse.actors.Upgrade;
import co.paralleluniverse.fibers.SuspendExecution;
import java.util.concurrent.TimeUnit;

//@Upgrade
public class SimpleActor extends Actor<Message, Void> {
    static String NAME;
    private int count;
    private final Foo foo = new Foo();
    private final Runnable task = new MyTask();
    
    private class MyTask implements Runnable {
        private int ra;
        private String sra;

        @Override
        public void run() {
            System.out.println("An actor's simple but improved runnable: " + (count + 1000) + " " + (ra++) + " " + sra);
        }

        @OnUpgrade
        void foo() {
            this.sra = "$" + ra + "$";
        }
    };

    @OnUpgrade
    static void foo() {
        NAME = NAME + "!";
    }

    public SimpleActor(String name) {
        super(name, null);
    }

    @Override
    protected Void doRun() throws InterruptedException, SuspendExecution {
        for (;;) {
            Message m = receive(500, TimeUnit.MILLISECONDS);
            if (m != null)
                System.out.println("Got message: " + m.num);
            System.out.println(NAME + " I am a simple actor, but better than before! - " + (count++) + ", foo:" + (foo.a++) + "," + (foo.b++));
            task.run();
            checkCodeSwap();
        }
    }

    @Override
    protected void onCodeChange() {
        // foo.b = foo.a * 100;
    }

    static class Foo {
        int a;
        int b;

        @OnUpgrade
        private void foo() {
            b = -10;
        }
    }
}
