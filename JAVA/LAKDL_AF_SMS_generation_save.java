// DEVELOP BY :  DISNAKA FOR OFSCL FACTORING    DATE:2011-11-08


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.regex.*;

public class LAKDL_AF_SMS_generation_save extends HttpServlet {
	
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
	String reqstr;
	ServletOutputStream out = null;
	Statement stmt = null;
	public ResultSet rs = null;
	BufferedWriter log=null;
	java.text.NumberFormat nf;
	
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
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			m_msg = "'Information saved successfully'";
			//PDFConversion pdfConversion = new PDFConversion();
			stmt = conn.createStatement();
			
			
			Vector smsNumbers = new Vector();
			Vector smsmessage = new Vector();
			String smpp_host_name = "";
			int smpp_port         = 0;
			String smpp_path      = "";
			String smpp_auth_user = "";
			String smpp_auth_pwd  = "";
			String smpp_display_name  = "";
			
			String m_send_type = m_sn_methods.met_formdata(reqstr,"hid_send_type");
			
			//String m_phone_num =  /*m_sn_methods.met_formdata(reqstr,"TXT_CODE_NUM") +*/ m_sn_methods.met_formdata(reqstr,"TXT_TO_NUM");
			String m_message = m_sn_methods.met_formdata(reqstr,"TXT_MESSAGE");
			
			//smsNumbers.addElement(m_phone_num);
			String output =  m_message;
			
			output = output + "\nLAKDERANA.";
			/*
			String patternStr = "\n"; 
			String replacementStr = "%0D%0A";
			
			//  Compile regular expression
			Pattern pattern = Pattern.compile(patternStr);
			
			//Replace all occurrences of pattern in input
			Matcher matcher = pattern.matcher(output);
			output =  matcher.replaceAll(replacementStr);
			
			patternStr = " "; 
			replacementStr = "%20";
			
			//  Compile regular expression
			pattern = Pattern.compile(patternStr);
			
			//Replace all occurrences of pattern in input
			matcher = pattern.matcher(output);
			output =  matcher.replaceAll(replacementStr);
			*/
			
