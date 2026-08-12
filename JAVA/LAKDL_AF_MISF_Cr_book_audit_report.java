import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_Cr_book_audit_report extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf,nf1;
	Statement stmt1,stmt2;
	CallableStatement callstmt1 =null;
	public ResultSet rs1,rs2;
	
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
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(2);
			nf1.setMaximumFractionDigits(2);			
			
			double oustanding_bal = 0.0;
			double future_receivble = 0.0;
			double unearned_income= 0.0;
			double tot_oustanding_bal = 0.0;
			double tot_future_receivble = 0.0;
			double tot_unearned_income = 0.0;			
			
			double nibsm = 0.0;			
			double ami = 0.0;			
			double maintaince = 0.0;			
			double tot_nibsm = 0.0;			
			double tot_ami = 0.0;			
			double tot_maintaince = 0.0;			
			int count=0;
			
			stmt1 = conn.createStatement();	
			stmt2 = conn.createStatement();	
			String m_chksql=req.getParameter("chksql");
			String m_finance_no="";
			String m_report="BROKER_PER_SUM";
			
			if(req.getParameter("finance_no")!=null&&req.getParameter("finance_no")!=""){
			   m_finance_no=req.getParameter("finance_no");
			}
			
			
			String m_date="";
			String m_to_date="";
			String m_charge_type="";
			String m_vehicle_no="";
			
			if(req.getParameter("vehicle_no")!=null&&req.getParameter("vehicle_no")!=""){
			   m_vehicle_no=req.getParameter("vehicle_no");
			}
			
		  if(m_chksql.equals("run_report")){ 				
			 try{	
			  /*callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_TBD_SAVE_CR_BOOK_RPT(:1,:2,:3);END;");
		
			  //callstmt1.setString(1,m_report);	
			  callstmt1.setString(1,m_bank);
			  callstmt1.setString(2,m_branch);
			  callstmt1.setString(3,m_username);
				
			  callstmt1.execute();*/
			
			  out.print("OK"); 
			    }
			 catch(Exception ex){
			  out.println("ERROR"+ex.toString()); 
		  }

		  }else							
  	   if(m_chksql.equals("main_page")){ 
		
	 		out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>CR Book Audit Trail Report </TITLE>"); 
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
			
			out.println("function load_sysdate(){	"); 
			rs1= stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
			
			if(rs1.next()){
			out.println("document.Form1.VAL_DAY.value='"+rs1.getString(1)+"';");
			out.println("document.Form1.VAL_MONTH.value='"+rs1.getString(2)+"';");
			out.println("document.Form1.VAL_YEAR.value='"+rs1.getString(3)+"';");
			
			out.println("document.Form1.TO_DAY.value='"+rs1.getString(1)+"';");
			out.println("document.Form1.TO_MONTH.value='"+rs1.getString(2)+"';");
			out.println("document.Form1.TO_YEAR.value='"+rs1.getString(3)+"';");
			}
			out.println("}"); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Cr_book_audit_report?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
				
			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Cr_book_audit_report?chksql=main_page';"); 
			out.println("}"); 
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_RE_Collection_Report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
			
			out.println("function load_roll_value(m_val){"); 
			out.println("		help_box.innerHTML=\" CR Book Audit Trail Report - \"+m_val;"); 
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
			out.println("	popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MAS_Help_Servlet?class_in="+m_fschema_name+"AF_MAS_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("		if(oBj.valout[1]!=\"Next\"){"); 
			out.println("			if(IfCount==\"1\"){"); 
			out.println("				broker_assign(oBj);"); 
			out.println("			}"); 
			out.println("			if(IfCount==\"3\"){"); 
			out.println("				help_value_assign_collection(oBj);"); 
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
			out.println("	}else{ document.Form1.BROKER_CODE.value='';}");
			out.println("	}else{ document.Form1.BROKER_CODE.value='';}	");
			out.println("}"); 
			
			
			out.println("function HelpBox1(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_CR_BOOK_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			//out.println("	clear_data();");
			out.println("	}else");
			
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_update_value_assign_bank();"); 
	  		out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("		help_update_value_assign_branch();"); 
	  		out.println("		}"); 		
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		Next1(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev1(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	else{");
			//out.println("	clear_data();");
			out.println("	}");
			out.println("	}	"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function Prev1(Start,End,Hid_No){"); 
			out.println("    HelpBox1(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next1 (Start,End,Hid_No){"); 
			out.println("    HelpBox1(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println("");
			
			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 

			out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
    
			out.println("function finance_help(){");
			out.println("	Crit=document.Form1.FINANCE_NO.value+\"@\";");
			out.println(" document.Form1.hid_help_type.value='1';");
			out.println("	HelpBox('1','10','0',Crit,'FinanceSql','1');");
			out.println("}");	
			
			out.println("function broker_help(){");
			out.println("	Crit=document.Form1.BROKER_CODE.value+\"@\"+\"Y@\";");
			out.println(" document.Form1.hid_help_type.value='1';");
			out.println("	HelpBox('1','10','0',Crit,'m_help_TXT_BROKER_CODE_sql_New','1');");
			out.println("}");
			
			
			out.println("function client_assign(oBj){");
			out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[2]");
			out.println("}");
			
			out.println("function finance_assign(oBj){");
			out.println(" document.Form1.FINANCE_NO.value =oBj.valout[2]");
			out.println("}");
			
			out.println("function broker_assign(oBj){");
			out.println(" document.Form1.BROKER_CODE.value =oBj.valout[2]");
			out.println("}");
 
			

			out.println("function check_Date(objDD,objMM,objYY) {");
			out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
			out.println("if(checkMonthLength(objDD,objMM,objYY)){");
			out.println("}");
			out.println("}");
			
      out.println("function run_report() {");
			out.println("		m_bank=document.Form1.TXT_BANK_CODE.value;");
			out.println("		m_branch=document.Form1.TXT_BRANCH_CODE.value;");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Cr_book_audit_report?chksql=run_report&bank=\"+m_bank+\"&branch=\"+m_branch;"); 
			out.println("   set_timer_actions();");
			out.println("		load_interface(m_url,'NORM');");
			out.println("}");
			
			out.println("function get_vector_normal(m_data){");
			out.println("		if(m_data==\"OK\"){");
			out.println("			print_report();"); 
			out.println("		}");
			out.println("		else{");
			out.println("			alert('Error when generating Report...'+m_data);");
			out.println("		}");
			out.println("}");
			
			out.println("function print_report(){");
			out.println("		clearTimeout(timerID);");
			out.println("		m_table.innerHTML=\"\";");
			out.println("		m_finance_no=document.Form1.TXT_FINANCE_NO.value;");
			out.println("		m_vehicle_no=document.Form1.TXT_VEH_NO.value;");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Cr_book_audit_report?chksql=print_report&finance_no=\"+m_finance_no+\"&vehicle_no=\"+m_vehicle_no;"); 
			out.println("			window.open(m_url);");
			out.println("}");
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
		  out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
		  out.println("}");
				
			out.println("function load_c_date(val) {");	
			out.println(" popupwin.close();");
		  out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
      out.println("if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");			
			out.println("     document.Form1.VAL_DAY.value=v_date;");
			out.println("     document.Form1.VAL_MONTH.value=v_month;");
			out.println("     document.Form1.VAL_YEAR.value=val;");
			out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
			out.println("  }");		
			
			out.println("if(document.Form1.hid_cal_date.value=='3'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");			
			out.println("     document.Form1.TO_DAY.value=v_date;");
			out.println("     document.Form1.TO_MONTH.value=v_month;");
			out.println("     document.Form1.TO_YEAR.value=val;");
			out.println("     document.Form1.hid_date.value=document.Form1.TO_DAY.value+'-'+document.Form1.TO_MONTH.value+'-'+document.Form1.TO_YEAR.value;");			
			out.println("  }");	
			
			
			out.println("}");
			out.println("}");
				
				
			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}");
			out.println("}"); 	
			
			out.println("function help_branch() {"); 
			out.println("bttn_help=\"2\";");
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_finance_no_all_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"Y@\";");
			out.println("    HelpBox1('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_bank() {");
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_BANK_CODE_sql\";"); 
			out.println("    var bank=document.Form1.TXT_BANK_CODE.value.substring(0,1)");
			out.println("    m_criteria = document.Form1.TXT_BANK_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox1('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_bank() {"); 
			out.println("    document.Form1.TXT_BANK_CODE.value=oBj.valout[2];"); 
			out.println("}");
			
			out.println("function help_update_value_assign_branch() {"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];");
			out.println("    document.Form1.TXT_VEH_NO.value=oBj.valout[3];");
			
			out.println("}"); 
			
			out.println("</script>"); 
				
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=''> ");
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_REPORT\">"); 
			out.println("<input type=hidden name='hid_cal_date' value=\"\">");
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>CR Book Audit Trail Report</td>"); 
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
			out.println("<table align='center' width='80%' class='table' border='0'>"); 
			
			
	        out.println("<tr >"); 
            out.println("<td width='10%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No * </DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='50' size='50'>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_branch()\" ></td>"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_VEH_NO'  class=div_input>Vehicle No </DIV></td>"); 
			out.println("<td  width='10%' ><input class='txt_input' type='text' name='TXT_VEH_NO' maxlength='25' size='25' disabled>"); 
			out.println("</td>"); 
			out.println("<td width='*%'>");
			out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report()\" style='{width=150px}'>");
			out.println("</td>"); 
			out.println("</tr>");
			
			/*out.println("<tr >"); 
            out.println("<td width='20%' ><DIV id='DIV_TXT_LOAN_NO'  class=div_input>Loan No</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LOAN_NO' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_loan()\" ></td>"); 
			out.println("</tr>");*/
			
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
			
			}else	
			if(m_chksql.equals("print_report")){	
				

			
			double cal_tot=0.00;
			double bal_tot=0.00;
			double set_tot=0.00;
			double adjust_tot=0.00;
			
			String m_broker_filter="";
			
			/*if(m_broker_code!=null && m_broker_code!=""){
			m_broker_filter =" AND A.BROKER_CODE='"+m_broker_code+"'";
		}*/
			
			out.println("<HTML><HEAD><TITLE>CR Book Summary</TITLE></HEAD>");
			out.println("<SCRIPT language=\"JavaScript\">"); //Added by Dineth on 2008-12-31
			
			
			out.println("   function show_maintenance_drill(m_app_no,m_date){");
	    out.println("   m_url=servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MISF_Contract_Balance_Report?chksql=SHOW_MAINTENANCE_DRILL&date=\"+m_date+\"&app_no=\"+m_app_no;");
	    out.println("   window.open(m_url,\"popupwin1\",\"status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1\");");
      out.println("   }");
		
		
		out.println("   function show_drill(m_loan_id){");
	    out.println("   m_url=servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MISF_Cr_book_audit_report?chksql=show_drill&loan_id=\"+m_loan_id;");
		out.println("   window.open(m_url);");
        out.println("   }");
		
		
      out.println("</SCRIPT>");
		
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			out.println("<FORM NAME='Form1' method='post'>"); 							

			out.println("<TABLE  WIDTH='100%' class='factoring-letter-body'>");
			out.println("<TR><TD align='Center' class=factoring-letter-body><B>CR Book Audit Trail Report</B></TD></TR>");
			out.println("</TABLE>");
			
			out.println("<br>");
			
			
			out.println("<table width='95%' align='center' class='table' border='1'  cellspacing='0' cellspacing='1' >");
			out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
			
			
			out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Finance No <DIV></td>");//0
			out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Vehicle No <DIV></td>");//0
			out.println("<td width='20%' align=left><DIV class=factoring-letter-body><b>CR Book Action<DIV></td>");//1
			out.println("<td width='15%' align=left><DIV class=factoring-letter-body><b>Loan No.<DIV></td>"); // added by udara 23-07-2019
			out.println("<td width='15%' align=left><DIV class=factoring-letter-body><b>Bank<DIV></td>"); // added by udara 23-07-2019
			//out.println("<td width='20%' align=center><DIV class=factoring-letter-body><b>Action<DIV></td>"); // added by udara 23-07-2019
			out.println("<td width='15%' align=left><DIV class=factoring-letter-body><b>Date<DIV></td>");	//2		
			out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>User</b></DIV></td>");//3
			out.println("</tr>");
		
			
			// commented by udara 07-09-2021
			/*
		  String Report_Data="SELECT FINANCE_NO, " +
							"  VEHICLE_NO, " +
							"  ENT_USER, " +
							"  TO_CHAR(ENT_DATE,'DD-MON-YYYY HH:MI:SS') ENT_DATE_VIEW, " +
							"  DESCRIPTION, " +
							" NVL(" + m_schema_name + ".AF_CO_GET_CR_BOOK_LOAN_NO(FINANCE_NO,CR_BOOK_STATUS),'-') LOAN_NO, " + // added by udara 23-07-2019
							" NVL(" + m_schema_name + ".AF_CO_GET_CR_BOOK_BANK(FINANCE_NO,CR_BOOK_STATUS),'-')  BANK, " + // added by udara 23-07-2019
							" " + m_schema_name + ".AF_CO_GET_CR_BOOK_STATUS(FINANCE_NO)  STATUS, " + 
							" " + m_schema_name + ".AF_CO_GET_CR_WITHDRAW_COUNT(FINANCE_NO,ENT_DATE)  COUNTS " +  
							" FROM " + m_schema_name + ".AF_PRO_CR_BOOK_LOG " +
							" WHERE FINANCE_NO='"+m_finance_no+"' AND VEHICLE_NO='"+m_vehicle_no+"' ORDER BY ENT_DATE";
			*/
			
			// added by udara 07-09-2021
			/*
			String Report_Data=" " +
							" SELECT FINANCE_NO, "+
								" VEHICLE_NO, "+
								" ENT_USER, "+
								" TO_CHAR(ENT_DATE,'DD-MON-YYYY HH:MI:SS') ENT_DATE_VIEW, "+
								" DESCRIPTION, "+
								" NVL(" + m_schema_name + ".AF_CO_GET_CR_BOOK_LOAN_NO(FINANCE_NO,CR_BOOK_STATUS),'-') LOAN_NO,  "+
								" NVL(" + m_schema_name + ".AF_CO_GET_CR_BOOK_BANK(FINANCE_NO,CR_BOOK_STATUS),'-')  BANK,  "+
								" " + m_schema_name + ".AF_CO_GET_CR_BOOK_STATUS(FINANCE_NO)  STATUS, "+  
								" " + m_schema_name + ".AF_CO_GET_CR_WITHDRAW_COUNT(FINANCE_NO,ENT_DATE)  COUNTS, "+
								" ENT_DATE "+
								" FROM " + m_schema_name + ".AF_PRO_CR_BOOK_LOG  "+
								" WHERE FINANCE_NO='"+m_finance_no+"' AND VEHICLE_NO='"+m_vehicle_no+"' "+
								
								" UNION "+
								
								" SELECT FINANCE_NO, "+
								" VEHICLE_NO, "+
								" NVL(MOD_USER,ENT_USER) ENT_USER, "+
								" TO_CHAR(NVL(MOD_DATE,ENT_DATE),'DD-MON-YYYY HH:MI:SS') ENT_DATE_VIEW, "+
								" " + m_schema_name + ".AF_CO_GET_CR_BOOK_STATUS(FINANCE_NO) DESCRIPTION, "+
								" NVL(" + m_schema_name + ".AF_CO_GET_CR_BOOK_LOAN_NO(FINANCE_NO,CR_STATUS),'-') LOAN_NO,  "+
								" NVL(" + m_schema_name + ".AF_CO_GET_CR_BOOK_BANK(FINANCE_NO,CR_STATUS),'-')  BANK, "+ 
								" " + m_schema_name + ".AF_CO_GET_CR_BOOK_STATUS(FINANCE_NO)  STATUS,  "+
								" " + m_schema_name + ".AF_CO_GET_CR_WITHDRAW_COUNT(FINANCE_NO,ENT_DATE)  COUNTS, "+
								" NVL(MOD_DATE,ENT_DATE) ENT_DATE "+
								" FROM " + m_schema_name + ".AF_PRO_CR_BOOK  "+
								" WHERE FINANCE_NO='"+m_finance_no+"' AND VEHICLE_NO='"+m_vehicle_no+"'  "+
								" ORDER BY ENT_DATE "+
							" ";
			*/
			
			String Report_Data=" "+
				" SELECT "+
					" FINANCE_NO, "+
					" VEHICLE_NO, "+
					" ENT_USER, "+
					" ENT_DATE_VIEW, "+
					" DESCRIPTION, "+
					" LOAN_NO, "+
					" BANK, "+
					" STATUS, "+
					" COUNTS, "+
					" ENT_DATE "+
					" FROM ( "+
					
					" SELECT FINANCE_NO,  "+
					" VEHICLE_NO, "+
					" ENT_USER, "+
					" TO_CHAR(ENT_DATE,'DD-MON-YYYY HH:MI:SS') ENT_DATE_VIEW, "+
					" DESCRIPTION, "+
					" NVL(" + m_schema_name + ".AF_CO_GET_CR_BOOK_LOAN_NO(FINANCE_NO,CR_BOOK_STATUS),'-') LOAN_NO,  "+
					" NVL(" + m_schema_name + ".AF_CO_GET_CR_BOOK_BANK(FINANCE_NO,CR_BOOK_STATUS),'-')  BANK,  "+
					" " + m_schema_name + ".AF_CO_GET_CR_BOOK_STATUS(FINANCE_NO)  STATUS,  "+ 
					" " + m_schema_name + ".AF_CO_GET_CR_WITHDRAW_COUNT(FINANCE_NO,ENT_DATE)  COUNTS, "+
					" ENT_DATE "+
					" FROM " + m_schema_name + ".AF_PRO_CR_BOOK_LOG  "+
					" WHERE FINANCE_NO='"+m_finance_no+"' AND VEHICLE_NO='"+m_vehicle_no+"' "+
													
					" UNION "+
													
					" SELECT A.FINANCE_NO, "+
					" A.VEHICLE_NO, "+
					" NVL(A.MOD_USER,A.ENT_USER) ENT_USER, "+
					" TO_CHAR(NVL(A.MOD_DATE,A.ENT_DATE),'DD-MON-YYYY HH:MI:SS') ENT_DATE_VIEW, "+
					" " + m_schema_name + ".AF_CO_GET_CR_BOOK_STATUS(A.FINANCE_NO) DESCRIPTION, "+
					//" NVL(" + m_schema_name + ".AF_CO_GET_CR_BOOK_LOAN_NO(A.FINANCE_NO,A.CR_STATUS),'-') LOAN_NO,  "+
					//" NVL(" + m_schema_name + ".AF_CO_GET_CR_BOOK_BANK(A.FINANCE_NO,A.CR_STATUS),'-')  BANK,  "+
					" NVL(" + m_schema_name + ".AF_CO_GET_CR_LOAN_NO_CURR(A.FINANCE_NO,A.CR_STATUS),'-') LOAN_NO,  "+
					" NVL(" + m_schema_name + ".AF_CO_GET_CR_BOOK_BANK_CURR(A.FINANCE_NO,A.CR_STATUS),'-')  BANK,  "+
					" " + m_schema_name + ".AF_CO_GET_CR_BOOK_STATUS(A.FINANCE_NO)  STATUS,  "+
					" " + m_schema_name + ".AF_CO_GET_CR_WITHDRAW_COUNT(A.FINANCE_NO,A.ENT_DATE)  COUNTS, "+
					" NVL(A.MOD_DATE,A.ENT_DATE) ENT_DATE "+
					" FROM " + m_schema_name + ".AF_PRO_CR_BOOK A  "+
					" WHERE A.FINANCE_NO='"+m_finance_no+"' AND VEHICLE_NO='"+m_vehicle_no+"'  "+
					" AND A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM " + m_schema_name + ".AF_DEL_LETTER_PROCESS WHERE FINANCE_NO = A.FINANCE_NO AND active_status = 'P') "+
					
					// commented by udara 08-12-2021
					/*
					" UNION "+
													
					" SELECT A.FINANCE_NO, "+
					" A.VEHICLE_NO, "+
					" (SELECT MOD_USER FROM " + m_schema_name + ".AF_DEL_LETTER_PROCESS WHERE FINANCE_NO = A.FINANCE_NO AND active_status = 'P') ENT_USER, "+
					" (SELECT TO_CHAR(MOD_DATE,'DD-MON-YYYY HH:MI:SS') FROM " + m_schema_name + ".AF_DEL_LETTER_PROCESS WHERE FINANCE_NO = A.FINANCE_NO AND active_status = 'P') ENT_DATE_VIEW, "+
					" 'Released' DESCRIPTION,  "+
					" NVL(" + m_schema_name + ".AF_CO_GET_CR_BOOK_LOAN_NO(A.FINANCE_NO,A.CR_STATUS),'-') LOAN_NO, "+ 
					" NVL(" + m_schema_name + ".AF_CO_GET_CR_BOOK_BANK(A.FINANCE_NO,A.CR_STATUS),'-')  BANK,  "+
					" 'Released'  STATUS,  "+
					" " + m_schema_name + ".AF_CO_GET_CR_WITHDRAW_COUNT(A.FINANCE_NO,A.ENT_DATE)  COUNTS, "+
					" (SELECT MOD_DATE FROM " + m_schema_name + ".AF_DEL_LETTER_PROCESS WHERE FINANCE_NO = A.FINANCE_NO AND active_status = 'P') ENT_DATE "+
					" FROM " + m_schema_name + ".AF_PRO_CR_BOOK A  "+
					" WHERE A.FINANCE_NO='"+m_finance_no+"' AND VEHICLE_NO='"+m_vehicle_no+"'  "+
					" AND A.FINANCE_NO  IN (SELECT FINANCE_NO FROM " + m_schema_name + ".AF_DEL_LETTER_PROCESS WHERE FINANCE_NO = A.FINANCE_NO AND active_status = 'P') "+
					*/
					
					" ) "+
				" ORDER BY ENT_DATE ";


             
			
			out.println	("<!--Report_Data = "+Report_Data+" -->");
			rs2= stmt2.executeQuery(Report_Data);
			
		
			int j=0;

			while(rs2.next()){
				if(j==0){
					out.println("<tr bgcolor=\"#FFFFFF\" >");
					j=1;
				}
				else{
					out.println("<tr bgcolor=\"#C0C0C0\" >");
					j=0;
				}
			count++;	
		 
            out.println("<td width='10%' class=factoring-letter-body  align=left   >"+rs2.getString("FINANCE_NO")+"</td>");//1 style='cursor:hand'
			out.println("<td width='10%' class=factoring-letter-body  align=left >"+rs2.getString("VEHICLE_NO")+"</td>");//2
			
			//out.println("<td width='10%' class=factoring-letter-body  align=left >"+rs2.getString("DESCRIPTION")+"</td>");//3 // commented by udara 23-07-2019
			
			// added by udara 23-07-2019
			if( (rs2.getString("DESCRIPTION").equals("CR in Safe")) && (rs2.getInt("COUNTS")>0)){
				out.println("<td width='20%' class=factoring-letter-body  align=left > Withdrawn & in safe </td>");
			}
			else{
				out.println("<td width='20%' class=factoring-letter-body  align=left >"+rs2.getString("DESCRIPTION")+"</td>");
			}
			// end by udara 23-07-2019
			
			out.println("<td width='10%' class=factoring-letter-body  align=left  >"+rs2.getString("LOAN_NO")+"</td>"); // added by udara 23-07-2019
			out.println("<td width='15%' class=factoring-letter-body  align=left  >"+rs2.getString("BANK")+"</td>"); // added by udara 23-07-2019
			
			// added by udara 24-07-2019
			/*
			if( (rs2.getString("STATUS").equals("Verified in Safe")) && (rs2.getInt("COUNTS")>0)){
				out.println("<td width='10%' class=factoring-letter-body  align=center  > Withdrawn & in safe </td>"); 
			}
			else{
				out.println("<td width='10%' class=factoring-letter-body  align=center  >"+rs2.getString("STATUS")+"</td>");
			}
			*/
			// end by udara 24-07-2019
			
			out.println("<td width='15%' class=factoring-letter-body  align=left  >"+rs2.getString("ENT_DATE_VIEW")+"</td>");//4
			out.println("<td width='10%' class=factoring-letter-body  align=left  >"+rs2.getString("ENT_USER")+"</td>");//4
			out.println("</tr>");	
			
			}
			out.println("</table>");
			
			
			out.println("</form>"); 
			
			
		  out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</BODY></HTML>");
				
			}
			
			
			
			
			
           if(stmt1!=null)
	       stmt1.close();
           if(rs1!=null)
		   rs1.close();
		   if(stmt2!=null)
		   stmt2.close();
		   if(rs2!=null)
		   rs2.close();
		  
			out.close();	
			conn.close();
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