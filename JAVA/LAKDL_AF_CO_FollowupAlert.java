//Option Id is 6.0  
//This File was created by SVA on 13-07-2006 
//FoolowUp Alert Display
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;

import oracle.jdbc.driver.*;

public class LAKDL_AF_CO_FollowupAlert extends javax.servlet.http.HttpServlet {
    
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
            String m_username 						= con_method.username;
            out = res.getOutputStream();
            String header_name    = con_method.header_name;
            //out.println("conn="+conn);
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
            CallableStatement callstmt1 =null;
            
            
            //************************************************************
            
            nf = java.text.NumberFormat.getInstance(Locale.US);   
            nf.setMinimumFractionDigits(0);
            
            nf1 = java.text.NumberFormat.getInstance(Locale.US);   
            nf1.setMinimumFractionDigits(4);
            
            res.setStatus(HttpServletResponse.SC_OK);
            res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
     		res.setDateHeader("Expires", 0);
            
            m_chksql = req.getParameter("chksql");
            stmt = conn.createStatement ();
            if (m_chksql.trim().equals("idle")) {
                out.println("idle");
            }
            else if(m_chksql.trim().equals("main_page")){
                
                String m_cash_out   = req.getParameter("cashout");
                String m_trn_sub    = req.getParameter("trnsub");
                String m_int_type   = req.getParameter("nittype");
                String m_trn_type   = req.getParameter("trn_type");
                String m_option     = req.getParameter("option");
                
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Asset Financing System</title>    ");
                out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
                out.println("</head>");
                out.println("<Script>");
                out.println("var m_bsubmit = '0';");
                out.println("var arr_assign= new Array();");
                out.println("var m_send_val= '';");
                
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
                //out.println("alert('opt='+opt+'--opt1='+opt1); ");
                //http_request.open('GET', "http://localhost:/myserver/servlet/CreateFileFormat?chksql=get_pmt_value_working&rate=18&value=2500000&terms=48&freq=12&type=ADDVANCE&tax=.1", true);
                
                out.println(" if(opt1=='YES'){");
                //window.open("http://www.ofscl-leasing.lk:/myserver/servlet/LAKDL_AF_MK_Price?"+m_send_val);
                //out.println("  window.open(url+'?'+m_send_val);");
                out.println("  http_request.onreadystatechange = function() { alertContents(http_request); };");
                out.println("  http_request.open('POST',url, true);");
                out.println("	 http_request.setRequestHeader(\"Content-Type\",\"application/x-www-form-urlencoded\");");
                //out.println("  alert(construct_URL(\"get_advance_price_cal\",opt));");
                //out.println("  window.open(url+'?'+construct_URL(\"get_advance_price_cal\",opt));");
                out.println("	 m_fact_val = \"\";"); 
                out.println("	 m_prac_val = \"\";"); 
                out.println("  m_percentage = \"\";"); 
                out.println("  m_char_val = \"0\";"); 
                out.println("  m_main_val = \"0\";"); 
                out.println("  m_mainten  = \"\";"); 
                out.println("  m_cash_out = \"\";"); 
                out.println("  m_cashOAmo = \"0\";"); 
                
                out.println("  for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
                out.println("    m_send_val   = m_send_val+unformat_noobject(document.Form1.elements[\"NETAMT\"+i].value)+\"@\";");
                out.println("    m_fact_val   = m_fact_val+unformat_noobject(document.Form1.elements[\"FACTOR\"+i].value)+\"@\";");
                out.println("    m_prac_val   = m_prac_val+unformat_noobject(document.Form1.elements[\"PRACENT\"+i].value)+\"@\";");
                out.println("    m_cash_out   = m_cash_out+unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value)+\"@\";");
                out.println("    m_cashOAmo   = parseFloat(m_cashOAmo)+parseFloat(unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value));");
                
                out.println("    m_percentage = m_percentage+unformat_noobject(document.Form1.elements[\"PERCENTAGE\"+i].value)+\"@\";");
                //out.println("    alert('m_percentage'+m_percentage);");
                out.println("	 }");
                out.println("	 document.Form1.CASH_OUTFLOW.value=m_cash_out;");
                out.println("	 window.opener.document.Form1.CASH_OUTFLOW.value=m_cash_out;");
                //out.println("	 alert(m_cashOAmo+'!='+document.Form1.GROSS_AMOUNT.value);");
                out.println("	 if(parseFloat(m_cashOAmo)!=parseFloat(document.Form1.GROSS_AMOUNT.value)){");
                out.println("	   alert('Please Check the Cash Outflow Amounts.');");
                out.println("	   return false;");
                out.println("	 }");
                
                out.println("	 m_send_val = \"chksql=get_outflow_recal&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+");
                out.println("	              \"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&option=\"+");
                out.println("	              \"&last_rent=&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+");
                out.println("	              \"&supcrper=0&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+unformat_noobject(document.Form1.NIBSM.value)+");
                out.println("	              \"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&installments=\";");
                
                out.println("	 if(document.Form1.INTEREST_TYPE.value=='VARIABLE'){");
                out.println("	   m_send_val = m_send_val+\"&nitbasemar=\"+document.Form1.INTEREST_BASE.value+\"&nitmar=\"+document.Form1.INTEREST_MARGIN.value+\"\";");						
                out.println("	 }else{");
                out.println("	   m_send_val = m_send_val+\"&nitbasemar=&nitmar=\";");						
                out.println("	 }");
                
                //out.println("    alert('m_percentage'+m_percentage);");
                //out.println("	 }");
                //out.println("  for(i=0;i<parseFloat(document.Form1.c_c_count.value);i++){");
                //out.println("    m_char_val   = parseFloat(m_char_val)+parseFloat(unformat_noobject(document.Form1.elements[\"CHARGE_\"+i].value));");
                //out.println("	 }");
                
                //out.println("  for(j=0;j<=parseFloat(document.Form1.hid_y_count.value);j++){");
                //out.println("      m_main_val   =0;");
                //out.println("    for(i=0;i<parseFloat(document.Form1.hid_m_count.value);i++){");
                //out.println("      m_main_val   = parseFloat(m_main_val)+parseFloat(unformat_noobject(document.Form1.elements[\"MAINTANENCE_\"+i+\"_\"+j].value));");
                //out.println("	   }");
                //out.println("    m_mainten   = m_mainten+m_main_val+\"@\";");
                //out.println("	 }");
                
                out.println("    m_send_val = m_send_val+\"&maintan=\"+document.Form1.MAINTANENCE.value+\"&factor=\"+m_fact_val+\"&pracent=\"+m_prac_val+\"&ami=\"+document.Form1.AMI.value+\"&other_cha=\"+document.Form1.CHARGE.value+\"&percentage=\"+m_percentage;");
                out.println("  window.open(url+'?'+m_send_val);");
                out.println("  http_request.send(m_send_val);");
                out.println(" }else{");
                
                out.println("  http_request.onreadystatechange = function() { alertGetContents(http_request,opt); };");
                out.println("  http_request.open('GET',url, true);");
                //out.println("  window.open(url);");
                //out.println("  alert('opt-'+opt);");
                out.println("  http_request.send(null);");
                out.println(" }");
                out.println("}");
                
                
                out.println("function construct_URL(chk_sql,opt) {");
                out.println("	 m_send_val = \"chksql=\"+chk_sql+\"&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+");
                out.println("	              \"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&option=\"+opt+");
                out.println("	              \"&last_rent=&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+");
                out.println("	              \"&supcrper=0&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+unformat_noobject(document.Form1.NIBSM.value)+");
                out.println("	              \"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&installments=\";");
                
                out.println("	 if(document.Form1.INTEREST_TYPE.value=='VARIABLE'){");
                out.println("	   m_send_val = m_send_val+\"&nitbasemar=\"+document.Form1.INTEREST_BASE.value+\"&nitmar=\"+document.Form1.INTEREST_MARGIN.value+\"\";");						
                out.println("	 }else{");
                out.println("	   m_send_val = m_send_val+\"&nitbasemar=&nitmar=\";");						
                out.println("	 }");
                out.println("	 m_fact_val = \"\";"); 
                out.println("	 m_prac_val = \"\";"); 
                out.println("  m_percentage = \"\";"); 
                out.println("  m_char_val = \"0\";"); 
                out.println("  m_main_val = \"0\";"); 
                out.println("  m_mainten  = \"\";"); 
                
                out.println("  for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
                out.println("    m_send_val   = m_send_val+unformat_noobject(document.Form1.elements[\"NETAMT\"+i].value)+\"@\";");
                out.println("    m_fact_val   = m_fact_val+unformat_noobject(document.Form1.elements[\"FACTOR\"+i].value)+\"@\";");
                out.println("    m_prac_val   = m_prac_val+unformat_noobject(document.Form1.elements[\"PRACENT\"+i].value)+\"@\";");
                
                out.println("    m_percentage = m_percentage+unformat_noobject(document.Form1.elements[\"PERCENTAGE\"+i].value)+\"@\";");
                //out.println("    alert('m_percentage'+m_percentage);");
                out.println("	 }");
                //out.println("  for(i=0;i<parseFloat(document.Form1.c_c_count.value);i++){");
                //out.println("    m_char_val   = parseFloat(m_char_val)+parseFloat(unformat_noobject(document.Form1.elements[\"CHARGE_\"+i].value));");
                //out.println("	 }");
                
                //out.println("  for(j=0;j<=parseFloat(document.Form1.hid_y_count.value);j++){");
                //out.println("      m_main_val   =0;");
                //out.println("    for(i=0;i<parseFloat(document.Form1.hid_m_count.value);i++){");
                //out.println("      m_main_val   = parseFloat(m_main_val)+parseFloat(unformat_noobject(document.Form1.elements[\"MAINTANENCE_\"+i+\"_\"+j].value));");
                //out.println("	   }");
                //out.println("    m_mainten   = m_mainten+m_main_val+\"@\";");
                //out.println("	 }");
                
                out.println("    m_send_val = m_send_val+\"&maintan=\"+document.Form1.MAINTANENCE.value+\"&factor=\"+m_fact_val+\"&pracent=\"+m_prac_val+\"&ami=\"+document.Form1.AMI.value+\"&other_cha=\"+m_char_val+\"&percentage=\"+m_percentage;");
                out.println("    return m_send_val;");
                out.println("	 }");
                
                
                out.println("function alertContents(http_request) {");
                //out.println(" alert('test');");
                out.println(" if (http_request.readyState == 4) {");
                out.println("    if (http_request.status == 200) {");
                out.println("      alert(http_request.responseText);");
                out.println("         price_cal.innerHTML=http_request.responseText; ");
                out.println("         for(i=0;i<10000;i++){o=i;}");
                out.println("         asign_div();");
                out.println("    } else {");
                out.println("        alert('There was a problem with the request.');");
                out.println("    }");
                out.println(" }");
                out.println("}");
                
                out.println("function alertGetContents(http_request,opt) {");
                //out.println(" alert('test--'+opt);");
                out.println(" if (http_request.readyState == 4) {");
                out.println("    if (http_request.status == 200) {");
                out.println("      if(opt==\"2\"){");
                //out.println("         alert(http_request.responseText);");
                out.println("         Follow_up.innerHTML=''; ");
                out.println("         Follow_up.innerHTML=http_request.responseText; ");
                out.println("      }else if(opt==\"3\"){");
                
                out.println("      }");
                out.println("    } else {");
                out.println("        Follow_up.innerHTML='';");
                out.println("    }");
                out.println(" }");
                out.println("}");
                //End Of Checking Values
                
                out.println("function asign_div(){");
                out.println("g_re.innerHTML=document.Form1.sun_g_nt.value");
                out.println("n_re.innerHTML=document.Form1.sun_rent.value");
                out.println("p_va.innerHTML=document.Form1.sun_p_nt.value");
                out.println("m_fact.innerHTML=document.Form1.sun_fact.value");
                out.println("document.Form1.hid_count.value=document.Form1.hid_cou.value;");
                out.println("}");
                
                //Help Function
                out.println("function MyDialog(){");
                out.println("this.valout   = new Array(10);");
                out.println("}");	
                
                out.println("function get_help(Start,End,Hid_No,Crit,Sql,IfCount) {");			
                
                out.println("oBj = new MyDialog();");
                out.println("oBj.valout[3]  = \" \";");
                out.println("oBj.valout[4]  = \" \";");
                out.println("oBj.valout[5]  = \" \";");
                //out.println("window.open('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Help_Servlet?class_in="+m_client_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=');");
                out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Help_Servlet?class_in="+m_client_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
                //out.println("alert('333333--'+oBj.valout[0]+'--'+oBj.valout[1]+'--');");
                
                out.println("if(oBj.valout[1]=='Next')  {");
                out.println("Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
                out.println("}");
                out.println("else if  (oBj.valout[1]=='Prev') {");
                out.println("Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
                out.println("}		");
                out.println("else if(oBj.valout[0] == 'Close'){");
                out.println("}");
                out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
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
                out.println("else if(IfCount=='7'){"); 
                out.println("price_assign(oBj);");
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
                out.println(" document.Form1.INQ_NO.value =oBj.valout[2]");
                out.println("}");		
                
                out.println("function price_help(Start,End,Hid_No,Sql,IfCount){");
                out.println("Crit=document.Form1.PRICE_NO.value+\"@\";");
                out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
                out.println("}");		
                out.println("function price_assign(oBj){");
                out.println(" document.Form1.PRICE_NO.value =oBj.valout[2]");
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
                
                out.println("function change_repay(m_type){");
                out.println(" if(m_type=='TYPE'){");
                out.println("    document.Form1.REPAYMENT_INTERVAL.selectedIndex=document.Form1.REPAYMENT_TYPE.selectedIndex ");
                out.println(" }else{");
                out.println("    document.Form1.REPAYMENT_TYPE.selectedIndex=document.Form1.REPAYMENT_INTERVAL.selectedIndex ");
                out.println(" }");
                out.println("}");
                
                //Main Button Action
                //Submit
                out.println("function befor_submit(){");
                out.println(" m_str='<table><tr><td>Month</td><td>Amount</td></tr>'; ");
                out.println(" m_cashOAmo ='0'; ");
                out.println(" m_cash_out =''; ");
                out.println(" m_cashcount='0'; ");
                
                out.println("   for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
                out.println("    if(parseFloat(document.Form1.elements[\"CASHOUT\"+i].value)!=0){");
                out.println("     m_str      = m_str+'<tr><td><input type=text name=\"month'+m_cashcount+'\"       value='+document.Form1.elements[\"INSTALLMENT\"+i].value+' class=\"txt_input2\" disabled></td>';");
                out.println("     m_str      = m_str+'    <td><input type=text name=\"cash_amount'+m_cashcount+'\" value='+document.Form1.elements[\"CASHOUT\"+i].value+'     class=\"txt_input2\" disabled></td></tr>';");
                out.println("     m_cashOAmo = parseFloat(m_cashOAmo)+parseFloat(unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value));");
                out.println("     m_cashcount= parseFloat(m_cashcount)+1");
                out.println("    }");
                out.println("    m_cash_out   = m_cash_out+unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value)+\"@\";");
                out.println("   }");
                out.println("	 if(parseFloat(m_cashOAmo)!=parseFloat(document.Form1.GROSS_AMOUNT.value)){");
                out.println("	   alert('Please Check the Cash Outflow Amounts.');");
                out.println("	   return false;");
                out.println("	 }");
                out.println("	window.opener.document.Form1.CASH_OUTFLOW.value=m_cash_out;");
                out.println(" m_str = m_str+'</table>'; ");
                out.println(" window.opener.outflow_id.innerHTML = m_str; ");
                out.println(" window.opener.document.Form1.CAL_COUNT.value = '1'; ");
                out.println(" window.opener.document.Form1.OUT_COUNT.value = m_cashcount; ");
                
                out.println(" window.close();");
                
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
                //out.println(" if(confirm(\"Are You Sure?\")){  ");
                //out.println("  Form1.reset() ;  ");
                //out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
                //out.println("  document.Form1.OPTION_DESC.value=\"B\";");
                //out.println("  document.Form1.PRICE_NO.disabled=true;");
                out.println("  top.close();");
                //out.println(" }  ");
                out.println("}");
                
                out.println("function befor_modify(){");
                out.println(" if(confirm(\"Are You Sure?\")){  ");
                //out.println("  Form1.reset();   ");
                out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
                out.println("  document.Form1.OPTION_DESC.value=\"Edit\";");
                //out.println("  alert(document.Form1.OPTION_DESC.value);");
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
                
                out.println("function befor_cal(m_stat,opt) {");
                //out.println(" if(document.Form1.RATE.value!=\"\" && document.Form1.NET_AMOUNT.value !=\"\" && document.Form1.VAT_PERCENTAGE.value !=\"\" && document.Form1.PERIOD.value !=\"\" && document.Form1.GROSS_AMOUNT.value!=\"\"){");
                out.println("  document.Form1.CAL_COUNT.value=0;");
                out.println("  if(opt==\"YES\"){");
                out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price\";");
                out.println(" }else{");
                out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?chksql=get_basic_price_cal&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+\"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&ami=\"+document.Form1.AMI.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&last_rent=&nitbasemar=\"+document.Form1.INTEREST_BASE_MARGIN.value+\"&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+\"&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+document.Form1.NIBSM.value+\"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"\";");
                out.println(" }");
                out.println("   m_opt=document.Form1.CACULATED.value;");
                out.println("   document.Form1.CACULATED.value=\"YES\";");
                out.println("   makeRequest(m_url,m_stat,opt);");
                //out.println(" }else{");
                //out.println("   alert('Please Enter ');");
                //out.println(" }");
                
                out.println("}");
                
                out.println("function load_flow() {");
                out.println(" if(document.Form1.RATE.value!=\"\" && document.Form1.NET_AMOUNT.value !=\"\" && document.Form1.VAT_PERCENTAGE.value !=\"\" && document.Form1.PERIOD.value !=\"\" && document.Form1.GROSS_AMOUNT.value!=\"\"){");
                
                out.println("	 m_fact_val = \"\";"); 
                out.println("	 m_prac_val = \"\";"); 
                out.println("  m_percentage = \"\";"); 
                out.println("  m_char_val = \"0\";"); 
                out.println("  m_main_val = \"0\";"); 
                out.println("  m_mainten  = \"\";"); 
                out.println("  m_cash_out = \"\";"); 
                out.println("  m_cashOAmo = \"0\";"); 
                
                out.println("  for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
                out.println("    m_send_val   = m_send_val+unformat_noobject(document.Form1.elements[\"NETAMT\"+i].value)+\"@\";");
                out.println("    m_fact_val   = m_fact_val+unformat_noobject(document.Form1.elements[\"FACTOR\"+i].value)+\"@\";");
                out.println("    m_prac_val   = m_prac_val+unformat_noobject(document.Form1.elements[\"PRACENT\"+i].value)+\"@\";");
                out.println("    m_cash_out   = m_cash_out+unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value)+\"@\";");
                out.println("    m_cashOAmo   = parseFloat(m_cashOAmo)+parseFloat(unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value));");
                
                out.println("    m_percentage = m_percentage+unformat_noobject(document.Form1.elements[\"PERCENTAGE\"+i].value)+\"@\";");
                //out.println("    alert('m_percentage'+m_percentage);");
                out.println("	 }");
                out.println("	 document.Form1.CASH_OUTFLOW.value=m_cash_out;");
                out.println("	 window.opener.document.Form1.CASH_OUTFLOW.value=m_cash_out;");
                //out.println("	 alert(m_cashOAmo+'!='+document.Form1.GROSS_AMOUNT.value);");
                
                out.println("	 if(parseFloat(m_cashOAmo)!=parseFloat(document.Form1.GROSS_AMOUNT.value)){");
                out.println("	   alert('Please Check the Cash Outflow Amounts.');");
                out.println("	   return false;");
                out.println("	 }");
                
                out.println("	 m_send_val = \"chksql=get_outflow_recal&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+");
                out.println("	              \"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&option=\"+");
                out.println("	              \"&last_rent=&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+");
                out.println("	              \"&supcrper=0&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+unformat_noobject(document.Form1.NIBSM.value)+");
                out.println("	              \"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&installments=\";");
                
                out.println("	 if(document.Form1.INTEREST_TYPE.value=='VARIABLE'){");
                out.println("	   m_send_val = m_send_val+\"&nitbasemar=\"+document.Form1.INTEREST_BASE.value+\"&nitmar=\"+document.Form1.INTEREST_MARGIN.value+\"\";");						
                out.println("	 }else{");
                out.println("	   m_send_val = m_send_val+\"&nitbasemar=&nitmar=\";");						
                out.println("	 }");
                
                //out.println("  for(i=0;i<parseFloat(document.Form1.c_c_count.value);i++){");
                //out.println("    m_char_val   = parseFloat(m_char_val)+parseFloat(unformat_noobject(document.Form1.elements[\"CHARGE_\"+i].value));");
                //out.println("	 }");
                
                //out.println("  for(j=0;j<=parseFloat(document.Form1.hid_y_count.value);j++){");
                //out.println("      m_main_val   =0;");
                //out.println("    for(i=0;i<parseFloat(document.Form1.hid_m_count.value);i++){");
                //out.println("      m_main_val   = parseFloat(m_main_val)+parseFloat(unformat_noobject(document.Form1.elements[\"MAINTANENCE_\"+i+\"_\"+j].value));");
                //out.println("	   }");
                //out.println("    m_mainten   = m_mainten+m_main_val+\"@\";");
                //out.println("	 }");
                
                out.println("    m_send_val = m_send_val+\"&maintan=\"+m_mainten+\"&factor=\"+m_fact_val+\"&pracent=\"+m_prac_val+\"&ami=\"+document.Form1.AMI.value+\"&other_cha=\"+m_char_val+\"&percentage=\"+m_percentage;");
                //out.println("   m_str=construct_URL(\"get_outflow_cal\",\"\");");
                out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?\"+m_send_val;");
                out.println("   popupwin = window.open(m_url,'displayWindow1','left=50,top=280,width=900,height=390,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');");
                out.println(" }else{");
                out.println("   alert('Please Enter ');");
                out.println(" }");
                
                out.println("}");
                
                out.println("function befor_main(m_stat,opt) {");
                //out.println("   alert('func ');");
                out.println(" if(document.Form1.MAINTENANCE_APP.options[document.Form1.MAINTENANCE_APP.selectedIndex].value!=\"N\"){");
                //out.println("   alert('if ');");
                out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?chksql=loadMaintenance&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+\"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&ami=\"+document.Form1.AMI.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&last_rent=&nitbasemar=\"+document.Form1.INTEREST_BASE_MARGIN.value+\"&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+\"&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+document.Form1.NIBSM.value+\"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"\";");
                out.println("   makeRequest(m_url,\"Main\",'NO');");
                out.println(" }else{");
                out.println("   Follow_up.innerHTML='';;");
                out.println(" }");
                out.println("}");
                
                
                out.println("function befor_subtype(m_stat,opt) {");
                //out.println("   alert('func ');");
                //out.println(" if(document.Form1.MAINTENANCE_APP.options[document.Form1.MAINTENANCE_APP.selectedIndex].value!=\"N\"){");
                //out.println("   alert('if ');");
                out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?chksql=chkSubType&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+\"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.TRANSACTION_TYPE.value+\"\";");
                out.println("   makeRequest(m_url,\"SubType\",'NO');");
                //out.println(" }else{");
                //out.println("   Follow_up.innerHTML='';;");
                //out.println(" }");
                
                out.println("}");
                
                out.println("function befor_end(m_obj) {");
                out.println("   m_obj.focus();");
                out.println("}");
                
                //end of Main Button Action
                //onload Action
                
                out.println("function befor_onload(){");
                // 60000 milliseconds = 1 minute
                out.println("  setInterval(function() { window.location.reload(true); }, 60000);");
                /*out.println("  document.Form1.PRICE_NO.disabled=true;");
				out.println("  document.Form1.inqu_help.disabled=true;");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println("  load_roll_value(\"New\");"); */
                out.println("}");
                //end of onload
                
                out.println("function cal_VatNet(){");
                out.println("  if(document.Form1.GROSS_AMOUNT.value!=\"\" && document.Form1.VAT_PERCENTAGE.value!=\"\"){");
                out.println("   document.Form1.NET_AMOUNT.value=format_noobject(parseFloat(unformat_noobject(document.Form1.GROSS_AMOUNT.value))/((parseFloat(unformat_noobject(document.Form1.VAT_PERCENTAGE.value))/100)+1));");
                out.println("   document.Form1.VAT_AMOUNT.value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*(parseFloat(unformat_noobject(document.Form1.VAT_PERCENTAGE.value))/100));");
                
                //out.println("   for(j=0;j<parseFloat(document.Form1.c_c_count.value);j++){");
                //out.println("    if(document.Form1.elements['CHARGE_'+j].value=='0' || document.Form1.elements['CHARGE_'+j].value==null){");
                //out.println("     document.Form1.elements['CHARGE_'+j].value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*document.Form1.elements['CHARGE_PER_'+j].value);");
                //out.println("    }");
                //out.println("   }");
                
                out.println("   document.Form1.GROSS_AMOUNT.value=format_noobject(unformat_noobject(document.Form1.GROSS_AMOUNT.value));");
                out.println("  }");
                out.println("}");
                
                out.println("function cal_VatGross(){");
                out.println("  if(document.Form1.NET_AMOUNT.value!=\"\" && document.Form1.VAT_PERCENTAGE.value!=\"\"){");
                out.println("   document.Form1.GROSS_AMOUNT.value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*((parseFloat(unformat_noobject(document.Form1.VAT_PERCENTAGE.value))/100)+1));");
                out.println("   document.Form1.VAT_AMOUNT.value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*(parseFloat(unformat_noobject(document.Form1.VAT_PERCENTAGE.value))/100));");
                
                //out.println("   for(j=0;j<parseFloat(document.Form1.c_c_count.value);j++){");
                //out.println("    if(document.Form1.elements['CHARGE_'+j].value=='0' || document.Form1.elements['CHARGE_'+j].value==null){");
                //out.println("     document.Form1.elements['CHARGE_'+j].value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*document.Form1.elements['CHARGE_PER_'+j].value);");
                //out.println("    }");
                //out.println("   }");
                
                out.println("   document.Form1.NET_AMOUNT.value=format_noobject(unformat_noobject(document.Form1.NET_AMOUNT.value));");
                out.println("  }");
                out.println("}");
                
                out.println("function load_roll_value(m_val){"); 
                out.println("if(m_val==''){");
                out.println("help_box.innerHTML=\"Follow Up - Outstanding Activity Report\";"); 
                out.println("}else{");
                out.println("help_box.innerHTML=\"Follow Up - Outstanding Activity Report - \"+m_val;"); 
                out.println("}");
                out.println("}");
                
                out.println("   ");
                out.println("function chan_residual(){"); 
                out.println(" if(parseFloat(unformat_noobject(document.Form1.NIBSM.value))>0){"); 
                out.println("  document.Form1.RESIDUAL_VALUE.value=format_noobject(document.Form1.NIBSM.value);"); 
                out.println("  document.Form1.NIBSM.value=format_noobject(document.Form1.NIBSM.value);"); 
                out.println("  document.Form1.RESIDUAL_VALUE.disabled=true;");
                out.println(" }else{"); 
                out.println("  document.Form1.RESIDUAL_VALUE.value='0';");
                out.println("  document.Form1.RESIDUAL_VALUE.disabled=false;");
                out.println(" }"); 
                out.println("}");
                
                out.println("function format_text(obj){"); 
                out.println("  obj.value=format_noobject(obj.value);"); 
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
                
                out.println("function cal_count(){"); 
                out.println("  document.Form1.CAL_COUNT.value=parseFloat(document.Form1.CAL_COUNT.value)+1;"); 
                out.println("}");
                
                out.println("function load_fild(){"); 
                out.println(" if(document.Form1.INTEREST_TYPE.value=='VARIABLE'){");
                /*
				out.println("   int_base.innerHTML='Variable Interest Base'; ");
				out.println("   int_base_txt.innerHTML='<input name=\"INTEREST_BASE\" type=\"text\" maxlength=\"50\" class=\"txt_input\" >';; ");
				out.println("   int_mar.innerHTML='Variable Interest Margin'; ");
				out.println("   int_mar_txt.innerHTML='<input name=\"INTEREST_MARGIN\" type=\"text\" maxlength=\"50\" class=\"txt_input\" >';; ");
				*/
                out.println("   int_type.innerHTML='<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >'+");
                out.println("                      '<tr class=tr_input><td width=25%>Variable Interest Base</td>'+");
                out.println("                      '<td  ><input name=\"INTEREST_BASE\" type=\"text\" maxlength=\"50\" class=\"txt_input\" ></td></tr>'+");
                out.println("                      '<tr class=tr_input><td >Variable Interest Margin</td>'+");
                out.println("                      '<td ><input name=\"INTEREST_MARGIN\" type=\"text\" maxlength=\"50\" class=\"txt_input\" ></td></tr></table>';");
                out.println(" }else{");
                out.println("   int_type.innerHTML='';");
                out.println(" }");
                out.println("}");
                
                
                
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
                //out.println("alert('opt='+opt+'--opt1='+opt1); ");
                //http_request.open('GET', "http://localhost:/myserver/servlet/CreateFileFormat?chksql=get_pmt_value_working&rate=18&value=2500000&terms=48&freq=12&type=ADDVANCE&tax=.1", true);
                
                out.println(" if(opt=='1'){");
                //window.open("http://www.ofscl-leasing.lk:/myserver/servlet/LAKDL_AF_MK_Price?"+m_send_val);
                out.println("  http_request.onreadystatechange = function() { alertContents(http_request); };");
                out.println("  http_request.open('POST',url, true);");
                out.println("	 http_request.setRequestHeader(\"Content-Type\",\"application/x-www-form-urlencoded\");");
                
                out.println("	 m_send_val = \"chksql=get_outflow_recal&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+");
                out.println("	              \"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&option=\"+");
                out.println("	              \"&last_rent=&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+");
                out.println("	              \"&supcrper=0&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+unformat_noobject(document.Form1.NIBSM.value)+");
                out.println("	              \"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&installments=\";");
                
                //out.println("  window.open(url+'?'+m_send_val);");
                out.println("  http_request.send(m_send_val);");
                out.println(" }else{");
                
                out.println("  http_request.onreadystatechange = function() { alertGetContents(http_request,opt); };");
                out.println("  http_request.open('GET',url, true);");
                //out.println("  window.open(url);");
                //out.println("  alert('opt-'+opt);");
                out.println("  http_request.send(null);");
                out.println(" }");
                out.println("}");
                
                
                
                out.println("function alertContents(http_request) {");
                //out.println(" alert('test');");
                out.println(" if (http_request.readyState == 4) {");
                out.println("    if (http_request.status == 200) {");
                //out.println("      alert(http_request.responseText);");
                out.println("         price_cal.innerHTML=http_request.responseText; ");
                //out.println("         for(i=0;i<10000;i++){o=i;}");
                //out.println("         asign_div();");
                out.println("    } else {");
                out.println("        alert('There was a problem with the request.');");
                out.println("    }");
                out.println(" }");
                out.println("}");
                
                out.println("function alertGetContents(http_request,opt) {");
                //out.println(" alert('test--'+opt);");
                out.println(" if (http_request.readyState == 4) {");
                out.println("    if (http_request.status == 200) {");
                out.println("      if(opt==\"2\"){");
                //out.println("         alert(http_request.responseText);");
                out.println("         Follow_up.innerHTML=http_request.responseText; ");
                out.println("      }else if(opt==\"3\"){");
                //out.println("         Follow_up.innerHTML=http_request.responseText; ");
                out.println("         Curr_no_opts=document.Form1.TRANSACTION_SUB.options;");
                out.println("         Curr_no_opts.length=0;");
                out.println("         m_string=http_request.responseText; ");
                out.println("         m_index =m_string.indexOf(\"~#@\");"); 
                out.println("         z=0;");
                out.println("         while(m_index!=-1){");
                out.println("           arr_assign[z]  = m_string.substring(0,m_string.indexOf(\"~#@\")); ");
                out.println("           m_string       = m_string.substring(m_string.indexOf(\"~#@\")+3); ");
                out.println("           z              = z+1;");
                out.println("           m_index =m_string.indexOf(\"~#@\");"); 
                out.println("         }");
                out.println("         for(x=0; x<arr_assign.length; x++){");
                out.println("           m_string1      = arr_assign[x].substring(0,arr_assign[x].indexOf(\"@#\")); ");
                out.println("           m_string2      = arr_assign[x].substring(arr_assign[x].indexOf(\"@#\")+2); ");
                out.println("           m_string       = m_string2.substring(0,m_string2.indexOf(\"@#\")); ");
                out.println("           alert('m_string1='+m_string1+'--m_string='+m_string+'');");
                out.println("           Curr_no_opts[x]= new Option(m_string,m_string1);");
                out.println("         }");
                
                out.println("      }");
                out.println("    } else {");
                out.println("        Follow_up.innerHTML='';");
                out.println("    }");
                out.println(" }");
                out.println("}");
                //End Of Checking Values
                
                out.println("function asign_div(){");
                out.println("g_re.innerHTML=document.Form1.sun_g_nt.value");
                out.println("n_re.innerHTML=document.Form1.sun_rent.value");
                out.println("p_va.innerHTML=document.Form1.sun_p_nt.value");
                out.println("m_fact.innerHTML=document.Form1.sun_fact.value");
                out.println("document.Form1.hid_count.value=document.Form1.hid_cou.value;");
                out.println("}");
                
                out.println("function format_text(obj){"); 
                out.println("  obj.value=format_noobject(obj.value);"); 
                out.println("}");
                
                // out.println("function load_edit_window(count,fu_no){");
                // out.println("   document.Form1.ROW_ID.value=count;");
                // out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup?chksql=main_page&Followu_no=\"+fu_no+\"\";");
                // out.println("   popupwin = window.open(m_url,'displayWindow2','left=50,top=200,width=900,height=430,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');");
                // //out.println(" }else{");
                // //out.println("   alert('Please Enter ');");
                // //out.println(" }");
                
                // out.println("}");

                //Follow up comment edit window
                out.println("function load_edit_window(count,fu_no){");
                out.println("   document.Form1.ROW_ID.value=count;");
                out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Follow_Up_Comment?chksql=update_page&comment_id=\"+count+\"&finance_no=\"+fu_no+\"\";");
                // out.println("   popupwin = window.open(m_url,'displayWindow2','left=50,top=200,width=900,height=430,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');");
                out.println("    window.open(m_url, 'displayWindow2', 'left=300,top=200,width=700,height=450,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
                out.println("}");
                //Follow up comment edit window
                
                  rs = stmt.executeQuery (" SELECT COUNT(*)  "+
                    " FROM   "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A,  "+
                    "        "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B  "+
                    " WHERE  STATUS = 'PENDING' AND ACTION_SET_FOR='"+m_username+"' AND  "+
                    "        CATEGORY_CODE=ACTION_TOBE_TAKEN  "+
                     " AND TO_DATE(TO_CHAR(EFF_VAL_DATE,'DD-MON-YYYY'),'DD-MON-YYYY') <= ( "+
                    " SELECT  MIN(EFF_VAL_DATE) "+
                    " FROM   "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A,  "+
                    "        "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B  "+
                    " WHERE  STATUS = 'PENDING' AND ACTION_SET_FOR='"+m_username+"' AND  "+
                    " CATEGORY_CODE=ACTION_TOBE_TAKEN "+
                    " AND EFF_VAL_DATE>=TO_DATE(TO_CHAR(SYSDATE,'DD-MON-YYYY'),'DD-MON-YYYY')  "+
                    " ) "+
                    " ORDER  BY PRIORITY  ");
                    
                    
                    
               /*rs = stmt.executeQuery (" SELECT COUNT(*)  "+
                    " FROM   "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A "+
                    "	WHERE  STATUS = 'PENDING' AND ACTION_SET_FOR='"+m_username+"' AND "+
                    "        EFF_VAL_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MON-YYYY'),'DD-MON-YYYY') ");*/
                
                out.println("function load_alert(){	");
                if(rs.next()){
                    if(rs.getInt(1)>0){
                        out.println(" if(confirm(\"Please Read Your Diary and Proceed. ( "+rs.getInt(1)+" )\")){}"); 
                    }
                }
                out.println("}	"); 
                
                out.println("function load_all_foll(m_stat,opt) {");
                out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Followup?chksql=get_followup\";");
                //out.println("   window.open(m_url);");
                //out.println("  setInterval('makeRequest(m_url,\"2\")',36000);");//Mod by Sandun on 08-12-2009
                out.println("  makeRequest(m_url,\"2\");");
                out.println("}");
                
                out.println("function load_data(num) {");
                
                out.println("	popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Documents?chksql=get_documents&deal_no=\"+num+\"\", \"oBj\",\"left=150,top=280,width=520,height=290\");"); 
                //out.println(" load_c_date(document.Form1.hid_cal_date.value);");
                out.println("}");
                
                //Added By Disnaka on 2010-10-13
                out.println("function load_data1(val1,val2) {");
                out.println("show_transaction_history_new(val1,val2); "); 
                out.println("}");
                
                
                out.println("function show_transaction_history_new(val1,val2){ "); 
                out.println("m_url='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val1+'&client_code='+val2;"); 
                out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
                out.println("}");
                
                
                out.println("</Script>");
                // Change this line:
                out.println("<body onload=\"befor_onload();load_alert();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
                // out.println("<body onload=\"befor_onload();load_alert();load_all_foll('','2');\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
                out.println("<form name=\"Form1\" method=post>");
                out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
                out.println("<input type=hidden name=\"ROW_ID\" ></td>");
                
                out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >");
                out.println("<tr>");
                
                out.println("<td width=\"1\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
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
                out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
                out.println("</tr>");
                out.println("<tr class=\"pdn_txtpos2\">");
                out.println("<td align=\"left\"  style=\"height: 18px\" id=help_box>Follow Up - Outstanding Activity Report</td>");
                out.println("<td align=\"left\"  style=\"height: 18px\"><input type=button name=Close value=\"Close\" class=mainbut onclick=befor_back(); ></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td  class=\"pdn_txtpos\" style=\"height: 28px\" colspan=2>");
                out.println("<table class=table border=\"0\">");
                out.println("<tr>");
                
                //out.println("<td style=\"width: 6px\">");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
                //out.println("<td>&nbsp;</td>");
                //out.println("<td><input type=button name=new      value=\"New\"       class=mainbut onclick=befor_new();      onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");//document.Form1.OPTION_DESC.value
                //out.println("<td>&nbsp;</td>");
                //out.println("<td><input type=button name=edit     value=\"Edit\"      class=mainbut onclick=befor_modify();   onMouseOver='load_roll_value(\"Edit\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                //out.println("<td>&nbsp;</td>");
                //out.println("<td>&nbsp;</td>");
                //out.println("<td>&nbsp;</td>");
                //out.println("<td>&nbsp;</td>");
                //out.println("<td><input type=button name=delete   value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                //out.println("<td>&nbsp;</td>");
                //out.println("<td><input type=button name=cancel   value=\"Re-active\" class=mainbut onclick=befor_active();   onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                //out.println("<td>&nbsp;</td>");
                //out.println("<td><input type=button name=b_submit value=\"Save\"      class=mainbut onclick=befor_submit();   onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                //out.println("<td>&nbsp;</td>");
                //out.println("<td><input type=button name=back     value=\"Close\"     class=mainbut onclick=befor_back();     onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                //out.println("<td>&nbsp;</td>");
                //out.println("<td><input type=button name=reset_   value=\"Reset\"     class=mainbut onclick=befor_reset();    onMouseOver='load_roll_value(\"Reset\");'     onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                //out.println("<td>&nbsp;</td>");
                //out.println("<td>&nbsp;</td>");
                //out.println("<td>&nbsp;</td>");
                //out.println("<td>&nbsp;</td>");
                //out.println("<td>&nbsp;</td>");
                //out.println("<td>&nbsp;</td></tr>");
                //out.println("<td ><input type=button name=cal     value=\"Calculate\" class=mainbut onclick=befor_cal(\"NO\",\"YES\");     onMouseOver='load_roll_value(\"Calculate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                rs = stmt.executeQuery (" SELECT TO_CHAR(SYSDATE,'DD MONTH YYYY HH24:MI:SS'),USER_ID, "+
                    "        UPPER(NAME), UPPER(EMP_ID),UPPER("+m_schema_name+".AF_CO_GET_LOCATION_DESC(LOCATION_CODE)),   "+
                    "        UPPER("+m_schema_name+".AF_CO_GET_DIVISION_DESC(DIVISION_CODE)),UPPER(NVL("+m_schema_name+".AF_CO_GET_DESIGNATION_DESC(DESIGNATION_CODE),'-')) "+
                    " FROM   "+m_schema_name+".CO_CO_MAS_USER "+
                    " WHERE  USER_ID = '"+m_username+"' ");
                boolean more = rs.next(); 
                if(more){
                    
                    out.println("<td class=\"pdn_txtpos\" ><b>OUTSTANDING ACTIVITY REPORT AS AT "+rs.getString(1)+" </b></td>");
                    out.println("</tr>");
                    out.println("<tr class=tr_input>");
                    out.println("<td class=\"pdn_txtpos\"><b>LOGGED USER : "+m_username+" ("+rs.getString(4)+" / "+rs.getString(5)+" / "+rs.getString(6)+" / "+rs.getString(7)+" )</b></td></tr>");
                }
                out.println("</table>");
                out.println("</td>	");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
                out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\" colspan=2>");
                out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
                
                /*out.println("<tr class=tr_input>");
				out.println("<td width=\"15%\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"120\" ></td>");
				out.println("<td width=\"35%\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"180\" ></td>");
				out.println("<td width=\"15%\">");
				out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" ></td>");
				out.println("<td>");
				out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" ></td>");
				out.println("</tr>");*/
                out.println("</table>");

                //-------------------------------Follow-up comment edit section - start---------------------------------------- Added by Kasun on 2026-03-09
                
                out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
                out.println("<tr class=tr_input>");
                out.println("<td valign=top  width=100% Id=Follow_up> ");
                
                
                out.println("<table class=table border='0' width='100%' >");
                out.println("<tr class=tr_input>");
                //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                out.println("</tr>");
                
                out.println("<tr class=pdn_txtpos2>");
                out.println("<td  width='15%' >Contract No</td>");
                out.println("<td  width='10%' >Comment</td>");
                out.println("<td  width='15%' >Created By</td>");
                out.println("<td  width='15%' >Created Date</td>");
                out.println("<td  width='10%' >Follow up Date</td>");
                out.println("<td  width='10%' align='center'>Action</td>");
                out.println("</tr>");
                
                int j = 0;      					
                
                // rs = stmt.executeQuery ( " SELECT "+ 
				// 	"  NVL(FINANCE_NO,'-'), "+//1
				// 	"  NVL(COMMENTS,'-'), "+//2
				// 	"  NVL(ENT_USER,'-'), "+//3
				// 	"  NVL(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'-'), "+//4
				// 	"  NVL(TO_CHAR(FOLLOWUP_DATE,'DD-MM-YYYY'),'-'), "+//5
				// 	"  NVL(DECODE(ACTION, '001', 'New', '002', 'Pending', '003', 'In Progress','004','Hold','005','Completed', ACTION), '-'), "+//6	 		
				// 	"  NVL(TO_CHAR(FINISH_DATE,'DD-MM-YYYY'),'-'), "+//7
                //     "  COMMENT_ID "+ //8
				// 	"  FROM "+m_schema_name+".AF_CO_FOLLOWUP_COMMENTS " + 
                //     "  WHERE ASSIGN_PERSON = '"+m_username+"'" +
                //     "  AND TRUNC(FOLLOWUP_DATE) <= TRUNC(SYSDATE) " +
                //     "  AND ACTION <> '005' " +
                //     "  ORDER BY ENT_DATE ASC ");

                // rs = stmt.executeQuery ( 
                //     " SELECT " + 
                //     "  NVL(C.FINANCE_NO,'-'), " + // 1
                //     "  NVL(C.COMMENTS,'-'), " + // 2
                //     "  NVL(C.ENT_USER,'-'), " + // 3
                //     "  NVL(TO_CHAR(C.ENT_DATE,'DD-MM-YYYY'),'-'), " + // 4
                //     "  NVL(TO_CHAR(C.FOLLOWUP_DATE,'DD-MM-YYYY'),'-'), " + // 5
                //     "  NVL(DECODE(C.ACTION, '001', 'New', '002', 'Pending', '003', 'In Progress','004','Hold','005','Completed', C.ACTION), '-'), " + // 6        
                //     "  NVL(TO_CHAR(C.FINISH_DATE,'DD-MM-YYYY'),'-'), " + // 7
                //     "  C.COMMENT_ID " + // 8
                //     " FROM " + m_schema_name + ".AF_CO_FOLLOWUP_COMMENTS C " + 
                //     " WHERE ( " +
                //     "       C.ASSIGN_PERSON = '" + m_username + "' " + // Standard condition: user sees their own
                //     "       OR C.ASSIGN_PERSON IN ( " + // Manager condition: sees everyone in their location
                //     "           SELECT U2.USER_ID " +
                //     "           FROM " + m_schema_name + ".CO_CO_MAS_USER U1 " +
                //     "           JOIN " + m_schema_name + ".CO_CO_MAS_USER U2 ON U1.LOCATION_CODE = U2.LOCATION_CODE " +
                //     "           WHERE U1.USER_ID = '" + m_username + "' " +
                //     "           AND TRIM(U1.DESIGNATION_CODE) = 'MEXECUTIVE' " + 
                //     "       ) " +
                //     " ) " +
                //     " AND TRUNC(C.FOLLOWUP_DATE) <= TRUNC(SYSDATE) " +
                //     " AND C.ACTION <> '005' " +
                //     " ORDER BY C.ENT_DATE ASC "
                // );
                

                rs = stmt.executeQuery ( 
                            " SELECT " + 
                            "  NVL(C.FINANCE_NO,'-'), " + // 1
                            "  NVL(C.COMMENTS,'-'), " + // 2
                            "  NVL(C.ENT_USER,'-'), " + // 3
                            "  NVL(TO_CHAR(C.ENT_DATE,'DD-MM-YYYY'),'-'), " + // 4
                            "  NVL(TO_CHAR(C.FOLLOWUP_DATE,'DD-MM-YYYY'),'-'), " + // 5
                            // "  NVL(DECODE(C.ACTION, '001', 'New', '002', 'Pending', '003', 'In Progress','004','Hold','005','Completed', C.ACTION), '-'), " + // 6 
                            "   NVL((SELECT ACTION_DESC FROM " + m_schema_name + ".AF_CO_FOLLOWUP_COM_ACTION WHERE ACTION_CODE = C.ACTION), '-'), " + // 6       
                            "  NVL(TO_CHAR(C.FINISH_DATE,'DD-MM-YYYY'),'-'), " + // 7
                            "  C.COMMENT_ID, " + // 8
                            // "  NVL((SELECT COLOUR_CODE FROM " + m_schema_name + ".AF_CO_FOLLOWUP_COM_ACTION WHERE ACTION_CODE = C.ACTION),'#000000') COLOUR_CODE " + //9
                            "   CASE " +
                            "     WHEN TRUNC(C.FOLLOWUP_DATE) <= TRUNC(SYSDATE) THEN '#FF0000' " + //If overdue/due today, make it red
                            "     ELSE NVL((SELECT COLOUR_CODE FROM " + m_schema_name + ".AF_CO_FOLLOWUP_COM_ACTION WHERE ACTION_CODE = C.ACTION), '#000000') " + //9  // Otherwise use standard color
                            "   END AS COLOUR_CODE " + //9
                            " FROM " + m_schema_name + ".AF_CO_FOLLOWUP_COMMENTS C " + 
                            " WHERE ( " +
                            "       C.ASSIGN_PERSON = '" + m_username + "' " + 
                            "       OR ( " +
                            "           C.ENT_USER = '" + m_username + "' " + 
                            "           AND EXISTS ( " +                      
                            "               SELECT 1 " +
                            "               FROM " + m_schema_name + ".CO_CO_MAS_USER U " +
                            "               WHERE U.USER_ID = '" + m_username + "' " +
                            "                 AND TRIM(U.DESIGNATION_CODE) IN (SELECT DESIGNATION_CODE FROM "+ m_schema_name + ".AF_CO_FOLLOW_UP_COMMENT_AUTH) " + 
                            "           ) " +
                            "       ) " +
                            " ) " +
                            " AND TRUNC(C.FOLLOWUP_DATE) <= TRUNC(SYSDATE) " +
                            " AND C.ACTION <> '005' " +
                            " ORDER BY C.ENT_DATE ASC "
                        );
                
                
                
                
                // rs = stmt.executeQuery (" SELECT A.FOLLOW_UP_NO, NVL(A.ID_NO,'-'), CATEGORY_NAME, "+
                //     "        TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),  "+
                //     "        A.ENT_USER,A.ENT_REMARKS,A.ACTION_TAKEN,  "+
                //     "        A.ACTION_DATE,A.REMARKS,  "+
                //     "        "+m_schema_name+".AF_CO_GET_FOLLOWUP_TYPE(A.ID_NO,SCREEN_NAME),   "+
                //     "        FOLLOWUP_TIME, NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO) ,"+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)), "+ //Modified by Disnaka on 2010-10-13
                //     "        NVL("+m_schema_name+".FA_OP_GET_CLINET_COMMENT ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO),NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO) ),A.ID_NO),"+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.ID_NO)) ),A.ENT_REMARKS)"+
                //     " FROM   "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A,  "+
                //     "        "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B  "+
                //     " WHERE  STATUS = 'PENDING' AND ACTION_SET_FOR='"+m_username+"' AND  "+
                //     "        CATEGORY_CODE=ACTION_TOBE_TAKEN  "+
                //     " AND TO_DATE(TO_CHAR(EFF_VAL_DATE,'DD-MON-YYYY'),'DD-MON-YYYY') <= ( "+
                //     //" AND TO_DATE(TO_CHAR(EFF_VAL_DATE,'DD-MON-YYYY'),'DD-MON-YYYY') =  "+
                //     //" TO_DATE(TO_CHAR(SYSDATE,'DD-MON-YYYY'),'DD-MON-YYYY') "+
                //     " SELECT  MIN(EFF_VAL_DATE) "+
                //     " FROM   "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A,  "+
                //     "        "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY B  "+
                //     " WHERE  STATUS = 'PENDING' AND ACTION_SET_FOR='"+m_username+"' AND  "+
                //     " CATEGORY_CODE=ACTION_TOBE_TAKEN "+
                //     " AND EFF_VAL_DATE>=TO_DATE(TO_CHAR(SYSDATE,'DD-MON-YYYY'),'DD-MON-YYYY')  "+
                //      " ) "+
                //     " ORDER  BY PRIORITY  ");
                /* 
                while(rs.next()){
                    //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                    out.println("<tr class=tr_input style=\"cursor:hand\">");
                    out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(1) +"<input type=hidden name=\"FollowUp_No_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                    out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(11)+"</td>");
                    out.println("<td onclick=\"load_data1('"+rs.getString(13)+"','"+rs.getString(14)+"')\";>"+rs.getString(13) +"<input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                    out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(3) +"</td>");
                    out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(4) +"<input type=hidden name=\"Ent_User_"+j+"\" value=\""+rs.getString(6)+"\"></td>");
                    out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(12)+"</td>");
                    out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(15) +"</td>");
                    out.println("<td ><input type=button name=\"Edit_"+j+"\" value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"Edit\"); >");
                    out.println("     </td>");
                    out.println("</tr>");
                    j=j+1;
                    
                    if(rs.next()){
                        out.println("<tr class=tr_input1 style=\"cursor:hand\">");
                        out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(1) +"<input type=hidden name=\"FollowUp_No_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                        out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(11)+"</td>");
                        out.println("<td onclick=\"load_data1('"+rs.getString(13)+"','"+rs.getString(14)+"')\";>"+rs.getString(13) +"<input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                        out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(3) +"</td>");
                        out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(4) +"<input type=hidden name=\"Ent_User_"+j+"\" value=\""+rs.getString(6)+"\"></td>");
                        out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(12)+"</td>");
                        out.println("<td onclick=\"load_data('"+rs.getString(2)+"')\";>"+rs.getString(15) +"</td>");
                        out.println("<td ><input type=button name=\"Edit_"+j+"\" value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"Edit\"); >");
                        out.println("     </td>");
                        out.println("</tr>");
                        j=j+1;
                    }
                    
                } */
                //}
                
                 while(rs.next()){
                    //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                    out.println("<tr class=tr_input style=\"cursor:hand\">");
                    out.println("<td style='color: " + rs.getString(9) + ";' onclick=\"load_data1('"+rs.getString(1)+"')\";>"+rs.getString(1) +"<input type=hidden name=\"FollowUp_No_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                    out.println("<td style='color: " + rs.getString(9) + ";'>"+rs.getString(2)+"</td>");
                    out.println("<td style='color: " + rs.getString(9) + ";'>"+rs.getString(3) +"<input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                    out.println("<td style='color: " + rs.getString(9) + ";'>"+rs.getString(4) +"</td>");
                    out.println("<td style='color: " + rs.getString(9) + ";'>"+rs.getString(5) +"<input type=hidden name=\"Ent_User_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                    out.println("<td align='center'><input type=button name=\"Edit_"+j+"\" value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+rs.getString(8)+"\",\""+rs.getString(1)+"\",\"Edit\"); >");
                    out.println("     </td>");
                    out.println("</tr>");
                    j=j+1;
                    
                    if(rs.next()){
                        out.println("<tr class=tr_input1 style=\"cursor:hand\">");
                        out.println("<td style='color: " + rs.getString(9) + ";' onclick=\"load_data1('"+rs.getString(1)+"')\";>"+rs.getString(1) +"<input type=hidden name=\"FollowUp_No_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                        out.println("<td style='color: " + rs.getString(9) + ";'>"+rs.getString(2)+"</td>");
                        out.println("<td style='color: " + rs.getString(9) + ";'>"+rs.getString(3) +"<input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                        out.println("<td style='color: " + rs.getString(9) + ";'>"+rs.getString(4) +"</td>");
                        out.println("<td style='color: " + rs.getString(9) + ";'>"+rs.getString(5) +"<input type=hidden name=\"Ent_User_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                        out.println("<td align='center'><input type=button name=\"Edit_"+j+"\" value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+rs.getString(8)+"\",\""+rs.getString(1)+"\",\"Edit\"); >");
                        out.println("     </td>");
                        out.println("</tr>");
                        j=j+1;
                    }
                    
                }
                
                out.println("<tr class=tr_input>");
                out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                
                out.println("</tr></table>");
                out.println("</td>");
                
                out.println("</tr>");
                out.println("</table>");
                out.println("</form>");
                out.println("</body>");
                out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
                out.println("</html>");

                //-------------------------------Follow-up comment edit section - end----------------------------------------
            }
            else if(m_chksql.trim().equals("loadMaintenance")){
                
                
            }			//=========================================================================================================================			
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
