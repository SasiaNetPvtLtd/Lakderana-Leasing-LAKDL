//ID         :
//SCREEN NAME:Document Printing - Asset Replacement Document 1
//CREATED BY :Sandun Jayathilake	
//DATE/TIME  : 19-11-2008
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Document_Asset_Replace_3 extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt5,stmt6,stmt7,stmt8;
	java.text.NumberFormat nf;
	
  public ResultSet rs,rs1,rs2,rs3,rs4,rs5,rs6,rs7,rs8;
 	

	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print;
	public double m_amount_due;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
		
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);	
			
			String m_full_name="";
			String m_add1="";
			String m_add2="";
			String m_city_name="";
			String m_title="";
			String m_client_type=""; 
			String m_start_date="";
			String m_partner_name [];
			int m_data_count=0;
		  String m_status ="";
			
			
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			
			String m_eng_no="";
			String m_cha_no="";
			String m_reg_no="";
			String m_discrp="";
			String m_produce_year="";
			
			double m_valuation_value = 0.0;											
			double m_monthly_rental = 0.0;											
			double m_1st_rental = 0.0;
			double m_prv_valuation_value = 0.0;
			double m_financed_amt = 0.0;
			int m_paid_perid = 0;
			int m_no_arr_months =0;
			
			double m_cap_oustanding= 0.0;
			double m_final_rental= 0.0;
			int m_inst_no =0;
			
			String m_prv_eng_no="";
			String m_prv_cha_no="";
			String m_prv_reg_no="";
			String m_prv_discrp="";
			String m_prv_produce_year = "";
			
		 String m_chksql = req.getParameter("chksql");
			
  	 if(m_chksql.trim().equals("main_page")){
							
			stmt = conn.createStatement ();
			stmt1 = conn.createStatement ();
			stmt2 = conn.createStatement ();
			stmt3 = conn.createStatement ();	
			stmt4 = conn.createStatement ();
			stmt5 = conn.createStatement ();
			stmt6 = conn.createStatement ();	
			stmt7 = conn.createStatement ();	
			stmt8 = conn.createStatement ();	
						
			String m_application_no = req.getParameter("application_no");
			String m_client_code	  =req.getParameter("client_code");		
			String m_document_code	=req.getParameter("document_code");	
			String m_print=req.getParameter("print");			
			
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'fmddth') || ' ' || TO_CHAR(SYSDATE, 'Month')|| TO_CHAR(SYSDATE, 'YYYY') LETTER_DATE FROM DUAL ");
				            
				boolean more = rs.next();
				if(more){
				m_Letter_date=rs.getString(1);
				}
				
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
				
		
					
					
					  rs = stmt.executeQuery(" SELECT "+
        												" NVL(UPPER(TITLE),''), "+
																" DECODE(CLIENT_TYPE,'I',NVL(UPPER(TITLE),' ')||' '||NVL(UPPER(FULL_NAME),' '),'C',NVL(UPPER(FULL_NAME),' ')), "+ //MODIFIED BY Chandana on 26/07/2007 For Ref No.748
																" REPLACE(REPLACE(NVL(UPPER(ADDRESS1),' '),'-',' '),'null',' '),"+
																" REPLACE(REPLACE(NVL(UPPER(ADDRESS2),' '),'-',' '),'null',' '),"+
                                " NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' ') "+
																" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
                                " WHERE   CLIENT_CODE = "+
                                " (SELECT "+
		                            " CLIENT_CODE "+
                                " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
                                " WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"'))");
    
			 more = rs.next();		
				
				if(more){
				m_title=rs.getString(1);
				m_full_name=rs.getString(2);
				m_add1=rs.getString(3);
				m_add2=rs.getString(4);
				m_city_name=rs.getString(5);
								
				}
				
				if(!m_full_name.equals(" "))
				{
				m_full_name=m_full_name+",";
				}
				
				if(!m_add1.equals(" "))
				{
				m_add1=m_add1+",";
				}
				if(!m_add2.equals(" "))
				{
				m_add2=m_add2+",";
				}		
				
				 rs=stmt.executeQuery (" SELECT "+
        " NVL(FINANCE_NO,'-'), "+
				" NVL(TOTAL_FINANCE_AMOUNT,0), "+
				" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) "+
		    " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
        " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
				
				more = rs.next();
				String m_lessee_name = "";
			  double m_leased_amt = 0.0;
				
			  if(more){
			  m_finance_no=rs.getString(1);
			  m_leased_amt = rs.getDouble(2);
				m_lessee_name = rs.getString(3);
			  }
									


				 rs=stmt.executeQuery (" SELECT "+
        "  NVL(FINANCE_NO,'-'), "+
				"  TO_CHAR(ACTIVATED_DATE, 'fmddth') || ' ' || TO_CHAR(ACTIVATED_DATE, 'Month')|| TO_CHAR(ACTIVATED_DATE, 'YYYY') START_DATE "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
        " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
				
				more = rs.next();
			
			if(more){
			  m_finance_no=rs.getString(1);
				m_start_date=rs.getString(2);
			  
			  }
								
							
			out.println("<html><head>"); 
			out.println("<title>Acceptance Receipt </title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
		  out.println("<script>");
			
			out.println("function save_data(){");			  
			out.println("m_table.innerHTML=\"\" ");		
			out.println("window.print();");
			out.println("}");		
		
			out.println("function add_button(){");
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
			out.println("if(document.Form1.hid_client_type.value=='CO'){");
			out.println("m_letter='<tr><td width=\"*%\" align=\"center\" class=\"rep-body1\" ><b>To be typed on company letter head</b></td></tr>';"); 
	  	out.println("		}else{");
			out.println("m_letter=\"\" ");
			out.println("		}");
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_letter+'</table>';");
			}
			else
			{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';");
			out.println("if(document.Form1.hid_client_type.value=='CO'){");
			out.println("m_letter='<tr><td width=\"*%\" align=\"center\" class=\"rep-body1\" ><b>To be typed on company letter head</b></td></tr>';"); 
	  	out.println("		}else{");
			out.println("m_letter=\"\" ");
			out.println("		}");
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+m_letter+'</table>';");
			}
			out.println("}");
			
			out.println("</script>");
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");
			out.println("<body bgcolor='white'><br>");
							
			out.println("<form name='Form1'>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_client_type' VALUE=\"\">");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");			
			
			
																
						//get proposed Asset											
					rs=stmt.executeQuery (" SELECT "+
					        " nvl(B.ENGINE_NO,'-'), "+
									" nvl(B.CHASSIS_NO,'-'), "+
		              " NVL(B.REG_NO,'-'), "+								 
									" INITCAP(C.MAKE_DESC||' '||D.DESCRIPTION ||' '||H.DESCRIPTION) MODEL_DESC, "+ 
								  " NVL(D.YEAR_OF_MANUFACTURE,''), "+
								  " NVL(B.SUB_MODEL_CODE,' '), "+ 
								  " INITCAP(H.DESCRIPTION) ITEM_SUB_CAT_DESC, "+
								  " C.MAKE_CODE, "+
								  " F.ITEM_SUB_CAT,"+
								  " UPPER(E.VENDOR_CODE),"+
								  " UPPER(E.BRANCH), "+
								  " INITCAP(G.NAME) "+
									" FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+
								  " "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
								  " "+m_schema_name+".AF_CO_MAS_MAKE C, "+
								  " "+m_schema_name+".AF_CO_MAS_SUB_MODLE D, "+
								  " "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION E, "+
								  " "+m_schema_name+".AF_CO_MAS_MODEL F , "+
								  " "+m_schema_name+".AF_CO_MAS_VENDORS G , "+
								  " "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY H "+
								  " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
								  " A.ACTIVE_STATUS='Y' AND "+
								  " B.ACTIVE_STATUS='Y' AND "+
								  " A.APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
								  " A.ASSET_ID=B.ASSET_ID AND "+
								  " C.MAKE_CODE=(SELECT "+
								  " MAKE_CODE "+
								  " FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
								  " WHERE "+
								  " MODEL_CODE IN ( SELECT "+
								  " MODEL_CODE "+
								  " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
								  " WHERE INVOICE_NO=B.INVOICE_NO AND B.ACTIVE_STATUS='Y' "+
								  " )) AND "+
								  " D.SUB_CODE=B.SUB_MODEL_CODE AND "+
								  " UPPER(E.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
								  " UPPER(G.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
								  " UPPER(F.ITEM_SUB_CAT) =UPPER(H.ITEM_SUB_CAT) AND "+
								  " UPPER(E.BRANCH)=UPPER(B.BRANCH_ID) AND "+
								  " B.MODEL_CODE=F.MODEL_CODE "+
									" AND B.ENT_DATE IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
							    " GROUP BY APPLICATION_NO) ");									
							
				
				if(rs.next()){ 
				  m_eng_no=rs.getString(1);
					m_cha_no=rs.getString(2);
					m_reg_no=rs.getString(3);
				  m_discrp=rs.getString(4);
				  m_produce_year = rs.getString(5);		
					
			}
			
			//get previouse Asset		
				   rs3=stmt3.executeQuery (" SELECT   nvl(B.ENGINE_NO,'-'), "+
															   " nvl(B.CHASSIS_NO,'-'), "+
									               " NVL(B.REG_NO,'-'), "+ 								 
															   " INITCAP(C.MAKE_DESC||' '||D.DESCRIPTION ||' '||H.DESCRIPTION) MODEL_DESC,  "+
															   " NVL(D.YEAR_OF_MANUFACTURE,''), "+
															   " NVL(B.SUB_MODEL_CODE,' '),  "+
															   " INITCAP(H.DESCRIPTION) ITEM_SUB_CAT_DESC, "+
															   " C.MAKE_CODE, "+
															   " F.ITEM_SUB_CAT, "+
															   " UPPER(E.VENDOR_CODE), "+
															   " UPPER(E.BRANCH), "+
															   " INITCAP(G.NAME) "+
															   " FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+ 
															   " "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DET_BK B, "+
															   " "+m_schema_name+".AF_CO_MAS_MAKE C, "+
															   " "+m_schema_name+".AF_CO_MAS_SUB_MODLE D, "+
															   " "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION E, "+
															   " "+m_schema_name+".AF_CO_MAS_MODEL F , "+
															   " "+m_schema_name+".AF_CO_MAS_VENDORS G , "+
															   " "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY H "+
															   " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
															   " A.ACTIVE_STATUS='Y' AND "+
															   " B.ACTIVE_STATUS='Y' AND "+
															   " A.APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
															   " A.ASSET_ID=B.ASSET_ID AND "+
															   " C.MAKE_CODE=(SELECT "+
															   " MAKE_CODE "+
															   " FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
															   " WHERE "+
															   " MODEL_CODE IN ( SELECT "+
															   " MODEL_CODE "+
															   " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DET_BK "+
															   " WHERE INVOICE_NO=B.INVOICE_NO AND B.ACTIVE_STATUS='Y' "+
															   " )) AND "+
															   " D.SUB_CODE=B.SUB_MODEL_CODE AND "+
															   " UPPER(E.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
															   " UPPER(G.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
															   " UPPER(F.ITEM_SUB_CAT) =UPPER(H.ITEM_SUB_CAT) AND "+
															   " UPPER(E.BRANCH)=UPPER(B.BRANCH_ID) AND "+
															   " B.MODEL_CODE=F.MODEL_CODE "+
							                   " AND 	B.ENT_DATE IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DET_BK "+
							                   " GROUP BY APPLICATION_NO) ");
				
				
				if(rs3.next()){ 
				  m_prv_eng_no=rs3.getString(1);
					m_prv_cha_no=rs3.getString(2);
					m_prv_reg_no=rs3.getString(3);
				  m_prv_discrp=rs3.getString(4);
					m_prv_produce_year=rs3.getString(5);
				  }
						
			rs4=stmt4.executeQuery (" SELECT A.APPLICATION_NO, "+//1
	       											" A.INSTALLMENT_NO, "+//2
												      " A.GRENTAL_AMOUNT "+//3      
												  		" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A "+
												  		" WHERE A.APPLICATION_NO=UPPER('"+m_application_no+"') "+
															" AND A.INSTALLMENT_NO = 0 ");
			
			if(rs4.next()){
			m_1st_rental = rs4.getDouble(3);
			
			}			
			
				rs5=stmt5.executeQuery (" SELECT COUNT(INSTALLMENT_NO), "+//1
																" "+m_schema_name+".AF_CO_GET_APP_CAP_OUTSTANDING('"+m_application_no+"'), "+ //2
																" "+m_schema_name+".AF_CO_GET_INSTALMENT_AMT('"+m_application_no+"'), "+//3
																" "+m_schema_name+".AF_CO_GET_LAST_PAY_AMT('"+m_finance_no+"'), "+//4
																" "+m_schema_name+".AF_CO_GET_NO_RENTALS_ARRIES('"+m_finance_no+"') "+//5
																" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
																" WHERE APPLICATION_NO=UPPER('"+m_application_no+"') "+
																" GROUP BY APPLICATION_NO ");
			
			if(rs5.next()){
			m_monthly_rental = rs5.getDouble(3);
			m_final_rental = rs5.getDouble(4);
			m_no_arr_months = rs5.getInt(5);
			m_cap_oustanding  =rs5.getDouble(2);
			m_inst_no = rs5.getInt(1);
			}
			
			m_paid_perid = m_inst_no - m_no_arr_months;
						
			rs6=stmt6.executeQuery (" SELECT NVL(A.VALUE,0) "+
												      " FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION A "+
												      " WHERE UPPER(A.APPLICATION_NO) = UPPER('"+m_application_no+"') "+
															" AND   A.ENT_DATE IN (SELECT "+
														  " MAX(ENT_DATE) "+
												      " FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION "+
														  " GROUP BY APPLICATION_NO) " );
		
		
			if(rs6.next()){
			m_valuation_value = rs6.getDouble(1);
			}
			
			rs7=stmt7.executeQuery (" SELECT NVL(VALUE,0) "+
														  " FROM   "+m_schema_name+".AF_CO_PRO_APP_VALUATION_BK "+
														  " WHERE  UPPER(APPLICATION_NO) = UPPER('"+m_application_no+"') "+
														  " AND    ENT_DATE IN (SELECT "+
														  " MAX(ENT_DATE) "+
												      " FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION_BK "+
														  " GROUP BY APPLICATION_NO) " );
					if(rs7.next()){
					m_prv_valuation_value = rs7.getDouble(1);
					}
																	
				rs8 = stmt8.executeQuery (" SELECT NVL(TO_CHAR(A.TERMINATION_VALIDITY_DATE,'DD/MM/YYYY'),'-'), "+ //1
															   " NVL("+m_schema_name+".AF_CO_GET_GROSS_RENTAL_ARREARS('"+m_finance_no+"',A.TERMINATION_VALIDITY_DATE),0), "+ //2
															   " NVL("+m_schema_name+".AF_CO_GET_ODI_ARREARS('"+m_finance_no+"',A.TERMINATION_VALIDITY_DATE),0) "+ //3
															   " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION A  "+
															   " WHERE UPPER(APPLICATION_NO) = UPPER('"+m_application_no+"') "+
															   " AND A.ENT_DATE IN (SELECT MAX(ENT_DATE) "+
															   " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION "+
															   " GROUP BY APPLICATION_NO)	");	
				
				String m_cal_date = "";
				double m_gros_rent_arr = 0.0;
				double m_odi_arr = 0.0;
				if(rs8.next()){
				m_cal_date = rs8.getString(1);
				m_gros_rent_arr = rs8.getDouble(2);
				m_odi_arr = rs8.getDouble(3);
				}
																	
			
			out.println("<blockquote><font size=3><p style='text-align:left'>");					
			
						
			out.println("<table border='0' width='90%' class='table' align='center'>"); 		
			out.println("<tr><td width='*%' align='center' class='rep-body1'><b><u>PROPOSAL FOR REPLACEMENT OF ASSET</td></tr>");
			out.println("</table>");
			
			out.println("<br><br>");
			
			out.println("<table border='1' width='90%' class='table' bordercolor='black' cellspacing='0' celipadding='0'>"); 		
			out.println("<tr><td width='15%' class='rep-body1' >Lessee :</td><td width='45%' class='rep-body1' >"+m_lessee_name+"</td><td width='10%' class='rep-body1' >LA No :</td><td width='20%' class='rep-body1' >"+m_finance_no+"</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table' >"); 		
			out.println("<tr><td width='5%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' ><b><u>Details of the Present asset:</td></tr>");
			out.println("</table>");
			
			out.println("<table border='1' width='90%' class='table' align='center' bordercolor='black' cellspacing='0' celipadding='0'>"); 		
			out.println("<tr><td width='15%' class='rep-body1' >Description of Asset</td><td width='30%' class='rep-body1' >"+m_prv_discrp+"</td><td width='15%' class='rep-body1' >Model/Year of Manufacture</td><td width='30%' class='rep-body1' >"+m_prv_produce_year+"</td></tr>");
			out.println("<tr><td width='15%' class='rep-body1' >Engine No/Serial No</td><td width='30%' class='rep-body1' >"+m_prv_eng_no+"</td><td width='15%' class='rep-body1' >Chassis No</td><td width='30%' class='rep-body1' >"+m_prv_cha_no+"</td></tr>");
			out.println("<tr><td width='15%' class='rep-body1' >Leased Amount </td><td width='30%' class='rep-body1' >"+nf.format(m_leased_amt)+"</td><td width='15%' class='rep-body1' >Valuation Value</td><td width='30%' class='rep-body1' >"+nf.format(m_prv_valuation_value)+"</td></tr>");
			out.println("<tr><td width='15%' class='rep-body1' >1<sup>st</sup> Rental</td><td width='30%' class='rep-body1' >"+nf.format(m_1st_rental)+"</td><td width='15%' class='rep-body1' >Rental based on</td><td width='30%' class='rep-body1' >"+nf.format(m_1st_rental)+"</td></tr>");
			out.println("</table>");
												
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table' >"); 		
			out.println("<tr><td width='5%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' ><b><u>Details of the facility obtained</td></tr>");
			out.println("</table>");
			
			out.println("<table border='1' width='90%' class='table' align='center' bordercolor='black' cellspacing='0' celipadding='0'>"); 		
			out.println("<tr><td width='15%' class='rep-body1' >Monthly Rental(Gross)</td><td width='30%' class='rep-body1' colspan='2'>"+nf.format(m_monthly_rental)+"&nbsp;Final Rental - Rs."+nf.format(m_final_rental)+"</td><td width='15%' class='rep-body1' >Arrears as at "+m_cal_date+"</td><td width='20%' class='rep-body1' >"+nf.format(m_gros_rent_arr)+"</td><td width='10%' class='rep-body1' >Months "+m_no_arr_months+"</td></tr>");
			out.println("<tr><td width='15%' class='rep-body1' >Capital Outstanding As at "+m_cal_date+"</td><td width='15%' class='rep-body1' >"+nf.format(m_cap_oustanding)+"</td><td width='15%' class='rep-body1' >Paid Period "+m_paid_perid+"</td><td width='15%' class='rep-body1' >ODI as at "+m_cal_date+"</td><td width='30%' class='rep-body1' colspan='2'>"+nf.format(m_odi_arr)+"</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table' >"); 		
			out.println("<tr><td width='5%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' ><b><u>Details of the Proposed assets</td></tr>");
			out.println("</table>");
			
			out.println("<table border='1' width='90%' class='table' align='center' bordercolor='black' cellspacing='0' celipadding='0'>"); 		
			out.println("<tr><td width='15%' class='rep-body1' >Description of Asset</td><td width='30%' class='rep-body1' >"+m_discrp+"</td><td width='15%' class='rep-body1' >Year of Manufacture</td><td width='30%' class='rep-body1' >"+m_produce_year+"</td></tr>");
			out.println("<tr><td width='15%' class='rep-body1' >Engine No/Serial No</td><td width='30%' class='rep-body1' >"+m_eng_no+"</td><td width='15%' class='rep-body1' >Chassis No</td><td width='30%' class='rep-body1' >"+m_cha_no+"</td></tr>");
			out.println("<tr><td width='15%' class='rep-body1' >Reg Number </td><td width='30%' class='rep-body1' >"+m_reg_no+"</td><td width='15%' class='rep-body1' >Valuation Value</td><td width='30%' class='rep-body1' >"+nf.format(m_valuation_value)+"</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table' >"); 		
			out.println("<tr><td width='5%' class='rep-body1' >&nbsp;</td><td width='25%' class='rep-body1' ><b><u>Recommendation :</td><td width='5%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("<tr><td width='5%' class='rep-body1' >&nbsp;</td><td width='25%' class='rep-body1' >&nbsp;</td><td width='5%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("<tr><td width='5%' class='rep-body1' >&nbsp;</td><td width='25%' class='rep-body1' >&nbsp;</td><td width='5%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("<tr><td width='5%' class='rep-body1' >&nbsp;</td><td width='25%' class='rep-body1' >..................................................</td><td width='5%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >............................................</td></tr>");
			out.println("<tr><td width='5%' class='rep-body1' >&nbsp;</td><td width='25%' class='rep-body1' >Asst.General Manager - (B/D)</td><td width='5%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >Date</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table' >");				
			out.println("<tr><td width='5%' class='rep-body1' >&nbsp;</td><td width='25%' class='rep-body1' ><b><u>Recommendation :</td><td width='5%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("<tr><td width='5%' class='rep-body1' >&nbsp;</td><td width='25%' class='rep-body1' >Arrears and ODI to be collected</td><td width='5%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("<tr><td width='5%' class='rep-body1' >&nbsp;</td><td width='25%' class='rep-body1' >&nbsp;</td><td width='5%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("<tr><td width='5%' class='rep-body1' >&nbsp;</td><td width='25%' class='rep-body1' >&nbsp;</td><td width='5%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("<tr><td width='5%' class='rep-body1' >&nbsp;</td><td width='25%' class='rep-body1' >..................................................</td><td width='5%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >............................................</td></tr>");
			out.println("<tr><td width='5%' class='rep-body1' >&nbsp;</td><td width='25%' class='rep-body1' >Credit Officer</td><td width='5%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >Date</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table' >");				
			out.println("<tr><td width='5%' class='rep-body1' >&nbsp;</td><td width='25%' class='rep-body1' ><b><u>Approval :</td><td width='10%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("<tr><td width='5%' class='rep-body1' >&nbsp;</td><td width='25%' class='rep-body1' >&nbsp;</td><td width='10%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("<tr><td width='5%' class='rep-body1' >&nbsp;</td><td width='25%' class='rep-body1' >&nbsp;</td><td width='10%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("<tr><td width='5%' class='rep-body1' >&nbsp;</td><td width='25%' class='rep-body1' >....................................................</td><td width='10%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >....................................................</td></tr>");
			out.println("<tr><td width='5%' class='rep-body1' >&nbsp;</td><td width='25%' class='rep-body1' >Asst General Manager - Operations</td><td width='10%' class='rep-body1' ></td><td width='*%' class='rep-body1' >Chief Executive Officer/Director</td></tr>");
			out.println("<tr><td width='5%' class='rep-body1' >&nbsp;</td><td width='25%' class='rep-body1' >Date :</td><td width='10%' class='rep-body1' ></td><td width='*%' class='rep-body1' >Date :</td></tr>");
			out.println("</table>");
			
			
			
			
    		
		out.println("</font></p></blockquote>");		
		//out.println("   <p style=\"page-break-after:always\"></p>"); 												
		
			 out.println("</form>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>");
		  out.println("</body></html>");
			
			
			out.flush();
			}
			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
