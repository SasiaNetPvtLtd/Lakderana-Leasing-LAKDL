import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_CR_BOOK_WITH_DATE_RANGE extends javax.servlet.http.HttpServlet { 

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
			String m_bank="";
			String m_branch="";
			
				if(req.getParameter("date")!=null&&req.getParameter("date")!=""){
			        m_date=req.getParameter("date");
			     }
				
				if(req.getParameter("to_date")!=null&&req.getParameter("to_date")!=""){
			        m_to_date=req.getParameter("to_date");
			     }
			
			    if(req.getParameter("bank")!=null&&req.getParameter("bank")!=""){
			        m_bank=req.getParameter("bank");
			     }
				
				 if(req.getParameter("branch")!=null&&req.getParameter("branch")!=""){
			        m_branch=req.getParameter("branch");
			     }
			
			
			
		//   if(m_chksql.equals("run_report")){ 				
		// 	 try{	
		// 	  callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_TBD_SAVE_CR_BOOK_RPT(:1,:2,:3);END;");
		
		// 	  //callstmt1.setString(1,m_report);	
		// 	  callstmt1.setString(1,m_bank);
		// 	  callstmt1.setString(2,m_branch);
		// 	  callstmt1.setString(3,m_username);
				
		// 	  callstmt1.execute();
			
		// 	  out.print("OK"); 
		// 	    }
		// 	 catch(Exception ex){
		// 	  out.println("ERROR"+ex.toString()); 
		//   }
		 if(m_chksql.equals("run_report_cr")){ 	
			
			String m_from_date="";
			String m_too_date="";
			
			
				if(req.getParameter("from_date")!=null&&req.getParameter("from_date")!=""){
			        m_from_date=req.getParameter("from_date");
			     }else{
					out.println("ERROR : Null from date, please enter from date"); 
				 }
				
				if(req.getParameter("to_date")!=null&&req.getParameter("to_date")!=""){
			        m_too_date=req.getParameter("to_date");
			     }
				else{
					out.println("ERROR : Null To date, please enter to date"); 
				}


			 try{	
			  callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_CR_BOOK_RPT(:1,:2,:3);END;");
		
			  callstmt1.setString(1,m_from_date);
			  callstmt1.setString(2,m_too_date);
			  callstmt1.setString(3,m_username);
				
			  callstmt1.execute();
			
			  out.print("OK"); 
			    }
			 catch(Exception ex){
			  out.println("ERROR"+ex.toString()); 
		  }

		  }else							
  	   if(m_chksql.equals("main_page")){ 
		
	 		out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>CR Book Summary Report </TITLE>"); 
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_CR_BOOK_WITH_DATE_RANGE?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
				
			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Cr_book_summary_report?chksql=main_page';"); 
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
			out.println("		help_box.innerHTML=\" CR Book summary Report - \"+m_val;"); 
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
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Cr_book_summary_report?chksql=run_report&bank=\"+m_bank+\"&branch=\"+m_branch;"); 
			out.println("   set_timer_actions();");
			out.println("		load_interface(m_url,'NORM');");
			out.println("}");

			out.println("function run_report_CR() {");
			
			//out.println(" alert('alert_1'); ");
			
			out.println("  m_from_date = document.Form1.FROM_DAY.value+'-'+document.Form1.FROM_MONTH.value+'-'+document.Form1.FROM_YEAR.value;");  
			
			//out.println(" alert('alert_2'); ");
			
			out.println("  m_to_date   = document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;"); 
			
			//out.println(" alert('alert_3'); ");
			
			//out.println("console.log('run_report_CR FUNCTION');");
			//out.println("console.log('FROM DATE = ' + m_from_date);");
			//out.println("console.log('TO DATE   = ' + m_to_date);");
			
			//out.println(" alert('alert_4'); ");
			
			// out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"LAKDL_AF_MISF_CR_BOOK_WITH_DATE_RANGE?chksql=run_report_cr&bank=\"+m_bank+\"&branch=\"+m_branch;"); 
			out.println("m_url=\"" + m_class_url + "/" + m_fschema_name +"AF_MISF_CR_BOOK_WITH_DATE_RANGE?chksql=run_report_cr&from_date=\" + m_from_date + \"&to_date=\" + m_to_date ;");
			
			//out.println(" alert('alert_5'); ");
			
			out.println("   set_timer_actions();");
			
			//out.println(" alert('alert_6'); ");
			
			out.println("		load_interface(m_url,'NORM');");
			
			//out.println(" alert('alert_7'); ");
			
			out.println("}");
			
			out.println("function get_vector_normal(m_data){");
			out.println("		if(m_data==\"OK\"){");
			// out.println("			print_report();"); //ld
			out.println("			print_report_cr();");  // added by lakshitha dilshan 26/6/23

			out.println("		}");
			out.println("		else{");
			out.println("			alert('Error when generating Report...'+m_data);");
			out.println("		}");
			out.println("}");
			
			out.println("function print_report(){");
			out.println("		clearTimeout(timerID);");
			out.println("		m_table.innerHTML=\"\";");
			//out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
			//out.println("		m_to_date=document.Form1.TO_DAY.value+'-'+document.Form1.TO_MONTH.value+'-'+document.Form1.TO_YEAR.value;");
			out.println("		m_bank=document.Form1.TXT_BANK_CODE.value;");
			out.println("		m_branch=document.Form1.TXT_BRANCH_CODE.value;");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Cr_book_summary_report?chksql=print_report&bank=\"+m_bank+\"&branch=\"+m_branch;"); 
			out.println("			window.open(m_url);");
			out.println("}");
