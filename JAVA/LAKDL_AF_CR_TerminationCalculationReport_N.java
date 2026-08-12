/*
// header - edit "Data/yourJavaHeader" to customize
// contents - edit "EventHandlers/Java file/onCreate" to customize
//
*/
//Created by Dineth Meemanage on 2008-10-21
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;

import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_TerminationCalculationReport_N extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1,nf2;
	public ResultSet rs,rs1,rs2;
	public String m_chksql;
	ServletOutputStream out = null;
	
	public /*synchronized*/ void service(HttpServletRequest req, HttpServletResponse res)
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
			double m_res_value_1=0;
			
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
			//nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
			nf1.setMinimumFractionDigits(4);
			
			nf2 = java.text.NumberFormat.getInstance(Locale.US);   
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
				String m_termination_no=req.getParameter("TERMINATION_NO");
				String m_finance_no=req.getParameter("FINANCE_NO");
				String m_termination_type=req.getParameter("TERMINATION_TYPE");
				//Newly Added by Dineth
				rs = stmt.executeQuery("SELECT TERMINATION_NO, "+//1
					" NVL(FINANCE_NO,'-'), "+//2
					" NVL(APPLICATION_NO,'-'), "+//3
					" NVL(CLIENT_CODE,'-'), "+//4
					" NVL(TO_CHAR(TERMINATION_VALIDITY_DATE,'DD-MM-YYYY'),'-'), "+//5
					" NVL(TO_CHAR(APPLY_DATE,'DD-MM-YYYY'),'-'), "+//6
					" NVL(REQUESTED_BY,'-'), "+//7
					" NVL(RATE,0), "+//8
					" NVL(AMOUNT,0), "+//9
					" NVL(REMARKS,'-'), "+//10
					" NVL(CHARGES,0), "+//11
					" NVL(TERMINATION_COUNT,0), "+//12
					" NVL(DUE_AMOUNT,0), "+//13
					" NVL(REG_NO,'-'), "+//14
					" NVL(RESIDUAL_VALUE,0), "+//15
					" NVL(VAT_PER,0), "+//16
					" NVL(GAIN_LOSS,0), "+//17
					" NVL(NET_AMOUNT,0), "+//18
					" NVL(NET_RENTALS,0), "+//19
					" NVL(ODI_AMOUNT,0), "+//20
					" NVL(ODI_ADJUSTMENT,0), "+//21
					" NVL(ODI_NET,0), "+//22
					" NVL(TERMINATION_TYPE,'-'), "+//23
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-'), "+//24
					" '' "+
					// " NVL(CLOSURE_IRR,0) "+//25
					" FROM "+m_schema_name+".AF_CR_PRO_TERMINATION "+
					" WHERE TERMINATION_NO='"+m_termination_no+"'");
				
				String m_application_no_1="";
				String m_client_code_1="";
				String m_ter_validity_date_1="";
				String m_apply_date_1="";
				String m_requested_by_1="";
				double m_rate_1=0;
				double m_amount_1=0;
				String m_remarks_1="";
				double m_charges_1=0;
				double m_term_count_1=0;
				double m_due_amount_1=0;
				String m_reg_no_1="";
				//double m_res_value_1=0;
				double m_vat_per_1=0;
				double m_gain_loss_1=0;
				double m_net_amount_1=0;
				double m_net_rentals_1=0;
				double m_odi_amount_1=0;
				double m_odi_adjustment_1=0;
				double m_odi_net_1=0;
				String m_term_type_1="";
				String m_client_name_1="";
				double m_closure_irr_1=0;
				while(rs.next()){
					m_application_no_1=rs.getString(3);
					m_client_code_1=rs.getString(4);
					m_ter_validity_date_1=rs.getString(5);
					m_apply_date_1=rs.getString(6);
					m_requested_by_1=rs.getString(7);
					m_rate_1=rs.getDouble(8);
					m_amount_1=rs.getDouble(9);
					m_remarks_1=rs.getString(10);
					m_charges_1=rs.getDouble(11);
					m_term_count_1=rs.getDouble(12);
					m_due_amount_1=rs.getDouble(13);
					m_reg_no_1=rs.getString(14);
					m_res_value_1=rs.getDouble(15);
					m_vat_per_1=rs.getDouble(16);
					m_gain_loss_1=rs.getDouble(17);
					m_net_amount_1=rs.getDouble(18);
					m_net_rentals_1=rs.getDouble(19);
					m_odi_amount_1=rs.getDouble(20);
					m_odi_adjustment_1=rs.getDouble(21);
					m_odi_net_1=rs.getDouble(22);
					m_term_type_1=rs.getString(23);
					m_client_name_1=rs.getString(24);
					m_closure_irr_1=rs.getDouble(25);
				}
				
				
				
				
				//end by Dineth
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Asset Financing System</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("var durationID=0;");
				out.println("var timerID;");
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
				out.println("         get_term_details();");
				//Added timeout Dineth
				//out.println("		clearTimeout(timerID);");
				//out.println("   div_time_out.innerHTML=\"\";");
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
				out.println("         rec.innerHTML=http_request.responseText; ");
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
				//out.println("   document.Form1.btn_details.disabled=false;");
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
				out.println("     TERMINATION_NO_1.innerHTML            =data[0];");
				out.println("     document.Form1.TERMINATION_NO.value   =data[0];");
				//out.println("     get_Receipt_del();");
				out.println("   }else if(type=='Cli'){"); 
				out.println("     CLIENT_CODE_1.innerHTML            =data[0];"); 
				out.println("     CLIENT_NAME_1.innerHTML            =data[1];");
				out.println("     document.Form1.CLIENT_CODE.value   =data[0];");
				out.println("     document.Form1.CLIENT_NAME.value   =data[1];");
				//out.println("     get_Receipt();");
				out.println("   }else if(type=='Veh'){"); 
				//out.println("     document.Form1.VEHICLE_NO.value      =data[0];"); 
				out.println("     LEASE_NO_1.innerHTML                 =data[0];"); 
				out.println("     document.Form1.APPLICATION_NO.value  =data[1];"); 
				out.println("     CLIENT_CODE_1.innerHTML              =data[2];");
				out.println("     document.Form1.LEASE_NO.value        =data[0];");
				out.println("     document.Form1.CLIENT_CODE.VALUE     =data[2];");
				out.println("     check_due_inv();");
				//out.println("     get_Receipt();");
				out.println("   }else if(type=='Lea'){"); 
				out.println("     LEASE_NO_1.innerHTML                 =data[3];"); 
				out.println("     document.Form1.APPLICATION_NO.value  =data[0];"); 
				out.println("     CLIENT_CODE_1.innerHTML              =data[1];"); 
				out.println("     TRN_TYPE_1.innerHTML                 =data[4];"); 
				out.println("     ODI_1.innerHTML                      =data[12];"); 
				//out.println("     ODI_NET_1.innerHTML                  =data[12];"); 	
				out.println("     ODI_NET_1.innerHTML                  ="+m_odi_net_1+";"); 	
				out.println("     document.Form1.Unallo_Rec.value      =data[13];"); 
				out.println("     Unallo_Rec_1.innerHTML               =data[13];");
				out.println("     document.Form1.LEASE_NO.value        =data[3];");
				out.println("    document.Form1.CLIENT_CODE.value     =data[1];");
				out.println("    document.Form1.TRN_TYPE.value        =data[4];");
				out.println("    document.Form1.ODI.value             =data[12];");
				//out.println("    document.Form1.ODI_NET.value         =data[12];");//Commented by Dineth on 2008-10-31
				out.println("    document.Form1.ODI_NET.value         ="+m_odi_net_1+";");
				
				
				out.println("     document.Form1.LEASE_RATE.value      =data[14];"); 
				out.println("     document.Form1.DUE_AMOUNT.value      =data[15];"); 
				out.println("     document.Form1.DUE_NET.value         =data[16];"); 	
				out.println("     document.Form1.DUE_VAT.value         =data[17];"); 
				
				out.println("     LEASE_RATE_1.innerHTML     =data[14];"); 
				out.println("     DUE_AMOUNT_1.innerHTML      =data[15];"); 
				
				out.println("     get_due_rent_sum();");
				//out.println("     check_due_inv();");
				out.println("   }else if(type=='ODI_NET'){"); 
				/*out.println("     document.Form1.ODI_NET.value         =data[0];");//Commented by Dineth on 2008-10-31 	
				out.println("     ODI_NET_1.innerHTML        =data[0];");*/ 
				
				out.println("     document.Form1.ODI_NET.value         ="+m_odi_net_1+";"); 	
				out.println("     ODI_NET_1.innerHTML        ="+m_odi_net_1+";"); 
				
				out.println("   }else if(type=='DUE'){"); 
				out.println("     document.Form1.DUE_AMOUNT.value      =data[0];"); 
				out.println("     document.Form1.DUE_NET.value         =data[1];"); 	
				out.println("     document.Form1.DUE_VAT.value         =data[2];");
				out.println("     DUE_AMOUNT_1.innerHTML      =data[0];"); 
				out.println("     DUE_NET_1.innerHTML         =data[1];"); 	
				out.println("     DUE_VAT_1.innerHTML        =data[2];"); 	
				out.println("     check_lease_rate();");
				out.println("   }else if(type=='CAP'){");//       CAP_SETT   CAP_SETT_PER
				out.println("     document.Form1.AMOUNT_FINANCE.value  =data[0];"); 
				out.println("     document.Form1.NIBSM.value           =data[1];"); 
				out.println("     document.Form1.AMI.value             =data[2];"); 
				out.println("     document.Form1.CAP_OUT.value         =data[3];"); 
				out.println("     document.Form1.CAP_OUT_PER.value     =data[5];"); 
				//out.println("     document.Form1.VAT_PER.value         =data[6];");//Commented by Dineth on 2008-10-31 
				out.println("     document.Form1.VAT_PER.value         ="+m_vat_per_1+";");
				
				out.println("     AMOUNT_FINANCE_1.innerHTML  =data[0];"); 
				out.println("     NIBSM_1.innerHTML           =data[1];"); 
				out.println("    AMI_1.innerHTML              =data[2];"); 
				out.println("     CAP_OUT_1.innerHTML         =data[3];"); 
				out.println("     CAP_OUT_PER_1.innerHTML     =data[5];"); 
				//out.println("     VAT_PER_1.innerHTML         =data[6];");//Commented by Dineth on 2008-10-31 
				out.println("     VAT_PER_1.innerHTML         ="+m_vat_per_1+";"); 
				
				out.println("   }else if(type=='TCOUNT'){");
				out.println("     document.Form1.TER_COUNT.value       =data[0];");
				out.println("     TER_COUNT_1.innerHTML                =data[0];"); 
				out.println("     if(document.Form1.TER_COUNT.value>'0'){");
				out.println("     	 check_term_char();");
				out.println("     }else{ ");
				out.println("       document.Form1.TERM_AMOUNT.value       ='0'; ");
				out.println("       TERM_AMOUNT_1.innerHTML='0';");
				out.println("       get_term_vehicles();");
				//out.println("       check_due_inv();");
				out.println("     } ");
				out.println("   }else if(type=='TCHAR'){"); 
				//out.println("     document.Form1.TERM_AMOUNT.value      =data[0];");
				//out.println("    TERM_AMOUNT_1.innerHTML               =data[0];");
				out.println("     document.Form1.TERM_AMOUNT.value     = '"+m_charges_1+"';");//Modified By Kanishka On 05-01-2014
				out.println("    TERM_AMOUNT_1.innerHTML               = '"+nf.format(m_charges_1)+"';");//Modified By Kanishka On 05-01-2014
				out.println("     get_term_vehicles();");
				out.println("   }else if(type=='LEASERATE'){"); 
				out.println("     document.Form1.LEASE_RATE.value       =data[0];"); 
				out.println("    LEASE_RATE_1.innerHTML                =data[0];");
				out.println("     check_client();");
				out.println("   }else if(type=='TERATE'){"); 
				out.println("   }else if(type=='LERATE'){"); 
				out.println("     document.Form1.LEASE_RATE.value       =data[0];");
				out.println("    LEASE_RATE_1.innerHTML                =data[0];");
				out.println("     get_due_rent_sum();");
				out.println("   }else if(type=='DUER'){"); 
				out.println("     document.Form1.DUE_RENTALS.value       =data[0];"); 
				out.println("     document.Form1.DUE_RENTALS_NET.value   =data[1];"); 
				out.println("     document.Form1.DUE_RENTALS_VAT.value   =data[2];"); 
				
				out.println("     DUE_RENTALS_1.innerHTML               =data[0];"); 
				
				
				
				out.println("     document.Form1.TER_COUNT.value       =data[3];"); 
				//out.println("       document.Form1.TERM_AMOUNT.value   =data[4]; ");
				out.println("     TER_COUNT_1.innerHTML                =data[3];"); 
				//out.println("       TERM_AMOUNT_1.innerHTML            =data[4]; ");
				out.println("       document.Form1.TERM_AMOUNT.value   = '"+m_charges_1+"';");//Modified By Kanishka On 05-01-2014
				out.println("       TERM_AMOUNT_1.innerHTML            = '"+nf.format(m_charges_1)+"';");//Modified By Kanishka On 05-01-2014
				out.println("       get_term_vehicles();");
				
				//out.println("     check_term_count();");
				
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
				
				
				out.println("     TERMINATION_NO_1.innerHTML   =data[0];"); 
				out.println("     LEASE_NO_1.innerHTML         =data[1];"); 
				out.println("     APPLICATION_NO_1.innerHTML   =data[2];"); 
				out.println("     CLIENT_CODE_1.innerHTML      =data[3];"); 
				//out.println("     document.Form1.LEASE_RATE.value       =data[4];"); 
				//out.println("     document.Form1.LEASE_RATE.value       =data[5];"); 
				out.println("     REQ_BY_1.innerHTML           =data[6];"); 
				out.println("     TER_RATE_1.innerHTML         =data[7];"); 
				//out.println("     document.Form1.TERM_AMOUNT.value      =data[8];"); 
				out.println("     REMARK_1.innerHTML           =data[9];"); 
				out.println("     TERM_AMOUNT_1.innerHTML      =data[10];"); 
				out.println("     TER_COUNT_1.innerHTML        =data[11];");
				out.println("     DUE_AMOUNT_1.innerHTML      	=data[12];");
				out.println("     document.Form1.hid_cal_date.value='3';");
				out.println("     load_c_date(data[4]);");
				out.println("     document.Form1.hid_cal_date.value='2';");
				out.println("     load_c_date(data[5]);");
				//out.println("     befor_cal();	");
				out.println("   }"); 
				out.println(" }else{");
				out.println("  if(type=='Cli'){"); 
				out.println("   client_help();"); //Added by Chandana on 06/08/2007 for Ref no.759  
				out.println("  }");	
				out.println("}");
				//out.println("alert('*@*');");
				out.println("     befor_cal();	");
				out.println("}");
				//End Of Checking Values
				
				out.println("function assign_div(){");
				out.println(" rent.innerHTML=document.Form1.h_rent.value;"); 
				out.println("	term.innerHTML=document.Form1.h_term.value;"); 
				out.println(" rpv.innerHTML =document.Form1.h_rpv.value;"); 
				out.println(" ina.innerHTML =document.Form1.h_in.value;"); 
				out.println(" inr.innerHTML =document.Form1.h_inr.value;"); 
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
				
				out.println(" S21.innerHTML='Rentals Due up to Termination Date';"); 
				out.println("	S22.innerHTML=document.Form1.DUE_RENTALS_NET.value;"); 
				out.println(" S23.innerHTML=document.Form1.DUE_RENTALS_VAT.value;"); 
				out.println("	S24.innerHTML=document.Form1.DUE_RENTALS.value;");
				//out.println(" alert(document.Form1.h_term.value+'--'+document.Form1.rpv.value);");  
				
				
				out.println(" S31.innerHTML='Termination Calculation Amount';"); 
				
				//out.println("	S32.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.h_term.value))+parseFloat(unformat_noobject(document.Form1.h_rpv.value)));"); 
				//out.println(" S33.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.h_vat.value)));"); 
				//out.println("	S34.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.h_gtv.value)));");
				//modified by kanishka on 29-01-2014
				out.println(" 	num=(parseFloat(unformat_noobject(document.Form1.h_term.value))+parseFloat(unformat_noobject(document.Form1.h_rpv.value)));");
				out.println(" 	S32.innerHTML=format_noobject(Math.round(num*100)/100);");
				out.println(" 	S33.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.h_vat.value)));"); 
				out.println("	S34.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.h_gtv.value)));");
				
				//Modified by Dineth on 2008-10-24
				//out.println(" S41.innerHTML='Residual Amount';");
				out.println(" S41.innerHTML='Sales Price';");
				out.println("	S42.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.hid_sn_val.value)));"); 
				out.println(" S43.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));"); 
				out.println("	S44.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.hid_sg_val.value)));");
				
				out.println(" S51.innerHTML='Termination Charges';"); 
				out.println("	S52.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value)));"); 
				out.println(" S53.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.T_V.value)));"); 
				out.println("	S54.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.T_S.value)));");
				
				out.println(" S61.innerHTML='ODI';"); 
				out.println("	S62.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value)));"); 
				out.println(" S63.innerHTML=format_noobject(parseFloat(unformat_noobject('0.00')));"); 
				out.println("	S64.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value)));");
				
				out.println(" T1.innerHTML='Total Amount';"); 
				//out.println("	T2.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value))+parseFloat(unformat_noobject(document.Form1.h_rpv.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value)));"); 
				//out.println("   T3.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.T_V.value))+parseFloat(unformat_noobject(document.Form1.DUE_VAT.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_VAT.value))+parseFloat(unformat_noobject(document.Form1.h_vat.value))  + parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));"); 
				//out.println("	T4.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.DUE_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_RENTALS.value))    +parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value)));");
				
				//modified by kanishka on 29-01-2014
				out.println(" 	num=(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value))+parseFloat(unformat_noobject(document.Form1.h_rpv.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value)));");
				out.println(" 	T2.innerHTML=format_noobject(Math.round(num*100)/100);");
				//modified by kanishka on 29-01-2014
				out.println(" 	num=(parseFloat(unformat_noobject(document.Form1.T_V.value))+parseFloat(unformat_noobject(document.Form1.DUE_VAT.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_VAT.value))+parseFloat(unformat_noobject(document.Form1.h_vat.value))  + parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));");
				out.println(" 	T3.innerHTML=format_noobject(Math.round(num*100)/100);");
				//modified by kanishka on 29-01-2014
				out.println(" 	num=(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.DUE_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_RENTALS.value))    +parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value)));");
				out.println(" 	T4.innerHTML=format_noobject(Math.round(num*100)/100) + \"<input type=hidden name=hid_total_payable value=\"+num+\">\";");//Hidden Variable Added By KD On 27-01-2015
				
				
				//Added by Dineth on 2008-10-01
				out.println(" S65.innerHTML='Unallocated Receipt';"); 
				out.println("	S66.innerHTML='('+format_noobject(parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)))+')';"); 
				out.println(" S67.innerHTML='('+format_noobject(parseFloat(unformat_noobject('0.00')))+')';"); 
				out.println("	S68.innerHTML='('+format_noobject(parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)))+')';");
				
				//out.println(" alert('ODI_NET'+document.Form1.ODI_NET.value)");
				//out.println(" alert('TERM_AMOUNT'+document.Form1.TERM_AMOUNT.value)");
				//out.println(" alert('DUE_NET'+document.Form1.DUE_NET.value)");
				//out.println(" alert('DUE_RENTALS_NET'+document.Form1.DUE_RENTALS_NET.value)");
				//out.println(" alert('h_term'+document.Form1.h_term.value)");
				//out.println(" alert('hid_sn_val'+document.Form1.hid_sn_val.value)");
				//out.println(" alert('Unallo_Rec'+document.Form1.Unallo_Rec.value)");
				//out.println(" alert('Unallo_Rec'+document.Form1.h_rpv.value)");
				
				
				/*
				out.println(" T30.innerHTML='Net Total Amount';");
				out.println(" if(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value))+ parseFloat(unformat_noobject(document.Form1.h_rpv.value)) -parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value))<0){");
				out.println("	T31.innerHTML='('+format_noobject(  parseFloat(Math.round( parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value))+parseFloat(unformat_noobject(document.Form1.h_rpv.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value))   *100)/100)  )    +')';"); 
				out.println("   T32.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.T_V.value))+parseFloat(unformat_noobject(document.Form1.DUE_VAT.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_VAT.value))+parseFloat(unformat_noobject(document.Form1.h_vat.value))  + parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));"); 
				out.println("	T33.innerHTML='('+format_noobject(  parseFloat(Math.round( parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.DUE_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_RENTALS.value))    +parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value))*100)/100) )    +')';");
				out.println(" } ");
				out.println(" else{ ");
				out.println("	T31.innerHTML=format_noobject(  parseFloat(Math.round(  parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value)) +parseFloat(unformat_noobject(document.Form1.h_rpv.value))+ parseFloat(unformat_noobject(document.Form1.hid_sn_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value))*100)/100))  ;"); 
				out.println("   T32.innerHTML=format_noobject(  parseFloat(unformat_noobject(document.Form1.T_V.value))+parseFloat(unformat_noobject(document.Form1.DUE_VAT.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_VAT.value))+parseFloat(unformat_noobject(document.Form1.h_vat.value))  + parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));"); 
				out.println("	T33.innerHTML=format_noobject(  parseFloat(Math.round(  parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.DUE_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_RENTALS.value))    +parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value))    *100)/100));");
				out.println(" } ");
				*/
				
				out.println(" 	T30.innerHTML='Net Total Amount';");
				out.println(" if(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value))+ parseFloat(unformat_noobject(document.Form1.h_rpv.value)) -parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value))<0){");
				//modified by kanishka on 29-01-2014
				out.println(" 	num=(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value))+parseFloat(unformat_noobject(document.Form1.h_rpv.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)));");
				out.println(" 	T31.innerHTML='('+format_noobject(Math.round(num*100)/100)+')';");
				//modified by kanishka on 29-01-2014
				out.println(" 	num=(parseFloat(unformat_noobject(document.Form1.T_V.value))+parseFloat(unformat_noobject(document.Form1.DUE_VAT.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_VAT.value))+parseFloat(unformat_noobject(document.Form1.h_vat.value))  + parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));");
				out.println(" 	T32.innerHTML='('+format_noobject(Math.round(num*100)/100)+')';");
				//modified by kanishka on 29-01-2014
				out.println(" 	num=(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.DUE_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_RENTALS.value))    +parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)));");
				out.println(" 	T33.innerHTML='('+format_noobject(Math.round(num*100)/100)+')';");
				out.println(" } ");
				out.println(" else{ ");
				//modified by kanishka on 29-01-2014
				out.println(" 	num=(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value)) +parseFloat(unformat_noobject(document.Form1.h_rpv.value))+ parseFloat(unformat_noobject(document.Form1.hid_sn_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)));");
				out.println(" 	T31.innerHTML=format_noobject(Math.round(num*100)/100);");
				//modified by kanishka on 29-01-2014
				out.println(" 	num=(parseFloat(unformat_noobject(document.Form1.T_V.value))+parseFloat(unformat_noobject(document.Form1.DUE_VAT.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_VAT.value))+parseFloat(unformat_noobject(document.Form1.h_vat.value))  + parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));");
				out.println(" 	T32.innerHTML=format_noobject(Math.round(num*100)/100);");
				//modified by kanishka on 29-01-2014
				out.println(" 	num=(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.DUE_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_RENTALS.value))    +parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)));");
				out.println(" 	T33.innerHTML=format_noobject(Math.round(num*100)/100);");
				out.println(" } ");
				
				
				//out.println("	REBATE.innerHTML='Rebate %';");
				//out.println("	REBATE_AMT.innerHTML=format_noobject(100-(((parseFloat(unformat_noobject(document.Form1.h_term.value))-parseFloat(unformat_noobject(document.Form1.CAP_OUT.value)))/parseFloat(unformat_noobject(document.Form1.hid_int_amount.value)))*100));");
				
				//End by Dineth on 2008-10-01
				out.println(" R1.innerHTML='No of Future Rentals';"); 
				out.println("	R2.innerHTML=document.Form1.F_R.value;"); 
				out.println(" R3.innerHTML='No of Rentals Paid';"); 
				out.println("	R4.innerHTML=document.Form1.R_P.value;");
				
				out.println(" R5.innerHTML='No of Rentals Arrears';"); 
				out.println("	R6.innerHTML=document.Form1.R_A.value;");
				
				out.println("	R13.innerHTML='Total';");
				out.println("	R14.innerHTML=document.Form1.R_T.value;");
				
				out.println("	R7.innerHTML='Termination Gain / Loss';");
				out.println("	R8.innerHTML=document.Form1.h_term.value;");
				
				
				
				//out.println("	document.Form1.CLOSURE_IRR.value=document.Form1.C_IRR.value;"); 
				
				
				//out.println(" alert(unformat_noobject(document.Form1.h_term.value)+'<'+unformat_noobject(document.Form1.h_rpv.value));");
				
				//out.println(" if(parseFloat(unformat_noobject(document.Form1.h_term.value))<parseFloat(unformat_noobject(document.Form1.h_rpv.value))){");
				//out.println("   document.Form1.b_submit.disabled   = true;");
				//out.println("   document.Form1.b_submit_1.disabled = true;");
				//out.println("   alert('Please enter correct termination rate and continue.');");
				//out.println(" }else{");
				//out.println("   document.Form1.b_submit.disabled   = false;");
				//out.println("   document.Form1.b_submit_1.disabled = false;");
				//out.println(" }");
				
				//out.println("	check_leaserate();");
				//t.println("	get_CLOSURE_IRR();");
				
				out.println("}");	
				
				
				out.println("function inv_help(num){");
				out.println(" document.Form1.hid_opt_val.value=num;"); 
				out.println("	popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationCalculationReport?chksql=get_Invoice&client=\"+document.Form1.CLIENT_CODE.value+\"\", \"oBj\",\"left=130,top=200,width=750,height=400\");"); 
				out.println("}");		
				
				out.println("function cal_amount(opt,am1,am2,num) {");//
				out.println("   document.Form1.hid_win_opt.value=num;");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationCalculationReport?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'3');");
				
				out.println("}");	
				
				out.println("function cal_amount_del(opt,am1,am2,num) {");//
				out.println("   document.Form1.hid_win_opt.value=num;");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationCalculationReport?chksql=Add_Min_Amount&amount1=\"+am1+\"&amount2=\"+am2+\"&type=\"+opt;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'6');");
				
				out.println("}");	
				
				out.println("function get_term_vehicles() {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationCalculationReport_N?chksql=get_Vehicles&Lease_no=\"+document.Form1.LEASE_NO.value;");
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
				out.println("function check_due_inv(val) {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_due_invoice_sum&finance_no=\"+document.Form1.LEASE_NO.value+\"&client_code=\"+document.Form1.CLIENT_CODE.value;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','DUE');");
				out.println("}");
				
				out.println("function get_due_rent_sum(val) {");
				
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_due_rent_sum&finance_no=\"+document.Form1.LEASE_NO.value+\"&veh_no=\"+document.Form1.VEHICLE_NO.value+\"&tdate="+m_apply_date_1+"\";");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','DUER');");
				
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
				//out.println(" alert(document.Form1.hid_vcount.value);");
				
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
				//out.println(" alert('invoice '+document.Form1.INVOICE_NO.value);"); 
				//out.println(" alert('LEASE_NO '+document.Form1.LEASE_NO.value);");
				
				out.println(" if(document.Form1.INVOICE_NO.value!=\"\" && document.Form1.LEASE_NO.value!=\"\"){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_term_details&finance_no=\"+document.Form1.LEASE_NO.value+\"&veh_no=\"+document.Form1.VEHICLE_NO.value+\"&chas_no=\"+document.Form1.CHASSIS_NO.value+\"&invo_no=\"+document.Form1.INVOICE_NO.value;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'4','CAP');");
				out.println(" }else{");
				out.println("   alert('Please check performa invoice number or Finance no.')");
				out.println(" }");
				out.println("}");
				
				
				
				out.println("function check_lease_rate() {");
				out.println("  if(document.Form1.LEASE_NO.value!=''){");
				out.println("   build_veh_no();");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_lease_rate&finance_no=\"+document.Form1.LEASE_NO.value+\"&veh_no=\"+document.Form1.VEHICLE_NO.value+\"&client_code=\"+document.Form1.CLIENT_CODE.value;");
				//out.println("   window.open(m_url);");
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
				
				
				/*out.println("function set_timer_actions() {");
				out.println("   durationID=durationID+1;");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		div_time_out.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				out.println("}");*/
				
				
				
				
				out.println("function check_lease(val1,val2,val3) {");
				
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_XMLFile?chksql=get_Finance_no&finance_no=\"+val1+\"&client_code=\"+val2+\"&m_date=\"+val3+\"\";");
				//out.println("   window.open(m_url);");
				//out.println("alert(m_url);");
				out.println("   makeRequest(m_url,'4','Lea');");
				
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
				
				
				out.println("function befor_cal(){ "); 
				//out.println("		if(confirm(\"Are You Sure?\")){ "); 
				//out.println("   set_timer_actions();");
				out.println("  for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("  document.Form1.elements[i].disabled=false;");
				out.println("}");
				out.println("if(document.Form1.hid_option.value=='NEW'){");
				out.println("	if( document.Form1.LEASE_NO.value==\"\"){");
				out.println("   FNO.style.color='red';");
				out.println("		alert('Please enter finance no and continue!') "); 
				out.println("	}else if(document.Form1.TER_RATE.value==\"\" ){");
				out.println("   TMR.style.color='red';");
				out.println("		alert('Please enter discount rate and continue!') "); 
				out.println("	}else if(document.Form1.TER_DATE.value==\"\"){");
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
				//out.println("    m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationCalculationReport_N?chksql=get_Termi_Char&Term_val=\"+document.Form1.TERM_AMOUNT.value+\"&Vehicle_no=\"+document.Form1.VEHICLE_NO.value+\"&Lease_no=\"+document.Form1.LEASE_NO.value+\"&Disco_rate=\"+document.Form1.TER_RATE.value+\"&vat_rate=\"+document.Form1.VAT_PER.value+\"&lease_rate=\"+document.Form1.LEASE_RATE.value+\"&App_Date=\"+document.Form1.TER_DATE.value+\"&SaleVal=\"+document.Form1.SALE_VALUE.value+\"&Client=\"+document.Form1.CLIENT_CODE.value+\"&chas_no=\"+document.Form1.CHASSIS_NO.value+\"&sum_sal=\"+document.Form1.SUM_SALE_VALUE.value+\"&invo_no=\"+document.Form1.INVOICE_NO.value;");
				out.println("             m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule_new?chksql=get_Termi_Char&Term_val=\"+document.Form1.TERM_AMOUNT.value+\"&Vehicle_no=\"+document.Form1.VEHICLE_NO.value+\"&Lease_no=\"+document.Form1.LEASE_NO.value+\"&Disco_rate=\"+document.Form1.TER_RATE.value+\"&vat_rate=\"+document.Form1.VAT_PER.value+\"&lease_rate=\"+document.Form1.LEASE_RATE.value+\"&App_Date=\"+document.Form1.TER_DATE.value+\"&SaleVal=\"+document.Form1.SALE_VALUE.value+\"&Client=\"+document.Form1.CLIENT_CODE.value+\"&chas_no=\"+document.Form1.CHASSIS_NO.value+\"&sum_sal=\"+document.Form1.SUM_SALE_VALUE.value+\"&invo_no=\"+document.Form1.INVOICE_NO.value;");
				
				// out.println("   window.open(m_url);");
				out.println("    makeRequest(m_url,'2');");
				out.println("	 }else{");
				//out.println("	   alert('Sorry there is no selected vehicle to terminate.');"); 
				out.println("	 }"); 
				out.println("	}"); 
				out.println("}else{"); 
				//out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationCalculationReport_N?chksql=get_Termi_Det&Term_val=\"+document.Form1.TERM_AMOUNT.value+\"&Termination_no=\"+document.Form1.TERMINATION_NO.value+\"&Lease_no=\"+document.Form1.LEASE_NO.value+\"&vat_rate=\"+document.Form1.VAT_PER.value+\"&Disco_rate=\"+document.Form1.TER_RATE.value+\"&App_Date=\"+document.Form1.TER_DATE.value+\"&SaleVal=\"+document.Form1.SALE_VALUE.value+\"&Client=\"+document.Form1.CLIENT_CODE.value+\"&chas_no=\"+document.Form1.CHASSIS_NO.value;");
				out.println("            m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule_new?chksql=get_Termi_Det&Term_val=\"+document.Form1.TERM_AMOUNT.value+\"&Termination_no=\"+document.Form1.TERMINATION_NO.value+\"&Lease_no=\"+document.Form1.LEASE_NO.value+\"&vat_rate=\"+document.Form1.VAT_PER.value+\"&Disco_rate=\"+document.Form1.TER_RATE.value+\"&App_Date=\"+document.Form1.TER_DATE.value+\"&SaleVal=\"+document.Form1.SALE_VALUE.value+\"&Client=\"+document.Form1.CLIENT_CODE.value+\"&chas_no=\"+document.Form1.CHASSIS_NO.value;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'2');");
				out.println("}"); 
				out.println("  for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("  document.Form1.elements[i].disabled=true;");
				out.println("}");
				
				out.println("} "); 
				
				/*out.println("function befor_reset(){");
					out.println(" if(confirm(\"Are you sure you want to clear the screen?\")){  ");
					//out.println("  Form1.reset()   ");
					out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationSchedule?chksql=main_page'");
					out.println(" }  ");
					out.println("}");*/
				
				out.println("function befor_back(){");
				out.println("   close_window(); ");
				//out.println(" if(confirm(\"Are you sure?\")){  ");
				//out.println("  top.frames[1].location=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_FollowupAlert?chksql=main_page\";");
				//out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
				//out.println(" }  ");
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
				//out.println(" get_term_details();");
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
				out.println(" }");
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
				http://www.ofscl-netasset.lk:/myserver/servlet/OFSCL_AF_MISF_balance_receivable_report1?chksql=MAIN&client_name=TEST&finance_no=AP20070511-0624&client_code=0000000233&allocation_date=01-12-2007
					
					out.println("function load_Rec_data(num) {");
				out.println(" if(num!=\"\" ){");	
				//out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Documents?chksql=get_documents&deal_no=\"+num+\"&foll_no=\", \"oBj\",\"left=150,top=280,width=620,height=390\");"); 
				out.println("	 popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationCalculationReport_N?chksql=SHOW_BALANCES_INFO&url=&client_code=\"+num+\"\", \"oBj\",\"left=150,top=280,width=800,height=600\");"); 
				//0000000123
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println(" }else{");
				out.println("   alert('Please enter Client Code and continue!');");
				out.println(" }");
				out.println("}");
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"check_lease('"+m_finance_no+"','"+m_client_code_1+"','"+m_apply_date_1+"')\">"); 
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
				
				//Newly Added by Dineth
				
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td id=TMN><b>Termination No</b></td>");
				out.println("<td> ");
				out.println(m_termination_no);
				//out.println("<input type=button name=rec_help value=Help class=\"but_input\" onclick=\"term_help()\" disabled></td>");
				out.println("</td><input type=\"hidden\" name=\"TERMINATION_NO\" VALUE=\"\">");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td id=fod><b>Termination Type</b></td>");
				out.println("<td>"+m_termination_type+"</TD><input type=\"hidden\" name=\"TERMINATION_TYPE\" value=\"\">");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td id=TMD><b>Termination Date</b></td>");
				out.println("<td> ");
				out.println(m_apply_date_1);
				out.println("</td><input type=\"hidden\" name=\"TER_DATE\" value='"+m_apply_date_1+"'>");
				out.println("<td id=tod><b>Requested By</b></td>");
				out.println("<td>");
				out.println(m_requested_by_1);
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td id=CLC><b>Client Code</b></td>");
				out.println("<td ID=CLIENT_CODE_1> ");
				out.println(m_client_code_1);
				out.println("</td><input type=\"hidden\" name=\"CLIENT_CODE\" value=\"\">");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td id=tod><b>Client Name</b></td>");
				out.println("<td ID=CLIENT_NAME_1> ");
				out.println(m_client_name_1);
				out.println("</td><input type=\"hidden\" name=\"CLIENT_NAME\" value=\"\">");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");		
				
				
				out.println("<tr class=tr_input>");
				out.println("<td id=FNO><b>Finance No</b></td>");
				out.println("<td id=LEASE_NO_1>"+m_finance_no+"</td><input type=\"hidden\" name=\"LEASE_NO\" value='"+m_finance_no+"'><input name=\"APPLICATION_NO\" type=\"hidden\">");
				out.println("<td ><b>Termination Count</b></td>");
				out.println("<td ID=TER_COUNT_1> ");
				out.println(m_term_count_1);
				out.println("</td><input type=\"hidden\" name=\"TER_COUNT\">");
				//out.println("<td ></td>");
				//out.println("<td></td>");
				
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td id=TRNT><b>Transaction Type</b></td>");
				out.println("<td ID=TRN_TYPE_1>");
				
				
				
				out.println("</td><input name=\"TRN_TYPE\" type=\"HIDDEN\" VALUE=\"\">");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				// Commented by Dineth on 2008-11-13
				/*rs1=stmt1.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_CLOSURE_IRR('"+m_finance_no+"','"+m_apply_date_1+"')"+
															 " FROM DUAL");	
				double m_closure_irr_1=0;
				if(rs1.next()){
				m_closure_irr_1=rs1.getDouble(1);
				}*/
				
				
				
				out.println("<td ><b>Closure IRR</b></td>");
				//out.println("<td><input name=\"CLOSURE_IRR\" type=\"text\" maxlength=\"15\" class=\"txt_input\" Disable STYLE=\"{text-align:right;}>");
				//out.println("</td>");
				out.println("<td >"+nf.format(m_closure_irr_1)+"</td><input type=\"hidden\" name=\"CLOSURE_IRR\" value="+m_closure_irr_1+">");
				//out.println("<td></td><input name=\"CLOSURE_IRR\"  type=\"text\" maxlength=\"6\"  class=\"txt_input\" disabled onchange=\"\" STYLE=\"{text-align:right;}\">");
				
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				//out.println("<td id=TMR><b>Termination Rate</b></td>");//kanishka commented on 09-12-2013
				out.println("<td id=TMR><b>Rebate Rate</b></td>"); //Kanishka added
				out.println("<td ID=\"TER_RATE_1\">");
				out.println(nf2.format(m_rate_1));
				out.println("</td><input name=\"TER_RATE\" type=\"hidden\" value="+nf2.format(m_rate_1)+">");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td ><b>Finance Rate</b></td>");
				out.println("<td ID=LEASE_RATE_1> ");
				out.println("</td><input type=\"hidden\" name=\"LEASE_RATE\" value=\"\">");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td id=TMV><b>Termination Valid Date</b></td>");
				out.println("<td>");
				out.println(m_ter_validity_date_1);
				out.println("</td>");
				out.println("<td ><b>Due Amount</b></td>");
				out.println("<td ID=DUE_AMOUNT_1></td><input name=\"DUE_AMOUNT\" type=\"hidden\">");
				out.println("<input name=\"DUE_NET\" type=\"hidden\" ><input name=\"DUE_VAT\" type=\"hidden\" >");
				
				//out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td ><b>Remark</b></td>");
				out.println("<td ID=REMARK>");
				out.println(m_remarks_1);
				out.println("</td><input type=\"hidden\" name=\"REMARK\">");
				out.println("<td ><b>Termination Charge</b></td>");
				out.println("<td ID=TERM_AMOUNT_1>");
				out.println("</td><input type=\"hidden\" name=\"TERM_AMOUNT\">");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td ><b>VAT %</b></td>");
				out.println("<td ID=VAT_PER_1> ");//
				out.println("</td><input type=\"hidden\" name=\"VAT_PER\">");
				out.println("<td ><b>Normal Rentals Due up to Termination Date</b></td>");
				out.println("<td ID=DUE_RENTALS_1></td>");
				out.println("<input name=\"DUE_RENTALS_NET\" type=\"hidden\" ><input name=\"DUE_RENTALS_VAT\" type=\"hidden\" >");
				out.println("<input type=\"hidden\" name=\"DUE_RENTALS\">");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				//amount finance , NIBSM ,AMI(Amount) , Capital Repayment ,Total Capital already settled, % (Amount Setteled/Financed Amount) 
				out.println("<tr class=tr_input>");
				out.println("<td ><b>Amount Finance</b></td>");
				out.println("<td ID=AMOUNT_FINANCE_1>");
				out.println("</td><input type=\"hidden\" name=\"AMOUNT_FINANCE\">");
				out.println("<td ><b>NIBSM</b></td>");
				out.println("<td ID=NIBSM_1>");
				out.println("</td><input type=\"hidden\" name=\"NIBSM\">");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td><b>AMI</b></td>");
				out.println("<td ID=AMI_1>");
				out.println("</td><input type=\"hidden\" name=\"AMI\">");
				out.println("<td><b>Unallocated Receipt</b></td>");
				out.println("<td ID=\"Unallo_Rec_1\">");
				//out.println("<input name=\"Unallo_Rec\" type=\"text\" disabled class=\"txt_input\" STYLE=\"{text-align:right;}\">");
				out.println("</td><input name=\"Unallo_Rec\" type=\"hidden\">");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td><b>Total Capital Outstanding</b></td>");
				out.println("<td ID=CAP_OUT_1>");
				out.println("</td><input type=\"hidden\" name=\"CAP_OUT\">");
				out.println("<td><b>%</b></td>");
				out.println("<td ID=CAP_OUT_PER_1>");
				out.println("</td><input type=\"hidden\" name=\"CAP_OUT_PER\">");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td><b>ODI</b></td>");
				out.println("<td ID=ODI_1>");
				out.println("</td><input type=\"hidden\" name=\"ODI\">");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td><b>ODI Adjustment</b></td>");
				out.println("<td ID=ODI_ADJ_1> ");
				out.println(nf2.format(m_odi_adjustment_1));
				out.println("</td><input type=\"hidden\" name=\"ODI_ADJ\">");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td><b>ODI Net</b></td>");
				out.println("<td ID=ODI_NET_1> ");
				out.println("</td><input type=\"hidden\" name=\"ODI_NET\">");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//Modified by Dineth on 2008-10-21
				out.println("<td>&nbsp;</td>");
				
				
				
				out.println("<td>&nbsp;</td>");
				//End by Dineth on 2008-10-21
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td colspan=4><div id=veh><input type='hidden' name='hid_vcount' value='0'></div></td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				//out.println("<td colspan=4><input type=\"button\" name=\"btn_details\" class=\"but_input\" value=\"Details\" onclick=\"befor_cal()\" disabled=true></td>");
				out.println("<td colspan=4><div id=div_time_out></div></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				
				
				
				
				
				
				
				//End by Dineth
				
				
				
				
				
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
				/*
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
				*/
				//out.println("<td><input type=button name=edit_1 value=\"Delete\" class=mainbut onclick=load_screen_status(\"DELETE\"); onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				//out.println("<td width=10%>&nbsp;</td>");
				//out.println("<td><input type=button name=delete value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel value=\"Re-active\" class=mainbut onclick=befor_active(); onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				/*out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=b_submit_1 value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td><input class='mainbut' type='button' name='BUT_HELP_MAIN_1' value=\"Help\" onClick=\"help_update()\" disabled>  </td>"); 
			  out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset_1 value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Cancel\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back_1 value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=cal_1 value=\"Calculate\" class=mainbut onclick=befor_cal();></td>");
        
				out.println("</tr></table>");
				out.println("&nbsp;&nbsp;</td>");
				out.println("</tr>");
				*/
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
			
			else if(m_chksql.trim().equals("get_Vehicles")){
				
				String m_Lease_no     = req.getParameter("Lease_no");
				
				rs = stmt.executeQuery(" SELECT B.CHASSIS_NO,B.REG_NO,VAT_PERCENTAGE,VAT_ON_RENTAL,VAT_APP,B.INVOICE_NO, "+
					"        "+m_schema_name+".AF_CO_GET_MODEL_DESC(B.MODEL_CODE),B.APPLICATION_NO "+
					//" NVL((SELECT RESIDUAL_VALUE FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE INVOICE_NO=B.INVOICE_NO),0) "+
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
					rs1=stmt1.executeQuery(" SELECT COUNT(TERMINATION_NO) "+
						" FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES  "+
						" WHERE /* CHASSIS_NO='"+rs.getString(1)+"'"+
						" AND VEHICLE_NO='"+rs.getString(2)+"'  "+//commented by kd on 11-08-2016
						" AND */PRO_INVOICE_NO='"+rs.getString(6)+"'");
					
					int count=0;							
					if(rs1.next()){
						count=rs1.getInt(1);
					}

					rs2=stmt1.executeQuery(" SELECT SALE_VALUE FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES  "+
						" WHERE /*CHASSIS_NO='"+rs.getString(1)+"'"+//commented by kd on 11-08-2016
						" AND VEHICLE_NO='"+rs.getString(2)+"'"+
						" AND */PRO_INVOICE_NO='"+rs.getString(6)+"'");
					String m_sale_value="";						
					if(rs2.next()){
						m_sale_value=rs2.getString(1);
					}
					out.println("<tr class=tr_input >");
					out.println("<td width=10%>"+rs.getString(6)+"<input type=hidden name=\"INVOICE_NO_"+j+"\"  value=\""+rs.getString(6)+"\"></td>");
					out.println("<td width=10%>"+rs.getString(1)+"<input type=hidden name=\"CHASSIS_NO_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
					out.println("<td width=10%>"+rs.getString(2)+"<input type=hidden name=\"VEHICLE_NO_"+j+"\"  value=\""+rs.getString(2)+"\"></td>");
					out.println("<td width=10%>"+rs.getString(7)+"</td>");
					out.println("<td width=10%>"+rs.getString(3)+"<input type=hidden name=\"VAT_"+j+"\"  value=\""+rs.getString(3)+"\"><input type=hidden name=\"VATR_"+j+"\"  value=\""+rs.getString(4)+"\"></td>");
					if(rs.getString(1)==null){
						//out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\" ></td>");//remove disabled
						//out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
						out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\""+m_sale_value+"\" maxlength=\"25\" class=\"txt_input2\" disabled></td>");//Modified by Dineth on 2008-10-31
						//out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" ></td>");
						if(count>0){
							out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" checked disabled></td>");
						}
						else{
							out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" disabled></td>");
						}
					}else{
						//out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\" disabled ></td>");
						out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\""+m_sale_value+"\" maxlength=\"25\" class=\"txt_input2\" disabled ></td>");
						if(count>0){
							out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" checked disabled></td>");
						}
						else{
							out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" disabled></td>");
						}
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
				
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'), PERCENTAGE, RENTAL_AMOUNT,  "+
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
				String m_SaleVal      = req.getParameter("SaleVal");
				String m_Chassis_no   = req.getParameter("chas_no");
				String m_Invoice_no   = req.getParameter("invo_no");
				String m_sum_sal      = req.getParameter("sum_sal");
				String m_Term_val     = req.getParameter("Term_val");
				
				
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
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(INSTALLMENT_DATE,'DD-MM-YYYY'),PERCENTAGE,  "+
					"       SUM(RENTAL_AMOUNT), SUM(PV),SUM(TERMINATION_AMOUNT),   "+
					"       SUM(TERMINATION_PV),INSTALLMENT_NO,"+
					"       SUM(TERMINATION_PV-TERMINATION_AMOUNT) "+
					"FROM   "+m_schema_name+".AF_CR_TBD_TERMINATION "+
					"WHERE  ENT_USER='"+m_username+"' "+
					"GROUP  BY INSTALLMENT_NO, "+
					"       INSTALLMENT_DATE, PERCENTAGE "+
					"ORDER BY TO_DATE(TO_CHAR(INSTALLMENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY'),TO_NUMBER(INSTALLMENT_NO) ");
				
				
				out.println("<tr class=tr_input>");
				//out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td colspan=6 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
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
				double vat =0;	
				
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
					vat  = vat   + rs.getDouble(8);
					//vat  = 0;
					
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
						vat  = vat   + rs.getDouble(8);
						j=j+1;
					}
					
					
				}
				
				
				out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
				out.println("<td><input type=hidden name=hid_count value="+j+">");
				out.println("</td>");
				out.println("<td ></td>");
				out.println("<td align=right>"+nf.format(rent)+"<input type=hidden name=h_rent value="+nf.format(rent)+"></td>");
				out.println("<td align=right>"+nf.format(rpv) +"<input type=hidden name=h_rpv  value="+nf.format(rpv)+"></td>");
				out.println("<td align=right>"+nf.format(term)+"<input type=hidden name=h_term value="+nf.format(term)+"></td>");
				out.println("<td align=right>"+nf.format(tpv) +"<input type=hidden name=h_tpv  value="+nf.format(tpv)+"></td>");
				out.println("<td align=right>"+nf.format(gtv) +"<input type=hidden name=h_gtv  value="+nf.format(gtv)+"><input type=hidden name=h_vat  value="+nf.format(vat)+"></td>");
				out.println("</tr>");
				
				rs = stmt.executeQuery (" SELECT '"+m_sum_sal+"' , "+
					"        round('"+m_sum_sal+"'*('"+m_vat_rate+"'/100)), "+
					"        '"+m_sum_sal+"'+ round('"+m_sum_sal+"'*('"+m_vat_rate+"'/100)) "+
					" FROM DUAL");
				if(rs.next()){       
					out.println("<tr class=tr_input>");
					out.println("<td align=right colspan=6><input type=hidden name=hid_sn_val value="+rs.getString(1)+"><input type=hidden name=hid_sv_val value="+rs.getString(2)+"><input type=hidden name=hid_sg_val value="+rs.getString(3)+"></td>");
					out.println("</tr>");
				}else{
					out.println("<tr class=tr_input>");
					out.println("<td align=right colspan=6><input type=hidden name=hid_sn_val value=0><input type=hidden name=hid_sv_val value=0><input type=hidden name=hid_sg_val value=0></td>");
					out.println("</tr>");
					
				}
				
				/*
				rs = stmt.executeQuery (" SELECT "+m_schema_name+".AF_CR_GET_NO_FUTURE_RENTAL(APPLICATION_NO),"+
																"	       "+m_schema_name+".AF_CO_GET_RENTALS_PAID_TER(FINANCE_NO),"+
																"	       "+m_schema_name+".AF_CO_GET_NO_RENTALS_ARRIES(FINANCE_NO),"+
																"        round('"+m_Term_val+"'*('"+m_vat_rate+"'/100)),"+
																"        '"+m_Term_val+"'+round('"+m_Term_val+"'*('"+m_vat_rate+"'/100)),'' "+
																//"        "+m_schema_name+".AF_CO_GET_CLOSURE_IRR(FINANCE_NO,'"+m_App_date+"') "+
																"	FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																"	WHERE  FINANCE_NO= '"+m_Lease_no+"'");
																*/
				rs = stmt.executeQuery (" SELECT "+m_schema_name+".AF_CR_GET_NO_FUTURE_RENTAL(APPLICATION_NO),"+
					"	       "+m_schema_name+".AF_CO_GET_RENTALS_PAID_TER(FINANCE_NO),"+
					"	       "+m_schema_name+".AF_CO_GET_NO_RENTALS_ARRIES(FINANCE_NO),"+
					"        "+m_Term_val+"*("+m_vat_rate+"/100),"+
					"        "+m_Term_val+" + "+m_Term_val+"*("+m_vat_rate+"/100),'' "+
					"	FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					"	WHERE  FINANCE_NO= '"+m_Lease_no+"'");
				
				if(rs.next()){
					out.println("<tr class=tr_input><input type=hidden name=F_R value=\""+nf1.format(rs.getDouble(1))+"\">");
					out.println("<input type=hidden name=R_P value=\""+nf1.format(rs.getDouble(2))+"\"><input type=hidden name=R_A value=\""+nf1.format(rs.getDouble(3))+"\"><input type=hidden name=R_T value=\""+nf1.format(rs.getDouble(2)+rs.getDouble(3)+rs.getDouble(1))+"\">");
					out.println("<input type=hidden name=T_V value=\""+nf.format(Math.round(rs.getDouble(4)))+"\"><input type=hidden name=T_S value=\""+nf.format(rs.getDouble(5))+"\"><input type=hidden name=C_IRR value=\""+nf1.format(rs.getDouble(6))+"\">");
				}else{
					out.println("<tr class=tr_input><input type=hidden name=F_R value=\"\">");
					out.println("<input type=hidden name=R_P value=\"\"><input type=hidden name=R_A value=\"\">");
					out.println("<input type=hidden name=T_V value=\"\"><input type=hidden name=T_S value=\"\"><input type=hidden name=C_IRR value=\"\">");
					
				}
				//out.println("<td align=right colspan=6><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
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
					"         AND UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')  ";
				
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
