//ID         :
//SCREEN NAME:Document Printing - NIBSM Letter
//CREATED BY :Nuwan De Silva	
//DATE/TIME  : 12-02-2006
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Document_NIBSM_Letter extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt;
//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
  public ResultSet rs;
 	

	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_mlease_no,m_print;
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
		
		
				//Decaring variables
			
			String m_full_name="";
			String m_add1="";
			String m_add2="";
			String m_city_name="";
			String m_title="";
			String m_client_type=""; 
			double m_NIBSM=0;
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
			String m_document_code	=req.getParameter("document_code");	
			String m_print=req.getParameter("print");
			
			
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YY') FROM DUAL ");
								
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
        												" NVL(UPPER(TITLE),' '), "+ // "UPPER" Added by Chandana on 04/07/07
																//" NVL(UPPER(FULL_NAME),' '), "+
																" DECODE(CLIENT_TYPE,'I',NVL(UPPER(TITLE),' ')||'. '||NVL(UPPER(FULL_NAME),' '),'C',NVL(UPPER(FULL_NAME),' ')), "+ //MODIFIED BY Chandana on 26/07/2007 For Ref No.748
																" REPLACE(REPLACE(NVL(UPPER(ADDRESS1),' '),'-',' '),'NULL',' '),"+
																" REPLACE(REPLACE(NVL(UPPER(ADDRESS2),' '),'-',' '),'NULL',' '),"+
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
				
				/*if(!m_full_name.equals(" "))
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
				*/
				
				
				 rs=stmt.executeQuery (" SELECT "+
        "  NVL(FINANCE_NO,'-'),NVL(MASTER_AGREEMENT_NO,'-') "+
		    " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
        " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
				
				more = rs.next();
			
			if(more){
			  m_finance_no=rs.getString(1);
				m_mlease_no=rs.getString(2);
			 
			  }
				
				rs=stmt.executeQuery (" SELECT "+
				" SUM(NIBSM) "+ //MODIFIED NUWAN DE SILVA 29-06-07-------
				" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
        " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");

				more = rs.next();
				if(more){
			  m_NIBSM=rs.getDouble(1);
			 
			  }
				
				
				
				
			  out.println("<html><head>"); 
				out.println("<title>NIBSM letter with Right of Set Off (if applicable) </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
      out.println("<script>");
			
			out.println("function save_data(){");
			
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";"); 
		  out.println(" window.location.href=m_url;"); 
			
			out.println("m_table.innerHTML=\"\" ");
			out.println("m_letter='<tr><td width=\"*%\" align=\"center\" class=\"rep-body1\" ><b>To be typed on company letter head</b></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_letter+'</table>';");
				
		
			out.println("window.print();");
			
			
			
			
			out.println("}");
			
		
		  out.println("function add_button(){");
			
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
			out.println("m_letter='<tr><td width=\"*%\" align=\"center\" class=\"rep-body1\" ><b>To be typed on company letter head</b></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_letter+'</table>';");

			}
			else
			{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			out.println("m_letter='<tr><td width=\"*%\" align=\"center\" class=\"rep-body1\" ><b>To be typed on company letter head</b></td></tr>';"); 
			
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+m_letter+'</table>';");
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
			out.println("<tr><td width=\"100%\" class='rep-body1'><b></b></td></tr>");
		  out.println("</table>");

			out.println("</font></p></blockquote>");	
			
		 out.println("<br><br><br><br>");	
		 out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
			
			//out.println("<table border='0' width='80%' class='table'>"); 		
			//out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_Letter_date+"</td></tr>");
			//out.println("</table>");
			//out.println("<br><br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_full_name+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_add1+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_add2+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_city_name+"</td></tr>");
			out.println("</table>");	
			
			out.println("<br><br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_orient_name+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_orient_add1+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_orient_add2+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_orient_city_name+"</td></tr>");
			out.println("</table>");	
			
			out.println("<br><br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' ><b>Dear Sirs,</td></tr>");
			out.println("</table>");
			
			out.println("<br><br>");
			
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' ><b><U>NON - INTEREST BEARING SECURITY MARGIN (NIBSM) WITH AN EMBODIED RIGHT OF SET OFF</U></td></tr>");
			
		 			
			out.println("</table>");
			
			out.println("<br><br>");
			
	    out.println("</font></p></blockquote>");
			
		
	
		 out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
		
		  String data="I/We refer to your Lease Agreement Schedule "+m_finance_no+" and confirm having made the payment of "+
								"the sum of <b>Rs. "+nf.format(m_NIBSM)+"</b> being the NIBSM required by you. ";
								
								
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
						
                  
     data=  "We hereby expressly agree undertake and declare as follows:";
		  
			out.println("<br>");									
			
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
					
		 
    
		data="The Said NIBSM shall be available to you throughout the term of the Lease Agreement and in "+
									          "the event of the NIBSM being diminished by reason of any set off as mentioned below or "+
													  "otherwise howsoever I/we undertake to make good any such shortfall immediately.Failure on "+
														"our part to do so would entitle you, without prejuidice however to any other rights you may "+
														"have in low or under the lease agreement and the consequences thereupon will then follow. ";
			
			out.println("<br>");									
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body1' style='text-align:left' valign=top >1.</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
								
			             data="You are hereby expressly authorized to apply the said NIBSM and set off the proceeds thereof "+
									 "against any and all amounts due from me/us to you under the <b>Master Lease Agreement "+
									 "No. "+m_mlease_no+" </b> or otherwise howsoever including, but not limited to, overdue lease rentals "+
									 "or interest, legal fees, repossession charges, license fees, insurance premiums, etc.";
			
			out.println("<br>");									
			
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body1' style='text-align:left' valign=top >2.</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			
																		
									
		             data="I/We shall not pledge, mortgage assign or otherwise encumber the said NIBSM in favor of any "+	
		             "person or persons during the term of the lease; nor shall we permit the NIBSM or the proceeds "+
								 "thereof (or any part thereof) becoming subject or liable to be seized in respect of a claim of "+
								 "any third party it being expressly understood that during the term of the lease you shall have "+
								 "the sole control, custody and the right as a aforesaid to apply the proceeds of the said NIBSM in "+
								 "priority and to the exclusion of any person claiming under me/us or any other person whomsoever. ";
			
			out.println("<br>");									
			
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body1' style='text-align:left' valign=top >3.</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			
			
		
		       data= "You shall be entitled, and hereby expressly and irrevocably directed and authorized, to set "+	
		             "off appropriate or apply any lease rental or other amount received by you from me/us under or in "+
								 "terms of any of the said lease agreements against or in reduction or settlement of any lease "+
								 "rental, arrear or lease rental or other amount whatsoever due to you under or in terms of any "+
								 "other lease agreement or lease agreements referred to above ";
			
			out.println("<br>");									
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body1' style='text-align:left' valign=top >4.</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
									
	
	        data="The right of set off as aforesaid shall be in addition to, not in substitution of and shall not "+	
		           "prejudice or be prejudiced by any right or remedy which you shall have under the said lease "+
							 "agreement or any one or more of them. ";
			out.println("<br>");														
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body1' style='text-align:left' valign=top >5.</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");							
									
									
	     data=     "It is expressly agreed and understood that you right of set off as aforesaid may be exercise at "+	
		             "any time your sole discretion <u>irrespective</u> of whether or not any of the lease agreement is  "+
								 "in default at the time of the excercise of your right of set off and <u>notwithstanding</u> that the lease "+
								 "rentals under any of the said lease agreements may, by the exercise of the said right of set off, "+	
								 "be or became in arrears or in default. ";
			out.println("<br>");									
									
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body1' style='text-align:left' valign=top >6.</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			

                 data="Your right of set off as aforesaid shall extended to and apply in respect of any amount received "+	
		             "by you from any other person whomsoever (including guarantors) under any security granted "+
								 "in respect of any of the lease agreements or the proceeds thereof which may be applied at "+
								 "your discretion in reduction of my/our liability under any other lease agreement or lease "+	
								 "agreements.";
	out.println("<br>");																	
									
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body1' style='text-align:left' valign=top >7.</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
							

out.println("<br><br><br><br><B>"+m_full_name+"");
						
    		
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
