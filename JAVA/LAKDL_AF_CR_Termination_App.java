//Option Id is 1.65 
//This File was created by  
//1.65 Allocate Unallocated Receipt Process Display
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;

import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_Termination_App extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1;
	public String m_chksql;
	ServletOutputStream out = null;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			//LAKDL_AF_CO_FU_methods CO_methods = new LAKDL_AF_CO_FU_methods();
			
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
			//out.println("conn="+conn);
			//Class.forName("oracle.jdbc.driver.OracleDriver");
      //conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
			CallableStatement callstmt1 =null;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(2);
			
			//nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  //nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			stmt1= conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					
	    else if(m_chksql.trim().equals("main_page")){
			
	      String m_Followu_no   = req.getParameter("Followu_no");
        String m_Status   = req.getParameter("Status");
          
	      out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Termination Check</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
      
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
        out.println("      if(opt==\"2\"){");
				//out.println("         alert(http_request.responseText);");
				out.println("         inv.innerHTML=http_request.responseText; ");
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
				
				out.println("      }else if(opt==\"6\"){");
				//out.println("         alert(http_request.responseText);");
				out.println("         document.Form1.tot_val.value=http_request.responseText; ");
				out.println("         document.Form1.elements[\"SETT_AMOUN_0\"].value=format_noobject(document.Form1.tot_val.value); ");
				
				out.println("      }else if(opt==\"5\"){");
				//out.println("         alert(http_request.responseText);");
				out.println("         inv.innerHTML=http_request.responseText; ");
				out.println("      }else if(opt==\"4\"){");
				out.println("   				data_vec= new Array(); ");

		 		out.println("           var xmlbody=http_request.responseXML.documentElement;");
				out.println("           var vsize=0;");

   			out.println("  				  for(var i=0;i<xmlbody.childNodes.length;i++){");
				out.println("    				 for(var j=0;j<xmlbody.childNodes[i].childNodes.length;j++){");
				out.println("      			  data_vec[vsize]=xmlbody.childNodes[i].childNodes[j].text;");
				out.println("       			vsize++;");
					//alert(data_vec[vsize]);
				out.println("    				}");
				out.println("   		   }");
		
				out.println("          addrow(data_vec,type);");
                      
				out.println("      }");
				out.println("    } else {");
        out.println("        load_followup.innerHTML='';");
        out.println("    }");
        out.println(" }");
        out.println("}");
				
				
       out.println("function addrow( data,type) {");
       out.println(" str=\"\";");
       out.println(" i=0;");
       out.println(" if(data.length>0){");
       out.println("   if(type=='Rec'){"); 
			 out.println("     document.Form1.RECEPT_NO.value       =data[0];"); 
			 out.println("     get_Receipt_del();");
			 out.println("   }else if(type=='Cli'){"); 
			 out.println("     document.Form1.CLIENT_CODE.value     =data[0];"); 
			 out.println("     auto_allocate();");
			out.println("   }else if(type=='rec_det'){"); 
			out.println("      document.Form1.elements['Text_standard'+document.Form1.hid_opt_val.value].value =\"NO\";");
			out.println("      document.Form1.elements['Text_standard'+document.Form1.hid_opt_val.value].checked=false;");
			out.println("      document.Form1.elements['TERM_TYPE_'+document.Form1.hid_opt_val.value].disabled =false;");
			out.println("      alert('There are pending receipts which have not yet got realized please complete the stage prior to saving this termination.');");
				
			 out.println("   }else if(type=='Lea'){"); 
			 out.println("     document.Form1.LEASE_NO.value        =data[0];"); 
			 out.println("     get_Receipt();");
			 out.println("   }"); 
       out.println(" }");
       out.println("}");
				//End Of Checking Values
				
				out.println("function Price(num,num1,amount,finno){");
				out.println(" document.Form1.hid_opt_val.value=num;"); 
			  out.println("	popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?chksql=main_page&screen_type=Term&amount=\"+amount+\"&app_no=\"+finno+\"\", \"oBj\",\"left=20,top=100,width=980,height=610\");"); 
			  out.println("}");		
								
				out.println("function inv_help(num){");
				out.println(" document.Form1.hid_opt_val.value=num;"); 
			  out.println("	popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_Termination_App?chksql=get_Invoice&client=\"+document.Form1.CLIENT_CODE.value+\"\", \"oBj\",\"left=130,top=200,width=750,height=400\");"); 
			  out.println("}");	
				
				/*
				out.println("function cal_amount(opt,am1,am2,num,inv_no) {");//
				out.println("  document.Form1.hid_win_opt.value=num;");
				out.println("  m_inv_bal = 0;");
				out.println("  m_inv_allo= 0;");
				out.println("  for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
				out.println("   if(i!=parseFloat(document.Form1.hid_opt_val.value)){ ");
				out.println("    for(j=0;j<parseFloat(document.Form1.elements['hid_invoice_count_'+i].value);j++){");	
				out.println("      if(document.Form1.elements['TER_NO_'+i+'_'+j].value   ==inv_no){"); //
				out.println("        m_inv_allo = parseFloat(m_inv_allo)+parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+i+'_'+j].value)) ");	
				out.println("        m_inv_bal  = parseFloat(document.Form1.elements['BAL_AM_'+i+'_'+j].value); ");	
				out.println("      }");	
				out.println("    }");	
				out.println("   }");
				out.println("  }");
				out.println("  alert('m_inv_allo='+m_inv_allo+'---m_inv_bal='+m_inv_bal+'---am2='+am2)");
        out.println("   if(parseFloat(m_inv_allo)>0){"); 
				out.println("   if(parseFloat(m_inv_allo)>=parseFloat(m_inv_bal)){ ");
				out.println("           popupwin.document.Form1.elements['Text_standard'+num].value      =\"NO\";");
				out.println("           popupwin.document.Form1.elements['Text_sett_amount'+num].disabled=false;");
				out.println("           popupwin.document.Form1.elements['Text_standard'+num].checked    =false;");
				
				out.println("   }else{");	
				out.println("     if(parseFloat(m_inv_bal-m_inv_allo)<parseFloat(am2)){ ");
				out.println("           popupwin.document.Form1.elements['Text_standard'+num].value      =\"NO\";");
				out.println("           popupwin.document.Form1.elements['Text_sett_amount'+num].disabled=false;");
				out.println("           popupwin.document.Form1.elements['Text_standard'+num].checked    =false;");
				
				out.println("     }else{");	
				  
				out.println("       m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_Termination_App?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
        //out.println("     window.open(m_url);");
				out.println("       makeRequest(m_url,'3');");
				out.println("     }");	
				out.println("   }");	
				out.println("     }else{");	
				  
				out.println("       m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_Termination_App?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
        //out.println("     window.open(m_url);");
				out.println("       makeRequest(m_url,'3');");
				out.println("     }");	
				
        out.println("}");	
				*/
				
				out.println("function cal_amount(num1,num2) {");//
				out.println("  document.Form1.hid_win_opt.value=num2;");
				out.println("  document.Form1.hid_opt_val.value=num1;");
				out.println("  m_inv_bal  = 0;");
				out.println("  m_inv_allo = 0;");
				out.println("  m_rec_allo = 0;");
				out.println("  m_rec_bal  = parseFloat(document.Form1.elements['BAL_AM_'+num1].value);");
				//out.println("  alert(document.Form1.elements['TER_NO_'+num1].value);"); //
				
				out.println("  for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
				//out.println("   if(i!=parseFloat(num1)){ ");
				out.println("    for(j=0;j<parseFloat(document.Form1.elements['hid_invoice_count_'+i].value);j++){");	
				out.println("      if(document.Form1.elements['REC_NO_'+i+'_'+j].value   ==document.Form1.elements['REC_NO_'+num1+'_'+num2].value){"); //
				out.println("       if(document.Form1.elements['Text_standard'+i+'_'+j].checked){");
				//out.println("        alert(document.Form1.elements['SETT_AMOUN_'+i+'_'+j].value); ");	
				out.println("        m_inv_allo = parseFloat(m_inv_allo)+parseFloat(unformat_noobject(document.Form1.elements['SETT_AMOUN_'+i+'_'+j].value)) ");	
				out.println("        m_inv_bal  = parseFloat(document.Form1.elements['BAL_AMOUNT_'+i+'_'+j].value); ");	
				out.println("       }");	
				out.println("      }");	
				out.println("    }");	
				//out.println("   }");
				out.println("  }");
				//  
				out.println("    for(j=0;j<parseFloat(document.Form1.elements['hid_invoice_count_'+num1].value);j++){");	
				out.println("      //if(j!=parseFloat(num2)){ ");
				out.println("      if(document.Form1.elements['Text_standard'+num1+'_'+j].checked){");
				out.println("        m_rec_allo = parseFloat(m_rec_allo)+parseFloat(unformat_noobject(document.Form1.elements['SETT_AMOUN_'+num1+'_'+j].value)) ");	
				out.println("      }"); 
				out.println("      //}"); 
				//out.println("        m_inv_bal  = parseFloat(document.Form1.elements['BAL_AM_'+num1+'_'+j].value); ");	
				out.println("    }");	
				
				//out.println("  alert('m_inv_allo='+m_inv_allo+'---m_inv_bal='+m_inv_bal+'---m_rec_bal='+m_rec_bal+'---m_rec_allo='+m_rec_allo)");
        out.println("   if(parseFloat(m_inv_allo)>0){"); 
				out.println("   if(parseFloat(m_inv_allo)>parseFloat(m_inv_bal)){ ");
				out.println("      document.Form1.elements['Text_standard'+num1+'_'+num2].value      =\"NO\";");
				out.println("      document.Form1.elements['SETT_AMOUN_'+num1+'_'+num2].disabled=false;");
				out.println("      document.Form1.elements['SETT_AMOUN_'+num1+'_'+num2].value   =0;");
				out.println("      document.Form1.elements['Text_standard'+num1+'_'+num2].checked    =false;");
				out.println("   }else{");	
				out.println("     if(parseFloat(m_inv_bal-m_inv_allo)<parseFloat(m_rec_bal) && parseFloat(m_rec_bal)<parseFloat(m_rec_allo)){ ");
				out.println("      document.Form1.elements['Text_standard'+num1+'_'+num2].value      =\"NO\";");
				out.println("      document.Form1.elements['SETT_AMOUN_'+num1+'_'+num2].disabled=false;");
				out.println("      document.Form1.elements['SETT_AMOUN_'+num1+'_'+num2].value   =0;");
				out.println("      document.Form1.elements['Text_standard'+num1+'_'+num2].checked    =false;");
				out.println("     }else{");
				out.println("      document.Form1.elements['Text_standard'+num1+'_'+num2].value      =\"YES\";");
				out.println("      document.Form1.elements['SETT_AMOUN_'+num1+'_'+num2].disabled=true;");
				//out.println("       m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_ReceiptAllocation?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
        //out.println("       window.open(m_url);");
				//out.println("       makeRequest(m_url,'3');");
				out.println("     }");	
				out.println("   }");
				out.println("   }else if(parseFloat(m_rec_bal)<parseFloat(m_rec_allo)){"); 
				out.println("      document.Form1.elements['Text_standard'+num1+'_'+num2].value      =\"NO\";");
				out.println("      document.Form1.elements['SETT_AMOUN_'+num1+'_'+num2].disabled=false;");
				out.println("      document.Form1.elements['SETT_AMOUN_'+num1+'_'+num2].value   =0;");
				out.println("      document.Form1.elements['Text_standard'+num1+'_'+num2].checked    =false;");
				
				out.println("     }else{");	
				out.println("      document.Form1.elements['Text_standard'+num1+'_'+num2].value      =\"YES\";");
				out.println("      document.Form1.elements['SETT_AMOUN_'+num1+'_'+num2].disabled=true;");
				//out.println("       m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_ReceiptAllocation?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
        //out.println("     window.open(m_url);");
				//out.println("       makeRequest(m_url,'3');");
				out.println("     }");	
				out.println("}");	
				
				
				out.println("function cal_amount1(opt,am1,am2,num) {");//
				out.println("   document.Form1.hid_win_opt.value=num;");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_Termination_App?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'3');");
				
        out.println("}");	
				
				out.println("function cal_amount_del(opt,am1,am2,num) {");//
				out.println("   document.Form1.hid_win_opt.value=num;");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_Termination_App?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'6');");
				
        out.println("}");	
				
			  out.println("function get_Receipt(m_stat,opt) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_Termination_App?chksql=get_Receipt&client=\"+document.Form1.CLIENT_CODE.value+\"&Status="+m_Status+"\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'2');");
				
        out.println("}");	
				
				out.println("function load_allo_window(num,code) {");
				out.println("	if(num!=''){ ");
	      out.println("	  popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Ter_ReceiptAllocation?chksql=main_page&Ter_no=\"+num+\"&Cli_no=\"+code, \"oBj\",\"left=30,top=50,width=900,height=600\");"); 
				out.println("	}");
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
				
				out.println("function get_Receipt_del(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_Termination_App?chksql=get_Receipt_del&rec_no=\"+val+\"\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'5');");
				
        out.println("}");	
				
				out.println("function check_receipt(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_XMLFile?chksql=get_rec_no&rec_no=\"+document.Form1.RECEPT_NO.value+\"\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','Rec');");
				
        out.println("}");
				
				out.println("function check_lease(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_XMLFile?chksql=get_lea_no&lea_no=\"+document.Form1.LEASE_NO.value+\"\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','Lea');");
				
        out.println("}");
				
				out.println("function check_receipt_det(num1) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_rec_det&lea_no=\"+document.Form1.elements['FIN_NO_'+num1].value+\"\";");
        //out.println("   window.open(m_url);");
				out.println("   document.Form1.hid_opt_val.value=num1;");
				out.println("   makeRequest(m_url,'4','rec_det');");
				
        out.println("}");
				
				out.println("function check_client(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_XMLFile?chksql=get_client_code&client_code=\"+document.Form1.CLIENT_CODE.value+\"\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','Cli');");
				
        out.println("}");
				
				out.println("function load_all_foll(m_stat,opt) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup?chksql=get_followup&fno="+m_Followu_no+"\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,opt);");
				out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("m_sub=0;"); 
			/*
			out.println("if(document.Form1.hid_option.value==\"NEW\"){"); 
			out.println("if(document.Form1.TXT_FOLLOW_UP_NO.value==\"\"){  "); 
			out.println("FNO.style.color='red';");
			out.println("m_sub = 1;;"); 
			out.println("}"); 
			out.println("if(document.Form1.TXT_ACTION_TOBE_TAKEN.value==\"\"){  "); 
			out.println("ATBT.style.color='red';");
			out.println("m_sub = 1;;"); 
			out.println("}"); 
			out.println("if(document.Form1.TXT_EFF_VAL_DATE.value==\"\"){  "); 
			out.println("TDA.style.color='red';");
			out.println("m_sub = 1;;"); 
			out.println("}");
			
			out.println("}else if(document.Form1.hid_option.value==\"EDIT\"){"); 
			
			out.println("if(document.Form1.TXT_ACTION_TOOK_DATE.value==\"\"){  "); 
			out.println("AAD.style.color='red';");
			out.println("m_sub = 1;;"); 
			out.println("}"); 
			out.println("if(document.Form1.TXT_ACTION_TOOK.value==\"\"){  "); 
			out.println("AT.style.color='red';");
			out.println("m_sub = 1;;"); 
			out.println("}"); 
			out.println("}");
			*/
			out.println("if(m_sub=='1'){");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function befor_submit(){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to save?\")){ "); 
			out.println("		document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_Save';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			if(m_Followu_no==null){
			out.println("		window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup';"); 
			}else{
			out.println("		window.close();");
			}
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 
      
			out.println("function befor_back(){");
			out.println("   close_window(); ");
		  out.println("}");
			
			out.println("function befor_reset(){");
			out.println(" if(confirm(\"Are you sure you want to clear the screen?\")){  ");
			//out.println("  Form1.reset()   ");
		  out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_Termination_App?chksql=main_page&Status="+m_Status+"'");
		  out.println(" }  ");
			out.println("}");
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CO_Followup\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"Finance - Termination - Processing - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"Finance - Termination - Processing - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function load_screen_status(m_val){"); 
			out.println("    document.Form1.hid_option.value    =m_val;"); 
			out.println("if(m_val==\"NEW\"){"); 
			//out.println("new_window();");
			//out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.CLIENT_CODE.disabled=false;"); 
			out.println("document.Form1.cli_help.disabled=false;"); 
			out.println("document.Form1.rec_help.disabled=true;"); 
			out.println("document.Form1.RECEPT_NO.disabled=true;"); 
			
			out.println("}else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val==\"DELETE\"){"); 
			out.println("document.Form1.CLIENT_CODE.disabled=true;"); 
			out.println("document.Form1.cli_help.disabled=true;"); 
			out.println("document.Form1.rec_help.disabled=false;"); 
			out.println("document.Form1.RECEPT_NO.disabled=false;"); 
			
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
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

      out.println("function auto_allocate(){"); 
      //out.println("  popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_Termination_App?chksql=RUN_AUTO_ALLO&client='+document.Form1.CLIENT_CODE.value+'&screen='+document.Form1.Hid_scr_name.value+'', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			//out.println("  get_Receipt();"); 
      out.println("  get_Receipt();"); 
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
			/*
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_CO_Help_Servlet?class_in=\"+client_name+\"AF_CO_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			*/
			//out.println("window.open('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_CO_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=');");
			out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_CR_Ter_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
			out.println("	"); 
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
			out.println("		if(IfCount==\"4\"){"); 
			out.println("		vehicle_assign(oBj);"); 
	  	out.println("		}");
			out.println("		if(IfCount==\"3\"){"); 
			out.println("		lease_assign(oBj);"); 
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
				out.println("HelpBox('1','10','0',Crit,'ClientTSql','1');");
				out.println("}");		
				out.println("function client_assign(oBj){");
				out.println(" document.Form1.CLIENT_NAME.value =oBj.valout[3]");
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[2]");
				out.println(" check_client(document.Form1.CLIENT_CODE.value);");
				out.println("}");
			 
			//Receipt Help
			  out.println("function receipt_help(){");
				out.println("Crit=document.Form1.RECEPT_NO.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'AlloReceiptSql','2');");
				out.println("}");	
				
				out.println("function receipt_assign(oBj){");
				out.println(" document.Form1.RECEPT_NO.value =oBj.valout[2]");
				out.println(" get_Receipt_del(document.Form1.RECEPT_NO.value);");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");
			//Lease Help
			  out.println("function lease_help(){");
				out.println("Crit=document.Form1.LEASE_NO.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'LeaseSql','3');");
				out.println("}");	
				
				out.println("function lease_assign(oBj){");
				out.println(" document.Form1.LEASE_NO.value =oBj.valout[2]");
				out.println(" get_Receipt_del(document.Form1.LEASE_NO.value);");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");
			//Vehicle Help
			  out.println("function vehicle_help(){");
				out.println("Crit=document.Form1.VEHICLE_NO.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'VehicleSql','4');");
				out.println("}");	
				
				out.println("function vehicle_assign(oBj){");
				out.println(" document.Form1.VEHICLE_NO.value =oBj.valout[2]");
				out.println(" get_Receipt_del(document.Form1.VEHICLE_NO.value);");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");
						

 
			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_FOLLOW_UP_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_ID_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_ACTION_TOBE_TAKEN.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_EFF_VAL_DATE.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_ACTION_TOOK.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_ACTION_TOOK_DATE.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_ACTION_SET_FOR.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_SCREEN_NAME.value=oBj.valout[9];"); 
			out.println("    document.Form1.TXT_DIVISION_CODE.value=oBj.valout[10];"); 
			out.println("    document.Form1.TXT_ENT_REMARKS.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_REMARKS.value=oBj.valout[12];"); 
			out.println("    document.Form1.TXT_ACTION_ENT_DATE.value=oBj.valout[13];"); 
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
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
							
			
			
			

			//
			out.println("function check_status(num1) {");
			
			//out.println("alert(document.Form1.elements['TERM_TYPE_'+num1].value);");
			//out.println("alert(document.Form1.elements['SETT_A_'+num1].value);");
			//added temp
			out.println("       document.Form1.elements['Text_standard'+num1].value=\"YES\";");
			//out.println("       document.Form1.elements['TERM_TYPE_'+num1].disabled=true;");
			
			/* temp
			out.println("if(document.Form1.elements['Text_standard'+num1].checked){");//SETT_A_   DUE_AM_
			//out.println("alert('0');");
			out.println("  if(parseFloat(document.Form1.elements['SETT_A_'+num1].value)>0){");
			//out.println("alert('1');");
			out.println("   if(document.Form1.elements['TERM_TYPE_'+num1].value!='RESCHEDULE' && ");
      out.println("      document.Form1.elements['TERM_TYPE_'+num1].value!='RENT_REVIS' && ");
			out.println("      document.Form1.elements['TERM_TYPE_'+num1].value!='BALANCE_RE'){ ");
			//out.println("alert('2');");
			out.println("       document.Form1.elements['Text_standard'+num1].value=\"YES\";");
			out.println("       document.Form1.elements['TERM_TYPE_'+num1].disabled=true;");
			out.println("       check_receipt_det(num1);");
			out.println("   }else{");
			//out.println("alert('3');");
			out.println("       document.Form1.elements['Text_standard'+num1].checked=false;");
			out.println("       document.Form1.elements['Text_standard'+num1].value =\"NO\";");
			out.println("       document.Form1.elements['TERM_TYPE_'+num1].disabled =false;");
			out.println("   }");
			out.println("  }else{");
			//out.println("alert('4');");
			
			out.println("    if(document.Form1.elements['TERM_TYPE_'+num1].value=='RESCHEDULE' || ");
      out.println("       document.Form1.elements['TERM_TYPE_'+num1].value=='RENT_REVIS' ){ ");
			//out.println("alert('5');");
			out.println("     if(parseFloat(document.Form1.elements['A_V_COUNT_'+num1].value)==parseFloat(document.Form1.elements['T_V_COUNT_'+num1].value)){");
			out.println("        document.Form1.elements['Text_standard'+num1].value=\"YES\";");
			out.println("        document.Form1.elements['TERM_TYPE_'+num1].disabled=true;");
			out.println("        check_receipt_det(num1);");
			out.println("     }else{");
			//out.println("alert('6');");
			out.println("       document.Form1.elements['Text_standard'+num1].checked=false;");
			out.println("       document.Form1.elements['Text_standard'+num1].value =\"NO\";");
			out.println("       document.Form1.elements['TERM_TYPE_'+num1].disabled =false;");
			out.println("     }");
			
			
			out.println("    }else if(document.Form1.elements['TERM_TYPE_'+num1].value=='BALANCE_RE' && document.Form1.elements['VAT_'+num1].value=='0'){ ");
			out.println("     if(parseFloat(document.Form1.elements['A_V_COUNT_'+num1].value)==parseFloat(document.Form1.elements['T_V_COUNT_'+num1].value)){");
			out.println("        document.Form1.elements['Text_standard'+num1].value=\"YES\";");
			out.println("        document.Form1.elements['TERM_TYPE_'+num1].disabled=true;");
			out.println("        check_receipt_det(num1);");
			out.println("     }else{");
			//out.println("alert('3');");
			out.println("       document.Form1.elements['Text_standard'+num1].checked=false;");
			out.println("       document.Form1.elements['Text_standard'+num1].value =\"NO\";");
			out.println("       document.Form1.elements['TERM_TYPE_'+num1].disabled =false;");
			out.println("     }");
			out.println("    }else if(document.Form1.elements['TERM_TYPE_'+num1].value=='BALANCE_RE' && document.Form1.elements['VAT_'+num1].value!='0'){ ");
			out.println("       alert('Please recalculate on zero VAT rate.');");      
			out.println("       document.Form1.elements['Text_standard'+num1].checked=false;");
			out.println("       document.Form1.elements['Text_standard'+num1].value =\"NO\";");
			out.println("       document.Form1.elements['TERM_TYPE_'+num1].disabled =false;");
			out.println("    }else{");
			//out.println("alert('6');");
			out.println("        document.Form1.elements['Text_standard'+num1].checked=false;");
			out.println("        document.Form1.elements['Text_standard'+num1].value =\"NO\";");
			out.println("        document.Form1.elements['TERM_TYPE_'+num1].disabled =false;");
			out.println("    }");
			
			//out.println("  document.Form1.elements['SETT_AMOUN_'+num1].value    = \"0.00\";");
			//out.println("  document.Form1.elements['Text_standard'+num1].value  = \"NO\";");
			//out.println("  document.Form1.elements['SETT_AMOUN_'+num1].disabled = false;");
			out.println(" }");
			
			out.println("}else{");
			//out.println("alert('7');");
			out.println("       document.Form1.elements['Text_standard'+num1].checked=false;");
			out.println("       document.Form1.elements['Text_standard'+num1].value =\"NO\";");
			out.println("       document.Form1.elements['TERM_TYPE_'+num1].disabled =false;");
			
			out.println("  }");
			temp*/
			
			
			out.println("}");   
				
			/*
			out.println("function check_status(num) {");
			//out.println("alert(document.Form1.elements['Text_standard'+num].checked);");
			out.println("if(document.Form1.elements['Text_standard'+num].checked){");
			out.println("  document.Form1.elements['Text_standard'+num].value=\"YES\";");
			out.println("}else{");
			out.println("  document.Form1.elements['Text_standard'+num].value=\"NO\";");
			out.println("}");
			out.println("}");
			*/
			
			out.println("function check_amount(num) {");
			//out.println("alert(document.Form1.elements['Text_standard'+num].checked);");
			out.println("if(Number(document.Form1.elements['Text_sett_amount'+num].value)>Number(document.Form1.elements['Hid_amount'+num].value)){");
			out.println(" alert('Amount cannot be greater than Net Amount');");
			out.println(" document.Form1.elements['Text_sett_amount'+num].value = document.Form1.elements['Hid_amount'+num].value;");
			out.println("}");
			out.println("}");
			
			
			out.println("function check_status_del(num) {");
			//out.println("alert(document.Form1.elements['Text_standard'+num].checked);");
			out.println("if(document.Form1.elements['Text_standard_0_'+num].checked){");
			
			out.println(" document.Form1.elements['Text_standard_0_'+num].value=\"YES\";");
			out.println(" cal_amount_del('add',unformat_noobject(document.Form1.SETT_AMOUN_0.value),unformat_noobject(document.Form1.elements['Text_sett_amount0_'+num].value),num);");
			out.println("}else{");
			out.println(" document.Form1.elements['Text_standard_0_'+num].value=\"NO\";");
			out.println(" cal_amount_del('min',unformat_noobject(document.Form1.SETT_AMOUN_0.value),unformat_noobject(document.Form1.elements['Text_sett_amount0_'+num].value),num);");
			out.println("}");
			out.println("}");
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_out_value();load_lock();get_Receipt();\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input type='hidden' name='Hid_scr_name' value='AF_LEGAL_TERMINATION_CHECK' > ");
			out.println("<input type='hidden' name='TXT_SCREEN_NAME' value='AF_LEGAL_TERMINATION_CHECK' > ");
			out.println("<INPUT TYPE='Hidden' NAME='hid_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_opt_status' VALUE=\""+m_Status+"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"NEW\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_win_type' VALUE=\"Main\">"); 			
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"NEW\">");
			out.println("<input type=hidden name=\"tot_val\" value=\"0\">");
			out.println("<input type=hidden name=\"hid_opt_val\" value=\"0\">");
			out.println("<input type=hidden name=\"hid_win_opt\" value=\"0\">");
			out.println("<input type=hidden name=\"CLIENT_CODE\" value=\"\">");
			
				
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
				out.println("<td style=\"width: 6px\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td width=10%>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset value=\"New\" class=mainbut onclick=load_screen_status(\"NEW\"); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");//document.Form1.OPTION_DESC.value
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=edit value=\"Delete\" class=mainbut onclick=load_screen_status(\"DELETE\"); onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				//out.println("<td width=10%>&nbsp;</td>");
				//out.println("<td><input type=button name=delete value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel value=\"Re-active\" class=mainbut onclick=befor_active(); onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=b_submit value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td><input class='mainbut' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled>  </td>"); 
			  out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Cancel\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td ><input type=button name=cal value=\"Calculate\" class=mainbut onclick=befor_cal();></td>");
      
				out.println("</tr></table>");
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				
				
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
				
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" colspan=4>");
				out.println("<div id=inv>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("</table>");
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
				
				out.println("<td style=\"width: 6px\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td width=10%>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=new_1 value=\"New\" class=mainbut onclick=load_screen_status(\"NEW\"); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");//document.Form1.OPTION_DESC.value
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=edit value=\"Delete\" class=mainbut onclick=load_screen_status(\"DELETE\"); onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				//out.println("<td width=10%>&nbsp;</td>");
				//out.println("<td><input type=button name=edit_1 value=\"Edit\" class=mainbut onclick=befor_modify(); onMouseOver='load_roll_value(\"Edit\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td width=10%>&nbsp;</td>");
				//out.println("<td><input type=button name=delete value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel value=\"Re-active\" class=mainbut onclick=befor_active(); onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=b_submit_1 value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td><input class='mainbut' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled>  </td>"); 
			  out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back_1 value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset_1 value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Cancel\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				
				out.println("</tr></table>");
				out.println("&nbsp;&nbsp;</td>");
				out.println("</tr>");

				//out.println("<tr class=tr_input>");
				//out.println("<td class=\"pdn_txtpos1 & txt-bodyRed\">");
				//out.println("</td>");
				//out.println("</tr>");
				//out.println("</table>");
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
			out.println("</body>"); 
			out.println("</html>"); 
		
      } 
			
			else if(m_chksql.trim().equals("get_Receipt")){
			
			    String m_client      = req.getParameter("client");
					String m_term_type   = req.getParameter("term_type");					
          String m_Status      = req.getParameter("Status");
        
			    String m_ter_type="";
				
				
			
			    out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=10 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
										
					rs = stmt.executeQuery (" SELECT TERMINATION_NO,FINANCE_NO,APPLICATION_NO, "+
					                        "        "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE), "+
																	"        ACTIVE_STATUS,ENT_USER,CLIENT_CODE, "+
																	"        ENT_DATE,ARR_AMOUNT_TOT,CAPITAL_AMOUNT, "+
																	"        INTEREST_AMOUNT,ODI_NET,NVL(REMARKS,'-'),FIRST_APPR_BY, "+
																	"        TO_CHAR(FIRST_APP_DATE,'DD-MM-YYYY HH24:MI:SS'), "+
																	"        SECOND_APPR_BY, "+
																	"        TO_DATE(SECOND_APP_DATE,'DD-MM-YYYY HH24:MI:SS') "+
																	" FROM   "+m_schema_name+".AF_CR_PRO_LEGAL_TERMINATION "+
																	" WHERE  ACTIVE_STATUS =UPPER('"+m_Status+"') AND  CLIENT_CODE LIKE '"+m_client+"%' ");
					
					
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='12%' >Client Name</td>");
					out.println("<td  width='12%' >Termination No</td>");
					out.println("<td  width='12%' >Finance No</td>");
          out.println("<td  width='8%' align=right>Arrears Amount</td>");
					out.println("<td  width='8%' align=right>Capital Outstanding</td>");
					out.println("<td  width='8%' align=right>Interest Outstanding</td>");
					out.println("<td  width='8%' align=right>ODI Amount</td>");
					out.println("<td  width='18%' >Remarks</td>");
					out.println("<td  width='8%' >Status</td>");
					out.println("</tr>");
      
           int j = 0; 
					 double m_ter_all = 0;
					 double m_ter_bal = 0;
							
              while(rs.next()){
							    //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/>"); 
									out.println("<td >"+rs.getString(4)+"<input type=hidden name=\"CLI_NO_"+j+"\" value=\""+rs.getString(7)+"\"></td>");
                  out.println("<td class=hs onclick=load_allo_window('"+rs.getString(1)+"','"+rs.getString(7)+"')>"+rs.getString(1) +"<input type=hidden name=\"TER_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td >"+rs.getString(2) +"<input type=hidden name=\"FIN_NO_"+j+"\" value=\""+rs.getString(2)+"\"><input type=hidden name=\"APP_NO_"+j+"\" value=\""+rs.getString(3)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(9))+"<input type=hidden name=\"ARR_AMT_"+j+"\" value=\""+rs.getString(9)+"\"><input type=hidden name=\"A_V_COUNT_"+j+"\" value=\""+rs.getString(14)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(10))+"<input type=hidden name=\"CAP_OUT_"+j+"\" value=\""+rs.getString(10)+"\"><input type=hidden name=\"T_V_COUNT_"+j+"\" value=\""+rs.getString(15)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(11))+"<input type=hidden name=\"INT_OUT_"+j+"\" value=\""+(rs.getString(11))+"\"></td>");
									out.println("<td align=right>"+nf.format(rs.getDouble(12))+"<input type=hidden name=\"ODI_AMT_"+j+"\" value=\""+rs.getString(12)+"\"><input type=hidden name=\"VAT_"+j+"\" value=\""+rs.getString(16)+"\"></td>");
									out.println("<td align=right>"+rs.getString(13) +"</td>");//onclick=check_type(\""+j+"\")
								  out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\" >");
									//}
									//out.println("     </td>");
									out.println("</tr>");
										
                	j=j+1;
									                
              }
									
								out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=10><input type=hidden name=hid_inv_count value="+j+"><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");

				
      } 	
						
			else if(m_chksql.trim().equals("get_Receipt_del")){
			   String m_rec_no      = req.getParameter("rec_no");          
      } 	
			
			else if(m_chksql.trim().equals("Add_Min_Amount")){
			    	
			}
			else if(m_chksql.trim().equals("RUN_AUTO_ALLO")){
			    
			}
	 }catch (Exception e) {
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
