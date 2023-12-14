package tr.edu.bilkent.bilsync.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import tr.edu.bilkent.bilsync.service.TransactionService;

@Configuration
@EnableScheduling
public class TransactionScheduler {

    @Autowired
    private TransactionService transactionService;

    @Scheduled(cron = "0 0 0 * * *") // Run every day at midnight
    public void updateTransactionState() {
        transactionService.updateTransactionStateBasedOnDates();
    }
}