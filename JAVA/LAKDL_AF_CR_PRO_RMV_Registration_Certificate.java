//ID         :
//SCREEN NAME:Document Printing - Cash price for goods
//CREATED BY :Chandana 	
//DATE/TIME  : 11-06-2007
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_RMV_Registration_Certificate extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt_invoice;
//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
  public ResultSet rs,rs_invoice;
 	

	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print,m_letter_date;
	public String m_Reg_no,m_Make_modle,m_Eng_no,m_Chas_no,m_Manu_year,m_amnt_word;
	public double m_amount_due,m_amount;
	public String m_master_lease_no;
	
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
			String m_orient_name2="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			
		
		 String m_chksql = req.getParameter("chksql");
			
  	 if(m_chksql.trim().equals("main_page")){
			
			  
				
			stmt = conn.createStatement ();
			stmt_invoice = conn.createStatement ();
			
			String m_application_no = req.getParameter("application_no");
			String m_client_code	  =req.getParameter("client_code");	
			String m_document_code	=req.getParameter("document_code");	
			String m_print=req.getParameter("print");
			

					
      rs=stmt.executeQuery (" SELECT TO_CHAR(SYSDATE, 'fmddth') ||'  '||TO_CHAR(SYSDATE, 'Month')||TO_CHAR(SYSDATE, 'YYYY') LETTER_DATE "+
				                      " FROM DUAL ");
      			
					
			boolean	more = rs.next();
			
			if(more){
			  m_letter_date=rs.getString(1);
				}
		
		String m_title="";
		String m_full_name="";
		String m_id_num="";
		String m_add1="";
		String m_add2="";
		String m_city_name="";
		
		 rs = stmt.executeQuery(" SELECT "+
        												" NVL(UPPER(TITLE),' '), "+
																" NVL(UPPER(FULL_NAME),' '), "+
																" REPLACE(REPLACE(NVL(UPPER(ADDRESS1),' '),'-',' '),'null',' '),"+ //REGISTERED_ADDRESS1
																" REPLACE(REPLACE(NVL(UPPER(ADDRESS2),' '),'-',' '),'null',' '),"+ //REGISTERED_ADDRESS2
                                " UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)), "+  //REGISTERED_CITY_CODE
																" DECODE(CLIENT_TYPE,'I',NVL(NIC_NO,'-'),'C',NVL(BUSINESS_CERTIFICATE_NO,'-')) "+ //ID NUM
																" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
                                " WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')");
			  more = rs.next();		
				
				if(more){
				m_title=rs.getString(1);
				m_full_name=rs.getString(2);
				m_add1=rs.getString(3);
				m_add2=rs.getString(4);
				m_city_name=rs.getString(5);
				m_id_num = rs.getString(6);
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
		
				
				
					 rs = stmt.executeQuery(" SELECT "+
					    " NVL(UPPER(COMPANY_NAME),' '), "+
					    " NVL(UPPER(ADDRESS1),' '), "+
					    " NVL(UPPER(ADDRESS2),' '), "+
					    " NVL(UPPER(CITY),' '), "+
					    " NVL(TEL_NO,' '), "+
					    " NVL(FAX_NO,' '),  "+
							" NVL(VAT_RATE,0), "+
							" NVL(INITCAP(COMPANY_NAME),' ') "+
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
											m_orient_name2=rs.getString(8);
											}
											
																			
															 
			rs = stmt.executeQuery(" SELECT SUM(GROSS_AMOUNT) "+  //ADDED BY CHANDANA FOR REF NO.789 ON 12/08/2007
			                       " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
														 " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");  
																											
		more = rs.next(); 
				
		if(more){
		m_amnt_word=rs.getString(1);
		m_amount=rs.getDouble(1);
		}		
			
				
				
				
	/*	String sql = " SELECT X.APPLICATION_NO, "+
		             " NVL(X.TOTAL_AMOUNT,0), "+
								 " NVL(X.REG_NO,'-'), "+
								 " NVL(X.ENGINE_NO,'-'), "+
								 " NVL(X.CHASSIS_NO,'-'), "+
								 " NVL(X.MODEL_CODE,'-'), "+
								 " NVL(X.MAKE_CODE,'-')||' '||NVL(X.ITEM_SUB_CAT,'-')||' '||NVL(Y.DESCRIPTION,'-'), "+
								 " Y.YEAR_OF_MANUFACTURE "+
								 " FROM (SELECT DISTINCT A.APPLICATION_NO, "+
								 " A.TOTAL_AMOUNT, "+
								 " A.REG_NO, "+
								 " A.ENGINE_NO, "+
								 " A.CHASSIS_NO, "+
								 " A.MODEL_CODE, "+
								 " B.ITEM_SUB_CAT, "+
								 " B.MAKE_CODE, "+
								 " B.DESCRIPTION, "+
								 " A.SUB_MODEL_CODE "+
								 " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A, "+
								 " "+m_schema_name+".AF_CO_MAS_MODEL B "+
								 " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
								 " A.MODEL_CODE=B.MODEL_CODE ) X , "+
								 " "+m_schema_name+".AF_CO_MAS_SUB_MODLE Y "+
								 " WHERE X.SUB_MODEL_CODE=Y.SUB_CODE "; 	*/
									
									
			//-----------Added by Chandana on 16/07/07 -------------						
									
		String sql = " SELECT NVL(B.TOTAL_AMOUNT,0), "+   //1
		             " NVL(B.REG_NO,'-'), "+        //2
								" nvl(B.ENGINE_NO,'-'), "+   //3
								" nvl(B.CHASSIS_NO,'-'), "+  //4
								" B.MODEL_CODE,"+   //5
								" INITCAP(C.MAKE_DESC||' '||D.DESCRIPTION ||' '||H.DESCRIPTION) MODEL_DESC, "+ //6
								" NVL(B.YEAR_OF_MANUFACTURE,0), "+  //7
								" INITCAP(H.DESCRIPTION) ITEM_SUB_CAT_DESC, "+  //8
								" C.MAKE_CODE, "+  //9
								" F.ITEM_SUB_CAT,"+  //10
								" UPPER(E.VENDOR_CODE),"+ //11
								" UPPER(E.BRANCH), "+   //12
								" INITCAP(G.NAME), "+   //13
								" UPPER(NVL(F.FUEL_TYPE,'-')), "+ //14
								" UPPER(NVL(B.COLOUR,'-')) , "+    //15
								" NVL(TO_CHAR(B.REG_DATE,'DD/MM/YYYY'),'-') , "+  //16
								" DECODE(A.STATUS,'N','BRAND NEW','U','USED','R','RE-CONDITION'), "+ //17
								" NVL(D.ENGINE_CAPACITY,'0'), "+  //18
								" DECODE(A.PURPOSE,'P','PRIVATE','B','BUSSINESS'), "+ //19
								" NVL(CR_BOOK_NO,'-'), "+ //20
								" NVL("+m_schema_name+".AF_CO_GET_PROVINCE_DESC(DISTRICT_CODE),'-')"+ //21
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
								" B.MODEL_CODE=F.MODEL_CODE ";
 

									
									
									
				
				
			  out.println("<html><head>"); 
				out.println("<title>RMV Registration Certificate </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  		
			out.println("<script>");
			
			out.println("function save_data(){");
			//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";");			
			//out.println(" window.location.href=m_url;"); 
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
			out.println("<body bgcolor='white'><br>");
							
			out.println("<form name='Form1'>");
										
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");
			
			rs = stmt.executeQuery(sql);
			
			more = rs.next(); 
				
		  while(more){
      		
		  out.println("<blockquote><font size=3><p style='text-align:left'>");					
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr><td width=\"100%\" class='rep-body1'><b></b></td></tr>"); //"+m_status+"
		  out.println("</table>");
  		out.println("</font></p></blockquote>");	
			
			out.println("<font size=3><p style='text-align:left'>");					
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"*%\" class='rep-body1'><B>"+m_letter_date+"</td></tr>");
			out.println("</table>");
			out.println("<br>");
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"*%\" class='rep-body1'><B>Commissioner of Motor Traffic</td></tr>");
			out.println("<tr><td width=\"*%\" class='rep-body1'><B>"+rs.getString(21)+"</td></tr>");
			out.println("<tr><td width=\"*%\" class='rep-body1'><B></td></tr>");		
		  out.println("</table>");
			//out.println("<br>");
			
			out.println("<br>");
			//out.println("<br>");
      
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"*%\" class='rep-body1'><B>Dear Sir, </td></tr>");
		  out.println("</table>");
      out.println("<br>");
      
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"*%\" class='rep-body1'><B><U>REGISTRATION CERTIFICATE</U></B></td></tr>");
		  out.println("</table>");
			out.println("<br>");
						
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"35%\" class='rep-body1'><B>Make & Model</td><td width=\"3%\" class='rep-body1'><B>:</td><td width=\"60%\" class='rep-body1'><B>"+rs.getString(6)+"</td></tr>");		
		  out.println("</table>");
			out.println("<br>");

      out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"35%\" class='rep-body1'><B>Registration No</td><td width=\"3%\" class='rep-body1'><B>:</td><td width=\"60%\" class='rep-body1'><B>"+rs.getString(2)+"</td></tr>");		
		  out.println("</table>");
			out.println("<br>");
			
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"35%\" class='rep-body1'><B>Chassis Number</td><td width=\"3%\" class='rep-body1'><B>:</td><td width=\"60%\" class='rep-body1'><B>"+rs.getString(4)+"</td></tr>");		
		  out.println("</table>");
			out.println("<br>");
			
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"35%\" class='rep-body1'><B>Engine Number</td><td width=\"3%\" class='rep-body1'><B>:</td><td width=\"60%\" class='rep-body1'><B>"+rs.getString(3)+"</td></tr>");		
		  out.println("</table>");
			out.println("<br>");
			
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"35%\" class='rep-body1'><B>CR Book Number</td><td width=\"3%\" class='rep-body1'><B>:</td><td width=\"60%\" class='rep-body1'><B>"+rs.getString(20)+"</td></tr>");		
		  out.println("</table>");
			out.println("<br>");
			
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"35%\" class='rep-body1'><B>Colour</td><td width=\"3%\" class='rep-body1'><B>:</td><td width=\"60%\" class='rep-body1'><B>"+rs.getString(15)+"</td></tr>");		
		  out.println("</table>");
			out.println("<br>");

      out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"35%\" class='rep-body1'><B>Cylinder Capacity</td><td width=\"3%\" class='rep-body1'><B>:</td><td width=\"60%\" class='rep-body1'><B>"+rs.getString(18)+"</td></tr>");		
		  out.println("</table>");
			out.println("<br>");

      out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"35%\" class='rep-body1'><B>Usage</td><td width=\"3%\" class='rep-body1'><B>:</td><td width=\"60%\" class='rep-body1'><B>"+rs.getString(19)+"</td></tr>");		
		  out.println("</table>");
			out.println("<br>");

      out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"35%\" class='rep-body1'><B>Status when Registered</td><td width=\"3%\" class='rep-body1'><B>:</td><td width=\"60%\" class='rep-body1'><B>"+rs.getString(17)+"</td></tr>");		
		  out.println("</table>");
			out.println("<br>");

      out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"35%\" class='rep-body1'><B>Licensing Authority</td><td width=\"3%\" class='rep-body1'><B>:</td><td width=\"60%\" class='rep-body1'><B>"+rs.getString(21)+"</td></tr>");		
		  out.println("</table>");
			out.println("<br>");
			
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"35%\" class='rep-body1'><B>Year of Manufacture</td><td width=\"3%\" class='rep-body1'><B>:</td><td width=\"60%\" class='rep-body1'><B>"+rs.getString(7)+"</td></tr>");		
		  out.println("</table>");
			out.println("<br>");
			
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"35%\" class='rep-body1'><B>Type of Fuel</td><td width=\"3%\" class='rep-body1'><B>:</td><td width=\"60%\" class='rep-body1'><B>"+rs.getString(14)+"</td></tr>");		
		  out.println("</table>");
			out.println("<br>");
			
      
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"35%\" class='rep-body1'><B>Date for Tax and First Registration</td><td width=\"3%\" class='rep-body1'><B>:</td><td width=\"60%\" class='rep-body1'><B>"+rs.getString(16)+"</td></tr>");		
		  out.println("</table>");
			out.println("<br>");
			
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"35%\" class='rep-body1'><B>Absolute Owner</td><td width=\"3%\" class='rep-body1'><B>:</td><td width=\"60%\" class='rep-body1'><B>"+m_orient_name2+"</td></tr>");		
		  out.println("</table>");
			out.println("<br>");
      
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"35%\" class='rep-body1'><B>Current Owner </td><td width=\"3%\" class='rep-body1'><B>:</td><td width=\"60%\" class='rep-body1'><B>"+m_full_name+"</td></tr>");		
		  out.println("</table>");
			out.println("<br>");
			
 
	    out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"*%\" class='rep-body1'><B>We would be much obliged if you could make an arrangement to hand over Revenue Licence of the above vehicle to "+m_full_name+" bearing I.D. No:"+m_id_num+"</td></tr>");		
		  out.println("</table>");
			out.println("<br>");
	
	
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"*%\" class='rep-body1'><B></td></tr>");		
		  out.println("</table>");
			out.println("<br>");
			
			out.println("<br>");
			

			
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"*%\" class='rep-body1' align='left'><b>Thanking you,</b></td></tr>");
			out.println("<tr><td width=\"*%\" class='rep-body1' align='left'><b>Yours faithfully,</td></tr>");
		  
			out.println("</table>");
	
			//out.println("<br>");
			//out.println("<br>"); 
			
				
			out.println("<table align='center' width='90%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"*%\" class='rep-body1' align='left'></td></tr>");
		  out.println("<tr><td width=\"*%\" class='rep-body1' align='left'><b>"+m_orient_name.toUpperCase()+"</b></td></tr>");
			out.println("</table>");			
		
		more = rs.next();
		if(more){
		out.println("   <p style=\"page-break-after:always\"></p>"); 

		}
		
		}	
			
			
			out.println("<br>");
			

			
			
			
				
			
			out.println("</font></p>");												
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
