//ID         :
//SCREEN NAME:Document Printing - Hire Purechase Board Resulution
//CREATED BY :Chandana 	
//DATE/TIME  : 08-06-2007
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Document_Hirepur_Board_resulotion extends javax.servlet.http.HttpServlet { 

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
			
		// out.println("conn"+conn);
		
		
				//Decaring variables
			
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
			
			
			
						
		 String m_chksql = req.getParameter("chksql");
			
  	 if(m_chksql.trim().equals("main_page")){
			
			  
				
			stmt = conn.createStatement ();
			stmt_partner = conn.createStatement ();
			
						
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
				
		
					
					
					  rs = stmt.executeQuery(" SELECT "+
        												" NVL(TITLE,''), "+
																" NVL(FULL_NAME,' '), "+
																" REPLACE(REPLACE(NVL(ADDRESS1,' '),'-',' '),'null',' '),"+
																" REPLACE(REPLACE(NVL(ADDRESS2,' '),'-',' '),'null',' '),"+
                                " "+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE) "+
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
        "  NVL(FINANCE_NO,'-') "+
		    " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
        " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
				
				more = rs.next();
			
			if(more){
			  m_finance_no=rs.getString(1);
			 
			  }
											 
					
				 String sql=" SELECT "+
         " NAME "+
         " FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DIRECTORS "+
         " WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') ";


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
			out.println("<tr><td width=\"100%\" class='rep-body'><b>"+m_status+"</b></td></tr>");
		  out.println("</table>");

			out.println("</font></p></blockquote>");	
			
			
		out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' ><b>Date: "+m_Letter_date+"</td></tr>");
			out.println("</table>");
			out.println("<br><br>");
												
			out.println("<br><br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' ><b>"+m_orient_name+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body' ><b>"+m_orient_add1+ "," +m_orient_add2+ "," +"</td></tr>");
			//out.println("<tr><td width='*%' class='rep-body' ><b>"+m_orient_add2+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body' ><b>"+m_orient_city_name+".</td></tr>");
			out.println("</table>");	
			
			out.println("<br><br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' ><b>Dear Sirs,</td></tr>");
			out.println("</table>");
						
			out.println("<br><br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' ><b><u>LEASE AGREEMENT NO: "+m_finance_no+"</u></td></tr>");
			out.println("</table>");
			
	    out.println("</font></p></blockquote>");
			
		
	
		out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
		
				
		rs_partner=stmt_partner.executeQuery (sql);
		int count=0;
		int i=0;
				
		while(rs_partner.next()){
		count=count+1;
		}
		
		m_partner_name = new String[count];
		
		rs_partner=stmt_partner.executeQuery (sql);
		rs_partner.next();
		while(i<count){
		m_partner_name[i]=rs_partner.getString(1);
		i=i+1;
		rs_partner.next();
		}
		
		
		
		//out.println("<br><br>We, ");
    
		rs_partner=stmt_partner.executeQuery (sql);
		
	/*	while(rs_partner.next()){
		i=i+1;
		if(count==1)
		{
		out.print(""+rs_partner.getString(1)+" ");
		break;
		}
		else if(i>1 && count==i ){
		out.print(" and "+rs_partner.getString(1)+" ");
		break;
		}
		else 
		{
		out.print(""+rs_partner.getString(1)+ ","+ " ");
		}
				
		}*/
		
		String m_contact_people="";
		int m_pos=count-1;
		int j=1;
		i=0;
		while(i<count){
					
		if(j==m_pos)		{
		m_contact_people=m_contact_people+m_partner_name[i]+" and"+" ";
		}
		else if(i==m_pos){
		m_contact_people=m_contact_people+m_partner_name[i];
		}
		
		else{
		m_contact_people=m_contact_people+m_partner_name[i]+","+" ";
		}
		
		i=i+1;
		j=j+1;
		}
		
		
		
		
		
		    String   data="We, "+m_contact_people+" being Partners of the business carried on under the name style and firm of "+m_add1+" "+m_add2+" " +m_city_name+"  "+
		            "Sri Lanka hereby confirm ratify and adopt the "+ 
								"Lease Agreement No. "+m_finance_no+" dated "+m_start_date+" with your Company executed by our Co- "+
								"partner/s...................................................................................................................................................... and "+ 
								"............................................................................................................................................................. ";
      
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
								
		       data="for and behalf of the partnership a copy of which seen by us is enclosed gerewith duly initiated "+
								"by us for purposes of identification.";
			
			out.println("<br>");
			
	    out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			
		       data=  "We further confirm, agree and undertake that we shall, notwithstanding our not joining in the "+
			            "execution of the Lease Agreement No. "+m_finance_no+" be fully responsible and entirely liable in "+
								  "respect of all the obligations on the part of Lease contained therein and shall ensure that the terms "+
									"and conditions of the said Lease Agreement No. "+m_finance_no+" are carried out in full at all times "+
									"without any exception.";
		out.println("<br>");
		
		out.println("<table border='0' width='90%' class='table'>"); 		
		out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
		out.println("</tr></table>");		
			
		       data=  "Whilst we shall indemnify and keep you indemnified from and against all losses and expenses "+
			            "sustained or incurred by you in this connection we confirm our willingness to execute any further "+
								  "documentation including a personal guarantee which you may require from us. ";
									
									
											
		out.println("<br><br>Yours faithfully,");
		
		out.println("<br><br>");
		
		out.println("<table border='0' width='80%' class='table'>"); 		
		out.println("<tr><td width='40%' class='rep-body' ><u>Signature of Partners</td>");
		out.println("<td width='40%' class='rep-body' ><u>Name</td></tr>");
		
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		
		
		rs_partner=stmt_partner.executeQuery (sql);		
		
		
		while(rs_partner.next()){
				
				
		out.println("<tr><td width='40%' class='rep-body' >..................................</td>");
		out.println("    <td width='40%' class='rep-body' >"+rs_partner.getString(1)+"</td></tr>");
		
		out.println("<tr></tr>");
		
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
