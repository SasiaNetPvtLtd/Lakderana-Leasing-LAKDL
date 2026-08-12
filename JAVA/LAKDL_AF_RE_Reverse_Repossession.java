//Option Id is 4.12  
//This File was created by SVA on 21-08-2006 
//Collection Invoice
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_RE_Reverse_Repossession extends javax.servlet.http.HttpServlet {
	
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
			//LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
      String m_username 						= con_method.username;
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			String m_fschema_name=con_method.client_name.trim();
			
			String m_sys_date_dd="";
			String m_sys_date_mm="";
			String m_sys_date_yy="";
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
      //conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");

			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(2);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			out = res.getOutputStream();
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			else if (m_chksql.trim().equals("main_page")) {
			
			
			 		rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD'), "+
																	"TO_CHAR(SYSDATE,'MM'), "+
																	"TO_CHAR(SYSDATE,'YYYY') "+
																	"FROM DUAL ");
								
					boolean more1 = rs.next();
						
							if (more1){
              			
										m_sys_date_dd=rs.getString(1);
										m_sys_date_mm=rs.getString(2);
										m_sys_date_yy=rs.getString(3);
										}
										
			
				
				
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Reverse Repossession</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
      out.println("var m_bsubmit = '0';");
			out.println("var arr_assign= new Array();");
			out.println("var m_send_val= '';");
				
			out.println("function makeRequest(url,opt,obj) {");
			out.println("var http_request = false;");
			out.println("if (window.XMLHttpRequest) {");
			out.println("http_request = new XMLHttpRequest();");
			out.println("if (http_request.overrideMimeType) {");
			out.println("     http_request.overrideMimeType('text/xml');");
			out.println("}");
			out.println("} else if (window.ActiveXObject) { ");
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
			out.println("http_request.onreadystatechange = function() {");
			out.println("alertContents(http_request,opt,obj); };");
			out.println("http_request.open('GET',url, true);");
			out.println("http_request.send(null);");
			out.println("}");
			
        out.println("function alertContents(http_request,opt,opt1) {");
        out.println(" if (http_request.readyState == 4) {");
        out.println("    if (http_request.status == 200) {");
        out.println("      if(http_request.responseText!=\"\"){");
				out.println("        if(opt==\"EXCEPTION\"){");
				out.println("          window_load(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR,document.Form1.TO_DAY,document.Form1.TO_MONTH,document.Form1.TO_YEAR);");
				out.println("        }");				
        out.println("        else {");
				out.println("          var xmlbody=http_request.responseXML.documentElement;");
			  out.println("          var vsize=0;");
				out.println("          data_vec = new Array();");

			  out.println("           for(var i=0;i<xmlbody.childNodes.length;i++){");
				out.println("      			 for(var j=0;j<xmlbody.childNodes[i].childNodes.length;j++){");
				out.println("         	  data_vec[vsize]=xmlbody.childNodes[i].childNodes[j].text;");
				out.println("         	  vsize++;");
				out.println("            }");
			  out.println("           }");
				out.println("          if(data_vec.length>0){");
				out.println("          if(opt==\"REPOSSESS\"){");
				out.println("            assing_date(data_vec);");
				out.println("          }else if(opt==\"FINANCE\"){");
				out.println("            opt1.value=data_vec[3];");
				out.println("          }else if(opt==\"USER\"){");
				out.println("            assign_user(data_vec);");
				out.println("          }else if(opt==\"SIZER\"){");
				out.println("            opt1.value=data_vec[0];");
				out.println("var name =data_vec[1] +' '+data_vec[2];"); 
				out.println("            document.Form1.TXT_SEIZER_NAME.value=name ;");
				out.println("            document.Form1.TXT_LETTER_VALIDITY_PERIOD.value=data_vec[12];");
  		  out.println("          }");				
				out.println("          }else{");
				out.println("          if(opt==\"REPOSSESS\"){");
				out.println("            Reposses_Help('1','10','0','m_help_Repossseion_Help','1')");//m_help_Repossseion_Help  RepossessionNoSql
				out.println("          }else if(opt==\"FINANCE\"){");
				out.println("            Finance_Help('1','10','0','m_help_Finance_Repossessed_Sql','2');");//m_help_Finance_Repossession_Sql FinanceNoSql
				out.println("          }else if(opt==\"SIZER\"){");
				out.println("            Seizer_Help('1','10','0','SeizerCodeSql','3');");
				out.println("          }else if(opt==\"USER\"){");
				out.println("            User_Help();"); 
				out.println("          }");				
				out.println("          }");				
        out.println("        }");				
        out.println("      }");				
        out.println("      else {");
				out.println("         alert('no data found');");
				out.println("      }");
        out.println("    } else {");
        out.println("        alert('There was a problem with the request.');");
        out.println("    }");
        out.println(" }");
        out.println("}");
        

      out.println("function chk_repossession_no(){");
      out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_Repossess_no&repossess_no=\"+document.Form1.TXT_REPOSSESSION_NO.value;");
			out.println(" makeRequest(url,'REPOSSESS',document.Form1.TXT_REPOSSESSION_NO);");
			out.println("  ");
      out.println("}");
  
			out.println("function chk_finance_no(){");
      out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_Finance_no_Repossession&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&ac_status=ACTIVATED\";");//get_Finance_no_Repossession get_Finance_no
			out.println(" makeRequest(url,'FINANCE',document.Form1.TXT_FINANCE_NO);");
      out.println("  ");
      out.println("}");
			
  					
			out.println("function ckeck_data(){ "); 
			out.println("b_flag=0;");			
			out.println("if(!count_receipts()){"); 
			out.println("alert('No vehicle selected');");
			out.println("b_flag=1;");
			out.println("}"); 
			out.println("else{");
			out.println("b_flag=0;");
			out.println("}"); 
			out.println("}"); 
				      
			out.println("function count_receipts(){ ");
			out.println("count=0;");
			out.println("for(i=0;i<parseInt(document.Form1.hid_no_rec.value);i++){");
			out.println("m_chk_deposit_tmp=\"CHK_GENERATE\"+i;");
			out.println("if(document.Form1.elements[m_chk_deposit_tmp].checked==true){");
			out.println("count=count+1;");
			out.println("}");		
			out.println("}");		
			out.println("if(count>0)");
			out.println("return true;");
			out.println("else");
			out.println("return false;");
			out.println("}"); 
			
			
			 
			out.println("function count_receipts_date(){ ");
			out.println("count=0;");
			out.println("for(i=0;i<parseInt(document.Form1.hid_no_rec.value);i++){");
			out.println("m_chk_deposit_tmp=\"CHK_GENERATE\"+i;");
			out.println("m_date_dd=\"TXT_EFF_DATE_DD_\"+i;");
			out.println("m_date_mm=\"TXT_EFF_DATE_MM_\"+i;");
			out.println("m_date_yy=\"TXT_EFF_DATE_YY_\"+i;");
			out.println("if(document.Form1.elements[m_chk_deposit_tmp].checked==true){");
			out.println("if(document.Form1.elements[m_date_dd].value!='' && document.Form1.elements[m_date_mm].value!='' && document.Form1.elements[m_date_yy].value!=''){");
			out.println("count=count+1;");
			out.println("}");					
			out.println("}");		
			out.println("}");		
			out.println("if(count>0)");
			out.println("return true;");
			out.println("else");
			out.println("return false;");
			out.println("}"); 
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_REPOSSESSION_NO.value==\"\" && document.Form1.SCREEN_NAME.value!=\"NEW\" ){  "); 
			out.println("repo.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_FINANCE_NO.value==\"\"){  "); 
			out.println("DIV_TXT_FINANCE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 					
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
					
			out.println("function get_vector_normal(http_response) {");
			out.println(" m_table_vehicle.innerHTML = ''; ");
			out.println(" m_table_vehicle.innerHTML = http_response; ");
			out.println("}");
			
			

			out.println("function before_submit(){ "); 
			out.println("ckeck_data();");
			//out.println("    if(b_flag==0) "); // commented by udara 07-01-2013
			
			out.println("    if(b_flag==0){ ");  // added by udara 07-01-2013
			
			out.println("	    if(confirm(\"Are you sure you want to Revese the selected Repossession?\")){ "); 
			
			// ADDED BY UDARA 01-11-2013
			out.println("		    for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("		       document.Form1.elements[i].disabled=false;");
			out.println("		    }");
			
			out.println("		    document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Save';");  // released by udara 01-11-2013
			out.println("		    document.Form1.submit();	"); 
			out.println("		 }"); 
			
			out.println("	 }"); // added by udara 07-01-2013
	
			// added by udara 07-01-2013
			out.println("	 else{");
			out.println("	      alert('Cannot save record'); ");
			out.println("	 }");
			// end by udara 07-01-2013
			
			out.println("	}"); 
			
			out.println("function load_lock(){	"); 
			out.println("load_roll_value(\"New\");");
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Reverse_Repossession?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Reverse_Repossession?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_RE_Repossession\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Help_Msg_Servlet?class_in="+m_client_name+"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection - Reverse Repossession - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection - Reverse Repossession  - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
/*
			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println(" if(confirm(\"Are you sure you want to enter New record?\")){  ");
			out.println("new_window();"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;"); 
			out.println("}"); 
			out.println("}else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val==\"EDIT\"){"); 
			out.println(" if(confirm(\"Are you sure you want to Modify a record?\")){  ");
			out.println("document.Form1.hid_status.value=\"Edit\";"); 
			out.println("document.Form1.SCREEN_NAME.value=\"EDIT\";"); 
			//out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
		  //out.println("document.Form1.TXT_REPOSSESSION_NO.disabled=false;"); 
			//out.println("document.Form1.TXT_FINANCE_NO.disabled=false;"); 
			out.println("}"); 
			out.println("}else if(m_val==\"DELETE\"){");  
			out.println(" if(confirm(\"Are you sure you want to Delete a record?\")){  ");
			out.println("document.Form1.hid_status.value=\"Delete\";");  
			out.println("document.Form1.SCREEN_NAME.value=\"DELETE\";"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_REPOSSESSION_NO.disabled=false;"); 
			out.println("document.Form1.TXT_FINANCE_NO.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_FINANCE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_SEIZER_CODE.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_SEIZER_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_LETTER_VALIDITY_PERIOD.disabled=true;"); 
			out.println("}"); 
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.TXT_REPOSSESSION_NO.disabled=false;"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.TXT_FINANCE_NO.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_FINANCE_NO.disabled=true;");
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("}else if(m_val==\"DELETE\"){");  
			out.println("document.Form1.hid_save_status.value=\"Delete\";"); 
			out.println("document.Form1.hid_status.value=\"Delete\";");  
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_save_status.value=\"Reactive\";"); 
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 
*/
				out.println("function MyDialog(){");
				out.println("this.valout   = new Array(10);");
				out.println("}");	
				
				out.println("function get_help(Start,End,Hid_No,Crit,Sql,IfCount) {");			
				out.println("oBj = new MyDialog();");
				out.println("oBj.valout[3]  = \" \";");
				out.println("oBj.valout[4]  = \" \";");
				out.println("oBj.valout[5]  = \" \";");
				out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Help_Servlet?class_in="+m_client_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("if(oBj.valout[1]=='Next')  {");
				out.println("Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
				out.println("}");
				out.println("else if  (oBj.valout[1]=='Prev') {");
				out.println("Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
				out.println("}		");
				out.println("else if(oBj.valout[1] == 'Close'  || oBj.valout[1] == null){");
				out.println("if(IfCount=='1'){"); 
				out.println(" document.Form1.TXT_REPOSSESSION_NO.value='';");
				out.println("}");
				out.println("else if(IfCount=='2'){"); 
				out.println(" document.Form1.TXT_FINANCE_NO.value='';");
				out.println("}");
				out.println("else if(IfCount=='3'){"); 
				out.println(" document.Form1.TXT_SEIZER_CODE.value='';");
				out.println("}");
				out.println("}");
				out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != null){");
				out.println("if(IfCount=='1'){"); 
				out.println("Repossession_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='2'){"); 
				out.println("Finance_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='3'){"); 
				out.println("Seizer_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='5'){"); 
				out.println("assign_User_Help(oBj);");
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
				
				out.println("function Reposses_Help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.TXT_REPOSSESSION_NO.value+\"@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				
				out.println("function Repossession_assign(oBj){");
				out.println(" document.Form1.TXT_REPOSSESSION_NO.value =oBj.valout[2]");
				out.println(" chk_repossession_no(document.Form1.TXT_REPOSSESSION_NO);");
				out.println("}");		
				
				out.println("function Finance_Help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.TXT_FINANCE_NO.value+\"@ACTIVATED@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				
				out.println("function Finance_assign(oBj){");
				out.println(" document.Form1.TXT_FINANCE_NO.value =oBj.valout[2]");
				out.println(" document.Form1.TXT_CLIENT_NAME.value =oBj.valout[4]"); 
				out.println("get_vehicle_details();");	
			
				out.println("}");		
				
				out.println("function Seizer_Help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.TXT_SEIZER_CODE.value+\"@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
								
				out.println("function Seizer_Help(){");
				out.println("Crit=document.Form1.TXT_SEIZER_CODE.value+\"@\";");
				out.println("get_help('1','10','0',Crit,'SeizerCodeSql','3');");
				out.println("}");
							
				out.println("function User_Help() {"); 
				out.println("    Crit = document.Form1.TXT_USER_ID.value+\"@Y@\";"); 
				out.println("get_help('1','10','0',Crit,'m_help_TXT_USER_ID_sql','5');");
				out.println("}"); 
				out.println(""); 

				out.println("function assign_User_Help() {"); 
				out.println("    document.Form1.TXT_USER_ID.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_USER_NAME.value=oBj.valout[3];"); 
				out.println("}"); 
				
				out.println("function assign_user(data_vec) {"); 
				out.println("    document.Form1.TXT_USER_ID.value=data_vec[0];"); 
				out.println("    document.Form1.TXT_USER_NAME.value=data_vec[1];"); 
				out.println("}"); 


				
				out.println("function Seizer_assign(oBj){");
				out.println(" document.Form1.TXT_SEIZER_CODE.value =oBj.valout[2]");
				out.println(" document.Form1.TXT_SEIZER_NAME.value =oBj.valout[3]"); 
				
				out.println(" chk_sizer_code();");
				out.println("}");		
				
				
				out.println("function get_vehicle_details(){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Reverse_Repossession?chksql=get_vehicle_details&finance_no=\"+document.Form1.TXT_FINANCE_NO.value;");
			  out.println("load_interface(m_url,'NORM');");
				out.println("}");		
				
				
				out.println("function fill_vehicle_details(finance_no,repo_no,client_code){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Reverse_Repossession?chksql=fill_vehicle_details&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&repo_no=\"+document.Form1.TXT_REPOSSESSION_NO.value+\"&finance_no=\"+document.Form1.TXT_FINANCE_NO.value;");
			  out.println("load_interface(m_url,'NORM');");
				out.println("}");		
			
				out.println("function change_val_generate_status(obj){")	;
				out.println("if(obj.checked==true){");
				out.println("obj.value='on'");
				out.println("}else if(obj.checked==false){");
				out.println("obj.value='off'");
				out.println("}");
				out.println("}");			
				

				
				
				
      out.println("function assing_date(data){");				
			out.println("    document.Form1.TXT_REPOSSESSION_NO.value=data[0];"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=data[1];"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=data[16];"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=data[20];"); 			
			out.println("fill_vehicle_details(data_vec[1],data_vec[0],data_vec[20]);"); 
			out.println("}");	
				
			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_REPOSSESSION_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[3];"); 
			out.println("fill_vehicle_details(oBj.valout[7],oBj.valout[8],document.Form1.SCREEN_NAME.value);"); 
			out.println("}"); 
			
			out.println("function check_Date(objDD,objMM,objYY) {");
			out.println("   if(objDD.value!='' && objMM.value!='' && objYY.value!=''){");
			out.println("   if(checkMonthLength(objDD,objMM,objYY)){");
			out.println("     document.Form1.hid_SYS_DAY.value="+m_sys_date_dd+";");
			out.println("     document.Form1.hid_SYS_MONTH.value="+m_sys_date_mm+";");
			out.println("     document.Form1.hid_SYS_YEAR.value="+m_sys_date_yy+";");
			out.println("if(!chk_validity(document.Form1.hid_SYS_DAY,document.Form1.hid_SYS_MONTH,document.Form1.hid_SYS_YEAR,objDD,objMM,objYY)){");
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("}");
			
			out.println("function load_calendar(num,row_no) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println(" document.Form1.hid_row_no.value=row_no;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			out.println("}");
			
			out.println("function load_c_date(val) {");
			out.println("var v_date=\"\"; ");
			out.println("var v_month=\"\"; ");
			out.println("var v_year=\"\"; ");
			out.println("m_row=document.Form1.hid_row_no.value");
		  out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("     document.Form1.hid_SYS_DAY.value="+m_sys_date_dd+";");
			out.println("     document.Form1.hid_SYS_MONTH.value="+m_sys_date_mm+";");
			out.println("     document.Form1.hid_SYS_YEAR.value="+m_sys_date_yy+";");
  		out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("     document.Form1.elements[\"TXT_EFF_DATE_DD_\"+m_row].value=v_date;");
			out.println("     document.Form1.elements[\"TXT_EFF_DATE_MM_\"+m_row].value=v_month;");
			out.println("     document.Form1.elements[\"TXT_EFF_DATE_YY_\"+m_row].value=val;");
			out.println("check_Date(document.Form1.elements[\"TXT_EFF_DATE_DD_\"+m_row],document.Form1.elements[\"TXT_EFF_DATE_MM_\"+m_row],document.Form1.elements[\"TXT_EFF_DATE_YY_\"+m_row]);");
			out.println("  }");				
			out.println("document.Form1.VAL_DAY.value=''");
			out.println("document.Form1.VAL_MONTH.value=''");
			out.println("document.Form1.VAL_YEAR.value=''");
			out.println("}");
      out.println("}");
			//out.println("}");
			
			out.println("function getDateValues(dval){");
			out.println("if(dval!='null'){");
			out.println("document.Form1.VAL_DAY.value=dval.substring(0,2)");
			out.println("document.Form1.VAL_MONTH.value=dval.substring(3,5)");
			out.println("document.Form1.VAL_YEAR.value=dval.substring(6,10)");
			out.println("}");
			out.println("}");
			
			
			out.println("function chk_validity(Obj_From_DD,Obj_From_MM,Obj_From_YYYY,Obj_To_DD,Obj_To_MM,Obj_To_YYYY){ ");
			out.println("b_val_date=0;");
			out.println("if((parseFloat(Obj_From_DD.value))>=(parseFloat(Obj_To_DD.value))){ ");
			out.println("if((parseFloat(Obj_From_MM.value))<=(parseFloat(Obj_To_MM.value))){ ");
			out.println("   if((parseFloat(Obj_From_YYYY.value))<=(parseFloat(Obj_To_YYYY.value))){ ");
			out.println("   if(((parseFloat(Obj_From_DD.value))==(parseFloat(Obj_To_DD.value)))&& ");
			out.println("    ((parseFloat(Obj_From_MM.value))==(parseFloat(Obj_To_MM.value)))&& ");
			out.println("   ((parseFloat(Obj_From_YYYY.value))==(parseFloat(Obj_To_YYYY.value)))){ ");
			out.println("    } ");
			out.println("   else if(((parseFloat(Obj_From_DD.value))>=(parseFloat(Obj_To_DD.value)))&& ");
			out.println("   ((parseFloat(Obj_From_MM.value))==(parseFloat(Obj_To_MM.value)))&& ");
			out.println("    ((parseFloat(Obj_From_YYYY.value))==(parseFloat(Obj_To_YYYY.value)))){ ");
			out.println("      alert(\"'Effective Value Date' should be greater than 'System Date'\"); ");
			out.println("b_val_date=1");
			out.println("        } ");
			out.println("  } ");
			out.println("  else{ ");
			out.println("      alert(\"'Effective Value Date' should be greater than 'System Date'\"); ");
			out.println("b_val_date=1");
			out.println("  } ");
			out.println("  } ");
			out.println("  else{ ");
			out.println("   if((parseFloat(Obj_From_YYYY.value))>=(parseFloat(Obj_To_YYYY.value))){ ");
			out.println("      alert(\"'Effective Value Date' should be greater than 'System Date'\"); ");
			out.println("b_val_date=1");
			out.println("   } ");
			out.println("    else{ ");
			out.println("   }  ");
			out.println("  } ");
			out.println(" } ");
			out.println(" else{ ");
			out.println("  if((parseFloat(Obj_From_MM.value))<=(parseFloat(Obj_To_MM.value))){ ");
			out.println("  if((parseFloat(Obj_From_YYYY.value))<=(parseFloat(Obj_To_YYYY.value))){ ");
			out.println("  } ");
			out.println("  else{ ");
			out.println("      alert(\"'Effective Value Date' should be greater than 'System Date'\"); ");
			out.println("b_val_date=1");
			out.println("  } ");
			out.println("  } ");
			out.println("  else{ ");
			out.println("   if((parseFloat(Obj_From_YYYY.value))<(parseFloat(Obj_To_YYYY.value))){ ");
			out.println("        } ");
			out.println("   else{ ");
			out.println("      alert(\"'Effective Value Date' should be greater than 'System Date'\"); ");
			out.println("b_val_date=1");
			out.println("   } ");
			out.println("  } ");
			out.println(" } ");
			out.println(" }");
			
     	
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			//out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); // commented by udara on 01-11-2013
			out.println("<input  type='hidden' value='CANCEL' name='SCREEN_NAME'> ");  // added by udara 01-11-2013
			out.println("<input  type='hidden' value='AF_REPOSSESSION' name='Hid_scr_name'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_SYS_DAY' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_SYS_MONTH' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_SYS_YEAR' VALUE=\"\">");
						
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection - Reverse Repossession </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr>");
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DELETE\")' value=\"Delete\"></td>");  
			
			out.println("<td width='6%'></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			
				
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

			out.println("<table align='center' width='100%' class='table' border='0'  cellspacing='0' >"); 

			out.println("<tr >"); 
			out.println("<td width='30%' id=repo>Repossession No</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REPOSSESSION_NO' maxlength='20' size='20' onblur=\"chk_repossession_no()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"...\" onClick=\"Reposses_Help('1','10','0','m_help_Repossseion_Help','1')\" ></td>");//m_help_Repossseion_Help  RepossessionNoSql
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
				
				out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"  cellspacing='0' ><tr>");
			  out.println("<td width='*%' ><div id=m_table_main></div></td>");
			  out.println("<tr></table>"); 
			
			out.println("<table align='center' width='100%' class='table' border=\"0\" cellspacing='0' >"); 
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='20' size='20' disabled onblur=\"Finance_Help('1','10','0','m_help_Finance_Repossessed_Sql','2')\">"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE_NO' value=\"Help\" onClick=\"Finance_Help('1','10','0','m_help_Finance_Repossessed_Sql','2')\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='20' size='20' disabled onblur=\"Finance_Help('1','10','0','m_help_Finance_Repossessed_Sql','2')\">"); 
		//	out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_CODE' value=\"Help\" onClick=\"Finance_Help('1','10','0','m_help_Finance_Repossessed_Sql','2')\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_NAME'  class=div_input>Client Name</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' style=\"{width:350px;}\" maxlength='22' size='22' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table_vehicle'></DIV></td>");
			out.println("</tr>"); 
			out.println("</table>"); 

			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			
			
//////////////////////////////////////////////////////////////////////////////////////////////////////////	
			
			
			
			}
			else if (m_chksql.trim().equals("main_page1")) {
			
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				out.println("var m_bsubmit = '0';");
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
        out.println("http_request.onreadystatechange = function() {alertContents(http_request,opt,opt1); };");
				//alertContents(http_request); };
        //http_request.open('GET', "http://localhost:/myserver/servlet/CreateFileFormat?chksql=get_pmt_value_working&rate=18&value=2500000&terms=48&freq=12&type=ADDVANCE&tax=.1", true);
        //http://localhost:/myserver/servlet/CreateFileFormat?chksql=dis_data
				//http://localhost:/myserver/servlet/CreateFileFormat?chksql=get_pmt_value_working&rate=18&value=2500000&terms=48&freq=12
				//out.println("url=\"http://localhost:/myserver/servlet/LAKDL_AF_MK_Inquiry?chksql=chkCity&Meth=getCustomerCat&value=Y&value1=TEST\";");
        //out.println("window.open(url);");
				out.println("http_request.open('GET',url, true);");
        //alert('1111');
				out.println("http_request.send(null);");
				out.println("}");

        out.println("function alertContents(http_request,opt,opt1) {");
        out.println(" if (http_request.readyState == 4) {");
        out.println("    if (http_request.status == 200) {");
        out.println("      if(http_request.responseText!=\"\"){");
				out.println("        if(opt==\"EXCEPTION\"){");
				out.println("          window_load(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR,document.Form1.TO_DAY,document.Form1.TO_MONTH,document.Form1.TO_YEAR);");
				out.println("        }");				
        out.println("        else {");
				out.println("          var xmlbody=http_request.responseXML.documentElement;");
			  out.println("          var vsize=0;");
				out.println("          data_vec = new Array();");

			  out.println("           for(var i=0;i<xmlbody.childNodes.length;i++){");
				out.println("      			 for(var j=0;j<xmlbody.childNodes[i].childNodes.length;j++){");
				out.println("         	  data_vec[vsize]=xmlbody.childNodes[i].childNodes[j].text;");
				out.println("         	  vsize++;");
					//alert(data_vec[vsize]);
				out.println("            }");
			  out.println("           }");
				out.println("          if(data_vec.length>0){");
				out.println("          if(opt==\"LOAD_DATE\"){");
				out.println("            assing_date(data_vec);");
				out.println("          }else if(opt==\"CHK_DATES\"){");
				out.println("            opt1.value=data_vec[0];");
				out.println("          }else if(opt==\"TEAM_CODE\"){");
				out.println("            opt1.value=data_vec[0];");
				out.println("          }");				
				out.println("          }else{");
				out.println("          opt1.value='';");
				out.println("          alert('no data found');");
				out.println("          }");				
        
				out.println("        }");				
        out.println("      }");				
        out.println("      else {");
				out.println("        if(opt==\"EXCEPTION\"){");
				out.println("          befor_submit();");
				//out.println("        }else if(opt==\"INQ_NO\"){");
				//out.println("          opt1.value='';");
				//out.println("        }else if(opt==\"TEAM_CODE\"){");
				//out.println("          opt1.value='';");				
				//out.println("        }else if(opt==\"TRANSACTION_SUB\"){");
				//out.println("          opt1.value='';");
				out.println("        }else{");				
        out.println("          alert('no data found');");
				out.println("        }");
        out.println("      }");
        out.println("    } else {");
        out.println("        alert('There was a problem with the request.');");
        out.println("    }");
        out.println(" }");
        out.println("}");
        
				
				out.println("function assing_date(data){");
				out.println(" document.Form1.FROM_DAY.value   =data[0]");
				out.println(" document.Form1.FROM_MONTH.value =data[1]");
				out.println(" document.Form1.FROM_YEAR.value  =data[2]");
				out.println(" document.Form1.TO_DAY.value     =data[3]");
				out.println(" document.Form1.TO_MONTH.value   =data[4]");
				out.println(" document.Form1.TO_YEAR.value    =data[5]");
				out.println("}");	
				
				
        out.println("function chk_exception(dayobj,monthobj,yearobj,todayobj,tomonthobj,toyearobj) {");
				out.println(" if(dayobj.value!='' && monthobj.value!='' && yearobj.value!='' && todayobj.value!='' && tomonthobj.value!='' && toyearobj.value!=''){");
				out.println("   url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_InvoiceGenaration?chksql=get_exception&from_date=\"+dayobj.value+\"-\"+monthobj.value+\"-\"+yearobj.value+\"&to_date=\" +todayobj.value+\"-\"+tomonthobj.value+\"-\"+toyearobj.value;");
				out.println("   makeRequest(url,'EXCEPTION',dayobj);");
        out.println(" }");
	      out.println("}");
				    
				out.println("function window_load(dayobj,monthobj,yearobj,todayobj,tomonthobj,toyearobj) {");
				out.println(" if(dayobj.value!='' && monthobj.value!='' && yearobj.value!='' && todayobj.value!='' && tomonthobj.value!='' && toyearobj.value!=''){");
				out.println("   url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_InvoiceGenaration?chksql=get_exception&from_date=\"+dayobj.value+\"-\"+monthobj.value+\"-\"+yearobj.value+\"&to_date=\" +todayobj.value+\"-\"+tomonthobj.value+\"-\"+toyearobj.value;");
				out.println("   INV_POP=window.open(url,\"oBj\",\"left=190,top=380,width=320,height=230\");");
				
				out.println(" }");
	      out.println("}");
						
						
				//End Of Checking Values
				
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
				out.println("function befor_submit1(){");
				out.println("  chk_exception(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR,document.Form1.TO_DAY,document.Form1.TO_MONTH,document.Form1.TO_YEAR);");
				out.println("  ");
				out.println("}");
				
				
				out.println("function befor_submit(){");
				out.println(" m_bsubmit='0';");
				out.println(" if(document.Form1.TO_DAY.value==''){");
				out.println("   tod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.TO_MONTH.value==''){");
				out.println("   tod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.TO_YEAR.value==''){  ");
				out.println("   tod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.FROM_DAY.value==''){");
				out.println("   fod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.FROM_MONTH.value==''){");
				out.println("   fod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.FROM_YEAR.value==''){  ");
				out.println("   fod.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				/*
				out.println(" if(document.Form1.FROM_DAY.value+'-'+document.Form1.FROM_MONTH.value+'-'+document.Form1.FROM_YEAR.value=='--'){  ");
				out.println("      fod.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				*/
				out.println(" if(m_bsubmit=='0'){");
				//out.println("  if(document.Form1.OPTION_NAME.value==\"NEW\" && document.Form1.INQ_NO.value!=\"\"){ ");
				//out.println("   alert();");
				//out.println("  }else{");
				
				
				
				out.println("   document.Form1.action=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Save\";");
				out.println("   document.Form1.submit();");
				//out.println("  }");
				out.println(" }");
				out.println("}");
				//end of Submit Function
				
				out.println("function befor_deactive(){");
				out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  Form1.reset()   ");
				out.println("  document.Form1.OPTION_NAME.value=\"INA\";");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_new(){");
				out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  Form1.reset()   ");
				out.println("  document.Form1.INQ_NO.disabled=true;");
				out.println("  document.Form1.inqu_help.disabled=true;");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println("  assign_null();");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_back(){");
				out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  Form1.reset()   ");
				//out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_modify(){");
				out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  Form1.reset()   ");
				//out.println("  document.Form1.INQ_NO.disabled=false;");
				out.println("  document.Form1.inqu_help.disabled=false;");
				out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
				out.println("  document.Form1.OPTION_DESC.value=\"Edit\";");
				out.println("  assign_null();");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_active(){");
				out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  Form1.reset()   ");
				out.println("  document.Form1.INQ_NO.disabled=false;");
				out.println("  document.Form1.inqu_help.disabled=false;");
				out.println("  document.Form1.OPTION_NAME.value=\"ACT\";");
				out.println("  document.Form1.OPTION_DESC.value=\"Re-activate\";");
				out.println("  assign_null();");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_deactive(){");
				out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  Form1.reset()   ");
				out.println("  document.Form1.INQ_NO.disabled=false;");
				out.println("  document.Form1.inqu_help.disabled=false;");
				out.println("  document.Form1.OPTION_NAME.value=\"INA\";");
				out.println("  document.Form1.OPTION_DESC.value=\"De-activate\";");
				out.println("  assign_null();");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_reset(){");
				out.println(" if(confirm(\"Are You Sure?\")){  ");
				//out.println("  Form1.reset()   ");
			  out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_InvoiceGenaration?chksql=main_page'");
			  out.println(" }  ");
				out.println("}");
				
				
				//end of Main Button Action
				
				//onload Action
				out.println("function befor_onload(){");
				//out.println("  document.Form1.INQ_NO.disabled=true;");
				//out.println("  document.Form1.inqu_help.disabled=true;");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println("  load_roll_value(\"New\");"); 
				out.println("  assign_null();");
				out.println("}");
				//end of onload
				//Mouse movement(MM)
				out.println("function load_roll_value(m_val){"); 
			  out.println("help_box.innerHTML=\"Collection Invoice - \"+m_val;"); 
			  out.println("}");
				//end of MM
				
				out.println("function assign_null(oBj){");
				out.println("chk_load_date();");
				out.println("}");		
				
				out.println("function chk_load_date() {");
        out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_inv_gen_date\";");
				out.println(" makeRequest(url,'LOAD_DATE',document.Form1.TEAM);");
        out.println("}");
  
				out.println("   ");
				out.println("function chkstartdate(dayobj,monthobj,yearobj) {");
				out.println(" if(dayobj.value!='' && monthobj.value!='' && yearobj.value!=''){");
				out.println("	  checkMonthLength(dayobj,monthobj,yearobj);		");
				out.println(" }");
	      out.println("}");
				
				out.println("function chkdate_dif(dayobj,monthobj,yearobj,todayobj,tomonthobj,toyearobj) {");
				out.println(" if(dayobj.value!='' && monthobj.value!='' && yearobj.value!='' && todayobj.value!='' && tomonthobj.value!='' && toyearobj.value!=''){");
				out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_date_dif&from_date=\"+dayobj.value+\"-\"+monthobj.value+\"-\"+yearobj.value+\"&to_date=\" +todayobj.value+\"-\"+tomonthobj.value+\"-\"+toyearobj.value;");
				out.println(" makeRequest(url,'LOAD_DATE',dayobj);");
        out.println(" }");
	      out.println("}");
				
				out.println("</Script>");
				out.println("<body onload=\"befor_onload();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"Hid_scr_name\" value=\"AF_RE_INVOICE_GEN\">");
				out.println("<input type=hidden name=\"OPTION_NAME\" value=\"NEW\">");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"New\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
				//out.println("<input type=hidden name=\"INQ_NO\" value=\"\">");
				
				
				out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\" class=table>");
				out.println("<tr>");
				
				out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
				out.println("<td class=\"border_wht\" valign=\"top\"> ");
				out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" class=table cellpadding=\"0\" height=\"100%\">");
				out.println("<tr> ");
				out.println("<td height=\"6%\" class=\"pdn_mainHD\" class>Asset Financing System</td>");
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
				out.println("<td width=40%>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=reset value=\"New\" class=mainbut onclick=befor_new(); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");//document.Form1.OPTION_DESC.value
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=edit value=\"Edit\" class=mainbut onclick=befor_modify(); onMouseOver='load_roll_value(\"Edit\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=delete value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel value=\"Re-active\" class=mainbut onclick=befor_active(); onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=b_submit value=\"Save\" class=mainbut onclick=befor_submit1(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Reset\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
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
			
				out.println("<tr class=tr_input>");
				out.println("<td id=fod>From Date</td>");
				out.println("<td><input name=\"FROM_DAY\"   type=\"text\" maxlength=\"2\" class=\"txt_input\" style=\"width:25px;\" onchange=chkstartdate(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)> ");
				out.println("    <input name=\"FROM_MONTH\" type=\"text\" maxlength=\"2\" class=\"txt_input\" style=\"width:25px;\" onchange=chkstartdate(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)> ");
				out.println("    <input name=\"FROM_YEAR\"  type=\"text\" maxlength=\"4\" class=\"txt_input\" style=\"width:40px;\" onchange=chkstartdate(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)> ");
				out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td id=tod>To Date</td>");
				out.println("<td><input name=\"TO_DAY\"   type=\"text\" maxlength=\"2\" class=\"txt_input\" style=\"width:25px;\" onchange=chkstartdate(document.Form1.TO_DAY,document.Form1.TO_MONTH,document.Form1.TO_YEAR)> ");
				out.println("    <input name=\"TO_MONTH\" type=\"text\" maxlength=\"2\" class=\"txt_input\" style=\"width:25px;\" onchange=chkstartdate(document.Form1.TO_DAY,document.Form1.TO_MONTH,document.Form1.TO_YEAR)> ");
				out.println("    <input name=\"TO_YEAR\"  type=\"text\" maxlength=\"4\" class=\"txt_input\" style=\"width:40px;\" onchange=chkstartdate(document.Form1.TO_DAY,document.Form1.TO_MONTH,document.Form1.TO_YEAR);chkdate_dif(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR,document.Form1.TO_DAY,document.Form1.TO_MONTH,document.Form1.TO_YEAR);> ");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
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
				out.println("<td width=40%>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=new_1 value=\"New\" class=mainbut onclick=befor_new(); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");//document.Form1.OPTION_DESC.value
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=edit_1 value=\"Edit\" class=mainbut onclick=befor_modify(); onMouseOver='load_roll_value(\"Edit\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=delete value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel value=\"Re-active\" class=mainbut onclick=befor_active(); onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back_1 value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=b_submit_1 value=\"Save\" class=mainbut onclick=befor_submit1(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset_1 value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Reset\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				
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
				out.println("</form>");
				out.println("</body>");
				out.println("<SCRIPT language1.2=\"JavaScript\" src=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT>");
				//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
		
				out.println("</html>");
			
			
			}
			else if (m_chksql.trim().equals("get_exception")) {
			
			String M_FROM_DATE = req.getParameter("from_date");
			
			  rs = stmt.executeQuery(" SELECT FINANCE_NO, A.GRENTAL_AMOUNT,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'), A.CASH_OUT_FLOW, "+
															 "        A.VAT_RENTAL_AMOUNT, A.INVOICE_NO "+
															 " FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
															 " WHERE  RENTAL_DATE<TO_DATE('"+M_FROM_DATE+"','DD-MM-YYYY') AND "+
															 "        B.APPLICATION_NO=A.APPLICATION_NO AND "+
															 //"        RENTAL_DATE<=TO_DATE(M_TO_DATE,'DD-MM-YYYY') AND "+
															 "        INVOICE_NO IS NULL ");
				boolean more = rs.next();
				if(more){
				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				out.println("var m_bsubmit = '0';");
				//Check Values Using AJAX
				
				
				//end of Main Button Action
				//onload Action
				out.println("function befor_onload(){");
				//out.println("  document.Form1.INQ_NO.disabled=true;");
				//out.println("  document.Form1.inqu_help.disabled=true;");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println("  load_roll_value(\"New\");"); 
				//out.println("  assign_null();");
				out.println("}");
				//end of onload
				//Mouse movement(MM)
				out.println("function load_roll_value(m_val){"); 
			  out.println("help_box.innerHTML=\"Collection Invoice - Exception Report\";"); 
			  out.println("}");
				//end of MM
				
				
				
				out.println("   ");
				out.println("</Script>");
				out.println("<body onload=\"befor_onload();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"Hid_scr_name\" value=\"AF_MK_INQUIRY\">");
				out.println("<input type=hidden name=\"OPTION_NAME\" value=\"NEW\">");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"New\">");
				//out.println("<input type=hidden name=\"INQ_NO\" value=\"\">");
				
				
				out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\" class=table>");
				out.println("<tr>");
				
				out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
				out.println("<td class=\"border_wht\" valign=\"top\"> ");
				out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" class=table cellpadding=\"0\" height=\"100%\">");
				out.println("<tr> ");
				out.println("<td height=\"6%\" class=\"pdn_mainHD\" class>Asset Financing System</td>");
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
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
				
				out.println("<tr class=tr_input>");
				out.println("<td>Finance No</td>");
				out.println("<td>Rental Amount</td>");
				out.println("<td>Rental Date</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				while(more){
				//   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
				out.println("<tr class=tr_input>");
				out.println("<td >"+rs.getString(1)+"</td>");
				out.println("<td >"+nf.format(rs.getDouble(2))+"</td>");
				out.println("<td >"+rs.getString(3)+"");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
					 more = rs.next();	
				}	
				
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td class=\"line\" height=\"1\">");
				out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td class=\"pdn_txtpos\" style=\"height: 10px\">");
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
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
			  }
				
			
			}
			
			//added by nuwan de silva on31-10-07---------------------
			else if (m_chksql.trim().equals("get_vehicle_details")) {
			
			
			String _m_finance_no = req.getParameter("finance_no").trim();
			/*
			String Sql_vehicle="SELECT"+		
			"			NVL(UPPER(C.MAKE_DESC),' ' ) || '-'|| NVL(UPPER("+m_schema_name+".AF_CO_GET_MODEL_DESC(B.MODEL_CODE)), ' ')||'-'|| NVL(UPPER(F.DESCRIPTION),' ' ) DESCRIPTION, "+
			"			NVL(B.ENGINE_NO,'-') , "+
			"			NVL(B.CHASSIS_NO,'-') , "+
			"     B.REG_NO , "+
			"			B.INVOICE_NO "+
			"			FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
			"			"+m_schema_name+".AF_CO_MAS_MAKE C,"+
			"    "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY F,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS E "+
			"			WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
			"			B.APPLICATION_NO=E.APPLICATION_NO AND "+
			"			E.FINANCE_NO=UPPER('"+_m_finance_no+"') AND "+
			"			A.ACTIVE_STATUS='Y' AND "+
			"			A.ASSET_ID=B.ASSET_ID AND "+
			"			B.INVOICE_NO NOT IN ( "+
			"     SELECT  PRO_INVOICE_NO FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION F  WHERE UPPER(FINANCE_NO) = UPPER('"+_m_finance_no+"') "+
			"			) "+
			"			AND (C.MAKE_CODE ,F.ITEM_SUB_CAT ) IN "+
			"			(SELECT "+
			"			MAKE_CODE ,ITEM_SUB_CAT "+
			"			FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
			"			WHERE "+
			"			MODEL_CODE IN ( "+
			"			SELECT "+
			"			MODEL_CODE "+
			"			FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
			"			WHERE INVOICE_NO=B.INVOICE_NO "+
			"			AND ACTIVE_STATUS='Y' "+
			"			)) ";
		
			*/
			
			String Sql_vehicle="";
			/*" SELECT "+ 
												 " A.APPLICATION_NO, "+//1
												 " A.FINANCE_NO, "+//2
												 " A.CLIENT_CODE, "+//3
												 " A.ASSET_DESC, "+//4
												 " C.ENGINE_NO, "+//5
												 " C.CHASSIS_NO, "+//6
												 " C.REG_NO, "+//7
												 " A.CLIENT_FULL_NAME, "+//8
												 " NVL(TO_CHAR(REPOSSESSED_DATE,'DD-MM-YYYY'),'-'),"+//9
												 " A.LAST_DATE_OF_VALUATION, "+//10
												 " A.VALUATION_AMT, "+//11
											   " B.VEHICLE_NO "+//12
												 " FROM "+m_schema_name+".AF_RE_TBD_CB_REPOS_VEHICLES A, "+
												 " "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B, "+
												 " "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
											   " WHERE B.CLIENT_CODE=A.CLIENT_CODE "+
												 " AND A.APPLICATION_NO = C.APPLICATION_NO "+
												 " AND C.ACTIVE_STATUS='Y' "+
												 " AND A.FINANCE_NO=UPPER('"+_m_finance_no+"')";
											*/					
			
			  rs = stmt.executeQuery(Sql_vehicle);
	      boolean more = rs.next();
				if(more){
				out.println("<table class='table'border='0' width='100%'>");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='30%' ><b>Vehicle Description</td>");
				out.println("<td width='15%' ><b>Vehicle No</td>");
				out.println("<td width='15%' ><b>Engine No</td>");
				out.println("<td width='15%' ><b>Chassis No</td>");
				out.println("<td width='10%' ><b>Registration No</td>");
				out.println("<td width='10%' ><b>Repossessed Date</td>");
				out.println("<td width='5%'  ><b>Reverse</td>");
				out.println("</td>");
				out.println("</tr>");
				
				}
				int j=0;
				while(more){
				
					if(j>0 && j%2==1){
					out.println("<tr class=tr_input1 >");
					}
					else{
					out.println("<tr class=tr_input >");
					}


//				out.println("<tr class=tr_input>");
				out.println("<td width='30%' >"+rs.getString(4)+"</td>");
				out.println("<td width='15%' >"+rs.getString(12)+"</td>");
				out.println("<td width='15%' >"+rs.getString(5)+"</td>");
				out.println("<td width='15%' >"+rs.getString(6)+"</td>");
				out.println("<td width='10%' >"+rs.getString(7)+"</td>");
				out.println("<td width='10%' >"+rs.getString(9)+"</td>");
				//out.println("<td width=\"12%\" align=left><input class=\"txt_input5\" type=\"text\" name=TXT_EFF_DATE_DD_"+j+" maxlength=\"2\" size=\"1\" value=\"\" onblur=\"check_Date(document.Form1.TXT_EFF_DATE_DD_"+j+",document.Form1.TXT_EFF_DATE_MM_"+j+",document.Form1.TXT_EFF_DATE_YY_"+j+")\">");
			  //out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_EFF_DATE_MM_"+j+"  maxlength=\"2\" size=\"1\" value=\"\"                             onblur=\"check_Date(document.Form1.TXT_EFF_DATE_DD_"+j+",document.Form1.TXT_EFF_DATE_MM_"+j+",document.Form1.TXT_EFF_DATE_YY_"+j+")\">");
			 // out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_EFF_DATE_YY_"+j+" maxlength=\"4\" size=\"4\" value=\"\"                              onblur=\"check_Date(document.Form1.TXT_EFF_DATE_DD_"+j+",document.Form1.TXT_EFF_DATE_MM_"+j+",document.Form1.TXT_EFF_DATE_YY_"+j+")\"></td>");
				//out.println("<td width=\"3%\" align=left style= cursor:hand;cursor-color:blue onclick=load_calendar('2',"+j+") ><u>Calender</u></td>"); 
				out.println("<td width=\"5%\" align=center><INPUT TYPE=\"checkbox\" NAME=CHK_GENERATE"+j+"  VALUE=\"off\" onclick=\"change_val_generate_status(this)\" ></td>");			
				out.println("</td>");
				out.println("<input type=hidden name=hid_INVOICE_NO_"+j+" value=\""+rs.getString(5)+"\">");
				out.println("</tr>");
				more = rs.next();	
				j=j+1;
			  }
				out.println("</table>");
				out.println("<input type=hidden name=hid_no_rec value="+j+">");
			
			}
			
			else if (m_chksql.trim().equals("fill_vehicle_details")) {
			
			String _m_finance_no = req.getParameter("finance_no");
		  String _m_client_code = req.getParameter("client_code");
			String _m_repo_no = req.getParameter("repo_no");
				
	    String Sql_vehicle=" SELECT "+
												 " NVL(UPPER(C.MAKE_DESC),' ' ) || '-'|| NVL(UPPER("+m_schema_name+".AF_CO_GET_MODEL_DESC(B.MODEL_CODE)), ' ') ||'-'|| NVL(UPPER(D.DESCRIPTION),' ') ||'-'|| NVL(UPPER(F.DESCRIPTION),' ' ) DESCRIPTION, "+
												 " NVL(B.ENGINE_NO,'-') , "+//2
												 " NVL(B.CHASSIS_NO,'-') , "+//3
												 " D.YEAR_OF_MANUFACTURE, "+ //4
												 " B.INVOICE_NO , "+//5
											   " H.REPOSSESSION_NO, "+//6
												 " H.FINANCE_NO, "+//7
												 " NVL(B.VEHICLE_NO,'-'), "+//8
												 " NVL(B.REG_NO,'-') "+//9
												 " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
												 " "+m_schema_name+".AF_CO_MAS_MAKE C, "+
												 " "+m_schema_name+".AF_CO_MAS_SUB_MODLE D,"+
												 " "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY F, "+
												 " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS E, "+
												 " "+m_schema_name+".AF_RE_PRO_REPOSSESSION H "+
												 " WHERE B.APPLICATION_NO=E.APPLICATION_NO AND "+ 
												 " E.FINANCE_NO=H.FINANCE_NO AND "+
												 " UPPER(E.FINANCE_NO)=UPPER('"+_m_finance_no+"') AND "+
												 " UPPER(H.REPOSSESSION_NO)=UPPER('"+_m_repo_no+"') AND	"+
												 " UPPER(E.CLIENT_CODE)=UPPER('"+_m_client_code+"') AND	"+
												 " (C.MAKE_CODE ,F.ITEM_SUB_CAT ) IN "+
												 " (SELECT "+
												 " MAKE_CODE ,ITEM_SUB_CAT "+
												 " FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
												 " WHERE "+
												 " MODEL_CODE IN ( "+ 
												 " SELECT "+
												 " MODEL_CODE "+
												 " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+ 
												 " WHERE INVOICE_NO=B.INVOICE_NO "+ 
												 " AND ACTIVE_STATUS='Y' )) "+
												 " AND D.SUB_CODE=B.SUB_MODEL_CODE ";
				
			
				rs = stmt.executeQuery(Sql_vehicle);
	      boolean more = rs.next();
				if(more){
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='30%' ><b>Vehicle Description</td>");
				out.println("<td width='15%' ><b>Vehicle No</td>");
				out.println("<td width='15%' ><b>Engine No</td>");
				out.println("<td width='15%' ><b>Chassis No</td>");
				out.println("<td width='10%' ><b>Registration No</td>");
				out.println("<td width='5%'  ><b>Reverse</td>");
				out.println("</td>");
				out.println("</tr>");
				
				}
				int j=0;
				while(more){
				
					if(j>0 && j%2==1){
					out.println("<tr class=tr_input1 >");
					}
					else{
					out.println("<tr class=tr_input >");
					}


  			out.println("<td width='30%' >"+rs.getString(1)+"</td>");
				out.println("<td width='20%' >"+rs.getString(8)+"</td>");
				out.println("<td width='10%' >"+rs.getString(2)+"</td>");
				out.println("<td width='10%' >"+rs.getString(3)+"</td>");
				out.println("<td width='10%' >"+rs.getString(9)+"</td>");
				out.println("<td width=\"5%\" align=center><INPUT TYPE=\"checkbox\" NAME=CHK_GENERATE"+j+"  VALUE=\"off\" onclick=\"change_val_generate_status(this)\"   ></td>");			
										
				out.println("</tr>");
				more = rs.next();	
				j=j+1;
			  }
				out.println("</table>");
				out.println("<input type=hidden name=hid_no_rec value="+j+">");
			
			}

			
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
