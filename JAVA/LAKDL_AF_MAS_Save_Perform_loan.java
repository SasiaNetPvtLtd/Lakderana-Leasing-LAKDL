//CREATED BY DINETH MEEMANAGE
//DATE 2008-10-02
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_Save_Perform_loan extends HttpServlet {

	ResultSet rs1;
	Statement stmt;
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
			stmt=conn.createStatement();
      m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			int count=0;
			
			
			
			
			int row_count= Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"perf_num"));
			int row_ncount = Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"nperf_num"));
			String m_chk_type = "off"; 
			String m_chk_type_n = "off"; 
			//out.println("row_ncount "+row_ncount);
			//out.println("row_count "+row_count);
			//callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_PERFORM_CON(:1,:2,:3,:4,:5); END;"); // comnmented by udara 18-02-2014
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_PERFORM_CON(:1,:2,:3,:4,:5,:6,:7); END;"); // added by udara 18-02-2014
			
			if (row_count>0){
			
			for(int k=0;k<row_count;k++){
			m_chk_type = (String)m_sn_methods.met_formdata(reqstr,"TXT_PERF_"+(Integer.toString(k)));
		  //out.println("m_chk_type "+m_chk_type);
			
			if(m_chk_type.equals("on")){			
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_HID_FIN_"+(Integer.toString(k))));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_HID_APP_"+(Integer.toString(k))));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_HID_CLIENT_"+(Integer.toString(k))));
			callstmt.setString(4,m_username);
			callstmt.setString(5,"NPERFORM");
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_HID_VALUE_DATE_"+(Integer.toString(k))));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_REMARKS_"+(Integer.toString(k)))); // added by udara 11-03-2014
			callstmt.execute();
			}
			}
			}
			if(row_ncount>0){
			
			for(int j=0;j<row_ncount;j++){
			m_chk_type_n = (String)m_sn_methods.met_formdata(reqstr,"TXT_NPERF_"+(Integer.toString(j)));
		  //out.println("m_chk_type_n "+m_chk_type_n);
			
			
			if(m_chk_type_n.equals("on")){			
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_HID_NFIN_"+(Integer.toString(j))));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_HID_NAPP_"+(Integer.toString(j))));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_HID_NCLIENT_"+(Integer.toString(j))));
			callstmt.setString(4,m_username);
			callstmt.setString(5,"PERFORM");
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_HID_VALUE_DATE_"+(Integer.toString(j))));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_REMARKS_"+(Integer.toString(j)))); // added by udara 11-03-2014
			callstmt.execute();
			}
			}
			}
			callstmt.close();
			
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/LAKDL_MK_MAS_Performing_Loan_Setup?chksql=main_page';");
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
		
