//ID         :
//SCREEN NAME:Document Printing - Noobjection - Join Applcant Letter
//CREATED BY :Chandana 	
//DATE/TIME  : 12-06-2007
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Document_Noobject_join_applicant extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt_partner,stmt2;
//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
  public ResultSet rs,rs2,rs_partner;
 	

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
			stmt2 = conn.createStatement ();
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
        												" NVL(UPPER(TITLE),''), "+
																//" NVL(UPPER(FULL_NAME),' '), "+
																" DECODE(CLIENT_TYPE,'I',NVL(UPPER(TITLE),' ')||' '||NVL(UPPER(FULL_NAME),' '),'C',NVL(UPPER(FULL_NAME),' ')), "+ //MODIFIED BY Chandana on 26/07/2007 For Ref No.748
																" REPLACE(REPLACE(NVL(UPPER(ADDRESS1),' '),'-',' '),'null',' '),"+
																" REPLACE(REPLACE(NVL(UPPER(ADDRESS2),' '),'-',' '),'null',' '),"+
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
											 
					
				/* String sql=" SELECT "+
         " NAME "+
         " FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DIRECTORS "+
         " WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') ";
					*/
					
			/*	String sql=" SELECT UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)), "+
				           " NVL(UPPER("+m_schema_name+".af_co_get_client_name(CO_APPLICANT)),' ') "+
									 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
									 " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ";*/
										
										
				String sql=" SELECT "+
				           " (SELECT decode(X.client_type,'I',X.TITLE,'C',' ') || '. '|| NVL(UPPER(X.FULL_NAME),' ') "+
									 " FROM "+m_schema_name+".AF_CO_MAS_CLIENT X "+
									 " WHERE X.CLIENT_CODE=A.CLIENT_CODE) CLIENT_NAME, "+
									 " (SELECT decode(X.client_type,'I',X.TITLE,'C',' ') || '. '|| NVL(UPPER(X.FULL_NAME),' ') "+
									 " FROM "+m_schema_name+".AF_CO_MAS_CLIENT X "+
									 " WHERE X.CLIENT_CODE=A.CO_APPLICANT) CO_APP_NAME "+
									 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+
									 " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
									 " A.CLIENT_CODE=B.CLIENT_CODE ";
										


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
				
				
				String m_eng_no="";
				String m_cha_no="";
				String m_reg_no="";
				String m_discrp="";
				
				/* rs=stmt.executeQuery ("SELECT NVL(A.ENGINE_NO,' '),NVL(A.CHASSIS_NO,' '),NVL(A.REG_NO,' '), "+
					                     " NVL(A.MODEL_CODE,' ')|| '-' ||NVL(B.DESCRIPTION,' ') "+
															 " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_MAS_SUB_MODLE B "+
															 " WHERE A.SUB_MODEL_CODE=B.SUB_CODE AND "+
															 " APPLICATION_NO='"+m_application_no+"' "); */
																
			
				//added by nuwan de silva on 12-10-07		
			String Client_Data=" SELECT  "+
			" 'CLIENT', "+ //1
			" NVL(DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C',/*'MESS' || '. '  || */UPPER(FULL_NAME)),' ') ,   "+ //2
			" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS1),'C',UPPER(REGISTERED_ADDRESS1)),' '),  "+ //3
			" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS2),'C',UPPER(REGISTERED_ADDRESS2)),' ') , "+ //4
			" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //5
			" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+ //6
			" NVL(DECODE(CLIENT_TYPE,'I',UPPER(FULL_NAME),'C',UPPER(FULL_NAME)),' ') ,   "+ //2
			" NVL(DECODE(CLIENT_TYPE,'I','NIC No','C','REG No'),' ') C_TYPE,    "+ //2
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
			" DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C',/*'MESS' || '. '  ||*/ UPPER(FULL_NAME)),   "+
			" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS1),'C',UPPER(REGISTERED_ADDRESS1)),' '),  "+
			" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS2),'C',UPPER(REGISTERED_ADDRESS2)),' ') , "+
			" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+
			" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+
			" NVL(DECODE(CLIENT_TYPE,'I',UPPER(FULL_NAME),'C',UPPER(FULL_NAME)),' '),    "+ //2
			" NVL(DECODE(CLIENT_TYPE,'I','NIC No','C','REG No'),' ') C_TYPE,    "+ //2
			" CLIENT_TYPE "+
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
			" WHERE   CLIENT_CODE =  "+
			" (SELECT  "+
		  " CO_APPLICANT  "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
			" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"')) ";
			
				
				
				
				
			  out.println("<html><head>"); 
				out.println("<title>Acceptance Receipt </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
		   out.println("<script>");
			
			out.println("function save_data(){");
			//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_Noobject_and_aware_LetII?chksql=main_page&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"&print=FALSE\";"); 
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";");
			
			//LAKDL_AF_CR_PRO_Document_Noobject_and_aware_Let?chksql=main_page&application_no=AP20061124-0184&document_code=NIBSM_LETT&print=TRUE&client_code=A
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
			
		
		 /* out.println("function add_button(){");
			
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
			*/
			
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
			
			
																
					rs=stmt.executeQuery (" SELECT "+
					        " nvl(B.ENGINE_NO,'-'), "+
									" nvl(B.CHASSIS_NO,'-'), "+
		              " NVL(B.REG_NO,'-'), "+
								 // " INITCAP(C.MAKE_DESC||' '||F.DESCRIPTION||' '||D.DESCRIPTION ||' '||H.DESCRIPTION) MODEL_DESC, "+ //comment by nuwan de silva on 12-12-2007 at ofscl
									" INITCAP(C.MAKE_DESC||' '||D.DESCRIPTION ||' '||H.DESCRIPTION) MODEL_DESC, "+ //added by nuwan de silva on 12-12-2007 at ofscl
								  " NVL(D.YEAR_OF_MANUFACTURE,''), "+
								  " NVL(B.SUB_MODEL_CODE,' '), "+ 
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
								  " B.MODEL_CODE=F.MODEL_CODE ");								
																
																
																
				
				more = rs.next();
				
				while(more){ //Modified by Chandana on 06/08/2007 (genarate a letter for each asset)
				  m_eng_no=rs.getString(1);
					m_cha_no=rs.getString(2);
					m_reg_no=rs.getString(3);
				  m_discrp=rs.getString(4);
				
			
      		
			out.println("<blockquote><font size=3><p style='text-align:left'>");					

			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr><td width=\"100%\" class='rep-body1'><b></b></td></tr>");
		  out.println("</table>");

			out.println("</font></p></blockquote>");	
			
			
		  out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
			
			
		//	out.println("<table border='0' width='80%' class='table'>"); 		
		//	out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_title+". "+m_full_name+"</B></td></tr>");
		//	out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_add1+"</B></td></tr>");
		//	out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_add2+"</B></td></tr>");
		//	out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_city_name+".</B></td></tr>");
		//	out.println("</table>");	
			
			out.println("<br><br>");	
			out.println("<br><br>");
			out.println("<br><br>");
			out.println("<br><br>");
			out.println("<br><br>");
			//out.println("<br><br>");
			//out.println("<br><br>");
			
			
				//added by nuwan de silva on 05-09-07		
			//comment by nuwan de silva on 11-10-07-----
			rs2 = stmt2.executeQuery(Client_Data);
			boolean more2 = rs2.next();		
			String m_type=rs2.getString(9);
			if(m_type.equals("I")){
				
			while(more2){	
			
				//m_title=rs.getString(1);
				m_full_name=rs2.getString(2);
				m_add1=rs2.getString(3);
				m_add2=rs2.getString(4);
				m_city_name=rs2.getString(5);
				//m_nic_no=rs.getString(6);
				
			
			if(rs2.getString(1).equals("CLIENT")){
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_full_name+"</B></td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><B>Of "+m_add1+"</B></td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_add2+"</B></td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_city_name+" </B></td></tr>");
			out.println("</table>");	
			}
			else if(rs2.getString(1).equals("CO-APPLICANT")){
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
			more2 = rs2.next();		
			}
			}
			out.println("<br><br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' ><b>Date: "+m_Letter_date+"</td></tr>");
			out.println("</table>");
			out.println("<br><br>");
												
			out.println("<br><br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >COMMISSIONER OF MOTOR TRAFFIC</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >DEPARTMENT OF MOTOR TRAFFIC</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >NARAHENPITA</td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' >COLOMBO 05.</td></tr>");
			out.println("</table>");	
			
			out.println("<br><br>");
			
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' >Dear Sir,</td></tr>");
			out.println("</table>");
						
			out.println("<br><br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' ><b>DESCRIPTION OF THE VEHICLE : "+m_discrp+"</u></td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><b>CHASSIS NO: "+m_cha_no+"</u></td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><b>ENGINE NO: "+m_eng_no+"</u></td></tr>");
	    out.println("<tr><td width='*%' class='rep-body1' ><b>REGISTER NO: "+m_reg_no+"</u></td></tr>");
			out.println("<tr><td width='*%' class='rep-body1' ><b>LEASE AGREEMENT NO: "+m_finance_no+"</td></tr>");
			out.println("</table>");
			
	    out.println("</font></p></blockquote>");
			
		
	
		out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
		
				
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
		
		
		    String   data="We wish to inform you, that we have no objection in registering the vehicle <B>Chassis No "+
		                  ""+m_cha_no+"</B> to the name of"; 
						
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");	
			
			data="<B>....................................................................."+
					 ".......................................................................................................</B>"; 
			
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
				
				
		out.println("<br><br>");
		
		out.println("<table border='0' width='80%' class='table'>"); 		
		out.println("<tr><td width='40%' class='rep-body1' ><u>Signature</td>");
		out.println("<td width='40%' class='rep-body1' ><u>Name</td></tr>");
		
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		
		rs_partner=stmt_partner.executeQuery (sql);		
		
		
		while(rs_partner.next()){
				
				
		out.println("<tr><td width='40%' class='rep-body1' >............................................</td>");
		out.println("    <td width='40%' class='rep-body1' >"+rs_partner.getString(1)+"</td></tr>");
		
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		out.println("<tr></tr>");
		
		out.println("<tr><td width='40%' class='rep-body1' >............................................</td>");
		out.println("    <td width='40%' class='rep-body1' >"+rs_partner.getString(2)+"</td></tr>");
		
		out.println("<tr></tr>");
		
		}
		
		out.println("</table>");
		
						
    		
		out.println("</font></p></blockquote>");		
		out.println("   <p style=\"page-break-after:always\"></p>"); 
				
		more = rs.next();
		}	
			
				
		
												
		 // out.println("</form></body></html>");
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
