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

public class LAKDL_AF_RE_Recovery_Letter_HP4_letter_of_termi extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt;
	//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
	public ResultSet rs;
	
	
	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_mlease_no,m_print,m_ter_type,m_ter_date,m_agree_date;
	public double m_amount_due;
	
	String m_guranter0="";
	String m_guranter1="";
	String m_guranter2="";
	String m_guranter3="";
	String m_guranter4="";
	String m_gurantertitle0="";
	String m_gurantertitle1="";
	String m_gurantertitle2="";
	String m_gurantertitle3="";
	String m_gurantertitle4="";
	String m_guranteradd0="";
	String m_guranteradd1="";
	String m_guranteradd2="";
	String m_guranteradd3="";
	String m_guranteradd4="";
	String name_gurant="";
	String title_gurant="";
	String add1_gurant="";
	String add2_gurant="";
	String city_gurant="";
	String m_guranteradd0_2="";
	String m_guranteradd1_2="";
	String m_guranteradd2_2="";
	String m_guranteradd3_2="";
	String m_guranteradd4_2="";
	String m_guranter_city0="";
	String m_guranter_city1="";
	String m_guranter_city2="";
	String m_guranter_city3="";
	String m_guranter_city4="";
	
	
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
				String m_date=req.getParameter("date");
				String m_due_amount=req.getParameter("due_amount");
				
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY') FROM DUAL ");
				boolean  more = rs.next();			
				
				if(more){
					
					m_Letter_date=rs.getString(1);
				}
				
				/*if(req.getParameter("status")==null){
							
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
		}*/
				
				
				
				/* rs = stmt.executeQuery(" SELECT "+
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
										}*/
				//System.out.println("bbbbbbbb");	
				//added by nuwan de silva on 05-09-07		
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
				//System.out.println("ccccccccccccccccccc");	
				//System.out.println(Client_Data);
				
				rs=stmt.executeQuery (" SELECT "+
					"  NVL(FINANCE_NO,'-'),NVL(MASTER_AGREEMENT_NO,'-'),TO_CHAR(AGREEMENT_DATE,'DD-MM-YYYY'), "+
					"  NVL(TER_TYPE,'-'), "+
					"  NVL(TO_CHAR(TER_TYPE_ENT_DATE,'DD-MM-YYYY'),'-') "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
				
				more = rs.next();
				
				
				if(more){
					m_ter_type = rs.getString(4);
					m_ter_date = rs.getString(5);
					m_finance_no=rs.getString(1);
					m_mlease_no=rs.getString(2);
					m_agree_date=rs.getString(3);
				}
				
				
				
				out.println("<html><head>"); 
				out.println("<title>Letter of termination -HP </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				
				out.println("<script>");
				
				out.println("function save_data(){");
				out.println("m_table.innerHTML=\"\" ");
				out.println("window.print();");
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_RE_RECOVE_LETTER_GENERATION&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"&due_amount="+m_due_amount+"&date="+m_date+"\";"); 
				out.println(" window.location.href=m_url;"); 
				
				out.println("}");
				
				// Added by Chandana on 12/07/07 for Rerf No.540	
				out.println("function makeRequest() {");
				//out.println(" document.Form1.hid_status.value ='H1';");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_chk_get_client_type&data_val="+m_client_code+" \";");
				// out.println("load_interface(m_url,'XML');");
				out.println("}");
				
				out.println("function get_vector(data_vec) {");
				out.println("	if(data_vec.length>0 && document.Form1.hid_status.value == 'H1' ){");
				out.println(" document.Form1.hid_client_type.value=data_vec[1];");
				out.println("	}");
				out.println(" add_button();");
				out.println("		}");
				// End on 12/07/07		  
				
				out.println("function add_button(){");
				
				//out.println(m_print.trim()+"AAAAAA");
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
				
				
				
				rs = stmt.executeQuery(Client_Data);
				more = rs.next();	
				//String m_type=rs.getString(9);
				//if(m_type.equals("I")){
				
				while(more){	
					
					//m_title=rs.getString(1);
					m_full_name=rs.getString(2);
					m_add1=rs.getString(3);
					m_add2=rs.getString(4);
					m_city_name=rs.getString(5);
					m_nic_no=rs.getString(6);
					
					
					more = rs.next();		
				}
				//}
				
				
				out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()
				out.println("<body bgcolor='white'><br>");
				out.println("<form name='Form1'>");
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_client_type' VALUE=\"\">");
				
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");
				
				
				
				out.println("<blockquote><p style='text-align:left'>");					
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr><td width=\"100%\" class='txt-body' align='center'><font size=2.7 ><b> REGISTERED POST</b></font></td></tr>");
				out.println("</table>");
				out.println("</p></blockquote>");
				
				
				out.println("<blockquote><p style='text-align:left'>");					
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr><td width=\"100%\" class='txt-body'>"+m_Letter_date+"</td></tr>");
				//out.println("<tr><td width=\"100%\" class='txt-body'><font size=2.7 >"+m_date+"</font></td></tr>");// Added By Minal on 25-11-2014 for # 14507
				
				out.println("</table>");
				out.println("</p></blockquote>");
				
				
				out.println("<blockquote><p style='text-align:left'>");					
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr><td width=\"100%\" class='txt-body'><font size=2.7 >"+m_full_name+"</font></td></tr>");
				out.println("<tr><td width=\"100%\" class='txt-body'><font size=2.7 >"+m_add1+"</font></td></tr>");
				out.println("<tr><td width=\"100%\" class='txt-body'><font size=2.7 >"+m_add2+",<BR> "+m_city_name+"</font></td></tr>");
				out.println("</table>");
				out.println("</p></blockquote>");	
				
				out.println("<blockquote><p style='text-align:right' width='100%'>");					
				out.println("<table align='center' width='90%' class='table'>"); 
				out.println("<tr><td width='*%' class='txt-body' style='text-align:right'><font size=2.7 ><b><i>Without prejudice</i></b></font></td></tr>");
				out.println("</table>");
				out.println("</p></blockquote>");
				
				
				out.println("<blockquote><p style='text-align:left' class='txt-body'>");	
				out.println("<table border='0' width='90%' class='table'>"); 
				out.println("<tr><td width='*%' class='txt-body' ><font size=2.7 >Dear Sir/ Madam, </font></td></tr>");
				out.println("</table>");
				out.println("</p></blockquote>");
				
				out.println("<blockquote><p style='text-align:left' class='txt-body'>");	
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='txt-body' ><font size=2.7 ><B>TERMINATION OF THE HIRE PURCHASE AGREEMENT UNDER SECTION 18 (1)/18(2) OF THE CONSUMER CREDIT ACT NO: 29 OF 1982 AS AMENDED FOR NON PAYMENT OR BREACH OF TERMS/CONDITIONS OF THE AGREEMENT.</B></font></td></tr>");
				out.println("</table>");
				out.println("</p></blockquote>");
				
				out.println("<blockquote><p style='text-align:left' class='txt-body'>");	
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='txt-body' ><font size=2.7 ><B><U>AGREEMENT NO:    "+m_finance_no+"</U></B></font></td></tr>");
				out.println("</table>");
				out.println("</p></blockquote>");
				
				
				
				String data="";
				String data1="";
				String data2="";
				
				
				
				data="We refer to our Notice of Termination of Hire dated "+m_date+" sent to you in terms of the Hire Purchase Agreement and provisions of the Consumer Credit Act No: 29 of 1982 as amended.";
				
				
				
				out.println("<blockquote><p style='text-align:left'>");	
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >"+data+"</font></td>");
				out.println("</tr></table>");
				out.println("</p></blockquote>");
				
				
				
				data=     "Notwithstanding the aforesaid notice you have failed and neglected to pay the arrears / remedy the inconsistent act /breach of condition specified in the said notice within the time limit stipulated therein.";
				
				
				out.println("<blockquote><p style='text-align:left'>");	
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >"+data+"</font></td>");
				out.println("</tr></table>");	
				out.println("</p></blockquote>");
				
				
				
				//data= "In the circumstances, you should either deliver the property hired forthwith to our office at Level 01,   No. 33, Park Street, Colombo 2 during our normal business hours or pay at once a sum of Rs."+m_due_amount+" being the total amount due to us as at "+m_date+".";
				
				data= "In the above circumstances, we hereby declare that the Hire created by the aforesaid Agreement is terminated forthwith and further inform you that you are no longer entitled to retain possession of the goods hired to you under the Agreement, and further your possession of the property is unlawful.";
				
				out.println("<blockquote><p style='text-align:left'>");	
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >"+data+"</font></td>");
				out.println("</tr></table>");	
				out.println("</p></blockquote>");
				
				
				
				//data= "Should you fail to do so, we will proceed with necessary action to safeguard our interest without any further notice.";
				data= "In the circumstances we hereby call upon you to deliver our property immediately to our office at Level 1,   No. 33, Park Street, Colombo 02, during normal business hours and pay forthwith a sum of Rs."+m_due_amount+" being the total amount due from you as at "+m_date+" in terms of the Agreement.";
				
				
				
				out.println("<blockquote><p style='text-align:left'>");	
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >"+data+"</font></td>");
				out.println("</tr></table>");	
				out.println("</p></blockquote>");
				
				
				data= "Should you fail to do so, we will proceed with appropriate action to safeguard our interest and enforce our rights under the Agreement and Consumer Credit Act No: 29 of 1982 (as amended) without further notice to you.";
				
				out.println("<blockquote><p style='text-align:left'>");	
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >"+data+"</font></td>");
				out.println("</tr></table>");	
				out.println("</p></blockquote>");
				
				
				data= "Termination of the Hire Purchase Agreement shall be without prejudice to the terms and conditions of the Agreement including the rights or remedies (including accrued rights prior to termination) to which we may be entitled to under the Agreement or at law and/or any pending/accrued payments due to us under the Agreement.";
				
				out.println("<blockquote><p style='text-align:left'>");	
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >"+data+"</font></td>");
				out.println("</tr></table>");	
				out.println("</p></blockquote>");
				
				
				
				
				data= "Yours faithfully";
				data1= "<b>LAKDERANA INVESTMENT</b>";
				
				out.println("<blockquote><p style='text-align:left'>");	
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >"+data+"</font></td></tr>");
				out.println("<tr><td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >"+data1+"</font></td></tr>");
				out.println("</table>");
				out.println("</p></blockquote>");
				
				
				/*
				
				out.println("<blockquote><font size=10><p style='text-align:left'>");	
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='txt-body' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");
				out.println("</font></p></blockquote>");*/
				
				
				data= "Authorized officer";
				
				
				out.println("<blockquote><font size=10><p style='text-align:left'>");	
				out.println("<table border='0' width='90%' class='table'>"); 	
				out.println("<tr><td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >......................</font></td></tr>");
				out.println("<td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >"+data+"</font></td>");
				out.println("</tr></table>");
				out.println("</font></p></blockquote>");
				
				
				rs = stmt.executeQuery ("SELECT A.GUARANTOR_CODE, "+
					
					" DECODE(C.CLIENT_TYPE,'I',NVL(UPPER(C.TITLE),' ')||'. '||NVL(UPPER(C.FULL_NAME),' '),'C',NVL(UPPER(C.FULL_NAME),' ')), "+ //MODIFIED BY Chandana on 26/07/2007 For Ref No.748
					" UPPER(NVL(C.ADDRESS1,'-')), "+ //MODIFIED BY NWUAN DE SILVA 09-07-07
					" UPPER(NVL(C.ADDRESS2,'-')), "+ //MODIFIED BY NWUAN DE SILVA 09-07-07
					" NVL(C.NIC_NO,'-'),  "+  //"UPPER" Added by Chandana on 04/07/2007
					" UPPER(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(C.CITY_CODE),'-')) "+ // Added by Chandana on 27/07/2007
					" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B ,"+m_schema_name+".AF_CO_MAS_CLIENT C "+
					" WHERE "+
					" UPPER(C.CLIENT_CODE)=UPPER(A.GUARANTOR_CODE)  "+
					" AND C.CLIENT_TYPE = 'I' "+ //Added by Chandana on 21/06/2007 
					" AND A.ACTIVE_STATUS ='Y' "+ //Added by Chandana on 16/07/2007 
					" and upper(a.application_no)=upper(b.application_no) "+
					//" AND b.TRANSACTION_TYPE <>'HIREPURCH' "+
					"	AND UPPER(B.APPLICATION_NO)=UPPER('"+m_application_no+"')"+
					" ORDER BY A.GUAR_ID,A.GUARANTOR_CODE ");
				
				
				
				
				String m_guarontor_name="";
				String m_address="";
				//String m_nic_no="";
				
				more=rs.next();
				int j=1;
				
				
				int count_gurant=0;
				while (more){
					if(count_gurant==0){
						m_guranter0=rs.getString(2);
						//m_guranteradd0=rs.getString(2);
						m_guranteradd0_2=rs.getString(3);
						m_guranter_city0=rs.getString(4);
						m_gurantertitle0=rs.getString(5);
					}
					else if(count_gurant==1){   
						m_guranter1=rs.getString(2);
						//m_guranteradd1=rs.getString(2);
						m_guranteradd1_2=rs.getString(3);
						m_guranter_city1=rs.getString(4);
						m_gurantertitle1=rs.getString(5);
					}
					else if(count_gurant==2){   
						m_guranter2=rs.getString(2);
						//m_guranteradd2=rs.getString(2);
						m_guranteradd2_2=rs.getString(3);
						m_guranter_city2=rs.getString(4);
						m_gurantertitle2=rs.getString(5);
					}
					else if(count_gurant==3){   
						m_guranter3=rs.getString(2);
						//m_guranteradd3=rs.getString(2);
						m_guranteradd3_2=rs.getString(3);
						m_guranter_city3=rs.getString(4);
						m_gurantertitle3=rs.getString(5);
					}
					else if(count_gurant==4){   
						m_guranter4=rs.getString(2);
						//m_guranteradd4=rs.getString(2);
						m_guranteradd4_2=rs.getString(3);
						m_guranter_city4=rs.getString(4);
						m_gurantertitle4=rs.getString(5);
					}
					more=rs.next();
					count_gurant=count_gurant+1;
				}
				
				/*while(more){
					
					m_guarontor_name=rs.getString(2); //ADDED BY NWUAN DE SILVA 09-07-07
					m_add1=rs.getString(3); //ADDED BY NWUAN DE SILVA 09-07-07
					m_add2=rs.getString(4);  //ADDED BY NWUAN DE SILVA 09-07-07
					m_nic_no=rs.getString(5);
					m_city_desc= rs.getString(6); //ADDED BY Chandna ON 27/07/2007
					
					
					if(!m_add1.equals("-") && !m_add2.equals("-") ) //ADDED BY NWUAN DE SILVA 09-07-07
					{
						m_address=m_add1+","+m_add2;
					}
					else if(!m_add1.equals("-") && m_add2.equals("-") )
					{
						m_address=m_add1;
					}
					if(!m_city_desc.equals("-")){ //ADDED BY CHANDANA 27-07-2007
						m_address = m_address +", "+m_city_desc;
					}
					more = rs.next();		
				}	*/
				
				data= "C/c : Guarantors";
				
				
				out.println("<blockquote><font size=10><p style='text-align:left'>");	
				out.println("<table border='0' width='90%' class='table'>"); 
				
				
				if(m_guranter0!=""){
					out.println("<tr><td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >"+data+"</font></td><tr>");	
					out.println("<tr><td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >1). "+m_guranter0+"</font></td>");
				}if(m_guranter1!=""){
					out.println("<td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >2). "+m_guranter1+"</font></td></tr>");
				}if(m_guranter2!=""){
					out.println("<td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >2). "+m_guranter2+"</font> </td></tr>");
				}if(m_guranter3!=""){
					out.println("<td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >2). "+m_guranter3+" </font></td></tr>");
				}if(m_guranter4!=""){	
					out.println("<td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >2). "+m_guranter4+"</font></td></tr>");
				}
				out.println("<tr><td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >3. Legal Division </font></td></tr>");
				
				out.println("</table>");
				out.println("</font></p></blockquote>");
				
				out.println("<br>");
				data= " Please obtain an official receipt upon your payment from Lakderana Investment.";
				//10-04-2015
				out.println("<blockquote><p style='text-align:left'>");	
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7>"+data+"</font></td>");
				out.println("</tr></table>");
				out.println("</p></blockquote>");
				out.println("<br>");
				
				
				//added by nuwan de silva on 05-09-07
				//commented by milinda 2013-10-25
				/*rs.close();
				rs = stmt.executeQuery(Client_Data);
				more = rs.next();		
					
				while(more){	
					m_full_name=rs.getString(7);
					m_nic_no=rs.getString(6);
					
				if(rs.getString(1).equals("CLIENT")){
					out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='txt-body' style='text-align:left'  >...............................................</td>");
				out.println("<tr><td width='*%' class='txt-body' ><B>"+m_full_name+"</B></td></tr>");
				out.println("</table>");	
				out.println("<br>");	
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='txt-body' style='text-align:left'  ><B>"+rs.getString(8)+"</B>:"+m_nic_no+"</td>");
				out.println("</table>");	
				}
				else if(rs.getString(1).equals("CO-APPLICANT")){
				out.println("<br><br><br>");
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='txt-body' style='text-align:left'  >...............................................</td>");
				out.println("<tr><td width='*%' class='txt-body' ><B>"+m_full_name+"</B></td></tr>");
				out.println("</table>");	
				out.println("<br>");	
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='txt-body' style='text-align:left'  ><B>"+rs.getString(8)+"</B>:"+m_nic_no+"</td>");
				out.println("</table>");	
				}
				more = rs.next();		
				}*/
				//end milinda
				
				/*out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='*%' class='txt-body' style='text-align:left'  >...............................................</td>");
				out.println("<tr><td width='*%' class='txt-body' style='text-align:left'  ><B>"+m_full_name.toUpperCase()+"</B></td>");
				out.println("<tr></tr>");
				out.println("<tr></tr>");
				out.println("<tr></tr>");
				out.println("<tr><td width='*%' class='txt-body' style='text-align:left'  ><B>NIC No</B>:"+m_nic_no+"</td>");
				out.println("</tr></table>");
				*/
				//added milnda 2013-10-25
				
				
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
