// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_FA_MAS_Save_Fee_Struct extends HttpServlet {
		
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
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;

			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			conn.setAutoCommit(false);
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".FA_MAS_SAVE_FEE_STRUCT(:1,:2,:3,:4,:5); END;");
  			
			callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_FEE_PACK_CODE")).trim());
			callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_FEE_PACK_DESC")).trim());
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_DEFAULT_VALUE"));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(5,m_username);
			callstmt.execute();
			callstmt.close();
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".FA_MAS_SAVE_FEE_STRUCT_DETAILS(:1,:2,:3,:4,:5,:6,:7); END;");

			String m_model_code=(String)m_sn_methods.met_formdata(reqstr,"TXT_FEE_PACK_CODE");
			String m_screen_name=m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");
			String m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"NUM_CHKS");
			int m_num=Integer.parseInt(m_scr_num);
			
			int m_tnum=0;
					
			for(int i=0;i<m_num;i++){
				String m_chk=(String)m_sn_methods.met_formdata(reqstr,"CHK_"+i+"");
				String m_fee_code=(String)m_sn_methods.met_formdata(reqstr,"FEE_CODE_"+i+"");
				String m_fee_amout=m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"FEE_MIN_ALLO_"+i+""));
				String m_fee_comm=(String)m_sn_methods.met_formdata(reqstr,"FEE_PACK_DESC_"+i+"");
				
				if(m_chk.equals("on")){
				m_tnum++;
				callstmt.setString(1,m_model_code);
				callstmt.setString(2,m_fee_code);
				callstmt.setString(3,m_fee_comm);
				callstmt.setString(4,m_fee_amout);
				callstmt.setString(5,m_screen_name);
				callstmt.setString(6,m_username);
				callstmt.setInt(7,m_tnum);
				callstmt.execute();
				}
			}
			callstmt.close();
			conn.commit();
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"FA_MAS_display_Fee_Struct';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();
 
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());
			
			conn.rollback();
			}catch(Exception e){}
			
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
