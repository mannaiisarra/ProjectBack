package com.spring.pfe.controllers;


import com.spring.pfe.models.Demande;
import com.spring.pfe.models.Quiz;
import com.spring.pfe.models.Response;
import com.spring.pfe.models.User;
import com.spring.pfe.repository.DemandeRepository;
import com.spring.pfe.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {


    @Autowired
    QuizRepository iquiz;


    @RequestMapping(value="/add",method= RequestMethod.POST)
    public Response<Quiz> addQuiz(@RequestBody Quiz t ) {
        try {

            return new Response<Quiz>("200", "Creat quiz", iquiz.save(t));

        } catch (Exception e) {
            return new Response<Quiz>("406", e.getMessage(), null);
        }

    }


    @GetMapping("/GetQuiz/{id}")
    public Response<Quiz> GetQuizById(@PathVariable("id") Long id) {
        try {
            return new Response<Quiz>("200", "Get quiz By ID", iquiz.findById(id).orElse(null));

        } catch (Exception e) {
            return new Response<Quiz>("406", e.getMessage(), null);
        }

    }

    @GetMapping("/")
    public Response<List<Quiz>> findAllQuiz (){
        // return /*iCategory.findAll();*/

        try {

            return new Response<List<Quiz>>("200", "Get all quiz", iquiz.findAll());
        }catch (Exception e){
            return new Response<List<Quiz>>("406", e.getMessage(), null);

        }
    }
}
