//ID         :
//SCREEN NAME:Document Printing - Board Resolution
//CREATED BY :Chandana 	
//DATE/TIME  : 08-06-2007
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Document_Hirepur_Board_Resolution extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt;
//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
  public ResultSet rs;
 	

	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print,m_Letter_year="";
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
			int m_data_count=0;
		  String m_status ="";
			
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			
			String m_orient_init_name="";
		  String m_orient_init_add1="";
			String m_orient_init_add2="";
			String m_orient_init_city_name="";
			String m_asset_desc = "";
    			
			
						
		 String m_chksql = req.getParameter("chksql");
			
  	 if(m_chksql.trim().equals("main_page")){
			
			  
				
			stmt = conn.createStatement ();
						
			String m_application_no = req.getParameter("application_no");
			String m_client_code	  =req.getParameter("client_code");		
			String m_document_code	=req.getParameter("document_code");	
			String m_print=req.getParameter("print");
			
			
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YY'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
								
				boolean more = rs.next();
				if(more){
				   m_Letter_date=rs.getString(1);
					 m_Letter_year=rs.getString(2);
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
				   }else{
				   m_status="COPY";
				   }
					}else{
			    m_status=req.getParameter("status");
			    }
			
			  rs = stmt.executeQuery(" SELECT "+
					                     " UPPER(COMPANY_NAME), "+
					                     " nvl(UPPER(ADDRESS1),''), "+
					                     " nvl(UPPER(ADDRESS2),''), "+
					                     " nvl(UPPER(CITY),''), "+
					                     " TEL_NO, "+
					                     " FAX_NO,  "+
							                 " VAT_RATE, "+
															 " INITCAP(COMPANY_NAME), "+
															 " INITCAP(ADDRESS1), "+
															 " INITCAP(ADDRESS2), "+	
															 " INITCAP(CITY) "+	
							                 " FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");

							  more = rs.next();		
											
					if(more){
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
											
				
				 rs = stmt.executeQuery(" SELECT "+
        												" NVL(UPPER(TITLE),''), "+
																" NVL(UPPER(FULL_NAME),' '), "+
																" REPLACE(REPLACE(NVL(UPPER(REGISTERED_ADDRESS1),' '),'-',' '),'NULL',' '),"+
																" REPLACE(REPLACE(NVL(UPPER(REGISTERED_ADDRESS2),' '),'-',' '),'NULL',' '),"+
                               // " UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(REGISTERED_CITY_CODE)) "+
																 " UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)) "+
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
			/*	if(!m_full_name.equals(" "))
				{
				m_full_name=m_full_name+",";
				}
				*/
				if(!m_add1.equals(" "))
				{
				m_add1=m_add1+",";
				}
			
			
				//if(!m_add2.equals(" "))
				//{
				//m_add2=m_add2+",";
				//}	
			//---------------------------------------------------------	

				if(!m_add2.equals(" ") && !m_city_name.equals("")){
				   m_add2=m_add2+",";
				   }else{
				   m_add2=m_add2;
				   }
						
				 rs=stmt.executeQuery (" SELECT "+
                               " NVL(FINANCE_NO,'-'), "+
																" "+m_schema_name+".AF_CO_GET_ASSET_UNITS('"+m_application_no+"') "+
		                           " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
                               " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
				
				       more = rs.next();
			
			    if(more){
			       m_finance_no=rs.getString(1);
							m_asset_desc=rs.getString(2);
			 		  }

								
				
			  out.println("<html><head>"); 
				out.println("<title>Board Resolution </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  out.println("<script>");
			
			  out.println("function save_data(){");
			  out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";");			
			  out.println(" window.location.href=m_url;"); 
			  out.println("m_table.innerHTML=\"\" ");
				out.println("if(document.Form1.hid_client_type.value=='CO'){");
				out.println("m_letter='<tr><td width=\"*%\" align=\"center\" class=\"rep-body1\" ><b>To be typed on company letter head</b></td></tr>';"); 
				out.println("		}else{");
				out.println("m_letter=\"\" ");
				out.println("		}");
				out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
				out.println("m_letter+'</table>';");
			
				out.println("window.print();");
				out.println("}");
				
				// Added by Chandana on 12/07/07 for Rerf No.540	
		  out.println("function makeRequest() {");
			out.println(" document.Form1.hid_status.value ='H1';");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_chk_get_client_type&data_val="+m_client_code+" \";");
		  out.println("load_interface(m_url,'XML');");
			out.println("}");
		
		  out.println("function get_vector(data_vec) {");
			out.println("	if(data_vec.length>0 && document.Form1.hid_status.value == 'H1' ){");
			out.println(" document.Form1.hid_client_type.value=data_vec[1];");
			out.println("	}");
			out.println(" add_button();");
			out.println("		}");
			// End on 12/07/07	
			
			/*  out.println("function add_button(){");
				if (m_print.trim().equals("FALSE")) {
			  out.println("m_table.innerHTML=\"\" ");
			  }else{
			  out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
				out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	  out.println("m_writedata+'</table>';");
			  }
				out.println("}");
				*/
				
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
				
			//New Letter format added by Chandana on 05-09-2007		
      //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX		
				
				/*	  out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"makeRequest()\">");//add_button()
				//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
				out.println("<body bgcolor='white'><br>");
				out.println("<form name='Form1'>");
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">");
	   		out.println("<INPUT TYPE='Hidden' NAME='hid_client_type' VALUE=\"\">");
 
									
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
			
				out.println("<br><br>");
			  out.println("<br><br>");
			  out.println("<br><br>");
			
			  out.println("<font size=2><p style='text-align:center' class='rep-body'>");	
			 	
				out.println("<table border='0' width='90%' class='table'>"); 		
			  out.println("<tr><td width='*%' class='rep-body' align='right' ><b><U>BOARD RESOLUTION</U></b></td></tr>");
			  out.println("</table>");	
				out.println("<br><br>");
				
				out.println("<table border='0' width='90%' class='table'>"); 		
			  out.println("<tr><td width='*%' class='rep-body' align='center' ><b>EXTRACT OF THE MINUTES OF THE BOARD MEETING OF </td></tr>");
			  out.println("<tr><td width='*%' class='rep-body' align='center' ><b><I>"+m_full_name+"</I></b></td></tr>");
			  out.println("<tr><td width='*%' class='rep-body' align='center' ><b><I>duly registered under Companies laws of Sri Lanka</I></b></td></tr>");
				out.println("<tr><td width='*%' class='rep-body' align='center' ><b>AT THE REGISTERED OFFICE OF THE COMPANY</b></td></tr>");
				out.println("<tr><td width='*%' class='rep-body' align='center' ><b><I>"+m_add1+" "+m_add2+"</I></b></td></tr>");
				out.println("<tr><td width='*%' class='rep-body' align='center' ><b><I>"+m_city_name+"</I></b></td></tr>");
				
				
				out.println("</table>");
				out.println("</font>");
			
	      out.println("<br><br>");
		 	
		    out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
					
			 String data="It is a hereby resolved that as it has been agreed with the "+m_orient_init_name+" having its registered office at "+
				           "No.100, Hyde Park Corner, Colombo 02 and the principle place of the business at "+m_orient_init_add1+", "+m_orient_init_add2+", " +m_orient_init_city_name+" "+
									 "carrying on business that the company do enter into the Lease purchase agreement with "+m_orient_init_name+" "+
									 "as per the draft Lease purchase Agreement tabled and approved.";
									
	
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body' style='text-align:left' valign=top ></td>");
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
			
			out.println("<br>");
			
			 data=" It was further RESOLVED that";
		
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%'></td><td width='*%' class='rep-body' style='text-align:left' >"+data+"</td>");
			out.println("</tr></table>");
			out.println("</font></p></blockquote>");	
	    			
			out.println("<blockquote><font size=2><p style='text-align:left' class='rep-body'>");
		
		 out.println("<table border='0' width='90%' class='table'>"); 		
		 out.println("<tr><td width='1%' class='rep-body'  >&nbsp</td><td width='1%' class='rep-body'  ><li></td>");
		 out.println("<td width='80%' class='rep-body'  >any two directors of the company</td></tr>");
     
		 out.println("<tr><td width='5%' class='rep-body'  >&nbsp</td><td width='5%' class='rep-body'  ><li></td>");
		 out.println("<td width='80%' class='rep-body'  >any one director and secretary of the company</td></tr>");	

     out.println("<tr><td width='5%' class='rep-body'  >&nbsp</td><td width='5%' class='rep-body'  ><li></td>");
		 out.println("<td width='80%' class='rep-body'  >A director of the company</td></tr>");
 
     data = "are/ is hereby authorized and empowered place the common seal and/ or to sign and execute the Lease purchase "+
			      "agreement and all document pertaining to the said agreement";
	
     out.println("<tr><td width='5%' class='rep-body'  >&nbsp</td><td width='5%' class='rep-body'  ><li></td>");
		 out.println("<td width='80%' class='rep-body'  >"+data+"</td></tr>");

     out.println("</table>");
		 out.println("</font></p></blockquote>");		
		 out.println("<br><br>");	
		 out.println("<br><br>");	
			
		 out.println("<blockquote><font size=2><p style='text-align:left' class='rep-body'>");
		 out.println("<table border='0' width='90%' class='table'>"); 	
		  
		 out.println("<tr><td width='1%'></td><td width='65%' align='left' class='rep-body' >............................................</td>");
		 out.println("    <td width='24%' align='left' class='rep-body' >...........................................</td></tr>");
		 out.println("<tr><td width='1%'></td><td width='65%' align='left' class='rep-body' >Director</td>");
		 out.println("    <td width='24%' align='left' class='rep-body' >Director/Secretary</td></tr>");
			
		 out.println("</table>");
		 out.println("</font></p></blockquote>");		
		 
		 out.println("<br><br>");	
		 out.println("<blockquote><font size=2><p style='text-align:left' class='rep-body'>");
		 out.println("<table border='0' width='90%' class='table'>"); 	
		  
		 out.println("<tr><td width='1%'></td><td width='40%' align='left' class='rep-body' >Certified to be a true</td>");
		 out.println("    <td width='40%' align='left' class='rep-body' >&nbsp</td></tr>");	
		 out.println("<tr><td width='1%'></td><td width='40%' align='left' class='rep-body' >copy of the Resolution</td>");
		 out.println("    <td width='40%' align='left' class='rep-body' >&nbsp</td></tr>");	
		 out.println("<tr><td width='1%'></td><td width='40%' align='left' class='rep-body' >passed by board of Director</td>");
		 out.println("    <td width='40%' align='left' class='rep-body' >&nbsp</td></tr>");	
		 out.println("<tr><td width='1%'></td><td width='40%' align='left' class='rep-body' >of the Company</td>");
		 out.println("    <td width='40%' align='left' class='rep-body' >&nbsp</td></tr>");	
	
     out.println("</table>");
		 out.println("</font></p></blockquote>");			
	   out.println("<br><br>");
		 out.println("<blockquote><font size=2><p style='text-align:left' class='rep-body'>");
		 out.println("<table border='0' width='90%' class='table'>"); 	
		 //out.println("<tr><td width='1%'></td><td width='*%' align='left' class='rep-body' >On this ....................................................................... day of ................................. 2007. </td>");
		 out.println("<tr><td width='1%'></td><td width='*%' align='left' class='rep-body' >On this ....................................................................... day of ................................. "+m_Letter_year+". </td>");
			
		 out.println("</tr>");	
     out.println("</table>");
		 out.println("</font></p></blockquote>");	
		 		 
		 	 	
		 out.println("<table></table>");	
		
		out.println("</form>");
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>");
		out.println("</body></html>");

			*/	
				
				
		//Comment by Chandana on 05-09-2007		
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX				
				
			  out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"makeRequest()\">");//add_button()
				//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
				out.println("<body bgcolor='white'><br>");
				out.println("<form name='Form1'>");
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">");
	   		out.println("<INPUT TYPE='Hidden' NAME='hid_client_type' VALUE=\"\">");
 
										
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
			
				out.println("<br><br>");
			 
			
			  out.println("<font size=2><p style='text-align:center' class='rep-body'>");	
			 	out.println("<table border='0' width='90%' class='table'>"); 		
			  out.println("<tr><td width='*%' class='rep-body' align='center' ><b>Resolution passed by the Board of Directors of </td></tr>");
			  out.println("<tr><td width='*%' class='rep-body' align='center' ><b>"+m_full_name+"</td></tr>");
			  out.println("</table>");
				out.println("</font>");
			
	      out.println("<br><br>");
		 	
		    out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
				out.println("<table border='0' width='90%' class='table'>"); 		
			  out.println("<tr><td width='*%' class='rep-body' ><B>WHEREAS</B></td></tr>");
			  out.println("</table>"); 	
					
			 /*String data="<B>"+m_full_name+"</B> a company duly incorporated / reregistered under "+
								   "the companies act No. 7 of 2007 and having its registered office at <b>"+m_add1+" "+m_add2+" " +m_city_name+"</b> "+
								   "in the Democratic Socialist Republic of Sri "+
								   "Lanka (hereinafter referred to as \"The Company\") has made arrangements with "+
								   "the "+m_orient_name.toUpperCase()+" a company duly "+
								   "reregistered  under the companies act No. 7 of 2007 and having its registered "+
								 	 "office at NO: 100 Hyde Park Corner , Colombo 02. in the Democratic Socialist Republic of "+
								   "Sri Lanka (hereinafter referred to as \"the "+m_schema_name+"\") to obtain a Lease Purchase "+
								   "on the terms and conditions stipulated in the Lease Purchase agreement of hire executed "+
								   "by the company and the "+m_schema_name+"";
       */										
			 String data=	"<B>"+m_full_name+"</B> a company duly incorporated / reregistered under the companies act No. 7 of 2007 and having its registered office at <b>"+m_add1+" "+m_add2+" " +m_city_name+"</b> "+
										" in the Democratic Socialist Republic of Sri Lanka  (hereinafter referred to as \"the Company\")"+
										"has made arrangements with the "+m_orient_name.toUpperCase()+" a company duly "+
										"reregistered under the companies act No. 7 of 2007 and having its registered office at No: 75 Arnold Ratnayake Mawatha , Colombo 10."+
										"in the Democratic Socialist Republic of Sri Lanka  (hereinafter referred to as \"the "+m_schema_name+"\") "+
										"to obtain a Lease Purchase  on the terms and conditions stipulated in the Lease Purchase  agreement  "+
										"of hire executed by the company and the "+m_schema_name+""; 
								
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body' style='text-align:left' valign=top >1.</td>");
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
			
			out.println("<br>");
			

		 // data= "The "+m_schema_name+" agreed to make available to the hirer's the Hire Purchase facility/Lease Purchase Facility and the company has agreed to take on Hire Purchase/Lease Purchase <b>"+m_asset_desc+"</b> subject to the terms and conditions setout in the said Hire Purchase Agreement/Lease Purchase Agreement. ";
			
			data="The "+m_schema_name+" agreed to make available to the lease purchaser's the Lease Purchase facility and the company has agreed to take on Hire <b>"+m_asset_desc+"</b> subject to the terms and conditions setout in the said Lease Purchase Agreement."; 
		
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='1%' class='rep-body' style='text-align:left' valign=top >2.</td>");
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
			out.println("</font></p></blockquote>");	
	            
			out.println("<blockquote><p style='text-align:justify' class='rep-body'>");	
		
		 /* data=" AND IT IS HEREBY RESOLVED that the company do execute a Hire Purchase Agreement/Lease Purchase Agreement "+
			   		"indenture and such other documents in favor of "+m_schema_name+" in the form acceptable to "+
						""+m_schema_name+" for the repayment of said Hire Purchase Agreement/Lease Purchase Agreement facility in accordance with the "+
						"terms and conditions stipulated in Hire Purchase Agreement/Lease Purchase Agreement as stated above "+
						"and that the common seal of the company be affixed to such number of "+
						"endorsement of the said Hire Purchase Agreement/Lease Purchase Agreement as may be required by "+
						""+m_schema_name+" and such sealing be attested by any two Directors of the company.";
      */
			
			data="AND IT IS HEREBY RESOLVED that the Company do execute a Lease Purchase Agreement "+
			     "indenture and such other documents in favour of "+m_schema_name+" in the form acceptable to "+
					 ""+m_schema_name+" for the repayment of said Lease Purchase Agreement facility in accordance with the "+
					 "terms and conditions stipulated in Lease Purchase Agreement as stated above and that the common seal of the company be affixed to such number of endorsement of the said Lease Purchase Agreement as may be required by "+m_schema_name+" and such sealing be attested by any two Directors or Director and the Secretary  of the Company.";
		  out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");
								
		 out.println("<br><br>");
		
		 out.println("<table border='0' width='90%' class='table'>"); 		
		 out.println("<tr><td width='40%' class='rep-body'  >Name</td>");
		 out.println("    <td width='20%' class='rep-body'  >&nbsp;</td>");
		 out.println("    <td width='40%' class='rep-body'  >Signature</td></tr>");
		 out.println("</table>");
		 out.println("<br><br>");
		 out.println("<table border='0' width='90%' class='table'>"); 		
		 out.println("<tr><td width='40%' class='rep-body' >1.....................................................</td>");
		 out.println("<td width='10%' class='rep-body'  >&nbsp;</td>");
		 out.println("<td width='40%' class='rep-body' >.....................................................</td></tr>");
		 out.println("</table>");
			
		 out.println("<br><br>");
			
     out.println("<table border='0' width='90%' class='table'>"); 		
		 out.println("<tr><td width='40%' class='rep-body' >2.....................................................</td>");
		 out.println("<td width='10%' class='rep-body'  >&nbsp;</td>");
		 out.println("<td width='40%' class='rep-body' >.....................................................</td></tr>");
  	 out.println("</table>");
			
		 out.println("<br><br>");   		
			
		 out.println("<table border='0' width='90%' class='table'>"); 		
		 out.println("<tr><td width='*%' class='rep-body'  >(on this ..................................................day of ..................................................200....)</td>");
		 out.println("</table>");
		 out.println("</font></p></blockquote>");		
													
		out.println("</form>");
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>");
		out.println("</body></html>");
		
		
		
		
		
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
