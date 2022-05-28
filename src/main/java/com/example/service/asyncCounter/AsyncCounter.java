package com.example.service.asyncCounter;

import com.example.service.process.Solution;
import lombok.Getter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


public class AsyncCounter {
    private static long count;

    static Logger logger = LoggerFactory.getLogger(Solution.class);

    public static synchronized void increase() {
        count++;
        logger.info("Solution Service requests amount is: " + count);
    }

    public static synchronized long getCount() {
        return count;
    }
}
