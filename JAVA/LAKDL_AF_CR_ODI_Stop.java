//Option Id is  
//This File was created by SVA on 05-02-2007 
//OD Interest Stop Process Display
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;

import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_ODI_Stop extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1,nf2;
	public ResultSet rs,rs1;
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
			out = res.getOutputStream();
			//out.println("conn="+conn);
			//Class.forName("oracle.jdbc.driver.OracleDriver");
      //conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
			CallableStatement callstmt1 =null;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  //nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			nf2 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf2.setMaximumFractionDigits(2);
			nf2.setMinimumFractionDigits(2);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			stmt1= conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					
	    	else if(m_chksql.trim().equals("main_page")){
			
	        String m_Followu_no   = "";//req.getParameter("Followu_no");
          
      out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Asset Financing System</TITLE>"); 
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
				out.println("         rec.innerHTML=http_request.responseText; ");
				 out.println("      }else if(opt==\"99\"){");				
				out.println("         rec_1.innerHTML=http_request.responseText; ");
				//out.println("         assign_div();");
				//out.println("      }else if(opt==\"7\"){");
				//out.println("         veh.innerHTML=http_request.responseText; ");
				//out.println("         get_term_details();");
				//out.println("      }else if(opt==\"3\"){");
				//out.println("         alert(http_request.responseText);");
				//out.println("         document.Form1.tot_val.value=http_request.responseText; ");
				//out.println("         f=document.Form1.hid_opt_val.value ;");
				//out.println("         g=document.Form1.hid_win_opt.value ;");
				//out.println("         if(parseFloat(unformat_noobject(document.Form1.tot_val.value))<=parseFloat(document.Form1.elements[\"BAL_AMOUNT_\"+f].value)){");
				//out.println("           popupwin.document.Form1.tot_val.value=document.Form1.tot_val.value; ");
				//out.println("           popupwin.total.innerHTML=format_noobject(document.Form1.tot_val.value); ");
				//out.println("           document.Form1.elements[\"SETT_AMOUN_\"+f].value=format_noobject(document.Form1.tot_val.value); ");
				
				//out.println("         }else{");
				//out.println("           popupwin.document.Form1.elements['Text_standard'+g].value      =\"NO\";");
				//out.println("           popupwin.document.Form1.elements['Text_sett_amount'+g].disabled=false;");
				//out.println("           popupwin.document.Form1.elements['Text_standard'+g].checked    =false;");
				
				//out.println("         }");
				
				//out.println("      }else if(opt==\"6\"){");
				//out.println("         alert(http_request.responseText);");
				//out.println("         document.Form1.tot_val.value=http_request.responseText; ");
				//out.println("         document.Form1.elements[\"SETT_AMOUN_0\"].value=format_noobject(document.Form1.tot_val.value); ");
				
				//out.println("      }else if(opt==\"5\"){");
				//out.println("         alert(http_request.responseText);");
				//out.println("         rec.innerHTML=http_request.responseText; ");
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
			 //out.println("     document.Form1.TERMINATION_NO.value       =data[0];"); 
			 //out.println("     get_Receipt_del();");
			 out.println("   }else if(type=='Cli'){"); 
			 out.println("     document.Form1.CLIENT_CODE.value     =data[0];"); 
			 out.println("     document.Form1.CLIENT_NAME.value     =data[1];"); 
			 //out.println("     get_Receipt();");//Commented by Dineth on 2008-12-03
			 //out.println("   }else if(type=='Veh'){"); 
			 ////out.println("     document.Form1.VEHICLE_NO.value      =data[0];"); 
			 //out.println("     document.Form1.LEASE_NO.value        =data[0];"); 
			 //out.println("     document.Form1.APPLICATION_NO.value  =data[1];"); 
			 //out.println("     document.Form1.CLIENT_CODE.value     =data[2];"); 
			 //out.println("     check_due_inv();");
			 //out.println("     get_Receipt();");
			 out.println("   }else if(type=='Lea'){"); 
			 out.println("     document.Form1.LEASE_NO.value        =data[3];"); 
			 out.println("     document.Form1.APPLICATION_NO.value  =data[0];"); 
			 out.println("     document.Form1.CLIENT_CODE.value     =data[1];"); 
			 //out.println("     check_due_inv();");
			 //out.println("     get_Receipt();");//Commented by Dineth on 2008-12-03
			 //out.println("   }else if(type=='DUE'){"); 
			 //out.println("     document.Form1.DUE_AMOUNT.value      =data[0];"); 
			 //out.println("     check_lease_rate();");
			 //out.println("   }else if(type=='CAP'){");//       CAP_SETT   CAP_SETT_PER
			 //out.println("     document.Form1.AMOUNT_FINANCE.value  =data[0];"); 
			 //out.println("     document.Form1.NIBSM.value           =data[1];"); 
			 //out.println("     document.Form1.AMI.value             =data[2];"); 
			 //out.println("     document.Form1.CAP_OUT.value         =data[3];"); 
			 //out.println("     document.Form1.CAP_OUT_PER.value     =data[5];"); 
			 //out.println("     document.Form1.VAT_PER.value         =data[6];"); 
			 	
			 //out.println("   }else if(type=='TCOUNT'){");
			 //out.println("     document.Form1.TER_COUNT.value       =data[0];"); 
			 //out.println("     if(document.Form1.TER_COUNT.value>'0'){");
			 //out.println("     	 check_term_char();");
			 //out.println("     }else{ ");
			 //out.println("       document.Form1.TERM_AMOUNT.value       ='0'; ");
			 //out.println("       get_term_vehicles();");
			 ////out.println("       check_due_inv();");
			 //out.println("     } ");
			 //out.println("   }else if(type=='TCHAR'){"); 
			 //out.println("     document.Form1.TERM_AMOUNT.value      =data[0];");
			 //out.println("     get_term_vehicles();");
			 //out.println("   }else if(type=='LEASERATE'){"); 
			 //out.println("     document.Form1.LEASE_RATE.value       =data[0];"); 
			 //out.println("     check_client();");
			 //out.println("   }else if(type=='LERATE'){"); 
			 //out.println("     document.Form1.LEASE_RATE.value       =data[0];"); 
			 //out.println("     get_due_rent_sum();");
			 //out.println("   }else if(type=='DUER'){"); 
			 //out.println("     document.Form1.DUE_RENTALS.value       =data[0];"); 
			 //out.println("     check_term_count();");
			
			 //out.println("   }else if(type=='TermNo'){"); 
			 //out.println("     document.Form1.TERMINATION_NO.value   =data[0];"); 
			 //out.println("     document.Form1.LEASE_NO.value         =data[1];"); 
			 //out.println("     document.Form1.APPLICATION_NO.value   =data[2];"); 
			 //out.println("     document.Form1.CLIENT_CODE.value      =data[3];"); 
			 ////out.println("     document.Form1.LEASE_RATE.value       =data[4];"); 
			 ////out.println("     document.Form1.LEASE_RATE.value       =data[5];"); 
			 //out.println("     document.Form1.REQ_BY.value           =data[6];"); 
			 //out.println("     document.Form1.TER_RATE.value         =data[7];"); 
			 ////out.println("     document.Form1.TERM_AMOUNT.value      =data[8];"); 
			 //out.println("     document.Form1.REMARK.value           =data[9];"); 
			 //out.println("     document.Form1.TERM_AMOUNT.value      =data[10];"); 
			 //out.println("     document.Form1.TER_COUNT.value        =data[11];");
			 //out.println("     document.Form1.DUE_AMOUNT.value       =data[12];");
			 //out.println("     document.Form1.hid_cal_date.value='3';");
			 //out.println("     load_c_date(data[4]);");
			 //out.println("     document.Form1.hid_cal_date.value='2';");
			 //out.println("     load_c_date(data[5]);");
			 //out.println("     befor_cal();	");
			 out.println("   }"); 
       out.println(" }");
       out.println("}");
				//End Of Checking Values
				/*
				out.println("function assign_div(){");
				out.println(" rent.innerHTML=document.Form1.h_rent.value;"); 
			  out.println("	term.innerHTML=document.Form1.h_term.value;"); 
			  out.println(" rpv.innerHTML =document.Form1.h_rpv.value;"); 
			  out.println("	tpv.innerHTML =document.Form1.h_tpv.value;");
				out.println("	gtv.innerHTML =document.Form1.h_gtv.value;");
				out.println(" if(unformat_noobject(document.Form1.h_term.value)<unformat_noobject(document.Form1.h_rpv.value)){");
				out.println("   document.Form1.b_submit.disabled   = true;");
				out.println("   document.Form1.b_submit_1.disabled = true;");
				out.println("   alert('Please enter correct termination rate and continue.');");
				out.println(" }else{");
				out.println("   document.Form1.b_submit.disabled   = false;");
				out.println("   document.Form1.b_submit_1.disabled = false;");
				out.println(" }");
				out.println("	check_leaserate();");
			  out.println("}");		
				
				
				out.println("function inv_help(num){");
				out.println(" document.Form1.hid_opt_val.value=num;"); 
			  out.println("	popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_ODI_Stop?chksql=get_Invoice&client=\"+document.Form1.CLIENT_CODE.value+\"\", \"oBj\",\"left=130,top=200,width=750,height=400\");"); 
			  out.println("}");		
				
				out.println("function cal_amount(opt,am1,am2,num) {");//
				out.println("   document.Form1.hid_win_opt.value=num;");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_ODI_Stop?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'3');");
				
        out.println("}");	
				
				out.println("function cal_amount_del(opt,am1,am2,num) {");//
				out.println("   document.Form1.hid_win_opt.value=num;");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_ODI_Stop?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'6');");
				
        out.println("}");	
				
				out.println("function get_term_vehicles() {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_ODI_Stop?chksql=get_Vehicles&Lease_no=\"+document.Form1.LEASE_NO.value;");
      //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'7');");
				out.println("}");
				
				*/
				
				out.println("function get_Receipt_sum() {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_ODI_Stop?chksql=get_Receipt_sum&client=\"+document.Form1.CLIENT_CODE.value+\"&fin_no=\"+document.Form1.LEASE_NO.value;");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'2','Rec');");
				
        out.println("}");	
				
				
			  out.println("function get_Receipt() {");
				
				out.println("m_amt = document.Form1.ADJ_VALUE.value ;");
				out.println("m_type= document.Form1.SELECT_CRT.value ;");
				out.println("if(document.Form1.ADJ_VALUE.value==\"\"){");
			  out.println("alert('Please Enter Adjusted Value..!');");
				out.println("}");
				out.println("else{");
				out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_ODI_Stop?chksql=get_ODI_Details&type=\"+m_type+\"&m_amt=\"+unformat_noobject(m_amt)+\"&client=\"+document.Form1.CLIENT_CODE.value+\"&fin_no=\"+document.Form1.LEASE_NO.value;");
      	out.println("makeRequest(m_url,'99','Rec');");
				out.println("}");	
        out.println("}");	
				/*
				out.println("function get_Receipt_del(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_ODI_Stop?chksql=get_Receipt_del&rec_no=\"+val+\"\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'5');");
				
        out.println("}");	
				
				out.println("function check_receipt(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_XMLFile?chksql=get_rec_no&rec_no=\"+document.Form1.RECEPT_NO.value+\"\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','Rec');");
				
        out.println("}");
				*/
				/*
				out.println("function check_due_inv(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_due_invoice_sum&finance_no=\"+document.Form1.LEASE_NO.value+\"&client_code=\"+document.Form1.CLIENT_CODE.value;");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','DUE');");
				out.println("}");
				
				out.println("function get_due_rent_sum(val) {");
				out.println(" if(document.Form1.TER_DAY.value!=\"\" && document.Form1.TER_MONTH.value!=\"\" && document.Form1.TER_YEAR.value!=\"\"){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_due_rent_sum&finance_no=\"+document.Form1.LEASE_NO.value+\"&veh_no=\"+document.Form1.VEHICLE_NO.value+\"&tdate=\"+document.Form1.TER_DAY.value+\"-\"+document.Form1.TER_MONTH.value+\"-\"+document.Form1.TER_YEAR.value;");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','DUER');");
				out.println(" }else{");
				out.println("   alert('Please enter termination date.')");
				out.println(" }");
				out.println("}");
				
				out.println("function get_term_details(val) {");
				out.println("  m_v_no=\"\";");
				out.println("for(i=0;i<parseFloat(document.Form1.hid_vcount.value);i++){");    
				out.println(" if(document.Form1.elements['ch_v_'+i].checked){");
				out.println("  m_v_no=m_v_no+document.Form1.elements['VEHICLE_NO_'+i].value+\"@\";");    
				//out.println("  m_v_count=m_v_count+1;");    
				out.println(" }");    
				out.println("}");    
				//out.println("if(parseFloat(document.Form1.hid_vcount.value)==m_v_count){");
				//out.println(" document.Form1.VEHICLE_NO.value=\"\";");    
				//out.println("}else{");
				out.println(" document.Form1.VEHICLE_NO.value=m_v_no;");    
				//out.println("}");    
				out.println(" if(document.Form1.VEHICLE_NO.value!=\"\" && document.Form1.LEASE_NO.value!=\"\"){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_term_details&finance_no=\"+document.Form1.LEASE_NO.value+\"&veh_no=\"+document.Form1.VEHICLE_NO.value;");
        out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','CAP');");
				out.println(" }else{");
				out.println("   alert('Please enter termination date.')");
				out.println(" }");
				out.println("}");
				
				
				
				out.println("function check_lease_rate() {");
				out.println("  if(document.Form1.LEASE_NO.value!=''){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_lease_rate&finance_no=\"+document.Form1.LEASE_NO.value+\"&veh_no=\"+document.Form1.VEHICLE_NO.value+\"&client_code=\"+document.Form1.CLIENT_CODE.value;");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','LERATE');");
				out.println("  }"); 
				out.println("}");
				
				out.println("function check_leaserate() {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_lease_rate&finance_no=\"+document.Form1.LEASE_NO.value+\"&veh_no=\"+document.Form1.VEHICLE_NO.value+\"&client_code=\"+document.Form1.CLIENT_CODE.value;");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','LEASERATE');");
				out.println("}");
				
				out.println("function check_Rate() {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_term_rate&ter_rate=\"+document.Form1.TER_RATE.value+\"&veh_no=\"+document.Form1.VEHICLE_NO.value+\"&client_code=\"+document.Form1.CLIENT_CODE.value;");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','TERATE');");
				out.println("}");
				
				
				out.println("function check_term_count() {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_term_count&finance_no=\"+document.Form1.LEASE_NO.value+\"&client_code=\"+document.Form1.CLIENT_CODE.value;");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','TCOUNT');");
				out.println("}");

        out.println("function check_term_char() {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_term_charge&term_type=\"+document.Form1.TERMINATION_TYPE.value+\"&client_code=\"+document.Form1.CLIENT_CODE.value;");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','TCHAR');");
				out.println("}");
        */
        out.println("function check_lease(val) {");
				out.println("  if(document.Form1.LEASE_NO.value!=''){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_Finance_no&finance_no=\"+document.Form1.LEASE_NO.value+\"&client_code=\"+document.Form1.CLIENT_CODE.value;");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','Lea');");
				out.println("  }");
				out.println("}");
				/*
				out.println("function check_vehicle(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_veh_no&veh_no=\"+document.Form1.VEHICLE_NO.value+\"\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','Veh');");
				out.println("}");
				
				out.println("function check_term(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_term_no&term_no=\"+document.Form1.TERMINATION_NO.value+\"\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','TermNo');");
				out.println("}");
				*/
				
				out.println("function check_client(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_client_code&client_code=\"+document.Form1.CLIENT_CODE.value+\"\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','Cli');");
				
        out.println("}");
				/*
				out.println("function load_all_foll(m_stat,opt) {");
				//out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup?chksql=get_followup&fno="+m_Followu_no+"\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,opt);");
				out.println("}");
			  */
			out.println("function validate_data(){"); 
			out.println("m_sub=0;"); 
			
			out.println("if(document.Form1.hid_option.value==\"NEW\"){"); 
			//out.println("if(document.Form1.LEASE_NO.value==\"\"){  "); 
			//out.println("FNO.style.color='red';");
			//out.println("m_sub = 1;;"); 
			//out.println("}");
			out.println("}else {"); 
			
			out.println("}");
			
			// added by udara on 22-10-2013
			out.println("if(document.Form1.TXT_REMARK.value==''){");
			out.println(" alert('Please enter the remarks'); ");
			out.println("  m_sub = 1;"); 
			out.println("  document.Form1.TXT_REMARK.focus();");
			out.println("}");
			
			out.println(" if(document.Form1.hid_count.value==\"0\"){");
			out.println("		alert('Please select value and continue!') "); 
			out.println("   m_sub = 1;;"); 
			out.println(" }");
			
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
			out.println("		if(document.Form1.hid_odi_inv_cnt.value > 0){");//Added By Sandun on 09-06-2009
			out.println("alert(\"Please complete pending adjustment..!\");");
			out.println("}");
			out.println(" else {");
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to save?\")){ "); 
			out.println("		document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_Save';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 
			out.println("} "); 
			
			/*
			out.println("function befor_cal(){ "); 
			//out.println("		if(confirm(\"Are You Sure?\")){ "); 
			out.println("if(document.Form1.hid_option.value=='NEW'){");
			out.println("	if( document.Form1.LEASE_NO.value==\"\"){");
			out.println("		alert('Please enter finance no and continue!') "); 
			out.println("	}else if(document.Form1.TER_RATE.value==\"\" ){");
			out.println("		alert('Please enter discount rate and continue!') "); 
			out.println("	}else if(document.Form1.TER_DAY.value==\"\" || document.Form1.TER_MONTH.value==\"\" || document.Form1.TER_YEAR.value==\"\"){");
			out.println("		alert('Please enter termination date and continue!') "); 
			out.println("	}else{"); 
			out.println(" m_v_count=0;");    
			out.println(" m_v_no=\"\";");    
			
			out.println("for(i=0;i<parseFloat(document.Form1.hid_vcount.value);i++){");    
			out.println(" if(document.Form1.elements['ch_v_'+i].checked){");
			out.println("  m_v_no=m_v_no+document.Form1.elements['VEHICLE_NO_'+i].value+\"@\";");    
			out.println("  m_v_count=m_v_count+1;");    
			out.println(" }");    
			out.println("}");    
			out.println("if(parseFloat(document.Form1.hid_vcount.value)==m_v_count){");
			out.println(" document.Form1.VEHICLE_NO.value=\"\";");    
			out.println("}else{");
			out.println(" document.Form1.VEHICLE_NO.value=m_v_no;");    
			out.println("}");    
			
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_ODI_Stop?chksql=get_Termi_Char&Vehicle_no=\"+document.Form1.VEHICLE_NO.value+\"&Lease_no=\"+document.Form1.LEASE_NO.value+\"&Disco_rate=\"+document.Form1.TER_RATE.value+\"&vat_rate=\"+document.Form1.VAT_PER.value+\"&lease_rate=\"+document.Form1.LEASE_RATE.value+\"&App_Date=\"+document.Form1.TER_DAY.value+\"-\"+document.Form1.TER_MONTH.value+\"-\"+document.Form1.TER_YEAR.value+\"&Client=\"+document.Form1.CLIENT_CODE.value;");
        //out.println("   window.open(m_url);");
			out.println("   makeRequest(m_url,'2');");
			out.println("	}"); 
			out.println("}else{"); 
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_ODI_Stop?chksql=get_Termi_Det&Termination_no=\"+document.Form1.TERMINATION_NO.value+\"&Lease_no=\"+document.Form1.LEASE_NO.value+\"&vat_rate=\"+document.Form1.VAT_PER.value+\"&Disco_rate=\"+document.Form1.TER_RATE.value+\"&App_Date=\"+document.Form1.TER_DAY.value+\"-\"+document.Form1.TER_MONTH.value+\"-\"+document.Form1.TER_YEAR.value+\"&Client=\"+document.Form1.CLIENT_CODE.value;");
        //out.println("   window.open(m_url);");
			out.println("   makeRequest(m_url,'2');");
			out.println("}"); 
			out.println("} "); 
			*/
      out.println("function befor_reset(){");
			out.println(" if(confirm(\"Are you sure you want to clear the screen?\")){  ");
			//out.println("  Form1.reset()   ");
		  out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_ODI_Stop?chksql=main_page'");
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
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CO_Followup\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Credit - OD Interest Stop - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Credit - OD Interest Stop - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function load_screen_status(m_val){"); 
			out.println("    document.Form1.hid_option.value    =m_val;"); 
			out.println("if(m_val==\"NEW\"){"); 
			//out.println("new_window();");
			//out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.CLIENT_CODE.disabled=false;"); 
			out.println("document.Form1.cli_help.disabled=false;"); 
			out.println("document.Form1.lea_help.disabled=false;"); 
			//out.println("document.Form1.veh_help.disabled=false;"); 
			//out.println("document.Form1.rec_help.disabled=true;"); 
			out.println("document.Form1.LEASE_NO.disabled=true;}"); 
			
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val==\"DELETE\"){"); 
			out.println("document.Form1.CLIENT_CODE.disabled=true;"); 
			out.println("document.Form1.cli_help.disabled=true;"); 
			out.println("document.Form1.lea_help.disabled=true;"); 
			//out.println("document.Form1.veh_help.disabled=true;"); 
			//out.println("document.Form1.rec_help.disabled=false;"); 
			out.println("document.Form1.LEASE_NO.disabled=false;"); 
			
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
			/*out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_CO_Help_Servlet?class_in=\"+client_name+\"AF_CO_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			*/
			//out.println("window.open('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_CO_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=');");
			out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
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
				out.println("HelpBox('1','10','0',Crit,'ClientSql','1');");
				out.println("}");		
				out.println("function client_assign(oBj){");
				out.println(" document.Form1.CLIENT_NAME.value =oBj.valout[3]");
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[2]");
				out.println(" check_client(document.Form1.CLIENT_CODE.value);");
				out.println("}");

			//Lease Help
			  out.println("function lease_help(){");
				out.println("Crit=document.Form1.LEASE_NO.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'LeaseSql','3');");
				out.println("}");	
				
				out.println("function lease_assign(oBj){");
				out.println(" document.Form1.LEASE_NO.value =oBj.valout[2]");
				out.println(" document.Form1.APPLICATION_NO.value =oBj.valout[3]");
				//Added by Dineth on 2008-12-08
				out.println(" document.Form1.CLIENT_NAME.value =oBj.valout[4]");
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[6]");

				//End by Dineth on 2008-12-08
			//	out.println(" get_Receipt();");//Uncommented by Dineth on 2008-12-03
				//out.println(" check_lease(document.Form1.LEASE_NO.value);");//Commented by Dineth on 2008-12-03
				out.println(" get_Receipt_sum();");
					out.println("get_odi_inv_count();");//Added By Sandun on 09-06-2009
				out.println("}");
			//Vehicle Help
			  out.println("function vehicle_help(){");
				out.println("Crit=document.Form1.VEHICLE_NO.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\"+document.Form1.LEASE_NO.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'VehicleSql','4');");
				out.println("}");	
				
				
				
				out.println("function vehicle_assign(oBj){");
				out.println(" document.Form1.VEHICLE_NO.value =oBj.valout[2]");
				out.println(" check_vehicle(document.Form1.VEHICLE_NO.value);");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");
						
        //Vehicle Help
			  out.println("function term_help(){");
				out.println("Crit=document.Form1.TERMINATION_NO.value+\"@\";");
				out.println("HelpBox('1','10','5',Crit,'TerminationNoSql','5');");
				out.println("}");	
				
				out.println("function term_assign(oBj){");
				out.println(" document.Form1.TERMINATION_NO.value =oBj.valout[2]");
				out.println(" check_term(document.Form1.TERMINATION_NO.value);");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");
					
 /*
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
		*/	
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
							
			out.println("function load_history(num) {");
			out.println("	if(num!=''){ ");
      out.println("	  popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup?chksql=get_History&deal_no=\"+num+\"\", \"oBj\",\"left=100,top=200,width=650,height=400\");"); 
			out.println("	}else{");
			out.println("	  alert('Please enter Followup Number and continue!');");
			out.println("	}");
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
											
			out.println("function ch_status(num) {");
				//out.println("alert(document.Form1.elements['Text_standard'+num].checked);");
			out.println("if(document.Form1.elements['ch_v_'+num].checked){");
			out.println(" document.Form1.elements['ch_v_'+num].value=\"YES\";");
			out.println("}else{");
			out.println(" document.Form1.elements['ch_v_'+num].value=\"NO\";");
			out.println("}");
			out.println("}");
			
			out.println("function Check_Amount(num) {");
			//out.println(" alert(Number(document.Form1.elements[\"SETT_AMOUN_\"+num].value));");
				//out.println("alert(document.Form1.elements['Text_standard'+num].checked);");
			out.println("if(Number(document.Form1.elements[\"SETT_AMOUN_\"+num].value)>Number(document.Form1.elements[\"ODIB_AMOUNT_\"+num].value)){");
			out.println(" alert('Amount cannot be greater than Balance Amount');");
			out.println(" document.Form1.elements[\"SETT_AMOUN_\"+num].value = document.Form1.elements[\"ODIB_AMOUNT_\"+num].value;");
			out.println("}");
			out.println("}");
			
			
			out.println("function check_status(num) {");
				//out.println("alert(document.Form1.elements['Text_standard'+num].checked);");
			out.println("if(document.Form1.elements['Text_standard'+num].checked){");
			out.println(" document.Form1.elements['Text_standard'+num].value=\"YES\";");
			out.println("  document.Form1.elements['SETT_AMOUN_'+num].disabled =false;"); //Added BY Sandun on 16-01-2009
			//out.println(" cal_amount_del('add',unformat_noobject(document.Form1.SETT_AMOUN_0.value),unformat_noobject(document.Form1.elements['Text_sett_amount0_'+num].value),num);");
			out.println("}else{");
			out.println(" document.Form1.elements['Text_standard'+num].value=\"NO\";");
			out.println("  document.Form1.elements['SETT_AMOUN_'+num].disabled =true;"); //Added BY Sandun on 16-01-2009
			//out.println(" cal_amount_del('min',unformat_noobject(document.Form1.SETT_AMOUN_0.value),unformat_noobject(document.Form1.elements['Text_sett_amount0_'+num].value),num);");
			out.println("}");			
			out.println("}");
			
			out.println("function check_Date(val1,val2,val3) {");
			out.println("  checkMonthLength(val1,val2,val3); ");
			out.println("}");
			
						out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
							
			out.println("function load_c_date(val) {");
      out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("     document.Form1.TER_DAY.value=val.substr(0,2);");
			out.println("     document.Form1.TER_MONTH.value=val.substr(3,2);");
			out.println("     document.Form1.TER_YEAR.value=val.substr(6,4);");
			out.println("     check_lease();");
			out.println("  }else if(document.Form1.hid_cal_date.value=='3'){"); 
			out.println("     document.Form1.TER_V_DAY.value=val.substr(0,2);");
			out.println("     document.Form1.TER_V_MONTH.value=val.substr(3,2);");
			out.println("     document.Form1.TER_V_YEAR.value=val.substr(6,4);");
			out.println("  }");				
			out.println("}");				
			
			out.println("function load_data(num) {");
			out.println(" if(num!=\"\"){");	
      out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_PRO_drill_downs_3?chksql=SHOW_FINANCE_DETAIL_DRILL&finance_no=\"+num+\"\", \"oBj\",\"left=150,top=100,width=620,height=390,scrollBars=1\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println(" }else{");
			out.println("   alert('Please enter Finance Number and continue!');");   //SHOW_APPLICATION_DETAIL_DRILL
			out.println(" }");
			out.println("}");
			
			//--------//Added By Sandun on 16-09-2009---------------
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
			
			
			out.println("function chk_manu_bal(obj){");
			out.println("  var hid_cnt=0;");
			out.println("  var sett_amt=0;");
			out.println("  var amount=0;");
			out.println("  hid_cnt=parseFloat(document.Form1.hid_count.value);");
			out.println(" for(j=0;j<hid_cnt;j++){"); 
			out.println("  sett_amt = sett_amt + parseFloat(unformat_noobject(document.Form1.elements['SETT_AMOUN_'+j].value));");
			out.println("}");
	   	out.println("amount = parseFloat(unformat_noobject(document.Form1.ADJ_VALUE.value));");
			out.println("if(sett_amt > amount){");
			out.println("  alert('Allocated Amount cannt be greater than Rs. '+format_noobject(amount));");
			out.println("  document.Form1.elements['SETT_AMOUN_'+obj].value = format_noobject(0.00);");
			out.println("}");
			out.println("if(document.Form1.SELECT_CRT.value==\"DEC\"){");
			out.println("adj_amt = parseFloat(unformat_noobject(document.Form1.elements['SETT_AMOUN_'+obj].value))");
			out.println("bal_amt = parseFloat(unformat_noobject(document.Form1.elements['ODIB_AMOUNT_'+obj].value))");
			out.println("if(adj_amt>bal_amt){");
			out.println("alert('Adjustment Amount cannot be greater than Balance Amount..!');");
			out.println("document.Form1.elements['SETT_AMOUN_'+obj].value = bal_amt;");
			out.println("}");
			out.println("}");
			out.println("}");
			
			
			
			//-------------------------end---------------------------------------------------
			
			
			out.println("function get_odi_inv_count(){"); //Added by Sandun on 2009-06-09
			out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_get_no_of_odi_invoices&finance_no=\"+document.Form1.LEASE_NO.value+\"\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function get_vector(data_vec) {");//Added by Sandun on 2009-06-09
			out.println("if(data_vec.length>0){");
	    out.println("document.Form1.hid_odi_inv_cnt.value=data_vec[0]; "); 
			out.println("}");
			//out.println("alert(document.Form1.hid_odi_bal.value);");
			out.println("}");
			
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_out_value();load_lock();\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input type='hidden' name='Hid_scr_name' value='AF_ODI_WRITEOFF' > ");
			//out.println("<input type='hidden' name='TXT_SCREEN_NAME' value='AF_CR_TERMINATION_CAL' > ");
			out.println("<INPUT TYPE='Hidden' NAME='hid_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"NEW\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_win_type' VALUE=\"Main\">"); 			
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"NEW\">");
			out.println("<input type=hidden name=\"tot_val\" value=\"0\">");
			out.println("<input type=hidden name=\"hid_opt_val\" value=\"0\">");
			out.println("<input type=hidden name=\"hid_win_opt\" value=\"0\">");
			out.println("<input type=hidden name=\"VEHICLE_NO\" value=\"\">");	
			out.println("<input type=hidden name=\"hid_odi_inv_cnt\" value=0 >");		//Added By Sandun on 09-06-2009
			
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
				//out.println("<td><input type=button name=reset value=\"New\" class=mainbut onclick=load_screen_status(\"NEW\"); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");//document.Form1.OPTION_DESC.value
				//out.println("<td>&nbsp;</td>");
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
				out.println("<td><input type=button name=reset value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Cancel\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td ><input type=button name=cal value=\"Calculate\" class=mainbut onclick=befor_cal();></td>");
      
				out.println("</tr></table>");
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"\" valign=\"top\">");
				out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
			  
				/*
				out.println("<tr class=tr_input>");
				out.println("<td id=TMN>Termination No</td>");
				out.println("<td><input name=\"TERMINATION_NO\" type=\"text\" maxlength=\"15\" class=\"txt_input\" onchange=check_term()> ");
				out.println("<input type=button name=rec_help value=Help class=\"but_input\" onclick=\"term_help()\" disabled></td>");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td id=fod></td>");
				out.println("<td><input type=hidden name=TERMINATION_TYPE value=''></TD>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td id=TMD>Termination Date *</td>");
				out.println("<td><input name=\"TER_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.TER_DAY,document.Form1.TER_MONTH,document.Form1.TER_YEAR);check_lease();> ");
				out.println("    <input name=\"TER_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.TER_DAY,document.Form1.TER_MONTH,document.Form1.TER_YEAR);check_lease();> ");
				out.println("    <input name=\"TER_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.TER_DAY,document.Form1.TER_MONTH,document.Form1.TER_YEAR);check_lease();><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("</td>");
				out.println("<td id=tod>Requested By</td>");
				out.println("<td><select name=REQ_BY class=\"txt_input\"><option value=\"CLIENT\" Selected>Client</option> ");
				out.println("<option value=\"MANAGEMENT\" >Management</option><select> ");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				*/
				
				out.println("<tr class=tr_input>");
				out.println("<td id=CLC>Client Code *</td>");
				out.println("<td><input name=\"CLIENT_CODE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" onchange=check_client()> ");
				out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"client_help()\"></td>");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td id=tod>Client Name</td>");
				out.println("<td> <input name=\"CLIENT_NAME\" type=\"text\" maxlength=\"200\" class=\"txt_input\" disabled style=\"width:300px;\">");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");		

				
				out.println("<tr class=tr_input>");
				out.println("<td id=FNO>Finance No *</td>");
				//out.println("<td><input name=\"LEASE_NO\" type=\"text\" maxlength=\"15\" class=\"txt_input\" onchange=lease_help()><input name=\"APPLICATION_NO\" type=\"hidden\">");
				out.println("<td><input name=\"LEASE_NO\" type=\"text\" maxlength=\"20\" class=\"txt_input\" onchange=lease_help()><input name=\"APPLICATION_NO\" type=\"hidden\">");
				out.println("<input type=button name=lea_help value=... class=\"but_input\" onclick=\"lease_help()\" >");
				out.println("<input class='but_input' type='button' name='BUT_HELP_DET' value=\"Detail\" onClick=\"load_data(document.Form1.LEASE_NO.value)\" ></td>"); 
			  out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<td >Termination Count</td>");
				//out.println("<td><input name=\"TER_COUNT\"   type=\"text\" maxlength=\"2\"  class=\"txt_input\" disabled > ");
				//out.println("</td>");
				out.println("<td ></td>");
				out.println("<td></td>");
				/*out.println("<td >Vehicle No</td>");
				out.println("<td> <input name=\"VEHICLE_NO\" type=\"text\" maxlength=\"20\" class=\"txt_input\" onchange=check_vehicle()> ");
				out.println("<input type=button name=veh_help value=Help class=\"but_input\" onclick=\"vehicle_help()\" >");
				out.println("</td>");*/
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				/*
				out.println("<tr class=tr_input>");dea
				out.println("<td id=TMR>Termination Rate *</td>");
				out.println("<td><input name=\"TER_RATE\" type=\"text\" maxlength=\"6\" class=\"txt_input\" onchange=check_Rate(document.Form1.TER_RATE.value) STYLE=\"{text-align:right;}\">");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td >Finance Rate</td>");
				out.println("<td><input name=\"LEASE_RATE\"  type=\"text\" maxlength=\"6\"  class=\"txt_input\" disabled onchange=check_Date(document.Form1.TER_V_DAY,document.Form1.TER_V_MONTH,document.Form1.TER_V_YEAR) STYLE=\"{text-align:right;}\"> ");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td id=TMV>Termination Valid Date *</td>");
				out.println("<td><input name=\"TER_V_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.TER_V_DAY,document.Form1.TER_V_MONTH,document.Form1.TER_V_YEAR)> ");
				out.println("    <input name=\"TER_V_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.TER_V_DAY,document.Form1.TER_V_MONTH,document.Form1.TER_V_YEAR)> ");
				out.println("    <input name=\"TER_V_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.TER_V_DAY,document.Form1.TER_V_MONTH,document.Form1.TER_V_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
				out.println("</td>");
				out.println("<td >Due Amount</td>");
				out.println("<td><input name=\"DUE_AMOUNT\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled>");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Remark</td>");
				out.println("<td><input name=\"REMARK\"   type=\"text\" maxlength=\"200\"  class=\"txt_input\" style=\"width:300px;\"> ");
				out.println("</td>");
				out.println("<td >Termination Charge</td>");
				out.println("<td><input name=\"TERM_AMOUNT\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" STYLE=\"{text-align:right;}\">");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >VAT %</td>");
				out.println("<td><input name=\"VAT_PER\" type=\"text\" maxlength=\"5\" class=\"txt_input\" STYLE=\"{text-align:right;}\"> ");//
				out.println("</td>");
				out.println("<td >Normal Rentals Due up to Termination Date</td>");
				out.println("<td><input name=\"DUE_RENTALS\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled>");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
		       
				//amount finance , NIBSM ,AMI(Amount) , Capital Repayment ,Total Capital already settled, % (Amount Setteled/Financed Amount) 
		    out.println("<tr class=tr_input>");
				out.println("<td >Amount Finance</td>");
				out.println("<td><input name=\"AMOUNT_FINANCE\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled> ");
				out.println("</td>");
				out.println("<td >NIBSM</td>");
				out.println("<td><input name=\"NIBSM\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled>");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
		    
				out.println("<tr class=tr_input>");
				out.println("<td >AMI</td>");
				out.println("<td><input name=\"AMI\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled> ");
				out.println("</td>");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
		    
		    out.println("<tr class=tr_input>");
				out.println("<td >Total Capital Outstanding</td>");
				out.println("<td><input name=\"CAP_OUT\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled> ");
				out.println("</td>");
				out.println("<td >%</td>");
				out.println("<td><input name=\"CAP_OUT_PER\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled>");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
		    */ 
				out.println("<tr class=tr_input>");
				out.println("<td colspan=4><div id=veh><input type=hidden name=hid_vcount value=0></div></td>");
				out.println("</tr>");	
				
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\" colspan=4>");
				out.println("<div id=rec><input type=hidden name=hid_count value=0>");
				out.println("</td>");
				out.println("</tr>");				
				
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");	
				
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\" colspan=4>");
				out.println("<div id=rec_1><input type=hidden name=hid_count value=0>");
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
				
				out.println("<td style=\"width: 6px\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td width=10%>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=reset_1 value=\"New\" class=mainbut onclick=load_screen_status(\"NEW\"); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");//document.Form1.OPTION_DESC.value
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=edit_1 value=\"Delete\" class=mainbut onclick=load_screen_status(\"DELETE\"); onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				//out.println("<td width=10%>&nbsp;</td>");
				//out.println("<td><input type=button name=delete value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel value=\"Re-active\" class=mainbut onclick=befor_active(); onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=b_submit_1 value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td><input class='mainbut' type='button' name='BUT_HELP_MAIN_1' value=\"Help\" onClick=\"help_update()\" disabled>  </td>"); 
			  out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset_1 value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Cancel\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back_1 value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td ><input type=button name=cal_1 value=\"Calculate\" class=mainbut onclick=befor_cal();></td>");
      
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
		
      }
			
			else if(m_chksql.trim().equals("get_Vehicles")){
			
			    String m_Lease_no     = req.getParameter("Lease_no");
  	   
					rs = stmt.executeQuery("SELECT REG_NO "+
																 "FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
																 "WHERE APPLICATION_NO IN (SELECT APPLICATION_NO "+
																 "FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																 "WHERE FINANCE_NO='"+m_Lease_no+"') AND ACTIVE_STATUS='Y'"); 
																					
					out.println("<table class=table border='0' width='100%' >");
					
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  colspan=8>Vehicle No</td>");
					out.println("</tr>");
          int j=0;
			    while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
            out.println("<tr class=tr_input >");
						out.println("<td width=10%>"+rs.getString(1)+"</td><td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"YES\" checked><input type=hidden name=\"VEHICLE_NO_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
						out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\"></td>");
						
						j=j+1;
						if(rs.next()){
						 out.println("<td width=10%>"+rs.getString(1)+"</td><td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"YES\" checked><input type=hidden name=\"VEHICLE_NO_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
						 out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\"></td>");
						 j=j+1;
						}else{
						 out.println("<td width=10%></td><td width=10%></td><td width=10%></td>");
						}
						if(rs.next()){
						 out.println("<td width=10% >"+rs.getString(1)+"</td><td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"YES\" checked><input type=hidden name=\"VEHICLE_NO_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
						 out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\"></td>");
						 j=j+1;
						}else{
						 out.println("<td width=10%></td><td width=10%></td><td width=10%></td>");
						}
						
						out.println("</tr>");
					}
          					
          out.println("<input type=hidden name=hid_vcount value=\""+j+"\"></table>");

    }
    else if(m_chksql.trim().equals("get_Termi_Det")){
			
			    String m_Termination_no    = req.getParameter("Termination_no");
          
			   
					rs = stmt.executeQuery ("SELECT TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'), PERCENTAGE, RENTAL_AMOUNT,  "+
					                        "       RENTAL_PV,TERM_AMOUNT, TERM_PV "+
																	"FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION_DETAILS "+
                                  "WHERE  TERMINATION_NO='"+m_Termination_no+"'"); 
																					
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=6 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='15%' >Installment Date</td>");
					out.println("<td  width='5%'  align=right>Percentage</td>");
          out.println("<td  width='20%' align=right>Rental</td>");
					out.println("<td  width='20%' align=right>P.V.</td>");
					out.println("<td  width='20%' align=right>Termination Amount</td>");
					out.println("<td  width='20%' align=right>P.V.</td>");
					out.println("</tr>");
      
           int j = 0;
					 double rent=0;
					 double rpv =0;
					 double term=0;
					 double tpv =0;
					    		out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ ><b>");
									out.println("<td></td>");
                  out.println("<td ></td>");
                  out.println("<td align=right id=rent></td>");
                  out.println("<td align=right id=rpv ></td>");
									out.println("<td align=right id=term></td>");
									out.println("<td align=right id=tpv ></td>");
									out.println("</b></tr>");
							
							
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
					
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=6><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
					
          out.println("</table>");

    }
			
	    	else if(m_chksql.trim().equals("get_Termi_Char")){
			
			    String m_Lease_no     = req.getParameter("Lease_no");
          String m_Vehicle_no   = req.getParameter("Vehicle_no");
          String m_Disco_rate   = req.getParameter("Disco_rate");
          String m_App_date     = req.getParameter("App_Date");
          String m_client       = req.getParameter("Client");
					String m_lease_rate   = req.getParameter("lease_rate");
					String m_vat_rate     = req.getParameter("vat_rate");
          
					
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_CR_TEMP_TERMINATION_SAVE(:1,:2,:3,:4,:5,:6,:7,:8);END;");
				  callstmt1.setString(1 ,m_Disco_rate);
          callstmt1.setString(2 ,m_Vehicle_no);
          callstmt1.setString(3 ,m_Lease_no);
					callstmt1.setString(4 ,m_username);
					callstmt1.setString(5 ,m_App_date);
          callstmt1.setString(6 ,m_client);
 				  callstmt1.setString(7 ,m_lease_rate);
 				  callstmt1.setString(8 ,m_vat_rate);
 				 
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
					
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=6 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='15%' >Installment Date</td>");
					out.println("<td  width='5%'  align=right>Percentage</td>");
          out.println("<td  width='15%' align=right>Rental</td>");
					out.println("<td  width='15%' align=right>P.V. at Finance Rate</td>");
					out.println("<td  width='15%' align=right>P.V. at Termination Rate</td>");
					out.println("<td  width='15%' align=right>Termination Gain / Loss</td>");
					out.println("<td  width='20%' align=right>Gross Termination</td>");
					out.println("</tr>");
      
           int j = 0;
					 double rent=0;
					 double rpv =0;
					 double term=0;
					 double tpv =0;
					 double gtv =0;
							
					    		out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td></td>");
                  out.println("<td ></td>");
                  out.println("<td align=right id=rent></td>");
                  out.println("<td align=right id=rpv ></td>");
									out.println("<td align=right id=term></td>");
									out.println("<td align=right id=tpv></td>");
									out.println("<td align=right id=gtv ></td>");
									out.println("</tr>");
							
							
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input >");
									if(rs.getDouble(3)>=0){ 
									 out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
                  }else{
									 out.println("<td >NIBSM/AMI<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
									}
									out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j+"\"    value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j+"\"        value=\""+rs.getString(3)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j+"\"            value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
                  out.println("<td align=right>"+nf.format((rs.getDouble(5)-rs.getDouble(4)))+"<input type=hidden name=\"TER_PV_AM_"+j+"\" value=\""+(rs.getDouble(5)-rs.getDouble(6))+"\"></td>");
	                out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j+"\"     value=\""+rs.getString(6)+"\"></td>");
									out.println("");
									out.println("</tr>");
									rent = rent  + rs.getDouble(3);
									rpv  = rpv   + rs.getDouble(4);
									term = term  + rs.getDouble(5);
									tpv  = tpv   + (rs.getDouble(5)-rs.getDouble(4));
									gtv  = gtv   + rs.getDouble(6);
									  j=j+1;
									
									if(rs.next()){
									  out.println("<tr class=tr_input1 >");
										out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
	                  out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j+"\"    value=\""+rs.getString(2)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j+"\"        value=\""+rs.getString(3)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j+"\"            value=\""+rs.getString(4)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
	                  out.println("<td align=right>"+nf.format((rs.getDouble(5)-rs.getDouble(4)))+"<input type=hidden name=\"TER_PV_AM_"+j+"\" value=\""+(rs.getDouble(5)-rs.getDouble(6))+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j+"\"     value=\""+rs.getString(6)+"\"></td>");
										out.println("");
										out.println("</tr>");
										rent = rent  + rs.getDouble(3);
									  rpv  = rpv   + rs.getDouble(4);
									  term = term  + rs.getDouble(5);
									  tpv  = tpv   + (rs.getDouble(5)-rs.getDouble(4));
									  gtv  = gtv   + rs.getDouble(6);
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
									out.println("<td align=right>"+nf.format(gtv) +"<input type=hidden name=h_gtv  value="+nf.format(gtv)+"></td>");
									out.println("</tr>");
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=6><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("</table>");

    }else if(m_chksql.trim().equals("get_ODI_Details")){
			
			    String m_client      = req.getParameter("client");
          String m_fin_no      = req.getParameter("fin_no");
					double m_adj_bal     = new Double(req.getParameter("m_amt")).doubleValue();
					String m_type        = req.getParameter("type"); 					
					double tot_odi_bal_amt=0;
          double m_rec_tot   = 0;
					
					
					
					
					
					rs = stmt.executeQuery (" SELECT ODI_REF_NO,A.INVOICE_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY'), "+
																	"	       ODI_CAL_AMOUNT,ODI_SETTLED_AMOUNT, "+//-- STATUS,B.FINANCE_NO,B.CLIENT_CODE, A.SETTLED_STATUS, SETTLED_DATE,
																	"	       ODI_BAL_AMOUNT,NVL(A.ADJUSTED_AMOUNT,0),"+ //--A.ADJUSTED_USER, A.ADJUSTED_DATE,
																	"	       NVL(A.SETTLED_STATUS,'YES'),A.DUE_DATE, CURR_CODE "+//--,ODI_AMOUNT_CURR, ODI_BAL_AMOUNT_CURR, ODI_SETTLED_AMOUNT_CURR
																	"	FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A,"+
																	"        "+m_schema_name+".AF_CO_PRO_INVOICE B "+
																	"	WHERE  A.INVOICE_NO=B.INVOICE_NO AND "+																	
																	"        B.CLIENT_CODE='"+m_client+"' AND "+ 
																	"	       B.FINANCE_NO LIKE '"+m_fin_no+"' AND "+
																	"		 	   A.STATUS<>'CAN'  ");//AND A.SETTLED_STATUS='NO'
				
				
					 int j = 0;
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
					
					//if(rs.next()){
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					//out.println("<td  width='13%' >ODI Ref No</td>");
					out.println("<td  width='12%' >Invoice No</td>");
					out.println("<td  width='10%' >ODI Date</td>");
					out.println("<td  width='10%' align=right>ODI Amount</td>");
          out.println("<td  width='10%' align=right>ODI Settlement Amount</td>");
					//out.println("<td  width='10%' align=right>Adjusted Amount</td>");
					//out.println("<td  width='10%' align=right>Pending Adjusted Amount</td>");
					out.println("<td  width='10%' align=right>ODI Balance Amount</td>");
					out.println("<td  width='10%' >ODI Status</td>");
					out.println("<td  width='10%' align=right>Adjustment</td>");
					out.println("<td  width='5%' align=right></td>");
					//out.println("<td  width='10%'  ></td>");
					out.println("</tr>");
      
           double m_inv_bal = 0.0;     					
					 double m_new_adj_amt = 0.0;	
						
              while(rs.next()){					
							
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input >");
									out.println("<input type=hidden name=\"ODI_NO_"+j+"\" value=\""+rs.getString(1)+"\">");
                  out.println("<td >"+rs.getString(2)+"<input type=hidden name=\"INV_NO_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td >"+rs.getString(3)+"<input type=hidden name=\"ODI_DATE_"+j+"\" value=\""+rs.getString(3)+"\"></td>");
                  out.println("<td align=right>"+nf2.format(rs.getDouble(4)-rs.getDouble(7)) +"<input type=hidden name=\"ADJU_AMOUNT_"+j+"\" value=\""+rs.getString(7)+"\"><input type=hidden name=\"ODIC_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right>"+nf2.format(rs.getDouble(5)) +"<input type=hidden name=\"ODIS_AMOUNT_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
                  //out.println("<td align=right>"+nf.format(rs.getDouble(7)) +"<input type=hidden name=\"ADJU_AMOUNT_"+j+"\" value=\""+rs.getString(7)+"\"></td>");
									//out.println("<td align=right>"+nf2.format(rs.getDouble(11)) +"</td>");
									out.println("<td align=right>"+nf2.format(rs.getDouble(6)) +"<input type=hidden name=\"ODIB_AMOUNT_"+j+"\" value=\""+rs.getString(6)+"\"></td>");
									out.println("<td>");
									if(rs.getString(8).equals("NO")){
									out.println("<select name=\"ODI_STATUS_"+j+"\" class=\"txt_input\" >");
 			            out.println("<OPTION value=\"NO\" selected>Stop</option>");
									out.println("<OPTION value=\"YES\">Start</option>");
									out.println("</SELECT>");
									}else{
									out.println("<select name=\"ODI_STATUS+"+j+"\" class=\"txt_input\" >");
 			            out.println("<OPTION value=\"NO\" >Stop</option>");
									out.println("<OPTION value=\"YES\" selected >Start</option>");
									out.println("</SELECT>");
									}
									out.println("</td>");
									//--------------------------Added By Sandun on 16-01-2009-------------------------------------
									if(m_type.equals("DEC")){
									m_inv_bal  = m_inv_bal+rs.getDouble(6); 								
									
									if(m_rec_tot<m_inv_bal){
										if(m_adj_bal>= (m_inv_bal-m_rec_tot)){									  
										  								    
											out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\""+nf2.format((m_inv_bal-m_rec_tot))+"\" class=\"txt_input2\" onchange=\"Check_Amount("+j+"),chk_manu_bal("+j+")\" onBlur='check_number(document.Form1.SETT_AMOUN_"+j+",25)' disabled><input type=hidden name=\"ODI_STATUS_TYPE"+j+"\" value=\"A\"></td>");
									    //out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
                      out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"YES\" checked>");//,check_status_inv(\""+j+"\")
									    m_adj_bal = m_adj_bal-(m_inv_bal-m_rec_tot);
											m_rec_tot = m_rec_tot +(m_inv_bal-m_rec_tot);
										}else{
										 if(m_adj_bal>0){ 										  
									    									   
											out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\""+nf2.format(m_adj_bal)+"\" class=\"txt_input2\" onchange=\"Check_Amount("+j+"),chk_manu_bal("+j+")\" onBlur='check_number(document.Form1.SETT_AMOUN_"+j+",25)' disabled><input type=hidden name=\"ODI_STATUS_TYPE"+j+"\" value=\"A\"></td>");
									    //out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
                      out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"YES\" checked>");
																					
											m_rec_tot = m_rec_tot+m_adj_bal;
											m_adj_bal = 0;
										 }else{
																    
											out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\""+nf2.format(m_adj_bal)+"\" class=\"txt_input2\" onchange=\"Check_Amount("+j+"),chk_manu_bal("+j+")\" onBlur='check_number(document.Form1.SETT_AMOUN_"+j+",25)' disabled><input type=hidden name=\"ODI_STATUS_TYPE"+j+"\" value=\"A\"></td>");
									    //out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
                      out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\" >");
																						
										 }	
										}
									}else{
									   
																	   
											
											out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\""+nf2.format(0.00)+"\" class=\"txt_input2\" onchange=\"Check_Amount("+j+"),chk_manu_bal("+j+")\"  onBlur='check_number(document.Form1.SETT_AMOUN_"+j+",25)' disabled><input type=hidden name=\"ODI_STATUS_TYPE"+j+"\" value=\"A\"></td>");
									    //out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
                      out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\" >");
											
											
											
									}
									}	
									else{
									if(m_adj_bal==0.0){
									out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\""+nf2.format(m_adj_bal)+"\" class=\"txt_input2\" onchange=\"Check_Amount("+j+"),chk_manu_bal("+j+")\" onBlur='check_number(document.Form1.SETT_AMOUN_"+j+",25)' disabled><input type=hidden name=\"ODI_STATUS_TYPE"+j+"\" value=\"A\"></td>");
									//out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
                  out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\" >");
									}else{
									out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\""+nf2.format(-m_adj_bal)+"\" class=\"txt_input2\" onchange=\"Check_Amount("+j+"),chk_manu_bal("+j+")\" onBlur='check_number(document.Form1.SETT_AMOUN_"+j+",25)' disabled><input type=hidden name=\"ODI_STATUS_TYPE"+j+"\" value=\"A\"></td>");
									//out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
                  out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"YES\" checked>");
									}
									m_adj_bal=0.0;
									}
									//--------------------------------------------------------------								
									
									
								
									
									out.println("</td>");
									out.println("</tr>");
									j=j+1;
									tot_odi_bal_amt = tot_odi_bal_amt+rs.getDouble(6);
									
	         }
					
						out.println("<tr>");
						out.println("<td  width='55%' colspan=4><b>Total</td>");
						out.println("<td  width='10%' align=right><b>"+nf2.format(tot_odi_bal_amt)+"</td>");
						out.println("<td  width='25%' colspan=3>&nbsp;</td>");
						out.println("</tr>");
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          // }
				//	else{
					//out.println("<tr><td width='*%' align='center'><font color='red'>No Data Found..!</font></td></tr>");
				//	}
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
			//-----Added By Sandun On 16-01-2009---------------------------------------------
			else if(m_chksql.trim().equals("get_Receipt_sum")){
			
			 String m_client_code  = req.getParameter("client");
			 String m_finance_no   = req.getParameter("fin_no");
				
						rs = stmt.executeQuery (" SELECT "+
																		"	NVL(SUM(ODI_CAL_AMOUNT),0), "+
																		"	NVL(SUM(ODI_SETTLED_AMOUNT),0), "+ 
																		"	NVL(SUM(ODI_BAL_AMOUNT),0), "+
																		"	NVL(SUM(A.ADJUSTED_AMOUNT),0) "+
																		"	FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A, "+
																		"	       "+m_schema_name+".AF_CO_PRO_INVOICE B "+
																		"	WHERE  A.INVOICE_NO=B.INVOICE_NO "+ 
																		"	AND    B.CLIENT_CODE = '"+m_client_code+"' "+ 
																		"	AND    B.FINANCE_NO  LIKE '"+m_finance_no+"'  "+
																		"	AND     STATUS <> 'CAN' "+
																		"  ");
																		
					
					
			    out.println("<table class=table border='0' width='100%' >");
					
					if(rs.next()){
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='10%' align=right>ODI Amount</td>");
          out.println("<td  width='10%' align=right>Settled Amount</td>");
					out.println("<td  width='10%' align=right>Adjusted Amount</td>");
					out.println("<td  width='10%' align=right>Balance Amount</td>");
					out.println("<td  width='10%' align=center>Amount</td>");
					out.println("<td  width='15%' align=center>Adjustment Type</td>");
					out.println("<td  width='25%' align=center>Remark</td>");
					out.println("<td  width='10%' align=center>&nbsp;</td>");
					out.println("</tr>");
								
				 
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='10%' align=right>"+nf2.format(rs.getDouble(1))+"</td>");
          out.println("<td  width='10%' align=right>"+nf2.format(rs.getDouble(2))+"</td>");
					out.println("<td  width='10%' align=right>"+nf2.format(rs.getDouble(4))+"</td>");
					out.println("<td  width='10%' align=right>"+nf2.format(rs.getDouble(3))+"</td>");
					// Modified by Thamali Jayatunga on 2009.10.12, Added maxlength='25' and replace argument to 21 in function call to check_number 
					out.println("<td  width='10%' align=center><input type='text' name='ADJ_VALUE' maxlength='25' class='txt_input' style='text-align:right' onBlur='check_number(document.Form1.ADJ_VALUE,21)'></td>");
					out.println("<td  width='15%' align=center><select class='txt_input' name='SELECT_CRT'><option value='INC'>Increase</option><option value='DEC'>Decrease</option></select></td>");						
					out.println("<td  width='25%' align=center><input type='text' name='TXT_REMARK' style='width:250' class='txt_input'></td>");
					out.println("<td  width='10%' align=center><input type='button' name='BTT_ALLO' value='Allocate' class='but_input' onClick=get_Receipt()></td>");
					out.println("</tr>");
					}
					else{
					out.println("<tr><td width='*%' align='center'><font color='red'>No Data Found..!</font></td></tr>");
					}
					out.println("</table>");
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
