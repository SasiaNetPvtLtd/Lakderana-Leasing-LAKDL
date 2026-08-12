/*
// header - edit "Data/yourJavaHeader" to customize
// contents - edit "EventHandlers/Java file/onCreate" to customize
//
*/
//ID         :
//SCREEN NAME: Collections -Insurance Details
//CREATED BY : Dineth Meemanage	
//DATE/TIME  : 2008-08-18
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_Insurance_Details extends javax.servlet.http.HttpServlet { 
	
Connection conn;
	Statement stmt,stmt1;
	public ResultSet rs,rs1 ;
	ServletOutputStream out =  null;
	
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res) { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
		//	String m_html_client_url = m_sn_methods.html_client_url;
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			Connection conn=m_sn_methods.met_user_validate(req);
      String m_username = m_sn_methods.username;
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			
		
							 
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			String m_CLOSE=""; 
			
			String m_chksql = req.getParameter("chksql");
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			else if (m_chksql.trim().equals("main_page")) {
			
			String m_application_no = req.getParameter("application_no");

			m_CLOSE = req.getParameter("CLOSE");
			
			if(req.getParameter("CLOSE")==null){
			m_CLOSE="N";
			}

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE> Insurance Details </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
					
					
			out.println("var lineno=0;");
			out.println("var arr_size=0;");
			out.println("var b_flag=0;"); //Boolean Variable To Hold The Status.
      out.println("var num=0;");
						
			out.println("var application_no=\"\"; ");
			
			
							
			out.println("function get_vector(data_vec) {");
			out.println("			 m_gur_code=\"TXT_GAURANTOR_CODE\"+document.Form1.hid_row_no.value");
			out.println("			if(document.Form1.hid_chk_status.value=='M7'){");
      out.println("      display_data(data_vec);"); 		
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M8'  && document.Form1.elements[m_gur_code].value!=\"\"){");
			//out.println("       document.Form1.elements[m_gur_code].value=\"\"; "); 
			//out.println("				help_button_5(document.Form1.hid_row_no.value);");
			//out.println("       document.Form1.elements[m_gur_code].focus()  "); 
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M8'  && document.Form1.elements[m_gur_code].value!=\"\"){");
			out.println("			 alert('Guarantor code already exist ')	");
			out.println("			 m_gur_name=\"TXT_GAURANTOR_NAME\"+document.Form1.hid_row_no.value");
			out.println("			 m_tel_no=\"TXT_TEL_NO\"+document.Form1.hid_row_no.value;");
			out.println("			 m_nic_reg_no=\"TXT_NIC_REG_NO\"+document.Form1.hid_row_no.value;");	
			out.println("      document.Form1.elements[m_gur_code].value=\"\"; "); 
			out.println("      document.Form1.elements[m_gur_name].value=\"\"; "); 
			out.println("      document.Form1.elements[m_tel_no].value=\"\"; "); 
			out.println("      document.Form1.elements[m_nic_reg_no].value=\"\"; "); 
			//out.println("      document.Form1.elements[m_gur_code].focus()  "); 
			out.println("			}");
			out.println("}");
			

				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Details?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 

				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Details?chksql=main_page';"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}");
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MK_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
					
		
					
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\" Collection Process - Insurance Details - \"+m_val;"); 
				out.println("}"); 
		
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Collection Process - Insurance Details \";"); 
				out.println("}"); 
		
				out.println("function load_screen_status(m_val){"); 
				out.println("if(m_val==\"NEW\"){"); 
				out.println("new_window();"); 
				out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
				out.println("else if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
				out.println("}"); 
				out.println("else if(m_val!=\"EDIT\"){"); 
				out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
				out.println("document.Form1.TXT_APPLICATION_NO.disabled=false;"); 
				out.println("}"); 
				out.println("else{");
				out.println("document.Form1.TXT_APPLICATION_NO.disabled=false;"); 
				out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				out.println("}else if(m_val==\"EDIT\"){");  
				out.println("document.Form1.hid_status.value=\"Edit\";");  
				out.println("}else if(m_val==\"DEL\"){");  
				out.println("document.Form1.hid_status.value=\"Delete\";");  
				out.println("}else if(m_val==\"RACT\"){");  
				out.println("document.Form1.hid_status.value=\"Reactivate\";");  
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
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
			out.println("	if(IfCount=='99'){"); 
			out.println("		help_update_value_assign_99(oBj);"); 
			out.println("	}");
			out.println("	else if(IfCount=='2'){"); 
			out.println("		finance_assign(oBj);"); 
			out.println("	}");
			out.println("	else if(IfCount=='3'){"); 
			out.println("		help_value_assign_client(oBj);"); 
			out.println("	}");
			out.println("	else if(IfCount=='4'){"); 
			out.println("		help_value_assign_4(oBj);"); 
			out.println("	}");
			out.println("	else if(IfCount=='15'){");
		  out.println("		finance_assign_bulk(oBj);"); 
			out.println("	}");
			//Added by Dineth on 28-07-2009
			out.println("	else if(IfCount=='5'){"); 
			out.println("		asset_assign(oBj);"); 
			out.println("	}");
			//End by Dineth on 28-07-2009
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
					
			
		
					out.println("function help_update(Start,End,Hid_No,Sql,IfCount) {"); 
					out.println("    document.Form1.hid_help_type.value=\"99\";");
					out.println("    m_from_date = document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value");
					out.println("    m_to_date = document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value");
					//out.println("    alert(m_to_date);");
					out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\"+m_to_date+\"@\"+m_from_date+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\";"); //\"ENTERED@\"+\"ENT_CON@\"+\"@\"+
					out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
		      out.println("}");
					
					out.println("function help_update_value_assign_99(oBj) {"); 
					out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 					
					out.println("		 if(oBj.valout[3]=='' || oBj.valout[3]=='null' ){");
					out.println("     document.Form1.TXT_FINANCE_NO.value='-';"); 
					out.println("		 }");
					out.println("		 else{");
					out.println("     document.Form1.TXT_FINANCE_NO.value=oBj.valout[3];"); 
					out.println("    }");
					out.println("     document.Form1.TXT_APPLICANT_CODE.value=oBj.valout[4];"); 
					out.println("     document.Form1.TXT_APPLICANT_NAME.value=oBj.valout[5];"); 
					
					out.println("}"); 
					
					out.println("function help_finance(Start,End,Hid_No,Sql,IfCount) {"); 					
					out.println(" document.Form1.hid_help_type.value=\"2\";"); 
					out.println("    m_from_date = document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value");
					out.println("    m_to_date = document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value");
					//out.println(" Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\";"); 
					out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\"+m_to_date+\"@\"+m_from_date+\"@\"+document.Form1.TXT_FINANCE_NO.value+\"@\";"); 
					//out.println(" HelpBox('1','10','0',Crit,'m_help_TXT_FINANCE_NO_4','2');");
					out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
					out.println("}");
					
					//Added by Dineth on 28-07-2009
					out.println("function help_asset(Start,End,Hid_No,Sql,IfCount) {"); 					
					out.println(" document.Form1.hid_help_type.value=\"6\";"); 
					out.println("    m_from_date = document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value");
					out.println("    m_to_date = document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value");
					//out.println(" Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\";"); 
					//out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\"+m_to_date+\"@\"+m_from_date+\"@\"+document.Form1.TXT_FINANCE_NO.value+\"@\";"); 
					out.println("    Crit = document.Form1.TXT_ASSET_ID.value+\"@\"+m_to_date+\"@\"+m_from_date+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\";");
					//out.println(" HelpBox('1','10','0',Crit,'m_help_TXT_FINANCE_NO_4','2');");
					out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
					out.println("}");
					
					//End by Dineth on 28-07-2009
					
					out.println("function help_finance_bulk() {"); 
					out.println("    document.Form1.hid_help_type.value=\"15\";"); 
					out.println("    Crit = document.Form1.TXT_FINANCE_NO_BLK.value+\"@\";"); 
					out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_FINANCE_NO_4','15');");
					out.println("}");
				 
				  out.println("function finance_assign_bulk(oBj) {");				
					out.println("   document.Form1.TXT_FINANCE_NO_BLK.value=oBj.valout[2];"); 
					out.println("}");
					
					//Added by Dineth on 28-07-2009
					out.println("function asset_assign(oBj) {");				
					out.println("   document.Form1.TXT_ASSET_ID.value=oBj.valout[2];");
					out.println("     document.Form1.HID_INV_NO.value=oBj.valout[14]");
					out.println("}");
					
					//End by Dineth on 28-07-2009
					
					out.println("function finance_assign(oBj) {");
					out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
					out.println("   document.Form1.TXT_APPLICATION_NO.value=oBj.valout[3];"); 					
					out.println("   document.Form1.TXT_APPLICANT_CODE.value=oBj.valout[4];"); 
					out.println("   document.Form1.TXT_APPLICANT_NAME.value=oBj.valout[5];");
					out.println("   document .Form1.HID_INV_NO.value=oBj.valout[11]");
					out.println("}");
					
					out.println("function help_button_2(Start,End,Hid_No,Sql,IfCount) {"); 
					out.println("    document.Form1.hid_help_type.value=\"3\";"); 
					out.println("    m_from_date = document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value");
					out.println("    m_to_date = document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value");
					//out.println("		 m_gur_code=\"TXT_GAURANTOR_CODE\"+document.Form1.hid_row_no.value;");
					//out.println("    Crit =document.Form1.TXT_APPLICANT_CODE.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\"+document.Form1.TXT_FINANCE_NO.value+\"@Y@\";"); 
				  out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\"+m_to_date+\"@\"+m_from_date+\"@\"+document.Form1.TXT_FINANCE_NO.value+\"@\";"); 
				  out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
					out.println("}"); 
					out.println(""); 
		
					out.println("function help_value_assign_client(oBj) {"); 
					out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
					out.println("   document.Form1.TXT_APPLICATION_NO.value=oBj.valout[3];"); 					
					out.println("   document.Form1.TXT_APPLICANT_CODE.value=oBj.valout[4];"); 
					out.println("   document.Form1.TXT_APPLICANT_NAME.value=oBj.valout[5];");
					out.println("   document .Form1.HID_INV_NO.value=oBj.valout[11]");
					out.println("}"); 
					
					out.println("function clear_fields(){"); 
					out.println("	 if(document.Form1.hid_help_type.value==\"99\") {" ); 
					out.println("   document.Form1.TXT_APPLICATION_NO.value =''; ");
					out.println("   }		"); 
					out.println("	 else	if(document.Form1.hid_help_type.value==\"2\") {" ); 
					out.println("   document.Form1.TXT_FINANCE_NO.value='';"); 
					out.println("  }		");
					//Added by Dineth on 28-07-2009
					out.println("	 else	if(document.Form1.hid_help_type.value==\"6\") {" ); 
					out.println("   document.Form1.TXT_ASSET_ID.value='';"); 
					out.println("  }		");
					//End by Dineth on 28-07-2009
					out.println("	 else	if(document.Form1.hid_help_type.value==\"3\") {" ); 
					out.println("   document.Form1.TXT_APPLICANT_CODE.value='';"); 
					out.println("   document.Form1.TXT_APPLICANT_NAME.value='';"); 
					out.println("  }		"); 
					out.println("	 else	if(document.Form1.hid_help_type.value==\"5\") {" ); 
					out.println("			 m_gur_code=\"TXT_GAURANTOR_CODE\"+document.Form1.hid_row_no.value");
					out.println("			 m_gur_name=\"TXT_GAURANTOR_NAME\"+document.Form1.hid_row_no.value");
					out.println("			 m_tel_no=\"TXT_TEL_NO\"+document.Form1.hid_row_no.value;");
					out.println("			 m_nic_reg_no=\"TXT_NIC_REG_NO\"+document.Form1.hid_row_no.value;");	
					out.println("      document.Form1.elements[m_gur_code].value=\"\"; "); 
					out.println("      document.Form1.elements[m_gur_name].value=\"\"; "); 
					out.println("      document.Form1.elements[m_tel_no].value=\"\"; "); 
					out.println("      document.Form1.elements[m_nic_reg_no].value=\"\"; "); 
					out.println("  }		"); 
					out.println("}		"); 					

					
		      out.println("function disable_app_no(){")			;
		   	  out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
					//out.println("    document.Form1.hid_row_no.value=rowNo;"); 
				  out.println("}");
						
					out.println("function load_guarantor(){");
					out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_application_guarantors?chksql=main_page&APP_NO='+document.Form1.TXT_APPLICATION_NO.value;"); 
			    out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=350,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
		 			out.println("}");
					
					out.println("function close_screen() {");
					//out.println(" alert(document.Form1.hid_close_sts.value);");
					out.println("   document.Form1.hid_CLOSE.value=\""+m_CLOSE+"\";");
					out.println("		if(document.Form1.hid_close_sts.value=='Y' ){ "); 
					out.println("		     if(confirm(\"Are you sure you want to close the screen?\")){ "); 
					out.println("		      window.close();"); 
					out.println("		     }"); 
					out.println("		 }"); 
					out.println("		else if(document.Form1.hid_CLOSE.value==\"Y\"){ "); 
					out.println("		     if(confirm(\"Are you sure you want to close the screen?\")){ "); 
					out.println("		      window.close();"); 
					out.println("					window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Status_Report?chksql=main_page';");
					out.println("		     }"); 
					out.println("		 }"); 
					out.println("		else { "); 
					out.println("		     close_window();"); 
					out.println("		}"); 
					out.println("}");

          
					/*out.println("function but_view(){");
          out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Printing_View?chksql=main_page&APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'&FIN_NO='+document.Form1.TXT_FINANCE_NO.value+'&CLIENT_NO='+document.Form1.TXT_APPLICANT_CODE.value;");  
			    out.println("window.open(m_url,'displayWindow4','left=20,top=330,width=980,height=350,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
          out.println("}");*/

					out.println("function view_letter1(){");
					out.println("if(chk_init_values()){");
         // out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Payment_Demand_Letter?chksql=Generate&APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'&FIN_NO='+document.Form1.TXT_FINANCE_NO.value+'&CLIENT_NO='+document.Form1.TXT_APPLICANT_CODE.value;");  
			   out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Payment_Demand_Letter?chksql=Generate&APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'&FIN_NO='+document.Form1.TXT_FINANCE_NO.value+'&CLIENT_NO='+document.Form1.TXT_APPLICANT_CODE.value;");  
					out.println("window.open(m_url,'displayWindow4','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
          out.println("}");
					out.println("}");
				
				
				  out.println("function view_letter2(){");
				  out.println("if(chk_init_values()){");
					out.println("invoice_no = document.Form1.HID_INV_NO.value;");
					//out.println("alert(invoice_no);");
         // out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Letters_done_by_others?chksql=Generate&APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'&FIN_NO='+document.Form1.TXT_FINANCE_NO.value+'&CLIENT_NO='+document.Form1.TXT_APPLICANT_CODE.value;");  
					out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Renewal_Data_Remind_Letter?chksql=main_page&invoice_no='+invoice_no+'&finance_no='+document.Form1.TXT_FINANCE_NO.value+'&client_no='+document.Form1.TXT_APPLICANT_CODE.value;");  
			    out.println("window.open(m_url,'displayWindow4','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
          out.println("}");
					out.println("}");
					//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
					
					//-----------------------**Added By Sandun on 03-10-2008**------------------------------------
					
									
					out.println("function check_date(objdd,objmm,objyy) {");						
					out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
					out.println("  checkMonthLength(objdd,objmm,objyy);");
					//out.println("  validate_date(objdd,objmm,objyy,document.Form1.HID_SYS_VAL_DAY,document.Form1.HID_SYS_VAL_MONTH,document.Form1.HID_SYS_VAL_YEAR);");
		      out.println("}");
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
					out.println("document.Form1.hid_from_date.value=date1");
					//	out.println("alert(document.Form1.hid_from_date.value);");
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
					out.println("document.Form1.hid_to_date.value=date2");
					out.println("}");		
					out.println("}");		
					
					out.println("function get_vector_normal(http_response) {");
					out.println("if(num==1){");
			    out.println(" request_details.innerHTML = ''; ");
			    out.println(" request_details.innerHTML = http_response; ");
			    out.println("} else if(num==2){");
					out.println(" request_details_1.innerHTML = ''; ");
			    out.println(" request_details_1.innerHTML = http_response; ");
					out.println("}");
					out.println("}");
																						
					out.println("function makeRequest(){");					
					out.println("m_opt = document.Form1.TXT_PRINT_OPT.value");					
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Details?chksql=load_types&print_type=\"+m_opt+\"\";");
					//out.println("window.open(m_url);");
					out.println("num=1;");
					out.println("load_interface(m_url,'NORM');");
					out.println("if(m_opt == \"INDIVIDUAL\"){");
					out.println("request_details_1.innerHTML=\"\"; ");
					out.println("}");
					out.println("}");
					
					out.println("function on_load_date(){");
					out.println("if(document.Form1.TXT_PRINT_OPT.value==\"BULK\"){");
					out.println("load_sysdate();");
					out.println("}");
					out.println("}");
					
					
					out.println("function load_bulk_data(){");
					out.println("m_from_date=document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
			    out.println("m_to_date=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
					out.println("m_insur_type = document.Form1.TXT_INSURANCE_DONE.value;");
					out.println("m_finance_no = document.Form1.TXT_FINANCE_NO_BLK.value;");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Details?chksql=view&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&finance_no=\"+m_finance_no+\"&insuarane_type=\"+m_insur_type+\"\";");
					//out.println("window.open(m_url);");
					out.println("num=2;");
					out.println("load_interface(m_url,'NORM');");
					out.println("}");
					
					out.println("function view_letter_bulk(appVal,finVal,clintVal,invoNo,polNo){");
          out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Letters_done_by_others?chksql=Generate&POLICY_NO=\"+polNo+\"&INVOICE_NO=\"+invoNo+\"&APP_NO=\"+appVal+\"&FIN_NO=\"+finVal+\"&CLIENT_NO=\"+clintVal+\"\";");  
			    out.println("window.open(m_url,'displayWindow4','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
          out.println("}");
				
					out.println("function befor_end(m_obj) {");
          out.println("   m_obj.focus();");
          out.println("}");
					
					out.println("function load_sysdate(){	"); 
				rs1= stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				if(rs1.next()){
				out.println("document.Form1.VAL_DAY1.value='"+rs1.getString(1)+"';");
				out.println("document.Form1.VAL_MONTH1.value='"+rs1.getString(2)+"';");
				out.println("document.Form1.VAL_YEAR1.value='"+rs1.getString(3)+"';");
				out.println("document.Form1.VAL_DAY2.value='"+rs1.getString(1)+"';");
				out.println("document.Form1.VAL_MONTH2.value='"+rs1.getString(2)+"';");
				out.println("document.Form1.VAL_YEAR2.value='"+rs1.getString(3)+"';");
				}
				out.println("}"); 
				
				out.println("function chk_init_values(){");				
				out.println("	if(document.Form1.TXT_APPLICATION_NO.value==\"\" || document.Form1.TXT_APPLICANT_CODE.value==\"\" ){");
				out.println(" DIV_TXT_APPLICATION_NO.style.color='red';");
				out.println(" DIV_TXT_APPLICANT_CODE.style.color='red';");
				out.println("	alert('Please enter * values to process..!'); ");
				out.println(" return false;");
				out.println("}else{"); 
				out.println(" return true;");
				out.println("}");
				out.println("}");
					
					
				//-------------------------------------***-------------------------------------------------------
				
					out.println("</script>"); 
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_sysdate(),makeRequest()\">"); //load_lock()
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
					out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			    out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
			    out.println("<INPUT TYPE='Hidden' NAME='hid_client_type' VALUE=\"\">"); 
			    out.println("<INPUT TYPE='Hidden' NAME='hid_price_tot' VALUE=\"\">"); 
			    out.println("<INPUT TYPE='Hidden' NAME='hid_profo_tot' VALUE=\"\">"); 
			    out.println("<INPUT TYPE='Hidden' NAME='hid_tot_fin_amt' VALUE=\"\">"); 
			    out.println("<INPUT TYPE='Hidden' NAME='hid_cur_fin_amt' VALUE=\"\">"); 
			    out.println("<INPUT TYPE='Hidden' NAME='hid_close_sts' VALUE=\"N\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_transaction_type' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_CLOSE' VALUE=\"N\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_insurance_done' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_MK_APPLICATION_PROCESS\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_from_date' VALUE=\"\">");
			    out.println("<INPUT TYPE='Hidden' NAME='hid_to_date' VALUE=\"\">");
					out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
					out.println("<INPUT TYPE='Hidden' NAME='HID_INV_NO' VALUE=\"\">");
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Insurance Details</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>");
					
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
					out.println("<td width='10%' align='center'></td>");  
					out.println("<td width='10%' align='center'></td>");  
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
	    		out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_screen(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_out_value();'></td>");
					out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
					out.println("</table>");  
					out.println("</td></tr><tr>");  
					out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
					out.println("</tr><tr>");  
					out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
					out.println("<tr class='tr_input'>");  
					out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
					out.println("</tr>");  
					out.println("</table>");  
		
					out.println("<table align='center' width='100%' border=0 class='' bordercolor=''>"); 
			   	out.println("<tr align='right'>");  
			   	out.println("<td width='100%'>");
					
					//------------------------------**Added By Sandun on 03-10-2008**--------------------------------
								
					out.println("<table align='center' width='100%' class='table' border='0'>"); 	
					out.println("<tr class=tr_input>"); 
					out.println("<td width='14%'>Print Category</td>"); 
					out.println("<td width='25%'><select name='TXT_PRINT_OPT' class='txt_input' style=\"width:150px;\" onchange=\"makeRequest()\">");
					out.println("<option value=\"INDIVIDUAL\" selected>Individual</option>");
					out.println("<option value=\"BULK\" >Bulk</option>");
					out.println("</select>");
					out.println("<td width='61%'></td>");
					out.println("</td>");
					out.println("</tr>");
					out.println("</table>");	
									
					out.println("<table align='center' width='100%' class='table' border='0' >"); 				
					out.println("<tr class=tr_input>");
					out.println("<td width='7%' ID=VDATE>From</td>");
					out.println("<td width='8%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
					out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
					out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
					out.println("</td>");
					out.println("<td width='2%'></td>"); 
					out.println("<td width='2%' ID=VDATE>To</td>");
					out.println("<td width='10%' ><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
					out.println("    <input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
					out.println("    <input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
					out.println("</td>");
					out.println("<td width='10%'></td>"); 
					out.println("<td width='10%'></td>"); 
					out.println("</tr>");	
					out.println("</table>");
					
					
					out.println("<table align='center' width='100%' class='table'>"); 
					out.println("<tr>");  
					out.println("<td width=\"100%\"><DIV ID='request_details'></DIV></td>");
					out.println("</tr>"); 
					out.println("</table>");
					out.println("</td>"); 
			   	out.println("</tr>"); 
			   	out.println("</table>");
						
					out.println("<table align='center' width='100%' class='table'>"); 
					out.println("<tr>");  
					out.println("<td width=\"100%\"><DIV ID='request_details_1'></DIV></td>");
					out.println("</tr>"); 
					out.println("</table>");
					out.println("</td>"); 
			   	out.println("</tr>"); 
			   	out.println("</table>");
//---------------------------------------------***--------------------------------------------------	
				 	out.println("<br>");  
				 	out.println("<table align='center' width='100%' border=0 class='' bordercolor=''>"); 
			   	out.println("<tr align='right'>");  
			   	out.println("<td width='100%'>");	
				 	out.println("</td>"); 
			   	out.println("<tr>");  
				 	out.println("</table>");	
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
					out.println("</body>"); 
					out.println("</html>"); 
      }
			
			else if(m_chksql.trim().equals("load_types")){//ADD BY SANDUN ON 03-10-2008
			String m_print_type = req.getParameter("print_type");
			
			if(m_print_type.trim().equals("INDIVIDUAL")){
					
					out.println("<table align='center' width='100%' border=0 class='table' >"); 
					out.println("<tr >"); 
					out.println("<td width='14%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application No *</DIV></td>"); 
					out.println("<td width='50%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='30' onblur=\"help_update('1','10','5','m_help_TXT_APPLICATION_NO_IN_DETA','99')\">"); 
					out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"...\" onClick=\"help_update('1','10','5','m_help_TXT_APPLICATION_NO_IN_DETA','99')\" ></td>"); //M_APPLICATION_PROCESS_APPLICATION_HELP m_help_TXT_APPLICATION_NO
					out.println("</tr>"); 
					out.println("<tr >"); 
					out.println("<td  width='14%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance Number</DIV></td>"); 
					out.println("<td width='50%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='20' size='30'  OnBlur=\"help_finance('1','10','0','m_help_TXT_FINANCE_NO_IN_DETA','2')\">"); //m_help_TXT_FINANCE_NO_4
					out.println("<input class='but_input' type='button' name='BUT_TXT_APPLICATION_NO' value=\"...\" onClick=\"help_finance('1','10','0','m_help_TXT_FINANCE_NO_IN_DETA','2')\" > </td>"); 
					out.println("</tr>"); 
					out.println("<tr >"); 
					out.println("<td width='14%' ><DIV id='DIV_TXT_APPLICANT_CODE'  class=div_input>Client Code *</DIV></td>"); 
					out.println("<td width='50%' ><input class='txt_input' type='text' name='TXT_APPLICANT_CODE' maxlength='10' size='30' onblur=\"help_button_2('1','10','0','m_help_TXT_FINANCE_NO_IN_DETA','3')\">"); //m_help_TXT_CLIENT_NO_4
					out.println("<input class='but_input' type='button' name='BUT_TXT_APPLICANT_CODE' value=\"...\" onClick=\"help_button_2('1','10','0','m_help_TXT_FINANCE_NO_IN_DETA','3')\"></td>");
					//out.println("&nbsp&nbsp<input class='but_input' type='button' name='BUT_VIEW' value=\" View \" onClick=\"but_view()\"></td>"); 
					//out.println("<td width='10%'>&nbsp;</td>"); 
					out.println("<td >Client Name </td>");					
					out.println("<td ><input class='txt_input' type='text' name='TXT_APPLICANT_NAME' style=\"width:250px;\" maxlength='250' size='22' disabled></td>"); 
					out.println("</tr>"); 
					//Added by Dineth on 2008-08-18
					//Added by Dineth on 28-07-2009
					
					out.println("<tr >"); 
					out.println("<td  width='14%' ><DIV id='DIV_TXT_ASSET_ID'  class=div_input>Vehicle No</DIV></td>"); 
					out.println("<td width='50%' ><input class='txt_input' type='text' name='TXT_ASSET_ID' maxlength='20' size='30'  OnBlur=\"help_asset('1','10','0','m_help_TXT_ASSET_ID_sql3','5')\">"); //m_help_TXT_FINANCE_NO_4
					out.println("<input class='but_input' type='button' name='BUT_TXT_ASSET_ID' value=\"...\" onClick=\"help_asset('1','10','0','m_help_TXT_ASSET_ID_sql3','5')\" > </td>"); 
					out.println("</tr>"); 
					//End by Dineth on 28-07-2009
					
					out.println("<tr>");
					out.println("<td colspan=4><br><br></td>");
					out.println("</tr>");
					out.println("<tr >"); 
					out.println("<td width='14%'>&nbsp;</td>"); 
					out.println("<td width='*%' colspan=3>"); 
					out.println("<input class='but_input' type='button' name='BUT_PAYMENT_DEMAND_LETTER' style='{width:120px}' value=\"Payment Demand Letter\" onClick=\"view_letter1()\"></td>");
					//out.println("&nbsp&nbsp<input class='but_input' type='button' name='BUT_VIEW' value=\" View \" onClick=\"but_view()\"></td>"); 
					 //end by Dineth on 2008-08-18
						
					out.println("</tr>"); 
					out.println("<tr >"); 
					out.println("<td width='14%'>&nbsp;</td>"); 
					out.println("<td width='*%' colspan=3>"); 
					out.println("<input class='but_input' type='button' name='BUT_REMAINDER_LETTER_DONE_BY_OTHER' style='{width:120px}' value=\"Remainder Letter-s\" onClick=\"view_letter2()\"></td>"); 
					out.println("</tr>");				
				 	out.println("</table>"); 
			
			
			}else 
			if(m_print_type.trim().equals("BULK")){
			/*
					String m_date_dd="";                 //Commented By Sandun on 06-10-2008
					String m_date_mm="";
					String m_date_yy="";
					
					rs1= stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
					if(rs1.next()){
					 m_date_dd = rs1.getString(1);
				   m_date_mm = rs1.getString(2);
					 m_date_yy = rs1.getString(3);
					}
					
					out.println("<table align='center' width='100%' class='table' border='0' >"); 				
					out.println("<tr class=tr_input>");
					out.println("<td width='7%' ID=VDATE>From</td>");
					out.println("<td width='8%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" value=\""+m_date_dd+"\" onblur=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
					out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" value=\""+m_date_mm+"\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
					out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" value=\""+m_date_yy+"\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
					out.println("</td>");
					out.println("<td width='2%'></td>"); 
					out.println("<td width='2%' ID=VDATE>To</td>");
					out.println("<td width='10%' ><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" value=\""+m_date_dd+"\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
					out.println("    <input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" value=\""+m_date_mm+"\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
					out.println("    <input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" value=\""+m_date_yy+"\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
					out.println("</td>");
					out.println("<td width='10%'></td>"); 
					out.println("<td width='10%'></td>"); 
					out.println("</tr>");	
					out.println("</table>");*/
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' border='0'>");
					
					out.println("<tr >"); //Added By Sandun on 17-10-2008
					out.println("<td  width='13%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance Number</DIV></td>"); 
					out.println("<td width='24%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO_BLK' maxlength='20' style='width:150'  OnBlur=\"help_finance_bulk()\">"); 
					out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE_NO_BLK' value=\"Help\" onClick=\"help_finance_bulk()\" > </td>"); 
					out.println("<td width='7%'>&nbsp;</td>");
					out.println("</tr>"); 
					
					out.println("<tr class=tr_input>"); 
					out.println("<td width='13%'>Insurance Done By</td>"); 
					out.println("<td width='24%'><select name='TXT_INSURANCE_DONE' class='txt_input' style=\"width:150px;\" onchange=\"\">");
				/*	out.println("<option value=\"LICENSEE\" >Licensee</option>");
					out.println("<option value=\"BROKER\" >Broker</option>");
					out.println("<option value=\"CLIENT\" >Client</option>");  //Mod by sandun on 22-12-2008
					*/					
					out.println("<option value=\"LICENSEE\" >Company</option>");					
					out.println("<option value=\"CLIENT\" >Lessee</option>");
					out.println("</select>");
					out.println("<td width='7%'></td>");
					out.println("</td>");
					out.println("<td width='11%' align='right'><input type=\"button\" class='mainbut'onClick='load_bulk_data()' value=\"Go\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>");
					out.println("</table>");
			
		    	}
			
		}	
		else if(m_chksql.equals("view")){		//ADD BY SANDUN ON 03-10-2008
				
		String m_insuarane_type= req.getParameter("insuarane_type"); 
		String m_from_date = req.getParameter("from_date");
		String m_to_date = req.getParameter("to_date");
		String m_finance_no = req.getParameter("finance_no");
		int j=1;
										
		if(m_finance_no.equals("")){			
			rs=stmt.executeQuery(" SELECT DISTINCT A.FINANCE_NO, "+//1
													 " A.INSURED_BY, "+//2
										       " A.ASSET_DESCRIPTION, "+//3
										       " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE), "+//4
										       " B.ACTIVATED_DATE, "+//5
										       " DECODE(B.APPLICATION_STATUS,'ACTIVATED','Activated'), "+//6
										       " A.PRO_INVOICE_NO, "+//7
													 " B.APPLICATION_NO, "+//8
													 " A.POLICY_NO, "+//9
													 " B.CLIENT_CODE "+	//10
													 " FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
													 " WHERE  A.FINANCE_NO=B.FINANCE_NO "+
													 " AND    B.APPLICATION_STATUS='ACTIVATED' "+
													 " AND    A.PRINT_STATUS IS NULL "+
													 " AND    A.END_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
									         " AND    A.END_DATE > TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
                           " AND    A.INSURED_BY ='"+m_insuarane_type+"' ");
				}
				else{				
				rs=stmt.executeQuery(" SELECT DISTINCT A.FINANCE_NO, "+//1
													 " A.INSURED_BY, "+//2
										       " A.ASSET_DESCRIPTION, "+//3
										       " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE), "+//4
										       " B.ACTIVATED_DATE, "+//5
										       " DECODE(B.APPLICATION_STATUS,'ACTIVATED','Activated'), "+//6
										       " A.PRO_INVOICE_NO, "+//7
													 " B.APPLICATION_NO, "+//8
													 " A.POLICY_NO, "+//9
													 " B.CLIENT_CODE "+	//10
													 " FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
													 " WHERE  A.FINANCE_NO=B.FINANCE_NO "+
													 " AND    B.APPLICATION_STATUS='ACTIVATED' "+	
													 " AND    A.PRINT_STATUS IS NULL "+
													 " AND    A.FINANCE_NO = '"+m_finance_no+"' "+
									         " AND    A.END_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
									         " AND    A.END_DATE > TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
                           " AND    A.INSURED_BY ='"+m_insuarane_type+"' ");
				
				}
						
			boolean more = rs.next();
			
			if(!more){
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
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
			out.println("<td width='20%' align ='left'>Finance No.</td>"); 
			out.println("<td width='25%' align ='left'>Client Name</td>"); 
			out.println("<td width='10%' align ='left'>Status</td>"); 
			out.println("<td width='20%' align ='center'>Asset Description</td>"); 
			out.println("<td width='10%' align ='center'>Letter</td>"); 
			out.println("</tr >"); 
			
			while(more){
			
			if(j%2==1){
			out.println("<tr class=tr_input>"); 
			}
			else{
			out.println("<tr class=tr_input1>"); 
			}
			out.println("<input type=hidden name=\"hid_client_no\" value=\""+rs.getString(10)+"\"></td>");
			out.println("<input type=hidden name=\"hid_app_no\" value=\""+rs.getString(8)+"\"></td>");
			out.println("<td width='20%' align ='left'>"+rs.getString(1)+"</td>"); 
			out.println("<td width='25%' align ='left'>"+rs.getString(4)+"</td>"); 
			out.println("<td width='10%' align ='left'>"+rs.getString(6)+"</td>");		
			out.println("<td width='20%' align ='center'>"); 
			out.println("<select name=TXT_ASSET_DETA_"+j+" class='txt_input' style=\"width:200px;\">");
			
			rs1=stmt1.executeQuery(" SELECT DISTINCT A.ASSET_DESCRIPTION , "+
														 " A.ASSET_DESCRIPTION || '@' || A.PRO_INVOICE_NO ||'@' ,"+
														 " A.FINANCE_NO ,A.POLICY_NO "+
														 " FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A "+
														 " WHERE A.FINANCE_NO     = '"+rs.getString(1)+"' "+
														 " AND   A.INSURED_BY     = '"+m_insuarane_type+"' ");
						
			boolean more1 = rs1.next();
			while(more1){			
			out.println("<option value=\""+rs1.getString(2)+"\" >"+rs1.getString(1)+"</option>");
			more1 = rs1.next();
			}
			out.println("</select></td>");
			out.println("<td width='10%' align ='center'><input type='button' name='butt_detail' class='mainbut' value='Letter' onClick=\"view_letter_bulk('"+rs.getString(8)+"','"+rs.getString(1)+"','"+rs.getString(10)+"','"+rs.getString(7)+"','"+rs.getString(9)+"')\"></td>");  //onclick=\"load_scr('"+rs.getString(1)+"','"+rs.getString(3)+"',"+j+")\"
			out.println("</tr>"); 			
			more = rs.next();
			j=j+1;
			}
			out.println("<br>");
			out.println("<tr class=tr_input>");//
			out.println("<td align=right colspan=11><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
      out.println("</tr>");
			out.println("</table>");
		}
	}
}	
catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
      //return null;
		}finally{
		  //if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			//if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	    //if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e2){}}
			//try{conn.setAutoCommit(true);
		}
	}


}

