
//--
//SCREEN NAME:COLLECTION - ADVERTISEMENT PROCESS
//CREATED BY:NUWAN DE SILVA
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_Collection_Advertistment_Generation extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			
			
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Repossesion Advertisements</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			

			out.println("function get_vector(data_vec) {");
			
			out.println("			if(data_vec.length==0 && document.Form1.TXT_ADVETST_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
			out.println("     help_advetst_no();");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_ADVETST_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
			out.println("     assign_values(data_vec);");
			out.println("			}");
			
		  out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M2' && document.Form1.TXT_INVENTORY_NO.value!=\"\"){");
			out.println("    document.Form1.TXT_INVENTORY_NO.value=data_vec[0];"); 
			
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M2' && document.Form1.TXT_INVENTORY_NO.value!=\"\"){");
			out.println("     help_button_inventory();");
      out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M3' && document.Form1.TXT_VEHICLE_NO.value!=\"\"){");
			out.println("    document.Form1.TXT_VEHICLE_NO.value=data_vec[0];"); 
			out.println("			}");

  		out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M3' && document.Form1.TXT_VEHICLE_NO.value!=\"\"){");
			out.println("     help_button_vehicle_no();");
      out.println("			}");
			
			
			
								
			out.println("}");
			
			
			
			out.println("function getDateValues(dval){");
			out.println("document.Form1.TXT_ADVTEST_DATE_DD.value=dval.substring(0,2)");
			out.println("document.Form1.TXT_ADVTEST_DATE_MM.value=dval.substring(3,5)");
			out.println("document.Form1.TXT_ADVTEST_DATE_YY.value=dval.substring(6,10)");
			out.println("}");
			
			
			out.println("function assign_values(data_vec){");
			
			out.println("    document.Form1.TXT_ADVETST_NO.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_INVENTORY_NO.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_VEHICLE_NO.value=data_vec[2];"); 
			out.println("getDateValues(data_vec[3]);");
			out.println("document.Form1.TXT_AMOUNT.value=data_vec[4];"); 
			out.println("format_number(document.Form1.TXT_AMOUNT,21);");
			out.println("document.Form1.TXT_VAT_AMOUNT.value=data_vec[5];"); 
			out.println("format_number(document.Form1.TXT_VAT_AMOUNT,25);");
			out.println("document.Form1.TXT_TOTAL_AMOUNT.value=data_vec[6];"); 
			out.println("format_number(document.Form1.TXT_TOTAL_AMOUNT,25);");
		//	out.println("    document.Form1.TXT_OFFER.value=data_vec[7];"); 

			
			out.println("}");
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			
			
			out.println("function makeRequest(obj) {");
			out.println("   if( document.Form1.TXT_INVENTORY_NO.value==\"\" && document.Form1.SCREEN_NAME.value==\"NEW\"){"); 

			out.println("   document.Form1.hid_client_code.value=\"\";"); 
			out.println(" }");
			
			out.println("   if( document.Form1.TXT_ADVETST_NO.value==\"\" && document.Form1.SCREEN_NAME.value!=\"NEW\"){"); 

			out.println("   document.Form1.hid_client_code.value=\"\";"); 
			out.println(" }");


			out.println("if(document.Form1.hid_chk_status.value=='M1')");


			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Gen_advertisment_data&data_val=\"+obj.value+\"&ac_status=GENERATED\";");
			
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M2')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Gen_Inv_no&data_val=\"+obj.value+\"&ac_status=ENT\";");
			
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M3')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Gen_Veh_no&data_val=\"+obj.value+\"&data_val2=\"+document.Form1.TXT_INVENTORY_NO.value+\"&ac_status=ENT&ac_status2=Y\";");
			
			
				
		//	out.println("window.open(m_url);");

			out.println("load_interface(m_url,'XML');");
			out.println("}");


			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_ADVETST_NO.value==\"\" && document.Form1.SCREEN_NAME.value!=\"NEW\" ){  "); 
			out.println("DIV_TXT_ADVETST_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 

			out.println("else if(document.Form1.TXT_INVENTORY_NO.value==\"\"){  "); 
			out.println("DIV_INVENTORY_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("if(document.Form1.TXT_VEHICLE_NO.value==\"\"){  "); 
			out.println("DIV_TXT_VEHICLE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ADVTEST_DATE_DD.value==\"\" || document.Form1.TXT_ADVTEST_DATE_MM.value==\"\" || document.Form1.TXT_ADVTEST_DATE_YY.value==\"\" ){  "); 
			out.println("DIV_TXT_ADVTEST_DATE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_AMOUNT.value==\"\"){  "); 
			out.println("DIV_TXT_AMOUNT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_VAT_AMOUNT.value==\"\"){  "); 
			out.println("DIV_TXT_VAT_AMOUNT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
						
			

			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
						
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Collection_Advertistment_Generation';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			
			out.println("} "); 

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Advertistment_Generation';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Advertistment_Generation';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_RE_Collection_Advertistment_Generation\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection - Repossesion Advertisements - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection - Repossesion Advertisements - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			
			out.println("if(m_val==\"NEW\"){"); 
			out.println(" if(confirm(\"Are you sure you want to enter New record?\")){  ");
			out.println("new_window();"); 
			out.println("document.Form1.BUT_TXT_ADVETST_NO.disabled=true;}"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println(" if(confirm(\"Are you sure you want to Delete a record?\")){  ");
			out.println("document.Form1.TXT_ADVETST_NO.disabled=false;"); 
			out.println("document.Form1.BUT_TXT_ADVETST_NO.disabled=false;"); 
			out.println("document.Form1.TXT_INVENTORY_NO.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_INVENTORY_NO.disabled=true;"); 
		//	out.println("document.Form1.TXT_VEHICLE_NO.disabled=true;"); 
		//	out.println("document.Form1.BUT_TXT_VEHICLE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_VAT_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_TOTAL_AMOUNT.disabled=true;"); 
		//	out.println("document.Form1.TXT_OFFER.disabled=true;"); 			
			out.println("document.Form1.TXT_ADVTEST_DATE_DD.disabled=true;"); 	
			out.println("document.Form1.TXT_ADVTEST_DATE_MM.disabled=true;"); 	
			out.println("document.Form1.TXT_ADVTEST_DATE_YY.disabled=true;"); 	
				out.println("document.Form1.TXT_PAID_TO.disabled=true;"); 	

      out.println("}"); 
			out.println("}"); 
			out.println("else{");
			out.println(" if(confirm(\"Are you sure you want to Modify a record?\")){  ");
			out.println("document.Form1.BUT_TXT_ADVETST_NO.disabled=false;}"); 
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

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			
			//----------------------------------------------------------------------------------------------------------------------------------------
			
			
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			/*out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_CO_Help_Servlet?class_in=\"+client_name+\"AF_CO_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			*/
			//
			//out.println("window.open('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=');");
			out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data(IfCount);");
			out.println("	}else");
			
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
		
			
			out.println("		if(IfCount==\"99\"){"); 
			out.println("		help_value_assign_advetst_no();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		help_value_assign_inventory();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		help_value_assign_vehicle_no();"); 
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
			
			
			
			//-----------------------------------------------------------------------------------------------------------------------------------------

			out.println(""); 
			
			
			
			
			
			out.println("function clear_data(IfCount) {");
			
			out.println("		if(IfCount==\"99\"){"); 
			out.println("document.Form1.TXT_ADVETST_NO.value='';");
			out.println("    document.Form1.hid_client_code.value='';"); 

		//	out.println("document.Form1.TXT_TEMP_REC_NO.focus();");
			out.println("	}");
			
			out.println("		if(IfCount==\"1\"){"); 
			out.println("document.Form1.TXT_INVENTORY_NO.value='';"); 
			out.println("    document.Form1.hid_client_code.value='';"); 

			//out.println("document.Form1.TXT_FINANCE_NO.focus();"); 
			out.println("}");
			
			out.println("		if(IfCount==\"2\"){"); 
			out.println("document.Form1.TXT_VEHICLE_NO.value='';"); 
			//out.println("document.Form1.TXT_BRANCH_CODE.focus();"); 
			out.println("	}");
			
						
					
			
			out.println("}");
			
			
			
			
			
			
			out.println("function help_button_inventory() {"); 
			//Modified Nuwan De Silva 17-04-2007
			
			out.println("Crit=document.Form1.TXT_INVENTORY_NO.value+\"@\"+\"VAL_ENT@\"+\"APP@\";");
			
			//out.println("Crit=document.Form1.TXT_INVENTORY_NO.value+\"@\"+\"VAL_ENT@\"+\"ENT@\";");
		//	out.println("    Crit =document.Form1.TXT_INVENTORY_NO.value+\"@\"+\"ENT@\"+\"Y@\";"); 

			//------(2007-02-26)-------------------------------------------------------------------
		  //------MODIIFIED BY : DELANJALI-------------------------------------------------------
			//out.println("    Crit = document.Form1.TXT_INVENTORY_NO.value+\"@ENT@\";"); 
			//out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_INVENTORY_NO_sql','1');"); 
			//-------------------------------------------------------------------------------------
					
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_INVENTORY_NO_sql_new','1');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_inventory() {"); 
			out.println("    document.Form1.TXT_INVENTORY_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_VEHICLE_NO.value=oBj.valout[6];"); 
			out.println("    document.Form1.hid_client_code.value=oBj.valout[4];"); 

			
			//out.println(" if(document.Form1.TXT_INVENTORY_NO.value!=''){;"); 
			//out.println("    document.Form1.TXT_VEHICLE_NO.disabled=false;"); 
			//out.println("    document.Form1.BUT_TXT_VEHICLE_NO.disabled=false;"); 
			//out.println("}"); 
			out.println("}"); 

			out.println("function help_button_vehicle_no() {"); 
		
			//out.println("    Crit = document.Form1.TXT_VEHICLE_NO.value+\"@ENT@\";"); 
						
			//out.println("    Crit =document.Form1.TXT_INVENTORY_NO.value+\"@\"+document.Form1.TXT_VEHICLE_NO.value+\"@ENT@\";"); 
			
			out.println("    Crit =document.Form1.TXT_INVENTORY_NO.value+\"@\"+document.Form1.TXT_VEHICLE_NO.value+\"@\"+\"ENT@\"+\"Y@\";"); 
				
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_VEHICLE_NO_ADVEST','2');"); 
			
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_vehicle_no() {"); 
			out.println("    document.Form1.TXT_VEHICLE_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			
			out.println("function help_advetst_no() {"); 
			//out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    Crit = document.Form1.TXT_ADVETST_NO.value+\"@GENERATED@\";"); 
			//out.println("    } ");
			//out.println("    else{");
			//out.println("    Crit = document.Form1.TXT_TEMP_REC_NO.value+\"@N@\";"); 
			//out.println("    } ");
			//out.println("    HelpBox('1','10','0');"); 
						
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_ADVETST_NO_sql','99');"); 
			out.println("}"); 

			out.println("function help_value_assign_advetst_no() {"); 
			out.println("    document.Form1.TXT_ADVETST_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_INVENTORY_NO.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_VEHICLE_NO.value=oBj.valout[7];");
			
			out.println("getDateValues(oBj.valout[8]);");
			out.println("document.Form1.TXT_AMOUNT.value=oBj.valout[9];"); 
			out.println("format_number(document.Form1.TXT_AMOUNT,21);");
			out.println("document.Form1.TXT_VAT_AMOUNT.value=oBj.valout[10];"); 
			out.println("format_number(document.Form1.TXT_VAT_AMOUNT,25);");
			out.println("document.Form1.TXT_TOTAL_AMOUNT.value=oBj.valout[11];"); 
			out.println("format_number(document.Form1.TXT_TOTAL_AMOUNT,25);");
		//	out.println("    document.Form1.TXT_OFFER.value=oBj.valout[9];"); 
			//out.println("    document.Form1.TXT_VEHICLE_NO.disabled=false;");
			out.println("document.Form1.TXT_PAID_TO.value=oBj.valout[12];"); 
			out.println("    document.Form1.hid_client_code.value=oBj.valout[4];"); 

			out.println("}"); 
			
			
			
			out.println("function check_number(obj,size){");
			out.println("if(obj.value!='')"); 
			out.println("if(isnumberok(obj,size)){"); 
			out.println("format_number(obj,size)"); 
			out.println("}"); 
			out.println("else{");
			out.println("alert('please enter a number');"); 
			out.println("obj.value='';"); 
			out.println("obj.focus();"); 
			out.println("}"); 
			out.println("}"); 
			
						
			
			out.println("function enable_vat(obj,size){");
			out.println("if(obj.value!='')"); 
			out.println("if(isnumberok(obj,size)){"); 
			out.println("document.Form1.TXT_VAT_AMOUNT.disabled=false;"); 
			//out.println("document.Form1.TXT_VAT_AMOUNT.focus();;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
							
			out.println("function load_c_date(val) {");
      out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_ADVTEST_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_ADVTEST_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_ADVTEST_DATE_YY.value=v_yy;");
			out.println("  }");				
			out.println("  if(document.Form1.hid_cal_date.value=='3'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.CHEQUE_DATE_DD.value=v_dd;");
			out.println("     document.Form1.CHEQUE_DATE_MM.value=v_mm;");
			out.println("     document.Form1.CHEQUE_DATE_YY.value=v_yy;");
			out.println("  }");				
			out.println("}");		
			
			
			out.println("function calculate_total(obj1,size1,obj2,size2){");
			out.println("if(obj1.value!='' && obj2.value!='')"); 
			out.println("if(isnumberok(obj1,size1)){"); 
			out.println("if(isnumberok(obj2,size2))"); 
			out.println("document.Form1.TXT_TOTAL_AMOUNT.value=format_noobject((parseFloat(unformat_noobject(obj1.value)))+(parseFloat(unformat_noobject(obj2.value))));");
			//out.println("format_noobject(document.Form1.TXT_TRN_AMOUNT_CURR.value);");
			out.println("}"); 
			out.println("}"); 
			

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\">"); //load_lock()
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_ADVEST_GEN\">"); 
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_client_code' VALUE=\"\">");
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection - Repossesion Advertisements - New</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" name='btn_delete' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' class='table'>"); 

			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_ADVETST_NO'  class=div_input>Advertisement Number *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ADVETST_NO' maxlength='15' size='15' onblur=\"assignState('M1'),makeRequest(document.Form1.TXT_ADVETST_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_ADVETST_NO' value=\"...\" onClick=\"help_advetst_no()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_INVENTORY_NO'  class=div_input>Inventory Number *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_INVENTORY_NO' maxlength='15' size='15' onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_INVENTORY_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_INVENTORY_NO' value=\"...\" onClick=\"help_button_inventory()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_VEHICLE_NO'  class=div_input>Vehicle No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_VEHICLE_NO' maxlength='10' size='10' onblur=\"assignState('M3'),makeRequest(document.Form1.TXT_VEHICLE_NO)\" disabled></td>"); 
		//	out.println("<input class='but_input' type='button' name='BUT_TXT_VEHICLE_NO' value=\"Help\"  disabled onClick=\"help_button_vehicle_no()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_ADVTEST_DATE'  class=div_input>Advertisement Date *[DD-MM-YYYY]</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input5' type='text' name='TXT_ADVTEST_DATE_DD' maxlength='2' size='2' onBlur='checkMonthLength(document.Form1.TXT_ADVTEST_DATE_DD,document.Form1.TXT_ADVTEST_DATE_MM,document.Form1.TXT_ADVTEST_DATE_YY)'>");
			out.println("                 <input class='txt_input5' type='text' name='TXT_ADVTEST_DATE_MM' maxlength='2' size='2' onBlur='checkMonthLength(document.Form1.TXT_ADVTEST_DATE_DD,document.Form1.TXT_ADVTEST_DATE_MM,document.Form1.TXT_ADVTEST_DATE_YY)'>");
			out.println("                 <input class='txt_input5' type='text' name='TXT_ADVTEST_DATE_YY' maxlength='4' size='4' onBlur='checkMonthLength(document.Form1.TXT_ADVTEST_DATE_DD,document.Form1.TXT_ADVTEST_DATE_MM,document.Form1.TXT_ADVTEST_DATE_YY)'><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			    
						
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_AMOUNT'  class=div_input>Amount *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_AMOUNT'   maxlength='25' size='22' onBlur=\"check_number(this,21),enable_vat(document.Form1.TXT_AMOUNT,25),calculate_total(document.Form1.TXT_AMOUNT,25,document.Form1.TXT_VAT_AMOUNT,25)\" STYLE='{text-align:right;}'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 


			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_VAT_AMOUNT'  class=div_input>VAT Amount *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VAT_AMOUNT'  maxlength='25' size='22'  onBlur=\"check_number(this,25),calculate_total(document.Form1.TXT_VAT_AMOUNT,25,document.Form1.TXT_AMOUNT,25)\" disabled STYLE='{text-align:right;}'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_TOTAL_AMOUNT'  class=div_input> Total Amount *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TOTAL_AMOUNT' maxlength='25' size='22' onBlur=\"check_number(this,21)\" disabled  STYLE='{text-align:right;}'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			/*out.println("<tr>"); 
			out.println("<td width='30%' > No Of Offers </td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_OFFER' maxlength='4' size='4' onBlur=\"check_number2(document.Form1.TXT_OFFER,4)\" STYLE='{text-align:right;}'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			*/
			
			
			out.println("<tr>"); 
			out.println("<td width='30%' > Paid To </td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PAID_TO' maxlength='10' STYLE='{text-align:right;}' size='10' ></td>"); 
			out.println("<td width='*%'></td>"); 
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
      out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
