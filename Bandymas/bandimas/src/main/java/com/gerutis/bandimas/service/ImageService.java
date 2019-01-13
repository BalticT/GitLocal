package com.gerutis.bandimas.service;


import com.gerutis.bandimas.entity.ImageModel;
import com.gerutis.bandimas.entity.Tag;
import com.gerutis.bandimas.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
    public class ImageService {

        @Autowired
        ImageRepository imageRepository;

        public ImageModel createImage (ImageModel imageModel) {



//            imageModel.setName("pavadinimas");
//            imageModel.setType("tipas");
//        imageModel.setPic( imageModel.getPic());

            return   imageRepository.save(imageModel);
        }



}
