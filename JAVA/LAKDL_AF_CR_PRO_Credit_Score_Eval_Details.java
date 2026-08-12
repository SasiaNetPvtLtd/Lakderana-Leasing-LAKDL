//--
//SCREEN NAME	:SAVE PAYMENT DETAILS
//CREATED BY	:DELANJALI
//DATE/TIME		:
//NOTES				:




import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CR_PRO_Credit_Score_Eval_Details extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs;
	public String m_chksql;
	ServletOutputStream out = null;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
		  String m_username =  con_method.username;
		
			String m_pre_stage;
			String m_pre_stage1;
			String m_app_stage;		
			String m_close;
			String m_new_stage;
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			stmt = conn.createStatement ();
			String m_sort_column   = "APPLICATION_NO";	
			String m_order_by_type = "ASC";
							
			if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
		     m_sort_column = req.getParameter("sort_column");
			   m_order_by_type = req.getParameter("order_by_type");
			}
			String m_return_status=""; //added by nuwan de silva on 04-10-07
			String m_status="VERIFY1";
			
			m_status=req.getParameter("status");
			if(m_status==null){
			m_status="VERIFY1";
			}
					String m_screen_type="NEW";
				if(req.getParameter("screen_type")!=null){
				m_screen_type=req.getParameter("screen_type");
				}

      out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
				
			out.println("function befor_end(m_obj) {");
      out.println("   m_obj.focus();");
      out.println("}");
				
			out.println("function load_roll_value(m_val){");
			out.println("help_box.innerHTML=\"Credit - Credit Score Evaluation Details - \"+m_val"); 
			out.println("}");
				
			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			
			if(m_screen_type.equals("NEW")){
			out.println("load_screen_status(\"NEW\")"); 
			out.println("load_roll_value(\"New\");");

			}
			
			if(m_screen_type.equals("REVERSE")){
			out.println("load_screen_status(\"REVERSE\");"); 		
			out.println("load_roll_value(\"Reverse\");");
			}
			
			out.println("}	"); 
			
			
			
			
			
			
			

			


			out.println("function load_data(m_app_no,row,fin_no) {");
		//  out.println("	  m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_display_credit_score_enter?screen_type="+m_screen_type+"&application_no=\"+m_app_no"&finance_no=\"+fin_no;"); 
			out.println("	  m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_display_credit_score_enter?screen_type="+m_screen_type+"&application_no=\"+m_app_no+\"&finance_no=\"+fin_no;"); 
			out.println(" window.location.href=m_url;");
			out.println("}");
			
			out.println("function load_roll_out_value(m_val){");
			out.println("help_box.innerHTML=\"Credit - Credit Score Evaluation Details \";"); 
			out.println("}");

			out.println("function sort_data(m_sort_col) {");
			out.println("var m_bk=1");
			out.println("	 m_order_by_type = 'ASC'; ");  
			out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
			out.println("	   if('"+m_order_by_type+"'=='DESC'){");
			out.println("	      m_order_by_type = 'ASC'; ");  
			out.println("    }else{");
			out.println("       m_order_by_type = 'DESC'; ");
			out.println("    }");
			out.println("  }else{");
			out.println("    m_order_by_type = 'ASC'; ");
			out.println("  }");
			out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Credit_Score_Eval_Details?sort_column=\"+m_sort_col+\"&status="+m_status+"&order_by_type=\"+m_order_by_type;"); 
			out.println(" window.location.href=m_url;"); 
			out.println("}");
			
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function before_submit(){ "); 
			out.println("check_app();");
			out.println("if(b_flag==0){");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
 			out.println("		document.Form1.action=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_save_application_approval_details?scr=\"+document.Form1.hid_scr.value+\"&number=\"+document.Form1.hid_no.value+\"&actst1=\"+m_prev+\"&actst2=\"+m_app;");   
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("} ");
			out.println("} ");
			
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CR_display_credit_score_enter\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
 			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
					

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}");
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"NEW\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"REVERSE\"){");  
			out.println("reverse_window()");
			
			out.println("document.Form1.hid_status.value=\"Reverse\";"); 
			out.println("document.Form1.hid_save.value=\"Reverse\";"); 
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("document.Form1.hid_save.value=\"Deactivate\";");  
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";"); 
			out.println("document.Form1.hid_save.value=\"Reactivate\";");  
			out.println("}else if(m_val==\"VIEW\"){");  
			out.println("document.Form1.hid_status.value=\"ViewLetter\";");  
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("m_scr=m_val");
			out.println("}"); 
			
			out.println("function reverse_window() {");
			out.println("	window.location.href = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Credit_Score_Eval_Details?status=V-APP&screen_type=REVERSE\";"); 
			out.println("}");
				
			out.println("function new_window() {");
			out.println("	window.location.href = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Credit_Score_Eval_Details?status=VERIFY1&screen_type=NEW\";"); 
			out.println("}");

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("	window.location.href = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Credit_Score_Eval_Details?status='VERIFY1'\";"); 
			out.println("		}"); 
			out.println("}"); 
