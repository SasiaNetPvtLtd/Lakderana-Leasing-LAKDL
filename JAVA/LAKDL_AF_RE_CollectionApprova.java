//Option Id is 4.10  
//This File was created by SVA on 07-08-2006 
//Collection Receipt Approval
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;

import oracle.jdbc.driver.*;

public class LAKDL_AF_RE_CollectionApprova extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs;
	public String m_chksql;
	ServletOutputStream out = null;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
      String m_username 						= "AA";//m_sn_methods.username;
			out = res.getOutputStream();
			
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			else if (m_chksql.trim().equals("main_page")) {
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			
				
				out.println("</head>");
				out.println("<Script>");
				out.println("var m_bsubmit = '0';");
				out.println("var arr_assign= new Array();");
				out.println("var m_send_val= '';");
				
				//Check Values Using AJAX
				out.println("function makeRequest(url,opt,opt1) {");
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
        
				out.println("  http_request.onreadystatechange = function() { alertGetContents(http_request,opt); };");
				out.println("  http_request.open('GET',url, true);");
     //   out.println("  window.open(url);");
				out.println("  http_request.send(null);");
				out.println("}");
				
        out.println("function alertGetContents(http_request,opt) {");
				//out.println(" alert('test--'+opt);");
        out.println(" if (http_request.readyState == 4) {");
        out.println("    if (http_request.status == 200) {");
        out.println("      if(opt==\"LOAD_DATA\"){");
				//out.println("         alert(http_request.responseText);");
				out.println("         load_data.innerHTML=http_request.responseText; ");
				out.println("      }else {");
				out.println("        var xmlbody=http_request.responseXML.documentElement;");
			  out.println("        var vsize=0;");
        out.println("        data_vec = new Array();");

			  out.println("           for(var i=0;i<xmlbody.childNodes.length;i++){");
				out.println("      			 for(var j=0;j<xmlbody.childNodes[i].childNodes.length;j++){");
				out.println("         	  data_vec[vsize]=xmlbody.childNodes[i].childNodes[j].text;");
				out.println("         	  vsize++;");
					//alert(data_vec[vsize]);
				out.println("            }");
			  out.println("           }");
				out.println("          if(data_vec.length>0){");
				
				out.println("          if(opt==\"PRICENO\"){");
				out.println("             assing_pval(data_vec);");
				out.println("          }else if(opt==\"INQ_NO\"){");
				out.println("            document.Form1.INQ_NO.value=data_vec[0];");
				out.println("          }");			
				out.println("          } else {");
        out.println("          if(opt==\"INQ_NO\"){");
				out.println("            alert('No data found');");
				out.println("            document.Form1.INQ_NO.value='';");
				out.println("          }else{");			
				out.println("            main_id.innerHTML='';");
        out.println("          }");
        out.println("          }");
    
				
				//out.println("          }");
				
				out.println("      }");
				out.println("    } else {");
        out.println("          if(opt==\"INQ_NO\"){");
				out.println("            alert('No data found');");
				out.println("            document.Form1.INQ_NO.value='';");
				out.println("          }else if(opt==\"VENDOR\"){");
				out.println("            alert('No data found');");
				out.println("            document.Form1.VENDOR.value='';");
				out.println("          }else if(opt==\"SUB_MODEL\"){");
				out.println("            alert('No data found');");
				out.println("            document.Form1.SUB_MODEL.value='';");
				out.println("          }else{");			
				out.println("            main_id.innerHTML='';");
        out.println("          }");
        out.println("    }");
        out.println(" }");
        out.println("}");
				
				//End Of Checking Values
				
				out.println("function asign_div(){");
				out.println("alert(document.Form1.sun_fact.value);");
				out.println("format_num(document.Form1.sun_p_nt.value,0)");
				out.println("format_num(document.Form1.sun_fact.value,4)");
				out.println("m_fact.innerHTML =document.Form1.sun_fact.value");
				out.println("document.Form1.hid_count.value=document.Form1.hid_cou.value;");
				out.println("}");

				//Main Button Action
				//Submit
				out.println("function befor_submit(){");
				out.println(" m_bsubmit='0';");
				//out.println(" if(document.Form1.TRANSACTION_TYPE.value==''){ ");
				//out.println("   trntype.style.color=\"red\";");
				//out.println("   m_bsubmit='1';");
				//out.println(" }");
				
				out.println(" if(m_bsubmit=='0'){");
				//out.println("   for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
				//out.println("     document.Form1.elements[\"PRACENT\"+i].value = unformat_noobject(document.Form1.elements[\"PRACENT\"+i].value) ");// unformat_noobject(
				//out.println("     document.Form1.elements[\"FACTOR\"+i].disabled  = false; ");//");//unformat_noobject(
				//out.println("   }");
				
				out.println("   document.Form1.action=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Save\";");
				out.println("   document.Form1.submit();");
				out.println(" }");
				out.println("}");
				//end of Submit Function
				
				
				out.println("function befor_new(){");
				//out.println(" if(confirm(\"Are You Sure?\")){  ");
				out.println(" if(confirm(\"Are you sure you want to enter New record?\")){  ");
				//out.println("  Form1.reset() ;  ");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println("  befor_load(\"P\",\"LOAD_DATA\");");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_back(){");
				//*************************************************************
				//modified by : delanjali
				//date				: 2007-05-09
				//reason			: error is given when closing of the screen
				
				out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
				//*************************************************************

				//modified by : delanjali
				//date				: 2007-05-09
				//reason			: error is given when closing of the screen
				//out.println(" if(confirm(\"Are You Sure?\")){  ");
				//*************************************************************
				
				
				
				//out.println(" if(confirm(\"Are you sure you want to enter New record?\")){  ");
				//out.println("  Form1.reset() ;  ");
				//out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
				//out.println("  document.Form1.OPTION_DESC.value=\"B\";");
				
				//*************************************************************
				//modified by : delanjali
				//date				: 2007-05-09
				//reason			: error is given when closing of the screen
				//out.println(" if(confirm(\"Are You Sure?\")){  ");
				//*************************************************************
	
				//out.println("  document.Form1.PRICE_NO.disabled=true;");
				
				//out.println("  document.Form1.inqu_help.disabled=true;");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_modify(){");
				//out.println(" if(confirm(\"Are You Sure?\")){  ");
				out.println(" if(confirm(\"Are you sure you want to Delete a record?\")){  ");
				//out.println("  Form1.reset();   ");
				out.println("  document.Form1.OPTION_NAME.value=\"DEL\";");
				out.println("  document.Form1.OPTION_DESC.value=\"Delete\";");
				//out.println("  alert(document.Form1.OPTION_DESC.value);");
				out.println("  befor_load(\"Y\",\"LOAD_DATA\");");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_active(){");
				
				out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  Form1.reset() ;  ");
				out.println("  document.Form1.OPTION_NAME.value=\"ACT\";");
				out.println("  document.Form1.OPTION_DESC.value=\"Re-activate\";");
				//out.println("  alert(document.Form1.OPTION_DESC.value);");
				
				out.println("  document.Form1.PRICE_NO.disabled=false;");
				
				
				out.println("  document.Form1.inqu_help.disabled=false;");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_deactive(){");
				out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  Form1.reset();   ");
				out.println("  document.Form1.OPTION_NAME.value=\"INA\";");
				out.println("  document.Form1.OPTION_DESC.value=\"De-activate\";");
				out.println("  document.Form1.PRICE_NO.disabled=false;");
				out.println("  document.Form1.inqu_help.disabled=fales;");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_reset(){");
				out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  Form1.reset()   ");
			  out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_CollectionApprova?chksql=main_page'");
			  out.println(" }  ");
				out.println("}");
				
				out.println("function befor_load(m_stat,opt) {");
        out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_CollectionApprova?chksql=get_load_data&status=\"+m_stat+\"\";");
        out.println("   makeRequest(m_url,\"LOAD_DATA\",\"LOAD_DATA\");");
        out.println("}");
        
				out.println("function befor_main(m_stat,opt) {");
        //out.println("   alert('func ');");
        out.println(" if(document.Form1.MAINTENANCE_APP.options[document.Form1.MAINTENANCE_APP.selectedIndex].value!=\"N\"){");
        //out.println("   alert('if ');");
        out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?chksql=loadMaintenance&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+\"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&ami=\"+document.Form1.AMI.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&last_rent=&nitbasemar=\"+document.Form1.INTEREST_BASE_MARGIN.value+\"&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+\"&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+document.Form1.NIBSM.value+\"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"\";");
        out.println("   makeRequest(m_url,\"Main\",'NO');");
        out.println(" }else{");
				out.println("   main_id.innerHTML='';;");
        out.println(" }");
        out.println("}");
				
				
				out.println("function befor_subtype(m_stat,opt) {");
        //out.println("   alert('func ');");
        //out.println(" if(document.Form1.MAINTENANCE_APP.options[document.Form1.MAINTENANCE_APP.selectedIndex].value!=\"N\"){");
        //out.println("   alert('if ');");
        out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?chksql=chkSubType&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+\"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.TRANSACTION_TYPE.value+\"\";");
        out.println("   makeRequest(m_url,\"SubType\",'NO');");
        //out.println(" }else{");
				//out.println("   main_id.innerHTML='';;");
        //out.println(" }");
        
        out.println("}");
				
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
				
				//end of Main Button Action
				//onload Action
				
				out.println("function befor_onload(){");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println("  load_roll_value(\"New\");"); 
				out.println("  befor_load(\"P\",\"LOAD_DATA\");");
				out.println("}");
				//end of onload

				out.println("function load_roll_value(m_val){"); 
			  out.println("help_box.innerHTML=\"Collection - Temporary Receipts Approval - \"+m_val;"); 
			  out.println("}");
				
				out.println("function format_text(obj){"); 
			  out.println("  obj.value=format_noobject(obj.value);"); 
			  out.println("}");
				
				out.println("function format_num(obj,i){");
				out.println("  if(isNaN(obj)){");
				out.println("   alert('Please Enter Number!');");
				out.println("   obj='0';");
				out.println("  }else{");
			  out.println("   obj=unformat_noobject(obj)");
				out.println("   obj=format_noobject0(obj,i);"); 
			  out.println("  }");
				out.println("}");
				
				out.println("function checkfor_num(obj){");
				out.println("  if(isNaN(obj)){");
				out.println("   alert('Please Enter Number!');");
				out.println("   obj.value='0';");
				out.println("  }");
				out.println("}");
				
				out.println("function cha_val(count){"); 
				out.println(" if(document.Form1.TRANSACTION_SUB.value==\"STEP-UP\" || document.Form1.TRANSACTION_SUB.value==\"STEP-DOWN\"){ ");
			  out.println("  if(confirm(\"Do you want apply this change to all the below installments?\")){"); 
			  out.println("    for(i=parseFloat(count)+1;i<parseFloat(document.Form1.hid_count.value);i++){"); 
			  out.println("     document.Form1.elements[\"PERCENTAGE\"+i].value = unformat_noobject(document.Form1.elements[\"PERCENTAGE\"+count].value) ");// unformat_noobject(
				out.println("    }");
				out.println("  }");
				out.println(" }");
				out.println("}");
				
				
				out.println("function check_status(num) {");
				//out.println("alert(document.Form1.elements['Text_standard'+num].checked);");
				out.println("if(document.Form1.elements['Text_standard'+num].checked){");
				out.println(" document.Form1.elements['Text_standard'+num].value=\"YES\";");
				out.println("}else{");
				out.println(" document.Form1.elements['Text_standard'+num].value=\"NO\";");
				out.println("}");
				out.println("}");

				out.println("</Script>");
				out.println("<body onload=\"befor_onload();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"Hid_scr_name\" value=\"AF_RE_TEMP_REC\">");
				out.println("<input type=hidden name=\"OPTION_NAME\" value=\"NEW\">");
				out.println("<input type=hidden name=\"OPTION_DESC\"   value=\"New\">");
				out.println("<input type=hidden name=\"CURRENCY_CODE\" value=\"SLR\">");
				out.println("<input type=hidden name=\"EXCHANGE_RATE\" value=\"0\">");
				out.println("<input type=hidden name=\"CACULATED\"     value=\"NO\">");
				out.println("<input type=hidden name=\"CAL_COUNT\"     value=\"0\">");
				out.println("<input type=hidden name=\"OUT_COUNT\"     value=\"0\">");
				out.println("<input type=hidden name=\"CASH_OUTFLOW\"  value=\"\">");
				
				
				out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
				out.println("<tr>");
				
				out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
				out.println("<td class=\"border_wht\" valign=\"top\"> ");
				out.println("<table class=table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
				out.println("<tr> ");
				out.println("<td height=\"30\" class=\"pdn_mainHD\" class>Asset Financing System</td>");
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
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
				out.println("<tr>");
		
				out.println("<td style=\"width: 6px\">");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=new      value=\"New\"       class=mainbut onclick=befor_new();      onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");//document.Form1.OPTION_DESC.value
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=edit     value=\"Delete\"      class=mainbut onclick=befor_modify();   onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=delete   value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel   value=\"Re-active\" class=mainbut onclick=befor_active();   onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=b_submit value=\"Save\"      class=mainbut onclick=befor_submit();   onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_value(document.Form1.OPTION_DESC.value);' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\" disabled></td>");  
				out.println("<td>&nbsp;</td>");
				//date :2007-05-09
				//delanjali		
				out.println("<td><input type=button name=back     value=\"Close\"     class=mainbut onclick=close_window();     onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");

				//				out.println("<td><input type=button name=back     value=\"Close\"     class=mainbut onclick=befor_back();     onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset_   value=\"Cancel\"     class=mainbut onclick=befor_reset();    onMouseOver='load_roll_value(\"Cancel\");'     onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td id=cal_but><input type=button name=cal     value=\"Calculate\" class=mainbut onclick=befor_cal(\"NO\",\"YES\");     onMouseOver='load_roll_value(\"Calculate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        //out.println("<td>&nbsp;</td>");
				//out.println("<td ><input type=button name=recal     value=\"Re-Cal\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");     onMouseOver='load_roll_value(\"Re-Calculate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        //out.println("<td>&nbsp;</td>");
				//out.println("<td ><input type=button name=end_b     value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b);  onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
				out.println("</tr></table>");
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
				
				/*
				out.println("<tr class=tr_input>");
				out.println("<td width=\"15%\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"120\" ></td>");
				out.println("<td width=\"35%\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"180\" ></td>");
				out.println("<td width=\"15%\">");
				out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" ></td>");
				out.println("<td>");
				out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" ></td>");
				out.println("</tr>");
				*/
				out.println("</table>");
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
				out.println("<tr class=tr_input>");
				out.println("<td valign=top  width=40% id=load_data>");
				//out.println("<td class=\"txt-bodyGreen\" height=\"18\">Personal Details</td>");
				/*out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//sub table start(data entry)
				
			    out.println("<tr class=pdn_txtpos2>");
          //out.println("<td  width='20%' >Rate per Month(RM)</td>");
          //out.println("<td  width='30%' >(RM+1)=X</td>");
          out.println("<td  width='15%' >Receipt No</td>");
					out.println("<td  width='15%' >Financial No</td>");
					out.println("<td  width='20%' >Client Code</td>");
					out.println("<td  width='10%' >Trn Date</td>");
					out.println("<td  width='15%' align=right>Amount</td>");
					out.println("<td  width='10%' >Sett. Mode</td>");
					out.println("<td  width='10%' >Bank</td>");
					out.println("<td  width='5%' ></td></tr>");
					
					
          
          double sum_rate =0;
          double sum_rent =0;
					double sum_p_re =0;
					double sum_g_re =0;
					double sum_o_re =0;
					double sum_op_re=0;
					int m_start=0;
			
					int j=0;
         		rs = stmt.executeQuery (" SELECT A.TEMP_REC_NO, A.FINANCE_NO, A.CLIENT_CODE, "+
							                        "        TO_CHAR(A.TRN_DATE,'DD-MM-YYYY'), A.AMOUNT,"+
																			"	       A.SETTELMENT_MODE, A.BANK_CODE, A.BRANCH_CODE, "+
																			"        A.ACCOUNT_NO,A.CURR_CODE,TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),   "+
																			"	       A.TRN_AMOUNT_CURR, A.STATUS,A.EXCHANGE_RATE, "+
																			"	       A.COLLECTION_OFFICER "+
																			"	FROM   "+m_schema_name+".AR_RE_PRO_TEMP_RECEIPT A "+
																			"	WHERE  STATUS='P'");
																			
              while(rs.next()){
                  out.println("<tr class=tr_input>");
									out.println("<td >"+rs.getString(1)+"<input type=hidden name=\"rec_no"+j+"\"       value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td >"+rs.getString(2)+"<input type=hidden name=\"fin_no"+j+"\"       value="+rs.getString(2)+" maxlength=\"10\" class=\"txt_input1\" disabled></td>");
                  out.println("<td >"+rs.getString(3)+"<input type=hidden name=\"client_code"+j+"\"  value=\""+rs.getString(3)+"\" maxlength=\"6\" class=\"txt_input1\" disabled onchange=format_num(document.Form1.CASHOUT"+j+".value,'4');cha_val(\""+j+"\")></td>");
                  out.println("<td >"+rs.getString(4)+"<input type=hidden name=\"trn_date"+j+"\"     value="+rs.getString(4)+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"amount"+j+"\" value="+rs.getString(5)+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td >"+rs.getString(6)+"<input type=hidden name=\"sett_mode"+j+"\"    value="+rs.getString(6)+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                  out.println("<td >"+rs.getString(7)+"<input type=hidden name=\"bank"+j+"\"         value="+rs.getString(7)+" maxlength=\"25\" class=\"txt_input2\" onchange=format_num(document.Form1.CASHOUT"+j+".value,'0')></td>");
                  out.println("<td ><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\" ></td>");
                  //out.println("<td ><input name=\"AMI"+j+"\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  out.println("</tr>");
                  j=j+1;
              }
   
				/*out.println("<tr >"); 
			out.println("<td width='20%' >TEMP_REC_NO *</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_TEMP_REC_NO' maxlength='15' size='15' onblur=\"makeRequest(document.Form1.TXT_TEMP_REC_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>FINANCE_NO *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='15'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE_NO' value=\"Help\" onClick=\"help_button_1()\"></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td>CLIENT_CODE</td>"); 
			out.println("<td><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_CODE' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
			out.println("<td>TRN_DATE *</td>"); 
			out.println("<td  ><input class='txt_input' type='text' name='TXT_TRN_DATE' maxlength='7' size='7'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td>AMOUNT *</td>"); 
			out.println("<td  ><input class='txt_input' type='text' name='TXT_AMOUNT' maxlength='22' size='22'></td>"); 
			out.println("<td>SETTELMENT_MODE *</td>"); 
			out.println("<td  ><input class='txt_input' type='text' name='TXT_SETTELMENT_MODE' maxlength='10' size='10'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td>BANK_CODE</td>"); 
			out.println("<td><input class='txt_input' type='text' name='TXT_BANK_CODE' maxlength='5' size='5'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_BANK_CODE' value=\"Help\" onClick=\"help_button_3()\"></td>"); 
			out.println("<td>BRANCH_CODE</td>"); 
			out.println("<td><input class='txt_input' type='text' name='TXT_BRANCH_CODE' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_BRANCH_CODE' value=\"Help\" onClick=\"help_button_4()\"></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td>ACCOUNT_NO *</td>"); 
			out.println("<td  ><input class='txt_input' type='text' name='TXT_ACCOUNT_NO' maxlength='20' size='20'></td>"); 
			out.println("<td>CURR_CODE</td>"); 
			out.println("<td><input class='txt_input' type='text' name='TXT_CURR_CODE' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_CURR_CODE' value=\"Help\" onClick=\"help_button_5()\"></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td>EXCHANGE_RATE *</td>"); 
			out.println("<td  ><input class='txt_input' type='text' name='TXT_EXCHANGE_RATE' maxlength='22' size='22'></td>"); 
			out.println("<td>TRN_AMOUNT_CURR *</td>"); 
			out.println("<td  ><input class='txt_input' type='text' name='TXT_TRN_AMOUNT_CURR' maxlength='22' size='22'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td>COLLECTION_OFFICER</td>"); 
			out.println("<td><input class='txt_input' type='text' name='TXT_COLLECTION_OFFICER' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_COLLECTION_OFFICER' value=\"Help\" onClick=\"help_button_6()\"></td>"); 
			out.println("<td ></td>"); 
			out.println("<td ></td>"); 
			out.println("</tr>"); 
				
				out.println("</table>");//sub table close(data entry)
				*/
				out.println("</td>");
				
				out.println("<td valign=top><div id=price_cal></div>");
				out.println("<INPUT TYPE=HIDDEN NAME=hid_count VALUE=0></td>");
				out.println("</tr>");
				
				
				out.println("</table>");//main table
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td class=\"line\" height=\"1\">");
				out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td class=\"pdn_txtpos\" style=\"height: 10px\">");
				out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
				out.println("<tr class=tr_input>");
				     
				out.println("<td style=\"width: 6px\">");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=new1      value=\"New\"       class=mainbut onclick=befor_new();      onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");//document.Form1.OPTION_DESC.value
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=edit1     value=\"Delete\"      class=mainbut onclick=befor_modify();   onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=delete   value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel   value=\"Re-active\" class=mainbut onclick=befor_active();   onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=b_submit1 value=\"Save\"      class=mainbut onclick=befor_submit();   onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_value(document.Form1.OPTION_DESC.value);' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\" disabled ></td>");  
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back1     value=\"Close\"     class=mainbut onclick=befor_back();     onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset_1   value=\"Cancel\"     class=mainbut onclick=befor_reset();    onMouseOver='load_roll_value(\"Cancel\");'     onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td ><input type=button name=cal     value=\"Calculate\" class=mainbut onclick=befor_cal(\"NO\");     onMouseOver='load_roll_value(\"Calculate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        
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
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("</html>");
			
			
			}
			
			else if(m_chksql.trim().equals("get_load_data")){
			
		 	  String status = req.getParameter("status");
				int j=0;
				  		rs = stmt.executeQuery (" SELECT A.TEMP_REC_NO, A.FINANCE_NO, A.CLIENT_CODE, "+
							                        "        TO_CHAR(A.TRN_DATE,'DD-MM-YYYY'), A.AMOUNT,"+
																			"	       A.SETTELMENT_MODE, A.BANK_CODE, A.BRANCH_CODE, "+
																			"        A.ACCOUNT_NO,A.CURR_CODE,TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),   "+
																			"	       A.TRN_AMOUNT_CURR, A.STATUS,A.EXCHANGE_RATE, "+
																			"	       A.COLLECTION_OFFICER, "+
																			"        NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.BRANCH_CODE),'-') "+
																			"	FROM   "+m_schema_name+".AF_RE_PRO_TEMP_RECEIPT A "+
																			"	WHERE  STATUS='"+status+"'");
							boolean more=rs.next();
							if(more){

                out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//sub table start(data entry)
						    out.println("<tr class=pdn_txtpos2>");
			          out.println("<td  width='15%' >Receipt No</td>");
								out.println("<td  width='15%' >Financial No</td>");
								out.println("<td  width='13%' >Client Code</td>");
								out.println("<td  width='8%' >Trn Date</td>");
								out.println("<td  width='15%' align=right>Amount</td>");
								out.println("<td  width='8%' align=center >Sett. Mode</td>");
								out.println("<td  width='8%' >Bank</td>");
								out.println("<td  width='14%' >Branch Name</td>"); //Added by Chandana on 22/05/2007 for Ref No.45
								out.println("<td  width='5%' ></td></tr>");
							  
                while(more){
                  out.println("<tr class=tr_input>");
									out.println("<td >"+rs.getString(1)+"<input type=hidden name=\"rec_no_"+j+"\"       value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td >"+rs.getString(2)+"<input type=hidden name=\"fin_no_"+j+"\"       value=\""+rs.getString(2)+"\" ></td>");
                  out.println("<td >"+rs.getString(3)+"<input type=hidden name=\"client_code_"+j+"\"  value=\""+rs.getString(3)+"\" ></td>");
                  out.println("<td >"+rs.getString(4)+"<input type=hidden name=\"trn_date_"+j+"\"     value=\""+rs.getString(4)+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"amount_"+j+"\" value=\""+rs.getString(5)+"\" ></td>");
                  out.println("<td align=center >"+rs.getString(6)+"<input type=hidden name=\"sett_mode_"+j+"\"    value=\""+rs.getString(6)+"\" ></td>");
                  out.println("<td >"+rs.getString(7)+"<input type=hidden name=\"bank_"+j+"\"         value=\""+rs.getString(7)+"\" ></td>");
									out.println("<td >"+rs.getString(16)+"<input type=hidden name=\"bank_name"+j+"\"         value=\""+rs.getString(16)+"\" ></td>"); //Added by Chandana on 22/05/2007 for Ref No.45
                  out.println("<td ><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\" ></td>");
                  //out.println("<td ><input name=\"AMI"+j+"\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  out.println("</tr>");
                  j=j+1;
									more = rs.next();
								}	
								out.println("<INPUT TYPE=HIDDEN NAME=HID_COUNT VALUE="+j+"></table>");
				
              }else{
				        out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//sub table start(data entry)
						    out.println("<tr class=pdn_txtpos2>");
								out.println("<td colspan=8 align=center>No Data Found</td>");
								out.println("</tr>");
                out.println("</table>");
				 			
							}
   
				
				
				
			}
			else if(m_chksql.trim().equals("chkClient")){
			
			  String clientName = req.getParameter("clientName");
				String addrees1   = req.getParameter("addrees1");
				String city_code 	= req.getParameter("city_code");
				String tel_no 		= req.getParameter("tel_no");
				String email 			= req.getParameter("email");
				String nic_no 		= req.getParameter("nic_no");
				String bc_no		  = req.getParameter("bc_no");
				
			  rs = stmt.executeQuery( " SELECT FULL_NAME, ADDRESS1, CITY_CODE,TEL_NO,EMAIL,NIC_NO "+
																" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
																" WHERE FULL_NAME = UPPER('"+clientName+"') OR "+
																"       (ADDRESS1 = UPPER('"+addrees1+"') AND "+
																"	      CITY_CODE = UPPER('"+city_code+"')) OR "+
																"       TEL_NO    = UPPER('"+tel_no+"') OR "+
																"	      EMAIL     = UPPER('"+email+"') OR "+
																"       NIC_NO    = UPPER('"+nic_no+"') OR "+
																"	      BUSINESS_CERTIFICATE_NO = UPPER('"+bc_no+"') AND "+
		                            "       ACTIVE_STATUS='Y'");
				boolean more = rs.next();
				if(more){
				   out.println(rs.getString(1)+"@#"+rs.getString(2)+"@#"+rs.getString(3)+"@#"+rs.getString(4)
						           +"@#"+rs.getString(5)+"@#"+rs.getString(6)+"@##@");
				}	    
						
			}
			
	    	
	    	

			//=========================================================================================================================			
  
			
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
