package com.testukas.testukas.repository;

import com.testukas.testukas.entity.Pazymiai;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PazymiaiRepository extends CrudRepository<Pazymiai, Long> {
}
