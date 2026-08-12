
//--
//SCREEN NAME:SYSTEM ADMINISTRATION - DESIGNATION
//ID:1.11 Employee Creation Process
//CREATED BY:M.M. WICKRAMASEKARA
//DATE/TIME:2006.07.19
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_save_employee extends HttpServlet {
		
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
			conn.setAutoCommit(false);

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_EMPLOYEE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18); END;"); //increse 15 in to 18 by Prabash on 19-08-2011 

			callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_EMP_CODE")).trim());
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_TITLE"));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_FIRST_NAME"));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_LAST_NAME"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS"));
			callstmt.setString(6,(m_sn_methods.met_formdata(reqstr,"TXT_LOCATION_CODE")).trim());
			callstmt.setString(7,(m_sn_methods.met_formdata(reqstr,"TXT_AREA_CODE")).trim());
			callstmt.setString(8,(m_sn_methods.met_formdata(reqstr,"TXT_CITY_CODE")).trim());
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_CONTACT_NO"));
			callstmt.setString(10,(m_sn_methods.met_formdata(reqstr,"TXT_DESIGNATION_CODE")).trim());
			callstmt.setString(11,(m_sn_methods.met_formdata(reqstr,"TXT_DIVISION_CODE")).trim());
			callstmt.setString(12,(m_sn_methods.met_formdata(reqstr,"TXT_EPF_NO")).trim());
			callstmt.setString(13,(m_sn_methods.met_formdata(reqstr,"TXT_ID_NO")).trim());
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(15,m_username);
			
			
			//added by Prabash on 19-08-2011 ---------------------**
			//*--Date Of Birth---*
			String m_br_dd =m_sn_methods.met_formdata(reqstr,"BR_DAY");
			String m_br_mm =m_sn_methods.met_formdata(reqstr,"BR_MONTH");
			String m_br_yy =m_sn_methods.met_formdata(reqstr,"BR_YEAR");
			
			
			String m_br_date="";
			
			if(m_br_dd.equals("") && m_br_mm.equals("") &&  m_br_yy.equals("") )
			{
			m_br_date=m_br_dd+m_br_mm+m_br_yy; 
			}
			else
			{
			m_br_date=m_br_dd+"-"+m_br_mm+"-"+m_br_yy;
			}
			
			callstmt.setString(16,m_br_date);
			
			//*--Date Of Joined---*
			String m_jo_dd =m_sn_methods.met_formdata(reqstr,"JO_DAY");
			String m_jo_mm =m_sn_methods.met_formdata(reqstr,"JO_MONTH");
			String m_jo_yy =m_sn_methods.met_formdata(reqstr,"JO_YEAR");
			
			
			String m_jo_date="";
			
			if(m_jo_dd.equals("") && m_jo_mm.equals("") &&  m_jo_yy.equals("") )
			{
			m_jo_date=m_jo_dd+m_jo_mm+m_jo_yy; 
			}
			else
			{
			m_jo_date=m_jo_dd+"-"+m_jo_mm+"-"+m_jo_yy;
			}
			
			callstmt.setString(17,m_jo_date);
			
			//*--Date Of Resign---*
			String m_re_dd =m_sn_methods.met_formdata(reqstr,"RE_DAY");
			String m_re_mm =m_sn_methods.met_formdata(reqstr,"RE_MONTH");
			String m_re_yy =m_sn_methods.met_formdata(reqstr,"RE_YEAR");
			
			
			String m_re_date="";
			
			if(m_re_dd.equals("") && m_re_mm.equals("") &&  m_re_yy.equals("") )
			{
			m_re_date=m_re_dd+m_re_mm+m_re_yy; 
			}
			else
			{
			m_re_date=m_re_dd+"-"+m_re_mm+"-"+m_re_yy;
			}
			
			callstmt.setString(18,m_re_date);
			//----------------------------------------------------**
			callstmt.execute();
			conn.setAutoCommit(true);
			callstmt.close();


	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/LAKDL_AF_MAS_display_employee';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();
			
		//--CHANGED BY DELANJALI ON 2007-11-15------------------------------------------------------------------------------
		
		/*}
		catch (Exception ex) {
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
		}

	}
}*/

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
				if(conn!=null){try{conn.close(); }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
			}
	
			
			
	
		}
	}
			
	
	
