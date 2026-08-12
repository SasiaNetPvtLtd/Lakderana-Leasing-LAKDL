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

public class LAKDL_AF_CR_PRO_Document_Discrepancies_Letter extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt2;
	java.text.NumberFormat nf;
	
  public ResultSet rs,rs2;
 	

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
			String m_client_type="",m_nic_no=""; 
			double m_NIBSM=0;
			 int m_data_count=0;
		   String m_status ="",m_activated_date="";
			
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			
			String m_chassis_no="",m_engine_no="",m_invoice_no="",m_chassis_no_new="",m_engine_no_new="",m_asset_desc="";
			
						
		 String m_chksql = req.getParameter("chksql");
			
  	 if(m_chksql.trim().equals("main_page")){
			
			  
				
			stmt = conn.createStatement ();
			stmt2 = conn.createStatement ();			
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
        												" NVL(UPPER(TITLE),' '), "+ 
																" DECODE(CLIENT_TYPE,'I',NVL(UPPER(TITLE),' ')||'. '||NVL(UPPER(FULL_NAME),' '),'C',NVL(UPPER(FULL_NAME),' ')), "+ //MODIFIED BY Chandana on 26/07/2007 For Ref No.748
																" REPLACE(REPLACE(NVL(UPPER(ADDRESS1),' '),'-',' '),'NULL',' '),"+
																" REPLACE(REPLACE(NVL(UPPER(ADDRESS2),' '),'-',' '),'NULL',' '),"+
                                " NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' ') ,"+
																" CLIENT_CATEGORY ,"+
																" NIC_NO "+
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
				m_client_type=rs.getString(6);
				m_nic_no=rs.getString(7);
				}
				
				
				
				 rs=stmt.executeQuery (" SELECT "+
        "  NVL(FINANCE_NO,'-'),NVL(MASTER_AGREEMENT_NO,'-'),TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY') "+
		    " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
        " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
				
				more = rs.next();
			
			if(more){
			  m_finance_no=rs.getString(1);
				m_mlease_no=rs.getString(2);
			  m_activated_date=rs.getString(3);
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
				out.println("<title>Discrepancies Letter </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
      out.println("<script>");
			
			out.println("function save_data(){");
			
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_CR_PRO_DISCRE_LETTER&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";"); 
		  out.println(" window.location.href=m_url;"); 
			
			out.println("m_table.innerHTML=\"\" ");
			out.println("m_letter='<tr><td width=\"*%\" align=\"center\" class=\"rep-body1\" ><b>To be typed on company letter head</b></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_letter+'</table>';");
				
		
			out.println("window.print();");
			
			
			
			
			out.println("}");
			
		
		  out.println("function add_button(){");
			
			if (m_print.trim().equals("FALSE")) {
			if(!m_client_type.equals("INDIVIDUAL") && !m_client_type.equals("SOLEPROPRI") ){
			out.println("m_table.innerHTML=\"\" ");
			out.println("m_letter='<tr><td width=\"*%\" align=\"center\" class=\"rep-body1\" ><b>To be typed on company letter head</b></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_letter+'</table>';");
      }
			
			}
			else
			{
			
			if(!m_client_type.equals("INDIVIDUAL") && !m_client_type.equals("SOLEPROPRI") ){
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			out.println("m_letter='<tr><td width=\"*%\" align=\"center\" class=\"rep-body1\" ><b>To be typed on company letter head</b></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+m_letter+'</table>';");
			}
			
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
					
			rs=stmt.executeQuery (" SELECT "+
			" INVOICE_NO, "+
      " NVL(ENGINE_NO,'-'), "+
      " NVL(CHASSIS_NO,'-') "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DET_BK "+
			" WHERE UPPER(APPLICATION_NO) = UPPER('"+m_application_no+"') "+
			" AND MOD_DATE ="+
			" (SELECT MAX(MOD_DATE) FROM  "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DET_BK "+
			" WHERE UPPER(APPLICATION_NO) = UPPER('"+m_application_no+"') )");
			
			/*out.println(" SELECT "+
			" INVOICE_NO, "+
      " NVL(ENGINE_NO,'-'), "+
      " NVL(CHASSIS_NO,'-') "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DET_BK "+
			" WHERE UPPER(APPLICATION_NO) = UPPER('"+m_application_no+"') "+
			" AND MOD_DATE ="+
			" (SELECT MAX(MOD_DATE) FROM  "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DET_BK "+
			" WHERE UPPER(APPLICATION_NO) = UPPER('"+m_application_no+"') )");
			*/
			more=rs.next();
			
			while (more){		
			m_invoice_no=rs.getString(1);
			m_chassis_no=rs.getString(3);
		  m_engine_no=rs.getString(2);
			
			rs2=stmt2.executeQuery (" SELECT "+
			" NVL(ENGINE_NO,'-'), "+
      " NVL(CHASSIS_NO,'-') ,"+
			" "+m_schema_name+".AF_CO_GET_MAKE_DESC("+m_schema_name+".AF_CO_GET_MAKE_CODE(MODEL_CODE)) || ' ' || "+m_schema_name+".AF_CO_GET_MODEL_DESC(MODEL_CODE) "+
			
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
			" WHERE UPPER(APPLICATION_NO) = UPPER('"+m_application_no+"')  AND  UPPER(INVOICE_NO)=UPPER('"+m_invoice_no+"')  ");  
   
			/*out.println(" SELECT "+
			" NVL(ENGINE_NO,'-'), "+
      " NVL(CHASSIS_NO,'-') "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
			" WHERE UPPER(APPLICATION_NO) = UPPER('"+m_application_no+"')   ");
			*/
			boolean more_2=rs2.next();
			
			if (more_2){
			m_chassis_no_new=rs2.getString(2);
		  m_engine_no_new=rs2.getString(1);
			m_asset_desc=rs2.getString(3);
			}
			
			
			if(!m_chassis_no_new.equals(m_chassis_no) || !m_engine_no_new.equals(m_engine_no) ) {
			
			for (int i=0;i<3;i++){
			out.println("<blockquote><font size=3><p style='text-align:left'>");					

			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr><td width=\"100%\" class='rep-body1'><b></b></td></tr>");
		  out.println("</table>");

			out.println("</font></p></blockquote>");	
			
		 out.println("<br><br><br><br>");	
		 out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
			
			if(m_client_type.equals("INDIVIDUAL") || m_client_type.equals("SOLEPROPRI") ){
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_full_name+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_add1+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_add2+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_city_name+"</td></tr>");
			out.println("</table>");	
			out.println("<br><br>");
			}
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_Letter_date+"</td></tr>");
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
			out.println("<tr><td width='*%' class='rep-body1' ><b><U>LEASE AGREEMENT NO :&nbsp;"+m_finance_no+"&nbsp;  &nbsp;&nbsp;DATED :&nbsp;"+m_activated_date+"&nbsp; </U></td></tr>");
			out.println("</table>");
			
			out.println("<br><br>");
			
	    out.println("</font></p></blockquote>");
			
		
	
		 out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
		
		  String data="We have noticed some discrepancies in the Identification Numbers of the vehicle bearing "+
			            "<b>Chassis No:&nbsp;"+m_chassis_no+" Engine No:&nbsp;"+m_engine_no+"</b> (Which was leased "+
									"out of us by you under and in terms of the Lease Agreement Schedule No. "+m_finance_no+" dated: "+m_activated_date+" ) "+
									"as against the information recorded in the schedule of the Lease Agreement.";
								
								
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
						
                  
     data=  "The correct information should be recorded as follows:-";
		  
			out.println("<br>");									
			
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			//_____________________________________________________________________________________________
			
			out.println("<br>");									
			if(m_client_type.equals("PARTNERS")){
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr>");
			out.println("<td width='15%' class='rep-body1' style='text-align:justify' >Make & Modle No</td>");
			out.println("<td width='3%' class='rep-body1' style='text-align:justify' >:</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+m_asset_desc+"</td>");
			out.println("</tr>");		
			
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr>");
			out.println("<td width='15%' class='rep-body1' style='text-align:justify' >Engine No</td>");
			out.println("<td width='3%' class='rep-body1' style='text-align:justify' >:</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+m_engine_no_new+"</td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td width='15%' class='rep-body1' style='text-align:justify' >Chassis No</td>");
			out.println("<td width='3%' class='rep-body1' style='text-align:justify' >:</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+m_chassis_no_new+"</td>");
			out.println("</tr>");		
			out.println("</table>");		
			}
			else if(m_client_type.equals("PUBLIC") || m_client_type.equals("LIMITED") ){

		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr>");
			out.println("<td width='15%' class='rep-body1' style='text-align:justify' >Make & Modle No</td>");
			out.println("<td width='3%' class='rep-body1' style='text-align:justify' >:</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+m_asset_desc+"</td>");
			out.println("</tr>");		
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr>");
			out.println("<td width='15%' class='rep-body1' style='text-align:justify' >Engine No</td>");
			out.println("<td width='3%' class='rep-body1' style='text-align:justify' >:</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+m_engine_no_new+"</td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td width='15%' class='rep-body1' style='text-align:justify' >Chassis No</td>");
			out.println("<td width='3%' class='rep-body1' style='text-align:justify' >:</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+m_chassis_no_new+"</td>");
			out.println("</tr>");		
			out.println("</table>");		
			}
			else {
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr>");
			out.println("<td width='15%' class='rep-body1' style='text-align:justify' >Make & Modle No</td>");
			out.println("<td width='3%' class='rep-body1' style='text-align:justify' >:</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+m_asset_desc+"</td>");
			out.println("</tr>");		
			
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr>");
			out.println("<td width='15%' class='rep-body1' style='text-align:justify' >Engine No</td>");
			out.println("<td width='3%' class='rep-body1' style='text-align:justify' >:</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+m_engine_no_new+"</td>");
			out.println("</tr>");		
			
			out.println("<tr>");
			out.println("<td width='15%' class='rep-body1' style='text-align:justify' >Chassis No</td>");
			out.println("<td width='3%' class='rep-body1' style='text-align:justify' >:</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+m_chassis_no_new+"</td>");
			out.println("</tr>");		
			out.println("</table>");		
			}
			//______________________________________________________________________________________		
		 
    
		data="We agree and undertake that notwithstanding the discrepancies as aforesaid the Lease Agreement Schedule No: "+m_finance_no+" "+
		     "shall stand confirmed in all respects and the rentals stipulated there under shall continue to be payable by us.";
			
			out.println("<br>");									
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
								
			data="if the foregoing is acceptable to you, please return copy of this letter duly signed to signify your agreement.";
			
			out.println("<br>");									
			
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
																	
									
		  data="Yours faithfully,";
			
			out.println("<br>");									
			
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			out.println("<br><br>");									
			
			//____________________________________________________________________________________________
			if(m_client_type.equals("PARTNERS")){
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='30%' class='rep-body1' style='text-align:justify' >.................................</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify'     >.................................</td>");
			out.println("</tr></table>");		
			out.println("<br><br>");									
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='30%' class='rep-body1' style='text-align:justify' >Partner</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify'     >Partner</td>");
			out.println("</tr></table>");		
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='30%' class='rep-body1' style='text-align:justify' >(Rubber Stamp)</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify'     >(Rubber Stamp)</td>");
			out.println("</tr></table>");		
			
			}
			else if(m_client_type.equals("PUBLIC") || m_client_type.equals("LIMITED") ){
			
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='30%' class='rep-body1' style='text-align:justify' >.................................</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify'     >.................................</td>");
			out.println("</tr></table>");		
			out.println("<br><br>");									
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='30%' class='rep-body1' style='text-align:justify' >Director</td>");
			out.println("<td width='30%' class='rep-body1' style='text-align:justify'     >Director</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify'     >(Embossed seal)</td>");
			out.println("</tr></table>");		
			
			}
			else {
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='30%' class='rep-body1' style='text-align:justify' >.................................</td>");
			out.println("</tr></table>");		
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='30%' class='rep-body1' style='text-align:justify' >"+m_full_name+"</td>");
			out.println("</tr></table>");		
			out.println("<br><br>");									
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='30%' class='rep-body1' style='text-align:justify' >.................................</td>");
			out.println("</tr></table>");		
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='30%' class='rep-body1' style='text-align:justify' >NIC NO / BUS REG NO</td>");
			out.println("</tr></table>");		
			
			}
			
			 data="We agreed for the above changes.";
			
			out.println("<br>");									
			
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			out.println("<br><br>");									
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='30%' class='rep-body1' style='text-align:justify' >.................................</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify'     >.................................</td>");
			out.println("</tr></table>");		
			out.println("<br><br>");												
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='70%' class='rep-body1' style='text-align:justify' >For "+m_orient_name+"</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify'     >Date: ....................................</td>");
			out.println("</tr></table>");		
			
			//____________________________________________________________________________________________
			  			
    		
		  out.println("</font></p></blockquote>");		
		 
			out.println("   <p style=\"page-break-after:always\"></p>");		
			}
			}
			more=rs.next();

			}
									
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
