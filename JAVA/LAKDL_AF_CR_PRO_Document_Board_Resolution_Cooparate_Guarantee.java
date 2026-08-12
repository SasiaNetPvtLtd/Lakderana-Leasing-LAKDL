//ID         :
//SCREEN NAME:Document Printing - Board Resolution for Cooparate Guarantee
//CREATED BY :Nuwan De Silva	
//DATE/TIME  : 12-02-2006
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Document_Board_Resolution_Cooparate_Guarantee extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt;
//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
  public ResultSet rs;
 	

	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print,m_trn_type;
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
		
		//check_number
				//Decaring variables
			
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
			String m_address_orient="";
			
			String m_orient_init_name="";
		  String m_orient_init_add1="";
			String m_orient_init_add2="";
			String m_orient_init_city_name="";
						
						
		 String m_chksql = req.getParameter("chksql");
			
  	 if(m_chksql.trim().equals("main_page")){
			
			  
				
			stmt = conn.createStatement ();
						
			String m_application_no = req.getParameter("application_no");
			String m_gur_code	  =req.getParameter("gur_code");		
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
		" WHERE APPLICATION_NO=UPPER('"+m_application_no+"') AND CLIENT_CODE=UPPER('"+m_gur_code+"') AND DOCUMENT_CODE=UPPER('"+m_document_code+"') ");

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
					    " UPPER(COMPANY_NAME), "+
					    " UPPER(ADDRESS1), "+
					    " UPPER(ADDRESS2), "+
					    " UPPER(CITY), "+
					    " TEL_NO, "+
					    " FAX_NO,  "+
							" VAT_RATE, "+
							" INITCAP(COMPANY_NAME), "+
							" INITCAP(ADDRESS1), "+
							" INITCAP(ADDRESS2), "+	
							" INITCAP(CITY) "+
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
											m_orient_init_name=rs.getString(8);
						          m_orient_init_add1=rs.getString(9);
						          m_orient_init_add2=rs.getString(10);
						          m_orient_init_city_name=rs.getString(11);
											
											}
											
											//Added by Mahela on 10-07-2007
											if(!m_orient_name.equals("-") && !m_orient_add1.equals("-") && !m_orient_add2.equals("-") && !m_orient_city_name.equals("-") )
											{
											m_address_orient=m_orient_name.toUpperCase()+","+m_orient_add1+","+m_orient_add2+","+m_orient_city_name;
											}			
											else if(!m_orient_name.equals("-") && !m_orient_add1.equals("-") && !m_orient_add2.equals("-") && m_orient_city_name.equals("-") )
											{
											m_address_orient=m_orient_name.toUpperCase()+","+m_orient_add1+","+m_orient_add2;
											}
											else if(!m_orient_name.equals("-") && !m_orient_add1.equals("-") && m_orient_add2.equals("-") && !m_orient_city_name.equals("-") )
											{
											m_address_orient=m_orient_name.toUpperCase()+","+m_orient_add1+","+m_orient_city_name;
											}
											else if(!m_orient_name.equals("-") && m_orient_add1.equals("-") && m_orient_add2.equals("-") && !m_orient_city_name.equals("-") )
											{
											m_address_orient=m_orient_name.toUpperCase()+","+m_orient_city_name;
											}
											else if(!m_orient_name.equals("-") && m_orient_add1.equals("-") && m_orient_add2.equals("-") && m_orient_city_name.equals("-") )
											{
											m_address_orient=m_orient_name.toUpperCase();
											}
		
					
					
					  rs = stmt.executeQuery(" SELECT "+
        												" NVL(TITLE,''), "+
																" NVL(UPPER(FULL_NAME),' '), "+
																" REPLACE(REPLACE(NVL(UPPER(REGISTERED_ADDRESS1),' '),'-',' '),'null',' '),"+
																" REPLACE(REPLACE(NVL(UPPER(REGISTERED_ADDRESS2),' '),'-',' '),'null',' '),"+
																" REPLACE(REPLACE(NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),'-',' '),'null',' ') "+ //Added by Chandana for Ref No.765 on 03/08/2007
                                //" UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(REGISTERED_CITY_CODE)) "+ //Modified by Chandana for Ref No.765 on 03/08/2007
																" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
                                " WHERE UPPER(CLIENT_CODE)=UPPER('"+m_gur_code+"')");
																/*" WHERE   CLIENT_CODE ="+
                                " (SELECT "+
		                            " CLIENT_CODE "+
                                " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
                                " WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"'))");
																*/
																
    
			 more = rs.next();		
				
				if(more){
				m_title=rs.getString(1);
				m_full_name=rs.getString(2);
				m_add1=rs.getString(3);
				m_add2=rs.getString(4);
				m_city_name=rs.getString(5);
							
				
				}
				
				//if(!m_full_name.equals(" "))
				//{
				//m_full_name=m_full_name+",";
			//	}
				
				if(!m_add1.equals(" "))
				{
				m_add1=m_add1+",";
				}
				if(!m_add2.equals(" "))
				{
				m_add2=m_add2+",";
				}		
				
				 rs=stmt.executeQuery (" SELECT "+
        "  NVL(FINANCE_NO,'-'), "+
				" DECODE(TRANSACTION_TYPE,'FINLEASE','Finance Lease','OPELEASE','Operating Lease','HIREPURCH','Lease Purchase') "+ 
		    " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
        " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
				
				more = rs.next();
			
			if(more){
			  m_finance_no=rs.getString(1);
			  m_trn_type=rs.getString(2);
			  }

				
				
				String m_client_name ="";
				String m_client_code ="";
				
				rs=stmt.executeQuery (" SELECT A.FULL_NAME,A.CLIENT_CODE "+
				" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A, "+
				" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				" WHERE  A.CLIENT_CODE = B.CLIENT_CODE "+
				" AND B.APPLICATION_NO = UPPER('"+m_application_no+"') "); 
								
				more = rs.next();
			
			 if(more){
			  m_client_name=rs.getString(1);
			  m_client_code=rs.getString(2);
			  }
				
				
				
				
				
				
				
			  out.println("<html><head>"); 
				out.println("<title>Acceptance Receipt </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
			out.println("<script>");
			
			out.println("function save_data(){");
			
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_gur_code+"&document_code="+m_document_code+"\";"); 
		  out.println(" window.location.href=m_url;"); 
			//out.println("m_table.innerHTML=\"\" ");
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
				
			//New Letter format added by Chandana on 05-09-2007	
			//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX		
				
				
				
					out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">");
	   		out.println("<INPUT TYPE='Hidden' NAME='hid_client_type' VALUE=\"\">");
 
										
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
			
				out.println("<br><br>");
			  out.println("<br><br>");
			  out.println("<br><br>");
			
			  out.println("<font size=2><p style='text-align:center' class='rep-body1'>");	
			 	
				out.println("<table border='0' width='90%' class='table'>"); 		
			  out.println("<tr><td width='*%' class='rep-body1' align='right' ><b><U>BOARD RESOLUTION</U></b></td></tr>");
			  out.println("</table>");	
				out.println("<br><br>");
				
				out.println("<table border='0' width='90%' class='table'>"); 		
			  out.println("<tr><td width='*%' class='rep-body1' align='center' ><b>EXTRACT OF THE MINUTES OF THE BOARD MEETING OF </td></tr>");
			  out.println("<tr><td width='*%' class='rep-body1' align='center' ><b><I>"+m_full_name+"</I></b></td></tr>");
			  out.println("<tr><td width='*%' class='rep-body1' align='center' ><b><I>duly registered under Companies laws of Sri Lanka</I></b></td></tr>");
				out.println("<tr><td width='*%' class='rep-body1' align='center' ><b>AT THE REGISTERED OFFICE OF THE COMPANY</b></td></tr>");
				out.println("<tr><td width='*%' class='rep-body1' align='center' ><b><I>"+m_add1+" "+m_add2+"</I></b></td></tr>");
				out.println("<tr><td width='*%' class='rep-body1' align='center' ><b><I>"+m_city_name+"</I></b></td></tr>");
				
				
				out.println("</table>");
				out.println("</font>");
			
	      out.println("<br><br>");
		 	
		    out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
					
			 String data="It is a hereby resolved that as it has been agreed with the "+m_orient_init_name+" having its registered office at "+
				           "No.75, Arnold Ratnayake Mawatha, Colombo 10 and the principle place of the business at "+m_orient_init_add1+", "+m_orient_init_add2+", " +m_orient_init_city_name+" "+
									 "carrying on business that the company do execute a corporate Guarantee infavour of "+m_orient_init_name+" in consideration of granting "+m_trn_type+" Facility "+
									 "to "+m_client_name+" ";
									
	
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body1' style='text-align:left' valign=top ></td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
			
			out.println("<br><br>");
			
			 data=" It was further RESOLVED that";
		
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%'></td><td width='*%' class='rep-body1' style='text-align:left' >"+data+"</td>");
			out.println("</tr></table>");
			out.println("</font></p></blockquote>");	
	    			
			out.println("<blockquote><font size=2><p style='text-align:left' class='rep-body1'>");
		
		 out.println("<table border='0' width='90%' class='table'>"); 		
		 out.println("<tr><td width='1%' class='rep-body1'  >&nbsp</td><td width='1%' class='rep-body1'  ><li></td>");
		 out.println("<td width='80%' class='rep-body1'  >any two directors of the company</td></tr>");
     
		 out.println("<tr><td width='5%' class='rep-body1'  >&nbsp</td><td width='5%' class='rep-body1'  ><li></td>");
		 out.println("<td width='80%' class='rep-body1'  >any one director and secretary of the company</td></tr>");	

     out.println("<tr><td width='5%' class='rep-body1'  >&nbsp</td><td width='5%' class='rep-body1'  ><li></td>");
		 out.println("<td width='80%' class='rep-body1'  >A director of the company</td></tr>");
 
     data = "are/ is hereby authorized and empowered place the common seal/ or to sign and execute the corporate guarantee.";
			      
	
     out.println("<tr><td width='5%' class='rep-body1'  >&nbsp</td><td width='5%' class='rep-body1'  ><li></td>");
		 out.println("<td width='80%' class='rep-body1'  >"+data+"</td></tr>");

     out.println("</table>");
		 out.println("</font></p></blockquote>");		
		 out.println("<br><br>");	
			
		 out.println("<blockquote><font size=2><p style='text-align:left' class='rep-body1'>");
		 out.println("<table border='0' width='90%' class='table'>"); 	
		  
		 out.println("<tr><td width='1%'></td><td width='69%' align='left' class='rep-body1' >............................................</td>");
		 out.println("    <td width='20%' align='left' class='rep-body1' >....................................................</td></tr>");
		 out.println("<tr><td width='1%'></td><td width='69%' align='left' class='rep-body1' >Director</td>");
		 out.println("    <td width='20%' align='left' class='rep-body1' >Director/Secretary</td></tr>");
			
		 out.println("</table>");
		 out.println("</font></p></blockquote>");		
		
		 out.println("<br>");
		 out.println("<blockquote><font size=2><p style='text-align:left' class='rep-body1'>");
		 out.println("<table border='0' width='90%' class='table'>"); 	
		  
		 out.println("<tr><td width='1%'></td><td width='40%' align='left' class='rep-body1' >Certified to be a true</td>");
		 out.println("    <td width='40%' align='left' class='rep-body1' >&nbsp</td></tr>");	
		 out.println("<tr><td width='1%'></td><td width='40%' align='left' class='rep-body1' >copy of the Resolution</td>");
		 out.println("    <td width='40%' align='left' class='rep-body1' >&nbsp</td></tr>");	
		 out.println("<tr><td width='1%'></td><td width='40%' align='left' class='rep-body1' >passed by board of Director</td>");
		 out.println("    <td width='40%' align='left' class='rep-body1' >&nbsp</td></tr>");	
		 out.println("<tr><td width='1%'></td><td width='40%' align='left' class='rep-body1' >of the Company</td>");
		 out.println("    <td width='40%' align='left' class='rep-body1' >&nbsp</td></tr>");	
	
     out.println("</table>");
		 out.println("</font></p></blockquote>");			
	   
		 out.println("<br>");	
		 out.println("<blockquote><font size=2><p style='text-align:left' class='rep-body1'>");
		 out.println("<table border='0' width='90%' class='table'>"); 	
		 out.println("<tr><td width='1%'></td><td width='*%' align='left' class='rep-body1' >On this ....................................................................... day of ................................ 2007. </td>");
		 out.println("</tr>");	
     out.println("</table>");
		 out.println("</font></p></blockquote>");	
		  
		 out.println("<br><br>");
		 out.println("<br><br>");	 	
		 out.println("<table></table>");	

				
				
				
				
			//Comment by Chandana on 05-09-2007	
			//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX	
			/*				
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
			
			
			
			out.println("<br><br>");
			out.println("<br><br>");
			out.println("<br><br>");
			out.println("<br><br>");
			
			out.println("<font size=2><p style='text-align:center' class='rep-body1'>");	
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' align='center' ><b>Resolution passed by the Board of Directors of </td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' align='center' ><b>"+m_full_name+" </td></tr>");
			
			out.println("</table>");
			
			
			
			out.println("</font>");
			
	    out.println("<br><br>");
			
			
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
						  
			String data="WHEREAS the Lessee morefully described in the Master Finance Lease Agreement  "+
									"has entered into Master Finance Lease Agreement as setout and described in the said "+
									"agreeement with "+m_orient_name.toUpperCase()+" a company "+
									"duly incorporated under the companies act No.17 of 1982 and having its registered "+
									"office at <b>"+m_address_orient+".</b> in the Democratic Socialist Republic of "+
									"Sri Lanka (hereinafter refered to as \"the "+m_schema_name+"\") to take on lease the Articles "+
									"morefully described in the schedule/schedules of the said Master Finance Lease "+
									"Agreement";
									
					
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
			
			out.println("<br><br>");
			
			data=""+m_full_name+" a company duly incorporated under "+
								"the companies act No. 17 of 1982 and having its registered office at "+m_add1+" "+m_add2+" " +m_city_name+" "+
								"in the Democratic Socialist Republic of Sri "+
								"Lanka (hereinafter referred to as \"The Company\") has made arrangements with "+
								"the "+m_orient_name.toUpperCase()+" a company duly "+
								"incorporated  under the companies act No.17 of 1982 and having its registered "+
								"office at "+m_orient_name+" NO 100, HYDE PARK CORNER,COLOMBO 2 in the Democratic Socialist Republic of "+ //"+m_orient_add1+", "+m_orient_add2+" " +m_orient_city_name+".hard codded the address nuwan de silva on 01-09-07
								"Sri Lanka";
								
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body1' style='text-align:left' valign=top >1.</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
			
			out.println("<br>");
			
			data="To furnish a guarantee to secure the repayment to OFSCL of the said facility by the said Lessee.";
		
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body1' style='text-align:left' valign=top >2.</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
			
			
			data="The <b><i>company</i></b> is empowered by its memorandum and Articles of Association to "+
			   					 "guarantee indemnify or become liable for the payment of money or for the "+
									 "performance of any obligations by any other company firm or person ";
										
			out.println("<br>");							
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body1' style='text-align:left' valign=top >3.</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
			
			
			
			data=		     "AND IT IS HEREBY RESOLVED that the <b><i>Company</i></b> do execute the guarantee in favour "+
			   					 "of "+m_schema_name+" in the form acceptable to "+m_schema_name+" for the repayment of said Lease facility in "+
									 "accordance with the terms and conditions stipulated in lease agreement has stated  "+
									 "above and that the common seal of the company be afficed to such number of "+
									 "endorsement of the said guarantee has may be required "+m_schema_name+" and such sealing be "+
									 "attested by any two of the under mentioned Directors of the company. ";
			out.println("<br><br>");
				
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
										
		out.println("<br><br><br><br>");							 										
																	 
		out.println("<table border='0' width='90%' class='table'>"); 		
		out.println("<tr><td width='*%' class='rep-body1' >1.</td></tr>");
		out.println("</table>");
		out.println("<br><br><br><br><br>");							 										
		out.println("<table border='0' width='90%' class='table'>"); 		
		out.println("<tr><td width='*%' class='rep-body1' >2.</td></tr>");
		out.println("</table>");
		out.println("<br><br><br><br><br>");							 										
		out.println("<table border='0' width='90%' class='table'>"); 		
		out.println("<tr><td width='*%' class='rep-body1' >3.</td></tr>");
		out.println("</table>");
		out.println("<br><br><br><br><br>");							 										
		out.println("<table border='0' width='90%' class='table'>"); 		
		out.println("<tr><td width='*%' class='rep-body1' >4.</td></tr>");
		out.println("</table>");
		
		out.println("</table>");
		
		
		out.println("<table border='0' width='90%' class='table'>"); 		
		out.println("<tr><td width='*%' class='rep-body1' align='center' >By Order of Board</td></tr>");
		out.println("</table>");
    		
		out.println("</font></p></blockquote>");		
		*/
												
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
