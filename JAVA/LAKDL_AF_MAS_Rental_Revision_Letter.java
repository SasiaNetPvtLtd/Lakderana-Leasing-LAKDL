//DEVELOPED BY : SANDUN 
//DATE         : 11 NOV 2008
// OFSCL BALANCE CONFIRMATION LETTER

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_MAS_Rental_Revision_Letter extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3,stmt5,stmt6;
  java.text.NumberFormat nf;
	
  public ResultSet rs,rs1,rs2,rs3,rs5,rs6;
 	

	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print;
	public double m_amount_due;
	String rec_count="";
	
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
			
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			
		 String m_chksql = req.getParameter("chksql");
		 stmt = conn.createStatement ();
		 stmt1 = conn.createStatement ();
		 stmt2 = conn.createStatement ();
		 stmt3 = conn.createStatement ();
		 stmt5 = conn.createStatement ();
		 stmt6 = conn.createStatement ();
		 			
  	 if(m_chksql.trim().equals("Letter")){			
			String m_finance_no	  =req.getParameter("finance_no");	
			
			String m_client_full_name = "";
			String m_client_add1 = "";
			String m_client_add2 = "";
			String m_client_city = "";
			String m_client_type = "";
			String m_nic_no      = "";     
			String m_biz_reg_no  = "";
			String m_next_rental_date   = "";
			int m_cnt_asset = 0;
			rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD Month YYYY') FROM DUAL ");
			
			boolean more = rs.next();
			if(more){
			m_Letter_date=rs.getString(1);
			}
			rs.close();
			stmt.close();			
			stmt = conn.createStatement ();
			
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
		  rs.close();
			stmt.close();			
			stmt = conn.createStatement ();
				
				
				rs1 = stmt1.executeQuery(" SELECT A.TITLE || ' ' || A.FULL_NAME, "+
													      " NVL(A.ADDRESS1,'-'), "+
													      " NVL(A.ADDRESS2,'-'), "+
													      " INITCAP(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),'-')), "+
																" A.CLIENT_TYPE, "+
																" NVL(A.NIC_NO,'-'), "+
                                " NVL(A.BUSINESS_CERTIFICATE_NO,'-') "+
													      " FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B"+
																" WHERE A.CLIENT_CODE = B.CLIENT_CODE "+
																" AND B.FINANCE_NO='"+m_finance_no+"'");

				if(rs1.next()){
				m_client_full_name = rs1.getString(1);
				m_client_add1      = rs1.getString(2);
				m_client_add2      = rs1.getString(3);
				m_client_city      = rs1.getString(4);
				m_client_type      = rs1.getString(5);
				m_nic_no           = rs1.getString(6);
				m_biz_reg_no       = rs1.getString(6);
				}		
				
			rs2 = stmt2.executeQuery(" SELECT TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),APPLICATION_NO "+											      
													      " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																" WHERE FINANCE_NO='"+m_finance_no+"'");
			
			String m_activated_date = "";
			String m_application_no = "";
			
			if(rs2.next()){
			m_activated_date = rs2.getString(1);
			m_application_no = rs2.getString(2);
			}
			int count=0;
			rs = stmt.executeQuery(" SELECT COUNT(A.VEHICLE_NO) "+
														 " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES A,"+m_schema_name+".AF_CR_PRO_TERMINATION B "+
														 " WHERE A.TERMINATION_NO = B.TERMINATION_NO "+
														 " AND B.FINANCE_NO='"+m_finance_no+"' "+
														 " GROUP BY A.TERMINATION_NO ");
			if(rs.next()){
			count  = rs.getInt(1);			
			}
			
			rs.close();
			stmt.close();			
			stmt = conn.createStatement ();
			
			
			rs3 = stmt3.executeQuery(" SELECT A.TERMINATION_NO, NVL(A.VEHICLE_NO,'-'),NVL(B.AMOUNT,0),"+m_schema_name+".AF_CO_GET_RENTAL_DATE(B.APPLICATION_NO) "+
  														 " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES A,"+m_schema_name+".AF_CR_PRO_TERMINATION B "+
															 " WHERE A.TERMINATION_NO = B.TERMINATION_NO "+
															 " AND B.FINANCE_NO='"+m_finance_no+"' ");
			
			String m_termi_reg_no = "";
			//String m_nxt_rental_date ="";
			double m_net_amt=0.0;
			while(rs3.next()){
			if(count==1){
			m_termi_reg_no = rs3.getString(2);
			}
			else if(count!=1){
			m_termi_reg_no += ","+ rs3.getString(2);
			}
			m_net_amt = rs3.getDouble(3);
		//	m_nxt_rental_date = rs3.getString(4);
			}
			
			
		rs = stmt.executeQuery(" SELECT REG_NO,'','', "+
													" MODEL_CODE,MODEL_DESC,YEAR_OF_MANUFACTURE,PRICING_NO,SUB_MODEL_CODE"+
													" FROM "+
													" (SELECT NVL(B.REG_NO,'-') REG_NO, "+
													" nvl(B.ENGINE_NO,'-') ENGINE_NO, "+
													" nvl(B.CHASSIS_NO,'-') CHASSIS_NO, "+
													" B.MODEL_CODE, "+
													" INITCAP(C.MAKE_DESC||' '||D.DESCRIPTION||' '||H.DESCRIPTION) MODEL_DESC, "+ 
													" NVL(D.YEAR_OF_MANUFACTURE,'') YEAR_OF_MANUFACTURE, "+
													" B.PRICING_NO, "+
													" NVL(B.SUB_MODEL_CODE,' ') SUB_MODEL_CODE, "+
												  " INITCAP(H.DESCRIPTION) ITEM_SUB_CAT_DESC, "+
													" C.MAKE_CODE, "+
													" F.ITEM_SUB_CAT, "+
													" UPPER(E.VENDOR_CODE), "+
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
													" B.MODEL_CODE=F.MODEL_CODE)  "+
													" GROUP BY REG_NO,PRICING_NO,MODEL_CODE,MODEL_DESC,YEAR_OF_MANUFACTURE,SUB_MODEL_CODE"); 
	
	
int m_reg_count =0;
	
	
													
	rs5 = stmt5.executeQuery(" SELECT INSTALLMENT_NO, "+//1
												 " SUM(A.NET_RENTAL_AMOUNT), "+//2
												 " SUM(A.GRENTAL_AMOUNT)-SUM(A.NET_RENTAL_AMOUNT), "+ //3
												 " SUM(A.GRENTAL_AMOUNT) "+ //4
												 " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,  "+
												 " "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
												 " WHERE A.PRO_INVOICE_NO=B.INVOICE_NO  "+
												 " AND A.APPLICATION_NO=B.APPLICATION_NO "+
												 " AND A.PRICING_NO=B.PRICING_NO  "+
												 " AND B.ACTIVE_STATUS='Y'  "+
												 " AND A.APPLICATION_NO='"+m_application_no+"'  "+
												 " AND A.INVOICE_NO IS NULL "+
												 " GROUP BY INSTALLMENT_NO ");
			
      double m_net_rental_amt =0.0;;
			double m_vat_amt=0.0;
			double m_gross_amt = 0.0;
			if(rs5.next()){
			m_net_rental_amt = rs5.getDouble(2);
			m_vat_amt = rs5.getDouble(3);
			m_gross_amt = rs5.getDouble(4);
			}
			rs5.close();
			stmt5.close();			
			stmt5 = conn.createStatement ();
			
			
			
			rs6 = stmt6.executeQuery(" SELECT MIN(TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY')) "+
												       " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A "+
												       " WHERE A.RENTAL_DATE > (SELECT B.TER_TYPE_ENT_DATE "+
												       "                  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
												       "                  WHERE B.APPLICATION_NO='"+m_application_no+"') "+
												       " AND A.APPLICATION_NO = '"+m_application_no+"' "+
												       " GROUP BY A.APPLICATION_NO ");
												  
			if(rs6.next()){
			m_next_rental_date = rs6.getString(1);
			}
			rs2.close();
			stmt2.close();			
			stmt2 = conn.createStatement ();
			rs2= stmt2.executeQuery(" SELECT COUNT(A.APPLICATION_NO) "+
																  " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B"+ 
																  " WHERE A.APPLICATION_NO  = B.APPLICATION_NO "+
																  " AND B.FINANCE_NO = '"+m_finance_no+"' "+
																  " GROUP BY A.APPLICATION_NO ");
			if(rs2.next()){
			m_cnt_asset = rs2.getInt(1);
			}

			
			out.println("<html><head>"); 
			out.println("<title>Rental Revision Letter</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			   		
			out.println("<script>");			
									
			out.println("function save_data(){");		
			out.println("m_table.innerHTML=\"\" ");
			out.println("window.print();");			
		  out.println("}");
		
		  out.println("function add_button(){");					
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
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
			out.println("<blockquote><blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >"+m_Letter_date+"</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >"+m_client_full_name+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >"+m_client_add1+",</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >"+m_client_add2+",</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >"+m_client_city+".</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			out.println("<br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >Dear Sir,</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' ><b>LEASE AGREEMENT SHEDULE NO : "+m_finance_no+" &nbsp;&nbsp;&nbsp;DATED : "+m_activated_date+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><u><b>RELEASE OF LEASE ASSETS REGISTRATION NOs"+m_termi_reg_no+"</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >Reference is made to the Lease Agreement Schedule No : "+m_finance_no+" executed by you on in respect of the following assets:</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
							
			int row = 1;
			out.println("<table border='0' width='80%' class='table'>");
			/*
			if(m_cnt_asset<=1){
			out.println("<tr><td width='*%' class='rep-body1' colspan=2>"+m_cnt_asset+" Number</td></tr>");
			}
			else if(m_cnt_asset>1){
			out.println("<tr><td width='*%' class='rep-body1' colspan=2>"+m_cnt_asset+" Numbers</td></tr>");
 		  }
			*/	
		  while(rs.next()){		
			out.println("<tr><td width='3%' class='rep-body1' >"+row+"</td><td width='*%' class='rep-body1' >"+rs.getString(5)+" "+rs.getString(1)+"</td></tr>");
			++row;
			}
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >At your express request we are agreeable to releasing to you the ("+m_termi_reg_no+")above upon your making and once-and-for-all payment of Rs."+nf.format(m_net_amt)+"</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >Consequence to the release of the aforesaid asset, the monthly rental payable by you under the above Lease Agreement Schedule shall "+
			            "stand proportionately reduce as follows:</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='15%' class='rep-body1' >Net Rental</td><td width='12%' class='rep-body1' align=right>"+nf.format(m_net_rental_amt)+"</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("<tr><td width='15%' class='rep-body1' >VAT</td><td width='12%' class='rep-body1' align=right >"+nf.format(m_vat_amt)+"<br>-----------------</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("<tr><td width='15%' class='rep-body1' >Gross Amount</td><td width='12%' class='rep-body1' align=right >"+nf.format(m_gross_amt)+"<br>========</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("</table>");			
			
			out.println("<br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >The change will be effected from the "+m_next_rental_date+" subject however to out receiving the one-for-all payment of Rs."+nf.format(m_net_amt)+"</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >It must be clearly understood that subject to the release of the asset mentioned above and consequent changes pertaining to the payment "+
			            "of rentals as aforesaid the said Lease Agreement Schedule No :<b> "+m_finance_no+"</b> dated <b>"+m_activated_date+"</b> shall stand confirmed in all respects and Terms, Conditions & Obligations "+
									"of the lease agreement will continue in full force and effect and binding on the lease for Term thereof.</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >If the foregoing is acceptable to you, please return the duplicate copy of this letter duly signed to signify your arrangements.</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			out.println("<br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >Yours faithfully</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >"+m_orient_name+"</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
					
			out.println("<table border='0' width='80%' class='table'>"); 	
			out.println("<tr><td width='10%' class='rep-body1' >.............................................</td><td width='30%' class='rep-body1' >&nbsp;</td><td width='20%' class='rep-body1' >.............................................</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("<tr><td width='10%' class='rep-body1' >Nishaman Karunapala</td><td width='30%' class='rep-body1' >&nbsp;</td><td width='20%' class='rep-body1' >G C B Ranasinghe</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("<tr><td width='10%' class='rep-body1' >ASST.GENERAL MANAGER   (OPERATION)</td><td width='30%' class='rep-body1' >&nbsp;</td><td width='20%' class='rep-body1' >DIRECTOR/CHIEF EXECUTIVE OFFICER</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("</table>");
			
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >I/We agree.</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >"+m_client_full_name+"</td></tr>");
			if(m_client_type.equals("I")){
			out.println("<tr><td width='*%' class='rep-body1' >"+m_nic_no+"</td></tr>");
			}
			else{
			out.println("<tr><td width='*%' class='rep-body1' >"+m_biz_reg_no+"</td></tr>");
		
			out.println("</table>");
			
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='20%' class='rep-body1' >&nbsp;</td><td width='25%' class='rep-body1' >&nbsp;</td><td width='20%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' align=right>Company Seal</td></tr>");
			out.println("<tr><td width='20%' class='rep-body1' >&nbsp;</td><td width='25%' class='rep-body1' >&nbsp;</td><td width='20%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("</table>");
			
			out.println("<br>");			
	
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='20%' class='rep-body1' >.............................................</td><td width='25%' class='rep-body1' >&nbsp;</td><td width='20%' class='rep-body1' >.............................................</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("<tr><td width='20%' class='rep-body1' >DIRECTOR</td><td width='25%' class='rep-body1' >&nbsp;</td><td width='20%' class='rep-body1' >DIRECTOR</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
				
			out.println("</table>");
			}
			out.println("</font></p></blockquote>");		
			
			 
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
