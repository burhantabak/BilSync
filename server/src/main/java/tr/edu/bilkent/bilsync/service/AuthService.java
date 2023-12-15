package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.controller.controllerEntities.AuthenticationRequestBody;
import tr.edu.bilkent.bilsync.controller.controllerEntities.AuthenticationResponse;
import tr.edu.bilkent.bilsync.entity.UserEntity;
import tr.edu.bilkent.bilsync.repository.UserRepository;

@Service
public class AuthService {
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final MailService mailService;

    public AuthService(TokenService tokenService,
                       UserRepository userRepository, MailService mailService) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    public AuthenticationResponse authenticate(AuthenticationRequestBody request){
        String adminEmail = "admin@bilkent.edu.tr";
        String adminPassword = "admin";
        UserEntity userEntity = userRepository.findByEmail(request.email);
        if(request.email.equals(adminEmail) && request.password.equals(adminPassword)){
            return new AuthenticationResponse(tokenService.generateToken(adminEmail),
                    "admin", adminEmail,true);
        }
        if(userEntity != null && userEntity.getPassword().equals(request.password)){
            return new AuthenticationResponse(tokenService.generateToken(userEntity.getEmail()),
                    userEntity.getName(), userEntity.getEmail(),false);
        }
        return null;
    }

    public boolean register(UserEntity userEntity) {
        try {
            userRepository.save(userEntity);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * This functions check whether the givenClaimedEmail is true or not
     * @param claimedEmail
     * @return Url of the createNewPassword page, and givenClaimedEmail is false it will return Null
     */
    public boolean forgotPasswordUrl(String claimedEmail){
        UserEntity user = userRepository.findByEmail(claimedEmail);
        if(user==null){//if there is no user with this name it is null
            return false;
        }
        //token creation
        String tokenCreated = tokenService.generateToken(claimedEmail);
        String baseUrl = "http://127.0.0.1:5173";
        String endpoint = "/resetPassword?ticket=";
        String mailLink =  baseUrl+endpoint+tokenCreated;//here the token is returned
        //mail title
        String mailTitle = "Reset Password Link at BilSync";
        String mailBody = "Hello,\n Here is your password reset link:\n" + mailLink +"\n Bilsync Team :)";
        return mailService.sendMail(mailTitle,mailBody, claimedEmail);
    }

    /**
     * This method will check the claim that the specified token is valid
     * Additionally if the user email exists in database, user's password is changed
     * newPassword Requirements:
     * -must be nonEmpty
     * -must Consist of At Least 1 Big Keyword
     * @param token token that is returned from chat Frontend
     * @param newPassword
     * @return
     */
    public boolean changePassword(String token, String newPassword){
        //prechecking the token
        String username = tokenService.extractUsername(token);
        UserEntity user = userRepository.findByEmail(username);
        if(user == null){
            return false;
        }

        user.setPassword(newPassword);
        userRepository.save(user);
        //user's change is commited to db.
        return true;
    }
}
