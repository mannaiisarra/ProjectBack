package com.spring.pfe.controllers;

import com.spring.pfe.models.Formation;
import com.spring.pfe.models.Response;
import com.spring.pfe.repository.FormationRepository;
import com.spring.pfe.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/formation")
@CrossOrigin("*")
public class FormationController {

    @Autowired
    private FormationRepository FormationRepository;
    @Autowired
    private StorageService storageService;

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public Response<Formation> addFormation(Formation p, @RequestParam("file") MultipartFile file) {
        try {
            if(p!=null) {
                Date d=new Date();
                String date=""+d.getDay()+d.getMonth()+d.getYear()+d.getHours()+d.getMinutes()+d.getSeconds();
                p.setPhoto(date+file.getOriginalFilename());
                storageService.store(file,date+file.getOriginalFilename());
                return new Response<Formation>("200", "Creat formation", FormationRepository.save(p));
            } else {
                return new Response<Formation>("500", "formation not found", null);
            }
        } catch (Exception e) {
            return new Response<Formation>("406", e.getMessage(), null);
        }

    }





    @GetMapping("/")
    public Response<List<Formation>> findAllFormation () {
        return new Response<List<Formation>>("200", "Get all Formation", FormationRepository.findAll());
    }

    @DeleteMapping("/deleteFormation/{id}")
    public Response<Formation> deleteFormation (@PathVariable("id") Long  id){

        FormationRepository.deleteById(id);
        try{
            return new Response<Formation>("200", "Formation deleted", null);
        } catch (Exception e){
            return new Response<Formation> ("406",e.getMessage(),null);
        }
    }

    @GetMapping("/findById/{id}")
    public Response<Formation> findById(@PathVariable Long id){
        try {

            return new Response<Formation>("200", "Get Formation by id", FormationRepository.findById(id).orElse(null));
        }catch (Exception e){
            return new Response<Formation>("406", e.getMessage(), null);

        }
    }

    @PutMapping("/updateFormation/{id}")
    public Response<Formation> updateFormation(@PathVariable("id") Long id, @RequestBody Formation f) {
        try {

            Formation oldUser = FormationRepository.findById(id).orElse(null);
            f.setTitre(f.getTitre() == null ? oldUser.getTitre() : f.getTitre());
            f.setDescription(f.getDescription() == null ? oldUser.getDescription() : f.getDescription());
            f.setPhoto(f.getPhoto() == null ? oldUser.getPhoto() : f.getPhoto());
            f.setDate_deDebut(f.getDate_deDebut() == null ? oldUser.getDate_deDebut() : f.getDate_deDebut());
            f.setDate_defin(f.getDate_defin() == null ? oldUser.getDate_defin() : f.getDate_defin());
            f.setId(id);
            return new Response<Formation>("200","Formation updated", FormationRepository.save(f));
        }catch (Exception e){
            return new Response<Formation>("406", e.getMessage(), null);

        }
    }

}

