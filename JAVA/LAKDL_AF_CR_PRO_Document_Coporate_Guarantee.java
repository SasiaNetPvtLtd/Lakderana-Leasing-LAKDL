//ID         :
//SCREEN NAME:Document Printing - Joint And Several Guarantee
//CREATED BY :Nuwan De Silva	
//DATE/TIME  : 07-02-2006
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Document_Coporate_Guarantee extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt1;
//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
  public ResultSet rs,rs1;
 	

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
			
		// out.println("conn"+conn);
		String m_start_date="";
		String m_status="";
	  int m_data_count=0;
		
		
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
			stmt1 = conn.createStatement ();
						
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
					    " COMPANY_NAME, "+
					    " ADDRESS1, "+
					    " ADDRESS2, "+
					    " CITY, "+
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



				
				
				rs = stmt.executeQuery (	" SELECT "+
  		  " INITCAP(FULL_NAME) "+
  	    " FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
  	    " WHERE CLIENT_CODE='"+m_client_code+"' ");
		
					
			more = rs.next();
			if(more){
			m_name=rs.getString(1);
					}
					
					
			 rs=stmt.executeQuery (" SELECT "+
        "  NVL(FINANCE_NO,'-'), "+
				"  TO_CHAR(ACTIVATED_DATE, 'fmddth') || ' ' || TO_CHAR(ACTIVATED_DATE, 'Month')|| TO_CHAR(ACTIVATED_DATE, 'YYYY') START_DATE, "+
				"	 nvl(MASTER_AGREEMENT_NO,'-') "+
		    " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
        " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
				
				more = rs.next();
			String m_master_lease_no="";
			
			
			if(more){
			  m_finance_no=rs.getString(1);
			  m_start_date=rs.getString(2);
				m_master_lease_no=rs.getString(3);
				
			  }



				
				
				
				
			  out.println("<html><head>"); 
				out.println("<title>Joint And Several Guarantee </title></head>");
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
			out.println("<tr><td width=\"100%\" class='rep-body'><b></b></td></tr>");
		  out.println("</table>");

			out.println("</font></p></blockquote>");	
			
			
				
			out.println("<font size=3><p style='text-align:center'>");				
						
			out.println("<table border='0' width='100%' class='table' align='center'>"); 		
			out.println("<tr><td width='*%' class='rep-body' align='center'><B>GUARANTEE AND INDEMNITY TO THE MASTER FINANCE LEASE </td></tr>");
			out.println("<tr><td width='*%' class='rep-body' align='center'><b>AGREEMENT NO : "+m_master_lease_no+"</td></tr>");
	  	out.println("<tr><td width='*%' class='rep-body' align='center'><b>SCHEDULE NO :"+m_finance_no+"</td></tr>");		  
		  out.println("</table>");
										
			out.println("</font></p></center>");
			
			
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
						
			String data="TO:<b>"+m_orient_name.toUpperCase()+" "+m_orient_add1+", "+m_orient_add2+", "+m_orient_city_name+".</B> and its successor - in-title and assigns.";
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
			 
		
		       data="In consideration of your entering at our request in to the foregoing Master Finance Lease Agreement "+
                "(hereinafter called \"Master Finance Lease Agreement \" the term Master Finance Lease Agreement shall) "+
								"mean and include the said Master Finance Lease Agreement and all addendums and schedules that are to "+
								"be executed subsequently) with the Lessee as mentioned in schedules to the Master Finance Lease "+
								"Agreement (hereinafter called \"the Lessee\" which expression shall mean and include its successors and "+
								"assigns) ";
			
			out.println("<br>");					
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
								
                  
       data=    "I/We, the undersigned (hereinafter some time called and referred to as the Guarantor/s which term or "+
                "expression as herein used shall mean and include the undersigned and under mentioned us/each of us and "+
				        "our respective representatives, heirs executors and administrators) in the said republic.";
					
			out.println("<br>");					
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
			
		data=         "I/We the undersigned jointly and severally do hereby guarantee to you the punctual Payment by the "+
			            "Lessee of all rental, interest, the Stipulated Loss value, referred to in schedules of the Master Finance "+
								  "Lease Payment Agreement and schedules and all other sums whatsoever due under the Master Finance Lease "+
									"Agreement and the performance of all the Lessee's obligations there under and I/We undertake to "+
									"indemnify to you jointly and severally at Colombo on demand against all losses, expenses (including "+
									"legal costs on a full indemnity basis) charges and damages incurred or suffered by you during the "+
									"entirety of the terms/extended term of the Master Finance Lease Agreement in consequence of any "+
									"failure by the Lessee to performed any of one of the Lessee's obligations whether express or implied "+
									"under the Master Finance Lease Agreement and including the payment of any damages and/or costs "+
									"awarded to the Lessor by a competent Court of Law in respect of the said Master Lease "+
									"Agreement.";
			
			out.println("<br>");						
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body' style='text-align:left' valign=top >1.</td>");
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
								
	data      =     "I/We specifically agree that my/our liability under this guarantee and indemnity shall be as principal "+
									"debtors and not merely as sureties and shall be continuing security and shall be irrevocable and my/our "+
									"liability shall not be in any way discharged, diminished or affected by the granting of time or "+
									"indulgence to the Lessee or by the affecting of any compromise with the Lessee or any subsequent "+
									"agreement addendum to any subsequent amendments, additions, substitutions, inclusions not to sue the "+
									"Lessee or any variations of the Master Finance Lease Agreement or any change in the constitution of "+
									"the Lessee and my/our liability hereunder shall subsist whether or not you have a legal right and "+
									"whether or not you have availed yourself of your legal remedies against the Lessee and my/our and "+
									"liability shall also extend to cover any renewal or renewals of the Master Finance Lease Agreement "+
							    "and that this guarantees and indemnity shall not be affected or prejudiced by any other guarantees and "+
									"/or indemnities and any other forms of security now or hereafter held by the Lessor.";
					
			out.println("<br>");						
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body' style='text-align:left' valign=top >2.</td>");
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");						
			
	data=          "I/We further agree that no relaxation, forbearance or indulgence granted by you to us shall affect "+	
		             "my/our liability to you hereunder nor shall any release of or agreement not to sue me/us or liability "+
								 "hereunder and that this Guarantee and indemnity shall bind our heirs, executors and administrators.";
		
	out.println("<br>");	
	out.println("<table border='0' width='90%' class='table'>"); 		
	out.println("<tr><td width='1%' class='rep-body' style='text-align:left' valign=top >3.</td>");
	out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
	out.println("</tr></table>");
			
		data=        "I/We specifically agree that you shall be at liberty either in one action to sue the Lessee and me/us or "+	
		             "each one of us and also any other person or persons all jointly and severally or to proceed in the first "+
								 "instance against us or each one of us only and further that we hereby renounce the right to claim that "+
								 "the Lessee should be excused or proceeded against by action in the first instance and the right to claim "+
								 "that you should divide your claim and bring action against us or each one of us or any other person or "+	
								 "persons whomsoever each pro rata and the right to claim in any action brought against us or each one of us with or without all or any other person that you should only recover from us or each one of us pro "+
								 "rata the amount claimed and all other rights and benefits to which sureties are or may be by law "+
								 "entitled IT BEING AGREED that I/We am/are jointly and severally liable in all respects hereunder as "+
								 "principal debtor to the extent aforementioned including the liability to be sued before resource is had "+
								 "against the Lessee.";
		
		out.println("<br>");
				
		out.println("<table border='0' width='90%' class='table'>"); 		
		out.println("<tr><td width='1%' class='rep-body' style='text-align:left' valign=top >4.</td>");
		out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
		out.println("</tr></table>");
			
		 data       = "It BEING AGREED that I/we and each of us are and is liable in all respects hereunder not merely as surety "+	
								 "or sureties or guarantors but as sole or principal debtor/s.";
		
		out.println("<br>");							
		
		out.println("<table border='0' width='90%' class='table'>"); 		
		out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
		out.println("</tr></table>");
		
		
		 data       ="We and each of expressly agreed and confirm that the arbitration clause in the said agreement is treated as having "+	
								 "been incorporated herein and each of us hereby submit ourselves/my self to the arbitration provisions contained therein unconditionally ";
		
		out.println("<br>");							
		
		out.println("<table border='0' width='90%' class='table'>"); 		
		out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
		out.println("</tr></table>");
		
			out.println("<br>");	
			out.println("<br>");
		
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>Signed on this</td>");
		
		out.println("<td><b>"+m_start_date+" at Colombo");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
    		
		out.println("</font></p></blockquote>");		
		
		out.println("</blockquote><font size=2><p style='text-align:left'>");				
					
				/*rs = stmt.executeQuery (" SELECT A.CLIENT_CODE, "+
  		  " INITCAP(FULL_NAME),(ADDRESS1||','||ADDRESS2) ADDRESS,NIC_NO "+
  	    " FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
  	    " WHERE "+
		//		"UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') "+
				"	UPPER(A.CLIENT_CODE)=UPPER(B.CLIENT_CODE) "+
				" AND TRANSACTION_TYPE <>'HIREPURCH' "+
				"	AND UPPER(B.APPLICATION_NO)=UPPER('"+m_application_no+"') "+
				"	AND A.AF_GUARANTORS='Y' ");
				*/
				rs1 = stmt1.executeQuery ("SELECT A.GUARANTOR_CODE, "+
  		  " INITCAP(c.FULL_NAME),(c.ADDRESS1||','||c.ADDRESS2) ADDRESS,nvl(c.NIC_NO,'-')  "+
  	    " FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B ,"+m_schema_name+".AF_CO_MAS_CLIENT c "+
  	    " WHERE "+
				" UPPER(c.CLIENT_CODE)=UPPER(A.GUARANTOR_CODE)  "+
				" AND C.CLIENT_TYPE = 'I' "+ //Added by Chandana on 21/06/2007 
				//and	UPPER(A.GUARANTOR_CODE)=UPPER(B.CLIENT_CODE) 
        " and upper(a.application_no)=upper(b.application_no) "+
				" AND b.TRANSACTION_TYPE <>'HIREPURCH' "+
				"	AND UPPER(B.APPLICATION_NO)=UPPER('"+m_application_no+"') "+
				" ORDER BY A.GUAR_ID,A.GUARANTOR_CODE ");//A.GUAR_ID  Sandun on 26/05/2009
				
					//AND A.AF_GUARANTORS='Y'
				
		
					
		//more = rs.next();
			String m_guarontor_name="";
			String m_address="";
			String m_nic_no="";
			boolean more1=rs1.next();
			int j=1;
			
			while(more1){
			m_guarontor_name=rs1.getString(2);
			m_address=rs1.getString(3);
			m_nic_no=rs1.getString(4);
			//}
					

					
					
		out.println("<table border='0' width='90%' class='table' align='center'>"); 		
		if(j<=2){
		out.println("<tr><td width='45%' class='rep-body' align='left'><B>WITNESS:</td>");
		}
		else{
		out.println("<tr><td width='45%' class='rep-body' align='left'>&nbsp</td>");
	
		}
		out.println("    <td width='5%'  class='rep-body' align='left'><B>GUARANTOR:</td>");
		out.println("    <td width='*%'  class='rep-body' align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
		out.println("</tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		
		out.println("<tr><br><br>");
		if(j<=2){
		if(j==1){
		out.println("<td width='45%' class='rep-body' align='left'>(A) Signature:...........................</td>");
		}
		if(j==2){
		out.println("<td width='45%' class='rep-body' align='left'>(B) Signature:...........................</td>");
		}
		}
		else{
		out.println("<tr><td width='45%' class='rep-body' align='left'>&nbsp</td>");
	
		}

		out.println("    <td width='5%' class='rep-body' align='left'>Signature "+j+":</td>");
		out.println("    <td width='*%' class='rep-body' align='left'>...........................</td>");
		out.println("</tr>");

		out.println("<tr></tr>");  
		if(j<=2){
		out.println("<tr><td width='45%' class='rep-body' align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Witness</td>");
		}
		else{
		out.println("<tr><td width='45%' class='rep-body' align='left'>&nbsp</td>");
	
		}

		out.println("    <td width='5%' class='rep-body' align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Guarantor</td>");
		out.println("    <td width='*%' class='rep-body' align='left'>&nbsp</td>");
		
		out.println("<tr></tr>");
		out.println("<tr>");
		if(j<=2){
		out.println("<td width='45%' class='rep-body' align='left'>Full Name:</td>");
		}
		else{
		out.println("<tr><td width='45%' class='rep-body' align='left'>&nbsp</td>");
		}
		out.println("    <td width='5%' class='rep-body' align='left'>Full Name:</td>");
		
		out.println("    <td width='*%' class='rep-body' align='left'><b>"+rs1.getString(2)+"</td>");
  	out.println("</tr>");

		out.println("<tr></tr>");
		out.println("<tr>");
		
		if(j<=2){
		out.println("<td width='45%' class='rep-body' align='left'>Address:</td>");
		}
		else{
		out.println("<tr><td width='45%' class='rep-body' align='left'>&nbsp</td>");
		}
		
		out.println("    <td width='5%' class='rep-body' align='left'>Address:</td>");
		
		if(!rs1.getString(3).equals(",")){
		out.println("    <td width='*%' class='rep-body' align='left'><b>"+rs1.getString(3)+"</td>");
		}
		else {
		out.println("    <td width='*%' class='rep-body' align='left'><b></td>");
		}
		
  	out.println("</tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr>");
		
		if(j<=2){

		out.println("<td width='45%' class='rep-body' align='left'>NIC No:</td>");
		}
				else{
		out.println("<tr><td width='45%' class='rep-body' align='left'>&nbsp</td>");
		}

		out.println("    <td width='5%' class='rep-body' align='left'>NIC No:</td>");
		out.println("    <td width='*%' class='rep-body' align='left'><b>"+rs1.getString(4)+"</td>");
  	out.println("</tr>");
		
		//out.println("<tr><td width='45%' class='rep-body' align='left'>(B) Signature:...........................</td>");
		//out.println("    <td width='5%' class='rep-body' align='left'>Signature "+j+":</td>");
   	//out.println("    <td width='*%' class='rep-body' align='left'>...........................</td>");
		//out.println("</tr>");
		//more1 = rs1.next();	
		//}
		//if(more1){
	
		/*
		out.println("<tr></tr>");
		
		out.println("<tr><td width='45%' class='rep-body' align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Witness</td>");
		out.println("    <td width='5%' class='rep-body' align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Guarantor</td>");
		
	 	out.println("    <td width='*%' class='rep-body' align='left'>&nbsp</td>");
		out.println("</tr>");
		out.println("<tr></tr>");
		out.println("<tr><td width='45%' class='rep-body' align='left'>Full Name:</td>");
		out.println("    <td width='5%' class='rep-body' align='left'>Full Name:</td>");
		out.println("    <td width='*%' class='rep-body' align='left'>"+rs1.getString(2)+"</td>");
		out.println("</tr>");
		out.println("<tr></tr>");
		out.println("<tr><td width='45%' class='rep-body' align='left'>Address:</td>");
		out.println("    <td width='5%' class='rep-body' align='left'>Address:</td>");
		out.println("    <td width='*%' class='rep-body' align='left'>"+rs1.getString(3)+"</td>");
		out.println("</tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr><td width='45%' class='rep-body' align='left'>NIC No:</td>");
		out.println("    <td width='5%' class='rep-body' align='left'>NIC No:</td>");
		out.println("    <td width='*%' class='rep-body' align='left'>"+rs1.getString(4)+"</td>");
		out.println("</tr>");

		*/
		//	}
	 	more1 = rs1.next();
		j=j+1;
					
					
		}
		
		if(j==2){
		
		out.println("<tr><td width='45%' class='rep-body' align='left'>(B) Signature:...........................</td>");
		out.println("    <td width='45%' class='rep-body' align='left'>&nbsp</td>");
    out.println("<tr></tr>");
		out.println("<tr><td width='45%' class='rep-body' align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Witness</td>");
		out.println("    <td width='45%' class='rep-body' align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
		out.println("<tr></tr>");
		out.println("<tr><td width='45%' class='rep-body' align='left'>Full Name:</td>");
		out.println("    <td width='45%' class='rep-body' align='left'>&nbsp</td>");
		out.println("<tr></tr>");
		out.println("<tr><td width='45%' class='rep-body' align='left'>Address:</td>");
		out.println("    <td width='45%' class='rep-body' align='left'>&nbsp</td>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr><td width='45%' class='rep-body' align='left'>NIC No:</td>");
		out.println("    <td width='45%' class='rep-body' align='left'>&nbsp</td>");
		out.println("</tr>");

		}
	if(m_guarontor_name.equals("")){
		out.println("<table border='0' width='90%' class='table' align='center'>"); 		
		
		out.println("<tr><td width='45%' class='rep-body' align='left'><B>WITNESS:</td>");
		out.println("    <td width='45%' class='rep-body' align='left'><B>GUARANTOR:</td>");
		
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		
		out.println("<tr><br><br><td width='45%' class='rep-body' align='left'>(A) Signature:...........................</td>");
		out.println("    <td width='45%' class='rep-body' align='left'>Signature 1: ...........................</td>");
    out.println("<tr></tr>");   
		out.println("<tr><td width='45%' class='rep-body' align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Witness</td>");
		out.println("    <td width='45%' class='rep-body' align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Guarantor</td>");
		out.println("<tr></tr>");
		out.println("<tr><td width='45%' class='rep-body' align='left'>Full Name:</td>");
		out.println("    <td width='45%' class='rep-body' align='left'>Full Name:</td>");
		out.println("<tr></tr>");
		out.println("<tr><td width='45%' class='rep-body' align='left'>Address:</td>");
		out.println("    <td width='45%' class='rep-body' align='left'>Address:</td>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		
		out.println("<tr><td width='45%' class='rep-body' align='left'>NIC No:</td>");
		out.println("    <td width='45%' class='rep-body' align='left'>NIC No:</td>");
		
		
		
		out.println("<tr><td width='45%' class='rep-body' align='left'>(B) Signature:...........................</td>");
		out.println("    <td width='45%' class='rep-body' align='left'>Signature 2: ...........................</td>");
    out.println("<tr></tr>");
		out.println("<tr><td width='45%' class='rep-body' align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Witness</td>");
		out.println("    <td width='45%' class='rep-body' align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Guarantor</td>");
		out.println("<tr></tr>");
		out.println("<tr><td width='45%' class='rep-body' align='left'>Full Name:</td>");
		out.println("    <td width='45%' class='rep-body' align='left'>Full Name:</td>");
		out.println("<tr></tr>");
		out.println("<tr><td width='45%' class='rep-body' align='left'>Address:</td>");
		out.println("    <td width='45%' class='rep-body' align='left'>Address:</td>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr><td width='45%' class='rep-body' align='left'>NIC No:</td>");
		out.println("    <td width='45%' class='rep-body' align='left'>NIC No:</td>");
		out.println("</tr>");

		}
		
		
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
