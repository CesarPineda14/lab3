/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arst.concprg.prodcons;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.Semaphore;

public class StartProduction {
    
    
    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedBlockingQueue<>();
        long stockLimit = 5L;
        Semaphore pauseSemaphore = new Semaphore(0);

        new Producer(queue, stockLimit, pauseSemaphore).start();

        // let the producer create products for 5 seconds (stock).
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(StartProduction.class.getName()).log(Level.SEVERE, null, ex);
        }

        new Consumer(queue, pauseSemaphore).start();
    }
    

}