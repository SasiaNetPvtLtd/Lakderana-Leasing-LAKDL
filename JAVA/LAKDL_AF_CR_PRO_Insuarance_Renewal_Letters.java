
//--
//SCREEN NAME:COLLECTION PROCESS
//CREATED BY:NUWAN DE SILVA 
//DATE/TIME:26-04-2007
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_PRO_Insuarance_Renewal_Letters extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf;
	java.lang.Math a;
	Statement stmt,stmt1;
	public ResultSet rs,rs1;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			String m_username = m_sn_methods.username;
			String header_name=m_sn_methods.header_name.trim();
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			
			stmt = conn.createStatement ();
			stmt1 = conn.createStatement ();
			
			String m_date_dd="";
			String m_date_mm="";
			String m_date_yy="";
			String m_val_date="";
			String m_month="";
			
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			String m_chksql=req.getParameter("chksql");		
		
	    String m_sort_column   = "FINANCE_NO";	
		  String m_order_by_type = "ASC";
			
			
		  if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
		  m_sort_column = req.getParameter("sort_column");
		  m_order_by_type = req.getParameter("order_by_type");
		  }
			
		
			if(m_chksql.equals("main_page")){ 
			
			stmt = conn.createStatement ();

			rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD'), "+
																	"TO_CHAR(SYSDATE,'MM'), "+
																	"TO_CHAR(SYSDATE,'YYYY'), "+
																	"TO_CHAR(ADD_MONTHS(SYSDATE,1),'MON') "+
																	"FROM DUAL ");
								
		  if(rs.next()){
			m_date_dd=rs.getString(1);
			m_date_mm=rs.getString(2);
			m_date_yy=rs.getString(3);
			m_month=rs.getString(4);
			}
			
						
				
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Advertistment Offers Process</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			
			out.println("var b_flag=0;");
			out.println("var m_order_by_type_old=\"ASC\";");
			out.println("var m_sort_col_old=\"FINANCE_NO\";");
			
			
						
			
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
			
			out.println("document.Form1.MONTH.value='"+m_month+"'	"); 
			
			out.println("}	"); 
			
			
			
			out.println("function assign_system_date(){	"); 
			
			    out.println("document.Form1.VAL_DAY.value='"+m_date_dd+"'");
					out.println("document.Form1.VAL_MONTH.value='"+m_date_mm+"'");
					out.println("document.Form1.VAL_YEAR.value='"+m_date_yy+"'");
					
					//m_val_date=m_date_dd+"-"+m_date_mm+"-"+m_date_yy;
					m_val_date=m_date_mm+"-"+m_date_yy;	
					out.println("document.Form1.hid_bank_date.value='"+m_val_date+"'");
			   out.println("get_renewal_data(document.Form1.hid_bank_date.value);");

					
			out.println("}	"); 
			
			
			//Used To Display The Letter Format

			out.println("function show_letter(m_finance_no,m_client_code,m_invoice_no,m_revenue_license_date,m_luxury_tax_date,m_driving_license_date){	"); 
			
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Renewal_Data_Remind_Letter?chksql=main_page&Hid_scr_name='+document.Form1.Hid_scr_name.value+'&finance_no='+m_finance_no+'&client_no='+m_client_code+'&invoice_no='+m_invoice_no+'&revenue_license_date='+m_revenue_license_date+'&luxury_tax_date='+m_luxury_tax_date+'&driving_license_date='+m_driving_license_date+'';"); 
			out.println("window.open(m_url,'displayWindow3','left=250,top=60,width=650,height=600,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 

			
			
			out.println("}	"); 
			
			
			

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Insuarance_Renewal_Letters?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Insuarance_Renewal_Letters?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_RE_Renewal_Data\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection Process - Renewal Letters - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection Process - Renewal Letters - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
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
			
			out.println("function get_renewal_data(){");
			//out.println("alert('as'+val);");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Renewal_Data?chksql=view&data_val=\"+val;");
			out.println("m_from_date = document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value");
			out.println("m_to_date = document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value");
			out.println("finance_no = document.Form1.TXT_FINANCE_NO.value;");
			out.println("m_screen_mode = document.Form1.hid_status.value;");

			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Insuarance_Renewal_Letters?chksql=view&finance_no=\"+finance_no+\"&screen_mode=\"+m_screen_mode+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Renewal_Data?chksql=view&finance_no=\"+finance_no+\"&in_done=\"+insuranceDone+\"&data_val=\"+val;");
			out.println("load_interface(m_url,'NORM');");
			//out.println("window.open(m_url);");
			out.println("}"); 
					
			out.println("function get_vector_normal(http_response) {");
			out.println(" m_table.innerHTML = ''; ");
			out.println(" m_table.innerHTML = http_response; ");
			out.println("}");
					
			out.println("function check_date(){ ");
		 
			out.println("var date='' ");

			
			out.println(" if((document.Form1.VAL_DAY.value !=\"\")&&(document.Form1.VAL_MONTH.value !=\"\")&&(document.Form1.VAL_YEAR.value !=\"\")){");
			
			out.println("  if(checkMonthLength(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)){");
					
			//out.println("date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
			
			out.println("date=document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");		
			
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
			out.println("var date1='' ");
			out.println("var date2='' ");
		    out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("     document.Form1.VAL_DAY1.value=v_date;");
			out.println("     document.Form1.VAL_MONTH1.value=v_month;");
			out.println("     document.Form1.VAL_YEAR1.value=val;");
			out.println("date1=v_date+'-'+v_month+'-'+val;");
			//out.println("document.Form1.hid_from_date.value=date1");
			out.println("}");
				
			out.println("  if(document.Form1.hid_cal_date.value=='3'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("     document.Form1.VAL_DAY2.value=v_date;");
			out.println("     document.Form1.VAL_MONTH2.value=v_month;");
			out.println("     document.Form1.VAL_YEAR2.value=val;");
			out.println("date2=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
			//out.println("document.Form1.hid_to_date.value=date2");
			out.println("}");

			out.println("}");

			
			
			out.println("function help_button_View() {");		
			out.println("if(document.Form1.VAL_DAY1.value ==''  || document.Form1.VAL_MONTH1.value=='' || document.Form1.VAL_YEAR1.value=='' ||  document.Form1.VAL_DAY2.value=='' || document.Form1.VAL_MONTH2.value=='' || document.Form1.VAL_YEAR2.value==''){ ");
			out.println("alert('Enter date range to view details.');");
			out.println("}");
			out.println("else{");
			out.println("get_renewal_data();");
			out.println("}");
			out.println("}");
						
			out.println("function change_val_receipt_status(obj){")	;
								
			out.println("if(obj.checked==true){");
			out.println("obj.value='on'");
			
			out.println("}else if(obj.checked==false){");
			out.println("obj.value='off'");
			out.println("}");
			out.println("}");			
			
			
			
				out.println("function sort_data(m_sort_col) {");
				
				//out.println("alert('"+m_order_by_type+"');");
				
				out.println("	 m_order_by_type = 'ASC'; ");  
			//	out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
				out.println("	 if(m_sort_col==m_sort_col_old){");
				//out.println("	   if('"+m_order_by_type+"'=='DESC'){");
				out.println("	   if(m_order_by_type_old=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
			  out.println("       m_order_by_type = 'DESC'; ");
			  out.println("    }");
			  out.println("  }else{");
			  out.println("    m_order_by_type = 'ASC'; ");
			  out.println("  }");
				
				out.println("    m_order_by_type_old =m_order_by_type; ");
				out.println("    m_sort_col_old =m_sort_col; ");	
				//out.println("alert('m_order_by_type'+m_order_by_type);");
				
	      out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Renewal_Data?chksql=view&data_val=\"+document.Form1.MONTH.value+\"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
			  //out.println(" window.location.href=m_url;"); 
				// out.println(" window.open(m_url);"); 
				out.println("load_interface(m_url,'NORM');");

				out.println("}");

			
			out.println("function load_detail_scr(val1,val2,val3,val4,val5,val6){");//val1,val2,val3,val4,val5,val6
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Insuarance_Renewal_Letters?chksql=letter&print=TRUE&policy_no=\"+val1+\"&debit_no=\"+val2+\"&client_code=\"+val3+\"&client_name=\"+val4+\"&fin_no=\"+val5+\"&in_no=\"+val6+\"\";"); 
			out.println("window.open(m_url,'displayWindow2','left=175,top=60,width=650,height=600,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");


			out.println("function load_detail_scr2(val1,val2,val3,val4,val5,val6){");//val1,val2,val3,val4,val5,val6
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Insuarance_Renewal_Letters?chksql=letter2&print=TRUE&policy_no=\"+val1+\"&debit_no=\"+val2+\"&client_code=\"+val3+\"&client_name=\"+val4+\"&fin_no=\"+val5+\"&in_no=\"+val6+\"\";"); 
			out.println("window.open(m_url,'displayWindow2','left=175,top=60,width=650,height=600,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");

			
			out.println("function MyDialog(){"); 
				out.println("    this.valout   = new Array(10);"); 
				out.println("}		"); 
					
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("		 popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("		if(oBj.valout[1] ==\" \"){"); 
			out.println("    clear_fields(); ");
			out.println("		} else ");
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("	if(oBj.valout[1]=='Next')  {");
			out.println("		Next(oBj.valout[3],oBj.valout[4],Hid_No,Crit,Sql,IfCount);");
			out.println("	}");
			out.println("	else if  (oBj.valout[1]=='Prev') {");
			out.println("		Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
			out.println("	}		");
			out.println("	else if(oBj.valout[1] == 'Close'){");
			out.println("	}");
			out.println("	else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
			out.println("	if(IfCount=='4'){"); 
			out.println("		finance_assign(oBj);"); 
			out.println("	}");
			out.println("	}"); 
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	else{	"); 
			out.println("    clear_fields(); ");
			out.println("	}	"); 
			out.println("	}	"); 
			out.println("}");  

			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			
			out.println("function help_finance(){"); 
			out.println("document.Form1.hid_help_type.value=\"4\";"); 
			out.println("Crit=document.Form1.TXT_FINANCE_NO.value+\"@\";"); 
			out.println("HelpBox('1','10','0',Crit,'m_help_TXT_FINANCE_NO_4','4');");
			out.println("}");
		 
		  out.println("function finance_assign(oBj) {");				
			out.println("document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("}");
			
			out.println("function clear_fields(){ ");
			out.println("if(document.Form1.hid_help_type.value==\"4\"){");
			out.println("document.Form1.TXT_FINANCE_NO.value=\"\";");
			out.println("}");
			out.println("}");
			
			out.println("</script>"); 
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\"> "); //load_lock(), header(),add_row() assign_system_date()
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_RENEWAL_DATA\">"); 
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<input type=hidden name='hid_cal_date' value=\"\"></td>");
			out.println("<input type=hidden name='hid_row_no' value=\"\"></td>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_bank_date' VALUE=\"\">"); 
			//out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=0>"); 
			

			
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Renewal Letters</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
		
		
		    out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%'></td>");
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			
			out.println("<td class='pdn_txtpos' height='150' valign='top'>"); 	
			
			
			
					out.println("<table align='center' width='100%' class='table' border='0' >"); 				
					out.println("<tr class=tr_input>");
					out.println("<td width='10%' ID=VDATE>From</td>");
					out.println("<td width='20%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" > ");//onblur=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)
					out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" > ");
					out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" ><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
					out.println("</td>");
					out.println("<td width='10%' ID=VDATE>To</td>");
					out.println("<td width='20%' ><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" > ");
					out.println("    <input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" > ");
					out.println("    <input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" ><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
					out.println("</td>");
					out.println("<td width='20%' ><input class='but_input' type='button' name='BUT_VIEW' value=\"View\" style='width:80' onClick=\"help_button_View()\"><input class='txt_input' type='hidden' name='TXT_FINANCE_NO' ></td>"); 
					out.println("<td width='*%'></td>");
					out.println("</tr>");	
					out.println("</table>");

			/*out.println("<table  width='100%' class='table' border='0'>"); 
			out.println("<tr >"); 
			//out.println("<td  width='20%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance Number</DIV></td>"); 
			//out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='20' size='30'  OnBlur=\"help_finance()\">"); 
			//out.println("<input class='but_input' type='button' name='BUT_FINANCE_NO' value=\"Help\" onClick=\"help_finance()\" > </td>"); 
			out.println("<td width='20%' ><input class='but_input' type='button' name='BUT_VIEW' value=\"View\" style='width:80' onClick=\"help_button_View()\"><input class='txt_input' type='hidden' name='TXT_FINANCE_NO' ></td>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>");
			out.println("</table>"); */
			
			
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table  width='100%' class='table' border='0'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
			  out.println("</tr>"); 
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
		
		
		
		else if(m_chksql.equals("letter")){
		
			int i=0;		
			String m_client_name = req.getParameter("client_name");	
      		String m_policy_no= req.getParameter("policy_no");
  			String m_debit_no = req.getParameter("debit_no");
			String m_client_code = req.getParameter("client_code");
			String m_print =req.getParameter("print");
      		String m_fin_no= req.getParameter("fin_no");
      		String m_in_no= req.getParameter("in_no");
				
			String m_company = "";
			String m_Letter_date="";
			String m_add="";
			String m_city="";
			String m_start_date="";
			String m_reg_no="";
			String m_user_name="";
			String m_emp_id="";
			String m_designation="";

			double m_sum_insured=0.00;
			double m_premium=0.00;
			double m_tax=0.00;

			rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'ddth') ||' '||TO_CHAR(SYSDATE,'Month')||' '||TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
								
			boolean more = rs.next();
			if(more){
			m_Letter_date=rs.getString(1);
			}
			
			rs = stmt.executeQuery ("SELECT  TITLE || '. ' || UPPER(INITIALS) || UPPER(SURNAME), UPPER(ADDRESS1) || ', ' || UPPER(ADDRESS2) || ', ' ,UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)) FROM "+m_schema_name+".AF_CO_MAS_CLIENT WHERE CLIENT_CODE='"+m_client_code+"' ");
								
			boolean more1 = rs.next();
			if(more1){
			m_client_name=rs.getString(1);
			m_add=rs.getString(2);
			m_city=rs.getString(3);
			}

			rs = stmt.executeQuery ("SELECT  NVL(TO_CHAR(START_DATE,'DD.MM.YYYY'),'-'), NVL(SUM_INSSURED,0.00), NVL(PREMIUM,0.00), NVL(TAX_DUE,0.00)  FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA WHERE POLICY_NO='"+m_policy_no+"' AND DEBIT_NOTE_NO='"+m_debit_no+"' ");
								
			boolean more2 = rs.next();
			if(more2){
			m_start_date=rs.getString(1);
			m_sum_insured=rs.getDouble(2);
			m_premium=rs.getDouble(3);
			m_tax=rs.getDouble(4);
			}
			
			rs = stmt.executeQuery ("SELECT NVL(REG_NO,'-')  FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS WHERE INVOICE_NO='"+m_in_no+"' ");
								
			boolean more3 = rs.next();
			if(more3){
			m_reg_no=rs.getString(1);
			}
			
			rs = stmt.executeQuery ("SELECT NVL(COMPANY_NAME,'-')  FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS  ");
								
			boolean more4 = rs.next();
			if(more4){
			m_company=rs.getString(1);
			}
			
			rs = stmt.executeQuery(" SELECT NAME,EMP_ID  FROM "+m_schema_name+".CO_CO_MAS_USER  WHERE USER_ID='"+m_username+"'");
					
			boolean more5 = rs.next();	
			if(more5){
			m_user_name = rs.getString(1);
			m_emp_id = rs.getString(2);
			}
					
	 		rs = stmt.executeQuery("SELECT NVL(DESIGNATION_NAME,'-')  FROM "+m_schema_name+".CO_CO_MAS_DESIGNATION  "+
								   " WHERE DESIGNATION_CODE=(SELECT DESIGNATION_CODE FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE  WHERE EMP_CODE=upper('"+m_emp_id+"')) ");			
					
			boolean more6 = rs.next();				
			if(more6){
			m_designation = rs.getString(1);
			}

			out.println("<html><head>"); 
			out.println("<title>Renewal Letter</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
            out.println("<script>");
			
			out.println("function save_data(){");
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Insuarance_Renewal_Letters?chksql=letter&client_name="+m_client_name+"&policy_no="+m_policy_no+"&debit_no="+m_debit_no+"&client_code="+m_client_code+"&fin_no="+m_fin_no+"&in_no="+m_in_no+"&print=FALSE\";");  
		    out.println(" window.location.href=m_url;");		
			out.println("m_table.innerHTML=\"\" ");		
			out.println("window.print();");		
			out.println("}");
			
		    out.println("function add_button(){");
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
			}
			else
			{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	    out.println("m_writedata+'</table>';");
			}
			out.println("}");
			
			out.println("</script>");
			
				
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()
				
			out.println("<body bgcolor='white'><br>");
			out.println("<form name='Form1'>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		    out.println("</tr>"); 
			out.println("</table>");
      
			out.println("<blockquote><font size=3><p style='text-align:center'>");	

			out.println("</font></p></blockquote>");	
			
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");

			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;'>"+m_Letter_date+"</tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;'><input class='txt_input6' type='text' name='TXT_NIL' maxlength='300'  value='"+m_client_name+"' size='300' style='width:450px;font-family:Times New Roman; font-size:11pt;'></td></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;'><input class='txt_input6' type='text' name='TXT_NIL' maxlength='300'  value='"+m_add+"'         size='300' style='width:450px;font-family:Times New Roman; font-size:11pt;'></td></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;'><input class='txt_input6' type='text' name='TXT_NIL' maxlength='300'  value='"+m_city+"'        size='300' style='width:300px;font-family:Times New Roman; font-size:11pt;'></td></tr>");
			out.println("</table>");	
			
			out.println("<br>");
									
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body'  style='font-family:Times New Roman; font-size:11pt;'>Dear Sir/Madam,</td></tr>");
			out.println("</table>");
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body'  style='font-family:Times New Roman; font-size:11pt;'><b><u>RENEWAL OF VEHICLE INSURANCE</u></b></td></tr>");
			out.println("</table>");
			out.println("<br>");

			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='25%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;'><b>Renewal Date</b></td><td width='25%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;'><b>:&nbsp;"+m_start_date+"</b></td><td width='25%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;'><b>Sum Insured</b></td><td width='25%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;'><b>:&nbsp;<input class='txt_input6' type='text' name='TXT_NIL' maxlength='300'  value='"+nf.format(m_sum_insured)+"' size='300' style='font-family:Times New Roman; font-size:11pt;font-weight:bold;width:300'></b></td></tr>");
			out.println("<tr><td width='25%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;'><b>Agreement No</b></td><td width='25%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;'><b>:&nbsp;"+m_fin_no+"</b></td><td width='25%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;'><b>Premium Payable</b></td><td width='25%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;'><b>:&nbsp;<input class='txt_input6' type='text' name='TXT_NIL' maxlength='300'  value='"+nf.format(m_premium)+"' size='300' style='font-family:Times New Roman; font-size:11pt;font-weight:bold;width:300'></b></td></tr>");
			out.println("<tr><td width='25%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;'><b>Vehicle No</b></td><td width='25%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;'><b>:&nbsp;"+m_reg_no+"</b></td><td width='25%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;' ><b>No Claim Bonus</b></td><td width='25%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;'><b>:&nbsp;<input class='txt_input6' type='text' name='TXT_NIL' maxlength='300'  value='ALLOWED' size='300' style='font-family:Times New Roman; font-size:11pt;font-weight:bold;width:300'></b></td></tr>");
			out.println("</table>");
			out.println("<br>");

			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' ><b>Tax Amount Due Is (If applicable): &nbsp;<input class='txt_input6' type='text' name='TXT_NIL' maxlength='300'  value='"+nf.format(m_tax)+"' size='300' style='font-family:Times New Roman; font-size:11pt;font-weight:bold;width:300'></b></td></tr>");
			out.println("</table>");
			out.println("<hr>");
			
		 								
		    String data="The insurance cover of the above asset is due for renewal on the date specified. "+
			            "Please ensure that the above mentioned premium is settled on or before the renewal date directly to <B>M/s "+m_company+"<B> ";  						
								
								
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
						
            out.println("<br>");            
     
			//out.println("<table border='0' width='90%' class='table'>"); 		
			//out.println("<td width='*%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;text-align:justify' ><B><u>IMPORTANT:<u></B></td>");
			//out.println("</tr></table>");				
     
			data = "Insurance cover of the asset will be automatically renewed with Janashakthi Insurance PLC on the above mentioned date.";
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='15%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;text-align:right' valign='top'><b>IMPORTANT:&nbsp;&nbsp;&nbsp;&nbsp;1.&nbsp;</b></td><td width='*%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;text-align:justify' ><b>"+data+"</b></td>");
			out.println("</tr></table>");	
			//out.println("<br>");
			
			data = "Changes pertaining to (Changing the Insurance Company, Sum Insured and Additional Covers etc.) should be notified in writing, within 7 days of this letter.";
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='15%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;text-align:right' valign='top'><b>2.&nbsp;</b></td><td width='*%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;text-align:justify' ><b>"+data+"</b></td>");
			out.println("</tr></table>");	
			//out.println("<br>");
			
			data = "The sum insured should represent the market value to obtain a proper indemnity and to ensure that the cover obtained by you, Suits your present requirements.";
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='15%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;text-align:right' valign='top'><b>3.&nbsp;</b></td><td width='*%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;text-align:justify' ><b>"+data+"</b></td>");
			out.println("</tr></table>");	
			out.println("<br>");
			
			data = "The premium payable preferably should be by cash or cheque to reach us before the renewal date. If the renewal date falls on a week-end or on a holiday, please ensure that the premium is sent on a working day prior to it.";
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='5%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;text-align:right' valign='top'>*&nbsp;&nbsp;</td><td width='*%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");	
			out.println("<br>");
			
			data = "Diesel & Luxury taxes are payable by cash only (If applicable). ";
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='5%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;text-align:right' valign='top'>*&nbsp;&nbsp;</td><td width='*%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");	
			out.println("<br>");
			
			data = "If you have any further clarification please do not hesitate to contact, <b><input class='txt_input6' type='text' name='TXT_NIL' maxlength='300'  value='Shammi on 0777-598204 or 0117-577577.' size='300' style='width:400px;font-weight:bold;font-family:Times New Roman; font-size:11pt;'></b> ";
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");	
		
            data="Thanking you for your cooperation & patronage. ";
			
			out.println("<br>");
									
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr>");
			out.println("<td width='*%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
			
			data="Yours Faithfully ";
			
			out.println("<br>");	
	
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr>");
			out.println("<td width='*%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;text-align:justify' >"+data+"</td></tr>");
			out.println("</tr></table>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr>");
			out.println("<td width='*%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;text-align:justify' ><B>"+m_company+"</B></td></tr>");
			out.println("</tr></table>");

			out.println("<br>");
			out.println("<br>");	
			
			out.println("<table border='0' width='90%' class='table'>");  
			out.println("<tr>");
			out.println("<td width='50%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;text-align:justify' >............................</td>");
			out.println("</tr>");	
			out.println("<tr>");
			out.println("<td width='50%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;text-align:justify' ><b>"+m_user_name+"</b></td>"); 
			out.println("</tr>");	
			out.println("<tr>");
			out.println("<td width='50%' class='rep-body' style='font-family:Times New Roman; font-size:11pt;text-align:justify' ><b>"+m_designation+"</b></td>"); 
			out.println("</tr></table>");
			out.println("</font></p></blockquote>");		
		  	out.println("</form></body></html>");

}

		else if(m_chksql.equals("letter2")){
		
			int i=0;		
			String m_client_name = req.getParameter("client_name");	
      		String m_policy_no= req.getParameter("policy_no");
  			String m_debit_no = req.getParameter("debit_no");
			String m_client_code = req.getParameter("client_code");
			String m_print =req.getParameter("print");
      		String m_fin_no= req.getParameter("fin_no");
      		String m_in_no= req.getParameter("in_no");
				
			String m_company = "";
			String m_Letter_date="";
			String m_add="";
			String m_city="";
			String m_start_date="";
			String m_reg_no="";
			String m_user_name="";
			String m_emp_id="";
			String m_designation="";

			double m_sum_insured=0.00;
			double m_premium=0.00;
			double m_tax=0.00;

			rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM DUAL ");
								
			boolean more = rs.next();
			if(more){
			m_Letter_date=rs.getString(1);
			}
			
			rs = stmt.executeQuery ("SELECT  TITLE || '. ' || UPPER(INITIALS) || UPPER(SURNAME), UPPER(ADDRESS1) || ', ' || UPPER(ADDRESS2) || ', ' ,UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)) FROM "+m_schema_name+".AF_CO_MAS_CLIENT WHERE CLIENT_CODE='"+m_client_code+"' ");
								
			boolean more1 = rs.next();
			if(more1){
			m_client_name=rs.getString(1);
			m_add=rs.getString(2);
			m_city=rs.getString(3);
			}

			rs = stmt.executeQuery ("SELECT  NVL(TO_CHAR(START_DATE,'DD.MM.YYYY'),'-'), NVL(SUM_INSSURED,0.00), NVL(PREMIUM,0.00), NVL(TAX_DUE,0.00)  FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA WHERE POLICY_NO='"+m_policy_no+"' AND DEBIT_NOTE_NO='"+m_debit_no+"' ");
								
			boolean more2 = rs.next();
			if(more2){
			m_start_date=rs.getString(1);
			m_sum_insured=rs.getDouble(2);
			m_premium=rs.getDouble(3);
			m_tax=rs.getDouble(4);
			}
			
			rs = stmt.executeQuery ("SELECT NVL(REG_NO,'-')  FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS WHERE INVOICE_NO='"+m_in_no+"' ");
								
			boolean more3 = rs.next();
			if(more3){
			m_reg_no=rs.getString(1);
			}
			
			rs = stmt.executeQuery ("SELECT NVL(COMPANY_NAME,'-')  FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS  ");
								
			boolean more4 = rs.next();
			if(more4){
			m_company=rs.getString(1);
			}
			
			rs = stmt.executeQuery(" SELECT NAME,EMP_ID  FROM "+m_schema_name+".CO_CO_MAS_USER  WHERE USER_ID='"+m_username+"'");
					
			boolean more5 = rs.next();	
			if(more5){
			m_user_name = rs.getString(1);
			m_emp_id = rs.getString(2);
			}
					
	 		rs = stmt.executeQuery("SELECT NVL(DESIGNATION_NAME,'-')  FROM "+m_schema_name+".CO_CO_MAS_DESIGNATION  "+
								   " WHERE DESIGNATION_CODE=(SELECT DESIGNATION_CODE FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE  WHERE EMP_CODE=upper('"+m_emp_id+"')) ");			
					
			boolean more6 = rs.next();				
			if(more6){
			m_designation = rs.getString(1);
			}

			out.println("<html><head>"); 
			out.println("<title>Renewal Letter</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
            out.println("<script>");
			
			out.println("function save_data(){");
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Insuarance_Renewal_Letters?chksql=letter2&client_name="+m_client_name+"&policy_no="+m_policy_no+"&debit_no="+m_debit_no+"&client_code="+m_client_code+"&fin_no="+m_fin_no+"&in_no="+m_in_no+"&print=FALSE\";");  
		    out.println(" window.location.href=m_url;");		
			out.println("m_table.innerHTML=\"\" ");		
			out.println("window.print();");		
			out.println("}");
			
		    out.println("function add_button(){");
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
			}
			else
			{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	    out.println("m_writedata+'</table>';");
			}
			out.println("}");
			
			out.println("</script>");
			
				
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()
				
			out.println("<body bgcolor='white'><br>");
			out.println("<form name='Form1'>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		    out.println("</tr>"); 
			out.println("</table>");
      
			out.println("<blockquote><font size=3><p style='text-align:center'>");	

			out.println("</font></p></blockquote>");	
			
			out.println("<blockquote><font size=2px><p style='text-align:justify;' class='rep-body'>");	
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' >"+m_Letter_date+"</tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' ><input class='txt_input6' type='text' name='TXT_NIL' maxlength='300'  value='"+m_client_name+"' size='300' style='width:400px'></td></tr>");//;font-family:aKandyNew
			out.println("<tr><td width='*%' class='rep-body' ><input class='txt_input6' type='text' name='TXT_NIL' maxlength='300'  value='"+m_add+"' size='300' style='width:400px'></td></tr>");
			out.println("<tr><td width='*%' class='rep-body' ><input class='txt_input6' type='text' name='TXT_NIL' maxlength='300'  value='"+m_city+"' size='300' style='width:300px'>.</td></tr>");
			out.println("</table>");	
			
			out.println("<br><br>");
			
			String data="mhWmy`@zÎ,mhWÉyª,"; 
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body' style='text-align:justify;font-family:aKandyNew;font-size:13px;' >"+data+"</td>");
			out.println("</tr></table>");		
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body'><b><u><font face='aKandyNew' size=2px>r:z a`vrzy aÆW ¿Ým sMbN[vû.</font></u></b></td></tr>");
			out.println("</table>");
			out.println("<br>");

			out.println("<table border='0' width='90%' class='table'>"); 
			out.println("<tr><td width='25%' class='rep-body' ><font face='aKandyNew' size=2px><b>aÆW ¿Ý@M Øny</font></b></td><td width='25%' class='rep-body' ><b>:&nbsp;"+m_start_date+"</b></td><td width='25%' class='rep-body' ><font face='aKandyNew' size=2px><b>r:w vçn`km</font></b></td><td width='25%' class='rep-body' ><b>:&nbsp;<input class='txt_input6' type='text' name='TXT_NIL' maxlength='300'  value='"+nf.format(m_sum_insured)+"' size='300' style='font-family:Times New Roman; font-size:11pt;font-weight:bold;width:300'></b></td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><font face='aKandyNew' size=2px><b>r:z oPÕ aAky</font></b></td><td width='25%' class='rep-body' ><b>:&nbsp;"+m_policy_no+"</b></td><td width='25%' class='rep-body' ><font face='aKandyNew' size=2px><b>@gìy ýó Ëql</font></b></td><td width='25%' class='rep-body' ><b>:&nbsp;<input class='txt_input6' type='text' name='TXT_NIL' maxlength='300'  value='"+nf.format(m_premium)+"' size='300' style='font-family:Times New Roman; font-size:11pt;font-weight:bold;width:300'></b></td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><font face='aKandyNew' size=2px><b>v`hn aAky</font></b></td><td width='25%' class='rep-body' ><b>:&nbsp;"+m_reg_no+"</b></td><td width='25%' class='rep-body' ><font face='aKandyNew' size=2px><b>°ìäM aAky</font></b></td><td width='25%' class='rep-body' ><b>:&nbsp;"+m_fin_no+"</b></td></tr>");
			out.println("</table>");
			out.println("<br>");

			/*out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' ><b>Tax Amount Due Is (If applicable): &nbsp;"+nf.format(m_tax)+"</b></td></tr>");
			out.println("</table>");
			out.println("<br>");*/
			out.println("<hr>");
			
		 								
		    data="ihw sqhN Ønt ob@G v`hn r:z`vrzy aÆW ¿Ýmt ÎyÉw b#ìN kr#z`kr ihw sqhN Ëql, r:z`vrzy aÆW ì@M Ønt sñ 2kt @pr oÝyNT fûn#NºyL sRìsS @k~p@R;N ÄÊtD sm`gm @vw l#¡mt slSvNn.";  						
								
								
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body' style='text-align:justify' ><font face='aKandyNew' size=2px>"+data+"</font></td>");
			out.println("</tr></table>");		
						
            out.println("<br>");            
     
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body' style='text-align:justify' ><B><u><font face='aKandyNew' size=2px>v#qgW</font><u></B></td>");
			out.println("</tr></table>");				
     
			
			data = "r:z`vrzy aÆW ¿Ým jn\\Kñ r:z sm`g@mN âÚ ¿Ýmt ap sm`g@M         k]mn`k`ÝWvy ñrzy kr a#ñ bv qNv` âçË.";
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='5%' class='rep-body' style='text-align:right' valign='top'>*&nbsp;&nbsp;</td><td width='*%' class='rep-body' style='text-align:justify' ><font face='aKandyNew' size=2px>"+data+"</font></td>");
			out.println("</tr></table>");	
			out.println("<br>");
			
			data = "ob@G r:z ËqL ap @vw b#r ì@mN anór#v r:z shñky @n`pm`v ob @vw w#p#@lN lb` g#Îmt ktýó krÐ a#w. ";
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='5%' class='rep-body' style='text-align:right' valign='top'>*&nbsp;&nbsp;</td><td width='*%' class='rep-body' style='text-align:justify' ><font face='aKandyNew' size=2px>"+data+"</font></td>");
			out.println("</tr></table>");	
			out.println("<br>");
			
			//data = "ìmâM - äsNw Ekn`yk -0117-577 532· 0117-577 577</b> ";
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body' style='text-align:justify' ><font face='aKandyNew' size=2px>ìmâM - <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300'  value='äsNw Ekn`yk - 0117-577 532 - 0117-577 577' size='300' style='width:600px;font-weight:bold;font-family:aKandyNew; font-size:12px;'></font></td>");
			                                                                                                        
			out.println("</tr></table>");	
		
			out.println("<br>");	
			out.println("<br>");	
			
			out.println("<table border='0' width='90%' class='table'>");  
			out.println("<tr>");
			out.println("<td width='50%' class='rep-body' style='text-align:justify' >............................</td>");
			out.println("</tr>");	
			out.println("<tr>");
			out.println("<td width='50%' class='rep-body' style='text-align:justify' ><font face='aKandyNew' size=2px>blylW Îl[`Ý.</font></td>"); 
			out.println("</tr>");	
			out.println("</table>");
			out.println("</font></p></blockquote>");		
		  	out.println("</form></body></html>");

}


//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		
		
		
		else if(m_chksql.equals("view")){		//MODIFIED BY SANDUN ON 03-10-2008
		String m_date=req.getParameter("data_val");
		String m_insuarane_type =req.getParameter("in_done");
		String m_finance_no = req.getParameter("finance_no");
		String m_business_type = req.getParameter("business_type");
		String m_screen_mode = "New";//req.getParameter("screen_mode");
		String m_from_date = req.getParameter("from_date");
		String m_to_date = req.getParameter("to_date");

		int j=1;
		if(m_screen_mode.equals("New")){
			rs=stmt.executeQuery(" SELECT DISTINCT A.FINANCE_NO, "+//1
								 " A.INSURED_BY, "+//2
								 " A.ASSET_DESCRIPTION, "+//3
								 " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE), "+//4
								 " B.ACTIVATED_DATE, "+//5
								 " DECODE(B.APPLICATION_STATUS,'ACTIVATED','Activated'), "+//6
								 " A.PRO_INVOICE_NO, "+//7
								 " B.APPLICATION_NO, "+//8
								 " A.POLICY_NO, "+	//9
								 " A.DEBIT_NOTE_NO, "+ //10
								 " B.CLIENT_CODE "+ //11	
								 " FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
								 " WHERE  A.FINANCE_NO=B.FINANCE_NO "+
								 " AND B.APPLICATION_STATUS='ACTIVATED' "+	
								 " AND A.BUSINESS_TYPE = 'NEW' "+
								 " AND A.POLICY_NO NOT IN "+
                                 " (SELECT Z.POLICY_NO "+
                                 " FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA Z "+
                                 " WHERE Z.BUSINESS_TYPE = 'RENEWAL' "+
                                 " AND  A.POLICY_NO = Z.POLICY_NO "+
                                 " AND B.FINANCE_NO = Z.FINANCE_NO) "+
                                 " AND A.START_DATE <=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
								 " AND A.START_DATE >=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
								 " ");
				

		}
			boolean more = rs.next();
			
			if(!more){
			out.println("<table align='center' width='100%' class='table' border=0>");
			out.println("<tr>"); 
			out.println("<td width='20%' align ='center'><font color='red'>No Data Found...!</font></td>"); 
			out.println("</tr>"); 
			out.println("</table>");
			}
			if(more){
			out.println("<table align='center' width='100%' class='table' border=0>"); 
		
			out.println("<tr class=tr_input>");
			out.println("<td align=right colspan=12><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
      out.println("</tr>");
			
			out.println("<br>");
			out.println("<hr>");
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='15%' align ='left'>Finance No.</td>"); 
			out.println("<td width='25%' align ='left'>Client Name</td>"); 
			out.println("<td width='10%' align ='left'>Policy No</td>"); 
			out.println("<td width='10%' align ='center'>Debit Note No</td>"); 
			out.println("<td width='10%' align ='center'>Letter1</td>"); 
			out.println("<td width='10%' align ='center'>Letter2</td>"); 
			out.println("</tr >"); 
			
			while(more){
			
			if(j%2==1){
			out.println("<tr class=tr_input>"); 
			}
			else{
			out.println("<tr class=tr_input1>"); 
			}
			
			out.println("<input type=hidden name=\"hid_pro_in_no\" value=\""+rs.getString(7)+"\"></td>");
			out.println("<input type=hidden name=\"hid_policy_no\" value=\""+rs.getString(9)+"\"></td>");
			out.println("<td width='20%' align ='left'>"+rs.getString(1)+"</td>"); 
			out.println("<td width='25%' align ='left'>"+rs.getString(4)+"</td>"); 
			out.println("<td width='10%' align ='left'>"+rs.getString(9)+"</td>");	
            out.println("<td width='10%' align ='left'>"+rs.getString(10)+"</td>");	
     		out.println("<td width='10%' align ='center'><input type='button' name='butt_detail1' class='mainbut' value='Letter1' onclick=\"load_detail_scr('"+rs.getString(9)+"','"+rs.getString(10)+"','"+rs.getString(11)+"','"+rs.getString(4)+"','"+rs.getString(1)+"','"+rs.getString(7)+"')\"></td>");  
     		out.println("<td width='10%' align ='center'><input type='button' name='butt_detail2' class='mainbut' value='Letter2' onclick=\"load_detail_scr2('"+rs.getString(9)+"','"+rs.getString(10)+"','"+rs.getString(11)+"','"+rs.getString(4)+"','"+rs.getString(1)+"','"+rs.getString(7)+"')\"></td>");  
			out.println("</tr>"); 			
			more = rs.next();
			j=j+1;
			}
		
		
			out.println("<br>");
			out.println("<tr class=tr_input>");//
			out.println("<td align=right colspan=11><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
      out.println("</tr>");
			}
      out.println("</table >"); 	
			}
			

			else	if(m_chksql.equals("load_details_btt")) //ADDED BY SANDUN ON 03-10-2008
			
			{
			
			String m_finance_no=req.getParameter("finance_no");
			String m_cli_name=req.getParameter("clint_name");
			String m_asset_deta=req.getParameter("asst_deta");
			String m_invoice_no=req.getParameter("invo_no");
			String m_scr_name=req.getParameter("sr_name");
						
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");

			out.println("<script>");
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println(" document.Form2.txt_policy_no.value=\"\";");
			out.println(" document.Form2.start_dd.value=\"\";");
			out.println(" document.Form2.start_mm.value=\"\";");
			out.println(" document.Form2.start_yy.value=\"\";");
			out.println(" document.Form2.end_dd.value=\"\";");
			out.println(" document.Form2.end_mm.value=\"\";");
			out.println(" document.Form2.end_yy.value=\"\";");
			out.println(" document.Form2.txt_sum_in.value=\"\";");
			out.println(" document.Form2.txt_premium.value=\"\";");
			out.println(" document.Form2.txt_in_company.value=\"\";");
			//out.println(" document.Form2.txt_insurance_done.value=\"\";");
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form2.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
							
			out.println("function load_c_date(val) {");
			out.println("var date1='' ");
			out.println("var date2='' ");
		  out.println("  if(document.Form2.hid_cal_date.value=='2'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("     document.Form2.start_dd.value=v_date;");
			out.println("     document.Form2.start_mm.value=v_month;");
			out.println("     document.Form2.start_yy.value=val;");
			out.println("date1=v_date+'-'+v_month+'-'+val;");
			out.println("document.Form2.hid_from_date.value=date1");
						
			out.println("}");
				
			out.println("  if(document.Form2.hid_cal_date.value=='3'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("     document.Form2.end_dd.value=v_date;");
			out.println("     document.Form2.end_mm.value=v_month;");
			out.println("     document.Form2.end_yy.value=val;");
			out.println("date2=document.Form2.end_dd.value+'-'+document.Form2.end_mm.value+'-'+document.Form2.end_yy.value;");
			out.println("document.Form2.hid_to_date.value=date2");
			
			out.println("}");

			out.println("}");
			
			out.println("function submit_data(){");
			out.println("if(validate_data()){");
			out.println("document.Form2.txt_policy_no.disabled=false;");
			out.println("	if(confirm(\"Are you sure, you want to save data?\")){ ");
			out.println("		document.Form2.action='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Save_Asset_Insurance_Detail';");  
			out.println("		document.Form2.submit();	");
			out.println("}");
			out.println("}");
			out.println("}");
			
			out.println("function validate_data(){");
			out.println("if(document.Form2.txt_policy_no.value==\"\"){;");
			out.println("alert('Please Enter Policy No..!');");
			out.println("document.Form2.txt_policy_no.focus();");
			out.println("return false;");
			out.println("}else if(document.Form2.start_dd.value==\"\" || document.Form2.start_mm.value==\"\" || document.Form2.start_yy.value==\"\"){");
			out.println("alert('Please Enter Start Date..!');");
			out.println("return false;");
			out.println("}else if(document.Form2.end_dd.value==\"\" || document.Form2.end_mm.value==\"\" || document.Form2.end_yy.value==\"\"){");
			out.println("alert('Please Enter End Date..!');");
			out.println("return false;");			
			out.println("}else if(document.Form2.txt_sum_in.value==\"\"){;");
			out.println("alert('Please Enter Inssured Sum..!');");
			out.println("document.Form2.txt_sum_in.focus();");
			out.println("return false;");
			out.println("}else if(document.Form2.txt_premium.value==\"\"){;");
			out.println("alert('Please Enter Premium Value..!');");
			out.println("document.Form2.txt_premium.focus();");
			out.println("return false;");
			out.println("}else if(document.Form2.txt_in_company.value==\"\"){;");
			out.println("alert('Please Enter Insurance Company Name..!');");
			out.println("document.Form2.txt_in_company.focus();");
			out.println("return false;");
			out.println("}else if(!chk_date()){;");//Added By Sandun on 01-01-2009
			out.println("alert('End date must be greater than Start Date..!');");
			out.println("return false;");
			out.println("}else{");
			out.println("return true;");
			out.println("}");
			out.println("}");
			
			out.println("function chk_date(){");//Added By Sandun on 01-01-2009
			out.println("end_date=document.Form2.end_dd.value+'-'+document.Form2.end_mm.value+'-'+document.Form2.end_yy.value;");
			out.println("start_date=document.Form2.start_dd.value+'-'+document.Form2.start_mm.value+'-'+document.Form2.start_yy.value;");
			out.println("var d1 = new Date(start_date);");
			out.println("var d2 = new Date(end_date);");		
			out.println("if(d2>d1){");		
			out.println("return true;");
			out.println("}");
			out.println("}");
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"  Collection Prossess - Asset Insurance Details - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"  Collection Prossess - Asset Insurance Details - \"+document.Form2.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function check_amt(obj,size){");
			out.println("if(obj.value!=''){"); 
			out.println("format_number(obj,18)"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function window_close(){");
			out.println("	if(confirm(\"Are you sure you want to close the screen?\")){ ");
			out.println("window.close()");
			out.println("}");
			out.println("}");
			
			out.println("function check_date(objdd,objmm,objyy) {"); 						
			out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
			out.println("  checkMonthLength(objdd,objmm,objyy);");
			//out.println("  validate_date(objdd,objmm,objyy,document.Form1.HID_SYS_VAL_DAY,document.Form1.HID_SYS_VAL_MONTH,document.Form1.HID_SYS_VAL_YEAR);");
      out.println("}");
			out.println("}");
			
			out.println("</script>");
			out.println("<body>");
			
			out.println("<form name=\"Form2\" method=post>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_from_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_to_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_finance_no' VALUE=\""+m_finance_no+"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_asset_deta' VALUE=\""+m_asset_deta+"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_invo_no' VALUE=\""+m_invoice_no+"\">");
			
		
			out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >");
			out.println("<tr>");
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
			out.println("<tr>");
			out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Collection Process - Renewal Letters  </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
			out.println("<table class=table align=center cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
			out.println("<tr>");			
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='submit_data()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");' onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");' onclick='window_close()' value=\"Close\"></td>");  
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
		
						
			out.println("<table width='80%' class='table' border='0'>"); 	
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>Finance No</td>"); 
			out.println("<td width='20%' align ='left'>"+m_finance_no+"</td>"); 
			out.println("</tr>");
			out.println("<td width='20%' align ='left'>Client Name</td>");
			out.println("<td width='25%' align ='left'>"+m_cli_name+"</td>"); 
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>Asset Description</td>");
			out.println("<td width='25%' align ='left'>"+m_asset_deta+"</td>");
			out.println("</tr>");
			
			
			if(m_scr_name.equals("New") || m_scr_name.equals("Edit")){
			
					String m_start_date_dd="";
					String m_start_date_mm="";
					String m_start_date_yy="";
					String m_end_date_dd="";
					String m_end_date_mm="";
					String m_end_date_yy="";
					String m_policy_no="";
					String m_start_date="";
					String m_end_date="";
					String m_insur_by="";
					String m_insur_company="";
				  String m_insur_remarks = "";
					double m_premium=0.0;
					double m_sum_insur=0.0;
			
				  rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+ //1
												       " A.PRO_INVOICE_NO,"+ //2
												       " A.POLICY_NO, "+ //3
												       " A.ASSET_DESCRIPTION, "+ //4
												       " TO_CHAR(A.START_DATE,'DD-MM-YYYY'), "+ //5
												       " TO_CHAR(A.END_DATE,'DD-MM-YYYY'), "+ //6
												       " A.SUM_INSSURED, "+ //7
												       " A.PREMIUM, "+ //8
												       " A.INSURED_BY, "+ //9
												       " A.INSUR_COM, "+  //10    
															 " NVL(A.REMARKS,'-') "+//11
												       " FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A "+		
															 " WHERE A.FINANCE_NO = '"+m_finance_no+"' "+
															 " AND A.PRO_INVOICE_NO='"+m_invoice_no+"' ");
			
			boolean more = rs.next();
			if(more){
			m_policy_no     = rs.getString(3);
			m_start_date    = rs.getString(5);
			m_end_date      = rs.getString(6);
			m_premium       = rs.getDouble(8);
			m_sum_insur     = rs.getDouble(7);
			m_insur_by      = rs.getString(9);
			m_insur_company = rs.getString(10);
			m_insur_remarks = rs.getString(11);
			}
			m_start_date_dd = m_start_date.substring(0,2);
			m_start_date_mm = m_start_date.substring(3,5);
			m_start_date_yy = m_start_date.substring(6,10);
			m_end_date_dd   = m_end_date.substring(0,2);
			m_end_date_mm   = m_end_date.substring(3,5);
			m_end_date_yy   = m_end_date.substring(6,10);
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>Policy No</td>"); 
			out.println("<td width='20%' align ='left'><input type=\"text\" class=\"txt_input\" name=\"txt_policy_no\" style=\"width:153px;\" maxlength=20 value=\""+m_policy_no+"\"></td>"); 			
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>Start Date</td>"); 
			out.println("<td width='10%' ><input name=\"start_dd\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" value=\""+m_start_date_dd+"\" onblur=check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy)> ");
			out.println("    <input name=\"start_mm\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" value=\""+m_start_date_mm+"\" onblur=check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy)> ");
			out.println("    <input name=\"start_yy\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" value=\""+m_start_date_yy+"\" onblur=check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy)><a href style='{cursor:hand; }' onclick=load_calendar('2')> Calendar</a> ");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>End Date</td>"); 
			out.println("<td width='10%' ><input name=\"end_dd\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\"  value=\""+m_end_date_dd+"\" onblur=check_date(document.Form2.end_dd,document.Form2.end_mm,document.Form2.end_yy)> ");
			out.println("    <input name=\"end_mm\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\"  value=\""+m_end_date_mm+"\" onblur=check_date(document.Form2.end_dd,document.Form2.end_mm,document.Form2.end_yy)> ");
			out.println("    <input name=\"end_yy\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" value=\""+m_end_date_yy+"\" onblur=check_date(document.Form2.end_dd,document.Form2.end_mm,document.Form2.end_yy)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>Sum Inssured</td>"); 
			out.println("<td width='20%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:153px;text-align:right\" name=\"txt_sum_in\" value=\""+nf.format(m_sum_insur)+"\" maxlength=18 onblur=\"check_amt(this,18)\"></td>"); 			
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>Premium</td>"); 
			out.println("<td width='20%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:153px;text-align:right\" name=\"txt_premium\" value=\""+nf.format(m_premium)+"\" maxlength=18 onblur=\"check_amt(this,18)\"></td>"); 			
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>Insured By</td>"); 
			out.println("<td width='8%'><select name='txt_insurance_done' class='txt_input' style=\"width:153px;\">");
			if(m_insur_by.trim().equals("LICENSEE")){
			out.println("<option value=\"LICENSEE\" SELECTED>Company</option>");
			//out.println("<option value=\"BROKER\" >Broker</option>");
			out.println("<option value=\"CLIENT\" >Lessee</option>");
			/*}else if(m_insur_by.trim().equals("BROKER")){
			out.println("<option value=\"LICENSEE\" >Licensee</option>");
			out.println("<option value=\"BROKER\" SELECTED>Broker</option>");  // Mod by Sandun on 01-01-2009
			out.println("<option value=\"CLIENT\" >Client</option>");*/
			}else if(m_insur_by.trim().equals("CLIENT")){
			out.println("<option value=\"LICENSEE\" >Company</option>");
			//out.println("<option value=\"BROKER\" >Broker</option>");
			out.println("<option value=\"CLIENT\" SELECTED>Lessee</option>");
			}
			out.println("</select>");
			out.println("</td>");
			out.println("</tr>");
					
					
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>Insurance company</td>"); 
			out.println("<td width='20%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:250px;\" name=\"txt_in_company\" maxlength=100 value=\""+m_insur_company+"\"></td>"); 			
			out.println("</tr>");
			
			out.println("<tr>");//Added By Sandun on 08-01-2009
			out.println("<td width='20%' align ='left' valign=top>Remarks</td>"); 
			out.println("<td width='*%' align ='left'><TEXTAREA name='TXT_REMARK' class=\"txt_input\" style='width:250px' >"+m_insur_remarks+"</TEXTAREA></td>"); 			
			out.println("</tr>");
			
			
			}
					
			out.println("</table>");
		
			out.println("</form>");
			out.println("</body>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("</html>");
			
			
			}

		
	}	
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
