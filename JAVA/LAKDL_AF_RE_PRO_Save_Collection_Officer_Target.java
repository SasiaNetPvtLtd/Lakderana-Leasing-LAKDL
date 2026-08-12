//SCREEN NAME:SAVE APPLICATION PROCESS
//CREATED BY:NUWAN DE SILVA
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_PRO_Save_Collection_Officer_Target extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt1;
  String reqstr;
	Statement stmt;
	public ResultSet rs;
  ServletOutputStream out = null;

	

	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	synchronized(this){ 

		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
		
		//	PrintStream out = new PrintStream(res.getOutputStream());
			out = res.getOutputStream();

	   // out.println(reqstr);
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
		//	out.println("conn"+conn);
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
		
			String m_screen_name="";
			String m_app_no="";
			String m_vendor_code="";
		  
			String m_status="";
			String m_val_date="";
			String m_scr_name="";
			String m_deposit="";
		//	out.println("m_username"+m_username);
	
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
      
		//	out.println("conn"+conn);
		//	out.println(reqstr);
			
      m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			//m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");	
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
		/*	callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_RE_SAVE_APP_STATUS_CHANGE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10);END;");
											
		//	for (int j = 0; j < m_maxentries; j++) {
			
        //   m_status=(String)m_sn_methods.met_formdata(reqstr,"CHK_RECEIPT"+(Integer.toString(j)));										
							
					//	if(m_status.equals("on")){
					//	}	
					
						
					//Update The Status of The Cheques
					
					//callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"hid_finance_no"+(Integer.toString(j))));													
					//callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"hid_client_code"+(Integer.toString(j))));													
					//callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_STATUS_TYPE_"+(Integer.toString(j))));													
					//callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"hid_app_status"+(Integer.toString(j))));													
					
					
					callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"hid_finance_no"));
					callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"hid_client_code"));
					callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_STATUS_TYPE"));
					callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"hid_app_status"));
					
					
					String m_eff_dd =m_sn_methods.met_formdata(reqstr,"TXT_EFF_DATE_DD");
					String m_eff_mm =m_sn_methods.met_formdata(reqstr,"TXT_EFF_DATE_MM");
					String m_eff_yy =m_sn_methods.met_formdata(reqstr,"TXT_EFF_DATE_YY");
				 
					String m_eff_date="";
					
					if(m_eff_dd.equals("") && m_eff_mm.equals("") &&  m_eff_yy.equals("") )
					{
					m_eff_date=m_eff_dd+m_eff_mm+m_eff_yy; 
					}
					else
					{
					m_eff_date=m_eff_dd+"-"+m_eff_mm+"-"+m_eff_yy;
					}
			
					callstmt1.setString(5,m_eff_date);
					//callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_ENT_COMMENT_"+(Integer.toString(j))));													
					callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_ENT_COMMENT"));
					callstmt1.setString(7,"N");
					callstmt1.setString(8,"NEW");
					callstmt1.setString(9,m_username);
					callstmt1.setString(10,m_scr_name);
					
								
					callstmt1.execute();							
							
				
							
			//	}
			
			*/
			
			
			
			    //============================================================================
					m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");	
				  int hid_count=Integer.parseInt((String)m_sn_methods.met_formdata(reqstr,"Hid_Count"));
		    	callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+".AF_RE_PRO_SAVE_OFFICER_TARGET(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17);END;");
				
						for(int j=0;j<hid_count;j++){
						String branch=(String)m_sn_methods.met_formdata(reqstr,"Hid_branch_"+j);//get account code//1
						String advisor=(String)m_sn_methods.met_formdata(reqstr,"Hid_advisor_"+j);//2
						String bstartdate=(String)m_sn_methods.met_formdata(reqstr,"Hid_St_Date_0");//get start date//3
						
						String bug_amt1=m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"MONTH_1"+"_"+j));//6
						String bug_amt2=m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"MONTH_2"+"_"+j));//7
						String bug_amt3=m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"MONTH_3"+"_"+j));//8
						String bug_amt4=m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"MONTH_4"+"_"+j));//9
						String bug_amt5=m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"MONTH_5"+"_"+j));//10
						String bug_amt6=m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"MONTH_6"+"_"+j));//11
						String bug_amt7=m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"MONTH_7"+"_"+j));//12
						String bug_amt8=m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"MONTH_8"+"_"+j));//13
						String bug_amt9=m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"MONTH_9"+"_"+j));//14
						String bug_amt10=m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"MONTH_10"+"_"+j));//15
						String bug_amt11=m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"MONTH_11"+"_"+j));//16
						String bug_amt12=m_sn_methods.met_unformat_number((String)m_sn_methods.met_formdata(reqstr,"MONTH_12"+"_"+j));//17

						callstmt1.setString(1,branch);
						callstmt1.setString(2,advisor);
						callstmt1.setString(3,bstartdate);
						callstmt1.setString(4,m_username);	
						if (m_screen_name.equals("NEW")){
						callstmt1.setInt(5,1);	
						}
						else{
						callstmt1.setInt(5,2);	
						}
						
						callstmt1.setString(6,bug_amt1);
						callstmt1.setString(7,bug_amt2);
						callstmt1.setString(8,bug_amt3);
						callstmt1.setString(9,bug_amt4);
						callstmt1.setString(10,bug_amt5);
						callstmt1.setString(11,bug_amt6);
						callstmt1.setString(12,bug_amt7);
						callstmt1.setString(13,bug_amt8);
						callstmt1.setString(14,bug_amt9);
						callstmt1.setString(15,bug_amt10);
						callstmt1.setString(16,bug_amt11);
						callstmt1.setString(17,bug_amt12);
						callstmt1.execute();
						}
						
						callstmt1.close();		
				
			//============================================================================	
    	conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_RE_PRO_Collection_Officer_Target?chksql=main_page';");
			
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
