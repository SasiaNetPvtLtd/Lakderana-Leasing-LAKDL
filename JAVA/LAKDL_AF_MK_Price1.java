//Option Id is 2.0  
//This File was created by SVA on 17-05-2006 
//Marketing Pricing Display
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;

import oracle.jdbc.driver.*;

public class LAKDL_AF_MK_Price1 extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf;
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
			//out.println("conn="+conn);
			//Class.forName("oracle.jdbc.driver.OracleDriver");
      //conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
			CallableStatement callstmt1 =null;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(2);
			
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
				
				//Check Values Using AJAX
				out.println("function makeRequest(url,opt) {");
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
        out.println("http_request.onreadystatechange = function() { alertContents(http_request); };");
				//alertContents(http_request); };
        //http_request.open('GET', "http://localhost:/myserver/servlet/CreateFileFormat?chksql=get_pmt_value_working&rate=18&value=2500000&terms=48&freq=12&type=ADDVANCE&tax=.1", true);
        //http://localhost:/myserver/servlet/CreateFileFormat?chksql=dis_data
				//http://localhost:/myserver/servlet/CreateFileFormat?chksql=get_pmt_value_working&rate=18&value=2500000&terms=48&freq=12
				//"http://localhost:/myserver/servlet/LAKDL_AF_Inquiry?chksql=get_Method&Meth=getCustomerCat&value=Y&value1=TEST"
        
				out.println(" if(opt=='YES'){");
			  out.println("  http_request.onreadystatechange = function() { alertContents(http_request); };");
				out.println("  http_request.open('POST',url, true);");
				out.println("	 http_request.setRequestHeader(\"Content-Type\",\"application/x-www-form-urlencoded\");");
				out.println("	 m_send_val = \"chksql=get_advance_price_cal&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+");
				out.println("	              \"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+");
				out.println("	              \"&last_rent=\"+document.Form1.LAST_RENTAL.value+\"&nitbasemar=\"+document.Form1.INTEREST_BASE_MARGIN.value+\"&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+document.Form1.RESIDUAL_VALUE.value+");
				out.println("	              \"&other_cha=0&maintan=0&supcrper=0&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+document.Form1.NIBSM.value+");
				out.println("	              \"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&installments=\";");
									
									
										
				out.println("	 m_fact_val = \"\";"); 
				out.println("	 m_prac_val = \"\";"); 
				out.println("  m_percentage = \"\";"); 
				out.println("  for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
			  out.println("    m_send_val   = m_send_val+unformat_noobject(document.Form1.elements[\"NETAMT\"+i].value)+\"@\";");
				out.println("    m_fact_val   = m_fact_val+unformat_noobject(document.Form1.elements[\"FACTOR\"+i].value)+\"@\";");
			  out.println("    m_prac_val   = m_prac_val+unformat_noobject(document.Form1.elements[\"PRACENT\"+i].value)+\"@\";");
				
				out.println("    m_percentage = m_percentage+unformat_noobject(document.Form1.elements[\"PERCENTAGE\"+i].value)+\"@\";");
				//out.println("    alert('m_percentage'+m_percentage);");
			  out.println("	 }");
				out.println("    m_send_val = m_send_val+\"&factor=\"+m_fact_val+\"&pracent=\"+m_prac_val+\"&ami=\"+document.Form1.AMI.value+\"&percentage=\"+m_percentage;");
				//window.open("http://www.ofscl-leasing.lk:/myserver/servlet/LAKDL_AF_MK_Price?"+m_send_val);
				out.println("  window.open(url+'?'+m_send_val);");
				out.println("  http_request.send(m_send_val);");
			  out.println(" }else{");
				
				out.println("  http_request.open('GET',url, true);");
        //alert('1111');
				out.println("  http_request.send(null);");
				out.println(" }");
        out.println("}");

        out.println("function alertContents(http_request) {");
				//out.println(" alert('test');");
        out.println(" if (http_request.readyState == 4) {");
        out.println("    if (http_request.status == 200) {");
        //out.println("      alert(http_request.responseText);");
				out.println("         price_cal.innerHTML=http_request.responseText; ");
        out.println("    } else {");
        out.println("        alert('There was a problem with the request.');");
        out.println("    }");
        out.println(" }");
        out.println("}");

        out.println("function AjaxReturn_GetCustomerDetails(res){");
				out.println("alert(res.value);");
				out.println("  var ds = res.value;");
				out.println("  var html = new Array();");
				  
				out.println("  if(ds!= null && typeof(ds) == \"object\" && ds.Tables!= null)");
				out.println("  {");
				  
				out.println("    for(var i=0;i<ds.Tables[0].Rows.length;i++)");
				out.println("    {");
				out.println("      html[html.length] = \"<tr><td>\" + ");
				out.println("                          ds.Tables[0].Rows[i].CustomerID + \"</td><td>\" ");
				out.println("                          + ds.Tables[0].Rows[i].Name + ");
				out.println("                          \"</td></tr>\";");
				out.println("    }");
				out.println("    document.getElementById(\"AdvancedSearchControl1_Label1\").innerHTML = ");
				out.println("        \"<table><tr><td>Customer ID</td><td>Customer Name</td></tr>\" ");
				out.println("        html.join(\"\") + \"</table>\";");
				out.println("  }");
				out.println("  res = null;");
				out.println("}");
				//End Of Checking Values
				
				//Help Function
				out.println("function MyDialog(){");
				out.println("this.valout   = new Array(10);");
				out.println("}");	
				
				out.println("function get_help(Start,End,Hid_No,Crit,Sql,IfCount) {");			
				
				out.println("oBj = new MyDialog();");
				out.println("oBj.valout[3]  = \" \";");
				out.println("oBj.valout[4]  = \" \";");
				out.println("oBj.valout[5]  = \" \";");
				out.println("window.open('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Help_Servlet?class_in="+m_client_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=');");
				out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Help_Servlet?class_in="+m_client_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("alert('oBj-'+oBj+'');");
				
				out.println("if(oBj.valout[0]=='Next')  {");
				out.println("Next(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount);");
				out.println("}");
				out.println("else if  (oBj.valout[0]=='Prev') {");
				out.println("Prev(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount);");
				out.println("}		");
				out.println("else if(oBj.valout[0] == 'Exit'){");
				out.println("}");
				out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != ''){");
				out.println("if(IfCount=='1'){"); 
				out.println("client_assign(oBj);");
				//out.println("load_data(document.Form1.txt_aff_code.value);");
				out.println("}");
				out.println("else if(IfCount=='2'){"); 
				out.println("modle_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='3'){"); 
				out.println("");
				out.println("}");
				out.println("else if(IfCount=='4'){"); 
				out.println("vendor_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='5'){"); 
				out.println("mk_trn_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='6'){"); 
				out.println("mk_sub_trn_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='7'){"); 
				out.println("inq_assign(oBj);");
				out.println("}");
				
				
				out.println("}");
				out.println("else if(oBj.valout[4] != \" \"){ ");
				out.println("Crit = oBj.valout[4];");
				out.println("criteria_1('1','10','1',oBj.valout[4],Sql,IfCount);");
				out.println("}	");
				out.println("}");	
				
				out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount)");
				out.println("}");
				
				out.println("function Next(Start,End,Hid_No,Crit,Sql,IfCount){");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount)");
				out.println("}");
				
				out.println("function criteria_1(Start,End,Hid_No,Crit,Sql,IfCount){");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				//client Help
				out.println("function model_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.ASSET_MAKE.value+\"@\"+document.Form1.SUB_MODEL.value+\"@Y@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function modle_assign(oBj){");
				out.println(" document.Form1.SUB_MODEL.value      =oBj.valout[2]");
				out.println(" document.Form1.VAT_PERCENTAGE.value =oBj.valout[4]");
				out.println(" document.Form1.MODEL_CODE.value =oBj.valout[4]");
				out.println("}");		
				//Marketing Officer
				out.println("function vendor_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.VENDOR.value+\"@Y@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function vendor_assign(oBj){");
				out.println(" document.Form1.VENDOR.value =oBj.valout[2]");
				out.println(" document.Form1.VENDOR_CODE.value =oBj.valout[1]");
				out.println("}");				
				
				out.println("function trn_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.TRANSACTION_CODE.value+\"@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function mk_trn_assign(oBj){");
				out.println(" document.Form1.TRANSACTION_CODE.value =oBj.valout[2]");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");		
				
				out.println("function sub_trn_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.TRANSACTION_SUB.value+\"@\"+document.Form1.TRANSACTION_TYPE.value+\"@A@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function mk_sub_trn_assign(oBj){");
				out.println(" document.Form1.TRANSACTION_SUB.value =oBj.valout[2]");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");		
				
				out.println("function inq_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.INQ_NO.value+\"@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function inq_assign(oBj){");
				out.println(" alert(oBj.valout[12]+'--'+oBj.valout[10]);document.Form1.INQ_NO.value =oBj.valout[2]");
				out.println("}");		
				
				
				//End of Help Function
				//change required DIV
				out.println("function change_div(){");
				out.println(" if(document.Form1.CLIENT_TYPE.options[document.Form1.CLIENT_TYPE.selectedIndex].text!='Individual' &&");
				out.println("    document.Form1.CONTACT_PERSON.value==''){ ");
				out.println("   conp.innerHTML=\"Contact Person *\";");
				out.println(" }else{");
				out.println("   conp.innerHTML=\"Contact Person\";");
				out.println(" }");
				out.println("}");
				
				
				//end of DIV change
				//Main Button Action
				//Submit
				out.println("function befor_submit(){");
				out.println(" m_bsubmit='0';");
				out.println(" if(document.Form1.TRANSACTION_TYPE.value==''){ ");
				out.println("   trntype.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.TRANSACTION_SUB.value=='' ){ ");
				out.println("   trnsub.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.INTEREST_TYPE.value==''){  ");
				out.println("      inttype.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.ASSET_CONDITION.value==''){  ");
				out.println("      conass.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.PERIOD.value==''){  ");
				out.println("      peri.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				/*out.println(" if(document.Form1.MOBILE_NO.value==''){  ");
				out.println("      cont.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				*/
				out.println(" if(document.Form1.CACULATED.value==\"NO\"){  ");
				out.println("      alert('Please calculate values befor save.');");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				
				out.println(" if(m_bsubmit=='0'){");
				out.println("  if(document.Form1.OPTION_NAME.value==\"NEW\" && document.Form1.PRICE_NO.value!=\"\"){ ");
				out.println("   alert();");
				out.println("  }else{");
				out.println("   document.Form1.VAT_AMOUNT.value=unformat_noobject(document.Form1.VAT_AMOUNT.value);");
				out.println("   document.Form1.NET_AMOUNT.value=unformat_noobject(document.Form1.NET_AMOUNT.value);");
				out.println("   document.Form1.GROSS_AMOUNT.value=unformat_noobject(document.Form1.GROSS_AMOUNT.value);");
				out.println("   for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
				out.println("     document.Form1.elements[\"PRACENT\"+i].value = unformat_noobject(document.Form1.elements[\"PRACENT\"+i].value) ");// unformat_noobject(
				out.println("     document.Form1.elements[\"NETAMT\"+i].value = unformat_noobject(document.Form1.elements[\"NETAMT\"+i].value) ");//
				out.println("     document.Form1.elements[\"GROSS\"+i].value = unformat_noobject(document.Form1.elements[\"GROSS\"+i].value) ");//");//unformat_noobject(
				out.println("     document.Form1.elements[\"FACTOR\"+i].value = unformat_noobject(document.Form1.elements[\"FACTOR\"+i].value) ");//");//unformat_noobject(
				out.println("   }");
				
				out.println("   for(j=0;j<parseFloat(document.Form1.c_c_count.value);j++){");
				out.println("     document.Form1.elements['CHARGE_'+j].value=unformat_noobject(document.Form1.elements['CHARGE_'+j].value);");
				out.println("     document.Form1.elements['CHARGE_PER_'+j].value=unformat_noobject(document.Form1.elements['CHARGE_PER_'+j].value);");
				out.println("   }");
				
				out.println("   document.Form1.action=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Save\";");
				out.println("   document.Form1.submit();");
				out.println("  }");
				out.println(" }");
				out.println("}");
				//end of Submit Function
				
				
				out.println("function befor_new(){");
				out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  Form1.reset() ;  ");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_back(){");
				out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  Form1.reset() ;  ");
				//out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
				//out.println("  document.Form1.OPTION_DESC.value=\"B\";");
				out.println("  document.Form1.PRICE_NO.disabled=true;");
				out.println("  document.Form1.inqu_help.disabled=true;");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_modify(){");
				out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  Form1.reset();   ");
				out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
				out.println("  document.Form1.OPTION_DESC.value=\"Edit\";");
				out.println("  alert(document.Form1.OPTION_DESC.value);");
				out.println("  document.Form1.PRICE_NO.disabled=false;");
				out.println("  document.Form1.inqu_help.disabled=false;");
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
				
				out.println("function befor_cal(m_stat) {");
        out.println(" if(document.Form1.RATE.value!=\"\" && document.Form1.NET_AMOUNT.value !=\"\" && document.Form1.VAT_PERCENTAGE.value !=\"\" && document.Form1.PERIOD.value !=\"\" && document.Form1.GROSS_AMOUNT.value!=\"\"){");
        out.println("  if(m_stat==\"YES\"){");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price\";");
        out.println(" }else{");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?chksql=get_basic_price_cal&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+\"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&ami=\"+document.Form1.AMI.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&last_rent=\"+document.Form1.LAST_RENTAL.value+\"&nitbasemar=\"+document.Form1.INTEREST_BASE_MARGIN.value+\"&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+document.Form1.RESIDUAL_VALUE.value+\"&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+document.Form1.NIBSM.value+\"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"\";");
        out.println(" }");
        out.println("   m_opt=document.Form1.CACULATED.value;");
        out.println("   document.Form1.CACULATED.value=\"YES\";");
        out.println("   makeRequest(m_url,m_stat);");
        out.println(" }else{");
				out.println("   alert('Please Enter ');");
        out.println(" }");
        
        out.println("}");
        
				
				//end of Main Button Action
				//onload Action
				out.println("function befor_onload(){");
				out.println("  document.Form1.PRICE_NO.disabled=true;");
				out.println("  document.Form1.inqu_help.disabled=true;");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println("  load_roll_value(\"New\");"); 
				out.println("}");
				//end of onload
				
				out.println("function cal_VatNet(){alert();");
				out.println("  if(document.Form1.GROSS_AMOUNT.value!=\"\" && document.Form1.VAT_PERCENTAGE.value!=\"\"){");
				out.println("   document.Form1.NET_AMOUNT.value=format_noobject(parseFloat(unformat_noobject(document.Form1.GROSS_AMOUNT.value))/((parseFloat(unformat_noobject(document.Form1.VAT_PERCENTAGE.value))/100)+1));");
				out.println("   document.Form1.VAT_AMOUNT.value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*(parseFloat(unformat_noobject(document.Form1.VAT_PERCENTAGE.value))/100));");
				
				out.println("   for(j=0;j<parseFloat(document.Form1.c_c_count.value);j++){");
				out.println("    if(document.Form1.elements['CHARGE_'+j].value=='0' || document.Form1.elements['CHARGE_'+j].value==null){");
				out.println("     document.Form1.elements['CHARGE_'+j].value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*document.Form1.elements['CHARGE_PER_'+j].value);");
				out.println("    }");
				out.println("   }");
				
				out.println("   document.Form1.GROSS_AMOUNT.value=format_noobject(unformat_noobject(document.Form1.GROSS_AMOUNT.value));");
				out.println("  }");
				out.println("}");
				
				 out.println("function cal_VatGross(){alert();");
				out.println("  if(document.Form1.NET_AMOUNT.value!=\"\" && document.Form1.VAT_PERCENTAGE.value!=\"\"){");
				out.println("   document.Form1.GROSS_AMOUNT.value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*((parseFloat(unformat_noobject(document.Form1.VAT_PERCENTAGE.value))/100)+1));");
				out.println("   document.Form1.VAT_AMOUNT.value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*(parseFloat(unformat_noobject(document.Form1.VAT_PERCENTAGE.value))/100));");
				
				out.println("   for(j=0;j<parseFloat(document.Form1.c_c_count.value);j++){");
				out.println("    if(document.Form1.elements['CHARGE_'+j].value=='0' || document.Form1.elements['CHARGE_'+j].value==null){");
				out.println("     document.Form1.elements['CHARGE_'+j].value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*document.Form1.elements['CHARGE_PER_'+j].value);");
				out.println("    }");
				out.println("   }");
				
				out.println("   document.Form1.NET_AMOUNT.value=format_noobject(unformat_noobject(document.Form1.NET_AMOUNT.value));");
				out.println("  }");
				out.println("}");
				
				out.println("function load_roll_value(m_val){"); 
			  out.println("help_box.innerHTML=\"Marketing Pricing - \"+m_val;"); 
			  out.println("}");
				
				out.println("   ");
				
				
				out.println("</Script>");
				out.println("<body onload=\"befor_onload()\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"Hid_scr_name\" value=\"F_MK_PRICE\">");
				out.println("<input type=hidden name=\"OPTION_NAME\" value=\"NEW\">");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"New\">");
				out.println("<input type=hidden name=\"CURRENCY_CODE\" value=\"SLR\">");
				out.println("<input type=hidden name=\"EXCHANGE_RATE\" value=\"0\">");
				out.println("<input type=hidden name=\"CACULATED\" value=\"NO\">");
				//out.println("<input type=hidden name=\"INQ_NO\" value=\"\">");
				
				
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
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
				out.println("<tr>");
				/*out.println("<td style=\"width: 6px\"><img src=\""+m_html_client_url+"/images/btnback.gif\" ></td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><img src=\""+m_html_client_url+"/images/btndelete.gif\" ></td>");
				//out.println("<td>&nbsp;</td>");
				out.println("<td onclick=befor_deactive();><img src=\""+m_html_client_url+"/images/btndelete.gif\" ></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td onclick=befor_modify();><img src=\""+m_html_client_url+"/images/btnedit.gif\" ></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td onclick=befor_active();><img src=\""+m_html_client_url+"/images/btncancel.gif\" ></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td onclick=befor_reset();><img src=\""+m_html_client_url+"/images/btnreset.gif\" ></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td onclick=befor_submit();><img src=\""+m_html_client_url+"/images/btnsubmit.gif\" ></td>");*/
				
				out.println("<td style=\"width: 6px\">");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=new      value=\"New\"       class=mainbut onclick=befor_new();      onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");//document.Form1.OPTION_DESC.value
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=edit     value=\"Edit\"      class=mainbut onclick=befor_modify();   onMouseOver='load_roll_value(\"Edit\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
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
				out.println("<td><input type=button name=back     value=\"Close\"     class=mainbut onclick=befor_back();     onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset_   value=\"Reset\"     class=mainbut onclick=befor_reset();    onMouseOver='load_roll_value(\"Reset\");'     onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=cal     value=\"Calculate\" class=mainbut onclick=befor_cal(\"NO\");     onMouseOver='load_roll_value(\"Calculate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=recal     value=\"Re-Cal\" class=mainbut onclick=befor_cal(\"YES\");     onMouseOver='load_roll_value(\"Re-Calculate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=recal     value=\"Go to End\" class=mainbut onclick=befor_end(\"YES\");  onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
				out.println("</tr></table>");
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
				
				out.println("<tr class=tr_input>");
				out.println("<td width=\"15%\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"120\" ></td>");
				out.println("<td width=\"35%\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"180\" ></td>");
				out.println("<td width=\"15%\">");
				out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" ></td>");
				out.println("<td>");
				out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" ></td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
				out.println("<tr class=tr_input>");
				out.println("<td valign=top  width=40%>");
				//out.println("<td class=\"txt-bodyGreen\" height=\"18\">Personal Details</td>");
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//sub table start(data entry)
				out.println("<tr class=tr_input>");
				out.println("<td width=25%><div id=prino class=div_input>Pricing No </div></td>");
				out.println("<td><input name=\"PRICE_NO\" type=\"text\" maxlength=\"15\" class=\"txt_input\" >");
				out.println("<input type=button name=inqu_help value=Help class=\"but_input\" onclick=\"inq_help('1','10','13','InquirySql','7')\"></td>");
				
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td><div id=inqno class=div_input>Inquiry No </div></td>");
				out.println("<td><input name=\"INQ_NO\" type=\"text\" maxlength=\"15\" class=\"txt_input\" >");
				out.println("<input type=button name=inq_help value=Help class=\"but_input\" onclick=\"inq_help('1','10','13','InquirySql','7')\"></td>");
				
				out.println("</tr>");
				
					out.println("<tr class=tr_input>");
				out.println("<td><div id=trntype class=div_input>Transaction Type *</div></td>");
				out.println("<td>");
				out.println("<select name=\"TRANSACTION_TYPE\" class=\"txt_input\" >");
				
				rs = stmt.executeQuery(CO_methods.getTransaction(m_schema_name,"Y",""));
				        boolean	more = rs.next();
								while(more){
				           out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
									 more = rs.next();	
								}	
				out.println("</SELECT>");
				
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td ><div id=trnsub class=div_input>Transaction Sub Type *</div></td>");
				out.println("<td>");
				out.println("<input name=\"TRANSACTION_SUB\" type=\"text\" maxlength=\"15\" class=\"txt_input\" >");
				out.println("<input type=button name=trnsub_help value=Help class=\"but_input\" onclick=\"sub_trn_help('1','10','0','TrnSubSql','6')\">");
				out.println("</td>");
							
				//out.println("<input name=\"INITIATION_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				/*out.println("<td >Customer Category</td>");
				out.println("<td>");
				out.println("<select name=\"CLIENT_CATEGORY\" class=\"txt_input\" >");
				rs = stmt.executeQuery(CO_methods.getCustomerCat(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT></td>");*/
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td ><div id=inttype class=div_input>Interest Type *</div></td>");
				out.println("<td>");
				out.println("<select name=\"INTEREST_TYPE\" class=\"txt_input\" onChange=\"change_div()\">");
				rs = stmt.executeQuery(CO_methods.getInterestType(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td >Variable Interest Base and Margin</td>");
				out.println("<td>");
				out.println("<input name=\"INTEREST_BASE_MARGIN\" type=\"text\" maxlength=\"50\" class=\"txt_input\" ></td>");
				out.println("</tr>");
				

				out.println("<tr class=tr_input>");
				out.println("<td ><DIV id=peri  class=div_input>Period of Financing *</div></td>");
				out.println("<td>");
				out.println("<input name=\"PERIOD\" type=\"text\" maxlength=\"20\" class=\"txt_input\" value=4 >");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Repayment Mode</td>");
				out.println("<td>");
				out.println("<select name=\"PAYMENT_MODE\" class=\"txt_input\" onChange=\"change_div()\">");
		    rs = stmt.executeQuery(CO_methods.getRepaymentMeth(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}
				out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Repayment Interval *</td>");
				out.println("<td>");
				out.println("<select name=\"REPAYMENT_INTERVAL\" class=\"txt_input\" onChange=\"change_div()\">");
				rs = stmt.executeQuery(CO_methods.getRepaymentInt(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}
				out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Rate *</td>");
				out.println("<td>");
				out.println("<input name=\"RATE\" type=\"text\" maxlength=\6\" class=\"txt_input\" value=18 >");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >VAT Percentage *</td>");
				out.println("<td>");
				out.println("<input name=\"VAT_PERCENTAGE\" type=\"text\" maxlength=\"5\" class=\"txt_input\" onchange=cal_VatNet() value=20>");
				out.println("<input type=button name=vat_help value=Help class=\"but_input\" onclick=\"model_help('1','10','0','ModelSql','2')\"></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >VAT % Applicable *</td>");
				out.println("<td>");
				out.println("<input name=\"VAT_PER_APP\" type=\"text\" maxlength=\"5\" class=\"txt_input\" onchange=cal_VatNet() value=15>");
				out.println("<input type=button name=vat_help value=Help class=\"but_input\" onclick=\"model_help('1','10','0','ModelSql','2')\"></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Amount Gross *</td>");
				out.println("<td>");
				out.println("<input name=\"GROSS_AMOUNT\" type=\"text\" maxlength=\"20\" class=\"txt_input\" onchange=cal_VatNet() STYLE=\"{text-align:right;}\">");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Amount VAT *</td>");
				out.println("<td>");
				out.println("<input name=\"VAT_AMOUNT\" type=\"text\" maxlength=\"20\" class=\"txt_input\" STYLE=\"{text-align:right;}\" >");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Amount Net *</td>");
				out.println("<td>");
				out.println("<input name=\"NET_AMOUNT\" type=\"text\" maxlength=\"20\" class=\"txt_input\" STYLE=\"{text-align:right;}\" onchange=cal_VatGross()>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >NIBSM *</td>");//Non Interest Bearing Security Margin
				out.println("<td>");
				out.println("<input name=\"NIBSM\" type=\"text\" maxlength=\"20\" class=\"txt_input\" value=0 STYLE=\"{text-align:right;}\">");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >AMI *</td>");//Advance Monthly Installment
				out.println("<td>");
				out.println("<input name=\"AMI\" type=\"text\" maxlength=\"20\" class=\"txt_input\" value=0 STYLE=\"{text-align:right;}\">");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Last Rental *</td>");
				out.println("<td>");
				out.println("<input name=\"LAST_RENTAL\" type=\"text\" maxlength=\"20\" class=\"txt_input\" value=0 STYLE=\"{text-align:right;}\">");
				out.println("</td>");
				out.println("</tr>");

				out.println("<tr class=tr_input>");
				out.println("<td >Residual Value *</td>");
				out.println("<td>");
				out.println("<input name=\"RESIDUAL_VALUE\" type=\"text\" maxlength=\"20\" class=\"txt_input\" value=0 STYLE=\"{text-align:right;}\">");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Cash Outflow Pattern *</td>");
				out.println("<td>");
				out.println("<input name=\"CASH_OUTFLOW\" type=\"text\" maxlength=\"20\" class=\"txt_input\" value=0 >");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Supplier Credit  </td>");
				out.println("<td>");
				out.println("<input name=\"SUPPLIER_CREDIT\" type=\"text\" maxlength=\"20\" class=\"txt_input\" value=0 >");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Cash Inflow Pattern *</td>");
				out.println("<td>");
				out.println("<input name=\"CASH_INFLOW\" type=\"text\" maxlength=\"20\" class=\"txt_input\"  >");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td ><div id=conass class=div_input>Condition of Asset *</div></td>");
				out.println("<td >");
				out.println("<select name=\"ASSET_CONDITION\" class=\"txt_input\" onChange=\"change_div()\">");
				rs = stmt.executeQuery(CO_methods.getConditionOfAsset(m_schema_name,"Y","",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Item Category</td>");
				out.println("<td>");
				out.println("<select name=\"ITEM_CAT\" class=\"txt_input\" onChange=\"change_div()\">");
				rs = stmt.executeQuery(CO_methods.getItemCat(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Item Sub Category </td>");
				out.println("<td>");
				out.println("<select name=\"ITEM_SUB_CAT\" class=\"txt_input\" onChange=\"change_div()\">");
				rs = stmt.executeQuery(CO_methods.getItemSubCat(m_schema_name,"Y","",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Make of Asset </td>");
				out.println("<td>");
				out.println("<select name=\"ASSET_MAKE\" class=\"txt_input\" onChange=\"change_div()\">");
				rs = stmt.executeQuery(CO_methods.getMake(m_schema_name,"Y","",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Sub Model of Asset</td>");
				out.println("<td><input name=\"SUB_MODEL\" type=\"text\" maxlength=\"10\" class=\"txt_input\" onchange=chk_model()><INPUT TYPE=HIDDEN NAME=MODEL_CODE>");
				out.println("<input type=button name=mode_help value=Help class=\"but_input\" onclick=\"model_help('1','10','0','ModelSql','2')\"></td>");
				out.println("</tr>");
								
        out.println("<tr class=tr_input>");
				out.println("<td >Engine Capacity </td>");
				out.println("<td>");
				out.println("<select name=\"ENGIN_CAPACITY\" class=\"txt_input\" onChange=\"change_div()\">");
				rs = stmt.executeQuery(CO_methods.getEngineCapacity(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Fuel Type</td>");
				out.println("<td>");
				 out.println("<select name=\"FUEL_TYPE\" class=\"txt_input\" onChange=\"change_div()\">");
				rs = stmt.executeQuery(CO_methods.getFuelType(m_schema_name,"Y","",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Buy Back </td>");
				out.println("<td>");
				out.println("<select name=\"BUY_BACK\" class=\"txt_input\" onChange=\"change_div()\">");
		    out.println("<OPTION value=\"YES\">Yes</option>");
			  out.println("<OPTION value=\"NO\" >No </option>");
			  out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Vendor</td>");
				out.println("<td>");
				out.println("<input name=\"VENDOR\" type=\"text\" maxlength=\"20\" class=\"txt_input\"  ><INPUT TYPE=HIDDEN NAME=VENDOR_CODE>");
				out.println("<input type=button name=ven_help value=Help class=\"but_input\" onclick=\"vendor_help('1','10','0','VendorSql','4')\"></td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Asset Usage Type</td>");
				out.println("<td>");
				out.println("<select name=\"ASSET_USAGE\" class=\"txt_input\" onChange=\"change_div()\">");
				rs = stmt.executeQuery(CO_methods.getAssetUsageType(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=\"txt-bodyGreen\">");
				//out.println("<td class=\"txt-bodyGreen\" height=\"18\">Personal Details</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=hidden name=c_c_count value=0></td>");
				out.println("</tr>");
				 
				int i = 0;
				rs = stmt.executeQuery(CO_methods.getChargesApp(m_schema_name,"Y","","",""));
				more = rs.next();
				if(more){
				
				out.println("<tr class=\"txt-bodyGreen\">");
				//out.println("<td class=\"txt-bodyGreen\" height=\"18\">Personal Details</td>");
				out.println("<td>Standard Charges and Costs</td>");
				out.println("<td></td>");
				out.println("</tr>");
				
				while(more){
				  out.println("<tr class=tr_input>");
					out.println("<td >"+rs.getString(1)+"<input type=hidden name=\"CHARGE_NAME_"+i+"\" value="+rs.getString(2)+"></td>");
					out.println("<td><input type=hidden name=\"CHARGE_PER_"+i+"\" value="+rs.getString(4)+">");
					out.println("<input name=\"CHARGE_"+i+"\" type=\"text\" maxlength=\"20\" class=\"txt_input\" value=\""+rs.getString(3)+"\">");
					out.println("</td>");
					out.println("</tr>");
					i = i+1;
				  more = rs.next();	
				}
				  out.println("<script>");
					out.println("document.Form1.c_c_count.value=\""+i+"\";");
					out.println("</script>");
					
				}
				/*
				out.println("<tr class=tr_input>");
				out.println("<td >Revenue License</td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Taxes</td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Insurance</td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Document</td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=\"txt-bodyGreen\">");
				//out.println("<td class=\"txt-bodyGreen\" height=\"18\">Personal Details</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td></td>");
				out.println("</tr>");
				 */
				
				
				out.println("<tr class=\"txt-bodyGreen\">");
				//out.println("<td class=\"txt-bodyGreen\" height=\"18\">Personal Details</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=hidden name=m_count value=0></td>");
				out.println("</tr>");
				 
				out.println("<tr class=\"txt-bodyGreen\">");
				//out.println("<td class=\"txt-bodyGreen\" height=\"18\">Personal Details</td>");
				out.println("<td>Maintenance</td>");
				out.println("<td></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Applicability</td>");
				out.println("<td>");
				out.println("<select name=\"MAINTENANCE_APP\" class=\"txt_input\" onChange=\"change_div()\">");
		    out.println("<OPTION value=\"Y\">Yes</option>");
			  out.println("<OPTION value=\"N\" >No </option>");
			  out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("</td>");
				out.println("</tr>");
				/*
				i = 0;
				rs = stmt.executeQuery(CO_methods.getChargesApp(m_schema_name,"Y","","",""));
				more = rs.next();
				if(more){
				
			   while(more){
				  out.println("<tr class=tr_input>");
					out.println("<td >"+rs.getString(1)+"<input type=hidden name=\"MAINTE_NAME_"+i+"\" value="+rs.getString(1)+"></td>");
					out.println("<td>");
					out.println("<input name=\"MAINTANENCE_"+i+"\" type=\"text\" maxlength=\"20\" class=\"txt_input\" value=\"0\">");
					out.println("</td>");
					out.println("</tr>");
					i = i+1;
				  more = rs.next();	
				}
				  out.println("<script>");
					out.println("document.Form1.c_c_count.value=\""+i+"\";");
					out.println("</script>");
					
				}
				*/
				/*
				out.println("<tr class=tr_input>");
				out.println("<td >Garage</td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Mileage</td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Document</td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");*/
				out.println("</table>");//sub table close(data entry)
				out.println("</td>");
				
				out.println("<td valign=top><div id=price_cal></div>");
				out.println("</td>");
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
				/*
				out.println("<td style=\"width: 6px\">");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back value=\"Back\" class=mainbut onclick=befor_back();></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=delete value=\"Delete\" class=mainbut onclick=befor_deactive();></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=edit value=\"Edit\" class=mainbut onclick=befor_modify();></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=cancel value=\"Cancel\" class=mainbut onclick=befor_active();></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset value=\"Reset\" class=mainbut onclick=befor_reset();></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=b_submit value=\"Submit\" class=mainbut onclick=befor_submit();></td>");
        */
				out.println("<td style=\"width: 6px\">");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=new1 value=\"New\" class=mainbut onclick=befor_new(); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");//document.Form1.OPTION_DESC.value
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=edit1 value=\"Edit\" class=mainbut onclick=befor_modify(); onMouseOver='load_roll_value(\"Edit\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=delete1 value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=cancel1 value=\"Re-active\" class=mainbut onclick=befor_active(); onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back1 value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=b_submit1 value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset1 value=\"Reset\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Reset\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=cal1 value=\"Calculate\" class=mainbut onclick=befor_cal();></td>");
 
				out.println("</tr></table>");
				out.println("&nbsp;&nbsp;</td>");
				out.println("</tr>");
				
				/*out.println("<!--tr>");
				out.println("<td class="pdn_txtpos1 & txt-bodyRed" height="15">");
				out.println("<div id="ValidationSummary1" style="color:Red;display:none;">");
				
				out.println("</div>");
				out.println(" <span id="lblError" style="color:Red;"></span></td>");
				out.println("</tr-->");
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
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("</html>");
			
			
			}
			else if(m_chksql.trim().equals("get_basic_price_cal")){
          String m_rate		 = req.getParameter("rate");
          String m_value   = req.getParameter("value");
          String m_terms	 = req.getParameter("terms");
          String m_freq		 = req.getParameter("freq");
          String m_type    = req.getParameter("type");
					String m_vat_per = req.getParameter("tax");
          String m_ami     = req.getParameter("ami");
					String m_vat_app = req.getParameter("tax_app");
          											
          
          rs = stmt.executeQuery ("SELECT "+m_rate+","+m_rate+"/100,("+m_rate+"/100)/"+m_freq+","+m_freq+","+m_terms+","+m_value+","+
					                        "       "+m_schema_name+".AF_CO_CAL_FACTOR("+m_rate+","+m_freq+","+m_terms+",'"+m_type+"','"+m_ami+"'),"+
																	"       "+m_vat_per+","+m_freq+"*"+m_terms+",'"+m_ami+"' "+
																	" FROM DUAL");
																	
          /*
					out.println("<html><head>");
          out.println("<title>PMT Value - Formulation</title></head>");
          out.println("<body bgcolor='white'>");
          out.println("<form name='Form1'>");
          out.println("<br>");
          */
          int 	 mm_freq					  = 0;
          int 	 mm_terms						= 0;
          int    mm_term            = 0;
					double mm_value						= 0;
          double mm_rate_per_month  = 0;
					double mm_tot_fact 				= 0;
					double mm_vat_per 				= 0;
					int mm_ami             = 0;
					
          if(rs.next()){
              
              mm_rate_per_month = rs.getDouble(3);
              mm_value					= rs.getDouble(6);
              mm_terms					= rs.getInt(5);
              mm_term 					= rs.getInt(9);
              mm_freq						= rs.getInt(4);
							mm_tot_fact				= rs.getDouble(7);
							mm_vat_per				= rs.getDouble(8);
              mm_ami     				= rs.getInt(10);
               
              out.println("<table class=table border='0' width='100%' >");
              out.println("<tr class=tr_input><td  width='40%' >Total Factor</td>");
              out.println("<td width='60%' >"+nf.format(rs.getDouble(7))+"</td></tr>");
              /*out.println("<tr><td  width='40%' >Rate (%)</td>");
              out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(1))+"</td></tr>");
              out.println("<tr><td  width='40%' >Rate </td>");
              out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(2))+"</td></tr>");
              out.println("<tr><td  width='40%' >Rate per Month</td>");
              out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(3))+"</td></tr>");
              out.println("<tr><td  width='40%' >Payment Frequency</td>");
              out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(4))+"</td></tr>");
              out.println("<tr><td  width='40%' >No of Installaments</td>");
              out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(5))+"</td></tr>");
              */
							out.println("</table>");
							
          }
          
          out.println("<br>");
          
          out.println("<table class=table border='0' width='100%' class=table>");
          out.println("<tr class=tr_input><td  width='10%' >Installment No(Y)</td>");
          //out.println("<td  width='20%' >Rate per Month(RM)</td>");
          //out.println("<td  width='30%' >(RM+1)=X</td>");
          out.println("<td  width='10%' >Factor</td>");
					out.println("<td  width='10%' >Percentage</td>");
					out.println("<td  width='20%' >Net Rental</td>");
					out.println("<td  width='20%' >Gross Rental</td>");
					out.println("<td  width='20%' >Pracent Value</td>");
					//out.println("<td  width='20%' >AMI</td>");
					
					
          out.println("</tr>");
          double sum_rate=0;
          double sum_rent=0;
					double sum_p_re=0;
					int m_start=0;
					int m_end  =mm_term;
					
					if(m_type.equals("ARREASE")){
					  m_start=1;
						m_end  =mm_term+1;
					}
					int j=0;
          for(j=m_start;j<m_end;j++){
              //out.println("J="+j);
              out.println("<tr class=tr_input><td  width='8%' >"+j+"<input type=hidden name=\"INSTALLMENT"+j+"\" value=\""+j+"\"></td>");
              //out.println("<td  width='20%' >"+nf.format(mm_rate_per_month)+"</td>");
              										
							rs = stmt.executeQuery ("SELECT "+(mm_rate_per_month+1)+",1/POWER("+(mm_rate_per_month+1)+","+j+"),"+
							                                ""+m_value+"/"+mm_tot_fact+"/(1+("+m_vat_app+"/100)),"+m_value+"/"+mm_tot_fact+","+//"+mm_vat_per+"
																							""+m_value+"/"+mm_tot_fact+"/1/POWER("+(mm_rate_per_month+1)+","+j+") "+
																			"FROM   DUAL ");

              if(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<td ><input name=\"FACTOR"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(2))+" maxlength=\"10\" class=\"txt_input1\" disabled></td>");
                  out.println("<td ><input name=\"PERCENTAGE"+j+"\" type=\"text\" value=\"1\" maxlength=\"5\" class=\"txt_input1\" ></td>");
                  out.println("<td ><input name=\"NETAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(3))+" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  out.println("<td ><input name=\"GROSS"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(4))+" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  out.println("<td ><input name=\"PRACENT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(5))+" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\"0\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  out.println("</tr>");
                  sum_rate=sum_rate+(rs.getDouble(2));
									sum_rent=sum_rent+(rs.getDouble(3));
									sum_p_re=sum_p_re+(rs.getDouble(5));
              }
          }
          out.println("<tr class=tr_input><td  ></td>");
          //out.println("<td  width='20%' ></td>");
          out.println("<td><input type=hidden name=hid_count value="+j+">"+nf.format(sum_rate)+"</td>");
          out.println("<td></td>");
          out.println("<td align=right>"+nf.format(sum_rent)+"</td>");
          out.println("<td></td>");
          out.println("<td align=right>"+nf.format(sum_p_re)+"</td>");
          //out.println("<td></td>");
          out.println("</tr></table>");
          
        
      }
			
			else if(m_chksql.trim().equals("get_advance_price_cal")){
			
			    String m_rate		    = req.getParameter("rate");
          String m_value      = req.getParameter("value");
          String m_terms	    = req.getParameter("terms");
          String m_freq		    = req.getParameter("freq");
          String m_type       = req.getParameter("type");
					String m_vat_per    = req.getParameter("tax");
          String m_install    = req.getParameter("installments");
					String m_factor     = req.getParameter("factor");
					String m_pracent    = req.getParameter("pracent");
					String m_percentage = req.getParameter("percentage");
					String m_ami        = req.getParameter("ami");
					String m_vat_app    = req.getParameter("tax_app");
          String m_sup_cr_per = req.getParameter("supcrper");
					String m_nibsm      = req.getParameter("nibsm");
          String m_residual   = req.getParameter("residual");
					String m_sup_credit = req.getParameter("sup_cr");
          String m_other_cha  = req.getParameter("other_cha");
					String m_maintanance= req.getParameter("maintan");
        	String m_int_base_m = req.getParameter("nitbasemar");
          String m_last_rent  = req.getParameter("last_rent");
          String m_cash_out   = req.getParameter("cashout");
          String m_trn_sub    = req.getParameter("trnsub");
          String m_int_type   = req.getParameter("nittype");
          String m_trn_type   = req.getParameter("trn_type");
          			
					//out.println("m_percentage="+m_percentage);											
          callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_MK_TEMP_PRICE_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19);END;");
				  callstmt1.setString(1 ,m_terms);
          callstmt1.setString(2 ,m_freq);
          callstmt1.setString(3 ,m_install);
					callstmt1.setString(4 ,m_pracent);
					callstmt1.setString(5 ,m_factor);
          callstmt1.setString(6 ,m_type);
 				  callstmt1.setString(7 ,m_username);
 				  callstmt1.setString(8 ,m_rate);
 				  callstmt1.setString(9 ,m_value);
 				  callstmt1.setString(10,m_vat_per);
					callstmt1.setString(11,m_percentage);	
 				  callstmt1.setString(12,m_vat_app);	
					callstmt1.setString(13,m_ami);	
					callstmt1.setString(14,m_sup_credit);	
					callstmt1.setString(15,m_sup_cr_per);	
					callstmt1.setString(16,m_nibsm);	
					callstmt1.setString(17,m_residual);	
					callstmt1.setString(18,m_other_cha);	
					callstmt1.setString(19,m_maintanance);	
					//callstmt1.setString(13,m_ami);	
						
 				  //out.println("t5");
			    callstmt1.execute();
					//out.println("t6");
			    
					
					
					out.println("<table class=table border='0' width='100%' class=table>");
          out.println("<tr class=tr_input><td  width='10%' >Installment No(Y)</td>");
          //out.println("<td  width='20%' >Rate per Month(RM)</td>");
          //out.println("<td  width='30%' >(RM+1)=X</td>");
          out.println("<td  width='10%' >Factor</td>");
					out.println("<td  width='10%' >Percentage</td>");
					out.println("<td  width='20%' >Net Rental</td>");
					out.println("<td  width='20%' >Gross Rental</td>");
					out.println("<td  width='20%' >Pracent Value</td>");
					//out.println("<td  width='20%' >AMI</td>");
					
					
          out.println("</tr>");
          double sum_rate=0;
          double sum_rent=0;
					double sum_p_re=0;
					int m_start=0;
					/*int m_end  =mm_term;
					
					if(m_type.equals("ARREASE")){
					  m_start=1;
						m_end  =mm_term+1;
					}
					*/
					int j=0;
          //for(j=m_start;j<m_end;j++){
              //out.println("J="+j);
             //out.println("<td  width='20%' >"+nf.format(mm_rate_per_month)+"</td>");
              					
							rs = stmt.executeQuery (" SELECT A.INSTALLMENT, A.FACTOR, A.NEW_NET, A.NEW_GROSS, "+
																			"        A.NEW_PRACENT, A.PERCENTAGE, A.AMI "+
																			"  FROM  "+m_schema_name+".AF_MK_TBD_PRICE_CAL A "+
													 						"	WHERE  ENT_USER  = '"+m_username+"'");
																			
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input><td  width='8%' >"+rs.getString(1)+"<input type=hidden name=\"INSTALLMENT"+j+"\" value=\""+j+"\"></td>");
                  out.println("<td ><input name=\"FACTOR"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(2))+" maxlength=\"10\" class=\"txt_input1\" disabled></td>");
                  out.println("<td ><input name=\"PERCENTAGE"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(6))+"\" maxlength=\"5\" class=\"txt_input1\" ></td>");
                  out.println("<td ><input name=\"NETAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(3))+" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  out.println("<td ><input name=\"GROSS"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(4))+" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  out.println("<td ><input name=\"PRACENT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(5))+" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  out.println("</tr>");
                  sum_rate=sum_rate+(rs.getDouble(2));
									sum_rent=sum_rent+(rs.getDouble(3));
									sum_p_re=sum_p_re+(rs.getDouble(5));
									j=j+1;
              }
          //}
          out.println("<tr class=tr_input><td  ></td>");
          //out.println("<td  width='20%' ></td>");
          out.println("<td><input type=hidden name=hid_count value="+j+">"+nf.format(sum_rate)+"</td>");
          out.println("<td></td>");
          out.println("<td align=right>"+nf.format(sum_rent)+"</td>");
          out.println("<td></td>");
          out.println("<td align=right>"+nf.format(sum_p_re)+"</td>");
          //out.println("<td></td>");
          out.println("</tr></table>");
          
           
   	
			}
			else if(m_chksql.trim().equals("chkCity")){
			 rs = stmt.executeQuery("SELECT A.CITY_CODE, A.CITY_DESC, B.DESCRIPTION "+
															 "FROM   "+m_schema_name+".AF_CO_MAS_CITY A,"+
															 "       "+m_schema_name+".AF_CO_MAS_POSTAL_CODES B"+
															 "WHERE  A.CITY_CODE = B.CITY_CODE AND A.ACTIVE_STATUS='A' AND "+
                               "       B.ACTIVE_STATUS='A'");
				boolean more = rs.next();
				if(more){
				   out.println(rs.getString(1)+"@#"+rs.getString(2)+"@#"+rs.getString(3)+"@##@");
				}	    
						
			}
			else if(m_chksql.trim().equals("loadMaintenance")){
			
			  String clientName = req.getParameter("clientName");
				
			  rs = stmt.executeQuery("SELECT A.CITY_CODE, A.CITY_DESC, B.DESCRIPTION "+
															 "FROM   "+m_schema_name+".AF_CO_MAS_CITY A,"+
															 "       "+m_schema_name+".AF_CO_MAS_POSTAL_CODES B"+
															 "WHERE  A.CITY_CODE = B.CITY_CODE AND A.ACTIVE_STATUS='A' AND "+
                               "       B.ACTIVE_STATUS='A'");
				boolean more = rs.next();
				if(more){
				   out.println(rs.getString(1)+"@#"+rs.getString(2)+"@#"+rs.getString(3)+"@##@");
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
		                            "       ACTIVE_STATUS='A'");
				boolean more = rs.next();
				if(more){
				   out.println(rs.getString(1)+"@#"+rs.getString(2)+"@#"+rs.getString(3)+"@#"+rs.getString(4)
						           +"@#"+rs.getString(5)+"@#"+rs.getString(6)+"@##@");
				}	    
						
			}
			
	      /*out.println("<HTML>");
				out.println("<head>");
				out.println("<TITLE>DealNet</TITLE>");
				out.println("</head>");
				out.println("<link href=\""+m_html_client_url+"/back_scr_styles.css\" rel=\"stylesheet\" type=\"text/css\">");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function display_data(){");
				out.println("");
				out.println("");
				out.println("");
				out.println("");
				out.println("}");
				out.println("</SCRIPT>");
				
				out.println("<BODY leftmargin='0' topmargin='0'>");
				out.println("<FORM NAME='Form1'>");
				out.println("<TABLE WIDTH=100% class='.FORM_TAB'>");
				out.println("<tr >");
				out.println("<td width=20% class=.FORM_TEXT>Initiation Type</td>");
				out.println("<td><SELECT name=InitiationType class=.FORM_TEXT>");
				out.println("<OPTION value=\"\">Please select</option>");
				rs = stmt.executeQuery(" SELECT INITIATION_CODE Initiation,DESCRIPTION \"Initiation Name\" "+
                           " FROM   "+m_schema_name+".L_MK_MAS_INITIATION_TYPE "+
													 " WHERE  ACTIVE_STATUS ='Y' AND INITIATION_CODE LIKE '%%' ");//CO_methods.getInitiationType(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
           out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				
				out.println("</SELECT></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=.FORM_TEXT1>Customer Category</td>");
				out.println("<td><SELECT name=CusCat >");
				out.println("<OPTION value=\"\">Please select</option>");
				rs = stmt.executeQuery(" SELECT CAT_TYPE_CODE Category,DESCRIPTION \"Category Name\" "+
                           " FROM   "+m_schema_name+".L_MK_MAS_CUSTOMER_CATOGORY "+
													 " WHERE  ACTIVE_STATUS ='Y' AND CAT_TYPE_CODE LIKE '%%' ");//CO_methods.getCustomerCat(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
           out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>Customer Type</td>");
				out.println("<td><SELECT name=CusType>");
				out.println("<OPTION value=\"\">Please select</option>");
				rs = stmt.executeQuery(" SELECT ENTITY_CODE Entity,DESCRIPTION \"Entity Name\" "+
													 " FROM "+m_schema_name+".L_CO_MAS_LEGAL_ENTITY "+
													 " WHERE  ACTIVE_STATUS ='Y' AND ENTITY_CODE LIKE '%%' ");//CO_methods.getCustomerType(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
           out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT>");
				
				
				
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>Lead Source Category</td>");
				//out.println("<td><input type=Button name=But_Go Value=Go onclick=\"display_date();\"></td>");
				out.println("</tr>");
				out.println("</TABLE>");
				out.println("</form>");
				out.println("</body>");
				out.println("</HTML>");
				*/
			/*	function makeRequest(url) {

        var http_request = false;

        if (window.XMLHttpRequest) { // Mozilla, Safari,...
            http_request = new XMLHttpRequest();
            if (http_request.overrideMimeType) {
                http_request.overrideMimeType('text/xml');
                // See note below about this line
            }
        } else if (window.ActiveXObject) { // IE
            try {
                http_request = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (e) {
                try {
                    http_request = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (e) {}
            }
        }

        if (!http_request) {
            alert('Giving up :( Cannot create an XMLHTTP instance');
            return false;
        }
        http_request.onreadystatechange = function() { alertContents(http_request); };
        http_request.open('GET', url, true);
        http_request.send(null);

    }

    function alertContents(http_request) {

        if (http_request.readyState == 4) {
            if (http_request.status == 200) {
                alert(http_request.responseText);
            } else {
                alert('There was a problem with the request.');
            }
        }

    }

				
				out.println("function display_data(){");
				out.println("");
				out.println("");
				out.println("");
				out.println("");
				out.println("}");
				out.println("</SCRIPT>");
				
				out.println("<BODY leftmargin='0' topmargin='0'>");
				out.println("<FORM NAME='Form1'>");
				out.println("<TABLE BORDER='0' WIDTH='0' ></TABLE>");
				out.println("<tr>");
				out.println("<td>Enter Table Name</td>");
				out.println("<td><input type=text name=Tab_Name></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>Enter Saving Servlet Name</td>");
				out.println("<td><input type=text name=Ser_Name></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>Enter Procedure Name</td>");
				out.println("<td><input type=text name=Pro_Name></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>Enter Procedure Name</td>");
				out.println("<td><input type=Button name=But_Go Value=Go onclick=\"display_date();\"></td>");
				out.println("</tr>");
				out.println("</form>");
				out.println("</body>");
				out.println("</HTML>");
				
				
	    }		
			
			else if(m_chksql.trim().equals("get_pmt_value_working")){
                    String m_rate		 = req.getParameter("rate");
                    String m_value   = req.getParameter("value");
                    String m_terms	 = req.getParameter("terms");
                    String m_freq		 = req.getParameter("freq");
                    String m_type    = req.getParameter("type");
										String m_vat_per = req.getParameter("tax");
                    
                    
                    rs = stmt.executeQuery ("SELECT "+m_rate+","+m_rate+"/100,("+m_rate+"/100)/"+m_freq+","+m_freq+","+m_terms+","+m_value+","+
										                        "        LAKDL.L_CO_CAL_FACTOR("+m_rate+","+m_freq+","+m_terms+",'"+m_type+"'),"+m_vat_per+" FROM DUAL");
																						
                    /*
										out.println("<html><head>");
                    out.println("<title>PMT Value - Formulation</title></head>");
                    out.println("<body bgcolor='white'>");
                    out.println("<form name='Form1'>");
                    out.println("<br>");
                    
                    int 	 mm_freq					  = 0;
                    int 	 mm_terms						= 0;
                    double mm_value						= 0;
                    double mm_rate_per_month  = 0;
										double mm_tot_fact 				= 0;
										double mm_vat_per 				= 0;
                    if(rs.next()){
                        
                        mm_rate_per_month = rs.getDouble(3);
                        mm_value					= rs.getDouble(6);
                        mm_terms					= rs.getInt(5);
                        mm_freq						= rs.getInt(4);
												mm_tot_fact				= rs.getDouble(7);
												mm_vat_per				= rs.getDouble(8);
                        
                        out.println("<table border='0' width='100%' >");
                        out.println("<tr><td  width='40%' >Total Factor</td>");
                        out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(7))+"</td></tr>");
                        /*out.println("<tr><td  width='40%' >Rate (%)</td>");
                        out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(1))+"</td></tr>");
                        out.println("<tr><td  width='40%' >Rate </td>");
                        out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(2))+"</td></tr>");
                        out.println("<tr><td  width='40%' >Rate per Month</td>");
                        out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(3))+"</td></tr>");
                        out.println("<tr><td  width='40%' >Payment Frequency</td>");
                        out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(4))+"</td></tr>");
                        out.println("<tr><td  width='40%' >No of Installaments</td>");
                        out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(5))+"</td></tr>");
                        
												out.println("</table>");
												
                    }
                    
                    out.println("<br>");
                    
                    out.println("<table border='1' width='100%' >");
                    out.println("<tr><td  width='20%' >Installment No(Y)</td>");
                    //out.println("<td  width='20%' >Rate per Month(RM)</td>");
                    //out.println("<td  width='30%' >(RM+1)=X</td>");
                    out.println("<td  width='30%' >Factor</td>");
										out.println("<td  width='30%' >Gross Rental</td>");
										out.println("<td  width='30%' >Net Rental</td>");
										out.println("<td  width='30%' >Pracent Value</td>");
										
										
                    out.println("</tr>");
                    double sum_rate=0;
                    double sum_rent=0;
										double sum_p_re=0;
										int m_start=0;
										int m_end  =Integer.parseInt(m_terms);
										
										if(m_type.equals("ARREASE")){
										  m_start=1;
											m_end  =Integer.parseInt(m_terms)+1;
										}
										
                    for(int j=m_start;j<m_end;j++){
                        //out.println("J="+j);
                        out.println("<tr><td  width='20%' >"+j+"</td>");
                        //out.println("<td  width='20%' >"+nf.format(mm_rate_per_month)+"</td>");
                        rs = stmt.executeQuery ("SELECT "+(mm_rate_per_month+1)+",1/POWER("+(mm_rate_per_month+1)+","+j+"),"+
												                                ""+m_value+"/"+mm_tot_fact+","+m_value+"/"+mm_tot_fact+"+("+m_value+"/"+mm_tot_fact+")*"+mm_vat_per+","+
																												""+m_value+"/"+mm_tot_fact+"/1/POWER("+(mm_rate_per_month+1)+","+j+") "+
																								"FROM   DUAL ");
                        if(rs.next()){
                            //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                            out.println("<td  width='20%' >"+nf.format(rs.getDouble(2))+"</td>");
                            out.println("<td  width='20%' >"+nf.format(rs.getDouble(3))+"</td>");
                            out.println("<td  width='20%' >"+nf.format(rs.getDouble(4))+"</td>");
                            out.println("<td  width='20%' "+nf.format(rs.getDouble(5))+"</td>");
                            out.println("</tr>");
                            sum_rate=sum_rate+(rs.getDouble(2));
														sum_rent=sum_rent+(rs.getDouble(3));
														sum_p_re=sum_p_re+(rs.getDouble(5));
                        }
                    }
                    out.println("<tr><td  width='20%' ></td>");
                    //out.println("<td  width='20%' ></td>");
                    out.println("<td  >"+nf.format(sum_rate)+"</td>");
                    out.println("<td  width='30%' >"+nf.format(sum_rent)+"</td>");
                    out.println("<td  width='30%' >"+nf.format(sum_rent)+"</td>");
                    out.println("<td  width='30%' >"+nf.format(sum_p_re)+"</td>");
                    out.println("</tr></table>");
                    
                    /*out.println("<br>");
                    out.println("<table border='0' width='100%' >");
                    out.println("<tr><td  width='40%' ></td>");
                    out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>PMT=(Future Value/Sum of Interest Rates)</td></tr>");
                    out.println("<tr><td  width='40%' ></td>");
                    out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'></td></tr>");
                    out.println("<tr><td  width='40%' style='{ text-align:left; color: black;  font: bold 10pt arial;}'>PMT Value</td>");
                    out.println("<td width='60%' style='{text-align:left; font: bold 10pt arial; }'>"+nf.format(mm_value/sum_rate)+"</td></tr>");
                    out.println("</table>");
                    //out.println("</form></body></html>");
                }

		else if(m_chksql.trim().equals("get_Method")){
                    String m_meth		 = req.getParameter("Meth");
                    String m_value   = req.getParameter("value");
                    String m_value1	 = req.getParameter("value1");
                    String m_value2	 = req.getParameter("value2");
                    String m_value3  = req.getParameter("value3");
										          
                    String m_me = "";
										if(m_value2!=null){
										  m_me = "CO_methods."+m_meth+"("+conn+",OFSCL,"+m_value+","+m_value1+","+m_value2+")";										
										}else if(m_value3!=null){
										  m_me = "CO_methods."+m_meth+"("+conn+",OFSCL,"+m_value+","+m_value1+","+m_value2+","+m_value3+")";										
										}else{
										  m_me = "CO_methods."+m_meth+"("+conn+",OFSCL,"+m_value+","+m_value1+")";										
										}
										out.println("m_me="+m_me);
                    String sql	= m_me;//CO_methods.getCustomerCat(conn,"OFSCL",m_value,m_value1);
										rs = stmt.executeQuery(sql);
										//return rs;

										boolean more = rs.next();
										while(more){
										 vec.addElement(rs.getString(1));//out.println("rs.getstring(1)="+rs.getString(1));
											more = rs.next();
										}
										//return vec;
                   /*
										out.println("<html><head>");
                    out.println("<title>PMT Value - Formulation</title></head>");
                    out.println("<body bgcolor='white'>");
                    out.println("<form name='Form1'>");
                    out.println("<br>");
                    
                    int 	 mm_freq					  = 0;
                    int 	 mm_terms						= 0;
                    double mm_value						= 0;
                    double mm_rate_per_month  = 0;
										double mm_tot_fact 				= 0;
										double mm_vat_per 				= 0;
                    if(rs.next()){
                        
                        mm_rate_per_month = rs.getDouble(3);
                        mm_value					= rs.getDouble(6);
                        mm_terms					= rs.getInt(5);
                        mm_freq						= rs.getInt(4);
												mm_tot_fact				= rs.getDouble(7);
												mm_vat_per				= rs.getDouble(8);
                        
                        out.println("<table border='0' width='100%' >");
                        out.println("<tr><td  width='40%' >Total Factor</td>");
                        out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(7))+"</td></tr>");
                        out.println("</table>");
												
                    }
                    
                    out.println("<br>");
                    
                    out.println("<table border='1' width='100%' >");
                    out.println("<tr><td  width='20%' >Installment No(Y)</td>");
                    out.println("<td  width='30%' Factor</td>");
										out.println("<td  width='30%' >Gross Rental</td>");
										out.println("<td  width='30%' >Net Rental</td>");
										out.println("<td  width='30%' >Pracent Value</td>");
										
										
                    out.println("</tr>");
                    double sum_rate=0;
                    double sum_rent=0;
										double sum_p_re=0;
										int m_start=0;
										int m_end  =Integer.parseInt(m_terms);
										
										if(m_type.equals("ARREASE")){
										  m_start=1;
											m_end  =Integer.parseInt(m_terms)+1;
										}
										
                    for(int j=m_start;j<m_end;j++){
                        //out.println("J="+j);
                        out.println("<tr><td  width='20%' >"+j+"</td>");
                        //out.println("<td  width='20%' >"+nf.format(mm_rate_per_month)+"</td>");
                        rs = stmt.executeQuery ("SELECT "+(mm_rate_per_month+1)+",1/POWER("+(mm_rate_per_month+1)+","+j+"),"+
												                                ""+m_value+"/"+mm_tot_fact+","+m_value+"/"+mm_tot_fact+"+("+m_value+"/"+mm_tot_fact+")*"+mm_vat_per+","+
																												""+m_value+"/"+mm_tot_fact+"/1/POWER("+(mm_rate_per_month+1)+","+j+") "+
																								"FROM   DUAL ");
                        if(rs.next()){
                            //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                            out.println("<td  width='20%' >"+nf.format(rs.getDouble(2))+"</td>");
                            out.println("<td  width='20%' >"+nf.format(rs.getDouble(3))+"</td>");
                            out.println("<td  width='20%' >"+nf.format(rs.getDouble(4))+"</td>");
                            out.println("<td  width='20%' >"+nf.format(rs.getDouble(5))+"</td>");
                            out.println("</tr>");
                            sum_rate=sum_rate+(rs.getDouble(2));
														sum_rent=sum_rent+(rs.getDouble(3));
														sum_p_re=sum_p_re+(rs.getDouble(5));
                        }
                    }
                    out.println("<tr><td  width='20%' ></td>");
                    //out.println("<td  width='20%' ></td>");
                    out.println("<td  >"+nf.format(sum_rate)+"</td>");
                    out.println("<td  width='30%' >"+nf.format(sum_rent)+"</td>");
                    out.println("<td  width='30%' >"+nf.format(sum_rent)+"</td>");
                    out.println("<td  width='30%' >"+nf.format(sum_p_re)+"</td>");
                    out.println("</tr></table>");
                    
                    /*out.println("<br>");
                    out.println("<table border='0' width='100%' >");
                    out.println("<tr><td  width='40%' ></td>");
                    out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>PMT=(Future Value/Sum of Interest Rates)</td></tr>");
                    out.println("<tr><td  width='40%' ></td>");
                    out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'></td></tr>");
                    out.println("<tr><td  width='40%' style='{ text-align:left; color: black;  font: bold 10pt arial;}'>PMT Value</td>");
                    out.println("<td width='60%' style='{text-align:left; font: bold 10pt arial; }'>"+nf.format(mm_value/sum_rate)+"</td></tr>");
                    out.println("</table>");*/
                    //out.println("</form></body></html>");
              //  }	
		/*	
			else if (m_chksql.trim().equals("dis_data")) {
			  String m_tab_name = req.getParameter("Tab_Name");
				String m_string="";
				int i =0;
				rs = stmt.executeQuery (" SELECT A.COLUMN_NAME, A.DATA_TYPE, A.DATA_LENGTH,A.NULLABLE, "+
				                        "        A.COLUMN_ID,A.DEFAULT_LENGTH, A.DATA_DEFAULT "+
																" FROM SYS.ALL_TAB_COLUMNS A "+
                                " WHERE TABLE_NAME='ALL_APPLY_DML_HANDLERS' AND OWNER='SYS' ");
        boolean more=rs.next(); 																
				if(more){
				  m_string ="<TABLE BORDER='0' WIDTH='100%' BGCOLOR='white' STYLE='{font: 9pt arial;}'>"+
					          "<TR BGCOLOR='white'>"+
				            "<TD  WIDTH='10%'>COLUMN_NAME</TD>"+
										"<TD  WIDTH='10%'>Type</TD>"+
										"<TD  WIDTH='10%'>DATA_TYPE</TD>"+
										"<TD  WIDTH='8% '>DATA_LENGTH</TD>"+
										"<TD  WIDTH='8% '>NULLABLE</TD>"+
										"<TD  WIDTH='10%'>COLUMN_ID</TD>"+
										"<TD  WIDTH='10%'>DEFAULT_LENGTH</TD>"+
										"<TD  WIDTH='10%'>DATA_DEFAULT</TD></tr>";
					
				  while(more){
					          m_string = m_string +"<TR BGCOLOR='white'>"+
				            "<TD  WIDTH='10%'><input type=text name=col_name"+i+" value="+rs.getString(1)+" size=30></TD>"+
										"<TD  WIDTH='10%'><SELECT name=typ_name"+i+" ><option value=text>Text<option value=textButton>Text With Button<option value=Select>Select<option value=checkbox>Check Box<option value=hidden>Hidden</select></TD>"+
										"<TD  WIDTH='10%'><input type=text name=dat_name"+i+" value="+rs.getString(2)+"  size=10></TD>"+
										"<TD  WIDTH='8% '><input type=text name=len_name"+i+" value="+rs.getString(3)+"  size=5></TD>"+
										"<TD  WIDTH='8% '><input type=text name=nul_name"+i+" value="+rs.getString(4)+"  size=5></TD>"+
										"<TD  WIDTH='10%'><input type=text name=cid_name"+i+" value="+rs.getString(5)+"  size=5></TD>"+
										"<TD  WIDTH='10%'><input type=text name=dle_name"+i+" value="+rs.getString(6)+"  size=10></TD>"+
										"<TD  WIDTH='10%'><input type=text name=def_name"+i+" value="+rs.getString(7)+"  size=10></TD></tr>";
										more = rs.next();
										i=i+1;
					}					
					m_string=m_string+"<input type=hidden name=hid_col_count value="+i+"></table>";
					out.println(m_string);
				}
			}
		/*	else if (m_chksql.trim().equals("mrep_data")) {
	
	/// Scrolling Heading part done by Samanthi Ranasinghe on 09/04/2001

			  //double sum_leg1=0;
				//double sum_leg2=0;
				//double leg1_yield=0;
				//double sum_leg1_yield=0;
				String m_mdate_from = req.getParameter("mdate_from");
				String m_mdate_to = req.getParameter("mdate_to");
				//String m_client= req.getParameter("client");

				
				out.println("<head><TITLE>DealNet</TITLE></head><HTML><BODY leftmargin='0' topmargin='0'><FORM NAME='Form1'><TABLE BORDER='0' WIDTH='0' ></TABLE>");
				out.println("<TABLE BORDER='0' WIDTH='2800' ><B></TABLE>");
				
				rs = stmt.executeQuery ("SELECT  TO_CHAR(PROCESS_DATE,'DD-MON-YYYY'),NVL(REPO_YIELD,0),NVL(REPO_AMOUNT,0),NVL(REPO_INTR,0),NVL(REVERSE_REPO_YIELD,0),NVL(REVERSE_REPO_AMOUNT,0),NVL(REVERSE_REPO_INTR ,0) , "+
				                       " NVL(TBOND_FACE_VALUE,0), NVL(TBOND_PRICE_ACCR,0),NVL(TBOND_COUPON_ACCR,0), NVL(TBILL_FACE_VALUE,0), NVL(TBILL_PRICE_ACCR,0),NVL(TBILL_YIELD,0),NVL(TBOND_YIELD,0),NVL(BOND_INI_COST,0),NVL(BILL_INI_COST,0)  "+
																" FROM "+m_m_schema_name+".DN_REF_YIELD_FLUCTUATION  "+
				                        " WHERE  TO_DATE(TO_CHAR(PROCESS_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('"+m_mdate_to+"','DD-MM-YYYY') AND "+
																" TO_DATE(TO_CHAR(PROCESS_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>= TO_DATE('"+m_mdate_from+"','DD-MM-YYYY') ORDER BY  PROCESS_DATE  ASC ");
				
				
				//DIV TAG FOR   Scrolling Rows DATA AREA TABLE
				out.println("<DIV STYLE='{position:absolute; top:40; left:0 cursor: hand;}'>");
				out.println("<TABLE BORDER='1' WIDTH='2800' BGCOLOR='white' STYLE='{font: 9pt arial;}'>");
					
				boolean more = rs.next(); 
				while (more) {
					//sum_leg1=sum_leg1+rs.getDouble(11);
					//sum_leg2=sum_leg2+rs.getDouble(12);
					//leg1_yield=rs.getDouble(10) * rs.getDouble(11);
					//sum_leg1_yield=sum_leg1_yield+leg1_yield;
					
			    out.println("<TR BGCOLOR='white'>");
				  out.println("<TD  WIDTH='100' STYLE='{font: 9pt arial; text-align:center;}'>"+rs.getString(1)+"</TD>");
					out.println("<TD  WIDTH='150' STYLE='{font: 9pt arial; text-align:right;}'>"+rs.getString(2)+"</TD>");
					out.println("<TD  WIDTH='150' STYLE='{font: 9pt arial; text-align:right;}'>"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("<TD  WIDTH='150' STYLE='{font: 9pt arial; text-align:right;}'>"+nf.format(rs.getDouble(4))+"</TD>");
					out.println("<TD  WIDTH='150' STYLE='{font: 9pt arial; text-align:right;}'>"+rs.getString(5)+"</TD>");
					out.println("<TD  WIDTH='150' STYLE='{font: 9pt arial; text-align:right;}'>"+nf.format(rs.getDouble(6))+"</TD>");
					out.println("<TD  WIDTH='150' STYLE='{font: 9pt arial; text-align:right;}'>"+nf.format(rs.getDouble(7))+"</TD>");
					out.println("<TD  WIDTH='150' STYLE='{font: 9pt arial; text-align:right;}'>"+nf.format(rs.getDouble(8))+"</TD>");
					out.println("<TD  WIDTH='150' STYLE='{font: 9pt arial; text-align:right;}'>"+nf.format(rs.getDouble(9))+"</TD>");
					//out.println("<TD  WIDTH='150' STYLE='{font: 9pt arial; text-align:right;}'>"+nf.format(rs.getDouble(13))+"</TD>");
					//out.println("<TD  WIDTH='150' STYLE='{font: 9pt arial; text-align:right;}'>"+nf.format(rs.getDouble(10))+"</TD>");
					out.println("<TD  WIDTH='150' STYLE='{font: 9pt arial; text-align:right;}'>"+nf.format(rs.getDouble(10))+"</TD>");
					out.println("<TD  WIDTH='150' STYLE='{font: 9pt arial; text-align:right;}'>"+nf.format(rs.getDouble(13))+"</TD>");
					out.println("<TD  WIDTH='150' STYLE='{font: 9pt arial; text-align:right;}'>"+nf.format(rs.getDouble(15))+"</TD>");
					out.println("<TD  WIDTH='150' STYLE='{font: 9pt arial; text-align:right;}'>"+nf.format(rs.getDouble(11))+"</TD>");
					out.println("<TD  WIDTH='150' STYLE='{font: 9pt arial; text-align:right;}'>"+nf.format(rs.getDouble(12))+"</TD>");
					out.println("<TD  WIDTH='150' STYLE='{font: 9pt arial; text-align:right;}'>"+nf.format(rs.getDouble(14))+"</TD>");
					out.println("<TD  WIDTH='150' STYLE='{font: 9pt arial; text-align:right;}'>"+nf.format(rs.getDouble(16))+"</TD>");
					
					out.println("</TR>");
					more = rs.next();	
				}
			 		out.println("</TABLE>");
				  out.println("</DIV>");

				//DIV TAG FOR Scrolling Rows (LEFT & RIGHT) AREA TABLE
			 	out.println("<DIV STYLE='position: absolute; top: 0; left: 0; width: 2800; height: 34'></DIV>");
				
				//DIV TAG FOR Scrolling Columns (UP & DOWN ) AREA TABLE
			  out.println("<DIV STYLE='position: absolute; top: 0; left: 3; width: 2800 ; height: 34'>");

			//	out.println("<TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD>");
			//	out.println("<TD STYLE='{font: bold 9pt arial; text-align:center;}'><B><FONT COLOR='black' >Total</TD>");
  		//	out.println("<TD STYLE='{font: bold 9pt arial; text-align:right;}'>"+nf.format(sum_leg1)+"</TD>");
			//	out.println("<TD STYLE='{font: bold 9pt arial; text-align:right;}'>"+nf.format(sum_leg2)+"</TD></TR>");
				
			//	out.println("<TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD>");
			//	out.println("<TD STYLE='{font: bold 9pt arial; text-align:center;}'><B><FONT COLOR='black' >W. Average - Yield</TD>");
  		//	out.println("<TD STYLE='{font: bold 9pt arial; text-align:right;}'>"+nf.format(sum_leg1_yield/sum_leg2)+"</TD></TR>");
        out.println("<TABLE BORDER='0' WIDTH='2800' ><B>");
        out.println("<TD WIDTH='2800' STYLE='{text-align:center;}'><B><FONT COLOR='black'><U>YIELD  FLUCTUATIONS  FROM  "+m_mdate_from +"  TO   "+m_mdate_to+"</U></TD></B></FONT></TABLE>");
        //out.println("<TABLE WIDTH='450' BORDER='0' CELLPADDING='0' CELLSPACING='2'></TABLE>");
        //out.println("<TABLE WIDTH='450' BORDER='0' CELLPADDING='0' CELLSPACING='2'><BR></TABLE>");
				out.println("<TABLE BORDER='1' WIDTH='2800' BGCOLOR='white' STYLE='{font: 9pt arial;}'><B>");
				
				out.println("<TR BGCOLOR='silver' >");
				out.println("<TD WIDTH='100' STYLE='{text-align:center;}'><B><FONT COLOR='black' >Date</TD>");
				out.println("<TD WIDTH='150' STYLE='{text-align:center;}'><B><FONT COLOR='black'>Repo Yield</TD>");
				out.println("<TD WIDTH='150' STYLE='{text-align:center;}'><B><FONT COLOR='black'>Repo Amount</TD>");
				out.println("<TD WIDTH='150' STYLE='{text-align:center;}'><B><FONT COLOR='black'>Repo Interest</TD>");
				
				out.println("<TD WIDTH='150' STYLE='{text-align:center;}'><B><FONT COLOR='black'>Reverse Repo Yield</TD>");
				out.println("<TD WIDTH='150' STYLE='{text-align:center;}'><B><FONT COLOR='black'>Reverse Repo Amount</TD>");
				out.println("<TD WIDTH='150' STYLE='{text-align:center;}'><B><FONT COLOR='black'>Reverse Repo Interest</TD>");
				
				
				out.println("<TD WIDTH='150' STYLE='{text-align:center;}'><B><FONT COLOR='black'>Tbond Face Value</TD>");
				out.println("<TD WIDTH='150' STYLE='{text-align:center;}'><B><FONT COLOR='black'>Tbond Price IntAccr</TD>");
				out.println("<TD WIDTH='150' STYLE='{text-align:center;}'><B><FONT COLOR='black'>Tbond Coupon IntAccr</TD>");
				out.println("<TD WIDTH='150' STYLE='{text-align:center;}'><B><FONT COLOR='black'>Tbond Yield</TD>");
				out.println("<TD WIDTH='150' STYLE='{text-align:center;}'><B><FONT COLOR='black'>Tbond Initial Cost</TD>");
				
				
				out.println("<TD WIDTH='150' STYLE='{text-align:center;}'><B><FONT COLOR='black'>Tbill Face Value</TD>");
				out.println("<TD WIDTH='150' STYLE='{text-align:center;}'><B><FONT COLOR='black'>Tbill Price IntAccr</TD>");
				out.println("<TD WIDTH='150' STYLE='{text-align:center;}'><B><FONT COLOR='black'>Tbill Yield</TD>");
				out.println("<TD WIDTH='150' STYLE='{text-align:center;}'><B><FONT COLOR='black'>Tbill Initial Cost</TD>");
				
				
				out.println("</B></TABLE></div>");
				
				// DIV TAG FOR Scrolling Header (UP - DOWN  & LEFT - RIGHT) AREA TABLE
				out.println("<DIV STYLE='position: absolute; top: 0; left: 0; width: 2800; height: 34'></DIV>");
				
				//javascript file(float_1.js) takes the DIV tags found in this HTML and
				//does the appropriate positionning & moving.
			  out.println("<SCRIPT language1.2='JavaScript' src='http://www.fcam.lk/intranet/float_1.js'></SCRIPT>");
			  out.println("</BODY></HTML>");

			}
		*/	
			
			//=========================================================================================================================			
  		/*else {
				out.println("Undefined");
			}
			*/
      //out.close();
			//conn.close();
			//this.destroy();
			
			
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
