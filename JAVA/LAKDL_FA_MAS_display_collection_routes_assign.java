// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:07-12-2006
    

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_FA_MAS_display_collection_routes_assign extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration -  Assign Collection Routes </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			 
			out.println("var m_sav_msg='';");
			out.println("function get_vector(data_vec) {");
			out.println("	 if(data_vec.length>0  && document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CODE.value!=\"\" && document.Form1.SCREEN_NAME.value==\"NEW\" ){	");
			out.println("    if(data_vec[3] == \"N\"){ ");
			out.println("       m_str = 'Route officer code exist with route officer code : '+data_vec[0]+' and name : '+data_vec[1]+' already deactivated, Do you want to reactivate ? ' ");
			out.println("	      if(confirm(m_str)) { ");
			out.println("    	    document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CODE.value=data_vec[0];"); 
			out.println("    	    document.Form1.TXT_COLLECTION_ROUTE_OFFICER_NAME.value=data_vec[1];"); 
			out.println("    	    document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CONTACT.value=data_vec[2];"); 
			out.println("    	    makeRequest_detail();");
			out.println("		    }");
			out.println("			  else {");
			out.println("				 clear_data();");
			out.println("		    }");
			out.println("		 }");
			out.println("		 else {	");
			out.println("    	 document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CODE.value=data_vec[0];"); 
			out.println("    	 document.Form1.TXT_COLLECTION_ROUTE_OFFICER_NAME.value=data_vec[1];"); 
			out.println("    	 document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CONTACT.value=data_vec[2];"); 
			out.println("    	 makeRequest_detail();");
			out.println("		 }");
			out.println("		}");
			out.println("	 else if(data_vec.length==0  && document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CODE.value!=\"\" && document.Form1.SCREEN_NAME.value==\"NEW\" ){	");
			out.println("		 makeRequest_detail();");
			out.println("		}");
			out.println("	 else if(data_vec.length>0  && document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CODE.value!=\"\" && document.Form1.SCREEN_NAME.value!=\"NEW\"  ){");
			out.println("    	document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CODE.value=data_vec[0];"); 
			out.println("    	document.Form1.TXT_COLLECTION_ROUTE_OFFICER_NAME.value=data_vec[1];"); 
			out.println("    	document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CONTACT.value=data_vec[2];"); 
			out.println("    	makeRequest_detail();");
			out.println("		}");
			out.println("	 else if(data_vec.length==0 &&  document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CODE.value!=\"\" ){");
			out.println("			help_update();");
			out.println("		}");
			out.println("}");
	  
			out.println("function makeRequest(obj1,obj2) {");
			out.println("	if(obj1.value!=\"\"){");
			out.println("		document.Form1.hid_help_status.value=obj2;");
			out.println("		obj3=document.Form1.SCREEN_NAME.value;");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_COLLECTION_ROUTE_ASSIGN&data_val=\"+obj1.value+\"&data_va2=\"+obj2+\"&data_va3=\"+obj3;");
			//out.println("window.open(m_url);");
			out.println("		load_interface(m_url,'XML');");
			out.println("	}");
			out.println("}");
			
			out.println("function makeRequest_detail() {");
			out.println("		obj3=document.Form1.SCREEN_NAME.value;");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_MAS_sql_validations_normal?chksql=COLLECTION_ROUTE_ASSIGN&route_officer_id=\"+document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CODE.value+\"&screen_id=\"+obj3;");
			//out.println("window.open(m_url);");
			out.println("		load_interface(m_url,'NO');");
			out.println("}");
			
			
			out.println("function get_vector_normal(m_data){");
			out.println("		collection_areas_details.innerHTML=m_data;");
			out.println("		document.Form1.hid_area_count.value=document.Form1.NUM_CHKS.value;");
			out.println("}");
			
			out.println("function clear_fields(){"); 
			out.println("	 if(document.Form1.hid_help_type.value==\"99\") {" ); 
			out.println("		clear_data();");
			out.println("  }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"2\") {" ); 
			out.println("		obj_num=document.Form1.hid_obejct_no.value;");
			out.println("		document.Form1.elements[\"TXT_ROUTE_CODE_\"+obj_num].value=\"\";"); 
			out.println("		document.Form1.elements[\"TXT_ROUTE_DESC_\"+obj_num].value=\"\";"); 
			out.println("		document.Form1.elements[\"TXT_ROUTE_DESC_\"+obj_num].disabled=true;");
			out.println("  }		"); 
			out.println("}		"); 
			
			
			out.println("function clear_data(){"); 
			out.println("  document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CODE.value=\"\";"); 
			out.println("  document.Form1.TXT_COLLECTION_ROUTE_OFFICER_NAME.value=\"\";"); 
			out.println("  document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CONTACT.value=\"\";"); 
			out.println("  collection_areas_details.innerHTML=\"\";");
			out.println("	 makeRequest_detail();");
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("	//validations goes here"); 
			out.println("		 m_dir_count=parseInt(document.Form1.hid_area_count.value);");
			out.println("		 m_route =	\"TXT_ROUTE_CODE_\"+m_dir_count ");
			out.println("	if(document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CODE.value==\"\" ){  "); 
			out.println("		DIV_TXT_COLLECTION_ROUTE_OFFICER_CODE.style.color='red';");
			out.println("		return false;"); 
			out.println("	}"); 
			out.println("	else if(m_dir_count!=0 && document.Form1.elements[m_route].value==\"\" ){  "); 
			out.println("		DIV_TXT_COLLECTION_AREA_CODE.style.color='red';");
			out.println("		return false;"); 
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		return true;"); 
			out.println("	}"); 
			out.println("}"); 			
			
			out.println("function check_duplicates(){");
			out.println("		 m_dir_count=parseInt(document.Form1.hid_area_count.value);");
			out.println("		 m_route =	\"TXT_ROUTE_CODE_\"+m_dir_count ");
			out.println(" if( document.Form1.elements[m_route].value != \"\" ) { ");
			out.println("			  m_route_count = parseInt(document.Form1.hid_area_count.value)");
			out.println("		    m_route =	\"TXT_ROUTE_CODE_\"+m_route_count ");
			out.println("		    m_desc =	\"TXT_ROUTE_DESC_\"+m_route_count ");
			out.println("        tmp_route = document.Form1.elements[m_route].value; ");
			out.println("			   b_count = 0;");
			out.println("         for(var i=1;i<m_dir_count;i++){");
			out.println("            m_tmp_route=\"TXT_ROUTE_CODE_\"+i");				
			out.println("									cur_route= document.Form1.elements[m_tmp_route].value ");
			out.println("             if(m_dir_count >=2 ){");
			out.println("               if(document.Form1.elements[m_tmp_route].value == tmp_route){");
			out.println("                 alert('Route code cannot be duplicated')");
			out.println("									document.Form1.elements[m_route].value =''; ");
			out.println("									document.Form1.elements[m_desc].value =''; ");
			out.println("                 b_count=1;");
			out.println("		 						  return false;	");
			out.println("		            }"); 
			out.println("               if(b_count==1){");
			out.println("                break;");
			out.println("		            }"); 
			out.println("             } ");
			out.println("         }");
			out.println("		 	  return true;	");
			out.println(" }"); 
			out.println("}");
			
			out.println("function assignState(val){");
			out.println("	document.Form1.hid_chk_status.value=val;");
			out.println("}");

			out.println("function before_submit(){ "); 
			out.println("	get_display_msg();");
			out.println("	if(validate_data()){"); 
			out.println("  if(check_duplicates()) {");
			out.println("	 	 if(confirm(\"Are you sure you want to \"+m_sav_msg+\" ?\")){ ");
			out.println("			 for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("				 document.Form1.elements[i].disabled=false;");
			out.println("			 }");
			out.println("			 if(validate_data()){"); 
			out.println("				 document.Form1.action='"+m_class_url+"/"+m_fschema_name+"FA_MAS_Save_Collection_routes_assign';");  
			out.println("				 document.Form1.submit();	"); 
			out.println("			 }"); 
			out.println("		 }"); 
			out.println("	 }"); 
			out.println("	}"); 
			out.println(" else{ "); 
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("	}");
			out.println("} "); 

			out.println("function load_lock(){	"); 
			out.println("//document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_MAS_display_collection_routes_assign';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_MAS_display_collection_routes_assign';"); 
			out.println("}"); 

			out.println("function save_window(){	"); 
			out.println("	before_submit();"); 
			out.println("}"); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_FA_MAS_COLLECTION_ROUTE_ASSIGN\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
 
			out.println("function load_roll_value(m_val){"); 
			out.println("	help_box.innerHTML=\" System Administration - Assign Collection Routes - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\" System Administration - Assign Collection Routes - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("		if(m_val==\"NEW\"){"); 
			out.println("			new_window();"); 
			out.println("			document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CODE.disabled=true;"); 
			out.println("		}"); 
			out.println("		else if(m_val==\"HELP\"){"); 
			out.println("			load_help_msg();"); 
			out.println("		}"); 
			out.println("		else if(m_val!=\"EDIT\"){"); 
			//out.println("			clear_data();");
			out.println("			document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("			document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CODE.disabled=false;"); 
			out.println("			document.Form1.BUT_AREA_ADD.disabled=true;"); 
			out.println("		}"); 
			out.println("		else if(m_val==\"EDIT\"){"); 
			//out.println("			clear_data();");
			out.println("			document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("			document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CODE.disabled=false;"); 
			out.println("			document.Form1.BUT_AREA_ADD.disabled=false;"); 
			out.println("		}"); 
			out.println("		else{");
			out.println("			document.Form1.BUT_HELP_MAIN.disabled=false;");
			out.println("		}"); 
			out.println("		document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("		if(m_val==\"NEW\"){");
			out.println("			document.Form1.hid_status.value=\"New\";"); 
			out.println("		}");
			out.println("		else if(m_val==\"EDIT\"){");  
			out.println("			document.Form1.hid_status.value=\"Edit\";");  
			out.println("		}");
			out.println("		else if(m_val==\"DACT\"){");  
			out.println("			document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("		}");
			out.println("		else if(m_val==\"RACT\"){");  
			out.println("			document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("		}");
			out.println("		else{");  
			out.println("			document.Form1.hid_status.value=\"\";");  
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function get_display_msg(){"); 
			out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("		m_sav_msg=\"Save\";"); 
			out.println("	}");
			out.println("	else if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");  
			out.println("		m_sav_msg=\"Modify\";");  
			out.println("	}");
			out.println("	else if(document.Form1.SCREEN_NAME.value==\"DACT\"){");  
			out.println("		m_sav_msg=\"Deactivate\";");  
			out.println("	}");
			out.println("	else if(document.Form1.SCREEN_NAME.value==\"RACT\"){");  
			out.println("		m_sav_msg=\"Reactivate\";");  
			out.println("	}");
			out.println("	else{");  
			out.println("		m_sav_msg=\"\";");  
			out.println("	}"); 
			out.println("}"); 

			out.println("function MyDialog(){"); 
			out.println("	this.valout   = new Array(10);"); 
			out.println("}"); 

			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"FA_MAS_Help_Servlet?class_in=\"+client_name+\"FA_MAS_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	 clear_fields();	");
			out.println(" } else"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("		if(oBj.valout[1] !=\"Close\"){"); 
			out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("				if(oBj.valout[1]!=\"Next\"){"); 
			out.println("					if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("						help_update_value_assign_99();"); 
	  	out.println("					}"); 
			out.println("					if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("						help_update_value_assign_2();"); 
	  	out.println("					}"); 
			out.println("				}"); 
			out.println("				else{"); 
			out.println("					Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("					return false;"); 
			out.println("				} "); 
			out.println("			}"); 
			out.println("			else{	"); 
			out.println("				Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("			}	"); 
			out.println("	 	}");  
			out.println("	else{	"); 
			out.println("			clear_fields();"); 
			out.println("		}	"); 
			out.println("	}"); 
			out.println("}"); 

			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("		HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("		HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			
			out.println("function help_update() {"); 
			out.println(" document.Form1.hid_help_type.value=\"99\";"); 
			out.println(" if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println(" 	m_sql = \"m_help_DIV_TXT_COLLECTION_ROUTE_EMP_CODE_EDIT_sql\";"); 
			out.println("  	m_criteria = document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CODE.value+\"@\"+\"Y@\";"); 
			out.println(" } ");
			out.println(" else if(document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
			out.println(" 		m_sql = \"m_help_DIV_TXT_COLLECTION_ROUTE_EMP_CODE_sql\";"); 
			out.println("  		m_criteria = document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CODE.value+\"@\"+\"Y@\";"); 
			out.println(" } ");
			out.println(" else if(document.Form1.SCREEN_NAME.value==\"RACT\"){");
			out.println(" 	m_sql = \"m_help_DIV_TXT_COLLECTION_ROUTE_EMP_CODE_EDIT_sql\";"); 
			out.println("  	m_criteria = document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CODE.value+\"@\"+\"N@\";"); 
			out.println(" } ");
			out.println(" HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("		document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CODE.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_COLLECTION_ROUTE_OFFICER_NAME.value=oBj.valout[3];"); 
			out.println("		document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CONTACT.value=oBj.valout[4];"); 
			out.println("		makeRequest(document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CODE,'1');");
			out.println("}"); 
			
			out.println("function help_update_value_assign_2() {"); 
			out.println("		obj_num=document.Form1.hid_obejct_no.value;");
			out.println("		document.Form1.elements[\"TXT_ROUTE_CODE_\"+obj_num].value=oBj.valout[2];"); 
			out.println("		document.Form1.elements[\"TXT_ROUTE_DESC_\"+obj_num].value=oBj.valout[3];"); 
			out.println("		document.Form1.elements[\"TXT_ROUTE_DESC_\"+obj_num].disabled=true;");
			out.println("}");
			
			out.println("function update_route_code(m_num) {"); 
			out.println(" document.Form1.hid_help_type.value=\"2\";"); 
			out.println(" obj1=document.Form1.elements[\"TXT_ROUTE_CODE_\"+m_num].value;");
			out.println("	document.Form1.hid_obejct_no.value=m_num;");
			out.println("	if(obj1 != ''){");
			out.println(" m_sql = \"m_help_DIV_TXT_COLLECTION_ROUTE_CODE_sql\";"); 
			out.println(" m_criteria = obj1+\"@\";"); 
			out.println(" HelpBox('1','10','0');"); 
			out.println(" }"); 
			out.println("	else {");
			out.println(" m_sql = \"m_help_DIV_TXT_COLLECTION_ASSIGN_ROUTE_CODE_sql\";"); 
			out.println(" m_criteria = obj1+\"@\";"); 
			out.println(" HelpBox('1','10','0');"); 
			out.println(" }"); 
			out.println("}"); 
			
			out.println("function HelpView(Start,End,Hid_No,Max) {"); 
			out.println(" oBj = new MyDialog();"); 
			out.println(" oBj.valout[1]  = \" \";"); 
			out.println(" oBj.valout[2]  = \" \";"); 
			out.println(" oBj.valout[3]  = \" \";"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_View_Help_Servlet?class_in=\"+client_name+\"FA_MAS_View_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:40em; dialogHeight:25em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("		if(oBj.valout[1] !=\"Close\"){"); 
			out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("				if(oBj.valout[1]!=\"Next\"){"); 
			out.println("					if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("						help_update_value_assign_99();"); 
	  	out.println("					}"); 
			out.println("					if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("						help_value_assign_1();"); 
	  	out.println("					}"); 
			out.println("				}"); 
			out.println("				else{"); 
			out.println("					ViewNext(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("					return false;"); 
			out.println("				} "); 
			out.println("			}"); 
			out.println("			else{	"); 
			out.println("				ViewPrev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("			}	"); 
			out.println("		}		"); 
			out.println("	}	"); 
			out.println("}"); 
			
			out.println("function ViewPrev(Start,End,Hid_No){"); 
			out.println("    HelpView(Start,End,Hid_No);"); 
			out.println("}"); 

			out.println("function ViewNext(Start,End,Hid_No){"); 
			out.println("    HelpView(Start,End,Hid_No);"); 
			out.println("}"); 
			
			out.println("function View_all(){");	
			out.println("    m_sql = \"m_help_DIV_TXT_COLLECTION_ROUTE_CODE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_COLLECTION_ROUTE_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			out.println("function add_route_details(){"); 
			out.println(" var b_flag=0;");
			out.println("		 m_dir_count=parseInt(document.Form1.hid_area_count.value);");
			out.println("		 m_area =	\"TXT_ROUTE_CODE_\"+m_dir_count ");
			out.println("		 m_dir_count++;");
			out.println("    if( parseInt(document.Form1.hid_area_count.value) != 0  ) { ");
			out.println("			 if( document.Form1.elements[m_area].value == \"\" ) { ");
			out.println("    	  alert('Route code cannot be null ');");
			out.println("		    b_flag=1;	");
			out.println("		   }"); 
			out.println("    	 else if( parseInt(document.Form1.hid_area_count.value) >1  && document.Form1.elements[m_area].value != \"\" ) { ");
			out.println("			  m_route_count = parseInt(document.Form1.hid_area_count.value)");
			out.println("		    m_route =	\"TXT_ROUTE_CODE_\"+m_route_count ");
			out.println("		    m_desc =	\"TXT_ROUTE_DESC_\"+m_route_count ");
			out.println("        tmp_route = document.Form1.elements[m_route].value; ");
			out.println("			   b_count = 0;");
			out.println("         for(var i=1;i<m_dir_count-1;i++){");
			out.println("            m_tmp_route=\"TXT_ROUTE_CODE_\"+i");				
			out.println("									cur_route= document.Form1.elements[m_tmp_route].value ");
			out.println("             if(m_dir_count >=2 ){");
			out.println("               if(document.Form1.elements[m_tmp_route].value == tmp_route){");
			out.println("                 alert('Route code cannot be duplicated')");
			out.println("									document.Form1.elements[m_route].value =''; ");
			out.println("									document.Form1.elements[m_desc].value =''; ");
			out.println("                 b_count=1;");
			out.println("		 						  b_flag=1;	");
			out.println("		            }"); 
			out.println("               if(b_count==1){");
			out.println("                break;");
			out.println("		            }"); 
			out.println("             } ");
			out.println("         }");
			out.println("		   }"); 
			out.println("		 }"); 
			
			out.println("		if(b_flag == 0 ) {	");
			out.println("		 collection_areas_details.innerHTML=collection_areas_details.innerHTML+'<table  width=\"100%\">'+"); 
			out.println("		 '<tr>'+"); 
			out.println("		 '<td width=\"1%\" class=div_input></td>'+"); 
			out.println("		 '<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_ROUTE_CODE_'+m_dir_count+'\" maxlength=\"10\" disabled ><input class=\"but_input\" type=\"button\" name=\"BUT_BANK_ACC_HELP\" value=\"?\" onClick=\"update_route_code('+m_dir_count+')\"></td>'+"); 
			out.println("		 '<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_ROUTE_DESC_'+m_dir_count+'\" maxlength=\"10\" disabled ></td>'+");
			out.println("		 '<td width=\"*%\" class=div_input><input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_ACC_MAIN\" value=\"Delete\" onClick=\"delete_route_details('+m_dir_count+')\"></td>'+"); 
			out.println("		 '</tr>'+"); 
			out.println("		 '</table>';"); 
			out.println("		 document.Form1.hid_area_count.value=m_dir_count;");
			out.println("    }"); 
			//out.println("		 document.Form1.hid_area_count.value=m_dir_count;");
			out.println("}"); 

			out.println("function delete_route_details(mnum){");
			out.println("	if(parseInt(mnum)>0){");
			out.println("		 m_dir_count=parseInt(document.Form1.hid_area_count.value);");
			out.println("    m_count=0;");
			out.println("    m_str=\"\";");
			out.println("    for(k=1;k<=m_dir_count;k++){");
			out.println("    	if(k!=mnum){");
			out.println("		 		m_count++;");
			out.println("    		obj1=document.Form1.elements[\"TXT_ROUTE_CODE_\"+k].value;");
			out.println("    		obj2=document.Form1.elements[\"TXT_ROUTE_DESC_\"+k].value;");
			out.println("		 		m_str=m_str+'<table  width=\"100%\">'+"); 
			out.println("		 		'<tr>'+"); 
			out.println("		 		'<td width=\"1%\" class=div_input></td>'+"); 
			out.println("		 		'<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_ROUTE_CODE_'+m_count+'\" maxlength=\"10\" value=\"'+obj1+'\" disabled ><input class=\"but_input\" type=\"button\" name=\"BUT_BANK_ACC_HELP\" value=\"?\" onClick=\"update_route_code('+m_count+')\"></td>'+"); 
			out.println("		 		'<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_ROUTE_DESC_'+m_count+'\" maxlength=\"50\" value=\"'+obj2+'\" disabled ></td>'+");
			out.println("		 		'<td width=\"*%\" class=div_input><input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_ACC_MAIN\" value=\"Delete\" onClick=\"delete_route_details('+m_count+')\"></td>'+"); 
			out.println("		 		'</tr>'+"); 
			out.println("		 		'</table>';");
			out.println("    	}");
			out.println("	 	 }");
			out.println("		 collection_areas_details.innerHTML=m_str;");
			out.println("		 document.Form1.hid_area_count.value=m_count;");
			//out.println("		 alert(document.Form1.hid_area_count.value);	");
			out.println("	}");
			out.println("}"); 
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New'),makeRequest_detail()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_area_count' VALUE=\"0\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_obejct_no' VALUE=\"0\">"); 
			
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>"+m_header_name+"</td>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Assign Collection Routes </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all()' value=\"View All\"></td>");
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
			
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_COLLECTION_ROUTE_OFFICER_CODE'  class=div_input>Route Officer Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_COLLECTION_ROUTE_OFFICER_CODE' maxlength='10' size='10' onblur=\"makeRequest(document.Form1.TXT_COLLECTION_ROUTE_OFFICER_CODE,'1')\" disabled >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_COLLECTION_ROUTE_OFFICER_NAME'  class=div_input>Route Officer Name *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_COLLECTION_ROUTE_OFFICER_NAME' maxlength='50' size='50' style='width:200' onblur=\"\" disabled ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
     
			out.println("<tr >"); 
			out.println("<td width='20%' >Contact No</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_COLLECTION_ROUTE_OFFICER_CONTACT' maxlength='10' size='10' onblur=\"\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			
			out.println("<br>"); 
			out.println("<table>"); 
			out.println("<tr >"); 
			out.println("<td width='20%'  class=div_input><b>Collection Routes<b></td>"); 
			out.println("<td width='*%' ></td>");
			out.println("</tr>"); 
			out.println("</table>");
			out.println("<br>"); 
			out.println("<table width=\"100%\">");
			out.println("<tr>"); 
			out.println("<td width=\"1%\" class=div_input></td>"); 
			out.println("<td width=\"20%\" class=div_input><DIV id='DIV_TXT_COLLECTION_AREA_CODE'  class=div_input>Route Code *</td>"); 
			out.println("<td width=\"15%\" class=div_input>Description</td>");
			out.println("<td width=\"*%\" class=div_input><input class=\"but_input\" type=\"button\" name=\"BUT_AREA_ADD\" value=\"Add\" onClick=\"add_route_details()\"></td>");
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("<DIV id='collection_areas_details'  class=div_input></DIV>");
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());
			}catch(Exception e){}
		}
		finally{
				if(out!=null){
				try{out.close();  
				}catch(Exception e){}
				}
		}
	}
}
