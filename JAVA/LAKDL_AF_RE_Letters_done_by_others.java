/*
// header - edit "Data/yourJavaHeader" to customize
// contents - edit "EventHandlers/Java file/onCreate" to customize
//
*/

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_RE_Letters_done_by_others extends javax.servlet.http.HttpServlet { 

			ServletOutputStream out = null;
			Connection conn;
			CallableStatement callstmt;
			Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt5,stmt6;
			java.text.NumberFormat nf;
			public ResultSet rs,rs1,rs2,rs3,rs4,rs5,rs6;
			public String m_html_client_url,m_Letter_date,m_full_name,m_add1,m_add2,m_city_name,m_nic_no,m_data,m_data_1,m_data_2,m_data_3;
			
			
			public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
			try { 
			
			
				
				LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
				LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
				conn = con_method.met_user_validate(req); 
				String m_html_client_url = con_method.html_client_url;
				String header_name=con_method.header_name.trim();
				String m_schema_name = con_method.schema_name;
				String m_username = con_method.username;
				String m_servlet_client_url=con_method.servlet_client_url;
				String m_client_name=con_method.client_name;
				String m_client_t3_port=con_method.client_t3_port;
				String m_class_url=m_servlet_client_url.trim()+":"+m_client_t3_port.trim(); 

				
				String m_orient_name="";
				String m_orient_add1="";
				String m_orient_add2="";
				String m_orient_city_name="";
				String m_orient_tel_no="";
				String m_orient_fax_no="";
				String m_orient_vat_rate="";
				
				String vehicle_no = "";
				
				String m_make_desc="";
				String m_chksql = req.getParameter("chksql");
				res.setStatus(HttpServletResponse.SC_OK); 
				res.setContentType("text/html"); 
				out = res.getOutputStream(); 
				if(m_chksql.trim().equals("main_page")){
				
						stmt = conn.createStatement ();
						String m_application_no="";
						String m_finance_no="";
						String m_client_code="";
						String m_due_date="";
						if(req.getParameter("APP_NO")!=null){
							m_application_no	  =req.getParameter("APP_NO");		
						}
						if(req.getParameter("FIN_NO")!=null){
							m_finance_no	=req.getParameter("FIN_NO");	
							//m_finance_no = "070319-0463";
						}
						if(req.getParameter("CLIENT_NO")!=null){
							m_client_code=req.getParameter("CLIENT_NO");
						}
						if(req.getParameter("DUE_DATE")!=null){
							m_due_date=req.getParameter("DUE_DATE");
						}
						
						//String m_document_code	=req.getParameter("document_code");	
						//String m_print=req.getParameter("print");
						//out.println(m_application_no);
						//out.println(m_finance_no);
						//out.println(m_client_code);
						
						
						rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'MONTH DD,YYYY') FROM DUAL ");
			
						boolean more = rs.next();
						if(more){
								m_Letter_date=rs.getString(1);
						}
			
			
						
						//--Close the Result Set And Stateement--------			
						rs.close();
						stmt.close();
						
						
						stmt1 = conn.createStatement ();
						
						out.println("<html><head>"); 
						out.println("<title>Remainder Letter-s Done By Others </title></head>");
						out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  		out.println("<script>");
						
						out.println("function save_data(){");
						//out.println("get_annexure('"+m_application_no+"')");
						out.println("m_table.innerHTML=\"\" ");
						out.println("window.print();");
						out.println("  }");
						
						out.println("function add_button(){");
						out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
						out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    				out.println("m_writedata+'</table>';");
						out.println("}");
						
						out.println("</script>");
						
						out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()
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
			
						out.println("<blockquote><blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
			
						out.println("<table border='0' width='80%' class='table'>"); 		
						out.println("<tr><td width='*%' class='rep-body1' >"+m_Letter_date+"</td></tr>");
						out.println("</table>");
			
						out.println("<br><br><br>");
						
						
						//out.println("<table border='0' width='80%' class='table'>"); 		
						//out.println("<tr><td width='*%' class='rep-body1' >Lessee</td></tr>");
						out.println("</table>");
						String Client_Data=" SELECT  "+
						" 'CLIENT', "+ //1
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C',/*'MESS' || '. '  || */UPPER(FULL_NAME)),' ') ,   "+ //2
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS1),'C',UPPER(REGISTERED_ADDRESS1)),' '),  "+ //3
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS2),'C',UPPER(REGISTERED_ADDRESS2)),' ') , "+ //4
						" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //5
						" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+ //6
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(FULL_NAME),'C',UPPER(FULL_NAME)),' ')    "+ //2
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
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(FULL_NAME),'C',UPPER(FULL_NAME)),' ')    "+ //2
						" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
						" WHERE   CLIENT_CODE =  "+
						" (SELECT  "+
		  			" CO_APPLICANT  "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
						" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"')) ";
						
						rs1 = stmt1.executeQuery(Client_Data);
						boolean more1 = rs1.next();		

						while(more1){	
							m_full_name=rs1.getString(2);
							m_add1=rs1.getString(3);
							m_add2=rs1.getString(4);
							m_city_name=rs1.getString(5);
							m_nic_no=rs1.getString(6);
				
						if(rs1.getString(1).equals("CLIENT")){
		 					out.println("<table border='0' width='80%' class='table'>"); 		
							out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_full_name+"</B></td></tr>");
							out.println("<tr><td width='*%' class='rep-body1' ><B>Of "+m_add1+"</B></td></tr>");
							out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_add2+"</B></td></tr>");
							out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_city_name+"</B></td></tr>");
							out.println("</table>");	
						}
			
						else if(rs1.getString(1).equals("CO-APPLICANT")){
						out.println("<table border='0' width='80%' class='table'>"); 		
						out.println("<tr><td width='*%' class='rep-body1' ><B>And</B></td></tr>");
						out.println("</table>");	
		 				out.println("<table border='0' width='80%' class='table'>"); 		
						out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_full_name+" </B></td></tr>");
						out.println("<tr><td width='*%' class='rep-body1' ><B>Of "+m_add1+"</B></td></tr>");
						out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_add2+"</B></td></tr>");
						out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_city_name+"</B></td></tr>");
						out.println("</table>");	
						}
						more1 = rs1.next();		
					}
					  rs1.close();
						stmt1.close();
						
						
						stmt2 = conn.createStatement ();
						
						String GuarantorSql= "SELECT  "+
						
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C',/*'MESS' || '. '  || */UPPER(FULL_NAME)),' ') ,   "+ //1
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS1),'C',UPPER(REGISTERED_ADDRESS1)),' '),  "+ //2
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS2),'C',UPPER(REGISTERED_ADDRESS2)),' ') , "+ //3
						" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //4
						" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+ //5
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(FULL_NAME),'C',UPPER(FULL_NAME)),' ')    "+ //1
						" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
						" WHERE   CLIENT_CODE IN  "+
						" (SELECT  "+
						" GUARANTOR_CODE "+ 
						" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR "+
						" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"'))";
						rs2 = stmt2.executeQuery(GuarantorSql);
						boolean more2 = rs2.next();
						if(more2){
						int j=1;
						out.println("<table border='0' width='80%' class='table' cellspacing='0' cellpadding='0'>");
						//out.println("<tr><td width='60%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >Guarantor</td></tr>");

						while(more2){
						
						
						//out.println("<tr><td width='60%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >("+j+")&nbsp;"+rs2.getString(1)+"</td></tr>");
						//out.println("<tr><td width='60%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >&nbsp;&nbsp&nbsp;&nbsp;"+rs2.getString(2)+"</td></tr>");
						//out.println("<tr><td width='60%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >&nbsp;&nbsp&nbsp;&nbsp;"+rs2.getString(3)+"</td></tr>");
						//out.println("<tr><td width='60%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >&nbsp;&nbsp&nbsp;&nbsp"+rs2.getString(4)+"</td></tr>");
						//out.println("<tr><td width='60%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
						//out.println("<tr><td width='60%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");


						
						j=j+1;
						more2=rs2.next();
						}
						
						out.println("</table>");
						}
						out.println("<BR><BR>");
						
						
						rs2.close();
						stmt2.close();
						
						stmt3 = conn.createStatement ();
						stmt5 = conn.createStatement ();
						stmt6 = conn.createStatement ();
						
						
						out.println("<table border='0' width='80%' class='table'>"); 	
						out.println("<tr><td width='30%' class='rep-body1' style='{text-align:left}' >Dear Sir,</td>");
						out.println("<tr>");
						out.println("<tr><td width='30%' class='rep-body1' style='{text-align:left}' >&nbsp</td>");
						out.println("<tr>");
						out.println("<tr><td width='30%' class='rep-body1' style='{text-align:left}' ><b><u>INSURANCE DETAILS</b></u></td>");
						out.println("<tr>");
						out.println("<tr><td width='30%' class='rep-body1' style='{text-align:left}' >&nbsp</td>");
						out.println("<tr>");
						out.println("<tr><td width='30%' class='rep-body1' style='{text-align:left}' >Lease Agreement No</td>");
						out.println("<td width='4%' class='rep-body1' style='{text-align:left}'>:</td>");
						out.println("<td width='*%' class='rep-body1' style='{text-align:left}'>"+m_finance_no+"</td></tr>");
						
						out.println("<tr><td width='30%' class='rep-body1' style='{text-align:left}'>Vehicle No</td><td width='4%' style='{text-align:left}'>:</td>");
						
						String get_vehicle_no = " select NVL(VEHICLE_NO,'.....................') "+
						                        " from "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
						                        " where APPLICATION_NO = '"+m_application_no+"' ";
						rs5 = stmt5.executeQuery(get_vehicle_no);
						boolean more5 = rs5.next();
						if(more5){
							vehicle_no = rs5.getString(1);
							out.println("<td width='*%' class='rep-body1' style='{text-align:left}'>"+vehicle_no+"</td></tr>");
						}
						
						out.println("</table>");
						
						String sql_make= " SELECT '','','', "+
	" MODEL_CODE,NVL(MODEL_DESC,' '),YEAR_OF_MANUFACTURE,PRICING_NO,SUB_MODEL_CODE "+
	" FROM "+
	" (SELECT NVL(B.REG_NO,'-') REG_NO, "+
	" nvl(B.ENGINE_NO,'-') ENGINE_NO, "+
	" nvl(B.CHASSIS_NO,'-') CHASSIS_NO, "+
	" B.MODEL_CODE, "+
	//" INITCAP(C.MAKE_DESC||' '||F.DESCRIPTION||' '||D.DESCRIPTION ||' '||H.DESCRIPTION) MODEL_DESC, "+
	" INITCAP(C.MAKE_DESC||' '||D.DESCRIPTION||' '||H.DESCRIPTION) MODEL_DESC, "+ // modified by nuwan de silva on 12-12-2007
	" NVL(D.YEAR_OF_MANUFACTURE,'') YEAR_OF_MANUFACTURE, "+
	" B.PRICING_NO, "+
	" NVL(B.SUB_MODEL_CODE,' ') SUB_MODEL_CODE, "+
  " INITCAP(H.DESCRIPTION) ITEM_SUB_CAT_DESC, "+
	" C.MAKE_CODE, "+
	" F.ITEM_SUB_CAT, "+
	" UPPER(E.VENDOR_CODE), "+
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
	" B.MODEL_CODE=F.MODEL_CODE ) "+
	" GROUP BY PRICING_NO,MODEL_CODE,MODEL_DESC,YEAR_OF_MANUFACTURE,SUB_MODEL_CODE ";                           
		
						rs3=stmt3.executeQuery("SELECT "+m_schema_name+".AF_CO_MAS_ASSET_DESC('"+m_application_no+"') FROM DUAL");
						boolean more3=rs3.next();
						if(more3){
						m_make_desc=rs3.getString(1);
						}
						
						
						out.println("<table border='0' width='80%' class='table'>"); 		
						//out.println("<tr><td width='13%' class='rep-body1' style='{text-align:left}' >Asset Details</td>");
						//out.println("<td width='8%' class='rep-body1' style='{text-align:left}'>:</td>");
						//out.println("<td width='*%' class='rep-body1' style='{text-align:left}'>"+m_make_desc+"</td></tr>");
						//out.println("<tr><td width='20%' class='rep-body1' style='{text-align:left}'>Assets Details</td><td width='5%' style='{text-align:center}'>:</td>></tr>");
						out.println("</table>");
						
				String get_revenue_date = " select NVL(TO_CHAR(REVENUE_LICENSE_DATE,'DD-MM-YYYY'),'...........................') "+
												  " from "+m_schema_name+".AF_RE_RENEWAL_INSUARANCE "+
												  " where FINANCE_NO = '"+m_finance_no+"' "+
												  " AND VEHICLE_NO = '"+vehicle_no+"' ";
												
						out.println("<br>");
						// Commented by Dineth on 2008-08-27
						/*String data100;
						rs6 = stmt6.executeQuery(get_revenue_date);
						boolean more6 = rs6.next();
						if(more6){
							data100=rs6.getString(1);
						}
						else{
						data100="................................";
						}*/
						m_data_3="This is to inform you that the insurance policy on the above vehicle was due for renewal on " +m_due_date+ " as per our records. As you aware it is very essential that you to submit the insurance policy in order to safeguard possible unexpected losses.";
						out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'>"+m_data_3+"</td></tr>");
						out.println("</table>");
						
						out.println("<br>");
						
						m_data="We are kindly requesting you to send a copy of the insurance certificate and the schedule /renewal endorsement to Lakderana Investments Limited to make sure the insurance cover is in force.";


						out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'>"+m_data+"</td></tr>");
						out.println("</table>");
						
						
						out.println("<br>");
						
						m_data="If you are unable to send a copy of the above documents within 15 Days of this letter we are reluctantly compelled to debit your lease account and do the insurance for the mutual benefit.";
						out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'>"+m_data+"</td></tr>");
						out.println("</table>");
						
						out.println("<br>");
						
						m_data="If you need further clarification on this matter please do not hesitate to contact:";
					  out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'>"+m_data+"</td></tr>");
						out.println("</table>");
						
						out.println("<br>");
						
						m_data_1 = "Mr.Damitha Girihagama ";
						m_data_2 = "Snr.Manager Insurance 5577577/0777598205";
						out.println("<table border='0' width='50%' class='table'>");
						out.println("<tr><td class='rep-body1' width='30%' style='{text-align:left}'>"+m_data_1+"</td><td class='rep-body1' width='50%' style='{text-align:left}'>"+m_data_2+"</td></tr>");
						out.println("</table>");
						
						//ut.println("<br>");
						
						m_data_1 = "Mr.Pubudu Algama  ";
						m_data_2 = "Assistant Manager Insurance 5577577/0773136568";
						out.println("<table border='0' width='50%' class='table'>");
						out.println("<tr><td class='rep-body1' width='30%' style='{text-align:left}'>"+m_data_1+"</td><td class='rep-body1' width='50%' style='{text-align:left}'>"+m_data_2+"</td></tr>");
						out.println("</table>");
						
						out.println("<br>");
						
						m_data="If you have done the insurance through Lakderana Investments Limited please ignore this letter";
					  out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'>"+m_data+"</td></tr>");
						out.println("</table>");
						
						out.println("<br>");
						
						m_data="This is a computer-generator statement. Signature is not required.";
					  out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'>"+m_data+"</td></tr>");
						out.println("</table>");
						
						/*m_data="Yours faithfully,";
						out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'>"+m_data+"</td></tr>");
						out.println("</table>");
						stmt4=conn.createStatement();
						rs4 = stmt4.executeQuery(" SELECT "+
							" INITCAP(NVL(UPPER(COMPANY_NAME),' ')), "+
							" NVL(UPPER(ADDRESS1),' '), "+
							" NVL(UPPER(ADDRESS2),' '), "+
							" NVL(UPPER(CITY),' '), "+
							" NVL(TEL_NO,' '), "+
							" NVL(FAX_NO,' '),  "+
							" NVL(VAT_RATE,0) "+
							" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
				
						boolean more5 = rs4.next();	
						
						if(more5)
						{
							m_orient_name=rs4.getString(1);
							m_orient_add1=rs4.getString(2);
							m_orient_add2=rs4.getString(3);
							m_orient_city_name=rs4.getString(4);
							m_orient_tel_no=rs4.getString(5);
							m_orient_fax_no=rs4.getString(6);
							m_orient_vat_rate=rs4.getString(7);			
						}
						
						out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'><b>"+m_orient_name+"</b></td></tr>");
						out.println("</table>");
						
						out.println("<br><br><br>");
						
						m_data="Damitha Girihagama";
						out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'><b>"+m_data+"</b></td></tr>");
						out.println("</table>");
						
						m_data="Senior Manager - Insurance ";
						out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'>"+m_data+"</td></tr>");
						out.println("</table>");
						
						
						
						
						out.println("</blockquote></blockquote>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

			*/
				}
				else if(m_chksql.trim().equals("Generate")){
				
						stmt = conn.createStatement ();
						String m_application_no="";
						String m_finance_no="";
						String m_client_code="";
						if(req.getParameter("APP_NO")!=null){
							m_application_no	  =req.getParameter("APP_NO");		
						}
						if(req.getParameter("FIN_NO")!=null){
							m_finance_no	=req.getParameter("FIN_NO");	
							//m_finance_no = "070319-0463";
						}
						if(req.getParameter("CLIENT_NO")!=null){
							m_client_code=req.getParameter("CLIENT_NO");
						}
           String m_invoice_no=req.getParameter("INVOICE_NO");
					 String m_policy_no=req.getParameter("POLICY_NO");
						
						
						rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'MONTH DD,YYYY') FROM DUAL ");
			
						boolean more = rs.next();
						if(more){
								m_Letter_date=rs.getString(1);
						}
			
			
						
						//--Close the Result Set And Stateement--------			
						rs.close();
						stmt.close();
						
						
						stmt1 = conn.createStatement ();
						
						out.println("<html><head>"); 
						out.println("<title>Remainder Letter-s Done By Others </title></head>");
						out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  		out.println("<script>");
						
						out.println("function check_date(objdd,objmm,objyy) {");						
						out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
						out.println("  checkMonthLength(objdd,objmm,objyy);");
						out.println("}");
						out.println("}");
												
						out.println("function save_data(){");
						out.println("due_date = document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
						out.println("m_url='"+m_class_url+"/"+m_schema_name+"_AF_RE_Letters_done_by_others?chksql=save_data&POLICY_NO="+m_policy_no+"&APP_NO="+m_application_no+"&CLIENT_NO="+m_client_code+"&INV_NO="+m_invoice_no+"&FIN_NO="+m_finance_no+"&DUE_DATE='+due_date;"); 
						out.println("window.location.href=m_url;");
						//out.println("m_url='"+m_class_url+"/"+m_schema_name+"_AF_RE_Letters_done_by_others?chksql=main_page&APP_NO="+m_application_no+"&FIN_NO="+m_finance_no+"&CLIENT_NO="+m_client_code+"&DUE_DATE='+due_date;");  
			    	//out.println("window.open(m_url,'displayWindow4','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
						out.println("  }");
						
						out.println("function add_button(){");
						out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Generate\" onClick=\"save_data()\"></td></tr>';"); 
						out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    				out.println("m_writedata+'</table>';");
						out.println("}");
						
						out.println("</script>");
						
						out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()
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
			
						out.println("<blockquote><blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
			
						out.println("<table border='0' width='80%' class='table'>"); 		
						out.println("<tr><td width='*%' class='rep-body1' >"+m_Letter_date+"</td></tr>");
						out.println("</table>");
			
						out.println("<br><br><br>");
						
						
						//out.println("<table border='0' width='80%' class='table'>"); 		
						//out.println("<tr><td width='*%' class='rep-body1' >Lessee</td></tr>");
						out.println("</table>");
						String Client_Data=" SELECT  "+
						" 'CLIENT', "+ //1
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C',/*'MESS' || '. '  || */UPPER(FULL_NAME)),' ') ,   "+ //2
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS1),'C',UPPER(REGISTERED_ADDRESS1)),' '),  "+ //3
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS2),'C',UPPER(REGISTERED_ADDRESS2)),' ') , "+ //4
						" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //5
						" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+ //6
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(FULL_NAME),'C',UPPER(FULL_NAME)),' ')    "+ //2
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
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(FULL_NAME),'C',UPPER(FULL_NAME)),' ')    "+ //2
						" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
						" WHERE   CLIENT_CODE =  "+
						" (SELECT  "+
		  			" CO_APPLICANT  "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
						" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"')) ";
						
						rs1 = stmt1.executeQuery(Client_Data);
						boolean more1 = rs1.next();		

						while(more1){	
							m_full_name=rs1.getString(2);
							m_add1=rs1.getString(3);
							m_add2=rs1.getString(4);
							m_city_name=rs1.getString(5);
							m_nic_no=rs1.getString(6);
				
						if(rs1.getString(1).equals("CLIENT")){
		 					out.println("<table border='0' width='80%' class='table'>"); 		
							out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_full_name+"</B></td></tr>");
							out.println("<tr><td width='*%' class='rep-body1' ><B>Of "+m_add1+"</B></td></tr>");
							out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_add2+"</B></td></tr>");
							out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_city_name+"</B></td></tr>");
							out.println("</table>");	
						}
			
						else if(rs1.getString(1).equals("CO-APPLICANT")){
						out.println("<table border='0' width='80%' class='table'>"); 		
						out.println("<tr><td width='*%' class='rep-body1' ><B>And</B></td></tr>");
						out.println("</table>");	
		 				out.println("<table border='0' width='80%' class='table'>"); 		
						out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_full_name+" </B></td></tr>");
						out.println("<tr><td width='*%' class='rep-body1' ><B>Of "+m_add1+"</B></td></tr>");
						out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_add2+"</B></td></tr>");
						out.println("<tr><td width='*%' class='rep-body1' ><B>"+m_city_name+"</B></td></tr>");
						out.println("</table>");	
						}
						more1 = rs1.next();		
					}
					  rs1.close();
						stmt1.close();
						
						
						stmt2 = conn.createStatement ();
						
						String GuarantorSql= "SELECT  "+
						
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C',/*'MESS' || '. '  || */UPPER(FULL_NAME)),' ') ,   "+ //1
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS1),'C',UPPER(REGISTERED_ADDRESS1)),' '),  "+ //2
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS2),'C',UPPER(REGISTERED_ADDRESS2)),' ') , "+ //3
						" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //4
						" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+ //5
						" NVL(DECODE(CLIENT_TYPE,'I',UPPER(FULL_NAME),'C',UPPER(FULL_NAME)),' ')    "+ //1
						" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
						" WHERE   CLIENT_CODE IN  "+
						" (SELECT  "+
						" GUARANTOR_CODE "+ 
						" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR "+
						" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"'))";
						rs2 = stmt2.executeQuery(GuarantorSql);
						boolean more2 = rs2.next();
						if(more2){
						int j=1;
						out.println("<table border='0' width='80%' class='table' cellspacing='0' cellpadding='0'>");
						//out.println("<tr><td width='60%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >Guarantor</td></tr>");

						while(more2){
						
						
						//out.println("<tr><td width='60%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >("+j+")&nbsp;"+rs2.getString(1)+"</td></tr>");
						//out.println("<tr><td width='60%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >&nbsp;&nbsp&nbsp;&nbsp;"+rs2.getString(2)+"</td></tr>");
						//out.println("<tr><td width='60%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >&nbsp;&nbsp&nbsp;&nbsp;"+rs2.getString(3)+"</td></tr>");
						//out.println("<tr><td width='60%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >&nbsp;&nbsp&nbsp;&nbsp"+rs2.getString(4)+"</td></tr>");
						//out.println("<tr><td width='60%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");
						//out.println("<tr><td width='60%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >&nbsp;</td></tr>");


						
						j=j+1;
						more2=rs2.next();
						}
						
						out.println("</table>");
						}
						out.println("<BR><BR>");
						
						
						rs2.close();
						stmt2.close();
						
						stmt3 = conn.createStatement ();
						stmt5 = conn.createStatement ();
						stmt6 = conn.createStatement ();
						
						
						out.println("<table border='0' width='80%' class='table'>"); 	
						out.println("<tr><td width='30%' class='rep-body1' style='{text-align:left}' >Dear Sir,</td>");
						out.println("<tr>");
						out.println("<tr><td width='30%' class='rep-body1' style='{text-align:left}' >&nbsp</td>");
						out.println("<tr>");
						out.println("<tr><td width='30%' class='rep-body1' style='{text-align:left}' ><b><u>INSURANCE DETAILS</b></u></td>");
						out.println("<tr>");
						out.println("<tr><td width='30%' class='rep-body1' style='{text-align:left}' >&nbsp</td>");
						out.println("<tr>");
						out.println("<tr><td width='30%' class='rep-body1' style='{text-align:left}' >Lease Agreement No</td>");
						out.println("<td width='4%' class='rep-body1' style='{text-align:left}'>:</td>");
						out.println("<td width='*%' class='rep-body1' style='{text-align:left}'>"+m_finance_no+"</td></tr>");
						
						out.println("<tr><td width='30%' class='rep-body1' style='{text-align:left}'>Vehicle No</td><td width='4%' style='{text-align:left}'>:</td>");
						
						String get_vehicle_no = " select NVL(VEHICLE_NO,'.....................') "+
						                        " from "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
						                        " where APPLICATION_NO = '"+m_application_no+"' ";
						rs5 = stmt5.executeQuery(get_vehicle_no);
						boolean more5 = rs5.next();
						if(more5){
							vehicle_no = rs5.getString(1);
							out.println("<td width='*%' class='rep-body1' style='{text-align:left}'>"+vehicle_no+"</td></tr>");
						}
						
						out.println("</table>");
						
						String sql_make= " SELECT '','','', "+
	" MODEL_CODE,NVL(MODEL_DESC,' '),YEAR_OF_MANUFACTURE,PRICING_NO,SUB_MODEL_CODE,INVOICE_NO "+
	" FROM "+
	" (SELECT NVL(B.REG_NO,'-') REG_NO, "+
	" nvl(B.ENGINE_NO,'-') ENGINE_NO, "+
	" nvl(B.CHASSIS_NO,'-') CHASSIS_NO, "+
	" B.MODEL_CODE, "+
	//" INITCAP(C.MAKE_DESC||' '||F.DESCRIPTION||' '||D.DESCRIPTION ||' '||H.DESCRIPTION) MODEL_DESC, "+
	" INITCAP(C.MAKE_DESC||' '||D.DESCRIPTION||' '||H.DESCRIPTION) MODEL_DESC, "+ // modified by nuwan de silva on 12-12-2007
	" NVL(D.YEAR_OF_MANUFACTURE,'') YEAR_OF_MANUFACTURE, "+
	" B.PRICING_NO, "+
	" NVL(B.SUB_MODEL_CODE,' ') SUB_MODEL_CODE, "+
  " INITCAP(H.DESCRIPTION) ITEM_SUB_CAT_DESC, "+
	" C.MAKE_CODE, "+
	" F.ITEM_SUB_CAT, "+
	" UPPER(E.VENDOR_CODE), "+
	" UPPER(E.BRANCH), "+
	" INITCAP(G.NAME), "+
	" UPPER(B.INVOICE_NO) INVOICE_NO "+
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
	" B.MODEL_CODE=F.MODEL_CODE ) "+
	" GROUP BY PRICING_NO,MODEL_CODE,MODEL_DESC,YEAR_OF_MANUFACTURE,SUB_MODEL_CODE,INVOICE_NO ";                           
		
						rs3=stmt3.executeQuery("SELECT "+m_schema_name+".AF_CO_MAS_ASSET_DESC('"+m_application_no+"') FROM DUAL");
						boolean more3=rs3.next();
						if(more3){
						m_make_desc=rs3.getString(1);
						}
						
						
						out.println("<table border='0' width='80%' class='table'>"); 		
						//out.println("<tr><td width='13%' class='rep-body1' style='{text-align:left}' >Asset Details</td>");
						//out.println("<td width='8%' class='rep-body1' style='{text-align:left}'>:</td>");
						//out.println("<td width='*%' class='rep-body1' style='{text-align:left}'>"+m_make_desc+"</td></tr>");
						//out.println("<tr><td width='20%' class='rep-body1' style='{text-align:left}'>Assets Details</td><td width='5%' style='{text-align:center}'>:</td>></tr>");
						out.println("</table>");
						
				String get_revenue_date = " select NVL(TO_CHAR(REVENUE_LICENSE_DATE,'DD-MM-YYYY'),'...........................') "+
												  " from "+m_schema_name+".AF_RE_RENEWAL_INSUARANCE "+
												  " where FINANCE_NO = '"+m_finance_no+"' "+
												  " AND VEHICLE_NO = '"+vehicle_no+"' ";
												
						out.println("<br>");
						// Commented by Dineth on 2008-08-27
						/*String data100;
						rs6 = stmt6.executeQuery(get_revenue_date);
						boolean more6 = rs6.next();
						if(more6){
							data100=rs6.getString(1);
						}
						else{
						data100="................................";
						}*/
						m_data_3="This is to inform you that the insurance policy on the above vehicle was due for renewal on "+
						          " <input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> "+
											" <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> "+
										  " <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onblur=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> "+
						          "as per our records. As you aware it is very essential that you to submit the insurance policy in order to safeguard possible unexpected losses.";
						out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' style='{text-align:left}'>"+m_data_3+"</td>");
						
						out.println("</tr>");
						out.println("</table>");
						
						out.println("<br>");
						
						m_data="We are kindly requesting you to send a copy of the insurance certificate and the schedule /renewal endorsement to Lakderana Investments Limited to make sure the insurance cover is in force.";


						out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'>"+m_data+"</td></tr>");
						out.println("</table>");
						
						
						out.println("<br>");
						
						m_data="If you are unable to send a copy of the above documents within 15 Days of this letter we are reluctantly compelled to debit your lease account and do the insurance for the mutual benefit.";
						out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'>"+m_data+"</td></tr>");
						out.println("</table>");
						
						out.println("<br>");
						
						m_data="If you need further clarification on this matter please do not hesitate to contact:";
					  out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'>"+m_data+"</td></tr>");
						out.println("</table>");
						
						out.println("<br>");
						
						m_data_1 = "Mr.Damitha Girihagama ";
						m_data_2 = "Snr.Manager Insurance 5577577/0777598205";
						out.println("<table border='0' width='50%' class='table'>");
						out.println("<tr><td class='rep-body1' width='30%' style='{text-align:left}'>"+m_data_1+"</td><td class='rep-body1' width='50%' style='{text-align:left}'>"+m_data_2+"</td></tr>");
						out.println("</table>");
						
						//ut.println("<br>");
						
						m_data_1 = "Mr.Pubudu Algama  ";
						m_data_2 = "Assistant Manager Insurance 5577577/0773136568";
						out.println("<table border='0' width='50%' class='table'>");
						out.println("<tr><td class='rep-body1' width='30%' style='{text-align:left}'>"+m_data_1+"</td><td class='rep-body1' width='50%' style='{text-align:left}'>"+m_data_2+"</td></tr>");
						out.println("</table>");
						
						out.println("<br>");
						
						m_data="If you have done the insurance through Lakderana Investments Limited please ignore this letter";
					  out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'>"+m_data+"</td></tr>");
						out.println("</table>");
						
						out.println("<br>");
						
						m_data="This is a computer-generator statement. Signature is not required.";
					  out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'>"+m_data+"</td></tr>");
						out.println("</table>");
						
						/*m_data="Yours faithfully,";
						out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'>"+m_data+"</td></tr>");
						out.println("</table>");
						stmt4=conn.createStatement();
						rs4 = stmt4.executeQuery(" SELECT "+
							" INITCAP(NVL(UPPER(COMPANY_NAME),' ')), "+
							" NVL(UPPER(ADDRESS1),' '), "+
							" NVL(UPPER(ADDRESS2),' '), "+
							" NVL(UPPER(CITY),' '), "+
							" NVL(TEL_NO,' '), "+
							" NVL(FAX_NO,' '),  "+
							" NVL(VAT_RATE,0) "+
							" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
				
						boolean more5 = rs4.next();	
						
						if(more5)
						{
							m_orient_name=rs4.getString(1);
							m_orient_add1=rs4.getString(2);
							m_orient_add2=rs4.getString(3);
							m_orient_city_name=rs4.getString(4);
							m_orient_tel_no=rs4.getString(5);
							m_orient_fax_no=rs4.getString(6);
							m_orient_vat_rate=rs4.getString(7);			
						}
						
						out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'><b>"+m_orient_name+"</b></td></tr>");
						out.println("</table>");
						
						out.println("<br><br><br>");
						
						m_data="Damitha Girihagama";
						out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'><b>"+m_data+"</b></td></tr>");
						out.println("</table>");
						
						m_data="Senior Manager - Insurance ";
						out.println("<table border='0' width='80%' class='table'>");
						out.println("<tr><td class='rep-body1' width='100%' style='{text-align:left}'>"+m_data+"</td></tr>");
						out.println("</table>");
						
						
					*/
						
						out.println("</blockquote></blockquote>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

			
				}else if(m_chksql.trim().equals("save_data")){		//Added By SANDUN on 21-10-2008		
				
				try {
				String m_invoice_no     = req.getParameter("INV_NO");
				String m_finance_no     = req.getParameter("FIN_NO");
				String m_client_no      = req.getParameter("CLIENT_NO");
				String m_application_no = req.getParameter("APP_NO");
				String m_due_date       = req.getParameter("DUE_DATE");
				String m_policy_no      = req.getParameter("POLICY_NO");
				
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_IS_LTTER_PRNT_STATUS_SAVE(:1,:2,:3,:4,:5,:6); END;");
				callstmt.setString(1,m_invoice_no);      
				callstmt.setString(2,m_finance_no);
				callstmt.setString(3,m_due_date);
				callstmt.setString(4,"PRINT");
				callstmt.setString(5,m_policy_no);
				callstmt.setString(6,m_username);
				callstmt.execute();		
				
				callstmt.close();
				
		   	out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");				
				out.println("m_url='"+m_class_url+"/"+m_schema_name+"_AF_RE_Letters_done_by_others?chksql=main_page&APP_NO="+m_application_no+"&FIN_NO="+m_finance_no+"&CLIENT_NO="+m_client_no+"&DUE_DATE="+m_due_date+" ';");  
			  out.println("window.open(m_url,'displayWindow4','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");	
				out.flush();
				}
					catch (Exception E) {
			  	try{conn.rollback();}catch(Exception e){}
				out.println("ERROR:"+E.toString());
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert('Error with generating letter..!');");
				out.println("window.history.back();"); 
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				out.flush();							
		}
		}
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(rs!=null){try{rs.close();  }catch(Exception e){}}
				if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
				if(conn!=null){try{conn.close();  }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
				}
				
			}
}


