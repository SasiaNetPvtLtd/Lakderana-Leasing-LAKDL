


import javax.mail.*;
import javax.mail.internet.*;


/**
 *
 * @author Chatura Jayawardena
 */
	
public class LAKDL_FA_CR_SMTPAuthenticator extends javax.mail.Authenticator {
      String username ;
      String password ;

    // Constructor 

    public LAKDL_FA_CR_SMTPAuthenticator(String username,String password){
           this .username = username;
            this.password = password;
	   }

      public PasswordAuthentication getPasswordAuthentication() {
         
                return new PasswordAuthentication(username, password);
        }
    }
