

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CR_Purchase_Order_Printing extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	CallableStatement callstmt;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1;
	public String m_chksql,m_no_of_due_days,m_sys_date,m_ac_status;
	ServletOutputStream out = null;
	int m_appno_count=0;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
		//	LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			
		//	String m_html_client_url = m_sn_methods.html_client_url;
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			
   //   String m_username 						= "AA";//m_sn_methods.username;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			
	//		m_chksql         = req.getParameter("chksql");
	//		m_ac_status = req.getParameter("ac_status");
		//	stmt = conn.createStatement ();
		///	stmt1 = conn.createStatement ();
		
		
			
			
     
        out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				
				out.println("var lineno=0;");
			  out.println("var arr_size=0;");
				out.println("var m_app_no=\"\";");
				out.println("var m_ven_code=\"\";");
				
				
			out.println("function get_vector(data_vec) {");
			//out.println("m_user_val=\"TXT_USER\"+document.Form1.hid_row_no.value;");
		
			out.println("			if(data_vec.length>0 ){");
			out.println("    document.Form1.TXT_PUR_ORD_NO.value=data_vec[0];"); 
			
			out.println("m_app_no=data_vec[1];");
			out.println("m_ven_code=data_vec[2];");
			out.println("m_branch_code=data_vec[4];"); //6
			out.println("m_app_stat=data_vec[6];");
			out.println("m_client_code=data_vec[7];");
			
			out.println("if(data_vec[8]=='N'){");
			out.println("    document.Form1.hid_print_status.value='ORIGINAL';"); 
			out.println("			}");
			
			out.println("			else");
			out.println("			{");
			out.println("    document.Form1.hid_print_status.value='COPY';"); 
			out.println("			}");
							
			out.println("			}");
			
			out.println("			else{");
			
			out.println("     help_pur_no();");
      out.println("			}");
			
			
			out.println("			}");
			
			
					
				
				
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
			out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data();");
			out.println("	}else");
			
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
		
			
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		help_pur_no_assign();"); 
	  	out.println("		}"); 
		
		
			out.println("	}"); //end next
			
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			
			out.println("	}"); //end prev
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); ///close
			
			out.println("	else{");
			out.println("	clear_data();");//Added To The Clear The Area Code
			out.println("	}");
			
			
			out.println("	}	"); //
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
			
			
			
			
			out.println("function clear_data() {");
		//  out.println("m_user_clear=\"TXT_USER\"+document.Form1.hid_row_no.value;");
			//out.println("document.Form1.TXT_PUR_ORD_NO.value='';");
				
			out.println("}");
			
			
			
			
				
			
				
	
				
	
			
			
			out.println("function help_pur_no() {"); 
			//out.println("document.Form1.hid_row_no.value=row_No;");
		//	out.println("m_user=\"TXT_USER\"+row_No");
			//out.println("    Crit = document.Form1.TXT_PUR_ORD_NO.value+\"@VERIFY@\";"); 
			out.println("    Crit =document.Form1.TXT_PUR_ORD_NO.value+\"@\"+\"VERIFY@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_PURCHASE_ORDER_NO1','1');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_pur_no_assign() {"); 
			//out.println("m_user_assign=\"TXT_USER\"+row_No");
			out.println("    document.Form1.TXT_PUR_ORD_NO.value=oBj.valout[2];"); 
			
			out.println("m_app_no=oBj.valout[3];");
			out.println("m_ven_code=oBj.valout[4];");
			out.println("m_branch_code=oBj.valout[6];");
			out.println("m_client_code=oBj.valout[8];");
			out.println("m_app_stat=oBj.valout[13];");
			
			out.println("if(oBj.valout[14]=='N'){");
			out.println("    document.Form1.hid_print_status.value='ORIGINAL';"); 
			out.println("			}");
			out.println("			else{");
			out.println("    document.Form1.hid_print_status.value='COPY';"); 
			out.println("			}");
			out.println("}"); 
			
			
			out.println("function val_pur_ord_no(obj){");
				//out.println("assignState('M2')");
		//	out.println("document.Form1.hid_row_no.value=row_No;");
			//out.println("m_user_val=\"TXT_USER\"+row_No");
			
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_PRO_CR_purchase_order_view_lette&data_val=\"+obj.value+\"&ac_status=COMPLETED\";");
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_no_validate_view_Letter&data_val=\"+obj.value+\"&ac_status2=Y&ac_status=VERIFY\";");
						
			out.println("load_interface(m_url,'XML');");
		//	out.println("window.open(m_url);");
			
			out.println("}"); 
			
			
			
			
			
				
				
				
							
										
				
		//--------------------------------------------------------------------------		
		
					
			
			out.println("function validate_data(){"); 
			
			out.println("return true;"); 
		
			out.println("}"); 

			/*out.println("function before_submit(){ "); 
			
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Lease_Assign';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("} "); 
			
			*/
			

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_Purchase_Order_Printing';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_Purchase_Order_Printing';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_RE_Lease_Assign\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Credit Process - Purchase Order Printing - \"+m_val;"); 
			//out.println("if(m_val==\"New\")");
			//out.println("document.Form1.hid_status.value=\"Save\";"); //Added By Nuwan De Silva
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Credit Process - Purchase Order Printing - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();}"); 
			//out.println("document.Form1.BUT_TXT_CITY_CODE.disabled=false;");
		//	out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
		
   
			out.println("}"); 
			out.println("else{}");
			//out.println("document.Form1.BUT_TXT_CITY_CODE.disabled=false;");
			//out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("}else if(m_val==\"LETTER\"){");  
			out.println("document.Form1.hid_status.value=\"Letter\";");  
			out.println("document.Form1.hid_save_status.value=\"Letter\";"); 
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("document.Form1.hid_save_status.value=\"Deactive\";"); 
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("document.Form1.hid_save_status.value=\"Reactive\";"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 
			
			
			out.println("function letter() {"); 
			out.println("if(document.Form1.TXT_PUR_ORD_NO.value==''){");
			out.println("DIV_TXT_PUR_ORD.style.color='red';");
			//out.println("alert('Please enter a purchase order no to view letter')");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("}");
			out.println("else{");
			
			String m_client_code="";
			String m_app_no     ="";
			String m_trn_type   ="";
			
			
			//Modified by Chandana on 14/06/2007 
			out.println("if(m_app_stat==\"HIREPURCH\"){");
			//out.println(" m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Hirepur_ind_delivery_order?chksql=main_page&pur_ord_no=\"+document.Form1.TXT_PUR_ORD_NO.value+\"&application_no=\"+m_app_no+\"&document_code=HP_DELOD&print=TRUE&client_code=\"+m_client_code;	");				
			out.println(" m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Hirepur_ind_delivery_order?chksql=main_page&pur_ord_no=\"+document.Form1.TXT_PUR_ORD_NO.value+\"&application_no=\"+m_app_no+\"&document_code=HP_DELOD&print=TRUE&client_code=\"+m_client_code+\"&vendor_code=\"+m_ven_code+\"&branch_code=\"+m_branch_code;	");				
			out.println("}else{");
			out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Purchase_Order_Letter?chksql=generatereport&pur_ord_no=\"+document.Form1.TXT_PUR_ORD_NO.value+\"&app_no=\"+m_app_no+\"&status=\"+document.Form1.hid_print_status.value+\"&print=TRUE&vendor_code=\"+m_ven_code+\"&branch_code=\"+m_branch_code;"); 
			out.println("}");
			//End Chandana on 14/06/2007 
			out.println("popupwin =window.open(m_url,'displayWindow3','left=110,top=110,width=750,height=800,toolbar=0,location=0,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
			
			out.println("}");
			out.println("}");
			
			out.println("function close_screen(){	"); 
			out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 

			out.println("window.close();");
			out.println("}");
			out.println("}");


						
			
      out.println("</Script>");
			
			
				
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"\">"); //load_lock()
					out.println("<FORM NAME='Form1' method='post'>"); 
				  				
					out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
					out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"Cancel\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			    out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_CR_PUR_ORDER_PRINT\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_print_status' VALUE=\"\">");
					
					
					
										
					out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
					out.println("<tr>"); 
					out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
					out.println("<td class='border_wht' valign='top'> "); 
					out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
					out.println("<tr> "); 
					out.println("<td height='30' class='pdn_mainHD'>"+header_name+"</td>"); 
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Purchase Order Printing</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<td width='10%'></td>");  
					out.println("<td width='10%'></td>");  
					out.println("<td width='10%'></td>");  
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\" ></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_screen()' value=\"Close\"></td>"); 
							
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
					
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>   "); 
					out.println("<tr>"); 
			 				
					out.println("<td width='15%' ><DIV id='DIV_TXT_PUR_ORD'  class=div_input>Purchase Order No *</DIV></td>"); 
					out.println("<td width='15%' ><input class='txt_input' type='text' name='TXT_PUR_ORD_NO' maxlength='15' size='15' onblur=\"help_pur_no()\">&nbsp;"); 
			    out.println("<input class='but_input' type='button' name='BUT_TXT_PUR_ORD_NO' value=\"...\" onClick=\"help_pur_no()\"></td>"); 
					out.println("<td width='20%' align='center' ><input class='but_input' type='button' name='BUT_BCODE' value=\"View\" onClick=\"letter()\" style='{cursor:hand;}'></td>"); 
					out.println("<td width='*%'>&nbsp;</td>");
					out.println("</tr>"); 
          out.println("</table>");          
				
				
					
				
		
				out.println("</form>");
				out.println("</body>");
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("</html>");
  //    }
			
			//=========================================================================================================================			
  	
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
