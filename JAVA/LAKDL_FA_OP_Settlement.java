import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;

import oracle.jdbc.driver.*;
   
public class LAKDL_FA_OP_Settlement extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1;
	public String m_chksql;
	ServletOutputStream out = null;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res){
		
		try {
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
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
			nf.setMaximumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			stmt1= conn.createStatement ();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					
	    else if(m_chksql.trim().equals("main_page")){
          
      out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Asset Financing System</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
      
			out.println("function makeRequest(url,opt,type) {");
      out.println("var http_request = false;");
      out.println("if (window.XMLHttpRequest) {"); 
      out.println("    http_request = new XMLHttpRequest();");
      out.println("    if (http_request.overrideMimeType) {");
      out.println("        http_request.overrideMimeType('text/xml');");
      out.println("    }");
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

      out.println("function alertGetContents(http_request,opt,type) {");
      out.println(" if (http_request.readyState == 4) {");
      out.println("    if (http_request.status == 200) {");
      out.println("      if(opt==\"2\"){");
			out.println("         rec.innerHTML=http_request.responseText; ");
			out.println("         assign_div();");
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
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"FA_OP_Settlement?chksql=get_return_receipt&client=\"+obj+\"\";");
			out.println("	load_interface(m_url,'NORM');");
			out.println("}");
			
			out.println("function makeRequest3(obj) {");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"FA_OP_Settlement?chksql=get_return_receipt2&client=\"+obj+\"&rec_no=\"+document.Form1.RECEIPT_NO.value;");
			out.println("	 load_interface(m_url,'NORM');");
			out.println("}");
			
			out.println("function makeRequest4(obj) {");
			out.println(" document.Form1.hid_help_status.value='H_account' ");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"FA_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_get_account_no&data_val=\"+obj;");
			out.println("	 load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest5(obj) {");
			out.println(" document.Form1.hid_help_status.value='H_client' ");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"FA_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_get_client_code&data_val=\"+obj;");
			out.println("	 load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest6(obj) {");
			out.println(" document.Form1.hid_help_status.value='H_receipt' ");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"FA_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_get_receipt_no&data_val=\"+obj;");
			out.println("	 load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function get_vector(data_vec){ ");
			out.println("			if( data_vec.length==0 && document.Form1.hid_help_status.value=='H_account' && document.Form1.PAY_ACCOUNT.value !='' ){");
			out.println("      account_help();");
			out.println("			}");
			out.println("			else if( data_vec.length>0 && document.Form1.hid_help_status.value=='H_account' && document.Form1.PAY_ACCOUNT.value !='' ){");
			out.println("      assign_account(data_vec);");
			out.println("			}");
			out.println("			else if( data_vec.length==0 && document.Form1.hid_help_status.value=='H_client'  && document.Form1.CLIENT_CODE.value !='' ){");
			out.println("      client_help();");
			out.println("			}");
			out.println("			else if( data_vec.length>0 && document.Form1.hid_help_status.value=='H_client' && document.Form1.CLIENT_CODE.value !='' ){");
			out.println("      makeRequest2(document.Form1.CLIENT_CODE.value);");
			out.println("			}");
			out.println("			else if( data_vec.length==0 && document.Form1.hid_help_status.value=='H_receipt'  && document.Form1.RECEIPT_NO.value !='' ){");
			out.println("      receipt_help_2();");
			out.println("			}");
			out.println("			else if( data_vec.length>0 && document.Form1.hid_help_status.value=='H_receipt' && document.Form1.RECEIPT_NO.value !='' ){");
			out.println("      get_Receipt();");
			out.println("			}");
			out.println("}");
			
			out.println("function get_vector_normal(http_response){ ");
			out.println(" return_rec.innerHTML = ''; ");
			out.println(" return_rec.innerHTML = http_response; ");
			out.println(" if(parseFloat(document.Form1.hid_count.value)== 0 ){ ");
			out.println(" return_rec.innerHTML = ''; ");
			out.println(" document.Form1.hid_return_count.value=0 ");
			out.println(" }");
			out.println(" else{");
			out.println(" document.Form1.hid_return_count.value = parseFloat(document.Form1.hid_count.value);");
			out.println(" }");
			out.println("}");
			
     out.println("function addrow( data,type) {");
     out.println(" str=\"\";");
     out.println(" i=0;");
     out.println(" if(data.length>0){");
     out.println("   if(type=='Rec'){"); 
		 out.println("     document.Form1.TERMINATION_NO.value       =data[0];"); 
		 out.println("   }else if(type=='Cli'){"); 
		 out.println("     document.Form1.CLIENT_CODE.value     =data[0];"); 
		 out.println("     document.Form1.CLIENT_NAME.value     =data[1];"); 
		 out.println("   }else if(type=='ExcRate'){"); 
		 out.println("     document.Form1.EXCHANE_RATE.value        =data[0];"); 
		 out.println("   }else if(type=='ChExcRate'){"); 
		 out.println("     document.Form1.EXCHANE_RATE.value    =data[1];"); 
	   out.println("     document.Form1.REP_AMOUNT.value      =data[0];"); 
		 out.println("   }else if(type=='RepAmt'){");  
		 out.println("     document.Form1.REP_AMOUNT.value        =data[0];"); 
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
		 out.println("     document.Form1.CLIENT_CODE.value     =data[4];"); 
		 out.println(" 		 makeRequest3(data[4]);");	
		 out.println("     document.Form1.CURR_CODE.value       =data[5];"); 
		 out.println("     document.Form1.AMOUNT.value          =data[6];"); 
		 out.println("     document.Form1.EXCHANE_RATE.value    =data[7];"); 
		 out.println("     document.Form1.REP_AMOUNT.value      =data[8];"); 
		 out.println("     document.Form1.SETT_MODE.value       =data[9];"); 
		 out.println("		 if(data[10] == '' || data[10]== 'null' )");	
		 out.println("     document.Form1.PAY_BRANCH.value      ='';"); 	
		 out.println("		 else ");	
		 out.println("     document.Form1.PAY_BRANCH.value      =data[10];"); 
		 out.println("		 if(data[11] == '' || data[11]== 'null' )");		
		 out.println(" 		 document.Form1.PAY_ACCOUNT.value     ='';	");	
		 out.println(" 		 else");	
		 out.println("     document.Form1.PAY_ACCOUNT.value     =data[11];");
		 out.println("		 if(data[12] == '' || data[12]== 'null' )");			
		 out.println(" 		 document.Form1.CHEQUE_NO.value     ='';	");	
		 out.println("		 else");	
		 out.println("     document.Form1.CHEQUE_NO.value       =data[12];");
		 out.println("		 if(data[13] == '' || data[13]== 'null' )");	
		 out.println("     document.Form1.REMARK.value          ='';");
		 out.println("		 else");		
		 out.println("     document.Form1.REMARK.value          =data[13];");	
		 out.println("     check_client();	");
		 out.println("   }"); 
     out.println(" }");
     out.println("}");
				
			out.println("function assign_div(){");
			out.println(" rent.innerHTML=document.Form1.h_rent.value;"); 
		  out.println("	term.innerHTML=document.Form1.h_term.value;;"); 
		  out.println(" rpv.innerHTML =document.Form1.h_rpv.value;"); 
		  out.println("	tpv.innerHTML =document.Form1.h_tpv.value;;"); 
		  out.println("}");		
			
			
			out.println("function inv_help(num){");
			out.println(" document.Form1.hid_opt_val.value=num;"); 
		  out.println("	popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"FA_OP_Settlement?chksql=get_Invoice&client=\"+document.Form1.CLIENT_CODE.value+\"\", \"oBj\",\"left=130,top=200,width=750,height=400\");"); 
		  out.println("}");		
			
			out.println("function cal_amount(opt,am1,am2,num) {");//
			out.println("   document.Form1.hid_win_opt.value=num;");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"FA_OP_Settlement?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
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
			
			out.println("function check_rep_amount(obj,no){ ");
			out.println(" m_count = parseFloat(document.Form1.hid_count.value); ");
			out.println(" m_amount = \"AMOUNT_\"+no");
			out.println(" document.Form1.elements[m_amount].value = format_noobject(document.Form1.elements[m_amount].value); ");
			out.println(" m_rep_amount = parseFloat(unformat_number(document.Form1.AMOUNT)); ");
			out.println(" m_amount = parseFloat(unformat_number(obj)); ");
			out.println(" if(m_rep_amount < parseFloat(unformat_number(obj))){");
			out.println("  alert('Returned amount cannot be greater than Receipt amount '); ");
			out.println("   AMOU.style.color='red';");
			out.println("  obj.value = '';");
			out.println("  obj.focus();"); 
			out.println(" }");
			out.println(" else { ");
			out.println("   AMOU.style.color='black';");
			out.println(" }");
			out.println("}");
			
			out.println("function cal_rep_amount(val) {");
			out.println("   document.Form1.AMOUNT.value=format_noobject(document.Form1.AMOUNT.value);");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=cal_rep_amount&AMOUNT=\"+unformat_noobject(document.Form1.AMOUNT.value)+\"&EXC_RATE=\"+unformat_noobject(document.Form1.EXCHANE_RATE.value)+\"\";");
			out.println("   document.Form1.EXCHANE_RATE.value=format_noobject(document.Form1.EXCHANE_RATE.value);");
			out.println("   makeRequest(m_url,'4','RepAmt');");
			out.println("}");
			
			out.println("function cal_exc_rate(val) {");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=cal_exc_rate&CURR_CODE=\"+document.Form1.CURR_CODE.value+\"&EXC_RATE=\"+unformat_noobject(document.Form1.EXCHANE_RATE.value)+\"&AMOUNT=\"+unformat_noobject(document.Form1.AMOUNT.value)+\"&REP_AMOUNT=\"+unformat_noobject(document.Form1.REP_AMOUNT.value)+\"\";");
      out.println("   document.Form1.REP_AMOUNT.value=format_noobject(document.Form1.REP_AMOUNT.value);");
			out.println("   makeRequest(m_url,'4','ChExcRate');");
			out.println("}");
			
			out.println("function get_Receipt(val) {");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_receipt&RECEIPT_NO=\"+document.Form1.RECEIPT_NO.value+\"\";");
			out.println("   makeRequest(m_url,'4','Receipt');");
			out.println("}");
			
			out.println("function get_Return_Receipt(val) {");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"FA_OP_Settlement?chksql=get_return_receipt&client=\"+document.Form1.CLIENT_CODE.value+\"\";");
			out.println("   makeRequest(m_url,'4','Receipt');");
			out.println("}");
			
			out.println("function check_client(val) {");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_client_code&client_code=\"+document.Form1.CLIENT_CODE.value+\"\";");
			out.println("   makeRequest(m_url,'4','Cli');");
      out.println("}");
				
			out.println("function validate_data(){"); 
			out.println("m_sub=0;"); 
			out.println("if(document.Form1.hid_option.value==\"NEW\"){"); 
			out.println("if(document.Form1.CLIENT_CODE.value==\"\"){  "); 
			out.println("  CCODE.style.color='red';");
			out.println("  m_sub = 1;;"); 
			out.println("}"); 
			out.println("if(document.Form1.document.Form1.VAL_DAY.value+document.Form1.VAL_MONTH.value+document.Form1.VAL_YEAR.value==\"\"){  "); 
			out.println("  VDATE.style.color='red';");
			out.println("  m_sub = 1;;"); 
			out.println("}"); 
			out.println("if(document.Form1.AMOUNT.value==\"\"){  "); 
			out.println("  AMOU.style.color='red';");
			out.println("  m_sub = 1;;"); 
			out.println("}");
			out.println("if(document.Form1.EXCHANE_RATE.value==\"\"){  "); 
			out.println("  EXCH.style.color='red';");
			out.println("  m_sub = 1;;"); 
			out.println("}"); 
			out.println("if(document.Form1.REP_AMOUNT.value==\"\"){  "); 
			out.println("  RAMO.style.color='red';");
			out.println("  m_sub = 1;;"); 
			out.println("}"); 
			out.println("}else {"); 
			out.println("if(document.Form1.RECEIPT_NO.value==\"\"){  "); 
			out.println("  RNO.style.color='red';");
			out.println("  m_sub = 1;;"); 
			out.println("}"); 
			out.println("if(document.Form1.CLIENT_CODE.value==\"\"){  "); 
			out.println("  CCODE.style.color='red';");
			out.println("  m_sub = 1;;"); 
			out.println("}"); 
			out.println("if(document.Form1.document.Form1.VAL_DAY.value+document.Form1.VAL_MONTH.value+document.Form1.VAL_YEAR.value==\"\"){  "); 
			out.println("  VDATE.style.color='red';");
			out.println("  m_sub = 1;;"); 
			out.println("}"); 
			out.println("if(document.Form1.AMOUNT.value==\"\"){  "); 
			out.println("  AMOU.style.color='red';");
			out.println("  m_sub = 1;;"); 
			out.println("}");
			out.println("if(document.Form1.EXCHANE_RATE.value==\"\"){  "); 
			out.println("  EXCH.style.color='red';");
			out.println("  m_sub = 1;;"); 
			out.println("}"); 
			out.println("if(document.Form1.REP_AMOUNT.value==\"\"){  "); 
			out.println("  RAMO.style.color='red';");
			out.println("  m_sub = 1;;"); 
			out.println("}"); 
			out.println("}");
			out.println("if(!check_rep_amount_onsave() || m_sub=='1'){");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else {"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function befor_submit(){ "); 
			out.println("   m_status = document.Form1.hid_option.value ");
			out.println("   m_save_msg='Are you sure you want to Save ? ';"); 
			out.println("   if(m_status == \"EDIT\"){ ");
			out.println("   m_save_msg = 'Are you sure you want to Modify ? '");
			out.println("   }"); 
			out.println("   else if(m_status == \"DELETE\"){");
			out.println("   m_save_msg = 'Are you sure you want to Delete ? '");
			out.println("   }"); 
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(m_save_msg)){ "); 
			out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("		document.Form1.elements[i].disabled=false;");
			out.println("		}");
			out.println("		document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Save';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 
			
      out.println("function befor_reset(){");
			out.println(" if(confirm(\"Are you sure you want to clear the screen?\")){  ");
		  out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"FA_OP_Settlement?chksql=main_page'");
		  out.println(" }  ");
			out.println("}");
			
			out.println("function befor_back(){");
			out.println("   close_window(); ");
			out.println("}");
			
			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			out.println("		window.close();");
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"FA_OP_Settlement?chksql=main_page'");
			out.println("}");  

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 
	
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_FA_OP_Settlement\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection - Settelments - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection - Settelments - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
		 out.println("function befor_clear(){");
		 out.println("     document.Form1.RECEIPT_NO.value      ='';"); 
		 out.println("     document.Form1.CLIENT_CODE.value     ='';"); 
		 out.println("     document.Form1.CLIENT_NAME.value     ='';"); 	
		 out.println("     document.Form1.AMOUNT.value          ='';"); 
		 out.println("     document.Form1.REP_AMOUNT.value      ='';"); 
		 out.println("     document.Form1.PAY_BRANCH.value      ='';"); 
		 out.println("     document.Form1.PAY_ACCOUNT.value     ='';");
		 out.println("     document.Form1.CHEQUE_NO.value       ='';");
		 out.println("     document.Form1.REMARK.value          ='';");
		 //out.println(" 		 return_rec.innerHTML = ''; ");	
		 out.println("}");
				
				
			out.println("function load_screen_status(m_val){"); 
			out.println("    document.Form1.hid_option.value    =m_val;"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;"); 
			out.println(" if(confirm(\"Are you sure you want to enter New record?\")){  ");
			out.println("document.Form1.CLIENT_CODE.disabled=false;"); 
			out.println("document.Form1.cli_help.disabled=false;"); 
			out.println("document.Form1.rec_help.disabled=true;"); 
			out.println("document.Form1.RECEIPT_NO.disabled=true;");
			out.println("new_window(); ");
			out.println("}"); 
			out.println("}else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val==\"EDIT\"){"); 
			out.println(" if(confirm(\"Are you sure you want to Modify a record?\")){  ");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.RECEIPT_NO.disabled=false;"); 
			out.println("document.Form1.CLIENT_CODE.disabled=true;"); 
			out.println("document.Form1.cli_help.disabled=true;"); 
			out.println("document.Form1.rec_help.disabled=false;"); 
			out.println("document.Form1.RECEIPT_NO.disabled=false;"); 
			out.println("befor_clear();");
			out.println("}"); 
			out.println("}else if(m_val==\"DELETE\"){"); 
			out.println(" if(confirm(\"Are you sure you want to  Delete a record?\")){  ");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.RECEIPT_NO.disabled=false;"); 
			out.println("document.Form1.CLIENT_CODE.disabled=true;"); 
			out.println("document.Form1.cli_help.disabled=true;"); 
			out.println("document.Form1.rec_help.disabled=false;"); 
			out.println("document.Form1.RECEIPT_NO.disabled=false;"); 
			out.println("befor_clear();");
			out.println("}"); 
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("}"); 
			out.println("document.Form1.OPTION_DESC.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"DELETE\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
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
			//out.println(" return_rec.innerHTML = ''; ");
			out.println(" document.Form1.CLIENT_CODE.value=''; }"); 
			out.println(" else if(document.Form1.hid_help_type.value == '2'){ ");
			//out.println(" return_rec.innerHTML = ''; ");
			out.println(" document.Form1.RECEIPT_NO.value=''; }"); 
			out.println(" else if(document.Form1.hid_help_type.value == '3'){ ");
			out.println(" document.Form1.PAY_ACCOUNT.value='';  "); 
			out.println(" document.Form1.PAY_BRANCH.value=''; } "); 
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
			out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
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
			out.println(" document.Form1.CLIENT_NAME.value =oBj.valout[3]");
			out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[2]");
			out.println(" makeRequest2(oBj.valout[2]);");
			out.println("}");
      
			out.println("function receipt_help(){");
			out.println("Crit=document.Form1.CLIENT_CODE.value+\"@\";");
			out.println(" document.Form1.hid_help_type.value='2' ");
			out.println("HelpBox('1','10','0',Crit,'ReceiptSql','2');");
			out.println("}");		
			
			out.println("function receipt_help_2(){");
			out.println("Crit=document.Form1.RECEIPT_NO.value+\"@\";");
			out.println(" document.Form1.hid_help_type.value='2' ");
			out.println("HelpBox('1','10','0',Crit,'ReceiptSql','2');");
			out.println("}");		
			
			out.println("function receipt_assign(oBj){");
			out.println(" document.Form1.RECEIPT_NO.value =oBj.valout[2]");
			out.println(" get_Receipt();");
			out.println("}");
      
			out.println("function account_help(){");
			out.println("Crit=document.Form1.PAY_ACCOUNT.value+\"@\"+\"Y@\";");
			out.println(" document.Form1.hid_help_type.value='3' ");
			out.println("HelpBox('1','10','0',Crit,'AccountSql','3');");
			out.println("}");		
			
			out.println("function account_assign(oBj){");
			out.println(" document.Form1.PAY_ACCOUNT.value =oBj.valout[2]");
			out.println(" document.Form1.PAY_BRANCH.value =oBj.valout[3]");
			out.println("}");
			
			out.println("function assign_account(data_vec){");
			out.println(" document.Form1.PAY_ACCOUNT.value =data_vec[0]");
			out.println(" document.Form1.PAY_BRANCH.value =data_vec[1]");
			out.println("}");
				
			out.println("function load_edit_window(i,foll_no,type) {");
			out.println("   ");
			out.println("}"); 

			out.println("function befor_end(m_obj) {");
      out.println("   m_obj.focus();");
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
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;"); 
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
			out.println("     document.Form1.VAL_DAY.value=v_dd;");
			out.println("     document.Form1.VAL_MONTH.value=v_mm;");
			out.println("     document.Form1.VAL_YEAR.value=v_yy;");
			out.println("  }");				
			out.println("}");				
							
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_out_value();load_lock();get_sysdate();disable_help();\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input type='hidden' name='Hid_scr_name' value='FA_OP_SETTLEMENTS'> ");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\"> ");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\"> ");
			out.println("<INPUT TYPE='Hidden' NAME='hid_return_count' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"NEW\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_win_type' VALUE=\"Main\">"); 			
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"NEW\">");
			out.println("<input type=hidden name=\"tot_val\" value=\"0\">");
			out.println("<input type=hidden name=\"hid_opt_val\" value=\"0\">");
			out.println("<input type=hidden name=\"hid_win_opt\" value=\"0\">");
				
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
			out.println("<td style=\"width: 6px\"></td>");
			out.println("<td width=10%>&nbsp;</td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td><input type=button name=reset value=\"New\" class=mainbut onclick=load_screen_status(\"NEW\"); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");//document.Form1.OPTION_DESC.value
			out.println("<td>&nbsp;</td>");
			out.println("<td><input type=button name=edit value=\"Edit\" class=mainbut onclick=load_screen_status(\"EDIT\"); onMouseOver='load_roll_value(\"Edit\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td><input type=button name=Dele value=\"Delete\" class=mainbut onclick=load_screen_status(\"DELETE\"); onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
			out.println("<td width=10%>&nbsp;</td>");
			out.println("<td><input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onclick=load_screen_status(\"HELP\"); >  </td>"); 
		  out.println("<td>&nbsp;</td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td><input type=button name=back value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td ><input type=button name=b_submit value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
      out.println("<td>&nbsp;</td>");
			out.println("<td><input type=button name=reset value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Reset\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
			out.println("</tr></table>");
			out.println("</td>	");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
			out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
		  
			out.println("<tr class=tr_input>");
			out.println("<td id=RNO>Receipt No *</td>");
			out.println("<td><input name=\"RECEIPT_NO\" type=\"text\" maxlength=\"15\" class=\"txt_input\" onblur=\"makeRequest6(this.value)\"  onchange=check_client() disabled > ");
			out.println("<input type=button name=rec_help value=Help class=\"but_input\" onclick=\"receipt_help()\" disabled ></td>");
			out.println("</td>");
			out.println("<td ></td>");
			out.println("<td> ");
			out.println("</td>");
			out.println("</tr>");		

			out.println("<tr class=tr_input>");
			out.println("<td ID=VDATE>Value Date *</td>");
			out.println("<td><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY.value,document.Form1.VAL_MONTH.value,document.Form1.VAL_YEAR.value)> ");
			out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY.value,document.Form1.VAL_MONTH.value,document.Form1.VAL_YEAR.value)> ");
			out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY.value,document.Form1.VAL_MONTH.value,document.Form1.VAL_YEAR.value)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
			out.println("</td>");
			out.println("<td ><!--Settlement Mode--></td>");
			out.println("<td><!--SELECT name=\"RECEIPT_TYPE\" class=\"txt_input\"> ");
			out.println("<OPTION value=\"INV\">Invoice</OPTION>");
			out.println("<OPTION value=\"TER\">Termination</OPTION>");
			out.println("</SELECT>--></TD>");
			out.println("</tr>");
			
			out.println("<tr class=tr_input>");
			out.println("<td id=CCODE>Client Code *</td>");
			out.println("<td><input name=\"CLIENT_CODE\" type=\"text\" maxlength=\"10\"  onblur=\"makeRequest5(this.value)\"  class=\"txt_input\" onchange=check_client()> ");
			out.println("<input type=button name=cli_help value=Help class=\"but_input\" onclick=\"client_help()\"></td>");
			out.println("</td>");
			out.println("<td >Client Name</td>");
			out.println("<td> <input name=\"CLIENT_NAME\" type=\"text\" maxlength=\"200\" class=\"txt_input\" disabled>");
			out.println("</td>");
			out.println("</tr>");		

			out.println("<tr class=tr_input>");
			out.println("<td ID=CURR>Currency </td>");
			out.println("<td><SELECT onchange=get_excharate() name=CURR_CODE class=\"txt_input\" > ");
			rs = stmt.executeQuery ("SELECT CURR_CODE, CURR_SYMBOL, REP_CURR "+
			                        "FROM   "+m_schema_name+".AF_CO_MAS_CURRENCY "+
															"ORDER  BY DEFAULT_VALUE DESC ");
			while(rs.next()){
			out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</OPTION>");
			}
			out.println("</td>");
			out.println("<td ></td>");
			out.println("<td>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr class=tr_input>");
			out.println("<td ID=AMOU>Amount *</td>");
			out.println("<td><input name=\"AMOUNT\" type=\"text\" maxlength=\"20\" class=\"txt_input\" onchange=cal_rep_amount(document.Form1.AMOUNT.value) STYLE=\"{text-align:right;}\">");
			out.println("</td>");
			out.println("<td ></td>");
			out.println("<td>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr class=tr_input>");
			out.println("<td id=EXCH>Exchange Rate *</td>");
			out.println("<td><input name=\"EXCHANE_RATE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" onchange=cal_rep_amount(document.Form1.AMOUNT.value) STYLE=\"{text-align:right;}\">");
			out.println("</td>");
			out.println("<td ></td>");
			out.println("<td> ");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr class=tr_input>");
			out.println("<td ID=RAMO>Rep. Curr. Amount *</td>");
			out.println("<td><input name=\"REP_AMOUNT\"   type=\"text\" maxlength=\"25\"  class=\"txt_input\" onchange=cal_exc_rate(document.Form1.REP_AMOUNT.value) STYLE=\"{text-align:right;}\"> ");
			out.println("</td>");
			out.println("<td ></td>");
			out.println("<td>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr class=tr_input>");
			out.println("<td >Settelment Mode</td>");
			out.println("<td><SELECT name=\"SETT_MODE\" class=\"txt_input\"> ");
			out.println("<OPTION value=\"CASH\">Cash</OPTION>");
			out.println("<OPTION value=\"CHEQUE\">Cheque</OPTION>");
			out.println("<OPTION value=\"BANK_TRA\">Bank Transfer</OPTION>");
			
			out.println("</SELECT></TD>");
			out.println("<td ></td>");
			out.println("<td>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr class=tr_input>");
			out.println("<td >Payer Account</td>");
			out.println("<td><input name=\"PAY_ACCOUNT\"   type=\"text\" maxlength=\"20\" onblur=\"makeRequest4(this.value)\"  class=\"txt_input\" > ");
			out.println("<input type=button name=accno_help value=Help class=\"but_input\" onclick=\"account_help()\"></td>");
			out.println("<td ></td>");
			out.println("<td>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr class=tr_input>");
			out.println("<td >Payer Branch</td>");
			out.println("<td><input name=\"PAY_BRANCH\"   type=\"text\" maxlength=\"10\"  class=\"txt_input\" > ");
			out.println("</td>");
			out.println("<td ></td>");
			out.println("<td>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr class=tr_input>");
			out.println("<td >Ref. No</td>");
			out.println("<td><input name=\"CHEQUE_NO\"   type=\"text\" maxlength=\"5\"  class=\"txt_input\" > ");
			out.println("</td>");
			out.println("<td ></td>");
			out.println("<td>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr class=tr_input>");
			out.println("<td >Remark</td>");
			out.println("<td><input name=\"REMARK\"   type=\"text\" maxlength=\"30\"  class=\"txt_input\" > ");
			out.println("</td>");
			out.println("<td ></td>");
			out.println("<td>");
			out.println("</td>");
			out.println("</tr>");
			out.println("</table>");
			
			out.println("<br>");
			out.println("<br>");
			
			out.println("<tr>");
			out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
			//out.println("<div id=return_rec >");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
			out.println("<div id=inv>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr class=tr_input>");
			out.println("<td class=\"line\" height=\"1\">");
			out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
			out.println("</tr>");
			
			out.println("<tr class=tr_input>");
			out.println("<td class=\"pdn_txtpos\" style=\"height: 10px\">");
			out.println("<table cellpadding=\"2\" cellspacing=\"2\" border=\"0\" class=table>");
			out.println("<tr class=tr_input>");
			
			out.println("<td style=\"width: 6px\"></td>");
			out.println("<td width=10%>&nbsp;</td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td><input type=button name=new_1 value=\"New\" class=mainbut onclick=load_screen_status(\"NEW\"); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");//document.Form1.OPTION_DESC.value
			out.println("<td>&nbsp;</td>");
			out.println("<td><input type=button name=edit_1 value=\"Edit\" class=mainbut onclick=load_screen_status(\"EDIT\"); onMouseOver='load_roll_value(\"Edit\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td><input type=button name=Dele_1 value=\"Delete\" class=mainbut onclick=load_screen_status(\"DELETE\"); onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
			out.println("<td width=10%>&nbsp;</td>");

			out.println("<td>&nbsp;</td>");
			out.println("<td><input type=button name=back_1 value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td ><input type=button name=b_submit_1 value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
      out.println("<td>&nbsp;</td>");
			out.println("<td><input type=button name=reset_1 value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Reset\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
			
			out.println("</tr></table>");
			out.println("&nbsp;&nbsp;</td>");
			out.println("</tr>");

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
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			
			
			}else if(m_chksql.trim().equals("get_return_receipt")){
			
			    String m_client      = req.getParameter("client");
																
					rs = stmt.executeQuery ("SELECT  b.RETURN_NO,b.DIPOSIT_NO,b.RECEIPT_NO,a.CHEQUE_NO, "+
																	"				a.PAYER_ACC_NO,b.AMOUNT,b.ALLOCATED_AMOUNT,b.BAL_AMOUNT "+
 																	" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT a, "+m_schema_name+".AF_CO_PRO_RETURN_DETAILS b "+
 																	" where a.REC_NO = b.RECEIPT_NO  and client_code=UPPER('"+m_client+"')  and  b.BAL_AMOUNT > 0 "+
																	" order by  b.RETURN_NO	");
	

					
					out.println("<table class=table border='0' width='100%' >");

          out.println("<tr >");
					out.println("<td  width='12%' ><b>Return No </b></td>");
					out.println("<td  width='12%' ><b>Deposit No </b></td>");
          out.println("<td  width='12%' ><b>Receipt No </b></td>");
					out.println("<td  width='8%' ><b>Cheque No </b></td>");
					out.println("<td  width='12%' ><b>Payer Account No</b></td>");
					out.println("<td  width='8%' align='right' ><b>Amount</b></td>");
					out.println("<td  width='12%' align='right' ><b>Allocated Amount</b></td>");
					out.println("<td  width='12%' align='right' ><b>Balance Amount</b></td>");
					out.println("<td  width='15%' align='right' ><b>Returned Amount</b></td>");
					out.println("</tr>");

           int j = 0;      					
							
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td >"+rs.getString(1) +"<input type=hidden name=\"RETURN_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td >"+rs.getString(2) +"<input type=hidden name=\"DIPOSIT_NO_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td >"+rs.getString(3) +"<input type=hidden name=\"RECEIPT_NO_"+j+"\" value=\""+rs.getString(3)+"\"></td>");
									out.println("<td >"+rs.getString(4) +"</td>");
									out.println("<td >"+rs.getString(5) +"</td>");
                  out.println("<td align='right' >"+nf.format(rs.getDouble(6)) +"<input type=hidden name=\"HID_AMOUNT_"+j+"\" value=\""+rs.getDouble(6)+"\">  </td>");
									out.println("<td align='right' >"+nf.format(rs.getDouble(7)) +"<input type=hidden name=\"ALLOCATED_AMOUNT_"+j+"\" value=\""+rs.getDouble(7)+"\">  </td>");
									out.println("<td align='right' >"+nf.format(rs.getDouble(8)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getDouble(8)+"\"> </td>");
									out.println("<td align='right' ><input type=text name=\"AMOUNT_"+j+"\" value=\"0\" onblur=\"check_rep_amount(this,'"+j+"')\" class=\"txt_input2\"> <input type=hidden name=\"RET_AMOUNT_"+j+"\" value=\"\">  </td>");
									out.println("</tr>");
								
                	j=j+1;
									
	         }
 
          out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");
					
			  }else if(m_chksql.trim().equals("get_return_receipt2")){
			
			    String m_client      = req.getParameter("client");
					String m_rec_no      = req.getParameter("rec_no");
															 
		rs = stmt.executeQuery(" SELECT B.RETURN_NO,B.DIPOSIT_NO,B.RECEIPT_NO,A.CHEQUE_NO, "+
													 " A.PAYER_ACC_NO ,B.AMOUNT ,B.ALLOCATED_AMOUNT,B.BAL_AMOUNT,C.AMOUNT RETURNED_AMOUNT "+
													 " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_RETURN_DETAILS B, "+
													 " "+m_schema_name+".AF_CO_PRO_RETURN_REC_ALL_DET C "+
													 " WHERE A.REC_NO = B.RECEIPT_NO AND A.REC_NO = C.RETURN_REC_NO "+
													 " AND B.RETURN_NO = C.RETURN_NO AND B.DIPOSIT_NO = C.DIPOSIT_NO "+
													 " AND B.RECEIPT_NO = C.RETURN_REC_NO AND CLIENT_CODE = UPPER('"+m_client+"') "+
													 " AND B.BAL_AMOUNT > 0 AND ALLO_RECEIPT_NO = '"+m_rec_no+"' "+
											 		 " UNION ALL "+
											     " SELECT B.RETURN_NO,B.DIPOSIT_NO,B.RECEIPT_NO,A.CHEQUE_NO, "+
											     " A.PAYER_ACC_NO,B.AMOUNT,B.ALLOCATED_AMOUNT,B.BAL_AMOUNT,0 "+
													 " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_RETURN_DETAILS B "+
													 " WHERE A.REC_NO = B.RECEIPT_NO AND CLIENT_CODE=UPPER('"+m_client+"') AND B.BAL_AMOUNT > 0 "+
													 " AND (B.RETURN_NO,B.DIPOSIT_NO) NOT IN (SELECT RETURN_NO,DIPOSIT_NO "+
													 " FROM "+m_schema_name+".AF_CO_PRO_RETURN_REC_ALL_DET "+
											 		 " WHERE ALLO_RECEIPT_NO='"+m_rec_no+"') order by RETURN_NO ");
	


					out.println("<table class=table border='0' width='100%' >");
          out.println("<tr >");
					out.println("<td  width='12%' ><b>Return No </b></td>");
					out.println("<td  width='12%' ><b>Deposit No </b></td>");
          out.println("<td  width='12%' ><b>Receipt No </b></td>");
					out.println("<td  width='8%' ><b>Cheque No </b></td>");
					out.println("<td  width='12%' ><b>Payer Account No</b></td>");
					out.println("<td  width='8%' align='right' ><b>Amount</b></td>");
					out.println("<td  width='12%' align='right' ><b>Allocated Amount</b></td>");
					out.println("<td  width='12%' align='right' ><b>Balance Amount</b></td>");
					out.println("<td  width='15%' align='right' ><b> Returned Amount</b></td>");
					out.println("</tr>");
						
           int j = 0;      					
							
              while(rs.next()){
									
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td >"+rs.getString(1) +"<input type=hidden name=\"RETURN_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td >"+rs.getString(2) +"<input type=hidden name=\"DIPOSIT_NO_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td >"+rs.getString(3) +"<input type=hidden name=\"RECEIPT_NO_"+j+"\" value=\""+rs.getString(3)+"\"></td>");
									out.println("<td >"+rs.getString(4) +"</td>");
									out.println("<td >"+rs.getString(5) +"</td>");
                  out.println("<td align='right' >"+nf.format(rs.getDouble(6)) +"<input type=hidden name=\"HID_AMOUNT_"+j+"\" value=\""+rs.getDouble(6)+"\">  </td>");
									out.println("<td align='right' >"+nf.format(rs.getDouble(7)) +"<input type=hidden name=\"ALLOCATED_AMOUNT_"+j+"\" value=\""+rs.getDouble(7)+"\">  </td>");
									out.println("<td align='right' >"+nf.format(rs.getDouble(8)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getDouble(8)+"\"> </td>");
									out.println("<td align='right' ><input type=text name=\"AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(9))+"\" onblur=\"check_rep_amount(this,'"+j+"')\"  class=\"txt_input2\" > <input type=hidden name=\"RET_AMOUNT_"+j+"\" value=\""+rs.getDouble(9)+"\" ></td>");
									out.println("</tr>");
								  
                	j=j+1;
									
	         }
 
          out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");		
					
      } else if(m_chksql.trim().equals("get_Termi_Det")){
			
			    String m_Termination_no    = req.getParameter("Termination_no");
          
			   
					rs = stmt.executeQuery ("SELECT RENTAL_DATE, PERCENTAGE, RENTAL_AMOUNT,  "+
					                        "       RENTAL_PV,TERM_AMOUNT, TERM_PV "+
																	"FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION_DETAILS "+
                                  "WHERE  TERMINATION_NO='"+m_Termination_no+"'"); 
																					
					out.println("<table class=table border='0' width='100%' >");
					
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='15%' >Installment Date</td>");
					out.println("<td  width='5%'  >Percentage</td>");
          out.println("<td  width='20%' >Rental</td>");
					out.println("<td  width='20%' >P.V.</td>");
					out.println("<td  width='20%' >Termination Amount</td>");
					out.println("<td  width='20%' >P.V.</td>");
					out.println("</tr>");
      
           int j = 0;
					 double rent=0;
					 double rpv =0;
					 double term=0;
					 double tpv =0;
					    		out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td></td>");
                  out.println("<td ></td>");
                  out.println("<td align=right id=rent></td>");
                  out.println("<td align=right id=rpv ></td>");
									out.println("<td align=right id=term></td>");
									out.println("<td align=right id=tpv ></td>");
									out.println("</tr>");
							
							
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input >");
									out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j+"\"    value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j+"\"        value=\""+rs.getString(3)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j+"\"            value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j+"\"     value=\""+rs.getString(6)+"\"></td>");
									out.println("");
									out.println("</tr>");
									rent = rent  + rs.getDouble(3);
									rpv  = rpv   + rs.getDouble(4);
									term = term  + rs.getDouble(5);
									tpv  = tpv   + rs.getDouble(6);
									j=j+1;
									
									if(rs.next()){
									  out.println("<tr class=tr_input1 >");
										out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
	                  out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j+"\"    value=\""+rs.getString(2)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j+"\"        value=\""+rs.getString(3)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j+"\"            value=\""+rs.getString(4)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j+"\"     value=\""+rs.getString(6)+"\"></td>");
										out.println("");
										out.println("</tr>");
										rent = rent  + rs.getDouble(3);
									  rpv  = rpv   + rs.getDouble(4);
									  term = term  + rs.getDouble(5);
									  tpv  = tpv   + rs.getDouble(6);
									  j=j+1;
									}
                	
									
			        }
          
					        out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td><input type=hidden name=hid_count value="+j+"></td>");
                  out.println("<td ></td>");
                  out.println("<td align=right>"+nf.format(rent)+"<input type=hidden name=h_rent value="+nf.format(rent)+"></td>");
									out.println("<td align=right>"+nf.format(rpv) +"<input type=hidden name=h_rpv  value="+nf.format(rpv)+"></td>");
									out.println("<td align=right>"+nf.format(term)+"<input type=hidden name=h_term value="+nf.format(term)+"></td>");
									out.println("<td align=right>"+nf.format(tpv) +"<input type=hidden name=h_tpv  value="+nf.format(tpv)+"></td>");
									out.println("</tr>");
					
          out.println("</table>");

    }
			
	    	else if(m_chksql.trim().equals("get_Termi_Char")){
			
			    String m_Lease_no     = req.getParameter("Lease_no");
          String m_Vehicle_no   = req.getParameter("Vehicle_no");
          String m_Disco_rate   = req.getParameter("Disco_rate");
          String m_App_date     = req.getParameter("App_Date");
          String m_client       = req.getParameter("Client");
          
					
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_CR_TEMP_TERMINATION_SAVE(:1,:2,:3,:4,:5,:6);END;");
				  callstmt1.setString(1 ,m_Disco_rate);
          callstmt1.setString(2 ,m_Vehicle_no);
          callstmt1.setString(3 ,m_Lease_no);
					callstmt1.setString(4 ,m_username);
					callstmt1.setString(5 ,m_App_date);
          callstmt1.setString(6 ,m_client);
 				 
 				  //out.println("t5");
			    callstmt1.execute();
					//out.println("t6");
			   
					rs = stmt.executeQuery ("SELECT TO_CHAR(INSTALLMENT_DATE,'DD-MM-YYYY'),PERCENTAGE,  "+
																  "       SUM(RENTAL_AMOUNT), SUM(PV),SUM(TERMINATION_AMOUNT),   "+
																  "       SUM(TERMINATION_PV),INSTALLMENT_NO "+
																  "FROM   "+m_schema_name+".AF_CR_TBD_TERMINATION "+
																  "WHERE  ENT_USER='"+m_username+"' "+
																  "GROUP  BY INSTALLMENT_NO, "+
																  "       INSTALLMENT_DATE, PERCENTAGE ");
																					
					out.println("<table class=table border='0' width='100%' >");
					
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='15%' >Installment Date</td>");
					out.println("<td  width='5%'  >Percentage</td>");
          out.println("<td  width='20%' >Rental</td>");
					out.println("<td  width='20%' >P.V.</td>");
					out.println("<td  width='20%' >Termination Amount</td>");
					out.println("<td  width='20%' >P.V.</td>");
					out.println("</tr>");
      
           int j = 0;
					 double rent=0;
					 double rpv =0;
					 double term=0;
					 double tpv =0;
					    		out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td></td>");
                  out.println("<td ></td>");
                  out.println("<td align=right id=rent></td>");
                  out.println("<td align=right id=rpv ></td>");
									out.println("<td align=right id=term></td>");
									out.println("<td align=right id=tpv ></td>");
									out.println("</tr>");
							
							
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input >");
									out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j+"\"    value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j+"\"        value=\""+rs.getString(3)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j+"\"            value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j+"\"     value=\""+rs.getString(6)+"\"></td>");
									out.println("");
									out.println("</tr>");
									rent = rent  + rs.getDouble(3);
									rpv  = rpv   + rs.getDouble(4);
									term = term  + rs.getDouble(5);
									tpv  = tpv   + rs.getDouble(6);
									j=j+1;
									
									if(rs.next()){
									  out.println("<tr class=tr_input1 >");
										out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
	                  out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j+"\"    value=\""+rs.getString(2)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j+"\"        value=\""+rs.getString(3)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j+"\"            value=\""+rs.getString(4)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j+"\"     value=\""+rs.getString(6)+"\"></td>");
										out.println("");
										out.println("</tr>");
										rent = rent  + rs.getDouble(3);
									  rpv  = rpv   + rs.getDouble(4);
									  term = term  + rs.getDouble(5);
									  tpv  = tpv   + rs.getDouble(6);
									  j=j+1;
									}
                	
									
			        }
          
					        out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td><input type=hidden name=hid_count value="+j+"></td>");
                  out.println("<td ></td>");
                  out.println("<td align=right>"+nf.format(rent)+"<input type=hidden name=h_rent value="+nf.format(rent)+"></td>");
									out.println("<td align=right>"+nf.format(rpv) +"<input type=hidden name=h_rpv  value="+nf.format(rpv)+"></td>");
									out.println("<td align=right>"+nf.format(term)+"<input type=hidden name=h_term value="+nf.format(term)+"></td>");
									out.println("<td align=right>"+nf.format(tpv) +"<input type=hidden name=h_tpv  value="+nf.format(tpv)+"></td>");
									out.println("</tr>");
					
          out.println("</table>");

    }else if(m_chksql.trim().equals("get_Receipt")){
			
			    String m_client      = req.getParameter("client");
          
					rs = stmt.executeQuery (" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
																	"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE, "+
																	"	       A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK, "+
																	"	       EXCHANGE_RATE_REP_CURR,REC_AMMOUNT_REP_CURR, "+
																	"	       EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+ 
																	"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
																	"	WHERE  A.REC_NO = B.REC_NO AND STATUS='P' AND "+
																	"	       BAL_TOBE_RECEIVE>0 AND CLIENT_CODE = '"+m_client+"' ");
	

					
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='15%' >Receipt No</td>");
					out.println("<td  width='15%' align=right>Receipt Amount</td>");
          out.println("<td  width='15%' align=right>Allocated Amount</td>");
					out.println("<td  width='20%' align=right>Balance Amount</td>");
					out.println("<td  width='35%' align=right>Amount</td>");
					out.println("</tr>");
      
           int j = 0;      					
							
              while(rs.next()){
                  out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td >"+rs.getString(1) +"<input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"ALLO_AMOUN_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");//out.pr	
                  out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled>");
									out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									out.println("<td></TD>");
									out.println("<td colspan=5 ><div id='inv_"+j+"'><input type=hidden name=hid_invoice_count_"+j+" value=0></div>");
									out.println("</td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									out.println("<td>&nbsp;</TD>");
									out.println("<td colspan=5 >");
									out.println("</td>");
									out.println("</tr>");
                	j=j+1;
									
	         }
          
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");
      } 	
			
			
			else if(m_chksql.trim().equals("get_Receipt_del")){
			
			    String m_rec_no      = req.getParameter("rec_no");
          
					rs = stmt.executeQuery (" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
																	"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE, "+
																	"	       A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK, "+
																	"	       EXCHANGE_RATE_REP_CURR,REC_AMMOUNT_REP_CURR, "+
																	"	       EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+ 
																	"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
																	"	WHERE  A.REC_NO = B.REC_NO AND STATUS='P' AND "+
																	"	       ALLOCATED_AMOUNT>0 AND A.REC_NO = '"+m_rec_no+"' ");

					
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='15%' >Receipt No</td>");
					out.println("<td  width='15%' align=right>Receipt Amount</td>");
          out.println("<td  width='15%' align=right>Allocated Amount</td>");
					out.println("<td  width='20%' align=right>Balance Amount</td>");
					out.println("<td  width='25%' align=right>Amount</td>");
					out.println("<td  width='10%'  ></td>");
					out.println("</tr>");
      
           int j = 0;      					
							
              while(rs.next()){
                  out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td >"+rs.getString(1) +"<input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"ALLO_AMOUN_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled></td>");
									out.println("</tr>");
									
						
																	
					rs1 = stmt1.executeQuery ("SELECT A.INVOICE_NO,TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'), A.INVOICED_AMOUNT, "+
																  	"       A.SETTELED_AMOUNT,ALLOCATION_NO, A.REMARKS, A.SETTELED_AMOUNT_CURR "+
							                      "FROM   "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A "+
								                    "WHERE  RECEIPT_NO = '"+rs.getString(1)+"'");
																								
																	
									
					out.println("<tr class=tr_input>");
					out.println("<td></TD>");
					out.println("<td colspan=5 ><div id='inv_"+j+"'>");
									
					out.println("<table><tr class=pdn_txtpos2 WIDTH=100%>");
					out.println("      <td WIDTH=20%>Invoice No</td>");
					out.println("      <td WIDTH=15%>Date</td>");
					out.println("      <td WIDTH=15% align=right >Invoice Amount</td>");
					out.println("      <td WIDTH=15% align=right >Allocated Amount</td>");
					out.println("      <td WIDTH=15% align=CENTER>Status</td></tr>");
					int x=0;
		
		      while(rs1.next()){
	          out.println(" <tr>");
						out.println("  <td align=left ><input type=text name=\"INV_NO_0_"+x+"\" value=\""+rs1.getString(1)+"\" class=\"txt_input2\" disabled><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_0_"+x+"\" VALUE=\""+rs1.getString(5)+"\"></td>");
						out.println("  <td align=left ><input type=text name=\"V_DATE_0_"+x+"\" value=\""+rs1.getString(2)+"\" class=\"txt_input2\" disabled></td>");
						out.println("  <td align=right><input type=text name=\"INV_AM_0_"+x+"\" value=\""+nf.format(rs1.getDouble(3))+"\" class=\"txt_input2\" disabled></td>");
						out.println("  <td align=right><input type=text name=\"Text_sett_amount0_"+x+"\" value=\""+nf.format(rs1.getDouble(4))+"\" class=\"txt_input2\" disabled></td>");
						out.println("  <td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard_0_"+x+"\" onclick=check_status_del(\""+x+"\") value=\"NO\">");
						out.println(" </tr>");
						x=x+1;
					}
					        out.println("<input type=hidden name=hid_invoice_count_0  value="+x+"></table>");
									out.println("</div>");
									out.println("</td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									out.println("<td>&nbsp;</TD>");
									out.println("<td colspan=5 >");
									out.println("</td>");
									out.println("</tr>");
                	j=j+1;
		            }
          
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");

				
      } 	
			
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