//LAKSHITHA DILSHAN 2026/6/22
			out.println("function print_report_cr(){");
			out.println("		clearTimeout(timerID);");
			out.println("		m_table.innerHTML=\"\";");
			out.println("       durationID = 0; ");
			
			out.println(" m_from_date = document.Form1.FROM_DAY.value+'-'+document.Form1.FROM_MONTH.value+'-'+document.Form1.FROM_YEAR.value;");  
			out.println("  m_to_date   = document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;"); 
			// out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CR_BOOK_WITH_DATE_RANGE?chksql=print_report_cr&from_date=\"+m_from_date+\"&to_date=\"+m_to_date\";"); 
	out.println(
"m_url=\"" + m_class_url + "/" + m_fschema_name +
"AF_MISF_CR_BOOK_WITH_DATE_RANGE?chksql=print_report_cr&from_date=\" + m_from_date + \"&to_date=\" + m_to_date ;"
);
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
			out.println("    m_sql = \"m_help_TXT_BRANCH_CODE_sql_new\";"); 
			out.println("    m_criteria = document.Form1.TXT_BRANCH_CODE.value+\"@\"+document.Form1.TXT_BANK_CODE.value+\"@\"+\"Y@\";");
			out.println("    HelpBox1('1','10','8');"); 
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
			out.println("    document.Form1.TXT_BRANCH_CODE.value=oBj.valout[2];");
			out.println("    document.Form1.TXT_BANK_CODE.value=oBj.valout[4];");
			out.println("}"); 
			
			// //lakshitha dishan 26/6/22
			// out.println("function check_Date(objDD,objMM,objYY) {");
			// 		out.println("if( objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
			// 		out.println("if(checkMonthLength(objDD,objMM,objYY))");
			// 		out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");						
			// 		out.println("}");
			
				// added by udara 01-07-2026
				out.println("function get_vector(data_vec) {");
				out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
				out.println("			document.Form1.FROM_DAY.value=data_vec[0];");
				out.println("			document.Form1.FROM_MONTH.value=data_vec[1];");
				out.println("			document.Form1.FROM_YEAR.value=data_vec[2];");
				out.println("			document.Form1.VAL_DAY.value=data_vec[0];");
				out.println("			document.Form1.VAL_MONTH.value=data_vec[1];");
				out.println("			document.Form1.VAL_YEAR.value=data_vec[2];");
				out.println("		}");
				out.println("}");



				out.println("function get_system_date() {");
				out.println("	  document.Form1.hid_option.value=\"1\";");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
				out.println("		load_interface(m_url,'XML');");
				out.println("}");
				// end by udara 01-07-2026


			out.println("</script>"); 
				
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD='get_system_date()'> ");
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"\">");
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>CR Book summary Report</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<td width='50%'> &nbsp; </td>");
			out.println("<td width='10%'> &nbsp; </td>");
			out.println("<td width='10%'> &nbsp; </td>");
			out.println("<td width='6%'> &nbsp; </td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			out.println("<table align='center' width='100%' class='table' border='0'>"); 
			
			/*out.println("<tr class=tr_input>");
			out.println("<td width='10%'ID=VDATE>From Date </td>");
			out.println("<td width='15%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
			out.println("<input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)>");
			out.println("<input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a>");
			out.println("</td>");
			
			out.println("<td width='10%'ID=VDATE>To Date </td>");
			out.println("<td width='15%'><input name=\"TO_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.TO_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
			out.println("<input name=\"TO_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.TO_DAY,document.Form1.TO_MONTH,document.Form1.TO_YEAR)>");
			out.println("<input name=\"TO_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.TO_DAY,document.Form1.TO_MONTH,document.Form1.TO_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a>");
			out.println("</td>");
			
			
			out.println("<td width='*%'>");
			out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report()\" style='{width=150px}'>");
			out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style='{width=150px}'>");
			out.println("</td>"); 
			out.println("</td>");
			out.println("</tr>");*/
			
			//  out.println("<tr >"); 
			// // out.println("<td width='30%' ><DIV id='DIV_TXT_BANK_CODE'  class=div_input>Bank </DIV></td>"); 
			// // out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_BANK_CODE' maxlength='5' size='5' >"); 
			// // out.println("<input class='but_input' type='button' name='BUT_TXT_BANK_CODE' value=\"Help\" onClick=\"help_bank()\"></td>");
			// // out.println("<td width='*%'></td>"); 
			// out.println("</tr>"); 
			
			
			/*
	        out.println("<tr >"); 
            // out.println("<td width='20%' ><DIV id='DIV_TXT_BRANCH_CODE'  class=div_input>Branch </DIV></td>"); 
			// out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BRANCH_CODE' maxlength='10' size='10'>"); 
			// out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_branch()\" ></td>"); 
			out.println("<td width='*%'>");
			// out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report()\" style='{width=150px}'>");
			// out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style='{width=150px}'>");
			out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report CR\" onClick=\"run_report_CR()\" style='{width=300px}'>");
			out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View CR BOOK\" onClick=\"print_report_cr()\" style='{width=300px}'>");

			out.println("</td>"); 
			out.println("</tr>");
			*/

					out.println("<tr class=tr_input >");
					out.println("<td width='20%'ID=VDATE>From Date</td>");
					out.println("<td width='*%'><input name=\"FROM_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)> ");
					out.println("    <input name=\"FROM_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)> ");
					out.println("    <input name=\"FROM_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)>");
					out.println("</td>");
					out.println("</tr>");

					out.println("<tr class=tr_input>");
					out.println("<td width='20%'ID=VDATE>To Date</td>");
					out.println("<td width='*%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
					out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
					out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)>"); // <a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> 
					out.println("</td>");
					out.println("</tr>");
					
					out.println("<tr class=tr_input>");
					out.println("<td width='20%'> &nbsp; </td>");
					out.println("<td width='*%'>");
					out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report CR\" onClick=\"run_report_CR()\" style='{width=150px}'>");
					out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View CR BOOK\" onClick=\"print_report_cr()\" style='{width=150px}'>");
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
			
			}
			//ADDED BY LAKSHITHA DILSHAN
			if(m_chksql.equals("run_report_CRX")){ 				
			 try{	
			  callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_CR_BOOK_RPT(:1,:2,:3);END;");
		
			  //callstmt1.setString(1,m_report);	
			  callstmt1.setString(1,m_bank);
			  callstmt1.setString(2,m_branch);
			  callstmt1.setString(3,m_username);
				
			  callstmt1.execute();
			
			  out.print("OK"); 
			    }
			 catch(Exception ex){
			  out.println("ERROR"+ex.toString()); 
		  }

		  }
			
			else	
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
	    out.println("   m_url=servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MISF_Cr_book_summary_report?chksql=show_drill&loan_id=\"+m_loan_id;");
		out.println("   window.open(m_url);");
        out.println("   }");
		
		// added by udara 01-06-2021
		out.println("   function show_drill_total(){");
	    out.println("   m_url=servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MISF_Cr_book_summary_report?chksql=show_drill_total\";");
		out.println("   window.open(m_url);");
        out.println("   }");
		// end by udara 01-06-2021
		
		
      out.println("</SCRIPT>");
		
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			out.println("<FORM NAME='Form1' method='post'>"); 							

			out.println("<TABLE  WIDTH='100%' class='factoring-letter-body'>");
			out.println("<TR><TD align='Center' class=factoring-letter-body><B>CR Book summary Report</B></TD></TR>");
			out.println("</TABLE>");
			
			out.println("<br>");
			
			
			out.println("<table width='100%' class='table' border='1'  cellspacing='0' cellspacing='1' >");
			out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
			
			out.println("<td width='5%' align=left><DIV class=factoring-letter-body><b>No <DIV></td>");//0
			out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Loan Id <DIV></td>");//0
			//out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Bank<DIV></td>");//1
			out.println("<td width='20%' align=left><DIV class=factoring-letter-body><b>Bank/Branch<DIV></td>");	//2		
			out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>No of Contracts</b></DIV></td>");//3
			out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Net Capital</b></DIV></td>");//3
			
		
		  String Report_Data="SELECT LOAN_ID, " +
								"  BANK, " +
								"  BRANCH, " +
								"  " + m_schema_name + ".af_co_get_branch_name_2(BRANCH)BRANCH_DESC, " +
								"  COUNT(FINANCE_NO) CONTRACT_COUNT," +
								"  SUM(NVL(FUTURE_CAPITAL,0)) FUTURE_CAPITAL" +
								" FROM " + m_schema_name + ".AF_TBD_CR_BOOK_RPT  " +
								" WHERE ENT_USER='"+m_username+"' AND DISPLAY_SECTION='PLEDGE' " +
								" GROUP BY LOAN_ID,BANK, BRANCH ";
             
			
			out.println	("<!--Report_Data = "+Report_Data+" -->");
			rs2= stmt2.executeQuery(Report_Data);
			
		
			int j=0;
			
			int m_no_of_contracts_count = 0; // added by udara 31-12-2019
			double m_net_cap_tot = 0; // added by udara 31-12-2019

			while(rs2.next()){
				
				m_no_of_contracts_count = m_no_of_contracts_count + rs2.getInt("CONTRACT_COUNT"); // added by udara 31-12-2019
				m_net_cap_tot = m_net_cap_tot + rs2.getDouble("FUTURE_CAPITAL"); // added by udara 31-12-2019
				
				if(j==0){
					out.println("<tr bgcolor=\"#FFFFFF\" >");
					j=1;
				}
				else{
					out.println("<tr bgcolor=\"#C0C0C0\" >");
					j=0;
				}
			count++;	
		 
		  out.println("<td width='5%' class=factoring-letter-body align=left>"+count+"</td>");//0
     
            out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=left   onclick='show_drill(\""+rs2.getString("LOAN_ID")+"\")'>"+rs2.getString("LOAN_ID")+"</td>");//1
			//out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=center onclick='show_drill(\""+rs2.getString("LOAN_ID")+"\")'>"+rs2.getString("BANK")+"</td>");//2
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=center onclick='show_drill(\""+rs2.getString("LOAN_ID")+"\")' >"+rs2.getString("BRANCH_DESC")+"</td>");//3
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=right  onclick='show_drill(\""+rs2.getString("LOAN_ID")+"\")' >"+rs2.getString("CONTRACT_COUNT")+"</td>");//4
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=right  onclick='show_drill(\""+rs2.getString("LOAN_ID")+"\")' >"+nf1.format(rs2.getDouble("FUTURE_CAPITAL"))+"</td>");//4
			out.println("</tr>");	
			
			}
			
			// added by udara 31-12-2019
			out.println("<tr>");
			out.println("<td width='5%' class=factoring-letter-body align=left><b> Total </b></td>");
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=left    >&nbsp;</td>");//1
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=center  >&nbsp;</td>");//3
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=right   onclick='show_drill_total()' ><b>"+m_no_of_contracts_count+"</b></td>");//4
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=right   onclick='show_drill_total()' ><b>"+nf1.format(m_net_cap_tot)+"</b></td>");//4
			out.println("</tr>");
			// end by udar 31-12-2019
			
			
			out.println("</table>");
			
			out.println("<br/><br/><br/>");
			
			Report_Data="SELECT   COUNT(FINANCE_NO) CONTRACT_COUNT, " +
				                "  SUM(NVL(FUTURE_CAPITAL,0)) FUTURE_CAPITAL" +
								" FROM " + m_schema_name + ".AF_TBD_CR_BOOK_RPT " +
								"WHERE ENT_USER='"+m_username+"' AND DISPLAY_SECTION='UNSECURE' ";
             
			
			out.println	("<!--Report_Data = "+Report_Data+" -->");
			rs2= stmt2.executeQuery(Report_Data);
			

			if(rs2.next()){
				out.println("<table width='40%' class='table' border='1'  cellspacing='0' cellspacing='1' >");
				out.println("<tr bgcolor=\"#FFFFFF\" >");
				out.println("<td width='10%' class=factoring-letter-body align=left  style='cursor:hand' onclick='show_drill(\"UNSECURE\")' ><b>Unsecured CR Books</b></td>");
				out.println("<td width='10%' class=factoring-letter-body align=right style='cursor:hand' onclick='show_drill(\"UNSECURE\")'  >"+rs2.getInt("CONTRACT_COUNT")+"</td>");
				out.println("<td width='10%' class=factoring-letter-body align=right style='cursor:hand' onclick='show_drill(\"UNSECURE\")'  >"+nf1.format(rs2.getDouble("FUTURE_CAPITAL"))+"</td>");
				out.println("</tr>");	
			    out.println("</table>");
			}
			
			count=0;
			out.println("</form>"); 
			
			
		  out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</BODY></HTML>");
				
			}