			callstmt  = conn.prepareCall("BEGIN " + m_schema_name + ".SAVE_SMS_LOG(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
			
			/*
			
			if (m_send_type.equals("SS")) {
				
				String m_phone_num =  m_sn_methods.met_formdata(reqstr,"TXT_CODE_NUM") + m_sn_methods.met_formdata(reqstr,"TXT_TO_NUM");
				String m_message = m_sn_methods.met_formdata(reqstr,"TXT_MESSAGE");
				
				
				String patternStr = "%0D%0A"; 
				String replacementStr = "\n";
				
				//  Compile regular expression
				Pattern pattern = Pattern.compile(patternStr);
				
				//Replace all occurrences of pattern in input
				Matcher matcher = pattern.matcher(m_message);
				String output =  matcher.replaceAll(replacementStr);
				
				
				
				
				
				smsNumbers.addElement(m_phone_num);
				smsmessage.addElement(output + "\nLAKDERANA.");
				
				callstmt.setString(1,null);
				callstmt.setDouble(2,0.0);
				callstmt.setString(3,null);
				callstmt.setString(4,m_phone_num);
				callstmt.setString(5,null);
				callstmt.setString(6,"SINGLE"); 
				callstmt.setString(7,m_username); 
				callstmt.execute();
				
				
				
			} else if (m_send_type.equals("SB")) {
				*/
			
			
			rs = stmt.executeQuery("SELECT A.SMPP_HOST_NAME, A.SMPP_PORT, A.PATH,A.SMPP_AUTH_USER, A.SMPP_AUTH_PWD,A.DISPLAY_NAME"+
				" FROM "+m_schema_name+".AF_CO_MAS_SMS_SERVER_DET A");
			
			if(rs.next()){
				smpp_host_name = rs.getString(1);
				smpp_port      = rs.getInt(2);
				smpp_path      = rs.getString(3);
				smpp_auth_user = rs.getString(4);
				smpp_auth_pwd  = rs.getString(5);
				smpp_display_name = rs.getString(6);
				
				
			}
			LAKDL_SasianetSms objSms = new LAKDL_SasianetSms();
			
			String m_phone_num = null;
			
			rs = stmt.executeQuery(""+
				" SELECT B.FINANCE_NO, A.BALANCE_TO_BE_RECEIVED, TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'), "+
				" NVL('0765320953','-') ,C.CLIENT_CODE, NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(B.APPLICATION_NO),'-') "+
				" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A , "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_MAS_CLIENT C "+
				" WHERE "+
				" A.FINANCE_NO = B.FINANCE_NO "+
				" AND B.APPLICATION_STATUS = 'ACTIVATED' AND B.CLIENT_CODE = C.CLIENT_CODE "+
				" AND TRUNC(A.VALUE_DATE - 3,'DD') = TRUNC(SYSDATE,'DD') AND A.INVOICE_TYPE = 'INV_GENER' "+
				" AND A.VALUE_DATE >= TO_DATE('01-04-2012','DD-MM-YYYY') "+
				" AND A.BALANCE_TO_BE_RECEIVED > 0"+
				" and ROWNUM <=20 ");
			while (rs.next()){
				try{
					if (!rs.getString(4).equals("-")) {
						if ( rs.getString(4).indexOf("0")==0 ) {
							m_phone_num = "" + rs.getString(4).substring(1);
						}
						
						else {
							m_phone_num = "" + rs.getString(4);
						}
						//smsNumbers.addElement(m_phone_num);
						//smsmessage.addElement("Your next rental of Rs. " + nf.format(rs.getDouble(2)) + " for Agreement Number " + rs.getString(1) + " is due on "+ rs.getString(3) + "\nORIENT FINANCIAL SERVICES CORPORATION LTD." );
						String message = "Ayubowan! Obage "+rs.getString(6)+" wahanaye maasika warikaya "+rs.getString(3)+" dinata Rs. " + nf.format(rs.getDouble(2)) + " gewiya yuthu bawa danwa sitimu. Suba dawasak! Lakderana" ;
						
						String patternStr = "\n"; 
						String replacementStr = "%0D%0A";
						
						//  Compile regular expression
						Pattern pattern = Pattern.compile(patternStr);
						
						//Replace all occurrences of pattern in input
						Matcher matcher = pattern.matcher(message);
						message =  matcher.replaceAll(replacementStr);
						
						patternStr = " "; 
						replacementStr = "%20";
						
						//  Compile regular expression
						pattern = Pattern.compile(patternStr);
						
						//Replace all occurrences of pattern in input
						matcher = pattern.matcher(message);
						message =  matcher.replaceAll(replacementStr);
						
						m_msg = objSms.SendMessage ( smpp_host_name,smpp_port ,smpp_path,smpp_auth_user,smpp_auth_pwd,smpp_display_name,m_phone_num,message);
						
						
						String m_status = m_msg.substring(0,m_msg.indexOf(":"));
						String m_ref = m_msg.substring(m_msg.lastIndexOf(":"),m_msg.length());
						
						callstmt.setString(1,rs.getString(1));
						callstmt.setString(2,rs.getString(6));
						callstmt.setString(3,m_status);
						callstmt.setString(4,m_ref);
						callstmt.setString(5,null);
						callstmt.setString(6,m_username);  
						callstmt.setString(7,message);  
						callstmt.setString(8,"3");  
						callstmt.setString(9,null); 
						callstmt.execute();
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			
			
			rs = stmt.executeQuery(""+
				" SELECT B.FINANCE_NO,  "+m_schema_name+".AF_GET_TOTAL_ARREARS(B.FINANCE_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'),'"+m_username+"') , TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'), "+ // A.BALANCE_TO_BE_RECEIVED
				" NVL('0765320953','-') ,C.CLIENT_CODE, NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(B.APPLICATION_NO),'-') "+
				" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A , "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_MAS_CLIENT C "+
				" WHERE "+
				" A.FINANCE_NO = B.FINANCE_NO "+
				" AND B.APPLICATION_STATUS = 'ACTIVATED' AND B.CLIENT_CODE = C.CLIENT_CODE "+
				" AND TRUNC(A.VALUE_DATE,'DD') = TRUNC(SYSDATE,'DD') AND A.INVOICE_TYPE = 'INV_GENER' "+
				//" AND A.VALUE_DATE >= TO_DATE('01-04-2012','DD-MM-YYYY') "+
				" AND A.BALANCE_TO_BE_RECEIVED > 0"+
				" and ROWNUM <=20 ");
			while (rs.next()){
				
				try{
					if (!rs.getString(4).equals("-")) {
						if ( rs.getString(4).indexOf("0")==0 ) {
							m_phone_num = "" + rs.getString(4).substring(1);
						}
						
						else {
							m_phone_num = "" + rs.getString(4);
						}
						//smsNumbers.addElement(m_phone_num);
						//smsmessage.addElement("Your next rental of Rs. " + nf.format(rs.getDouble(2)) + " for Agreement Number " + rs.getString(1) + " is due on "+ rs.getString(3) + "\nORIENT FINANCIAL SERVICES CORPORATION LTD." );
						String message = "Ayubowan! Obage "+rs.getString(6)+" wahanaya sandaha ada dinata geviya yuthu mulu mudala Rs. " + nf.format(rs.getDouble(2)) + " wana bawa danwamu. Suba dawasak! Lakderana" ;
						
						String patternStr = "\n"; 
						String replacementStr = "%0D%0A";
						
						//  Compile regular expression
						Pattern pattern = Pattern.compile(patternStr);
						
						//Replace all occurrences of pattern in input
						Matcher matcher = pattern.matcher(message);
						message =  matcher.replaceAll(replacementStr);
						
						patternStr = " "; 
						replacementStr = "%20";
						
						//  Compile regular expression
						pattern = Pattern.compile(patternStr);
						
						//Replace all occurrences of pattern in input
						matcher = pattern.matcher(message);
						message =  matcher.replaceAll(replacementStr);
						
						m_msg = objSms.SendMessage ( smpp_host_name,smpp_port ,smpp_path,smpp_auth_user,smpp_auth_pwd,smpp_display_name,m_phone_num,message);
						
						
						String m_status = m_msg.substring(0,m_msg.indexOf(":"));
						String m_ref = m_msg.substring(m_msg.lastIndexOf(":"),m_msg.length());
						
						callstmt.setString(1,rs.getString(1));
						callstmt.setString(2,rs.getString(6));
						callstmt.setString(3,m_status);
						callstmt.setString(4,m_ref);
						callstmt.setString(5,null);
						callstmt.setString(6,m_username);  
						callstmt.setString(7,message);  
						callstmt.setString(8,"1");  
						callstmt.setString(9,null); 
						callstmt.execute();
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			
			
			
			
			rs = stmt.executeQuery(""+
				" SELECT NVL("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO),'-'), A.REC_AMOUNT, TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'), "+
				" NVL('0765320953','-') ,A.CLIENT_CODE, NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO("+m_schema_name+".AF_CO_GET_APPLICATION_NO("+m_schema_name+".AF_CO_GET_REC_FIN_NO(A.REC_NO))),'-'),A.REC_NO "+
				" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+
				" WHERE TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')= TRUNC(SYSDATE,'DD') "+
				" and ROWNUM <=20 ");
			while (rs.next()){
				try{
					if (!rs.getString(4).equals("-")) {
						if ( rs.getString(4).indexOf("0")==0 ) {
							m_phone_num = "" + rs.getString(4).substring(1);
						}
						
						else {
							m_phone_num = "" + rs.getString(4);
						}
						//smsNumbers.addElement(m_phone_num);
						//smsmessage.addElement("Your next rental of Rs. " + nf.format(rs.getDouble(2)) + " for Agreement Number " + rs.getString(1) + " is due on "+ rs.getString(3) + "\nORIENT FINANCIAL SERVICES CORPORATION LTD." );
						String message = "Ayubowan! Obage "+rs.getString(6)+" darana wahanaya sandaha Rs. " + nf.format(rs.getDouble(2)) + ", "+rs.getString(3)+" dina ginumgatha wee athi bawa danwamu. Sthuthi! Lakderana" ;
						
						String patternStr = "\n"; 
						String replacementStr = "%0D%0A";
						
						//  Compile regular expression
						Pattern pattern = Pattern.compile(patternStr);
						
						//Replace all occurrences of pattern in input
						Matcher matcher = pattern.matcher(message);
						message =  matcher.replaceAll(replacementStr);
						
						patternStr = " "; 
						replacementStr = "%20";
						
						//  Compile regular expression
						pattern = Pattern.compile(patternStr);
						
						//Replace all occurrences of pattern in input
						matcher = pattern.matcher(message);
						message =  matcher.replaceAll(replacementStr);
						
						m_msg = objSms.SendMessage ( smpp_host_name,smpp_port ,smpp_path,smpp_auth_user,smpp_auth_pwd,smpp_display_name,m_phone_num,message);
						
						
						String m_status = m_msg.substring(0,m_msg.indexOf(":"));
						String m_ref = m_msg.substring(m_msg.lastIndexOf(":"),m_msg.length());
						
						callstmt.setString(1,rs.getString(1));
						callstmt.setString(2,rs.getString(6));
						callstmt.setString(3,m_status);
						callstmt.setString(4,m_ref);
						callstmt.setString(5,null);
						callstmt.setString(6,m_username);  
						callstmt.setString(7,message);  
						callstmt.setString(8,"R");  
						callstmt.setString(9,rs.getString(7)); 
						callstmt.execute();
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			//System.out.println("");
			//}
			
			
			
			//int m_num = 0;
			//String m_scr_num = "0";
			
			
			
			
			
			
			
			
			
			
			//m_msg = "Sms sent successfully ";
			
			conn.commit();
			out.println("<html>");
			out.println("	<head>");
			out.println("		<script type = \"text/javascript\">");
			out.println("			function displaymsg() {");
			out.println("				alert('" + m_msg + "');");
			out.println("				window.location.href='" + m_class_url + "/" + m_fschema_name + "AF_MAS_Send_Sms_Leasing';");
			out.println("			}");
			out.println("		</script>");
			out.println("	</head>");
			out.println("	<body onload = \"displaymsg();\">");
			out.println("	</body>");
			out.println("</html>");
			
			out.flush();
			
		}
		catch (SQLException sqlex) {
			sqlex.printStackTrace();
			try{conn.rollback();}catch(Exception e){}
			out.println();
			out.println("<html><head>");
			out.println("<script type='text/javascript'>");
			out.println("     function displaymsg() {");
			
			out.println("     alert('Database error ocurred..');");
			
			out.println("				window.location.href='" + m_class_url + "/" + m_fschema_name + "AF_MAS_Send_Sms_Leasing';"); 
			out.println("	}"); 
			out.println("</script></head>");
			out.println("<body onload=\'displaymsg();\'>Error: Database error occured.<br/> "+sqlex.toString()+"</body>");
			out.println("</html>");
			out.flush();
			out.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			}
			catch(Exception e) {out.println(e.toString());}
			
			
			
			out.println("<html>");
			out.println("	<head>");
			out.println("		<script type = \"text/javascript\">");
			out.println("			function displaymsg() {");
			out.println("				alert('Error When Saving Record..');");
			out.println("				window.location.href='" + m_class_url + "/" + m_fschema_name + "AF_MAS_Send_Sms_Leasing';"); 
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
