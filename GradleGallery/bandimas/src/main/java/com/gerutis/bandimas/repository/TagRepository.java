package com.gerutis.bandimas.repository;

import com.gerutis.bandimas.entity.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, Long> {

    Tag findByName(String name);

    List<Tag> findByNameContaining (String name);
}