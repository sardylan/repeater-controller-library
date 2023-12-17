package org.thehellnet.ham.repeatercontroller.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LoopThread {

    private static final Logger logger = LoggerFactory.getLogger(LoopThread.class);

    private final Object SYNC = new Object();

    private volatile boolean keepRunning = false;

    private Thread thread;

    public synchronized void start() {
        synchronized (SYNC) {
            logger.info("Starting thread");

            if (thread != null) {
                logger.warn("Already started");
                return;
            }

            thread = new Thread(this::loop);

            keepRunning = true;
            thread.start();
        }
    }

    public synchronized void stop() {
        synchronized (SYNC) {
            logger.info("Stopping thread");
            keepRunning = false;
        }
    }

    protected void loop() {
        logger.info("Starting loop");

        preLoop();

        while (keepRunning) {
            logger.debug("Running job");

            try {
                job();
            } catch (Exception e) {
                logger.error("Exception in job: {}", e.getMessage());
                break;
            }
        }

        postLoop();

        logger.info("Loop finished");

        synchronized (SYNC) {
            if (thread != null) {
                logger.debug("Setting thread to null");
                thread = null;
            }
        }
    }

    protected void preLoop() {

    }

    protected void postLoop() {

    }

    protected abstract void job();
}
