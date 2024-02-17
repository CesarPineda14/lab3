/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arst.concprg.prodcons;

import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.Semaphore;

/**
 *
 * @author hcadavid
 */
public class Consumer extends Thread {

    private Queue<Integer> queue;
    private final Semaphore pauseSemaphore;

    public Consumer(Queue<Integer> queue,Semaphore pauseSemaphore) {
        this.queue = queue;
        this.pauseSemaphore = pauseSemaphore;
        
        
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                try {
                    pauseSemaphore.acquire();
                    queue.notifyAll();
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            int elem = queue.poll();
            System.out.println("Consumer consumes " + elem);
        }
    }
}
