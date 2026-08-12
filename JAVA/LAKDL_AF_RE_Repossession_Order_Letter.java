//ID         :
//SCREEN NAME:LAKDL_AF_RE_Repossession_Order_Letter
//CREATED BY :Nuwan De Silva	
//DATE/TIME  : 31-10-07
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_RE_Repossession_Order_Letter extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt_partner;
//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
  public ResultSet rs,rs_partner;
 	

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
		//	String m_chksql;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
			
				//Decaring variables
			String m_full_name="";
			String m_add1="";
			String m_add2="";
			String m_city_name="";
			String m_title="";
			String m_client_type=""; 
			String m_start_date="";
			String m_partner_name [];
			String m_partner_add [];
			int m_data_count=0;
		  String m_status ="";
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			String m_validity_period="";
			String m_reg_no="N(PBS)1076";

						
		 String m_chksql = req.getParameter("chksql");
			
  	 if(m_chksql.trim().equals("main_page")){
			stmt = conn.createStatement ();
			
			String m_repossession_no = req.getParameter("repossession_no").trim();
			String _m_finance_no     = req.getParameter("finance_no").trim();
			String m_seizer_code     = req.getParameter("seizer_code").trim();
			String m_print           = req.getParameter("print").trim();
								
			String m_reposession[]=m_repossession_no.split("@",m_repossession_no.length());
						
				//rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'fmddth') || ' ' || TO_CHAR(SYSDATE, 'Month')|| TO_CHAR(SYSDATE, 'YYYY') LETTER_DATE FROM DUAL ");
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'Month') || ' ' || TO_CHAR(SYSDATE, 'DD')|| ',' || TO_CHAR(SYSDATE, 'YYYY') LETTER_DATE FROM DUAL ");
				boolean more = rs.next();
				if(more){
				m_Letter_date=rs.getString(1);
				}
				rs.close();
					 rs = stmt.executeQuery(" SELECT "+
					    " INITCAP(COMPANY_NAME), "+ 
					    " INITCAP(ADDRESS1), "+
					    " INITCAP(ADDRESS2), "+
					    " INITCAP(CITY), "+
					    " TEL_NO, "+
					    " FAX_NO,  "+
							" VAT_RATE "+
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
											
										 String sql_sizer= "    SELECT "+
																			 "    SEIZER_CODE, "+
																			 "    FIRST_NAME || ' ' || LAST_NAME , "+
																			 "    NVL(ADDRESS1,' '), "+
																			 "    NVL(ADDRESS2,' '), "+
																			 "    NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),' ' ), "+
																			 "    VALIDITY_PERIOD "+
																			 "    FROM "+m_schema_name+".AF_CO_MAS_SEIZER "+
																			 "    WHERE UPPER(SEIZER_CODE)=UPPER('"+m_seizer_code+"')" ;
	

				rs = stmt.executeQuery(sql_sizer);	
			  more = rs.next();		
				if(more){
				m_title=rs.getString(1);
				m_full_name=rs.getString(2);
				m_add1=rs.getString(3);
				m_add2=rs.getString(4);
				m_city_name=rs.getString(5);
				m_validity_period=rs.getString(6);
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
				
			
			out.println("<html><head>"); 
			out.println("<title>Repossession Order Letter </title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
  		
			out.println("<script>");
			
			out.println("function save_data(){");
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Repossession_Order_Letter_Generated_status?chksql=save_page&scr_name=AF_RE_REPOSSESSION&type=main_page&seizer_code="+m_seizer_code+"&repossession_no="+m_repossession_no+"&finance_no="+_m_finance_no+"\";"); 
			out.println(" window.location.href=m_url;"); 
			out.println("m_table.innerHTML=\"\" ");
			out.println("window.print();");
			out.println("}");

		
			out.println("function add_button(){");
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
			}else{
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
			
			out.println("<br>");
			//out.println("<br><br>");
			//out.println("<br><br>");
			//out.println("<br><br>");
		  out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
			int i=0;
			String Sql_vehicle="";
			while(i<m_reposession.length){
		
			if (!m_reposession[i].equals("")){
			
			Sql_vehicle="SELECT"+		
			//"			NVL(UPPER(C.MAKE_DESC),' ' ) || '-'|| NVL(UPPER("+m_schema_name+".AF_CO_GET_MODEL_DESC(B.MODEL_CODE)), ' ') ||'-'|| NVL(UPPER(D.DESCRIPTION),' ') ||'-'|| NVL(UPPER(F.DESCRIPTION),' ' ) DESCRIPTION, "+ //comment by nuwan de silva on 12-12-2007 at ofscl
			"			NVL(UPPER(C.MAKE_DESC),' ' ) || '-'||  NVL(UPPER(D.DESCRIPTION),' ') ||'-'|| NVL(UPPER(F.DESCRIPTION),' ' ) DESCRIPTION, "+  //added by nuwan de silva on 12-12-2007 at ofscl
			"			NVL(B.REG_NO,'-') , "+
			"			NVL(B.CHASSIS_NO,'-') , "+
			"			NVL(B.ENGINE_NO,'-') , "+
			//"			D.YEAR_OF_MANUFACTURE, "+
		  "     UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(E.CLIENT_CODE)) , "+
			"			B.INVOICE_NO "+
			"			FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
			"			"+m_schema_name+".AF_CO_MAS_MAKE C,"+m_schema_name+".AF_CO_MAS_SUB_MODLE D,"+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY F,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS E, "+
			"     "+m_schema_name+".AF_RE_PRO_REPOSSESSION G "+
			"			WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
			"			B.APPLICATION_NO=E.APPLICATION_NO AND "+
			"     E.FINANCE_NO=G.FINANCE_NO AND "+
			"     B.INVOICE_NO=G.PRO_INVOICE_NO AND "+
			"     G.REPOSSESSION_NO='"+m_reposession[i].trim()+"' AND "+
			"			UPPER(E.FINANCE_NO)=UPPER('"+_m_finance_no+"') AND "+
			"			A.ACTIVE_STATUS='Y' AND "+
			"			A.ASSET_ID=B.ASSET_ID AND "+
			"			(C.MAKE_CODE ,F.ITEM_SUB_CAT ) IN "+
			"			(SELECT "+
			"			MAKE_CODE ,ITEM_SUB_CAT "+
			"			FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
			"			WHERE "+
			"			MODEL_CODE IN ( "+
			"			SELECT "+
			"			MODEL_CODE "+
			"			FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
			"			WHERE INVOICE_NO=B.INVOICE_NO "+
			"			AND ACTIVE_STATUS='Y' "+
			"			)) AND "+
			"			D.SUB_CODE=B.SUB_MODEL_CODE ";
			
			  rs = stmt.executeQuery(Sql_vehicle);
	      more = rs.next();
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='70%' class='rep-body1' ><b>Date: "+m_Letter_date+"</td>");
			//out.println("<td width='10%' class='rep-body1' ><b>"+m_reg_no+"</td></tr>");
			out.println("</tr>");
			out.println("</table>");
			
			//out.println("<br>");	
			out.println("<br>");	
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_full_name+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_add1+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_add2+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_city_name+"</td></tr>");
			out.println("</table>");	
			
			out.println("<br>");	
			out.println("<br>");	

			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' ><b>Dear Sir,</td></tr>");
			out.println("</table>");
											
			out.println("<br><br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='20%' class='rep-body1' align='left'   ><b>VEHICLE</td>");
			out.println("    <td width='5%'  class='rep-body1' align='center' ><b>:</td>");
			out.println("    <td width='*%'  class='rep-body1' align='left'   ><b>"+rs.getString(1)+"</td></tr>");
			
			out.println("<tr><td width='20%' class='rep-body1' align='left'   ><b>REGISTRATION NO</td>");
			out.println("    <td width='5%'  class='rep-body1' align='center' ><b>:</td>");
			out.println("    <td width='*%'  class='rep-body1' align='left'   ><b>"+rs.getString(2)+"</td></tr>");
			
			out.println("<tr><td width='20%' class='rep-body1' align='left'   ><b>CHASSIS NO</td>");
			out.println("    <td width='5%'  class='rep-body1' align='center' ><b>:</td>");
			out.println("    <td width='*%'  class='rep-body1' align='left'   ><b>"+rs.getString(3)+"</td></tr>");
			
			out.println("<tr><td width='20%' class='rep-body1' align='left'   ><b>ENGINE NO</td>");
			out.println("    <td width='5%'  class='rep-body1' align='center' ><b>:</td>");
			out.println("    <td width='*%'  class='rep-body1' align='left'   ><b>"+rs.getString(4)+"</td></tr>");
			
			out.println("<tr><td width='20%' class='rep-body1' align='left'   ><b>LEASE AGREEMENT NO</td>");
			out.println("    <td width='5%'  class='rep-body1' align='center' ><b>:</td>");
			out.println("    <td width='*%'  class='rep-body1' align='left'   ><b>"+_m_finance_no+"</td></tr>");
			
			out.println("<tr><td width='20%' class='rep-body1' align='left'   ><b>LESSEE</td>");
			out.println("    <td width='5%'  class='rep-body1' align='center' ><b>:</td>");
			out.println("    <td width='*%'  class='rep-body1' align='left'   ><b>"+rs.getString(5)+"</td></tr>");
						
			out.println("</table>");	
			
			out.println("<hr color='black'>");
						
	    out.println("</font></p></blockquote>");
			
		  out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
		  String data="The Lessee in the above Lease Agreement has defaulted payment of rentals, and we have terminated "+
			            "the Lease of the above asset, by the dispatch of a registered letter to him in terms of the said Lease.";
      
			//out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			data="As per of the provisions of the said Lease Agreement the said Lessee inter-alia agreed to handover peaceful "+
			            "possession of the said asset but has failed to do so upon termination of the said Lease as aforesaid, despite "+
                  "written instructions.";
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			data="You are hereby instructed, within the next "+m_validity_period+" days to persuade the Lease and/or his or such other "+
			     "person as in possession of the said asset by peaceful means to handover possession of the said asset to "+
           "you or to us. Please also note that the Police of the nearest area should be notified before and after the "+
					 "repossession of the vehicle.";
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			data="I have been authorized to repossess the abovementioned asset which is owned by "+m_orient_name+", "+
			     "and which has been leased out to "+rs.getString(5)+" through a written "+
           "Agreement has failed to pay the monies due to "+m_orient_name+", and they have "+
					 "terminated the lease of the above item/s and have demanded the return thereof.";
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			
			data="You are authorized to obtain the assistance of any competent person to assist you if necessary for the "+
			   	 "purpose mentioned above.";
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			
			data="You are here by authorized to receive the delivery of the said asset for and on our behalf, and upon receipt of "+
			   	 "same, you are instructed to make the following statement to the Police Station referred to in the paragraph";
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			
			data="\"i am a representative of "+m_orient_name+". I have been authorized to "+
			   	 "repossess the above equipment, which I have repossessed on .................................................\"";
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			data="You are hereby directed to refrain from using force of any kind but only to adopt peaceful means. Further, "+
			   	 "you are specially cautioned not to cause any harm or damage to person or asset in the execution of our "+
					 "instruction and we wish to state that, we shall not hold ourselves responsible or liable for any of your action "+
					 "which do not fall within the scope of our specific instructions.";
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			
			data="Upon re-possessing, you are requested to hand over the said asset to our vehicle yard at No 46,48, DR. N. M. PERERA MAWATHA COLOMBO 08, "+
			   	 "immediately obtain an inventory of the repossessed vehicle/asset.";
					 
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
		 
			data="Yours faithfully,";
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+m_orient_name+"</td>");
			out.println("</tr></table>");		
			
			//data="AGM Recoveries";//Commented By Lalanka on 08-06-2009
			/*out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
			*/
			
			data="-----------------------------------------------------------";
			out.println("<br><br><br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			data="Authorized Signature";//mod by LK on 05-06-2009
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			out.println("<p style=\"page-break-after:always\"></p>");		
			}
			i=i+1;
			
			
			}
			
			out.println("</font></p></blockquote>");											
			
		  out.println("</form></body></html>");
			}
			
			else if(m_chksql.trim().equals("copy")){
			stmt = conn.createStatement ();
			
			String m_repossession_no    = req.getParameter("repossession_no");
			String _m_finance_no        = req.getParameter("finance_no");
			String m_seizer_code        = req.getParameter("seizer_code").trim();
			String m_pro_invoice_no     = req.getParameter("pro_invoice_no").trim();
			String m_print              = req.getParameter("print");
			
				//rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'fmddth') || ' ' || TO_CHAR(SYSDATE, 'Month')|| TO_CHAR(SYSDATE, 'YYYY') LETTER_DATE FROM DUAL ");
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'Month') || ' ' || TO_CHAR(SYSDATE, 'DD')|| ',' || TO_CHAR(SYSDATE, 'YYYY') LETTER_DATE FROM DUAL ");
				boolean more = rs.next();
				if(more){
				m_Letter_date=rs.getString(1);
				}
				rs.close();
					 rs = stmt.executeQuery(" SELECT "+
					    " INITCAP(COMPANY_NAME), "+ 
					    " INITCAP(ADDRESS1), "+
					    " INITCAP(ADDRESS2), "+
					    " INITCAP(CITY), "+
					    " TEL_NO, "+
					    " FAX_NO,  "+
							" VAT_RATE "+
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
											
				String sql_sizer= "    SELECT "+
				"    SEIZER_CODE, "+
				"    FIRST_NAME || ' ' || LAST_NAME , "+
				"    NVL(ADDRESS1,' '), "+
				"    NVL(ADDRESS2,' '), "+
				"    NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),' ' ), "+
				"    VALIDITY_PERIOD "+
				"    FROM "+m_schema_name+".AF_CO_MAS_SEIZER "+
				"    WHERE UPPER(SEIZER_CODE)=UPPER('"+m_seizer_code+"')" ;

				rs = stmt.executeQuery(sql_sizer);	
			  more = rs.next();		
				if(more){
				m_title=rs.getString(1);
				m_full_name=rs.getString(2);
				m_add1=rs.getString(3);
				m_add2=rs.getString(4);
				m_city_name=rs.getString(5);
				m_validity_period=rs.getString(6);

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
			
			out.println("<html><head>"); 
			out.println("<title>Repossession Order Letter </title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
  		
			out.println("<script>");
			
      out.println("function save_data(){");
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Repossession_Order_Letter_Generated_status?chksql=save_page&type=copy&scr_name=AF_RE_REPOSSESSION&repossession_no="+m_repossession_no+"&seizer_code="+m_seizer_code+"&pro_invoice_no="+m_pro_invoice_no+"&finance_no="+_m_finance_no+"\";"); 
			out.println(" window.location.href=m_url;"); 
			out.println("m_table.innerHTML=\"\" ");
			out.println("window.print();");
			out.println("}");

		
			out.println("function add_button(){");
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
			}else{
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
			
			//out.println("<br><br>");//COMMENTED BY LALANKA ON 15-07-2009
			//out.println("<br><br>");
			//out.println("<br>");
			out.println("<br>");
		  out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
			
			String			Sql_vehicle="SELECT"+		
			"			NVL(UPPER(C.MAKE_DESC),' ' ) || '-'|| NVL(UPPER("+m_schema_name+".AF_CO_GET_MODEL_DESC(B.MODEL_CODE)), ' ') ||'-'|| NVL(UPPER(D.DESCRIPTION),' ') ||'-'|| NVL(UPPER(F.DESCRIPTION),' ' ) DESCRIPTION, "+
			"			NVL(B.REG_NO,'-') , "+
			"			NVL(B.CHASSIS_NO,'-') , "+
			"			NVL(B.ENGINE_NO,'-') , "+
			//"			D.YEAR_OF_MANUFACTURE, "+
			//"			B.INVOICE_NO "+
			"     UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(E.CLIENT_CODE))  "+
			"			FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
			"			"+m_schema_name+".AF_CO_MAS_MAKE C,"+m_schema_name+".AF_CO_MAS_SUB_MODLE D,"+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY F,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS E, "+
			"     "+m_schema_name+".AF_RE_PRO_REPOSSESSION G "+
			"			WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
			"			B.APPLICATION_NO=E.APPLICATION_NO AND "+
			"     E.FINANCE_NO=G.FINANCE_NO AND "+
			"     B.INVOICE_NO=G.PRO_INVOICE_NO AND "+
			"     G.REPOSSESSION_NO='"+m_repossession_no+"' AND "+
			"			UPPER(E.FINANCE_NO)=UPPER('"+_m_finance_no+"') AND "+
			"			A.ACTIVE_STATUS='Y' AND "+
			"			A.ASSET_ID=B.ASSET_ID AND "+
			"			(C.MAKE_CODE ,F.ITEM_SUB_CAT ) IN "+
			"			(SELECT "+
			"			MAKE_CODE ,ITEM_SUB_CAT "+
			"			FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
			"			WHERE "+
			"			MODEL_CODE IN ( "+
			"			SELECT "+
			"			MODEL_CODE "+
			"			FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
			"			WHERE INVOICE_NO=B.INVOICE_NO "+
			"			AND ACTIVE_STATUS='Y' "+
			"			)) AND "+
			"			D.SUB_CODE=B.SUB_MODEL_CODE ";
			
			  rs = stmt.executeQuery(Sql_vehicle);
	      more = rs.next();
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='70%' class='rep-body1' ><b>Date: "+m_Letter_date+"</td>");
			//out.println("<td width='10%' class='rep-body1' ><b>"+m_reg_no+"</td></tr>"); //modified by Sandun on 19-09-2008
			// Added by Dineth on 2008-09-16
			out.println("<td width='10%' class='rep-body1' >&nbsp;</td></tr>");
			out.println("</table>");

			
			out.println("<br>");	
			//out.println("<br>");	
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_full_name+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_add1+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_add2+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_city_name+"</td></tr>");
			out.println("</table>");	
			out.println("<br>");	
			//out.println("<br>");	
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' ><b>Dear Sir,</td></tr>");
			out.println("</table>");
											
			out.println("<br><br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='20%' class='rep-body1' align='left'   ><b>VEHICLE</td>");
			out.println("    <td width='5%'  class='rep-body1' align='center' ><b>:</td>");
			out.println("    <td width='*%'  class='rep-body1' align='left'   ><b>"+rs.getString(1)+"</td></tr>");
			
			out.println("<tr><td width='20%' class='rep-body1' align='left'   ><b>REGISTRATION NO</td>");
			out.println("    <td width='5%'  class='rep-body1' align='center' ><b>:</td>");
			out.println("    <td width='*%'  class='rep-body1' align='left'   ><b>"+rs.getString(2)+"</td></tr>");
			
			out.println("<tr><td width='20%' class='rep-body1' align='left'   ><b>CHASSIS NO</td>");
			out.println("    <td width='5%'  class='rep-body1' align='center' ><b>:</td>");
			out.println("    <td width='*%'  class='rep-body1' align='left'   ><b>"+rs.getString(3)+"</td></tr>");
			
			out.println("<tr><td width='20%' class='rep-body1' align='left'   ><b>ENGINE NO</td>");
			out.println("    <td width='5%'  class='rep-body1' align='center' ><b>:</td>");
			out.println("    <td width='*%'  class='rep-body1' align='left'   ><b>"+rs.getString(4)+"</td></tr>");
			
			out.println("<tr><td width='20%' class='rep-body1' align='left'   ><b>LEASE AGREEMENT NO</td>");
			out.println("    <td width='5%'  class='rep-body1' align='center' ><b>:</td>");
			out.println("    <td width='*%'  class='rep-body1' align='left'   ><b>"+_m_finance_no+"</td></tr>");
			
			out.println("<tr><td width='20%' class='rep-body1' align='left'   ><b>LESSEE</td>");
			out.println("    <td width='5%'  class='rep-body1' align='center' ><b>:</td>");
			out.println("    <td width='*%'  class='rep-body1' align='left'   ><b>"+rs.getString(5)+"</td></tr>");
						
			out.println("</table>");	
			
			out.println("<hr color='black'>");
						
	    out.println("</font></p></blockquote>");
			
		  out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
		  String data="The Lessee in the above Lease Agreement has defaulted payment of rentals, and we have terminated "+
			            "the Lease of the above asset, by the dispatch of a registered letter to him in terms of the said Lease.";
      
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			data="As per of the provisions of the said Lease Agreement the said Lessee inter-alia agreed to handover peaceful "+
			            "possession of the said asset but has failed to do so upon termination of the said Lease as aforesaid, despite "+
                  "written instructions.";
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			data="You are hereby instructed, within the next "+m_validity_period+"  days to persuade the Lease and/or his or such other "+
			     "person as in possession of the said asset by peaceful means to handover possession of the said asset to "+
           "you or to us. Please also note that the Police of the nearest area should be notified before and after the "+
					 "repossession of the vehicle.";
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			data="I have been authorized to repossess the abovementioned asset which is owned by "+m_orient_name+", "+
			     "and which has been leased out to "+rs.getString(5)+" through a written "+
           "Agreement has failed to pay the monies due to "+m_orient_name+", and they have "+
					 "terminated the lease of the above item/s and have demanded the return thereof.";
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			
			data="You are authorized to obtain the assistance of any competent person to assist you if necessary for the "+
			   	 "purpose mentioned above.";
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			
			data="You are here by authorized to receive the delivery of the said asset for and on our behalf, and upon receipt of "+
			   	 "same, you are instructed to make the following statement to the Police Station referred to in the paragraph";
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			
			data="\"i am a representative of "+m_orient_name+". I have been authorized to "+
			   	 "repossess the above equipment, which I have repossessed on .................................................\"";
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			data="You are hereby directed to refrain from using force of any kind but only to adopt peaceful means. Further, "+
			   	 "you are specially cautioned not to cause any harm or damage to person or asset in the execution of our "+
					 "instruction and we wish to state that, we shall not hold ourselves responsible or liable for any of your action "+
					 "which do not fall within the scope of our specific instructions.";
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			
			data="Upon re-possessing, you are requested to hand over the said asset to our vehicle yard at No 46,48, DR. N. M. PERERA MAWATHA COLOMBO 08, "+
			   	 "immediately obtain an inventory of the repossessed vehicle/asset.";
					 
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
		 
			data="Yours faithfully,";
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+m_orient_name+"</td>");
			out.println("</tr></table>");		
			
			
			/*data="AGM Recoveries";//Commented By Lalanka on 08-06-2009
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");*/
			
			data="-----------------------------------------------------------";
			out.println("<br><br><br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			data="Authorized Signature";//Mod By Lalanka on 08-06-2009
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
		 
			   	
		  out.println("</font></p></blockquote>");		
		
												
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
