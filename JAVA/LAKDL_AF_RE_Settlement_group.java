import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 

import java.util.*;

import oracle.jdbc.driver.*;

public class LAKDL_AF_RE_Settlement_group extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs_grp1;
	public String m_chksql,m_sysdate;
	ServletOutputStream out = null;
	java.lang.Math ab;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			
			LAKDL_AF_CO_FU_methods CO_methods = new LAKDL_AF_CO_FU_methods();		
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_username 						= con_method.username;
			String header_name    = con_method.header_name;
			String m_rep_cur=""; 
			
			String m_fschema_name=con_method.client_name.trim();
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
			
			
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
			nf.setMaximumFractionDigits(2);
			
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
				
				String m_Followu_no   = ""; 
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Asset Financing System</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				
				out.println(" var m_rental_other_inv=0;");
				out.println(" var m_insurance_premium=0;");
				out.println(" var m_lux_tax=0;");
				out.println(" var m_revenue_lux=0;");
				out.println(" var m_rmv_reg_fee=0;");
				out.println(" var m_tot=0;");
				out.println(" var m_balance=0;");
				out.println(" var m_tendered=0;");
				out.println(" var m_amount_entered=0;");
				out.println(" var m_untot=1");
				out.println(" var m_store=0");
				out.println(" var m_true=''");
				out.println(" var m_untot1=1");			
				
				out.println("function show_document() {");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_group_Receipt_Document\";");
				out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=650,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Settlement_group?chksql=main_page';"); 
				out.println("}");
				
				
				
				out.println("function makeRequest(url,opt,type) {");
				out.println("var http_request = false;");
				out.println("if (window.XMLHttpRequest) {"); // Mozilla, Safari,...
				out.println("    http_request = new XMLHttpRequest();");
				out.println("    if (http_request.overrideMimeType) {");
				out.println("        http_request.overrideMimeType('text/xml');");
				out.println("    }");
				out.println("} else if (window.ActiveXObject) { ");// IE
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
				out.println(" if(opt=='1'){");
				out.println("  http_request.onreadystatechange = function() { alertContents(http_request); };");
				out.println("  http_request.open('POST',url, true);");
				out.println("	 http_request.setRequestHeader(\"Content-Type\",\"application/x-www-form-urlencoded\");");
				out.println("	 m_send_val = \"chksql=get_advance_price_cal&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+");
				out.println("	              \"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&option=\"+opt+");
				out.println("	              \"&last_rent=&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+");
				out.println("	              \"&supcrper=0&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+unformat_noobject(document.Form1.NIBSM.value)+");
				out.println("	              \"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&installments=\";");
				out.println("  http_request.send(m_send_val);");
				out.println(" }else{");
				out.println("  http_request.onreadystatechange = function() { alertGetContents(http_request,opt,type); };");
				out.println("  http_request.open('GET',url, true);");
				out.println("  http_request.send(null);");
				out.println(" }");
				out.println("}");
				
				out.println("function alertContents(http_request,type) {");
				out.println(" if (http_request.readyState == 4) {");
				out.println("    if (http_request.status == 200) {");
				out.println("         price_cal.innerHTML=http_request.responseText; ");
				out.println("    } else {");
				out.println("        alert('There was a problem with the request.');");
				out.println("    }");
				out.println(" }");
				out.println("}");
				
				out.println("function addrow( data,type) {");
				out.println(" str=\"\";");
				out.println(" i=0;");
				out.println(" if(data.length>0){");
				out.println("   if(type=='Rec'){"); 
				out.println("     document.Form1.TERMINATION_NO.value       =data[0];"); 
				
				out.println("   }else if(type=='ChequeDate'){"); 
				out.println("     document.Form1.CHEQUE_DATE_DD.value     =data[0];"); 
				out.println("     document.Form1.CHEQUE_DATE_MM.value     =data[1];"); 
				out.println("     document.Form1.CHEQUE_DATE_YY.value     =data[2];");
				
				out.println("   }else if(type=='Cli'){"); 
				out.println("     document.Form1.CLIENT_CODE.value     =data[0];"); 
				out.println("     document.Form1.CLIENT_NAME.value     =data[1];"); 
				
				
				out.println("if(data[4]!='-' && data[5]!='-' && data[6]!='-' ){");
				out.println("address=data[4]+','+data[5]+','+data[6]; ");		
				out.println("}");		
				out.println("else if(data[4]!='-' && data[5]!='-' && data[6]=='-' ){");
				out.println("address=data[4]+','+data[5]; ");		
				out.println("}");		
				out.println("else if(data[4]!='-' && data[5]=='-' && data[6]!='-' ){");
				out.println("address=data[4]+','+data[6]; ");		
				out.println("}");		
				out.println("else if(data[4]=='-' && data[5]=='-' && data[6]=='-' ){");
				out.println("address=data[6]; ");		
				out.println("}");		
				out.println("else if(data[4]=='-' && data[5]!='-' && data[6]=='-' ){");
				out.println("address=data[5]; ");		
				out.println("}");		
				
				out.println(" document.Form1.CLIENT_ADDRESS.value =address");
				out.println("set_address_pay_type();"); 
				out.println("   }else if(type=='ExcRate'){"); 
				out.println("     if(document.Form1.hid_rep_cur.value==document.Form1.CURR_CODE.value){");
				out.println("     document.Form1.EXCHANE_RATE.value        =\"1\";"); 
				out.println("     document.Form1.EXCHANE_RATE.disabled =true;"); 	
				out.println("     }");	
				out.println("     else{");
				out.println("     document.Form1.EXCHANE_RATE.value        =data[0];"); 
				out.println("     document.Form1.EXCHANE_RATE.disabled =false;"); 	
				out.println("     }");		
				out.println("   }else if(type=='ChExcRate'){"); 
				out.println("     document.Form1.EXCHANE_RATE.value    =data[1];"); 
				out.println("     document.Form1.REP_AMOUNT.value      =data[0];"); 
				out.println("   }else if(type=='RepAmt'){");  
				out.println("     document.Form1.REP_AMOUNT.value        =data[0];"); 
				out.println("     get_invoice();");
				out.println("   }else if(type=='SysDate'){");			 	
				out.println("     document.Form1.VAL_DAY.value         =data[0];"); 
				out.println("     document.Form1.VAL_MONTH.value       =data[1];"); 
				out.println("     document.Form1.VAL_YEAR.value        =data[2];"); 
				out.println("     get_excharate();");
				
				out.println("   }else if(type=='Receipt'){"); 
				out.println("     document.Form1.RECEIPT_NO.value      =data[0];"); 
				out.println("     document.Form1.VAL_DAY.value         =data[1];"); 
				out.println("     document.Form1.VAL_MONTH.value       =data[2];"); 
				out.println("     document.Form1.VAL_YEAR.value        =data[3];"); 
				
				out.println("     document.Form1.CURR_CODE.value       =data[5];"); 
				out.println("     document.Form1.AMOUNT.value          =data[6];"); 
				
				out.println("     document.Form1.PAY_TYPE.value   =\"THIRD\";"); 	
				out.println("     display_row_third_party(); ");				
				out.println("     document.Form1.CLIENT_NAME_1.value   =data[25];"); 
				out.println("     document.Form1.CLIENT_ADDRESS_1.value=data[26];"); 	
				out.println("     document.Form1.CLIENT_NAME_1.disabled =true;");	
				out.println("     document.Form1.CLIENT_ADDRESS_1.disabled =true;");	
				out.println("     document.Form1.BUT_GROUP_HELP.disabled =true;");	
				out.println("     document.Form1.EXCHANE_RATE.value    =data[7];"); 
				out.println("     document.Form1.REP_AMOUNT.value      =data[8];"); 
				out.println("     document.Form1.SETT_MODE.value       =data[9];"); 
				out.println("     display_row_cheque(data_vec);");				
				out.println("		 if(data[10] == '' || data[10]== 'null' ){");	
				//out.println("     document.Form1.PAY_BRANCH.value      ='';"); 	
				out.println("		 }else {");	
				out.println("     document.Form1.PAY_BRANCH.value      =data[10];");
				out.println("     document.Form1.PAY_BRANCH_NAME.value      =data[24];");	
				out.println("      } ");
				out.println("		 if(data[11] == '' || data[11]== 'null' ){");		
				//out.println(" 		 document.Form1.PAY_ACCOUNT.value     ='';	");	
				out.println(" 		 }else");	
				out.println("     document.Form1.PAY_ACCOUNT.value     =data[11];");
				
				out.println("		 if(data[13] == '' || data[13]== 'null' )");	
				out.println("     document.Form1.REMARK.value          ='';");
				out.println("		 else");		
				out.println("     document.Form1.REMARK.value          =data[13];");	
				out.println("     if(data[9]=='STD_ORD' || data[9]=='DIR_DEP'){"); 
				out.println("m_table_account_no.innerHTML='<table align=\"left\" width=\"100%\" class=\"table\" border=\"0\"><tr ID=T_ID >'+");		//class=tr_input
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">Account Number *</TD>'+");
				out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ACCOUNT_NO maxlength=\"10\" size=\"10\" value=\"\" onblur=\"makeRequest_account_no(this)\" >'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_ACCOUNT_NO  value=\"Help\" onClick=\"account_number_help()\"></TD>'+");
				out.println("'<td  >Branch Name</td>'+");
				out.println("'<td  ><input name=\"BRANCH_NAME\" type=\"text\" style=\"width:250px;\" maxlength=\"200\" class=\"txt_input\" disabled></td>'+");
				out.println("'</tr></table>';");
				out.println("}");
				
				out.println("else if(data[9]=='CASH' ){"); 
				out.println("m_table_tendered.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr ID=T_ID class=tr_input>'+");		
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">Tendered Amount</TD>'+");
				out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TEN_AMOUNT maxlength=\"23\" value=\"'+data[17]+'\"  size=\"25\" onchange =\"calculate_balance(this)\" onblur=\"get_returned_value(this,25)\" disabled></TD>'+");
				out.println("'<td></td>'+");
				out.println("'<td></td></tr>'+");
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">Returned Amount</TD>'+");
				out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=RET_AMOUNT maxlength=\"23\" value=\"'+data[18]+'\"   size=\"10\" onblur=\"cal_retamt(this)\" disabled ></TD>'+");
				out.println("'<td></td>'+");
				out.println("'<td></td></tr>'+");
				out.println("'</table>';");
				out.println("}");
				
				out.println(" fill_data()");
				out.println("   }"); 
				out.println(" }"); 
				out.println("}");
				
				out.println("function alertGetContents(http_request,opt,type) {");
				out.println(" if (http_request.readyState == 4) {");
				out.println("    if (http_request.status == 200) {");
				out.println("      if(opt==\"10\"){");
				out.println("         alert(http_request.responseText);");
				out.println("         contract.innerHTML=http_request.responseText; ");
				out.println("      }else if(opt==\"2\"){");
				out.println("         inv.innerHTML=http_request.responseText; ");
				out.println("      }else if(opt==\"3\"){");
				out.println("         document.Form1.tot_val.value=http_request.responseText; ");
				out.println("         f=document.Form1.hid_opt_val.value ;");
				out.println("         g=document.Form1.hid_win_opt.value ;");
				out.println("         if(parseFloat(unformat_noobject(document.Form1.tot_val.value))<=parseFloat(document.Form1.elements[\"BAL_AMOUNT_\"+f].value)){");
				out.println("           popupwin.document.Form1.tot_val.value=document.Form1.tot_val.value; ");
				out.println("           popupwin.total.innerHTML=format_noobject(document.Form1.tot_val.value); ");
				out.println("           document.Form1.elements[\"SETT_AMOUN_\"+f].value=format_noobject(document.Form1.tot_val.value); ");
				
				out.println("         }else{");
				out.println("           popupwin.document.Form1.elements['Text_standard'+g].value      =\"NO\";");
				out.println("           popupwin.document.Form1.elements['Text_sett_amount'+g].disabled=false;");
				out.println("           popupwin.document.Form1.elements['Text_standard'+g].checked    =false;");
				
				out.println("         }");
				
				out.println("      }else if(opt==\"6\"){");
				out.println("         document.Form1.tot_val.value=http_request.responseText; ");
				out.println("         document.Form1.elements[\"SETT_AMOUN_0\"].value=format_noobject(document.Form1.tot_val.value); ");
				
				out.println("      }else if(opt==\"5\"){");
				out.println("         rec.innerHTML=http_request.responseText; ");
				out.println("      }else if(opt==\"4\"){");
				out.println("   				data_vec= new Array(); ");
				
				out.println("           var xmlbody=http_request.responseXML.documentElement;");
				out.println("           var vsize=0;");
				
				out.println("  				  if(xmlbody.childNodes.length!=0){");
				out.println("  				  for(var i=0;i<xmlbody.childNodes.length;i++){");
				out.println("    				 for(var j=0;j<xmlbody.childNodes[i].childNodes.length;j++){");
				out.println("      			  data_vec[vsize]=xmlbody.childNodes[i].childNodes[j].text;");
				out.println("       			vsize++;");
				out.println("    				}");
				out.println("   		   }");
				out.println("   		   }else{");
				out.println("             if(type=='ExcRate'){"); 
				out.println("               document.Form1.EXCHANE_RATE.value        =\"0\";"); 
				out.println("     document.Form1.EXCHANE_RATE.disabled =false;"); 
				out.println("             }  ");
				
				
				out.println("   		   }");
				out.println("          addrow(data_vec,type);");
				
				
				out.println("      }");
				out.println("    } else {");
				out.println("        ");
				out.println("    }");
				out.println(" }");
				out.println("}");
				
				
				out.println("function makeRequest2(obj) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=get_return_receipt&client=\"+obj+\"\";");
				out.println("	load_interface(m_url,'NORM');");
				out.println("}");
				
				out.println("function makeRequest3(obj) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=get_return_receipt2&client=\"+obj+\"&rec_no=\"+document.Form1.RECEIPT_NO.value;");
				out.println("	 load_interface(m_url,'NORM');");
				out.println("}");
				
				out.println("function makeRequest4(obj) {");
				out.println(" document.Form1.hid_help_status.value='H_account' ");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_get_account_no_group_receipt&data_val=\"+obj;");
				//out.println(" window.open(m_url);");
				out.println("	 load_interface(m_url,'XML');");
				out.println("}");
				
				
				out.println("function makeRequest_branch(obj) {");
				out.println("if(document.Form1.PAY_BRANCH.value==\"\"){");
				out.println("document.Form1.PAY_BRANCH_NAME.value=\"\"");
				out.println("}");
				out.println(" document.Form1.hid_help_status.value='H_branch' ");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Temp_receipt_Branch_code&data_val=\"+obj+\"&ac_status=Y\";");
				out.println("	 load_interface(m_url,'XML');");
				out.println("}");
				
				
				
				out.println("function makeRequest5(obj) {");
				out.println(" document.Form1.hid_help_status.value='H_client' ");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_get_client_code&data_val=\"+obj;");
				out.println("	 load_interface(m_url,'XML');");
				out.println("}");
				
				out.println("function makeRequest6(obj) {");
				out.println(" document.Form1.hid_help_status.value='H_receipt' ");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_get_receipt_no&data_val=\"+obj;");
				out.println("	 load_interface(m_url,'XML');");
				out.println("}");
				
				
				out.println("function makeRequest_account_no(obj) {");
				out.println(" document.Form1.hid_help_status.value='H_account_no' ");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_val_account_code&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println("	 load_interface(m_url,'XML');");
				out.println("}");
				
				
				out.println("function get_vector(data_vec){ ");
				out.println("			 if( data_vec.length>0 && document.Form1.hid_help_status.value=='H_account' && document.Form1.PAY_ACCOUNT.value !='' ){");
				out.println("      assign_account(data_vec);");
				out.println("			}");
				out.println("			 if( data_vec.length==0 && document.Form1.hid_help_status.value=='H_client'  && document.Form1.CLIENT_CODE.value !='' ){");
				out.println("      client_help();");
				out.println("			}");
				out.println("			 if( data_vec.length>0 && document.Form1.hid_help_status.value=='H_client' && document.Form1.CLIENT_CODE.value !='' ){");
				out.println("      makeRequest2(document.Form1.CLIENT_CODE.value);");
				out.println("			}");
				out.println("			 if(data_vec.length==0 && document.Form1.hid_help_status.value=='H_receipt'  && document.Form1.RECEIPT_NO.value !='' ){");
				out.println("receipt_help()");
				out.println("			}");
				out.println("			 if(data_vec.length>0 && document.Form1.hid_help_status.value=='H_receipt' && document.Form1.RECEIPT_NO.value !='' ){");
				out.println("      get_Receipt();");
				out.println("			}");
				
				out.println("			 if( data_vec.length>0 && document.Form1.hid_help_status.value=='H_account_no' && document.Form1.TXT_ACCOUNT_NO.value !='' ){");
				out.println("      document.Form1.TXT_ACCOUNT_NO.VALUE=data_vec[0];");
				out.println("      document.Form1.hid_TXT_BRANCH_CODE.VALUE=data_vec[1];");
				out.println("      document.Form1.BRANCH_NAME.value = data_vec[4]+' - '+data_vec[2]");
				out.println("      document.Form1.hid_TXT_ACC_REF_NO.VALUE=data_vec[3];");
				out.println("			}");
				
				out.println("			 if( data_vec.length==0 && document.Form1.hid_help_status.value=='H_account_no' && document.Form1.TXT_ACCOUNT_NO.value !='' ){");
				out.println("      account_number_help();");
				out.println("			}");
				
				out.println("			 if(document.Form1.hid_help_status.value=='H_tendered' && document.Form1.TEN_AMOUNT.value !='' ){");
				out.println("      document.Form1.RET_AMOUNT.value=data_vec[0];");
				
				out.println("			}");
				
				out.println("			 if( data_vec.length==0 && document.Form1.hid_help_status.value=='H_branch' && document.Form1.PAY_BRANCH.value !='' ){");
				out.println("      branch_help();");
				out.println("			}");
				
				out.println("			 else if(document.Form1.hid_help_status.value=='H_branch' && document.Form1.PAY_BRANCH.value !='' ){");
				out.println("      document.Form1.PAY_BRANCH.value=data_vec[0];");
				out.println("      document.Form1.PAY_BRANCH_NAME.value = data_vec[10]+' - '+data_vec[1]"); //Added by Chdandana on15/10/2007
				out.println("			validate_cheque_no();"); //44444
				out.println("			}");
				
				out.println("else if(data_vec.length>0 && document.Form1.hid_help_status.value=='VAL_CHEQUE' ){"); //added by nuwan de silva 02-08-07
				out.println("alert('Record already exists');");
				out.println("  m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Settlement_group?chksql=view_cheques&cheque_no='+document.Form1.CHEQUE_NO.value+'&branch_code='+document.Form1.PAY_BRANCH.value;");
				out.println("window.open(m_url,'displayWindow2','left=450,top=200,width=600,height=350,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("document.Form1.CHEQUE_NO.value=\"\"");
				out.println("			}");
				
				out.println("			 if( data_vec.length>0 && document.Form1.hid_help_status.value=='H_group_rec' && document.Form1.OPTION_DESC.value =='DELETE' ){");
				out.println("      branch_help();");
				out.println("			}");
				
				out.println("			 if( data_vec.length==0 && document.Form1.hid_help_status.value=='H_group'  && document.Form1.CLIENT_NAME_1.value !=\"\" ){");
				out.println("      group_help();");
				out.println("			}");
				
				
				out.println("}");
				
				out.println("function makeRequest_third(obj) {");
				out.println("if(document.Form1.CLIENT_NAME_1.value==\"\"){");
				out.println("document.Form1.GROUP_NAME.value=\"\"");
				out.println("document.Form1.CLIENT_ADDRESS_1.value=\"\"");
				out.println("}");
				out.println(" document.Form1.hid_help_status.value='H_group' ");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_display_group_invoices1&data_val=\"+obj+\"&ac_status=Y\";");
				out.println("	 load_interface(m_url,'XML');");
				out.println("}");
				
				
				out.println("function get_vector_normal(http_response){ ");
				out.println(" get_group_details.innerHTML = ''; ");
				out.println(" get_group_details.innerHTML = http_response; ");
				out.println("}");
				
				
				
				
				out.println("function assign_div(){");
				out.println(" rent.innerHTML=document.Form1.h_rent.value;"); 
				out.println("	term.innerHTML=document.Form1.h_term.value;;"); 
				out.println(" rpv.innerHTML =document.Form1.h_rpv.value;"); 
				out.println("	tpv.innerHTML =document.Form1.h_tpv.value;;"); 
				out.println("}");		
				
				
				out.println("function inv_help(num){");
				out.println(" document.Form1.hid_opt_val.value=num;"); 
				out.println("	popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=get_Invoice&client=\"+document.Form1.CLIENT_CODE.value+\"\", \"oBj\",\"left=130,top=200,width=750,height=400\");"); 
				out.println("}");		
				
				out.println("function cal_amount(opt,am1,am2,num) {");
				out.println("   document.Form1.hid_win_opt.value=num;");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
				out.println("   makeRequest(m_url,'3');");
				
				out.println("}");	
				
				out.println("function get_excharate(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_excharate&CURR_CODE=\"+document.Form1.CURR_CODE.value+\"&VAL_DATE=\"+document.Form1.VAL_DAY.value+\"-\"+document.Form1.VAL_MONTH.value+\"-\"+document.Form1.VAL_YEAR.value;");
				out.println("   makeRequest(m_url,'4','ExcRate');");
				out.println("}");
				
				out.println("function get_sysdate(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_sysdate\";");
				out.println("   makeRequest(m_url,'4','SysDate');");
				out.println("}");
				
				out.println("function get_invoice(val) {");
				out.println("  if(document.Form1.CLIENT_CODE.value!=\"\" && document.Form1.AMOUNT.value!=\"\"){"); 
				out.println("if(document.Form1.OTHER_CHARGES.value==\"Y\" && document.Form1.TXT_RENTAL_OTHER_INV.value!=\"\" ){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=get_contract&Client_Code=\"+document.Form1.CLIENT_CODE.value+\"&Amount=\"+unformat_noobject(document.Form1.TXT_RENTAL_OTHER_INV.value)+\"&Type=\"+document.Form1.hid_option.value+\"&Rec_No=\"+document.Form1.RECEIPT_NO.value;");
				out.println("  }");
				out.println("else if(document.Form1.OTHER_CHARGES.value==\"N\" ){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=get_contract&Client_Code=\"+document.Form1.CLIENT_CODE.value+\"&Amount=\"+unformat_noobject(document.Form1.AMOUNT.value)+\"&Type=\"+document.Form1.hid_option.value+\"&Rec_No=\"+document.Form1.RECEIPT_NO.value;");
				out.println("  }");
				out.println("   makeRequest(m_url,'2','Invoice');");
				out.println("  }");
				out.println("}");
				
				
				out.println("function get_contract(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=get_contract&Client_Code=\"+document.Form1.CLIENT_CODE.value+\"&Amount=\"+unformat_noobject(document.Form1.AMOUNT.value)+\"&Type=\"+document.Form1.hid_option.value+\"&Rec_No=\"+document.Form1.RECEIPT_NO.value;");
				out.println("window.open(m_url);");
				out.println("   makeRequest(m_url,'10','Contract');");
				out.println("}");				
				
				
				out.println("function get_excharate(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_excharate&CURR_CODE=\"+document.Form1.CURR_CODE.value+\"&VAL_DATE=\"+document.Form1.VAL_DAY.value+\"-\"+document.Form1.VAL_MONTH.value+\"-\"+document.Form1.VAL_YEAR.value;");
				out.println("   makeRequest(m_url,'4','ExcRate');");
				out.println("}");
				
				out.println("function check_rep_amount_onsave() {");
				out.println(" m_count = 0 ");
				out.println(" if( return_rec.innerHTML != '' )");
				out.println(" m_count = parseFloat(document.Form1.hid_count.value); ");
				out.println(" for(var i=0;i<m_count;i++){ ");
				out.println(" m_amount = \"AMOUNT_\"+i ");
				out.println(" m_amount_val =  parseFloat(unformat_number(document.Form1.elements[m_amount]))");
				out.println(" m_rep_amount = parseFloat(unformat_number(document.Form1.AMOUNT)); ");
				out.println("  if(m_rep_amount < m_amount_val ){");
				out.println("   alert('Returned amount cannot be greater than Receipt amount '); ");
				out.println("   AMOU.style.color='red';");
				out.println("   document.Form1.elements[m_amount].value = '';");
				out.println("   document.Form1.elements[m_amount].focus();");
				out.println("   return false;");
				out.println("  }");
				out.println("  else {");
				out.println("   continue; ");
				out.println("  }");
				out.println(" }");
				out.println("   AMOU.style.color='black';");
				out.println("   return true;");
				out.println("}");
				
				
				out.println("function cal_exc_rate(val) {");
				out.println("if(document.Form1.REP_AMOUNT.value!='' && isnumberok(document.Form1.REP_AMOUNT,25)){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=cal_exc_rate&CURR_CODE=\"+document.Form1.CURR_CODE.value+\"&EXC_RATE=\"+unformat_noobject(document.Form1.EXCHANE_RATE.value)+\"&AMOUNT=\"+unformat_noobject(document.Form1.AMOUNT.value)+\"&REP_AMOUNT=\"+unformat_noobject(document.Form1.REP_AMOUNT.value)+\"\";");
				out.println("   document.Form1.REP_AMOUNT.value=format_noobject(document.Form1.REP_AMOUNT.value);");
				out.println("   makeRequest(m_url,'4','ChExcRate');");
				out.println("}");
				out.println("else");
				out.println("{");
				out.println("alert('Please enter a number');");
				out.println("   document.Form1.REP_AMOUNT.value=''");
				out.println("}");
				out.println("}");
				
				out.println("function get_Receipt() {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_receipt&RECEIPT_NO=\"+document.Form1.RECEIPT_NO.value+\"\";");
				out.println("   makeRequest(m_url,'4','Receipt');");
				out.println("}");
				
				out.println("function get_Return_Receipt(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=get_return_receipt&client=\"+document.Form1.CLIENT_CODE.value+\"\";");
				out.println("   makeRequest(m_url,'4','Receipt');");
				out.println("}");
				
				out.println("function check_client(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_client_code&client_code=\"+document.Form1.CLIENT_CODE.value+\"\";");
				out.println("   makeRequest(m_url,'4','Cli');");
				out.println("}");
				
				
				
				out.println("function validate_data(){"); 
				out.println("m_sub=0;"); 
				out.println("if(document.Form1.hid_option.value==\"NEW\"){"); 
				out.println("if(document.Form1.document.Form1.VAL_DAY.value+document.Form1.VAL_MONTH.value+document.Form1.VAL_YEAR.value==\"\"){  "); 
				out.println("  VDATE.style.color='red';");
				out.println("  m_sub = 1;;"); 
				out.println("}"); 
				out.println("else ");//added 2012-02-18
				out.println("{");
				out.println("  VDATE.style.color='black';"); //added 2012-02-18
				out.println("}");//added 2012-02-18
				
				out.println("if(document.Form1.AMOUNT.value==\"\"){  "); 
				out.println("  AMOU.style.color='red';");
				out.println("  m_sub = 1;;"); 
				out.println("}");
				out.println("else ");//added 2012-02-18
				out.println("{");
				out.println("  AMOU.style.color='black';"); //added 2012-02-18
				out.println("}");//added 2012-02-18
				
				out.println("if(document.Form1.EXCHANE_RATE.value==\"\"){  "); 
				out.println("  EXCH.style.color='red';");
				out.println("  m_sub = 1;;"); 
				out.println("}"); 
				out.println("else ");//added 2012-02-18
				out.println("{");
				out.println("  EXCH.style.color='black';"); //added 2012-02-18
				out.println("}");//added 2012-02-18
				
				
				
				out.println("if(document.Form1.REP_AMOUNT.value==\"\"){  "); 
				out.println("  RAMO.style.color='red';");
				out.println("  m_sub = 1;;"); 
				out.println("}");
				out.println("else ");//added 2012-02-18
				out.println("{");
				out.println("  RAMO.style.color='black';"); //added 2012-02-18
				out.println("}");//added 2012-02-18
				
				
				out.println("if(document.Form1.SETT_MODE.value==\"CHEQUE\"){  "); 
				out.println("if(document.Form1.document.Form1.CHEQUE_DATE_DD.value+document.Form1.CHEQUE_DATE_MM.value+document.Form1.CHEQUE_DATE_YY.value==\"\" ){  ");  //Added By nuwan De silva
				out.println("  CDATE.style.color='red';");
				out.println("  m_sub = 1;;"); 
				out.println("}"); 
				out.println("else ");//added 2012-02-18
				out.println("{");
				out.println("  CDATE.style.color='black';"); //added 2012-02-18
				out.println("}");//added 2012-02-18
				
				out.println("if(document.Form1.CHEQUE_NO.value==\"\"  ){  ");  
				out.println("  CARNO.style.color='red';");
				out.println("  m_sub = 1;;"); 
				out.println("}"); 
				out.println("else ");//added 2012-02-18
				out.println("{");
				out.println("  CARNO.style.color='black';"); //added 2012-02-18
				out.println("}");//added 2012-02-18
				
				
				out.println("if(document.Form1.PAY_BRANCH.value==\"\"  ){  ");  
				out.println("  PBRANCH.style.color='red';");
				out.println("  m_sub = 1;;"); 
				out.println("}"); 
				out.println("else ");//added 2012-02-18
				out.println("{");
				out.println("  PBRANCH.style.color='black';"); //added 2012-02-18
				out.println("}");//added 2012-02-18
				
				out.println("}"); 
				
				out.println("if(document.Form1.SETT_MODE.value==\"STD_ORD\" || document.Form1.SETT_MODE.value==\"DIR_DEP\"){  "); 
				
				out.println("if(document.Form1.TXT_ACCOUNT_NO.value==\"\" ){  ");  
				out.println("  ACNO.style.color='red';");
				out.println("  m_sub = 1;;"); 
				out.println("}");
				out.println("else ");//added 2012-02-18
				out.println("{");
				out.println("  ACNO.style.color='black';"); //added 2012-02-18
				out.println("}");//added 2012-02-18
				
				out.println("}"); 
				
				
				out.println("}else {"); 
				
				out.println("if(document.Form1.RECEIPT_NO.value==\"\"){  "); 
				out.println("  RNO.style.color='red';");
				out.println("  m_sub = 1;;"); 
				out.println("}");
				out.println("else ");//added 2012-02-18
				out.println("{");
				out.println("  RNO.style.color='black';"); //added 2012-02-18
				out.println("}");//added 2012-02-18
				
				out.println("if(document.Form1.document.Form1.VAL_DAY.value+document.Form1.VAL_MONTH.value+document.Form1.VAL_YEAR.value==\"\"){  "); 
				out.println("  VDATE.style.color='red';");
				out.println("  m_sub = 1;;"); 
				out.println("}"); 
				out.println("else ");//added 2012-02-18
				out.println("{");
				out.println("  VDATE.style.color='black';"); //added 2012-02-18
				out.println("}");//added 2012-02-18
				
				out.println("if(document.Form1.AMOUNT.value==\"\"){  "); 
				out.println("  AMOU.style.color='red';");
				out.println("  m_sub = 1;;"); 
				out.println("}");
				out.println("else ");//added 2012-02-18
				out.println("{");
				out.println("  AMOU.style.color='black';"); //added 2012-02-18
				out.println("}");//added 2012-02-18
				
				
				out.println("if(document.Form1.EXCHANE_RATE.value==\"\"){  "); 
				out.println("  EXCH.style.color='red';");
				out.println("  m_sub = 1;;"); 
				out.println("}"); 
				out.println("else ");//added 2012-02-18
				out.println("{");
				out.println("  EXCH.style.color='black';"); //added 2012-02-18
				out.println("}");//added 2012-02-18
				
				
				out.println("if(document.Form1.REP_AMOUNT.value==\"\"){  "); 
				out.println("  RAMO.style.color='red';");
				out.println("  m_sub = 1;;"); 
				out.println("}");
				out.println("else ");//added 2012-02-18
				out.println("{");
				out.println("  RAMO.style.color='black';"); //added 2012-02-18
				out.println("}");//added 2012-02-18
			
				out.println("}");
				out.println("if(m_sub=='1'){");
				out.println("return false;"); 
				out.println("}"); 
				out.println("else {"); 
				out.println("return true;"); 
				out.println("}"); 
				out.println("}"); 
				
				out.println("function unallocated_amount(){");
				
				out.println("if(document.Form1.TXT_TOT_AMT.value!=\"0.00\"){  ");  
				out.println("alert('Total Unallocated amount must be Zero');");
				out.println("   m_untot=0;"); 
				out.println("}"); 
				out.println("else{  ");   
				out.println("   m_untot=1;"); 
				out.println("}"); 
				
				out.println("for(var t=0;t<document.Form1.hid_count_client.value;t++){");
				out.println("if(parseFloat(unformat_noobject(document.Form1.elements[\"TXT_CLIENT_AMOUNT_\"+t].value))!=\"0\"){");
				out.println("   m_untot1=1;"); 
				out.println(" break");
				out.println("}");
				out.println("else{");
				out.println("   m_untot1=0;"); 
				out.println("}");
				out.println("}"); 
				out.println("if(m_untot1==\"0\"){");
				out.println("alert('Must enter a amount for a client');");
				out.println("}"); 
				out.println("}"); 
				
				out.println("function befor_submit(){ "); 	
				out.println(" if(document.Form1.hid_count_client) ");
				out.println(" {   ");
				out.println("   assign_hidden_values();");
				out.println("   m_status = document.Form1.hid_option.value ");
				out.println("   m_save_msg='Are you sure you want to Save ? ';"); 
				out.println("   if(m_status == \"EDIT\"){ ");
				out.println("   m_save_msg = 'Are you sure you want to Modify ? '");
				out.println("   }"); 
				out.println("   else if(m_status == \"DELETE\"){");
				out.println("   m_tot=0;"); 
				out.println("   m_amount_entered=0;");  
				out.println("   m_balance=0;");  
				out.println("   m_save_msg = 'Are you sure you want to Delete ? '");
				out.println("   }");
				out.println("    unallocated_amount()"); 
				out.println("   if(m_untot==1 && m_untot1==1){");
				out.println("		if(validate_data()){"); 
				out.println("		if(confirm(m_save_msg)){ "); 
				out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("		document.Form1.elements[i].disabled=false;");
				out.println("		}");
				out.println("		document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_save_Settlement_group';");  
				out.println("		document.Form1.submit();	"); 
				out.println("		}"); 
				out.println("		}"); 
				out.println("		else { "); 
				out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
				out.println("		}");
				out.println("		}");
				out.println("   }else  ");
				out.println("   { "); 
				out.println("		if(!validate_data())"); 
				out.println("   	{ ");
				out.println("			alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
				out.println("       } ");
				out.println("   } "); //added 2012-02-18
				out.println("} "); 
				
				
				out.println("function befor_reset(){");
				out.println(" if(confirm(\"Are you sure you want to clear the screen?\")){  ");
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement_group?chksql=main_page'");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_back(){");
				out.println("   close_window(); ");
				out.println("}");
				
				out.println("function load_lock(){	"); 
				out.println("}	"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are You Sure?\")){ "); 
				out.println("		window.close();");
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement_group?chksql=main_page'");
				out.println("}"); 
				
				out.println(""); 
				out.println(""); 
				
				out.println("function save_window(){	"); 
				out.println("before_submit();"); 
				out.println("}"); 
				out.println(""); 
				
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_LAKDL_AF_RE_Settlement\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\" Collection - Group Receipts - Entry - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Collection - Group Receipts - Entry - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				out.println("function befor_clear(){");
				out.println("     document.Form1.RECEIPT_NO.value      ='';"); 
				out.println("     document.Form1.AMOUNT.value          ='';"); 
				out.println("     document.Form1.REP_AMOUNT.value      ='';"); 
				out.println("     document.Form1.PAY_BRANCH.value      ='';");
				out.println("     document.Form1.PAY_BRANCH_NAME.value ='';");
				out.println("     document.Form1.PAY_ACCOUNT.value     ='';");
				out.println("     document.Form1.CHEQUE_NO.value       ='';");
				out.println("     document.Form1.REMARK.value          ='';");
				out.println(" 		 return_rec.innerHTML = ''; ");	
				out.println("}");
				
				
				out.println("function load_screen_status(m_val){"); 
				out.println("    document.Form1.hid_option.value    =m_val;"); 
				out.println("if(m_val==\"NEW\"){"); 
				out.println(" if(confirm(\"Are you sure you want to enter New record?\")){  ");
				out.println("document.Form1.rec_help.disabled=true;"); 
				out.println("document.Form1.RECEIPT_NO.disabled=true;");
				out.println("new_window(); ");
				out.println("}"); 
				
				out.println("}else if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
				out.println("}"); 
				out.println("else if(m_val==\"EDIT\"){"); 
				out.println(" if(confirm(\"Are you sure you want to Modify a record?\")){  ");
				out.println("document.Form1.RECEIPT_NO.disabled=false;"); 
				out.println("document.Form1.rec_help.disabled=false;"); 
				out.println("document.Form1.CHEQUE_DATE_DD.disabled=false;");
				out.println("document.Form1.CHEQUE_DATE_MM.disabled=false;");
				out.println("document.Form1.CHEQUE_DATE_YY.disabled=false;");
				out.println("document.Form1.PAY_ACCOUNT.disabled=false;");
				out.println("document.Form1.accno_help.disabled=false;");
				out.println("befor_clear();");
				out.println("}"); 
				out.println("}else if(m_val==\"DELETE\"){"); 
				out.println(" if(confirm(\"Are you sure you want to  Delete a record?\")){  ");
				out.println("document.Form1.RECEIPT_NO.disabled=false;"); 
				out.println("document.Form1.rec_help.disabled=false;"); 
				out.println("document.Form1.AMOUNT.disabled=true;"); 
				out.println("document.Form1.REP_AMOUNT.disabled=true;"); 
				out.println("document.Form1.EXCHANE_RATE.disabled=true;");
				out.println("document.Form1.PAY_ACCOUNT.disabled=true;"); 
				out.println("document.Form1.PAY_BRANCH.disabled=true;"); 
				out.println("document.Form1.BUT_PAY_BRANCH.disabled=true;"); 
				out.println("document.Form1.accno_help.disabled=true;"); 
				out.println("document.Form1.PAY_BRANCH_NAME.disabled=true;"); 
				out.println("document.Form1.REMARK.disabled=true;");
				out.println("document.Form1.OTHER_CHARGES.disabled=true;"); 
				out.println("document.Form1.SETT_MODE.disabled=true;"); 
				out.println("document.Form1.PAY_TYPE.disabled=true;");
				out.println("document.Form1.CURR_CODE.disabled=true;");
				out.println("document.Form1.VAL_DAY.disabled=true;");
				out.println("document.Form1.VAL_MONTH.disabled=true;");
				out.println("document.Form1.VAL_YEAR.disabled=true;");
				out.println("document.Form1.CLIENT_NAME_1.disabled=true;");
				out.println("document.Form1.BUT_GROUP_HELP.disabled=true;");
				out.println("document.Form1.CLIENT_ADDRESS_1.disabled=true;");
				
				out.println("}"); 
				
				
				out.println("}"); 
				out.println("else{");
				out.println("}"); 
				out.println("document.Form1.OPTION_DESC.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				out.println("}else if(m_val==\"DELETE\"){");  
				out.println("document.Form1.hid_status.value=\"Delete\";");  
				out.println("}else if(m_val==\"DACT\"){");  
				out.println("document.Form1.hid_status.value=\"Deactivate\";");  
				out.println("}else if(m_val==\"RACT\"){");  
				out.println("document.Form1.hid_status.value=\"Reactivate\";");  
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
				
				out.println("function clear_data() {");
				out.println(" if(document.Form1.hid_help_type.value == '1') { ");
				out.println(" return_rec.innerHTML = ''; ");
				out.println(" return_rec.innerHTML = ''; ");
				out.println(" document.Form1.RECEIPT_NO.value=''; }"); 
				out.println(" else if(document.Form1.hid_help_type.value == '3'){ ");
				out.println(" document.Form1.PAY_ACCOUNT.value='';  "); 
				out.println(" document.Form1.PAY_BRANCH.value='';  ");
				out.println(" document.Form1.PAY_BRANCH_NAME.value=''; ");
				out.println(" } "); 
				out.println(" ");
				out.println(" else if(document.Form1.hid_help_type.value == '8'){ ");
				out.println(" document.Form1.PAY_BRANCH.value=''; ");
				out.println(" document.Form1.PAY_BRANCH_NAME.value=''; ");
				out.println(" } ");  
				out.println(" ");
				out.println("}");
				
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
				out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Help_Servlet?class_in="+m_client_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("	"); 
				out.println("	if(oBj.valout[1] ==\" \"){"); 
				out.println("		clear_data();");
				out.println("		} else "); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println("		if(IfCount==\"99\"){"); 
				out.println("		help_update_value_assign_99();"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"1\"){"); 
				out.println("		client_assign(oBj);"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"2\"){"); 
				out.println("		receipt_assign(oBj);");
				out.println("		}");
				out.println("		if(IfCount==\"3\"){"); 
				out.println("		account_assign(oBj);"); 
				out.println("		}");
				out.println("		if(IfCount==\"4\"){"); 
				out.println("		lease_assign(oBj);"); 
				out.println("		}");
				out.println("		if(IfCount==\"5\"){"); 
				out.println("		term_assign(oBj);"); 
				out.println("		}");
				
				out.println("		if(IfCount==\"7\"){"); 
				out.println("		account_number_assign(oBj);"); 
				out.println("		}");
				
				out.println("		if(IfCount==\"8\"){"); 
				out.println("		branch_assign(oBj);"); 
				out.println("		}");
				
				out.println("		if(IfCount==\"120\"){"); 
				out.println("		assign_group_help(oBj);"); 
				out.println("		}");		
				
				
				
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
				out.println("	else{");
				out.println("	clear_data();");
				out.println("	}");
				out.println("	}	"); 
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
				
				out.println("function client_help(){");
				out.println("Crit=document.Form1.CLIENT_CODE.value+\"@\";");
				out.println(" document.Form1.hid_help_type.value='1' ");
				out.println("HelpBox('1','10','0',Crit,'ClientSql','1');");
				out.println("}");		
				out.println("function client_assign(oBj){");
				
				out.println("if(oBj.valout[5]!='-' && oBj.valout[6]!='-' && oBj.valout[7]!='-' ){");
				out.println("address=oBj.valout[5]+','+oBj.valout[6]+','+oBj.valout[7]; ");		
				out.println("}");		
				out.println("else if(oBj.valout[5]!='-' && oBj.valout[6]!='-' && oBj.valout[7]=='-' ){");
				out.println("address=oBj.valout[5]+','+oBj.valout[6]; ");		
				out.println("}");		
				out.println("else if(oBj.valout[5]!='-' && oBj.valout[6]=='-' && oBj.valout[7]!='-' ){");
				out.println("address=oBj.valout[5]+','+oBj.valout[7]; ");		
				out.println("}");		
				out.println("else if(oBj.valout[5]=='-' && oBj.valout[6]=='-' && oBj.valout[7]=='-' ){");
				out.println("address=oBj.valout[7]; ");		
				out.println("}");		
				out.println("else if(oBj.valout[5]=='-' && oBj.valout[6]!='-' && oBj.valout[7]=='-' ){");
				out.println("address=oBj.valout[6]; ");		
				out.println("}");		
				
				out.println(" document.Form1.CLIENT_ADDRESS.value =address");
				
				out.println(" makeRequest2(oBj.valout[2]);");
				out.println("set_address_pay_type();"); 
				
				out.println("}");
				
				out.println("function receipt_help(){");
				out.println("Crit=document.Form1.CLIENT_NAME_1.value+\"@\";");
				out.println(" document.Form1.hid_help_type.value='2' ");
				out.println("HelpBox('1','10','0',Crit,'ReceiptSql_group','2');");
				out.println("}");		
				
				out.println("function receipt_help_2(){");
				out.println("Crit=document.Form1.RECEIPT_NO.value+\"@\";");
				out.println(" document.Form1.hid_help_type.value='2' ");
				out.println("HelpBox('1','10','0',Crit,'ReceiptSql1','2');");
				
				
				out.println("}");		
				
				out.println("function receipt_assign(oBj){");
				out.println(" document.Form1.RECEIPT_NO.value =oBj.valout[2]");
				out.println(" get_Receipt();");
				out.println("}");
				
				out.println("function account_help(){");
				out.println("val_1=''");
				out.println("Crit=document.Form1.PAY_ACCOUNT.value+\"@\"+val_1+\"@\"+\"Y@\";");
				out.println(" document.Form1.hid_help_type.value='3' ");
				out.println("HelpBox('1','10','0',Crit,'AccountSql_receipt','3');");
				
				out.println("}");		
				
				out.println("function account_assign(oBj){");
				out.println(" document.Form1.PAY_ACCOUNT.value =oBj.valout[2]");
				out.println(" document.Form1.PAY_BRANCH.value =oBj.valout[3]");
				out.println(" document.Form1.PAY_BRANCH_NAME.value =oBj.valout[6]+' - '+oBj.valout[4]");
				out.println("}");
				
				
				
				out.println("function branch_help(){");
				out.println("Crit=document.Form1.PAY_BRANCH.value+\"@\"+\"Y@\";");
				out.println(" document.Form1.hid_help_type.value='8' ");
				out.println("HelpBox('1','10','0',Crit,'m_help_TXT_BRANCH_CODE_sql','8');");
				
				out.println("}");		
				
				out.println("function branch_assign(oBj){");
				out.println(" document.Form1.PAY_BRANCH.value =oBj.valout[2]");
				out.println(" document.Form1.PAY_BRANCH_NAME.value = oBj.valout[12]+' - '+oBj.valout[3]");
				out.println("}");
				
				
				
				
				
				
				out.println("function assign_account(data_vec){");
				out.println(" document.Form1.PAY_ACCOUNT.value =data_vec[0]");
				out.println(" document.Form1.PAY_BRANCH.value =data_vec[1]"); 
				out.println(" document.Form1.PAY_BRANCH_NAME.value =data_vec[4]+' - '+data_vec[3]");
				out.println("}");
				
				out.println("function load_edit_window(i,foll_no,type) {");
				out.println("   ");
				out.println("}"); 
				
				out.println("function befor_end(m_obj) {");
				out.println("   m_obj.focus();");
				out.println("}");
				
				out.println("function account_number_help(){");
				out.println("Crit=document.Form1.TXT_ACCOUNT_NO.value+\"@\"+\"Y@\";");
				out.println(" document.Form1.hid_help_type.value='7' ");
				out.println("HelpBox('1','10','0',Crit,'m_help_TXT_ACCOUNT_NO_sql','7');");
				out.println("}");		
				
				out.println("function account_number_assign(oBj){");
				out.println(" document.Form1.TXT_ACCOUNT_NO.value =oBj.valout[2]");
				out.println(" document.Form1.BRANCH_NAME.value =oBj.valout[4]");
				out.println("      document.Form1.hid_TXT_BRANCH_CODE.value=oBj.valout[3];");
				out.println("      document.Form1.hid_TXT_ACC_REF_NO.value=oBj.valout[6];");
				
				out.println("}");
				
				
				
				out.println("function load_c_date(val) {");
				out.println("  if(document.Form1.hid_cal_date.value=='1'){");
				out.println("     document.Form1.TXT_EFF_VAL_DATE.value=val;");
				out.println("  }else if(document.Form1.hid_cal_date.value=='2'){"); 
				out.println("     document.Form1.TXT_ACTION_TOOK_DATE.value=val;");
				out.println("  }else if(document.Form1.hid_cal_date.value=='3'){"); 
				out.println("     document.Form1.TXT_next_day.value=val;");
				out.println("  }");				
				out.println("}");				
				
				out.println("function load_data(num) {");
				out.println("	popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Documents?chksql=get_documents&deal_no=\"+num+\"\", \"oBj\",\"left=150,top=280,width=520,height=290\");"); 
				out.println("}");
				
				out.println("function load_history(num) {");
				out.println("	if(num!=''){ ");
				out.println("	  popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup?chksql=get_History&deal_no=\"+num+\"\", \"oBj\",\"left=100,top=200,width=650,height=400\");"); 
				out.println("	}else{");
				out.println("	  alert('Please enter Followup Number and continue!');");
				out.println("	}");
				out.println("}");
				
				out.println("function check_status(num) {");
				out.println("if(document.Form1.elements['Text_standard'+num].checked){");
				out.println(" document.Form1.elements['Text_standard'+num].value=\"YES\";");
				out.println("}else{");
				out.println(" document.Form1.elements['Text_standard'+num].value=\"NO\";");
				out.println("}");
				out.println("}");
				
				out.println("function check_amount(num) {");
				out.println("if(Number(document.Form1.elements['Text_sett_amount'+num].value)>Number(document.Form1.elements['Hid_amount'+num].value)){");
				out.println(" alert('Amount cannot be greater than Net Amount');");
				out.println(" document.Form1.elements['Text_sett_amount'+num].value = document.Form1.elements['Hid_amount'+num].value;");
				out.println("}");
				out.println("}");
				
				out.println("function check_Date(val1,val2,val3) {");
				out.println("");
				out.println("}");
				
				out.println("function disable_help(){");
				out.println("}");
				
				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
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
				out.println("if(document.Form1.hid_option.value==\"NEW\"){");
				//out.println("     document.Form1.VAL_DAY.value=v_dd;"); //Comment by Chandana for Ref No.873 on 15/10/2007
				//out.println("     document.Form1.VAL_MONTH.value=v_mm;");
				//out.println("     document.Form1.VAL_YEAR.value=v_yy;");
				out.println("  }");				
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
				
				
				out.println("function assign_hidden_values(){");
				out.println("if(document.Form1.SETT_MODE.value==\"STD_ORD\" || document.Form1.SETT_MODE.value==\"DIR_DEP\" ){");
				out.println("document.Form1.hid_TXT_ACCOUNT_NO.value=document.Form1.TXT_ACCOUNT_NO.value"); 
				out.println("}"); 
				out.println("if(document.Form1.SETT_MODE.value==\"CASH\" ){");
				out.println("document.Form1.hid_TEN_AMOUNT.value=document.Form1.TEN_AMOUNT.value"); 
				out.println("document.Form1.hid_RET_AMOUNT.value=document.Form1.RET_AMOUNT.value"); 
				out.println("}"); 
				out.println("if(document.Form1.OTHER_CHARGES.value==\"Y\" ){");
				out.println("document.Form1.hid_TXT_RENTAL_OTHER_INV.value=document.Form1.TXT_RENTAL_OTHER_INV.value"); 
				out.println("document.Form1.hid_INSURANCE_PREMIUM.value=document.Form1.TXT_INSURANCE_PREMIUM.value"); 
				out.println("document.Form1.hid_TXT_LUX_TAX.value=document.Form1.TXT_LUX_TAX.value"); 
				out.println("document.Form1.hid_TXT_REVENUE_LICENCY.value=document.Form1.TXT_REVENUE_LICENCY.value"); 
				out.println("document.Form1.hid_TXT_RMV_REG_FEES.value=document.Form1.TXT_RMV_REG_FEES.value"); 
				out.println("m_balance=parseFloat(unformat_noobject(document.Form1.TXT_BALANCE_PENDING.value))");  //added by nwuan de sivla 09-07-07
				out.println("}"); 
				out.println("else if(document.Form1.OTHER_CHARGES.value==\"N\" ){");
				out.println("m_balance=0;"); 
				out.println("}"); 
				out.println("}"); 
				
				out.println("function calculate_balance(obj){");
				out.println("m_rental_other_inv=0;");
				out.println("m_insurance_premium=0;");
				out.println("m_lux_tax=0;");
				out.println("m_revenue_lux=0;");
				out.println("m_rmv_reg_fee=0;");
				out.println("m_tendered=0;");
				out.println("m_tot=0;");
				out.println("m_balance=0;");
				out.println("m_amount_entered=0;");
				out.println("if(document.Form1.OTHER_CHARGES.value==\"Y\" ){ "); //added by nuwan de silva 06-07-07
				out.println("if(document.Form1.AMOUNT.value!=\"\" && isnumberok(document.Form1.AMOUNT,25)){"); 
				out.println("m_amount_entered=parseFloat(unformat_noobject(document.Form1.AMOUNT.value))");
				out.println("}"); 
				out.println("if(document.Form1.TXT_RENTAL_OTHER_INV.value!=\"\" && isnumberok(document.Form1.TXT_RENTAL_OTHER_INV,25)){"); 
				out.println("m_rental_other_inv=unformat_noobject(document.Form1.TXT_RENTAL_OTHER_INV.value)");
				out.println("  get_invoice();");
				out.println("}"); 
				out.println("if(document.Form1.TXT_INSURANCE_PREMIUM.value!=\"\" && isnumberok(document.Form1.TXT_INSURANCE_PREMIUM,25)){"); 
				out.println("m_insurance_premium=unformat_noobject(document.Form1.TXT_INSURANCE_PREMIUM.value)");
				out.println("}"); 
				out.println("if(document.Form1.TXT_LUX_TAX.value!=\"\" && isnumberok(document.Form1.TXT_LUX_TAX,25)){"); 
				out.println("m_lux_tax=unformat_noobject(document.Form1.TXT_LUX_TAX.value)");
				out.println("}"); 
				out.println("if(document.Form1.TXT_REVENUE_LICENCY.value!=\"\" && isnumberok(document.Form1.TXT_REVENUE_LICENCY,25)){"); 
				out.println("m_revenue_lux=unformat_noobject(document.Form1.TXT_REVENUE_LICENCY.value)");
				out.println("}"); 
				out.println("if(document.Form1.TXT_RMV_REG_FEES.value!=\"\" && isnumberok(document.Form1.TXT_RMV_REG_FEES,25)){"); 
				out.println("m_rmv_reg_fee=unformat_noobject(document.Form1.TXT_RMV_REG_FEES.value)");
				out.println("}"); 
				out.println("if(document.Form1.SETT_MODE.value==\"CASH\" && document.Form1.TEN_AMOUNT.value!=\"\" && isnumberok(document.Form1.TEN_AMOUNT,25)){"); 
				out.println("m_tendered=unformat_noobject(document.Form1.TEN_AMOUNT.value)");
				out.println("}"); 
				out.println("m_tot=parseFloat(m_rental_other_inv)+parseFloat(m_insurance_premium)+parseFloat(m_lux_tax)+parseFloat(m_revenue_lux)+parseFloat(m_rmv_reg_fee)"); //modified by nuwan de silva 06-07-07
				out.println("if(m_tot>m_amount_entered){");
				out.println("alert('The amount entered is more than the total value')"); 
				out.println("obj.value='';"); 
				out.println("}"); 
				out.println("else {"); 
				out.println("document.Form1.TXT_TOT_ENTERED.value=m_tot");
				out.println("m_balance=parseFloat(m_amount_entered)- parseFloat(m_tot)");
				out.println("document.Form1.TXT_BALANCE_PENDING.value=m_balance;");
				out.println("}"); 
				out.println("}");   
				out.println("}"); 
				
				
				out.println("function display_other_charges(data_vec){");
				out.println("m_table_other_charges.innerHTML=\"\" ");
				out.println("if(document.Form1.OTHER_CHARGES.value==\"Y\"){");
				out.println("header();");
				out.println("m_table_other_charges.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr ID=T_ID class=tr_input>'+");		
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">Rental and Other Invoices</TD>'+");
				out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_RENTAL_OTHER_INV maxlength=\"23\" value=\"'+data_vec[19]+'\"  size=\"10\" style=\"{text-align:right;}\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\"  disabled></TD>'+");
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">Total Amount entered</TD>'+");
				out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_TOT_ENTERED maxlength=\"23\"   value=\"'+data_vec[6]+'\" size=\"10\" onblur=\"check_number(this,25)\"  style=\"{text-align:right;}\" disabled></TD>'+");
				out.println("'</tr>'+");
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">Insurance Premium</TD>'+");
				out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\"  name=TXT_INSURANCE_PREMIUM maxlength=\"23\" value=\"'+data_vec[20]+'\" style=\"{text-align:right;}\" size=\"10\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\"  disabled></TD>'+");
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">Balance Pending</TD>'+");
				out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\"  name=TXT_BALANCE_PENDING maxlength=\"23\"   size=\"10\" value=\"0.00\" onblur=\"check_number(this,25)\" style=\"{text-align:right;}\" disabled ></TD>'+");
				out.println("'</tr>'+");
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">Luxury Tax</TD>'+");
				out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_LUX_TAX maxlength=\"23\" size=\"10\" value=\"'+data_vec[21]+'\" style=\"{text-align:right;}\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\"  disabled></TD>'+");
				out.println("'<td></td>'+");
				out.println("'<td></td></tr>'+");
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">Revenue License</TD>'+");
				out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_REVENUE_LICENCY maxlength=\"23\"  value=\"'+data_vec[22]+'\" style=\"{text-align:right;}\" size=\"10\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\"  disabled></TD>'+");
				out.println("'<td></td>'+");
				out.println("'<td></td></tr>'+");
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">RMV Registration Fees</TD>'+");
				out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_RMV_REG_FEES maxlength=\"23\" value=\"'+data_vec[23]+'\" style=\"{text-align:right;}\" size=\"10\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\"  disabled></TD>'+");
				out.println("'<td></td>'+");
				out.println("'<td></td></tr>'+");
				out.println("'</table>';");
				out.println("}");				
				out.println("}");				
				
				
				out.println("function header(){");
				out.println("m_table_other_charges.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr ID=T_ID class=tr_input>'+");		
				out.println("'<TD WIDTH=\"20%\"  align=\"left\"><b><u>Other Charges</u></TD>'+");
				out.println("'<td></td>'+");
				out.println("'<td></td>'+");
				out.println("'<td></td></tr>'+");
				out.println("'</table>';");
				out.println("}");				
				
				out.println("function get_other_charges(){");
				out.println("m_table_other_charges.innerHTML=\"\" ");
				out.println("inv.innerHTML=\"\" ");
				out.println("if(document.Form1.OTHER_CHARGES.value==\"Y\"){");
				out.println("header();");
				out.println("m_table_other_charges.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr ID=T_ID class=tr_input>'+");		
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">Rental and Other Invoices</TD>'+");
				out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_RENTAL_OTHER_INV maxlength=\"23\"  style=\"{text-align:right;}\" size=\"10\" value=\"0\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\" ></TD>'+");
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">Total Amount entered</TD>'+");
				out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_TOT_ENTERED maxlength=\"23\"   style=\"{text-align:right;}\" size=\"10\" value=\"0\" onblur=\"check_number(this,25)\"  disabled></TD>'+");
				out.println("'</tr>'+");
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">Insurance Premium</TD>'+");
				out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\"  name=TXT_INSURANCE_PREMIUM maxlength=\"23\"style=\"{text-align:right;}\"  size=\"10\" value=\"0\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\" ></TD>'+");
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">Balance Pending</TD>'+");
				out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\"  name=TXT_BALANCE_PENDING maxlength=\"23\"  style=\"{text-align:right;}\" size=\"10\" value=\"0\" onblur=\"check_number(this,25)\" disabled ></TD>'+");
				out.println("'</tr>'+");
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">Luxury Tax</TD>'+"); //modified by nwuan de silva  04-07-07
				out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_LUX_TAX maxlength=\"23\" size=\"10\"  style=\"{text-align:right;}\" value=\"0\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\" ></TD>'+");
				out.println("'<td></td>'+");
				out.println("'<td></td></tr>'+");
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">Revenue License</TD>'+");
				out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_REVENUE_LICENCY maxlength=\"23\"   style=\"{text-align:right;}\" size=\"10\" value=\"0\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\" ></TD>'+");
				out.println("'<td></td>'+");
				out.println("'<td></td></tr>'+");
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">RMV Registration Fees</TD>'+");
				out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_RMV_REG_FEES maxlength=\"23\"  style=\"{text-align:right;}\" size=\"10\" value=\"0\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\" ></TD>'+");
				out.println("'<td></td>'+");
				out.println("'<td></td></tr>'+");
				out.println("'</table>';");
				out.println("   document.Form1.TXT_BALANCE_PENDING.value=format_noobject(document.Form1.AMOUNT.value);");
				out.println("}");		
				out.println("}");			
				
				out.println("function set_address_pay_type(){");
				out.println("if(document.Form1.PAY_TYPE.value!=\"CLIENT\"){");
				
				out.println("  document.Form1.elements['CLIENT_NAME_1'].value  =document.Form1.CLIENT_NAME.value;");
				out.println("  document.Form1.elements['CLIENT_ADDRESS_1'].value  =document.Form1.CLIENT_ADDRESS.value;");
				out.println("}");				
				out.println("}");				
				//-------------------------------------------------------------
				out.println("function enable_check_date(){");
				out.println("m_table_account_no.innerHTML=\"\" ");
				out.println("m_table_tendered.innerHTML=\"\" ");
				out.println("m_table_cheque.innerHTML=\"\" ");
				//out.println("  PBRANCH.style.color='black';");
				out.println("if(document.Form1.SETT_MODE.value==\"STD_ORD\" || document.Form1.SETT_MODE.value==\"DIR_DEP\" ){");
				out.println("m_table_account_no.innerHTML+='<table align=\"left\" width=\"100%\" class=\"table\" border=\"0\"><tr ID=T_ID >'+");		
				out.println("'<TD WIDTH=\"20%\"  ID=ACNO align=\"left\">Account Number *</TD>'+");
				out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ACCOUNT_NO maxlength=\"10\" size=\"10\" onblur=\"makeRequest_account_no(this)\" >'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_ACCOUNT_NO  value=\"Help\" onClick=\"account_number_help()\"></TD>'+");
				out.println("'<td >Branch Name</td>'+");
				out.println("'<td ><input name=\"BRANCH_NAME\" type=\"text\" style=\"width:340px;\" maxlength=\"200\" class=\"txt_input\" disabled></td>'+");
				out.println("'</tr></table>';");
				out.println("}");				
				out.println("else if(document.Form1.SETT_MODE.value==\"CASH\"){");
				out.println("m_table_tendered.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr ID=T_ID class=tr_input>'+");		
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">Tendered Amount</TD>'+");
				out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TEN_AMOUNT maxlength=\"23\"  size=\"25\" onchange =\"calculate_balance(this)\" onblur=\"get_returned_value(this,25)\" ></TD>'+");
				out.println("'<td></td>'+");
				out.println("'<td></td></tr>'+");
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">Returned Amount</TD>'+");
				out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=RET_AMOUNT maxlength=\"23\"   size=\"10\" onblur=\"cal_retamt(this)\" disabled ></TD>'+");
				out.println("'<td></td>'+");
				out.println("'<td></td></tr>'+");
				out.println("'</table>';");
				out.println("}");	
				
				
				out.println("enable_branch();");
				out.println("add_row_cheque();");				
				
				out.println("disable_val_date()");
				
				
				out.println("}");	
				
				
				out.println("function disable_val_date(){");
				out.println("if((document.Form1.SETT_MODE.value==\"CASH\")||(document.Form1.SETT_MODE.value==\"CHEQUE\")){"); 
				out.println("document.Form1.VAL_DAY.disabled=true;");
				out.println("document.Form1.VAL_MONTH.disabled=true;");
				out.println("document.Form1.VAL_YEAR.disabled=true;");
				out.println("}else{");	
				out.println("document.Form1.VAL_DAY.disabled=false;");
				out.println("document.Form1.VAL_MONTH.disabled=false;");
				out.println("document.Form1.VAL_YEAR.disabled=false;");			
				out.println("}");	
				out.println("}");
				
				
				out.println("function add_row_cheque(){");
				out.println("if(document.Form1.SETT_MODE.value!=\"CASH\" ){");
				out.println("m_table_cheque.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr class=tr_input>'+");		
				out.println("'<TD WIDTH=\"20%\"  ID=CARNO align=\"left\">Cheque No/Ref. No *</TD>'+");
				out.println("'<TD colspan=\"2\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=CHEQUE_NO maxlength=\"8\"  size=\"22\" onblur=validate_cheque_no()></TD>'+");
				out.println("'<td width=\"*%\">&nbsp;</td></tr>'+");
				out.println("'<tr ><TD WIDTH=\"20%\" ID=CDATE align=\"left\">Cheque Date *</TD>'+");
				out.println("'<td width=\"11%\"><input name=CHEQUE_DATE_DD   type=\"text\" maxlength=\"2\" class=\"txt_input\" style=\"width:25px\" onBlur=\"checkMonthLength(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)\">'+ ");
				out.println("'<input name=CHEQUE_DATE_MM   type=\"text\" maxlength=\"2\" class=\"txt_input\" style=\"width:25px\" onBlur=\"checkMonthLength(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)\" >'+ ");
				out.println("'<input name=CHEQUE_DATE_YY   type=\"text\" maxlength=\"4\" class=\"txt_input\" style=\"width:45px\"   onBlur=\"checkMonthLength(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)\"><a href style=\"{cursor:hand; }\" onclick=load_calendar(\"3\")>Calendar</a></td>'+ "); //
				//out.println("'<td width=\"6%\" style=\"{cursor:hand; }\" onclick=load_calendar(\"3\")>Calendar</td>'+");
				out.println("'<td width=\"6%\" ></td>'+");
				out.println("'<td width=\"*%\">&nbsp;</td></tr>'+");
				
				
				out.println("'<tr class=tr_input>'+");
				out.println("'<td ID=PACC width=\"20%\" >Payer Account </td>'+");
				out.println("'<td width=\"30%\"><input name=\"PAY_ACCOUNT\"   type=\"text\" maxlength=\"20\" onblur=\"makeRequest4(this.value)\"  class=\"txt_input\"  >'+ ");
				out.println("'<input type=button name=accno_help value=... class=\"but_input\" onclick=\"account_help()\" ></td>'+");
				out.println("'<td ></td>'+");
				out.println("'<td></td>'+");
				out.println("'</tr>'+");
				
				out.println("'<tr class=tr_input>'+");
				out.println("'<td  width=\"20%\" ID=PBRANCH>Payer Branch *</td>'+");
				out.println("'<td width=\"30%\" > <input name=\"PAY_BRANCH\"   type=\"text\" maxlength=\"10\"  onblur=\"makeRequest_branch(this.value)\" class=\"txt_input\" >'+ ");
				out.println("'<input type=button name=BUT_PAY_BRANCH value=... class=\"but_input\" onclick=\"branch_help()\" ></td>'+");
				out.println("'<td ></td>'+");
				out.println("'<td ></td>'+");
				out.println("'</tr>'+");
				
				out.println("'<tr class=tr_input>'+");
				out.println("'<td  width=\"20%\">Payer Branch Name</td>'+");
				out.println("'<td width=\"40%\" > <input name=\"PAY_BRANCH_NAME\"   type=\"text\" maxlength=\"100\"  onblur=\"\" class=\"txt_input\" style=\"width: 200px\" disabled></td>'+ ");
				out.println("'<td ></td>'+");
				out.println("'<td ></td>'+");
				out.println("'</tr>'+");
				
				out.println("'</table>';");
				out.println("assign_sysdate();"); 
				
				out.println("}");
				out.println("}");
				
				
				
				out.println("function display_row_cheque(data_vec){");
				out.println("m_table_cheque.innerHTML=\"\" ");
				out.println("if(document.Form1.SETT_MODE.value!=\"CASH\" ){");
				out.println("m_table_cheque.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr class=tr_input>'+");		
				out.println("'<TD WIDTH=\"20%\"  ID=CARNO align=\"left\">Cheque No/Ref. No *</TD>'+");
				out.println("'<TD colspan=\"2\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=CHEQUE_NO maxlength=\"8\"  size=\"22\" onblur=validate_cheque_no() value=\"'+data_vec[12]+'\" disabled></TD>'+");
				out.println("'<td width=\"*%\">&nbsp;</td></tr>'+");
				out.println("'<tr ><TD WIDTH=\"20%\" ID=CDATE align=\"left\">Cheque Date *</TD>'+");
				out.println("'<td width=\"11%\"><input name=CHEQUE_DATE_DD   type=\"text\" maxlength=\"2\" class=\"txt_input\" style=\"width:25px\" value=\"'+data_vec[14]+'\" disabled>'+ ");
				out.println("'<input name=CHEQUE_DATE_MM   type=\"text\" maxlength=\"2\" class=\"txt_input\" style=\"width:25px\"  value=\"'+data_vec[15]+'\" disabled>'+ ");
				out.println("'<input name=CHEQUE_DATE_YY   type=\"text\" maxlength=\"4\" class=\"txt_input\" style=\"width:45px\"   value=\"'+data_vec[16]+'\" onBlur=\"check_date_value()\" disabled></td>'+ "); //<a href style=\"{cursor:hand; }\" onclick=load_calendar(\"3\")>Calendar</a>
				out.println("'<td width=\"6%\" style=\"{cursor:hand; }\" onclick=load_calendar(\"3\") disabled >Calendar</td>'+");
				out.println("'<td width=\"*%\">&nbsp;</td></tr>'+");
				
				out.println("'<tr class=tr_input>'+");
				out.println("'<td ID=PACC width=\"20%\" >Payer Account </td>'+");
				out.println("'<td width=\"30%\"><input name=\"PAY_ACCOUNT\"   type=\"text\" maxlength=\"20\" onblur=\"makeRequest4(this.value)\"  class=\"txt_input\"  >'+ ");
				out.println("'<input type=button name=accno_help value=... class=\"but_input\" onclick=\"account_help()\" ></td>'+");
				out.println("'<td ></td>'+");
				out.println("'<td></td>'+");
				out.println("'</tr>'+");
				
				out.println("'<tr class=tr_input>'+");
				out.println("'<td  width=\"20%\" ID=PBRANCH>Payer Branch *</td>'+");
				out.println("'<td width=\"30%\" > <input name=\"PAY_BRANCH\"   type=\"text\" maxlength=\"10\"  onblur=\"makeRequest_branch(this.value)\" class=\"txt_input\" >'+ ");
				out.println("'<input type=button name=BUT_PAY_BRANCH value=... class=\"but_input\" onclick=\"branch_help()\" ></td>'+");
				out.println("'<td ></td>'+");
				out.println("'<td ></td>'+");
				out.println("'</tr>'+");
				
				out.println("'<tr class=tr_input>'+");
				out.println("'<td  width=\"20%\">Payer Branch Name</td>'+");
				out.println("'<td width=\"40%\" > <input name=\"PAY_BRANCH_NAME\"   type=\"text\" maxlength=\"100\"  onblur=\"\" class=\"txt_input\" style=\"width: 200px\" disabled></td>'+ ");
				out.println("'<td ></td>'+");
				out.println("'<td ></td>'+");
				out.println("'</tr>'+");
				
				out.println("'</table>';");
				out.println("}");
				
				out.println("}");
				
				
				out.println("function assign_sysdate(){"); //Added by Chandana For Ref No.875 on 15/10/2007
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_sysdate\";");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','ChequeDate');");
				out.println("}");
				
				
				
				
				out.println("function enable_branch(){");
				
				out.println("if(document.Form1.SETT_MODE.value==\"CHEQUE\" || document.Form1.SETT_MODE.value==\"STD_ORD\" ){");
				
				//out.println("document.Form1.PAY_ACCOUNT.disabled=false;");
				//out.println("document.Form1.accno_help.disabled=false;");
				
				
				out.println("}");				
				out.println("else");
				out.println("{");				
				
				//out.println("document.Form1.PAY_ACCOUNT.value='';");
				//out.println("document.Form1.PAY_BRANCH.value='';");  
				//out.println("document.Form1.PAY_BRANCH_NAME.value='';");
				
				//out.println("document.Form1.PAY_ACCOUNT.disabled=true;");
				//out.println("document.Form1.PAY_BRANCH.disabled=true;");
				//out.println("document.Form1.accno_help.disabled=true;"); 
				//out.println("document.Form1.PAY_BRANCH_NAME.disabled=true;");
				out.println("}");				
				
				out.println("}");				
				
				out.println("function check_date_value(){");
				
				out.println("if(document.Form1.CHEQUE_DATE_DD.value!='' && document.Form1.CHEQUE_DATE_MM.value!='' && document.Form1.CHEQUE_DATE_YY.value!='')");
				out.println("checkMonthLength(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)");
				
				out.println("}");				
				
				out.println("function check_status_inv(num2,num1) {");
				out.println("if(document.Form1.elements['Text_standard'+num2].checked){");
				out.println("  document.Form1.elements['Text_standard'+num2].value       =\"YES\";");
				out.println("}else{");
				out.println("  document.Form1.elements['Text_sett_amount'+num2].value    =0;");
				out.println("  document.Form1.elements['Text_sett_amount'+num2].disabled =false;");
				out.println("  document.Form1.elements['Text_standard'+num2].value       =\"NO\";");
				out.println("}");
				out.println("}");
				
				out.println("function cal_amount(num2,num1) {");//
				out.println("  document.Form1.hid_win_opt.value=num2;");
				out.println("  document.Form1.hid_opt_val.value=num1;");
				out.println("  m_inv_bal  = 0;");
				out.println("  m_inv_allo = 0;");
				out.println("  m_rec_allo = 0;");
				
				out.println("  m_rec_bal  = parseFloat(unformat_noobject(document.Form1.elements['AMOUNT'].value));");
				
				out.println("    for(j=0;j<parseFloat(document.Form1.elements['hid_invoice_count'].value);j++){");	
				out.println("        m_rec_allo = parseFloat(m_rec_allo)+parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+j].value)) ");	
				out.println("    }");	
				
				out.println("   if(parseFloat(m_rec_bal)<parseFloat(m_rec_allo)){"); 
				out.println("      document.Form1.elements['Text_standard'+num2].value      =\"NO\";");
				out.println("      document.Form1.elements['Text_sett_amount'+num2].disabled=false;");
				out.println("      document.Form1.elements['Text_sett_amount'+num2].value   =0;");
				out.println("      document.Form1.elements['Text_standard'+num2].checked    =false;");
				
				out.println("     }else{");	
				out.println("      document.Form1.elements['Text_standard'+num2].value      =\"YES\";");
				out.println("      document.Form1.elements['Text_sett_amount'+num2].disabled=true;");
				out.println("     }");	
				out.println("}");	
				
				
				out.println("function cal_amount_update(num2,num1) {");
				
				out.println("  document.Form1.hid_win_opt.value=num2;");
				out.println("  document.Form1.hid_opt_val.value=num1;");
				out.println("  m_inv_bal  = 0;");
				out.println("  m_inv_allo = 0;");
				out.println("  m_rec_allo = 0;");
				
				out.println("if(document.Form1.OTHER_CHARGES.value==\"N\"){");
				out.println("  m_rec_bal  = parseFloat(unformat_noobject(document.Form1.elements['AMOUNT'].value));");
				out.println("    }");	
				out.println("    else {");	
				out.println("  m_rec_bal  = parseFloat(unformat_noobject(document.Form1.elements['TXT_RENTAL_OTHER_INV'].value));");
				out.println("    }");	
				out.println("    for(j=0;j<parseFloat(document.Form1.elements['hid_invoice_count'].value);j++){");	
				out.println("        m_rec_allo = parseFloat(m_rec_allo)+parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+j].value)) ");	
				out.println("    }");	
				
				out.println("   if(parseFloat(m_rec_bal)<parseFloat(m_rec_allo)){"); 
				out.println("      document.Form1.elements['Text_standard'+num2].value      =\"NO\";");
				out.println("      document.Form1.elements['Text_sett_amount'+num2].disabled=false;");
				out.println("      document.Form1.elements['Text_sett_amount'+num2].value   =0;");
				out.println("      document.Form1.elements['Text_standard'+num2].checked    =false;");
				
				out.println("     }else{");	
				out.println("      document.Form1.elements['Text_standard'+num2].value      =\"YES\";");
				out.println("      document.Form1.elements['Text_sett_amount'+num2].disabled=true;");
				out.println("      calcualte_values(num2,num1);");	
				
				out.println("     }");	
				out.println("}");	
				
				
				out.println("function calcualte_values(num2,num1) {");
				
				out.println("  m_rec_allo = 0;");
				out.println("  m_rec_bal = 0;");
				out.println("  m_rec_balance_new = 0;");
				out.println("  m_balance_val = 0;");
				
				out.println("    for(j=0;j<parseFloat(document.Form1.elements['hid_invoice_count'].value);j++){");	
				out.println("        m_rec_allo = parseFloat(m_rec_allo)+parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+j].value)) ");	
				out.println("    }");	
				
				out.println("if(document.Form1.OTHER_CHARGES.value==\"N\"){");
				out.println("  m_rec_bal  = parseFloat(unformat_noobject(document.Form1.elements['AMOUNT'].value));");
				out.println("    }");	
				out.println("    else {");	
				out.println("  m_rec_bal  = parseFloat(unformat_noobject(document.Form1.elements['TXT_RENTAL_OTHER_INV'].value));");
				out.println("    }");	
				
				
				out.println("  if(m_rec_bal>m_rec_allo){  ");	
				
				out.println("m_rec_balance_new=m_rec_bal - m_rec_allo ;");
				
				out.println("m_balance_val=parseFloat(unformat_noobject(document.Form1.elements['BAL_AM_'+num2].value));");
				
				out.println("if(m_rec_balance_new > m_balance_val ){"); //&& document.Form1.elements['Text_sett_amount'+num2].value>0
				out.println(" document.Form1.elements['Text_sett_amount'+num2].value =m_balance_val;");
				out.println("format_number(document.Form1.elements['Text_sett_amount'+num2],23)");
				out.println("    }");	
				
				out.println("else if(document.Form1.elements['Text_sett_amount'+num2].value==0 ){"); //&& document.Form1.elements['Text_sett_amount'+num2].value>0
				out.println(" document.Form1.elements['Text_sett_amount'+num2].value =m_rec_balance_new;");
				out.println("format_number(document.Form1.elements['Text_sett_amount'+num2],23)");
				out.println("    }");	
				out.println("    }");	
				out.println("}");	
				
				
				out.println("function get_returned_value(obj,size) {");
				
				
				out.println("if(obj.value!=\"\" ){");
				
				out.println("if(isnumberok(obj,size)){");
				out.println("if(parseFloat(unformat_noobject(obj.value))>=parseFloat(unformat_noobject(document.Form1.AMOUNT.value))){ ");			 //modified by nuwan de silva 06-07-07
				out.println(" document.Form1.hid_help_status.value='H_tendered' ");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_settlement_tendered_amount&data_val1=\"+unformat_noobject(document.Form1.AMOUNT.value)+\"&data_val2=\"+unformat_noobject(document.Form1.TEN_AMOUNT.value);");
				out.println("	 load_interface(m_url,'XML');");
				out.println("format_number(obj,size)");
				out.println("}");			
				
				out.println("else");
				out.println("{");
				out.println("alert('Tendered amount can not be less than amount');");
				out.println("obj.value=''");
				out.println("document.Form1.RET_AMOUNT.value=''");
				out.println("obj.focus();");
				out.println("}");
				
				out.println("}");
				
				out.println("else");
				out.println("{");
				out.println("alert('Please enter a number');");
				out.println("obj.value=''");
				out.println("document.Form1.RET_AMOUNT.value=''");
				out.println("obj.focus();");
				
				out.println("}");
				
				out.println("}");
				
				out.println("}");
				
				
				out.println("function cal_retamt(val) {");
				out.println("if(document.Form1.RET_AMOUNT.value!=\"\"){ ");
				out.println("format_number(document.Form1.RET_AMOUNT,29)");
				out.println("if(format_number2(document.Form1.RET_AMOUNT,29)){");
				out.println("val=unformat_noobject(document.Form1.RET_AMOUNT.value)");
				out.println("}");
				out.println("}");
				out.println("}");
				
				out.println("function validate_cheque_no() {");
				out.println("if(document.Form1.SETT_MODE.value!='CASH' ){");
				out.println("if(document.Form1.CHEQUE_NO.value!='' && document.Form1.PAY_BRANCH.value!='' ){");
				out.println("document.Form1.hid_help_status.value='VAL_CHEQUE'");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_SETT_RECEIPT_CHEQUE_VALIDATE&data_val='+document.Form1.CHEQUE_NO.value+'&branch_code='+document.Form1.PAY_BRANCH.value+'';");
				out.println("load_interface(m_url,'XML');");
				out.println("}");
				out.println("}");
				out.println("}");
				
				
				out.println("	function chk_comment_length(obj){ ");
				out.println(" var remarks_length=obj.value.toString().length;");
				out.println("if(remarks_length>obj.maxlength) ");
				out.println("		window.event.keyCode=\"\"; ");
				out.println("} ");
				
				out.println("function count_length(obj){ ");
				out.println("var remarks_length=obj.value.toString().length; ");
				out.println("var remarks=obj.value.toString(); ");
				out.println("if(remarks_length>obj.maxlength){ ");
				out.println("obj.value=remarks.substring(0,obj.maxlength); ");
				out.println("} ");
				out.println("} ");
				
				
				out.println("function display_row_third_party(){");
				out.println("m_table_third_party_del.innerHTML=\"\" ");
				
				out.println("if(document.Form1.PAY_TYPE.value==\"THIRD\"){"); 
				out.println("m_table_third_party_del.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr class=tr_input>'+");
				out.println("'<td width=\"20%\" id=pay_name>Group Code</td>'+");
				out.println("'<td width=\"30%\"><input name=\"CLIENT_NAME_1\" type=\"text\" style=\"width:230px;\" maxlength=\"200\" class=\"txt_input\" onblur=\"makeRequest_third(this.value)\" >'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_GROUP_HELP  value=\"...\" onClick=\"group_help()\"></TD></tr>'+");
				out.println("'<tr class=tr_input><td width=\"20%\" id=pay_name>Group Name</td>'+");
				out.println("'<td width=\"30%\"><input name=\"GROUP_NAME\" type=\"text\" style=\"width:250px;\" maxlength=\"200\" class=\"txt_input\" disabled></td>'+");
				out.println("'<td id=pay_address>Address</td>'+");
				out.println("'<td> <input name=\"CLIENT_ADDRESS_1\" type=\"text\" style=\"width:330px;\" maxlength=\"200\" class=\"txt_input\" disabled>'+");
				out.println("'</td>'+");
				out.println("'</tr>'+");	
				out.println("'</table>';");
				out.println("}");
				out.println("}");
				out.println("function group_help(){");
				out.println("Crit=document.Form1.CLIENT_NAME_1.value+\"@\"+\"Y@\";");
				out.println("HelpBox('1','10','0',Crit,'m_help_TXT_GRP_INV_sql','120');");
				out.println("}");
				
				out.println("function assign_group_help(oBj){");
				out.println("document.Form1.CLIENT_NAME_1.value=oBj.valout[2]");
				out.println("document.Form1.GROUP_NAME.value=oBj.valout[3]");
				out.println("document.Form1.CLIENT_ADDRESS_1.value=oBj.valout[4]");
				out.println("}");
				
				out.println("function makeRequest_group() {");
				out.println("if(document.Form1.OPTION_DESC.value =='New'){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement_group?chksql=get_group_details&m_value=\"+document.Form1.AMOUNT.value+\"&group_code=\"+document.Form1.CLIENT_NAME_1.value+\"\";");
				out.println("}");
				out.println("if(document.Form1.OPTION_DESC.value =='DELETE'){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement_group?chksql=get_group_receipt&rec_no=\"+document.Form1.RECEIPT_NO.value+\"\";");
				out.println("}");
				
				
				out.println("	load_interface(m_url,'NORM');");
				out.println("}");
				
				out.println("function fill_data(){");
				out.println("document.Form1.REP_AMOUNT.value= document.Form1.AMOUNT.value");
				out.println("if(document.Form1.REP_AMOUNT.value!=\"\"){");
				out.println("format_number(document.Form1.REP_AMOUNT,25)");
				out.println(" }");
				out.println("if(document.Form1.CLIENT_NAME_1.value!=\"\" && document.Form1.AMOUNT.value!=\"0\"){");
				out.println("makeRequest_group()");
				out.println("}");
				/*out.println("else{");
				out.println("alert('Please Select a Group code First')");
				out.println("document.Form1.AMOUNT.value=\"\"");
				out.println("document.Form1.AMOUNT.focus()");
				out.println("document.Form1.REP_AMOUNT.value=\"\"");
				out.println("}");*/
				out.println("}");
				
				out.println("function check_client_amount(obj,size){");
				out.println("if(obj.value!='')"); 
				out.println("if(isnumberok(obj,22)){"); 
				out.println("format_number(obj,22)"); 
				out.println("m_store=1");
				out.println("}"); 
				out.println("else{");
				out.println("alert('please enter a number');"); 
				out.println("obj.value='0.00';"); 
				out.println("obj.focus();"); 
				out.println("m_store=0");
				out.println("}");
				out.println("m_store=1");
				out.println("}"); 
				
				/*
				out.println("function check_client_amount(objval,row){");
				out.println("if(objval.value!=\"\"){");
				out.println("format_number(objval,22)");
				out.println("}");
				
				out.println("}");*/
				
				out.println("function total_check(objval,row){");
				out.println("var m_sum=0");		
				out.println("var m_tot_sum=0");	
				out.println("if(m_store!=\"0\"){");
				out.println("for(var t=0;t<document.Form1.hid_count_client.value;t++){");
				out.println("m_sum=m_sum+parseFloat(unformat_noobject(document.Form1.elements[\"TXT_CLIENT_AMOUNT_\"+t].value))");
				out.println("}");
				out.println("document.Form1.TXT_ALL_TOT_AMT.value=m_sum");
				out.println("format_number(document.Form1.TXT_ALL_TOT_AMT,22)");
				out.println("document.Form1.TXT_TOT_AMT.value=Math.abs(parseFloat(unformat_noobject(document.Form1.AMOUNT.value))-m_sum)");
				out.println("format_number(document.Form1.TXT_TOT_AMT,22)");
				out.println("}");
				out.println("}");
				
				out.println("function store_val(objval){");				
				out.println("if(document.Form1.CLIENT_NAME_1.value==\"\"){");
				out.println("alert('Please select a Group code first')");
				out.println("document.Form1.AMOUNT.value=\"\"");
				out.println("document.Form1.REP_AMOUNT.value=\"\"");
				out.println("document.Form1.CLIENT_NAME_1.focus()");
				out.println("}");	
				out.println("else {");
				out.println("if(objval.value!=\"0\"){");
				out.println("format_number(objval,25)");
				out.println("if(!format_number(objval,25)){");
				out.println("document.Form1.REP_AMOUNT.value=\"\"");
				out.println(" get_group_details.innerHTML = ''; ");
				out.println("}");			
				out.println("}");	
				out.println("else if(objval.value==\"0\" || objval.value==\"0.00\" || objval.value==\"\"){");
				out.println("alert('Amount cannot be Zero')");
				out.println("document.Form1.AMOUNT.focus()");
				out.println("document.Form1.REP_AMOUNT.value=\"\"");
				out.println(" get_group_details.innerHTML = ''; ");
				out.println("}");			
				out.println("}");
				out.println("}");			
				
				//-------------------------------------------------------------------------------------------------------			
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_out_value();display_row_third_party();add_row_cheque();\">");  //disable_val_date();
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input type='hidden' name='Hid_scr_name' value='AF_RE_SETTELMENT' > ");
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\"> ");
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\"> ");
				
				out.println("<INPUT TYPE='Hidden' NAME='hid_return_count' VALUE=\"\">"); 
				
				out.println("<INPUT TYPE='Hidden' NAME='hid_date' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"NEW\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_win_type' VALUE=\"Main\">"); 			
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"New\">");//Modified Nuwan De Silva 17-05-07
				out.println("<input type=hidden name=\"tot_val\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_opt_val\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_win_opt\" value=\"0\">");
				
				out.println("<input type=hidden name=\"hid_TXT_ACCOUNT_NO\" value=\"\">");
				out.println("<input type=hidden name=\"hid_TEN_AMOUNT\" value=\"\">");
				out.println("<input type=hidden name=\"hid_RET_AMOUNT\" value=\"\">");
				out.println("<input type=hidden name=\"hid_TXT_RENTAL_OTHER_INV\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_INSURANCE_PREMIUM\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_TXT_LUX_TAX\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_TXT_REVENUE_LICENCY\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_TXT_RMV_REG_FEES\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_TXT_BRANCH_CODE\" value=\"\">");
				out.println("<input type=hidden name=\"hid_TXT_ACC_REF_NO\" value=\"\">");
				out.println("<input type=hidden name=\"hid_TXT_STORE\" value=\"\">");
				rs = stmt.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') "+
					" FROM   DUAL");
				
				
				if(rs.next()){
					m_sysdate=rs.getString(1);
				}
				
				out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\" class=table>");
				out.println("<tr>");
				
				out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
				out.println("<td class=\"border_wht\" valign=\"top\"> ");
				out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" class=table cellpadding=\"0\" height=\"100%\">");
				out.println("<tr> ");
				out.println("<td height=\"6%\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
				out.println("</tr>");
				out.println("<tr> ");
				out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td style=\"height: 327px\">");
				
				
				out.println("<table border=\"0\" cellpadding=\"0\" class=table cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
				out.println("<tr>");
				out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" height=\"2%\" id=help_box></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				
				out.println("<table cellpadding=\"2\" cellspacing=\"2\" border=\"0\" class=table>");
				out.println("<tr>");
				out.println("<td><input type=button name=reset value=\"New\" class=mainbut onclick=load_screen_status(\"NEW\"); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_out_value();'></td>");//document.Form1.OPTION_DESC.value
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=Dele value=\"Delete\" class=mainbut onclick=load_screen_status(\"DELETE\"); onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_out_value();'></td>");
				out.println("<td width=10%>&nbsp;</td>");
				out.println("<td ><input type=button name=b_submit value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_out_value();'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				
				out.println("<td><input type='button' name=help class=mainbut value=\"Help\" onclick=load_screen_status(\"HELP\"); >  </td>");  //Added By Nuwan De Silv 17-05-05
				
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Reset\");' onmouseout='load_roll_out_value();'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_out_value();'></td>");
				out.println("<td>&nbsp;&nbsp;&nbsp;</td>");			
				out.println("<td><input type=button name=Doc_1 value=\"Document\" class=mainbut onclick=show_document(); onMouseOver='load_roll_value(\"Document\");' style='width: 130px' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				
				out.println("</tr></table>");
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				
				out.println("<table align=\"center\" border=\"0\"  width=\"100%\" class=table>"); 
				out.println("<tr class=tr_input>");
				out.println("<td width=\"20%\" id=RNO>Receipt No *</td>");
				out.println("<td width=\"30%\"><input name=\"RECEIPT_NO\" type=\"text\" maxlength=\"15\" class=\"txt_input\" onblur=\"makeRequest6(this.value)\"  onchange=check_client() disabled > ");
				out.println("<input type=button name=rec_help value=... class=\"but_input\" onclick=\"receipt_help()\" disabled ></td>");
				out.println("</td>");
				out.println("<td >&nbsp;</td>");
				out.println("<td>&nbsp; ");
				out.println("</td>");
				out.println("</tr>");		
				
				
				out.println("<tr class=tr_input>");
				out.println("<td width=\"20%\" >Settlement Mode</td>");
				out.println("<td width=\"30%\"> <SELECT name=\"SETT_MODE\" class=\"txt_input\" onChange=\"enable_check_date()\"> ");
				out.println("<OPTION value=\"CHEQUE\">Cheque</OPTION>");
				out.println("<OPTION value=\"CASH\">Cash</OPTION>");
				out.println("<OPTION value=\"STD_ORD\">Standing Order</OPTION>");
				out.println("<OPTION value=\"DIR_DEP\">Direct Deposit</OPTION>");
				out.println("</SELECT></TD>");
				out.println("<td >&nbsp;</td>");
				out.println("<td>&nbsp;");
				out.println("</td>");
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td ID=VDATE width=\"20%\">Value Date *</td>");
				out.println("<td width=\"30%\"><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" value="+m_sysdate.substring(0,2)+" onblur=checkMonthLength(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" value="+m_sysdate.substring(3,5)+" onblur=checkMonthLength(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" value="+m_sysdate.substring(6,10)+" onblur=checkMonthLength(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("</td>");
				out.println("<td >&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("</tr>");
				
				out.println("</table>");	    
				
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class='table'>"); 
				out.println("<tr > ");  
				out.println("<td width=\"100%\"><DIV ID='m_table_account_no'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");	 
				
				
				out.println("<table align='center'  width='100%' border=\"0\" class='table'>"); 				
				
				out.println("<tr class=tr_input>");
				out.println("<td width=\"20%\">Payee Type</td>");
				out.println("<td width=\"30%\"><SELECT name=\"PAY_TYPE\" class=\"txt_input\" onChange=\" display_row_third_party(), set_address_pay_type()\" > ");
				//	out.println("<OPTION value=\"CLIENT\">Client</OPTION>");
				out.println("<OPTION value=\"THIRD\" selected>Third Party</OPTION>");
				out.println("</SELECT></TD>");
				out.println("<td >&nbsp;</td>");
				out.println("<td >&nbsp;</td> ");
				out.println("</tr>");
				
				out.println("</table>");	   
				
				out.println("<table align=\"center\" width=\"100%\" class='table'>"); 
				out.println("<tr > ");  
				out.println("<td width=\"100%\"><DIV ID='m_table_third_party_del'></DIV></td>"); 
				out.println("</tr>"); 
				out.println("</table>");
				//-----------------------------------------------
				out.println("<table align=\"center\" width=\"100%\" class='table'>"); 
				out.println("<tr > ");  
				out.println("<td width=\"100%\"><DIV ID='m_table_cheque'></DIV></td>"); 
				out.println("</tr>"); 
				out.println("</table>");
				
				/*
				out.println("<table align='center'  width='100%' class='table'>");
				out.println("<tr class=tr_input>");
				out.println("<td ID=PACC width=\"20%\" >Payer Account </td>");
				out.println("<td width=\"30%\"><input name=\"PAY_ACCOUNT\"   type=\"text\" maxlength=\"20\"  class=\"txt_input\"  > ");//onblur=\"makeRequest4(this.value)\"
				out.println("<input type=button name=accno_help value=Help class=\"but_input\" onclick=\"account_help()\" ></td>");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td  width=\"20%\" ID=PBRANCH>Payer Branch *</td>");
				out.println("<td width=\"30%\" > <input name=\"PAY_BRANCH\"   type=\"text\" maxlength=\"10\"  onblur=\"makeRequest_branch(this.value)\" class=\"txt_input\" > ");
				out.println("<input type=button name=BUT_PAY_BRANCH value=Help class=\"but_input\" onclick=\"branch_help()\" ></td>");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
			
   			out.println("<tr class=tr_input>");
				out.println("<td  width=\"20%\">Payer Branch Name</td>");
				out.println("<td width=\"40%\" > <input name=\"PAY_BRANCH_NAME\"   type=\"text\" maxlength=\"100\"  onblur=\"\" class=\"txt_input\" style=\"width: 200px\" disabled> ");
				out.println("</td>");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				

				out.println("</table>");	  */
				
				//----------------------------------------------
				out.println("<table align='center'  width='100%' class='table'>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td ID=CURR width=\"20%\">Currency </td>");
				out.println("<td width=\"30%\"><SELECT onchange=get_excharate() name=CURR_CODE class=\"txt_input\" > ");
				rs = stmt.executeQuery ("SELECT CURR_CODE, CURR_SYMBOL, REP_CURR "+
					"FROM   "+m_schema_name+".AF_CO_MAS_CURRENCY "+
					"WHERE ACTIVE_STATUS='Y' "+
					//"AND CURR_CODE='SLR' "+
					"ORDER  BY DEFAULT_VALUE DESC ");
				while(rs.next()){
					
					if(rs.getString(3).equals("Y"))
					{
						
						m_rep_cur=rs.getString(2);
					}
					out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</OPTION>");
				}
				out.println("<input type=hidden name=hid_rep_cur value="+m_rep_cur+">");
				out.println("</td>");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				
				
				out.println("<tr class=tr_input>");
				out.println("<td ID=OTH_CHARGE width=\"20%\">Receipt Amount Include Other Charges </td>");
				out.println("<td width=\"30%\"><SELECT  onchange=get_other_charges() name=OTHER_CHARGES class=\"txt_input\" > ");
				out.println("<OPTION value=\"N\" selected>No</OPTION>");
				out.println("</SELECT></TD>");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				
				out.println("</tr>");
				
				
				
				out.println("<tr class=tr_input>");
				out.println("<td ID=AMOU width=\"20%\">Amount *</td>"); 
				out.println("<td width=\"30%\"><input name=\"AMOUNT\" type=\"text\" maxlength=\"20\" class=\"txt_input\" onblur=store_val(this) onchange=fill_data() STYLE=\"{text-align:right;}\" >");
				out.println("</td>");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				
				
				
				out.println("<tr class=tr_input>");
				out.println("<td id=EXCH width=\"20%\">Exchange Rate *</td>");
				out.println("<td width=\"30%\"><input name=\"EXCHANE_RATE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" onblur=format_number3(this,4) value=\"1\"  STYLE=\"{text-align:right;}\">"); // added validations by udara on 13/10/2009
				out.println("</td>");
				out.println("<td ></td>");
				out.println("<td> ");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td ID=RAMO width=\"20%\" >Rep. Curr. Amount *</td>");
				out.println("<td width=\"30%\" > <input name=\"REP_AMOUNT\"   type=\"text\" maxlength=\"25\"  class=\"txt_input\" onchange=cal_exc_rate(document.Form1.REP_AMOUNT.value) STYLE=\"{text-align:right;}\"> ");
				out.println("</td>");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("</table>");	   
				
				out.println("<table align=\"center\" width=\"100%\" class='table'>"); 
				out.println("<tr > ");  
				out.println("<td width=\"100%\"><DIV ID='m_table_other_charges'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");	
				
				out.println("<table align=\"center\" width=\"100%\" class='table'>"); 
				out.println("<tr > ");  
				out.println("<td width=\"100%\"><DIV ID='m_table_tendered'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");	    
				
				/*out.println("<table align='center'  width='100%' class='table'>"); 				
					
					

				out.println("<tr class=tr_input>");
				out.println("<td ID=PACC width=\"20%\" >Payer Account </td>");
				out.println("<td width=\"30%\"><input name=\"PAY_ACCOUNT\"   type=\"text\" maxlength=\"20\"  class=\"txt_input\"  > ");//onblur=\"makeRequest4(this.value)\"
				out.println("<input type=button name=accno_help value=Help class=\"but_input\" onclick=\"account_help()\" ></td>");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td  width=\"20%\" ID=PBRANCH>Payer Branch *</td>");
				out.println("<td width=\"30%\" > <input name=\"PAY_BRANCH\"   type=\"text\" maxlength=\"10\"  onblur=\"makeRequest_branch(this.value)\" class=\"txt_input\" > ");
				out.println("<input type=button name=BUT_PAY_BRANCH value=Help class=\"but_input\" onclick=\"branch_help()\" ></td>");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
			
   			out.println("<tr class=tr_input>");
				out.println("<td  width=\"20%\">Payer Branch Name</td>");
				out.println("<td width=\"40%\" > <input name=\"PAY_BRANCH_NAME\"   type=\"text\" maxlength=\"100\"  onblur=\"\" class=\"txt_input\" style=\"width: 200px\" > ");
				out.println("</td>");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				

				out.println("</table>");	  

				out.println("<table align=\"center\" width=\"100%\" class='table'>"); 
			  out.println("<tr > ");  
			  out.println("<td width=\"100%\"><DIV ID='m_table_cheque'></DIV></td>"); 
		    out.println("</tr>"); 
			  out.println("</table>");	 */
				
				out.println("<table align='center'  width='100%' class='table'>"); 				
				out.println("<tr class=tr_input>");
				out.println("<td width=\"20%\" valign='top'>Remark</td>");
				out.println("<td width='30%' ><TEXTAREA class='txt_input' name='REMARK' style=\"width:280px; height:50px;\" maxlength='500' size='500' onkeyPress=\"chk_comment_length(this)\"  onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\" ></TEXTAREA></td>"); 
				out.println("</td>");
				out.println("<td width=\"*%\"></td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
				out.println("<td ><div id=get_group_details></div></td></tr></table>");
				
				
				
				out.println("<table cellpadding=\"2\" cellspacing=\"2\" border=\"0\" class=table>");
				out.println("<tr>");
				out.println("<td><input type=button name=new_1 value=\"New\" class=mainbut onclick=load_screen_status(\"NEW\"); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_out_value();'></td>");//document.Form1.OPTION_DESC.value
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=Dele_1 value=\"Delete\" class=mainbut onclick=load_screen_status(\"DELETE\"); onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_out_value();'></td>");
				out.println("<td width=10%>&nbsp;</td>");
				out.println("<td ><input type=button name=b_submit value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_out_value();'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type='button' name=help_1 class=mainbut value=\"Help\" onclick=load_screen_status(\"HELP\"); >  </td>");  //Added By Nuwan De Silv 17-05-05
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset_1 value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Reset\");' onmouseout='load_roll_out_value();'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back_1 value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_out_value();'></td>");
				out.println("<td>&nbsp;&nbsp;&nbsp;</td>");			
				out.println("<td><input type=button name=Doc_1 value=\"Document\" class=mainbut onclick=show_document(); onMouseOver='load_roll_value(\"Document\");' style='width: 130px' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("</tr></table>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td class=\"pdn_txtpos1 & txt-bodyRed\">");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				
				
				
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");    
				out.println("<tr class=tr_input> ");
				out.println("<td valign=\"bottom\" height=\"20\"></td>");
				out.println("</tr>");
				
				out.println("</table>");
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v2.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				out.flush();
				
				
			}else if(m_chksql.trim().equals("get_group_details")){
				String m_group    = req.getParameter("group_code");
				double m_group_value   = new Double(req.getParameter("m_value")).doubleValue();
				
				rs_grp1 = stmt.executeQuery("SELECT DISTINCT CLIENT_CODE,"+m_schema_name+".af_co_get_client_name(CLIENT_CODE),NVL(AMOUNT,0),NVL(REMARKS,'-'),NVL(APPLICATION_NO,'-') "+
					" FROM "+m_schema_name+".AF_RE_PRO_ASSN_GROUP_INV "+
					" WHERE UPPER(GROUP_CODE)=UPPER('"+m_group+"') ");
				
				boolean more=rs_grp1.next();
				
				out.println("<table align=\"center\" width=\"100%\" class='table' border=0>"); 
				int j=0;
				
				
				double m_sum_amt=0;
				double m_client_value=0;
				double m_assign_value=0;
				
				double m_tot_amt=0;
				double m_initial_group_value=m_group_value;
				
				while(more){
					
					m_sum_amt=m_sum_amt+rs_grp1.getDouble(3);
					
					m_client_value=rs_grp1.getDouble(3);
					
					if(j==0){
						out.println("<tr>");
						out.println("<td width='15%' style='{text-align:left;}'><b>Client Code</td>");
						out.println("<td width='15%' style='{text-align:left;}'><b>Ref. No. </td>");
						out.println("<td width='25%' style='{text-align:left;}'><b>Client Name</td>");
						out.println("<td width='15%' style='{text-align:right;}'><b>Client Initial Amount</td>");
						out.println("<td width='10%' style='{text-align:right;}'><b>Allocate</td>");
						out.println("<td width='20%' style='{text-align:right;}'><b>Remarks</td>");
						out.println("</tr>");
					}
					
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 >");
					}else{
						out.println("<tr class=tr_input >");
					}
					out.println("<td width='15%' style='{text-align:left;}' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client('"+rs_grp1.getString(1)+"')\"><U>"+rs_grp1.getString(1)+"<input class='txt_input' type='hidden' name=TXT_CLIENT_CODE_"+j+" value=\""+rs_grp1.getString(1)+"\"></td>");
					out.println("<td width='15%' style='{text-align:left;}' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_application_detail_drill('"+rs_grp1.getString(5)+"')\"><U>"+rs_grp1.getString(5)+"<input class='txt_input' type='hidden' name=TXT_APPLIC_NO_"+j+" value=\""+rs_grp1.getString(5)+"\"></td>");
					out.println("<td width='25%' style='{text-align:left;}'>"+rs_grp1.getString(2)+"</td>"); 
					
					//------------------------------------------------------------------------
					
					
					if(m_assign_value<m_sum_amt){
						if(m_group_value>= (m_sum_amt-m_assign_value)){
							
							out.println("<td width='15%' style='{text-align:right;}'>"+nf.format((m_sum_amt-m_assign_value))+"<input type='hidden' name=TXT_CA_"+j+" value=\""+nf.format((m_sum_amt-m_assign_value))+"\"></td>");
							//out.println("<td width='10%' style='{text-align:right;}'>"+nf.format((m_sum_amt-m_assign_value))+"<input class='txt_input' style='{text-align:right;}' type='hidden' name=TXT_CLIENT_AMOUNT_"+j+" value=\""+nf.format((m_sum_amt-m_assign_value))+"\" onchange=\"total_check(this,"+j+")\" ></td>");
							
							m_group_value = m_group_value-(m_sum_amt-m_assign_value);
							m_assign_value = m_assign_value +(m_sum_amt-m_assign_value);
							
						}else{
							if(m_group_value>0){ 
								
								out.println("<td width='15%' style='{text-align:right;}'>"+nf.format(m_group_value)+"<input type='hidden' name=TXT_CA_"+j+" value=\""+nf.format(m_group_value)+"\"></td>");
								//out.println("<td width='10%' style='{text-align:right;}'>"+nf.format(m_group_value)+"<input class='txt_input' type='hidden' style='{text-align:right;}' name=TXT_CLIENT_AMOUNT_"+j+" value=\""+nf.format(m_group_value)+"\"  onchange=\"total_check(this,"+j+")\"></td>");
								
								m_assign_value = m_assign_value+m_group_value;
								
								m_group_value = 0;
								
							}else{
								out.println("<td idth='15%' style='{text-align:right;}'>"+nf.format(m_group_value)+"<input type='hidden' name=TXT_CA_"+j+" value=\""+nf.format(m_group_value)+"\"></td>");
								//out.println("<td idth='10%' style='{text-align:right;}'>"+nf.format(m_group_value)+"<input style='{text-align:right;}' class='txt_input' type='hidden' name=TXT_CLIENT_AMOUNT_"+j+" value=\""+nf.format(m_group_value)+"\"  onchange=\"total_check(this,"+j+")\"></td>");
								
							}	
						}
					}else{
						
						out.println("<td idth='15%' style='{text-align:right;}'>"+nf.format(0.00)+"<input type='hidden' name=TXT_CA_"+j+" value=\""+nf.format(0.00)+"\"></td>"); 
						//out.println("<td idth='10%' style='{text-align:right;}'>"+nf.format(0.00)+"<input class='txt_input' style='{text-align:right;}' type='hidden' style='{text-align:right;}' name=TXT_CLIENT_AMOUNT_"+j+" value=\""+nf.format(0.00)+"\"></td>"); 
						
					}
					
					//	out.println("<td width='5%' style='{text-align:right;}'><b>&nbsp</td>");
					out.println("<td idth='10%' style='{text-align:right;}'><input class='txt_input' style='{text-align:right;}' type='text' style='{text-align:right;}' name=TXT_CLIENT_AMOUNT_"+j+" value=\""+nf.format(0.00)+"\" onblur=\"check_client_amount(this,"+j+"),total_check(this,"+j+")\" ></td>"); 
					
					
					
					
					
					
					out.println("<td width='20%' style='{text-align:right;}'>"+rs_grp1.getString(4)+"<input class='txt_input' type='hidden' name=TXT_CLIENT_REMARKS_"+j+" value=\""+rs_grp1.getString(4)+"\"></td>"); 
					
					
					out.println("</tr>"); 
					more=rs_grp1.next();
					j=j+1;
					
				}
				out.println("<tr>"); 
				out.println("<td width='15%' style='{text-align:left;}'><b>&nbsp</td>");
				out.println("<td width='15%' style='{text-align:left;}'><b>&nbsp</td>");
				out.println("<td width='25%' style='{text-align:left;}'><b>Total Amount</td>");
				
				
				m_tot_amt=ab.abs(m_initial_group_value-m_sum_amt);
				
				out.println("<td width='15%' style='{text-align:right;}'><b>"+nf.format(m_assign_value)+"</td>");
				
				///out.println("<td width='15%' style='{text-align:right;}'><b>"+nf.format(m_tot_amt)+"</td>");
				out.println("<td width='10%' align=right><b><input  style='{text-align:right;}' class='txt_input' type='text' name=TXT_ALL_TOT_AMT value=\""+nf.format(0.00)+"\" disabled></td>");
				out.println("<td width='20%' style='{text-align:left;}'><b>&nbsp</td>");
				
				out.println("</tr>"); 
				
				
				out.println("<tr>"); 
				out.println("<td width='15%' style='{text-align:left;}'><b>&nbsp</td>");
				out.println("<td width='15%' style='{text-align:left;}'><b>&nbsp</td>");
				out.println("<td width='25%' style='{text-align:left;}'><b>Total Unallocated Amount</td>");
				out.println("<td width='15%' style='{text-align:right;}'><b>&nbsp</td>");
				out.println("<td width='10%' align=right><b><input  style='{text-align:right;}' class='txt_input' type='text' name=TXT_TOT_AMT value=\""+nf.format(0.00)+"\" disabled></td>");
				out.println("<td width='20%' style='{text-align:left;}'><b>&nbsp</td>");
				out.println("</tr>"); 
				
				
				out.println("<input type=hidden name=hid_count_client value="+j+">");	
				
				out.println("</table>"); 
			}
			
			
			
			
			
			
			
			
			
			else if(m_chksql.trim().equals("get_group_receipt")){
				String m_rec_no    = req.getParameter("rec_no");
				
				
				
				rs_grp1 = stmt.executeQuery("SELECT REC_NO,CLIENT_CODE,"+m_schema_name+".af_co_get_client_name(CLIENT_CODE),GROUP_REC_NO,NVL(REC_AMOUNT,0) "+
					"FROM  "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+
					"WHERE GROUP_REC_NO='"+m_rec_no+"'");
				
				
				boolean more=rs_grp1.next();
				out.println("<table align=\"center\" width=\"100%\" class='table' border=0>"); 
				int j=0;
				
				
				double m_sum_amt=0;
				
				while(more){
					
					m_sum_amt=m_sum_amt+rs_grp1.getDouble(5);
					
					
					if(j==0){
						out.println("<tr>");
						out.println("<td width='25%' style='{text-align:left;}'><b>Receipt No</td>");
						out.println("<td width='25%' style='{text-align:left;}'><b>Client Code</td>");
						out.println("<td width='25%' style='{text-align:left;}'><b>Client Name</td>");
						out.println("<td width='*%' style='{text-align:right;}'><b>Amount</td>");
						
						out.println("</tr>");
					}
					
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 >");
					}else{
						out.println("<tr class=tr_input >");
					}
					out.println("<td width='25%' style='{text-align:left;}' style='{text-align:left;}' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_settle_receipt_drill('"+rs_grp1.getString(1)+"')\"><U>"+rs_grp1.getString(1)+"<input class='txt_input' type='hidden' name=TXT_REC_NO_"+j+" value=\""+rs_grp1.getString(1)+"\"></td>");
					out.println("<td width='25%' style='{text-align:left;}' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client('"+rs_grp1.getString(2)+"')\"><U>"+rs_grp1.getString(2)+"<input class='txt_input' type='hidden' name=TXT_CLIENT_CODE_"+j+" value=\""+rs_grp1.getString(2)+"\"></td>"); 
					out.println("<td width='25%' style='{text-align:left;}'>"+rs_grp1.getString(3)+"</td>"); 
					out.println("<td width='*%' style='{text-align:right;}'>"+rs_grp1.getDouble(5)+"<input class='txt_input' type='hidden' name=TXT_CLIENT_AMOUNT_"+j+" value=\""+rs_grp1.getDouble(5)+"\"></td>"); 
					
					
					out.println("</tr>"); 
					more=rs_grp1.next();
					j=j+1;
					
				}
				out.println("<tr>"); 
				out.println("<td width='25%' style='{text-align:left;}'><b>Total Unallocated Amount</td>");
				out.println("<td width='25%' style='{text-align:left;}'><b>&nbsp</td>");
				out.println("<td width='25%' style='{text-align:left;}'><b>&nbsp</td>");
				out.println("<td width='*%' style='{text-align:right;}'><b>"+nf.format(m_sum_amt)+"<input class='txt_input' type='hidden' name=TXT_TOT_AMT value=\"0.00\"></td>");
				
				out.println("</tr>"); 
				out.println("<input type=hidden name=hid_count_client value="+j+">");	
				
				out.println("</table>"); 
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








