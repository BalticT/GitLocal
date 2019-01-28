package com.gerutis.bandimas.controller;




import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gerutis.bandimas.entity.*;


import com.gerutis.bandimas.repository.ImageRepository;
import com.gerutis.bandimas.repository.TagRepository;
import com.gerutis.bandimas.service.ImageService;
import com.gerutis.bandimas.service.UserService;
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
    UserService service;


    @RequestMapping("/user/upload")
    public String uploadForm( Model model,
                              @ModelAttribute("imageModel") ImageModel imageModel
                                   ) {

        List<Tag> taguciai = (List<Tag>)  tagRepository.findAll();
        Set<Tag> tageriai = new HashSet<Tag>(taguciai);

        model.addAttribute("tageriai", tageriai);
        model.addAttribute("authenticated", service.isAuthenticated());
        return "user/uploadit";
    }


    @RequestMapping(value = "/user/upload", method = RequestMethod.POST)
    public String index(@RequestParam("fileUpload") MultipartFile file, RedirectAttributes redirectAttributes,
                        String tipas, String pavadinimas,
                        Model model, String multiCheckboxSelectedValues,
                        String naujastagas, @ModelAttribute("catalog") Catalog catalog ){

        if (file.isEmpty() || tipas.isEmpty() || pavadinimas.isEmpty() ) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/user/upload";
        }



        ImageModel imageModel = new ImageModel();

        imageService.createnewtag1(imageModel, catalog);
        imageService.createnewtag2(imageModel, multiCheckboxSelectedValues );

        try {
            imageModel.setName(pavadinimas);
            imageModel.setType(tipas);
            imageModel.setPic(file.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        imageRepository.save(imageModel);

        return "redirect:/user/profile";
    }



    @RequestMapping("/user/profile")
    public String welcome(@RequestParam(name = "name", required = false, defaultValue ="") String name,
                          @RequestParam(name = "type", required = false, defaultValue ="") String type,
                          @RequestParam(name = "tagas", required = false, defaultValue ="") String tagas,
                          Model model) {

        model.addAttribute("authenticated", service.isAuthenticated());
        model.addAttribute("adminas", service.isadmin() );

imageService.paieska(name,type,tagas,model);

        return "user/welcome";
    }


    @RequestMapping("/admin/profile/{id}/delete")
    public String deleteUser(@PathVariable Long id, Model model) {

        imageRepository.deleteById(id);

        return "redirect:/user/profile";
    }

    @RequestMapping("/admin/profile/{id}/delete/{tagId}")
    public String deleteAuthority(@PathVariable Long id, @PathVariable Long tagId,  Model model) {

        ImageModel imageModel = imageRepository.findById(id).get();
        Tag tag = tagRepository.findById(tagId).get();

        imageModel.removeTag(tag);
        imageRepository.save(imageModel);

        return "redirect:/admin/profile/" + id;
    }


    @RequestMapping("/admin/profile/{id}")
    public String updateForm(@PathVariable Long id,  Model model) {

        ImageModel imageModel = imageRepository.findById(id).get();

        model.addAttribute("tageriai", imageService.removesame(imageModel));
        model.addAttribute("imageModel", imageModel);
        model.addAttribute("authenticated", service.isAuthenticated());

        String pavadinimas =  imageModel.getName();
        String tipas = imageModel.getType();

        model.addAttribute("pavadinimas", pavadinimas);
        model.addAttribute("tipas", tipas);

        return "admin/update";
    }



    @RequestMapping(value = "/admin/profile/{id}", method = RequestMethod.POST)
    public String  editUseris(@PathVariable Long id, Model model,
                              String tipas, String pavadinimas,
                              String multiCheckboxSelectedValues,
                              @ModelAttribute("catalog") Catalog catalog ) {


       ImageModel imageModel = imageRepository.findById(id).get();
       model.addAttribute("imageModel", imageModel);

imageService.createnewtag1(imageModel, catalog);
imageService.createnewtag2(imageModel, multiCheckboxSelectedValues);

            imageModel.setName(pavadinimas);
            imageModel.setType(tipas);

        imageRepository.save(imageModel);

        return "redirect:/admin/profile/" + id;
    }



    @ModelAttribute("multiCheckboxAllValues")
    public String[] getMultiCheckboxAllValues() {
        return new String[]{

        };
    }

    @ModelAttribute("catalog")
    public Catalog getCatalog() {
        Catalog catalog = new Catalog();
        catalog.setMovies(new ArrayList<>());

        return catalog;
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

}