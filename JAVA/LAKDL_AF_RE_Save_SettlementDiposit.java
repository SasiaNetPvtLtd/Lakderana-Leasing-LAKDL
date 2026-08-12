//SCREEN NAME:SAVE APPLICATION PROCESS
//CREATED BY:NUWAN DE SILVA
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_Save_SettlementDiposit extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	Statement stmt;
	public ResultSet rs;
	

	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	synchronized(this){ 

		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
		
			PrintStream out = new PrintStream(res.getOutputStream());
	   // out.println(reqstr);
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
		//	out.println("conn"+conn);
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
		//	String m_class_name_save;
	//		String m_save_procedure_name;
			String m_screen_name="";
			String m_app_no="";
			String m_vendor_code="";
		//	String callstmt.setString(7,m_username);="";
			String m_status="";
			String m_val_date="";
			String m_scr_name="";
			String m_deposit="";
			
	
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
      
		//	out.println("conn"+conn);
			//out.println(reqstr);
			
      m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");	
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			
			
			//stmt=conn.createStatement();
			
			
			int m_maxentries     = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
			
			for (int j = 0; j < m_maxentries; j++) {
			
				
			
			if(j==0){
			
			       	callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_DIPOSIT(:1,:2,:3,:4,:5,:6,:7,:8); END;");
								
							String m_dip_no=(String)m_sn_methods.met_formdata(reqstr,"DEPOSIT_CODE");
     
			
			         if(m_dip_no.equals("")){
                  callstmt.registerOutParameter(1,java.sql.Types.CHAR);	
                }
							else
							{
							callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"DEPOSIT_CODE")).trim());
							}
							
							String m_val_day =(String)m_sn_methods.met_formdata(reqstr,"VAL_DAY");
			        String m_val_month =(String)m_sn_methods.met_formdata(reqstr,"VAL_MONTH");
			        String m_val_year =(String)m_sn_methods.met_formdata(reqstr,"VAL_YEAR");
							
							
					   m_val_date=m_val_day+"-"+m_val_month+"-"+m_val_year;
						 
							// m_val_date=m_val_day+m_val_month+m_val_year;
						  //out.println(m_val_date);
							callstmt.setString(2,m_val_date);
							//callstmt.setString(2,"12-12-2006");
							
							
							callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"REFERENCE"));
							callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"ACCOUNT_NO"));
							callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"BRANCH_CODE"));
							callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt.setString(7,m_username);
							callstmt.setString(8,m_scr_name);
														
							callstmt.execute();
							
							 
							if(m_screen_name.equals("NEW")){
                m_deposit =callstmt.getString(1);
								m_msg = "'"+m_deposit+ "-" +"Deposit saved successfully.'";
							
					//		out.println("pur no1"+m_deposit);
							}
							
							else
							{
							m_deposit=(String)m_sn_methods.met_formdata(reqstr,"DEPOSIT_CODE");
						//	out.println("pur no2"+m_pur_ord_no1);
							}
							
							}
				    
											
				
		
						  callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_DIP_DETAILS(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
						
              m_status=(String)m_sn_methods.met_formdata(reqstr,"CHK_DEPOSIT"+(Integer.toString(j)));										
							
							if(m_status.equals("on")){
							
														
				      callstmt.setString(1,m_deposit.trim());
							callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"hid_TXT_RECEIPT_NO"+(Integer.toString(j))));
							callstmt.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_AMOUNT"+(Integer.toString(j)))));
							//callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"ACCOUNT_NO"+(Integer.toString(j))));
							//callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"BRANCH_CODE"+(Integer.toString(j))));
							callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"ACCOUNT_NO"));
							callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"BRANCH_CODE"));
							callstmt.setString(6,m_val_date);
							callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt.setString(8,m_username);
			        callstmt.setString(9,m_scr_name);
											   
						  callstmt.execute();
							
							}
							
				}
				
				
				////////////////////////////////////////////////////////////////////////////////////////
				
				
				
				
				
				
				
				
			
			
						
			
			
			
			
			

		
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("m_scr_name='"+m_screen_name+"'");
			
			//========added by nuwan de silva 06-06-07======
			
			out.println("if(m_scr_name!='DEL'){");
			out.println("popupwin = window.open('"+m_url+"/"+m_fschema_name+"AF_RE_Receipt_Deposit_Slip?chksql=print_deposit_slip&deposit_no="+m_deposit+"','displayWindow1','left=110,top=110,width=660,height=700,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=0');	");				
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_RE_SettlementDiposit?chksql=main_page';");
			out.println("}");
			out.println("else {");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_RE_SettlementDiposit?chksql=main_page';");
			out.println("}");
			
			//===============================================
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();
      out.close();
		}
		catch (Throwable th) {
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
		}
	}
	}
}
