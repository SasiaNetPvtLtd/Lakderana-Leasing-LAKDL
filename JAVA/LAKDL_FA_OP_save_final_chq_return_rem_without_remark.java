// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:26-01-2007
    
       
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
     
public class LAKDL_FA_OP_save_final_chq_return_rem_without_remark extends HttpServlet {

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
				callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_OP_SAVE_INIT_CHQ_RETURN_RM(:1,:2,:3,:4); END;");
				
				int tnum=0;
				
				if(m_num>0){
				
					for(int i=1;i<=m_num;i++){
						
						String m_status=m_sn_methods.met_formdata(reqstr,"TXT_APPROVE_TYPE_"+i).trim();

						if(m_status.equals("Y")){
							callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_RETURN_NO_"+i).trim());
							callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_RETURN_COMMENT_"+i));
							callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt1.setString(4,m_username);
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
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_final_cheque_return_reminder_without_rem';");
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

