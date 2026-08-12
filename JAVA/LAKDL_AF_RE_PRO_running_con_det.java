import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : MAHELA FOR OFSCL LEASING    DATE:23-03-2007

public class LAKDL_AF_RE_PRO_running_con_det extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt_invoice;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;

    public ResultSet rs,rs1,rs2,rs3,rs4,rs_invoice;
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			//**************************************************************					
			//**************************************************************					
			//--MODIFIED By :delanjali----------------------------------------
			//--DATE				: 2007-07-27--------------------------------------
			
			
			
			String m_html_client_url1=m_sn_methods.html_client_url.trim(); 

			String url = "";
			if(req.getParameter("url")!=null){
			url=req.getParameter("url");
			}
			
			if(url.equals("http://www.lakdac.lk")){
	 		m_html_client_url="http://www.lakdac.lk"; 
			m_class_url="http://www.lakdac.lk:/myserver/servlet"; 
			}
		
			else{
		
			m_html_client_url=m_sn_methods.html_client_url.trim(); 
			}
			
			
			//---------------------------------------------------------------
			
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
			stmt1=conn.createStatement();
			stmt=conn.createStatement();
			stmt_invoice=conn.createStatement();
			
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			
			
			else if(m_chksql.equals("SHOW_RUNNING_CON_DETAILS")){
				
				int count = 0;
				String m_string="";								
				String m_application_no=req.getParameter("application_no");
			/*	
				
					out.println("<HTML><HEAD><TITLE> Security Running Case - Application No : "+m_application_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Asset Details - Application No : "+m_application_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");


																									
					String		Sql_Asset=  " SELECT "+
								  "  ASSIGNED_FINANCE_NO, "+
								  "  "+m_schema_name+".AF_CO_GET_APPLICATION_NO(ASSIGNED_FINANCE_NO), "+
								  "  "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(ASSIGNED_FINANCE_NO)) "+
								  "  FROM "+m_schema_name+".AF_ASSET_RUN_CONTRACTS "+
									"  WHERE UPPER(APP_NO)=UPPER('"+m_application_no+"')  ";
								        
													 									
												  

		    rs=stmt1.executeQuery(Sql_Asset);
				
				boolean  more =rs.next();
						
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Application No "+m_application_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				    out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input><b>Finance No</b></td>");
					out.println("<td width='10%' class=div_input><b>Application No</b></td>");
					out.println("<td width='10%' class=div_input><b>Client Code</b></td>");
					out.println("</tr>");
					out.println("</table>");
				
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input >"+rs.getString(1)+"</td>");
					out.println("<td width='10%' class=div_input >"+rs.getString(2)+"</td>");
					out.println("<td width='10%' class=div_input >"+rs.getString(3)+"</td>");
					out.println("</tr>");
					
					more = rs.next();
				}
				
		
				  out.println("</table>");
					
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					
		       */
			
					out.println("<HTML><HEAD><TITLE> Security Running Case - Application No : "+m_application_no+" </TITLE></HEAD>");
					
					out.println(" <SCRIPT language1.2='JavaScript' > ");
					
					out.println("	function show_transaction_info(m_client_code,m_finance_no){");
					out.println("    	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
					out.println("    	window.open(m_url); ");
					out.println("	}");	
					
					out.println(" </SCRIPT> ");
					
					
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Asset Details - Application No : "+m_application_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
			
					out.println("<table width=\"100%\"  align=\"left\" class=\"table\" border=\"0\"  cellpadding=\"2\"> "); 
	
					stmt = conn.createStatement ();
			
					rs = stmt.executeQuery(	" SELECT "+ 
													        " VEHICLE_NO, "+ //1
													        " CUSTOMER_NAME, "+ //2
													        " NVL(VEHICLE_TYPE,'-') VEHICLE_TYPE, "+ //3
													        " NVL(MODEL_CODE,'-') MODEL_CODE, "+ //4
													        " NVL(YEAR_OF_MANUFACTURE,0) YEAR_OF_MANUFACTURE, "+ //5
													        " NVL(ENGINE_NO,'-') ENGINE_NO, "+ //6
													        " NVL(VALUE,0) VALUE, "+ //7
																	" DECODE(CONDITION_OF_ASSET,'N','New','R','Recondition'), "+ //8
																	" NVL(APPLICATION_NO,'-') APPLICATION_NO, "+ //9
																	" NVL(REMARKS,'-') REMARKS "+ // 10 added by udara 10-12-2019
													        " FROM "+m_schema_name+".AF_MK_APP_SECURITY_VEHICLE "+
													        " WHERE UPPER(APPLICATION_NO) = UPPER('"+m_application_no+"') "+
													        " ORDER BY VEHICLE_NO DESC ");
	    
					boolean more = rs.next();		
					int i=0;
					int line_no=0;

			
			  if(more){
				out.println(" <tr > ");
				out.println(" <td > ");
				out.println("<table width=\"100%\"  align=\"center\" class=\"table\" border=\"0\" cellpadding=\"0\"> ");  // out.println("<table width=\"100%\"  align=\"left\" class=\"table\" border=\"1\"  cellpadding=\"2\"> "); 
				out.println("<tr ><td width=\"10%\" align='left'><b><u>Vehicle</u></b></td> <td colspan=8 > &nbsp; </td> </tr>");
				out.println(" <tr > ");
				out.println("  <td width=\"10%\"  align='left'><b>Vehicle Number</b></td> ");
				out.println("  <td width=\"15%\"  align='left'><b>Customer Name</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Vehicle Type</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Make and Model</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Year of Manufacture</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Engine Number</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Value</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Condition of Asset</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Remarks</b></td> "); // added by udara 10-12-2019
				out.println("  <td width=\"10%\"  align='left'> &nbsp; </td> ");
				out.println(" </tr>"); 
				}

				while(more){
						if(i>0 && i%2==1){
								out.println("<tr class=tr_input1 >");
						}
						else{
								out.println("<tr class=tr_input >");
						}

						out.println("  <td width=\"10%\"  align='left'>"+rs.getString(1)+"</td> ");
						out.println("  <td width=\"15%\"  align='left'>"+rs.getString(2)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs.getString(3)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs.getString(4)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs.getInt(5)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs.getString(6)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+nf.format(rs.getDouble(7))+"</td> "); // right
						out.println("  <td width=\"10%\"  align='left'>"+rs.getString(8)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs.getString(10)+"</td> "); // added by udara 10-12-2019
						out.println("  <td width=\"10%\"  align='left'> &nbsp; </td> "); // out.println("  <td width=\"10%\"  align='center'><input class=\"but_input\" value=\"Edit\" type=\"button\" name=BUT_EDIT"+line_no+"  onClick=\"vehicle_edit('"+rs.getString(1)+"','"+rs.getString(9)+"')\" ></td>");
						out.println(" </tr>");
						i=i+1;
						line_no=line_no+1;
						more=rs.next();
				}
				//out.println("</table >"); // udara 26-11-2014
				out.println(" </td > ");
				out.println(" </tr > ");

				rs.close();

				//////////////////////////////////////////////////////////////////////
				stmt1 = conn.createStatement ();
		
				rs1 = stmt1.executeQuery(" SELECT "+ 
												        " MORTGAGE_NO, "+ //1
																" DECODE(MORTGAGE_TYPE,'P','Primary Mortgage','S','Secondary Mortgage','C','Caveat'), "+ //2
												        " NVL(DEED_NO,'-') DEED_NO, "+ //3
												        " NVL(ADDRESS,'-') ADDRESS, "+ //4 
												        " NVL(VALUE,0) VALUE, "+ //5 
												        " NVL(VALUES_NAME,'-') VALUES_NAME, "+ //6
												        " TO_CHAR(VALUATION_DATE,'DD-MM-YYYY') VALUATION_DATE, "+ //7
												        " NVL(REMARKS,'-') REMARKS, "+ //8
																" NVL(APPLICATION_NO,'-') APPLICATION_NO "+ //9
												        " FROM "+m_schema_name+".AF_MK_APP_SECURITY_LAND "+
												        " WHERE UPPER(APPLICATION_NO) = UPPER('"+m_application_no+"') "+
												        " ORDER BY MORTGAGE_NO DESC ");
    
				boolean more1 = rs1.next();		
				int i1=0;
				int line_no1=0;
				
				if(more1){
				out.println(" <tr > ");
				out.println(" <td > ");
				//out.println("<table width=\"100%\"  align=\"center\" class=\"table\" border=\"0\" cellpadding=\"0\"> "); // udara 26-11-2014  //out.println("<table width=\"100%\"  align=\"left\" class=\"table\" border=\"1\" cellpadding=\"0\"> "); 
				out.println("<tr ><td width=\"10%\" align='left'><u><b>Land</b></u></td>  <td colspan=8 > &nbsp; </td> </tr>");
				out.println(" <tr > ");
				out.println("  <td width=\"10%\"  align='left'><b>Mortgage Number</b></td> ");
				out.println("  <td width=\"15%\"  align='left'><b>Mortgage Type</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Deed Number</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Land Situated At</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Value of the Land</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Values Name</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Valuation Date</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Special Remarks</b></td> ");
				out.println("  <td width=\"10%\"  align='left'> &nbsp; </td> ");
				out.println(" </tr>");

				}
		
				while(more1){
						if(i1>0 && i1%2==1){
								out.println("<tr class=tr_input1 >");
						}
						else{
								out.println("<tr class=tr_input >");
						}

						out.println("  <td width=\"10%\"  align='left'>"+rs1.getString(1)+"</td> ");
						out.println("  <td width=\"15%\"  align='left'>"+rs1.getString(2)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs1.getString(3)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs1.getString(4)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+nf.format(rs1.getDouble(5))+"</td> "); // right
						out.println("  <td width=\"10%\"  align='left'>"+rs1.getString(6)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs1.getString(7)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs1.getString(8)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'> &nbsp; </td> "); // out.println("  <td width=\"10%\"  align='center'><input class=\"but_input\" value=\"Edit\" type=\"button\" type=\"button\" name=BUT_EDIT"+line_no1+"  onClick=\"land_edit('"+rs1.getString(1)+"','"+rs1.getString(9)+"')\" ></td>"); 
						out.println(" </tr>");
						i1=i1+1;
						line_no1=line_no1+1;
						more1=rs1.next();
				}
				//out.println("</table >"); // udara 26-11-2014
				out.println(" </td > ");
				out.println(" </tr > ");
				rs1.close();
				
				//////////////////////////////////////////////////////////////
				
				stmt2 = conn.createStatement ();
		
				rs2 = stmt2.executeQuery(" SELECT "+ 
												        " FD_ACC_NO, "+ //1
												        " NVL(AMOUNT,0) AMOUNT, "+ //2
												        " TO_CHAR(STARTING_DATE,'DD-MM-YYYY') STARTING_DATE, "+ //3
												        " TO_CHAR(MATURITY_DATE,'DD-MM-YYYY') MATURITY_DATE, "+ //4
												        " TO_CHAR(INTEREST_DATE,'DD-MM-YYYY') INTEREST_DATE, "+ //5
												        " NVL(INTEREST_PAYABLE,'-') INTEREST_PAYABLE, "+ //6
												        " NVL(PERIOD,0) PERIOD, "+ //7
												        " NVL(REMARKS,'-') REMARKS, "+ //8
																" NVL(APPLICATION_NO,'-') APPLICATION_NO "+ //9
												        " FROM "+m_schema_name+".AF_MK_APP_SECURITY_FIXED_DEP "+
												        " WHERE UPPER(APPLICATION_NO) = UPPER('"+m_application_no+"') "+
												        " ORDER BY FD_ACC_NO DESC ");
    
				boolean more2 = rs2.next();		
				int i2=0;
				int line_no2=0;

				if(more2){
				out.println(" <tr > ");
				out.println(" <td > ");

				//out.println("<table width=\"100%\"  align=\"center\" class=\"table\" border=\"0\" cellpadding=\"0\"> "); // udara 26-11-2014 // out.println("<table width=\"100%\"  align=\"left\" class=\"table\" border=\"1\"  cellpadding=\"2\"> "); 
				out.println("<tr ><td width=\"20%\" align='left'><b><u>Fixed Deposit</u></b></td> <td colspan=8 > &nbsp; </td> </tr>");
				out.println(" <tr> ");
				out.println("  <td width=\"10%\"  align='left'><b>Fixed Deposit A/C Number</b></td> ");
				out.println("  <td width=\"15%\"  align='left'><b>Amount</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Starting Date</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Maturity Date</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Interest Date</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Interest Payable</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Period</b></td> ");
				out.println("  <td width=\"10%\"  align='left'><b>Special Remarks</b></td> ");
				out.println("  <td width=\"10%\"  align='left'> &nbsp; </td> ");
				out.println(" </tr>");
				}
				

				while(more2){
						if(i2>0 && i2%2==1){
								out.println("<tr class=tr_input1 >");
						}
						else{
								out.println("<tr class=tr_input >");
						}

						out.println("  <td width=\"10%\"  align='left'>"+rs2.getString(1)+"</td> ");
						out.println("  <td width=\"15%\"  align='left'>"+nf.format(rs2.getDouble(2))+"</td> "); // right
						out.println("  <td width=\"10%\"  align='left'>"+rs2.getString(3)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs2.getString(4)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs2.getString(5)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs2.getString(6)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs2.getInt(7)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs2.getString(8)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'> &nbsp; </td> "); // out.println("  <td width=\"10%\"  align='center'><input class=\"but_input\" value=\"Edit\" type=\"button\" name=BUT_EDIT"+line_no2+"  onClick=\"fixed_deposit_edit('"+rs2.getString(1)+"','"+rs2.getString(9)+"')\"></td>"); 
						out.println(" </tr>");
						i2=i2+1;
						line_no2=line_no2+1;
						more2=rs2.next();
				}
				//out.println("</table >"); // udara 26-11-2014
				out.println(" </td > ");
				out.println(" </tr > ");
				
				rs2.close();
				
				
				// ========================== added by udara 27-11-2014 ===============================================================
				
				stmt4 = conn.createStatement ();
				rs4 = stmt4.executeQuery(" SELECT "+ 
												" ASSIGNED_FINANCE_NO, "+ //1
												" "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(ASSIGNED_FINANCE_NO)) "+
												" FROM "+m_schema_name+".AF_ASSET_RUN_CONTRACTS "+
												" WHERE UPPER(APP_NO) = UPPER('"+m_application_no+"') "+
												" ORDER BY ASSIGNED_FINANCE_NO DESC ");
    
				boolean more4 = rs4.next();		
				int i4=0;
				int line_no4=0;
				
				
				if(more4){
					
					out.println(" <tr > ");
					out.println(" <td > ");
					//out.println("<table width=\"100%\"  align=\"left\" class=\"table\" border=\"0\" cellpadding=\"0\"> "); // udara 21-10-2014
					out.println("<tr>");
					out.println("  <td width=\"15%\"  align='left'><u><b>Running Contract</b></u></td>");
					out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
					out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
					out.println("  <td width=\"*%\"   align='left' colspan=6 > &nbsp; </td> ");
					out.println("</tr>");
					
					out.println(" <tr> ");
					out.println("  <td width=\"15%\"  align='left'><b>Finance Number</b></td> ");
					out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
					out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
					out.println("  <td width=\"*%\"   align='left' colspan=6 > &nbsp; </td> ");
					out.println(" </tr>");
					

				}
		
				
				while(more4){
					
						if(i4>0 && i4%2==1){
								out.println("<tr class=tr_input1 >");
						}
						else{
								out.println("<tr class=tr_input >");
						}
						
						out.println("  <td width=\"10%\"  align='left' style= cursor:hand; onclick=\"show_transaction_info('"+rs4.getString(2)+"','"+rs4.getString(1)+"');\" ><u>"+rs4.getString(1)+"</u></td> "); // out.println("  <td width=\"15%\"  align='left'>"+rs4.getString(1)+"</td> ");
						out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
					    out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
						out.println("  <td width=\"*%\"  align='left' colspan=6 > &nbsp; </td> ");
						out.println(" </tr>");
						

						i4=i4+1;
						line_no4=line_no4+1;
						more4=rs4.next();
				}
				
				/*
				out.println("</table >");
				out.println(" </td > ");
				out.println(" </tr > ");
				
				rs4.close();
				*/
				
				// ========================== end by udara 27-11-2014 =================================================================
				
				
				
				
				// ====================== added by udara 03-04-2014 ===================================================================
			
				
			    stmt3 = conn.createStatement ();

				
				rs3 = stmt3.executeQuery(" "+
							" SELECT "+
					         " "+m_schema_name+".AF_CO_GET_FINANCE_NO(APP_NO), "+
					         " "+m_schema_name+".AF_CO_GET_CLIENT_CODE(APP_NO) "+
					         " FROM "+m_schema_name+".AF_ASSET_RUN_CONTRACTS "+
						         " WHERE ASSIGNED_FINANCE_NO = "+m_schema_name+".AF_CO_GET_FINANCE_NO('"+m_application_no+"') "+
								 " AND   "+m_schema_name+".AF_CO_GET_FINANCE_NO(APP_NO) IS NOT NULL "+
						         " ORDER BY ASSIGNED_FINANCE_NO DESC "+							
							" ");
    
				boolean more3 = rs3.next();		
				int i3=0;
				int line_no3=0;
				
				
				if(more3){
					
					/*
					out.println(" <tr > ");
					out.println(" <td > ");
					*/
					//out.println("<table width=\"100%\"  align=\"left\" class=\"table\" border=\"0\" cellpadding=\"0\"> "); // udara 21-10-2014
					out.println("<tr>");
					out.println("  <td width=\"15%\"  align='left' colspan=2 ><u><b>Security Connected Contract</b></u></td>");
					out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
					//out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
					out.println("  <td width=\"*%\"   align='left' colspan=6 > &nbsp; </td> ");
					out.println("</tr>");
					
					out.println(" <tr> ");
					out.println("  <td width=\"15%\"  align='left' colspan=2 ><b>Finance Number</b></td> ");
					out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
					//out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
					out.println("  <td width=\"*%\"   align='left' colspan=6 > &nbsp; </td> ");
					out.println(" </tr>");
					

				}
		
				
				while(more3){
					
					
						if(i3>0 && i3%2==1){
								out.println("<tr class=tr_input1 >");
						}
						else{
								out.println("<tr class=tr_input >");
						}
						
						out.println("  <td width=\"10%\"  align='left' style= cursor:hand; onclick=\"show_transaction_info('"+rs3.getString(2)+"','"+rs3.getString(1)+"');\" colspan=2 ><u>"+rs3.getString(1)+"</u></td> "); // out.println("  <td width=\"15%\"  align='left'>"+rs3.getString(1)+"</td> ");
						out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
					    //out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
						out.println("  <td width=\"*%\"  align='left' colspan=6 > &nbsp; </td> ");
						out.println(" </tr>");
						

						i3=i3+1;
						line_no3=line_no3+1;
						more3=rs3.next();
				}
				
				/*
				out.println("</table >");
				out.println(" </td > ");
				out.println(" </tr > ");
				*/
				// rs3.close();
				
			
			
				// ====================== end by udara 03-04-2014 =====================================================================
				
				
				// ====================== added by udara 30-10-2018 ===================================================================
				out.println("<tr> <td colspan=9 > &nbsp; </td></tr>");
				
				out.println("<tr>");
				out.println("  <td width=\"15%\"  align='left' ><u><b>Pledge Details</b></u></td>");
				out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
				out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
				out.println("  <td width=\"*%\"   align='left' colspan=6 > &nbsp; </td> ");
				out.println("</tr>");
				
				out.println("<tr> <td colspan=9 > &nbsp; </td></tr>");
				
				
				stmt4 = conn.createStatement ();
				rs4 = stmt4.executeQuery(" SELECT "+ 
												" APPLICATION_NO, "+ //1
												" NVL(FINANCE_NO,'-'), "+
												" CLIENT_CODE "+
												" FROM "+m_schema_name+".AF_MAS_PLEDGE_CONTRACTS "+
												" WHERE PLEDGE_CONTRACT = "+m_schema_name+".AF_CO_GET_FINANCE_NO('"+m_application_no+"') "+
												" ");
    
				more4 = rs4.next();		
				i4=0;
				line_no4=0;
				
				
				if(more4){


					out.println("<tr>");
					out.println("  <td width=\"15%\"  align='left'><u><b>Loan Contract</b></u></td>");
					out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
					out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
					out.println("  <td width=\"*%\"   align='left' colspan=6 > &nbsp; </td> ");
					out.println("</tr>");
					
					out.println(" <tr> ");
					out.println("  <td width=\"15%\"  align='left'><b>Loan Number</b></td> ");
					out.println("  <td width=\"5%\"  align='left'><b>Application No</b></td> ");
					out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
					out.println("  <td width=\"*%\"   align='left' colspan=6 > &nbsp; </td> ");
					out.println(" </tr>");
					

				}
		
				
				while(more4){
					
						if(i4>0 && i4%2==1){
								out.println("<tr class=tr_input1 >");
						}
						else{
								out.println("<tr class=tr_input >");
						}
						
						out.println("  <td width=\"10%\"  align='left' style= cursor:hand; onclick=\"show_transaction_info('"+rs4.getString(3)+"','"+rs4.getString(2)+"');\" ><u>"+rs4.getString(2)+"</u></td> "); // out.println("  <td width=\"15%\"  align='left'>"+rs4.getString(1)+"</td> ");
						out.println("  <td width=\"5%\"  align='left'>"+rs4.getString(1)+"</td> ");
						out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
						out.println("  <td width=\"*%\"  align='left' colspan=6 > &nbsp; </td> ");
						out.println(" </tr>");
						

						i4=i4+1;
						line_no4=line_no4+1;
						more4=rs4.next();
				}
				
				
				out.println("<tr> </tr>");

				
				stmt4 = conn.createStatement ();
				rs4 = stmt4.executeQuery(" SELECT "+ 
												" B.APPLICATION_NO, "+ //1
												" B.FINANCE_NO, "+
												" B.CLIENT_CODE "+
												" FROM "+m_schema_name+".AF_MAS_PLEDGE_CONTRACTS A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
												" WHERE A.PLEDGE_CONTRACT = B.FINANCE_NO "+
												" AND A.FINANCE_NO = "+m_schema_name+".AF_CO_GET_FINANCE_NO('"+m_application_no+"') "+
												" ");
    
				 more4 = rs4.next();		
				 i4=0;
				 line_no4=0;
				
				
				if(more4){
					


					out.println("<tr>");
					out.println("  <td width=\"15%\"  align='left'><u><b>Pledge Contract</b></u></td>");
					out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
					out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
					out.println("  <td width=\"*%\"   align='left' colspan=6 > &nbsp; </td> ");
					out.println("</tr>");
					
					out.println(" <tr> ");
					out.println("  <td width=\"15%\"  align='left'><b>Pledge Number</b></td> ");
					out.println("  <td width=\"5%\"  align='left'><b>Application No</b></td> ");
					out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
					out.println("  <td width=\"*%\"   align='left' colspan=6 > &nbsp; </td> ");
					out.println(" </tr>");
					

				}
		
				
				while(more4){
					
						if(i4>0 && i4%2==1){
								out.println("<tr class=tr_input1 >");
						}
						else{
								out.println("<tr class=tr_input >");
						}
						
						out.println("  <td width=\"10%\"  align='left' style= cursor:hand; onclick=\"show_transaction_info('"+rs4.getString(3)+"','"+rs4.getString(2)+"');\" ><u>"+rs4.getString(2)+"</u></td> "); // out.println("  <td width=\"15%\"  align='left'>"+rs4.getString(1)+"</td> ");
						out.println("  <td width=\"10%\"  align='left'>"+rs4.getString(1)+"</td> ");
						out.println("  <td width=\"5%\"  align='left'> &nbsp; </td> ");
						out.println("  <td width=\"*%\"  align='left' colspan=6 > &nbsp; </td> ");
						out.println(" </tr>");
						

						i4=i4+1;
						line_no4=line_no4+1;
						more4=rs4.next();
				}
				

				//rs4.close();
				// ====================== end by udara 30-10-2018 =====================================================================


				// added by kasun on 27-11-2024

				stmt4 = conn.createStatement ();
				rs4 = stmt4.executeQuery(" SELECT "+ 
											" "+m_schema_name+".AF_CO_GET_ITEM_SUB_DESC_2(ITEM_SUB_CAT), "+ //1
											" NVL(REMARKS,'-') REMARKS, "+ //2
											" ITEM_SUB_CAT "+ //3
											" FROM "+m_schema_name+".AF_ASSET_PRO_HADAGASMA "+
											" WHERE UPPER(APP_NO) = UPPER('"+m_application_no+"') "+
											" ORDER BY ITEM_SUB_CAT DESC");
    
				more4 = rs4.next();		
				i4=0;
				line_no4=0;
				
				
				if(more4){

					stmt3 = conn.createStatement ();
					rs3 = stmt3.executeQuery(" SELECT "+ 
											" NVL("+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE('"+m_application_no+"'),'N/A'), "+ //1
											" NVL(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),'N/A') ACTIVATED_DATE "+ //2
											" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
											" WHERE UPPER(APPLICATION_NO) = UPPER('"+m_application_no+"') "+
											" ");
					more3 = rs3.next();	
					
					out.println("<tr>");
					out.println("  <td width=\"15%\"  align='left'><u><b>Hadagasma Product</b></u></td>");
					out.println("  <td width=\"15%\"  align='left'> &nbsp; </td> ");
					out.println("  <td width=\"15%\"  align='left'> &nbsp; </td> ");
					out.println("  <td width=\"15%\"  align='left'> &nbsp; </td> ");
					out.println("  <td width=\"*%\"   align='left' colspan=6 > &nbsp; </td> ");
					out.println("</tr>");

					if(more3){

					out.println(" <tr> ");
					out.println("  <td width=\"15%\"  align='left'><b>Contract Activation Date : "+rs3.getString(2)+"</b></td> ");
					out.println("  <td width=\"15%\"  align='left'><b>Last Rental Date : "+rs3.getString(1)+"</b></td> ");
					out.println("  <td width=\"15%\"  align='left'> &nbsp; </td> ");
					out.println("  <td width=\"*%\"   align='left' colspan=6 > &nbsp; </td> ");
					out.println(" </tr>");

					}

					out.println(" <tr> ");
					// out.println("  <td width=\"15%\"  align='left'><b>Contract Activation Date</b></td> ");
					// out.println("  <td width=\"15%\"  align='left'><b>Last Rental Date</b></td> ");
					// out.println("  <td width=\"15%\"  align='left'> &nbsp; </td> ");
					// out.println("  <td width=\"*%\"   align='left' colspan=6 > &nbsp; </td> ");
					out.println(" </tr>");

					
					out.println(" <tr> ");
					out.println("  <td width=\"15%\"  align='left'><b>Item Sub Category</b></td> ");
					out.println("  <td width=\"15%\"  align='left'><b>Remark</b></td> ");
					out.println("  <td width=\"15%\"  align='left'><b>Application No</b></td> ");
					out.println("  <td width=\"*%\"   align='left' colspan=6 > &nbsp; </td> ");
					out.println(" </tr>");
					

				}
				rs3.close();
				
				while(more4){
					
						if(i4>0 && i4%2==1){
								out.println("<tr class=tr_input1 >");
						}
						else{
								out.println("<tr class=tr_input >");
						}
						
						out.println("  <td width=\"15%\"  align='left'>"+rs4.getString(1)+"</td> ");
						out.println("  <td width=\"15%\"  align='left'>"+rs4.getString(2)+"</td> ");
					    out.println("  <td width=\"15%\"  align='left'>"+m_application_no+"</td> ");
						out.println("  <td width=\"*%\"  align='left' colspan=6 > &nbsp; </td> ");
						out.println(" </tr>");
						

						i4=i4+1;
						line_no4=line_no4+1;
						more4=rs4.next();
				}

				rs4.close();
				
				// end by kasun on 27-11-2024

				out.println("</table >"); 
				
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


