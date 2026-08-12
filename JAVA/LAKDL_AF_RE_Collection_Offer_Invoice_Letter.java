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

 

public class LAKDL_AF_RE_Collection_Offer_Invoice_Letter extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3,stmt4;
//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
  public ResultSet rs;
  public ResultSet rs1,rs2,rs3,rs4;
	

	public String m_chksql,m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_invoice_no,m_client_no,m_no_of_due_date,m_finance_no,m_print,m_address;
	public double m_amount_due,m_total_net,m_total_vat,m_gross;
	public String m_no,m_reg_no,m_model_code,m_chassis_no,m_engine_no,m_sub_model_code,m_make,m_status;
	
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
			String m_LAKDL_vat_no="134010762-7000";
		//	String m_chksql;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
			
		// out.println("conn"+conn);
						
			m_chksql=req.getParameter("chksql");
			m_print=req.getParameter("print");
			m_status=req.getParameter("status");
			String m_inventory_no	    =req.getParameter("inventory_no");		
			String m_vehicle_no	      =req.getParameter("vehicle_no");		
			String m_offer_no	      =req.getParameter("offer_no");		
			
			
			
			stmt = conn.createStatement ();
			stmt1 = conn.createStatement ();
			stmt2 = conn.createStatement ();
			stmt3 = conn.createStatement ();			
			stmt4 = conn.createStatement ();			
										
				if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
								
			else if (m_chksql.trim().equals("main_page")) {
			
			
		   
				
			  out.println("<html><head>"); 
				out.println("<title>Collection Due Letter </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  					
			out.println("<script>");
			
			out.println("function save_data(){");
			//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Save_Collection_Due_Report?chksql=save_page&invoice_no="+m_invoice_no+"&client_no="+m_client_code+"&no_of_due_date="+m_no_of_due_date+"&scr_name=AF_RE_RPT_COLLECTION_DUE;); 
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Collection_Advertistment_Offers_Issue_Invoice?chksql=save_page&offer_no="+m_offer_no+"&inventory_no="+m_inventory_no+"&vehicle_no="+m_vehicle_no+"&scr_name=AF_RE_COLLECTION_ADVEST_OFFER_ISSUE\";"); 
		  out.println(" window.location.href=m_url;"); 
			out.println("m_table.innerHTML=\"\" ");
			
			//out.println("m_table.innerHTML=\"\" ");
		
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
				
				out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");
				
			//	out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
				
				out.println("<body bgcolor='white'><br>");
				
			
				
				out.println("<form name='Form1'>");
				
				
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     out.println("</tr>"); 
			
			   out.println("</table>");
       
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YY') FROM DUAL ");
								
				boolean more = rs.next();
				if(more){
				m_Letter_date=rs.getString(1);
				}
				
			
   rs1 = stmt1.executeQuery (	" SELECT "+
    " OFFER_FULL_NAME, "+
    " OFFER_ADDRESS"+
 		" FROM "+m_schema_name+".AF_RE_ADVTIST_OFFER_VALUES "+
		" WHERE INVENTORY_NO='"+m_inventory_no+"' AND UPPER(VEHICLE_NO)=UPPER('"+m_vehicle_no+"') ");
							
		more = rs1.next();
		if(more){
		m_name=rs1.getString(1);
		m_address=rs1.getString(2);
		}
		
		rs2 = stmt2.executeQuery (" SELECT "+
    " DISTINCT VEHICLE_NO, "+
 		" NVL(REG_NO,'-') REG_NO, "+
 		" NVL(MODEL_CODE,'-') MODEL_CODE, "+
 		" NVL(CHASSIS_NO,'-') CHASSIS_NO, "+
 		" NVL(ENGINE_NO,'-') ENGINE_NO "+
 		" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
 		" WHERE UPPER(VEHICLE_NO)=UPPER('"+m_vehicle_no+"') ");
			
		more = rs2.next();
		if(more){
		m_no=rs2.getString(1);
		m_reg_no=rs2.getString(2);
		m_model_code=rs2.getString(3);
		m_chassis_no=rs2.getString(4);
		m_engine_no=rs2.getString(5);
		
		}
		
		
		rs3 = stmt3.executeQuery (" SELECT "+
    " MAKE_DESC "+
    " FROM "+m_schema_name+".AF_CO_MAS_MAKE "+
    " WHERE MAKE_CODE=( "+
    " SELECT   "+
    " MAKE_CODE "+
    " FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
    " WHERE UPPER(MODEL_CODE)=UPPER('"+m_model_code+"')) ");
		
		more = rs3.next();
		if(more){
		m_make=rs3.getString(1);
		
		}
		
		
		rs4= stmt4.executeQuery (" SELECT SUM(TOTAL_AMOUNT) TOTAL_NET_AMOUNT,SUM(VAT_AMOUNT) TOTAL_VAT_AMOUNT  "+
		" FROM( "+
		
 		" SELECT "+
 		" SUM(AMOUNT) TOTAL_AMOUNT,SUM(VAT_AMOUNT) VAT_AMOUNT "+
 		" FROM "+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_DETAIL "+
 		" WHERE INVENTORY_NO=UPPER('"+m_inventory_no+"') AND UPPER(VEHICLE_NO)=UPPER('"+m_vehicle_no+"') "+
 
		" UNION  "+
		
 		" SELECT "+
 		" INVOICE_AMOUNT TOTAL_AMOUNT,0  VAT_AMOUNT "+
 		" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
 		" WHERE REPOSSESSION_NO=( "+
 		" SELECT "+
 		" REPOSSESSION_NO "+
 		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
 		" WHERE INVENTORY_NO=UPPER('"+m_inventory_no+"') AND UPPER(VEHICLE_NO)=UPPER('"+m_vehicle_no+"') "+
 		" ) "+
 
		" UNION "+
		
		" SELECT TBL_RE.REMANING_PERIOD*NET_RENTAL_AMOUNT  TOTAL_AMOUNT ,TBL_RE.REMANING_PERIOD*(NET_RENTAL_AMOUNT*VAT_APP/100) VAT_AMOUNT  "+
		" FROM "+
		" ( "+
		" SELECT (A.PERIOD - B.INSTALMENT_NO) REMANING_PERIOD "+
		" FROM "+

 		" (SELECT "+
 		" PERIOD "+
 		" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
 		" WHERE PRICING_NO ="+
		" ( "+
		" SELECT "+
		" PRICING_NO "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		" WHERE  APPLICATION_NO=( "+
 		" SELECT APPLICATION_NO "+
 		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
 		" WHERE FINANCE_NO= "+
		" (SELECT "+
 		" FINANCE_NO "+
 		" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
		" WHERE REPOSSESSION_NO= "+
		" (SELECT "+
 		" REPOSSESSION_NO "+
 		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
 		" WHERE INVENTORY_NO=UPPER('"+m_inventory_no+"') AND UPPER(VEHICLE_NO)=UPPER('"+m_vehicle_no+"')) "+
		" ))AND UPPER(VEHICLE_NO)=UPPER('"+m_vehicle_no+"') "+
		" )) A, "+

		" (SELECT "+
		" MAX(INSTALLMENT_NO) INSTALMENT_NO "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
		" WHERE  PRICING_NO= "+
		" ( "+
		" SELECT "+
		" PRICING_NO  "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		" WHERE  APPLICATION_NO=( "+
 		" SELECT APPLICATION_NO "+
 		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
 		" WHERE FINANCE_NO= "+
		" (SELECT "+
 		" FINANCE_NO "+
 		" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
		" WHERE REPOSSESSION_NO= "+
		" (SELECT "+
 		" REPOSSESSION_NO "+
 		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
 		" WHERE INVENTORY_NO=UPPER('"+m_inventory_no+"') AND UPPER(VEHICLE_NO)=UPPER('"+m_vehicle_no+"')) "+

		" ))AND UPPER(VEHICLE_NO)=UPPER('"+m_vehicle_no+"') "+
		" )) B "+
		" ) TBL_RE, "+

		" (SELECT "+
		" DISTINCT NET_RENTAL_AMOUNT "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
		" WHERE  PRICING_NO= "+
		" ( "+
		" SELECT "+
		" PRICING_NO  "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		" WHERE  APPLICATION_NO=( "+
 		" SELECT APPLICATION_NO "+
 		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
 		" WHERE FINANCE_NO= "+
		" (SELECT "+
 		" FINANCE_NO "+
 		" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION"+
		" WHERE REPOSSESSION_NO= "+
		" (SELECT "+
 		" REPOSSESSION_NO "+
 		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
 		" WHERE INVENTORY_NO=UPPER('"+m_inventory_no+"') AND UPPER(VEHICLE_NO)=UPPER('"+m_vehicle_no+"')) "+

		" ))AND UPPER(VEHICLE_NO)=UPPER('"+m_vehicle_no+"') "+
		" )) TBL_GEN, "+
		" (SELECT "+
		" DISTINCT VAT_APP "+
		" FROM "+m_schema_name+".AF_MK_PRO_PRICING "+
		" WHERE  PRICING_NO= "+
		" ( "+
		" SELECT "+
		" PRICING_NO  "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		" WHERE  APPLICATION_NO=( "+
 		" SELECT APPLICATION_NO "+
 		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
 		" WHERE FINANCE_NO= "+
		" (SELECT "+
 		" FINANCE_NO "+
 		" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION"+
		" WHERE REPOSSESSION_NO= "+
		" (SELECT "+
 		" REPOSSESSION_NO "+
 		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
 		" WHERE INVENTORY_NO=UPPER('"+m_inventory_no+"') AND UPPER(VEHICLE_NO)=UPPER('"+m_vehicle_no+"')) "+
		" ))AND UPPER(VEHICLE_NO)=UPPER('"+m_vehicle_no+"') "+
		" )) TBL_VAT_APP "+
		
		" ) ");
		
			more = rs4.next();
		if(more){
		m_total_net=rs4.getDouble(1);
		m_total_vat=rs4.getDouble(2);
		}
		
		m_gross=m_total_net+m_total_vat;


			out.println("<blockquote><font size=3><p style='text-align:left'>");					

			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr><td width=\"100%\" class='rep-body'><b>"+m_status+"</b></td></tr>");
		  out.println("</table>");

			out.println("</font></p></blockquote>");	
		
		
		  out.println("<font size=2><p style='text-align:	center'>");				
						
			out.println("<table border='0' width='100%' class='table' style='text-align:	center'>"); 		
			out.println("<tr><td width='*%'class='rep-body'><b>PROFORMA INVOICE</b></td></tr>");
			out.println("</TABLE>");
					
			out.println("</font></p>");
			
		
		
			out.println("<blockquote><font size=2><p style='text-align:left'>");				
						
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='55%' class='rep-body' ></td><td width='15%' class='rep-body'><b>Invoice No</td><td width='20%'  class='rep-body'>&nbsp;&nbsp;: "+m_offer_no+"</td></tr>");
	  	out.println("<tr><td width='55%' class='rep-body' ></td><td width='15%' class='rep-body'><b>Date</td><td width='20%'  class='rep-body'>&nbsp;&nbsp;: "+m_Letter_date+"</td></tr>");
		  out.println("<tr><td width='55%' class='rep-body' ></td><td width='15%' class='rep-body'><b>VAT No</td><td width='20%'  class='rep-body'>&nbsp;&nbsp;: "+m_LAKDL_vat_no+"</td></tr>");
		  out.println("</TABLE>");
					
			out.println("</font></p></blockquote>");
			
			out.println("<blockquote><font size=2><p style='text-align:left'>");				
						
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='25%' class='rep-body' ><b>Name & Address</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+m_name+"</td></tr>");
	  	out.println("<tr><td width='25%' class='rep-body' ></td><td width='*%' class='rep-body'>&nbsp;&nbsp;&nbsp; "+m_address+"</td></tr>");
		  out.println("<tr><td width='25%' class='rep-body' ></td><td width='*%' class='rep-body'></td></tr>");
		  out.println("</TABLE>");
					
			out.println("</font></p></blockquote>");
			
						
			out.println("<blockquote><font size=2><p style='text-align:left'>");				
						
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='25%' class='rep-body' ><b>Registration No</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+m_reg_no+"</td></tr>");
	    out.println("</TABLE>");
			out.println("</font></p></blockquote>");
			
			out.println("<blockquote><font size=2><p style='text-align:left'>");				
						
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='25%' class='rep-body' ><b>Make/Model</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+m_make+"&nbsp;&nbsp;"+m_model_code+"</td></tr>");
	    out.println("</TABLE>");
					
			out.println("</font></p></blockquote>");
			
			out.println("<blockquote><font size=2><p style='text-align:left'>");				
						
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='25%' class='rep-body' ><b>Chassis No</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+m_chassis_no+"</td></tr>");
	    out.println("</TABLE>");
			
			out.println("</font></p></blockquote>");
			
			out.println("<blockquote><font size=2><p style='text-align:left'>");				
						
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='25%' class='rep-body' ><b>Engine No</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+m_engine_no+"</td></tr>");
	    out.println("</TABLE>");
			
			out.println("</font></p></blockquote>");
			
			out.println("<blockquote><font size=2><p style='text-align:left'>");				
						
			out.println("<table border='0' width='40%' class='table'>"); 		
			out.println("<tr><td width='21%' class='rep-body' ><b>NET Value</td><td width='4%' class='rep-body' align='left'><b>Rs</b>&nbsp;&nbsp;: </td><td width='13%' class='rep-body' align='right'> "+nf.format(m_total_net)+"</td></tr>");
			out.println("<tr><td width='21%' class='rep-body' ><b>VAT Value</td><td width='4%' class='rep-body' align='left'><b>Rs</b>&nbsp;&nbsp;: </td><td width='15%' class='rep-body' align='right'><u> "+nf.format(m_total_vat)+"</u></td></tr>");
			out.println("<tr><td width='21%' class='rep-body' ><b>Total Value</td><td width='4%' class='rep-body' align='left'><b>Rs</b>&nbsp;&nbsp;: </td><td width='15%' class='rep-body'align='right'><u> "+nf.format(m_gross)+"</u></td></tr>");
	    out.println("</TABLE>");
			
			out.println("</font></p></blockquote>");
			
			out.println("<blockquote><font size=2><p style='text-align:left'>");				
						
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='25%' class='rep-body' ><b>Delivered To</td><td width='*%' class='rep-body'>&nbsp;&nbsp;: "+m_name+"</td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ></td><td width='*%' class='rep-body'>&nbsp;&nbsp;&nbsp; "+m_address+"</td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ></td><td width='*%' class='rep-body'></td></tr>");
	    out.println("</TABLE>");
			
			out.println("<br><br>");
			
			out.println("</font></p></blockquote>");
			
			out.println("<blockquote><font size=2><p style='text-align:left'>");				
						
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='25%' class='rep-body' >-----------------------------</td><td width='*%' class='rep-body'></td></tr>");
			out.println("<tr><td width='25%' class='rep-body' ><b>Authorised signatory</td><td width='*%' class='rep-body'></td></tr>");
			out.println("</TABLE>");
			out.println("</font></p></blockquote>");
			
			out.println("<blockquote><font size=2><p style='text-align:left'>");				
						
			out.println("<table border='0' width='90%' class='table'>"); 		
			out.println("<tr><td width='*%' class='rep-body' >Note : This proforma invoice valid until</td></tr>");
			out.println("</TABLE>");
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
