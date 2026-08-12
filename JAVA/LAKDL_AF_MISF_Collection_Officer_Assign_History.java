/*
// header - edit "Data/yourJavaHeader" to customize
// contents - edit "EventHandlers/Java file/onCreate" to customize
//
*/
/* Created by Dineth on 25th July 2008*/

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 



public class LAKDL_AF_MISF_Collection_Officer_Assign_History extends javax.servlet.http.HttpServlet
{
			ServletOutputStream out = null;
			Connection conn;
			java.text.NumberFormat nf,nf1;
			Statement stmt,stmt1,stmt2,stmt3,stmt4;
			CallableStatement callstmt1 =null;
			public ResultSet rs,rs1,rs2,rs3,rs4;
  		java.lang.Math a;
			
		public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 	
			try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name=m_sn_methods.schema_name;
			String m_username=m_sn_methods.username;
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(0);
			nf.setMaximumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(2);
			nf1.setMaximumFractionDigits(2);
			
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt3 = conn.createStatement();
			stmt4 = conn.createStatement();
			String m_chksql=req.getParameter("chksql");
			
			
			
			if(m_chksql.equals("print_report1")){
			
				String collection_officer = req.getParameter("coll_officer");
				//String finance_no = req.getParameter("fin_no");
				String from_date = req.getParameter("from_date");
				String to_date = req.getParameter("to_date");
				//String application_no = req.getParameter("app_no");

		 		String sql1      = " SELECT B.FINANCE_NO,LAKDL.AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE),LAKDL.AF_CO_GET_EMP_NAME(A.COLLECTION_OFFICER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'DD-MM-YYYY hh:mm:ss'),NVL(A.REMARKS,'-'), "+
												" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+//7
												" TO_CHAR(SYSDATE,'DD-MM-YYYY') "+//8
		 										" FROM "+m_schema_name+".AF_RE_PRO_COLLEC_OFFICER_BK A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B"+
												" WHERE TO_DATE('"+from_date+"','DD-MM-YYYY') <= TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
												" AND TO_DATE('"+to_date+"','DD-MM-YYYY') >= TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')"+
												" AND A.COLLECTION_OFFICER LIKE '%"+ collection_officer + "%'" +
												" AND A.APPLICATION_NO = B.APPLICATION_NO "+
												" ORDER BY A.ENT_DATE ASC";
			
			
			
			
			rs1=stmt1.executeQuery(sql1);
			boolean more = rs1.next();
			
				out.println("<HTML><HEAD><TITLE>Collection Process - Collection Officer Assign History</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				
	
			  
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='*%'align='center' class=div_input><b>Collection Officer Assign History</b></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<hr color='black'>");
				out.println("<br>");
				if(!more){
						out.println("<BR/>");
						out.println("<BR/>");
						out.println("<BR/>");
						out.println("<DIV valign='middle'>");
						out.println("<TABLE  WIDTH='100%' border='0'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Records </B></TD></TR>");
						out.println("</TABLE>");
						out.println("</DIV>");
					}		
				if(more){
				
				out.println("<table align='center' width='100%' class='table' border='0' cellspacing='0' >");
				out.println("<tr class=pdn_txtpos2>");
				
				out.println("<td width='13%' class=div_input align='left'>Finance No</td>");
				out.println("<td width='13%' class=div_input align='left'>Client Name</td>");
				out.println("<td width='13%' class=div_input align='left'>Collection Officer</td>");
				out.println("<td width='13%' class=div_input align='left'>Assigned Date</td>");
				out.println("<td width='13%' class=div_input align='left'>Assigned User</td>");
				out.println("<td width='13%' class=div_input align='left'>Remarks</td>");
				out.println("</tr>");
				
		while(more){
			/*out.println(rs1.getString(1));
			out.println(rs1.getString(2));
			out.println(rs1.getString(3));
			out.println(rs1.getString(4));*/
			out.println("<tr>");
			out.println("<td width='13%' class=div_input>"+rs1.getString(1)+"</td>");
			out.println("<td width='13%' class=div_input>"+rs1.getString(2)+"</td>");
			out.println("<td width='13%' class=div_input>"+rs1.getString(3)+"</td>");
			out.println("<td width='13%' class=div_input>"+rs1.getString(5)+"</td>");
			out.println("<td width='13%' class=div_input>"+rs1.getString(4)+"</td>");
			out.println("<td width='13%' class=div_input>"+rs1.getString(6)+"</td>");
			
			out.println("</tr>");
			//out.println(rs1.getString(5));
			more=rs1.next();
			}
			out.println("</table>");
			}
			out.println("</body>");
			out.println("</html>");
			
			
			}
			if(m_chksql.equals("print_report2")){
			 			
			
			//String collection_officer = req.getParameter("coll_officer");
			String finance_no = req.getParameter("fin_no");
			//String from_date = req.getParameter("from_date");
			//String to_date = req.getParameter("to_date");
			String application_no = req.getParameter("app_no");

									
		/*String sql2  = " SELECT  "+
								" B.FINANCE_NO, "+//1
								" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE), "+//2
								" NVL("+m_schema_name+".AF_CO_GET_EMP_NAME(A.COLLECTION_OFFICER),'-'), "+//3
								" A.ENT_USER, "+//4
								" TO_CHAR(A.ENT_DATE,'YYYY-MM-DD hh:mm:ss'),  "+//5
								" NVL(A.REMARKS,'-') , "+//6
								" TO_CHAR(B.ENT_DATE,'YYYY-MM-DD hh:mm:ss'), "+
								" NVL(TO_CHAR(B.ASSIGN_DATE,'YYYY-MM-DD hh:mm:ss'),TO_CHAR(A.ENT_DATE,'YYYY-MM-DD hh:mm:ss')) "+
								" FROM "+m_schema_name+".AF_RE_PRO_COLLEC_OFFICER_BK A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
								" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
								" AND A.APPLICATION_NO = '"+application_no+"'  "+
								" ORDER BY A.ENT_DATE DESC";*/
								
		String sql2  = " SELECT  "+
								" B.FINANCE_NO, "+//1
								" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE), "+//2
								//" NVL("+m_schema_name+".AF_CO_GET_EMP_NAME(A.COLLECTION_OFFICER),'-'), "+//3
								//" NVL("+m_schema_name+".AF_CO_GET_EMP_NAME(A.COLLECTION_OFFICER),"+m_schema_name+".AF_CO_GET_EMP_NAME(B.COLLECTION_OFFICER)), "+//3
								//" NVL(A.COLLECTION_OFFICER,B.COLLECTION_OFFICER), "+//Commented by Dineth on 2009-02-26
								" "+m_schema_name+".AF_CO_GET_EMP_NAME(NVL(A.COLLECTION_OFFICER,B.COLLECTION_OFFICER)), "+//Added by Dineth on 2009-02-26
								" A.ENT_USER, "+//4
								" NVL(TO_CHAR(A.ENT_DATE,'YYYY-MM-DD hh:mi:ss'),TO_CHAR(B.ENT_DATE,'YYYY-MM-DD hh:mi:ss')),  "+//5//hh:mi:ss Modified By Lalanka on 25-06-2009
								" NVL(A.REMARKS,'-') , "+//6
								" TO_CHAR(A.ENT_DATE,'YYYY-MM-DD hh:mi:ss'), "+//7//hh:mi:ss Modified By Lalanka on 25-06-2009
								" NVL(TO_CHAR(B.ASSIGN_DATE,'YYYY-MM-DD hh:mi:ss'),TO_CHAR(A.ENT_DATE,'YYYY-MM-DD hh:mi:ss')) "+//8
								" FROM "+m_schema_name+".AF_RE_PRO_COLLEC_OFFICER_BK A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
								" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
								" AND A.APPLICATION_NO = '"+application_no+"'  "+
								" ORDER BY A.ENT_DATE DESC";
			//out.println(sql2);					
		 	rs2=stmt2.executeQuery(sql2);
			boolean more2 = rs2.next();
			
				out.println("<HTML><HEAD><TITLE>Collection Process - Collection Officer Assign History</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
			  
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='*%'align='center' class=div_input><b>Collection Officer Assign History</b></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<hr color='black'>");
				//out.println("<br>");
				if(!more2){
						out.println("<BR/>");
						out.println("<BR/>");
						out.println("<BR/>");
						out.println("<DIV valign='middle'>");
						out.println("<TABLE  WIDTH='100%' border='0'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Records </B></TD></TR>");
						out.println("</TABLE>");
						out.println("</DIV>");
					}		
				if(more2){
				out.println("<table align='center' width='98%' class='table' border=0>");
				out.println("<tr>");
				out.println("<td width='8%'align='left' class=div_input><b>Finance No</b></td>");
				
	//======================================= add by Indika ===============================================================================
				String curr_collection_officer = "";
				String pas_collection_officer1 = "";
				String pas_collection_officer2 = "";
				String from_date = "";
				String to_date = "";
				
	//======================================= end by Indika ==============================================================================
				
				out.println("<td width='*%'align='left' class=div_input><b>:&nbsp;"+rs2.getString(1)+"</b></td>");
				out.println("</tr>");
				//out.println("</table>");
				
				//out.println("<table align='center' width='98%' class='table' >");
				out.println("<tr>");
				
				String sql4  = " SELECT "+ 
									" NVL("+m_schema_name+".AF_CO_GET_EMP_NAME(B.COLLECTION_OFFICER),' '), "+ //1
									" TO_CHAR(B.ENT_DATE,'YYYY-MM-DD hh:mm:ss') "+//2
									" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
									" ORDER BY B.ENT_DATE DESC";
									
				rs4=stmt4.executeQuery(sql4);
				boolean more4 = rs4.next();
				
				if(more4){
					out.println("<td width='8%'align='left' class=div_input><b>Collection Officer Name</b></td>");
					curr_collection_officer = rs4.getString(1);
					if(curr_collection_officer.equals(" ")) {
						while(more2) {
							curr_collection_officer = rs4.getString(1);
							if(!curr_collection_officer.equals(" ")) {
								out.println("<td width='25%'align='left' class=div_input><b>:&nbsp;"+rs4.getString(1)+"</b></td>");	
								out.println("</tr>");
								out.println("<td width='8%'align='left' class=div_input><b>From Date</b></td>");
								out.println("<td width='*%'align='left' class=div_input><b>:&nbsp;"+rs4.getString(2)+"</b></td>");
								break;
							}
						}
					}
					else {
						out.println("<td width='25%'align='left' class=div_input><b>:&nbsp;"+rs4.getString(1)+"</b></td>");
						out.println("</tr>");
						out.println("<td width='8%'align='left' class=div_input><b>From Date</b></td>");
						out.println("<td width='*%'align='left' class=div_input><b>:&nbsp;"+rs4.getString(2)+"</b></td>");
					}
					//out.println("</tr>");
					out.println("</tr>");
				}
				out.println("</table>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' border=0>"); 
			  out.println("<tr class=pdn_txtpos2 >"); //class=pdn_txtpos2
				
				//out.println("<td width='16%' class=div_input align='left'>Finance No</td>");
				//out.println("<td width='16%' class=div_input align='left'>Client Name</td>");
				out.println("<td width='25%' class=div_input align='left'>Collection Officer</td>");
				out.println("<td width='19%' class=div_input align='left'>Assigned Date</td>");
				out.println("<td width='15%' class=div_input align='left'>Assigned User</td>");
				out.println("<td width='15%' class=div_input align='left'>Remarks</td>");
				
				//============================= add by Indika =================================
				
				out.println("<td width='19%' class=div_input align='left'>From Date</td>");
				//out.println("<td width='19%' class=div_input align='left'>To Date</td>");
				
				//=============================== End by Indika =====================================

				out.println("</tr>");
				
		while(more2){
			out.println("<tr>");
			out.println("<td width='25%' class=div_input align='left'>"+rs2.getString(3)+"</td>");			
			out.println("<td width='19%' class=div_input align='left'>"+rs2.getString(5)+"</td>");			
			out.println("<td width='15%' class=div_input align='left'>"+rs2.getString(4)+"</td>");
			out.println("<td width='15%' class=div_input align='left'>"+rs2.getString(6)+"</td>");
			out.println("<td width='19%' class=div_input align='left'>"+rs2.getString(5)+"</td>");
			
			//=============================== add by Indika =====================================
			
			
			
			
			
			/*if (curr_collection_officer.equals(rs2.getString(3))) {
			
				String sql3  = " SELECT  "+
								" NVL("+m_schema_name+".AF_CO_GET_EMP_NAME(A.COLLECTION_OFFICER),'-'), "+//1
								" TO_CHAR(A.ENT_DATE,'YYYY-MM-DD hh:mm:ss')  "+//2
								" FROM "+m_schema_name+".AF_RE_PRO_COLLEC_OFFICER_BK A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
								" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
								" AND A.APPLICATION_NO = '"+application_no+"'  "+
								" ORDER BY A.ENT_DATE DESC";
								
				rs3=stmt3.executeQuery(sql3);
				boolean more3 = rs3.next();
			
				while(more3) {
					pas_collection_officer1 = rs3.getString(1);
					if(!pas_collection_officer1.equals(curr_collection_officer)) {
						from_date = rs3.getString(2);
						to_date = "-";
						out.println("<td width='19%' class=div_input align='left'>"+from_date+"</td>");
						out.println("<td width='19%' class=div_input align='left'>"+to_date+"</td>");
						break;
					}
					more3=rs3.next();
				}
			
			}
			else {
				String sql3  = " SELECT  "+
								" NVL("+m_schema_name+".AF_CO_GET_EMP_NAME(A.COLLECTION_OFFICER),'-'), "+//1
								" TO_CHAR(A.ENT_DATE,'YYYY-MM-DD hh:mm:ss')  "+//2
								" FROM "+m_schema_name+".AF_RE_PRO_COLLEC_OFFICER_BK A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
								" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
								" AND A.APPLICATION_NO = '"+application_no+"'  "+
								" ORDER BY A.ENT_DATE DESC";
								
				rs3=stmt3.executeQuery(sql3);
				boolean more3 = rs3.next();
			
				while(more3) {
					pas_collection_officer2 = rs3.getString(1);
					to_date = from_date;
					if(!pas_collection_officer1.equals(pas_collection_officer2)) {
						from_date = rs3.getString(2);
						out.println("<td width='19%' class=div_input align='left'>"+from_date+"</td>");
						out.println("<td width='19%' class=div_input align='left'>"+to_date+"</td>");
						break;
					}
					more3=rs3.next();
				}
			}
			*/
			
			
			//out.println("<td width='15%' class=div_input align='left'>"+rs2.getString(5)+"</td>");
			
			//=============================== End by Indika =====================================
			
			out.println("</tr>");
			//out.println(rs1.getString(5));
			more2=rs2.next();
			}
			out.println("</table>");
			}
			out.println("</body>");
			out.println("</html>");
			
			
			}
			
			
			
			if(m_chksql.equals("main_page")){ 
			
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Collection Officer Assign History</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("var timerID;");
				out.println("var durationID=0;");
				
				out.println("function set_timer_actions() {");
				out.println("   durationID=durationID+1;");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				out.println("}");
				
				/*out.println("function load_sysdate(){	"); 
				rs1= stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				if(rs1.next()){
				out.println("document.Form1.FROM_VAL_DAY.value='"+rs1.getString(1)+"';");
				out.println("document.Form1.FROM_VAL_MONTH.value='"+rs1.getString(2)+"';");
				out.println("document.Form1.FROM_VAL_YEAR.value='"+rs1.getString(3)+"';");
				}
				
				rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				if(rs2.next()){
				out.println("document.Form1.TO_VAL_DAY.value='"+rs2.getString(1)+"';");
				out.println("document.Form1.TO_VAL_MONTH.value='"+rs2.getString(2)+"';");
				out.println("document.Form1.TO_VAL_YEAR.value='"+rs2.getString(3)+"';");
				}

				out.println("}"); */
				
				out.println("function load_lock(){	"); 
				//out.println("		document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Officer_Assign_History?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				/*
				out.println("function new_window(){	"); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Officer_Assign_History?chksql=main_page;"); 
				out.println("}"); 
				*/
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_AF_RE_Collection_Report\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("		help_box.innerHTML=\" Collection Process - Collection Officer Assign History - \"+m_val;"); 
				out.println("}"); 
				
				out.println("function load_roll_out_value(){");
				out.println("}");
				
				out.println("function MyDialog(){"); 
				out.println("    this.valout=new Array(10);"); 
				out.println("}");
				
				out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
				out.println("	oBj = new MyDialog();"); 
				out.println(" oBj.valout[1]=\" \";"); 
				out.println(" oBj.valout[2]=\" \";"); 
				out.println(" oBj.valout[3]=\" \";"); 
				out.println("	popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("		if(oBj.valout[1]!=\"Next\"){"); 
				out.println("			if(IfCount==\"1\"){"); 
				out.println("			client_assign(oBj);"); 
				out.println("			}"); 
				out.println("			if(IfCount==\"3\"){"); 
				out.println("			help_value_assign_collection(oBj);"); 
				out.println("			}");
				out.println("			if(IfCount==\"4\"){"); 
				out.println("			help_value_assign_finance_no(oBj);"); 
				out.println("			}"); 
				
				out.println("		}");
				out.println("		else{"); 
				out.println("			Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
				out.println("			return false;"); 
				out.println("		}"); 
				out.println("	}");
				out.println("	else{"); 
				out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
				out.println("	}"); 
				out.println("	}");
				out.println("	}	");
				out.println("}"); 

				out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 

				out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 

				out.println("function client_help(){");
				out.println("	Crit=document.Form1.CLIENT_CODE.value+\"@\";");
				out.println(" document.Form1.hid_help_type.value='1';");
				out.println("	HelpBox('1','10','0',Crit,'ClientSql','1');");
				out.println("}");		

				out.println("function client_assign(oBj){");
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[2]");
				out.println("}");

				out.println("function help_button_collection_officer() {"); 
			  out.println(" document.Form1.hid_help_type.value='3';");
				
				out.println(" Crit = document.Form1.TXT_COLLECTION_OFFICER.value+\"@Y@\";"); 
			  //out.println(" Crit = document.Form1.TXT_COLLECTION_OFFICER.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@Y@\";"); 
			  out.println(" HelpBox('1','10','0',Crit,'m_help_TXT_EMP_CODE_sql','3');");
			  out.println("}"); 
			  
				
				out.println("function help_value_assign_collection(oBj) {"); 
				out.println(" document.Form1.TXT_COLLECTION_OFFICER.value=oBj.valout[2];"); 
				out.println("}"); 
			
				
				out.println("function help_button_finance_no() {"); 
			  	out.println(" document.Form1.hid_help_type.value='4';");
			  	out.println(" Crit = document.Form1.TXT_FINANCE_NO.value+\"@Y@\";"); 
			  	out.println(" HelpBox('1','10','0',Crit,'m_help_TXT_FINANCE_OFF_sql_new','4');");
			  	out.println("}"); 
				
				out.println("function help_value_assign_finance_no(oBj) {"); 
				out.println(" document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
				out.println(" document.Form1.HID_TXT_APPLICATION_NO.value=oBj.valout[8];");
				//out.println("alert(oBj.valout[8]);");
				out.println("}");
				
			
				
				out.println("function run_report() {");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Officer_Assign_History?chksql=run_report&date=\"+m_date+\"&client_code=\"+document.Form1.CLIENT_CODE.value+\"&coll_officer=\"+document.Form1.TXT_COLLECTION_OFFICER.value;");

				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				out.println("			print_report();"); 
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				out.println("function print_report1(){");
				out.println("		clearTimeout(timerID);");
				out.println("		m_table.innerHTML=\"\";");
				out.println("	if((document.Form1.FROM_VAL_DAY.value!=\"\" && document.Form1.FROM_VAL_MONTH.value!=\"\" && document.Form1.FROM_VAL_YEAR.value!=\"\") && (document.Form1.TO_VAL_DAY.value!=\"\" && document.Form1.TO_VAL_MONTH.value!=\"\" && document.Form1.TO_VAL_YEAR.value!=\"\")){");
				out.println(" from_date=document.Form1.FROM_VAL_DAY.value+'-'+document.Form1.FROM_VAL_MONTH.value+'-'+document.Form1.FROM_VAL_YEAR.value;" );
				out.println(" to_date=document.Form1.TO_VAL_DAY.value+'-'+document.Form1.TO_VAL_MONTH.value+'-'+document.Form1.TO_VAL_YEAR.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Officer_Assign_History?chksql=print_report1&from_date=\"+from_date+\"&to_date=\"+to_date+\"&coll_officer=\"+document.Form1.TXT_COLLECTION_OFFICER.value;"); 
				
				//out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Officer_Assign_History?chksql=print_report\";"); 
				out.println("			window.open(m_url);");
				out.println("	}");
				out.println("}");

			//=================================================== Edit By Indika =============================================================================================================================================================================
				
				out.println("function print_report2(){");
				out.println("		clearTimeout(timerID);");
				out.println("		m_table.innerHTML=\"\";");
				//out.println("	if((document.Form1.FROM_VAL_DAY.value!=\"\" && document.Form1.FROM_VAL_MONTH.value!=\"\" && document.Form1.FROM_VAL_YEAR.value!=\"\") && (document.Form1.TO_VAL_DAY.value!=\"\" && document.Form1.TO_VAL_MONTH.value!=\"\" && document.Form1.TO_VAL_YEAR.value!=\"\")){");
				
				//out.println(" from_date=document.Form1.FROM_VAL_DAY.value+'-'+document.Form1.FROM_VAL_MONTH.value+'-'+document.Form1.FROM_VAL_YEAR.value;" );
				//out.println(" to_date=document.Form1.TO_VAL_DAY.value+'-'+document.Form1.TO_VAL_MONTH.value+'-'+document.Form1.TO_VAL_YEAR.value;");
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Officer_Assign_History?chksql=print_report2&from_date=\"+from_date+\"&to_date=\"+to_date+\"&fin_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&app_no=\"+document.Form1.HID_TXT_APPLICATION_NO.value;"); 
				
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Officer_Assign_History?chksql=print_report2&fin_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&app_no=\"+document.Form1.HID_TXT_APPLICATION_NO.value;"); 
           
				//out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Collection_Officer_Assign_History?chksql=print_report\";"); 
				out.println("			window.open(m_url);");
				//out.println("	}");
				out.println("}");				
			//=================================================== End By Indika ===============================================================================================================================================================================
			
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_REPORT\">");
				out.println("<INPUT TYPE='Hidden' NAME='HID_TXT_APPLICATION_NO' VALUE=\"\">");
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Collection Officer Assign History</td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
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
				out.println("<table align='center' width='100%' class='table' border='0'>"); 
				
			//================================================ comment by Indika ==========================================================================
			
				//out.println("<tr class=tr_input>");
				//out.println("<td width='20%'ID=VDATE>From Date *</td>");
				//out.println("<td width='15%'><input name=\"FROM_VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.FROM_VAL_DAY,document.Form1.FROM_VAL_MONTH,document.Form1.FROM_VAL_YEAR)> ");
				//out.println("<input name=\"FROM_VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.FROM_VAL_DAY,document.Form1.FROM_VAL_MONTH,document.Form1.FROM_VAL_YEAR)>");
				//out.println("<input name=\"FROM_VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.FROM_VAL_DAY,document.Form1.FROM_VAL_MONTH,document.Form1.FROM_VAL_YEAR)>");
				//out.println("</td>");
				//out.println("<td width='10%'>&nbsp;</td>");
				//out.println("<td width='20%'ID=VDATE>To Date *</td>");
				//out.println("<td width='15%'><input name=\"TO_VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.TO_VAL_DAY,document.Form1.TO_VAL_MONTH,document.Form1.TO_VAL_YEAR)> ");
				//out.println("<input name=\"TO_VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.TO_VAL_DAY,document.Form1.TO_VAL_MONTH,document.Form1.TO_VAL_YEAR)>");
				//out.println("<input name=\"TO_VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.TO_VAL_DAY,document.Form1.TO_VAL_MONTH,document.Form1.TO_VAL_YEAR)>");
				//out.println("</td>");
				
			//================================================ end of comment =================================================================================
				out.println("<td width='*%'>");
				
				out.println("</td>"); 
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr >");
				
				/*out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code </DIV></td>"); 
				out.println("<td width='15%' ><input class='txt_input' type='text' name='CLIENT_CODE' maxlength='50' style='{width=150px}' size='50' onblur=\"client_help()\">"); 
				out.println("</td>");
				out.println("<td width='*%'><input class='but_input' type='button' name='BUT_TXT_CLIENT_CODE' value=\"Help\" onClick=\"client_help()\">"); 
				out.println("</td>");
				out.println("</tr>");*/
				/*out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_COLLECTION_OFFICER'  class=div_input>Collection Officer </DIV></td>"); 
				out.println("<td width='15%' ><input class='txt_input' type='text' name='TXT_COLLECTION_OFFICER' maxlength='10' style='{width=150px}' size='10' onblur=\"help_button_collection_officer()\">"); 
				out.println("</td>");
				out.println("<td width='*%' colspan='2'><input class='but_input' type='button' name='BUT_COLLECTION_OFFICER' value=\"Help\" onClick=\"help_button_collection_officer()\">"); 
				//out.println("</td>");
				out.println("&nbsp;<input class='but_input' type='button' name='BUT_PRINT1' value=\"View Report\" onClick=\"print_report1()\" style='{width=100px}'>");
				out.println("</td>");
				out.println("</tr>"); */

			
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No </DIV></td>"); 
				out.println("<td width='15%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='20' style='{width=150px}' size='10' onblur=\"help_button_finance_no()\">"); 
				out.println("</td>");
				out.println("<td width='*%' colspan='2'><input class='but_input' type='button' name='BUT_FINANCE_NO' value=\"Help\" onClick=\"help_button_finance_no()\">"); 
				//out.println("</td>");
				out.println("&nbsp;<input class='but_input' type='button' name='BUT_PRINT2' value=\"View Report\" onClick=\"print_report2()\" style='{width=100px}'>");
				out.println("</td>");
				out.println("</tr>"); 
				
				//========================================================= End by Indika ============================================================================================================
				
				out.println("</table>"); 
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>"); 
				out.println("</table>"); 
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' class='note'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				out.flush();
		}
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
		
				if(stmt1!=null){try{stmt1.close();  }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
				if(conn!=null){try{conn.close();  }catch(Exception e){}}
		}
	}
}

