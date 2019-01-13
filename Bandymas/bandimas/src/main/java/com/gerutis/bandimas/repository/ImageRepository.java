package com.gerutis.bandimas.repository;



import com.gerutis.bandimas.entity.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ImageRepository extends CrudRepository<ImageModel, Long> {

    ImageModel findByName(String name);

    List<ImageModel> findByNameContaining (String name);
    List<ImageModel> findByTypeContaining (String type);

//    List<ImageModel> findByAllContaining (String name, String type);
}
