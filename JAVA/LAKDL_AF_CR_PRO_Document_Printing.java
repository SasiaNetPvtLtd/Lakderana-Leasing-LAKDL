//Created by Nuwan De Silva on 08-02-2007 at  9.00am A.M.
//Document Printting

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CR_PRO_Document_Printing extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	CallableStatement callstmt;
	Statement stmt,stmt1,stmt_gur;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs_gur;
	public String m_chksql,m_client_code,m_client_name,m_client_type,m_type_desc,m_agrmnt_type,m_agrmnt_desc;
	ServletOutputStream out = null;
	int m_appno_count=0,m_inv_status=0;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			
			conn = m_sn_methods.met_user_validate(req); 
			
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			
			
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			String m_username 						= m_sn_methods.username;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
			String m_application_no="";
			
			
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
			nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
			nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			
			///	stmt1 = conn.createStatement ();
			
			String m_gur_type [];
			String m_gur_name [];
			String m_gur_code [];
			
			int b_flag_gur=0;
			String m_chksql = req.getParameter("chksql");
			String m_item_cat=""; //added by nuwan de silva on 04-09-04
			
			if(m_chksql.trim().equals("main_page")){
				
				
				m_application_no = req.getParameter("application_no");
				
				
				
				stmt = conn.createStatement ();
				stmt_gur = conn.createStatement ();
				
				
				rs= stmt.executeQuery (" SELECT  "+
					"	CLIENT_CODE,FULL_NAME,CLIENT_CATEGORY "+
					"	FROM "+
					"	"+m_schema_name+".AF_CO_MAS_CLIENT	"+
					//"  WHERE UPPER(CLIENT_CODE)= "+
					"  WHERE CLIENT_CODE= "+
					" (SELECT					 "+
					//"	    UPPER(CLIENT_CODE) "+
					"	    CLIENT_CODE "+
					"	FROM "+
					"			"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS		 "+
					"  WHERE APPLICATION_NO=UPPER('"+m_application_no+"')) ");
				
				
				boolean more=rs.next();
				if(more)
				{
					m_client_code=rs.getString(1);
					m_client_name=rs.getString(2);
					m_client_type=rs.getString(3);
				}
				
				rs= stmt.executeQuery (" SELECT  "+m_schema_name+".AF_CO_GET_PRO_INV_STATUS('"+m_application_no+"') FROM DUAL ");
				more=rs.next();
				if(more){
					m_inv_status=rs.getInt(1);
				}
				
				//added by nuwan de silva on 04-09-07===================================================
				rs.close();
				rs= stmt.executeQuery (" SELECT DISTINCT "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					//" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') ");
					" WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
				
				more=rs.next();
				if(more)
				{
					m_item_cat=rs.getString(1);
				}				
				
				//out.println("m_item_cat"+m_item_cat);
				//=======================================================================================
				
				
				String m_ind_guarant ="";
				
				rs= stmt.executeQuery (" SELECT A.GUARANTOR_CODE, a.application_no "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A, "+
					" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
					" "+m_schema_name+".AF_CO_MAS_CLIENT C "+
					//" WHERE upper(a.application_no)=upper(b.application_no) "+
					//" AND UPPER(c.CLIENT_CODE)=UPPER(A.GUARANTOR_CODE) "+
					" WHERE a.application_no=upper(b.application_no) "+
					" AND c.CLIENT_CODE=UPPER(A.GUARANTOR_CODE) "+
					" AND C.CLIENT_TYPE = 'I' "+
					" AND a.application_no =UPPER('"+m_application_no+"') ");
				
				more=rs.next();
				if(more)
				{
					m_ind_guarant = "TRUE";
				}else{
					m_ind_guarant = "FALSE";
				}				
				
				
				
				//Added by Chandana on 06/06/2007
				if(m_client_type.equals("INDIVIDUAL")){
					m_type_desc="Individual";
				}else{
					
					rs= stmt.executeQuery (" SELECT ENTITY_CODE,DESCRIPTION "+
						" FROM   "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY "+
						" WHERE  ENTITY_CODE='"+m_client_type+"' ");
					
					more=rs.next();
					if(more)
					{
						m_type_desc=rs.getString(2);
					}				
				}
				
				// added by udara 16-01-2019
				int m_delivery_order_count = 0;
				
				rs= stmt.executeQuery (" SELECT COUNT(APPLICATION_NO) "+
					" FROM   "+m_schema_name+".AF_CR_PRO_DELIVERY_ORD  "+
					" WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
				
				if(rs.next()){
					m_delivery_order_count = rs.getInt(1);
				}
				
				// end by udara 16-01-2019
				
				String m_application_status = ""; // added by udara 09-01-2019
				
				rs= stmt.executeQuery (" SELECT TRANSACTION_TYPE, "+
					" "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(TRANSACTION_TYPE), "+
					" APPLICATION_STATUS "+ // added by udara 01-09-2019
					" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
				
				more=rs.next();
				if(more)
				{
					m_agrmnt_type=rs.getString(1);
					m_agrmnt_desc=rs.getString(2);
					m_application_status = rs.getString(3); // added by udara 09-01-2019
				}	
				
				
				//End on 06/06/2007
				
				String m_lease_join_app="";
				
				rs= stmt.executeQuery (" SELECT APPLICATION_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE TRANSACTION_TYPE IN ('FINLEASE','OPELEASE','FIN_OTHER','FIN_LORRY') AND "+
					" CO_APPLICANT <>'-' AND "+
					" APPLICATION_NO ='"+m_application_no+"'");
				// " CLIENT_CODE ='' ");
				more=rs.next();
				if(more)
				{
					m_lease_join_app="TRUE";
				}else{
					m_lease_join_app="FALSE";
				}					
				
				
				String m_lease_join_app1="";
				
				rs= stmt.executeQuery (" SELECT APPLICATION_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE "+
					" CO_APPLICANT <>'-' AND "+
					" APPLICATION_NO ='"+m_application_no+"'");
				// " CLIENT_CODE ='' ");
				more=rs.next();
				if(more)
				{
					m_lease_join_app1="TRUE";
				}else{
					m_lease_join_app1="FALSE";
				}					
				
				
				
				
				
				
				rs_gur= stmt_gur.executeQuery ("SELECT  "+
					"	CLIENT_CATEGORY, "+
					"	INITCAP(FULL_NAME), "+	
					"	CLIENT_CODE "+	
					"	FROM "+
					"	"+m_schema_name+".AF_CO_MAS_CLIENT	"+
					//"  WHERE UPPER(CLIENT_CODE) IN "+
					"  WHERE CLIENT_CODE IN "+
					" (SELECT					 "+
					//"	    UPPER(GUARANTOR_CODE) "+
					"	    GUARANTOR_CODE "+
					"	FROM "+
					"			"+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR		 "+
					"  WHERE APPLICATION_NO=UPPER('"+m_application_no+"')  AND  ACTIVE_STATUS='Y') ");
				
				
				more=rs_gur.next();
				int i=0;
				int arr_size=0;
				int count_gur=0;
				m_gur_type = new String[100];
				m_gur_name = new String[100];
				m_gur_code = new String[100];
				
				while(more)
				{
					m_gur_type[arr_size]=rs_gur.getString(1);
					m_gur_name[arr_size]=rs_gur.getString(2);
					m_gur_code[arr_size]=rs_gur.getString(3);
					arr_size=arr_size+1;
					more=rs_gur.next();
				}
				
				//-----modified by : delanjali---------
				//-----date				: 2007-07-02---------
				double m_nibsm=0;
				
				rs= stmt.executeQuery (" SELECT NVL(NIBSM,0) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
					" WHERE "+
					//					 " CO_APPLICANT <>'-' AND "+
					//" UPPER(APPLICATION_NO) =UPPER('"+m_application_no+"')");
					" APPLICATION_NO =UPPER('"+m_application_no+"')");
				// " CLIENT_CODE ='' ");
				more=rs.next();
				if(more)
				{
					m_nibsm=rs.getDouble(1);
				}					
				

				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				
				out.println("var lineno=0;");
				out.println("var arr_size=0;");
				
				out.println("var m_sort_column='ENT_DATE';");
				out.println("var m_order_by_type='ASC';");
				
				out.println("function sort_data(m_sort_col) {");
				out.println("	 order_by_type = 'ASC'; ");  
				out.println("	 if(m_sort_col==m_sort_column){");
				out.println("	   if(m_order_by_type=='DESC'){");
				out.println("	      order_by_type = 'ASC'; ");  
				out.println("    }else{");
				out.println("       order_by_type = 'DESC'; ");
				out.println("    }");
				out.println("  }else{");
				out.println("       order_by_type = 'ASC'; ");
				out.println("  }");
				
				out.println("m_sort_column=m_sort_col;");
				out.println("m_order_by_type=order_by_type;");
				
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_approval&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"&ac_status=VERIFY\";");
				out.println("get_due_letters(document.Form1.TXT_CLIENT_CODE.value,m_sort_column,m_order_by_type);");
				//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Purchase_Order_Approval?chksql=main_page&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				// out.println(" window.location.href=m_url;"); 
				
				//	out.println("load_interface(m_url,'XML');");
				
				out.println("}");
				
				
				
				
				//!--------Display The Header -------------------------------------//
				out.println("function header(){");
				
				out.println("m_table.innerHTML=\"\" ");
				out.println("lineno=0;");
				out.println("arr_size=0;");
				
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">'+");
				out.println("'<td  width=\"12%\" style= cursor:hand; title=\"Click here to sort by - Invoice No  \"    onclick=sort_data(\"INVOICE_NO\") align=\"left\"><B>Invoice No</td>'+");
				out.println("'<td  width=\"12%\" style= cursor:hand; title=\"Click here to sort by - Finanace No  \"    onclick=sort_data(\"FINANCE_NO\") align=\"left\"><B>Finance No</td>'+");
				out.println("'<td  width=\"12%\" style= cursor:hand; title=\"Click here to sort by - Invoice Date  \"    onclick=sort_data(\"INVOICE_DATE\") align=\"left\"><B>Invoice Date</td>'+");
				//     out.println("'<td  width=\"10%\" align=\"left\"><B>Issued Date</td>'+");
				out.println("'<td  width=\"10%\" style= cursor:hand; title=\"Click here to sort by - No of Days Due  \"    onclick=sort_data(\"NO_OF_DAYS_DUE\") align=\"left\"><B>No of Days Due</td>'+");
				out.println("'<td  width=\"12%\" style= cursor:hand; title=\"Click here to sort by - Amount Due  \"    onclick=sort_data(\"AMOUNT_DUE\") align=\"right\"><B>Amount Due</td>'+");
				out.println("'<td  width=\"12%\" style= cursor:hand; title=\"Click here to sort by - Collection Officer  \"    onclick=sort_data(\"COLLECTION_OFFICER\") align=\"left\"><B>Collection Officer</td>'+");
				out.println("'<td  width=\"10%\" style= cursor:hand; title=\"Click here to sort by - Transaction Date  \"    onclick=sort_data(\"TRN_DATE\") align=\"left\"><B>Transaction Date</td>'+");
				out.println("'<td  width=\"10%\" style= cursor:hand; title=\"Click here to sort by - Receipt Not Allocated  \"    onclick=sort_data(\"RECEIPT_NOT_ALLO\") align=\"right\"><B>Receipt Not Allocated</td>'+");
				out.println("'<td  width=\"10%\" align=\"center\"><B>Status</td>'+");
				out.println("'</TR></table>';");		
				
				out.println("}");
				
				
				out.println("function change_val_req(row_no){")	;
				out.println("m_chk_required=\"CHK_REQUIRED\"+row_no;");
				
				
				
				out.println("if(document.Form1.elements[m_chk_required].checked==true){");
				out.println("document.Form1.elements[m_chk_required].value='on'");
				out.println("}else if(document.Form1.elements[m_chk_required].checked==false){");
				out.println("document.Form1.elements[m_chk_required].value='off'");
				out.println("}");	
				//out.println("alert(document.Form1.elements[m_chk_required].value);");
				out.println("}");	
				
				
				
				out.println("  function  display_data(data_vec){");
				
				
				out.println("header();	");		
				
				out.println("var i=0;");
				out.println("var j=0;");
				
				
				
				out.println("while(i<data_vec.length){");
				
				
				
				
				out.println("m_inv_no='<TD WIDTH=\"12%\" align=\"left\">'+data_vec[i]+'</TD>';");
				out.println("m_fin_no='<TD WIDTH=\"12%\" align=\"left\">'+data_vec[i+1]+'</TD>';");
				out.println("m_inv_date='<TD WIDTH=\"12%\" align=\"left\">'+data_vec[i+2]+'</TD>';");
				out.println("m_no_of_days='<TD WIDTH=\"10%\" align=\"left\">'+data_vec[i+3]+'</TD>';");
				out.println("m_amt_due='<TD WIDTH=\"12%\" align=\"right\">'+data_vec[i+4]+'</TD>';");
				out.println("m_col_officer='<TD WIDTH=\"12%\" align=\"left\">'+data_vec[i+5]+'</TD>';");		
				out.println("m_trn_date='<TD WIDTH=\"10%\" align=\"left\">'+data_vec[i+6]+'</TD>';");		
				out.println("m_receipt_not='<TD WIDTH=\"10%\" align=\"right\">'+data_vec[i+7]+'</TD>';");		
				//	out.println("m_pur_date='<TD WIDTH=\"10%\" align=\"center\">'+data_vec[i+8]+'</TD>';");		
				out.println("m_status='<TD WIDTH=\"10%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED'+lineno+' VALUE=\"\" onclick=\"change_val_req('+lineno+')\"></td>';");			
				
				
				
				out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_INVOICE_NO'+lineno+'	VALUE='+data_vec[i]+'>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_NO_OF_DAYS'+lineno+'	VALUE='+data_vec[i+3]+'>';");
				
				out.println("if(j>0 && j%2==1){");
				//       	out.println("<tr class=\"tr_input1\" >");
				out.println("m_writedata='<TR class=\"tr_input1\">'+m_inv_no+m_fin_no+m_inv_date+m_no_of_days+m_amt_due+m_col_officer+m_trn_date+m_receipt_not+m_status+'</TR>'+m_hid_input;"); 
				out.println("	}");
				out.println("	else{");
				//      	out.println("<tr class=\"tr_input\" >");
				out.println("m_writedata='<TR class=\"tr_input\">'+m_inv_no+m_fin_no+m_inv_date+m_no_of_days+m_amt_due+m_col_officer+m_trn_date+m_receipt_not+m_status+'</TR>'+m_hid_input;"); 
				out.println("	}");
				
				
				//  out.println("m_writedata='<TR>'+m_pur_no+m_app_no+m_ven_no+m_issued_date+m_dr_date+m_net_amt+m_tot_vat+m_pur_date+m_required+'</TR>'+m_hid_input;"); 
				
				
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
				out.println("m_writedata+'</table>';");
				
				out.println("j=j+1;");
				
				out.println("i=i+8;");
				out.println("lineno=lineno+1;");
				out.println("arr_size=arr_size+1;");		
				out.println("}"); //End while loop
				
				
				out.println("}");		
				
				
				
				
				
				//--------------------------------------------------------------------------		
				
				
				out.println("function makeRequest(obj) {");
				
				
				out.println("if(document.Form1.hid_chk_status.value=='M1')");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_client_code&data_val=\"+obj.value+\"&ac_status=Y\";");
				
				
				out.println("load_interface(m_url,'XML');");
				//		out.println("window.open(m_url)");
				
				out.println("}"); 
				
				
				out.println("function assignState(val){");
				out.println("document.Form1.hid_chk_status.value=val");
				out.println("}");
				
				
				out.println("function get_vector(data_vec) {");
				
				
				out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M3'){");
				out.println("				display_data(data_vec);");
				out.println("			}");
				
				out.println("else");
				out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M3'){");
				out.println("alert('No records available');");
				out.println("			}");
				
				out.println("else");
				out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M1'){");
				out.println("    document.Form1.TXT_CLIENT_CODE.value=data_vec[0];"); 
				out.println("get_due_letters(document.Form1.TXT_CLIENT_CODE.value,'ENT_DATE','DESC');");
				out.println("			}");
				
				out.println("else");
				out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M1'){");
				out.println("help_button_2();");
				out.println("			}");
				
				
				out.println("			}");
				
				
				out.println("function get_due_letters(val,m_sort_column,m_order_by_type){");
				//out.println("alert('approval');");
				out.println("assignState('M3');");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Due_Status&sort_column=\"+m_sort_column+\"&order_by_type=\"+m_order_by_type+\"&data_val=\"+val+\"&ac_status=Y\";");
				
				//	out.println("window.open(m_url);");
				out.println("load_interface(m_url,'XML');");
				
				out.println("}");	
				
				
				
				out.println("function validate_data(){"); 
				
				
				out.println("if(document.Form1.TXT_CLIENT_CODE.value==\"\"){  "); 
				out.println("DIV_TXT_CLIENT_CODE.style.color='red';");
				out.println("return false;"); 
				out.println("}"); 
				
				
				out.println("else{"); 
				out.println("return true;"); 
				out.println("}"); 
				
				
				out.println("}"); 
				
				out.println("function before_submit(){ "); 
				
				out.println("		if(validate_data()){"); 
				out.println("ckeck_issues();");
				out.println("if(b_flag==0)");
				out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
				out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
				out.println("		if(validate_data()){"); 
				out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("document.Form1.elements[i].disabled=false;");
				out.println("}");
				out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Collection_Due_Status';");  
				out.println("		document.Form1.submit();	"); 
				out.println("		}"); 
				out.println("		}"); 
				out.println("		}"); 
				out.println("else{");
				out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
				out.println("} "); 
				out.println("} "); 
				
				out.println("function load_lock(){	"); 
				out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Printing?chksql=main_page&application_no="+m_application_no+"';"); 
				out.println("		}"); 
				out.println("}"); 
				
				
				out.println("function close_screen(){	"); 
				out.println("		if(confirm(\"Are you sure you want to close the screen? \")){ "); 
				out.println("		window.close();"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Due_Status';"); 
				out.println("}"); 
				out.println(""); 
				out.println(""); 
				
				out.println("function save_window(){	"); 
				out.println("before_submit();"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_AF_CR_PRO_Document_Printing\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\" Documentation Process - Document Printing - \"+m_val;"); 
				//out.println("if(m_val==\"New\")");
				//out.println("document.Form1.hid_status.value=\"Save\";"); //Added By Nuwan De Silva
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Documentation Process - Document Printing - \"+document.Form1.hid_status.value;"); 
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
				
				//out.println("window.open('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_CO_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=');");
				out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
				out.println("	if(oBj.valout[1] ==\" \"){"); 
				out.println("	clear_data(IfCount);");
				out.println("	}else");
				
				
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				
				
				
				
				out.println("		if(IfCount==\"2\"){"); 
				out.println("		help_value_assign_2();"); 
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
				out.println("	clear_data(IfCount);");//Added To The Clear The Area Code
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
				
				
				out.println("function clear_data(IfCount) {");
				
				out.println("		if(IfCount==\"2\"){"); 
				out.println("document.Form1.TXT_CLIENT_CODE.value='';");
				//	out.println("document.Form1.TXT_TEMP_REC_NO.focus();");
				out.println("	}");
				out.println("	}");
				
				
				out.println("function help_button_2() {"); 
				//	out.println("    document.Form1.hid_help_type.value=\"2\";"); 
				//	out.println("    m_sql = \"TXT_CLIENT_CODE\";"); 
				//	out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
				//out.println("    HelpBox('1','10','0');"); 
				
				out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
				
				out.println("    HelpBox('1','10','2',Crit,'m_help_TXT_CLIENT_CODE','2');"); 
				
				
				out.println("}"); 
				out.println(""); 
				
				out.println("function help_value_assign_2() {"); 
				out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
				out.println("get_due_letters(document.Form1.TXT_CLIENT_CODE.value,'ENT_DATE','ASC');");
				
				out.println("}"); 
				
				
				
				out.println("function ckeck_issues(){ "); 
				
				out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
				out.println("if(m_table.innerHTML==\"\"){");
				out.println("alert('No records to save');");
				out.println("b_flag=1;");
				out.println("}"); 
				//out.println("else if(!count_offers()){"); 
				//out.println("alert('Please select an offer');");
				//out.println("b_flag=1;");
				//out.println("}"); 
				out.println("else");
				out.println("b_flag=0;");
				out.println("}"); 
				
				out.println("}"); 
				
				// added by udara 13-02-2019
				out.println("function disable_print_button(obj){");
				out.println(" obj.disabled=true; ");
				out.println("}"); 
				// added by udara 13-02-2019
				
				out.println("function load_documents(val){");
				
				out.println("m_application_no='"+m_application_no+"';");
				out.println("m_client_code='"+m_client_code+"';");
				
				out.println("if(val==\"2\"){");
				
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Acceptance_Receipt?chksql=main_page&application_no="+m_application_no+"&document_code=ACCEPT_REC&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				//	out.println("window.open(m_url)");
				out.println("}");
				
				
				
				out.println("else if(val=='3'){");
				
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Joint_Several_Guarantee?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"';"); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Joint_Several_Guarantee?chksql=main_page&application_no="+m_application_no+"&document_code=JOINT_GUAR&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=110,top=60,width=690,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				
				out.println("}");
				//added by nuwan de silva on 02-10-07-----------------
				out.println("else if(val=='33'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Joint_Several_Guarantee?chksql=select_guarantor&application_no="+m_application_no+"&document_code=JOINT_GUAR&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow2','left=110,top=60,width=600,height=300,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("else if(val=='4'){");
				
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_First_Letter?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"';"); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_First_Letter?chksql=main_page&application_no="+m_application_no+"&document_code=FIRST_LETT&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("else if(val=='5'){");
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_NIBSM_Letter?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"';"); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_NIBSM_Letter?chksql=main_page&application_no="+m_application_no+"&document_code=NIBSM_LETT&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=690,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("else if(val=='6'){");
				
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Board_Resolution?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"';"); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Board_Resolution?chksql=main_page&application_no="+m_application_no+"&document_code=BOARD_RESL&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=690,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("else if(val=='7'){");
				
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Partners_Indemnity?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"';"); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Partners_Indemnity?chksql=main_page&application_no="+m_application_no+"&document_code=PARTN_INDE&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=720,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				
				out.println("}");
				
				out.println("else if(val=='8'){");
				
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Board_Resolution_Cooparate_Guarantee?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=700,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("else if(val=='9'){");
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Lease_Schedule?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&client_type="+m_client_type+"';"); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Lease_Schedule?chksql=main_page&application_no="+m_application_no+"&document_code=LEASE_SCHE&print=TRUE&client_code="+m_client_code+"&client_type="+m_client_type+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=690,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("else if(val=='10'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Corporate_Guarantee?chksql=main_page&application_no="+m_application_no+"&document_code=CORATE_GUR&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=690,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("else if(val=='11'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Hirepur_Board_Resolution?chksql=main_page&application_no="+m_application_no+"&document_code=HP_RESIL&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=690,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("else if(val=='12'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Hirepur_ind_delivery_order?chksql=main_page&application_no="+m_application_no+"&document_code=HP_DELOD&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=690,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("else if(val=='13'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_cash_price_of_goods?chksql=main_page&application_no="+m_application_no+"&document_code=CASH_PRIC&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
				out.println("}");
				
				out.println("else if(val=='14'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_hirepurchase_shedule?chksql=main_page&application_no="+m_application_no+"&document_code=HP_SHEDUL&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');");
				out.println("}");
				
				out.println("else if(val=='15'){");
				//	out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_hirepurchase_shedule?chksql=shedule1&application_no="+m_application_no+"&document_code=ACCEPT_REC&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Hirepur_stamp_duty?chksql=main_page&application_no="+m_application_no+"&document_code=HP_STAMP&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
				out.println("}");
				
				out.println("else if(val=='16'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Noobject_and_aware_Let?chksql=main_page&application_no="+m_application_no+"&document_code=NIBSM_LETT&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=720,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
				out.println("}");
				
				out.println("else if(val=='17'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Noobject_and_aware_LetII?chksql=main_page&application_no="+m_application_no+"&document_code=JAPP_NBJII&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
				out.println("}");
				
				out.println("else if(val=='18'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Noobject_join_applicant?chksql=main_page&application_no="+m_application_no+"&document_code=JAPP_NOOBJ&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
				out.println("}");
				
				out.println("else if(val=='19'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_lease_ind_stamp_duty?chksql=main_page&application_no="+m_application_no+"&document_code=IND_STAMP&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
				out.println("}");
				
				out.println("else if(val=='20'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_lease_ind_Letter?chksql=main_page&application_no="+m_application_no+"&document_code=LEASE_IND&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
				out.println("}");
				
				out.println("else if(val=='21'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_lease_ind_Letter?chksql=main_page&application_no="+m_application_no+"&document_code=LEASE_IND&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
				out.println("}");
				
				out.println("else if(val=='22'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_RMV_Registration_Certificate?chksql=main_page&application_no="+m_application_no+"&document_code=LEASE_IND&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');");
				out.println("}");
				
				out.println("else if(val=='23'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Discrepancies_Letter?chksql=main_page&application_no="+m_application_no+"&document_code=DISCR_LET&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
				out.println("}");
				
				out.println("else if(val=='24'){");			//Added By Sandun on 26-09-2008
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Vari_Lease_Schedule?chksql=main_page&application_no="+m_application_no+"&document_code=LEASE_VAR&print=TRUE&client_code="+m_client_code+"&client_type="+m_client_type+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=690,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("else if(val=='25'){");			//Added By Sandun on 19-11-2008
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Asset_Replace_1?chksql=main_page&application_no="+m_application_no+"&document_code=LEASE_VAR&print=TRUE&client_code="+m_client_code+"&client_type="+m_client_type+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=690,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
				out.println("}");
				out.println("else if(val=='26'){");			//Added By Sandun on 19-11-2008
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Asset_Replace_2?chksql=main_page&application_no="+m_application_no+"&document_code=LEASE_VAR&print=TRUE&client_code="+m_client_code+"&client_type="+m_client_type+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=690,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
				out.println("}");
				out.println("else if(val=='27'){");			//Added By Sandun on 219-11-2008
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Asset_Replace_3?chksql=main_page&application_no="+m_application_no+"&document_code=LEASE_VAR&print=TRUE&client_code="+m_client_code+"&client_type="+m_client_type+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=690,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				//Added by Prabash on 05-04-2012----**
				out.println("else if(val=='28'){");	
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Rental_letter_summery?chksql=main_page&application_no="+m_application_no+"&document_code=RENT_LETT&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
				out.println("}");
				//-----------------------------------**
				//Added by Prabash on 10-05-2012----**
				out.println("else if(val=='29'){");	
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Reminder_blue_letter?chksql=main_page&application_no="+m_application_no+"&document_code=BLUE_LETT&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("else if(val=='30'){");	
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Reminder_red_letter?chksql=main_page&application_no="+m_application_no+"&document_code=RED_LETT&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("else if(val=='31'){");	
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Ceasing_order_letter?chksql=main_page&application_no="+m_application_no+"&document_code=CEAS_ORDER&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("else if(val=='32'){");	
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Deletion_letter?chksql=main_page&application_no="+m_application_no+"&document_code=DELE_LETT&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				// added by udara 12-11-2013
				out.println("else if(val=='34'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_First_Letter_sinhala?chksql=main_page&application_no="+m_application_no+"&document_code=FIRST_LETT&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
				out.println("}");
				// end by udara 12-11-2013
				
				
				
				// ADDED BY SAJITH MENDIS 25/11/2013
				out.println("else if(val=='40'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_sinhala_letter_1?chksql=main_page&application_no="+m_application_no+"&document_code=FIRST_LETT&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("else if(val=='41'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_sinhala_letter_2?chksql=main_page&application_no="+m_application_no+"&document_code=FIRST_LETT&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("else if(val=='42'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_sinhala_letter_3?chksql=main_page&application_no="+m_application_no+"&document_code=FIRST_LETT&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
				out.println("}");
				//END BY SAJITH MENDIS ON 25/11/2013
				
				//-----------------------------------**
				
				// ADDED BY Minal 05-01-2015 for #15195
				out.println("else if(val=='43'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_sinhala_letter_4?chksql=main_page&application_no="+m_application_no+"&document_code=FIRST_LETT&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("else if(val=='44'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_sinhala_letter_5?chksql=main_page&application_no="+m_application_no+"&document_code=FIRST_LETT&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("else if(val=='45'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_sinhala_letter_6?chksql=main_page&application_no="+m_application_no+"&document_code=FIRST_LETT&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
				out.println("}");
			
				out.println("else if(val=='46'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_sinhala_letter_7?chksql=main_page&application_no="+m_application_no+"&document_code=FIRST_LETT&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
				out.println("}");
				//-----------------------------------------------------------------------------------
				
				// added by udara 12-10-2018
				out.println("else if(val=='47'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Offer_Letter?chksql=main_page&application_no="+m_application_no+"&document_code=FIRST_LETT&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
				out.println("}");
				// end by udara 12-10-2018
				
				// added by udara 09-01-2019
				out.println("else if(val=='48'){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_New_Delivery_order?chksql=main_page&application_no="+m_application_no+"&document_code=DEL_ORD&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
				out.println("}");
				// end by udara 09-01-2019
				
				
				out.println("}"); 
				
				out.println("function load_guarantor(m_gur_name,m_gur_code){"); 
				
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Corporate_Guarantee?chksql=main_page&application_no="+m_application_no+"&document_code=CORATE_GUR&gur_name='+m_gur_name+'&print=TRUE&client_code="+m_client_code+"';"); 
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Hirepur_Board_Resolution?chksql=main_page&application_no="+m_application_no+"&document_code=BOARD_RESL&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("window.open(m_url,'displayWindow3','left=110,top=60,width=690,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				// out.println("window.open(m_url)");
				out.println("}"); 
				
				
				
				
				out.println("function load_resolution(m_gur_code){"); 
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Board_Resolution_Cooparate_Guarantee?chksql=main_page&application_no="+m_application_no+"&document_code=NIBSM_LETT&print=TRUE&client_code="+m_client_code+"';"); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Board_Resolution_Cooparate_Guarantee?chksql=main_page&application_no="+m_application_no+"&document_code=RESOLU_COR&print=TRUE&gur_code='+m_gur_code+'';"); 
				out.println("window.open(m_url,'displayWindow3','left=110,top=60,width=690,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				// out.println("window.open(m_url)");
				out.println("}"); 
				
				
				out.println("</Script>");
				
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"\">"); //load_lock()
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_DUE_STATUS\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
				
				
				
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Documentation Process - Document Printing - New</td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
				out.println("<td width='10%'></td>");  
				out.println("<td width='10%'></td>");  
				out.println("<td width='10%'></td>");  
				out.println("<td width='6%'></td>");  
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
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
				
				
				out.println("<table align='center' width='100%' class='table'>"); 
				
				out.println("<tr>"); 
				out.println("<td width='30%' >Application Number</td>"); 
				out.println("<td width='30%' >"+m_application_no+"</td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr>"); 
				out.println("<td width='30%' >Client Code </td>"); 
				out.println("<td width='30%' >"+m_client_code+"</td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr>"); 
				out.println("<td width='30%' >Client Name </td>"); 
				out.println("<td width='30%' >"+m_client_name+"</td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr>"); 
				out.println("<td width='30%' >Client Type </td>"); 
				out.println("<td width='30%' >"+m_type_desc+"</td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				
				out.println("<tr>"); 
				out.println("<td width='30%' >Transaction Type </td>");  //added by nuwan de silva on 04-09-07
				out.println("<td width='30%' ><b>"+m_agrmnt_desc+"</td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				
				
				out.println("</table>");
				
				out.println("<br>"); 
				out.println("<hr>"); 
				out.println("<br>"); 
				
				
				if(m_application_status.equals("VERIFY-M")){
					
						out.println("<table align='center' width='100%' class='table'>"); 
						
						out.println("<tr >"); 
						out.println("<td width='5%' ><b>Document Number </td>"); 
						out.println("<td width='30%' ><b>Document Name </td>"); 
						out.println("<td width='30%' ><b>Letter </td>"); 
						out.println("</tr>"); 
						
						if(m_delivery_order_count>0){
						
							out.println("<tr >"); 
							out.println("<td width='5%' >35</td>"); 
							out.println("<td width='30%' >Delivery Order </td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT_DEL_ORDER' id='BUT_PRINT_DEL_ORDER' value=\"Print\" onClick=\"load_documents('48');disable_print_button(this);\" disabled ></td>"); 
							out.println("</tr>");
							
						}
						else{
							
							out.println("<tr >"); 
							out.println("<td width='5%' >35</td>"); 
							out.println("<td width='30%' >Delivery Order </td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT_DEL_ORDER' id='BUT_PRINT_DEL_ORDER' value=\"Print\" onClick=\"load_documents('48');disable_print_button(this);\"></td>"); 
							out.println("</tr>");
							
						}
						
						out.println("</table>"); 
					
				}
				
				else if(m_application_status.equals("VERIFY2")){
					
						out.println("<table align='center' width='100%' class='table'>"); 
					
						out.println("<tr >"); 
						out.println("<td width='5%' ><b>Document Number </td>"); 
						out.println("<td width='30%' ><b>Document Name </td>"); 
						out.println("<td width='30%' ><b>Letter </td>"); 
						out.println("</tr>"); 
					
						if(m_delivery_order_count>0){
						
							out.println("<tr >"); 
							out.println("<td width='5%' >35</td>"); 
							out.println("<td width='30%' >Delivery Order </td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT_DEL_ORDER' id='BUT_PRINT_DEL_ORDER' value=\"Print\" onClick=\"load_documents('48');disable_print_button(this);\" disabled ></td>"); 
							out.println("</tr>");
							
						}
						else{
							
							out.println("<tr >"); 
							out.println("<td width='5%' >35</td>"); 
							out.println("<td width='30%' >Delivery Order </td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT_DEL_ORDER' id='BUT_PRINT_DEL_ORDER' value=\"Print\" onClick=\"load_documents('48');disable_print_button(this);\"></td>"); 
							out.println("</tr>");
							
						}
						
						out.println("</table>"); 
					
				}
				
				else{ // added by udara 14-01-2019
				
						out.println("<table align='center' width='100%' class='table'>"); 
						
						out.println("<tr >"); 
						out.println("<td width='5%' ><b>Document Number </td>"); 
						out.println("<td width='30%' ><b>Document Name </td>"); 
						out.println("<td width='30%' ><b>Letter </td>"); 
						out.println("</tr>"); 
						//################################################################################################################################################
						//----modified by : delanjali----------------------------------------------------------------------------------------------------------------------
						//----date:2007-06-22-------------------------------------------------------------------------------------------------------------------------------
						if(m_agrmnt_type.equals("FINLEASE") || m_agrmnt_type.trim().equals("OPELEASE") || m_agrmnt_type.trim().equals("FIN_OTHER") || m_agrmnt_type.trim().equals("FIN_LORRY") ){ //FIN_LORRY ADDED BY NS ON 29-04-2011
							out.println("<tr >");  
							out.println("<td width='5%' >01</td>"); 
							out.println("<td width='30%' >Lease Agreement</td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('1')\" disabled ></td>");  //modified by nuwan de silva on 19-09-07
							out.println("</tr>"); 
						}
						//-------------------------------------------------------------------------------------------------------------------------------------------------
						//----modified by : delanjali----------------------------------------------------------------------------------------------------------------------
						//----date:2007-06-22-------------------------------------------------------------------------------------------------------------------------------
						if(m_agrmnt_type.equals("HIREPURCH")){
							out.println("<tr >"); 
							out.println("<td width='5%' >01</td>"); 
							out.println("<td width='30%' >Lease Agreement</td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('1')\" disabled></td>"); 
							out.println("</tr>"); 
						}
						//-------------------------------------------------------------------------------------------------------------------------------------------------
						//################################################################################################################################################
						//-------------------------------------------------------------------------------------------------------------------------------------------------
						//----modified by : delanjali----------------------------------------------------------------------------------------------------------------------
						//----date:2007-06-22-------------------------------------------------------------------------------------------------------------------------------
						if(m_agrmnt_type.equals("FINLEASE") || m_agrmnt_type.trim().equals("OPELEASE") || m_agrmnt_type.trim().equals("FIN_OTHER") || m_agrmnt_type.trim().equals("FIN_LORRY") ){
							
							
							out.println("<tr >"); 
							out.println("<td width='5%' >02</td>"); 
							out.println("<td width='30%' >Acceptance Receipt</td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('2')\"></td>"); 
							out.println("</tr>"); 
						}
						//-------------------------------------------------------------------------------------------------------------------------------------------------
						//----modified by : delanjali----------------------------------------------------------------------------------------------------------------------
						//----date:2007-06-22-------------------------------------------------------------------------------------------------------------------------------
						if(m_agrmnt_type.equals("HIREPURCH")){
							
							
							out.println("<tr >"); 
							out.println("<td width='5%' >02</td>"); 
							out.println("<td width='30%' >Acceptance Receipt</td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('2')\" disabled></td>"); 
							out.println("</tr>"); 
						}
						//-------------------------------------------------------------------------------------------------------------------------------------------------
						//################################################################################################################################################
						//----modified by : delanjali----------------------------------------------------------------------------------------------------------------------
						//----date:2007-06-22-------------------------------------------------------------------------------------------------------------------------------
						if(m_agrmnt_type.equals("FINLEASE") || m_agrmnt_type.trim().equals("OPELEASE") || m_agrmnt_type.trim().equals("FIN_OTHER") || m_agrmnt_type.trim().equals("FIN_LORRY") ){
							
							if(m_ind_guarant.equals("TRUE")){
								out.println("<tr >"); 
								out.println("<td width='5%' >03</td>"); 
								out.println("<td width='30%' ><b><u>Joint And Several Guarantee</td>"); 
								//out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('3')\"></td>"); 
								out.println("</tr>"); 
								
								out.println("<tr >"); 
								out.println("<td width='5%' >&nbsp;</td>"); 
								out.println("<td width='30%' ><li>&nbsp;&nbsp;All Guarantor</td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('3')\" ></td>"); 
								out.println("</tr>"); 
								out.println("<tr >"); 
								out.println("<td width='5%' >&nbsp;</td>"); 
								out.println("<td width='30%' ><li>&nbsp;&nbsp;Individual Guarantor</td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('33')\" ></td>"); 
								out.println("</tr>"); 
								
								
							}else{
								out.println("<tr >"); 
								out.println("<td width='5%' >03</td>"); 
								out.println("<td width='30%' ><b><u>Joint And Several Guarantee</td>"); 
								//out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('3')\" disabled></td>"); 
								out.println("</tr>"); 
								
								out.println("<tr >"); 
								out.println("<td width='5%' >&nbsp;</td>"); 
								out.println("<td width='30%' ><li>&nbsp;&nbsp;All Guarantor</td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('3')\" disabled></td>"); 
								out.println("</tr>"); 
								out.println("<tr >"); 
								out.println("<td width='5%' >&nbsp;</td>"); 
								out.println("<td width='30%' ><li>&nbsp;&nbsp;Individual Guarantor</td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('33')\" disabled></td>"); 
								out.println("</tr>"); 
								
							}
							
						}
						
						//----modified by : delanjali----------------------------------------------------------------------------------------------------------------------
						//----date:2007-06-22-------------------------------------------------------------------------------------------------------------------------------
						if(m_agrmnt_type.equals("HIREPURCH")){
							
							if(m_ind_guarant.equals("TRUE")){
								out.println("<tr >"); 
								out.println("<td width='5%' >03</td>"); 
								out.println("<td width='30%' ><b><u>Joint And Several Guarantee</td>"); 
								//out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('3')\" disabled></td>"); 
								out.println("</tr>"); 
								
								out.println("<tr >"); 
								out.println("<td width='5%' >&nbsp;</td>"); 
								out.println("<td width='30%' ><li>&nbsp;&nbsp;All Guarantor</td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('3')\" disabled></td>"); 
								out.println("</tr>"); 
								out.println("<tr >"); 
								out.println("<td width='5%' >&nbsp;</td>"); 
								out.println("<td width='30%' ><li>&nbsp;&nbsp;Individual Guarantor</td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('33')\" disabled></td>"); 
								out.println("</tr>"); 
								
							}else{
								out.println("<tr >"); 
								out.println("<td width='5%' >03</td>"); 
								out.println("<td width='30%' ><b><u>Joint And Several Guarantee</td>"); 
								//out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('3')\" disabled></td>"); 
								out.println("</tr>"); 
								
								out.println("<tr >"); 
								out.println("<td width='5%' >&nbsp;</td>"); 
								out.println("<td width='30%' ><li>&nbsp;&nbsp;All Guarantor</td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('3')\" disabled></td>"); 
								out.println("</tr>"); 
								out.println("<tr >"); 
								out.println("<td width='5%' >&nbsp;</td>"); 
								out.println("<td width='30%' ><li>&nbsp;&nbsp;Individual Guarantor</td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('33')\" disabled></td>"); 
								out.println("</tr>"); 
							}
							
						}
						
						//################################################################################################################################################
						
					
						//################################################################################################################################################
						//----modified by : delanjali----------------------------------------------------------------------------------------------------------------------
						//----date:2007-06-22-------------------------------------------------------------------------------------------------------------------------------
						if(m_agrmnt_type.equals("FINLEASE") || m_agrmnt_type.trim().equals("OPELEASE") || m_agrmnt_type.trim().equals("FIN_OTHER") || m_agrmnt_type.trim().equals("FIN_LORRY") ){
							
							out.println("<tr >"); 
							out.println("<td width='5%' >04</td>"); 
							out.println("<td width='30%' >First Letter</td>"); 
							//out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('4')\"></td>"); // commented by udara 12-11-2013
							
							// added by udara 12-11-2013
							out.println("<td width='30%' >");
						    out.println("   <input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('4')\">");
					        //out.println("   <input class='but_input' type='button' name='BUT_PRINT' value=\"Sinhala\" onClick=\"load_documents('34')\">");
							out.println("</td>"); 
							// end by udara 12-11-2013
							
							out.println("</tr>"); 
							
							
							out.println("<tr >"); 
							out.println("<td width='5%' > &nbsp; </td>"); 
							out.println("<td width='30%' >First Letter Sinhala</td>"); 
							//out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('4')\"></td>"); // commented by udara 12-11-2013
							
							// added by udara 12-11-2013
							out.println("<td width='30%' >");
						    //out.println("   <input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('4')\">");
					        out.println("   <input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('34')\">");
							out.println("</td>"); 
							// end by udara 12-11-2013
							
							out.println("</tr>");
							
							
						}
						//--------------------------------------------------------------------------------------------------------------------------------------------------
						
						//----modified by : delanjali----------------------------------------------------------------------------------------------------------------------
						//----date:2007-06-22-------------------------------------------------------------------------------------------------------------------------------
						if(m_agrmnt_type.equals("HIREPURCH")){
							
							out.println("<tr >"); 
							out.println("<td width='5%' >04</td>"); 
							out.println("<td width='30%' >First Letter</td>"); 
							//out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('4')\" ></td>"); // commented by udara 12-11-2013
							
							// added by udara 12-11-2013
							out.println("<td width='30%' >");
						    out.println("   <input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('4')\">");
					        //out.println("   <input class='but_input' type='button' name='BUT_PRINT' value=\"Sinhala\" onClick=\"load_documents('34')\">");
							out.println("</td>"); 
							// end by udara 12-11-2013
							
							out.println("</tr>"); 
							
							
							
							out.println("<tr >"); 
							out.println("<td width='5%' >&nbsp; </td>"); 
							out.println("<td width='30%' >First Letter Sinhala</td>"); 
							//out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('4')\" ></td>"); // commented by udara 12-11-2013
							
							// added by udara 12-11-2013
							out.println("<td width='30%' >");
						    //out.println("   <input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('4')\">");
					        out.println("   <input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('34')\">");
							out.println("</td>"); 
							// end by udara 12-11-2013
							
							out.println("</tr>"); 
							
							
							
						}
						//--------------------------------------------------------------------------------------------------------------------------------------------------
						//################################################################################################################################################
						//----modified by : delanjali----------------------------------------------------------------------------------------------------------------------
						//----date:2007-06-22-------------------------------------------------------------------------------------------------------------------------------
						if(m_agrmnt_type.equals("FINLEASE") || m_agrmnt_type.trim().equals("OPELEASE") || m_agrmnt_type.trim().equals("FIN_OTHER") || m_agrmnt_type.trim().equals("FIN_LORRY") ){
							if(m_nibsm!=0){
								out.println("<tr >"); 
								out.println("<td width='5%' >05</td>"); 
								out.println("<td width='30%' >NIBSM letter with Right of Set Off </td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('5')\"></td>"); 
								out.println("</tr>"); 
							}
							if(m_nibsm==0){
								out.println("<tr >"); 
								out.println("<td width='5%' >05</td>"); 
								out.println("<td width='30%' >NIBSM letter with Right of Set Off </td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('5')\" disabled></td>"); 
								out.println("</tr>"); 
							}
							
						}
						
						//----modified by : delanjali----------------------------------------------------------------------------------------------------------------------
						//----date:2007-06-22-------------------------------------------------------------------------------------------------------------------------------
						if(m_agrmnt_type.equals("HIREPURCH")){
							
							out.println("<tr >"); 
							out.println("<td width='5%' >05</td>"); 
							out.println("<td width='30%' >NIBSM letter with Right of Set Off </td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('5')\" disabled></td>"); 
							out.println("</tr>"); 
						}
						//--------------------------------------------------------------------------------------------------------------------------------------------------
						//################################################################################################################################################
						
						//----modified by : delanjali----------------------------------------------------------------------------------------------------------------------
						//----date:2007-06-22-------------------------------------------------------------------------------------------------------------------------------
						if(m_agrmnt_type.equals("FINLEASE") || m_agrmnt_type.trim().equals("OPELEASE") || m_agrmnt_type.trim().equals("FIN_OTHER") || m_agrmnt_type.trim().equals("FIN_LORRY") ){
							
							
							if(m_client_type.trim().equals("CORPORATE")||m_client_type.trim().equals("LIMITED")||m_client_type.trim().equals("PUBLIC")) 
							{
								out.println("<tr >"); 
								out.println("<td width='5%' >06</td>"); 
								out.println("<td width='30%' >Board Resolution</td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('6')\"></td>"); 
								out.println("</tr>"); 
							}
							
							else{
								out.println("<tr >"); 
								out.println("<td width='5%' >06</td>"); 
								out.println("<td width='30%' >Board Resolution</td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" disabled></td>"); 
								out.println("</tr>"); 
								
							}
						}
						if(m_agrmnt_type.equals("HIREPURCH")){
							
							
							
							out.println("<tr >"); 
							out.println("<td width='5%' >06</td>"); 
							out.println("<td width='30%' >Board Resolution</td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" disabled></td>"); 
							out.println("</tr>"); 
							
							//}
						}
						//################################################################################################################################################
						
						//----modified by : delanjali----------------------------------------------------------------------------------------------------------------------
						//----date:2007-06-22-------------------------------------------------------------------------------------------------------------------------------
						if(m_agrmnt_type.equals("FINLEASE") || m_agrmnt_type.trim().equals("OPELEASE") || m_agrmnt_type.trim().equals("FIN_OTHER") || m_agrmnt_type.trim().equals("FIN_LORRY") ){
							
							
							if(m_client_type.trim().equals("PARTNERS")){ //Added by Chandana on 06/06/2007
								out.println("<tr >"); 
								out.println("<td width='5%' >07</td>"); 
								out.println("<td width='30%' >Partners Indemnity</td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('7')\"></td>"); 
								out.println("</tr>"); 					
							}else
							{
								
								out.println("<tr >"); 
								out.println("<td width='5%' >07</td>"); 
								out.println("<td width='30%' >Partners Indemnity</td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" disabled></td>"); 
								out.println("</tr>"); 
								
							}
						}
						//------------------------------------------------------------------------------------------------------------------------------
						if(m_agrmnt_type.equals("HIREPURCH")){
							
							
							if(m_client_type.trim().equals("PARTNERS")){ //Added by Chandana on 06/06/2007
								out.println("<tr >"); 
								out.println("<td width='5%' >07</td>"); 
								out.println("<td width='30%' >Partners Indemnity</td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('7')\"></td>"); 
								out.println("</tr>"); 					
							}
							else
							{
								
								out.println("<tr >"); 
								out.println("<td width='5%' >07</td>"); 
								out.println("<td width='30%' >Partners Indemnity</td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" disabled></td>"); 
								out.println("</tr>"); 
								
							}
						}
						//------------------------------------------------------------------------------------------------------------------------------
						//################################################################################################################################################
						
						
						
						if(m_agrmnt_type.equals("FINLEASE") || m_agrmnt_type.trim().equals("OPELEASE") || m_agrmnt_type.trim().equals("FIN_OTHER") || m_agrmnt_type.trim().equals("FIN_LORRY") ){
							
							out.println("<tr >"); 
							//out.println("<td width='5%' >08</td>"); 
							out.println("<td width='5%' >&nbsp</td>"); 
							
							out.println("<td width='30%' ><b><u>Resolution for corporate guarantee</td>"); 
							//out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('10')\" disabled></td>"); 
							out.println("</tr>"); 
							//	out.println("</table>");
							
							//out.println("<tr>"); 									
							
							out.println("<table align='center' width='100%' class='table'>"); 
							
							i=0;
							int j=1;
							
							while(i<arr_size)
							{
								//if(m_gur_type[i].equals("LIMITED")){
								if(!m_gur_type[i].trim().equals("INDIVIDUAL"))
								{
									
									out.println("<tr>");  
									out.println("<td width='10%' >&nbsp;</td>"); 
									out.println("<td width='5%' >"+j+"</td>"); 
									out.println("<td width='20%' >"+m_gur_name[i]+"</td>");  
									out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_resolution('"+m_gur_code[i]+"')\"></td>"); 
									out.println("</tr>"); 
									
									j=j+1;
								}
								i=i+1;
								
							}
							out.println("</table>");	
						}
						if(m_agrmnt_type.trim().equals("HIREPURCH")){
							out.println("<tr >"); 
							//out.println("<td width='5%' >08</td>"); 
							out.println("<td width='5%' >&nbsp</td>"); 
							
							out.println("<td width='30%' ><b><u>Resolution for corporate guarantee</td>"); 
							//out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('10')\" disabled></td>"); 
							out.println("</tr>"); 
							
							//------ Added by Chandana 06-09-2007 ----------------------//
							
							out.println("<table align='center' width='100%' class='table'>"); 
							
							i=0;
							int j=1;
							
							while(i<arr_size)
							{
								//if(m_gur_type[i].equals("LIMITED")){
								if(!m_gur_type[i].trim().equals("INDIVIDUAL"))
								{
									
									out.println("<tr>");  
									out.println("<td width='10%' >&nbsp;</td>"); 
									out.println("<td width='5%' >"+j+"</td>"); 
									out.println("<td width='20%' >"+m_gur_name[i]+"</td>");  
									out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_resolution('"+m_gur_code[i]+"')\"></td>"); 
									out.println("</tr>"); 
									
									j=j+1;
								}
								i=i+1;
								
							}
							out.println("</table>");	
							
							
							//------ End on 06-09-2007 -----------------//
							
							
						}	
						// out.println("</tr>"); 
						
						out.println("<table align='center' width='100%' class='table'>");
						
						//################################################################################################################################################
						//----modified by : delanjali----------------------------------------------------------------------------------------------------------------------
						//----date:2007-06-22-------------------------------------------------------------------------------------------------------------------------------
						if(m_agrmnt_type.equals("FINLEASE") || m_agrmnt_type.trim().equals("OPELEASE") || m_agrmnt_type.trim().equals("FIN_OTHER") || m_agrmnt_type.trim().equals("FIN_LORRY") ){
							
							String m_interest_rate="";
							rs= stmt.executeQuery(" SELECT DISTINCT A.APPLICATION_NO,A.INTEREST_TYPE "+
								" FROM LAKDL.AF_CO_PRO_APP_PRICING A "+
								" WHERE A.APPLICATION_NO='"+m_application_no+"' ");//Added by Sandun on 02-10-2008
							
							
							more = rs.next();
							if(more){
								m_interest_rate = rs.getString(2);
							}				
							
							//	if(m_agrmnt_type.trim().equals("HIREPURCH")){
							
							out.println("<tr >"); 
							out.println("<td width='5%' >09</td>"); 
							out.println("<td width='30%' >Lease schedule</td>"); 
							if(m_interest_rate.trim().equals("FIXED")){
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('9')\" ></td>"); 
							}else if(m_interest_rate.trim().equals("VARIABLE")){
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('9')\" disabled></td>"); 
							}
							out.println("</tr>");
							
							out.println("<tr >"); //Added By Sandun on 26-09-2008
							out.println("<td width='5%' >10</td>"); 
							out.println("<td width='30%' >Variable Lease schedule</td>");
							if(m_interest_rate.trim().equals("FIXED")){
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('24')\" disabled></td>"); 
							}else if(m_interest_rate.trim().equals("VARIABLE")){
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('24')\" ></td>"); 
							}
							out.println("</tr>");
							
						}
						if(m_agrmnt_type.trim().equals("HIREPURCH")){
							out.println("<tr >"); 
							out.println("<td width='5%' >09</td>"); 
							out.println("<td width='30%' >Lease schedule</td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('9')\" disabled></td>"); 
							out.println("</tr>");					
						}
						
						//################################################################################################################################################
						if(m_agrmnt_type.equals("FINLEASE") || m_agrmnt_type.trim().equals("OPELEASE") || m_agrmnt_type.trim().equals("FIN_OTHER") || m_agrmnt_type.trim().equals("FIN_LORRY") ){
							out.println("<tr >"); 
							//	out.println("<td width='5%' >10</td>"); 
							out.println("<td width='5%' >&nbsp</td>"); 
							
							out.println("<td width='30%' ><b><u>Corporate Guarantee</td>"); 
							//out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('10')\" disabled></td>"); 
							out.println("</tr>"); 
							
		
							out.println("<table align='center' width='100%' class='table'>"); 
							
							i=0;
							int j=1;
							
							while(i<arr_size)
							{
								//if(m_gur_type[i].equals("LIMITED") || m_gur_type[i].equals("PUBLIC")){
								if(!m_gur_type[i].trim().equals("INDIVIDUAL"))
								{		
									out.println("<tr>");  
									out.println("<td width='10%' >&nbsp;</td>"); 
									out.println("<td width='5%' >"+j+"</td>"); 
									out.println("<td width='20%' >"+m_gur_name[i]+"</td>");  
									out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_guarantor('"+m_gur_name[i]+"','"+m_gur_code[i]+"')\"></td>"); 
									out.println("</tr>"); 
									
									j=j+1;
								}
								//}
								i=i+1;
								
							}
							
							out.println("</table>");
							
						}
						if(m_agrmnt_type.trim().equals("HIREPURCH")){
							
							out.println("<tr >"); 
							out.println("<td width='5%' >&nbsp</td>"); 
							out.println("<td width='30%' ><b><u>Corporate Guarantee</td>"); 
							//out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('10')\" disabled></td>"); 
							out.println("</tr>"); 
							
						}
						
						
						//============= Added by Chandana on 08-06-2007 ================	
						
						out.println("<table align='center' width='100%' class='table'>");
						//----modified by : delanjali----------------------------------------------------------------------------------------------------------------------
						//----date:2007-06-22-------------------------------------------------------------------------------------------------------------------------------
						//################################################################################################################################################
						if(m_agrmnt_type.trim().equals("HIREPURCH")){//////
							
							if(m_client_type.trim().equals("CORPORATE")||m_client_type.trim().equals("LIMITED")||m_client_type.trim().equals("PUBLIC"))
							{
								out.println("<tr >"); 
								out.println("<td width='5%' >11</td>"); 
								out.println("<td width='30%' >Lease purchase - Board resolution</td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('11')\" ></td>"); 
								out.println("</tr>"); 					
							}
							
							else { 
								out.println("<tr >"); 
								out.println("<td width='5%' >11</td>"); 
								out.println("<td width='30%' >Lease purchase - Board resolution</td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('11')\" disabled></td>"); 
								out.println("</tr>"); 
							}
						}
						
						if(m_agrmnt_type.equals("FINLEASE") || m_agrmnt_type.trim().equals("OPELEASE") || m_agrmnt_type.trim().equals("FIN_OTHER") || m_agrmnt_type.trim().equals("FIN_LORRY") ){
							out.println("<tr >"); 
							out.println("<td width='5%' >11</td>"); 
							out.println("<td width='30%' >Lease purchase - Board resolution</td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('11')\" disabled></td>"); 
							out.println("</tr>"); 
							
						}
						//################################################################################################################################################
						//------------------------------------------------------------------------------------
						if(m_agrmnt_type.trim().equals("HIREPURCH")){
							out.println("<td width='5%' >12</td>"); 
							out.println("<td width='30%' >Lease purchase - Cash price of vehicle</td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('13')\" ></td>"); 
							out.println("</tr>");
						}
						if(m_agrmnt_type.equals("FINLEASE") || m_agrmnt_type.trim().equals("OPELEASE") || m_agrmnt_type.trim().equals("FIN_OTHER") || m_agrmnt_type.trim().equals("FIN_LORRY") ){
							out.println("<td width='5%' >12</td>"); 
							out.println("<td width='30%' >Lease purchase - Cash price of vehicle</td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('13')\" disabled ></td>"); 
							out.println("</tr>");
							
						}
						
						
						
						//################################################################################################################################################
						
						//	if(m_agrmnt_type.equals("FINLEASE") || m_agrmnt_type.trim().equals("OPELEASE")){
						
						if(m_agrmnt_type.trim().equals("HIREPURCH")){
							
							out.println("<tr >");
							out.println("<td width='5%' >14</td>"); 
							out.println("<td width='30%' >Lease purchase - Stamp Duty  </td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('15')\" ></td>"); 
							out.println("</tr>");			
						}
						if(m_agrmnt_type.equals("FINLEASE") || m_agrmnt_type.trim().equals("OPELEASE") || m_agrmnt_type.trim().equals("FIN_OTHER") || m_agrmnt_type.trim().equals("FIN_LORRY") ){
							
							out.println("<tr >");
							out.println("<td width='5%' >14</td>"); 
							out.println("<td width='30%' >Lease purchase - Stamp Duty  </td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('15')\" disabled></td>"); 
							out.println("</tr>");			
						}
						
						//################################################################################################################################################
						
						if(m_agrmnt_type.trim().equals("HIREPURCH")){
							
							out.println("<tr >");
							out.println("<td width='5%' >13</td>"); 
							out.println("<td width='30%' >Lease purchase - Schedule </td>"); //- Shedule I 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('14')\" ></td>"); 
							out.println("</tr>");
						}
						if(m_agrmnt_type.equals("FINLEASE") || m_agrmnt_type.trim().equals("OPELEASE") || m_agrmnt_type.trim().equals("FIN_OTHER") || m_agrmnt_type.trim().equals("FIN_LORRY") ){
							
							out.println("<tr >");
							out.println("<td width='5%' >13</td>"); 
							out.println("<td width='30%' >Lease purchase - Schedule </td>"); //- Shedule I 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('14')\" disabled></td>"); 
							out.println("</tr>");
						}
						//################################################################################################################################################
						
						
						//-----------------------------------
						//if(m_type_desc.trim().equals("Individual")) 
						//{
						//if(m_agrmnt_type.trim().equals("HIREPURCH")){
						
						//################################################################################################################################################
						//if(m_lease_join_app.trim().equals("TRUE"))
						//{
						if((m_agrmnt_type.trim().equals("FINLEASE"))||(m_agrmnt_type.trim().equals("OPELEASE")) || m_agrmnt_type.trim().equals("FIN_OTHER") || m_agrmnt_type.trim().equals("FIN_LORRY") ){
							
							if(m_client_type.trim().equals("PARTNERS")){
								
								if(m_item_cat.equals("VEHICLE")){ //added by nuwan de silva on 04-09-07
									out.println("<tr >"); 
									out.println("<td width='5%' >15</td>"); 
									out.println("<td width='30%' >No objection Letter</td>"); 
									out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('16')\" ></td>"); 
									out.println("</tr>");
								}
								out.println("<tr >"); 
								out.println("<td width='5%' >16</td>"); 
								out.println("<td width='30%' >Awareness Letter </td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('17')\" ></td>"); 
								out.println("</tr>");
								
							}
							else{
								out.println("<tr >"); 
								out.println("<td width='5%' >15</td>"); 
								out.println("<td width='30%' >No Objection Letter</td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('16')\" disabled></td>"); 
								out.println("</tr>");					
								out.println("<tr >"); 
								out.println("<td width='5%' >16</td>"); 
								out.println("<td width='30%' >Awareness Letter </td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('17')\" disabled></td>"); 
								out.println("</tr>");				
								
							}
							
						}
						if(m_agrmnt_type.trim().equals("HIREPURCH")){
							if(m_client_type.trim().equals("PARTNERS")){
								
								if(m_item_cat.equals("VEHICLE")){ //added by nuwan de silva on 04-09-07
									out.println("<tr >"); 
									out.println("<td width='5%' >15</td>"); 
									out.println("<td width='30%' >No Objection Letter</td>"); 
									out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('16')\" ></td>"); 
									out.println("</tr>");
								}
								
								out.println("<tr >"); 
								out.println("<td width='5%' >16</td>"); 
								out.println("<td width='30%' >Awareness Letter </td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('17')\" ></td>"); 
								out.println("</tr>");
								
							}
							else{
								out.println("<tr >"); 
								out.println("<td width='5%' >15</td>"); 
								out.println("<td width='30%' >No Objection Letter</td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('16')\" disabled></td>"); 
								out.println("</tr>");	
								out.println("<tr >"); 
								out.println("<td width='5%' >16</td>"); 
								out.println("<td width='30%' >Awareness Letter </td>"); 
								out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('17')\" disabled></td>"); 
								out.println("</tr>");				
								
								
							}
							
						}
						//}
						
						
						
						//################################################################################################################################################
						
						
						
						//################################################################################################################################################
						
						if(m_lease_join_app1.equals("TRUE")){
							if(!m_client_type.trim().equals("CORPORATE") || !m_client_type.trim().equals("PARTNERS") || !m_client_type.trim().equals("LIMITED") || !m_client_type.trim().equals("INDIVIDUAL"))
							{
								
								if(m_item_cat.equals("VEHICLE")){ //added by nuwan de silva on 04-09-07
									out.println("<tr >"); 
									out.println("<td width='5%' >17</td>"); 
									out.println("<td width='30%' >Join Applicant - No objection Letter</td>"); 
									out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('18')\"></td>"); 
									out.println("</tr>");			
								}
								
								
							}
							
							
						}
						
						
						else{
							out.println("<tr >"); 
							out.println("<td width='5%' >17</td>"); 
							out.println("<td width='30%' >Join Applicant - No objection Letter</td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('18')\" disabled></td>"); 
							out.println("</tr>");										
							
							
						}
						
						
						
						//if(m_type_desc.trim().equals("Individual")) 
						//{
						if((m_agrmnt_type.trim().equals("FINLEASE"))||(m_agrmnt_type.trim().equals("OPELEASE")) || m_agrmnt_type.trim().equals("FIN_OTHER") || m_agrmnt_type.trim().equals("FIN_LORRY") ){
							out.println("<tr >"); 
							out.println("<td width='5%' >18</td>"); 
							out.println("<td width='30%' >Individual - Lease Stamp duty Letter</td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('19')\"></td>"); 
							out.println("</tr>");		
							
							
							//}
						}//else{
						if(m_agrmnt_type.trim().equals("HIREPURCH")){
							
							out.println("<tr >"); 
							out.println("<td width='5%' >18</td>"); 
							out.println("<td width='30%' >Individual - Lease Stamp duty Letter</td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('19')\" disabled></td>"); 
							out.println("</tr>");
															
						}
						
						if((m_agrmnt_type.trim().equals("FINLEASE"))||(m_agrmnt_type.trim().equals("OPELEASE")) || m_agrmnt_type.trim().equals("FIN_OTHER") || m_agrmnt_type.trim().equals("FIN_LORRY") ){
							
							
							
							out.println("<tr >"); 
							out.println("<td width='5%' >19</td>"); 
							out.println("<td width='30%' >ML Letter</td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('20')\"></td>"); 
							out.println("</tr>");
						}
						if(m_agrmnt_type.trim().equals("HIREPURCH")){
							
							out.println("<tr >"); 
							out.println("<td width='5%' >19</td>"); 
							out.println("<td width='30%' >ML Letter</td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('20')\" disabled></td>"); 
							out.println("</tr>");										
						}
						
						
						
						out.println("<tr >"); 
						out.println("<td width='5%' >20</td>"); 
						out.println("<td width='30%' >RMV Registration Certificate</td>"); 
						out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('22')\"></td>"); 
						out.println("</tr>");
						
						
						// added by nuwan de silva on 14-01-2008------------------------					
						
						if (m_inv_status >0) {
							out.println("<tr >"); 
							out.println("<td width='5%' >21</td>"); 
							out.println("<td width='30%' >Discrepancies Letter</td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('23')\" ></td>");
							out.println("</tr>");
						}
						else{
							out.println("<tr >"); 
							out.println("<td width='5%' >21</td>"); 
							out.println("<td width='30%' >Discrepancies Letter</td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('23')\" disabled ></td>");
							out.println("</tr>");
						}
						
						//================================================================================================
						
						out.println("<tr >"); 
						out.println("<td width='5%' >22</td>");  //Added By Sandun on 19-11-2008
						out.println("<td width='30%' ><b><u>Asset Replacement Documents</td>"); 					
						out.println("</tr>");
						
						out.println("<tr >"); 
						out.println("<td width='5%' ></td>");  
						out.println("<td width='30%' ><li>&nbsp;&nbsp;Document 1</td>"); 
						out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('25')\" ></td>");
						out.println("</tr>");
						out.println("<tr >"); 
						out.println("<td width='5%' ></td>");  
						out.println("<td width='30%' ><li>&nbsp;&nbsp;Document 2</td>"); 
						out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('26')\" ></td>");
						out.println("</tr>");
						out.println("<tr >"); 
						out.println("<td width='5%' ></td>");  
						out.println("<td width='30%' ><li>&nbsp;&nbsp;Document 3</td>"); 
						out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('27')\" ></td>");
						out.println("</tr>");
						
						//Added ByPrabash on 05-04-2012 --------**
						out.println("<tr >"); 
						out.println("<td width='5%' >23</td>"); 
						out.println("<td width='30%' >Rental letter summery </td>"); 
						out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('28')\"></td>"); 
						out.println("</tr>");
						//--------------------------------------**
						
						//Added By Prabash on 10-05-2012 --------**
						out.println("<tr >"); 
						out.println("<td width='5%' >24</td>"); 
						out.println("<td width='30%' >Reminder Blue </td>"); 
						out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('29')\"></td>"); 
						out.println("</tr>");
						
						out.println("<tr >"); 
						out.println("<td width='5%' >25</td>"); 
						out.println("<td width='30%' >Reminder Red </td>"); 
						out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('30')\"></td>"); 
						out.println("</tr>");
						
						out.println("<tr >"); 
						out.println("<td width='5%' >26</td>"); 
						out.println("<td width='30%' >Seasing Order </td>"); 
						out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('31')\"></td>"); 
						out.println("</tr>");
						
						// ADDED BY SAJITH MENDIS ON 25/11/2013
						out.println("<tr >"); 
						out.println("<td width='5%' >27</td>"); 
						out.println("<td width='30%' >Sinhala Letter 1 </td>"); 
						out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('40')\"></td>"); 
						out.println("</tr>");
						
						out.println("<tr >"); 
						out.println("<td width='5%' >28</td>"); 
						out.println("<td width='30%' >Sinhala Letter 2 </td>"); 
						out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('41')\"></td>"); 
						out.println("</tr>");
						
						out.println("<tr >"); 
						out.println("<td width='5%' >29</td>"); 
						out.println("<td width='30%' >Sinhala Letter 3 </td>"); 
						out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('42')\"></td>"); 
						out.println("</tr>");
						// END BY SAJITH MENDIS ON 25/11/2013
						
						//--------------------------------------**
						// Added by Minal on 05-01-2015 for  #15195
						out.println("<tr >"); 
						out.println("<td width='5%' >30</td>"); 
						out.println("<td width='30%' >Sinhala Letter 4 </td>"); 
						out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('43')\"></td>"); 
						out.println("</tr>");
						
						out.println("<tr >"); 
						out.println("<td width='5%' >31</td>"); 
						out.println("<td width='30%' >Sinhala Letter 5 </td>"); 
						out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('44')\"></td>"); 
						out.println("</tr>");
						
						out.println("<tr >"); 
						out.println("<td width='5%' >32</td>"); 
						out.println("<td width='30%' >Sinhala Letter 6 </td>"); 
						out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('45')\"></td>"); 
						out.println("</tr>");
						
						out.println("<tr >"); 
						out.println("<td width='5%' >33</td>"); 
						out.println("<td width='30%' >Sinhala Letter 7 </td>"); 
						out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('46')\"></td>"); 
						out.println("</tr>");
						//--------------------
						
						//added by udara 12-10-2018
						out.println("<tr >"); 
						out.println("<td width='5%' >34</td>"); 
						out.println("<td width='30%' >Offer Letter </td>"); 
						out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT' value=\"Print\" onClick=\"load_documents('47')\"></td>"); 
						out.println("</tr>");
						//end by udara 12-10-2018
						
						// added by udara 09-01-2019
						
						if(m_delivery_order_count>0){
						
							out.println("<tr >"); 
							out.println("<td width='5%' >35</td>"); 
							out.println("<td width='30%' >Delivery Order </td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT_DEL_ORDER' id='BUT_PRINT_DEL_ORDER' value=\"Print\" onClick=\"load_documents('48');disable_print_button(this);\" disabled ></td>"); 
							out.println("</tr>");
							
						}
						else{
							
							out.println("<tr >"); 
							out.println("<td width='5%' >35</td>"); 
							out.println("<td width='30%' >Delivery Order </td>"); 
							out.println("<td width='30%' ><input class='but_input' type='button' name='BUT_PRINT_DEL_ORDER' value=\"Print\" id='BUT_PRINT_DEL_ORDER' onClick=\"load_documents('48');disable_print_button(this);\"></td>"); 
							out.println("</tr>");
							
						}
		
						// end by udara 09-01-2019
						
						//===============================================================================================				
						out.println("</table>");	
						
						//========== End 08-06-2007 ======================
						
		
						
						out.println("</table>");
				
				
				}
				
				
				
				
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");
				
				
				
				out.println("</form>");
				out.println("</body>");
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("</html>");
			}
			
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
