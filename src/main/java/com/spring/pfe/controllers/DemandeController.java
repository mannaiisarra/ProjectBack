package com.spring.pfe.controllers;


import com.spring.pfe.models.*;
import com.spring.pfe.repository.DemandeRepository;
import com.spring.pfe.repository.FormationRepository;
import com.spring.pfe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/formation/demande")
public class DemandeController {

    @Autowired
    DemandeRepository idemande;

    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private UserRepository iuser;

    @RequestMapping(value="/add/{formation_id}/{users_id}", method= RequestMethod.POST)
    public Response<Demande> addDemande(@RequestBody Demande t , @PathVariable Long formation_id,@PathVariable Long users_id) {
        try {
            Formation f = formationRepository.findById(formation_id).orElse(null);
            User u = iuser.findById(users_id).orElse(null);

            t.setFormationn(f);
            t.setUsers(u);
                return new Response<Demande>("200", "Creat demande", idemande.save(t));

        } catch (Exception e) {
            return new Response<Demande>("406", e.getMessage(), null);
        }

    }
    @RequestMapping(value="/addUser/{users_id}", method= RequestMethod.POST)
    public Response<Demande> addDemandeUser(@RequestBody Demande t ,@PathVariable Long users_id) {
        try {
            User u = iuser.findById(users_id).orElse(null);

            t.setUsers(u);
            return new Response<Demande>("200", "Creat theme", idemande.save(t));

        } catch (Exception e) {
            return new Response<Demande>("406", e.getMessage(), null);
        }

    }


    @GetMapping("/")
    public Response<List<Demande>> findAllFormation () {
        return new Response<List<Demande>>("200", "Get all Formation", idemande.findAll());
    }

}
