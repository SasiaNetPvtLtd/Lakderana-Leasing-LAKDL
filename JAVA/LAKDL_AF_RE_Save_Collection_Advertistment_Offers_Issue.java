//SCREEN NAME:SAVE APPLICATION PROCESS
//CREATED BY:NUWAN DE SILVA
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_Save_Collection_Advertistment_Offers_Issue extends HttpServlet {
		
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
	  //  out.println(reqstr);
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
			//String m_app_no="";
			//String m_vendor_code="";
			//String m_status="";
		//	String m_val_date="";
			String m_scr_name="";
			String m_type,m_inventory_no,m_vehicle_no;
			String m_offer_no="";
			
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
      
		//	out.println("conn"+conn);
		//	out.println(reqstr);
			
      m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");	
			
			m_type=(String)m_sn_methods.met_formdata(reqstr,"RELEASE_MODE");
			m_inventory_no=(String)m_sn_methods.met_formdata(reqstr,"TXT_INVENTORY_NO");
			m_vehicle_no=(String)m_sn_methods.met_formdata(reqstr,"TXT_VEHICLE_NO");
			
			
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			int m_maxentries     = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
			
			
			
			for (int j = 0; j < m_maxentries; j++) {
						
			       	callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_ADVTIS_OFF_ISSUE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15); END;");
								
							//String m_offer_no=(String)m_sn_methods.met_formdata(reqstr,"TXT_OFFER_NO"+(Integer.toString(j)));
							 String m_status=(String)m_sn_methods.met_formdata(reqstr,"CHK_ISSUE"+(Integer.toString(j)));										
							
							if(m_status.equals("on")){
     					
										
			     		callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_ADVETST_NO"));
							callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_INVENTORY_NO"));
							callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_VEHICLE_NO"));
							callstmt.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_OUTSTANDING_VALUE")));	
							callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"hid_m_no"+(Integer.toString(j))));
							callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"hid_m_name"+(Integer.toString(j))));
							callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"hid_m_address"+(Integer.toString(j))));
							callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"hid_m_tel_no"+(Integer.toString(j))));
							callstmt.setString(9,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_m_amount"+(Integer.toString(j))))); 
							callstmt.setString(10,m_username);
							callstmt.setString(11,m_scr_name);
							callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"RELEASE_MODE"));
							callstmt.setString(13,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_OUTSTANDING_VALUE_INVOICES")));	
							callstmt.setString(14,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_TOTAL_OUTSTANDING_VALUE")));	
							callstmt.setString(15,m_screen_name);
							
							m_offer_no=(String)m_sn_methods.met_formdata(reqstr,"hid_m_no"+(Integer.toString(j)));
							
				
							callstmt.execute();
							break;
							
							}
							
													
												
						}
							
			//	}
				
				
				////////////////////////////////////////////////////////////////////////////////////////
				
				
				
				
				
				
				
				
			
			
						
			
			
			
			
			

		
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("m_type='"+m_type+"';");
			out.println("m_screen_name='"+m_screen_name+"';");
			
			out.println("if(m_type=='LEASE' && m_screen_name!='DEL'){");
			out.println("popupwin = window.open('"+m_url+"/"+m_client_name+"AF_RE_Collection_Offer_Invoice_Letter?chksql=main_page&inventory_no="+m_inventory_no+"&offer_no="+m_offer_no+"&vehicle_no="+m_vehicle_no+"&status=ORIGINAL&print=TRUE','displayWindow1','left=0,top=110,width=790,height=410,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');	");				
								
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_RE_Collection_Advertistment_Offers_Issue';");
			
			out.println("}else if('"+m_type+"'=='CASH' && m_screen_name!='DEL'){");
			
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_RE_Collection_Advertistment_Offers_Issue';");
			out.println("}");
			out.println("else{");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_RE_Collection_Advertistment_Offers_Issue';");
			out.println("}");
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
