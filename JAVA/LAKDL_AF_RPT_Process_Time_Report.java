
//--
//SCREEN NAME:ADVERTISEMENT OFFERS REPORT
//CREATED BY:CHANDANA
//DATE/TIME:30/03/2007
//NOTES:
//URL:https://dev-lakdl.sasianet.com:/myserver/servlet/LAKDL_AF_RPT_Process_Time_Report?chksql=MAIN&

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//Modified by Mahela on 10-04-2007

public class LAKDL_AF_RPT_Process_Time_Report extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt;
		public ResultSet rs,rs1;
		PreparedStatement pstmt;
		java.text.NumberFormat nf;

public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 

			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			
			
      LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			//SCREEN_METHODS m_sn_methods = new SCREEN_METHODS(); 

			String m_html_client_url=con_method.html_client_url.trim(); 
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			conn = con_method.met_user_validate(req); 
			stmt = conn.createStatement();
			
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_fschema_name=con_method.client_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			

			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 

			ServletOutputStream out = res.getOutputStream(); 


			String m_screen_type= req.getParameter("chksql");
			
			
      if(m_screen_type.trim().equals("main_page1")){	
			
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
			
			out.println("function help_update() {");
			out.println("m_help=\"1\";");
			out.println("    document.Form1.hid_help_type.value=\"99\";");
			out.println("    m_sql = \"m_help_TXT_APPLICATION_NO_3\";"); 
			out.println("    m_criteria = document.Form1.TXT_OFFER_NO.value+\"@\"+\"Y@\";"); 
							
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
			out.println("}");
			
			out.println("function help_update_value_assign_99() {");
			out.println("    document.Form1.TXT_OFFER_NO.value=oBj.valout[2];");
			out.println("}"); 
			
			
			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(IfCount==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
			out.println("		}"); 
			out.println("		if(IfCount==\"100\"){"); 
			out.println("		help_update_value_assign_100();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
	  	out.println("		}"); 

			out.println("	}"); //end next
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); //end prev
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); ///close
			out.println("	}	"); //
			out.println("}"); 
			out.println(""); 

			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RPT_Process_Time_Report?chksql=main_page1';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RPT_Process_Time_Report?chksql=main_page1';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 


			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_LAKDL_AF_MISF_display_payment_report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}");
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"  Advertisement Offers Report - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"  Advertisement Offers Report \";"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
			out.println("document.Form1.TXT_ASSET_ID.disabled=true;"); 
			out.println("document.Form1.TXT_ENGINE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CHASSIS_NO.disabled=true;"); 
			out.println("document.Form1.TXT_REG_NO.disabled=true;"); 
			out.println("document.Form1.TXT_REG_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_VEHICLE_NO.disabled=true;"); 
			out.println("}"); 
			out.println("else{");
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}");
			
			out.println("function load_data_frame(){ ");
			//out.println("alert(parent.frames[1].location); ");
			//out.println("parent.frames[1].location.replace(\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Advertisement_Offers_Report?chksql=MAIN&OFFER_NO=\"+document.Form1.TXT_OFFER_NO.value+\" \");  ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Process_Time_Report?chksql=MAIN&OFFER_NO=\"+document.Form1.TXT_OFFER_NO.value+\" \";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=100,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println(" } ");
				
				
			out.println("</Script>");
			
			out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >");
			out.println("<tr>");
			
			out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
			out.println("<td class=\"border_wht\" valign=\"top\"> ");
			out.println("<table class=table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
			out.println("<tr> ");
			out.println("<td height=\"30\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
			out.println("</tr>");
			out.println("<tr> ");
			out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td style=\"height: 327px\">");
						
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
			out.println("<tr>");
			out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Advertisement Offers Report</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
      out.println("</table>");
			out.println("</td>	");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
			out.println("</table>");
			out.println("<BR>");
			out.println("<table align='center' width='100%' class='table'>"); 
  		out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_OFFER_NO'  class=div_input>Application No </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_OFFER_NO' maxlength='15' size='15' onkeypress=\"\" onblur=\"\" onchange=\"\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_OFFER_NO' value=\"Help\" onClick=\"help_update()\" ></td>"); 
			out.println("<td width='10%'><input class='mainbut' type='button' name='BUT_LOAD_DATA' value=\"View\" onClick=\"load_data_frame()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>");
				
			
			
			
			out.println("</form>");
			out.println("</body>");
			out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
			out.println("</html>");

					
			}else	if(m_screen_type.equals("MAIN")){

      String m_app_no = req.getParameter("OFFER_NO");
			//m_chksql = req.getParameter(\"chksql\");
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Advertisement Offers Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("</script>"); 

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			
			if(m_app_no.equals("")){
			
			pstmt = conn.prepareStatement(" SELECT DISTINCT A.APPLICATION_NO, "+
			                              " NVL(DECODE(B.TRANSACTION_TYPE,'FINLEASE','Finance Lease','OPELEASE','Operating Lease','HIREPURCH','Hire Purchase','LOANS','Loans','HIRING','Hiring'),'-'), "+
																		" TO_CHAR(B.ENT_DATE,'DD-MON-YY'), "+
																		" TO_CHAR(B.ENT_DATE,'HH:MI:SS') "+
																		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
																		" WHERE A.APPLICATION_NO = B.APPLICATION_NO ");
																		
																		
      


			rs=pstmt.executeQuery(); 

			boolean more=rs.next();
			out.println("<DIV STYLE='{position:absolute; top:10; left:0 cursor: hand;}'>");
			out.println("<table align='center' width='1925' class='table' border=1>"); 
			while(more){
			
			String m_appli_no = rs.getString(1);
			
					
				String Stage1_no_of_days ="";
				String Stage1_time       ="";
        String Stage2_no_of_days ="";
				String Stage2_time       ="";
        String Stage3_no_of_days ="";
				String Stage3_time       ="";
        String Stage4_no_of_days ="";
				String Stage4_time       ="";
				String Stage5_no_of_days ="";
				String Stage5_time       ="";
				String Stage6_no_of_days ="";
				String Stage6_time       ="";
				String Stage7_no_of_days ="";
				String Stage7_time       ="";
				String Stage8_no_of_days ="";
				String Stage8_time       ="";
				String Stage9_no_of_days ="";
				String Stage9_time       ="";
				String Stage10_no_of_days ="";
				String Stage10_time       ="";
				String Stage11_no_of_days ="";
				String Stage11_time       ="";
				
				
				
				
						 rs1=stmt.executeQuery(" SELECT FLOOR((B.ENT_DATE - A.ENT_DATE)) STAGE1_DD, "+
							                     " TO_CHAR(MOD(FLOOR((B.ENT_DATE - A.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((B.ENT_DATE - A.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((B.ENT_DATE - A.ENT_DATE)*24*60*60),60 )) STAGE1_TIME "+
																	 " FROM "+
																	 " (SELECT ENT_DATE "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																	 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
																	 " STAGE = 'ENTER' ) A, "+
																	 " (SELECT ENT_DATE "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																	 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
																	 " STAGE = 'APPROVE1' ) B ");
						boolean more1=rs1.next();	
						
						if(more1){
						Stage1_no_of_days =rs1.getString(1);
				    Stage1_time       =rs1.getString(2);
						}
						
				
				
				 rs1=stmt.executeQuery(" SELECT FLOOR((C.ENT_DATE - B.ENT_DATE)) STAGE1_DD, "+
							                     " TO_CHAR(MOD(FLOOR((C.ENT_DATE - B.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((C.ENT_DATE - B.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((C.ENT_DATE - B.ENT_DATE)*24*60*60),60 )) STAGE1_TIME "+
																	 " FROM "+
																	 " (SELECT ENT_DATE "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																	 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
																	 " STAGE = 'APPROVE1' ) B, "+
																	 " (SELECT ENT_DATE "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																	 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
																	 " STAGE = 'V-APP' ) C ");
						 more1=rs1.next();	
						
						if(more1){
						Stage2_no_of_days =rs1.getString(1);
				    Stage2_time       =rs1.getString(2);
						}
				
				 
					
				rs1=stmt.executeQuery(" SELECT FLOOR((D.ENT_DATE - C.ENT_DATE)) STAGE1_DD, "+
							                     " TO_CHAR(MOD(FLOOR((D.ENT_DATE - C.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((D.ENT_DATE - C.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((D.ENT_DATE - C.ENT_DATE)*24*60*60),60 )) STAGE1_TIME "+
																	 " FROM "+
																	 " (SELECT MAX(ENT_DATE) ENT_DATE "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																	 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
																	 " STAGE = 'V-APP' ) C, "+
																	 " (SELECT MAX(ENT_DATE) ENT_DATE "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																	 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
																	 " STAGE = 'VERIFY-M' ) D ");
						 more1=rs1.next();	
						
						if(more1){
						Stage3_no_of_days =rs1.getString(1);
				    Stage3_time       =rs1.getString(2);
						}	
				
				
				
					rs1=stmt.executeQuery(" SELECT FLOOR((E.ENT_DATE - D.ENT_DATE)) STAGE1_DD, "+
							                     " TO_CHAR(MOD(FLOOR((E.ENT_DATE - D.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((E.ENT_DATE - D.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((E.ENT_DATE - D.ENT_DATE)*24*60*60),60 )) STAGE1_TIME "+
																	 " FROM "+
																	 " (SELECT ENT_DATE "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																	 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
																	 " STAGE = 'VERIFY-M' ) D, "+
																	 " (SELECT ENT_DATE "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																	 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
																	 " STAGE = 'VERIFY2' ) E ");
						 more1=rs1.next();	
						
						if(more1){
						Stage4_no_of_days =rs1.getString(1);
				    Stage4_time       =rs1.getString(2);
						}	
				
				
				
					rs1=stmt.executeQuery(" SELECT FLOOR((F.MOD_DATE - E.ENT_DATE)) STAGE1_DD, "+
							                     " TO_CHAR(MOD(FLOOR((F.MOD_DATE - E.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((F.MOD_DATE - E.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((F.MOD_DATE - E.ENT_DATE)*24*60*60),60 )) STAGE1_TIME "+
																	 " FROM "+
																	 " (SELECT ENT_DATE "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																	 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
																	 " STAGE = 'VERIFY2' ) E, "+
																	 " (SELECT MOD_DATE "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																	 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
																	 " FINANCE_NO IS NOT NULL ) F ");
																					
						 more1=rs1.next();	
						
						if(more1){
						Stage5_no_of_days =rs1.getString(1);
				    Stage5_time       =rs1.getString(2);
						}	
				
				
				    
		    rs1=stmt.executeQuery(" SELECT FLOOR((G.ENT_DATE - F.MOD_DATE)) STAGE1_DD, "+
				                      " TO_CHAR(MOD(FLOOR((G.ENT_DATE - F.MOD_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((G.ENT_DATE - F.MOD_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((G.ENT_DATE - F.MOD_DATE)*24*60*60),60 )) STAGE1_TIME "+
															" FROM "+
															" (SELECT MOD_DATE "+
															" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
															" WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
															" FINANCE_NO IS NOT NULL ) F, "+
															" (SELECT ENT_DATE "+
															" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
															" WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
															" ACTIVE_STATUS = 'VERIFY' ) G ");
				   
				
				 more1=rs1.next();	
						
						if(more1){
						Stage6_no_of_days =rs1.getString(1);
				    Stage6_time       =rs1.getString(2);
						}	
				
				
				
				
				
				 rs1=stmt.executeQuery(" SELECT FLOOR((H.ENT_DATE - G.ENT_DATE)) STAGE1_DD, "+
					                     " TO_CHAR(MOD(FLOOR((H.ENT_DATE - G.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((H.ENT_DATE - G.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((H.ENT_DATE - G.ENT_DATE)*24*60*60),60 )) STAGE1_TIME "+
															 " FROM "+
															 " (SELECT ENT_DATE "+
															 " FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
															 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
															 " ACTIVE_STATUS = 'VERIFY' ) G, "+
															 " (SELECT SUPPLIER_APPROVAL_DATE ENT_DATE "+
															 " FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
															 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
															 " APPROVAL_STATUS = 'Y' ) H ");
				 more1=rs1.next();	
						
						if(more1){
						Stage7_no_of_days =rs1.getString(1);
				    Stage7_time       =rs1.getString(2);
						}	
				
				
				
				
				rs1=stmt.executeQuery(" SELECT FLOOR((I.ENT_DATE - H.ENT_DATE)) STAGE1_DD, "+
				                      " TO_CHAR(MOD(FLOOR((I.ENT_DATE - H.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((I.ENT_DATE - H.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((I.ENT_DATE - H.ENT_DATE)*24*60*60),60 )) STAGE1_TIME "+
															" FROM "+
															" (SELECT SUPPLIER_APPROVAL_DATE ENT_DATE "+
															" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
															" WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
															" APPROVAL_STATUS = 'Y' ) H , "+
															" (SELECT MAX(F.ENT_DATE) ENT_DATE "+
															" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
															" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
															" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E, "+
															" "+m_schema_name+".AF_CR_PRO_APPROVALDETAILS F "+
															" WHERE UPPER(C.APPLICATION_STATUS) =UPPER('ACTIVATED') "+
															" AND B.REF_NO=D.INVOICE_NO "+
															"	AND D.APPLICATION_NO=C.APPLICATION_NO "+
															" AND E.SUS_REF_NO=B.SUS_REF_NO "+
															" AND F.REF_NO = E.PAYMENT_NO "+
															" AND C.APPLICATION_NO ='"+m_appli_no+"' "+
															" AND F.APP_STATUS='RE-APP' "+
															" GROUP BY    C.APPLICATION_NO ,F.APP_STATUS )I ");  
				
				more1=rs1.next();	
						
						if(more1){
						Stage8_no_of_days =rs1.getString(1);
				    Stage8_time       =rs1.getString(2);
						}	
				
				    
						
				rs1=stmt.executeQuery(" SELECT FLOOR((I.ENT_DATE - H.ENT_DATE)) STAGE1_DD, "+
				                      " TO_CHAR(MOD(FLOOR((I.ENT_DATE - H.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((I.ENT_DATE - H.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((I.ENT_DATE - H.ENT_DATE)*24*60*60),60 )) STAGE1_TIME "+
															" FROM "+
															" (SELECT SUPPLIER_APPROVAL_DATE ENT_DATE "+
															" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
															" WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
															" ACTIVE_STATUS = 'VERIFY' ) H , "+
															" (SELECT MAX(F.ENT_DATE) ENT_DATE "+
															" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
															" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
															" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E, "+
															" "+m_schema_name+".AF_CR_PRO_APPROVALDETAILS F "+
															" WHERE UPPER(C.APPLICATION_STATUS) =UPPER('ACTIVATED') "+
															" AND B.REF_NO=D.INVOICE_NO "+
															" AND D.APPLICATION_NO=C.APPLICATION_NO "+
															" AND E.SUS_REF_NO=B.SUS_REF_NO "+
															" AND F.REF_NO = E.PAYMENT_NO "+
															" AND C.APPLICATION_NO ='"+m_appli_no+"' "+
															" AND F.APP_STATUS='RE-APP' "+
															" GROUP BY    C.APPLICATION_NO ,F.APP_STATUS )I ");				
						
							more1=rs1.next();	
						
						if(more1){
						Stage9_no_of_days =rs1.getString(1);
				    Stage9_time       =rs1.getString(2);
						}	
						
						
						
						
						rs1=stmt.executeQuery(" SELECT FLOOR((I.ENT_DATE - H.ENT_DATE)) STAGE1_DD, "+
						                      " TO_CHAR(MOD(FLOOR((I.ENT_DATE - H.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((I.ENT_DATE - H.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((I.ENT_DATE - H.ENT_DATE)*24*60*60),60 )) STAGE1_TIME "+
																	" FROM "+
																	" (SELECT MAX(F.ENT_DATE) ENT_DATE "+
																	" FROM  	"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
																	" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
																	" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E, "+
																	" "+m_schema_name+".AF_CR_PRO_APPROVALDETAILS F "+
																	" WHERE UPPER(C.APPLICATION_STATUS) =UPPER('ACTIVATED') "+
																	" AND B.REF_NO=D.INVOICE_NO "+
																	" AND D.APPLICATION_NO=C.APPLICATION_NO "+
																	" AND E.SUS_REF_NO=B.SUS_REF_NO "+
																	" AND F.REF_NO = E.PAYMENT_NO "+
																	" AND C.APPLICATION_NO ='"+m_appli_no+"' "+
																	" AND F.APP_STATUS='RE-APP' "+
																	" GROUP BY    C.APPLICATION_NO ,F.APP_STATUS ) H , "+
																	"(SELECT MAX(F.ENT_DATE) ENT_DATE "+
																	" FROM  	"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
																	" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
																	" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E, "+
																	" "+m_schema_name+".AF_CR_PRO_APPROVALDETAILS F "+
																	" WHERE UPPER(C.APPLICATION_STATUS) =UPPER('ACTIVATED') "+
																	" AND B.REF_NO=D.INVOICE_NO "+
																	" AND D.APPLICATION_NO=C.APPLICATION_NO "+
																	" AND E.SUS_REF_NO=B.SUS_REF_NO "+
																	" AND F.REF_NO = E.PAYMENT_NO "+
																	" AND C.APPLICATION_NO ='"+m_appli_no+"' "+
																	" AND F.APP_STATUS='APPRO1' "+
																	" GROUP BY    C.APPLICATION_NO ,F.APP_STATUS )I ");              
						
						
							more1=rs1.next();	
						
						if(more1){
						Stage10_no_of_days =rs1.getString(1);
				    Stage10_time       =rs1.getString(2);
						}	
						
						
						
						
				rs1=stmt.executeQuery(" SELECT FLOOR((I.ENT_DATE - H.ENT_DATE)) STAGE1_DD, "+
						                  " TO_CHAR(MOD(FLOOR((I.ENT_DATE - H.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((I.ENT_DATE - H.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((I.ENT_DATE - H.ENT_DATE)*24*60*60),60 )) STAGE1_TIME "+
															" FROM "+
															" (SELECT MAX(F.ENT_DATE) ENT_DATE "+
															" FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
															" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
															" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E, "+
															" "+m_schema_name+".AF_CR_PRO_APPROVALDETAILS F "+
															" WHERE UPPER(C.APPLICATION_STATUS) =UPPER('ACTIVATED') "+
															" AND B.REF_NO=D.INVOICE_NO "+
															" AND D.APPLICATION_NO=C.APPLICATION_NO "+
															" AND E.SUS_REF_NO=B.SUS_REF_NO "+
															" AND F.REF_NO = E.PAYMENT_NO "+
															" AND C.APPLICATION_NO ='"+m_appli_no+"' "+
															" AND F.APP_STATUS='APPRO1' "+
															" GROUP BY    C.APPLICATION_NO ,F.APP_STATUS ) H , "+
															" (SELECT MAX(F.ENT_DATE) ENT_DATE "+
															" FROM  	"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
															" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
															" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E, "+
															" "+m_schema_name+".AF_CR_PRO_APPROVALDETAILS F "+
															" WHERE UPPER(C.APPLICATION_STATUS) =UPPER('ACTIVATED') "+
															" AND B.REF_NO=D.INVOICE_NO "+
															" AND D.APPLICATION_NO=C.APPLICATION_NO "+
															" AND E.SUS_REF_NO=B.SUS_REF_NO "+
															" AND F.REF_NO = E.PAYMENT_NO "+
															" AND C.APPLICATION_NO ='"+m_appli_no+"' "+
															" AND F.APP_STATUS='APPRO2' "+
															" GROUP BY    C.APPLICATION_NO ,F.APP_STATUS )I ");		
						
							more1=rs1.next();	
						
						if(more1){
						Stage11_no_of_days =rs1.getString(1);
				    Stage11_time       =rs1.getString(2);
						}	
						
				
			
						
						out.println("<tr >"); 
						out.println("<TD width='80' class='txt_report_data' align='center' style=cursor:hand;cursor-color:blue onclick=show_application_detail_drill('"+rs.getString(1)+"')><U>"+rs.getString(1)+"</U></TD>");
						out.println("<TD width='130' class='txt_report_data' >"+rs.getString(2)+"</TD>");
						out.println("<TD width='80' class='txt_report_data' align='center'>"+rs.getString(3)+"</TD>");
						out.println("<TD width='110' class='txt_report_data' align='center'>"+rs.getString(4)+"</TD>");
							
						
						if(Stage1_time.equals("")){
						out.println("<TD width='100' class='txt_report_data' align='center'>-</TD>");
						}else{
						out.println("<TD width='100' class='txt_report_data' align='center'>"+Stage1_no_of_days+"Days and "+Stage1_time+"</TD>");
						}
						if(Stage2_time.equals("")){
						out.println("<TD width='100' class='txt_report_data' align='center'>-</TD>");
						}else{
						out.println("<TD width='100' class='txt_report_data' align='center'>"+Stage2_no_of_days+"Days and "+Stage2_time+"</TD>");
						}
						if(Stage3_time.equals("::")){
						out.println("<TD width='100' class='txt_report_data' align='center'>-</TD>");
						}else{
						out.println("<TD width='100' class='txt_report_data' align='center'>"+Stage3_no_of_days+"Days and "+Stage3_time+"</TD>");
						}
						if(Stage4_time.equals("")){
						out.println("<TD width='100' class='txt_report_data' align='center'>-</TD>");
						}else{
						out.println("<TD width='100' class='txt_report_data' align='center'>"+Stage4_no_of_days+"Days and "+Stage4_time+"</TD>");
						}
						if(Stage5_time.equals("")){
						out.println("<TD width='100' class='txt_report_data' align='center'>-</TD>");
						}else{
						out.println("<TD width='100' class='txt_report_data' align='center'>"+Stage5_no_of_days+"Days and "+Stage5_time+"</TD>");
						}
						if(Stage6_time.equals("")){
						out.println("<TD width='100' class='txt_report_data' align='center'>-</TD>");
						}else{
						out.println("<TD width='100' class='txt_report_data' align='center'>"+Stage6_no_of_days+"Days and "+Stage6_time+"</TD>");
						}
						if(Stage7_time.equals("")){
						out.println("<TD width='100' class='txt_report_data' align='center'>-</TD>");
						}else{
						out.println("<TD width='100' class='txt_report_data' align='center'>"+Stage7_no_of_days+"Days and "+Stage7_time+"</TD>");
						}
						if(Stage8_time.equals("")){
						out.println("<TD width='100' class='txt_report_data' align='center'>-</TD>");
						}else{
						out.println("<TD width='100' class='txt_report_data' align='center'>"+Stage8_no_of_days+"Days and "+Stage8_time+"</TD>");
						}
						if((Stage9_time.equals(""))||(Stage9_time.equals("::"))){
						out.println("<TD width='100' class='txt_report_data' align='center'>-</TD>");
						}else{
						out.println("<TD width='100' class='txt_report_data' align='center'>"+Stage9_no_of_days+"Days and "+Stage9_time+"</TD>");
						}
						if(Stage10_time.equals("")){
						out.println("<TD width='100' class='txt_report_data' align='center'>-</TD>");
						}else{
						out.println("<TD width='100' class='txt_report_data' align='center'>"+Stage10_no_of_days+"Days and "+Stage10_time+"</TD>");
						}
						if(Stage11_time.equals("")){
						out.println("<TD width='100' class='txt_report_data' align='center'>-</TD>");
						}else{
						out.println("<TD width='100' class='txt_report_data' align='center'>"+Stage11_no_of_days+"Days and "+Stage11_time+"</TD>");
						}
						out.println("</tr >"); 
						more=rs.next(); 
			} 

			out.println("</table>");
			out.println("</DIV>");
			
			out.println("<DIV STYLE='position: absolute; top: 0; left: 0; width:0; height: 0'></DIV>");
   		out.println("<DIV STYLE='position: absolute; top: 0; left: 3; width : 1925; height: 15'>");
			out.println("<table align='center' width='1925' class='table' border=1>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='80' class='txt_report_column'>Application NO</td>"); 
			out.println("<td width='130' class='txt_report_column'>Transaction Type</td>"); 
			out.println("<td width='80' class='txt_report_column'>Entered Date</td>"); 
			out.println("<td width='110' class='txt_report_column'>Entered Time</td>"); 
			out.println("<td width='100' class='txt_report_column'>Time for Stage1 (Application Enter Level - Credit Verification)</td>"); 
			out.println("<td width='100' class='txt_report_column'>Time for Stage2 (Credit Verification - Credit Score Evaluation)</td>"); 
			out.println("<td width='100' class='txt_report_column'>Time for Stage3 (Credit Score Evaluation - Credit Approval 1)</td>"); 
			out.println("<td width='100' class='txt_report_column'>Time for Stage4 (Credit Approval 1 - Credit Approval 2)</td>");
			out.println("<td width='100' class='txt_report_column'>Time for Stage5 (Credit Approval 2 -  Enter Finance No)</td>");
			out.println("<td width='100' class='txt_report_column'>Time for Stage6 (Enter Finance No - Purchase Order Enter Stage)</td>");
			out.println("<td width='100' class='txt_report_column'>Time for Stage7 (Purchase Order Enter Stage - Purchase Order Approval)</td>");
			out.println("<td width='100' class='txt_report_column'>Time for Stage8 (Purchase Order Approval - Payment Requsition - New)</td>");
			out.println("<td width='100' class='txt_report_column'>Time for Stage8 (Purchase Order Enter Stage - Payment Requsition - New)</td>");
			out.println("<td width='100' class='txt_report_column'>Time for Stage9 (Payment Requsition - New - Payment Requsition -Approve 1)</td>");
			out.println("<td width='100' class='txt_report_column'>Time for Stage10 (Payment Requsition -Approve 1 - Payment Requsition -Approve 2)</td>");
			out.println("</tr >"); 
			out.println("</table>");  
			out.println("</div>"); 
			out.println("<DIV STYLE='position: absolute; top: 0; left: 0; width: 0; height: 0'></DIV>");
			out.println("<br>"); 
	
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr><td width='100%' class='note'></td></tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("</body>"); 
			out.println("</html>"); 
			rs.close();
			pstmt.close();
			conn.close();
			out.flush();
			out.close();
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!//
	
			}else{
				
																			
			pstmt = conn.prepareStatement(" SELECT DISTINCT A.APPLICATION_NO, "+
			                              " NVL(DECODE(B.TRANSACTION_TYPE,'FINLEASE','Finance Lease','OPELEASE','Operating Lease','HIREPURCH','Hire Purchase','LOANS','Loans','HIRING','Hiring'),'-'), "+
																		" TO_CHAR(B.ENT_DATE,'DD-MON-YY'), "+
																		" TO_CHAR(B.ENT_DATE,'HH:MI:SS') "+
																		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
																		" WHERE A.APPLICATION_NO = B.APPLICATION_NO AND "+
																		" A.APPLICATION_NO = '"+m_app_no+"' ");															
																		
																		
																		
			rs=pstmt.executeQuery(); 

			boolean more=rs.next();
		
		
			out.println("<table align='center' width='1925' class='table' border=1>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='80' class='txt_report_column'>Application NO</td>"); 
			out.println("<td width='130' class='txt_report_column'>Transaction Type</td>"); 
			out.println("<td width='80' class='txt_report_column'>Entered Date</td>"); 
			out.println("<td width='110' class='txt_report_column'>Entered Time</td>"); 
			out.println("<td width='100' class='txt_report_column'>Time for Stage1 (Application Enter Level - Credit Verification)</td>"); 
			out.println("<td width='100' class='txt_report_column'>Time for Stage2 (Credit Verification - Credit Score Evaluation)</td>"); 
			out.println("<td width='100' class='txt_report_column'>Time for Stage3 (Credit Score Evaluation - Credit Approval 1)</td>"); 
			out.println("<td width='100' class='txt_report_column'>Time for Stage4 (Credit Approval 1 - Credit Approval 2)</td>");
			out.println("<td width='100' class='txt_report_column'>Time for Stage5 (Credit Approval 2 -  Enter Finance No)</td>");
			out.println("<td width='100' class='txt_report_column'>Time for Stage6 (Enter Finance No - Purchase Order Enter Stage)</td>");
			out.println("<td width='100' class='txt_report_column'>Time for Stage7 (Purchase Order Enter Stage - Purchase Order Approval)</td>");
			out.println("<td width='100' class='txt_report_column'>Time for Stage8 (Purchase Order Approval - Payment Requsition - New)</td>");
			out.println("<td width='100' class='txt_report_column'>Time for Stage8 (Purchase Order Enter Stage - Payment Requsition - New)</td>");
			out.println("<td width='100' class='txt_report_column'>Time for Stage9 (Payment Requsition - New - Payment Requsition -Approve 1)</td>");
			out.println("<td width='100' class='txt_report_column'>Time for Stage10 (Payment Requsition -Approve 1 - Payment Requsition -Approve 2)</td>");
			out.println("</tr >"); 
			out.println("</table>"); 
			
			out.println("<table align='center' width='1925' class='table' border=1>"); 
			while(more){
			
		String m_appli_no = rs.getString(1);
			
			
			
				String Stage1_no_of_days ="";
				String Stage1_time       ="";
        String Stage2_no_of_days ="";
				String Stage2_time       ="";
        String Stage3_no_of_days ="";
				String Stage3_time       ="";
        String Stage4_no_of_days ="";
				String Stage4_time       ="";
				String Stage5_no_of_days ="";
				String Stage5_time       ="";
				String Stage6_no_of_days ="";
				String Stage6_time       ="";
				String Stage7_no_of_days ="";
				String Stage7_time       ="";
				String Stage8_no_of_days ="";
				String Stage8_time       ="";
				String Stage9_no_of_days ="";
				String Stage9_time       ="";
				String Stage10_no_of_days ="";
				String Stage10_time       ="";
				String Stage11_no_of_days ="";
				String Stage11_time       ="";
				
				
				
				
						 rs1=stmt.executeQuery(" SELECT FLOOR((B.ENT_DATE - A.ENT_DATE)) STAGE1_DD, "+
							                     " TO_CHAR(MOD(FLOOR((B.ENT_DATE - A.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((B.ENT_DATE - A.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((B.ENT_DATE - A.ENT_DATE)*24*60*60),60 )) STAGE1_TIME "+
																	 " FROM "+
																	 " (SELECT ENT_DATE "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																	 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
																	 " STAGE = 'ENTER' ) A, "+
																	 " (SELECT ENT_DATE "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																	 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
																	 " STAGE = 'APPROVE1' ) B ");
						boolean more1=rs1.next();	
						
						if(more1){
						Stage1_no_of_days =rs1.getString(1);
				    Stage1_time       =rs1.getString(2);
						}
						
				
				
				 rs1=stmt.executeQuery(" SELECT FLOOR((C.ENT_DATE - B.ENT_DATE)) STAGE1_DD, "+
							                     " TO_CHAR(MOD(FLOOR((C.ENT_DATE - B.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((C.ENT_DATE - B.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((C.ENT_DATE - B.ENT_DATE)*24*60*60),60 )) STAGE1_TIME "+
																	 " FROM "+
																	 " (SELECT ENT_DATE "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																	 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
																	 " STAGE = 'APPROVE1' ) B, "+
																	 " (SELECT ENT_DATE "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																	 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
																	 " STAGE = 'V-APP' ) C ");
						 more1=rs1.next();	
						
						if(more1){
						Stage2_no_of_days =rs1.getString(1);
				    Stage2_time       =rs1.getString(2);
						}
				
				 
					
				rs1=stmt.executeQuery(" SELECT FLOOR((D.ENT_DATE - C.ENT_DATE)) STAGE1_DD, "+
							                     " TO_CHAR(MOD(FLOOR((D.ENT_DATE - C.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((D.ENT_DATE - C.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((D.ENT_DATE - C.ENT_DATE)*24*60*60),60 )) STAGE1_TIME "+
																	 " FROM "+
																	 " (SELECT MAX(ENT_DATE) ENT_DATE "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																	 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
																	 " STAGE = 'V-APP' ) C, "+
																	 " (SELECT MAX(ENT_DATE) ENT_DATE "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																	 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
																	 " STAGE = 'VERIFY-M' ) D ");
						 more1=rs1.next();	
						
						if(more1){
						Stage3_no_of_days =rs1.getString(1);
				    Stage3_time       =rs1.getString(2);
						}	
				
				
				
					rs1=stmt.executeQuery(" SELECT FLOOR((E.ENT_DATE - D.ENT_DATE)) STAGE1_DD, "+
							                     " TO_CHAR(MOD(FLOOR((E.ENT_DATE - D.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((E.ENT_DATE - D.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((E.ENT_DATE - D.ENT_DATE)*24*60*60),60 )) STAGE1_TIME "+
																	 " FROM "+
																	 " (SELECT ENT_DATE "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																	 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
																	 " STAGE = 'VERIFY-M' ) D, "+
																	 " (SELECT ENT_DATE "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																	 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
																	 " STAGE = 'VERIFY2' ) E ");
						 more1=rs1.next();	
						
						if(more1){
						Stage4_no_of_days =rs1.getString(1);
				    Stage4_time       =rs1.getString(2);
						}	
				
				
				
					rs1=stmt.executeQuery(" SELECT FLOOR((F.MOD_DATE - E.ENT_DATE)) STAGE1_DD, "+
							                     " TO_CHAR(MOD(FLOOR((F.MOD_DATE - E.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((F.MOD_DATE - E.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((F.MOD_DATE - E.ENT_DATE)*24*60*60),60 )) STAGE1_TIME "+
																	 " FROM "+
																	 " (SELECT ENT_DATE "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																	 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
																	 " STAGE = 'VERIFY2' ) E, "+
																	 " (SELECT MOD_DATE "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																	 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
																	 " FINANCE_NO IS NOT NULL ) F ");
																					
						 more1=rs1.next();	
						
						if(more1){
						Stage5_no_of_days =rs1.getString(1);
				    Stage5_time       =rs1.getString(2);
						}	
				
				
				    
		    rs1=stmt.executeQuery(" SELECT FLOOR((G.ENT_DATE - F.MOD_DATE)) STAGE1_DD, "+
				                      " TO_CHAR(MOD(FLOOR((G.ENT_DATE - F.MOD_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((G.ENT_DATE - F.MOD_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((G.ENT_DATE - F.MOD_DATE)*24*60*60),60 )) STAGE1_TIME "+
															" FROM "+
															" (SELECT MOD_DATE "+
															" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
															" WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
															" FINANCE_NO IS NOT NULL ) F, "+
															" (SELECT ENT_DATE "+
															" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
															" WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
															" ACTIVE_STATUS = 'VERIFY' ) G ");
				   
				
				 more1=rs1.next();	
						
						if(more1){
						Stage6_no_of_days =rs1.getString(1);
				    Stage6_time       =rs1.getString(2);
						}	
				
				
				
				
				
				 rs1=stmt.executeQuery(" SELECT FLOOR((H.ENT_DATE - G.ENT_DATE)) STAGE1_DD, "+
					                     " TO_CHAR(MOD(FLOOR((H.ENT_DATE - G.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((H.ENT_DATE - G.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((H.ENT_DATE - G.ENT_DATE)*24*60*60),60 )) STAGE1_TIME "+
															 " FROM "+
															 " (SELECT ENT_DATE "+
															 " FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
															 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
															 " ACTIVE_STATUS = 'VERIFY' ) G, "+
															 " (SELECT SUPPLIER_APPROVAL_DATE ENT_DATE "+
															 " FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
															 " WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
															 " APPROVAL_STATUS = 'Y' ) H ");
				 more1=rs1.next();	
						
						if(more1){
						Stage7_no_of_days =rs1.getString(1);
				    Stage7_time       =rs1.getString(2);
						}	
				
				
				
				
				rs1=stmt.executeQuery(" SELECT FLOOR((I.ENT_DATE - H.ENT_DATE)) STAGE1_DD, "+
				                      " TO_CHAR(MOD(FLOOR((I.ENT_DATE - H.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((I.ENT_DATE - H.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((I.ENT_DATE - H.ENT_DATE)*24*60*60),60 )) STAGE1_TIME "+
															" FROM "+
															" (SELECT SUPPLIER_APPROVAL_DATE ENT_DATE "+
															" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
															" WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
															" APPROVAL_STATUS = 'Y' ) H , "+
															" (SELECT MAX(F.ENT_DATE) ENT_DATE "+
															" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
															" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
															" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E, "+
															" "+m_schema_name+".AF_CR_PRO_APPROVALDETAILS F "+
															" WHERE UPPER(C.APPLICATION_STATUS) =UPPER('ACTIVATED') "+
															" AND B.REF_NO=D.INVOICE_NO "+
															"	AND D.APPLICATION_NO=C.APPLICATION_NO "+
															" AND E.SUS_REF_NO=B.SUS_REF_NO "+
															" AND F.REF_NO = E.PAYMENT_NO "+
															" AND C.APPLICATION_NO ='"+m_appli_no+"' "+
															" AND F.APP_STATUS='RE-APP' "+
															" GROUP BY    C.APPLICATION_NO ,F.APP_STATUS )I ");  
				
				more1=rs1.next();	
						
						if(more1){
						Stage8_no_of_days =rs1.getString(1);
				    Stage8_time       =rs1.getString(2);
						}	
				
				    
						
				rs1=stmt.executeQuery(" SELECT FLOOR((I.ENT_DATE - H.ENT_DATE)) STAGE1_DD, "+
				                      " TO_CHAR(MOD(FLOOR((I.ENT_DATE - H.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((I.ENT_DATE - H.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((I.ENT_DATE - H.ENT_DATE)*24*60*60),60 )) STAGE1_TIME "+
															" FROM "+
															" (SELECT SUPPLIER_APPROVAL_DATE ENT_DATE "+
															" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
															" WHERE APPLICATION_NO='"+m_appli_no+"' AND "+
															" ACTIVE_STATUS = 'VERIFY' ) H , "+
															" (SELECT MAX(F.ENT_DATE) ENT_DATE "+
															" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
															" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
															" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E, "+
															" "+m_schema_name+".AF_CR_PRO_APPROVALDETAILS F "+
															" WHERE UPPER(C.APPLICATION_STATUS) =UPPER('ACTIVATED') "+
															" AND B.REF_NO=D.INVOICE_NO "+
															" AND D.APPLICATION_NO=C.APPLICATION_NO "+
															" AND E.SUS_REF_NO=B.SUS_REF_NO "+
															" AND F.REF_NO = E.PAYMENT_NO "+
															" AND C.APPLICATION_NO ='"+m_appli_no+"' "+
															" AND F.APP_STATUS='RE-APP' "+
															" GROUP BY    C.APPLICATION_NO ,F.APP_STATUS )I ");				
						
							more1=rs1.next();	
						
						if(more1){
						Stage9_no_of_days =rs1.getString(1);
				    Stage9_time       =rs1.getString(2);
						}	
						
						
						
						
						rs1=stmt.executeQuery(" SELECT FLOOR((I.ENT_DATE - H.ENT_DATE)) STAGE1_DD, "+
						                      " TO_CHAR(MOD(FLOOR((I.ENT_DATE - H.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((I.ENT_DATE - H.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((I.ENT_DATE - H.ENT_DATE)*24*60*60),60 )) STAGE1_TIME "+
																	" FROM "+
																	" (SELECT MAX(F.ENT_DATE) ENT_DATE "+
																	" FROM  	"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
																	" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
																	" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E, "+
																	" "+m_schema_name+".AF_CR_PRO_APPROVALDETAILS F "+
																	" WHERE UPPER(C.APPLICATION_STATUS) =UPPER('ACTIVATED') "+
																	" AND B.REF_NO=D.INVOICE_NO "+
																	" AND D.APPLICATION_NO=C.APPLICATION_NO "+
																	" AND E.SUS_REF_NO=B.SUS_REF_NO "+
																	" AND F.REF_NO = E.PAYMENT_NO "+
																	" AND C.APPLICATION_NO ='"+m_appli_no+"' "+
																	" AND F.APP_STATUS='RE-APP' "+
																	" GROUP BY    C.APPLICATION_NO ,F.APP_STATUS ) H , "+
																	"(SELECT MAX(F.ENT_DATE) ENT_DATE "+
																	" FROM  	"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
																	" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
																	" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E, "+
																	" "+m_schema_name+".AF_CR_PRO_APPROVALDETAILS F "+
																	" WHERE UPPER(C.APPLICATION_STATUS) =UPPER('ACTIVATED') "+
																	" AND B.REF_NO=D.INVOICE_NO "+
																	" AND D.APPLICATION_NO=C.APPLICATION_NO "+
																	" AND E.SUS_REF_NO=B.SUS_REF_NO "+
																	" AND F.REF_NO = E.PAYMENT_NO "+
																	" AND C.APPLICATION_NO ='"+m_appli_no+"' "+
																	" AND F.APP_STATUS='APPRO1' "+
																	" GROUP BY    C.APPLICATION_NO ,F.APP_STATUS )I ");              
						
						
							more1=rs1.next();	
						
						if(more1){
						Stage10_no_of_days =rs1.getString(1);
				    Stage10_time       =rs1.getString(2);
						}	
						
						
						
						
				rs1=stmt.executeQuery(" SELECT FLOOR((I.ENT_DATE - H.ENT_DATE)) STAGE1_DD, "+
						                  " TO_CHAR(MOD(FLOOR((I.ENT_DATE - H.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((I.ENT_DATE - H.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((I.ENT_DATE - H.ENT_DATE)*24*60*60),60 )) STAGE1_TIME "+
															" FROM "+
															" (SELECT MAX(F.ENT_DATE) ENT_DATE "+
															" FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
															" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
															" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E, "+
															" "+m_schema_name+".AF_CR_PRO_APPROVALDETAILS F "+
															" WHERE UPPER(C.APPLICATION_STATUS) =UPPER('ACTIVATED') "+
															" AND B.REF_NO=D.INVOICE_NO "+
															" AND D.APPLICATION_NO=C.APPLICATION_NO "+
															" AND E.SUS_REF_NO=B.SUS_REF_NO "+
															" AND F.REF_NO = E.PAYMENT_NO "+
															" AND C.APPLICATION_NO ='"+m_appli_no+"' "+
															" AND F.APP_STATUS='APPRO1' "+
															" GROUP BY    C.APPLICATION_NO ,F.APP_STATUS ) H , "+
															" (SELECT MAX(F.ENT_DATE) ENT_DATE "+
															" FROM  	"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
															" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
															" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E, "+
															" "+m_schema_name+".AF_CR_PRO_APPROVALDETAILS F "+
															" WHERE UPPER(C.APPLICATION_STATUS) =UPPER('ACTIVATED') "+
															" AND B.REF_NO=D.INVOICE_NO "+
															" AND D.APPLICATION_NO=C.APPLICATION_NO "+
															" AND E.SUS_REF_NO=B.SUS_REF_NO "+
															" AND F.REF_NO = E.PAYMENT_NO "+
															" AND C.APPLICATION_NO ='"+m_appli_no+"' "+
															" AND F.APP_STATUS='APPRO2' "+
															" GROUP BY    C.APPLICATION_NO ,F.APP_STATUS )I ");		
						
							more1=rs1.next();	
						
						if(more1){
						Stage11_no_of_days =rs1.getString(1);
				    Stage11_time       =rs1.getString(2);
						}	
						
				
			
						
						out.println("<tr >"); 
						out.println("<TD width='80' class='txt_report_data' align='center' style=cursor:hand;cursor-color:blue onclick=show_application_detail_drill('"+rs.getString(1)+"')><U>"+rs.getString(1)+"</U></TD>");
						out.println("<TD width='130' class='txt_report_data' >"+rs.getString(2)+"</TD>");
						out.println("<TD width='80' class='txt_report_data' align='center'>"+rs.getString(3)+"</TD>");
						out.println("<TD width='110' class='txt_report_data' align='center'>"+rs.getString(4)+"</TD>");
							
						
						if(Stage1_time.equals("")){
						out.println("<TD width='100' class='txt_report_data' align='center'>-</TD>");
						}else{
						out.println("<TD width='100' class='txt_report_data' align='center'>"+Stage1_no_of_days+"Days and "+Stage1_time+"</TD>");
						}
						if(Stage2_time.equals("")){
						out.println("<TD width='100' class='txt_report_data' align='center'>-</TD>");
						}else{
						out.println("<TD width='100' class='txt_report_data' align='center'>"+Stage2_no_of_days+"Days and "+Stage2_time+"</TD>");
						}
						if(Stage3_time.equals("::")){
						out.println("<TD width='100' class='txt_report_data' align='center'>-</TD>");
						}else{
						out.println("<TD width='100' class='txt_report_data' align='center'>"+Stage3_no_of_days+"Days and "+Stage3_time+"</TD>");
						}
						if(Stage4_time.equals("")){
						out.println("<TD width='100' class='txt_report_data' align='center'>-</TD>");
						}else{
						out.println("<TD width='100' class='txt_report_data' align='center'>"+Stage4_no_of_days+"Days and "+Stage4_time+"</TD>");
						}
						if(Stage5_time.equals("")){
						out.println("<TD width='100' class='txt_report_data' align='center'>-</TD>");
						}else{
						out.println("<TD width='100' class='txt_report_data' align='center'>"+Stage5_no_of_days+"Days and "+Stage5_time+"</TD>");
						}
						if(Stage6_time.equals("")){
						out.println("<TD width='100' class='txt_report_data' align='center'>-</TD>");
						}else{
						out.println("<TD width='100' class='txt_report_data' align='center'>"+Stage6_no_of_days+"Days and "+Stage6_time+"</TD>");
						}
						if(Stage7_time.equals("")){
						out.println("<TD width='100' class='txt_report_data' align='center'>-</TD>");
						}else{
						out.println("<TD width='100' class='txt_report_data' align='center'>"+Stage7_no_of_days+"Days and "+Stage7_time+"</TD>");
						}
						if(Stage8_time.equals("")){
						out.println("<TD width='100' class='txt_report_data' align='center'>-</TD>");
						}else{
						out.println("<TD width='100' class='txt_report_data' align='center'>"+Stage8_no_of_days+"Days and "+Stage8_time+"</TD>");
						}
						if((Stage9_time.equals(""))||(Stage9_time.equals("::"))){
						out.println("<TD width='100' class='txt_report_data' align='center'>-</TD>");
						}else{
						out.println("<TD width='100' class='txt_report_data' align='center'>"+Stage9_no_of_days+"Days and "+Stage9_time+"</TD>");
						}
						if(Stage10_time.equals("")){
						out.println("<TD width='100' class='txt_report_data' align='center'>-</TD>");
						}else{
						out.println("<TD width='100' class='txt_report_data' align='center'>"+Stage10_no_of_days+"Days and "+Stage10_time+"</TD>");
						}
						if(Stage11_time.equals("")){
						out.println("<TD width='100' class='txt_report_data' align='center'>-</TD>");
						}else{
						out.println("<TD width='100' class='txt_report_data' align='center'>"+Stage11_no_of_days+"Days and "+Stage11_time+"</TD>");
						}
						out.println("</tr >"); 
						more=rs.next(); 
			} 

			out.println("</table>");

			out.println("<br>"); 
	
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr><td width='100%' class='note'></td></tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("</body>"); 
			out.println("</html>"); 
			rs.close();
			pstmt.close();
			conn.close();
			out.flush();
			out.close();
	
		
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
			}		
			
			}
					


			}

			catch (Exception e) { 
			try { 
		
			}	 
			catch (Exception eti) {}
		
			ByteArrayOutputStream ostr = new ByteArrayOutputStream(); 
			e.printStackTrace(new PrintStream(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString()); 
			out.close();
			
			}
	}
}


