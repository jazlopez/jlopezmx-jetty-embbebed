package jlopezmx.demo.http;

import com.sun.istack.internal.Nullable;

import java.io.InputStream;

class Worker {

    /**
     *
     */
    static int MIN_WAIT_TIME_SECONDS = 1;

    /**
     *
     */
    static int MAX_WAIT_TIME_SECONDS = 3;


    public Worker() {
    }

    /**
     *
     * @param is InputStream
     * @param minWaitTime Integer
     * @param maxWaitTime Integer
     */
    void write(InputStream is, @Nullable  Integer minWaitTime, @Nullable  Integer maxWaitTime ) {

        if(minWaitTime == null) {
            minWaitTime = MIN_WAIT_TIME_SECONDS;
        }

        if(maxWaitTime == null) {
            maxWaitTime = MAX_WAIT_TIME_SECONDS;
        }

        try {

            Thread.sleep((int)(maxWaitTime * Math.random() + minWaitTime) * 1000);

        } catch (InterruptedException e){

            e.printStackTrace();
        }
    }
}
