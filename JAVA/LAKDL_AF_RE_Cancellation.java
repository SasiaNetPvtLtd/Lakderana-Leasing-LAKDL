//This File was created by AH on 25-09-2007

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;

import oracle.jdbc.driver.*;

public class LAKDL_AF_RE_Cancellation extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2;
	public String m_chksql;
	ServletOutputStream out = null;
	
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
			//out.println("conn="+conn);
			//Class.forName("oracle.jdbc.driver.OracleDriver");
      //conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
			CallableStatement callstmt1 =null;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  //nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			stmt1= conn.createStatement ();
			stmt2= conn.createStatement ();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					
	    else if(m_chksql.trim().equals("main_page")){
			
	        String m_Followu_no   = ""; //req.getParameter("Followu_no");
          
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
			out.println(" var m_status=0;");

			
				//Check Values Using AJAX
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
        //out.println("  window.open(url);");
				out.println("  http_request.send(null);");
				out.println(" }");
        out.println("}");
        
        out.println("function alertContents(http_request,type) {");
				//out.println(" alert('test');");
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
				
				
				out.println("      if(opt==\"10\"){");
				out.println("         invoice_details.innerHTML=http_request.responseText; ");
				out.println("      }else if(opt==\"11\"){");
				out.println("         deposite_details.innerHTML=http_request.responseText; ");
				out.println("      }else if(opt==\"12\"){");
				out.println("         retandrealiz_details.innerHTML=http_request.responseText; ");
			
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

        out.println("   		   }");
				out.println("          addrow(data_vec,type);");
				
                      
				out.println("      }");
				out.println("    } else {");
        out.println("        ");
        out.println("    }");
        out.println(" }");
        out.println("}");
				
       out.println("function addrow( data,type) {");
       out.println(" str=\"\";");
       out.println(" i=0;");
       out.println(" if(data.length>0){");
       out.println("   if(type=='Receipt'){"); 
			 out.println("     document.Form1.RECEIPT_NO.value      =data[0];"); 
			 out.println("   }"); 
			 out.println("   else if(type=='SysDate'){"); 	
			 out.println("     document.Form1.VAL_DAY.value         =data[0];"); 
			 out.println("     document.Form1.VAL_MONTH.value       =data[1];"); 
			 out.println("     document.Form1.VAL_YEAR.value        =data[2];"); 
			 out.println("     document.Form1.HID_SYS_VAL_DAY.value         =data[0];"); 
		   out.println("     document.Form1.HID_SYS_VAL_MONTH.value       =data[1];"); 
			 out.println("     document.Form1.HID_SYS_VAL_YEAR.value        =data[2];"); 
       out.println("   }"); 
				
       out.println(" }"); 
       out.println("}");
				
			out.println("function get_vector_normal(http_response){ ");
			out.println(" if(parseFloat(document.Form1.hid_count.value)== 0 ){ ");
			out.println(" document.Form1.hid_return_count.value=0 ");
			out.println(" }");
			out.println(" else{");
			out.println(" document.Form1.hid_return_count.value = parseFloat(document.Form1.hid_count.value);");
			out.println(" }");
			out.println("}");
			
			

			
	
			

			
			out.println("function makeRequest6(obj) {");
			out.println(" document.Form1.hid_help_status.value='H_receipt' ");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MAS_sql_validations2?chksql=m_prime_chk_LAKDL_AF_RE_get_receipt_no&data_val=\"+obj;");
			//out.println("  window.open(m_url);");
			out.println("	 load_interface(m_url,'XML');");
			//out.println("	 receipt_help();");
			out.println("}");
			
			  out.println("function get_sysdate(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_sysdate\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','SysDate');");
				out.println("}");
			
				
				out.println("function get_invoice_det() {");
				out.println("if(document.Form1.RECEIPT_NO.value!=\"\" ){");
			  out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Cancellation?chksql=get_return_receipt&m_receipt_no=\"+document.Form1.RECEIPT_NO.value+\"\";");
				out.println("  }");
        out.println("   makeRequest(m_url,'10','invoice_details');");
 				out.println("  }");
				
				out.println("function get_deposite_det() {");
				out.println("if(document.Form1.RECEIPT_NO.value!=\"\" ){");
			  out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Cancellation?chksql=get_deposite_details&m_receipt_no=\"+document.Form1.RECEIPT_NO.value+\"\";");
				out.println("  }");
        out.println("   makeRequest(m_url,'11','deposite_details');");
 				out.println("  }");
				
				out.println("function get_returnandreal_det() {");
				out.println("if(document.Form1.RECEIPT_NO.value!=\"\" ){");
			  out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Cancellation?chksql=get_retrealiz_details&m_receipt_no=\"+document.Form1.RECEIPT_NO.value+\"\";");
				out.println("  }");
        out.println("   makeRequest(m_url,'12','retandrealiz_details');");
 				out.println("  }");
				
				
				
			out.println("function get_vector(data_vec){ ");
			out.println("			 if(data_vec.length==0 && document.Form1.hid_help_status.value=='H_receipt'  && document.Form1.RECEIPT_NO.value !='' ){");
			out.println("receipt_help()");
			out.println("			}");
			out.println("}");				
				
				

			
			
			
			out.println("function befor_submit(){ "); 
			out.println("if(document.Form1.RECEIPT_NO.value!=\"\" ){");
			out.println("   m_save_msg = 'Are you sure you want to Delete ? '");
			out.println("		if(confirm(m_save_msg)){ "); 
			out.println("		document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Cancellation_Save';");  
			out.println("		document.Form1.submit();	"); 
			out.println("   }"); 
      out.println("}else{");
			out.println("		alert(\"Please enter the receipt number that you want to delete\"); ");
			out.println("}");
			out.println("}");
			

      out.println("function befor_reset(){");
			out.println(" if(confirm(\"Are you sure you want to clear the screen?\")){  ");
		  out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Cancellation?chksql=main_page'");
		  out.println(" }  ");
			out.println("}");
			
			out.println("function befor_back(){");
			out.println("   close_window(); ");
			out.println("}");


			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			out.println("		window.close();");
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Cancellation?chksql=main_page'");
			out.println("}"); 
			
			out.println(""); 
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
			out.println("help_box.innerHTML=\" Collection - Receipts Cancellation - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection - Receipts Cancellation - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			 out.println("function befor_clear(){");
			 out.println("     document.Form1.RECEIPT_NO.value      ='';"); 
			 out.println("     document.Form1.REMARK.value          ='';");
			 //out.println(" 		 return_rec.innerHTML = ''; ");	
			 out.println("}");
				
				
			out.println("function load_screen_status(m_val){"); 
			out.println("    document.Form1.hid_option.value    =m_val;"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println(" if(confirm(\"Are you sure you want to enter New record?\")){  ");
			out.println("document.Form1.rec_help.disabled=true;"); 
			out.println("document.Form1.RECEIPT_NO.disabled=false;");
			out.println("new_window(); ");
			out.println("}"); 
			
			out.println("}else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else{");
			//out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
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
			out.println("  if(document.Form1.hid_help_type.value == '2'){ ");
			//out.println(" return_rec.innerHTML = ''; ");
			out.println(" document.Form1.RECEIPT_NO.value=''; }"); 
			
			// added by udara 14-09-2017
			out.println("  else if(document.Form1.hid_help_type.value == '3'){ ");
			out.println("     document.Form1.FINANCE_NO.value=''; ");
			out.println("     document.Form1.CLIENT_NAME.value=''; ");
			out.println("  }");
			out.println("  else if(document.Form1.hid_help_type.value == '4'){ ");
			out.println("     document.Form1.CLIENT_NAME.value=''; ");
			out.println("  }");
			// end by udara 14-09-2017
			
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
			out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_PRO_CR_Help_Servlet?class_in="+m_client_name+"AF_RE_cancelation_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			//out.println("window.open('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_PRO_CR_Help_Servlet?class_in="+m_client_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=');");
			out.println("	"); 
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("		clear_data();");
			out.println("		} else "); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		receipt_assign(oBj);");
			out.println("		}");
			
			// added below by udara on 18-02-2012
			
			out.println("		if(IfCount==\"3\"){"); 
			out.println("		finance_assign(oBj);");
			out.println("		}");
			
			out.println("		if(IfCount==\"4\"){"); 
			out.println("		client_assign(oBj);");
			out.println("		}");
			// end by udara 18-02-2012

			
			
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
			

        
			    out.println("function receipt_help(){");
				//out.println("Crit=document.Form1.RECEIPT_NO.value+\"@\";"); // commented by udara on 18-02-2012
				out.println("Crit=document.Form1.RECEIPT_NO.value+\"@\"+document.Form1.CLIENT_NAME.value+\"@\";"); // added by udara on 18-02-2012
				out.println(" document.Form1.hid_help_type.value='2' ");
				//out.println("HelpBox('1','10','0',Crit,'ReceiptSql','2');"); // commented by udara on 18-02-2012
				//out.println("HelpBox('1','10','0',Crit,'NewReceiptSql','2');"); //commented by kanishka on 03-09-2013
				out.println("HelpBox('1','10','0',Crit,'NewReceiptSql_1','2');");//added by kanishka on 03-09-2013
				out.println("}");
		
				// Added by Udara on 18-02-2012
				out.println("function finance_help(){");
				out.println("Crit=document.Form1.FINANCE_NO.value+\"@\"+document.Form1.CLIENT_NAME.value+\"@\";");
				out.println(" document.Form1.hid_help_type.value='3' ");
				out.println("HelpBox('1','10','0',Crit,'NewFinanceSql','3');");
				out.println("}");
				
				out.println("function client_help(){");
				out.println("Crit=document.Form1.CLIENT_NAME.value+\"@\";");
				out.println(" document.Form1.hid_help_type.value='4' ");
				out.println("HelpBox('1','10','0',Crit,'new_client_help','4');");
				out.println("}");
				
				out.println("function client_assign(oBj){");
				out.println(" document.Form1.CLIENT_NAME.value =oBj.valout[2]");
				out.println("}");

				out.println("function finance_assign(oBj){");
				out.println(" document.Form1.FINANCE_NO.value =oBj.valout[2]");
				out.println(" document.Form1.CLIENT_NAME.value =oBj.valout[3]");
				out.println("}");	
				
				// End by Udara on 18-02-2012
				
				out.println("function receipt_assign(oBj){");
				out.println(" document.Form1.RECEIPT_NO.value =oBj.valout[2]");
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[6]");
				out.println(" get_invoice_det()"); 
				out.println(" get_deposite_det()"); 
				out.println(" get_returnandreal_det()"); 
				out.println("}");
        

				
			out.println("function load_edit_window(i,foll_no,type) {");
			out.println("   ");
			out.println("}"); 

			out.println("function befor_end(m_obj) {");
      out.println("   m_obj.focus();");
      out.println("}");
			
			
			out.println("function header(){");
			out.println("m_table_other_charges.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr ID=T_ID class=tr_input>'+");		
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"><b><u>Other Charges</u></TD>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");
			out.println("'</table>';");
			out.println("}");				
			
			
			//added by nuwan de silva on 27-08-07---------------------------
			out.println("	function chk_comment_length(obj){ ");
			out.println(" var remarks_length=obj.value.toString().length;");
			out.println("if(remarks_length>obj.maxlength) ");
			out.println("		window.event.keyCode=\"\"; ");
			out.println("} ");
			
			//added by nuwan de silva on 27-08-07---------------------------
			out.println("function count_length(obj){ ");
			out.println("var remarks_length=obj.value.toString().length; ");
			out.println("var remarks=obj.value.toString(); ");
			out.println("if(remarks_length>obj.maxlength){ ");
			out.println("obj.value=remarks.substring(0,obj.maxlength); ");
			out.println("} ");
			out.println("} ");
			
			
	
	   out.println(" function remv_inv_det(){ ");
		 out.println("inv.innerHTML=\"\" ");	
		 out.println("}");
	 
			
			out.println("function show_deposit_details(row_No) {"); 
			out.println("show_deposit_drill(row_No);"); 
			out.println("}"); 
			
			
			out.println("function show_deposit_details(row_No) {"); 
			out.println("show_deposit_drill(row_No);"); 
			out.println("}"); 


			out.println("function load_details(lineno){");
			out.println(" m_receipt_no_val = lineno;");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CO_display_receipt_details?chksql=pop_receipt_details&REC_NO='+m_receipt_no_val;"); 
			out.println("window.open(m_url,'displayWindow3','left=80,top=200,width=900,height=200,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
		 	out.println("}");
				
			out.println("function show_document() {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_View_Receipt_Cancel_Document\";");
	    out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=650,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
	    out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Cancellation?chksql=main_page';"); 
			out.println("}");
			
			out.println("function validate_date_future_date(objdd,objmm,objyy) {"); 
			out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
			out.println("  checkMonthLength(objdd,objmm,objyy);");
			out.println("  validate_date(objdd,objmm,objyy,document.Form1.HID_SYS_VAL_DAY,document.Form1.HID_SYS_VAL_MONTH,document.Form1.HID_SYS_VAL_YEAR);");
      out.println("}");
			out.println("}");
			
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
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
			out.println("     validate_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR,document.Form1.HID_SYS_VAL_DAY,document.Form1.HID_SYS_VAL_MONTH,document.Form1.HID_SYS_VAL_YEAR);");
			out.println("  }");	
			
			out.println("}");		
			
			out.println("function validate_date(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY){");
			out.println("if(!chk_validity(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY)){");
	    out.println("FROM_DD.value=TO_DD.value;");
			out.println("FROM_MM.value=TO_MM.value;");
			out.println("FROM_YY.value=TO_YY.value;");
			out.println("}");
			out.println("}");
			
			out.println("function chk_validity(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY){  ");	
		  out.println("if((FROM_DD.value!=\"\" || FROM_MM.value!=\"\" || FROM_YY.value!=\"\")  && (TO_DD.value!=\"\" || TO_MM.value!=\"\" || TO_YY.value!=\"\" )){");
      out.println("if((parseFloat(FROM_DD.value))>=(parseFloat(TO_DD.value))){");
      out.println("if((parseFloat(FROM_MM.value))<=(parseFloat(TO_MM.value))){");
      out.println("if((parseFloat(FROM_YY.value))<=(parseFloat(TO_YY.value))){");
      out.println(" if(((parseInt(FROM_DD.value))<(parseFloat(TO_DD.value)))&&");
      out.println("((parseFloat(FROM_MM.value))==(parseFloat(TO_MM.value)))&&");
      out.println("((parseFloat(FROM_YY.value))==(parseFloat(TO_YY.value)))){");
      out.println("}");
      out.println("else if(((parseFloat(FROM_DD.value))>(parseFloat(TO_DD.value)))&&");
      out.println("((parseFloat(FROM_MM.value))==(parseFloat(TO_MM.value)))&&");
      out.println(" ((parseFloat(FROM_YY.value))==(parseFloat(TO_YY.value)))){");
       out.println("      alert('Value Date should not be greater than System Date');");
			out.println("return false;"); 
      out.println("     } ");
      out.println("}");
      out.println("else{");
      out.println("      alert('Value Date should be greater than System Date');");
			out.println("return false;"); 
      out.println("}");
      out.println(" }");
      out.println(" else{");
      out.println("   if((parseFloat(FROM_YY.value))>=(parseFloat(TO_YY.value))){");
          out.println("      alert('Value Date should be greater than System Date');");
			out.println("return false;"); 
      out.println("   }");
      out.println("   else{");
      out.println("   } ");
      out.println(" }");
      out.println("}");
      out.println("else{");
      out.println(" if((parseFloat(FROM_MM.value))<=(parseFloat(TO_MM.value))){");
      out.println("  if((FROM_YY.value)<=(TO_YY.value)){");
      out.println(" }");
      out.println(" else{");
       out.println("      alert('Value Date should be greater than System Date');");
			out.println("return false;"); 
      out.println(" }");
      out.println("}");
      out.println("else{");
      out.println("   if((parseFloat(FROM_YY.value))<(parseFloat(TO_YY.value))){ ");
      out.println("    }");
      out.println("  else{");
       out.println("      alert('Value Date should be greater than System Date');");
			out.println("return false;"); 
      out.println("  }");
      out.println(" }");
      out.println("}");
			out.println("return true;");
      out.println("}");
		  out.println("}");





			
			
			//-------------------------------------------------------------------------------------------------------			
			out.println("</script>"); 
		  out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_out_value(),get_sysdate()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input type='hidden' name='Hid_scr_name' value='AF_RE_CANCELLATION' > ");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\"> ");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\"> ");
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_return_count' VALUE=\"\">"); 
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"NEW\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_win_type' VALUE=\"Main\">"); 			
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"New\">");
			out.println("<input type=hidden name=\"tot_val\" value=\"0\">");
			out.println("<input type=hidden name=\"hid_opt_val\" value=\"0\">");
			out.println("<input type=hidden name=\"hid_win_opt\" value=\"0\">");
			out.println("<input type=hidden name=\"hid_count\" value=\"0\">");
			
			
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
			
			out.println("<input type=hidden name=\"HID_SYS_VAL_DAY\" value=\"\">");
			out.println("<input type=hidden name=\"HID_SYS_VAL_MONTH\" value=\"\">");
			out.println("<input type=hidden name=\"HID_SYS_VAL_YEAR\" value=\"\">");
			
			
				
			  out.println("<table width=\"100%\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\" class=table>");
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
				out.println("<td ><input type=button name=b_submit value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_out_value();'></td>");
			  out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=help value=\"Help\" class=mainbut onclick=load_screen_status(\"HELP\"); onMouseOver='load_roll_value(\"Help\");' onmouseout='load_roll_out_value();'></td>");
				
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Reset\");' onmouseout='load_roll_out_value();'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_out_value();'></td>");
				out.println("<td><input type=button name=Doc_1 value=\"Document\" class=mainbut onclick=show_document(); onMouseOver='load_roll_value(\"Document\");' style='width: 130px' onmouseout='load_roll_value();'></td>");
      
				out.println("</tr></table>");
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				
				
				// Added by Udara on 18-02-2012	
				
				out.println("<table align=\"center\" border=\"0\"  width=\"100%\" class=table>"); 
				out.println("<tr class=tr_input>");
				out.println("<td width=\"20%\" id=RNO>Finance No. </td>");
				out.println("<td width=\"30%\"><input name=\"FINANCE_NO\" type=\"text\" maxlength=\"15\" class=\"txt_input\" onblur=\"\"    > ");
				out.println("<input name=\"HID_FINANCE_NO\" type=\"hidden\"  value=\"\"  >");
				out.println("<input type=button name=fin_help value=... class=\"but_input\" onclick=\"finance_help()\"  ></td>");
				out.println("</td>");
				out.println("<td >&nbsp;</td>");
				out.println("<td>&nbsp; ");
				out.println("</td>");
				out.println("</tr>");		
			    out.println("</table>");	
				
				out.println("<table align=\"center\" border=\"0\"  width=\"100%\" class=table>"); 
				out.println("<tr class=tr_input>");
				out.println("<td width=\"20%\" id=RNO>Client Name </td>");
				out.println("<td width=\"30%\"><input name=\"CLIENT_NAME\" type=\"text\" maxlength=\"15\" class=\"txt_input\" onblur=\"\"    > ");
				out.println("<input name=\"HID_CLIENT_NAME\" type=\"hidden\"  value=\"\"  >");
				out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"client_help()\"  ></td>");
				out.println("</td>");
				out.println("<td >&nbsp;</td>");
				out.println("<td>&nbsp; ");
				out.println("</td>");
				out.println("</tr>");		
			    out.println("</table>");
		
				// End by Udara on 18-02-2012
				
				
				out.println("<table align=\"center\" border=\"0\"  width=\"100%\" class=table>"); 
				out.println("<tr class=tr_input>");
				out.println("<td width=\"20%\" id=RNO>Receipt No </td>");
				out.println("<td width=\"30%\"><input name=\"RECEIPT_NO\" type=\"text\" maxlength=\"15\" class=\"txt_input\" onblur=\"makeRequest6(this.value)\"    > ");
				out.println("<input name=\"CLIENT_CODE\" type=\"hidden\"  value=\"\"  >");
				out.println("<input type=button name=rec_help value=... class=\"but_input\" onclick=\"receipt_help()\"  ></td>");
				out.println("</td>");
				out.println("<td >&nbsp;</td>");
				out.println("<td>&nbsp; ");
				out.println("</td>");
				out.println("</tr>");		
			    out.println("</table>");				
				
				out.println("<table align=\"center\" border=\"0\"  width=\"100%\" class=table>"); 
				out.println("<tr class=tr_input>");
				out.println("<td ID=VDATE width=\"20%\">Value Date *</td>");
				out.println("<td width=\"30%\"><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onBlur=validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onBlur=validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onBlur=validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("</td>");
				out.println("<td >&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("</tr>");
				out.println("</table>");	  
				
				out.println("<table align='center'  width='100%' class='table'>"); 				
				out.println("<table align=\"center\" border=\"0\"  width=\"100%\" class=table>"); 
			  out.println("<tr class=tr_input>");
				out.println("<td width=\"20%\" valign='top'>Remark</td>");
				out.println("<td width=\"30%\" ><TEXTAREA class='txt_input' name='REMARK' style=\"width:280px; height:50px;\" maxlength='500' size='500' onkeyPress=\"chk_comment_length(this)\"  onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\" ></TEXTAREA></td>"); 
				out.println("</td>");
				out.println("<td width=\"50%\"></td>");
				out.println("</tr>");
				out.println("</table>");	
				

				


				out.println("<table align='center'  width='100%' class='table'>"); 				
				out.println("<tr>");
				out.println("<td width='100%' class=\"pdn_txtpos\" height=\"100\" valign=\"top\">");
				out.println("<div id=invoice_details>"); 
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");


				out.println("<table align='center'  width='100%' class='table'>"); 				
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"100\" valign=\"top\">");
				out.println("<div id=deposite_details>"); 
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				

				out.println("<table align='center'  width='100%' class='table'>"); 				
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"100\" valign=\"top\">");
				out.println("<div id=retandrealiz_details>"); 
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");



				
				
				out.println("<table cellpadding=\"2\" cellspacing=\"2\" border=\"0\" class=table>");
				out.println("<tr>");
				out.println("<td ><input type=button name=b_submit value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_out_value();'></td>");
			  out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=help value=\"Help\" class=mainbut onclick=load_screen_status(\"HELP\"); onMouseOver='load_roll_value(\"Help\");' onmouseout='load_roll_out_value();'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset_1 value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Reset\");' onmouseout='load_roll_out_value();'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back_1 value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_out_value();'></td>");
				
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
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			
			}
			
		
		
		

		
		
  else if(m_chksql.trim().equals("get_return_receipt")){
							
							String m_receipt_no    = req.getParameter("m_receipt_no");
							
				       	rs1 = stmt1.executeQuery ("SELECT B.INVOICE_NO, TO_CHAR(ALLOCATED_DATE,'DD-MM-YYYY'), "+
									                        "       INVOICED_AMOUNT,SETTELED_AMOUNT,ALLOCATION_NO,FINANCE_NO "+
																					"FROM   "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_INVOICE  B "+
																					"WHERE  A.INVOICE_NO=B.INVOICE_NO AND RECEIPT_NO = '"+m_receipt_no+"' ");
																				
																													
							 boolean more1 = rs1.next();	
								 int i = 0;
								 if(more1){	
									out.println("<table align='center'  width='100%' class='table'><tr class=pdn_txtpos2 WIDTH=100%>");
									out.println("  <td WIDTH=20%>Finance No</td>");
									out.println("  <td WIDTH=20%>Invoice No</td>");
									out.println("  <td WIDTH=20%>Allocated Date</td>");
									out.println("  <td WIDTH=20% align=right>Invoice Amount</td>");
									out.println("  <td WIDTH=20% align=right>Allocated Amount</td>");
								 while(more1){	
									out.println("  <tr>");
									out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs1.getString(6)+"')\" ><u>"+rs1.getString(6)+"</u></td>"); 
									out.println("  <td align=left >"+rs1.getString(1)+"</td>"); 
									out.println("  <td align=left >"+rs1.getString(2)+"</td>"); 
									out.println("  <td align=right >"+nf.format(rs1.getDouble(3))+"</td>");
									out.println("  <td align=right >"+nf.format(rs1.getDouble(4))+"</td>");
									
									out.println("  </tr>");
									i = i+1;
									more1 = rs1.next();	
									
								 }
										out.println(" </table></div>");
					      
								}else{
								  out.println("  </div>");
					      
								}
								
							
							}		
							
							
 else if(m_chksql.trim().equals("get_deposite_details")){
							
							String m_receipt_no    = req.getParameter("m_receipt_no");
							
				       	rs1 = stmt1.executeQuery ("SELECT A.DIPOSIT_NO ,NVL(TO_CHAR(B.EFF_VALDATE,'DD-MM-YYYY'),'-') ,NVL(B.CHEQUE_NO,'-'),NVL(B.PAYER_ACC_NO,'-'),  "+
									                        "       NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(B.PAYER_BRANCH_CODE),'-') BANK_NAME, A.AMOUNT, NVL(C.BANK_CODE,'-') "+
																					"FROM   "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS A,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  B, "+
																					         " "+m_schema_name+".AF_CO_MAS_BANK_BRANCH C "+
																					"WHERE  A.RECEIPT_NO=B.REC_NO AND A.RECEIPT_NO=UPPER('"+m_receipt_no+"') AND  "+
																					" A.STATUS=('Y') AND B.PAYER_BRANCH_CODE=C.BRANCH_CODE(+) ");
																			
  		 
							 boolean more1 = rs1.next();	
								 int i = 0;
								 if(more1){	
									out.println("<table align='center'  width='100%' class='table'><tr class=pdn_txtpos2 WIDTH=100%>");
									out.println("  <td WIDTH=15%>Deposit Number</td>");
									out.println("  <td WIDTH=15%>Receipt Date</td>");
									out.println("  <td WIDTH=15%>Cheque Number</td>");
									out.println("  <td WIDTH=15%>Bank Account No</td>");
									out.println("  <td WIDTH=20%>Bank Name</td>");
									out.println("  <td WIDTH=20% align=right>Amount</td>");
								 while(more1){	
									out.println("  <tr>");
									out.println("  <td align=left style= cursor:hand; onClick=\"show_deposit_details('"+rs1.getString(1)+"')\" ><u>"+rs1.getString(1)+"</u></td>"); 
									out.println("  <td align=left  >"+rs1.getString(2)+"</td>"); 
									out.println("  <td align=left  >"+rs1.getString(3)+"</td>"); 
									out.println("  <td align=left  >"+rs1.getString(4)+"</td>"); 
									out.println("  <td align=left  >"+rs1.getString(5)+"</td>"); 
									out.println("  <td align=right >"+nf.format(rs1.getDouble(6))+"</td>");
									
									out.println("  </tr>");
									i = i+1;
									more1 = rs1.next();	
									
								 }
										out.println(" </table></div>");
					      
								}else{
								  out.println("  </div>");
					      
								}
								
							}
										
										

 else if(m_chksql.trim().equals("get_retrealiz_details")){
							
							String m_receipt_no    = req.getParameter("m_receipt_no");
							
																			
  		 						rs1= stmt.executeQuery (" SELECT UPPER(REC_NO),"+m_schema_name+".AF_CO_GET_DEPOSIT_NO(REC_NO),TO_CHAR(BANK_DATE,'DD-MM-YYYY'),SETTLE_MODE,RECON_STATUS, "+
						                              " TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),UPPER(NVL(CHEQUE_NO,'-')),REC_AMOUNT_CURR,UPPER(NVL(RETURN_CHARGE,'0')),UPPER(NVL(COMMENTS,' ')) "+
										                      " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT,  "+
																					" "+m_schema_name+".AF_CO_PRO_RETURN_DETAILS "+
										                      " WHERE RECON_STATUS='Y' AND  "+
										                      " GROUP_REC_NO IS NULL  AND "+
										                      " REC_NO = RECEIPT_NO(+) AND "+
										                      " "+m_schema_name+".AF_CO_GET_DEPOSIT_NO(REC_NO) =DIPOSIT_NO (+) AND  "+
										                      " REC_NO IN "+
    									                    "(SELECT RECEIPT_NO "+
     									                    " FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS "+
     										                  " /*WHERE DIPOSIT_NO IN "+
        							                    " (SELECT DIPOSIT_NO "+
         							                    " FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT "+
         							                    " WHERE   UPPER(ACC_NO)=UPPER('"+m_receipt_no+"'))*/) AND REC_NO =UPPER('"+m_receipt_no+"')   ");

				
				
				
							 boolean more1 = rs1.next();	
								 int i = 0;
								 if(more1){
									
									
									out.println("<table align='center'  width='100%' class='table'><tr class=pdn_txtpos2 WIDTH=100%>");
									out.println("  <td align=left  WIDTH=15%>Receipt No</td>");
									//out.println("  <td align=left WIDTH='110'>Deposit No</td>");
									out.println("  <td align=left WIDTH=15%>Transaction Date</td>");
									//out.println("  <td align=center WIDTH='100'>Returned Status</td>");
									//out.println("  <td align=center WIDTH='100'>Realised Status</td>");
									//out.println("  <td align=right WIDTH='150'>Return Charge</td>");
									out.println("  <td align=left WIDTH=15%>Realised date</td>");
									out.println("  <td align=left WIDTH=15%>Settlement Mode</td>");
									//out.println("  <td align=left WIDTH='100'>Reference No</td>");
									//out.println("  <td align=right WIDTH='150'>Amount</td>");
									out.println("  <td align=left WIDTH=20%>Comment</td>");
									out.println("  <td align=left WIDTH=20%></td>");
									
								 while(more1){	
									out.println("  <tr>");
								//	out.println("  <td align=left  >"+rs1.getString(1)+"</td>");  
									out.println("  <td align=left style= cursor:hand; onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" ><u>"+rs1.getString(1)+"</u></td>"); 
									//out.println("  <td align=left style= cursor:hand; onClick=\"show_deposit_details('"+rs1.getString(2)+"')\" ><u>"+rs1.getString(2)+"</u></td>"); 
									out.println("  <td align=left  >"+rs1.getString(3)+"</td>"); 
									
                  /*if(rs1.getString(5).equals("Y")){									
		              out.println("<TD align=center ><INPUT TYPE=\"checkbox\" NAME=CHK_RETURNED_STATUS VALUE=\"off\"   disabled ></td>");		
			            out.println("<TD align=center ><INPUT TYPE=\"checkbox\" NAME=CHK_REALIZED_STATUS  VALUE=\"on\"  checked  disabled></td>");			
									}
									else if(rs1.getString(5).equals("N")) {
		              out.println("<TD align=center ><INPUT TYPE=\"checkbox\" NAME=CHK_RETURNED_STATUS VALUE=\"on\"   checked disabled ></td>");		
			            out.println("<TD align=center ><INPUT TYPE=\"checkbox\" NAME=CHK_REALIZED_STATUS  VALUE=\"off\"    disabled></td>");			
									}
									else {
		              out.println("<TD align=center ><INPUT TYPE=\"checkbox\" NAME=CHK_RETURNED_STATUS VALUE=\"on\"    disabled ></td>");		
			            out.println("<TD align=center ><INPUT TYPE=\"checkbox\" NAME=CHK_REALIZED_STATUS  VALUE=\"on\"    disabled></td>");			
									}*/
									
									//out.println("  <td align=right><input class=\"txt_input\" type=\"text\" name=TXT_RET_CHARGE maxlength=\"10\" size=\"20\" value=\"\" style=\"{ width:130px;text-align:right;}\" disabled  ></td>"); 
									//out.println("  <td align=left ><input class=\"txt_input\" type=\"text\" name=TXT_COMMENT maxlength=\"10\" size=\"20\" value=\"\" style=\"{ width:130px;}\"  disabled ><input class=\"but_input\" type=\"button\" name=BUT_HELP'+line_no+' value=\"Help\"  disabled  ></td>"); 
								//	out.println("  <td align=center >"+rs1.getString(9)+"</td>"); 
									out.println("  <td align=center >"+rs1.getString(6)+"</td>"); 
									out.println("  <td align=left  >"+rs1.getString(4)+"</td>"); 
									//out.println("  <td align=left  >"+rs1.getString(7)+"</td>"); 
								//	out.println("  <td align=right >"+nf.format(rs1.getDouble(8))+"</td>");
									out.println("  <td align=center >"+rs1.getString(10)+"</td>"); 
			            out.println("  <td align=right><input class=\"but_input\" type=\"button\" name=BUT_DETAIL value=\"View\" onClick=\"load_details('"+rs1.getString(1)+"')\"  style=\"width: 40px\" ></td>'");
									out.println("  </tr>");
									i = i+1;
									more1 = rs1.next();	
									
								 }
										out.println(" </table></div>");
					      
								}else{
								  out.println("  </div>");
					      
								}
								
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









																					

