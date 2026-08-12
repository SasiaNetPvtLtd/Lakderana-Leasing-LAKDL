//--
//SCREEN NAME	:SAVE SYSTEM ADMINISTRATION - MAINTENANCE RATE
//MODIFIED BY	:delanjali
//DATE/TIME		:16-01-2007
//NOTES				:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_save_maintenance_rate extends HttpServlet {
		
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
			String m_save_procedure_name;
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			conn.setAutoCommit(false);

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			int m_chksql;
			
			m_chksql = Integer.parseInt(req.getParameter("number"));



			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_MAINTENANCE_RATE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11); END;");
			int k=0;
			for(int i=0;i<m_chksql;i++)	{
					
			String m_status = m_sn_methods.met_formdata(reqstr,"CHK_STATUS_"+i+"");
			String m_sub_cat=m_sn_methods.met_formdata(reqstr,"TXT_SUB_TYPE_CODE_"+i);
			String m_inc=m_sn_methods.met_formdata(reqstr,"TXT_INCREASE_DECREASE_"+i);

			if(m_status.equals("")){
			m_status="off";
			}
			
			if(m_status.equals("on")){
		
		  String m_inc1=m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_INCREASE_DECREASE_"+i));
			String m_amt=m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AMOUNT_"+i));
			if(m_inc1==null || m_inc1.equals("")){
			m_inc1="0";
			}
			if(m_amt==null || m_amt.equals("")){
			m_amt="0";
			}
      // comment by nuwan de silva on 10-12-2007 ___________________________________________
			//callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_MAKE_CODE")).trim());
			//callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_SUB_MODEL_CODE")).trim());
			
			callstmt.setString(1,"");
			callstmt.setString(2,"");
			//_____________________________________________________________________________________
			
			//callstmt.setString(3,(m_sn_methods.met_formdata(reqstr,"TXT_TYPE_"+i)).trim());  
			callstmt.setString(3,m_sub_cat);//Modified Nuwan De Silva 15-05-07
			
			// comment by nuwan de silva on 10-12-2007 -----------------------------------------
			//callstmt.setString(4,(m_sn_methods.met_formdata(reqstr,"TXT_MILEAGE_CODE")).trim());
			callstmt.setString(4,"");
			
			callstmt.setString(5,m_inc1);
			callstmt.setString(6,m_amt);

			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_DEFAULT_VALUE_"+i));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(9,m_username);
			callstmt.setString(10,Integer.toString(i));
      callstmt.setString(11,(m_sn_methods.met_formdata(reqstr,"TXT_ITEM_CAT_CODE")).trim()); 
			
	
			if(!m_sub_cat.equals("") && !m_inc.equals("")){
							callstmt.execute();
							k=k+1;
							}
			}
			
			
			else if(m_status.equals("off") && k==0){
			// comment by nuwan de silva on 10-12-2007 -----------------------------------------
			//callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_MAKE_CODE")).trim());
			//callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_SUB_MODEL_CODE")).trim());
			// added by nuwan de silva on 10-12-2007 -----------------------------------------
			callstmt.setString(1,"");
			callstmt.setString(2,"");
					
			
			//callstmt.setString(3,(m_sn_methods.met_formdata(reqstr,"TXT_TYPE_"+i)).trim());
			
      callstmt.setString(3,m_sub_cat);//Modified Nuwan De Silva 15-05-07
		//callstmt.setString(3,(m_sn_methods.met_formdata(reqstr,"TXT_SUB_TYPE_CODE_"+i)).trim());
		
			// comment by nuwan de silva on 10-12-2007 -----------------------------------------
			//callstmt.setString(4,(m_sn_methods.met_formdata(reqstr,"TXT_MILEAGE_CODE")).trim());
			// added by nuwan de silva on 10-12-2007 -----------------------------------------
			callstmt.setString(4,"");
			
			callstmt.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_INCREASE_DECREASE_"+i)));
			callstmt.setString(6,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AMOUNT_"+i)));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_DEFAULT_VALUE_"+i));
			callstmt.setString(8,"NEW");
			callstmt.setString(9,m_username);
			callstmt.setString(10,Integer.toString(i));
			callstmt.setString(11,(m_sn_methods.met_formdata(reqstr,"TXT_ITEM_CAT_CODE")).trim()); 
			callstmt.execute();

			}
										

		

}
conn.commit();
	callstmt.close();
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_MAS_display_maintenance_rate?chksql=main_page';");
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
			out.println("window.history.back();"); 
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

