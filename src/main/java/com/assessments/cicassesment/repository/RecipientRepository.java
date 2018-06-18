package com.assessments.cicassesment.repository;

import com.assessments.cicassesment.entitiy.RecipientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Flora Makgaba
 */
public interface RecipientRepository extends JpaRepository<RecipientEntity,Long> {

    Long findByEmailAddress(String emailAddress);

}
