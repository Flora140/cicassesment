package com.assessments.cicassesment.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 *
 * @author Flora Makgaba
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Cic {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "cicId")
    private Long id;
    @NotBlank
    private String type;
    @NotBlank
    private String body;
    @NotBlank
    private String subject;
    @NotBlank
    private String sourceSystem;
    @CreatedDate
    @Temporal(TIMESTAMP)
    private Date createdDate;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "recipientId", nullable = false, updatable = false)
    private RecipientEntity recipientEntity;

    public Cic() {

    }

    public Cic(final String type,  final String subject, final String body, final String sourceSystem, final Date dateCreated, final RecipientEntity recipientEntity){
        this.type = type;
        this.subject = subject;
        this.body = body;
        this.sourceSystem = sourceSystem;
        this.createdDate = dateCreated;
        this.recipientEntity = recipientEntity;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public RecipientEntity getRecipientEntity() {
        return recipientEntity;
    }

    public void setRecipientEntity(RecipientEntity recipientEntity) {
        this.recipientEntity = recipientEntity;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
