//--
//SCREEN NAME : CREDIT VERIFICATION ASSET
//MODIFIED BY : DELANJALI
//DATE/TIME   : 
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_PRO_Credit_Asset_Veri_1 extends javax.servlet.http.HttpServlet { 
	 
	ServletOutputStream out =  null;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res) { 
		 
		try { 
			 
					LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
							
					String m_html_client_url=m_sn_methods.html_client_url.trim(); 
					String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
					String m_fschema_name=m_sn_methods.client_name.trim();
					String m_schema_name = m_sn_methods.schema_name;
					String m_servlet_client_url=m_sn_methods.servlet_client_url;
					String m_client_name=m_sn_methods.client_name;
					String m_client_t3_port=m_sn_methods.client_t3_port;
					
					
				  String m_username 						= "AA";//m_sn_methods.username;
					String m_applicaton_no        = req.getParameter("applicaton_no");
					
							
					res.setStatus(HttpServletResponse.SC_OK); 
					res.setContentType("text/html"); 
					out = res.getOutputStream(); 

					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>Credit Process - Asset Verification</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">"); 
					
					out.println("var m_applicaton_no='"+m_applicaton_no+"'");
					out.println("var asset_enable_sts='Y';");
					out.println("var m_size=0 ;");
					
					out.println("function makeRequest() {");
					out.println("if(m_applicaton_no!=''){");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_pop_LAKDL_AF_CR_display_asset_data&application_no=\"+m_applicaton_no+\"\";");
					out.println("}");
					out.println("load_interface(m_url,'XML');");
					out.println("get_vector(data_vec);");
					out.println("}");
					
					out.println("function get_vector(data_vec) {");
			 		out.println("			if(data_vec.length > 0) {");
					out.println("     display_data(data_vec);");
					out.println("			}");
				  out.println("}");
					
					
					out.println("function header(){");
					out.println("m_table.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\"><TR>'+");
					out.println("'<TD WIDTH=\"15%\"     align=\"center\" class=\"pdn_txtpos2\" style=\"height:20px;font:11px bold;\" id=\"help_box\"><B>Asset ID</B></TD>'+");
					out.println("'<TD WIDTH=\"15%\"     align=\"center\" class=\"pdn_txtpos2\" style=\"height:20px;font:11px bold;\" id=\"help_box\"><B>Supplier</B></TD>'+");
					out.println("'<TD WIDTH=\"10%\"     align=\"center\" class=\"pdn_txtpos2\" style=\"height:20px;font:11px bold;\" id=\"help_box\"><B>Quantity</B></TD>'+");
					out.println("'<TD WIDTH=\"11%\"     align=\"center\" class=\"pdn_txtpos2\" style=\"height:20px;font:11px bold;\" id=\"help_box\"><B>Cost</B></TD>'+");
					out.println("'<TD WIDTH=\"10%\"     align=\"center\" class=\"pdn_txtpos2\" style=\"height:20px;font:11px bold;\" id=\"help_box\"><B>Model</B></TD>'+");
					out.println("'<TD WIDTH=\"15%\"     align=\"center\" class=\"pdn_txtpos2\" style=\"height:20px;font:11px bold;\" id=\"help_box\"><B>Sub Model</B></TD>'+");
					out.println("'<TD WIDTH=\"8%\"     align=\"center\" class=\"pdn_txtpos2\" style=\"height:20px;font:11px bold;\" id=\"help_box\"><B>Status</B></TD>'+");
					out.println("'<TD WIDTH=\"8%\"     align=\"center\" class=\"pdn_txtpos2\" style=\"height:20px;font:11px bold;\" id=\"help_box\"><B>Purpose</B></TD>'+");
					out.println("'<TD WIDTH=\"4%\"     align=\"left\" class=\"pdn_txtpos2\" style=\"height:20px;font:11px bold;\" id=\"help_box\"><B>Period</B></TD>'+");
					out.println("'<TD WIDTH=\"5%\"     align=\"center\" bgcolor=\"white\" ></TD>'+");
					out.println("'</TR></table>';");
		     	out.println("}");
						
					
					out.println("function display_data(data_vec){");
				  out.println("m_table.innerHTML=\"\";");
					out.println("header();");
					out.println("i=0;");
					out.println("j=1;");
					out.println("if(data_vec.length==0){");
				  out.println("alert('No Asset Details Exist for the selected Application No.');");
					out.println("}"); 
					out.println("else{");
					out.println("while(i<data_vec.length){");
				  out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\"><TR>'+");			
					out.println("'<TD align=\"left\" WIDTH=\"15%\" class=\"pdn_txtpos2\" style=\"height:16px;font:11px;\" id=\"help_box\"><input type=\"Hidden\" name=hid_TXT_Asset_id'+j+'	VALUE='+data_vec[i]+'><b> '+data_vec[i]+'</td>'+");
					out.println("'<TD align=\"left\" WIDTH=\"15%\" class=\"pdn_txtpos2\" style=\"height:16px;font:11px\" id=\"help_box\"><b>'+data_vec[i+1]+'</td>'+");
					out.println("'<TD align=\"right\" WIDTH=\"10%\" class=\"pdn_txtpos2\" style=\"height:16px;font:11px\" id=\"help_box\"><b>'+data_vec[i+2]+'</td>'+");
					out.println("'<TD align=\"right\" WIDTH=\"10%\" class=\"pdn_txtpos2\" style=\"height:16px;font:11px\" id=\"help_box\"><b>'+data_vec[i+3]+'</td>'+");
					out.println("'<TD align=\"center\" WIDTH=\"10%\" class=\"pdn_txtpos2\" style=\"height:16px;font:11px\" id=\"help_box\"><b>'+data_vec[i+4]+'</td>'+");
					out.println("'<TD align=\"center\" WIDTH=\"15%\" class=\"pdn_txtpos2\" style=\"height:16px;font:11px\" id=\"help_box\"><b>'+data_vec[i+5]+'</td>'+");
					out.println("'<TD align=\"center\" WIDTH=\"8%\" class=\"pdn_txtpos2\" style=\"height:16px;font:11px\" id=\"help_box\"><b>'+data_vec[i+6]+'</td>'+");
					out.println("'<TD align=\"center\" WIDTH=\"8%\" class=\"pdn_txtpos2\" style=\"height:16px;font:11px\" id=\"help_box\"><b>'+data_vec[i+7]+'</td>'+");
					out.println("'<TD align=\"right\" WIDTH=\"6%\" class=\"pdn_txtpos2\" style=\"height:16px;font:11px\" id=\"help_box\"><b>'+data_vec[i+8]+'</td>'+");
					out.println("'<TD align=\"center\"   WIDTH=\"5%\" > <input class=\"mainbut\" style=\"{width:41}\" type=\"button\" name=BUT_VERI'+j+' value=\"Verify\" onClick=load_asset(hid_TXT_Asset_id'+j+',hid_View_status'+j+')><input type=\"Hidden\" name=hid_View_status'+j+'	VALUE=\"N\">'+");
					out.println("'</td></tr></table>';");
					out.println("i=i+9;");
					out.println("j=j+1;");
					out.println("  }"); 
					out.println("m_size=j;"); 
					out.println(" }");
		      out.println("}"); 	
					
					out.println("function load_asset(obj,objv){	"); 
					out.println("		if(obj.value !=''){ "); 
					out.println("		m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Asset_Detail_Equipment?APP_NO='+obj.value+'';"); 
			    out.println("window.open(m_url,'displayWindow3','left=0,top=133,width=800,height=700,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
					out.println("objv.value='Y';"); 
					out.println(" chk_view_status();"); 
					out.println(" }");
					
					out.println(" if(asset_enable_sts=='Y'){ "); 
					out.println("window.opener.document.Form1.Btn_Invoice_Det.disabled=false;"); 
					out.println(" }"); 
					out.println("}"); 
					
					
					out.println("function chk_view_status(){	"); 
					out.println("for(k=1; k<m_size; k++) { "); 
					out.println("var m_hid_View_status='hid_View_status'+k");
					out.println("if(document.Form1.elements[m_hid_View_status].value=='N'){"); 
					out.println(" asset_enable_sts='N';"); 
					out.println(" break;"); 
					out.println("  }"); 
					out.println(" else if(document.Form1.elements[m_hid_View_status].value=='Y'){ "); 
					out.println(" asset_enable_sts='Y';"); 
					out.println("  }");
					out.println(" }"); //end for
					out.println("}"); 
					
					
					out.println("function clear_window(){	"); 
					out.println("		if(confirm(\"Are You Sure?\")){ "); 
					out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Asset_Veri_1?applicaton_no='+m_applicaton_no+'';"); 
					out.println("		}"); 
					out.println("}"); 
					
					
					out.println("</script>"); 
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"makeRequest()\">"); 
					out.println("<FORM NAME='Form1' method='get'>"); 
					
					
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Asset Verification [Application No "+m_applicaton_no+"]</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onclick='window.close()' value=\"Close\"></td>");  
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
					
				  out.println("<table align='center' width='100%'>"); 
				  out.println("<tr>");  
				  out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
			    out.println("</tr>"); 
				
				  out.println("</table>");
					
					out.println("</FORM>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
					out.println("</body>"); 
					out.println("</html>"); 
	  
		}catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
      //return null;
		}finally{
		  //if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			//if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	    //if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e2){}}
			//try{conn.setAutoCommit(true);
		}
	}
}


