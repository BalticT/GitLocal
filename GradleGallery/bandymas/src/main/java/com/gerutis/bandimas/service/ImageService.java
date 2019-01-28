package com.gerutis.bandimas.service;


import com.gerutis.bandimas.entity.Catalog;
import com.gerutis.bandimas.entity.ImageModel;
import com.gerutis.bandimas.entity.Movie;
import com.gerutis.bandimas.entity.Tag;
import com.gerutis.bandimas.repository.ImageRepository;
import com.gerutis.bandimas.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
    public class ImageService {

        @Autowired
        ImageRepository imageRepository;

        @Autowired
        TagRepository tagRepository;

    static  List<ImageModel> paieska;


    public void paieska (String name, String type, String tagas, Model model) {


        Set<ImageModel>     tagsearch = new HashSet<>();
        Tag tag2 = tagRepository.findByName(tagas); // find jpql - transaction


//        List <Tag> tagopaieska = new ArrayList<>();

        if ( ( name.isEmpty() ) && (type.isEmpty() ) && (tagas.isEmpty() ) ){
            paieska = (List<ImageModel>) imageRepository.findAll();


        } else if ( ( !name.isEmpty()  ) && (type.isEmpty()) && (tagas.isEmpty() ) ) {

            paieska =  imageRepository.findByNameContaining(name);

        }

        else if ( ( name.isEmpty()  ) && (!type.isEmpty()) && (tagas.isEmpty() ) ) {

            paieska =  imageRepository.findByTypeContaining(type);

            for (ImageModel imageModel2 : paieska ) {
                if (imageModel2.getTags().equals(tagas)) {


                }
            }
        }




        else if ( ( name.isEmpty()  ) && (type.isEmpty()) && (!tagas.isEmpty() ) ) {

//            for (ImageModel tag : imageRepository.findAll() ) {
//                tag.getTags();
//            }




            if (tag2 != null) {


                List<ImageModel> find1 = (List<ImageModel>) imageRepository.findAll();


                for (ImageModel imageModel2 : find1) {

                    for (Tag best : imageModel2.getTags()) {

                        if (best.getName().equals(tagas)) {

//                        System.out.println(imageModel2.getName());

                            tagsearch.add(imageModel2);


                        }
                    }
                }


                for (ImageModel img1 : tagsearch) {

                    System.out.println(img1.getName());
                }

//            Set<ImageModel> useriai1 = new HashSet<ImageModel>(find1);
//
//useriai1.removeAll(tagsearch);

//            paieska.addAll(tagsearch);

                model.addAttribute("useriai", tagsearch);
//            tagopaieska = tagRepository.findByNameContaining(tagas);

//           for (Tag tagis : imageModel.getTags()) {
//
//           }

//                System.out.println(" kuku ");
//            model.addAttribute("useriai", tagopaieska);
            } else {

                System.out.println(tagas);

//                List<ImageModel> jaja = new ArrayList<>();
//                model.addAttribute("useriai", jaja);
            }
//            paieska =  imageRepository.findByTypeContaining(type);
        }

        else if ( ( !name.isEmpty()  ) && (!type.isEmpty()) && (tagas.isEmpty() ) ) {

            List<ImageModel> test = new ArrayList<>();

            test.addAll ( imageRepository.findByTypeContaining(name) ) ;
            test.addAll ( imageRepository.findByTypeContaining(type) ) ;

//            System.out.println("dar vienas");
        }

        else {


            if (tag2 != null) {

                List<ImageModel> last = imageRepository.findByTypeContaining(name) ;
                List<ImageModel> last2 = imageRepository.findByTypeContaining(type) ;

                paieska.addAll(last);
                paieska.addAll(last2);
            }



//            paieska = imageRepository.findByAllContaining (name, type);

//            System.out.println(name);
//            System.out.println(type);
//            System.out.println(tagas);
//            System.out.println("koks cia variantas");
        }


        if (tagsearch.size() > 0)  {
            model.addAttribute("useriai", tagsearch);
        } else if ((!tagas.isEmpty() && ( (name.isEmpty() ) || ( type.isEmpty() ) ) ) && (tag2 == null)  ) {

            model.addAttribute("useriai", tagsearch);
        }

        else if ( ( !name.isEmpty()  ) && (!type.isEmpty()) && (tagas.isEmpty() ) ) {

            List<ImageModel> test = new ArrayList<>();

            test.addAll ( imageRepository.findByTypeContaining(name) ) ;
            test.addAll ( imageRepository.findByTypeContaining(type) ) ;

//            System.out.println("dar vienas");

            Set<ImageModel> useriai = new HashSet<ImageModel>(test);
            model.addAttribute("useriai", useriai);

        }

        else if ( ( !name.isEmpty()  ) && (!type.isEmpty()) && (!tagas.isEmpty() ) ) {

            if (tag2 != null) {


                List<ImageModel> test = new ArrayList<>();

                test.addAll ( imageRepository.findByTypeContaining(name) ) ;
                test.addAll ( imageRepository.findByTypeContaining(type) ) ;

//                System.out.println("dar kitas");

                Set<ImageModel> useriai = new HashSet<ImageModel>(test);
                model.addAttribute("useriai", useriai);
            }
        }

        else {
            Set<ImageModel> useriai = new HashSet<ImageModel>(paieska);
            model.addAttribute("useriai", useriai);
        }


    }


        public ImageModel createnewtag1 (ImageModel imageModel, Catalog catalog) {



            if (!catalog.getMovies().isEmpty()) {
                System.out.println(catalog.getMovies().toString());
            }

            Set<String> allis = new HashSet<>();

            for (Movie mov : catalog.getMovies()) {
                if (!mov.getTitle().isEmpty()) {

                    allis.add(mov.getTitle());
                }
            }

            System.out.println(allis.size());


            if (allis.size() > 0 ) {

                for (String ggg : allis) {

                    Tag tag1;

//             tag1 = new Tag(ggg);
                    if (!ggg.isEmpty()) {


                    }


                    tag1 = tagRepository.findByName(ggg); // find jpql - transaction

                    if (tag1 != null)  {
                        imageModel.addTag(tag1);
                        System.out.println("toks pat tagas");
                    }    else {

                        if (!ggg.isEmpty()) {
                            try {
                                tag1 = new Tag(ggg);
                                System.out.println(ggg + " - veikia naujas tagas");
                                imageModel.addTag(tag1);
                            } catch ( Exception e ) {
                                System.out.println(e.getMessage());
                                throw new RuntimeException(e);

                            }

                        }
                    }



                }
            }



            return imageModel;
        }


    public ImageModel createnewtag2 (ImageModel imageModel, String multiCheckboxSelectedValues ) {



        String s = "";
        if (multiCheckboxSelectedValues != null) {


            s += multiCheckboxSelectedValues;
        }
        System.out.println(s);

//        List<String> all = new ArrayList<>();


        String[] splitted = s.split("\\W+");

        List<String> occ = new ArrayList<>();


        for (String word : splitted) {

            if ( (!word.isEmpty())  ) {
                occ.add(word);
            }
        }


//        occ.removeAll(allis );

//        System.out.println(occ.size());

        if (occ.size() > 0 ) {

            for (String ggg : occ) {

                Tag tag1;

//             tag1 = new Tag(ggg);

                tag1 = tagRepository.findByName(ggg); // find jpql - transaction

                if (tag1 != null) {
                    imageModel.addTag(tag1);
                    System.out.println(ggg + " - pasirinktas Tagas is checkboxo");
                }

            }
        }
            return  imageModel;
    }



        public Set<Tag> removesame (ImageModel img) {

            List<Tag> taguciai = (List<Tag>)  tagRepository.findAll();
            Set<Tag> tageriai = new HashSet<Tag>(taguciai);
            Set<Tag> geritagai  = new HashSet<Tag>( img.getTags());
            tageriai.removeAll(geritagai);

            return tageriai;
    }


    public Tag naujasTagas (Catalog catalog) {

            return null;
    }

}
