
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Deletion_letter_new_process extends javax.servlet.http.HttpServlet { 

	// commentedd by udara 26-12-2017
	/*
    ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt_doc_charges,stmt_make,stmt_rental;
	java.text.NumberFormat nf;
    ResultSet rs,rs_doc_charges,rs_make,rs_anx_status,rs_rental,rs_instal1,rs_install2,rs_sysdate;
	String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print,m_termination_no;
	double m_amount_due,m_overdu_amout,m_insu_amout,m_totoverdu_amout;
	String rec_count="";
	
	*/
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		
		// added by udara 26-12-2017
		ServletOutputStream out = null;
	
	
		Connection conn= null;
		Statement stmt= null,stmt_doc_charges= null,stmt_make= null,stmt_rental= null;
		java.text.NumberFormat nf= null;
	    ResultSet rs= null,rs_doc_charges= null,rs_make= null,rs_anx_status= null,rs_rental= null,rs_instal1= null,rs_install2= null,rs_sysdate= null;
		String reqstr= null,m_Letter_date= null,m_c_code= null,m_name= null,m_city_desc= null,m_due_date= null,m_no_of_due_date= null,m_finance_no= null,m_termination_no= null;
		double m_amount_due=0,m_overdu_amout= 0,m_insu_amout= 0,m_totoverdu_amout= 0;
		String rec_count="";
		// end by udara 26-12-2017
		
		CallableStatement callstmt2 =null; // added by udara 11-11-2020
		

		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_username=m_sn_methods.username;
			
			// added by udara 11-11-2020
			String m_servlet_client_url	= m_sn_methods.servlet_client_url;
			String m_client_t3_port		= m_sn_methods.client_t3_port;
			String m_client_name  = m_sn_methods.client_name;
			// end by udara 11-11-2020
			
			
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
			int count = 0;
			String m_full_name="";
			String m_client_no="";
			String m_add1="";
			String m_add2="";
			String m_city_name="";
			String m_title="";
			String m_client_type=""; 
			String m_repayment_interval="";
			String m_start_date="";
			String m_master_lease="";
			String m_nic_no="";
			
			String m_end_date="";
			String m_no_of="";
			String m_no_of_mon="";
			String m_rental="";
			int m_period=0;
			String m_rental_start_date="";
			String m_rental_start_day="";
			double m_gross_rental=0;
			String m_credit_manager_name="";
			String m_make_code="";
			String m_make_desc="";
			String m_reg_no="";	 //PRA
			String m_chassis_no="";	 //PRA
			String m_engine_no="";	 //PRA
			String m_row_no =""	;//PRA
			String m_rental_date ="";//PRA
			double m_rental2=0; //PRA
			String m_rental3=""; //PRA
			String m_sysdate=""; //PRA
			String m_sysdate_new="";
			String m_model_code="";
			String m_last_rental_date="";
			String m_invoice_no="";
			double m_od_interest_rate=0;
			int b_flag=0;
			int m_period_time=0;
			double m_security_margin_val=0;
			double m_residual_value=0;
			String m_rental_due_date="";
			String m_sub_model=""; //added by nuwan de silva 04-07-07
			String m_pricing_no="";  //added by nuwan de silva 04-07-07
			int m_qty=0;
			String m_item_desc="";
			
			
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
	
	 // added by udara 11-11-2020
	 if(m_chksql.trim().equals("generate")){
			
			String mm_finance_no = "";
			String mm_application_no = "";
			String mm_client_code = "";
			String mm_document_code = "";
			String mm_print = "";
			String mm_del_code = "";
			
			if(req.getParameter("finance_no")!=null){
				mm_finance_no = req.getParameter("finance_no");		
			}
			if(req.getParameter("application_no")!=null){
				mm_application_no = req.getParameter("application_no");		
			}
			if(req.getParameter("client_code")!=null){
				mm_client_code = req.getParameter("client_code");		
			}
			if(req.getParameter("document_code")!=null){
				mm_document_code = req.getParameter("document_code");	
			}
			if(req.getParameter("print")!=null){
				mm_print = req.getParameter("print");
			}
			if(req.getParameter("del_code")!=null){
				mm_del_code = req.getParameter("del_code");
			}
			

			callstmt2 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
						"AF_DEL_LETTER_PROCESS_SAVE(:1,:2,:3,:4,:5);END;");
			
			if(mm_del_code.equals("")){
				callstmt2.registerOutParameter(1,java.sql.Types.CHAR);
			}else{	
				callstmt2.setString(1 ,mm_del_code);
			}
			callstmt2.setString(2,mm_finance_no); 
			callstmt2.setString(3,"TERMINATION PROCESS"); 
			callstmt2.setString(4,m_username);
			callstmt2.setString(5,"NEW"); 
			callstmt2.execute();	
							
			if(mm_del_code.equals("")){
				mm_del_code = callstmt2.getString(1);				 	  
			} 
							
			callstmt2.close();
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Deletion_letter_new_process?chksql=main_page&application_no="+mm_application_no+"&client_code="+mm_client_code+"&finance_no="+mm_finance_no+"&document_code=DELE_LETT&print=TRUE&del_code="+mm_del_code+"\";");
			out.println("   window.location.href=m_url;");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			
	 }
	 // end by udara 11-11-2020
	 
	 // added by udara 27-01-2021
	 else if(m_chksql.trim().equals("generate_process")){
			
			String mm_finance_no = "";
			String mm_application_no = "";
			String mm_client_code = "";
			String mm_document_code = "";
			String mm_print = "";
			String mm_del_code = "";
			
			String mm_checked_status = ""; // added by udara 25-11-2021
			
			if(req.getParameter("finance_no")!=null){
				mm_finance_no = req.getParameter("finance_no");		
			}
			if(req.getParameter("application_no")!=null){
				mm_application_no = req.getParameter("application_no");		
			}
			if(req.getParameter("client_code")!=null){
				mm_client_code = req.getParameter("client_code");		
			}
			if(req.getParameter("document_code")!=null){
				mm_document_code = req.getParameter("document_code");	
			}
			if(req.getParameter("print")!=null){
				mm_print = req.getParameter("print");
			}
			if(req.getParameter("del_code")!=null){
				mm_del_code = req.getParameter("del_code");
			}
			
			// added by udara 25-11-2021
			if(req.getParameter("checked_status")!=null){
				mm_checked_status = req.getParameter("checked_status");
			}
			// end by udara 25-11-2021
			

			callstmt2 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
						"AF_DEL_LETTER_PROCESS_SAVE(:1,:2,:3,:4,:5);END;");
			
			if(mm_del_code.equals("")){
				callstmt2.registerOutParameter(1,java.sql.Types.CHAR);
			}else{	
				callstmt2.setString(1 ,mm_del_code);
			}
			callstmt2.setString(2,mm_finance_no); 
			callstmt2.setString(3,"DELETION PENDING PROCESS"); 
			callstmt2.setString(4,m_username);
			callstmt2.setString(5,"NEW"); 
			callstmt2.execute();	
							
			if(mm_del_code.equals("")){
				mm_del_code = callstmt2.getString(1);				 	  
			} 
							
			callstmt2.close();
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Deletion_letter_new_process?chksql=main_page&application_no="+mm_application_no+"&client_code="+mm_client_code+"&finance_no="+mm_finance_no+"&document_code=DELE_LETT&print=TRUE&del_code="+mm_del_code+"\";"); // commented by udara 25-11-2021
			out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Deletion_letter_new_process?chksql=main_page&application_no="+mm_application_no+"&client_code="+mm_client_code+"&finance_no="+mm_finance_no+"&document_code=DELE_LETT&print=TRUE&del_code="+mm_del_code+"&checked_status="+mm_checked_status+"\";"); // added by udara 25-11-2021
			out.println("   window.location.href=m_url;");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			
	 }
	 // end by udara 27-01-2021
			
  	 else if(m_chksql.trim().equals("main_page")){
						
			// added by udara 29-06-2015
			
			//stmt = conn.createStatement ();
			stmt_doc_charges = conn.createStatement ();
			stmt_make = conn.createStatement ();
			stmt_rental= conn.createStatement (); //added by nuwan de silva on 10-09-07
			
			//String m_application_no = req.getParameter("application_no");
			//String m_client_code	  =req.getParameter("client_code");		
			String m_client_code="";
			String m_document_code="";
			String m_print="";
			String m_del_code = "";
			
			String mm_checked_status = ""; // added by udara 25-11-2021
			
			if(req.getParameter("client_code")!=null){
			m_client_code	  =req.getParameter("client_code");		
			}
			if(req.getParameter("document_code")!=null){
			m_document_code	=req.getParameter("document_code");	
			}
			if(req.getParameter("print")!=null){
			m_print=req.getParameter("print");
			}
			
			if(req.getParameter("del_code")!=null){
			m_del_code=req.getParameter("del_code");
			}
			
			// added by udara 25-11-2021
			if(req.getParameter("checked_status")!=null){
				mm_checked_status = req.getParameter("checked_status");
			}
			// end by udara 25-11-2021
			
			//String m_document_code	=req.getParameter("document_code");	
			//String m_print=req.getParameter("print");
			
			rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY') FROM DUAL ");
			
			boolean more = rs.next();
			if(more){
			m_Letter_date=rs.getString(1);
			}
			
			
			
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
		//	" NVL(DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C',/*'MESS' || '. '  || */UPPER(FULL_NAME)),' ') ,   "+ //2
		    " NVL(UPPER(TITLE) || '. '|| "+m_schema_name+".AF_GET_CLIENT_NM_WITH_INITIALS('"+m_application_no+"'),' '),"+ //Added by kanchana on 2011/11/13
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
			
			//--Close the Result Set And Stateement--------			
			rs.close();
			stmt.close();
			//---------------------------------------------
			//--Create The Statement----------------------
			stmt = conn.createStatement ();
			
			
			//rs=stmt.executeQuery (" SELECT TO_CHAR(SYSDATE,'DD')||'-'||TO_CHAR(SYSDATE,'MM')||'-'||TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
			rs=stmt.executeQuery (" SELECT TO_CHAR(SYSDATE,'DD')||'-'||TO_CHAR(SYSDATE,'MM')||'-'||TO_CHAR(SYSDATE,'YYYY') , "+
				   " TO_CHAR(SYSDATE,'ddth Month yyyy') "+
				" FROM DUAL ");
					
					more = rs.next();
					
					if(more){
					m_sysdate=rs.getString(1);
					m_sysdate_new=rs.getString(2);
					}

					
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
					//" ,TO_CHAR(ACTIVATED_DATE, 'DD-MM-YYYY'),NVL("+m_schema_name+".AF_CO_GET_CONTRACT_BAL(FINANCE_NO,CLIENT_CODE,'"+m_sysdate+"','"+m_username+"'),0),"+m_schema_name+".AF_CO_GET_RENTAL_INSURANCE(FINANCE_NO,'"+m_sysdate+"') "+ // commented by udara 26-12-2017
					" ,TO_CHAR(ACTIVATED_DATE, 'DD-MM-YYYY'),0,0 "+ // added by udara 26-12-2017
					" ,NVL(TERMINATION_NO,'-')  "+ // added by udara on 23-10-2012
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
					
					more = rs.next();
					
					if(more){
					m_finance_no=rs.getString(1);
					m_start_date=rs.getString(2);
					m_master_lease=rs.getString(3);
					m_Letter_date=rs.getString(4);
					m_overdu_amout= rs.getDouble(5); 
					m_insu_amout= rs.getDouble(6); 
					m_termination_no = rs.getString(7); // added by udara on 23-10-2012
					

					}

		
	String sql_make= " SELECT  A.APPLICATION_NO,NVL(A.REG_NO,'_'),NVL(A.CHASSIS_NO,'_'),NVL(A.ENGINE_NO,'_')"+
					 " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A"+
					 //" WHERE   A.ACTIVE_STATUS = 'Y' "+
					 " WHERE A.APPLICATION_NO=UPPER('"+m_application_no+"')"+
						"AND A.ACTIVE_STATUS <> 'C' "; // added by udara 13-01-2022
	
	
	// commented by udara 05-06-2019
	/*
	String sql_install= " SELECT ROWNUM,A.APPLICATION_NO,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'),A.BALANCE_TO_BE_RECEIVED "+
					 " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A "+
					 " WHERE A.APPLICATION_NO=UPPER('"+m_application_no+"') ";
	*/
	

	
	
	
			//--Close the Result Set And Stateement--------			
			rs.close();
			stmt.close();
			//---------------------------------------------
			//--Create The Statement----------------------
			stmt = conn.createStatement ();
			//--------------------------------------------	
			

			//Credit Manager Details================================================================
			// commented by udara 05-06-2019
			/*
			rs=stmt.executeQuery (" SELECT "+
				"  NVL(UPPER(NAME),'-') "+
				" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DIRECTORS "+
				" WHERE UPPER(POSITION)='CREDIT MANAGER' ");
				
				
				more = rs.next();
				
				if(more){
				m_credit_manager_name=rs.getString(1);
				}
				*/
			//=======================================================================================
		
			//Interest Rate=======================================================
			
			
			//--Close the Result Set And Stateement--------			
			rs.close();
			stmt.close();
			//---------------------------------------------
			//--Create The Statement----------------------
			stmt = conn.createStatement ();
			//--------------------------------------------	
			
			// commented by udara 05-06-2019
			/*
				rs=stmt.executeQuery (" SELECT "+ 	 
				" RATE "+
				" FROM "+m_schema_name+".AF_CO_MAS_OD_INTEREST_RATE "+
				" WHERE ACTIVE_STATUS='Y' ");
				
				more=rs.next();
				if(more){
				m_od_interest_rate=rs.getDouble(1);
				}
     			*/
			
			out.println("<html><head>"); 
			out.println("<title>Deletion Letter </title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
			out.println("<script>");
			
			out.println("function get_annexure(m_application_no){");

			
		//	out.println("window.open(m_url);");
			out.println("}"); 
			
			out.println("function get_vector_normal(http_response) {");
	
			out.println("m_table.innerHTML=\"\" ");
			out.println("window.print();");
			
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";"); 
			
		  out.println(" window.location.href=m_url;"); 
		  
			out.println("}");
			
			
			
			/*
			out.println("function save_data(){");
			//out.println("get_annexure('"+m_application_no+"')");
			out.println("m_table.innerHTML=\"\" ");
			out.println("window.print();");

			//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";"); 
		    out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Enter_Deletion_letter_gen_save?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"&button_status=N\";"); 
			out.println(" window.location.href=m_url;"); 

			out.println("}");
			*/
			
			out.println("function save_data(){");
			//out.println("get_annexure('"+m_application_no+"')");
			out.println("m_table.innerHTML=\"\" ");
			out.println("window.print();");

			//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";"); 
		    //out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Enter_Deletion_letter_gen_save_process?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"&button_status=N&finance_no="+m_finance_no+"\";"); 
			
			//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Enter_Deletion_letter_gen_save_process?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"&button_status=N&finance_no="+m_finance_no+"&del_code="+m_del_code+"\";");  // commented by udara 25-11-2021
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Enter_Deletion_letter_gen_save_process?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"&button_status=N&finance_no="+m_finance_no+"&del_code="+m_del_code+"&checked_status="+mm_checked_status+"\";");  // added by udara 25-11-2021
			out.println(" window.location.href=m_url;"); 

			out.println("}");
		
		  out.println("function add_button(){");
			
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
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
			out.println("<body bgcolor='white'>");
			out.println("<form name='Form1'>");
			
			//out.println(" <font size=\"10\" > "); // font size
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		    out.println("</tr>"); 
			out.println("</table>");
			
			out.println("<blockquote><font size=4><p style='text-align:left'>");					
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr><td width=\"100%\" class='rep-body1'><b></b></td></tr>");
		  	out.println("</table>");
			out.println("</font></p></blockquote>");	
			//out.println("<br><br><br><br><br>");
			
	
			
			//================================================================================
			rs = stmt.executeQuery(Client_Data);
			more = rs.next();		

			while(more){
				m_full_name=rs.getString(2);
				m_add1=rs.getString(3);
				m_add2=rs.getString(4);
				m_city_name=rs.getString(5);
				m_nic_no=rs.getString(6);

			more = rs.next();		
			}
			
			//================================================================================
			
			//=================================
			rs_make=stmt_make.executeQuery (sql_make)	;
			int j=0;
		
				if(rs_make.next()){
			  m_reg_no =rs_make.getString(2);
			  m_chassis_no= rs_make.getString(3);
			  m_engine_no= rs_make.getString(4);
	
			}
			
				// commented by udara 05-06-2019
				/*
		rs_instal1=stmt_make.executeQuery (sql_install)	;
			int Q=0;
		
			if(rs_instal1.next()){
			 m_rental3 =nf.format(rs_instal1.getDouble(4));

		}	
		*/	
			// commented by udara 05-06-2019
			/*
		rs_install2=stmt_make.executeQuery (sql_install)	;
			
			boolean more2 = rs_install2.next();

					int c =0;
					while (more2) {
					c = c++	;
					m_row_no		= rs_install2.getString(1);
					m_rental_date	=rs_install2.getString(3);
					m_rental2		=rs_install2.getDouble(4);

						more2 = rs_install2.next();
						
						if(more2){
						m_row_no		= rs_install2.getString(1);
					    m_rental_date	=rs_install2.getString(3);
					    m_rental2		=rs_install2.getDouble(4);
						m_totoverdu_amout= m_rental2 + m_overdu_amout + m_insu_amout;
						
					    }else{
	
				    	}

			    	  more2 = rs_install2.next();	
				}	
				*/	
	
			//=================================

			out.println("<table border='0' align='center' width='100%' class='table'>");
			out.println("<tr ><td width='70%' align='center'> &nbsp; </TD>");
			out.println("<td width='30%' align='left'>&nbsp</TD></tr>");
			
			out.println("<tr ><td width='70%' align='center'></TD>");
			out.println("<td width='30%' align='left'>&nbsp</TD></tr>");
			
			out.println("<tr ><td width='70%' align='center'></TD>");
			out.println("<td width='30%' align='left'>&nbsp</TD></tr>");
			//
			out.println("<tr ><td width='70%' align='center'></TD>");
			out.println("<td width='30%' align='left'>&nbsp</TD></tr>");
			
			out.println("<tr ><td width='70%' align='center'></TD>");
			out.println("<td width='30%' align='left'>&nbsp</TD></tr>");
			
			out.println("<tr ><td width='70%' align='center'></TD>");
			out.println("<td width='30%' align='left'>&nbsp</TD></tr>");
			//
			
			int font_size = 2;
			
			out.println("<tr ><td width='70%' align='center'></TD>");
			out.println("<td width='30%' align='left'><font size='"+font_size+"' >"+m_sysdate_new+"</font></TD></tr>"); // m_sysdate
			
			out.println("<tr ><td width='70%' align='center'></TD>");
			//out.println("<td width='70%' align='left'><b>--</TD><b></tr>"); // commented by udara on 23-10-2012
			out.println("<td width='30%' align='left'><font size='"+font_size+"' >Contract No - "+m_finance_no+" </font></TD></tr>"); // added by udara on 23-10-2012

			out.println("<tr ><td width='70%' align='center'></TD>");
			//out.println("<td width='70%' align='left'><b>--</TD><b></tr>"); // commented by udara on 23-10-2012
			out.println("<td width='30%' align='left'><font size='"+font_size+"' >Serial No - "+m_del_code+" </font></TD></tr>"); // m_termination_no added by udara on 23-10-2012
			
			out.println("<tr ><td width='70%' align='center'></TD>");
			out.println("<td width='30%' align='left'>&nbsp</TD></tr>");
			
			out.println("<tr ><td width='70%' align='center'></TD>");
			out.println("<td width='30%' align='left'>&nbsp</TD></tr>");
			
			out.println("</table>  ");
			
			out.println("<table border='0' align='center' width='100%' class='table'>");
			
			out.println("<tr ><td width='10%' align='center'></TD>");
			out.println("<td width='70%' align='left'><font size='"+font_size+"' >The Commissioner</font></TD></tr>"); // added by udara on 23-10-2012
			
			out.println("<tr ><td width='10%' align='center'></TD>");
			out.println("<td width='70%' align='left'><font size='"+font_size+"' >Department of Motor Traffic</font></TD></tr>"); // added by udara on 23-10-2012 
			
			out.println("<tr ><td width='10%' align='center'></TD>");
			out.println("<td width='70%' align='left'><font size='"+font_size+"' >No. 341,</font></TD></tr>");
			
			out.println("<tr ><td width='10%' align='center'></TD>");
			out.println("<td width='70%' align='left'><font size='"+font_size+"' >Elvitigala Mawatha,</font></TD></tr>");
			
			out.println("<tr ><td width='10%' align='center'></TD>");
			out.println("<td width='70%' align='left'><font size='"+font_size+"' >Colombo 05.</font></TD></tr>");
			
			out.println("<tr ><td width='10%' align='center'></TD>");
			out.println("<td width='70%' align='left'>&nbsp</TD></tr>");
			out.println("<tr ><td width='10%' align='center'></TD>");
			out.println("<td width='70%' align='left'>&nbsp</TD></tr>");
			
			out.println("<tr ><td width='10%' align='center'></TD>");
			out.println("<td width='70%' align='left'><font size='"+font_size+"' >Dear Sir,</font></TD></tr>");
			
			out.println("<tr ><td width='10%' align='center'></TD>");
			out.println("<td width='70%' align='left'>&nbsp</TD></tr>");

			out.println("</table>");


		
			
			out.println("<table border='0' align='center' width='100%' class='table'>");
			//out.println("<tr ><td width='70%' align='center'><b><U>VEHICLE NO:"+m_reg_no+"<br>MR/MR "+m_full_name+"</TD></U></b></tr>");
			out.println("<tr ><td width='10%' align='center'></td><td width='70%' align='left'><b><font size='"+font_size+"' >Vehicle No : "+m_reg_no+" </font></td></b></tr>");
			out.println("<tr ><td width='10%' align='center'></td><td width='70%' align='left'><b><font size='"+font_size+"' >Registered Owner : "+m_full_name+" </font></td></b></tr>"); // Mr/Mrs. 
			out.println("</table>");
			
			out.println("<table border='0' align='center' width='100%' class='table'>");
			out.println("<tr><td width='10%' align='center'></td><td width='70%' align='left'>");
			out.println("<hr>");
			out.println("</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' align='center' width='100%' class='table'>");
			out.println("<tr ><td width='10%' align='center'></TD>");
			//out.println("<td width='70%' align='left'><b>Please take notice that the hire purchase agreement in respect of the above vehicle has been<br>completed and that we hane on claim on hirer in respect of this vehicle.</TD><b></tr>"); // commented by udara on 23-10-2012
			//out.println("<td width='70%' align='left'><b>Please take notice that the hire purchase agreement in respect of the above vehicle has been<br>completed and that we have no claim on the hirer in respect of this vehicle.</TD><b></tr>"); // commented by udara 11-12-2014
			out.println("<td width='70%' align='left'><font size='"+font_size+"' >Please be informed that the hire purchase agreement in respect of the above vehicle has been completed and we do not have any claims or interest in respect of the above vehicle.</font></TD></tr>"); // added by udara 11-12-2014
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' align='center' width='100%' class='table'>"); 
			out.println("<tr ><td width='10%' align='center'></TD>");
			//out.println("<td width='70%' align='left'><b>The registration of the company as 'ABSOLUTE OWNERS' may now be deleted.</TD><b></tr>"); // commented by udara 11-12-2014
			out.println("<td width='70%' align='left'><font size='"+font_size+"' >The registration of the company as <b>\"ABSOLUTE OWNER\"</b> may now be deleted.</font></TD></tr>"); // added by udara 11-12-2014
			out.println("</table>");
			
			out.println("<br>");
				
			out.println("<br>");
		
			out.println("<table border='0' align='center' width='100%' class='table'>");
			//out.println("<tr ><td width='70%' align='center'><b>Thank you</TD><b></tr>"); // commented by udara 11-12-2014
			out.println("<tr ><td width='10%' align='center'> </TD>");
		    out.println("<td width='70%' align='left'><font size='"+font_size+"' >Thanking You,</font></TD></tr>"); // added by udara 11-12-2014
			out.println("</table>");
			
			out.println("<br>");
		
			out.println("<table border='0' align='center' width='100%' class='table'>");
			out.println("<tr ><td width='10%' align='center'><b></TD>");
			out.println("<td width='70%' align='left'><font size='"+font_size+"' >Yours truly,</font></TD></tr>");
			
			// added by udara 11-12-2014
			out.println("<tr ><td width='10%' align='center'></TD>");
			//out.println("<td width='70%' align='left'><b><font size='"+font_size+"' >LAKDERANA INVESTMENTS LTD</font></TD></b></tr>");
			out.println("<td width='70%' align='left'><b><font size='"+font_size+"' >"+m_orient_name+"</font></TD></b></tr>"); // added by udara 03-06-2019
			// end by udara 11-12-2014
			
			
			out.println("</table>");
				
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			
			out.println("<table border='0' align='center' width='100%' class='table'>");
			out.println("<tr ><td width='10%' align='center'><b></td>");
			//out.println("<td width='70%' align='left'><font size='"+font_size+"' >--------------</font></td></tr>");
			out.println("<td width='70%' align='left'><font size='"+font_size+"' >------------------------------------</font></td></tr>"); // added by udara 03-06-2019
			out.println("<tr ><td width='10%' align='center'></td>");
			out.println("<td width='70%' align='left'><font size='"+font_size+"' >Director</font></td></tr>");
			out.println("</table>");

			out.println("<br>");
			
			// commented by udara 05-06-2019
			/*
			// added by udara 09-04-2015
			String m_insur_company = "";
			rs=stmt.executeQuery ("  "+
				   " select NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(INSUR_COM),'-') "+     
				   " from "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA  "+
				   " WHERE FINANCE_NO = '"+m_finance_no+"'  "+
				   " ORDER BY ENT_DATE DESC "+
				"  ");
				
				
				more = rs.next();
				
				if(more){
				 m_insur_company=rs.getString(1);
				}
			// end by udara 09-04-2015
			*/
			
			out.println("<table border='0' align='center' width='100%' class='table'>");
			out.println("<tr ><td width='10%' align='center'></td>");
			//out.println("<td width='70%' align='left'> "+m_insur_company+" </td></tr>");
			//out.println("<td width='70%' align='left'><font size='"+font_size+"' > CC: "+m_insur_company+" </font></td></tr>"); // commented by udara 05-06-2019
			out.println("</table>");

			out.println("<br>");
			
			
			out.println("</form>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</BODY></HTML>");
			
			// end by udara 29-06-2015

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
