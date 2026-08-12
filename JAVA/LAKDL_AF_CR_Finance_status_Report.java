 
//Created by Chandana on 08-03-2007 
//Finance Staus Report

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CR_Finance_status_Report extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2;
	public String m_chksql;
	ServletOutputStream out = null;
	int m_appno_count=0;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			//LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_fschema_name=con_method.client_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
      String m_username 						= "AA";//m_sn_methods.username;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					else if(m_chksql.trim().equals("main_page")){
					   
						  String m_sort_column="REPOSSESSION_NO";	
							String m_order_by_type = "ASC";
							
							if(req.getParameter("sort_column")!=null){
			          m_sort_column = req.getParameter("sort_column");
							}
					 		if(req.getParameter("order_by_type")!=null){
								m_order_by_type = req.getParameter("order_by_type");
							}
     
        out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				
				
				
				
				
				
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length==0 && field=='T1' && document.Form1.TXT_INQUARY_NO.value!=\"\"){");
			out.println("help_update_inquiry();");
			out.println("			}");
			out.println("	else	if(data_vec.length==0  && field=='T2' && document.Form1.TXT_APPLICATION_NO.value!=\"\"){");
			out.println("help_update_application();");
			out.println("			}");
			out.println("	else	if(data_vec.length==0  && field=='T3' && document.Form1.TXT_FINANCE_NO.value!=\"\"){");
			out.println("help_update_finance();");
			out.println("			}");
			out.println("	else	if(data_vec.length==0  && field=='T4' && document.Form1.TXT_VIHICLE_NO.value!=\"\"){");
			out.println("help_update_vihicle();");
			out.println("			}");
			out.println("	else	if(data_vec.length==0  && field=='T5' && document.Form1.TXT_CLIENT_NO.value!=\"\"){");
			out.println("help_update_client();");
			out.println("			}");
  		out.println("}");
			
			out.println("function check_inquary(obj) {");
			out.println("field='T1'");
			out.println("m_url=\""+m_class_url+"/LAKDL_AF_PRO_CR_sql_validations?chksql=m_prime_chk_AF_CR_Finance_status_inqury_no&data_val=\"+document.Form1.TXT_INQUARY_NO.value+\" \";"); 
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function check_application(obj) {");
			out.println("field='T2'");
			out.println("m_url=\""+m_class_url+"/LAKDL_AF_PRO_CR_sql_validations?chksql=m_prime_chk_AF_CR_Finance_status_application_no&data_val=\"+document.Form1.TXT_APPLICATION_NO.value+\" \";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			out.println("function check_finance(obj) {");
			out.println("field='T3'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_AF_CR_Finance_status_finance_no&data_val=\"+document.Form1.TXT_FINANCE_NO.value+\" \";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function check_vihicle(obj) {");
			out.println("field='T4'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_AF_CR_Finance_status_vihicle_no&data_val=\"+document.Form1.TXT_FINANCE_NO.value+\" \";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function check_client(obj) {");
			out.println("field='T5'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_AF_CR_Finance_status_client_no&data_val=\"+document.Form1.TXT_CLIENT_NO.value+\" \";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			
			
			
			out.println("function makeRequest(obj) {");
			out.println("var http_request = false;");
			out.println("if (window.XMLHttpRequest) {");
			out.println("http_request = new XMLHttpRequest();");
			out.println("if (http_request.overrideMimeType) {");
			out.println("     http_request.overrideMimeType('text/xml');");
			out.println("}");
			out.println("} else if (window.ActiveXObject) { ");
			out.println("    try {");
			out.println("        http_request = new ActiveXObject(\"Msxml2.XMLHTTP\");");
			out.println("    } catch (e) {");
			out.println("        try {");
			out.println("            http_request = new ActiveXObject(\"Microsoft.XMLHTTP\");");
			out.println("        } catch (e) {}");
			out.println("    }");
			out.println("}");
			out.println("if (!http_request) {");
			out.println("    alert('Giving up :( Cannot create an XMLHTTP instance');");
			out.println("    return false;");
			out.println("}");
			out.println("url=\"\";");
			out.println("if(obj==\"M1\"){");
			out.println("url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_SCORE_validations?chksql=SCORE_DETAILS_EDIT&pre_stage1='ENTER'&model=\"+document.Form1.TXT_SCORE_MODEL_CODE.value+\"&application=\"+document.Form1.TXT_APPLICATION_NO.value;");
			out.println("}else{");
			out.println("url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_SCORE_validations?chksql=SCORE_DETAILS_ENTER&pre_stage1='ENTER'&model=\"+document.Form1.TXT_SCORE_MODEL_CODE.value;");
			out.println("}");
			out.println("http_request.onreadystatechange = function() {");
			out.println("alertContents(http_request,1,obj); ");
			out.println("};");
			out.println("http_request.open('GET',url, true);");
			out.println("http_request.send(null);");
			out.println("}");
			
			out.println("function alertContents(http_request,count,obj) {");
			out.println(" if (http_request.readyState == 4) {");
			out.println("    if (http_request.status == 200) {");
			out.println("      	if(http_request.responseText!=\"\"){");
			out.println(" 				m_data=http_request.responseText;");
			out.println("					if(obj==\"M1\"){");
			out.println("						score_details.innerHTML=m_data;");
			out.println("					}");
			out.println("					else{");
		//	out.println("						alert(m_data);");
			out.println("						score_details.innerHTML=m_data;");
			out.println("					}");
			out.println("				}");
			out.println("    } else {");
			out.println("        alert('There was a problem with the request.');");
			out.println("    }");

			out.println(" }");
			out.println("}");
			
			out.println("function valdate_values(obj,min,max){");
			out.println("objname=document.Form1.elements[\"SCORE_\"+obj];");
			out.println("if((parseFloat(objname.value)>parseFloat(max)) || (parseFloat(objname.value)<parseFloat(min))){");
			out.println("alert('Credit score should between the Min and Max value for the Score Category');");
			out.println("objname.value=\"0\";");
			out.println("}");
			out.println("obj4=document.Form1.elements[\"NUM_COLS\"];");
			out.println("m_val=0;");
			out.println("for(i=1;i<parseInt(obj4.value);i++){");
			out.println("obj5=document.Form1.elements[\"SCORE_\"+i];");
			out.println("m_val=parseFloat(m_val)+parseFloat(obj5.value);");
			out.println("}");
			out.println("document.Form1.TXT_TOTAL_SCORE_APP.value=format_noobject(m_val);");
			out.println("}");
			
			out.println("function load_default_score(val1){");
			out.println("obj_select=document.Form1.elements[\"RATE_\"+val1];");
			out.println("val=obj_select.value;");
			out.println("obj1=document.Form1.elements[\"RATE_VAL_\"+val];");
			out.println("obj2=document.Form1.elements[\"SCORE_\"+val1];");
			out.println("obj3=document.Form1.elements[\"MAX_\"+val1];");
			out.println("obj2.value=format_noobject((parseFloat(obj3.value)/100)*parseFloat(obj1.value));");
			out.println("obj4=document.Form1.elements[\"NUM_COLS\"];");
			out.println("m_val=0;");
			out.println("for(i=1;i<parseInt(obj4.value);i++){");
			out.println("obj5=document.Form1.elements[\"SCORE_\"+i];");
			out.println("m_val=parseFloat(m_val)+parseFloat(obj5.value);");
			out.println("}");
			out.println("document.Form1.TXT_TOTAL_SCORE_APP.value=format_noobject(m_val);");
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(parseFloat(document.Form1.TXT_TOTAL_SCORE_APP.value)>parseFloat(document.Form1.TXT_TOTAL_SCORE.value)){");
			out.println("DIV_TXT_TOTAL_SCORE_AP.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("if(document.Form1.TXT_CREDIT_EVAL.value==\"\"){  "); 
			out.println("DIV_TXT_USER.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_APPLICATION_NO.value==\"\"){  "); 
			out.println("DIV_TXT_APPLICATION_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_SCORE_MODEL_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_SCORE_MODEL_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 

			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

	

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_Finance_status_Report?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_Finance_status_Report?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CR_display_credit_score_enter\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
	

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Credit Process - Finance Status Report - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Credit Process - Finance Status Report - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_TOTAL_SCORE.disabled=true;"); 
			out.println("}"); 
			out.println("else{");

			out.println("document.Form1.BUT_HELP_MAIN_USER.disabled=false;"); 
			out.println("document.Form1.TXT_CREDIT_EVAL.value='';"); 
			
			
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){"); 
											out.println("clear_data_edit_delete()");

			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save.value=\"Modify\";"); 
			out.println("}");  
			out.println("else if(m_val==\"DELETE\"){"); 
											out.println("clear_data_edit_delete()");
												out.println("clear_data_disable()");

			out.println("document.Form1.hid_status.value=\"Delete\";");  
			out.println("document.Form1.hid_save.value=\"Delete\";"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			/*
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {");			
			out.println("oBj = new MyDialog();");
			out.println("oBj.valout[3]  = \" \";");
			out.println("oBj.valout[4]  = \" \";");
			out.println("oBj.valout[5]  = \" \";");
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	"); 
		
			out.println("if(oBj.valout[0]=='Next')  {");
			out.println("Next(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
			out.println("}");
			out.println("else if  (oBj.valout[0]=='Prev') {");
			out.println("Prev(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
			out.println("}		");
			out.println("else if(oBj.valout[1] == 'Close'){");
			out.println("clear_data()");
			out.println("}");
			out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != null){");
			out.println("if(IfCount=='1'){"); 
			out.println("		help_value_assign_1(oBj);"); 
			out.println("}");
				

			
			out.println("else if(IfCount=='10'){"); 
			out.println("		help_update_value_assign_10(oBj);"); 
	  	out.println("		}"); 
			out.println("else if(IfCount=='11'){"); 
			out.println("		help_update_value_assign_11(oBj);"); 
	  	out.println("		}");
			out.println("else if(IfCount=='12'){"); 
			out.println("		help_update_value_assign_12(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='13'){"); 
			out.println("		help_update_value_assign_13(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='14'){"); 
			out.println("		help_update_value_assign_14(oBj);"); 
			out.println("}");
			out.println("}");
			
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
			
			out.println("}");	*/
			
			
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
			//out.println("window.open('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_CO_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=');");
			out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\"");
			
		//	out.println("window.open(m_url);");
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data();");
			out.println("	}else");
			
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 

			out.println("if(IfCount=='1'){"); 
			out.println("		help_value_assign_1(oBj);"); 
			out.println("}");			
			out.println("else if(IfCount=='10'){"); 
			out.println("		help_update_value_assign_10(oBj);"); 
	  	out.println("		}"); 
			out.println("else if(IfCount=='11'){"); 
			out.println("		help_update_value_assign_11(oBj);"); 
	  	out.println("		}");
			out.println("else if(IfCount=='12'){"); 
			out.println("		help_update_value_assign_12(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='13'){"); 
			out.println("		help_update_value_assign_13(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='14'){"); 
			out.println("		help_update_value_assign_14(oBj);"); 
			out.println("}");

		
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
			out.println("	clear_data();");
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


			out.println(" function Close_2(){");//**
			out.println("clear_data()	");
     // out.println("window.close();");
      out.println(" }");
		
		
					out.println("function clear_data_edit_delete() {");
			out.println("document.Form1.TXT_APPLICATION_NO.value='';");
			out.println("document.Form1.TXT_APPLICATION_NO.focus();");
			//out.println("document.Form1.TXT_CREDIT_EVAL.value='';");
			out.println("    document.Form1.TXT_SCORE_MODEL_CODE.value='';"); 
			out.println("    document.Form1.TXT_TOTAL_SCORE_APP.value='';"); 
			out.println("    document.Form1.TXT_TOTAL_SCORE.value='';"); 
			out.println("    document.Form1.TXT_COMMENTS.value='';"); 
			out.println("score_details.innerHTML=\"\";");
			out.println("}");
			
					out.println("function clear_data_disable() {");
		//	out.println("document.Form1.TXT_APPLICATION_NO.disabeld=true;");
			out.println("document.Form1.TXT_APPLICATION_NO.focus();");
			out.println("document.Form1.TXT_CREDIT_EVAL.disabled=true;");
			out.println("    document.Form1.TXT_SCORE_MODEL_CODE.disabled=true;");
			out.println("    document.Form1.BUT_HELP_UPDATE.disabled=true;");
			out.println("    document.Form1.TXT_TOTAL_SCORE_APP.disabled=true;");
			out.println("    document.Form1.TXT_TOTAL_SCORE.disabled=true;");
			out.println("    document.Form1.TXT_COMMENTS.disabled=true;");
			out.println("}");


			
			out.println("function clear_data() {");
			out.println("if(document.Form1.hid_help_type.value==\"10\"){");
			out.println("document.Form1.TXT_APPLICATION_NO.value='';");
			out.println("}");
			out.println("if(document.Form1.hid_help_type.value==\"11\"){");
			out.println("document.Form1.TXT_INQUARY_NO.value='';");
			out.println("}");
			out.println("if(document.Form1.hid_help_type.value==\"12\"){");
			out.println("document.Form1.TXT_FINANCE_NO.value='';");
			out.println("}");
			out.println("if(document.Form1.hid_help_type.value==\"13\"){");
			out.println("document.Form1.TXT_VIHICLE_NO.value='';");
			out.println("}");
			out.println("if(document.Form1.hid_help_type.value==\"14\"){");
			out.println("document.Form1.TXT_CLIENT_NO.value='';");
			out.println("}");
			out.println("}");
			
			
				out.println("function help_button_8() {"); 
			out.println("    document.Form1.hid_help_type.value=\"8\";"); 
			out.println("    m_sql = \"m_help_TXT_APPLICATION_STATUS_sql\";"); 
			out.println("    Crit = document.Form1.TXT_APPLICATION_STATUS.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_8(oBj) {"); 
			out.println("    document.Form1.TXT_APPLICATION_STATUS.value=oBj.valout[2];"); 
			out.println("}"); 
			
			
			out.println("function help_update_user() {"); 
			out.println("    document.Form1.hid_help_type.value=\"100\";"); 
			out.println("    Sql = \"m_help_TXT_USER_ID_sql\";"); 
			out.println("    Crit = document.Form1.TXT_CREDIT_EVAL.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox(0,10,6,Crit,Sql,100);"); 
			out.println("}"); 
			
		
			

			out.println("function help_update_model() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    Sql = \"m_help_TXT_SCORE_MODEL_CODE_sql\";"); 
			out.println("    Crit = document.Form1.TXT_SCORE_MODEL_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox(0,10,0,Crit,Sql,99);"); 
			out.println("}"); 


						
			out.println("function help_update_value_assign_10(oBj) {"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_11(oBj) {"); 
			out.println("    document.Form1.TXT_INQUARY_NO.value=oBj.valout[2];"); 
			out.println("}"); 
			
			
			out.println("function help_update_value_assign_12(oBj) {"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_13(oBj) {"); 
			out.println("    document.Form1.TXT_VIHICLE_NO.value=oBj.valout[2];");
			out.println("chk_detail();");
			out.println("}");  
			
			out.println("function help_update_value_assign_14(oBj) {"); 
			out.println("    document.Form1.TXT_CLIENT_NO.value=oBj.valout[2];");
			out.println("chk_detail();");
			out.println("}"); 
						
				
				
				
				
				
				
							
			out.println("function help_update_inquiry() {");
			out.println("    document.Form1.hid_help_type.value=\"11\";"); 
			out.println("    Sql = \"m_help_fin_staus_inquary_sql\";"); 
			out.println("    Crit = document.Form1.TXT_INQUARY_NO.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\";"); 
			out.println("    HelpBox('1','10','0',Crit,Sql,'11');"); 
			out.println("}");
			
			
			out.println("function help_update_application() {");
			out.println("    document.Form1.hid_help_type.value=\"10\";"); 
			out.println("if((document.Form1.TXT_FINANCE_NO.value!=\"\")||(document.Form1.TXT_VIHICLE_NO.value!=\"\")||(document.Form1.TXT_CLIENT_NO.value!=\"\")){");			
			out.println("    Sql = \"m_help_fin_staus_application_sql2\";"); 
			out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\"+document.Form1.TXT_INQUARY_NO.value+\"@\"+document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_CLIENT_NO.value+\"@\"+document.Form1.TXT_VIHICLE_NO.value+\"@\";");
			out.println("}else");
			out.println("{");	
			out.println("    Sql = \"m_help_fin_staus_application_sql\";"); 
			out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\"+document.Form1.TXT_INQUARY_NO.value+\"@\";");
			out.println("} ");
			//out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\";"); 
			out.println("    HelpBox(0,10,0,Crit,Sql,10);"); 
			out.println("}");
			
			
			out.println("function help_update_finance() {");
			out.println("    document.Form1.hid_help_type.value=\"12\";"); 
			out.println("    Sql = \"m_help_fin_staus_finance_sql\";"); 
			out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\"+document.Form1.TXT_INQUARY_NO.value+\"@\"+document.Form1.TXT_CLIENT_NO.value+\"@\"+document.Form1.TXT_VIHICLE_NO.value+\"@\";"); 
			out.println("    HelpBox(0,10,0,Crit,Sql,12);"); 
			out.println("}");

			out.println("function help_update_vihicle() {");
			out.println("    document.Form1.hid_help_type.value=\"13\";"); 
			out.println("    Sql = \"m_help_fin_staus_vihicle_sql\";"); 
			out.println("    Crit = document.Form1.TXT_VIHICLE_NO.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\"+document.Form1.TXT_INQUARY_NO.value+\"@\"+document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_CLIENT_NO.value+\"@\";");
			out.println("    HelpBox(0,10,0,Crit,Sql,13);"); 
			out.println("}"); 
			
			
			out.println("function help_update_client() {");
			out.println("    document.Form1.hid_help_type.value=\"14\";"); 
			out.println("    Sql = \"m_help_fin_staus_client_sql\";"); 
			out.println("    Crit = document.Form1.TXT_CLIENT_NO.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\"+document.Form1.TXT_INQUARY_NO.value+\"@\"+document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_VIHICLE_NO.value+\"@\";"); 
		//	out.println("    Crit = document.Form1.TXT_VIHICLE_NO.value+\"@\";"); 
			out.println("    HelpBox(0,10,0,Crit,Sql,14);"); 
			out.println("}"); 
				
			out.println("function chk_detail(){");
			out.println("if(((document.Form1.TXT_CLIENT_NO.value!=\"\")||(document.Form1.TXT_VIHICLE_NO.value!=\"\"))&&(document.Form1.TXT_FINANCE_NO.value==\"\")){  "); 
			out.println("alert('Please select the Finance No');");
			out.println("help_update_finance();");
			out.println("}");
			out.println("}");
			
			out.println("function report_window(obj1,obj2,obj3,obj4,obj5)	{");
			//out.println("alert(obj1+obj2+obj3+obj4+obj5);");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_Finance_status_Report?chksql=detail&INQ_NO=\"+obj1+\"&APP_NO=\"+obj2+\"&FIN_NO=\"+obj3+\"&VIH_NO=\"+obj4+\"&CLN_NO=\"+obj5+\" \";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=600,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=0,resizable=1');");
			out.println("}");
				
				
				
			
        out.println("</Script>");
				
				
				out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
				out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >");
				out.println("<tr>");
				
				out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
				out.println("<td class=\"border_wht\" valign=\"top\"> ");
				out.println("<table class=table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
				out.println("<tr> ");
				out.println("<td height=\"30\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
				out.println("</tr>");
				out.println("<tr> ");
				out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td style=\"height: 327px\">");
				
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
				out.println("<tr>");
				out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Credit Process - Finance Status Report </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				
						out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
				
				
				out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");

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
				
				
				
				
				
				
				
			out.println("<table align='center' width='100%' class='table' border='0'>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_INQUARY_NO'  class=div_input>Inquiry No </DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_INQUARY_NO' maxlength='15' size='10' onblur=\"check_inquary(document.Form1.TXT_INQUARY_NO)\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_INQUARY_NO' value=\"Help\" onClick=\"help_update_inquiry()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application No </DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='10' onblur=\"check_application(document.Form1.TXT_APPLICATION_NO)\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_APPLICATION_NO' value=\"Help\" onClick=\"help_update_application()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No </DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='10' onblur=\"check_finance(document.Form1.TXT_FINANCE_NO)\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_FINANCE_NO' value=\"Help\" onClick=\"help_update_finance()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_VIHICLE_NO'  class=div_input>Vehicle No </DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_VIHICLE_NO' maxlength='15' size='10' onblur=\"check_vihicle(document.Form1.TXT_VIHICLE_NO)\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_vihicle()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_NO'  class=div_input>Client No </DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_CLIENT_NO' maxlength='15' size='10' onblur=\"check_client(document.Form1.TXT_CLIENT_NO)\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_client()\"></td>");
		  out.println("<td width='10%'><input type=\"button\" class='mainbut' onclick='report_window(document.Form1.TXT_INQUARY_NO.value, document.Form1.TXT_APPLICATION_NO.value, document.Form1.TXT_FINANCE_NO.value, document.Form1.TXT_VIHICLE_NO.value ,document.Form1.TXT_CLIENT_NO.value)' value=\"View\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("</tr>");
			out.println("</table>");
				
				
			
		/*	out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr><td width='30%' align='center'></td>");  
			out.println("<td width='40%' align='center'><input type=\"button\" class='mainbut' onclick='report_window(document.Form1.TXT_INQUARY_NO.value, document.Form1.TXT_APPLICATION_NO.value, document.Form1.TXT_FINANCE_NO.value, document.Form1.TXT_VIHICLE_NO.value ,document.Form1.TXT_CLIENT_NO.value)' value=\"Details\"></td>");  
			out.println("<td width='*%'></td>");  
			out.println("</tr></table>"); */
				
				
				
				
				
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</html>");
      }
			
			//=========================================================================================================================			
			
			else if(m_chksql.trim().equals("detail")){
			
			 String m_inq_no = req.getParameter("INQ_NO"); 
			 String m_app_no = req.getParameter("APP_NO");
			 String m_fin_no = req.getParameter("FIN_NO");
			 String m_vih_no = req.getParameter("VIH_NO");
			 String m_cln_no = req.getParameter("CLN_NO");
			 String m_status = "";	
				
			//out.println(m_inq_no+m_app_no+m_fin_no+m_vih_no+m_cln_no);
			
			 out.println("<html>");
			 out.println("<head>");
			 out.println("<title>Asset Financing System</title>    ");
			 out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			 out.println("</head>");
			 out.println("<Script>");
			
			
			
			 out.println("</Script>");
			 out.println("<HTML><HEAD><TITLE>Allocated Inquiry Details Report </TITLE></HEAD>");
			 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			 out.println("<FORM NAME='Form1' method='post'>"); 

			 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
			 out.println("<TR><TD align='Center' ><B> Finance Status Details Report </B></TD></TR>");
			 out.println("</TABLE>");
			 out.println("<br>");
				
				
					if((!m_inq_no.equals(""))&&(m_app_no.equals(""))&&(m_fin_no.equals(""))&&(m_vih_no.equals(""))){
				
				rs = stmt.executeQuery ("SELECT INQUIRY_CODE,NVL(LEAD_SOURCE_CATEGORY,'-'),NVL(LEAD_SOURCE_NAME,'-'), "+
				" NVL(ID_NO,'-'),DECODE(CLIENT_CATEGORY,'SUSPECT','Suspect','PROBABLE','Probable','DEFINETE','Definete'),CLIENT_NAME,NVL(ADDRESS,'-'),NVL(ADDRESS2,'-'),NVL(CITY_CODE,'-'),NVL(CONTACT_PERSON,'-'),NVL(TEL_NO,'-'), "+
				" DECODE(INQUIRY_STATUS,'QOT-APP','Quotation approval','QOT-SENT','Quotation Process','INQUIRY','Inquiry Level') "+
				" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
				" WHERE INQUIRY_CODE='"+m_inq_no+"' ");
								
			boolean more = rs.next();
			//Modified by Mahela on 07-05-2007
			
			if (more) {			
			out.println("<blockquote><font size=2><p style='text-align:left'>");				
			out.println("<table border='0' width='90%' class='table'>"); 	
			//Added by Mahela on 07-05-2007
			out.println("<tr><td width='30%' class='rep-body' ><b>Inquiry No</td><td width='*%' class='rep-body' onClick=\"show_inquiry_drill('"+m_inq_no+"')\" style='cursor:hand' >&nbsp;&nbsp;: <u>"+m_inq_no+"</u></td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Application No</td><td width='*%' class='rep-body' onClick=\"show_application_detail_drill('"+m_app_no+"')\" style='cursor:hand' >&nbsp;&nbsp;: <u>"+m_app_no+"</u></td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Finance No</td><td width='*%' class='rep-body' onClick=\"show_finance_detail_drill('"+m_fin_no+"')\" style='cursor:hand' >&nbsp;&nbsp;: <u>"+m_fin_no+"</u></td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Vehicle No</td><td width='*%' class='rep-body' onClick=\"\" style='cursor:hand' >&nbsp;&nbsp;: <u>"+m_vih_no+"</u></td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Client No</td><td width='*%' class='rep-body' onClick=\"show_client('"+m_cln_no+"')\" style='cursor:hand' ><u>&nbsp;&nbsp;: "+m_cln_no+"</u></td></tr>");
			
			out.println("<tr><td width='30%' class='rep-body' ><b>Lead Source Category</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(2)+"</td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Lead Source Name</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(3)+"</td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Client Category</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(5)+"</td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Client Nic/Bussiness Reg. No</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(4)+"</td></tr>");
	  	out.println("<tr><td width='30%' class='rep-body' ><b>Name</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(6)+"</td></tr>");
		  out.println("<tr><td width='30%' class='rep-body' ><b>Address</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(7)+"</td></tr>");
			if(rs.getString(8).equals("-")){
			}else{
			out.println("<tr><td width='30%' class='rep-body' ><b></td><td width='*%' class='rep-body'>&nbsp;&nbsp;&nbsp; "+rs.getString(8)+"</td></tr>");
			}
			if(rs.getString(9).equals("-")){
			}else{
			out.println("<tr><td width='30%' class='rep-body' ><b></td><td width='*%' class='rep-body'>&nbsp;&nbsp;&nbsp; "+rs.getString(9)+"</td></tr>");
			}
			out.println("<tr><td width='30%' class='rep-body' ><b>Contact person</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(10)+"</td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Contact Number</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(11)+"</td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Staus</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(12)+"</td></tr>");		
		  out.println("</TABLE>");
					
			out.println("</font></p></blockquote>");
       }
			}else
			{
			
			if(!m_app_no.equals("")){
			  m_app_no=m_app_no;
			}else
			if(!m_fin_no.equals("")){
			
			rs = stmt.executeQuery ("SELECT DISTINCT B.APPLICATION_NO,B.APPLICATION_STATUS "+
			" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+ 
      " WHERE A.INQUIRY_CODE=B.INQUARY_NO AND "+                  
      " B.FINANCE_NO='"+m_fin_no+"' ");
			
			boolean more = rs.next();
							
			if (more) {
			m_app_no=rs.getString(1);
			}
			
			}
			
			
			
			}
			
			
		
			//out.println(m_app_no);			
			
			rs1 = stmt.executeQuery("SELECT B.APPLICATION_STATUS,NVL(A.LEAD_SOURCE_CATEGORY,'-'),NVL(A.LEAD_SOURCE_NAME,'-'), "+
				   " NVL(A.ID_NO,'-'), DECODE(A.CLIENT_CATEGORY,'SUSPECT','Suspect','PROBABLE','Probable','DEFINETE','Definete'), A.CLIENT_NAME,NVL(A.ADDRESS,'-'),NVL(A.ADDRESS2,'-'),NVL(A.CITY_CODE,'-'),NVL(A.CONTACT_PERSON,'-'),NVL(A.TEL_NO,'-') "+
           " FROM "+m_schema_name+".AF_MK_PRO_INQUIRY A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
           " WHERE A.INQUIRY_CODE=B.INQUARY_NO AND "+
					 " B.APPLICATION_NO='"+m_app_no+"' ");
						
			boolean more1 = rs1.next();
		
				
					if (more1) {			
			out.println("<blockquote><font size=2><p style='text-align:left'>");				
			out.println("<table border='0' width='90%' class='table'>"); 	
			//Added by Mahela on 07-05-2007
			out.println("<tr><td width='30%' class='rep-body' ><b>Inquiry No</td><td width='*%' class='rep-body' onClick=\"show_inquiry_drill('"+m_inq_no+"')\" style='cursor:hand' >&nbsp;&nbsp;: <u>"+m_inq_no+"</u></td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Application No</td><td width='*%' class='rep-body' onClick=\"show_application_detail_drill('"+m_app_no+"')\" style='cursor:hand' >&nbsp;&nbsp;: <u>"+m_app_no+"</u></td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Finance No</td><td width='*%' class='rep-body' onClick=\"show_finance_detail_drill('"+m_fin_no+"')\" style='cursor:hand' >&nbsp;&nbsp;: <u>"+m_fin_no+"</u></td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Vehicle No</td><td width='*%' class='rep-body' onClick=\"\" style='cursor:hand' >&nbsp;&nbsp;: <u>"+m_vih_no+"</u></td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Client No</td><td width='*%' class='rep-body' onClick=\"show_client('"+m_cln_no+"')\" style='cursor:hand' >&nbsp;&nbsp;: <u>"+m_cln_no+"</u></td></tr>");
			
			out.println("<tr><td width='30%' class='rep-body' ><b>Lead Source Category</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs1.getString(2)+"</td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Lead Source Name</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs1.getString(3)+"</td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Client category</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs1.getString(5)+"</td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Client Nic/Business Reg. No</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs1.getString(4)+"</td></tr>");
	  	out.println("<tr><td width='30%' class='rep-body' ><b>Name</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs1.getString(6)+"</td></tr>");
		  out.println("<tr><td width='30%' class='rep-body' ><b>Address</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs1.getString(7)+"</td></tr>");
			if(rs1.getString(8).equals("-")){
			}else{
			out.println("<tr><td width='30%' class='rep-body' ><b></td><td width='*%' class='rep-body'>&nbsp;&nbsp;&nbsp; "+rs1.getString(8)+"</td></tr>");
			}
			if(rs1.getString(9).equals("-")){
			}else{
			out.println("<tr><td width='30%' class='rep-body' ><b></td><td width='*%' class='rep-body'>&nbsp;&nbsp;&nbsp; "+rs1.getString(9)+"</td></tr>");
			}
			out.println("<tr><td width='30%' class='rep-body' ><b>Contact person</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs1.getString(10)+"</td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Contact Number</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs1.getString(11)+"</td></tr>");
		//	out.println("<tr><td width='30%' class='rep-body' ><b>Staus</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs1.getString(1)+"</td></tr>");
			
			if(rs1.getString(1).equals("ACTIVATED")){
			m_status="Activated";			
			}else
			if(rs1.getString(1).equals("VERIFYL")){
			m_status="Enter leasing stage";			
			}else
			if(rs1.getString(1).equals("REPOSSESS")){
			m_status="Reprossesion stage";			
			}else
			if(rs1.getString(1).equals("ENT_CON")){
			m_status="To Be Verify";			
			}else
			if(rs1.getString(1).equals("CANCEL")){
			m_status="Canceled";			
			}			
			
			out.println("<tr><td width='30%' class='rep-body' ><b>Staus</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+m_status+"</td></tr>");
			
			if((rs1.getString(1).equals("ACTIVATED"))||(rs1.getString(1).equals("REPOSSESS"))){
			rs2 = stmt.executeQuery("SELECT "+
			      " A.PRICING_NO, "+
						" DECODE(A.TRANSACION_TYPE,'FINLEASE','Finance Lease','OPELEASE','Operating Lease','HIREPURCH','Hire Purchase','LOANS','Loans','HIRING','Hiring'), "+
						" A.PERIOD, "+
						" DECODE(A.PAYMENT_MODE,'ARREARS','Arrears','ADVANCE','Advance'), "+
						" DECODE(A.PAYMENT_INTERVAL,'1','Anualy','3','Quatly','6','Semi Anualy','12','Monthly'), "+
						" A.RATE, "+
						" A.GROSS_AMOUNT, "+
						" A.NET_AMOUNT, "+
						" A.AMI "+
						" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING A ,"+m_schema_name+".AF_MK_PRO_PRICING B "+
						" WHERE A.PRICING_NO=B.PRICING_NO AND "+
						" A.APPLICATION_NO='"+m_app_no+"' ");

				boolean more2 = rs2.next();
		
				
					if (more2) {
			out.println("<tr><td width='30%' class='rep-body' ><b>Transaction Type</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs2.getString(2)+"</td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Period</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+nf.format(rs2.getDouble(3))+"</td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Payment Type</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs2.getString(4)+"</td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Payment Interval</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs2.getString(5)+"</td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Rate</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+nf.format(rs2.getDouble(6))+"</td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Amount</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+nf.format(rs2.getDouble(7))+"</td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>AMI</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+nf.format(rs2.getDouble(9))+"</td></tr>");
			
			}
			}
			
		  out.println("</TABLE>");
					
			out.println("</font></p></blockquote>");
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</BODY></HTML>");
       }
				
				
				
				
				
				
				
				
				
				
				
				
				
	
			}
			
			
			else if(m_chksql.trim().equals("detail2")){
			
			 String m_inq_no = req.getParameter("INQ_NO"); 
			 String m_app_no = req.getParameter("APP_NO");
			 String m_fin_no = req.getParameter("FIN_NO");
			 String m_vih_no = req.getParameter("VIH_NO");
			out.println(m_inq_no+m_app_no+m_fin_no+m_vih_no);
			
			
			
			 out.println("<html>");
			 out.println("<head>");
			 out.println("<title>Asset Financing System</title>    ");
			 out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			 out.println("</head>");
			 out.println("<Script>");
			
			
			
			 out.println("</Script>");
			 out.println("<HTML><HEAD><TITLE>Allocated Inquiry Details Report </TITLE></HEAD>");
			 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			 out.println("<FORM NAME='Form1' method='post'>"); 

			 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
			 out.println("<TR><TD align='Center' ><B> Finance Status Details Report </B></TD></TR>");
			 out.println("</TABLE>");
			 out.println("<br>");
				
			if((!m_inq_no.equals(""))&&(m_app_no.equals(""))&&(m_fin_no.equals(""))&&(m_vih_no.equals(""))){
				
				rs = stmt.executeQuery ("SELECT INQUIRY_CODE,NVL(LEAD_SOURCE_CATEGORY,'-'),NVL(LEAD_SOURCE_NAME,'-'), "+
				" ID_NO,CLIENT_CATEGORY,CLIENT_NAME,NVL(ADDRESS,'-'),NVL(ADDRESS2,'-'),NVL(CITY_CODE,'-'),NVL(CONTACT_PERSON,'-'),NVL(TEL_NO,'-'), "+
				" DECODE(INQUIRY_STATUS,'QOT-APP','Quotation approval','QOT-SENT','Quotation Process') "+
				" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
				" WHERE INQUIRY_CODE='"+m_inq_no+"' ");
								
			boolean more = rs.next();
							
			if (more) {			
			out.println("<blockquote><font size=2><p style='text-align:left'>");				
			out.println("<table border='0' width='90%' class='table'>"); 	
			//Added by Mahela on 07-05-2007
			out.println("<tr><td width='30%' class='rep-body' ><b>Inquiry No</td><td width='*%' class='rep-body' onClick=\"show_inquiry_drill('"+m_inq_no+"')\" style='cursor:hand' >&nbsp;&nbsp;: <u>"+m_inq_no+"</u></td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Application No</td><td width='*%' class='rep-body' onClick=\"show_application_detail_drill('"+m_app_no+"')\" style='cursor:hand' >&nbsp;&nbsp;: <u>"+m_app_no+"</u></td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Finance No</td><td width='*%' class='rep-body' onClick=\"show_finance_detail_drill('"+m_fin_no+"')\" style='cursor:hand' >&nbsp;&nbsp;: <u>"+m_fin_no+"</u></td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Vehicle No</td><td width='*%' class='rep-body' onClick=\"\" style='cursor:hand' >&nbsp;&nbsp;: <u>"+m_vih_no+"</u></td></tr>");
			//out.println("<tr><td width='30%' class='rep-body' ><b>Client No</td><td width='*%' class='rep-body' onClick=\"show_client('"+m_cln_no+"')\" style='cursor:hand' ><u>&nbsp;&nbsp;: "+m_cln_no+"</u></td></tr>");
			
			out.println("<tr><td width='25%' class='rep-body' ><b>Lead Source Category</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(2)+"</td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><b>Lead Source Name</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(3)+"</td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><b>Client type</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(5)+"</td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><b>Client Nic/Business Reg. No</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(4)+"</td></tr>");
	  	out.println("<tr><td width='25%' class='rep-body' ><b>Name</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(6)+"</td></tr>");
		  out.println("<tr><td width='25%' class='rep-body' ><b>Address</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(7)+"</td></tr>");
			if(rs.getString(8).equals("-")){
			}else{
			out.println("<tr><td width='25%' class='rep-body' ><b></td><td width='*%' class='rep-body'>&nbsp;&nbsp;&nbsp; "+rs.getString(8)+"</td></tr>");
			}
			if(rs.getString(9).equals("-")){
			}else{
			out.println("<tr><td width='25%' class='rep-body' ><b></td><td width='*%' class='rep-body'>&nbsp;&nbsp;&nbsp; "+rs.getString(9)+"</td></tr>");
			}
			out.println("<tr><td width='25%' class='rep-body' ><b>Contact person</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(10)+"</td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><b>Contact Number</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(11)+"</td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><b>Staus</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(12)+"</td></tr>");		
		  out.println("</TABLE>");
					
			out.println("</font></p></blockquote>");
       }
			}//else
			//{
			//if((!m_inq_no.equals(""))&&(!m_app_no.equals(""))&&(m_fin_no.equals(""))&&(m_vih_no.equals(""))){
			
			if((!m_inq_no.equals(""))||(!m_app_no.equals(""))||(!m_fin_no.equals(""))||(m_vih_no.equals(""))){
			
		/*	rs = stmt.executeQuery ("SELECT B.APPLICATION_STATUS,NVL(A.LEAD_SOURCE_CATEGORY,'-'),NVL(A.LEAD_SOURCE_NAME,'-'), "+
				   " A.ID_NO,A.CLIENT_CATEGORY,A.CLIENT_NAME,NVL(A.ADDRESS,'-'),NVL(A.ADDRESS2,'-'),NVL(A.CITY_CODE,'-'),NVL(A.CONTACT_PERSON,'-'),NVL(A.TEL_NO,'-'), "+
           " D.FULL_NAME,DECODE(B.APPLICATION_STATUS,'ENTERED','To Be Completed','ENT_CON','To be verify','V-APP','Credit score evaluation stage') "+
				   " FROM "+m_schema_name+".AF_MK_PRO_INQUIRY A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
           " "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR C, "+m_schema_name+".AF_CO_MAS_CLIENT D "+
					 " WHERE A.INQUIRY_CODE=B.INQUARY_NO AND "+
					 " B.APPLICATION_NO=C.APPLICATION_NO AND "+ 
					 " C.GUARANTOR_CODE=D.CLIENT_CODE AND "+
					 " B.APPLICATION_NO='"+m_app_no+"' AND "+
					 " A.INQUIRY_CODE='"+m_inq_no+"' ");*/
						
						
						
			rs = stmt.executeQuery ("SELECT B.APPLICATION_STATUS,NVL(A.LEAD_SOURCE_CATEGORY,'-'),NVL(A.LEAD_SOURCE_NAME,'-'), "+
				   " A.ID_NO,A.CLIENT_CATEGORY,A.CLIENT_NAME,NVL(A.ADDRESS,'-'),NVL(A.ADDRESS2,'-'),NVL(A.CITY_CODE,'-'),NVL(A.CONTACT_PERSON,'-'),NVL(A.TEL_NO,'-'), "+
           " D.FULL_NAME,DECODE(B.APPLICATION_STATUS,'ENTERED','To Be Completed','ENT_CON','To be verify','V-APP','Credit score evaluation stage') "+
				   " FROM "+m_schema_name+".AF_MK_PRO_INQUIRY A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
           " "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR C, "+m_schema_name+".AF_CO_MAS_CLIENT D "+
					 " WHERE A.INQUIRY_CODE=B.INQUARY_NO AND "+
					 " B.APPLICATION_NO=C.APPLICATION_NO AND  "+
					 " C.GUARANTOR_CODE=D.CLIENT_CODE AND "+
					 " ( B.APPLICATION_NO='"+m_app_no+"' OR A.INQUIRY_CODE='"+m_inq_no+"' OR B.FINANCE_NO='"+m_fin_no+"') ");
						
						
						
						
						
						
		boolean more = rs.next();
							
			if (more) {					
						
			out.println("<blockquote><font size=2><p style='text-align:left'>");				
			out.println("<table border='0' width='90%' class='table'>"); 	
			//Added by Mahela on 07-05-2007
			out.println("<tr><td width='30%' class='rep-body' ><b>Inquiry No</td><td width='*%' class='rep-body' onClick=\"show_inquiry_drill('"+m_inq_no+"')\" style='cursor:hand' >&nbsp;&nbsp;: <u>"+m_inq_no+"</u></td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Application No</td><td width='*%' class='rep-body' onClick=\"show_application_detail_drill('"+m_app_no+"')\" style='cursor:hand' >&nbsp;&nbsp;: <u>"+m_app_no+"</u></td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Finance No</td><td width='*%' class='rep-body' onClick=\"show_finance_detail_drill('"+m_fin_no+"')\" style='cursor:hand' >&nbsp;&nbsp;: <u>"+m_fin_no+"</u></td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Vehicle No</td><td width='*%' class='rep-body' onClick=\"\" style='cursor:hand' >&nbsp;&nbsp;: <u>"+m_vih_no+"</u></td></tr>");
			//out.println("<tr><td width='30%' class='rep-body' ><b>Client No</td><td width='*%' class='rep-body' onClick=\"show_client('"+m_cln_no+"')\" style='cursor:hand' ><u>&nbsp;&nbsp;: "+m_cln_no+"</u></td></tr>");
			
			out.println("<tr><td width='25%' class='rep-body' ><b>Lead Source Category</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(2)+"</td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><b>Lead Source Name</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(3)+"</td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><b>Client type</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(5)+"</td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><b>Client Nic/Business Reg. No</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(4)+"</td></tr>");
	  	out.println("<tr><td width='25%' class='rep-body' ><b>Name</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(6)+"</td></tr>");
		  out.println("<tr><td width='25%' class='rep-body' ><b>Address</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(7)+"</td></tr>");
			if(rs.getString(8).equals("-")){
			}else{
			out.println("<tr><td width='25%' class='rep-body' ><b></td><td width='*%' class='rep-body'>&nbsp;&nbsp;&nbsp; "+rs.getString(8)+"</td></tr>");
			}
			if(rs.getString(9).equals("-")){
			}else{
			out.println("<tr><td width='25%' class='rep-body' ><b></td><td width='*%' class='rep-body'>&nbsp;&nbsp;&nbsp; "+rs.getString(9)+"</td></tr>");
			}
			out.println("<tr><td width='25%' class='rep-body' ><b>Contact person</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(10)+"</td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><b>Contact Number</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(11)+"</td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><b>Guarantor Name</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(12)+"</td></tr>");
			if(rs.getString(1).equals("ENTERED")){
			out.println("<tr><td width='25%' class='rep-body' ><b>Staus</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(13)+"</td></tr>");	
			}else{
			if((rs.getString(1).equals("ENT_CON"))||(rs.getString(1).equals("V-APP"))){
			out.println("<tr><td width='25%' class='rep-body' ><b>Staus</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(13)+"</td></tr>");
			
			 rs1 = stmt.executeQuery (" SELECT "+
				                       " DISTINCT A.PRICING_NO, "+
															 " B.GRENTAL_AMOUNT, "+
															 " A.PERIOD,A.RATE, "+
															 " B.AMI_AMOUNT "+
																"FROM "+m_schema_name+".AF_MK_PRO_PRICING A,"+m_schema_name+".AF_MK_PRO_PRICING_INSTALLMENT B "+
																"WHERE A.PRICING_NO=B.PRICING_NO AND "+
																" APP_NO='"+m_app_no+"' ");
				boolean more1 = rs1.next();
							
			if (more1) {																
			out.println("<tr><td width='25%' class='rep-body' ><b>Retal</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+nf.format(rs1.getDouble(2))+"</td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><b>Period</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+nf.format(rs1.getDouble(3))+"</td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><b>Rate</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+nf.format(rs1.getDouble(4))+"</td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><b>AMI</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+nf.format(rs1.getDouble(5))+"</td></tr>");
			
			}
			
			}
			
			
			
			
			out.println("<tr><td width='25%' class='rep-body' ><b>Staus</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(1)+"</td></tr>");
			}
				
		  out.println("</TABLE>");
					
			out.println("</font></p></blockquote>");
					
					
					
					
						
				}		
			
			
			} //else{
			
			if(!m_vih_no.equals("")){
			
			rs = stmt.executeQuery ("SELECT distinct B.APPLICATION_NO,B.APPLICATION_STATUS,NVL(A.LEAD_SOURCE_CATEGORY,'-'),NVL(A.LEAD_SOURCE_NAME,'-'), "+
				                      " A.ID_NO,A.CLIENT_CATEGORY,A.CLIENT_NAME,NVL(A.ADDRESS,'-'),NVL(A.ADDRESS2,'-'),NVL(A.CITY_CODE,'-'),NVL(A.CONTACT_PERSON,'-'),NVL(A.TEL_NO,'-'), "+
                              " DECODE(B.APPLICATION_STATUS,'ENTERED','To Be Completed','ENT_CON','To be verify','V-APP','Credit score evaluation stage') "+
				                      " FROM "+m_schema_name+".AF_MK_PRO_INQUIRY A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
                              " "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR C, "+m_schema_name+".AF_CO_MAS_CLIENT D, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS E "+ 
					                    " WHERE A.INQUIRY_CODE=B.INQUARY_NO AND "+
					                    " B.APPLICATION_NO=E.APPLICATION_NO AND "+ 
                              " E.REG_NO='"+m_vih_no+"' ");
															
		boolean more = rs.next();
							
			if (more) {	
			
			String m_stat_chk = rs.getString(2);
			String m_status = rs.getString(13);
			
			out.println("<blockquote><font size=2><p style='text-align:left'>");				
			out.println("<table border='0' width='90%' class='table'>"); 	
			//Added by Mahela on 07-05-2007
			out.println("<tr><td width='30%' class='rep-body' ><b>Inquiry No</td><td width='*%' class='rep-body' onClick=\"show_inquiry_drill('"+m_inq_no+"')\" style='cursor:hand' >&nbsp;&nbsp;: <u>"+m_inq_no+"</u></td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Application No</td><td width='*%' class='rep-body' onClick=\"show_application_detail_drill('"+m_app_no+"')\" style='cursor:hand' >&nbsp;&nbsp;: <u>"+m_app_no+"</u></td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Finance No</td><td width='*%' class='rep-body' onClick=\"show_finance_detail_drill('"+m_fin_no+"')\" style='cursor:hand' >&nbsp;&nbsp;: <u>"+m_fin_no+"</u></td></tr>");
			out.println("<tr><td width='30%' class='rep-body' ><b>Vehicle No</td><td width='*%' class='rep-body' onClick=\"\" style='cursor:hand' >&nbsp;&nbsp;: <u>"+m_vih_no+"</u></td></tr>");
			//out.println("<tr><td width='30%' class='rep-body' ><b>Client No</td><td width='*%' class='rep-body' onClick=\"show_client('"+m_cln_no+"')\" style='cursor:hand' ><u>&nbsp;&nbsp;: "+m_cln_no+"</u></td></tr>");
			
			out.println("<tr><td width='25%' class='rep-body' ><b>Lead Source Category</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(3)+"</td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><b>Lead Source Name</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(4)+"</td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><b>Client type</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(6)+"</td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><b>Client Nic/Business Reg. No</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(5)+"</td></tr>");
	  	out.println("<tr><td width='25%' class='rep-body' ><b>Name</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(7)+"</td></tr>");
		  out.println("<tr><td width='25%' class='rep-body' ><b>Address</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(8)+"</td></tr>");
			if(rs.getString(8).equals("-")){
			}else{
			out.println("<tr><td width='25%' class='rep-body' ><b></td><td width='*%' class='rep-body'>&nbsp;&nbsp;&nbsp; "+rs.getString(9)+"</td></tr>");
			}
			if(rs.getString(9).equals("-")){
			}else{
			out.println("<tr><td width='25%' class='rep-body' ><b></td><td width='*%' class='rep-body'>&nbsp;&nbsp;&nbsp; "+rs.getString(10)+"</td></tr>");
			}
			out.println("<tr><td width='25%' class='rep-body' ><b>Contact person</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(11)+"</td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><b>Contact Number</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(12)+"</td></tr>");
			
			
			
			rs2 = stmt.executeQuery ("SELECT DISTINCT B.APPLICATION_NO,	"+
			                        " D.FULL_NAME "+
															" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
															" "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR C, "+m_schema_name+".AF_CO_MAS_CLIENT D, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS E "+
															" WHERE A.INQUIRY_CODE=B.INQUARY_NO AND "+
															" B.APPLICATION_NO=C.APPLICATION_NO AND "+
															" C.GUARANTOR_CODE=D.CLIENT_CODE AND "+
															" B.APPLICATION_NO=E.APPLICATION_NO AND "+
															" E.REG_NO='"+m_vih_no+"' ");
															
					boolean more2 = rs2.next();		
					
					while (more2) {
					out.println("<tr><td width='25%' class='rep-body' ><b>Guarantor Name</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(2)+"</td></tr>");										
			    more2 = rs2.next();
			    }
			
			
			
			
			//out.println("<tr><td width='25%' class='rep-body' ><b>Gaurantor Name</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(12)+"</td></tr>");
			if(m_stat_chk.equals("ENTERED")){
			out.println("<tr><td width='25%' class='rep-body' ><b>Staus</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+m_status+"</td></tr>");	
			}else{
			if((m_stat_chk.equals("ENT_CON"))||(m_stat_chk.equals("V-APP"))){
			out.println("<tr><td width='25%' class='rep-body' ><b>Staus</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+m_status+"</td></tr>");
			
			 rs1 = stmt.executeQuery (" SELECT "+
				                       " DISTINCT A.PRICING_NO, "+
															 " B.GRENTAL_AMOUNT, "+
															 " A.PERIOD,A.RATE, "+
															 " B.AMI_AMOUNT "+
																"FROM "+m_schema_name+".AF_MK_PRO_PRICING A,"+m_schema_name+".AF_MK_PRO_PRICING_INSTALLMENT B "+
																"WHERE A.PRICING_NO=B.PRICING_NO AND "+
																" APP_NO='"+m_app_no+"' ");
				boolean more1 = rs1.next();
							
			if (more1) {																
			out.println("<tr><td width='25%' class='rep-body' ><b>Retal</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+nf.format(rs1.getDouble(2))+"</td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><b>Period</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+nf.format(rs1.getDouble(3))+"</td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><b>Rate</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+nf.format(rs1.getDouble(4))+"</td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><b>AMI</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+nf.format(rs1.getDouble(5))+"</td></tr>");
			
			}
			
			}
			
			
			
			
			out.println("<tr><td width='25%' class='rep-body' ><b>Staus</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+rs.getString(1)+"</td></tr>");
			}
				
		  out.println("</TABLE>");
					
			out.println("</font></p></blockquote>");
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
		  out.println("</BODY></HTML>");		
					
					
					
						
								
				}											
															
															
			
			
			
			}
			//}
						
			
			//}
			
			
			
			}
  		/*else {
				out.println("Undefined");
			}
			*/
      //out.close();
			//conn.close();
			//this.destroy();
			
			
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
      //return null;
		}finally{
		  if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	    if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}
