// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006


import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_FA_CR_display_client_facility extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	java.text.NumberFormat nf;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name=m_sn_methods.schema_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
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
			out.println("<TITLE>Credit Process - Client Facility Creation</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("var m_sav_msg='';");
			
			out.println("function get_vector(data_vec) {");
			out.println("		if(data_vec.length>0 && document.Form1.hid_display_client.value==\"0\"){");
			out.println("		}");
			out.println("		else if(data_vec.length>0 && document.Form1.hid_display_client.value==\"1\"){");
			out.println("			m_act_len=parseInt(data_vec.length)/4;");
			out.println("			m_act_len=parseInt(m_act_len)-parseInt(document.Form1.hid_product_count.value);");
			out.println("			for(var i=1;i<=parseInt(m_act_len);i++){");
			out.println("				add_products();");
			out.println("			}");
			out.println("			var m_count=0;");
			out.println("   	for(k=1;k<=parseInt(document.Form1.hid_product_count.value);k++){");
			out.println("   		document.Form1.elements[\"TXT_PRODUCT_FEATURE_CODE_\"+k].value=data_vec[m_count];");
			out.println("   		document.Form1.elements[\"TXT_PRODUCT_FEATURE_DESC_\"+k].value=data_vec[m_count+1];");
			out.println("   		document.Form1.elements[\"TXT_PRODUCT_FEATURE_COMMENT_\"+k].value=data_vec[m_count+2];");
			out.println("   		document.Form1.elements[\"TXT_PRODUCT_FEATURE_PARAM_\"+k].value=data_vec[m_count+3];");
			out.println("   		m_count=m_count+4;");
			out.println("			}");
			out.println("  		makeRequest(99);");
			out.println("		}");
			out.println("		else if(data_vec.length>0 && document.Form1.hid_display_client.value==\"2\"){");
			out.println("			m_act_len=parseInt(data_vec.length)/4;");
			out.println("			m_act_len=parseInt(m_act_len)-parseInt(document.Form1.hid_fee_count.value);");
			out.println("			for(var i=1;i<=parseInt(m_act_len);i++){");
			out.println("				add_fee();");
			out.println("			}");
			out.println("			var m_count=0;");
			out.println("   	for(k=1;k<=parseInt(document.Form1.hid_fee_count.value);k++){");
			out.println("   		document.Form1.elements[\"TXT_FEE_CODE_\"+k].value=data_vec[m_count];");
			out.println("   		document.Form1.elements[\"TXT_FEE_DESC_\"+k].value=data_vec[m_count+1];");
			out.println("   		document.Form1.elements[\"TXT_FEE_TYPE_\"+k].value=data_vec[m_count+2];");
			out.println("   		document.Form1.elements[\"TXT_FEE_VALUE_\"+k].value=data_vec[m_count+3];");
			out.println("   		m_count=m_count+4;");
			out.println("			}");
			out.println("  		makeRequest(99);");
			
			out.println("		}");
			out.println("		else if(data_vec.length>0 && document.Form1.hid_display_client.value==\"3\"){");
			out.println("			document.Form1.TXT_FACILITY_NO.value=data_vec[0];");
			out.println("			document.Form1.TXT_CLIENT_CODE.value=data_vec[1];");
			out.println("			document.Form1.TXT_CLIENT_NAME.value=data_vec[2];");
			out.println("			document.Form1.TXT_FACILITY_MANAGER.value=data_vec[3];");
			out.println("			document.Form1.TXT_FACILITY_MANAGER_NAME.value=data_vec[4];");
			out.println("			document.Form1.TXT_CLIENT_CMGR.value=data_vec[6];");
			out.println("			document.Form1.TXT_CLIENT_MMGR.value=data_vec[8];");
			out.println("			document.Form1.TXT_PRODUCT_CODE.value=data_vec[9];"); 
			out.println("			document.Form1.TXT_PRODUCT_DESC.value=data_vec[10];"); 
			out.println("			document.Form1.TXT_FEE_CODE.value=data_vec[11];"); 
			out.println("			document.Form1.TXT_FEE_DESC.value=data_vec[12];"); 
			out.println("			document.Form1.TXT_START_DD.value=data_vec[13];");
			out.println("			document.Form1.TXT_START_MM.value=data_vec[14];");
			out.println("			document.Form1.TXT_START_YY.value=data_vec[15];");
			out.println("			document.Form1.TXT_END_DD.value=data_vec[16];");
			out.println("			document.Form1.TXT_END_MM.value=data_vec[17];");
			out.println("			document.Form1.TXT_END_YY.value=data_vec[18];"); 
			out.println("			document.Form1.TXT_CREDIT_LIMIT.value=data_vec[19];");
			out.println("			document.Form1.TXT_CREDIT_PERIOD.value=data_vec[20];");
			out.println("			document.Form1.TXT_TOL_CREDIT_PERIOD.value=data_vec[21];");
			out.println("	  	document.Form1.TXT_RES_MARGIN.value=data_vec[22];");
			out.println("	  	document.Form1.TXT_INT_RATE.value=data_vec[23];");
			out.println("	  	document.Form1.TXT_COMMENTS.value=data_vec[24];");
			out.println("  		makeRequest(4);");
			
			out.println("		}");
			out.println("		else if(data_vec.length>0 && document.Form1.hid_display_client.value==\"4\"){");
			out.println("			m_act_len=parseInt(data_vec.length)/5;");	
			out.println("			m_act_len=parseInt(m_act_len)-parseInt(document.Form1.hid_product_count.value);");
			out.println("			for(var i=1;i<=parseInt(m_act_len);i++){");
			out.println("				add_products();");
			out.println("			}");
			out.println("			var m_count=0;");
			out.println("   	for(k=1;k<=parseInt(document.Form1.hid_product_count.value);k++){");
			out.println("   		document.Form1.elements[\"TXT_PRODUCT_FEATURE_CODE_\"+k].value=data_vec[m_count];");
			out.println("   		document.Form1.elements[\"TXT_PRODUCT_FEATURE_DESC_\"+k].value=data_vec[m_count+1];");
			out.println("   		document.Form1.elements[\"TXT_PRODUCT_FEATURE_COMMENT_\"+k].value=data_vec[m_count+2];");
			out.println("   		document.Form1.elements[\"TXT_PRODUCT_FEATURE_PARAM_\"+k].value=data_vec[m_count+3];");
			out.println("   		if(data_vec[m_count+4]==\"Y\"){");
			out.println("   		document.Form1.elements[\"CHK_PRODUCT_\"+k].checked=true;");
			out.println("   		}else{");
			out.println("   		document.Form1.elements[\"CHK_PRODUCT_\"+k].checked=false;}");
			out.println("   		m_count=m_count+5;");
			out.println("			}");
			out.println("			document.Form1.BUT_HELP_PRODUCT_MAIN_ADD.disabled=true;");
			out.println("  		makeRequest(5);");
			out.println("		}");
			out.println("		else if(data_vec.length==0 && document.Form1.hid_display_client.value==\"4\"){");
			out.println("  		makeRequest(5);");
			out.println("		}");
			out.println("		else if(data_vec.length>0 && document.Form1.hid_display_client.value==\"5\"){");
			out.println("			m_act_len=parseInt(data_vec.length)/5;");		
			out.println("			m_act_len=parseInt(m_act_len)-parseInt(document.Form1.hid_fee_count.value);");
			out.println("			for(var i=1;i<=parseInt(m_act_len);i++){");
			out.println("				add_fee();");
			out.println("			}");
			out.println("			var m_count=0;");
			out.println("   	for(k=1;k<=parseInt(document.Form1.hid_fee_count.value);k++){");
			out.println("   		document.Form1.elements[\"TXT_FEE_CODE_\"+k].value=data_vec[m_count];");
			out.println("   		document.Form1.elements[\"TXT_FEE_DESC_\"+k].value=data_vec[m_count+1];");
			out.println("   		document.Form1.elements[\"TXT_FEE_TYPE_\"+k].value=data_vec[m_count+2];");
			out.println("   		document.Form1.elements[\"TXT_FEE_VALUE_\"+k].value=data_vec[m_count+3];");
			out.println("   		if(data_vec[m_count+4]==\"Y\"){");
			out.println("   		document.Form1.elements[\"CHK_FEE_\"+k].checked=true;");
			out.println("   		}else{");
			out.println("   		document.Form1.elements[\"CHK_FEE_\"+k].checked=false;}");
			out.println("   		m_count=m_count+5;");
			out.println("			}");
			out.println("		document.Form1.BUT_HELP_FEE_MAIN_ADD.disabled=true;");
			out.println("  	makeRequest(6);");
			//out.println("  	alert(\"000000\");");
			
			out.println("		}");	
			out.println("		else if(data_vec.length==0 && document.Form1.hid_display_client.value==\"5\"){");
			out.println("  		makeRequest(6);");
		
			out.println("		}");
			out.println("		else if(data_vec.length>0 && document.Form1.hid_display_client.value==\"6\"){");
			out.println("			m_act_len=parseInt(data_vec.length)/7;");		
			out.println("			m_act_len=parseInt(m_act_len)-parseInt(document.Form1.hid_guarantee_count.value);");
			out.println("			for(var i=1;i<=parseInt(m_act_len);i++){");
			out.println("				add_guarantee();");
			out.println("			}");
			out.println("			var m_count=0;");
			out.println("   	for(k=1;k<=parseInt(document.Form1.hid_guarantee_count.value);k++){");
			out.println("   		document.Form1.elements[\"TXT_GUR_NAME\"+k].value=data_vec[m_count];");
			out.println("   		document.Form1.elements[\"TXT_GUR_BANK_\"+k].value=data_vec[m_count+1];");
			out.println("   		document.Form1.elements[\"TXT_GUR_CONT_PERSON_\"+k].value=data_vec[m_count+2];");
			out.println("   		document.Form1.elements[\"TXT_GUR_START_DATE_\"+k].value=data_vec[m_count+3];");
			out.println("   		document.Form1.elements[\"TXT_GUR_END_DATE_\"+k].value=data_vec[m_count+4];");
			out.println("   		document.Form1.elements[\"TXT_GUR_VALUE_\"+k].value=data_vec[m_count+5];");
			out.println("   		document.Form1.elements[\"TXT_GUR_COMMENT_\"+k].value=data_vec[m_count+6];");
			out.println("   		m_count=m_count+7;");
			out.println("			}");
			out.println("  		makeRequest(10);");
			//out.println("  	alert(\"1111\");");
			out.println("		}");
			
			
			//added by disnaka on 2011-10-05
			
			out.println("		else if(data_vec.length>0 && document.Form1.hid_display_client.value==\"100\"){");
			out.println("			m_act_len=parseInt(data_vec.length)/2;");		
			out.println("			m_act_len=parseInt(m_act_len)-parseInt(document.Form1.hid_guarantor_count.value);");
			//out.println("			document.Form1.hid_guarantor_count.value=m_act_len;");
			out.println("			m_act_len=parseInt(m_act_len);");
			out.println("			for(var i=1;i<=parseInt(m_act_len);i++){");
			out.println("				add_guarantor();");
			out.println("			}");
			out.println("			var m_count=0;");
			out.println("   	for(k=1;k<=parseInt(document.Form1.hid_guarantor_count.value);k++){");
			out.println("   		document.Form1.elements[\"TXT_GAURANTOR_CODE\"+k].value=data_vec[m_count];");
			out.println("   		document.Form1.elements[\"TXT_GAURANTOR_NAME\"+k].value=data_vec[m_count+1];");
			out.println("   		m_count=m_count+2;");
			out.println("			}");
			//out.println("  		makeRequest(10);");
			out.println("		}");
			
			//---
			out.println("		else if(data_vec.length==0 && document.Form1.hid_display_client.value==\"6\"){");
			out.println("  		makeRequest(10);");
			out.println("		}");
			out.println("		else if(data_vec.length>0 && document.Form1.hid_display_client.value==\"7\"){");
			out.println("			if(document.Form1.SCREEN_NAME.value==\"NEW\"){"); 
			out.println("				document.Form1.TXT_CLIENT_CODE.value=data_vec[1];");
			out.println("				document.Form1.TXT_CLIENT_NAME.value=data_vec[2];");
			out.println("				document.Form1.TXT_FACILITY_MANAGER.value=data_vec[3];");
			out.println("				document.Form1.TXT_FACILITY_MANAGER_NAME.value=data_vec[4];");
			out.println("				document.Form1.TXT_CLIENT_CMGR.value=data_vec[4];");
			out.println("				document.Form1.TXT_CLIENT_MMGR.value=data_vec[6];");
			out.println("				document.Form1.TXT_PRODUCT_CODE.value=data_vec[12];"); 
			out.println("				document.Form1.TXT_PRODUCT_DESC.value=data_vec[13];"); 
			out.println("				document.Form1.TXT_FEE_CODE.value=data_vec[14];"); 
			out.println("				document.Form1.TXT_FEE_DESC.value=data_vec[15];");  
			out.println("				document.Form1.TXT_CREDIT_LIMIT.value=data_vec[7];");
			out.println("				document.Form1.TXT_CREDIT_PERIOD.value=data_vec[8];");
			out.println("				document.Form1.TXT_TOL_CREDIT_PERIOD.value=data_vec[9];");
			out.println("	  		document.Form1.TXT_RES_MARGIN.value=data_vec[10];");
			out.println("	  		document.Form1.TXT_INT_RATE.value=data_vec[11];");
			out.println("  			makeRequest(8);");
			out.println("			}");
			out.println("		}");
			out.println("		else if(data_vec.length>0 && document.Form1.hid_display_client.value==\"9\"){");
			out.println("			m_act_len=parseInt(data_vec.length)/5;");
			out.println("			m_act_len=parseInt(m_act_len)-parseInt(document.Form1.hid_product_count.value);");
			out.println("			for(var i=1;i<=parseInt(m_act_len);i++){");
			out.println("				add_products();");
			out.println("			}");
			out.println("			var m_count=0;");
			out.println("   	for(k=1;k<=parseInt(document.Form1.hid_product_count.value);k++){");
			out.println("   		document.Form1.elements[\"TXT_PRODUCT_FEATURE_CODE_\"+k].value=data_vec[m_count];");
			out.println("   		document.Form1.elements[\"TXT_PRODUCT_FEATURE_DESC_\"+k].value=data_vec[m_count+1];");
			out.println("   		document.Form1.elements[\"TXT_PRODUCT_FEATURE_COMMENT_\"+k].value=data_vec[m_count+2];");
			out.println("   		document.Form1.elements[\"TXT_PRODUCT_FEATURE_PARAM_\"+k].value=data_vec[m_count+3];");
			out.println("   		if(data_vec[m_count+4]==\"Y\"){");
			out.println("   		document.Form1.elements[\"CHK_PRODUCT_\"+k].checked=true;");
			out.println("   		}else{");
			out.println("   		document.Form1.elements[\"CHK_PRODUCT_\"+k].checked=false;}");
			out.println("   		m_count=m_count+5;");
			out.println("			}");
			out.println("		document.Form1.BUT_HELP_PRODUCT_MAIN_ADD.disabled=true;");
			//out.println("  	makeRequest(99);");
			out.println("		}");
			out.println("		else if(data_vec.length>0 && document.Form1.hid_display_client.value==\"8\"){");
			out.println("			m_act_len=parseInt(data_vec.length)/5;");		
			out.println("			m_act_len=parseInt(m_act_len)-parseInt(document.Form1.hid_fee_count.value);");
			out.println("			for(var i=1;i<=parseInt(m_act_len);i++){");
			out.println("				add_fee();");
			out.println("			}");
			out.println("			var m_count=0;");
			out.println("   	for(k=1;k<=parseInt(document.Form1.hid_fee_count.value);k++){");
			out.println("   		document.Form1.elements[\"TXT_FEE_CODE_\"+k].value=data_vec[m_count];");
			out.println("   		document.Form1.elements[\"TXT_FEE_DESC_\"+k].value=data_vec[m_count+1];");
			out.println("   		document.Form1.elements[\"TXT_FEE_TYPE_\"+k].value=data_vec[m_count+2];");
			out.println("   		document.Form1.elements[\"TXT_FEE_VALUE_\"+k].value=data_vec[m_count+3];");
			out.println("   		if(data_vec[m_count+4]==\"Y\"){");
			out.println("   		document.Form1.elements[\"CHK_FEE_\"+k].checked=true;");
			out.println("   		}else{");
			out.println("   		document.Form1.elements[\"CHK_FEE_\"+k].checked=false;}");
			out.println("   		m_count=m_count+5;");
			out.println("			}");
			out.println("		document.Form1.BUT_HELP_FEE_MAIN_ADD.disabled=true;");
			out.println("  	makeRequest(9);");
			out.println("		}");	
			out.println("		else if(data_vec.length>0 && document.Form1.hid_display_client.value==\"10\"){");
			out.println("			document.Form1.TXT_QUOTATION_NO.value=data_vec[0];");
			out.println("  		makeRequest(99);");
			out.println("  		makeRequest(100);"); //added by disnaka on 2011-10-05
			out.println("		}");
			
			out.println("		else if(data_vec.length==0 && document.Form1.hid_display_client.value==\"10\"){");
			//out.println("			document.Form1.TXT_QUOTATION_NO.value=data_vec[0];");
			out.println("  		makeRequest(99);");
			out.println("  		makeRequest(100);"); //added by disnaka on 2011-10-05
			
			out.println("		}");
			
			
			out.println("	  else if(data_vec.length>0 && document.Form1.hid_display_client.value==\"11\" && document.Form1.TXT_CLIENT_CODE.value!=\"\" ){");
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
			out.println("    	document.Form1.TXT_CREDIT_LIMIT.value='"+nf.format(credit_limit)+"';"); 
			out.println("    	document.Form1.TXT_RES_MARGIN.value='"+nf.format(reserve_margin)+"';"); 
			out.println("    	document.Form1.TXT_INT_RATE.value='"+nf.format(int_rate)+"';"); 
			out.println("		}");
			out.println("}");
			
			out.println("function makeRequest(m_val) {");
			out.println("		if(m_val==\"1\"){");
			out.println("			document.Form1.hid_display_client.value=1;");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=m_NEW_PRODUCT_FEATURE_FACILITY&product_code=\"+document.Form1.TXT_PRODUCT_CODE.value;");
			out.println("		}");
			out.println("		else if(m_val==\"2\"){");
			out.println("			document.Form1.hid_display_client.value=2;");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=m_NEW_FEE_FEATURE_FACILITY&fee_code=\"+document.Form1.TXT_FEE_CODE.value;");
			out.println("		}");
			out.println("		else if(m_val==\"3\"){");
			out.println("			document.Form1.hid_display_client.value=3;");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=m_EDIT_MAIN&facility_code=\"+document.Form1.TXT_FACILITY_NO.value;");
			out.println("		}");
			out.println("		else if(m_val==\"4\"){");
			out.println("			document.Form1.hid_display_client.value=4;");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=m_EDIT_PRODUCT&facility_code=\"+document.Form1.TXT_FACILITY_NO.value+\"&product_code=\"+document.Form1.TXT_PRODUCT_CODE.value;");
			out.println("		}");
			out.println("		else if(m_val==\"5\"){");
			out.println("			document.Form1.hid_display_client.value=5;");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=m_EDIT_FEE&facility_code=\"+document.Form1.TXT_FACILITY_NO.value+\"&fee_code=\"+document.Form1.TXT_FEE_CODE.value;");
			out.println("		}");
			out.println("		else if(m_val==\"6\"){");
			out.println("			document.Form1.hid_display_client.value=6;");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=m_EDIT_GRANTEE&facility_code=\"+document.Form1.TXT_FACILITY_NO.value;");
			
			
			out.println("		}");
			out.println("		else if(m_val==\"7\"){");
			out.println("			document.Form1.hid_display_client.value=7;");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=m_LOAD_QUOTATION&quotation_code=\"+document.Form1.TXT_QUOTATION_NO.value;");
			out.println("		}");
			out.println("		else if(m_val==\"8\"){");
			out.println("			document.Form1.hid_display_client.value=8;");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=m_LOAD_QUOTATION_FEE&quotation_code=\"+document.Form1.TXT_QUOTATION_NO.value+\"&fee_code=\"+document.Form1.TXT_FEE_CODE.value;");
			out.println("		}");
			out.println("		else if(m_val==\"9\"){");
			out.println("			document.Form1.hid_display_client.value=9;");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=m_LOAD_QUOTATION_PRODUCT&quotation_code=\"+document.Form1.TXT_QUOTATION_NO.value+\"&product_code=\"+document.Form1.TXT_PRODUCT_CODE.value;");
			out.println("		}");
			out.println("		else if(m_val==\"10\"){");
			out.println("			document.Form1.hid_display_client.value=10;");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=m_LOAD_QUOTATION_FACILITY&facility_code=\"+document.Form1.TXT_FACILITY_NO.value;");
			out.println("		}");
			out.println("		else if(m_val==\"11\"){");
			out.println("			document.Form1.hid_display_client.value=11;");
			out.println("		  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_CLIENT_CREDIT_PERIOD&client_code=\"+document.Form1.TXT_CLIENT_CODE.value;");
			out.println("		}");
			out.println("		else if(m_val==\"99\"){");
			out.println("			document.Form1.hid_display_client.value=99;");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=idle\";");
			out.println("		}");
			
			out.println("		else if(m_val==\"100\"){"); //adde by disnaka on 2011-10-05
			out.println("			document.Form1.hid_display_client.value=100;");
			
			
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=m_EDIT_GRANTOR&facility_code=\"+document.Form1.TXT_FACILITY_NO.value;");
			
			
			out.println("		}");
			
			out.println("		load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function add_products(){"); 
			out.println("		 m_dir_count=parseInt(document.Form1.hid_product_count.value);");
			out.println("		 m_dir_count++;");
			out.println("		 if(parseInt(document.Form1.hid_product_count.value)==0){");
			out.println("		 product_detail_data.innerHTML=\"\";"); 
			out.println("		 }");
			out.println("		 product_detail_data.innerHTML=product_detail_data.innerHTML+'<table  width=\"100%\">'+"); 
			out.println("		 '<tr>'+"); 
			out.println("		 '<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_PRODUCT_FEATURE_CODE_'+m_dir_count+'\" maxlength=\"100\"  disabled><input class=\"but_input\" type=\"button\" name=\"BUT_HELP_PRODUCT_MAIN\" value=\"Help\" onClick=\"help_products_feature('+m_dir_count+')\"></td>'+"); 
			out.println("		 '<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_PRODUCT_FEATURE_DESC_'+m_dir_count+'\" maxlength=\"100\" style=\"width:200\" disabled></td>'+");
			out.println("		 '<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_PRODUCT_FEATURE_COMMENT_'+m_dir_count+'\" maxlength=\"100\" style=\"width:200\"  disabled></td>'+");
			out.println("		 '<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_PRODUCT_FEATURE_PARAM_'+m_dir_count+'\" maxlength=\"25\" ><input type=\"checkbox\" name=\"CHK_PRODUCT_'+m_dir_count+'\" checked></td>'+");
			out.println("		 '<td width=\"*%\" class=div_input></td>'+");
			out.println("		 '</tr>'+"); 
			out.println("		 '</table>';"); 
			out.println("		 document.Form1.hid_product_count.value=m_dir_count;");
			out.println("}"); 
			
			/*out.println("function add_products(){"); 
			out.println("		 m_dir_count=parseInt(document.Form1.hid_product_count.value);");
			out.println("		 m_dir_count++;");
			out.println("		 if(parseInt(document.Form1.hid_product_count.value)==0){");
			out.println("		 product_detail_data.innerHTML=\"\";"); 
			out.println("		 }");
			out.println("		 product_detail_data.innerHTML=product_detail_data.innerHTML+'<table  width=\"100%\">'+"); 
			out.println("		 '<tr>'+"); 
			out.println("		 '<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_PRODUCT_FEATURE_CODE_'+m_dir_count+'\" maxlength=\"100\"  disabled><input class=\"but_input\" type=\"button\" name=\"BUT_HELP_PRODUCT_MAIN\" value=\"Help\" onClick=\"help_products_feature('+m_dir_count+')\"></td>'+"); 
			out.println("		 '<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_PRODUCT_FEATURE_DESC_'+m_dir_count+'\" maxlength=\"100\" style=\"width:200\" disabled></td>'+");
			out.println("		 '<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_PRODUCT_FEATURE_COMMENT_'+m_dir_count+'\" maxlength=\"100\" style=\"width:200\"  disabled></td>'+");
			out.println("		 '<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_PRODUCT_FEATURE_PARAM_'+m_dir_count+'\" maxlength=\"25\" ><input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_DIRECTOR_MAIN\" value=\"Delete\" onClick=\"delete_products('+m_dir_count+')\"></td>'+");
			out.println("		 '<td width=\"*%\" class=div_input></td>'+");
			out.println("		 '</tr>'+"); 
			out.println("		 '</table>';"); 
			out.println("		 document.Form1.hid_product_count.value=m_dir_count;");
			out.println("}"); 
			*/
			out.println("function delete_products(mnum){");
			out.println("	if(parseInt(mnum)>0){");
			out.println("		 m_dir_count=parseInt(document.Form1.hid_product_count.value);");
			out.println("    m_count=0;");
			out.println("    m_str=\"\";");
			out.println("    for(k=1;k<=m_dir_count;k++){");
			out.println("    	if(k!=mnum){");
			out.println("		 		m_count++;");
			out.println("    		obj1=document.Form1.elements[\"TXT_PRODUCT_FEATURE_CODE_\"+k].value;");
			out.println("    		obj2=document.Form1.elements[\"TXT_PRODUCT_FEATURE_DESC_\"+k].value;");
			out.println("    		obj3=document.Form1.elements[\"TXT_PRODUCT_FEATURE_COMMENT_\"+k].value;");
			out.println("    		obj4=document.Form1.elements[\"TXT_PRODUCT_FEATURE_PARAM_\"+k].value;");
			out.println("		 		m_str=m_str+'<table  width=\"100%\">'+"); 
			out.println("		 		'<tr>'+"); 
			out.println("		 		'<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_PRODUCT_FEATURE_CODE_'+m_count+'\" maxlength=\"100\"  disabled value=\"'+obj1+'\"><input class=\"but_input\" type=\"button\" name=\"BUT_HELP_PRODUCT_MAIN\" value=\"Help\" onClick=\"help_products_feature('+m_count+')\"></td>'+"); 
			out.println("		 		'<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_PRODUCT_FEATURE_DESC_'+m_count+'\" maxlength=\"100\" disabled style=\"width:200\" value=\"'+obj2+'\"></td>'+");
			out.println("		 		'<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_PRODUCT_FEATURE_COMMENT_'+m_count+'\" maxlength=\"100\" disabled style=\"width:200\" value=\"'+obj3+'\"></td>'+");
			out.println("		 		'<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_PRODUCT_FEATURE_PARAM_'+m_count+'\" maxlength=\"25\" value=\"'+obj4+'\"><input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_PRODUCT_MAIN\" value=\"Delete\" onClick=\"delete_products('+m_count+')\"></td>'+");
			out.println("		 		'<td width=\"*%\" class=div_input></td>'+");
			out.println("		 		'</tr>'+"); 
			out.println("		 		'</table>';");
			out.println("    	}");
			out.println("	 	 }");
			out.println("		 product_detail_data.innerHTML=m_str;");
			out.println("		 document.Form1.hid_product_count.value=m_count;");
			out.println("	}");
			out.println("}"); 
			
			out.println("function add_fee(){"); 
			out.println("		 m_dir_count=parseInt(document.Form1.hid_fee_count.value);");
			out.println("		 m_dir_count++;");
			out.println("		 if(parseInt(document.Form1.hid_fee_count.value)==0){");
			out.println("		 fee_detail_data.innerHTML=\"\";"); 
			out.println("		 }");
			out.println("		 fee_detail_data.innerHTML=fee_detail_data.innerHTML+'<table  width=\"100%\">'+"); 
			out.println("		 '<tr>'+"); 
			out.println("		 '<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_FEE_CODE_'+m_dir_count+'\" maxlength=\"100\"  disabled><input class=\"but_input\" type=\"button\" name=\"BUT_HELP_FEE_MAIN\" value=\"Help\" onClick=\"help_fee_feature('+m_dir_count+')\"></td>'+"); 
			out.println("		 '<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_FEE_DESC_'+m_dir_count+'\" maxlength=\"100\" style=\"width:200\" disabled></td>'+");
			out.println("		 '<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_FEE_TYPE_'+m_dir_count+'\" maxlength=\"100\" style=\"width:200\"  disabled></td>'+");
			out.println("		 '<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_FEE_VALUE_'+m_dir_count+'\" maxlength=\"25\"  onblur=\"format_number2(this,20)\" style=\"text-align:right\"><input type=\"checkbox\" name=\"CHK_FEE_'+m_dir_count+'\" checked></td>'+");
			out.println("		 '<td width=\"*%\" class=div_input></td>'+");
			out.println("		 '</tr>'+"); 
			out.println("		 '</table>';"); 
			out.println("		 document.Form1.hid_fee_count.value=m_dir_count;");
			out.println("}"); 
			
			/*out.println("function add_fee(){"); 
			out.println("		 m_dir_count=parseInt(document.Form1.hid_fee_count.value);");
			out.println("		 m_dir_count++;");
			out.println("		 if(parseInt(document.Form1.hid_fee_count.value)==0){");
			out.println("		 fee_detail_data.innerHTML=\"\";"); 
			out.println("		 }");
			out.println("		 fee_detail_data.innerHTML=fee_detail_data.innerHTML+'<table  width=\"100%\">'+"); 
			out.println("		 '<tr>'+"); 
			out.println("		 '<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_FEE_CODE_'+m_dir_count+'\" maxlength=\"100\"  disabled><input class=\"but_input\" type=\"button\" name=\"BUT_HELP_FEE_MAIN\" value=\"Help\" onClick=\"help_fee_feature('+m_dir_count+')\"></td>'+"); 
			out.println("		 '<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_FEE_DESC_'+m_dir_count+'\" maxlength=\"100\" style=\"width:200\" disabled></td>'+");
			out.println("		 '<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_FEE_TYPE_'+m_dir_count+'\" maxlength=\"100\" style=\"width:200\"  disabled></td>'+");
			out.println("		 '<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_FEE_VALUE_'+m_dir_count+'\" maxlength=\"25\"  onblur=\"format_number2(this,20)\" style=\"text-align:right\"><input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_FEE_MAIN\" value=\"Delete\" onClick=\"delete_fee('+m_dir_count+')\" ></td>'+");
			out.println("		 '<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"checkbox\" name=\"CHK_FEE_'+m_dir_count+'\" checked><input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_FEE_MAIN\" value=\"Delete\" onClick=\"delete_fee('+m_dir_count+')\" ></td>'+");
			out.println("		 '<td width=\"*%\" class=div_input></td>'+");
			out.println("		 '</tr>'+"); 
			out.println("		 '</table>';"); 
			out.println("		 document.Form1.hid_fee_count.value=m_dir_count;");
			out.println("}"); */
			
			out.println("function delete_fee(mnum){");
			out.println("	if(parseInt(mnum)>0){");
			out.println("		 m_dir_count=parseInt(document.Form1.hid_fee_count.value);");
			out.println("    m_count=0;");
			out.println("    m_str=\"\";");
			out.println("    for(k=1;k<=m_dir_count;k++){");
			out.println("    	if(k!=mnum){");
			out.println("		 		m_count++;");
			out.println("    		obj1=document.Form1.elements[\"TXT_FEE_CODE_\"+k].value;");
			out.println("    		obj2=document.Form1.elements[\"TXT_FEE_DESC_\"+k].value;");
			out.println("    		obj3=document.Form1.elements[\"TXT_FEE_TYPE_\"+k].value;");
			out.println("    		obj4=document.Form1.elements[\"TXT_FEE_VALUE_\"+k].value;");
			out.println("		 		m_str=m_str+'<table  width=\"100%\">'+"); 
			out.println("		 		'<tr>'+"); 
			out.println("		 		'<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_FEE_CODE_'+m_count+'\" maxlength=\"100\"  disabled value=\"'+obj1+'\"><input class=\"but_input\" type=\"button\" name=\"BUT_HELP_FEE_MAIN\" value=\"Help\" onClick=\"help_fee_feature('+m_count+')\"></td>'+"); 
			out.println("		 		'<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_FEE_DESC_'+m_count+'\" maxlength=\"100\" disabled style=\"width:200\" value=\"'+obj2+'\"></td>'+");
			out.println("		 		'<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_FEE_TYPE_'+m_count+'\" maxlength=\"100\" disabled style=\"width:200\" value=\"'+obj3+'\"></td>'+");
			out.println("		 		'<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_FEE_VALUE_'+m_count+'\" maxlength=\"25\" value=\"'+obj4+'\"  onblur=\"format_number2(this,20)\" style=\"text-align:right\"><input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_FEE_MAIN\" value=\"Delete\" onClick=\"delete_fee('+m_count+')\"></td>'+");
			out.println("		 		'<td width=\"*%\" class=div_input></td>'+");
			out.println("		 		'</tr>'+"); 
			out.println("		 		'</table>';");
			out.println("    	}");
			out.println("	 	 }");
			out.println("		 fee_detail_data.innerHTML=m_str;");
			out.println("		 document.Form1.hid_fee_count.value=m_count;");
			out.println("	}");
			out.println("}"); 
			
			out.println("function add_guarantee(){"); 
			out.println("		 m_dir_count=parseInt(document.Form1.hid_guarantee_count.value);");
			out.println("		 m_dir_count++;");
			out.println("		 if(parseInt(document.Form1.hid_guarantee_count.value)==0){");
			out.println("		 guarantee_detail_data.innerHTML=\"\";"); 
			out.println("		 }");
			out.println("		 guarantee_detail_data.innerHTML=guarantee_detail_data.innerHTML+'<table  width=\"100%\">'+"); 
			out.println("		 '<tr>'+"); 
			out.println("		 '<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_GUR_NAME'+m_dir_count+'\" style=\"width: 130px\" maxlength=\"100\" ></td>'+"); 
			out.println("		 '<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_GUR_BANK_'+m_dir_count+'\" style=\"width: 130px\" maxlength=\"100\" ></td>'+");
			out.println("		 '<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_GUR_CONT_PERSON_'+m_dir_count+'\" style=\"width: 130px\"  maxlength=\"100\" ></td>'+");
			out.println("		 '<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_GUR_START_DATE_'+m_dir_count+'\" style=\"width: 85px\"  maxlength=\"10\" ></td>'+");
			out.println("		 '<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_GUR_END_DATE_'+m_dir_count+'\" style=\"width: 85px\"  maxlength=\"10\" ></td>'+");
			out.println("		 '<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_GUR_VALUE_'+m_dir_count+'\" style=\"width: 130px\" maxlength=\"25\" onblur=\"format_number2(this,20)\"></td>'+");
			out.println("		 '<td width=\"*%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_GUR_COMMENT_'+m_dir_count+'\" style=\"width: 120px\"  maxlength=\"200\" ><input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_GUARANTEE_MAIN\" value=\"Delete\" onClick=\"delete_guarantee('+m_dir_count+')\" ></td>'+");
			out.println("		 '</tr>'+"); 
			out.println("		 '</table>';"); 
			out.println("		 document.Form1.hid_guarantee_count.value=m_dir_count;");
			out.println("}"); 
			
			out.println("function delete_guarantee(mnum){");
			out.println("	if(parseInt(mnum)>0){");
			out.println("		 m_dir_count=parseInt(document.Form1.hid_guarantee_count.value);");
			out.println("    m_count=0;");
			out.println("    m_str=\"\";");
			out.println("    for(k=1;k<=m_dir_count;k++){");
			out.println("    	if(k!=mnum){");
			out.println("		 		m_count++;");
			out.println("    		obj1=document.Form1.elements[\"TXT_GUR_NAME\"+k].value;");
			out.println("    		obj2=document.Form1.elements[\"TXT_GUR_BANK_\"+k].value;");
			out.println("    		obj3=document.Form1.elements[\"TXT_GUR_CONT_PERSON_\"+k].value;");
			out.println("    		obj4=document.Form1.elements[\"TXT_GUR_START_DATE_\"+k].value;");
			out.println("    		obj5=document.Form1.elements[\"TXT_GUR_END_DATE_\"+k].value;");
			out.println("    		obj6=document.Form1.elements[\"TXT_GUR_VALUE_\"+k].value;");
			out.println("    		obj7=document.Form1.elements[\"TXT_GUR_COMMENT_\"+k].value;");
			out.println("		 		m_str=m_str+'<table  width=\"100%\">'+"); 
			out.println("		 		'<tr>'+"); 
			out.println("		 		'<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_GUR_NAME'+m_count+'\" style=\"width: 130px\"  maxlength=\"100\" value=\"'+obj1+'\"></td>'+"); 
			out.println("		 		'<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_GUR_BANK_'+m_count+'\" style=\"width: 130px\"  maxlength=\"100\" value=\"'+obj2+'\"></td>'+");
			out.println("		 		'<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_GUR_CONT_PERSON_'+m_count+'\" style=\"width: 130px\" maxlength=\"100\" value=\"'+obj3+'\"></td>'+");
			out.println("		 		'<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_GUR_START_DATE_'+m_count+'\" style=\"width: 85px\" maxlength=\"15\" value=\"'+obj4+'\"></td>'+");
			out.println("		 		'<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_GUR_END_DATE_'+m_count+'\" style=\"width: 85px\"  maxlength=\"15\" value=\"'+obj5+'\"></td>'+");
			out.println("		 		'<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_GUR_VALUE_'+m_count+'\" style=\"width: 130px\" maxlength=\"25\" onblur=\"format_number2(this,20)\" value=\"'+obj6+'\"></td>'+");
			out.println("		 		'<td width=\"*%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_GUR_COMMENT_'+m_count+'\"  style=\"width: 120px\"  maxlength=\"200\" value=\"'+obj7+'\"><input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_GUARANTEE_MAIN\" value=\"Delete\" onClick=\"delete_guarantee('+m_count+')\" ></td>'+");
			out.println("		 		'</tr>'+"); 
			out.println("		 		'</table>';");
			out.println("    	}");
			out.println("	 	 }");
			out.println("		 guarantee_detail_data.innerHTML=m_str;");
			out.println("		 document.Form1.hid_guarantee_count.value=m_count;");
			out.println("	}");
			out.println("}");
			
			
			
			//--add by disnaka Jayasuriya on 2011-09-28
			out.println("function add_guarantor(){"); 
			out.println("		 m_dir_count=parseInt(document.Form1.hid_guarantor_count.value);");
			out.println("		 m_dir_count++;");
			out.println("		 if(parseInt(document.Form1.hid_guarantor_count.value)==0){");
			out.println("		 guarantor_detail_data.innerHTML=\"\";"); 
			out.println("		 }");
			out.println("		 guarantor_detail_data.innerHTML=guarantor_detail_data.innerHTML+'<table  width=\"100%\">'+"); 
			out.println("		 '<tr>'+"); 
			out.println("        '<td width=\"1%\" class=div_input></td>'+ ");
			out.println("		 '<td width=\"25%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_GAURANTOR_CODE'+m_dir_count+'\" style=\"width: 130px\" maxlength=\"100\" >'+"); 
			out.println("        '<input class=\"but_input\" type=\"button\" name=BUT_TXT_GAURANTOR_CODE_HELP'+m_dir_count+' value=\"Help\" onClick=\"help_button_5('+m_dir_count+')\"></td>'+");
			out.println("		 '<td width=\"25%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_GAURANTOR_NAME'+m_dir_count+'\" style=\"width: 130px\" maxlength=\"100\" disabled>'+");
			out.println("		 '<input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_GUARANTOR_MAIN\" value=\"Delete\" onClick=\"delete_guarantor('+m_dir_count+')\" ></td>'+");
			out.println("        '<td width=\"*%\" class=div_input></td>'+ ");
			out.println("		 '</tr>'+"); 
			out.println("		 '</table>';"); 
			out.println("		 document.Form1.hid_guarantor_count.value=m_dir_count;");
			out.println("}"); 
			
			out.println("function delete_guarantor(mnum){");
			
			
			out.println("	if(parseInt(mnum)>0){");
			out.println("		 m_dir_count=parseInt(document.Form1.hid_guarantor_count.value);");
			out.println("    m_count=0;");
			out.println("    m_str=\"\";");
			out.println("    for(k=1;k<=m_dir_count;k++){");
			
			out.println("    	if(k!=mnum){");
			out.println("		 		m_count++;");
			out.println("    		obj1=document.Form1.elements[\"TXT_GAURANTOR_CODE\"+k].value;");
			out.println("    		obj2=document.Form1.elements[\"TXT_GAURANTOR_NAME\"+k].value;");
			out.println("		 		m_str=m_str+'<table  width=\"100%\">'+"); 
			out.println("		 		'<tr>'+"); 
			out.println("               '<td width=\"1%\" class=div_input></td>'+ ");
			out.println("		 		'<td width=\"25%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_GAURANTOR_CODE'+m_count+'\" style=\"width: 130px\"  maxlength=\"100\" value=\"'+obj1+'\">'+"); 
			out.println("               '<input class=\"but_input\" type=\"button\" name=BUT_TXT_GAURANTOR_CODE_HELP'+m_count+' value=\"Help\" onClick=\"help_button_5('+m_count+')\"></td>'+");
			out.println("		 		'<td width=\"25%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_GAURANTOR_NAME'+m_count+'\" style=\"width: 130px\"  maxlength=\"100\" value=\"'+obj2+'\" disabled>'+");
			out.println("		        '<input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_GUARANTOR_MAIN\" value=\"Delete\" onClick=\"delete_guarantor('+m_count+')\" ></td>'+");
			out.println("               '<td width=\"*%\" class=div_input></td>'+ ");
			out.println("		 		'</tr>'+"); 
			out.println("		 		'</table>';");
			out.println("    	}");
			out.println("	 	 }");
			out.println("		 guarantor_detail_data.innerHTML=m_str;");
			out.println("		 document.Form1.hid_guarantor_count.value=m_count;");
			out.println("	}");
			out.println("}");
			
			//---------
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
			
			out.println("function clear_data(){"); 
			out.println("		document.Form1.TXT_FACILITY_NO.value=\"\";");
			out.println("		document.Form1.TXT_CLIENT_CODE.value=\"\";");
			out.println("		document.Form1.TXT_CLIENT_NAME.value=\"\";");
			out.println("		document.Form1.TXT_CLIENT_CMGR.value=\"\";");
			out.println("		document.Form1.TXT_CLIENT_MMGR.value=\"\";");
			out.println("		document.Form1.TXT_FACILITY_MANAGER.value=\"\";");
			out.println("		document.Form1.TXT_FACILITY_MANAGER_NAME.value=\"\";");
			out.println("		document.Form1.TXT_START_DD.value=\"\";");
			out.println("		document.Form1.TXT_START_MM.value=\"\";");
			out.println("		document.Form1.TXT_START_YY.value=\"\";");
			out.println("		document.Form1.TXT_END_DD.value=\"\";");
			out.println("		document.Form1.TXT_END_MM.value=\"\";");
			out.println("		document.Form1.TXT_END_YY.value=\"\";");
			out.println("		document.Form1.TXT_PRODUCT_CODE.value=\"\";");
			out.println("		document.Form1.TXT_PRODUCT_DESC.value=\"\";");
			out.println("		document.Form1.TXT_FEE_CODE.value=\"\";");
			out.println("		document.Form1.TXT_FEE_DESC.value=\"\";");
			out.println("	  document.Form1.TXT_FACILITY_NO.value=\"\";"); 
			out.println("		document.Form1.TXT_CREDIT_LIMIT.value=\"\";");
			out.println("		document.Form1.TXT_CREDIT_PERIOD.value=\"\";");
			out.println("		document.Form1.TXT_TOL_CREDIT_PERIOD.value=\"\";");
			out.println("	  document.Form1.TXT_RES_MARGIN.value=\"\";");
			out.println("	  document.Form1.TXT_INT_RATE.value=\"\";");
			out.println("	  document.Form1.TXT_COMMENTS.value=\"\";");
			out.println("		product_detail_data.innerHTML=\"\";");
			out.println("		fee_detail_data.innerHTML=\"\";");
			out.println("		guarantee_detail_data.innerHTML=\"\";");
			out.println("		document.Form1.hid_product_count.value=0;");
			out.println("		document.Form1.hid_fee_count.value=0;");
			out.println("		document.Form1.hid_guarantee_count.value=0;");
			out.println("		document.Form1.hid_guarantor_count.value=0;");
			out.println("}");
			
			
			out.println("function validate_data(){"); 
			out.println("	if(document.Form1.TXT_CLIENT_CODE.value==\"\" ){  "); 
			out.println("		DIV_TXT_CLIENT_CODE.style.color='red';");
			out.println("		return false;"); 
			out.println("	}"); 
			out.println("	else if(document.Form1.TXT_FACILITY_NO.value==\"\" && document.Form1.SCREEN_NAME.value==\"EDIT\"){  "); 
			out.println("		DIV_TXT_FACILITY_NO.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	else if(document.Form1.TXT_FACILITY_MANAGER.value==\"\"){  "); 
			out.println("		DIV_TXT_FACILITY_MANAGER.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	else if(document.Form1.TXT_START_DD.value==\"\"){  "); 
			out.println("		DIV_TXT_CLIENT_START_DATE.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	else if(document.Form1.TXT_START_MM.value==\"\"){  "); 
			out.println("		DIV_TXT_CLIENT_START_DATE.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	else if(document.Form1.TXT_START_YY.value==\"\"){  "); 
			out.println("		DIV_TXT_CLIENT_START_DATE.style.color='red';");
			out.println("		return false;"); 
			out.println("	}"); 
			out.println("	else if(document.Form1.TXT_END_DD.value==\"\"){  "); 
			out.println("		DIV_TXT_CLIENT_END_DATE.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	else if(document.Form1.TXT_END_MM.value==\"\"){  "); 
			out.println("		DIV_TXT_CLIENT_END_DATE.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	else if(document.Form1.TXT_END_YY.value==\"\"){  "); 
			out.println("		DIV_TXT_CLIENT_END_DATE.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
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
			out.println("				document.Form1.action='"+m_class_url+"/"+m_fschema_name+"FA_CR_Save_client_facility_creation';");  
			out.println("				document.Form1.submit();	"); 
			out.println("			}"); 
			out.println("		}"); 
			out.println("	}"); 
			out.println("		else { "); 
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		}");
			out.println("} "); 
			
			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_CR_display_client_facility';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_CR_display_client_facility';"); 
			out.println("}"); 
			
			out.println("function save_window(){	"); 
			out.println("	before_submit();"); 
			out.println("}"); 
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_FA_CR_CLIENT_FACILITY_CREATION\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
			
			out.println("function load_roll_value(m_val){"); 
			out.println("	help_box.innerHTML=\"  Credit Process - Client Facility Creation - \"+m_val;"); 
			out.println("}"); 
			
			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\"  Credit Process - Client Facility Creation - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function load_screen_status(m_val){"); 
			out.println("		if(m_val==\"NEW\"){"); 
			//out.println("			new_window();"); 
			out.println("		}"); 
			out.println("		else if(m_val==\"HELP\"){"); 
			out.println("			load_help_msg();"); 
			out.println("		}"); 
			out.println("		document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("		if(m_val==\"NEW\"){");
			out.println("		 if(confirm(\"Are you sure you want enter New record ?\")){ ");
			out.println("			new_window();"); 
			out.println("			document.Form1.BUT_HELP_MAIN_1.disabled=true;");
			out.println("	 		document.Form1.TXT_FACILITY_NO.disabled=true;");
			out.println("			document.Form1.hid_status.value=\"New\";"); 
			out.println("		 }");
			out.println("		}");
			out.println("		else if(m_val==\"EDIT\"){");  
			out.println("		 if(confirm(\"Are you sure you want to Modify a record ?\")){ ");
			out.println("			document.Form1.BUT_HELP_MAIN_1.disabled=false;");
			out.println("	 		document.Form1.TXT_FACILITY_NO.disabled=false;");
			out.println("			document.Form1.hid_status.value=\"Edit\";");  
			out.println("			clear_data();");
			out.println("		 }");
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
			
			out.println("function clear_fields(){"); 
			out.println("	 if(document.Form1.hid_help_type.value==\"1\") {" ); 
			out.println("		document.Form1.TXT_QUOTATION_NO.value='';"); 
			out.println("		document.Form1.BUT_HELP_PRODUCT_MAIN_ADD.disabled=true;");
			out.println("		document.Form1.BUT_HELP_FEE_MAIN_ADD.disabled=true;");
			out.println("		product_detail_data.innerHTML=\"\";");
			out.println("		document.Form1.hid_product_count.value=0;");
			out.println("		fee_detail_data.innerHTML=\"\";");
			out.println("		document.Form1.hid_fee_count.value=0;");
			out.println("		document.Form1.hid_guarantee_count.value=0;");
			out.println("		document.Form1.hid_guarantor_count.value=0;");
			out.println("		clear_data();");
			out.println("  }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"2\") {" ); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value='';"); 
			out.println("		document.Form1.TXT_CLIENT_NAME.value='';"); 
			out.println("		document.Form1.TXT_CLIENT_CMGR.value='';"); 
			out.println("		document.Form1.TXT_CLIENT_MMGR.value='';"); 
			out.println("		document.Form1.TXT_FACILITY_MANAGER.value='';"); 
			out.println("		document.Form1.TXT_FACILITY_MANAGER_NAME.value='';"); 
			out.println("  }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"3\") {" ); 
			out.println("		document.Form1.TXT_FACILITY_MANAGER.value='';"); 
			out.println("		document.Form1.TXT_FACILITY_MANAGER_NAME.value='';"); 
			out.println("  }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"4\") {" ); 
			out.println("		product_detail_data.innerHTML=\"\";");
			out.println("		document.Form1.hid_product_count.value=0;");
			out.println("		document.Form1.TXT_PRODUCT_CODE.value='';"); 
			out.println("		document.Form1.TXT_PRODUCT_DESC.value='';"); 
			out.println("		document.Form1.BUT_HELP_PRODUCT_MAIN_ADD.disabled=true;");
			out.println("  }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"5\") {" ); 
			out.println("		fee_detail_data.innerHTML=\"\";");
			out.println("		document.Form1.hid_fee_count.value=0;");
			out.println("		document.Form1.TXT_FEE_CODE.value='';"); 
			out.println("		document.Form1.TXT_FEE_DESC.value='';"); 
			out.println("		document.Form1.BUT_HELP_FEE_MAIN_ADD.disabled=true;");
			out.println("  }		"); 
			
			out.println("	 else	if(document.Form1.hid_help_type.value==\"8\") {" ); 
			out.println("		if(document.Form1.SCREEN_NAME.value == \"NEW\") {");
			out.println("		document.Form1.TXT_QUOTATION_NO.value='';"); 
			out.println("		document.Form1.BUT_HELP_PRODUCT_MAIN_ADD.disabled=true;");
			out.println("		document.Form1.BUT_HELP_FEE_MAIN_ADD.disabled=true;");
			out.println("		product_detail_data.innerHTML=\"\";");
			out.println("		document.Form1.hid_product_count.value=0;");
			out.println("		fee_detail_data.innerHTML=\"\";");
			out.println("		document.Form1.hid_fee_count.value=0;");
			out.println("		clear_data();");
			out.println("   }		"); 
			out.println("		else {");
			out.println("		document.Form1.TXT_QUOTATION_NO.value='';"); 
			out.println("   }		"); 
			out.println("  }		"); 
			out.println("}		"); 
			
			
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"FA_MAS_Help_Servlet?class_in=\"+client_name+\"FA_CR_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_fields()");
			out.println("	 } else "); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("		if(oBj.valout[1] !=\"Close\"){"); 
			out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("				if(oBj.valout[1]!=\"Next\"){"); 
			out.println("					if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("						help_update_value_1();"); 
			out.println("					}"); 
			out.println("					else if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("						help_update_value_2();"); 
			out.println("					}"); 
			out.println("					else if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("						help_update_value_3();"); 
			out.println("					}"); 
			out.println("					else if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("						help_update_value_4();"); 
			out.println("					}"); 
			out.println("					else if(document.Form1.hid_help_type.value==\"5\"){"); 
			out.println("						help_update_value_5();"); 
			out.println("					}"); 
			out.println("					else if(document.Form1.hid_help_type.value==\"6\"){"); 
			out.println("						help_update_value_6();"); 
			out.println("					}"); 
			out.println("					else if(document.Form1.hid_help_type.value==\"7\"){"); 
			out.println("						help_update_value_7();"); 
			out.println("					}"); 
			out.println("					else if(document.Form1.hid_help_type.value==\"8\"){"); 
			out.println("						help_update_value_8();"); 
			out.println("					}");
			out.println("					else if(document.Form1.hid_help_type.value==\"9\"){"); 
			out.println("		                help_update_value_9(document.Form1.hid_row_no.value,oBj);"); 
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
			out.println("    clear_fields(); ");
			out.println("	}	"); 
			out.println("	}"); 
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
			
			//Added by Mahela on 15-12-2006
			out.println("function load_calendar(num) {");
			out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
			out.println("}");
			
			out.println("function load_c_date(val) {");
			out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_END_DD.value=v_dd;");
			out.println("     document.Form1.TXT_END_MM.value=v_mm;");
			out.println("     document.Form1.TXT_END_YY.value=v_yy;");
			out.println("  }");		
			out.println("  else if(document.Form1.hid_cal_date.value=='1'){"); 	
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_START_DD.value=v_dd;");
			out.println("     document.Form1.TXT_START_MM.value=v_mm;");
			out.println("     document.Form1.TXT_START_YY.value=v_yy;");
			out.println("  }");		
			out.println("}");				
			//End of Addition
			
			out.println("function help_update_facility() {"); 
			out.println(" document.Form1.hid_help_type.value=\"1\";"); 
			out.println(" m_sql = \"m_help_DIV_TXT_FACILITY_sql\";"); 
			out.println(" m_criteria = document.Form1.TXT_FACILITY_NO.value+\"@\";");
			out.println(" HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_1() {"); 
			out.println("		document.Form1.TXT_FACILITY_NO.value=oBj.valout[2];"); 
			out.println("		makeRequest(3);");
			out.println("}");
			
			out.println("function help_update_client() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"2\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_CLIENT_CODE_FACILITY_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_2() {"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];"); 
			out.println("		document.Form1.TXT_CLIENT_CMGR.value=oBj.valout[4];"); 
			out.println("		document.Form1.TXT_CLIENT_MMGR.value=oBj.valout[5];"); 
			out.println("		document.Form1.TXT_FACILITY_MANAGER.value=oBj.valout[6];"); 
			out.println("		document.Form1.TXT_FACILITY_MANAGER_NAME.value=oBj.valout[4];"); 
			out.println("		makeRequest(11);");
			out.println("}"); 
			
			out.println("function help_client_manager() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"3\";"); 
			out.println(" 	m_sql=\"m_help_DIV_TXT_CR_CLIENT_MGT_sql\";"); 
			out.println(" 	m_criteria=document.Form1.TXT_FACILITY_MANAGER.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}");
			
			out.println("function help_update_value_3() {"); 
			out.println("		document.Form1.TXT_FACILITY_MANAGER.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_FACILITY_MANAGER_NAME.value=oBj.valout[3];"); 
			out.println("}"); 
			
			out.println("function help_update_product() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"4\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_PRODUCT_FACILITY_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_PRODUCT_CODE.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}"); 
			
			
			
			//Added by disnaka Jayasuriya 2011-09-28
			out.println("function help_button_5(rowNo) {"); 
			out.println("m_gur_code=\"TXT_GAURANTOR_CODE\"+rowNo;");
			out.println("    document.Form1.hid_help_type.value=\"9\";"); 
			out.println("    document.Form1.hid_row_no.value=rowNo;"); 
			out.println("    m_sql = \"m_help_TXT_GURANTOR\";"); 
			out.println("    m_criteria =document.Form1.elements[m_gur_code].value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','2');"); 
			out.println("}"); 
			
			//ADDED BY DISNAKA ON 2011-10-05
			out.println("function help_update_value_9(rowNo,oBj) {"); 

			out.println("     var gaurantorExsist = 1;"); 
			out.println("     for(k=1;k<=parseInt(document.Form1.hid_guarantor_count.value);k++){");
			out.println("           if(oBj.valout[2]==document.Form1.elements[\"TXT_GAURANTOR_CODE\"+k].value){");
			out.println("              alert('Gaurantor Already Exist');");
			out.println("              gaurantorExsist = 0;");
			out.println("              break;");
			out.println("            }"); 
			out.println("     }"); 

			out.println("    if(gaurantorExsist == 1){");
            out.println(" 		m_gur_code=\"TXT_GAURANTOR_CODE\"+rowNo;");
			out.println(" 		m_gur_name=\"TXT_GAURANTOR_NAME\"+rowNo;");
			out.println("    	document.Form1.elements[m_gur_code].value=oBj.valout[2];"); 
			out.println("    	document.Form1.elements[m_gur_name].value=oBj.valout[3];"); 
	        out.println("    }"); 
			
			out.println("}"); 
			
			//--
			
	
			//------------------------------------
			out.println("function help_update_value_4() {"); 
			out.println("		product_detail_data.innerHTML=\"\";");
			out.println("		document.Form1.hid_product_count.value=0;");
			out.println("		document.Form1.TXT_PRODUCT_CODE.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_PRODUCT_DESC.value=oBj.valout[3];"); 
			out.println("		document.Form1.BUT_HELP_PRODUCT_MAIN_ADD.disabled=true;");
			out.println("		makeRequest(1);");
			out.println("}"); 
			
			out.println("function help_update_fee() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"5\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_FEE_FACILITY_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_FEE_CODE.value+\"@\";");
			out.println("	  HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_5() {"); 
			out.println("		fee_detail_data.innerHTML=\"\";");
			out.println("		document.Form1.hid_fee_count.value=0;");
			out.println("		document.Form1.TXT_FEE_CODE.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_FEE_DESC.value=oBj.valout[3];"); 
			out.println("		document.Form1.BUT_HELP_FEE_MAIN_ADD.disabled=true;");
			out.println("		makeRequest(2);");
			out.println("}"); 
			
			out.println("function help_products_feature(m_num) {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"6\";"); 
			out.println(" 	document.Form1.hid_help_count.value=m_num;"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_PRODUCT_FEATURE_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_PRODUCT_CODE.value+\"@\";");
			out.println("	  HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_6() {"); 
			out.println(" 	m_num=document.Form1.hid_help_count.value;"); 
			out.println("  	document.Form1.elements[\"TXT_PRODUCT_FEATURE_CODE_\"+m_num].value=oBj.valout[2];");
			out.println("  	document.Form1.elements[\"TXT_PRODUCT_FEATURE_DESC_\"+m_num].value=oBj.valout[3];");
			out.println("  	document.Form1.elements[\"TXT_PRODUCT_FEATURE_COMMENT_\"+m_num].value=oBj.valout[4];");
			out.println("  	document.Form1.elements[\"TXT_PRODUCT_FEATURE_PARAM_\"+m_num].value=oBj.valout[5];");
			out.println("}"); 
			
			out.println("function help_fee_feature(m_num) {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"7\";"); 
			out.println(" 	document.Form1.hid_help_count.value=m_num;"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_FEE_FEATURE_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_FEE_CODE.value+\"@\";");
			out.println("	  HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_7() {"); 
			out.println(" 	m_num=document.Form1.hid_help_count.value;"); 
			out.println("  	document.Form1.elements[\"TXT_FEE_CODE_\"+m_num].value=oBj.valout[2];");
			out.println("  	document.Form1.elements[\"TXT_FEE_DESC_\"+m_num].value=oBj.valout[3];");
			out.println("  	document.Form1.elements[\"TXT_FEE_TYPE_\"+m_num].value=oBj.valout[4];");
			out.println("  	document.Form1.elements[\"TXT_FEE_VALUE_\"+m_num].value=oBj.valout[5];");
			out.println("}"); 
			
			out.println("function help_update_quotation(){");
			out.println(" 	document.Form1.hid_help_type.value=\"8\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_QUOTATION_FACILITY_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_QUOTATION_NO.value+\"@\";");
			out.println("	  HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_8() {"); 
			out.println("		document.Form1.TXT_QUOTATION_NO.value=oBj.valout[2];"); 
			out.println("		document.Form1.BUT_HELP_PRODUCT_MAIN_ADD.disabled=true;");
			out.println("		document.Form1.BUT_HELP_FEE_MAIN_ADD.disabled=true;");
			out.println("		makeRequest(7);");
			out.println("}"); 
			
			out.println("function print_facility_app(){");
			out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_Application_approval?facility_no=\"+document.Form1.TXT_FACILITY_NO.value;");
			out.println("	window.open(m_url);"); 
			out.println("}");
			
			out.println("</script>"); 
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock();\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"99\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_product_count' VALUE=\"0\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_fee_count' VALUE=\"0\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_guarantee_count' VALUE=\"0\">");
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_guarantor_count' VALUE=\"0\">");
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_display_client' VALUE=\"99\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_count' VALUE=\"0\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Credit Process - Client Facility Creation </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			//out.println("<td width='10%' align='center'></td>");
			//out.println("<td width='10%' align='center'></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Approval Sheet\");'  onClick='print_facility_app()' style='width:150' value=\"Approval Sheet\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");   
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			
			out.println("<br>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_FACILITY_NO'  class=div_input>Facility No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FACILITY_NO' maxlength='10' size='10' onblur=\"help_update_facility()\" disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_1' value=\"Help\" onClick=\"help_update_facility()\" disabled></td>"); 
			out.println("<td width='5%'></td>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_QUOTATION_NO'  class=div_input>Quotation No </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_QUOTATION_NO' maxlength='10' size='10' onblur=\"help_update_quotation()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_2' value=\"Help\" onClick=\"help_update_quotation()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>");
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' onblur=\"help_update_client()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_client()\">");
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Details\" onClick=\"show_client(document.Form1.TXT_CLIENT_CODE.value)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_DESC'  class=div_input>Client Name</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' maxlength='10' size='50' style='width:200' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_DESC'  class=div_input>Client Manager</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_CMGR' maxlength='10' size='50'  style='width:200' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_DESC'  class=div_input>Client Mkt. Executive</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_MMGR' maxlength='10' size='50' style='width:200' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_FACILITY_MANAGER'  class=div_input>Facility Manager *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FACILITY_MANAGER' onblur='help_client_manager()' maxlength='50' size='50'><input class=\"but_input\" type=\"button\" name=\"BUT_CLIENT_MGT\" value=\"Help\" onClick=\"help_client_manager()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_FACILITY_MANAGER_NAME'  class=div_input>Facility Manager Name*</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FACILITY_MANAGER_NAME' maxlength='50' size='50'  style='width:200' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width=\"20%\"><DIV id=\"DIV_TXT_CLIENT_START_DATE\"  class=div_input>Start Date *</DIV></td>");
			out.println("<td width=\"30%\"><input class=\"txt_input5\" type=\"text\" name=\"TXT_START_DD\" maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_START_MM\" maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_START_YY\" maxlength=\"4\" size=\"4\" onblur=\"checkMonthLength(document.Form1.TXT_START_DD,document.Form1.TXT_START_MM,document.Form1.TXT_START_YY)\"><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width=\"20%\"><DIV id=\"DIV_TXT_CLIENT_END_DATE\"  class=div_input>End Date *</DIV></td>");
			out.println("<td width=\"30%\"><input class=\"txt_input5\" type=\"text\" name=\"TXT_END_DD\" maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_END_MM\" maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_END_YY\" maxlength=\"4\" size=\"4\" onblur=\"checkMonthLength(document.Form1.TXT_END_DD,document.Form1.TXT_END_MM,document.Form1.TXT_END_YY)\"><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a></td>");
			out.println("<td width=\"*%\"></td>");
			out.println("</tr>");
			out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CREDIT_LIMIT'  class=div_input>Total Facility Credit Limit *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CREDIT_LIMIT' maxlength='25' size='50' style='width:200;text-align:right;' value='"+nf.format(credit_limit)+"'  onchange=format_num(document.Form1.TXT_CREDIT_LIMIT,4)></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");	
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CREDIT_PERIOD'  class=div_input>Facility Credit Period *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CREDIT_PERIOD' maxlength='25' size='50' style='width:200;text-align:right;'  value='"+nf.format(credit_period)+"'  onchange=format_num(document.Form1.TXT_CREDIT_PERIOD,4)></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_TOL_CREDIT_PERIOD'  class=div_input>Facility Tolerance Credit Period *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_TOL_CREDIT_PERIOD' maxlength='25' size='50' style='width:200;text-align:right;' value='"+nf.format(credit_tol_period)+"'  onchange=format_num(document.Form1.TXT_TOL_CREDIT_PERIOD,4)></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_RES_MARGIN'  class=div_input>Facility Reserve Margin Value (%) *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_RES_MARGIN' maxlength='25' size='25' style='width:200;text-align:right;'   value='"+nf.format(reserve_margin)+"'  onchange=format_num(document.Form1.TXT_RES_MARGIN,4)></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_INT_RATE'  class=div_input>Interest Rate (%) *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_INT_RATE' maxlength='25' size='25' style='width:200;text-align:right;'  value='"+nf.format(int_rate)+"'  onchange=format_num(document.Form1.TXT_INT_RATE,4)></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_COMMENTS'  class=div_input>Comments</DIV></td>"); 
			out.println("<td width='430%' ><TEXTAREA class='txt_input' name='TXT_COMMENTS' style='width:550px' style='height:100px' maxlength='1000' ></TEXTAREA></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' class='table'>");
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_PRODUCT_CODE'  class=div_input>Product Package Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_PRODUCT_CODE' maxlength='10' size='10' onblur=\"help_update_product()\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_product()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_PRODUCT_DESC'  class=div_input>Product Package Description </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_PRODUCT_DESC' maxlength='50' size='50' style='width:300' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>");
			out.println("<tr>"); 
			out.println("<td width='20%' ><input class=\"but_input\" type=\"button\" name=\"BUT_HELP_PRODUCT_MAIN_ADD\" value=\"Add\" onClick=\"add_products()\" disabled></td>"); 
			out.println("<td width='30%' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>");
			out.println("<tr>"); 
			out.println("<td width='100%'><DIV id='product_detail_data'  class=div_input></DIV></td>");
			out.println("</tr>");
			out.println("</table>"); 
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%' class='table'>");
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_FEE_CODE'  class=div_input>Fee Package Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FEE_CODE' maxlength='10' size='10' onblur=\"help_update_fee()\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_fee()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_FEE_DESC'  class=div_input>Fee Package Description </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FEE_DESC' maxlength='50' size='50' style='width:300' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>"); 
			out.println("<table align='center' width='100%' class='table'>");
			out.println("<tr>"); 
			out.println("<td width='20%' ><input class=\"but_input\" type=\"button\" name=\"BUT_HELP_FEE_MAIN_ADD\" value=\"Add\" onClick=\"add_fee()\" disabled></td>"); 
			out.println("<td width='30%' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>");
			out.println("<tr>"); 
			out.println("<td width='100%'><DIV id='fee_detail_data'  class=div_input></DIV></td>");
			out.println("</tr>");
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_GUARANTEE'  class=div_input><b>Bank Guarantee/Personal Guarantee<b></DIV></td>"); 
			out.println("<td width='30%' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");	
			out.println("<tr >"); 
			out.println("<td width='20%' ><input class=\"but_input\" type=\"button\" name=\"BUT_HELP_GUARANTEE_MAIN_ADD\" value=\"Add\" onClick=\"add_guarantee()\"></td>"); 
			out.println("<td width='30%' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");	
			out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>");
			out.println("<tr>"); 
			out.println("<td width=\"1%\" class=div_input></td>"); 
			out.println("<td width=\"15%\" class=div_input>Guarantee Name</td>"); 
			out.println("<td width=\"15%\" class=div_input>Bank/Personal Name</td>"); 
			out.println("<td width=\"15%\" class=div_input>Contact Person</td>");
			out.println("<td width=\"10%\" class=div_input>Start Date</td>");
			out.println("<td width=\"10%\" class=div_input>End Date</td>");
			out.println("<td width=\"15%\" class=div_input>Value (Rs.)</td>");
			out.println("<td width=\"*%\" class=div_input>Comment</td>");
			out.println("</tr>");
			out.println("</table>");
			
			out.println("<table>"); 
			out.println("<table align='center' width='100%' class='table'>");
			out.println("<tr>"); 
			out.println("<td width='100%'><DIV id='guarantee_detail_data'  class=div_input></DIV></td>");
			out.println("</tr>");
			out.println("</table>"); 
			
			//Added By Disnaka Jayasuriya on 2011-09-28
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_GUARANTOR'  class=div_input><b>Guarantors<b></DIV></td>"); 
			out.println("<td width='30%' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");	
			out.println("<tr >"); 
			out.println("<td width='20%' ><input class=\"but_input\" type=\"button\" name=\"BUT_HELP_GUARANTOR_MAIN_ADD\" value=\"Add\" onClick=\"add_guarantor()\"></td>"); 
			out.println("<td width='30%' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");	
			out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>");
			out.println("<tr>"); 
			out.println("<td width=\"1%\" class=div_input></td>"); 
			out.println("<td width=\"25%\" class=div_input>Code</td>"); 
			out.println("<td width=\"25%\" class=div_input>Name</td>");
			out.println("<td width=\"*\" class=div_input></td>");
			out.println("</tr>");
			out.println("</table>");
			
			out.println("<table>"); 
			out.println("<table align='center' width='100%' class='table'>");
			out.println("<tr>"); 
			out.println("<td width='100%'><DIV id='guarantor_detail_data'  class=div_input></DIV></td>");
			out.println("</tr>");
			out.println("</table>"); 
			
			
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
