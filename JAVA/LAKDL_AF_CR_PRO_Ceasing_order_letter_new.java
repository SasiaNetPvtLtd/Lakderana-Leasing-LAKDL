
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Ceasing_order_letter_new extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt_doc_charges,stmt_make,stmt_rental;
//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
  public ResultSet rs,rs_doc_charges,rs_make,rs_anx_status,rs_rental,rs_instal1,rs_install2,rs_sysdate;
 	

	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print;
	public double m_amount_due,m_overdu_amout,m_insu_amout,m_totoverdu_amout;
	String rec_count="";
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_username=m_sn_methods.username;
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
			String m_make="";	 //PRA
			String m_row_no =""	;//PRA
			String m_rental_date ="";//PRA
			double m_rental2=0; //PRA
			String m_rental3=""; //PRA
			String m_sysdate=""; //PRA
			String m_model_code="";
			String m_last_rental_date="";
			String m_invoice_no="";
			double m_od_interest_rate=0;
			int b_flag=0;
			int m_period_time=0;
			double m_security_margin_val=0;
			double m_residual_value=0;
			String m_rental_due_date="";
		//	String m_sub_model=""; //added by nuwan de silva 04-07-07
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
	//	 String m_application_no = "";
	//	 String m_application_no="";
		 stmt = conn.createStatement ();
			
  	 if(m_chksql.trim().equals("main_page")){
			//stmt = conn.createStatement ();
			stmt_doc_charges = conn.createStatement ();
			stmt_make = conn.createStatement ();
			stmt_rental= conn.createStatement (); //added by nuwan de silva on 10-09-07
			
			String m_application_no = req.getParameter("application_no");
			String m_client_code	  =req.getParameter("client_code");		
		//	String m_client_code="";
			String m_document_code="";
		//	String m_print="";
			
			String m_repossession_no    = req.getParameter("repossession_no");
		//	String m_finance_no        = req.getParameter("finance_no");
		//	String m_seizer_code        = req.getParameter("seizer_code").trim();
		//	String m_pro_invoice_no     = req.getParameter("pro_invoice_no").trim();
			String m_print              = req.getParameter("print");
			
	//		if(req.getParameter("client_code")!=null){
	//		m_client_code	  =req.getParameter("client_code");		
	//		}
			if(req.getParameter("document_code")!=null){
			m_document_code	=req.getParameter("document_code");	
			}
			if(req.getParameter("print")!=null){
			m_print=req.getParameter("print");
			}
			
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
			
			
			//pra-------------------------------**
	
			stmt = conn.createStatement ();
			
			//out.println(" SELECT B.CLIENT_CODE,A.FINANCE_NO,B.APPLICATION_NO"+
			rs=stmt.executeQuery (" SELECT B.CLIENT_CODE,A.FINANCE_NO,B.APPLICATION_NO"+
									" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B"+
									" WHERE A.FINANCE_NO=B.FINANCE_NO"+
									" AND A.REPOSSESSION_NO='"+m_repossession_no+"' ");
					
					more = rs.next();
					
					if(more){
					m_client_code=rs.getString(1);
					m_application_no=rs.getString(3);
					}

					
			//--Close the Result Set And Stateement--------			
			rs.close();
			stmt.close();
		
			//pra--------------------------------------**
			
			
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
			
			//--Close the Result Set And Stateement--------			
			rs.close();
			stmt.close();
			//---------------------------------------------
			//--Create The Statement----------------------
			stmt = conn.createStatement ();
			
			
			rs=stmt.executeQuery (" SELECT TO_CHAR(SYSDATE,'DD')||'-'||TO_CHAR(SYSDATE,'MM')||'-'||TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
					
					more = rs.next();
					
					if(more){
					m_sysdate=rs.getString(1);
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
					" ,TO_CHAR(ACTIVATED_DATE, 'DD-MM-YYYY'),NVL("+m_schema_name+".AF_CO_GET_CONTRACT_BAL(FINANCE_NO,CLIENT_CODE,'"+m_sysdate+"','"+m_username+"'),0),"+m_schema_name+".AF_CO_GET_RENTAL_INSURANCE(FINANCE_NO,'"+m_sysdate+"') "+
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
					
					

					}

		
	String sql_make= " SELECT  A.APPLICATION_NO,NVL(A.REG_NO,'_'),NVL(A.CHASSIS_NO,'_'),NVL(A.ENGINE_NO,'_'),NVL("+m_schema_name+".af_co_get_make('"+m_application_no+"'),'-')"+
					 " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A"+
					 " WHERE   A.ACTIVE_STATUS = 'Y' "+
					 " AND A.APPLICATION_NO=UPPER('"+m_application_no+"')";
	
	
	String sql_install= " SELECT ROWNUM,A.APPLICATION_NO,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'),A.BALANCE_TO_BE_RECEIVED "+
					 " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A "+
					 " WHERE A.APPLICATION_NO=UPPER('"+m_application_no+"') ";
	

	
	
	
			//--Close the Result Set And Stateement--------			
			rs.close();
			stmt.close();
			//---------------------------------------------
			//--Create The Statement----------------------
			stmt = conn.createStatement ();
			//--------------------------------------------	
			

			//Credit Manager Details================================================================
			rs=stmt.executeQuery (" SELECT "+
				"  NVL(UPPER(NAME),'-') "+
				" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DIRECTORS "+
				" WHERE UPPER(POSITION)='CREDIT MANAGER' ");
				
				
				more = rs.next();
				
				if(more){
				m_credit_manager_name=rs.getString(1);
				}
				
			//=======================================================================================
		
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
     
			
			out.println("<html><head>"); 
			out.println("<title>Ceasing Order Letter </title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
			out.println("<script>");
			
			out.println("function get_annexure(m_application_no){");

			
		//	out.println("window.open(m_url);");
			out.println("}"); 
			
			out.println("function get_vector_normal(http_response) {");
	
			out.println("m_table.innerHTML=\"\" ");
			out.println("window.print();");
			
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&repossess_no="+m_repossession_no+"&document_code="+m_document_code+"\";"); 
		  out.println(" window.location.href=m_url;"); 
		  
			out.println("}");
			
			
			
			
			out.println("function save_data(){");
			//out.println("get_annexure('"+m_application_no+"')");
			out.println("m_table.innerHTML=\"\" ");
			out.println("window.print();");

			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&repossess_no="+m_repossession_no+"&document_code="+m_document_code+"\";");
		
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
		//	out.println(Client_Data);
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
			  m_make=rs_make.getString(5);
	
			}
			
		rs_instal1=stmt_make.executeQuery (sql_install)	;
			int Q=0;
		
			if(rs_instal1.next()){
			 m_rental3 =nf.format(rs_instal1.getDouble(4));

		}	
			
			
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
					
	
			//=================================

			out.println("<table border='0' align='left' width='100%' class='table'>");
			out.println("<tr ><td width='30%' align='center'></TD>");
			out.println("<td width='70%' align='left'><font face='times new roman'  size=3>&nbsp</TD></tr>");
			
			out.println("<tr ><td width='60%' align='right'></TD>");
			out.println("<td width='35%' align='right'><font face='times new roman'  size=3>"+m_sysdate+"</TD><td width='5%' align='right'></TD></tr>");
			out.println("</table >");

				out.println("</BR>");
				out.println("</BR>");
				out.println("</BR>");
			//	out.println("<BR>")
				
			//	out.println("---"+m_reg_no+"***"+m_make+"----"+m_full_name+"***"+m_finance_no+"====="+m_repossession_no+"____"+m_application_no+"");
			out.println("<table border='0' align='left' width='70%' class='table'>");
			out.println("<tr style='height:25px' ><td width='25%' align='center'></TD>");
			out.println("<td width='80%' align='left'><font face='times new roman'  size=3>"+m_reg_no+"</TD></tr>");
			out.println("<tr style='height:20px' ><td width='25%' align='center'></TD>");
			out.println("<td width='80%' align='left'><font face='times new roman'  size=3>"+m_make+"</TD></tr>");
			out.println("<tr style='height:25px' ><td width='25%' align='center'></TD>");
			out.println("<td width='80%' align='left'><font face='times new roman'  size=3>"+m_chassis_no+"</TD></tr>");

			out.println("<tr style='height:20px' ><td width='25%' align='center'></TD>");
			out.println("<td width='80%' align='left'><font face='times new roman'  size=3>&nbsp</TD></tr>");

			out.println("<tr style='height:20px' ><td width='25%' align='center'></TD>");
			out.println("<td width='80%' align='left'><font face='times new roman'  size=3>"+m_engine_no+"</TD></tr>");
			
			out.println("<tr style='height:20px' ><td width='25%' align='center'></TD>");
			out.println("<td width='80%' align='left'><font face='times new roman'  size=3>"+m_full_name+"</TD></tr>");

			out.println("<tr style='height:20px' ><td width='25%' align='center'></TD>");
			out.println("<td width='80%' align='left'><font face='times new roman'  size=3>"+m_add1+"</TD></tr>");
			
			out.println("<tr style='height:20px' ><td width='25%' align='center'></TD>");
			out.println("<td width='80%' align='left'><font face='times new roman'  size=3>"+m_add2+"</TD></tr>");

			
			out.println("<tr style='height:25px' ><td width='25%' align='center'></TD>");
			out.println("<td width='80%' align='left'><font face='times new roman'  size=3>"+m_city_name+"</TD></tr>");

			out.println("<tr style='height:20px' ><td width='25%' align='center'></TD>");
			out.println("<td width='80%' align='left'><font face='times new roman'  size=3>"+m_finance_no+"</TD></tr>");
		
			
	//		out.println("<td width='30%' align='center'><b>&nbsp&nbsp&nbsp "+m_reg_no+"</b><TD><tr >");
	//		out.println("<tr ><td width='60%' align='center'><b>&nbsp&nbsp&nbsp make</b><TD><tr >");
	//		out.println("<tr ><td width='60%' align='center'><b>&nbsp&nbsp&nbsp "+m_chassis_no+"</b><TD><tr >");
	//		out.println("<tr ><td width='40%' align='left'>&nbsp<br>");
	//		out.println("<tr ><td width='60%' align='center'><b>&nbsp&nbsp&nbsp "+m_engine_no+"</b><TD><tr >");
	//		out.println("<tr ><td width='60%' align='center'><b>&nbsp&nbsp&nbsp"+m_full_name+"<b><br>");
	//		out.println("<tr ><td width='60%' align='center'>&nbsp&nbsp&nbsp<b>"+m_add1+"<b><br>");
	//		out.println("<tr ><td width='60%' align='center'>&nbsp&nbsp&nbsp<b>"+m_add2+"<b><br>");
	//		out.println("<tr ><td width='60%' align='center'>&nbsp&nbsp&nbsp<b>"+m_city_name+"<b><br>");
	//		out.println("<tr ><td width='60%' align='center'><b>&nbsp&nbsp&nbsp "+m_finance_no+"</b><TD><tr >");
			

			out.println("</table>");
			
			
			out.println("<br>");
			
			
			


			out.println("<br>");
			
	
	
			
			out.println("<br>");
			
			
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
