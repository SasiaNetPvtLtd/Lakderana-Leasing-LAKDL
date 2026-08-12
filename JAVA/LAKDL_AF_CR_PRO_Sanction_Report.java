
//DEVELOPED BY MAHELA FOR LEASING ON 08-01-2007

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

 

public class LAKDL_AF_CR_PRO_Sanction_Report extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt5,stmt_odi,stmt_odi2,stmt_return,stmt_return2,stmt_gua,stmt_model,stmt_model2;
	Statement stmt_asset_det,stmt_remarks;
	java.text.NumberFormat nf;
	
  public ResultSet rs,rs_rental,rs_asset,rs_charges,rs_arrears,rs_odi,rs_odi2,rs_return,rs_return2;
  public ResultSet rs1,rs2,rs3,rs4,rs5,rs_gua,rs_model2,rs_model,rs_remarks ;
	public String m_chksql,m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_client_no,m_print;
	public double m_credit_limit,m_reverse_margin,m_int_rate;
	String m_debtor_code,m_client_name;
	
	
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
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
						
			m_chksql=req.getParameter("chksql");		
			m_print=req.getParameter("print");

			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			
			String m_full_name="",m_bus_sector="-",m_add1="",m_add2="",m_city_name="",m_title="",m_sector_desc="-";
			String m_remark="-",m_inq_no="-",m_lead_source="-",m_co_applicant="-";
			double m_credit_score=0;

			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt3 = conn.createStatement();
			stmt4 = conn.createStatement();
			stmt_asset_det = conn.createStatement();
			stmt_odi = conn.createStatement();
			stmt_odi2 = conn.createStatement();
			stmt_return = conn.createStatement();
			stmt_return2 = conn.createStatement();
			stmt_gua = conn.createStatement();
			stmt_model = conn.createStatement();
			stmt_model2 = conn.createStatement();
			stmt_remarks = conn.createStatement();
			
		  if (m_chksql.trim().equals("main_page")) {
			String m_application_no = req.getParameter("applicaton_no");
			String m_facility_no = req.getParameter("facility_no");		
			String m_client_code = req.getParameter("client_code");		
			
			out.println("<html><head>"); 
			out.println("<title>Sanction Report </title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");	
			out.println("<script>");
			out.println("function print_data(){");
			out.println(" m_table.innerHTML=\"\" ");
			out.println(" window.print();");
			out.println("}");
			out.println("function add_button(){");
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_data()\"></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			out.println("}");
			out.println("</script>");				
			out.println("<body leftmargin='0' topmargin='0' rightmargin='0' class=body onLoad=\"add_button()\">");	
			out.println("<body bgcolor='white'><br>");
			out.println("<form name='Form1'>");	
		  out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");
		
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ");								
				boolean more = rs.next();
				
				if(more){
				m_Letter_date=rs.getString(1);
				}
					//Company Details	
					rs2 = stmt2.executeQuery(" SELECT "+
					    " COMPANY_NAME, "+//1
					    " ADDRESS1, "+//2
					    " ADDRESS2, "+//3
					    " CITY, "+//4
					    " TEL_NO, "+//5
					    " FAX_NO,  "+//6
							" VAT_RATE "+//7
							" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");

							  more = rs2.next();		
											
											if(more)
											{
											m_orient_name=rs2.getString(1);
											m_orient_add1=rs2.getString(2);
											m_orient_add2=rs2.getString(3);
											m_orient_city_name=rs2.getString(4);
											m_orient_tel_no=rs2.getString(5);
											m_orient_fax_no=rs2.getString(6);
											m_orient_vat_rate=rs2.getString(7);			
											}
								
						    //Client Details
							  rs = stmt.executeQuery(" SELECT "+
        												" NVL(INITCAP(TITLE),'-'), "+//1
																" NVL(FULL_NAME,' '), "+//2
																" REPLACE(REPLACE(NVL(ADDRESS1,' '),'-',' '),'null',' '),"+//3
																" REPLACE(REPLACE(NVL(ADDRESS2,' '),'-',' '),'null',' '),"+//4
                                " NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-'), "+//5
															  " NVL(BUSINESS_CERTIFICATE_NO,' '), "+//6
																" NVL(BUSINESS_SUB_SECTOR,'-'), "+//7
																" NVL(CLIENT_CODE,'-'), "+//8
																" CLIENT_TYPE,"+//9
																" REPLACE(REPLACE(NVL(REGISTERED_ADDRESS1,' '),'-',' '),'null',' '),"+//10
																" REPLACE(REPLACE(NVL(REGISTERED_ADDRESS2,' '),'-',' '),'null',' ')"+//11
																" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
                                " WHERE   CLIENT_CODE = "+
                                " (SELECT "+
		                            " CLIENT_CODE "+
                                " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
                                " WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"'))");
    
			 					more = rs.next();		
				
							if(more)
							{
								m_title=rs.getString(1);
								m_full_name=rs.getString(2);
								if(rs.getString(9).equals("I")){
								m_add1=rs.getString(3);
								m_add2=rs.getString(4);
								}
								else {
								m_add1=rs.getString(10);
								m_add2=rs.getString(11);
								}
								m_city_name=rs.getString(5);
								m_bus_sector=rs.getString(7);
							}
				
							if(!m_add1.equals(" "))
							{
								m_add1=m_add1+",";
							}
							if(!m_add2.equals(" "))
							{
								m_add2=m_add2+",";
							}
							
							//Sector Description
							rs = stmt.executeQuery(" SELECT  SUB_CODE,"+
    						" SECTOR_CODE,"+
    						" NVL(DESCRIPTION,'-') "+
 							  " FROM "+m_schema_name+".AF_CO_MAS_SUB_BUSINESS_SECTORS "+
 							  " WHERE SECTOR_CODE='"+m_bus_sector+"' ");
								
							more = rs.next();		
							if(more)
							{
								m_sector_desc=rs.getString(3);
							}	
							
							//Credit Score Details
							rs = stmt.executeQuery("SELECT  APPLICATION_CODE,"+
    						 "	SCORE_SUB_CODE, "+
    						 "	NVL(SCORE,'-'),"+
    						 "	NVL(REMARKS,'-')"+
 							   "  FROM "+m_schema_name+".AF_CR_PRO_CRSCORE_DETAIL "+
 							   "  WHERE APPLICATION_CODE='"+m_application_no+"'");
							
							more = rs.next();		
							if(more)
							{
								m_credit_score=rs.getDouble(3);
								m_remark=rs.getString(4);
							}
						
						  rs = stmt.executeQuery("SELECT "+
  						" NVL(APPLICATION_NO,'-'),"+
  						" NVL(INQUARY_NO,'-'), "+
							" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT),'-') "+
 							" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
 							" WHERE APPLICATION_NO='"+m_application_no+"' ");
							more = rs.next();		 	
							if(more)
							{
								m_inq_no=rs.getString(2);
								m_co_applicant=rs.getString(3);
							}
						  
							rs = stmt.executeQuery("SELECT "+
  					   " NVL(LEAD_SOURCE_NAME,'-') "+
 							 " FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
 							 " WHERE INQUIRY_CODE='"+m_inq_no+"' ");
							more = rs.next();		 	 	
							if(more)
							{
								m_lead_source=rs.getString(1);
							}
			
			//out.println("<blockquote>");										
			out.println("<font size=2><p style='text-align:left' class='rep-body'>");		
			out.println("<table width='100%'  border='1' cellspacing='0'>");
  		out.println("<tr class='rep-body'>");
   		out.println("<td colspan='10' class='rep-body' ><p align='center'><strong>"+m_orient_name+" </strong><br>");
     	out.println("<strong>SANCTION LETTER</strong> <br> ");
      out.println("<br>");
   		out.println("</p></td> ");
 			out.println("</tr>");
 			out.println("<tr class='rep-body'>");
    	out.println("<td class='rep-body'>Application No : </td> ");
  	  out.println("<td colspan='3' class='rep-body'>&nbsp; "+m_application_no+"</td>"); 
      out.println("<td class='rep-body'>Introduced by : </td> ");
      out.println("<td colspan='2' class='rep-body'>"+m_lead_source+"</td> ");
   		out.println("<td class='rep-body'>Date</td> ");
    	out.println("<td colspan='2' class='rep-body'>"+m_Letter_date+"</td> ");
 		  out.println("</tr>");
 			out.println("<tr class='rep-body'>");
    	out.println("<td colspan='2' class='rep-body'>Client</td>");
			if(m_title.equals("-")){
			out.println("<td colspan='4' class='rep-body'><b>"+m_full_name+" </b></td> ");
			}
			else {
      out.println("<td colspan='4' class='rep-body'><b>"+m_title+"."+m_full_name+" </b></td> ");
			}
      out.println("<td class='rep-body'><strong>SECTOR/INDUSTRY </strong></td> ");
      out.println("<td colspan='3' class='rep-body'><strong>"+m_sector_desc+"</strong></td>");
  		out.println("</tr> ");
 	    out.println("<tr>");
      out.println("<td colspan='2' valign='top' class='rep-body'>Address</td>");
      out.println("<td colspan='4' class='rep-body'>"+m_add1+""+m_add2+"<br> ");
      out.println(""+m_city_name+"      <br> </td> ");
      out.println("<td class='rep-body'>Credit Rating </td> ");
      out.println("<td colspan='3' class='rep-body'> "+m_credit_score+" </td> ");
      out.println("</tr>");
			
			out.println("<tr>");
      out.println("<td colspan='2' valign='top' class='rep-body'>Co-Applicant</td>");
      out.println("<td colspan='4' class='rep-body'>"+m_co_applicant+"<br> </td>");
      out.println("<td class='rep-body'>&nbsp;</td> ");
      out.println("<td colspan='3' class='rep-body'>&nbsp;</td> ");
      out.println("</tr>");
			
					//Valuer Details
					rs = stmt.executeQuery(" SELECT "+
					  "  DISTINCT "+
					  "  A.VALUER_CODE, "+//1
					  "  B.FIRST_NAME, "+//2
					  "  B.LAST_NAME "+//3
					" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION A,"+m_schema_name+".AF_CO_MAS_VALUERS B "+
					" WHERE A.APPLICATION_NO='"+m_application_no+"' "+
					" AND A.VALUER_CODE=B.VALUER_CODE(+) ");
					more = rs.next();			
					int count2=0;
			
			if(!more){
			out.println("<tr>");
      out.println("<td colspan='4' class='rep-body' valign='top' >Valuers<br> ");
      out.println("<br></td>");
			out.println("<td width='11%' class='rep-body'> <div align='center'>"+count2+"</div></td> ");
      out.println("<td colspan='5' class='rep-body'>&nbsp; </td>");
			out.println("</tr>");
			}
			while(more) {
			count2++;
			out.println("<tr>");
			if(count2==1){
      out.println("<td colspan='4' class='rep-body' valign='top' >Valuers<br> ");
      out.println("<br></td>");
			}
			else {
			out.println("<td colspan='4' class='rep-body' valign='top' >&nbsp;<br> ");
      out.println("<br></td>");
			}
      out.println("<td width='11%' class='rep-body'> <div align='center'>"+count2+"</div></td> ");
      out.println("<td colspan='5' class='rep-body'>&nbsp;"+rs.getString(2)+" "+rs.getString(3)+"</td>");
			out.println("</tr>");
			more = rs.next();			
			}
			
					//Guarantor Details
					rs = stmt.executeQuery(" SELECT "+
  			  " APPLICATION_NO,"+
    			" GUARANTOR_CODE,"+
  			  " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(GUARANTOR_CODE),'-') "+	 		
 					" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR "+
 				  " WHERE APPLICATION_NO='"+m_application_no+"' ");
					more = rs.next();			
					int count=0;
			
      
			if(!more){
			out.println("<tr>");
      out.println("<td colspan='4' class='rep-body' valign='top' >Guarantors<br> ");
      out.println("<br></td>");
			out.println("<td width='11%' class='rep-body'> <div align='center'>"+count+"</div></td> ");
      out.println("<td colspan='5' class='rep-body'>&nbsp; </td>");
			out.println("</tr>");
			}
			while(more) {
			count++;
			out.println("<tr>");
			if(count==1){
      out.println("<td colspan='4' class='rep-body' valign='top' >Guarantors<br> ");
      out.println("<br></td>");
			}
			else {
			out.println("<td colspan='4' class='rep-body' valign='top' >&nbsp;<br> ");
      out.println("<br></td>");
			}
      out.println("<td width='11%' class='rep-body'> <div align='center'>"+count+"</div></td> ");
      out.println("<td colspan='5' class='rep-body'>&nbsp; "+rs.getString(3)+"</td>");
			out.println("</tr>");
			more = rs.next();			
			}
      
      out.println("<tr>");
      out.println("<td colspan='10' class='rep-body'>Directors/Partners/Share Holders Shareholding</td>");
      out.println("</tr>");
			 
		   rs = stmt.executeQuery("SELECT "+
   		 " CLIENT_CODE, "+
   		 " NAME, "+
  		 " VALUE "+
 			 " FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DIRECTORS "+
 			 " WHERE CLIENT_CODE='"+m_client_code+"' ");
       more = rs.next();			
			
  		out.println("<tr>");
      out.println("<td colspan='4' class='rep-body'><div align='center'>Name</div></td>");
      out.println("<td><div align='center' class='rep-body'>Value</div></td>");
      out.println("<td width='13%' rowspan='6' class='rep-body'><br>");
      out.println("<br><br><br><br> ");
      out.println("<br></td>");
    	out.println("<td width='13%' class='rep-body'><div align='center'><strong>Exposure</strong></div></td>");
      out.println("<td width='5%' class='rep-body'>&nbsp;</td>");
      out.println("<td width='5%' class='rep-body'>&nbsp;</td>");
      out.println("<td width='7%' class='rep-body'>&nbsp;</td>");
      out.println("</tr>");
      out.println("<tr>");
			if(more){
			out.println("<td colspan='4' class='rep-body'>&nbsp;"+rs.getString(2)+"</td>");
      out.println("<td class='rep-body' align='right'>&nbsp;"+nf.format(rs.getDouble(3))+"</td>");
			more = rs.next();			
			}
			else {
			out.println("<td colspan='4' class='rep-body'>&nbsp;</td>");
      out.println("<td class='rep-body'>&nbsp;</td>");
			}
      out.println("<td class='rep-body'>&nbsp;</td>");
      out.println("<td class='rep-body'>Arrears</td>");
      out.println("<td class='rep-body'>ODI</td>");
      out.println("<td class='rep-body'>NIL</td>");
      out.println("</tr>");
      out.println("<tr class='rep-body'>");
			if(more){
			out.println("<td colspan='4' class='rep-body'>&nbsp;"+rs.getString(2)+"</td>");
      out.println("<td class='rep-body' align='right'>&nbsp;"+nf.format(rs.getDouble(3))+"</td>");
			more = rs.next();			
			}
			else {
			out.println("<td colspan='4' class='rep-body'>&nbsp;</td>");
      out.println("<td class='rep-body'>&nbsp;</td>");
			}
			    
					rs_arrears = stmt4.executeQuery("SELECT SUM(BALANCE_TO_BE_RECEIVED) "+
 				   " FROM   "+m_schema_name+".AF_CO_PRO_INVOICE "+
 					 " WHERE  client_code='"+m_client_code+"' AND "+
         	 " VALUE_DATE <= (SELECT SYSDATE FROM DUAL)   AND "+
         	 " ACTIVE_STATUS='Y' ");
						
			    boolean more_arr = rs_arrears.next();			
					double m_arrears = 0;
					
      out.println("<td>Client</td>");
			if(more_arr){
			m_arrears = rs_arrears.getDouble(1);
      out.println("<td align='right'>"+nf.format(rs_arrears.getDouble(1))+"</td>");
			}
			
			  rs_odi = stmt_odi.executeQuery("SELECT  INVOICE_NO,"+ 
   			" CLIENT_CODE "+
 			  "	FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
 				" WHERE CLIENT_CODE='"+m_client_code+"' AND ACTIVE_STATUS='Y' ");
				boolean more_odi = rs_odi.next();				
			  double m_odi_amount = 0;
				
				while(more_odi){
				  
					 rs_odi2 = stmt_odi2.executeQuery(" SELECT "+
    			 " SUM((ODI_CAL_AMOUNT - ODI_SETTLED_AMOUNT) - NVL(ADJUSTED_AMOUNT,0)) "+
 					 " FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
 					 " WHERE INVOICE_NO='"+rs_odi.getString(1)+"' ");
					 boolean more_odi2 = rs_odi2.next();					
				   
					 	if(more_odi2){
						  m_odi_amount = m_odi_amount + rs_odi2.getDouble(1);
						}
						
					 more_odi = rs_odi.next();				
				}
			
      out.println("<td align='right'>"+nf.format(m_odi_amount)+"</td>");
      out.println("<td align='right'>&nbsp;</td>");
      out.println("</tr>");
      out.println("<tr class='rep-body'>");
			if(more){
			out.println("<td colspan='4' class='rep-body'>&nbsp;"+rs.getString(2)+"</td>");
      out.println("<td class='rep-body'>&nbsp;"+rs.getString(3)+"</td>");
			more = rs.next();			
			}
			else {
			out.println("<td colspan='4' class='rep-body'>&nbsp;</td>");
      out.println("<td class='rep-body'>&nbsp;</td>");
			}
      out.println("<td>Group</td>");
      out.println("<td align='right'>&nbsp;</td>");
      out.println("<td align='right'>&nbsp;</td>");
      out.println("<td align='right'>&nbsp;</td>");
      out.println("</tr>");
      out.println("<tr class='rep-body'>");
			if(more){
			out.println("<td colspan='4' class='rep-body'>&nbsp;"+rs.getString(2)+"</td>");
      out.println("<td class='rep-body' align='right'>&nbsp;"+nf.format(rs.getDouble(3))+"</td>");
			more = rs.next();			
			}
			else {
			out.println("<td colspan='4' class='rep-body'>&nbsp;</td>");
      out.println("<td class='rep-body'>&nbsp;</td>");
			}
      out.println("<td>Transaction</td>");
      out.println("<td>&nbsp;</td>");
      out.println("<td>&nbsp;</td>");
      out.println("<td><div align='right'>0</div></td>");
      out.println("</tr>");
      out.println("<tr class='rep-body'>");
			if(more){
			out.println("<td colspan='4' class='rep-body'>&nbsp;"+rs.getString(2)+"</td>");
      out.println("<td class='rep-body'>&nbsp;"+nf.format(rs.getDouble(3))+"</td>");
			more = rs.next();			
			}
			else {
			out.println("<td colspan='4' class='rep-body'>&nbsp;</td>");
      out.println("<td class='rep-body'>&nbsp;</td>");
			}
      out.println("<td>Total</td>");
      out.println("<td><div align='right'>"+nf.format(m_arrears)+"</div></td>");
      out.println("<td><div align='right'>"+nf.format(m_odi_amount)+"</div></td>");
      out.println("<td>&nbsp;</td>");
      out.println("</tr>");
			while(more){
			out.println("<tr class='rep-body'>");
			out.println("<td colspan='4' class='rep-body'>&nbsp;"+rs.getString(2)+"</td>");
      out.println("<td class='rep-body'>&nbsp;"+nf.format(rs.getDouble(3))+"</td>");
      out.println("<td>&nbsp;</td>");
      out.println("<td><div align='right'>&nbsp;</div></td>");
      out.println("<td><div align='right'>&nbsp;</div></td>");
      out.println("<td>&nbsp;</td>");
      out.println("</tr>");
			more = rs.next();			
			}
      out.println("<tr class='rep-body'>");
      out.println("<td colspan='10'>&nbsp;</td>");
      out.println("</tr>");
				
				
			rs = stmt.executeQuery("SELECT COUNT(DISTINCT C.ASSET_ID ) "+//A.PRO_INVOICE_NO
 		   " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING A ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
     	 " "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS C,"+m_schema_name+".AF_CO_MAS_SUB_MODLE D,"+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY E,  "+
		   " "+m_schema_name+".AF_CO_MAS_MAKE F	 "+
 		   " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
       " A.APPLICATION_NO=C.APPLICATION_NO AND "+     
		   " A.PRICING_NO=B.PRICING_NO AND "+	
		   //" C.ASSET_ID=B.ASSET_ID AND 	"+
       " A.APPLICATION_NO='"+m_application_no+"' AND "+ 
       " D.SUB_CODE=B.SUB_MODEL_CODE AND "+
		   " E.ITEM_SUB_CAT=A.ITEM_SUB_CAT_CODE AND "+
		 	 " A.MAKE_CODE=F.MAKE_CODE --ORDER BY ASSET_ID ");

			
				int m_asset_count = 0;
			  more = rs.next();			
				
				if(more){
				 m_asset_count = rs.getInt(1);
				}
				
			rs_asset = stmt.executeQuery("SELECT DISTINCT C.ASSET_ID "+ // A.PRO_INVOICE_NO
 		   " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING A ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
     	 " "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS C,"+m_schema_name+".AF_CO_MAS_SUB_MODLE D,"+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY E,  "+
		   " "+m_schema_name+".AF_CO_MAS_MAKE F	 "+
 		   " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
       " A.APPLICATION_NO=C.APPLICATION_NO AND "+     
		   " A.PRICING_NO=B.PRICING_NO AND "+	
		   //" C.ASSET_ID=B.ASSET_ID AND 	"+
       " A.APPLICATION_NO='"+m_application_no+"' AND "+ 
       " D.SUB_CODE=B.SUB_MODEL_CODE AND "+
		   " E.ITEM_SUB_CAT=A.ITEM_SUB_CAT_CODE ");
		 	 //" A.MAKE_CODE=F.MAKE_CODE --ORDER BY ASSET_ID ");			  
				
				int m_mount=0;
				String arr_model_code[];
				arr_model_code = new String[m_asset_count];
				more = rs_asset.next();
				
				while(more && m_mount<m_asset_count ){
					
							rs = stmt_asset_det.executeQuery("SELECT DISTINCT "+
				    	" DECODE(A.TRANSACION_TYPE,'FINLEASE','Finance Lease','OPELEASE','Operating Lease','HIREPURCH','Hire Purchase','LOANS','Loans','HIRING','FA','Factoring'), "+//1
				    	" INITCAP(A.INTEREST_TYPE), "+//2    
				    	" INITCAP(A.TRN_SUB_TYPE), "+//3
				    	" NVL(A.ITEM_CATEGORY,'-'), "+//4
				    	" NVL(E.DESCRIPTION,'-'), "+//5
				    	" NVL(DECODE(A.CONDITION_OF_ASSET,'RECON','Reconditioned','BRANDNEW','Brand New','REGISTERED','Registered','N/A','Not Applicable'),'-'), "+//6
				    	" NVL(F.MAKE_DESC,'-'), "+//7
				    	" NVL(INITCAP(C.MODEL_CODE),'-'), "+//8
				    	" NVL(C.SUB_MODEL_CODE,'-'), "+//9
				  	  " NVL(A.ENGINE_CAPACITY,'-'), "+//10
				  	  " NVL(B.CHASSIS_NO,'-'), "+//11
				  	  " NVL(B.TOTAL_AMOUNT,0), "+//12
				  	  " NVL(D.YEAR_OF_MANUFACTURE,0), "+//13
				  	  " NVL(C.QTY,0), "+//14
				  	  " NVL(A.VAT_PERCENTAGE,0), "+//15
				  	  " NVL(A.PERIOD,0), "+//16
				  	  " NVL(A.RATE,0), "+//17
				  	  " NVL(A.GROSS_AMOUNT,0), "+//18
				  	  " NVL(A.VAT_AMOUNT,0), "+//19
				  	  " NVL(A.NET_AMOUNT,0), "+//20
				  	  " NVL(A.AMI,0), "+//21
				  	  " NVL(A.NIBSM,0), "+//22
				  	  " NVL(A.RESIDUAL_VALUE,0), "+//23
							" NVL(A.VAT_APP,0), "+//24
							" NVL(C.ASSET_ID,'-') ASSET_ID, "+//25
							" NVL(A.PRICING_NO,'-'), " +//26
							" NVL(A.PRO_INVOICE_NO,'-') " +//27
				 		  " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING A ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
				      "	"+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS C,"+m_schema_name+".AF_CO_MAS_SUB_MODLE D,"+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY E, "+ 
						  " "+m_schema_name+".AF_CO_MAS_MAKE F"+	
				 		  " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
				      " A.APPLICATION_NO=C.APPLICATION_NO AND  "+    
							" A.PRO_INVOICE_NO=B.INVOICE_NO AND"+
						  " A.PRICING_NO=B.PRICING_NO AND "+	
							//" B.ASSET_ID=C.ASSET_ID AND "+
						  " C.ASSET_ID='"+rs_asset.getString(1)+"' AND "+	
				      //"  A.APPLICATION_NO='"+m_application_no+"' AND  "+
							//" A.PRO_INVOICE_NO = '"+rs_asset.getString(1)+"' AND"+
				      " D.SUB_CODE=B.SUB_MODEL_CODE AND "+
						  " E.ITEM_SUB_CAT=A.ITEM_SUB_CAT_CODE AND "+
							"	F.MAKE_CODE=(SELECT MAKE_CODE FROM "+m_schema_name+".AF_CO_MAS_MODEL WHERE MODEL_CODE=C.MODEL_CODE) ORDER BY ASSET_ID ");
						  //"	A.MAKE_CODE=F.MAKE_CODE ORDER BY ASSET_ID ");
						 
							boolean more_ass = rs.next();			
							
							String m_pricing_no="";
							String m_pro_invoice_no="";
		 					String m_asset_id="",m_temp_asset_id="";	
							String m_model_code="";
							
		 					double m_net_amt=0,m_vat_amt=0,m_vat_app=0,m_rent_based_on=0,m_vat_percent=0;	
							
							while(more_ass){
							  
								m_pricing_no = rs.getString(26);
								m_pro_invoice_no = rs.getString(27);
								m_vat_amt = rs.getDouble(19); 
								m_net_amt = rs.getDouble(20);
								m_vat_app = rs.getDouble(24);
								m_vat_percent = rs.getInt(15);
								
								  //Facility Start
						      out.println("<tr class='rep-body'>");
						      out.println("<td colspan='4'>Type of Facility  </td>");
						      out.println("<td>"+rs.getString(1)+" </td>");
						      out.println("<td colspan='5'><div align='center'><strong>Facility</strong></div></td>");
						  		out.println("</tr>");
						      out.println("<tr class='rep-body'>");
						      out.println("<td colspan='4'>Type of Vehicle </td>");
						      out.println("<td>"+rs.getString(5)+" </td>");
						      out.println("<td>&nbsp;</td>");
						      out.println("<td><div align='center'>Gross</div></td>");
						      out.println("<td><div align='center'>Net</div></td>");
						      out.println("<td><div align='center'>VAT</div></td>");
						      out.println("<td>&nbsp;</td>");
						      out.println("</tr>");
						      out.println("<tr class='rep-body'>");
						      out.println("<td colspan='4'>Status of Vehicle </td>");
						      out.println("<td>"+rs.getString(6)+"</td>");
						      out.println("<td>Net Cost </td>");
						      out.println("<td align='right'>"+nf.format(rs.getDouble(18))+" </td>");
						      out.println("<td align='right'>"+nf.format(rs.getDouble(20))+"</td>");
						      out.println("<td align='right'>"+nf.format(rs.getDouble(19))+"</td>");
						      out.println("<td>&nbsp;</td>");
						      out.println("</tr>");
						      out.println("<tr class='rep-body'>");
						      out.println("<td colspan='4'>Make</td>");
						      out.println("<td>"+rs.getString(7)+"</td>");
						      out.println("<td>Rental Based on </td>");
									
									  m_rent_based_on = ((m_net_amt*( m_vat_percent - m_vat_app)) + m_net_amt)/100;
										
						      out.println("<td> "+m_vat_percent+"% VAT </td>");
						      out.println("<td align='right'>"+nf.format(m_rent_based_on)+"</td>");
						      out.println("<td>&nbsp;</td>");
						      out.println("<td>&nbsp;</td>");
						      out.println("</tr>");
						      out.println("<tr class='rep-body'>");
						      out.println("<td colspan='4'>Model</td>");
						      out.println("<td>"+rs.getString(8)+"</td>");
						      out.println("<td>Period (Months) </td>");
						      out.println("<td align='right'>"+rs.getString(16)+" </td>");
						      out.println("<td>&nbsp;</td>");
						      out.println("<td>&nbsp;</td>");
						      out.println("<td>&nbsp;</td>");
						      out.println("</tr>");
						      out.println("<tr class='rep-body'>");
						      out.println("<td colspan='4'>Engine Capacity </td>");
						      out.println("<td>"+rs.getString(10)+" </td>");
						      out.println("<td>AMI</td>");
						      out.println("<td align='right'>"+nf.format(rs.getDouble(21))+"</td>");
						      out.println("<td align='right'>&nbsp;</td>");
						      out.println("<td align='right'>&nbsp;</td>");
						      out.println("<td align='right'>&nbsp;</td>");
						      out.println("</tr>");
						      out.println("<tr class='rep-body'>");
						      out.println("<td colspan='4'>Chassis No </td>");
						      out.println("<td>"+rs.getString(11)+"</td>");
						      out.println("<td>(NIBSM)/Residual</td>");
						      out.println("<td align='right'>"+nf.format(rs.getDouble(22))+"</td>");
						      out.println("<td align='right'>"+nf.format(rs.getDouble(23))+"</td>");
						      out.println("<td>&nbsp;</td>");
						      out.println("<td>&nbsp;</td>");
						      out.println("</tr>");
						      out.println("<tr class='rep-body'>");
						      out.println("<td colspan='4'>Value</td>");
						      out.println("<td>"+nf.format(rs.getDouble(12))+"</td>");
						      out.println("<td><strong>Installment Structure</strong></td>");
						      out.println("<td colspan='3'><div align='center' ><strong> Rental Stream</strong></div></td>");
						      out.println("<td>&nbsp;</td>");
						      out.println("</tr>");
										
										rs_rental = stmt1.executeQuery("SELECT  COUNT(GRENTAL_AMOUNT),GRENTAL_AMOUNT,NET_RENTAL_AMOUNT,(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT ) "+
						 					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
						 					" WHERE APPLICATION_NO='"+m_application_no+"'  AND PRICING_NO='"+m_pricing_no+"' AND PRO_INVOICE_NO='"+m_pro_invoice_no+"' "+
											" GROUP BY  GRENTAL_AMOUNT,NET_RENTAL_AMOUNT,(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT )");
										
									 	 boolean more_rental = rs_rental.next();
											
										 double m_gross_amout=0,m_net_amount=0,m_vat_amount=0;	
										 double m_gross=0;
										 double m_temp_gross=0;
										 int m_count=0;
										
									if(!more_rental){
									  out.println("<tr class='rep-body'>");
						      	out.println("<td colspan='4'>Year of Manufacture </td>");
						   			out.println("<td>"+rs.getInt(13)+"</td>");
						      	out.println("<td>"+m_count+" Rentals of </td>");
						      	out.println("<td>0</td>");
						      	out.println("<td>0</td>");
						      	out.println("<td>0</td>");
						      	out.println("<td>&nbsp;</td>");
										out.println("</tr>");
						   		}
									while(more_rental){
									  m_count++;
										out.println("<tr class='rep-body'>");
										if(m_count==1){
						      	out.println("<td colspan='4'>Year of Manufacture </td>");
						   			out.println("<td>"+rs.getInt(13)+"</td>");
										}
										else {
										out.println("<td colspan='4'>&nbsp;</td>");
						   			out.println("<td>&nbsp;</td>");
										}
						      	out.println("<td>"+rs_rental.getInt(1)+" Rentals of </td>");
						      	out.println("<td align='right'>"+nf.format(rs_rental.getDouble(2))+"</td>");
						      	out.println("<td align='right'>"+nf.format(rs_rental.getDouble(3))+"</td>");
						      	out.println("<td align='right'>"+nf.format(rs_rental.getDouble(4))+"</td>");
						      	out.println("<td>&nbsp;</td>");
										out.println("</tr>");
										more_rental = rs_rental.next();
									}

						      //out.println("</tr>");
						      out.println("<tr class='rep-body'>");
						      out.println("<td colspan='4'><strong>No. Of Units </strong></td>");
						      out.println("<td><strong>"+rs.getInt(14)+"</strong></td>");
						      out.println("<td><strong>"+rs.getString(2)+" "+rs.getString(3)+"**</strong>  </td>");
						      out.println("<td>&nbsp;</td>");
						      out.println("<td>&nbsp;</td>");
						      out.println("<td>&nbsp;</td>");
						      out.println("<td>&nbsp;</td>");
						      out.println("</tr>");
						      out.println("<tr class='rep-body'>");
						      out.println("<td colspan='4'>Asset cover </td>");
						      out.println("<td>25%</td>");
						      out.println("<td><strong>True Interest Rate(TIR) </strong></td>");
						      out.println("<td>"+nf.format(rs.getDouble(17))+" %</td>");
						      out.println("<td>&nbsp;</td>");
						      out.println("<td>&nbsp;</td>");
						      out.println("<td>&nbsp;</td>");
						      out.println("</tr>");
									//Facility End
									
							    more_ass = rs.next();			
							}
							
					 m_mount++;
					 more = rs_asset.next();	

				}

      out.println("<tr class='rep-body'>");
      out.println("<td colspan='10'>&nbsp;</td>");
      out.println("</tr>");
      out.println("<tr class='rep-body'>");
      out.println("<td colspan='3'><strong>Expenses included in to the Lease Rental </strong></td>");
      out.println("<td width='9%' align='center' >Increase/Dec</td>");
      out.println("<td width='7%' align='center' >Year 1 </td>");
      out.println("<td width='6%' align='center' >Year 2 </td>");
      out.println("<td width='6%' align='center' > Year 3 </td>");
      out.println("<td width='6%' align='center' > Year 4 </td>");
      out.println("<td width='6%' align='center' >Year 5 </td>");
      out.println("<td width='6%' align='center' > Year 6 </td>");
      out.println("</tr>");
					
					/*
 					rs = stmt.executeQuery(" SELECT "+
  					 " A.PRICING_NO, "+//1
						 " NVL(A.SUB_CHAGE_CODE,'-'),"+	//2
  					 " NVL(B.DESCRIPTION,'-'), "+//3
  					 " NVL(A.PERCENT_AMOUNT,0), "+//4
  					 " NVL(A.AMOUNT,0), "+//5
  					 " NVL(A.YEAR_NO,0) "+//6
 					" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_MAINTEIN A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
 					" WHERE A.APPLICATION_NO='"+m_application_no+"' AND B.SUB_TYPE_CODE=A.SUB_CHAGE_CODE ORDER BY A.SUB_CHAGE_CODE ");
			    */
					
					rs = stmt.executeQuery("SELECT  DISTINCT "+
						 " NVL(A.SUB_CHAGE_CODE,'-') SUB_CHAGE_CODE,	"+
  					 " NVL(B.DESCRIPTION,'-'), "+
						 " NVL(A.PERCENT_AMOUNT,0) "+	
 					   " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_MAINTEIN A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
 					   " WHERE A.APPLICATION_NO='"+m_application_no+"' AND B.SUB_TYPE_CODE=A.SUB_CHAGE_CODE ORDER BY SUB_CHAGE_CODE ");
	
			   	more = rs.next();			
					
					double m_tot_yr1=0,m_tot_yr2=0,m_tot_yr3=0,m_tot_yr4=0,m_tot_yr5=0,m_tot_yr6=0;					
					double m_tot_percent=0;
					
					while(more){					
						rs_charges = stmt3.executeQuery("SELECT  DISTINCT "+
					  "	NVL(YEAR_NO,0) YEAR_NO, "+
            " NVL(PERCENT_AMOUNT,0),"+
            " NVL(AMOUNT,0) "+
 					  " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_MAINTEIN A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
 					  " WHERE A.APPLICATION_NO='"+m_application_no+"' AND B.SUB_TYPE_CODE=A.SUB_CHAGE_CODE "+
            " AND A.SUB_CHAGE_CODE = '"+rs.getString(1)+"' ORDER BY YEAR_NO ");
					
					 boolean more_year = rs_charges.next();
					 int no_years=0;
						
									out.println("<tr class='rep-body'>");
									out.println("<td colspan='3'>"+rs.getString(2)+"</td>");
									out.println("<td align='right'>"+nf.format(rs.getDouble(3))+"</td>");
									m_tot_percent = m_tot_percent + rs.getDouble(3);
									
							while(more_year){
							
									if(rs_charges.getInt(1)==0){
									out.println("<td align='right'>"+nf.format(rs_charges.getDouble(3))+"</td>");	
									m_tot_yr1=m_tot_yr1+rs_charges.getDouble(3);
									no_years = no_years+1;
									}
					      	if(rs_charges.getInt(1)==1){
					      	out.println("<td align='right'>"+nf.format(rs_charges.getDouble(3))+"</td>");
									m_tot_yr2=m_tot_yr2+rs_charges.getDouble(3);
									no_years = no_years+1;
									}
									if(rs_charges.getInt(1)==2){
					      	out.println("<td align='right'>"+nf.format(rs_charges.getDouble(3))+"</td>");
									m_tot_yr3=m_tot_yr3+rs_charges.getDouble(3);
									no_years = no_years+1;
									}
									if(rs_charges.getInt(1)==3){
					      	out.println("<td align='right'>"+nf.format(rs_charges.getDouble(3))+"</td>");
									m_tot_yr4=m_tot_yr4+rs_charges.getDouble(3);
									no_years = no_years+1;
									}
									if(rs_charges.getInt(1)==4){
					      	out.println("<td align='right'>"+nf.format(rs_charges.getDouble(3))+"</td>");
									m_tot_yr5=m_tot_yr5+rs_charges.getDouble(3);
									no_years = no_years+1;
									}
									if(rs_charges.getInt(1)==5){
					      	out.println("<td align='right'>"+nf.format(rs_charges.getDouble(3))+"</td>");
									m_tot_yr6=m_tot_yr6+rs_charges.getDouble(3);
									no_years = no_years+1;
									}
									
								 more_year = rs_charges.next();	
							}		
									if(no_years==6){
					  	
									}
									else if(no_years==5){
									out.println("<td>&nbsp;</td>");
									}
									else if(no_years==4){
									out.println("<td>&nbsp;</td>");
					      	out.println("<td>&nbsp;</td>");
									}
									else if(no_years==3){
									out.println("<td>&nbsp;</td>");
					      	out.println("<td>&nbsp;</td>");
					      	out.println("<td>&nbsp;</td>");
									}
									else if(no_years==2){
									out.println("<td>&nbsp;</td>");
					      	out.println("<td>&nbsp;</td>");
					      	out.println("<td>&nbsp;</td>");
									out.println("<td>&nbsp;</td>");
									}
									else if(no_years==1){
									out.println("<td>&nbsp;</td>");
					      	out.println("<td>&nbsp;</td>");
					      	out.println("<td>&nbsp;</td>");
									out.println("<td>&nbsp;</td>");
									out.println("<td>&nbsp;</td>");
									}
									else if(no_years==0){
									out.println("<td>&nbsp;</td>");
					      	out.println("<td>&nbsp;</td>");
					      	out.println("<td>&nbsp;</td>");
									out.println("<td>&nbsp;</td>");
									out.println("<td>&nbsp;</td>");
									out.println("<td>&nbsp;</td>");
									}
									out.println("</tr>");		
									
					 		more = rs.next();	
						
					}
			
      out.println("<tr class='rep-body'>");
      out.println("<td colspan='3'>Total </td>");
      out.println("<td align='right'>"+nf.format(m_tot_percent)+"</td>");
      out.println("<td align='right'>"+nf.format(m_tot_yr1)+"</td>");
      out.println("<td align='right'>"+nf.format(m_tot_yr2)+"</td>");
      out.println("<td align='right'>"+nf.format(m_tot_yr3)+"</td>");
      out.println("<td align='right'>"+nf.format(m_tot_yr4)+"</td>");
      out.println("<td align='right'>"+nf.format(m_tot_yr5)+"</td>");
      out.println("<td align='right'>"+nf.format(m_tot_yr6)+"</td>");
      out.println("</tr>");
			//*********
			out.println("<tr class='rep-body'>");
      out.println("<td colspan='10'>&nbsp;</td>");
      out.println("</tr>");
			out.println("<tr class='rep-body'>");
      out.println("<td><strong>Amortised Charges </strong></td>");
      out.println("<td colspan='2'><div align='right'><strong>Amount</strong></div></td> ");
      out.println("<td colspan='7'>&nbsp;</td> ");
      out.println("</tr>");
			
			
					rs = stmt.executeQuery(" SELECT DISTINCT "+
					   " A.PRICING_NO,"+//1
					   " A.SUB_CHAGE_CODE,"+//2
					   " B.DESCRIPTION,"+//3
					   " A.AMOUNT,"+//4
					   " A.CHARGE_TYPE" +//5
					" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
					" WHERE A.APPLICATION_NO='"+m_application_no+"' "+
					" AND A.SUB_CHAGE_CODE=B.SUB_TYPE_CODE(+) AND A.CHARGE_TYPE='AMO' "+
					" ORDER BY CHARGE_TYPE ");
 
					more = rs.next();
					double m_amor_tot=0;
					
			while(more){
			m_amor_tot=m_amor_tot+rs.getDouble(4);
      out.println("<tr class='rep-body'>");
      out.println("<td>"+rs.getString(3)+"</td> ");
      out.println("<td colspan='2' align='right' >"+nf.format(rs.getDouble(4))+"</td> ");
      out.println("<td colspan='7'>&nbsp;</td>");
      out.println("</tr>");
			more = rs.next();
			}
			
  		out.println("<tr class='rep-body'>");
      out.println("<td>Total</td> ");
      out.println("<td colspan='2' align='right' >"+nf.format(m_amor_tot)+"</td> ");
      out.println("<td colspan='7'>&nbsp;</td> ");
      out.println("</tr> ");
			out.println("<tr class='rep-body'>");
      out.println("<td colspan='10'>&nbsp;</td>");
      out.println("</tr>");
      out.println("<tr class='rep-body'>");
      out.println("<td><strong>Upfront Charges</strong></td> ");
      out.println("<td colspan='2'><div align='right'><strong>Amount</strong></div></td> ");
      out.println("<td colspan='7'>&nbsp;</td> ");
      out.println("</tr>");
					
					rs = stmt.executeQuery(" SELECT DISTINCT "+
					   " A.PRICING_NO,"+//1
					   " A.SUB_CHAGE_CODE,"+//2
					   " B.DESCRIPTION,"+//3
					   " A.AMOUNT,"+//4
					   " A.CHARGE_TYPE" +//5
					" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
					" WHERE A.APPLICATION_NO='"+m_application_no+"' "+
					" AND A.SUB_CHAGE_CODE=B.SUB_TYPE_CODE(+) AND A.CHARGE_TYPE='INV' "+
					" ORDER BY CHARGE_TYPE ");
 
					more = rs.next();
					double m_upfro_tot=0;
			
      while(more){
			m_upfro_tot=m_upfro_tot+rs.getDouble(4);
      out.println("<tr class='rep-body'>");
      out.println("<td>"+rs.getString(3)+"</td> ");
      out.println("<td colspan='2' align='right' >"+nf.format(rs.getDouble(4))+"</td> ");
      out.println("<td colspan='7'>&nbsp;</td>");
      out.println("</tr>");
			more = rs.next();
			}
			
  		out.println("<tr class='rep-body'>");
      out.println("<td >Total</td> ");
      out.println("<td  colspan='2' align='right' >"+nf.format(m_upfro_tot)+"</td> ");
      out.println("<td  colspan='7'>&nbsp;</td>");
  		out.println("</tr>");
			
      out.println("<tr class='rep-body'>");
      out.println("<td colspan='10'>&nbsp;</td>");
      out.println("</tr>");
      out.println("<tr class='rep-body'>");
      out.println("<td>Credit Scoring &amp; Remarks </td>");
      out.println("<td colspan='2'>"+m_credit_score+"</td>");
      out.println("<td colspan='2'>"+m_remark+"</td>");
      out.println("<td rowspan='5'>&nbsp;</td>");
			out.println("<td colspan='4' rowspan='5'> ");

						
					rs = stmt.executeQuery(" SELECT "+
  				 " APPLICATION_NO, "+
  				 " NVL(FLOOR((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY'))/365 ),0) "+
 					 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
 					 " WHERE APPLICATION_NO='"+m_application_no+"' ");
					
			  	more = rs.next();
					int m_yrs = 0;
					
					if(more){
					  m_yrs = rs.getInt(2);
					}
					
					 rs_return = stmt_return.executeQuery("SELECT "+
  				 " REC_NO, "+
  				 " CLIENT_CODE, "+
  				 " REC_AMOUNT "+
 					 " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
 					 " WHERE CLIENT_CODE='"+m_client_code+"' ");
						
					 boolean more_return = rs_return.next(); 	
					 double m_return_val=0;	
					 int m_return_count = 0;
						
						while(more_return) {
						
							 rs_return2 = stmt_return2.executeQuery(" SELECT "+
  						 " AMOUNT "+
 							 " FROM "+m_schema_name+".AF_CO_PRO_RETURN_DETAILS "+
 							 " WHERE RECEIPT_NO='"+rs_return.getString(1)+"' ");
							 boolean more_return2 = rs_return2.next(); 	
							
							 while(more_return2){
								 m_return_val = m_return_val + rs_return2.getDouble(1);
								 m_return_count++;
								 more_return2 = rs_return2.next(); 	
							 }	
								
							more_return = rs_return.next(); 		
						}
					
					
					  rs = stmt.executeQuery("SELECT DISTINCT C.MODEL_CODE "+
 		    		" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING A ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
     			  " "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS C,"+m_schema_name+".AF_CO_MAS_SUB_MODLE D,"+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY E, "+ 
		  		  " "+m_schema_name+".AF_CO_MAS_MAKE F	"+ 
		 		    " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
		        " A.APPLICATION_NO=C.APPLICATION_NO AND "+     
		  		  " A.PRICING_NO=B.PRICING_NO AND "+	
    			  " A.APPLICATION_NO='"+m_application_no+"' AND "+ 
    			  " D.SUB_CODE=B.SUB_MODEL_CODE AND "+
		  		  " E.ITEM_SUB_CAT=A.ITEM_SUB_CAT_CODE AND "+
		 			  " A.MAKE_CODE=F.MAKE_CODE ");
					  more = rs.next();
					
					while(more){
					    
						  rs_model = stmt_model.executeQuery(" SELECT DISTINCT "+
   						"	APPLICATION_NO "+
 							" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
 							" WHERE MODEL_CODE='"+rs.getString(1)+"' ORDER BY APPLICATION_NO DESC ");
						
						 boolean more_model=rs_model.next(); 
						 int m_app_count = 0;
						 double m_tot_amout = 0;
						 double m_avg = 0;
							
							while(more_model && m_app_count<5 ){
							   
									rs_model2 = stmt_model2.executeQuery(" SELECT APPLICATION_NO, "+
  								 " SUM(GRENTAL_AMOUNT) "+
 									 " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
 									 " WHERE APPLICATION_NO='"+rs_model.getString(1)+"' GROUP BY APPLICATION_NO ");
									 
								 	boolean more_mod = rs_model2.next();    
									if(more_mod){
									 m_tot_amout = m_tot_amout + rs_model2.getDouble(2);
									}
									
							  m_app_count++;
								more_model=rs_model.next(); 
							}					
					     
								m_avg=m_tot_amout/m_app_count;
								
					 out.println(" Average Value of the last "+m_app_count+" vehicles "+nf.format(m_avg)+"<br>");
					 more = rs.next();
					}

      out.println("Cheque returns "+m_return_count+" &amp; Value "+nf.format(m_return_val)+"<br>ODI collected  "+nf.format(m_odi_amount)+" <br> Relationship with LAKDL "+m_yrs+" yrs </td>");
      out.println("</tr>");
			
			    rs = stmt.executeQuery("SELECT  INVOICE_NO, "+
  				" FINANCE_NO, "+
  				" VALUE_DATE, "+
  			  " CLIENT_CODE "+
 				  " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
 				  " WHERE CLIENT_CODE='"+m_client_code+"' AND BALANCE_TO_BE_RECEIVED>0 ");
					
					more = rs.next();			
					
      out.println("<tr class='rep-body'>");
      out.println("<td>Repayment Status</td>");
			if(more){
      out.println("<td colspan='2'>Yes</td>");
			}
			else {
			out.println("<td colspan='2'>No</td>");
			}
      out.println("<td colspan='2' rowspan='4'>&nbsp;</td>");
      out.println("</tr>");
					
					rs = stmt.executeQuery("SELECT "+
  				 " CLIENT_CODE, "+
					 " "+m_schema_name+".AF_CO_GET_CLIENT_EXPOSURE(CLIENT_CODE) "+	
 					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
 					" WHERE APPLICATION_NO='"+m_application_no+"' ");
					more = rs.next();			
			
      out.println("<tr class='rep-body'>");
      out.println("<td>Guarantors Exposure </td>");
			if(more){
      out.println("<td colspan='2'>"+nf.format(rs.getDouble(2))+"</td>");
			}
			else {
			out.println("<td colspan='2'>0</td>");
			}
      out.println("</tr>");
				
				  //Guarantor Details
					rs = stmt.executeQuery(" SELECT "+
  			  " APPLICATION_NO,"+
    			" GUARANTOR_CODE,"+
  			  " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(GUARANTOR_CODE),'-') "+	 		
 					" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR "+
 				  " WHERE APPLICATION_NO='"+m_application_no+"' ");
					more = rs.next();			
					
					boolean m_repay_gua = false;
					
					while(more){
					
					    rs_gua = stmt_gua.executeQuery("SELECT  INVOICE_NO, "+
  						" FINANCE_NO, "+
							" VALUE_DATE, "+
  					  " CLIENT_CODE "+
 						  " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
 						  " WHERE CLIENT_CODE='"+rs.getString(2)+"' AND BALANCE_TO_BE_RECEIVED>0 ");
					    boolean more_gua = rs_gua.next();			
						
						if(more_gua){
						  m_repay_gua = true;
						}
					
					more = rs.next();			
					
					}
			
      out.println("<tr class='rep-body'>");
      out.println("<td >Guarantors Repayment Status </td>");
			if(m_repay_gua == true){
      out.println("<td colspan='2'>Yes</td>");
			}
			else {
			out.println("<td colspan='2'>No</td>"); 
			}
      out.println("</tr>");
      out.println("<tr>");
      out.println("<td>CRIB Status </td>");
      out.println("<td>Client</td>");
      out.println("<td>Guarantor</td>");
      out.println("</tr>");
				
							rs = stmt.executeQuery(" SELECT B.NAME,"+    
    						" NVL(A.AUTHORAIZED_USER,'-'), "+
  						  " NVL(TO_CHAR(A.ENT_DATE,'HH:MM:SS'),'-'), "+
  						  " NVL(A.REMARK,'-') "+   
 							  " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER B "+
 							  " WHERE A.APPLICATION_NO='"+m_application_no+"' AND A.AUTHORAIZED_USER=B.USER_ID ");
							more = rs.next();			
			
      out.println("<tr class='rep-body'>");
      out.println("<td colspan='10'><strong>APPROVAL PROCESS</strong></td>");
      out.println("</tr>");
      out.println("<tr class='rep-body'>");
      out.println("<td>Name</td>");
      out.println("<td>System ID </td>");
      out.println("<td>Time</td>");
      out.println("<td colspan='7'><div align='center'>Conditions</div></td>");
      out.println("</tr>");
			
			while(more){
      out.println("<tr class='rep-body'>");
      out.println("<td>"+rs.getString(1)+"</td>");
      out.println("<td>"+rs.getString(2)+"</td>");
      out.println("<td>"+rs.getString(3)+"</td>");
      out.println("<td colspan='7'>"+rs.getString(4)+"</td>");
      out.println("</tr>");
			more = rs.next();			
			}
			
						rs_remarks = stmt_remarks.executeQuery(" SELECT APPLICATION_NO, NVL(INITCAP(REMARK),'N/A') "+
      " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
		  " WHERE STATUS='ENTERED' AND APPLICATION_NO='"+m_application_no+"' ");
			boolean more1 = rs_remarks.next();

			
			
			out.println("<tr class='rep-body'>");
      out.println("<td colspan='10'><strong>REMARKS</strong></td>");
      out.println("</tr>");
			out.println("<tr class='rep-body'>");
      out.println("<td valign='top'>Application Process Stage</td>");
			
			
		/*	rs_remarks = stmt_remarks.executeQuery(" SELECT APPLICATION_NO, NVL(INITCAP(REMARK),'N/A') "+
      " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
		  " WHERE STATUS='ENTERED' AND APPLICATION_NO='"+m_application_no+"' ");
			boolean more1 = rs_remarks.next();
			
      */
			if(more1){
			out.println("<td colspan='10'><div align='center'>"+rs_remarks.getString(2)+"</div></td>");
			}else if(!more1){
			out.println("<td colspan='10'><div align='center'>&nbsp - &nbsp</div></td>");
			}
      out.println("</tr>");
						
			
      out.println("</table>");
			out.println("</font></p>");
			//out.println("</blockquote>");		
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

