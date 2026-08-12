
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


class LAKDL_EMAIL_Thread extends Thread {
	
	// Mails List
	private List messages;
	
	
	
	public LAKDL_EMAIL_Thread(List messages) {
		this.messages = messages;
	}
	
	public void run() {
		try{
			Iterator itr = messages.iterator();
			
			while(itr.hasNext()){
				try{
					Message msg = (Message)itr.next();
					Transport.send(msg);
				}catch (MessagingException mex) {
					System.out.println(mex.toString());
				}
				yield();				
			}         
			
			
		}catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}
	
	
	
} 
