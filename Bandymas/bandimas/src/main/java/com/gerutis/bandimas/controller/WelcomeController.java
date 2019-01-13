package com.gerutis.bandimas.controller;




import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gerutis.bandimas.entity.*;


import com.gerutis.bandimas.repository.BookService;
import com.gerutis.bandimas.repository.ImageRepository;
import com.gerutis.bandimas.repository.TagRepository;
import com.gerutis.bandimas.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WelcomeController {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    ImageService imageService;

    @Autowired
    BookService bookService;

    private String message = "Doing Good";

    static  List<ImageModel> paieska;

    @RequestMapping("/")
    public String root(Model model) {

        model.addAttribute("message", this.message);

        return "index";
    }


    @ModelAttribute("catalog")
    public Catalog getCatalog() {
        Catalog catalog = new Catalog();
        catalog.setMovies(new ArrayList<>());


        return catalog;
    }

    @RequestMapping("/upload")
    public String registrationForm( Model model,
                                    @ModelAttribute("imageModel") ImageModel imageModel
                                   ) {


//        String naujastagas, @ModelAttribute("catalog") Catalog catalog
//        @ModelAttribute("command") FormCommand command,  String multiCheckboxSelectedValues

        List<Tag> taguciai = (List<Tag>)  tagRepository.findAll();

        Set<Tag> tageriai = new HashSet<Tag>(taguciai);

        model.addAttribute("tageriai", tageriai);

//        model.addAttribute("multiCheckboxSelectedValues", multiCheckboxSelectedValues);



        return "uploadit";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String index(@RequestParam("fileUpload") MultipartFile file, RedirectAttributes redirectAttributes,
                        String tipas, String pavadinimas,
                        Model model, String multiCheckboxSelectedValues,
                        String naujastagas, @ModelAttribute("catalog") Catalog catalog ){
//        ImageModel imageModel,


        System.out.println(naujastagas);

        ImageModel imageModel = new ImageModel();

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
if (!ggg.isEmpty()) {}
    System.out.println(ggg);

                tag1 = tagRepository.findByName(ggg); // find jpql - transaction

                if (tag1 != null) {
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


//          model.addAttribute("multiCheckboxSelectedValues", multiCheckboxSelectedValues);
//
//            System.out.println(multiCheckboxSelectedValues);


        if (file.isEmpty() || tipas.isEmpty() || pavadinimas.isEmpty() ) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:upload";
        }

//        if (naujastagas.equals(multiCheckboxSelectedValues) ) {
//            System.out.println("nebus vienodi nes stringu massivas");
//        }

//        model.addAttribute("name", tipas);
//        model.addAttribute("authorities", pavadinimas);

//        System.out.println(tageriai);

//        model.addAttribute("tageriai", tageriai);

//        System.out.println(tageriai);

//        Tag role1 = tagRepository.findByName("ROLE_ADMIN");
//



//        List<Tag> roleSet = new ArrayList<>();
//
//        roleSet.add(tageriai);

//        System.out.println(roleSet.toString());

//        if (naujastagas != null) {



//        }




       String s = "";
        if (multiCheckboxSelectedValues != null) {


            s += multiCheckboxSelectedValues;
        }
        System.out.println(s);

//        List<String> all = new ArrayList<>();


        String[] splitted = s.split("\\W+");

        List<String> occ = new ArrayList<>();


    for (String word : splitted) {

        if ((!word.isEmpty()) || (!word.equals(naujastagas))) {
            occ.add(word);
        }
    }

//        all.addAll(occ);


        occ.removeAll(allis );

   System.out.println(occ.size());

if (occ.size() > 0 ) {

        for (String ggg : occ                 ) {

            Tag tag1;

//             tag1 = new Tag(ggg);

            tag1 = tagRepository.findByName(ggg); // find jpql - transaction

            if (tag1 != null) {
                imageModel.addTag(tag1);
                System.out.println(ggg + " - pasirinktas Tagas is checkboxo");
            }

        }
//            else {
//
//                if (!ggg.isEmpty()) {
//                    try {
//                        tag1 = new Tag(ggg);
//                        System.out.println(ggg + " - veikia naujas tagas");
//                        imageModel.addTag(tag1);
//                    } catch ( Exception e ) {
//                        System.out.println(e.getMessage());
//                        throw new RuntimeException(e);
//
//                    }
//
//                }
//            }


//                imageModel.addTag(tag1);
//            System.out.println(tag1.getName());
        }











//
//        if (multiCheckboxSelectedValues != null) {
//
//            System.out.println(multiCheckboxSelectedValues);
//
//            Tag tag1 = new Tag(multiCheckboxSelectedValues);
//            imageModel.addTag(tag1);
//        }


//        List<Tag> tageriai = (List<Tag>)  tagRepository.findAll();
//
//        for (Tag tagutis : tageriai) {
//            if (tagutis.getName().equals(multiCheckboxSelectedValues)) {
//
//                System.out.println(tagutis.getName() + " spausdinam chekboxo Taga");
//            }
//        }






















//                if (naujastagas != null) {
//
//            Tag tag2 = new Tag(naujastagas);
//            imageModel.addTag(tag2);
//        }

        if (naujastagas != null) {

            Tag tag;

            tag = tagRepository.findByName(naujastagas); // find jpql - transaction

            if (tag != null) {
                imageModel.addTag(tag);
                System.out.println("toks pat tagas");
            }


            else {

                if (!naujastagas.isEmpty()) {
                    try {
                        tag = new Tag(naujastagas);
                        System.out.println(naujastagas + " - veikia naujas tagas");
                        imageModel.addTag(tag);
                    } catch ( Exception e ) {
                        System.out.println(e.getMessage());
                       throw new RuntimeException(e);

                   }

                }
            }
        }

        try {

//            imageModel.setTags(roleSet);


            imageModel.setName(pavadinimas);
            imageModel.setType(tipas);
            imageModel.setPic(file.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        imageRepository.save(imageModel);


//imageService.createImage(imageModel);
        return "redirect:profile";
    }

//    @RequestMapping("/uploadStatus")
//    public String uploadStatus() {
//        return "uploadit";
//    }

//    @RequestMapping("/zahod")
//    public String zagodget(ImageModel imageModel) {
//        return "zahodit";
//    }
//
//    @RequestMapping(value = "/zahod", method = RequestMethod.POST)
//    public String zahodpost(@RequestParam("uploadpic") MultipartFile file, ImageModel imageModel ) {
//
//        try {
//            imageModel.setPic(file.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        imageService.createImage(imageModel);
//
//        return "welcome";
//    }


    @RequestMapping("/profile")
    public String welcome(@RequestParam(name = "name", required = false, defaultValue ="") String name,
                          @RequestParam(name = "type", required = false, defaultValue ="") String type,
                          @RequestParam(name = "tagas", required = false, defaultValue ="") String tagas,

                          Model model) {

ImageModel imageModel1;

        System.out.println(name);
        System.out.println(type);
        System.out.println(tagas);




        List <Tag> tagopaieska = new ArrayList<>();

        if ( ( name.isEmpty() ) && (type.isEmpty() ) && (tagas.isEmpty() ) ){
            paieska = (List<ImageModel>) imageRepository.findAll();


            Set<ImageModel> useriai = new HashSet<ImageModel>(paieska);
            model.addAttribute("useriai", useriai);
        } else if ( ( !name.isEmpty()  ) && (type.isEmpty()) && (tagas.isEmpty() ) ) {

            paieska =  imageRepository.findByNameContaining(name);

            Set<ImageModel> useriai = new HashSet<ImageModel>(paieska);
            model.addAttribute("useriai", useriai);
        }

        else if ( ( name.isEmpty()  ) && (!type.isEmpty()) && (tagas.isEmpty() ) ) {

            paieska =  imageRepository.findByTypeContaining(type);

            for (ImageModel imageModel2 : paieska ) {
                if (imageModel2.getTags().equals(tagas)) {


                }
            }
            Set<ImageModel> useriai = new HashSet<ImageModel>(paieska);
            model.addAttribute("useriai", useriai);
        }

        else if ( ( name.isEmpty()  ) && (type.isEmpty()) && (!tagas.isEmpty() ) ) {

//            for (ImageModel tag : imageRepository.findAll() ) {
//                tag.getTags();
//            }


            List<ImageModel>    find1 = (List<ImageModel>) imageRepository.findAll();

            List<ImageModel>     tagsearch = new ArrayList<>();

            for (ImageModel imageModel2 : find1 ) {

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

            System.out.println(" kuku ");
//            model.addAttribute("useriai", tagopaieska);

//            paieska =  imageRepository.findByTypeContaining(type);
        }

        else {

//           List<ImageModel> last = imageRepository.findByTypeContaining(name) ;
//            List<ImageModel> last2 = imageRepository.findByTypeContaining(type) ;
//
//            last2.removeAll(last);
//
//            useriai.addAll(last2);

//            paieska = imageRepository.findByAllContaining (name, type);

            paieska.addAll ( imageRepository.findByTypeContaining(name) ) ;
            paieska.addAll ( imageRepository.findByTypeContaining(type) ) ;

            Set<ImageModel> useriai = new HashSet<ImageModel>(paieska);
            model.addAttribute("useriai", useriai);
        }




//        List<ImageModel> useriai = (List<ImageModel>)  imageRepository.findAll();


//              ImageModel vienas = imageRepository.findById((long) 19).get();
//        model.addAttribute("vienas", vienas);


        return "welcome";
    }

//    @RequestMapping("/promile")
//    public String download(Model model, HttpServletResponse response) {
//
//        Document doc = documentDao.get(documentId);
//        try {
//            response.setHeader("Content-Disposition", "inline;filename=\"" +doc.getFilename()+ "\"");
//            OutputStream out = response.getOutputStream();
//            response.setContentType(doc.getContentType());
//            IOUtils.copy(doc.getContent().getBinaryStream(), out);
//            out.flush();
//            out.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//        return null;
//    }



    @RequestMapping("/profile/{id}/delete")
    public String deleteUser(@PathVariable Long id, Model model) {

        imageRepository.deleteById(id);

        return "redirect:/profile";
    }

    @RequestMapping("/profile/{id}/delete/{tagId}")
    public String deleteAuthority(@PathVariable Long id, @PathVariable Long tagId,  Model model) {

        ImageModel imageModel = imageRepository.findById(id).get();

        Tag tag = tagRepository.findById(tagId).get();

        imageModel.removeTag(tag);

        imageRepository.save(imageModel);
        return "redirect:/profile/" + id;
    }


    @RequestMapping("/profile/{id}")
    public String updateForm(@PathVariable Long id,  Model model, String multiCheckboxSelectedValues) {

        ImageModel imageModel = imageRepository.findById(id).get();
        model.addAttribute("multiCheckboxSelectedValues", multiCheckboxSelectedValues);
//
//        List<Tag> tags = (List<Tag>) tagRepository.findAll();
//
//        model.addAttribute("tags", tags);

        String pavadinimas =  imageModel.getName();
        String tipas = imageModel.getType();

        model.addAttribute("pavadinimas", pavadinimas);
        model.addAttribute("tipas", tipas);

        List<Tag> taguciai = (List<Tag>)  tagRepository.findAll();

        Set<Tag> tageriai = new HashSet<Tag>(taguciai);

        Set<Tag> geritagai  = new HashSet<Tag>( imageModel.getTags());

        tageriai.removeAll(geritagai);

        model.addAttribute("tageriai", tageriai);


        model.addAttribute("imageModel", imageModel);
        return "update";
    }






//    @RequestMapping("/profile/{id}")
//    public String updateForm(@PathVariable Long id, Model model) {
//
//        ImageModel imageModel = imageRepository.findById(id).get();
//
//
//
//        try{
//
//            FileOutputStream fos = new FileOutputStream("images/output.jpg");
//            fos.write(imageModel.getPic());
//            fos.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//        model.addAttribute("imageModel", imageModel);
//        return "update";
//    }





    @RequestMapping(value = "/profile/{id}", method = RequestMethod.POST)
    public String  editUseris(@PathVariable Long id, Model model,
                              String tipas, String pavadinimas,
                              String multiCheckboxSelectedValues,
                              String naujastagas,
                              @ModelAttribute("catalog") Catalog catalog ) {


       ImageModel imageModel = imageRepository.findById(id).get();

        model.addAttribute("imageModel", imageModel);

        //------------------------------------

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
                if (!ggg.isEmpty()) {}
                System.out.println(ggg);

                tag1 = tagRepository.findByName(ggg); // find jpql - transaction

                if (tag1 != null) {
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



        String s = "";
        if (multiCheckboxSelectedValues != null) {


            s += multiCheckboxSelectedValues;
        }
        System.out.println(s);

//        List<String> all = new ArrayList<>();


        String[] splitted = s.split("\\W+");

        List<String> occ = new ArrayList<>();


        for (String word : splitted) {

            if ((!word.isEmpty()) ) {
                occ.add(word);
            }
        }

//        all.addAll(occ);


        occ.removeAll(allis );

        System.out.println(occ.size());

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

        // -------------------------------
            imageModel.setName(pavadinimas);
            imageModel.setType(tipas);
//            imageModel.setPic(file.getBytes());

        imageRepository.save(imageModel);

        return "redirect:/profile/" + id;
    }


//    @RequestMapping(value = "/useris/{id}/new", method = RequestMethod.POST)
//    public String createArticle(@PathVariable Long id, @ModelAttribute("article") String name, String pavadinimas, String tekstas, Model model) {
//
//        Article article = new Article();
//        article.setPavadinimas(pavadinimas);
//        article.setTekstas(tekstas);
//
//        User user = userRepository.findById(id).get();
//
//        article = articleRepository.save(article);
//
//        user.addArticle(article);
//
//        userRepository.save(user);
//
//        model.addAttribute("authenticated", service.isAuthenticated());
//        model.addAttribute("user_id", user);
//        return "redirect:/admin/users/" ;
//    }

//    @RequestMapping( "/profile/{id}/new" )
//    public String  editUseris(@PathVariable Long id, Model model,
//                              String tipas, String pavadinimas ) {
//
//
//        ImageModel imageModel = imageRepository.findById(id).get();
//
//        model.addAttribute("imageModel", imageModel);
//
//
//
//        imageModel.setName(pavadinimas);
//        imageModel.setType(tipas);
////            imageModel.setPic(file.getBytes());
//
//        imageRepository.save(imageModel);
//
//        return "update";
//    }

    @ModelAttribute("multiCheckboxAllValues")
    public String[] getMultiCheckboxAllValues() {
        return new String[]{
                "Monday", "Tuesday", "Wednesday", "Thursday",
                "Friday", "Saturday", "Sunday"
        };
    }

//    @RequestMapping("/fooform")
//    public String getfoo (Model model) {
//
//        return "multi";
//    }

        @RequestMapping("/fooform")
        public String foobarPost(
                @ModelAttribute("command") FormCommand command,
                Model model, String multiCheckboxSelectedValues ){

        model.addAttribute("multiCheckboxSelectedValues", multiCheckboxSelectedValues);


//            System.out.println(multiCheckboxSelectedValues);
            String s = "";
            if (multiCheckboxSelectedValues != null) {


                s += multiCheckboxSelectedValues;
                                                    }
                System.out.println(s);

                List<String> all = new ArrayList<>();


                String[] splitted = s.split("\\W+");

                List<String> occ = new ArrayList<>();

                for (String word : splitted) {

if (!word.isEmpty()) {
    occ.add(word);
}
                }

                all.addAll(occ);



//
//                    List <String> listas = new ArrayList<>();
//            listas.add(multiCheckboxSelectedValues);

                System.out.println(all.size());

            for (String ggg : all                 ) {
                Tag tag = new Tag(ggg);

//                imageModel.addTag(tag);
                System.out.println(tag.getName());
            }

        return "multi";
        }

    @RequestMapping("/youform")
    public String youbarPost(
            @ModelAttribute("imageModel") ImageModel imageModel  ,
            Model model, String multiCheckboxSelectedValues ){

        model.addAttribute("multiCheckboxSelectedValues", multiCheckboxSelectedValues);


        System.out.println(multiCheckboxSelectedValues);

        return "test";
    }


    @RequestMapping("/books/all")
    public String showAll(Model model) {

        List<Book> books = (List<Book>) bookService.findAll();
        model.addAttribute("books", books);
        return "books/allBooks";
    }

    @RequestMapping("/books/create")
    public String showCreateForm(Model model, Book book) {
        List<Book> bookes = new ArrayList<>();
        BooksCreationDto booksForm = new BooksCreationDto(bookes);


            for (int i = 1; i <= 5; i++) {
                booksForm.addBook(new Book());

            }

        model.addAttribute("form", booksForm);
        return "books/createBooksForm";
    }

    @PostMapping("/books/save")
    public String saveBooks(@ModelAttribute BooksCreationDto form, Model model, Book booke ) {

//        if (form != null) {
//
//        }

//                for (Book booke : books) {
//            if (( booke.getTitle() == null) || (booke.getAuthor() == null)) {
//                this.books.remove(booke);
//            }
//        }
        List<Book> bookes = new ArrayList<>();
        BooksCreationDto modedlist = new BooksCreationDto(bookes);

        modedlist.getBooks().addAll(form.getBooks());

        for (Book book : modedlist.getBooks()) {

            if ((book.getTitle().isEmpty() ) || (book.getAuthor().isEmpty() )) {
                form.getBooks().remove(book);


                System.out.println(book.getTitle());
            }

        }
        System.out.println(modedlist.getBooks().size());

        System.out.println(form.getBooks().size());



        bookService.saveAll(form.getBooks());

        List<Book> books = (List<Book>) bookService.findAll();


        model.addAttribute("books", books);
        return "redirect:/books/all";
    }
}