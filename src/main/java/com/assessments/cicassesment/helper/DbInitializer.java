package com.assessments.cicassesment.helper;

import com.assessments.cicassesment.entitiy.Cic;
import com.assessments.cicassesment.entitiy.RecipientEntity;
import com.assessments.cicassesment.repository.CicRepository;
import com.assessments.cicassesment.repository.RecipientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * @author Flora Makgaba
 * Adds mock data to the database when the application starts up
 */
@Component
    public class DbInitializer implements CommandLineRunner {

        private RecipientRepository recipientRepository;
        private CicRepository cicRepository;
        private  java.util.Date today = new java.util.Date();
        private Date curent = new Date(today.getDate());

        @Autowired
        public DbInitializer(RecipientRepository recipientRepository, CicRepository cicRepository) {
            this.recipientRepository = recipientRepository;
            this.cicRepository = cicRepository;
        }

        @Override
        public void run(String... strings) {
            /**
             * Creating mock data
             */
            RecipientEntity recipient1 = new RecipientEntity("Mantse Makgaba", "mmakgaba@gmail.com");
            RecipientEntity recipient2 = new RecipientEntity("Mosa Makgaba", "momakgaba@gmail.com");
            RecipientEntity recipient3 = new RecipientEntity("Mokete Makgaba", "ketemakgaba@gmail.com");

            Cic cic1 = new Cic("e-mail","Statement",  "Please see attached your statement for the month", "accounts", curent, recipient1);
            Cic cic2 = new Cic("e-mail", "Promo" ,"We are coming to you soon. Watch our wall for details", "marketing", curent, recipient1);
            Cic cic3 = new Cic("e-mail",  "50% Off Promo","You get upto 50% off your purchases. Today Only", "marketing", curent, recipient2);
            Cic cic4 = new Cic("e-mail",  "Payment reminder", "Reminder :  Your payment is due on 02/07/2018 ", "accounts", curent, recipient2);

            /**
             * The sample data to the in-memory database
             */
            this.recipientRepository.save(recipient1);
            this.cicRepository.save(cic1);
            this.cicRepository.save(cic2);
            this.recipientRepository.save(recipient2);
            this.cicRepository.save(cic3);
            this.cicRepository.save(cic4);
            this.recipientRepository.save(recipient3);

        }
    }

