package com.assessments.cicassesment.service;

import com.assessments.cicassesment.controller.CicController;
import com.assessments.cicassesment.entitiy.Cic;
import com.assessments.cicassesment.entitiy.RecipientEntity;
import com.assessments.cicassesment.repository.CicRepository;
import com.assessments.cicassesment.repository.RecipientRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Perform business logic for the rest api
 * @author Flora Makgaba
 */
@Service
public class CicService {

    private CicRepository cicRepository;
    private RecipientRepository recipientRepository;
    private static final Logger logger = Logger.getLogger(CicController.class.getName());

    /**
     * Class Constructor
     * @param cicRepository
     * @param recipientRepository
     */
    @Autowired
    public CicService(CicRepository cicRepository, RecipientRepository recipientRepository){
        logger.log(Level.INFO, "Injecting Entity Repositories");
        this.cicRepository = cicRepository;
        this.recipientRepository = recipientRepository;
    }

    /**
     *
     * @return list of all recipients in the database
     */
    public List<RecipientEntity> getAllRecipients() {
        logger.log(Level.INFO, "Getting a list of all registered " +
                "recipients in the database");
        return this.recipientRepository.findAll();
    }
    /**
     * Adds a new recipient to the database
     * The recipient had a name and email address
     * @param recipient
     * @See RecipientEntity
     */
    public void addRecipient(RecipientEntity recipient){
        logger.log(Level.INFO, "Adding a recipient to the database");
        this.recipientRepository.save(recipient);
    }

    /**
     * Gets a recipient by the id provided
     * @param recipientId
     * @return
     * @throws NotFoundException
     */
    public RecipientEntity getRecipient(Long recipientId) throws NotFoundException {
        logger.log(Level.INFO, "Getting the recipient associated with ID : "+ recipientId);
        return recipientRepository.findById(verifyRecipient(recipientId).getId()).get();
    }

    /**
     * Get recipient id using emailAddress
     * @param emailAddress
     * @return an Id belonging to a recipient using an email
     * @throws NotFoundException
     */
    public Long getRecipientIdByEmail(String emailAddress) throws NotFoundException{
        return recipientRepository.findByEmailAddress(emailAddress);
    }
    /**
     * Deletes a specific recipient with a provided id
     * @param recipientId
     * @throws NotFoundException
     */
    public void deleteRecipient(Long recipientId) throws NotFoundException{
        logger.log(Level.INFO, "Removing recipient with id :" + recipientId);
        recipientRepository.deleteById(verifyRecipient(recipientId).getId());
    }
    /**
     * Updates details of an existing recipient
     * i.e the name and email address
     * @param id
     * @param recipient
     */
    public void updateRecipient(Long id, RecipientEntity recipient){
        recipient.setId(id);
        recipientRepository.save(recipient);
    }
    /**
     * Add a new email belonging to a specific recipient
     * The recipient is identified by an id
     * @param recipientId
     * @param cic
     * @throws NotFoundException
     */
    public void addEmail(Long recipientId, Cic cic) throws NotFoundException{
        RecipientEntity recipient = getRecipient(recipientId);
        logger.log(Level.INFO, " The recipient email :  "+ recipient.getEmailAddress());
        cic.setRecipientEntity(recipient);
        cicRepository.save(cic);
    }
    /**
     *  Checks if a specific recipient exists in the database
     *  The recipient is identified by an id
     * @param recipientId
     * @return
     * @throws NotFoundException
     */
    private RecipientEntity verifyRecipient(Long recipientId) throws NotFoundException {
        Optional<RecipientEntity> cp = recipientRepository.findById(recipientId);
        return cp.orElseThrow(
                () ->  new NotFoundException("Unable to get Account with Code =  " + recipientId));

    }
}

