package ohtu.authentication;

import ohtu.data_access.UserDao;
import ohtu.domain.User;
import ohtu.util.CreationStatus;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public CreationStatus createUser(String username, String password, String passwordConfirmation) {
        CreationStatus status = new CreationStatus();
        
        if (userDao.findByName(username) != null) {
            status.addError("username is already taken");
        }
        
        boolean containsCharacters = true;
        for (int i = 0; i < username.length(); i++) {       
            if (!Character.isLetter(username.charAt(i))) {
                containsCharacters = false;
            }
        } 
        
        if(!containsCharacters) {
            status.addError("username must consist of characters");
        }
        
        
        if (username.length()<3 ) {
            status.addError("username should have at least 3 characters");
        }
        
        boolean containsDigits = false;
        for (int i = 0; i < password.length(); i++) {       
            if (Character.isDigit(password.charAt(i))) {
                containsDigits = true;
            }
        }       
        
        if (password.length()<8 || !containsDigits ) {
            status.addError("password should have at least 8 characters");
        }
        
        if (!password.equals(passwordConfirmation)) {
            System.out.println("EIVÄT TÄSMÄÄ");
            status.addError("password and password confirmation do not match");
            //username is already taken, password and password confirmation do not match
        }

        if (status.isOk()) {
            userDao.add(new User(username, password));
        }
        
        return status;
    }

}
