//Option Id is 1.65 
//This File was created by SVA on 28-08-2006 
//1.65 Allocate Unallocated Receipt Process Display
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;

import oracle.jdbc.driver.*;

public class LAKDL_AF_CO_ReceiptAllocation extends javax.servlet.http.HttpServlet {
	
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
          
      out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Receipt Allocate / Unallocate</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
      out.println(" var b_rec_status=0;");
			out.println(" var b_flag_inv=0;"); //added by nuwan de silva on 28-02-2008

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
				out.println("         rec.innerHTML=http_request.responseText; ");
				out.println("         cal_b_a_amount();");
				//out.println("         get_alocate_amt();");
				
				out.println("         alocate_amts();");
				//out.println("         alert('http2');");
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
				//out.println("        m_del_row=document.Form1.hid_del_row_no.value;");
				//out.println("         document.Form1.elements[\"SETT_AMOUN_0\"].value=format_noobject(document.Form1.tot_val.value); ");
				out.println("         document.Form1.elements['SETT_AMOUN_'+document.Form1.hid_del_row_no.value].value=format_noobject(document.Form1.tot_val.value); ");
				
				out.println("      }else if(opt==\"7\"){");
				//out.println("         alert(http_request.responseText);");
				out.println("         rec_alc.innerHTML=http_request.responseText; ");
				out.println("      }else if(opt==\"5\"){");
				//out.println("         alert(http_request.responseText);");
				out.println("         rec.innerHTML=http_request.responseText; ");
				out.println("      }else if(opt==\"12\"){");
				//out.println("         alert(http_request.responseText);");
				//out.println("         auto_allo.innerHTML=http_request.responseText; ");  
				out.println("         rec_alc.innerHTML=http_request.responseText; ");
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
			 out.println("     document.Form1.CLIENT_NAME.value     =data[1];");
			 //out.println("     get_Receipt();");
			 //out.println("     get_Receipt_Allocation();");  //Comment by Chandana on 22/10/2007
				out.println("   }else if(type=='Veh'){"); 
			 out.println("     document.Form1.VEHICLE_NO.value      =data[0];"); 
			 //out.println("     get_Receipt();"); 
			 //out.println("     get_Receipt_Allocation();"); //Comment by Chandana on 22/10/2007
				out.println("   }else if(type=='Lea'){"); 
			 out.println("     document.Form1.LEASE_NO.value        =data[0];"); 
			 //out.println("     get_Receipt();"); 
			 //out.println("     get_Receipt_Allocation();"); //Comment by Chandana on 22/10/2007 
				out.println("   }"); 
       out.println(" }");
       out.println("}");
				//End Of Checking Values
				
				
				out.println("function inv_help(num){");
				out.println(" document.Form1.hid_opt_val.value=num;"); 
			  out.println("	popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_ReceiptAllocation?chksql=get_Invoice&client=\"+document.Form1.CLIENT_CODE.value+\"\", \"oBj\",\"left=130,top=200,width=750,height=400\");"); 
			  out.println("}");		
				
				/*
				out.println("  <td align=left ><input type=text name=\"INV_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(1)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=left ><input type=text name=\"V_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(2)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=right><input type=text name=\"INV_AM_"+j+"_"+i+"\" disabled value=\""+rs1.getString(3)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=right><input type=text name=\"BAL_AM_"+j+"_"+i+"\" disabled value=\""+rs1.getString(4)+"\" class=\"txt_input2\"></td>");
									m_inv_bal  = m_inv_bal+rs1.getDouble(4);
									if(m_rec_tot<m_inv_bal){
										if(m_rec_bal>= (m_inv_bal-m_rec_tot)){
										  //out.println("m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
										  out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format((m_inv_bal-m_rec_tot))+"\" class=\"txt_input2\" onchange=\"chk_bal('"+j+"','"+i+"')\"></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");
				*/				
				
				out.println("function check_status(num1,num2) {");
				//out.println("alert(document.Form1.elements['Text_standard'+num].checked);");
				out.println("if(document.Form1.elements['Text_standard'+num1+'_'+num2].checked){");
				out.println("document.Form1.elements['Text_sett_amount'+num1+'_'+num2].disabled =true;");	 //added nuwan
				out.println("  cal_amount(num1,num2);");
				out.println("  cal_b_a_amount();");
				//out.println(" document.Form1.elements['Text_standard'+num].value=\"YES\";");
				out.println("}else{");
				out.println("  document.Form1.elements['Text_sett_amount'+num1+'_'+num2].value   =\"0.00\";");
				out.println("  document.Form1.elements['Text_standard'+num1+'_'+num2].value=\"NO\";");
				out.println("  document.Form1.elements['Text_sett_amount'+num1+'_'+num2].disabled=false;");
				out.println("  cal_b_a_amount();");
				out.println("}");
				out.println("}");
				
				out.println("function cal_amount(num1,num2) {");//
				out.println("  document.Form1.hid_win_opt.value=num2;");
				out.println("  document.Form1.hid_opt_val.value=num1;");
				out.println("  m_inv_bal  = 0;");
				out.println("  m_inv_allo = 0;");
				out.println("  m_rec_allo = 0;");
				out.println("  m_rec_bal  = parseFloat(document.Form1.elements['BAL_AMOUNT_'+num1].value);");
				//out.println("  alert(document.Form1.elements['INV_NO_'+num1+'_'+num2].value);"); //
				
				out.println("  for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
				//out.println("   if(i!=parseFloat(num1)){ ");
				out.println("    for(j=0;j<parseFloat(document.Form1.elements['hid_invoice_count_'+i].value);j++){");	
				out.println("      if(document.Form1.elements['INV_NO_'+i+'_'+j].value   ==document.Form1.elements['INV_NO_'+num1+'_'+num2].value){"); //
				//out.println("        alert(document.Form1.elements['Text_sett_amount'+i+'_'+j].value); ");	
				out.println("        m_inv_allo = parseFloat(m_inv_allo)+parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+i+'_'+j].value)) ");	
				out.println("        m_inv_bal  = parseFloat(document.Form1.elements['BAL_AM_'+i+'_'+j].value); ");	
				out.println("      }");	
				out.println("    }");	
				//out.println("   }");
				out.println("  }");
				//  
				out.println("    for(j=0;j<parseFloat(document.Form1.elements['hid_invoice_count_'+num1].value);j++){");	
				out.println("       //if(j!=parseFloat(num2)){ ");
				out.println("        m_rec_allo = parseFloat(m_rec_allo)+parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+num1+'_'+j].value)) ");	
				out.println("       //}"); 
				//out.println("        m_inv_bal  = parseFloat(document.Form1.elements['BAL_AM_'+num1+'_'+j].value); ");	
				out.println("    }");	
				
				//out.println("  alert('m_inv_allo='+m_inv_allo+'---m_inv_bal='+m_inv_bal+'---m_rec_bal='+m_rec_bal+'---m_rec_allo='+m_rec_allo)");
        out.println("   if(parseFloat(m_inv_allo)>0){"); 
				out.println("   if(parseFloat(m_inv_allo)>parseFloat(m_inv_bal)){ ");
				out.println("      document.Form1.elements['Text_standard'+num1+'_'+num2].value      =\"NO\";");
				out.println("      document.Form1.elements['Text_sett_amount'+num1+'_'+num2].disabled=false;");
				out.println("      document.Form1.elements['Text_sett_amount'+num1+'_'+num2].value   =0;");
				out.println("      document.Form1.elements['Text_standard'+num1+'_'+num2].checked    =false;");
				out.println("   }else{");	
				out.println("     if(parseFloat(m_inv_bal-m_inv_allo)<parseFloat(m_rec_bal) && parseFloat(m_rec_bal)<parseFloat(m_rec_allo)){ ");
				out.println("      document.Form1.elements['Text_standard'+num1+'_'+num2].value      =\"NO\";");
				out.println("      document.Form1.elements['Text_sett_amount'+num1+'_'+num2].disabled=false;");
				out.println("      document.Form1.elements['Text_sett_amount'+num1+'_'+num2].value   =0;");
				out.println("      document.Form1.elements['Text_standard'+num1+'_'+num2].checked    =false;");
				out.println("     }else{");
				out.println("      document.Form1.elements['Text_standard'+num1+'_'+num2].value      =\"YES\";");
				out.println("      document.Form1.elements['Text_sett_amount'+num1+'_'+num2].disabled=true;");
				//out.println("       m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_ReceiptAllocation?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
        //out.println("       window.open(m_url);");
				//out.println("       makeRequest(m_url,'3');");
				out.println("     }");	
				out.println("   }");
				out.println("   }else if(parseFloat(m_rec_bal)<parseFloat(m_rec_allo)){"); 
				out.println("      document.Form1.elements['Text_standard'+num1+'_'+num2].value      =\"NO\";");
				out.println("      document.Form1.elements['Text_sett_amount'+num1+'_'+num2].disabled=false;");
				out.println("      document.Form1.elements['Text_sett_amount'+num1+'_'+num2].value   =0;");
				out.println("      document.Form1.elements['Text_standard'+num1+'_'+num2].checked    =false;");
				
				out.println("     }else{");	
				out.println("      document.Form1.elements['Text_standard'+num1+'_'+num2].value      =\"YES\";");
				out.println("      document.Form1.elements['Text_sett_amount'+num1+'_'+num2].disabled=true;");
				//out.println("       m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_ReceiptAllocation?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
        //out.println("     window.open(m_url);");
				//out.println("       makeRequest(m_url,'3');");
				out.println("     }");	
				out.println("}");	
				
				
				out.println("function cal_b_a_amount(num1,num2) {");//$$$$$$$$$$
				out.println("  document.Form1.hid_win_opt.value=num2;");
				out.println("  document.Form1.hid_opt_val.value=num1;");
				out.println("  m_inv_bal  = 0;");
				out.println("  m_inv_allo = 0;");
				out.println("  m_rec_allo = 0;");
				//out.println("  m_rec_bal  = parseFloat(document.Form1.elements['BAL_AMOUNT_'+num1].value);");
				//out.println("  alert(document.Form1.elements['INV_NO_'+num1+'_'+num2].value);"); //
				
				out.println("  for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
				out.println("  m_inv_allo = 0;");
				//out.println("   if(i!=parseFloat(num1)){ ");
				out.println("    for(j=0;j<parseFloat(document.Form1.elements['hid_invoice_count_'+i].value);j++){");	
				out.println("      if(document.Form1.elements['Text_standard'+i+'_'+j].checked==true){"); //
				//out.println("        alert(document.Form1.elements['Text_sett_amount'+i+'_'+j].value); ");	
				out.println("        m_inv_allo = parseFloat(m_inv_allo)+parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+i+'_'+j].value)) ");	
				//out.println("        m_inv_bal  = parseFloat(document.Form1.elements['BAL_AM_'+i+'_'+j].value); ");	
				out.println("      }");	
				out.println("    }");	
				out.println("    document.Form1.elements['A_AMOUNT_'+i].value  = format_noobject(m_inv_allo);"); //
				out.println("    document.Form1.elements['BA_AMOUNT_'+i].value = format_noobject(parseFloat(unformat_noobject(document.Form1.elements['BAL_AMOUNT_'+i].value))-parseFloat(m_inv_allo));"); //
				
				//out.println("   }");
				out.println("  }");
					
				out.println("}");
				
				
				out.println("function chk_bal(obj,num) {");//
				out.println("   document.Form1.elements['Text_sett_amount'+obj+'_'+num].value=format_noobject(document.Form1.elements['Text_sett_amount'+obj+'_'+num].value);");	
				out.println("}");	
								
				out.println("function Status_Change(obj,num) {");//
				out.println(" if(obj.checked==true){");
				out.println("   obj.value=\"YES\";");	
				//out.println("   document.Form1.elements['Text_sett_amount'+num].value=\"0\";");	
				out.println(" }else{");	
				out.println("   obj.value=\"NO\";");	
				out.println("   document.Form1.elements['Text_sett_amount'+num].value=\"0\";");	
				out.println(" }");	
				//out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_ReceiptAllocation?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
        //out.println("   window.open(m_url);");
				//out.println("   makeRequest(m_url,'3');");
				out.println("}");	
				
				out.println("function cal_amount1(opt,am1,am2,num) {");//
				out.println("   document.Form1.hid_win_opt.value=num;");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_ReceiptAllocation?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'3');");
				
        out.println("}");	
				
				out.println("function cal_amount_del(opt,am1,am2,num) {");//
				out.println("   document.Form1.hid_win_opt.value=num;");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_ReceiptAllocation?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'6');");
				
        out.println("}");	
				
