// SCREEN NAME SAVE ODI ADJUSTMENT
// CREATED BY DINETH MEEMANAGE
// CREATED DATE 2008-12-04


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_ODI_Adjustment_Save extends HttpServlet {
		
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
			out = res.getOutputStream();
		
			PrintStream out = new PrintStream(res.getOutputStream());
	 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			String m_user="";
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			conn.setAutoCommit(false); 	
			int m_maxentries     = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count"));
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_ODI_ADJ_SAVE(:1,:2,:3,:4,:5,:6); END;");
			for (int j = 0; j < m_maxentries; j++) {
			String m_req =(String)m_sn_methods.met_formdata(reqstr,"Text_standard"+(Integer.toString(j)));
			out.println(m_req);
			if(!m_req.equals("YES")){
							continue;
							}
							else //if(m_chk_required.equals("on"))
							{
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"ODI_NO_"+(Integer.toString(j))));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"INV_NO_"+(Integer.toString(j))));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"ODIB_AMOUNT_"+(Integer.toString(j))));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"SETT_AMOUN_"+(Integer.toString(j))));
			}
			callstmt.setString(5,m_username);
			callstmt.setString(6,m_scr_name);
			callstmt.execute();
			}
				callstmt.close();
				conn.commit(); 
			
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("m_scr_name='"+m_scr_name+"'");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_CR_ODI_Stop?chksql=main_page';");
		  out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();
      out.close();
			


		}	catch (Exception E) {
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

