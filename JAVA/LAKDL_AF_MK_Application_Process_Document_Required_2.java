//--
//SCREEN NAME:APPLICATION PROCESS DOCUMENTS REQUIRED
//CREATED BY:NUWAN DE SILVA
//DATE/TIME:26/12/2006
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MK_Application_Process_Document_Required_2 extends javax.servlet.http.HttpServlet 
{ 
	Connection conn;
	ServletOutputStream out =  null;
	Statement stmt;
	public ResultSet rs;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res) 
	{ 
		 
		try 
		{ 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
				
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			
		//	String m_html_client_url = m_sn_methods.html_client_url;
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			int no_of_clinet_doc=0;
			int no_of_core_doc=0;
			
			
		  String m_username=m_sn_methods.username; 			//			= "AA";//m_sn_methods.username;
			
			String m_hid_records,m_app_no,m_client_code,m_core_app_code,m_status;
			String m_name="";
			String m_core_name="";
			String m_txt_type="";
			String m_followup_num="";
			String m_scr_approv="Y";//date : 2007-05-09 
															//modified by : delanjali 
															//reason: documents are not saved for co appliant
			String m_close="N";
			String m_Hid_scr_name="";
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			String m_chksql = req.getParameter("chksql");
			
			
			if(m_chksql.equals("main_page"))
			{
			stmt = conn.createStatement ();
			
			m_app_no = req.getParameter("APP_NO");
		//	m_txt_type = req.getParameter("TXT_TYPE");
			m_client_code = req.getParameter("CLIENT_CODE");
			m_core_app_code = req.getParameter("CORE_APP_CODE");
			m_hid_records = req.getParameter("hid_records");
			//int size=Integer.parseInt(m_hid_records);
			
			//out.println(m_hid_records);
			
			if(m_core_app_code.equals("") )
			{
			m_core_app_code="-";
			
			}
			
			if(m_hid_records.equals("A") || m_hid_records.equals(""))
			{
			m_close="Y";
						
			}
			
			
					//	out.println("$"+req.getParameter("Hid_scr_name"));
			
			if(req.getParameter("scr_approv")!=null)
			{
			m_scr_approv=req.getParameter("scr_approv");
			}
			
			if(req.getParameter("Hid_scr_name")!=null)
			{
			m_Hid_scr_name=req.getParameter("Hid_scr_name");
			}
			
			//m_Hid_scr_name="AF_MK_APPLICATION_PROCESS";
			
			

			
			m_status= req.getParameter("ac_stauts");
			//String array_gur_code;
		
		
		 			rs= stmt.executeQuery ("SELECT  "+
																 															
    														 "	    FULL_NAME "+
																 "	FROM "+
																 "			"+m_schema_name+".AF_CO_MAS_CLIENT		 "+
  															 "  WHERE CLIENT_CODE=UPPER('"+m_client_code+"') ");
				
				
				boolean more=rs.next();
				if(more)
				{
				m_name=rs.getString(1);
				}
				
				
				rs= stmt.executeQuery ("SELECT  "+
																 															
    														 "	    FULL_NAME "+
																 "	FROM "+
																 "			"+m_schema_name+".AF_CO_MAS_CLIENT		 "+
  															 "  WHERE CLIENT_CODE=UPPER('"+m_core_app_code+"') ");
				
				
				 more=rs.next();
				if(more)
				{
				m_core_name=rs.getString(1);
				}
				
				
				rs= stmt.executeQuery ("SELECT  "+
																 															
    														 "	    TRANSACTION_TYPE "+
																 "	FROM "+
																 "			"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS		 "+
  															 "  WHERE APPLICATION_NO=UPPER('"+m_app_no+"') ");
				
				
				 more=rs.next();
				if(more)
				{
				m_txt_type=rs.getString(1);
				}
			//	out.println("m_txt_type"+m_txt_type);
				

				rs= stmt.executeQuery ("SELECT  "+
																 															
    														 "	    FOLLOW_UP_NO "+
																 "	FROM "+
																 "			"+m_schema_name+".AF_CO_PRO_FOLLOW_UP		 "+
  															 "  WHERE ID_NO=UPPER('"+m_app_no+"') ");
				
				
				more=rs.next();
				if(more)
				{
				m_followup_num=rs.getString(1);
				}
			
			
			String m_my_screen="";

			
		//	else { // if (m_appNo.trim().equals("APP_NO")) {
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>Application Process - Documents Required</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">"); 
					
					
			out.println("var lineno=0;");
			out.println("var arr_size=0;");
			out.println("var lineno_inv_doc=0;");
			out.println("var arr_size_inv_doc=0;");
			out.println("var array_gur_code_size=0;");
			out.println("var array_invoice_size=0;");
		  out.println("var array_asset_size=0;");
			 out.println("var row_asset_no=0;");
			

			
			
			out.println("var new_data_vec_gur=new Array();");
			out.println("var new_data_vec_inv=new Array();");


			
			out.println("var b_flag=0;"); //Boolean Variable To Hold The Status.
			out.println("var arr_size=0;");
			out.println("var row_count=0;");
			out.println("var row_no=0;");
			out.println("var invoice_no_count=0;");
			out.println("var row_invoice_no=0;");
			
			
			out.println("var asset_no_count=0;");
			out.println("var row_asset_no=0;");
			
			out.println("var flag1=0;"); //Boolean Variable To Hold The Status.
			out.println("var flag2=0;"); //Boolean Variable To Hold The Status.
			out.println("var flag3=0;"); //Boolean Variable To Hold The Status.
			out.println("var b_flag_on=0;"); //Boolean Variable To Hold The Status.
			out.println("var flag11=0; "); 
			out.println("var count11=0; "); 
			out.println("var flag22=0; "); 
			out.println("var count22=0; "); 
			out.println("var flag33=0; "); 
			out.println("var count33=0; "); 
			out.println("var flag44=0; "); 
			out.println("var count44=0; "); 
			out.println("var b_flag_asset=0; "); 
			

			
			out.println("var count1=0;"); //Boolean Variable To Hold The Status.
			out.println("var count2=0;"); //Boolean Variable To Hold The Status.
			out.println("var count3=0;"); //Boolean Variable To Hold The Status.
			
			out.println("var array_gur_code=new Array();");
			out.println("var array_gur_name=new Array();");
			
			out.println("var array_invoice=new Array();");
			out.println("var array_asset=new Array();");
			
			

			
		//	out.println("chk_arry[0]=\"0\";");	
						
	
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			
						
			
			out.println("function assign_app_no(){");
		//	out.println("alert('"+m_app_no+"');");
			out.println(" app_id.innerHTML=\""+m_app_no+"\";"); 
		//	out.println(" app_id.innerHTML=\""+m_name+"\";"); 
			out.println(" document.Form1.hid_app_no.value=\""+m_app_no+"\";"); 
			out.println(" document.Form1.hid_client_no.value=\""+m_client_code+"\";"); 
			out.println(" document.Form1.hid_core_app_code.value=\""+m_core_app_code+"\";"); 
			out.println(" document.Form1.hid_followup_num.value=\""+m_followup_num+"\";"); 
			out.println(" document.Form1.hid_scr_approv.value=\""+m_scr_approv+"\";"); 
	//	out.println("alert('value'+document.Form1.hid_scr_approv.value);");
			out.println("display_client_documents();");
			out.println("}");
			
			
			
			out.println("function display_client_documents(){");
			
			out.println("assignState('M10')");
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Procee_Documents_gurantor&data_val_app_no="+m_app_no+"&ac_status=Y\";");
		 // out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
						
			out.println("}");
			
			
			
			out.println("function load_value(val){");
						
			out.println("    document.Form1.TXT_APPLICATION_NO.value=\""+m_app_no+"\";"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
			out.println("load_roll_value('New')");
			out.println("}");
			
				
			out.println("function enable_app_no(){");
		
			out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\"){");
			//out.println("    document.Form1.TXT_APPLICATION_NO.value=\"\";"); 
			//out.println("    document.Form1.TXT_APPLICATION_NO.disabled=false;"); 
			out.println("}");
			out.println("}");
			
			
			
			

			
			
			out.println("function close_screen() {");
			
			//out.println("	if(document.Form1.close2.value==\"Proceed to Next Level\"){");
			out.println("m_close_status=\""+m_close+"\"");
			//out.println("alert('ad'+m_close_status);");

      out.println("	if(m_close_status==\"Y\"){");
			out.println(" if(document.Form1.CHK_ACK.checked==true ){ ");

			out.println("		if(confirm(\"Are you sure you want to Proceed to Next Level?\")){ "); 

		//	out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		window.close();"); 
			out.println("		}"); 
			
			out.println(" }");
			
			out.println(" else { ");
			out.println(" alert('Please check acknowledge to proceed next level'); ");
			out.println(" } ");
			
			
			out.println("		}"); 
			
			//out.println("	if(document.Form1.close2.value==\"Close\"){");
			out.println("	else if(m_close_status==\"N\"){");
		//	out.println("		if(confirm(\"Are you sure you want to Proceed to next level?\")){ "); 

			out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		window.close();"); 
			out.println("		}"); 
			out.println("		}"); 
				
				
			out.println("}");
			
			
			out.println("function show_client_details(row_No) {"); 
						
			out.println("show_client(array_gur_code[row_No]);"); 
			
			out.println("}"); 
			
		  out.println("function show_inv_details(row_No) {"); 
						
			out.println("show_proforma_invoice_drill(array_invoice[row_No]);"); 
			
			out.println("}");
			
		  out.println("function show_make_details(row_No) {"); 
						
			out.println("show_make_details_drill(array_invoice[row_No]);"); 
			
			out.println("}");


	   out.println("function show_model_details(row_No) {"); 
						
			out.println("show_model_details_drill(array_invoice[row_No]);"); 
			
			out.println("}");

			
			
			 out.println("function show_doc_details(row_No) {"); 
						
			out.println("show_document_drill(new_data_vec_gur[row_No]);"); 
			
			out.println("}");
			
			out.println("function show_doc_inv_details(row_No) {"); 
						
			out.println("show_document_drill(new_data_vec_inv[row_No]);"); 
			
			out.println("}");
			
			
			
			out.println("function header(){");
									   					
			
			
		 	out.println("m_header='<tr><td  width=\"*%\" ><b><U>Required Documents - Gurantor </U></td></tr>';");
			//	out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_gur_code'+row_no+'	VALUE='+array_gur_code[row_no]+'>';");
				//out.println("m_make1='<tr><td width=\"15%\" id=\"MK_NAME2\" ></td>'+"); 
			//out.println("'</tr>';");	
			
			out.println("m_table_gurantor.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_header+'</table>';");
					
				
			out.println("m_header_data='<tr><td  width=\"30%\" ><b>Guarantor Code</b></td><td  width=\"*%\"  STYLE=\"{cursor:hand;}\"  onclick=\"show_client_details('+[row_no]+')\" ><u>'+array_gur_code[row_no]+'</u></td></tr>'+");
			out.println("'<tr><td  width=\"30%\" >Guarantor Name</td><td  width=\"*%\" >'+array_gur_name[row_no]+'</td></tr>';");
					
			out.println("m_table_gurantor.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_header_data+'</table>';");

					
			
			//==========if call from Application process====================================================
			out.println("if(document.Form1.hid_scr_approv.value==\"N\"){");
			
			out.println("m_code='<TD WIDTH=\"15%\"     align=\"left\"><B>Document Code</B></TD>';");
			out.println("m_desc='<TD WIDTH=\"30%\"     align=\"left\"><B>Description</B></TD>';");
			out.println("m_status='<TD WIDTH=\"10%\"     align=\"left\"><B>Status</B></TD>';");
			
			out.println("m_remark='<TD WIDTH=\"15%\"     align=\"left\"><B>Remarks</B></TD>';");
			out.println("m_fol_status='<TD WIDTH=\"15%\"     align=\"left\"><B>Follow up Status</B></TD>';");
			out.println("m_fol_remarks='<TD WIDTH=\"15%\"     align=\"left\"><B>Follow up Remarks</B></TD>'; ");
			
			out.println("m_writedata='<TR>'+m_code+m_desc+'</TR>';"); 
			//out.println("m_writedata='<TR>'+m_code+m_desc+m_status+m_remark+m_fol_status+m_fol_remarks+'</TR>';"); 
			
		  out.println("}");
			
			//==========if call from credit process====================================================
			out.println("else if(document.Form1.hid_scr_approv.value==\"Y\"){");		
			
			out.println("m_code='<TD WIDTH=\"15%\"     align=\"left\"><B>Document Code</B></TD>';");
			out.println("m_desc='<TD WIDTH=\"25%\"     align=\"left\"><B>Description</B></TD>';");
			out.println("m_status='<TD WIDTH=\"10%\"     align=\"left\"><B>Status</B></TD>';");
			out.println("m_not='<TD WIDTH=\"10%\"     align=\"left\"><B>Not Applicable</B></TD>';");
			
			out.println("m_remark='<TD WIDTH=\"15%\"     align=\"left\"><B>Remarks</B></TD>';");
			out.println("m_fol_status='<TD WIDTH=\"10%\"     align=\"left\"><B>Follow up Status</B></TD>';");
			out.println("m_fol_remarks='<TD WIDTH=\"15%\"     align=\"left\"><B>Follow up Remarks</B></TD>'; ");
			
			out.println("m_writedata='<TR>'+m_code+m_desc+'</TR>';");
			//out.println("m_writedata='<TR>'+m_code+m_desc+m_status+m_not+m_remark+m_fol_status+m_fol_remarks+'</TR>';"); 
			
			out.println("}");
			
						  			  
						
			out.println("m_table_gurantor.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			
		
     	out.println("}");
				
			///============end of header guarantor=============================================
			
			
			
				
			out.println("function header_inv_doc(){");
			    
					
					
					out.println("if(lineno_inv_doc==0){");
		
		  		out.println("m_header='<hr><tr><td  width=\"*%\" ><b><U>Documents Required - Asset</U></td></tr>';");
						
					out.println("m_asset.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	    out.println("m_header+'</table>';");
					
					out.println("}");
					
					// udara 29-10-2013
					/*
					out.println("m_header_data='<br><tr><td  width=\"30%\" ><b>Invoice Number</b></td><td  width=\"*%\" STYLE=\"{cursor:hand;}\"  onclick=\"show_inv_details('+[row_invoice_no]+')\" ><u>'+array_invoice[row_invoice_no]+'</u></td></tr>';");
					
					out.println("m_header_data_asset='<tr><td  width=\"30%\"  >Make Code</td><td  width=\"*%\" style=cursor:hand;cursor-color:blue onclick=show_make_details('+[row_invoice_no+1]+') ><u>'+array_invoice[row_invoice_no+1]+'</u></td></tr>'+");
					out.println("'<tr><td  width=\"30%\"  >Model Code</td><td  width=\"*%\" style=cursor:hand;cursor-color:blue onclick=show_model_details('+[row_invoice_no+2]+') ><u>'+array_invoice[row_invoice_no+2]+'</u></td></tr>'+");
                    out.println("'<tr><td  width=\"30%\" >Engine No</td><td  width=\"*%\" >'+array_invoice[row_invoice_no+3]+'</td></tr>'+");
					out.println("'<tr><td  width=\"30%\" >Chassis No</td><td  width=\"*%\" >'+array_invoice[row_invoice_no+4]+'</td></tr>'+");
					out.println("'<tr><td  width=\"30%\" >Vehicle No</td><td  width=\"*%\" >'+array_invoice[row_invoice_no+5]+'</td></tr>';");
					*/
					
					out.println("m_header_data='<br><tr> </tr>';");
					
					out.println("m_header_data_asset='<tr>  </tr>'+");
					out.println("'<tr> </tr>'+");
                    out.println("'<tr> </tr>'+");
					out.println("'<tr> </tr>'+");
					out.println("'<tr> </tr>';");
					
					

					//out.println("'<tr><td  width=\"30%\" >Gurantor Name</td><td  width=\"*%\" >'+array_gur_name[row_invoice_no]+'</td></tr>';");
					
					out.println("m_asset.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	    out.println("m_header_data+m_header_data_asset+'</table>';");

					out.println("if(document.Form1.hid_scr_approv.value==\"N\"){");
			
					out.println("m_code='<TD WIDTH=\"15%\"     align=\"left\"><B>Document Code</B></TD>';");
					out.println("m_desc='<TD WIDTH=\"30%\"     align=\"left\"><B>Description</B></TD>';");
					out.println("m_status='<TD WIDTH=\"10%\"     align=\"left\"><B>Status</B></TD>';");
					out.println("m_remark='<TD WIDTH=\"15%\"     align=\"left\"><B>Remarks</B></TD>';");
					out.println("m_fol_status='<TD WIDTH=\"15%\"     align=\"left\"><B>Follow up Status</B></TD>';");
					out.println("m_fol_remarks='<TD WIDTH=\"15%\"     align=\"left\"><B>Follow up Remarks</B></TD>'; ");
			
						
					//out.println("m_writedata='<TR>'+m_code+m_desc+m_status+m_remark+m_fol_status+m_fol_remarks+'</TR>';"); 
					out.println("m_writedata='<TR>'+m_code+m_desc+'</TR>';"); 
					
					out.println("}");
					out.println("else if(document.Form1.hid_scr_approv.value==\"Y\"){");
					
					
					out.println("m_code='<TD WIDTH=\"15%\"     align=\"left\"><B>Document Code</B></TD>';");
					out.println("m_desc='<TD WIDTH=\"25%\"     align=\"left\"><B>Description</B></TD>';");
					out.println("m_status='<TD WIDTH=\"10%\"     align=\"left\"><B>Status</B></TD>';");
					out.println("m_not_app='<TD WIDTH=\"10%\"     align=\"left\"><B>Not Applicable</B></TD>';");
					out.println("m_remark='<TD WIDTH=\"15%\"     align=\"left\"><B>Remarks</B></TD>';");
					out.println("m_fol_status='<TD WIDTH=\"10%\"     align=\"left\"><B>Follow up Status</B></TD>';");
					out.println("m_fol_remarks='<TD WIDTH=\"15%\"     align=\"left\"><B>Follow up Remarks</B></TD>'; ");
			
						
					//out.println("m_writedata='<TR>'+m_code+m_desc+m_status+m_not_app+m_remark+m_fol_status+m_fol_remarks+'</TR>';"); 
					out.println("m_writedata='<TR>'+m_code+m_desc+'</TR>';"); 
					
					out.println("}");
					
	  			  
						
				out.println("m_asset.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	  out.println("m_writedata+'</table>';");
			  
				
				
		
     	out.println("}");
				

					
			//==assign the data guarantor=========================
			out.println("function display_data_gurantor(data_vec){");
				
				out.println("				new_data_vec_gur=data_vec;");

			
	      out.println("var i=0;");
  			out.println("var j=0;");
				
				out.println("header();");
				
				
				//==if application process==========
				out.println("if(document.Form1.hid_scr_approv.value==\"N\"){");
			
			  out.println("while(i<data_vec.length){");
				
				out.println("m_code='<TD WIDTH=\"15%\" align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"show_doc_details('+[i]+')\" ><u>'+data_vec[i]+'</u></td>';");
				out.println("m_description='<TD WIDTH=\"30%\" align=\"left\">'+data_vec[i+1].replace(\"$\",\"&\")+'</TD>';");
				
													
				out.println("					if(data_vec[i+3]==\"Y\" ) ");
				out.println("					{ ");
				out.println("m_chk_status='<TD WIDTH=\"10%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_GUR'+lineno+' value=\"on\" checked onClick=\"Val_Change_Gur('+lineno+')\" ></TD>';");
				out.println("					} ");
				
				out.println("					else if(data_vec[i+3]==\"-\"  || data_vec[i+3]==\"N\" || data_vec[i+3]==\"A\" ) ");
				out.println("					{ ");
				out.println("m_chk_status='<TD WIDTH=\"10%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_GUR'+lineno+' value=\"off\" onClick=\"Val_Change_Gur('+lineno+')\"  ></TD>';");
				out.println("					} ");
											
				
				out.println("m_remark='<TD WIDTH=\"15%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_REMARK_GUR'+lineno+' maxlength=\"100\" size=\"10\" value=\"'+data_vec[i+2]+'\"></TD>';");
				
				out.println("					if(data_vec[i+5]==\"-\") ");
				out.println("					{ ");
				out.println("m_chk_status_fol='<TD WIDTH=\"15%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_FOLLOWUP_GUR'+lineno+' maxlength=\"50\"  size=\"10\" onClick=\"Val_Change_Fol_Gur('+lineno+')\" value=\"off\"></TD>';");
				out.println("					} ");
				
				out.println("					else ");
				out.println("					{ ");
				out.println("m_chk_status_fol='<TD WIDTH=\"15%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_FOLLOWUP_GUR'+lineno+' maxlength=\"50\"  size=\"10\" onClick=\"Val_Change_Fol_Gur('+lineno+')\" value=\"on\" checked ></TD>';");
				out.println("					} ");
												
				out.println("m_remark_fol='<TD WIDTH=\"15%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOL_REMARK_GUR'+lineno+' maxlength=\"100\" size=\"10\" value=\"'+data_vec[i+4]+'\" onBlur=\"check_remark_gur('+lineno+')\"></td>';");
				
				out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_CODE_GUR'+lineno+'	VALUE=\"'+data_vec[i]+'\">'+");
				out.println("             '<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DESC_GUR'+lineno+'	VALUE=\"'+data_vec[i+1].replace(\"$\",\"&\")+'\">'+");
				out.println("             '<INPUT TYPE=\"Hidden\" NAME=hid_gur_code'+lineno+'	VALUE='+array_gur_code[row_no]+'>';");
																
					out.println("if(j>0 && j%2==1){");
      
								//out.println("m_writedata='<TR class=\"tr_input1\">'+m_code+m_description+m_chk_status+m_remark+m_chk_status_fol+m_remark_fol+'</TR>'+m_hid_input;"); 
								out.println("m_writedata='<TR class=\"tr_input1\">'+m_code+m_description+'</TR>'+m_hid_input;"); 
								out.println("	}");
								out.println("	else{");
      
								out.println("m_writedata='<TR class=\"tr_input\">'+m_code+m_description+'</TR>'+m_hid_input;"); 
								//out.println("m_writedata='<TR class=\"tr_input\">'+m_code+m_description+m_chk_status+m_remark+m_chk_status_fol+m_remark_fol+'</TR>'+m_hid_input;"); 
								out.println("	}");
			
						
	  			  
						
				out.println("m_table_gurantor.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	  out.println("m_writedata+'</table>';");

				
				out.println("lineno=lineno+1;");
			  out.println("arr_size=arr_size+1;");
				out.println("i=i+6;");
				out.println("j=j+1;");

				//out.println("get_doc();}");
				out.println("}"); 
				
				out.println("}"); 
				
				//==if credit process=======================
				out.println("else if(document.Form1.hid_scr_approv.value==\"Y\"){");
				
			  out.println("count=0");
				
			  out.println("while(i<data_vec.length){");
				
				out.println("b_state=0");
				
				out.println("m_code='<TD WIDTH=\"15%\" align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"show_doc_details('+[i]+')\" ><u>'+data_vec[i]+'</u></td>';");
				out.println("m_description='<TD WIDTH=\"25%\" align=\"left\">'+data_vec[i+1].replace(\"$\",\"&\")+'</TD>';");
				
													
				out.println("					if(data_vec[i+3]==\"Y\" ) ");
				out.println("					{ ");
				out.println("m_chk_status='<TD WIDTH=\"10%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_GUR'+lineno+' value=\"on\" checked onClick=\"Val_Change_Gur('+lineno+')\" ></TD>';");
				out.println("m_chk_status_not_app='<TD WIDTH=\"10%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_GUR_NOT_APP'+lineno+' value=\"off\" onClick=\"Val_Change_Gur_Not_app('+lineno+')\"  ></TD>';");
				
				out.println("					} ");
				
				
				out.println("					else if(data_vec[i+3]==\"A\" ) ");
				out.println("					{ ");
				out.println("m_chk_status='<TD WIDTH=\"10%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_GUR'+lineno+' value=\"off\" onClick=\"Val_Change_Gur('+lineno+')\"  ></TD>';");
				out.println("m_chk_status_not_app='<TD WIDTH=\"10%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_GUR_NOT_APP'+lineno+' value=\"on\" checked onClick=\"Val_Change_Gur_Not_app('+lineno+')\"  ></TD>';");
				out.println("b_state=1;");
				
				out.println("					} ");
				
				out.println("					else  ");
				out.println("					{ ");
				out.println("m_chk_status='<TD WIDTH=\"10%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_GUR'+lineno+' value=\"off\" onClick=\"Val_Change_Gur('+lineno+')\"  ></TD>';");
				out.println("m_chk_status_not_app='<TD WIDTH=\"10%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_GUR_NOT_APP'+lineno+' value=\"off\" onClick=\"Val_Change_Gur_Not_app('+lineno+')\"  ></TD>';");
				out.println("					} ");

							
				out.println("m_remark='<TD WIDTH=\"15%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_REMARK_GUR'+lineno+' maxlength=\"100\" size=\"10\" value=\"'+data_vec[i+2]+'\"></TD>';");
				
				out.println("					if(data_vec[i+5]==\"-\") ");
				out.println("					{ ");
				out.println("m_chk_status_fol='<TD WIDTH=\"10%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_FOLLOWUP_GUR'+lineno+' maxlength=\"50\"  size=\"10\" onClick=\"Val_Change_Fol_Gur('+lineno+')\" value=\"off\"></TD>';");
				out.println("					} ");
				
				out.println("					else ");
				out.println("					{ ");
				out.println("m_chk_status_fol='<TD WIDTH=\"10%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_FOLLOWUP_GUR'+lineno+' maxlength=\"50\"  size=\"10\" onClick=\"Val_Change_Fol_Gur('+lineno+')\" value=\"on\" checked ></TD>';");
				out.println("					} ");
												
				out.println("m_remark_fol='<TD WIDTH=\"15%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOL_REMARK_GUR'+lineno+' maxlength=\"100\" size=\"10\" value=\"'+data_vec[i+4]+'\" onBlur=\"check_remark_gur('+lineno+')\"></td>';");
				
				out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_CODE_GUR'+lineno+'	VALUE=\"'+data_vec[i]+'\">'+");
				out.println("             '<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DESC_GUR'+lineno+'	VALUE=\"'+data_vec[i+1].replace(\"$\",\"&\")+'\">'+");
				out.println("             '<INPUT TYPE=\"Hidden\" NAME=hid_gur_code'+lineno+'	VALUE='+array_gur_code[row_no]+'>';");
																
			
				    out.println("if(j>0 && j%2==1){");
				
				    out.println("if(count==0 && b_state==1){");
					 	out.println("m_error='<TR STYLE=\"{color:red;}\"><TD WIDTH=\"100%\"><b>System Exception</TD></TR>';");			
															
						out.println("m_table_gurantor.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
      		  out.println("m_error+'</table>';");
						 
						out.println("}");
																		
						out.println("if(b_state==1){");	
						out.println("m_writedata='<TR class=\"tr_input1\" STYLE=\"{color:red;}\">'+m_code+m_description+'</TR>'+m_hid_input;"); 
						//out.println("m_writedata='<TR class=\"tr_input1\" STYLE=\"{color:red;}\">'+m_code+m_description+m_chk_status+m_chk_status_not_app+m_remark+m_chk_status_fol+m_remark_fol+'</TR>'+m_hid_input;"); 
     									
						out.println("m_table_gurantor.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
      		  out.println("m_writedata+'</table>';");
						
 		 				out.println("}");
						out.println("else");
						out.println("{");
						out.println("m_writedata='<TR class=\"tr_input1\">'+m_code+m_description+'</TR>'+m_hid_input;");
						//out.println("m_writedata='<TR class=\"tr_input1\">'+m_code+m_description+m_chk_status+m_chk_status_not_app+m_remark+m_chk_status_fol+m_remark_fol+'</TR>'+m_hid_input;"); 
     		
				
				    out.println("m_table_gurantor.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+");
      		  out.println("m_writedata+'</table>';");
						
						out.println("}");
						
						out.println("	}");
						out.println("	else {");
						
						out.println("if(count==0 && b_state==1){");
					 	out.println("m_error='<TR STYLE=\"{color:red;}\"><TD WIDTH=\"100%\"><b>System Exception</TD></TR>';");			
															
						out.println("m_table_gurantor.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
      		  out.println("m_error+'</table>';");
						 
						out.println("}");
						
						out.println("if(b_state==1){");	
						out.println("m_writedata='<TR class=\"tr_input\" STYLE=\"{color:red;}\">'+m_code+m_description+'</TR>'+m_hid_input;"); 
						//out.println("m_writedata='<TR class=\"tr_input\" STYLE=\"{color:red;}\">'+m_code+m_description+m_chk_status+m_chk_status_not_app+m_remark+m_chk_status_fol+m_remark_fol+'</TR>'+m_hid_input;"); 
     											
						out.println("m_table_gurantor.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
      		  out.println("m_writedata+'</table>';");
						
 		 				out.println("}");
						out.println("else");
						out.println("{");
						out.println("m_writedata='<TR class=\"tr_input\">'+m_code+m_description+'</TR>'+m_hid_input;"); 
						//out.println("m_writedata='<TR class=\"tr_input\">'+m_code+m_description+m_chk_status+m_chk_status_not_app+m_remark+m_chk_status_fol+m_remark_fol+'</TR>'+m_hid_input;"); 
     					
				    out.println("m_table_gurantor.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+");
      		  out.println("m_writedata+'</table>';");
						
						out.println("}");						
						
						out.println("	}");

				out.println("count=count+1;");
				out.println("lineno=lineno+1;");
			  out.println("arr_size=arr_size+1;");
				out.println("i=i+6;");
				out.println("j=j+1;");

				
				out.println("}"); 
				
				out.println("}"); 
				
				
				out.println("row_no=row_no+1;"); 		
			
			 // out.println("get_doc();");
							
			  out.println("if(b_flag_on==1 && row_no==array_gur_code_size ){"); //
			 // out.println("alert('here we go');");
			  out.println("get_Invoice_numbers();");
        
			  out.println("}"); 
						
	      out.println("}"); 
			
			//-----------------------------------------------------------------------------
			
			
				
			//-----------------------------------------------------------------------------
			
			out.println("function assign_invoice_Documents(data_vec){");
			
	      out.println("var i=0;");
  			out.println("var j=0;");
				
				out.println("				new_data_vec_inv=data_vec;");
							
				out.println("header_inv_doc();");
				
				out.println("if(document.Form1.hid_scr_approv.value==\"N\"){");
			
			  out.println("while(i<data_vec.length){");
				
				out.println("m_code='<TD WIDTH=\"15%\" align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"show_doc_inv_details('+[i]+')\"><u>'+data_vec[i]+'</u></td>';");
				out.println("m_description='<TD WIDTH=\"30%\" align=\"left\">'+data_vec[i+1].replace(\"$\",\"&\")+'</TD>';");
				
													
				out.println("					if(data_vec[i+3]==\"Y\" ) ");
				out.println("					{ ");
				out.println("m_chk_status='<TD WIDTH=\"10%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_INV_DOC'+lineno_inv_doc+' value=\"on\" checked onClick=\"Val_Change_Inv_Doc('+lineno_inv_doc+')\" ></TD>';");
				out.println("					} ");
				
				out.println("					else if(data_vec[i+3]==\"-\"  || data_vec[i+3]==\"N\" || data_vec[i+3]==\"A\" ) ");
				out.println("					{ ");
				out.println("m_chk_status='<TD WIDTH=\"10%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_INV_DOC'+lineno_inv_doc+' value=\"off\" onClick=\"Val_Change_Inv_Doc('+lineno_inv_doc+')\"  ></TD>';");
				out.println("					} ");
											
				
				out.println("m_remark='<TD WIDTH=\"15%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_REMARK_INV_DOC'+lineno_inv_doc+' maxlength=\"100\" size=\"10\" value=\"'+data_vec[i+2]+'\"></TD>';");
				
				out.println("					if(data_vec[i+5]==\"-\") ");
				out.println("					{ ");
				out.println("m_chk_status_fol='<TD WIDTH=\"15%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_FOLLOWUP_INV_DOC'+lineno_inv_doc+' maxlength=\"50\"  size=\"10\" onClick=\"Val_Change_Fol_Inv_Doc('+lineno_inv_doc+')\" value=\"off\"></TD>';");
				out.println("					} ");
				
				out.println("					else ");
				out.println("					{ ");
				out.println("m_chk_status_fol='<TD WIDTH=\"15%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_FOLLOWUP_INV_DOC'+lineno_inv_doc+' maxlength=\"50\"  size=\"10\" onClick=\"Val_Change_Fol_Inv_Doc('+lineno_inv_doc+')\" value=\"on\" checked ></TD>';");
				out.println("					} ");
				
				
								
				out.println("m_remark_fol='<TD WIDTH=\"15%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOL_REMARK_INV_DOC'+lineno_inv_doc+' maxlength=\"100\" size=\"10\" value=\"'+data_vec[i+4]+'\" onBlur=\"check_remark_inv_doc('+lineno_inv_doc+')\"></td>';");
				
				out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_CODE_INV_DOC'+lineno_inv_doc+'	VALUE=\"'+data_vec[i]+'\">'+");
				out.println("             '<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DESC_INV_DOC'+lineno_inv_doc+'	VALUE=\"'+data_vec[i+1].replace(\"$\",\"&\")+'\">'+");
				out.println("             '<INPUT TYPE=\"Hidden\" NAME=hid_invoice_no'+lineno_inv_doc+'	VALUE='+array_invoice[row_invoice_no]+'>';");
						
				
				
												
					out.println("if(j>0 && j%2==1){");
      
					out.println("m_writedata='<TR class=\"tr_input1\">'+m_code+m_description+'</TR>'+m_hid_input;"); 
								//out.println("m_writedata='<TR class=\"tr_input1\">'+m_code+m_description+m_chk_status+m_remark+m_chk_status_fol+m_remark_fol+'</TR>'+m_hid_input;"); 
								out.println("	}");
								
								out.println("	else{");
								out.println("m_writedata='<TR class=\"tr_input\">'+m_code+m_description+'</TR>'+m_hid_input;");
      					//out.println("m_writedata='<TR class=\"tr_input\">'+m_code+m_description+m_chk_status+m_remark+m_chk_status_fol+m_remark_fol+'</TR>'+m_hid_input;"); 
								out.println("	}");
									
	  			  
						
				out.println("m_asset.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	  out.println("m_writedata+'</table>';");
				
				out.println("lineno_inv_doc=lineno_inv_doc+1;");
			  out.println("arr_size_inv_doc=arr_size_inv_doc+1;");
				out.println("i=i+6;");
				out.println("j=j+1;");
			  out.println("}"); 
				
				out.println("}"); 
				
				
				
				out.println("else if(document.Form1.hid_scr_approv.value==\"Y\"){");
				
				out.println("count=0;");
			
			  out.println("while(i<data_vec.length){");
				out.println("b_state=0;");
				out.println("m_code='<TD WIDTH=\"15%\" align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"show_doc_inv_details('+[i]+')\"><u>'+data_vec[i]+'</u></td>';");
				out.println("m_description='<TD WIDTH=\"25%\" align=\"left\">'+data_vec[i+1].replace(\"$\",\"&\")+'</TD>';");
				
													
				out.println("					if(data_vec[i+3]==\"Y\" ) ");
				out.println("					{ ");
				out.println("m_chk_status='<TD WIDTH=\"10%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_INV_DOC'+lineno_inv_doc+' value=\"on\" checked onClick=\"Val_Change_Inv_Doc('+lineno_inv_doc+')\" ></TD>';");
				out.println("m_chk_status_not_app='<TD WIDTH=\"10%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_INV_DOC_NOT_APP'+lineno_inv_doc+' value=\"off\" onClick=\"Val_Inv_Doc_Not_App('+lineno_inv_doc+')\"  ></TD>';");
				out.println("					} ");
				
				//out.println("					else if(data_vec[i+3]==\"-\"  || data_vec[i+3]==\"N\") ");
				///out.println("					{ ");
				//out.println("m_chk_status='<TD WIDTH=\"10%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_INV_DOC'+lineno_inv_doc+' value=\"off\" onClick=\"Val_Change_Inv_Doc('+lineno_inv_doc+')\"  ></TD>';");
				//out.println("					} ");
								
				out.println("				else	if(data_vec[i+3]==\"A\" ) ");
				out.println("					{ ");
				out.println("m_chk_status='<TD WIDTH=\"10%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_INV_DOC'+lineno_inv_doc+' value=\"off\" onClick=\"Val_Change_Inv_Doc('+lineno_inv_doc+')\"  ></TD>';");
				out.println("m_chk_status_not_app='<TD WIDTH=\"10%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_INV_DOC_NOT_APP'+lineno_inv_doc+' value=\"on\" checked onClick=\"Val_Inv_Doc_Not_App('+lineno_inv_doc+')\"  ></TD>';");
				out.println("b_state=1;");
				out.println("					} ");
				
				out.println("					else ");
				out.println("					{ ");
				out.println("m_chk_status='<TD WIDTH=\"10%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_INV_DOC'+lineno_inv_doc+' value=\"off\" onClick=\"Val_Change_Inv_Doc('+lineno_inv_doc+')\"  ></TD>';");
				out.println("m_chk_status_not_app='<TD WIDTH=\"10%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_INV_DOC_NOT_APP'+lineno_inv_doc+' value=\"off\" onClick=\"Val_Inv_Doc_Not_App('+lineno_inv_doc+')\"  ></TD>';");
				out.println("					} ");

							
				out.println("m_remark='<TD WIDTH=\"15%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_REMARK_INV_DOC'+lineno_inv_doc+' maxlength=\"100\" size=\"10\" value=\"'+data_vec[i+2]+'\"></TD>';");
				
				out.println("					if(data_vec[i+5]==\"-\") ");
				out.println("					{ ");
				out.println("m_chk_status_fol='<TD WIDTH=\"10%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_FOLLOWUP_INV_DOC'+lineno_inv_doc+' maxlength=\"50\"  size=\"10\" onClick=\"Val_Change_Fol_Inv_Doc('+lineno_inv_doc+')\" value=\"off\"></TD>';");
				out.println("					} ");
				
				out.println("					else ");
				out.println("					{ ");
				out.println("m_chk_status_fol='<TD WIDTH=\"10%\" align=\"left\"><input  type=\"checkbox\" name=CHK_STATUS_FOLLOWUP_INV_DOC'+lineno_inv_doc+' maxlength=\"50\"  size=\"10\" onClick=\"Val_Change_Fol_Inv_Doc('+lineno_inv_doc+')\" value=\"on\" checked ></TD>';");
				out.println("					} ");
				
				
								
				out.println("m_remark_fol='<TD WIDTH=\"15%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOL_REMARK_INV_DOC'+lineno_inv_doc+' maxlength=\"100\" size=\"10\" value=\"'+data_vec[i+4]+'\" onBlur=\"check_remark_inv_doc('+lineno_inv_doc+')\"></td>';");
				
				out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_CODE_INV_DOC'+lineno_inv_doc+'	VALUE=\"'+data_vec[i]+'\">'+");
				out.println("             '<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DESC_INV_DOC'+lineno_inv_doc+'	VALUE=\"'+data_vec[i+1].replace(\"$\",\"&\")+'\">'+");
				out.println("             '<INPUT TYPE=\"Hidden\" NAME=hid_invoice_no'+lineno_inv_doc+'	VALUE='+array_invoice[row_invoice_no]+'>';");
												

				
				   out.println("if(j>0 && j%2==1){");
				
				    out.println("if(count==0 && b_state==1){");
					 	out.println("m_error='<TR STYLE=\"{color:red;}\"><TD WIDTH=\"100%\"><b>System Exception</TD></TR>';");			
															
						out.println("m_asset.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
      		  out.println("m_error+'</table>';");
						 
						out.println("}");
						
												
						out.println("if(b_state==1){");	
						out.println("m_writedata='<TR class=\"tr_input1\" STYLE=\"{color:red;}\">'+m_code+m_description+'</TR>'+m_hid_input;"); 
						//out.println("m_writedata='<TR class=\"tr_input1\" STYLE=\"{color:red;}\">'+m_code+m_description+m_chk_status+m_chk_status_not_app+m_remark+m_chk_status_fol+m_remark_fol+'</TR>'+m_hid_input;"); 
     											
						out.println("m_asset.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
      		  out.println("m_writedata+'</table>';");
						
 		 				out.println("}");
						out.println("else");
						out.println("{");
						out.println("m_writedata='<TR class=\"tr_input1\">'+m_code+m_description+'</TR>'+m_hid_input;"); 
						//out.println("m_writedata='<TR class=\"tr_input1\">'+m_code+m_description+m_chk_status+m_chk_status_not_app+m_remark+m_chk_status_fol+m_remark_fol+'</TR>'+m_hid_input;"); 
     				
				    out.println("m_asset.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+");
      		  out.println("m_writedata+'</table>';");
						
						out.println("}");
						
						out.println("	}");
						out.println("	else {");
						
						out.println("if(count==0 && b_state==1){");
					 	out.println("m_error='<TR STYLE=\"{color:red;}\"><TD WIDTH=\"100%\"><b>System Exception</TD></TR>';");			
															
						out.println("m_asset.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
      		  out.println("m_error+'</table>';");
						 
						out.println("}");
						
						out.println("if(b_state==1){");	
						out.println("m_writedata='<TR class=\"tr_input\" STYLE=\"{color:red;}\">'+m_code+m_description+'</TR>'+m_hid_input;"); 
						//out.println("m_writedata='<TR class=\"tr_input\" STYLE=\"{color:red;}\">'+m_code+m_description+m_chk_status+m_chk_status_not_app+m_remark+m_chk_status_fol+m_remark_fol+'</TR>'+m_hid_input;"); 
     										
						out.println("m_asset.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
      		  out.println("m_writedata+'</table>';");
						
 		 				out.println("}");
						out.println("else");
						out.println("{");
						out.println("m_writedata='<TR class=\"tr_input\">'+m_code+m_description+'</TR>'+m_hid_input;"); 
						//out.println("m_writedata='<TR class=\"tr_input\">'+m_code+m_description+m_chk_status+m_chk_status_not_app+m_remark+m_chk_status_fol+m_remark_fol+'</TR>'+m_hid_input;"); 
     			
			      out.println("m_asset.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+");
      		  out.println("m_writedata+'</table>';");
						
						out.println("}");						
						
						out.println("	}");


				out.println("count=count+1;");
				out.println("lineno_inv_doc=lineno_inv_doc+1;");
			  out.println("arr_size_inv_doc=arr_size_inv_doc+1;");
				out.println("i=i+6;");
				out.println("j=j+1;");
			  out.println("}"); 
				
				out.println("}"); 
				
				
				
				
				
				out.println("row_invoice_no=row_invoice_no+6;"); 			
			//	out.println("row_asset_no=row_asset_no+5;"); 			

       // out.println("alert('row asset no'+row_asset_no);");
								
	    out.println("}"); 
			
			
								
					
			out.println("function get_vector(data_vec) {");

			//out.println("   alert(document.Form1.hid_chk_status.value + ' ---- ' + data_vec.length + ' ------ ' + document.Form1.hid_chk_status.value); ");
			
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M10'){"); //data_vec.length>0 && 
			out.println("     display_gurantor_details(data_vec);");
			out.println("			}");
			
			out.println("			else if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M10'){");
			out.println("     get_Invoice_numbers();");
			out.println("			}");
			
			out.println("			else if(document.Form1.hid_chk_status.value=='M5'){"); //data_vec.length>0 &&
		//	out.println("alert('test val'+b_flag_on);");
			out.println("     display_data_gurantor(data_vec);");
			out.println("			}");

			
			out.println("			else if(document.Form1.hid_chk_status.value=='M_INVOICE'){"); //data_vec.length>0 &&
			out.println("     assign_invoice_numbers(data_vec);");
			out.println("			}");
			
			out.println("			else if(document.Form1.hid_chk_status.value=='M_INV_DOC'){"); //data_vec.length>0 &&
			out.println("     assign_invoice_Documents(data_vec);");
			out.println("			}");
			
			out.println("			else if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M_ASSET'){");
			out.println("     assign_asset_details(data_vec);");
			out.println("			}");
			
			
			
			

		
		 out.println("}");
			
			
			out.println("function display_gurantor_details(data_vec){");
			out.println("var i=0;");
			out.println("var j=0;");
			out.println("array_gur_code_size=0;");
			
			
			out.println("while(i<data_vec.length){");
			
			out.println("array_gur_code[j]=data_vec[i];");
			out.println("array_gur_name[j]=data_vec[i+1];");
			
			out.println("j=j+1;");
			out.println("i=i+2;");
			
			out.println("array_gur_code_size=array_gur_code_size+1;");
			
			out.println("}");
		//	out.println("alert('size'+array_gur_code_size);");
			
			out.println("row_count=0;");
			out.println("b_flag_on=0;");
			out.println("row_no=0;");
			out.println("lineno=0;");
			out.println("arr_size=0;");
			
			out.println("get_doc();");
				
			out.println("}");
			
			
				
			out.println("function assign_invoice_numbers(data_vec){");
			
			out.println("var i=0;");
			out.println("array_invoice_size=0;");
			
		//	out.println("var j=0;");
			//out.println("array_invoice='';");
			out.println("while(i<data_vec.length){");
			
			
			out.println("array_invoice[i]=data_vec[i];");
			//out.println("array_gur_name[j]=data_vec[i+1];");
			
		//	out.println("j=j+1;");
			out.println("i=i+1;");
			out.println("array_invoice_size=array_invoice_size+1;");
			
			out.println("}");
		
		out.println("lineno_inv_doc=0;");
		out.println("arr_size_inv_doc=0;");
		out.println("row_invoice_no=0;");
		out.println("invoice_no_count=0;");
		
		
		

		
		
		//out.println("alert('inv size'+array_invoice_size);");
			
			
		 out.println("get_invoice_doc();");
		
		//	out.println("get_Asset_details();");	
				
			out.println("}");
			
					
						
			
			out.println("function get_doc(){");
			
					 
		//	out.println("alert('test length'+array_gur_code.length);");
			
			//out.println("alert('test count'+row_count);");	
			
			out.println("while(row_count<array_gur_code_size){");
			out.println("assignState('M5');");
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_Documents_client&data_val_client_code=\"+array_gur_code[row_count]+\"&data_val_txt_type="+m_txt_type+"&Hid_Scr_name="+m_Hid_scr_name+"&data_val_app_no="+m_app_no+"&ac_status=Y\";");
			
			
		  //out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			
			out.println("row_count=row_count+1;");
			out.println("}");
			
			out.println("if(row_count==array_gur_code_size){");
			out.println("b_flag_on=1;");
			//out.println("alert('test count'+row_count);");	
			//out.println("alert('array_gur_code_size count'+array_gur_code_size);");	
			
			//out.println("alert('test count'+row_count);");	
			out.println("}");
									
			out.println("}");
			
			
			
			out.println("function get_invoice_doc(){");
			
			out.println("assignState('M_INV_DOC');"); 
			out.println("while(invoice_no_count<array_invoice_size){");
			
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_Document_req_get_invoice_doc&data_val_invoice_no=\"+array_invoice[invoice_no_count]+\"&data_val_txt_type="+m_txt_type+"&Hid_Scr_name="+m_Hid_scr_name+"&data_val_client_code="+m_client_code+"&data_val_app_no="+m_app_no+"&ac_status=Y\";");
						
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			
			out.println("invoice_no_count=invoice_no_count+6;");
			out.println("}");
		
		
									
			out.println("}");
			
						
			
			out.println("function get_Invoice_numbers(){");
			//out.println(" alert('get_Invoice_numbers'); ");
			out.println("assignState('M_INVOICE');"); 
			
			//out.println(" alert('M_INVOICE'); ");
			
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql= &&data_val_app_no="+m_app_no+"&ac_status=Y\";");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_Document_req_get_invoice&data_val_app_no="+m_app_no+"&ac_status=Y\";");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");

			out.println("}");
			
			
			
			
			
			  
					
			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Process_Document_Required_2?chksql=main_page&APP_NO="+m_app_no+"&Hid_scr_name="+m_Hid_scr_name+"&TXT_TYPE="+m_txt_type+"&CLIENT_CODE="+m_client_code+"&ac_status=Y&scr_approv="+m_scr_approv+"&hid_records="+m_hid_records+"&CORE_APP_CODE="+m_core_app_code+"';"); //&hid_records=A&scr_approv=Y'
		//	out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Process_Document_Required_2?chksql=main_page&Hid_scr_name='+document.Form1.Hid_scr_name.value+'&APP_NO='+document.Form1.TXT_APPLICATION_NO.value+'&TXT_TYPE='+document.Form1.TXT_TR_TYPE.value+'&CLIENT_CODE='+document.Form1.TXT_APPLICANT_CODE.value+'&CORE_APP_CODE='+document.Form1.TXT_CORE_APPLICANT_CODE.value+'&ac_status=Y&hid_records='+document.Form1.hid_no_rec.value;"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Process_Document_Required_2?chksql=main_page&APP_NO="+m_app_no+"&TXT_TYPE="+m_txt_type+"&CLIENT_CODE="+m_client_code+"&ac_status=Y&CORE_APP_CODE="+m_core_app_code+"';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			
			out.println("return true;"); 
		
			out.println("}"); 



			
			
			out.println("function before_submit(){ "); 
	
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		if(validate_data()){"); 
			out.println("   chk_data();");
			out.println("   if(count1==0 && count2==0 && count3==0 && count4==0  ){");
			out.println("   if(flag1==0 && flag2==0 && flag3==0 && flag4==0 ){");
			out.println("   if(flag11==0 && flag22==0 && flag33==0 && flag44==0 ){");
			out.println("   if(count11==0 && count22==0 && count33==0 && count44==0  ){");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ ");
			
			out.println("   document.Form1.hid_count_gur.value=arr_size;");//Added By Nuwan De Silva
			out.println("   document.Form1.hid_count_invoice_doc.value=arr_size_inv_doc;");//Added By Nuwan De Silva
			out.println("   document.Form1.Hid_scr_name.value='"+m_Hid_scr_name+"';");//Added By Nuwan De Silva
			out.println("   document.Form1.Hid_records.value='"+m_hid_records+"';");//Added By Nuwan De Silva
						
			out.println("		if(validate_data()){"); 
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_Save_Application_Process_Document_Required';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}");
			
			out.println("		}");
			out.println("else{");
			
			out.println("		alert(\"Please select Follow up status if the document is applicable.\");");
			
			//--modified by delanjali on 2007-08-13 for ref no : 788-----------------------------------------------------------------------------
			out.println("	if(count11==1){");
			out.println("MK_NAME.innerHTML='<table border=0><tr style=\"{color:red}\"><td><b>Please select Documents</td></tr></table>';"); 
			out.println("} "); 
			
			out.println("	if(count22==1){");
			out.println("MK_NAME1.innerHTML='<table border=0><tr style=\"{color:red}\"><td><b>Please select Documents</td></tr></table>';"); 
			out.println("} "); 
			
			out.println("	if(count33==1){");
			out.println("add_label_ba();");
				
			out.println("DIV_TXT_BA.style.color='red';");
			out.println("} "); 
						
			out.println("	if(count44==1){");
			out.println("add_label_ba_1();");
			out.println("DIV_TXT_BA1.style.color='red';");
			out.println("} "); 
			out.println("} "); 
			//------------------------------------------------------------------------------------------------------------------------------------
			
			
			out.println("		}");
			out.println("else{");
			// Modified By Samitha Kulatilaka On 2009-10-19
			out.println("		alert(\"Please enter Follow up remarks for documents which have to be Followed up.\");");
			out.println("} "); 


			
			out.println("		}");
			out.println("else{");
			// Modified By Samitha Kulatilaka On 2009-10-19
			out.println("alert(\"Please enter Follow up remarks for documents which have to be Followed up.\");");
			out.println("} "); 

			
			out.println("		}");
			out.println("else{");
			out.println("alert(\"Please select either 'status' or 'followup status'\");");
			out.println("} "); 
			
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			
			out.println("} "); 
			
			
			
			 out.println("function chk_data(){");
				
				out.println("flag1=0; "); 
				out.println("flag2=0; "); 
			  out.println("flag3=0; "); 
				out.println("flag4=0; "); 
				
				out.println("flag11=0; "); 
				out.println("count11=0; "); 
				out.println("flag22=0; "); 
				out.println("count22=0; "); 
				out.println("flag33=0; "); 
				out.println("count33=0; "); 
				out.println("flag44=0; "); 
				out.println("count44=0; "); 

				
				out.println("count1=0; "); 
				out.println("count2=0; "); 
			  out.println("count3=0; "); 
				out.println("count4=0; "); 
				
				//This Part Validate The Client Documents
				
			out.println("if(document.Form1.hid_scr_approv.value==\"N\"){");	
				
			out.println("for(var i=0;i<document.Form1.hid_no_of_clinet_doc.value;i++){");
			
			out.println("m_chk_status_fol=\"CHK_STATUS_FOLLOWUP\"+i");
			out.println("m_remark_fol=\"TXT_FOL_REMARK\"+i");
			out.println("m_chk_status=\"CHK_STATUS\"+i");
						
		
			out.println("if(document.Form1.elements[m_chk_status_fol].checked==true && (document.Form1.elements[m_remark_fol].value=='-' || document.Form1.elements[m_remark_fol].value=='')){");
			out.println("flag1=1; "); 
			out.println("} "); 
			
			out.println("else if(document.Form1.elements[m_chk_status_fol].checked==false && document.Form1.elements[m_chk_status].checked==false){");
			out.println("count1=1; "); 
			out.println("} "); 

			out.println("} "); 
			
			out.println("} "); 
			
			out.println("else if(document.Form1.hid_scr_approv.value==\"Y\"){");	
			
					
			out.println("for(var i=0;i<document.Form1.hid_no_of_clinet_doc.value;i++){");
			
			out.println("m_chk_status_fol=\"CHK_STATUS_FOLLOWUP\"+i");
			out.println("m_remark_fol=\"TXT_FOL_REMARK\"+i");
			out.println("m_chk_status=\"CHK_STATUS\"+i");
			out.println("m_chk_not_applicable=\"CHK_STATUS_NOT_APP\"+i");
					
			out.println("if(document.Form1.elements[m_chk_status_fol].checked==true && (document.Form1.elements[m_remark_fol].value=='-' || document.Form1.elements[m_remark_fol].value=='')){");
			out.println("flag11=1; "); 
			out.println("} "); 
			
			out.println("else if(document.Form1.elements[m_chk_status].checked==false && document.Form1.elements[m_chk_not_applicable].checked==false && document.Form1.elements[m_chk_status_fol].checked==false){");
			out.println("count11=1; "); 
			out.println("} "); 
			
			out.println("} "); 
			
			
			out.println("} "); 
			
			
			//This Part Validate The Core Applicant documents
			
			out.println("if(document.Form1.hid_scr_approv.value==\"N\"){");	
			
			out.println("for(i=0;i<document.Form1.hid_no_of_core_doc.value;i++){");
			
			out.println("m_chk_status_fol_core=\"CHK_STATUS_FOLLOWUP_CORE\"+i");
			out.println("m_remark_fol_core=\"TXT_FOL_REMARK_CORE\"+i");
			out.println("m_chk_status=\"CHK_STATUS_CORE\"+i");
			
			out.println("if(document.Form1.elements[m_chk_status_fol_core].checked==true && (document.Form1.elements[m_remark_fol_core].value=='-' || document.Form1.elements[m_remark_fol_core].value=='')){");
			//		out.println("alert('b');");
			out.println("flag2=1; "); 
			out.println("} "); 
			
			out.println("else if(document.Form1.elements[m_chk_status_fol_core].checked==false && document.Form1.elements[m_chk_status].checked==false){");
			out.println("count2=1; "); 
			out.println("} "); 

			
			out.println("} "); 
			
			out.println("} "); 
			
			out.println("else if(document.Form1.hid_scr_approv.value==\"Y\"){");	
			
			
			out.println("for(var i=0;i<document.Form1.hid_no_of_core_doc.value;i++){");
			
			out.println("m_chk_status_fol=\"CHK_STATUS_FOLLOWUP_CORE\"+i");
			out.println("m_remark_fol=\"TXT_FOL_REMARK_CORE\"+i");
			out.println("m_chk_status=\"CHK_STATUS_CORE\"+i");
			out.println("m_chk_not_applicable=\"CHK_STATUS_CORE_NOT_APP\"+i");
					
			out.println("if(document.Form1.elements[m_chk_status_fol].checked==true && (document.Form1.elements[m_remark_fol].value=='-' || document.Form1.elements[m_remark_fol].value=='')){");
			out.println("flag22=1; "); 
			out.println("} "); 
			
			out.println("else if(document.Form1.elements[m_chk_status].checked==false && document.Form1.elements[m_chk_not_applicable].checked==false && document.Form1.elements[m_chk_status_fol].checked==false){");
			out.println("count22=1; "); 
			out.println("} "); 
			
			out.println("} "); 
			
			
			out.println("} "); 
			
			
			
			
			//This Part Validate The Gurantor Documents
			
			out.println("if(document.Form1.hid_scr_approv.value==\"N\"){");	
			
			out.println("for(i=0;i<arr_size;i++){");
			
			out.println("m_chk_status_fol_gur=\"CHK_STATUS_FOLLOWUP_GUR\"+i");
			out.println("m_remark_fol_gur=\"TXT_FOL_REMARK_GUR\"+i");
			out.println("m_chk_status=\"CHK_STATUS_GUR\"+i");
			
			out.println("if(document.Form1.elements[m_chk_status_fol_gur].checked==true && (document.Form1.elements[m_remark_fol_gur].value=='-' || document.Form1.elements[m_remark_fol_gur].value=='')){");
			out.println("flag3=1; "); 
			out.println("} "); 
			
			out.println("else if(document.Form1.elements[m_chk_status_fol_gur].checked==false && document.Form1.elements[m_chk_status].checked==false){");
			out.println("count3=1; "); 
			out.println("} "); 
			
			out.println("} "); 
			
			out.println("} "); 
			
			out.println("else if(document.Form1.hid_scr_approv.value==\"Y\"){");	
			
					
			out.println("for(var i=0;i<arr_size;i++){");
			
		  out.println("m_chk_status_fol=\"CHK_STATUS_FOLLOWUP_GUR\"+i");
			out.println("m_remark_fol=\"TXT_FOL_REMARK_GUR\"+i");
			out.println("m_chk_status=\"CHK_STATUS_GUR\"+i");
			out.println("m_chk_not_applicable=\"CHK_STATUS_GUR_NOT_APP\"+i");
					
			out.println("if(document.Form1.elements[m_chk_status_fol].checked==true && (document.Form1.elements[m_remark_fol].value=='-' || document.Form1.elements[m_remark_fol].value=='')){");
			out.println("flag33=1; "); 
			out.println("} "); 
			
			out.println("else if(document.Form1.elements[m_chk_status].checked==false && document.Form1.elements[m_chk_not_applicable].checked==false && document.Form1.elements[m_chk_status_fol].checked==false){");
			out.println("count33=1; "); 
			out.println("} "); 
			
			out.println("} "); 
			
			
			out.println("} "); 
			
			
			
			//This Part Validate The Invoice Documents
			
			out.println("if(document.Form1.hid_scr_approv.value==\"N\"){");	
				
			out.println("for(i=0;i<arr_size_inv_doc;i++){");
			
			out.println("m_chk_status_fol_inv_doc=\"CHK_STATUS_FOLLOWUP_INV_DOC\"+i");
			out.println("m_remark_fol_inv_doc=\"TXT_FOL_REMARK_INV_DOC\"+i");
			out.println("m_chk_status=\"CHK_STATUS_INV_DOC\"+i");
			
			out.println("if(document.Form1.elements[m_chk_status_fol_inv_doc].checked==true && (document.Form1.elements[m_remark_fol_inv_doc].value=='-' || document.Form1.elements[m_remark_fol_inv_doc].value=='')){");
			out.println("flag4=1; "); 
			out.println("} "); 
			
			out.println("else if(document.Form1.elements[m_chk_status_fol_inv_doc].checked==false && document.Form1.elements[m_chk_status].checked==false){");
			out.println("count4=1; "); 
			out.println("} "); 
			
			out.println("} "); 
			
			
				out.println("} "); 
			
			out.println("else if(document.Form1.hid_scr_approv.value==\"Y\"){");	
			
		
			out.println("for(var i=0;i<arr_size_inv_doc;i++){");
			
		  out.println("m_chk_status_fol=\"CHK_STATUS_FOLLOWUP_INV_DOC\"+i");
			out.println("m_remark_fol=\"TXT_FOL_REMARK_INV_DOC\"+i");
			out.println("m_chk_status=\"CHK_STATUS_INV_DOC\"+i");
			out.println("m_chk_not_applicable=\"CHK_STATUS_INV_DOC_NOT_APP\"+i");
					
			out.println("if(document.Form1.elements[m_chk_status_fol].checked==true && (document.Form1.elements[m_remark_fol].value=='-' || document.Form1.elements[m_remark_fol].value=='')){");
			out.println("flag44=1; "); 
			out.println("} "); 
			
			out.println("else if(document.Form1.elements[m_chk_status].checked==false && document.Form1.elements[m_chk_not_applicable].checked==false && document.Form1.elements[m_chk_status_fol].checked==false){");
			out.println("count44=1; "); 
			out.println("} "); 
			
			out.println("} "); 
			
			
			out.println("} "); 
			
			
			


      out.println("} "); 
			
			
			
			
		
								
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MK_Application_Process_Document_req\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MK_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MK_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
					
		
					
					out.println("function load_roll_value(m_val){"); 
					out.println("help_box.innerHTML=\" Application Process - Documents Required- \"+m_val;"); 
					out.println("}"); 
					out.println(""); 
		
					out.println("function load_roll_out_value(){");
					out.println("help_box.innerHTML=\" Application Process - Documents Required - \"+document.Form1.hid_status.value;"); 
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
					
					
					
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,txtObj) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
		//	out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MK_Help_Servlet?class_in=\"+client_name+\"AF_MK_help_select\"+"); 
		//	out.println("    \"&Sql_in=\"+Sql+\"&Start_in=\"+Start+"); 
		//	out.println("    \"&End_in=\"+End+\"&Crit_In=\"+Crit+"); 
	//		out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("    clear_fields(txtObj); ");
			out.println("		} else ");
			
			
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
			
			  out.println("if(oBj.valout[0]=='Next')  {");
				out.println("Next(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount,txtObj);");
				out.println("}");
				out.println("else if  (oBj.valout[0]=='Prev') {");
				out.println("Prev(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount,txtObj);");
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
			out.println("		Next(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount,txtObj);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount,txtObj);"); 
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
					
											
					out.println("function clear_fields(txtObj){"); 
					out.println("   document.Form1.elements[txtObj].value=\"\"");
					out.println("}		"); 					
					
					
					out.println("function help_button_1(rowNo) {"); 
					
					out.println("m_make=\"TXT_MAKE\"+rowNo;");
					out.println("m_model=\"TXT_MODEL\"+rowNo;");
					out.println("    document.Form1.hid_help_type.value=\"1\";"); 
					out.println("    document.Form1.hid_row_no.value=rowNo;"); 
					
					out.println("   Sql =\"m_help_TXT_MAKE\";"); 
					out.println("    Crit =document.Form1.elements[m_make].value+\"@\"+document.Form1.elements[m_model].value+\"@Y@\";"); 
				//	out.println("    Crit = document.Form1.elements[m_make].value+\"@Y@\";"); 
				
				// out.println("alert('Crit'+Crit);");
					out.println("    HelpBox('1','10','0',Crit,Sql,'1',m_make);"); 
					out.println("}"); 
					out.println(""); 
		
					
					out.println("function help_value_assign_1(rowNo,oBj) {"); 
					out.println("m_make=\"TXT_MAKE\"+rowNo;");
								
					out.println("    document.Form1.elements[m_make].value=oBj.valout[2];"); 
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
					out.println("    HelpBox('0','10','0',Crit,Sql,'2',m_model);"); 
					out.println("}"); 
					out.println(""); 
					
					out.println("function help_value_assign_2(rowNo,oBj) {"); 
					out.println("m_model=\"TXT_MODEL\"+rowNo;");
										
					out.println("    document.Form1.elements[m_model].value=oBj.valout[2];"); 
										
					out.println("}");
					
					
					out.println("function help_button_3(rowNo) {"); 
					
					out.println("m_sub_model=\"TXT_SUB_MODEL\"+rowNo;");
					out.println("m_model=\"TXT_MODEL\"+rowNo;");
					out.println("    document.Form1.hid_help_type.value=\"3\";"); 
					out.println("    document.Form1.hid_row_no.value=rowNo;"); 
					
					out.println("   Sql =\"m_help_TXT_SUB_MODEL_sql\";"); 
					out.println("    Crit =document.Form1.elements[m_sub_model].value+\"@\"+document.Form1.elements[m_model].value+\"@Y@\";"); 
					//out.println("    Crit = document.Form1.elements[m_sub_model].value+\"@Y@\";"); 
					out.println("    HelpBox('0','10','6',Crit,Sql,'3',m_sub_model);"); 
					out.println("}"); 
					out.println(""); 
					
					out.println("function help_value_assign_3(rowNo,oBj) {"); 
					out.println("m_sub_model=\"TXT_SUB_MODEL\"+rowNo;");
										
					out.println("    document.Form1.elements[m_sub_model].value=oBj.valout[2];"); 
										
					out.println("}");
					
					
									
					out.println("function help_value_assign_4(rowNo,oBj) {"); 
					out.println("m_city=\"TXT_CITY\"+rowNo;");
										
					out.println("    document.Form1.elements[m_city].value=oBj.valout[2];"); 
										
					out.println("}");
					
									
					
					
		
					out.println("function help_update(Start,End,Hid_No,Sql,IfCount) {"); 
					out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\"){ ");
				  
					out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@Y@\";"); 
				//	out.println("    Crit =\"Y@\";"); 
				//	out.println("    Crit =document.Form1.TXT_ASSET_NO.value+\"@\"+document.Form1.hid_app_no.value+\"@Y@\";"); 
					out.println("    } ");
					//out.println("alert('crit'+Crit);");
					out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,0);"); 
					out.println("}"); 
		
					out.println("function help_update_value_assign_99(oBj) {"); 
					out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
					
					out.println("    assignState('M7');"); 
			    out.println("    makeRequest(document.Form1.TXT_APPLICATION_NO);");
			
					out.println("}"); 
					
	
			
			out.println("function check_data(row_no){")	;
			
			//out.println("alert('row'+row_no);");
			out.println("m_chk=\"CHK_STATUS\"+row_no;");
			out.println("m_chk_not=\"CHK_STATUS_NOT_APP\"+row_no;");
			//out.println("alert('m_chk'+document.Form1.elements[m_chk].value);");
			//out.println("alert('m_chk_not'+document.Form1.elements[m_chk_not].value);");
			out.println("if(document.Form1.elements[m_chk].checked==true && document.Form1.elements[m_chk_not].checked==true){");
			
			out.println("document.Form1.elements[m_chk].value='on'");
			out.println("document.Form1.elements[m_chk_not].checked=false");
			out.println("document.Form1.elements[m_chk_not].value='off'");
			
			out.println("}else if(document.Form1.elements[m_chk].checked==true && document.Form1.elements[m_chk_not].checked==false){");
			out.println("document.Form1.elements[m_chk].value='on'");
			out.println("}");	
			
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[m_chk].value='off'");
			out.println("}");		
			out.println("}");	
			
			
			out.println("function check_data2(row_no){")	;
			
			//out.println("alert('row'+row_no);");
		
			out.println("m_chk=\"CHK_STATUS\"+row_no;");
			out.println("m_chk_not=\"CHK_STATUS_NOT_APP\"+row_no;");
			//out.println("alert('m_chk'+document.Form1.elements[m_chk].value);");
			//out.println("alert('m_chk_not'+document.Form1.elements[m_chk_not].value);");

			out.println("if(document.Form1.elements[m_chk_not].checked==true && document.Form1.elements[m_chk].checked==true){");
			
			out.println("document.Form1.elements[m_chk_not].value='on'");
			out.println("document.Form1.elements[m_chk].checked=false");
			out.println("document.Form1.elements[m_chk].value='off'");
			
			out.println("}else if(document.Form1.elements[m_chk_not].checked==true && document.Form1.elements[m_chk].checked==false){");
			out.println("document.Form1.elements[m_chk_not].value='on'");
			out.println("}");	
			
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[m_chk_not].value='off'");
			out.println("}");		
			
			out.println("}");	
			
			
			out.println("function check_data_core(row_no){")	;
			//out.println("alert('row'+row_no);");
			
			out.println("var m_chk=\"CHK_STATUS_CORE\"+row_no;");
			out.println("var m_chk_not=\"CHK_STATUS_CORE_NOT_APP\"+row_no;");
		//	out.println("alert('m_chk'+document.Form1.elements[m_chk].value);");
		//	out.println("alert('m_chk_not'+document.Form1.elements[m_chk_not].value);");

			out.println("if(document.Form1.elements[m_chk].checked==true && document.Form1.elements[m_chk_not].checked==true){");
			out.println("document.Form1.elements[m_chk].value='on'");
			out.println("document.Form1.elements[m_chk_not].checked=false");
			out.println("document.Form1.elements[m_chk_not].value='off'");
			
			out.println("}else if(document.Form1.elements[m_chk].checked==true && document.Form1.elements[m_chk_not].checked==false){");
			out.println("document.Form1.elements[m_chk].value='on'");
			out.println("}");	
			
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[m_chk].value='off'");
			out.println("}");		
			out.println("}");	
			
			out.println("function check_data_core2(row_no){")	;
			//out.println("alert('row'+row_no);");
				
			out.println("m_chk=\"CHK_STATUS_CORE\"+row_no;");
			out.println("m_chk_not=\"CHK_STATUS_CORE_NOT_APP\"+row_no;");
		//	out.println("alert('m_chk'+document.Form1.elements[m_chk].value);");
		//	out.println("alert('m_chk_not'+document.Form1.elements[m_chk_not].value);");

			out.println("if(document.Form1.elements[m_chk_not].checked==true && document.Form1.elements[m_chk].checked==true){");
			
			out.println("document.Form1.elements[m_chk_not].value='on'");
			out.println("document.Form1.elements[m_chk].checked=false");
			out.println("document.Form1.elements[m_chk].value='off'");
			
			out.println("}else if(document.Form1.elements[m_chk_not].checked==true && document.Form1.elements[m_chk].checked==false){");
			out.println("document.Form1.elements[m_chk_not].value='on'");
			out.println("}");	
			
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[m_chk_not].value='off'");
			out.println("}");		
			out.println("}");	
			
			
			
			
			out.println("function check_data_gur(row_no){")	;
			out.println("m_chk=\"CHK_STATUS_GUR\"+row_no;");
			out.println("m_chk_not=\"CHK_STATUS_GUR_NOT_APP\"+row_no;");
			out.println("if(document.Form1.elements[m_chk].checked==true && document.Form1.elements[m_chk_not].checked==true){");
			
			out.println("document.Form1.elements[m_chk].value='on'");
			out.println("document.Form1.elements[m_chk_not].checked=false");
			out.println("document.Form1.elements[m_chk_not].value='off'");
			
			out.println("}else if(document.Form1.elements[m_chk].checked==true && document.Form1.elements[m_chk_not].checked==false){");
			out.println("document.Form1.elements[m_chk].value='on'");
			out.println("}");	
			
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[m_chk].value='off'");
			out.println("}");		
			out.println("}");	
			
			out.println("function check_data_gur2(row_no){")	;
			
			out.println("m_chk=\"CHK_STATUS_GUR\"+row_no;");
			out.println("m_chk_not=\"CHK_STATUS_GUR_NOT_APP\"+row_no;");
			out.println("if(document.Form1.elements[m_chk_not].checked==true && document.Form1.elements[m_chk].checked==true){");
			
			out.println("document.Form1.elements[m_chk_not].value='on'");
			out.println("document.Form1.elements[m_chk].checked=false");
			out.println("document.Form1.elements[m_chk].value='off'");
			
			out.println("}else if(document.Form1.elements[m_chk_not].checked==true && document.Form1.elements[m_chk].checked==false){");
			out.println("document.Form1.elements[m_chk_not].value='on'");
			out.println("}");	
			
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[m_chk_not].value='off'");
			out.println("}");		
			out.println("}");	
			
			
			
			
			out.println("function check_data_inv(row_no){")	;
			out.println("m_chk=\"CHK_STATUS_INV_DOC\"+row_no;");
			out.println("m_chk_not=\"CHK_STATUS_INV_DOC_NOT_APP\"+row_no;");
			out.println("if(document.Form1.elements[m_chk].checked==true && document.Form1.elements[m_chk_not].checked==true){");
			
			out.println("document.Form1.elements[m_chk].value='on'");
			out.println("document.Form1.elements[m_chk_not].checked=false");
			out.println("document.Form1.elements[m_chk_not].value='off'");
			
			out.println("}else if(document.Form1.elements[m_chk].checked==true && document.Form1.elements[m_chk_not].checked==false){");
			out.println("document.Form1.elements[m_chk].value='on'");
			out.println("}");	
			
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[m_chk].value='off'");
			out.println("}");		
			out.println("}");	
			
			out.println("function check_data_inv2(row_no){")	;
			
			out.println("m_chk=\"CHK_STATUS_INV_DOC\"+row_no;");
			out.println("m_chk_not=\"CHK_STATUS_INV_DOC_NOT_APP\"+row_no;");
			out.println("if(document.Form1.elements[m_chk_not].checked==true && document.Form1.elements[m_chk].checked==true){");
			
			out.println("document.Form1.elements[m_chk_not].value='on'");
			out.println("document.Form1.elements[m_chk].checked=false");
			out.println("document.Form1.elements[m_chk].value='off'");
			
			out.println("}else if(document.Form1.elements[m_chk_not].checked==true && document.Form1.elements[m_chk].checked==false){");
			out.println("document.Form1.elements[m_chk_not].value='on'");
			out.println("}");	
			
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[m_chk_not].value='off'");
			out.println("}");		
			out.println("}");	


			
			out.println("function Val_Change_Check(row){");
			
			out.println("if(document.Form1.hid_scr_approv.value==\"N\"){");
			
			out.println("m_chk_status=\"CHK_STATUS\"+row");
			
			out.println("if(document.Form1.elements[m_chk_status].checked==true){");
			out.println("document.Form1.elements[m_chk_status].value='on'");
			out.println("document.Form1.elements[m_chk_status].checked=true");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_chk_status].checked==false){");
			out.println("document.Form1.elements[m_chk_status].value='off'");
			out.println("document.Form1.elements[m_chk_status].checked=false");
			out.println("}");
			out.println("}");
			
			out.println("else if(document.Form1.hid_scr_approv.value==\"Y\"){");
			out.println("check_data(row);");
			out.println("}");
						
			out.println("}");
			
			
			out.println("function Val_Change_Check_Core(row){");
			
			out.println("if(document.Form1.hid_scr_approv.value==\"N\"){");
			
			out.println("m_chk_status=\"CHK_STATUS_CORE\"+row");
			
			out.println("if(document.Form1.elements[m_chk_status].checked==true){");
			out.println("document.Form1.elements[m_chk_status].value='on'");
			out.println("document.Form1.elements[m_chk_status].checked=true");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_chk_status].checked==false){");
			out.println("document.Form1.elements[m_chk_status].value='off'");
			out.println("document.Form1.elements[m_chk_status].checked=false");
			out.println("}");
			
			out.println("}");
			
			out.println("else if(document.Form1.hid_scr_approv.value==\"Y\"){");
			out.println("check_data_core(row);");
			out.println("}");
			
			
			
			
			out.println("}");
			
			
			out.println("function Val_Not_Applicable(row){");
			
			out.println("if(document.Form1.hid_scr_approv.value==\"N\"){");
			
			out.println("m_chk_status=\"CHK_STATUS_NOT_APP\"+row");
			
			out.println("if(document.Form1.elements[m_chk_status].checked==true){");
			out.println("document.Form1.elements[m_chk_status].value='on'");
			out.println("document.Form1.elements[m_chk_status].checked=true");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_chk_status].checked==false){");
			out.println("document.Form1.elements[m_chk_status].value='off'");
			out.println("document.Form1.elements[m_chk_status].checked=false");
			out.println("}");
			
			out.println("}");
			out.println("else if(document.Form1.hid_scr_approv.value==\"Y\"){");
			out.println("check_data2(row);");
			out.println("}");
			
			
			
			out.println("}");
			
						
			out.println("function Val_Core_Not_Applicable(row){");
			
			out.println("if(document.Form1.hid_scr_approv.value==\"N\"){");
				
			out.println("m_chk_status=\"CHK_STATUS_CORE_NOT_APP\"+row");
			
			out.println("if(document.Form1.elements[m_chk_status].checked==true){");
			out.println("document.Form1.elements[m_chk_status].value='on'");
			out.println("document.Form1.elements[m_chk_status].checked=true");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_chk_status].checked==false){");
			out.println("document.Form1.elements[m_chk_status].value='off'");
			out.println("document.Form1.elements[m_chk_status].checked=false");
			out.println("}");
			
			out.println("}");
			out.println("else if(document.Form1.hid_scr_approv.value==\"Y\"){");
			
			out.println("check_data_core2(row);");
			out.println("}");
			
			
			
			out.println("}");
			
			
			
			
			
				out.println("function Val_Change_Fol(row){");
			
			out.println("m_chk_status=\"CHK_STATUS_FOLLOWUP\"+row");
			
			out.println("if(document.Form1.elements[m_chk_status].checked==true){");
			out.println("document.Form1.elements[m_chk_status].value='on'");
			out.println("document.Form1.elements[m_chk_status].checked=true");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_chk_status].checked==false){");
			out.println("document.Form1.elements[m_chk_status].value='off'");
			out.println("document.Form1.elements[m_chk_status].checked=false");
			out.println("}");
			
			
			out.println("}");
			
			out.println("function Val_Change_Fol_Core(row){");
			
			out.println("m_chk_status_fol_core=\"CHK_STATUS_FOLLOWUP_CORE\"+row");
			
			out.println("if(document.Form1.elements[m_chk_status_fol_core].checked==true){");
			out.println("document.Form1.elements[m_chk_status_fol_core].value='on'");
			out.println("document.Form1.elements[m_chk_status_fol_core].checked=true");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_chk_status_fol_core].checked==false){");
			out.println("document.Form1.elements[m_chk_status_fol_core].value='off'");
			out.println("document.Form1.elements[m_chk_status_fol_core].checked=false");
		  out.println("}");
						
			out.println("}");
			
			out.println("function Val_Change_Gur(row){");
			
			out.println("if(document.Form1.hid_scr_approv.value==\"N\"){");
			
			out.println("m_chk_status_gur=\"CHK_STATUS_GUR\"+row");
			
			out.println("if(document.Form1.elements[m_chk_status_gur].checked==true){");
			out.println("document.Form1.elements[m_chk_status_gur].value='on'");
			out.println("document.Form1.elements[m_chk_status_gur].checked=true");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_chk_status_gur].checked==false){");
			out.println("document.Form1.elements[m_chk_status_gur].value='off'");
			out.println("document.Form1.elements[m_chk_status_gur].checked=false");
		  out.println("}");
			
			out.println("}");
			
			out.println("else if(document.Form1.hid_scr_approv.value==\"Y\"){");
			out.println("check_data_gur(row);");
			out.println("}");
			
			
						
			out.println("}");
			
			
			out.println("function Val_Change_Gur_Not_app(row){");
			
				out.println("if(document.Form1.hid_scr_approv.value==\"N\"){");
			
			out.println("m_chk_status_gur=\"CHK_STATUS_GUR_NOT_APP\"+row");
			
			out.println("if(document.Form1.elements[m_chk_status_gur].checked==true){");
			out.println("document.Form1.elements[m_chk_status_gur].value='on'");
			out.println("document.Form1.elements[m_chk_status_gur].checked=true");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_chk_status_gur].checked==false){");
			out.println("document.Form1.elements[m_chk_status_gur].value='off'");
			out.println("document.Form1.elements[m_chk_status_gur].checked=false");
		  out.println("}");
			
			out.println("}");
			
			out.println("else if(document.Form1.hid_scr_approv.value==\"Y\"){");
			out.println("check_data_gur2(row);");
			out.println("}");
						
			out.println("}");
			
			
			
			
			
			out.println("function Val_Change_Fol_Gur(row){");
			
			out.println("m_chk_status_fol_gur=\"CHK_STATUS_FOLLOWUP_GUR\"+row");
			
			out.println("if(document.Form1.elements[m_chk_status_fol_gur].checked==true){");
			out.println("document.Form1.elements[m_chk_status_fol_gur].value='on'");
			out.println("document.Form1.elements[m_chk_status_fol_gur].checked=true");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_chk_status_fol_gur].checked==false){");
			out.println("document.Form1.elements[m_chk_status_fol_gur].value='off'");
			out.println("document.Form1.elements[m_chk_status_fol_gur].checked=false");
		  out.println("}");
						
			out.println("}");
			
			
			out.println("function Val_Change_Inv_Doc(row){");
			
			
			out.println("if(document.Form1.hid_scr_approv.value==\"N\"){");
			
			out.println("m_chk_status_inv_doc=\"CHK_STATUS_INV_DOC\"+row");
			
			out.println("if(document.Form1.elements[m_chk_status_inv_doc].checked==true){");
			out.println("document.Form1.elements[m_chk_status_inv_doc].value='on'");
			out.println("document.Form1.elements[m_chk_status_inv_doc].checked=true");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_chk_status_inv_doc].checked==false){");
			out.println("document.Form1.elements[m_chk_status_inv_doc].value='off'");
			out.println("document.Form1.elements[m_chk_status_inv_doc].checked=false");
		  out.println("}");
			
			out.println("}");
			
			out.println("else if(document.Form1.hid_scr_approv.value==\"Y\"){");
			out.println("check_data_inv(row);");
			out.println("}");
			
						
			out.println("}");
			
			
			out.println("function Val_Inv_Doc_Not_App(row){");
			
			
			out.println("if(document.Form1.hid_scr_approv.value==\"N\"){");
				
			out.println("m_chk_status_inv_doc=\"CHK_STATUS_INV_DOC_NOT_APP\"+row");
			
			out.println("if(document.Form1.elements[m_chk_status_inv_doc].checked==true){");
			out.println("document.Form1.elements[m_chk_status_inv_doc].value='on'");
			out.println("document.Form1.elements[m_chk_status_inv_doc].checked=true");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_chk_status_inv_doc].checked==false){");
			out.println("document.Form1.elements[m_chk_status_inv_doc].value='off'");
			out.println("document.Form1.elements[m_chk_status_inv_doc].checked=false");
		  out.println("}");
			
			out.println("}");
			out.println("else if(document.Form1.hid_scr_approv.value==\"Y\"){");
			out.println("check_data_inv2(row);");
			out.println("}");
						
			out.println("}");
			
			
			
			
			out.println("function Val_Change_Fol_Inv_Doc(row){");
			
			out.println("m_chk_status_fol_inv_doc=\"CHK_STATUS_FOLLOWUP_INV_DOC\"+row");
			
			out.println("if(document.Form1.elements[m_chk_status_fol_inv_doc].checked==true){");
			out.println("document.Form1.elements[m_chk_status_fol_inv_doc].value='on'");
			out.println("document.Form1.elements[m_chk_status_fol_inv_doc].checked=true");
			out.println("}");
			
			out.println("else if(document.Form1.elements[m_chk_status_fol_inv_doc].checked==false){");
			out.println("document.Form1.elements[m_chk_status_fol_inv_doc].value='off'");
			out.println("document.Form1.elements[m_chk_status_fol_inv_doc].checked=false");
		  out.println("}");
						
			out.println("}");
			
			
			
			
			out.println("function check_remark(row){");
			
			out.println("m_status=\"CHK_STATUS_FOLLOWUP\"+row");
			out.println("m_remark=\"TXT_FOL_REMARK\"+row");
									
			out.println("if(document.Form1.elements[m_status].checked==false && document.Form1.elements[m_remark].value!=''  ){ ");
			
			out.println("alert('followup status should check');");
			out.println("document.Form1.elements[m_remark].value=''");
			
			out.println("}");			
			
			out.println("}");
			
			
			out.println("function check_remark_core(row){");
			
			out.println("m_status=\"CHK_STATUS_FOLLOWUP_CORE\"+row");
			out.println("m_remark=\"TXT_FOL_REMARK_CORE\"+row");
									
			out.println("if(document.Form1.elements[m_status].checked==false && document.Form1.elements[m_remark].value!=''  ){ ");
			
			out.println("alert('followup status should check');");
			out.println("document.Form1.elements[m_remark].value=''");
			
			out.println("}");			
			
			out.println("}");
			

			out.println("function check_remark_gur(row){");
			
			out.println("m_status=\"CHK_STATUS_FOLLOWUP_GUR\"+row");
			out.println("m_remark=\"TXT_FOL_REMARK_GUR\"+row");
									
			out.println("if(document.Form1.elements[m_status].checked==false && document.Form1.elements[m_remark].value!=''  ){ ");
			
			out.println("alert('followup status should check');");
			out.println("document.Form1.elements[m_remark].value=''");
			
			out.println("}");		
			
			out.println("}");			
			
			out.println("function check_remark_inv_doc(row){");
			
			out.println("m_status=\"CHK_STATUS_FOLLOWUP_INV_DOC\"+row");
			out.println("m_remark=\"TXT_FOL_REMARK_INV_DOC\"+row");
									
			out.println("if(document.Form1.elements[m_status].checked==false && document.Form1.elements[m_remark].value!=''  ){ ");
			
			out.println("alert('followup status should check');");
			out.println("document.Form1.elements[m_remark].value=''");
			
			out.println("}");			
			
			out.println("}");

					//-----added by : delanjali-----------------------------------------------------------
					//-----purpose	: to add acknowlege check box for credit vreification process---------
					//-----date			:	2007-01-18----------------------------------------------------------
					

					
					out.println("function check_change() {");
					out.println("if(document.Form1.CHK_ACK.checked==false){");
					out.println("document.Form1.CHK_ACK.value=\"0\"");
					//out.println("window.opener.document.Form1.Btn_approve.disabled=true"); // commented by udara on 07-01-2013
					out.println("window.opener.document.Form1.Btn_Security_Det.disabled=true"); // added by udara on 07-01-2013
					out.println("}");
					out.println("else if(document.Form1.CHK_ACK.checked==true){");
					out.println("document.Form1.CHK_ACK.value=\"1\"");
					//out.println("window.opener.document.Form1.Btn_approve.disabled=false"); // commentedd by udara on 07-01-2013
					out.println("window.opener.document.Form1.Btn_Security_Det.disabled=false"); // added by udara on 07-01-2013
					out.println("}");
					out.println("}");
					
					//--modified by delanjali on 2007-08-13 for ref no : 788-----------------------------------------------------------------------------
					out.println("function add_label_ba(){");
					out.println("e_label_ba.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
					out.println("'<tr>'+");
					out.println("'<td width=\"80%\" ><b><DIV id=\"DIV_TXT_BA\" class=\"div_input\">Please select Documents</div></b></td>'+"); 
					out.println("'</tr>'+");
			    out.println("'</TR></table>';");
					out.println("}");
					
					out.println("function add_label_ba_1(){");
					out.println("e_label_ba1.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\"><TR>'+");
					out.println("'<tr>'+");
					out.println("'<td width=\"80%\" ><b><DIV id=\"DIV_TXT_BA1\" class=\"div_input\">Please select Documents</div></b></td>'+"); 
					out.println("'</tr>'+");
			    out.println("'</TR></table>';");
					out.println("}");
					
					
					//-----------------------------------------------------------------------------------------------------------------------------------
			//==========Added By Nuwan De Silva 29-05-07============================================//
			
			out.println("function close_screen2() {");
	
			out.println("	if(document.Form1.close.value==\"Close\"){");

			out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		window.close();"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("}");
			
     //============================================================================================//


				
					
			
		
					out.println("</script>"); 
					
					
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"assign_app_no()\">"); //load_lock()load_application_no(),disable_app_no(),header(),add_row()
					out.println("<FORM NAME='Form1' method='post'>");
					
					out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
					out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			  //  out.println("<INPUT TYPE='Hidden' NAME='hid_no_of_clinet_doc' VALUE=\"\">"); 
				  //out.println("<INPUT TYPE='Hidden' NAME='hid_no_of_core_doc' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_app_no' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_sum' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
					out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_MK_APPLICATION_PROCESS\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_core_app_code' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_followup_num' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_client_no' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_gur_code' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_count_gur' VALUE=\"0\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_count_invoice_doc' VALUE=\"0\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_scr_approv' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='Hid_records' VALUE=\"\">"); 
					
					
					
					
					
					
					out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
					out.println("<tr>"); 
					out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
					out.println("<td class='border_wht' valign='top'> "); 
					
					out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
					/*
					out.println("<tr> "); 
					
					out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
					out.println("</tr>"); 
					*/
					out.println("<tr> "); 
					out.println("<td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td style='height: 327px'>"); 
					
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
					out.println("<tr>"); 
					out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
					out.println("</tr>"); 
					/*
					out.println("<tr>"); 
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Application Process - Documents Required</td>"); 
					out.println("</tr>"); 
					*/
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					
					/*
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%'></td>");  
					out.println("<td width='10%'></td>");  
					out.println("<td width='10%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
   		            out.println("<td width='10%' align='center'><input type=button name=close value=\"Close\" class=mainbut onclick=close_screen2(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");


					
								
					out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
					out.println("</table>");  
					*/
					
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
		
									
					out.println("<tr >"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input><b>Application No</b> </DIV></td>"); 
					//out.println("<td width='30%' id='app_id' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15' onblur=\"assignState('M1'),makeRequest(document.Form1.TXT_APPLICATION_NO)\" disabled>"); 
					out.println("<td width='30%'  style= cursor:hand; onClick=\"show_application_detail_drill('"+m_app_no+"')\"><u><DIV ID='app_id'></DIV></u></td>"); //id='app_id'
					out.println("</td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					out.println("</table>"); 
					
					
					out.println("<br>"); 
					
					 
				
				
				  out.println("<table align='center' width='100%' class='table'>");
					
					out.println("<tr><td width='*%' id=\"MK_NAME\" ></td>"); 
					out.println("</tr>");
									
					out.println("<tr ><td  width='*%' ><b><U>Required Documents - Applicant </U></td></tr>");
					
					out.println("</table>"); 
					// udara 29-10-2013
					/*
					out.println("<table align='center' width='100%' class='table'>"); 
			   									
					out.println("<tr ><td  width='30%' ><b>Applicant Code</b></td><td  width='*%' style= cursor:hand; onClick=\"show_client('"+m_client_code+"')\" ><u>"+m_client_code+"</u></td></tr>");
					
					out.println("<tr ><td  width='30%' >Applicant Name</td><td  width='*%' >"+m_name+"</td></tr>");
					out.println("<tr></tr>");
				  out.println("<tr></tr>");
					out.println("</table>"); 
					*/
					
					out.println("<table align='center' width='100%' class='table'>"); 
					
					
					if(m_scr_approv.equals("N"))
				{
						
					out.println("<tr align='left'>");
					out.println("<td  width='15%' ><b>Document Code</td>");
			        out.println("<td  width='30%' ><b>Description</td>");
					/*
			        out.println("<td  width='10%' ><b>Status</td>");
			        out.println("<td  width='15%' ><b>Remarks</td>");
					out.println("<td  width='15%' ><b>Follow up Status</td>");
					out.println("<td  width='15%' ><b>Follow up Remarks</td>");
					*/
					out.println("</tr>");
					
					}
					else if(m_scr_approv.equals("Y"))
				    {
					
					out.println("<tr align='left'>");
					out.println("<td  width='15%' ><b>Document Code</td>");
          			out.println("<td  width='25%' ><b>Description</td>");
					/*
          			out.println("<td  width='10%' ><b>Status</td>");
					out.println("<td  width='10%' ><b>Not Applicable</td>");
          			out.println("<td  width='15%' ><b>Remarks</td>");
					out.println("<td  width='10%' ><b>Follow up Status</td>");
					out.println("<td  width='15%' ><b>Follow up Remarks</td>");
					*/
					out.println("</tr>");


					
					}
					
					int j = 0;   
					int count=0;
					

				
						rs= stmt.executeQuery ("SELECT "+
						"     CODE,DESCRIPTION,NVL(NULL,'-') REMARK,NVL(NULL,'-') STATUS, "+
						"     NVL(NULL,'-') FOLLOWUP_REMARKS, NVL(NULL,'-') REF_NO  "+
						" FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED  "+
						" WHERE   "+
						"     CODE IN ( SELECT  CODE  "+
						"          FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE  "+
						"          WHERE  ENTITY_TYPE=(    "+
						"                       SELECT CLIENT_CATEGORY  "+
						"                         FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
						"                         WHERE CLIENT_CODE=UPPER('"+m_client_code+"'))  AND  "+
						"              DIVISION_CODE='AF'       AND     "+
						"              ITEM_CAT_CODE IS NULL    AND     "+
						"              ACTIVE_STATUS='Y'        AND     "+
						"  						FROM_SCREEN_NO <=  "+
						"  						(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
						"  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_Hid_scr_name+"')  "+
						"  						AND TO_SCREEN_NO >=  "+
						"  						(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
						"  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_Hid_scr_name+"') AND  "+
						//"            PRODUCT_CODE=('FINLEASE')          AND    "+ 
						"            PRODUCT_CODE=('"+m_txt_type+"')      AND    "+  //Modified By Nuwan De Silva 17-05-07
						"              CODE NOT IN ( SELECT  DOCUMENT_TYPE  "+
						"                      FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT  "+
						"                      WHERE APPLICATION_NO=UPPER('"+m_app_no+"')  AND   "+
						"                         CLIENT_CODE=UPPER('"+m_client_code+"') AND   "+
						" 				PRO_INVOICE_NO IS NULL   "+
						"                          )  "+
						"             ) AND             "+
						"             DOC_APP_TYPE='CLIENT' AND  "+ 
						"             ACTIVE_STATUS='Y'  "+
						
						"  UNION  "+
						
						"  SELECT DISTINCT C.DOCUMENT_TYPE ,A.DESCRIPTION D,NVL(C.REMARK,'-') REMARK, C.STATUS STATUS,NVL(C.FOLLOWUP_REMARKS,'-') FOLLOWUP_REMARKS,NVL(C.REF_NO,'-') REF_NO  "+
						"  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED A, "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE B,"+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT C "+
						"  WHERE "+
						
						"  B.ENTITY_TYPE=( SELECT CLIENT_CATEGORY  "+
						"                  FROM   "+m_schema_name+".AF_CO_MAS_CLIENT  "+
						"                  WHERE CLIENT_CODE=UPPER('"+m_client_code+"') "+
						"                )  "+
						
						"  AND "+
						"  DOC_APP_TYPE='CLIENT' AND A.ACTIVE_STATUS=('Y') "+
						"  AND C.DOCUMENT_TYPE IN "+
						"  (SELECT CODE "+
						"  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
						"  WHERE FROM_SCREEN_NO <= "+
						"  (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
						"  WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_Hid_scr_name+"') "+
						"  AND TO_SCREEN_NO >=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
						"  WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_Hid_scr_name+"') AND "+
						"  ENTITY_TYPE=( SELECT  "+
						"                CLIENT_CATEGORY  "+
						"                FROM  "+
						"                "+m_schema_name+".AF_CO_MAS_CLIENT  "+
						"                WHERE  "+
						"                CLIENT_CODE=UPPER('"+m_client_code+"') "+
						"                    )  "+
						
						//"  AND  PRODUCT_CODE=('FINLEASE')       "+
						"  AND PRODUCT_CODE=('"+m_txt_type+"')          "+  //Modified By Nuwan De Silva 17-05-07
						"  AND B.CODE=A.CODE "+
						"  AND ACTIVE_STATUS=('Y')) "+
						"  AND C.DOCUMENT_TYPE=A.CODE "+
						" AND C.APPLICATION_NO=UPPER('"+m_app_no+"') "+
						" AND C.CLIENT_CODE=UPPER('"+m_client_code+"') "+
						"  AND C.PRO_INVOICE_NO IS NULL "+
						" ORDER BY STATUS ASC ");


			
						
							if(m_scr_approv.equals("N"))
							{
						
								//	count=0;
									j=0;
						while(rs.next())
				{
						      
									
								String m_chk_status=rs.getString(4);
									
						
									if(j>0 && j%2==1)
										{
									
									
                  out.println("<tr class=tr_input1  >");
									}
									else
										{
                  	out.println("<tr class=tr_input  >");
									}
									

						
									out.println("<td width='15%' align='left' style= cursor:hand; onClick=\"show_document_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u></td>");
									out.println("<INPUT TYPE=\"Hidden\" NAME=hid_TXT_CODE"+j+"	VALUE='"+rs.getString(1) +"'>");
									
									
                  out.println("<td width='30%' align='left'>"+rs.getString(2) +"</td>");
							
                  /*
									// m_chk_status=rs.getString(4);
									
									if(m_chk_status.equals("Y") ) //|| m_chk_status.equals("N")
									{
									out.println("<td width='10%' align='left'><input  type='checkbox' name=CHK_STATUS"+j+" value='on' checked  onclick=\"Val_Change_Check("+j+")\"></td>");
									}
									else if(m_chk_status.equals("-") || m_chk_status.equals("N") || m_chk_status.equals("A"))
									{
									out.println("<td width='10%' align='left'><input  type='checkbox' name=CHK_STATUS"+j+" value='off'  onclick=\"Val_Change_Check("+j+")\"></td>");
									}
																	
                  out.println("<td width='15%' align='left'><input  class='txt_input' type='text' name=TXT_REMARK"+j+"  Value='"+rs.getString(3) +"' maxlength='100' size='10'  ></td>");
									
									String m_foll_num=rs.getString(6);
									
                  if(m_foll_num.equals("-") ) 
									{
									out.println("<td width='15%' align='left'><input  type='checkbox' name=CHK_STATUS_FOLLOWUP"+j+"  onclick=\"Val_Change_Fol("+j+")\" value='off'></td>");
									}
									else 
									{
									out.println("<td width='15%' align='left'><input  type='checkbox' name=CHK_STATUS_FOLLOWUP"+j+"  onclick=\"Val_Change_Fol("+j+")\" value='on' checked ></td>");
									}
					
					        out.println("<td width='15%' align='left'><input  class='txt_input' name=TXT_FOL_REMARK"+j+" onBlur=\"check_remark("+j+")\" maxlength='100' size='10' Value=\""+rs.getString(5) +"\"  ></td>");
							*/
                  out.println("</tr>");
									
									
								//	count=count+1;
                	j=j+1;
									no_of_clinet_doc=no_of_clinet_doc+1;
									
									}
									
									
									}
									
									
									else if(m_scr_approv.equals("Y"))
							{
									
									count=0;
									j=0;
									String m_chk_status="";
								while(rs.next())
				      {

									m_chk_status=rs.getString(4);
									
									if(j>0 && j%2==1)
										{
									
									if(m_chk_status.equals("A"))
										{
									if(count==0)
										{  //======header====
									out.println("<tr STYLE=\"{color:red;}\">");
									out.println("<td width='15%' align='left'><b>System Exception</b></td>");
									out.println("</tr>");
									  }  //==end header
									
                  out.println("<tr class=tr_input1 STYLE=\"{color:red;}\"   >"); //red line row=
									}
									
									else
									{
									out.println("<tr class=tr_input1>");
									}
									
									
									}
									
									else
										{
									
									if(m_chk_status.equals("A"))
										{
									if(count==0)
										{
									out.println("<tr STYLE=\"{color:red;}\">");
									out.println("<td width='15%' align='left'><b>System Exception</b></td>");
									out.println("</tr>");
									}
									
                  out.println("<tr class=tr_input STYLE=\"{color:red;}\"   >");
									}
									else
										{
									out.println("<tr class=tr_input  >");
									}
                  	
									}
								                				

									out.println("<td width='15%' align='left' style= cursor:hand; onClick=\"show_document_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u></td>");
									out.println("<INPUT TYPE=\"Hidden\" NAME=hid_TXT_CODE"+j+"	VALUE='"+rs.getString(1) +"'>");
									
									out.println("<td width='25%' align='left'>"+rs.getString(2) +"</td>");
							
                  					/*
									//m_chk_status=rs.getString(4);
									
								  //out.println("status"+m_chk_status);
									
									if(m_chk_status.equals("Y"))
									{
									out.println("<td width='10%' align='left'><input  type='checkbox' name=CHK_STATUS"+j+" value='on' checked  onclick=\"Val_Change_Check("+j+")\"></td>");
									out.println("<td width='10%' align='left'><input  type='checkbox' name=CHK_STATUS_NOT_APP"+j+" value='off'  onclick=\"Val_Not_Applicable("+j+")\"></td>");
									}
									
									else if(m_chk_status.equals("A")) 
									{
									out.println("<td width='10%' align='left'><input  type='checkbox' name=CHK_STATUS"+j+"         value='off'  onclick=\"Val_Change_Check("+j+")\"></td>");
									out.println("<td width='10%' align='left'><input  type='checkbox' name=CHK_STATUS_NOT_APP"+j+" value='on' checked  onclick=\"Val_Not_Applicable("+j+")\"></td>");
									}
									else
									{
									out.println("<td width='10%' align='left'><input  type='checkbox' name=CHK_STATUS"+j+" value='off'  onclick=\"Val_Change_Check("+j+")\"></td>");
									out.println("<td width='10%' align='left'><input  type='checkbox' name=CHK_STATUS_NOT_APP"+j+" value='off'  onclick=\"Val_Not_Applicable("+j+")\"></td>");
									}
									
															
                  out.println("<td width='15%' align='left'><input  class='txt_input' type='text' name=TXT_REMARK"+j+"  Value='"+rs.getString(3) +"' maxlength='100' size='10'  ></td>");
									
									String m_foll_num=rs.getString(6);
									
                  if(m_foll_num.equals("-") ) 
									{
									out.println("<td width='10%' align='left'><input  type='checkbox' name=CHK_STATUS_FOLLOWUP"+j+"  onclick=\"Val_Change_Fol("+j+")\" value='off'></td>");
									}
									else 
									{
									out.println("<td width='10%' align='left'><input  type='checkbox' name=CHK_STATUS_FOLLOWUP"+j+"  onclick=\"Val_Change_Fol("+j+")\" value='on' checked ></td>");
									}
					
					        out.println("<td width='15%' align='left'><input  class='txt_input' name=TXT_FOL_REMARK"+j+" onBlur=\"check_remark("+j+")\" maxlength='100' size='10' Value=\""+rs.getString(5) +"\"  ></td>");
							*/
                  out.println("</tr>");
																
									count=count+1;
                	j=j+1;
									no_of_clinet_doc=no_of_clinet_doc+1;
									
									}
									
											
									
              }
							
							
					       out.println("<INPUT TYPE=\"Hidden\" NAME=hid_no_of_clinet_doc	VALUE=\""+no_of_clinet_doc+"\">");
			   
				
			   
			   out.println("</table>");
					
					
					
					out.println("<br><hr><br>");
					
					if(!m_core_app_code.equals("-"))
				{
				 
				 out.println("<table align='center' width='100%' class='table'>"); 
					
				 out.println("<tr><td width='15%' id=\"MK_NAME1\" ></td>"); 
				 out.println("</tr>");


					out.println("<tr ><td  width='*%' ><b><U>Required Documents - Co-Applicant </U></td></tr>");
					
					out.println("</table>"); 
					
					out.println("<table align='center' width='100%' class='table'>"); 
			   									
					out.println("<tr ><td  width='30%' ><b>Core Applicant Code</b></td><td  width='*%' style= cursor:hand; onClick=\"show_client('"+m_core_app_code+"')\" ><u>"+m_core_app_code+"</u></td></tr>");
					
					out.println("<tr ><td  width='30%' >Core Applicant Name</td><td  width='*%' >"+m_core_name+"</td></tr>");
					out.println("<tr></tr>");
				  out.println("<tr></tr>");
					out.println("</table>"); 
					
					out.println("<table align='center' width='100%' class='table'>"); 
			
					
					if(m_scr_approv.equals("N"))
						{
						
					out.println("<tr align='left'>");
					out.println("<td  width='15%' ><b>Document Code</td>");
          out.println("<td  width='30%' ><b>Description</td>");
			/*
          out.println("<td  width='10%' ><b>Status</td>");
          out.println("<td  width='15%' ><b>Remarks</td>");
					out.println("<td  width='15%' ><b>Follow up Status</td>");
					out.println("<td  width='15%' ><b>Follow up Remarks</td>");
					*/
					out.println("</tr>");
					
					}
					else if(m_scr_approv.equals("Y"))
						{
					
					out.println("<tr align='left'>");
					out.println("<td  width='15%' ><b>Document Code</td>");
          out.println("<td  width='25%' ><b>Description</td>");
			/*
          out.println("<td  width='10%' ><b>Status</td>");
					out.println("<td  width='10%' ><b>Not Applicable</td>");
          out.println("<td  width='15%' ><b>Remarks</td>");
					out.println("<td  width='10%' ><b>Follow up Status</td>");
					out.println("<td  width='15%' ><b>Follow up Remarks</td>");
					*/
					out.println("</tr>");


					
					}


					
					
					j = 0;   
					count=0;
								
				
							rs= stmt.executeQuery 
							("            SELECT "+
							"             CODE,DESCRIPTION,NVL(NULL,'-') REMARK,NVL(NULL,'-') STATUS, "+
							"             NVL(NULL,'-') FOLLOWUP_REMARKS, NVL(NULL,'-') REF_NO  "+
							"             FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED  "+
							"             WHERE   "+
							"             CODE IN ( SELECT  CODE  "+
							"             FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE  "+
							"             WHERE  ENTITY_TYPE=(    "+
							"                       SELECT CLIENT_CATEGORY  "+
							"                         FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
							"                         WHERE CLIENT_CODE=UPPER('"+m_core_app_code+"'))  AND  "+
							"             DIVISION_CODE='AF'       AND     "+
							"             ITEM_CAT_CODE IS NULL    AND     "+
							"             ACTIVE_STATUS='Y'        AND     "+
							"  						FROM_SCREEN_NO <=  "+
							"  						(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
							"  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_Hid_scr_name+"')  "+
							"  						AND TO_SCREEN_NO >=  "+
							"  						(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
							"  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_Hid_scr_name+"') AND  "+
							//"            PRODUCT_CODE=('FINLEASE')          AND    "+ 
							"             PRODUCT_CODE=('"+m_txt_type+"')          AND    "+  //Modified Nuwan De Silva 17-05-07
							"             CODE NOT IN ( SELECT  DOCUMENT_TYPE  "+
							"                      FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT  "+
							"                      WHERE APPLICATION_NO=UPPER('"+m_app_no+"')  AND   "+
							"                         CLIENT_CODE=UPPER('"+m_core_app_code+"') AND   "+
							" 				     PRO_INVOICE_NO IS NULL   "+
							"                          )  "+
							"              ) AND             "+
							"              DOC_APP_TYPE='CLIENT' AND  "+ 
							"              ACTIVE_STATUS='Y'  "+
							
							"  UNION  "+
							
							"  SELECT DISTINCT C.DOCUMENT_TYPE ,A.DESCRIPTION D,NVL(C.REMARK,'-') REMARK, C.STATUS STATUS,NVL(C.FOLLOWUP_REMARKS,'-') FOLLOWUP_REMARKS,NVL(C.REF_NO,'-') REF_NO  "+
							"  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED A, "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE B,"+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT C "+
							"  WHERE "+
							
							"  B.ENTITY_TYPE=( SELECT CLIENT_CATEGORY  "+
							"                  FROM   "+m_schema_name+".AF_CO_MAS_CLIENT  "+
							"                  WHERE CLIENT_CODE=UPPER('"+m_core_app_code+"') "+
							"                )  "+
							
							"  AND "+
							"  DOC_APP_TYPE='CLIENT' AND A.ACTIVE_STATUS=('Y') "+
							"  AND C.DOCUMENT_TYPE IN "+
							"  (SELECT CODE "+
							"  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
							"  WHERE FROM_SCREEN_NO <= "+
							"  (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
							"  WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_Hid_scr_name+"') "+
							"  AND TO_SCREEN_NO >=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
							"  WHERE DIVISION_CODE='AF' AND SCREEN_NAME='"+m_Hid_scr_name+"') AND "+
							"  ENTITY_TYPE=( SELECT  "+
							"                CLIENT_CATEGORY  "+
							"                FROM  "+
							"                "+m_schema_name+".AF_CO_MAS_CLIENT  "+
							"                WHERE  "+
							"                CLIENT_CODE=UPPER('"+m_core_app_code+"') "+
							"                    )  "+
							
							//"  AND  PRODUCT_CODE=('FINLEASE')       "+
							"  AND PRODUCT_CODE=('"+m_txt_type+"')              "+  //Modified Nuwan De Silva 17-05-07
							"  AND B.CODE=A.CODE "+
							"  AND ACTIVE_STATUS=('Y')) "+
							"  AND C.DOCUMENT_TYPE=A.CODE "+
							"  AND C.APPLICATION_NO=UPPER('"+m_app_no+"') "+
							"  AND C.CLIENT_CODE=UPPER('"+m_core_app_code+"') "+
							"  AND C.PRO_INVOICE_NO IS NULL "+
							"  ORDER BY STATUS ASC ");

				
					
					 
						if(m_scr_approv.equals("N"))
						{
						j=0;
						
						String m_chk_status_core="";
						
						while(rs.next())
							{
							    
							m_chk_status_core=rs.getString(4);
			
																	
									if(j>0 && j%2==1)
							{
                  	out.println("<tr class=tr_input1 >");
									}
									else
							{
                  	out.println("<tr class=tr_input >");
									}
									
								  out.println("<td width='15%' align='left' style= cursor:hand; onClick=\"show_document_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u></td>");
									out.println("<INPUT TYPE=\"Hidden\" NAME=hid_TXT_CODE_CORE"+j+"	VALUE='"+rs.getString(1) +"'>");
									
                  out.println("<td width='30%' align='left'>"+rs.getString(2) +"</td>");
								
									
									/*
									if(m_chk_status_core.equals("Y") ) //|| m_chk_status.equals("N")
									{
									out.println("<td width='10%' align='left'><input  type='checkbox' name=CHK_STATUS_CORE"+j+" value='on' checked  onclick=\"Val_Change_Check_Core("+j+")\"></td>");
									}
									else if(m_chk_status_core.equals("-") || m_chk_status_core.equals("N") || m_chk_status_core.equals("A") )
									{
									out.println("<td width='10%' align='left'><input  type='checkbox' name=CHK_STATUS_CORE"+j+" value='off'  onclick=\"Val_Change_Check_Core("+j+")\"></td>");
									}
									
																	
									
                                  
									out.println("<td width='15%' align='left'><input  class='txt_input' type='text' name=TXT_REMARK_CORE"+j+" Value='"+rs.getString(3) +"' maxlength='100' size='10' > </td>");
									
									String m_foll_num=rs.getString(6);
									
                   if(m_foll_num.equals("-") ) 
									{
									out.println("<td width='15%' align='left'><input  type='checkbox'  name=CHK_STATUS_FOLLOWUP_CORE"+j+" value='off' onclick=\"Val_Change_Fol_Core("+j+")\"></td>");
									}
									else 
									{
									out.println("<td width='15%' align='left'><input  type='checkbox'  name=CHK_STATUS_FOLLOWUP_CORE"+j+" value='on' checked onclick=\"Val_Change_Fol_Core("+j+")\"></td>");
									}
																	
                  out.println("<td width='15%' align='left'><input  class='txt_input' name=TXT_FOL_REMARK_CORE"+j+"  maxlength='100' size='10' Value=\""+rs.getString(5) +"\" onBlur=\"check_remark_core("+j+")\"></td>");
                  */
					out.println("</tr>");
									
									}
															
                	j=j+1;
									no_of_core_doc=no_of_core_doc+1;
									
                }
									
									
									else if(m_scr_approv.equals("Y"))
						{
									
									count=0;
									j=0;
									String m_chk_status_core="";
									while(rs.next())
										{
							    
								  m_chk_status_core=rs.getString(4);
									
										
									if(j>0 && j%2==1)
										{
									
									if(m_chk_status_core.equals("A"))
										{
									if(count==0)
										{
									out.println("<tr STYLE=\"{color:red;}\">");
									out.println("<td width='15%' align='left'><b>System Exception</b></td>");
									out.println("</tr>");
									}
                  out.println("<tr class=tr_input1 STYLE=\"{color:red;}\"   >");
									}
									else
										{
									out.println("<tr class=tr_input1 >");
									}
									
									}
									else
										{
									
									if(m_chk_status_core.equals("A"))
										{
									if(count==0)
										{
									out.println("<tr STYLE=\"{color:red;}\">");
									out.println("<td width='15%' align='left'><b>System Exception</b></td>");
									out.println("</tr>");
									}
									
                  out.println("<tr class=tr_input STYLE=\"{color:red;}\"   >");
									}
									else
										{
									out.println("<tr class=tr_input >");
									}
                  	
									}
									
									//if(j==0){
									//out.println("<tr><td width='15%' id=\"MK_NAME1\" ></td>"); 
									//out.println("</tr>");
									//}
									out.println("<td width='15%' align='left' style= cursor:hand; onClick=\"show_document_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u></td>");
									out.println("<INPUT TYPE=\"Hidden\" NAME=hid_TXT_CODE_CORE"+j+"	VALUE='"+rs.getString(1) +"'>");
									
                  out.println("<td width='25%' align='left'>"+rs.getString(2) +"</td>");
								
					
									/*
									if(m_chk_status_core.equals("Y"))
									{
									out.println("<td width='10%' align='left'><input  type='checkbox' name=CHK_STATUS_CORE"+j+" value='on' checked  onclick=\"Val_Change_Check_Core("+j+")\"></td>");
									out.println("<td width='10%' align='left'><input  type='checkbox' name=CHK_STATUS_CORE_NOT_APP"+j+" value='off'  onclick=\"Val_Core_Not_Applicable("+j+")\"></td>");
									}
									else if(m_chk_status_core.equals("A")) 
									{
									out.println("<td width='10%' align='left'><input  type='checkbox' name=CHK_STATUS_CORE"+j+" value='off'  onclick=\"Val_Change_Check_Core("+j+")\"></td>");
									out.println("<td width='10%' align='left'><input  type='checkbox' name=CHK_STATUS_CORE_NOT_APP"+j+" value='on' checked  onclick=\"Val_Core_Not_Applicable("+j+")\"></td>");
																		
									}
									else
									{
									out.println("<td width='10%' align='left'><input  type='checkbox' name=CHK_STATUS_CORE"+j+" value='off'  onclick=\"Val_Change_Check_Core("+j+")\"></td>");
									out.println("<td width='10%' align='left'><input  type='checkbox' name=CHK_STATUS_CORE_NOT_APP"+j+" value='off'  onclick=\"Val_Core_Not_Applicable("+j+")\"></td>");
									}
									
														                                 
									out.println("<td width='15%' align='left'><input  class='txt_input' type='text' name=TXT_REMARK_CORE"+j+" Value='"+rs.getString(3) +"' maxlength='100' size='10' > </td>");
									
									String m_foll_num=rs.getString(6);
									
                   if(m_foll_num.equals("-") ) 
									{
									out.println("<td width='10%' align='left'><input  type='checkbox'  name=CHK_STATUS_FOLLOWUP_CORE"+j+" value='off' onclick=\"Val_Change_Fol_Core("+j+")\"></td>");
									}
									else 
									{
									out.println("<td width='10%' align='left'><input  type='checkbox'  name=CHK_STATUS_FOLLOWUP_CORE"+j+" value='on' checked onclick=\"Val_Change_Fol_Core("+j+")\"></td>");
									}
																	
                  out.println("<td width='15%' align='left'><input  class='txt_input' name=TXT_FOL_REMARK_CORE"+j+"  maxlength='100' size='10' Value=\""+rs.getString(5) +"\" onBlur=\"check_remark_core("+j+")\"></td>");
					*/
					
					out.println("</tr>");
														
									
									
									count=count+1;
                	j=j+1;
									no_of_core_doc=no_of_core_doc+1;
									
              }
							
							}
							  //out.println("<INPUT TYPE=\"Hidden\" NAME=hid_no_of_core_doc	VALUE=\""+no_of_core_doc+"\">");
							
							out.println("</table>");	
								
							out.println("<hr>");
							
						}	
							
					out.println("<INPUT TYPE=\"Hidden\" NAME=hid_no_of_core_doc	VALUE=\""+no_of_core_doc+"\">");
					
					out.println("<table align='center' width='100%' class='table'>"); 
				  out.println("<tr>" );
				  out.println("<td width=\"100%\"><DIV ID=e_label_ba> </DIV></td>");				
					out.println("</tr>" );
		      out.println("</table>");
			
				 out.println("<table align='center' width='100%' border=\"0\" class='table'>"); 
				 out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table_gurantor'></DIV></td>");
		     out.println("</tr>"); 	
			   out.println("</table>");
					
					
					
				out.println("<table align='center' width='100%' class='table'>"); 
		    out.println("<tr>" );
		  	out.println("<td width=\"100%\"><DIV ID=e_label_ba1> </DIV></td>");				
				out.println("</tr>" );
     		out.println("</table>");
							
					
			 out.println("<table align='center' width='100%' border=\"0\" class='table'>"); 
			 out.println("<tr>"); 
		   out.println("<td width=\"100%\"><DIV ID='m_asset'></DIV></td>");
	     out.println("</tr>"); 	
		   out.println("</table>");
	

					//-----added by : delanjali-----------------------------------------------------------
					//-----purpose	: to add acknowlege check box for credit vreification process---------
					//-----date			:	2007-01-18----------------------------------------------------------
					
					if  (m_hid_records.equals("A") || m_hid_records.equals(""))
				{
									 out.println("<table align='center' width='100%' border=\"0\" class='table'>"); 

	//				out.println("<tr><td width=\"1%\" align=\"left\"><b>Acknowledge<input  type=\"checkbox\" name=CHK_ACK value=\"N\" unchecked onclick=\"check_change()\" ></td></tr>");
								out.println("<tr><td width='10%' align=\"left\"><b>Acknowledge<input  type=\"checkbox\" name=CHK_ACK value=\"N\" unchecked onclick=\"check_change()\" ></td></tr>");
			out.println("<tr><td width='10%' align=\"left\" ><input type=\"button\"  class='mainbut' style=\"width:140px\" name=\"close2\" onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Next\");'  onclick='close_screen()' value=\"Proceed to Next Level\"></td></tr>");  
			out.println("<tr></tr>");
			out.println("<tr></tr>");
					out.println("</table>");
					}
							
					out.println("<br>"); 
					
					out.println("<table align='center' width='100%'>"); 
					out.println("<tr>"); 
					out.println("<td width='100%' class='note'></td>"); 
					out.println("</tr>"); 
					out.println("</table>"); 
					
					/*
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
					out.println("<td width='10%'></td>");  
					out.println("<td width='10%'></td>");  
					out.println("<td width='10%'></td>");  
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
   		   			// if  (m_hid_records.equals("A") || m_hid_records.equals("")){

			   		// out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' style=\"width:140px\" name=\"close2\" onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Next\");'  onclick='close_screen()' value=\"Proceed to Next Level\"></td>");  
					//}
					//else{
           			if  (!m_hid_records.equals("A") && !m_hid_records.equals(""))
				  	{
					out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_screen(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
					}
					
					out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
					out.println("</table>"); 
					*/
					
					out.println("</td></tr><tr>");  
					out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
					out.println("</tr><tr>");  
					out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
					
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
					out.println("<tr class='tr_input'>");  
					out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
					out.println("</tr>");  
					out.println("</table>");  
					
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 

					out.println("</body>"); 
					out.println("</html>"); 
      }
	  }catch (Exception e) 
		{
			try{out.println(e.toString());}catch(Exception e1){}
      //return null;
		}finally
		{
		  //if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			//if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	    //if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e2){}}
			//try{conn.setAutoCommit(true);
		}
	}
}


