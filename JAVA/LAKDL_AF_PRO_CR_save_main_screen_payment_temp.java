
//--
//SCREEN NAME:SAVE PAYMENT DETAILS - NEW
//CREATED BY :delanjali	
//DATE/TIME  :25-01-2007
//NOTES      :

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_PRO_CR_save_main_screen_payment_temp extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	ServletOutputStream out = null;
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
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			conn.setAutoCommit(false);
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			String m_fschema_name=m_sn_methods.client_name.trim();

			String m_status = req.getParameter("actst1");//A HAVE TO R NOW
			String m_chksql1 = req.getParameter("actst2");//T
			int m_chksql;
			
			m_chksql = Integer.parseInt(req.getParameter("number"));

			
			for (int k=0; k<=m_chksql; k++) 
			{
			
			
	
			
			
			String m_po_no=m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO_"+k);
			String m_chk=m_sn_methods.met_formdata(reqstr,"CHK_APP_"+k);
			
			if(m_chk.trim().equals("Y")){
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_MAIN_TEMP_PAY(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12); END;");

			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO_"+k));
			callstmt.setInt(2,1);
			callstmt.setString(3,"AF_PRO_CR_TEMP_PAYMENT");
			callstmt.setString(4,m_status);
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(6,m_username);
			
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO_"+k));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_ENGIN_NO_"+k));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_CHASSIS_"+k));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_VEHICLE_NO_"+k));
			String m_status_select=m_sn_methods.met_formdata(reqstr,"TXT_STATUS"+k);
			
			if(m_status_select.equals("N")){
			callstmt.setString(11,"NEW");
			}
			else if(m_status_select.equals("O")){
			callstmt.setString(11,"OLD");
			}
			callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_REQU_NO_"+k));

			
				if  (m_po_no.trim().equals("")) {
						  break;
				}
				callstmt.execute();
				}		
			
			
				}		
			
			
			//-----------------------------------------------------------------------
						for (int h=0; h<=m_chksql; h++) 
			{
				String m_status_select1=m_sn_methods.met_formdata(reqstr,"TXT_STATUS"+h);
				String m_po_no1=m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO_"+h);
				String m_chk1=m_sn_methods.met_formdata(reqstr,"CHK_APP_"+h);
			
			if(m_chk1.trim().equals("Y")){

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_PAYMENT_SET(:1,:2,:3,:4,:5,:6,:7); END;");
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO_"+h));
			callstmt.setString(2,"NEW");
			callstmt.setString(3,m_username);
			callstmt.setString(4,"0");
			callstmt.setString(5,"AF_PRO_CR_TEMP_PAYMENT");
			callstmt.setString(6,m_status);
			
			if(m_status_select1.equals("N")){
			callstmt.setString(7,"NEW");
			}
			else if(m_status_select1.equals("O")){
			callstmt.setString(7,"OLD");
			}
			
					if  (m_po_no1.trim().equals("")) {
						  break;
			}
			callstmt.execute();
			
			}	}
//-----------------------------------------------------------------------

			
			callstmt.close();

			conn.commit();
			
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_PRO_CR_display_main_screen_payment_temp?chksql="+m_chksql1+"&chksql2="+m_status+"';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();

		}

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
			//if(input     !=null){try{input.close();    }catch(Exception e){}}
			//if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}
		

