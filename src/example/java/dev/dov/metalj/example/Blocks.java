package dev.dov.metalj.example;

import dev.dov.metalj.device.Metal;
import dev.dov.metalj.objc.Block;
import dev.dov.metalj.sync.MTLSharedEvent;
import dev.dov.metalj.sync.MTLSharedEventListener;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;

public class Blocks {
    @SneakyThrows
    public static void main(String[] args) {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var queue = device.newCommandQueue();

        var finished = new CountDownLatch(1);
        var completed = Block.once(() -> {
            System.out.println("completed on " + Thread.currentThread().getName());
            finished.countDown();
        });
        var cmd = queue.commandBuffer();
        cmd.addCompletedHandler(completed);
        cmd.commit();
        finished.await(2, TimeUnit.SECONDS);

        var event = device.newSharedEvent();
        var listener = MTLSharedEventListener.new_();
        var signalled = new CountDownLatch(1);
        var handler = MTLSharedEvent.listener(value -> {
            System.out.println("event reached " + value);
            signalled.countDown();
        });
        event.notifyListener(listener, 5, handler);
        event.setSignaledValue(5);
        signalled.await(2, TimeUnit.SECONDS);

        completed.close();
        System.out.println("blocks released");
    }
}
