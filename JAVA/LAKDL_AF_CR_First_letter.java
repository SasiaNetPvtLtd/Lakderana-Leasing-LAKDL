//Created By Amila 21-08-2017
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_CR_First_letter extends javax.servlet.http.HttpServlet { 

	
	
	
	public void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		
		
		ServletOutputStream out = null;
		//String m_chksql = null;
		Connection conn = null;
		Statement stmt= null,stmt1= null,stmt2= null;
		java.text.NumberFormat nf= null,nf1= null;
		ResultSet rs= null,rs1= null,rs2= null;
		// end by udara 18-05-2017
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		    nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			String m_schema_name = m_sn_methods.schema_name;
			
			int row = 0;
			
		    String m_chksql=req.getParameter("chksql");
			String m_username = m_sn_methods.username; // added by udara 13-05-2015
			
			
			
			if(m_chksql.equals("main_page")){
				stmt2 = conn.createStatement ();
				rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				
				
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>First Letter</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">");
					out.println("var m_flag=0");

					
					out.println("function get_vector(data_vec) {");
					out.println("	if(data_vec.length>0 && document.Form1.hid_text.value==\"J8\"){");
					out.println("        if(data_vec[0]=='N'){");
					out.println("           alert('New due date cannot be less than day end date'); ");
					out.println("     	    document.getElementById('TXT_VALUE_DATE_DD_NEW_'+document.Form1.hid_cal_id.value).value='';");
					out.println("     	    document.getElementById('TXT_VALUE_DATE_MM_NEW_'+document.Form1.hid_cal_id.value).value='';");
					out.println("     	    document.getElementById('TXT_VALUE_DATE_YY_NEW_'+document.Form1.hid_cal_id.value).value='';");
					out.println("        }");
					out.println("   }");
					out.println("}");
					
					 
			
					out.println("function get_vector_normal(http_response) {");
					out.println(" details.innerHTML = ''; ");
					out.println(" details.innerHTML = http_response; ");
					out.println(" ");
					out.println("}");
					
					out.println("function load_details() {");
					out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
			        out.println("       m_from_date = document.Form1.FROM_DAY.value+'-'+document.Form1.FROM_MONTH.value+'-'+document.Form1.FROM_YEAR.value;");  
				    out.println("       m_to_date   = document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;"); 
					out.println("      m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_First_letter?chksql=details&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&FIN_NO=\"+document.Form1.TXT_FIN_NO.value;");  
					//out.println("    alert(m_url); ");
					out.println("      load_interface(m_url,'NORM');");
					out.println("	}");
				    out.println("}");
					
					out.println("function load_date_validations(due_date,new_due_date,row_num) {");
					out.println("      m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=load_date_validations&due_date=\"+due_date+\"&new_due_date=\"+new_due_date;");
					out.println("      document.Form1.hid_text.value = 'J8';   ");
					out.println("      load_interface(m_url,'XML');");
					out.println("}");
					
					out.println("function load_date_validations_on_blur(row_num) {");
					
					out.println("    if(document.getElementById('TXT_VALUE_DATE_DD_NEW_'+row_num).value!='' && document.getElementById('TXT_VALUE_DATE_MM_NEW_'+row_num).value!='' && document.getElementById('TXT_VALUE_DATE_YY_NEW_'+row_num).value!=''){ ");
					
					out.println("       var due_date     = document.getElementById('TXT_VALUE_DATE_DD_'+row_num).value     +'-'+ document.getElementById('TXT_VALUE_DATE_MM_'+row_num).value     + '-' + document.getElementById('TXT_VALUE_DATE_YY_'+row_num).value ");
					out.println("       var due_date_new = document.getElementById('TXT_VALUE_DATE_DD_NEW_'+row_num).value +'-'+ document.getElementById('TXT_VALUE_DATE_MM_NEW_'+row_num).value + '-' + document.getElementById('TXT_VALUE_DATE_YY_NEW_'+row_num).value ");
					out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=load_date_validations&due_date=\"+due_date+\"&new_due_date=\"+due_date_new;");
					out.println("       document.Form1.hid_text.value = 'J8';   ");
					out.println("       document.Form1.hid_cal_id.value = row_num;   ");
					out.println("       load_interface(m_url,'XML');");
					
					out.println("   }");
					
					out.println("}");
					
					out.println("function clear_window(){	"); 
					out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
					out.println("			window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_First_letter?chksql=main_page';"); 
					out.println("		}"); 
					out.println("}"); 

					out.println("function new_window(){	"); 
					out.println("   window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_First_letter?chksql=main_page';"); 
					out.println("}"); 
					out.println(""); 
					out.println(""); 
					
										
					out.println("function load_roll_value(m_val){"); 
					out.println("   help_box.innerHTML=\" First Letter - \"+m_val;"); 
					out.println("}"); 
					out.println(""); 

					out.println("function load_roll_out_value(){");
					out.println("help_box.innerHTML=\" First Letter - \"+document.Form1.hid_status.value;"); 
					out.println("}"); 
					
					
					out.println("function load_screen_status(m_val){"); 
					out.println("if(m_val==\"NEW\"){"); 
					out.println("new_window();"); 
					out.println("}"); 
					out.println("else if(m_val==\"HELP\"){"); 
					//out.println("load_help_msg();"); 
					out.println("}");
					 
					out.println("else{");
					out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
					//out.println("edit_window();"); 

					out.println("}"); 
					out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
					out.println("if(m_val==\"NEW\"){");
					out.println("document.Form1.hid_status.value=\"New\";"); 
					out.println("document.Form1.hid_delete.value=\"Delete\";"); 
					out.println("document.Form1.hid_save.value=\"reverse\";");
					out.println("}else if(m_val==\"EDIT\"){");  
					out.println("	if(confirm(\"Are you sure you want to modify records?\")){ "); 
					out.println("document.Form1.hid_status.value=\"Edit\";");  
					out.println("document.Form1.hid_delete.value=\"Modify\";");
					out.println("document.Form1.hid_save.value=\"generate\";");
					out.println("chng_butt();");					
					out.println("document.Form1.TXT_APP_NO.value=\"\";");
					out.println("details.innerHTML=\"\";");
					out.println("document.Form1.TXT_VALUE_DATE_DD.disabled=true;");
				  out.println("document.Form1.TXT_VALUE_DATE_MM.disabled=true;");
				  out.println("document.Form1.TXT_VALUE_DATE_YY.disabled=true;");
					out.println("}"); 
					out.println("}else if(m_val==\"DACT\"){");  
					out.println("document.Form1.hid_status.value=\"Deactivate\";");  
					out.println("}else if(m_val==\"RACT\"){");  
					out.println("document.Form1.hid_status.value=\"Reactivate\";");  
					out.println("}else{");  
					out.println("document.Form1.hid_status.value=\"\";");  
					out.println("}"); 
					out.println("}"); 
					
					out.println("function MyDialog(){"); 
					out.println("    this.valout   = new Array(10);"); 
					out.println("}		"); 
					out.println(""); 


					out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
					out.println("    oBj = new MyDialog();"); 
					out.println("    oBj.valout[1]  = \" \";"); 
					out.println("    oBj.valout[2]  = \" \";"); 
					out.println("    oBj.valout[3]  = \" \";"); 
					out.println("	");
					
					
					out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_MISF_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
					
					out.println("	if(oBj.valout[4] ==\" \"){"); 
					out.println(" clear(); ");
					out.println(" }");
					
					out.println("	if(oBj.valout[1] !=\" \"){"); 
					out.println("	if(oBj.valout[1] !=\"Close\"){"); 
					out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
					out.println("	if(oBj.valout[1]!=\"Next\"){"); 
					out.println("if(document.Form1.hid_help_type.value=='1'){"); 
					out.println("		help_value_assign_1(oBj);"); 
					out.println("}");
					
					out.println("	}"); 
					out.println("	else{"); 
					out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
					out.println("		return false;"); 
					out.println("	} "); 
					out.println("	}"); 
					out.println("	else{	"); 
					out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
					out.println("	}	"); 
					out.println("	}		"); 
					out.println("	else{");
					out.println("clear()");
					out.println("	}");
					out.println("}");
					out.println("if(oBj.valout[2]==' '){");
					out.println("clear()");
					out.println("	}	"); 
					out.println("}"); 
			
					out.println("function Prev(Start,End,Hid_No){"); 
					out.println("    HelpBox(Start,End,Hid_No);"); 
					out.println("}"); 
					out.println(""); 

					out.println("function Next (Start,End,Hid_No){"); 
					out.println("    HelpBox(Start,End,Hid_No);"); 
					out.println("}"); 
					out.println(""); 
					
					
					out.println("function clear(){");
					out.println("   if(document.Form1.hid_help_type.value==\"1\"){;"); 
					//out.println("document.Form1.TXT_APP_NO.value=\"\"");
					out.println("document.Form1.TXT_FIN_NO.value=\"\""); 
					out.println(" details.innerHTML = ''; ");
					out.println("}");
					out.println("}");
					
					out.println("function help_button_1() {"); 
					out.println("    document.Form1.hid_help_type.value=\"1\";"); 
					//out.println("    m_sql = \"m_help_TXT_confirmation_rpt_view_sql\";"); 
					out.println("    m_sql = \"m_help_Finance_No_sql\";"); 
					out.println("    m_criteria = document.Form1.TXT_FIN_NO.value+\"@\";");
					out.println("    HelpBox('1','10','0');"); 
					out.println("}");
					
					out.println(" function help_value_assign_1(oBj) {"); 
					out.println(" 	document.Form1.TXT_FIN_NO.value=oBj.valout[2];");
					//out.println("   load_details()");
					out.println("}"); 					

					
						out.println("function ckeck_new_date(){ "); 
				out.println("b_flag=0;");
				out.println("if(m_table.innerHTML==\"\"){");
				out.println("alert('No data to save');");
				out.println("b_flag=1;");
				out.println("}"); 
				out.println("else if(!count_date_selected()){"); 
				out.println("alert('Please enter new date');");
				out.println("b_flag=1;");
				out.println("}"); 
				
				out.println("else{");
				out.println("b_flag=0;");
				out.println("}"); 
				
				out.println("}"); 

				out.println("function count_date_selected(){ ");
				out.println("count=0;");
				out.println("var arr_size=document.Form1.hid_no_rec.value;");
				
				out.println("for(i=0;i<arr_size;i++){");
				out.println("m_new_date_dd=\"TXT_NEW_DATE_DD_\"+i;");
				out.println("m_new_date_mm=\"TXT_NEW_DATE_MM_\"+i;");
				out.println("m_new_date_yy=\"TXT_NEW_DATE_YY_\"+i;");
				
				out.println("if(document.Form1.elements[m_new_date_dd].value!='' && document.Form1.elements[m_new_date_mm].value!='' &&  document.Form1.elements[m_new_date_yy].value!=''){");
				out.println("count=count+1;");
				out.println("}");		
				
				out.println("}");		
				
				out.println("if(count>0)");
				out.println("return true;");
				out.println("else");
				out.println("return false;");
				
				out.println("}"); 

				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
				
				out.println("function load_c_date(val) {");
				
				
				out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
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
				out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
				//	out.println("alert('date'+document.Form1.hid_date.value);");
				out.println("  }");
				//---added by Prabash on 09-05-2012-----**
				out.println("else  if(document.Form1.hid_cal_date.value=='3'){"); 
				out.println("v_date=val.substr(0,val.indexOf('-'));");
				out.println("if(v_date.length<2)");
				out.println("v_date=0+v_date");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("v_month=val.substr(0,val.indexOf('-'));");
				out.println("if(v_month.length<2)");
				out.println("v_month=0+v_month");
				
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				
				
				out.println("     document.Form1.REN_DAY.value=v_date;");		
				//	out.println("alert('date'+document.Form1.hid_date.value);");
				out.println("  }");
				//--------------------------------------**
				
				out.println("}");
				out.println("}");
				
				
					out.println("function check_Date(objDD,objMM,objYY) {");
					out.println("if( objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
					out.println("if(checkMonthLength(objDD,objMM,objYY))");
					out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");						
					out.println("}");
				
					out.println("function validate_data(){"); 
					out.println("if(document.Form1.TXT_APP_NO.value==\"\"){  "); 
					out.println("DIV_TXT_APP_NO.style.color='red';");
					out.println("return false;"); 
					out.println("}else{"); 
					out.println("return true;"); 
					out.println("}"); 
					out.println("}"); 

					out.println("function load_sysdate(){	"); 
					if(rs2.next()){
					out.println("document.Form1.VAL_DAY.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR.value='"+rs2.getString(3)+"';");
					out.println("document.Form1.FROM_DAY.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.FROM_MONTH.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.FROM_YEAR.value='"+rs2.getString(3)+"';");
					out.println("document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");//To-date
					}
					out.println("}"); 

					
					
					out.println("function before_submit(){ "); 
					
					out.println(" 		var row_count = document.Form1.HID_TOTAL_ROW_COUNT.value; ");
					out.println(" 		var counts = 0; ");
					out.println(" 		var counts1 = 0; ");
					
					out.println("       for (i = 0; i < row_count; i++) { ");
					out.println("            if(document.getElementById('CHK_'+i).checked == true){ ");
					out.println("                 counts = counts + 1; ");
					out.println("            } ");
					out.println("       }  ");	
			
			        out.println("       for (i = 0; i < row_count; i++) { ");
					out.println("            if(document.getElementById('CHK_CLIENT_'+i).checked == true){ ");
					out.println("                 counts1 = counts1 + 1; ");
					out.println("            } ");
					out.println("       }  ");	
					
	                out.println("		m_option = document.Form1.hid_status.value;"); 
						
					out.println("       if((counts > 0)||(counts1>0)) {  ");						
	                out.println("			if(confirm('Are you sure you want to save?')){"); 
	                out.println("				document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_First_Letter_Save';");  
	                out.println("				document.Form1.submit();	"); 
	                out.println("			}"); 						
					out.println("	    } "); 
					out.println("	    else{ "); 
					out.println("	       alert('Contracts are not selected for printing'); ");
					out.println("	    } "); 
						
	                out.println("	} "); 
					
					
					out.println("function check_all(){ "); 
					//out.println(" alert('check_all'); "); 
					out.println(" var row_count = document.Form1.HID_TOTAL_ROW_COUNT.value; "); 
					//out.println(" alert(row_count); ");
					
					out.println("   if(document.getElementById('CHK_ALL').checked==true){  ");
					//out.println("         alert('checked'); ");
					out.println("         for (i = 0; i < row_count; i++) { ");
					out.println("              document.getElementById('CHK_'+i).checked = true; ");
					out.println("              document.getElementById('CHK_CLIENT_'+i).checked = false; ");
					out.println("         }  ");
					out.println("   } "); 
					out.println("   else{ "); 
					//out.println("         alert('uncheck'); ");
					out.println("         for (i = 0; i < row_count; i++) { ");
					out.println("              document.getElementById('CHK_'+i).checked = false; ");
					out.println("              document.getElementById('CHK_CLIENT_'+i).checked = false; ");
					out.println("         }  ");
					out.println("   } "); 

					out.println("} "); 
					
					
					out.println(" function check_client(row_no) {");
					out.println(" var row_count = document.Form1.HID_TOTAL_ROW_COUNT.value; "); 
					//out.println("   if(document.getElementById('CHK_CLIENT_'+row_no').checked==true){  ");
					out.println("              document.getElementById('CHK_'+row_no).checked = false; ");
					out.println("} "); 
					
					out.println(" function check_client_all(row_no) {");
					out.println(" var row_count = document.Form1.HID_TOTAL_ROW_COUNT.value; "); 
					//out.println("   if(document.getElementById('CHK_CLIENT_'+row_no').checked==true){  ");
					out.println("              document.getElementById('CHK_CLIENT_'+row_no).checked = false; ");
					out.println("} "); 
					
					
					
					
				
					
					//-------------------------------end---------------------------------------------------------------------
					out.println("</script>"); 
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_sysdate();\">"); 
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"reverse\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_cal_id' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_delete' VALUE=\"Delete\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_text' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='HID_SCREEN' VALUE=\"AF_CR_PRO_INVOICE_REVERSAL_OPTION\">");
					out.println("<input type=hidden name='hid_date' value=\"\">");

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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>First Letter</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  //modified by nuwan  de silva 20-08-07
					out.println("<td width='10%'> &nbsp; </td>"); 
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'    onClick='before_submit()' name=\"MAIN_BUT_3\" value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");'    onClick='load_screen_status(\"HELP\")'        value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()'                      value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'   onclick='close_window()'                      value=\"Close\"></td>");  

					out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
					out.println("</table>");  
					out.println("</td></tr><tr>");  
					out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
					out.println("</tr><tr>");  
					out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


					out.println("<table align='center' width='100%' class='table'>");

				/*	out.println("<tr >"); 
					out.println("<td width='10%'>Approval Level</td>"); 
					out.println("<td width='40%'><select class='txt_input' name='TXT_APP_NO'>");
					out.println("<option value='ALL'>All</option>"); // added by udara 31-08-2015
					out.println("<option value='GEN'>Approval Level 1</option>");
					out.println("<option value='APPROVED'>Approval Level 2</option>");
					out.println("<option value='APPROVED2'>Approval Level 3</option>");
					out.println("<option value='APPROVED3'>Approval Level 4</option>");
					out.println("<option value='CONFIRMED'>Confirmed</option>");
					out.println("</select></td>"); 

				*/	
				
				    out.println("<tr >"); 
					out.println("<td width='10%' ><DIV id='DIV_TXT_APP_NO' class=div_input>Finance No</div></td>"); 
					out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FIN_NO' maxlength='30' size='30' >"); 
					out.println("<input class='but_input' type='button' name='BUT_BUTTON_1' value=\"...\" onClick=\"help_button_1()\" > ");
					//out.println("<input class='but_input' type='button' name='BUT_BUTTON_S' value=\"Search\" onClick=\"load_details()\" > ");
					out.println("</td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>");
				
					out.println("<tr class=tr_input>");
					out.println("<td width='20%'ID=VDATE>From Date</td>");
					out.println("<td width='*%'><input name=\"FROM_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)> ");
					out.println("    <input name=\"FROM_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)> ");
					out.println("    <input name=\"FROM_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)>");
					out.println("</tr>");

					out.println("<tr class=tr_input>");
					out.println("<td width='20%'ID=VDATE>To Date</td>");
					out.println("<td width='*%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
					out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
					out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)>"); // <a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> 
		       		//out.println("<input class='but_input' type='button' name='BUT_BUTTON_S' value=\"Search\" onClick=\"load_details()\" > ");
					out.println("<input class='but_input' type='button' name='BUT_BUTTON_S' value=\"Search\" onClick=\"load_details()\" > ");	
					out.println("</tr>");
					
					// added by udara 31-08-2015
					
			/*		out.println("<tr >"); 
					out.println("<td width='10%' ><DIV id='DIV_TXT_APP_NO' class=div_input>Finance No</div></td>"); 
					out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FIN_NO' maxlength='30' size='30' >"); 
					out.println("<input class='but_input' type='button' name='BUT_BUTTON_1' value=\"...\" onClick=\"help_button_1()\" > ");
					out.println("<input class='but_input' type='button' name='BUT_BUTTON_S' value=\"Search\" onClick=\"load_details()\" > ");
					out.println("</td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>");
           */
					
					// end by udara 31-08-2015

					out.println("</td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 

					out.println("</table>");
					
					out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
					out.println("<td ><div id=details></div></td></tr></table>");
					
					out.println("</table>"); 
	                out.println("<br>"); 
	                out.println("<table align='center' width='100%'>"); 
	                out.println("<tr>"); 
	                out.println("<td width='100%' class='note'><INPUT TYPE='hidden' name='num_row' value='"+row+"'></td>"); 
	                out.println("</tr>"); 
	                out.println("</table>");
		
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
					out.println("</body>"); 
					out.println("</html>"); 
					out.flush();
		
			}
			
			
				else if(m_chksql.equals("details"))
				{
					
					int m_row_count = 0;
					
					String m_from_date= req.getParameter("from_date");
					String m_to_date = req.getParameter("to_date");
					String m_fin_no = req.getParameter("FIN_NO"); 
					
					//out.println("check");
					
					String Sql_data="";
					
					// commented by udara 11-12-2018
					/*
					Sql_data = 	
						     " SELECT "+
							 " A.FINANCE_NO, "+ // 1
						     " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+//2
							 " NVL(TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY'),'-') "+//3
							 " FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+	
							 " WHERE  A.FINANCE_NO LIKE '%"+m_fin_no+"%'  "+ 
							 " AND TRUNC(A.ACTIVATED_DATE) >= TO_DATE ( '"+m_from_date+"','DD-MM-YYYY' )  "+
							 " AND TRUNC(A.ACTIVATED_DATE) <= TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' )  "+
							 " AND APPLICATION_STATUS = 'ACTIVATED' "+
							 " ORDER BY A.ACTIVATED_DATE DESC,A.FINANCE_NO ASC  "+ 
							 " ";	
					*/
					
					// added by udara 11-12-2018
					Sql_data = 
							" SELECT "+
							  " A.FINANCE_NO, "+ // 1
                			  " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+ // 2
							  " NVL(TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY'),'-'), "+ // 3
                			  " B.ADDRESS1, "+ // 4
                			  " B.ADDRESS2, "+ // 5
                			  " INITCAP("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)) "+ // 6
									  " FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_MAS_CLIENT B  "+	
									  " WHERE A.CLIENT_CODE = B.CLIENT_CODE "+
		                			  " AND A.FINANCE_NO LIKE '%"+m_fin_no+"%'  "+  
									  " AND TRUNC(A.ACTIVATED_DATE) >= TO_DATE ( '"+m_from_date+"','DD-MM-YYYY' ) "+ 
									  " AND TRUNC(A.ACTIVATED_DATE) <= TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' ) "+ 
									  " AND A.APPLICATION_STATUS = 'ACTIVATED' "+
									  " AND A.TRANSACTION_TYPE <> 'LOANS'  "+ // added by udara 13-12-2018
									  " ORDER BY A.ACTIVATED_DATE DESC,A.FINANCE_NO ASC "+
									  " ";
					
					out.println	("<!-- "+Sql_data+" -->");
					//out.println	(Sql_data);
				    rs=stmt.executeQuery(Sql_data);
					
					int j=0;
		    	    boolean more = rs.next();
					
						if(!more){
						out.println("<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">");
						out.println("<td  width=\"*%\" align=\"center\"><B>No Records Found</B></td>");
	          			out.println("</TR></table>");
						out.println("<input type='hidden' name='REC_COUNT' value=0>");
				  }
					
					int counts = 0;
					
					if(more){	
					
					out.println("<br>");	
				    out.println("<b><HR>");	
				    out.println("<br>");	
				    out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
	      		    out.println("<tr class=pdn_txtpos2 align='left'>");
					out.println("<td  width='1%' > No. </td>"); 
					out.println("<td  width='10%' > Contract No </td>");
					out.println("<td  width='10%' > Client Name </td>");
					out.println("<td  width='10%' > Activated Date </td>");
					out.println("<td  width='10%' > Address Line 1 </td>"); // added by udara 11-12-2018
					out.println("<td  width='10%' > Address Line 2 </td>"); // added by udara 11-12-2018
					out.println("<td  width='10%' > City </td>"); // added by udara 11-12-2018
					out.println("<td  width='5%' align=center > Client  + Guarantor <input type='checkbox' name='CHK_ALL' value ='ON' checked onclick='check_all();' > </td>");
					out.println("<td  width='5%' align=center > Client  </td>");
					out.println("</tr>");
					
					while(more){
						
					 counts = counts + 1;	
					
					 out.println("<tr>");
					 out.println("<td  width='1%' >"+counts+"</td>"); 
					 out.println("<INPUT TYPE='Hidden' NAME='fin_no_"+row+"' VALUE=\""+rs.getString(1)+"\">");		
					 out.println("<td  width='10%' NAME='fin_no_"+j+"' STYLE='{text-align:left;}' <b>"+rs.getString(1)+"</b> </td>");	
					 out.println("<INPUT TYPE='Hidden' NAME='client_name_"+row+"' VALUE=\""+rs.getString(2)+"\">");	
					 out.println("<td  width='10%' NAME='client_name_"+j+"' STYLE='{text-align:left;}' <b>"+rs.getString(2)+"</b> </td>");
					 out.println("<INPUT TYPE='Hidden' NAME='act_date_"+row+"' VALUE=\""+rs.getString(3)+"\">");		
					 out.println("<td  width='10%' NAME='act_date_"+j+"' STYLE='{text-align:left;}' <b>"+rs.getString(3)+"</b> </td>");	
					 out.println("<td  width='10%' NAME='address1_"+j+"' STYLE='{text-align:left;}' <b>"+rs.getString(4)+"</b> </td>");	 // added by udara 11-12-2018
					 out.println("<td  width='10%' NAME='address2_"+j+"' STYLE='{text-align:left;}' <b>"+rs.getString(5)+"</b> </td>");	 // added by udara 11-12-2018
					 out.println("<td  width='10%' NAME='city_"+j+"' STYLE='{text-align:left;}' <b>"+rs.getString(6)+"</b> </td>");	 // added by udara 11-12-2018
					 out.println("<td  width='5%' align=center><input type='checkbox' id='CHK_"+j+"' name='CHK_"+j+"' value ='ON' checked onclick='check_client_all("+j+")'></td>"); 	
					 out.println("<td  width='5%' align=center><input type='checkbox' id='CHK_CLIENT_"+j+"' name='CHK_CLIENT_"+j+"' value ='ON' onclick='check_client("+j+")'></td>"); 		
					
					
					j=j+1;
					row++;	
					m_row_count++;
					more=rs.next();
					
					}	 
					out.println("<input type=\"hidden\" id=\"HID_TOTAL_ROW_COUNT\" name=\"HID_TOTAL_ROW_COUNT\" value=\"" + m_row_count + "\" />");
				    out.println("</table>");
					
				 }
				}

			
				 
					
					
					
			// added by udara 18-05-2017
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(rs1!=null){try{rs1.close();  }catch(Exception e){}}
			if(rs2!=null){try{rs2.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(stmt1!=null){try{stmt1.close();  }catch(Exception e){}}
			if(stmt2!=null){try{stmt2.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			// end by udara 18-05-2017		
					
			//*****************************************************************************************************************
		}catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
	