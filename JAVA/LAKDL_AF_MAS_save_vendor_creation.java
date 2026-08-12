//--
//SCREEN NAME:SAVE SYSTEM ADMINISTRATION - VENDOR CREATION
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_save_vendor_creation extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url,m_vender_code,m_branch_code;
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
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			int m_chksql;
			String m_field,number;
		  String br_1 ="" ;
			
			
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

			String m_fschema_name=m_sn_methods.client_name.trim();

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			PrintStream out = new PrintStream(res.getOutputStream());
			
			m_field = req.getParameter("number");
			m_chksql = Integer.parseInt(m_field);

			m_url = m_class_url;
			
			synchronized(this){ 
			
			conn.setAutoCommit(false);	
			
			
			String vend  = m_sn_methods.met_formdata(reqstr,"TXT_VENDOR_CODE");
			String m_opt = m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_VENDOR_CREATION(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
			
		  if(m_opt.equals("NEW")) {
        m_vender_code ="";
				if (m_vender_code=="") {
				callstmt.registerOutParameter(1,java.sql.Types.CHAR);
				}
				else {
				callstmt.setString(1,m_vender_code.trim());
				}			
			
		} else if(!(m_opt.equals("NEW"))){
			m_vender_code  = m_sn_methods.met_formdata(reqstr,"TXT_VENDOR_CODE");
			callstmt.setString(1,m_vender_code.trim());
		}			
			
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_NAME"));
			callstmt.setString(3,(m_sn_methods.met_formdata(reqstr,"TXT_CATEGORY")).trim());
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_TYPE"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_DEFAULT_VALUE"));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(7,m_username);
			callstmt.setString(8,m_vender_code);
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_VAT_REG"));//added by Sandun on 05-01-2009
			callstmt.execute();
	
			if (m_vender_code.equals("")) {
			m_vender_code = callstmt.getString(1);
			}		

	
			
						
			for (int k=0; k<=m_chksql; k++) 
			{
			

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_VEND_LOC_CREA(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;");

			
			
			
			//-----------------------------
		if(m_opt.equals("NEW")) {
  			callstmt.setString(1,m_vender_code.trim());
        m_branch_code ="";
				if (m_branch_code=="") {
				callstmt.registerOutParameter(2,java.sql.Types.CHAR);
				}
   	}
		else if(!(m_opt.equals("NEW"))){
  	 callstmt.setString(1,m_vender_code.trim());
		 m_branch_code=m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_CODE_"+k);
			
    if((m_branch_code==null)||(m_branch_code=="")) {
			
				callstmt.registerOutParameter(2,java.sql.Types.CHAR);
    }
		else  if(!((m_branch_code==null) ||(m_branch_code=="")) ) {
     callstmt.setString(2,m_branch_code.trim());
		}	
			
			
		}	
		
			
			
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_ADDRESS_"+k));
			callstmt.setString(4,(m_sn_methods.met_formdata(reqstr,"TXT_CITY_CODE_"+k)).trim());
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_DEFAULT_VALUE1_"+k));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(7,m_username);
			callstmt.setString(8,Integer.toString(k));
      callstmt.setString(9,m_branch_code.trim());
			callstmt.setString(10,(m_sn_methods.met_formdata(reqstr,"TXT_COUNTRY_CODE_"+k)).trim()); //added by nuwan de silva on 20-11-2007
			callstmt.execute();
	
	
			if ((m_branch_code=="")|| (m_branch_code==null)  ) {
			m_branch_code = callstmt.getString(2);
			}		
		
		

	callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_VEN_CON_CREAT(:1,:2,:3,:4,:5,:6); END;");

			
		//if(m_opt.equals("NEW")) {
			callstmt1.setString(1,m_vender_code);
			callstmt1.setString(2,m_branch_code.trim());
   //	}
		/*else if((m_opt.equals("EDIT"))){
		
		 callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_VENDOR_CODE"));
		 m_branch_code=m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_CODE_"+k);
    if((br_1==null)||(br_1=="")) {
		 callstmt1.setString(1,m_branch_code);
    }
		else  if(!((br_1==null) ||(br_1=="")) ) {
     callstmt1.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_CODE_"+k)).trim());
		}	
		}*/
			
			
			callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_TEL_NO_"+k));
			callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_FAX_NO_"+k));
			callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt1.setString(6,m_username);
			callstmt1.execute();
			conn.commit();					
			}
					
					
		if(m_opt.equals("NEW")) {
		m_msg= "'New vender is saved successfully.The vender code is "+m_vender_code+"'";
		} else if(m_opt.equals("EDIT")){
		m_msg= "'Vender is modified successfully.The modified vender code is "+m_vender_code+"'";
		} else if(m_opt.equals("DACT")){
		m_msg= "'Vender is deactivated successfully.The deactivated vender code is "+m_vender_code+"'";
		} else if(m_opt.equals("RACT")){
		m_msg= "'Vender is reactivated successfully.The reactivated vender code is "+m_vender_code+"'";
		}   

		
			conn.setAutoCommit(true);			
			conn.commit();
			}//end syncro.
	

		
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_MAS_display_vendor_creation';");
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
			//out.println("window.history.back();"); 
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
		
