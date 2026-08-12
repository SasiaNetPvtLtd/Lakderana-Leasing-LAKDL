// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:21-09-2006
      
     
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
   
public class LAKDL_FA_OP_Save_cheque_print extends HttpServlet {
		
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
			
			LAKDL_FA_Bulk_Printing_Main chq_print=new LAKDL_FA_Bulk_Printing_Main();

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
			
			int mm_page_count=0;
			String[] m_temp_list=new String[100];
				
			LAKDL_FA_Bulk_Printing_Main m_Print_cheques=new LAKDL_FA_Bulk_Printing_Main();
			
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_CHEQUE_PRINT(:1,:2); END;");

			String m_scr_num1=(String)m_sn_methods.met_formdata(reqstr,"NUM_CHKS");
			int m_num1=Integer.parseInt(m_scr_num1);
			
			for(int i=0;i<m_num1;i++){
				
				String m_action=(String)m_sn_methods.met_formdata(reqstr,"TXT_PRINT_STATUS_"+i+"");
				
				if(m_action.equals("on")){
					String m_payment_no=(String)m_sn_methods.met_formdata(reqstr,"TXT_PAYMENT_NO_"+i+"");
					//out.println("m_payment_no="+m_payment_no);

					m_msg = "'Information saved successfully ";
					callstmt1.setString(1,m_payment_no);
					callstmt1.setString(2,m_username);
					callstmt1.execute();
					m_temp_list[mm_page_count]=m_payment_no;
					mm_page_count++;

				}
			}
			callstmt1.close();
			
			String[] m_paycount=new String[mm_page_count];
			for (int j=0;j<m_paycount.length;j++){
				m_paycount[j]=m_temp_list[j];
			}
			
			//cheques printing goes here
			if(mm_page_count>0){
				int mm=m_Print_cheques.printing_interface_factoring_chq(m_paycount);	
			}
			     
			conn.commit();
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+"');");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"FA_OP_display_cheque_print';");
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
