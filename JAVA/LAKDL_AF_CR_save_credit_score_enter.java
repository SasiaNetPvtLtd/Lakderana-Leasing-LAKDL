//--
//SCREEN NAME	:CREDIT SCORE EVALUATION
//MODIFIED BY	:DELANJALI
//DATE/TIME		:25-01-2007
//NOTES				:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_save_credit_score_enter extends HttpServlet {
	
	
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1,callstmt2;
  String reqstr;
	ServletOutputStream out = null;

	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	synchronized(this){ 

		try {
			//out.println("		window.open('ok')");

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			//PrintStream out = new PrintStream(res.getOutputStream());
			out = res.getOutputStream();

			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
     
     //Modifid Nuwan De Silva on 18-04-2007--------------------
		//	SCREEN_METHODS m_sn_methods1 = new SCREEN_METHODS();
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
			
			//m_html_client_url=SCREEN_METHODS.html_client_url;
			//m_class_url=SCREEN_METHODS.servlet_client_url.trim()+":"+SCREEN_METHODS.client_t3_port.trim();
			
	    //modified nuwan de silva 18-04-2007-------------------------		
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
      int m_num=0; 

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			String m_app_code=(String)m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO");
			conn.setAutoCommit(false);

			m_msg = "'Information saved successfully'";
			m_url = m_class_url;

			String m_screen_name=m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");
			String m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"NUM_COLS");
			String m_cr_eval=(String)m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_EVAL");
			int  m_score_cat_num=0;
			
			if(!m_screen_name.equals("DELETE")){
			 m_score_cat_num=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"SCORE_NUM"));
			
			}
			
			
			String m_fschema_name=m_sn_methods.client_name.trim();
     	
			//Added	By Nuwan De Silva 16-05-07---------------------
			//if(!m_scr_num.equals(" ") || m_scr_num.equals(null)){
			
			//modified by delanjali
			//date 2007-06-11
			//reason error in saving
			if(!m_scr_num.equals("") || m_scr_num==null){
			m_num=Integer.parseInt(m_scr_num);
			}
			//-------------------------------------------------------

			//out.println("ok");
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_CREDIT_SCORE_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;");
			callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_EVAL"));
			callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_SCORE_MODEL_CODE"));
			callstmt1.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_TOTAL_SCORE_APP")));
			callstmt1.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_TOTAL_SCORE")));

			String m_comment=(String)(m_sn_methods.met_formdata(reqstr,"TXT_COMMENTS")).trim();
			
			callstmt1.setString(6,m_sn_methods.format_text_area_string(m_comment));
			callstmt1.setString(7,m_screen_name);
			callstmt1.setString(8,m_username);

			callstmt1.setString(9,"V-APP");
			callstmt1.setString(10,"VERIFY1");
			callstmt1.execute();
			callstmt1.close();
			
			//----modified by : delanjali------------------------------------------------------------
			//----date				:	2007-06-11-----------------------------------------------------------
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_CREDIT_SCORE_SAVE_NEW(:1,:2,:3,:4,:5); END;");
			String m_app=m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO");
			if(!m_screen_name.equals("DELETE")){
				
			//out.println("m_score_cat_num : "+ m_score_cat_num);	
				
			for(int d=0;d<m_score_cat_num;d++){
			String m_scr=m_sn_methods.met_formdata(reqstr,"SC_CODE_"+d);
			String m_com=m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"COMMENT_"+d));
		    
			
			//out.println("SC_CODE_ : "+ m_scr);
			//out.println("COMMENT_ : "+ m_com);
			
		if(m_scr.equals("")){
			m_scr="";
			}
			if(m_com.equals("")){
			m_com="";
			}
			
			//out.println("d : "+ d);	
		
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			callstmt.setString(2,m_scr);
			callstmt.setString(3,m_com);
			callstmt.setString(4,m_screen_name);
			callstmt.setString(5,m_username);
			//if(!m_app.equals("") && !m_scr.equals("")){
			callstmt.execute();
			//}
			}
			}
			else{
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
			callstmt.setString(2,"");
			callstmt.setString(3,"");
			callstmt.setString(4,m_screen_name);
			callstmt.setString(5,"");
			if(!m_app.equals("")){
			callstmt.execute();
			}

			}
			callstmt.close();

			//---------------------------------------------------------------------------------------
			
			
			
			callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_CREDIT_SCORE_SAVE_DETAIL(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");			
		
		   
			//int m_num=Integer.parseInt(m_scr_num);
			
			for(int i=1;i<m_num;i++){
				String m_sub_cat=(String)m_sn_methods.met_formdata(reqstr,"SUB_CAT_"+i+"");
				String m_rate_code=(String)m_sn_methods.met_formdata(reqstr,"RATE_"+i+"");
				String m_score=(String)m_sn_methods.met_formdata(reqstr,"SCORE_"+i+"");
				
				//out.println("i : "+ i);	
				
				 //out.println("SUB_CAT_ : "+ m_sub_cat);
				// out.println("RATE_ : "+ m_rate_code);
				// out.println("SCORE_ : "+ m_score);	
				
				 String m_comment1="";
				// out.println("COMMENT_ : "+ m_comment1);
				//String m_comment1=(String)(m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"COMMENT_"+i+"")));
               //String m_comment1=m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"COMMENT_"+i));
					
				callstmt2.setString(1,m_app_code);
				callstmt2.setString(2,m_sub_cat);
				callstmt2.setString(3,m_rate_code);
				callstmt2.setString(4,m_score);
				callstmt2.setString(5,m_comment1.trim());
				callstmt2.setString(6,m_screen_name);
				callstmt2.setString(7,m_username);
				callstmt2.setInt(8,i);
				callstmt2.setString(9,m_cr_eval);
				callstmt2.execute();
			}
			callstmt2.close();
			conn.commit();

			conn.close();
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			if(m_screen_name.equals("NEW")){
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Score_Eval_Details?status=VERIFY1';");
			}
			if(m_screen_name.equals("DELETE") || m_screen_name.equals("EDIT")){
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Score_Eval_Details?status=V-APP';");
			}

			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();
      out.close();
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
		     	//PrintStream out = new PrintStream(res.getOutputStream());

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
		//PrintStream out = new PrintStream(res.getOutputStream());
		try{conn.setAutoCommit(true);}catch(Exception e){}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
		
		
		
		
		
	}
	}
}
