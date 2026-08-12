// created by udara on 31-10-2013
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_PRO_Enter_Deletion_letter_gen_save_process extends HttpServlet {

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
            //String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			int count=0;

			//int row_count = Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"REC_COUNT"));
			//String m_chk_type = "off"; 
			
			String m_chksql=req.getParameter("chksql");
			String application_no=req.getParameter("application_no");
			String m_client_code=req.getParameter("client_code");
			String m_finance_no=req.getParameter("finance_no"); 
			String m_document_code=req.getParameter("document_code"); 
			String m_del_code=req.getParameter("del_code");  
			
			String mm_checked_status = ""; // added by udara 25-11-2021
			
			// added by udara 25-11-2021
			if(req.getParameter("checked_status")!=null){
				mm_checked_status = req.getParameter("checked_status");
			}
			// end by udara 25-11-2021
			
				//callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_MAS_SEIZER_DETAILS_SAVE(:1,:2,:3,:4); END;"); // to be change for letter generation
			    //callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_DEL_LETTER_PROCESS_SAVE_2(:1,:2,:3); END;");  // commented by udara 25-11-2021
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_DEL_LETTER_PROCESS_SAVE_2(:1,:2,:3,:4); END;"); // added by udara 25-11-2021
				//for(int k=0;k<row_count;k++){
					
				//	m_chk_type = (String)m_sn_methods.met_formdata(reqstr,"CHK_"+(Integer.toString(k)));
			
				//	if(m_chk_type.equals("on")){

						//callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"HID_APP_NO_"+(Integer.toString(k))));
						//callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"HID_SEIZER_NO_"+(Integer.toString(k))));
						callstmt.setString(1,m_finance_no);
						callstmt.setString(2,m_username);
						callstmt.setString(3,"PRINT");
						callstmt.setString(4,mm_checked_status); // added by udara 25-11-2021
						callstmt.execute();
						
				//	}
				//}
			callstmt.close();
			
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			//out.println("window.location.href='"+m_url+"/LAKDL_AF_CR_PRO_Enter_Seizer_details_app?chksql=main_page';");
			//out.println("window.location.href='"+m_url+"/LAKDL_AF_CR_PRO_Deletion_letter_new_process?chksql=main_page&application_no="+application_no+"&client_code="+m_client_code+"&document_code=CEAS_ORDER&button_status=N&print=FALSE&del_code="+m_del_code+"';"); // commented by udara 05-06-2019
			out.println("window.location.href='"+m_url+"/LAKDL_AF_CR_PRO_Deletion_letter_new_process?chksql=main_page&application_no="+application_no+"&client_code="+m_client_code+"&document_code=CEAS_ORDER&finance_no="+m_finance_no+"&button_status=N&print=FALSE&del_code="+m_del_code+"';");  // added by udara 05-06-2019
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();
		//	}

		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error When Saving Record..');");
			//out.println("window.history.back();"); 
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
		
