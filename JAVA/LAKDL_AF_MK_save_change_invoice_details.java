//--
//SCREEN NAME	:SAVE SYSTEM ADMINISTRATION - PERFORMA INVOICE
//CREATED BY	:
//DATE/TIME		:
//NOTES				:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MK_save_change_invoice_details extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	ServletOutputStream out = null;
	Statement stmt,stmt1;
	 
  public ResultSet rs;
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{


		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();
      LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			

			
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			String m_price_no="";
			String m_screen_name="";
			String m_invoice_no="";
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
      conn.setAutoCommit(false); //added by nuwan de silva on 03-09-07	
				
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			String m_appNo=(String)m_sn_methods.met_formdata(reqstr,"hid_app_no"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			m_screen_name =	"EDIT";				
	
			String m_my_screen="";
			
			m_my_screen =  req.getParameter("my_screen_name");
			
			if(m_my_screen==null){
			m_my_screen="";
			
			}
			
    
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_PER_INVOICE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31,:32,:33,:34,:35,:36,:37,:38,:39); END;");// insert 32,33,34,35,36,37,38 by Prabash  on 27-04-2012
			
      
			String m_inv_no=(String)m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO");
     
			
			if(m_inv_no.equals("")){
      callstmt.registerOutParameter(1,java.sql.Types.CHAR);
		
      }
			else{ 
      callstmt.setString(1 ,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO").toUpperCase());
		   }	
			
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_ASSET_ID"));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_ENGINE_NO"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_CHASSIS_NO"));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_REG_NO"));
			String m_reg_dd =m_sn_methods.met_formdata(reqstr,"TXT_REG_DATE_DD");
			String m_reg_mm =m_sn_methods.met_formdata(reqstr,"TXT_REG_DATE_MM");
			String m_reg_yy =m_sn_methods.met_formdata(reqstr,"TXT_REG_DATE_YY");
		 
			String m_reg_date="";
			
			if(m_reg_dd.equals("") && m_reg_mm.equals("") &&  m_reg_yy.equals("") )
			{
			m_reg_date=m_reg_dd+m_reg_mm+m_reg_yy; 
			}
			else
			{
			m_reg_date=m_reg_dd+"-"+m_reg_mm+"-"+m_reg_yy;
			}
			
			callstmt.setString(7,m_reg_date);
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_PRICING_NO"));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_SUB_MODEL_NO"));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_COLOUR"));
			callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_SEATING_CAPACITY"));
			callstmt.setString(12,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_NET_PRICE")));
			callstmt.setString(13,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_VAT")));
			callstmt.setString(14,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_TOTAL_AMOUNT")));
			callstmt.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_TO_BE_DELIVERD_TO"));
			callstmt.setString(16,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_VALUE")));
			callstmt.setString(17,m_sn_methods.met_formdata(reqstr,"TXT_CURR_CODE"));
			callstmt.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_MODEL_NO"));
			callstmt.setString(19,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_DOC_NO"));
			callstmt.setString(20,m_sn_methods.met_formdata(reqstr,"TXT_LOCATION"));
			callstmt.setString(21,(m_sn_methods.met_formdata(reqstr,"TXT_CITY_CODE")).trim());
			callstmt.setString(22,m_sn_methods.met_formdata(reqstr,"TXT_VENDOR_CODE"));
			callstmt.setString(23,m_sn_methods.met_formdata(reqstr,"TXT_LOCATION_CODE"));
			callstmt.setString(24,m_sn_methods.met_formdata(reqstr,"TXT_FUEL_CONV_STS"));
			
			String m_due_dd =m_sn_methods.met_formdata(reqstr,"TXT_DUE_DATE_DD");
			String m_due_mm =m_sn_methods.met_formdata(reqstr,"TXT_DUE_DATE_MM");
			String m_due_yy =m_sn_methods.met_formdata(reqstr,"TXT_DUE_DATE_YY");
			
			
			String m_due_date="";
			
			if(m_due_dd.equals("") && m_due_mm.equals("") &&  m_due_yy.equals("") )
			{
			m_due_date=m_due_dd+m_due_mm+m_due_yy; 
			}
			else
			{
			m_due_date=m_due_dd+"-"+m_due_mm+"-"+m_due_yy;
			}
			
			callstmt.setString(25,m_due_date);
			callstmt.setString(26,"EDIT");
			callstmt.setString(27,m_username);
			callstmt.setString(28,m_sn_methods.met_formdata(reqstr,"TXT_YEAR_MANUFACTURE"));
			callstmt.setString(29,m_sn_methods.met_formdata(reqstr,"TXT_EXTRAS"));  // added by nuwan de silva on 11-12-2007
			callstmt.setString(30,m_sn_methods.met_formdata(reqstr,"TXT_DISTRICT_CODE")); // added by nuwan de silva on 22-04-2008
			callstmt.setString(31,m_sn_methods.met_formdata(reqstr,"TXT_CR_BOOK_NO"));   //  added by nuwan de silva on 22-04-2008
			callstmt.setString(32,(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_SUM_INSURED"))).trim()); 	//added by Prabash on 26-03-2012
			callstmt.setString(33,(m_sn_methods.met_formdata(reqstr,"TXT_AREA")).trim()); 			//added by Prabash on 27-03-2012
			callstmt.setString(34,(m_sn_methods.met_formdata(reqstr,"TXT_POLICE")).trim()); 		//added by Prabash on 27-03-2012
			callstmt.setString(35,(m_sn_methods.met_formdata(reqstr,"TXT_OWN_ADD")).trim()); 		//added by Prabash on 25-04-2012
			callstmt.setString(36,(m_sn_methods.met_formdata(reqstr,"TXT_COLLE_SEC")).trim()); 		//added by Prabash on 27-04-2012
			callstmt.setString(37,(m_sn_methods.met_formdata(reqstr,"TXT_LIC_AUTH")).trim()); 		//added by Prabash on 27-04-2012
			callstmt.setString(38,(m_sn_methods.met_formdata(reqstr,"VEHICAL_AGA")).trim()); 		//added by Prabash on 27-04-2012
			callstmt.setString(39,m_sn_methods.met_formdata(reqstr,"CHK_CR_BOOK_RECEIVED")); // added by thamali 2013.08.12
			callstmt.execute();
			
				if(m_screen_name.equals("NEW")){
              m_invoice_no =callstmt.getString(1);
           }
			
      //comment by nuwan de silva on 22-08-07 for refinement no :818
			
		/*	callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_APP_SAVE_PRICING(:1,:2,:3,:4,:5,:6); END;");//Modified Nuwan De Silva
			
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			if(m_screen_name.equals("NEW")){
      callstmt.setString(2,m_invoice_no.trim());
				       m_msg = "'"+m_invoice_no+" Proforma Invoice saved successfully.'";
                }
							else
							{
							callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO")).trim());
							
							
							}	
			
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_PRICING_NO"));
			callstmt.setString(4,"EDIT");
			callstmt.setString(5,m_username);
			callstmt.setString(6,m_scr_name);

			
			callstmt.execute();
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_APP_PR_SAVE_CHAR(:1,:2,:3,:4,:5,:6); END;");
			
			
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_PRICING_NO"));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			
			
				if(m_screen_name.equals("NEW")){
              callstmt.setString(3,m_invoice_no.trim());
				      }
							else
							{
							callstmt.setString(3,(m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO")).trim());
							}	
							
			callstmt.setString(4,"EDIT");
			callstmt.setString(5,m_username);
			callstmt.setString(6,m_scr_name);
			callstmt.execute();
			
			
			
			
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_APP_SAVE_INST(:1,:2,:3,:4,:5,:6); END;"); //Modified Nuwan De Silva
			
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			if(m_screen_name.equals("NEW")){
               callstmt.setString(2,m_invoice_no.trim());
                }
							else
							{
							callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO")).trim());
							
							
							}	
			
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_PRICING_NO"));
			callstmt.setString(4,"EDIT");
			callstmt.setString(5,m_username);
			callstmt.setString(6,m_scr_name); //Modified Nuwan De Silva

			callstmt.execute();
			
			*/
			
			callstmt.close();
			conn.commit(); //added by nuwan de silva on 03-09-07----------------------
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			
			/*out.println("<server> ");
			out.println("SendMail SMName = new SendMail(); ");
			out.println("SMName.To = \"chandana@sasianet.net\"");
			out.println("SMName.From = \"nuwan@sasianet.net\" ");
			out.println("SMName.Subject = \"Test message\" ");
			out.println("SMName.Body = \"This Is a Test Mail from the java Scripts\" ");
			out.println("SMName.send() ");
			out.println("</server>");
      */

			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MK_display_change_invoice_details?chksql=main_page&edit_screen=Y';");
			
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();

		}
		/*catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error When Saving Record..');");
			out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
      out.close();
		}
		finally{
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}*/
		
		
			catch (Exception E) {
		  try{conn.rollback();}catch(Exception e){}
			out.println("ERROR:"+E.toString());
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			
			
			out.println("function displaymsg() {");
			out.println("alert('Error when Saving');");
			out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
			
	 }
		finally{
		  try{conn.setAutoCommit(true);}catch(Exception e){}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}


	}
}
