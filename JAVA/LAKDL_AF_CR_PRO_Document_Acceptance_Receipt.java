//ID         :
//SCREEN NAME:Document Printing - Acceptance_Receipt
//CREATED BY :Nuwan De Silva	
//DATE/TIME  : 07-02-2006
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Document_Acceptance_Receipt extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt_invoice;
//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
  public ResultSet rs,rs_invoice;
 	

	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print,m_start_date;
	public double m_amount_due;
	public String m_master_lease_no,m_co_app="";
	
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
		 int m_data_count=0;
		 String m_status ="";
			
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			String m_item_code="";
			String m_item_desc="";
			
		
		  String m_chksql = req.getParameter("chksql");
			//String m_application_no = req.getParameter("application_no");
			
  	 if(m_chksql.trim().equals("main_page")){
				
			stmt = conn.createStatement ();
			stmt_invoice = conn.createStatement ();

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
				
				
				
				rs = stmt.executeQuery (	" SELECT "+
  		  //" NVL(UPPER(FULL_NAME),' ') "+ //INITCAP MODIFIED BY Chandana on 04/07/2007
				" DECODE(CLIENT_TYPE,'I',NVL(UPPER(TITLE),' ')||'. '||NVL(UPPER(FULL_NAME),' '),'C',NVL(UPPER(FULL_NAME),' ')) "+ //MODIFIED BY Chandana on 26/07/2007
  	    " FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
  	    " WHERE CLIENT_CODE='"+m_client_code+"' ");
		
					
			more = rs.next();
			if(more){

			m_name=rs.getString(1);
					}
					
			
			//added by nuwan de silva on 04-09-07		
			String sql_co_app=" SELECT  "+
			/*" DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C','MESS' || '. '  || UPPER(FULL_NAME)),   "+
			" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS1),'C',UPPER(REGISTERED_ADDRESS1)),' '),  "+
			" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS2),'C',UPPER(REGISTERED_ADDRESS2)),' ') , "+
			" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+
			" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-')   "+
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
			" WHERE   CLIENT_CODE =  "+
			" (SELECT  "+*/
		  " NVL(CO_APPLICANT,'-')  "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
			" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"')";
			rs = stmt.executeQuery (sql_co_app);
			
			more = rs.next();
			if(more){
			m_co_app=rs.getString(1);
					}


        rs=stmt.executeQuery (" SELECT "+
        "  NVL(FINANCE_NO,'-'), "+
				"  TO_CHAR(ACTIVATED_DATE, 'fmddth') || ' day of ' || TO_CHAR(ACTIVATED_DATE, 'Month')|| TO_CHAR(ACTIVATED_DATE, 'YYYY') START_DATE,nvl(MASTER_AGREEMENT_NO,'-') "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
        " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
				
				more = rs.next();
			
			if(more){

			  m_finance_no=rs.getString(1);
				m_start_date=rs.getString(2);
				m_master_lease_no=rs.getString(3);
			  
			  }
				
				
					 rs = stmt.executeQuery(" SELECT "+
					    " NVL(UPPER(COMPANY_NAME),' '), "+
					    " NVL(UPPER(ADDRESS1),' '), "+
					    " NVL(UPPER(ADDRESS2),' '), "+
					    " NVL(CITY,' '), "+
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
											
			String sql=" SELECT "+
  //  " E.TITLE, "+
    " UPPER(g.name),  "+//1
    " '',  "+//2
    " NVL(UPPER(E.ADDRESS),' '),  "+//3
    //" NVL(UPPER(e.city_code),' ') , "+//4 //Modified by Chandana for Ref No.716 on 26/07/2007
		" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(e.city_code)),' '), "+//4 
    " C.MAKE_CODE, "+//5
		" B.MODEL_CODE, "+//6
		" F.ITEM_SUB_CAT, "+//7
    " nvl(B.ENGINE_NO,'-'), "+//8
    " nvl(B.CHASSIS_NO,'-'), "+//9
		" UPPER(E.VENDOR_CODE), "+//10
		" UPPER(E.BRANCH), "+//11
		" UPPER(G.NAME), "+//12 //modified by nuwan de silva 
		" UPPER(C.MAKE_DESC) MAKE_DESC , "+//13 //modified by nuwan de silva 
  //  " UPPER(F.DESCRIPTION||' '||D.DESCRIPTION) MODEL_DESC, "+//14 //modified by nuwan de silva  //comment by nuwan de silva on 12-12-2007 at ofscl
	  " UPPER(D.DESCRIPTION) MODEL_DESC, "+//14 //modified by nuwan de silva    //added by nuwan de silva on 12-12-2007 at ofscl
    " UPPER(H.DESCRIPTION) ITEM_SUB_CAT_DESC, "+//15 //modified by nuwan de silva 
		" UPPER(NVL(B.REG_NO,'-')), "+ //16 modified by nuwan de silva  ON 18-07-07
		" NVL(H.ITEM_CAT_CODE,'-') "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
    " "+m_schema_name+".AF_CO_MAS_MAKE C,"+m_schema_name+".AF_CO_MAS_SUB_MODLE D,"+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION E,"+m_schema_name+".AF_CO_MAS_MODEL F ,"+m_schema_name+".AF_CO_MAS_VENDORS G ,"+
		" "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY H  "+
		
    " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
    " A.ACTIVE_STATUS='Y' AND "+
	  " B.ACTIVE_STATUS='Y' AND "+

    " A.APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
    " A.ASSET_ID=B.ASSET_ID AND "+
    " C.MAKE_CODE= "+
    "   (SELECT  "+
    "  MAKE_CODE   "+
    "  FROM "+m_schema_name+".AF_CO_MAS_MODEL  "+
    "  WHERE   "+
    "  MODEL_CODE IN (  "+
    "  SELECT  "+
    "  MODEL_CODE  "+ 
    "  FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+ 
    "  WHERE INVOICE_NO=B.INVOICE_NO AND B.ACTIVE_STATUS='Y' "+		
    "  )) AND "+
    "  D.SUB_CODE=B.SUB_MODEL_CODE AND "+
    "  UPPER(E.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
    "  UPPER(G.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
		"  UPPER(F.ITEM_SUB_CAT) =UPPER(H.ITEM_SUB_CAT) AND  "+
		
		//----------------------------------------------------------------------------------------------------
		//--Modified by : delanjali---------------------------------------------------------------------------
		//--Date 				: 2007-06-05--------------------------------------------------------------------------
		//--Reason			: error occur for some records (null pointer exception)-------------------------------

    "  UPPER(E.BRANCH)=UPPER(B.BRANCH_ID) AND  "+
		//----------------------------------------------------------------------------------------------------

		"  B.MODEL_CODE=F.MODEL_CODE ";
		//"  AND B.ENT_DATE IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS GROUP BY ASSET_ID)"; //Added By Sandun on 05-12-2008
		
	
				//out.println("va"+m_data_count+"");
				//out.println("status"+m_status+"");
				
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
       
			
				
	/*			
			rs2 = stmt2.executeQuery (" SELECT "+
			" AMOUNT_DUE, "+
			" TO_CHAR(INVOICE_DATE,'DD-MM-YYYY'), "+
			" NVL(FINANCE_NO,'-') "+
			" FROM "+m_schema_name+".AF_RE_PRO_RPT_COLLECTION_DUE "+
			" WHERE  INVOICE_NO=UPPER('"+m_invoice_no+"') AND  CLIENT_CODE=UPPER('"+m_client_no+"') ");


			more = rs2.next();
			if(more){
			m_amount_due=rs2.getDouble(1);
			m_due_date  =rs2.getString(2);
			m_finance_no =rs2.getString(3);
			}
			
				*/
			out.println("<br><br><br>"); 
			out.println("<font size=3><p style='text-align:left'>");					

			out.println("<table align='center' width='100%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"100%\" class='rep-body1'><b></b></td></tr>");
		  out.println("</table>");

			out.println("</font></p>");	
			out.println("<font size=3><p style='text-align:center'>");				
			out.println("<table border='0' width='100%' class='table' align='center' cellspacing='0' cellpadding='0'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' align='center'>TO :<B> "+m_orient_name.toUpperCase()+" </td></tr>");
			out.println("<tr></TR>");
			out.println("<tr><td width='*%' class='rep-body1' align='center'>ACCEPTANCE RECEIPT</td></tr>");
		  out.println("</table>");
			out.println("</font></p></center>");
			out.println("<font size=2><p style='text-align:left'>");				
			out.println("<table border='0' width='100%' class='table' cellspacing='0' cellpadding='0'>"); 		
			out.println("<tr><td width='60%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >Ref No. <b>"+m_master_lease_no+"</b></td></tr>");
			out.println("<tr><td width='60%' class='rep-body1' >&nbsp;</td><td width='*%' class='rep-body1' >Schedule No.<b>"+m_finance_no+"</b></td></tr>");
			out.println("</table>");
			out.println("<BR><BR>");
			
			int i=1;
//======================================================================		
      out.println("<table border='0' width='100%' class='table' cellspacing='0' cellpadding='0'>"); 		
			out.println("<tr>");
			out.println("<td width='1%'  align='left' class='rep-body1'   ><b>1.</b></td>");
			out.println("<td width='38%' align='left' class='rep-body1'   ><b>Name Of Lessee</b></td>");
			out.println("<td width='5%'  align='center' class='rep-body1' ><b>:</b></td>");
			out.println("<td width='*%'  align='left' class='rep-body1' ><b>"+m_name+"</b></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("<br>");
			//rs_invoice.close();
      rs_invoice=stmt_invoice.executeQuery (sql);
			more = rs_invoice.next();
			int j=1;
			if(more){
			m_item_code = rs_invoice.getString(17);
			out.println("<table border='0' width='100%' class='table' cellspacing='0' cellpadding='0'>"); 		
			out.println("<tr>");
			out.println("<td width='1%'  align='left' class='rep-body1'   ><b>2.</b></td>");
			out.println("<td width='38%' align='left' class='rep-body1'   ><b>Description of Equipment/s</b></td>");
			out.println("<td width='5%'  align='center' class='rep-body1' ><b>:</b></td>");
			out.println("<td width='5%'  align='left' class='rep-body1' ><b>("+j+")</b></td>");
			out.println("<td width='*%'  align='left' class='rep-body1' ><b>"+rs_invoice.getString(13)+ " "+rs_invoice.getString(14)+ " "+rs_invoice.getString(15)+"</b></td>");
			out.println("</tr>");
			out.println("</table>");
			
			out.println("<table border='0' width='100%' class='table' cellspacing='0' cellpadding='0'>"); 		
			//added by nuwan de silva on 04-09-07---------------------------------------
			if(m_item_code.equals("VEHICLE")){
			out.println("<tr>");
			out.println("<td width='1%'  align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='38%' align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='center' class='rep-body1' ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='15%' align='left' class='rep-body1'   ><b>ENGINE NO</b></td>");
			out.println("<td width='2%'  align='center' class='rep-body1' ><b>:</b></td>");
			out.println("<td width='*%'  align='left' class='rep-body1'   ><b>"+rs_invoice.getString(8)+"</b></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='1%'  align='left'   class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='38%' align='left'   class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='center' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='left'   class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='15%' align='left'   class='rep-body1'   ><b>CHASSIS NO</b></td>");
			out.println("<td width='2%'  align='center'   class='rep-body1' ><b>:</b></td>");
			out.println("<td width='*%'  align='left'   class='rep-body1'   ><b>"+rs_invoice.getString(9)+"</b></td>");
			out.println("</tr>");
			}
			else if(m_item_code.equals("EQUIPMENT")){
			out.println("<tr>");
			out.println("<td width='1%'  align='left'   class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='38%' align='left'   class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='center' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='left'   class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='15%' align='left'   class='rep-body1'   ><b>SERAIL NO</b></td>");
			out.println("<td width='2%'  align='center'   class='rep-body1' ><b>:</b></td>");
			out.println("<td width='*%'  align='left'   class='rep-body1'   ><b>"+rs_invoice.getString(9)+"</b></td>");
			out.println("</tr>");
			}
			
			
			//ADDED BY NUWAN DE SILVA 18-07-07-------------------
			if(!rs_invoice.getString(16).equals("-")){
			out.println("<tr>");
			out.println("<td width='1%'  align='left'   class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='38%' align='left'   class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='center' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='left'   class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='15%' align='left'   class='rep-body1'   ><b>REGISTRATION NO</b></td>");
			out.println("<td width='2%'  align='center'   class='rep-body1' ><b>:</b></td>");
			out.println("<td width='*%'  align='left'   class='rep-body1'   ><b>"+rs_invoice.getString(16)+"</b></td>");
			out.println("</tr>");
			}
			
			out.println("</table>");
			out.println("<br>");
			}
			more = rs_invoice.next();
			j=j+1;
			
			while(more){
			
			out.println("<table border='0' width='100%' class='table' cellspacing='0' cellpadding='0'>"); 		
			out.println("<tr>");
			out.println("<td width='1%'  align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='38%' align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='center' class='rep-body1' ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='left' class='rep-body1'   ><b>("+j+")</b></td>");
			out.println("<td width='*%'  align='left' class='rep-body1'   ><b>"+rs_invoice.getString(13)+ " "+rs_invoice.getString(14)+ " "+rs_invoice.getString(15)+"</b></td>");
			out.println("</tr>");
			out.println("</table>");
			
			out.println("<table border='0' width='100%' class='table' cellspacing='0' cellpadding='0'>"); 		
			
			//comment by nuwan de silva on 04-09-07---------------------------------------
			/*out.println("<tr>");
			out.println("<td width='1%'  align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='38%' align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='center' class='rep-body1' ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='15%' align='left' class='rep-body1'   ><b>ENGINE NO</b></td>");
			out.println("<td width='2%'  align='center' class='rep-body1' ><b>:</b></td>");
			out.println("<td width='*%'  align='left' class='rep-body1'   ><b>"+rs_invoice.getString(8)+"</b></td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='1%'  align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='38%' align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='center' class='rep-body1' ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='15%' align='left'  class='rep-body1'  ><b>CHASSIS NO</b></td>");
			out.println("<td width='2%'  align='center' class='rep-body1' ><b>:</b></td>");
			out.println("<td width='*%'  align='left'   class='rep-body1' ><b>"+rs_invoice.getString(9)+"</b></td>");
			out.println("</tr>");
			*/
			
			//added by nuwan de silva on 04-09-07---------------------------------------
			if(m_item_code.equals("VEHICLE")){
			out.println("<tr>");
			out.println("<td width='1%'  align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='38%' align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='center' class='rep-body1' ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='15%' align='left' class='rep-body1'   ><b>ENGINE NO</b></td>");
			out.println("<td width='2%'  align='center' class='rep-body1' ><b>:</b></td>");
			out.println("<td width='*%'  align='left' class='rep-body1'   ><b>"+rs_invoice.getString(8)+"</b></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='1%'  align='left'   class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='38%' align='left'   class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='center' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='left'   class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='15%' align='left'   class='rep-body1'   ><b>CHASSIS NO</b></td>");
			out.println("<td width='2%'  align='center'   class='rep-body1' ><b>:</b></td>");
			out.println("<td width='*%'  align='left'   class='rep-body1'   ><b>"+rs_invoice.getString(9)+"</b></td>");
			out.println("</tr>");
			}
			else if(m_item_code.equals("EQUIPMENT")){
			out.println("<tr>");
			out.println("<td width='1%'  align='left'   class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='38%' align='left'   class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='center' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='left'   class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='15%' align='left'   class='rep-body1'   ><b>SERAIL NO</b></td>");
			out.println("<td width='2%'  align='center'   class='rep-body1' ><b>:</b></td>");
			out.println("<td width='*%'  align='left'   class='rep-body1'   ><b>"+rs_invoice.getString(9)+"</b></td>");
			out.println("</tr>");
			}
			
			
				//ADDED BY NUWAN DE SILVA 18-07-07-------------------
			if(!rs_invoice.getString(16).equals("-")){
			out.println("<tr>");
			out.println("<td width='1%'  align='left'   class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='38%' align='left'   class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='center' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='left'   class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='15%' align='left'   class='rep-body1'   ><b>REGISTRATION NO</b></td>");
			out.println("<td width='2%'  align='center'   class='rep-body1' ><b>:</b></td>");
			out.println("<td width='*%'  align='left'   class='rep-body1'   ><b>"+rs_invoice.getString(16)+"</b></td>");
			out.println("</tr>");
			}
			
			out.println("</table>");
			j=j+1;
			more = rs_invoice.next();
			out.println("<br>");
			}
     
//=======================================================================

//====sup======================================================================
			out.println("<br>");
			//rs_invoice.close();
      rs_invoice=stmt_invoice.executeQuery (sql);
			more = rs_invoice.next();
			//String m_supplier_code=rs_invoice.getString(10);
    	//String m_supplier_branch=rs_invoice.getString(11);
			String m_supplier_code="";
    	String m_supplier_branch="";

			j=1;
			if(more){
			 m_supplier_code=rs_invoice.getString(10);
    	 m_supplier_branch=rs_invoice.getString(11);
				
			out.println("<table border='0' width='100%' class='table' cellspacing='0' cellpadding='0'>"); 		
			out.println("<tr>");
			out.println("<td width='1%'  align='left' class='rep-body1'   ><b>3.</b></td>");
			out.println("<td width='38%' align='left' class='rep-body1'   ><b>Supplier</b></td>");
			out.println("<td width='5%'  align='center' class='rep-body1' ><b>:</b></td>");
			out.println("<td width='5%'  align='left' class='rep-body1' ><b>("+j+")</b></td>");
//			out.println("<td width='*%'  align='left' class='rep-body1' ><b>"+rs_invoice.getString(1)+" "+rs_invoice.getString(2)+"</b></td>");
			out.println("<td width='*%'  align='left' class='rep-body1' ><b>"+rs_invoice.getString(1)+"</b></td>");
		  out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='1%'  align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='38%' align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='center' class='rep-body1' ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='*%'  align='left' class='rep-body1'   ><b>"+rs_invoice.getString(3)+"</b></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='1%'  align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='38%' align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='center' class='rep-body1' ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='*%'  align='left' class='rep-body1'   ><b>"+rs_invoice.getString(4)+"</b></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("<br>");
			}
			
			more = rs_invoice.next();
			j=j+1;
			while(more){
			if(!(m_supplier_code.equals(rs_invoice.getString(10)) && m_supplier_branch.equals(rs_invoice.getString(11))))
			{
			out.println("<table border='0' width='100%' class='table' cellspacing='0' cellpadding='0'>"); 		
			out.println("<tr>");
			out.println("<td width='1%'  align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='38%' align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='center' class='rep-body1' ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='left' class='rep-body1'   ><b>("+j+")</b></td>");
		//	out.println("<td width='*%'  align='left' class='rep-body1'   ><b>"+rs_invoice.getString(1)+" "+rs_invoice.getString(2)+"</b></td>");
			out.println("<td width='*%'  align='left' class='rep-body1'   ><b>"+rs_invoice.getString(1)+"</b></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='1%'  align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='38%' align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='center' class='rep-body1' ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='*%'  align='left' class='rep-body1'   ><b>"+rs_invoice.getString(3)+"</b></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='1%'  align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='38%' align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='center' class='rep-body1' ><b>&nbsp;</b></td>");
			out.println("<td width='5%'  align='left' class='rep-body1'   ><b>&nbsp;</b></td>");
			out.println("<td width='*%'  align='left' class='rep-body1'   ><b>"+rs_invoice.getString(4)+"</b></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("<br>");
			 m_supplier_code=rs_invoice.getString(10);
    	 m_supplier_branch=rs_invoice.getString(11);
				
			}
			j=j+1;
			more = rs_invoice.next();
			
			}//
//==========================end sup==============================================
    
		//------- Added by Chandana on 27/07/2007--------------// 
		rs = stmt.executeQuery(" SELECT ITEM_CAT_CODE, DESCRIPTION "+
		                       " FROM "+m_schema_name+".AF_CO_MAS_ITEM_CATEGORY "+
													 " WHERE ITEM_CAT_CODE = '"+m_item_code+"' "); 
		more = rs.next();
		
		if(more){
		m_item_desc=rs.getString(2);
		}
	  //--------End by Chandana ------------------------//
	
		out.println("</font></p></blockquote>");
		out.println("<font size=2><p style='text-align:justify' class='rep-body1'>");	
		
		out.println("I/We hereby acknowledge the receipt of the above mentioned "+m_item_desc+" "+ //Equipment
                    "(which is the subject matter of the Leasing Agreement dated <b>"+m_start_date+"</b> "+
                    " between "+m_orient_name+" and me/us.) "+ //Orient Financial Services Corporation Ltd //comment by nuwan de silva on 06-09-07
										"I/We have examined the above "+m_item_desc+" and found it to be in good order "+ //Equipment //modified by nwuan de silva on 10-09-07
										"and condition. ");
   	//modified by :delanjali
		//date :2007-05-10
		//reason :changed examine to examined
    out.println("<br><br>I/We acknowledge that the terms of the Lease shall commence from the date "+
                  "of this receipt.");
									
    out.println("<br><br>I/We also undertake that if called for by the Lessor I/we will give a further "+
		             "confirmation of acceptance.");	
    		
		out.println("</font></p>");		
		
		out.println("<br><br><br><br>");
		
		out.println("<font size=2><p style='text-align:left'>");				
			
		out.println("<table border='0' width='90%' class='table'>"); 		
		out.println("<tr><td width='30%' class='rep-body1' ><b>Date :  .....................................</td>");
		out.println("    <td width='30%' class='rep-body1' >&nbsp;</td>");
		out.println("    <td width='30%' class='rep-body1' ><b>Signed. .....................................</td></tr>");
		out.println("</table>");
		
		if(!m_co_app.equals("-")){
		out.println("<br><br>");
		out.println("<table border='0' width='90%' class='table'>"); 		
		out.println("<tr><td width='30%' class='rep-body1' ><b>Date :  .....................................</td>");//modified by nuwan de silva on 11-10-07
		out.println("    <td width='30%' class='rep-body1' >&nbsp;</td>");
		out.println("    <td width='30%' class='rep-body1' ><b>Signed. .....................................</td></tr>");
		out.println("</table>");
		}
			
		out.println("</font></p>");
				//	}//
		
		  out.println("</form></body></html>");
			}
			
			
			
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