//LAKSHITHA DILSHAN 2026/6/22
			else if(m_chksql.equals("print_report_cr")){	
				
			String m_from_date= req.getParameter("from_date");
			String m_too_date = req.getParameter("to_date");
			
			double cal_tot=0.00;
			double bal_tot=0.00;
			double set_tot=0.00;
			double adjust_tot=0.00;
			
			String m_broker_filter="";
			
			/*if(m_broker_code!=null && m_broker_code!=""){
			m_broker_filter =" AND A.BROKER_CODE='"+m_broker_code+"'";
		}*/
			
			out.println("<HTML><HEAD><TITLE>CR Book Range</TITLE></HEAD>");
			out.println("<SCRIPT language=\"JavaScript\">"); //Added by Dineth on 2008-12-31
			
			
			out.println("   function show_maintenance_drill(m_app_no,m_date){");
	    out.println("   m_url=servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MISF_Contract_Balance_Report?chksql=SHOW_MAINTENANCE_DRILL&date=\"+m_date+\"&app_no=\"+m_app_no;");
	    out.println("   window.open(m_url,\"popupwin1\",\"status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1\");");
      out.println("   }");
		
		
		out.println("   function show_drill(m_loan_id){");
	    out.println("   m_url=servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MISF_Cr_book_summary_report?chksql=show_drill&loan_id=\"+m_loan_id;");
		out.println("   window.open(m_url);");
        out.println("   }");
		
		// added by udara 01-06-2021
		out.println("   function show_drill_total(){");
	    out.println("   m_url=servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MISF_Cr_book_summary_report?chksql=show_drill_total\";");
		out.println("   window.open(m_url);");
        out.println("   }");
		// end by udara 01-06-2021
		
		
      out.println("</SCRIPT>");
		
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			out.println("<FORM NAME='Form1' method='post'>"); 							

			out.println("<TABLE  WIDTH='100%' class='factoring-letter-body'>");
			out.println("<TR><TD align='Center' class=factoring-letter-body><B>CR Book summary Report</B></TD></TR>");
			out.println("</TABLE>");
			
			out.println("<br>");
			
			
			out.println("<table width='100%' class='table' border='1'  cellspacing='0' cellspacing='1' >");
			out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
			
			// out.println("<td width='5%' align=left><DIV class=factoring-letter-body><b>No <DIV></td>");//0
			// out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Facility Number<DIV></td>");//0
			// //out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Bank<DIV></td>");//1
			// out.println("<td width='20%' align=left><DIV class=factoring-letter-body><b>Vehicle Number<DIV></td>");	//2		
			// out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Customer Name</b></DIV></td>");//3
			// out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Customer Address</b></DIV></td>");//3

			out.println("<td width='5%' align=left><DIV class=factoring-letter-body><b>No</b></DIV></td>");//0

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Facility Number</b></DIV></td>");//1

				out.println("<td width='15%' align=left><DIV class=factoring-letter-body><b>Vehicle No</b></DIV></td>");//2

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Bank / Safe</b></DIV></td>");//3

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Bank of CR pledged</b></DIV></td>");//4

				out.println("<td width='15%' align=left><DIV class=factoring-letter-body><b>Client Name</b></DIV></td>");//5

				out.println("<td width='15%' align=left><DIV class=factoring-letter-body><b>Client Address</b></DIV></td>");//6

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Client NIC</b></DIV></td>");//7

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Vehicle Type</b></DIV></td>");//8

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Vehicle Model</b></DIV></td>");//9

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Make</b></DIV></td>");//10

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Engine Number</b></DIV></td>");//11

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Chassis Number</b></DIV></td>");//12

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Year Of Manufacture</b></DIV></td>");//13

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Commenced on</b></DIV></td>");//14

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Matured on</b></DIV></td>");//15

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>HP Amount Granted</b></DIV></td>");//16

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Rental Amount</b></DIV></td>");//17

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Period</b></DIV></td>");//18

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Future Period</b></DIV></td>");//19

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Mature Period</b></DIV></td>");//20

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>No of Months in Arrears</b></DIV></td>");//21

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Arrears</b></DIV></td>");//22

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Future Receivable</b></DIV></td>");//23

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Future income</b></DIV></td>");//24

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Future Capital</b></DIV></td>");//25

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Lead Source Category</b></DIV></td>");//26

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Insurance Company</b></DIV></td>");//27

				out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Market Value</b></DIV></td>");//28
							
		
             
			//String Report_Data = " SELECT FINANCE_NO,CR_STATUS, VEHICLE_NUMBER, CUSTOMER_NAME, " + m_schema_name + ".af_co_get_branch_name_2(BRANCH)BRANCH_DESC, BRANCH, CUSTOMER_ADDRESS, CUSTOMER_NIC, ITEM_CATEGORY, VEHICLE_MODEL, MAKE_CODE, ENGINE_NUMBER, CHASSIS_NUMBER, YEAR_OF_MANUFACTURE, CONTRACT_START_DATE, MATURITY_DATE, RENTAL_AMOUNT, RENTAL_PERIOD, BALANCE_OUSTANDING, FUTURE_RECEIVABLE, FUTURE_CAPITAL, LEAD_SOURCE_CATEGORY, INSURANCE_COMPANY, MARKET_VALUE, GEN_USER,PERIOD,FUTURE_PERIOD,MATURE_PERIOD,NO_MONTHS_ARREARS,FUTURE_INCOME,HP_AMOUNT_GRANTED FROM AF_CO_CR_BOOK";
			//String Report_Data = " SELECT FINANCE_NO,CR_STATUS, VEHICLE_NUMBER, CUSTOMER_NAME, " + m_schema_name + ".af_co_get_branch_name_2(BRANCH)BRANCH_DESC, BRANCH, CUSTOMER_ADDRESS, CUSTOMER_NIC, ITEM_CATEGORY, VEHICLE_MODEL, MAKE_CODE, ENGINE_NUMBER, CHASSIS_NUMBER, YEAR_OF_MANUFACTURE,TO_CHAR(CONTRACT_START_DATE, 'DD-MON-YYYY') AS CONTRACT_START_DATE, TO_CHAR(MATURITY_DATE, 'DD-MON-YYYY') AS  MATURITY_DATE, RENTAL_AMOUNT, RENTAL_PERIOD, BALANCE_OUSTANDING, FUTURE_RECEIVABLE, FUTURE_CAPITAL, LEAD_SOURCE_CATEGORY, INSURANCE_COMPANY, MARKET_VALUE, GEN_USER,PERIOD,FUTURE_PERIOD,MATURE_PERIOD,NO_MONTHS_ARREARS,FUTURE_INCOME,HP_AMOUNT_GRANTED FROM AF_CO_CR_BOOK";
			String Report_Data = " SELECT FINANCE_NO,CR_STATUS, VEHICLE_NUMBER, CUSTOMER_NAME, " + m_schema_name + ".af_co_get_branch_name_LD(BRANCH)BRANCH_DESC, BRANCH, CUSTOMER_ADDRESS, CUSTOMER_NIC, ITEM_CATEGORY, VEHICLE_MODEL, MAKE_CODE, ENGINE_NUMBER, CHASSIS_NUMBER, YEAR_OF_MANUFACTURE,TO_CHAR(CONTRACT_START_DATE, 'DD-MON-YYYY') AS CONTRACT_START_DATE, TO_CHAR(MATURITY_DATE, 'DD-MON-YYYY') AS  MATURITY_DATE, RENTAL_AMOUNT, RENTAL_PERIOD, BALANCE_OUSTANDING, FUTURE_RECEIVABLE, FUTURE_CAPITAL, LEAD_SOURCE_CATEGORY, INSURANCE_COMPANY, MARKET_VALUE, GEN_USER,PERIOD,FUTURE_PERIOD,MATURE_PERIOD,NO_MONTHS_ARREARS,FUTURE_INCOME,HP_AMOUNT_GRANTED FROM AF_CO_CR_BOOK WHERE GEN_USER='"+m_username+"'";

			out.println	("<!--Report_Data = "+Report_Data+" -->");
			rs2= stmt2.executeQuery(Report_Data);
			
		
			int j=0;
			
			int m_no_of_contracts_count = 0; // added by udara 31-12-2019
			double m_net_cap_tot = 0; // added by udara 31-12-2019

			while(rs2.next()){
				
				m_no_of_contracts_count = m_no_of_contracts_count + rs2.getInt("RENTAL_AMOUNT"); // edited
				m_net_cap_tot = m_net_cap_tot + rs2.getDouble("RENTAL_AMOUNT"); // added by udara 31-12-2019
				
				if(j==0){
					out.println("<tr bgcolor=\"#FFFFFF\" >");
					j=1;
				}
				else{
					out.println("<tr bgcolor=\"#C0C0C0\" >");
					j=0;
				}
			count++;	
		 
		  out.println("<td width='5%' class=factoring-letter-body align=left>"+count+"</td>");//0
     
            // out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=left   onclick='show_drill(\""+rs2.getString("CUSTOMER_NAME")+"\")'>"+rs2.getString("CUSTOMER_NAME")+"</td>");//1
			// //out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=center onclick='show_drill(\""+rs2.getString("LOAN_ID")+"\")'>"+rs2.getString("BANK")+"</td>");//2
			
out.println("<td width='8%' class=factoring-letter-body align=center>"+rs2.getString("FINANCE_NO")+"</td>");
// out.println("<td width='10%' class=factoring-letter-body align=center>"+rs2.getString("VEHICLE_NUMBER")+"</td>");
out.println("<td width='10%' class='factoring-letter-body align=center' style='white-space:nowrap;'>" + rs2.getString("VEHICLE_NUMBER") + "</td>");
out.println("<td width='12%' class=factoring-letter-body align=left>"+rs2.getString("CR_STATUS")+"</td>");
out.println("<td width='15%' class=factoring-letter-body align=left>"+rs2.getString("BRANCH_DESC")+"</td>");//BANK PLEDG
out.println("<td width='15%' class=factoring-letter-body align=left>"+rs2.getString("CUSTOMER_NAME")+"</td>");

out.println("<td width='10%' class=factoring-letter-body align=center>"+rs2.getString("CUSTOMER_ADDRESS")+"</td>");
out.println("<td width='10%' class=factoring-letter-body align=center>"+rs2.getString("CUSTOMER_NIC")+"</td>");
out.println("<td width='10%' class=factoring-letter-body align=center>"+rs2.getString("ITEM_CATEGORY")+"</td>");

out.println("<td width='10%' class=factoring-letter-body align=center>"+rs2.getString("VEHICLE_MODEL")+"</td>");
out.println("<td width='10%' class=factoring-letter-body align=center>"+rs2.getString("MAKE_CODE")+"</td>");
out.println("<td width='10%' class=factoring-letter-body align=center>"+rs2.getString("ENGINE_NUMBER")+"</td>");
out.println("<td width='10%' class=factoring-letter-body align=center>"+rs2.getString("CHASSIS_NUMBER")+"</td>");
out.println("<td width='8%' class=factoring-letter-body align=center>"+rs2.getString("YEAR_OF_MANUFACTURE")+"</td>");
// out.println("<td width='10%' class=factoring-letter-body align=center>"+rs2.getString("CONTRACT_START_DATE")+"</td>");
// out.println("<td width='10%' class=factoring-letter-body align=center>"+rs2.getString("MATURITY_DATE")+"</td>");
// For CONTRACT_START_DATE - keep as is or add nowrap
out.println("<td width='10%' class='factoring-letter-body align=center' style='white-space:nowrap;'>" + rs2.getString("CONTRACT_START_DATE") + "</td>");

// For MATURITY_DATE - add nowrap to prevent break at hyphens
out.println("<td width='10%' class='factoring-letter-body align=center' style='white-space:nowrap;'>" + rs2.getString("MATURITY_DATE") + "</td>");
out.println("<td width='10%' class=factoring-letter-body align=right>"+rs2.getString("HP_AMOUNT_GRANTED")+"</td>");//HP GRANTED AMOUNT
out.println("<td width='8%' class=factoring-letter-body align=center>"+rs2.getString("RENTAL_AMOUNT")+"</td>");
out.println("<td width='10%' class=factoring-letter-body align=right>"+rs2.getString("PERIOD")+"</td>");
out.println("<td width='10%' class=factoring-letter-body align=right>"+rs2.getString("FUTURE_PERIOD")+"</td>");
out.println("<td width='10%' class=factoring-letter-body align=right>"+rs2.getString("MATURE_PERIOD")+"</td>");
out.println("<td width='12%' class=factoring-letter-body align=left>"+rs2.getString("NO_MONTHS_ARREARS")+"</td>");
out.println("<td width='12%' class=factoring-letter-body align=left>"+rs2.getString("BALANCE_OUSTANDING")+"</td>");//A
out.println("<td width='10%' class=factoring-letter-body align=right>"+rs2.getString("FUTURE_RECEIVABLE")+"</td>");
out.println("<td width='8%' class=factoring-letter-body align=center>"+rs2.getString("FUTURE_INCOME")+"</td>");
out.println("<td width='8%' class=factoring-letter-body align=center>"+rs2.getString("FUTURE_CAPITAL")+"</td>");
out.println("<td width='8%' class=factoring-letter-body align=center>"+rs2.getString("LEAD_SOURCE_CATEGORY")+"</td>");
out.println("<td width='8%' class=factoring-letter-body align=center>"+rs2.getString("INSURANCE_COMPANY")+"</td>");
out.println("<td width='8%' class=factoring-letter-body align=center>"+rs2.getString("MARKET_VALUE")+"</td>");

			out.println("</tr>");	
			
			}
			
			
			
			
			out.println("</table>");
			
			out.println("<br/><br/><br/>");
			
			// Report_Data="SELECT   COUNT(FINANCE_NO) CONTRACT_COUNT, " +
			// 	                "  SUM(NVL(FUTURE_CAPITAL,0)) FUTURE_CAPITAL" +
			// 					" FROM " + m_schema_name + ".AF_TBD_CR_BOOK_RPT " +
			// 					"WHERE ENT_USER='"+m_username+"' AND DISPLAY_SECTION='UNSECURE' ";
             
			
			// out.println	("<!--Report_Data = "+Report_Data+" -->");
			// rs2= stmt2.executeQuery(Report_Data);
			

			// if(rs2.next()){
			// 	out.println("<table width='40%' class='table' border='1'  cellspacing='0' cellspacing='1' >");
			// 	out.println("<tr bgcolor=\"#FFFFFF\" >");
			// 	out.println("<td width='10%' class=factoring-letter-body align=left  style='cursor:hand' onclick='show_drill(\"UNSECURE\")' ><b>Unsecured CR Books</b></td>");
			// 	out.println("<td width='10%' class=factoring-letter-body align=right style='cursor:hand' onclick='show_drill(\"UNSECURE\")'  >"+rs2.getInt("CONTRACT_COUNT")+"</td>");
			// 	out.println("<td width='10%' class=factoring-letter-body align=right style='cursor:hand' onclick='show_drill(\"UNSECURE\")'  >"+nf1.format(rs2.getDouble("FUTURE_CAPITAL"))+"</td>");
			// 	out.println("</tr>");	
			//     out.println("</table>");
			 //}
			
			count=0;
			out.println("</form>"); 
			
			
		  out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</BODY></HTML>");
				
			}
			//LAKSHITHA DILSHAN 2026/06/22
			else if(m_chksql.equals("cr_book_date_range")){	
				

			
			double cal_tot=0.00;
			double bal_tot=0.00;
			double set_tot=0.00;
			double adjust_tot=0.00;
			
			String m_loan_id=req.getParameter("loan_id");
			
			
			out.println("<HTML><HEAD><TITLE>CR Book </TITLE></HEAD>");
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			// added by udara 09-07-2019
			out.println("function show_transaction_history_new(val,val2){ "); 
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
			out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");
			// end by udara 09-07-2019
			
            out.println("</SCRIPT>");
		
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			out.println("<FORM NAME='Form1' method='post'>"); 							

			out.println("<TABLE  WIDTH='100%' class='factoring-letter-body'>");
			out.println("<TR><TD align='Center' class=factoring-letter-body><B>CR Book summary Report</B></TD></TR>");
			out.println("</TABLE>");
			
			out.println("<br>");
			
			
			out.println("<table width='100%' class='table' border='1'  cellspacing='0' cellspacing='1' >");
			out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
			
			out.println("<td width='5%' align=left><DIV class=factoring-letter-body><b>No <DIV></td>");//0
			out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Loan Id <DIV></td>");//0
			//out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Bank<DIV></td>");//1
			out.println("<td width='20%' align=left><DIV class=factoring-letter-body><b>Bank/Branch<DIV></td>");	//2		
			out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Finance No</b></DIV></td>");//3
			out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Vehicle No</b></DIV></td>");//3
			out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Net Capital</b></DIV></td>");//3
			
		String Report_Data="";
		if(!m_loan_id.equals("UNSECURE")){
		Report_Data  ="SELECT LOAN_ID, " +
								"  BANK, " +
								"  BRANCH, " +
								"  " + m_schema_name + ".af_co_get_branch_name_2(BRANCH)BRANCH_DESC, " +
								"  NVL(VEHICLE_NO,'-')VEHICLE_NO ,FINANCE_NO,NVL(FUTURE_CAPITAL,0) FUTURE_CAPITAL " +
								" FROM " + m_schema_name + ".AF_TBD_CR_BOOK_RPT  " +
								" WHERE ENT_USER='"+m_username+"' AND LOAN_ID='"+m_loan_id+"' " ;
             
	}else if(m_loan_id.equals("UNSECURE")){
				
		Report_Data="SELECT    NVL(LOAN_ID,'-')LOAN_ID, NVL(BANK,'-') BANK, " +
								"  NVL(BRANCH,'-') BRANCH, " +
								"  " + m_schema_name + ".af_co_get_branch_name_2(BRANCH)BRANCH_DESC, " +
								"  NVL(VEHICLE_NO,'-')VEHICLE_NO ,FINANCE_NO,NVL(FUTURE_CAPITAL,0) FUTURE_CAPITAL " +
								" FROM " + m_schema_name + ".AF_TBD_CR_BOOK_RPT " +
								" WHERE ENT_USER='"+m_username+"' AND DISPLAY_SECTION='UNSECURE' ";
		
	    }else{
			
				Report_Data="SELECT LOAN_ID, " +
								"  NVL(BANK,'-') BANK, " +
								"  NVL(BRANCH,'-')BRANCH, " +
								"  " + m_schema_name + ".af_co_get_branch_name_2(BRANCH)BRANCH_DESC, " +
								"  NVL(VEHICLE_NO,'-')VEHICLE_NO ,FINANCE_NO,NVL(FUTURE_CAPITAL,0) FUTURE_CAPITAL " +
								" FROM " + m_schema_name + ".AF_TBD_CR_BOOK_RPT  " +
								" WHERE ENT_USER='"+m_username+"' AND DISPLAY_SECTION='PLEDGE'" ;
			}
		
		
			out.println	("<!--Report_Data = "+Report_Data+" -->");
			rs2= stmt2.executeQuery(Report_Data);
			
		
			int j=0;
			
			int m_count = 0; // added by udara 31-12-2019
			double m_net_total = 0;

			while(rs2.next()){
				
				m_count = m_count + 1; // added by udara 31-12-2019
				m_net_total = m_net_total + rs2.getDouble("FUTURE_CAPITAL"); // added by udara 31-12-2019
				
				if(j==0){
					out.println("<tr bgcolor=\"#FFFFFF\" >");
					j=1;
				}
				else{
					out.println("<tr bgcolor=\"#C0C0C0\" >");
					j=0;
				}
			count++;	
		 
		  out.println("<td width='5%' class=factoring-letter-body align=left>"+count+"</td>");//0
     
            out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=left   >"+rs2.getString("LOAN_ID")+"</td>");//1
			//out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=center >"+rs2.getString("BANK")+"</td>");//2
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=center  >"+rs2.getString("BRANCH_DESC")+"</td>");//3
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=left onclick=\"show_transaction_history_new('','"+rs2.getString("FINANCE_NO")+"')\"  ><u>"+rs2.getString("FINANCE_NO")+"</u></td>");//4 // added show_transaction_history_new by udara 09-07-2019
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=left   >"+rs2.getString("VEHICLE_NO")+"</td>");//5
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=right   >"+nf1.format(rs2.getDouble("FUTURE_CAPITAL"))+"</td>");//5
			
			out.println("</tr>");	
			
			}
			
			// added by udara 31-12-2019
			out.println("<tr>");
			out.println("<td width='5%' class=factoring-letter-body align=left><b>Total</b></td>");//0
            out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=left   ><b>"+m_count+"</b></td>");//1
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=center  > &nbsp; </td>");//3
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=left  > &nbsp; </td>");//4 // added show_transaction_history_new by udara 09-07-2019
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=left   > &nbsp; </td>");//5
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=right   ><b>"+nf1.format(m_net_total)+"</b></td>");//5
			
			out.println("</tr>");	
			// end by udara 31-12-2019
			
			
			out.println("</table>");
			count=0;
			
			out.println("</form>"); 
			
			
		  out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</BODY></HTML>");
				
			}
			
			
			
			else if(m_chksql.equals("show_drill")){	
				

			
			double cal_tot=0.00;
			double bal_tot=0.00;
			double set_tot=0.00;
			double adjust_tot=0.00;
			
			String m_loan_id=req.getParameter("loan_id");
			
			
			out.println("<HTML><HEAD><TITLE>CR Book Summary Drill</TITLE></HEAD>");
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			// added by udara 09-07-2019
			out.println("function show_transaction_history_new(val,val2){ "); 
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
			out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");
			// end by udara 09-07-2019
			
            out.println("</SCRIPT>");
		
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			out.println("<FORM NAME='Form1' method='post'>"); 							

			out.println("<TABLE  WIDTH='100%' class='factoring-letter-body'>");
			out.println("<TR><TD align='Center' class=factoring-letter-body><B>CR Book summary Report</B></TD></TR>");
			out.println("</TABLE>");
			
			out.println("<br>");
			
			
			out.println("<table width='100%' class='table' border='1'  cellspacing='0' cellspacing='1' >");
			out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
			
			out.println("<td width='5%' align=left><DIV class=factoring-letter-body><b>No <DIV></td>");//0
			out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Loan Id <DIV></td>");//0
			//out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Bank<DIV></td>");//1
			out.println("<td width='20%' align=left><DIV class=factoring-letter-body><b>Bank/Branch<DIV></td>");	//2		
			out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Finance No</b></DIV></td>");//3
			out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Vehicle No</b></DIV></td>");//3
			out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Net Capital</b></DIV></td>");//3
			
		String Report_Data="";
		if(!m_loan_id.equals("UNSECURE")){
		Report_Data  ="SELECT LOAN_ID, " +
								"  BANK, " +
								"  BRANCH, " +
								"  " + m_schema_name + ".af_co_get_branch_name_2(BRANCH)BRANCH_DESC, " +
								"  NVL(VEHICLE_NO,'-')VEHICLE_NO ,FINANCE_NO,NVL(FUTURE_CAPITAL,0) FUTURE_CAPITAL " +
								" FROM " + m_schema_name + ".AF_TBD_CR_BOOK_RPT  " +
								" WHERE ENT_USER='"+m_username+"' AND LOAN_ID='"+m_loan_id+"' " ;
             
	}else if(m_loan_id.equals("UNSECURE")){
				
		Report_Data="SELECT    NVL(LOAN_ID,'-')LOAN_ID, NVL(BANK,'-') BANK, " +
								"  NVL(BRANCH,'-') BRANCH, " +
								"  " + m_schema_name + ".af_co_get_branch_name_2(BRANCH)BRANCH_DESC, " +
								"  NVL(VEHICLE_NO,'-')VEHICLE_NO ,FINANCE_NO,NVL(FUTURE_CAPITAL,0) FUTURE_CAPITAL " +
								" FROM " + m_schema_name + ".AF_TBD_CR_BOOK_RPT " +
								" WHERE ENT_USER='"+m_username+"' AND DISPLAY_SECTION='UNSECURE' ";
		
	    }else{
			
				Report_Data="SELECT LOAN_ID, " +
								"  NVL(BANK,'-') BANK, " +
								"  NVL(BRANCH,'-')BRANCH, " +
								"  " + m_schema_name + ".af_co_get_branch_name_2(BRANCH)BRANCH_DESC, " +
								"  NVL(VEHICLE_NO,'-')VEHICLE_NO ,FINANCE_NO,NVL(FUTURE_CAPITAL,0) FUTURE_CAPITAL " +
								" FROM " + m_schema_name + ".AF_TBD_CR_BOOK_RPT  " +
								" WHERE ENT_USER='"+m_username+"' AND DISPLAY_SECTION='PLEDGE'" ;
			}
		
		
			out.println	("<!--Report_Data = "+Report_Data+" -->");
			rs2= stmt2.executeQuery(Report_Data);
			
		
			int j=0;
			
			int m_count = 0; // added by udara 31-12-2019
			double m_net_total = 0;

			while(rs2.next()){
				
				m_count = m_count + 1; // added by udara 31-12-2019
				m_net_total = m_net_total + rs2.getDouble("FUTURE_CAPITAL"); // added by udara 31-12-2019
				
				if(j==0){
					out.println("<tr bgcolor=\"#FFFFFF\" >");
					j=1;
				}
				else{
					out.println("<tr bgcolor=\"#C0C0C0\" >");
					j=0;
				}
			count++;	
		 
		  out.println("<td width='5%' class=factoring-letter-body align=left>"+count+"</td>");//0
     
            out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=left   >"+rs2.getString("LOAN_ID")+"</td>");//1
			//out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=center >"+rs2.getString("BANK")+"</td>");//2
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=center  >"+rs2.getString("BRANCH_DESC")+"</td>");//3
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=left onclick=\"show_transaction_history_new('','"+rs2.getString("FINANCE_NO")+"')\"  ><u>"+rs2.getString("FINANCE_NO")+"</u></td>");//4 // added show_transaction_history_new by udara 09-07-2019
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=left   >"+rs2.getString("VEHICLE_NO")+"</td>");//5
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=right   >"+nf1.format(rs2.getDouble("FUTURE_CAPITAL"))+"</td>");//5
			
			out.println("</tr>");	
			
			}
			
			// added by udara 31-12-2019
			out.println("<tr>");
			out.println("<td width='5%' class=factoring-letter-body align=left><b>Total</b></td>");//0
            out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=left   ><b>"+m_count+"</b></td>");//1
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=center  > &nbsp; </td>");//3
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=left  > &nbsp; </td>");//4 // added show_transaction_history_new by udara 09-07-2019
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=left   > &nbsp; </td>");//5
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=right   ><b>"+nf1.format(m_net_total)+"</b></td>");//5
			
			out.println("</tr>");	
			// end by udara 31-12-2019
			
			
			out.println("</table>");
			count=0;
			
			out.println("</form>"); 
			
			
		  out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</BODY></HTML>");
				
			}
			
			
			// added by udara 01-06-2021
			else if(m_chksql.equals("show_drill_total")){	
				

			
			double cal_tot=0.00;
			double bal_tot=0.00;
			double set_tot=0.00;
			double adjust_tot=0.00;
			
			//String m_loan_id=req.getParameter("loan_id");
			
			
			out.println("<HTML><HEAD><TITLE>CR Book Summary Drill</TITLE></HEAD>");
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			// added by udara 09-07-2019
			out.println("function show_transaction_history_new(val,val2){ "); 
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
			out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");
			// end by udara 09-07-2019
			
            out.println("</SCRIPT>");
		
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			out.println("<FORM NAME='Form1' method='post'>"); 							

			out.println("<TABLE  WIDTH='100%' class='factoring-letter-body'>");
			out.println("<TR><TD align='Center' class=factoring-letter-body><B>CR Book summary Report</B></TD></TR>");
			out.println("</TABLE>");
			
			out.println("<br>");
			
			
			out.println("<table width='100%' class='table' border='1'  cellspacing='0' cellspacing='1' >");
			out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
			
			out.println("<td width='5%' align=left><DIV class=factoring-letter-body><b>No <DIV></td>");//0
			out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Loan Id <DIV></td>");//0
			//out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Bank<DIV></td>");//1
			out.println("<td width='20%' align=left><DIV class=factoring-letter-body><b>Bank/Branch<DIV></td>");	//2		
			out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Finance No</b></DIV></td>");//3
			out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Vehicle No</b></DIV></td>");//3
			out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Net Capital</b></DIV></td>");//3
			
		String Report_Data="";
		
		Report_Data="SELECT LOAN_ID, " +
								"  NVL(BANK,'-') BANK, " +
								"  NVL(BRANCH,'-')BRANCH, " +
								"  " + m_schema_name + ".af_co_get_branch_name_2(BRANCH)BRANCH_DESC, " +
								"  NVL(VEHICLE_NO,'-')VEHICLE_NO ,FINANCE_NO,NVL(FUTURE_CAPITAL,0) FUTURE_CAPITAL " +
								" FROM " + m_schema_name + ".AF_TBD_CR_BOOK_RPT  " +
								" WHERE ENT_USER='"+m_username+"' "+
								" AND DISPLAY_SECTION='PLEDGE'  " ;
		
		
			out.println	("<!--Report_Data = "+Report_Data+" -->");
			rs2= stmt2.executeQuery(Report_Data);
			
		
			int j=0;
			
			int m_count = 0; // added by udara 31-12-2019
			double m_net_total = 0;

			while(rs2.next()){
				
				m_count = m_count + 1; // added by udara 31-12-2019
				m_net_total = m_net_total + rs2.getDouble("FUTURE_CAPITAL"); // added by udara 31-12-2019
				
				if(j==0){
					out.println("<tr bgcolor=\"#FFFFFF\" >");
					j=1;
				}
				else{
					out.println("<tr bgcolor=\"#C0C0C0\" >");
					j=0;
				}
			count++;	
		 
		  out.println("<td width='5%' class=factoring-letter-body align=left>"+count+"</td>");//0
     
            out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=left   >"+rs2.getString("LOAN_ID")+"</td>");//1
			//out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=center >"+rs2.getString("BANK")+"</td>");//2
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=center  >"+rs2.getString("BRANCH_DESC")+"</td>");//3
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=left onclick=\"show_transaction_history_new('','"+rs2.getString("FINANCE_NO")+"')\"  ><u>"+rs2.getString("FINANCE_NO")+"</u></td>");//4 // added show_transaction_history_new by udara 09-07-2019
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=left   >"+rs2.getString("VEHICLE_NO")+"</td>");//5
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=right   >"+nf1.format(rs2.getDouble("FUTURE_CAPITAL"))+"</td>");//5
			
			out.println("</tr>");	
			
			}
			
			// added by udara 31-12-2019
			out.println("<tr>");
			out.println("<td width='5%' class=factoring-letter-body align=left><b>Total</b></td>");//0
            out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=left   ><b>"+m_count+"</b></td>");//1
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=center  > &nbsp; </td>");//3
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=left  > &nbsp; </td>");//4 // added show_transaction_history_new by udara 09-07-2019
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=left   > &nbsp; </td>");//5
			out.println("<td width='10%' class=factoring-letter-body style='cursor:hand' align=right   ><b>"+nf1.format(m_net_total)+"</b></td>");//5
			
			out.println("</tr>");	
			// end by udara 31-12-2019
			
			
			out.println("</table>");
			count=0;
			
			out.println("</form>"); 
			
			
		    out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</BODY></HTML>");
				
			}
			// end by udara 01-06-2021
			
			
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
