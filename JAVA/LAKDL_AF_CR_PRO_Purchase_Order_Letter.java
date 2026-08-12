//ID         :
//SCREEN NAME:PURCHASE ORDER LETTER
//CREATED BY :Nuwan De Silva	
//DATE/TIME  : 19-JUL-2006
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;



public class LAKDL_AF_CR_PRO_Purchase_Order_Letter extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt;
//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
  public ResultSet rs;
 // public ResultSet rs1,rs2,rs4,rs5,rs6,rs7,rs8;
   
	public String m_chksql,m_html_client_url,reqstr;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
		// 	BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
	//		reqstr = input.readLine();   	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_username =  m_sn_methods.username;
			String m_full_name="";
			String m_add1="";
			String m_add2="";
			String m_city_name="";
			String m_title="";
			String data="";
			String m_address1="";
			String m_address2="";
			String m_address_name="";
			String m_approved_user="";
			
			String co_m_full_name="";
			String co_m_add1="";
			String co_m_add2="";
			String co_m_city_name="";
			
			String co_m_address="";
			String co_m_address_name="";
			String co_m_address1="";
			String co_m_address2="";
			
			//array array_to_deliverd = new array(); 
			//int i;
		//	String m_chksql;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
		// out.println("conn"+conn);
		
		 //Declare String Variables----------------------------
		  String m_orient_name="",m_orient_add1="",m_orient_add2="",m_orient_city_name="",m_orient_tel_no="",m_orient_fax_no="",m_orient_vat_rate="",m_company_reg_no="";
			String m_address="",m_type="",m_finance_no="";
			String m_date_dd="",m_date_mm="",m_date_yy="";
			String m_ven_name="",m_ven_add="",m_ven_city="";
			//---------------------------------------------------
			m_chksql=req.getParameter("chksql");
			
			stmt = conn.createStatement ();
			//stmt2 = conn.createStatement ();
			//stmt3 = conn.createStatement ();			
			//stmt4 = conn.createStatement ();			
			//stmt5 = conn.createStatement ();				
			//stmt6 = conn.createStatement ();							
			//stmt7 = conn.createStatement ();										
			//stmt8 = conn.createStatement ();			
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
								
			else if (m_chksql.trim().equals("generatereport")) {
			  
			 synchronized(this){
		    String m_pur_ord_no	  =req.getParameter("pur_ord_no");		
				String m_app_no	      =req.getParameter("app_no");		
				String m_vendor_code	=req.getParameter("vendor_code");		
				String m_status       =req.getParameter("status");
				String m_print=req.getParameter("print");
				String m_branch_code  =req.getParameter("branch_code");
				String m_sts = "";
				
				String path="ss";
				
			
				//modified nuwan de silva 12-07-07---------------------------------------
					 rs = stmt.executeQuery(" SELECT "+
					    " NVL(UPPER(COMPANY_NAME),' '), "+
					    " NVL(UPPER(ADDRESS1),' '), "+
					    " NVL(UPPER(ADDRESS2),' '), "+
					    " NVL(UPPER(CITY),' '), "+
					    " NVL(TEL_NO,' '), "+
					    " NVL(FAX_NO,' '),  "+
							" NVL(VAT_RATE,'0'), "+
							" REG_NO "+
							" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");

							 boolean more = rs.next();		
											
											if(more){
											m_orient_name=rs.getString(1);
											m_orient_add1=rs.getString(2);
											m_orient_add2=rs.getString(3);
											m_orient_city_name=rs.getString(4);
											m_orient_tel_no=rs.getString(5);
											m_orient_fax_no=rs.getString(6);
											m_orient_vat_rate=rs.getString(7);			
											m_company_reg_no=rs.getString(8);			
											}
											
								//--Close the Result Set And Stateement--------			
								rs.close();
								stmt.close();
								//---------------------------------------------
								//--Create The Statement----------------------
								stmt = conn.createStatement ();
                //--------------------------------------------
					
				//---------------------------------------------------------------------------
					//Added By Sandun on 15-06-2009
					
					rs=stmt.executeQuery (" SELECT PRINTED_STATUS,APPROVED_USER "+
					" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
					" WHERE PURCHASE_ORDER_NO='"+m_pur_ord_no+"'");
					boolean more1 = rs.next();

					//if(req.getParameter("status")==null){
					if(more1){
			  		m_sts=rs.getString(1);
						m_approved_user=rs.getString(2);
			    }					
					if(m_sts.equals("Y")){
					  m_status="COPY";
					}	else if(m_sts.equals("N")){
						m_status="ORIGINAL";
					}
					
				/*}else{
						m_status=req.getParameter("status");
					}
				*/
				
				//----------------------------------------------------------------------------
			  out.println("<html><head>"); 
				out.println("<title>Purchase Order Letter </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
	
			out.println("<script>");
			out.println("function save_data(){");
			//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Purchase_Order_View_Letter?chksql=save_page&scr_name=AF_CR_PRO_PURCHASE_ORDER&vendor_code="+m_vendor_code+"&status="+m_status+"&app_no="+m_app_no+"&print="+m_print+"&pur_ord_no="+m_pur_ord_no+"&branch_code="+m_branch_code+"\";"); 
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Purchase_Order_View_Letter?chksql=save_page&scr_name=AF_CR_PRO_PURCHASE_ORDER&vendor_code="+m_vendor_code+"&status="+m_status+"&app_no="+m_app_no+"&print="+m_print+"&pur_ord_no="+m_pur_ord_no+"&scr_type=PUR_ORD&branch_code="+m_branch_code+"\";"); //Modified by Sandun on 15-06-2009
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
			
				out.println("<body leftmargin='0' topmargin='0' class=body bgcolor='white' onLoad=\"add_button()\">");
			  
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\" class='rep-body1'  ><DIV ID='m_table'></DIV></td>");
			out.println("</tr>"); 
			out.println("</table>");
			
			/*****
			out.println("<table border='0' width='100%' class='table' align='center'>"); 
			out.println("<tr><td width='*%' class='rep-body' align='center'>SUPPLIER AGREEMENT/CONFIRMED PURCHASE ORDER - "+m_status+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body' align='center'><b>"+m_orient_name.toUpperCase()+"</b></td></tr>");
			out.println("<tr><td width='*%' class='rep-body' align='center'><b>"+m_orient_add1+", "+m_orient_add2+", "+m_orient_city_name+".</b></td></tr>");
			out.println("<tr><td width='*%' class='rep-body' align='center'><b>T.P "+m_orient_tel_no+" Fax "+m_orient_fax_no+" Co.Reg no "+m_company_reg_no+" </b></td></tr>");
			out.println("</table>");
			*****/
			out.println("<table border='0' bordercolor='black' cellspacing='0' width='100%' class='table' align='center' height='100px'>"); 
			out.println("<tr><td width='*%' class='rep-body' align='right'><b>"+m_status+"</b></td></tr>");
			out.println("</table>");

			out.println("<form name='Form1'>");
			out.println("<br>");
			
			rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD'), "+
																	"TO_CHAR(SYSDATE,'MM'), "+
																	"TO_CHAR(SYSDATE,'YYYY') "+
																	"FROM DUAL ");
			more = rs.next();
			if(more){
			m_date_dd=rs.getString(1);
			m_date_mm=rs.getString(2);
			m_date_yy=rs.getString(3);
			}
			//--Close the Result Set And Stateement--------			
			rs.close();
			stmt.close();
			//---------------------------------------------
			//--Create The Statement----------------------
			stmt = conn.createStatement ();
			//--------------------------------------------
								
			
