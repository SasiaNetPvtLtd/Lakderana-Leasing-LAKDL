//ID         :
//SCREEN NAME:Document Printing - Stamp Duty Letter
//CREATED BY :Chandana 	
//DATE/TIME  : 07-06-2007
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Document_Hirepur_stamp_duty extends javax.servlet.http.HttpServlet { 

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
			String m_nic_no=""; 
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
			
			
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY') FROM DUAL ");
								
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
				
				 rs.close();
				 stmt.close(); 
				
				
				if(m_data_count==0){
				    m_status="ORIGINAL";
				    }else{
				    m_status="COPY";
				    }
					 }else{
			     m_status=req.getParameter("status");
			   }
			
			
			  stmt = conn.createStatement ();					
				rs = stmt.executeQuery(" SELECT "+
				                       " UPPER(COMPANY_NAME), "+
					                     " NVL(UPPER(ADDRESS1),''), "+
					                     " NVL(UPPER(ADDRESS2),''), "+
					                     " NVL(UPPER(CITY),''), "+
					                     " NVL(TEL_NO,''), "+
					                     " NVL(FAX_NO,''),  "+
							                 " VAT_RATE "+
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
											   }
													
			  
													
													
		     rs.close();
				 stmt.close(); 
					
			//added by nuwan de silva on 05-09-07		
			String Client_Data=" SELECT  "+
			" 'CLIENT', "+ //1
			" NVL(DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C',/*'MESS' || '. '  || */UPPER(FULL_NAME)),' ') ,   "+ //2
			" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS1),'C',UPPER(REGISTERED_ADDRESS1)),' '),  "+ //3
			" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS2),'C',UPPER(REGISTERED_ADDRESS2)),' ') , "+ //4
			" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //5
			" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+ //6
			" NVL(DECODE(CLIENT_TYPE,'I',UPPER(FULL_NAME),'C',UPPER(FULL_NAME)),' ') ,   "+ //2
			" NVL(DECODE(CLIENT_TYPE,'I','NIC No','C','REG No'),' ') C_TYPE ,   "+ //2
			" CLIENT_TYPE "+
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
			" WHERE   CLIENT_CODE =  "+
			" (SELECT  "+
			" CLIENT_CODE "+ 
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
			" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"')) "+
			
			" UNION "+
			
			" SELECT  "+
			" 'CO-APPLICANT', "+
			" DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C',UPPER(FULL_NAME)),   "+
			" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS1),'C',UPPER(ADDRESS1)),' '),  "+ //REGISTERED_ADDRESS1
			" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS2),'C',UPPER(ADDRESS1)),' ') , "+ //REGISTERED_ADDRESS1
			" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+
			" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+
			" NVL(DECODE(CLIENT_TYPE,'I',UPPER(FULL_NAME),'C',UPPER(FULL_NAME)),' '),    "+ //2
			" NVL(DECODE(CLIENT_TYPE,'I','NIC No','C','REG No'),' ') C_TYPE ,   "+ //2
			" CLIENT_TYPE "+
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
			" WHERE   CLIENT_CODE =  "+
			" (SELECT  "+
		  " CO_APPLICANT  "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
			" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"')) ";
					
				/* stmt = conn.createStatement ();	
				 rs = stmt.executeQuery(" SELECT "+
        												" NVL(UPPER(TITLE),''), "+
			                          //" NVL(UPPER(FULL_NAME),' '), "+
																" DECODE(CLIENT_TYPE,'I',NVL(UPPER(TITLE),' ')||' '||NVL(UPPER(FULL_NAME),' '),'C',NVL(UPPER(FULL_NAME),' ')), "+ //MODIFIED BY Chandana on 26/07/2007 For Ref No.748
																" REPLACE(REPLACE(NVL(UPPER(ADDRESS1),' '),'-',' '),'null',' '),"+
																" REPLACE(REPLACE(NVL(UPPER(ADDRESS2),' '),'-',' '),'null',' '),"+
                                " UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)), "+
																" NVL(DECODE(CLIENT_TYPE,'C',BUSINESS_CERTIFICATE_NO,'I',NIC_NO),' '), "+
																" CLIENT_TYPE "+
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
				         m_nic_no=rs.getString(6);
								 m_client_type=rs.getString(7);
							   }
				 rs.close();
				 stmt.close(); 
         */
					
				 stmt = conn.createStatement ();	
				 rs=stmt.executeQuery ( " SELECT "+
                                " NVL(FINANCE_NO,'-'),NVL(MASTER_AGREEMENT_NO,'-'), "+
															  " TO_CHAR(AGREEMENT_DATE,'DD-MM-YYYY'), "+
																"  NVL(TER_TYPE,'-'), "+//
				                        "  NVL(TO_CHAR(TER_TYPE_ENT_DATE,'DD-MM-YYYY'),'-') "+
		                            " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
                                " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
				
				           more = rs.next();
			
		          if(more){
						    	
			            m_finance_no =rs.getString(1);
				          m_mlease_no=rs.getString(2);
								 	m_Letter_date=rs.getString(3);
								 }
				
				rs=stmt.executeQuery (" SELECT "+
				                      " NIBSM "+
				                      " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
                              " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
        				more = rs.next();
				
				       if(more){
			            m_NIBSM=rs.getDouble(1);
			            }
			
	
							
			  out.println("<html><head>"); 
				out.println("<title>Stamp Duty letter </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
        out.println("<script>");
			
  		 out.println("function save_data(){");
       out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";");			
			//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Hirepur_stamp_duty?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&document_code="+m_document_code+"&print=FALSE\";"); 
		  //LAKDL_AF_CR_PRO_Document_Hirepur_stamp_duty?chksql=main_page&application_no=AP20070608-0723&document_code=NIBSM_LETT&print=TRUE&client_code=0000000366
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
			
		 //comment by nuwan de silva on 11-10-07---------------------
		  /* out.println("function add_button(){");
				if (m_print.trim().equals("FALSE")) {
			 out.println("m_table.innerHTML=\"\" ");
			  }else{
			 out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			 out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	 out.println("m_writedata+'</table>';");
			 }
			 out.println("}");
				*/
				
			///MODIFIED BY NUWAN DE SILVA 10-07-07--------------------
			/*out.println("function add_button(){");
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
			}
			else
			{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			out.println("m_letter='<tr><td width=\"*%\" align=\"center\" class=\"rep-body1\" ><b>To be typed on company letter head</b></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+m_letter+'</table>';");
			}
						
			out.println("}");
			*/
			
		  //added by nuwan de silva on 11-10-07---------------------
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


			
			
						
			out.println("</script>");
							
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"makeRequest()\">");//add_button()
			out.println("<body bgcolor='white'><br>");
			out.println("<form name='Form1'>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_client_type' VALUE=\"\">");
				
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");
			
      out.println("<br><br><br><br>");			
		   out.println("<br><br><br><br>");			
				
			out.println("<blockquote><font size=3><p style='text-align:left'>");					
  		out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr><td width=\"100%\" class='rep-body1'><b></b></td></tr>"); //"+m_status+"
		  out.println("</table>");

			out.println("</font></p></blockquote>");	
	  	out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
			
		 	/*out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >"+m_full_name+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >"+m_add1+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >"+m_add2+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >"+m_city_name+"</td></tr>");
			out.println("</table>");	
			out.println("<br><br>");
			*/
			
			
				//added by nuwan de silva on 05-09-07		
			rs = stmt.executeQuery(Client_Data);
			more = rs.next();		
			String m_type=rs.getString(9);
			if(m_type.equals("I")){	
			while(more){	
			
				//m_title=rs.getString(1);
				m_full_name=rs.getString(2);
				m_add1=rs.getString(3);
				m_add2=rs.getString(4);
				m_city_name=rs.getString(5);
				m_nic_no=rs.getString(6);
				
			if(rs.getString(1).equals("CLIENT")){
		 	out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_full_name+"</B></td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><B>Of "+m_add1+"</B></td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_add2+"</B></td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_city_name+" </B></td></tr>");
			out.println("</table>");	
			}
			else if(rs.getString(1).equals("CO-APPLICANT")){
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' ><B>And</B></td></tr>");
			out.println("</table>");	
		 	out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_full_name+" </B></td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><B>Of "+m_add1+"</B></td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_add2+"</B></td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_city_name+"</B></td></tr>");
			out.println("</table>");	
			}
			more = rs.next();		
			}
			}
			
			out.println("<br><br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_Letter_date+"</b></td></tr>");
			out.println("</table>");
			out.println("<br><br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' ><b>THE MANAGER</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_orient_name+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_orient_add1+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_orient_add2+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_orient_city_name+"</td></tr>");
			out.println("</table>");	
			out.println("<br><br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >Dear Sir,</td></tr>");
			out.println("</table>");
			out.println("<br><br>");
					
			out.println("<table border='0' width='90%' class='table'>"); 	
					
			out.println("<tr><td width='*%' class='rep-body1' ><b>Stamp Duty on Lease Purchase Agreement - Agreement No : "+m_finance_no+"</b></td></tr>");
			
			out.println("</table>");
			out.println("<br><br>");
			
	    out.println("</font></p></blockquote>");
			
		  out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
		
		  String data="In the event of the Inland Revenue Department ruling that the above Lease Purchase "+
			            "agreement would be liable for stamp duty I/We agree to pay stamp duty payable on the "+
									"agreement within seven days of being informed by <B><I>"+m_orient_name+"</I></B> "+
									"I/We also agree that in the event of my/Our not paying the stamp duty <B><I>"+m_orient_name+"</I></B> "+
									"would have the right to debit the stamp duty payable by me/ us to the lease contract and appropriate any "+
									"payment made by me/ us to the contract against stamp duty payable by me/ us."; 
									
								
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			    
     	out.println("<br>");	
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			
			
			
				//added by nuwan de silva on 05-09-07		
			rs.close();
			rs = stmt.executeQuery(Client_Data);
			more = rs.next();		
				
			while(more){	
				m_full_name=rs.getString(7);
				m_nic_no=rs.getString(6);
				
			if(rs.getString(1).equals("CLIENT")){
		 	out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' style='text-align:left'  >...............................................</td>");
			out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_full_name+"</B></td></tr>");
			out.println("</table>");	
			out.println("<br>");	
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' style='text-align:left'  ><B>"+rs.getString(8)+"</B>:"+m_nic_no+"</td>");
			out.println("</table>");	
			}
			else if(rs.getString(1).equals("CO-APPLICANT")){
			out.println("<br><br><br>");
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' style='text-align:left'  >...............................................</td>");
			out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_full_name+"</B></td></tr>");
			out.println("</table>");	
			out.println("<br>");	
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' style='text-align:left'  ><B>"+rs.getString(8)+"</B>:"+m_nic_no+"</td>");
			out.println("</table>");	
			}
			more = rs.next();		
			}
			
			
			
			
			//comment by nuwan de silva on 04-09-07----------------------						
			
		 /*	out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' style='text-align:left'  >...............................................</td>");
			out.println("<tr><td width='*%' class='rep-body1' style='text-align:left'  >"+m_full_name.toUpperCase()+"</td>");
			out.println("<tr></tr>");
			out.println("<tr></tr>");
			out.println("<tr></tr>");
			
			if(m_client_type.equals("I")){
			out.println("<tr><td width='*%' class='rep-body1' style='text-align:left'  ><B>NIC No</B>:"+m_nic_no+"</td></tr>");
			}else{
			out.println("<tr><td width='*%' class='rep-body1' style='text-align:left'  ><B>Bus. Reg. No</B>:"+m_nic_no+"</td></tr>");
			}
			out.println("</table>");
			
			*/
			
			out.println("<br><br>");
			    		
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
			   if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			   if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	       if(conn  !=null){try{conn.close(); }catch(Exception e){}}
				 if(out   !=null){try{out.close();  }catch(Exception e){}}
				}
	   } 
    }