				//Added by Chandana on 20/09/2007  
				out.println("function get_Receipt_Allocation(val) {");
			  // out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_ReceiptAllocation?chksql=get_Receipt_Allocation&client=\"+document.Form1.CLIENT_CODE.value+\"&lea_no=\"+document.Form1.LEASE_NO.value+\"&vih_no=\"+document.Form1.VEHICLE_NO.value+\"\";"); 
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_ReceiptAllocation?chksql=get_Receipt_Allocation_2&client=\"+document.Form1.CLIENT_CODE.value+\"&lea_no=\"+document.Form1.LEASE_NO.value+\"&vih_no=\"+document.Form1.VEHICLE_NO.value+\"\";"); 
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'7');");
				out.println("}");	
							
				
			  out.println("function get_Receipt(m_stat,opt) {");
				//out.println("alert('get_Receipt');"); get_Invoice_alocation
				//out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_ReceiptAllocation?chksql=get_Receipt&client=\"+document.Form1.CLIENT_CODE.value+\"&lea_no=\"+document.Form1.LEASE_NO.value+\"\";");
        //out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_ReceiptAllocation?chksql=get_Invoice_alocation&client=\"+document.Form1.CLIENT_CODE.value+\"&lea_no=\"+document.Form1.LEASE_NO.value+\"\";");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_ReceiptAllocation?chksql=get_Invoice_alocation_2&client=\"+document.Form1.CLIENT_CODE.value+\"&lea_no=\"+document.Form1.LEASE_NO.value+\"\";");
				
 				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'2');");
				out.println("}");	
				
	      out.println("function get_manual_alocation() {");
				//out.println("alert('get_Receipt');"); get_Invoice_alocation
				//out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_ReceiptAllocation?chksql=get_Receipt&client=\"+document.Form1.CLIENT_CODE.value+\"&lea_no=\"+document.Form1.LEASE_NO.value+\"\";");
        out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_ReceiptAllocation?chksql=get_manual_alocation&client=\"+document.Form1.CLIENT_CODE.value+\"&lea_no=\"+document.Form1.LEASE_NO.value+\"\";");
 				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'12');");
				out.println("}");		
		      
		
		
			
				out.println("function get_Receipt_del(val) {");
				//out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_ReceiptAllocation?chksql=get_Receipt_del&rec_no=\"+val+\"\";");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_ReceiptAllocation?chksql=get_Receipt_del_new&rec_no=\"+val+\"\";");
				
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
				
				out.println("function check_vehicle(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_XMLFile?chksql=get_veh_no&veh_no=\"+document.Form1.VEHICLE_NO.value+\"\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','Veh');");
				
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
			
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to Save?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Save';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 

      out.println("function befor_allo(){ "); 
			//out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			//out.println("document.Form1.elements[i].disabled=false;");
			//out.println("}");
			//out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to run the invoice auto allocation routeen?\")){ "); 
			out.println("   document.Form1.Hid_scr_name.value=\"AUTO_ALLOCATION_INV\";");  
			out.println("		document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Save';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			//out.println("		}"); 
			out.println("} "); 

      

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function befor_reset(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		  window.location.href=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_ReceiptAllocation?chksql=main_page\";"); 
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
			out.println("help_box.innerHTML=\"Collection - Receipt Allocate / Unallocate - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"Collection - Receipt Allocate / Unallocate - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function befor_back(){");
			out.println("  close_window();");
			out.println("}");
			
			out.println("function load_screen_status(m_val){"); 
			out.println("    document.Form1.hid_option.value    =m_val;"); 
			out.println("if(m_val==\"NEW\"){"); 
			//out.println("new_window();");
			//out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.CLIENT_CODE.disabled=false;"); 
			out.println("document.Form1.cli_help.disabled=false;"); 
			out.println("document.Form1.rec_help.disabled=true;"); 
			out.println("document.Form1.RECEPT_NO.disabled=true;}"); 
			
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val==\"DELETE\"){"); 
			out.println("document.Form1.CLIENT_CODE.disabled=true;"); 
			out.println("document.Form1.cli_help.disabled=true;"); 
			out.println("document.Form1.rec_help.disabled=false;"); 
			out.println("document.Form1.RECEPT_NO.disabled=false;"); 
			out.println("document.Form1.TXT_FIFO.disabled=true;");
			out.println("document.Form1.Allocate.disabled=true;");
			out.println("document.Form1.LEASE_NO.disabled=true;");
			out.println("document.Form1.lea_help.disabled=true;");
			
			
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
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
			out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_CO_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
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
				out.println("HelpBox('1','10','0',Crit,'ClientSql','1');");
				out.println("}");		
				out.println("function client_assign(oBj){");
				out.println(" document.Form1.CLIENT_NAME.value =oBj.valout[3]");
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[2]");
				//out.println(" get_Receipt();");
				//out.println("  get_Receipt_Allocation();"); Comment Chandana on 22/10/2007
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
				out.println("Crit=document.Form1.LEASE_NO.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'LeaseSql','3');");
				out.println("}");	
				
				out.println("function lease_assign(oBj){");
				out.println(" document.Form1.LEASE_NO.value =oBj.valout[2]");
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[6]");
				out.println(" document.Form1.CLIENT_NAME.value =oBj.valout[4]");
				out.println(" if(document.Form1.hid_option.value=='NEW'){");//lea_no
				//out.println("  get_Receipt(document.Form1.LEASE_NO.value);");
				//out.println("     get_Receipt_Allocation();"); //Comment by Chandana on 22/10/2007
				out.println(" }else{");
				out.println("  get_Receipt_del(document.Form1.LEASE_NO.value);");
				out.println(" }"); 
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");
			//Vehicle Help
			  out.println("function vehicle_help(){");
				out.println("Crit=document.Form1.VEHICLE_NO.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'VehicleSql','4');");
				out.println("}");	
				
				out.println("function vehicle_assign(oBj){");
				out.println(" document.Form1.VEHICLE_NO.value =oBj.valout[2]");
				//out.println("     get_Receipt_Allocation();"); //Comment by Chandana on 22/10/2007
				//out.println(" get_Receipt_del(document.Form1.VEHICLE_NO.value);"); //vvvvvv
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
							
			out.println("function load_history(num) {");
			out.println("	if(num!=''){ ");
      out.println("	  popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup?chksql=get_History&deal_no=\"+num+\"\", \"oBj\",\"left=100,top=200,width=650,height=400\");"); 
			out.println("	}else{");
			out.println("	  alert('Please enter Followup Number and continue!');");
			out.println("	}");
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
											
			/*
			out.println("function check_status(num) {");
				//out.println("alert(document.Form1.elements['Text_standard'+num].checked);");
			out.println("if(document.Form1.elements['Text_standard'+num].checked){");
			out.println(" document.Form1.elements['Text_standard'+num].value=\"YES\";");
			out.println("}else{");
			out.println(" document.Form1.elements['Text_standard'+num].value=\"NO\";");
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
			
			
			out.println("function check_status_del_new(row,num) {");
			out.println("document.Form1.hid_del_row_no.value=row;");
				//out.println("alert(document.Form1.elements['Text_standard'+num].checked);");
			out.println("if(document.Form1.elements['Text_standard'+row+'_'+num].checked){");
			out.println(" document.Form1.elements['Text_standard'+row+'_'+num].value=\"YES\";");
			out.println(" cal_amount_del('add',unformat_noobject(document.Form1.elements['SETT_AMOUN_'+row].value),unformat_noobject(document.Form1.elements['Text_sett_amount'+row+'_'+num].value),num);");
			out.println("}else{");
			out.println(" document.Form1.elements['Text_standard'+row+'_'+num].value=\"NO\";");
			out.println(" cal_amount_del('min',unformat_noobject(document.Form1.elements['SETT_AMOUN_'+row].value),unformat_noobject(document.Form1.elements['Text_sett_amount'+row+'_'+num].value),num);");
			out.println("}");
			out.println("}");
			
			
			out.println("function check_status_del(num) {");
				//out.println("alert(document.Form1.elements['Text_standard'+num].checked);");
			out.println("if(document.Form1.elements['Text_standard0_'+num].checked){");
			out.println(" document.Form1.elements['Text_standard0_'+num].value=\"YES\";");
			out.println(" cal_amount_del('add',unformat_noobject(document.Form1.SETT_AMOUN_0.value),unformat_noobject(document.Form1.elements['Text_sett_amount0_'+num].value),num);");
			out.println("}else{");
			out.println(" document.Form1.elements['Text_standard0_'+num].value=\"NO\";");
			out.println(" cal_amount_del('min',unformat_noobject(document.Form1.SETT_AMOUN_0.value),unformat_noobject(document.Form1.elements['Text_sett_amount0_'+num].value),num);");
			out.println("}");
			out.println("}");
			
			
			
			out.println("function chk_bal(obj) {");
			//out.println("alert('test'+obj);");  
			out.println("validate_receipts(obj);");
			out.println("if(b_rec_status==1) {");
			out.println("m_rec_bal    = parseFloat(unformat_noobject(document.Form1.elements['BAL_AMOUNT1_'+obj].value));");
			out.println("m_alc_amount = parseFloat(unformat_noobject(document.Form1.elements['A_AMOUNT1_'+obj].value));");
			out.println("m_bal_amount = m_rec_bal - m_alc_amount;");
			out.println("if(m_bal_amount>0){");
			out.println("document.Form1.elements['BA_AMOUNT1_'+obj].value = format_noobject(m_bal_amount);");
			out.println("}else{");
			out.println("alert('Maximum alocated amount should be Rs.'+format_noobject(m_rec_bal));");
			out.println("document.Form1.elements['A_AMOUNT1_'+obj].value = format_noobject(m_rec_bal);");
			out.println("document.Form1.elements['BA_AMOUNT1_'+obj].value = format_noobject(0.00);");
			out.println("}");
			
			out.println("}");
			out.println("else {");
			out.println("alert('please select receipt by an order');");
			out.println("document.Form1.elements['A_AMOUNT1_'+obj].value =0;");
			out.println("}");
			
			out.println("}");
			
			//added by nuwan de silva on 28-02-2008-----
			out.println("function validate_receipts(obj){");
			out.println("var row_no=0;");
			out.println("b_rec_status=0;");
			out.println("if(obj!=0){");
			out.println("row_no=obj-1;");
			out.println(" if(document.Form1.elements['BA_AMOUNT1_'+row_no].value==0.00){");
			out.println("b_rec_status=1;");
			out.println("}");
			out.println("}else if(obj==0){");
			out.println("b_rec_status=1;");
			out.println("}");
			out.println("}");
			
			
			out.println("function chk_bal_amnt(obj,num) {");//
		  out.println("   document.Form1.elements['Text_sett_amount'+obj+'_'+num].value=format_noobject(document.Form1.elements['Text_sett_amount'+obj+'_'+num].value);");	
			out.println("}");	
			
			
			
			//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
			
			//--------Added by Chandana for Auto Allocation option on 02/10/2007---------------------
			
			out.println("function chk_bal_amt(num,num2) {");
			
			out.println("var m_count =0;");
			out.println("var m_aloc_amt=0.00;");
			out.println("var m_rec_alc_amount=0.00;");
			out.println("m_count = parseFloat(document.Form1.elements['hid_invoice_count_'+num].value);");
			
      out.println("for(j=0;j<m_count;j++){");
			out.println("m_aloc_amt = m_aloc_amt + parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+num+'_'+j].value));");
			//out.println("alert('m_aloc_amt==='+j+'=='+m_aloc_amt);");
			out.println("}");
			
			out.println("m_rec_alc_amount = parseFloat(unformat_noobject(document.Form1.elements['A_AMOUNT1_'+num].value));");
			out.println("document.Form1.elements['BA_AMOUNT_'+num].value = format_noobject(m_rec_alc_amount - m_aloc_amt);");
			
			out.println("calculate_inv_balance(num,num2);");			
		
			out.println("}");
			
			
			
			/*out.println("function check_aloc_amt(obj1,obj2,obj3,obj4){ ");	
			
			out.println("obj44=((parseFloat(obj4))-1);");
			out.println("M_TOT = parseFloat(unformat_noobject(document.Form1.elements['ODI_VAL_'+obj4].value)) + parseFloat(unformat_noobject(document.Form1.elements['INV_VAL_'+obj4].value));");
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
			out.println("if(obj4==0){");
			out.println("if((M_TOT < (M_ODI+M_INV))||(M_AMT < (M_ODI+M_INV))){");
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
			out.println("var tot=0");
			out.println("for(j=0; j<num_of_contract; j++){"); 
			out.println("odi_sett_amount=parseFloat(unformat_noobject(document.Form1.elements['Text_odi_sett_amount'+j].value));");
      out.println("inv_sett_amount=parseFloat(unformat_noobject(document.Form1.elements['Text_inv_sett_amount'+j].value));");
			out.println(" balance = (amount - (odi_sett_amount + inv_sett_amount) - tot);");
			out.println("if(balance>=0){");
			out.println(" document.Form1.elements['Text_balance_amount'+j].value = balance;");
			out.println(" tot = tot + (odi_sett_amount + inv_sett_amount);");
			out.println("}");
			out.println("}");
			out.println("}");	
      */
			
			  out.println("function calculate_inv_balance(num,num2) {");
   			
				out.println("var m_inv_amount=0.00;");
        out.println("var m_inv_no=0.00;");
        out.println("var m_inv_allo=0.00;");
        out.println("var m_inv_no='';");
				
				out.println(" m_inv_amount =  parseFloat(unformat_noobject(document.Form1.elements['BAL_AM_'+num+'_'+num2].value));");
			  out.println(" m_inv_no     =  document.Form1.elements['INV_NO_'+num+'_'+num2].value;");
 				
				//out.println("    alert('Alocated amount exceeds invoice balance amount - CHECK' +m_inv_amount);");
				//out.println("        alert('rec count' +document.Form1.hid_count_total.value);");
				
			  out.println("  for(i=0;i<parseFloat(document.Form1.hid_count_total.value);i++){");
				out.println("    for(j=0;j<parseFloat(document.Form1.elements['hid_invoice_count_'+i].value);j++){");	
				//out.println("    alert('inv no' +document.Form1.elements['INV_NO_'+i+'_'+j].value);");
				out.println("      if(document.Form1.elements['INV_NO_'+i+'_'+j].value == m_inv_no){"); //
				out.println("        m_inv_allo = parseFloat(m_inv_allo)+parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+i+'_'+j].value)) ");	
				//out.println("        alert('m_inv_allo' +m_inv_allo);");
				out.println("      }");	
				out.println("    }");	
				out.println("    }");	
				
				out.println("    if(m_inv_allo > m_inv_amount ) {");	
				out.println("    alert('Alocated amount exceeds invoice balance amount');");
				out.println("    document.Form1.elements['Text_sett_amount'+num+'_'+num2].value = format_noobject(0.00);");
				out.println("    }");	
				
			  out.println("    }");	
				
			
			out.println("function chk_alocate_amt(num,num2) {");
			out.println("m_num2    = parseFloat(unformat_noobject(num2));");
			//out.println("alert('m_num2==='+m_num2);");
			out.println("var m_count =0;");
			out.println("var m_aloc_amt=0.00;");
			out.println("var m_tot_aloc=0.00;");
			
			out.println("var m_inv_amount=0.00;"); //added by nuwan de silva on 19-08-2008
			out.println("var m_amount=0.00;");
			
			out.println("m_count = parseFloat(document.Form1.elements['hid_invoice_count_'+num].value);");
			out.println("m_tot_aloc = parseFloat(unformat_noobject(document.Form1.elements['A_AMOUNT_'+num].value));");
			
			//added by nuwan de silva on 19-08-2008
			out.println("m_inv_amount = parseFloat(unformat_noobject(document.Form1.elements['BAL_AM_'+num+'_'+num2].value));");
			out.println("m_amount = parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+num+'_'+num2].value));");
			
			out.println("if(m_amount > m_inv_amount){");
			out.println("alert('Alocated amount exceeds invoice balance amount');");
			out.println("document.Form1.elements['Text_sett_amount'+num+'_'+num2].value = format_noobject(0.00);");
      out.println("}");
			
			// -------------- end by nuwan de silva on 19-08-2008 -----------------------------------------------
			
			out.println("for(j=0;j<m_count;j++){");
			out.println("m_aloc_amt = m_aloc_amt + parseFloat(unformat_noobject(document.Form1.elements['Text_sett_amount'+num+'_'+j].value));");
			//out.println("alert('m_aloc_amt==='+j+'=='+m_aloc_amt);");
			out.println("}");
			
			//out.println("alert(m_tot_aloc+' < '+m_aloc_amt);");
			out.println("if(m_tot_aloc < m_aloc_amt){");
			out.println("alert('Total Allocated Amount Should Be Rs.'+format_noobject(m_tot_aloc));");
			out.println("document.Form1.elements['Text_sett_amount'+num+'_'+num2].value = format_noobject(0.00);");
			out.println("}");
			
			out.println("chk_bal_amt(num,num2);");			
			
			out.println("}");
			
				//added by the nuwan de silva on 28-02-2009
				out.println("function validate_check_status_inv(num,num2) {");
				out.println("b_flag_inv=0;");
				out.println("var m_count_inv=num2;");
				out.println("if(parseInt(m_count_inv)!=0) {");
				out.println("for(var m_inv=0;m_inv<parseInt(m_count_inv);m_inv++){");
				//out.println("if(!document.Form1.elements['Text_standard'+num+'_'+m_inv].checked &&  document.Form1.elements['hid_invoice_type'+num+'_'+m_inv].value==\"INV_GENER\" ){");
				out.println("if(!document.Form1.elements['Text_standard'+num+'_'+m_inv].checked ){");
				//out.println("b_flag_inv=1;");
				out.println("break;");
				out.println("}");
				out.println("}");
				out.println("}");
				out.println("}");
				
				//added by the nuwan de silva on 28-02-2009
				out.println("function unallocate_invoices(num,num2) {");
				out.println("var m_count_inv=document.Form1.elements['hid_invoice_count_'+num].value;");
				out.println("if(parseInt(m_count_inv)!=0) {");
				out.println("for(var m_inv=num2;m_inv<parseInt(m_count_inv);m_inv++){");
				out.println("if(document.Form1.elements['Text_standard'+num+'_'+m_inv].checked){");
				out.println("  document.Form1.elements['Text_sett_amount'+num+'_'+m_inv].value    =0;");
				out.println("  document.Form1.elements['Text_sett_amount'+num+'_'+m_inv].disabled =false;");
				out.println("  document.Form1.elements['Text_standard'+num+'_'+m_inv].checked      =false;");
				out.println("  document.Form1.elements['Text_standard'+num+'_'+m_inv].value       =\"NO\";");
			  out.println("}");
				out.println("}");
				out.println("}");
				out.println("}");

			
			
			out.println("function chk_status(num,num2) {");
			out.println("if(document.Form1.elements['Text_standard'+num+'_'+num2].checked){");
			out.println("validate_check_status_inv(num,num2);"); //added by the nuwan de silva on 28-02-2009
			 //out.println(" document.Form1.elements['Text_standard'+num+'_'+num2].value=\"YES\";");
			//out.println(" document.Form1.elements['Text_sett_amount'+num+'_'+num2].disabled=true;");
			
				out.println("if(b_flag_inv==0){"); //added by the nuwan de silva on 28-02-2008
				out.println(" document.Form1.elements['Text_standard'+num+'_'+num2].value=\"YES\";");
				out.println(" document.Form1.elements['Text_sett_amount'+num+'_'+num2].disabled=true;");
				out.println("}");
				out.println("else{");
				out.println("alert('Please select the invoices by order');");
				out.println("  document.Form1.elements['Text_standard'+num+'_'+num2].checked      =false;");
				out.println("  document.Form1.elements['Text_standard'+num+'_'+num2].value        =\"NO\";");
				out.println("  document.Form1.elements['Text_sett_amount'+num+'_'+num2].value     =0;");
				out.println("}");
			
      out.println("}else{");
			out.println(" document.Form1.elements['Text_standard'+num+'_'+num2].value=\"NO\";");
			out.println(" document.Form1.elements['Text_sett_amount'+num+'_'+num2].value=format_noobject(0.00);");
			out.println(" document.Form1.elements['Text_sett_amount'+num+'_'+num2].disabled=false;");
			//out.println(" window.opener.cal_amount('min',unformat_noobject(document.Form1.tot_val.value),unformat_noobject(document.Form1.elements['Text_sett_amount'+num+'_'+num2].value),num,document.Form1.elements['INV_NO_'+num+'_'+num2].value,num2);");
			out.println("  unallocate_invoices(num,num2);"); //added by the nuwan de silva on 28-02-2009

			out.println("}");
			
			out.println("chk_bal_amt(num,num2);");
			out.println("}");			
			
			
		  out.println("function alocate_amts() {"); 
			out.println("m_rec_cnt    = parseFloat(unformat_noobject(document.Form1.hid_rec_count.value));");
      out.println("var m_rec_tot=0;");

			//out.println("alert('m_rec_cnt'+m_rec_cnt);");
			out.println("for(j=0;j<m_rec_cnt;j++){");
			out.println("m_rec_alc_amount1 = parseFloat(unformat_noobject(document.Form1.elements['A_AMOUNT1_'+j].value));"); 
			out.println("m_rec_bal = m_rec_alc_amount1;");			
			out.println("m_rec_bal_amount1 = parseFloat(unformat_noobject(document.Form1.elements['BA_AMOUNT1_'+j].value));");

      out.println("var m_inv_bal=0;"); 
      out.println("var m_alloamt=0;");
			//out.println("alert('m_rec_alc_amount1'+m_rec_bal+'m_rec_bal_amount1'+m_rec_bal_amount1);");
      
			out.println("document.Form1.elements['A_AMOUNT_'+j].value  = format_noobject(m_rec_alc_amount1);");
			//out.println("document.Form1.elements['BA_AMOUNT_'+j].value = format_noobject(m_rec_bal_amount1);");	
			out.println("document.Form1.elements['BA_AMOUNT_'+j].value = format_noobject(0.00);");
			
			out.println("m_invoice_count = parseFloat(unformat_noobject(document.Form1.elements['hid_invoice_count_'+j].value));");
			//out.println("alert('m_invoice_count'+m_invoice_count);");
			
			out.println("for(i=0;i<m_invoice_count;i++){");
			out.println("m_inv_bal = m_inv_bal + parseFloat(unformat_noobject(document.Form1.elements['BAL_AM_'+j+'_'+i].value));");
			out.println("	if(m_rec_tot<m_inv_bal){ ");
			out.println("	if(m_rec_bal>= (m_inv_bal-m_rec_tot)){ ");
			
			out.println(" m_alloamt = m_alloamt +(m_inv_bal-m_rec_tot);");
			//out.println("alert('111111111');");
			out.println("document.Form1.elements['Text_sett_amount'+j+'_'+i].value = format_noobject(m_inv_bal-m_rec_tot);");
			out.println("document.Form1.elements['Text_standard'+j+'_'+i].value =\"YES\";");	
			out.println("document.Form1.elements['Text_standard'+j+'_'+i].checked = true;"); 
			out.println("document.Form1.elements['Text_sett_amount'+j+'_'+i].disabled =true;");	 //added nuwan
		  out.println(" m_rec_bal = m_rec_bal-(m_inv_bal-m_rec_tot); ");
			out.println("	m_rec_tot = m_rec_tot +(m_inv_bal-m_rec_tot); ");  //CCCCCCCCCCCC
			
			out.println("}else{ ");
			out.println(" if(m_rec_bal>0){ "); 
			out.println(" m_alloamt = m_alloamt +m_rec_bal; ");
			//out.println("alert('2222222');");
			out.println("document.Form1.elements['Text_sett_amount'+j+'_'+i].value = format_noobject(m_rec_bal);");
			out.println("document.Form1.elements['Text_standard'+j+'_'+i].value =\"YES\";");	
			out.println("document.Form1.elements['Text_standard'+j+'_'+i].checked = true;"); 
			out.println("document.Form1.elements['Text_sett_amount'+j+'_'+i].disabled =true;");	 //added nuwan
      out.println(" m_rec_tot = m_rec_tot+m_rec_bal; ");
			out.println(" m_rec_bal = 0; ");
			
			out.println(" }else{ ");
			out.println(" m_alloamt = m_alloamt +m_rec_bal; ");
			//out.println("alert('333333');");
			out.println("document.Form1.elements['Text_sett_amount'+j+'_'+i].value = format_noobject(m_rec_bal);");
			out.println("document.Form1.elements['Text_standard'+j+'_'+i].value =\"NO\";");	
			out.println("document.Form1.elements['Text_standard'+j+'_'+i].checked = false;"); 
			out.println("document.Form1.elements['Text_sett_amount'+j+'_'+i].disabled =false;");	 //added nuwan
			
			out.println(" }	");
			out.println("	} ");
			out.println(" }else{ ");
	    //out.println("alert('44444444');");		
			out.println("document.Form1.elements['Text_sett_amount'+j+'_'+i].value = format_noobject(0.00);");
			//out.println("document.Form1.elements['Text_standard'+j+'_'+i].value =\"NO\";");	
			//out.println("document.Form1.elements['Text_standard'+j+'_'+i].checked = false;"); 
			out.println(" }	");
			
			
			//out.println("alert('m_inv_bal====='+m_inv_bal);");
			
			
			out.println("}");
			
			out.println("}");
			
			out.println("}");
		  
      //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	
			
			out.println("function get_alocate_amt() {");
			out.println("alert('test123');");
			
			out.println("m_rec_cnt    = parseFloat(unformat_noobject(document.Form1.hid_rec_count.value));");
			out.println("alert('m_rec_cnt'+m_rec_cnt);");
			out.println("for(j=0;j<m_rec_cnt;j++){");
			out.println("m_rec_alc_amount1 = parseFloat(unformat_noobject(document.Form1.elements['A_AMOUNT1_'+j].value));");
			out.println("m_rec_bal_amount1 = parseFloat(unformat_noobject(document.Form1.elements['BA_AMOUNT1_'+j].value));");
       
			out.println("m_rec_alc_amount = parseFloat(unformat_noobject(document.Form1.elements['A_AMOUNT_'+j].value));");
			out.println("m_rec_bal_amount = parseFloat(unformat_noobject(document.Form1.elements['BA_AMOUNT_'+j].value));");	
				
			out.println("document.Form1.elements['A_AMOUNT_'+j].value  = format_noobject(m_rec_alc_amount1);");
			out.println("document.Form1.elements['BA_AMOUNT_'+j].value = format_noobject(m_rec_bal_amount1);");	
					
			out.println("m_invoice_count = parseFloat(unformat_noobject(document.Form1.elements['hid_invoice_count_'+j].value));");
      
      out.println("var tot_aloc_amt =0;");
			
			out.println("for(i=0;i<m_invoice_count;i++){");
      
			
			out.println("m_bal_amount = parseFloat(unformat_noobject(document.Form1.elements['BAL_AM_'+j+'_'+i].value));");	
			//out.println("alert('amount-'+j+'-'+i+'-'+m_rec_alc_amount1+'-'+(m_rec_alc_amount1 - tot_aloc_amt)+'-'+m_bal_amount);"); 
			
			//out.println("if((m_rec_alc_amount1 - tot_aloc_amt) >= m_bal_amount){");
			//out.println("alert('m_rec_alc_amount1'+m_rec_alc_amount1+'tot_aloc_amt'+tot_aloc_amt+'m_bal_amount'+m_bal_amount);");

      		  	
			out.println(" if(m_bal_amount<=(m_rec_alc_amount1 - tot_aloc_amt)){");
			//out.println("alert('amount1-'+j+'-'+i);");	
			//11111111111111111111111111111111111111111111111111111111111111111111111111111
			out.println("if(j==0){");			
			out.println("document.Form1.elements['Text_sett_amount'+j+'_'+i].value = m_bal_amount;"); 
			out.println("}else{");
			
			out.println("if(document.Form1.elements['Text_sett_amount'+(j-1)+'_'+i].value == document.Form1.elements['BAL_AM_'+(j-1)+'_'+i].value){"); 
			out.println("document.Form1.elements['Text_sett_amount'+j+'_'+i].value = 0.00;");
			out.println("}else{");
			
			out.println("document.Form1.elements['Text_sett_amount'+j+'_'+i].value = m_bal_amount;");
			
			out.println("}");
			out.println("}");
			
			out.println(" tot_aloc_amt = tot_aloc_amt + m_bal_amount;");
			out.println("}else{");	
			out.println("alert('m_rec_alc_amount1'+m_rec_alc_amount1+'tot_aloc_amt'+tot_aloc_amt);");
			
			out.println("if(m_rec_alc_amount1 - tot_aloc_amt >0){");
			//out.println("alert('amount2-'+j+'-'+i);");
		  //22222222222222222222222222222222222222222222222222222222222222222222222222222222	
					
			out.println("document.Form1.elements['Text_sett_amount'+j+'_'+i].value = (m_rec_alc_amount1 - tot_aloc_amt);");
			out.println(" tot_aloc_amt = m_rec_alc_amount1;");
			out.println("}else{");
			//out.println("alert('amount3-'+j+'-'+i);");
			//333333333333333333333333333333333333333333333333333333333333333333333333333333333
			out.println("document.Form1.elements['Text_sett_amount'+j+'_'+i].value = 0.00;");
			out.println(" tot_aloc_amt = m_rec_alc_amount1;");
			out.println("}");
			out.println("}");
			//out.println("alert('m_bal_amount'+m_bal_amount);"); 
			//out.println("alert('m_rec_bal_amount'+m_rec_bal_amount);");
			out.println("}");
			
			out.println("}");
			out.println("}");
			
			
			out.println("function show_inv_det() {");
			out.println(" get_Receipt();");
			//out.println(" get_alocate_amt();");
			out.println("}");
			
			out.println("function remv_inv_det() {");
			out.println("rec.innerHTML=\"\";");
			out.println("}");
			
			out.println("function get_alocate_amt1() {");
			out.println("m_rec_cnt    = parseFloat(unformat_noobject(document.Form1.hid_rec_count.value));");
			out.println("alert('m_rec_cnt'+m_rec_cnt);");
			out.println("for(j=0;j<m_rec_cnt;j++){");
			out.println("m_rec_alc_amount1 = parseFloat(unformat_noobject(document.Form1.elements['A_AMOUNT1_'+j].value));");
			out.println("m_rec_bal_amount1 = parseFloat(unformat_noobject(document.Form1.elements['BA_AMOUNT1_'+j].value));");
       
			out.println("m_rec_alc_amount = parseFloat(unformat_noobject(document.Form1.elements['A_AMOUNT_'+j].value));");
			out.println("m_rec_bal_amount = parseFloat(unformat_noobject(document.Form1.elements['BA_AMOUNT_'+j].value));");	
				
			out.println("document.Form1.elements['A_AMOUNT_'+j].value  = format_noobject(m_rec_alc_amount1);");
			out.println("document.Form1.elements['BA_AMOUNT_'+j].value = format_noobject(m_rec_bal_amount1);");	
					
			out.println("m_invoice_count = parseFloat(unformat_noobject(document.Form1.elements['hid_invoice_count_'+j].value));");
      
      out.println("var tot_aloc_amt =0;");
			
			out.println("for(i=0;i<m_invoice_count;i++){");
      
			
			out.println("m_bal_amount = parseFloat(unformat_noobject(document.Form1.elements['BAL_AM_'+j+'_'+i].value));");	
			//out.println("alert('amount-'+j+'-'+i+'-'+m_rec_alc_amount1+'-'+(m_rec_alc_amount1 - tot_aloc_amt)+'-'+m_bal_amount);"); 
			
			//out.println("if((m_rec_alc_amount1 - tot_aloc_amt) >= m_bal_amount){");
			//out.println("alert('m_rec_alc_amount1'+m_rec_alc_amount1+'tot_aloc_amt'+tot_aloc_amt+'m_bal_amount'+m_bal_amount);");

      		  	
			out.println(" if(m_bal_amount<=(m_rec_alc_amount1 - tot_aloc_amt)){");
			//out.println("alert('amount1-'+j+'-'+i);");	
			//11111111111111111111111111111111111111111111111111111111111111111111111111111
			out.println("if(j==0){");			
			out.println("document.Form1.elements['Text_sett_amount'+j+'_'+i].value = m_bal_amount;"); 
			out.println("}else{");
			
			out.println("if(document.Form1.elements['Text_sett_amount'+(j-1)+'_'+i].value == document.Form1.elements['BAL_AM_'+(j-1)+'_'+i].value){"); 
			out.println("document.Form1.elements['Text_sett_amount'+j+'_'+i].value = 0.00;");
			out.println("}else{");
			
			out.println("document.Form1.elements['Text_sett_amount'+j+'_'+i].value = m_bal_amount;");
			
			out.println("}");
			out.println("}");
			
			out.println(" tot_aloc_amt = tot_aloc_amt + m_bal_amount;");
			out.println("}else{");	
			out.println("alert('m_rec_alc_amount1'+m_rec_alc_amount1+'tot_aloc_amt'+tot_aloc_amt);");
			
			out.println("if(m_rec_alc_amount1 - tot_aloc_amt >0){");
			//out.println("alert('amount2-'+j+'-'+i);");
		  //22222222222222222222222222222222222222222222222222222222222222222222222222222222	
			out.println("if(j==0){");			
			out.println("document.Form1.elements['Text_sett_amount'+j+'_'+i].value = (m_rec_alc_amount1 - tot_aloc_amt);");
			out.println("}else{");
			
			out.println("if(document.Form1.elements['Text_sett_amount'+(j-1)+'_'+i].value == document.Form1.elements['BAL_AM_'+(j-1)+'_'+i].value){"); 
			out.println("document.Form1.elements['Text_sett_amount'+j+'_'+i].value = 0.00;");
			out.println("}else{");
			
			out.println("document.Form1.elements['Text_sett_amount'+j+'_'+i].value = (m_rec_alc_amount1 - tot_aloc_amt);");
			
			out.println("}");
			out.println("}");

			
			
			
			
			
			
			
			
			out.println(" tot_aloc_amt = m_rec_alc_amount1;");
			out.println("}else{");
			//out.println("alert('amount3-'+j+'-'+i);");
			//333333333333333333333333333333333333333333333333333333333333333333333333333333333
			out.println("document.Form1.elements['Text_sett_amount'+j+'_'+i].value = 0.00;");
			out.println(" tot_aloc_amt = m_rec_alc_amount1;");
			out.println("}");
			out.println("}");
			//out.println("alert('m_bal_amount'+m_bal_amount);"); 
			//out.println("alert('m_rec_bal_amount'+m_rec_bal_amount);");
			out.println("}");
			
			out.println("}");
			out.println("}");
			
			
			
			out.println("function Change_Allo_Mode(val){ ");
						
			out.println(" if(val==\"FIFO_MANU\"){"); 
			out.println("   rec_alc.innerHTML=\"\"; ");
			//out.println("   auto_allo.innerHTML=\"\"; ");
			out.println("  get_Receipt_Allocation();");
			out.println("} else {");
			out.println("  rec_alc.innerHTML =\"\"; ");
			out.println("  rec.innerHTML     =\"\"; ");
					
			out.println("  get_manual_alocation();");
					
			out.println("}");
			
			out.println("}");
			
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_out_value();load_lock();\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input type='hidden' name='Hid_scr_name' value='AF_RECEIPT_ALLO_UNALLO' > ");
			out.println("<input type='hidden' name='TXT_SCREEN_NAME' value='AF_RECEIPT_ALLO_UNALLO' > ");
			out.println("<INPUT TYPE='Hidden' NAME='hid_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"NEW\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_win_type' VALUE=\"Main\">"); 			
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"NEW\">");
			out.println("<input type=hidden name=\"tot_val\" value=\"0\">");
			out.println("<input type=hidden name=\"hid_opt_val\" value=\"0\">");
			out.println("<input type=hidden name=\"hid_win_opt\" value=\"0\">");
			out.println("<input type=hidden name=\"hid_del_row_no\" value=\"0\">");
			
				
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
				out.println("<td><input type=button name=reset value=\"New\" class=mainbut onclick=load_screen_status(\"NEW\"); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");//document.Form1.OPTION_DESC.value
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=edit value=\"Delete\" class=mainbut onclick=load_screen_status(\"DELETE\"); onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td width=10%>&nbsp;</td>");
				out.println("<td><input class='mainbut' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled>  </td>"); 
			  out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=delete value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel value=\"Re-active\" class=mainbut onclick=befor_active(); onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=b_submit value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Reset\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=allo value=\"Invoice Allocation\" class=mainbut style='width: 130px' onclick=befor_allo(); onMouseOver='load_roll_value(\"Invoice Allocation\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");// Modified by Thamali Jayatunga on 2009.10.27 // commented by udara 03-06-2024
				out.println("<td>&nbsp;</td>"); // released by udara 03-06-2024
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
			    out.println("<tr class=tr_input>");
				out.println("<td id=fod width=\"20%\">Receipt No</td>");
				out.println("<td width=\"30%\"><input name=\"RECEPT_NO\" type=\"text\" maxlength=\"15\" class=\"txt_input\" onchange=check_receipt()> ");
				out.println("<input type=button name=rec_help value=... class=\"but_input\" onclick=\"receipt_help()\" disabled></td>");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td id=tod width=\"20%\"></td>");
				out.println("<td width=\"30%\"> ");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td id=fod>Client Code</td>");
				out.println("<td><input name=\"CLIENT_CODE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" onblur=check_client()> ");
				out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"client_help()\"></td>");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td id=tod>Client Name</td>");
				out.println("<td> <input name=\"CLIENT_NAME\" type=\"text\" maxlength=\"200\" class=\"txt_input\" disabled style=\"width:300px;\">");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");		

				out.println("<tr class=tr_input>");
				out.println("<td id=lno>Finance No</td>");
				//out.println("<td><input name=\"LEASE_NO\" type=\"text\" maxlength=\"15\" class=\"txt_input\" onclick=\"lease_help()\" > "); //onchange=check_lease()
				out.println("<td><input name=\"LEASE_NO\" type=\"text\" maxlength=\"20\" class=\"txt_input\" onclick=\"lease_help()\" > ");
				out.println("<input type=button name=lea_help value=... class=\"but_input\" onclick=\"lease_help()\" >");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td id=vno>Vehicle No</td>");
				out.println("<td> <input name=\"VEHICLE_NO\" type=\"text\" maxlength=\"7\" class=\"txt_input\" disabled onchange=check_vehicle()> ");
				out.println("<input type=button name=veh_help value=... class=\"but_input\" onclick=\"vehicle_help()\" >");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				//out.println("</table>");
				
				out.println("<tr></tr>");
				
				//out.println("<table align='center'  width='100%' class='table'>"); //Added by Chandana on 19/10/2007				
			  out.println("<tr class=tr_input>"); //Added by Chandana on 19/10/2007				
				out.println("<td valign='top'>Allocation Method</td>");
				out.println("<td ><SELECT name=\"TXT_FIFO\" class=\"txt_input\" onChange=\"\" disabled>"); 
				out.println("<OPTION value=\"FIFO_AUTO\" >&nbsp&nbsp  Auto Allocation</OPTION>");
				out.println("<OPTION value=\"FIFO_MANU\" SELECTED>&nbsp&nbsp  Manual Allocation</OPTION>");
				out.println("</SELECT>");
				out.println("<input type=button name=Allocate value=Allocate class=\"but_input\" style=\"width:90px;\" onclick=\"Change_Allo_Mode(document.Form1.TXT_FIFO.value)\">");
				out.println("</td>");
				out.println("<td width=\"*%\"></td>");
				out.println("<td width=\"*%\"></td>");
				out.println("</tr>");
				out.println("</table>");
				
				
				
				
				
				
				out.println("</td>");
				out.println("</tr>");
				
				//Added by Chandana on 20/09/2007
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<div id=rec_alc><input type=hidden name=hid_count1 value=0>");
				out.println("</td>");
				out.println("</tr>");
				//End
				
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<div id=rec><input type=hidden name=hid_count value=0>");
				out.println("</td>");
				out.println("</tr>");  
				
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<div id=auto_allo><input type=hidden name=hid_count value=0>");
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
				out.println("<tr>");
				out.println("<td style=\"width: 6px\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td width=10%>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset1 value=\"New\" class=mainbut onclick=load_screen_status(\"NEW\"); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");//document.Form1.OPTION_DESC.value
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=edit1 value=\"Delete\" class=mainbut onclick=load_screen_status(\"DELETE\"); onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td width=10%>&nbsp;</td>");
				out.println("<td><input class='mainbut' type='button' name='BUT_HELP_MAIN1' value=\"Help\" onClick=\"help_update()\" disabled>  </td>"); 
			  out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=delete value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel value=\"Re-active\" class=mainbut onclick=befor_active(); onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back1 value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=b_submit1 value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset1 value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Reset\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td ><input type=button name=cal value=\"Calculate\" class=mainbut onclick=befor_cal();></td>");
      
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
			/*
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			if(m_Followu_no==null){
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"NEW\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_win_type' VALUE=\"Main\">"); 			
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"NEW\">");
				
      }else{
			 	out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"Edit\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"Edit\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_win_type' VALUE=\"Window\">"); 
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"EDIT\">");
			}
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>"+header_name+"</td>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Followup</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			if(m_Followu_no==null){
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\");ena_text()' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\");dis_text()' value=\"Edit\"></td>");  
			}else{
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			
			}
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
			out.println("<tr class='tr_input'>");  
			out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
			out.println("</tr>");  
			out.println("</table>");  

			out.println("<table align='center' width='100%' class='table'>"); 

			out.println("<tr >"); 
			//out.println("<td width='1%'></td>"); 
			out.println("<td width='20%' ID=FNO>Followup No *</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FOLLOW_UP_NO' maxlength='16' size='16' onblur=\"makeRequest(document.Form1.TXT_FOLLOW_UP_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled>  <input class='but_input' type='button' name='BUT_HELP_HIS' value=\"History\" onClick=\"load_history(document.Form1.TXT_FOLLOW_UP_NO.value)\" ></td>"); 
			//out.println("<td width='*%'></td>"); 
		
			out.println("<td width='20%' ID=INO>ID No</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_ID_NO' maxlength='15' size='15'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_ID_NO' value=\"Help\" onClick=\"help_button_1()\"></td>"); 
			//out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			//out.println("<td width='1%'></td>"); 
			out.println("<td >Assigned By </td>"); 
			out.println("<td ><input class='txt_input' type='text' name='TXT_ACTION_SET_FOR' maxlength='10' size='10'></td>"); 
			out.println("<td >Action Assing To</td>"); 
			out.println("<td ><input class='txt_input' type='text' name='TXT_ACTION_ASS_TO' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_USER_NO' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
			//out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			//out.println("<td width='1%'></td>"); 
			//out.println("<td width='*%'></td>"); 
	
			out.println("<td ID=TDA>Action Target Date *</td>"); 
			if(m_Followu_no==null){
			out.println("<td ><input class='txt_input' type='text' name='TXT_EFF_VAL_DATE' maxlength='10' size='10'><a href style='{cursor:hand; }' onclick=\"load_calendar('1')\">   Calendar</a></td>"); 
			}else{
			out.println("<td ><input class='txt_input' type='text' name='TXT_EFF_VAL_DATE' maxlength='10' size='10'></td>"); 
			}
			out.println("<td ID=AAD>Action Actual Date *</td>"); 
			out.println("<td ><input class='txt_input' type='text' name='TXT_ACTION_TOOK_DATE' maxlength='10' size='10'><a href style='{cursor:hand; }' onclick=\"load_calendar('2')\">   Calendar</a></td>"); 
			//out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			//out.println("<td width='1%'></td>"); 
			out.println("<td ><DIV id='ATBT'  class=div_input>Action To Be Taken *</DIV></td>"); 
			out.println("<td ><SELECT class='txt_input' name='TXT_ACTION_TOBE_TAKEN'>"); 
			rs = stmt.executeQuery(CO_methods.getFollowupCat(m_schema_name,"Y",""));
      boolean	more = rs.next();
			while(more){
         out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
				 more = rs.next();	
			}	
			out.println("</SELECT>");
			out.println("</td>");
			//out.println("<td ><input class='txt_input' type='text' name='TXT_ACTION_TOBE_TAKEN' maxlength='10' size='10'></td>"); 
			//out.println("<td width='*%'></td>"); 
		
			out.println("<td ID=AT>Action Taken *</td>"); 
			out.println("<td ><SELECT class='txt_input' name='TXT_ACTION_TOOK'>"); 
			rs = stmt.executeQuery(CO_methods.getFollowupCat(m_schema_name,"Y",""));
     	more = rs.next();
			while(more){
         out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
				 more = rs.next();	
			}	
			out.println("</SELECT>");
			out.println("</td>");//out.println("<td ><input class='txt_input' type='text' name='TXT_ACTION_TOOK' maxlength='10' size='10'></td>"); 
			//out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			//out.println("<td width='1%'></td>"); 
			//out.println("<td width='*%'></td>"); 
			out.println("<td ID=EUR>Enterd User Remarks *</td>"); 
			out.println("<td ><input class='txt_input' type='text' name='TXT_ENT_REMARKS' maxlength='200' size='200'></td>"); 
			out.println("<td ID=UR>Remarks *</td>"); 
			out.println("<td ><input class='txt_input' type='text' name='TXT_REMARKS' maxlength='200' size='200'></td>"); 
			//out.println("<td width='*%'></td>");
			//out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td >Status</td>"); 
			out.println("<td ><select name='TXT_STATUS' class='txt_input' onchange=load_div()>");
			out.println("<option value=COMPLETED >Completed  </option>");
			out.println("<option value=INPROGRESS>In progress</option>");
			out.println("</select>");
			out.println("</td>"); //<input class='txt_input' type='text' name='TXT_STATUS' >
			out.println("<td id=tnextd></td>"); 
			out.println("<td id=bnextd></td>"); 
			out.println("</tr>");
			
			
			//out.println("<tr >"); 
			//out.println("<td width='1%'></td>"); 
			//out.println("<td >Next  Action Date *</td>"); 
			//out.println("<td ><input class='txt_input' type='text' name='TXT_ACTION_SET_FOR' maxlength='10' size='10'></td>"); 
			
			//out.println("<td ></td>"); 
			//out.println("<td ></td>"); 
			//out.println("<td width='*%'></td>"); 
			
			//out.println("</tr>");
		
			out.println("</table>"); 
			out.println("<br>"); 
			
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
			out.println("<tr class='tr_input'>");  
			out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
			out.println("</tr>"); 
			out.println("<tr class='tr_input'>");  
			out.println("<td width='100%' id=load_followup></td>");  
			out.println("</tr>"); 
			out.println("</table>");  

			
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); */
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
		
      } 
			
	    	else if(m_chksql.trim().equals("get_Invoice")){
			
			    String m_client      = req.getParameter("client");
          
					rs = stmt.executeQuery ("SELECT INVOICE_NO,FINANCE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY'),"+
					                        "       TOTAL_AMOUNT,SETTELE_AMOUNT, "+
																	"       BALANCE_TO_BE_RECEIVED,VAT_AMOUNT, "+
																	"       DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																	"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																	"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																	"       INVOICE_TYPE "+
																	"  FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+
																	"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																	"  WHERE CLIENT_CODE='"+m_client+"' AND "+
																	"        BALANCE_TO_BE_RECEIVED>0 AND "+
																	"        A.INVOICE_TYPE = B.INVOICE_TYPE_CODE AND "+
                                  "	       A.ACTIVE_STATUS = 'Y' "+
																	"	ORDER BY ORDER_NO,VALUE_DATE	");

		
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>Receipt Allocate / Unallocate</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">"); 
					
					out.println("function check_status(num,num2) {");
				  //out.println("alert(document.Form1.elements['Text_standard'+num].checked);");
					out.println("if(document.Form1.elements['Text_standard'+num+'_'+num2].checked){");
					out.println(" document.Form1.elements['Text_standard'+num+'_'+num2].value=\"YES\";");
					out.println(" document.Form1.elements['Text_sett_amount'+num+'_'+num2].disabled=true;");
					//out.println(" window.opener.cal_amount('add',unformat_noobject(document.Form1.tot_val.value),unformat_noobject(document.Form1.elements['Text_sett_amount'+num+'_'+num2].value),num,document.Form1.elements['INV_NO_'+num+'_'+num2].value,num2);");
					out.println("}else{");
					out.println(" document.Form1.elements['Text_standard'+num+'_'+num2].value=\"NO\";");
					out.println(" document.Form1.elements['Text_sett_amount'+num+'_'+num2].disabled=false;");
					//out.println(" window.opener.cal_amount('min',unformat_noobject(document.Form1.tot_val.value),unformat_noobject(document.Form1.elements['Text_sett_amount'+num+'_'+num2].value),num,document.Form1.elements['INV_NO_'+num+'_'+num2].value,num2);");
					out.println("}");
					out.println("}");
					
					
				
					
					out.println("function check_amount(num,num2) {");
						//out.println("alert(document.Form1.elements['Text_standard'+num].checked);");
					out.println("if(Number(document.Form1.elements['Text_sett_amount'+num+'_'+num2].value)>Number(document.Form1.elements['Hid_amount'+num].value)){");
					out.println(" alert('Amount cannot be greater than Net Amount');");
					out.println(" document.Form1.elements['Text_sett_amount'+num+'_'+num2].value = document.Form1.elements['Hid_amount'+num].value;");
					out.println("}");
			    out.println("}");
					
					out.println("function load_data_main(num) {");
					//out.println("alert(document.Form1.elements['Text_standard'+num].checked);");
					out.println("m_row='<table><tr class=pdn_txtpos2 WIDTH=100%>'+");
					out.println("      '<td WIDTH=20%>Invoice No</td>'+");
					out.println("      '<td WIDTH=10%>Date</td>'+");
					out.println("      '<td WIDTH=10%>Value Date</td>'+");
					out.println("      '<td WIDTH=15%>Invoice Amount</td>'+");
					out.println("      '<td WIDTH=15%>Balance Amount</td>'+");
					out.println("      '<td WIDTH=15%>Allocated Amount</td></tr>';");
					out.println("x=0;");
					out.println("H=window.opener.document.Form1.hid_opt_val.value;");
					out.println("for(i=0;i<Number(document.Form1.elements['hid_inv_count'].value);i++){");
					//out.println(" alert(document.Form1.elements['Text_standard'+i].checked);");
					out.println(" if(document.Form1.elements['Text_standard'+i].checked){");
					out.println("  m_row = m_row +'<tr><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_'+H+'_'+x+'\">'+");
					out.println("          '<td align=left ><input type=text name=\"INV_NO_'+H+'_'+x+'\" value=\"'+document.Form1.elements[\"INV_NO_\"+i].value+'\" class=\"txt_input2\" ></td>'+");
					out.println("          '<td align=left ><input type=text name=\"V_DATE_'+H+'_'+x+'\" value=\"'+document.Form1.elements[\"V_DATE_\"+i].value+'\" class=\"txt_input2\" ></td>'+");
					out.println("          '<td align=left ><input type=text name=\"D_DATE_'+H+'_'+x+'\" value=\"'+document.Form1.elements[\"D_DATE_\"+i].value+'\" class=\"txt_input2\" ></td>'+");
					out.println("          '<td align=right><input type=text name=\"INV_AM_'+H+'_'+x+'\" value=\"'+document.Form1.elements[\"AMOUNT_\"+i].value+'\" class=\"txt_input2\" ></td>'+");
					out.println("          '<td align=right><input type=text name=\"BAL_AM_'+H+'_'+x+'\" value=\"'+document.Form1.elements[\"BAL_AM_\"+i].value+'\" class=\"txt_input2\" ></td>'+");
					out.println("          '<td align=right><input type=text name=\"Text_sett_amount'+H+'_'+x+'\" value=\"'+document.Form1.elements[\"Text_sett_amount\"+i].value+'\" class=\"txt_input2\" ></td>'+");
					out.println("          '</tr>';");
					out.println("   x=x+1;");
					out.println(" }");
					out.println(" }");
					out.println(" m_row = m_row +'<input type=hidden name=hid_invoice_count_'+H+'  value='+x+'></table>';");
					//out.println(" alert('m_row ='+m_row);");
					out.println(" window.opener.document.getElementById(\"inv_\"+H).innerHTML = m_row;");
					//out.println(" window.opener.document.elements[\"hid_inv_count\"+H].value = x;");
					out.println(" window.close();");
			    out.println("}");
          
					out.println("</script>"); 
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\">"); //load_roll_out_value();load_lock();
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<input type=hidden name=\"tot_val\" value=\"0\"></td>");
									
					out.println("<table class=table border='0' width='100%' >");
					
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='15%' >Invoice No</td>");
					out.println("<td  width='15%' >Finance No</td>");
          out.println("<td  width='10%' >Value Date</td>");
					out.println("<td  width='13%' >Amount</td>");
					out.println("<td  width='13%' >Setteled Amount</td>");
					out.println("<td  width='13%' >Balance Amount</td>");
					out.println("<td  width='13%' >Amount Allocat</td>");
					out.println("<td  width='8%' ></td>");
					out.println("</tr>");
      
           int j = 0;      					
							
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
									out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td >"+rs.getString(1) +"<input type=hidden name=\"INV_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td >"+rs.getString(2) +"<input type=hidden name=\"FIN_NO_"+j+"\" value=\""+rs.getString(2)+"\"></td>"); 
                  out.println("<td >"+rs.getString(3) +"<input type=hidden name=\"V_DATE_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"SETT_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"BAL_AM_"+j+"\" value=\""+(rs.getString(6))+"\">");
									//out.println("  <input type=hidden NAME=\"hid_invoice_type"+j+"_"+i+"\"  value=\""+rs1.getString(17)+"\" ");
  								out.println("</td>");
									out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"\" value=\""+nf.format(rs.getDouble(6))+"\" class=\"txt_input2\" ></td>");
									
                  out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
									out.println("     </td>");
									out.println("</tr>");
                	j=j+1;
									
									//if(rs.getString(6).equals(m_username)){
									//  out.println("<td ><input type=button name=\"Edit_"+j+"\" value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"EDIT\"); >");
									//	out.println("<input type=button name=\"Dele_"+j+"\" value=\"Del\" class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"DELETE\"); ></td>");
									//}else{
									//}                  
              }
          //}
          
					        out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td colspan=5><input type=hidden name=hid_inv_count value="+j+"></td>");
                  //out.println("<td ></td>");
                  out.println("<td id=total></td>");
                  out.println("<td ><input type=button name=\"Proceed\" value=\"Go\"   class=mainbut1 onclick=load_data_main(); ></td>");
									out.println("</tr>");
					
          out.println("</table>");

				  out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</body>"); 
					out.println("</html>"); 
		
      } 	
			
			
			else if(m_chksql.trim().equals("get_Receipt_Allocation_2")){
			
			String m_client      = req.getParameter("client");
			String m_lea_no      = req.getParameter("lea_no");
			String m_vih_no      = req.getParameter("vih_no");
			
							rs = stmt.executeQuery (" SELECT "+
				    		" A.REC_NO,   "+           //1
								" A.REC_AMOUNT ,"+         //2
								" A.APP_REC_AMOUNT,   "+   //3
								" A.BAL_TOBE_RECEIVE,   "+ //4
								" A.ALLOCATED_AMOUNT  , "+ //5
								" A.FINANCE_NO  "+         //6
								" FROM "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B "+
								" WHERE  A.REC_NO=B.REC_NO AND A.CLIENT_CODE='"+m_client+"'  AND A.FINANCE_NO LIKE '"+m_lea_no+"%' AND A.BAL_TOBE_RECEIVE > 0 "+
								" AND B.STATUS NOT IN ('CAD','RET','C') "+
								//" ORDER BY EFF_VALDATE "+ // commented by udara 19-11-2013
								" ORDER BY B.ENT_DATE ASC "+  // added by udara 19-11-2013
								"");

			
			
				/*rs = stmt.executeQuery (" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
																	"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE, "+
																	"	       A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK, "+
																	"	       EXCHANGE_RATE_REP_CURR,REC_AMOUNT_REP_CURR, "+
																	"	       EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+ 
																	"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
																	"	WHERE  A.REC_NO = B.REC_NO AND STATUS NOT IN ('CAD','RET','C') AND "+//<>'C' AND "+
																	"	       BAL_TOBE_RECEIVE>0 AND CLIENT_CODE = '"+m_client+"' ORDER BY A.EFF_VALDATE  ");
																	
				*/
				
								

					
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          //out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' >Finance No</td>");
					out.println("<td width='15%' >Receipt No</td>");
					out.println("<td width='10%' align=center>Receipt Amount</td>");
					out.println("<td width='10%' align=center>Rec.Amount App</td>");
          out.println("<td width='10%' align=center>Settled Amount</td>");
					out.println("<td width='10%' align=center>Balance Amount</td>");
					out.println("<td width='15%' align=center>Allocated Amount</td>");
					out.println("<td width='15%' align=center>Balance to Allocate</td>");
					//out.println("<td  width='35%' align=right>Amount</td>");
					//out.println("<td  width='10%'  ></td>");
					out.println("</tr>");
      
           int j = 0;      					
					 //Vector m_amount  = new Vector();
					 //Vector m_amount1 = new Vector();	
					 double m_rec_tot = 0;
					 double m_rec_bal = 0;
					
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input1 /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td style= cursor:hand; align=center; onClick=\"show_finance_drill('"+rs.getString(6)+"')\" ><u>"+rs.getString(6) +"</u><input type=hidden name=\"FINANCE_NO_"+j+"\" value=\""+rs.getString(6)+"\"></td>");
									out.println("<td style= cursor:hand; align=center; onClick=\"show_settle_receipt_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u><input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>"); //modify nuwan de silva 18-07-07
                  out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT1_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
									out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"REC_AMOUNT_APP1_"+j+"\" value=\""+rs.getString(3)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(5)) +"<input type=hidden name=\"ALLO_AMOUN1_"+j+"\" value=\""+rs.getString(5)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT1_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right><input type=text name=\"A_AMOUNT1_"+j+"\" value=\"0\" class=\"txt_input2\" onBlur=\"chk_bal('"+j+"'),format_number(document.Form1.A_AMOUNT1_"+j+",'30')\"></td>");
                  out.println("<td align=right><input type=text name=\"BA_AMOUNT1_"+j+"\" value=\""+nf.format(rs.getDouble(4))+"\" class=\"txt_input2\" disabled><input type=hidden name=\"row_id"+j+"\" value=\""+j+"\" ></td>");
                  //out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled>");
									//out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
                  //out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
									//out.println("     </td>");
									out.println("</tr>");
									j+=1;
                 }


		  	out.println("<tr><input type=hidden name=hid_rec_count value="+j+"></tr></table>");
			
			  out.println(" <br><hr><br>");	
				out.println(" <table border='0'><tr class=pdn_txtpos2 WIDTH=100%>");
				out.println(" <td WIDTH=80%>&nbsp</td>");
				out.println(" <td WIDTH=25%><input type=button name=show_inv value=\"Allocate to Invoices\" class=mainbut onclick=show_inv_det(); style='width: 130px'></td>"); //show_inv_det();
				out.println(" <td WIDTH=25%><input type=button name=remove_inv value=\"Remove Invoices\" class=mainbut onclick=remv_inv_det(); style='width: 130px'</td>");	//remv_inv_det();			
				out.println(" </tr></table>");		
				out.println(" <br><hr>");	
						
			 }
			
			
			else if(m_chksql.trim().equals("get_Receipt_Allocation")){
			
			String m_client      = req.getParameter("client");
			String m_lea_no      = req.getParameter("lea_no");
			String m_vih_no      = req.getParameter("vih_no");
			
			
				rs = stmt.executeQuery (" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
																	"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE, "+
																	"	       A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK, "+
																	"	       EXCHANGE_RATE_REP_CURR,REC_AMOUNT_REP_CURR, "+
																	"	       EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+ 
																	"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
																	"	WHERE  A.REC_NO = B.REC_NO AND STATUS NOT IN ('CAD','RET','C') AND "+//<>'C' AND "+
																	"	       BAL_TOBE_RECEIVE>0 AND CLIENT_CODE = '"+m_client+"' "+
																	//" ORDER BY A.EFF_VALDATE  "+ // commented by udara 19-11-2013
																	" ORDER BY A.ENT_DATE ASC  "+ // added by udara 19-11-2013
																	" ");
																	
				
				
								

					
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          //out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' >Receipt No</td>");
					out.println("<td width='15%' align=center>Receipt Amount</td>");
          out.println("<td width='15%' align=center>Settled Amount</td>");
					out.println("<td width='20%' align=center>Balance Amount</td>");
					out.println("<td width='15%' align=center>Allocated Amount</td>");
					out.println("<td width='15%' align=center>Balance to Allocate</td>");
					//out.println("<td  width='35%' align=right>Amount</td>");
					//out.println("<td  width='10%'  ></td>");
					out.println("</tr>");
      
           int j = 0;      					
					 //Vector m_amount  = new Vector();
					 //Vector m_amount1 = new Vector();	
					 double m_rec_tot = 0;
					 double m_rec_bal = 0;
					
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input1 /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td style= cursor:hand; align=center; onClick=\"show_settle_receipt_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u><input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>"); //modify nuwan de silva 18-07-07
                  out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT1_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"ALLO_AMOUN1_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT1_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right><input type=text name=\"A_AMOUNT1_"+j+"\" value=\"0\" class=\"txt_input2\" onBlur=\"chk_bal('"+j+"'),format_number(document.Form1.A_AMOUNT1_"+j+",'30')\"></td>");
                  out.println("<td align=right><input type=text name=\"BA_AMOUNT1_"+j+"\" value=\""+nf.format(rs.getDouble(4))+"\" class=\"txt_input2\" disabled><input type=hidden name=\"row_id"+j+"\" value=\""+j+"\" ></td>");
                  //out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled>");
									//out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
                  //out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
									//out.println("     </td>");
									out.println("</tr>");
									j+=1;
                 }


		  	out.println("<tr><input type=hidden name=hid_rec_count value="+j+"></tr></table>");
			
			  out.println(" <br><hr><br>");	
				out.println(" <table border='0'><tr class=pdn_txtpos2 WIDTH=100%>");
				out.println(" <td WIDTH=80%>&nbsp</td>");
				out.println(" <td WIDTH=25%><input type=button name=show_inv value=\"Allocate to Invoices\" class=mainbut onclick=show_inv_det(); style='width: 130px'></td>"); //show_inv_det();
				out.println(" <td WIDTH=25%><input type=button name=remove_inv value=\"Remove Invoices\" class=mainbut onclick=remv_inv_det(); style='width: 130px'</td>");	//remv_inv_det();			
				out.println(" </tr></table>");		
				out.println(" <br><hr>");	
						
			 }
				
					else if(m_chksql.trim().equals("get_Invoice_alocation_2")){
			
			    String m_client      = req.getParameter("client").trim();
					String m_lea_no      = req.getParameter("lea_no").trim();
					
          
				/*	rs = stmt.executeQuery (" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
																	"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE, "+
																	"	       A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK, "+
																	"	       EXCHANGE_RATE_REP_CURR,REC_AMOUNT_REP_CURR, "+
																	"	       EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+ 
																	"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
																	"	WHERE  A.REC_NO = B.REC_NO AND STATUS NOT IN ('CAD','RET','C') AND "+//<>'C' AND "+
																	"	       BAL_TOBE_RECEIVE>0 AND CLIENT_CODE = '"+m_client+"' ");
        */
				
				
						rs = stmt.executeQuery (" SELECT "+
																	" A.REC_NO,   "+           //1
																	" A.REC_AMOUNT ,"+         //2
																	" A.APP_REC_AMOUNT,   "+   //3
																	" A.BAL_TOBE_RECEIVE,   "+ //4
																	" A.ALLOCATED_AMOUNT  , "+ //5
																	" A.FINANCE_NO  "+         //6
																	" FROM "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  B "+
																	" WHERE  A.REC_NO=B.REC_NO AND A.CLIENT_CODE='"+m_client+"' AND A.FINANCE_NO LIKE '"+m_lea_no+"%'  AND A.BAL_TOBE_RECEIVE > 0  "+
																	" AND B.STATUS NOT IN ('CAD','RET','C')  "+
																	//" ORDER BY EFF_VALDATE "+ // commented by udara 19-11-2013
																	" ORDER BY B.ENT_DATE ASC "+ // added by udara 19-11-2013
																	"");



					
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' >Finance No</td>");
					out.println("<td width='15%' >Receipt No</td>");
					out.println("<td width='10%' align=center>Receipt Amount</td>");
					out.println("<td width='10%' align=center>Rec.Amount App</td>");
          out.println("<td width='10%' align=center>Settled Amount</td>");
					out.println("<td width='10%' align=center>Balance Amount</td>");
					out.println("<td width='15%' align=center>Allocated Amount</td>");
					out.println("<td width='15%' align=center>Balance to Allocate</td>");
					out.println("</tr>");
      
           int j = 0;      					
					 //Vector m_amount  = new Vector();
					 //Vector m_amount1 = new Vector();	
					 double m_rec_tot = 0;
					 double m_rec_bal = 0;
						
						      
					
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input1 /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td style= cursor:hand; align=center; onClick=\"show_finance_drill('"+rs.getString(6)+"')\" ><u>"+rs.getString(6) +"</u><input type=hidden name=\"FINANCE_NO_"+j+"\" value=\""+rs.getString(6)+"\"></td>");
									out.println("<td style= cursor:hand; onClick=\"show_settle_receipt_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u><input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>"); //modify nuwan de silva 18-07-07
                  out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
									out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"REC_AMOUNT_APP_"+j+"\" value=\""+rs.getString(3)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(5)) +"<input type=hidden name=\"ALLO_AMOUN_"+j+"\" value=\""+rs.getString(5)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right><input type=text name=\"A_AMOUNT_"+j+"\" value=\"0\" class=\"txt_input2\" disabled ></td>");
                  out.println("<td align=right><input type=text name=\"BA_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(4))+"\" class=\"txt_input2\" disabled ></td>");
                  //out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled>");
									//out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
                  //out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
									//out.println("     </td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									//out.println("<td></TD>");
									
									out.println("<td colspan=8 ><div id='inv_"+j+"'>");
								  //m_rec_tot = m_rec_tot +rs.getDouble(4);
									m_rec_bal   = rs.getDouble(4);
									m_lea_no    = rs.getString(6);
									if(m_lea_no.equals("")){
									 																					
									 rs1 = stmt1.executeQuery(
									 //out.println(
									 "SELECT INVOICE_NO,VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       TO_CHAR(DUE_DATE,'DD-MM-YYYY'),NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE,DESCR "+
																						
																						" FROM  (SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE, "+
																						"       "+m_schema_name+".AF_CO_GET_INVOICE_DESCR(INVOICE_TYPE) DESCR "+ 
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,"+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND "+
																						"       BALANCE_TO_BE_RECEIVED>0 AND "+
																						"       A.INVOICE_TYPE = B.INVOICE_TYPE_CODE AND "+
					                                  //"	      A.ACTIVE_STATUS <> 'C' "+//AND DUE_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																						"       A.ACTIVE_STATUS NOT IN ('C','DB_CAN')  "+
																						" UNION ALL  "+
																						"	SELECT ODI_REF_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						"	       ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT, "+
																						"	       0,FINANCE_NO NO,ODI_DATE,0,CLIENT_CODE,'', "+
																						"	       CURRENCY_CODE, EXCHANGE_RATE,0,0,0,'ODI',ODI_DATE, "+
																						"        "+m_schema_name+".AF_CO_GET_INVOICE_DESCR('ODI') DESCR "+ 
																						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
																						"	WHERE  CLIENT_CODE='"+m_client+"' AND "+
																						"        A.INVOICE_NO=B.INVOICE_NO AND "+//ODI_DATE<=SYSDATE AND "+
																						"	       ODI_BAL_AMOUNT>0 "+
																						" UNION ALL "+
																						" SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						" VAT_AMOUNT,FINANCE_NO, "+
																						" DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						" 'INV_OTHER' INVOICE_TYPE,VALUE_DATE, "+
																						" "+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE) DESCR "+
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND "+
																						" BALANCE_TO_BE_RECEIVED>0 AND "+
																						" A.INVOICE_TYPE NOT IN ( SELECT INVOICE_TYPE_CODE FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD ) AND "+
																						//" A.ACTIVE_STATUS <> 'C' "+
																						"   A.ACTIVE_STATUS NOT IN ('C','DB_CAN')  "+
																						
																						" ) A, "+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE A.INVOICE_TYPE = B.INVOICE_TYPE_CODE "+
																						"	ORDER BY ORDER_NO,VALUE_DATE	");
                  }else{
									 rs1 = stmt1.executeQuery(
									 //out.println(
									 "SELECT INVOICE_NO,VAL_DATE, "+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       TO_CHAR(DUE_DATE,'DD-MM-YYYY'),NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE, DESCR "+
																						" FROM  (SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE, "+
																						"       "+m_schema_name+".AF_CO_GET_INVOICE_DESCR(INVOICE_TYPE) DESCR "+ 
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A"+
																						//"       ,"+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND FINANCE_NO='"+m_lea_no+"' AND "+
																						"       BALANCE_TO_BE_RECEIVED>0 AND "+
																						//"	      A.ACTIVE_STATUS <> 'C' "+//AND DUE_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																						"       A.ACTIVE_STATUS NOT IN ('C','DB_CAN')  "+
																						" UNION ALL  "+
																						"	SELECT ODI_REF_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						"	       ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT, "+
																						"	       0,B.FINANCE_NO NO,ODI_DATE,0,CLIENT_CODE,'', "+
																						"	       CURRENCY_CODE, EXCHANGE_RATE,0,0,0,'ODI',ODI_DATE, "+
																						"        "+m_schema_name+".AF_CO_GET_INVOICE_DESCR('ODI') DESCR "+ 
																						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
																						"	WHERE  CLIENT_CODE='"+m_client+"' AND B.FINANCE_NO='"+m_lea_no+"' AND "+
																						"        A.INVOICE_NO=B.INVOICE_NO AND "+//ODI_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
																						"	       ODI_BAL_AMOUNT>0  "+
																						
																						
																						" UNION ALL "+
																						" SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						" VAT_AMOUNT,FINANCE_NO, "+
																						" DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						" 'INV_OTHER' INVOICE_TYPE,VALUE_DATE, "+
																						" "+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE) DESCR "+
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND FINANCE_NO='"+m_lea_no+"' AND "+
																						" BALANCE_TO_BE_RECEIVED>0 AND "+
																						" A.INVOICE_TYPE NOT IN ( SELECT INVOICE_TYPE_CODE FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD ) AND "+
																						//" A.ACTIVE_STATUS <> 'C' "+
																						" A.ACTIVE_STATUS NOT IN ('C','DB_CAN')  "+
																						
																						
																						" ) A, "+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE A.INVOICE_TYPE = B.INVOICE_TYPE_CODE "+
					                                  "	ORDER BY ORDER_NO,VALUE_DATE	");
									}
									
								 boolean more1 = rs1.next();	
								 int i = 0;
								 double m_alloamt = 0;
								 if(more1){	
									out.println("<table><tr class=pdn_txtpos2 WIDTH=100%>");
									out.println("  <td WIDTH=12%>Invoice No</td>");
									out.println("  <td WIDTH=13%>Invoice Type</td>");
									out.println("  <td WIDTH=13%>Finance No</td>");
									out.println("  <td WIDTH=10%>Value Date</td>");
									out.println("  <td WIDTH=10%>Due Date</td>");
									out.println("  <td WIDTH=13%>Invoice Amount</td>");
									out.println("  <td WIDTH=12%>Balance Amount</td>");
									out.println("  <td WIDTH=13%>Allocated Amount</td>");
									out.println("  <td WIDTH=12%>Status</td></tr>");
									double m_inv_bal = 0;
									m_alloamt = 0;
								 while(more1){	
									out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_"+j+"_"+i+"\">");
									out.println("  <input type=hidden NAME=\"hid_invoice_type"+j+"_"+i+"\"  value=\""+rs1.getString(17)+"\"> ");
									out.println("  <input type=hidden NAME=\"hid_contract_no_"+j+"_"+i+"\"  value=\""+rs1.getString(7)+"\"> ");
									out.println("  <td align=left ><input type=text name=\"INV_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(1)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=left >"+rs1.getString(19)+"</td>");
									out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs1.getString(7)+"')\"><input type=text name=\"FIN_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(7)+"\" style=text-decoration:underline class=\"txt_input2\"></td>"); //drill down added ref no 728 nuwan de silva 26-07-07
									out.println("  <td align=left ><input type=text name=\"V_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(2)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=left ><input type=text name=\"D_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(8)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=right><input type=text name=\"INV_AM_"+j+"_"+i+"\" disabled value=\""+rs1.getString(3)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=right><input type=text name=\"BAL_AM_"+j+"_"+i+"\" disabled value=\""+rs1.getString(4)+"\" class=\"txt_input2\"></td>");
									
							m_inv_bal  = m_inv_bal+rs1.getDouble(4); // XXXXXXXXX
									if(m_rec_tot<m_inv_bal){
										if(m_rec_bal>= (m_inv_bal-m_rec_tot)){
										  //out.println("111m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
										  m_alloamt = m_alloamt +(m_inv_bal-m_rec_tot);
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"chk_alocate_amt('"+j+"','"+i+"'),format_number(document.Form1.Text_sett_amount"+j+"_"+i+",'30')\" disabled ></td>"); //"+nf.format((m_inv_bal-m_rec_tot))+"
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=chk_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");  //check_status
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    m_rec_bal = m_rec_bal-(m_inv_bal-m_rec_tot);
											m_rec_tot = m_rec_tot +(m_inv_bal-m_rec_tot);
											
										}else{
										 if(m_rec_bal>0){ 
										  //out.println("222m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
											m_alloamt = m_alloamt +m_rec_bal;
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"chk_alocate_amt('"+j+"','"+i+"'),format_number(document.Form1.Text_sett_amount"+j+"_"+i+",'30')\" disabled ></td>"); //"+nf.format(m_rec_bal)+"
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=chk_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>"); //check_status
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    m_rec_tot = m_rec_tot+m_rec_bal;
											m_rec_bal = 0;
											
										 }else{
											//out.println("444m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
											m_alloamt = m_alloamt +m_rec_bal;
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"chk_alocate_amt('"+j+"','"+i+"'),format_number(document.Form1.Text_sett_amount"+j+"_"+i+",'30')\" ></td>"); //"+nf.format(m_rec_bal)+"
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=chk_status(\""+j+"\",\""+i+"\") value=\"NO\" >"); //check_status
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    
										 }	
										}
									}else{
									    //out.println("333m_inv_bal="); 
									    out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"chk_alocate_amt('"+j+"','"+i+"'),format_number(document.Form1.Text_sett_amount"+j+"_"+i+",'30')\"  ></td>"); 
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=chk_status(\""+j+"\",\""+i+"\") value=\"NO\">"); //check_status
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"NO\" ></td>"); 
									}
									
									
									out.println("  </tr>");
									//out.println("m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
									i = i+1;
									more1 = rs1.next();	
									
								 }
										out.println("<input type=hidden name=\"ba_"+j+"\" value="+nf.format(m_rec_bal)+"><input type=hidden name=\"hid_invoice_count_"+j+"\" value="+i+"></table></div>");
					      
								}else{
								  out.println("  <input type=hidden name=hid_invoice_count_"+j+"  value="+i+"></div>");
					      
								}
								  //m_rec_tot = m_rec_tot +rs.getDouble(4);
									
								    
									//out.println("<input type=hidden name=hid_invoice_count_"+j+" value=0>");
									out.println("</td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									out.println("<td>&nbsp;</TD>");
									out.println("<td colspan=5 >");
									out.println("</td>");
									out.println("</tr>");
                	j=j+1;
									
									//if(rs.getString(6).equals(m_username)){
									//  out.println("<td ><input type=button name=\"Edit_"+j+"\" value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"EDIT\"); >");
									//	out.println("<input type=button name=\"Dele_"+j+"\" value=\"Del\" class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"DELETE\"); ></td>");
									//}else{
									//}                  
              }
          //}
          
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("<input type=hidden name=hid_count value="+j+"><input type=hidden name=hid_count_total value="+j+"></tr></table>");

				
      }
						
			
			else if(m_chksql.trim().equals("get_Invoice_alocation")){
			
			    String m_client      = req.getParameter("client");
					String m_lea_no      = req.getParameter("lea_no");
					
          
					rs = stmt.executeQuery (" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
																	"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE, "+
																	"	       A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK, "+
																	"	       EXCHANGE_RATE_REP_CURR,REC_AMOUNT_REP_CURR, "+
																	"	       EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+ 
																	"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
																	"	WHERE  A.REC_NO = B.REC_NO AND STATUS NOT IN ('CAD','RET','C') AND "+//<>'C' AND "+
																	"	       BAL_TOBE_RECEIVE>0 AND CLIENT_CODE = '"+m_client+"' "+
																	" ORDER BY EFF_VALDATE "+
																	"");
        
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' >Receipt No</td>");
					out.println("<td width='15%' align=right>Receipt Amount</td>");
          out.println("<td width='15%' align=right>Settled Amount</td>");
					out.println("<td width='20%' align=right>Balance Amount</td>");
					out.println("<td width='15%' align=right>Allocated Amount</td>");
					out.println("<td width='15%' align=right>Balance to Allocate</td>");
					//out.println("<td  width='35%' align=right>Amount</td>");
					//out.println("<td  width='10%'  ></td>");
					out.println("</tr>");
      
           int j = 0;      					
					 //Vector m_amount  = new Vector();
					 //Vector m_amount1 = new Vector();	
					 double m_rec_tot = 0;
					 double m_rec_bal = 0;
					
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input1 /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td style= cursor:hand; onClick=\"show_settle_receipt_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u><input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>"); //modify nuwan de silva 18-07-07
                  out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"ALLO_AMOUN_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right><input type=text name=\"A_AMOUNT_"+j+"\" value=\"0\" class=\"txt_input2\"></td>");
                  out.println("<td align=right><input type=text name=\"BA_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(4))+"\" class=\"txt_input2\"></td>");
                  //out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled>");
									//out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
                  //out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
									//out.println("     </td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									//out.println("<td></TD>");
									
									out.println("<td colspan=6 ><div id='inv_"+j+"'>");
								  //m_rec_tot = m_rec_tot +rs.getDouble(4);
									m_rec_bal   = rs.getDouble(4);
									if(m_lea_no.equals("")){
									 																					
									 rs1 = stmt1.executeQuery("SELECT INVOICE_NO,VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       TO_CHAR(DUE_DATE,'DD-MM-YYYY'),NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE,DESCR "+
																						
																						" FROM  (SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE, "+
																						"       "+m_schema_name+".AF_CO_GET_INVOICE_DESCR(INVOICE_TYPE) DESCR "+ 
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,"+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND "+
																						"       BALANCE_TO_BE_RECEIVED>0 AND "+
																						"       A.INVOICE_TYPE = B.INVOICE_TYPE_CODE AND "+
					                                  //"	      A.ACTIVE_STATUS <> 'C' "+//AND DUE_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																						"       A.ACTIVE_STATUS NOT IN ('C','DB_CAN')  "+
																						" UNION ALL  "+
																						"	SELECT ODI_REF_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						"	       ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT, "+
																						"	       0,FINANCE_NO NO,ODI_DATE,0,CLIENT_CODE,'', "+
																						"	       CURRENCY_CODE, EXCHANGE_RATE,0,0,0,'ODI',ODI_DATE, "+
																						"        "+m_schema_name+".AF_CO_GET_INVOICE_DESCR('ODI') DESCR "+ 
																						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
																						"	WHERE  CLIENT_CODE='"+m_client+"' AND "+
																						"        A.INVOICE_NO=B.INVOICE_NO AND "+//ODI_DATE<=SYSDATE AND "+
																						"	       ODI_BAL_AMOUNT>0 "+
																						" UNION ALL "+
																						" SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						" VAT_AMOUNT,FINANCE_NO, "+
																						" DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						" 'INV_OTHER' INVOICE_TYPE,VALUE_DATE, "+
																						" "+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE) DESCR "+
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND "+
																						" BALANCE_TO_BE_RECEIVED>0 AND "+
																						" A.INVOICE_TYPE NOT IN ( SELECT INVOICE_TYPE_CODE FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD ) AND "+
																						//" A.ACTIVE_STATUS <> 'C' "+
																						" A.ACTIVE_STATUS NOT IN ('C','DB_CAN')  "+
																						
																						" ) A, "+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE A.INVOICE_TYPE = B.INVOICE_TYPE_CODE "+
																						"	ORDER BY ORDER_NO,VALUE_DATE	");
                  }else{
									 rs1 = stmt1.executeQuery("SELECT INVOICE_NO,VAL_DATE, "+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       TO_CHAR(DUE_DATE,'DD-MM-YYYY'),NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE, DESCR "+
																						" FROM  (SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE, "+
																						"       "+m_schema_name+".AF_CO_GET_INVOICE_DESCR(INVOICE_TYPE) DESCR "+ 
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A"+
																						//"       ,"+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND FINANCE_NO='"+m_lea_no+"' AND "+
																						"       BALANCE_TO_BE_RECEIVED>0 AND "+
																						//"	      A.ACTIVE_STATUS <> 'C' "+//AND DUE_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																						"       A.ACTIVE_STATUS NOT IN ('C','DB_CAN')  "+
																						" UNION ALL  "+
																						"	SELECT ODI_REF_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						"	       ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT, "+
																						"	       0,FINANCE_NO NO,ODI_DATE,0,CLIENT_CODE,'', "+
																						"	       CURRENCY_CODE, EXCHANGE_RATE,0,0,0,'ODI',ODI_DATE, "+
																						"        "+m_schema_name+".AF_CO_GET_INVOICE_DESCR('ODI') DESCR "+ 
																						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
																						"	WHERE  CLIENT_CODE='"+m_client+"' AND FINANCE_NO='"+m_lea_no+"' AND "+
																						"        A.INVOICE_NO=B.INVOICE_NO AND "+//ODI_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
																						"	       ODI_BAL_AMOUNT>0  "+
																						
																						
																						" UNION ALL "+
																						" SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						" VAT_AMOUNT,FINANCE_NO, "+
																						" DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						" 'INV_OTHER' INVOICE_TYPE,VALUE_DATE, "+
																						" "+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE) DESCR "+
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND FINANCE_NO='"+m_lea_no+"' AND "+
																						" BALANCE_TO_BE_RECEIVED>0 AND "+
																						" A.INVOICE_TYPE NOT IN ( SELECT INVOICE_TYPE_CODE FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD ) AND "+
																						//" A.ACTIVE_STATUS <> 'C' "+
																						" A.ACTIVE_STATUS NOT IN ('C','DB_CAN')  "+
																						
																						
																						" ) A, "+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE A.INVOICE_TYPE = B.INVOICE_TYPE_CODE "+
					                                  "	ORDER BY ORDER_NO,VALUE_DATE	");
									}
									
								 boolean more1 = rs1.next();	
								 int i = 0;
								 double m_alloamt = 0;
								 if(more1){	
									out.println("<table><tr class=pdn_txtpos2 WIDTH=100%>");
									out.println("  <td WIDTH=12%>Invoice No</td>");
									out.println("  <td WIDTH=13%>Invoice Type</td>");
									out.println("  <td WIDTH=13%>Finance No</td>");
									out.println("  <td WIDTH=10%>Value Date</td>");
									out.println("  <td WIDTH=10%>Due Date</td>");
									out.println("  <td WIDTH=13%>Invoice Amount</td>");
									out.println("  <td WIDTH=12%>Balance Amount</td>");
									out.println("  <td WIDTH=13%>Allocated Amount</td>");
									out.println("  <td WIDTH=12%>Status</td></tr>");
									double m_inv_bal = 0;
									m_alloamt = 0;
								 while(more1){	
									out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_"+j+"_"+i+"\">");
									out.println("  <input type=hidden NAME=\"hid_invoice_type"+j+"_"+i+"\"  value=\""+rs1.getString(17)+"\"> ");
									out.println("  <td align=left ><input type=text name=\"INV_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(1)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=left >"+rs1.getString(19)+"</td>");
									out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs1.getString(7)+"')\"><input type=text name=\"FIN_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(7)+"\" style=text-decoration:underline class=\"txt_input2\"></td>"); //drill down added ref no 728 nuwan de silva 26-07-07
									out.println("  <td align=left ><input type=text name=\"V_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(2)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=left ><input type=text name=\"D_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(8)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=right><input type=text name=\"INV_AM_"+j+"_"+i+"\" disabled value=\""+rs1.getString(3)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=right><input type=text name=\"BAL_AM_"+j+"_"+i+"\" disabled value=\""+rs1.getString(4)+"\" class=\"txt_input2\"></td>");
									
							m_inv_bal  = m_inv_bal+rs1.getDouble(4); // XXXXXXXXX
									if(m_rec_tot<m_inv_bal){
										if(m_rec_bal>= (m_inv_bal-m_rec_tot)){
										  //out.println("111m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
										  m_alloamt = m_alloamt +(m_inv_bal-m_rec_tot);
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"chk_alocate_amt('"+j+"','"+i+"'),format_number(document.Form1.Text_sett_amount"+j+"_"+i+",'30')\" ></td>"); //"+nf.format((m_inv_bal-m_rec_tot))+"
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=chk_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");  //check_status
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    m_rec_bal = m_rec_bal-(m_inv_bal-m_rec_tot);
											m_rec_tot = m_rec_tot +(m_inv_bal-m_rec_tot);
											
										}else{
										 if(m_rec_bal>0){ 
										  //out.println("222m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
											m_alloamt = m_alloamt +m_rec_bal;
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"chk_alocate_amt('"+j+"','"+i+"'),format_number(document.Form1.Text_sett_amount"+j+"_"+i+",'30')\" ></td>"); //"+nf.format(m_rec_bal)+"
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=chk_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>"); //check_status
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    m_rec_tot = m_rec_tot+m_rec_bal;
											m_rec_bal = 0;
											
										 }else{
											//out.println("444m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
											m_alloamt = m_alloamt +m_rec_bal;
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"chk_alocate_amt('"+j+"','"+i+"'),format_number(document.Form1.Text_sett_amount"+j+"_"+i+",'30')\"></td>"); //"+nf.format(m_rec_bal)+"
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=chk_status(\""+j+"\",\""+i+"\") value=\"NO\" >"); //check_status
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    
										 }	
										}
									}else{
									    //out.println("333m_inv_bal="); 
									    out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"chk_alocate_amt('"+j+"','"+i+"'),format_number(document.Form1.Text_sett_amount"+j+"_"+i+",'30')\"></td>"); 
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=chk_status(\""+j+"\",\""+i+"\") value=\"NO\">"); //check_status
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"NO\" ></td>"); 
									}
									
									
									out.println("  </tr>");
									//out.println("m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
									i = i+1;
									more1 = rs1.next();	
									
								 }
										out.println("<input type=hidden name=\"ba_"+j+"\" value="+nf.format(m_rec_bal)+"><input type=hidden name=\"hid_invoice_count_"+j+"\" value="+i+"></table></div>");
					      
								}else{
								  out.println("  <input type=hidden name=hid_invoice_count_"+j+"  value="+i+"></div>");
					      
								}
								  //m_rec_tot = m_rec_tot +rs.getDouble(4);
									
								    
									//out.println("<input type=hidden name=hid_invoice_count_"+j+" value=0>");
									out.println("</td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									out.println("<td>&nbsp;</TD>");
									out.println("<td colspan=5 >");
									out.println("</td>");
									out.println("</tr>");
                	j=j+1;
									
									//if(rs.getString(6).equals(m_username)){
									//  out.println("<td ><input type=button name=\"Edit_"+j+"\" value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"EDIT\"); >");
									//	out.println("<input type=button name=\"Dele_"+j+"\" value=\"Del\" class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"DELETE\"); ></td>");
									//}else{
									//}                  
              }
          //}
          
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");

				
      }
					
			
				else if(m_chksql.trim().equals("get_manual_alocation22")){
			
			    String m_client      = req.getParameter("client");
					String m_lea_no      = req.getParameter("lea_no");
					
          
					rs = stmt.executeQuery (" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
																	"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE, "+
																	"	       A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK, "+
																	"	       EXCHANGE_RATE_REP_CURR,REC_AMOUNT_REP_CURR, "+
																	"	       EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+ 
																	"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
																	"	WHERE  A.REC_NO = B.REC_NO AND STATUS NOT IN ('CAD','RET','C') AND "+//<>'C' AND "+
																	"	       BAL_TOBE_RECEIVE>0 AND CLIENT_CODE = '"+m_client+"' "+
																	//" ORDER BY EFF_VALDATE "+ // commented by udara 19-11-2013
																	" ORDER BY A.ENT_DATE ASC "+ // added by udara 19-11-2013
																	"");

					
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' >Receipt No</td>");
					out.println("<td width='15%' align=right>Receipt Amount</td>");
          out.println("<td width='15%' align=right>Settled Amount</td>");
					out.println("<td width='20%' align=right>Balance Amount</td>");
					out.println("<td width='15%' align=right>Allocated Amount</td>");
					out.println("<td width='15%' align=right>Balance to Allocate</td>");
					//out.println("<td  width='35%' align=right>Amount</td>");
					//out.println("<td  width='10%'  ></td>");
					out.println("</tr>");
      
           int j = 0;      					
					 //Vector m_amount  = new Vector();
					 //Vector m_amount1 = new Vector();	
					 double m_rec_tot = 0;
					 double m_rec_bal = 0;
					
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input1 /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td style= cursor:hand; onClick=\"show_settle_receipt_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u><input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>"); //modify nuwan de silva 18-07-07
                  out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"ALLO_AMOUN_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right><input type=text name=\"A_AMOUNT_"+j+"\" value=\"0\" class=\"txt_input2\"></td>");
                  out.println("<td align=right><input type=text name=\"BA_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(4))+"\" class=\"txt_input2\"></td>");
                  //out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled>");
									//out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
                  //out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
									//out.println("     </td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									//out.println("<td></TD>");
									
									out.println("<td colspan=6 ><div id='inv_"+j+"'>");
								  m_rec_tot = m_rec_tot +rs.getDouble(4);
									//m_rec_bal   = rs.getDouble(4);
									if(m_lea_no.equals("")){
									 																					
									 rs1 = stmt1.executeQuery("SELECT INVOICE_NO,VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       TO_CHAR(DUE_DATE,'DD-MM-YYYY'),NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE "+
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
					                                  //"	      A.ACTIVE_STATUS <> 'C' "+//AND DUE_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																						"       A.ACTIVE_STATUS NOT IN ('C','DB_CAN')  "+
																						" UNION ALL  "+
																						"	SELECT ODI_REF_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						"	       ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT, "+
																						"	       0,FINANCE_NO NO,ODI_DATE,0,CLIENT_CODE,'', "+
																						"	       CURRENCY_CODE, EXCHANGE_RATE,0,0,0,'ODI',ODI_DATE "+
																						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
																						"	WHERE  CLIENT_CODE='"+m_client+"' AND "+
																						"        A.INVOICE_NO=B.INVOICE_NO AND "+//ODI_DATE<=SYSDATE AND "+
																						"	       ODI_BAL_AMOUNT>0 ) A, "+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE A.INVOICE_TYPE = B.INVOICE_TYPE_CODE "+
																						"	ORDER BY ORDER_NO,VALUE_DATE	");
                  }else{
									 rs1 = stmt1.executeQuery("SELECT INVOICE_NO,VAL_DATE, "+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       TO_CHAR(DUE_DATE,'DD-MM-YYYY'),NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE "+
																						" FROM  (SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE "+
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A"+
																						//"       ,"+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND FINANCE_NO='"+m_lea_no+"' AND "+
																						"       BALANCE_TO_BE_RECEIVED>0 AND "+
																						//"	      A.ACTIVE_STATUS <> 'C' "+//AND DUE_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																						"       A.ACTIVE_STATUS NOT IN ('C','DB_CAN')  "+
																						" UNION ALL  "+
																						"	SELECT ODI_REF_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						"	       ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT, "+
																						"	       0,FINANCE_NO NO,ODI_DATE,0,CLIENT_CODE,'', "+
																						"	       CURRENCY_CODE, EXCHANGE_RATE,0,0,0,'ODI',ODI_DATE "+
																						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
																						"	WHERE  CLIENT_CODE='"+m_client+"' AND FINANCE_NO='"+m_lea_no+"' AND "+
																						"        A.INVOICE_NO=B.INVOICE_NO AND "+//ODI_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
																						"	       ODI_BAL_AMOUNT>0 ) A, "+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE A.INVOICE_TYPE = B.INVOICE_TYPE_CODE "+
					                                  "	ORDER BY ORDER_NO,VALUE_DATE	");
									}
									
								 boolean more1 = rs1.next();	
								 int i = 0;
								 double m_alloamt = 0;
								 if(more1){	
									out.println("<table><tr class=pdn_txtpos2 WIDTH=100%>");
									out.println("  <td WIDTH=12%>Invoice No</td>");
									out.println("  <td WIDTH=13%>Finance No</td>");
									out.println("  <td WIDTH=10%>Value Date</td>");
									out.println("  <td WIDTH=10%>Due Date</td>");
									out.println("  <td WIDTH=13%>Invoice Amount</td>");
									out.println("  <td WIDTH=12%>Balance Amount</td>");
									out.println("  <td WIDTH=13%>Allocated Amount</td>");
									out.println("  <td WIDTH=12%>Status</td></tr>");
									double m_inv_bal = 0;
									m_alloamt = 0;
								 while(more1){	
									out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_"+j+"_"+i+"\">");
									out.println("  <input type=hidden NAME=\"hid_invoice_type"+j+"_"+i+"\"  value=\""+rs1.getString(17)+"\" >");

									out.println("  <td align=left ><input type=text name=\"INV_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(1)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs1.getString(7)+"')\"><input type=text name=\"FIN_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(7)+"\" style=text-decoration:underline class=\"txt_input2\"></td>"); //drill down added ref no 728 nuwan de silva 26-07-07
									out.println("  <td align=left ><input type=text name=\"V_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(2)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=left ><input type=text name=\"D_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(8)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=right><input type=text name=\"INV_AM_"+j+"_"+i+"\" disabled value=\""+rs1.getString(3)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=right><input type=text name=\"BAL_AM_"+j+"_"+i+"\" disabled value=\""+rs1.getString(4)+"\" class=\"txt_input2\"></td>");

									m_inv_bal  = m_inv_bal+rs1.getDouble(4); // XXXXXXXXX
									if(m_rec_tot<m_inv_bal){
										if(m_rec_bal>= (m_inv_bal-m_rec_tot)){
										  out.println("111m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
										  m_alloamt = m_alloamt +(m_inv_bal-m_rec_tot);
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format((m_inv_bal-m_rec_tot))+"\" class=\"txt_input2\" onBlur=\"chk_alocate_amt('"+j+"','"+i+"'),format_number(document.Form1.Text_sett_amount"+j+"_"+i+",'30')\" ></td>"); //"+nf.format((m_inv_bal-m_rec_tot))+"
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=chk_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");  //check_status
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    m_rec_bal = m_rec_bal-(m_inv_bal-m_rec_tot);
											m_rec_tot = m_rec_tot +(m_inv_bal-m_rec_tot);
											
										}else{
										 if(m_rec_bal>0){ 
										  out.println("222m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
											m_alloamt = m_alloamt +m_rec_bal;
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onBlur=\"chk_alocate_amt('"+j+"','"+i+"'),format_number(document.Form1.Text_sett_amount"+j+"_"+i+",'30')\" ></td>"); //"+nf.format(m_rec_bal)+"
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=chk_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>"); //check_status
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    m_rec_tot = m_rec_tot+m_rec_bal;
											m_rec_bal = 0;
											
										 }else{
											out.println("444m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
											m_alloamt = m_alloamt +m_rec_bal;
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onBlur=\"chk_alocate_amt('"+j+"','"+i+"'),format_number(document.Form1.Text_sett_amount"+j+"_"+i+",'30')\"></td>"); //"+nf.format(m_rec_bal)+"
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=chk_status(\""+j+"\",\""+i+"\") value=\"NO\" >"); //check_status
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    
										 }	
										}
									}else{
									    //out.println("333m_inv_bal="); 
									    out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"chk_alocate_amt('"+j+"','"+i+"'),format_number(document.Form1.Text_sett_amount"+j+"_"+i+",'30')\"></td>"); 
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=chk_status(\""+j+"\",\""+i+"\") value=\"NO\">"); //check_status
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"NO\" ></td>"); 
									}
									
									
									out.println("  </tr>");
									//out.println("m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
									i = i+1;
									more1 = rs1.next();	
									
								 }
										out.println("<input type=hidden name=\"ba_"+j+"\" value="+nf.format(m_rec_bal)+"><input type=hidden name=\"hid_invoice_count_"+j+"\" value="+i+"></table></div>");
					      
								}else{
								  out.println("  <input type=hidden name=hid_invoice_count_"+j+"  value="+i+"></div>");
					      
								}
								  //m_rec_tot = m_rec_tot +rs.getDouble(4);
									
								    
									//out.println("<input type=hidden name=hid_invoice_count_"+j+" value=0>");
									out.println("</td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									out.println("<td>&nbsp;</TD>");
									out.println("<td colspan=5 >");
									out.println("</td>");
									out.println("</tr>");
                	j=j+1;
									
									//if(rs.getString(6).equals(m_username)){
									//  out.println("<td ><input type=button name=\"Edit_"+j+"\" value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"EDIT\"); >");
									//	out.println("<input type=button name=\"Dele_"+j+"\" value=\"Del\" class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"DELETE\"); ></td>");
									//}else{
									//}                  
              }
          //}
          
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");

				
      }
		   //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx	
			
			
			
			else if(m_chksql.trim().equals("get_manual_alocation")){
			
			    String m_client      = req.getParameter("client");
					String m_lea_no      = req.getParameter("lea_no");
					
          
					/*rs = stmt.executeQuery (" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
																	"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE, "+
																	"	       A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK, "+
																	"	       EXCHANGE_RATE_REP_CURR,REC_AMOUNT_REP_CURR, "+
																	"	       EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+ 
																	"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
																	"	WHERE  A.REC_NO = B.REC_NO AND STATUS NOT IN ('CAD','RET','C') AND "+//<>'C' AND "+
																	"	       BAL_TOBE_RECEIVE>0 AND CLIENT_CODE = '"+m_client+"' ");
                    */
									rs = stmt.executeQuery (" SELECT "+
																	" A.REC_NO,   "+           //1
																	" A.REC_AMOUNT ,"+         //2
																	" A.APP_REC_AMOUNT,   "+   //3
																	" A.BAL_TOBE_RECEIVE,   "+ //4
																	" A.ALLOCATED_AMOUNT  , "+ //5
																	" A.FINANCE_NO  "+         //6
																	" FROM "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  B "+
																	" WHERE  A.REC_NO=B.REC_NO AND A.CLIENT_CODE='"+m_client+"' AND A.FINANCE_NO LIKE '"+m_lea_no+"%'  AND A.BAL_TOBE_RECEIVE > 0  "+
																	" AND B.STATUS NOT IN ('CAD','RET','C') "+
																	//" ORDER BY EFF_VALDATE "+ // commented by udara 18-11-2013
																	" ORDER BY B.ENT_DATE ASC "+ // added by udara 18-11-2013
																	" ");
					
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' >Receipt No</td>");
					out.println("<td width='15%' align=right>Receipt Amount</td>");
          out.println("<td width='15%' align=right>Settled Amount</td>");
					out.println("<td width='20%' align=right>Balance Amount</td>");
					out.println("<td width='15%' align=right>Allocated Amount</td>");
					out.println("<td width='15%' align=right>Balance to Allocate</td>");
					//out.println("<td  width='35%' align=right>Amount</td>");
					//out.println("<td  width='10%'  ></td>");
					out.println("</tr>");
      
           int j = 0;      					
					 //Vector m_amount  = new Vector();
					 //Vector m_amount1 = new Vector();	
					 double m_rec_tot = 0;
					 double m_rec_bal = 0;
					
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input1 /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td style= cursor:hand; onClick=\"show_settle_receipt_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u><input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>"); //modify nuwan de silva 18-07-07
                  out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"ALLO_AMOUN_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right><input type=text name=\"A_AMOUNT_"+j+"\" value=\"0\" class=\"txt_input2\"></td>");
                  out.println("<td align=right><input type=text name=\"BA_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(4))+"\" class=\"txt_input2\"></td>");
                  //out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled>");
									//out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
                  //out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
									//out.println("     </td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									//out.println("<td></TD>");
									
									out.println("<td colspan=6 ><div id='inv_"+j+"'>");
								  //m_rec_tot = m_rec_tot +rs.getDouble(4);
									m_rec_bal   = rs.getDouble(4);
									if(m_lea_no.equals("")){
									 																					
									 rs1 = stmt1.executeQuery(
									// out.println(
									 "SELECT INVOICE_NO,VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       TO_CHAR(DUE_DATE,'DD-MM-YYYY'),NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE,DESCR "+
																						" FROM  (SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE, "+
																						"        "+m_schema_name+".AF_CO_GET_INVOICE_DESCR(A.INVOICE_TYPE)  DESCR "+
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,"+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND "+
																						"       BALANCE_TO_BE_RECEIVED>0 AND "+
																						"       A.INVOICE_TYPE = B.INVOICE_TYPE_CODE AND "+
					                                  //"	      A.ACTIVE_STATUS <> 'C' "+//AND DUE_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																						"       A.ACTIVE_STATUS NOT IN ('C','DB_CAN')  "+
																						" UNION ALL  "+
																						"	SELECT ODI_REF_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						"	       ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT, "+
																						"	       0,FINANCE_NO NO,ODI_DATE,0,CLIENT_CODE,'', "+
																						"	       CURRENCY_CODE, EXCHANGE_RATE,0,0,0,'ODI',ODI_DATE, "+
																						"        "+m_schema_name+".AF_CO_GET_INVOICE_DESCR('ODI')  DESCR "+
																						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
																						"	WHERE  CLIENT_CODE='"+m_client+"' AND "+
																						"        A.INVOICE_NO=B.INVOICE_NO AND "+//ODI_DATE<=SYSDATE AND "+
																						"	       ODI_BAL_AMOUNT>0 "+
																						" UNION ALL  "+
																						" SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						" VAT_AMOUNT,FINANCE_NO, "+
																						" DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						" 'INV_OTHER' INVOICE_TYPE,VALUE_DATE, "+
																						" "+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE) DESCR "+
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND "+
																						" BALANCE_TO_BE_RECEIVED>0 AND "+
																						" A.INVOICE_TYPE NOT IN (SELECT INVOICE_TYPE_CODE  FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD) AND "+
																						//" A.ACTIVE_STATUS <> 'C' "+														
																						"       A.ACTIVE_STATUS NOT IN ('C','DB_CAN')  "+
																						
																						" ) A, "+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE A.INVOICE_TYPE = B.INVOICE_TYPE_CODE "+
																						"	ORDER BY ORDER_NO,VALUE_DATE	");
                  }else{
									 rs1 = stmt1.executeQuery(
									// out.println(
									 "SELECT INVOICE_NO,VAL_DATE, "+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       TO_CHAR(DUE_DATE,'DD-MM-YYYY'),NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE,DESCR "+
																						" FROM  (SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE, "+
																						"      "+m_schema_name+".AF_CO_GET_INVOICE_DESCR(INVOICE_TYPE) DESCR "+
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A"+
																						//"       ,"+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND FINANCE_NO='"+m_lea_no+"' AND "+
																						"       BALANCE_TO_BE_RECEIVED>0 AND "+
																						//"	      A.ACTIVE_STATUS <> 'C' "+//AND DUE_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																						"       A.ACTIVE_STATUS NOT IN ('C','DB_CAN')  "+
																						" UNION ALL  "+
																						"	SELECT ODI_REF_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						"	       ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT, "+
																						"	       0,B.FINANCE_NO NO,ODI_DATE,0,CLIENT_CODE,'', "+
																						"	       CURRENCY_CODE, EXCHANGE_RATE,0,0,0,'ODI',ODI_DATE, "+
																						"        "+m_schema_name+".AF_CO_GET_INVOICE_DESCR('ODI') DESCR "+
																						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
																						"	WHERE  CLIENT_CODE='"+m_client+"' AND B.FINANCE_NO='"+m_lea_no+"' AND "+
																						"        A.INVOICE_NO=B.INVOICE_NO AND "+//ODI_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
																						"	       ODI_BAL_AMOUNT>0 "+
																						
																							" UNION ALL  "+
																						" SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						" VAT_AMOUNT,FINANCE_NO, "+
																						" DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						" 'INV_OTHER' INVOICE_TYPE,VALUE_DATE, "+
																						" "+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE) DESCR "+
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND FINANCE_NO='"+m_lea_no+"' AND "+
																						" BALANCE_TO_BE_RECEIVED>0 AND "+
																						" A.INVOICE_TYPE NOT IN (SELECT INVOICE_TYPE_CODE  FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD) AND "+
																						//" A.ACTIVE_STATUS <> 'C' "+																						
																						" A.ACTIVE_STATUS NOT IN ('C','DB_CAN')  "+
																						
																						" ) A, "+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE A.INVOICE_TYPE = B.INVOICE_TYPE_CODE "+
					                                  "	ORDER BY ORDER_NO,VALUE_DATE	");
									}
									
								 boolean more1 = rs1.next();	
								 int i = 0;
								 double m_alloamt = 0;
								 if(more1){	
									out.println("<table><tr class=pdn_txtpos2 WIDTH=100%>");
									out.println("  <td WIDTH=12%>Invoice No </td>");
									out.println("  <td WIDTH=13%>Invoice Type</td>");
									out.println("  <td WIDTH=13%>Finance No</td>");
									out.println("  <td WIDTH=10%>Value Date</td>");
									out.println("  <td WIDTH=10%>Due Date</td>");
									out.println("  <td WIDTH=13%>Invoice Amount</td>");
									out.println("  <td WIDTH=12%>Balance Amount</td>");
									out.println("  <td WIDTH=13%>Allocated Amount</td>");
									out.println("  <td WIDTH=12%>Status</td></tr>");
									double m_inv_bal = 0;
									m_alloamt = 0;
								 while(more1){	
									out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_"+j+"_"+i+"\">");
									out.println("  <input type=hidden NAME=\"hid_invoice_type"+j+"_"+i+"\"  value=\""+rs1.getString(17)+"\"> ");
									out.println("  <td align=left ><input type=text name=\"INV_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(1)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=left >"+rs1.getString(19)+"</td>");
									out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs1.getString(7)+"')\"><input type=text name=\"FIN_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(7)+"\" style={text-decoration:underline,width='200px'} class=\"txt_input2\"></td>"); //drill down added ref no 728 nuwan de silva 26-07-07
									out.println("  <td align=left ><input type=text name=\"V_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(2)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=left ><input type=text name=\"D_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(8)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=right><input type=text name=\"INV_AM_"+j+"_"+i+"\" disabled value=\""+rs1.getString(3)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=right><input type=text name=\"BAL_AM_"+j+"_"+i+"\" disabled value=\""+rs1.getString(4)+"\" class=\"txt_input2\"></td>");
									m_inv_bal  = m_inv_bal+rs1.getDouble(4); 
									if(m_rec_tot<m_inv_bal){
										if(m_rec_bal>= (m_inv_bal-m_rec_tot)){
										  //out.println("111m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
										  m_alloamt = m_alloamt +(m_inv_bal-m_rec_tot);
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format((m_inv_bal-m_rec_tot))+"\" class=\"txt_input2\" onBlur=\"chk_bal_amnt('"+j+"','"+i+"')\" disabled></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    m_rec_bal = m_rec_bal-(m_inv_bal-m_rec_tot);
											m_rec_tot = m_rec_tot +(m_inv_bal-m_rec_tot);
											
										}else{
										 if(m_rec_bal>0){ 
										  //out.println("222m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
											m_alloamt = m_alloamt +m_rec_bal;
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onBlur=\"chk_bal_amnt('"+j+"','"+i+"')\" disabled></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    m_rec_tot = m_rec_tot+m_rec_bal;
											m_rec_bal = 0;
											
										 }else{
											//out.println("444m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
											m_alloamt = m_alloamt +m_rec_bal;
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onBlur=\"chk_bal_amnt('"+j+"','"+i+"')\"></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"NO\" >");
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    
										 }	
										}
									}else{
									    //out.println("333m_inv_bal="); 
									    out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"chk_bal_amnt('"+j+"','"+i+"')\"></td>"); 
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"NO\">");
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"NO\" ></td>"); 
									}
									
									
									out.println("  </tr>");
									//out.println("m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
									i = i+1;
									more1 = rs1.next();	
									
								 }
										out.println("<input type=hidden name=\"ba_"+j+"\" value="+nf.format(m_rec_bal)+"><input type=hidden name=\"hid_invoice_count_"+j+"\" value="+i+"></table></div>");
					      
								}else{
								  out.println("  <input type=hidden name=hid_invoice_count_"+j+"  value="+i+"></div>");
					      
								}
								  //m_rec_tot = m_rec_tot +rs.getDouble(4);
									
								    
									//out.println("<input type=hidden name=hid_invoice_count_"+j+" value=0>");
									out.println("</td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									out.println("<td>&nbsp;</TD>");
									out.println("<td colspan=5 >");
									out.println("</td>");
									out.println("</tr>");
                	j=j+1;
									
									//if(rs.getString(6).equals(m_username)){
									//  out.println("<td ><input type=button name=\"Edit_"+j+"\" value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"EDIT\"); >");
									//	out.println("<input type=button name=\"Dele_"+j+"\" value=\"Del\" class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"DELETE\"); ></td>");
									//}else{
									//}                  
              }
          //}
          
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");

				
      }
			
			
			
			
			//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
			
			
			else if(m_chksql.trim().equals("get_Receipt")){
			
			    String m_client      = req.getParameter("client");
					String m_lea_no      = req.getParameter("lea_no");
					
          
					rs = stmt.executeQuery (" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
																	"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE, "+
																	"	       A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK, "+
																	"	       EXCHANGE_RATE_REP_CURR,REC_AMOUNT_REP_CURR, "+
																	"	       EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+ 
																	"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
																	"	WHERE  A.REC_NO = B.REC_NO AND STATUS NOT IN ('CAD','RET','C') AND "+//<>'C' AND "+
																	"	       BAL_TOBE_RECEIVE>0 AND CLIENT_CODE = '"+m_client+"'  "+
																	//" ORDER BY EFF_VALDATE "+ // commented by udara 19-11-2013
																	" ORDER BY A.ENT_DATE ASC "+
																	"");

					
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' >Receipt No</td>");
					out.println("<td width='15%' align=right>Receipt Amount</td>");
          out.println("<td width='15%' align=right>Settled Amount</td>");
					out.println("<td width='20%' align=right>Balance Amount</td>");
					out.println("<td width='15%' align=right>Allocated Amount</td>");
					out.println("<td width='15%' align=right>Balance to Allocate</td>");
					//out.println("<td  width='35%' align=right>Amount</td>");
					//out.println("<td  width='10%'  ></td>");
					out.println("</tr>");
      
           int j = 0;      					
					 //Vector m_amount  = new Vector();
					 //Vector m_amount1 = new Vector();	
					 double m_rec_tot = 0;
					 double m_rec_bal = 0;
					
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input1 /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td style= cursor:hand; onClick=\"show_settle_receipt_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u><input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>"); //modify nuwan de silva 18-07-07
                  out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"ALLO_AMOUN_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right><input type=text name=\"A_AMOUNT_"+j+"\" value=\"0\" class=\"txt_input2\"></td>");
                  out.println("<td align=right><input type=text name=\"BA_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(4))+"\" class=\"txt_input2\"></td>");
                  //out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled>");
									//out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
                  //out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
									//out.println("     </td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									//out.println("<td></TD>");
									
									out.println("<td colspan=6 ><div id='inv_"+j+"'>");
								  //m_rec_tot = m_rec_tot +rs.getDouble(4);
									m_rec_bal   = rs.getDouble(4);
									if(m_lea_no.equals("")){
									 																					
									 rs1 = stmt1.executeQuery("SELECT INVOICE_NO,VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       TO_CHAR(DUE_DATE,'DD-MM-YYYY'),NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE "+
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
					                                  //"	      A.ACTIVE_STATUS <> 'C' "+//AND DUE_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																						//"       A.ACTIVE_STATUS NOT IN ('C','DB_CAN')  "+
																						"         A.ACTIVE_STATUS = 'Y'  "+//Modified Sandun on 04-06-2009
																						" UNION ALL  "+
																						"	SELECT ODI_REF_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						"	       ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT, "+
																						"	       0,FINANCE_NO NO,ODI_DATE,0,CLIENT_CODE,'', "+
																						"	       CURRENCY_CODE, EXCHANGE_RATE,0,0,0,'ODI',ODI_DATE "+
																						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
																						"	WHERE  CLIENT_CODE='"+m_client+"' AND "+
																						"        A.INVOICE_NO=B.INVOICE_NO AND "+//ODI_DATE<=SYSDATE AND "+
																						"	       ODI_BAL_AMOUNT>0 ) A, "+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE A.INVOICE_TYPE = B.INVOICE_TYPE_CODE "+
																						"	ORDER BY ORDER_NO,VALUE_DATE	");
                  }else{
									 rs1 = stmt1.executeQuery("SELECT INVOICE_NO,VAL_DATE, "+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       TO_CHAR(DUE_DATE,'DD-MM-YYYY'),NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE "+
																						" FROM  (SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE,"+
										                        "       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																						"       VAT_AMOUNT,FINANCE_NO, "+
																						"       DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																						"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																						"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																						"       INVOICE_TYPE,VALUE_DATE "+
																						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A"+
																						//"       ,"+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE CLIENT_CODE='"+m_client+"' AND FINANCE_NO='"+m_lea_no+"' AND "+
																						"       BALANCE_TO_BE_RECEIVED>0 AND "+
																						//"	      A.ACTIVE_STATUS <> 'C' "+//AND DUE_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																						//"       A.ACTIVE_STATUS NOT IN ('C','DB_CAN')  "+
																						"         A.ACTIVE_STATUS = 'Y'  "+//Modified Sandun on 04-06-2009
																						" UNION ALL  "+
																						"	SELECT ODI_REF_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY') VAL_DATE, "+
																						"	       ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT, "+
																						"	       0,B.FINANCE_NO NO,ODI_DATE,0,CLIENT_CODE,'', "+
																						"	       CURRENCY_CODE, EXCHANGE_RATE,0,0,0,'ODI',ODI_DATE "+
																						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
																						"	WHERE  CLIENT_CODE='"+m_client+"' AND B.FINANCE_NO='"+m_lea_no+"' AND "+
																						"        A.INVOICE_NO=B.INVOICE_NO AND "+//ODI_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
																						"	       ODI_BAL_AMOUNT>0 ) A, "+
																						"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																						" WHERE A.INVOICE_TYPE = B.INVOICE_TYPE_CODE "+
					                                  "	ORDER BY ORDER_NO,VALUE_DATE	");
									}
									
								 boolean more1 = rs1.next();	
								 int i = 0;
								 double m_alloamt = 0;
								 if(more1){	
									out.println("<table><tr class=pdn_txtpos2 WIDTH=100%>");
									out.println("  <td WIDTH=12%>Invoice No</td>");
									out.println("  <td WIDTH=13%>Finance No</td>");
									out.println("  <td WIDTH=10%>Value Date</td>");
									out.println("  <td WIDTH=10%>Due Date</td>");
									out.println("  <td WIDTH=13%>Invoice Amount</td>");
									out.println("  <td WIDTH=12%>Balance Amount</td>");
									out.println("  <td WIDTH=13%>Allocated Amount</td>");
									out.println("  <td WIDTH=12%>Status</td></tr>");
									double m_inv_bal = 0;
									m_alloamt = 0;
								 while(more1){	
									out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_"+j+"_"+i+"\">");
									out.println("  <input type=hidden NAME=\"hid_invoice_type"+j+"_"+i+"\"  value=\""+rs1.getString(17)+"\"> ");
									out.println("  <td align=left ><input type=text name=\"INV_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(1)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs1.getString(7)+"')\"><input type=text name=\"FIN_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(7)+"\" style=text-decoration:underline class=\"txt_input2\"></td>"); //drill down added ref no 728 nuwan de silva 26-07-07
									out.println("  <td align=left ><input type=text name=\"V_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(2)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=left ><input type=text name=\"D_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(8)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=right><input type=text name=\"INV_AM_"+j+"_"+i+"\" disabled value=\""+rs1.getString(3)+"\" class=\"txt_input2\"></td>");
									out.println("  <td align=right><input type=text name=\"BAL_AM_"+j+"_"+i+"\" disabled value=\""+rs1.getString(4)+"\" class=\"txt_input2\"></td>");

									m_inv_bal  = m_inv_bal+rs1.getDouble(4); 
									if(m_rec_tot<m_inv_bal){
										if(m_rec_bal>= (m_inv_bal-m_rec_tot)){
										  //out.println("111m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
										  m_alloamt = m_alloamt +(m_inv_bal-m_rec_tot);
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format((m_inv_bal-m_rec_tot))+"\" class=\"txt_input2\" onBlur=\"chk_bal('"+j+"','"+i+"')\" disabled></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    m_rec_bal = m_rec_bal-(m_inv_bal-m_rec_tot);
											m_rec_tot = m_rec_tot +(m_inv_bal-m_rec_tot);
											
										}else{
										 if(m_rec_bal>0){ 
										  //out.println("222m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
											m_alloamt = m_alloamt +m_rec_bal;
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onBlur=\"chk_bal('"+j+"','"+i+"')\" disabled></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    m_rec_tot = m_rec_tot+m_rec_bal;
											m_rec_bal = 0;
											
										 }else{
											//out.println("444m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
											m_alloamt = m_alloamt +m_rec_bal;
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onBlur=\"chk_bal('"+j+"','"+i+"')\"></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"NO\" >");
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"YES\" checked></td>"); 
									    
										 }	
										}
									}else{
									    //out.println("333m_inv_bal="); 
									    out.println("<td align=right><input type=text name=\"Text_sett_amount"+j+"_"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"chk_bal('"+j+"','"+i+"')\"></td>"); 
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"NO\">");
									    //out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+j+"_"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+j+"_"+i+",'"+j+"_"+i+"')\" value=\"NO\" ></td>"); 
									}
									
									
									out.println("  </tr>");
									//out.println("m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
									i = i+1;
									more1 = rs1.next();	
									
								 }
										out.println("<input type=hidden name=\"ba_"+j+"\" value="+nf.format(m_rec_bal)+"><input type=hidden name=\"hid_invoice_count_"+j+"\" value="+i+"></table></div>");
					      
								}else{
								  out.println("  <input type=hidden name=hid_invoice_count_"+j+"  value="+i+"></div>");
					      
								}
								  //m_rec_tot = m_rec_tot +rs.getDouble(4);
									
								    
									//out.println("<input type=hidden name=hid_invoice_count_"+j+" value=0>");
									out.println("</td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									out.println("<td>&nbsp;</TD>");
									out.println("<td colspan=5 >");
									out.println("</td>");
									out.println("</tr>");
                	j=j+1;
									
									//if(rs.getString(6).equals(m_username)){
									//  out.println("<td ><input type=button name=\"Edit_"+j+"\" value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"EDIT\"); >");
									//	out.println("<input type=button name=\"Dele_"+j+"\" value=\"Del\" class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"DELETE\"); ></td>");
									//}else{
									//}                  
              }
          //}
          
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");

				
      } 	
						
			   if (this.m_chksql.trim().equals("get_Receipt_del_new"))
              {
                String rec_no = req.getParameter("rec_no");

                this.rs = this.stmt.executeQuery(" SELECT  A.REC_NO,    A.REC_AMOUNT , A.ALLOCATED_AMOUNT,    A.BAL_TOBE_RECEIVE  ,  A.FINANCE_NO   FROM " + m_schema_name + ".AF_CO_PRO_SETTL_REC_APP_BAL A , " + m_schema_name + ".AF_CO_PRO_SETTL_RECEIPT B  " + " WHERE  A.REC_NO=B.REC_NO AND B.REC_NO='" + rec_no + "'  AND A.ALLOCATED_AMOUNT > 0 " + " AND B.STATUS NOT IN ('CAD','RET','C') ");

                this.out.println("<table class=table border='0' width='100%' >");
                this.out.println("<tr class=tr_input>");

                this.out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                this.out.println("</tr>");

                this.out.println("<tr class=pdn_txtpos2>");
                this.out.println("<td  width='15%' >Receipt No</td>");
                this.out.println("<td  width='15%' >Finance No</td>");
                this.out.println("<td  width='15%' align=right>Receipt Amount</td>");
                this.out.println("<td  width='15%' align=right>Allocated Amount</td>");
                this.out.println("<td  width='20%' align=right>Balance Amount</td>");
                this.out.println("<td  width='25%' align=right>Amount</td>");
                this.out.println("<td  width='10%'  ></td>");
                this.out.println("</tr>");

               int  j = 0;

                while (this.rs.next()) {
                  this.out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
                  this.out.println("<td style= cursor:hand; onClick=\"show_settle_receipt_drill('" + this.rs.getString(1) + "')\" ><u>" + this.rs.getString(1) + "</u><input type=hidden name=\"REC_NO_" + j + "\" value=\"" + this.rs.getString(1) + "\" ></td>");
                  this.out.println("<td align=left>" + this.rs.getString(5) + "<input type=hidden name=\"HID_FINANCE_NO_" + j + "\" value=\"" + this.rs.getString(5) + "\"></td>");
                  this.out.println("<td align=right>" + this.nf.format(this.rs.getDouble(2)) + "<input type=hidden name=\"REC_AMOUNT_" + j + "\" value=\"" + this.rs.getString(2) + "\"></td>");
                  this.out.println("<td align=right>" + this.nf.format(this.rs.getDouble(3)) + "<input type=hidden name=\"ALLO_AMOUN_" + j + "\" value=\"" + this.rs.getString(3) + "\"><input type=hidden name=\"Edit_Type" + j + "\" ></td>");
                  this.out.println("<td align=right>" + this.nf.format(this.rs.getDouble(4)) + "<input type=hidden name=\"BAL_AMOUNT_" + j + "\" value=\"" + this.rs.getString(4) + "\"></td>");
                  this.out.println("<td align=right><input type=text name=\"SETT_AMOUN_" + j + "\" value=\"0\" class=\"txt_input2\" disabled></td>");
                  this.out.println("</tr>");

                  //this.rs1 = this.stmt1.executeQuery("SELECT A.INVOICE_NO,TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'), A.INVOICED_AMOUNT,        A.SETTELED_AMOUNT,ALLOCATION_NO, A.REMARKS, A.SETTELED_AMOUNT_CURR ,B.FINANCE_NO  FROM   " + m_schema_name + ".AF_CO_PRO_INVOICE_DETAILS A, " + m_schema_name + ".AF_CO_PRO_INVOICE B " + " WHERE  RECEIPT_NO = '" + this.rs.getString(1) + "' " + " AND A.INVOICE_NO  = B.INVOICE_NO " + " AND B.FINANCE_NO  = '" + this.rs.getString(5) + "' " + " UNION " + " SELECT " + " A.INVOICE_NO,  " + " TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),  " + " A.INVOICED_AMOUNT,  " + " A.SETTELED_AMOUNT, " + " A.ALLOCATION_NO,  " + " A.REMARKS ," + " A.SETTELED_AMOUNT_CURR, " + " C.FINANCE_NO " + " FROM " + m_schema_name + ".AF_CO_PRO_INVOICE_DETAILS A," + m_schema_name + ".AF_CO_PRO_OD_INTEREST_MONTHLY B ," + m_schema_name + ".AF_CO_PRO_INVOICE C  " + " WHERE UPPER(A.RECEIPT_NO)=UPPER('" + this.rs.getString(1) + "')  " + " AND B.INVOICE_NO=C.INVOICE_NO " + " AND C.FINANCE_NO  = '" + this.rs.getString(5) + "' " + " AND A.INVOICE_NO=B.ODI_REF_NO ");
					
					
											/*added by ns on 27-07-2012*/
						this.rs1 = stmt1.executeQuery (
						" SELECT  INVOICE_NO,'',INVOICED_AMOUNT,SETTELED_AMOUNT,ALLOCATION_NO,'',SETTELED_AMOUNT_CURR,FINANCE_NO "+
						" FROM ( "+
						" SELECT  "+
						" A.INVOICE_NO,A.INVOICED_AMOUNT,  "+
						" SUM(A.SETTELED_AMOUNT) SETTELED_AMOUNT,REPLACE(ALLOCATION_NO,'U','A') ALLOCATION_NO,A.REMARKS,SUM(A.SETTELED_AMOUNT_CURR) SETTELED_AMOUNT_CURR ,B.FINANCE_NO  "+
						" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A, "+m_schema_name+".AF_CO_PRO_INVOICE B  "+
						" WHERE  RECEIPT_NO = UPPER('"+this.rs.getString(1)+"') "+
						" AND A.INVOICE_NO  = B.INVOICE_NO  "+
						" AND B.FINANCE_NO  = '"+this.rs.getString(5)+"'  "+
						" GROUP BY  A.INVOICE_NO,A.INVOICED_AMOUNT,REPLACE(ALLOCATION_NO,'U','A'),A.REMARKS,B.FINANCE_NO  "+
						
						" UNION  "+
						
						" SELECT  "+
						" A.INVOICE_NO,   "+
						" A.INVOICED_AMOUNT,    "+
						" SUM(A.SETTELED_AMOUNT) SETTELED_AMOUNT,  "+
						" REPLACE(ALLOCATION_NO,'U','A') ALLOCATION_NO  , "+
						" A.REMARKS , "+
						" SUM(A.SETTELED_AMOUNT_CURR) SETTELED_AMOUNT_CURR,  "+
						" C.FINANCE_NO  "+
						" FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY B ,"+m_schema_name+".AF_CO_PRO_INVOICE C   "+
						" WHERE A.RECEIPT_NO = UPPER('"+this.rs.getString(1)+"')   "+
						" AND B.INVOICE_NO   = C.INVOICE_NO  "+
						" AND C.FINANCE_NO   = '"+this.rs.getString(5)+"'   "+
						" AND A.INVOICE_NO   = B.ODI_REF_NO "+
						" GROUP BY  A.INVOICE_NO,A.INVOICED_AMOUNT,REPLACE(ALLOCATION_NO,'U','A'),A.REMARKS,C.FINANCE_NO  "+
						" ) XX "+
						"  WHERE XX.SETTELED_AMOUNT <>0 ");
					

                  this.out.println("<tr class=tr_input>");
                  this.out.println("<td></TD>");
                  this.out.println("<td colspan=5 ><div id='inv_" + j + "'>");

                  this.out.println("<table><tr class=pdn_txtpos2 WIDTH=100%>");
                  this.out.println("      <td WIDTH=20%>Invoice No</td>");
                  //this.out.println("      <td WIDTH=15%>Date</td>");
                  this.out.println("      <td WIDTH=15% align=right >Invoice Amount</td>");
                  this.out.println("      <td WIDTH=15% align=right >Allocated Amount</td>");
                  this.out.println("      <td WIDTH=15% align=CENTER>Status</td></tr>");
                  int k = 0;

                  while (this.rs1.next())
                  {
                    this.out.println(" <tr>");
                    this.out.println("  <td align=left ><input type=text name=\"INV_NO_" + j + "_" + k + "\" value=\"" + this.rs1.getString(1) + "\" class=\"txt_input2\" disabled><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_" + j + "_" + k + "\" VALUE=\"" + this.rs1.getString(5) + "\"></td>");
                    //this.out.println("  <td align=left ><input type=text name=\"V_DATE_" + j + "_" + k + "\" value=\"" + this.rs1.getString(2) + "\" class=\"txt_input2\" disabled></td>");
                    this.out.println("  <td align=right><input type=text name=\"INV_AM_" + j + "_" + k + "\" value=\"" + this.nf.format(this.rs1.getDouble(3)) + "\" class=\"txt_input2\" disabled></td>");
                    this.out.println("  <td align=right><input type=text name=\"Text_sett_amount" + j + "_" + k + "\" value=\"" + this.nf.format(this.rs1.getDouble(4)) + "\" class=\"txt_input2\" disabled></td>");
                    this.out.println("  <td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard" + j + "_" + k + "\" onclick=check_status_del_new(\"" + j + "\",\"" + k + "\") value=\"NO\">");
                    this.out.println("  <INPUT TYPE=HIDDEN NAME=\"ALLO_NO_" + j + "_" + k + "\" VALUE=\"" + this.rs1.getString(8) + "\">");
                    this.out.println("  <INPUT TYPE=HIDDEN NAME=\"hid_contract_no_" + j + "_" + k + "\" VALUE=\"" + this.rs1.getString(8) + "\">");
                    this.out.println(" </tr>");
                    k += 1;
                  }

                  this.out.println("<input type=hidden name=hid_invoice_count_" + j + "  value=" + k + "></table>");
                  this.out.println("</div>");
                  this.out.println("</td>");
                  this.out.println("</tr>");
                  this.out.println("<tr class=tr_input>");
                  this.out.println("<td>&nbsp;</TD>");
                  this.out.println("<td colspan=5 >");
                  this.out.println("</td>");
                  this.out.println("</tr>");
                  j += 1;
                }

                this.out.println("<tr class=tr_input>");
                this.out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");

                this.out.println("<input type=hidden name=hid_count value=" + j + "></tr></table>");
              }
					else if(m_chksql.trim().equals("get_Receipt_del")){
			
			    String m_rec_no      = req.getParameter("rec_no");
          
					rs = stmt.executeQuery (" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
																	"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE, "+
																	"	       A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK, "+
																	"	       EXCHANGE_RATE_REP_CURR,REC_AMOUNT_REP_CURR, "+
																	"	       EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+ 
																	"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
																	"	WHERE  A.REC_NO = B.REC_NO AND STATUS NOT IN ('CAD','RET','C') AND "+//<>'C' AND "+//STATUS='P' AND
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
									out.println("<td style= cursor:hand; onClick=\"show_settle_receipt_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1)+"</u><input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\" ></td>"); //add the drill down link by nuwan de silva on 18-07-07
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
					//out.println("H=window.opener.document.Form1.hid_opt_val.value;");
					//out.println("for(i=0;i<Number(document.Form1.elements['hid_inv_count'].value);i++){");
					//out.println(" alert(document.Form1.elements['Text_standard'+i].checked);");
					//out.println(" if(document.Form1.elements['Text_standard'+i].checked){");
					while(rs1.next()){
	          out.println(" <tr>");
						out.println("  <td align=left ><input type=text name=\"INV_NO_0_"+x+"\" value=\""+rs1.getString(1)+"\" class=\"txt_input2\" disabled><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_0_"+x+"\" VALUE=\""+rs1.getString(5)+"\"></td>");
						out.println("  <td align=left ><input type=text name=\"V_DATE_0_"+x+"\" value=\""+rs1.getString(2)+"\" class=\"txt_input2\" disabled></td>");
						out.println("  <td align=right><input type=text name=\"INV_AM_0_"+x+"\" value=\""+nf.format(rs1.getDouble(3))+"\" class=\"txt_input2\" disabled></td>");
						out.println("  <td align=right><input type=text name=\"Text_sett_amount0_"+x+"\" value=\""+nf.format(rs1.getDouble(4))+"\" class=\"txt_input2\" disabled></td>");
						out.println("  <td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard0_"+x+"\" onclick=check_status_del(\""+x+"\") value=\"NO\">");
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
									
									//if(rs.getString(6).equals(m_username)){
									//  out.println("<td ><input type=button name=\"Edit_"+j+"\" value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"EDIT\"); >");
									//	out.println("<input type=button name=\"Dele_"+j+"\" value=\"Del\" class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"DELETE\"); ></td>");
									//}else{
									//}                  
              }
          //}
          
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");

				
      } 	
			
			else if(m_chksql.trim().equals("Add_Min_Amount")){
			    String type   	    = req.getParameter("type");
					String amount1	    = req.getParameter("amount1");
					String amount2	    = req.getParameter("amount2");
					
					if(type.equals("min")){
					   //out.println(" SELECT '"+amount1+"'-'"+amount2+"' FROM DUAL ");
						 rs = stmt.executeQuery (" SELECT '"+amount1+"'-'"+amount2+"' FROM DUAL ");
					}else{
					   //out.println(" SELECT '"+amount1+"'+'"+amount2+"' FROM DUAL ");
					   rs = stmt.executeQuery (" SELECT '"+amount1+"'+'"+amount2+"' FROM DUAL ");
					}
					
					if(rs.next()){
					  out.println(nf.format(rs.getDouble(1)));
					}
					
			}
			/*
			else if(m_chksql.trim().equals("get_History")){
			
			    String m_deal_no	    = req.getParameter("deal_no");
					
					
					 out.println("<html>");
					out.println("<head>");
					out.println("<title>"+header_name+"</title>    ");
					out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
					out.println("</head>");
					out.println("<Script>");
					out.println("var m_bsubmit = '0';");
					out.println("var arr_assign= new Array();");
					out.println("var m_send_val= '';");
					
					out.println("function load_all_foll(m_stat,opt) {");
					out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup?chksql=get_followup\";");
	        //out.println("   window.open(m_url);");
					out.println("  setInterval('makeRequest(m_url,\"2\")',36000);");
					out.println("}");
				
	        out.println("</Script>");
					out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
					out.println("<form name=\"Form1\" method=post>");
					out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
	        out.println("<input type=hidden name=\"ROW_ID\" ></td>");
	                  
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
					out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>History</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
					out.println("</td>	");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
					
					out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
					out.println("<tr class=tr_input>");
					out.println("<td valign=top  width=100% Id=Follow_up> ");
					
					out.println("<table class=table border='0' width='100%' >");
					//out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          //out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          //out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='15%' >Followup No</td>");
					out.println("<td  width='15%' >Category</td>");
          out.println("<td  width='15%' >ID No</td>");
					out.println("<td  width='15%' >Action to be taken</td>");
					out.println("<td  width='10%' >Effective Date</td>");
					out.println("<td  width='10%' >Remarks</td>");
					out.println("<td  width='10%' >Action Taken</td>");
					out.println("<td  width='10%' >Action Date</td>");
					out.println("</tr>");

           int j = 0;      					
							rs = stmt.executeQuery (" SELECT A.FOLLOW_UP_NO, NVL(A.ID_NO,'-'), CATEGORY_NAME,"+
																			"	       TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+
																			"	       A.ENT_USER,NVL(A.ENT_REMARKS,'-'),NVL(A.ACTION_TAKEN,'-'), "+
																			"	       TO_CHAR(A.ACTION_DATE,'DD-MM-YYYY'),NVL(A.REMARKS,'-'),"+
																			"        "+m_schema_name+".AF_CO_GET_FOLLOWUP_TYPE(A.ID_NO,SCREEN_NAME)  "+
																			" FROM   "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A, "+
																			"		     "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B "+
																			"	WHERE  ACTION_SET_FOR='"+m_username+"' AND "+
																			"        ORG_FOLLOWUP_NO = (SELECT ORG_FOLLOWUP_NO "+
																			"													  FROM   "+m_schema_name+".AF_CO_PRO_FOLLOW_UP "+
																			"														WHERE  FOLLOW_UP_NO = UPPER('"+m_deal_no+"')) AND"+
																			"        CATEGORY_CODE=ACTION_TOBE_TAKEN AND "+
																			"        EFF_VAL_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MON-YYYY'),'DD-MON-YYYY') "+
																			"	ORDER  BY PRIORITY ");

              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input alt=\"Click Here to get Details\" >");
									out.println("<td >"+rs.getString(1) +"</td>");
                  out.println("<td >"+rs.getString(11)+"</td>");
                  out.println("<td >"+rs.getString(2) +"</td>");
                  out.println("<td >"+rs.getString(3) +"</td>");
                  out.println("<td >"+rs.getString(4) +"</td>");
                  out.println("<td >"+rs.getString(7) +"</td>");
									out.println("<td >"+rs.getString(8) +"</td>");
									out.println("<td >"+rs.getString(9) +"</td>");
									
									out.println("     ");
									//out.println("</tr>");
                	j=j+1;
									
									//if(rs.getString(6).equals(m_username)){
									//  out.println("<td ><input type=button name=\"Edit_"+j+"\" value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"EDIT\"); >");
									//	out.println("<input type=button name=\"Dele_"+j+"\" value=\"Del\" class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"DELETE\"); ></td>");
									//}else{
									//}                  
              }
          //}
          
					
					//out.println("<tr class=tr_input>");
          //out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("</tr></table>");


					
					out.println("</td>");
			
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("</html>");
					
					
					
					
									
      } */	
		}
		catch (Exception e) {
			e.printStackTrace();
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
