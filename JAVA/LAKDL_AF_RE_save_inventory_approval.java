//--
//SCREEN NAME	:SAVE INVENTORY APPROVAL
//CREATED BY	:DELANJALI
//DATE/TIME		:2007-02-27
//NOTES				:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_save_inventory_approval extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	ServletOutputStream out = null;
	Statement stmt;
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
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			conn.setAutoCommit(false);

			
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			int m_chksql_inv = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count_inv"));
			
			
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			
			for(int j=0;j<m_chksql_inv;j++){
				
			String m_finance=m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO_"+j);
		  String m_inventory=m_sn_methods.met_formdata(reqstr,"TXT_INVENTORY_NO_"+j);
			String m_chk=m_sn_methods.met_formdata(reqstr,"chk_app_"+j);
			String m_pur="";
			String m_inv="";
			
			if(m_chk.equals("")){
			m_chk="N";
			}
			if(m_chk.equals("Y")){			
						
					rs = stmt.executeQuery ("SELECT DISTINCT INVOICE_NO,A.APPLICATION_NO,A.PURCHASE_ORDER_NO,D.FINANCE_NO "+
						"FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER B, "+
						""+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET C ,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS D "+
						"WHERE C.PURCHASE_ORDER_NO=B.PURCHASE_ORDER_NO "+
						"AND A.PURCHASE_ORDER_NO=B.PURCHASE_ORDER_NO "+
						"AND INVOICE_NO=PRO_INVOICE_NO "+
						"AND A.ACTIVE_STATUS='Y' "+
						"AND D.FINANCE_NO='"+m_finance+"' "+
						"AND A.APPLICATION_NO=D.APPLICATION_NO "+
						"AND A.VEHICLE_NO IN ( "+
						"SELECT VEHICLE_NO "+
						"FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+						
						"WHERE UPPER(INVENTORY_NO)=UPPER('"+m_inventory+"') "+
						"AND  ACTIVE_STATUS=('ENT'))	");
				
				
			boolean more = rs.next();
			
			while(more){
						m_pur=rs.getString(3);
						m_inv=rs.getString(1);
							
								
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_GEN_VENDOR_PAYMENT(:1,:2,:3,:4,:5); END;");
				
      callstmt.setString(1,m_inv);
	    callstmt.setString(2,m_pur);
	    callstmt.setString(3,"AF_RE_INVENTORY_APPROVAL");
	  	callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
      callstmt.setString(5,m_username);
			more=rs.next();
			
			if(!m_pur.equals("") && !m_inv.equals("")){
			callstmt.execute();
			}
			else{
			break;
			}
			
			}
			
			}
		
			if(m_chk.equals("Y")){			
		
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_INVENTORY_APPROVAL(:1,:2,:3,:4,:5,:6); END;");
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_INVENTORY_NO_"+j));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(3,m_username);
			callstmt.setString(4,Integer.toString(j));
			callstmt.setString(5,"APP");
			callstmt.setString(6,"AF_RE_INVENTORY_APPROVAL");
			callstmt.execute();
			}

		}
			callstmt.close();
			conn.commit();


	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/LAKDL_AF_RE_display_inventory_approval?chksql=main_page';");
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
		

