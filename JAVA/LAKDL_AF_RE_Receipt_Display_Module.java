//Option Id is 1.65 
//This File was created by SVA on 28-08-2006 
//1.65 Termination Receipt Process Display
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;

import oracle.jdbc.driver.*;

public class LAKDL_AF_RE_Receipt_Display_Module extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt12,stmt_act;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2,rs12,rs_act;
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
			String m_rep_cur=""; //added by nuwan de silva23-07-07
			
			String m_fschema_name=con_method.client_name.trim();
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 

			out = res.getOutputStream();
			//out.println("conn="+conn);
			//Class.forName("oracle.jdbc.driver.OracleDriver");
      //conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
			CallableStatement callstmt1 =null;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			stmt1= conn.createStatement ();
			stmt2= conn.createStatement ();
			stmt12= conn.createStatement ();
			stmt_act= conn.createStatement ();
			
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
			out.println(" var b_flag_inv=0;"); //added by nuwan de silva on 28-02-2008
			
		
			
			//Added by Mahela on 22-03-2007
			//Purpose : Receipt Document
			out.println("function show_document() {");
			
			//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Receipt_Document?chksql=main_page&receipt_no=\"+document.Form1.RECEIPT_NO.value+\"&client_no=\"+document.Form1.CLIENT_CODE.value+\"&print=TRUE\";"); 
			//out.println(" window.open(m_url);"); 	
			//out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=720,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
			
			//ADDED BY NUWAN DE SILVA--------------------------
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_View_Receipt_Document\";");
	    out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=650,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
	    out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Settlement?chksql=main_page';"); 
			
			out.println("}");
			
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
				//out.println(" alert('test--'+opt);");
        out.println(" if (http_request.readyState == 4) {");
        out.println("    if (http_request.status == 200) {");
				
				
				out.println("      if(opt==\"11\"){"); 
				//out.println("         alert(http_request.responseText);");
				//out.println("         rec.innerHTML=http_request.responseText; ");  
				//out.println("         assign_div();"); 
				out.println("         contract.innerHTML=http_request.responseText; ");
				//out.println("         fifo_aloc.innerHTML=http_request.responseText; ");
				out.println("      }else if(opt==\"10\"){");
				//out.println("         alert(http_request.responseText);");
				//out.println("         rec.innerHTML=http_request.responseText; ");  fifo_aloc
				//out.println("         assign_div();");
				out.println("         contract.innerHTML=http_request.responseText; ");
				
				out.println("      }else if(opt==\"12\"){");
				//out.println("         alert(http_request.responseText);");
				//out.println("         rec.innerHTML=http_request.responseText; ");  fifo_aloc
				//out.println("         assign_div();");
				
				out.println("         invoice_details.innerHTML=http_request.responseText; ");
				
				out.println("      }else if(opt==\"2\"){");
				//out.println("         alert(http_request.responseText);");
				//out.println("         rec.innerHTML=http_request.responseText; ");
				//out.println("         assign_div();");
				out.println("         inv.innerHTML=http_request.responseText; ");
				out.println("         allo_inv_det();");  
				out.println("         allo_other_inv_det();");
				
				out.println("      }else if(opt==\"3\"){");
				//out.println("         alert(http_request.responseText);");
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
				
				out.println("      }else if(opt==\"15\"){");
				out.println("         alert('wwwwww==='+http_request.responseText);");
				
				out.println("      }else if(opt==\"6\"){");
				//out.println("         alert(http_request.responseText);");
				out.println("         document.Form1.tot_val.value=http_request.responseText; ");
				out.println("         document.Form1.elements[\"SETT_AMOUN_0\"].value=format_noobject(document.Form1.tot_val.value); ");
				
				out.println("      }else if(opt==\"5\"){");
				//out.println("         alert(http_request.responseText);");
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
					//alert(data_vec[vsize]);
				out.println("    				}");
				out.println("   		   }");
        out.println("   		   }else{");
		    out.println("             if(type=='ExcRate'){"); 
			  out.println("               document.Form1.EXCHANE_RATE.value        =\"0\";"); 
				out.println("     document.Form1.EXCHANE_RATE.disabled =true;"); 	 //added by nuwan de silva 23-07-07
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
			//out.println(" window.open(m_url);");
			out.println("	load_interface(m_url,'NORM');");
			out.println("}");
			
			out.println("function makeRequest3(obj) {");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=get_return_receipt2&client=\"+obj+\"&rec_no=\"+document.Form1.RECEIPT_NO.value;");
			//out.println(" alert('Receipt No  '+document.Form1.RECEIPT_NO.value);");
			out.println("	 load_interface(m_url,'NORM');");
			//out.println(" document.Form1.CLIENT_CODE.disabled = true");
			out.println("}");
			
			out.println("function makeRequest4(obj) {");
		
		  out.println(" document.Form1.hid_help_status.value='H_account' ");
			////////////////out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_get_account_no&data_val=\"+obj;");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_get_account_no_receipt&data_val2=\"+document.Form1.CLIENT_CODE.value+\"&data_val=\"+obj;");
			//////out.println("  window.open(m_url);");
			out.println("	 load_interface(m_url,'XML');");
			
			out.println("}");
			
			
			out.println("function makeRequest_branch(obj) {");
		  out.println(" document.Form1.hid_help_status.value='H_branch' ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Temp_receipt_Branch_code&data_val=\"+obj+\"&ac_status=Y\";");
  		////////////////out.println("  window.open(m_url);");
			out.println("	 load_interface(m_url,'XML');");
			
			out.println("}");
			
			
			
			out.println("function makeRequest5(obj) {");
			out.println(" document.Form1.hid_help_status.value='H_client' ");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_get_client_code&data_val=\"+obj;");
			//out.println("  window.open(m_url);");
			out.println("	 load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest6(obj) {");
			out.println(" document.Form1.hid_help_status.value='H_receipt' ");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_get_receipt_no&data_val=\"+obj;");
		//	out.println("  window.open(m_url);");
			out.println("	 load_interface(m_url,'XML');");
			out.println("}");
			
			
			out.println("function makeRequest_account_no(obj) {");
			out.println(" document.Form1.hid_help_status.value='H_account_no' ");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_val_account_code&data_val=\"+obj.value+\"&ac_status=Y\";");
			//out.println("  window.open(m_url);");
			out.println("	 load_interface(m_url,'XML');");
			out.println("}");
			
			
			out.println("function get_vector(data_vec){ ");
			
			//out.println("			if( data_vec.length==0 && document.Form1.hid_help_status.value=='H_account' && document.Form1.PAY_ACCOUNT.value !='' ){");
			//----date		: --(2007-03-23)---------------------------------------------------------------------------------------------------
			//----modified: ------------------------------------------------------------------------------------------------------
   //   out.println("account_help();");
			//out.println("alert('Account code does not exists')");
			//out.println("document.Form1.PAY_BRANCH.value=\"\"");
			//out.println("document.Form1.PAY_BRANCH.disabled=false");
			//out.println("      account_help();");
			//----------------------------------------------------------------------------------------------------------

			//out.println("			}");
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
			//out.println("      receipt_help_2();");
			out.println("receipt_help()");
			out.println("			}");
			out.println("			 if(data_vec.length>0 && document.Form1.hid_help_status.value=='H_receipt' && document.Form1.RECEIPT_NO.value !='' ){");
			out.println("      get_Receipt();");
			out.println("			}");
			
			out.println("			 if( data_vec.length>0 && document.Form1.hid_help_status.value=='H_account_no' && document.Form1.TXT_ACCOUNT_NO.value !='' ){");
			out.println("      document.Form1.TXT_ACCOUNT_NO.VALUE=data_vec[0];");
			out.println("      document.Form1.hid_TXT_BRANCH_CODE.VALUE=data_vec[1];");
		  out.println("      document.Form1.BRANCH_NAME.value =data_vec[2]");
			out.println("      document.Form1.hid_TXT_ACC_REF_NO.VALUE=data_vec[3];");
			//out.println("  alert('test');");
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
			out.println("      document.Form1.PAY_BRANCH_NAME.value =data_vec[10]+' - '+data_vec[1]; ");
			out.println("			validate_cheque_no();"); ///llllll
			out.println("			}");
			
			out.println("else if(data_vec.length>0 && document.Form1.hid_help_status.value=='VAL_CHEQUE' ){"); //added by nuwan de silva 02-08-07
			out.println("alert('Record already exists');");
			out.println("  m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Settlement?chksql=view_cheques&cheque_no='+document.Form1.CHEQUE_NO.value+'&branch_code='+document.Form1.PAY_BRANCH.value;");
			out.println("window.open(m_url,'displayWindow2','left=450,top=200,width=600,height=350,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("document.Form1.CHEQUE_NO.value=\"\"");
			out.println("			}");
			
			out.println("}");
			
			
			out.println("function get_vector_normal(http_response){ ");
			//out.println(" alert(http_response)");
			out.println(" return_rec.innerHTML = ''; ");
			out.println(" return_rec.innerHTML = http_response; ");
			out.println(" if(parseFloat(document.Form1.hid_count.value)== 0 ){ ");
			out.println(" return_rec.innerHTML = ''; ");
			out.println(" document.Form1.hid_return_count.value=0 ");
			out.println(" }");
			out.println(" else{");
			out.println(" document.Form1.hid_return_count.value = parseFloat(document.Form1.hid_count.value);");
			out.println(" }");
			//out.println(" alert('return Count '+document.Form1.hid_return_count.value)");
			out.println("}");
			
       out.println("function addrow( data,type) {");
			 out.println(" str=\"\";");
       out.println(" i=0;");
       out.println(" if(data.length>0){");
       out.println("   if(type=='Rec'){"); 
			 out.println("     document.Form1.TERMINATION_NO.value       =data[0];"); 
			 //out.println("     get_Receipt_del();");
			 out.println("   }else if(type=='Cli'){"); 
			 out.println("     document.Form1.CLIENT_CODE.value     =data[0];"); 
			 out.println("     document.Form1.CLIENT_NAME.value     =data[1];"); 
				
				
				/*out.println("if(data[4]!='-' && data[5]!='-' ){");
				out.println("address=data[4]+','+data[5]; ");		
				out.println("}");		
				out.println("else if(data[4]!='-' && data[5]=='-' ){");
				out.println("address=data[4]; ");		
				out.println("}");		
				out.println("else if(data[4]=='-' && data!='-' ){");
				out.println("address=data[5]; ");		
				out.println("}");		
				*/
				
				//added by nuwan de silva 25-07-07----------------------------
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
				out.println("set_address_pay_type();"); //added by nuwan de silva 01-08-07
				out.println("document.Form1.CLIENT_CODE.disabled =true;"); //added by nuwan de silva
				out.println("document.Form1.cli_help.disabled    =true;"); //added by nuwan de silva
				//----------------------------------------------------------------
				
			 //out.println(" 		 makeRequest2(data[0]);");		
			 //out.println("     get_Receipt();");
			 out.println("   }else if(type=='ExcRate'){"); 
			 //out.println("     document.Form1.VEHICLE_NO.value      =data[0];"); 
				
			 //added by nuwan de silva 23-07-07-----------------------------------
			 out.println("     if(document.Form1.hid_rep_cur.value==document.Form1.CURR_CODE.value){");
			 out.println("     document.Form1.EXCHANE_RATE.value        =\"1\";"); 
			 out.println("     document.Form1.EXCHANE_RATE.disabled =true;"); 	
			 out.println("     }");	
			 out.println("     else{");
			 out.println("     document.Form1.EXCHANE_RATE.value        =data[0];"); 
				out.println("     document.Form1.EXCHANE_RATE.disabled =true;"); 	//Modified by Chandana on 24/10/2007
			 out.println("     }");		
				//---------------------------------------------------------------------
			 //out.println("     document.Form1.EXCHANE_RATE.value        =data[0];"); 
			 out.println("   }else if(type=='ChExcRate'){"); 
			 out.println("     document.Form1.EXCHANE_RATE.value    =data[1];"); 
		   out.println("     document.Form1.REP_AMOUNT.value      =data[0];"); 
			 out.println("   }else if(type=='RepAmt'){");  
			 out.println("     document.Form1.REP_AMOUNT.value        =data[0];"); 
			 
		   //out.println("     get_invoice();"); //Comment By Chandana on 17/09/2007
			 //out.println("     get_contract();"); //Comment By Chandana on 18/10/2007
			 
				out.println("   }else if(type=='client_comment'){");
			 out.println("  show_client_comment(data);");	
				
			 out.println("   }else if(type=='clnt_state'){");
			 out.println("  show_client_state(data);");	
				
				
			 out.println("   }else if(type=='SysDate'){");			 	
			 out.println("     document.Form1.VAL_DAY.value         =data[0];"); 
			 out.println("     document.Form1.VAL_MONTH.value       =data[1];"); 
			 out.println("     document.Form1.VAL_YEAR.value        =data[2];"); 
			 out.println("     document.Form1.HID_SYS_VAL_DAY.value         =data[0];"); 
		   out.println("     document.Form1.HID_SYS_VAL_MONTH.value       =data[1];"); 
			 out.println("     document.Form1.HID_SYS_VAL_YEAR.value        =data[2];"); 

			 out.println("     get_excharate();");  
			
			 out.println("   }else if(type=='ChequeDate'){");			 	
			 out.println("     document.Form1.CHEQUE_DATE_DD.value  =data[0];"); 
			 out.println("     document.Form1.CHEQUE_DATE_MM.value  =data[1];"); 
			 out.println("     document.Form1.CHEQUE_DATE_YY.value  =data[2];"); 
			
			 out.println("   }else if(type=='Receipt'){"); 
			 out.println("     document.Form1.RECEIPT_NO.value      =data[0];"); 
			 out.println("     document.Form1.VAL_DAY.value         =data[1];"); 
			 out.println("     document.Form1.VAL_MONTH.value       =data[2];"); 
			 out.println("     document.Form1.VAL_YEAR.value        =data[3];"); 
			 out.println("     document.Form1.CLIENT_CODE.value     =data[4];"); 
			 out.println(" 		 makeRequest3(data[4]);");	//display the return details
			 out.println("     document.Form1.CURR_CODE.value       =data[5];"); 
			 out.println("     document.Form1.AMOUNT.value          =data[6];"); 
				
			 out.println("		 if(data[25] == '-' && data[26]== '-' ){");		
			 out.println("     document.Form1.PAY_TYPE.value   =\"CLIENT\";"); 		
			 out.println("      } ");
			 out.println("      else { ");
			 out.println("     document.Form1.PAY_TYPE.value   =\"THIRD\";"); 	
			 out.println("     display_row_third_party(); ");				
			 out.println("     document.Form1.elements['CLIENT_NAME_1'].value   =data[25];"); //XXXXXXX	
			 out.println("     document.Form1.elements['CLIENT_ADDRESS_1'].value=data[26];"); 	
			 out.println("     document.Form1.elements['CLIENT_NAME_1'].disabled =true;");	
			 out.println("     document.Form1.elements['CLIENT_ADDRESS_1'].disabled =true;");	
			 out.println("      } ");
			 out.println("     document.Form1.EXCHANE_RATE.value    =data[7];"); 
			 out.println("     document.Form1.REP_AMOUNT.value      =data[8];"); 
			 out.println("     document.Form1.SETT_MODE.value       =data[9];"); 
			 out.println("     display_row_cheque(data_vec);");				 //added by nuwan de silva 02-08-07
				
			 out.println("		 if(data[9] !=\"CASH\"){"); //Added by Chandana on 02-10-2007	
			 out.println("		 if(data[10] == '' || data[10]== 'null' )");	
			 out.println("     document.Form1.PAY_BRANCH.value      ='';"); 	
			 out.println("		 else {");	
			 out.println("     document.Form1.PAY_BRANCH.value      =data[10];");
			 out.println("     document.Form1.PAY_BRANCH_NAME.value      =data[24];");	
			 out.println("      } ");
			 out.println("		 if(data[11] == '' || data[11]== 'null' )");		
			 out.println(" 		 document.Form1.PAY_ACCOUNT.value     ='';	");	
			 out.println(" 		 else");	
			 out.println("     document.Form1.PAY_ACCOUNT.value     =data[11];");
			 out.println("     disable_cheque_elemnt();");		
			 out.println("      } ");	
				
				//comment by nuwan de silva 02-08-07
			 /*out.println("		 if(data[12] == '' || data[12]== 'null' )");			
			 out.println(" 		 document.Form1.CHEQUE_NO.value     ='';	");	
			 out.println("		 else");	
			 out.println("     document.Form1.CHEQUE_NO.value       =data[12];");
			 */	
			 out.println("		 if(data[13] == '' || data[13]== 'null' )");	
			 out.println("     document.Form1.REMARK.value          ='';");
			 out.println("		 else");		
			 out.println("     document.Form1.REMARK.value          =data[13];");	
				
				//comment by nuwan de silva 02-08-07
			 /*out.println("		 if(data[14] == '' || data[14]== 'null' )");	
			 out.println("     document.Form1.CHEQUE_DATE_DD.value          ='';");
			 out.println("		 else");		
			 out.println("     document.Form1.CHEQUE_DATE_DD.value          =data[14];");	
       out.println("		 if(data[15] == '' || data[15]== 'null' )");	
			 out.println("     document.Form1.CHEQUE_DATE_MM.value          ='';");
			 out.println("		 else");		
			 out.println("     document.Form1.CHEQUE_DATE_MM.value          =data[15];");	
       out.println("		 if(data[16] == '' || data[16]== 'null' )");	
			 out.println("     document.Form1.CHEQUE_DATE_YY.value          ='';");
			 out.println("		 else");		
			 out.println("     document.Form1.CHEQUE_DATE_YY.value          =data[16];");	
			 */	
			 out.println("     if(data[9]=='STD_ORD' || data[9]=='DIR_DEP'){"); 
				out.println("m_table_account_no.innerHTML='<table align=\"left\" width=\"100%\" class=\"table\" border=\"0\"><tr ID=T_ID >'+");		//class=tr_input
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">Account Number *</TD>'+");
				out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ACCOUNT_NO maxlength=\"10\" size=\"10\" value=\"\" onblur=\"makeRequest_account_no(this)\" >'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_ACCOUNT_NO  value=\"Help\" onClick=\"account_number_help()\"></TD>'+");
				out.println("'<td  >Branch Name</td>'+");
			  out.println("'<td  ><input name=\"BRANCH_NAME\" type=\"text\" style=\"width:250px;\" maxlength=\"200\" class=\"txt_input\" disabled></td>'+");
				//out.println("'<td></td>'+");
				//out.println("'<td></td>'+");
				out.println("'</tr></table>';");
		   out.println("}");
				
			out.println("else if(data[9]=='CASH' ){"); //999999999
			out.println("m_table_tendered.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr ID=T_ID class=tr_input>'+");		
			out.println("'<TD WIDTH=\"20%\"  align=\"left\">Tendered Amount</TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TEN_AMOUNT style=\"{text-align:right;}\" maxlength=\"23\" value=\"'+data[17]+'\"  size=\"25\" onchange =\"calculate_balance(this)\" onblur=\"get_returned_value(this,25)\" disabled></TD>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");
			out.println("'<TD WIDTH=\"20%\"  align=\"left\">Returned Amount</TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=RET_AMOUNT style=\"{text-align:right;}\" maxlength=\"23\" value=\"'+data[18]+'\"   size=\"10\" onblur=\"cal_retamt(this)\" disabled ></TD>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");
			out.println("'</table>';");
      out.println("}");
			 out.println("if(parseFloat(unformat_noobject(data[19]))> 0 &&( parseFloat(unformat_noobject(data[20]))> 0 || parseFloat(unformat_noobject(data[21]))> 0 || parseFloat(unformat_noobject(data[22])) > 0 || parseFloat(unformat_noobject(data[23])) > 0 ) ){"); 	
			 out.println("document.Form1.OTHER_CHARGES.value=\"Y\" ");
			 out.println("display_other_charges(data_vec);");
			 out.println("}");	
			 //out.println("     get_invoice();");//added by nwuan de silva 25-07-07 //Comment By Chandana on 17/09/2007
			 //out.println("     get_contract();"); //Comment By Chandana on 19/10/2007
				out.println("     check_client();	");
			 out.println("   }"); 
       out.println(" }else{");
			 out.println("   if(type=='RepAmt'){");  
			 //out.println("     get_invoice();"); //Comment By Chandana on 17/09/2007
			//	out.println("     get_contract();"); //Comment By Chandana on 19/10/2007
				
				
			 out.println("   }"); 
       out.println(" }"); 
       out.println("}");
				//End Of Checking Values
				
			 out.println(" function disable_cheque_elemnt(){");	
			 out.println("     document.Form1.PAY_BRANCH.disabled =true;");
			 out.println("     document.Form1.PAY_BRANCH_NAME.disabled =true;");		
			 out.println("     document.Form1.PAY_ACCOUNT.disabled =true;"); 
			 out.println("     document.Form1.BUT_PAY_BRANCH.disabled =true;"); 
			 out.println("     document.Form1.accno_help.disabled =true;");
				out.println("}");	
				
				out.println(" function display_client_state(val){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=m_chk_LAKDL_AF_RE_val_client_state&data_val=\"+val+\"&ac_status=Y\";");
				out.println("  window.open(m_url);");
				out.println("   makeRequest(m_url,'4','clnt_state');");
				out.println("}");	
				
				
				out.println(" function display_client_comment(val){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=m_chk_LAKDL_AF_RE_val_client_comment&data_val=\"+val+\"&ac_status=Y\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','client_comment');");
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
				
				out.println("function cal_amount(opt,am1,am2,num) {");//
				out.println("   document.Form1.hid_win_opt.value=num;");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'3');");
				
        out.println("}");	
				
				out.println("function get_excharate(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_excharate&CURR_CODE=\"+document.Form1.CURR_CODE.value+\"&VAL_DATE=\"+document.Form1.VAL_DAY.value+\"-\"+document.Form1.VAL_MONTH.value+\"-\"+document.Form1.VAL_YEAR.value;");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','ExcRate');");
				out.println("}");
				
				out.println("function get_sysdate(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_sysdate\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','SysDate');");
				out.println("}");
				
				out.println("function get_invoice(val) {");
				//out.println("alert('rrrrrrr=======');");
				
				out.println("  if(document.Form1.CLIENT_CODE.value!=\"\" && document.Form1.AMOUNT.value!=\"\"){"); 
				//t.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=get_invoice&Client_Code=\"+document.Form1.CLIENT_CODE.value+\"&Amount=\"+unformat_noobject(document.Form1.AMOUNT.value)+\"&Type=\"+document.Form1.hid_option.value+\"&Rec_No=\"+document.Form1.RECEIPT_NO.value;");				
				//Added By Nuwan De Silva 28-03-2007
				out.println("if(document.Form1.OTHER_CHARGES.value==\"Y\" && document.Form1.TXT_RENTAL_OTHER_INV.value!=\"\" ){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=get_contract&Client_Code=\"+document.Form1.CLIENT_CODE.value+\"&Amount=\"+unformat_noobject(document.Form1.TXT_RENTAL_OTHER_INV.value)+\"&Other_Amount=\"+unformat_noobject(document.Form1.TXT_OTHER_INV.value)+\"&Type=\"+document.Form1.hid_option.value+\"&Rec_No=\"+document.Form1.RECEIPT_NO.value;");
				out.println("  }");
				out.println("else if(document.Form1.OTHER_CHARGES.value==\"N\" ){");
				//out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=get_invoice&Client_Code=\"+document.Form1.CLIENT_CODE.value+\"&Amount=\"+unformat_noobject(document.Form1.AMOUNT.value)+\"&Type=\"+document.Form1.hid_option.value+\"&Rec_No=\"+document.Form1.RECEIPT_NO.value;");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=get_contract&Client_Code=\"+document.Form1.CLIENT_CODE.value+\"&Amount=\"+unformat_noobject(document.Form1.AMOUNT.value)+\"&Other_Amount=\"+unformat_noobject(0.00)+\"&Type=\"+document.Form1.hid_option.value+\"&Rec_No=\"+document.Form1.RECEIPT_NO.value;");
				out.println("  }");
        out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'2','Invoice');");
				out.println("  }");
				
				out.println("}");
				
				
				
				
			 out.println("function assgn_balance(){");
			
			 out.println("m_num_of_cont = parseFloat(unformat_noobject(document.Form1.hid_cntract_cnt.value));");
			 //out.println(" alert('m_num_of_cont'+m_num_of_cont);");	
			
			 out.println("    for(k=0;k<m_num_of_cont;k++){");
			
			//out.println("alert(document.Form1.elements['Text_balance_amount'+k].value);");
			//out.println("alert(document.Form1.elements['Text_balance_amount2'+k].value);");
			out.println("document.Form1.elements['Text_balance_amount2'+k].value=document.Form1.elements['Text_balance_amount'+k].value;");
			out.println("}");
			out.println("}");
				
				
				
			  out.println("function inv_details() {");
				
				out.println("  if(document.Form1.CLIENT_CODE.value!=\"\"){");
				//out.println("alert('inv_details123');");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=get_inv_details&Client_Code=\"+document.Form1.CLIENT_CODE.value;");
				//out.println(" window.open(m_url);");
				
				out.println("   makeRequest(m_url,'12','inv_detail');");
				out.println("  }");
				out.println("  }");
				
				
				
				
				
				out.println("function get_contract() {");
							
				out.println("  if(document.Form1.CLIENT_CODE.value!=\"\" && document.Form1.AMOUNT.value!=\"\"){"); 
				
				out.println("if(document.Form1.OTHER_CHARGES.value==\"Y\" && document.Form1.TXT_RENTAL_OTHER_INV.value!=\"\" ){");
    		out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=get_contract_det&Client_Code=\"+document.Form1.CLIENT_CODE.value+\"&Amount=\"+unformat_noobject(document.Form1.TXT_RENTAL_OTHER_INV.value)+\"&Other_Amount=\"+unformat_noobject(document.Form1.TXT_OTHER_INV.value)+\"&Type=\"+document.Form1.hid_option.value+\"&Rec_No=\"+document.Form1.RECEIPT_NO.value;");
				out.println("  }");
				out.println("else if(document.Form1.OTHER_CHARGES.value==\"N\" ){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=get_contract_det&Client_Code=\"+document.Form1.CLIENT_CODE.value+\"&Amount=\"+unformat_noobject(document.Form1.AMOUNT.value)+\"&Other_Amount=\"+unformat_noobject(0.00)+\"&Type=\"+document.Form1.hid_option.value+\"&Rec_No=\"+document.Form1.RECEIPT_NO.value;");
				//out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=get_contract&Client_Code=\"+document.Form1.CLIENT_CODE.value+\"&Amount=\"+unformat_noobject(document.Form1.AMOUNT.value)+\"&Type=\"+document.Form1.hid_option.value+\"&Rec_No=\"+document.Form1.RECEIPT_NO.value;");
				out.println("  }");
				out.println("   window.open(m_url);");
        out.println("   makeRequest(m_url,'10','Contract');");
				out.println("  }");
				
				out.println("}");				
				
				
				
				out.println("function check_rep_amount_onsave() {");
				out.println(" m_count = 0 ");
				out.println(" if( return_rec.innerHTML != '' )");
				out.println(" m_count = parseFloat(document.Form1.hid_count.value); ");
				//out.println(" alert('count @@ '+m_count);");
				out.println(" for(var i=0;i<m_count;i++){ ");
				out.println(" m_amount = \"AMOUNT_\"+i ");
				out.println(" m_amount_val =  parseFloat(unformat_number(document.Form1.elements[m_amount]))");
				//out.println(" alert('Amount  @@ '+m_amount_val);");
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
				//out.println(" alert('lineno @@'+no)");
				//out.println(" for(var i=0;i<m_count;i++ ){ ");
				out.println(" m_amount = \"AMOUNT_\"+no");
				out.println(" document.Form1.elements[m_amount].value = format_noobject(document.Form1.elements[m_amount].value); ");
				//out.println(" }");
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
				
				out.println("if(document.Form1.AMOUNT.value!='' && isnumberok(document.Form1.AMOUNT,25)){");
				out.println("   document.Form1.AMOUNT.value=format_noobject(document.Form1.AMOUNT.value);");
				
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=cal_rep_amount&AMOUNT=\"+unformat_noobject(document.Form1.AMOUNT.value)+\"&EXC_RATE=\"+unformat_noobject(document.Form1.EXCHANE_RATE.value)+\"\";");
				
				//out.println("   window.open(m_url);");
				
				
				//ADDED BY NUWAN DE SILVA
				out.println("if(document.Form1.OTHER_CHARGES.value==\"Y\"){");
			//	out.println("   document.Form1.TXT_TOT_ENTERED.value=format_noobject(document.Form1.AMOUNT.value);");
			  out.println("m_balance=parseFloat(unformat_noobject(document.Form1.AMOUNT.value)) - parseFloat(m_balance)");
				out.println("   document.Form1.TXT_BALANCE_PENDING.value=format_noobject(m_balance);");
				//out.println("   document.Form1.TXT_RENTAL_OTHER_INV.value=format_noobject(document.Form1.AMOUNT.value);");
    
				out.println("}");
				
				out.println("}");
				
				out.println("else{");
				out.println("alert('Please enter a number');");
				out.println("   document.Form1.AMOUNT.value=''");
				out.println("}");
				
				//out.println("alert('qqqqqqqq');");
				
				out.println("if(document.Form1.EXCHANE_RATE.value!='' && isnumberok(document.Form1.EXCHANE_RATE,6)){");
				out.println("   document.Form1.EXCHANE_RATE.value=format_noobject(document.Form1.EXCHANE_RATE.value);");
				out.println("   makeRequest(m_url,'4','RepAmt');");
				out.println("}");
				out.println("else{");
				out.println("alert('Please enter a number');");
				out.println("   document.Form1.EXCHANE_RATE.value=''");
				out.println("}");

				
				out.println("}");
				
				
				
				out.println("function cal_exc_rate(val) {");
								
				out.println("if(document.Form1.REP_AMOUNT.value!='' && isnumberok(document.Form1.REP_AMOUNT,25)){");
				
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=cal_exc_rate&CURR_CODE=\"+document.Form1.CURR_CODE.value+\"&EXC_RATE=\"+unformat_noobject(document.Form1.EXCHANE_RATE.value)+\"&AMOUNT=\"+unformat_noobject(document.Form1.AMOUNT.value)+\"&REP_AMOUNT=\"+unformat_noobject(document.Form1.REP_AMOUNT.value)+\"\";");
        out.println("   document.Form1.REP_AMOUNT.value=format_noobject(document.Form1.REP_AMOUNT.value);");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','ChExcRate');");
				out.println("}");
				out.println("else");
				out.println("{");
				out.println("alert('Please enter a number');");
				out.println("   document.Form1.REP_AMOUNT.value=''");
				out.println("}");
				out.println("}");
				
				out.println("function get_Receipt(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_receipt&RECEIPT_NO=\"+document.Form1.RECEIPT_NO.value+\"\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','Receipt');");
				out.println("}");
				
				out.println("function get_Return_Receipt(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=get_return_receipt&client=\"+document.Form1.CLIENT_CODE.value+\"\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','Receipt');");
				out.println("}");
				
				out.println("function check_client(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_client_code&client_code=\"+document.Form1.CLIENT_CODE.value+\"\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','Cli');");
        out.println("}");
				
				
				out.println("function cal_total_amount_entered(val) {");
				
				out.println("}");
				
				
				
				
				
			out.println("function validate_data(){"); 
			out.println("m_sub=0;"); 
			//out.println("alert('status &'+document.Form1.hid_option.value) ");
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

			out.println("if(document.Form1.SETT_MODE.value==\"CHEQUE\" ){  "); //|| document.Form1.SETT_MODE.value==\"STD_ORD\"
			//out.println("alert('s'+document.Form1.SETT_MODE.value);"); 
			out.println("if(document.Form1.document.Form1.CHEQUE_DATE_DD.value+document.Form1.CHEQUE_DATE_MM.value+document.Form1.CHEQUE_DATE_YY.value==\"\" ){  ");  //Added By nuwan De silva
			out.println("  CDATE.style.color='red';");
			out.println("  m_sub = 1;;"); 
			out.println("}"); 
			out.println("if(document.Form1.CHEQUE_NO.value==\"\"  ){  ");  //Added By nuwan De silva 17-07-07 //
			out.println("  CARNO.style.color='red';");
			out.println("  m_sub = 1;;"); 
			out.println("}"); 
			out.println("if(document.Form1.PAY_BRANCH.value==\"\"  ){  ");  //Added By nuwan De silva 02-08-07 
			out.println("  PBRANCH.style.color='red';");
			out.println("  m_sub = 1;;"); 
			out.println("}"); 
			
			out.println("}"); 
			
			out.println("if(document.Form1.SETT_MODE.value==\"STD_ORD\" || document.Form1.SETT_MODE.value==\"DIR_DEP\"){  "); 
			out.println("if(document.Form1.TXT_ACCOUNT_NO.value==\"\" ){  ");  
			out.println("  ACNO.style.color='red';");
			out.println("  m_sub = 1;;"); 
			out.println("}"); 
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
			//out.println("cal_balance();");
			//out.println("alert(m_status);");
			//out.println("document.Form1.hid_status.value=\"Delete\";");
			//out.println(" alert(document.Form1.hid_status.value);");
			
			//out.println(" check_contract();");
			
			
			out.println(" if(document.Form1.hid_status.value == \"Delete\"){");
			out.println("befor_save();");
			out.println("}else{");
			
			out.println(" if(document.Form1.TXT_FIFO.value == \"FIFO_MANU\"){");
			
			out.println("if(check_contract()){");
			out.println("befor_save();");
			out.println("}else{");
			out.println("if(cal_balance()){"); //||(document.Form1.hid_cntract_cnt.value==0)
      out.println("befor_save();"); 
      out.println("}else{");
			out.println("alert('Pleace check, Allocated amounts incorrect.');");
			out.println("}");
			out.println("}");
			
			out.println("}else if(document.Form1.TXT_FIFO.value == \"FIFO_AUTO\"){");
			out.println("befor_save();");
			out.println("}");
			
			out.println("else if(document.Form1.TXT_FIFO.value == \"NO_ALLO\"){");
			out.println("befor_save();");
			out.println("}");
			
			out.println("}");
			
			out.println("}");
			
			out.println("function befor_save(){ "); 
			
			//out.println("cal_balance();");
			
			//out.println("alert('==='+document.Form1.hid_invoice_count.value);");
			
			out.println("assign_hidden_values();");
			out.println("   m_status = document.Form1.hid_option.value ");
			out.println("   m_save_msg='Are you sure you want to Save ? ';"); 
			out.println("   if(m_status == \"EDIT\"){ ");
			out.println("   m_save_msg = 'Are you sure you want to Modify ? '");
			out.println("   }"); 
			out.println("   else if(m_status == \"DELETE\"){");
			out.println("   m_tot=0;"); //added by nuwan de silva 02-08-07
			out.println("   m_amount_entered=0;");  //added by nuwan de silva 02-08-07
			out.println("   m_balance=0;");  //added by nuwan de silva 02-08-07
			out.println("   m_save_msg = 'Are you sure you want to Delete ? '");
			out.println("   }"); 
			out.println("		if(validate_data()){"); 
			out.println("		document.Form1.b_submit.disabled=true"); 
			out.println("		document.Form1.b_submit1.disabled=true"); 
			
			out.println("		if(m_tot==m_amount_entered && m_balance==0 ){");  //modified by nuwan de silva 09-07-07
			out.println("		if(confirm(m_save_msg)){ "); 
			out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("		document.Form1.elements[i].disabled=false;");
			out.println("		}");
			//disable save button added by nuwan de silva
			out.println("		document.Form1.b_submit.disabled=true"); 
			out.println("		document.Form1.b_submit1.disabled=true"); 
			
			//out.println("		document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Save';");  
			out.println("		document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Save_Receipt';");  //added by nuwan de silva
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			
			out.println("		}"); 
			out.println("		else { "); 
			out.println("		alert(\"Total Amount Entered Should Equal To The Amount Entered Balace Pending \" +m_balance); ");
			out.println("		}");
			
			
			
			out.println("		}"); 
			out.println("		else { "); 
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		}");
			out.println("} "); 
			

      out.println("function befor_reset(){");
			out.println(" if(confirm(\"Are you sure you want to clear the screen?\")){  ");
			//out.println("  Form1.reset()   ");
		  out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=main_page'");
		  out.println(" }  ");
			out.println("}");
			
			out.println("function befor_back(){");
			out.println("   close_window(); ");
			//out.println(" if(confirm(\"Are you sure?\")){  ");
			//out.println("  top.frames[1].location=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_FollowupAlert?chksql=main_page\";");
			//out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
			//out.println(" }  ");
			out.println("}");
			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			//if(m_Followu_no==null){
			//out.println("		window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup';"); 
			//}else{
			out.println("		window.close();");
			//}
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=main_page'");
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
			out.println("help_box.innerHTML=\" Collection - Receipts - Entry - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection - Receipts - Entry - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			 out.println("function befor_clear(){");
			 out.println("     document.Form1.RECEIPT_NO.value      ='';"); 
			 out.println("     document.Form1.CLIENT_CODE.value     ='';"); 
			 out.println("     document.Form1.CLIENT_NAME.value     ='';"); 	
			 out.println("     document.Form1.AMOUNT.value          ='';"); 
			 //out.println("     document.Form1.EXCHANE_RATE.value    ='';"); 
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
			//out.println("new_window();");
		//	out.println("document.Form1.BUT_HELP_MAIN.disabled=true;"); 
			out.println(" if(confirm(\"Are you sure you want to enter New record?\")){  ");
			out.println("document.Form1.CLIENT_CODE.disabled=false;"); 
			out.println("document.Form1.cli_help.disabled=false;"); 
			out.println("document.Form1.rec_help.disabled=true;"); 
			//out.println("document.Form1.veh_help.disabled=false;"); 
			//out.println("document.Form1.rec_help.disabled=true;"); 
			out.println("document.Form1.RECEIPT_NO.disabled=true;");
			out.println("new_window(); ");
			//out.println("befor_clear();");
			out.println("}"); 
			
			out.println("}else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val==\"EDIT\"){"); 
			out.println(" if(confirm(\"Are you sure you want to Modify a record?\")){  ");
		//	out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.RECEIPT_NO.disabled=false;"); 
			out.println("document.Form1.CLIENT_CODE.disabled=true;"); 
			out.println("document.Form1.cli_help.disabled=true;"); 
			out.println("document.Form1.rec_help.disabled=false;"); 
			out.println("document.Form1.RECEIPT_NO.disabled=false;"); 
			out.println("document.Form1.CHEQUE_DATE_DD.disabled=false;");
			out.println("document.Form1.CHEQUE_DATE_MM.disabled=false;");
			out.println("document.Form1.CHEQUE_DATE_YY.disabled=false;");
			out.println("document.Form1.PAY_ACCOUNT.disabled=false;");
			out.println("document.Form1.accno_help.disabled=false;");
			out.println("befor_clear();");
			out.println("}"); 
			out.println("}else if(m_val==\"DELETE\"){"); 
			out.println(" if(confirm(\"Are you sure you want to  Delete a record?\")){  ");
			//out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.RECEIPT_NO.disabled=false;"); 
			out.println("document.Form1.CLIENT_CODE.disabled=true;"); 
			out.println("document.Form1.cli_help.disabled=true;"); 
			out.println("document.Form1.charges.disabled=true;"); 
			out.println("document.Form1.rec_help.disabled=false;"); 
			out.println("document.Form1.RECEIPT_NO.disabled=false;");
			out.println("document.Form1.AMOUNT.disabled=true;"); //added by nuwan de silva  02-08-07
			out.println("document.Form1.REP_AMOUNT.disabled=true;"); //added by nuwan de silva  02-08-07
			out.println("document.Form1.EXCHANE_RATE.disabled=true;"); //added by nuwan de silva  02-08-07
			out.println("document.Form1.PAY_ACCOUNT.disabled=true;"); //added by nuwan de silva  02-08-07
			out.println("document.Form1.PAY_BRANCH.disabled=true;"); //added by nuwan de silva  02-08-07
			out.println("document.Form1.BUT_PAY_BRANCH.disabled=true;"); //added by nuwan de silva  02-08-07
			out.println("document.Form1.accno_help.disabled=true;"); //added by nuwan de silva  02-08-07
			out.println("document.Form1.PAY_BRANCH_NAME.disabled=true;"); //added by nuwan de silva  02-08-07
			out.println("document.Form1.REMARK.disabled=true;"); //added by nuwan de silva  02-08-07
			out.println("document.Form1.OTHER_CHARGES.disabled=true;"); //added by nuwan de silva  02-08-07
			out.println("document.Form1.SETT_MODE.disabled=true;"); //added by nuwan de silva  02-08-07
			out.println("document.Form1.PAY_TYPE.disabled=true;"); //added by nuwan de silva  02-08-07
			out.println("document.Form1.CURR_CODE.disabled=true;"); //added by nuwan de silva  02-08-07
			out.println("document.Form1.VAL_DAY.disabled=true;"); //added by nuwan de silva  02-08-07
			out.println("document.Form1.VAL_MONTH.disabled=true;"); //added by nuwan de silva  02-08-07
			out.println("document.Form1.VAL_YEAR.disabled=true;"); //added by nuwan de silva  02-08-07  //555555 
			out.println("document.Form1.client_det.disabled=true;");   
			out.println("document.Form1.Allocate.disabled=true;"); 
			out.println("document.Form1.TXT_FIFO.disabled=true;");
			
			out.println("befor_clear();");
			out.println("}"); 
			
			
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
			
			out.println(" if(document.Form1.hid_help_type.value == '1') { ");
			out.println(" return_rec.innerHTML = ''; ");
			out.println(" document.Form1.CLIENT_NAME.value=''; "); 
			out.println(" document.Form1.CLIENT_CODE.value=''; }"); 
			out.println(" else if(document.Form1.hid_help_type.value == '2'){ ");
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
			out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_PRO_CR_Help_Servlet?class_in="+m_client_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			//out.println("window.open('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_PRO_CR_Help_Servlet?class_in="+m_client_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=');");
	
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
			
				//client Help
				out.println("function client_help(){");
				out.println("Crit=document.Form1.CLIENT_CODE.value+\"@\";");
				out.println(" document.Form1.hid_help_type.value='1' ");
				//out.println("HelpBox('1','10','0',Crit,'ClientSql','1');");
				out.println("HelpBox('1','10','0',Crit,'ClientSql_Receipt','1');"); //ADDED by nuwan de silva on07-04-2008
				out.println("}");		
				
				out.println("function client_assign(oBj){");
				out.println(" document.Form1.CLIENT_NAME.value =oBj.valout[3]");
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[9]");
				//out.println(" display_client_state(oBj.valout[2]);"); //added by nuwan de silva 0=04-08-2008.............
				out.println(" display_client_comment(oBj.valout[9]);"); //added by nuwan de silva 0=04-08-2008.............
				//out.println("show_client_comment();");
				out.println(" inv_details();");
				out.println(" var address= '';");
				//added by nuwan de silva 25-07-07----------------------------
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
				//----------------------------------------------------------------
				
				out.println(" makeRequest2(oBj.valout[2]);");
				//out.println(" get_Receipt();");
				out.println("set_address_pay_type();"); //added by nuwan de silva 01-08-07
				out.println("document.Form1.CLIENT_CODE.disabled =true;"); //added by nuwan de silva
				out.println("document.Form1.cli_help.disabled    =true;"); //added by nuwan de silva
				
				out.println("}");
        
				//receipt Help
				out.println("function receipt_help(){");
				out.println("Crit=document.Form1.CLIENT_CODE.value+\"@\";");
				out.println(" document.Form1.hid_help_type.value='2' ");
				out.println("HelpBox('1','10','0',Crit,'ReceiptSql','2');");
				out.println("}");		
				

				
				out.println("function receipt_assign(oBj){");
				out.println(" document.Form1.RECEIPT_NO.value =oBj.valout[2]");
				out.println(" get_Receipt();");
				//out.println(" makeRequest3();");
				out.println("}");
        
				//account Help
				out.println("function account_help(){");
				//out.println("Crit=document.Form1.PAY_ACCOUNT.value+\"@\"+\"Y@\";");
				out.println("Crit=document.Form1.PAY_ACCOUNT.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\"+\"Y@\";");
				out.println(" document.Form1.hid_help_type.value='3' ");
				//out.println("HelpBox('1','10','0',Crit,'AccountSql','3');");
				out.println("HelpBox('1','10','0',Crit,'AccountSql_receipt','3');");
				
				out.println("}");		
				
				out.println("function account_assign(oBj){");
				out.println(" document.Form1.PAY_ACCOUNT.value =oBj.valout[2]");
				out.println(" document.Form1.PAY_BRANCH.value =oBj.valout[3]");
				out.println(" document.Form1.PAY_BRANCH_NAME.value =oBj.valout[6]+' - '+oBj.valout[4] "); //666666
				//out.println(" get_Receipt();");
				out.println("}");
				
				
				
				//Branch Help
				out.println("function branch_help(){");
				out.println("Crit=document.Form1.PAY_BRANCH.value+\"@\"+\"Y@\";");
				out.println(" document.Form1.hid_help_type.value='8' ");
				out.println("HelpBox('1','10','0',Crit,'m_help_TXT_BANK_BRANCH_CODE_sql','8');");
				
				out.println("}");		
				
				out.println("function branch_assign(oBj){");
				out.println(" document.Form1.PAY_BRANCH.value =oBj.valout[2]");
				out.println(" document.Form1.PAY_BRANCH_NAME.value = oBj.valout[4] +' - '+ oBj.valout[3] ");
								
				out.println("}");
				
				
				
				
				
				
				out.println("function assign_account(data_vec){");
				out.println(" document.Form1.PAY_ACCOUNT.value =data_vec[0]");
				out.println(" document.Form1.PAY_BRANCH.value =data_vec[1]"); 
				out.println(" document.Form1.PAY_BRANCH_NAME.value = data_vec[4]+' - '+data_vec[3]");
				//out.println(" get_Receipt();");
				out.println("}");
				
			out.println("function load_edit_window(i,foll_no,type) {");
			out.println("   ");
			out.println("}"); 

			out.println("function befor_end(m_obj) {");
      out.println("   m_obj.focus();");
      out.println("}");
			
			out.println("function display_charges_pending(m_client_code){");
			out.println("if(document.Form1.CLIENT_CODE.value!=\"\"){");
			//out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Receipt_Charges_Details?chksql=LOAD_CHARGES_DETAILS&client_code=\"+m_client_code;");
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Receipt_Charges_Details?chksql=LOAD_CHARGES_DETAILS_IMPROVED&client_code=\"+m_client_code;");
			out.println("  window.open(m_url,'popupwin3','status=0,menubar=0,scrollbars=1,height=500,width=550,resizable=1');");
		  out.println("}");		
			out.println("else");		
			out.println("{");		
			out.println("  CCODE.style.color='red';");
			out.println("}");		
			out.println("}");		
			
				//account Help
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
				
				//out.println(" document.Form1.hid_ACCOUNT_NO.value =oBj.valout[2]");
				//out.println(" document.Form1.PAY_BRANCH.value =oBj.valout[3]");
				//out.println(" get_Receipt();");
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
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
							
			out.println("function load_history(num) {");
			out.println("	if(num!=''){ ");
      out.println("	  popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup?chksql=get_History&deal_no=\"+num+\"\", \"oBj\",\"left=100,top=200,width=650,height=400\");"); 
			out.println("	}else{");
			out.println("	  alert('Please enter Followup Number and continue!');");
			out.println("	}");
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
											
			out.println("function check_status(num) {");
			//out.println("alert(document.Form1.elements['Text_standard'+num].checked);");
			out.println("if(document.Form1.elements['Text_standard'+num].checked){");
			out.println(" document.Form1.elements['Text_standard'+num].value=\"YES\";");
			out.println("}else{");
			out.println(" document.Form1.elements['Text_standard'+num].value=\"NO\";");
			out.println("}");
			out.println("}");
			
			out.println("function check_amount(num) {");
			//out.println("alert(document.Form1.elements['Text_standard'+num].checked);");
			out.println("if(Number(document.Form1.elements['Text_sett_amount'+num].value)>Number(document.Form1.elements['Hid_amount'+num].value)){");
			out.println(" alert('Amount cannot be greater than Net Amount');");
			out.println(" document.Form1.elements['Text_sett_amount'+num].value = document.Form1.elements['Hid_amount'+num].value;");
			out.println("}");
			out.println("}");

			out.println("function check_Date(val1,val2,val3) {");
			out.println("");
			out.println("}");
			
			out.println("function disable_help(){");
		//	out.println("document.Form1.BUT_HELP_MAIN.disabled=true;"); 
			//out.println(" alert(' ok');");
			out.println("}");
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
							
			out.println("function load_c_date(val) {");
      out.println("  if(document.Form1.hid_cal_date.value=='4'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("if(document.Form1.hid_option.value==\"NEW\"){");  //modified by nuwan de silva 02-08-07
			//out.println("     document.Form1.VAL_DAY.value=v_dd;"); //Comment By Chandana for Ref No.873 on 15-10-07
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

      out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			//out.println("if(document.Form1.SETT_MODE.value==\"STD_ORD\" || document.Form1.SETT_MODE.value==\"DIR_DEP\" ){");
			out.println("     document.Form1.VAL_DAY.value=v_dd;");
			out.println("     document.Form1.VAL_MONTH.value=v_mm;");
			out.println("     document.Form1.VAL_YEAR.value=v_yy;");
			out.println("     validate_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR,document.Form1.HID_SYS_VAL_DAY,document.Form1.HID_SYS_VAL_MONTH,document.Form1.HID_SYS_VAL_YEAR);");
			//out.println("  }");				
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
			//out.println("document.Form1.hid_TXT_RENTAL_OTHER_INV.value=\"\""); 
			//out.println("document.Form1.hid_INSURANCE_PREMIUM.value=\"\""); 
			//out.println("document.Form1.hid_TXT_LUX_TAX.value=\"\""); 
			//out.println("document.Form1.hid_TXT_REVENUE_LICENCY.value=\"\""); 
			//out.println("document.Form1.hid_TXT_RMV_REG_FEES.value=\"\""); 
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
			//out.println("cal_rep_amount(document.Form1.TXT_RENTAL_OTHER_INV.value);");
			//out.println("  get_invoice();"); //Comment By Chandana on 17/09/2007
			//out.println("     get_contract();"); //Comment By Chandana on 19/10/2007
			out.println("}"); 
			out.println("if(document.Form1.TXT_OTHER_INV.value!=\"\" && isnumberok(document.Form1.TXT_OTHER_INV,25)){"); 
			out.println("m_other_invoice=unformat_noobject(document.Form1.TXT_OTHER_INV.value)");
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
			//out.println("m_tot=parseFloat(m_rental_other_inv)+parseFloat(m_insurance_premium)+parseFloat(m_lux_tax)+parseFloat(m_revenue_lux)+parseFloat(m_rmv_reg_fee)+parseFloat(m_tendered)"); 
			out.println("m_tot=parseFloat(m_rental_other_inv)+parseFloat(m_insurance_premium)+parseFloat(m_lux_tax)+parseFloat(m_revenue_lux)+parseFloat(m_rmv_reg_fee)+parseFloat(m_other_invoice)"); //modified by nuwan de silva 06-07-07
						
			out.println("if(m_tot>m_amount_entered){");
			out.println("alert('The amount entered is more than the total value')"); 
			out.println("obj.value='';"); 
			//out.println("calculate_balance(obj);");
			out.println("}"); 
			out.println("else {"); 
			out.println("document.Form1.TXT_TOT_ENTERED.value=m_tot");
			out.println("m_balance=parseFloat(m_amount_entered)- parseFloat(m_tot)");
			out.println("document.Form1.TXT_BALANCE_PENDING.value=m_balance;");
			out.println("}"); 
			out.println("}");   //added by nuwan de silva 06-07-07
			out.println("}"); 
			
			
			out.println("function display_other_charges(data_vec){");
			out.println("m_table_other_charges.innerHTML=\"\" ");
			out.println("if(document.Form1.OTHER_CHARGES.value==\"Y\"){");
			out.println("header();");
			
			
			out.println("m_table_other_charges.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr ID=T_ID class=tr_input>'+");		
			out.println("'<TD WIDTH=\"20%\"  align=\"left\">Rental Invoices</TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_RENTAL_OTHER_INV maxlength=\"25\"  style=\"{text-align:right;}\" size=\"10\" value=\"'+data_vec[19]+'\" onblur=\"calculate_balance(this),check_number(this,25)\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"20%\"  align=\"left\">Total Amount entered</TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_TOT_ENTERED maxlength=\"25\"   style=\"{text-align:right;}\" size=\"10\" value=\"'+data_vec[6]+'\" onblur=\"check_number(this,25)\"  disabled></TD>'+");
			out.println("'</tr>'+");
  		//out.println("'<TD WIDTH=\"20%\"  align=\"left\">Other Invoices</TD>'+"); // commented by udara on 08-05-2012
			out.println("'<TD WIDTH=\"20%\"  align=\"left\">Insurance</TD>'+"); // added by udara on 08-05-2012
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\"  name=TXT_OTHER_INV maxlength=\"25\"style=\"{text-align:right;}\"  size=\"10\" value=\"'+data_vec[20]+'\" onBlur=\"calculate_balance(this),check_number(this,25)\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"20%\"  align=\"left\">Balance Pending</TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\"  name=TXT_BALANCE_PENDING maxlength=\"25\"  style=\"{text-align:right;}\" size=\"10\" value=\"0.00\" onblur=\"check_number(this,25)\" disabled ></TD>'+");
			out.println("'</tr>'+");
			//out.println("'<TD WIDTH=\"20%\"  align=\"left\">Luxury Tax</TD>'+"); //modified by nwuan de silva  04-07-07
			//out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_LUX_TAX maxlength=\"23\" size=\"10\"  style=\"{text-align:right;}\" value=\"0\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\" ></TD>'+");
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"></TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input value=\"0\" class=\"txt_input\"  maxlength=\"50\" type=\"hidden\" name=TXT_INSURANCE_PREMIUM ></TD>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");			
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"></TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input value=\"0\" class=\"txt_input\"  maxlength=\"50\" type=\"hidden\" name=TXT_LUX_TAX  ></TD>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");
			//out.println("'<TD WIDTH=\"20%\"  align=\"left\">Revenue License</TD>'+"); 
			//out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_REVENUE_LICENCY maxlength=\"23\"   style=\"{text-align:right;}\" size=\"10\" value=\"0\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\" ></TD>'+");
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"></TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input value=\"0\" class=\"txt_input\"  maxlength=\"50\" type=\"hidden\" name=TXT_REVENUE_LICENCY  ></TD>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");
			//out.println("'<TD WIDTH=\"20%\"  align=\"left\">RMV Registration Fees</TD>'+");
			//out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_RMV_REG_FEES maxlength=\"23\"  style=\"{text-align:right;}\" size=\"10\" value=\"0\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\" ></TD>'+");
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"></TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input value=\"0\" class=\"txt_input\"  maxlength=\"50\" type=\"hidden\" name=TXT_RMV_REG_FEES ></TD>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");
			out.println("'</table>';");
			
			
			
			/*
			
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
			out.println("'</table>';"); */
	//		out.println("   document.Form1.TXT_TOT_ENTERED.value=format_noobject(document.Form1.AMOUNT.value);");
				//out.println("   document.Form1.TXT_BALANCE_PENDING.value=format_noobject(document.Form1.AMOUNT.value);");
			out.println("}");				
			out.println("}");				
			
			
			out.println("function header(){");
			out.println("m_table_other_charges.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr ID=T_ID class=tr_input>'+");		
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"><b><u>Charges Allocation</u></TD>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");
			out.println("'</table>';");
			out.println("}");				
			
			out.println("function get_other_charges(){");
			out.println("m_table_other_charges.innerHTML=\"\" ");
			out.println("inv.innerHTML=\"\" ");
			//out.println("if(document.Form1.OTHER_CHARGES.value==\"Y\"){"); // commented by udara on 08-05-2012
			out.println("if(document.Form1.OTHER_CHARGES.value==\"Y\" ){");
			out.println("header();");
			//modified by nwuan de silva  23-07-07
			out.println("m_table_other_charges.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr ID=T_ID class=tr_input>'+");		
			out.println("'<TD WIDTH=\"20%\"  align=\"left\">Rental Invoices</TD>'+");
			//out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_RENTAL_OTHER_INV maxlength=\"23\"  style=\"{text-align:right;}\" size=\"10\" value=\"0\" onBlur=\"calculate_balance(this) ,check_number(this,25)\" ></TD>'+"); // commented by udara on 08-05-2012
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_RENTAL_OTHER_INV maxlength=\"23\"  style=\"{text-align:right;}\" size=\"10\" value=\"0\" onBlur=\"calculate_balance(this) ,check_number(this,25)\" disabled ></TD>'+"); // added by udara on 08-05-2012
			out.println("'<TD WIDTH=\"20%\"  align=\"left\">Total Amount entered</TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_TOT_ENTERED maxlength=\"23\"   style=\"{text-align:right;}\" size=\"10\" value=\"0\" onblur=\"check_number(this,25)\"  disabled></TD>'+");
			out.println("'</tr>'+");
  		//out.println("'<TD WIDTH=\"20%\"  align=\"left\">Other Invoices</TD>'+"); // commented by udara on 08-05-2012
			out.println("'<TD WIDTH=\"20%\"  align=\"left\">Insurance</TD>'+"); // added by udara on 08-05-2012
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\"  name=TXT_OTHER_INV maxlength=\"23\"style=\"{text-align:right;}\"  size=\"10\" value=\"0\" onBlur=\"calculate_balance(this),check_number(this,25)\" ></TD>'+");
			out.println("'<TD WIDTH=\"20%\"  align=\"left\">Balance Pending</TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\"  name=TXT_BALANCE_PENDING maxlength=\"23\"  style=\"{text-align:right;}\" size=\"10\" value=\"0\" onblur=\"check_number(this,25)\" disabled ></TD>'+");
			out.println("'</tr>'+");
			//out.println("'<TD WIDTH=\"20%\"  align=\"left\">Luxury Tax</TD>'+"); //modified by nwuan de silva  04-07-07
			//out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_LUX_TAX maxlength=\"23\" size=\"10\"  style=\"{text-align:right;}\" value=\"0\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\" ></TD>'+");
			
			
			
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"></TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input value=\"0\" class=\"txt_input\"  maxlength=\"50\" type=\"hidden\" name=TXT_INSURANCE_PREMIUM  ></TD>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");			
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"></TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input value=\"0\" class=\"txt_input\"  maxlength=\"50\" type=\"hidden\" name=TXT_LUX_TAX  ></TD>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");
			//out.println("'<TD WIDTH=\"20%\"  align=\"left\">Revenue License</TD>'+"); 
			//out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_REVENUE_LICENCY maxlength=\"23\"   style=\"{text-align:right;}\" size=\"10\" value=\"0\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\" ></TD>'+");
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"></TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input value=\"0\" class=\"txt_input\"  maxlength=\"50\" type=\"hidden\" name=TXT_REVENUE_LICENCY  ></TD>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");
			//out.println("'<TD WIDTH=\"20%\"  align=\"left\">RMV Registration Fees</TD>'+");
			//out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_RMV_REG_FEES maxlength=\"23\"  style=\"{text-align:right;}\" size=\"10\" value=\"0\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\" ></TD>'+");
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"></TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input value=\"0\" class=\"txt_input\"  maxlength=\"50\" type=\"hidden\" name=TXT_RMV_REG_FEES ></TD>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");
			out.println("'</table>';");
	//		out.println("   document.Form1.TXT_TOT_ENTERED.value=format_noobject(document.Form1.AMOUNT.value);");
			out.println("   document.Form1.TXT_BALANCE_PENDING.value=format_noobject(document.Form1.AMOUNT.value);");
		//	out.println("m_balance=document.Form1.AMOUNT.value");
		    out.println("   document.Form1.TXT_FIFO.value='FIFO_MANU'; ");	
			out.println("}");
		
		    // added by udara on 08-05-2012
		    out.println("else if(document.Form1.OTHER_CHARGES.value!=\"Y\" && document.Form1.TXT_FIFO.value==\"FIFO_MANU\" ){");
			out.println("header();");
			//modified by nwuan de silva  23-07-07
			out.println("m_table_other_charges.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr ID=T_ID class=tr_input>'+");		
			out.println("'<TD WIDTH=\"20%\"  align=\"left\">Rental Invoices</TD>'+");
			//out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_RENTAL_OTHER_INV maxlength=\"23\"  style=\"{text-align:right;}\" size=\"10\" value=\"0\" onBlur=\"calculate_balance(this) ,check_number(this,25)\" ></TD>'+"); // commented by udara on 08-05-2012
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_RENTAL_OTHER_INV maxlength=\"23\"  style=\"{text-align:right;}\" size=\"10\" value=\"0\" onBlur=\"calculate_balance(this) ,check_number(this,25)\"  ></TD>'+"); // added by udara on 08-05-2012
			out.println("'<TD WIDTH=\"20%\"  align=\"left\">Total Amount entered</TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_TOT_ENTERED maxlength=\"23\"   style=\"{text-align:right;}\" size=\"10\" value=\"0\" onblur=\"check_number(this,25)\"  disabled></TD>'+");
			out.println("'</tr>'+");
  		  //out.println("'<TD WIDTH=\"20%\"  align=\"left\">Other Invoices</TD>'+"); // commented by udara on 08-05-2012
			out.println("'<TD WIDTH=\"20%\"  align=\"left\">Insurance</TD>'+"); // added by udara on 08-05-2012
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\"  name=TXT_OTHER_INV maxlength=\"23\"style=\"{text-align:right;}\"  size=\"10\" value=\"0\" onBlur=\"calculate_balance(this),check_number(this,25)\" disabled ></TD>'+");
			out.println("'<TD WIDTH=\"20%\"  align=\"left\">Balance Pending</TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\"  name=TXT_BALANCE_PENDING maxlength=\"23\"  style=\"{text-align:right;}\" size=\"10\" value=\"0\" onblur=\"check_number(this,25)\" disabled ></TD>'+");
			out.println("'</tr>'+");
			//out.println("'<TD WIDTH=\"20%\"  align=\"left\">Luxury Tax</TD>'+"); //modified by nwuan de silva  04-07-07
			//out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_LUX_TAX maxlength=\"23\" size=\"10\"  style=\"{text-align:right;}\" value=\"0\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\" ></TD>'+");
			
			
			
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"></TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input value=\"0\" class=\"txt_input\"  maxlength=\"50\" type=\"hidden\" name=TXT_INSURANCE_PREMIUM  ></TD>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");			
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"></TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input value=\"0\" class=\"txt_input\"  maxlength=\"50\" type=\"hidden\" name=TXT_LUX_TAX  ></TD>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");
			//out.println("'<TD WIDTH=\"20%\"  align=\"left\">Revenue License</TD>'+"); 
			//out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_REVENUE_LICENCY maxlength=\"23\"   style=\"{text-align:right;}\" size=\"10\" value=\"0\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\" ></TD>'+");
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"></TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input value=\"0\" class=\"txt_input\"  maxlength=\"50\" type=\"hidden\" name=TXT_REVENUE_LICENCY  ></TD>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");
			//out.println("'<TD WIDTH=\"20%\"  align=\"left\">RMV Registration Fees</TD>'+");
			//out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_RMV_REG_FEES maxlength=\"23\"  style=\"{text-align:right;}\" size=\"10\" value=\"0\" onChange=\"calculate_balance(this)\" onblur=\"check_number(this,25)\" ></TD>'+");
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"></TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input value=\"0\" class=\"txt_input\"  maxlength=\"50\" type=\"hidden\" name=TXT_RMV_REG_FEES ></TD>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");
			out.println("'</table>';");
	        //		out.println("   document.Form1.TXT_TOT_ENTERED.value=format_noobject(document.Form1.AMOUNT.value);");
			out.println("   document.Form1.TXT_BALANCE_PENDING.value=format_noobject(document.Form1.AMOUNT.value);");
		    //	out.println("m_balance=document.Form1.AMOUNT.value");		
			out.println("}");		
		    // end by udara on 08-05-2012
		
		
		
		
		
		/*	out.println("else {");		
			out.println("if(document.Form1.AMOUNT.value!='' && isnumberok(document.Form1.AMOUNT,25)){");
		  out.println("if(document.Form1.EXCHANE_RATE.value!='' && isnumberok(document.Form1.EXCHANE_RATE,6)){");
      out.println("   makeRequest(m_url,'4','RepAmt');");
			out.println("}");				
			out.println("}");
			out.println("}");		
	*/		
			out.println("}");			
			
			//added by nuwan de silva 01-08-07----------------------------
			out.println("function set_address_pay_type(){");
			//out.println("if(document.Form1.hid_option.value==\"NEW\"){"); 
			out.println("if(document.Form1.PAY_TYPE.value!=\"CLIENT\"){");
			//out.println("pay_name.innerHTML=\"Client Name\";");				
			//out.println("pay_address.innerHTML=\"Client Address\";");				
			//out.println(" document.Form1.CLIENT_NAME_1.value =document.Form1.CLIENT_NAME.value;");
			
			out.println("  document.Form1.elements['CLIENT_NAME_1'].value  =document.Form1.CLIENT_NAME.value;");
			out.println("  document.Form1.elements['CLIENT_ADDRESS_1'].value  =document.Form1.CLIENT_ADDRESS.value;");
			//out.println(" document.Form1.CLIENT_ADDRESS_1.value =document.Form1.CLIENT_ADDRESS.value;");
			//out.println(" document.Form1.CLIENT_NAME_1.disabled=true;");
			//out.println(" document.Form1.CLIENT_ADDRESS_1.disabled=true;");
			out.println("}");				
		/*	out.println("else if(document.Form1.PAY_TYPE.value==\"THIRD\" && document.Form1.hid_option.value==\"NEW\"){");
			out.println("pay_name.innerHTML=\"Third Party Name\";");				
			out.println("pay_address.innerHTML=\"Third Party Address\";");				
			out.println(" document.Form1.CLIENT_NAME_1.value =document.Form1.CLIENT_NAME.value;");
			out.println(" document.Form1.CLIENT_ADDRESS_1.value =document.Form1.CLIENT_ADDRESS.value;");
			out.println(" document.Form1.CLIENT_NAME_1.disabled=false;");
			out.println(" document.Form1.CLIENT_ADDRESS_1.disabled=false;"); */
			//out.println("}");				
			//out.println("}");			
			out.println(" display_client_comment(document.Form1.CLIENT_CODE.value);"); //added by nuwan de silva 0=04-08-2008.............
			out.println("}");				
			//-------------------------------------------------------------
			out.println("function enable_check_date(){");
			out.println("m_table_account_no.innerHTML=\"\" ");
			out.println("m_table_tendered.innerHTML=\"\" ");
			out.println("m_table_cheque.innerHTML=\"\" ");
			//out.println("  PBRANCH.style.color='black';"); //added by nuwan de silva 02-08-07
			out.println("if(document.Form1.SETT_MODE.value==\"STD_ORD\" || document.Form1.SETT_MODE.value==\"DIR_DEP\" ){");
			out.println("m_table_account_no.innerHTML+='<table align=\"left\" width=\"100%\" class=\"table\" border=\"0\"><tr ID=T_ID >'+");		//class=tr_input
			out.println("'<TD WIDTH=\"20%\"  ID=ACNO align=\"left\">Account Number *</TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ACCOUNT_NO maxlength=\"10\" size=\"10\" onblur=\"makeRequest_account_no(this)\" >'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_ACCOUNT_NO  value=\"Help\" onClick=\"account_number_help()\"></TD>'+");
			out.println("'<td >Branch Name</td>'+");
			out.println("'<td ><input name=\"BRANCH_NAME\" type=\"text\" style=\"width:340px;\" maxlength=\"200\" class=\"txt_input\" disabled></td>'+");
			out.println("'</tr></table>';");
			/*out.println("m_table_account_no.innerHTML+='<tr ID=T_ID>'+");		
			out.println("'<TD >Account Number</TD>'+");
			out.println("'<TD ><input class=\"txt_input\" type=\"text\" name=TXT_ACCOUNT_NO maxlength=\"10\" size=\"10\" onblur=\"\" >'+");
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_ACCOUNT_NO  value=\"Help\" onClick=\"help_account_no()\"></TD>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td>'+");
			out.println("'</tr>';");
			*/	
			out.println("}");				
			out.println("else if(document.Form1.SETT_MODE.value==\"CASH\"){");
			out.println("m_table_tendered.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr ID=T_ID class=tr_input>'+");		
			out.println("'<TD WIDTH=\"20%\"  align=\"left\">Tendered Amount</TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TEN_AMOUNT style=\"{text-align:right;}\"  maxlength=\"23\"  size=\"25\" onchange =\"calculate_balance(this)\" onblur=\"get_returned_value(this,25)\" ></TD>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");
			out.println("'<TD WIDTH=\"20%\"  align=\"left\">Returned Amount</TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=RET_AMOUNT style=\"{text-align:right;}\" maxlength=\"23\"   size=\"10\" onblur=\"cal_retamt(this)\" disabled ></TD>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");
			out.println("'</table>';");
			out.println("}");	
			
			//out.println("else if(document.Form1.SETT_MODE.value!=\"CASH\"){");
			//out.println("document.Form1.CHEQUE_DATE_DD.disabled=false;");
			//out.println("document.Form1.CHEQUE_DATE_MM.disabled=false;");
			//out.println("document.Form1.CHEQUE_DATE_YY.disabled=false;");
			//out.println("add_row_cheque();");				 //added by nuwan de silva 01-08-07
			//out.println("}");				
			//out.println("else");
			//out.println("{");				
			//out.println("document.Form1.CHEQUE_DATE_DD.value='';");
			//out.println("document.Form1.CHEQUE_DATE_MM.value='';");
			//out.println("document.Form1.CHEQUE_DATE_YY.value='';");
			//out.println("document.Form1.CHEQUE_DATE_DD.disabled=true;");
			//out.println("document.Form1.CHEQUE_DATE_MM.disabled=true;");
			//out.println("document.Form1.CHEQUE_DATE_YY.disabled=true;");
			//out.println("}");			
			
			out.println("enable_branch();");
			out.println("add_row_cheque();");				 //added by nuwan de silva 01-08-07
			
			out.println("disable_val_date()");
      //Added By Ns on 25-02-2011
			out.println("     validate_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR,document.Form1.HID_SYS_VAL_DAY,document.Form1.HID_SYS_VAL_MONTH,document.Form1.HID_SYS_VAL_YEAR);");
			out.println("}");	
			

      out.println("function disable_val_date(){");
      out.println("if((document.Form1.SETT_MODE.value==\"CASH\")||(document.Form1.SETT_MODE.value==\"CHEQUE\")){"); //Added by Chandana on 06/09/2007 
			out.println("document.Form1.VAL_DAY.disabled=false;");
			out.println("document.Form1.VAL_MONTH.disabled=false;");
			out.println("document.Form1.VAL_YEAR.disabled=false;");
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
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=CHEQUE_NO maxlength=\"8\"  size=\"22\" onblur=validate_cheque_no() value=\"\" ></TD>'+");
			out.println("'<td></td><td></td></tr>'+");
			out.println("'<tr class=tr_input><TD WIDTH=\"20%\" ID=CDATE align=\"left\">Cheque Date *</TD>'+");
			out.println("'<td width=\"30%\"><input name=CHEQUE_DATE_DD   type=\"text\" maxlength=\"2\" class=\"txt_input\" style=\"width:25px\" value=\"\" >'+ ");
			out.println("'<input name=CHEQUE_DATE_MM   type=\"text\" maxlength=\"2\" class=\"txt_input\" style=\"width:25px\"  value=\"\">'+ ");
			out.println("'<input name=CHEQUE_DATE_YY   type=\"text\" maxlength=\"4\" class=\"txt_input\" style=\"width:45px\"   value=\"\" onBlur=\"check_date_value()\" >'+ "); //<a href style=\"{cursor:hand; }\" onclick=load_calendar(\"3\")>Calendar</a>
			out.println("'<a href style=\"{cursor:hand; }\" onclick=load_calendar(\"3\")>   Calendar</a></td>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");
			
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
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=CHEQUE_NO maxlength=\"8\"  size=\"22\" onblur=validate_cheque_no() value=\"'+data_vec[12]+'\" disabled></TD>'+");
			out.println("'<td></td><td></td></tr>'+");
			out.println("'<tr ><TD WIDTH=\"20%\" ID=CDATE align=\"left\">Cheque Date *</TD>'+");
			out.println("'<td width=\"30%\"><input name=CHEQUE_DATE_DD   type=\"text\" maxlength=\"2\" class=\"txt_input\" style=\"width:25px\" value=\"'+data_vec[14]+'\" disabled>'+ ");
			out.println("'<input name=CHEQUE_DATE_MM   type=\"text\" maxlength=\"2\" class=\"txt_input\" style=\"width:25px\"  value=\"'+data_vec[15]+'\" disabled>'+ ");
			out.println("'<input name=CHEQUE_DATE_YY   type=\"text\" maxlength=\"4\" class=\"txt_input\" style=\"width:45px\"   value=\"'+data_vec[16]+'\" onBlur=\"check_date_value()\" disabled>'+ "); //<a href style=\"{cursor:hand; }\" onclick=load_calendar(\"3\")>Calendar</a>
			out.println("'<a href style=\"{cursor:hand; }\" onclick=load_calendar(\"3\")>   Calendar</a></td>'+");
			out.println("'<td></td>'+");
			out.println("'<td></td></tr>'+");
			
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
			out.println("'<td width=\"40%\" > <input name=\"PAY_BRANCH_NAME\"   type=\"text\" maxlength=\"100\"  onblur=\"\" class=\"txt_input\" style=\"width: 200px\" ></td>'+ ");
			out.println("'<td ></td>'+");
			out.println("'<td ></td>'+");
			out.println("'</tr>'+");
			out.println("'</table>';");
			out.println("assign_sysdate();"); 
			out.println("}");
			
			out.println("}");

			
			out.println("function assign_sysdate(){");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_sysdate\";");
      //out.println("   window.open(m_url);");
			out.println("   makeRequest(m_url,'4','ChequeDate');");
			
			out.println("}");

			
			out.println("function enable_branch(){");
			
			out.println("if(document.Form1.SETT_MODE.value==\"CHEQUE\" || document.Form1.SETT_MODE.value==\"STD_ORD\" ){");
						
		//	out.println("document.Form1.PAY_ACCOUNT.disabled=false;");
		//	out.println("document.Form1.PAY_BRANCH.disabled=false;");
		//	out.println("document.Form1.accno_help.disabled=false;");
			
			
			out.println("}");				
			out.println("else");
			out.println("{");				
			/*
			out.println("document.Form1.PAY_ACCOUNT.value='';");
			out.println("document.Form1.PAY_BRANCH.value='';");  
			out.println("document.Form1.PAY_BRANCH_NAME.value='';");
			
			out.println("document.Form1.PAY_ACCOUNT.disabled=true;");
			out.println("document.Form1.PAY_BRANCH.disabled=true;");
			out.println("document.Form1.accno_help.disabled=true;"); 
		  out.println("document.Form1.PAY_BRANCH_NAME.disabled=true;");
			*/
			out.println("}");				
			
			out.println("}");				
			
			out.println("function check_date_value(){");
			
			out.println("if(document.Form1.CHEQUE_DATE_DD.value!='' && document.Form1.CHEQUE_DATE_MM.value!='' && document.Form1.CHEQUE_DATE_YY.value!='')");
			out.println("checkMonthLength(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)");
			
			out.println("}");				
			
				out.println("function check_status_inv(num2,num1) {");
				//out.println("alert(document.Form1.elements['Text_standard'+num2].checked);");
				out.println("if(document.Form1.elements['Text_standard'+num2].checked){");
				out.println("validate_check_status_inv(num2);"); //added by the nuwan de silva on 28-02-2009
				//out.println("  cal_amount(num2,num1);"); //Comment By Nuwan De Silva
				//out.println(" alert('9999');");
				out.println("if(b_flag_inv==0){");
				out.println("  document.Form1.elements['Text_standard'+num2].value       =\"YES\";");
				out.println("  document.Form1.elements['Text_sett_amount'+num2].disabled =true;"); // added by nuwan de silva on 12-08-2008
				
				out.println("}");
				out.println("else{");
				out.println("alert('Please select the invoces by order');");
				out.println("  document.Form1.elements['Text_standard'+num2].checked      =false;");
				out.println("  document.Form1.elements['Text_standard'+num2].value        =\"NO\";");
				out.println("  document.Form1.elements['Text_sett_amount'+num2].value     =0;");
				out.println("  document.Form1.elements['Text_sett_amount'+num2].disabled  =false;"); // added by nuwan de silva on 12-08-2008
				out.println("}");
				//out.println("  cal_amount_update(num2,num1);"); //Added By Nuwan De Silva
				//out.println(" document.Form1.elements['Text_standard'+num].value=\"YES\";");
				out.println("}else{");
				out.println("  document.Form1.elements['Text_sett_amount'+num2].value    =0;");
				out.println("  document.Form1.elements['Text_sett_amount'+num2].disabled =false;");
				out.println("  document.Form1.elements['Text_standard'+num2].value       =\"NO\";");
				out.println("  unallocate_invoices(num2);"); //added by the nuwan de silva on 28-02-2009
				out.println("}");
				out.println("}");
				
				//added by the nuwan de silva on 28-02-2009
				out.println("function validate_check_status_inv(num2) {");
				out.println("b_flag_inv=0;");
				out.println("var m_count_inv=num2;");
				out.println("if(parseInt(m_count_inv)!=0) {");
				out.println("for(var m_inv=0;m_inv<parseInt(m_count_inv);m_inv++){");
				out.println("if(!document.Form1.elements['Text_standard'+m_inv].checked){");
				out.println("b_flag_inv=1;");
				out.println("break;");
				out.println("}");
				out.println("}");
				out.println("}");
				out.println("}");
				//added by the nuwan de silva on 28-02-2009
				out.println("function unallocate_invoices(num2) {");
				out.println("var m_count_inv=document.Form1.hid_invoice_count.value;");
				out.println("if(parseInt(m_count_inv)!=0) {");
				out.println("for(var m_inv=num2;m_inv<parseInt(m_count_inv);m_inv++){");
				out.println("if(document.Form1.elements['Text_standard'+m_inv].checked){");
				out.println("  document.Form1.elements['Text_sett_amount'+m_inv].value    =0;");
				out.println("  document.Form1.elements['Text_sett_amount'+m_inv].disabled =false;");
				out.println("  document.Form1.elements['Text_standard'+m_inv].checked      =false;");
				out.println("  document.Form1.elements['Text_standard'+m_inv].value       =\"NO\";");
			  out.println("}");
				out.println("}");
				out.println("}");
				out.println("}");
				
				
				
				out.println("function cal_amount(num2,num1) {");//
				out.println("  document.Form1.hid_win_opt.value=num2;");
				out.println("  document.Form1.hid_opt_val.value=num1;");
				out.println("  m_inv_bal  = 0;");
				out.println("  m_inv_allo = 0;");
				out.println("  m_rec_allo = 0;");
				
				out.println("  m_rec_bal  = parseFloat(unformat_noobject(document.Form1.elements['AMOUNT'].value));");
				//out.println("  alert(document.Form1.elements['INV_NO_'+num2].value);"); //
				
				out.println("    for(j=0;j<parseFloat(document.Form1.elements['hid_invoice_count'].value);j++){");	
				out.println("        m_rec_allo = parseFloat(m_rec_allo)+parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+j].value)) ");	
				//out.println("        m_inv_bal  = parseFloat(document.Form1.elements['BAL_AM_'+num1+'_'+j].value); ");	
				out.println("    }");	
				
				//out.println("  alert('m_inv_allo='+m_inv_allo+'---m_inv_bal='+m_inv_bal+'---m_rec_bal='+m_rec_bal+'---m_rec_allo='+m_rec_allo)");
        out.println("   if(parseFloat(m_rec_bal)<parseFloat(m_rec_allo)){"); 
				out.println("      document.Form1.elements['Text_standard'+num2].value      =\"NO\";");
				out.println("      document.Form1.elements['Text_sett_amount'+num2].disabled=false;");
				out.println("      document.Form1.elements['Text_sett_amount'+num2].value   =0;");
				out.println("      document.Form1.elements['Text_standard'+num2].checked    =false;");
				
				out.println("     }else{");	
				out.println("      document.Form1.elements['Text_standard'+num2].value      =\"YES\";");
				out.println("      document.Form1.elements['Text_sett_amount'+num2].disabled=true;");
				//out.println("       m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_ReceiptAllocation?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
        //out.println("     window.open(m_url);");
				//out.println("       makeRequest(m_url,'3');");
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
        out.println("      calcualte_values(num2,num1);");	//Added By Nuwan De Silva
				
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
				
				//out.println("    else {");	
				//out.println(" document.Form1.elements['Text_sett_amount'+num2].value =m_rec_balance_new;");
				//out.println("    }");	
				
				out.println("else if(document.Form1.elements['Text_sett_amount'+num2].value==0 ){"); //&& document.Form1.elements['Text_sett_amount'+num2].value>0
			  out.println(" document.Form1.elements['Text_sett_amount'+num2].value =m_rec_balance_new;");
				out.println("format_number(document.Form1.elements['Text_sett_amount'+num2],23)");
				out.println("    }");	
				out.println("    }");	
				out.println("}");	

				
			out.println("function get_returned_value(obj,size) {");
			
			
			out.println("if(obj.value!=\"\" ){");//document.Form1.AMOUNT.value!=\"\" &&
			
			out.println("if(isnumberok(obj,size)){");
			//out.println("if(parseFloat(obj.value)<=parseFloat(unformat_noobject(document.Form1.AMOUNT.value))){ ");			
			out.println("if(parseFloat(unformat_noobject(obj.value))>=parseFloat(unformat_noobject(document.Form1.AMOUNT.value))){ ");			 //modified by nuwan de silva 06-07-07
			out.println(" document.Form1.hid_help_status.value='H_tendered' ");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_settlement_tendered_amount&data_val1=\"+unformat_noobject(document.Form1.AMOUNT.value)+\"&data_val2=\"+unformat_noobject(document.Form1.TEN_AMOUNT.value);");
			out.println("	 load_interface(m_url,'XML');");
			out.println("format_number(obj,size)");
			out.println("}");			
			
			out.println("else");
			out.println("{");
			//out.println("alert('Tendered amount can not be greater than amount');"); //modified by nuwan de silva 06-07-07
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
			
			/*out.println("function cal_tenamt(val) {");
			out.println("if(document.Form1.TEN_AMOUNT.value!=\"\"){ ");
			out.println("format_number(document.Form1.TEN_AMOUNT,29)");
			out.println("if(format_number2(document.Form1.TEN_AMOUNT,29)){");
			out.println("val=unformat_noobject(document.Form1.TEN_AMOUNT.value)");
			out.println("}");
			out.println("}");
			out.println("}");
      */
			
			out.println("function cal_retamt(val) {");
			out.println("if(document.Form1.RET_AMOUNT.value!=\"\"){ ");
			out.println("format_number(document.Form1.RET_AMOUNT,29)");
			out.println("if(format_number2(document.Form1.RET_AMOUNT,29)){");
			out.println("val=unformat_noobject(document.Form1.RET_AMOUNT.value)");
			out.println("}");
			out.println("}");
			out.println("}");
			
			//added by nuwan de silva 02-08-07----------------------------------------------------------------------
			out.println("function validate_cheque_no() {");
			out.println("if(document.Form1.SETT_MODE.value!='CASH' ){");
			out.println("if(document.Form1.CHEQUE_NO.value!='' && document.Form1.PAY_BRANCH.value!='' ){");
			out.println("document.Form1.hid_help_status.value='VAL_CHEQUE'");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_SETT_RECEIPT_CHEQUE_VALIDATE&data_val='+document.Form1.CHEQUE_NO.value+'&branch_code='+document.Form1.PAY_BRANCH.value+'';");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);");
			out.println("}");
			out.println("}");
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
			
			//added by Chandana no 30/08/2007 ------------------// 
			
			
			out.println("function display_row_third_party(){");
			out.println("m_table_third_party_del.innerHTML=\"\" ");
			
			out.println("if(document.Form1.PAY_TYPE.value==\"THIRD\"){"); 
			out.println("m_table_third_party_del.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr class=tr_input>'+");
			out.println("'<td width=\"20%\" id=pay_name>Third Party Name</td>'+");
			out.println("'<td width=\"30%\"><input name=\"CLIENT_NAME_1\" type=\"text\" style=\"width:250px;\" maxlength=\"200\" class=\"txt_input\" ></td>'+");
			out.println("'<td id=pay_address>Third Party Address</td>'+");
			out.println("'<td> <input name=\"CLIENT_ADDRESS_1\" type=\"text\" style=\"width:350px;\" maxlength=\"200\" class=\"txt_input\" >'+");
			out.println("'</td>'+");
			out.println("'</tr>'+");	
			out.println("'</table>';");
			out.println("}");
			out.println("}");
			
			
			out.println("function check_alocate_amt(obj1,obj2,obj3,obj4){ ");	
			//out.println("alert('Alocated amount too large'+obj1.value+'==,'+obj3+'=====>'+obj4);");
      //out.println("alert('Alocated'+obj2);");
			
			//out.println("if(unformat_noobject(document.Form1.elements['Text_odi_sett_amount'+obj4].value)>unformat_noobject(obj2)){");
			//out.println("alert('Alocated amount too large');");
			
			out.println("obj44=((parseFloat(obj4))-1);");
			
			
			out.println("M_TOT = parseFloat(unformat_noobject(document.Form1.elements['ODI_VAL_'+obj4].value) + unformat_noobject(document.Form1.elements['INV_VAL_'+obj4].value));");
			out.println("M_BAL = parseFloat(unformat_noobject(document.Form1.elements['Text_balance_amount'+obj4].value));");  
			
			out.println("M_ODI = parseFloat(unformat_noobject(document.Form1.elements['Text_odi_sett_amount'+obj4].value));");
			out.println("M_INV = parseFloat(unformat_noobject(document.Form1.elements['Text_inv_sett_amount'+obj4].value));");
			
			out.println("M_ODI_BAL = parseFloat(unformat_noobject(document.Form1.elements['ODI_VAL_'+obj4].value));");
			out.println("M_INV_BAL = parseFloat(unformat_noobject(document.Form1.elements['INV_VAL_'+obj4].value));");
			
			
			out.println("if(document.Form1.OTHER_CHARGES.value == \"Y\"){");
			out.println("M_AMT = parseFloat(unformat_noobject(document.Form1.TXT_RENTAL_OTHER_INV.value));");			
			out.println("}else{");
			out.println("M_AMT = parseFloat(unformat_noobject(document.Form1.AMOUNT.value));");
			out.println("}");
			
			
			
			//out.println("alert('=='+obj4+'==='+M_TOT+'===='+M_BAL+'==='+(M_ODI+M_INV));");
			
			
			out.println("if(obj4==0){");
			
			//out.println("alert('M_ODI_BAL======='+M_ODI_BAL+'M_INV_BAL======'+M_INV_BAL);");
			
			out.println("if((M_TOT < (M_ODI+M_INV))||(M_AMT < (M_ODI+M_INV))){");
			//out.println("alert('Alocated amount too large');");
			out.println("alert('Alocated amount exceeds invoice balance amount');");
						
			out.println("if(obj3==\"ODI\"){");
			
			out.println("if(M_AMT<M_ODI_BAL){");
			out.println("document.Form1.elements['Text_odi_sett_amount'+obj4].value=M_AMT;");
			out.println("}else {");
			out.println("document.Form1.elements['Text_odi_sett_amount'+obj4].value=M_ODI_BAL;");
			out.println("}"); 
			
			out.println("} else if(obj3==\"INV\"){"); 
      
			out.println("if(M_AMT<M_INV_BAL){");
			out.println("document.Form1.elements['Text_inv_sett_amount'+obj4].value=M_AMT;");
			out.println("}else {");
			out.println("document.Form1.elements['Text_inv_sett_amount'+obj4].value=M_INV_BAL;");
			out.println("}"); 
			
			out.println("}"); 
			out.println("}");
			 
			out.println("}else{");
			
			out.println("M_BAL_PRE = parseFloat(unformat_noobject(document.Form1.elements['Text_balance_amount'+obj44].value));");
			
			out.println("if((M_TOT < (M_ODI+M_INV))||(M_BAL_PRE < (M_ODI+M_INV))){");
			//out.println("alert('Alocated amount too large');");
			out.println("alert('Alocated amount exceeds invoice balance amount');");

      
			
			out.println("if(obj3==\"ODI\"){");
			
			out.println("if(M_BAL_PRE<M_ODI_BAL){");
			out.println("document.Form1.elements['Text_odi_sett_amount'+obj4].value=M_BAL_PRE;");
			out.println("}else {");
			out.println("document.Form1.elements['Text_odi_sett_amount'+obj4].value=M_ODI_BAL;");
			out.println("}"); 
			
			out.println("} else if(obj3==\"INV\"){"); 
      			
			out.println("if(M_BAL_PRE<M_INV_BAL){");
		  out.println("document.Form1.elements['Text_inv_sett_amount'+obj4].value=M_BAL_PRE;");
			out.println("}else {");
			out.println("document.Form1.elements['Text_inv_sett_amount'+obj4].value=M_INV_BAL;");
			out.println("}"); 
			
			out.println("}"); 
						
      out.println("}");
			
				
			out.println("}");	
			
			
			

			out.println(" num_of_contract=parseFloat(unformat_noobject(document.Form1.hid_cntract_cnt.value));");
			
			out.println("if(document.Form1.OTHER_CHARGES.value == \"Y\"){");
			out.println("amount = parseFloat(unformat_noobject(document.Form1.TXT_RENTAL_OTHER_INV.value));");			
			out.println("}else{");
			out.println("amount = parseFloat(unformat_noobject(document.Form1.AMOUNT.value));");
			out.println("}");
			
			//out.println(" amount=parseFloat(unformat_noobject(document.Form1.AMOUNT.value));");
						
			out.println("var tot=0");
			
			out.println("for(j=0; j<num_of_contract; j++){"); 
			
			
			//out.println("alert('Alocated amount=='+j+'===='+parseFloat(unformat_noobject(document.Form1.elements['Text_balance_amount'+j].value)));");
			
			//out.println("alert('Text_odi_sett_amount=='+j+'===='+parseFloat(unformat_noobject(document.Form1.elements['Text_odi_sett_amount'+j].value)));");
      //out.println("alert('Text_inv_sett_amount=='+j+'===='+parseFloat(unformat_noobject(document.Form1.elements['Text_inv_sett_amount'+j].value)));");
      
			out.println("odi_sett_amount=parseFloat(unformat_noobject(document.Form1.elements['Text_odi_sett_amount'+j].value));");
      out.println("inv_sett_amount=parseFloat(unformat_noobject(document.Form1.elements['Text_inv_sett_amount'+j].value));");

			
			out.println(" balance = (amount - (odi_sett_amount + inv_sett_amount) - tot);");
						
			out.println("if(balance>=0){");
			out.println(" document.Form1.elements['Text_balance_amount'+j].value = balance;");
			//out.println("alert('====='+j+'==='+document.Form1.elements['Text_standard_chk'+j].value);");
			out.println(" document.Form1.elements['Text_standard_chk'+j].checked=true;");   
			out.println(" document.Form1.elements['Text_standard_chk'+j].value=\"YES\";");
			
			out.println(" tot = tot + (odi_sett_amount + inv_sett_amount);");
			out.println("}");
			
			out.println("}");
			
			
			
			out.println("}");	
			
			
			
			out.println("function aloc_other1_inv(obj1){ ");   
			
			//out.println("alert('Start value');");
			
			out.println("if(obj1==0){");
			//out.println("alert('Start value'+(parseFloat(obj1)));");
			out.println("val1=(parseFloat(obj1));");
			
			out.println("start=parseFloat(obj1);");
			out.println("start2=parseFloat(obj1);");
			out.println("start3=parseFloat(obj1);");
						
			out.println("}else{");
			//out.println("alert('Start value'+((parseFloat(obj1))-1));");
			out.println("val1=((parseFloat(obj1))-1);");
			out.println("start=parseFloat(document.Form1.elements['hid_other_invoice_tot_cnt_'+val1].value);");
			out.println("start2=parseFloat(document.Form1.elements['hid_other_invoice_tot_cnt_'+val1].value);");
			
			out.println("}");			

			out.println("end=parseFloat(document.Form1.elements['hid_other_invoice_tot_cnt_'+obj1].value);");
			
	    
			out.println("tot_odi=parseFloat(unformat_noobject(document.Form1.elements['other_Text_odi_sett_amount'+obj1].value));"); 
			out.println("tot_inv=parseFloat(unformat_noobject(document.Form1.elements['other_Text_inv_sett_amount'+obj1].value));"); 
			out.println("total=tot_odi+tot_inv;");
			
			out.println("var total_sett_amount=0;");
						
			out.println("    for(j=0;start<end;start++){");
			out.println("document.Form1.elements['Text_sett_amount'+start].value=0.00;");
			out.println("document.Form1.elements['Text_standard'+start].checked=false;");
			out.println("document.Form1.elements['Text_standard'+start].value=\"NO\";");
			out.println("}");
			
			out.println("    for(j=0;start2<end;start2++){");
			out.println("sett_amount=parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+start2].value));");
			out.println("bal_amount=parseFloat(unformat_noobject(document.Form1.elements['BAL_AM_'+start2].value));"); 
			
			//out.println("alert('===bal_amount==='+bal_amount+'=====sett_amount===='+sett_amount);");
			
			out.println(" if(bal_amount<=total - total_sett_amount){"); 
			
			
			out.println("document.Form1.elements['Text_sett_amount'+start2].value=format_noobject(bal_amount);");
			
			//out.println("alert('111 total test==='+total+'total_sett_amount'+total_sett_amount);");
			//out.println("document.Form1.elements['Text_bal_amount'+start2].value=(total - total_sett_amount);");
			
			out.println("document.Form1.elements['Text_standard'+start2].checked=true;");
			out.println("document.Form1.elements['Text_standard'+start2].value=\"YES\";");
			out.println("total_sett_amount=total_sett_amount + bal_amount;");
			//out.println("alert('111');");
			out.println("}else {");
			out.println("if((total - total_sett_amount)>0){");			
			out.println("document.Form1.elements['Text_sett_amount'+start2].value=format_noobject(total - total_sett_amount);");
			out.println("document.Form1.elements['Text_standard'+start2].checked=true;");
			out.println("document.Form1.elements['Text_standard'+start2].value=\"YES\";");
						
			out.println("total_sett_amount=total;");
   		//out.println("alert('2222');");
			out.println("}else{");
			
			//out.println("document.Form1.elements['Text_bal_amount'+start2].value=0.00;");
			
      out.println("document.Form1.elements['Text_sett_amount'+start2].value=format_noobject(0.00);");
			out.println("document.Form1.elements['Text_standard'+start2].checked=false;");
			out.println("document.Form1.elements['Text_standard'+start2].value=\"NO\";");
			
			out.println("total_sett_amount=total;");
   		//out.println("alert('3333');");			
			out.println("}");
			out.println("}");
			out.println("}");
										
			out.println("}");
			
			
			
			
			
	////////////////////////////////////////////////////////////////////////////
	
      out.println("function aloc_inv(obj1){ ");   
			
			//out.println("alert('test');");
			
			out.println("if(obj1==0){");
			//out.println("alert('Start value'+(parseFloat(obj1)));");
			out.println("val1=(parseFloat(obj1));");
			
			out.println("start=parseFloat(obj1);");
			out.println("start2=parseFloat(obj1);");
			out.println("start3=parseFloat(obj1);");
						
			out.println("}else{");
			//out.println("alert('Start value'+((parseFloat(obj1))-1));");
			out.println("val1=((parseFloat(obj1))-1);");
			out.println("start=parseFloat(document.Form1.elements['hid_invoice_tot_cnt_'+val1].value);");
			out.println("start2=parseFloat(document.Form1.elements['hid_invoice_tot_cnt_'+val1].value);");
			
			out.println("}");			

			out.println("end=parseFloat(document.Form1.elements['hid_invoice_tot_cnt_'+obj1].value);");
			
	    
			out.println("tot_odi=parseFloat(unformat_noobject(document.Form1.elements['Text_odi_sett_amount'+obj1].value));"); 
			out.println("tot_inv=parseFloat(unformat_noobject(document.Form1.elements['Text_inv_sett_amount'+obj1].value));"); 
			//out.println("total=tot_odi+tot_inv;");
			
			out.println("total=tot_inv;");
			
			out.println("var total_sett_amount=0;");
						
			out.println("    for(j=0;start<end;start++){");
			
			out.println(" if(document.Form1.elements['hid_othr_invoice_no_'+start].value =='INV_GENER'){");
			out.println("document.Form1.elements['Text_sett_amount'+start].value=0.00;");
			out.println("document.Form1.elements['Text_standard'+start].checked=false;");
			out.println("document.Form1.elements['Text_standard'+start].value=\"NO\";");
			out.println("}");
			out.println("}");
			
			out.println("    for(j=0;start2<end;start2++){");
			out.println(" if(document.Form1.elements['hid_othr_invoice_no_'+start2].value =='INV_GENER'){");
			out.println("sett_amount=parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+start2].value));");
			out.println("bal_amount=parseFloat(unformat_noobject(document.Form1.elements['BAL_AM_'+start2].value));");
			
			//out.println("alert('===bal_amount==='+parseFloat(bal_amount)+'=====sett_amount===='+parseFloat(sett_amount));");
			//out.println("alert('111 total test==='+total+'total_sett_amount'+total_sett_amount);");

			out.println(" if(bal_amount<=total - total_sett_amount){"); 
			
			
			out.println("document.Form1.elements['Text_sett_amount'+start2].value=format_noobject(bal_amount);");
			
			//out.println("alert('test 111  bal_amount=='+bal_amount+' total ==='+total+'total_sett_amount'+total_sett_amount);");
			//out.println("document.Form1.elements['Text_bal_amount'+start2].value=(total - total_sett_amount);");
			
			out.println("document.Form1.elements['Text_standard'+start2].checked=true;");
			out.println("document.Form1.elements['Text_standard'+start2].value=\"YES\";");
			out.println("total_sett_amount=parseFloat(total_sett_amount) + parseFloat(bal_amount);");
			//out.println("alert('111');");
			out.println("}else {");
			out.println("if((total - total_sett_amount)>0){");			
			//out.println("alert('test 111  bal_amount=='+bal_amount+' total ==='+total+'total_sett_amount'+total_sett_amount);");
			//out.println("alert('test----'+(parseFloat(Math.round((total - total_sett_amount)*100)/100)))");
			
			out.println("document.Form1.elements['Text_sett_amount'+start2].value=format_noobject(parseFloat(Math.round((total - total_sett_amount)*100)/100));");
			//out.println("document.Form1.elements['Text_sett_amount'+start2].value=format_noobject(parseFloat(total) - parseFloat(total_sett_amount));");
			out.println("document.Form1.elements['Text_standard'+start2].checked=true;");
			out.println("document.Form1.elements['Text_standard'+start2].value=\"YES\";");
						
			out.println("total_sett_amount=parseFloat(total);");
			
   		//out.println("alert('2222');");
			out.println("}else{");
			
			//out.println("document.Form1.elements['Text_bal_amount'+start2].value=0.00;");
			
      out.println("document.Form1.elements['Text_sett_amount'+start2].value=format_noobject(0.00);");
			out.println("document.Form1.elements['Text_standard'+start2].checked=false;");
			out.println("document.Form1.elements['Text_standard'+start2].value=\"NO\";");
			
			out.println("total_sett_amount=parseFloat(total);");
   		//out.println("alert('3333');");			
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("aloc_inv_odi(obj1);");							
										
			out.println("}");
			
			
			out.println("function aloc_inv_odi(obj1){ ");   
						
			out.println("if(obj1==0){");
			//out.println("alert('Start value'+(parseFloat(obj1)));");
			out.println("val1=(parseFloat(obj1));");
			
			out.println("start=parseFloat(obj1);");
			out.println("start2=parseFloat(obj1);");
			out.println("start3=parseFloat(obj1);");
						
			out.println("}else{");
			//out.println("alert('Start value'+((parseFloat(obj1))-1));");
			out.println("val1=((parseFloat(obj1))-1);");
			out.println("start=parseFloat(document.Form1.elements['hid_invoice_tot_cnt_'+val1].value);");
			out.println("start2=parseFloat(document.Form1.elements['hid_invoice_tot_cnt_'+val1].value);");
			
			out.println("}");			

			out.println("end=parseFloat(document.Form1.elements['hid_invoice_tot_cnt_'+obj1].value);");
			
	    
			out.println("tot_odi=parseFloat(unformat_noobject(document.Form1.elements['Text_odi_sett_amount'+obj1].value));"); 
			out.println("tot_inv=parseFloat(unformat_noobject(document.Form1.elements['Text_inv_sett_amount'+obj1].value));"); 
			out.println("total=tot_odi;");
			
			out.println("var total_sett_amount=0;");
						
			out.println("    for(j=0;start<end;start++){");
			
			out.println(" if(document.Form1.elements['hid_othr_invoice_no_'+start].value =='ODI'){");
			out.println("document.Form1.elements['Text_sett_amount'+start].value=0.00;");
			out.println("document.Form1.elements['Text_standard'+start].checked=false;");
			out.println("document.Form1.elements['Text_standard'+start].value=\"NO\";");
			out.println("}");
			out.println("}");
			
			out.println("    for(j=0;start2<end;start2++){");
			out.println(" if(document.Form1.elements['hid_othr_invoice_no_'+start2].value =='ODI'){");
			out.println("sett_amount=parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+start2].value));");
			out.println("bal_amount=parseFloat(unformat_noobject(document.Form1.elements['BAL_AM_'+start2].value));");
			
			//out.println("alert('===bal_amount==='+parseFloat(bal_amount)+'=====sett_amount===='+parseFloat(sett_amount));");
			//out.println("alert('111 total test==='+total+'total_sett_amount'+total_sett_amount);");

			out.println(" if(bal_amount<=total - total_sett_amount){"); 
			
			
			out.println("document.Form1.elements['Text_sett_amount'+start2].value=format_noobject(bal_amount);");
			
			//out.println("alert('test 111  bal_amount=='+bal_amount+' total ==='+total+'total_sett_amount'+total_sett_amount);");
			//out.println("document.Form1.elements['Text_bal_amount'+start2].value=(total - total_sett_amount);");
			
			out.println("document.Form1.elements['Text_standard'+start2].checked=true;");
			out.println("document.Form1.elements['Text_standard'+start2].value=\"YES\";");
			out.println("total_sett_amount=parseFloat(total_sett_amount) + parseFloat(bal_amount);");
			//out.println("alert('111');");
			out.println("}else {");
			out.println("if((total - total_sett_amount)>0){");			
			//out.println("alert('test 111  bal_amount=='+bal_amount+' total ==='+total+'total_sett_amount'+total_sett_amount);");
			//out.println("alert('test----'+(parseFloat(Math.round((total - total_sett_amount)*100)/100)))");
			
			out.println("document.Form1.elements['Text_sett_amount'+start2].value=format_noobject(parseFloat(Math.round((total - total_sett_amount)*100)/100));");
			//out.println("document.Form1.elements['Text_sett_amount'+start2].value=format_noobject(parseFloat(total) - parseFloat(total_sett_amount));");
			out.println("document.Form1.elements['Text_standard'+start2].checked=true;");
			out.println("document.Form1.elements['Text_standard'+start2].value=\"YES\";");
						
			out.println("total_sett_amount=parseFloat(total);");
			
   		//out.println("alert('2222');");
			out.println("}else{");
			
			//out.println("document.Form1.elements['Text_bal_amount'+start2].value=0.00;");
			
      out.println("document.Form1.elements['Text_sett_amount'+start2].value=format_noobject(0.00);");
			out.println("document.Form1.elements['Text_standard'+start2].checked=false;");
			out.println("document.Form1.elements['Text_standard'+start2].value=\"NO\";");
			
			out.println("total_sett_amount=parseFloat(total);");
   		//out.println("alert('3333');");			
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("}");
										
			out.println("}");

        //added by nuwan de silva on 11-08-2008-----------------
			  out.println("function validate_contract_balance(obj){ ");
				out.println(" num_of_contract=parseFloat(unformat_noobject(document.Form1.hid_cntract_cnt.value));");
				
				
				out.println("    for(k=0;k<num_of_contract;k++){");
				//out.println(" alert('k==='+document.Form1.elements['Text_inv_sett_amount'+k].value);");
				//out.println(" alert('kk==='+document.Form1.elements['Text_inv_sett_amount2'+k].value);");
				out.println("document.Form1.elements['Text_inv_sett_amount2'+k].value=document.Form1.elements['Text_inv_sett_amount'+k].value;");
				out.println("document.Form1.elements['Text_odi_sett_amount2'+k].value=document.Form1.elements['Text_odi_sett_amount'+k].value;");
				//out.println("odi=parseFloat(unformat_noobject(document.Form1.elements['Text_odi_sett_amount'+k].value));"); 
				//out.println("inv=parseFloat(unformat_noobject(document.Form1.elements['Text_inv_sett_amount'+k].value));"); 
				out.println("}");

				
				out.println(" num_of_contract=parseFloat(unformat_noobject(document.Form1.hid_other_cntract_cnt.value));");

			 
				out.println("}");
				//-------------------------------------------------------
				
				
			
				//ADDED BY NUWAN DE SILVA ON 03-04-2008
				out.println("function check_aloc_status_new(obj1){ ");
			  out.println("if(document.Form1.elements['Text_standard_chk'+obj1].checked == false){");
				out.println("document.Form1.elements['Text_odi_sett_amount'+obj1].disabled=false ;");
				out.println("document.Form1.elements['Text_inv_sett_amount'+obj1].disabled=false ;");	
				
				out.println("document.Form1.elements['Text_odi_sett_amount'+obj1].value=\"0.00\" ;");
				out.println("document.Form1.elements['Text_inv_sett_amount'+obj1].value=\"0.00\" ;");	
				out.println("check_aloc_amt(document.Form1.elements['hid_inv_'+obj1],0,'INV',obj1);");
				out.println("}");
				
				out.println("if(document.Form1.elements['Text_standard_chk'+obj1].checked == true){");
				out.println("document.Form1.elements['Text_odi_sett_amount'+obj1].disabled=true ;");
				out.println("document.Form1.elements['Text_inv_sett_amount'+obj1].disabled=true ;");	
				out.println("check_aloc_amt(document.Form1.elements['hid_inv_'+obj1],0,'INV',obj1);");
				out.println("}");
				
				out.println("}");
				
				//ADDED BY NUWAN DE SILVA ON 03-04-2008 other_Text_balance_amount
				out.println("function check_aloc_status_new_other(obj1){ ");
			  out.println("if(document.Form1.elements['other_Text_standard_chk'+obj1].checked == false){");
				out.println("document.Form1.elements['other_Text_inv_sett_amount'+obj1].disabled=false ;");
				out.println("document.Form1.elements['other_Text_odi_sett_amount'+obj1].value=\"0.00\" ;");
				out.println("document.Form1.elements['other_Text_inv_sett_amount'+obj1].value=\"0.00\" ;");	
				//out.println("check_aloc_amt(document.Form1.elements['activate_hid_inv_'+obj1],0,'INV',obj1);");
				out.println("}");
				
			  out.println("if(document.Form1.elements['other_Text_standard_chk'+obj1].checked == true){");
				out.println("document.Form1.elements['other_Text_inv_sett_amount'+obj1].disabled=true ;");
				//out.println("document.Form1.elements['activate_Text_odi_sett_amount'+obj1].value=\"0.00\" ;");
				//out.println("document.Form1.elements['activate_Text_inv_sett_amount'+obj1].value=\"0.00\" ;");	
				out.println("}");

				out.println("}");
				
			
			
			////////////////////////////////////////////////////////////////////////////
			
			
				//ADDED BY NUWAN DE SILVA ON 03-04-2008
				out.println("function check_aloc_status_new_activated(obj1){ ");
			  out.println("if(document.Form1.elements['activate_Text_standard_chk'+obj1].checked == false){");
				out.println("document.Form1.elements['activate_Text_inv_sett_amount'+obj1].disabled=false ;");
				out.println("document.Form1.elements['activate_Text_odi_sett_amount'+obj1].value=\"0.00\" ;");
				out.println("document.Form1.elements['activate_Text_inv_sett_amount'+obj1].value=\"0.00\" ;");	
				//out.println("check_aloc_amt(document.Form1.elements['activate_hid_inv_'+obj1],0,'INV',obj1);");
				out.println("}");
				
			  out.println("if(document.Form1.elements['activate_Text_standard_chk'+obj1].checked == true){");
				out.println("document.Form1.elements['activate_Text_inv_sett_amount'+obj1].disabled=true ;");
				//out.println("document.Form1.elements['activate_Text_odi_sett_amount'+obj1].value=\"0.00\" ;");
				//out.println("document.Form1.elements['activate_Text_inv_sett_amount'+obj1].value=\"0.00\" ;");	
				out.println("}");

				out.println("}");
			
		  out.println("function check_aloc_status(obj1){ ");
			//out.println("alert('Start');");
			
			out.println("if(obj1==0){");
			out.println("val1=(parseFloat(obj1));");
			out.println("start=parseFloat(obj1);");
			out.println("}else{");
			//out.println("alert('Start value'+((parseFloat(obj1))-1));");
			out.println("val1=((parseFloat(obj1))-1);");
			out.println("start=parseFloat(document.Form1.elements['hid_invoice_tot_cnt_'+val1].value);");
			out.println("}");			

			out.println("end=parseFloat(document.Form1.elements['hid_invoice_tot_cnt_'+obj1].value);");

			//out.println("alert('Start===='+start+'end====='+end);");			
			
			out.println("for(i=0;start<end;start++){ ");
			out.println("document.Form1.elements['Text_sett_amount'+start].value=\"0.00\" ;");
			out.println("document.Form1.elements['Text_standard'+start].checked=false;");
			out.println("document.Form1.elements['Text_standard'+start].value=\"NO\" ;");
			
      //out.println("alert('Start===='+start);");			
			out.println("}");
			
			
			
			
			
			
			//out.println("alert(document.Form1.elements['Text_standard_chk'+obj1].value);");
			//out.println("alert('obj_no=='+obj1);");
			out.println("if(document.Form1.elements['Text_standard_chk'+obj1].value==\"YES\"){");
      //out.println("document.Form1.elements['Text_standard_chk'+obj1].value=\"NO\" ;");
			out.println("document.Form1.elements['Text_odi_sett_amount'+obj1].value=\"0.00\" ;");
			out.println("document.Form1.elements['Text_inv_sett_amount'+obj1].value=\"0.00\" ;");
			
			
			out.println("if(obj1==0){");
			
			out.println("if(document.Form1.OTHER_CHARGES.value == \"Y\"){");
			out.println("amount = parseFloat(unformat_noobject(document.Form1.TXT_RENTAL_OTHER_INV.value));");			
			out.println("}else{");
			out.println("amount = parseFloat(unformat_noobject(document.Form1.AMOUNT.value));");
			out.println("}");

			out.println("document.Form1.elements['Text_balance_amount'+obj1].value = amount;");
			
			out.println("}else{");
			out.println("val2=((parseFloat(obj1))-1);");
      out.println("pre_bal = parseFloat(unformat_noobject(document.Form1.elements['Text_balance_amount'+val2].value));");			
			out.println("document.Form1.elements['Text_balance_amount'+obj1].value = pre_bal;");
			
			
			out.println("}");
			
			
			
			//out.println(" assign_val(obj1);");
				
			out.println("}else{");
			//out.println("document.Form1.elements['Text_standard_chk'+obj1].value=\"YES\" ;");
			out.println("}");
			
			
				
			
			out.println("}");
			
			
		/////////////////////////////////////////////////	
		
	    	
		  out.println("function aloc_other_inv(obj){");
			
			out.println("group_no = parseInt(obj);");
						
			out.println("inv_cnt = parseInt(document.Form1.elements['hid_other_inv_cnt_'+group_no].value);");
			out.println("start_val = parseInt(document.Form1.elements['start_val_'+group_no].value);");
      
			//out.println("alert('inv_cnt12'+document.Form1.elements['hid_other_inv_cnt_'+group_no].value);");
			
      out.println("group_allo_num = (group_no-1);");
			out.println("othr_inv_sett_amount = parseFloat(unformat_noobject(document.Form1.elements['Text_othr_inv_sett_amount2'+group_allo_num].value));");
			out.println("total = othr_inv_sett_amount;");
			
			
			//out.println("alert('inv_cnt--'+inv_cnt);");
			//out.println("alert('start_val--'+start_val);");
			//out.println("alert('othr_inv_sett_amount--'+othr_inv_sett_amount);");

	    
  		out.println("var total_sett_amount=0;");
						
			out.println(" for(j=0;j<inv_cnt;j++){");
			out.println(" m = start_val + j;");
			
			out.println("document.Form1.elements['Text_sett_amount'+m].value=0.00;");
			out.println("document.Form1.elements['Text_standard'+m].checked=false;");
			out.println("document.Form1.elements['Text_standard'+m].value=\"NO\";");
			out.println("}");
			
			
			out.println("  for(j=0;j<inv_cnt;j++){");
			
			out.println(" n = start_val + j;");
			out.println("sett_amount=parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+n].value));");
			out.println("bal_amount=parseFloat(unformat_noobject(document.Form1.elements['BAL_AM_'+n].value));");  //hhhhhhhhh
			
			//out.println("alert('===bal_amount==='+bal_amount+'=====sett_amount===='+sett_amount);");
			
			//out.println("alert('===bal_amount==='+bal_amount+'=====total===='+total+'========total_sett_amount'+total_sett_amount);");
			
			
			out.println(" if(bal_amount<=total - total_sett_amount){"); 
			
			
			out.println("document.Form1.elements['Text_sett_amount'+n].value=format_noobject(bal_amount);");
			
			//out.println("alert('111 total test==='+total+'total_sett_amount'+total_sett_amount);");
			//out.println("document.Form1.elements['Text_bal_amount'+start2].value=(total - total_sett_amount);");
			
			out.println("document.Form1.elements['Text_standard'+n].checked=true;");
			out.println("document.Form1.elements['Text_standard'+n].value=\"YES\";");
			out.println("total_sett_amount=total_sett_amount + bal_amount;");
			//out.println("alert('111');");
			out.println("}else {");
			out.println("if((total - total_sett_amount)>0){");			
			out.println("document.Form1.elements['Text_sett_amount'+n].value=format_noobject(total - total_sett_amount);");
			out.println("document.Form1.elements['Text_standard'+n].checked=true;");
			out.println("document.Form1.elements['Text_standard'+n].value=\"YES\";");
						
			out.println("total_sett_amount=total;");
   		//out.println("alert('2222');");
			out.println("}else{");
			
			//out.println("document.Form1.elements['Text_bal_amount'+start2].value=0.00;");
			
      out.println("document.Form1.elements['Text_sett_amount'+n].value=format_noobject(0.00);");
			out.println("document.Form1.elements['Text_standard'+n].checked=false;");
			out.println("document.Form1.elements['Text_standard'+n].value=\"NO\";");
			
			out.println("total_sett_amount=total;");
   		//out.println("alert('3333');");			
			out.println("}"); 
			out.println("}");  
			out.println("}");
			
					
			
			out.println("}");
			
			
 		
			
			out.println("function chk_othr_batch_val(obj1,obj2){ "); 
			
			out.println("var group_no=0;");
			out.println("var inv_cnt =0;");
			out.println("var start_val =0;");
			
			out.println("group_no = parseInt(obj1);");
			//out.println("inv_cnt  = parseInt(obj2);");
			
			out.println("inv_cnt = parseInt(document.Form1.elements['hid_other_inv_cnt_'+group_no].value);");
			out.println("start_val = parseInt(document.Form1.elements['start_val_'+group_no].value);");
			
			out.println("group_allo_num = (group_no - 1);");
			out.println("othr_inv_sett_amount = parseFloat(unformat_noobject(document.Form1.elements['Text_othr_inv_sett_amount2'+group_allo_num].value));");
			
			out.println("bal_per_inv=parseFloat(unformat_noobject(document.Form1.elements['BAL_AM_'+obj2].value));"); 
			out.println("alo_per_inv=parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+obj2].value));"); 

			//out.println("alert('bal_per_inv==='+bal_per_inv+'alo_per_inv===='+alo_per_inv);");	
			
			out.println("if(bal_per_inv<alo_per_inv){");
			out.println("alert('Maximum alocated amount Should be Rs.'+format_noobject(bal_per_inv));");
			out.println("document.Form1.elements['Text_sett_amount'+obj2].value=format_noobject(bal_per_inv);");
			out.println("}");
			
			
			
			
			
			//out.println("alert('group_no--'+group_no);");
			//out.println("alert('inv_cnt--'+inv_cnt);");
			//out.println("alert('start_val--'+start_val);");
			//out.println("alert('othr_inv_sett_amount--'+othr_inv_sett_amount);");
			out.println("var bal_amt =0;");
			out.println("var tot_sett_amount =0;");
			
			out.println("for(var i=0; i<inv_cnt; i++){");  
			
			out.println("k = (start_val+i);");
			
			out.println("bal_amt = parseFloat(unformat_noobject(document.Form1.elements['BAL_AM_'+k].value));");
			out.println("sett_amount = parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+k].value));");
			out.println("tot_sett_amount = tot_sett_amount + sett_amount;");
			out.println("}");
			
			//out.println("alert('tot_sett_amount---'+tot_sett_amount);");
			
			out.println("if(tot_sett_amount <= othr_inv_sett_amount){"); 
					
			out.println("}else{");
			out.println("alert('Total Invoice Amount Cannot be greater than Rs.'+format_noobject(othr_inv_sett_amount));"); 
			out.println("document.Form1.elements['Text_sett_amount'+obj2].value=\"0.00\";");
			out.println("document.Form1.elements['Text_standard'+obj2].checked=false;");
		  out.println("document.Form1.elements['Text_standard'+obj2].value=\"NO\";");
			out.println("}");
			
			
			out.println("if(document.Form1.elements['Text_sett_amount'+obj2].value > 0){");
			out.println("document.Form1.elements['Text_sett_amount'+obj2].value = format_noobject(document.Form1.elements['Text_sett_amount'+obj2].value);");
			out.println("document.Form1.elements['Text_standard'+obj2].checked=true;");
		  out.println("document.Form1.elements['Text_standard'+obj2].value=\"YES\";");
			out.println("}");
			
			
			
			//out.println("alert('othr_inv_sett_amount---'+othr_inv_sett_amount+'BAL_AM---'+bal_amt);");  
			
			/*
      out.println("if(bal_amt <= othr_inv_sett_amount){"); 
			out.println(" document.Form1.elements['Text_sett_amount'+k].value = bal_amt;");
			out.println("document.Form1.elements['Text_standard'+k].checked=true;");
		  out.println("document.Form1.elements['Text_standard'+k].value=\"YES\";");
			
			out.println("othr_inv_sett_amount = othr_inv_sett_amount - bal_amt;");
			out.println("}else{");
			out.println(" document.Form1.elements['Text_sett_amount'+k].value = othr_inv_sett_amount;");
			out.println("document.Form1.elements['Text_standard'+k].checked=true;");
		  out.println("document.Form1.elements['Text_standard'+k].value=\"YES\";");
			out.println("othr_inv_sett_amount = 0.00;");
			out.println("}"); */
			
			
			
			
			//out.println("othr_inv_amount = parseFloat(unformat_noobject(document.Form1.elements['Text_othr_inv_sett_amount2'+i].value));");
      //out.println("alert('BAL_AM----'+BAL_AM);");
			
			
			
			
			out.println("contract_cnt=parseInt(document.Form1.hid_othr_contract_cnt.value);");
			//out.println("alert('cntract cnt---'+contract_cnt);");
			
			out.println("var inv_num=0;");
			
			
			out.println("for(var i=1; i<=contract_cnt; i++){");
			//out.println("alert('---'+i);");
      out.println("inv_num=parseInt(document.Form1.elements['hid_other_inv_cnt_'+i].value);");
			//out.println("alert('inv_num---'+document.Form1.elements['hid_other_inv_cnt_'+i].value);");
			out.println("}");
			
			out.println("}");
			
			
			
			
			
			
			
			out.println("function chk_batch_val(obj1,obj2){ "); 
			
								
			out.println("if(obj1==0){");
			//out.println("alert('Start value'+(parseFloat(obj1)));");
			out.println("val1=(parseFloat(obj1));");
			
			out.println("start=parseFloat(obj1);");
			out.println("start2=parseFloat(obj1);");
						
			out.println("}else{");
			//out.println("alert('Start value'+((parseFloat(obj1))-1));");
			out.println("val1=((parseFloat(obj1))-1);");
			out.println("start=parseFloat(document.Form1.elements['hid_invoice_tot_cnt_'+val1].value);");
			out.println("start2=parseFloat(document.Form1.elements['hid_invoice_tot_cnt_'+val1].value);");
			
			out.println("}");			

			out.println("end=parseFloat(document.Form1.elements['hid_invoice_tot_cnt_'+obj1].value);");
			
	    		
      
			//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
			out.println("bal_per_inv=parseFloat(unformat_noobject(document.Form1.elements['BAL_AM_'+obj2].value));"); 
			out.println("alo_per_inv=parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+obj2].value));"); 

			//out.println("alert('bal_per_inv==='+bal_per_inv+'alo_per_inv===='+alo_per_inv);");	
			
			out.println("if(bal_per_inv<alo_per_inv){");
			out.println("alert('Maximum alocated amount Should be Rs.'+format_noobject(bal_per_inv));");
			out.println("document.Form1.elements['Text_sett_amount'+obj2].value=format_noobject(bal_per_inv);");
			out.println("}");
			
			out.println("tot_odi=parseFloat(unformat_noobject(document.Form1.elements['Text_odi_sett_amount'+obj1].value));"); 
			out.println("tot_inv=parseFloat(unformat_noobject(document.Form1.elements['Text_inv_sett_amount'+obj1].value));"); 
			out.println("total=tot_odi+tot_inv;");
			
		 out.println("var tot_sett_amt=0;");
						
			out.println("    for(j=0;start<end;start++){");
			out.println("sett_amt = parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+start].value));");
			
			out.println("tot_sett_amt = tot_sett_amt + sett_amt;");
			//out.println("alert('tot_sett_amt=='+tot_sett_amt+'bal_per_inv==='+bal_per_inv);");
			out.println("}"); 
		
		 out.println("if(tot_sett_amt>total){");
     out.println("alert('Total Invoice Amount Cannot be greater than Rs.'+format_noobject(total));"); 
			
		 out.println(" document.Form1.elements['Text_sett_amount'+obj2].value =\"0.00\"");
		 out.println("document.Form1.elements['Text_standard'+obj2].checked=false;");
		 out.println("document.Form1.elements['Text_standard'+obj2].value=\"NO\";");	
		 out.println("}"); 
		
		 //out.println("alert('val1'+val1);"); 
		
		out.println("odi_amt1 = parseFloat(unformat_noobject(document.Form1.elements['Text_odi_sett_amount2'+val1].value));");
    out.println("inv_amt1 = parseFloat(unformat_noobject(document.Form1.elements['Text_inv_sett_amount2'+val1].value));");
		out.println("tot_amt1 = inv_amt1+odi_amt1;");
		
		 //out.println("alert('start2'+start2+'end'+end);");
		
		 out.println("var tot_sett_amt2=0;");
		 
		 out.println(" for(j=0;start2<end;start2++){"); 
		 out.println("sett_amt2 = parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+start2].value));");
		 	
			out.println("tot_sett_amt2 = tot_sett_amt2 + sett_amt2;");
			
			out.println("if(tot_sett_amt2<=tot_amt1){");
			out.println("bal_amt = tot_amt1 - tot_sett_amt2;");
			//out.println("document.Form1.elements['Text_bal_amount'+start2].value=bal_amt;");
			//Text_bal_amount
			
			out.println("}");
			
			
						
			out.println("if(sett_amt2>0){");
					
			out.println("document.Form1.elements['Text_standard'+start2].checked=true;");
			out.println("document.Form1.elements['Text_standard'+start2].value=\"YES\";");
			out.println("  document.Form1.elements['Text_sett_amount'+start2].disabled =true;"); // added by nuwan de silva on 12-08-2008
			out.println("}else{");
		  out.println("document.Form1.elements['Text_standard'+start2].checked=false;");
			out.println("document.Form1.elements['Text_standard'+start2].value=\"NO\";");
			out.println("document.Form1.elements['Text_sett_amount'+start2].disabled =false;"); // added by nuwan de silva on 12-08-2008
			out.println("}");
			//out.println("tot_sett_amt2 = tot_sett_amt2 + sett_amt2;");
			out.println("");
     		
		 out.println("}");
     		
			/*out.println("    for(j=0;start2<end;start2++){");
			out.println("sett_amount=parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+start2].value));");
			out.println("bal_amount=parseFloat(unformat_noobject(document.Form1.elements['BAL_AM_'+start2].value));");
			
			//out.println("alert('===bal_amount==='+bal_amount+'=====sett_amount===='+sett_amount);");
			
			out.println(" if(bal_amount<=total - total_sett_amount){"); 
			
			//out.println("alert('======'+bal_amount+'========='+total_sett_amount);");
			
			out.println("document.Form1.elements['Text_sett_amount'+start2].value=bal_amount;");
			out.println("document.Form1.elements['Text_standard'+start2].checked=true;");
			out.println("total_sett_amount=total_sett_amount + bal_amount;");
			//out.println("alert('111');");
			out.println("}else {");
			out.println("if((total - total_sett_amount)>0){");			
			out.println("document.Form1.elements['Text_sett_amount'+start2].value=(total - total_sett_amount);");
			out.println("document.Form1.elements['Text_standard'+start2].checked=true;");
			out.println("total_sett_amount=total;");
   		//out.println("alert('2222');");
			out.println("}else{");
      out.println("document.Form1.elements['Text_sett_amount'+start2].value=(0.00);");
			out.println("document.Form1.elements['Text_standard'+start2].checked=false;");
			out.println("total_sett_amount=total;");
   		//out.println("alert('3333');");			
			out.println("}");
			out.println("}");
			out.println("}");
				*/	
			out.println("}");

			
			
			
			
		
      out.println(" function show_inv_det(){ ");
			
			out.println("  get_invoice();");
			
						
			out.println("}");
	
	
		  out.println(" function allo_inv_det(){ "); ///yyyyyyyyy
			
			//out.println("   alert('k====test');");
			
			//out.println("   alert('k===='+document.Form1.hid_cntract_cnt.value);");
			
			out.println("m_num_of_cont = parseFloat(unformat_noobject(document.Form1.hid_cntract_cnt.value));");
				
	    //out.println("   alert('k===='+m_num_of_cont);"); 	
 	    
			out.println("    for(k=0;k<m_num_of_cont;k++){");
	 							
			
			//out.println(" alert('k==='+document.Form1.elements['Text_inv_sett_amount'+k].value);");
			//out.println(" alert('kk==='+document.Form1.elements['Text_inv_sett_amount2'+k].value);");

			out.println("document.Form1.elements['Text_inv_sett_amount2'+k].value=document.Form1.elements['Text_inv_sett_amount'+k].value;");
			out.println("document.Form1.elements['Text_odi_sett_amount2'+k].value=document.Form1.elements['Text_odi_sett_amount'+k].value;");
			
						
		  //out.println("odi=parseFloat(unformat_noobject(document.Form1.elements['Text_odi_sett_amount'+k].value));"); 
			//out.println("inv=parseFloat(unformat_noobject(document.Form1.elements['Text_inv_sett_amount'+k].value));"); 
			
			out.println("}");
			
			out.println("   for(k=0;k<m_num_of_cont;k++){");
			//out.println("   alert('k===='+k);");
			
			
			
			out.println("  aloc_inv(k);");
			out.println("}");
			
      //out.println("   for(k=0;k<m_num_of_cont;k++){");
			//out.println("  assign_bal(k);");
			//out.println("}");
			out.println("assgn_balance();");
				
			out.println("}");
			
			
			
			
			 out.println(" function allo_other_inv_det(){ "); ///yyyyyyyyy
			 //out.println("alert('test12');");
			//out.println("m_num_of_othr_cont = parseFloat(unformat_noobject(document.Form1.hid_othr_contract_cnt.value));");
			out.println("m_num_of_othr_cont = parseFloat(document.Form1.hid_othr_contract_cnt.value);");
			//out.println("   alert('m_num_of_othr_cont===='+m_num_of_othr_cont);");   	
						
			out.println("    for(k=0;k<m_num_of_othr_cont;k++){");
	 		out.println("document.Form1.elements['Text_othr_balance_amount2'+k].value=document.Form1.elements['other_Text_balance_amount'+k].value;");
			out.println("document.Form1.elements['Text_othr_inv_sett_amount2'+k].value=document.Form1.elements['other_Text_inv_sett_amount'+k].value;");
				
			out.println("}");
			
			out.println("   for(k=1;k<=m_num_of_othr_cont;k++){"); //  //m_num_of_cont
			out.println("m_num_of_othr_cont = parseFloat(document.Form1.hid_othr_contract_cnt.value);");
			out.println("if(m_num_of_othr_cont != 0){");
			//out.println("   alert('k===='+k+'===='+m_num_of_othr_cont);");
			out.println("  aloc_other_inv(k);");
			out.println("}");
			//out.println("  aloc_other1_inv(k);");
			out.println("}");
			
			//out.println("assgn_balance();");
				
			out.println("}");

			
			
			
			
	
	
	   out.println(" function remv_inv_det(){ ");
		 out.println("inv.innerHTML=\"\" ");	
		 out.println("}");
	 
	    
		 //out.println("function aloc_inv(obj){");	
			
		 //out.println("con_num=(parseFloat(obj));");
	   //out.println("alert('test'+con_num);");
			
	   //out.println("}");
			
			
			out.println("function check_act_aloc_amt(obj1,obj2,obj3,obj4){ ");	
			
			out.println("obj44=((parseFloat(obj4))-1);");
			out.println("M_TOT = parseFloat(unformat_noobject(document.Form1.elements['OTHR_ODI_VAL_'+obj4].value)) + parseFloat(unformat_noobject(document.Form1.elements['OTHR_INV_VAL_'+obj4].value));");
			out.println("M_BAL = parseFloat(unformat_noobject(document.Form1.elements['other_Text_balance_amount'+obj4].value));"); 
			
			out.println("M_ODI = parseFloat(unformat_noobject(document.Form1.elements['other_Text_odi_sett_amount'+obj4].value));");
			out.println("M_INV = parseFloat(unformat_noobject(document.Form1.elements['other_Text_inv_sett_amount'+obj4].value));");
			
			out.println("M_ODI_BAL = parseFloat(unformat_noobject(document.Form1.elements['OTHR_ODI_VAL_'+obj4].value));");
			out.println("M_INV_BAL = parseFloat(unformat_noobject(document.Form1.elements['OTHR_INV_VAL_'+obj4].value));");
			
			out.println("if(document.Form1.OTHER_CHARGES.value == \"Y\"){");
			out.println("M_AMT = parseFloat(unformat_noobject(document.Form1.TXT_OTHER_INV.value));");			
			out.println("}else{");
			out.println("M_AMT = parseFloat(unformat_noobject(0.00));");
			out.println("}");
			out.println("if(obj4==0){");
			out.println("if((M_TOT < (M_ODI+M_INV))||(M_AMT < (M_ODI+M_INV))){");
			out.println("alert('Alocated amount exceeds invoice balance amount');");
			out.println("if(obj3==\"ODI\"){");
			out.println("if(M_AMT<M_ODI_BAL){");
			out.println("document.Form1.elements['other_Text_odi_sett_amount'+obj4].value=M_AMT;");
			out.println("}else {");
			out.println("document.Form1.elements['other_Text_odi_sett_amount'+obj4].value=M_ODI_BAL;");
			out.println("}"); 
			out.println("} else if(obj3==\"INV\"){"); 
			out.println("if(M_AMT<M_INV_BAL){");
			out.println("document.Form1.elements['other_Text_inv_sett_amount'+obj4].value=M_AMT;");
			out.println("}else {");
			out.println("document.Form1.elements['other_Text_inv_sett_amount'+obj4].value=M_INV_BAL;");
			out.println("}"); 
			out.println("}"); 
			out.println("}");
			out.println("}else{");
			out.println("M_BAL_PRE = parseFloat(unformat_noobject(document.Form1.elements['other_Text_balance_amount'+obj44].value));");
			out.println("if((M_TOT < (M_ODI+M_INV))||(M_BAL_PRE < (M_ODI+M_INV))){");
			out.println("alert('Alocated amount exceeds invoice balance amount');");
			out.println("if(obj3==\"ODI\"){");
			out.println("if(M_BAL_PRE<M_ODI_BAL){");
			out.println("document.Form1.elements['other_Text_odi_sett_amount'+obj4].value=M_BAL_PRE;");
			out.println("}else {");
			out.println("document.Form1.elements['other_Text_odi_sett_amount'+obj4].value=M_ODI_BAL;");
			out.println("}"); 
			out.println("} else if(obj3==\"INV\"){"); 
			out.println("if(M_BAL_PRE<M_INV_BAL){");
		  out.println("document.Form1.elements['other_Text_inv_sett_amount'+obj4].value=M_BAL_PRE;");
			out.println("}else {");
			out.println("document.Form1.elements['other_Text_inv_sett_amount'+obj4].value=M_INV_BAL;");
			out.println("}"); 
			out.println("}"); 
      out.println("}");
			out.println("}");	
			out.println(" num_of_contract=parseFloat(unformat_noobject(document.Form1.hid_other_cntract_cnt.value));");
			out.println("if(document.Form1.OTHER_CHARGES.value == \"Y\"){");
			out.println("amount = parseFloat(unformat_noobject(document.Form1.TXT_OTHER_INV.value));");			
			out.println("}else{");
			out.println("amount = parseFloat(unformat_noobject(0.00));");
			out.println("}");
			out.println("var tot=0");
			out.println("for(j=0; j<num_of_contract; j++){"); 
			out.println("odi_sett_amount=parseFloat(unformat_noobject(document.Form1.elements['other_Text_odi_sett_amount'+j].value));");
      out.println("inv_sett_amount=parseFloat(unformat_noobject(document.Form1.elements['other_Text_inv_sett_amount'+j].value));");
			out.println(" balance = (amount - (odi_sett_amount + inv_sett_amount) - tot);");
			out.println("if(balance>=0){");
			out.println(" document.Form1.elements['other_Text_balance_amount'+j].value = balance;");
			out.println(" document.Form1.elements['other_Text_standard_chk'+j].checked=true;");   
			out.println(" document.Form1.elements['other_Text_standard_chk'+j].value=\"YES\";");
			out.println(" tot = tot + (odi_sett_amount + inv_sett_amount);");
			out.println("}");
			out.println("}");
			out.println("}");	

			
			
			
			
			out.println("function check_other_aloc_amt(obj1,obj2,obj3,obj4){ ");	
		 		 
			//out.println("alert('test'+obj4);");
			
			out.println("obj44=((parseFloat(obj4))-1);");
			
			
			out.println("M_TOT = parseFloat(unformat_noobject(document.Form1.elements['OTHR_ODI_VAL_'+obj4].value)) + parseFloat(unformat_noobject(document.Form1.elements['OTHR_INV_VAL_'+obj4].value));");
			//out.println("alert('M_TOT'+M_TOT);");
			out.println("M_BAL = parseFloat(unformat_noobject(document.Form1.elements['other_Text_balance_amount'+obj4].value));");  
			
			out.println("M_ODI = parseFloat(unformat_noobject(document.Form1.elements['other_Text_odi_sett_amount'+obj4].value));");
			out.println("M_INV = parseFloat(unformat_noobject(document.Form1.elements['other_Text_inv_sett_amount'+obj4].value));");
			
			out.println("M_ODI_BAL = parseFloat(unformat_noobject(document.Form1.elements['OTHR_ODI_VAL_'+obj4].value));");
			out.println("M_INV_BAL = parseFloat(unformat_noobject(document.Form1.elements['OTHR_INV_VAL_'+obj4].value));");
			
			
			out.println("if(document.Form1.OTHER_CHARGES.value == \"Y\"){");
			out.println("M_AMT = parseFloat(unformat_noobject(document.Form1.TXT_OTHER_INV.value));");			
			out.println("}else{");
			out.println("M_AMT = parseFloat(unformat_noobject(0.00));");
			out.println("}");
			
			
			
			//out.println("alert('=='+obj4+'==='+M_TOT+'===='+M_BAL+'==='+(M_ODI+M_INV));");
			
			
			out.println("if(obj4==0){");
			
			//out.println("alert('M_TOT=='+M_TOT+' < (M_ODI+M_INV)=='+(M_ODI+M_INV)+'M_AMT'+M_AMT+' < (M_ODI+M_INV)=='+(M_ODI+M_INV));");
			
			out.println("if((M_TOT < (M_ODI+M_INV))||(M_AMT < (M_ODI+M_INV))){");
			//out.println("alert('Alocated amount too large');");
			out.println("alert('Alocated amount exceeds invoice balance amount');");
						
			out.println("if(obj3==\"ODI\"){");
			
			out.println("if(M_AMT<M_ODI_BAL){");
			out.println("document.Form1.elements['other_Text_odi_sett_amount'+obj4].value=M_AMT;");
			out.println("}else {");
			out.println("document.Form1.elements['other_Text_odi_sett_amount'+obj4].value=M_ODI_BAL;");
			out.println("}"); 
			
			out.println("} else if(obj3==\"INV\"){"); 
      
			out.println("if(M_AMT<M_INV_BAL){");
			out.println("document.Form1.elements['other_Text_inv_sett_amount'+obj4].value=M_AMT;");
			out.println("}else {");
			out.println("document.Form1.elements['other_Text_inv_sett_amount'+obj4].value=M_INV_BAL;");
			out.println("}"); 
			
			out.println("}"); 
			out.println("}");
			 
			out.println("}else{");
			
			out.println("M_BAL_PRE = parseFloat(unformat_noobject(document.Form1.elements['other_Text_balance_amount'+obj44].value));");
			
			//out.println("alert('M_TOT=='+M_TOT+'(M_ODI+M_INV)'+(M_ODI+M_INV)+'M_BAL_PRE'+M_BAL_PRE+'(M_ODI+M_INV)'+(M_ODI+M_INV));");
			out.println("if((M_TOT < (M_ODI+M_INV))||(M_BAL_PRE < (M_ODI+M_INV))){");
			//out.println("alert('Alocated amount too large');");
			out.println("alert('Alocated amount exceeds invoice balance amount');");

      
			
			out.println("if(obj3==\"ODI\"){");
			
			out.println("if(M_BAL_PRE<M_ODI_BAL){");
			out.println("document.Form1.elements['other_Text_odi_sett_amount'+obj4].value=M_BAL_PRE;");
			out.println("}else {");
			out.println("document.Form1.elements['other_Text_odi_sett_amount'+obj4].value=M_ODI_BAL;");
			out.println("}"); 
			
			out.println("} else if(obj3==\"INV\"){"); 
      			
			out.println("if(M_BAL_PRE<M_INV_BAL){");
		  out.println("document.Form1.elements['other_Text_inv_sett_amount'+obj4].value=M_BAL_PRE;");
			out.println("}else {");
			out.println("document.Form1.elements['other_Text_inv_sett_amount'+obj4].value=M_INV_BAL;");
			out.println("}"); 
			
			out.println("}"); 
						
      out.println("}");
			
				
			out.println("}");	
			
			
			

			out.println(" num_of_contract=parseFloat(unformat_noobject(document.Form1.hid_other_cntract_cnt.value));");
			
			out.println("if(document.Form1.OTHER_CHARGES.value == \"Y\"){");
			out.println("amount = parseFloat(unformat_noobject(document.Form1.TXT_OTHER_INV.value));");			
			out.println("}else{");
			out.println("amount = parseFloat(unformat_noobject(0.00));");
			out.println("}");
			
			//out.println(" amount=parseFloat(unformat_noobject(document.Form1.AMOUNT.value));");
						
			out.println("var tot=0");
			
			out.println("for(j=0; j<num_of_contract; j++){"); 
			
			
			//out.println("alert('Alocated amount=='+j+'===='+parseFloat(unformat_noobject(document.Form1.elements['Text_balance_amount'+j].value)));");
			
			//out.println("alert('Text_odi_sett_amount=='+j+'===='+parseFloat(unformat_noobject(document.Form1.elements['Text_odi_sett_amount'+j].value)));");
      //out.println("alert('Text_inv_sett_amount=='+j+'===='+parseFloat(unformat_noobject(document.Form1.elements['Text_inv_sett_amount'+j].value)));");
      
			out.println("odi_sett_amount=parseFloat(unformat_noobject(document.Form1.elements['other_Text_odi_sett_amount'+j].value));");
      out.println("inv_sett_amount=parseFloat(unformat_noobject(document.Form1.elements['other_Text_inv_sett_amount'+j].value));");

			
			out.println(" balance = (amount - (odi_sett_amount + inv_sett_amount) - tot);");
						
			out.println("if(balance>=0){");
			out.println(" document.Form1.elements['other_Text_balance_amount'+j].value = balance;");
			//out.println("alert('====='+j+'==='+document.Form1.elements['Text_standard_chk'+j].value);");
			out.println(" document.Form1.elements['other_Text_standard_chk'+j].checked=true;");   
			out.println(" document.Form1.elements['other_Text_standard_chk'+j].value=\"YES\";");
			
			out.println(" tot = tot + (odi_sett_amount + inv_sett_amount);");
			out.println("}");
			out.println("}");
			out.println("}");	
			
			
			
			
		  out.println("function check_aloc_amt(obj1,obj2,obj3,obj4){ ");	
			out.println("obj44=((parseFloat(obj4))-1);");
			
			out.println("M_TOT = parseFloat(unformat_noobject(document.Form1.elements['ODI_VAL_'+obj4].value)) + parseFloat(unformat_noobject(document.Form1.elements['INV_VAL_'+obj4].value));");
			//out.println("alert('M_TOT'+M_TOT);");
			out.println("M_BAL = parseFloat(unformat_noobject(document.Form1.elements['Text_balance_amount'+obj4].value));");  
			
			out.println("M_ODI = parseFloat(unformat_noobject(document.Form1.elements['Text_odi_sett_amount'+obj4].value));");
			out.println("M_INV = parseFloat(unformat_noobject(document.Form1.elements['Text_inv_sett_amount'+obj4].value));");
			
			out.println("M_ODI_BAL = parseFloat(unformat_noobject(document.Form1.elements['ODI_VAL_'+obj4].value));");
			out.println("M_INV_BAL = parseFloat(unformat_noobject(document.Form1.elements['INV_VAL_'+obj4].value));");
			
			
			out.println("if(document.Form1.OTHER_CHARGES.value == \"Y\"){");
			out.println("M_AMT = parseFloat(unformat_noobject(document.Form1.TXT_RENTAL_OTHER_INV.value));");			
			out.println("}else{");
			out.println("M_AMT = parseFloat(unformat_noobject(document.Form1.AMOUNT.value));");
			out.println("}");
			
			//out.println("alert('=='+obj4+'==='+M_TOT+'===='+M_BAL+'==='+(M_ODI+M_INV));");
			out.println("if(obj4==0){");
			
			//out.println("alert('M_TOT=='+M_TOT+' < (M_ODI+M_INV)=='+(M_ODI+M_INV)+'M_AMT'+M_AMT+' < (M_ODI+M_INV)=='+(M_ODI+M_INV));");
			
			out.println("if((M_TOT < (M_ODI+M_INV))||(M_AMT < (M_ODI+M_INV))){");
			//out.println("alert('Alocated amount too large');");
			
			out.println("alert('Alocated amount exceeds invoice balance amount');");
						
			out.println("if(obj3==\"ODI\"){");
			
			out.println("if(M_AMT<M_ODI_BAL){");
			out.println("document.Form1.elements['Text_odi_sett_amount'+obj4].value=M_AMT;");
			out.println("}else {");
			out.println("document.Form1.elements['Text_odi_sett_amount'+obj4].value=M_ODI_BAL;");
			out.println("}"); 
			
			out.println("} else if(obj3==\"INV\"){"); 
      
			out.println("if(M_AMT<M_INV_BAL){");
			out.println("document.Form1.elements['Text_inv_sett_amount'+obj4].value=M_AMT;");
			out.println("}else {");
			out.println("document.Form1.elements['Text_inv_sett_amount'+obj4].value=M_INV_BAL;");
			out.println("}"); 
			
			out.println("}"); 
			out.println("}");
			 
			out.println("}else{");
			
			out.println("M_BAL_PRE = parseFloat(unformat_noobject(document.Form1.elements['Text_balance_amount'+obj44].value));");
			
			//out.println("alert('M_TOT=='+M_TOT+'(M_ODI+M_INV)'+(M_ODI+M_INV)+'M_BAL_PRE'+M_BAL_PRE+'(M_ODI+M_INV)'+(M_ODI+M_INV));");
			out.println("if((M_TOT < (M_ODI+M_INV))||(M_BAL_PRE < (M_ODI+M_INV))){");
			//out.println("alert('Alocated amount too large');");
			out.println("alert('Alocated amount exceeds invoice balance amount');");
			
			out.println("if(obj3==\"ODI\"){");
			
			out.println("if(M_BAL_PRE<M_ODI_BAL){");
			out.println("document.Form1.elements['Text_odi_sett_amount'+obj4].value=M_BAL_PRE;");
			out.println("}else {");
			out.println("document.Form1.elements['Text_odi_sett_amount'+obj4].value=M_ODI_BAL;");
			out.println("}"); 
			
			out.println("} else if(obj3==\"INV\"){"); 
      			
			out.println("if(M_BAL_PRE<M_INV_BAL){");
		  out.println("document.Form1.elements['Text_inv_sett_amount'+obj4].value=M_BAL_PRE;");
			out.println("}else {");
			out.println("document.Form1.elements['Text_inv_sett_amount'+obj4].value=M_INV_BAL;");
			out.println("}"); 
			
			out.println("}"); 
						
      out.println("}");
			
				
			out.println("}");	
			
			out.println(" num_of_contract=parseFloat(unformat_noobject(document.Form1.hid_cntract_cnt.value));");
			
			out.println("if(document.Form1.OTHER_CHARGES.value == \"Y\"){");
			out.println("amount = parseFloat(unformat_noobject(document.Form1.TXT_RENTAL_OTHER_INV.value));");			
			out.println("}else{");
			out.println("amount = parseFloat(unformat_noobject(document.Form1.AMOUNT.value));");
			out.println("}");
			
			//out.println(" amount=parseFloat(unformat_noobject(document.Form1.AMOUNT.value));");
						
			out.println("var tot=0");
			
			out.println("for(j=0; j<num_of_contract; j++){"); 
			
			
			//out.println("alert('Alocated amount=='+j+'===='+parseFloat(unformat_noobject(document.Form1.elements['Text_balance_amount'+j].value)));");
			
			//out.println("alert('Text_odi_sett_amount=='+j+'===='+parseFloat(unformat_noobject(document.Form1.elements['Text_odi_sett_amount'+j].value)));");
      //out.println("alert('Text_inv_sett_amount=='+j+'===='+parseFloat(unformat_noobject(document.Form1.elements['Text_inv_sett_amount'+j].value)));");
      
			out.println("odi_sett_amount=parseFloat(unformat_noobject(document.Form1.elements['Text_odi_sett_amount'+j].value));");
      out.println("inv_sett_amount=parseFloat(unformat_noobject(document.Form1.elements['Text_inv_sett_amount'+j].value));");

			
			out.println(" balance = (amount - (odi_sett_amount + inv_sett_amount) - tot);");
						
			out.println("if(balance>=0){");
			out.println(" document.Form1.elements['Text_balance_amount'+j].value = balance;");
			//out.println("alert('====='+j+'==='+document.Form1.elements['Text_standard_chk'+j].value);");
			//comment by nuwan de silva on 04-04-2008
			//out.println(" document.Form1.elements['Text_standard_chk'+j].checked=true;");   
			//out.println(" document.Form1.elements['Text_standard_chk'+j].value=\"YES\";");
			
			out.println(" tot = tot + (odi_sett_amount + inv_sett_amount);");
			out.println("}");
			out.println("}");
			out.println("}");	
			
			
			//ADDED BY NUWAN DE SILVA ON 12-08-2008------------------
			out.println("function check_aloc_amt_activate(obj1,obj2,obj3,obj4){ ");	
			out.println(" num_of_contract=parseFloat(unformat_noobject(document.Form1.hid_cntract_cnt.value));");
			out.println("var tot_rental=0");
			out.println("var tot_other=0");
			out.println("var Total_Activate=0");
			
			out.println("for(j=0; j<num_of_contract; j++){"); 
			out.println("odi_sett_amount=parseFloat(unformat_noobject(document.Form1.elements['Text_odi_sett_amount'+j].value));");
      out.println("inv_sett_amount=parseFloat(unformat_noobject(document.Form1.elements['Text_inv_sett_amount'+j].value));");
			out.println(" tot_rental =parseFloat(tot_rental) +(odi_sett_amount + inv_sett_amount) ;");
			out.println("}");
			
			//out.println("M_AMT = parseFloat(unformat_noobject(document.Form1.AMOUNT.value)) - tot ;");			
			
			
			out.println(" num_of_contract=parseFloat(unformat_noobject(document.Form1.hid_other_cntract_cnt.value));");
			
			out.println("for(j=0; j<num_of_contract; j++){"); 
			out.println("odi_sett_amount=parseFloat(unformat_noobject(document.Form1.elements['other_Text_odi_sett_amount'+j].value));");
      out.println("inv_sett_amount=parseFloat(unformat_noobject(document.Form1.elements['other_Text_inv_sett_amount'+j].value));");
			out.println(" tot_other =parseFloat(tot_other) +(odi_sett_amount + inv_sett_amount) ;");
			out.println("}");
			
			out.println("M_AMT = parseFloat(unformat_noobject(document.Form1.AMOUNT.value)) - ( parseFloat(tot_rental) + parseFloat(tot_other) ) ;");			
			
			out.println(" num_of_contract=parseFloat(unformat_noobject(document.Form1.hid_activate_cntract_cnt.value));");
			out.println("for(j=0; j<num_of_contract; j++){"); 
			out.println("odi_sett_amount=parseFloat(unformat_noobject(document.Form1.elements['activate_Text_odi_sett_amount'+j].value));");
      out.println("inv_sett_amount=parseFloat(unformat_noobject(document.Form1.elements['activate_Text_inv_sett_amount'+j].value));");
			out.println(" Total_Activate =parseFloat(Total_Activate) +(odi_sett_amount + inv_sett_amount) ;");
			out.println("}");
			
			out.println("if(Total_Activate > M_AMT ){");
			out.println("alert('Alocated amount exceeds invoice balance amount');");
		  out.println("document.Form1.elements['activate_Text_inv_sett_amount'+obj4].value=0.00;");
      out.println("}");
		 
			
			/*//out.println("alert('=='+obj4+'==='+M_TOT+'===='+M_BAL+'==='+(M_ODI+M_INV));");
			out.println("if(obj4==0){");
			
			//out.println("alert('M_TOT=='+M_TOT+' < (M_ODI+M_INV)=='+(M_ODI+M_INV)+'M_AMT'+M_AMT+' < (M_ODI+M_INV)=='+(M_ODI+M_INV));");
			
			out.println("if((M_TOT < (M_ODI+M_INV))||(M_AMT < (M_ODI+M_INV))){");
			//out.println("alert('Alocated amount too large');");
			
			out.println("alert('Alocated amount exceeds invoice balance amount');");
						
			out.println("if(obj3==\"ODI\"){");
			
			out.println("if(M_AMT<M_ODI_BAL){");
			out.println("document.Form1.elements['activate_Text_odi_sett_amount'+obj4].value=M_AMT;");
			out.println("}else {");
			out.println("document.Form1.elements['activate_Text_odi_sett_amount'+obj4].value=M_ODI_BAL;");
			out.println("}"); 
			
			out.println("} else if(obj3==\"INV\"){"); 
      
			out.println("if(M_AMT<M_INV_BAL){");
			out.println("document.Form1.elements['activate_Text_inv_sett_amount'+obj4].value=M_AMT;");
			out.println("}else {");
			out.println("document.Form1.elements['activate_Text_inv_sett_amount'+obj4].value=M_INV_BAL;");
			out.println("}"); 
			
			out.println("}"); 
			out.println("}");
			 
			out.println("}else{");
			
			out.println("M_BAL_PRE = parseFloat(unformat_noobject(document.Form1.elements['activate_Text_balance_amount'+obj44].value));");
			
			//out.println("alert('M_TOT=='+M_TOT+'(M_ODI+M_INV)'+(M_ODI+M_INV)+'M_BAL_PRE'+M_BAL_PRE+'(M_ODI+M_INV)'+(M_ODI+M_INV));");
			out.println("if((M_TOT < (M_ODI+M_INV))||(M_BAL_PRE < (M_ODI+M_INV))){");
			//out.println("alert('Alocated amount too large');");
			out.println("alert('Alocated amount exceeds invoice balance amount');");
			
			out.println("if(obj3==\"ODI\"){");
			
			out.println("if(M_BAL_PRE<M_ODI_BAL){");
			out.println("document.Form1.elements['activate_Text_odi_sett_amount'+obj4].value=M_BAL_PRE;");
			out.println("}else {");
			out.println("document.Form1.elements['activate_Text_odi_sett_amount'+obj4].value=M_ODI_BAL;");
			out.println("}"); 
			
			out.println("} else if(obj3==\"INV\"){"); 
      			
			out.println("if(M_BAL_PRE<M_INV_BAL){");
		  out.println("document.Form1.elements['activate_Text_inv_sett_amount'+obj4].value=M_BAL_PRE;");
			out.println("}else {");
			out.println("document.Form1.elements['activate_Text_inv_sett_amount'+obj4].value=M_INV_BAL;");
			out.println("}"); 
			
			out.println("}"); 
						
      out.println("}");
			
				
			out.println("}");	
			
			out.println(" num_of_contract=parseFloat(unformat_noobject(document.Form1.hid_activate_cntract_cnt.value));");
			
			out.println("if(document.Form1.OTHER_CHARGES.value == \"Y\"){");
			out.println("amount = parseFloat(unformat_noobject(document.Form1.TXT_RENTAL_OTHER_INV.value));");			
			out.println("}else{");
			out.println("amount = parseFloat(unformat_noobject(document.Form1.AMOUNT.value));");
			out.println("}");
			
			//out.println(" amount=parseFloat(unformat_noobject(document.Form1.AMOUNT.value));");
						
			out.println("var tot=0");
			
			out.println("for(j=0; j<num_of_contract; j++){"); 
			
			
			//out.println("alert('Alocated amount=='+j+'===='+parseFloat(unformat_noobject(document.Form1.elements['Text_balance_amount'+j].value)));");
			
			//out.println("alert('Text_odi_sett_amount=='+j+'===='+parseFloat(unformat_noobject(document.Form1.elements['Text_odi_sett_amount'+j].value)));");
      //out.println("alert('Text_inv_sett_amount=='+j+'===='+parseFloat(unformat_noobject(document.Form1.elements['Text_inv_sett_amount'+j].value)));");
      
			out.println("odi_sett_amount=parseFloat(unformat_noobject(document.Form1.elements['activate_Text_odi_sett_amount'+j].value));");
      out.println("inv_sett_amount=parseFloat(unformat_noobject(document.Form1.elements['activate_Text_inv_sett_amount'+j].value));");

			
			out.println(" balance = (amount - (odi_sett_amount + inv_sett_amount) - tot);");
						
			out.println("if(balance>=0){");
			out.println(" document.Form1.elements['activate_Text_balance_amount'+j].value = balance;");
			//out.println("alert('====='+j+'==='+document.Form1.elements['Text_standard_chk'+j].value);");
			//comment by nuwan de silva on 04-04-2008
			//out.println(" document.Form1.elements['Text_standard_chk'+j].checked=true;");   
			//out.println(" document.Form1.elements['Text_standard_chk'+j].value=\"YES\";");
			
			out.println(" tot = tot + (odi_sett_amount + inv_sett_amount);");
			out.println("}");
			out.println("}");
			
			*/
			
			out.println("}");	
    			

      
			
			out.println("function cal_balance(){");
			out.println("m_status = 1;");
			
			 out.println("var m_num_of_cont=0;");
			 out.println("m_num_of_cont = parseFloat(unformat_noobject(document.Form1.hid_cntract_cnt.value));");
			 //out.println(" alert('m_num_of_cont'+m_num_of_cont);");	
			
			 out.println("    for(k=0;k<m_num_of_cont;k++){");
			
			//out.println("alert(document.Form1.elements['Text_balance_amount'+k].value);");
			//out.println("alert(document.Form1.elements['Text_balance_amount2'+k].value);");
			
			out.println("val1=(parseFloat(k));");
				
			out.println("if(k==0){");
			//out.println("val1=(parseFloat(obj1));");
			out.println("start=parseFloat(k);");
			out.println("}else{");
			//out.println("alert('Start value'+((parseFloat(obj1))-1));");
			out.println("val1=((parseFloat(k))-1);");
			out.println("start=parseFloat(document.Form1.elements['hid_invoice_tot_cnt_'+val1].value);");
			out.println("}");			

			out.println("end=parseFloat(document.Form1.elements['hid_invoice_tot_cnt_'+k].value);"); //hid_invoice_tot_cnt_
			
			out.println(" var tot_amt=0;");
			out.println("m_odi=parseFloat(unformat_noobject(document.Form1.elements['Text_odi_sett_amount'+k].value));");
			out.println("m_inv=parseFloat(unformat_noobject(document.Form1.elements['Text_inv_sett_amount'+k].value));");
			out.println("tot_amt=m_odi+m_inv;");
			
      out.println(" var tot_aloc_amt=0;");
			out.println(" for(j=0;start<end;start++){"); 
      out.println("aloc_amt=parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+start].value));");
			
			out.println("tot_aloc_amt = tot_aloc_amt + aloc_amt; ");
			out.println("}");
			
			//out.println("alert('Start value'+start+'end'+end);");
			//out.println("alert('tot_aloc_amt'+tot_aloc_amt+'tot_amt'+tot_amt);"); 
			//out.println("alert('m_num_of_cont'+m_num_of_cont);");
			
			out.println("if(( Math.round(parseFloat(tot_aloc_amt)*100)/100==parseFloat(tot_amt))||(m_num_of_cont==0)){");
			//out.println("alert('Sucsess');");
			out.println("return true;");			
			out.println("}else{");
      //out.println("alert('Fail');");
			out.println("return false;");
			out.println("}");
			
			/*
			out.println("if(tot_aloc_amt==tot_amt){");
			out.println("m_status = 2;");
			out.println("}");		
			out.println("else if(m_num_of_cont==0){");
			//||(m_num_of_cont==0)
			out.println("m_status = 2;");
			out.println("}else{");
			out.println("m_status = 0;");
			out.println("}"); */
			
			out.println("}");
			
			//out.println("}");
			
			
			
			
			out.println("}");
			
			
			
			
			
			
			
			
			
			
			
			
			
      
			
			out.println("function assign_bal(obj1){ ");
			
			out.println("val1=(parseFloat(obj1));");
				
			out.println("if(obj1==0){");
			//out.println("val1=(parseFloat(obj1));");
			out.println("start=parseFloat(obj1);");
			out.println("}else{");
			//out.println("alert('Start value'+((parseFloat(obj1))-1));");
			//out.println("val1=((parseFloat(obj1))-1);");
			out.println("start=parseFloat(document.Form1.elements['hid_invoice_tot_cnt_'+val1].value);");
			out.println("}");			

			out.println("end=parseFloat(document.Form1.elements['hid_invoice_tot_cnt_'+obj1].value);");
			
			
			out.println("odi_amt1 = parseFloat(unformat_noobject(document.Form1.elements['Text_odi_sett_amount2'+val1].value));");
      out.println("inv_amt1 = parseFloat(unformat_noobject(document.Form1.elements['Text_inv_sett_amount2'+val1].value));");
		  out.println("tot_amt1 = inv_amt1+odi_amt1;");			

			//out.println("alert('assign bal Start===='+start+'end====='+end+'==tot_amt1=='+tot_amt1);");			
			out.println("var tot_sett_amt2 =0 ;"); 
			
			out.println(" for(j=0;start<end;start++){"); 
		  out.println("sett_amt2 = parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+start].value));");
		 	
			out.println("tot_sett_amt2 = tot_sett_amt2 + sett_amt2;");
			
			out.println("if(tot_sett_amt2<=tot_amt1){");
			out.println("bal_amt = tot_amt1 - tot_sett_amt2;");
			//out.println("alert('bal_amt'+bal_amt);");
			out.println("document.Form1.elements['Text_bal_amount'+start].value=bal_amt;");
			out.println("}else{");
      out.println("alert('bal_amt'+tot_amt1);");
			
			out.println("}");
			
			
			out.println("}");
			
			
			
			
			//out.println("for(i=0;start<end;start++){ ");
			//out.println("document.Form1.elements['Text_sett_amount'+start].value=\"0.00\" ;");
			//out.println("document.Form1.elements['Text_standard'+start].checked=false;");
			//out.println("document.Form1.elements['Text_standard'+start].value=\"NO\" ;");
			
      //out.println("alert('Start===='+start);");			
			out.println("}");












     /* out.println("function assign_bal(){");	
			
			
			
			out.println("odi_amt1 = parseFloat(unformat_noobject(document.Form1.elements['Text_odi_sett_amount2'+val1].value));");
    out.println("inv_amt1 = parseFloat(unformat_noobject(document.Form1.elements['Text_inv_sett_amount2'+val1].value));");
		out.println("tot_amt1 = inv_amt1+odi_amt1;");
		
		 //out.println("alert('start2'+start2+'end'+end);");
		
		 out.println("var tot_sett_amt2=0;");
		 
		 out.println(" for(j=0;start2<end;start2++){"); 
		 out.println("sett_amt2 = parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+start2].value));");
		 	
			out.println("tot_sett_amt2 = tot_sett_amt2 + sett_amt2;");
			
			out.println("if(tot_sett_amt2<=tot_amt1){");
			out.println("bal_amt = tot_amt1 - tot_sett_amt2;");
			out.println("document.Form1.elements['Text_bal_amount'+start2].value=bal_amt;");
			//Text_bal_amount
			
			out.println("}");
      out.println("}");
			out.println("}");
			*/
			
			
			//Added by Chandana on 02/10/2007		
			out.println("function show_client_state(data_vec){");
			out.println("m_table_client_status.innerHTML=\"\" ");
			out.println("if(data_vec[1]!=\"Y\"){"); 
			out.println("m_table_client_status.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr class=tr_input>'+");		
			out.println("'<TD WIDTH=\"20%\" align=\"left\">Client Status</TD>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\" style=\"color:red;\"><b>'+data_vec[2]+'</b></TD>'+");
			out.println("'<td width=\"*%\">&nbsp;</td></tr>'+");
			out.println("'</table>';");
			out.println("}");
			//out.println("display_client_comment(document.Form1.CLIENT_CODE.value);");
			out.println("}");
			
			out.println("function show_client_comment(data_vec){");
			//out.println("m_table_client_comment.innerHTML=\"\" ");
			//out.println("if(data_vec[1]!=\"Y\"){"); 
			out.println("m_table_client_comment.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr class=tr_input>'+");		
			out.println("'<TD WIDTH=\"30%\" align=\"left\">Client Comment</TD>'+");
			out.println("'<TD WIDTH=\"*%\" align=\"left\" style=\"color:red;\"><b><marquee>'+data_vec[0]+'</marquee></b></TD>'+");
			out.println("'</table>';");
			//out.println("}");			
			out.println("}");
			
			
			
			out.println("function check_contract(){");
			out.println("var m_num_of_cont=0;");
			out.println("var odi_amt= 0;");
			out.println("var inv_amt= 0;");
			out.println("var tot_amt= 0;");
			out.println("m_num_of_cont = parseFloat(unformat_noobject(document.Form1.hid_cntract_cnt.value));");
			//out.println("alert('m_num_of_cont'+m_num_of_cont);");
			
			out.println(" for(j=0;j<m_num_of_cont;j++){"); 
			out.println("odi_amt = odi_amt + parseFloat(unformat_noobject(document.Form1.elements['Text_odi_sett_amount'+j].value));");
			out.println("inv_amt = inv_amt + parseFloat(unformat_noobject(document.Form1.elements['Text_inv_sett_amount'+j].value));");
			//out.println("alert('vvvvvvvvv=='+odi_amt+inv_amt);");
			out.println("}");
			out.println("tot_amt=(odi_amt+inv_amt);");
			//out.println("alert('tot_amt'+tot_amt);");

			
			out.println("if(tot_amt >0){ ");
			out.println("return false;");
			out.println("}else{");
			out.println("return true;");
			out.println("}");
			out.println("}");
			
			
			out.println("function Change_Allo_Mode(val){");
      out.println("document.Form1.AMOUNT.disabled=true;	"); // added by nuwan de silva
			out.println("if(val==\"FIFO_MANU\"){");
			
			out.println("contract.innerHTML=\"\"; ");
			//out.println("fifo_aloc.innerHTML=\"\"; "); 
			out.println("contract.innerHTML=\"\"; ");
			out.println("get_contract();");
			out.println("}else{");
			out.println("contract.innerHTML=\"\"; ");
			out.println("inv.innerHTML=\"\"; ");
			out.println("get_auto_mode();");			
			out.println("}");
			
			out.println("}");
			
			
			
			out.println("function get_auto_mode() {");   //get_manual
						
			out.println("  if(document.Form1.CLIENT_CODE.value!=\"\" && document.Form1.AMOUNT.value!=\"\"){"); 
			
			out.println("if(document.Form1.OTHER_CHARGES.value==\"Y\" && document.Form1.TXT_RENTAL_OTHER_INV.value!=\"\" ){");
  		//out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=get_invoice&Client_Code=\"+document.Form1.CLIENT_CODE.value+\"&Amount=\"+unformat_noobject(document.Form1.TXT_RENTAL_OTHER_INV.value)+\"&Type=\"+document.Form1.hid_option.value+\"&Rec_No=\"+document.Form1.RECEIPT_NO.value;");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=get_invoice&Client_Code=\"+document.Form1.CLIENT_CODE.value+\"&Amount=\"+unformat_noobject(document.Form1.AMOUNT.value)+\"&Type=\"+document.Form1.hid_option.value+\"&Rec_No=\"+document.Form1.RECEIPT_NO.value;");
			out.println("  }");
			out.println("else if(document.Form1.OTHER_CHARGES.value==\"N\" ){");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Settlement?chksql=get_invoice&Client_Code=\"+document.Form1.CLIENT_CODE.value+\"&Amount=\"+unformat_noobject(document.Form1.AMOUNT.value)+\"&Type=\"+document.Form1.hid_option.value+\"&Rec_No=\"+document.Form1.RECEIPT_NO.value;");
			out.println("  }");
      out.println("   makeRequest(m_url,'11','Manual');");
			out.println("  }");
			
			out.println("}");	
			
			
			out.println("function chk_manu_bal(obj){");
			out.println("  var hid_cnt=0;");
			out.println("  var sett_amt=0;");
			out.println("  var amount=0;");
			
			out.println("  hid_cnt=parseFloat(document.Form1.hid_invoice_count.value);");
			//out.println("  alert('hid_cnt'+hid_cnt);");
			
			out.println(" for(j=0;j<hid_cnt;j++){"); 
			out.println("  sett_amt = sett_amt + parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+j].value));");
      				
			out.println("}");
	    //out.println("  alert('sett_amt'+sett_amt);");
			
			
			out.println("if(document.Form1.OTHER_CHARGES.value == \"Y\"){");
			out.println("amount = parseFloat(unformat_noobject(document.Form1.TXT_RENTAL_OTHER_INV.value));");			
			out.println("}else{");
			out.println("amount = parseFloat(unformat_noobject(document.Form1.AMOUNT.value));");
			out.println("}");
			
			out.println("if(sett_amt > amount){");
			out.println("  alert('Allocated Amount cannt be greater than Rs. '+format_noobject(amount));");
			out.println("  document.Form1.elements['Text_sett_amount'+obj].value = format_noobject(0.00);");
			//out.println("  alert('sett_amt==='+sett_amt+'==amount=='+amount);");
			out.println("}else{");
			
			
			out.println("}");
			out.println("}");
			
       
			out.println("function alloc_other(){");
			
			out.println(" if(document.Form1.TXT_FIFO.value == 'FIFO_MANU'){");
			//out.println(" document.Form1.OTHER_CHARGES.value = 'Y';"); // commented by udara on 08-05-2012
			out.println(" get_other_charges();");
			out.println("}else{");			
			//out.println(" document.Form1.OTHER_CHARGES.value = 'N';"); // commented by udara on 08-05-2012 
			out.println(" get_other_charges();");
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
			
			out.println("function chk_validity2(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY){  ");	
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
       out.println("      alert('Value Date should not be less than System Date');");
			out.println("return false;"); 
      out.println("     } ");
      out.println("}");
      out.println("else{");
      out.println("      alert('Value Date should not be less than System Date');");
			out.println("return false;"); 
      out.println("}");
      out.println(" }");
      out.println(" else{");
      out.println("   if((parseFloat(FROM_YY.value))>=(parseFloat(TO_YY.value))){");
      out.println("      alert('Value Date should not be less than System Date');");
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
      out.println("      alert('Value Date should not be less than System Date');");
			out.println("return false;"); 
      out.println(" }");
      out.println("}");
      out.println("else{");
      out.println("   if((parseFloat(FROM_YY.value))<(parseFloat(TO_YY.value))){ ");
      out.println("    }");
      out.println("  else{");
      out.println("      alert('Value Date should not be less than System Date');");
			out.println("return false;"); 
      out.println("  }");
      out.println(" }");
      out.println("}");
			out.println("return true;");
      out.println("}");
		  out.println("}");
			
			
			out.println("function validate_date(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY){");
			
			out.println("if(!chk_validity(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY)){");
	    out.println("FROM_DD.value=TO_DD.value;");
			out.println("FROM_MM.value=TO_MM.value;");
			out.println("FROM_YY.value=TO_YY.value;");
			out.println("}");
			//Added By ns on 23-02-2011 for restric the back dated receipt entry
			out.println("if(document.Form1.SETT_MODE.value !='STD_ORD' ) { ");
			out.println("if(!chk_validity2(TO_DD,TO_MM,TO_YY,FROM_DD,FROM_MM,FROM_YY)){");
	    out.println("FROM_DD.value=TO_DD.value;");
			out.println("FROM_MM.value=TO_MM.value;");
			out.println("FROM_YY.value=TO_YY.value;");
			out.println("}");
			out.println("}");
			
			out.println("}");
			
			out.println("function validate_date_future_date(objdd,objmm,objyy) {"); 
			out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
			out.println("  checkMonthLength(objdd,objmm,objyy);");
			out.println("  validate_date(objdd,objmm,objyy,document.Form1.HID_SYS_VAL_DAY,document.Form1.HID_SYS_VAL_MONTH,document.Form1.HID_SYS_VAL_YEAR);");
      out.println("}");
			out.println("}");
			
					
			//-------------------------------------------------------------------------------------------------------			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_out_value();load_lock();get_sysdate();disable_help();add_row_cheque();disable_val_date();alloc_other();\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input type='hidden' name='Hid_scr_name' value='AF_RE_SETTELMENT' > ");
			//out.println("<input type='hidden' name='TXT_SCREEN_NAME' value='AF_CR_TERMINATION_CAL' > ");
			//out.println("<INPUT TYPE='Hidden' NAME='hid_count' VALUE=\"\">");
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
			
			out.println("<input type=hidden name=\"HID_SYS_VAL_DAY\" value=\"\">");
			out.println("<input type=hidden name=\"HID_SYS_VAL_MONTH\" value=\"\">");
			out.println("<input type=hidden name=\"HID_SYS_VAL_YEAR\" value=\"\">");
			
			out.println("<input type=hidden name=\"hid_cntract_cnt\" value=\"0\">"); //uncomment by Prabash on 09-02-2012
			//out.println("<input type=hidden name=\"hid_rep_cur\" value=\"\">"); //added by nuwan de silva 
			
			
				
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
			//	out.println("<td style=\"width: 6px\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				//out.println("<td width=10%>&nbsp;</td>");
			//	out.println("<td>&nbsp;</td>");
			//	out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset value=\"New\" class=mainbut onclick=load_screen_status(\"NEW\"); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_out_value();'></td>");//document.Form1.OPTION_DESC.value
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=edit value=\"Edit\" class=mainbut onclick=load_screen_status(\"EDIT\"); onMouseOver='load_roll_value(\"Edit\");' onmouseout='load_roll_out_value();'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=Dele value=\"Delete\" class=mainbut onclick=load_screen_status(\"DELETE\"); onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_out_value();' disabled></td>");
				out.println("<td width=10%>&nbsp;</td>");
				out.println("<td ><input type=button name=b_submit value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_out_value();'></td>");
			  out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=delete value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel value=\"Re-active\" class=mainbut onclick=befor_active(); onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				
				//out.println("<td><input type='button' name='BUT_HELP_MAIN' class=mainbut value=\"Help\" onclick=load_screen_status(\"HELP\"); >  </td>"); 
				out.println("<td><input type='button' name=help class=mainbut value=\"Help\" onclick=load_screen_status(\"HELP\"); >  </td>");  //Added By Nuwan De Silv 17-05-05
				
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Reset\");' onmouseout='load_roll_out_value();'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_out_value();'></td>");
				out.println("<td>&nbsp;&nbsp;&nbsp;</td>");			
				out.println("<td><input type=button name=Doc_1 value=\"Document\" class=mainbut onclick=show_document(); onMouseOver='load_roll_value(\"Document\");' style='width: 130px' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td ><input type=button name=cal value=\"Calculate\" class=mainbut onclick=befor_cal();></td>");
				
				//out.println("<td ><input type=button name=b_submit value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td><input class='but_input' type='button' name='BUT_HELP_MAIN' class=mainbut value=\"Help\" onclick=load_screen_status(\"HELP\"); >  </td>"); 
				//out.println("<td><input type=button name=back value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
      
				out.println("</tr></table>");
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				
				out.println("<table align=\"center\" border=\"0\"  width=\"100%\" class=table>"); //cellpadding=\"0\" cellspacing=\"0\"
				out.println("<tr class=tr_input>");
				out.println("<td width=\"20%\" id=RNO>Receipt No *</td>");
				out.println("<td width=\"30%\"><input name=\"RECEIPT_NO\" type=\"text\" maxlength=\"15\" class=\"txt_input\" onblur=\"makeRequest6(this.value)\"  onchange=check_client() disabled > ");
				out.println("<input type=button name=rec_help value=... class=\"but_input\" onclick=\"receipt_help()\" disabled ></td>");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td >&nbsp;</td>");
				out.println("<td>&nbsp; ");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
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
				//out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td ID=VDATE width=\"20%\">Value Date *</td>");
				out.println("<td width=\"30%\"><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onBlur=validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onBlur=validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onBlur=validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("</td>");
				// comment by nuwan de silva 01-08-07-
				/*out.println("<td><SELECT name=\"RECEIPT_TYPE\" class=\"txt_input\"> ");
				out.println("<OPTION value=\"INV\">Client</OPTION>");
				out.println("<OPTION value=\"TER\">Other</OPTION>");
				out.println("</SELECT></TD>");*/              
				out.println("<td >&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");

			  	out.println("</table>");	    
				
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class='table'>"); 
				out.println("<tr > ");  
				out.println("<td width=\"100%\"><DIV ID='m_table_account_no'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");	 
				

				out.println("<table align='center'  width='100%' border=\"0\" class='table'>"); 				
				out.println("<tr class=tr_input>");
				out.println("<td id=CCODE width=\"20%\">Client Code *</td>");
				out.println("<td width=\"40%\"  ><input name=\"CLIENT_CODE\"   type=\"text\" maxlength=\"10\"  onblur=\"makeRequest5(this.value)\"  class=\"txt_input\" onchange=\"check_client(), inv_details()\"> "); //style=\"{background-color:#CCCCFF}\"
				out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"client_help()\">");
				out.println("<input type=button name=client_det value=\"Client Detail\" class=\"but_input\" style=\"width:90px;\" onclick=\"show_client(document.Form1.CLIENT_CODE.value)\">");
				out.println("<input type=button name=charges value=Charges class=\"but_input\" style=\"width:90px;\" onclick=\"display_charges_pending(document.Form1.CLIENT_CODE.value)\">");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<td>Client Name</td>");
				//out.println("<td> <input name=\"CLIENT_NAME\" type=\"text\" style=\"width:250px;\" maxlength=\"200\" class=\"txt_input\" disabled></td>");
				out.println("<td >&nbsp;</td>");
				//out.println("<td >&nbsp;</td> ");
				out.println("<td ><DIV ID='m_table_client_comment'></DIV></td>");				
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");	
				out.println("</table>");	
				
				
				//adderess added by nuwan de silva 25-07-07----------------------
				out.println("<table align='center'  width='100%' border=\"0\" class='table'>"); 
				out.println("<tr class=tr_input>");
				out.println("<td width=\"20%\" >Client Name</td>");
				out.println("<td width=\"30%\" ><input name=\"CLIENT_NAME\" type=\"text\" style=\"width:250px;\" maxlength=\"200\" class=\"txt_input\" disabled></td>");
				out.println("<td  >Client Address</td>");
				out.println("<td> <input name=\"CLIENT_ADDRESS\" type=\"text\" style=\"width:350px;\" maxlength=\"200\" class=\"txt_input\" disabled>");
				out.println("</td>");
				out.println("</tr>");		
				out.println("</table>");	
				
				
				out.println("<table align=\"center\" width=\"100%\" class='table'>"); 
				out.println("<tr>");
				out.println("<td width=\"100%\"><DIV ID='m_table_client_status'></DIV></td>");				
				out.println("</tr>");		
				out.println("</table>");
				
				
				//added by nuwan de silva 01-08-7----------------------------------------------
				out.println("<table align='center'  width='100%' border=\"0\" class='table'>");		
				out.println("<tr class=tr_input>");
				out.println("<td width=\"20%\">Payee Type</td>");
				out.println("<td width=\"30%\"><SELECT name=\"PAY_TYPE\" class=\"txt_input\" onChange=\" display_row_third_party(), set_address_pay_type()\" > ");
				out.println("<OPTION value=\"CLIENT\">Client</OPTION>");
				out.println("<OPTION value=\"THIRD\">Third Party</OPTION>");
				out.println("</SELECT></TD>");
				out.println("<td >&nbsp;</td>");
				out.println("<td >&nbsp;</td> ");
				out.println("</tr>");
				
				//-------- Added by Chandana on 30/08/2007 -----------------// 
				out.println("</table>");	   

				out.println("<table align=\"center\" width=\"100%\" class='table'>"); 
			  out.println("<tr > ");  
			  out.println("<td width=\"100%\"><DIV ID='m_table_third_party_del'></DIV></td>"); 
		    out.println("</tr>"); 
			  out.println("</table>");
				
				/* Added by Chandana on 01/10/2007 */
		    out.println("<table align=\"center\" width=\"100%\" class='table'>"); 
			  out.println("<tr > ");  
			  out.println("<td width=\"100%\"><DIV ID='m_table_cheque'></DIV></td>"); 
		    out.println("</tr>"); 
			  out.println("</table>");	
		    /* End By Chandana on 01/10/2007 */
				
				out.println("<table align='center'  width='100%' class='table'>");
				//-------------- End on 30/08/2007 -------------------//
				
			/*	out.println("<tr class=tr_input>");
				out.println("<td width=\"20%\" ><div id=pay_name>Client Name</div></td>");
				out.println("<td width=\"30%\"><input name=\"CLIENT_NAME_1\" type=\"text\" style=\"width:250px;\" maxlength=\"200\" class=\"txt_input\" disabled></td>");
				out.println("<td ><div id=pay_address>Client Address</div></td>");
				out.println("<td> <input name=\"CLIENT_ADDRESS_1\" type=\"text\" style=\"width:350px;\" maxlength=\"200\" class=\"txt_input\" disabled>");
				out.println("</td>");
				out.println("</tr>");		
                                */
				//===============================================================================
				
			  
				/*out.println("<table align=\"center\" width=\"100%\" class='table'>"); 
			  out.println("<tr > ");  
			  out.println("<td width=\"100%\"><DIV ID='m_table_account_no'></DIV></td>");
		    out.println("</tr>"); 
			  out.println("</table>");	 
				*/
		//		out.println("<table align='center'  width='100%' class='table'>"); 			
		
				out.println("<tr class=tr_input>");
				out.println("<td ID=CURR width=\"20%\">Currency </td>");
				out.println("<td width=\"30%\"><SELECT onchange=get_excharate() name=CURR_CODE class=\"txt_input\" > ");
				rs = stmt.executeQuery ("SELECT CURR_CODE, CURR_SYMBOL, REP_CURR "+
				                        "FROM   "+m_schema_name+".AF_CO_MAS_CURRENCY "+
																"WHERE ACTIVE_STATUS='Y' "+ //added by nuwan de silva 23-07-07
																"ORDER  BY DEFAULT_VALUE DESC ");
				while(rs.next()){
				
				if(rs.getString(3).equals("Y"))
				{
				
				//out.println("<input type=hidden name=hid_rep_cur value="+rs.getString(2)+">");
				m_rep_cur=rs.getString(2);
				}
				out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</OPTION>");
				}
				//out.println("assd"+m_rep_cur);
				out.println("<input type=hidden name=hid_rep_cur value="+m_rep_cur+">");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				
				
				out.println("<tr class=tr_input>");
				//out.println("<td ID=OTH_CHARGE width=\"20%\">Receipt Amount Include Other Charges </td>"); // commented by udara on 08-05-2012
				out.println("<td ID=OTH_CHARGE width=\"20%\">Insurance Charges </td>"); // added by udara on 08-05-2012
				out.println("<td width=\"30%\"><SELECT  onchange=get_other_charges() name=OTHER_CHARGES class=\"txt_input\" > ");
				out.println("<OPTION value=\"N\" selected >No</OPTION>");
				out.println("<OPTION value=\"Y\">Yes</OPTION>");

				out.println("</SELECT></TD>");
				//out.println("</td>");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				
				out.println("</tr>");
				
				
				
				out.println("<tr class=tr_input>");
				out.println("<td ID=AMOU width=\"20%\">Amount *</td>"); 
				out.println("<td width=\"30%\"><input name=\"AMOUNT\" type=\"text\" maxlength=\"20\" class=\"txt_input\" onblur=cal_rep_amount(document.Form1.AMOUNT,25) STYLE=\"{text-align:right;}\">");
				//out.println("<td width=\"30%\"><input name=\"AMOUNT\" type=\"text\" maxlength=\"20\" class=\"txt_input\" onchange=get_contract(document.Form1.AMOUNT) STYLE=\"{text-align:right;}\">");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>"); 
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				
				
				
				out.println("<tr class=tr_input>");
				out.println("<td id=EXCH width=\"20%\">Exchange Rate *</td>");
				out.println("<td width=\"30%\"><input name=\"EXCHANE_RATE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" onchange=cal_rep_amount(document.Form1.AMOUNT,25) STYLE=\"{text-align:right;}\" disabled >");
				out.println("</td>");
				out.println("<td ></td>");
				out.println("<td> ");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td ID=RAMO width=\"20%\" >Rep. Curr. Amount *</td>");
				out.println("<td width=\"30%\" > <input name=\"REP_AMOUNT\"   type=\"text\" maxlength=\"25\"  class=\"txt_input\" onchange=cal_exc_rate(document.Form1.REP_AMOUNT.value) STYLE=\"{text-align:right;}\" disabled > ");
				out.println("</td>");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("</table>");	   
				
				/*out.println("<table align=\"center\" width=\"100%\" class='table'>"); 
			  out.println("<tr > ");  
			  out.println("<td width=\"100%\"><DIV ID='m_table_other_charges'></DIV></td>");
		    out.println("</tr>"); 
			  out.println("</table>");	
				*/
				
				out.println("<table align=\"center\" width=\"100%\" class='table'>"); 
			  out.println("<tr > ");  
			  out.println("<td width=\"100%\"><DIV ID='m_table_tendered'></DIV></td>");
		    out.println("</tr>"); 
			  out.println("</table>");	
				
				
 
				//out.println("<table align='center'  width='100%' class='table'>"); 				
				
				
				//----------------------------------------------------------------------------------
				//--date			: (2007-03-05)--------------------------------------------------------------------
				//--modified  :delanjali
				
		/*		out.println("<tr class=tr_input>");
				out.println("<td  width=\"20%\">Tender Amount *</td>");
				out.println("<td width=\"30%\"><input name=\"TEN_AMOUNT\" type=\"text\" maxlength=\"29\" class=\"txt_input\" onblur=cal_tenamt(document.Form1.TEN_AMOUNT) STYLE=\"{text-align:right;}\">");
				out.println("</td>");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td  width=\"20%\">Return Amount *</td>");
				out.println("<td width=\"30%\"><input name=\"RET_AMOUNT\" type=\"text\" maxlength=\"29\" class=\"txt_input\" onblur=cal_retamt(document.Form1.RET_AMOUNT) STYLE=\"{text-align:right;}\">");
				out.println("</td>");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
	*/
				//-----------------------------------------------------------------------------------

			/*	out.println("<tr class=tr_input>");
				out.println("<td >Settlement Mode</td>");
				out.println("<td><SELECT name=\"SETT_MODE\" class=\"txt_input\" onChange=\"enable_check_date()\"> ");
				out.println("<OPTION value=\"CASH\">Cash</OPTION>");
				out.println("<OPTION value=\"CHEQUE\">Cheque</OPTION>");
				out.println("<OPTION value=\"BANK_TRA\">Bank Transfer</OPTION>");
				out.println("</SELECT></TD>");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				*/
			/*	out.println("<tr class=tr_input>");
				out.println("<td ID=PACC width=\"20%\" >Payer Account </td>");//modified by nuwan de silva 23-07-07
				out.println("<td width=\"30%\"><input name=\"PAY_ACCOUNT\"   type=\"text\" maxlength=\"20\" onblur=\"makeRequest4(this.value)\"  class=\"txt_input\"  > ");
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
				
				//------Added by Chandana on 22/05/2007 for Ref No.45 -------------------//
   			out.println("<tr class=tr_input>");
				out.println("<td  width=\"20%\">Payer Branch Name</td>");
				out.println("<td width=\"40%\" > <input name=\"PAY_BRANCH_NAME\"   type=\"text\" maxlength=\"100\"  onblur=\"\" class=\"txt_input\" style=\"width: 200px\" > ");
				out.println("</td>");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				*/
				
				out.println("<table align='center'  width='100%' class='table'>"); 				
			  out.println("<tr class=tr_input>");
				out.println("<td width=\"20%\" valign='top'>Remark</td>");
				//out.println("<td width=\"30%\"><input name=\"REMARK\"   type=\"text\" maxlength=\"100\"   style=\"width: 300px\" class=\"txt_input\" > "); //comment by nuwan de silva on 27-08-07
				out.println("<td width='30%' ><TEXTAREA class='txt_input' name='REMARK' style=\"width:280px; height:50px;\" maxlength='500' size='500' onkeyPress=\"chk_comment_length(this)\"  onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\" ></TEXTAREA></td>"); 
				out.println("</td>");
				out.println("<td width=\"*%\"></td>");
				out.println("</tr>");
				out.println("</table>");
				
				
				out.println("<table align='center'  width='100%' class='table'>"); //Added by Chandana on 19/10/2007				
			  out.println("<tr class=tr_input>");
				out.println("<td width=\"20%\" valign='top'>Allocation Method</td>");
				out.println("<td width='30%' ><SELECT name=\"TXT_FIFO\" class=\"txt_input\" onChange=\"alloc_other()\">"); 
				//out.println("<OPTION value=\"FIFO_AUTO\" >&nbsp&nbsp  Auto Allocation</OPTION>");
				out.println("<OPTION value=\"FIFO_MANU\" >&nbsp&nbsp  Manual Allocation</OPTION>");
				out.println("<OPTION value=\"NO_ALLO\"  SELECTED >&nbsp&nbsp  No Allocation</OPTION>");

				out.println("</SELECT>");
				out.println("<input type=button name=Allocate value=Allocate class=\"but_input\" style=\"width:90px;\" onclick=\"Change_Allo_Mode(document.Form1.TXT_FIFO.value)\">");
				out.println("</td>");
				out.println("<td width=\"*%\"></td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<table align=\"center\" width=\"100%\" class='table'>"); 
			  out.println("<tr > ");  
			  out.println("<td width=\"100%\"><DIV ID='m_table_other_charges'></DIV></td>");
		    	out.println("</tr>"); 
			  out.println("</table>");
				
				out.println("<table align='center'  width='100%' class='table'>"); 				
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<div id=invoice_details></div>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				
				
				out.println("<table align='center'  width='100%' class='table'>"); 				
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<div id=return_rec >");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				
				
				
				out.println("<table align='center'  width='100%' class='table'>"); 				
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<div id=contract>"); 
				//out.println("<div id=inv>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				//out.println("<div id=contract>"); 
				out.println("<div id=inv>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<div id=fifo_aloc>"); 
				//out.println("<div id=inv>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("</table>");
				
				/*
				out.println("<table align='center'  width='100%' class='table'>"); 				
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				//out.println("<div id=contract>"); 
				out.println("<div id=inv>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				
				
								
				out.println("<table align='center'  width='100%' class='table'>"); 				
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<div id=fifo_aloc>"); 
				//out.println("<div id=inv>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>"); */
				

				
				
	
			//  out.println("<table align='center'  width='100%' class='table'>"); 				
			//	out.println("<tr class=tr_input>");
				//out.println("<td class=\"line\" height=\"1\">");
			//	out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
			//	out.println("</tr>");
				//out.println("<tr class=tr_input>");
				//out.println("<td class=\"pdn_txtpos\" style=\"height: 10px\">");
				/*out.println("<table cellpadding=\"2\" cellspacing=\"2\" border=\"0\" class=table>");
				out.println("<tr class=tr_input>");
				out.println("<td style=\"width: 6px\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td width=10%>&nbsp;</td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=new_1 value=\"New\" class=mainbut onclick=load_screen_status(\"NEW\"); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");//document.Form1.OPTION_DESC.value
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=edit_1 value=\"Edit\" class=mainbut onclick=load_screen_status(\"EDIT\"); onMouseOver='load_roll_value(\"Edit\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=Dele_1 value=\"Delete\" class=mainbut onclick=load_screen_status(\"DELETE\"); onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td width=10%>&nbsp;</td>");

				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back_1 value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=b_submit_1 value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset_1 value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Reset\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("</tr></table>");
				*/
				//out.println("<table border=\"0\" cellpadding=\"0\" class=table cellspacing=\"0\" height=\"100%\" width=\"100%\">");
				//out.println("<tr>");
				//out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				//out.println("</tr></table>");				
				
				out.println("<table cellpadding=\"2\" cellspacing=\"2\" border=\"0\" class=table>");
				out.println("<tr>");
				out.println("<td><input type=button name=new_1 value=\"New\" class=mainbut onclick=load_screen_status(\"NEW\"); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_out_value();'></td>");//document.Form1.OPTION_DESC.value
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=Dele_1 value=\"Delete\" class=mainbut onclick=load_screen_status(\"DELETE\"); onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_out_value();' disabled></td>");
				out.println("<td width=10%>&nbsp;</td>");
				out.println("<td ><input type=button name=b_submit1 value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_out_value();'></td>");
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
				
				//out.println("&nbsp;&nbsp;</td>");
				//out.println("</tr>");

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
			
			
			
			
	else if(m_chksql.trim().equals("get_inv_details")){
			
			String m_client    = req.getParameter("Client_Code");
			double m_invoice_amt =0.00;
			double m_rental_amt  =0.00;
			double m_rental_amt_future  =0.00;
			double m_odi_amt     =0.00;
			double m_other_amt   =0.00;
			double m_other_amt_future   =0.00;
			double m_total_amt   =0.00;
			double m_total_amt_future =0.00;
			double m_odi_amt_future=0.00;
			String m_sys_date="";
			
			rs1 = stmt1.executeQuery (" SELECT  SUM(TOTAL_AMOUNT),TO_CHAR(SYSDATE,'DD-MM-YYYY') "+
			      " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
						" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
						" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
						" A.FINANCE_NO = B.FINANCE_NO ");
			
			if(rs1.next()){
			 m_invoice_amt = rs1.getDouble(1);			
			 m_sys_date = rs1.getString(2);			
			}
			
			
		 rs1 = stmt1.executeQuery (" SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
			      " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
						" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
						" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
						" A.FINANCE_NO = B.FINANCE_NO AND "+
						" B.VALUE_DATE <=SYSDATE  AND "+
						" B.ACTIVE_STATUS='Y' AND "+
						" B.INVOICE_TYPE = 'INV_GENER' ");
			
			if(rs1.next()){
			 m_rental_amt = rs1.getDouble(1);			
			}
			
			rs1 = stmt1.executeQuery (" SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
			      " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
						" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
						" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
						" A.FINANCE_NO = B.FINANCE_NO AND "+
						" B.VALUE_DATE >SYSDATE  AND "+
						" B.ACTIVE_STATUS='Y' AND "+
						" B.INVOICE_TYPE = 'INV_GENER' ");
			
			if(rs1.next()){
			 m_rental_amt_future = rs1.getDouble(1);			
			}
			
			
		
		 rs1 = stmt1.executeQuery (" SELECT  SUM(ODI_BAL_AMOUNT) "+
			     " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					 " "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
					 " "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
					 " WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					 " A.FINANCE_NO = B.FINANCE_NO AND "+
					 " C.odi_date <=SYSDATE AND "+
						" B.ACTIVE_STATUS='Y' AND "+
					 " B.INVOICE_NO = C.INVOICE_NO ");
			
			if(rs1.next()){
			 m_odi_amt = rs1.getDouble(1);			
			}
			
			rs1 = stmt1.executeQuery (" SELECT  SUM(ODI_BAL_AMOUNT) "+
			     " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					 " "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
					 " "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
					 " WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					 " A.FINANCE_NO = B.FINANCE_NO AND "+
					 " C.odi_date >SYSDATE AND "+
						" B.ACTIVE_STATUS='Y' AND "+
					 " B.INVOICE_NO = C.INVOICE_NO ");
			
			if(rs1.next()){
			 m_odi_amt_future = rs1.getDouble(1);			
			}
		
		
		
		rs1 = stmt1.executeQuery ("  SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
		      " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" B.VALUE_DATE <=SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_TYPE <> 'INV_GENER' ");
			
			if(rs1.next()){
			 m_other_amt = rs1.getDouble(1);			
			}
			
			rs1 = stmt1.executeQuery ("  SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
		      " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" B.VALUE_DATE >SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_TYPE <> 'INV_GENER' ");
			
			if(rs1.next()){
			 m_other_amt_future = rs1.getDouble(1);			
			}
		  
			//added by sh on 23-11-2010 
			double m_new_odi=0;
			double m_old_odi=0;
			rs1 = stmt1.executeQuery (" SELECT  SUM(ODI_BAL_AMOUNT) "+
			     " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					 " "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
					 " "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
					 " WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					 " A.FINANCE_NO = B.FINANCE_NO AND "+
					 " C.odi_date <=SYSDATE AND "+
						"C.ODI_DATE>=(SELECT ODI_ALLO_DATE FROM   "+m_schema_name+".AF_CO_MAS_ODI_DATE) AND "+
						" B.ACTIVE_STATUS='Y' AND "+
					 " B.INVOICE_NO = C.INVOICE_NO ");
			
			if(rs1.next()){
			 m_new_odi = rs1.getDouble(1);
			 m_old_odi = m_odi_amt-m_new_odi;
			 if (m_old_odi<0){
				 m_old_odi=0;
			 }	
			}
		  //end of addition
			/*out.println("<table border='0'>");
			out.println("  <tr WIDTH=100%>");
			out.println("  <td WIDTH=*%><b>Amount Invoice </td>");
			out.println("  </tr>");
			out.println("</table>");
		  out.println("  <hr>");
			out.println("<table border='0'>");
			out.println("  <tr WIDTH=100%>");
			out.println("  <td WIDTH=45%><b>Rental Invoice </td>");
			out.println("  <td WIDTH=15% align=right><b></td>");
			out.println("  <td WIDTH=25% align=right><b>"+nf.format(m_rental_amt)+"</td>");
			out.println("  <td WIDTH=15% align=right><b></td>");
			out.println("  </tr>");
			out.println("  <tr WIDTH=100%>");
			out.println("  <td WIDTH=45%><b>OD Invoice </td>");
			out.println("  <td WIDTH=15% align=right><b></td>");
			out.println("  <td WIDTH=25% align=right><b>"+nf.format(m_odi_amt)+"</td>");
			out.println("  <td WIDTH=15% align=right><b></td>");
			out.println("  </tr>");
						out.println("  <tr WIDTH=100%>");
			out.println("  <td WIDTH=45%><b>Other Invoice </td>");
			out.println("  <td WIDTH=15% align=right><b></td>");
			out.println("  <td WIDTH=25% align=right><b>"+nf.format(m_other_amt)+"</td>");
			out.println("  <td WIDTH=15% align=right><b></td>");
			out.println("  </tr>");
			
			m_total_amt = (m_rental_amt+m_odi_amt+m_other_amt);
			
									out.println("  <tr WIDTH=100%>");
			out.println("  <td WIDTH=45%><b>Total Outstanding</td>");
			out.println("  <td WIDTH=15% align=right><b></td>");
			out.println("  <td WIDTH=25% align=right><b>"+nf.format(m_total_amt)+"</td>");
			out.println("  <td WIDTH=15% align=right><b></td>");
			out.println("  </tr>");
		  out.println("</table>");							
			
			*/
			
			out.println("<table border='0'>");
			out.println("  <tr WIDTH=100%>");
			out.println("  <td WIDTH=*%><b>Amount Invoice </td>");
			out.println("  </tr>");
			out.println("</table>");
		  out.println("  <hr>");
			out.println("<table border='0' WIDTH=60% class='table' >");
			out.println("  <tr >");
			out.println("  <td WIDTH=15% ><b>&nbsp;</td>");
			out.println("  <td WIDTH=15% bgcolor=\"#CCCCCC\" align=right><b>Arr as at - "+m_sys_date+"</td>");
			out.println("  <td WIDTH=15% bgcolor=\"#CCCCCC\" align=right><b>Next Due</td>");
			out.println("  <td WIDTH=15% bgcolor=\"#CCCCCC\" align=right><b>Total</td>");
			out.println("  </tr>");
						
			out.println("  <tr >");
			out.println("  <td WIDTH=15% bgcolor=\"#FFFF00\" ><b>Rental Invoice </td>");
			out.println("  <td WIDTH=15% bgcolor=\"#CC66FF\" align=right><b>"+nf.format(m_rental_amt)+"</td>");
			out.println("  <td WIDTH=15% bgcolor=\"#CC99FF\" align=right><b>"+nf.format(m_rental_amt_future)+"</td>");
			out.println("  <td WIDTH=15% bgcolor=\"#CCCCFF\" align=right><b>"+nf.format(m_rental_amt+m_rental_amt_future)+"</td>");
			out.println("  </tr>");
			
			out.println("  <tr >");
			out.println("  <td WIDTH=15% bgcolor=\"#FFFF00\"><b>OD Invoice </td>");
			out.println("  <td WIDTH=15% bgcolor=\"#CC66FF\" align=right><b>"+nf.format(m_odi_amt)+"</td>");
			out.println("  <td WIDTH=15% bgcolor=\"#CC99FF\" align=right><b>"+nf.format(m_odi_amt_future)+"</td>");
			out.println("  <td WIDTH=15% bgcolor=\"#CCCCFF\" align=right><b>"+nf.format(m_odi_amt+m_odi_amt_future)+"</td>");
			out.println("  </tr>");
			
			out.println("  <tr >");
			out.println("  <td WIDTH=15% bgcolor=\"#FFFF00\" ><b>Other Invoice </td>");
			out.println("  <td WIDTH=15% bgcolor=\"#CC66FF\" align=right><b>"+nf.format(m_other_amt)+"</td>");
			out.println("  <td WIDTH=15% bgcolor=\"#CC99FF\" align=right><b>"+nf.format(m_other_amt_future)+"</td>");
			out.println("  <td WIDTH=15% bgcolor=\"#CCCCFF\" align=right><b>"+nf.format(m_other_amt+m_other_amt_future)+"</td>");
			out.println("  </tr>");
			
			m_total_amt = (m_rental_amt+m_odi_amt+m_other_amt);
			m_total_amt_future = (m_rental_amt_future+m_odi_amt_future+m_other_amt_future);
			
			out.println("  <tr >");
			out.println("  <td WIDTH=15% bgcolor=\"#CCCCCC\" ><b>Total Outstanding</td>");
			out.println("  <td WIDTH=15% bgcolor=\"#CCCCCC\" align=right><b>"+nf.format(m_total_amt)+"</td>");
			out.println("  <td WIDTH=15% bgcolor=\"#CCCCCC\" align=right><b>"+nf.format(m_total_amt_future)+"</td>");
			out.println("  <td WIDTH=15% bgcolor=\"#CCCCCC\" align=right><b>"+nf.format(m_total_amt+m_total_amt_future)+"</td>");
			out.println("  </tr>");
			
			//added by sh on 23-11-2010
			out.println("  <tr >");
			out.println("  <td WIDTH=15% bgcolor=\"#FFFFFF\" ><b>New ODI</td>");
			out.println("  <td WIDTH=15% bgcolor=\"#FFFFFF\" align=right><b>"+nf.format(m_new_odi)+"</td>");
			out.println("  <td WIDTH=15% bgcolor=\"#FFFFFF\" ><b>OLD ODI</td>");
			out.println("  <td WIDTH=15% bgcolor=\"#FFFFFF\" align=right><b>"+nf.format(m_old_odi)+"</td>");
			out.println("  </tr>");
			
			
		out.println("</table>");							

			
	}		
			
	else if(m_chksql.trim().equals("get_contract_det")){
			
			String m_client    = req.getParameter("Client_Code");
			double m_rec_bal   = new Double(req.getParameter("Amount")).doubleValue();
			double m_othr_rec_bal   = new Double(req.getParameter("Other_Amount")).doubleValue();
			String m_type      = req.getParameter("Type");

			
			
			
			
			double m_rec_tot   = 0;
			double m_rec_tot2   = 0; 
			double m_rec_bal2   = 0;
			double m_tot_amt = 0; //added by nuwan de silva

			String m_finance_num ="";
			
			if(m_type.equals("NEW")){
									rs1 = stmt1.executeQuery (" SELECT FINANCE_NO, "+
									      " SUM(INV_AMOUNT), "+
												" SUM(INV_BALANCE_AMOUNT), "+
												" SUM(ODI_AMOUNT), "+
												" SUM(ODI_BAL_AMOUNT), "+
												" NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') "+
												" FROM "+
												" ((SELECT FINANCE_NO, "+
												" SUM(TOTAL_AMOUNT_CURR) INV_AMOUNT, "+
												" SUM(BALANCE_TO_BE_RECEIVED)  INV_BALANCE_AMOUNT, "+
												" 0 ODI_AMOUNT, "+
												" 0 ODI_BAL_AMOUNT "+
												" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A, "+
												" "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
												" WHERE CLIENT_CODE='"+m_client+"' AND "+
												" BALANCE_TO_BE_RECEIVED>0 AND "+
												" A.INVOICE_TYPE = B.INVOICE_TYPE_CODE AND "+
												//" A.ACTIVE_STATUS <> 'C'  "+
												" A.ACTIVE_STATUS = 'Y'  "+
												" AND A.INVOICE_TYPE IN ('INV_GENER','ODI') "+
												" GROUP BY FINANCE_NO ) "+
												
												" UNION ALL "+
												" (SELECT a.FIN_NO FINANCE_NO, "+
												" 0 INV_AMOUNT, "+
												" 0 INV_BALANCE_AMOUNT, "+
												" SUM(ODI_CAL_AMOUNT) ODI_AMOUNT, "+
												" SUM(ODI_BAL_AMOUNT) ODI_BAL_AMOUNT "+
												" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A, "+
												" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
												" WHERE  CLIENT_CODE='"+m_client+"' AND "+
												" A.INVOICE_NO=B.INVOICE_NO AND "+
												" ODI_BAL_AMOUNT>0 "+
												" GROUP BY a.FIN_NO )) "+
												" GROUP BY  FINANCE_NO ");     
			
			
			  boolean more1 = rs1.next();	
								 int i = 0;
								 int k = 0;
									
									double m_inv_bal = 0;
									double m_odi_bal = 0;
									double m_tot_bal = 0;
									
									double m_inv_bal2 = 0;
									double m_tot_val2 = 0;
									
									double m_inv_val = 0;
									double m_odi_val = 0;
                  double m_tot_val = 0;
									//double m_tot_amt = 0;
                  
									
									
									
								 if(more1) {	
								  out.println("<table border='0'><tr class=pdn_txtpos2 WIDTH=100%>");
									out.println("  <td WIDTH=15%>Finance No</td>");
									out.println("  <td WIDTH=15%>Vehicle Reg. No</td>");
									out.println("  <td WIDTH=12% align=right>ODI Balance Amount</td>");
									out.println("  <td WIDTH=12% align=right>Invoice Balance Amount</td>");
									out.println("  <td WIDTH=12% align=right>ODI Allocated Amount</td>");
									out.println("  <td WIDTH=12% align=right>Invoice Allocated Amount</td>");
									out.println("  <td WIDTH=12% align=right>Balance Receipt Amount</td>");
									out.println("  <td WIDTH=10% align=center>Status</td></tr>");
									
									/*double m_inv_bal = 0;
									double m_odi_bal = 0;
									double m_tot_bal = 0;
									
									double m_inv_bal2 = 0;
									double m_tot_val2 = 0;
									
									double m_inv_val = 0;
									double m_odi_val = 0;
                  double m_tot_val = 0;
									double m_tot_amt = 0;
									*/
									
									 }
								 while(more1) {
							    m_finance_num = rs1.getString(1);
									out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"ALLO_NO1_"+i+"\">");
									out.println("  <input type=hidden NAME=\"hid_finance_no_"+i+"\"  value=\""+rs1.getString(1)+"\"> ");
									out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\" ><u>"+rs1.getString(1)+"</u></td>"); 
									out.println("  <td align=left  onClick=\"\" >"+rs1.getString(6)+"</td>");
									out.println("  <td align=right ><input type=text name=\"ODI_VAL_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(5))+"\" class=\"txt_input2\" style=\"width: 100px\"><input type=hidden name=\"hid_odi_"+i+"\" value="+rs1.getDouble(5)+"></td>");
									out.println("  <td align=right ><input type=text name=\"INV_VAL_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(3))+"\" class=\"txt_input2\"><input type=hidden name=\"hid_inv_"+i+"\" value="+rs1.getDouble(3)+">");
									//out.println("  <td align=right ><input type=text name=\"D_DATE_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(5))+"\" class=\"txt_input2\"></td>");
									//out.println("  <td align=right><input type=text name=\"INV_AM_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(3))+"\" class=\"txt_input2\"></td>");
									//out.println("  <td align=right><input type=text name=\"BAL_AM_"+i+"\" disabled value=\"-\" class=\"txt_input2\">");
									out.println("  <input type=hidden name=\"allo_no1_"+i+"\" value=\"\"><input type=hidden name=\"hid_main_cnt_"+i+"\" value=\""+i+"\"></td>");
									
									m_inv_val=rs1.getDouble(3);
									m_odi_val=rs1.getDouble(5);
									
									
									
									m_inv_bal  = m_inv_bal+rs1.getDouble(3); 
									m_odi_bal  = m_odi_bal+rs1.getDouble(5); //zzzzzzzzz
									m_tot_bal=m_inv_bal+m_odi_bal;
									
									if((m_rec_bal - m_tot_amt) > (m_inv_val + m_odi_val)) {
									   out.println("<td align=right><input type=text name=\"Text_odi_sett_amount"+i+"\" value=\""+nf.format(m_odi_val)+"\" class=\"txt_input2\" onBlur=\"check_aloc_amt(document.Form1.hid_odi_"+i+","+m_odi_val+" ,'ODI',"+i+"),format_number(document.Form1.Text_odi_sett_amount"+i+",30)\"   disabled></td>"); //disabled
									   out.println("<td align=right><input type=text name=\"Text_inv_sett_amount"+i+"\" value=\""+nf.format(m_inv_val)+"\" class=\"txt_input2\" onBlur=\"check_aloc_amt(document.Form1.hid_inv_"+i+","+m_inv_val+" ,'INV',"+i+"),format_number(document.Form1.Text_inv_sett_amount"+i+",30)\"  disabled></td>"); //disabled
										  m_tot_val2= (m_inv_val + m_odi_val);
											m_tot_amt = m_tot_amt + (m_inv_val + m_odi_val);	
										 												
										 	if((m_rec_bal - m_tot_amt)>0){
											out.println("<td align=right><input type=text name=\"Text_balance_amount"+i+"\" value=\""+nf.format((m_rec_bal - m_tot_amt))+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
											}else{
											out.println("<td align=right><input type=text name=\"Text_balance_amount"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
											}
											out.println("<td align=center><INPUT TYPE=\"checkbox\" name=\"Text_standard_chk"+i+"\" onclick=check_aloc_status_new(\""+i+"\")  value=\"YES\" checked></td>"); //check_aloc_status(\""+i+"\")
									   								
									}else{
									  // out.println("2222====>"+(m_rec_bal - m_tot_amt));
										 if((m_rec_bal - m_tot_amt)>0){												
										   if((m_rec_bal - m_tot_amt)>m_odi_val){
										     out.println("<td align=right><input type=text name=\"Text_odi_sett_amount"+i+"\" value=\""+nf.format(m_odi_val)+"\" class=\"txt_input2\" onBlur=\"check_aloc_amt(document.Form1.hid_odi_"+i+","+m_odi_val+",'ODI',"+i+"),format_number(document.Form1.Text_odi_sett_amount"+i+",30)\"  disabled></td>"); //disabled
									       out.println("<td align=right><input type=text name=\"Text_inv_sett_amount"+i+"\" value=\""+nf.format(((m_rec_bal - m_tot_amt) - m_odi_val))+"\" class=\"txt_input2\" onBlur=\"check_aloc_amt(document.Form1.hid_inv_"+i+","+((m_rec_bal - m_tot_amt) - m_odi_val)+",'INV',"+i+"),format_number(document.Form1.Text_inv_sett_amount"+i+",30)\" disabled ></td>"); //disabled
									        m_tot_val2= m_odi_val + ((m_rec_bal - m_tot_amt) - m_odi_val);
													m_tot_amt = m_tot_amt + (m_inv_val + m_odi_val);	
												 												
											   if((m_rec_bal - m_tot_amt)>0){
											   out.println("<td align=right><input type=text name=\"Text_balance_amount"+i+"\" value=\""+nf.format((m_rec_bal - m_tot_amt))+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
											   }else{
											   out.println("<td align=right><input type=text name=\"Text_balance_amount"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
											   }												
												
												out.println("<td align=center><INPUT TYPE=\"checkbox\" name=\"Text_standard_chk"+i+"\"  onclick=check_aloc_status_new(\""+i+"\")  value=\"YES\" checked></td>"); //check_aloc_status(\""+i+"\")
									           
										   }else{
										    out.println("<td align=right><input type=text name=\"Text_odi_sett_amount"+i+"\" value=\""+nf.format((m_rec_bal - m_tot_amt))+"\" class=\"txt_input2\" onBlur=\"check_aloc_amt(document.Form1.hid_odi_"+i+","+(m_rec_bal - m_tot_amt)+",'ODI',"+i+"),format_number(document.Form1.Text_odi_sett_amount"+i+",30)\"  disabled></td>"); //disabled
									      out.println("<td align=right><input type=text name=\"Text_inv_sett_amount"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"check_aloc_amt(document.Form1.hid_inv_"+i+","+(0.00)+",'INV',"+i+"),format_number(document.Form1.Text_inv_sett_amount"+i+",'30')\"  disabled ></td>"); //disabled
									        m_tot_val2= (m_rec_bal - m_tot_amt);
													m_tot_amt = m_tot_amt + (m_rec_bal - m_tot_amt);
												 	
												
												if((m_rec_bal - m_tot_amt)>0){
											  out.println("<td align=right><input type=text name=\"Text_balance_amount"+i+"\" value=\""+nf.format((m_rec_bal - m_tot_amt))+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
											  }else{
											  out.println("<td align=right><input type=text name=\"Text_balance_amount"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
											  }	
												
												out.println("<td align=center><INPUT TYPE=\"checkbox\" name=\"Text_standard_chk"+i+"\"  onclick=check_aloc_status_new(\""+i+"\")  value=\"YES\" checked></td>"); //check_aloc_status(\""+i+"\")
	                     
											}					
										}else{
										   out.println("<td align=right><input type=text name=\"Text_odi_sett_amount"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"check_aloc_amt(document.Form1.hid_odi_"+i+","+(0.00)+",'ODI',"+i+"),format_number(document.Form1.Text_odi_sett_amount"+i+",30)\"  disabled ></td>"); //disabled
									     out.println("<td align=right><input type=text name=\"Text_inv_sett_amount"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"check_aloc_amt(document.Form1.hid_inv_"+i+","+(0.00)+",'INV',"+i+"),format_number(document.Form1.Text_inv_sett_amount"+i+",30)\"  disabled ></td>"); //disabled
									     m_tot_amt = m_tot_amt + 0.00;
											 m_tot_val2=0.00;
												
											 if((m_rec_bal - m_tot_amt)>0){
											out.println("<td align=right><input type=text name=\"Text_balance_amount"+i+"\" value=\""+nf.format((m_rec_bal - m_tot_amt))+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
											}else{
											out.println("<td align=right><input type=text name=\"Text_balance_amount"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
											}	
											
											out.println("<td align=center><INPUT TYPE=\"checkbox\" name=\"Text_standard_chk"+i+"\" onclick=check_aloc_status_new(\""+i+"\")  value=\"NO\" ></td>"); //check_aloc_status(\""+i+"\")
	                   									
										}
									 }
																										
								   out.println("  </tr>");

			
			          i = i+1;
								more1 = rs1.next();
									
								 }
									
								out.println("<input type=hidden name=hid_cntract_cnt  value="+i+"></table>");
									
									
									
								rs1 = stmt1.executeQuery ("	SELECT FINANCE_NO, "+
								                          " SUM(INV_AMOUNT), "+
																					" SUM(INV_BALANCE_AMOUNT), "+
																					" SUM(ODI_AMOUNT), "+
																					" SUM(ODI_BAL_AMOUNT), "+
																					" NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') "+
																					" FROM ((SELECT FINANCE_NO, "+
																					" SUM(TOTAL_AMOUNT_CURR) INV_AMOUNT, "+
																					" SUM(BALANCE_TO_BE_RECEIVED)  INV_BALANCE_AMOUNT, "+
																					" 0 ODI_AMOUNT, "+
																					" 0 ODI_BAL_AMOUNT "+
																					" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A "+
																					" WHERE CLIENT_CODE='"+m_client+"' AND "+
																					" BALANCE_TO_BE_RECEIVED>0 AND "+ //comment by nuwan de silva 21-05-2008
																					//" A.INVOICE_TYPE NOT IN (SELECT INVOICE_TYPE_CODE FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD) "+
																					" A.INVOICE_TYPE NOT IN ('INV_GENER','ODI') "+
																					" AND "+
																					//" A.ACTIVE_STATUS <> 'C' "+
																					" A.ACTIVE_STATUS = 'Y'  "+
																					" GROUP BY FINANCE_NO ) ) "+
																					" GROUP BY  FINANCE_NO ");      
									
									
									 boolean more_other = rs1.next();	
								 int m = 0;
								// int k = 0;
									
								 if(more_other) {	
								  out.println("<table border='0'><tr class=tr_input1 WIDTH=100%>");
									out.println("  <td WIDTH=15%>Finance No</td>");
									out.println("  <td WIDTH=15%>Vehicle Reg. No</td>");
									out.println("  <td WIDTH=12% align=right>ODI Balance Amount</td>");
									out.println("  <td WIDTH=12% align=right>Invoice Balance Amount</td>");
									out.println("  <td WIDTH=12% align=right>ODI Allocated Amount</td>");
									out.println("  <td WIDTH=12% align=right>Invoice Allocated Amount</td>");
									out.println("  <td WIDTH=12% align=right>Balance Receipt Amount</td>");
									out.println("  <td WIDTH=10% align=center>Status</td></tr>");
									double m_other_inv_bal = 0;
									double m_other_odi_bal = 0;
									double m_other_tot_bal = 0;
									
									double m_other_inv_bal2 = 0;
									double m_other_tot_val2 = 0;
									
									double m_other_inv_val = 0;
									double m_other_odi_val = 0;
                  double m_other_tot_val = 0;
									double m_other_tot_amt = 0;
									
																		
									
								 while(more_other) {
									
                 
							    m_finance_num = rs1.getString(1);
									
									out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"OTHR_ALLO_NO1_"+m+"\">");
									out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\" ><u>"+rs1.getString(1)+"</u></td>"); 
									out.println("  <input type=hidden NAME=\"hid_other_finance_no_"+m+"\"  value=\""+rs1.getString(1)+"\"> ");
									out.println("  <td align=left  onClick=\"\" >"+rs1.getString(6)+"</td>");
									out.println("  <td align=right ><input type=text name=\"OTHR_ODI_VAL_"+m+"\" disabled value=\""+nf.format(rs1.getDouble(5))+"\" class=\"txt_input2\" style=\"width: 100px\"><input type=hidden name=\"hid_odi_"+m+"\" value="+rs1.getDouble(5)+" ></td>");
									out.println("  <td align=right ><input type=text name=\"OTHR_INV_VAL_"+m+"\" disabled value=\""+nf.format(rs1.getDouble(3))+"\" class=\"txt_input2\"><input type=hidden name=\"hid_inv_"+m+"\" value="+rs1.getDouble(3)+">");
									//out.println("  <td align=right ><input type=text name=\"D_DATE_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(5))+"\" class=\"txt_input2\"></td>");
									//out.println("  <td align=right><input type=text name=\"INV_AM_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(3))+"\" class=\"txt_input2\"></td>");
									//out.println("  <td align=right><input type=text name=\"BAL_AM_"+i+"\" disabled value=\"-\" class=\"txt_input2\">");
									out.println("  <input type=hidden name=\"other_allo_no1_"+m+"\" value=\"\"><input type=hidden name=\"other_hid_main_cnt_"+m+"\" value=\""+m+"\">"+m+"</td>");
									
									m_other_inv_val=rs1.getDouble(3);
									m_other_odi_val=rs1.getDouble(5);
									
									
									
									m_other_inv_bal  = m_other_inv_bal+rs1.getDouble(3); 
									m_other_odi_bal  = m_other_odi_bal+rs1.getDouble(5); //zzzzzzzzz
									m_other_tot_bal=m_other_inv_bal+m_other_odi_bal;
									
									if((m_othr_rec_bal - m_other_tot_amt) > (m_other_inv_val + m_other_odi_val)) {
									   out.println("<td align=right><input type=text name=\"other_Text_odi_sett_amount"+m+"\" value=\""+nf.format(m_other_odi_val)+"\" class=\"txt_input2\" disabled onBlur=\"check_other_aloc_amt(document.Form1.hid_odi_"+m+","+m_other_odi_val+" ,'ODI',"+m+"),format_number(document.Form1.other_Text_odi_sett_amount"+m+",30)\"  ></td>");
									   out.println("<td align=right><input type=text name=\"other_Text_inv_sett_amount"+m+"\" value=\""+nf.format(m_other_inv_val)+"\" class=\"txt_input2\" disabled onBlur=\"check_other_aloc_amt(document.Form1.hid_inv_"+m+","+m_other_inv_val+" ,'INV',"+m+"),format_number(document.Form1.other_Text_inv_sett_amount"+m+",30)\"  ></td>");
										  m_other_tot_val2= (m_other_inv_val + m_other_odi_val);
											m_other_tot_amt = m_other_tot_amt + (m_other_inv_val + m_other_odi_val);	
										  	
											
										  if((m_othr_rec_bal - m_other_tot_amt)>0){
											out.println("<td align=right><input type=text name=\"other_Text_balance_amount"+m+"\" value=\""+nf.format((m_othr_rec_bal - m_other_tot_amt))+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
											}else{
											out.println("<td align=right><input type=text name=\"other_Text_balance_amount"+m+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
											}	
											
										 out.println("<td align=center><INPUT TYPE=\"checkbox\" name=\"other_Text_standard_chk"+m+"\" onclick=check_aloc_status_new_other(\""+m+"\")  value=\"YES\" checked></td>"); //check_aloc_status(\""+i+"\")
									   								
									}else{
									   //out.println("2222====>"+(m_othr_rec_bal - m_other_tot_amt));
										 if((m_othr_rec_bal - m_other_tot_amt)>0){												
										   if((m_othr_rec_bal - m_other_tot_amt)>m_other_odi_val){
										     out.println("<td align=right><input type=text name=\"other_Text_odi_sett_amount"+m+"\" value=\""+nf.format(m_other_odi_val)+"\" class=\"txt_input2\" onBlur=\"check_other_aloc_amt(document.Form1.hid_odi_"+m+","+m_other_odi_val+",'ODI',"+m+"),format_number(document.Form1.other_Text_odi_sett_amount"+m+",30)\"  disabled ></td>");
									       out.println("<td align=right><input type=text name=\"other_Text_inv_sett_amount"+m+"\" value=\""+nf.format(((m_othr_rec_bal - m_other_tot_amt) - m_other_odi_val))+"\" class=\"txt_input2\" onBlur=\"check_other_aloc_amt(document.Form1.hid_inv_"+m+","+((m_othr_rec_bal - m_other_tot_amt) - m_other_odi_val)+",'INV',"+m+"),format_number(document.Form1.other_Text_inv_sett_amount"+m+",30)\" disabled ></td>");
									        m_other_tot_val2= m_other_odi_val + ((m_othr_rec_bal - m_other_tot_amt) - m_other_odi_val);
													m_other_tot_amt = m_other_tot_amt + (m_other_inv_val + m_other_odi_val);	
												 
													
											   if((m_othr_rec_bal - m_other_tot_amt)>0){
											   out.println("<td align=right><input type=text name=\"other_Text_balance_amount"+m+"\" value=\""+nf.format((m_othr_rec_bal - m_other_tot_amt))+"\" class=\"txt_input2\"  disabled></td>");
											   }else{
											   out.println("<td align=right><input type=text name=\"other_Text_balance_amount"+m+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
											   }												
												
												out.println("<td align=center><INPUT TYPE=\"checkbox\" name=\"other_Text_standard_chk"+m+"\"  onclick=check_aloc_status_new_other(\""+m+"\")  value=\"YES\" checked></td>"); //check_aloc_status(\""+i+"\")
									           
										   }else{
										    out.println("<td align=right><input type=text name=\"other_Text_odi_sett_amount"+m+"\" value=\""+nf.format((m_othr_rec_bal - m_other_tot_amt))+"\" class=\"txt_input2\" onBlur=\"check_other_aloc_amt(document.Form1.hid_odi_"+m+","+(m_othr_rec_bal - m_other_tot_amt)+",'ODI',"+m+"),format_number(document.Form1.other_Text_odi_sett_amount"+m+",30)\"  disabled ></td>");
									      out.println("<td align=right><input type=text name=\"other_Text_inv_sett_amount"+m+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"check_other_aloc_amt(document.Form1.hid_inv_"+m+","+(0.00)+",'INV',"+m+"),format_number(document.Form1.other_Text_inv_sett_amount"+m+",'30')\"  disabled ></td>");
									        m_other_tot_val2= (m_othr_rec_bal - m_other_tot_amt);
													m_other_tot_amt = m_other_tot_amt + (m_othr_rec_bal - m_other_tot_amt);
												 	
												
												if((m_othr_rec_bal - m_other_tot_amt)>0){
											  out.println("<td align=right><input type=text name=\"other_Text_balance_amount"+m+"\" value=\""+nf.format((m_othr_rec_bal - m_other_tot_amt))+"\" class=\"txt_input2\"  disabled></td>");
											  }else{
											  out.println("<td align=right><input type=text name=\"other_Text_balance_amount"+m+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
											  }	
												
												out.println("<td align=center><INPUT TYPE=\"checkbox\" name=\"other_Text_standard_chk"+m+"\"  onclick=check_aloc_status_new_other(\""+m+"\")  value=\"YES\" checked></td>"); //check_aloc_status(\""+i+"\")
	                     
											}					
										}else{
										   out.println("<td align=right><input type=text name=\"other_Text_odi_sett_amount"+m+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"check_other_aloc_amt(document.Form1.hid_odi_"+m+","+(0.00)+",'ODI',"+m+"),format_number(document.Form1.other_Text_odi_sett_amount"+m+",30)\" disabled></td>");
									     out.println("<td align=right><input type=text name=\"other_Text_inv_sett_amount"+m+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"check_other_aloc_amt(document.Form1.hid_inv_"+m+","+(0.00)+",'INV',"+m+"),format_number(document.Form1.other_Text_inv_sett_amount"+m+",30)\" disabled></td>");
									     m_other_tot_amt = m_other_tot_amt + 0.00;
											 m_other_tot_val2=0.00;
												
											 if((m_othr_rec_bal - m_other_tot_amt)>0){
											out.println("<td align=right><input type=text name=\"other_Text_balance_amount"+m+"\" value=\""+nf.format((m_othr_rec_bal - m_other_tot_amt))+"\" class=\"txt_input2\" disabled></td>");
											}else{
											out.println("<td align=right><input type=text name=\"other_Text_balance_amount"+m+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" disabled></td>");
											}	
											
											out.println("<td align=center><INPUT TYPE=\"checkbox\" name=\"other_Text_standard_chk"+m+"\" onclick=check_aloc_status_new_other(\""+m+"\")  value=\"NO\" ></td>"); //check_aloc_status(\""+i+"\")
	                   									
										}
									 }
																										
								   out.println("  </tr>");
                   
			
			          m = m+1;
								more_other = rs1.next();
									
								 }
			           out.println("<input type=hidden name=hid_other_cntract_cnt  value="+m+"></table>");
									
					      /*out.println(" <br><hr><br>");	
					      out.println("<table border='0'><tr class=pdn_txtpos2 WIDTH=100%>");
								out.println("  <td WIDTH=80%>&nbsp</td>");
								out.println("  <td WIDTH=25%><input type=button name=show_inv value=\"Allocate to Invoices\" class=mainbut onclick='show_inv_det();' style='width: 130px'></td>");
								out.println("  <td WIDTH=25%><input type=button name=remove_inv value=\"Remove Invoices\" class=mainbut onclick='remv_inv_det();' style='width: 130px'</td>");				
							  out.println(" </tr></table>");		
								out.println(" <br><hr>");					
								*/	
														
						
			
			}else{
			
			//out.println("<input type=hidden name=\"hid_cntract_cnt\" value=\"0\">");
			out.println("<input type=hidden name=hid_other_cntract_cnt  value=\"0\">");
			
								/*out.println(" <br><hr><br>");	
					      out.println("<table border='0'><tr class=pdn_txtpos2 WIDTH=100%>");
								out.println("  <td WIDTH=80%>&nbsp</td>");
								out.println("  <td WIDTH=25%><input type=button name=show_inv value=\"Allocate to Invoices\" class=mainbut onclick='show_inv_det();' style='width: 130px'></td>");
								out.println("  <td WIDTH=25%><input type=button name=remove_inv value=\"Remove Invoices\" class=mainbut onclick='remv_inv_det();' style='width: 130px'</td>");				
							  out.println(" </tr></table>");		
								out.println(" <br><hr>");	
			          */
			
			}
			
			
								//===================================================================================
							  	rs_act = stmt_act.executeQuery ("	SELECT FINANCE_NO, "+
								" 0, "+
								" 0, "+
								" 0, "+
								" 0 ,"+
								" NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-')  "+
								" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
								" WHERE APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS','LEGAL', "+
								"                              'V-RECOM','VERIFY-M','ENT_CON','VERIFY2','VERIFYL','ENT-CON','V-APP','VERIFY1','VERIFY') "+ //ADDED BY SH ON 15-03-2012
								" AND client_code='"+m_client+"' "+
								" ");

								/*" AND FINANCE_NO NOT IN "+
								" (SELECT DISTINCT FINANCE_NO "+
								" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
								" where client_code='"+m_client+"' "+
								" )");*/
								
								
								
								
								/*rs_act = stmt_act.executeQuery (" select FINANCE_NO,BAL,0,0,0,NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') REG_NO  "+
								" from "+
								" (SELECT FINANCE_NO,  "+
								" 0 BAL,0,0,0,NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') REG_NO  "+
								" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+ 
								" WHERE APPLICATION_STATUS='ACTIVATED'  "+
								" AND client_code='"+m_client+"'  "+
								" AND FINANCE_NO NOT IN  "+
								" (SELECT DISTINCT FINANCE_NO  "+
								" FROM "+m_schema_name+".AF_CO_PRO_INVOICE  "+
								" where client_code='"+m_client+"'  "+
							  " 	) "+
								" union "+
								" SELECT DISTINCT FINANCE_NO,  "+
								" SUM(BALANCE_TO_BE_RECEIVED) BAL,0,0,0,NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-')   "+
								" FROM "+m_schema_name+".AF_CO_PRO_INVOICE  "+
								" where client_code='"+m_client+"'  "+
								" GROUP BY FINANCE_NO  "+
								
								" UNION  "+
								" SELECT DISTINCT FINANCE_NO,   "+
								" 0 BAL,0,0,0,NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-')    "+
								" FROM "+m_schema_name+".AF_CO_PRO_INVOICE   "+
								" where client_code='"+m_client+"' "+
								" AND INVOICE_TYPE NOT IN ('INV_GENER','ODI') "+
								" GROUP BY FINANCE_NO )"+
								
								" where  BAL = 0 ");
								*/
								
								boolean more_rs_act=rs_act.next();
								int act=0;
								
								if(more_rs_act) {	
								  out.println("<table border='0'><tr class=tr_input1 WIDTH=100%>");
									out.println("  <td WIDTH=15%>Finance No</td>");
									out.println("  <td WIDTH=15%>Vehicle Reg. No</td>");
									out.println("  <td WIDTH=12% align=right>ODI Balance Amount</td>");
									out.println("  <td WIDTH=12% align=right>Invoice Balance Amount</td>");
									out.println("  <td WIDTH=12% align=right>ODI Allocated Amount</td>");
									out.println("  <td WIDTH=12% align=right>Invoice Allocated Amount</td>");
									out.println("  <td WIDTH=12% align=right>Balance Receipt Amount</td>");
									out.println("  <td WIDTH=10% align=center>Status</td></tr>");
									}
								while(more_rs_act){
								
								
								out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"ACTIVATE_OTHR_ALLO_NO1_"+act+"\">");
								out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs_act.getString(1)+"')\" ><u>"+rs_act.getString(1)+"</u></td>"); 
								out.println("  <input type=hidden NAME=\"hid_activate_finance_no_"+act+"\"  value=\""+rs_act.getString(1)+"\"> ");
								out.println("  <td align=left  onClick=\"\" >"+rs_act.getString(6)+"</td>");
								out.println("  <td align=right ><input type=text name=\"activate_ODI_VAL_"+act+"\" disabled value=\""+nf.format(rs_act.getDouble(5))+"\" class=\"txt_input2\" style=\"width: 100px\"><input type=hidden name=\"activate_hid_odi_"+act+"\" value="+rs_act.getDouble(5)+"></td>");
								out.println("  <td align=right ><input type=text name=\"activate_INV_VAL_"+act+"\" disabled value=\""+nf.format(rs_act.getDouble(3))+"\" class=\"txt_input2\"><input type=hidden name=\"activate_hid_inv_"+act+"\" value="+rs_act.getDouble(3)+">");
								out.println("  <input type=hidden name=\"activate_allo_no1_"+act+"\" value=\"\"><input type=hidden name=\"activate_hid_main_cnt_"+act+"\" value=\""+act+"\"></td>");
								out.println("<td align=right><input type=text name=\"activate_Text_odi_sett_amount"+act+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"format_number(document.Form1.activate_Text_odi_sett_amount"+act+",30) \" onblur=\"\" disabled ></td>");
								
								/*if((m_rec_bal - m_tot_amt)>0){
								out.println("<td align=right><input type=text name=\"activate_Text_inv_sett_amount"+act+"\" value=\""+nf.format((m_rec_bal - m_tot_amt))+"\" class=\"txt_input2\" onchange=\"check_aloc_amt_activate(document.Form1.activate_hid_inv_"+act+","+(0.00)+",'INV',"+act+")\"   disabled></td>");  //
								}else{
								out.println("<td align=right><input type=text name=\"activate_Text_inv_sett_amount"+act+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
								}	
								*/
								
								m_tot_amt = m_tot_amt + (m_rec_bal - m_tot_amt);
								out.println("<td align=right><input type=text name=\"activate_Text_inv_sett_amount"+act+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\"   onBlur=\"check_aloc_amt_activate(document.Form1.activate_hid_inv_"+act+","+(0.00)+",'INV',"+act+")\"   disabled onBlur=\"format_number(document.Form1.elements['activate_Text_inv_sett_amount'"+act+"],30)\" ></td>"); //onchange=\"check_act_aloc_amt(document.Form1.activate_hid_inv_"+act+","+(0.00)+" ,'INV',"+act+")
								out.println("<td align=right><input type=text name=\"activate_Text_balance_amount"+act+"\"    value=\""+nf.format(0.00)+"\" class=\"txt_input2\" disabled></td>");
								
								/*if((m_rec_bal - m_tot_amt)>0){
								out.println("<td align=right><input type=text name=\"activate_Text_balance_amount"+act+"\"  value=\""+nf.format((m_rec_bal - m_tot_amt))+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
								}else{
								out.println("<td align=right><input type=text name=\"activate_Text_balance_amount"+act+"\"  value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
								}	
                */

								out.println("<td align=center><INPUT TYPE=\"checkbox\" name=\"activate_Text_standard_chk"+act+"\"  onclick=check_aloc_status_new_activated(\""+act+"\")  value=\"YES\" checked></td>");  
								act=act+1;
								more_rs_act=rs_act.next();
								}	
								
								out.println("<input type=hidden name=hid_activate_cntract_cnt  value="+act+"></table>");

								//===================================================================================
								
								out.println(" <br><hr><br>");	
					      out.println("<table border='0'><tr class=pdn_txtpos2 WIDTH=100%>");
								out.println("  <td WIDTH=80%>&nbsp</td>");
								out.println("  <td WIDTH=25%><input type=button name=show_inv value=\"Allocate to Invoices\" class=mainbut onclick='show_inv_det();' style='width: 130px'></td>");
								out.println("  <td WIDTH=25%><input type=button name=remove_inv value=\"Remove Invoices\" class=mainbut onclick='remv_inv_det();' style='width: 130px'</td>");				
							  out.println(" </tr></table>");		
								out.println(" <br><hr>");	
			
			
			//} 
			
			
			
			
			
			}
			
			
			
			}else if(m_chksql.trim().equals("get_contract")){
			
			String m_client    = req.getParameter("Client_Code");
			double m_rec_bal   = new Double(req.getParameter("Amount")).doubleValue();
			double m_Other_Amt = new Double(req.getParameter("Other_Amount")).doubleValue();
			String m_type      = req.getParameter("Type");
			
			double m_rec_tot   = 0;
			double m_rec_tot2   = 0; 
			double m_rec_bal2   = 0;
			String m_finance_num ="";
			
			if(m_type.equals("NEW")){
			
			
									rs1 = stmt1.executeQuery (" SELECT FINANCE_NO, "+
									      " SUM(INV_AMOUNT), "+
												" SUM(INV_BALANCE_AMOUNT), "+
												" SUM(ODI_AMOUNT), "+
												" SUM(ODI_BAL_AMOUNT), "+
												" NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') "+
												" FROM "+
												" ((SELECT FINANCE_NO, "+
												" SUM(TOTAL_AMOUNT_CURR) INV_AMOUNT, "+
												" SUM(BALANCE_TO_BE_RECEIVED)  INV_BALANCE_AMOUNT, "+
												" 0 ODI_AMOUNT, "+
												" 0 ODI_BAL_AMOUNT  "+
												" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A, "+
												" "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
												" WHERE CLIENT_CODE='"+m_client+"' AND "+
												" BALANCE_TO_BE_RECEIVED>0 AND "+
												" A.INVOICE_TYPE = B.INVOICE_TYPE_CODE AND "+
												//" A.ACTIVE_STATUS <> 'C' "+
												" A.ACTIVE_STATUS = 'Y'  "+
												" AND A.INVOICE_TYPE IN ('INV_GENER','ODI')  "+ //ADDED BY NUWAN DE SILVA
												" GROUP BY FINANCE_NO ) "+
												" UNION ALL "+
												" (SELECT a.FIN_NO FINANCE_NO, "+
												" 0 INV_AMOUNT, "+
												" 0 INV_BALANCE_AMOUNT, "+
												" SUM(ODI_CAL_AMOUNT) ODI_AMOUNT, "+
												" SUM(ODI_BAL_AMOUNT) ODI_BAL_AMOUNT "+
												" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A, "+
												" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
												" WHERE  CLIENT_CODE='"+m_client+"' AND "+
												" A.INVOICE_NO=B.INVOICE_NO AND "+
												" B.INVOICE_TYPE IN ('INV_GENER','ODI') AND "+
												" ODI_BAL_AMOUNT>0 "+
												" GROUP BY a.FIN_NO )) "+
												" GROUP BY  FINANCE_NO ");     
			
			
			  boolean more1 = rs1.next();	
								 int i = 0;
								 int k = 0;
								 int h = 0;	
								 int d = 0;	
									
								  double m_inv_bal = 0;
									double m_odi_bal = 0;
									double m_tot_bal = 0;
									
									double m_inv_bal2 = 0;
									double m_tot_val2 = 0;
									
									double m_inv_val = 0;
									double m_odi_val = 0;
                  double m_tot_val = 0;
									double m_tot_amt = 0;
									int j=0;

									
								 if(more1) {	
							/*		double m_inv_bal = 0;
									double m_odi_bal = 0;
									double m_tot_bal = 0;
									
									double m_inv_bal2 = 0;
									double m_tot_val2 = 0;
									
									double m_inv_val = 0;
									double m_odi_val = 0;
                  double m_tot_val = 0;
									double m_tot_amt = 0;
									int j=0;
							*/											
									
								 while(more1) {
									
									out.println("<table border='0'><tr class=pdn_txtpos2 WIDTH=100%>");
									out.println("  <td WIDTH=15%>Finance No</td>");
									out.println("  <td WIDTH=15%>Vehicle Reg. No</td>");
									out.println("  <td WIDTH=12% align=right>ODI Balance Amount</td>");
									out.println("  <td WIDTH=12% align=right>Invoice Balance Amount</td>");
									out.println("  <td WIDTH=12% align=right>ODI Allocated Amount</td>");
									out.println("  <td WIDTH=12% align=right>Invoice Allocated Amount</td>");
									out.println("  <td WIDTH=12% align=right>Balance Receipt Amount</td>");
									out.println("  <td WIDTH=10% align=center>Status</td></tr>");				
									
							    m_finance_num = rs1.getString(1);
									
									out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"ALLO_NO1_"+i+"\">");
									out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\" ><u>"+rs1.getString(1)+"</u></td>"); 
									out.println("  <td align=left  onClick=\"\" >"+rs1.getString(6)+"</td>");
									out.println("  <td align=right ><input type=text name=\"ODI_VAL_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(5))+"\" class=\"txt_input2\" style=\"width: 100px\"><input type=hidden name=\"hid_odi_"+i+"\" value="+rs1.getDouble(5)+"></td>");
									out.println("  <td align=right ><input type=text name=\"INV_VAL_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(3))+"\" class=\"txt_input2\"><input type=hidden name=\"hid_inv_"+i+"\" value="+rs1.getDouble(3)+">");
									//out.println("  <td align=right ><input type=text name=\"D_DATE_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(5))+"\" class=\"txt_input2\"></td>");
									//out.println("  <td align=right><input type=text name=\"INV_AM_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(3))+"\" class=\"txt_input2\"></td>");
									//out.println("  <td align=right><input type=text name=\"BAL_AM_"+i+"\" disabled value=\"-\" class=\"txt_input2\">");
									out.println("  <input type=hidden name=\"allo_no1_"+i+"\" value=\"\"><input type=hidden name=\"hid_main_cnt_"+i+"\" value=\""+i+"\"></td>");
									
									m_inv_val=rs1.getDouble(3);
									m_odi_val=rs1.getDouble(5);
																											
									m_inv_bal  = m_inv_bal+rs1.getDouble(3); 
									m_odi_bal  = m_odi_bal+rs1.getDouble(5); 
									m_tot_bal=m_inv_bal+m_odi_bal;
									
									out.println("<td align=right><input type=text name=\"Text_odi_sett_amount2"+i+"\" value=\""+nf.format(m_odi_val)+"\" class=\"txt_input2\" onchange=\"\" onblur=\"\" disabled></td>");
									out.println("<td align=right><input type=text name=\"Text_inv_sett_amount2"+i+"\" value=\""+nf.format(m_inv_val)+"\" class=\"txt_input2\" onchange=\"\" onblur=\"\" disabled></td>");
									out.println("<td align=right><input type=text name=\"Text_balance_amount2"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
									out.println("<td align=center><INPUT TYPE=\"checkbox\" name=\"Text_standard_chk"+i+"\" onclick=check_aloc_status(\""+i+"\")  value=\"YES\" checked></td>"); //
								  out.println("  </tr></table>");
																		
									out.println("<table WIDTH=90% border='0'><tr class=pdn_txtpos2 WIDTH=100%>");
									out.println("  <td WIDTH=15% align=center>Invoice No </td>");
									out.println("  <td WIDTH=10% align=center>Invoice Type</td>");
									out.println("  <td WIDTH=15% align=right>Invoiced Date</td>");
									out.println("  <td WIDTH=15% align=right>Due Date</td>");
									out.println("  <td WIDTH=15% align=right>Invoice Amount</td>");
									out.println("  <td WIDTH=15% align=right>Balance Amount</td>");
									out.println("  <td WIDTH=15% align=right>Allocated Amount</td>");
									//out.println("  <td WIDTH=15% align=center>Balance</td>");
									out.println("  <td WIDTH=10% align=center>Status</td></tr>");
									
									if(m_tot_val2>0){
									m_rec_bal2 = m_tot_val2;
									}else{
									m_rec_bal2 = 0.00;
									}
																	
							  //out.println("m_rec_bal2"+m_rec_bal2+"m_tot_val2"+m_tot_val2);
																						
											rs2 = stmt2.executeQuery ("SELECT INVOICE_NO,VAL_DATE, "+
											             " TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																		" VAT_AMOUNT,FINANCE_NO,TO_CHAR(DUE_DATE,'DD-MM-YYYY'), "+
																		" NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																		" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																		" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																		" INVOICE_TYPE,VALUE_DATE, "+m_schema_name+".AF_CO_GET_INVOICE_DESCR(INVOICE_TYPE) INV_DESCR "+
																		" FROM  (SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE, "+
																		" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																		" VAT_AMOUNT,FINANCE_NO, "+
																		" DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																		" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																		" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																		" INVOICE_TYPE,VALUE_DATE "+
																		" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A, "+
																		" "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																		" WHERE CLIENT_CODE='"+m_client+"' AND "+
																		" BALANCE_TO_BE_RECEIVED>0 AND "+
																		" A.INVOICE_TYPE = B.INVOICE_TYPE_CODE AND "+
																		" A.INVOICE_TYPE IN ('INV_GENER','ODI') AND "+ //ADDED BY NUWAN DE SILVA 
																		//" A.ACTIVE_STATUS <> 'C' AND "+
																		" A.ACTIVE_STATUS = 'Y'  AND "+
																		" FINANCE_NO = '"+m_finance_num+"' "+
																		" UNION ALL "+
																		" SELECT ODI_REF_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY') VAL_DATE, "+
																		" ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT, "+
																		" 0,a.FIN_NO NO,ODI_DATE,0,CLIENT_CODE,'', "+
																		" CURRENCY_CODE, EXCHANGE_RATE,0,0,0,'ODI' INVOICE_TYPE,ODI_DATE "+
																		" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A, "+
																		" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
																		" WHERE  CLIENT_CODE='"+m_client+"' AND "+
																		" A.INVOICE_NO=B.INVOICE_NO AND "+
																		" a.FIN_NO = '"+m_finance_num+"' AND "+
																		" ODI_BAL_AMOUNT>0 ) A, "+
																		" "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																		" WHERE A.INVOICE_TYPE = B.INVOICE_TYPE_CODE "+
																		" AND A.INVOICE_TYPE IN ('INV_GENER','ODI') "+
																		" ORDER BY ORDER_NO,VALUE_DATE ");																	
																		
									//int j=0;
									boolean more2 = rs2.next();	
														
									double m_bal_amt = 0;
									
									while(more2) {
										//out.println("m_inv_bal2" +m_rec_bal2);
									out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_"+k+"\">");
									//out.println("  <td align=left ><input type=text name=\"INV_NO_"+k+"\" disabled value=\""+rs2.getString(1)+"\" class=\"txt_input2\" style=\"width: 100px\"></td>");
									out.println("  <td align=center style= cursor:hand;  onClick=\"\" ><u>"+rs2.getString(1)+"</u></td>"); 
									out.println("  <td align=center style= cursor:hand;  onClick=\"\" >"+rs2.getString(19)+"</td>");
									out.println("  <td align=right ><input type=text name=\"V_DATE_"+k+"\" disabled value=\""+rs2.getString(2)+"\" class=\"txt_input2\" style=\"width: 100px\"></td>");
									out.println("  <td align=right ><input type=text name=\"D_DATE_"+k+"\" disabled value=\""+rs2.getString(8)+"\" class=\"txt_input2\">");
									out.println("  <td align=right ><input type=text name=\"INV_AM_"+k+"\" disabled value=\""+nf.format(rs2.getDouble(3))+"\" class=\"txt_input2\">");
									out.println("  <td align=right ><input type=text name=\"BAL_AM_"+k+"\" disabled value=\""+nf.format(rs2.getDouble(4))+"\" class=\"txt_input2\">");
									out.println("  <input type=hidden name=\"allo_no_"+k+"\" value=\"\">");
									out.println("  <input type=hidden name=\"hid_contract_no_"+k+"\" value=\""+rs2.getString(7)+"\">"); //added by nuwan de silva on 16-06-2008
									out.println("  <input type=hidden name=\"INV_NO_"+k+"\" value=\""+rs2.getString(1)+"\"></td>");
			
									
									m_inv_bal2  = m_inv_bal2+rs2.getDouble(4); 
																		
									if(m_rec_tot2<m_inv_bal2){
										if(m_rec_bal2>= (m_inv_bal2-m_rec_tot2)){
									   //out.println("m_inv_bal" +m_rec_bal2);
										  out.println("<td align=right><input type=text name=\"Text_sett_amount"+k+"\" value=\""+nf.format((m_inv_bal2-m_rec_tot2))+"\" class=\"txt_input2\" onchange=\"chk_batch_val("+i+","+k+")\" disabled ></td>");  //chk_bal('"+k+"')
											out.println("  <input type=hidden name=\"inv_alocate_amt_"+k+"\" value=\"\">");
											//out.println("<td align=right><input type=text name=\"Text_bal_amount"+k+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled ></td>"); 
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+k+"\" onclick=check_status_inv(\""+k+"\") value=\"YES\" checked>");
									     
									    m_rec_bal2 = m_rec_bal2-(m_inv_bal2-m_rec_tot2);
											m_rec_tot2 = m_rec_tot2 +(m_inv_bal2-m_rec_tot2);
										}else{
										 if(m_rec_bal2>0){ 
											//out.println("m_inv_bal" +m_rec_bal2);
									    out.println("<td align=right><input type=text name=\"Text_sett_amount"+k+"\" value=\""+nf.format(m_rec_bal2)+"\" class=\"txt_input2\" onchange=\"chk_batch_val("+i+","+k+")\"  disabled></td>");//chk_bal('"+i+"')
											out.println("  <input type=hidden name=\"inv_alocate_amt_"+k+"\" value=\"\">");
											//out.println("<td align=right><input type=text name=\"Text_bal_amount"+k+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled ></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+k+"\" onclick=check_status_inv(\""+k+"\") value=\"YES\" checked>");
									     
									    m_rec_tot2 = m_rec_tot2+m_rec_bal2;
											m_rec_bal2 = 0;
										 }else{
											//out.println("m_inv_bal" +m_rec_bal2);
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+k+"\" value=\""+nf.format(m_rec_bal2)+"\" class=\"txt_input2\" onchange=\"chk_batch_val("+i+","+k+")\" disabled></td>");//chk_bal('"+i+"')
											out.println("  <input type=hidden name=\"inv_alocate_amt_"+k+"\" value=\"\">");
											//out.println("<td align=right><input type=text name=\"Text_bal_amount"+k+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled ></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+k+"\" onclick=check_status_inv(\""+k+"\") value=\"NO\" >");
									    									    
										 }	
										}
									 }else{
									   	//out.println("m_inv_bal2" +m_rec_bal2);
									    out.println("<td align=right><input type=text name=\"Text_sett_amount"+k+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"chk_batch_val("+i+","+k+")\" disabled></td>"); //chk_bal('"+i+"')
											out.println("  <input type=hidden name=\"inv_alocate_amt_"+k+"\" value=\"\">");
											//out.println("<td align=right><input type=text name=\"Text_bal_amount"+k+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled ></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+k+"\" onclick=check_status_inv(\""+k+"\") value=\"NO\">");
									}
									out.println("<input type=hidden name=hid_othr_invoice_no_"+k+"  value="+rs2.getString(17)+"></tr>");
				
									//----- END INNER LOOP --------------------------------------------//
											
									j = j+1;
									k = k+1; 
								 more2 = rs2.next();
								}
								
								
								
								out.println("<tr></tr>");
								// out.println("j ==== value"+j+"k ==== value"+k);	
																
								out.println(" <input type=hidden name=hid_invoice_cnt_"+i+"  value="+j+"><input type=hidden name=hid_invoice_tot_cnt_"+i+"  value="+k+"></table>");
							
							  //added by nuwan de silva on 21-05-2008 --------
								more1 = rs1.next();
								i=i+1;
							  }
								
								}
								//added by nuwan de silva on 21-05-2008 --------
								
								///////////////////////////////////////////////////////////////////////////////////////////////////////////////
								/* OTHER INVOICE ALLOCATION */
								
								
							rs12 = stmt12.executeQuery (" SELECT FINANCE_NO, "+
								                 " SUM(INV_AMOUNT), "+
																 " SUM(INV_BALANCE_AMOUNT), "+
																 " SUM(ODI_AMOUNT), "+
																 " SUM(ODI_BAL_AMOUNT), "+
																 " NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') "+
																 " FROM ((SELECT FINANCE_NO, "+
																 " SUM(TOTAL_AMOUNT_CURR) INV_AMOUNT, "+
																 " SUM(BALANCE_TO_BE_RECEIVED)  INV_BALANCE_AMOUNT, "+
																 " 0 ODI_AMOUNT, "+
																	" 0 ODI_BAL_AMOUNT "+
																	" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A "+
																	" WHERE CLIENT_CODE='"+m_client+"' AND "+
																//	" FINANCE_NO = '"+m_finance_num+"' AND "+ //comment by nuwan de silva on 21-05-2008
																	" BALANCE_TO_BE_RECEIVED>0 AND "+
																	//" A.INVOICE_TYPE NOT IN (SELECT INVOICE_TYPE_CODE FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD) "+
																	" A.INVOICE_TYPE NOT IN ('INV_GENER','ODI') "+
																	" AND "+
																	//" A.ACTIVE_STATUS <> 'C' "+
																	" A.ACTIVE_STATUS = 'Y'  "+
																	" GROUP BY FINANCE_NO ) ) "+
																	" GROUP BY  FINANCE_NO ");
								
								
								boolean more_othr_det = rs12.next();
								
								double m_other_inv_val =0.00;
								double m_other_odi_val =0.00;
								
								double m_other_inv_bal =0.00;
								double m_other_odi_bal =0.00; 
                double m_other_tot_bal =0.00;
																
								if(more_othr_det){
								
								while(more_othr_det){		
								
							 		out.println("<table border='0'><tr class=tr_input1 WIDTH=100%>");
									out.println("  <td WIDTH=15%>Finance No</td>");
									out.println("  <td WIDTH=15%>Vehicle Reg. No</td>");
									out.println("  <td WIDTH=12% align=right>ODI Balance Amount</td>");
									out.println("  <td WIDTH=12% align=right>Invoice Balance Amount</td>");
									out.println("  <td WIDTH=12% align=right>ODI Allocated Amount</td>");
									out.println("  <td WIDTH=12% align=right>Invoice Allocated Amount</td>");
									out.println("  <td WIDTH=12% align=right>Balance Receipt Amount</td>");
									out.println("  <td WIDTH=10% align=center>Status</td></tr>");		
															
								  out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"OTHR_ALLO_NO1_"+h+"\">");
									out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs12.getString(1)+"')\" ><u>"+rs12.getString(1)+"</u></td>"); 
									out.println("  <td align=left  onClick=\"\" >"+rs12.getString(6)+"</td>");
									out.println("  <td align=right ><input type=text name=\"OTHR_ODI_VAL_"+h+"\" disabled value=\""+nf.format(rs12.getDouble(5))+"\" class=\"txt_input2\" style=\"width: 100px\"><input type=hidden name=\"hid_othr_odi_"+h+"\" value="+rs12.getDouble(5)+"></td>");
									out.println("  <td align=right ><input type=text name=\"OTHR_INV_VAL_"+h+"\" disabled value=\""+nf.format(rs12.getDouble(3))+"\" class=\"txt_input2\"><input type=hidden name=\"hid_othr_inv_"+h+"\" value="+rs12.getDouble(3)+">");
									//out.println("  <td align=right ><input type=text name=\"D_DATE_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(5))+"\" class=\"txt_input2\"></td>");
									//out.println("  <td align=right><input type=text name=\"INV_AM_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(3))+"\" class=\"txt_input2\"></td>");
									//out.println("  <td align=right><input type=text name=\"BAL_AM_"+i+"\" disabled value=\"-\" class=\"txt_input2\">");
									out.println("  <input type=hidden name=\"othr_allo_no1_"+h+"\" value=\"\"><input type=hidden name=\"hid_othr_main_cnt_"+h+"\" value=\""+h+"\"></td>");
									
									m_other_inv_val=rs12.getDouble(3);
									m_other_odi_val=rs12.getDouble(5);
									
									
									
									m_other_inv_bal  = m_other_inv_bal+rs12.getDouble(3); 
									m_other_odi_bal  = m_other_odi_bal+rs12.getDouble(5); 
									m_other_tot_bal = m_other_inv_bal+m_other_odi_bal;
									
									
									   out.println("<td align=right><input type=text name=\"Text_othr_odi_sett_amount2"+h+"\" value=\""+nf.format(m_other_odi_val)+"\" class=\"txt_input2\" onchange=\"\" onblur=\"\" disabled></td>");
									   out.println("<td align=right><input type=text name=\"Text_othr_inv_sett_amount2"+h+"\" value=\""+nf.format(m_other_inv_val)+"\" class=\"txt_input2\" onchange=\"\" onblur=\"\" disabled></td>");
												  
										 out.println("<td align=right><input type=text name=\"Text_othr_balance_amount2"+h+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
																	
										 out.println("<td align=center><INPUT TYPE=\"checkbox\" name=\"Text_othr_standard_chk"+h+"\" onclick=check_aloc_status(\""+h+"\")  value=\"YES\" checked></td>"); //
									   								
									   out.println("</tr></table>");
								
								 //out.println("---h--++"+h); 
								h=h+1;
						    m_finance_num = rs12.getString(1);

							//	}
																		
								rs2 = stmt2.executeQuery ("	SELECT INVOICE_NO,VAL_DATE, "+
								                    " TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																		" VAT_AMOUNT,FINANCE_NO,TO_CHAR(DUE_DATE,'DD-MM-YYYY'), "+
																		" NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																		" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																		" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																		" INVOICE_TYPE,VALUE_DATE, "+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE) "+
																		" FROM "+
																		" (SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE, "+
																		" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																		" VAT_AMOUNT,FINANCE_NO, "+
																		" DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																		" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																		" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																		" INVOICE_TYPE,VALUE_DATE "+
																		" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A "+
																		" WHERE CLIENT_CODE='"+m_client+"' AND "+
																		" BALANCE_TO_BE_RECEIVED>0 AND "+
																		//" A.INVOICE_TYPE  NOT IN (SELECT INVOICE_TYPE_CODE FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD) AND "+
																		" A.INVOICE_TYPE NOT IN ('INV_GENER','ODI') AND "+
																		//" A.ACTIVE_STATUS <> 'C' AND "+
																		" A.ACTIVE_STATUS = 'Y'  AND "+
																		" FINANCE_NO = '"+m_finance_num+"' "+
																		" ORDER BY VALUE_DATE) ");
																		
																										
									 j=0;
									boolean more_othr = rs2.next();	
									
									
									
									if(more_othr){
									out.println("<table WIDTH=90% border='0'><tr class=tr_input1 WIDTH=100%>");
									out.println("  <td WIDTH=15% align=center>Invoice No </td>");
									out.println("  <td WIDTH=10% align=center>Invoice Type</td>");
									out.println("  <td WIDTH=15% align=right>Invoiced Date</td>");
									out.println("  <td WIDTH=15% align=right>Due Date</td>");
									out.println("  <td WIDTH=15% align=right>Invoice Amount</td>");
									out.println("  <td WIDTH=15% align=right>Balance Amount</td>");
									out.println("  <td WIDTH=15% align=right>Allocated Amount</td>");
									//out.println("  <td WIDTH=15% align=center>Balance</td>");
									out.println("  <td WIDTH=10% align=center>Status</td></tr>");
									
									}
								
									
									if(m_tot_val2>0){
									m_rec_bal2 = m_tot_val2;
									}else{
									m_rec_bal2 = 0.00;
									}										
															
									
									int t = k;
								  int n = 0;
									
									double m_othr_bal_amt = 0;
									
									while(more_othr) {
									out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_"+k+"\">");
									//out.println("  <td align=left ><input type=text name=\"INV_NO_"+k+"\" disabled value=\""+rs2.getString(1)+"\" class=\"txt_input2\" style=\"width: 100px\"></td>");
									out.println("  <td align=center style= cursor:hand;  onClick=\"\" ><u>"+rs2.getString(1)+"</u></td>"); 
									out.println("  <td align=center style= cursor:hand;  onClick=\"\" >"+rs2.getString(19)+"</td>");
									out.println("  <td align=right ><input type=text name=\"V_DATE_"+k+"\" disabled value=\""+rs2.getString(2)+"\" class=\"txt_input2\" style=\"width: 100px\"></td>");
									out.println("  <td align=right ><input type=text name=\"D_DATE_"+k+"\" disabled value=\""+rs2.getString(8)+"\" class=\"txt_input2\">");
									out.println("  <td align=right ><input type=text name=\"INV_AM_"+k+"\" disabled value=\""+nf.format(rs2.getDouble(3))+"\" class=\"txt_input2\">");
									out.println("  <td align=right ><input type=text name=\"BAL_AM_"+k+"\" disabled value=\""+nf.format(rs2.getDouble(4))+"\" class=\"txt_input2\">");
									out.println("  <input type=hidden name=\"allo_no_"+k+"\" value=\"\">");
									out.println("  <input type=hidden name=\"hid_contract_no_"+k+"\" value=\""+rs2.getString(7)+"\">"); //added by nuwan de silva on 16-06-2008
									if(n==0){
									out.println(" <input type=hidden name=\"start_val_"+h+"\" value=\""+k+"\">");
									}else{
									//out.println(" "+h+" <input type=hidden name=\"start_val_"+h+"\" value=\"0\">");
									}
									
									out.println("  <input type=hidden name=\"INV_NO_"+k+"\" value=\""+rs2.getString(1)+"\"></td>");
			
									
									m_inv_bal2  = m_inv_bal2+rs2.getDouble(4); 
																		
									if(m_rec_tot2<m_inv_bal2){
										if(m_rec_bal2>= (m_inv_bal2-m_rec_tot2)){
										  out.println("<td align=right><input type=text name=\"Text_sett_amount"+k+"\" value=\""+nf.format((m_inv_bal2-m_rec_tot2))+"\" class=\"txt_input2\" onchange=\"chk_othr_batch_val("+h+","+k+")\" disabled ></td>");  //chk_bal('"+k+"') //uuuuuuuuu
											out.println("  <input type=hidden name=\"inv_alocate_amt_"+k+"\" value=\"\">");
											//out.println("<td align=right><input type=text name=\"Text_bal_amount"+k+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled ></td>"); 
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+k+"\" onclick=check_status_inv(\""+k+"\") value=\"YES\" checked>");
									     
									    m_rec_bal2 = m_rec_bal2-(m_inv_bal2-m_rec_tot2);
											m_rec_tot2 = m_rec_tot2 +(m_inv_bal2-m_rec_tot2);
										}else{
										 if(m_rec_bal2>0){ 
										  out.println("<td align=right><input type=text name=\"Text_sett_amount"+k+"\" value=\""+nf.format(m_rec_bal2)+"\" class=\"txt_input2\" onchange=\"chk_othr_batch_val("+h+","+k+")\" disabled ></td>");//chk_bal('"+i+"')
											out.println("  <input type=hidden name=\"inv_alocate_amt_"+k+"\" value=\"\">");
											//out.println("<td align=right><input type=text name=\"Text_bal_amount"+k+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled ></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+k+"\" onclick=check_status_inv(\""+k+"\") value=\"YES\" checked>");
									     
									    m_rec_tot2 = m_rec_tot2+m_rec_bal2;
											m_rec_bal2 = 0;
										 }else{
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+k+"\" value=\""+nf.format(m_rec_bal2)+"\" class=\"txt_input2\" onchange=\"chk_othr_batch_val("+h+","+k+")\" disabled ></td>");//chk_bal('"+i+"')
											out.println("  <input type=hidden name=\"inv_alocate_amt_"+k+"\" value=\"\">");
											//out.println("<td align=right><input type=text name=\"Text_bal_amount"+k+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled ></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+k+"\" onclick=check_status_inv(\""+k+"\") value=\"NO\" >");
									    									    
										 }	
										}
									 }else{
									   
									    out.println("<td align=right><input type=text name=\"Text_sett_amount"+k+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"chk_othr_batch_val("+h+","+k+")\" disabled></td>"); //chk_bal('"+i+"')
											out.println("  <input type=hidden name=\"inv_alocate_amt_"+k+"\" value=\"\">");
											//out.println("<td align=right><input type=text name=\"Text_bal_amount"+k+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled ></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+k+"\" onclick=check_status_inv(\""+k+"\") value=\"NO\">");
									}
									out.println("<input type=hidden name=hid_othr_invoice_no_"+k+"  value="+rs2.getString(17)+"></tr>");
				
									//----- END INNER LOOP --------------------------------------------//
				
				          
				
									j = j+1;
									k = k+1; 
									n = n+1;
								 more_othr = rs2.next();
								}	                        //
																								
							 if(d!=h){ /*ADDED BY CHANDANA ON 23/04/2008*/
								//out.println("Test ok ------"+h+"---"+j); 
								out.println("<input type=hidden name=hid_other_inv_cnt_"+h+"  value="+j+">");
								d=h;
								}else{
                //out.println("Test no ------"+h+"---"+j); 
								
								}	
								
								
							 
								
								//out.println("<input type=hidden name=hid_other_inv_cnt_"+h+"  value="+j+">"); /*COMMENT BY CHANDANA ON 23/04/2008*/
								out.println("<input type=hidden name=hid_other_invoice_tot_cnt_"+i+"  value="+k+">");
								out.println("</table>");
								
								out.println("<hr>");
								//////////////////////////////////////////////////////////////////////////////
							 //comment by nuwan de silva on 21-05-2008
								// i = i+1;
							 //	more1 = rs1.next();
							// }
							
							
							//added by nuwan de silva on 21-05-2008 -----
								more_othr_det = rs12.next();
								}
								
								
								}
								//-------------------------------------------
									
							out.println("<table><tr class=pdn_txtpos2 WIDTH=100%>");
							
							out.println("  <tr><input type=hidden name=hid_invoice_count  value="+k+"><input type=hidden name=hid_othr_contract_cnt  value="+h+"></tr>");	//<input type=hidden name=hid_cntract_cnt  value="+i+">
							out.println("</table>");		
									
									
								}
					//		 }
									
									
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 						
									
}
			else if(m_chksql.trim().equals("get_invoice")){
			        
							String m_client    = req.getParameter("Client_Code");
			        double m_rec_bal   = new Double(req.getParameter("Amount")).doubleValue();
							String m_type      = req.getParameter("Type");
							String m_rec_no    = req.getParameter("Rec_No");
							
							double m_rec_tot   = 0;//m_rec_bal;
							
							if(m_type.equals("NEW")){
							/*
									rs1 = stmt1.executeQuery ("SELECT INVOICE_NO,VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO,TO_CHAR(DUE_DATE,'DD-MM-YYYY'), "+
																						"       NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE, "+
																						"       NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') "+
																						" FROM  (SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE "+
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,"+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND "+
																						"       BALANCE_TO_BE_RECEIVED>0 AND "+
																						"       A.INVOICE_TYPE = B.INVOICE_TYPE_CODE AND "+
					                                  "	      A.ACTIVE_STATUS <> 'C' "+//AND DUE_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																						" UNION ALL  "+
																						"	SELECT ODI_REF_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						"	       ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT, "+
																						"	       0,FINANCE_NO NO,ODI_DATE,0,CLIENT_CODE,'', "+
																						"	       CURRENCY_CODE, EXCHANGE_RATE,0,0,0,'ODI',ODI_DATE "+
																						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
																						"	WHERE  CLIENT_CODE='"+m_client+"' AND "+
																						"        A.INVOICE_NO=B.INVOICE_NO AND "+//ODI_DATE<=SYSDATE AND "+
																						"	       ODI_BAL_AMOUNT>0  "+
																						" UNION ALL  "+
																						" SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						" VAT_AMOUNT,FINANCE_NO, "+
																						" DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						" INVOICE_TYPE,VALUE_DATE, "+
																						" LAKDL.AF_CO_GET_SUB_CHARG_DESC(A.INVOICE_TYPE) "+
																						" FROM  LAKDL.AF_CO_PRO_INVOICE A "+
																						" WHERE CLIENT_CODE='0000000475' AND "+
																						" BALANCE_TO_BE_RECEIVED>0 AND "+
																						" A.INVOICE_TYPE  NOT IN (SELECT INVOICE_TYPE_CODE FROM LAKDL.AF_CO_MAS_INVOICE_RECEIPT_ORD ) "+
																						" AND A.ACTIVE_STATUS <> 'C' "+						
																						
																						
																						
																						
																						") A, "+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE A.INVOICE_TYPE = B.INVOICE_TYPE_CODE "+
																						"	ORDER BY ORDER_NO,VALUE_DATE	"); */
																						
																						
																						
																						
									rs1 = stmt1.executeQuery ("	SELECT INVOICE_NO,VAL_DATE, "+
									                       "  TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																					" VAT_AMOUNT,FINANCE_NO,TO_CHAR(DUE_DATE,'DD-MM-YYYY'), "+
																					" NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																					" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																					" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																					" INVOICE_TYPE,VALUE_DATE, "+
																					" NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-'), DESCR "+
																					" FROM  (SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE, "+
																					" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																					" VAT_AMOUNT,FINANCE_NO, "+
																					" DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																					" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																					" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																					" INVOICE_TYPE,VALUE_DATE,"+m_schema_name+".AF_CO_GET_INVOICE_DESCR(INVOICE_TYPE) DESCR "+
																					" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A, "+
																					" "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																					" WHERE CLIENT_CODE='"+m_client+"' AND "+
																					" BALANCE_TO_BE_RECEIVED>0 AND "+
																					" A.INVOICE_TYPE = B.INVOICE_TYPE_CODE AND "+
																					//" A.ACTIVE_STATUS <> 'C' "+
																					" A.ACTIVE_STATUS = 'Y'  "+
																					" UNION ALL "+
																					" SELECT ODI_REF_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY') VAL_DATE, "+
																					" ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT, "+
																					" 0,A.FIN_NO NO,ODI_DATE,0,CLIENT_CODE,'', "+
																					" CURRENCY_CODE, EXCHANGE_RATE,0,0,0,'ODI',ODI_DATE, "+
																					" "+m_schema_name+".AF_CO_GET_INVOICE_DESCR('ODI') DESCR "+ //B.INVOICE_TYPE
																					" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A, "+
																					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
																					" WHERE  CLIENT_CODE='"+m_client+"' AND "+
																					" A.INVOICE_NO=B.INVOICE_NO AND "+
																					" ODI_BAL_AMOUNT>0 "+
																					" UNION ALL "+
																					" SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE, "+
																					" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																					" VAT_AMOUNT,FINANCE_NO, "+
																					" DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																					" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																					" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																					" 'INV_OTHER' INVOICE_TYPE,VALUE_DATE, "+
																					" "+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(A.INVOICE_TYPE) DESCR "+
																					" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A "+
																					" WHERE CLIENT_CODE='"+m_client+"' AND "+
																					" BALANCE_TO_BE_RECEIVED>0 AND "+
																					" A.INVOICE_TYPE  NOT IN (SELECT INVOICE_TYPE_CODE FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD ) "+
																					//" AND A.ACTIVE_STATUS <> 'C') A, "+
																					" AND A.ACTIVE_STATUS = 'Y') A, "+ //modified nuwan de silva
																					" "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																					" WHERE A.INVOICE_TYPE = B.INVOICE_TYPE_CODE "+
																					" ORDER BY ORDER_NO,VALUE_DATE ");	
   
   
   
																						
																						
																						
																						

									
								 boolean more1 = rs1.next();	
								 int i = 0;
								 if(more1){	
									out.println("<table><tr class=pdn_txtpos2 WIDTH=100%>");
									out.println("  <td WIDTH=15%>Finance No</td>");
									out.println("  <td WIDTH=10%>Vehicle Reg. No</td>");
									out.println("  <td WIDTH=10%>	Invoice Type</td>");
									out.println("  <td WIDTH=10%>Invoice No</td>");
									out.println("  <td WIDTH=10%>Invoiced Date</td>");
									out.println("  <td WIDTH=10%>Due Date</td>");
									out.println("  <td WIDTH=15% align=right>Invoice Amount</td>");
									out.println("  <td WIDTH=15% align=right>Balance Amount</td>");
									out.println("  <td WIDTH=15% align=right>Allocated Amount</td>");
									out.println("  <td WIDTH=10% align=center>Status</td></tr>");
									double m_inv_bal = 0;
									
								 while(more1){	
									out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_"+i+"\">");
									out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs1.getString(7)+"')\" ><u>"+rs1.getString(7)+"</u></td>"); //added by nuwan de silva 26-07-07
									out.println("  <td align=left  >"+rs1.getString(19)+"</td>");
									out.println("  <td align=left  >"+rs1.getString(20)+"</td>");
									out.println("  <td align=left style= cursor:hand; onClick=\"show_invoice_drill('"+rs1.getString(1)+"')\" ><input type=text name=\"INV_NO_"+i+"\" disabled value=\""+rs1.getString(1)+"\" class=\"txt_input2\" style=\"width: 100px\" ></td>");
									out.println("  <td align=left ><input type=text name=\"V_DATE_"+i+"\" disabled value=\""+rs1.getString(2)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=left ><input type=text name=\"D_DATE_"+i+"\" disabled value=\""+rs1.getString(8)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=right><input type=text name=\"INV_AM_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(3))+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=right><input type=text name=\"BAL_AM_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(4))+"\" class=\"txt_input2\">");
									out.println("  <input type=hidden name=\"HID_FIN_NO_"+i+"\" value="+rs1.getString(7)+">");
									out.println("  <input type=hidden name=\"allo_no_"+i+"\" value=\"\"></td>");
									m_inv_bal  = m_inv_bal+rs1.getDouble(4); 
									
									//out.println("111m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
									
									if(m_rec_tot<m_inv_bal){
										if(m_rec_bal>= (m_inv_bal-m_rec_tot)){
										  //out.println("111m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
										  out.println("<td align=right><input type=text name=\"Text_sett_amount"+i+"\" value=\""+nf.format((m_inv_bal-m_rec_tot))+"\" class=\"txt_input2\" onchange=\"chk_manu_bal('"+i+"'),format_number(document.Form1.Text_sett_amount"+i+",30)\" disabled></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+i+"\" onclick=check_status_inv(\""+i+"\") value=\"YES\" checked>");
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+i+",'"+i+"')\" value=\"YES\" checked></td>"); 
									    m_rec_bal = m_rec_bal-(m_inv_bal-m_rec_tot);
											m_rec_tot = m_rec_tot +(m_inv_bal-m_rec_tot);
										}else{
										 if(m_rec_bal>0){ 
										  //out.println("222m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onchange=\"chk_manu_bal('"+i+"'),format_number(document.Form1.Text_sett_amount"+i+",30)\" disabled></td>");//chk_bal('"+i+"')
											//out.println("<td align=right><input type=text name=\"Text_sett_amount"+i+"\" value=\""+(m_rec_bal)+"\" class=\"txt_input2\" onchange=\"chk_manu_bal('"+i+"'),format_number(document.Form1.Text_sett_amount"+i+",30)\" disabled></td>");//chk_bal('"+i+"')
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+i+"\" onclick=check_status_inv(\""+i+"\") value=\"YES\" checked>");
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+i+",'"+i+"')\" value=\"YES\" checked></td>"); 
									    m_rec_tot = m_rec_tot+m_rec_bal;
											m_rec_bal = 0;
										 }else{
											//out.println("444m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onchange=\"chk_manu_bal('"+i+"'),format_number(document.Form1.Text_sett_amount"+i+",30)\"></td>");//chk_bal('"+i+"')
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+i+"\" onclick=check_status_inv(\""+i+"\") value=\"NO\" >");
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+i+",'"+i+"')\" value=\"YES\" checked></td>"); 
									    
										 }	
										}
									}else{
									    //out.println("333m_inv_bal=");
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"chk_manu_bal('"+i+"'),format_number(document.Form1.Text_sett_amount"+i+",30)\"></td>"); //chk_bal('"+i+"')
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+i+"\" onclick=check_status_inv(\""+i+"\") value=\"NO\">");
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+i+",'"+i+"')\" value=\"NO\" ></td>"); 
									}
									
									
									out.println("  </tr>");
									i = i+1;
									more1 = rs1.next();	
									
								 }
										out.println("  <input type=hidden name=hid_invoice_count  value="+i+"></table></div>");
					      
								}else{
								  out.println("  <input type=hidden name=hid_invoice_count  value="+i+"></div>");
					      
								}
								  //m_rec_tot = m_rec_tot +rs.getDouble(4);
							}else{
							
							//modified by nuwan de silva 26-07-07----------------------------------------------------------
				       	rs1 = stmt1.executeQuery ("SELECT B.INVOICE_NO, TO_CHAR(ALLOCATED_DATE,'DD-MM-YYYY'), "+
									                        "       INVOICED_AMOUNT,SETTELED_AMOUNT,ALLOCATION_NO,FINANCE_NO, "+
																					"       NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') "+
																					"FROM   "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_INVOICE  B "+
																					"WHERE  A.INVOICE_NO=B.INVOICE_NO AND RECEIPT_NO = '"+m_rec_no+"' ");
																				
																													
							 boolean more1 = rs1.next();	
								 int i = 0;
								 if(more1){	
									out.println("<table><tr class=pdn_txtpos2 WIDTH=100%>");
									out.println("  <td WIDTH=15%>Finance No</td>");
									out.println("  <td WIDTH=15%>Vehicle Reg. No</td>");
									out.println("  <td WIDTH=20%>Invoice No</td>");
									out.println("  <td WIDTH=15%>Allocated Date</td>");
									out.println("  <td WIDTH=15% align=right>Invoice Amount</td>");
									out.println("  <td WIDTH=15% align=right>Allocated Amount</td>");
									out.println("  <td WIDTH=15% align=center>Status</td></tr>");
									double m_inv_bal = 0;
								 while(more1){	
									out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_"+i+"\">");
									out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs1.getString(6)+"')\" ><u>"+rs1.getString(6)+"</u></td>"); //added by nuwan de silva 26-07-07
									out.println("  <td align=left style= cursor:hand; onClick=\"\" >"+rs1.getString(7)+"</td>");
									out.println("  <td align=left ><input type=text name=\"INV_NO_"+i+"\" disabled value=\""+rs1.getString(1)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=left ><input type=text name=\"V_DATE_"+i+"\" disabled value=\""+rs1.getString(2)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=right><input type=text name=\"INV_AM_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(3))+"\" class=\"txt_input2\"></td>");
									out.println("<td align=right><input type=text name=\"Text_sett_amount"+i+"\" value=\""+nf.format(rs1.getDouble(4))+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");//chk_bal('"+i+"')
									out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+i+"\" onclick=check_status_inv(\""+i+"\") value=\"YES\" checked disabled >"); //modified by nuwan de silva 25-07-07
									out.println("  <input type=hidden name=\"HID_FIN_NO_"+i+"\" value="+rs1.getString(7)+">");
									out.println("  <input type=hidden name=\"allo_no_"+i+"\" value=\""+(rs1.getString(5))+"\"></td>");
									
									out.println("  </tr>");
									//out.println("m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
									i = i+1;
									more1 = rs1.next();	
									
								 }
										out.println("  <input type=hidden name=hid_invoice_count  value="+i+"></table></div>");
					      
								}else{
								  out.println("  <input type=hidden name=hid_invoice_count  value="+i+"></div>");
					      
								}
								
							
							}

			
			
			}else if(m_chksql.trim().equals("get_return_receipt")){
			
			    String m_client      = req.getParameter("client");
																
					rs = stmt.executeQuery ("SELECT  b.RETURN_NO,b.DIPOSIT_NO,b.RECEIPT_NO,NVL(a.CHEQUE_NO,'-'), "+
																	"				NVL(a.PAYER_ACC_NO,'-'),b.AMOUNT,b.ALLOCATED_AMOUNT,b.BAL_AMOUNT "+
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
                  out.println("<tr class=tr_input  >");
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
					
			  }
				
				else if(m_chksql.trim().equals("get_return_receipt2")){
			
			    String m_client      = req.getParameter("client");
					String m_rec_no      = req.getParameter("rec_no");
															 
		rs = stmt.executeQuery(" SELECT B.RETURN_NO,B.DIPOSIT_NO,B.RECEIPT_NO,NVL(A.CHEQUE_NO,'-'), "+
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
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='15%' >Receipt No</td>");
					out.println("<td  width='15%' align=right>Receipt Amount</td>");
          out.println("<td  width='15%' align=right>Allocated Amount</td>");
					out.println("<td  width='20%' align=right>Balance Amount</td>");
					out.println("<td  width='35%' align=right>Amount</td>");
					//out.println("<td  width='10%'  ></td>");
					out.println("</tr>");
      
           int j = 0;      					
							
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td >"+rs.getString(1) +"<input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"ALLO_AMOUN_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");//out.pr	
                  out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled>");
									out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
                  //out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
									//out.println("     </td>");
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
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td >"+rs.getString(1) +"<input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"ALLO_AMOUN_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled></td>");
									//out.println("<input type=button name=inv_h_"+j+" value=\"Help\" class=mainbut1 onclick=inv_help('"+j+"');></td>");
                  //out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
									//out.println("     </td>");
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
					//out.println(" }");
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
			
			else if(m_chksql.equals("view_cheques")){		
			int count=0;		
			
			String m_cheque_no = req.getParameter("cheque_no");	
			String m_branch_code= req.getParameter("branch_code");	
				  										
					 rs = stmt.executeQuery (" SELECT CLIENT_CODE,NVL(REC_NO,'-'),NVL(CHEQUE_NO,'-'),NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY'),NVL(PAYER_ACC_NO,'-'), "+
           " PAYER_BRANCH_CODE,REC_AMOUNT "+
           " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
           " WHERE UPPER(PAYER_BRANCH_CODE)=UPPER('"+m_branch_code+"') AND UPPER(CHEQUE_NO)=UPPER('"+m_cheque_no+"') ");
						
										
					out.println("<HTML><HEAD><TITLE>Receipt Details</TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Receipt Details</B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
					boolean more = rs.next();
					
			   if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				if (more) {
				
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>The cheque number that has been entered already exists </u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='12%' class=div_input><b>Client Code</b></td>");
					out.println("<td width='15%' class=div_input><b>Receipt No</b></td>");
					out.println("<td width='12%' class=div_input><b>Cheque No</b></td>");
					out.println("<td width='12%' class=div_input><b>Cheque Date</b></td>");
					out.println("<td width='15%' class=div_input><b>Account No</b></td>");
					out.println("<td width='15%' class=div_input><b>Branch</b></td>");
					out.println("<td width='15%' align='right' class=div_input><b>Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='12%' class=div_input style= cursor:hand; onclick=show_client('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='12%' class=div_input >"+rs.getString(3)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs.getString(4)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(5)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(6)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(7))+"</td>");
					out.println("</tr>");
					more = rs.next();
				}
				  out.println("</table>");
					
				  out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b>In the event this is the genuine cheque number please add a suffix to the actual number </b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b>Example :</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Actual Number</b></td>");
					out.println("<td width='*%' class=div_input><b>123456</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b>Please enter additional records as </b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Example :</b></td>");
					out.println("<td width='*%' class=div_input><b>123456<font color='red'>A</font></b></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='*%' class=div_input><b>123456<font color='red'>B</font>&nbsp;&nbsp;etc.</b></td>");
					out.println("</tr>");
					out.println("</table>");
					
				  
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");

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









																					

