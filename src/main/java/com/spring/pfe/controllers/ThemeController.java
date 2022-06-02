package com.spring.pfe.controllers;


import com.spring.pfe.models.Formation;
import com.spring.pfe.models.Response;
import com.spring.pfe.models.Theme;
import com.spring.pfe.repository.FormationRepository;
import com.spring.pfe.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/theme")
public class ThemeController {

    @Autowired
    private ThemeRepository iTheme;
    @Autowired
    private FormationRepository formationRepository;

    @RequestMapping(value="/add/{id_formation}", method= RequestMethod.POST)
    public Response<Theme> addTheme(@RequestBody /*@RequestParam("file") MultipartFile file*/Theme t , @PathVariable Long id_formation) {
        try {
            if(t!=null) {
                Formation f = formationRepository.findById(id_formation).orElse(null);
                t.setFormation(f);
              /*  String fileName = storageService.fileName(file);
                storageService.store(file,fileName);
                f.setPhoto(fileName);*/
                return new Response<Theme>("200", "Creat theme", iTheme.save(t));
            } else {
                return new Response<Theme>("500", "Theme not found", null);
            }
        } catch (Exception e) {
            return new Response<Theme>("406", e.getMessage(), null);
        }

    }


    @GetMapping("/")
    public Response<List<Theme>> findAllFormation () {
        System.out.println("affichage");
        return new Response<List<Theme>>("200", "Get all theme", iTheme.findAll());
    }


    @DeleteMapping("/deleteTheme/{id}")
    public Response<Theme> ThemeUser (@PathVariable("id") Long  id){

        iTheme.deleteById(id);
        try{
            return new Response<Theme>("200", "Theme deleted", null);
        } catch (Exception e){
            return new Response<Theme> ("406",e.getMessage(),null);
        }
    }

    @GetMapping("/findById/{id}")
    public Response<Theme> findById(@PathVariable Long id){
        try {

            return new Response<Theme>("200", "Get all Theme", iTheme.findById(id).orElse(null));
        }catch (Exception e){
            return new Response<Theme>("406", e.getMessage(), null);

        }
    }

    @PutMapping("/updateTheme/{id}")
    public Response<Theme> updateUsers(@PathVariable("id") Long id, @RequestBody Theme c) {
        try {

            Theme oldUser = iTheme.findById(id).orElse(null);
            c.setTheme_titre(c.getTheme_titre() == null ? oldUser.getTheme_titre() : c.getTheme_titre());
            c.setFormation(c.getFormation() == null ? oldUser.getFormation() : c.getFormation());
            c.setId(id);
            return new Response<Theme>("200","Theme updated", iTheme.save(c));
            }catch (Exception e){
            return new Response<Theme>("406", e.getMessage(), null);

        }
    }

    @GetMapping("/chercher/{id}")
    public Response<List<Theme>> getAllThemeByFormation(@PathVariable("id") Long id) {

        return  new Response<List<Theme>>("200","get formation by theme",iTheme.getAllThemeByFormation(id));

    }


}
