package com.spring.pfe.controllers;

import com.spring.pfe.models.*;
import com.spring.pfe.repository.DemandeRepository;
import com.spring.pfe.repository.FormationRepository;
import com.spring.pfe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/demande")
@CrossOrigin("*")
public class DemandeController {
    @Autowired
    DemandeRepository idemande;


    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private UserRepository iuser;

    @RequestMapping(value="/{users_id}", method= RequestMethod.POST)
    public Response<Demande> addDemandeUserr(@RequestBody Demande t , @PathVariable Long users_id) {
        try {
            User u = iuser.findById(users_id).orElse(null);

            t.setUsers(u);
            return new Response<Demande>("200", "Creat theme", idemande.save(t));

        } catch (Exception e) {
            return new Response<Demande>("406", e.getMessage(), null);
        }

    }       
    @RequestMapping(value="/add/{formation_id}/{users_id}", method= RequestMethod.POST)
    public Response<Demande> addDemanded(@RequestBody Demande t , @PathVariable Long formation_id,@PathVariable Long users_id) {
        try {
            Formation f = formationRepository.findById(formation_id).orElse(null);
            User u = iuser.findById(users_id).orElse(null);

    t.setFormationn(f);
    t.setUsers(u);

    t.setFormationn(f);
    t.setUsers(u);
    t.setActive(true);
    t.setDate(t.getDate());
    return new Response<Demande>("200", "Creat demande", idemande.save(t));


        } catch (Exception e) {
            return new Response<Demande>("406", e.getMessage(), null);
        }

    }
    @PutMapping("/updateFormation/{id}")
    public Response<Demande> updateFormation(@PathVariable("id") Long id,@RequestBody Demande d) {
        try {
            Demande oldDemande = idemande.findById(id).orElse(null);
            d.setUsers(d.getUsers() == null ? oldDemande.getUsers() : d.getUsers());
            d.setFormationn(d.getFormationn() == null ? oldDemande.getFormationn() : d.getFormationn());
            d.setId(id);
            d.setActive(true);

            return new Response<Demande>("200","Formation updated", idemande.save(d));
        }catch (Exception e){
            return new Response<Demande>("406", e.getMessage(), null);

        }
    }

    @GetMapping("/")
    public Response<List<Demande>> findAllDemande () {
        return new Response<List<Demande>>("200", "Get all demande", idemande.findAll());
    }

    @GetMapping("/Allformation")
    public Response<List<Formation>> findAllFormation () {
        return new Response<List<Formation>>("200", "Get all Formation", formationRepository.findAll());
    }

    @GetMapping("/all/{id}")
    public Response<List<Demande>> GetAllUserByFormation( @PathVariable("id") Long id) {
        try {
            return new Response<List<Demande>>("200", "Get all User by formaion", idemande.getAllUserParFormation(id));

        } catch (Exception e) {
            return new Response<List<Demande>>("406", e.getMessage(), null);
        }

    }


    @GetMapping("/active/{id}/{active}")
    public Response<List<Demande>> GetAllActive( @PathVariable("id") Long id,@PathVariable("active") Boolean active) {
        try {
            return new Response<List<Demande>>("200", "Get all User by formaion", idemande.getAllUserActive(id,active));

        } catch (Exception e) {
            return new Response<List<Demande>>("406", e.getMessage(), null);
        }

    }


    @GetMapping("/Notactive/{active}")
    public Response<List<Demande>> GetAllActived( @PathVariable("active") Boolean active) {
        try {
            return new Response<List<Demande>>("200", "Get all User by formaion", idemande.getAllNotActive(active));

        } catch (Exception e) {
            return new Response<List<Demande>>("406", e.getMessage(), null);
        }

    }

        @GetMapping("/hh/{users_id}")
    public Response<Demande> findById(@PathVariable Long users_id){

        try {

            return new Response<Demande>("200", "Get all users",  idemande.getAllsss(users_id));

        }catch (Exception e){
            return new Response<Demande>("406", e.getMessage(), null);

        }
    }

    @DeleteMapping("/delateDemande/{id}")
    public Response<Demande> DelateDemande ( @PathVariable("id") Long  id){

//Demande d = idemande.getAllsss(users_id);
        idemande.deleteById(id);
        try{
            return new Response<Demande>("200", "Etape deleted", null);
        } catch (Exception e){
            return new Response<Demande> ("406",e.getMessage(),null);
        }
    }
}
