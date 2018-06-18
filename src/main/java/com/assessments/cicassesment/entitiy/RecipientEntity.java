package com.assessments.cicassesment.entitiy;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Flora Makgaba
 */
@Entity
@NamedQuery(name = "RecipientEntity.findByEmailAddress",
        query = "select u.id from RecipientEntity u where u.emailAddress = ?1")
@Table(name = "recipient")
public class RecipientEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    @Column(unique = true)
    private String emailAddress;

    @OneToMany(
            mappedBy = "recipientEntity",
            cascade = CascadeType.ALL)
    private List<Cic> recipientEntity = new ArrayList<>();


    public RecipientEntity(){

    }

    public RecipientEntity(final String name, final String emailAddress){
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<Cic> getEmailEntities() {
        return recipientEntity;
    }

    public void setEmailEntities(List<Cic> emailEntities) {
        this.recipientEntity = emailEntities;
    }
}
