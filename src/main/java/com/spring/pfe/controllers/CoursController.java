package com.spring.pfe.controllers;

import com.spring.pfe.models.Cours;
import com.spring.pfe.models.Demande;
import com.spring.pfe.models.Response;
import com.spring.pfe.repository.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cours")
@CrossOrigin("*")
public class CoursController {

    @Autowired
    CoursRepository icours;

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public Response<Cours> addDemandeUserr(@RequestBody Cours t ) {
        try {

            return new Response<Cours>("200", "Creat theme", icours.save(t));

        } catch (Exception e) {
            return new Response<Cours>("406", e.getMessage(), null);
        }

    }

}
