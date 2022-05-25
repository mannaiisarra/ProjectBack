package com.spring.pfe.controllers;


import com.spring.pfe.models.ERole;
import com.spring.pfe.models.Response;
import com.spring.pfe.models.Role;
import com.spring.pfe.models.User;
import com.spring.pfe.payload.request.LoginRequest;
import com.spring.pfe.payload.request.SignupRequest;
import com.spring.pfe.payload.response.JwtResponse;
import com.spring.pfe.payload.response.MessageResponse;
import com.spring.pfe.repository.RoleRepository;
import com.spring.pfe.repository.UserRepository;
import com.spring.pfe.security.jwt.JwtUtils;
import com.spring.pfe.security.services.UserDetailsImpl;
import com.spring.pfe.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.core.io.Resource;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private StorageService storageService;
    @Autowired
    private RoleRepository irole;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getAdress(),
                userDetails.getPhone(),
                userDetails.getPhoto(),
                roles));

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestParam("file") MultipartFile file, SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }



        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getPhone(),
                signUpRequest.getAdress(),
                signUpRequest.getPhoto(),

                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        System.out.println("here roles string : " + strRoles );
        System.out.println("here roles : " + signUpRequest.getRole() );
        Set<Role> roles = new HashSet<>();
        System.out.println("here roles string : " + roles );
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "2":
                        Role adminRole = roleRepository.findByName(ERole.APPRENANT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "1":
                        Role modRole = roleRepository.findByName(ERole.FORMATEUR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.APPRENANT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        Date d=new Date();
        String date=""+d.getDay()+d.getMonth()+d.getYear()+d.getHours()+d.getMinutes()+d.getSeconds();
        user.setPhoto(date+file.getOriginalFilename());
        storageService.store(file,date+file.getOriginalFilename());
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/")
    public Response<List<User>> findAllUsers (){
        // return /*iCategory.findAll();*/

        try {

            return new Response<List<User>>("200", "Get all Categories", userRepository.findAll());
        }catch (Exception e){
            return new Response<List<User>>("406", e.getMessage(), null);

        }
    }


    @DeleteMapping("/deleteUser/{id}")
    public Response<User> deleteUser (@PathVariable("id") Long  id){

        userRepository.deleteById(id);
        try{
            return new Response<User>("200", "User deleted", null);
        } catch (Exception e){
            return new Response<User> ("406",e.getMessage(),null);
        }
    }

    @GetMapping("/findById/{id}")
    public Response<User> findById(@PathVariable Long id){
            try {

        return new Response<User>("200", "Get all users", userRepository.findById(id).orElse(null));
    }catch (Exception e){
        return new Response<User>("406", e.getMessage(), null);

    }
    }

    @PutMapping("/updateUser/{id}")
    public Response<User> updateUsers(@PathVariable("id") Long id, @RequestBody User c) {
        try {
       // System.out.println("here update  id is "+id+" aadress is  "+c.getAdress());
        //User oldUser = new User();
        User oldUser = userRepository.findById(id).orElse(null);
        c.setUsername(c.getUsername() == null ? oldUser.getUsername() : c.getUsername());
        c.setEmail(c.getEmail() == null ? oldUser.getEmail() : c.getEmail());
        c.setAdress(c.getAdress() == null ? oldUser.getAdress() : c.getAdress());
        c.setAdress(c.getPhone() == null ? oldUser.getPhone() : c.getPhone());
        c.setPassword(oldUser.getPassword());
        c.setRoles(oldUser.getRoles());
        c.setPhoto(c.getPhoto() == null ? oldUser.getPhoto() : c.getPhoto());
        c.setId(id);
            return new Response<User>("200","User updated", userRepository.save(c));




        }catch (Exception e){

            return new Response<User>("406", e.getMessage(), null);

        }
    }
    @PostMapping("/add")
    public Response<User> addUser(User c, @RequestParam("file") MultipartFile file) {

        Date d=new Date();
        String date=""+d.getDay()+d.getMonth()+d.getYear()+d.getHours()+d.getMinutes()+d.getSeconds();
        c.setPhoto(date+file.getOriginalFilename());
        storageService.store(file,date+file.getOriginalFilename());
        return  new Response<User>("200","create user",userRepository.save(c));
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
    @GetMapping("/roles")
    public Response<List<Role>> findAllRoles (){
        // return /*iCategory.findAll();*/

        try {

            return new Response<List<Role>>("200", "Get all Categories", irole.findAll());
        }catch (Exception e){
            return new Response<List<Role>>("406", e.getMessage(), null);

        }
    }


}

