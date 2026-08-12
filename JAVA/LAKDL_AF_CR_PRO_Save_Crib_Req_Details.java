
//--
//SCREEN NAME:Save CRIB Details
//CREATED BY:Chandana
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_PRO_Save_Crib_Req_Details extends HttpServlet {
		
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
	 //   out.println(reqstr);
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
			String m_screen_name="";
			String m_app_no="";
			String m_client_no="";
			String m_vendor_code="";
			String m_pur_ord_no1="";
			String m_status="";
			String m_crib_ref_no="";
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_t3_port=m_sn_methods.client_t3_port;
	     m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
      
			String m_client_status=req.getParameter("status");	
			
			if(m_client_status.equals("individual")){			
     
			//String m_client_type=(String)m_sn_methods.met_formdata(reqstr,"Hid_client_type");
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_CRIB_DETAILS(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31,:32,:33,:34,:35,:36,:37,:38,:39); END;");
			
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_COMP_NAME"));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_NAME"));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_MONTH_ENDING"));
			
			String m_chk_required =(String)m_sn_methods.met_formdata(reqstr,"CHK_REGULAR_VALUE");
			
							if(m_chk_required.equals("on")){
							callstmt.setString(4,"REGULAR");
							}
							else 
							{
							callstmt.setString(4,"IRREGULAR");
							//break;
							}
									
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_CLNT_NAME"));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_CLNT_SURE_NAME"));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_COAPP_NAME"));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_COAPP_SURE_NAME"));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_CLNT_NIC"));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_COAP_NIC"));
			callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_CLNT_DISG"));
			callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_COAP_DISG"));
			callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_CLNT_ADD"));
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_COAP_ADD"));
			callstmt.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_CLNT_BUS_NAME"));
			callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_COAP_BUS_NAME"));
			callstmt.setString(17,m_sn_methods.met_formdata(reqstr,"TXT_CLNT_BUS_NO"));
			callstmt.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_COAP_BUS_NO"));
			callstmt.setString(19,m_sn_methods.met_formdata(reqstr,"TXT_CLNT_BUS_ADD"));
			callstmt.setString(20,m_sn_methods.met_formdata(reqstr,"TXT_COAP_BUS_ADD"));
			callstmt.setString(21,m_sn_methods.met_formdata(reqstr,"TXT_FIN_NO"));
			callstmt.setString(22,m_sn_methods.met_formdata(reqstr,"TXT_GR_DATE"));
			callstmt.setString(23,m_sn_methods.met_formdata(reqstr,"TXT_TYPE"));
			callstmt.setString(24,m_sn_methods.met_formdata(reqstr,"TXT_SECTOR"));
			callstmt.setString(25,m_sn_methods.met_formdata(reqstr,"TXT_ADVANCE"));
			callstmt.setString(26,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AMT_LIMT")));
			callstmt.setString(27,m_sn_methods.met_formdata(reqstr,"TXT_SECURITY"));
			callstmt.setString(28,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_BALANCE")));
			callstmt.setString(29,m_sn_methods.met_formdata(reqstr,"TXT_GR1_NAME"));
			callstmt.setString(30,m_sn_methods.met_formdata(reqstr,"TXT_GR1_NIC"));
			callstmt.setString(31,m_sn_methods.met_formdata(reqstr,"TXT_GR1_DSG"));
			callstmt.setString(32,m_sn_methods.met_formdata(reqstr,"TXT_GR1_ADD"));
			callstmt.setString(33,m_sn_methods.met_formdata(reqstr,"TXT_GR2_NAME"));
			callstmt.setString(34,m_sn_methods.met_formdata(reqstr,"TXT_GR2_NIC"));
			callstmt.setString(35,m_sn_methods.met_formdata(reqstr,"TXT_GR2_DSG"));
			callstmt.setString(36,m_sn_methods.met_formdata(reqstr,"TXT_GR2_ADD"));
			callstmt.setString(37,m_username);
			callstmt.setString(38,m_sn_methods.met_formdata(reqstr,"hid_client_no"));
			callstmt.registerOutParameter(39,java.sql.Types.CHAR);
						
			callstmt.execute();
			
			
			
			m_crib_ref_no =callstmt.getString(39);
			
			
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
			
		////////////////////////////////////////////////////////////////////////////////////////
						
    
			m_client_no=m_sn_methods.met_formdata(reqstr,"hid_client_no");
			m_app_no=m_sn_methods.met_formdata(reqstr,"hid_app_no");
		//out.println(m_app_no);
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("m_scr_name='"+m_screen_name+"'");
		
		
		out.println("window.location.href='"+m_url+"/LAKDL_AF_CR_Preliminary_Information_Letter?chksql=letter&app_no="+m_app_no+"';");
			
		
		out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_Preliminary_Information_Letter?chksql=print_letter&crib_ref_no="+m_crib_ref_no+"\";"); 
		out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=850,height=750,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				
				
				

			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			
			}else if(m_client_status.equals("corperate")){
			
				m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_CRIB_CORP_DET(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26); END;");
			
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_COMP_NAME"));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_NAME"));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_MONTH_ENDING"));
			
			String m_chk_required =(String)m_sn_methods.met_formdata(reqstr,"CHK_REGULAR_VALUE");
			
							if(m_chk_required.equals("on")){
							callstmt.setString(4,"REGULAR");
							}
							else 
							{
							callstmt.setString(4,"IRREGULAR");
							//break;
							}
									
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_BORR_NAME"));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_BORR_ADD"));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_BORR_BUS_NO"));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO"));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_GRANT_DATE"));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_TRN_TYPE"));
			callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_SECTOR"));
			callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_ADVANCE"));
			//callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_AMOUNT"));
			
			callstmt.setString(13,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AMOUNT")));
			
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_SECURITY"));
			//callstmt.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_BALANCE"));
			callstmt.setString(15,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_BALANCE")));
			
			callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_GR1_NAME"));
			callstmt.setString(17,m_sn_methods.met_formdata(reqstr,"TXT_GR1_NIC"));
			callstmt.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_GR1_DESG"));
			callstmt.setString(19,m_sn_methods.met_formdata(reqstr,"TXT_GR1_ADD"));
			callstmt.setString(20,m_sn_methods.met_formdata(reqstr,"TXT_GR2_NAME"));
			callstmt.setString(21,m_sn_methods.met_formdata(reqstr,"TXT_GR2_NIC"));
			callstmt.setString(22,m_sn_methods.met_formdata(reqstr,"TXT_GR2_DESG"));
			callstmt.setString(23,m_sn_methods.met_formdata(reqstr,"TXT_GR2_ADD"));
			callstmt.setString(24,m_username);
			callstmt.registerOutParameter(25,java.sql.Types.CHAR);
			callstmt.setString(26,m_sn_methods.met_formdata(reqstr,"hid_client_no"));
						
			callstmt.execute();
					
			m_crib_ref_no =callstmt.getString(25);
			
			
			int m_hid_max= Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_max"));
			
			for (int j = 1; j < m_hid_max; j++) {
						
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_CRIB_PARTNERS(:1,:2,:3,:4,:5,:6); END;");
			
			callstmt.setString(1,m_crib_ref_no);
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"hid_client_no")); 
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_BORR_NAME"+(Integer.toString(j))));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_PART_ADD"+(Integer.toString(j))));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_PART_NIC"+(Integer.toString(j))));
			callstmt.setString(6,m_username);
			
			callstmt.execute();
			}
			
			
			
			int m_hid_maxim= Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_max"));
			
			for (int j = 1; j < m_hid_maxim; j++) {
						
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_CRIB_SUBSIDIARY(:1,:2,:3,:4,:5); END;");
			
			callstmt.setString(1,m_crib_ref_no);
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"hid_client_no")); 
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_SUBSIDERY_NAME"+(Integer.toString(j))));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_SUBSIDERY_REG_NO"+(Integer.toString(j))));
			callstmt.setString(5,m_username);
			
			callstmt.execute();
			}
			
			
			
			
			
			
			conn.close();
			
			
			
			
			m_app_no=m_sn_methods.met_formdata(reqstr,"hid_app_no");
			

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("m_scr_name='"+m_screen_name+"'");
	
		
			out.println("window.location.href='"+m_url+"/LAKDL_AF_CR_Preliminary_Information_Letter?chksql=letter&app_no="+m_app_no+"';");
			
		
	   out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_Preliminary_Information_Letter?chksql=print_letter_corp&crib_ref_no="+m_crib_ref_no+"\";"); 
	   out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=850,height=750,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				
			
		
		
		
		//	out.println("popupwin = window.open('"+m_url+"/LAKDL_AF_CR_PRO_Purchase_Order_Letter?chksql=generatereport&pur_ord_no="+m_pur_ord_no1+"&app_no="+m_app_no+"&vendor_code="+m_vendor_code+"','displayWindow1','left=0,top=110,width=790,height=410,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');	");				

			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			
			}

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
		//	out.println("<body onload='displaymsg();'></body>");
			out.println("<body onload=''></body>");
			out.println("</html>");
			out.flush();
      out.close();
		}
	}
	}
}
