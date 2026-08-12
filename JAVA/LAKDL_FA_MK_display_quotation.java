// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
    
    
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_FA_MK_display_quotation extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	java.text.NumberFormat nf;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			String m_schema_name=m_sn_methods.schema_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			double credit_limit=0;
			double credit_period=0;
			double credit_tol_period=0;
			double reserve_margin=0;
			double int_rate=0;
			
			Connection conn;
			Statement stmt;
			ResultSet rs;
			try{
				
				
				nf = java.text.NumberFormat.getInstance(Locale.US);   
			  nf.setMinimumFractionDigits(2);
				conn = m_sn_methods.met_user_validate(req); 
				stmt = conn.createStatement();
				
				rs = stmt.executeQuery(" SELECT CREDIT_LIMIT,CREDIT_PERIOD,TOLERANCE_CREDIT_PERIOD,RESERVE_MARGIN,INT_RATE "+
	  													" FROM "+m_schema_name+".FA_MK_PRO_QUOTA_DEFAULT ");
				if(rs.next()){
						credit_limit=rs.getDouble(1);
						credit_period=rs.getDouble(2);
						credit_tol_period=rs.getDouble(3);
						reserve_margin=rs.getDouble(4);
						int_rate=rs.getDouble(5);
				}
				conn.close();
				stmt.close();
				rs.close();
			}
			catch(Exception ex){
				out.println(ex.toString());
			}
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Marketing - Indicative Quotation for Factoring</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			     
			out.println("var m_sav_msg='';");
			out.println("function get_vector(data_vec) {");
			out.println("	 if(data_vec.length>0  && document.Form1.TXT_CLIENT_CODE.value!=\"\" ){");
			out.println("			if( data_vec[1] != \"0.00\"   ){ ");
			out.println("    	document.Form1.TXT_CREDIT_PERIOD.value=data_vec[1];"); 
			out.println("		  }");
			out.println("			else {");
			out.println("    	document.Form1.TXT_CREDIT_PERIOD.value='"+nf.format(credit_period)+"';"); 
			out.println("		  }");
			out.println("			if( data_vec[2] != \"0.00\"  ){ ");
			out.println("    	document.Form1.TXT_TOL_CREDIT_PERIOD.value=data_vec[2];"); 
			out.println("		  }");
			out.println(" 		else {");
			out.println("    	document.Form1.TXT_TOL_CREDIT_PERIOD.value='"+nf.format(credit_tol_period)+"';"); 
			out.println("		  }");
			out.println("		}");
			out.println("}");
			
			out.println("function makeRequest_detail_Fee() {");
			out.println("	  document.Form1.hid_option.value=\"3\";");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_MK_PRO_sql_validations_normal?chksql=FEE_PACKAGES&fee_pack_id=\"+document.Form1.TXT_FEE_CODE.value;");
			out.println("		load_interface(m_url,'NO');");
			out.println("}");
			
			out.println("function makeRequest_detail_Product() {");
			out.println("	  document.Form1.hid_option.value=\"2\";");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_MK_PRO_sql_validations_normal?chksql=PRODUCT_PACKAGES&product_id=\"+document.Form1.TXT_PRODUCT_CODE.value;");
			out.println("		load_interface(m_url,'NO');");
			out.println("}");
			
			out.println("function makeRequest() {");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_MK_sql_validations?chksql=get_CLIENT_CREDIT_PERIOD&client_code=\"+document.Form1.TXT_CLIENT_CODE.value;");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function get_vector_normal(m_data){");
			out.println("	if(document.Form1.hid_option.value==\"2\"){");
			out.println("		product_detail_data.innerHTML=m_data;");
			out.println("	}");
			out.println("	else if(document.Form1.hid_option.value==\"3\"){");
			out.println("		fee_detail_data.innerHTML=m_data;");
			out.println("	}");
			out.println("}");
			
			out.println("function help_update_product() {"); 
			out.println(" document.Form1.hid_help_type.value=\"2\";"); 
			out.println(" m_sql = \"m_help_DIV_TXT_PRODUCT_CODE_QUOTATION_sql\";"); 
			out.println(" m_criteria = document.Form1.TXT_PRODUCT_CODE.value+\"@\";");
			out.println(" HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_product() {"); 
			out.println("		document.Form1.TXT_PRODUCT_CODE.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_PRODUCT_DESC.value=oBj.valout[3];"); 
			out.println("		makeRequest_detail_Product();");
			out.println("}"); 
			
			out.println("function help_update_fee() {"); 
			out.println(" document.Form1.hid_help_type.value=\"3\";"); 
			out.println(" m_sql = \"m_help_DIV_TXT_FEE_CODE_QUOTATION_sql\";"); 
			out.println(" m_criteria = document.Form1.TXT_FEE_CODE.value+\"@\";");
			out.println(" HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_fee() {"); 
			out.println("		document.Form1.TXT_FEE_CODE.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_FEE_DESC.value=oBj.valout[3];"); 
			out.println("}"); 
			
			out.println("function clear_data(){"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=\"\";");
			out.println("		document.Form1.TXT_CLIENT_NAME.value=\"\";");
			out.println("		document.Form1.TXT_PRODUCT_CODE.value=\"\";");
			out.println("		document.Form1.TXT_PRODUCT_DESC.value=\"\";");
			out.println("		document.Form1.TXT_FEE_CODE.value=\"\";");
			out.println("		document.Form1.TXT_FEE_DESC.value=\"\";");
			out.println("	  document.Form1.TXT_INQUERY_NO.value=\"\";"); 
			out.println("		document.Form1.TXT_CREDIT_LIMIT.value=\"\";");
			out.println("		document.Form1.TXT_CREDIT_PERIOD.value=\"\";");
			out.println("		document.Form1.TXT_TOL_CREDIT_PERIOD.value=\"\";");
			out.println("	  document.Form1.TXT_RES_MARGIN.value=\"\";"); 
			out.println("		product_detail_data.innerHTML=\"\";");
			out.println("		fee_detail_data.innerHTML=\"\";");
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("	if(document.Form1.TXT_CLIENT_CODE.value==\"\" ){  "); 
			out.println("		DIV_TXT_CLIENT_CODE.style.color='red';");
			out.println("		return false;"); 
			out.println("	}"); 
			/*out.println("	else if(document.Form1.TXT_INQUERY_NO.value==\"\"){  "); 
			out.println("		DIV_TXT_INQUERY_NO.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");*/
			out.println("	else if(document.Form1.TXT_CREDIT_LIMIT.value==\"\"){  "); 
			out.println("		DIV_TXT_CREDIT_LIMIT.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	else if(document.Form1.TXT_CREDIT_PERIOD.value==\"\"){  "); 
			out.println("		DIV_TXT_CREDIT_PERIOD.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	else if(document.Form1.TXT_TOL_CREDIT_PERIOD.value==\"\"){  "); 
			out.println("		DIV_TXT_TOL_CREDIT_PERIOD.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	else if(document.Form1.TXT_RES_MARGIN.value==\"\"){  "); 
			out.println("		DIV_TXT_RES_MARGIN.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	else if(document.Form1.TXT_INT_RATE.value==\"\"){  "); 
			out.println("		DIV_TXT_INT_RATE.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	else if(document.Form1.TXT_PRODUCT_CODE.value==\"\"){  "); 
			out.println("		DIV_TXT_PRODUCT_CODE.style.color='red';");
			out.println("		return false;"); 
			out.println("	}"); 
			out.println("	else if(document.Form1.TXT_FEE_CODE.value==\"\"){  "); 
			out.println("		DIV_TXT_FEE_CODE.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	else{"); 
			out.println("		return true;"); 
			out.println("	}"); 
			out.println("}"); 			

			out.println("function before_submit(){ "); 
			out.println("	get_display_msg();");
			out.println("	if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+m_sav_msg+\" ?\")){ ");
			out.println("			for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("				document.Form1.elements[i].disabled=false;");
			out.println("			}");
			out.println("			if(validate_data()){"); 
			out.println("				document.Form1.action='"+m_class_url+"/"+m_fschema_name+"FA_MK_Save_Quotations';");  
			out.println("				document.Form1.submit();	"); 
			out.println("			}"); 
			out.println("		}"); 
			out.println("	}"); 
			out.println("	else { "); 
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("	}");
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_MK_display_quotation';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_MK_display_quotation';"); 
			out.println("}"); 

			out.println("function save_window(){	"); 
			out.println("	before_submit();"); 
			out.println("}"); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_FA_MK_QUOTATION\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
 
			out.println("function load_roll_value(m_val){"); 
			out.println("	help_box.innerHTML=\" Marketing - Indicative Quotation for Factoring - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\"  Marketing - Indicative Quotation for Factoring \";"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			//out.println("	if(confirm(\"Are You Sure\")){ ");
			out.println("		if(m_val==\"NEW\"){"); 
			out.println("			new_window();"); 
			out.println("		}"); 
			out.println("		else if(m_val==\"HELP\"){"); 
			out.println("			load_help_msg();"); 
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
			//out.println("	}"); 
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

			out.println("function clear_fields(){"); 
			out.println("	 if(document.Form1.hid_help_type.value==\"1\") {" ); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value='';"); 
			out.println("		document.Form1.TXT_CLIENT_NAME.value='';"); 
			out.println("		document.Form1.TXT_INQUERY_NO.value='';"); 
			//out.println("		clear_data();");
			out.println("  }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"2\") {" ); 
			out.println("		document.Form1.TXT_PRODUCT_CODE.value='';"); 
			out.println("		document.Form1.TXT_PRODUCT_DESC.value='';");  
			out.println("		product_detail_data.innerHTML=\"\";");
			out.println("  }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"3\") {" ); 
			out.println("		document.Form1.TXT_FEE_CODE.value='';"); 
			out.println("		document.Form1.TXT_FEE_DESC.value='';"); 
			out.println("		fee_detail_data.innerHTML=\"\";");
			out.println("  }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"4\") {" ); 
			out.println("		document.Form1.TXT_INQUERY_NO.value='';"); 
			out.println("  }		"); 
			out.println("}		"); 

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
			out.println(" clear_fields();");
			out.println(" } else "); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("		if(oBj.valout[1] !=\"Close\"){"); 
			out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("				if(oBj.valout[1]!=\"Next\"){"); 
			out.println("					if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("						help_update_value_assign_client();"); 
	  	out.println("					}"); 
			out.println("					else if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("						help_update_value_assign_product();"); 
	  	out.println("					}"); 
			out.println("					else if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("						help_update_value_assign_fee();"); 
	  	out.println("					}"); 
			out.println("					else if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("						help_update_value_assign_inquery();"); 
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
			out.println("	 	else{	"); 
			out.println("	 		clear_fields();	"); 
			out.println("	 	}	"); 
			out.println("	}"); 
			/*out.println("	else{	"); 
			out.println("		//clear_data();	"); 
			out.println("	}");*/
			out.println("}"); 

			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("		HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("		HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			
			/*out.println("function show_client(){");
			out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"FA_MAS_display_client_creation_details?client_code=\"+document.Form1.TXT_CLIENT_CODE.value;");
			out.println("	popupwin = window.showModalDialog(m_url,\"dialogWidth:350em; dialogHeight:300em; center:yes; status:no\");"); 
			out.println("}");*/

			out.println("function help_update_inquery() {"); 
			out.println(" document.Form1.hid_help_type.value=\"4\";"); 
			out.println(" m_sql = \"m_help_DIV_TXT_INQUERY_CODE_QUOTATION_sql\";"); 
			out.println(" m_criteria = document.Form1.TXT_INQUERY_NO.value+\"@\";");
			out.println(" HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_inquery() {"); 
			out.println("		document.Form1.TXT_INQUERY_NO.value=oBj.valout[2];"); 
			out.println("}");
			
			out.println("function help_update() {"); 
			out.println(" document.Form1.hid_help_type.value=\"1\";"); 
			out.println(" m_sql = \"m_help_DIV_TXT_CLIENT_CODE_QUOTATION_sql\";"); 
			out.println(" m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\";");
			out.println(" HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_client() {"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_CLIENT_NAME.value=oBj.valout[4];"); 
			out.println("		document.Form1.TXT_INQUERY_NO.value=oBj.valout[5];"); 
			out.println("		makeRequest();");
			out.println("   ");
			out.println("}"); 
			
			out.println("function help_update_product() {"); 
			out.println(" document.Form1.hid_help_type.value=\"2\";"); 
			out.println(" m_sql = \"m_help_DIV_TXT_PRODUCT_CODE_QUOTATION_sql\";"); 
			out.println(" m_criteria = document.Form1.TXT_PRODUCT_CODE.value+\"@\";");
			out.println(" HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_product() {"); 
			out.println("		document.Form1.TXT_PRODUCT_CODE.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_PRODUCT_DESC.value=oBj.valout[3];"); 
			out.println("		makeRequest_detail_Product();");
			out.println("}"); 
			
			out.println("function help_update_fee() {"); 
			out.println(" document.Form1.hid_help_type.value=\"3\";"); 
			out.println(" m_sql = \"m_help_DIV_TXT_FEE_CODE_QUOTATION_sql\";"); 
			out.println(" m_criteria = document.Form1.TXT_FEE_CODE.value+\"@\";");
			out.println(" HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_fee() {"); 
			out.println("		document.Form1.TXT_FEE_CODE.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_FEE_DESC.value=oBj.valout[3];"); 
			out.println("		makeRequest_detail_Fee();");
			out.println("}"); 
			out.println("</script>"); 
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock();\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"99\">"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Marketing - Indicative Quotation for Factoring </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");
			out.println("<td width='10%' align='center'></td>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			
			out.println("<br>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' onblur=\"help_update()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\">");
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Details\" onClick=\"show_client(document.Form1.TXT_CLIENT_CODE.value)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_INQUERY_NO'  class=div_input>Inqury No </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_INQUERY_NO' maxlength='10' size='10' onblur=\"help_update_inquery()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_inquery()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_DESC'  class=div_input>Client Name</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' maxlength='200' size='50' style='width:200' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CREDIT_LIMIT'  class=div_input>Credit Limit *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CREDIT_LIMIT' maxlength='25' size='50' style='width:200;text-align:right;' value=\""+nf.format(credit_limit)+"\" onchange=\"format_num(document.Form1.TXT_CREDIT_LIMIT,4)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");	
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CREDIT_PERIOD'  class=div_input>Credit Period *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CREDIT_PERIOD' maxlength='25' size='50' style='width:200;text-align:right;' value=\""+nf.format(credit_period)+"\"  onchange=\"format_num(document.Form1.TXT_CREDIT_PERIOD,4)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_TOL_CREDIT_PERIOD'  class=div_input>Tolerance Credit Period *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TOL_CREDIT_PERIOD' maxlength='25' size='50' style='width:200;text-align:right;'  value=\""+nf.format(credit_tol_period)+"\" onchange=\"format_num(document.Form1.TXT_TOL_CREDIT_PERIOD,4)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_RES_MARGIN'  class=div_input>Reserve Margin Value % *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_RES_MARGIN' maxlength='25' size='25' style='width:200;text-align:right;'  value=\""+nf.format(reserve_margin)+"\" onchange=\"format_num(document.Form1.TXT_RES_MARGIN,4)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_INT_RATE'  class=div_input>Interest Rate % *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_INT_RATE' maxlength='25' size='25' style='width:200;text-align:right;'  value=\""+nf.format(int_rate)+"\" onchange=\"format_num(document.Form1.TXT_INT_RATE,4)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>"); 
						
			out.println("<table align='center' width='100%' class='table'>");
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_PRODUCT_CODE'  class=div_input>Product Package Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PRODUCT_CODE' maxlength='10' size='10' onblur=\"help_update_product()\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_product()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_PRODUCT_DESC'  class=div_input>Product Package Description </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PRODUCT_DESC' maxlength='50' size='50' style='width:300' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<table>"); 
			out.println("<table align='center' width='100%' class='table'>");
			out.println("<tr>"); 
			out.println("<td width='100%'><DIV id='product_detail_data'  class=div_input></DIV></td>");
			out.println("</tr>");
			out.println("<table>"); 
			out.println("<br>"); 
			out.println("<table align='center' width='100%' class='table'>");
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FEE_CODE'  class=div_input>Fee Package Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FEE_CODE' maxlength='10' size='10' onblur=\"help_update_fee()\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_fee()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FEE_DESC'  class=div_input>Fee Package Description </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FEE_DESC' maxlength='50' size='50' style='width:300' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<DIV id='fee_detail_data'  class=div_input></DIV>");
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
