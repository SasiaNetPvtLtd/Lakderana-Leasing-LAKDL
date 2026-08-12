//Option Id is 1.65 
//This File was created by SVA on 28-08-2006 
//1.65 Allocate Unallocated Receipt Process Display
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
//import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;

import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_TerminationSchedule2 extends javax.servlet.http.HttpServlet {
	
	// commented by udara 30-05-2017
	/*
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2,rs3;
	public String m_chksql;
	ServletOutputStream out = null;
	*/
	
	public /*synchronized*/ void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		// added by udara 30-05-2017
		Connection conn =null;
		Statement stmt=null,stmt1=null,stmt2=null,stmt3=null;
		java.text.NumberFormat nf=null,nf1=null;
		ResultSet rs=null,rs1=null,rs2=null,rs3=null;
		String m_chksql=null;
		ServletOutputStream out = null;
		
		
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
			String m_dd="";
			String m_mm="";
			String m_yy="";
			//out.println("conn="+conn);
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
			CallableStatement callstmt1 =null;
			
			
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
			nf.setMinimumFractionDigits(0);
			nf.setMaximumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
			nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			stmt1= conn.createStatement ();
			stmt3= conn.createStatement ();
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
				out.println("         assign_div();");
				out.println("      }else if(opt==\"7\"){");
				out.println("         veh.innerHTML=http_request.responseText; ");
				//Added by Dineth on 2008-12-03
				out.println("         var no_veh=document.Form1.hid_vcount.value;");
				out.println("         if(parseInt(no_veh)>1){ ");
				out.println("             document.Form1.BUT_ARREARS.disabled=false;");
				out.println("            }");
				//End by Dineth on 2008-12-03
				out.println("         get_term_details();");
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
				
				out.println("      } ");
				
				
				// added by udara 06-02-2014
				out.println(" else if(opt==\"10\"){");
				
				out.println("   	data_vec= new Array(); ");
				out.println("       var xmlbody=http_request.responseXML.documentElement;");
				out.println("       var vsize=0;");
				out.println("  		for(var i=0;i<xmlbody.childNodes.length;i++){");
				out.println("    		for(var j=0;j<xmlbody.childNodes[i].childNodes.length;j++){");
				out.println("      			data_vec[vsize]=xmlbody.childNodes[i].childNodes[j].text;");
				out.println("       		vsize++;");
				out.println("    		}");
				out.println("   	}");
				
				//out.println("       alert(data_vec[0] +' - '+data_vec[1]);   ");
				/*
				out.println("  if(document.Form1.TERM_TYPE.value=='NOR_TER'){ ");  
				out.println("      if(data_vec[0]=='FALSE'){ ");
				out.println("        alert('Termination date should be last rental date'); ");
				out.println("      }");
				out.println("      else if(data_vec[1]=='FALSE'){ ");
				out.println("        alert('Termination validity date should be last rental date'); ");
				out.println("      }");
				out.println("      else{ ");
				out.println("        befor_submit(); ");save_check
				out.println("      }");
			    out.println("  }");
			    out.println("  else{"); 
				out.println("        befor_submit(); ");
				out.println("  }");
				*/
				
				out.println("      save_check(data_vec);"); // added by udara on 11-02-2014
				
				
				out.println(" } ");
				// end by udara 06-02-2014
				
				
				out.println(" else if(opt==\"5\"){");
				//out.println("         alert(http_request.responseText);");
				out.println("         rec.innerHTML=http_request.responseText; ");
				out.println("      }");
				
				
				// added by udara 06-01-2021
				out.println(" else if(opt==\"77\"){");
				out.println("         var respondValue = http_request.responseText; ");
				out.println("         var respondValueArry = respondValue.split('-'); ");
				out.println("  		var obj = document.getElementById('FUTURE_DEBIT'); ");
				out.println(" 			if(respondValueArry[0]=='Yes'){ ");
				out.println("  	 		document.getElementById('moratorium').innerHTML = '<b>Moratorium - Yes</b>'"); 
				out.println(" 			}else{ ");
				out.println("  		 	document.getElementById('moratorium').innerHTML = '<b>Moratorium - No</b>'");
				out.println(" 		 	}");
 
				out.println("  	document.getElementById('FUTURE_DEBIT').value = respondValueArry[1];"); 
				out.println("  	check_number_decimal(obj,21);"); 
				out.println(" }");
				// end by udara 06-01-2021
				
				out.println("else if(opt==\"4\"){");
				
				//out.println("         alert(http_request.responseText);");
				out.println("   				data_vec= new Array(); ");
				out.println("           var xmlbody=http_request.responseXML.documentElement;");
				out.println("           var vsize=0;");
				out.println("  				  for(var i=0;i<xmlbody.childNodes.length;i++){");
				out.println("    				 for(var j=0;j<xmlbody.childNodes[i].childNodes.length;j++){");
				//out.println("							alert(xmlbody.childNodes[i].childNodes[j].text)");
				out.println("      			  data_vec[vsize]=xmlbody.childNodes[i].childNodes[j].text;");
				out.println("       			vsize++;");
				//alert(data_vec[vsize]);
				out.println("    				}");
				out.println("   		   }");
				
				out.println("          addrow(data_vec,type);");
				
				out.println("      }");
				out.println("    } else {");
				//out.println("        load_followup.innerHTML='';");
				out.println("    }");
				out.println(" }");
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
				//out.println("     get_Receipt();");
				out.println("   }else if(type=='Veh'){"); 
				//out.println("     document.Form1.VEHICLE_NO.value      =data[0];"); 
				out.println("     document.Form1.LEASE_NO.value        =data[0];"); 
				out.println("     document.Form1.APPLICATION_NO.value  =data[1];"); 
				out.println("     document.Form1.CLIENT_CODE.value     =data[2];"); 
				out.println("     check_due_inv();");
				out.println("   }else if(type=='Lea'){"); 
				out.println("     document.Form1.LEASE_NO.value        =data[3];"); 
				out.println("     document.Form1.APPLICATION_NO.value  =data[0];"); 
				out.println("     document.Form1.CLIENT_CODE.value     =data[1];"); 
				out.println("     document.Form1.TRN_TYPE.value        =data[4];"); 
				out.println("     document.Form1.ODI.value             =data[12];"); 
				out.println("     document.Form1.ODI_NET.value         =data[12];"); 	
				out.println("     document.Form1.Unallo_Rec.value      =data[13];"); 
				
				out.println("     document.Form1.LEASE_RATE.value      =data[14];"); 
				out.println("     document.Form1.DUE_AMOUNT.value      =data[15];"); 
				out.println("     document.Form1.DUE_NET.value         =data[16];"); 	
				out.println("     document.Form1.DUE_VAT.value         =data[17];"); 
				
				
				
				
				out.println("     get_due_rent_sum();");
				//out.println("     check_due_inv();");
				out.println("   }else if(type=='ODI_NET'){"); 
				out.println("     document.Form1.ODI_NET.value         =data[0];"); 	
				
				out.println("   }else if(type=='DUE'){"); 
				out.println("     document.Form1.DUE_AMOUNT.value      =data[0];"); 
				out.println("     document.Form1.DUE_NET.value         =data[1];"); 	
				out.println("     document.Form1.DUE_VAT.value         =data[2];"); 	
				out.println("     check_lease_rate();");
				out.println("   }else if(type=='IRR'){"); 
				out.println("	   document.Form1.CLOSURE_IRR.value     =data[0];");
				out.println("   }else if(type=='CAP'){");//       CAP_SETT   CAP_SETT_PER
				out.println("     document.Form1.AMOUNT_FINANCE.value  =data[0];"); 
				out.println("     document.Form1.NIBSM.value           =data[1];"); 
				out.println("     document.Form1.AMI.value             =data[2];"); 
				out.println("     document.Form1.CAP_OUT.value         =data[3];"); 
				out.println("     document.Form1.CAP_OUT_PER.value     =data[5];"); 
				out.println("     document.Form1.VAT_PER.value         =data[6];"); 
				
				out.println("   }else if(type=='TCOUNT'){");
				out.println("     document.Form1.TER_COUNT.value       =data[0];"); 
				out.println("     if(document.Form1.TER_COUNT.value>'0'){");
				out.println("     	 check_term_char();");
				out.println("     }else{ ");
				out.println("       document.Form1.TERM_AMOUNT.value       ='0'; ");
				out.println("       get_term_vehicles();");
				//out.println("       check_due_inv();");
				out.println("     } ");
				out.println("   }else if(type=='TCHAR'){"); 
				out.println("     document.Form1.TERM_AMOUNT.value      =data[0];");
				out.println("     get_term_vehicles();");
				out.println("   }else if(type=='LEASERATE'){"); 
				out.println("     document.Form1.LEASE_RATE.value       =data[0];"); 
				out.println("     check_client();");
				out.println("   }else if(type=='TERATE'){"); 
				out.println("   }else if(type=='LERATE'){"); 
				out.println("     document.Form1.LEASE_RATE.value       =data[0];"); 
				out.println("     get_due_rent_sum();");
				out.println("   }else if(type=='DUER'){"); 
				out.println("     document.Form1.DUE_RENTALS.value       =data[0];"); 
				out.println("     document.Form1.DUE_RENTALS_NET.value   =data[1];"); 
				out.println("     document.Form1.DUE_RENTALS_VAT.value   =data[2];"); 
				
				out.println("     document.Form1.TER_COUNT.value       =data[3];"); 
				out.println("       document.Form1.TERM_AMOUNT.value   =data[4]; ");
				out.println("       get_term_vehicles();");
				
				//out.println("     check_term_count();");
				out.println("       setTimeout(function(){ getMoratoriumValues(); }, 500);  "); // added by udara 06-01-2021
				
				out.println("   }else if(type=='TermNo'){"); 
				out.println("     document.Form1.TERMINATION_NO.value   =data[0];"); 
				out.println("     document.Form1.LEASE_NO.value         =data[1];"); 
				out.println("     document.Form1.APPLICATION_NO.value   =data[2];"); 
				out.println("     document.Form1.CLIENT_CODE.value      =data[3];"); 
				//out.println("     document.Form1.LEASE_RATE.value       =data[4];"); 
				//out.println("     document.Form1.LEASE_RATE.value       =data[5];"); 
				out.println("     document.Form1.REQ_BY.value           =data[6];"); 
				out.println("     document.Form1.TER_RATE.value         =data[7];"); 
				//out.println("     document.Form1.TERM_AMOUNT.value      =data[8];"); 
				out.println("     document.Form1.REMARK.value           =data[9];"); 
				out.println("     document.Form1.TERM_AMOUNT.value      =data[10];"); 
				out.println("     document.Form1.TER_COUNT.value        =data[11];");
				out.println("     document.Form1.DUE_AMOUNT.value       =data[12];");
				out.println("     document.Form1.hid_cal_date.value='3';");
				out.println("     load_c_date(data[4]);");
				out.println("     document.Form1.hid_cal_date.value='2';");
				out.println("     load_c_date(data[5]);");
				out.println("     befor_cal();	");
				
				out.println("   }"); 
				
				
				out.println(" }else{");
				out.println("  if(type=='Cli'){"); 
				out.println("   client_help();"); //Added by Chandana on 06/08/2007 for Ref no.759  
				out.println("  }");	
				out.println("}");	
				out.println("}");
				//End Of Checking Values
				
				
				
				out.println("function assign_div(){");
				
				
				
				out.println("var due_amount=document.Form1.DUE_AMOUNT.value;");
				out.println("var due_rent=document.Form1.DUE_RENTALS.value;");
				out.println("var odi_amount=document.Form1.ODI.value;");
				out.println("var sale_price=format_noobject(parseFloat(unformat_noobject(document.Form1.hid_sg_val.value)));");//stk
				out.println("var agreemnt_no=document.Form1.LEASE_NO.value;");
				out.println("var client=document.Form1.CLIENT_CODE.value;");
				out.println("var tot_chrg=format_noobject(parseFloat(unformat_noobject(due_amount))+parseFloat(unformat_noobject(due_rent))+parseFloat(unformat_noobject(odi_amount))+parseFloat(unformat_noobject(document.Form1.hid_sg_val.value)));");
				//out.println(" alert(ter_chrg)");
				//out.println("	M4.innerHTML='<input type=button name=cash_f  value=\"Letter\" class=mainbut onclick=Generate_Letter(\"+due_amount+\",\"+due_rent+\",\"+sale_price+\")>';");
				//	\"'+term_type+'\"
				out.println("	M4.innerHTML='<input type=button name=cash_f  value=\"Letter\" class=mainbut onclick=Generate_Letter(\"'+due_amount+'\",\"'+due_rent+'\",\"'+odi_amount+'\",\"'+sale_price+'\",\"'+tot_chrg+'\",\"'+client+'\",\"'+agreemnt_no+'\")>';");
				
				out.println(" rent.innerHTML=document.Form1.h_rent.value;"); 
				out.println("	term.innerHTML=document.Form1.h_term.value;"); 
				out.println(" rpv.innerHTML =document.Form1.h_rpv.value;"); 
				out.println("	tpv.innerHTML =document.Form1.h_tpv.value;");
				out.println("	gtv.innerHTML =document.Form1.h_gtv.value;");
				
				out.println(" H1.innerHTML='';"); 
				out.println("	H2.innerHTML='Net Amount';"); 
				out.println(" H3.innerHTML='VAT Amount';"); 
				out.println("	H4.innerHTML='Total Amount';");
				
				out.println(" S11.innerHTML='Due Amount';"); 
				out.println("	S12.innerHTML=document.Form1.DUE_NET.value;"); 
				out.println(" S13.innerHTML=document.Form1.DUE_VAT.value;"); 
				out.println("	S14.innerHTML=document.Form1.DUE_AMOUNT.value;");
				
				// commented below by udara on 12-12-2012
				/*
				out.println(" S21.innerHTML='Rentals Due up to Termination Date';"); 
			  out.println("	S22.innerHTML=document.Form1.DUE_RENTALS_NET.value;"); 
			  out.println(" S23.innerHTML=document.Form1.DUE_RENTALS_VAT.value;"); 
			  out.println("	S24.innerHTML=document.Form1.DUE_RENTALS.value;");
				*/
				
				out.println(" S31.innerHTML='Termination Calculation Amount';"); 
				out.println("	S32.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.h_term.value)));"); 
				out.println(" S33.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.h_vat.value)));"); 
				out.println("	S34.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.h_gtv.value)));");
				//Modified by Dineth on 2008-10-24
				//out.println(" S41.innerHTML='Residual Amount';");
				out.println(" S41.innerHTML='Sales Price';");
				out.println("	S42.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.hid_sn_val.value)));"); 
				out.println(" S43.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));"); 
				out.println("	S44.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.hid_sg_val.value)));");
				//out.println("alert(document.Form1.T_V.value);");
				out.println(" S51.innerHTML='Termination Charges';");
				
				out.println("	S52.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value)));"); 
				out.println(" S53.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.T_V.value)));"); 
				out.println("	S54.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.T_S.value)));");
				
				out.println(" S61.innerHTML='ODI';"); 
				out.println("	S62.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value)));"); 
				out.println(" S63.innerHTML=format_noobject(parseFloat(unformat_noobject('0.00')));"); 
				out.println("	S64.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value)));");
				
				out.println(" T1.innerHTML='Total Amount';"); //parseFloat(unformat_noobject(document.Form1.DUE_VAT.value))   ++parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_VAT.value))++ parseFloat(unformat_noobject(document.Form1.hid_sv_val.value))
				
				//out.println("	T2.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.h_term.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value)));"); 
				//out.println(" T3.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.T_V.value))+parseFloat(unformat_noobject(document.Form1.h_vat.value))  + parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));"); 
				//out.println("	T4.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value)));");
				
				out.println(" T2.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value)));"); 
				out.println(" T3.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.T_V.value))+parseFloat(unformat_noobject(document.Form1.DUE_VAT.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_VAT.value))+parseFloat(unformat_noobject(document.Form1.h_vat.value))  + parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));"); 
				//added by disnaka 2012-05-16
				out.println(" var num=parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.DUE_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_RENTALS.value))    +parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value));");
				out.println(" T4.innerHTML=format_noobject(Math.round(num*Math.pow(10,2))/Math.pow(10,2));");
				
				//Added by Dineth on 2008-10-01
				out.println(" S65.innerHTML='Unallocated Receipt';"); 
				out.println("	S66.innerHTML='('+format_noobject(parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)))+')';"); 
				out.println(" S67.innerHTML='('+format_noobject(parseFloat(unformat_noobject('0.00')))+')';"); 
				out.println("	S68.innerHTML='('+format_noobject(parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)))+')';");
				
				/*out.println(" T30.innerHTML='Net Total Amount';");
				out.println(" if(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.h_term.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value))<0){");
			  out.println("	T31.innerHTML='('+format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.h_term.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)))+')';"); 
			  out.println(" T32.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.T_V.value))+parseFloat(unformat_noobject(document.Form1.h_vat.value))  + parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));"); 
			  out.println("	T33.innerHTML='('+format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)))+')';");
				out.println(" } ");
				out.println(" else{ ");
				out.println("	T31.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.h_term.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)));"); 
			  out.println(" T32.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.T_V.value))+parseFloat(unformat_noobject(document.Form1.h_vat.value))  + parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));"); 
			  out.println("	T33.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)));");
				out.println(" } ");
				//End by Dineth on 2008-10-01
				*/
				
				out.println(" T30.innerHTML='Net Total Amount';");
				out.println(" if(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value))<0){");
				out.println("	T31.innerHTML='('+format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)))+')';"); 
				out.println(" T32.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.T_V.value))+parseFloat(unformat_noobject(document.Form1.DUE_VAT.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_VAT.value))+parseFloat(unformat_noobject(document.Form1.h_vat.value))  + parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));"); 
				//out.println("	T33.innerHTML='('+format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.DUE_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_RENTALS.value))    +parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)))+')';");
				//added by disnaka 2012-05-16
				out.println(" var num=parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.DUE_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_RENTALS.value))    +parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value));");
				out.println("	T33.innerHTML='('+format_noobject(Math.round(num*Math.pow(10,2))/Math.pow(10,2))+')';");
				out.println("  bal_amount = format_noobject(Math.round(num*Math.pow(10,2))/Math.pow(10,2));"); //disnaka
				out.println(" } ");
				out.println(" else{ ");
				out.println("	T31.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)));"); 
				out.println(" T32.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.T_V.value))+parseFloat(unformat_noobject(document.Form1.DUE_VAT.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_VAT.value))+parseFloat(unformat_noobject(document.Form1.h_vat.value))  + parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));"); 
				//out.println("	T33.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.DUE_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_RENTALS.value))    +parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)));");
				//added by disnaka 2012-05-16
				out.println("	var num=parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.DUE_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_RENTALS.value))    +parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value));");
				out.println("	T33.innerHTML=format_noobject(Math.round(num*Math.pow(10,2))/Math.pow(10,2));");
				out.println("  bal_amount = format_noobject(Math.round(num*Math.pow(10,2))/Math.pow(10,2));"); 				
				out.println(" } ");
				
				
				out.println(" R1.innerHTML='No of Future Rentals';"); 
				out.println("	R2.innerHTML=document.Form1.F_R.value;"); 
				out.println(" R3.innerHTML='No of Rentals Paid';"); 
				out.println("	R4.innerHTML=document.Form1.R_P.value;");
				
				out.println(" R5.innerHTML='No of Rentals Arrears';"); 
				out.println("	R6.innerHTML=document.Form1.R_A.value;");
				
				out.println("	R13.innerHTML='Total';");
				out.println("	R14.innerHTML=document.Form1.R_T.value;");
				
				out.println("	R7.innerHTML='Termination Gain / Loss';");
				out.println("	R8.innerHTML=document.Form1.h_tpv.value;");
				
				
				//out.println("	M4.innerHTML='<input type=button name=cash_f  value=\"Letter\" class=mainbut onclick=befor_cal(\"YES\",\"YES\")>';"); //Added By Susitha 
				//Added By Susitha 
				
				//out.println("	document.Form1.CLOSURE_IRR.value=document.Form1.C_IRR.value;"); 
				
				
				//out.println(" alert(unformat_noobject(document.Form1.h_term.value)+'<'+unformat_noobject(document.Form1.h_rpv.value));");
				out.println(" if(parseFloat(unformat_noobject(document.Form1.h_term.value))<parseFloat(unformat_noobject(document.Form1.h_rpv.value))){");
				out.println("   document.Form1.b_submit.disabled   = true;");
				out.println("   document.Form1.b_submit_1.disabled = true;");
				out.println("   alert('Please enter correct termination rate and continue.');");
				out.println(" }else{");
				out.println("   document.Form1.b_submit.disabled   = false;");
				out.println("   document.Form1.b_submit_1.disabled = false;");
				out.println(" }");
				//out.println("	check_leaserate();");
				out.println("	get_CLOSURE_IRR();");
				out.println("   document.Form1.cal.disabled = false;"); // added by udara 29-01-2019
				out.println("   document.Form1.BUT_CLS.disabled = false;    "); // added by udara 07-02-2019
				out.println("}");		
				
				
				out.println("function inv_help(num){");
				out.println(" document.Form1.hid_opt_val.value=num;"); 
				out.println("	popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule?chksql=get_Invoice&client=\"+document.Form1.CLIENT_CODE.value+\"\", \"oBj\",\"left=130,top=200,width=750,height=400\");"); 
				out.println("}");		
				
				
				out.println("function cal_amount(opt,am1,am2,num) {");//
				out.println("   document.Form1.hid_win_opt.value=num;");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'3');");
				
				out.println("}");	
				
				out.println("function cal_amount_del(opt,am1,am2,num) {");//
				out.println("   document.Form1.hid_win_opt.value=num;");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'6');");
				
				out.println("}");	
				
				out.println("function get_term_vehicles() {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule?chksql=get_Vehicles&Lease_no=\"+document.Form1.LEASE_NO.value;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'7');");
				out.println("}");
				
				
				
				
				/*out.println("function get_Receipt(m_stat,opt) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule?chksql=get_Receipt&client=\"+document.Form1.CLIENT_CODE.value+\"\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'2');");
				
        out.println("}");	
				
				out.println("function get_Receipt_del(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule?chksql=get_Receipt_del&rec_no=\"+val+\"\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'5');");
				
        out.println("}");	
				
				out.println("function check_receipt(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_XMLFile?chksql=get_rec_no&rec_no=\"+document.Form1.RECEPT_NO.value+\"\";");
        //out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','Rec');");
				
        out.println("}");
				*/
				
				
				// added by udara 06-01-2021
				out.println("function getMoratoriumValues() {");
				out.println("   m_url=\"" + m_servlet_client_url + ":" + m_client_t3_port + "/" + m_client_name + "AF_CR_TerminationSchedule2?chksql=getMoratoriumValues&Lease_no=\"+document.Form1.LEASE_NO.value;");
				out.println("   makeRequest(m_url,'77');");
				out.println("}");
				// end by udara 06-01-2021
				
				out.println("function get_CLOSURE_IRR() {");
				//out.println("alert('ok');");
				out.println("   m_amt=parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value));");
				//out.println("alert(m_amt);");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_IRR&finance_no=\"+document.Form1.LEASE_NO.value+\"&term_amt=\"+m_amt+\"&odi_amt=\"+unformat_noobject(document.Form1.ODI_NET.value)+\"&tdate=\"+document.Form1.TER_DAY.value+\"-\"+document.Form1.TER_MONTH.value+\"-\"+document.Form1.TER_YEAR.value+\"&unallo_amt=\"+unformat_noobject(document.Form1.Unallo_Rec.value);");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','IRR');");
				out.println("}");
				
				
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
				
				out.println("function build_veh_no(val) {");
				out.println("  m_v_no=\"\";");
				out.println("  m_c_no=\"\";");
				out.println("  m_i_no=\"\";");
				out.println("for(i=0;i<parseFloat(document.Form1.hid_vcount.value);i++){");    
				out.println(" if(document.Form1.elements['ch_v_'+i].checked){");
				out.println("  m_v_no=document.Form1.elements['VEHICLE_NO_'+i].value;");    
				out.println("  m_c_no=m_c_no+document.Form1.elements['CHASSIS_NO_'+i].value+\"@\";");
				out.println("  m_i_no=m_i_no+document.Form1.elements['INVOICE_NO_'+i].value+\"@\";");
				//out.println("  m_v_count=m_v_count+1;");    
				out.println(" }");    
				out.println("}");    
				//out.println("if(parseFloat(document.Form1.hid_vcount.value)==m_v_count){");
				//out.println(" document.Form1.VEHICLE_NO.value=\"\";");    
				//out.println("}else{");
				out.println(" document.Form1.VEHICLE_NO.value=m_v_no;");    
				out.println(" document.Form1.CHASSIS_NO.value=m_c_no;");    
				out.println(" document.Form1.INVOICE_NO.value=m_i_no;");    
				out.println("}");
				
				
				out.println("function get_term_details(val) {");
				out.println("  m_v_no=\"\";");
				out.println("  m_c_no=\"\";");
				out.println("  m_i_no=\"\";");
				out.println("for(i=0;i<parseFloat(document.Form1.hid_vcount.value);i++){");    
				out.println(" if(document.Form1.elements['ch_v_'+i].checked){");
				out.println("  m_v_no=m_v_no+document.Form1.elements['VEHICLE_NO_'+i].value+\"@\";");    
				out.println("  m_c_no=m_c_no+document.Form1.elements['CHASSIS_NO_'+i].value+\"@\";");
				out.println("  m_i_no=m_i_no+document.Form1.elements['INVOICE_NO_'+i].value+\"@\";");
				//out.println("  m_v_count=m_v_count+1;");    
				out.println(" }");    
				out.println("}");    
				//out.println("if(parseFloat(document.Form1.hid_vcount.value)==m_v_count){");
				//out.println(" document.Form1.VEHICLE_NO.value=\"\";");    
				//out.println("}else{");
				out.println(" document.Form1.VEHICLE_NO.value=m_v_no;");    
				out.println(" document.Form1.CHASSIS_NO.value=m_c_no;");    
				out.println(" document.Form1.INVOICE_NO.value=m_i_no;");    
				//out.println("}");    
				out.println(" if(document.Form1.INVOICE_NO.value!=\"\" && document.Form1.LEASE_NO.value!=\"\"){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_term_details&finance_no=\"+document.Form1.LEASE_NO.value+\"&veh_no=\"+document.Form1.VEHICLE_NO.value+\"&chas_no=\"+document.Form1.CHASSIS_NO.value+\"&invo_no=\"+document.Form1.INVOICE_NO.value;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','CAP');");
				out.println(" }else{");
				//out.println("   alert('Please enter termination date.')");
				out.println(" }");
				out.println("}");
				
				
				
				out.println("function check_lease_rate() {");
				out.println("  if(document.Form1.LEASE_NO.value!=''){");
				out.println("   build_veh_no();");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_lease_rate&finance_no=\"+document.Form1.LEASE_NO.value+\"&veh_no=\"+document.Form1.VEHICLE_NO.value+\"&client_code=\"+document.Form1.CLIENT_CODE.value;");
				// out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','LERATE');");
				out.println("  }"); 
				out.println("}");
				
				out.println("function check_ODI_ADJ(val) {");
				out.println("  if(document.Form1.ODI.value!='' && document.Form1.ODI_ADJ.value!=''){");
				out.println("  if(parseFloat(unformat_noobject(document.Form1.ODI.value))>=parseFloat(unformat_noobject(document.Form1.ODI_ADJ.value))){");
				out.println("   m_url=\"" +m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+ "AF_CR_XMLFile?chksql=get_ODI_NET&ODI=\"+parseFloat(unformat_noobject(document.Form1.ODI.value))+\"&ODI_ADJ=\"+parseFloat(unformat_noobject(document.Form1.ODI_ADJ.value));");
				out.println("   makeRequest(m_url,'4','ODI_NET');");
				out.println("  }else{");
				out.println("   alert('Please check the ODI Adjustment amount.');");
				out.println("  }");
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
				//out.println("   makeRequest(m_url,'4','TERATE');");
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
				
				out.println("function check_lease(val) {");
				out.println("  if(document.Form1.LEASE_NO.value!='' && (document.Form1.TER_DAY.value+\"-\"+document.Form1.TER_MONTH.value+\"-\"+document.Form1.TER_YEAR.value!='--')){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_Finance_no&finance_no=\"+document.Form1.LEASE_NO.value+\"&client_code=\"+document.Form1.CLIENT_CODE.value+\"&m_date=\"+document.Form1.TER_DAY.value+\"-\"+document.Form1.TER_MONTH.value+\"-\"+document.Form1.TER_YEAR.value;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','Lea');");
				
				//out.println("get_CLOSURE_IRR();"); // commented by udara 26-07-2017 since the other version doesn't contain this function
				
				out.println("  }");
				out.println("}");
				
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
				
				
				out.println("function check_client(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_client_code&client_code=\"+document.Form1.CLIENT_CODE.value+\"\";");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','Cli');");
				
				out.println("}");
				
				// added by udara 06-02-2014
				out.println("function check_termi_date(val) {");
				out.println("   var m_termi_date = document.Form1.TER_DAY.value+'-'+document.Form1.TER_MONTH.value+'-'+document.Form1.TER_YEAR.value;  ");
				out.println("   var m_termi_valid_date = document.Form1.TER_V_DAY.value+'-'+document.Form1.TER_V_MONTH.value+'-'+document.Form1.TER_V_YEAR.value;  ");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=check_termi_date&finance_no=\"+document.Form1.LEASE_NO.value+\"&termi_date=\"+m_termi_date+\"&termi_valid_date=\"+m_termi_valid_date;");
				out.println("   makeRequest(m_url,'10','TermiChk');");
				out.println("}");
				// end by udara on 06-02-2014
				
				// added by udara 11-02-2014
				out.println("function save_check(data) {");
				out.println("   if(data.length>0){ ");
				out.println("  		if(document.Form1.TERM_TYPE.value=='NOR_TER'){ ");  
				
				// commented by udara 11-01-2018
				/*
				out.println("      		if(data_vec[0]=='FALSE'){ ");
				out.println("        		alert('Termination date should be last rental date'); ");
				out.println("      		}");
				out.println("      		else if(data_vec[1]=='FALSE'){ ");
				out.println("        		alert('Termination validity date should be last rental date'); ");
				out.println("      		}");
				out.println("      		else{ ");
				out.println("        		befor_submit(); ");
				out.println("      		}");
				*/
				
				out.println("        		befor_submit(); "); // added by udara 11-01-2018
				
				out.println("  		}");
				
				
				// commented by udara 10-04-2014
				
				// added by udara 03-03-2014
				
				out.println("  		else if(document.Form1.TERM_TYPE.value=='ERL_TER'){ ");  
				
				// commented since unnecessary alerts under issue No JB20112017-01748 on 05-01-2019, since termination date should be current date
				/*
				out.println("      		if(data_vec[2]=='FALSE'){ ");
				out.println("        		alert('Termination date should be rental date'); ");
				out.println("      		}");
				out.println("      		else if(data_vec[2]=='FALSE'){ ");
				out.println("        		alert('Termination validity date should be rental date'); ");
				out.println("      		}");
				out.println("      		else{ ");
				out.println("        		befor_submit(); ");
				out.println("      		}");
				*/
				
				out.println("        		befor_submit(); ");
				
				out.println("  		}");
				
				// end by udara 03-03-2014
				
				
				
				// added by udara 10-04-2014
				/*
				out.println("  		else if(document.Form1.TERM_TYPE.value=='ERL_TER'){ ");  
				
				out.println("       	if(data_vec[4]=='FALSE'){ ");
				out.println("        		alert('Termination date should be the max invoice due date'); ");
				out.println("  			}");
				
				out.println("  			else{");
				
				
				out.println("       		if(data_vec[5]=='FALSE'){ ");
				out.println("        			alert('Termination validity date should be the max invoice due date'); ");
				out.println("  				}");
				
				out.println("  			    else{");
				//
				out.println("      				if(data_vec[2]=='FALSE'){ ");
				out.println("        				alert('Termination date should be rental date'); ");
				out.println("      				}");
				out.println("      				else if(data_vec[3]=='FALSE'){ ");
				out.println("        				alert('Termination validity date should be rental date'); ");
				out.println("      				}");
				out.println("      				else{ ");
				out.println("        				befor_submit(); ");
				out.println("      				}");
				//
				out.println("        				befor_submit(); ");
				
				out.println("      			}");
				
				out.println("  			}");
				
				
				out.println("  		}");
				*/
				// end by udara 10-04-2014
				
				
				
				
				out.println("  		else{"); 
				out.println("        		befor_submit(); ");
				out.println("  		}");
				out.println("   }");
				out.println("   else{"); 
				out.println("        befor_submit(); ");
				out.println("   }");
				out.println("}");
				// added by udara 11-02-2014
				
				
				out.println("function cal_ter(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_ter&vat=15&ter_char=\"+document.Form1.TERM_AMOUNT.value+\"\";");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','TER_VAL');");
				
				out.println("}");
				
				out.println("function load_all_foll(m_stat,opt) {");
				//out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup?chksql=get_followup&fno="+m_Followu_no+"\";");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,opt);");
				out.println("}");
				
				out.println("function validate_data(){"); 
				out.println("m_sub=0;"); 
				
				out.println("if(document.Form1.hid_option.value==\"NEW\"){"); 
				out.println("if(document.Form1.LEASE_NO.value==\"\"){  "); 
				out.println("FNO.style.color='red';");
				out.println("m_sub = 1;;"); 
				out.println("}"); 
				out.println("if(document.Form1.CLIENT_CODE.value==\"\"){  "); 
				out.println("CLC.style.color='red';");
				out.println("m_sub = 1;;"); 
				out.println("}"); 
				out.println("if(document.Form1.TER_RATE.value==\"\"){  "); 
				out.println("TMR.style.color='red';");
				out.println("m_sub = 1;;"); 
				out.println("}");
				out.println("if(document.Form1.TER_V_YEAR.value==\"\" || document.Form1.TER_V_MONTH.value==\"\" || document.Form1.TER_V_DAY.value==\"\"){  "); 
				out.println("TMV.style.color='red';");
				out.println("m_sub = 1;;"); 
				out.println("}");
				out.println("if(document.Form1.TER_DAY.value==\"\" || document.Form1.TER_MONTH.value==\"\" || document.Form1.TER_YEAR.value==\"\"){  "); 
				out.println("TMD.style.color='red';");
				out.println("m_sub = 1;;"); 
				out.println("}");
				
				// added by udara 10-10-2018
				out.println("if(document.Form1.HID_PLEDGE_STATUS.value=='YES'){");
				out.println(" alert('This contract has been pledged'); ");
				out.println(" m_sub = 1;"); 
				out.println("}");
				// end by udara 10-10-2018
				
				//[COMMENTED BY MILINDA ON 05-10-2020 FOR JB31082020-11650]
				// added by udara 24-08-2017
				/*out.println(" if((document.Form1.TERM_TYPE.value=='BAL_TRANSF')&&(unformat_noobject(document.Form1.DUE_AMOUNT.value)<unformat_noobject(document.Form1.Unallo_Rec.value))){ ");
				out.println(" alert('Excess Balance Amount Exist'); ");
				out.println("m_sub = 1;;"); 
				out.println(" }");*/
				
				out.println(" if((document.Form1.TERM_TYPE.value=='NOR_TER')&&(unformat_noobject(bal_amount)!=0)){ "); //
				out.println(" alert('Balance amount should be 0.00 for normal termination.'); ");
				out.println("m_sub = 1;;"); 
				out.println(" }");
				
				out.println(" if((document.Form1.TERM_TYPE.value=='ERL_TER')&&(unformat_noobject(bal_amount)!=0)){ "); //
				out.println(" alert('Balance amount should be 0.00 for early termination.'); ");
				out.println(" m_sub = 1;;"); 
				out.println(" }");				
				// end by udara 24-08-2017
				
				//[Added by milinda for termination charge check on 20-12-2017]
				out.println(" if(parseFloat(unformat_noobject(document.Form1.T_S.value))!= parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))){ ");
				out.println(" alert('Termination charge amounts does not tally with calculated charge amount!'); ");
				out.println("m_sub = 1;;"); 
				out.println(" }");
				//[]
				
				out.println("}else {"); 
				
				out.println("if(document.Form1.TERMINATION_NO.value==\"\"){  "); 
				out.println("TMN.style.color='red';");
				out.println("m_sub = 1;"); 
				out.println("}"); 
				out.println("}");
				
				
				
				out.println(" if(document.Form1.hid_count.value==\"0\" && (document.Form1.TERM_TYPE.value!=\"RESCHEDULE\" && document.Form1.TERM_TYPE.value!=\"BALANCE_RE\")  ){"); // commented by udara on 21-06-2013
				//out.println(" if(document.Form1.hid_count.value==\"0\" && (document.Form1.TERM_TYPE.value!=\"RESCHEDULE\" || document.Form1.TERM_TYPE.value!=\"BALANCE_RE\" || document.Form1.TERM_TYPE.value!=\"NOR_TER\")  ){"); // added by udara on 21-06-2013
				
				out.println("   if((document.Form1.hid_count.value==\"0\") && (document.Form1.TERM_TYPE.value!=\"NOR_TER\") ){ "); // added by udara on 25-06-2013
				
				out.println("		alert('Please calculate termination value and continue!') "); 
				out.println("       m_sub = 1;;"); 
				
				out.println("    }"); // added by udara on 25-06-2013
				
				out.println(" }");
				
				out.println("if(m_sub=='1'){");
				out.println("return false;"); 
				out.println("}"); 
				out.println("else{"); 
				out.println("return true;"); 
				out.println("}"); 
				out.println("}"); 
				
				// added by udara on 06-02-2014 to check Termination Date
				out.println("function validate_termi_date(){ ");
				out.println("  check_termi_date(); ");
				out.println("}"); 
				// end by udara 06-02-2014
				
				out.println("function befor_submit(){ "); 
				/*out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");//[Commented by milinda ]
				out.println("document.Form1.elements[i].disabled=false;");
				out.println("}");*/
				out.println("		if(validate_data()){");
				out.println(" if(confirm(\"Are you sure, Termination Type is correct?\")){  "); //Start -- Added By Sandun on 26-11-2008
				out.println("		if(confirm(\"Are you sure you want to save?\")){ "); 
				out.println("m_v_no='';");
				out.println("m_s_va='';");
				out.println("m_c_no='';");
				out.println("m_i_no='';");
				out.println("m_ss_va='0';");
				out.println("for(i=0;i<parseFloat(document.Form1.hid_vcount.value);i++){");    
				out.println(" if(document.Form1.elements['ch_v_'+i].checked){");
				out.println("  m_v_no=m_v_no+document.Form1.elements['VEHICLE_NO_'+i].value+\"@\";");
				out.println("  m_c_no=m_c_no+document.Form1.elements['CHASSIS_NO_'+i].value+\"@\";");
				out.println("  m_i_no=m_i_no+document.Form1.elements['INVOICE_NO_'+i].value+\"@\";");
				out.println("  m_s_va=m_s_va+document.Form1.elements['sele_val_'+i].value+\"@\";");
				out.println("  m_ss_va=parseFloat(m_ss_va)+parseFloat(unformat_noobject(document.Form1.elements['sele_val_'+i].value));");    
				//out.println("  alert('m_s_va='+m_s_va);"); 
				//out.println("  alert('m_v_no='+m_v_no);"); 
				out.println("  m_v_count=m_v_count+1;");    
				out.println(" }");    
				out.println("}");    
				//out.println("if(parseFloat(document.Form1.hid_vcount.value)==m_v_count){");
				//out.println(" document.Form1.VEHICLE_NO.value=m_v_no;");    
				//out.println(" document.Form1.SALE_VALUE.value=m_s_va;");    
				//out.println(" document.Form1.SUM_SALE_VALUE.value=m_ss_va;");    
				////out.println(" document.Form1.VEHICLE_NO.value=\"\";");    
				////out.println(" document.Form1.SALE_VALUE.value=\"\";");    
				//out.println("}else{");
				out.println(" document.Form1.VEHICLE_NO.value=m_v_no;");    
				out.println(" document.Form1.CHASSIS_NO.value=m_c_no;");    
				out.println(" document.Form1.INVOICE_NO.value=m_i_no;");    
				out.println(" document.Form1.SALE_VALUE.value=m_s_va;");    
				out.println(" document.Form1.SUM_SALE_VALUE.value=m_ss_va;");    
				//out.println("}");    
				out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");//[added by milinda ]
				out.println("document.Form1.elements[i].disabled=false;");
				out.println("}");
				//out.println("		document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_Save';");  
				out.println("		document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_Save_2';"); 
				out.println("		document.Form1.submit();	"); 
				out.println("		}"); 
				out.println("		}"); //End
				out.println("		}"); 
				out.println("} "); 
				
				out.println("function befor_cal(){ "); 
				//out.println("		if(confirm(\"Are You Sure?\")){ "); 
				out.println("if(document.Form1.hid_option.value=='NEW'){");
				out.println("	if( document.Form1.LEASE_NO.value==\"\"){");
				out.println("   FNO.style.color='red';");
				out.println("		alert('Please enter finance no and continue!') "); 
				out.println("	}else if(document.Form1.TER_RATE.value==\"\" ){");
				out.println("   TMR.style.color='red';");
				out.println("		alert('Please enter discount rate and continue!') "); 
				out.println("	}else if(document.Form1.TER_DAY.value==\"\" || document.Form1.TER_MONTH.value==\"\" || document.Form1.TER_YEAR.value==\"\"){");
				out.println("   TMD.style.color='red';");
				out.println("		alert('Please enter termination date and continue!') "); 
				out.println("	}else{"); 
				out.println(" m_v_count=0;");    
				out.println(" m_v_no=\"\";");    
				out.println(" m_s_va=\"\";");    
				out.println(" m_c_no=\"\";");    
				out.println(" m_i_no=\"\";");    
				out.println(" m_ss_va=\"0\";");    
				//out.println("alert(document.Form1.hid_vcount.value);");
				out.println("for(i=0;i<parseFloat(document.Form1.hid_vcount.value);i++){");    
				out.println(" if(document.Form1.elements['ch_v_'+i].checked){");
				out.println("  m_v_no=m_v_no+document.Form1.elements['VEHICLE_NO_'+i].value+\"@\";");
				out.println("  m_c_no=m_c_no+document.Form1.elements['CHASSIS_NO_'+i].value+\"@\";");
				out.println("  m_i_no=m_i_no+document.Form1.elements['INVOICE_NO_'+i].value+\"@\";");
				out.println("  m_s_va=m_s_va+document.Form1.elements['sele_val_'+i].value+\"@\";");
				//out.println("  alert('m_s_va='+m_s_va);"); 
				//out.println("  alert('m_ss_va='+document.Form1.elements['sele_val_'+i].value);"); 
				out.println("  m_ss_va=parseFloat(m_ss_va)+parseFloat(unformat_noobject(document.Form1.elements['sele_val_'+i].value));");    
				out.println("  m_v_count=m_v_count+1;");    
				out.println(" }");    
				out.println("}");    
				//out.println("if(parseFloat(document.Form1.hid_vcount.value)==m_v_count){");
				//out.println(" document.Form1.VEHICLE_NO.value=m_v_no;");    
				//out.println(" document.Form1.SALE_VALUE.value=m_s_va;");    
				//out.println(" document.Form1.SUM_SALE_VALUE.value=m_ss_va;");    
				////out.println(" document.Form1.VEHICLE_NO.value=\"\";");    
				////out.println(" document.Form1.SALE_VALUE.value=\"\";");    
				//out.println("}else{");
				out.println(" document.Form1.VEHICLE_NO.value=m_v_no;");    
				out.println(" document.Form1.CHASSIS_NO.value=m_c_no;");    
				out.println(" document.Form1.INVOICE_NO.value=m_i_no;");    
				out.println(" document.Form1.SALE_VALUE.value=m_s_va;");    
				out.println(" document.Form1.SUM_SALE_VALUE.value=m_ss_va;");    
				//out.println("}");    
				//out.println("  alert(m_v_count+'---'+m_v_no);"); 
				//out.println("  alert('document.Form1.SUM_SALE_VALUE.value='+document.Form1.SUM_SALE_VALUE.value);"); 
				
				out.println("  if(parseFloat(m_v_count)>0){"); 
				out.println("      document.Form1.cal.disabled=true;"); // added by udara 29-01-2019
				//out.println("    m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule?chksql=get_Termi_Char&Term_val=\"+unformat_noobject(document.Form1.TERM_AMOUNT.value)+\"&Vehicle_no=\"+document.Form1.VEHICLE_NO.value+\"&Lease_no=\"+document.Form1.LEASE_NO.value+\"&Disco_rate=\"+document.Form1.TER_RATE.value+\"&vat_rate=\"+document.Form1.VAT_PER.value+\"&lease_rate=\"+document.Form1.LEASE_RATE.value+\"&App_Date=\"+document.Form1.TER_DAY.value+\"-\"+document.Form1.TER_MONTH.value+\"-\"+document.Form1.TER_YEAR.value+\"&SaleVal=\"+document.Form1.SALE_VALUE.value+\"&Client=\"+document.Form1.CLIENT_CODE.value+\"&chas_no=\"+document.Form1.CHASSIS_NO.value+\"&sum_sal=\"+document.Form1.SUM_SALE_VALUE.value+\"&invo_no=\"+document.Form1.INVOICE_NO.value;"); // commented by udara on 12-12-2012
				out.println("    m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule?chksql=get_Termi_Char&Term_val=\"+unformat_noobject(document.Form1.TERM_AMOUNT.value)+\"&Vehicle_no=\"+document.Form1.VEHICLE_NO.value+\"&Lease_no=\"+document.Form1.LEASE_NO.value+\"&Disco_rate=\"+document.Form1.TER_RATE.value+\"&vat_rate=\"+document.Form1.VAT_PER.value+\"&lease_rate=\"+document.Form1.LEASE_RATE.value+\"&App_Date=\"+document.Form1.TER_DAY.value+\"-\"+document.Form1.TER_MONTH.value+\"-\"+document.Form1.TER_YEAR.value+\"&SaleVal=\"+document.Form1.SALE_VALUE.value+\"&Client=\"+document.Form1.CLIENT_CODE.value+\"&chas_no=\"+document.Form1.CHASSIS_NO.value+\"&sum_sal=\"+document.Form1.SUM_SALE_VALUE.value+\"&invo_no=\"+document.Form1.INVOICE_NO.value+\"&term_opt_type=\"+document.Form1.TERMINATION_OPTION.value+\"&closing_rate=\"+document.Form1.TER_RATE.value;"); // added by udara on 12-12-2012
				//out.println("   window.open(m_url);");
				out.println("    makeRequest(m_url,'2');");
				out.println("	 }else{");
				out.println("	   alert('Sorry there is no selected vehicle to terminate.');"); 
				out.println("	 }"); 
				out.println("	}"); 
				out.println("}else{"); 
				out.println("      document.Form1.cal.disabled=true;"); // added by udara 29-01-2019
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule?chksql=get_Termi_Det&Term_val=\"+document.Form1.TERM_AMOUNT.value+\"&Termination_no=\"+document.Form1.TERMINATION_NO.value+\"&Lease_no=\"+document.Form1.LEASE_NO.value+\"&vat_rate=\"+document.Form1.VAT_PER.value+\"&Disco_rate=\"+document.Form1.TER_RATE.value+\"&App_Date=\"+document.Form1.TER_DAY.value+\"-\"+document.Form1.TER_MONTH.value+\"-\"+document.Form1.TER_YEAR.value+\"&SaleVal=\"+document.Form1.SALE_VALUE.value+\"&Client=\"+document.Form1.CLIENT_CODE.value+\"&chas_no=\"+document.Form1.CHASSIS_NO.value;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'2');");
				out.println("}"); 
				out.println("} "); 
				
				out.println("function befor_reset(){");
				out.println(" if(confirm(\"Are you sure you want to clear the screen?\")){  ");
				//out.println("  Form1.reset()   ");
				//out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule?chksql=main_page'"); // commented by udara 22-09-2017
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule2?chksql=main_page'"); // added by udara 22-09-2017
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
				
				rs = stmt.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') "+	
					" FROM   DUAL");
				if(rs.next()){
					
					m_dd         = rs.getString(1);
					m_mm         = rs.getString(2);
					m_yy         = rs.getString(3);
					
					/*out.println("  document.Form1.TER_DAY.value="+rs.getString(1)+";"); 
					out.println("  document.Form1.TER_MONTH.value="+rs.getString(2)+";"); 
					out.println("  document.Form1.TER_YEAR.value="+rs.getString(3)+";"); 
					out.println("  document.Form1.TER_V_DAY.value="+rs.getString(1)+";"); 
					out.println("  document.Form1.TER_V_MONTH.value="+rs.getString(2)+";"); 
					out.println("  document.Form1.TER_V_YEAR.value="+rs.getString(3)+";"); */
					
					out.println(" m_dd='" + m_dd + "'");
					out.println(" m_mm='" + m_mm + "'");
					out.println(" m_yy='" + m_yy + "'");
					out.println("document.Form1.TER_DAY.value=m_dd;");
					out.println("document.Form1.TER_MONTH.value=m_mm;");
					out.println("document.Form1.TER_YEAR.value=m_yy;");
					out.println("document.Form1.TER_V_DAY.value=m_dd;");
					out.println("document.Form1.TER_V_MONTH.value=m_mm;");
					out.println("document.Form1.TER_V_YEAR.value=m_yy;");
				}
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
				out.println("help_box.innerHTML=\"Finance - Termination - Calculation - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\"Finance - Termination - Calculation - \"+document.Form1.hid_status.value;"); 
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
				out.println("document.Form1.rec_help.disabled=true;"); 
				out.println("document.Form1.TERMINATION_NO.disabled=true;}"); 
				
				out.println("else if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
				out.println("}"); 
				out.println("else if(m_val==\"DELETE\"){"); 
				out.println("document.Form1.CLIENT_CODE.disabled=true;"); 
				out.println("document.Form1.cli_help.disabled=true;"); 
				out.println("document.Form1.lea_help.disabled=true;"); 
				//out.println("document.Form1.veh_help.disabled=true;"); 
				out.println("document.Form1.rec_help.disabled=false;"); 
				out.println("document.Form1.TERMINATION_NO.disabled=false;"); 
				
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
				out.println("Crit=document.Form1.CLIENT_CODE.value+\"@\"+document.Form1.LEASE_NO.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'ClientSql','1');");
				out.println("}");		
				out.println("function client_assign(oBj){");
				out.println(" document.Form1.CLIENT_NAME.value =oBj.valout[3]");
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[2]");
				//out.println(" get_Receipt();");
				out.println("}");
				
				//Lease Help
				out.println("function lease_help(){");
				
				// added by udara 22-05-2019
				out.println(" var m_string = document.Form1.LEASE_NO.value; ");
				out.println(" if(m_string!=''){ ");
				out.println("   m_string = m_string.replace(/^\\s+|\\s+$/g, ''); ");
				out.println(" } ");
				out.println(" document.Form1.LEASE_NO.value = m_string; ");
				out.println("Crit=m_string+\"@\"+document.Form1.CLIENT_CODE.value+\"@\";");
				// end by udara 22-05-2019
				
				//out.println("Crit=document.Form1.LEASE_NO.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\";"); // commented by udara 22-05-2019
				out.println("HelpBox('1','10','0',Crit,'LeaseSql','3');");
				out.println("}");	
				
				out.println("function lease_assign(oBj){");
				out.println(" document.Form1.LEASE_NO.value =oBj.valout[2]");
				out.println(" document.Form1.APPLICATION_NO.value =oBj.valout[3]");
				out.println(" document.Form1.CLIENT_NAME.value =oBj.valout[4]");//Added By Sandun on 26-11-2008
				out.println(" document.Form1.TER_RATE.value =oBj.valout[9]"); // added by udara on 12-09-2012
				//out.println(" document.Form1.TER_RATE.focus();"); // commented by udara 28-03-2016
				//out.println("alert(document.Form1.LEASE_NO.value);");	
				
				// added by udara 11-12-2017
				out.println(" if(oBj.valout[10]=='YES'){ ");
				out.println("  alert('This contract is rental freezed and continue with correct termination type'); ");
				out.println(" } ");
				// end by udara 11-12-2017
				
				out.println(" document.Form1.HID_PLEDGE_STATUS.value = oBj.valout[11]; "); // added by udara 10-10-2018
				
				out.println(" closing_rental_rate_change();  "); // added by udara on 24-04-2013
				out.println(" check_lease(document.Form1.LEASE_NO.value);");
				
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
				//ADDED BY SH ON 21/05/2007	
				out.println("m_vat='';");
				out.println("m_vcount='0';");
				out.println("for(i=0;i<parseFloat(document.Form1.hid_vcount.value);i++){");    
				out.println(" if(document.Form1.elements['ch_v_'+i].checked && i!=num){");
				out.println("  m_vat=parseFloat(unformat_noobject(document.Form1.elements['VAT_'+i].value));");    
				out.println("  m_vcount=m_vcount+1;");    
				out.println(" }");    
				out.println("}");    
				out.println(" if(m_vcount=='0'){");
				out.println("   m_vat=parseFloat(unformat_noobject(document.Form1.elements['VAT_'+num].value));");    
				out.println(" }");    
				
				//END	
				out.println("if(document.Form1.elements['ch_v_'+num].checked && (m_vat==parseFloat(unformat_noobject(document.Form1.elements['VAT_'+num].value)))){");
				out.println(" document.Form1.elements['ch_v_'+num].value=\"YES\";");
				out.println(" document.Form1.elements['ch_v_'+num].checked=true;");
				out.println("}else{");
				out.println(" if(document.Form1.elements['ch_v_'+num].checked && (m_vat!=parseFloat(unformat_noobject(document.Form1.elements['VAT_'+num].value)))){");
				out.println("  alert('Please check the VAT rate and continue.');");
				out.println(" }");
				out.println(" document.Form1.elements['ch_v_'+num].value=\"NO\";");
				out.println(" document.Form1.elements['ch_v_'+num].checked=false;");
				out.println("}");
				out.println(" get_term_details();");
				out.println("}");
				
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
				
				out.println("function check_Date(val1,val2,val3) {");
				//out.println("  alert(val1.value+'-'+val2.value+'-'+val3.value+'-');");
				out.println(" if(val1.value!='' && val2.value!='' && val3.value!=''){"); 
				//out.println("  alert(val1+'-'+val2+'-'+val3+'-');");
				
				out.println("  checkMonthLength(val1,val2,val3); ");
				//out.println(" check_sysdate();");//added by milinda
				out.println(" }");
				out.println("}");
				
				
				//====PRABASH on 09-02-2012========================
				
				out.println("function date_chack(){ ");	
				out.println("from_date = document.Form1.TER_DAY.value+'-'+document.Form1.TER_MONTH.value+'-'+document.Form1.TER_YEAR.value;");
				out.println("to_date = document.Form1.TER_V_DAY.value+'-'+document.Form1.TER_V_MONTH.value+'-'+document.Form1.TER_V_YEAR.value;");
				out.println("var d1 = new Date(from_date);");
				out.println("var d2 = new Date(to_date);");		
				out.println("if(d2<d1){");		
				out.println("alert('Termination Valid Date must be greater than Termination  Date..!');");	
				out.println(" } ");				
				out.println(" } ");
				//=============================
				
				
				
				
				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
				
				out.println("function load_c_date(val) {");
				//out.println("alert(val);");
				out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
				out.println("     document.Form1.TER_DAY.value=val.substr(0,2);");
				out.println("     document.Form1.TER_MONTH.value=val.substr(3,2);");
				out.println("     document.Form1.TER_YEAR.value=val.substr(6,4);");
				
				//out.println("     check_lease();");//[Commented by milinda on 02-01-2018]
				out.println(" check_sysdate(document.Form1.TER_DAY,document.Form1.TER_MONTH,document.Form1.TER_YEAR);");//[Added by milinda]
				
				out.println("  }else if(document.Form1.hid_cal_date.value=='3'){"); 
				out.println("     document.Form1.TER_V_DAY.value=val.substr(0,2);");
				out.println("     document.Form1.TER_V_MONTH.value=val.substr(3,2);");
				out.println("     document.Form1.TER_V_YEAR.value=val.substr(6,4);");
				
				out.println("date_chack();");	//added by Prabash on 09-02-2012
				out.println(" check_sysdate(document.Form1.TER_V_DAY,document.Form1.TER_V_MONTH,document.Form1.TER_V_YEAR);");
				out.println("  }");				
				out.println("}");	
				
				
				
				out.println("function load_data(num) {");
				out.println(" if(num!=\"\"){");	
				//out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Documents?chksql=get_documents&deal_no=\"+num+\"&foll_no=\", \"oBj\",\"left=150,top=280,width=620,height=390\");"); 
				out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Credit_Approval_Saction_Letter?chksql=Report&print=TRUE&applicaton_no=\"+num+\"\", \"oBj\",\"left=150,top=280,width=800,height=600,scrollBars=1\");"); 
				//0000000123
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println(" }else{");
				out.println("   alert('Please enter Finance Number and continue!');");
				out.println(" }");
				out.println("}");
				
				out.println("function load_cdata(num) {");
				out.println(" if(num!=\"\"){");	
				out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_PRO_drill_downs?chksql=SHOW_CLIENT_INFORMATION&client_code=\"+num+\"\", \"oBj\",\"left=150,top=280,width=800,height=600,scrollBars=1\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println(" }else{");
				out.println("   alert('Please enter Client Code and continue!');");
				out.println(" }");
				out.println("}");
				
				out.println("function load_inv_data(num) {");
				out.println(" if(num!=\"\" ){");	
				//out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Documents?chksql=get_documents&deal_no=\"+num+\"&foll_no=\", \"oBj\",\"left=150,top=280,width=620,height=390\");"); 
				out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_PRO_drill_downs_3?chksql=SHOW_RENT_DETAIL_INVOICED_DRILL&application_no=\"+num+\"\", \"oBj\",\"left=150,top=280,width=800,height=600,scrollBars=1\");"); 
				//0000000123
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println(" }else{");
				out.println("   alert('Please enter Finance Number and continue!');");
				out.println(" }");
				out.println("}");
				//https://dev-lakdl.sasianet.com:/myserver/servlet/LAKDL_AF_MISF_balance_receivable_report1?chksql=MAIN&client_name=TEST&finance_no=AP20070511-0624&client_code=0000000233&allocation_date=01-12-2007
				
				//Added by Dineth on 2008-12-03
				out.println("function load_arrears_data(num) {");
				out.println(" if(num!=\"\" ){");	
				//out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Documents?chksql=get_documents&deal_no=\"+num+\"&foll_no=\", \"oBj\",\"left=150,top=280,width=620,height=390\");"); 
				out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_ARREARS_DETAIL_DRILL&lease_no=\"+num+\"\", \"oBj1\",\"left=150,top=280,width=800,height=600,scrollBars=1\");"); 
				//0000000123
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println(" }else{");
				out.println("   alert('Please enter Finance Number and continue!');");
				out.println(" }");
				out.println("}");
				
				
				
				
				//End by Dineth on 2008-12-03
				
				// Added by Disnaka Jayasuriya on 2009.10.13, 	
				out.println("function check_number_decimal(obj,size){");
				out.println("if (obj.value != \"\") {");
				out.println("if(isnumberok(obj,size)){"); 
				out.println("format_number(obj,size)"); 
				out.println("}"); 
				out.println("else{");
				out.println("alert('please enter a number');"); 
				out.println("obj.value='';"); 
				out.println("obj.focus();"); 
				out.println("}"); 
				out.println("}"); 
				out.println("}");
				
				
				out.println("function load_Rec_data(num) {");
				out.println(" if(num!=\"\" ){");	
				//out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Documents?chksql=get_documents&deal_no=\"+num+\"&foll_no=\", \"oBj\",\"left=150,top=280,width=620,height=390\");"); 
				out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule?chksql=SHOW_BALANCES_INFO&url=&client_code=\"+num+\"\", \"oBj\",\"left=150,top=280,width=800,height=600\");"); 
				//0000000123
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println(" }else{");
				out.println("   alert('Please enter Client Code and continue!');");
				out.println(" }");
				out.println("}");	
				
				int m_cnt = 0;
				String m_fin_no = "";
				
				
				out.println("function load_remarks(val){");//Added By Sandun on 10-12-2008
				out.println("if(val==\"\"){");
				out.println("FNO.style.color='red';");
				out.println("}");	
				out.println("else{");
				out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationApproval1?chksql=REMARKS_HISTORY&finance_no=\"+val+\" \";");  
				out.println("window.open(m_url,'displayWindow4','left=50,top=60,width=800,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				out.println("}");
				
				///---------------------Added by Susitha 23-06-2011---------
				
				out.println("function Generate_Letter(m_due_amount,m_due_rent,m_odi_amount,m_sale_price,m_tot_chrg,m_client,m_agreemnt_no){");
				//out.println("alert(m_due_amount)");		//kk																																																																																																																																																																																																																								
				out.println("var m_term_type=document.Form1.TERM_TYPE.value;");
				out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule?chksql=generate_letter&due_amount=\"+m_due_amount+\"&due_rent=\"+m_due_rent+\"&odi_amount=\"+m_odi_amount+\"&sale_price=\"+m_sale_price+\"&tot_chrg=\"+m_tot_chrg+\"&agreemnt_no=\"+m_agreemnt_no+\"&term_type=\"+m_term_type+\"&client=\"+m_client+\"&print=true\";");//ss
				out.println("window.open(m_url,'displayWindow2','left=50,top=60,width=700,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				// added by udara on 11-12-2012
				out.println(" function befor_cal_new(){");
				
				//out.println(" val = document.Form1.APPLICATION_NO.value; ");
				
				out.println("   if(document.Form1.LEASE_NO.value==''){ ");
				out.println("   	alert('Please enter the contract number'); ");
				out.println("   }");
				
				out.println("   else if(document.Form1.hid_vcount.value=='0'){ ");
				out.println("   	alert('Please select a vehicle'); ");
				out.println("   }");
				
				out.println("   else{");
				
				out.println("       var found_status = 'no'; ");
				
				out.println("       for(var i=0; i<parseInt(document.Form1.hid_vcount.value); i++ ){ ");
				out.println("          if(document.Form1.elements['ch_v_'+i].checked==true){ ");
				out.println("       		 found_status = 'yes'; ");
				out.println("          		 break; "); 
				out.println("          } "); 	
				out.println("       } ");
				
				out.println("       if(found_status == 'yes'){ ");
				out.println(" 			val = document.Form1.LEASE_NO.value; ");
				out.println(" 			m_closing_rate = document.Form1.TER_RATE.value; ");
				
				out.println(" 			m_date = document.Form1.LEASE_NO.value; ");
				out.println("           m_odi_net = unformat_noobject(document.Form1.ODI_NET.value); "); // added by udara on 24-07-2013
				out.println("           document.Form1.BUT_CLS.disabled = true;    "); // added by udara 05-02-2019
				//out.println(" 			m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_Termination_calculation_rpt?chksql=CALCULATION_REPORT2&termi_date=\"+document.Form1.TER_DAY.value+\"-\"+document.Form1.TER_MONTH.value+\"-\"+document.Form1.TER_YEAR.value+\"&finance_no=\"+val+\"&closing_rate=\"+m_closing_rate;");  
				out.println(" 			m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_Termination_calculation_rpt?chksql=CALCULATION_REPORT2&termi_date=\"+document.Form1.TER_DAY.value+\"-\"+document.Form1.TER_MONTH.value+\"-\"+document.Form1.TER_YEAR.value+\"&finance_no=\"+val+\"&closing_rate=\"+m_closing_rate+\"&odi_net=\"+m_odi_net;");  
				//out.println(" 			window.open(m_url,'displayWindow4','left=50,top=60,width=800,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); // commented by udara 21-09-2017
				out.println(" 			window.open(m_url,'displayWindow4_cr2','left=50,top=60,width=800,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); // added by udara 21-09-2017
				out.println("   	}");
				out.println("   	else{");
				out.println("   		alert('Please select a vehicle'); ");
				out.println("   	}");
				
				out.println("   }");
				
				
				out.println("}");
				// end by udara on 11-12-2012
				
				// added by udara on 22-04-2013
				out.println("function closing_rental_rate_change(){");
				//out.println("  alert('closing_rental_rate_change new'); ");
				out.println("   if(parseFloat(document.Form1.TER_RATE.value)==0){   ");
				out.println("  		document.Form1.TERMINATION_OPTION.value = 'NORMAL_CLOSING_OPT'; ");
				out.println("   	document.Form1.TERMINATION_OPTION.disabled=true; ");
				out.println("   	document.Form1.TERM_TYPE.value='NOR_TER'; "); 
				out.println("   	document.Form1.TERM_TYPE.disabled=true; ");
				out.println("   	document.Form1.hid_count.value=1; ");
				//out.println("  alert('closing_rental_rate_change new' + document.Form1.hid_count.value); ");
				
				out.println("   }");
				out.println("   else{");
				out.println("   	document.Form1.TERMINATION_OPTION.disabled=false; ");
				out.println("   	document.Form1.TERM_TYPE.disabled=false; ");
				out.println("   }");
				out.println("}");
				
				//[Added by milinda on 02-01-2018]
				
				
				
				rs = stmt.executeQuery(" " +
					"   SELECT "+
					"   	  TO_CHAR(SYSDATE,'DD'), "+ // 2 
					"   	  TO_CHAR(SYSDATE,'MM'), "+ // 2 
					"   	  TO_CHAR(SYSDATE,'YYYY') "+ // 2 
					"   				FROM DUAL " +
					" ");
				
				if (rs.next()) {					
					m_dd         = rs.getString(1);
					m_mm         = rs.getString(2);
					m_yy         = rs.getString(3);
					
					
				}
				
				
				
				out.println("function check_sysdate(objdd,objmm,objyy) {"); 
				/*out.println("alert(objdd.value);");
				out.println("alert(objmm.value);");
				out.println("alert(objyy.value);");*/
				//out.println("alert("+m_dd+");");
				out.println(" m_dd='" + m_dd + "'");
				out.println(" m_mm='" + m_mm + "'");
				out.println(" m_yy='" + m_yy + "'");
				out.println("document.Form1.HID_SYS_VAL_YEAR.value=m_yy;");
				out.println("document.Form1.HID_SYS_VAL_MONTH.value=m_mm;");
				out.println("document.Form1.HID_SYS_VAL_DAY.value=m_dd;");
				/*out.println("alert(document.Form1.HID_SYS_VAL_YEAR.value);");
				out.println("alert(document.Form1.HID_SYS_VAL_MONTH.value);");
				out.println("alert(document.Form1.HID_SYS_VAL_DAY.value);");*/
				out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
				//out.println("  checkMonthLength(objdd,objmm,objyy);");
				//out.println("  validate_date(objdd,objmm,objyy,document.Form1.HID_SYS_VAL_DAY,document.Form1.HID_SYS_VAL_MONTH,document.Form1.HID_SYS_VAL_YEAR);");
				out.println("   if(objyy.value > document.Form1.HID_SYS_VAL_YEAR.value){");
				out.println("      alert('Termination Date Cannot be greater than System Date');");
				out.println("document.Form1.TER_DAY.value=m_dd;");
				out.println("document.Form1.TER_MONTH.value=m_mm;");
				out.println("document.Form1.TER_YEAR.value=m_yy;");
				out.println("document.Form1.TER_V_DAY.value=m_dd;");
				out.println("document.Form1.TER_V_MONTH.value=m_mm;");
				out.println("document.Form1.TER_V_YEAR.value=m_yy;");
				
				
				out.println("}");
				out.println("  else if(objmm.value>document.Form1.HID_SYS_VAL_MONTH.value){");
				out.println("      alert('Termination Date Cannot be greater than System Date');");
				out.println("document.Form1.TER_DAY.value=m_dd;");
				out.println("document.Form1.TER_MONTH.value=m_mm;");
				out.println("document.Form1.TER_YEAR.value=m_yy;");
				out.println("document.Form1.TER_V_DAY.value=m_dd;");
				out.println("document.Form1.TER_V_MONTH.value=m_mm;");
				out.println("document.Form1.TER_V_YEAR.value=m_yy;");
				
				out.println("}");
				out.println("  else if(objdd.value>document.Form1.HID_SYS_VAL_DAY.value){");
				out.println("      alert('Termination Date Cannot be greater than System Date');");
				out.println("document.Form1.TER_DAY.value=m_dd;");
				out.println("document.Form1.TER_MONTH.value=m_mm;");
				out.println("document.Form1.TER_YEAR.value=m_yy;");
				out.println("document.Form1.TER_V_DAY.value=m_dd;");
				out.println("document.Form1.TER_V_MONTH.value=m_mm;");
				out.println("document.Form1.TER_V_YEAR.value=m_yy;");
				out.println("}");
				out.println("}");
				out.println("}");
				
				////////////////////////////////////////////////////////////
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_out_value();load_lock();\">"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input type='hidden' name='Hid_scr_name' value='AF_CR_TERMINATION_CAL' > ");
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
				out.println("<input type=hidden name=\"CHASSIS_NO\" value=\"\">");
				out.println("<input type=hidden name=\"INVOICE_NO\" value=\"\">");
				out.println("<input type=hidden name=\"SALE_VALUE\" value=\"\">");
				out.println("<input type=hidden name=\"SUM_SALE_VALUE\" value=\"\">");
				out.println("<input type=hidden name=\"HID_SYS_VAL_DAY\" value=\"\">");
				out.println("<input type=hidden name=\"HID_SYS_VAL_MONTH\" value=\"\">");
				out.println("<input type=hidden name=\"HID_SYS_VAL_YEAR\" value=\"\">");
				out.println("<input type=hidden name=\"HID_PLEDGE_STATUS\" value=\"\">"); // added by udara 10-10-2018
				
				
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
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=edit value=\"Delete\" class=mainbut onclick=load_screen_status(\"DELETE\"); onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				//out.println("<td width=10%>&nbsp;</td>");
				//out.println("<td><input type=button name=delete value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel value=\"Re-active\" class=mainbut onclick=befor_active(); onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td ><input type=button name=b_submit value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>"); // commented by udara 06-02-2014
				out.println("<td ><input type=button name=b_submit value=\"Save\" class=mainbut onclick=validate_termi_date(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>"); // added by udara 06-02-2014
				out.println("<td>&nbsp;</td>");
				out.println("<td><input class='mainbut' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled>  </td>"); 
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Cancel\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=cal value=\"Calculate\" class=mainbut onclick=befor_cal();></td>");
				//out.println("<td><input type=button name=cal_1 value=\"Closing Letter\" class=mainbut onclick=befor_cal_new(); style={width:100px;} ></td>"); // added by udara on 11-12-2012
				
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
				out.println("<td id=TMN>Termination No</td>");
				out.println("<td><input name=\"TERMINATION_NO\" type=\"text\" maxlength=\"15\" class=\"txt_input\" onchange=check_term()> ");
				out.println("<input type=button name=rec_help value=... class=\"but_input\" onclick=\"term_help()\" disabled></td>");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//Modified by Dineth on 2008-10-24
				out.println("<td id=fod>Termination Type</td>");
				String m_ter_type="<SELECT name=\"TERM_TYPE\" class=\"txt_input\" >";
				rs2 = stmt.executeQuery("SELECT TERMINATION_TYPE, TERMINATION_DESC "+
					"FROM   "+m_schema_name+".AF_CO_MAS_TERMINATION_TYPE ");
				while(rs2.next()){
					if(rs2.getString(1).trim().equals("ERL_TER")){
						m_ter_type=m_ter_type+"<OPTION value=\""+rs2.getString(1)+"\" selected >"+rs2.getString(2)+"</OPTION>";
					}
					else{
						m_ter_type=m_ter_type+"<OPTION value=\""+rs2.getString(1)+"\">"+rs2.getString(2)+"</OPTION>";
					}
				}
				m_ter_type=m_ter_type+"</SELECT>";
				out.println("<td>"+m_ter_type+"<input type=hidden name=TERMINATION_TYPE value=''></TD>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				//End by Dineth on 2008-10-24
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td id=TMD>Termination Date *</td>");
				out.println("<td><input name=\"TER_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=\"check_sysdate(document.Form1.TER_DAY,document.Form1.TER_MONTH,document.Form1.TER_YEAR);\" onchange=check_Date(document.Form1.TER_DAY,document.Form1.TER_MONTH,document.Form1.TER_YEAR) > ");//[removed by milinda check_lease(); for when termination date change level tick box removie block]//onchange=(check_Date(document.Form1.TER_DAY,document.Form1.TER_MONTH,document.Form1.TER_YEAR));
				out.println("    <input name=\"TER_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=\"check_sysdate(document.Form1.TER_DAY,document.Form1.TER_MONTH,document.Form1.TER_YEAR);\" onchange=check_Date(document.Form1.TER_DAY,document.Form1.TER_MONTH,document.Form1.TER_YEAR) > ");//[removed by milinda check_lease();]onchange=(check_Date(document.Form1.TER_DAY,document.Form1.TER_MONTH,document.Form1.TER_YEAR));
				out.println("    <input name=\"TER_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onblur=\"check_sysdate(document.Form1.TER_DAY,document.Form1.TER_MONTH,document.Form1.TER_YEAR);\" onchange=check_Date(document.Form1.TER_DAY,document.Form1.TER_MONTH,document.Form1.TER_YEAR) ><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");//[removed by milinda check_lease();]//onchange=(check_Date(document.Form1.TER_DAY,document.Form1.TER_MONTH,document.Form1.TER_YEAR));
				out.println("</td>");
				out.println("<td id=tod>Requested By</td>");
				out.println("<td><select name=REQ_BY class=\"txt_input\"><option value=\"CLIENT\" Selected>Client</option> ");
				out.println("<option value=\"MANAGEMENT\" >Management</option><select> ");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td id=CLC>Client Code *</td>");
				out.println("<td><input name=\"CLIENT_CODE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" onchange=check_client()> ");
				out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"client_help()\">");
				out.println("<input class='but_input' type='button' name='BUT_HELP_DET' value=\"Detail\" onClick=\"load_cdata(document.Form1.CLIENT_CODE.value)\" >"); 
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td id=tod>Client Name</td>");
				out.println("<td> <input name=\"CLIENT_NAME\" type=\"text\" maxlength=\"200\" class=\"txt_input\" disabled style=\"width:300px;\">");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");		
				
				
				out.println("<tr class=tr_input>");
				out.println("<td id=FNO>Finance No *</td>");
				out.println("<td><input name=\"LEASE_NO\" type=\"text\" maxlength=\"30\" class=\"txt_input\" onchange=check_lease()><input name=\"APPLICATION_NO\" type=\"hidden\">");
				out.println("<input type=button name=lea_help value=... class=\"but_input\" onclick=\"lease_help()\" >");
				out.println("<input class='but_input' type='button' name='BUT_HELP_DET' value=\"Detail\" onClick=\"load_data(document.Form1.APPLICATION_NO.value)\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_REMARK_DET' value=\"Remarks\" onClick=\"load_remarks(document.Form1.LEASE_NO.value)\" >"); 
				out.println("<span id='moratorium' style='color:black'></span> "); // added by udara 06-01-2021
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td >Termination Count</td>");
				out.println("<td><input name=\"TER_COUNT\"   type=\"text\" maxlength=\"2\"  class=\"txt_input\" disabled > ");
				out.println("</td>");
				//out.println("<td ></td>");
				//out.println("<td></td>");
				/*out.println("<td >Vehicle No</td>");
				out.println("<td> <input name=\"VEHICLE_NO\" type=\"text\" maxlength=\"20\" class=\"txt_input\" onchange=check_vehicle()> ");
				out.println("<input type=button name=veh_help value=Help class=\"but_input\" onclick=\"vehicle_help()\" >");
				out.println("</td>");*/
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td id=TRNT>Transaction Type</td>");
				out.println("<td><input name=\"TRN_TYPE\" type=\"text\" maxlength=\"15\" class=\"txt_input\" Disable>");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td >Closure IRR</td>");
				//out.println("<td><input name=\"CLOSURE_IRR\" type=\"text\" maxlength=\"15\" class=\"txt_input\" Disable STYLE=\"{text-align:right;}>");
				//out.println("</td>");
				out.println("<td ><input name=\"CLOSURE_IRR\"  type=\"text\" maxlength=\"6\"  class=\"txt_input\" disabled onchange=\"\" STYLE=\"{text-align:right;}\"></td>");
				//out.println("<td></td>");
				/*out.println("<td >Vehicle No</td>");
				out.println("<td> <input name=\"VEHICLE_NO\" type=\"text\" maxlength=\"20\" class=\"txt_input\" onchange=check_vehicle()> ");
				out.println("<input type=button name=veh_help value=Help class=\"but_input\" onclick=\"vehicle_help()\" >");
				out.println("</td>");*/
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				//out.println("<td id=TMR>Termination Rate *</td>"); // commented by udara on 08-01-2013
				out.println("<td id=TMR>Closing Rental Rate *</td>"); // added by udara on 08-01-2013
				//out.println("<td><input name=\"TER_RATE\" type=\"text\" maxlength=\"6\" class=\"txt_input\" onblur=\"check_number_decimal(this,2)\" onchange=check_Rate(document.Form1.TER_RATE.value) STYLE=\"{text-align:right;}\">"); // commented by udara on 22-04-2013
				out.println("<td><input name=\"TER_RATE\" type=\"text\" maxlength=\"6\" class=\"txt_input\" onblur=\"check_number_decimal(this,2);closing_rental_rate_change();\" onchange=\"check_Rate(document.Form1.TER_RATE.value);\" STYLE=\"{text-align:right;}\" disabled>"); // added by udara on 22-04-2013
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td >Finance Rate</td>");
				out.println("<td><input name=\"LEASE_RATE\"  type=\"text\" maxlength=\"6\"  class=\"txt_input\" disabled onchange=check_Date(document.Form1.TER_V_DAY,document.Form1.TER_V_MONTH,document.Form1.TER_V_YEAR) STYLE=\"{text-align:right;}\"> ");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td id=TMV>Termination Valid Date *</td>");
				out.println("<td><input name=\"TER_V_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=\"check_sysdate(document.Form1.TER_V_DAY,document.Form1.TER_V_MONTH,document.Form1.TER_V_YEAR);\" onchange=check_Date(document.Form1.TER_V_DAY,document.Form1.TER_V_MONTH,document.Form1.TER_V_YEAR)> ");
				out.println("    <input name=\"TER_V_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=\"check_sysdate(document.Form1.TER_V_DAY,document.Form1.TER_V_MONTH,document.Form1.TER_V_YEAR);\" onchange=check_Date(document.Form1.TER_V_DAY,document.Form1.TER_V_MONTH,document.Form1.TER_V_YEAR)> ");
				out.println("    <input name=\"TER_V_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onblur=\"check_sysdate(document.Form1.TER_V_DAY,document.Form1.TER_V_MONTH,document.Form1.TER_V_YEAR);\" onchange=check_Date(document.Form1.TER_V_DAY,document.Form1.TER_V_MONTH,document.Form1.TER_V_YEAR) onblur=date_chack()><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
				out.println("</td>");
				out.println("<td >Due Amount</td>");
				out.println("<td><input name=\"DUE_AMOUNT\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onblur=\"check_number_decimal(this,2)\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled>");
				out.println("<input name=\"DUE_NET\" type=\"hidden\" ><input name=\"DUE_VAT\" type=\"hidden\" >");
				out.println("<input class='but_input' type='button' name='BUT_INV' value=\"Invoice Detail\" onClick=\"load_inv_data(document.Form1.APPLICATION_NO.value)\" style=\"width:90px;\">");
				//Added by Dineth on 2008-12-03
				out.println("<input class='but_input' type='button' name='BUT_ARREARS' value=\"Arrears Detail\" onClick=\"load_arrears_data(document.Form1.LEASE_NO.value)\" style=\"width:90px;\" disabled>");
				out.println("</td>"); 
				//out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Remark</td>");
				out.println("<td><input name=\"REMARK\"   type=\"text\" maxlength=\"200\"  class=\"txt_input\" style=\"width:300px;\"> ");
				out.println("</td>");
				out.println("<td >Termination Charge</td>");
				out.println("<td><input name=\"TERM_AMOUNT\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onblur=\"check_number_decimal(this,21)\" onchange=\"\" STYLE=\"{text-align:right;}\">");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >VAT %</td>");
				out.println("<td><input name=\"VAT_PER\" type=\"text\" maxlength=\"6\" class=\"txt_input\" onblur=\"check_number_decimal(this,2)\" STYLE=\"{text-align:right;}\" disabled> ");//
				out.println("</td>");
				out.println("<td >Normal Rentals Due up to Termination Date</td>");
				out.println("<td><input name=\"DUE_RENTALS\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled>");
				out.println("<input name=\"DUE_RENTALS_NET\" type=\"hidden\" ><input name=\"DUE_RENTALS_VAT\" type=\"hidden\" >");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				//amount finance , NIBSM ,AMI(Amount) , Capital Repayment ,Total Capital already settled, % (Amount Setteled/Financed Amount) 
				out.println("<tr class=tr_input>");
				out.println("<td >Amount Finance</td>");
				out.println("<td><input name=\"AMOUNT_FINANCE\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" onblur=\"check_number_decimal(this,2)\" STYLE=\"{text-align:right;}\" disabled> ");
				out.println("</td>");
				out.println("<td >NIBSM</td>");
				out.println("<td><input name=\"NIBSM\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onblur=\"check_number_decimal(this,2)\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled>");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td>AMI</td>");
				out.println("<td><input name=\"AMI\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onblur=\"check_number_decimal(this,2)\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled> ");
				out.println("</td>");
				out.println("<td>Unallocated Receipt</td>");
				out.println("<td><input name=\"Unallo_Rec\" type=\"text\" disabled class=\"txt_input\" STYLE=\"{text-align:right;}\">");
				out.println("<input class='but_input' type='button' name='BUT_REC' value=\"Receipt Detail\" onClick=\"load_Rec_data(document.Form1.CLIENT_CODE.value)\" style=\"width:90px;\">");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td>Total Capital Outstanding</td>");
				out.println("<td><input name=\"CAP_OUT\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onchange=\"\" onblur=\"check_number_decimal(this,2)\" STYLE=\"{text-align:right;}\" disabled> ");
				out.println("</td>");
				out.println("<td>%</td>");
				out.println("<td><input name=\"CAP_OUT_PER\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onblur=\"check_number_decimal(this,2)\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled>");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td>ODI</td>");
				out.println("<td><input name=\"ODI\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onblur=\"check_number_decimal(this,2)\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled> ");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td>ODI Adjustment</td>");
				out.println("<td><input name=\"ODI_ADJ\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onblur=\"check_number_decimal(this,21)\" onchange=\"check_ODI_ADJ()\" STYLE=\"{text-align:right;}\" VALUE='0.00' disabled> ");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td>ODI Net</td>");
				out.println("<td><input name=\"ODI_NET\" type=\"text\" maxlength=\"25\" class=\"txt_input\" onblur=\"check_number_decimal(this,2)\" onchange=\"\" STYLE=\"{text-align:right;}\" disabled> ");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//Modified by Dineth on 2008-10-21
				//out.println("<td>&nbsp;</td>"); // commented by udara on 12-12-2012
				
				
				//out.println("<td>&nbsp;</td>"); // commented by udara on 12-12-2012
				
				// added by udara on 12-12-2012
				out.println("<td id=tod>Termination Option</td>");
				out.println("<td><select name=TERMINATION_OPTION class=\"txt_input\">");
				//out.println("<option value=\"NORMAL_CLOSING_OPT\" Selected>Normal Closing Option</option> ");
				//out.println("<option value=\"CLOSING_OPT\" >Closing Option</option>");
				//out.println("<option value=\"RABATE_OPT\"  >Rebate Option</option> <select> ");
				
				out.println("<option value=\"NORMAL_CLOSING_OPT\" Selected>Normal Closing</option> ");
				out.println("<option value=\"CLOSING_OPT\" >Closing Rate</option>");
				out.println("<option value=\"RABATE_OPT\"  >5% Future Capital</option> <select> ");
				
				//out.println("  <input type=button name=cal_1 value=\"Closing Letter\" class=mainbut onclick=befor_cal_new(); style={width:100px;} > "); // added by udara on 08-01-2013
				out.println("<input class='but_input' type='button' name='BUT_CLS' value=\"Closing Letter\" onclick=befor_cal_new(); style=\"width:90px;\">"); // added by udara on 08-01-2013
				
				out.println("</td>");
				// end by udara on 12-12-2012
				
				//End by Dineth on 2008-10-21
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				// added by udara 06-01-2021
				out.println("<tr class=tr_input>");
				out.println("<td>&nbsp; </td>");
				out.println("<td>&nbsp; </td>");
					
				out.println("<td>Future Debit</td>");
				out.println("<td><input style='text-align:right' name=\"FUTURE_DEBIT\" id=\"FUTURE_DEBIT\" type=\"text\" maxlength=\"25\" class=\"txt_input\" disabled> ");
				out.println("</td>");
					
				out.println("</tr>");
				// end by udara 06-01-2021
				
				
				out.println("<tr class=tr_input>");
				out.println("<td colspan=4><div id=veh><input type=hidden name=hid_vcount value=0></div></td>");
				out.println("</tr>");
				
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				
				
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<div id=inv>");
				out.println("<table cellpadding=\"2\" cellspacing=\"2\" border=\"0\" class=table>");
				out.println("<tr>");
				out.println("<td WIDTH=25% id=H1 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td WIDTH=25% id=H2 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("<td WIDTH=25% id=H3 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("<td WIDTH=25% id=H4 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td id=S11 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=S12 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("<td id=S13 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("<td id=S14 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td id=S21 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=S22 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("<td id=S23 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("<td id=S24 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td id=S31 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=S32 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("<td id=S33 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("<td id=S34 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td id=S41 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=S42 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("<td id=S43 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("<td id=S44 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td id=S45 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=S46 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("<td id=S47 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("<td id=S48 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td id=S51 STYLE=\"{font-weight: bold}\"></td>");
				out.println("<td id=S52  align=right></td>");
				out.println("<td id=S53  align=right></td>");
				out.println("<td id=S54  align=right></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td id=S61 STYLE=\"{font-weight: bold}\"></td>");
				out.println("<td id=S62  align=right></td>");
				out.println("<td id=S63  align=right></td>");
				out.println("<td id=S64  align=right></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td id=T1 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=T2 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("<td id=T3 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("<td id=T4 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("</tr>");
				
				
				//Added by Dineth on 2008-10-01
				
				out.println("<tr>");
				out.println("<td id=S65 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=S66 align=right></td>");
				out.println("<td id=S67 align=right></td>");
				out.println("<td id=S68 align=right></td>");
				out.println("</tr>");
				
				
				out.println("<tr>");
				out.println("<td id=T30 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=T31 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("<td id=T32 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("<td id=T33 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("</tr>");
				
				
				//End by Dineth on 2008-10-01
				out.println("<tr>");
				out.println("<td>&nbsp;</td><td></td><td></td><td></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td id=R1 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=R2  align=right></td>");
				out.println("<td id=R7 STYLE=\"{font-weight: bold;color: green}\" align=right></td>");
				out.println("<td id=R8 STYLE=\"{font-weight: bold;color: green}\" align=right></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td id=R3  STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=R4   align=right></td>");
				out.println("<td id=R9  STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("<td id=R10 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td id=R5  STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=R6   align=right></td>");
				out.println("<td id=R11 STYLE=\"{font-weight: bold;color: green}\" align=right></td>");
				out.println("<td id=R12 STYLE=\"{font-weight: bold;color: green}\" align=right></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td id=R13 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=R14  align=right></td>");
				out.println("<td id=R15 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("<td id=R16 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td id=M1 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=M2  align=right></td>");
				out.println("<td id=M3 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("<td id=M4 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("</tr>");
				
				//out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");  
				
				
				out.println("</table>");
				out.println("</div>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<div id=rec><input type=hidden name=hid_count value=0></div>");
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
				out.println("<td><input type=button name=reset_1 value=\"New\" class=mainbut onclick=load_screen_status(\"NEW\"); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");//document.Form1.OPTION_DESC.value
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				
				//out.println("<td><input type=button name=edit_1 value=\"Delete\" class=mainbut onclick=load_screen_status(\"DELETE\"); onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				//out.println("<td width=10%>&nbsp;</td>");
				//out.println("<td><input type=button name=delete value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel value=\"Re-active\" class=mainbut onclick=befor_active(); onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=b_submit_1 value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");  // commented by udara 06-02-2014
				out.println("<td><input type=button name=b_submit_1 value=\"Save\" class=mainbut onclick=validate_termi_date(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");  // added by udara 06-02-2014
				out.println("<td>&nbsp;</td>");
				out.println("<td><input class='mainbut' type='button' name='BUT_HELP_MAIN_1' value=\"Help\" onClick=\"help_update()\" disabled>  </td>"); 
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset_1 value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Cancel\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back_1 value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=cal_1 value=\"Calculate\" class=mainbut onclick=befor_cal();></td>");
				//out.println("<td><input type=button name=cal_1 value=\"Closing Letter\" class=mainbut onclick=befor_cal_new(); style={width:100px;} ></td>"); // added by udara on 11-12-2012
				
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
				//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				
			}
			/*else if(m_chksql.trim().equals("get_term_details")){
				
			String m_finance_no = req.getParameter("finance_no");
			String m_vehicle_no = req.getParameter("veh_no");
				
				callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
							"AF_CR_TEMP_TERMINATION_CAPITAL(:1,:2,:3,:4,:5,:6,:7,:8,:9);END;");
				callstmt1.setString(1 ,m_username);
		callstmt1.setString(2 ,m_vehicle_no);
		callstmt1.setString(3 ,m_finance_no);
				callstmt1.registerOutParameter(4,java.sql.Types.CHAR);
				callstmt1.registerOutParameter(5,java.sql.Types.CHAR);
				callstmt1.registerOutParameter(6,java.sql.Types.CHAR);
				callstmt1.registerOutParameter(7,java.sql.Types.CHAR);
				callstmt1.registerOutParameter(8,java.sql.Types.CHAR);
		callstmt1.registerOutParameter(9,java.sql.Types.CHAR);
		
				//out.println("t5");
			callstmt1.execute();
				//out.println("t6");
			
		//boolean flag = rs.next();
			out.println("<Root>");
			//for(; flag; flag = rs.next())				{
				out.println("<ITEM>");
				out.println("<CAP_AMT>"+  nf.format(callstmt1.getDouble(4))  + "</CAP_AMT>");
				out.println("<NIBSM>"  +  nf.format(callstmt1.getDouble(5))  + "</NIBSM>");
				out.println("<AMI_AMT>"+  nf.format(callstmt1.getDouble(6))  + "</AMI_AMT>");
				out.println("<CAP_OUT>"+  nf.format(callstmt1.getDouble(7))  + "</CAP_OUT>");
				out.println("<AMI_CAP>"+  nf.format(callstmt1.getDouble(8))  + "</AMI_CAP>");
				
				double m_per = ((callstmt1.getDouble(7))/callstmt1.getDouble(4))*100; 
				out.println("<CAP_PER>"+  nf.format(m_per)  + "</CAP_PER>");
				out.println("<CAP_PER>"+  nf.format(callstmt1.getDouble(9))  + "</CAP_PER>");
				out.println("</ITEM>");
			//}
																	
			//out.println("</DATA>");
			out.println("</Root>");
	
	}*/
			else if(m_chksql.trim().equals("generate_letter")){
				
				
				
				String m_Letter_date="";
				String m_c_code="";
				String m_name="";
				String m_add1="";
				String m_add2="";
				String m_city_desc="";
				String m_designation="";
				
				String m_cont_person="";
				String m_desig_payment="";
				String m_client_type="C";
				
				String m_print=req.getParameter("print");
				String m_due_amount	=	req.getParameter("due_amount");
				String m_due_rent	=	req.getParameter("due_rent");
				String m_odi_amount = req.getParameter("odi_amount");
				String m_sale_price	=req.getParameter("sale_price");//stk
				String m_tot_chrg	= req.getParameter("tot_chrg");
				String m_client	= req.getParameter("client");
				String m_agreemnt_no = req.getParameter("agreemnt_no");
				String m_term_type	=	req.getParameter("term_type");
				
				out.println("<html><head>"); 
				out.println("<title></title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");	
				out.println("<script>");
				//===========Modified by Dineth on 2008-09-19
				
				//---------------------------------
				
				out.println("function print_data(){");
				out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule?chksql=generate_letter&due_amount="+m_due_amount+"&due_rent="+m_due_rent+"&odi_amount="+m_odi_amount+"&sale_price="+m_sale_price+"&tot_chrg="+m_tot_chrg+"&agreemnt_no="+m_agreemnt_no+"&term_type="+m_term_type+"&client="+m_client+"&print=false\";");//ss 
				
				out.println(" window.location.href=m_url;");
				
				//===========End by Dineth on 2008-09-19
				out.println(" m_table.innerHTML=\"\"; ");
				out.println(" window.print();");
				
				
				out.println("}");
				
				//---------------------------------
				
				out.println("function add_button(){");
				
				if (m_print.trim().equals("false")) {
					out.println("m_table.innerHTML=\"\" ");
				}
				else
				{
					out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_data()\"></td></tr>';"); 
					out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
					out.println("m_writedata+'</table>';");
				}
				
				out.println("}");
				
				
				
				out.println("</script>");				
				out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");	
				out.println("<body bgcolor='white'><br>");
				out.println("<form name='Form1'>");	
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");
				
				
				//	out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">");
				//	out.println("<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"window.print();\"></td></tr></table>");
				
				
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MONTH-YYYY') FROM DUAL ");								
				boolean more = rs.next();
				
				if(more){
					m_Letter_date=rs.getString(1);
				}
				//out.println (	" SELECT "+
				rs1 = stmt1.executeQuery (	" SELECT "+
					" NVL(A.CLIENT_CODE,'-'), "+//1
					" NVL(UPPER(A.FULL_NAME),'-'), "+//2
					" NVL(UPPER(A.REGISTERED_ADDRESS1),'-'), "+//3
					" NVL(UPPER(A.REGISTERED_ADDRESS2),'-'), "+//4
					" UPPER(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),'-')), "+//5
					" NVL(DESIGNATION,'-') "+ //6
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A "+
					" WHERE  A.CLIENT_CODE='"+m_client+"' ");
				
				more = rs1.next();
				if(more){
					m_c_code=rs1.getString(1);
					m_name=rs1.getString(2);
					m_add1=rs1.getString(3);
					m_add2=rs1.getString(4);
					m_city_desc=rs1.getString(5);
					m_designation=rs1.getString(6);
					
				}
				
				
				
				
				
				if(m_term_type.trim().equals("ERL_TER")){
					
					out.println("<p style='text-align:left'>");										
					out.println("<blockquote><font size=2><p style='text-align:justify'>");	
					out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='*%' >"+m_Letter_date+"</td></tr>");
					//out.println("<tr><td width='*%'  >"+m_name+" </td></tr>");//
					out.println("<tr><td width='*%'  >"+m_designation+" </td></tr>");
					out.println("<tr><td width='*%'  >"+m_add1+" </td></tr>");//
					out.println("<tr><td width='*%'  > "+m_add2+"</td></tr>");//
					out.println("</TABLE>");
					out.println("</font></p></blockquote>");
					out.println("<br><br>");
					out.println("<blockquote><font size=2><p style='text-align:justify'>");				
					out.println("<table border='0' width='100%' class='table'> ");	
					out.println("<tr ><td width='*%'  style='{text-align:left;}'><br>Dear Sir/Madam ,</td></tr>");
					out.println("</TABLE><br><br>");
					out.println("<table border='0' width='100%' class='table'> ");	
					out.println("<tr ><td width='*%'  style='{text-align:left;}'><u><b>LEASE AGREEMENT NO:"+m_agreemnt_no+"</b></u></td></tr>");
					out.println("</TABLE><br>");		
					out.println("</font></p></blockquote>");
					out.println("<blockquote><font size=5><p style='text-align:justify'  >");			
					out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='*%'  style='text-align:justify'>");
					out.println("We refer to your recent inquiry on the captioned subject and we give below"+
						" the early termination settlement amount <b>as at "+m_Letter_date+"</b> .<br>");
					out.println("</td></tr>");									
					out.println("</table>");
					out.println("</font></p></blockquote>");				
					out.println("<blockquote><font size=5><p style='text-align:justify'  >");				
					out.println("<table border='0' width='350' class='table'>"); 
					out.println("<tr><td width='*%'  style='text-align:justify'></td>");
					out.println("<td width='*%'  style='text-align:right'>Rs.</td>");
					out.println("</tr>");
					out.println("<tr><td width='80%' style='text-align:justify'>Overdue Rentals</td>");
					out.println("<td width='*%'  style='text-align:right'>"+m_due_amount+"</td>");
					out.println("</tr>");	
					out.println("<tr><td width='*%' style='text-align:justify'>Discounted Future Rental with VAT</td>");
					out.println("<td width='*%' style='text-align:right'>"+m_due_rent+"</td>");
					out.println("</tr>");	
					out.println("<tr><td width='*%' style='text-align:justify'>Sale price</td>");
					out.println("<td width='*%' style='text-align:right'>"+m_sale_price+"</td>");
					out.println("</tr>");	
					out.println("<tr><td width='*%' style='text-align:justify'>Overdue Interest Charges</td>");
					out.println("<td width='*%' style='text-align:right'>"+m_odi_amount+"</td>");
					out.println("</tr>");
					out.println("<tr><td width='*%' style='text-align:justify'></td>");
					out.println("<td width='*%' style='text-align:right'>______________</td>");
					out.println("</tr>");
					out.println("<tr><td width='*%' style='text-align:justify'>Total Settlement Amount</td>");
					out.println("<td width='*%' style='text-align:right'>"+m_tot_chrg+"</td>");
					out.println("</tr>");
					out.println("<tr><td width='*%'style='text-align:justify'></td>");
					out.println("<td width='*%' style='text-align:right'>==========</td>");
					out.println("</tr>");
					out.println("</table><br>");						
					out.println("</font></p></blockquote>");
					out.println("<blockquote><font size=2><p style='text-align:justify'   >");				
					out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='*%' style='text-align:justify'>");
					out.println("Since you wish to terminate the above mentioned Lease agreement, please settle the balance shown above on or before the settlement date.");
					out.println("</td></tr>");									
					out.println("</table><br>");															
					out.println("</font></p></blockquote>");
					
					// Added by Udara Somathilake on 17-05-2210
					out.println("<blockquote><font size=2><p style='text-align:justify'   >");				
					out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='*%' style='text-align:justify'>");
					out.println(""+
						" This offer is treated as expired after the settlement date mentioned above."+
						" ");
					out.println("</td></tr>");									
					out.println("</table><br>");															
					out.println("</font></p></blockquote>");
					
					
					out.println("<br><br></td><br><br></td> ");
					
					
					
					out.println("<blockquote><font size=2><p style='text-align:justify'   >");				
					out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='*%'   style='text-align:justify'>Yours faithfully.</td> ");
					out.println("</tr>");	
					out.println("<tr><td width='*%'   style='text-align:justify'><B>LAKDERANA INVESTMENTS LIMITED</B></BR></BR></BR></td> ");
					out.println("</tr>");	
					out.println("<tr><td width='*%'   style='text-align:justify'>................................<br><br></td> ");
					out.println("</tr>");	
					out.println("<tr><td width='*%'   style='text-align:justify'>Authorised Signatory.</td> ");
					out.println("</tr>");	
					out.println("</table><br>");															
					out.println("</font></p></blockquote>");
					
					out.println("</form></body></html>");
					
					
				}else{
					
					out.println("<p style='text-align:left'>");										
					out.println("<blockquote><font size=2><p style='text-align:justify'>");	
					out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='*%' >"+m_Letter_date+"</td></tr>");
					//out.println("<tr><td width='*%'  >"+m_name+" </td></tr>");//
					out.println("<tr><td width='*%'  >"+m_designation+" </td></tr>");
					out.println("<tr><td width='*%'  >"+m_add1+" </td></tr>");//
					out.println("<tr><td width='*%'  > "+m_add2+"</td></tr>");//
					out.println("</TABLE>");
					out.println("</font></p></blockquote>");
					out.println("<br><br>");
					out.println("<blockquote><font size=2><p style='text-align:justify'>");				
					out.println("<table border='0' width='100%' class='table'> ");	
					out.println("<tr ><td width='*%'  style='{text-align:left;}'><br>Dear Sir/Madam ,</td></tr>");
					out.println("</TABLE><br><br>");
					out.println("<table border='0' width='100%' class='table'> ");	
					out.println("<tr ><td width='*%'  style='{text-align:left;}'><u><b>AGREEMENT NO:"+m_agreemnt_no+"</b></u></td></tr>");
					out.println("</TABLE><br>");		
					out.println("</font></p></blockquote>");
					out.println("<blockquote><font size=5><p style='text-align:justify'  >");			
					out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='*%'  style='text-align:justify'>");
					out.println("We wish to inform you that the above lease has expired on "+m_Letter_date+","+
						"please be informed that the sum of <b>Rs."+m_tot_chrg+"</b> is due as detailed below.<br>");
					out.println("</td></tr>");									
					out.println("</table>");
					out.println("</font></p></blockquote>");				
					out.println("<blockquote><font size=5><p style='text-align:justify'  >");				
					out.println("<table border='0' width='350' class='table'>"); 
					out.println("<tr><td width='*%'  style='text-align:justify'></td>");
					out.println("<td width='*%'  style='text-align:right'>Rs.</td>");
					out.println("</tr>");
					out.println("<tr><td width='80%' style='text-align:justify'>Overdue Rentals</td>");
					out.println("<td width='*%'  style='text-align:right'>"+m_due_amount+"</td>");
					out.println("</tr>");	
					//out.println("<tr><td width='*%' style='text-align:justify'>Discounted Future Rental with VAT</td>");
					//out.println("<td width='*%' style='text-align:right'>"+m_due_rent+"</td>");
					//out.println("</tr>");	
					out.println("<tr><td width='*%' style='text-align:justify'>Sale price</td>");
					out.println("<td width='*%' style='text-align:right'>"+m_sale_price+"</td>");
					out.println("</tr>");	
					out.println("<tr><td width='*%' style='text-align:justify'>Overdue Interest Charges</td>");
					out.println("<td width='*%' style='text-align:right'>"+m_odi_amount+"</td>");
					out.println("</tr>");
					out.println("<tr><td width='*%' style='text-align:justify'></td>");
					out.println("<td width='*%' style='text-align:right'>______________</td>");
					out.println("</tr>");
					out.println("<tr><td width='*%' style='text-align:justify'>Total Settlement Amount</td>");
					out.println("<td width='*%' style='text-align:right'>"+m_tot_chrg+"</td>");
					out.println("</tr>");
					out.println("<tr><td width='*%'style='text-align:justify'></td>");
					out.println("<td width='*%' style='text-align:right'>==========</td>");
					out.println("</tr>");
					out.println("</table><br>");						
					out.println("</font></p></blockquote>");
					out.println("<blockquote><font size=2><p style='text-align:justify'   >");				
					out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='*%' style='text-align:justify'>");
					out.println("In order to finalize this transaction by sale of vehicles, please forward your "+
						" remittance for <b>Rs."+m_tot_chrg+"</b> on or before "+m_Letter_date+" . "+
						" Overdue interest would be charged on this date onwards.");
					out.println("</td></tr>");									
					out.println("</table><br>");															
					out.println("</font></p></blockquote>");
					
					
					
					
					
					out.println("<br><br></td><br><br></td> ");
					
					
					
					out.println("<blockquote><font size=2><p style='text-align:justify'   >");				
					out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='*%'   style='text-align:justify'>Yours faithfully.</td> ");
					out.println("</tr>");	
					out.println("<tr><td width='*%'   style='text-align:justify'><B>LAKDERANA INVESTMENTS LIMITED</></BR></BR></BR></td> ");
					out.println("</tr>");	
					out.println("<tr><td width='*%'   style='text-align:justify'>................................<br><br></td> ");
					out.println("</tr>");	
					out.println("<tr><td width='*%'   style='text-align:justify'>Authorised Signatory.</td> ");
					out.println("</tr>");	
					out.println("</table><br>");															
					out.println("</font></p></blockquote>");
					
					out.println("</form></body></html>");
					
					
				}
				
				
				
				
				/*
				out.println(" SELECT "+
				//rs2 = stmt2.executeQuery (" SELECT "+
  				  " NVL(CLIENT_CODE,'-'), "+
  				  " NVL(FULL_NAME,'-') "+
  				  " FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
  				  " WHERE CLIENT_CODE='"+m_client+"' ");

					more = rs2.next();
						if(more){
							m_client_name = rs2.getString(2);
						 }
						
			*/
				
				
				
			}
			else if(m_chksql.trim().equals("get_Vehicles")){
				
				String m_Lease_no     = req.getParameter("Lease_no");
				
				//out.println(" SELECT B.CHASSIS_NO,B.REG_NO,VAT_PERCENTAGE,VAT_ON_RENTAL,VAT_APP,B.INVOICE_NO, "+
				rs = stmt.executeQuery(" SELECT B.CHASSIS_NO,B.REG_NO,VAT_PERCENTAGE,VAT_ON_RENTAL,VAT_APP,B.INVOICE_NO, "+
					"        "+m_schema_name+".AF_CO_GET_MODEL_DESC(B.MODEL_CODE) "+ 
					" FROM   "+m_schema_name+".AF_CO_PRO_APP_PRICING A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
					" WHERE  A.PRICING_NO     = B.PRICING_NO AND "+
					"        A.APPLICATION_NO = B.APPLICATION_NO AND "+  
					"        A.PRO_INVOICE_NO = B.INVOICE_NO AND "+
					"				B.APPLICATION_NO IN (SELECT APPLICATION_NO "+
					"                             FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					"                             WHERE  FINANCE_NO='"+m_Lease_no+"') AND "+
					"        B.ACTIVE_STATUS='Y' ");
				
				/*"SELECT REG_NO "+
										"FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
										"WHERE APPLICATION_NO IN (SELECT APPLICATION_NO "+
										"                         FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
										"                         WHERE  FINANCE_NO='"+m_Lease_no+"') AND "+
										"      ACTIVE_STATUS='Y' "); */
				
				out.println("<table class=table border='0' width='100%' >");
				
				
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td WIDTH=10%>Id No</td>");
				out.println("<td WIDTH=10%>Chassis No</td>");
				out.println("<td WIDTH=10%>Vehicle No</td>");
				out.println("<td WIDTH=10%>Asset Description</td>");
				out.println("<td WIDTH=10%>VAT</td>");
				//Modified by Dineth on 2008-10-24
				//out.println("<td colspan=6>Residual value</td>");
				out.println("<td colspan=6>Sales Price</td>");
				out.println("</tr>");
				int j=0;
				while(rs.next()){
					//out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
					out.println("<tr class=tr_input >");
					out.println("<td width=10%>"+rs.getString(6)+"<input type=hidden name=\"INVOICE_NO_"+j+"\"  value=\""+rs.getString(6)+"\"></td>");
					out.println("<td width=10%>"+rs.getString(1)+"<input type=hidden name=\"CHASSIS_NO_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
					out.println("<td width=10%>"+rs.getString(2)+"<input type=hidden name=\"VEHICLE_NO_"+j+"\"  value=\""+rs.getString(2)+"\"></td>");
					out.println("<td width=10%>"+rs.getString(7)+"</td>");
					out.println("<td width=10%>"+rs.getString(3)+"<input type=hidden name=\"VAT_"+j+"\"  value=\""+rs.getString(3)+"\"><input type=hidden name=\"VATR_"+j+"\"  value=\""+rs.getString(4)+"\"></td>");
					if(rs.getString(1)==null){
						//out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\" ></td>");//remove disabled ---//comment by Prabash on 09-02-2012
						out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\" onblur=\"check_number_decimal(this,30)\" ></td>");//remove disabled----////Added by Prabash on 09-02-2012
						out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" ></td>");
					}else{
						//out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\" ></td>"); ---//comment by Prabash on 09-02-2012
						out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\" onblur=\"check_number_decimal(this,30)\" ></td>");//Added by Prabash on 09-02-2012
						out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" ></td>");
					}
					j=j+1;
					/*if(rs.next()){
						out.println("<td width=10%>"+rs.getString(6)+"<input type=hidden name=\"INVOICE_NO_"+j+"\"  value=\""+rs.getString(6)+"\"></td>");
						out.println("<td width=10%>"+rs.getString(1)+"<input type=hidden name=\"CHASSIS_NO_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
						out.println("<td width=10%>"+rs.getString(2)+"<input type=hidden name=\"VEHICLE_NO_"+j+"\"  value=\""+rs.getString(2)+"\"></td>");
						out.println("<td width=10%>"+rs.getString(3)+"<input type=hidden name=\"VAT_"+j+"\"  value=\""+rs.getString(3)+"\"><input type=hidden name=\"VATR_"+j+"\"  value=\""+rs.getString(4)+"\"></td>");
						if(rs.getString(1)==null){
							out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
							out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" disabled></td>");
						}else{
							out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\"></td>");
							out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" ></td>");
						}
						j=j+1;
					}else{
						out.println("<td width=10%></td><td width=10%></td><td width=10%></td><td width=10%></td><td width=10%></td>");
					}*/
					out.println("</tr>");
				}
				
				out.println("<input type=hidden name=hid_vcount value=\""+j+"\"></table>");
				
			}
			else if(m_chksql.trim().equals("get_Termi_Det")){
				
				String m_Termination_no    = req.getParameter("Termination_no");
				String mm_Disco_rate       = req.getParameter("Disco_rate");
				
				
				out.println ("SELECT TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'), PERCENTAGE, RENTAL_AMOUNT,  "+
					//	rs = stmt.executeQuery ("SELECT TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'), PERCENTAGE, RENTAL_AMOUNT,  "+
					"       RENTAL_PV,TERM_AMOUNT, TERM_PV "+
					"FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION_DETAILS "+
					"WHERE  TERMINATION_NO='"+m_Termination_no+"' "+
					"ORDER BY to_date(TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')"); 
				
				
				
				
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
				out.println("<td align=right id=rent><b></b></td>");
				out.println("<td align=right id=rpv ><b></b></td>");
				out.println("<td align=right id=term><b></b></td>");
				out.println("<td align=right id=tpv ><b></b></td>");
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
				out.println("<td align=right>"+nf.format(term)+"<input type=hidden name=h_term value="+nf.format(term)+"></td>");   //term //mm_TERMI_AMOUNT
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
				String m_SaleVal      = req.getParameter("SaleVal");
				String m_Chassis_no   = req.getParameter("chas_no");
				String m_Invoice_no   = req.getParameter("invo_no");
				String m_sum_sal      = req.getParameter("sum_sal");
				String m_Term_val     = req.getParameter("Term_val");
				
				String m_term_opt_type  = req.getParameter("term_opt_type");
				String m_closing_rate = req.getParameter("closing_rate");
				
				double mm_TERMI_AMOUNT=0.00;
				
				callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					"AF_CR_TEMP_TERMINATION_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11);END;");
				callstmt1.setString(1 ,m_Disco_rate);
				callstmt1.setString(2 ,m_Vehicle_no);
				callstmt1.setString(3 ,m_Lease_no);
				callstmt1.setString(4 ,m_username);
				callstmt1.setString(5 ,m_App_date);
				callstmt1.setString(6 ,m_client);
				callstmt1.setString(7 ,m_lease_rate);
				callstmt1.setString(8 ,m_vat_rate);
				callstmt1.setString(9 ,m_SaleVal);
				callstmt1.setString(10,m_Chassis_no);
				callstmt1.setString(11,m_Invoice_no);
				
				//out.println("t5");
				callstmt1.execute();
				//out.println("t6");
				out.println("<table class=table border='0' width='100%' >");
				
				//af_cr_get_no_future_rental,af_co_get_rentals_paid,af_co_get_no_rentals_arries,m_Term_val
				//out.println("SELECT TO_CHAR(INSTALLMENT_DATE,'DD-MM-YYYY'),PERCENTAGE,  "+
				
				// commented by udara 12-12-2012
				/*
				rs = stmt.executeQuery (
				" SELECT SUM(RENTAL_AMOUNT * '"+m_Disco_rate+"') TERMI_AMOUNT "+
				" FROM ( "+
				" SELECT TO_CHAR(INSTALLMENT_DATE,'DD-MM-YYYY') INSTALLMENT_DATE,PERCENTAGE,   "+
				" SUM(RENTAL_AMOUNT) RENTAL_AMOUNT, SUM(PV),SUM(TERMINATION_AMOUNT),    "+
				" SUM(TERMINATION_PV),INSTALLMENT_NO, "+
				" SUM(TERMINATION_PV-TERMINATION_AMOUNT)  "+
				" FROM   "+m_schema_name+".AF_CR_TBD_TERMINATION  "+
				" WHERE  ENT_USER='"+m_username+"'  "+
				" GROUP  BY INSTALLMENT_NO,INSTALLMENT_DATE, PERCENTAGE  "+
				" ) "+
				" WHERE ROWNUM='1' "+
				" ORDER BY INSTALLMENT_DATE "+
				"");
				
				if(rs.next()){
					
				mm_TERMI_AMOUNT =rs.getDouble("TERMI_AMOUNT");
				}
				
				*/
				
				// added by udara 12-12-2012
				double arrears_amount      = 0.00;
				double ceasing_charges     = 0.00;
				double insurance_charges   = 0.00;
				double visiting_charges    = 0.00;
				double total_arrears       = 0.00;
				double rental_amount       = 0.00;
				
				int no_of_future_rentals   = 0;
				
				double closing_rate        = 0.00;
				
				double normal_closing = 0.00;
				double closing_amount = 0.00;
				
				double final_closing_amount = 0.00;
				
				double rebate_amount  = 0.00;
				
				double future_capital = 0.00;
				double balance_capital = 0.00;
				double interest_for_capital = 0.00;
				
				double total_amount = 0.00;
				double mm_Termination_gainloss=0.00;
				double mm_net_temination_amount  = 0.00;
				double mm_gross_Termination_amount = 0.00;
				
				
				rs=stmt.executeQuery(" "+
					" SELECT "+
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+ // 1 client name
					" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO_NEW(A.APPLICATION_NO),'-') REG_NO, "+ // mod by udara 21-10-2013  //" NVL((SELECT REG_NO FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS WHERE APPLICATION_NO = A.APPLICATION_NO),'-') REG_NO, "+ // 2 registration no.
					
					" ( "+
					" SELECT "+ 
					" SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
					" WHERE     ACTIVE_STATUS='Y' "+
					" AND  A.FINANCE_NO = '"+m_Lease_no+"' "+
					" AND FINANCE_NO IN "+   
					" (SELECT "+
					" FINANCE_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					//" WHERE  UPPER(APPLICATION_NO)=UPPER(A.APPLICATION_NO) "+ // commented by udara 30-05-2017
					" WHERE  APPLICATION_NO=A.APPLICATION_NO "+ // added by udara 30-05-2017
					" AND APPLICATION_STATUS<>'CANCEL') "+ 
					" ) DUE_AMOUNT, "+ // 3 due amount / arrears
					" "+m_schema_name+".AF_CO_GET_INSTALMENT_AMT(A.APPLICATION_NO), "+     // 4 rental amount
					" "+m_schema_name+".AF_CR_GET_NO_FUTURE_RENTAL(A.APPLICATION_NO), "+ // 5 future rental
					" "+m_schema_name+".AF_CO_CLOSING_RATE(A.APPLICATION_NO), "+ // 6 closing rate
					
					" ( SELECT  SUM(NVL(CAPITAL_AMOUNT,0)) "+
					" FROM    "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+ 
					" WHERE   (PRO_INVOICE_NO,PRICING_NO) IN (SELECT INVOICE_NO,PRICING_NO "+
					" FROM   "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE  APPLICATION_NO =   A.APPLICATION_NO  "+
					" AND       ACTIVE_STATUS  =   'Y' "+
					" ) "+
					" AND INVOICE_NO IS NULL  "+
					" AND APPLICATION_NO = A.APPLICATION_NO  "+
					" ) OUTS_CAPITAL "+ // 7 total outstanding capital
					
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
					" WHERE A.FINANCE_NO = '"+m_Lease_no+"' "+
					" ");
				
				if(rs.next()){
					arrears_amount       = rs.getDouble(3);
					rental_amount        = rs.getDouble(4);
					no_of_future_rentals = rs.getInt(5);
					//closing_rate         = rs.getDouble(6);
					future_capital       = rs.getDouble(7);
				}
				
				closing_rate = Double.parseDouble(m_closing_rate);
				
				total_arrears         = arrears_amount + ceasing_charges + insurance_charges + visiting_charges + total_arrears;
				normal_closing        = (no_of_future_rentals * rental_amount) + total_arrears;
				closing_amount  	  = (rental_amount * closing_rate) + total_arrears;
				final_closing_amount  = closing_amount;
				rebate_amount  = normal_closing - closing_amount;
				//future_capital = closing_amount - rebate_amount;
				balance_capital = future_capital;
				interest_for_capital = future_capital * 0.05;
				total_amount = future_capital + interest_for_capital + total_arrears;
				
				
				if(m_term_opt_type.equals("NORMAL_CLOSING_OPT"))
					mm_TERMI_AMOUNT = normal_closing;
				else if(m_term_opt_type.equals("CLOSING_OPT"))
					mm_TERMI_AMOUNT = closing_amount;
				else if(m_term_opt_type.equals("RABATE_OPT"))
					mm_TERMI_AMOUNT = total_amount;
				
				
				mm_net_temination_amount   =  mm_TERMI_AMOUNT - total_arrears ; //+Double.parseDouble(m_Term_val)
				mm_Termination_gainloss     = mm_net_temination_amount - future_capital;
				mm_gross_Termination_amount = mm_net_temination_amount+ mm_Termination_gainloss;
				
				
				
				// end by udara on 12-12-2012
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(INSTALLMENT_DATE,'DD-MM-YYYY'),NVL(PERCENTAGE,0),  "+
					"       SUM(RENTAL_AMOUNT), SUM(PV),SUM(TERMINATION_AMOUNT),   "+
					"       SUM(TERMINATION_PV),INSTALLMENT_NO,"+
					"       SUM(TERMINATION_PV-TERMINATION_AMOUNT) "+
					"FROM   "+m_schema_name+".AF_CR_TBD_TERMINATION "+
					"WHERE  ENT_USER='"+m_username+"' "+
					"GROUP  BY INSTALLMENT_NO, "+
					"       INSTALLMENT_DATE, PERCENTAGE "+
					"ORDER BY TO_DATE(TO_CHAR(INSTALLMENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY'),TO_NUMBER(INSTALLMENT_NO) ");
				
				
				out.println("<tr class=tr_input style=\"display:none\" >");
				//out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td colspan=6 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("</tr>");
				
				// commented by udara on 12-12-2012
				/*
				out.println("<tr class=pdn_txtpos2>");
						out.println("<td  width='15%' >Installment Date</td>");
						out.println("<td  width='5%'  align=right>Percentage</td>");
				out.println("<td  width='15%' align=right>Rental</td>");
						out.println("<td  width='15%' align=right>P.V. at Finance Rate</td>");
						out.println("<td  width='15%' align=right>P.V. at Termination Rate</td>");
						out.println("<td  width='15%' align=right>Termination Gain / Loss</td>");
						out.println("<td  width='20%' align=right>Gross Termination</td>");
						out.println("</tr>");
						*/
				
				int j = 0;
				double rent=0;
				double rpv =0;
				double term=0;
				double tpv =0;
				double gtv =0;
				double vat =0;	
				
				out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ style=\"display:none\" >");
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
					// commented by udara on 12-12-2012		
					
					out.println("<tr class=tr_input  style=\"display:none\" >");
					if(rs.getDouble(3)>=0){ 
						out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
					}else{
						out.println("<td >NIBSM/AMI<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
					}
					out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j+"\"    value=\""+rs.getString(2)+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j+"\"        value=\""+rs.getString(3)+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j+"\"            value=\""+rs.getString(4)+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
					out.println("<td align=right>"+nf.format((rs.getDouble(5)-rs.getDouble(4)))+"<input type=hidden name=\"TER_PV_AM_N_"+j+"\" value=\""+(rs.getDouble(5)-rs.getDouble(6))+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j+"\"     value=\""+rs.getString(6)+"\"></td>");
					out.println("");
					out.println("</tr>");
					
					rent = rent  + rs.getDouble(3);
					rpv  = rpv   + rs.getDouble(4);
					term = term  + rs.getDouble(5);
					tpv  = tpv   + (rs.getDouble(5)-rs.getDouble(4));
					gtv  = gtv   + rs.getDouble(6);
					vat  = vat   + rs.getDouble(8);
					
					j=j+1;
					
					/*comment by ns on 09-01-2013*/		
					/*if(rs.next()){
										// commented by udara on 12-12-2012		
										
									  out.println("<tr class=tr_input1  style=\"display:none\" >");
										out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
	                  out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j+"\"    value=\""+rs.getString(2)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j+"\"        value=\""+rs.getString(3)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j+"\"            value=\""+rs.getString(4)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
	                  out.println("<td align=right>"+nf.format((rs.getDouble(5)-rs.getDouble(4)))+"<input type=hidden name=\"TER_PV_AM_N_"+j+"\" value=\""+(rs.getDouble(5)-rs.getDouble(6))+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j+"\"     value=\""+rs.getString(6)+"\"></td>");
										out.println("");
										out.println("</tr>");
										
										rent = rent  + rs.getDouble(3);
									  rpv  = rpv   + rs.getDouble(4);
									  term = term  + rs.getDouble(5);
									  tpv  = tpv   + (rs.getDouble(5)-rs.getDouble(4));
									  gtv  = gtv   + rs.getDouble(6);
									  vat  = vat   + rs.getDouble(8);
									  j=j+1;
									}
					*/				
					
					
				}
				
				
				out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ style=\"display:none\" >"); // add display none by udara on 13-12-2012
				out.println("<td><input type=hidden name=hid_count value="+j+">");
				out.println("</td>");
				out.println("<td ></td>");
				out.println("<td align=right>"+nf.format(rent)+" aaa <input type=hidden name=h_rent value="+nf.format(rent)+"></td>");
				out.println("<td align=right>"+nf.format(rpv) +"<input type=hidden name=h_rpv  value="+nf.format(rpv)+"></td>"); 
				out.println("<td align=right>"+nf.format(mm_net_temination_amount)+"<input type=hidden name=h_term value="+nf.format(mm_net_temination_amount)+"></td>"); //Net Term Amount //term variable replaced by ns
				out.println("<td align=right>"+nf.format(mm_Termination_gainloss) +"<input type=hidden name=h_tpv  value="+nf.format(mm_Termination_gainloss)+"></td>"); //tpv changed by ns
				out.println("<td align=right>"+nf.format(mm_net_temination_amount) +"<input type=hidden name=h_gtv  value="+nf.format(mm_net_temination_amount)+"><input type=hidden name=h_vat  value="+nf.format(vat)+"></td>");//Gross Term Amount gtv
				out.println("</tr>");
				
				//out.println(" SELECT '"+m_sum_sal+"' , "+
				rs = stmt.executeQuery (" SELECT '"+m_sum_sal+"' , "+
					"        round('"+m_sum_sal+"'*('"+m_vat_rate+"'/100)), "+
					"        '"+m_sum_sal+"'+ round('"+m_sum_sal+"'*('"+m_vat_rate+"'/100)) "+
					" FROM DUAL");
				if(rs.next()){       
					out.println("<tr class=tr_input style=\"display:none\" >");
					out.println("<td align=right colspan=6><input type=hidden name=hid_sn_val value="+rs.getString(1)+"><input type=hidden name=hid_sv_val value="+rs.getString(2)+"><input type=hidden name=hid_sg_val value="+rs.getString(3)+"></td>");
					out.println("</tr>");
				}else{
					out.println("<tr class=tr_input style=\"display:none\" >");
					out.println("<td align=right colspan=6><input type=hidden name=hid_sn_val value=0><input type=hidden name=hid_sv_val value=0><input type=hidden name=hid_sg_val value=0></td>");
					out.println("</tr>");
					
				}
				
				
				//out.println (" SELECT "+m_schema_name+".AF_CR_GET_NO_FUTURE_RENTAL(APPLICATION_NO),"+
				rs = stmt.executeQuery (" SELECT "+m_schema_name+".AF_CR_GET_NO_FUTURE_RENTAL(APPLICATION_NO),"+
					"	       "+m_schema_name+".AF_CO_GET_RENTALS_PAID_TER(FINANCE_NO),"+
					"	       "+m_schema_name+".AF_CO_GET_NO_RENTALS_ARRIES(FINANCE_NO),"+
					"        round('"+m_Term_val+"'*('"+m_vat_rate+"'/100)),"+
					"        '"+m_Term_val+"'+round('"+m_Term_val+"'*('"+m_vat_rate+"'/100)) "+//,"+
					//"        "+m_schema_name+".AF_CO_GET_CLOSURE_IRR(FINANCE_NO,'"+m_App_date+"') "+
					"	FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					"	WHERE  FINANCE_NO= '"+m_Lease_no+"'");
				
				
				
				
				
				if(rs.next()){
					out.println("<tr class=tr_input style=\"display:none\" ><input type=hidden name=F_R value=\""+nf1.format(rs.getDouble(1))+"\" >");
					out.println("<input type=hidden name=R_P value=\""+nf1.format(rs.getDouble(2))+"\"><input type=hidden name=R_A value=\""+nf1.format(rs.getDouble(3))+"\"><input type=hidden name=R_T value=\""+nf1.format(rs.getDouble(2)+rs.getDouble(3)+rs.getDouble(1))+"\">");
					out.println("<input type=hidden name=T_V value=\""+nf1.format(rs.getDouble(4))+"\"><input type=hidden name=T_S value=\""+nf1.format(rs.getDouble(5))+"\"><input type=hidden name=C_IRR value=\"\">");
				}else{
					out.println("<tr class=tr_input style=\"display:none\" ><input type=hidden name=F_R value=\"\">");
					out.println("<input type=hidden name=R_P value=\"\"><input type=hidden name=R_A value=\"\">");
					out.println("<input type=hidden name=T_V value=\"\"><input type=hidden name=T_S value=\"\"><input type=hidden name=C_IRR value=\"\">");
					
				}
				
				
				
				
				out.println("<td align=right colspan=6><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("</tr>");
				
				out.println("</table>");
				
			}else if(m_chksql.trim().equals("get_invoice_det")){
				
				String m_client      = req.getParameter("client");
				String m_finance_no  = req.getParameter("finance_no");
				
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
				out.println("<tr class=tr_input >");
				//out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td colspan=8 align=right  ><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
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
					out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
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
				
				
				out.println("<tr class=tr_input style=\"display:none\" >");
				out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				
				out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");
				
				
			}
			
			
			else if(m_chksql.trim().equals("get_Receipt")){
				
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
					out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
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
			
			else if(m_chksql.equals("SHOW_BALANCES_INFO")){
				
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Asset Financing System</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				//out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\">"); 
				
				/*
				String		Sql_pending_invoice=" SELECT "+
																	"    INVOICE_NO, "+
																	"    NET_AMOUNT, "+
																	"    VAT_AMOUNT, "+
																	"    TOTAL_AMOUNT, "+
																	"    SETTELE_AMOUNT, "+
																	"    BALANCE_TO_BE_RECEIVED, "+ 
																	"    TO_CHAR(VALUE_DATE,'DD-MM-YYYY') "+ 
																	"   FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
																	"   WHERE     ACTIVE_STATUS='Y' AND "+
																	"   FINANCE_NO IN   "+
																	"   (SELECT "+
																	"    FINANCE_NO "+ 
																	"    FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																	"    WHERE  UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
																	"           APPLICATION_STATUS<>'CANCEL' AND BALANCE_TO_BE_RECEIVED>0) ";
				
				
				
				
					rs=stmt1.executeQuery(Sql_pending_invoice);
					boolean  more =rs.next();
							
					
					if (more) {
					
						out.println("<br>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='80%' class=div_input><u><b>Invoice Pending</b></u></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						
						out.println("<br>");
					
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input ><b>Invoice No</b></td>");
						out.println("<td width='10%' class=div_input ><b>Date</b></td>");
						out.println("<td width='10%' class=div_input align='right'><b>Net</b></td>");
						out.println("<td width='15%' class=div_input align='right'><b>VAT</b></td>");
						out.println("<td width='15%' class=div_input align='right'><b>Gross</b></td>");
						out.println("<td width='15%' class=div_input align='right'><b>Settled Amount</b></td>");
						out.println("<td width='15%' class=div_input align='right'><b>Balance Outstanding</b></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<br>");
					}
					out.println("<table align='center' width='100%' class='table' >");
					while(more){
						count++;
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input align='left'>"+rs.getString(7)+"</td>");
						out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"</td>");
						
						out.println("</tr>");
						
					
						
						more = rs.next();
					}
						out.println("</table>");
					
																	
																	
									String		Sql_ODI=" SELECT "+
										"    ODI_REF_NO, "+
										"    INVOICE_NO, "+
										"    TO_DATE(ODI_DATE,'DD-MM-YYYY'), "+
										"    ODI_CAL_AMOUNT, "+
										"    ODI_BAL_AMOUNT, "+
										"    ODI_SETTLED_AMOUNT "+
										"  FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
										" WHERE INVOICE_NO IN "+
										"   (SELECT "+
										"    INVOICE_NO "+
										"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
										"    WHERE  UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
										"           ACTIVE_STATUS='Y') "+
										"   AND    ODI_BAL_AMOUNT=0     ";
			
					rs=stmt1.executeQuery(Sql_ODI);
						more =rs.next();
						
					
					
					if (more) {
					
						out.println("<br>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='80%' class=div_input><u><b>Over Due Interest Pending</b></u></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						
						out.println("<br>");
					
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input ><b>ODI Ref No</b></td>");
						out.println("<td width='15%' class=div_input align='right'><b>Invoice No</b></td>");
						out.println("<td width='15%' class=div_input align='right'><b>ODI Date</b></td>");
						out.println("<td width='15%' class=div_input align='right'><b>Amount</b></td>");
						out.println("<td width='15%' class=div_input align='right'><b>Balance Amount</b></td>");
						out.println("<td width='15%' class=div_input align='right'><b>Settled Amount</b></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<br>");
					}
					out.println("<table align='center' width='100%' class='table' >");
					while(more){
						count++;
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
						out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"</td>");
						
						out.println("</tr>");
						
						more = rs.next();
					}
						out.println("</table>");
					
					*/
				String		Sql_Unallocated=" SELECT "+ 
					"  A.REC_NO, "+
					"  NVL(A.PAYER_ACC_NO,'-'), "+
					"  NVL(A.PAYER_BRANCH_CODE,'-'), "+
					"  A.REC_AMOUNT, "+
					"  NVL(A.ACC_NO,'-'), "+
					"  NVL(A.BRANCH_CODE,'-') ,"+
					"  TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') "+ //added by nuwan de silva on 18-09-07
					//  "  STATUS "+
					"  FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+
					"       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
					"  WHERE  A.REC_NO=B.REC_NO  AND "+
					"         B.BAL_TOBE_RECEIVE<>0 AND STATUS <> 'C' "+
					//"         AND UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')  "; // commented by udara 30-05-2017
					"         AND CLIENT_CODE='"+m_client_code+"'  "; // added by udara 30-05-2017
				
				rs=stmt1.executeQuery(Sql_Unallocated);
				boolean more =rs.next();
				
				
				
				if (more) {
					
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><u><b>Un Allocated Receipt</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input ><b>Receipt No</b></td>");
					out.println("<td width='10%' class=div_input ><b>Date</b></td>");
					out.println("<td width='10%' class=div_input ><b>Payer Account No</b></td>");
					out.println("<td width='15%' class=div_input ><b>Payer Branch Code</b></td>");
					out.println("<td width='15%' class=div_input ><b>Account No</b></td>");
					out.println("<td width='15%' class=div_input ><b>Branch Code</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Receipt Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input >"+rs.getString(7)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(3)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(5)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(6)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");
					
					out.println("</tr>");
					
					more = rs.next();
				}
				out.println("</table>");
				
				
				/*				
										String		Sql_Realisation=" SELECT "+
								"    REC_NO,"+
								"    SETTLE_MODE,"+
								"    NVL(PAYER_ACC_NO,'-'), "+
								"    NVL(PAYER_BRANCH_CODE,'-'),"+
								"    REC_AMOUNT, "+
								"    DECODE(STATUS,'B','Banked','E','Entered') ,"+
								"    TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') "+ //added by nuwan de silva on 18-09-07
								"    FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
								"    WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
								"    SETTLE_MODE <>'CASH'  AND "+
								"    STATUS IN('B','E') ";
					
						
								rs=stmt1.executeQuery(Sql_Realisation);
									more =rs.next();
									
								
								
								if (more) {
								
									
									out.println("<br>");
									out.println("<table align='center' width='100%' class='table' >");
									out.println("<tr>");
									out.println("<td width='1%'></td>"); 
									out.println("<td width='80%' class=div_input><u><b>Receipt Pending Realisation </b></u></td>");
									out.println("<td width='*%'></td>");
									out.println("</tr>");
									out.println("</table>");
									
									out.println("<br>");
								
									out.println("<table align='center' width='100%' class='table' >");
									out.println("<tr>");
									out.println("<td width='1%'></td>"); 
									out.println("<td width='15%' class=div_input><b>Receipt</b></td>");
									out.println("<td width='10%' class=div_input ><b>Date</b></td>");
									out.println("<td width='10%' class=div_input><b>Settle Mode</b></td>");
									out.println("<td width='15%' class=div_input><b>Account No</b></td>");
									out.println("<td width='15%' class=div_input><b>Payer Branch Code</b></td>");
									out.println("<td width='15%' class=div_input><b>Status</b></td>");
									out.println("<td width='15%' class=div_input align='right'><b>Receipt Amount</b></td>");
									out.println("</tr>");
									
									out.println("</table>");
									out.println("<br>");
								}
								out.println("<table align='center' width='100%' class='table' >");
								while(more){
									count++;
									
									out.println("<tr>");
									out.println("<td width='1%'></td>"); 
									out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
									out.println("<td width='10%' class=div_input >"+rs.getString(7)+"</td>");
									out.println("<td width='10%' class=div_input >"+rs.getString(2)+"</td>");
									out.println("<td width='15%' class=div_input >"+rs.getString(3)+"</td>");
									out.println("<td width='15%' class=div_input >"+rs.getString(4)+"</td>");
									out.println("<td width='15%' class=div_input >"+rs.getString(6)+"</td>");
									out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
									
									out.println("</tr>");
									
									more = rs.next();
								}
									out.println("</table>");
								
												String		Sql_POD=" SELECT "+
												"  POD_REF_NO, "+
												// "  FINANCE_NO, "+
												"  NVL(CHEQUE_NO,'-'), "+
												"  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'),  "+
												"  NVL(PAYER_ACC_NO,'-'), "+
												"  NVL(PAYER_BRANCH_CODE,'-'), "+
												"  CHEQUE_AMOUNT "+
												"  FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
												"  WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
												"  STATUS='INV'   ";
					
					
						
								rs=stmt1.executeQuery(Sql_POD);
									more =rs.next();
									
								
								
								if (more) {
								
									
									out.println("<br>");
									out.println("<table align='center' width='100%' class='table' >");
									out.println("<tr>");
									out.println("<td width='1%'></td>"); 
									out.println("<td width='80%' class=div_input><u><b>Post Dated Cheque </b></u></td>");
									out.println("<td width='*%'></td>");
									out.println("</tr>");
									out.println("</table>");
									
									out.println("<br>");
								
									out.println("<table align='center' width='100%' class='table' >");
									out.println("<tr>");
									out.println("<td width='1%'></td>"); 
									out.println("<td width='15%' class=div_input><b>POD Ref.No</b></td>");
									out.println("<td width='15%' class=div_input><b>Cheque No</b></td>");
									out.println("<td width='15%' class=div_input><b>Cheque Date</b></td>");
									out.println("<td width='15%' class=div_input><b>Payer Account No</b></td>");
									out.println("<td width='15%' class=div_input ><b>Payer Branch Code</b></td>");
									out.println("<td width='15%' class=div_input align='right' ><b>cheque Amount</b></td>");
									out.println("</tr>");
									
									out.println("</table>");
									out.println("<br>");
								}
								out.println("<table align='center' width='100%' class='table' >");
								while(more){
									count++;
									
									out.println("<tr>");
									out.println("<td width='1%'></td>"); 
									out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_POD_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
									out.println("<td width='15%' class=div_input >"+rs.getString(2)+"</td>");
									out.println("<td width='15%' class=div_input >"+rs.getString(3)+"</td>");
									out.println("<td width='15%' class=div_input >"+rs.getString(4)+"</td>");
									out.println("<td width='15%' class=div_input >"+rs.getString(5)+"</td>");
									out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"</td>");
									
									out.println("</tr>");
									
									more = rs.next();
								}
									out.println("</table>");
					*/
				out.println("</body>"); 
				out.println("</HTML>"); 
				
				
			}
			
			// added by udara 06-01-2021
			if (m_chksql.trim().equals("getMoratoriumValues")){
								String finNo = req.getParameter("Lease_no");
								
								
					rs = stmt.executeQuery(" SELECT " + m_schema_name + ".AF_CO_GET_MORA_FLAG_STATUS(FINANCE_NO) MORATORIUM_STATUS " + 
									",NVL(" + m_schema_name + ".AF_CO_GET_FUTURE_DEBIT_AMOUNT(A.FINANCE_NO,NULL),0) FUTURE_DEBIT "+
									" FROM AF_CO_PRO_APPLICATION_DETAILS A "+
									" WHERE FINANCE_NO = '"+finNo+"' ");
								
					while (rs.next()){
							out.println(rs.getString(1) +"-"+rs.getString(2));									
					}
								
			}
			// added by udara 06-01-2021
			
			
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
