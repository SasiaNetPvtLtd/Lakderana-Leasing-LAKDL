//ID         :
//SCREEN NAME:Document Printing - First Letter
//CREATED BY :Nuwan De Silva	
//DATE/TIME  : 07-02-2006
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_New_Delivery_order extends javax.servlet.http.HttpServlet { 
	
	/*
	ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt_doc_charges,stmt_make,stmt_rental;
	java.text.NumberFormat nf;
	
	public ResultSet rs,rs_doc_charges,rs_make,rs_anx_status,rs_rental;
	
	
	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print;
	public double m_amount_due;
	String rec_count="";
	*/
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // synchronized
		
		ServletOutputStream out = null;
		
		
		Connection conn=null;
		Statement stmt=null, stmt_doc_charges=null, stmt_make=null, stmt_rental=null;
		java.text.NumberFormat nf=null;
		
		ResultSet rs=null, rs_doc_charges=null, rs_make=null, rs_anx_status=null, rs_rental=null;
		
		
		String reqstr=null,m_Letter_date=null,m_c_code=null;
		String m_name=null,m_city_desc=null,m_due_date=null,m_no_of_due_date=null,m_finance_no=null,m_branch_code=null;
		double m_amount_due=0;
		String rec_count=null;
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_username = m_sn_methods.username;
			//	String m_chksql;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			// out.println("conn"+conn);
			int m_data_count=0;
			String m_status ="";
			
			
			//Decaring variables
			
			String m_full_name="";
			String m_add1="";
			String m_add2="";
			String m_city_name="";

			String m_start_date="";
			String m_master_lease="";
			String m_nic_no="";

			double m_od_interest_rate=0;
			
			
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			
			String m_chksql = req.getParameter("chksql");
			String m_application_no = req.getParameter("application_no");
			stmt = conn.createStatement ();
			
			CallableStatement callstmt1 =null;
			
			int odi_rate = 0; // udara 12-11-2015
			
			if(m_chksql.trim().equals("main_page")){
				//stmt = conn.createStatement ();
				stmt_doc_charges = conn.createStatement ();
				stmt_make = conn.createStatement ();
				stmt_rental= conn.createStatement (); //added by nuwan de silva on 10-09-07
	
				String m_client_code="";
				String m_document_code="";
				String m_print="";
				if(req.getParameter("client_code")!=null){
					m_client_code	  =req.getParameter("client_code");		
				}
				if(req.getParameter("document_code")!=null){
					m_document_code	=req.getParameter("document_code");	
				}
				if(req.getParameter("print")!=null){
					m_print=req.getParameter("print");
				}
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY') FROM DUAL ");
				
				boolean more = rs.next();
				if(more){
					m_Letter_date=rs.getString(1);
				}
				
				
				rs = stmt.executeQuery ("SELECT RATE FROM "+m_schema_name+".AF_CO_MAS_OD_INTEREST_RATE ");
				if(rs.next()){
					odi_rate = rs.getInt(1);
				}
				
				
				// added by udara 02-11-2018
				String m_company_name = "";
				
				rs = stmt.executeQuery ("SELECT UPPER(COMPANY_NAME) "+
					" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
				
				if(rs.next()){
					m_company_name = rs.getString(1);
				}
				// end by udara 02-11-2018
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//--------------------------------------------	
				
				if(req.getParameter("status")==null){
					
					rs=stmt.executeQuery (" SELECT "+
						" COUNT(DOCUMENT_CODE) "+
						" FROM "+m_schema_name+".AF_CR_PRO_DOCUMENT_STATUS "+
						" WHERE APPLICATION_NO=UPPER('"+m_application_no+"') AND CLIENT_CODE=UPPER('"+m_client_code+"') AND DOCUMENT_CODE=UPPER('"+m_document_code+"') ");
					
					more = rs.next();
					if(more){
						m_data_count=rs.getInt(1);
					}
					
					if(m_data_count==0){
						m_status="ORIGINAL";
					}
					else{
						m_status="COPY";
					}
					
				}
				else
				{
					m_status=req.getParameter("status");
				}
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//--------------------------------------------	
				
				//Company Details============================================
				rs = stmt.executeQuery(" SELECT "+
					" NVL(UPPER(COMPANY_NAME),' '), "+
					" NVL(UPPER(ADDRESS1),' '), "+
					" NVL(UPPER(ADDRESS2),' '), "+
					" NVL(UPPER(CITY),' '), "+
					" NVL(TEL_NO,' '), "+
					" NVL(FAX_NO,' '),  "+
					" NVL(VAT_RATE,0) "+
					" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
				
				more = rs.next();		
				
				if(more)
				{
					m_orient_name=rs.getString(1);
					m_orient_add1=rs.getString(2);
					m_orient_add2=rs.getString(3);
					m_orient_city_name=rs.getString(4);
					m_orient_tel_no=rs.getString(5);
					m_orient_fax_no=rs.getString(6);
					m_orient_vat_rate=rs.getString(7);			
				}
				
				//=====================================================================
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//--------------------------------------------	
				
				
				String Client_Data=" SELECT  "+
					" 'CLIENT', "+ //1
					" NVL(DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C',/*'MESS' || '. '  || */UPPER(FULL_NAME)),' ') ,   "+ //2
					" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS1),'C',UPPER(REGISTERED_ADDRESS1)),' '),  "+ //3
					" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS2),'C',UPPER(REGISTERED_ADDRESS2)),' ') , "+ //4
					" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //5
					" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+ //6
					" NVL(DECODE(CLIENT_TYPE,'I',UPPER(FULL_NAME),'C',UPPER(FULL_NAME)),' ')    "+ //2
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
					" WHERE   CLIENT_CODE =  "+
					" (SELECT  "+
					" CLIENT_CODE "+ 
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
					" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"')) "+
					
					" UNION "+
					
					" SELECT  "+
					" 'CO-APPLICANT', "+
					" DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C',/*'MESS' || '. '  ||*/ UPPER(FULL_NAME)),   "+
					" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS1),'C',UPPER(REGISTERED_ADDRESS1)),' '),  "+
					" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS2),'C',UPPER(REGISTERED_ADDRESS2)),' ') , "+
					" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+
					" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+
					" NVL(DECODE(CLIENT_TYPE,'I',UPPER(FULL_NAME),'C',UPPER(FULL_NAME)),' ')    "+ //2
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
					" WHERE   CLIENT_CODE =  "+
					" (SELECT  "+
					" CO_APPLICANT  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
					" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"')) ";
	
				
				//============================================================================
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//--------------------------------------------	
				
				rs=stmt.executeQuery (" SELECT "+
					"  NVL(FINANCE_NO,'-'), "+
					"  TO_CHAR(ACTIVATED_DATE, 'fmddth') || ' ' || TO_CHAR(ACTIVATED_DATE, 'Month')|| TO_CHAR(ACTIVATED_DATE, 'YYYY') START_DATE,nvl(MASTER_AGREEMENT_NO,'-') "+
					" ,TO_CHAR(ACTIVATED_DATE, 'DD-MM-YYYY') "+
					" ,TO_CHAR(SYSDATE, 'DD-MM-YYYY') "+
					" ,BRANCH_CODE "+ 
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
				
				more = rs.next();
				
				if(more){
					m_finance_no=rs.getString(1);
					m_start_date=rs.getString(2);
					m_master_lease=rs.getString(3);
					//m_Letter_date=rs.getString(4);
					m_Letter_date=rs.getString(5);
					m_branch_code=rs.getString(6); 
				}
				
				
				//========================================================================================
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//--------------------------------------------	

				
				//Interest Rate=======================================================
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//--------------------------------------------	
				
				rs=stmt.executeQuery (" SELECT "+ 	 
					" RATE "+
					" FROM "+m_schema_name+".AF_CO_MAS_OD_INTEREST_RATE "+
					" WHERE ACTIVE_STATUS='Y' ");
				
				more=rs.next();
				if(more){
					m_od_interest_rate=rs.getDouble(1);
				}
				//===================================================================
				
				int m_serial_no = 0;
				int m_rec_count = 0;
				
				String serial_no_sql= " "+
							" SELECT COUNT(SERIAL_NO) "+
							" FROM "+m_schema_name+".AF_CR_PRO_DELIVERY_ORD "+
							" WHERE APPLICATION_NO = '"+m_application_no+"' "+
							" ";
				
				rs = stmt.executeQuery(serial_no_sql);
				
				if(rs.next()){
					m_rec_count = rs.getInt(1);
				}
				
				if(m_rec_count==0){
					
					serial_no_sql= " "+
							" SELECT last_number "+
							" FROM user_sequences "+
							" WHERE   sequence_name = 'AF_SEQ_DO_PROCESS_NEW' "+
							" ";
					
					rs = stmt.executeQuery(serial_no_sql);
					
					if(rs.next()){
						m_serial_no = rs.getInt(1);
					}

				}
				
				// =========== if a copy ============
				
				String m_copy_status = "";
				String m_doc_no = req.getParameter("doc_code");
				
				if(m_rec_count>0){
					
					if(m_rec_count <= 1 && m_doc_no == null){
						m_copy_status = " ";
					}
					else{
						m_copy_status = "COPY"; 
					}
					
					serial_no_sql = " "+
							" SELECT SERIAL_NO "+
							" FROM "+m_schema_name+".AF_CR_PRO_DELIVERY_ORD "+
							" WHERE APPLICATION_NO = '"+m_application_no+"' "+
							" ORDER BY ENT_DATE DESC  "+
							//" AND DO_CODE = '"+m_doc_no+"'  "+
							" ";
					
					
					rs = stmt.executeQuery(serial_no_sql);
				
					if(rs.next()){
						m_serial_no = rs.getInt(1);
					}
				
				}
				// ===============================
				

				
				out.println("<html><head>"); 
				out.println("<meta http-equiv=\"content-type\" content=\"text-html; charset=utf-8\">");
				out.println("<title>First Letter </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				
				
				out.println("<script>");

				
				out.println("function get_vector_normal(http_response) {");
				//added by nuwan de silva on 05-09-07

				
				out.println("m_table.innerHTML=\"\" ");
				out.println("window.print();");
				
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";"); 
				out.println(" window.location.href=m_url;"); 
				
				out.println("}");
				
				
				
				
				out.println("function save_data(){");
				//out.println("get_annexure('"+m_application_no+"')");
				//out.println(" alert('t1'); ");
				//out.println(" alert(window.opener.document.Form1.BUT_PRINT_DEL_ORDER.value); ");
				
				out.println("var m_copy_status = '"+m_copy_status+"'; ");
				
				out.println(" if(m_copy_status!='COPY') ");
				out.println("     window.opener.document.Form1.BUT_PRINT_DEL_ORDER.disabled=true; ");
				
				out.println("m_table.innerHTML=\"\" ");
				out.println("window.print();");				
				//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";"); 
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"&ref_no="+m_doc_no+"\";");
				out.println(" window.location.href=m_url;"); 
				
				out.println("}");
				
				out.println("function add_button(){");
				
				if (m_print.trim().equals("FALSE")) {
					out.println("m_table.innerHTML=\"\" ");
					
					try{
					   callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_DELIVERY_ORD_SAVE(:1,:2,:3); END;");
					
					   callstmt1.setString(1,m_application_no);
					   callstmt1.setString(2,m_client_code);
					   callstmt1.setString(3,m_username);
					   callstmt1.execute();
					}
					catch(Exception ee){
						out.println(ee.toString());
					}
					
					
				}
				else
				{
					out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
					out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
					out.println("m_writedata+'</table>';");
				}
				out.println("}");
				
				out.println("</script>");
				
				out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()
				out.println("<body bgcolor='white'><br>");
				out.println("<form name='Form1'>");
				
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");
		
				
				out.println("<blockquote><font size=3><p style='text-align:left'>");					
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr><td width=\"100%\" class='rep-body1'><b></b></td></tr>");
				out.println("</table>");
				out.println("</font></p></blockquote>");	

				
				out.println("<blockquote><blockquote><font size=1><p style='text-align:justify' class='rep-body1'>");	
				
				
				
				
				// ==================== vendor details start =====================================
				String m_vendor_code = "";
				String m_vendor_name = "";
				String m_vendor_address = "";
				String m_vendor_city = "";
				
				String m_item_sub_cat = "";
				String m_engine_no = "";
				String m_chassis_no = "";
				String m_reg_no = "";
				String m_make_code = "";
				double m_net_price = 0;
				
				String vendor_code_sql= " "+
					" SELECT A.VENDOR_CODE, "+
					" NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(A.VENDOR_CODE),'-'), "+
					" NVL("+m_schema_name+".AF_CO_GET_ITEM_SUB_CAT_DESC(B.ITEM_CATEGORY,B.ITEM_SUB_CAT_CODE),'-'), "+
					" NVL(A.ENGINE_NO,'-'), "+
					" NVL(A.CHASSIS_NO,'-'), "+
					" NVL(A.REG_NO,'-'), "+
					" NVL(A.SUB_MODEL_CODE,'-'), "+
					" NVL(NET_PRICE,0) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
					" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
					" AND A.APPLICATION_NO='"+m_application_no+"' "+
					" AND A.ACTIVE_STATUS='Y' "+
					" ";
				
				rs = stmt.executeQuery(vendor_code_sql);
				
				if(rs.next()){
					m_vendor_code  = rs.getString(1);
					m_vendor_name  = rs.getString(2);
					m_item_sub_cat = rs.getString(3);
				    m_engine_no    = rs.getString(4);
				    m_chassis_no   = rs.getString(5);
			        m_reg_no       = rs.getString(6);
					m_make_code    = rs.getString(7);
					m_net_price    = rs.getDouble(8);
				}
				
				String vendor_detail_sql= " "+
					" SELECT ADDRESS, "+
					" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-') "+
					" FROM "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION "+
					" WHERE VENDOR_CODE='"+m_vendor_code+"' "+
					" ";
				
				rs = stmt.executeQuery(vendor_detail_sql);
				
				if(rs.next()){
					m_vendor_address = rs.getString(1);
					m_vendor_city = rs.getString(2);
				}
				
				// ==================== vendor details end =======================================
				
				
				//================================================================================
				rs = stmt.executeQuery(Client_Data);
				//more = rs.next();	
				if(rs.next()){	
					m_full_name=rs.getString(2);
					m_add1=rs.getString(3);
					m_add2=rs.getString(4);
					m_city_name=rs.getString(5);
					m_nic_no=rs.getString(6);
				}
				
				//================================================================================
				
				
				// ============== start box start =============================
				
				// image start
				out.println("<table border='0' width='90%' class='table'>"); 
				out.println("<tr>");
				out.println("<td td width='60%' >");
				out.println("</td>");
				out.println("<td td width='40%' >");
				out.println("<DIV align=right><img src=\""+m_html_client_url+"/images/logo_new.png\"></DIV>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>"); 
				// image end

				out.println("<table border='0' width='90%' class='table'>"); 
				out.println("<tr>");
				
				out.println("<td td width='60%' >");

				out.println("<table border='0' width='100%' class='table'>"); 
				out.println("<tr><td width='*%' align='left' class='rep-body1' ><font size=1><b>Serial No : "+m_branch_code+" "+m_serial_no+" "+m_copy_status+" </b></td></tr>");
				out.println("<tr><td width='*%' align='left' class='rep-body1' ><font size=1>To :</td></tr>");
				out.println("<tr><td width='*%' align='left' class='rep-body1' ><font size=1>"+m_vendor_name+"</td></tr>");
				out.println("<tr><td width='*%' align='left' class='rep-body1' ><font size=1>"+m_vendor_address+"</td></tr>");
				out.println("<tr><td width='*%' align='left' class='rep-body1' ><font size=1>"+m_vendor_city+"</td></tr>");
				out.println("</table>");
				
				out.println("</td>");
				
				out.println("<td td width='40%' >");
				
				out.println("<table border='0' width='100%' class='table'>"); 		
				out.println("<tr><td width='*%' align='left' class='rep-body1' ><font size=1>Our  Reference: "+m_finance_no+"</td></tr>");
				out.println("<tr><td width='*%' align='left' class='rep-body1' ><font size=1>Issued By : "+m_username+"</td></tr>");
				out.println("<tr><td width='*%' align='left' class='rep-body1' ><font size=1>Date : "+m_Letter_date+"</td></tr>");
				out.println("</table>");	
				
				out.println("</td>");
				
				out.println("</tr>");
				out.println("</table>");
				// ============== start box end =============================
				
				//out.println("<br><br>"); // commented by udara 13-03-2019
				//out.println("<br>"); // added by udara 13-03-2019
				
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body1' ><font size=1>Dear Sir,</td></tr>");
				out.println("</table>");
				
				//out.println("<br>"); // commented by udara 13-03-2019
				
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='95%' colspan='2' class='rep-body1' ><font size=1><p align=\"justify\" >"+
					" In  accordance  with  our  Hire  Purchase  agreement  with "+ m_full_name + " of "+ m_add1 +"  "+m_add2+" , "+ m_city_name +
					" as  requested by the hirer, we the "+ m_company_name +" hereby agree to purchase and accordingly, place our official order with you for the property described hereunder subject to the following terms and conditions. "+
					" </p></td>");
				out.println("</tr>");
				
				out.println("</table>");
				//out.println("<br>"); // commented by udara 13-03-2019
			
				// table numeric bullets 1 start
				
				out.println("<table border='0' width='90%' class='table' >"); 

				out.println("<tr>");
				out.println("    <td width='2%' valign=top ><font size=1> 1. </font></td>");
				out.println("    <td width='38%' ><font size=1> Description of properties as per invoice: </font></td>");
				out.println("    <td width='40%' ><font size=1> &nbsp; </font></td>");				
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='2%'  ><font size=1> &nbsp; </font></td>");
				out.println("    <td width='38%' ><font size=1> <b> CLASS OF VEHICLE </b> </font></td>");
				out.println("    <td width='40%' ><font size=1> : "+m_item_sub_cat+" </font></td>");				
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='2%'  ><font size=1> &nbsp; </font></td>");
				out.println("    <td width='38%' ><font size=1> <b> ENGINE NO. </b> </font></td>");
				out.println("    <td width='40%' ><font size=1> : "+m_engine_no+" </font></td>");				
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='2%'  ><font size=1> &nbsp; </font></td>");
				out.println("    <td width='38%' ><font size=1> <b> CHASSIS NO. </b> </font></td>");
				out.println("    <td width='40%' ><font size=1> : "+m_chassis_no+" </font></td>");				
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='2%'  ><font size=1> &nbsp; </font></td>");
				out.println("    <td width='38%' ><font size=1> <b> REGISTERED NO. </b> </font></td>");
				out.println("    <td width='40%' ><font size=1> : "+m_reg_no+" </font></td>");				
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='2%'  ><font size=1> &nbsp; </font></td>");
				out.println("    <td width='38%' ><font size=1> <b> MAKE / MODEL </b> </font></td>");
				out.println("    <td width='40%' ><font size=1> : "+m_make_code+" </font></td>");				
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='2%'  valign=top ><font size=1> 2. </font></td>");
				out.println("    <td width='38%' ><font size=1> Purchase Price </font></td>");
				out.println("    <td width='40%' ><font size=1> : "+nf.format(m_net_price)+" </font></td>");				
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='2%'  valign=top ><font size=1> 3. </font></td>");
				out.println("    <td width='38%' ><font size=1> Place to be delivered </font></td>");
				out.println("    <td width='40%' ><font size=1> : "+ m_add1 +"  "+m_add2+" , "+ m_city_name +" </font></td>");				
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='2%'  valign=top ><font size=1> 4. </font></td>");
				out.println("    <td width='38%' ><font size=1> Other terms and conditions </font></td>");
				out.println("    <td width='40%' ><font size=1> : &nbsp; </font></td>");				
				out.println("</tr>");
				
				out.println("</table>");
				
				// table numeric bullets 1 end
				
				// table alphabetic bullets start
				
				out.println("<table border='0' width='90%' class='table' >");
			
				out.println("<tr>");
				out.println("    <td width='5%'  align='right' valign='top' ><font size=1> a) </font></td>");
				out.println("    <td width='75%' ><font size=1><p align=\"justify\" > Title to the said property shall be vested in "+ m_company_name +". Free from any liens and encumbrances of anyone claiming by through or under you with effect from the date of purchase by "+ m_company_name +" of the property. </font></p></td>");			
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='5%'  align='right' valign='top' ><font size=1> b) </font></td>");
				out.println("    <td width='75%' ><font size=1><p align=\"justify\" > "+ m_company_name +" shall make payment of the purchase price to you only on your delivery of annexed second copy to "+ m_company_name +" duly signed by the hirer confirming his receipt of the goods referred in item 1 in good order. </font></p></td>");			
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='5%'  align='right' valign='top' ><font size=1> c) </font></td>");
				out.println("    <td width='75%' ><font size=1><p align=\"justify\" > You shall deliver to "+ m_company_name +" and Hirer your written warranties in substance and in form as required by this Purchase Order. On acceptance of this order you hereby agree that all warranties written or oral, express or implied are for the benefit of the "+ m_company_name +" and Hirer and may be enforced by both "+ m_company_name +" and Hirer or either of them. </font></p></td>");			
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='5%'  align='right' valign='top' ><font size=1> d) </font></td>");
				out.println("    <td width='75%' ><font size=1><p align=\"justify\" > If the property described in (1) above is a vehicle, registration of the vehicle should be undertaken by you. Absolute ownership should be registered in the name of "+ m_company_name +" and the Hirer should be the registered user. </font></p></td>");			
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='5%'  align='right' valign='top' ><font size=1> e) </font></td>");
				out.println("    <td width='75%' ><font size=1><p align=\"justify\" > This Purchase Order is valid only for a period of 14 days from the date of issue. </p></font></td>");			
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='5%'  align='right' valign='top' ><font size=1> f) </font></td>");
				out.println("    <td width='75%' ><font size=1><p align=\"justify\" > Payment would be made on confirmation of "+ m_company_name +" as absolute owner, and upon vehicle inspection carried out by "+ m_company_name +". </font></p></td>");			
				out.println("</tr>");
			
				out.println("</table>");
				
				// table alphabetic bullets end
				
				
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='95%' colspan='2' class='rep-body1' ><font size=1><p align=\"justify\" >"+
					" If you accept our above Purchase Order, please return the <b> First Copy signed by you (as the Seller) and Second Copy signed by Hirer </b> in order to confirm both parties acceptance to the above order. "+
					" </p></td>");
				out.println("</tr>");
				
				out.println("</table>");
				
				out.println("<table border='0' width='90%' class='table' >"); 

				out.println("<tr>");
				out.println("    <td width='2%'  valign=top ><font size=1> 5. </font></td>");
				out.println("    <td width='78%' ><font size=1><p align=\"justify\" > Forward a Fresh Tax Invoice with invoice no. VAT no. and duplicate key. </p></font></td>");			
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='2%'  valign=top ><font size=1> 6. </font></td>");
				out.println("    <td width='78%' ><font size=1><p align=\"justify\" > If you are a VAT Registered Seller, please forward a copy of VAT registration certificate. </p></font></td>");			
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='2%'  valign=top ><font size=1> 7. </font></td>");
				out.println("    <td width='78%' ><font size=1><p align=\"justify\" > Where the Seller is not the importer of vehicle the Seller shall not include VAT on the tax invoice. In such instances the Seller shall obtain the buying and selling approval from the department of Inland Revenue enabling you to claim the said  tax from us. The said approval shall be obtained prior to the date of invoice and in absence of such approval we are unable  to pay you the VAT. </p></font></td>");			
				out.println("</tr>");
				
				out.println("</table>");

				out.println("<br>");

				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='95%' colspan='2' class='rep-body1' ><font size=1>");
				out.println("Yours faithfully,");
			    out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='95%' colspan='2' class='rep-body1' ><font size=1>");
				out.println("<b>"+m_company_name+"</b>");
			    out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<br><br>");
				
				out.println("<table border='0' width='90%' class='table'>"); 
		
				out.println("<tr>");				
				out.println("   <td width='75%' colspan='2' class='rep-body1' > <font size=1> ........................... </font> </td> ");
				out.println("   <td width='25%' colspan='2' class='rep-body1' > <font size=1> ........................... </font> </td> ");			
				out.println("</tr>");
				
				out.println("<tr>");				
				out.println("   <td width='75%' colspan='2' class='rep-body1' > <font size=1> Authorized Signatory </font> </td> ");
				out.println("   <td width='25%' colspan='2' class='rep-body1' > <font size=1> Authorized Signatory </font> </td> ");			
				out.println("</tr>");
				
				out.println("</table>");
				
				out.println("<hr>");
				
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='95%' colspan='2' class='rep-body1' ><font size=1>"+
					" To : "+m_company_name+" "+
					" </td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width='95%' colspan='2' class='rep-body1' ><font size=1>"+
					" I/We .................................................... hereby confirm our acceptance of your above Purchase Order and further confirm the following. "+
					" </td>");
				out.println("</tr>");
				
				out.println("</table>");
				
				out.println("<table border='0' width='90%' class='table' >"); 

				out.println("<tr>");
				out.println("    <td width='40%' ><font size=1> Expected date of delivery </font></td>");
				out.println("    <td width='60%' ><font size=1> : </font></td>");			
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='40%' ><font size=1> Period of Installation </font></td>");
				out.println("    <td width='60%' ><font size=1> : </font></td>");			
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='40%' ><font size=1> Warranty (if any) </font></td>");
				out.println("    <td width='60%' ><font size=1> : </font></td>");			
				out.println("</tr>");
				
				out.println("</table>");
				
				out.println("<table border='0' width='90%' class='table'>"); 
		
				out.println("<tr>");				
				out.println("   <td width='75%' colspan='2' class='rep-body1' > &nbsp;  </td> ");
				out.println("   <td width='25%' colspan='2' class='rep-body1' > <font size=1> ........................... </font> </td> ");			
				out.println("</tr>");
				
				out.println("<tr>");				
				out.println("   <td width='75%' colspan='2' class='rep-body1' > &nbsp;  </td> ");
				out.println("   <td width='25%' colspan='2' class='rep-body1' > <font size=1> Signed by </font> </td> ");			
				out.println("</tr>");
				
				out.println("<tr>");				
				out.println("   <td width='75%' colspan='2' class='rep-body1' > &nbsp;  </td> ");
				out.println("   <td width='25%' colspan='2' class='rep-body1' > <font size=1> For and on behalf of the Seller </font> </td> ");			
				out.println("</tr>");
				
				out.println("</table>");
				
				out.println("<hr>");
				
				out.println("<table border='0' width='90%' class='table'>"); 		
				
				out.println("<tr>");
				out.println("<td width='95%' colspan='2' class='rep-body1' ><font size=1>"+
					" I/We have received the property mentioned above in satisfactory condition and request you to make payment to the Seller. "+
					" </td>");
				out.println("</tr>");
				
				out.println("</table>");
				
				//out.println("<br><br>"); // commented by udara 13-03-2019
				out.println("<br>"); // added by udara 13-03-2019
				
				out.println("<table border='0' width='90%' class='table'>"); 
		
				out.println("<tr>");				
				out.println("   <td width='75%' colspan='2' class='rep-body1' > &nbsp;  </td> ");
				out.println("   <td width='25%' colspan='2' class='rep-body1' > <font size=1> ........................... </font> </td> ");			
				out.println("</tr>");
				
				out.println("<tr>");				
				out.println("   <td width='75%' colspan='2' class='rep-body1' > &nbsp;  </td> ");
				out.println("   <td width='25%' colspan='2' class='rep-body1' > <font size=1> (Hirer) </font> </td> ");			
				out.println("</tr>");
				
				out.println("</table>");
				
				// add footer start
				//out.println("<br>"); // commented by udara 13-03-2019
				out.println("<hr>");
				
				out.println("<table border='0' width='90%' class='table'>"); 
				out.println("<tr><td align=center><font size=1> Lakderana Investments Limited, No.100, Buthgamuwa Road, Rajagiriya </font></td></tr>");
				out.println("<tr><td align=center><font size=1> Tel : 011 7 586 500 | Fax : 011 7 586 509 | Email : info@lakderana.lk | Web : www.lakderana.lk </font></td></tr>");
				out.println("</table>");
				// add footer end


				out.println("</font></p></blockquote>");
				
				
				
				out.println("<blockquote><blockquote><font size=1><p style='text-align:justify' class='rep-body1'>");		

				
				//out.println("</font></p></blockquote></blockquote>"); // commented by udara 10-11-2016
				out.println("</font>"); // added by udara 10-11-2016
			
				//out.println("<br><br>"); // commented by udara 16-11-2015
				out.println("<br>"); // added by udara 16-11-2015
				

				
				out.println("</p></blockquote></blockquote>"); // added by udara 10-11-2016
				
				// end by udara on 02-01-2013

				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
			}
			
			
			
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
