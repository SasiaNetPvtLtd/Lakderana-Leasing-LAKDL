// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:26-01-2007
    
       
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
     
public class LAKDL_FA_OP_save_invoice_reminder extends HttpServlet {

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
			conn.setAutoCommit(false);
			//**************************************************************		
			//out.println("reqstr="+reqstr);
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url=m_sn_methods.html_client_url;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

			String m_date="";
			int m_num=0;
			int m_tnum=0;
			String m_scr_num="0";
			String m_return_refno="";
			String m_return_realize_no="";
				
			m_msg = "'Information saved successfully ";
			//------------------------------------------------------------------------------------------
			m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"NUM_CHKS");
			m_num=Integer.parseInt(m_scr_num);
			
			if(m_num>0){

				//------------------------------------------------------------------
				callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_OP_SAVE_INVOICE_REMINDERS(:1,:2,:3,:4,:5,:6,:7); END;");
				
				int tnum=0;
				
				if(m_num>0){
				
					for(int i=1;i<=m_num;i++){
						
						String m_status1=m_sn_methods.met_formdata(reqstr,"TXT_APPROVE_TYPE1_"+i).trim();
						String m_status2=m_sn_methods.met_formdata(reqstr,"TXT_APPROVE_TYPE2_"+i).trim();
						String m_status3=m_sn_methods.met_formdata(reqstr,"TXT_APPROVE_TYPE3_"+i).trim();
						String m_status4=m_sn_methods.met_formdata(reqstr,"TXT_APPROVE_TYPE4_"+i).trim();

						if(m_status1.equals("Y")){
							callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_SEQ_NO_"+i).trim());
							callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE_"+i).trim());
							callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO_"+i).trim());
							callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_CODE_"+i).trim());
							callstmt1.setString(5,"PRE_INVOICE_REMINDER");
							callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt1.setString(7,m_username);
							callstmt1.execute();
						}
						if(m_status2.equals("Y")){
							callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_SEQ_NO_"+i).trim());
							callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE_"+i).trim());
							callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO_"+i).trim());
							callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_CODE_"+i).trim());
							callstmt1.setString(5,"FIRST_INVOICE_REMINDER");
							callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt1.setString(7,m_username);
							callstmt1.execute();
						}
						if(m_status3.equals("Y")){
							callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_SEQ_NO_"+i).trim());
							callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE_"+i).trim());
							callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO_"+i).trim());
							callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_CODE_"+i).trim());
							callstmt1.setString(5,"SECOND_INVOICE_REMINDER");
							callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt1.setString(7,m_username);
							callstmt1.execute();
						}
						if(m_status4.equals("Y")){
							callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_SEQ_NO_"+i).trim());
							callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE_"+i).trim());
							callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO_"+i).trim());
							callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_CODE_"+i).trim());
							callstmt1.setString(5,"FINAL_INVOICE_REMINDER");
							callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt1.setString(7,m_username);
							callstmt1.execute();
						}

					}
				}
				callstmt1.close();
			}
			//-------------------------------------------------------------------------------------------
			conn.commit();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+"');");
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_invoice_reminder';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();

		}
		catch (Exception ex) {
			try{conn.rollback();}catch(Exception e){}
			out.println("Error:"+ex.toString());
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
			try{conn.setAutoCommit(true);}catch(Exception e){}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}

