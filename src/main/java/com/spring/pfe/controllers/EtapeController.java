package com.spring.pfe.controllers;


import com.spring.pfe.models.Etape;
import com.spring.pfe.models.Response;
import com.spring.pfe.models.Theme;
import com.spring.pfe.repository.EtapeRepository;
import com.spring.pfe.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/etape")
public class EtapeController {
    @Autowired
    private EtapeRepository iTape;

    @GetMapping("/")
    public Response<List<Etape>> findAllFormation () {
        System.out.println("affichage");
        return new Response<List<Etape>>("200", "Get all etapes", iTape.findAll());
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public Response<Etape> addEtape(@RequestBody Etape p) {
        try {
            if(p!=null) {

                return new Response<Etape>("200", "Creat Etape", iTape.save(p));
            } else {
                return new Response<Etape>("500", "Etape not found", null);
            }
        } catch (Exception e) {
            return new Response<Etape>("406", e.getMessage(), null);
        }

    }

    @DeleteMapping("/deletEtape/{id}")
    public Response<Etape> DeletEtape (@PathVariable("id") Long  id){

        iTape.deleteById(id);
        try{
            return new Response<Etape>("200", "Etape deleted", null);
        } catch (Exception e){
            return new Response<Etape> ("406",e.getMessage(),null);
        }
    }

    @GetMapping("/findById/{id}")
    public Response<Etape> findById(@PathVariable Long id){
        try {

            return new Response<Etape>("200", "Get all Etape", iTape.findById(id).orElse(null));
        }catch (Exception e){
            return new Response<Etape>("406", e.getMessage(), null);

        }
    }

    @PutMapping("/updateEtape/{id}")
    public Response<Etape> updateEtape(@PathVariable("id") Long id, @RequestBody Etape e) {
        try {

            Etape oldUser = iTape.findById(id).orElse(null);
            e.setStep_titre(e.getStep_titre() == null ? oldUser.getStep_titre() : e.getStep_titre());
            e.setIdEtape(id);
            return new Response<Etape>("200","Etape updated", iTape.save(e));
        }catch (Exception et){
            return new Response<Etape>("406", et.getMessage(), null);

        }
    }




}
