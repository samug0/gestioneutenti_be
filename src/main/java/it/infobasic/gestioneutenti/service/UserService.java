package it.infobasic.gestioneutenti.service;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import it.infobasic.gestioneutenti.model.User;
import it.infobasic.gestioneutenti.repository.UtenteRepository;




@Service
public class UserService  {


    @Autowired
    UtenteRepository utenteRepository;

   
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    
    
   
    public Optional<User> findById(Long id){

        return utenteRepository.findById(id);

    }

    public Optional<User> findByEmail(String email){
        return utenteRepository.findByEmail(email);
    }

    
    public void deleteById(Long id){
        utenteRepository.deleteById(id);
    }


    public User addUser(User user){
        user.setData_registrazione(LocalDate.now());
        String pwd_encoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(pwd_encoded);
        return utenteRepository.save(user);
    }


    public List<User> findAll() {
		return utenteRepository.findAll();
	}



    
    public void loadCustomerData() throws IOException{
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader("src\\main\\resources\\users.csv"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        while((line = br.readLine()) != null){
            String[] data = line.split(",");
            User u = new User();
            u.setUsername(data[0]);
            u.setEmail(data[1]);
            String encode_pwd = passwordEncoder.encode(data[2]);
            u.setPassword(encode_pwd);
            u.setData_nascita(LocalDate.parse(data[3], formatter));
            u.setData_registrazione(LocalDate.parse(data[4], formatter));
            utenteRepository.save(u);



        } 
    }


    public User updateUser(Long id, User user){
        Optional <User> userSelected = utenteRepository.findById(id);

        if(userSelected.isPresent()){
            User userToUpdate = userSelected.get();
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setData_nascita(user.getData_nascita());
            utenteRepository.save(userToUpdate);
            return userToUpdate;
        }else{
            try {
                throw new Exception("User not updated!");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return user;

    }
    



    
}
