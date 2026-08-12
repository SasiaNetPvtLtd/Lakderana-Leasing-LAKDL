//--
//SCREEN NAME:APPLICATION PROCESS
//CREATED BY:
//DATE/TIME:
//NOTES:

//===modified by nuwan de silva 20-06-07==============================================

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MK_Asset_Detail_Equipment extends javax.servlet.http.HttpServlet { 
	 
	//ServletOutputStream out =  null;
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res) { 
	public void service(HttpServletRequest req, HttpServletResponse res) { 
		
		ServletOutputStream out =  null;
		Statement stmt1 = null; // added by udara 04-09-2019
		ResultSet rs1 = null; // added by udara 04-09-2019
		Connection conn; // added by udara 04-09-2019
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
					
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			
		//	String m_html_client_url = m_sn_methods.html_client_url;
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			
			String m_username 						= "AA";//m_sn_methods.username;
			String m_row_no=""; //added by nuwan de silva 22-10-07
				 
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			conn = m_sn_methods.met_user_validate(req);  // added by udara 04-09-2019
			stmt1=conn.createStatement(); // added by udara 04-09-2019
			 
				
			String m_screen = "xx";
			m_screen=req.getParameter("screen");
			if(req.getParameter("screen")==null){
			m_screen= "xx";
			}
			
			
			String m_my_screen="";
			String m_inqNo=""; //added  by nuwan de silva 26-06-07
			
			m_my_screen =  req.getParameter("my_screen_name");
			
			if(m_my_screen==null){
			m_my_screen="";
			
			}
			String m_data_val1 ="";
			m_data_val1= req.getParameter("data_val1");

			if(m_data_val1==null){
			m_data_val1="";
			}

			//String m_chksql = req.getParameter("chksql");
			String m_appNo = req.getParameter("APP_NO");
			
			if(req.getParameter("INQ_NO")!=null){ //added by nuwan de silva 26-06-07
			m_inqNo=req.getParameter("INQ_NO");
			}
			
			if(req.getParameter("row")!=null){ //added by nuwan de silva 26-06-07
			m_row_no=req.getParameter("row");
			}
			
			// added by udara 10-12-2014
			String m_brk_status    = "";
			String m_direct_status = "";
			
			if(req.getParameter("brk_status")!=null){ 
			    m_brk_status=req.getParameter("brk_status");
			}
			
			if(req.getParameter("direct_status")!=null){ 
			    m_direct_status=req.getParameter("direct_status");
			}			
			// end by udara 10-12-2014
			
			// added by udara 04-09-2019
			String purpose_option_set = "";

			rs1 = stmt1.executeQuery (" "+
						" SELECT NVL(CODE,'-') CODE, NVL(DESCRIPTION,' ') DESCRIPTION "+
						" FROM  "+m_schema_name+".AF_MAS_ASSET_PURPOSE A "+
						" WHERE ACTIVE_STATUS = 'Y'  "+
						" ORDER BY ITEM_ORDER "+
						" ");
				
			while(rs1.next()){
				purpose_option_set = purpose_option_set + "<option value=\""+rs1.getString("CODE")+"\" >"+rs1.getString("DESCRIPTION")+"</option>";
			}
			// end by udara 04-09-2019
			
			
			if (m_appNo.trim().equals("")) {
				out.println("idle");
			}
			else { // if (m_appNo.trim().equals("APP_NO")) {
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>Application Process - Details of Asset</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">"); 
					
					
			out.println("var lineno=0;");
			out.println("var arr_size=0;");
			out.println("var b_flag=0;"); //Boolean Variable To Hold The Status.
			out.println("var sum=0;");
			out.println("var b_state=0;");
			out.println("var b_asset_no_present=0;");
			out.println("var count_asset_no=0;");
			out.println("var count_number=0;");
			out.println("var m_row_no='"+m_row_no+"';"); //added by nuwan de silva on 22-10-07
			
			
			out.println("var chk_arry=new Array();");	
			
			out.println("chk_arry[0]=\"0\";");	
						
			out.println("var array_asset_no=new Array();");
			out.println("var array_make=new Array();");
			out.println("var array_model=new Array();");
			out.println("var array_sub_model=new Array();");
			out.println("var array_status=new Array();");
			//out.println("var array_supplier=new Array();");
			out.println("var array_cost=new Array();");
			out.println("var array_purpose=new Array();");
			//out.println("var array_location=new Array();");
			//out.println("var array_city=new Array();");
			//out.println("var array_period=new Array();");
			out.println("var array_quantity=new Array();");
			
			
			//=======added by nuwan de silva 21-05-07-------
			
			out.println("var array_hid_make=new Array();");
			out.println("var array_hid_model=new Array();");
			out.println("var array_hid_sub_model=new Array();");
			
			out.println("var array_item_sub_cat=new Array();");
			out.println("var array_year_of_manufacture=new Array();");
			
			out.println(" var purpose_option_set = '"+purpose_option_set+"'; ");

			//===============================================
			
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			
			out.println("function load_main_menu(val){");
			out.println(" document.Form1.TXT_APPLICATION_NO.value=\""+m_appNo+"\";"); 
		
		  out.println(" if (val==1){");
			out.println("document.Form1.SCREEN_NAME.value=\"EDIT\"");
			out.println("     load_screen_status('EDIT');");
			out.println("    assignState('M7');"); 
			out.println("    makeRequest(document.Form1.TXT_APPLICATION_NO);");
			out.println("    load_roll_value('Edit')");
			out.println("}");
			out.println("else{");
			out.println("load_roll_value('New')");
			out.println("}");
			
			out.println("}");
			
			out.println("function load_value(val){");
						
			out.println("    document.Form1.TXT_APPLICATION_NO.value=\""+m_appNo+"\";"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
			out.println("load_roll_value('New')");
			out.println("}");
			
	
			
			out.println("function load_application_no(){");
				
			out.println("assignState('M1')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Asset_Detail_Equipment&data_val="+m_appNo+"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
					
			out.println("}"); 
			
		
			
			out.println("function enable_app_no(){");
		
			out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\"){");
			//out.println("    document.Form1.TXT_APPLICATION_NO.value=\"\";"); 
			//out.println("    document.Form1.TXT_APPLICATION_NO.disabled=false;"); 
			out.println("}");
			out.println("}");
			
		/*	out.println("function close_screen() {");
			
			out.println("	if(document.Form1.close3.value==\"Proceed to Next Level\"){");

			out.println("		if(confirm(\"Are you sure you want to Proceed to Next Level?\")){ "); 
			out.println("		window.close();"); 
			out.println("		}"); 
			out.println("		}"); 
			*/
			/*out.println("	if(document.Form1.close2.value==\"Close\"){");

			out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		window.close();"); 
			out.println("		}"); 
			out.println("		}"); 
			
						out.println("}");
			*/
			
			
			out.println("function close_screen() {");
			
			out.println("	if(document.Form1.close3.value==\"Proceed to Next Level\"){");
			out.println(" if(document.Form1.CHK_ACK.checked==true ){ ");
			out.println("		if(confirm(\"Are you sure you want to Proceed to Next Level?\")){ "); 
			//out.println("		alert('sdf'+m_row_no);"); 
			out.println("		window.close();"); 
			out.println("window.opener.document.Form1.elements[\"CHK_CDA_\"+m_row_no].checked=true; ");
			out.println("window.opener.document.Form1.elements[\"CHK_CDA_\"+m_row_no].value='YES'; ");
			out.println("window.opener.check_change_C(m_row_no);");
			
			out.println("		}"); 
			out.println(" }");
			out.println(" else { ");
			out.println(" alert('Please check acknowledge to proceed next level'); ");
			out.println(" } ");
			out.println("		}"); 
			
			out.println("	else if(document.Form1.close3.value==\"Close\"){");
			out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		window.close();"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("}");

			

			
			out.println("function close_screen2() {");
			out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		window.close();"); 
			out.println("		}"); 
			out.println("}");


			out.println("function header(){");
		/*	out.println("m_table.innerHTML+='<table align=\"center\" width=\"1180\" border=\"0\" class=\"table\"><TR>'+");
			out.println("'<TD WIDTH=\"180\"    align=\"left\"><B>Make</B></TD>'+");
			//out.println("'<TD WIDTH=\"50\"     align=\"left\">&nbsp;</B></TD>' +");
			out.println("'<TD WIDTH=\"200\"    align=\"left\"><B>Model</B></TD>'+");
			//out.println("'<TD WIDTH=\"50\"     align=\"left\">&nbsp;</B></TD>' +");
			out.println("'<TD WIDTH=\"200\"    align=\"left\"><B>Sub Model</B></TD>'+");
			//out.println("'<TD WIDTH=\"50\"     align=\"left\">&nbsp;</B></TD>' +");
			out.println("'<TD WIDTH=\"100\"     align=\"left\"><B>Item Sub Category</B></TD>'+");
			out.println("'<TD WIDTH=\"100\"     align=\"left\"><B>Year of Manufacture</B></TD>'+");
			out.println("'<TD WIDTH=\"100\"    align=\"left\"><B>Status</B></TD>' +");
			out.println("'<TD WIDTH=\"100\"     align=\"left\"><B>Purpose</B></TD>' +");
			out.println("'<TD WIDTH=\"50\"     align=\"left\"><B>Quantity</B></TD>' +");
			out.println("'<TD WIDTH=\"100\"     align=\"left\"><B>Asset No</B></TD>'+");
			out.println("'<TD WIDTH=\"50\"     align=\"left\">&nbsp;</B></TD>' +");
			out.println("'</TR></table>';");
     	
			*/
			
			//comment by nuwan de silva on 10-09-07----------------------------
			/*	out.println("m_table.innerHTML+='<table align=\"center\" width=\"1140\" class=\"table\" border=\"0\" >'+ ");//cellspacing=\"0\" cellpadding=\"0\"
				out.println(" '<tr>'+ ");
				out.println("   '<td width=\"110\"><b>Make</td>'+ ");
				out.println("   '<td width=\"60\">&nbsp;</td>'+ "); 
				out.println("   '<td width=\"110\"><b>Model</td>'+ ");
				out.println("   '<td width=\"60\">&nbsp;</td>'+ ");
				out.println("   '<td width=\"140\"><b>Sub Model</td>'+ ");
				out.println("   '<td width=\"60\">&nbsp;</td>'+ ");
				out.println("   '<td width=\"100\"><b>Item Sub Category</td>'+ ");
				out.println("   '<td width=\"100\"><b>Year of Manufacture</td>'+ ");
				out.println("   '<td width=\"100\"><b>Status</td>'+ ");
				out.println("   '<td width=\"100\"><b>Purpose</td>'+ ");
				out.println("   '<td width=\"100\"><b>Asset No</td>'+ ");
				out.println("   '<td width=\"50\"><b>Quantity</td>'+ ");
				out.println("   '<td width=\"50\">&nbsp;</td>'+ ");
				out.println("   '</tr>'+ ");
				out.println("   '</table>';");
				*/
				
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"940\" class=\"table\" border=\"0\" >'+ ");//cellspacing=\"0\" cellpadding=\"0\"
				out.println(" '<tr>'+ ");
				//out.println("   '<td width=\"140\"><b>Sub Model</td>'+ ");
				//out.println("   '<td width=\"60\">&nbsp;</td>'+ ");
				out.println("   '<td width=\"110\"><b>Model</td>'+ ");
				out.println("   '<td width=\"60\">&nbsp;</td>'+ ");
				out.println("   '<td width=\"110\"><b>Make</td>'+ ");
				out.println("   '<td width=\"60\">&nbsp;</td>'+ "); 
				out.println("   '<td width=\"50\"><b>Quantity</td>'+ ");
				out.println("   '<td width=\"100\"><b>Item Sub Category</td>'+ ");
				out.println("   '<td width=\"100\"><b>Year of Manufacture</td>'+ ");
				out.println("   '<td width=\"100\"><b>Status</td>'+ ");
				out.println("   '<td width=\"100\"><b>Facility Purpose</td>'+ ");
				out.println("   '<td width=\"100\"><b>Asset No</td>'+ ");
				//out.println("   '<td width=\"50\"><b>Quantity</td>'+ ");
				out.println("   '<td width=\"50\">&nbsp;</td>'+ ");
				out.println("   '</tr>'+ ");
				out.println("   '</table>';");
				
			out.println("}");
			
			out.println("function validate_make(rowNo){");
			
			out.println("m_make=\"TXT_MAKE\"+rowNo");
			out.println("assignState('M2')");
			out.println("document.Form1.hid_row_no.value=rowNo;"); 
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_make_creation&data_val=\"+document.Form1.elements[m_make].value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
		  
			out.println("}");	
			
			out.println("function validate_model(rowNo){");
			
			out.println("m_model=\"TXT_MODEL\"+rowNo");
			out.println("assignState('M3')");
			out.println("document.Form1.hid_row_no.value=rowNo;"); 
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_model_creation&data_val=\"+document.Form1.elements[m_model].value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
		  
			out.println("}");	
			
			out.println("function validate_sub_model(rowNo){");
			
			out.println("m_sub_model=\"TXT_SUB_MODEL\"+rowNo");
			out.println("assignState('M4')");
			out.println("document.Form1.hid_row_no.value=rowNo;"); 
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_sub_model&data_val=\"+document.Form1.elements[m_sub_model].value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
		  
			out.println("}");	
			
			out.println("function validate_city(rowNo){");
			
			out.println("m_city=\"TXT_CITY\"+rowNo");
			out.println("assignState('M5')");
			out.println("document.Form1.hid_row_no.value=rowNo;"); 
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_city&data_val=\"+document.Form1.elements[m_city].value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
		  
			out.println("}");	
			
			/*out.println("function validate_supplier(rowNo){");
			
			out.println("m_city=\"TXT_SUPPLIER\"+rowNo");
			out.println("assignState('M6')");
			out.println("document.Form1.hid_row_no.value=rowNo;"); 
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_vendor_creation1&data_val=\"+document.Form1.elements[m_city].value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
		  
			out.println("}");	*/
			
			
							
			
			
			out.println("function enable_select(data_vec){");
		//	out.println("alert('data'+data_vec.length);");
			out.println("var i=0;");
			out.println("var j=0;");
		
			out.println("while(j<data_vec.length){");;
			out.println("m_status=\"TXT_STATUS\"+i");
			out.println("m_purpose=\"TXT_PURPOSE\"+i");
			
			//out.println("alert('data_vec[j+4]'+data_vec[j+4]+'data_vec[j+7]'+data_vec[j+4])");
			out.println("document.Form1.elements[m_status].value=data_vec[j+7]");
			out.println("document.Form1.elements[m_purpose].value=data_vec[j+9]");
		//	out.println("alert('val1'+data_vec[j+4]);");
			//out.println("alert('val2'+data_vec[j+7]);");
			out.println("j=j+13;");
			out.println("i=i+1;");
			out.println("}");
			out.println("}");
			
			
			
			out.println("function disable_asset_no(){");
			
			out.println("for(var i=0;i<arr_size;i++){");
			out.println("m_asset_no=\"TXT_ASSET_NO\"+i");
			out.println("document.Form1.elements[m_asset_no].disabled=true;");
			out.println("}");		
	  	out.println("}");		
			
			/*----------------------------------------------------------------
		Purpose  : Display Gurantor Details
	
	    ----------------------------------------------------------------*/			

			out.println("function display_data(data_vec){");
			
		//	out.println("alert('display');");	
		  out.println("sum=0;");
			out.println("m_table.innerHTML=\"\"");
			out.println("header();");
						
		  out.println("j=0;");
			out.println("i=0;");
			
			out.println("if(data_vec.length==0){");
			
			  			
							
				/*out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<TD WIDTH=\"5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ASSET_NO'+j+' maxlength=\"10\" size=\"10\" value=\"\"></TD>'+");
				out.println("'<TD WIDTH=\"5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MAKE'+j+' maxlength=\"15\" size=\"15\" value=\"\" onblur=\"validate_make('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_MAKE_HELP'+j+' value=\"Help\" onClick=\"help_button_1('+j+')\"></TD>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_MAKE'+j+'	VALUE=\"\">'+");

			 	out.println("'<TD WIDTH=\"5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MODEL'+j+' STYLE=\"width:100\" maxlength=\"15\" size=\"15\" value=\"\" onblur=\"validate_model('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_2('+j+')\"></TD>'+");
 				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_MODEL'+j+'	VALUE=\"\">'+");
				
				out.println("'<TD WIDTH=\"10%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_SUB_MODEL'+j+' STYLE=\"width:100\" maxlength=\"15\" size=\"15\" value=\"\" onblur=\"validate_sub_model('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_SUB_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_3('+j+')\"></TD>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+j+'	VALUE=\"\">'+");

				
				
				out.println("'<td width=\"10%\" align=\"left\" ><select name=TXT_STATUS'+j+' STYLE=\"width:100\" class=\"txt_input\">'+");
				                                                    out.println("'<option value=\"N\" >New           </option>'+");
			                                                      out.println("'<option value=\"R\" >Re-Conditioned</option>'+");
																														out.println("'<option value=\"U\" >Used          </option></select></TD>'+");
			                        
				
				out.println("'<td width=\"5%\" align=\"left\" ><select name=TXT_PURPOSE'+j+' STYLE=\"width:50\" class=\"txt_input\">'+");
				                                                    out.println("'<option value=\"P\" >Personal</option>'+");
			                           														out.println("'<option value=\"B\" >Business</option></select></TD>'+");
			 
				
				out.println("'<TD WIDTH=\"10%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+j+' maxlength=\"5\" style=\"width:60px; text-align:right;\" size=\"5\" value=\"\" onblur=\"val_quantity('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\"></TD>'+");
				out.println("'</tr></table>';");
				*/
								
				
				//comment by nuwan de silva on 10-09-07-----------------------------------
				/*out.println("m_table.innerHTML+='<table width=\"1140\" align=\"center\" class=\"table\" border=\"0\" >'+ "); //cellspacing=\"0\" cellpadding=\"0\"
				out.println(" '<tr>'+ ");
				
				out.println(" '<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MAKE'+j+' maxlength=\"15\" STYLE=\"width:100\" size=\"15\" onblur=\"validate_make('+j+')\">'+");
				out.println(" '<input type=\"Hidden\" NAME=HID_TXT_MAKE'+j+'	value=\"\"></td>'+");
				out.println(" '<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MAKE_HELP'+j+' value=\"Help\" onClick=\"help_button_1('+j+')\"></td>'+ "); 

				out.println("'<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MODEL'+j+' STYLE=\"width:100\" maxlength=\"15\" size=\"15\" onblur=\"validate_model('+j+')\">'+");
				out.println("'<input type=\"Hidden\" NAME=HID_TXT_MODEL'+j+'	value=\"\"></td>'+");
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_2('+j+')\"></td>'+");
				
				out.println("'<td width=\"140\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_SUB_MODEL'+j+' STYLE=\"width:130\" maxlength=\"15\" size=\"15\" onblur=\"validate_sub_model('+j+')\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+j+'	VALUE=\"\"></td>'+");
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_SUB_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_3('+j+')\"></td>'+");
				
				
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_ITEM_SUB_CAT'+j+' STYLE=\"width:90\" maxlength=\"10\" size=\"10\" disabled        ></td>'+");
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_YEAR_OF_MANUFACTURE'+j+' STYLE=\"width:90\" maxlength=\"10\" size=\"10\" disabled ></td>'+");
								
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_STATUS'+j+'  STYLE=\"width:90\" class=\"txt_input\">'+"); //STYLE=\"width:80\"
				                                                    out.println("'<option value=\"N\" >New           </option>'+");
			                                                      out.println("'<option value=\"R\" >Re-Conditioned</option>'+");
																														out.println("'<option value=\"U\" >Used          </option></select></td>'+");
			                        
				
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_PURPOSE'+j+'  STYLE=\"width:90\"  class=\"txt_input\">'+");//STYLE=\"width:80\" 
				                                                    out.println("'<option value=\"P\" >Personal</option>'+");
			                           														out.println("'<option value=\"B\" >Business</option></select></td>'+");
												
			  				
				out.println("'<td width=\"100\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ASSET_NO'+j+' maxlength=\"10\" size=\"10\" STYLE=\"width:90\" ></td>'+");
				out.println("'<td width=\"50\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+j+' maxlength=\"5\" style=\"width:40px; text-align:right;\" size=\"5\" onblur=\"val_quantity('+j+')\"></td>'+");
				out.println("'<td width=\"50\" align=\"right\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:40px\" value=\" X \" onClick=\"del_row('+j+')\"></td>'+");
				out.println("   '</tr>'+ ");
				out.println("   '</table>';");
				*/
				
				//added by nuwan de silva on 10-09-07-----------------------
				out.println("m_table.innerHTML+='<table width=\"940\" align=\"center\" class=\"table\" border=\"0\" >'+ "); //cellspacing=\"0\" cellpadding=\"0\"
				out.println(" '<tr>'+ ");
				
				/*out.println("'<td width=\"140\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_SUB_MODEL'+j+' STYLE=\"width:130\" maxlength=\"15\" size=\"15\" onblur=\"validate_sub_model('+j+')\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+j+'	VALUE=\"\"></td>'+");
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_SUB_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_3('+j+')\"></td>'+");
				*/
				
				out.println("'<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MODEL'+j+' STYLE=\"width:100\" maxlength=\"15\" size=\"15\" onblur=\"validate_sub_model('+j+')\">'+");
				out.println("'<input type=\"Hidden\" NAME=HID_TXT_MODEL'+j+'	value=\"\"></td>'+");
				
				//__________ added by nuwan de silva on 10-12-2007 _________________________________
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+j+'	VALUE=\"\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=TXT_SUB_MODEL'+j+'	VALUE=\"\">'+");
				
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_3('+j+')\"></td>'+");
				
				out.println(" '<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MAKE'+j+' maxlength=\"15\" STYLE=\"width:100\" size=\"15\" onblur=\"validate_make('+j+')\">'+");
				out.println(" '<input type=\"Hidden\" NAME=HID_TXT_MAKE'+j+'	value=\"\"></td>'+");
				out.println(" '<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MAKE_HELP'+j+' value=\"Help\" onClick=\"help_button_1('+j+')\"></td>'+ "); 
				
				out.println("'<td width=\"50\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+j+' maxlength=\"5\" style=\"width:40px; text-align:right;\" size=\"5\" value=\"1\" onblur=\"val_quantity('+j+')\"></td>'+"); // Thamali 2010.12.27 
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_ITEM_SUB_CAT'+j+' STYLE=\"width:90\" maxlength=\"10\" size=\"10\" disabled        ></td>'+");
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_YEAR_OF_MANUFACTURE'+j+' STYLE=\"width:90\" maxlength=\"10\" size=\"10\" disabled ></td>'+");
								
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_STATUS'+j+'  STYLE=\"width:90\" class=\"txt_input\">'+"); //STYLE=\"width:80\"
				                                                    out.println("'<option value=\"N\" >New           </option>'+");
			                                                      out.println("'<option value=\"R\" >Re-Conditioned</option>'+");
																														out.println("'<option value=\"U\" >Used          </option></select></td>'+");
			                        
				
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_PURPOSE'+j+'  STYLE=\"width:90\"  class=\"txt_input\">'+");//STYLE=\"width:80\" 
				                                                    //out.println("'<option value=\"P\" >Personal</option>'+"); // commented by udara 04-09-2019
			                           														//out.println("'<option value=\"B\" >Business</option></select></td>'+"); // commented by udara 04-09-2019
																								out.println(" purpose_option_set + "); // added by udara 04-09-2019
																								out.println("'</select></td>'+"); // added by udara 04-09-2019
												
			  				
				out.println("'<td width=\"100\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ASSET_NO'+j+' maxlength=\"10\" size=\"10\" STYLE=\"width:90\" ></td>'+");
				//out.println("'<td width=\"50\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+j+' maxlength=\"5\" style=\"width:40px; text-align:right;\" size=\"5\" onblur=\"val_quantity('+j+')\"></td>'+");
				out.println("'<td width=\"50\" align=\"right\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:40px\" value=\" X \" onClick=\"del_row('+j+')\"></td>'+");
				out.println("   '</tr>'+ ");
				out.println("   '</table>';");

				
    
		  out.println("j=j+1;");	
			
			out.println("}");
			
			out.println("else{");
			out.println("while(i<data_vec.length){");
				
			
		/*		out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<TD WIDTH=\"5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ASSET_NO'+j+' maxlength=\"10\" size=\"10\" value=\"'+data_vec[i]+'\"></TD>'+");
				out.println("'<TD WIDTH=\"5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MAKE'+j+' maxlength=\"15\" size=\"15\" value=\"'+data_vec[i+2]+'\" onblur=\"validate_make('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_MAKE_HELP'+j+' value=\"Help\" onClick=\"help_button_1('+j+')\"></TD>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_MAKE'+j+'	value='+data_vec[i+1]+' >'+");


			 	out.println("'<TD WIDTH=\"5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MODEL'+j+' STYLE=\"width:100\" maxlength=\"15\" size=\"15\" value=\"'+data_vec[i+4]+'\" onblur=\"validate_model('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_2('+j+')\"></TD>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_MODEL'+j+'	value='+data_vec[i+3]+' >'+");

				
				out.println("'<TD WIDTH=\"10%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_SUB_MODEL'+j+' STYLE=\"width:100\" maxlength=\"15\" size=\"15\" value=\"'+data_vec[i+6]+'\" onblur=\"validate_sub_model('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_SUB_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_3('+j+')\"></TD>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+j+'	value='+data_vec[i+5]+' >'+");

				
				
				out.println("'<td width=\"10%\" align=\"left\" ><select name=TXT_STATUS'+j+' STYLE=\"width:100\" class=\"txt_input\">'+");
				                                                    out.println("'<option value=\"N\" >New           </option>'+");
			                                                      out.println("'<option value=\"R\" >Re-Conditioned</option>'+");
																														out.println("'<option value=\"U\" >Used          </option></select></TD>'+");
																														
			                        
				//out.println("'<TD WIDTH=\"10%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_SUPPLIER'+j+' maxlength=\"10\" value='+data_vec[i+5]+' size=\"10\" onblur=\"validate_supplier('+j+')\">'+");
				//out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_SUPPLIER_HELP'+j+' value=\"Help\" onClick=\"help_button_5('+j+')\"></TD>'+");
				out.println("'<td width=\"5%\" align=\"left\"><select name=TXT_PURPOSE'+j+' TYLE=\"width:50\"  class=\"txt_input\">'+");
				                                                    out.println("'<option value=\"P\" >Personal</option>'+");
			                           														out.println("'<option value=\"B\" >Business</option></select></TD>'+");
			 
				//out.println("'<TD WIDTH=\"10%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_LOCATION'+j+' maxlength=\"10\" value='+data_vec[i+8]+' size=\"10\"></TD>'+");
				//out.println("'<TD WIDTH=\"10%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CITY'+j+' maxlength=\"10\" size=\"10\" value='+data_vec[i+9]+' onblur=\"validate_city('+j+')\">'+");
				///out.println("'<TD WIDTH=\"10%\" align=\"left\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_SUB_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_4('+j+')\"></TD>'+");
				
				//out.println("'<TD WIDTH=\"10%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_PERIOD'+j+' maxlength=\"5\" style=\"width:60px; text-align:right;\" size=\"5\" value='+data_vec[i+8]+' onblur=\"val_period('+j+')\"></TD>'+");
				out.println("'<TD WIDTH=\"10%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+j+' maxlength=\"5\" style=\"width:60px; text-align:right;\" size=\"5\" value='+data_vec[i+10]+' onblur=\"val_quantity('+j+')\">'+");
				//out.println("'<TD WIDTH=\"10%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_COST'+j+' STYLE=\"{text-align:right;}\" maxlength=\"25\" size=\"25\" value='+format_noobject(data_vec[i+5])+' onblur=\"val_cost('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\"></TD>'+");
				out.println("'</tr></table>';");
   			//out.println("alert(document.Form1.elements['TXT_STATUS'+j].value);");			

			//out.println("document.Form1.hid_sum.value=document.Form1.hid_sum.value+data_vec[i+6];");			
			//out.println("sum=sum+parseFloat(data_vec[i+6]);");
			//out.println("m_cost=\"TXT_COST\"+j;");
			//out.println("format_number2(document.Form1.elements[m_cost],25);");
			//out.println("if(document.Form1.elements[m_cost].value!=\"\")");
  		//out.println("sum=sum+parseFloat(unformat_number(document.Form1.elements[m_cost]));");
			*/
			
			
			//comment byy nuwan de silva on 10-09-07-------------------------------------
			/*out.println("m_table.innerHTML+='<table width=\"1140\" align=\"center\" class=\"table\" border=\"0\" >'+ "); //cellspacing=\"0\" cellpadding=\"0\"
				out.println(" '<tr>'+ ");
				
				out.println(" '<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MAKE'+j+' maxlength=\"15\" STYLE=\"width:100\" size=\"15\" value=\"'+data_vec[i+2]+'\" onblur=\"validate_make('+j+')\">'+");
				out.println(" '<input type=\"Hidden\" NAME=HID_TXT_MAKE'+j+'	value=\"'+data_vec[i+1]+'\" ></td>'+");
				out.println(" '<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MAKE_HELP'+j+' value=\"Help\" onClick=\"help_button_1('+j+')\"></td>'+ "); 

				out.println("'<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MODEL'+j+' STYLE=\"width:100\" maxlength=\"15\" value=\"'+data_vec[i+4]+'\" size=\"15\" onblur=\"validate_model('+j+')\">'+");
				out.println("'<input type=\"Hidden\" NAME=HID_TXT_MODEL'+j+'	value=\"'+data_vec[i+3]+'\" ></td>'+");
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_2('+j+')\"></td>'+");
				
				out.println("'<td width=\"140\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_SUB_MODEL'+j+' STYLE=\"width:130\" maxlength=\"15\" value=\"'+data_vec[i+6]+'\" size=\"15\" onblur=\"validate_sub_model('+j+')\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+j+'	value=\"'+data_vec[i+5]+'\" ></td>'+");
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_SUB_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_3('+j+')\"></td>'+");
				
				
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_ITEM_SUB_CAT'+j+' STYLE=\"width:90\" value=\"'+data_vec[i+11]+'\" maxlength=\"10\" size=\"10\" disabled        ></td>'+");
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_YEAR_OF_MANUFACTURE'+j+' STYLE=\"width:90\" value=\"'+data_vec[i+12]+'\" maxlength=\"10\" size=\"10\" disabled ></td>'+");
								
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_STATUS'+j+'  STYLE=\"width:90\" class=\"txt_input\">'+"); //STYLE=\"width:80\"
				                                                    out.println("'<option value=\"N\" >New           </option>'+");
			                                                      out.println("'<option value=\"R\" >Re-Conditioned</option>'+");
																														out.println("'<option value=\"U\" >Used          </option></select></td>'+");
			                        
				
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_PURPOSE'+j+'  STYLE=\"width:90\"  class=\"txt_input\">'+");//STYLE=\"width:80\" 
				                                                    out.println("'<option value=\"P\" >Personal</option>'+");
			                           														out.println("'<option value=\"B\" >Business</option></select></td>'+");
												
			  				
				out.println("'<td width=\"100\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ASSET_NO'+j+' maxlength=\"10\" value=\"'+data_vec[i]+'\" size=\"10\" STYLE=\"width:90\" ></td>'+");
				out.println("'<td width=\"50\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+j+' maxlength=\"5\" value=\"'+data_vec[i+10]+'\" style=\"width:40px; text-align:right;\" size=\"5\" onblur=\"val_quantity('+j+')\"></td>'+");
				out.println("'<td width=\"50\" align=\"right\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:40px\" value=\" X \" onClick=\"del_row('+j+')\"></td>'+");
				out.println("   '</tr>'+ ");
				out.println("   '</table>';");
        */
				
				
				//added by nuwan de silva on 10-09-07----------------------------------
				out.println("m_table.innerHTML+='<table width=\"940\" align=\"center\" class=\"table\" border=\"0\" >'+ "); //cellspacing=\"0\" cellpadding=\"0\"
				out.println(" '<tr>'+ ");
				
				/*out.println("'<td width=\"140\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_SUB_MODEL'+j+' STYLE=\"width:130\" maxlength=\"15\" value=\"'+data_vec[i+6]+'\" size=\"15\" onblur=\"validate_sub_model('+j+')\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+j+'	value=\"'+data_vec[i+5]+'\" ></td>'+");
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_SUB_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_3('+j+')\"></td>'+");
				*/
				
				out.println("'<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MODEL'+j+' STYLE=\"width:100\" maxlength=\"15\" value=\"'+data_vec[i+4]+'\" size=\"15\" onblur=\"validate_sub_model('+j+')\">'+");
				out.println("'<input type=\"Hidden\" NAME=HID_TXT_MODEL'+j+'	value=\"'+data_vec[i+3]+'\" ></td>'+");
				
				//__________ added by nuwan de silva on 10-12-2007 _________________________________
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+j+'	value=\"'+data_vec[i+5]+'\" >'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=TXT_SUB_MODEL'+j+'	value=\"'+data_vec[i+6]+'\" >'+");
				
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_3('+j+')\"></td>'+");
				
				out.println(" '<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MAKE'+j+' maxlength=\"15\" STYLE=\"width:100\" size=\"15\" value=\"'+data_vec[i+2]+'\" onblur=\"validate_make('+j+')\">'+");
				out.println(" '<input type=\"Hidden\" NAME=HID_TXT_MAKE'+j+'	value=\"'+data_vec[i+1]+'\" ></td>'+");
				out.println(" '<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MAKE_HELP'+j+' value=\"Help\" onClick=\"help_button_1('+j+')\"></td>'+ "); 
				
				out.println("'<td width=\"50\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+j+' maxlength=\"5\" value=\"'+data_vec[i+10]+'\" style=\"width:40px; text-align:right;\" size=\"5\" onblur=\"val_quantity('+j+')\"></td>'+");
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_ITEM_SUB_CAT'+j+' STYLE=\"width:90\" value=\"'+data_vec[i+11]+'\" maxlength=\"10\" size=\"10\" disabled        ></td>'+");
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_YEAR_OF_MANUFACTURE'+j+' STYLE=\"width:90\" value=\"'+data_vec[i+12]+'\" maxlength=\"10\" size=\"10\" disabled ></td>'+");
								
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_STATUS'+j+'  STYLE=\"width:90\" class=\"txt_input\">'+"); //STYLE=\"width:80\"
				                                                    out.println("'<option value=\"N\" >New           </option>'+");
			                                                      out.println("'<option value=\"R\" >Re-Conditioned</option>'+");
																														out.println("'<option value=\"U\" >Used          </option></select></td>'+");
			                        
				
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_PURPOSE'+j+'  STYLE=\"width:90\"  class=\"txt_input\">'+");//STYLE=\"width:80\" 
				                                                    //out.println("'<option value=\"P\" >Personal</option>'+"); // commented by udara 04-09-2019
			                           														//out.println("'<option value=\"B\" >Business</option></select></td>'+"); // commented by udara 04-09-2019
																								out.println(" purpose_option_set + "); // added by udara 04-09-2019
																								out.println(" '</select></td>' + "); // added by udara 04-09-2019
												
			  				
				out.println("'<td width=\"100\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ASSET_NO'+j+' maxlength=\"10\" value=\"'+data_vec[i]+'\" size=\"10\" STYLE=\"width:90\" ></td>'+");
				//out.println("'<td width=\"50\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+j+' maxlength=\"5\" value=\"'+data_vec[i+10]+'\" style=\"width:40px; text-align:right;\" size=\"5\" onblur=\"val_quantity('+j+')\"></td>'+");
				out.println("'<td width=\"50\" align=\"right\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:40px\" value=\" X \" onClick=\"del_row('+j+')\"></td>'+");
				out.println("   '</tr>'+ ");
				out.println("   '</table>';");

				
			
			out.println("j=j+1;");
			out.println("i=i+13;");
			
			out.println("}"); //End of for loop;
			out.println("enable_select(data_vec);");
			out.println("}");
			out.println("lineno=j;");
			out.println("arr_size=j;");
		
			
		//	out.println("val_cost(j);");
			/*out.println("document.Form1.TXT_TOT.value=sum;");
			//out.println("format_number2(document.Form1.TXT_TOT,25);");
			out.println("document.Form1.TXT_TOT.value=format_noobject(document.Form1.TXT_TOT.value);");*/
			out.println("disable_asset_no();");
			
			//out.println("   check_asset_no();");
			
			out.println("}");
					
					
			out.println("function get_vector(data_vec) {");
			//out.println("alert('g'+document.Form1.hid_chk_status.value);");		
			//out.println("alert('data_vec'+document.Form1.elements[m_sub_model].value);");		
			out.println("m_make=\"TXT_MAKE\"+document.Form1.hid_row_no.value");
			out.println("m_model=\"TXT_MODEL\"+document.Form1.hid_row_no.value");
			out.println("m_sub_model=\"TXT_SUB_MODEL\"+document.Form1.hid_row_no.value");
			//out.println("m_city=\"TXT_CITY\"+document.Form1.hid_row_no.value");
			//out.println("m_supplier=\"TXT_SUPPLIER\"+document.Form1.hid_row_no.value");
	//		out.println("alert('g'+m_gur_code);");
						
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_chk_status.value=='M1'){");
			//out.println("			alert('Record already exists.');");
			out.println("     load_main_menu(1);");
			out.println("			}");
			
			out.println("			else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_chk_status.value=='M1'){");
			//out.println("			alert('Record already exists.');");
			out.println("     load_main_menu(0);");
			out.println("			}");
			
			out.println("			else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_chk_status.value=='M1'){");
			out.println("     load_value();");
			out.println("			}");
						
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_APPLICATION_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M1'){");
			out.println("				help_update('1','10','0','m_help_TXT_APPLICATION_NO_2','99') ;");
			//out.println("       document.Form1.TXT_APPLICATION_NO.value=\"\" "); 
			out.println("       document.Form1.TXT_APPLICATION_NO.focus()  "); 
			out.println("			}");
			
					
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M2' && document.Form1.elements[m_make].value!=\"\"){");
			out.println("				help_button_1(document.Form1.hid_row_no.value);");
			//out.println("       document.Form1.elements[m_make].value=\"\" "); 
			out.println("       document.Form1.elements[m_make].focus()  "); 
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M3' && document.Form1.elements[m_model].value!=\"\"){");
			out.println("				help_button_2(document.Form1.hid_row_no.value);");
			//out.println("       document.Form1.elements[m_model].value=\"\" "); 
			out.println("       document.Form1.elements[m_model].focus()  "); 
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M4' && document.Form1.elements[m_model].value!=\"\"){");
			//out.println("alert('here we go'+data_vec);");		
			out.println("				help_button_3(document.Form1.hid_row_no.value);");
			//out.println("       document.Form1.elements[m_sub_model].value=\"\" "); 
			//out.println("       document.Form1.elements[m_sub_model].focus()  "); 
			out.println("			}");
			
			/*out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M5' && document.Form1.elements[m_city].value!=\"\"){");
			out.println("				alert('Invalid Record, Use Help');");
			out.println("       document.Form1.elements[m_city].value=\"\" "); 
			out.println("       document.Form1.elements[m_city].focus()  "); 
			out.println("			}");*/
			
			/*out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M6' && document.Form1.elements[m_supplier].value!=\"\"){");
			out.println("				alert('Invalid Record, Use Help');");
			out.println("       document.Form1.elements[m_supplier].value=\"\" "); 
			out.println("       document.Form1.elements[m_supplier].focus()  "); 
			out.println("			}");*/
			
			
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_APPLICATION_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
			out.println("    document.Form1.TXT_APPLICATION_NO.value=data_vec[0];"); 
			out.println("    assignState('M7');"); 
			out.println("    makeRequest(document.Form1.TXT_APPLICATION_NO);");
			 out.println("			}");
			
			out.println("			else");
			out.println("			if(document.Form1.hid_chk_status.value=='M7'){");
      out.println("      display_data(data_vec);"); 		
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M45'){");
			out.println("     b_asset_no_present=1;"); 	
			out.println("alert('First delete the proforma invoice and valuation data prior to deleting this asset number');");
			
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M45'){");
			out.println("      b_asset_no_present=0;"); 	
			out.println("del_row_new(document.Form1.hid_row_no.value);");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M50'){");
			out.println("     count_number=count_number+1;"); 	
		//	out.println("     count_asset_no=count_asset_no+1;"); 	
		 // out.println("alert('count number'+count_number);");
			out.println("     check_asset_no();");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M50'){");
		//	out.println("     count_asset_no=count_asset_no+1;"); 	
			out.println("     check_asset_no();");
			out.println("			}");


						
			
		 out.println("}");
			
			
			
			
			
			
			
			
			
			
			out.println("function makeRequest(obj) {");
			out.println("if(document.Form1.hid_chk_status.value=='M1')");
		
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Asset_Detail_Equipment&data_val=\"+obj.value+\"&ac_status=Y\";");
			//out.println("window.open(m_url);");
					
		  out.println("else if(document.Form1.hid_chk_status.value=='M7')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Asset_Detail_Equipment_val&data_val=\"+obj.value+\"&ac_status=Y\";");
	
    //  out.println("window.open(m_url);")	;
			out.println("load_interface(m_url,'XML');");
			
					
			
			out.println("}");
			
			
						
			
			
			out.println("function val_cost(rowNo){");
			out.println("sum=0;");
			out.println("m_cost=\"TXT_COST\"+rowNo;");
			out.println("m_qty=\"TXT_QUANTITY\"+rowNo;");
			//out.println("if(!isPosInteger(document.Form1.elements[m_cost].value)){");
			//out.println("alert('Please Enter a Number');");
			//out.println("document.Form1.elements[m_cost].value=\"\"; ");
			//out.println("document.Form1.elements[m_cost].focus();}");
			out.println("document.Form1.elements[m_cost].value=format_noobject(parseFloat(unformat_number(document.Form1.elements[m_cost])) * document.Form1.elements[m_qty].value);");
			//format_number2(document.Form1.TXT_AMOUNT,25)
			
			out.println("for(var i=0;i<arr_size;i++){");			
			out.println("m_cost=\"TXT_COST\"+i;");
			out.println("if(document.Form1.elements[m_cost].value!=\"\")");
  		out.println("sum=sum+parseFloat(unformat_number(document.Form1.elements[m_cost]));");
			out.println("}");			
			
			/*			
			out.println("document.Form1.TXT_TOT.value=format_noobject(sum);");		
			out.println("format_number2(document.Form1.TXT_TOT,25);");*/
			out.println("}");
			
			
			
			/*out.println("function val_period(rowNo){");
			
			out.println("m_period=\"TXT_PERIOD\"+rowNo;");
			out.println("if(!isPosInteger(document.Form1.elements[m_period].value)){");
			out.println("alert('Please enter a number.');");
			out.println("document.Form1.elements[m_period].value=\"\"; ");
			out.println("document.Form1.elements[m_period].focus();}");
			
			out.println("}");*/
			
			out.println("function val_quantity(rowNo){");
			out.println("m_quantity=\"TXT_QUANTITY\"+rowNo;");
			out.println("if(!isPosInteger(document.Form1.elements[m_quantity].value)){");
			out.println("alert('Please Enter a Number');");
			out.println("document.Form1.elements[m_quantity].value=\"\"; ");
			out.println("document.Form1.elements[m_quantity].focus();}");
			out.println("}");
			
      out.println("function chk_data(){");
	    out.println("b_flag=0;");
	//	  out.println("if(lineno>1){");
			out.println("for(var i=0;i<lineno;i++){");
			out.println("m_make=\"TXT_MAKE\"+i");
			out.println("m_model=\"TXT_MODEL\"+i");
			out.println("m_sub_model=\"TXT_SUB_MODEL\"+i");
			out.println("m_status=\"TXT_STATUS\"+i");
			out.println("m_supplier=\"TXT_SUPPLIER\"+i");
			//out.println("m_cost=\"TXT_COST\"+i");
			out.println("m_purpose=\"TXT_PURPOSE\"+i");
			//out.println("m_location=\"TXT_LOCATION\"+i");
			//out.println("m_city=\"TXT_CITY\"+i");
			//out.println("m_period=\"TXT_PERIOD\"+i");
			out.println("m_quantity=\"TXT_QUANTITY\"+i");
			
			 			
				out.println("if(document.Form1.elements[m_make].value==\"\") {");
				out.println("alert('Make cannot be null.');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_model].value==\"\") {");
				out.println("alert('Model cannot be null.');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_sub_model].value==\"\") {");
				out.println("alert('Sub Model cannot be null.');");
				out.println("b_flag=1;");
				out.println("}");
				
				/*out.println("else if(document.Form1.elements[m_supplier].value==\"\") {");
				out.println("alert('Supplier code cannot be null.');");
				out.println("b_flag=1;");
				out.println("}");*/
				/*
				out.println("else if(document.Form1.elements[m_cost].value==\"\") {");
				out.println("alert('Cost cannot be null.');");
				out.println("b_flag=1;");
				out.println("}");
				*/
				
				/*
				out.println("else if(document.Form1.elements[m_location].value==\"\") {");
				out.println("alert('Location Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_city].value==\"\") {");
				out.println("alert('City Code Can Not Be Null');");
				out.println("b_flag=1;");
				out.println("}");
				*/
				
				/*out.println("else if(document.Form1.elements[m_period].value==\"\") {");
				out.println("alert('Period cannot be null.');");
				out.println("b_flag=1;");
				out.println("}");*/
				
				out.println("else if(document.Form1.elements[m_quantity].value==\"\") {");
				out.println("alert('Quantity cannot be null.');");
				out.println("b_flag=1;");
				out.println("}");
				
							
				
			 // out.println("return false;"); 
		//		out.println("}");
				
				out.println("}");
				
						
				
			  
				out.println("}");
				
	
			
		
			
			
			
			
			/*----------------------------------------------------------------
		Purpose  : Add New gurantor
	
	----------------------------------------------------------------*/			

			out.println("function add_row(){"); 
			
		//	out.println("chk_data();");
			 
			 out.println("b_flag=0;");
			
			
			out.println("if(lineno!=0){");
			out.println("count=lineno-1;");
				
			out.println("m_make=\"TXT_MAKE\"+count");
			out.println("m_model=\"TXT_MODEL\"+count");
			//out.println("m_sub_model=\"TXT_SUB_MODEL\"+count");
			out.println("m_status=\"TXT_STATUS\"+count");
			out.println("m_purpose=\"TXT_PURPOSE\"+count");
			out.println("m_quantity=\"TXT_QUANTITY\"+count");
			

			 			
				out.println("if(document.Form1.elements[m_make].value==\"\") {");
				out.println("alert('Make can not be null.');");
				out.println("b_flag=1;");
				out.println("}");
				
				out.println("else if(document.Form1.elements[m_model].value==\"\") {");
				out.println("alert('Model can not be null.');");
				out.println("b_flag=1;");
				out.println("}");
				
				/*out.println("else if(document.Form1.elements[m_sub_model].value==\"\") {");
				out.println("alert('Sub Model can not be null.');");
				out.println("b_flag=1;");
				out.println("}");
				*/
				
					
				out.println("else if(document.Form1.elements[m_quantity].value==\"\") {");
				out.println("alert('Quantity can not be null.');");
				out.println("b_flag=1;");
				out.println("}");
							  
				out.println("}");
			
									
				out.println("if(b_flag==0){");
			  
			/*	out.println("m_table.innerHTML+='<table align=\"center\" width=\"1180\" class=\"table\" border=\"0\" ><tr ID=T_ID'+lineno+'>'+");									
								
				out.println("'<td width=\"180\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MAKE'+lineno+' maxlength=\"15\" STYLE=\"width:100\" size=\"15\" onblur=\"validate_make('+lineno+')\">'+");
				out.println("'<input type=\"Hidden\" NAME=HID_TXT_MAKE'+lineno+'	VALUE=\"\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_MAKE_HELP'+lineno+' value=\"Help\" onClick=\"help_button_1('+lineno+')\"></TD>'+");
				
				
			 	out.println("'<TD WIDTH=\"200\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MODEL'+lineno+' STYLE=\"width:110\" maxlength=\"15\" size=\"15\" onblur=\"validate_model('+lineno+')\">'+");
				out.println("'<input type=\"Hidden\" NAME=HID_TXT_MODEL'+lineno+'	VALUE=\"\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_MODEL_HELP'+lineno+' value=\"Help\" onClick=\"help_button_2('+lineno+')\"></TD>'+");
							
				
				out.println("'<TD WIDTH=\"200\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_SUB_MODEL'+lineno+' STYLE=\"width:110\" maxlength=\"15\" size=\"15\" onblur=\"validate_sub_model('+lineno+')\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+lineno+'	VALUE=\"\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_SUB_MODEL_HELP'+lineno+' value=\"Help\" onClick=\"help_button_3('+lineno+')\"></TD>'+");
				
				
				out.println("'<TD WIDTH=\"100\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ITEM_SUB_CAT'+lineno+' STYLE=\"width:90\" maxlength=\"10\" size=\"10\" disabled ></TD>'+");
				out.println("'<TD WIDTH=\"100\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_YEAR_OF_MANUFACTURE'+lineno+' STYLE=\"width:90\" maxlength=\"10\" size=\"10\" disabled ></TD>'+");
								
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_STATUS'+lineno+'  STYLE=\"width:90\" class=\"txt_input\">'+"); //STYLE=\"width:80\"
				                                                    out.println("'<option value=\"N\" >New           </option>'+");
			                                                      out.println("'<option value=\"R\" >Re-Conditioned</option>'+");
																														out.println("'<option value=\"U\" >Used          </option></select></TD>'+");
			                        
				
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_PURPOSE'+lineno+'  STYLE=\"width:90\"  class=\"txt_input\">'+");//STYLE=\"width:80\" 
				                                                    out.println("'<option value=\"P\" >Personal</option>'+");
			                           														out.println("'<option value=\"B\" >Business</option></select></TD>'+");
												
			  				
				out.println("'<TD WIDTH=\"50\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+lineno+' maxlength=\"5\" style=\"width:40px; text-align:right;\" size=\"5\" onblur=\"val_quantity('+lineno+')\"></TD>'+");
				
				out.println("'<TD WIDTH=\"100\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ASSET_NO'+lineno+' maxlength=\"10\" size=\"10\" STYLE=\"width:90\" ></TD>'+");
				
				out.println("'<TD WIDTH=\"50\" align=\"right\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+lineno+')\"></TD>'+");
				
				out.println("'</tr></table>';");
				
				*/
				
				
				//comment by nuwan de silva on 10-09-07-----------------------------
				/*out.println("m_table.innerHTML+='<table width=\"1140\" align=\"center\" class=\"table\" border=\"0\" >'+ "); //cellspacing=\"0\" cellpadding=\"0\"
				out.println(" '<tr>'+ ");
				
				out.println(" '<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MAKE'+lineno+' maxlength=\"15\" STYLE=\"width:100\" size=\"15\" onblur=\"validate_make('+lineno+')\">'+");
				out.println(" '<input type=\"Hidden\" NAME=HID_TXT_MAKE'+lineno+'	value=\"\"></td>'+");
				out.println(" '<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MAKE_HELP'+lineno+' value=\"Help\" onClick=\"help_button_1('+lineno+')\"></td>'+ "); 

				out.println("'<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MODEL'+lineno+' STYLE=\"width:100\" maxlength=\"15\" size=\"15\" onblur=\"validate_model('+lineno+')\">'+");
				out.println("'<input type=\"Hidden\" NAME=HID_TXT_MODEL'+lineno+'	value=\"\"></td>'+");
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MODEL_HELP'+lineno+' value=\"Help\" onClick=\"help_button_2('+lineno+')\"></td>'+");
				
				out.println("'<td width=\"140\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_SUB_MODEL'+lineno+' STYLE=\"width:130\" maxlength=\"15\" size=\"15\" onblur=\"validate_sub_model('+lineno+')\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+lineno+'	VALUE=\"\"></td>'+");
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_SUB_MODEL_HELP'+lineno+' value=\"Help\" onClick=\"help_button_3('+lineno+')\"></td>'+");
				
				
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_ITEM_SUB_CAT'+lineno+' STYLE=\"width:90\" maxlength=\"10\" size=\"10\" disabled        ></td>'+");
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_YEAR_OF_MANUFACTURE'+lineno+' STYLE=\"width:90\" maxlength=\"10\" size=\"10\" disabled ></td>'+");
								
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_STATUS'+lineno+'  STYLE=\"width:90\" class=\"txt_input\">'+"); //STYLE=\"width:80\"
				                                                    out.println("'<option value=\"N\" >New           </option>'+");
			                                                      out.println("'<option value=\"R\" >Re-Conditioned</option>'+");
																														out.println("'<option value=\"U\" >Used          </option></select></td>'+");
			                        
				
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_PURPOSE'+lineno+'  STYLE=\"width:90\"  class=\"txt_input\">'+");//STYLE=\"width:80\" 
				                                                    out.println("'<option value=\"P\" >Personal</option>'+");
			                           														out.println("'<option value=\"B\" >Business</option></select></td>'+");
												
			  				
				out.println("'<td width=\"100\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ASSET_NO'+lineno+' maxlength=\"10\" size=\"10\" STYLE=\"width:90\" ></td>'+");
				out.println("'<td width=\"50\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+lineno+' maxlength=\"5\" style=\"width:40px; text-align:right;\" size=\"5\" onblur=\"val_quantity('+lineno+')\"></td>'+");
				out.println("'<td width=\"50\" align=\"right\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' style=\"width:40px\" value=\" X \" onClick=\"del_row('+lineno+')\"></td>'+");
				out.println("   '</tr>'+ ");
				out.println("   '</table>';");
	      */
				
				
				//added by nuwan de silva on 10-09-07---------------------------------
				out.println("m_table.innerHTML+='<table width=\"940\" align=\"center\" class=\"table\" border=\"0\" >'+ "); //cellspacing=\"0\" cellpadding=\"0\"
				out.println(" '<tr>'+ ");
				
				/*out.println("'<td width=\"140\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_SUB_MODEL'+lineno+' STYLE=\"width:130\" maxlength=\"15\" size=\"15\" onblur=\"validate_sub_model('+lineno+')\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+lineno+'	VALUE=\"\"></td>'+");
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_SUB_MODEL_HELP'+lineno+' value=\"Help\" onClick=\"help_button_3('+lineno+')\"></td>'+");
				*/
				
				//out.println("'<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MODEL'+lineno+' STYLE=\"width:100\" maxlength=\"15\" size=\"15\" onblur=\"validate_model('+lineno+')\">'+");
				out.println("'<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MODEL'+lineno+' STYLE=\"width:100\" maxlength=\"15\" size=\"15\" onblur=\"validate_sub_model('+lineno+')\">'+");
				out.println("'<input type=\"Hidden\" NAME=HID_TXT_MODEL'+lineno+'	value=\"\"></td>'+");
				//__________ added by nuwan de silva on 10-12-2007 _________________________________
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+lineno+'	VALUE=\"\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=TXT_SUB_MODEL'+lineno+'	VALUE=\"\">'+");
				
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MODEL_HELP'+lineno+' value=\"Help\" onClick=\"help_button_3('+lineno+')\"></td>'+");
				
				out.println(" '<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MAKE'+lineno+' maxlength=\"15\" STYLE=\"width:100\" size=\"15\" onblur=\"validate_make('+lineno+')\">'+");
				out.println(" '<input type=\"Hidden\" NAME=HID_TXT_MAKE'+lineno+'	value=\"\"></td>'+");
				out.println(" '<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MAKE_HELP'+lineno+' value=\"Help\" onClick=\"help_button_1('+lineno+')\"></td>'+ "); 
		    out.println("'<td width=\"50\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+lineno+' maxlength=\"5\" style=\"width:40px; text-align:right;\" size=\"5\" onblur=\"val_quantity('+lineno+')\" value=\"1\" ></td>'+"); // Thamali 2010.12.27
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_ITEM_SUB_CAT'+lineno+' STYLE=\"width:90\" maxlength=\"10\" size=\"10\" disabled        ></td>'+");
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_YEAR_OF_MANUFACTURE'+lineno+' STYLE=\"width:90\" maxlength=\"10\" size=\"10\" disabled ></td>'+");
								
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_STATUS'+lineno+'  STYLE=\"width:90\" class=\"txt_input\">'+"); //STYLE=\"width:80\"
				                                                    out.println("'<option value=\"N\" >New           </option>'+");
			                                                      out.println("'<option value=\"R\" >Re-Conditioned</option>'+");
																														out.println("'<option value=\"U\" >Used          </option></select></td>'+");
			                        
				
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_PURPOSE'+lineno+'  STYLE=\"width:90\"  class=\"txt_input\">'+");//STYLE=\"width:80\" 
				                                                    //out.println("'<option value=\"P\" >Personal</option>'+"); // commented by udara 04-09-2019
			                           														//out.println("'<option value=\"B\" >Business</option></select></td>'+"); // commented by udara 04-09-2019
																								out.println(" purpose_option_set + "); // added by udara 04-09-2019
																								out.println(" '</select></td>' + "); // added by udara 04-09-2019
												
			  				
				out.println("'<td width=\"100\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ASSET_NO'+lineno+' maxlength=\"10\" size=\"10\" STYLE=\"width:90\" ></td>'+");
				//out.println("'<td width=\"50\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+lineno+' maxlength=\"5\" style=\"width:40px; text-align:right;\" size=\"5\" onblur=\"val_quantity('+lineno+')\"></td>'+");
				out.println("'<td width=\"50\" align=\"right\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' style=\"width:40px\" value=\" X \" onClick=\"del_row('+lineno+')\"></td>'+");
				out.println("   '</tr>'+ ");
				out.println("   '</table>';");
				
	
		
					
				out.println("lineno=lineno+1;");
				out.println("arr_size=arr_size+1;");
				
      	out.println("}");
				
				out.println("document.Form1.hid_row_id.value=lineno-1;");
				//out.println("<INPUT TYPE='Hidden' NAME='hid_row_id' VALUE=\"+lineno+\">");
										
				out.println("disable_asset_no();");
				
		    out.println("}");
				
				
				
				
				
				
				
				out.println("function fill_value(){");
				
				out.println("for(i=0;i<arr_size;i++){");
			  out.println("m_status=\"TXT_STATUS\"+i");
			  out.println("m_purpose=\"TXT_PURPOSE\"+i");
			
			  out.println("document.Form1.elements[m_status].value=array_status[i]");
			  out.println("document.Form1.elements[m_purpose].value=array_purpose[i]");
			 // out.println("alert('val'+document.Form1.elements[m_status].value);");
				//out.println("alert('val'+document.Form1.elements[m_purpose].value);");
			  out.println("}");
				out.println("}");
				
				
				out.println("function check_asset_no(){");
				//out.println("alert('screen name'+document.Form1.SCREEN_NAME.value);");
				out.println("	if(document.Form1.SCREEN_NAME.value==\"DEL\"){");
				//out.println("count_asset_no=0;");
				out.println("assignState('M50');"); 
				
				//out.println("for(i=0;i<arr_size;i++){");
				out.println("while(count_asset_no<arr_size){");
				out.println("m_asset_no_check=\"TXT_ASSET_NO\"+count_asset_no");
			  out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_asset_validation&data_val=\"+document.Form1.TXT_APPLICATION_NO.value+\"&data_val2=\"+document.Form1.elements[m_asset_no_check].value;");
	    // out.println("window.open(m_url);")	;
				out.println("count_asset_no=count_asset_no+1;");
			  out.println("load_interface(m_url,'XML');");
				
				out.println("}");
				out.println("}");
								
				out.println("}");
				
				
				
				
		 out.println("function validate_asset_no(rowNo){");
			
			out.println("b_asset_no_present=0;");
			out.println("m_asset_no_check=\"TXT_ASSET_NO\"+rowNo");
			
			out.println("document.Form1.elements[m_asset_no_check].value");
			out.println("assignState('M45');"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_asset_validation&data_val=\"+document.Form1.TXT_APPLICATION_NO.value+\"&data_val2=\"+document.Form1.elements[m_asset_no_check].value;");
	  //  out.println("window.open(m_url);")	;
			out.println("load_interface(m_url,'XML');");
			
		/*	out.println("if(b_asset_no_present==1){");
			out.println("alert('first delete the proforma invoce and valuation data belong to asset id');");
			out.println("}");
			out.println("else if(b_asset_no_present==0){");
			out.println("del_row(rowNo)");
			out.println("}"); 
    */
			
			out.println("}");
    		
			
			out.println("function del_row(rowNo){"); 
			
			out.println("if(rowNo!=0){");
			out.println("document.Form1.hid_row_no.value=rowNo");
			out.println("validate_asset_no(rowNo);");
			

      /*out.println("if(b_asset_no_present==0){");
			out.println("var j=0;");
			
			out.println("for(var i=0;i<arr_size;i++){");
			out.println("m_asset_no=\"TXT_ASSET_NO\"+i");
			out.println("m_make=\"TXT_MAKE\"+i");
			out.println("m_model=\"TXT_MODEL\"+i");
			out.println("m_sub_model=\"TXT_SUB_MODEL\"+i");
			out.println("m_status=\"TXT_STATUS\"+i");
			//out.println("m_supplier=\"TXT_SUPPLIER\"+i");
			//out.println("m_cost=\"TXT_COST\"+i");
			out.println("m_purpose=\"TXT_PURPOSE\"+i");
			//out.println("m_location=\"TXT_LOCATION\"+i");
			//out.println("m_city=\"TXT_CITY\"+i");
			//out.println("m_period=\"TXT_PERIOD\"+i");
			out.println("m_quantity=\"TXT_QUANTITY\"+i");
			
			
		
						
			out.println("if(i==rowNo)");
			out.println("continue;");
			
				
			out.println("array_asset_no[j]=document.Form1.elements[m_asset_no].value;");
			out.println("array_make[j]=document.Form1.elements[m_make].value;");
		  out.println("array_model[j]=document.Form1.elements[m_model].value;");
			out.println("array_sub_model[j]=document.Form1.elements[m_sub_model].value;");
			out.println("array_status[j]=document.Form1.elements[m_status].value;");
		  //out.println("array_supplier[j]=document.Form1.elements[m_supplier].value;");
			//out.println("array_cost[j]=document.Form1.elements[m_cost].value;");
		  out.println("array_purpose[j]=document.Form1.elements[m_purpose].value;");
			//out.println("array_location[j]=document.Form1.elements[m_location].value;");
			//out.println("array_city[j]=document.Form1.elements[m_city].value;");
			//out.println("array_period[j]=document.Form1.elements[m_period].value;");
			out.println("array_quantity[j]=document.Form1.elements[m_quantity].value;");
		
     
			out.println("j=j+1;");
			
			out.println("}");
		 
			out.println("lineno=lineno-1;");
			out.println("arr_size=arr_size-1;");
		  out.println("write_data(arr_size);");
			out.println("}"); 
			*/
			out.println("}");
			
			out.println("}");
			
			
			out.println("function del_row_new(rowNo){"); 
			


			out.println("var j=0;");
			
			out.println("for(var i=0;i<arr_size;i++){");
			out.println("m_asset_no=\"TXT_ASSET_NO\"+i");
			out.println("m_make=\"TXT_MAKE\"+i");
			out.println("m_model=\"TXT_MODEL\"+i");
			out.println("m_sub_model=\"TXT_SUB_MODEL\"+i");
			out.println("m_status=\"TXT_STATUS\"+i");
			out.println("m_purpose=\"TXT_PURPOSE\"+i");
			out.println("m_quantity=\"TXT_QUANTITY\"+i");
			
			
			
			///added by nuwan de silva 21-05-07--------------------
			
			out.println("m_hid_make=\"HID_TXT_MAKE\"+i");
			out.println("m_hid_model=\"HID_TXT_MODEL\"+i");
			out.println("m_hid_sub_model=\"HID_TXT_SUB_MODEL\"+i");
			
			out.println("m_item_sub=\"TXT_ITEM_SUB_CAT\"+i");
			out.println("m_year_of_manufacture=\"TXT_YEAR_OF_MANUFACTURE\"+i");
			

			//=======================================================
		
						
			out.println("if(i==rowNo)");
			out.println("continue;");
			
				
			out.println("array_asset_no[j]=document.Form1.elements[m_asset_no].value;");
			out.println("array_make[j]=document.Form1.elements[m_make].value;");
		  out.println("array_model[j]=document.Form1.elements[m_model].value;");
			out.println("array_sub_model[j]=document.Form1.elements[m_sub_model].value;");
			out.println("array_status[j]=document.Form1.elements[m_status].value;");
		  out.println("array_purpose[j]=document.Form1.elements[m_purpose].value;");
			out.println("array_quantity[j]=document.Form1.elements[m_quantity].value;");
			out.println("array_hid_make[j]=document.Form1.elements[m_hid_make].value;");
		  out.println("array_hid_model[j]=document.Form1.elements[m_hid_model].value;");
			out.println("array_hid_sub_model[j]=document.Form1.elements[m_hid_sub_model].value;");
			out.println("array_item_sub_cat[j]=document.Form1.elements[m_item_sub].value;");
			out.println("array_year_of_manufacture[j]=document.Form1.elements[m_year_of_manufacture].value;");

		
     
			out.println("j=j+1;");
			
			out.println("}");
		 
			out.println("lineno=lineno-1;");
			out.println("arr_size=arr_size-1;");
		  out.println("write_data(arr_size);");
			//out.println("}"); 
			out.println("}");


			
			
			out.println("function write_data(size){");
				
			out.println("sum=0;");
				
				out.println("m_table.innerHTML=\"\";");
				out.println("header();");
        out.println(" for(var j=0;j<size;j++){");
				
								
							
			  out.println("if(array_make[j]==\"\" && array_model[j]==\"\" ){");
							  
								
				/*out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<TD WIDTH=\"5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ASSET_NO'+j+' maxlength=\"10\" size=\"10\" onblur=\"validate_city('+j+')\"></TD>'+");
				out.println("'<TD WIDTH=\"5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MAKE'+j+' maxlength=\"15\" size=\"15\" onblur=\"validate_make('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_MAKE_HELP'+j+' value=\"Help\" onClick=\"help_button_1('+j+')\"></TD>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_MAKE'+j+'	VALUE=\"\">'+");

			 	out.println("'<TD WIDTH=\"5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MODEL'+j+' STYLE=\"width:100\" maxlength=\"15\" size=\"15\" onblur=\"validate_model('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_2('+j+')\"></TD>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_MODEL'+j+'	VALUE=\"\">'+");
				
				out.println("'<TD WIDTH=\"10%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_SUB_MODEL'+j+' STYLE=\"width:100\" maxlength=\"15\" size=\"15\" onblur=\"validate_sub_model('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_SUB_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_3('+j+')\"></TD>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+j+'	VALUE=\"\">'+");
				
				
				
				out.println("'<td width=\"10%\" align=\"left\"><select name=TXT_STATUS'+j+' STYLE=\"width:100\" class=\"txt_input\">'+");
				                                                    out.println("'<option value=\"N\" >New           </option>'+");
			                                                      out.println("'<option value=\"R\" >Re-Conditioned</option>'+");
																														out.println("'<option value=\"U\" >Used          </option></select></TD>'+");
			                        
				out.println("'<td width=\"5%\" align=\"left\" ><select name=TXT_PURPOSE'+j+' TYLE=\"width:50\"  class=\"txt_input\">'+");
				                                                    out.println("'<option value=\"P\" >Personal</option>'+");
			                           														out.println("'<option value=\"B\" >Business</option></select></TD>'+");
			 
				out.println("'<TD WIDTH=\"10%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+j+' maxlength=\"5\" style=\"width:60px; text-align:right;\" size=\"5\" onblur=\"val_quantity('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\"></TD>'+");
				out.println("'</tr></table>';");
				*/
				
				//comment by nuwan de silva on 10-09-07--------------
				/*out.println("m_table.innerHTML+='<table width=\"1140\" align=\"center\" class=\"table\" border=\"0\" >'+ "); //cellspacing=\"0\" cellpadding=\"0\"
				out.println(" '<tr>'+ ");
				
				out.println(" '<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MAKE'+j+' maxlength=\"15\" STYLE=\"width:100\" size=\"15\" onblur=\"validate_make('+j+')\">'+");
				out.println(" '<input type=\"Hidden\" NAME=HID_TXT_MAKE'+j+'	value=\"\"></td>'+");
				out.println(" '<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MAKE_HELP'+j+' value=\"Help\" onClick=\"help_button_1('+j+')\"></td>'+ "); 

				out.println("'<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MODEL'+j+' STYLE=\"width:100\" maxlength=\"15\" size=\"15\" onblur=\"validate_model('+j+')\">'+");
				out.println("'<input type=\"Hidden\" NAME=HID_TXT_MODEL'+j+'	value=\"\"></td>'+");
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_2('+j+')\"></td>'+");
				
				out.println("'<td width=\"140\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_SUB_MODEL'+j+' STYLE=\"width:130\" maxlength=\"15\" size=\"15\" onblur=\"validate_sub_model('+j+')\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+j+'	VALUE=\"\"></td>'+");
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_SUB_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_3('+j+')\"></td>'+");
				
				
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_ITEM_SUB_CAT'+j+' STYLE=\"width:90\" maxlength=\"10\" size=\"10\" disabled        ></td>'+");
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_YEAR_OF_MANUFACTURE'+j+' STYLE=\"width:90\" maxlength=\"10\" size=\"10\" disabled ></td>'+");
								
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_STATUS'+j+'  STYLE=\"width:90\" class=\"txt_input\">'+"); //STYLE=\"width:80\"
				                                                    out.println("'<option value=\"N\" >New           </option>'+");
			                                                      out.println("'<option value=\"R\" >Re-Conditioned</option>'+");
																														out.println("'<option value=\"U\" >Used          </option></select></td>'+");
			                        
				
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_PURPOSE'+j+'  STYLE=\"width:90\"  class=\"txt_input\">'+");//STYLE=\"width:80\" 
				                                                    out.println("'<option value=\"P\" >Personal</option>'+");
			                           														out.println("'<option value=\"B\" >Business</option></select></td>'+");
												
			  				
				out.println("'<td width=\"100\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ASSET_NO'+j+' maxlength=\"10\" size=\"10\" STYLE=\"width:90\" ></td>'+");
				out.println("'<td width=\"50\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+j+' maxlength=\"5\" style=\"width:40px; text-align:right;\" size=\"5\" onblur=\"val_quantity('+j+')\"></td>'+");
				out.println("'<td width=\"50\" align=\"right\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:40px\" value=\" X \" onClick=\"del_row('+j+')\"></td>'+");
				out.println("   '</tr>'+ ");
				out.println("   '</table>';");
				*/
				
				
				out.println("m_table.innerHTML+='<table width=\"940\" align=\"center\" class=\"table\" border=\"0\" >'+ "); //cellspacing=\"0\" cellpadding=\"0\"
				out.println(" '<tr>'+ ");
				
				/*out.println("'<td width=\"140\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_SUB_MODEL'+j+' STYLE=\"width:130\" maxlength=\"15\" size=\"15\" onblur=\"validate_sub_model('+j+')\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+j+'	VALUE=\"\"></td>'+");
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_SUB_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_3('+j+')\"></td>'+");
				*/
				
				
				out.println("'<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MODEL'+j+' STYLE=\"width:100\" maxlength=\"15\" size=\"15\" onblur=\"validate_sub_model('+j+')\">'+");
				out.println("'<input type=\"Hidden\" NAME=HID_TXT_MODEL'+j+'	value=\"\"></td>'+");
				//__________ added by nuwan de silva on 10-12-2007 _________________________________
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+j+'	value=\"\" >'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=TXT_SUB_MODEL'+j+'	value=\"\" >'+");


				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_3('+j+')\"></td>'+");
				
				out.println(" '<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MAKE'+j+' maxlength=\"15\" STYLE=\"width:100\" size=\"15\" onblur=\"validate_make('+j+')\">'+");
				out.println(" '<input type=\"Hidden\" NAME=HID_TXT_MAKE'+j+'	value=\"\"></td>'+");
				out.println(" '<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MAKE_HELP'+j+' value=\"Help\" onClick=\"help_button_1('+j+')\"></td>'+ "); 
				
				out.println("'<td width=\"50\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+j+' maxlength=\"5\" style=\"width:40px; text-align:right;\" size=\"5\" onblur=\"val_quantity('+j+')\" value=\"1\" ></td>'+"); // Thamali 2010.12.27 
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_ITEM_SUB_CAT'+j+' STYLE=\"width:90\" maxlength=\"10\" size=\"10\" disabled        ></td>'+");
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_YEAR_OF_MANUFACTURE'+j+' STYLE=\"width:90\" maxlength=\"10\" size=\"10\" disabled ></td>'+");
								
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_STATUS'+j+'  STYLE=\"width:90\" class=\"txt_input\">'+"); //STYLE=\"width:80\"
				                                                    out.println("'<option value=\"N\" >New           </option>'+");
			                                                      out.println("'<option value=\"R\" >Re-Conditioned</option>'+");
																														out.println("'<option value=\"U\" >Used          </option></select></td>'+");
			                        
				
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_PURPOSE'+j+'  STYLE=\"width:90\"  class=\"txt_input\">'+");//STYLE=\"width:80\" 
				                                                    //out.println("'<option value=\"P\" >Personal</option>'+"); // commented by udara 04-09-2019
			                           														//out.println("'<option value=\"B\" >Business</option></select></td>'+"); // commented by udara 04-09-2019
																							out.println(" purpose_option_set + "); // added by udara 04-09-2019
																							out.println(" '</select></td>' + "); // added by udara 04-09-2019
												
			  				
				out.println("'<td width=\"100\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ASSET_NO'+j+' maxlength=\"10\" size=\"10\" STYLE=\"width:90\" ></td>'+");
				//out.println("'<td width=\"50\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+j+' maxlength=\"5\" style=\"width:40px; text-align:right;\" size=\"5\" onblur=\"val_quantity('+j+')\"></td>'+");
				out.println("'<td width=\"50\" align=\"right\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:40px\" value=\" X \" onClick=\"del_row('+j+')\"></td>'+");
				out.println("   '</tr>'+ ");
				out.println("   '</table>';");

								
        out.println("continue;");
				out.println("}");
				
				out.println("else if(array_asset_no[j]==\"\" && array_model[j]!=\"\" ){");
							  
				/*out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<TD WIDTH=\"5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ASSET_NO'+j+' maxlength=\"10\" size=\"10\" value=\"\" onblur=\"validate_city('+j+')\"></TD>'+");
				out.println("'<TD WIDTH=\"5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MAKE'+j+' maxlength=\"15\" size=\"15\" value='+array_make[j]+' onblur=\"validate_make('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_MAKE_HELP'+j+' value=\"Help\" onClick=\"help_button_1('+j+')\"></TD>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_MAKE'+j+'	value='+array_hid_make[j]+' >'+");

			 	out.println("'<TD WIDTH=\"5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MODEL'+j+' STYLE=\"width:100\" maxlength=\"15\" value='+array_model[j]+' size=\"15\" onblur=\"validate_model('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_2('+j+')\"></TD>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_MODEL'+j+'	value='+array_hid_model[j]+' >'+");
				
				out.println("'<TD WIDTH=\"10%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_SUB_MODEL'+j+' STYLE=\"width:100\" maxlength=\"15\" size=\"15\" value='+array_sub_model[j]+' onblur=\"validate_sub_model('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_SUB_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_3('+j+')\"></TD>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+j+'	value='+array_hid_sub_model[j]+' >'+");
				
				out.println("'<td width=\"10%\" align=\"left\"><select name=TXT_STATUS'+j+' STYLE=\"width:100\" class=\"txt_input\">'+");
				                                                    out.println("'<option value=\"N\" >New           </option>'+");
			                                                      out.println("'<option value=\"R\" >Re-Conditioned</option>'+");
																														out.println("'<option value=\"U\" >Used          </option></select></TD>'+");
			                        
				out.println("'<td width=\"5%\" align=\"left\"><select name=TXT_PURPOSE'+j+' TYLE=\"width:50\"  class=\"txt_input\">'+");
				                                                    out.println("'<option value=\"P\" >Personal</option>'+");
			                           														out.println("'<option value=\"B\" >Business</option></select></TD>'+");
			 
				out.println("'<TD WIDTH=\"10%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+j+' maxlength=\"5\" style=\"width:60px; text-align:right;\"; size=\"5\" value='+array_quantity[j]+' onblur=\"val_quantity('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\"></TD>'+");
				out.println("'</tr></table>';");
				*/
				
				//comment by nuwan de silva on 10-09-07---------------------------------------
				/*out.println("m_table.innerHTML+='<table width=\"1140\" align=\"center\" class=\"table\" border=\"0\" >'+ "); //cellspacing=\"0\" cellpadding=\"0\"
				out.println(" '<tr>'+ ");
				
				out.println(" '<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MAKE'+j+' maxlength=\"15\" STYLE=\"width:100\" value=\"'+array_make[j]+'\" size=\"15\" onblur=\"validate_make('+j+')\">'+");
				out.println(" '<input type=\"Hidden\" NAME=HID_TXT_MAKE'+j+'	value=\"'+array_hid_make[j]+'\" ></td>'+");
				out.println(" '<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MAKE_HELP'+j+' value=\"Help\" onClick=\"help_button_1('+j+')\"></td>'+ "); 

				out.println("'<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MODEL'+j+' STYLE=\"width:100\" maxlength=\"15\" value=\"'+array_model[j]+'\" size=\"15\" onblur=\"validate_model('+j+')\">'+");
				out.println("'<input type=\"Hidden\" NAME=HID_TXT_MODEL'+j+'	value=\"'+array_hid_model[j]+'\" ></td>'+");
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_2('+j+')\"></td>'+");
				
				out.println("'<td width=\"140\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_SUB_MODEL'+j+' STYLE=\"width:130\" maxlength=\"15\" size=\"15\" value=\"'+array_sub_model[j]+'\" onblur=\"validate_sub_model('+j+')\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+j+'	value=\"'+array_hid_sub_model[j]+'\" ></td>'+");
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_SUB_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_3('+j+')\"></td>'+");
				
				
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_ITEM_SUB_CAT'+j+' STYLE=\"width:90\" maxlength=\"10\" value='+array_item_sub_cat[j]+' size=\"10\" disabled        ></td>'+");
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_YEAR_OF_MANUFACTURE'+j+' STYLE=\"width:90\" maxlength=\"10\" value='+array_year_of_manufacture[j]+' size=\"10\" disabled ></td>'+");
								
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_STATUS'+j+'  STYLE=\"width:90\" class=\"txt_input\">'+"); //STYLE=\"width:80\"
				                                                    out.println("'<option value=\"N\" >New           </option>'+");
			                                                      out.println("'<option value=\"R\" >Re-Conditioned</option>'+");
																														out.println("'<option value=\"U\" >Used          </option></select></td>'+");
			                        
				
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_PURPOSE'+j+'  STYLE=\"width:90\"  class=\"txt_input\">'+");//STYLE=\"width:80\" 
				                                                    out.println("'<option value=\"P\" >Personal</option>'+");
			                           														out.println("'<option value=\"B\" >Business</option></select></td>'+");
												
			  				
				out.println("'<td width=\"100\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ASSET_NO'+j+' maxlength=\"10\" size=\"10\" STYLE=\"width:90\" ></td>'+");
				out.println("'<td width=\"50\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+j+' maxlength=\"5\" style=\"width:40px; text-align:right;\" size=\"5\" value='+array_quantity[j]+' onblur=\"val_quantity('+j+')\"></td>'+");
				out.println("'<td width=\"50\" align=\"right\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:40px\" value=\" X \" onClick=\"del_row('+j+')\"></td>'+");
				out.println("   '</tr>'+ ");
				out.println("   '</table>';");
				*/
				
				
				
				out.println("m_table.innerHTML+='<table width=\"940\" align=\"center\" class=\"table\" border=\"0\" >'+ "); //cellspacing=\"0\" cellpadding=\"0\"
				out.println(" '<tr>'+ ");
				
				/*out.println("'<td width=\"140\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_SUB_MODEL'+j+' STYLE=\"width:130\" maxlength=\"15\" size=\"15\" value=\"'+array_sub_model[j]+'\" onblur=\"validate_sub_model('+j+')\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+j+'	value=\"'+array_hid_sub_model[j]+'\" ></td>'+");
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_SUB_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_3('+j+')\"></td>'+");
				*/
				
				//__________ added by nuwan de silva on 10-12-2007 _________________________________
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+j+'	value=\"'+array_hid_sub_model[j]+'\" >'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=TXT_SUB_MODEL'+j+'	value=\"'+array_sub_model[j]+'\">'+");
				
				out.println("'<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MODEL'+j+' STYLE=\"width:100\" maxlength=\"15\" value=\"'+array_model[j]+'\" size=\"15\" onblur=\"validate_sub_model('+j+')\">'+");
				out.println("'<input type=\"Hidden\" NAME=HID_TXT_MODEL'+j+'	value=\"'+array_hid_model[j]+'\" ></td>'+");
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_3('+j+')\"></td>'+");
				
				out.println(" '<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MAKE'+j+' maxlength=\"15\" STYLE=\"width:100\" value=\"'+array_make[j]+'\" size=\"15\" onblur=\"validate_make('+j+')\">'+");
				out.println(" '<input type=\"Hidden\" NAME=HID_TXT_MAKE'+j+'	value=\"'+array_hid_make[j]+'\" ></td>'+");
				out.println(" '<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MAKE_HELP'+j+' value=\"Help\" onClick=\"help_button_1('+j+')\"></td>'+ "); 
				out.println("'<td width=\"50\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+j+' maxlength=\"5\" style=\"width:40px; text-align:right;\" size=\"5\" value='+array_quantity[j]+' onblur=\"val_quantity('+j+')\"></td>'+");
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_ITEM_SUB_CAT'+j+' STYLE=\"width:90\" maxlength=\"10\" value='+array_item_sub_cat[j]+' size=\"10\" disabled        ></td>'+");
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_YEAR_OF_MANUFACTURE'+j+' STYLE=\"width:90\" maxlength=\"10\" value='+array_year_of_manufacture[j]+' size=\"10\" disabled ></td>'+");
								
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_STATUS'+j+'  STYLE=\"width:90\" class=\"txt_input\">'+"); //STYLE=\"width:80\"
				                                                    out.println("'<option value=\"N\" >New           </option>'+");
			                                                      out.println("'<option value=\"R\" >Re-Conditioned</option>'+");
																														out.println("'<option value=\"U\" >Used          </option></select></td>'+");
			                        
				
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_PURPOSE'+j+'  STYLE=\"width:90\"  class=\"txt_input\">'+");//STYLE=\"width:80\" 
				                                                    //out.println("'<option value=\"P\" >Personal</option>'+"); // commented by udara 04-09-2019
			                           														//out.println("'<option value=\"B\" >Business</option></select></td>'+"); // commented by udara 04-09-2019
																								out.println(" purpose_option_set + "); // added by udara 04-09-2019
																								out.println(" '</select></td>' + "); // added by udara 04-09-2019
												
			  				
				out.println("'<td width=\"100\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ASSET_NO'+j+' maxlength=\"10\" size=\"10\" STYLE=\"width:90\" ></td>'+");
				//out.println("'<td width=\"50\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+j+' maxlength=\"5\" style=\"width:40px; text-align:right;\" size=\"5\" value='+array_quantity[j]+' onblur=\"val_quantity('+j+')\"></td>'+");
				out.println("'<td width=\"50\" align=\"right\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:40px\" value=\" X \" onClick=\"del_row('+j+')\"></td>'+");
				out.println("   '</tr>'+ ");
				out.println("   '</table>';");

				
				out.println("continue;");
				        
				out.println("}");
				
				
				
				
				out.println("else{");
							
				
				/*out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<TD WIDTH=\"5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ASSET_NO'+j+' maxlength=\"10\" size=\"10\" value='+array_asset_no[j]+' onblur=\"validate_city('+j+')\"></TD>'+");
				out.println("'<TD WIDTH=\"5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MAKE'+j+' maxlength=\"15\" size=\"15\" value='+array_make[j]+' onblur=\"validate_make('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_MAKE_HELP'+j+' value=\"Help\" onClick=\"help_button_1('+j+')\"></TD>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_MAKE'+j+'	value='+array_hid_make[j]+' >'+");

			 	out.println("'<TD WIDTH=\"5%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MODEL'+j+' STYLE=\"width:100\" maxlength=\"15\" value='+array_model[j]+' size=\"15\" onblur=\"validate_model('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_2('+j+')\"></TD>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_MODEL'+j+'	value='+array_hid_model[j]+' >'+");
				
				out.println("'<TD WIDTH=\"10%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_SUB_MODEL'+j+' STYLE=\"width:100\" maxlength=\"15\" size=\"15\" value='+array_sub_model[j]+' onblur=\"validate_sub_model('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_SUB_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_3('+j+')\"></TD>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+j+'	value='+array_hid_sub_model[j]+' >'+");
				
				
				out.println("'<td width=\"10%\" align=\"left\"><select name=TXT_STATUS'+j+' STYLE=\"width:100\" class=\"txt_input\">'+");
				                                                    out.println("'<option value=\"N\" >New           </option>'+");
			                                                      out.println("'<option value=\"R\" >Re-Conditioned</option>'+");
																														out.println("'<option value=\"U\" >Used          </option></select></TD>'+");
			                        
				out.println("'<td width=\"5%\" align=\"left\"><select name=TXT_PURPOSE'+j+' TYLE=\"width:50\"  class=\"txt_input\">'+");
				                                                    out.println("'<option value=\"P\" >Personal</option>'+");
			                           														out.println("'<option value=\"B\" >Business</option></select></TD>'+");
			 
				out.println("'<TD WIDTH=\"10%\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+j+' maxlength=\"5\" style=\"width:60px; text-align:right;\" size=\"5\" value='+array_quantity[j]+' onblur=\"val_quantity('+j+')\">'+");
				out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\"></TD>'+");
				out.println("'</tr></table>';");
				*/
				
			/*	out.println("m_table.innerHTML+='<table width=\"1140\" align=\"center\" class=\"table\" border=\"0\" >'+ "); //cellspacing=\"0\" cellpadding=\"0\"
				out.println(" '<tr>'+ ");
				
				out.println(" '<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MAKE'+j+' maxlength=\"15\" STYLE=\"width:100\" value=\"'+array_make[j]+'\" size=\"15\" onblur=\"validate_make('+j+')\">'+");
				out.println(" '<input type=\"Hidden\" NAME=HID_TXT_MAKE'+j+'	value=\"'+array_hid_make[j]+'\" ></td>'+");
				out.println(" '<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MAKE_HELP'+j+' value=\"Help\" onClick=\"help_button_1('+j+')\"></td>'+ "); 

				out.println("'<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MODEL'+j+' STYLE=\"width:100\" maxlength=\"15\" value=\"'+array_model[j]+'\" size=\"15\" onblur=\"validate_model('+j+')\">'+");
				out.println("'<input type=\"Hidden\" NAME=HID_TXT_MODEL'+j+'	value=\"'+array_hid_model[j]+'\" ></td>'+");
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_2('+j+')\"></td>'+");
				
				out.println("'<td width=\"140\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_SUB_MODEL'+j+' STYLE=\"width:130\" maxlength=\"15\" size=\"15\" value=\"'+array_sub_model[j]+'\" onblur=\"validate_sub_model('+j+')\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+j+'	value=\"'+array_hid_sub_model[j]+'\" ></td>'+");
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_SUB_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_3('+j+')\"></td>'+");
				
				
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_ITEM_SUB_CAT'+j+' STYLE=\"width:90\" maxlength=\"10\" value=\"'+array_item_sub_cat[j]+'\" size=\"10\" disabled        ></td>'+");
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_YEAR_OF_MANUFACTURE'+j+' STYLE=\"width:90\" maxlength=\"10\" value=\"'+array_year_of_manufacture[j]+'\" size=\"10\" disabled ></td>'+");
								
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_STATUS'+j+'  STYLE=\"width:90\" class=\"txt_input\">'+"); //STYLE=\"width:80\"
				                                                    out.println("'<option value=\"N\" >New           </option>'+");
			                                                      out.println("'<option value=\"R\" >Re-Conditioned</option>'+");
																														out.println("'<option value=\"U\" >Used          </option></select></td>'+");
			                        
				
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_PURPOSE'+j+'  STYLE=\"width:90\"  class=\"txt_input\">'+");//STYLE=\"width:80\" 
				                                                    out.println("'<option value=\"P\" >Personal</option>'+");
			                           														out.println("'<option value=\"B\" >Business</option></select></td>'+");
												
			  				
				out.println("'<td width=\"100\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ASSET_NO'+j+' maxlength=\"10\" size=\"10\" value='+array_asset_no[j]+' STYLE=\"width:90\" ></td>'+");
				out.println("'<td width=\"50\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+j+' maxlength=\"5\" style=\"width:40px; text-align:right;\" size=\"5\" value='+array_quantity[j]+' onblur=\"val_quantity('+j+')\"></td>'+");
				out.println("'<td width=\"50\" align=\"right\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:40px\" value=\" X \" onClick=\"del_row('+j+')\"></td>'+");
				out.println("   '</tr>'+ ");
				out.println("   '</table>';");
			*/
			
			
				out.println("m_table.innerHTML+='<table width=\"940\" align=\"center\" class=\"table\" border=\"0\" >'+ "); //cellspacing=\"0\" cellpadding=\"0\"
				out.println(" '<tr>'+ ");
				
				/*out.println("'<td width=\"140\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_SUB_MODEL'+j+' STYLE=\"width:130\" maxlength=\"15\" size=\"15\" value=\"'+array_sub_model[j]+'\" onblur=\"validate_sub_model('+j+')\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+j+'	value=\"'+array_hid_sub_model[j]+'\" ></td>'+");
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_SUB_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_3('+j+')\"></td>'+");
				*/
				
				//__________ added by nuwan de silva on 10-12-2007 _________________________________
				out.println("'<INPUT TYPE=\"Hidden\" NAME=HID_TXT_SUB_MODEL'+j+'	value=\"'+array_hid_sub_model[j]+'\" >'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=TXT_SUB_MODEL'+j+'	value=\"'+array_sub_model[j]+'\">'+");
				
				
				out.println("'<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MODEL'+j+' STYLE=\"width:100\" maxlength=\"15\" value=\"'+array_model[j]+'\" size=\"15\" onblur=\"validate_sub_model('+j+')\">'+");
				out.println("'<input type=\"Hidden\" NAME=HID_TXT_MODEL'+j+'	value=\"'+array_hid_model[j]+'\" ></td>'+");
				out.println("'<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MODEL_HELP'+j+' value=\"Help\" onClick=\"help_button_3('+j+')\"></td>'+");
								
				out.println(" '<td width=\"110\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_MAKE'+j+' maxlength=\"15\" STYLE=\"width:100\" value=\"'+array_make[j]+'\" size=\"15\" onblur=\"validate_make('+j+')\">'+");
				out.println(" '<input type=\"Hidden\" NAME=HID_TXT_MAKE'+j+'	value=\"'+array_hid_make[j]+'\" ></td>'+");
				out.println(" '<td width=\"60\"><input class=\"but_input\" type=\"button\" name=BUT_TXT_MAKE_HELP'+j+' value=\"Help\" onClick=\"help_button_1('+j+')\"></td>'+ "); 
				
				out.println("'<td width=\"50\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+j+' maxlength=\"5\" style=\"width:40px; text-align:right;\" size=\"5\" value='+array_quantity[j]+' onblur=\"val_quantity('+j+')\"></td>'+");
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_ITEM_SUB_CAT'+j+' STYLE=\"width:90\" maxlength=\"10\" value=\"'+array_item_sub_cat[j]+'\" size=\"10\" disabled        ></td>'+");
				out.println("'<td width=\"100\"  align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_YEAR_OF_MANUFACTURE'+j+' STYLE=\"width:90\" maxlength=\"10\" value=\"'+array_year_of_manufacture[j]+'\" size=\"10\" disabled ></td>'+");
								
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_STATUS'+j+'  STYLE=\"width:90\" class=\"txt_input\">'+"); //STYLE=\"width:80\"
				                                                    out.println("'<option value=\"N\" >New           </option>'+");
			                                                      out.println("'<option value=\"R\" >Re-Conditioned</option>'+");
																														out.println("'<option value=\"U\" >Used          </option></select></td>'+");
			                        
				
				out.println("'<td width=\"100\" align=\"left\"><select name=TXT_PURPOSE'+j+'  STYLE=\"width:90\"  class=\"txt_input\">'+");//STYLE=\"width:80\" 
				                                                    //out.println("'<option value=\"P\" >Personal</option>'+"); // commented by udara 04-09-2019
			                           														//out.println("'<option value=\"B\" >Business</option></select></td>'+"); // commented by udara 04-09-2019
																								out.println(" purpose_option_set + "); // added by udara 04-09-2019
																								out.println(" '</select></td>' + "); // added by udara 04-09-2019
												
			  				
				out.println("'<td width=\"100\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_ASSET_NO'+j+' maxlength=\"10\" size=\"10\" value='+array_asset_no[j]+' STYLE=\"width:90\" ></td>'+");
				//out.println("'<td width=\"50\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_QUANTITY'+j+' maxlength=\"5\" style=\"width:40px; text-align:right;\" size=\"5\" value='+array_quantity[j]+' onblur=\"val_quantity('+j+')\"></td>'+");
				out.println("'<td width=\"50\" align=\"right\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:40px\" value=\" X \" onClick=\"del_row('+j+')\"></td>'+");
				out.println("   '</tr>'+ ");
				out.println("   '</table>';");


			
				out.println("}");
						
			 out.println("}");
				
			 out.println("fill_value();");
			 out.println("disable_asset_no();");
				
			 out.println("}");		
					
								
				
					out.println("function validate_data(){"); 
					out.println("//validations goes here"); 
				//	out.println("if(document.Form1.TXT_ASSET_NO.value==\"\"){  "); 
				 // out.println("DIV_TXT_ASSET_NO.style.color='red';");
				 // out.println("return false;"); 
				 // out.println("}"); 
					
					
							
			//		out.println("else{"); 
					out.println("return true;"); 
		//			out.println("}"); 
					out.println("}"); 
		
					out.println("function before_submit(){ "); 
					out.println("   check_asset_no();");
					
					out.println("m_option = document.Form1.hid_status.value;"); 
					out.println("if(m_option=='New') {");
					out.println("m_sav_msg = 'Are you sure you want to Save?'; ");
					out.println("		}"); 
					out.println("else if(m_option=='Edit') {");
					out.println("m_sav_msg = 'Are you sure you want to Modify?'; ");
					out.println("		}"); 
					out.println("else if(m_option=='Delete') {");
					out.println("m_sav_msg = 'Are you sure you want to Delete?'; ");
					out.println("		}"); 
					//out.println("		if(validate_data())"); 
					out.println("	if(confirm(m_sav_msg)) { "); 
					out.println("	if(count_number==0) { "); 
					out.println("		if(validate_data()){"); 
					//out.println("alert('aa');");
					
					out.println("     for (var i=0; i < document.Form1.elements.length; i++ ) {");
					out.println("       document.Form1.elements[i].disabled=false;");
					out.println("     }");
					out.println("   chk_data();");
					out.println("   if(b_flag==0)");
					out.println("		if(m_sav_msg){ "); 
					out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
					//out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Save_Asset_Detail_Equipment?my_screen_name="+m_my_screen+"&INQ_NO="+m_inqNo+"';"); // commented by udara 10-12-2014   //MODIFIED BY NUWAN DE SILVA 26-06-07
					out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Save_Asset_Detail_Equipment?my_screen_name="+m_my_screen+"&INQ_NO="+m_inqNo+"&brk_status="+m_brk_status+"&direct_status="+m_direct_status+"';"); // added by udara 10-12-2014
					out.println("		document.Form1.submit();	"); 
					out.println("		 }"); 
					out.println("	}"); 
					out.println("		}"); 
					out.println("		else { "); 
					out.println("alert('First delete the proforma invoice and valuation data prior to deleting this asset number');");
					out.println("		}");


					out.println("} "); 
									//	out.println("} "); 
					out.println("} "); 
					
					
			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
				
			
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			if  (m_screen.equals("G") ){
			
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Asset_Detail_Equipment?APP_NO="+m_appNo+"&screen=G&my_screen_name=CV';"); 
			}
			else{
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Asset_Detail_Equipment?APP_NO="+m_appNo+"';"); 
			}
			
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Asset_Detail_Equipment?APP_NO="+m_appNo+"&screen=G';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	");
			//--Added by Chandana on 06/09/2007 for Ref no.854			
			out.println("m_make=\"TXT_MAKE\"+document.Form1.hid_row_id.value");
			out.println("m_model=\"TXT_MODEL\"+document.Form1.hid_row_id.value");
			out.println("m_sub_model=\"TXT_SUB_MODEL\"+document.Form1.hid_row_id.value"); 
			out.println("m_quantity=\"TXT_QUANTITY\"+document.Form1.hid_row_id.value");
			
			out.println("m_purpose=\"TXT_PURPOSE\"+document.Form1.hid_row_id.value"); // added by udara 04-09-2019
						
			//out.println(" alert(m_purpose); "); // added by udara 04-09-2019

			out.println("make = document.Form1.elements[m_make].value;");
			out.println("model = document.Form1.elements[m_model].value;");
			out.println("sub_model = document.Form1.elements[m_sub_model].value;");
			out.println("quantity = document.Form1.elements[m_quantity].value;");

            out.println("purpose = document.Form1.elements[m_purpose].value;");
			
			out.println("if(make==\"\"){");
			out.println("alert('Make code can not be null');");
			out.println("} else if(model==\"\"){");			
			out.println("alert('Model code can not be null');");
			out.println("} else if(sub_model==\"\"){");			
			out.println("alert('Sub model code can not be null');");
			out.println("} else if(quantity==\"\"){");			
			out.println("alert('Quantity can not be null');");
			
			// added by udara 04-09-2019
			out.println("} else if(purpose==\"-\"){");			
			out.println("alert('Purpose can not be null');");
			// end by udara 04-09-2019
			
			//--End Ref no.854
			out.println("}else{");		
			out.println("before_submit();"); 
			out.println("}");
			out.println("}"); 
			out.println(""); 
		
								
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MK_Asset_Detail_Equipment\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MK_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MK_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
					
		
					
					out.println("function load_roll_value(m_val){"); 
					if(m_screen.equals("G")){
					out.println("help_box.innerHTML=\" Credit Process - Details of Asset- \"+m_val;"); 
					}
					else{
					out.println("help_box.innerHTML=\" Application Process - Details of Asset- \"+m_val;"); 
					}
					out.println("}"); 
					out.println(""); 
		
					out.println("function load_roll_out_value(){");
					
					
					
					if(m_screen.equals("G")){
					out.println("help_box.innerHTML=\" Marketing - Details of Asset - \"+document.Form1.hid_status.value;"); 
					}
					else{
					out.println("help_box.innerHTML=\" Marketing - Details of Asset - \"+document.Form1.hid_status.value;"); 
					}
					
					out.println("}"); 
		
					out.println("function load_screen_status(m_val){"); 
				 // out.println("   check_asset_no();");
					out.println("if(m_val==\"NEW\"){"); 
					out.println("new_window();"); 
					
					//out.println("document.Form1.BUT_HELP_MAIN.disabled=true;");
					out.println("}");
				//	out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;}"); 
					out.println("else if(m_val==\"HELP\"){"); 
					out.println("load_help_msg();"); 
					out.println("}"); 
					out.println("else if(m_val!=\"EDIT\"){"); 
					//out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
					
			
					out.println("}"); 
					out.println("else{");
					//out.println("document.Form1.TXT_APPLICATION_NO.VALUE=\"\";"); 
					//out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
					out.println("}"); 
					out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
					out.println("if(m_val==\"NEW\"){");
					out.println("document.Form1.hid_status.value=\"New\";"); 
					out.println("}else if(m_val==\"EDIT\"){");  
					out.println("document.Form1.hid_status.value=\"Edit\";");  
					//out.println("document.Form1.TXT_APPLICATION_NO.VALUE=\"\";"); 
					//out.println("document.Form1.BUT_HELP_MAIN.disabled=true;"); 
					out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
					out.println("}else if(m_val==\"DEL\"){");  
					
				//	out.println("	if(count_number>0) {"); 
				//	out.println("alert('First delete the proforma invoice and valuation data prior to deleting this asset number');}");
					
					out.println("document.Form1.hid_status.value=\"Delete\";");  
					//out.println("document.Form1.BUT_HELP_MAIN.disabled=true;"); 
					out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
					//out.println("document.Form1.TXT_APPLICATION_NO.VALUE=\"\";"); 
					out.println("}else if(m_val==\"RACT\"){");  
					out.println("document.Form1.hid_status.value=\"Reactivate\";");  
					out.println("}else{");  
					out.println("document.Form1.hid_status.value=\"\";");  
					out.println("}"); 
					out.println("enable_app_no();");
					out.println("}"); 
					
					
		
					out.println("function MyDialog(){"); 
					out.println("    this.valout   = new Array(10);"); 
					out.println("}		"); 
					out.println(""); 
					
					
						
					
					
					
	/*		out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,txtObj) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
		
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Start+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\"");
			
		//	out.println("window.open(m_url);");
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("    clear_fields(txtObj); ");
			out.println("		} else ");
			
			
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
			
			  out.println("if(oBj.valout[0]=='Next')  {");
				out.println("Next(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount,txtObj);");
				out.println("}");
				out.println("else if  (oBj.valout[0]=='Prev') {");
				out.println("Prev(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount,txtObj);");
				out.println("}		");
				out.println("else if(oBj.valout[0] == 'Exit'){");
				out.println("}");
				out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
				
				out.println("if(IfCount=='99'){"); 
				out.println("		help_update_value_assign_99(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='1'){"); 
				out.println("		help_value_assign_1(document.Form1.hid_row_no.value,oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='2'){"); 
				out.println("		help_value_assign_2(document.Form1.hid_row_no.value,oBj);"); 
				out.println("");
				out.println("}");
				out.println("else if(IfCount=='3'){"); 
				out.println("		help_value_assign_3(document.Form1.hid_row_no.value,oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='4'){"); 
				out.println("		help_value_assign_4(document.Form1.hid_row_no.value,oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='5'){"); 
				out.println("		help_value_assign_5(document.Form1.hid_row_no.value,oBj);"); 
				out.println("}");
				
				
					
			out.println("	}"); 
			out.println("	}"); 
			
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount,txtObj);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount,txtObj);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	else{	"); 
			out.println("    clear_fields(txtObj); ");
			out.println("	}	"); 
			out.println("	}	"); 
			out.println("}"); 
			out.println(""); 

				out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount,txtObj){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,rowNo);"); 
				out.println("}"); 
				out.println(""); 

				out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount,txtObj){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,txtObj);"); 
				out.println("}"); 
				out.println(""); 
				*/
				
				
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,txtObj,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
						
			out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Max+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_fields(txtObj);");
			out.println("	}else");
			
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
		
			
				out.println("if(IfCount=='99'){"); 
				out.println("		help_update_value_assign_99(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='1'){"); 
				out.println("		help_value_assign_1(document.Form1.hid_row_no.value,oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='2'){"); 
				out.println("		help_value_assign_2(document.Form1.hid_row_no.value,oBj);"); 
				out.println("");
				out.println("}");
				out.println("else if(IfCount=='3'){"); 
				out.println("		help_value_assign_3(document.Form1.hid_row_no.value,oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='4'){"); 
				out.println("		help_value_assign_4(document.Form1.hid_row_no.value,oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='5'){"); 
				out.println("		help_value_assign_5(document.Form1.hid_row_no.value,oBj);"); 
				out.println("}");
		
		
		
		
			out.println("	}"); //end next
			
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount,txtObj,Max);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			
			out.println("	}"); //end prev
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount,txtObj,Max);"); 
			out.println("	}	"); 
			out.println("	}		"); ///close
			
			out.println("	else{");
			out.println("	clear_fields(txtObj);");//Added To The Clear The Area Code
			out.println("	}");
			
			
			out.println("	}	"); //
			out.println("}"); 
			out.println(""); 

			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount,txtObj,Max){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,txtObj,Max);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount,txtObj,Max){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,txtObj,Max);"); 
			out.println("}"); 
			out.println(""); 
				
				
					
											
					out.println("function clear_fields(txtObj){"); 
					out.println("   document.Form1.elements[txtObj].value=\"\"");
					out.println("}		"); 					
					
					
					out.println("function help_button_1(rowNo) {"); 
					
					//out.println("m_make=\"TXT_MAKE\"+rowNo;");
					//out.println("m_model=\"TXT_MODEL\"+rowNo;");
					
					//added by nuwan de silva 21-05-07-------------------
					out.println("m_make=\"TXT_MAKE\"+rowNo;");
					out.println("m_model=\"TXT_MODEL\"+rowNo;");
					
					out.println("    document.Form1.hid_help_type.value=\"1\";"); 
					out.println("    document.Form1.hid_row_no.value=rowNo;"); 
					
					out.println("   Sql =\"m_help_TXT_MAKE\";"); 
					out.println("    Crit =document.Form1.elements[m_make].value+\"@\"+document.Form1.elements[m_model].value+\"@Y@\";"); 
				//	out.println("    Crit = document.Form1.elements[m_make].value+\"@Y@\";"); 
				
				// out.println("alert('Crit'+Crit);");
					out.println("    HelpBox('0','10','0',Crit,Sql,'1',m_make,'0');"); 
					
					out.println("}"); 
					out.println(""); 
		
					
					out.println("function help_value_assign_1(rowNo,oBj) {"); 
					out.println("m_make=\"TXT_MAKE\"+rowNo;");
					out.println("m_hid_make=\"HID_TXT_MAKE\"+rowNo;");
					
					out.println("    document.Form1.elements[m_hid_make].value=oBj.valout[2];"); 
					out.println("    document.Form1.elements[m_make].value=oBj.valout[3];"); 
					out.println("");
					
					out.println("}");
					
					
					
					out.println("function help_button_2(rowNo) {"); 
					
					out.println("m_model=\"TXT_MODEL\"+rowNo;");
					out.println("m_make=\"TXT_MAKE\"+rowNo;");
					out.println("m_sub_model=\"TXT_SUB_MODEL\"+rowNo;");
					

					out.println("    document.Form1.hid_help_type.value=\"2\";"); 
					out.println("    document.Form1.hid_row_no.value=rowNo;"); 
					
											
					out.println("   Sql =\"m_help_TXT_MODEL_CODE_sql\";"); 
					
					
					out.println("    Crit =document.Form1.elements[m_model].value+\"@\"+document.Form1.elements[m_make].value+\"@\"+document.Form1.elements[m_sub_model].value+\"@Y@\";"); 
					
				//	out.println("    Crit = document.Form1.elements[m_model].value+\"@Y@\";"); 
				// out.println("alert('Crit'+Crit);");
					out.println("    HelpBox('0','10','0',Crit,Sql,'2',m_model,'0');"); 
					
					out.println("}"); 
					out.println(""); 
					
					
					out.println("function help_value_assign_2(rowNo,oBj) {"); 
					
					out.println("m_model=\"TXT_MODEL\"+rowNo;");
					out.println("m_hid_model=\"HID_TXT_MODEL\"+rowNo;");
					out.println("m_item_sub=\"TXT_ITEM_SUB_CAT\"+rowNo;");
					
					
					out.println("    document.Form1.elements[m_hid_model].value=oBj.valout[2];"); 
					out.println("    document.Form1.elements[m_model].value=oBj.valout[3];"); 
					out.println("    document.Form1.elements[m_item_sub].value=oBj.valout[4];"); 
					out.println("}");
					
					
					
					out.println("function help_button_3(rowNo) {"); 
					
					out.println("m_sub_model=\"TXT_SUB_MODEL\"+rowNo;");
					out.println("m_model=\"TXT_MODEL\"+rowNo;");
					out.println("    document.Form1.hid_help_type.value=\"3\";"); 
					out.println("    document.Form1.hid_row_no.value=rowNo;"); 
					
					out.println("   Sql ='m_help_TXT_SUB_MODEL_sql';"); 
					
										
					out.println("    Crit =document.Form1.elements[m_sub_model].value+\"@\"+document.Form1.elements[m_model].value+\"@Y@\";"); 
					
					//out.println("    Crit = document.Form1.elements[m_sub_model].value+\"@Y@\";"); 
					out.println("    HelpBox('0','10','5',Crit,Sql,'3',m_sub_model,'0');"); 
					//out.println("    HelpBox('0','10','6',Crit,'m_help_TXT_SUB_MODEL_sql','3');"); 
					
					out.println("}"); 
					out.println(""); 
					
					out.println("function help_value_assign_3(rowNo,oBj) {"); 
					//out.println("alert(oBj.valout);");
					out.println("m_sub_model=\"TXT_SUB_MODEL\"+rowNo;");
					out.println("m_hid_sub_model=\"HID_TXT_SUB_MODEL\"+rowNo;");
					out.println("m_year_of_manufac=\"TXT_YEAR_OF_MANUFACTURE\"+rowNo;");
					
					out.println("    document.Form1.elements[m_hid_sub_model].value=oBj.valout[2];"); 
					out.println("    document.Form1.elements[m_sub_model].value=oBj.valout[3];"); 
					//out.println("    document.Form1.elements[m_year_of_manufac].value=oBj.valout[12];"); 
					out.println("    document.Form1.elements[m_year_of_manufac].value=oBj.valout[10];"); 
					
					out.println("m_make=\"TXT_MAKE\"+rowNo;");
					out.println("m_hid_make=\"HID_TXT_MAKE\"+rowNo;");
					//out.println("    document.Form1.elements[m_hid_make].value=oBj.valout[6];"); 
					//out.println("    document.Form1.elements[m_make].value=oBj.valout[7];"); 
					out.println("    document.Form1.elements[m_hid_make].value=oBj.valout[4];"); 
					out.println("    document.Form1.elements[m_make].value=oBj.valout[5];"); 
					
					out.println("m_model=\"TXT_MODEL\"+rowNo;");
					out.println("m_hid_model=\"HID_TXT_MODEL\"+rowNo;");
					out.println("m_item_sub=\"TXT_ITEM_SUB_CAT\"+rowNo;");
					/*out.println("    document.Form1.elements[m_hid_model].value=oBj.valout[4];"); 
					out.println("    document.Form1.elements[m_model].value=oBj.valout[5];"); 
					out.println("    document.Form1.elements[m_item_sub].value=oBj.valout[8];"); 
					*/
					
					out.println("    document.Form1.elements[m_hid_model].value=oBj.valout[2];"); 
					out.println("    document.Form1.elements[m_model].value=oBj.valout[3];"); 
					out.println("    document.Form1.elements[m_item_sub].value=oBj.valout[6];"); 
					
					
					out.println("}");
					
					
					/*out.println("function help_button_4(rowNo) {"); 
					
					out.println("m_sub_model=\"TXT_CITY\"+rowNo;");
					out.println("    document.Form1.hid_help_type.value=\"4\";"); 
					out.println("    document.Form1.hid_row_no.value=rowNo;"); 
					
					out.println("   Sql =\"m_help_TXT_CITY_CODE_sql\";"); 
					out.println("    Crit = document.Form1.elements[m_sub_model].value+\"@Y@\";"); 
					out.println("    HelpBox('0','10','2',Crit,Sql,'4',rowNo);"); 
					out.println("}"); 
					out.println(""); */
					
					out.println("function help_value_assign_4(rowNo,oBj) {"); 
					out.println("m_city=\"TXT_CITY\"+rowNo;");
										
					out.println("    document.Form1.elements[m_city].value=oBj.valout[2];"); 
										
					out.println("}");
					
					/*
					out.println("function help_button_5(rowNo) {"); 
					
					out.println("m_supplier=\"TXT_SUPPLIER\"+rowNo;");
					out.println("    document.Form1.hid_help_type.value=\"5\";"); 
					out.println("    document.Form1.hid_row_no.value=rowNo;"); 
					
					out.println("   Sql =\"m_help_TXT_SUPPLIER_sql\";"); 
					out.println("    Crit = document.Form1.elements[m_supplier].value+\"@Y@\";"); 
					out.println("    HelpBox('0','10','0',Crit,Sql,'5');"); 
					out.println("}"); 
					out.println(""); 
					
					out.println("function help_value_assign_5(rowNo,oBj) {"); 
					out.println("m_supplier=\"TXT_SUPPLIER\"+rowNo;");
										
					out.println("    document.Form1.elements[m_supplier].value=oBj.valout[2];"); 
										
					out.println("}");
					*/
		
					out.println("function help_update() {"); 
													  
					out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@Y@\";"); 
					
					out.println("    HelpBox('0','10','0',Crit,'m_help_TXT_REPOSSESSION_NO_sql','99');"); 
					out.println("}"); 
				
					out.println("function help_update_value_assign_99(oBj) {"); 
					out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
					
					out.println("    assignState('M7');"); 
			    out.println("    makeRequest(document.Form1.TXT_APPLICATION_NO);");
			
					out.println("}"); 
														
					
		      out.println("function disable_app_no(){")			;
		   	//  out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
					//out.println("    document.Form1.hid_row_no.value=rowNo;"); 
					//out.println("document.Form1.TXT_TOT.value=sum;");
				  out.println("}");
		
					
					
					out.println("function check_change() {");
					out.println("if(document.Form1.CHK_ACK.checked==false){");
					out.println("document.Form1.CHK_ACK.value=\"0\"");
					out.println("}");
					out.println("else if(document.Form1.CHK_ACK.checked==true){");
					out.println("document.Form1.CHK_ACK.value=\"1\"");
					out.println("}");
					out.println("if(document.Form1.CHK_ACK.value==\"1\"){");
					if(m_data_val1.equals("VERIFY-M") || m_data_val1.equals("VERIFY1")){
					out.println("window.opener.document.Form1.Btn_approve.disabled=false;"); 
					}
					out.println("window.opener.document.Form1.Btn_Invoice_Det.disabled=false;");
					out.println("}");
					out.println("else if(document.Form1.CHK_ACK.value==\"0\"){");
					if(m_data_val1.equals("VERIFY-M") || m_data_val1.equals("VERIFY1")){
					out.println("window.opener.document.Form1.Btn_approve.disabled=false;"); 
					}
					out.println("window.opener.document.Form1.Btn_Invoice_Det.disabled=true;");
  				out.println("}");
					out.println("chk_arry[0]=document.Form1.CHK_ACK.value;");
					//out.println("alert(document.Form1.CHK_ACK.value)");
					out.println("}");
			
		
					out.println("</script>"); 
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_application_no(),disable_app_no(),header(),add_row()\">"); //load_lock()
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
					out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			    out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_app_no' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_sum' VALUE=\"\">");  
					out.println("<INPUT TYPE='Hidden' NAME='hid_row_id' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_MK_ASSET_DETAIL_EQUIPMENT\">");  //Added By Nuwan De Silva 25-05-07
					
					
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Application Process</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
   				//		out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_screen()' value=\"Close\"></td>"); 
    
					//out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_screen(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
					
			if  (m_screen.equals("G") && m_my_screen.equals("CV")  ){
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name=\"close2\" onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_screen2()' value=\"Close\"></td>");  
			}
			if (!m_screen.equals("G")){
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name=\"close2\" onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_screen2()' value=\"Close\"></td>");  
			}
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
		
					out.println("<table align='center' width='100%' class='table'>"); 
		
					/*out.println("<tr >"); 
					out.println("<td width='30%' ><DIV id='DIV_TXT_ASSET_NO'  class=div_input>Asset No </DIV></td>"); 
					out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ASSET_NO' maxlength='15' size='15' onblur=\"assignState('M1'),makeRequest(document.Form1.TXT_ASSET_NO)\">"); 
					out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update('1','10','6','m_help_TXT_ASSET_NO','99')\" disabled></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); */
					
					out.println("<tr >"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application No </DIV></td>"); 
					out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15' onblur=\"assignState('M1'),makeRequest(document.Form1.TXT_APPLICATION_NO)\" disabled >"); 
					//out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" >"); 
					out.println("</td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
				  out.println("</table>"); 
					out.println("<br>"); 
					
					
			   out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr align='right'>");  
			   out.println("<td width='90%'><input class='but_input' type='button' name='MORE_BUT' value=\"More\" onClick=\"add_row()\"></td>"); 
				 out.println("<td width='*%'></td>"); 
				 out.println("</tr>");  
			   out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     out.println("</tr>"); 
			
			   out.println("</table>");
					
					out.println("<br>"); 
					out.println("<br>"); 
					out.println("<br>"); 
					
				 out.println("<table align='center' width='100%' border=\"0\" class='table'>"); 
					if  (m_screen.equals("G")){
					//out.println("<tr><td style='{title:'Check here to Verify before closing'}'><b>Acknowledge<input  type=\"checkbox\" name=CHK_ACK value=\"N\" unchecked onclick=\"check_change()\" ></td></tr>");
					out.println("<tr><td width='10%'><b>Acknowledge<input  type=\"checkbox\" name=CHK_ACK value=\"N\" unchecked onclick=\"check_change()\" ></td></tr>");
			out.println("<tr><td width='10%' ><input type=\"button\" class='mainbut' style=\"width:140px\" name=\"close3\" onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Next\");'  onclick='close_screen()' value=\"Proceed to Next Level\"></td></tr>");  
										 out.println("<tr>");  
			   out.println("</tr>");	
							out.println("<tr>");  
			   out.println("</tr>");
			}
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
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
					out.println("</body>"); 
					out.println("</html>"); 
      }
	  }catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
      //return null;
		}finally{
		 // if(rs    !=null){try{rs.close();   }catch(Exception e){}}
		//	if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	   // if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e2){}}
			//try{conn.setAutoCommit(true);
		}
	}
}


