
//--
//SCREEN NAME:COLLECTION - OFFER ISSUE
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_Collection_Advertistment_Offers_Issue extends javax.servlet.http.HttpServlet { 

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
			out.println("<TITLE>Collection - Repossesion-Acceptance Of Offers</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("var lineno=0;");
			out.println("var arr_size=0;");
			out.println("var b_flag_max=0;");
			
			
			//Arrays To Hold The Data
			out.println("var array_name=new Array();");
			out.println("var array_address=new Array();");
			out.println("var array_tel_no=new Array();");
			out.println("var array_amount=new Array();");
			out.println("var array_offer_no=new Array();");
			
			out.println("var new_data_vec=new Array();");  //added by nuwan de silva 17-07-07-

			

			out.println("function get_vector(data_vec) {");
			
			out.println("			if(data_vec.length==0 && document.Form1.TXT_ADVETST_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
			out.println("     help_advetst_no();");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_ADVETST_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
			out.println("     assign_values(data_vec);");
			out.println("			}");
			
		 	out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_ADVETST_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M3' ){");
			out.println("				new_data_vec=data_vec;");
			out.println("     display_data(data_vec);");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_ADVETST_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M4' ){");
			out.println("     Assign_outstanding_value(data_vec);");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_ADVETST_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M5' ){");
			out.println("				new_data_vec=data_vec;");
			out.println("     display_data(data_vec);");
			out.println("			}");
			
				out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_INVENTORY_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M_INVENTORY' ){");
			out.println("     Assign_Inventory_data(data_vec);");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.TXT_INVENTORY_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M_INVENTORY' ){");
			out.println("     help_button_inventory();");
			out.println("			}");
			
								
			out.println("}");
			
			
			out.println("function Assign_outstanding_value(data_vec){");
			// commented by udara 26-11-2013
			/*
			out.println("    document.Form1.TXT_OUTSTANDING_VALUE_INVOICES.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_OUTSTANDING_VALUE.value=data_vec[1];"); 
			out.println("var sum=0;");
			out.println("sum=parseFloat(unformat_noobject(document.Form1.TXT_OUTSTANDING_VALUE.value))+parseFloat(unformat_noobject(document.Form1.TXT_OUTSTANDING_VALUE_INVOICES.value));");
						
			out.println("document.Form1.TXT_TOTAL_OUTSTANDING_VALUE.value=format_noobject(sum)");
			*/
			
			// added by udara 26-11-2013
			out.println("    document.Form1.TXT_OUTSTANDING_VALUE_INVOICES.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_OUTSTANDING_VALUE.value=data_vec[1];");
			out.println("    document.Form1.TXT_TOTAL_OUTSTANDING_VALUE.value=data_vec[2];");
			// end by udara 26-11-2013
						
			out.println("get_offers();");
			
			out.println("}");
			
			/*--------------------------------------------------------------------------------
			Purpose : Assign The Values Come From The data Vector
			--------------------------------------------------------------------------------*/					
			out.println("function assign_values(data_vec){");
			
			out.println("if(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			
			out.println("    document.Form1.TXT_ADVETST_NO.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_INVENTORY_NO.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_VEHICLE_NO.value=data_vec[2];"); 
			out.println("get_outstanding_amount();");
			
			out.println("}");
			
			out.println("else if(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value!=\"NEW\"){");
			
			out.println("    document.Form1.TXT_ADVETST_NO.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_INVENTORY_NO.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_VEHICLE_NO.value=data_vec[2];"); 
			out.println("    document.Form1.TXT_OUTSTANDING_VALUE_INVOICES.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_OUTSTANDING_VALUE.value=data_vec[4];"); 
			out.println("    document.Form1.TXT_TOTAL_OUTSTANDING_VALUE.value=data_vec[5];"); 
			out.println("    document.Form1.RELEASE_MODE.value=data_vec[6];"); 		
			out.println("get_selected_offer();");
			
			
			out.println("}");

			out.println("}");
			/*-------------------------------------------------------------------------------------
			End of function assign_values
			------------------------------------------------------------------------------------------*/
			
			
			
			
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			
			
			out.println("function makeRequest(obj) {");
			
			out.println("if(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value==\"NEW\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Gen_advertisment_data&data_val=\"+obj.value+\"&ac_status=GENERATED\";");
			
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value!=\"NEW\" )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Offer_Issue_validation&data_val=\"+obj.value+\"&data_val2=\"+document.Form1.TXT_INVENTORY_NO.value+\"&ac_status=Y\";");
			
			out.println("else if(document.Form1.hid_chk_status.value=='M_INVENTORY' && document.Form1.SCREEN_NAME.value==\"NEW\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Offer_Issue_Inventory_val&data_val=\"+obj.value+\"&ac_status=ENT\";");	
				
			//out.println("window.open(m_url);");

			out.println("load_interface(m_url,'XML');");
			out.println("}");


			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_ADVETST_NO.value==\"\" ){  "); 
			out.println("DIV_TXT_ADVETST_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 

								
			

			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			
			
			
	/*				out.println("function chk_data(){");
		///	 out.println("alert('chk');");
		  	out.println("b_flag=0;");
				out.println("b_flag_max=0;");
				
			
			
			  out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
				
							
				out.println("for(var i=0;i<lineno;i++){");
				
				out.println("m_name=\"TXT_NAME\"+i");
				out.println("m_address=\"TXT_ADDRESS\"+i");
				out.println("m_tel_no=\"TXT_TEL_NO\"+i");
				out.println("m_amount=\"TXT_AMOUNT\"+i");
				
						 			
								
				out.println("if(document.Form1.elements[m_name].value==\"\") {");
				out.println("alert('Please enter a name');");
				out.println("b_flag=1;");
				out.println("break;");
				out.println("}");						
				
				out.println("else if(document.Form1.elements[m_address].value==\"\") {");
				out.println("alert('Please enter a address');");
				out.println("b_flag=1;");
				out.println("break;");
				out.println("}");	
				
				out.println("else if(document.Form1.elements[m_tel_no].value==\"\") {");
				out.println("alert('Please enter a telephone number');");
				out.println("b_flag=1;");
				out.println("break;");
				out.println("}");	
				
				out.println("else if(document.Form1.elements[m_amount].value==\"\") {");
				out.println("alert('Please enter a amount');");
				out.println("b_flag=1;");
				out.println("break;");
				out.println("}");				
				
				
				out.println("}");
				
			  out.println("}");
			  out.println("}");	
				
				*/
				
					out.println("function Assign_Inventory_data(data_vec){");
			
			out.println("    document.Form1.TXT_INVENTORY_NO.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_ADVETST_NO.value='-';"); 
			out.println("    document.Form1.TXT_ADVETST_NO.disabled=true;"); 
			out.println("    document.Form1.BUT_TXT_ADVETST_NO.disabled=true;"); 
			
			out.println("    document.Form1.TXT_VEHICLE_NO.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_VEHICLE_NO.disabled=true;"); 
			
			out.println("get_outstanding_amount()");
			
			
			out.println("}");
			
			
			

			out.println("function before_submit(){ "); 
						
			out.println("		if(validate_data()){"); 
			out.println("ckeck_issues();");
			out.println("if(b_flag==0)");
			out.println("if(b_flag_max==0)");
			//out.println("		if(confirm(\"Are you sure selected offer is not the maximum\")){ "); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Collection_Advertistment_Offers_Issue';");  
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

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Advertistment_Offers_Issue';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Advertistment_Offers_Issue';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_RE_Collection_Advertistment_Offers_Issue\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection - Repossession - Acceptance Of Offers - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection - Repossession - Acceptance Of Offers - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("document.Form1.BUT_TXT_ADVETST_NO.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println(" if(confirm(\"Are you sure you want to Delete a record\")){  ");
			out.println("document.Form1.TXT_ADVETST_NO.disabled=false;"); 
			//out.println("document.Form1.BUT_TXT_ADVETST_NO.disabled=false;"); 
			out.println("document.Form1.TXT_INVENTORY_NO.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_INVENTORY_NO.disabled=true;"); 
			out.println("document.Form1.TXT_VEHICLE_NO.disabled=true;"); 
			//out.println("document.Form1.BUT_TXT_VEHICLE_NO.disabled=true;"); 
			out.println("}"); 
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_TXT_ADVETST_NO.disabled=false;}"); 
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
			out.println("	clear_data(IfCount);");//Added To The Clear 
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
			out.println("	}");
			
			out.println("		if(IfCount==\"1\"){"); 
			out.println("document.Form1.TXT_INVENTORY_NO.value='';");
			out.println("	}");
								
			
			
			out.println("}");
			
			
			
			
			
			
			out.println("function help_button_inventory() {"); 
			
		//	out.println("    Crit = document.Form1.TXT_INVENTORY_NO.value+\"@ENT@\";"); 
			
			  //m_help_Inventory_no_Offer_Issue m_help_TXT_INVENTORY_NO_sql
				//out.println("Crit=document.Form1.TXT_INVENTORY_NO.value+\"@\"+\"ENT@\"+\"VAL_ENT@\";");
				
				//Modified Nuwan De Silva 17-07-2007
				
				out.println("Crit=document.Form1.TXT_INVENTORY_NO.value+\"@\"+\"APP@\"+\"VAL_ENT@\";");
				
				out.println("    HelpBox('1','10','0',Crit,'m_help_Inventory_no_Offer_Issue_new','1');"); 
	
		
			out.println("}"); 
			
			out.println(""); 

			out.println("function help_value_assign_inventory() {"); 
			out.println("    document.Form1.TXT_INVENTORY_NO.value=oBj.valout[2];"); 
			out.println(" if(document.Form1.TXT_INVENTORY_NO.value!=''){"); 
			out.println("    document.Form1.TXT_ADVETST_NO.value='-';"); 
			out.println("    document.Form1.TXT_ADVETST_NO.disabled=true;"); 
			out.println("    document.Form1.BUT_TXT_ADVETST_NO.disabled=true;"); 
			out.println("    document.Form1.TXT_VEHICLE_NO.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_VEHICLE_NO.disabled=true;"); 
			
			out.println("get_outstanding_amount()");
			
			out.println("}"); 
			
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
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\"){ ");
			out.println("    Crit = document.Form1.TXT_ADVETST_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_ADERTTISMENT_NO_OFFERS_VAL','99');"); 
			out.println("    } ");
			out.println("    else{");
			//------(2007-02-26)-------------------------------------------------------------------------
		  //------MODIIFIED BY : DELANJALI-------------------------------------------------------------
			//out.println("    Crit = document.Form1.TXT_ADVETST_NO.value+\"@GENERATED@\";"); 
			//out.println("    HelpBox('1','10','5',Crit,'m_help_advertistemnt_help_offer_issue','99');"); 
		  out.println("Crit=document.Form1.TXT_ADVETST_NO.value+\"@\"+\"GENERATED@\"+\"VAL_ENT@\";");
			//--------------------------------------------------------------------------------------------
			out.println("    HelpBox('1','10','0',Crit,'m_help_advertistemnt_help_offer_issue_new','99');"); 
			out.println("    } ");
			//out.println("    HelpBox('1','10','0');");  m_help_TXT_ADVETST_NO_sql
			//m_help_advertistemnt_help_offer_issue
			out.println("}"); 

			out.println("function help_value_assign_advetst_no() {"); 
			
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
			
			out.println("    document.Form1.TXT_ADVETST_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_INVENTORY_NO.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_VEHICLE_NO.value=oBj.valout[7];");
					
			out.println("get_outstanding_amount();");
			
			out.println("}"); 
			
			out.println(" else   if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\"){ ");
			
			out.println("    document.Form1.TXT_ADVETST_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_INVENTORY_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_VEHICLE_NO.value=oBj.valout[4];");
			
			out.println("    document.Form1.TXT_OUTSTANDING_VALUE_INVOICES.value=oBj.valout[5];"); 
			out.println("format_number(document.Form1.TXT_OUTSTANDING_VALUE_INVOICES,25);");
			
			out.println("    document.Form1.TXT_OUTSTANDING_VALUE.value=oBj.valout[6];"); 
			out.println("format_number(document.Form1.TXT_OUTSTANDING_VALUE,25);");
			
			out.println("    document.Form1.TXT_TOTAL_OUTSTANDING_VALUE.value=oBj.valout[7];"); 
			out.println("format_number(document.Form1.TXT_TOTAL_OUTSTANDING_VALUE,25);");
			
			out.println("    document.Form1.RELEASE_MODE.value=oBj.valout[8];"); 		
			out.println("get_selected_offer();");

			out.println("}"); 
			
			out.println("}"); 
			
			
			out.println("function get_offers(){");
			out.println("assignState('M3')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Offers&data_val=\"+document.Form1.TXT_INVENTORY_NO.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);");
			out.println("}"); 
			
			out.println("function get_selected_offer(){");
			out.println("assignState('M5')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Offer_Issues_Offer_Numbers&data_val=\"+document.Form1.TXT_ADVETST_NO.value+\"&data_val2=\"+document.Form1.TXT_INVENTORY_NO.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);");
			out.println("}"); 
			
			
			out.println("function get_outstanding_amount(){");
			out.println("assignState('M4')");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Advertistment_Offers_outstanding&data_val=\"+document.Form1.TXT_INVENTORY_NO.value+\"&data_val2=\"+document.Form1.TXT_VEHICLE_NO.value;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=chk_Offers_outstanding_values&data_val=\"+document.Form1.TXT_INVENTORY_NO.value;");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);");
			out.println("}"); 


			
			out.println("function check_number(rowNo){");
			out.println("m_amount=\"TXT_AMOUNT\"+rowNo; ");
			out.println("if(document.Form1.elements[m_amount].value!='')"); 
			out.println("if(isnumberok(document.Form1.elements[m_amount],25)){"); 
			out.println("format_number(document.Form1.elements[m_amount],25)"); 
			out.println("}"); 
			out.println("else{");
			out.println("alert('please enter a number');"); 
			out.println("document.Form1.elements[m_amount].value='';"); 
			out.println("}"); 
			out.println("}"); 
			
			
		
			
			
			
			
			
			
			
			//!--------Display The Header -------------------------------------//
			out.println("function header(){");
			  
				  out.println("m_table.innerHTML=\"\" ");
				//	out.println("lineno=0;");
				//	out.println("arr_size=0;");
										
		      out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\" >'+");
					out.println("'<td  width=\"15%\" align=\"left\"  ><B>Offer Number</td>'+");
					out.println("'<td  width=\"20%\" align=\"left\"  ><B>Name</td>'+");
					out.println("'<td  width=\"30%\" align=\"left\"  ><B>Address</td>'+");
					out.println("'<td  width=\"15%\" align=\"left\"  ><B>Telephone Number</td>'+");
					out.println("'<td  width=\"10%\" align=\"right\"  ><B>Amount</td>'+");
          out.println("'<td  width=\"10%\" align=\"Center\" ><B>Issue</td>'+");
					out.println("'</TR></table>';");		
					
									
					out.println("}");
					
					//added by nuwan de silva 17-07-07-----------------
					out.println("function show_offer(row_No) {"); 
		    	out.println("show_offer_drill(new_data_vec[row_No]);"); 
			    out.println("}"); 
					
					
					out.println("  function  display_data(data_vec){");
			  	out.println("var c_client=0;"); //added by nuwan de silva 17-07-07			 		
					out.println("lineno=0;");
					out.println("arr_size=0;");		
					
					out.println("header();	");		
					
					 out.println("var i=0;");
			   
					  out.println("var j=0;");
						
					 out.println("while(i<data_vec.length){");
			
																
						out.println("m_no='<TD WIDTH=\"15%\" align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"show_offer('+[c_client]+')\"><u>'+data_vec[i]+'</u></TD>';");
						out.println("m_name='<TD WIDTH=\"20%\" align=\"left\">'+data_vec[i+1]+'</TD>';");
						out.println("m_address='<TD WIDTH=\"30%\" align=\"left\">'+data_vec[i+2]+'</TD>';");
						out.println("m_tel_no='<TD WIDTH=\"15%\" align=\"left\">'+data_vec[i+3]+'</TD>';");
						out.println("m_amount='<TD WIDTH=\"10%\" align=\"right\">'+data_vec[i+4]+'</TD>';");
						
						out.println("    if(document.Form1.SCREEN_NAME.value==\"DEL\"){ ");
					  
						out.println("m_chk='<TD WIDTH=\"10%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_ISSUE'+lineno+' VALUE=\"on\" checked disabled onclick=\"change_val_issue_status('+lineno+')\"></td>';");			
						
						out.println("}");
						
						out.println("else {");
						
						out.println("m_chk='<TD WIDTH=\"10%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_ISSUE'+lineno+' VALUE=\"\" onclick=\"change_val_issue_status('+lineno+')\"></td>';");			
						
						out.println("}");
									
						out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_m_no'+lineno+'	VALUE='+data_vec[i]+'>'+");
						out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_m_name'+lineno+'	VALUE=\"'+data_vec[i+1]+'\">'+");
						out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_m_address'+lineno+'	VALUE=\"'+data_vec[i+2]+'\">'+");
						out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_m_tel_no'+lineno+'	VALUE='+data_vec[i+3]+'>'+");
						out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_m_amount'+lineno+'	VALUE='+data_vec[i+4]+'>';");
						
						
			/*	out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<TD WIDTH=\"15%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_OFFER_NO'+lineno+'    value='+data_vec[i]+'   maxlength=\"15\" size=\"20\" disabled onblur=\"\"></td>'+");
				out.println("'<TD WIDTH=\"20%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME'+lineno+'    value=\"'+data_vec[i+1]+'\"  style=\"width:150px;\" maxlength=\"200\" size=\"50\" onblur=\"\"></td>'+");
				out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS'+lineno+' value=\"'+data_vec[i+2]+'\" style=\"width:225px;\" maxlength=\"200\" size=\"10\"></TD>'+");
				out.println("'<TD WIDTH=\"15%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO'+lineno+'  value='+data_vec[i+3]+'  maxlength=\"60\" size=\"60\" ></TD>'+");
				out.println("'<TD WIDTH=\"15%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_AMOUNT'+lineno+' value='+data_vec[i+4]+' maxlength=\"25\" size=\"10\" STYLE=\"{text-align:right;}\" onBlur=\"check_number('+lineno+')\" ></td>'+");
				out.println("'<TD WIDTH=\"5%\" align=\"center\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+'   value=\" X \" onClick=\"del_row('+lineno+')\"></td>'+");
				out.println("'</tr></table>';");
			*/			
											
					//	out.println("m_writedata='<TR class=\"\">'+m_no+m_name+m_address+m_tel_no+m_amount+m_chk+'</TR>'+m_hid_input;"); 
						
						
						out.println("if(j>0 && j%2==1){");
     				out.println("m_writedata='<TR class=\"tr_input1\">'+m_no+m_name+m_address+m_tel_no+m_amount+m_chk+'</TR>'+m_hid_input;"); 
						out.println("	}");
						out.println("	else{");
     				out.println("m_writedata='<TR class=\"tr_input\">'+m_no+m_name+m_address+m_tel_no+m_amount+m_chk+'</TR>'+m_hid_input;"); 
						out.println("	}");
								
								
								  
						out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	    	out.println("m_writedata+'</table>';");
						
				
					  out.println("j=j+1;");
			
						out.println("i=i+5");
						out.println("c_client=c_client+5"); //added by nuwan de silva 17-07-07
						out.println("lineno=lineno+1;");
						out.println("arr_size=arr_size+1;");		
						out.println("}"); //End while loop
		

			out.println("}");		
			
			
			
			
			/*out.println("function add_row(){"); 
			//out.println("alert('line no'+lineno);");
			
				
			 out.println("b_flag=0;");
			
			
			  out.println("if(lineno!=0){");
				out.println("count=lineno-1;");
				out.println("m_name=\"TXT_NAME\"+count");
			  out.println("m_address=\"TXT_ADDRESS\"+count");
				out.println("m_tel_no=\"TXT_TEL_NO\"+count");
				out.println("m_amount=\"TXT_AMOUNT\"+count");

				out.println("if(document.Form1.elements[m_name].value==\"\") {");
				out.println("alert('Please enter a name');");
				out.println("b_flag=1;");
				out.println("}");						
				
				out.println("else if(document.Form1.elements[m_address].value==\"\") {");
				out.println("alert('Please enter a address');");
				out.println("b_flag=1;");
				out.println("}");	
				
				out.println("else if(document.Form1.elements[m_tel_no].value==\"\") {");
				out.println("alert('Please enter a telephone number');");
				out.println("b_flag=1;");
				out.println("}");	
				
				out.println("else if(document.Form1.elements[m_amount].value==\"\") {");
				out.println("alert('Please enter a amount');");
				out.println("b_flag=1;");
				out.println("}");						



				
								
				out.println("else{");
				out.println("b_count=0;");
				out.println("tmp_name=document.Form1.elements[m_name].value;");
				
				out.println("for(var i=0;i<lineno-1;i++){");
				
				out.println("m_tmp_name=\"TXT_NAME\"+i");
				out.println("if(lineno>=2){");
				out.println("if(document.Form1.elements[m_tmp_name].value==tmp_name){");
				out.println("alert('Name Can not Be Duplicated')");
				out.println("b_flag=1;");
				out.println("b_count=1};");
				
				out.println("if(b_count==1){");
				out.println("break;}");
				
				out.println("}");
				
			  out.println("}");
				
			  out.println("}");
				
				
			  
				out.println("}");
				
											
					
				out.println("if(b_flag==0){");
				
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<TD WIDTH=\"15%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_OFFER_NO'+lineno+' disabled maxlength=\"15\" size=\"15\" onblur=\"\"></td>'+");
				out.println("'<TD WIDTH=\"20%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME'+lineno+'    style=\"width:150px;\" maxlength=\"200\" size=\"200\"  onblur=\"\"></td>'+");
				out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS'+lineno+' style=\"width:225px;\" maxlength=\"200\" size=\"200\" </TD>'+");
				out.println("'<TD WIDTH=\"15%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO'+lineno+'   maxlength=\"60\" size=\"60\" ></TD>'+");
				out.println("'<TD WIDTH=\"15%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_AMOUNT'+lineno+' maxlength=\"25\" size=\"10\" STYLE=\"{text-align:right;}\" onblur=\"check_number('+lineno+')\" ></td>'+");
				out.println("'<TD WIDTH=\"5%\" align=\"center\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' value=\" X \" onClick=\"del_row('+lineno+')\"></td>'+");
				out.println("'</tr></table>';");
				
				 
				out.println("lineno=lineno+1;");
				out.println("arr_size=arr_size+1;");
      	out.println("}");
				
				
			 out.println("}");
				
				
				
				
			out.println("function del_row(rowNo){"); 
			
			out.println("if(rowNo!=0){");
		
			out.println("var j=0;");
			
			out.println("for(var i=0;i<arr_size;i++){");
			
			out.println("m_offer_no=\"TXT_OFFER_NO\"+i");
			out.println("m_name=\"TXT_NAME\"+i");
			out.println("m_address=\"TXT_ADDRESS\"+i");
			out.println("m_tel_no=\"TXT_TEL_NO\"+i");
			out.println("m_amount=\"TXT_AMOUNT\"+i");
			
			
			out.println("if(i==rowNo)");
			out.println("continue;");
			
			out.println("array_offer_no[j]=document.Form1.elements[m_offer_no].value;");		
			out.println("array_name[j]=document.Form1.elements[m_name].value;");
		  out.println("array_address[j]=document.Form1.elements[m_address].value;");
			out.println("array_tel_no[j]=document.Form1.elements[m_tel_no].value;");
			out.println("array_amount[j]=document.Form1.elements[m_amount].value;");
		   
			out.println("j=j+1;");
			
			out.println("}");
		
			out.println("lineno=lineno-1;");
			out.println("arr_size=arr_size-1;");
		  out.println("write_data(arr_size);");
			out.println("}"); 
			out.println("}"); 
			  
			out.println("function write_data(size){");
				
			
				
			//	out.println("m_table.innerHTML=\"\";");
        out.println("header();");
	      out.println(" for(var j=0;j<size;j++){");
				
				
			  out.println("if(array_name[j]==\"\" && array_address[j]==\"\" && array_tel_no[j]==\"\" && array_amount[j]==\"\" && array_offer_no[j]==\"\" ){");
				 
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<TD WIDTH=\"15%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_OFFER_NO'+j+' size=\"20\" maxlength=\"15\" disabled onblur=\"\"></td>'+");
				out.println("'<TD WIDTH=\"20%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME'+j+'    style=\"width:150px;\" maxlength=\"200\" size=\"200\" onblur=\"\"></td>'+");
				out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS'+j+' style=\"width:225px;\" maxlength=\"200\" size=\"200\"></TD>'+");
				out.println("'<TD WIDTH=\"15%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO'+j+'   maxlength=\"60\" size=\"60\" ></TD>'+");
				out.println("'<TD WIDTH=\"15%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_AMOUNT'+j+' maxlength=\"25\" size=\"10\" STYLE=\"{text-align:right;}\" onBlur=\"check_number('+j+')\" ></td>'+");
				out.println("'<TD WIDTH=\"5%\"  align=\"center\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' value=\" X \" onClick=\"del_row('+j+')\"></td>'+");
				out.println("'</tr></table>';");
				out.println("continue;");
				out.println("}");
				
				out.println("if(array_name[j]!=\"\" && array_address[j]!=\"\" && array_tel_no[j]!=\"\" && array_amount[j]!=\"\" && array_offer_no[j]==\"\" ){");
				 
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<TD WIDTH=\"15%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_OFFER_NO'+j+'  size=\"20\" maxlength=\"15\" disabled onblur=\"\"></td>'+");
				out.println("'<TD WIDTH=\"20%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME'+j+'    value=\"'+array_name[j]+'\" style=\"width:150px;\" maxlength=\"200\" size=\"200\" onblur=\"\"></td>'+");
				out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS'+j+' value=\"'+array_address[j]+'\" style=\"width:225px;\" maxlength=\"200\" size=\"200\"></TD>'+");
				out.println("'<TD WIDTH=\"15%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO'+j+'  value='+array_tel_no[j]+'  maxlength=\"60\" size=\"60\" ></TD>'+");
				out.println("'<TD WIDTH=\"15%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_AMOUNT'+j+'  value='+array_amount[j]+'  maxlength=\"25\" size=\"10\" STYLE=\"{text-align:right;}\" onBlur=\"check_number('+j+')\" ></td>'+");
				out.println("'<TD WIDTH=\"15%\" align=\"center\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' value=\" X \" onClick=\"del_row('+j+')\"></td>'+");   
				out.println("'</tr></table>';");
				out.println("continue;");
				out.println("}");

										     
		    out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<TD WIDTH=\"15%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_OFFER_NO'+j+' value='+array_offer_no[j]+' size=\"20\" maxlength=\"15\" disabled onblur=\"\"></td>'+");
				out.println("'<TD WIDTH=\"20%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_NAME'+j+'    value=\"'+array_name[j]+'\" style=\"width:150px;\" maxlength=\"200\" size=\"200\" onblur=\"\"></td>'+");
				out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ADDRESS'+j+' value=\"'+array_address[j]+'\" style=\"width:225px;\" maxlength=\"200\" size=\"200\"></TD>'+");
				out.println("'<TD WIDTH=\"15%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_TEL_NO'+j+'  value='+array_tel_no[j]+'  maxlength=\"60\" size=\"60\" ></TD>'+");
				out.println("'<TD WIDTH=\"15%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_AMOUNT'+j+'  value='+array_amount[j]+'  maxlength=\"25\" size=\"10\" STYLE=\"{text-align:right;}\" onBlur=\"check_number('+j+')\" ></td>'+");
				out.println("'<TD WIDTH=\"15%\" align=\"center\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' value=\" X \" onClick=\"del_row('+j+')\"></td>'+");   
				out.println("'</tr></table>';");
		   			
			 out.println("}");
			 out.println("}");		
				
				*/
				
			out.println("function change_val_issue_status(row_no){")	;
			
			
			out.println("for(i=0;i<arr_size;i++){");
			
			out.println("m_chk_issue=\"CHK_ISSUE\"+i;");
			
			out.println("if(i==row_no){");
			out.println("if(document.Form1.elements[m_chk_issue].checked==true){");
			out.println("document.Form1.elements[m_chk_issue].value='on'");
			out.println("}else if(document.Form1.elements[m_chk_issue].checked==false){");
			out.println("document.Form1.elements[m_chk_issue].value='off'");
			out.println("}");
			out.println("}");
			out.println("else{");
			out.println("document.Form1.elements[m_chk_issue].checked=false;");
			out.println("document.Form1.elements[m_chk_issue].value='off'");
			out.println("}");
			out.println("}");			
			
					
						
			out.println("}");			
			
			
			out.println("function ckeck_issues(){ "); 
			
			out.println("b_flag=0;");
			out.println("b_flag_max=0;");
			
			out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
			out.println("if(m_table.innerHTML==\"\"){");
			out.println("alert('No offers to save');");
			out.println("b_flag=1;");
			out.println("}"); 
			out.println("else if(!count_offers()){"); 
			out.println("alert('Please select an offer');");
			out.println("b_flag=1;");
			out.println("}"); 
			out.println("else if(!get_max()){"); 
			out.println("alert('Are you sure offer selected  is not the maximum');");
			//out.println("b_flag_max=1;");
			out.println("}"); 

			
			out.println("else");
			out.println("b_flag=0;");
			out.println("}"); 
			
      out.println("}"); 
			
			
			/*---------------------------------------------------------------------
			Purpose : This Function Used TO Count THe Number Of Receipts Selected
			--------------------------------------------------------------------*/
			out.println("function count_offers(){ ");
			out.println("count=0;");
		//	out.println("alert('arr size'+arr_size);");
			out.println("for(i=0;i<arr_size;i++){");
			out.println("m_chk_issue_tmp=\"CHK_ISSUE\"+i;");
			
			out.println("if(document.Form1.elements[m_chk_issue_tmp].checked==true){");
		  out.println("count=count+1;");
			out.println("}");		
			
			out.println("}");		
			
			out.println("if(count>0)");
			out.println("return true;");
			out.println("else");
			out.println("return false;");
			
			out.println("}"); 
			
			
				/*---------------------------------------------------------------------
			Purpose : This Function Used TO Count THe Number Of Receipts Selected
			--------------------------------------------------------------------*/
			out.println("function get_max(){ ");
			out.println("row_value=0;");
			
			//	out.println("alert('arr size'+arr_size);");
			out.println("var max=0");
			out.println("for(i=0;i<arr_size;i++){");
			out.println("m_amount=\"hid_m_amount\"+i;");
			
					
			out.println("if(parseFloat(unformat_noobject(document.Form1.elements[m_amount].value))>parseFloat(unformat_noobject(max)) ){");
		  out.println("max=parseFloat(unformat_noobject(document.Form1.elements[m_amount].value));");
			out.println("row_value=i;");
			out.println("}");		
			
			out.println("}");		
			
			
			out.println("m_chk_issue_tmp=\"CHK_ISSUE\"+row_value;");
			
			out.println("if(document.Form1.elements[m_chk_issue_tmp].checked==true){");
		  out.println("return true;");
			out.println("}");		
		  out.println("else");
			out.println("return false;");
			
			out.println("}"); 
			
			
			
			
			out.println("function View_Letter(){");
					
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Advertistment_Offers_Issue_Letter_View\";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=650,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Advertistment_Offers_Issue';"); 
			
			out.println("}");
			
			
			
						
				
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\"> "); //load_lock(), header(),add_row()
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_ADVEST_OFFER_ISSUE\">"); 
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection - Repossession - Acceptance Of Offers - New</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
				//out.println("<td width='10%'></td>");
				//out.println("<td width='10%'></td>");
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
		//	out.println("<td width='10%'></td>");
			//		out.println("<td width='10%'></td>");
			//out.println("<td width='10%' align='center'><input type=\"button\" name='btn_delete' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			//out.println("<td width='10%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%'></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Letter\");'  onclick='View_Letter()' value=\"Letter\"></td>"); 
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
			out.println("<input class='but_input' type='button' name='BUT_TXT_ADVETST_NO' value=\"...\" onClick=\"help_advetst_no()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_INVENTORY_NO'  class=div_input>Inventory Number </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_INVENTORY_NO' maxlength='15' size='15'  onblur=\"assignState('M_INVENTORY'),makeRequest(document.Form1.TXT_INVENTORY_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_INVENTORY_NO' value=\"...\" onClick=\"help_button_inventory()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_VEHICLE_NO'  class=div_input>Vehicle No </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_VEHICLE_NO' maxlength='10' disabled  size='10' onblur=\"\"></td>"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_VEHICLE_NO' value=\"Help\"  disabled onClick=\"help_button_vehicle_no()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr>"); 
			out.println("<td width='30%' >Outstanding Value Of Invoices</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_OUTSTANDING_VALUE_INVOICES' maxlength='25' disabled  size='25' onblur=\"\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' >Outstanding Value Of Non Invoices</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_OUTSTANDING_VALUE' maxlength='25' disabled  size='25' onblur=\"\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			
			out.println("<tr>"); 
			out.println("<td width='30%' >Total Outstanding Value </td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_TOTAL_OUTSTANDING_VALUE' maxlength='25' disabled  size='25' onblur=\"\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 


			
			out.println("<td >Release Mode</td>");
			out.println("<td><SELECT name=\"RELEASE_MODE\" class=\"txt_input\" onChange=\"\"> ");
			out.println("<OPTION value=\"LEASE\" >Lease  </OPTION>");
			out.println("<OPTION value=\"CASH\"  >Cash   </OPTION>");
			out.println("</SELECT></TD>");
			out.println("</tr>");

			
			
			out.println("<table align='center' width='100%' class='table'>"); 
			//out.println("<tr>");  
			//out.println("<td width='*%'><input class='but_input' type='button' name='MORE_BUT' value=\"Add\" onClick=\"add_row()\"></td>"); 
			//out.println("</tr>"); 
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
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
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
