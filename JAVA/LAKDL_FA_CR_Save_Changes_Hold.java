// DEVELOP BY : SANDUN FOR OFSCL FACTORING    DATE:20-09-2008
   
 
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_FA_CR_Save_Changes_Hold extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1;
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
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
 			
			conn.setAutoCommit(false);
			    
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully ";
			m_url = m_class_url;
					
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_CHANGES_HOLD(:1,:2,:3,:4,:5,:6,:7); END;");

			String m_line_num1=(String)m_sn_methods.met_formdata(reqstr,"hid_line_count");
			int m_num1=Integer.parseInt(m_line_num1);
			String m_chk_status="";
			
			for(int i=0;i<m_num1;i++){
				m_chk_status=(String)m_sn_methods.met_formdata(reqstr,"chkbox_fee_"+(Integer.toString(i)));										
				//out.println("m_chk_status - "+i+"-"+m_chk_status);
				callstmt1.setString(1,(String)m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO"));
				callstmt1.setString(2,(String)m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE"));
				callstmt1.setString(3,(String)m_sn_methods.met_formdata(reqstr,"hid_fee_code_"+(Integer.toString(i))));
				callstmt1.setString(4,(String)m_sn_methods.met_formdata(reqstr,"hid_fee_value_"+(Integer.toString(i))));
				
				if(m_chk_status.equals("on"))
				{	
				callstmt1.setString(5,"Y");
				}
				else // if(m_chk_status.equals("off"))
				{	
				callstmt1.setString(5,"N");
				}
				
				callstmt1.setString(6,m_username);
				callstmt1.setInt(7,i);
				callstmt1.execute();
				
			}
			callstmt1.close();

			   
			conn.commit();
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+"');");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"FA_OP_Charges_Hold?chksql=main_page';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();

		}
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
}
