//--
//SCREEN NAME:CREDIT PROCESS - COLLECTION MISF REPORTS 
//CREATED BY:NUWAN DE SILVA 
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_RE_Collection_Vehicle_In_Hand extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf;
	java.lang.Math a;
	Statement stmt;
	public ResultSet rs;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			
			String m_date_dd="";
			String m_date_mm="";
			String m_date_yy="";
			String m_val_date="";
			
			
			
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			String m_chksql=req.getParameter("chksql");
			
			
			
			if(m_chksql.equals("main_page")){ 
				
				String m_sort_column   = "CLIENT_CODE";	
				String m_order_by_type = "ASC";
				
				
				stmt = conn.createStatement ();
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD'), "+
					"TO_CHAR(SYSDATE,'MM'), "+
					"TO_CHAR(SYSDATE,'YYYY') "+
					"FROM DUAL ");
				
				if(rs.next()){
					m_date_dd=rs.getString(1);
					m_date_mm=rs.getString(2);
					m_date_yy=rs.getString(3);
				}
				
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Advertistment Offers Process</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				
				out.println("var b_flag=0;");
				
				
				out.println("function befor_end(m_obj) {");
				out.println("   m_obj.focus();");
				out.println("}");
				
				
				out.println("function validate_data(){"); 
				out.println("//validations goes here"); 
				
				out.println("if(document.Form1.VAL_DAY.value==\"\" || document.Form1.VAL_MONTH.value==\"\" || document.Form1.VAL_YEAR.value==\"\" ){  "); 
				out.println("VDATE.style.color='red';");
				out.println("return false;"); 
				out.println("}"); 
				out.println("else{"); 
				out.println("return true;"); 
				out.println("}"); 
				out.println("}"); 
				
				
				
				
				out.println("function before_submit(){ "); 
				
				//out.println("alert('records'+document.Form1.hid_no_rec.value);");
				
				out.println("		if(validate_data()){"); 
				out.println("ckeck_data();");
				out.println("if(b_flag==0)");
				
				out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
				out.println("		if(validate_data()){"); 
				out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("document.Form1.elements[i].disabled=false;");
				out.println("}");
				//	out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
				out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Post_Dated_Receipt_Generation';");  
				out.println("		document.Form1.submit();	"); 
				out.println("		}"); 
				out.println("		}"); 
				//out.println("		}"); 
				
				out.println("		}"); 
				out.println("else{");
				out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
				out.println("} "); 
				
				out.println("} "); 
				
				
				
				out.println("function load_lock(){	"); 
				
				
				out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				
				
				out.println("function assign_system_date(){	"); 
				out.println("document.Form1.VAL_DAY.value='"+m_date_dd+"'");
				out.println("document.Form1.VAL_MONTH.value='"+m_date_mm+"'");
				out.println("document.Form1.VAL_YEAR.value='"+m_date_yy+"'");
				m_val_date=m_date_dd+"-"+m_date_mm+"-"+m_date_yy;
				out.println("document.Form1.hid_bank_date.value='"+m_val_date+"'");
				// out.println("get_vehicle_in_hand(document.Form1.hid_bank_date.value);");
				
				// added by udara 08-06-2015
				out.println("document.Form1.VAL_DAY_FROM.value='"+m_date_dd+"'");
				out.println("document.Form1.VAL_MONTH_FROM.value='"+m_date_mm+"'");
				out.println("document.Form1.VAL_YEAR_FROM.value='"+m_date_yy+"'");
				out.println("document.Form1.hid_bank_date_from.value='"+m_val_date+"'"); 
				// end by udara 08-06-2015
				
				out.println("}	"); 
				
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
				
				//out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Start+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("	if(oBj.valout[1] ==\" \"){"); 
				out.println("	clear_data(IfCount);");
				out.println("	}else");
				
				
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				
				out.println("		if(IfCount==\"5\"){"); 
				out.println("		help_value_assign_yard();"); 
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
				
				out.println("	else{");
				out.println("	clear_data(IfCount);");//Added To The Clear The Area Code
				out.println("	}");
				
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
				
				out.println("function clear_data(IfCount) {");
				out.println("		if(IfCount==\"1\"){"); 
				out.println("document.Form1.TXT_REPOSSESSION_CODE.value='';");
				out.println("	}");
				out.println("}");
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_In_Hand?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_In_Hand?chksql=main_page';"); 
				out.println("}"); 
				out.println(""); 
				out.println(""); 
				
				out.println("function save_window(){	"); 
				out.println("before_submit();"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_AF_RE_Vehicle_In_Hand\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\" Collection Process - Vehicle In Hand - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Collection Process - Vehicle In Hand - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				out.println("function load_screen_status(m_val){"); 
				out.println("if(m_val==\"NEW\"){"); 
				out.println("new_window();"); 
				out.println("}"); 
				out.println("else if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
				out.println("}"); 
				out.println("else if(m_val!=\"EDIT\"){"); 
				out.println(" if(confirm(\"Are you sure you want to Delete a record\")){  ");
				out.println("}"); 
				out.println("}"); 
				out.println("else{");
				out.println("}"); 
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				out.println("document.Form1.hid_save_status.value=\"Save\";"); 
				out.println("}else if(m_val==\"EDIT\"){");  
				out.println("document.Form1.hid_status.value=\"Edit\";");  
				out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
				out.println("}else if(m_val==\"DEL\"){");  
				out.println("document.Form1.hid_status.value=\"Delete\";");
				out.println("document.Form1.hid_save_status.value=\"Delete\";"); 
				out.println("}else if(m_val==\"RACT\"){");  
				out.println("document.Form1.hid_status.value=\"Reactivate\";");
				out.println("document.Form1.hid_save_status.value=\"Reactivate\";"); 
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
				
				out.println("function assignState(val){");
				out.println("document.Form1.hid_chk_status.value=val");
				out.println("}");
				
				out.println("var timerID;");
				out.println("var durationID=0;");
				
				out.println("function set_timer_actions() {"); // Add by Amila Sanjaya 2016-05-09
				out.println("   durationID=durationID+1;");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		m_table1.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
			 //	out.println("		clearTimeout(timerID);");
			//	out.println("		m_table1.innerHTML=\"\";");
				out.println("}");
				
				out.println("function get_report_status(val){"); // Add by Amila Sanjaya 2016-05-09
				out.println("		if(val==\"OK\"){");
				out.println("		clearTimeout(timerID);");
			    out.println("		m_table1.innerHTML=\"\";"); 
				out.println("		}");
				out.println("}");	
				
				
				out.println("function get_vehicle_in_hand(val){");
				out.println("assignState('M1');");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_In_Hand?chksql=view&yard_code=\"+document.Form1.TXT_YARD_CODE.value+\"&data_val=\"+val;");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_In_Hand?chksql=view&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"&yard_code=\"+document.Form1.TXT_YARD_CODE.value+\"&repo_status=\"+document.Form1.TXT_REPO_STATUS.value+\"&data_val=\"+val;");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_In_Hand?chksql=view&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"&yard_code=\"+document.Form1.TXT_YARD_CODE.value+\"&repo_status=\"+document.Form1.TXT_REPO_STATUS.value+\"&ro_seizer=\"+document.Form1.hid_CHECK_SEIZER.value+\"&ro_officer=\"+document.Form1.hid_CHECK_OFFICER.value+\"&ro_company=\"+document.Form1.hid_CHECK_COMPANY.value+\"&ro_buyback=\"+document.Form1.hid_CHECK_BUYBACK.value+\"&data_val=\"+val;"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	ro_type
				out.println("load_interface(m_url,'NORM');");
				out.println("   set_timer_actions();");// Add by Amila Sanjaya 2016-05-06 #20241
				//out.println("window.open(m_url);");
				out.println("}"); 
				
				// added by udara 08-06-2015

				out.println("function get_vehicle_in_hand_range(val,m_from_date){");
				//out.println(" alert(m_from_date+'::::'+val); "); 
				out.println("assignState('M2');");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_In_Hand?chksql=view_range&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"&yard_code=\"+document.Form1.TXT_YARD_CODE.value+\"&repo_status=\"+document.Form1.TXT_REPO_STATUS.value+\"&data_val=\"+val+\"&from_date=\"+m_from_date;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_In_Hand?chksql=view_range&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"&yard_code=\"+document.Form1.TXT_YARD_CODE.value+\"&repo_status=\"+document.Form1.TXT_REPO_STATUS.value+\"&ro_seizer=\"+document.Form1.hid_CHECK_SEIZER.value+\"&ro_officer=\"+document.Form1.hid_CHECK_OFFICER.value+\"&ro_company=\"+document.Form1.hid_CHECK_COMPANY.value+\"&ro_buyback=\"+document.Form1.hid_CHECK_BUYBACK.value+\"&data_val=\"+val+\"&from_date=\"+m_from_date;"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	ro_type
				out.println("load_interface(m_url,'NORM');");
				out.println("   set_timer_actions();");// Add by Amila Sanjaya 2016-05-06 #20241
				out.println("}"); 
				
				out.println("function get_vehicle_count_range(val,m_from_date){");
				out.println("assignState('M_COUNT');");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_In_Hand?chksql=count_range&yard_code=\"+document.Form1.TXT_YARD_CODE.value+\"&repo_status=\"+document.Form1.TXT_REPO_STATUS.value+\"&data_val=\"+val+\"&from_date=\"+m_from_date;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_In_Hand?chksql=count_range&yard_code=\"+document.Form1.TXT_YARD_CODE.value+\"&repo_status=\"+document.Form1.TXT_REPO_STATUS.value+\"&ro_seizer=\"+document.Form1.hid_CHECK_SEIZER.value+\"&ro_officer=\"+document.Form1.hid_CHECK_OFFICER.value+\"&ro_company=\"+document.Form1.hid_CHECK_COMPANY.value+\"&ro_buyback=\"+document.Form1.hid_CHECK_BUYBACK.value+\"&data_val=\"+val+\"&from_date=\"+m_from_date;"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 Ro TYPE 	
				out.println("load_interface(m_url,'NORM');");
				out.println("}"); 
				
				// end by udara 08-06-2015
				
				out.println("function get_vehicle_count(val){");
				out.println("assignState('M_COUNT');");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_In_Hand?chksql=count&yard_code=\"+document.Form1.TXT_YARD_CODE.value+\"&repo_status=\"+document.Form1.TXT_REPO_STATUS.value+\"&data_val=\"+val;");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_In_Hand?chksql=count&yard_code=\"+document.Form1.TXT_YARD_CODE.value+\"&repo_status=\"+document.Form1.TXT_REPO_STATUS.value+\"&ro_seizer=\"+document.Form1.hid_CHECK_SEIZER.value+\"&ro_officer=\"+document.Form1.hid_CHECK_OFFICER.value+\"&ro_company=\"+document.Form1.hid_CHECK_COMPANY.value+\"&ro_buyback=\"+document.Form1.hid_CHECK_BUYBACK.value+\"&data_val=\"+val;"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	Ro TYPE 	
				out.println("load_interface(m_url,'NORM');");
				out.println("}"); 
				
				// commented by udara 06-05-2015
				/*
				out.println("function sort_data(m_sort_col) {");
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
				//			out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report?chksql=main_page&generate=detail&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				//			out.println("load_interface(m_url,'NORM');");
				
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_In_Hand?chksql=view&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"&yard_code=\"+document.Form1.TXT_YARD_CODE.value+\"&data_val=\"+document.Form1.hid_bank_date.value;"); // commented by udara 06-05-2015
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_In_Hand?chksql=view&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"&yard_code=\"+document.Form1.TXT_YARD_CODE.value+\"&data_val=\"+document.Form1.hid_bank_date.value+\"&repo_status=\"+document.Form1.TXT_REPO_STATUS.value;"); // added by udara 06-05-2015
				out.println("  window.open(m_url); ");
				out.println("load_interface(m_url,'NORM');");
				
				out.println("}");
				*/
				
				// added by udara 06-05-2015
				out.println("function sort_data(m_sort_col,m_order_by_type) {");
				//out.println("	 m_order_by_type = 'ASC'; ");
				/*
				out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
				out.println("	   if('"+m_order_by_type+"'=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
				out.println("       m_order_by_type = 'DESC'; ");
				out.println("    }");
				
				out.println("  }else{");
				out.println("    m_order_by_type = 'ASC'; ");
				out.println("  }");
				*/
				
				out.println(" m_table.innerHTML = ''; "); // added by udara 07-05-2015
				out.println("assignState('M1');"); // added by udara 07-05-2015
				out.println("	 if(m_order_by_type=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else if(m_order_by_type=='ASC'){");
				out.println("       m_order_by_type = 'DESC'; ");
				out.println("    }");
				
				
				//			out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report?chksql=main_page&generate=detail&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				//			out.println("load_interface(m_url,'NORM');");
				
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_In_Hand?chksql=view&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"&yard_code=\"+document.Form1.TXT_YARD_CODE.value+\"&data_val=\"+document.Form1.hid_bank_date.value;"); // commented by udara 06-05-2015
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_In_Hand?chksql=view&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"&yard_code=\"+document.Form1.TXT_YARD_CODE.value+\"&data_val=\"+document.Form1.hid_bank_date.value+\"&repo_status=\"+document.Form1.TXT_REPO_STATUS.value;"); // added by udara 06-05-2015
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_In_Hand?chksql=view&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"&yard_code=\"+document.Form1.TXT_YARD_CODE.value+\"&data_val=\"+document.Form1.hid_bank_date.value+\"&ro_seizer=\"+document.Form1.hid_CHECK_SEIZER.value+\"&ro_officer=\"+document.Form1.hid_CHECK_OFFICER.value+\"&ro_company=\"+document.Form1.hid_CHECK_COMPANY.value+\"&ro_buyback=\"+document.Form1.hid_CHECK_BUYBACK.value+\"&repo_status=\"+document.Form1.TXT_REPO_STATUS.value;"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	ro_type
				//out.println("  window.open(m_url); ");
				out.println("load_interface(m_url,'NORM');");
				
				out.println("}");
				
				// added by udara 29-06-2015
				
				out.println("function sort_data_range(m_sort_col,m_order_by_type) {");
				
				
				out.println(" m_table.innerHTML = ''; "); // added by udara 07-05-2015
				out.println("assignState('M2');"); // added by udara 07-05-2015
				out.println("	 if(m_order_by_type=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else if(m_order_by_type=='ASC'){");
				out.println("       m_order_by_type = 'DESC'; ");
				out.println("    }");

				out.println(" m_from_date = document.Form1.VAL_DAY_FROM.value +'-'+document.Form1.VAL_MONTH_FROM.value +'-'+document.Form1.VAL_YEAR_FROM.value; "); 
				
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_In_Hand?chksql=view&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"&yard_code=\"+document.Form1.TXT_YARD_CODE.value+\"&data_val=\"+document.Form1.hid_bank_date.value;"); // commented by udara 06-05-2015
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Vehicle_In_Hand?chksql=view_range&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"&yard_code=\"+document.Form1.TXT_YARD_CODE.value+\"&data_val=\"+document.Form1.hid_bank_date.value+\"&repo_status=\"+document.Form1.TXT_REPO_STATUS.value+\"&from_date=\"+m_from_date;"); // added by udara 06-05-2015
				//out.println("  window.open(m_url); ");
				out.println("load_interface(m_url,'NORM');");
				
				out.println("}");
				
				// end by udara 29-06-2015
				
				
				out.println("function get_vector_normal(http_response) {");
			   out.println("get_report_status(document.Form1.hid_report_status.value);");// Add by Amila Sanjaya 2016-05-09
				out.println("	if(document.Form1.hid_chk_status.value=='M1' ){");
				out.println(" m_table.innerHTML = ''; ");
				out.println(" m_table.innerHTML = http_response; ");
				out.println("get_vehicle_count(document.Form1.hid_bank_date.value);");
				out.println("}");
				
				// added by udara 08-06-2015
				out.println("	if(document.Form1.hid_chk_status.value=='M2' ){");
				out.println("      m_table.innerHTML = ''; ");
				out.println("      m_table.innerHTML = http_response; ");
				out.println("      var m_from_date = document.Form1.VAL_DAY_FROM.value+'-'+document.Form1.VAL_MONTH_FROM.value+'-'+document.Form1.VAL_YEAR_FROM.value;  ");
				out.println("      var m_to_date   = document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;  ");
				out.println("      get_vehicle_count_range(m_to_date,m_from_date);");
				out.println("   }");
				// end by udara 08-06-2015
				
				out.println("	else if(document.Form1.hid_chk_status.value=='M_COUNT' ){");
				out.println(" m_table_count.innerHTML = ''; ");
				out.println(" m_table_count.innerHTML = http_response; ");
				out.println("}");
				
				out.println("}");
				
				
				out.println("function check_date(){ ");
				out.println("var date='' ");
				out.println(" if((document.Form1.VAL_DAY.value !=\"\")&&(document.Form1.VAL_MONTH.value !=\"\")&&(document.Form1.VAL_YEAR.value !=\"\")){");
				out.println("  if(checkMonthLength(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)){");
				out.println("date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("document.Form1.hid_bank_date.value=date");
				//out.println("get_vehicle_in_hand(document.Form1.hid_bank_date.value);");
				out.println(" }");
				out.println(" }");
				out.println("}");
				
				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
				
				out.println("function load_c_date(val) {");
				out.println("var date='' ");
				//	out.println("alert('date valaue'+val);");
				out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
				
				// added by udara 08-06-2015
				//out.println("alert(document.Form1.hid_cal_date.value);");
				
				out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
				out.println("v_date=val.substr(0,val.indexOf('-'));");
				out.println("if(v_date.length<2)");
				out.println("v_date=0+v_date");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("v_month=val.substr(0,val.indexOf('-'));");
				out.println("if(v_month.length<2)");
				out.println("v_month=0+v_month");
				
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("     document.Form1.VAL_DAY_FROM.value=v_date;");
				out.println("     document.Form1.VAL_MONTH_FROM.value=v_month;");
				out.println("     document.Form1.VAL_YEAR_FROM.value=val;");
				out.println("date=v_date+'-'+v_month+'-'+val;");
				out.println("document.Form1.hid_bank_date_from.value=date");
				out.println("  }");	
				
				// end by udara 08-06-2015
				
				
				out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
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
				out.println("date=v_date+'-'+v_month+'-'+val;");
				out.println("document.Form1.hid_bank_date.value=date");
				//	out.println("get_post_dated_cheques(document.Form1.hid_bank_date.value);");
				out.println("  }");				
				out.println("}");
				
				//out.println("alert(document.Form1.hid_bank_date_from.value);");
				
				
				
				out.println("}");
				
				out.println("function help_button_View(val) {");
				out.println("if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("get_vehicle_in_hand(document.Form1.hid_bank_date.value);");
				out.println("}");		
				out.println("else");		
				out.println("{");		
				out.println("  VDATE.style.color='red';");
				out.println("}");		
				out.println("}");
				
				// added by udara 08-06-2015
				
				out.println("function help_button_View_Range(val) {");
				//out.println("   alert('help_button_View_Range'); ");
				
				out.println("   var m_from_date = document.Form1.VAL_DAY_FROM.value+'-'+document.Form1.VAL_MONTH_FROM.value+'-'+document.Form1.VAL_YEAR_FROM.value; ");
				out.println("   var m_to_date   = document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value; ");
				out.println("   if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\" && document.Form1.VAL_DAY_FROM.value!=\"\" && document.Form1.VAL_MONTH_FROM.value!=\"\" && document.Form1.VAL_YEAR_FROM.value!=\"\"){ ");
				out.println("       get_vehicle_in_hand_range(m_to_date,m_from_date);");
				out.println("   }");		
				out.println("   else{ ");				
				out.println("       VDATE.style.color='red';");
				out.println("   }");
			
				out.println("}");
				
				// end by udara 08-06-2015
				
				out.println("function change_val_receipt_status(obj){")	;
				out.println("if(obj.checked==true){");
				out.println("obj.value='on'");
				out.println("}else if(obj.checked==false){");
				out.println("obj.value='off'");
				out.println("}");
				out.println("}");			
				
				out.println("function help_button_yard() {"); 
				out.println("    Crit = document.Form1.TXT_YARD_CODE.value+\"@Y@\";"); 
				//out.println("    HelpBox('1','10','6',Crit,'m_help_TXT_YARD_CODE_sql','5');"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_YARD_CODE_sql','5');"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function help_value_assign_yard() {"); 
				out.println("    document.Form1.TXT_YARD_CODE.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_YARD_NAME.value=oBj.valout[3];"); 
				out.println("}"); 
				
				
				out.println("function makeRequest(obj) {");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_Yard_code&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println("load_interface(m_url,'XML');");
				out.println("}");
				
				// ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 		 
				out.println("function ro_type(m_ro_type) {");
				//out.println("alert('m_ro_type'+m_ro_type)");
				out.println("  if(m_ro_type=='SEIZER'){ ");
				out.println("     if(document.Form1.TXT_TYPE_SEIZER.checked==true){");
				out.println("       document.Form1.hid_CHECK_SEIZER.value=m_ro_type;"); 
				out.println("	   }else{");
				out.println("       document.Form1.hid_CHECK_SEIZER.value='';"); 
				out.println("	   }");
				out.println("	 }");
				out.println("  if(m_ro_type=='OFFICER'){ ");
				out.println("     if(document.Form1.TXT_TYPE_OFFICER.checked==true){");
				out.println("       document.Form1.hid_CHECK_OFFICER.value=m_ro_type;"); 
				out.println("	   }else{");
				out.println("       document.Form1.hid_CHECK_OFFICER.value='';"); 
				out.println("	   }");
				out.println("	 }");
				out.println("  if(m_ro_type=='COMPANY'){ ");
				out.println("     if(document.Form1.TXT_TYPE_COMPANY.checked==true){");
				out.println("       document.Form1.hid_CHECK_COMPANY.value=m_ro_type;"); 
				out.println("	   }else{");
				out.println("       document.Form1.hid_CHECK_COMPANY.value='';"); 
				out.println("	   }");
				out.println("	 }");
				out.println("  if(m_ro_type=='BUYBACK'){ ");
				out.println("     if(document.Form1.TXT_TYPE_BUYBACK.checked==true){");
				out.println("       document.Form1.hid_CHECK_BUYBACK.value=m_ro_type;"); 
				out.println("	   }else{");
				out.println("       document.Form1.hid_CHECK_BUYBACK.value='';"); 
				out.println("	   }");
				out.println("	 }");
				//out.println("alert('m_ro_type2'+document.Form1.hid_CHECK_SEIZER.value)");
				out.println(" }");
				// END  BY A/S ON 26-08-2021 FOR JB20072021-14503 		 
				
				out.println("function get_vector(data_vec) {");
				//out.println("alert(data_vec)");
				out.println("			if(data_vec.length==0 &&  document.Form1.TXT_YARD_CODE.value!=\"\"){");
				out.println("     help_button_yard();");
				out.println("			}");
				out.println("			else if(data_vec.length>0 ){");
				out.println("    document.Form1.TXT_YARD_CODE.value=data_vec[0];"); 
				out.println("    document.Form1.TXT_YARD_NAME.value=data_vec[1];"); 
				out.println("			}");
				
				out.println("			}");
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"assign_system_date()\"> "); //load_lock(), header(),add_row() assign_system_date()
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_POST_DATED_RECEIPT_GENERATION\">"); 
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
				out.println("<input type=hidden name='hid_cal_date' value=\"\"></td>");
				out.println("<input type=hidden name='hid_row_no' value=\"\"></td>");
				out.println("<INPUT TYPE='Hidden' NAME='hid_bank_date' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_bank_date_from' VALUE=\"\">"); // added by udara 08-06-2015
				//out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=0>"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_report_status' VALUE=\"OK\">"); // Add by Amila Sanjaya 2016-05-09
				
				out.println("<INPUT TYPE='Hidden' NAME='hid_CHECK_SEIZER' VALUE=\"\">"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				out.println("<INPUT TYPE='Hidden' NAME='hid_CHECK_OFFICER' VALUE=\"\">"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				out.println("<INPUT TYPE='Hidden' NAME='hid_CHECK_COMPANY' VALUE=\"\">"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				out.println("<INPUT TYPE='Hidden' NAME='hid_CHECK_BUYBACK' VALUE=\"\">"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				
				
				
				
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Vehicle In Hand</td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				
				
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				//	out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
				//	out.println("<td width='10%'></td>");
				//		out.println("<td width='10%'></td>");
				//out.println("<td width='10%' align='center'><input type=\"button\" name='btn_delete' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
				//out.println("<td width='10%'></td>");  
				out.println("<td width='6%'></td>");  
				//	out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Letter\");'  onclick='View_Letter()' value=\"Letter\"></td>"); 
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				
				out.println("<table align='center' width='100%' class='table' border='0'>"); 
				
				out.println("<br>");  	
			
				out.println("<tr class=tr_input>");
				out.println("<td width='20%' ID=VDATE>From Date</td>");
				out.println("<td width='18%' >");
		        out.println("    <input name=\"VAL_DAY_FROM\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY_FROM,document.Form1.VAL_MONTH_FROM,document.Form1.VAL_YEAR_FROM)> ");
				out.println("    <input name=\"VAL_MONTH_FROM\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY_FROM,document.Form1.VAL_MONTH_FROM,document.Form1.VAL_YEAR_FROM)> ");
				out.println("    <input name=\"VAL_YEAR_FROM\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY_FROM,document.Form1.VAL_MONTH_FROM,document.Form1.VAL_YEAR_FROM)><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a> ");
				out.println("</td>");
				//out.println("<td width='10%' ><input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"help_button_View()\"></td>"); 
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%' ID=VDATE>Date as at (To Date)</td>");
				out.println("<td width='18%' ><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("</td>");
				//out.println("<td width='10%' ><input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"help_button_View()\"></td>"); 
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				
				out.println("</table>"); 
				
				out.println("<table align='center' width='100%' class='table' border='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_YARD_CODE'  class=div_input>Yard Code </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_YARD_CODE' maxlength='10'  size='10' onblur=\"makeRequest(document.Form1.TXT_YARD_CODE)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_YARD_CODE' value=\"Help\"   onClick=\"help_button_yard()\">"); 
				out.println("<input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"help_button_View()\">");
			    out.println("<input class='but_input' type='button' name='BUT_VIEW_RANGE' value=\"View Date Range\" onClick=\"help_button_View_Range()\" style=\"width:100px\" >");
			    out.println("</td>"); 
				out.println("</tr>"); 
				
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_YARD_NAME'  class=div_input>Yard Name </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_YARD_NAME' maxlength='100' style=\"width:250px;\"  size='100' onblur=\"\">"); 
				out.println("</td>"); 
				out.println("</tr>");
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_YARD_NAME'  class=div_input>Repossession Status </DIV></td>"); 
				out.println("<td width='*%' ><SELECT class='txt_input'  name='TXT_REPO_STATUS' maxlength='100' style=\"width:250px;\"   onblur=\"\"><option value='0'>All</option><option value='3'>Vehicle Inventory Approved</option><option value='4'>Valuation Completed</option><option value='5'>Advertisement Generated</option><option value='6'>Offers Entered</option><option value='7'>Offers accepted</option></select>"); 
				out.println("</td>"); 
				out.println("</tr>");
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_RO_TYPE'  class=div_input>Ro Type </DIV></td>"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 		 
				//out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_RO_TYPE' maxlength='100' style=\"width:250px;\"  size='100' onblur=\"\">"); 
				out.println("<td width='*%'>"); 
				out.println("<input id='TXT_TYPE_SEIZER' name='TXT_TYPE_SEIZER'    type='checkbox' value='SEIZER'  onclick=ro_type('SEIZER');><label for='CHECK_SEIZER'>Seizer</label> "); 
				out.println("<input id='TXT_TYPE_OFFICER' name='TXT_TYPE_OFFICER'  type='checkbox' value='OFFICER' onclick=ro_type('OFFICER');><label for='CHECK_OFFICER'>Officer</label> "); 
				out.println("<input id='TXT_TYPE_COMPANY' name='TXT_TYPE_COMPANY'  type='checkbox' value='COMPANY' onclick=ro_type('COMPANY');><label for='CHECK_COMPANY'>Company</label> "); 
				out.println("<input id='TXT_TYPE_BUYBACK' name='TXT_TYPE_BUYBACK'  type='checkbox' value='BUYBACK' onclick=ro_type('BUYBACK');><label for='CHECK_BUYBACK'>Buy Back</label> "); 
			    out.println("</td>"); 
				out.println("</tr>");
				out.println("</table>"); 
				
				
				
				
				
				
				
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table_count'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>"); 
				
				
				out.println("<table align='center' width='100%' class='table'>"); 
				
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>"); 
				
				out.println("<table align='center' width='100%' class='table'>"); //Add by Amila Sanjaya 2016-05-09
				
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table1'></DIV></td>");
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
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				out.flush();
			}
			
			else if(m_chksql.equals("view")){	
				
				
				String m_date=req.getParameter("data_val");
				String m_yard_code=req.getParameter("yard_code"); //added by nuwan de silva on 02-11-07
				double rep_total=0;
				String m_sort_column = req.getParameter("sort_column");
				String m_order_by_type = req.getParameter("order_by_type");
				String m_repo_status = req.getParameter("repo_status");
				
				String m_ro_seizer = req.getParameter("ro_seizer");   // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				String m_ro_officer = req.getParameter("ro_officer"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				String m_ro_company = req.getParameter("ro_company"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				String m_ro_buyback = req.getParameter("ro_buyback"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				
				String m_filter = "";
				
				// ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				String m_ro_filter_sql = " ";
				String m_ro_filter = "";
				
				if(!m_ro_seizer.equals("") && m_ro_seizer!=null){
					m_ro_filter=m_ro_filter+"'"+m_ro_seizer+"',";
				}
				
				if(!m_ro_officer.equals("") && m_ro_officer!=null){
					m_ro_filter=m_ro_filter+"'"+m_ro_officer+"',";
				}
				if(!m_ro_company.equals("") && m_ro_company!=null){
					m_ro_filter=m_ro_filter+"'"+m_ro_company+"',";
				}
				if(!m_ro_buyback.equals("") && m_ro_buyback!=null){
					m_ro_filter=m_ro_filter+"'"+m_ro_buyback+"',";
				}
				if(m_ro_filter.indexOf(",") != -1){
				    m_ro_filter = m_ro_filter.substring(0,m_ro_filter.length() - 1);
				}
				if(!m_ro_filter.equals("") && m_ro_filter!=null){
				    
					m_ro_filter_sql="AND A.REPOSSESS_TYPE IN("+m_ro_filter+")";
				 }

				
				// added by udara 08-05-2015
				if(!m_repo_status.equals("0")){
					m_filter = " AND "+m_schema_name+".AF_GET_REPO_STATUS(A.REPOSSESSION_NO) ="+m_repo_status+" ";
				}
				
				// added by udara 11-09-2018
				String m_yard_code_sql = " ";
				
				if(!m_yard_code.equals("") && m_yard_code!=null){
					m_yard_code_sql = " AND B.YARD_CODE = '"+m_yard_code+"' "; 
				}
				else{
					m_yard_code_sql = " ";
				}
				
				//out.println(" "+
				rs = stmt.executeQuery (" "+
					//" SELECT DISTINCT "+
					" SELECT  "+
					"	A.INVENTORY_NO INVENTORY_NO, "+  //1
					"	A.FINANCE_NO FINANCE_NO, "+ //2
					"	B.CLIENT_CODE CLIENT_CODE, "+ //3
					"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME, "+ //4
					"	B.VEHICLE_NO VEHICLE_NO, "+ //5
					"	A.SEIZER_CODE SEIZER_CODE, "+ //6
					"	"+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE) SEIZER_NAME, "+ //7
					"   DECODE("+m_schema_name+".AF_GET_REPO_STATUS2(A.REPOSSESSION_NO,'"+m_date+"'),0,'-',1,'Repossession Order Issued',2,'Vehicle Inventory Pending Approval',3,'Vehicle Inventory Approved',4,'Valuation Completed',5,'Advertisement Generated',6,'Offers Entered',7,'Offers accepted',8,'Repossession Order Deleted',9,'Repossession Order Reversed') APP_STATUS , "+
					"	A.REPOSSESSION_NO REPOSSESSION_NO, "+ //9
					"	NVL(TO_CHAR(A.TRN_DATE,'DD-MM-YYYY'),'-') TRN_DATE, "+ //10
					//"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'-') ENT_DATE, "+ // 11 
					"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'-') ENT_DATE, "+ // 11 
					"	NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),'-') EFF_VAL_DATE, "+ //12
					"	C.APPLICATION_NO APPLICATION_NO, "+ //13
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',C.APPLICATION_NO),0) ASSET_COUNT,   "+ //14
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',C.APPLICATION_NO),0) VALUATION_COUNT,  "+ //15
					//"	NVL("+m_schema_name+".AF_CO_GET_CAP_OUT_STD_AMT(C.APPLICATION_NO),0) CAPITAL_OUTSTANDING,  "+ //16
					"	0 CAPITAL_OUTSTANDING,  "+ //16
					//"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17 
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17 
					"   NVL("+m_schema_name+".AF_RENTAL_FREEZ_DATE(A.FINANCE_NO),'-'),    "+ // 18 
					"   NVL("+m_schema_name+".AF_CO_GET_YARD_NAME(B.YARD_CODE),'-') YARD_NAME, "+ // added by udara 12-04-2021
					"   NVL(DECODE(A.REPOSSESS_TYPE,'SEIZER','Seizer','OFFICER','Officer','COMPANY','Company','BUYBACK','Buy Back','-'),'-') "+ // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	//20
					"	FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					"	WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND A.FINANCE_NO=C.FINANCE_NO AND  "+
					"	A.ACTIVE_STATUS IN ('C','Y') AND B.ACTIVE_STATUS='APP'  "+
					//"   AND NVL(B.YARD_CODE,' ') LIKE UPPER('"+m_yard_code+"%')  "+ 
					m_yard_code_sql +
					//"   AND TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					"   AND TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					" AND A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE ACTIVE_STATUS = 'TERM_CHECK' AND APPLY_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY'))"+
					" AND  A.REPOSSESSION_NO NOT IN (SELECT REF FROM AF_RE_REPOSSESSION_STATUS WHERE STATUS = 'Vehicle Release' AND TRUNC(ENTDATE) <= TO_DATE('"+m_date+"','DD-MM-YYYY') ) "+ 
					m_filter +
					m_ro_filter_sql +
					
					" UNION  "+
					
					//"SELECT DISTINCT "+
					"SELECT  "+
					"	A.INVENTORY_NO INVENTORY_NO, "+  //1
					"	A.FINANCE_NO FINANCE_NO, "+ //2
					"	B.CLIENT_CODE CLIENT_CODE, "+ //3
					"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME, "+ //4
					"	B.VEHICLE_NO VEHICLE_NO, "+ //5
					"	A.SEIZER_CODE SEIZER_CODE, "+ //6
					"	"+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE) SEIZER_NAME, "+ //7
					"   DECODE("+m_schema_name+".AF_GET_REPO_STATUS2(A.REPOSSESSION_NO,'"+m_date+"'),0,'-',1,'Repossession Order Issued',2,'Vehicle Inventory Pending Approval',3,'Vehicle Inventory Approved',4,'Valuation Completed',5,'Advertisement Generated',6,'Offers Entered',7,'Offers accepted',8,'Repossession Order Deleted',9,'Repossession Order Reversed') APP_STATUS , "+
					"	A.REPOSSESSION_NO REPOSSESSION_NO, "+ //9
					"	NVL(TO_CHAR(A.TRN_DATE,'DD-MM-YYYY'),'-') TRN_DATE, "+ //10
					//"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'-') ENT_DATE, "+ // 11 
					"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'-') ENT_DATE, "+ // 11 
					"	NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),'-') EFF_VAL_DATE, "+ //12
					"	C.APPLICATION_NO APPLICATION_NO, "+ //13
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',C.APPLICATION_NO),0) ASSET_COUNT,   "+ //14
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',C.APPLICATION_NO),0) VALUATION_COUNT,  "+ //15
					//"	NVL("+m_schema_name+".AF_CO_GET_CAP_OUT_STD_AMT(C.APPLICATION_NO),0) CAPITAL_OUTSTANDING,  "+ //16
					"	0 CAPITAL_OUTSTANDING,  "+ //16
					//"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17 
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17 
					"   NVL("+m_schema_name+".AF_RENTAL_FREEZ_DATE(A.FINANCE_NO),'-'),    "+ // 18 
					"   NVL("+m_schema_name+".AF_CO_GET_YARD_NAME(B.YARD_CODE),'-') YARD_NAME, "+ // added by udara 12-04-2021
					"   NVL(DECODE(A.REPOSSESS_TYPE,'SEIZER','Seizer','OFFICER','Officer','COMPANY','Company','BUYBACK','Buy Back','-'),'-') "+ // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	//20
					"	FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					"	WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND A.FINANCE_NO=C.FINANCE_NO AND  "+
					//"	A.ACTIVE_STATUS IN ('C','R') AND  B.ACTIVE_STATUS='APP' AND "+
					"	A.ACTIVE_STATUS IN ('R') AND  B.ACTIVE_STATUS='APP'  "+
					//"   AND NVL(B.YARD_CODE,' ') LIKE UPPER('"+m_yard_code+"%')  "+ 
					m_yard_code_sql +
					"   AND TO_DATE(TO_CHAR(NVL(A.MOD_DATE,B.ENT_DATE),'DD-MM-YYYY'),'DD-MM-YYYY')> TO_DATE('"+m_date+"','DD-MM-YYYY') AND "+
					//"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					" AND A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE ACTIVE_STATUS = 'TERM_CHECK' AND APPLY_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY'))"+
					" AND  A.REPOSSESSION_NO NOT IN (SELECT REF FROM AF_RE_REPOSSESSION_STATUS WHERE STATUS = 'Vehicle Release' AND TRUNC(ENTDATE) <= TO_DATE('"+m_date+"','DD-MM-YYYY') ) "+
					m_filter +					
					m_ro_filter_sql +
					
					" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");

				
				
				out.println("<br>");			
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
				
				out.println("<br>");			
				
				out.println("<tr class=tr_input>");
				out.println("<td colspan=11 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("</tr>");
				
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td  width='1%' align='left'   style= cursor:hand; >No</td>");
				out.println("<td  width='10%' align='left'   style= cursor:hand; title='Click here to sort by - Repossession No' onclick=sort_data('REPOSSESSION_NO','"+m_order_by_type+"')>Repossession No</td>");
				out.println("<td  width='10%' align='left'   style= cursor:hand; title='Click here to sort by - Inventory No'    onclick=sort_data('INVENTORY_NO','"+m_order_by_type+"')>Inventory No</td>");
				out.println("<td  width='10%' align='left'   style= cursor:hand; title='Click here to sort by - Finance No'      onclick=sort_data('FINANCE_NO','"+m_order_by_type+"')>Finance No</td>");
				out.println("<td  width='10%' align='left'   style= cursor:hand; title='Click here to sort by - Client Name'     onclick=sort_data('CLIENT_CODE','"+m_order_by_type+"')>Client Name</td>");
				out.println("<td  width='5%' align='left'   style= cursor:hand; title='Click here to sort by - Vehicle No'      onclick=sort_data('VEHICLE_NO','"+m_order_by_type+"')>Vehicle No</td>");
				out.println("<td  width='10%' align='left'   style= cursor:hand; title='Click here to sort by - Seizer Name'     onclick=sort_data('SEIZER_NAME','"+m_order_by_type+"')>Seizer Name</td>");
				out.println("<td  width='10%' align='left'   style= cursor:hand; title='Click here to sort by - Inventory Date'  onclick=sort_data('MOD_DATE','"+m_order_by_type+"')>Inventory Date</td>"); 
				out.println("<td  width='5%' align='right'  style= cursor:hand; title='Click here to sort by - Capital O/S'     onclick=sort_data('CAPITAL_OUTSTANDING','"+m_order_by_type+"')>Capital O/S</td>");
				out.println("<td  width='5%'  align='center' style= cursor:hand; title='Click here to sort by - Total Asset'     onclick=sort_data('ASSET_COUNT','"+m_order_by_type+"')>Total Asset</td>");
				out.println("<td  width='5%'  align='center' style= cursor:hand; title='Click here to sort by - Total Valuation' onclick=sort_data('VALUATION_COUNT','"+m_order_by_type+"')>Total Valuation</td>");
				out.println("<td  width='10%' align='left'   style= cursor:hand; title='Click here to sort by - Status'          onclick=sort_data('APP_STATUS','"+m_order_by_type+"')>Status</td>");			    
				out.println("<td  width='5%'  align='left'   style= cursor:hand;              >Effective Freeze Date</td>"); // added by udara 17-02-2017
				out.println("<td  width='10%' align='left'             >Yard</td>"); // added by udara 12-04-2021
				out.println("<td  width='5%'  align='left'             >Ro Type</td>");  // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				
				out.println("</tr >"); 
				
				
				int j=0;
				while(rs.next()){
					
					
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 >");
					}
					else{
						out.println("<tr class=tr_input >");
					}
					
					out.println("<td width='1%' style= cursor:hand;  align='left' >"+(j+1) +"</td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_repossession_drill('"+rs.getString(9)+"')\" align='left' ><u>"+rs.getString(9) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_inventory_drill('"+rs.getString(1)+"')\" align='left'><u>"+rs.getString(1) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_transaction_history_by_contract('"+rs.getString(2)+"','"+rs.getString(3)+"')\" align='left'><u>"+rs.getString(2) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_client('"+rs.getString(3)+"')\" ><u>"+rs.getString(4) +"</u></td>");
					out.println("<td width='5%' align='left'>"+rs.getString(5) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(7) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(11) +"</td>");
					out.println("<td width='5%' align='right'>"+nf.format(rs.getDouble(16)) +"</td>");
					out.println("<td width='5%'  align='center'>"+rs.getInt(14) +"</td>");
					out.println("<td width='5%'  align='center'>"+rs.getInt(15) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(8) +"</td>");
					out.println("<td width='5%' align='left'>"+rs.getString(18) +"</td>"); // added by udara 17-02-2017
					out.println("<td width='5%' align='left'>"+rs.getString("YARD_NAME") +"</td>");  // added by udara 12-04-2021
					out.println("<td width='5%' align='left'>"+rs.getString(20) +"</td>"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503
					out.println("</tr>");
					
					
					
					j=j+1;
					
					
				}	
				
				// end by udara 08-05-2015
				
				
				// ==================================== TERMINATED CONTRACTS ==========================================================
				
					
				if(!m_repo_status.equals("0")){
					m_filter = " AND "+m_schema_name+".AF_GET_REPO_STATUS(A.REPOSSESSION_NO) ="+m_repo_status+" ";
				}
				
				
				// added by udara 05-06-2015
				
				//out.println(" "+
				rs = stmt.executeQuery (" "+
					//" SELECT DISTINCT "+
					" SELECT  "+
					"	A.INVENTORY_NO INVENTORY_NO, "+  //1
					"	A.FINANCE_NO FINANCE_NO, "+ //2
					"	B.CLIENT_CODE CLIENT_CODE, "+ //3
					"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME, "+ //4
					"	B.VEHICLE_NO VEHICLE_NO, "+ //5
					"	A.SEIZER_CODE SEIZER_CODE, "+ //6
					"	"+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE) SEIZER_NAME, "+ //7
					"   DECODE("+m_schema_name+".AF_GET_REPO_STATUS2(A.REPOSSESSION_NO,'"+m_date+"'),0,'-',1,'Repossession Order Issued',2,'Vehicle Inventory Pending Approval',3,'Vehicle Inventory Approved',4,'Valuation Completed',5,'Advertisement Generated',6,'Offers Entered',7,'Offers accepted',8,'Repossession Order Deleted',9,'Repossession Order Reversed') APP_STATUS , "+
					"	A.REPOSSESSION_NO REPOSSESSION_NO, "+ //9
					"	NVL(TO_CHAR(A.TRN_DATE,'DD-MM-YYYY'),'-') TRN_DATE, "+ //10
					//"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'-') ENT_DATE, "+ // 11 
					"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'-') ENT_DATE, "+ // 11 
					"	NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),'-') EFF_VAL_DATE, "+ //12
					"	C.APPLICATION_NO APPLICATION_NO, "+ //13
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',C.APPLICATION_NO),0) ASSET_COUNT,   "+ //14
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',C.APPLICATION_NO),0) VALUATION_COUNT,  "+ //15
					//"	NVL("+m_schema_name+".AF_CO_GET_CAP_OUT_STD_AMT(C.APPLICATION_NO),0) CAPITAL_OUTSTANDING,  "+ //16
					"	0 CAPITAL_OUTSTANDING,  "+ //16
					//"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17 
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17 
					"   NVL("+m_schema_name+".AF_RENTAL_FREEZ_DATE(A.FINANCE_NO),'-'),    "+ // 18 
					"   NVL("+m_schema_name+".AF_CO_GET_YARD_NAME(B.YARD_CODE),'-') YARD_NAME, "+ // added by udara 12-04-2021
					"   NVL(DECODE(A.REPOSSESS_TYPE,'SEIZER','Seizer','OFFICER','Officer','COMPANY','Company','BUYBACK','Buy Back','-'),'-') "+ // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	//20
					"	FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					"	WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND A.FINANCE_NO=C.FINANCE_NO   "+
					"	AND A.ACTIVE_STATUS IN ('C','Y') AND B.ACTIVE_STATUS='APP'  "+
					//"   AND NVL(B.YARD_CODE,' ') LIKE UPPER('"+m_yard_code+"%')  "+
					m_yard_code_sql +
					" AND A.FINANCE_NO  IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE ACTIVE_STATUS = 'TERM_CHECK' AND APPLY_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY'))"+
					" AND  A.REPOSSESSION_NO NOT IN (SELECT REF FROM AF_RE_REPOSSESSION_STATUS WHERE STATUS = 'Vehicle Release' AND TRUNC(ENTDATE) <= TO_DATE('"+m_date+"','DD-MM-YYYY') ) "+ // added by udara 27-08-2018
					m_filter +
					m_ro_filter_sql +
					
					" UNION   "+
					
					//" SELECT DISTINCT "+
					" SELECT  "+
					"	A.INVENTORY_NO INVENTORY_NO, "+  //1
					"	A.FINANCE_NO FINANCE_NO, "+ //2
					"	B.CLIENT_CODE CLIENT_CODE, "+ //3
					"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME, "+ //4
					"	B.VEHICLE_NO VEHICLE_NO, "+ //5
					"	A.SEIZER_CODE SEIZER_CODE, "+ //6
					"	"+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE) SEIZER_NAME, "+ //7
					"   DECODE("+m_schema_name+".AF_GET_REPO_STATUS2(A.REPOSSESSION_NO,'"+m_date+"'),0,'-',1,'Repossession Order Issued',2,'Vehicle Inventory Pending Approval',3,'Vehicle Inventory Approved',4,'Valuation Completed',5,'Advertisement Generated',6,'Offers Entered',7,'Offers accepted',8,'Repossession Order Deleted',9,'Repossession Order Reversed') APP_STATUS , "+
					"	A.REPOSSESSION_NO REPOSSESSION_NO, "+ //9
					"	NVL(TO_CHAR(A.TRN_DATE,'DD-MM-YYYY'),'-') TRN_DATE, "+ //10
					//"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'-') ENT_DATE, "+ // 11
					"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'-') ENT_DATE, "+ // 11
					"	NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),'-') EFF_VAL_DATE, "+ //12
					"	C.APPLICATION_NO APPLICATION_NO, "+ //13
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',C.APPLICATION_NO),0) ASSET_COUNT,   "+ //14
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',C.APPLICATION_NO),0) VALUATION_COUNT,  "+ //15
					//"	NVL("+m_schema_name+".AF_CO_GET_CAP_OUT_STD_AMT(C.APPLICATION_NO),0) CAPITAL_OUTSTANDING,  "+ //16
					"	0 CAPITAL_OUTSTANDING,  "+ //16
					//"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17 
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17 
					"   NVL("+m_schema_name+".AF_RENTAL_FREEZ_DATE(A.FINANCE_NO),'-'),    "+ // 18 
					"   NVL("+m_schema_name+".AF_CO_GET_YARD_NAME(B.YARD_CODE),'-') YARD_NAME, "+ // added by udara 12-04-2021
					"   NVL(DECODE(A.REPOSSESS_TYPE,'SEIZER','Seizer','OFFICER','Officer','COMPANY','Company','BUYBACK','Buy Back','-'),'-') "+ // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	//20
					"	FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					"	WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND A.FINANCE_NO=C.FINANCE_NO AND  "+
					//"	A.ACTIVE_STATUS IN ('C','R') AND B.ACTIVE_STATUS='APP' AND "+
					"	A.ACTIVE_STATUS IN ('R') AND B.ACTIVE_STATUS='APP'  "+
					//"   AND NVL(B.YARD_CODE,' ') LIKE UPPER('"+m_yard_code+"%')  "+ 
					m_yard_code_sql +
					" AND A.FINANCE_NO  IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE ACTIVE_STATUS = 'TERM_CHECK' AND APPLY_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY'))"+
					" AND  A.REPOSSESSION_NO NOT IN (SELECT REF FROM AF_RE_REPOSSESSION_STATUS WHERE STATUS = 'Vehicle Release' AND TRUNC(ENTDATE) <= TO_DATE('"+m_date+"','DD-MM-YYYY') ) "+ // added by udara 27-08-2018
					m_filter +
					m_ro_filter_sql +
					
					" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
				
				// end by udara 05-06-2015
				
				 j=0;
					if(j==0){
						out.println("<tr><td colspan='12' align='center'>&nbsp;</td></tr>");
						out.println("<tr><td colspan='12' align='center' style='font-size=14px;'><b><u>Terminated Contracts</u></b></td></tr>");
						out.println("<tr><td colspan='12' align='center'>&nbsp;</td></tr>");
					}
				while(rs.next()){
					
					
					
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 >");
					}
					else{
						out.println("<tr class=tr_input >");
					}
					
					out.println("<td width='1%' style= cursor:hand;  align='left' >"+(j+1) +"</td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_repossession_drill('"+rs.getString(9)+"')\" align='left' ><u>"+rs.getString(9) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_inventory_drill('"+rs.getString(1)+"')\" align='left'><u>"+rs.getString(1) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_transaction_history_by_contract('"+rs.getString(2)+"','"+rs.getString(3)+"')\" align='left'><u>"+rs.getString(2) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_client('"+rs.getString(3)+"')\" ><u>"+rs.getString(4) +"</u></td>");
					out.println("<td width='5%' align='left'>"+rs.getString(5) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(7) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(11) +"</td>");
					out.println("<td width='5%' align='right'>"+nf.format(rs.getDouble(16)) +"</td>");
					out.println("<td width='5%'  align='center'>"+rs.getInt(14) +"</td>");
					out.println("<td width='5%'  align='center'>"+rs.getInt(15) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(8) +"</td>");
					out.println("<td width='5%' align='left'>"+rs.getString(18) +"</td>"); // added by udara 17-02-2017
					out.println("<td width='5%' align='left'>"+rs.getString("YARD_NAME") +"</td>");  // added by udara 12-04-2021
					out.println("<td width='5%' align='left'>"+rs.getString(20) +"</td>"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503
					out.println("</tr>");
					
					
					
					j=j+1;
					
					
				}	
				
				
				
				if(!m_repo_status.equals("0")){
					m_filter = " AND "+m_schema_name+".AF_GET_REPO_STATUS2(A.REPOSSESSION_NO,'"+m_date+"') ="+m_repo_status+" ";
				}
				
				//out.println(" "+
				rs = stmt.executeQuery (" "+
					//" SELECT DISTINCT "+
					" SELECT  "+
					"	A.INVENTORY_NO INVENTORY_NO, "+  //1
					"	A.FINANCE_NO FINANCE_NO, "+ //2
					"	B.CLIENT_CODE CLIENT_CODE, "+ //3
					"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME, "+ //4
					"	B.VEHICLE_NO VEHICLE_NO, "+ //5
					"	A.SEIZER_CODE SEIZER_CODE, "+ //6
					"	"+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE) SEIZER_NAME, "+ //7
					"   DECODE("+m_schema_name+".AF_GET_REPO_STATUS2(A.REPOSSESSION_NO,'"+m_date+"'),0,'-',1,'Repossession Order Issued',2,'Vehicle Inventory Pending Approval',3,'Vehicle Inventory Approved',4,'Valuation Completed',5,'Advertisement Generated',6,'Offers Entered',7,'Offers accepted',8,'Repossession Order Deleted',9,'Repossession Order Reversed') APP_STATUS , "+
					"	A.REPOSSESSION_NO REPOSSESSION_NO, "+ //9
					"	NVL(TO_CHAR(A.TRN_DATE,'DD-MM-YYYY'),'-') TRN_DATE, "+ //10
					//"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'-') ENT_DATE, "+ // 11 
					"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'-') ENT_DATE, "+ // 11
					"	NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),'-') EFF_VAL_DATE, "+ //12
					"	C.APPLICATION_NO APPLICATION_NO, "+ //13
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',C.APPLICATION_NO),0) ASSET_COUNT,   "+ //14
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',C.APPLICATION_NO),0) VALUATION_COUNT,  "+ //15
					//"	NVL("+m_schema_name+".AF_CO_GET_CAP_OUT_STD_AMT(C.APPLICATION_NO),0) CAPITAL_OUTSTANDING,  "+ //16
					"	0 CAPITAL_OUTSTANDING,  "+ //16
					//"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17 
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17 
					"   NVL("+m_schema_name+".AF_RENTAL_FREEZ_DATE(A.FINANCE_NO),'-') ,   "+ // 18 
					"   NVL("+m_schema_name+".AF_CO_GET_YARD_NAME(B.YARD_CODE),'-') YARD_NAME, "+ // added by udara 12-04-2021
					"   NVL(DECODE(A.REPOSSESS_TYPE,'SEIZER','Seizer','OFFICER','Officer','COMPANY','Company','BUYBACK','Buy Back','-'),'-') "+ // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	//20
					"	FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					"	WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND A.FINANCE_NO=C.FINANCE_NO AND  "+
					"	A.ACTIVE_STATUS IN ('C','R') AND  B.ACTIVE_STATUS='APP'  "+
					//"   AND NVL(B.YARD_CODE,' ') LIKE UPPER('"+m_yard_code+"%')  "+ 
					m_yard_code_sql +
					"   AND TO_DATE(TO_CHAR(NVL(A.MOD_DATE,B.ENT_DATE),'DD-MM-YYYY'),'DD-MM-YYYY')> TO_DATE('"+m_date+"','DD-MM-YYYY') AND "+
					//"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					" AND A.FINANCE_NO  IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE ACTIVE_STATUS = 'TERM_CHECK' AND APPLY_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY'))"+
					" AND  A.REPOSSESSION_NO NOT IN (SELECT REF FROM AF_RE_REPOSSESSION_STATUS WHERE STATUS = 'Vehicle Release' AND TRUNC(ENTDATE) <= TO_DATE('"+m_date+"','DD-MM-YYYY') ) "+ // added by udara 27-08-2018
					m_filter +
					m_ro_filter_sql +
					" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
				
				
				 j=0;
				while(rs.next()){
					
	
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 >");
					}
					else{
						out.println("<tr class=tr_input >");
					}
					
					out.println("<td width='1%' style= cursor:hand;  align='left' >"+(j+1) +"</td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_repossession_drill('"+rs.getString(9)+"')\" align='left' ><u>"+rs.getString(9) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_inventory_drill('"+rs.getString(1)+"')\" align='left'><u>"+rs.getString(1) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_transaction_history_by_contract('"+rs.getString(2)+"','"+rs.getString(3)+"')\" align='left'><u>"+rs.getString(2) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_client('"+rs.getString(3)+"')\" ><u>"+rs.getString(4) +"</u></td>");
					out.println("<td width='5%' align='left'>"+rs.getString(5) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(7) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(11) +"</td>");
					out.println("<td width='5%' align='right'>"+nf.format(rs.getDouble(16)) +"</td>");
					out.println("<td width='5%'  align='center'>"+rs.getInt(14) +"</td>");
					out.println("<td width='5%'  align='center'>"+rs.getInt(15) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(8) +"</td>");
					out.println("<td width='5%' align='left'>"+rs.getString(18) +"</td>"); // added by udara 17-02-2017
					out.println("<td width='5%' align='left'>"+rs.getString("YARD_NAME") +"</td>");  // added by udara 12-04-2021
					out.println("<td width='5%' align='left'>"+rs.getString(20) +"</td>"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503
					out.println("</tr>");
					
					
					
					j=j+1;
					
					
				}	
				
				
					
				if(!m_repo_status.equals("0")){
					m_filter = " AND "+m_schema_name+".AF_GET_REPO_STATUS2(A.REPOSSESSION_NO,'"+m_date+"') ="+m_repo_status+" ";
				}
				
				
				
				// added by udara 06-04-2017
				//out.println(" "+
				rs = stmt.executeQuery (" "+
					//" SELECT DISTINCT "+
					" SELECT  "+
					"	A.INVENTORY_NO INVENTORY_NO, "+  //1
					"	A.FINANCE_NO FINANCE_NO, "+ //2
					"	B.CLIENT_CODE CLIENT_CODE, "+ //3
					"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME, "+ //4
					"	B.VEHICLE_NO VEHICLE_NO, "+ //5
					"	A.SEIZER_CODE SEIZER_CODE, "+ //6
					"	"+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE) SEIZER_NAME, "+ //7
					"   DECODE("+m_schema_name+".AF_GET_REPO_STATUS2(A.REPOSSESSION_NO,'"+m_date+"'),0,'-',1,'Repossession Order Issued',2,'Vehicle Inventory Pending Approval',3,'Vehicle Inventory Approved',4,'Valuation Completed',5,'Advertisement Generated',6,'Offers Entered',7,'Offers accepted',8,'Repossession Order Deleted',9,'Repossession Order Reversed',10,'Vehicle Release') APP_STATUS , "+
					"	A.REPOSSESSION_NO REPOSSESSION_NO, "+ //9
					"	NVL(TO_CHAR(A.TRN_DATE,'DD-MM-YYYY'),'-') TRN_DATE, "+ //10
					//"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'-') ENT_DATE, "+ // 11 
					"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'-') ENT_DATE, "+ // 11 
					"	NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),'-') EFF_VAL_DATE, "+ //12
					"	C.APPLICATION_NO APPLICATION_NO, "+ //13
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',C.APPLICATION_NO),0) ASSET_COUNT,   "+ //14
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',C.APPLICATION_NO),0) VALUATION_COUNT,  "+ //15
					//"	NVL("+m_schema_name+".AF_CO_GET_CAP_OUT_STD_AMT(C.APPLICATION_NO),0) CAPITAL_OUTSTANDING,  "+ //16
					"	0 CAPITAL_OUTSTANDING,  "+ //16
					//"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17 
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17 
					"   NVL("+m_schema_name+".AF_RENTAL_FREEZ_DATE(A.FINANCE_NO),'-'),    "+ // 18 added by udara 17-02-2017
					"   NVL("+m_schema_name+".AF_CO_GET_YARD_NAME(B.YARD_CODE),'-') YARD_NAME, "+ // added by udara 12-04-2021
					"   NVL(DECODE(A.REPOSSESS_TYPE,'SEIZER','Seizer','OFFICER','Officer','COMPANY','Company','BUYBACK','Buy Back','-'),'-') "+ // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	//20
					"	FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					"	WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND A.FINANCE_NO=C.FINANCE_NO AND  "+
					"	A.ACTIVE_STATUS='R' AND  B.ACTIVE_STATUS='APP'  "+
					//" UPPER(NVL(B.YARD_CODE,' ')) LIKE UPPER('"+m_yard_code+"%') AND "+ 
					//"   AND NVL(B.YARD_CODE,' ') LIKE UPPER('"+m_yard_code+"%')  "+
					m_yard_code_sql +
					"   AND TO_DATE(TO_CHAR(NVL(A.MOD_DATE,B.ENT_DATE),'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_date+"','DD-MM-YYYY') AND "+
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_RELEASED_DATE(A.FINANCE_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+
					" AND A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE ACTIVE_STATUS = 'TERM_CHECK' AND APPLY_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY'))"+
					m_filter +
					m_ro_filter_sql +
					" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
				
				
				 j=0;
				while(rs.next()){
					
					if(j==0){
						out.println("<tr><td colspan='12' align='center'>&nbsp;</td></tr>");
						out.println("<tr><td colspan='12' align='center' style='font-size=14px;'><b><u>Released Vehicles</u></b></td></tr>");
						out.println("<tr><td colspan='12' align='center'>&nbsp;</td></tr>");
					}
					
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 >");
					}
					else{
						out.println("<tr class=tr_input >");
					}
					
					out.println("<td width='1%' style= cursor:hand;  align='left' >"+(j+1) +"</td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_repossession_drill('"+rs.getString(9)+"')\" align='left' ><u>"+rs.getString(9) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_inventory_drill('"+rs.getString(1)+"')\" align='left'><u>"+rs.getString(1) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_transaction_history_by_contract('"+rs.getString(2)+"','"+rs.getString(3)+"')\" align='left'><u>"+rs.getString(2) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_client('"+rs.getString(3)+"')\" ><u>"+rs.getString(4) +"</u></td>");
					out.println("<td width='5%' align='left'>"+rs.getString(5) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(7) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(11) +"</td>");
					out.println("<td width='5%' align='right'>"+nf.format(rs.getDouble(16)) +"</td>");
					out.println("<td width='5%'  align='center'>"+rs.getInt(14) +"</td>");
					out.println("<td width='5%'  align='center'>"+rs.getInt(15) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(8) +"</td>");
					out.println("<td width='5%' align='left'>"+rs.getString(18) +"</td>"); // added by udara 17-02-2017
					out.println("<td width='5%' align='left'>"+rs.getString("YARD_NAME") +"</td>");  // added by udara 12-04-2021
					out.println("<td width='5%' align='left'>"+rs.getString(20) +"</td>"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503
					out.println("</tr>");
					
					
					
					j=j+1;
					
					
				}		
				
				
				out.println("<tr class=tr_input>");
				out.println("<td align=right colspan=11><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("</tr>");
				

				
				out.println("</table >"); 
				out.flush();
             
				//out.println("		clearTimeout(timerID);");
                //out.println("		m_table1.innerHTML=\"\";");
				
			}
			
			// added by udara 08-06-2015
			
			else if(m_chksql.equals("view_range")){		
				
				String m_date=req.getParameter("data_val");
				String m_from_date=req.getParameter("from_date");
				String m_yard_code=req.getParameter("yard_code"); //added by nuwan de silva on 02-11-07
				double rep_total=0;
				String m_sort_column = req.getParameter("sort_column");
				String m_order_by_type = req.getParameter("order_by_type");
				String m_repo_status = req.getParameter("repo_status");
				
				String m_ro_seizer = req.getParameter("ro_seizer");   // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				String m_ro_officer = req.getParameter("ro_officer"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				String m_ro_company = req.getParameter("ro_company"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				String m_ro_buyback = req.getParameter("ro_buyback"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				
				String m_filter = "";
				
				// ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				String m_ro_filter_sql = " ";
				String m_ro_filter = "";
				
				if(!m_ro_seizer.equals("") && m_ro_seizer!=null){
					m_ro_filter=m_ro_filter+"'"+m_ro_seizer+"',";
				}
				
				if(!m_ro_officer.equals("") && m_ro_officer!=null){
					m_ro_filter=m_ro_filter+"'"+m_ro_officer+"',";
				}
				if(!m_ro_company.equals("") && m_ro_company!=null){
					m_ro_filter=m_ro_filter+"'"+m_ro_company+"',";
				}
				if(!m_ro_buyback.equals("") && m_ro_buyback!=null){
					m_ro_filter=m_ro_filter+"'"+m_ro_buyback+"',";
				}
				if(m_ro_filter.indexOf(",") != -1){
				    m_ro_filter = m_ro_filter.substring(0,m_ro_filter.length() - 1);
				}
				if(!m_ro_filter.equals("") && m_ro_filter!=null){
				    
					m_ro_filter_sql="AND A.REPOSSESS_TYPE IN("+m_ro_filter+")";
				 }
				//out.println("m_ro_filter_sql="+m_ro_filter_sql);
				
				// END BY A/S ON 26-08-2021 FOR JB20072021-14503 
				
				// added by udara 08-05-2015
				if(!m_repo_status.equals("0")){
					m_filter = " AND "+m_schema_name+".AF_GET_REPO_STATUS(A.REPOSSESSION_NO) ="+m_repo_status+" ";
				}
				
				String m_yard_code_sql = " ";
				
				if(!m_yard_code.equals("") && m_yard_code!=null){
					m_yard_code_sql = " AND B.YARD_CODE = '"+m_yard_code+"' "; 
				}
				else{
					m_yard_code_sql = " ";
				}
				
				//out.println(" "+
				rs = stmt.executeQuery (" "+
					//" SELECT DISTINCT "+
					" SELECT  "+
					"	A.INVENTORY_NO INVENTORY_NO, "+  //1
					"	A.FINANCE_NO FINANCE_NO, "+ //2
					"	B.CLIENT_CODE CLIENT_CODE, "+ //3
					"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME, "+ //4
					"	B.VEHICLE_NO VEHICLE_NO, "+ //5
					"	A.SEIZER_CODE SEIZER_CODE, "+ //6
					"	"+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE) SEIZER_NAME, "+ //7
					"   DECODE("+m_schema_name+".AF_GET_REPO_STATUS2(A.REPOSSESSION_NO,'"+m_date+"'),0,'-',1,'Repossession Order Issued',2,'Vehicle Inventory Pending Approval',3,'Vehicle Inventory Approved',4,'Valuation Completed',5,'Advertisement Generated',6,'Offers Entered',7,'Offers accepted',8,'Repossession Order Deleted',9,'Repossession Order Reversed') APP_STATUS , "+
					"	A.REPOSSESSION_NO REPOSSESSION_NO, "+ //9
					"	NVL(TO_CHAR(A.TRN_DATE,'DD-MM-YYYY'),'-') TRN_DATE, "+ //10
					//"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'-') ENT_DATE, "+ // 11 
					"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'-') ENT_DATE, "+ // 11 // added by udara 21-03-2019
					"	NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),'-') EFF_VAL_DATE, "+ //12
					"	C.APPLICATION_NO APPLICATION_NO, "+ //13
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',C.APPLICATION_NO),0) ASSET_COUNT,   "+ //14
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',C.APPLICATION_NO),0) VALUATION_COUNT,  "+ //15
					//"	NVL("+m_schema_name+".AF_CO_GET_CAP_OUT_STD_AMT(C.APPLICATION_NO),0) CAPITAL_OUTSTANDING,  "+ //16 // commented by udara 19-03-2019
					"	0 CAPITAL_OUTSTANDING,  "+ //16 // added by udara 19-03-2019
					//"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17 
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17 
					"   NVL("+m_schema_name+".AF_RENTAL_FREEZ_DATE(A.FINANCE_NO),'-'),    "+ // 18 
					"   NVL("+m_schema_name+".AF_CO_GET_YARD_NAME(B.YARD_CODE),'-') YARD_NAME, "+ // added by udara 12-04-2021
					"   NVL(DECODE(A.REPOSSESS_TYPE,'SEIZER','Seizer','OFFICER','Officer','COMPANY','Company','BUYBACK','Buy Back','-'),'-') "+ // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	//20
					"	FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					"	WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND A.FINANCE_NO=C.FINANCE_NO AND  "+
					"	A.ACTIVE_STATUS IN ('C','Y') AND B.ACTIVE_STATUS='APP'  "+
					//"   AND UPPER(NVL(B.YARD_CODE,' ')) LIKE UPPER('"+m_yard_code+"%')  "+ 
					//"   AND TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					//"   AND TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					"   AND TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+ // added by udara 21-03-2019
					"   AND TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+  // added by udara 21-03-2019
					" AND A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE ACTIVE_STATUS = 'TERM_CHECK' AND APPLY_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY'))"+
					m_filter +
					m_yard_code_sql +
					m_ro_filter_sql +
					
					" UNION  "+
					
					//"SELECT DISTINCT "+
					"SELECT  "+
					"	A.INVENTORY_NO INVENTORY_NO, "+  //1
					"	A.FINANCE_NO FINANCE_NO, "+ //2
					"	B.CLIENT_CODE CLIENT_CODE, "+ //3
					"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME, "+ //4
					"	B.VEHICLE_NO VEHICLE_NO, "+ //5
					"	A.SEIZER_CODE SEIZER_CODE, "+ //6
					"	"+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE) SEIZER_NAME, "+ //7
					"   DECODE("+m_schema_name+".AF_GET_REPO_STATUS2(A.REPOSSESSION_NO,'"+m_date+"'),0,'-',1,'Repossession Order Issued',2,'Vehicle Inventory Pending Approval',3,'Vehicle Inventory Approved',4,'Valuation Completed',5,'Advertisement Generated',6,'Offers Entered',7,'Offers accepted',8,'Repossession Order Deleted',9,'Repossession Order Reversed') APP_STATUS , "+
					"	A.REPOSSESSION_NO REPOSSESSION_NO, "+ //9
					"	NVL(TO_CHAR(A.TRN_DATE,'DD-MM-YYYY'),'-') TRN_DATE, "+ //10
					//"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'-') ENT_DATE, "+ // 11 
					"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'-') ENT_DATE, "+ // 11 added by udara 21-03-2019
					"	NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),'-') EFF_VAL_DATE, "+ //12
					"	C.APPLICATION_NO APPLICATION_NO, "+ //13
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',C.APPLICATION_NO),0) ASSET_COUNT,   "+ //14
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',C.APPLICATION_NO),0) VALUATION_COUNT,  "+ //15
					//"	NVL("+m_schema_name+".AF_CO_GET_CAP_OUT_STD_AMT(C.APPLICATION_NO),0) CAPITAL_OUTSTANDING,  "+ //16 // commented by udara 19-03-2019
					"	0 CAPITAL_OUTSTANDING,  "+ //16 // added by udara 19-03-2019
					//"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17 
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17 added by udara 21-03-2019
					"   NVL("+m_schema_name+".AF_RENTAL_FREEZ_DATE(A.FINANCE_NO),'-'),    "+ // 18 
					"   NVL("+m_schema_name+".AF_CO_GET_YARD_NAME(B.YARD_CODE),'-') YARD_NAME, "+ // added by udara 12-04-2021
					"   NVL(DECODE(A.REPOSSESS_TYPE,'SEIZER','Seizer','OFFICER','Officer','COMPANY','Company','BUYBACK','Buy Back','-'),'-') "+ // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	//20
					"	FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					"	WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND A.FINANCE_NO=C.FINANCE_NO AND  "+
					//"	A.ACTIVE_STATUS IN ('C','R') AND  B.ACTIVE_STATUS='APP' AND "+
					"	A.ACTIVE_STATUS IN ('R') AND  B.ACTIVE_STATUS='APP'  "+
					//"   AND UPPER(NVL(B.YARD_CODE,' ')) LIKE UPPER('"+m_yard_code+"%')  "+ 
					"   AND TO_DATE(TO_CHAR(NVL(A.MOD_DATE,B.ENT_DATE),'DD-MM-YYYY'),'DD-MM-YYYY')> TO_DATE('"+m_date+"','DD-MM-YYYY') AND "+
					//"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					//"   AND TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					"   AND TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					"   AND A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE ACTIVE_STATUS = 'TERM_CHECK' AND APPLY_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY'))"+
					m_filter +					
					m_yard_code_sql +
					m_ro_filter_sql +
					
					" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");

				
				
				out.println("<br>");			
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
				
				out.println("<br>");			
				
				out.println("<tr class=tr_input>");
				out.println("<td colspan=11 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("</tr>");
				
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td  width='1%' align='left'   style= cursor:hand; >No</td>");
				out.println("<td  width='10%' align='left'   style= cursor:hand; title='Click here to sort by - Repossession No' onclick=sort_data_range('REPOSSESSION_NO','"+m_order_by_type+"')>Repossession No</td>");
				out.println("<td  width='10%' align='left'   style= cursor:hand; title='Click here to sort by - Inventory No'    onclick=sort_data_range('INVENTORY_NO','"+m_order_by_type+"')>Inventory No</td>");
				out.println("<td  width='10%' align='left'   style= cursor:hand; title='Click here to sort by - Finance No'      onclick=sort_data_range('FINANCE_NO','"+m_order_by_type+"')>Finance No</td>");
				out.println("<td  width='10%' align='left'   style= cursor:hand; title='Click here to sort by - Client Name'     onclick=sort_data_range('CLIENT_CODE','"+m_order_by_type+"')>Client Name</td>");
				out.println("<td  width='5%' align='left'   style= cursor:hand; title='Click here to sort by - Vehicle No'      onclick=sort_data_range('VEHICLE_NO','"+m_order_by_type+"')>Vehicle No</td>");
				out.println("<td  width='10%' align='left'   style= cursor:hand; title='Click here to sort by - Seizer Name'     onclick=sort_data_range('SEIZER_NAME','"+m_order_by_type+"')>Seizer Name</td>");
				out.println("<td  width='10%' align='left'   style= cursor:hand; title='Click here to sort by - Inventory Date'  onclick=sort_data_range('MOD_DATE','"+m_order_by_type+"')>Inventory Date</td>"); 
				out.println("<td  width='5%' align='right'  style= cursor:hand; title='Click here to sort by - Capital O/S'     onclick=sort_data_range('CAPITAL_OUTSTANDING','"+m_order_by_type+"')>Capital O/S</td>");
				out.println("<td  width='5%'  align='center' style= cursor:hand; title='Click here to sort by - Total Asset'     onclick=sort_data_range('ASSET_COUNT','"+m_order_by_type+"')>Total Asset</td>");
				out.println("<td  width='5%'  align='center' style= cursor:hand; title='Click here to sort by - Total Valuation' onclick=sort_data_range('VALUATION_COUNT','"+m_order_by_type+"')>Total Valuation</td>");
				out.println("<td  width='10%' align='left'   style= cursor:hand; title='Click here to sort by - Status'          onclick=sort_data_range('APP_STATUS','"+m_order_by_type+"')>Status</td>");
				out.println("<td  width='5%'  align='left'   style= cursor:hand;              >Effective Freeze Date</td>"); // added by udara 17-02-2017
				out.println("<td  width='5%'  align='left'                >Yard</td>");  // added by udara 12-04-2021
				out.println("<td  width='5%'  align='left'                >Ro Type</td>");  // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				out.println("</tr >"); 
				
				
				int j=0;
				while(rs.next()){
					
					
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 >");
					}
					else{
						out.println("<tr class=tr_input >");
					}
					
					out.println("<td width='1%' style= cursor:hand;  align='left' >"+(j+1) +"</td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_repossession_drill('"+rs.getString(9)+"')\" align='left' ><u>"+rs.getString(9) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_inventory_drill('"+rs.getString(1)+"')\" align='left'><u>"+rs.getString(1) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_transaction_history_by_contract('"+rs.getString(2)+"','"+rs.getString(3)+"')\" align='left'><u>"+rs.getString(2) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_client('"+rs.getString(3)+"')\" ><u>"+rs.getString(4) +"</u></td>");
					out.println("<td width='5%' align='left'>"+rs.getString(5) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(7) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(11) +"</td>");
					out.println("<td width='5%' align='right'>"+nf.format(rs.getDouble(16)) +"</td>");
					out.println("<td width='5%'  align='center'>"+rs.getInt(14) +"</td>");
					out.println("<td width='5%'  align='center'>"+rs.getInt(15) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(8) +"</td>");
					out.println("<td width='5%' align='left'>"+rs.getString(18) +"</td>"); // added by udara 17-02-2017
					out.println("<td width='5%' align='left'>"+rs.getString("YARD_NAME") +"</td>");  // added by udara 12-04-2021
					out.println("<td width='5%' align='left'>"+rs.getString(20) +"</td>");  // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
					out.println("</tr>");
					
					
					
					j=j+1;
					
					
				}	
				
				// end by udara 08-05-2015
				
				
				// ==================================== TERMINATED CONTRACTS ==========================================================
				
					
				if(!m_repo_status.equals("0")){
					m_filter = " AND "+m_schema_name+".AF_GET_REPO_STATUS(A.REPOSSESSION_NO) ="+m_repo_status+" ";
				}
				
				
				
				// added by udara 05-06-2015
				
				rs = stmt.executeQuery (" "+
					//" SELECT DISTINCT "+
					" SELECT  "+
					"	A.INVENTORY_NO INVENTORY_NO, "+  //1
					"	A.FINANCE_NO FINANCE_NO, "+ //2
					"	B.CLIENT_CODE CLIENT_CODE, "+ //3
					"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME, "+ //4
					"	B.VEHICLE_NO VEHICLE_NO, "+ //5
					"	A.SEIZER_CODE SEIZER_CODE, "+ //6
					"	"+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE) SEIZER_NAME, "+ //7
					"   DECODE("+m_schema_name+".AF_GET_REPO_STATUS2(A.REPOSSESSION_NO,'"+m_date+"'),0,'-',1,'Repossession Order Issued',2,'Vehicle Inventory Pending Approval',3,'Vehicle Inventory Approved',4,'Valuation Completed',5,'Advertisement Generated',6,'Offers Entered',7,'Offers accepted',8,'Repossession Order Deleted',9,'Repossession Order Reversed') APP_STATUS , "+
					"	A.REPOSSESSION_NO REPOSSESSION_NO, "+ //9
					"	NVL(TO_CHAR(A.TRN_DATE,'DD-MM-YYYY'),'-') TRN_DATE, "+ //10
					//"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'-') ENT_DATE, "+ // 11 
					"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'-') ENT_DATE, "+ // 11 
					"	NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),'-') EFF_VAL_DATE, "+ //12
					"	C.APPLICATION_NO APPLICATION_NO, "+ //13
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',C.APPLICATION_NO),0) ASSET_COUNT,   "+ //14
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',C.APPLICATION_NO),0) VALUATION_COUNT,  "+ //15
					//"	NVL("+m_schema_name+".AF_CO_GET_CAP_OUT_STD_AMT(C.APPLICATION_NO),0) CAPITAL_OUTSTANDING,  "+ //16 // commented by udara 19-03-2019
					"	0 CAPITAL_OUTSTANDING,  "+ //16 // commented by udara 19-03-2019 // added by udara 19-03-2019
					//"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17 // added by udara 21-03-2019
					"   NVL("+m_schema_name+".AF_RENTAL_FREEZ_DATE(A.FINANCE_NO),'-'),    "+ // 18 
					"   NVL("+m_schema_name+".AF_CO_GET_YARD_NAME(B.YARD_CODE),'-') YARD_NAME, "+ // added by udara 12-04-2021
					"   NVL(DECODE(A.REPOSSESS_TYPE,'SEIZER','Seizer','OFFICER','Officer','COMPANY','Company','BUYBACK','Buy Back','-'),'-') "+ // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	//20
					"	FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					"	WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND A.FINANCE_NO=C.FINANCE_NO AND  "+
					"	A.ACTIVE_STATUS IN ('C','Y') AND B.ACTIVE_STATUS='APP'  "+
					//"   AND UPPER(NVL(B.YARD_CODE,' ')) LIKE UPPER('"+m_yard_code+"%')  "+ 
					"   AND A.FINANCE_NO  IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE ACTIVE_STATUS = 'TERM_CHECK' AND APPLY_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY') AND APPLY_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') )"+
					"   AND  A.REPOSSESSION_NO NOT IN (SELECT REF FROM AF_RE_REPOSSESSION_STATUS WHERE STATUS = 'Vehicle Release' AND TRUNC(ENTDATE) <= TO_DATE('"+m_date+"','DD-MM-YYYY') ) "+ // added by udara 27-08-2018
					m_filter +
					m_yard_code_sql +
					m_ro_filter_sql +
					
					" UNION   "+
					
					//" SELECT DISTINCT "+
					" SELECT  "+
					"	A.INVENTORY_NO INVENTORY_NO, "+  //1
					"	A.FINANCE_NO FINANCE_NO, "+ //2
					"	B.CLIENT_CODE CLIENT_CODE, "+ //3
					"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME, "+ //4
					"	B.VEHICLE_NO VEHICLE_NO, "+ //5
					"	A.SEIZER_CODE SEIZER_CODE, "+ //6
					"	"+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE) SEIZER_NAME, "+ //7
					"   DECODE("+m_schema_name+".AF_GET_REPO_STATUS2(A.REPOSSESSION_NO,'"+m_date+"'),0,'-',1,'Repossession Order Issued',2,'Vehicle Inventory Pending Approval',3,'Vehicle Inventory Approved',4,'Valuation Completed',5,'Advertisement Generated',6,'Offers Entered',7,'Offers accepted',8,'Repossession Order Deleted',9,'Repossession Order Reversed') APP_STATUS , "+
					"	A.REPOSSESSION_NO REPOSSESSION_NO, "+ //9
					"	NVL(TO_CHAR(A.TRN_DATE,'DD-MM-YYYY'),'-') TRN_DATE, "+ //10
					//"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'-') ENT_DATE, "+ // 11 
					"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'-') ENT_DATE, "+ // 11 added by udara 21-03-2019
					"	NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),'-') EFF_VAL_DATE, "+ //12
					"	C.APPLICATION_NO APPLICATION_NO, "+ //13
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',C.APPLICATION_NO),0) ASSET_COUNT,   "+ //14
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',C.APPLICATION_NO),0) VALUATION_COUNT,  "+ //15
					//"	NVL("+m_schema_name+".AF_CO_GET_CAP_OUT_STD_AMT(C.APPLICATION_NO),0) CAPITAL_OUTSTANDING,  "+ //16 // commented by udara 19-03-2019
					"	0 CAPITAL_OUTSTANDING,  "+ //16 // added by udara 19-03-2019
					//"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17 added by udara 21-03-2019
					"   NVL("+m_schema_name+".AF_RENTAL_FREEZ_DATE(A.FINANCE_NO),'-'),    "+ // 18
					"   NVL("+m_schema_name+".AF_CO_GET_YARD_NAME(B.YARD_CODE),'-') YARD_NAME, "+ // added by udara 12-04-2021
					"   NVL(DECODE(A.REPOSSESS_TYPE,'SEIZER','Seizer','OFFICER','Officer','COMPANY','Company','BUYBACK','Buy Back','-'),'-') "+ // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	//20
					"	FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					"	WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND A.FINANCE_NO=C.FINANCE_NO AND  "+
					//"	A.ACTIVE_STATUS IN ('C','R') AND B.ACTIVE_STATUS='APP' AND "+
					"	A.ACTIVE_STATUS IN ('R') AND B.ACTIVE_STATUS='APP'  "+
					//"   AND UPPER(NVL(B.YARD_CODE,' ')) LIKE UPPER('"+m_yard_code+"%')  "+ 
					"   AND A.FINANCE_NO  IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE ACTIVE_STATUS = 'TERM_CHECK' AND APPLY_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY') AND APPLY_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') )"+
					"   AND  A.REPOSSESSION_NO NOT IN (SELECT REF FROM AF_RE_REPOSSESSION_STATUS WHERE STATUS = 'Vehicle Release' AND TRUNC(ENTDATE) <= TO_DATE('"+m_date+"','DD-MM-YYYY') ) "+ // added by udara 27-08-2018
					m_filter +
					m_yard_code_sql +
					m_ro_filter_sql +
					
					" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
				
				// end by udara 05-06-2015
				
				 j=0;
					if(j==0){
						out.println("<tr><td colspan='12' align='center'>&nbsp;</td></tr>");
						out.println("<tr><td colspan='12' align='center' style='font-size=14px;'><b><u>Terminated Contracts</u></b></td></tr>");
						out.println("<tr><td colspan='12' align='center'>&nbsp;</td></tr>");
					}
				while(rs.next()){
					
					
					
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 >");
					}
					else{
						out.println("<tr class=tr_input >");
					}
					
					out.println("<td width='1%' style= cursor:hand;  align='left' >"+(j+1) +"</td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_repossession_drill('"+rs.getString(9)+"')\" align='left' ><u>"+rs.getString(9) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_inventory_drill('"+rs.getString(1)+"')\" align='left'><u>"+rs.getString(1) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_transaction_history_by_contract('"+rs.getString(2)+"','"+rs.getString(3)+"')\" align='left'><u>"+rs.getString(2) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_client('"+rs.getString(3)+"')\" ><u>"+rs.getString(4) +"</u></td>");
					out.println("<td width='5%' align='left'>"+rs.getString(5) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(7) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(11) +"</td>");
					out.println("<td width='5%' align='right'>"+nf.format(rs.getDouble(16)) +"</td>");
					out.println("<td width='5%'  align='center'>"+rs.getInt(14) +"</td>");
					out.println("<td width='5%'  align='center'>"+rs.getInt(15) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(8) +"</td>");
					out.println("<td width='5%' align='left'>"+rs.getString(18) +"</td>"); // added by udara 17-02-2017
					out.println("<td width='5%' align='left'>"+rs.getString("YARD_NAME") +"</td>");  // added by udara 12-04-2021
					out.println("<td width='5%' align='left'>"+rs.getString(20) +"</td>"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
					out.println("</tr>");
					
					
					
					j=j+1;
					
					
				}	
				
				
				
				if(!m_repo_status.equals("0")){
					m_filter = " AND "+m_schema_name+".AF_GET_REPO_STATUS2(A.REPOSSESSION_NO,'"+m_date+"') ="+m_repo_status+" ";
				}
				
				
				rs = stmt.executeQuery (" "+
					//" SELECT DISTINCT "+
					" SELECT  "+
					"	A.INVENTORY_NO INVENTORY_NO, "+  //1
					"	A.FINANCE_NO FINANCE_NO, "+ //2
					"	B.CLIENT_CODE CLIENT_CODE, "+ //3
					"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME, "+ //4
					"	B.VEHICLE_NO VEHICLE_NO, "+ //5
					"	A.SEIZER_CODE SEIZER_CODE, "+ //6
					"	"+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE) SEIZER_NAME, "+ //7
					"   DECODE("+m_schema_name+".AF_GET_REPO_STATUS2(A.REPOSSESSION_NO,'"+m_date+"'),0,'-',1,'Repossession Order Issued',2,'Vehicle Inventory Pending Approval',3,'Vehicle Inventory Approved',4,'Valuation Completed',5,'Advertisement Generated',6,'Offers Entered',7,'Offers accepted',8,'Repossession Order Deleted',9,'Repossession Order Reversed') APP_STATUS , "+
					"	A.REPOSSESSION_NO REPOSSESSION_NO, "+ //9
					"	NVL(TO_CHAR(A.TRN_DATE,'DD-MM-YYYY'),'-') TRN_DATE, "+ //10
					//"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'-') ENT_DATE, "+ // 11
					"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'-') ENT_DATE, "+ // 11 added by udara 21-03-2019
					"	NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),'-') EFF_VAL_DATE, "+ //12
					"	C.APPLICATION_NO APPLICATION_NO, "+ //13
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',C.APPLICATION_NO),0) ASSET_COUNT,   "+ //14
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',C.APPLICATION_NO),0) VALUATION_COUNT,  "+ //15
					//"	NVL("+m_schema_name+".AF_CO_GET_CAP_OUT_STD_AMT(C.APPLICATION_NO),0) CAPITAL_OUTSTANDING,  "+ //16
					"	0 CAPITAL_OUTSTANDING,  "+ //16
					//"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17 
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17 
					"   NVL("+m_schema_name+".AF_RENTAL_FREEZ_DATE(A.FINANCE_NO),'-'),    "+ // 18 
					"   NVL("+m_schema_name+".AF_CO_GET_YARD_NAME(B.YARD_CODE),'-') YARD_NAME, "+ // added by udara 12-04-2021
					"   NVL(DECODE(A.REPOSSESS_TYPE,'SEIZER','Seizer','OFFICER','Officer','COMPANY','Company','BUYBACK','Buy Back','-'),'-') "+ // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	//20
					"	FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					"	WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND A.FINANCE_NO=C.FINANCE_NO AND  "+
					//"	A.ACTIVE_STATUS IN ('C','R') AND  B.ACTIVE_STATUS='APP' AND "+
					"	A.ACTIVE_STATUS IN ('R') AND  B.ACTIVE_STATUS='APP'  "+
					//"   AND UPPER(NVL(B.YARD_CODE,' ')) LIKE UPPER('"+m_yard_code+"%')  "+ 
					"   AND TO_DATE(TO_CHAR(NVL(A.MOD_DATE,B.ENT_DATE),'DD-MM-YYYY'),'DD-MM-YYYY')> TO_DATE('"+m_date+"','DD-MM-YYYY') AND "+
					//"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					//"   AND TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					"   AND TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					" AND A.FINANCE_NO  IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE ACTIVE_STATUS = 'TERM_CHECK' AND APPLY_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY'))"+
					m_filter +
					m_yard_code_sql +
					m_ro_filter_sql +
					" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
				
				
				 j=0;
				while(rs.next()){
					
	
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 >");
					}
					else{
						out.println("<tr class=tr_input >");
					}
					
					out.println("<td width='1%' style= cursor:hand;  align='left' >"+(j+1) +"</td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_repossession_drill('"+rs.getString(9)+"')\" align='left' ><u>"+rs.getString(9) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_inventory_drill('"+rs.getString(1)+"')\" align='left'><u>"+rs.getString(1) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_transaction_history_by_contract('"+rs.getString(2)+"','"+rs.getString(3)+"')\" align='left'><u>"+rs.getString(2) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_client('"+rs.getString(3)+"')\" ><u>"+rs.getString(4) +"</u></td>");
					out.println("<td width='5%' align='left'>"+rs.getString(5) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(7) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(11) +"</td>");
					out.println("<td width='5%' align='right'>"+nf.format(rs.getDouble(16)) +"</td>");
					out.println("<td width='5%'  align='center'>"+rs.getInt(14) +"</td>");
					out.println("<td width='5%'  align='center'>"+rs.getInt(15) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(8) +"</td>");
					out.println("<td width='5%' align='left'>"+rs.getString(18) +"</td>"); // added by udara 17-02-2017
					out.println("<td width='5%' align='left'>"+rs.getString("YARD_NAME") +"</td>");  // added by udara 12-04-2021
					out.println("<td width='5%' align='left'>"+rs.getString(20) +"</td>"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
					out.println("</tr>");
					
					
					
					j=j+1;
					
					
				}	
				
				
					
				if(!m_repo_status.equals("0")){
					m_filter = " AND "+m_schema_name+".AF_GET_REPO_STATUS2(A.REPOSSESSION_NO,'"+m_date+"') ="+m_repo_status+" ";
				}
				
				
				
				
				// added by udara 06-04-2017
				rs = stmt.executeQuery (" "+
					//" SELECT DISTINCT "+
					" SELECT  "+
					"	A.INVENTORY_NO INVENTORY_NO, "+  //1
					"	A.FINANCE_NO FINANCE_NO, "+ //2
					"	B.CLIENT_CODE CLIENT_CODE, "+ //3
					"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME, "+ //4
					"	B.VEHICLE_NO VEHICLE_NO, "+ //5
					"	A.SEIZER_CODE SEIZER_CODE, "+ //6
					"	"+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE) SEIZER_NAME, "+ //7
					"   DECODE("+m_schema_name+".AF_GET_REPO_STATUS2(A.REPOSSESSION_NO,'"+m_date+"'),0,'-',1,'Repossession Order Issued',2,'Vehicle Inventory Pending Approval',3,'Vehicle Inventory Approved',4,'Valuation Completed',5,'Advertisement Generated',6,'Offers Entered',7,'Offers accepted',8,'Repossession Order Deleted',9,'Repossession Order Reversed',10,'Vehicle Release') APP_STATUS , "+
					"	A.REPOSSESSION_NO REPOSSESSION_NO, "+ //9
					"	NVL(TO_CHAR(A.TRN_DATE,'DD-MM-YYYY'),'-') TRN_DATE, "+ //10
					//"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'-') ENT_DATE, "+ // 11 added by udara 11-05-2015
					"   NVL("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'-') ENT_DATE, "+ // 11 added by udara 11-05-2015
					"	NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),'-') EFF_VAL_DATE, "+ //12
					"	C.APPLICATION_NO APPLICATION_NO, "+ //13
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',C.APPLICATION_NO),0) ASSET_COUNT,   "+ //14
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',C.APPLICATION_NO),0) VALUATION_COUNT,  "+ //15
					//"	NVL("+m_schema_name+".AF_CO_GET_CAP_OUT_STD_AMT(C.APPLICATION_NO),0) CAPITAL_OUTSTANDING,  "+ //16
					"	0 CAPITAL_OUTSTANDING,  "+ //16
					//"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY') MOD_DATE,  "+ // 17 added by udara 07-05-2015 // "   B.MOD_DATE  MOD_DATE "+ // 17 added by udara 07-05-2015
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY') MOD_DATE,  "+ 
					"   NVL("+m_schema_name+".AF_RENTAL_FREEZ_DATE(A.FINANCE_NO),'-') ,   "+ // 18 added by udara 17-02-2017
					"   NVL("+m_schema_name+".AF_CO_GET_YARD_NAME(B.YARD_CODE),'-') YARD_NAME, "+ // added by udara 12-04-2021
					"   NVL(DECODE(A.REPOSSESS_TYPE,'SEIZER','Seizer','OFFICER','Officer','COMPANY','Company','BUYBACK','Buy Back','-'),'-') "+ // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	//20
					"	FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					"	WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND A.FINANCE_NO=C.FINANCE_NO AND  "+
					"	A.ACTIVE_STATUS='R' AND  B.ACTIVE_STATUS='APP'  "+
					//"   AND UPPER(NVL(B.YARD_CODE,' ')) LIKE UPPER('"+m_yard_code+"%')  "+ // added by udara 30-06-2015
					"   AND TO_DATE(TO_CHAR(NVL(A.MOD_DATE,B.ENT_DATE),'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_date+"','DD-MM-YYYY') AND "+
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_RELEASED_DATE(A.FINANCE_NO),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					"   AND TO_DATE("+m_schema_name+".AF_RE_VEH_RELEASED_DATE(A.FINANCE_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ // added by udara 15-05-2015
					" AND A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE ACTIVE_STATUS = 'TERM_CHECK' AND APPLY_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY'))"+
					m_filter +
					m_yard_code_sql +
					m_ro_filter_sql +
					" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
				
				
				 j=0;
				while(rs.next()){
					
					if(j==0){
						out.println("<tr><td colspan='12' align='center'>&nbsp;</td></tr>");
						out.println("<tr><td colspan='12' align='center' style='font-size=14px;'><b><u>Released Vehicles</u></b></td></tr>");
						out.println("<tr><td colspan='12' align='center'>&nbsp;</td></tr>");
					}
					
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 >");
					}
					else{
						out.println("<tr class=tr_input >");
					}
					
					out.println("<td width='1%' style= cursor:hand;  align='left' >"+(j+1) +"</td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_repossession_drill('"+rs.getString(9)+"')\" align='left' ><u>"+rs.getString(9) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_inventory_drill('"+rs.getString(1)+"')\" align='left'><u>"+rs.getString(1) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_transaction_history_by_contract('"+rs.getString(2)+"','"+rs.getString(3)+"')\" align='left'><u>"+rs.getString(2) +"</u></td>");
					out.println("<td width='10%' style= cursor:hand; onClick=\"show_client('"+rs.getString(3)+"')\" ><u>"+rs.getString(4) +"</u></td>");
					out.println("<td width='5%' align='left'>"+rs.getString(5) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(7) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(11) +"</td>");
					out.println("<td width='5%' align='right'>"+nf.format(rs.getDouble(16)) +"</td>");
					out.println("<td width='5%'  align='center'>"+rs.getInt(14) +"</td>");
					out.println("<td width='5%'  align='center'>"+rs.getInt(15) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(8) +"</td>");
					out.println("<td width='5%' align='left'>"+rs.getString(18) +"</td>"); // added by udara 17-02-2017
					out.println("<td width='5%' align='left'>"+rs.getString("YARD_NAME") +"</td>");  // added by udara 12-04-2021
					out.println("<td width='5%' align='left'>"+rs.getString(20) +"</td>"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 
					out.println("</tr>");
					
					
					
					j=j+1;
					
					
				}		
				
				
				out.println("<tr class=tr_input>");
				out.println("<td align=right colspan=11><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("</tr>");
				
				out.println("</table >"); 
				out.flush();
				
			}
			
			// end by udara 08-06-2015
			
			else if(m_chksql.equals("count")){		
				String m_yard_code="";
				String m_date=req.getParameter("data_val");
				
				if(req.getParameter("yard_code")!=null){
					m_yard_code = req.getParameter("yard_code");
				}
				
				String m_repo_status = req.getParameter("repo_status");
				
				String m_ro_seizer = req.getParameter("ro_seizer");   // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				String m_ro_officer = req.getParameter("ro_officer"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				String m_ro_company = req.getParameter("ro_company"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				String m_ro_buyback = req.getParameter("ro_buyback"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				
				String m_filter = "";
				
				// ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				String m_ro_filter_sql = " ";
				String m_ro_filter = "";
				
				if(!m_ro_seizer.equals("") && m_ro_seizer!=null){
					m_ro_filter=m_ro_filter+"'"+m_ro_seizer+"',";
				}
				
				if(!m_ro_officer.equals("") && m_ro_officer!=null){
					m_ro_filter=m_ro_filter+"'"+m_ro_officer+"',";
				}
				if(!m_ro_company.equals("") && m_ro_company!=null){
					m_ro_filter=m_ro_filter+"'"+m_ro_company+"',";
				}
				if(!m_ro_buyback.equals("") && m_ro_buyback!=null){
					m_ro_filter=m_ro_filter+"'"+m_ro_buyback+"',";
				}
				if(m_ro_filter.indexOf(",") != -1){
				    m_ro_filter = m_ro_filter.substring(0,m_ro_filter.length() - 1);
				}
				if(!m_ro_filter.equals("") && m_ro_filter!=null){
				    
					m_ro_filter_sql="AND A.REPOSSESS_TYPE IN("+m_ro_filter+")";
				 }
				
				if(!m_repo_status.equals("0")){
					m_filter = " AND "+m_schema_name+".AF_GET_REPO_STATUS(A.REPOSSESSION_NO) ="+m_repo_status+" ";
				}
				
				// added by udara 11-09-2018
				String m_yard_code_sql = " ";
				
				if(!m_yard_code.equals("") && m_yard_code!=null){
					m_yard_code_sql = " AND B.YARD_CODE = '"+m_yard_code+"' "; 
				}
				else{
					m_yard_code_sql = " ";
				}
				// end by udara 11-09-2018
				
				
				//String m_yard_code=req.getParameter("yard_code").trim(); //added by nuwan de silva on 02-11-07
				double rep_total=0;
				
				/*
				// added by udara 25-08-2015
				rs = stmt.executeQuery(" "+ 
				//out.println(" "+
					
					" SELECT COUNT(INVENTORY_NO) FROM(  "+
					" SELECT DISTINCT "+
					"	A.INVENTORY_NO INVENTORY_NO, "+  //1
					"	A.FINANCE_NO FINANCE_NO, "+ //2
					"	B.CLIENT_CODE CLIENT_CODE, "+ //3
					"	B.VEHICLE_NO VEHICLE_NO, "+ //5
					"	A.SEIZER_CODE SEIZER_CODE, "+ //6
					"	A.REPOSSESSION_NO REPOSSESSION_NO, "+ //9
					"	NVL(TO_CHAR(A.TRN_DATE,'DD-MM-YYYY'),'-') TRN_DATE, "+ //10
					"	NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),'-') EFF_VAL_DATE, "+ //12
					"	C.APPLICATION_NO APPLICATION_NO "+ //13
					"	FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					"	WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND A.FINANCE_NO=C.FINANCE_NO AND  "+
					"	A.ACTIVE_STATUS IN ('C','Y') AND B.ACTIVE_STATUS='APP' AND "+
					" UPPER(NVL(B.YARD_CODE,' ')) LIKE UPPER('"+m_yard_code+"%') AND "+ 
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					" AND A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE ACTIVE_STATUS = 'TERM_CHECK' AND APPLY_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY'))"+
					" AND  A.REPOSSESSION_NO NOT IN (SELECT REF FROM AF_RE_REPOSSESSION_STATUS WHERE STATUS = 'Vehicle Release' AND TRUNC(ENTDATE) <= TO_DATE('"+m_date+"','DD-MM-YYYY') ) "+ // added by udara 27-08-2018
					m_filter +
					
					" UNION  "+
					
					"SELECT DISTINCT "+
					"	A.INVENTORY_NO INVENTORY_NO, "+  //1
					"	A.FINANCE_NO FINANCE_NO, "+ //2
					"	B.CLIENT_CODE CLIENT_CODE, "+ //3
					"	B.VEHICLE_NO VEHICLE_NO, "+ //5
					"	A.SEIZER_CODE SEIZER_CODE, "+ //6
					"	A.REPOSSESSION_NO REPOSSESSION_NO, "+ //9
					"	NVL(TO_CHAR(A.TRN_DATE,'DD-MM-YYYY'),'-') TRN_DATE, "+ //10
					"	NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),'-') EFF_VAL_DATE, "+ //12
					"	C.APPLICATION_NO APPLICATION_NO "+ //13
					"	FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					"	WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND A.FINANCE_NO=C.FINANCE_NO AND  "+
					"	A.ACTIVE_STATUS IN ('C','R') AND  B.ACTIVE_STATUS='APP' AND "+
					" UPPER(NVL(B.YARD_CODE,' ')) LIKE UPPER('"+m_yard_code+"%') AND "+ 
					" TO_DATE(TO_CHAR(NVL(A.MOD_DATE,B.ENT_DATE),'DD-MM-YYYY'),'DD-MM-YYYY')> TO_DATE('"+m_date+"','DD-MM-YYYY') AND "+
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					" AND A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE ACTIVE_STATUS = 'TERM_CHECK' AND APPLY_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY'))"+
					" AND  A.REPOSSESSION_NO NOT IN (SELECT REF FROM AF_RE_REPOSSESSION_STATUS WHERE STATUS = 'Vehicle Release' AND TRUNC(ENTDATE) <= TO_DATE('"+m_date+"','DD-MM-YYYY') ) "+ // added by udara 27-08-2018
					m_filter +					
				
					" ) "+
					
					" ");
					*/
				
				rs = stmt.executeQuery(" "+
				//out.println(" "+
					" SELECT COUNT(INVENTORY_NO) FROM(  "+
					//" SELECT  DISTINCT "+
					" SELECT  "+
					"	A.INVENTORY_NO INVENTORY_NO "+  //1
					"	FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					"	WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND A.FINANCE_NO=C.FINANCE_NO AND  "+
					"	A.ACTIVE_STATUS IN ('C','Y') AND B.ACTIVE_STATUS='APP'  "+
					//"   AND UPPER(NVL(B.YARD_CODE,' ')) LIKE UPPER('"+m_yard_code+"%')  "+ 
					//"   AND TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					"   AND TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					"   AND A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE ACTIVE_STATUS = 'TERM_CHECK' AND APPLY_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY'))"+
					"   AND  A.REPOSSESSION_NO NOT IN (SELECT REF FROM AF_RE_REPOSSESSION_STATUS WHERE STATUS = 'Vehicle Release' AND TRUNC(ENTDATE) <= TO_DATE('"+m_date+"','DD-MM-YYYY') ) "+ // added by udara 27-08-2018
					m_filter +
					m_yard_code_sql +
					m_ro_filter_sql +
					
					" UNION  "+
					
					//"SELECT DISTINCT "+
					"SELECT  "+
					"	A.INVENTORY_NO INVENTORY_NO "+  //1
					"	FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					"	WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND A.FINANCE_NO=C.FINANCE_NO AND  "+
					//"	A.ACTIVE_STATUS IN ('C','R') AND  B.ACTIVE_STATUS='APP'  "+
					"	A.ACTIVE_STATUS IN ('R') AND  B.ACTIVE_STATUS='APP'  "+
					//"   AND UPPER(NVL(B.YARD_CODE,' ')) LIKE UPPER('"+m_yard_code+"%')  "+ 
					"   AND TO_DATE(TO_CHAR(NVL(A.MOD_DATE,B.ENT_DATE),'DD-MM-YYYY'),'DD-MM-YYYY')> TO_DATE('"+m_date+"','DD-MM-YYYY') AND "+
					//"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					"   AND A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE ACTIVE_STATUS = 'TERM_CHECK' AND APPLY_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY'))"+
					"   AND  A.REPOSSESSION_NO NOT IN (SELECT REF FROM AF_RE_REPOSSESSION_STATUS WHERE STATUS = 'Vehicle Release' AND TRUNC(ENTDATE) <= TO_DATE('"+m_date+"','DD-MM-YYYY') ) "+ // added by udara 27-08-2018
					m_filter +	
					m_yard_code_sql +
					m_ro_filter_sql +
				
					" ) "+
					
					" ");
				
				
				//	out.println("<br>");		
				
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
				out.println("<br>");			
				int j=0;
				if(rs.next()){
					out.println("<tr >");
					out.println("<td width='20%'  align='left'><b>Total No of Asset</td>");
					out.println("<td width='*%'  align='left'><b>"+rs.getInt(1) +"</td>");
					//out.println("<td width='*%'  align='left'>&nbsp;</td>");
					out.println("</tr>");
				}		
				
				out.println("</table >"); 
				out.flush();
			}
			
			
			// added by udara 08-06-2015
			
			else if(m_chksql.equals("count_range")){		
				String m_yard_code="";
				String m_date=req.getParameter("data_val");
				String m_from_date=req.getParameter("from_date");
				
				if(req.getParameter("yard_code")!=null){
					m_yard_code = req.getParameter("yard_code");
				}
				
				String m_repo_status = req.getParameter("repo_status");
				
				String m_ro_seizer = req.getParameter("ro_seizer");   // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				String m_ro_officer = req.getParameter("ro_officer"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				String m_ro_company = req.getParameter("ro_company"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				String m_ro_buyback = req.getParameter("ro_buyback"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				
				String m_filter = "";
				
				// ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 	
				String m_ro_filter_sql = " ";
				String m_ro_filter = "";
				
				if(!m_ro_seizer.equals("") && m_ro_seizer!=null){
					m_ro_filter=m_ro_filter+"'"+m_ro_seizer+"',";
				}
				
				if(!m_ro_officer.equals("") && m_ro_officer!=null){
					m_ro_filter=m_ro_filter+"'"+m_ro_officer+"',";
				}
				if(!m_ro_company.equals("") && m_ro_company!=null){
					m_ro_filter=m_ro_filter+"'"+m_ro_company+"',";
				}
				if(!m_ro_buyback.equals("") && m_ro_buyback!=null){
					m_ro_filter=m_ro_filter+"'"+m_ro_buyback+"',";
				}
				if(m_ro_filter.indexOf(",") != -1){
				    m_ro_filter = m_ro_filter.substring(0,m_ro_filter.length() - 1);
				}
				if(!m_ro_filter.equals("") && m_ro_filter!=null){
				    
					m_ro_filter_sql="AND A.REPOSSESS_TYPE IN("+m_ro_filter+")";
				 }
				
				if(!m_repo_status.equals("0")){
					m_filter = " AND "+m_schema_name+".AF_GET_REPO_STATUS(A.REPOSSESSION_NO) ="+m_repo_status+" ";
				}
				
				String m_yard_code_sql = " ";
				
				if(!m_yard_code.equals("") && m_yard_code!=null){
					m_yard_code_sql = " AND B.YARD_CODE = '"+m_yard_code+"' "; 
				}
				else{
					m_yard_code_sql = " ";
				}
				
				
				//String m_yard_code=req.getParameter("yard_code").trim(); //added by nuwan de silva on 02-11-07
				double rep_total=0;
				
				/*

					rs = stmt.executeQuery (" "+
					" SELECT COUNT(INVENTORY_NO) FROM(  "+
					//" SELECT DISTINCT "+
					" SELECT  "+
					"	A.INVENTORY_NO INVENTORY_NO, "+  //1
					"	A.FINANCE_NO FINANCE_NO, "+ //2
					"	B.CLIENT_CODE CLIENT_CODE, "+ //3
					"	B.VEHICLE_NO VEHICLE_NO, "+ //5
					"	A.SEIZER_CODE SEIZER_CODE, "+ //6
					"	A.REPOSSESSION_NO REPOSSESSION_NO, "+ //9
					"	NVL(TO_CHAR(A.TRN_DATE,'DD-MM-YYYY'),'-') TRN_DATE, "+ //10
					"	NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),'-') EFF_VAL_DATE, "+ //12
					"	C.APPLICATION_NO APPLICATION_NO "+ //13
					"	FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					"	WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND A.FINANCE_NO=C.FINANCE_NO AND  "+
					"	A.ACTIVE_STATUS IN ('C','Y') AND B.ACTIVE_STATUS='APP'  "+
					//"   AND UPPER(NVL(B.YARD_CODE,' ')) LIKE UPPER('"+m_yard_code+"%')  "+ 
					"   AND TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					"   AND TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					"   AND A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE ACTIVE_STATUS = 'TERM_CHECK' AND APPLY_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY'))"+
					m_filter +
					m_yard_code_sql +
					
					" UNION  "+
					
					//"SELECT DISTINCT "+
					"SELECT  "+
					"	A.INVENTORY_NO INVENTORY_NO, "+  //1
					"	A.FINANCE_NO FINANCE_NO, "+ //2
					"	B.CLIENT_CODE CLIENT_CODE, "+ //3
					"	B.VEHICLE_NO VEHICLE_NO, "+ //5
					"	A.SEIZER_CODE SEIZER_CODE, "+ //6
					"	A.REPOSSESSION_NO REPOSSESSION_NO, "+ //9
					"	NVL(TO_CHAR(A.TRN_DATE,'DD-MM-YYYY'),'-') TRN_DATE, "+ //10
					"	NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),'-') EFF_VAL_DATE, "+ //12
					"	C.APPLICATION_NO APPLICATION_NO "+ //13
					"	FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					"	WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND A.FINANCE_NO=C.FINANCE_NO AND  "+
					//"	A.ACTIVE_STATUS IN ('C','R') AND  B.ACTIVE_STATUS='APP'  "+
					"	A.ACTIVE_STATUS IN ('R') AND  B.ACTIVE_STATUS='APP'  "+
					//"   AND UPPER(NVL(B.YARD_CODE,' ')) LIKE UPPER('"+m_yard_code+"%')  "+ 
					"   AND TO_DATE(TO_CHAR(NVL(A.MOD_DATE,B.ENT_DATE),'DD-MM-YYYY'),'DD-MM-YYYY')> TO_DATE('"+m_date+"','DD-MM-YYYY') AND "+
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					"   AND TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					"   AND A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE ACTIVE_STATUS = 'TERM_CHECK' AND APPLY_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY'))"+
					m_filter +	
					m_yard_code_sql +
					
					" ) "+
					" ");
					
					*/
				
				rs = stmt.executeQuery (" "+
					" SELECT COUNT(INVENTORY_NO) FROM(  "+
					//" SELECT DISTINCT "+
					" SELECT  "+
					"	A.INVENTORY_NO INVENTORY_NO "+  //1
					"	FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					"	WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND A.FINANCE_NO=C.FINANCE_NO AND  "+
					"	A.ACTIVE_STATUS IN ('C','Y') AND B.ACTIVE_STATUS='APP'  "+
					//"   AND UPPER(NVL(B.YARD_CODE,' ')) LIKE UPPER('"+m_yard_code+"%')  "+ 
					//"   AND TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					//"   AND TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					"   AND TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					"   AND TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					"   AND A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE ACTIVE_STATUS = 'TERM_CHECK' AND APPLY_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY'))"+
					m_filter +
					m_yard_code_sql +
					m_ro_filter_sql +
					
					" UNION  "+
					
					//"SELECT DISTINCT "+
					"SELECT  "+
					"	A.INVENTORY_NO INVENTORY_NO "+  //1
					"	FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
					"	WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND A.FINANCE_NO=C.FINANCE_NO AND  "+
					//"	A.ACTIVE_STATUS IN ('C','R') AND  B.ACTIVE_STATUS='APP'  "+
					"	A.ACTIVE_STATUS IN ('R') AND  B.ACTIVE_STATUS='APP'  "+
					//"   AND UPPER(NVL(B.YARD_CODE,' ')) LIKE UPPER('"+m_yard_code+"%')  "+ 
					"   AND TO_DATE(TO_CHAR(NVL(A.MOD_DATE,B.ENT_DATE),'DD-MM-YYYY'),'DD-MM-YYYY')> TO_DATE('"+m_date+"','DD-MM-YYYY') AND "+
					//"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					//"   AND TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE(A.FINANCE_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					"   TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					"   AND TO_DATE("+m_schema_name+".AF_RE_VEH_INV_APP_DATE_2(A.REPOSSESSION_NO),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+ 
					"   AND A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE ACTIVE_STATUS = 'TERM_CHECK' AND APPLY_DATE <= TO_DATE('"+m_date+"','DD-MM-YYYY'))"+
					m_filter +	
					m_yard_code_sql +
					m_ro_filter_sql +
					
					" ) "+
					" ");
				
				
				//	out.println("<br>");		
				
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
				out.println("<br>");			
				int j=0;
				if(rs.next()){
					out.println("<tr >");
					out.println("<td width='20%'  align='left'><b>Total No of Asset</td>");
					out.println("<td width='*%'  align='left'><b>"+rs.getInt(1) +"</td>");
					//out.println("<td width='*%'  align='left'>&nbsp;</td>");
					out.println("</tr>");
				}		
				
				out.println("</table >"); 
				out.flush();
			}
			
			// end by udara 08-06-2015
			
			
			
			
		}
		
		
		catch (Exception ex) {
			ex.printStackTrace();
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}

