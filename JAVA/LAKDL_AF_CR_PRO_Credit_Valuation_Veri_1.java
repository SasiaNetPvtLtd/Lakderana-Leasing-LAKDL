//--
//SCREEN NAME : CREDIT VERIFICATION VALUATION
//MODIFIED BY : DELANJALI
//DATE/TIME   : 
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_PRO_Credit_Valuation_Veri_1 extends javax.servlet.http.HttpServlet { 
	 
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
					
					res.setStatus(HttpServletResponse.SC_OK); 
					res.setContentType("text/html"); 
					out = res.getOutputStream(); 
					
					String m_applicaton_no        = req.getParameter("applicaton_no");
						 
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>Credit Process - Valuation Verification</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">"); 
					
					out.println("var m_applicaton_no='"+m_applicaton_no+"'");
					out.println("var valu_enable_sts='Y';");
					out.println("var m_size=0 ;");
					out.println("var x=0 ;");
					
					out.println("function makeRequest() {");
					out.println("if(m_applicaton_no!=''){");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_pop_LAKDL_AF_CR_display_valuation_data&application_no=\"+m_applicaton_no+\"\";");
					//out.println("window.open(m_url);");
					out.println("}");
					out.println("load_interface(m_url,'XML');");
					out.println("}");
					
					out.println("function get_vector(data_vec) {");
			 		out.println("			if(data_vec.length > 0) {");
					out.println("     display_data(data_vec);");
					out.println("			}");
					out.println("	else if(data_vec.length == 0) {");
					out.println("window.opener.document.Form1.Btn_Document_Req.disabled=false;");
					out.println("			}");
				  out.println("}");
					
					//----ADDED BY DELANJALI ON 24/04/2007--------//
					out.println("function show_asset_data(m_asset) {"); 
          out.println("show_asset_detail_drill(m_asset.value);"); 
          out.println("}"); 
	
					//----ADDED BY DELANJALI ON 24/04/2007--------//
					out.println("function show_model(m_model) {"); 
          out.println("show_model_details_drill(m_model.value);"); 
          out.println("}"); 
					
					//----ADDED BY DELANJALI ON 24/04/2007--------//
					out.println("function show_sub(m_model) {"); 
          out.println("show_sub_model_details_drill(m_model.value);"); 
          out.println("}"); 


					//----ADDED BY DELANJALI ON 24/04/2007--------//
					out.println("function show_valuation_data(m_model) {"); 
          out.println("show_valuation_drill(m_model.value);"); 
          out.println("}"); 

					out.println("function display_data(data_vec){");
				  out.println("m_table.innerHTML=\"\";");
					out.println("header();");
					out.println("i=0;");
					out.println("j=1;");
					out.println("if(data_vec.length==0){");
				  out.println("alert('No Valuation Details Exist for the selected Application No.');");
					out.println("}");
					out.println("else{");
					out.println("while(i<data_vec.length){");
					out.println("if(j>0 && j%2==1){");
				  out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\"><TR class=\"tr_input\">'+");			
					out.println("'<TD align=\"left\"  WIDTH=\"10%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_valuation_data(hid_TXT_Valu_no'+j+')\"><U><input type=\"Hidden\" name=hid_TXT_Valu_no'+j+'	VALUE='+data_vec[i]+'>'+data_vec[i]+'</td>'+");
					
					out.println("'<TD align=\"left\" WIDTH=\"10%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_asset_data(hid_TXT_asset'+j+')\"><U>'+data_vec[i+1]+'<input type=\"Hidden\" name=hid_TXT_asset'+j+'	VALUE='+data_vec[i+1]+'></td>'+");
					out.println("'<TD align=\"left\" WIDTH=\"5%\"  style= \"cursor:hand;cursor-color:blue\" onclick=\"show_model(hid_TXT_model'+j+')\"><U>'+data_vec[i+2]+'<input type=\"Hidden\" name=hid_TXT_model'+j+'	VALUE='+data_vec[i+14]+'></td>'+");
					out.println("'<TD align=\"left\" WIDTH=\"15%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_sub(hid_TXT_sub'+j+')\"><U>'+data_vec[i+3]+'<input type=\"Hidden\" name=hid_TXT_sub'+j+'	VALUE='+data_vec[i+15]+'></td>'+");
					out.println("'<TD align=\"left\"  WIDTH=\"5%\"  >'+data_vec[i+4]+'</td>'+");
					out.println("'<TD align=\"left\"  WIDTH=\"8%\"  >'+data_vec[i+5]+'</td>'+");
					out.println("'<TD align=\"left\"  WIDTH=\"8%\"  >'+data_vec[i+11]+'</td>'+");
					out.println("'<TD align=\"left\" WIDTH=\"8%\"  >'+data_vec[i+16]+'</td>'+");
					out.println("'<TD align=\"left\" WIDTH=\"8%\"   >'+data_vec[i+18]+'</td>'+"); //Modified By Nuwan De Silva 23-05-07
					out.println("'<TD align=\"left\" WIDTH=\"8%\"  >'+data_vec[i+17]+'</td>'+"); //Modified By Nuwan De Silva 23-05-07

					out.println("'<TD align=\"center\"  WIDTH=\"5%\" ><input class=\"mainbut\" style=\"{width:50}\" type=\"button\" name=BUT_VERI'+j+' value=\"Verify\" onClick=load_valuation(hid_TXT_Valu_no'+j+',hid_View_status'+j+','+j+')><input type=\"Hidden\" name=hid_View_status'+j+'	VALUE=\"N\">'+");
					out.println("'<input type=\"hidden\" name=hid_chk1_'+j+'	VALUE=\"0\">'+");
					out.println("'</td></tr></table>';");
					out.println("  }"); 
					out.println("else{");
					out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" border=\"0\"  class=\"table\"><TR class=\"tr_input1\">'+");			
					out.println("'<TD align=\"left\" WIDTH=\"10%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_valuation_data(hid_TXT_Valu_no'+j+')\"><U><input type=\"Hidden\" name=hid_TXT_Valu_no'+j+'	VALUE='+data_vec[i]+'>'+data_vec[i]+'</td>'+");
					
					out.println("'<TD align=\"left\" WIDTH=\"10%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_asset_data(hid_TXT_asset'+j+')\"><U>'+data_vec[i+1]+'<input type=\"Hidden\" name=hid_TXT_asset'+j+'	VALUE='+data_vec[i+1]+'></td>'+");
					out.println("'<TD align=\"left\" WIDTH=\"5%\"  style= \"cursor:hand;cursor-color:blue\" onclick=\"show_model(hid_TXT_model'+j+')\"><U>'+data_vec[i+2]+'<input type=\"Hidden\" name=hid_TXT_model'+j+'	VALUE='+data_vec[i+14]+'></td>'+");
					out.println("'<TD align=\"left\" WIDTH=\"15%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_sub(hid_TXT_sub'+j+')\"><U>'+data_vec[i+3]+'<input type=\"Hidden\" name=hid_TXT_sub'+j+'	VALUE='+data_vec[i+15]+'></td>'+");
					
					out.println("'<TD align=\"left\" WIDTH=\"5%\"  >'+data_vec[i+4]+'</td>'+");
					out.println("'<TD align=\"left\" WIDTH=\"8%\"   >'+data_vec[i+5]+'</td>'+");
					out.println("'<TD align=\"left\" WIDTH=\"8%\"  >'+data_vec[i+11]+'</td>'+");
					
					out.println("'<TD align=\"left\" WIDTH=\"8%\"  >'+data_vec[i+16]+'</td>'+");
					out.println("'<TD align=\"left\" WIDTH=\"8%\"   >'+data_vec[i+18]+'</td>'+"); //Modified By Nuwan De Silva 23-05-07
					out.println("'<TD align=\"left\" WIDTH=\"8%\"  >'+data_vec[i+17]+'</td>'+"); //Modified By Nuwan De Silva 23-05-07
				
					out.println("'<TD align=\"center\" WIDTH=\"5%\" ><input class=\"mainbut\" style=\"{width:50}\" type=\"button\" name=BUT_VERI'+j+' value=\"Verify\" onClick=load_valuation(hid_TXT_Valu_no'+j+',hid_View_status'+j+','+j+')><input type=\"Hidden\" name=hid_View_status'+j+'	VALUE=\"N\">'+");
					out.println("'<input type=\"hidden\" name=hid_chk1_'+j+'	VALUE=\"0\">'+");
					out.println("'</td></tr></table>';");
					out.println("  }"); 
					out.println("i=i+19;");
					out.println("j=j+1;");
					out.println("  }"); 
					out.println("m_size=j;"); 
					out.println(" }");
		      out.println("}"); 	
		
		
					
					out.println("function header(){");
					out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\" ><TR class=\"pdn_txtpos2\">'+");
					out.println("'<TD WIDTH=\"10%\"     align=\"left\"   id=\"help_box\"><B>Valuation No</B></TD>'+");
					out.println("'<TD WIDTH=\"10%\"     align=\"left\"   id=\"help_box\"><B>Asset ID</B></TD>'+");
					out.println("'<TD WIDTH=\"5%\"     align=\"left\"   id=\"help_box\"><B>Model</B></TD>'+");
					out.println("'<TD WIDTH=\"15%\"     align=\"left\"   id=\"help_box\"><B>Sub Model</B></TD>'+");
					out.println("'<TD WIDTH=\"5%\"     align=\"left\"   id=\"help_box\"><B>Reg.No</B></TD>'+");
					out.println("'<TD WIDTH=\"8%\"     align=\"left\"   id=\"help_box\"><B>Engine No</B></TD>'+");
					out.println("'<TD WIDTH=\"8%\"     align=\"left\"  id=\"help_box\"><B>Valuation Date</B></TD>'+");
					out.println("'<TD WIDTH=\"8%\"     align=\"left\"  id=\"help_box\"><B>Valuer Code</B></TD>'+");
					out.println("'<TD WIDTH=\"8%\"     align=\"left\"  id=\"help_box\"><B>Year Of Manufacture</B></TD>'+");
					out.println("'<TD WIDTH=\"8%\"     align=\"left\"  id=\"help_box\"><B>Date Of Reg</B></TD>'+");
	
					
					out.println("'<TD WIDTH=\"5%\" class=\"pdn_txtpos2\" align=\"center\" bgcolor=\"white\" ></TD>'+");
					out.println("'</TR></table>';");
		     	out.println("}");
					
					
					out.println("function load_valuation(obj,objv,valu){	");
					out.println("hidchk='hid_chk1_'+valu");
					out.println("document.Form1.hid_chk_but.value=valu");
					out.println("		if(obj.value !=''){ "); 
					out.println("		m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_inspection_and_valuation_report?APP_NO='+m_applicaton_no+'&valuation_no='+obj.value+'&my_screen_name=CV&row='+valu+''");
			    out.println("popupwin=window.open(m_url,'displayWindow3','left=60,top=10,width=700,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=yes');"); 
					out.println("objv.value='Y';"); 
					out.println(" chk_view_status();"); 
					out.println(" }");
					out.println(" if(valu_enable_sts=='Y' && document.Form1.elements[hidchk].value!=0){ "); 
					out.println(" }"); 
					out.println("}"); 
					
					
					out.println("function chk_view_status(){	"); 
					out.println("for(k=1; k<m_size; k++) { "); 
					out.println("var m_hid_View_status='hid_View_status'+k");
					out.println("hidchk='hid_chk1_'+k");
					out.println("if(document.Form1.elements[m_hid_View_status].value=='N'){"); 
					out.println(" valu_enable_sts='N';"); 
					out.println(" break;"); 
					out.println("  }"); 
					out.println(" else if(document.Form1.elements[m_hid_View_status].value=='Y'){ "); 
					out.println(" valu_enable_sts='Y';"); 
					out.println("  }");
					out.println(" }"); //end for
					out.println("}"); 
					
					
					out.println("function clear_window(){	"); 
					out.println("		if(confirm(\"Are You Sure?\")){ "); 
					out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Valuation_Veri_1?applicaton_no='+m_applicaton_no+'';"); 
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
					out.println(" if(valu_enable_sts=='Y' && document.Form1.hid_chk_but.value==m_size-1 && document.Form1.elements[hidchk].value!=\"0\"){ ");
					out.println("window.opener.document.Form1.Btn_Document_Req.disabled=false;"); 
					out.println(" }");
					out.println("}");
					out.println("}");
					
					out.println("</script>"); 
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"makeRequest()\" onunload=\"check_value()\">"); 
					out.println("<FORM NAME='Form1' method='get'>"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_chk_but' VALUE=\"0\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_chk' VALUE=\"0\">");

					
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Valuation Verification [Application No "+m_applicaton_no+"]</td>"); 
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
									out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");

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


