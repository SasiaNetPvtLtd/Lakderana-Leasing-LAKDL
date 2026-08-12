//Option Id is 2.1  
//This File was created by SVA on 17-05-2006 
//Marketing Inquiry Display
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_MK_Inquiry extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf;
	public ResultSet rs,rs1;
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
      String m_username 		= con_method.username;
			String header_name    = con_method.header_name;
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
			
			  String m_inquiry_no = "";
				
			  if(req.getParameter("inquiry_no")!=null){
			   m_inquiry_no = req.getParameter("inquiry_no");
				}
			
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
				out.println("        if(opt==\"OFFICER\"){");
				out.println("          opt1.value=http_request.responseText;");
				out.println("          get_mk_team();");
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
				out.println("          if(opt==\"INQ_NO\"){");
				out.println("            assing_inqval(data_vec);");
				out.println("          }else if(opt==\"TRANSACTION_SUB\"){");
				out.println("            opt1.value=data_vec[0];");
				out.println("          }else if(opt==\"LOD_OFFICER\"){");//
				out.println("            opt1.value=data_vec[0];");//
				out.println("           document.Form1.SUPERVISOR_CODE.value=data_vec[2];");//
				out.println("           document.Form1.TEAM.value=data_vec[1];");//
				out.println("          }else if(opt==\"TEAM_CODE\"){");
				out.println("            opt1.value=data_vec[0];");
				out.println("          }else if(opt==\"TEAM\"){");
				out.println("            document.Form1.TEAM.value=data_vec[0];");
				out.println("            document.Form1.SUPERVISOR_CODE.value=data_vec[1];");
				out.println("            if(document.Form1.BRANCH_CODE.value==''){");
				out.println("              get_officer_branch();");
				out.println("            }");
				out.println("          }else if(opt==\"HEAD_CODE\"){");
				out.println("            document.Form1.TEAM.value=data_vec[0];");
				out.println("            document.Form1.SUPERVISOR_CODE.value=data_vec[1];");
				out.println("          }else if(opt==\"BRANCH\"){");
				out.println("            document.Form1.BRANCH_CODE.value=data_vec[0];");
				out.println("            document.Form1.BRANCH_DESC.value=data_vec[1];");
				out.println("          }else if(opt==\"OFF_BRANCH\"){");
				out.println("            document.Form1.BRANCH_CODE.value=data_vec[0];");
				out.println("            document.Form1.BRANCH_DESC.value=data_vec[1];");
				//
				out.println("          }");				
				out.println("          }else{");
				//out.println("          opt1.value='';");
				out.println("          if(opt==\"INQ_NO\"){");
				out.println("            inq_help('1','10','13','InquirySql','7');");
				out.println("          }else if(opt==\"TRANSACTION_SUB\"){");
				out.println("            sub_trn_help('1','10','0','TrnSubSql','6');");
				out.println("          }else if(opt==\"TEAM_CODE\"){");
				out.println("            mk_team_help('1','10','0','MKTeamSql','4');");
				out.println("          }else if(opt==\"OFFICER\"){");
				out.println("            mk_officer_help('1','10','0','MKOfficerSql','2');");
				out.println("          }else if(opt==\"HEAD_CODE\"){");
				out.println("            mk_team_help('1','10','0','MKTeamSql','11');");
				out.println("          }else if(opt==\"BRANCH\"){");
				out.println("            branch_help('1','10','0','BranchSql','9');");
				out.println("          }");
				out.println("          }");				
        
				out.println("        }");				
        out.println("      }");				
        out.println("      else {");
				out.println("        if(opt==\"OFFICER\"){");
				out.println("          mk_officer_help('1','10','0','MKOfficerSql','2');");
				out.println("        }else if(opt==\"INQ_NO\"){");
				out.println("          inq_help('1','10','13','InquirySql','7');");
				out.println("        }else if(opt==\"TEAM_CODE\"){");
				out.println("          mk_team_help('1','10','0','MKTeamSql','4');");			
				out.println("        }else if(opt==\"TRANSACTION_SUB\"){");
				out.println("            sub_trn_help('1','10','0','TrnSubSql','6');");
				out.println("          }else if(opt==\"HEAD_CODE\"){");
				out.println("            mk_team_help('1','10','0','MKTeamSql','11');");
				out.println("          }else if(opt==\"BRANCH\"){");
				out.println("            branch_help('1','10','0','BranchSql','9');");
				out.println("        }else{");				
        out.println("          alert('no data found');");
				out.println("        }");
        out.println("      }");
        out.println("    } else {");
        out.println("        alert('There was a problem with the request.');");
        out.println("    }");
        out.println(" }");
        out.println("}");
        
				
				out.println("function assing_inqval(data){");
				out.println(" document.Form1.CLIENT_NAME.value =data[1]");
				out.println(" document.Form1.TEL_NO.value =data[2]");
				out.println(" document.Form1.MOBILE_NO.value =data[3]");
				out.println(" document.Form1.FAX_NO.value =data[4]");
				out.println(" document.Form1.ADDRESS.value =data[5]");
				out.println(" document.Form1.ADDRESS1.value =data[6]");
				out.println(" document.Form1.CITY_CODE.value =data[7]");
				out.println(" document.Form1.CLIENT_TYPE.value =data[8]");
				out.println(" document.Form1.INITIATION_TYPE.value =data[10]");
				out.println(" document.Form1.CLIENT_CATEGORY.value =data[11]");
				out.println(" document.Form1.CLIENT_LAST_NAME.value =data[25]");
				//out.println(" document.Form1.INITIATION_TYPE.value =data[12]");
				 
				out.println(" document.Form1.LEAD_SOURCE_NAME.value =data[14]");
				//out.println(" document.Form1.INTRODUCER.value =data[15]");
				out.println(" document.Form1.ID_NO.value =data[16]");
				out.println(" document.Form1.TRANSACTION_CODE.value =data[18]");
				out.println(" document.Form1.TRANSACTION_SUB.value =data[19]");
				out.println(" document.Form1.EMAIL.value =data[20]");
				//out.println(" document.Form1.TEAM.value =data[21]");
				//out.println(" document.Form1.OFFICER_CODE.value =data[22]");
				//out.println(" document.Form1.SUPERVISOR_CODE.value =data[23]");
				out.println(" document.Form1.CONTACT_PERSON.value =data[24]");
				out.println(" document.Form1.CITY_DESC.value =data[27]");
				out.println(" document.Form1.BRANCH_CODE.value =data[28]");
				out.println(" document.Form1.BRANCH_DESC.value =data[29]");
				
				out.println("}");	
				
				
				
        out.println("function chk_mk_officer() {");
        out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Inquiry?chksql=chkOfficer&officer=\"+document.Form1.OFFICER_CODE.value+\"&user_name="+m_username+"\";");
				out.println(" makeRequest(url,'OFFICER',document.Form1.OFFICER_CODE);");			
				out.println("}");
        
				out.println("function get_mk_officer() {");
        out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Inquiry?chksql=getOfficer&officer=\"+document.Form1.OFFICER_CODE.value+\"&user_name="+m_username+"\";");
				//out.println(" window.open(url);");
        out.println(" makeRequest(url,'OFFICER',document.Form1.OFFICER_CODE);");
        out.println("}");
        
				out.println("function asign_mk_officer() {");
       // out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Inquiry?chksql=getOfficer&officer=\"+document.Form1.OFFICER_CODE.value+\"&user_name="+m_username+"\";");
				 out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_XMLFile?chksql=loadOfficer&user=\"+document.Form1.OFFICER_CODE.value+\"&user_name="+m_username+"\";");
				//out.println(" window.open(url);");
        out.println(" makeRequest(url,'LOD_OFFICER',document.Form1.OFFICER_CODE);");
        out.println("}");
				
				out.println("function get_mk_team() {");
        out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_XMLFile?chksql=getTeam&user=\"+document.Form1.OFFICER_CODE.value+\"&user_name="+m_username+"\";");
				out.println(" makeRequest(url,'TEAM',document.Form1.OFFICER_CODE);");
        out.println("}");
				
				out.println("function asign_mk_team_head(){");
        out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_XMLFile?chksql=getMktTeam&user=\"+document.Form1.OFFICER_CODE.value+\"&user_name="+m_username+"\";");
				//out.println("window.open(url);");
				out.println(" makeRequest(url,'LOD_TEAM',document.Form1.OFFICER_CODE);");
        out.println("}");
        
				out.println("function chk_mk_team() {");
        out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_XMLFile?chksql=get_team&team=\"+document.Form1.TEAM.value+\"\";");
				//out.println(" window.open(url);");
				out.println(" makeRequest(url,'TEAM_CODE',document.Form1.TEAM);");
        out.println("}");
				
				out.println("function asign_mk_team() {");
        out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_XMLFile?chksql=load_team&team=\"+document.Form1.OFFICER_CODE.value+\"\";");
				out.println(" window.open(url);");
				out.println(" makeRequest(url,'LOD_TEAM_CODE',document.Form1.OFFICER_CODE);");
        out.println("}");
				
				out.println("function chk_mk_head() {");
        out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_XMLFile?chksql=get_head&head=\"+document.Form1.TEAM.value+\"\";");
				//out.println(" window.open(url);");
				out.println(" makeRequest(url,'HEAD_CODE',document.Form1.SUPERVISOR_CODE);");
        out.println("}");
				
				
				out.println("function chk_sub_trncode() {");
        out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_XMLFile?chksql=get_trn_sub&trn_sub=\"+document.Form1.TRANSACTION_SUB.value+\"\";");
				out.println(" makeRequest(url,'TRANSACTION_SUB',document.Form1.TRANSACTION_SUB);");
        out.println("}");
    
        out.println("function chk_inqno() {");
        out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_XMLFile?chksql=get_inq_det&inqno=\"+document.Form1.INQ_NO.value+\"\";");
				out.println(" makeRequest(url,'INQ_NO',document.Form1.INQ_NO);");
        out.println("}");
        
				out.println("function chk_branch() {");
        out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_XMLFile?chksql=getBranch&BRANCH=\"+document.Form1.BRANCH_CODE.value+\"\";");
				//out.println(" window.open(url);");
				out.println(" makeRequest(url,'BRANCH',document.Form1.BRANCH_CODE);");
        out.println("}");
				
				out.println("function get_officer_branch() {");
        out.println(" url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_XMLFile?chksql=getOffBranch&user=\"+document.Form1.OFFICER_CODE.value+\"\";");
				//out.println(" window.open(url);");
				out.println(" makeRequest(url,'OFF_BRANCH',document.Form1.BRANCH_CODE);");
        out.println("}");
				
				//End Of Checking Values
				
				//Help Function
				out.println("function MyDialog(){");
				out.println("this.valout   = new Array(10);");
				out.println("}");	
				
				out.println("function get_help(Start,End,Hid_No,Crit,Sql,IfCount) {");			
				//
				out.println("oBj = new MyDialog();");
				out.println("oBj.valout[3]  = \" \";");
				out.println("oBj.valout[4]  = \" \";");
				out.println("oBj.valout[5]  = \" \";");
				//out.println("window.open('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Help_Servlet?class_in="+m_client_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=');");
				out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Help_Servlet?class_in="+m_client_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				//out.println("alert(oBj.valout[3]+'--oBj-'+oBj.valout[2]+'--'+oBj.valout[4]);");
				
				out.println("if(oBj.valout[1]=='Next')  {");
				out.println("Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
				out.println("}");
				out.println("else if  (oBj.valout[1]=='Prev') {");
				out.println("Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
				out.println("}		");
				out.println("else if(oBj.valout[1] == 'Close'){");
				out.println("if(IfCount=='7'){"); 
				out.println("document.Form1.INQ_NO.value ='';");
				out.println("}");
				out.println("else if(IfCount=='4'){"); 
				out.println("document.Form1.TEAM.value ='';");
				out.println("}");
				out.println("else if(IfCount=='11'){"); 
				out.println("document.Form1.SUPERVISOR_CODE.value ='';");
				out.println("}");
				out.println("else if(IfCount=='2'){"); 
				out.println("document.Form1.OFFICER_CODE.value ='';");
				out.println("}");
				out.println("}");
				out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != null){");
				out.println("if(IfCount=='1'){"); 
				out.println("client_assign(oBj);");
				//out.println("load_data(document.Form1.txt_aff_code.value);");
				out.println("}");
				out.println("else if(IfCount=='2'){"); 
				out.println("mk_officer_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='3'){"); 
				out.println("mk_super_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='4'){"); 
				out.println("mk_team_assign(oBj);");
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
				out.println("else if(IfCount=='8'){"); 
				out.println("brk_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='9'){"); 
				out.println("mk_branch_assign(oBj);");
				out.println("}");
				
				out.println("else if(IfCount=='11'){"); 
				out.println("mk_supe_assign(oBj);");   
				out.println("}");
				
				out.println("else if(IfCount=='12'){"); 
				out.println("city_assign(oBj);");  // 
				out.println("}");
				
				out.println("}");
				out.println("else if(oBj.valout[4] == \" \"){ ");
				
				out.println("if(IfCount=='7'){"); 
				out.println("document.Form1.INQ_NO.value ='';");
				out.println("}");
				out.println("else if(IfCount=='4'){"); 
				out.println("document.Form1.TEAM.value ='';");
				out.println("}");
				out.println("else if(IfCount=='11'){"); 
				out.println("document.Form1.SUPERVISOR_CODE.value ='';");
				out.println("}");
				out.println("else if(IfCount=='2'){"); 
				out.println("document.Form1.OFFICER_CODE.value ='';");
				out.println("}");
				//out.println("Crit = oBj.valout[4];");
				//out.println("criteria_1('1','10','1',oBj.valout[4],Sql,IfCount);");
				//out.println("}	");
				out.println("}");	
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
				//Modified by Mahela on 17-05-2007 
				//Purpose : To filter Customer Names by Selected Customer Type
				out.println("function client_help(Start,End,Hid_No,Sql,IfCount){");
				out.println(" document.Form1.hid_client_type.value=document.Form1.CLIENT_TYPE.options[document.Form1.CLIENT_TYPE.selectedIndex].value; ");
				//out.println(" alert('client type &&'+document.Form1.hid_client_type.value);");
				out.println("Crit=document.Form1.CLIENT_NAME.value+\"@\"+document.Form1.hid_client_type.value+\"@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");
				
				out.println("function city_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.CITY_CODE.value+\"@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");	
				
				out.println("function city_assign(obj){");
				out.println(" document.Form1.CITY_CODE.value =oBj.valout[2]");
				out.println(" document.Form1.CITY_DESC.value =oBj.valout[3]");
				out.println("}");
				
				out.println("function mk_supe_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.SUPERVISOR_CODE.value+\"@\"+document.Form1.OFFICER_CODE.value+\"@\";");//
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function mk_supe_assign(oBj){");
				//out.println(" alert(oBj.valout[4]);");
				out.println(" document.Form1.SUPERVISOR_CODE.value =oBj.valout[2]");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");		
				
				//Marketing Officer
				out.println("function mk_officer_help(Start,End,Hid_No,Sql,IfCount){");
				//out.println("Crit=document.Form1.OFFICER_CODE.value+\"@AF@\";");
			  out.println("Crit = document.Form1.OFFICER_CODE.value+\"@Y@\";"); 
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				
			/*	out.println("function mk_officer_assign(oBj){");
				out.println(" document.Form1.OFFICER_CODE.value =oBj.valout[2]");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]"); //Commented by sandun on 06-11-2008
				out.println("}");	
				*/
			out.println("function mk_officer_assign(oBj) {"); //Added By Sandu on 06-11-2008
			out.println("    document.Form1.OFFICER_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.SUPERVISOR_CODE.value=oBj.valout[8];"); 
			out.println("    document.Form1.TEAM.value=oBj.valout[6];"); 
			out.println("}"); 
								
								
				out.println("function client_assign(oBj){");
				out.println(" document.Form1.CLIENT_NAME.value =oBj.valout[3]");
				out.println(" document.Form1.CLIENT_LAST_NAME.value =oBj.valout[4]");
				out.println(" document.Form1.ID_NO.value =oBj.valout[5]");
				out.println(" document.Form1.ADDRESS.value =oBj.valout[6]");
				out.println(" document.Form1.ADDRESS1.value =oBj.valout[7]");
				out.println(" document.Form1.TEL_NO.value =oBj.valout[8]");
				out.println(" document.Form1.MOBILE_NO.value =oBj.valout[9]");
				out.println(" document.Form1.EMAIL.value =oBj.valout[10]");
				//out.println(" alert(oBj.valout[11]);");
				out.println(" document.Form1.CITY_CODE.value =oBj.valout[12]");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");		
				
				
				out.println("function mk_super_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.SUPERVISOR_CODE.value+\"@AF@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function mk_super_assign(oBj){");
				out.println(" document.Form1.SUPERVISOR_CODE.value =oBj.valout[2]");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");		
				
				out.println("function mk_team_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.TEAM.value+\"@\"+document.Form1.OFFICER_CODE.value+\"@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function mk_team_assign(oBj){");
				out.println(" document.Form1.TEAM.value =oBj.valout[2]");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
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
				out.println("Crit=document.Form1.TRANSACTION_SUB.value+\"@\"+document.Form1.TRANSACTION_CODE.value+\"@Y@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function mk_sub_trn_assign(oBj){");
				out.println(" document.Form1.TRANSACTION_SUB.value =oBj.valout[2]");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");		
				
				out.println("function branch_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.BRANCH_CODE.value+\"@Y@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function mk_branch_assign(oBj){");
				out.println(" document.Form1.BRANCH_CODE.value =oBj.valout[2]");
				out.println(" document.Form1.BRANCH_DESC.value =oBj.valout[3]");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");	
				
				out.println("function brk_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.LEAD_SOURCE_NAME.value+\"@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function brk_assign(oBj){");
				out.println(" document.Form1.LEAD_SOURCE_NAME.value =oBj.valout[3]+\" \"+oBj.valout[4]");
				out.println("}");
				
				out.println("function inq_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.INQ_NO.value+\"@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function inq_assign(oBj){");
				out.println(" document.Form1.INQ_NO.value =oBj.valout[2]");
				out.println(" chk_inqno();");
				/*out.println(" alert(oBj.valout[12]+'--'+oBj.valout[10]);
				out.println(" document.Form1.CLIENT_NAME.value =oBj.valout[3]");
				out.println(" document.Form1.TEL_NO.value =oBj.valout[4]");
				out.println(" document.Form1.MOBILE_NO.value =oBj.valout[5]");
				out.println(" document.Form1.ADDRESS.value =oBj.valout[6]");
				out.println(" document.Form1.ADDRESS1.value =oBj.valout[7]");
				out.println(" document.Form1.CITY_CODE.value =oBj.valout[8]");
				out.println(" document.Form1.CLIENT_TYPE.value =oBj.valout[9]");
				out.println(" document.Form1.LEAD_SOURCE_NAME.value =oBj.valout[10]");
				out.println(" document.Form1.ID_NO.value =oBj.valout[11]");
				out.println(" document.Form1.OFFICER_CODE.value =oBj.valout[12]");
				out.println(" document.Form1.CLIENT_CATEGORY.value =oBj.valout[14]");
				out.println(" document.Form1.LEAD_SOURCE_CATEGORY.value =oBj.valout[15]");
				out.println(" document.Form1.INTRODUCER.value =oBj.valout[16]");
				out.println(" document.Form1.EMAIL.value =oBj.valout[17]");
				out.println(" document.Form1.TEAM.value =oBj.valout[18]");
				out.println(" document.Form1.FAX_NO.value =oBj.valout[19]");
				out.println(" document.Form1.SUPERVISOR_CODE.value =oBj.valout[20]");
				out.println(" document.Form1.CONTACT_PERSON.value =oBj.valout[21]");
				out.println(" document.Form1.TRANSACTION_CODE.value =oBj.valout[22]");
				out.println(" document.Form1.TRANSACTION_SUB.value =oBj.valout[23]");
				//out.println(" document.Form1.INQ_NO.value =oBj.valout[2]");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				*/
				out.println("}");		
				
				//End of Help Function
				//change required DIV
				out.println("function change_div(){");
				out.println(" if(document.Form1.CLIENT_TYPE.options[document.Form1.CLIENT_TYPE.selectedIndex].text!='Individual' &&");
				out.println("    document.Form1.CONTACT_PERSON.value==''){ ");
				out.println("   conp.innerHTML=\"Contact Person *\";");
				out.println("   document.Form1.CLIENT_LAST_NAME.disabled=true; ");
				out.println("   document.Form1.TITLE.selectedIndex=0; ");
				out.println("   document.Form1.TITLE.disabled=true; ");
				out.println(" }else{");
				out.println("   conp.innerHTML=\"Contact Person\";");
				out.println("   document.Form1.CLIENT_LAST_NAME.disabled=false; ");
				out.println("   document.Form1.TITLE.disabled=false; ");
				out.println(" }");
				out.println("}");
				
				
				//end of DIV change
				//Main Button Action
				//Submit
				out.println("function befor_submit(){");
				out.println(" m_bsubmit='0';");
				out.println(" if(document.Form1.CLIENT_NAME.value==''){ ");
				out.println("   cus1.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.CLIENT_TYPE.options[document.Form1.CLIENT_TYPE.selectedIndex].value!='INDIVIDUAL' &&");
				out.println("    document.Form1.CONTACT_PERSON.value==''){ ");
				out.println("   conp.style.color=\"red\";");
				out.println("   m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.CLIENT_TYPE.options[document.Form1.CLIENT_TYPE.selectedIndex].value==''){  ");
				out.println("      cty.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.MOBILE_NO.value=='' && ");
				out.println("    document.Form1.TEL_NO.value=='' && ");
				out.println("    document.Form1.ADDRESS.value==''){  ");
				out.println("      cont.style.color=\"red\";");
				out.println("      cont1.style.color=\"red\";");
				out.println("      cont2.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.SUPERVISOR_CODE.value==''){  ");
				out.println("      msupper.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.OFFICER_CODE.value==''){  ");
				out.println("      mofficer.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.TEAM.value==''){  ");
				out.println("      mteam.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.CLIENT_CATEGORY.value==''){  ");
				out.println("      clc.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.LEAD_SOURCE_CATEGORY.value==''){  ");
				out.println("      isc.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");
				out.println(" if(document.Form1.CLIENT_TYPE.value==''){  ");
				out.println("      cty.style.color=\"red\";");
				out.println("      m_bsubmit='1';");
				out.println(" }");

				out.println(" if(m_bsubmit=='0'){");
				//out.println("  if(document.Form1.OPTION_NAME.value==\"NEW\" && document.Form1.INQ_NO.value!=\"\"){ ");
				//out.println("   alert();");
				//out.println("  }else{");
				out.println("   if(document.Form1.OPTION_NAME.value==\"NEW\"){m_val='Save'}else{m_val='Modify' }  ");
				//out.println("   alert(11);");
				out.println("   if(confirm(\"Are you sure you want to \"+m_val+\" ?\")){  ");
				out.println("     document.Form1.action=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Save\";");
				out.println("     document.Form1.submit();");
				out.println("   }");
				//out.println("  }");
				out.println(" }else { ");
		    out.println("   alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
		    out.println(" }");
				out.println("}");
				//end of Submit Function
				
				out.println("function befor_deactive(){");
				out.println(" if(confirm(\"Are you sure?\")){  ");
				//out.println("  Form1.reset()   ");
				out.println("  document.Form1.OPTION_NAME.value=\"INA\";");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_new(){");
				out.println(" if(confirm(\"Are you sure you want to enter new record?\")){  ");
				//out.println("  Form1.reset()   ");
				out.println("  document.Form1.INQ_NO.disabled=true;");
				out.println("  document.Form1.inqu_help.disabled=true;");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println("  assign_null();");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_back(){");
				out.println("   close_window(); ");
				//out.println(" if(confirm(\"Are you sure?\")){  ");
				//out.println("  top.frames[1].location=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_FollowupAlert?chksql=main_page\";");
				//out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
				//out.println(" }  ");
				out.println("}");
				
				out.println("function befor_modify(){");
				out.println(" if(confirm(\"Are you sure you want to modify record?\")){  ");
				//out.println("  Form1.reset()   ");
				out.println("  document.Form1.INQ_NO.disabled=false;");
				out.println("  document.Form1.inqu_help.disabled=false;");
				out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
				out.println("  document.Form1.OPTION_DESC.value=\"Edit\";");
				out.println("  assign_null();");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_active(){");
				out.println(" if(confirm(\"Are you sure?\")){  ");
				//out.println("  Form1.reset()   ");
				out.println("  document.Form1.INQ_NO.disabled=false;");
				out.println("  document.Form1.inqu_help.disabled=false;");
				out.println("  document.Form1.OPTION_NAME.value=\"ACT\";");
				out.println("  document.Form1.OPTION_DESC.value=\"Re-activate\";");
				out.println("  assign_null();");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_deactive(){");
				out.println(" if(confirm(\"Are you sure?\")){  ");
				//out.println("  Form1.reset()   ");
				out.println("  document.Form1.INQ_NO.disabled=false;");
				out.println("  document.Form1.inqu_help.disabled=false;");
				out.println("  document.Form1.OPTION_NAME.value=\"INA\";");
				out.println("  document.Form1.OPTION_DESC.value=\"De-activate\";");
				out.println("  assign_null();");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_reset(){");
				out.println(" if(confirm(\"Are you sure you want to clear the screen?\")){  ");
				//out.println("  Form1.reset()   ");
			  out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Inquiry?chksql=main_page'");
			  out.println(" }  ");
				out.println("}");
				
				
				//end of Main Button Action
				//onload Action
				out.println("function befor_onload(){");
				out.println("  document.Form1.INQ_NO.disabled=true;");
				out.println("  document.Form1.inqu_help.disabled=true;");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println("  load_roll_value(\"New\");"); 
				out.println("  assign_null();");
				out.println("}");
				//---------Added by Prabash on 21-02-2012- for Select Branch ---**
				
				out.println("function load_user_Branch(){"); 
				 rs1 = stmt.executeQuery (" SELECT TO_CHAR(SYSDATE,'DD MONTH YYYY HH24:MI:SS'),USER_ID, "+
                    "        UPPER(NAME), UPPER(EMP_ID),LOCATION_CODE "+
                    " FROM   "+m_schema_name+".CO_CO_MAS_USER "+
                    " WHERE  USER_ID = '"+m_username+"' ");
                boolean more2 = rs1.next(); 
                if(more2){
                    out.println("   document.Form1.BRANCH_CODE.value= '"+rs1.getString(5)+"' "); 
                }
				out.println("}"); 
				out.println(""); 
				//---------------------------------------------------------------**
				
				//end of onload
				//Mouse movement(MM)
				out.println("function load_roll_value(m_val){"); 
			  out.println("help_box.innerHTML=\"Marketing - Inquiry - \"+m_val;"); 
			  out.println("}");
				//end of MM
				
				out.println("function assign_null(oBj){");
				out.println("  document.Form1.INQ_NO.value ='';");
				out.println(" document.Form1.CLIENT_NAME.value =''");
				out.println(" document.Form1.TEL_NO.value =''");
				out.println(" document.Form1.MOBILE_NO.value =''");
				out.println(" document.Form1.ADDRESS.value =''");
				out.println(" document.Form1.ADDRESS1.value =''");
				out.println(" document.Form1.CITY_CODE.selectedIndex =0");
				out.println(" document.Form1.CLIENT_TYPE.selectedIndex =0");
				out.println(" document.Form1.LEAD_SOURCE_NAME.value =''");
				out.println(" document.Form1.ID_NO.value =''");
				out.println(" document.Form1.OFFICER_CODE.value =''");//"+m_username+"
				out.println(" document.Form1.CLIENT_CATEGORY.selectedIndex =0");
				out.println(" document.Form1.LEAD_SOURCE_CATEGORY.selectedIndex =0");
				//out.println(" document.Form1.INTRODUCER.value =''");
				out.println(" document.Form1.EMAIL.value =''");
				out.println(" document.Form1.TEAM.value =''");
				out.println(" document.Form1.FAX_NO.value =''");
				out.println(" document.Form1.SUPERVISOR_CODE.value =''");
				out.println(" document.Form1.CONTACT_PERSON.value =''");
				out.println(" document.Form1.TRANSACTION_CODE.selectedIndex=0");
				out.println(" document.Form1.TRANSACTION_SUB.value =''");
				//out.println(" document.Form1.INQ_NO.value =oBj.valout[2]");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");		
				
				out.println("function chk_count(){"); 
				//out.println(" alert(document.Form1.ID_NO.value.length+'-'+document.Form1.ID_NO.value.substr(document.Form1.ID_NO.value.length-1).toUpperCase());");
			  /*out.println(" if(document.Form1.ID_NO.value.length<10){");
				out.println("   alert('Please Enter Correct ID Number');");
				out.println("   document.Form1.ID_NO.value='';");
				out.println(" }else if(document.Form1.ID_NO.value.substr(document.Form1.ID_NO.value.length-1).toUpperCase()!='V' && ");
				out.println("          document.Form1.ID_NO.value.substr(document.Form1.ID_NO.value.length-1).toUpperCase()!='X' ){");
				out.println("   alert('Please Enter Correct ID Number');");
				out.println("   document.Form1.ID_NO.value='';");
				out.println(" }else if(isNaN(document.Form1.ID_NO.value.substr(0,document.Form1.ID_NO.value.length-1))){");
				out.println("   alert('Please Enter Correct ID Number');");
				out.println("   document.Form1.ID_NO.value='';");
				out.println(" } ");*/
			  out.println("}");
				
				out.println("function load_data(val) {");
				out.println(" pop =	window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Inquiry?chksql=Inquiry_Followup&order_by=ENT_DATE&order_by_type=DESC&officer_code=\"+document.Form1.OFFICER_CODE.value,'inq',\"left=10,top=75,width=980,height=600,scrollBars=1\");"); 
	      out.println("}");
				
				out.println("function load_data_all(val) {");
				out.println(" pop =	window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Inquiry?chksql=Inquiry_Followup_All&order_by=ENT_DATE&order_by_type=DESC\",'inq',\"left=10,top=75,width=980,height=600,scrollBars=1\");"); 
	      out.println("}");
				
				out.println("function HelpBox_msg() {");
	      out.println("  popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MK_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MK_Help_Msg_select\"+\"&help_message_in=inquiry_help\"); ");
        out.println("}");
				
				out.println("</Script>");//asign_mk_team();
			//	out.println("<body onload=\"befor_onload();asign_mk_officer();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");//get_mk_officer(); //Comment by Prabash on 23-03-2012
				out.println("<body onload=\"befor_onload();load_user_Branch();asign_mk_officer();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");//get_mk_officer();
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"Hid_scr_name\" value=\"AF_MK_INQUIRY\">");
				out.println("<input type=hidden name=\"OPTION_NAME\" value=\"NEW\">");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"New\">");
				out.println("<input type=hidden name=\"hid_client_type\" ></td>");
				//out.println("<input type=hidden name=\"INQ_NO\" value=\"\">");
				
				
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
				out.println("<td style=\"width: 6px\">");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset value=\"New\" class=mainbut onclick=befor_new(); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");//document.Form1.OPTION_DESC.value
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=edit value=\"Edit\" class=mainbut onclick=befor_modify(); onMouseOver='load_roll_value(\"Edit\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=report value=\"Report - User\" class=mainbut onclick=load_data(); onMouseOver='load_roll_value(\"Report - User\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=report value=\"Report - All\" class=mainbut onclick=load_data_all(); onMouseOver='load_roll_value(\"Report - All\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel value=\"Re-active\" class=mainbut onclick=befor_active(); onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=b_submit value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=delete value=\"Help\" class=mainbut onclick=HelpBox_msg(); onMouseOver='load_roll_value(\"Help\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Reset\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td ><input type=button name=cal value=\"Calculate\" class=mainbut onclick=befor_cal();></td>");
         /*
				out.println("<td style=\"width: 6px\">");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=b_reset value=\"New\" class=mainbut onclick=befor_new();></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=b_edit value=\"Edit\" class=mainbut onclick=befor_modify();></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=b_delete value=\"Delete\" class=mainbut onclick=befor_deactive();></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel value=\"Re-active\" class=mainbut onclick=befor_active();></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=b_back value=\"Back\" class=mainbut onclick=befor_back();></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=b_submit value=\"Submit\" class=mainbut onclick=befor_submit();></td>");
        */
				out.println("</tr></table>");
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
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
				out.println("<tr class=tr_input>");
				//out.println("<td class=\"txt-bodyGreen\" height=\"18\">Personal Details</td>");
				out.println("<td>Inquiry Number</td>");
				out.println("<td><input name=\"INQ_NO\" type=\"text\" maxlength=\"15\" class=\"txt_input\" onchange=chk_inqno();>");
				out.println("<input type=button name=inqu_help value=... class=\"but_input\" onclick=\"inq_help('1','10','20','InquirySql','7')\"></td>");
				
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td >Initiation Type</td>");
				out.println("<td>");
				out.println("<select name=\"INITIATION_TYPE\" class=\"txt_input\" >");
				
				rs = stmt.executeQuery(CO_methods.getInitiationType(m_schema_name,"Y",""));/*" SELECT INITIATION_CODE Initiation,DESCRIPTION \"Initiation Name\" "+
				                           " FROM   "+m_schema_name+".L_MK_MAS_INITIATION_TYPE "+
																	 " WHERE  ACTIVE_STATUS ='Y' AND INITIATION_CODE LIKE '%%' ");*///CO_methods.getInitiationType(m_schema_name,"Y",""));
							 boolean	more = rs.next();
								while(more){
				           out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
									 more = rs.next();	
								}	
				out.println("</SELECT>");				
				//out.println("<input name=\"INITIATION_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td id=clc>Customer Category</td>");
				out.println("<td>");
				out.println("<select name=\"CLIENT_CATEGORY\" class=\"txt_input\" >");
				rs = stmt.executeQuery(CO_methods.getCustomerCat(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT></td>");
				//out.println("<input name=\"CLIENT_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td id=cty>Customer Type</td>");
				out.println("<td>");
				out.println("<select name=\"CLIENT_TYPE\" class=\"txt_input\" onChange=\"change_div()\">");
				rs = stmt.executeQuery(CO_methods.getCustomerType(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td isc>Lead Source Category</td>");
				out.println("<td>");
				out.println("<select name=\"LEAD_SOURCE_CATEGORY\" class=\"txt_input\" >");
				rs = stmt.executeQuery(CO_methods.getLeadSourceCat(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT>");
				out.println("</td>");
				out.println("<td></td>");
				out.println("<td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Lead Source Name</td>");
				out.println("<td>");
				out.println("<input name=\"LEAD_SOURCE_NAME\" type=\"text\" maxlength=\"50\" class=\"txt_input\" style=\"width:200px;\">");
				out.println("<input type=button name=inqu_help value=... class=\"but_input\" onclick=\"brk_help('1','10','4','BrokerSql','8')\"></td>");
				//
				
				out.println("<td ></td>");//Introducer
				out.println("<td>");
				//out.println("<input name=\"INTRODUCER\" type=\"text\" maxlength=\"50\" class=\"txt_input\" ></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >NIC Number / Business Reg. Number</td>");
				out.println("<td>");
				out.println("<input name=\"ID_NO\" type=\"text\" maxlength=\"10\" class=\"txt_input\"  onchange=chk_count() style=\"width:200px;\">");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Title</td>");
				out.println("<td class=tr_input>");
				out.println("<select name=\"TITLE\" class=\"txt_input\">");
				out.println("<option value=\"-\"    >--</option>");
				out.println("<option value=\"Mr\"   >Mr</option>");
				out.println("<option value=\"Ms\"   >Ms</option>");
				out.println("<option value=\"Miss\" >Miss</option>");
				out.println("<option value=\"Dr\"   >Dr</option>");
				out.println("<option value=\"Prof\" >Prof</option>");
				out.println("<option value=\"Other\">Other</option>");
				out.println("</select>");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td ><div id = cus1 class=div_input>Customer Name *</div></td>");
				out.println("<td >");
				out.println("<input name=\"CLIENT_NAME\" type=\"text\" maxlength=\"200\" class=\"txt_input\" style=\"width:200px;\" >");
				out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"client_help('1','10','0','ClientSql','1')\"></td>");
				out.println("<td><div id=cusl >Customer Last Name </td>");
				out.println("<td>");
				out.println("<input name=\"CLIENT_LAST_NAME\" type=\"text\" maxlength=\"50\" class=\"txt_input\" style=\"width:200px;\"></td>");
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td ><div id=cont2 class=div_input>Address 1 **</td>");
				out.println("<td>");
				out.println("<input name=\"ADDRESS\" type=\"text\" maxlength=\"100\" class=\"txt_input\" style=\"width:200px;\">");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td >Address 2</td>");
				out.println("<td>");
				out.println("<input name=\"ADDRESS1\" type=\"text\" maxlength=\"100\" class=\"txt_input\"  style=\"width:200px;\">");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
			/*	out.println("<tr class=tr_input>");
				out.println("<td >City</td>");
				out.println("<td>");
				out.println("<select name=\"CITY_CODE\" class=\"txt_input\" >");
				rs = stmt.executeQuery(CO_methods.getCity(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT>");
				out.println("</td>");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>"); */
				
				//Added by Chandana on 21/06/2007
				out.println("<tr class=tr_input>");
				out.println("<td ><div id=cus class=div_input>City Code</div></td>");
				out.println("<td >");
				out.println("<input name=\"CITY_CODE\" type=\"text\" maxlength=\"200\" class=\"txt_input\" style=\"width:200px;\" >");
				out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"city_help('1','10','0','CitySql','12')\"></td>");
				out.println("<td>City</td>");
				out.println("<td><input name=\"CITY_DESC\" type=\"text\" maxlength=\"200\" class=\"txt_input\" style=\"width:200px;\" ></td>");
				out.println("</tr>");
				//End on 21/06/2007
				out.println("<tr class=tr_input>");
				out.println("<td><div id=conp >Contact Person </td>");
				out.println("<td>");
				out.println("<input name=\"CONTACT_PERSON\" type=\"text\" maxlength=\"200\" class=\"txt_input\" style=\"width:200px;\"></td>");
				out.println("<td style=\"height: 25px\"><div id=mofficer class=div_input>Marketing Officer Code *</div></td>");
				out.println("<td><input name=\"OFFICER_CODE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" onblur=mk_officer_help('1','10','0','m_help_team_user_id_sql_new','2')>");//chk_mk_officer()
				out.println("<input type=button name=mko_help value=... class=\"but_input\" onclick=\"mk_officer_help('1','10','0','m_help_team_user_id_sql_new','2')\"></td>");//MKOfficerSql--Comment & Add(m_help_team_user_id_sql_new) by Sandun on 03-11-2008
				
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td ><div id=cont class=div_input>Mobile Number **</div></td>");
				out.println("<td>");
				out.println("<input name=\"MOBILE_NO\" type=\"text\" maxlength=\"30\" class=\"txt_input\" ></td>");
				out.println("<td><div id=msupper class=div_input>Supervisor Code *</div></td>");//Marketing Officer
				out.println("<td>");
				out.println("<input name=\"SUPERVISOR_CODE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" >");//onchange=chk_mk_head()
				out.println("<input type=button name=sup_help value=... class=\"but_input\" onclick=\"mk_supe_help('1','10','0','m_help_txt_sub_team_sql','11')\"></td>");
				
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td ><div id=cont1 class=div_input>Contact Number **</td>");
				out.println("<td>");
				out.println("<input name=\"TEL_NO\" type=\"text\" maxlength=\"60\" class=\"txt_input\"  ></td>");
				out.println("<td ><div id=mteam class=div_input>Marketing Team *</div></td>");
				out.println("<td>");
				out.println("<input name=\"TEAM\" type=\"text\" maxlength=\10\" class=\"txt_input\"  ><input name=\"TEAM_CODE\" type=\"hidden\">");//onchange=chk_mk_team() 
				out.println("<input type=button name=tea_help value=... class=\"but_input\" onclick=\"mk_team_help('1','10','0','MKTeamSql','4')\"></td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td >Fax Number</td>");
				out.println("<td>");
				out.println("<input name=\"FAX_NO\" type=\"text\" maxlength=\"10\" class=\"txt_input\"  ></td>");
				out.println("<td >Transaction Type</td>");
				out.println("<td>");
				out.println("<select name=\"TRANSACTION_CODE\" class=\"txt_input\" >");
				rs = stmt.executeQuery(CO_methods.getTransaction(m_schema_name,"Y",""));
				more = rs.next();
				while(more){
				   out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
					 more = rs.next();	
				}	
				out.println("</SELECT>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >E-mail Address</td>");
				out.println("<td>");
				out.println("<input name=\"EMAIL\" type=\"text\" maxlength=\"20\" class=\"txt_input\"  ></td>");
				out.println("<td >Transaction Sub Type</td>");
				out.println("<td>");
				out.println("<input name=\"TRANSACTION_SUB\" type=\"text\" maxlength=\10\" class=\"txt_input\"  onchange=chk_sub_trncode() ><input name=\"TRN_SUB_CODE\" type=\"hidden\">");
				out.println("<input type=button name=trnsub_help value=... class=\"but_input\" onclick=\"sub_trn_help('1','10','0','TrnSubSql','6')\"></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td >Branch Code</td>");
				out.println("<td>");
				out.println("<input name=\"BRANCH_CODE\" type=\"text\" maxlength=\10\" class=\"txt_input\"  onchange=chk_branch() >");
				out.println("<input type=button name=trnsub_help value=... class=\"but_input\" onclick=\"branch_help('1','10','0','BranchSql','9')\"></td>");
				out.println("<td >Branch Description</td>");
				out.println("<td>");
				out.println("<input name=\"BRANCH_DESC\" type=\"text\" maxlength=\10\" class=\"txt_input\"  disabled ></td>");
				out.println("</tr>");
				
				
				//out.println("<tr class=tr_input>");
				//out.println("<input name=\"MARKETING_OFFICER\" type=\"text\" maxlength=\"50\" class=\"txt_input\"  DISABLED>");
				//out.println("</tr>");
				/*out.println("<tr class=tr_input>");
				out.println("<td >Marketing Supervisor Code<div id=msupper class=div_input></td>");
				out.println("<td><input name=\"SUPERVISOR_CODE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" onclick=chk_mk_super()>");
				out.println("<input type=button name=sup_help value=Help class=\"but_input\" onclick=\"mk_super_help('1','10','0','MKSuperSql','3')\"></td>");
				out.println("<td ></td>");//Marketing Supervisor
				out.println("<td>");
				//out.println("<input name=\"SUPERVISOR\" type=\"text\" maxlength=\"50\" class=\"txt_input\" DISABLED ></td>");
				out.println("</tr>");*/
				//out.println("<tr class=tr_input>");
				//out.println("</tr>");
				//out.println("<tr class=tr_input>");
				//out.println("<td ></td>");
				//out.println("<td>");
				//out.println("</td>");
				//out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td COLSPAN=2>&nbsp;</td>");
				
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td COLSPAN=2>** One of these filed is required.</td>");
				
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				
				/*out.println("<td >Country</td>");
				out.println("<td>");
				out.println("<select name=\"COUNTRY\" class=\"txt_input\" >");
				out.println("<option value=\"14\">UNITED KINGDOM </option>");
				out.println("</select></td>");*/
				/*out.println("<tr>");
				out.println("<td >Comp download count</td>");
				out.println("<td valign=\"middle\">");
				out.println("<input name=\"txtCompDownload\" type=\"text\" value=\"0\" maxlength=\"100\" id=\"txtCompDownload\" class=\"txt_input\"  >");
				out.println("<td >");
				out.println("</td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>  ");*/             
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
				
				out.println("<td style=\"width: 6px\">");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=new_1 value=\"New\" class=mainbut onclick=befor_new(); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");//document.Form1.OPTION_DESC.value
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=edit_1 value=\"Edit\" class=mainbut onclick=befor_modify(); onMouseOver='load_roll_value(\"Edit\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=report_1 value=\"Report - User\" class=mainbut onclick=load_data(); onMouseOver='load_roll_value(\"Report - User\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=report1_1 value=\"Report - All\" class=mainbut onclick=load_data_all(); onMouseOver='load_roll_value(\"Report - All\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=delete value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel value=\"Re-active\" class=mainbut onclick=befor_active(); onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td ><input type=button name=b_submit_1 value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=delete_1 value=\"Help\" class=mainbut onclick=HelpBox_msg(); onMouseOver='load_roll_value(\"Help\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back_1 value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset_1 value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Reset\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
					
				
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
			else if(m_chksql.trim().equals("chkCity")){
			  rs = stmt.executeQuery("SELECT A.CITY_CODE, A.CITY_DESC, B.DESCRIPTION "+
															 "FROM   "+m_schema_name+".AF_CO_MAS_CITY A,"+
															 "       "+m_schema_name+".AF_CO_MAS_POSTAL_CODES B"+
															 "WHERE  A.CITY_CODE = B.CITY_CODE AND A.ACTIVE_STATUS='Y' AND "+
                               "       B.ACTIVE_STATUS='Y'");
				boolean more = rs.next();

				if(more){
				   out.println(rs.getString(1)+"@#"+rs.getString(2)+"@#"+rs.getString(3)+"@##@");
				}	    
						
			}
			else if(m_chksql.trim().equals("chkOfficer")){
			  String officer = req.getParameter("officer");
				
			  rs = stmt.executeQuery(" SELECT A.USER_ID "+
														   " FROM   "+m_schema_name+".CO_CO_MAS_USER A "+  
															 " WHERE  A.USER_ID='"+officer+"' AND ACTIVE_STATUS='Y'");
								
									
				boolean more = rs.next();

				if(more){
				   out.println(rs.getString(1));
				}	    
			}
			else if(m_chksql.trim().equals("getOfficer")){
			  String User_name=req.getParameter("user_name");			
			
			/*
			rs = stmt.executeQuery(" SELECT A.USER_ID "+ 
														   " FROM   "+m_schema_name+".CO_CO_MAS_USER A "+
															 " WHERE  A.USER_ID='"+User_name+"' AND ACTIVE_STATUS='Y'");
																
					*/
					 rs = stmt.executeQuery(" SELECT EMP_CODE "+
					 												" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE "+
																	" WHERE FIRST_NAME = UPPER('"+User_name+"') AND ACTIVE_STATUS='Y' ");								
					
					
				boolean more = rs.next();

				if(more){
				   out.println(rs.getString(1));
				}	    
						
			}
			else if(m_chksql.trim().equals("getTeam")){
			  String User_name=req.getParameter("user_name");
			
			  rs = stmt.executeQuery(" SELECT A.TEAM_ID, A.TEAM_HEAD "+
				                       " FROM   "+m_schema_name+".AF_CO_MAS_TEAMS A "+
															 " WHERE  TEAM_ID =(SELECT A.TEAM_ID "+
															 "                  FROM   "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS A "+
															 "                  WHERE  USER_ID ='"+User_name+"' ) AND "+
															 "        A.ACTIVE_STATUS='Y'");
				boolean more = rs.next();

				if(more){
				   out.println(rs.getString(1)+"@#"+rs.getString(2));
				}	    
						
			}
			else if(m_chksql.trim().equals("Inquiry_Followup")){
			  String order_by			 = req.getParameter("order_by");
			  String order_by_type = req.getParameter("order_by_type");
				String officer_code = req.getParameter("officer_code");
			     
        out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				out.println("var m_bsubmit = '0';");
				out.println("var arr_assign= new Array();");
				out.println("var m_send_val= '';");
				
				
        
				
				out.println("function befor_new(){");
				out.println(" if(confirm(\"Are you sure you want to enter new record?\")){  ");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_back(){");// Modified by Mahela on 17-05-2007
				out.println(" if(confirm(\"Are you sure you want to close the screen? \")){  ");
				out.println("  window.close()");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_modify(){");
				out.println(" if(confirm(\"Are you sure you want to modify record?\")){  ");
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
				
		
				
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
				
				//end of Main Button Action
				//onload Action
				
				out.println("function befor_onload(){");
				/*out.println("  document.Form1.PRICE_NO.disabled=true;");
				out.println("  document.Form1.inqu_help.disabled=true;");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println("  load_roll_value(\"New\");"); */
				out.println("}");
				//end of onload
				
				//---------Added by Prabash on 21-02-2012- for Select Branch ---**
				
				out.println("function load_user_Branch(){"); 
				 rs1 = stmt.executeQuery (" SELECT TO_CHAR(SYSDATE,'DD MONTH YYYY HH24:MI:SS'),USER_ID, "+
                    "        UPPER(NAME), UPPER(EMP_ID),LOCATION_CODE "+
                    " FROM   "+m_schema_name+".CO_CO_MAS_USER "+
                    " WHERE  USER_ID = '"+m_username+"' ");
                boolean more2 = rs1.next(); 
                if(more2){
                    out.println("   document.Form1.BRANCH_CODE.value= '"+rs1.getString(5)+"' "); 
                }
				out.println("}"); 
				out.println(""); 
				//---------------------------------------------------------------**
				
				out.println("function load_roll_value(m_val){"); 
				out.println("if(m_val==''){");
				out.println("help_box.innerHTML=\"Marketing  - Inquiry Follow up Report\";"); 
			  out.println("}else{");
				out.println("help_box.innerHTML=\"Marketing  - Inquiry Follow up Report - \"+m_val;"); 
			  out.println("}");
				out.println("}");
				
				
					//End Of Checking Values
				
						
			  out.println("function load_data(val) {");
				out.println("	 order_by_type = 'ASC'; ");  
				out.println("	 if(val=='"+order_by+"'){");
				out.println("	   if('"+order_by_type+"'=='DESC'){");
				out.println("	      order_by_type = 'ASC'; ");  
				out.println("    }else{");
			  out.println("       order_by_type = 'DESC'; ");
			  out.println("    }");
			  out.println("  }else{");
			  out.println("    order_by_type = 'ASC'; ");
			  out.println("  }");
			  out.println("	window.location=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Inquiry?chksql=Inquiry_Followup&order_by=\"+val+\"&order_by_type=\"+order_by_type+\"\";"); 
	      //AF_MK_Inquiry?chksql=Inquiry_Followup&order_by=ENT_DATE&order_by_type=DESC
				//out.println("	popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Documents?chksql=get_documents&deal_no=\"+num+\"\", \"oBj\",\"left=150,top=280,width=520,height=290\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
			
			  out.println("function load_inq_data(val) {");
				//out.println("	window.location=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Inquiry?chksql=Inquiry_Followup&order_by=\"+val+\"&order_by_type=\"+order_by_type+\"\";"); 
	      //AF_MK_Inquiry?chksql=Inquiry_Followup&order_by=ENT_DATE&order_by_type=DESC
				out.println("	popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Inquiry?chksql=Inquiry_det&deal_no=\"+val+\"\", \"oBj\",\"left=150,top=280,width=520,height=290\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
			
				out.println("function load_fol_data(val) {");
				//out.println("	window.location=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Inquiry?chksql=Inquiry_Followup&order_by=\"+val+\"&order_by_type=\"+order_by_type+\"\";"); 
	      //AF_MK_Inquiry?chksql=Inquiry_Followup&order_by=ENT_DATE&order_by_type=DESC
				out.println("	popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Inquiry?chksql=Followup_det&deal_no=\"+val+\"\", \"oBj\",\"left=150,top=280,width=520,height=290\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
			
        out.println("</Script>");
			//	out.println("<body onload=\"befor_onload();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">"); Coment by Prabash on 23-03-2012
				out.println("<body onload=\"befor_onload();load_user_Branch();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
        out.println("<input type=hidden name=\"ROW_ID\" ></td>");
        out.println("<input type=hidden name=\"order_by\" value=\""+order_by+"\"></td>");
        out.println("<input type=hidden name=\"order_by_type\" value=\""+order_by_type+"\"></td>");
                  
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
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Marketing  - Inquiry Follow up Report</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  class=\"pdn_txtpos\" style=\"height: 28px\">");
				out.println("<table class=table border=\"0\">");
				out.println("<tr>");

				//out.println("<td width=\"40%\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td ><center><input type=button name=back     value=\"Close\"     class=mainbut onclick=befor_back();     onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></center></td>");
				out.println("</tr>");
				//out.println("<td ><input type=button name=cal     value=\"Calculate\" class=mainbut onclick=befor_cal(\"NO\",\"YES\");     onMouseOver='load_roll_value(\"Calculate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 /*
	rs = stmt.executeQuery (" SELECT TO_CHAR(SYSDATE,'DD MONTH YYYY HH24:MI:SS'),USER_ID, "+
				                        "        NAME, EMP_ID,"+m_schema_name+".AF_CO_GET_LOCATION_DESC(LOCATION_CODE),   "+
																"        "+m_schema_name+".AF_CO_GET_DIVISION_DESC(DIVISION_CODE),NVL("+m_schema_name+".AF_CO_GET_DESIGNATION_DESC(DESIGNATION_CODE),'-') "+
																" FROM   "+m_schema_name+".CO_CO_MAS_USER "+
																" WHERE  USER_ID = '"+m_username+"' ");
        boolean more = rs.next(); 
        if(more){
	
   				out.println("<td class=\"pdn_txtpos\" ><b>OUTSTANDING ACTIVITY REPORT AS AT "+rs.getString(1)+" </b></td>");
				  out.println("</tr>");
				  out.println("<tr class=tr_input>");
				  out.println("<td class=\"pdn_txtpos\"><b>LOGGED USER : "+m_username+" ("+rs.getString(4)+" / "+rs.getString(5)+" / "+rs.getString(6)+" / "+rs.getString(7)+" )</b></td></tr>");
				}*/
        out.println("</table>");
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
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
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
				out.println("<tr class=tr_input>");
				out.println("<td valign=top  width=100% Id=Follow_up> ");
				
				  
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=12 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					//Modified by Mahela on 17-05-2007
					rs = stmt.executeQuery(" SELECT INQUIRY_CODE,TO_CHAR(ENT_DATE,'DD-MM-YYYY'),  "+
				                       "        NVL("+m_schema_name+".AF_CO_GET_CLIENT_CAT_DESC(CLIENT_CATEGORY),'-'), "+
															 "        NVL("+m_schema_name+".AF_CO_GET_TRAN_TYPE_DESC(SUB_PRODUCT_CODE),'-'), "+
															 "      	NVL(CLIENT_NAME,'-'),NVL(MK_OFFICER,'-'),NVL(MK_SUPERVISOR,'-'), "+
															 "      	NVL("+m_schema_name+".AF_CO_GET_TEAM_DESC(TEAM),'-'), "+
															 "      	NVL(TO_CHAR("+m_schema_name+".AF_CO_GET_FOLL_LAST_NEXT_DATE(INQUIRY_CODE,'LAST'),'DD-MM-YYYY'),'-'), "+ 
															 "      	NVL(TO_CHAR("+m_schema_name+".AF_CO_GET_FOLL_LAST_NEXT_DATE(INQUIRY_CODE,'NEXT'),'DD-MM-YYYY'),'-'), "+ 
															 "        NVL(TEL_NO,'-')||'/'||NVL(MOBILE_NO,'-')||'/'||NVL(ADDRESS,'-'), "+
															 "        NVL(ADDRESS2,'-'), INQUIRY_STATUS,CITY_CODE,NVL("+m_schema_name+".AF_CO_GET_EGAL_ENTITY_DESC(LEGAL_ENTITY),'-'), "+
															 "        STATUS, INITIATION_TYPE,CLIENT_TYPE, "+
															 "      	NVL(LEAD_SOURCE_CATEGORY,'-'),NVL(LEAD_SOURCE_NAME,'-'),NVL(INTRODUCER,'-'), "+
															 "      	NVL(ID_NO,'-'), NVL("+m_schema_name+".AF_CO_GET_TRAN_SUB_TYPE_DESC(TRANSACTION_SUB_TYPE),'-'),  "+
															 "        EMAIL, CONTACT_PERSON, ENT_USER, MOD_USER,MOD_DATE "+
															 " FROM   "+m_schema_name+".AF_MK_PRO_INQUIRY "+
															 " WHERE  MK_OFFICER = '"+officer_code+"' "+	
															 " ORDER BY "+order_by+" "+order_by_type+" ");
				boolean more = rs.next();

				if(more){
          out.println("<tr class=\"pdn_txtpos2 & hs\">");
					out.println("<td  width='10%' onclick=load_data('INQUIRY_CODE')>Inquiry No</td>");
          out.println("<td  width='8%' onclick=load_data('ENT_DATE')>Ent Date</td>");
          out.println("<td  width='8%' onclick=''                   >Last Date</td>");
          out.println("<td  width='8%' onclick=''                   >Next Date</td>");
          out.println("<td  width='8%' onclick=load_data('CLIENT_CATEGORY')>Inquiry Category</td>");
					out.println("<td  width='10%' onclick=load_data('SUB_PRODUCT_CODE')>Transaction Type</td>");
					out.println("<td  width='20%' onclick=load_data('CLIENT_NAME')>Name</td>");
					out.println("<td  width='10%' onclick=load_data('MK_OFFICER')>Officer</td>");
					out.println("<td  width='8%' onclick=load_data('MK_SUPERVISOR')>Supervisor</td>");
					out.println("<td  width='8%' onclick=load_data('TEAM')>Team</td>");
					out.println("<td  width='2%' onclick=''>Inq</td>");
					out.println("<td  width='3%' onclick=''>Fol</td>");
					//out.println("<td  width='11%' onclick=load_data('TEL_NO||MOBILE_NO||ADDRESS')>Contact Details</td>");
					out.println("</tr>");
					 
           int j = 0;      					
							

              while(more){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input>");
									out.println("<td >"+rs.getString(1) +"</td>");//onclick=load_data('"+rs.getString(2)+"');
                  out.println("<td >"+rs.getString(2) +"</td>");
                  out.println("<td >"+rs.getString(9) +"</td>");
                  out.println("<td >"+rs.getString(10)+"</td>");
                  out.println("<td >"+rs.getString(3) +"</td>");
                  out.println("<td >"+rs.getString(4) +"</td>");
                  out.println("<td >"+rs.getString(5) +"</td>");
                  out.println("<td >"+rs.getString(6) +"</td>");
                  out.println("<td >"+rs.getString(7) +"</td>");
                  out.println("<td >"+rs.getString(8) +"</td>");
                  out.println("<td onclick=load_inq_data('"+rs.getString(1) +"') class=hs>Inq</td>");
					        out.println("<td onclick=load_fol_data('"+rs.getString(1) +"') class=hs>Fol</td>");
					        out.println("</tr>");
									more=rs.next();
									if(more){
										out.println("<tr class=tr_input1>");
										out.println("<td >"+rs.getString(1) +"</td>");//onclick=load_data('"+rs.getString(2)+"');
	                  out.println("<td >"+rs.getString(2) +"</td>");
	                  out.println("<td >"+rs.getString(9) +"</td>");
	                  out.println("<td >"+rs.getString(10)+"</td>");
	                  out.println("<td >"+rs.getString(3) +"</td>");
	                  out.println("<td >"+rs.getString(4) +"</td>");
	                  out.println("<td >"+rs.getString(5) +"</td>");
	                  out.println("<td >"+rs.getString(6) +"</td>");
	                  out.println("<td >"+rs.getString(7) +"</td>");
	                  out.println("<td >"+rs.getString(8) +"</td>");
	                  out.println("<td onclick=load_inq_data('"+rs.getString(1) +"') class=hs>Inq</td>");
					          out.println("<td onclick=load_fol_data('"+rs.getString(1) +"') class=hs>Fol</td>");
					          out.println("</tr>");
										
	                	more=rs.next();
									}
              }
          //}
          
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=12><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("</tr>");
					}
          out.println("</table>");
          out.println("</td>");
			
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("</html>");
					    
						
			}
			
			
			
			
			 else if(m_chksql.trim().equals("Inquiry_Followup_All")){
			  String order_by			 = req.getParameter("order_by");
			  String order_by_type = req.getParameter("order_by_type");
			     
        out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				out.println("var m_bsubmit = '0';");
				out.println("var arr_assign= new Array();");
				out.println("var m_send_val= '';");
				
				
        
				
				out.println("function befor_new(){");
				out.println(" if(confirm(\"Are you sure you want to enter new record?\")){  ");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_back(){");
				out.println(" if(confirm(\"Are you sure you want to close the screen? \")){  ");
				out.println("  window.close()");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_modify(){");
				out.println(" if(confirm(\"Are you sure you want to enter modify record?\")){  ");
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
				
		
				
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
				
				//end of Main Button Action
				//onload Action
				
				out.println("function befor_onload(){");
				/*out.println("  document.Form1.PRICE_NO.disabled=true;");
				out.println("  document.Form1.inqu_help.disabled=true;");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println("  load_roll_value(\"New\");"); */
				out.println("}");
				//end of onload
				
				//---------Added by Prabash on 21-02-2012- for Select Branch ---**
				
				out.println("function load_user_Branch(){"); 
				 rs1 = stmt.executeQuery (" SELECT TO_CHAR(SYSDATE,'DD MONTH YYYY HH24:MI:SS'),USER_ID, "+
                    "        UPPER(NAME), UPPER(EMP_ID),LOCATION_CODE "+
                    " FROM   "+m_schema_name+".CO_CO_MAS_USER "+
                    " WHERE  USER_ID = '"+m_username+"' ");
                boolean more2 = rs1.next(); 
                if(more2){
                    out.println("   document.Form1.BRANCH_CODE.value= '"+rs1.getString(5)+"' "); 
                }
				out.println("}"); 
				out.println(""); 
				//---------------------------------------------------------------**
				
				out.println("function load_roll_value(m_val){"); 
				out.println("if(m_val==''){");
				out.println("help_box.innerHTML=\"Marketing  - Inquiry Follow up Report\";"); 
			  out.println("}else{");
				out.println("help_box.innerHTML=\"Marketing  - Inquiry Follow up Report - \"+m_val;"); 
			  out.println("}");
				out.println("}");
				
				
					//End Of Checking Values
				
						
			  out.println("function load_data(val) {");
				out.println("	 order_by_type = 'ASC'; ");  
				out.println("	 if(val=='"+order_by+"'){");
				out.println("	   if('"+order_by_type+"'=='DESC'){");
				out.println("	      order_by_type = 'ASC'; ");  
				out.println("    }else{");
			  out.println("       order_by_type = 'DESC'; ");
			  out.println("    }");
			  out.println("  }else{");
			  out.println("    order_by_type = 'ASC'; ");
			  out.println("  }");
			  out.println("	window.location=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Inquiry?chksql=Inquiry_Followup_All&order_by=\"+val+\"&order_by_type=\"+order_by_type+\"\";"); 
	      //AF_MK_Inquiry?chksql=Inquiry_Followup&order_by=ENT_DATE&order_by_type=DESC
				//out.println("	popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Documents?chksql=get_documents&deal_no=\"+num+\"\", \"oBj\",\"left=150,top=280,width=520,height=290\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
			
			  out.println("function load_inq_data(val) {");
				//out.println("	window.location=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Inquiry?chksql=Inquiry_Followup&order_by=\"+val+\"&order_by_type=\"+order_by_type+\"\";"); 
	      //AF_MK_Inquiry?chksql=Inquiry_Followup&order_by=ENT_DATE&order_by_type=DESC
				out.println("	popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Inquiry?chksql=Inquiry_det&deal_no=\"+val+\"\", \"oBj\",\"left=150,top=280,width=520,height=290\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
			
				out.println("function load_fol_data(val) {");
				//out.println("	window.location=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Inquiry?chksql=Inquiry_Followup&order_by=\"+val+\"&order_by_type=\"+order_by_type+\"\";"); 
	      //AF_MK_Inquiry?chksql=Inquiry_Followup&order_by=ENT_DATE&order_by_type=DESC
				out.println("	popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Inquiry?chksql=Followup_det&deal_no=\"+val+\"\", \"oBj\",\"left=150,top=280,width=520,height=290\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
			
        out.println("</Script>");
			//	out.println("<body onload=\"befor_onload();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">"); //Comment by Prabash on 23-03-2012
				out.println("<body onload=\"befor_onload();load_user_Branch();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
        out.println("<input type=hidden name=\"ROW_ID\" ></td>");
        out.println("<input type=hidden name=\"order_by\" value=\""+order_by+"\"></td>");
        out.println("<input type=hidden name=\"order_by_type\" value=\""+order_by_type+"\"></td>");
                  
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
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Marketing  - Inquiry Follow up Report</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  class=\"pdn_txtpos\" style=\"height: 28px\">");
				out.println("<table class=table border=\"0\">");
				out.println("<tr>");

				//out.println("<td width=\"40%\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td ><center><input type=button name=back     value=\"Close\"     class=mainbut onclick=befor_back();     onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></center></td>");
				out.println("</tr>");
				//out.println("<td ><input type=button name=cal     value=\"Calculate\" class=mainbut onclick=befor_cal(\"NO\",\"YES\");     onMouseOver='load_roll_value(\"Calculate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 /*
	rs = stmt.executeQuery (" SELECT TO_CHAR(SYSDATE,'DD MONTH YYYY HH24:MI:SS'),USER_ID, "+
				                        "        NAME, EMP_ID,"+m_schema_name+".AF_CO_GET_LOCATION_DESC(LOCATION_CODE),   "+
																"        "+m_schema_name+".AF_CO_GET_DIVISION_DESC(DIVISION_CODE),NVL("+m_schema_name+".AF_CO_GET_DESIGNATION_DESC(DESIGNATION_CODE),'-') "+
																" FROM   "+m_schema_name+".CO_CO_MAS_USER "+
																" WHERE  USER_ID = '"+m_username+"' ");
        boolean more = rs.next(); 
        if(more){
	
   				out.println("<td class=\"pdn_txtpos\" ><b>OUTSTANDING ACTIVITY REPORT AS AT "+rs.getString(1)+" </b></td>");
				  out.println("</tr>");
				  out.println("<tr class=tr_input>");
				  out.println("<td class=\"pdn_txtpos\"><b>LOGGED USER : "+m_username+" ("+rs.getString(4)+" / "+rs.getString(5)+" / "+rs.getString(6)+" / "+rs.getString(7)+" )</b></td></tr>");
				}*/
        out.println("</table>");
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
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
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
				out.println("<tr class=tr_input>");
				out.println("<td valign=top  width=100% Id=Follow_up> ");
				
				  
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=12 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					//Modified by Mahela on 17-05-2007
					rs = stmt.executeQuery(" SELECT INQUIRY_CODE,TO_CHAR(ENT_DATE,'DD-MM-YYYY'),  "+
				                       "        NVL("+m_schema_name+".AF_CO_GET_CLIENT_CAT_DESC(CLIENT_CATEGORY),'-'), "+
															 "        NVL("+m_schema_name+".AF_CO_GET_TRAN_TYPE_DESC(SUB_PRODUCT_CODE),'-'), "+
															 "      	NVL(CLIENT_NAME,'-'),NVL(MK_OFFICER,'-'),NVL(MK_SUPERVISOR,'-'), "+
															 "      	NVL("+m_schema_name+".AF_CO_GET_TEAM_DESC(TEAM),'-'), "+
															 "      	NVL(TO_CHAR("+m_schema_name+".AF_CO_GET_FOLL_LAST_NEXT_DATE(INQUIRY_CODE,'LAST'),'DD-MM-YYYY'),'-'), "+ 
															 "      	NVL(TO_CHAR("+m_schema_name+".AF_CO_GET_FOLL_LAST_NEXT_DATE(INQUIRY_CODE,'NEXT'),'DD-MM-YYYY'),'-'), "+ 
															 "        NVL(TEL_NO,'-')||'/'||NVL(MOBILE_NO,'-')||'/'||NVL(ADDRESS,'-'), "+
															 "        NVL(ADDRESS2,'-'), INQUIRY_STATUS,CITY_CODE,"+m_schema_name+".AF_CO_GET_EGAL_ENTITY_DESC(LEGAL_ENTITY), "+
															 "        STATUS, INITIATION_TYPE,CLIENT_TYPE, "+
															 "      	LEAD_SOURCE_CATEGORY,LEAD_SOURCE_NAME,INTRODUCER, "+
															 "      	ID_NO, NVL("+m_schema_name+".AF_CO_GET_TRAN_SUB_TYPE_DESC(TRANSACTION_SUB_TYPE),'-'),  "+
															 "        EMAIL, CONTACT_PERSON, ENT_USER, MOD_USER,MOD_DATE "+
															 " FROM   "+m_schema_name+".AF_MK_PRO_INQUIRY "+
															 //" WHERE  MK_OFFICER = '"+m_username+"' "+	
															 " ORDER BY "+order_by+" "+order_by_type+" ");
				boolean more = rs.next();

				if(more){
          out.println("<tr class=\"pdn_txtpos2 & hs\">");
					out.println("<td  width='10%' onclick=load_data('INQUIRY_CODE')>Inquiry No</td>");
          out.println("<td  width='8%' onclick=load_data('ENT_DATE')>Ent Date</td>");
          out.println("<td  width='8%' onclick=''                   >Last Date</td>");
          out.println("<td  width='8%' onclick=''                   >Next Date</td>");
          out.println("<td  width='8%' onclick=load_data('CLIENT_CATEGORY')>Inquiry Category</td>");
					out.println("<td  width='10%' onclick=load_data('SUB_PRODUCT_CODE')>Transaction Type</td>");
					out.println("<td  width='20%' onclick=load_data('CLIENT_NAME')>Name</td>");
					out.println("<td  width='10%' onclick=load_data('MK_OFFICER')>Officer</td>");
					out.println("<td  width='8%' onclick=load_data('MK_SUPERVISOR')>Supervisor</td>");
					out.println("<td  width='8%' onclick=load_data('TEAM')>Team</td>");
					out.println("<td  width='2%' onclick=''>Inq</td>");
					out.println("<td  width='3%' onclick=''>Fol</td>");
					//out.println("<td  width='11%' onclick=load_data('TEL_NO||MOBILE_NO||ADDRESS')>Contact Details</td>");
					out.println("</tr>");
					 
           int j = 0;      					
							

              while(more){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input>");
									out.println("<td >"+rs.getString(1) +"</td>");//onclick=load_data('"+rs.getString(2)+"');
                  out.println("<td >"+rs.getString(2) +"</td>");
                  out.println("<td >"+rs.getString(9) +"</td>");
                  out.println("<td >"+rs.getString(10)+"</td>");
                  out.println("<td >"+rs.getString(3) +"</td>");
                  out.println("<td >"+rs.getString(4) +"</td>");
                  out.println("<td >"+rs.getString(5) +"</td>");
                  out.println("<td >"+rs.getString(6) +"</td>");
                  out.println("<td >"+rs.getString(7) +"</td>");
                  out.println("<td >"+rs.getString(8) +"</td>");
                  out.println("<td onclick=load_inq_data('"+rs.getString(1) +"') class=hs>Inq</td>");
					        out.println("<td onclick=load_fol_data('"+rs.getString(1) +"') class=hs>Fol</td>");
					        out.println("</tr>");
									more=rs.next();
									if(more){
										out.println("<tr class=tr_input1>");
										out.println("<td >"+rs.getString(1) +"</td>");//onclick=load_data('"+rs.getString(2)+"');
	                  out.println("<td >"+rs.getString(2) +"</td>");
	                  out.println("<td >"+rs.getString(9) +"</td>");
	                  out.println("<td >"+rs.getString(10)+"</td>");
	                  out.println("<td >"+rs.getString(3) +"</td>");
	                  out.println("<td >"+rs.getString(4) +"</td>");
	                  out.println("<td >"+rs.getString(5) +"</td>");
	                  out.println("<td >"+rs.getString(6) +"</td>");
	                  out.println("<td >"+rs.getString(7) +"</td>");
	                  out.println("<td >"+rs.getString(8) +"</td>");
	                  out.println("<td onclick=load_inq_data('"+rs.getString(1) +"') class=hs>Inq</td>");
					          out.println("<td onclick=load_fol_data('"+rs.getString(1) +"') class=hs>Fol</td>");
					          out.println("</tr>");
										
	                	more=rs.next();
									}
              }
          //}
          
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=12><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("</tr>");
					}
          out.println("</table>");
          out.println("</td>");
			
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("</html>");
					    
						
			}
			else if(m_chksql.trim().equals("Inquiry_det")){
			  String deal_no			 = req.getParameter("deal_no");
			     
        out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				out.println("var m_bsubmit = '0';");
				out.println("var arr_assign= new Array();");
				out.println("var m_send_val= '';");
				
				
        
				
				out.println("function befor_new(){");
				out.println(" if(confirm(\"Are you sure you want to enter new record?\")){  ");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_back(){");
				out.println("  window.close()");
				out.println("}");
				
				out.println("function befor_modify(){");
				out.println(" if(confirm(\"Are you sure you want to modify record?\")){  ");
				//out.println("  Form1.reset();   ");
				out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
				out.println("  document.Form1.OPTION_DESC.value=\"Edit\";");
				//out.println("  alert(document.Form1.OPTION_DESC.value);");
				out.println("  document.Form1.PRICE_NO.disabled=false;");
				out.println("  document.Form1.inqu_help.disabled=false;");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
				
				//end of Main Button Action
				//onload Action
				
				out.println("function befor_onload(){");
				/*out.println("  document.Form1.PRICE_NO.disabled=true;");
				out.println("  load_roll_value(\"New\");"); */
				out.println("}");
				//end of onload
				
				//---------Added by Prabash on 21-02-2012- for Select Branch ---**
				
				out.println("function load_user_Branch(){"); 
				 rs1 = stmt.executeQuery (" SELECT TO_CHAR(SYSDATE,'DD MONTH YYYY HH24:MI:SS'),USER_ID, "+
                    "        UPPER(NAME), UPPER(EMP_ID),LOCATION_CODE "+
                    " FROM   "+m_schema_name+".CO_CO_MAS_USER "+
                    " WHERE  USER_ID = '"+m_username+"' ");
                boolean more2 = rs1.next(); 
                if(more2){
                    out.println("   document.Form1.BRANCH_CODE.value= '"+rs1.getString(5)+"' "); 
                }
				out.println("}"); 
				out.println(""); 
				//---------------------------------------------------------------**
				
				
				out.println("function load_roll_value(m_val){"); 
				out.println("if(m_val==''){");
				out.println("help_box.innerHTML=\"Marketing  - Inquiry Follow up Report\";"); 
			  out.println("}else{");
				out.println("help_box.innerHTML=\"Marketing  - Inquiry Follow up Report - \"+m_val;"); 
			  out.println("}");
				out.println("}");
				
				
					//End Of Checking Values
				
				
        out.println("</Script>");
			//	out.println("<body onload=\"befor_onload();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">"); //Comment by Prabash on 23-03-2012
				out.println("<body onload=\"befor_onload();load_user_Branch();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
        out.println("<input type=hidden name=\"ROW_ID\" ></td>");
        //out.println("<input type=hidden name=\"order_by\" value=\""+order_by+"\"></td>");
        //out.println("<input type=hidden name=\"order_by_type\" value=\""+order_by_type+"\"></td>");
                  
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
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Marketing  - Inquiry Follow up Report</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  class=\"pdn_txtpos\" style=\"height: 28px\">");
				out.println("<table class=table border=\"0\">");
				out.println("<tr>");

				//out.println("<td width=\"40%\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td ><center><input type=button name=back     value=\"Close\"     class=mainbut onclick=befor_back();     onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></center></td>");
				out.println("</tr>");
				//out.println("<td ><input type=button name=cal     value=\"Calculate\" class=mainbut onclick=befor_cal(\"NO\",\"YES\");     onMouseOver='load_roll_value(\"Calculate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");

        out.println("</table>");
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
				
				out.println("</table>");
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
				out.println("<tr class=tr_input>");
				out.println("<td valign=top  width=100% Id=Follow_up> ");
				
				  
					out.println("<table class=table border='0' width='100%' >");
					
					rs = stmt.executeQuery(" SELECT INQUIRY_CODE,CLIENT_NAME,NVL(TEL_NO,'-')||'/'||NVL(MOBILE_NO,'-')||'/'||NVL(ADDRESS,'-'), "+
															 "        ADDRESS2, INQUIRY_STATUS,CITY_CODE,"+m_schema_name+".AF_CO_GET_EGAL_ENTITY_DESC(LEGAL_ENTITY), "+
															 "        STATUS, INITIATION_TYPE,CLIENT_TYPE, "+
															 "      	LEAD_SOURCE_CATEGORY,LEAD_SOURCE_NAME,INTRODUCER, "+
															 "      	ID_NO, "+m_schema_name+".AF_CO_GET_TRAN_SUB_TYPE_DESC(TRANSACTION_SUB_TYPE),  "+
															 "        EMAIL, CONTACT_PERSON, ENT_USER, MOD_USER,MOD_DATE "+
															 " FROM   "+m_schema_name+".AF_MK_PRO_INQUIRY "+
															 " WHERE  INQUIRY_CODE = UPPER('"+deal_no+"') "+	
															 " ORDER BY INQUIRY_CODE");
				boolean more = rs.next();

				if(more){
          out.println("<tr class=\"pdn_txtpos2 & hs\">");
					out.println("<td  width='10%'>Inquiry No</td>");
          out.println("<td  width='20%'>Client Name</td>");
          out.println("<td  width='20' >Contact Details</td>");
          out.println("</tr>");
					 
           int j = 0;      					
							

              while(more){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input>");
									out.println("<td >"+rs.getString(1) +"</td>");//onclick=load_data('"+rs.getString(2)+"');
                  out.println("<td >"+rs.getString(2) +"</td>");
                  out.println("<td >"+rs.getString(3) +"</td>");
                  out.println("</tr>");
									more=rs.next();
									if(more){
										out.println("<tr class=tr_input1>");
										out.println("<td >"+rs.getString(1) +"</td>");//onclick=load_data('"+rs.getString(2)+"');
	                  out.println("<td >"+rs.getString(2) +"</td>");
	                  out.println("<td >"+rs.getString(3) +"</td>");
	                  out.println("</tr>");
										
	                	more=rs.next();
									}
              }
          //}
          
					
          out.println("</tr>");
					}
          out.println("</table>");
          out.println("</td>");
			
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("</html>");
					    
						
			}
			
			else if(m_chksql.trim().equals("Followup_det")){
			  String deal_no			 = req.getParameter("deal_no");
			     
        out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				out.println("var m_bsubmit = '0';");
				out.println("var arr_assign= new Array();");
				out.println("var m_send_val= '';");
				
				
        
				
				out.println("function befor_new(){");
				out.println(" if(confirm(\"Are you sure you want to enter new record?\")){  ");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_back(){");
				out.println("  window.close()");
				out.println("}");
				
				out.println("function befor_modify(){");
				out.println(" if(confirm(\"Are you sure you want to modify record?\")){  ");
				//out.println("  Form1.reset();   ");
				out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
				out.println("  document.Form1.OPTION_DESC.value=\"Edit\";");
				//out.println("  alert(document.Form1.OPTION_DESC.value);");
				out.println("  document.Form1.PRICE_NO.disabled=false;");
				out.println("  document.Form1.inqu_help.disabled=false;");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
				
				//end of Main Button Action
				//onload Action
				
				out.println("function befor_onload(){");
				/*out.println("  document.Form1.PRICE_NO.disabled=true;");
				out.println("  load_roll_value(\"New\");"); */
				out.println("}");
				//end of onload
				
				
				//---------Added by Prabash on 21-02-2012- for Select Branch ---**
				
				out.println("function load_user_Branch(){"); 
				 rs1 = stmt.executeQuery (" SELECT TO_CHAR(SYSDATE,'DD MONTH YYYY HH24:MI:SS'),USER_ID, "+
                    "        UPPER(NAME), UPPER(EMP_ID),LOCATION_CODE "+
                    " FROM   "+m_schema_name+".CO_CO_MAS_USER "+
                    " WHERE  USER_ID = '"+m_username+"' ");
                boolean more2 = rs1.next(); 
                if(more2){
                    out.println("   document.Form1.BRANCH_CODE.value= '"+rs1.getString(5)+"' "); 
                }
				out.println("}"); 
				out.println(""); 
				//---------------------------------------------------------------**
				
				out.println("function load_roll_value(m_val){"); 
				out.println("if(m_val==''){");
				out.println("help_box.innerHTML=\"Marketing  - Inquiry Follow up Report\";"); 
			  out.println("}else{");
				out.println("help_box.innerHTML=\"Marketing  - Inquiry Follow up Report - \"+m_val;"); 
			  out.println("}");
				out.println("}");
				
				
					//End Of Checking Values
				
				
        out.println("</Script>");
		//		out.println("<body onload=\"befor_onload();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">"); comment by Prabash on 23-03-2012
				out.println("<body onload=\"befor_onload();load_user_Branch();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
        out.println("<input type=hidden name=\"ROW_ID\" ></td>");
        //out.println("<input type=hidden name=\"order_by\" value=\""+order_by+"\"></td>");
        //out.println("<input type=hidden name=\"order_by_type\" value=\""+order_by_type+"\"></td>");
                  
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
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Marketing  - Inquiry Follow up Report</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  class=\"pdn_txtpos\" style=\"height: 28px\">");
				out.println("<table class=table border=\"0\">");
				out.println("<tr>");

				//out.println("<td width=\"40%\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td ><center><input type=button name=back     value=\"Close\"     class=mainbut onclick=befor_back();     onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></center></td>");
				out.println("</tr>");
				//out.println("<td ><input type=button name=cal     value=\"Calculate\" class=mainbut onclick=befor_cal(\"NO\",\"YES\");     onMouseOver='load_roll_value(\"Calculate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");

        out.println("</table>");
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
				
				out.println("</table>");
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
				out.println("<tr class=tr_input>");
				out.println("<td valign=top  width=100% Id=Follow_up> ");
				
				  
					out.println("<table class=table border='0' width='100%' >");
																	
					rs = stmt.executeQuery(" SELECT FOLLOW_UP_NO, ACTION_TOBE_TAKEN,TO_CHAR(EFF_VAL_DATE,'DD-MM-YYYY'),NVL(ACTION_TAKEN,'-'),  "+
																 "	       TO_CHAR(ACTION_DATE,'DD-MM-YYYY'), ACTION_SET_FOR, ENT_REMARKS, REMARKS, "+
																 "	       TO_CHAR(ACTION_ENT_DATE,'DD-MM-YYYY'), STATUS,PREV_FOLLOWUP_NO, ORG_FOLLOWUP_NO "+
																 "	FROM   "+m_schema_name+".AF_CO_PRO_FOLLOW_UP "+
																 "	WHERE  ID_NO=UPPER('"+deal_no+"') "+
																 "	ORDER  BY PRIORITY,EFF_VAL_DATE DESC ");
				boolean more = rs.next();

				if(more){
          out.println("<tr class=\"pdn_txtpos2 & hs\">");
					out.println("<td  width='10%'>Follow Up No</td>");
          out.println("<td  width='20%'>Action </td>");
          out.println("<td  width='10' >Value Date</td>");
          out.println("<td  width='20' >Action Taken</td>");
          out.println("<td  width='10' >Action Date</td>");
          out.println("<td  width='10' >Action User</td>");
          out.println("</tr>");
					 
           int j = 0;      					
							

              while(more){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input>");
									out.println("<td >"+rs.getString(1) +"</td>");//onclick=load_data('"+rs.getString(2)+"');
                  out.println("<td >"+rs.getString(2) +"</td>");
                  out.println("<td >"+rs.getString(3) +"</td>");
                  out.println("<td >"+rs.getString(4) +"</td>");//onclick=load_data('"+rs.getString(2)+"');
                  out.println("<td >"+rs.getString(5) +"</td>");
                  out.println("<td >"+rs.getString(6) +"</td>");
                  out.println("</tr>");
									more=rs.next();
									if(more){
										out.println("<tr class=tr_input1>");
										out.println("<td >"+rs.getString(1) +"</td>");//onclick=load_data('"+rs.getString(2)+"');
	                  out.println("<td >"+rs.getString(2) +"</td>");
	                  out.println("<td >"+rs.getString(3) +"</td>");
	                  out.println("<td >"+rs.getString(4) +"</td>");//onclick=load_data('"+rs.getString(2)+"');
                    out.println("<td >"+rs.getString(5) +"</td>");
                    out.println("<td >"+rs.getString(6) +"</td>");
                    out.println("</tr>");
										
	                	more=rs.next();
									}
              }
          //}
          
					
          out.println("</tr>");
					}
          out.println("</table>");
          out.println("</td>");
			
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("</html>");
					    
						
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
			
			//added by SH on30-01-2007 for inquiry fillering report
			
			else if(m_chksql.trim().equals("Inquiry_Fill_Rep")){
			  String order_by			 = req.getParameter("order_by");
			  String order_by_type = req.getParameter("order_by_type");
			  String mk_officer		 = req.getParameter("mk_off");
			  String mk_supervisor = req.getParameter("mk_sup");
			     
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
        out.println("http_request.onreadystatechange = function() {alertContents(http_request,opt,opt1); };");
				out.println("http_request.open('GET',url, true);");
        //alert('1111');
				out.println("http_request.send(null);");
				out.println("}");

        out.println("function alertContents(http_request,opt,opt1) {");
        out.println(" if (http_request.readyState == 4) {");
        out.println("    if (http_request.status == 200) {");
        out.println("      if(http_request.responseText!=\"\"){");
				out.println("        if(opt==\"DATA\"){");
				out.println("          Inquiry.innerHTML=http_request.responseText;");
				//out.println("          get_mk_team();");
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
							
				out.println("          }else{");
				out.println("          }");				
        out.println("      }");				
        out.println("      }");				
        out.println("      else {");
				
        out.println("      }");
        out.println("    } else {");
        out.println("        alert('There was a problem with the request.');");
        out.println("    }");
        out.println(" }");
        out.println("}");
						
			
				out.println("function befor_new(){");
				out.println(" if(confirm(\"Are you sure you want to enter new record?\")){  ");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_back(){");
				//out.println(" if(confirm(\"Are You Sure \")){  ");
				//out.println("  Form1.reset() ;  ");
				//out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
				//out.println("  document.Form1.OPTION_DESC.value=\"B\";");
				//out.println("  document.Form1.PRICE_NO.disabled=true;");
				out.println("  window.close()");
				//out.println(" }  ");
				out.println("}");
				
				out.println("function befor_modify(){");
				out.println(" if(confirm(\"Are you sure you want to modify record?\")){  ");
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
				
		
				
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
				
				//end of Main Button Action
				//onload Action
				
				out.println("function befor_onload(){");
				/*out.println("  document.Form1.PRICE_NO.disabled=true;");
				out.println("  document.Form1.inqu_help.disabled=true;");
				out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
				out.println("  document.Form1.OPTION_DESC.value=\"New\";");
				out.println("  load_roll_value(\"New\");"); */
				out.println("}");
				//end of onload
				
				//---------Added by Prabash on 23-02-2012- for Select Branch ---**
				
				out.println("function load_user_Branch(){"); 
				 rs1 = stmt.executeQuery (" SELECT TO_CHAR(SYSDATE,'DD MONTH YYYY HH24:MI:SS'),USER_ID, "+
                    "        UPPER(NAME), UPPER(EMP_ID),LOCATION_CODE "+
                    " FROM   "+m_schema_name+".CO_CO_MAS_USER "+
                    " WHERE  USER_ID = '"+m_username+"' ");
                boolean more2 = rs1.next(); 
                if(more2){
                    out.println("   document.Form1.BRANCH_CODE.value= '"+rs1.getString(5)+"' "); 
                }
				out.println("}"); 
				out.println(""); 
				//---------------------------------------------------------------**
				
				out.println("function load_roll_value(m_val){"); 
				out.println("if(m_val==''){");
				out.println("help_box.innerHTML=\"Marketing  - Inquiry Follow up Report\";"); 
			  out.println("}else{");
				out.println("help_box.innerHTML=\"Marketing  - Inquiry Follow up Report - \"+m_val;"); 
			  out.println("}");
				out.println("}");
				
				
					//End Of Checking Values
				
						
			  out.println("function load_data(val) {");
				out.println("	 order_by_type = 'ASC'; ");  
				out.println("	 if(val==document.Form1.order_by.value){");
				out.println("	   if(document.Form1.order_by_type.value=='DESC'){");
				out.println("	      order_by_type = 'ASC'; ");  
				out.println("    }else{");
			  out.println("       order_by_type = 'DESC'; ");
			  out.println("    }");
			  out.println("  }else{");
			  out.println("    order_by_type = 'ASC'; ");
			  out.println("  }");
			  out.println("	url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Inquiry?chksql=Inquiry_Fill_Rep_data&order_by=&order_by_type=&mk_off=\"+document.Form1.OFFICER_CODE.value+\"&mk_sup=\"+document.Form1.SUPERVISOR_CODE.value+\"\";"); //\"+val+\"\"+order_by_type+\"
	      out.println(" makeRequest(url,'DATA','');");
        //AF_MK_Inquiry?chksql=Inquiry_Followup&order_by=ENT_DATE&order_by_type=DESC
				//out.println("	popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Documents?chksql=get_documents&deal_no=\"+num+\"\", \"oBj\",\"left=150,top=280,width=520,height=290\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
			
			  out.println("function load_inq_data(val) {");
				//out.println("	window.location=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Inquiry?chksql=Inquiry_Followup&order_by=\"+val+\"&order_by_type=\"+order_by_type+\"\";"); 
	      //AF_MK_Inquiry?chksql=Inquiry_Followup&order_by=ENT_DATE&order_by_type=DESC
				out.println("	popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Inquiry?chksql=Inquiry_det&deal_no=\"+val+\"\", \"oBj\",\"left=150,top=280,width=520,height=290\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
			
				out.println("function load_fol_data(val) {");
				//out.println("	window.location=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Inquiry?chksql=Inquiry_Followup&order_by=\"+val+\"&order_by_type=\"+order_by_type+\"\";"); 
	      //AF_MK_Inquiry?chksql=Inquiry_Followup&order_by=ENT_DATE&order_by_type=DESC
				out.println("	popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Inquiry?chksql=Followup_det&deal_no=\"+val+\"\", \"oBj\",\"left=150,top=280,width=520,height=290\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
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
				//out.println("alert(oBj.valout[3]+'--oBj-'+oBj.valout[2]+'--'+oBj.valout[4]);");
				
				out.println("if(oBj.valout[1]=='Next')  {");
				out.println("Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
				out.println("}");
				out.println("else if  (oBj.valout[1]=='Prev') {");
				out.println("Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
				out.println("}		");
				out.println("else if(oBj.valout[1] == 'Close'){");
				out.println("if(IfCount=='7'){"); 
				out.println("document.Form1.INQ_NO.value ='';");
				out.println("}");
				out.println("else if(IfCount=='4'){"); 
				out.println("document.Form1.TEAM.value ='';");
				out.println("}");
				out.println("else if(IfCount=='11'){"); 
				out.println("document.Form1.SUPERVISOR_CODE.value ='';");
				out.println("}");
				out.println("else if(IfCount=='2'){"); 
				out.println("document.Form1.OFFICER_CODE.value ='';");
				out.println("}");
				out.println("}");
				out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != null){");
				out.println("if(IfCount=='1'){"); 
				out.println("client_assign(oBj);");
				//out.println("load_data(document.Form1.txt_aff_code.value);");
				out.println("}");
				out.println("else if(IfCount=='2'){"); 
				out.println("mk_officer_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='3'){"); 
				out.println("mk_super_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='4'){"); 
				out.println("mk_team_assign(oBj);");
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
				out.println("else if(IfCount=='8'){"); 
				out.println("brk_assign(oBj);");
				out.println("}");
				out.println("else if(IfCount=='11'){"); 
				out.println("mk_supe_assign(oBj);");
				out.println("}");
				
				
				out.println("}");
				out.println("else if(oBj.valout[4] == \" \"){ ");
				
				out.println("if(IfCount=='7'){"); 
				out.println("document.Form1.INQ_NO.value ='';");
				out.println("}");
				out.println("else if(IfCount=='4'){"); 
				out.println("document.Form1.TEAM.value ='';");
				out.println("}");
				out.println("else if(IfCount=='11'){"); 
				//out.println("document.Form1.SUPERVISOR_CODE.value ='';");
				out.println("}");
				out.println("else if(IfCount=='2'){"); 
				out.println("document.Form1.OFFICER_CODE.value ='';");
				out.println("}");
				//out.println("Crit = oBj.valout[4];");
				//out.println("criteria_1('1','10','1',oBj.valout[4],Sql,IfCount);");
				//out.println("}	");
				out.println("}");	
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
				
				out.println("function client_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.CLIENT_NAME.value+\"@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");
								
				
				out.println("function mk_supe_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.SUPERVISOR_CODE.value+\"@\"+document.Form1.OFFICER_CODE.value+\"@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function mk_supe_assign(oBj){");
				//out.println(" alert(oBj.valout[4]);");
				out.println(" document.Form1.SUPERVISOR_CODE.value =oBj.valout[4]");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");		
				
			/*	//Marketing Officer
				out.println("function mk_officer_help(Start,End,Hid_No,Sql,IfCount){");
				out.println("Crit=document.Form1.OFFICER_CODE.value+\"@AF@\";");
				out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
				out.println("}");		
				out.println("function mk_officer_assign(oBj){");
				out.println(" document.Form1.OFFICER_CODE.value =oBj.valout[2]");
				//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
				out.println("}");		
					*/			
			
			
        out.println("</Script>");
			//	out.println("<body onload=\"befor_onload();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">"); comment by Prabash on 23/03/2012
				out.println("<body onload=\"befor_onload();load_user_Branch();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">"); //insert load_user_Branch()  by Prabash on 23-03-2012
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
        out.println("<input type=hidden name=\"ROW_ID\" ></td>");
				
        out.println("<input type=hidden name=\"order_by\" value=\""+order_by+"\"></td>");
        out.println("<input type=hidden name=\"order_by_type\" value=\""+order_by_type+"\"></td>");
                  
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
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Marketing  - Inquiry Follow up Report</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  class=\"pdn_txtpos\" style=\"height: 28px\">");
				out.println("<table class=table border=\"0\">");
				out.println("<tr>");

				//out.println("<td width=\"40%\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td ><center><input type=button name=back     value=\"Close\"     class=mainbut onclick=befor_back();     onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></center></td>");
				out.println("</tr>");
				//out.println("<td ><input type=button name=cal     value=\"Calculate\" class=mainbut onclick=befor_cal(\"NO\",\"YES\");     onMouseOver='load_roll_value(\"Calculate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 /*
	rs = stmt.executeQuery (" SELECT TO_CHAR(SYSDATE,'DD MONTH YYYY HH24:MI:SS'),USER_ID, "+
				                        "        NAME, EMP_ID,"+m_schema_name+".AF_CO_GET_LOCATION_DESC(LOCATION_CODE),   "+
																"        "+m_schema_name+".AF_CO_GET_DIVISION_DESC(DIVISION_CODE),NVL("+m_schema_name+".AF_CO_GET_DESIGNATION_DESC(DESIGNATION_CODE),'-') "+
																" FROM   "+m_schema_name+".CO_CO_MAS_USER "+
																" WHERE  USER_ID = '"+m_username+"' ");
        boolean more = rs.next(); 
        if(more){
	
   				out.println("<td class=\"pdn_txtpos\" ><b>OUTSTANDING ACTIVITY REPORT AS AT "+rs.getString(1)+" </b></td>");
				  out.println("</tr>");
				  out.println("<tr class=tr_input>");
				  out.println("<td class=\"pdn_txtpos\"><b>LOGGED USER : "+m_username+" ("+rs.getString(4)+" / "+rs.getString(5)+" / "+rs.getString(6)+" / "+rs.getString(7)+" )</b></td></tr>");
				}*/
        out.println("</table>");
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
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
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
				out.println("<tr class=tr_input>");
				out.println("<td colspan=5></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td valign=top  width=20% >Marketing Officer</td>");
				out.println("<td valign=top  width=20% > <input name=\"OFFICER_CODE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" onchange=chk_mk_officer()>");
				out.println("<input type=button name=mko_help value=... class=\"but_input\" onclick=\"mk_officer_help('1','10','0','MKOfficerSql','2')\"></td>");
				
				out.println("<td valign=top  width=20% >Marketing Supervisor</td>");
				out.println("<td valign=top  width=20% ><input name=\"SUPERVISOR_CODE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" onchange=chk_mk_head()>");//
				out.println("<input type=button name=sup_help value=... class=\"but_input\" onclick=\"mk_supe_help('1','10','0','MKTeamSql','11')\"></td>");//
				out.println("<td valign=top  width=20% ><input type=button name=go_help value=Run class=\"but_input\" onclick=\"load_data()\"></td>");
				out.println("</tr>");
				
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
				out.println("<tr class=tr_input>");
				out.println("<td valign=top  width=100% Id=Inquiry> ");
				
				  
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=12 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
					String m_where = " ";
					if ((mk_supervisor!=null) && (mk_officer!=null)){
					  m_where = " WHERE  MK_OFFICER = '"+mk_officer+"' AND MK_SUPERVISOR= '"+mk_supervisor+"' ";	
					}else if (mk_officer!=null){
					  m_where = " WHERE  MK_OFFICER = '"+mk_officer+"'  ";	
					}else if (mk_supervisor!=null){
					  m_where = " WHERE  MK_SUPERVISOR= '"+mk_supervisor+"'  ";	
					}
					if(order_by==null){
					  order_by="MK_OFFICER";
					}
					if(order_by_type==null){
					  order_by_type="ASC";
					}
					
					
					rs = stmt.executeQuery(" SELECT INQUIRY_CODE,TO_CHAR(ENT_DATE,'DD-MM-YYYY'),  "+
				                       "        "+m_schema_name+".AF_CO_GET_CLIENT_CAT_DESC(CLIENT_CATEGORY), "+
															 "        "+m_schema_name+".AF_CO_GET_TRAN_TYPE_DESC(SUB_PRODUCT_CODE), "+
															 "      	CLIENT_NAME,MK_OFFICER,MK_SUPERVISOR, "+
															 "      	"+m_schema_name+".AF_CO_GET_TEAM_DESC(TEAM), "+
															 "      	TO_CHAR("+m_schema_name+".AF_CO_GET_FOLL_LAST_NEXT_DATE(INQUIRY_CODE,'LAST'),'DD-MM-YYYY'), "+ 
															 "      	TO_CHAR("+m_schema_name+".AF_CO_GET_FOLL_LAST_NEXT_DATE(INQUIRY_CODE,'NEXT'),'DD-MM-YYYY'), "+ 
															 "        NVL(TEL_NO,'-')||'/'||NVL(MOBILE_NO,'-')||'/'||NVL(ADDRESS,'-'), "+
															 "        ADDRESS2, INQUIRY_STATUS,CITY_CODE,"+m_schema_name+".AF_CO_GET_EGAL_ENTITY_DESC(LEGAL_ENTITY), "+
															 "        STATUS, INITIATION_TYPE,CLIENT_TYPE, "+
															 "      	LEAD_SOURCE_CATEGORY,LEAD_SOURCE_NAME,INTRODUCER, "+
															 "      	ID_NO, "+m_schema_name+".AF_CO_GET_TRAN_SUB_TYPE_DESC(TRANSACTION_SUB_TYPE),  "+
															 "        EMAIL, CONTACT_PERSON, ENT_USER, MOD_USER,MOD_DATE "+
															 " FROM   "+m_schema_name+".AF_MK_PRO_INQUIRY "+
															 " "+m_where+"  "+	
															 " ORDER BY "+order_by+" "+order_by_type+" ");
				boolean more = rs.next();

				if(more){
				  
          out.println("<tr class=\"pdn_txtpos2 & hs\">");
					out.println("<td  width='10%' onclick=load_data('INQUIRY_CODE')>Inquiry No</td>");
          out.println("<td  width='8%' onclick=load_data('ENT_DATE')>Ent Date</td>");
          out.println("<td  width='8%' onclick=''                   >Last Date</td>");
          out.println("<td  width='8%' onclick=''                   >Next Date</td>");
          out.println("<td  width='8%' onclick=load_data('CLIENT_CATEGORY')>Inquiry Category</td>");
					out.println("<td  width='10%' onclick=load_data('SUB_PRODUCT_CODE')>Transaction Type</td>");
					out.println("<td  width='20%' onclick=load_data('CLIENT_NAME')>Name</td>");
					out.println("<td  width='10%' onclick=load_data('MK_OFFICER')>Officer</td>");
					out.println("<td  width='8%' onclick=load_data('MK_SUPERVISOR')>Supervisor</td>");
					out.println("<td  width='8%' onclick=load_data('TEAM')>Team</td>");
					out.println("<td  width='2%' onclick=''>Inq</td>");
					out.println("<td  width='3%' onclick=''>Fol</td>");
					//out.println("<td  width='11%' onclick=load_data('TEL_NO||MOBILE_NO||ADDRESS')>Contact Details</td>");
					out.println("</tr>");
					 
           int j = 0;      					
							

              while(more){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input>");
									out.println("<td >"+rs.getString(1) +"</td>");//onclick=load_data('"+rs.getString(2)+"');
                  out.println("<td >"+rs.getString(2) +"</td>");
                  out.println("<td >"+rs.getString(9) +"</td>");
                  out.println("<td >"+rs.getString(10)+"</td>");
                  out.println("<td >"+rs.getString(3) +"</td>");
                  out.println("<td >"+rs.getString(4) +"</td>");
                  out.println("<td >"+rs.getString(5) +"</td>");
                  out.println("<td >"+rs.getString(6) +"</td>");
                  out.println("<td >"+rs.getString(7) +"</td>");
                  out.println("<td >"+rs.getString(8) +"</td>");
                  out.println("<td onclick=load_inq_data('"+rs.getString(1) +"') class=hs>Inq</td>");
					        out.println("<td onclick=load_fol_data('"+rs.getString(1) +"') class=hs>Fol</td>");
					        out.println("</tr>");
									more=rs.next();
									if(more){
										out.println("<tr class=tr_input1>");
										out.println("<td >"+rs.getString(1) +"</td>");//onclick=load_data('"+rs.getString(2)+"');
	                  out.println("<td >"+rs.getString(2) +"</td>");
	                  out.println("<td >"+rs.getString(9) +"</td>");
	                  out.println("<td >"+rs.getString(10)+"</td>");
	                  out.println("<td >"+rs.getString(3) +"</td>");
	                  out.println("<td >"+rs.getString(4) +"</td>");
	                  out.println("<td >"+rs.getString(5) +"</td>");
	                  out.println("<td >"+rs.getString(6) +"</td>");
	                  out.println("<td >"+rs.getString(7) +"</td>");
	                  out.println("<td >"+rs.getString(8) +"</td>");
	                  out.println("<td onclick=load_inq_data('"+rs.getString(1) +"') class=hs>Inq</td>");
					          out.println("<td onclick=load_fol_data('"+rs.getString(1) +"') class=hs>Fol</td>");
					          out.println("</tr>");
										
	                	more=rs.next();
									}
              }
          //}
          
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=12><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
           
          
			
				  out.println("</tr>");
				  }
          out.println("</table>");
          out.println("</td>");
			
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("</html>");
					    
						
			}else if(m_chksql.trim().equals("Inquiry_Fill_Rep_data")){
			  String order_by			 = req.getParameter("order_by");
			  String order_by_type = req.getParameter("order_by_type");
			  String mk_officer		 = req.getParameter("mk_off");
			  String mk_supervisor = req.getParameter("mk_sup");
			
			  out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=12 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
					String m_where = " ";
					if ((mk_supervisor!=null) && (mk_officer!=null)){
					 if(!mk_supervisor.equals("") && !mk_officer.equals("") ){
					  m_where = " WHERE  MK_OFFICER = '"+mk_officer+"' AND MK_SUPERVISOR= '"+mk_supervisor+"' ";	
					 }else if(!mk_officer.equals("") ){
					  m_where = " WHERE  MK_OFFICER = '"+mk_officer+"' ";	
					 }else if(!mk_supervisor.equals("") ){
					  m_where = " WHERE MK_SUPERVISOR= '"+mk_supervisor+"' ";	
					 }	
					}else if (mk_officer!=null){
					 if(!mk_officer.equals("") ){
					  m_where = " WHERE  MK_OFFICER = '"+mk_officer+"' ";	
					 }	
					}else if (mk_supervisor!=null){
					 if(!mk_supervisor.equals("") ){
					  m_where = " WHERE MK_SUPERVISOR= '"+mk_supervisor+"' ";	
					 }	
					}
					order_by="MK_OFFICER";
					order_by_type="ASC";
					
					if(order_by==null){
					  order_by="MK_OFFICER";
					}
					if(order_by_type==null){
					  order_by_type="ASC";
					}
					
																					
					rs = stmt.executeQuery(" SELECT INQUIRY_CODE,TO_CHAR(ENT_DATE,'DD-MM-YYYY'),  "+
				                       "        "+m_schema_name+".AF_CO_GET_CLIENT_CAT_DESC(CLIENT_CATEGORY), "+
															 "        "+m_schema_name+".AF_CO_GET_TRAN_TYPE_DESC(SUB_PRODUCT_CODE), "+
															 "      	CLIENT_NAME,MK_OFFICER,MK_SUPERVISOR, "+
															 "      	"+m_schema_name+".AF_CO_GET_TEAM_DESC(TEAM), "+
															 "      	TO_CHAR("+m_schema_name+".AF_CO_GET_FOLL_LAST_NEXT_DATE(INQUIRY_CODE,'LAST'),'DD-MM-YYYY'), "+ 
															 "      	TO_CHAR("+m_schema_name+".AF_CO_GET_FOLL_LAST_NEXT_DATE(INQUIRY_CODE,'NEXT'),'DD-MM-YYYY'), "+ 
															 "        NVL(TEL_NO,'-')||'/'||NVL(MOBILE_NO,'-')||'/'||NVL(ADDRESS,'-'), "+
															 "        ADDRESS2, INQUIRY_STATUS,CITY_CODE,"+m_schema_name+".AF_CO_GET_EGAL_ENTITY_DESC(LEGAL_ENTITY), "+
															 "        STATUS, INITIATION_TYPE,CLIENT_TYPE, "+
															 "      	LEAD_SOURCE_CATEGORY,LEAD_SOURCE_NAME,INTRODUCER, "+
															 "      	ID_NO, "+m_schema_name+".AF_CO_GET_TRAN_SUB_TYPE_DESC(TRANSACTION_SUB_TYPE),  "+
															 "        EMAIL, CONTACT_PERSON, ENT_USER, MOD_USER,MOD_DATE "+
															 " FROM   "+m_schema_name+".AF_MK_PRO_INQUIRY "+
															 " "+m_where+"  "+	
															 " ORDER BY "+order_by+" "+order_by_type+" ");
				boolean more = rs.next();

				if(more){
				  
          out.println("<tr class=\"pdn_txtpos2 \">");
					out.println("<td  width='10%' onclick=load_data('INQUIRY_CODE')>Inquiry No</td>");
          out.println("<td  width='8%' onclick=load_data('ENT_DATE')>Ent Date</td>");
          out.println("<td  width='8%' onclick=''                   >Last Date</td>");
          out.println("<td  width='8%' onclick=''                   >Next Date</td>");
          out.println("<td  width='8%' onclick=load_data('CLIENT_CATEGORY')>Inquiry Category</td>");
					out.println("<td  width='10%' onclick=load_data('SUB_PRODUCT_CODE')>Transaction Type</td>");
					out.println("<td  width='20%' onclick=load_data('CLIENT_NAME')>Name</td>");
					out.println("<td  width='10%' onclick=load_data('MK_OFFICER')>Officer</td>");
					out.println("<td  width='8%' onclick=load_data('MK_SUPERVISOR')>Supervisor</td>");
					out.println("<td  width='8%' onclick=load_data('TEAM')>Team</td>");
					out.println("<td  width='2%' onclick=''>Inq</td>");
					out.println("<td  width='3%' onclick=''>Fol</td>");
					//out.println("<td  width='11%' onclick=load_data('TEL_NO||MOBILE_NO||ADDRESS')>Contact Details</td>");
					out.println("</tr>");
					 
           int j = 0;      					
							

              while(more){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input>");
									out.println("<td >"+rs.getString(1) +"</td>");//onclick=load_data('"+rs.getString(2)+"');
                  out.println("<td >"+rs.getString(2) +"</td>");
                  out.println("<td >"+rs.getString(9) +"</td>");
                  out.println("<td >"+rs.getString(10)+"</td>");
                  out.println("<td >"+rs.getString(3) +"</td>");
                  out.println("<td >"+rs.getString(4) +"</td>");
                  out.println("<td >"+rs.getString(5) +"</td>");
                  out.println("<td >"+rs.getString(6) +"</td>");
                  out.println("<td >"+rs.getString(7) +"</td>");
                  out.println("<td >"+rs.getString(8) +"</td>");
                  out.println("<td onclick=load_inq_data('"+rs.getString(1) +"') class=hs>Inq</td>");
					        out.println("<td onclick=load_fol_data('"+rs.getString(1) +"') class=hs>Fol</td>");
					        out.println("</tr>");
									more=rs.next();
									if(more){
										out.println("<tr class=tr_input1>");
										out.println("<td >"+rs.getString(1) +"</td>");//onclick=load_data('"+rs.getString(2)+"');
	                  out.println("<td >"+rs.getString(2) +"</td>");
	                  out.println("<td >"+rs.getString(9) +"</td>");
	                  out.println("<td >"+rs.getString(10)+"</td>");
	                  out.println("<td >"+rs.getString(3) +"</td>");
	                  out.println("<td >"+rs.getString(4) +"</td>");
	                  out.println("<td >"+rs.getString(5) +"</td>");
	                  out.println("<td >"+rs.getString(6) +"</td>");
	                  out.println("<td >"+rs.getString(7) +"</td>");
	                  out.println("<td >"+rs.getString(8) +"</td>");
	                  out.println("<td onclick=load_inq_data('"+rs.getString(1) +"') class=hs>Inq</td>");
					          out.println("<td onclick=load_fol_data('"+rs.getString(1) +"') class=hs>Fol</td>");
					          out.println("</tr>");
										
	                	more=rs.next();
									}
              }
          //}
          
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=12><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
           
          
			
				  out.println("</tr>");
				  }
          out.println("</table>");
		  }	
			
			//end of report
			
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
				out.println("<TABLE BORDER='0' WIDTH='0' BGCOLOR='silver' STYLE='{ font: bold 9pt arial;}'></TABLE>");
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
                        
                        out.println("<table border='0' width='100%' bgcolor='silver' style='{ font: bold 9pt arial;}'>");
                        out.println("<tr><td  width='40%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>Total Factor</td>");
                        out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(7))+"</td></tr>");
                        /*out.println("<tr><td  width='40%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>Rate (%)</td>");
                        out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(1))+"</td></tr>");
                        out.println("<tr><td  width='40%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>Rate </td>");
                        out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(2))+"</td></tr>");
                        out.println("<tr><td  width='40%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>Rate per Month</td>");
                        out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(3))+"</td></tr>");
                        out.println("<tr><td  width='40%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>Payment Frequency</td>");
                        out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(4))+"</td></tr>");
                        out.println("<tr><td  width='40%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>No of Installaments</td>");
                        out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(5))+"</td></tr>");
                        
												out.println("</table>");
												
                    }
                    
                    out.println("<br>");
                    
                    out.println("<table border='1' width='100%' bgcolor='silver' style='{ font: bold 9pt arial;}'>");
                    out.println("<tr><td  width='20%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>Installment No(Y)</td>");
                    //out.println("<td  width='20%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>Rate per Month(RM)</td>");
                    //out.println("<td  width='30%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>(RM+1)=X</td>");
                    out.println("<td  width='30%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>Factor</td>");
										out.println("<td  width='30%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>Gross Rental</td>");
										out.println("<td  width='30%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>Net Rental</td>");
										out.println("<td  width='30%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>Pracent Value</td>");
										
										
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
                        out.println("<tr><td  width='20%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>"+j+"</td>");
                        //out.println("<td  width='20%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>"+nf.format(mm_rate_per_month)+"</td>");
                        rs = stmt.executeQuery ("SELECT "+(mm_rate_per_month+1)+",1/POWER("+(mm_rate_per_month+1)+","+j+"),"+
												                                ""+m_value+"/"+mm_tot_fact+","+m_value+"/"+mm_tot_fact+"+("+m_value+"/"+mm_tot_fact+")*"+mm_vat_per+","+
																												""+m_value+"/"+mm_tot_fact+"/1/POWER("+(mm_rate_per_month+1)+","+j+") "+
																								"FROM   DUAL ");
                        if(rs.next()){
                            //out.println("<td  width='30%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>"+nf.format(rs.getDouble(1))+"</td>");
                            out.println("<td  width='20%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>"+nf.format(rs.getDouble(2))+"</td>");
                            out.println("<td  width='20%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>"+nf.format(rs.getDouble(3))+"</td>");
                            out.println("<td  width='20%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>"+nf.format(rs.getDouble(4))+"</td>");
                            out.println("<td  width='20%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>"+nf.format(rs.getDouble(5))+"</td>");
                            out.println("</tr>");
                            sum_rate=sum_rate+(rs.getDouble(2));
														sum_rent=sum_rent+(rs.getDouble(3));
														sum_p_re=sum_p_re+(rs.getDouble(5));
                        }
                    }
                    out.println("<tr><td  width='20%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'></td>");
                    //out.println("<td  width='20%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'></td>");
                    out.println("<td  style='{ text-align:left; color: black;  font: bold 9pt arial;}'>"+nf.format(sum_rate)+"</td>");
                    out.println("<td  width='30%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>"+nf.format(sum_rent)+"</td>");
                    out.println("<td  width='30%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>"+nf.format(sum_rent)+"</td>");
                    out.println("<td  width='30%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>"+nf.format(sum_p_re)+"</td>");
                    out.println("</tr></table>");
                    
                    /*out.println("<br>");
                    out.println("<table border='0' width='100%' bgcolor='silver' style='{ font: bold 9pt arial;}'>");
                    out.println("<tr><td  width='40%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'></td>");
                    out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>PMT=(Future Value/Sum of Interest Rates)</td></tr>");
                    out.println("<tr><td  width='40%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'></td>");
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
                        
                        out.println("<table border='0' width='100%' bgcolor='silver' style='{ font: bold 9pt arial;}'>");
                        out.println("<tr><td  width='40%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>Total Factor</td>");
                        out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(7))+"</td></tr>");
                        out.println("</table>");
												
                    }
                    
                    out.println("<br>");
                    
                    out.println("<table border='1' width='100%' bgcolor='silver' style='{ font: bold 9pt arial;}'>");
                    out.println("<tr><td  width='20%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>Installment No(Y)</td>");
                    out.println("<td  width='30%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>Factor</td>");
										out.println("<td  width='30%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>Gross Rental</td>");
										out.println("<td  width='30%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>Net Rental</td>");
										out.println("<td  width='30%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>Pracent Value</td>");
										
										
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
                        out.println("<tr><td  width='20%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>"+j+"</td>");
                        //out.println("<td  width='20%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>"+nf.format(mm_rate_per_month)+"</td>");
                        rs = stmt.executeQuery ("SELECT "+(mm_rate_per_month+1)+",1/POWER("+(mm_rate_per_month+1)+","+j+"),"+
												                                ""+m_value+"/"+mm_tot_fact+","+m_value+"/"+mm_tot_fact+"+("+m_value+"/"+mm_tot_fact+")*"+mm_vat_per+","+
																												""+m_value+"/"+mm_tot_fact+"/1/POWER("+(mm_rate_per_month+1)+","+j+") "+
																								"FROM   DUAL ");
                        if(rs.next()){
                            //out.println("<td  width='30%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>"+nf.format(rs.getDouble(1))+"</td>");
                            out.println("<td  width='20%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>"+nf.format(rs.getDouble(2))+"</td>");
                            out.println("<td  width='20%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>"+nf.format(rs.getDouble(3))+"</td>");
                            out.println("<td  width='20%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>"+nf.format(rs.getDouble(4))+"</td>");
                            out.println("<td  width='20%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>"+nf.format(rs.getDouble(5))+"</td>");
                            out.println("</tr>");
                            sum_rate=sum_rate+(rs.getDouble(2));
														sum_rent=sum_rent+(rs.getDouble(3));
														sum_p_re=sum_p_re+(rs.getDouble(5));
                        }
                    }
                    out.println("<tr><td  width='20%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'></td>");
                    //out.println("<td  width='20%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'></td>");
                    out.println("<td  style='{ text-align:left; color: black;  font: bold 9pt arial;}'>"+nf.format(sum_rate)+"</td>");
                    out.println("<td  width='30%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>"+nf.format(sum_rent)+"</td>");
                    out.println("<td  width='30%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>"+nf.format(sum_rent)+"</td>");
                    out.println("<td  width='30%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'>"+nf.format(sum_p_re)+"</td>");
                    out.println("</tr></table>");
                    
                    /*out.println("<br>");
                    out.println("<table border='0' width='100%' bgcolor='silver' style='{ font: bold 9pt arial;}'>");
                    out.println("<tr><td  width='40%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'></td>");
                    out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>PMT=(Future Value/Sum of Interest Rates)</td></tr>");
                    out.println("<tr><td  width='40%' style='{ text-align:left; color: black;  font: bold 9pt arial;}'></td>");
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

				
				out.println("<head><TITLE>DealNet</TITLE></head><HTML><BODY leftmargin='0' topmargin='0'><FORM NAME='Form1'><TABLE BORDER='0' WIDTH='0' BGCOLOR='silver' STYLE='{ font: bold 9pt arial;}'></TABLE>");
				out.println("<TABLE BORDER='0' WIDTH='2800' BGCOLOR='silver' STYLE='{ font: bold 9pt arial;}'><B></TABLE>");
				
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
        out.println("<TABLE BORDER='0' WIDTH='2800' BGCOLOR='silver' STYLE='{ font: bold 9pt arial;}'><B>");
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
