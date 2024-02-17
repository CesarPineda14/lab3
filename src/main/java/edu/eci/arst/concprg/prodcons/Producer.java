/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arst.concprg.prodcons;

import java.util.Queue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.Semaphore;

/**
 *
 * @author hcadavid
 */
public class Producer extends Thread {

    private Queue<Integer> queue = null;

    private int dataSeed = 0;
    private Random rand = null;
    private final long stockLimit;
    private final Semaphore pauseSemaphore;

    public Producer(Queue<Integer> queue, long stockLimit,Semaphore pauseSemaphore) {
        this.queue = queue;
        rand = new Random(System.currentTimeMillis());
        this.stockLimit = stockLimit;
        this.pauseSemaphore = pauseSemaphore;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                try {
                    if (queue.size() >= stockLimit-1) {
                        pauseSemaphore.release();
                        queue.wait();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dataSeed = dataSeed + rand.nextInt(10);
            System.out.println("Producer added " + dataSeed);
            queue.offer(dataSeed);
            
        }
    }
}
