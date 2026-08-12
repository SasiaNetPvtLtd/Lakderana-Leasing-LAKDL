
//--
//SCREEN NAME:SAVE SYSTEM ADMINISTRATION -INSPECTION AND VALUATION_REPORT
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_PRO_save_inspection_and_valuation_report extends HttpServlet {
		
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
			int m_chksql;
			String m_field,number;
			
			
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

			conn.setAutoCommit(false);
			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_chksql = Integer.parseInt(req.getParameter("number"));
			m_field=req.getParameter("field");
			
			int xx=0;
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			//out.println("wwwwwwww"+m_chksql);
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_SAVE_INSPEC_AND_VAL_RPT(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22); END;");
			
			/*out.println('1'+m_sn_methods.met_formdata(reqstr,"TXT_VALUATION_NO"));
			out.println('2'+m_sn_methods.met_formdata(reqstr,"TXT_ASSET_ID"));
			out.println('3'+m_sn_methods.met_formdata(reqstr,"TXT_SUB_MODEL_CODE"));
			out.println('4'+m_sn_methods.met_formdata(reqstr,"TXT_REG_NO"));
			out.println('5'+m_sn_methods.met_formdata(reqstr,"TXT_ENGINE_NO"));
			out.println('6'+m_sn_methods.met_formdata(reqstr,"TXT_CHASSIS_NO"));
			out.println('7'+m_sn_methods.met_formdata(reqstr,"TXT_COLOUR"));
			out.println('8'+m_sn_methods.met_formdata(reqstr,"TXT_MODEL_CODE"));
			out.println('9'+m_sn_methods.met_formdata(reqstr,"TXT_NOTES"));
			out.println(m_sn_methods.met_formdata(reqstr,"TXT_REMARKS"));
			out.println(m_sn_methods.met_formdata(reqstr,"TXT_VALUATION_DATE"));
			out.println(m_sn_methods.met_formdata(reqstr,"TXT_VALUE"));
			out.println(m_sn_methods.met_formdata(reqstr,"TXT_TYPE_OF_BODY"));
			out.println(m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_REG"));
			out.println(m_sn_methods.met_formdata(reqstr,"TXT_METER_READING"));
			out.println(m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			out.println(m_username);
			out.println('#'+m_sn_methods.met_formdata(reqstr,"TXT_GENERAL_INDEX"));
			out.println(m_sn_methods.met_formdata(reqstr,"TXT_SEATING_CAPACITY"));
			out.println(m_sn_methods.met_formdata(reqstr,"TXT_NO_OF_CYLINDERS"));

			
			out.println(Integer.toString(xx));*/



		

			callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_VALUATION_NO")).trim());
			callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_ASSET_ID")).trim());
			callstmt.setString(3,(m_sn_methods.met_formdata(reqstr,"TXT_SUB_MODEL_CODE")).trim());
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_REG_NO"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_ENGINE_NO"));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_CHASSIS_NO"));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_COLOUR"));
			callstmt.setString(8,(m_sn_methods.met_formdata(reqstr,"TXT_MODEL_CODE")).trim());
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_NOTES"));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_REMARKS"));
			callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_VALUATION_DATE"));
			callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_VALUE"));
			callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_TYPE_OF_BODY"));
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_DATE_OF_REG"));
			callstmt.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_METER_READING"));
			callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(17,m_username);
			callstmt.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_GENERAL_INDEX"));
			callstmt.setString(19,m_sn_methods.met_formdata(reqstr,"TXT_SEATING_CAPACITY"));
			callstmt.setString(20,m_sn_methods.met_formdata(reqstr,"TXT_NO_OF_CYLINDERS"));
			callstmt.setString(21,Integer.toString(xx));
			callstmt.setString(22,(m_sn_methods.met_formdata(reqstr,"TXT_APP_NO")).trim());
			
			callstmt.execute();
						
			for (int k=0; k<=m_chksql; k++) 
			{
			
			//if(k==0){
			//String m_field=	m_sn_methods.met_formdata(reqstr,"TXT_FILED_CODE_"+(Integer.toString(k)));

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_SAVE_INS_AND_VAL_RPT1(:1,:2,:3,:4,:5,:6,:7,:8); END;");
			/*out.println(m_sn_methods.met_formdata(reqstr,"TXT_VALUATION_NO"));
			out.println(m_sn_methods.met_formdata(reqstr,"TXT_ASSET_ID"));
			out.println(m_sn_methods.met_formdata(reqstr,"TXT_FILED_CODE_"+k));
			out.println(m_sn_methods.met_formdata(reqstr,"TXT_REMARK_"+k));*/

				
			callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_VALUATION_NO")).trim());
			callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_ASSET_ID")).trim());
			callstmt.setString(3,(m_sn_methods.met_formdata(reqstr,"TXT_FILED_CODE_"+k)).trim());
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_STATUS_"+k));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_REMARK_"+k));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(7,m_username);
			callstmt.setString(8,Integer.toString(k));
			
			//if  (m_field.trim().equals("")) {
			//			  break;
			//}
			
			
			callstmt.execute();
			conn.commit();
			}
			callstmt.close();


	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/LAKDL_AF_MAS_display_inspection_and_valuation_report';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();

		}
	/*
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
}*/

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
		
