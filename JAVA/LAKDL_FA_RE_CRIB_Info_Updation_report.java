


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

//DEVELOPED BY MAHELA FOR FACTORING ON 29-01-2007 

public class LAKDL_FA_RE_CRIB_Info_Updation_report extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf;
	
  public ResultSet rs;
  public ResultSet rs1,rs2;
	public String m_chksql,m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_client_no,m_print;
	public String m_business_cert_no,m_business_sub_sec;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
				
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_username = m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			String m_debtor_code="-",m_client_name="-",m_receipt_no="-",m_batch_no="-",m_return_no="-",m_facility_no="-";
			String m_chq_no="-",m_chq_date="-",m_bank="-",m_branch="-",m_deadline_date="-",m_invoice_no="-",m_mmonth="-";
			double m_chq_amount=0;
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
			m_chksql=req.getParameter("chksql");
			m_mmonth=req.getParameter("mmonth");		
			m_print=req.getParameter("print");
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();

		  if (m_chksql.trim().equals("main_page")) {
			out.println("<html><head>"); 
			out.println("<title>CRIB Information Updation Report</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");	
			out.println("<script>");
			out.println("</script>");				
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"\">");	
			out.println("<body bgcolor='white'><br>");
			out.println("<form name='Form1'>");	
		
				       
			rs2 = stmt2.executeQuery (" SELECT  "+
			"  A.CLIENT_CODE,"+//1
			"  A.FACILITY_CODE,"+//2
			"  NVL(TO_CHAR(A.MONTH_START_DATE,'DD-MM-YYYY'),'-'),"+//TO_CHAR(SYSDATE, 'fmddth Month YYYY')//3
			"  NVL(TO_CHAR(A.MONTH_END_DATE,'FMDDTH MONTH YYYY'),'-'),"+//4
			"  NVL(A.TRNAMOUNT,0), "+//5
			"  B.CLIENT_TYPE,"+//6
			"  NVL(UPPER(B.FULL_NAME),'-') "+//7
			"	 FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL A,"+m_schema_name+".FA_CO_MAS_CLIENT B "+
			"  WHERE TO_CHAR(A.MONTH_END_DATE,'MM-YYYY')='"+m_mmonth+"' "+
			"  AND A.CLIENT_CODE=B.CLIENT_CODE ");

		  String m_client_type="C";
			
			boolean more;
			more = rs2.next();
				
			while(more) { 	
			m_client_type=rs2.getString(6);
			
			out.println("<font size=2><p style='text-align:left'>");										
			out.println("<br>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='*%'class='rep-body' align='center' style='font-size: 10px;' ><b>CREDIT INFORMATION BUREAU OF SRI LANKA</b></td></tr>");
			if(m_client_type.equals("I")){
	  	out.println("<tr><td width='*%' class='rep-body' align='center' style='font-size: 10px;'>PRELIMINARY INFORMATION ON ADVANCES-PERSONAL BORROWERS</td></tr>");
			}
			else{
			out.println("<tr><td width='*%' class='rep-body' align='center' style='font-size: 10px;'>PRELIMINARY INFORMATION ON ADVANCES-CORPORATE BORROWERS</td></tr>");
			}
			out.println("</TABLE>");
			out.println("<br><br><br>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='50%'class='rep-body' align='left' style='font-size: 9px;' ><b>LENDING INSTITUTION</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- LAKDERANA INVESTMENTS LIMITED</td></tr>");
			out.println("</TABLE>");
			out.println("<table border='0' width='100%' class='table'>"); 		
	  	out.println("<tr><td width='50%' class='rep-body' align='left' style='font-size: 9px;'><B>BRANCH</B></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- <input class='txt_input6' type='text' name='TXT_N818' maxlength='100' value='HEAD OFFICE' size='200' style='width:200' style='font-size: 9px;'></td></tr>");
			out.println("</TABLE>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='50%'class='rep-body' align='left' style='font-size: 9px;' ><b>MONTH ENDING</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- "+rs2.getString(4)+"</td></tr>");
			out.println("</TABLE>");
			out.println("<br>");
		  out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='50%'class='rep-body' align='left' style='font-size: 9px;'><b>A) TYPE OF ADVANCE</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- <input class='txt_input6' type='text' name='TXT_N818' maxlength='100' value='REGULAR' size='200' style='width:200' style='font-size: 9px;'></td></tr>");
			out.println("</TABLE>");	
			out.println("<br>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			if(m_client_type.equals("I")){
			out.println("<tr><td width='50%'class='rep-body' align='left' style='font-size: 9px;'><b>B) <u>INFORMATION ON PERSONAL/JOINT BORROWER</u></b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'></td></tr>");
			}
			else{
			out.println("<tr><td width='50%'class='rep-body' align='left' style='font-size: 9px;'><b>B) <u>INFORMATION ON CORPORATE BORROWER</u></b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'></td></tr>");
			}
			out.println("</TABLE>");	
			
			
			rs1 = stmt1.executeQuery (" SELECT "+
			" A.CLIENT_CODE, "+
			" NVL(UPPER(A.FULL_NAME),'-'), "+
			" NVL(UPPER(A.REGISTERED_ADDRESS1),'-'), "+
			" NVL(UPPER(A.REGISTERED_ADDRESS2),'-'), "+
			" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-'), "+
			" NVL(A.BUSINESS_CERTIFICATE_NO,'-'), "+
			" NVL(UPPER(B.DESCRIPTION),'-'), "+
			" A.NIC_NO "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_MAS_SUB_BUSINESS_SECTORS B "+
			" WHERE A.CLIENT_CODE='"+rs2.getString(1)+"' AND A.BUSINESS_SUB_SECTOR=B.SUB_CODE(+) ");
			
			boolean more_client;
			more_client = rs1.next();
			
			String m_nic="";
			
			if(more_client){
			m_c_code=rs1.getString(1);
			m_name=rs1.getString(2);
			m_add1=rs1.getString(3);
			m_add2=rs1.getString(4);
			m_city_desc=rs1.getString(5);
			m_business_cert_no=rs1.getString(6);
			m_business_sub_sec=rs1.getString(7);
			m_nic=rs1.getString(8);
			}

			if(m_client_type.equals("I")){
			out.println("<br> ");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='3%'class='rep-body' align='left' style='font-size: 9px;'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>1) NAME IN FULL (MR/MRS/MISS)</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;' >- "+m_name+"</td></tr>");
			out.println("</TABLE>");	
			out.println("<br>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='3%'class='rep-body' align='left' style='font-size: 9px;'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>2) NIC NUMBER</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- "+m_nic+"</td></tr>");
			out.println("</TABLE>");
			out.println("<br>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='3%'class='rep-body' align='left' style='font-size: 9px;'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>3) OCCUPATION</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- <input class='txt_input6' type='text' name='TXT_N819' maxlength='100' value='-' size='200' style='width:250' style='font-size: 9px;'></td></tr>");
			out.println("</TABLE>");
			out.println("<br>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='3%'class='rep-body' align='left' style='font-size: 9px;'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>4) NAME OF THE BUSINESS</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- <input class='txt_input6' type='text' name='TXT_N819' maxlength='100' value='-' size='250' style='width:250' style='font-size: 9px;'></td></tr>");
			out.println("</TABLE>");	
			out.println("<br>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='3%'class='rep-body' align='left' style='font-size: 9px;'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>5) COMPANY/BUSINESS REGISTRATION NUMBER</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- <input class='txt_input6' type='text' name='TXT_N819' maxlength='100' value='-' size='250' style='width:200' style='font-size: 9px;'></td></tr>");
			out.println("</TABLE>");	
			out.println("<br>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='3%'class='rep-body' align='left' style='font-size: 9px;'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>6) ADDRESS</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- "+m_add1+","+m_add2+","+m_city_desc+"</td></tr>");
			out.println("</TABLE>");
			out.println("<br>");
			}
			else{
			out.println("<br> ");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='3%'class='rep-body' align='left' style='font-size: 9px;'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>1) NAME OF THE BORROWER</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;' >- "+m_name+"</td></tr>");
			out.println("</TABLE>");	
			out.println("<br>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='3%'class='rep-body' align='left' style='font-size: 9px;'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>2) REGISTERED ADDRESS</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- "+m_add1+","+m_add2+","+m_city_desc+"</td></tr>");
			out.println("</TABLE>");	
			out.println("<br>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='3%'class='rep-body' align='left' style='font-size: 9px;'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>3) COMPANY/BUSINESS REGISTRATION NUMBER</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- "+m_business_cert_no+"</td></tr>");
			out.println("</TABLE>");	
			out.println("<br>");
			
			rs1 = stmt1.executeQuery (" SELECT "+
			"  NVL(UPPER(DIR_NAME),'-'), "+
			"  NVL(UPPER(ADDRESS),'-'), "+
			"  NVL(UPPER(DIR_NIC_NO),'-') "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_COMP_DIR "+
			" WHERE CLIENT_CODE='"+rs2.getString(1)+"' ");
			boolean more_dir;
			more_dir=rs1.next();
					
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='3%'class='rep-body' align='left' style='font-size: 9px;'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>4) NAME & ADDRESS OF THE PROPRIETORS/PARTNERS/DIRECTOR</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'><b> N.I.C.NUMBER</b></td></tr>");
			out.println("</TABLE>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			while(more_dir){
			out.println("<tr><td width='5%'class='rep-body' align='left' style='font-size: 9px;'>&nbsp;&nbsp;&nbsp;</td><td width='25%'class='rep-body' align='left' style='font-size: 9px;'>* "+rs1.getString(1)+"</td><td width='20%' class='rep-body' align='left' style='font-size: 9px;'>"+rs1.getString(2)+"</td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'> "+rs1.getString(3)+"<td></tr>");
			more_dir=rs1.next();
			}
			out.println("</TABLE>");	
			
			
			rs1 = stmt1.executeQuery (" SELECT "+
			" UPPER(NAME), "+
			" UPPER(TEL_NO) "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_SUBSIDIA "+
			" WHERE CLIENT_CODE='"+rs2.getString(1)+"' ");
			
			boolean more_sub;
			more_sub=rs1.next();

			
			out.println("<br>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='3%'class='rep-body' align='left' style='font-size: 9px;'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>5) NAMES OF SUBSIDIARIES/ASSOCIATE COMPANIES</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'><b> COMPANY REGISTRATION NUMBER</b></td></tr>");
			out.println("</TABLE>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			while(more_sub){
			out.println("<tr><td width='5%'class='rep-body' align='left' style='font-size: 9px;'>&nbsp;&nbsp;&nbsp;</td><td width='45%'class='rep-body' align='left' style='font-size: 9px;'>* "+rs1.getString(1)+" </td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'> -<td></tr>");
			more_sub=rs1.next();
			}
			
			out.println("</TABLE>");
			}
				
			rs1 = stmt1.executeQuery (" SELECT "+
			" NVL(CREDIT_LIMIT,0), "+
			" NVL(TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'),'-') "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
			" WHERE FACILITY_NO='"+rs2.getString(2)+"' ");

			boolean more_faci;
			more_faci=rs1.next();
			double m_facility_amount=0;
			String m_facility_start_date="";
			
			if(more_faci){
			m_facility_amount=rs1.getDouble(1);
			m_facility_start_date=rs1.getString(2);
			}
			
			out.println("<br>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='50%'class='rep-body' align='left' style='font-size: 9px;'><b>C) <u>DETAILS OF ADVANCES</u></b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'></td></tr>");
			out.println("</TABLE>");	
			out.println("<br>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='2%' class='rep-body' align='left'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>1) LOAN/FACILITY ACCOUNT NUMBER</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- "+rs2.getString(2)+"</td></tr>");
			out.println("<tr><td width='2%' class='rep-body' align='left'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>2) DATE OF GRANTING ADVANCE</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- <input class='txt_input6' type='text' name='TXT_N819' maxlength='100' value='"+m_facility_start_date+"' size='200' style='width:120' style='font-size: 9px;'></td></tr>");
			out.println("<tr><td width='2%' class='rep-body' align='left'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>3) NATURE OF THE ADVANCE</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- <input class='txt_input6' type='text' name='TXT_N818' maxlength='100' value='FACTORING' size='200' style='width:120' style='font-size: 9px;'></td></tr>");
			out.println("<tr><td width='2%' class='rep-body' align='left'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>4) SECTOR </b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- "+m_business_sub_sec+"</td></tr>");
			out.println("<tr><td width='2%' class='rep-body' align='left'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>5) WHETHER THE ADVANCE IS DIRECT OR INDIRECT</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- <input class='txt_input6' type='text' name='TXT_N818' maxlength='100' value='DIRECT' size='200' style='width:120' style='font-size: 9px;'></td></tr>");
			out.println("<tr><td width='2%' class='rep-body' align='left'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>6) AMOUNT GRANTED</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- "+nf.format(m_facility_amount)+"</td></tr>");
			out.println("<tr><td width='2%' class='rep-body' align='left'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>7) SECURITY OFFERED</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- <input class='txt_input6' type='text' name='TXT_N818' maxlength='100' value='NIL' size='200' style='width:250' style='font-size: 9px;'></td></tr>");
			out.println("<tr><td width='2%' class='rep-body' align='left'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>8) BALANCE OUTSTANDING</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- "+nf.format(rs2.getDouble(5))+"</td></tr>");
			out.println("<tr><td width='2%' class='rep-body' align='left'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>9) AMOUNT WRITTEN OFF</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- <input class='txt_input6' type='text' name='TXT_N818' maxlength='100' value='0.00' size='200' style='width:250' style='font-size: 9px;'></td></tr>");
			out.println("</TABLE>");	
			out.println("<br>");
			
			
			rs1 = stmt1.executeQuery (" "+
			" SELECT UPPER(NVL(GUA_NAME,'-')),UPPER(NVL(GUA_NIC_NO,'-')),UPPER(NVL(GUA_ADDRESS,'-')),'-' "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_3RD_GUARAN "+
			" WHERE CLIENT_CODE='"+rs2.getString(1)+"' "+
			" UNION ALL "+
			" SELECT UPPER(NVL(DIR_NAME,'-')),UPPER(NVL(DIR_NIC_NO,'-')),UPPER(NVL(ADDRESS,'-')),UPPER(NVL(DIR_POSITION,'-')) "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_COMP_DIR "+
			" WHERE GUARANTOR_STATUS='Y' "+
			" AND CLIENT_CODE='"+rs2.getString(1)+"' ");
			
			boolean more_gua;
		  more_gua=rs1.next();
			
			int m_row_num=1;
			
			while(more_gua){
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='50%' class='rep-body' align='left' style='font-size: 9px;'><b> <u>"+m_row_num+". GUARANTOR</u></b></td><td width='*%' class='rep-body' align='left'></td></tr>");
			out.println("</TABLE>");	
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='2%' class='rep-body' align='left' style='font-size: 9px;'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>NAME IN FULL</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- "+rs1.getString(1)+"</td></tr>");
			out.println("<tr><td width='2%' class='rep-body' align='left' style='font-size: 9px;'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>N.I.C.NUMBER</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- "+rs1.getString(2)+"</td></tr>");
			out.println("<tr><td width='2%' class='rep-body' align='left' style='font-size: 9px;'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>OCCUPATION/DESIGNATION</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- <input class='txt_input6' type='text' name='TXT_N818' maxlength='100' value='"+rs1.getString(4)+"' size='250' style='width:200' style='font-size: 9px;'></td></tr>");
			out.println("<tr><td width='2%' class='rep-body' align='left' style='font-size: 9px;'>&nbsp;&nbsp;&nbsp;</td><td width='47%'class='rep-body' align='left' style='font-size: 9px;'><b>ADDRESS/RESIDENTIAL</b></td><td width='*%' class='rep-body' align='left' style='font-size: 9px;'>- "+rs1.getString(3)+"</td></tr>");
			out.println("</TABLE>");	
			out.println("<br>");
			more_gua=rs1.next();
			m_row_num++;
			}
			out.println("<br>");
			out.println("<br>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='30%' class='rep-body' align='center' style='font-size: 9px;'>..................................................</td><td width='30%'class='rep-body' align='center' style='font-size: 9px;'>........................................</td><td width='*%' class='rep-body' align='center' style='font-size: 9px;'>....................................</td></tr>");
			out.println("<tr><td width='30%' class='rep-body' align='center' style='font-size: 9px;'>NAME OF AUTHORIZED OFFICER</td><td width='30%'class='rep-body' align='center' style='font-size: 9px;'>SIGNATURE</td><td width='*%' class='rep-body' align='center' style='font-size: 9px;'>TELEPHONE NUMBER</td></tr>");
			out.println("</TABLE>");	
			out.println("</font></p>");
			out.println("<p style=\"page-break-after:always\"></p>");		
			more = rs2.next();
			}
			out.println("</form></body></html>");
			
			}//End of main page
			else  {
			out.println("idle");
			}	
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}  