//__________________________________________________________________________________________________________________________________________________________________________________________________			
     
			out.println("</Script>");
			out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" onload=\"load_lock()\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<input  type='hidden' value=\"APPROVE\" name=\"SCREEN_NAME\"> "); 
      out.println("<input type=hidden name=\"ROW_ID\" ></td>");
			out.println("<input type=hidden name=\"hid_save\" value=\"Save\" ></td>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
		
			
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>"+header_name+"</td>"); 
			out.println("</tr>"); 
			out.println("<tr> "); 
			out.println("<td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td style='height: 327px'>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - Credit Score Evaluation Details</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> ");
			
			out.println("<tr>");
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name=New onMouseout='load_roll_out_value(\"New\");' onMouseOver='load_roll_value(\"New\");' onClick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name=New onMouseout='load_roll_out_value(\"Reverse\");' onMouseOver='load_roll_value(\"Reverse\");' onClick='load_screen_status(\"REVERSE\")' value=\"Reverse\"></td>");  

			out.println("<td width='6%'></td>");  
			
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  

			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  

			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
			out.println("<tr class=tr_input>");
			out.println("<td valign=top  width=100% Id=Follow_up> ");
				
				  
			out.println("<table class=table border='0' width='100%' >");
					
      out.println("<tr class=pdn_txtpos2 align='left'>");
			out.println("<td  width='20%' style= cursor:hand; title='Click here to sort by - Application No  '    onclick=sort_data('APPLICATION_NO') >Application No</td>");
      out.println("<td  width='20%' style= cursor:hand; title='Click here to sort by - Inquary No  '       onclick=sort_data('INQUARY_NO') >Inquary No</td>");
      out.println("<td  width='20%'  style= cursor:hand; title='Click here to sort by - Finance No  '            onclick=sort_data('FINANCE_NO') >Finance No</td>");
			out.println("<td  width='20%'  style= cursor:hand; title='Click here to sort by - Client Code  '            onclick=sort_data('CLIENT_CODE') >Client Code</td>");
			out.println("<td  width='15%' style= cursor:hand; title='Click here to sort by - Client Name  ' onclick=sort_data('FULL_NAME') >Client Name</td>");
			out.println("<td  width='5%'>&nbsp</td>");
			out.println("</tr>");
					 
           int j = 0;   
					 int i = 1;  
					

			rs = stmt.executeQuery("SELECT  A.APPLICATION_NO AS APPLICATION_NO,INQUARY_NO,NVL(FINANCE_NO,'-'),B.CLIENT_CODE,B.FULL_NAME FULL_NAME,A.APPLICATION_STATUS ,"+
			//out.println (" TEST  ");
			//out.println ("SELECT  A.APPLICATION_NO AS APPLICATION_NO,INQUARY_NO,NVL(FINANCE_NO,'-'),B.CLIENT_CODE,B.FULL_NAME FULL_NAME,A.APPLICATION_STATUS ,"+

			"NVL( (SELECT NVL(STATUS,'N')  FROM "+//ADDED BY NUWAN DE SILVA ON 04-10-07
			" "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
			" WHERE   TRUNC(ENT_DATE,'DD') =( "+
			" SELECT  MAX(ENT_DATE) "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL  "+
			" WHERE APPLICATION_NO=A.APPLICATION_NO)),'N' ) STATUS "+ 
			"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A , "+
			"(SELECT X.APPLICATION_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO "+
			"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X, "+m_schema_name+".AF_CO_MAS_CLIENT V "+
			"WHERE V.CLIENT_CODE     =X.CLIENT_CODE )B "+
			"WHERE A.APPLICATION_NO  =B.APPLICATION_NO  "+
			"AND A.APPLICATION_STATUS='"+m_status+"'   "+
			" AND DECODE("+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"'),'HO','-',"+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"')) LIKE  DECODE("+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"'),'HO','-',"+m_schema_name+".AF_CO_GET_USER_LOCATION(A.ENT_USER))||'%' "+  // added by udara on 18-06-2013
			" ORDER BY "+m_sort_column+" "+m_order_by_type+"");
						

            while(rs.next()){
							m_return_status=rs.getString(7);

							if(!m_return_status.equals("RET-VE-APP")){
							if(j>0 && j%2==1){
							out.println("<tr class=tr_input1 >");
							}
							else{
							out.println("<tr class=tr_input >");
							}
							}
							else{
							out.println("<tr bgcolor='#CCCC99' >");
							}
							
							
									
									
					out.println("<td width='20%' align='left' style= cursor:hand;cursor-color:blue onclick=show_application_detail_drill('"+rs.getString(1)+"')><u>"+rs.getString(1) +"</u></td>");
					out.println("<td width='20%' align='left' style= cursor:hand;cursor-color:blue onclick=show_inquiry_drill('"+rs.getString(2)+"')><u>"+rs.getString(2) +"</u></td>");
					out.println("<td width='20%' align='left'  style= cursor:hand;cursor-color:blue onclick=show_finance_detail_drill('"+rs.getString(3)+"')><u>"+rs.getString(3) +"</td>");
					out.println("<td width='20%' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(4)+"')><u>"+rs.getString(4) +"</u></td>");
					out.println("<td width='15%' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(4)+"')><u>"+rs.getString(5) +"</u></td>");
					//out.println("<td width=\"5%\"><input type=button name=\"view_"+j+"\" value=\"Evaluate\"   class=\"but_input\" onclick=load_data(\""+rs.getString(1)+"\",\""+j+"\") >");//Modified By nuwan De silva 23-05-07  //comment by Prabash on 13-07-2012
					out.println("<td width=\"5%\"><input type=button name=\"view_"+j+"\" value=\"Evaluate\"   class=\"but_input\" onclick=load_data(\""+rs.getString(1)+"\",\""+j+"\",\""+rs.getString(3)+"\") >");  //Modified Prabash on 13-07-2012
			            j=j+1;
					
									
									}				
//-------------------------------------------------------------------------------------------------------------------------------------------------------
									

				 out.println("</tr>");
				 out.println("</tr>");
									
				 out.println("<tr>");
				 out.println("<td><input type=\"hidden\" name=hid_no value="+j+"></td>");
				 out.println("</tr>");
				 out.println("</table>");
				 out.println("</tr>");
				 out.println("</table>");
				 out.println("</form>");
				 out.println("</body>");
					
				 out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				 out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					
				 out.println("</html>");
			
			//=========================================================================================================================			
  				
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
      //return null;
		}finally{
		  if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	    if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}