//				out.println("more3" +more3);
			rs = stmt.executeQuery (" SELECT "+
                                " NVL(UPPER(B.NAME), ' ' ), "+
                                " NVL(UPPER(A.ADDRESS), ' '), "+
																//" CITY_CODE "+
																" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)), ' ' ) "+
                                " FROM "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION A, "+m_schema_name+".AF_CO_MAS_VENDORS B "+
																" WHERE UPPER(A.VENDOR_CODE)=UPPER(B.VENDOR_CODE) AND UPPER(A.VENDOR_CODE) =UPPER('"+m_vendor_code+"') AND UPPER(A.BRANCH)=UPPER('"+m_branch_code+"') ");
								
			more = rs.next();
			if(more){
			m_ven_name=rs.getString(1);
			m_ven_add=rs.getString(2);
			m_ven_city=rs.getString(3);
			}
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//--------------------------------------------
			  String m_ter_type  = "";
			  String m_ter_date  = "";
			
				rs = stmt.executeQuery(" SELECT "+
				" NVL(UPPER(DECODE(B.CLIENT_TYPE,'I',INITCAP(B.TITLE) || '. ' || B.FULL_NAME,'C','Mess' || '. '  || B.FULL_NAME)), '-' ), "+ //3
				" UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',B.ADDRESS1,'C',B.REGISTERED_ADDRESS1),'-')), "+//4 //REGISTERED_ADDRESS1 //modified by nuwan de silva on 29-06-2010
				" UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',B.ADDRESS2,'C',B.REGISTERED_ADDRESS2),'-')), "+//5 //REGISTERED_ADDRESS2 //modified by nuwan de silva on 29-06-2010
				//" UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)), '-' ),'C',NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(REGISTERED_CITY_CODE)), '-' )),'-')), "+//5 //REGISTERED_ADDRESS2
				" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)), '-' ) , "+
				" B.CLIENT_TYPE ,"+
				" A.FINANCE_NO, "+
				" NVL(A.TER_TYPE,'-'), "+
				" NVL(TO_CHAR(A.TER_TYPE_ENT_DATE,'DD-MM-YYYY'),'-') "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
				" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
				" UPPER(A.APPLICATION_NO)=UPPER('"+m_app_no+"')");
			  more = rs.next();		
			  if(more){
				m_full_name=rs.getString(1);
				m_add1=rs.getString(2);
				m_add2=rs.getString(3);
				m_city_name=rs.getString(4);
				m_type=rs.getString(5);
				m_finance_no=rs.getString(6);
			  m_ter_type  = rs.getString(7);
				m_ter_date  = rs.getString(8);
				}
			
				if(!m_full_name.equals("-") && !m_add1.equals("-") && !m_add2.equals("-") && !m_city_name.equals("-") ){
				//m_full_name=m_full_name+",";
				m_address=m_full_name+","+m_add1+","+m_add2+","+m_city_name;
				m_address_name=m_full_name;
				m_address1=m_add1+","+m_add2;
				m_address2=m_city_name;
				}
				else if(!m_full_name.equals("-") && !m_add1.equals("-") && !m_add2.equals("-") && m_city_name.equals("-") )	{
				m_address=m_full_name+","+m_add1+","+m_add2;
				m_address_name=m_full_name;
				m_address1=m_add1+","+m_add2;
				m_address2=" ";
				}
				else if(!m_full_name.equals("-") && !m_add1.equals("-") && m_add2.equals("-") && !m_city_name.equals("-") ){
				m_address=m_full_name+","+m_add1+","+m_city_name;	
				m_address_name=m_full_name;
				m_address1=m_add1;
				m_address2=m_city_name;
				}
				else if(!m_full_name.equals("-") && m_add1.equals("-") && m_add2.equals("-") && !m_city_name.equals("-") ){
				m_address=m_full_name+","+m_city_name;
				m_address_name=m_full_name;
				m_address1=m_add1+","+m_add2;
				m_address2=" ";
				}
				else if(!m_full_name.equals("-") && m_add1.equals("-") && m_add2.equals("-") && m_city_name.equals("-") )	{
				m_address=m_full_name;
				m_address_name=m_full_name;
				m_address1=" ";
				m_address2=" ";
				}
				
				//co-applicant added by ns on 22-09-2011
								rs = stmt.executeQuery(" SELECT "+
				" NVL(UPPER(DECODE(B.CLIENT_TYPE,'I',INITCAP(B.TITLE) || '. ' || B.FULL_NAME,'C','Mess' || '. '  || B.FULL_NAME)), '-' ), "+ //3
				" UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',B.ADDRESS1,'C',B.REGISTERED_ADDRESS1),'-')), "+//4 //REGISTERED_ADDRESS1 //modified by nuwan de silva on 29-06-2010
				" UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',B.ADDRESS2,'C',B.REGISTERED_ADDRESS2),'-')), "+//5 //REGISTERED_ADDRESS2 //modified by nuwan de silva on 29-06-2010
				//" UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)), '-' ),'C',NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(REGISTERED_CITY_CODE)), '-' )),'-')), "+//5 //REGISTERED_ADDRESS2
				" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)), '-' ) , "+
				" B.CLIENT_TYPE ,"+
				" A.FINANCE_NO, "+
				" NVL(A.TER_TYPE,'-'), "+
				" NVL(TO_CHAR(A.TER_TYPE_ENT_DATE,'DD-MM-YYYY'),'-') "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
				" WHERE A.CO_APPLICANT=B.CLIENT_CODE AND "+
				" UPPER(A.APPLICATION_NO)=UPPER('"+m_app_no+"')");
			  more = rs.next();		
			  if(more){
				co_m_full_name=rs.getString(1);
				co_m_add1=rs.getString(2);
				co_m_add2=rs.getString(3);
				co_m_city_name=rs.getString(4);
				
							
			
				if(!co_m_full_name.equals("-") && !co_m_add1.equals("-") && !co_m_add2.equals("-") && !co_m_city_name.equals("-") ){
				//m_full_name=m_full_name+",";
				co_m_address=" And "+co_m_full_name+","+co_m_add1+","+co_m_add2+","+co_m_city_name;
				co_m_address_name=co_m_full_name;
				co_m_address1=co_m_add1+","+co_m_add2;
				co_m_address2=co_m_city_name;
				}
				else if(!co_m_full_name.equals("-") && !co_m_add1.equals("-") && !co_m_add2.equals("-") && co_m_city_name.equals("-") )	{
				co_m_address=" And "+co_m_full_name+","+co_m_add1+","+co_m_add2;
				co_m_address_name=co_m_full_name;
				co_m_address1=co_m_add1+","+co_m_add2;
				co_m_address2=" ";
				}
				else if(!co_m_full_name.equals("-") && !co_m_add1.equals("-") && co_m_add2.equals("-") && !co_m_city_name.equals("-") ){
				co_m_address=" And "+co_m_full_name+","+co_m_add1+","+co_m_city_name;	
				co_m_address_name=co_m_full_name;
				co_m_address1=co_m_add1;
				co_m_address2=co_m_city_name;
				}
				else if(!co_m_full_name.equals("-") && co_m_add1.equals("-") && co_m_add2.equals("-") && !co_m_city_name.equals("-") ){
				co_m_address=" And "+co_m_full_name+","+co_m_city_name;
				co_m_address_name=co_m_full_name;
				co_m_address1=co_m_add1+","+co_m_add2;
				co_m_address2=" ";
				}
				else if(!co_m_full_name.equals("-") && co_m_add1.equals("-") && co_m_add2.equals("-") && co_m_city_name.equals("-") )	{
				co_m_address      = " And "+co_m_full_name;
				co_m_address_name = co_m_full_name;
				co_m_address1     = " ";
				co_m_address2     = " ";
				}
				}
				
		
				
				if(m_type.equals("I")){
				
									out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class='table'> "+
											" <tr> "+
											" <td width=\"60%\" class='rep-body1' align=left><b>"+m_ven_name+"</td> "+
											" <td width=\"12%\" class='rep-body1' align=left><b>Order No </td> "+
											" <td width=\"3%\"  class='rep-body1' align=center><b>:</td> "+
											" <td width=\"25%\" class='rep-body1' align=left><b>"+m_pur_ord_no+"</td> "+
											" </tr> "+
											" <tr> "+
											" <td class='rep-body1' align=left><b>"+m_ven_add+"</td> "+
											" <td class='rep-body1' align=left><b>Ref No </td> "+
											" <td class='rep-body1' align=center><b>:</td> ");
											if(m_ter_type.equals("ENHA_DOWN") || m_ter_type.equals("RESCHEDULE")){
											out.println(" <td class='rep-body1' align=left><b>"+m_finance_no+" - "+m_ter_date+"</td> ");
											}
											else{
											out.println(" <td class='rep-body1' align=left><b>"+m_finance_no+"</td> ");
											}
											out.println(" </tr> "+
											" <tr> "+
											" <td class='rep-body1' align=left><b>"+m_ven_city+"</td> "+
											" <td class='rep-body1' align=left><b>Date</td> "+
											" <td class='rep-body1' align=center><b>:</td> "+
											" <td class='rep-body1' align=left><b>"+m_date_dd+"/"+m_date_mm+"/"+m_date_yy+"</td> "+
											" </tr> "+
											" <tr> "+
											" <td class='rep-body1' align=left><b>&nbsp;</td> "+
											" <td class='rep-body1' align=left><b>&nbsp;</td> "+
											" <td class='rep-body1' align=center><b>&nbsp;</td> "+
											" <td class='rep-body1' align=left><b>&nbsp;</td> "+
											" </tr> "+
											" </table> ");

				}
				else
				{
				
								out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class='table'> "+
											" <tr> "+
											" <td width=\"60%\" class='rep-body1' align=left><b>Messer's</td> "+
											" <td width=\"12%\" class='rep-body1' align=left><b>&nbsp;</td> "+
											" <td width=\"3%\"  class='rep-body1' align=center><b>&nbsp;</td> "+
											" <td width=\"25%\" class='rep-body1' align=left><b>&nbsp;</td> "+
										  " </tr> "+
											" <tr> "+
											" <td  class='rep-body1' align=left><b>"+m_ven_name+"</td> "+
											" <td  class='rep-body1' align=left><b>Order No </td> "+
											" <td  class='rep-body1' align=center><b>:</td> "+
											" <td  class='rep-body1' align=left><b>"+m_pur_ord_no+"</td> "+
											" </tr> "+
											" <tr> "+
											" <td class='rep-body1' align=left><b>"+m_ven_add+"</td> "+
											" <td class='rep-body1' align=left><b>Ref No </td> "+
											" <td class='rep-body1' align=center><b>:</td> ");
											if(m_ter_type.equals("ENHA_DOWN") || m_ter_type.equals("RESCHEDULE")){
											out.println(" <td class='rep-body1' align=left><b>"+m_finance_no+" - "+m_ter_date+"</td> ");
											}
											else{
											out.println(" <td class='rep-body1' align=left><b>"+m_finance_no+"</td> ");
											}
											out.println(" </tr> "+
											" <tr> "+
											" <td class='rep-body1' align=left><b>"+m_ven_city+"</td> "+
											" <td class='rep-body1' align=left><b>Date</td> "+
											" <td class='rep-body1' align=center><b>:</td> "+
											" <td class='rep-body1' align=left><b>"+m_date_dd+"/"+m_date_mm+"/"+m_date_yy+"</td> "+
											" </tr> "+
											" </table> ");
											
				}
				
				if(m_type.equals("I")){
				data="<b>Dear Sir/Madam</b>";
				out.println("<table class='table' width=\"95%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> "); 		
				out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");		
				out.println("<br>");		
				data="<b>We "+m_orient_name+" </b> hereby place our official purchase "+ 
				"order / Supply Agreement to you for the Equipment/s in connection with our Leasing arrangement with "+
				"<b>"+m_address+".&nbsp;</b>"+
				"<b>"+co_m_address+".&nbsp;</b>"+
				"<b> subject to </b>the following terms and conditions.";
				out.println("<table class='table' width=\"95%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> "); 		
				out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");		
				}	else	{
				data="<b>Dear Sir/Madam</b>";
				out.println("<table class='table' width=\"95%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> "); 		
				out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");		
				out.println("<br>");		
				data="<b>We "+m_orient_name+" </b> hereby place our official purchase "+ 
				"order / Supply Agreement to you for the Equipment/s in connection with our Leasing arrangement with "+
				"<b>"+m_address+"</b>"+
				"<b>"+co_m_address+".&nbsp;</b>"+
				"<b> subject to </b>the following terms and conditions.";
				out.println("<table class='table' width=\"95%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> "); 		
				out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
				out.println("</tr></table>");		
				}
				rs.close();
				stmt.close();
				stmt = conn.createStatement ();
				
								rs =stmt.executeQuery ("	SELECT "+
				"				NVL(UPPER(F.MAKE_DESC),' ') ||' ' ||    NVL(UPPER(D.DESCRIPTION),' ') ||' ' ||   NVL(UPPER(E.DESCRIPTION),' ' ),  "+ //1 //added by nuwan de silva on 12-12-2007 at ofscl
				"				NVL(G.ENGINE_NO,' '),  "+ //2
				"				NVL(G.CHASSIS_NO,' ') , "+ //3
				"				NVL(G.NET_PRICE,0),  "+ //4
				"				NVL(G.VAT,0),  "+ //5
				"				NVL(G.TOTAL_AMOUNT,0),  "+ //6
				"				NVL(UPPER(TO_BE_DELIVERD_TO),' '),  "+ //7
				"				NVL(UPPER(ADDRESS),' '),  "+ //8
				"				NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '), "+ //9
				"       (G.NET_PRICE-ROUND(DECODE("+m_schema_name+".AF_CO_GET_APP_TERMI_TYPE(G.APPLICATION_NO),'ENHA_DOWN',"+m_schema_name+".AF_CO_GET_TERMINATED_AMOUNT(G.APPLICATION_NO),0))) NET_PRICE, "+//10 //Add By Sandun on 05-12-2008
				"       (G.NET_PRICE + G.VAT-ROUND(DECODE(LAKDL.AF_CO_GET_APP_TERMI_TYPE(G.APPLICATION_NO),'ENHA_DOWN',LAKDL.AF_CO_GET_TERMINATED_AMOUNT(G.APPLICATION_NO),0))) GROSS_AMOUNT "+ //11
				"				FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET A, "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS B,  "+
				"				 "+m_schema_name+".AF_CO_MAS_MODEL D,"+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY E,  "+
				"				"+m_schema_name+".AF_CO_MAS_MAKE F ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS G "+
				"				WHERE A.ASSET_ID=B.ASSET_ID AND  "+
				"				D.ITEM_SUB_CAT=E.ITEM_SUB_CAT AND  "+
				"				B.MODEL_CODE=D.MODEL_CODE AND  "+
				"				D.MAKE_CODE=F.MAKE_CODE AND   "+
				"				A.PRO_INVOICE_NO=G.INVOICE_NO AND "+
				"       G.ACTIVE_STATUS='Y' AND "+
				"				UPPER(A.PURCHASE_ORDER_NO)=UPPER('"+m_pur_ord_no+"')   ");
				//modified by nuwan de silva 16-07-07------------------------------------
				  more =rs.next();
					out.println("<br> ");
					
					while (more) {	
					out.println("<table class='table' width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> "); 		
					out.println("<tr> ");
					out.println("<td width=\"3%\"  class='rep-body1' valign='top'>1.</td> ");
					out.println("<td width=\"31%\" class='rep-body1' valign='top'> Description of Equipment/s </td> ");
					out.println("<td width=\"5%\"  class='rep-body1' valign='top'><div align=\"center\">:</div></td> ");
					out.println("<td width=\"16%\" class='rep-body1' valign='top'><b> Make </b></td> ");
					out.println("<td width=\"5%\"  class='rep-body1' valign='top'><div align=\"center\">:</div></td>  ");
					out.println("<td width=\"40%\" class='rep-body1' valign='top'><b> 1 Unit of &nbsp; "+rs.getString(1)+" </b></td> ");
					out.println("</tr> ");
					out.println("<tr> ");
					out.println("<td width=\"3%\"  class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width=\"31%\" class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width=\"5%\"  class='rep-body1' valign='top'><div align=\"center\">&nbsp;</div></td> ");
					out.println("<td width=\"16%\" class='rep-body1' valign='top'><b>Engine/Serial No</b></td> ");
					out.println("<td width=\"5%\"  class='rep-body1' valign='top'><div align=\"center\">:</div></td>  ");
					out.println("<td width=\"40%\" class='rep-body1' valign='top'><b>"+rs.getString(2)+"</b> </td> ");
					out.println("</tr> ");
					out.println("<tr> ");
					out.println("<td width=\"3%\"  class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width=\"31%\" class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width=\"5%\"  class='rep-body1' valign='top'><div align=\"center\">&nbsp;</div></td> ");
					out.println("<td width=\"16%\" class='rep-body1' valign='top'><b>Chassis No</b></td> ");
					out.println("<td width=\"5%\"  class='rep-body1' valign='top'><div align=\"center\">:</div></td>  ");
					out.println("<td width=\"40%\" class='rep-body1' valign='top'><b>"+rs.getString(3)+" </b></td> ");
					out.println("</tr> ");
					out.println("</table> ");
					out.println("<br> ");
					out.println("<table class='table' width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> "); 		
					out.println("<tr> ");
					out.println("<td width=\"3%\"  class='rep-body1' valign='top'>2.</td> ");
					out.println("<td width=\"31%\" class='rep-body1' valign='top'>Purchase Price</td> "); 
					out.println("<td width=\"5%\"  class='rep-body1' valign='top'><div align=\"center\">:</div></td> ");
					out.println("<td width=\"16%\" class='rep-body1' valign='top'><b>Net Price</b> </td> ");
					out.println("<td width=\"5%\"  class='rep-body1' valign='top'><div align=\"center\">:</div></td>  ");
					//out.println("<td width=\"40%\" class='rep-body1' valign='top'><b>Rs."+nf.format(rs.getDouble(4))+"</b></td> ");
					out.println("<td width=\"2%\"  class='rep-body1' valign='top'><div align=\"left\"><b>Rs.</div></td>  ");//Commented By Sandun on 05-12-2008
					out.println("<td width=\"12%\" class='rep-body1' valign='top' align='right'><b>"+nf.format(rs.getDouble(10))+"</b></td><td width='*%'>&nbsp;</td> "); //Added By Sandun on 05-12-2008
					out.println("</tr> ");
					out.println("<tr> ");
					out.println("<td width=\"3%\"  class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width=\"31%\" class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width=\"5%\"  class='rep-body1' valign='top'><div align=\"center\">&nbsp;</div></td> ");
					out.println("<td width=\"16%\" class='rep-body1' valign='top'><b>VAT</b></td> ");
					out.println("<td width=\"5%\"  class='rep-body1' valign='top'><div align=\"center\">:</div></td>  ");
					out.println("<td width=\"2%\"  class='rep-body1' valign='top'><div align=\"left\"><b>Rs.</div></td>  ");
					out.println("<td width=\"12%\" class='rep-body1' valign='top' align='right'><b><u>"+nf.format(rs.getDouble(5))+"</u></b></td><td width='*%'>&nbsp;</td> ");
					out.println("</tr> ");
					out.println("<tr> ");
					out.println("<td width=\"3%\"  class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width=\"31%\" class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width=\"5%\"  class='rep-body1' valign='top'><div align=\"center\">&nbsp;</div></td> ");
					out.println("<td width=\"16%\" class='rep-body1' valign='top'><b>Total</b></td> ");
					out.println("<td width=\"5%\"  class='rep-body1' valign='top'><div align=\"center\">:</div></td>  ");
					//out.println("<td width=\"40%\" class='rep-body1' valign='top'><b>Rs."+nf.format(rs.getDouble(6))+"</b></td> ");
					out.println("<td width=\"2%\"  class='rep-body1' valign='top'><div align=\"left\"><b>Rs.</div></td>  ");
					out.println("<td width=\"12%\" class='rep-body1' valign='top' align='right'><b>"+nf.format(rs.getDouble(11))+"</b></td><td width='*%'>&nbsp;</td> ");//Added By Sandun on 05-12-2008
					out.println("</tr> ");
					out.println("<tr> ");
					out.println("</tr> ");
					out.println("</table> ");
					out.println("<br> ");
					out.println("<table class='table' width=\"100%\"  border=\"0\" bordercolor=\"black\" cellspacing=\"0\" cellpadding=\"0\"> "); 		
					out.println("<tr> ");
					out.println("<td width=\"3%\"  class='rep-body1' valign='top'>3.</td> ");
					out.println("<td width=\"31%\" class='rep-body1' valign='top'>Place to be delivered</td> ");
					out.println("<td width=\"5%\"  class='rep-body1' valign='top'><div align=\"center\">:</div></td> ");
					out.println("<td width=\"16%\" class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width=\"5%\"  class='rep-body1' valign='top'><div align=\"center\">&nbsp;</div></td>  ");
					out.println("<td width=\"40%\" class='rep-body1' valign='top'><b>"+m_full_name+"</b></td> ");
					out.println("</tr> ");
					out.println("<tr> ");
					out.println("<td width=\"3%\"  class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width=\"31%\" class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width=\"5%\"  class='rep-body1' valign='top'><div align=\"center\">&nbsp;</div></td> ");
					out.println("<td width=\"16%\" class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width=\"5%\"  class='rep-body1' valign='top'><div align=\"center\">&nbsp;</div></td>  ");
					out.println("<td width=\"40%\" class='rep-body1' valign='top'><b>"+m_address1+"</b></td> ");
					out.println("</tr> ");
					out.println("<tr> ");
					out.println("<td width=\"3%\"  class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width=\"31%\" class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width=\"5%\"  class='rep-body1' valign='top'><div align=\"center\">&nbsp;</div></td> ");
					out.println("<td width=\"16%\" class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width=\"5%\"  class='rep-body1' valign='top'><div align=\"center\">&nbsp;</div></td>  ");
					out.println("<td width=\"40%\" class='rep-body1' valign='top'><b>"+m_address2+"</b></td> ");
					out.println("</tr> ");
					out.println("</table> ");
			   	more=rs.next();
				}
				
				 
				  out.println("<table border='0' bordercolor='black' cellspacing='0' width='100%' class='table' align='center' height='250px'>"); 
			    out.println("<tr><td width='*%' class='rep-body' align='center'>&nbsp;</td></tr>");
			    out.println("</table>");
					String m_sign="";
					if (m_approved_user.equals("NISHAMAN")) {
					m_sign="sign1.gif";
					}else if (m_approved_user.equals("DILUM")) {
					m_sign="sign2.gif";
					}else if (m_approved_user.equals("SUSANTHA")) {
					m_sign="sign3.gif";
					}else if (m_approved_user.equals("SURANGA")) {
					m_sign="sign4.gif";
					}
					else if (m_approved_user.equals("ASINI")) { //added by ns on 23-08-2011
					m_sign="sign5.gif";
					}
					else if (m_approved_user.equals("NILANTHAJ")) { //added by thamali on 28-12-2011
					m_sign="sign6.gif";
					}
					else if (m_approved_user.equals("LOHIKA")) { //added by nuwan on 25-06-2012
					m_sign="sign7.gif";
					}

					/*else{
					m_sign="sign1.gif";
					}*/
					
					out.println("<table border='0' bordercolor='black' cellspacing='0' width='100%' class='table' align='left'>"); 
			    out.println("<tr><td width='*%' class='rep-body' align='left'><img src='"+m_html_client_url+"/"+m_sign+"'></td></tr>");
			    out.println("</table>");
			
					// the below section comment by nuwan de silva on 28-08-2010 for the new purchase order format
					/*******
					out.println("<table class='table' width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
					out.println("<tr> ");
					out.println("<td width=\"3%\"  class='rep-body1' >4.</td> ");
					out.println("<td width=\"31%\" class='rep-body1' >Other terms and conditions</td> ");
					out.println("<td width=\"5%\"  class='rep-body1' ><div align=\"center\">:</div></td> ");
					out.println("<td width=\"16%\" class='rep-body1' ><b> &nbsp; </b></td> ");
					out.println("<td width=\"5%\"  class='rep-body1' ><div align=\"center\">&nbsp;</div></td>  ");
					out.println("<td width=\"40%\" class='rep-body1' ><b>&nbsp;</td> ");
					out.println("</tr> ");
					out.println("</table>");	
			
					out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
					data="Title to the said Equipment/s shall be vested in "+m_orient_name+" "+ 
					"free from any liens and encumbrances of anyone claiming "+
					"by, through or under you with effect from the date of purchase by "+m_orient_name+"  "+  
					"of the Equipment/s.";
					out.println("<table class='table' width=\"90%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
					out.println("<tr><td width='2%' class='rep-body1' style='text-align:right' valign=top >1.</td>");
					out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");		
					
					out.println("<br>");
					data=           " "+m_orient_name+" shall receive in writing "+
					"from the Lessee (a) the Lessee's acceptance of the said Equipment/s in good order and "+
					"approval of your invoice for the same (b) the lessee's instructions "+m_orient_name+" "+
					"to pay your invoice for the said Equipment/s,and";
					out.println("<table class='table' width=\"90%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
					out.println("<tr><td width='2%' class='rep-body1' style='text-align:right' valign=top >II.</td>");
					out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");		
					
					out.println("<br>");
					data=           "You shall deliver to "+m_orient_name+" "+
					"and Lessee your written warranties in substance and in form and required by the "+
					"aforesaid purchase order/supply agreement. By acceptance hereof you agree that all "+
					"warranties written or oral, express or implied are for the benefit of and may be enforced "+
					"by both "+m_orient_name+" and Lessee or either of them.";
					out.println("<table class='table' width=\"90%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
					out.println("<tr><td width='2%' class='rep-body1' style='text-align:right' valign=top >III.</td>");
					out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");		
					out.println("<br>");
					data=           "If the equipment/s described in (1) above is a Vehicle, registration of the vehicle should "+
					"be undertaken by you. "+
					"Registered ownership should be in favour of Lessee and you should have "+m_orient_name+" "+
					"as the Absolute Owner.";
					out.println("<table class='table' width=\"90%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
					out.println("<tr><td width='2%' class='rep-body1' style='text-align:right' valign=top >IV.</td>");
					out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");		
					out.println("<br>");		
					data="In support of above you are required to forward the following RMV documents.";
					
					out.println("<table class='table' width=\"90%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
					out.println("<tr><td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");		
					out.println("</font></p>");	
					
					out.println("<blockquote><font size=2><p style='text-align:left' class='rep-body1'>");	
					data="For Motor Vehicles<br>"+
					"Original Certificate of Registration and Vehicle Identity Card (VIC)/<br>"+
					"CMT 52 receipt with "+m_orient_name+" as <br>"+
					"Absolute Owner <br>";
					out.println("<table class='table' width=\"80%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
					out.println("<tr><td width='2%' class='rep-body1' style='text-align:right' valign=top ><li></td>");
					out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");		
					data="Original Invoice/Tax Invoice<br>";
					out.println("<table class='table' width=\"80%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
					out.println("<tr><td width='2%' class='rep-body1' style='text-align:right' valign=top ><li></td>");
					out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");		
					data="Duplicate Key<br>";
					out.println("<table class='table' width=\"80%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
					out.println("<tr><td width='2%' class='rep-body1' style='text-align:right' valign=top ><li></td>");
					out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");		
					data="Original Valuation Report (In case of Used or Reconditioned)<br>";
					out.println("<table class='table' width=\"80%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
					out.println("<tr><td width='2%' class='rep-body1' style='text-align:right' valign=top ><li></td>");
					out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");		
					data="Copy of VAT Registration Certificate<br>";
					out.println("<table class='table' width=\"80%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
					out.println("<tr><td width='2%' class='rep-body1' style='text-align:right' valign=top ><li></td>");
					out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");		
					data="Luxury Tax Paying Slips";
					out.println("<table class='table' width=\"80%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
					out.println("<tr><td width='2%' class='rep-body1' style='text-align:right' valign=top ><li></td>");
					out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");		
					out.println("</font></p></blockquote></blockquote>");
						
			data="In the event the registration/transfer cannot be executed due to irregularities/discrepancies found in "+
			"the RMV forms submitted by you to the commissioner of motor traffic, you will take full responsibility "+
			"to rectify same so as to confirm to condition as stipulated in clause (iv).";
			out.println("<table class='table' width=\"95%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
			out.println("<tr><td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");	
			data="If proforma invoice/invoice does not carry equipment/ s serial numbers, payment will be released subject to:";
			out.println("<table class='table' width=\"95%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
			out.println("<tr><td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");	
			
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
			data="Receipt of Acceptance Receipt with the invoice nos.";
			out.println("<table class='table' width=\"90%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
			out.println("<tr><td width='2%' class='rep-body1' style='text-align:right' valign=top >i)</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");	
			data="Receipt of invoice with Serial Number";
			out.println("<table class='table' width=\"90%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
			out.println("<tr><td width='2%' class='rep-body1' style='text-align:right' valign=top >ii)</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");	
			data="Acceptance Receipt duly authorised by the lessee";
			out.println("<table class='table' width=\"90%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
			out.println("<tr><td width='2%' class='rep-body1' style='text-align:right' valign=top >iii)</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");	
			out.println("</font></p></blockquote>");	
			data=         "This Purchase order/Supply Agreement will be valid for 30 days from the date of issue. if you "+
			"accept our above purchase order / Supply Agreement, please indicate your acceptance by signing and "+
			"returning same to "+m_orient_name+".";
			out.println("<table class='table' width=\"95%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
			out.println("<tr><td width='2%' class='rep-body1' style='text-align:right' valign=top >V.</td>");
			out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			
			out.println("<br><br><br><br>");
			out.println("<table class='table' width=\"95%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> "+
			"<tr><td width=40% class='rep-body1' align=left><b>.............................................................................</td><td width=25% class='rep-body1'></td><td class='rep-body1' width=30% align=left><b>.............................................................................</td></tr>"+
			"<tr><td width=40% class='rep-body1' align=left><b>"+m_orient_name+"</td><td width=25%></td><td class='rep-body1' width=30% align=left class='rep-body1'><b>"+m_ven_name+"</td></tr>"+ //m_ven_name rs4.getString(1)
			"<tr><td width=40% class='rep-body1' align=left><b>(Authorised Signatory/s)</td><td width=25%></td><td width=30%  align=left  class='rep-body1' ><b>(Authorised Signatory/s)</td></tr>"+//Authorised Signature NAME CHANGED BY LALANKA ON 14-07-2009
			"<tr><td width=40% class='rep-body1' align=left><b></td><td width=25% ></td><td  width=30% align=left class='rep-body1'><b>Co. rubber stamp to be placed</td></tr>");
			out.println("</table>");			
			out.println("</font></p>");
			***/
			
			
			//This part comment by nuwan de silva on 06-07-2010 SR20100706-035
			
			/*******
			out.println("   <p style=\"page-break-after:always\"></p>");//addedby nuwan de silva on 05-07-2010
			out.println("<br><br>");			//<hr>
			out.println("<br>");			
			data="<b>To be completed by Lessee (Section I)</b><br><br>";
			out.println("<table class='table' width=\"95%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
			out.println("<tr><td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			data="To: "+m_orient_name+"<br><br>";
			out.println("<table class='table' width=\"95%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
			out.println("<tr><td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			data="We have accepted the items described in Clause (1) above, and hereby authorise you to make payments "+
			"to the supplier on following Basis.<br>";   
			out.println("<table class='table' width=\"95%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
			out.println("<tr><td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
			out.println("</tr></table>");		
			out.println("</font></p>");
			
			
			
			//--Close the Result Set And Stateement--------			
			rs.close();
			stmt.close();
			//---------------------------------------------
			//--Create The Statement----------------------
			stmt = conn.createStatement ();
			//--------------------------------------------
			
			rs =stmt.executeQuery (" SELECT DISTINCT "+
        " NVL(UPPER(TO_BE_DELIVERD_TO),' ') "+
        " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
        " WHERE  INVOICE_NO IN "+
        " (SELECT "+
        " PRO_INVOICE_NO "+
        " FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET "+
        " WHERE PURCHASE_ORDER_NO='"+m_pur_ord_no+"' AND ACTIVE_STATUS='Y') ");
				
			more=rs.next();
			while (more) {	
			

			
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
			data="Full Payment of Rs ..................................................<br>";
					out.println("<table class='table' width=\"90%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
					out.println("<tr><td width='2%' class='rep-body1' style='text-align:right' valign=top ><li></td>");
					out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");	
					out.println("<br><br>");	
			data="Part Payment of Rs .................................................. and the balance to be paid on "+
			     "the receipt of our written instructions.<br>";
					out.println("<table class='table' width=\"90%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
					out.println("<tr><td width='2%' class='rep-body1' style='text-align:right' valign=top ><li></td>");
					out.println("<td width='*%' class='rep-body1' style='text-align:justify' >"+data+"</td>");
					out.println("</tr></table>");	
			out.println("</font></p></blockquote>");
			
			out.println("<br><br><br><br>");	

			if(m_type.equals("I")){
			

			
				out.println("<table class='table' width=\"80%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> "+
											"<tr><td width=70% class='rep-body1' align=left><b> ............................................ </td><td width=10% align=center class='rep-body1' ><b> .................................. </td></tr>"+											
											"<tr><td width=70% class='rep-body1' align=left><b>"+rs.getString(1).toUpperCase()+"</td><td width=10% class='rep-body1'  align=center><b>DATE</td></tr>");										
		  out.println("</table>");			
			out.println("<br><br>");	
			out.println("<table class='table' width=\"80%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> "+
									"<tr><td width=70% class='rep-body1' align=left><b>NIC NO: ......................... </td></tr>");											
		  out.println("</table>");		
			}
			else if(m_type.equals("C")){
			
			out.println("<table class='table' width=\"80%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> "+
											"<tr><td width=70% class='rep-body1' align=left><b> .............................................................. </td><td width=10% align=center><b> .................................. </td></tr>"+											
											"<tr><td width=70% class='rep-body1' align=left><b>"+rs.getString(1)+"</td><td width=10%  align=center class='rep-body1' ><b>DATE</td></tr>"+									
											"<tr><td width=70% class='rep-body1' align=left><b>(Authorised Signatory/s)</td><td width=10%  align=center class='rep-body1' >&nbsp;</td></tr>"+	//Authorised Signature NAME CHANGED BY LALANKA ON 14-07-2009								
											"<tr><td width=70% class='rep-body1' align=left><b>Co. rubber stamp to be placed</td><td width=10%  align=center class='rep-body1' >&nbsp;</td></tr>");									

		  out.println("</table>");			
			out.println("<br><br>");	
			out.println("<table class='table' width=\"80%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> "+
											"<tr><td width=70% class='rep-body1' align=left><b>Business Registration Number: ......................... </td></tr>");											
		  out.println("</table>");			
			
			}
			more=rs.next();
			}
			******/
			
			out.println("</form></body></html>");
			}
			
			}//end 
			
			out.flush();
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
