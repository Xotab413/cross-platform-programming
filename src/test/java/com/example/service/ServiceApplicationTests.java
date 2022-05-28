package com.example.service;

import com.example.service.asyncCounter.AsyncCounter;
import com.example.service.process.Solution;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;



import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ServiceApplicationTests {

    @Test
    void is2020LeapYear() {
        var year = new Solution().calculateOutputParams(2020);

        assertThat(year).isEqualTo(true);
    }

    @Test
    void testIncrement() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        IntStream.range(0, 10000).forEach(count -> executorService.execute(AsyncCounter::increase));

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

        assertEquals(10000, AsyncCounter.getCount(), "Synchronization check");
    }

}
