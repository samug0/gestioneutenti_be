package it.infobasic.gestioneutenti.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.infobasic.gestioneutenti.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import it.infobasic.gestioneutenti.model.*;



@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/java/api")
public class UserController {
    
    
    @Autowired
    UserService userservice;


    @GetMapping(path="/user/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(required=true) Long id){

       Optional<User> find = userservice.findById(id);
        
        if(find.isPresent()){
            return new ResponseEntity<>(find.get() ,HttpStatus.OK);
        }

        else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="/user/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable(required=true) String email){
        Optional<User> find = userservice.findByEmail(email);

        if(find.isPresent()){
            return new ResponseEntity<>(find.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(path = "/users/loadUsers")
    public ResponseEntity<List<User>> getUserByCsv() throws IOException{

        userservice.loadCustomerData();
        return null;
        

        
    }



    
    
    
    @PostMapping(path="/user/add")
    public ResponseEntity User(@RequestBody User user){
        User newUser = userservice.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @GetMapping(path = "/users")
	public ResponseEntity<List<User>> findAll() {
		List<User> getAllUsers = userservice.findAll();

		if (getAllUsers != null) {
			return new ResponseEntity<>(getAllUsers, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}




    @DeleteMapping(path="/user/delete/{id}")
    public ResponseEntity deleteUserById(@PathVariable(required=true) Long id){
        userservice.deleteById(id);
        return new ResponseEntity("User deleted with ID:" + id, HttpStatus.OK);
    }


    @PutMapping(path="/user/updateuser/{id}")
        public ResponseEntity updateUser(@PathVariable(required=true) Long id,  @RequestBody User u){
            User newUtente = userservice.updateUser(id, u);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


    


