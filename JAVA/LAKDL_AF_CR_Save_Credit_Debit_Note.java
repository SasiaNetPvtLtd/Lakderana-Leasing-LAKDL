//SCREEN NAME:SAVE APPLICATION PROCESS
//CREATED BY:NUWAN DE SILVA
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_Save_Credit_Debit_Note extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	Statement stmt;
	public ResultSet rs;
	ServletOutputStream out = null;
	

	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	synchronized(this){ 

		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
		
			//PrintStream out = new PrintStream(res.getOutputStream());
			out = res.getOutputStream();

	   // out.println(reqstr);
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
		//	out.println("conn"+conn);
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_screen_name="";
			String m_app_no="";
			String m_vendor_code="";
			String m_status="";
			String m_val_date="";
			String m_scr_name="";
			String m_adtistement_no="";
			String m_credit_no1="";
			String m_my_scr_name="";
			
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
      
		//	out.println("conn"+conn);
			//out.println(reqstr);
			
      m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_my_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_my_scr_name"); 
			m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");	
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
				String m_fschema_name=m_sn_methods.client_name.trim();
		
						
			       	callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_SAVE_CR_DR_DETAILS(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;");
								
						  
							String m_credit_no=(String)m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_NO");
     
			
			         if(m_credit_no.equals("")){
                  callstmt.registerOutParameter(1,java.sql.Types.CHAR);	
                }
							else
							{
							callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_NO")).trim());
							}
													
							callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO")).trim());
							callstmt.setString(3,(m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE")).trim());
							callstmt.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AMOUNT")));
							callstmt.setString(5,(m_sn_methods.met_formdata(reqstr,"TXT_CR_DR_TYPE")).trim());
							callstmt.setString(6,(m_sn_methods.met_formdata(reqstr,"TXT_NARRATIONS_CODE")).trim());
							callstmt.setString(7,(m_sn_methods.met_formdata(reqstr,"TXT_REMARKS")).trim());
							callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt.setString(9,m_username);
							callstmt.setString(10,m_scr_name);
																				
							callstmt.execute();
							
							
							if(m_screen_name.equals("NEW")){
              m_credit_no1 =callstmt.getString(1);
							m_msg = "'"+m_credit_no1+ "-" +"Reference Number saved successfully.'";
					
							}
							
				
							/*
							////////////////////////////////////////////////////////////////////////////////////////
							//--(2007-03-01) : add credit narrations---------------------------------------------------------------------
							//--Modified by  : delanjali---------------------------------------------------------------------------------
							callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_SAVE_CREDIT_DETAILS_NAR(:1,:2,:3,:4,:5,:6,:7); END;");
							if(m_screen_name.equals("NEW")){
							callstmt.setString(1,m_credit_no1);
							}
							else{
							callstmt.setString(1,m_credit_no);
							}
							callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO")).trim());
							callstmt.setString(3,(m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO")).trim());
							callstmt.setString(4,(m_sn_methods.met_formdata(reqstr,"TXT_NARRATIONS_CODE")).trim());
							callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt.setString(6,m_username);
							callstmt.setString(7,m_scr_name);
							callstmt.execute();
							String m_finance_no=m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO").trim();
							String m_invoice_no=m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO").trim();
							String m_client=m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE").trim();
							//-----------------------------------------------------------------------
							
							*/
			

		
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			
			//out.println("m_scr_name='"+m_screen_name+"'");
			//out.println("my_screen_name='"+m_my_scr_name+"';");
			
			/*out.println("if(my_screen_name=='Y'){");
			out.println("window.close();");
			out.println("window.opener.get_Application_numbers('FINANCE_NO','ASC');");			
			out.println("}");
			out.println("else {");
			out.println("if(m_scr_name!='DEL'){");
			out.println("		m_url=\""+m_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Notes_Letter?finance_no="+m_finance_no+"&client="+m_client+"&invoice_no="+m_invoice_no+"&print=TRUE&aouthname=TEST1&chksql=MAIN\";"); 
			out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=720,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_CR_invoice_adjustments';");
			out.println("}");
			out.println("else {");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_CR_PRO_Change_Insuarance_Date';");
			out.println("}");
			out.println("}");
			*/
			//out.println("window.close();");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_CR_PRO_Credit_Debit_Note?chksql=main_page';");
			
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();
      out.close();
		}
		/*catch (Throwable th) {
     	PrintStream out = new PrintStream(res.getOutputStream());
			th.printStackTrace(out);
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
}
