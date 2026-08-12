// DEVELOP BY :  DISNAKA FOR OFSCL FACTORING    DATE:2011-11-08


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class LAKDL_FA_MIS_report_generation_email_save extends HttpServlet {
	
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
	String reqstr;
	ServletOutputStream out = null;
	Statement stmt = null;
	public ResultSet rs = null;
	BufferedWriter log=null;
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
		String m_class_url = "";
		String m_fschema_name = "";
		String m_schema_name = "";
		
		try {
			
			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			conn.setAutoCommit(false);
			//**************************************************************		
			
			m_fschema_name = m_sn_methods.client_name.trim();
			m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
			String m_username = m_sn_methods.username;
			String m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
			
			m_msg = "'Information saved successfully'";
			//PDFConversion pdfConversion = new PDFConversion();
			stmt = conn.createStatement();
			
			
			
			
			String smtp_host_name = "";
			String smtp_port      = "";
			String smtp_auth_user = "";
			String smtp_auth_pwd  = "";
			String display_name   = "";
			
			//int m_num = 0;
			//String m_scr_num = "0";
			
			
			rs = stmt.executeQuery("SELECT A.SMTP_HOST_NAME, A.SMTP_PORT, A.SMTP_AUTH_USER, A.SMTP_AUTH_PWD, "+
				" A.DISPLAY_NAME "+
				" FROM "+m_schema_name+".FA_CO_MAS_EMAIL_SERVER_DET A");
			
			if(rs.next()){
				smtp_host_name = rs.getString(1);
				smtp_port      = rs.getString(2);
				smtp_auth_user = rs.getString(3);
				smtp_auth_pwd  = rs.getString(4);
				display_name   = rs.getString(5);
				
			}
			Properties props = new Properties();
			
			props.put("mail.smtp.host", smtp_host_name);
			
			props.put("mail.smtp.auth", "true");
			//props.put("mail.smtp.auth.mechanisms", "NTLM");
			//props.put("mail.smtp.sasl.authorizationid", smtp_auth_user.substring(0,smtp_auth_user.lastIndexOf("@")));
			props.put("mail.smtp.port", smtp_port); 
			props.put("mail.smtp.starttls.enable", "false");
			props.put("mail.smtp.socketFactory.fallback", "false");    
			props.setProperty("mail.smtp.quitwait", "false");    
			props.setProperty("mail.smtp.sasl.enable", "true");    
			props.put("mail.smtp.sendpartial", "true");
			
			
			
			
			//NETFAC_FA_CR_SMTPAuthenticator auth = new NETFAC_FA_CR_SMTPAuthenticator(smtp_auth_user.substring(0,smtp_auth_user.lastIndexOf("@")),smtp_auth_pwd);
			LAKDL_FA_CR_SMTPAuthenticator auth = new LAKDL_FA_CR_SMTPAuthenticator(smtp_auth_user,smtp_auth_pwd);
			
			
			Session mail_session = Session.getInstance(props,auth);
			//Session mail_session = Session.getInstance(props,null);
			mail_session.setDebug(false);
			
			
			
			
			
			String file_upload_path             = "D:\\SasiaNet_Products\\NetAsset\\OFSCL\\UPLOAD\\";
			
			
			String m_rep_id = m_sn_methods.met_formdata(reqstr,"REP_ID");
			String m_date   = m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_DD")+"-"+
				m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_MM")+"-"+
				m_sn_methods.met_formdata(reqstr,"TXT_FROM_DATE_YY");
			
			
			
			
			/*String m_today = null;
			rs = stmt.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ");
			if(rs.next()){
				m_today=rs.getString(1);
			}
			rs.close();*/
			
			
			String m_filename ="";
			String txtFpath = "";
			String pdfFpath = "";
			String txtFolderpath = "";
			String pdfFolderpath = "";
			
			String content = "";
			
			//callstmt=conn.prepareCall("BEGIN "+m_schema_name+".FA_MIS_SAVE_BULK_REPORTS_GEN(:1,:2,:3,:4); END;");
			String m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"NUM_CHKS");
			int m_num=Integer.parseInt(m_scr_num);
			
			List messages = new ArrayList();
			
			if(m_num>0){
				
				//callstmt1.registerOutParameter(1,java.sql.Types.CHAR);
				for(int i=1;i<=m_num;i++){
					
					String m_facility_no              = m_sn_methods.met_formdata(reqstr, "FACILITY_NO_"+i);
					String m_client_code              = m_sn_methods.met_formdata(reqstr, "CLIENT_CODE_"+i);
					String m_email                    = m_sn_methods.met_formdata(reqstr, "TXT_EMAIL_NO_"+i);
					String m_status                   = m_sn_methods.met_formdata(reqstr,"RECEIVED_"+i);
					m_email = m_email.replaceAll("%40","@");
					
					
					if (!m_rep_id.equals("ALL")) {
						
						txtFolderpath= file_upload_path + "Client_Statements\\"+m_rep_id+"\\"+m_date+"\\"+m_client_code+"("+m_facility_no+")\\TXT";  
						pdfFolderpath = file_upload_path + "Client_Statements\\"+m_rep_id+"\\"+m_date+"\\"+m_client_code+"("+m_facility_no+")\\PDF";
						
						
						
						if(m_status.equals("on")){
							
							txtFpath = txtFolderpath+"\\"+m_client_code+"-"+m_rep_id+".txt";
							pdfFpath = pdfFolderpath+"\\"+m_client_code+"-"+m_rep_id+".pdf";
							
							Message msg = new MimeMessage(mail_session);
							
							InternetAddress addressFrom = new InternetAddress(smtp_auth_user, display_name);
							msg.setFrom(addressFrom);
							
							InternetAddress addressTo = new InternetAddress(m_email);
							msg.setRecipient(Message.RecipientType.TO, addressTo);
							
							// Setting the Subject and Content Type
							
							msg.setSubject("Report");
							BodyPart messageBodyPart = new MimeBodyPart();
							messageBodyPart.setText(" Auto generated email");
							Multipart multipart = new MimeMultipart();
							multipart.addBodyPart(messageBodyPart);
							
							// Add atachment to the message
							
							messageBodyPart = new MimeBodyPart();
							
							
							
							System.out.println(txtFpath);
							File attachmentTxt = new File(txtFpath);
							if(!attachmentTxt.exists()){
								System.out.println("File "+txtFpath+ " is not exists");
								continue;
							}
							
							File attachmentPdf = new File(pdfFpath);
							if(!attachmentPdf.exists()){
								System.out.println("File "+pdfFpath+ " is not exists");
								continue;
							}
							
							DataSource sourceTxt = new FileDataSource(attachmentTxt);
							DataSource sourcePdf = new FileDataSource(attachmentPdf);
							
							messageBodyPart.setDataHandler(new DataHandler(sourceTxt));
							messageBodyPart.setFileName(attachmentTxt.getName());
							multipart.addBodyPart(messageBodyPart);
							
							messageBodyPart = new MimeBodyPart();
							
							messageBodyPart.setDataHandler(new DataHandler(sourcePdf));
							messageBodyPart.setFileName(attachmentPdf.getName());
							multipart.addBodyPart(messageBodyPart);
							
							
							// Completing the  message and sending it
							
							msg.setContent(multipart);
							messages.add(msg);		
							
							/*new FileOutputStream(txtFpath);
							String logFile =txtFpath;*/
							
							
							
							
							
						}
					}
					else {
						
						
						
						if(m_status.equals("on")){
							
							
							rs = stmt.executeQuery("SELECT REPORT_ID "+
								" FROM "+m_schema_name+".FA_MIS_EMAIL_REPORTS_LOG A"+
								" WHERE A.CLIENT_CODE = '"+m_client_code+"'"+
								" AND A.FACILITY_NO = '"+m_facility_no+"'"+
								" AND A.RUN_DATE = TO_DATE('"+m_date+"','DD-MM-YYYY')");
							
							
							while(rs.next()){
								
								m_rep_id = rs.getString(1);
								
								
								
								txtFolderpath= file_upload_path + "Client_Statements\\"+m_rep_id+"\\"+m_date+"\\"+m_client_code+"("+m_facility_no+")\\TXT";  
								pdfFolderpath = file_upload_path + "Client_Statements\\"+m_rep_id+"\\"+m_date+"\\"+m_client_code+"("+m_facility_no+")\\PDF";
								
								
								
								txtFpath = txtFolderpath+"\\"+m_client_code+"-"+m_rep_id+".txt";
								pdfFpath = pdfFolderpath+"\\"+m_client_code+"-"+m_rep_id+".pdf";
								
								Message msg = new MimeMessage(mail_session);
								
								InternetAddress addressFrom = new InternetAddress(smtp_auth_user, display_name);
								msg.setFrom(addressFrom);
								
								InternetAddress addressTo = new InternetAddress(m_email);
								msg.setRecipient(Message.RecipientType.TO, addressTo);
								
								// Setting the Subject and Content Type
								
								msg.setSubject("Report");
								BodyPart messageBodyPart = new MimeBodyPart();
								messageBodyPart.setText(" Auto generated email");
								Multipart multipart = new MimeMultipart();
								multipart.addBodyPart(messageBodyPart);
								
								// Add atachment to the message
								
								messageBodyPart = new MimeBodyPart();
								
								
								
								System.out.println(txtFpath);
								File attachmentTxt = new File(txtFpath);
								if(!attachmentTxt.exists()){
									System.out.println("File "+txtFpath+ " is not exists");
									continue;
								}
								
								File attachmentPdf = new File(pdfFpath);
								if(!attachmentPdf.exists()){
									System.out.println("File "+pdfFpath+ " is not exists");
									continue;
								}
								
								DataSource sourceTxt = new FileDataSource(attachmentTxt);
								DataSource sourcePdf = new FileDataSource(attachmentPdf);
								
								messageBodyPart.setDataHandler(new DataHandler(sourceTxt));
								messageBodyPart.setFileName(attachmentTxt.getName());
								multipart.addBodyPart(messageBodyPart);
								
								messageBodyPart = new MimeBodyPart();
								
								messageBodyPart.setDataHandler(new DataHandler(sourcePdf));
								messageBodyPart.setFileName(attachmentPdf.getName());
								multipart.addBodyPart(messageBodyPart);
								
								
								// Completing the  message and sending it
								
								msg.setContent(multipart);
								messages.add(msg);		
								
								
								
							}
							
							
						}
						
					}
					
					
					
				}
				
				
			}
			
			
			//callstmt.close();
			
			
			//conn.commit();
			
			List thread1  = new ArrayList();
			List thread2  = new ArrayList();
			List thread3  = new ArrayList();
			List thread4  = new ArrayList();
			List thread5  = new ArrayList();
			List thread6  = new ArrayList();
			List thread7  = new ArrayList();
			List thread8  = new ArrayList();
			List thread9  = new ArrayList();
			List thread10 = new ArrayList();
			List thread11 = new ArrayList();
			List thread12 = new ArrayList();
			List thread13 = new ArrayList();
			List thread14 = new ArrayList();
			List thread15 = new ArrayList();
			List thread16 = new ArrayList();
			List thread17 = new ArrayList();
			List thread18 = new ArrayList();
			List thread19 = new ArrayList();
			List thread20 = new ArrayList();
			
			
			Iterator itr = messages.iterator();
			while(itr.hasNext()){
				Message msg = (Message)itr.next();
				thread1.add(msg);
				
				if(itr.hasNext()){
					msg = (Message)itr.next();
					thread2.add(msg);
					
				}
				
				if(itr.hasNext()){
					msg = (Message)itr.next();
					thread3.add(msg);
					
				}
				
				if(itr.hasNext()){
					msg = (Message)itr.next();
					thread4.add(msg);
					
				}
				
				if(itr.hasNext()){
					msg = (Message)itr.next();
					thread5.add(msg);
					
				}
				
				if(itr.hasNext()){
					msg = (Message)itr.next();
					thread6.add(msg);
					
				}
				
				if(itr.hasNext()){
					msg = (Message)itr.next();
					thread7.add(msg);
					
				}
				
				if(itr.hasNext()){
					msg = (Message)itr.next();
					thread8.add(msg);
					
				}
				
				if(itr.hasNext()){
					msg = (Message)itr.next();
					thread9.add(msg);
					
				}
				
				if(itr.hasNext()){
					msg = (Message)itr.next();
					thread10.add(msg);
					
				}
				
				if(itr.hasNext()){
					msg = (Message)itr.next();
					thread11.add(msg);
					
				}
				
				if(itr.hasNext()){
					msg = (Message)itr.next();
					thread12.add(msg);
					
				}
				
				if(itr.hasNext()){
					msg = (Message)itr.next();
					thread13.add(msg);
					
				}
				
				if(itr.hasNext()){
					msg = (Message)itr.next();
					thread14.add(msg);
					
				}
				
				if(itr.hasNext()){
					msg = (Message)itr.next();
					thread15.add(msg);
					
				}
				
				if(itr.hasNext()){
					msg = (Message)itr.next();
					thread16.add(msg);
					
				}
				
				if(itr.hasNext()){
					msg = (Message)itr.next();
					thread17.add(msg);
					
				}
				
				if(itr.hasNext()){
					msg = (Message)itr.next();
					thread18.add(msg);
					
				}
				
				if(itr.hasNext()){
					msg = (Message)itr.next();
					thread19.add(msg);
					
				}
				
				if(itr.hasNext()){
					msg = (Message)itr.next();
					thread20.add(msg);
					
				}
				
			}
			
			List all_threads =  new ArrayList();
			
			all_threads.add(thread1);
			all_threads.add(thread2);
			all_threads.add(thread3);
			all_threads.add(thread4);
			all_threads.add(thread5);
			all_threads.add(thread6);
			all_threads.add(thread7);
			all_threads.add(thread8);
			all_threads.add(thread9);
			all_threads.add(thread10);
			all_threads.add(thread11);
			all_threads.add(thread12);
			all_threads.add(thread13);
			all_threads.add(thread14);
			all_threads.add(thread15);
			all_threads.add(thread16);
			all_threads.add(thread17);
			all_threads.add(thread18);
			all_threads.add(thread19);
			all_threads.add(thread20);
			
			
			
			
			
			
			Iterator itr_threads = all_threads.iterator();
			
			while(itr_threads.hasNext()){
				try{
					List msgs = (List)itr_threads.next();
					if(msgs.size()>0){
						new LAKDL_EMAIL_Thread(msgs).start();
					}
				}catch (Exception ex) {
					System.out.println(ex.toString());
				}
				
			}  
			
			
			m_msg = "'Emails sent successfully ";
			
			
			out.println("<html>");
			out.println("	<head>");
			out.println("		<script type = \"text/javascript\">");
			out.println("			function displaymsg() {");
			out.println("				alert(" + m_msg + "');");
			out.println("				window.location.href='" + m_class_url + "/" + m_fschema_name + "FA_MIS_report_email';");
			out.println("			}");
			out.println("		</script>");
			out.println("	</head>");
			out.println("	<body onload = \"displaymsg();\">");
			out.println("	</body>");
			out.println("</html>");
			
			out.flush();
			
		}
		catch (SQLException sqlex) {
			try{conn.rollback();}catch(Exception e){}
			out.println();
			out.println("<html><head>");
			out.println("<script type='text/javascript'>");
			out.println("     function displaymsg() {");
			
			out.println("     alert('Database error ocurred..');");
			
			out.println("				window.location.href='" + m_class_url + "/" + m_fschema_name + "FA_MIS_report_email';"); 
			out.println("	}"); 
			out.println("</script></head>");
			out.println("<body onload=\'displaymsg();\'>Error: Database error occured.<br/> "+sqlex.toString()+"</body>");
			out.println("</html>");
			out.flush();
			out.close();
		}
		catch (Exception ex) {
			try {
				conn.rollback();
			}
			catch(Exception e) {}
			
			
			
			out.println("<html>");
			out.println("	<head>");
			out.println("		<script type = \"text/javascript\">");
			out.println("			function displaymsg() {");
			out.println("				alert('Error When Saving Record..');");
			out.println("				window.location.href='" + m_class_url + "/" + m_fschema_name + "FA_MIS_report_email';"); 
			out.println("			}");
			out.println("		</script>");
			out.println("	</head>");
			out.println("	<body onload = \"displaymsg();\">");
			out.println("     Error: "+ ex.toString()+" ");
			out.println("	</body>");
			out.println("</html>");
			
			out.flush();
			out.close();
		}
		
		finally {
			try {
				conn.setAutoCommit(true);
			}
			catch(Exception e) {}
			
			if(conn != null) {
				try {
					conn.close();
				}
				catch(Exception e) {}
			}
			
			if(out != null) {
				try {
					out.close();
				}
				catch(Exception e) {}
			}
			
		}
		
	}
}

