import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : NUWAN DE SILVA    DATE:12-08-07 on 10.40am

public class LAKDL_AF_MISF_Contract_Report extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt_2,stmt,stmt_invoice,stmt_rental,stmt_pricing,stmt_charges;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
  java.lang.Math a;

    
 // public ResultSet rs1,rs_doc_charge;
  public ResultSet rs,rs2,rs_rental,rs_pricing,rs_charges;

	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_RE_PRO_drill_downs obj =new LAKDL_AF_RE_PRO_drill_downs();
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);      
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
     	res.setDateHeader("Expires", 0);

			ServletOutputStream out = res.getOutputStream();
			m_chksql=req.getParameter("chksql");
			
      stmt_invoice=conn.createStatement();
			stmt_pricing=conn.createStatement();
			stmt=conn.createStatement();
			stmt_2=conn.createStatement();
			stmt_rental=conn.createStatement();
			stmt_charges = conn.createStatement ();

			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("main_page")){
			//out.println("sfsdsd");
			
			String m_finance_no=req.getParameter("finance_no").trim();
			String m_print=req.getParameter("print").trim();
			
		//	out.println("m_print"+m_print);
			String m_client_code="";
			String m_application_no="";
		//				out.println("m_finance_no"+m_finance_no);
		
		
			rs=stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ");
			rs.next();
			String m_date=rs.getString(1);
			rs.close();

			     String Sql_client_data="SELECT DISTINCT A.APPLICATION_NO, "+//1
								" A.CLIENT_CODE, "+//2
								" UPPER(DECODE(B.CLIENT_TYPE,'I',INITCAP(B.TITLE) || '. ' || B.FULL_NAME,'C','Mess' || '. '  || B.FULL_NAME)), "+ //3
								" UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',B.ADDRESS1,'C',B.ADDRESS1),'-')), "+//4 //B.REGISTERED_ADDRESS1
								" UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',B.ADDRESS2,'C',B.ADDRESS2),'-')), "+//5 // B.REGISTERED_ADDRESS2
								" UPPER(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE),' ')) CITY_NAME ,"+ //6
								" NVL(DECODE(A.APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Application Process','VERIFY1','Credit Verification','V-APP','Credit Score Approval','VERIFY-M','Credit Approval 1','VERIFY2','Credit Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Purchase Order','CANCEL','Cancel','REPOSSESS','Repossess'),'-') "+//6
								" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
								" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
								" UPPER(A.FINANCE_NO)=UPPER('"+m_finance_no+"') ";
								
							String sql_co_applicant	=" SELECT  "+
							  " CLIENT_CODE, "+//1
								" DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C','MESS' || '. '  || UPPER(FULL_NAME)),   "+
								" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS1),'C',UPPER(ADDRESS1)),' '),  "+ //REGISTERED_ADDRESS1
								" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS2),'C',UPPER(ADDRESS2)),' ') , "+ //REGISTERED_ADDRESS1
								" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+
								" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-')   "+
								" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
								" WHERE   CLIENT_CODE =  "+
								" (SELECT  "+
								" CO_APPLICANT  "+
								" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
								" WHERE UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"')) ";
								
								String Sql_data_guarantor="SELECT DISTINCT "+
													 " NVL(C.GUARANTOR_CODE,'-'), "+ 	 //1
													 " UPPER(DECODE(B.CLIENT_TYPE,'I',INITCAP(B.TITLE) || '. ' || B.FULL_NAME,'C','Mess' || '. '  || B.FULL_NAME)), "+ //2
								           " UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',B.ADDRESS1,'C',B.ADDRESS1),'-')), "+//3//REGISTERED_ADDRESS1
								           " UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',B.ADDRESS2,'C',B.ADDRESS2),'-')), "+//4//REGISTERED_ADDRESS2
								           " NVL(DECODE(B.CLIENT_TYPE,'I',B.NIC_NO,  'C',B.BUSINESS_CERTIFICATE_NO),'-'), "+//5
													 " "+m_schema_name+".AF_CO_GET_CLIENT_EXPOSURE(C.GUARANTOR_CODE)  "+	//6	
													 " FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR C ,"+m_schema_name+".AF_CO_MAS_CLIENT B ,"+ 
													 " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
													 " WHERE  B.CLIENT_CODE=C.GUARANTOR_CODE AND  "+
													 " C.APPLICATION_NO=A.APPLICATION_NO AND  "+
										    	 " A.FINANCE_NO='"+m_finance_no+"' AND "+
													 " C.ACTIVE_STATUS = 'Y' "; //ACTIVE_STATUS Added by Chandana for Ref no:541 on 24/07/2007
														
									
										String				Sql_data_asset_details="  SELECT "+												
													  //" A.INVOICE_NO,"+ //1
														//" A.APPLICATION_NO,  "+ //2
														"DISTINCT  A.ASSET_ID, "+ //3
														" C.MAKE_CODE, "+ //4
														" D.MAKE_DESC, "+ //5
														" A.MODEL_CODE, "+ //6
														" C.DESCRIPTION, "+ //7
														//" NVL(A.ENGINE_NO,'-'), "+ //8
														//" NVL(A.CHASSIS_NO,'-'), "+ //9
														//" ROUND((VAT_PERCENTAGE-VAT_APP)/100*NET_AMOUNT + NET_AMOUNT,0), "+//12
														" A.SUB_MODEL_CODE, "+ //13
                            " F.DESCRIPTION, "+ //14
														" QTY, "+//10
														" DECODE(B.STATUS,'U','Used','N','New','R','Re-Condition'), "+ //11
														" NVL(A.REG_NO,'-') ,"+
														" NVL(A.PRICING_NO,'-') "+
													  " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS B, "+
														" "+m_schema_name+".AF_CO_MAS_MODEL C,"+m_schema_name+".AF_CO_MAS_MAKE D, "+
														" "+m_schema_name+".AF_CO_PRO_APP_PRICING E ,"+
														" "+m_schema_name+".AF_CO_MAS_SUB_MODLE F, "+
                            " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS G "+
														" WHERE G.FINANCE_NO='"+m_finance_no+"'  AND "+
														" A.ASSET_ID=B.ASSET_ID AND "+
														" A.APPLICATION_NO=E.APPLICATION_NO AND "+
														" A.APPLICATION_NO=G.APPLICATION_NO AND "+
                            " A.INVOICE_NO=E.PRO_INVOICE_NO AND  "+
														" C.MODEL_CODE=A.MODEL_CODE AND "+
														" C.MAKE_CODE=D.MAKE_CODE AND "+
														" A.ACTIVE_STATUS='Y' AND "+
														" B.ACTIVE_STATUS='Y' AND "+
														" C.ACTIVE_STATUS='Y' AND "+ 
														" A.SUB_MODEL_CODE=F.SUB_CODE AND "+
														" D.ACTIVE_STATUS='Y'  ";
				             
					
					 out.println("<HTML><HEAD><TITLE>Contract Details Report</TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						
			out.println("<script>");
			/*out.println("function save_data(){");
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_MISF_Contract_Report?chksql=main_page&print=FALSE&finance_no="+m_finance_no+"\";"); 
		  out.println(" window.location.href=m_url;"); 
			out.println("m_table.innerHTML=\"\" ");
			//out.println("window.print();");
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
			*/
			out.println("</script>");
			
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0' >"); //onLoad=\"add_button()\"
					 out.println("<FORM NAME='Form1' method='post'>"); 
						
					
				
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Contract Details for Finance No - "+m_finance_no+"  </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR><BR>");
						
					/*out.println("<table align='center' width='100%' class='table'>"); 
			    out.println("<tr>");  
			    out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		      out.println("</tr>"); 
			    out.println("</table>");
					*/	
						rs=stmt.executeQuery(Sql_client_data);
						boolean more=rs.next();
						if(more)
						{
						m_application_no=rs.getString(1);
						m_client_code=rs.getString(2);
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b>Application No</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='35%' align='left' onClick=\"show_application_detail_drill('"+m_application_no+"')\" style='cursor:hand' ><DIV class=div_input><u>"+m_application_no+"</u></DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b>Finance No</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='35%' align='left' onClick=\"show_finance_detail_drill('"+m_finance_no+"')\" style='cursor:hand' ><DIV class=div_input><u>"+m_finance_no+"</u></DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b>Application Status</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='35%' align='left'><DIV class=div_input><b>"+rs.getString(7)+"</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>");
						
						out.println("<br>");
						
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b><u>Client Information<b></u></td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='35%' align='left'>&nbsp;</td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>");
						
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b>Client Name</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='35%' align='left' onClick=show_client('"+m_client_code+"') style='cursor:hand' ><DIV class=div_input><u>"+rs.getString(3)+"</u></DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b>Address</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='35%' align='left' ><DIV class=div_input>"+rs.getString(4)+"</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' >&nbsp;</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='35%' align='left'  ><DIV class=div_input>"+rs.getString(5)+"</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' >&nbsp;</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='35%' align='left'><DIV class=div_input>"+rs.getString(6)+"</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>");
						}
						rs.close();
						rs=stmt.executeQuery(sql_co_applicant);
						more=rs.next();
						if(more)
						{
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b>Co-Applicant</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='35%' align='left' onClick=show_client('"+rs.getString(1)+"') style='cursor:hand' ><DIV class=div_input><u>"+rs.getString(2)+"</u></DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table >");
						}
						rs.close();
						rs=stmt.executeQuery(Sql_data_guarantor);
						more=rs.next();
						int i=1;
						if(more)
						{
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b>Guarantors</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='35%' align='left'>&nbsp;</td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table >");
						}
						out.println("<table align='center' width='100%' class='table' >");						
						while(more)
						{
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b>"+i+"</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='35%' align='left' onClick=show_client('"+rs.getString(1)+"') style='cursor:hand' ><DIV class=div_input><u>"+rs.getString(2)+"</u></DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						i=i+1;
						more=rs.next();
						}
					  out.println("</table >");
						rs.close();
						
						String Sql_new_asset_Query="SELECT  "+
						" /*DISTINCT*/  A.ASSET_ID, "+// //1
						" C.MAKE_CODE,  "+//--2
						" D.MAKE_DESC,  "+//--3
						" A.MODEL_CODE,  "+//--4
						" C.DESCRIPTION,  "+//--5
						" A.SUB_MODEL_CODE,  "+//--6
						" F.DESCRIPTION,  "+//--7
						//" QTY, "+//--8
						" '1', "+//--8
						" DECODE(B.STATUS,'U','Used','N','New','R','Re-Condition'),  "+//--9
						" NVL(A.REG_NO,'-') ,"+//--10
						//PRICING-----
						" A.PRICING_NO, "+//--11
						" NVL(E.PAYMENT_MODE,'-'),"+//--12
						" NVL(E.PAYMENT_INTERVAL,0),"+//--13
						" NVL(E.RATE,0),"+//--14
						" NVL(E.VAT_PERCENTAGE,0),"+//--15
						" NVL(E.NET_AMOUNT,0),"+//--16
						" NVL(E.VAT_AMOUNT,0),"+//--17
						" NVL(E.GROSS_AMOUNT,0),"+//--18
						" NVL(E.NIBSM,0),"+//--19
						" NVL(E.AMI,0),"+//--20
						" NVL(E.RESIDUAL_VALUE,0),"+//--21
						" NVL(E.SUPPLIER_CREDIT,0),"+//--22
						" NVL(E.VAT_APP,0),"+//--23
						" NVL(E.INT_MARGIN,0),"+//--24
						" NVL(E.PERIOD,0),"+//--25
						" NVL(E.VARIABLE_INT_BASE,'-'), "+//26
						" (ROUND(((E.VAT_PERCENTAGE - E.VAT_APP)/100)*E.NET_AMOUNT +E.NET_AMOUNT,0)) NET_APP, "+ //27
						" (SELECT  			 DISTINCT COUNT(INSTALLMENT_NO) INST  "+
					  " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT   "+
            "         WHERE PRICING_NO=A.PRICING_NO "+
            "          GROUP BY   PRO_INVOICE_NO ) period, "+//28
						" NVL(A.INVOICE_NO,'-')"+//--29
						
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS B, "+
						" "+m_schema_name+".AF_CO_MAS_MODEL C,"+m_schema_name+".AF_CO_MAS_MAKE D, "+
						" "+m_schema_name+".AF_CO_PRO_APP_PRICING E ,"+
						" "+m_schema_name+".AF_CO_MAS_SUB_MODLE F, "+
						" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS G "+
						" WHERE G.FINANCE_NO='"+m_finance_no+"'  AND "+
						" A.ASSET_ID=B.ASSET_ID AND "+
						" A.APPLICATION_NO=E.APPLICATION_NO AND "+
						" A.APPLICATION_NO=G.APPLICATION_NO AND "+ 
						" A.INVOICE_NO=E.PRO_INVOICE_NO AND  "+
						" C.MODEL_CODE=A.MODEL_CODE AND "+
						" C.MAKE_CODE=D.MAKE_CODE AND "+
						" A.ACTIVE_STATUS='Y' AND "+
						" B.ACTIVE_STATUS='Y' AND "+
						" C.ACTIVE_STATUS='Y' AND  "+
						" A.SUB_MODEL_CODE=F.SUB_CODE AND "+
						" D.ACTIVE_STATUS='Y'   "+
						" ORDER BY ASSET_ID,PRICING_NO ";
						
						String m_payment_intervel="";
						double m_ami=0;
						int m_ami_ok=0;
						String m_tmp_asset_id="";
						String m_tmp_pricing_no="";
						String m_tmp_invoice_no="";
						int b_flag=0;
						String m_period="",m_pay_mode="";
						double m_rate=0;
						double m_nibsm=0;
						double m_net=0;
						double m_vat=0;
						double m_gross=0;
						double m_residual=0;
						double m_fin_amt=0;
						double m_sup_credit=0;
						double m_vat_on_vehicle=0;
						double m_vat_app=0;
						int m_period_new=0;
						
						double	m_total_net=0;
					  double m_total_vat=0;
					  double m_total_gross=0;
					  String m_start_date="";
					  String m_end_date="";
						
						//rs=stmt.executeQuery(Sql_data_asset_details);
						rs=stmt.executeQuery(Sql_new_asset_Query);
						more=rs.next();
						if(more){
						m_tmp_asset_id  =rs.getString(1);
						m_tmp_pricing_no=rs.getString(11);
						m_tmp_invoice_no=rs.getString(29);
						}
						
						while(more){
						out.println("<br>"); 
						out.println("<hr color='black'>"); 
						while(m_tmp_asset_id.equals(rs.getString(1)) && m_tmp_pricing_no.equals(rs.getString(11)) && m_tmp_invoice_no.equals(rs.getString(29)) ){
						out.println("<br>"); 
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b><u>Asset Details</b></u></td>");
						out.println("<td width='2%'>&nbsp;</td>"); 
						out.println("<td width='35%' align='left'>&nbsp;</td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table >");
						
						//------------Asset Details --------------------------------------
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b>Asset Id</td>");
						out.println("<td width='2%'>&nbsp;</td>"); 
						out.println("<td width='35%' align='left' onClick=show_asset_detail_drill('"+rs.getString(1)+"') style='cursor:hand'><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b>Make Description</td>");
						out.println("<td width='2%'>&nbsp;</td>"); 
						out.println("<td width='35%' align='left'>"+rs.getString(3)+"</td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b>Model Description</td>");
						out.println("<td width='2%'>&nbsp;</td>"); 
						out.println("<td width='35%' align='left'>"+rs.getString(5)+"</td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b>Sub Model Description</td>");
						out.println("<td width='2%'>&nbsp;</td>"); 
						out.println("<td width='35%' align='left'>"+rs.getString(7)+"</td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b>Vehicle Registration No</td>");
						out.println("<td width='2%'>&nbsp;</td>"); 
						out.println("<td width='35%' align='left'>"+rs.getString(10)+"</td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b>Qty</td>");
						out.println("<td width='2%'>&nbsp;</td>"); 
						out.println("<td width='35%' align='left'>"+rs.getInt(8)+"</td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b>Condition Of Asset</td>");
						out.println("<td width='2%'>&nbsp;</td>"); 
						out.println("<td width='35%' align='left'>"+rs.getString(9)+"</td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table >");
						out.println("<Br>"); 
						 m_payment_intervel=rs.getString(13);
					   m_ami_ok=rs.getInt(20);
						 m_pay_mode=rs.getString(12);
						 m_period=rs.getString(25);
						 m_rate=rs.getDouble(14);
						 m_nibsm=rs.getDouble(19);
						 m_net=rs.getDouble(16);
						 m_vat=rs.getDouble(17);
						 m_gross=rs.getDouble(18);
						 m_residual=rs.getDouble(21);
						 m_fin_amt=rs.getDouble(27);
						 m_sup_credit=rs.getDouble(22);
						 m_vat_on_vehicle=rs.getDouble(15);
						 m_vat_app=rs.getDouble(23);
						 m_period_new=rs.getInt(28);	
						
						more=rs.next();
						if(!more){
						break;
						}
					 
						//-------------------------end asset details---------------------------
						}
						
						out.println("<br>"); 
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b><u>Rental Details</b></u></td>");
						out.println("<td width='2%'>&nbsp;</td>"); 
						out.println("<td width='35%' align='left'>&nbsp;</td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table >");
						//---------------------------------------------------------------------
						
			String Sql_updated_query="SELECT  "+			
			"	  /*A.SUM,*/B.NET_AMOUNT,B.VAT_AMOUNT,B.GROSS_AMOUNT,C.START_DATE,C.END_DATE "+
			"		FROM "+
			"		(SELECT  "+
			"		DISTINCT SUM(NET_RENTAL_AMOUNT) NET_AMOUNT,  "+
			"		(SUM(GRENTAL_AMOUNT) - SUM(NET_RENTAL_AMOUNT)) VAT_AMOUNT,  "+
			"		SUM(GRENTAL_AMOUNT) GROSS_AMOUNT  "+
			"		FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
			"		WHERE PRICING_NO='"+m_tmp_pricing_no+"'  "+
			"		GROUP BY PRO_INVOICE_NO )B, "+
				 
			"		(SELECT DISTINCT DECODE( B.PAYMENT_INTERVAL,'365', "+
			"		(TO_CHAR((A.ACTIVATED_DATE+((SELECT DISTINCT COUNT(INSTALLMENT_NO) FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  WHERE APPLICATION_NO=A.APPLICATION_NO  GROUP BY PRO_INVOICE_NO))), 'DD-MM-YYYY'))    "+
			"		,'52', "+
			"		(TO_CHAR((A.ACTIVATED_DATE+7*((SELECT DISTINCT COUNT(INSTALLMENT_NO) FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  WHERE APPLICATION_NO=A.APPLICATION_NO  GROUP BY PRO_INVOICE_NO))), 'DD-MM-YYYY')) ,  "+
			"		(TO_CHAR(ADD_MONTHS(A.ACTIVATED_DATE,  "+
			"		(((SELECT DISTINCT COUNT(INSTALLMENT_NO) FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  WHERE APPLICATION_NO=A.APPLICATION_NO  GROUP BY PRO_INVOICE_NO)-1))*(DECODE(B.PAYMENT_INTERVAL,'12','1','1','12',B.PAYMENT_INTERVAL))),'DD-MM-YYYY')     "+
			"		) )END_DATE, "+
			"		TO_CHAR(A.ACTIVATED_DATE, 'DD-MM-YYYY')  START_DATE "+
			"		FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING B  "+
			"		WHERE A.APPLICATION_NO=B.APPLICATION_NO AND  "+
			"		A.FINANCE_NO=UPPER('"+m_finance_no+"') "+
			"		)C ";
             
		
						
					String sql_ami="SELECT  "+
					" SUM(GRENTAL_AMOUNT) SUM  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A ,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
					" WHERE B.FINANCE_NO=UPPER('"+m_finance_no+"') "+
					" AND A.APPLICATION_NO=B.APPLICATION_NO "+
					" AND PRICING_NO=UPPER('"+m_tmp_pricing_no+"')  "+
					" AND PRO_INVOICE_NO='"+m_tmp_invoice_no+"'   "+
					" AND AMI_AMOUNT >0   "+
					" GROUP BY PRO_INVOICE_NO ";
					
					rs2=stmt_2.executeQuery(sql_ami);
					boolean more_ami=rs2.next();
					if(more_ami){
					if(m_ami_ok>0){
					m_ami=rs2.getDouble(1);
					}
					}
					rs2.close();
					rs2=stmt_2.executeQuery(Sql_updated_query);
					 more_ami=rs2.next();
					
					if(more_ami){
					m_total_net=rs2.getDouble(1);
					m_total_vat=rs2.getDouble(2);
					m_total_gross=rs2.getDouble(3);
					m_start_date=rs2.getString(4);
					m_end_date=rs2.getString(5);
					}										
					out.println("<table align='center' width='100%' border='0' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Pricing No</b></td>");
					out.println("<td width='12%' align='right' class=div_input  style= cursor:hand; onclick=show_pricing_drill('"+m_tmp_pricing_no+"')><u>"+m_tmp_pricing_no+"</u></td>");
					out.println("<td width='13%' class=div_input>&nbsp;</td>");
					out.println("<td width='20%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='*%' class=div_input>&nbsp;</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Payment Mode</b></td>");
					out.println("<td width='12%' class=div_input align='right'>"+m_pay_mode+"</td>");
					out.println("<td width='13%' class=div_input>&nbsp;</td>");
					out.println("<td width='20%' class=div_input><b>Period</b></td>");
					out.println("<td width='10%' align='right' class=div_input>"+m_period_new+"</td>");
					out.println("<td width='*%' class=div_input>&nbsp;</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Rate</b></td>");
					out.println("<td width='12%' class=div_input align='right' >"+nf.format(m_rate)+"</td>");
					out.println("<td width='13%' class=div_input>&nbsp;</td>");
					out.println("<td width='20%' class=div_input><b>NIBSM</b></td>");
					out.println("<td width='10%' align='right' class=div_input>"+nf.format(m_nibsm)+"</td>");
					out.println("<td width='*%' class=div_input>&nbsp;</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Net Amount</b></td>");
					out.println("<td width='12%' class=div_input align='right'>"+nf.format(m_net)+"</td>");
					out.println("<td width='13%' class=div_input>&nbsp;</td>");
					out.println("<td width='20%' class=div_input><b>AMI</b></td>");
					out.println("<td width='10%' align='right' class=div_input>"+nf.format(m_ami)+"</td>");
					out.println("<td width='*%' class=div_input>&nbsp;</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>VAT Amount</b></td>");
					out.println("<td width='12%' class=div_input align='right'>"+nf.format(m_vat)+"</td>");
					out.println("<td width='13%' class=div_input>&nbsp;</td>");
					out.println("<td width='20%' class=div_input><b>AMI(Installments)</b></td>");
					out.println("<td width='10%' align='right' class=div_input>"+nf1.format(m_ami_ok)+"</td>");
					out.println("<td width='*%' class=div_input>&nbsp;</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Gross Amount</b></td>");
					out.println("<td width='12%' class=div_input align='right'>"+nf.format(m_gross)+"</td>");
					out.println("<td width='13%' class=div_input>&nbsp;</td>");
					out.println("<td width='20%' class=div_input><b>Residual Value</b></td>");
					out.println("<td width='10%' align='right' class=div_input>"+nf.format(m_residual)+"</td>");
					out.println("<td width='*%' class=div_input>&nbsp;</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Finance Amount</b></td>");
					out.println("<td width='12%' class=div_input align='right'>"+nf.format(m_fin_amt)+"</td>");
					out.println("<td width='13%' class=div_input>&nbsp;</td>");
					out.println("<td width='20%' class=div_input><b>Supplier Credit</b></td>");
					out.println("<td width='10%' align='right' class=div_input>"+nf.format(m_sup_credit)+"</td>");
					out.println("<td width='*%' class=div_input>&nbsp;</td>");
					out.println("</tr>");
															
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Total Rental Net</b></td>");
					out.println("<td width='12%' class=div_input align='right'>"+nf.format(m_total_net)+"</td>");
					out.println("<td width='13%' class=div_input>&nbsp;</td>");
					out.println("<td width='20%' class=div_input><b>Payment Interval</b></td>");
					out.println("<td width='10%' align='right' class=div_input>"+(m_payment_intervel)+"</td>");
				  out.println("<td width='*%' class=div_input>&nbsp;</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Total Rental VAT</b></td>");
					out.println("<td width='12%' class=div_input align='right' >"+nf.format(m_total_vat)+"</td>");
					out.println("<td width='13%' class=div_input>&nbsp;</td>");
					out.println("<td width='20%' class=div_input><b>VAT on Vehicle</b></td>");
					out.println("<td width='10%' align='right' class=div_input>"+nf.format(m_vat_on_vehicle)+"</td>");
					out.println("<td width='*%' class=div_input>&nbsp;</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Total Rental Gross</b></td>");
					out.println("<td width='12%' class=div_input align='right' >"+nf.format(m_total_gross)+"</td>");
					out.println("<td width='13%' class=div_input>&nbsp;</td>");
					out.println("<td width='20%' class=div_input><b>VAT Applicable</b></td>");
					out.println("<td width='10%' align='right' class=div_input>"+nf.format(m_vat_app)+"</td>");
					out.println("<td width='*%' class=div_input>&nbsp;</td>");
					out.println("</tr>");
					
			    out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='12%' class=div_input align='right' >&nbsp;</td>");
					out.println("<td width='13%' class=div_input>&nbsp;</td>");
					out.println("<td width='20%' class=div_input><b>Installment Start Date</b></td>");
					out.println("<td width='10%' align='right' class=div_input>"+m_start_date+"</td>");
					out.println("<td width='*%' class=div_input>&nbsp;</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='12%' class=div_input align='right' >&nbsp;</td>");
					out.println("<td width='13%' class=div_input>&nbsp;</td>");
					out.println("<td width='20%' class=div_input><b>Installment End Date</b></td>");
					out.println("<td width='10%' align='right' class=div_input>"+m_end_date+"</td>");
					out.println("<td width='*%' class=div_input>&nbsp;</td>");
					out.println("</tr>");
					rs2.close();
					out.println("</table>");
					
					rs_charges =stmt_charges.executeQuery("SELECT "+
				  "  B.PRICING_NO, "+ 
				  "  B.SUB_CHAGE_CODE, "+ 
				  "  C.DESCRIPTION, "+ 
				  "  SUM(B.AMOUNT) "+ 
				 	"	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES B , "+
					" "+m_schema_name+".AF_CO_MAS_SUB_CHARGES C "+
			    "	WHERE A.APPLICATION_NO=B.APPLICATION_NO AND  "+
			    "	A.FINANCE_NO=UPPER('"+m_finance_no+"') AND "+
				  " B.PRICING_NO='"+m_tmp_pricing_no+"'  AND "+
					" B.PRO_INVOICE_NO='"+m_tmp_invoice_no+"'  AND "+
				  " B.SUB_CHAGE_CODE=C.SUB_TYPE_CODE AND "+
				  " NVL(B.AMOUNT,0) <> 0 "+
				  " GROUP BY B.PRICING_NO,B.SUB_CHAGE_CODE,C.DESCRIPTION ORDER BY  C.DESCRIPTION ");			
					boolean more_charges=rs_charges.next();
					
					if(more_charges){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b><u>Other Charges</b></u></td>");
						out.println("<td width='2%'>&nbsp;</td>"); 
						out.println("<td width='35%' align='left'>&nbsp;</td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table >");
					}
					
					while(more_charges){
					out.println("<table align='center' width='100%' class='table' >");						
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>"+rs_charges.getString(3)+"</b></td>");
					out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs_charges.getDouble(4))+"</td>");
					out.println("<td width='13%' class=div_input>&nbsp;</td>");
					out.println("<td width='20%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='*%' class=div_input>&nbsp;</td>");
					out.println("</tr>");
					out.println("</table >");
          more_charges=rs_charges.next();
					}
					rs_charges.close();
					
					
					out.println("<BR>");	
					if(more){
					m_tmp_asset_id  =rs.getString(1);
					m_tmp_pricing_no=rs.getString(11);
					m_tmp_invoice_no=rs.getString(29);

					}
            if(!more){
						break;
						}
						
						}//end while asset details new

				//======SUMMARY===============================================
				String Sql_summary_tot=" SELECT "+
				" 'A' TOT, "+
				" SUM(NET_AMOUNT), "+ 
				" SUM(VAT_AMOUNT), "+
				" SUM(GROSS_AMOUNT), "+ 
				"  '' DESCRIPTION, "+ 
				"  0 "+ 
				" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING A,"+
				" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+
				" AND B.FINANCE_NO='"+m_finance_no+"' "+
				
				" UNION "+
				
				" SELECT "+
				" 'B' TOT, "+
				" SUM(NET_RENTAL_AMOUNT), "+
				" SUM(GRENTAL_AMOUNT-NET_RENTAL_AMOUNT), "+
				" SUM(GRENTAL_AMOUNT), "+
				"  '' DESCRIPTION, "+ 
				"  0 "+ 
				" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+
				" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+
				" AND B.FINANCE_NO='"+m_finance_no+"' "+
				
				" UNION "+
				
				" SELECT "+
				" 'C' TOT, "+
				" SUM(NET_RENTAL_AMOUNT), "+
				" SUM(GRENTAL_AMOUNT-NET_RENTAL_AMOUNT), "+
				" SUM(GRENTAL_AMOUNT), "+
				"  '' DESCRIPTION, "+ 
				"  0 "+ 
				" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+
				" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+
				" AND B.FINANCE_NO='"+m_finance_no+"' "+
				"	AND INVOICE_NO IS NULL "+
				
				" UNION "+
				
				" SELECT "+
				" 'D' TOT, "+
				"  0, "+ 
				"  0, "+ 
				"  0, "+ 
				"  C.DESCRIPTION DESCRIPTION, "+ 
				"  SUM(B.AMOUNT) "+ 
				"	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES B , "+
				" "+m_schema_name+".AF_CO_MAS_SUB_CHARGES C "+
				"	WHERE A.APPLICATION_NO=B.APPLICATION_NO AND  "+
				"	A.FINANCE_NO=UPPER('"+m_finance_no+"') AND "+
				" B.SUB_CHAGE_CODE=C.SUB_TYPE_CODE AND "+
				" NVL(B.AMOUNT,0) <> 0 "+
				" GROUP BY B.SUB_CHAGE_CODE,C.DESCRIPTION "+		
				" ORDER BY TOT,DESCRIPTION ";
				
				int no_of_receivable=0;
				
				String Sql_summary_receivable=" SELECT "+
				"		DISTINCT COUNT(TO_NUMBER(INSTALLMENT_NO))  "+
				"		FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+
				"		"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				"		WHERE A.APPLICATION_NO=B.APPLICATION_NO "+
				"		AND B.FINANCE_NO='"+m_finance_no+"' "+
				"		AND INVOICE_NO IS NULL "+
				"		GROUP BY A.PRO_INVOICE_NO ";
				
				    rs.close();
						rs=stmt.executeQuery(Sql_summary_receivable);
						more=rs.next();
						if(more){
						no_of_receivable=rs.getInt(1);
						}
				
				    rs.close();
						rs=stmt.executeQuery(Sql_summary_tot);
						more=rs.next();
						int charge_count=0;
						if(more){
						out.println("<br>"); 
						out.println("<hr color='black'>"); 
						out.println("<br>"); 
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b><u>Rental Summary Details</b></u></td>");
						out.println("<td width='2%'>&nbsp;</td>"); 
						out.println("<td width='35%' align='left'>&nbsp;</td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table >");
						}
						while(more){
						if(rs.getString(1).equals("A")){
						out.println("<table align='center' width='100%' border='0' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Total Net Amount</b></td>");
						out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs.getDouble(2))+"</td>");
						out.println("<td width='20%' class=div_input><b>&nbsp;</b></td>");
						out.println("<td width='*%' class=div_input>&nbsp;</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Total VAT Amount</b></td>");
						out.println("<td width='12%' class=div_input  align='right' >"+nf.format(rs.getDouble(3))+"</td>");
						out.println("<td width='20%' class=div_input><b>&nbsp;</b></td>");
						out.println("<td width='*%' class=div_input>&nbsp;</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Total Gross Amount</b></td>");
						out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs.getDouble(4))+"</td>");
						out.println("<td width='20%' class=div_input><b>&nbsp;</b></td>");
						out.println("<td width='*%' class=div_input>&nbsp;</td>");
						out.println("</tr>");
						out.println("</table >");

						}
						else if(rs.getString(1).equals("B")){
						out.println("<table align='center' width='100%' border='0' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Total Rental Net Amount</b></td>");
						out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs.getDouble(2))+"</td>");
						out.println("<td width='20%' class=div_input><b>&nbsp;</b></td>");
						out.println("<td width='*%' class=div_input>&nbsp;</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Total Rental VAT Amount</b></td>");
						out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs.getDouble(3))+"</td>");
						out.println("<td width='20%' class=div_input><b>&nbsp;</b></td>");
						out.println("<td width='*%' class=div_input>&nbsp;</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Total Rental Gross Amount</b></td>");
						out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs.getDouble(4))+"</td>");
						out.println("<td width='20%' class=div_input><b>&nbsp;</b></td>");
						out.println("<td width='*%' class=div_input>&nbsp;</td>");
						out.println("</tr>");
						out.println("</table >");

						}
						else if(rs.getString(1).equals("C")){
						out.println("<table align='center' width='100%' border='0' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Future Installments</b></td>");
						out.println("<td width='12%' class=div_input align='right' >"+no_of_receivable+"</td>");
						out.println("<td width='20%' class=div_input><b>&nbsp;</b></td>");
						out.println("<td width='*%' class=div_input>&nbsp;</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Total Future Receivables - Net</b></td>");
						out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs.getDouble(2))+"</td>");
						out.println("<td width='20%' class=div_input><b>&nbsp;</b></td>");
						out.println("<td width='*%' class=div_input>&nbsp;</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Total Future Receivables - VAT</b></td>");
						out.println("<td width='12%' class=div_input  align='right'>"+nf.format(rs.getDouble(3))+"</td>");
						out.println("<td width='20%' class=div_input><b>&nbsp;</b></td>");
						out.println("<td width='*%' class=div_input>&nbsp;</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Total Future Receivables - Gross</b></td>");
						out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs.getDouble(4))+"</td>");
						out.println("<td width='20%' class=div_input><b>&nbsp;</b></td>");
						out.println("<td width='*%' class=div_input>&nbsp;</td>");
						out.println("</tr>");
						out.println("</table >");
					}
						
						else if(rs.getString(1).equals("D")){
						
						if(charge_count==0){
						out.println("<table align='center' width='100%' border='0' class='table' >");
						out.println("<br>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b><u>Charges Summary Details</u></b></td>");
						out.println("<td width='12%' class=div_input align='right' >&nbsp;</td>");
						out.println("<td width='20%' class=div_input><b>&nbsp;</b></td>");
						out.println("<td width='*%' class=div_input>&nbsp;</td>");
						out.println("</tr>");
						out.println("</table >");
						}
						out.println("<table align='center' width='100%' border='0' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Total&nbsp;"+rs.getString(5)+"</b></td>");
						out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs.getDouble(6))+"</td>");
						out.println("<td width='20%' class=div_input><b>&nbsp;</b></td>");
						out.println("<td width='*%' class=div_input>&nbsp;</td>");
						out.println("</tr>");
						out.println("</table >");
						charge_count=charge_count+1;
						}
						more=rs.next();
						}
											
					
						
						

				

				//===========================================================
				//====================================Rental and Balance Details=======================================	
				int count = 0;
				double sum_net=0;
				double sum_vat=0;
				double sum_tot=0;
				double sum_set=0;
				double sum_bal=0;
				
				double tot_net=0;
				double tot_vat=0;
				double tot_tot=0;
				double tot_set=0;
				double tot_bal=0;
				double tot_rental=0;
				
				double sum_odical_amt=0;
				double sum_odibal_amt=0;
				double sum_odiset_amt=0;
				
				
				double odi_cal_tot=0;
				double odi_bal_tot=0;
				double odi_set_tot=0;
				
					String sql_count=" SELECT A.INST,B.INST,C.INST,D.INST,E.INST,F.INST "+
					" FROM "+
								
					" (SELECT  "+
					" DISTINCT COUNT(INSTALLMENT_NO) INST "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
					" WHERE APPLICATION_NO IN (SELECT APPLICATION_NO  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
					" WHERE FINANCE_NO='"+m_finance_no+"' AND   "+
					" APPLICATION_STATUS <>'CANCEL'  "+
					" )  "+
					" GROUP BY   PRO_INVOICE_NO   "+
					" )A, "+
					" (SELECT COUNT (INVOICE_NO) INST "+
					" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE  "+
					" WHERE  ACTIVE_STATUS='Y' AND   "+
					" FINANCE_NO='"+m_finance_no+"' "+
					" AND INVOICE_TYPE='INV_GENER' "+
					" )B, "+
					
					
					" (SELECT (A.INV+PENDING) INST "+
					" FROM "+
					" (SELECT "+
					" COUNT(INVOICE_NO) INV "+
					" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE "+
					" WHERE FINANCE_NO=('"+m_finance_no+"') "+
					" AND   BALANCE_TO_BE_RECEIVED=0 "+
					" AND  TOTAL_AMOUNT <>0   "+
					" AND  INVOICE_TYPE='INV_GENER')A, "+
					
					" (SELECT "+
					" NVL(ROUND(SUM(SETTELE_AMOUNT)/SUM(TOTAL_AMOUNT),2),0) PENDING "+
					" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE "+
					" WHERE FINANCE_NO=('"+m_finance_no+"') AND "+
					" TOTAL_AMOUNT<>SETTELE_AMOUNT  AND  "+
					" BALANCE_TO_BE_RECEIVED>0 AND  "+
					" TOTAL_AMOUNT IS NOT NULL AND  "+
					" TOTAL_AMOUNT <>0 "+
					" AND SETTELE_AMOUNT >0 "+
					" AND  INVOICE_TYPE='INV_GENER')B ) C ,"+
					
					" (SELECT COUNT (INVOICE_NO) INST "+
					" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE  "+
					" WHERE  ACTIVE_STATUS='Y' AND   "+
					" FINANCE_NO='"+m_finance_no+"' "+
					" AND BALANCE_TO_BE_RECEIVED >0 "+ 
					" AND INVOICE_TYPE='INV_GENER' "+
					" )D, "+
					
					" (SELECT COUNT(INVOICE_NO) INST "+
					" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  "+
					" WHERE INVOICE_NO IN   "+
					" (SELECT INVOICE_NO  "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE  "+
					" WHERE FINANCE_NO='"+m_finance_no+"') "+
					" )E, "+
					" (SELECT COUNT(INVOICE_NO) INST "+
					" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  "+
					" WHERE INVOICE_NO IN   "+
					" (SELECT INVOICE_NO  "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE  "+
					" WHERE FINANCE_NO='"+m_finance_no+"') "+
					" )F ";
					
				String		Sql_Rent=" SELECT "+
							  " APPLICATION_NO, "+
							  " SUM(NET_RENTAL_AMOUNT) NET_RENTAL_AMOUNT "+
							  " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
							  " WHERE APPLICATION_NO IN (SELECT APPLICATION_NO "+
							  "                        FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
								"                          WHERE FINANCE_NO='"+m_finance_no+"' AND "+
							  "                        APPLICATION_STATUS <>'CANCEL' "+
							  "                           ) "+
							  " GROUP BY   APPLICATION_NO      ";                    
        
			  rs=stmt.executeQuery(Sql_Rent);
				more =rs.next();
				double net_rental=0;				
				if(more){
				net_rental=rs.getDouble(2);
				}
				
				
											String		Sql_Rent_details_new=" SELECT "+ 
									//  SUM(NET_AMOUNT), "+
									//  SUM(VAT_AMOUNT), "+
										"  SUM(TOTAL_AMOUNT), "+ 
										"  SUM(SETTELE_AMOUNT), "+
										"  SUM(BALANCE_TO_BE_RECEIVED) "+
										" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
										" WHERE     ACTIVE_STATUS='Y'  "+ 
										" AND INVOICE_TYPE='INV_GENER' "+
										" AND FINANCE_NO='"+m_finance_no+"' ";
				
				rs2=stmt.executeQuery(Sql_Rent_details_new);
				boolean more2 =rs2.next();						
				if(more2){
				//m_net=rs2.getDouble(1);
			//um_vat=rs2.getDouble(2);
				sum_tot=rs2.getDouble(1);
				sum_set=rs2.getDouble(2);
				sum_bal=rs2.getDouble(3);
				//m_bal=sum_tot-sum_set;
				}
				
				String		Sql_OdRent_details= " SELECT SUM(ODI_CAL_AMOUNT),SUM(ODI_BAL_AMOUNT),SUM(ODI_SETTLED_AMOUNT) "+
				                           " FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
																	 " WHERE INVOICE_NO IN "+
																	 " (SELECT INVOICE_NO "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
																	 " WHERE	FINANCE_NO='"+m_finance_no+"')";
						rs2.close();
						rs2=stmt.executeQuery(Sql_OdRent_details);
				    boolean more3 =rs2.next();	
						if(more3){
				    sum_odical_amt=rs2.getDouble(1);
				    sum_odibal_amt=rs2.getDouble(2);
				    sum_odiset_amt=rs2.getDouble(3);
				    }
						
				    if (more) {
					  out.println("<br>"); 
						out.println("<hr color='black'>"); 
						out.println("<br>"); 
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='*%' ><b>Client's Credit Balance Are Not Shown.</b></td>");
						out.println("</tr>"); 
						out.println("</table >"); 
						out.println("<br>"); 
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b><u>Rental and Balance Details</b></u></td>");
						out.println("<td width='2%'>&nbsp;</td>"); 
						out.println("<td width='35%' align='left'>&nbsp;</td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table >"); 
						
				  out.println("<table align='center' width='100%' class='table' border='0' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input ><b>Application No</b></td>");
					out.println("<td width='10%' class=div_input align='right'> <b>Net Rental Amount</b></td>");
					//out.println("<td width='10%' class=div_input align='right'><b>Invoiced Net</b></td>");
					//out.println("<td width='10%' class=div_input align='right'><b>Invoiced VAT</b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>Invoiced Gross</b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>Invoiced Settled</b></td>");
					out.println("<td width='10%'  class=div_input align='right'><b>Balance Outstanding</b></td>");
					out.println("<td width='10%'  class=div_input align='right'><b>ODI Calculate Amount</b></td>");
					out.println("<td width='10%'  class=div_input align='right'><b>ODI Balance Amount</b></td>");
					//out.println("<td width='10%'  class=div_input align='right'><b>ODI Settled Amount</b></td>");
					out.println("</tr>");
					out.println("</table >"); 
										
					out.println("<table align='center' width='100%' class='table' border='0' >");				
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input style= cursor:hand; onclick=show_application_detail_drill('"+m_application_no+"') ><u>"+m_application_no+"</u></td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(net_rental)+"</td>");
					//out.println("<td width='10%' class=div_input align='right' >"+nf.format(sum_net)+"</td>");
					//out.println("<td width='10%' class=div_input align='right' >"+nf.format(sum_vat)+"</td>");
					
					/***********out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_total_rental_drill_2('"+m_finance_no+"') ><u>"+nf.format(sum_tot)+"</u></td>"); //onclick=show_rent_invoiced_drill_2('"+m_application_no+"')
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_rental_paid_drill_2('"+m_finance_no+"')><u>"+nf.format(sum_set)+"</u></td>");
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_outstanding_rental_drill_2('"+m_finance_no+"')><u>"+nf.format(sum_bal)+"</u></td>");
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_odi_cal_drill_2('"+m_application_no+"')><u>"+nf.format(sum_odical_amt)+"</u></td>");
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_odi_bal_drill_2('"+m_application_no+"')><u>"+nf.format(sum_odibal_amt)+"</u></td>");
					
					****************/
					//added by nuwan  de silva on 20-08-07=============================================
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_rent_invoiced_drill('"+m_application_no+"') ><u>"+nf.format(sum_tot)+"</u></td>"); 
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_rent_settled_drill('"+m_application_no+"')><u>"+nf.format(sum_set)+"</u></td>");
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_rent_balance_to_be_received_drill('"+m_application_no+"')><u>"+nf.format(sum_bal)+"</u></td>");
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_odi_bal_drill('"+m_application_no+"')><u>"+nf.format(sum_odical_amt)+"</u></td>");
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_odi_set_drill('"+m_application_no+"')><u>"+nf.format(sum_odibal_amt)+"</u></td>");
					
					//out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_odi_set_drill_2('"+m_application_no+"')><u>"+nf.format(sum_odiset_amt)+"</u></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				
						rs2.close();
						rs2=stmt.executeQuery(sql_count);
				    more3 =rs2.next();	
					double inv_balance=0;
					if(more3){
					
					inv_balance=rs2.getDouble(2)-rs2.getDouble(3)	;
					out.println("<table align='center' width='100%' class='table' border='0' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input ><b>No of Rentals</b></td>");
					out.println("<td width='10%' class=div_input align='right'> <b>"+rs2.getDouble(1)+"</b></td>");
					//out.println("<td width='10%' class=div_input align='right'><b>Invoiced Net</b></td>");
					//out.println("<td width='10%' class=div_input align='right'><b>Invoiced VAT</b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>"+rs2.getDouble(2)+"</b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>"+rs2.getDouble(3)+"</b></td>");
					//out.println("<td width='10%'  class=div_input align='right'><b>"+rs2.getInt(4)+"</b></td>");
					out.println("<td width='10%'  class=div_input align='right'><b>"+inv_balance+"</b></td>");
					
					out.println("<td width='10%'  class=div_input align='right'><b>"+rs2.getDouble(5)+"</b></td>");
					out.println("<td width='10%'  class=div_input align='right'><b>"+rs2.getDouble(6)+"</b></td>");
					//out.println("<td width='10%'  class=div_input align='right'><b>ODI Settled Amount</b></td>");
					out.println("</tr>");
					out.println("</table >"); 
						
						}
					
					
				//==================================balance and receipt details=========================================	
						
					
				//-------------------transaction history------------------
				count = 0;
				String m_string="";		
				String m_orient_name="";
				String m_name="";
				String m_cheque_no="";
				double m_cum_value=0;
				double m_val=0;
				double m_debit=0;
				double m_credit=0;
					
					String		Sql_invoice=" SELECT REF_NO,TO_CHAR(DUE_DATE,'DD-MM-YYYY'),AMOUNT,TYP,NVL(CHEQUE_NO,'-') CHEQUE_NO,DESCRIPTION,STATUS,NO "+
					" FROM "+
					" ( "+
					"  SELECT "+
					"  INVOICE_NO REF_NO, "+
					"  VALUE_DATE   DUE_DATE, "+ // DUE_DATE --modified nuwan de silva 25-07-07 REF NO 708
					"  TOTAL_AMOUNT AMOUNT, "+
					"  'INVOICE' TYP, "+
					"  NVL(NULL,'-') CHEQUE_NO, "+
					//"  'RENTAL & VAT' DESCRIPTION ,"+
					"  DECODE(INVOICE_TYPE,'INV_OTHER','Other Invoice','INV_TAX','Tax Invoice','ODI','OD Interest','INV_RESI','Residual Invoice','INV_GENER','RENTAL & VAT') DESCRIPTION, "+
					"  NULL STATUS ,"+
					"  NVL("+m_schema_name+".AF_CO_GET_INSTALLMENT_NO(INVOICE_NO),' ')  NO "+
					"  FROM "+m_schema_name+".AF_CO_PRO_INVOICE  /*, "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B */ "+
					"  WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					"        ACTIVE_STATUS='Y' /*AND TOTAL_AMOUNT>0*/ "+
					  
					"  UNION "+
					 
						//added by nuwan de silva 16-08-07
					" SELECT "+
					" REC_NO REF_NO,  "+
				  " EFF_VALDATE DUE_DATE,  "+
					" SUM(SETTELED_AMOUNT) AMOUNT,  "+
					" 'RECEIPT' TYP,  "+
					" CHEQUE_NO  CHEQUE_NO , "+
					" DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt') DESCRIPTION,  "+
					" STATUS,  "+
					" '' NO  "+
					" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B,"+m_schema_name+".AF_CO_PRO_INVOICE C "+
					" WHERE RECEIPT_NO=REC_NO AND "+
					" A.INVOICE_NO=C.INVOICE_NO AND "+
					//       C.CLIENT_CODE='0000000476' AND
					" C.FINANCE_NO='"+m_finance_no+"' "+
					" GROUP BY REC_NO,EFF_VALDATE,CHEQUE_NO,SETTLE_MODE,STATUS "+
					
					"	UNION "+
					
					" SELECT "+
					"    INVOICE_NO REF_NO, "+
					"    DUE_DATE  DUE_DATE, "+
					"    ODI_CAL_AMOUNT AMOUNT, "+
					"    'ODI' TYP, "+
					"    NULL CHEQUE_NO, "+
					"    'Over Due Interst' DESCRIPTION, "+
					"    NULL STATUS, "+
					"     '' NO "+ //7
					
					" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
					" WHERE INVOICE_NO IN "+
					" ( SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					"          ACTIVE_STATUS='Y' "+
					") "+
			//		"  AND ACTIVE_STATUS='Y' "+
					" UNION "+
					" SELECT "+
					" INVOICE_NO REF_NO, "+
					" ADJUSTED_DATE DUE_DATE, "+ //ADJUSTED_DATE ///ENT_DATE
					" ADJUSTED_AMOUNT AMOUNT, "+
					" 'DR/CR' TYP, "+
					" NULL CHEQUE_NO, "+
					" DECODE(CREDIT_TYPE,'CR','Credit Note','Debit Note') DESCRIPTION, "+
					" CREDIT_TYPE STATUS, "+
					"     '' NO "+ //7
					" FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS "+
					" WHERE INVOICE_NO IN "+
					" ( SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					"    ACTIVE_STATUS='Y' "+
					" ) "+
				
				 " ) "+ 
					
					" ORDER BY DUE_DATE ";
										
					

				  rs.close();
				  rs=stmt.executeQuery(Sql_invoice);
					boolean  more_inv =rs.next();
					
					if(more_inv){
						out.println("<br>"); 
						out.println("<hr color='black'>"); 
						out.println("<br>"); 
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b><u>Transaction History Details</b></u></td>");
						out.println("<td width='2%'>&nbsp;</td>"); 
						out.println("<td width='35%' align='left'>&nbsp;</td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table >");
					
					}
    				
				while(more_inv){
					count++;
					if(count==1){
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>Date</td>");
					out.println("<td width='15%' class=div_input>Doc Ref</td>");
					out.println("<td width='30%' class=div_input>Narration</td>");
					out.println("<td width='10%' class=div_input>Cheque No.</td>");
					out.println("<td width='10%' class=div_input align='right'>Debit</td>");
					out.println("<td width='10%' class=div_input align='right'>Credit</td>");
					out.println("<td width='10%' class=div_input align='right'>Cum. Value</td>");
					out.println("<td width='4%' class=div_input></td>");
					out.println("</tr>");
					}
					m_debit =0;
					m_credit=0;
					if(rs.getString(4).equals("INVOICE")){
					m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("RECEIPT")){
					if(rs.getString(7).equals("RET")){
					m_debit=rs.getDouble(3);
          }
					else
					{
					m_credit=rs.getDouble(3);
					}
					}
					else if(rs.getString(4).equals("DR/CR")){
					if(rs.getString(7).equals("DR")){
					m_debit=rs.getDouble(3);
          }
					else
					{
					m_credit=rs.getDouble(3);
					}
					}
					else if(rs.getString(4).equals("ODI")){
					m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("OTHER")){
					m_debit=rs.getDouble(3);
					}
					m_val=m_debit-m_credit;
          m_cum_value=m_cum_value+m_val;
					if(rs.getString(4).equals("INVOICE")){
					out.println("<tr>");
					out.println("<td width='1%'>"+rs.getString(8)+"</td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info_2('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					else if(rs.getString(4).equals("RECEIPT")){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					if(rs.getString(7).equals("RET") ){
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					else if(rs.getString(4).equals("ODI")){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					else if(rs.getString(4).equals("OTHER")){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input >"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_payment_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					else if(rs.getString(4).equals("DR/CR")){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input>"+rs.getString(1)+"</td>"); //style= cursor:hand; show_invoice_drill('"+rs.getString(1)+"')
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					if(rs.getString(7).equals("DR") ){
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					more_inv = rs.next();
				}
				out.println("</table>");
				
				
				
			  /* String Sql_all =	" SELECT (A.AMOUNT-B.AMOUNT) "+
				" FROM "+
				" (SELECT  "+
				" SUM(REC_AMOUNT) AMOUNT   "+
				" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+ 
				" WHERE CLIENT_CODE='"+m_client_code+"'  "+
				" AND STATUS <> 'C' ) A, "+
				
				/*" (SELECT  "+
				" SUM(SETTELED_AMOUNT) AMOUNT   "+
				" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B,"+m_schema_name+".AF_CO_PRO_INVOICE C  "+
				" WHERE RECEIPT_NO=REC_NO AND  "+
				" A.INVOICE_NO=C.INVOICE_NO AND  "+
				" C.CLIENT_CODE='"+m_client_code+"') B ";
				*/
				/*" (SELECT "+ 
				" SUM(SETTELE_AMOUNT) AMOUNT "+
				" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
				" WHERE     ACTIVE_STATUS='Y'  "+ 
				" AND CLIENT_CODE='"+m_client_code+"') B ";
				*/
				
				
				String Sql_all ="	  SELECT "+
				"		NVL(SUM(BAL_TOBE_RECEIVE),0) "+
				"		FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL A, "+m_schema_name+"DATA.AF_CO_PRO_RECEIPT_DET C "+ //OFSCLDATA
				"		WHERE C.REC_NO=A.REC_NO  "+
				"		AND   UPPER(C.CONTRACT_NO)=UPPER('"+m_finance_no+"') ";
				
				
						
				  m_cum_value=0;
				  rs.close();
				  rs=stmt.executeQuery(Sql_all);
					boolean  more_inv_all =rs.next();
					if(more_inv_all){
					m_cum_value=rs.getDouble(1);
					}

				  if(m_cum_value>0){
					out.println("<br>");
					out.println("<hr color='black'>");
					out.println("<table align='center' width='100%' class='table' border='0' bordercolor='black' cellspacing='0' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='85%' class=div_input><b>Non Allocated Credit Balance Available For This Client<b></td>");
					out.println("<td width='85%' class=div_input><b>Non Allocated Credit Balance Available For This Contract<b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>"+nf.format(a.abs(m_cum_value))+"</b></td>");
					out.println("<td width='4%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					}
				
				
				
  String Sql_future_rentals= "  SELECT "+
   "  TO_NUMBER(INSTALLMENT_NO) +1 , "+ //modified by nuwan de silva on 20-08-07
	 "  TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'), "+
   "  SUM(NET_RENTAL_AMOUNT), "+
   "  SUM(GRENTAL_AMOUNT-NET_RENTAL_AMOUNT), "+
   "  SUM(GRENTAL_AMOUNT) "+ 
   "  FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+
   "  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
   "  WHERE INVOICE_NO IS NULL  "+
   "  AND A.APPLICATION_NO=B.APPLICATION_NO "+
   "  AND B.FINANCE_NO='"+m_finance_no+"' "+
	 "	GROUP BY INSTALLMENT_NO, "+
	 " TO_CHAR(RENTAL_DATE,'DD-MM-YYYY')  "+
	 " ORDER BY TO_NUMBER(INSTALLMENT_NO) ";	
		
		sum_net=0;
		sum_vat=0;
		double sum_gross=0;
		sum_set=0;
		sum_bal=0;
		int   flag=0;
		
		//--------future-----------------------------
		
		     rs.close();			
				 rs=stmt.executeQuery(Sql_future_rentals);
				 more =rs.next();
					if (more) {
						out.println("<br>"); 
						out.println("<hr color='black'>"); 
						out.println("<br>"); 
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><b><u>Future Rental Receivables</b></u></td>");
						out.println("<td width='2%'>&nbsp;</td>"); 
						out.println("<td width='35%' align='left'>&nbsp;</td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table >");
						
						out.println("<br>"); 				
						
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input ><b>Installment No</b></td>");
					out.println("<td width='20%' class=div_input align='left'><b>Rental Date</b></td>");
					out.println("<td width='20%' class=div_input align='right'><b>Net Amount</b></td>");
					out.println("<td width='20%' class=div_input align='right'><b>VAT Amount</b></td>");
					out.println("<td width='20%' class=div_input align='right'><b>Gross Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				
				while(more){
					count++;
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input align='left'>"+rs.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input align='left'>"+rs.getString(2)+"</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("</tr>");
					flag=1;
					sum_gross=sum_gross+rs.getDouble(3);
					sum_net=sum_net+rs.getDouble(4);
					sum_bal=sum_bal+rs.getDouble(5);
     			more = rs.next();
					
				}
				
					if(flag==1){
				 	out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input align='left'><b>&nbsp;</b></td>");
					out.println("<td width='20%' class=div_input><b>Total</b> </td>");
					out.println("<td width='20%' class=div_input align='right'><b>"+nf.format(sum_gross)+"</b></td>");
					out.println("<td width='20%' class=div_input align='right'><b>"+nf.format(sum_net)+"</b></td>");
					out.println("<td width='20%' class=div_input align='right'><b>"+nf.format(sum_bal)+"</b></td>");
					out.println("</tr>");
		      }
				  out.println("</table>");


		  	  rs.close();
		    
					  
						out.println("<br >");
						out.println("<hr color='black' >");
						out.println("<br >");
						out.println("<table align='center' width='100%' class='table' >");
					  out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%'><b>ML No</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='35%'><DIV class=div_input>...................................</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>"); 
						out.println("<br >");
						out.println("<br >");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%'><b>Insurance</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='35%'><DIV class=div_input>...................................</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>"); 
						out.println("<br >");
						out.println("<br >");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%'><b>Cheque Return</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='35%'><DIV class=div_input>...................................</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>"); 
						out.println("<br >");
						out.println("<br >");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%'><b>Outstanding - Insurance</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='35%'><DIV class=div_input>...................................</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>"); 
						out.println("<br >");
						out.println("<br >");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%'><b>Outstanding - Cheque Return</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='35%'><DIV class=div_input>...................................</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>"); 
						out.println("<br >");
						out.println("<br >");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%'><b>Outstanding - ODI</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='35%'><DIV class=div_input>...................................</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>"); 
						out.println("<br >");
						out.println("<br >");
											
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%'><b>Checked By</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='35%'><DIV class=div_input>...................................</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>"); 
						out.println("<br >");
						out.println("<br >");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%'><b>Certified By</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='35%'><DIV class=div_input>...................................</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>");		
						

						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

			
	    }
						
							
			else {
			    out.println("Undefined");
			}

      out.close();
			conn.close();
			this.destroy();
			
			
			}
			catch (Exception e) {
				try {
						conn.close();
				}catch (Exception eti) {}
			
					ByteArrayOutputStream ostr = new ByteArrayOutputStream();
					e.printStackTrace(new PrintWriter(ostr));
			
					ServletOutputStream out = res.getOutputStream();
					out.println(ostr.toString());
      		out.close();
			
			}
	}
}


