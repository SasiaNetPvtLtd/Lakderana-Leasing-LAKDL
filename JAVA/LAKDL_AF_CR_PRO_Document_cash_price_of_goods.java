//ID         :
//SCREEN NAME:Document Printing - Cash price for goods
//CREATED BY :Chandana 	
//DATE/TIME  : 11-06-2007
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Document_cash_price_of_goods extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt_invoice;
//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
  public ResultSet rs,rs_invoice;
 	

	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print,m_letter_date;
	public String m_Reg_no,m_Make_modle,m_Eng_no,m_Chas_no,m_Manu_year,m_amnt_word;
	public double m_amount_due,m_amount;
	public String m_master_lease_no;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
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
			
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			
		
		 String m_chksql = req.getParameter("chksql");
			
  	 if(m_chksql.trim().equals("main_page")){
			
			  
				
			stmt = conn.createStatement ();
			stmt_invoice = conn.createStatement ();
			
			String m_application_no = req.getParameter("application_no");
			String m_client_code	  =req.getParameter("client_code");	
			String m_document_code	=req.getParameter("document_code");	
			String m_print=req.getParameter("print");
			

					
			if(req.getParameter("status")==null){
							
		 rs=stmt.executeQuery (" SELECT "+
		" COUNT(DOCUMENT_CODE) "+
		" FROM "+m_schema_name+".AF_CR_PRO_DOCUMENT_STATUS "+
		" WHERE APPLICATION_NO=UPPER('"+m_application_no+"') AND CLIENT_CODE=UPPER('"+m_client_code+"') AND DOCUMENT_CODE=UPPER('"+m_document_code+"') ");

    	boolean	more = rs.next();
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
				
				


        rs=stmt.executeQuery (" SELECT TO_CHAR(SYSDATE, 'fmddth') ||'  '||TO_CHAR(SYSDATE, 'Month')||TO_CHAR(SYSDATE, 'YYYY') LETTER_DATE "+
				                      " FROM DUAL ");
      			
					
			boolean	more = rs.next();
			
			if(more){
			  m_letter_date=rs.getString(1);
				}
		
		String m_title="";
		String m_full_name="";
		String m_add1="";
		String m_add2="";
		String m_city_name="";
		
		 rs = stmt.executeQuery(" SELECT "+
        												" NVL(UPPER(TITLE),' '), "+
																" NVL(UPPER(FULL_NAME),' '), "+
																" REPLACE(REPLACE(NVL(UPPER(ADDRESS1),' '),'-',' '),'null',' '),"+ //REGISTERED_ADDRESS1
																" REPLACE(REPLACE(NVL(UPPER(ADDRESS2),' '),'-',' '),'null',' '),"+ //REGISTERED_ADDRESS2
                                " UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)) "+  //REGISTERED_CITY_CODE
																" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
                                " WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')");
			  more = rs.next();		
				
				if(more){
				m_title=rs.getString(1);
				m_full_name=rs.getString(2);
				m_add1=rs.getString(3);
				m_add2=rs.getString(4);
				m_city_name=rs.getString(5);
				}
				
				if(m_city_name==null){
				m_city_name="";
				}
				if(m_add1.equals("-")){
				m_add1="";
				}
				if(m_add2.equals("-")){
				m_add2="";
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
											
																			
		/*	rs = stmt.executeQuery("	SELECT A.APPLICATION_NO, "+ //COMMENT BY CHANDANA FOR REF NO.789 ON 12/08/2007 
			                       " A.TOTAL_AMOUNT, "+
														 " NVL(A.REG_NO,'-') "+
														 " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_MAS_MODEL B,"+m_schema_name+".AF_CO_MAS_SUB_MODLE C "+
															" WHERE   A.MODEL_CODE=B.MODEL_CODE AND "+
															" A.MODEL_CODE=C.MODEL_CODE AND "+
															" APPLICATION_NO=UPPER('"+m_application_no+"') "); */
															 
			rs = stmt.executeQuery(" SELECT SUM(GROSS_AMOUNT) "+  //ADDED BY CHANDANA FOR REF NO.789 ON 12/08/2007
			                       " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
														 " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");  
																											
		more = rs.next(); 
				
		if(more){
		m_amnt_word=rs.getString(1);
		m_amount=rs.getDouble(1);
		}		
			
				
				
				
	/*	String sql = " SELECT X.APPLICATION_NO, "+
		             " NVL(X.TOTAL_AMOUNT,0), "+
								 " NVL(X.REG_NO,'-'), "+
								 " NVL(X.ENGINE_NO,'-'), "+
								 " NVL(X.CHASSIS_NO,'-'), "+
								 " NVL(X.MODEL_CODE,'-'), "+
								 " NVL(X.MAKE_CODE,'-')||' '||NVL(X.ITEM_SUB_CAT,'-')||' '||NVL(Y.DESCRIPTION,'-'), "+
								 " Y.YEAR_OF_MANUFACTURE "+
								 " FROM (SELECT DISTINCT A.APPLICATION_NO, "+
								 " A.TOTAL_AMOUNT, "+
								 " A.REG_NO, "+
								 " A.ENGINE_NO, "+
								 " A.CHASSIS_NO, "+
								 " A.MODEL_CODE, "+
								 " B.ITEM_SUB_CAT, "+
								 " B.MAKE_CODE, "+
								 " B.DESCRIPTION, "+
								 " A.SUB_MODEL_CODE "+
								 " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A, "+
								 " "+m_schema_name+".AF_CO_MAS_MODEL B "+
								 " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
								 " A.MODEL_CODE=B.MODEL_CODE ) X , "+
								 " "+m_schema_name+".AF_CO_MAS_SUB_MODLE Y "+
								 " WHERE X.SUB_MODEL_CODE=Y.SUB_CODE "; 	*/
									
									
			//-----------Added by Chandana on 16/07/07 -------------						
									
		String sql = "	SELECT '',NVL(B.TOTAL_AMOUNT,0), "+
		             " NVL(B.REG_NO,'-'), "+
								" nvl(B.ENGINE_NO,'-'), "+
								" nvl(B.CHASSIS_NO,'-'), "+
								" B.MODEL_CODE,"+
								//" INITCAP(C.MAKE_DESC||' '||F.DESCRIPTION||' '||D.DESCRIPTION ||' '||H.DESCRIPTION) MODEL_DESC, "+ // comment by nuwan de silva on 12-12-2007 at ofscl
								" INITCAP(C.MAKE_DESC||' '||D.DESCRIPTION||' '||H.DESCRIPTION) MODEL_DESC, "+ // added by nuwan de silva on 12-12-2007 at ofscl
								" NVL(D.YEAR_OF_MANUFACTURE,''), "+
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
								" B.MODEL_CODE=F.MODEL_CODE ";
 

									
									
									
				
				
			  out.println("<html><head>"); 
				out.println("<title>Acceptance Receipt </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
			out.println("<script>");
			
			out.println("function save_data(){");
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";");			
			out.println(" window.location.href=m_url;"); 
			out.println("m_table.innerHTML=\"\" ");
			out.println("window.print();");
			
			
			
			
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
			out.println("<body bgcolor='white'><br>");
							
			out.println("<form name='Form1'>");
										
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");
      		
		  out.println("<blockquote><font size=3><p style='text-align:left'>");					
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr><td width=\"100%\" class='rep-body1'><b></b></td></tr>"); //"+m_status+"
		  out.println("</table>");
  		out.println("</font></p></blockquote>");	
			
			out.println("<font size=3><p style='text-align:left'>");					
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"*%\" class='rep-body1'>"+m_letter_date+"</td></tr>");
		  out.println("</table>");
			out.println("<br>");
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			//Modified by mahela on 26-06-2007
			if(m_title.equals(" ")){
			out.println("<tr><td width=\"*%\" class='rep-body1'>"+m_full_name+"</td></tr>");
			}
			else {
			out.println("<tr><td width=\"*%\" class='rep-body1'>"+m_title+"."+m_full_name+"</td></tr>");
			}
		  out.println("</table>");
			//out.println("<br>");
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"*%\" class='rep-body1'>"+m_add1+"</td></tr>");
			out.println("<tr><td width=\"*%\" class='rep-body1'>"+m_add2+"</td></tr>");
			out.println("<tr><td width=\"*%\" class='rep-body1'>"+m_city_name+"</td></tr>");
		  out.println("</table>");
			
			out.println("<br>");
			out.println("<br>");
      
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"*%\" class='rep-body1'>Dear Sir</td></tr>");
		  out.println("</table>");
      out.println("<br>");
      
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"*%\" class='rep-body1'><B><I>Cash price of the vehicle under section 3(I) of the Consumer Credit Act.</I></B></td></tr>");
		  out.println("</table>");
			out.println("<br>");
			
			String data ="We hereby confirm that the <B><I>cash price</I></B> of the goods at which you may purchase "+
			              "the <B><I>Goods</I></B> morefully described in the schedule is Rupees "+m_sn_methods.numbersToChar(m_amnt_word)+" Only"+ //modiifed nuwan de silva 10-07-07
										"(Rs."+nf.format(m_amount)+")";
			
			
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"*%\" class='rep-body1' align='justify'>"+data+"</td></tr>");
		  out.println("</table>");
      out.println("<br>");
			out.println("<br>"); 
						
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"*%\" class='rep-body1' align='center'><b>Schedule</b></td></tr>");
		  out.println("</table>");
			out.println("<br>");
			
			rs = stmt.executeQuery(sql);
			
			more = rs.next(); 
				
		while(more){
		m_amount=rs.getDouble(2);
		m_Reg_no =rs.getString(3);
		m_Eng_no =rs.getString(4);
		m_Chas_no=rs.getString(5);
		m_Make_modle =rs.getString(7);		
		m_Manu_year=rs.getString(8);		
		
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0' border='1' bordercolor='black'>"); 
			out.println("<tr><td width=\"45%\" class='rep-body1' align='left'>&nbsp Registration No:</td><td width=\"45%\" class='rep-body1' align='left'>&nbsp"+m_Reg_no+"</td></tr>");
			out.println("<tr><td width=\"45%\" class='rep-body1' align='left'>&nbsp Make & Model:</td><td width=\"45%\" class='rep-body1' align='left'>&nbsp"+m_Make_modle+"</td></tr>");
			out.println("<tr><td width=\"45%\" class='rep-body1' align='left'>&nbsp Engine No:</td><td width=\"45%\" class='rep-body1' align='left'>&nbsp"+m_Eng_no+"</td></tr>");
			out.println("<tr><td width=\"45%\" class='rep-body1' align='left'>&nbsp Chassis No:</td><td width=\"45%\" class='rep-body1' align='left'>&nbsp"+m_Chas_no+"</td></tr>");
			out.println("<tr><td width=\"45%\" class='rep-body1' align='left'>&nbsp Year of Manufacture:</td><td width=\"45%\" class='rep-body1' align='left'>&nbsp"+m_Manu_year+"</td></tr>");
		  out.println("</table>");
      out.println("<br>");
		more = rs.next();
		
		}	
			
			
			out.println("<br>");
			out.println("<br>"); 
			out.println("<br>");

			
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"*%\" class='rep-body1' align='left'>Yours faithfully,</td></tr>");
		  out.println("<tr><td width=\"*%\" class='rep-body1' align='left'><b>"+m_orient_name.toUpperCase()+"</b></td></tr>");
			out.println("</table>");
	
			out.println("<br>");
			out.println("<br>"); 
			out.println("<br>");
				
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"*%\" class='rep-body1' align='left'><B><I>Authorized Signatory</I></B></td></tr>");
		  out.println("</table>");

			
			
			
				
			
			out.println("</font></p>");												
		  out.println("</form></body></html>");
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
