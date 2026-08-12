//--
//SCREEN NAME : CREDIT VERIFICATION GUARANTOR
//MODIFIED BY : DELANJLI
//DATE/TIME   : 
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_PRO_Credit_Guarantor_Ver_1 extends javax.servlet.http.HttpServlet { 
	 
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
			
					res.setStatus(HttpServletResponse.SC_OK); 
					res.setContentType("text/html"); 
					out = res.getOutputStream(); 
					

					
				  String m_username 						= "AA";//m_sn_methods.username;
					String m_applicaton_no        = req.getParameter("applicaton_no");
					
	 				String m_data_val1 = req.getParameter("data_val1");
		
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>Credit Process - Guarantor Verification</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">"); 
					
					out.println("var m_applicaton_no='"+m_applicaton_no+"'");
					out.println("var asset_enable_sts='Y';");
					out.println("var m_size=0 ;");
					out.println("var x=0");
					
					out.println("var new_data_vec=new Array();"); //----ADDED BY CHANDANA ON 23/04/2007--------//
					
					out.println("function makeRequest() {");
					out.println("if(m_applicaton_no!=''){");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_pop_LAKDL_AF_CR_display_guarantor_data&application_no=\"+m_applicaton_no+\"\";");
					out.println("}");
					out.println("load_interface(m_url,'XML');");
					out.println("}");
					
				
					out.println("function get_vector(data_vec) {");
					out.println(" new_data_vec=data_vec;");
			 		out.println("			if(data_vec.length > 0) {");
					out.println("     display_data(data_vec);");
					out.println("			}");
					out.println("	else if(data_vec.length == 0) {");
					out.println("window.opener.document.Form1.Btn_Asset_Det.disabled=false;"); 
					out.println("			}");
				  out.println("}");
					
						//----ADDED BY CHANDANA ON 23/04/2007--------//
					out.println("function show_client_details(row_No) {"); 
          out.println("show_client(new_data_vec[row_No]);"); 
          out.println("}"); 
					//-------END --CHANDANA ON 23/04/2007--------//
					
					
					out.println("function display_data(data_vec){");
					
					out.println("var m_status1=\"\" ");
					out.println("var m_status2=\"\" ");
					out.println("var m_status3=\"\" ");
					
				  out.println("m_table.innerHTML=\"\";");
					out.println("header();");
					out.println("i=0;");
					out.println("j=1;");
					out.println("if(data_vec.length==0){");
				  out.println("alert('No Guarantors Exist for the selected Application No.');");
					out.println("}");
					out.println("else{");
					out.println("while(i<data_vec.length){");
					out.println("if(j>0 && j%2==1){");
					
					out.println("if(data_vec[i+8]!=\"-\" && data_vec[i+9]!=\"-\" && (data_vec[i+10]!=\"-\" || data_vec[i+10]==\"-\")){");
			  	out.println("m_status1=data_vec[i+8]+\",\";");
					out.println("if(data_vec[i+10]!=\"-\"){");
					out.println("m_status2=data_vec[i+9]+\",\";");
					out.println("m_status3=data_vec[i+10];");
					out.println("}");
					out.println("else {");
					out.println("m_status2=data_vec[i+9];");
					out.println("m_status3=\"\";");
					out.println("}");
					
					
					out.println("}");
					out.println("else if(data_vec[i+8]!=\"-\" && data_vec[i+9]==\"-\" && data_vec[i+10]!=\"-\"){");
					out.println("m_status1=data_vec[i+8]+\",\";");
					out.println("m_status2=\"\";");
					out.println("m_status3=data_vec[i+10];");
					out.println("}");
					
					out.println("else {");
					
					out.println("if(data_vec[i+8]!=\"-\"){");
					out.println("m_status1=data_vec[i+8];");
					out.println("}");
					out.println("else{");
					out.println("m_status1=\"\";");
					out.println("}");
					
					out.println("if(data_vec[i+9]!=\"-\"){");
					out.println("m_status2=data_vec[i+9];");
					out.println("}");
					out.println("else{");
					out.println("m_status2=\"\";");
					out.println("}");

					out.println("if(data_vec[i+10]!=\"-\"){");
					out.println("m_status3=data_vec[i+10];");
					out.println("}");
					out.println("else{");
					out.println("m_status3=\"\";");
					out.println("}");
					
					out.println("}");
					
					out.println("var m_status=\"\"; ");
					
					out.println("if(m_status1==\"\" && m_status2==\"\" && m_status3==\"\"){");
					out.println("m_status=\"-\" ");
					out.println("}");
					out.println("else{");
					out.println("m_status=m_status1+m_status2+m_status3;");
					out.println("}");
					
					

										
				  out.println("m_table.innerHTML+='<table align=\"center\"  border=\"0\" width=\"100%\" class=\"table\"><TR class=\"tr_input\">'+");			
					out.println("'<TD align=\"left\"  WIDTH=\"10%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client_details('+[i+1]+')\"  id=\"help_box\"> <input type=\"Hidden\" name=hid_TXT_Guarantor_Code'+j+'	VALUE='+data_vec[i+1]+'><U>'+data_vec[i+1]+'</U></td>'+");
					out.println("'<TD align=\"left\"  WIDTH=\"20%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client_details('+[i+1]+')\"  id=\"help_box\"><U>'+data_vec[i+2]+'</U></td>'+");
					out.println("'<TD align=\"left\"  WIDTH=\"15%\"  id=\"help_box\">'+data_vec[i+3]+'</td>'+");
					out.println("'<TD align=\"left\"    WIDTH=\"15%\"  id=\"help_box\">'+data_vec[i+4]+'</td>'+");
					out.println("'<TD align=\"left\"   WIDTH=\"10%\"  id=\"help_box\">'+data_vec[i+5]+'</td>'+");
					
					out.println("'<TD align=\"left\"  WIDTH=\"20%\"  id=\"help_box\">'+m_status+'</td>'+");//status
					
					out.println("'<TD align=\"left\"   WIDTH=\"10%\" ><input class=\"mainbut\" style=\"{width:50}\" type=\"button\" name=BUT_VERI'+j+' value=\"Verify\" onClick=load_guarantor(hid_TXT_Guarantor_Code'+j+',hid_View_status'+j+',hid_inquiry'+j+',hid_ctype'+j+','+j+')>'+");
					out.println("'<input type=\"Hidden\" name=hid_View_status'+j+'	VALUE=\"N\">'+");
					out.println("'<input type=\"Hidden\" name=hid_ctype'+j+'	VALUE='+data_vec[i+6]+'>'+");
					out.println("'<input type=\"Hidden\" name=hid_inquiry'+j+'	VALUE='+data_vec[i+7]+'></td>'+");
					out.println("'<input type=\"hidden\" name=hid_chk1_'+j+'	VALUE=\"0\"></td>'+");
					out.println("'</tr></table>';");
					out.println("  }");
					out.println("else{");
				  out.println("m_table.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\"><TR class=\"tr_input1\">'+");			
					out.println("'<TD align=\"left\"  WIDTH=\"10%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client_details('+[i+1]+')\" id=\"help_box\"> <input type=\"Hidden\" name=hid_TXT_Guarantor_Code'+j+'	VALUE='+data_vec[i+1]+'><U>'+data_vec[i+1]+'</U></td>'+");
					out.println("'<TD align=\"left\"  WIDTH=\"20%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client_details('+[i+1]+')\" id=\"help_box\"><U>'+data_vec[i+2]+'</U></td>'+");
					out.println("'<TD align=\"left\"  WIDTH=\"15%\"  id=\"help_box\">'+data_vec[i+3]+'</td>'+");
					out.println("'<TD align=\"left\"   WIDTH=\"15%\"  id=\"help_box\">'+data_vec[i+4]+'</td>'+");
					out.println("'<TD align=\"left\"   WIDTH=\"10%\"  id=\"help_box\">'+data_vec[i+5]+'</td>'+");
					out.println("'<TD align=\"left\"  WIDTH=\"20%\"  id=\"help_box\">'+data_vec[i+8]+data_vec[i+9]+data_vec[i+10]+'</td>'+");//status
					
					out.println("'<TD align=\"left\"   WIDTH=\"10%\" ><input class=\"mainbut\" style=\"{width:50}\" type=\"button\" name=BUT_VERI'+j+' value=\"Verify\" onClick=load_guarantor(hid_TXT_Guarantor_Code'+j+',hid_View_status'+j+',hid_inquiry'+j+',hid_ctype'+j+','+j+')>'+");
					out.println("'<input type=\"Hidden\" name=hid_View_status'+j+'	VALUE=\"N\">'+");
					out.println("'<input type=\"Hidden\" name=hid_ctype'+j+'	VALUE='+data_vec[i+6]+'>'+");
					out.println("'<input type=\"Hidden\" name=hid_inquiry'+j+'	VALUE='+data_vec[i+7]+'></td>'+");
					out.println("'<input type=\"hidden\" name=hid_chk1_'+j+'	VALUE=\"0\"></td>'+");
					out.println("'</tr></table>';");
					out.println("  }");
					out.println("i=i+11;");
					out.println("j=j+1;");
					out.println("  }"); 
					out.println("m_size=j;"); 
					out.println(" }");
		      out.println("}"); 	
					
					
					out.println("function header(){");
					out.println("m_table.innerHTML+='<table align=\"center\"  border=\"0\" width=\"100%\" class=\"table\" border=\"0\"><TR class=\"pdn_txtpos2\">'+");
					out.println("'<TD WIDTH=\"10%\"     align=\"left\" id=\"help_box\"><B>Guarantor Code</B></TD>'+");
					out.println("'<TD WIDTH=\"20%\"     align=\"left\"  id=\"help_box\"><B>Guarantor Name</B></TD>'+");
					out.println("'<TD WIDTH=\"15%\"     align=\"left\" id=\"help_box\"><B>Registered Address1</B></TD>'+");
					out.println("'<TD WIDTH=\"15%\"     align=\"left\"  id=\"help_box\"><B>Registered Address2</B></TD>'+");
					out.println("'<TD WIDTH=\"10%\"     align=\"left\" id=\"help_box\"><B>NIC No/Reg.No</B></TD>'+");
					out.println("'<TD WIDTH=\"20%\"     align=\"left\" id=\"help_box\"><B>Client Status</B></TD>'+");
					out.println("'<TD WIDTH=\"10%\"   class=\"pdn_txtpos2\"  align=\"center\" bgcolor=\"white\" ></TD>'+");
					out.println("'</TR></table>';");
		     	out.println("}");
				
					out.println("function load_guarantor(obj,objv,val,ctype,valu){	"); 
					out.println("hidchk='hid_chk1_'+valu");
					out.println("document.Form1.hid_chk_but.value=valu");
					out.println("document.Form1.elements[hidchk].value=\"0\";");
					out.println("document.Form1.elements[hidchk].checked=false;");
					
					out.println("		if(obj.value !=''){ "); 
					out.println("		m_url='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_guarantor_creation?client_type='+ctype.value+'&inquiry_no='+val.value+'&close_status=Y&screen=G&client_code='+obj.value+'&row='+valu+''");
					
					//=========comment by nuwan de silva 29-05-07===========================================================
					//out.println("     m_url='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?client_type='+ctype.value+'&inquiry_no='+val.value+'&screen=G&close_status=Y&client_code='+obj.value+'&row='+valu+''");
					
					out.println("popupwin=window.open(m_url,'displayWindow3','left=60,top=10,width=700,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=yes');"); 
					out.println("objv.value='Y';"); 
					out.println(" chk_view_status();"); 
					out.println(" }");
  				out.println("}"); 
					
					out.println("function chk_view_status(){	"); 
					out.println("for(k=1; k<m_size; k++) { "); 
					out.println("var m_hid_View_status='hid_View_status'+k");
					out.println("m_hidchk='hid_chk1_'+k");
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
					out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Guarantor_Ver_1?applicaton_no='+m_applicaton_no+'';"); 
					out.println("		}"); 
					out.println("}"); 
				
					out.println("function close_1(){	"); 
					out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
					out.println("		window.close()"); 
					out.println("}");
					out.println("		}"); 
					
					
					out.println("function check_value(){	");
					out.println("for(k=1; k<m_size; k++) { "); 
					out.println("hidchk='hid_chk1_'+k");
					out.println(" if(asset_enable_sts=='Y' && document.Form1.hid_chk_but.value==m_size-1 && document.Form1.elements[hidchk].value!=\"0\"){ ");
					
					if(m_data_val1.equals("VERIFY-M") || m_data_val1.equals("VERIFY1")){
					out.println("window.opener.document.Form1.Btn_approve.disabled=false;"); 
					}
					out.println("window.opener.document.Form1.Btn_Asset_Det.disabled=false;"); 
					out.println(" }");
					out.println("}");
					out.println("}");

					out.println("</script>"); 
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"makeRequest()\" onunload=\"check_value()\">"); 
					out.println("<FORM NAME='Form1' method='get'>"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_chk' value=\"0\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_chk_but' value=\"0\">");
					
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Guarantor Verification [Application No "+m_applicaton_no+"]</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table width='100%' class='table' border='0'> "); 
					out.println("<tr><td width='10%' align='right'><input type=\"button\" class='mainbut' onclick='close_1()' value=\"Close\"></td>");  
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
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
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


