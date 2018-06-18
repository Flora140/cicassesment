package com.assessments.cicassesment.controller;

import com.assessments.cicassesment.entitiy.Cic;
import com.assessments.cicassesment.entitiy.RecipientEntity;
import com.assessments.cicassesment.service.CicService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Flora Makgaba
 * This is an open API.
 */
@CrossOrigin(maxAge = 48)
@RestController
@RequestMapping("/api/cic")
public class CicController {
    private CicService service;
    private static final Logger logger = Logger.getLogger(CicController.class.getName());
    /**
     *  Injecting the Service
     * @param service
     */
    @Autowired
    public CicController(CicService service){
        this.service = service;
    }
    /**
     * Adds/Registers a new recipient
     * @param recipient
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addRecipient(@RequestBody RecipientEntity recipient){
        service.addRecipient(recipient);
    }

    /**
     *  Gets a list of all Entities/Recipients
     * @return gives a Json String of all recipients in the database
     */
    @GetMapping(path=("/getallrecipients"),produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RecipientEntity> getRecipients(){
        return  service.getAllRecipients();
    }

    /**
     *
     * @param id
     * @return gives recipient details based on an id provided
     * @throws NotFoundException
     */
    @GetMapping(path = ("{id}"),produces = MediaType.APPLICATION_JSON_VALUE)
    public RecipientEntity getRecipient(@PathVariable("id") Long id) throws NotFoundException{
        return  service.getRecipient(id);
    }

    /**
     *
     * @param emailAddress
     * @return
     * @throws NotFoundException
     */
    @GetMapping(path = ("getid/{emailAddress}"), produces = MediaType.APPLICATION_JSON_VALUE)
    public Long getRecipient(@PathVariable("emailAddress") String emailAddress) throws NotFoundException{
        return  service.getRecipientIdByEmail(emailAddress);
    }

    /**
     * Stores email information in the database
     * @param id
     * @param cic
     * @throws NotFoundException
     */
    @PostMapping(path = "{id}/send", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void sendMail(@PathVariable("id") Long id,
                         @RequestBody Cic cic) throws NotFoundException
    {
       service.addEmail(id, cic);
    }

    /**
     *  Edit an existing recipient
     * @param id
     * @param recipient
     */
    @PutMapping(path = "edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void editRecipient(@PathVariable("id") Long id,
                              @RequestBody RecipientEntity recipient){
        service.updateRecipient(id, recipient);
    }

    /**
     *  Deletes a recipient
     * @param id
     * @throws NotFoundException
     */
    @DeleteMapping(path = "delete/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteRecipient(@PathVariable("id") Long id) throws NotFoundException {
        service.deleteRecipient(id);
    }

}
