
//CREATED BY : Samith DF 	
//DATE/TIME  : 17-02-2015
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_RE_Recovery_Letter_HP3_3d_Reminder extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt;
	//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
	public ResultSet rs;
	
	
	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_mlease_no,m_print,m_ter_type,m_ter_date,m_agree_date;
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
				String m_contract_age=req.getParameter("contract_age");
				
				
				
				
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
				out.println("<title>1st Reminder letter-hp </title></head>");
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
				
				
				rs = stmt.executeQuery(Client_Data);
				while(rs.next()){	//Edited by Minal on 13-07-2015 for #17336
					m_title=rs.getString(1);
					m_full_name=rs.getString(2);
					m_add1=rs.getString(3);
					m_add2=rs.getString(4);
					m_city_name=rs.getString(5);
					m_nic_no=rs.getString(6);
					
					out.println("<br><br>");
					out.println("<br><br>");
					
					
					out.println("<blockquote><p style='text-align:left'>");					
					out.println("<table align='center' width='100%' class='table'>"); 
					out.println("<tr><td width=\"100%\" class='txt-body' align='center'><font size=2.7 ><b>Registered Post</b></font></td></tr>");
					out.println("</table>");
					out.println("</p></blockquote>");
					
					
					
					out.println("<blockquote><p style='text-align:left'>");					
					out.println("<table align='center' width='100%' class='table'>"); 
					out.println("<tr><td width=\"100%\" class='txt-body'> "+m_Letter_date+"</td></tr>");
					
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
					out.println("<tr><td width='*%' class='txt-body' ><font size=2.7 >Dear Sir/ Madam,</font></td></tr>");
					out.println("</table>");
					out.println("</p></blockquote>");
					
					
					out.println("<blockquote><p style='text-align:left' class='txt-body'>");	
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<tr><td width='*%' class='txt-body' ><font size=2.7 ><B><U>PAYMENT REMINDER</U></B></font></td></tr>");
					out.println("<tr><td width='*%' class='txt-body' ><font size=2.7 ><B><U>Hire Purchase Agreement No:  "+m_finance_no+"</U></B></font></td></tr>");
					//out.println("<tr><td width='*%' class='txt-body' ><B><U>Schedule No:  "+m_finance_no+"</U></B></td></tr>");
					out.println("</table>");
					out.println("</p></blockquote>");
					
					
					
					String data="";
					String data2="";
					String data3="";
					
					//data="We refer to the above hire purchase agreement and write to inform you that you have not paid "+m_contract_age+" hiring rentals totaling to Rs."+m_due_amount+" as at "+m_date+"  ";
					data="We refer to the above Hire Purchase Agreement and write to inform you that overdue here purchase rental/s and other charges amounts to Rs. "+m_due_amount+". ";
					
					
					out.println("<blockquote><p style='text-align:left'>");	
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >"+data+"</font></td>");
					out.println("</tr></table>");
					out.println("</p></blockquote>");
					
					
					/*data=     "We kindly request you to make immediate payment to settle the above amount. This will ensure that "+
					"you will not be called upon to unnecessarily pay any further overdue interest. Please arrange regular"+
					"payments on the due dates in the future. ";*/
					
					
					data="We regret to state that in the event that your dues are not settled, we would be reluctantly compelled to write to the guarantors about the status of your facility. Further your facility will be classified as an overdue facility in the Credit Information Bureau of Sri Lanka (CRIB) and such would adversely affect your credit worthiness.";	
					
					
					out.println("<blockquote><p style='text-align:left'>");	
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >"+data+"</font></td>");
					out.println("</tr></table>");	
					out.println("</p></blockquote>");
					
					
					
					data=  "We hope that you will take immediate action on receipt of this letter, to settle the said dues and will not allow us to proceed taking further action.";
					
					
					out.println("<blockquote><p style='text-align:left'>");	
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >"+data+"</font></td>");
					out.println("</tr></table>");	
					out.println("</p></blockquote>");
					
					
					
					
					data=  "<B>If you have already paid kindly disregard this reminder.</B> In the event that payment has been made by direct remittance, kindly communicate to us of the same by forwarding documentary proof relating to such remittance, either by handing over / email / fax of same followed by a telephone call.";
					
					
					out.println("<blockquote><p style='text-align:left'>");	
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >"+data+"</font></td>");
					out.println("</tr></table>");	
					out.println("</p></blockquote>");
					
					
					
					
					data= "Thanking You,";
					
					
					
					out.println("<blockquote><p style='text-align:left'>");	
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >"+data+"</font></td>");
					out.println("</tr></table>");	
					out.println("</p></blockquote>");
					
					/* data= 	"We are also compelled to report the position of overdue hiring rentals in your hire purchase facility to the Credit Information Bureau of Sri Lanka (CRIB) unless you make payments to settle the overdue rentals . Please note that if your name is reported to the CRIB as an irregular hirer, it will be a black mark on your name which will prevent you taking a credit facility from any financial institution in the future.";
					
						
						
					out.println("<blockquote><p style='text-align:left'>");	
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='txt-body' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");	
					out.println("</p></blockquote>");
					
						data= "We hope that you will take immediate action on receipt of this letter, to settle the arrears of hire purchase rentals and will not allow us to proceed in taking the other steps mentioned above."; 
					
						
						
					out.println("<blockquote><p style='text-align:left'>");	
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='txt-body' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");	
					out.println("</p></blockquote>");
					
						data= "In the event you have already settled the above mentioned overdue lease rentals, please disregard this reminder and we apologize for the inconvenience caused and thank you for such payments.";
					
						
					out.println("<blockquote><p style='text-align:left'>");	
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='txt-body' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");
					out.println("</p></blockquote>");
					
					
						data= "Thanking You,";
					
					
					out.println("<blockquote><p style='text-align:left'>");	
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='txt-body' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");
					out.println("</p></blockquote>");*/
					
					
					data= "Yours faithfully";
					String  data1= "<b>LAKDERANA INVESTMENT</b>";
					
					out.println("<blockquote><p style='text-align:left'>");	
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<tr><td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >"+data+"</font></td></tr>");
					out.println("<tr><td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >"+data1+"</font></td></tr>");
					out.println("</table>");
					out.println("</p></blockquote>");
					
					
					
					/*
					out.println("<blockquote><p style='text-align:left'>");	
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='txt-body' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");
					out.println("</p></blockquote>");
					
					
					data= "Authorized officer";
					
					
					out.println("<blockquote><p style='text-align:left'>");	
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='txt-body' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");
					out.println("</p></blockquote>");
					*/
					
					
					data= "Authorized officer";
					
					
					out.println("<blockquote><font size=10><p style='text-align:left'>");	
					out.println("<table border='0' width='90%' class='table'>"); 	
					out.println("<tr><td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >......................</font></td></tr>");
					out.println("<td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7 >"+data+"</font></td>");
					out.println("</tr></table>");
					out.println("</font></p></blockquote>");
					
					
					
					//out.println("<br>");
					
					
					out.println("<blockquote><p style='text-align:left'>");	
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='txt-body' style='text-align:justify' ><font size=1.9 >Payments can be made to Hatton National Bank Account number 003010507501.</font></td>");
					out.println("</tr></table>");
					out.println("</p></blockquote>");
					
					data= " Please obtain an official receipt upon your payment from Lakderana Investment.";
					//10-04-2015
					out.println("<blockquote><p style='text-align:left'>");	
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<td width='*%' class='txt-body' style='text-align:justify' ><font size=2.7>"+data+"</font></td>");
					out.println("</tr></table>");
					out.println("</p></blockquote>");
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
					
					if(rs.getRow()==1) { //added by kanishka dilshan on 10-09-2015 -- co-applicant margin
						out.println("<p style='page-break-before: always;'>");//Added by Minal on 13-07-2015 for #17336
						out.println("<table align='center' width='100%' class='table'>"); 
						out.println("<tr><td width=\"100%\">&nbsp;</td></tr><tr><td width=\"100%\">&nbsp;</td></tr>"); 
						out.println("</table>");
					}
				}
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