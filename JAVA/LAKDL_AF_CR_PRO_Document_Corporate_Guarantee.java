//ID         :
//SCREEN NAME:Document Printing - Corporate Gurantee
//CREATED BY :Nuwan De Silva	
//DATE/TIME  : 07-02-2006
//NOTES:
                
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Document_Corporate_Guarantee extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt;
//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
  public ResultSet rs;
 	

	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print,m_master_agree_no;
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
			
		// out.println("conn"+conn);
			String m_full_name="";
			String m_add1="";
			String m_add2="";
			String m_city_name="";
			String m_title="";
			String m_client_type=""; 
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
						
			String m_application_no = req.getParameter("application_no");
			String m_client_code	  =req.getParameter("client_code");		
			String m_gur_name	  =req.getParameter("gur_name");		
			String m_document_code	=req.getParameter("document_code");	
			String m_print=req.getParameter("print");
			
						
				
					rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'fmddth')|| ' ' || 'Day of'|| ' ' || TO_CHAR(SYSDATE, 'Month')|| TO_CHAR(SYSDATE, 'YYYY') LETTER_DATE FROM DUAL ");
				            
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
			
				
				//Modified by Mahela on 21-06-2007	
        rs=stmt.executeQuery (" SELECT "+
        "  NVL(FINANCE_NO,'-'), "+
				"  NVL(MASTER_AGREEMENT_NO,'-')"+
        " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
        " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
				
				more = rs.next();
			
			if(more){
			  m_finance_no=rs.getString(1);
			  m_master_agree_no=rs.getString(2);
			  }
				
								  rs = stmt.executeQuery(" SELECT "+
        												" NVL(UPPER(TITLE),''), "+ //UPPER - Added by Chandana on 04/07/2007
																" NVL(UPPER(FULL_NAME),' '), "+
																" REPLACE(REPLACE(NVL(UPPER(ADDRESS1),' '),'-',' '),'null',' '),"+
																" REPLACE(REPLACE(NVL(UPPER(ADDRESS2),' '),'-',' '),'null',' '),"+
                                " UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)) "+
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
				
					 rs = stmt.executeQuery(" SELECT "+
					    " UPPER(COMPANY_NAME), "+
					    " UPPER(ADDRESS1), "+
					    " UPPER(ADDRESS2), "+
					    " UPPER(CITY), "+
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
				
			/*	if(!m_full_name.equals(" "))
				{
				m_full_name=m_full_name;
				}
				
				if(!m_add1.equals(" "))
				{
				m_add1=m_add1+",";
				}
				if(!m_add2.equals(" "))
				{
				m_add2=m_add2+",";
				}
				*/
				
				
				
				
			  out.println("<html><head>"); 
				out.println("<title>Acceptance Receipt </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
			out.println("<script>");
			
			out.println("function save_data(){");
			
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&gur_name="+m_gur_name+"&document_code="+m_document_code+"\";"); 
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
				
			//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
				
			out.println("<body bgcolor='white'><br>");
				
			
				
				out.println("<form name='Form1'>");
				
							
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     out.println("</tr>"); 
			
			   out.println("</table>");
    			
			out.println("<blockquote><font size=3><p style='text-align:left'>");					

			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr><td width=\"100%\" class='rep-body1'><b></b></td></tr>"); //"+m_status+" Modified by Chandana on 03/08/2007
		  out.println("</table>");

			out.println("</font></p></blockquote>");	
			
			out.println("<font size=3><p style='text-align:center'>");				
						
			out.println("<table border='0' width='100%' class='table' align='center'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' align='center'><B>GUARANTEE AND INDEMNITY TO THE MASTER FINANCE LEASE </td></tr>");
	  	out.println("<tr><td width='*%' class='rep-body1' align='center'><b>AGREEMENT NO.&nbsp; "+m_master_agree_no+"</td></tr>");		  
			out.println("<tr><td width='*%' class='rep-body1' align='center'><b></td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' align='center'><b>SCHEDULE NO : "+m_finance_no+"</td></tr>");
		  out.println("</table>");
										
			out.println("</font></p></center>");
			
			out.println("<blockquote><font size=2><p style='text-align:left'>");				
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='90%' class='rep-body1' >TO:&nbsp;<b>"+m_orient_name+" OF NO.75,ARNOLD RATNAYAKE MAWATHA,COLOMBO 10</B>&nbsp;and its successor - in title and assigns.</td>");
			out.println("</table>");
			
			out.println("</font></p></blockquote>");
			
		  out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
	   	  
			String data="In consideration of your entering at our request in to the foregoing Master Lease "+
                  "Agreement (hereinafter called \"Master Finance Lease Agreement \" the term Master "+
								  "Lease Agreement shall mean and include the said Master Finance Lease "+
								  "Agreement and all addendums and schedules that are to be executed subsequently) with "+
								  "the Lessee as mentioned in schedule to the Master Finance Lease  Agreement (hereinafter "+
								  "called \"the Lessee\" which expression shall include its successors -in-title and permitted assigns.)"; 
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
			
			out.println("<br>");
			
			data=                "We the undersigned, a company duly incorporated in the said republic and having its "+
			                     "registered office / principal place of business at the given address do hereby guarantee "+
								           "to you the punctual payment by the Lessee of all rental, interest, the Stipulated Loss  "+
									         "value referred to in schedule of the Master Lease Agreement and all other sums "+
									         "whatsoever due under the Master Lease Agreement and the performance of all the "+
									         "Lessee's obligations there under and we undertake to indemnify you at Colombo on  "+
									         "demand against all losses, expenses (including legal costs on a full indemnity basis) "+
									         "charges and damages incurred or suffered by you during the entirety of the terms/ "+
									         "extended term of the Master Lease Agreement in consequence of any failure by the "+
									         "Lessee to perform any of one of the Lessee's obligations whether express or implied "+
									         "under the Master Lease Agreement and including the payment of any damages and/or "+
									         "costs awarded to the Lessor by a competent Court of Law in respect of the said "+
									         "Master Lease Agreement.";
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body1' style='text-align:left' valign=top >1.</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
      			         data=   "We specifically agree that our liability under this guarantee and indemnity shall be as "+
														 "principal debtors and not merely as sureties and shall be continuing security and shall "+
														 "be irrevocable and our liability shall not be in any way discharged, diminished or "+
														 "affected by the granting of time or indulgence to the Lessee or by the affecting of any "+
														 "compromise with the Lessee or any subsequent agreement addendum to any "+
														 "subsequent amendments, additions, substitutions, inclusions not to sue the Lessee or "+
														 "any variations of the Master Lease Agreement or any change in the constitution of the "+
														 "Lessee and our liability hereunder shall subsist whether or not you have a legal right "+
														 "and whether or not you have availed yourself of your legal remedies against the "+
												     "Lessee and our liability shall also extend to cover any renewal or renewals of the "+
														 "Master Lease Agreement and that this guarantee and indemnity shall not be affected "+
														 "or prejudiced by any other guarantees and /or indemnities and any other forms of "+
														 "security now or hereafter held by the Lessor.";		
		  out.println("<br>");
			
      out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body1' style='text-align:left' valign=top >2.</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
			
			data=                  "We further agree that no relaxation, forbearance or indulgence granted by you to us "+	
								             "shall affect our liability to you hereunder nor shall any release of or agreement not to "+
														 "sue us or liability hereunder and that this Guarantee and indemnity shall bind our "+
														 "successors - in-title and assigns and shall  not be determined or affected in any way by "+
														 "the liquidation/winding up of the company."; 
		  out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body1' style='text-align:left' valign=top >3.</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
			
			data=                  "We specifically agree that you shall be at liberty either in one action to sue the Lessee "+	
								             "and us and also any other person or persons all jointly and severally or to proceed in "+
														 "the first instance against us only and further that we hereby renounce the right to "+
														 "claim that the Lessee should be excused or proceeded against by action in the first "+
														 "instance and the right to claim that you should divide your claim and bring action "+	
														 "against us or any other person or persons whomsoever each pro rata and the right to "+
														 "claim in any action brought against us with or without all or any other person that you "+
														 "should only recover from us pro rate the amount claimed and all other rights and "+
														 "benefits to which sureties are or may be law entitled IT BEING AGREED that We "+
														 "are liable in all respects hereunder as principal debtor to the extent aforementioned "+
														 "including the liability to be sued before recourse is had against the Lessee.";
															
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body1' style='text-align:left' valign=top >4.</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");

								
									
									
		out.println("<br><br>Date this <b>"+m_Letter_date+"</b> at Colombo");
						
    		
		out.println("</font></p></blockquote>");		
		
		out.println("</blockquote><font size=2><p style='text-align:left'>");				
		
						
		out.println("<table border='0' width='90%' class='table' align='center'>"); 		
		
		out.println("<tr><td width='55%' class='rep-body1' align='left'><B>The common seal of the within name</b></td>");
		out.println("    <td width='35%' class='rep-body1' align='left'>&nbsp;</td>");
		
		
		out.println("<tr><br><br><td width='55%' class='rep-body1' align='left'><b>"+m_gur_name.toUpperCase()+"</b></td>");
		out.println("    <td width='35%' class='rep-body1' align='center'><b>Seal</b></td>");
   
		out.println("<tr><td width='55%' class='rep-body1' align='left'><b>was affixed hereto in the presence of......................</b></td>");
		out.println("    <td width='35%' class='rep-body1' align='left'>&nbsp;</td>");
		
		out.println("<tr><td width='55%' class='rep-body1' align='left'><b>................................................................................</b></td>");
		out.println("    <td width='35%' class='rep-body1' align='left'>&nbsp;</td>");
		
		out.println("<tr><td width='55%' class='rep-body1' align='left'><b>................................................................................</b></td>");
		out.println("    <td width='35%' class='rep-body1' align='left'>");
		
		out.println("<table border='0' width='100%' class='table' align='center'>"); 		
		out.println("    <tr><td width='*%' class='rep-body1' align='left'><b>Director</b></td>");
		out.println("    <td width='*%' class='rep-body1' align='left'><b>Director</b></td></tr>");
		out.println("</td></table>");
		
		
		
		out.println("<tr><td width='55%' class='rep-body1' align='left'><b>two Directors of the said company who do hereby </b></td>");
		out.println("    <td width='35%' class='rep-body1' align='left'>&nbsp;</td>");
		
		
		
		out.println("<tr><td width='55%' class='rep-body1' align='left'><b>attest the sealing thereof</b></td>");
		out.println("    <td width='35%' class='rep-body1' align='left'>&nbsp;</td>");
   
		out.println("<tr><td width='55%' class='rep-body1' align='left'>&nbsp;</td>");
		out.println("    <td width='35%' class='rep-body1' align='left'><b>Registered Address/</b></td>");
		
		out.println("<tr><td width='55%' class='rep-body1' align='left'>&nbsp;</td>");
		out.println("    <td width='35%' class='rep-body1' align='left'><b>Principle place of business</b></td>");
		
		
		
		out.println("</table>"); 
		
		out.println("<table border='0' width='90%' class='table' align='center'>"); 		
		out.println("<tr><td width='*%' class='rep-body1' align='left'>WITNESSES:</td></tr>");
		out.println("</table>"); 
		out.println("<br><br>"); 
		
		out.println("<table border='0' width='90%' class='table' align='center'>"); 		
		out.println("<tr><td width='*%' class='rep-body1' align='left'>1.</td></tr>");
		out.println("</table>"); 
		out.println("<br><br>");
		//out.println("<br><br>"); 
		out.println("<table border='0' width='90%' class='table' align='center'>"); 		
		out.println("<tr><td width='*%' class='rep-body1' align='left'>2.</td></tr>");
		out.println("</table>"); 
						
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
