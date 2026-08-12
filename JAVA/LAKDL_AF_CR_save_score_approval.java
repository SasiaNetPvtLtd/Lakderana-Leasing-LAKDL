//--
//SCREEN NAME:SAVE SYSTEM ADMINISTRATION - CREDIT SCORE MODEL CREATION
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_save_score_approval extends HttpServlet {
		 
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt2;
  String reqstr;
	ServletOutputStream out = null;

	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	synchronized(this){ 

		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();

//			PrintStream out = new PrintStream(res.getOutputStream());
 
			//SCREEN_METHODS m_sn_methods = new SCREEN_METHODS();
			
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
			String m_screen_name="";
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
 			conn.setAutoCommit(false); //added by nuwan de silva on 03-09-07	
			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			//String m_app_no=(String)m_sn_methods.met_formdata(reqstr,"Hid_app_no"); 
		  int m_maxentries     = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
			m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");	
				
			
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			String m_fschema_name=m_sn_methods.client_name.trim();
	
			String m_applicaton_no        = req.getParameter("app_no");
			String m_app_stage=req.getParameter("appro");
			String m_return_status=req.getParameter("return_status"); //added by nuwan de silva on 04-10-07
			
			String m_chk=m_sn_methods.met_formdata(reqstr,"chk_app");
			String m_chk_rej=m_sn_methods.met_formdata(reqstr,"chk_rej");
      String m_chk_return=m_sn_methods.met_formdata(reqstr,"chk_return"); //added by nuwan de silva on 04-10-07
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_SAVE_CRSCORE_APPROVAL_1(:1,:2,:3,:4,:5,:6); END;");
			
			//========modifed by nuwan de silva 13-06-07====================================
			
			//String m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"NUM_CHKS");
			//int m_num=Integer.parseInt(m_scr_num);
			
			//for(int i=0;i<m_num;i++){
			//	m_chk=(String)m_sn_methods.met_formdata(reqstr,"CHK_"+i+"");
			//	String m_chk_rej=(String)m_sn_methods.met_formdata(reqstr,"CHK_REJ_"+i+"");
				
			//	String m_app_code=(String)m_sn_methods.met_formdata(reqstr,"APP_"+i+"");
			//	String m_comment=(String)m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"COMMENT_"+i+"")); //modified nuwan de silva 22-05-07
				
				
				if(m_chk.equals("Y")){
				
				callstmt.setString(1,m_applicaton_no);
				//callstmt.setString(2,m_comment.trim());
				callstmt.setString(2,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"TXT_REMARKS")).trim()); //modified by nuwan de silva 22-05-07
				callstmt.setString(3,m_username);
				callstmt.setString(4,m_app_stage);//!!modifed (2006/11/12)
				//callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"SCORE_"+i+""));
				callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"Hid_Score_Model"));
				callstmt.setString(6,"APPROVE");
				callstmt.execute();
				}
				
				if(m_chk_rej.equals("Y")){
				callstmt.setString(1,m_applicaton_no);
				callstmt.setString(2,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"TXT_REMARKS")).trim()); //modified by nuwan de silva 22-05-07
				callstmt.setString(3,m_username);
				callstmt.setString(4,"REJECT");//!!modifed (2006/11/12)
				//callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"SCORE_"+i+""));
				callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"Hid_Score_Model"));
				//callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"EVAL_"+i+""));
				callstmt.setString(6,"REJECT");
				callstmt.execute();
				}
				//added by nuwan de silva on 04-10-07----------------------------------
				if(m_chk_return.equals("Y")){
				callstmt.setString(1,m_applicaton_no);
				callstmt.setString(2,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"TXT_REMARKS")).trim()); 
				callstmt.setString(3,m_username);
				callstmt.setString(4,m_return_status); 
				callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"Hid_Score_Model"));
				callstmt.setString(6,"RETURN");
				callstmt.execute();
				}
				
				
			//}

				
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_CONDITIONS(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;");
			  callstmt.registerOutParameter(2,java.sql.Types.CHAR); 
 
		    for (int j = 0; j < m_maxentries; j++) {
		    String m_remarks_foll=m_sn_methods.met_formdata(reqstr,"TXT_CONDITION"+(Integer.toString(j)));
  
	     callstmt.setString(1,m_applicaton_no);
	     callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_FOLLOW_UP_NO"+(Integer.toString(j))));
	     callstmt.setString(3,m_screen_name);
	     callstmt.setString(4,m_username);
	     callstmt.setString(5,m_scr_name);
				
				
		    if(m_remarks_foll==null){
		     callstmt.setString(6,"-");
		 
		    }
		    else{
		    callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_CONDITION"+(Integer.toString(j))));
		    }
    
		    callstmt.setString(7,"COLLE_DOC");
	      callstmt.setString(8,"AF");
	      callstmt.setString(9,"PENDING");
	      callstmt.setString(10,"CREDIT");
    
    
		    if(m_remarks_foll.equals("")){
		    break;
		    }
    
    
	     	callstmt.execute();
		   	}  
   
				//callstmt.close();
			  //conn.close();
				
				callstmt.close(); //added by nuwan de silva on 03-09-07----------------------
				conn.commit(); //added by nuwan de silva on 03-09-07----------------------
			
				String m_status="";
				
				if(m_app_stage.equals("VERIFY-M")){
				//m_status="V-APP";
				m_status="V-RECOM";
				
				}
				else if(m_app_stage.equals("VERIFY2")){
				//m_status="VERIFY1";
				m_status="VERIFY-M"; //modified by nuwan de silva 05-06-07-----------------
				}
				if(m_app_stage.equals("V-RECOM")){
				m_status="V-APP";
				}

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("m_scr_name='"+m_scr_name+"'");
			out.println("m_chk='"+m_chk+"'");
			
			//out.println("alert('m_scr_name'+m_scr_name)"); 
			//out.println("alert('m_chk'+m_chk)"); 
			out.println("if(m_scr_name=='AF_MK_APP_STATUS_APPROVE_3' && m_chk=='Y'){");
		  //out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Printing?chksql=main_page&application_no="+m_app_no+"';");
			//----modified by :delanjali------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			//----date				:2007-06-06-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_CR_PRO_Application_Status_Report_appr1?chksql=main_page&pre="+m_status+"&appro="+m_app_stage+"&qry="+m_status+"&applicaton_no="+m_applicaton_no+"';");
			out.println("}");
		  out.println("else if(m_scr_name=='AF_CR_CREDIT_RECOMMENDATION' && m_chk=='Y'){");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Recommendation?chksql=main_page&pre="+m_status+"&appro="+m_app_stage+"&qry="+m_status+"';");
			
			out.println("}");
			out.println("else{");
			//out.println("alert('here')"); //LAKDL_AF_CR_PRO_Credit_Verifi_app_1
			//out.println("window.close()");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_CR_PRO_Application_Status_Report_appr1?chksql=main_page&pre="+m_status+"&appro="+m_app_stage+"&qry="+m_status+"&applicaton_no="+m_applicaton_no+"';");
			out.println("}");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();
      out.close();
		 //	conn.close(); //comment by nuwan de silva 03-09-07
		}
		/*catch (Throwable th) {
     	PrintStream out = new PrintStream(res.getOutputStream());
			th.printStackTrace(out);
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
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
		
	}
	}
}
