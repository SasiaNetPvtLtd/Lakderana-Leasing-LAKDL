//Created by Minal on 31-12-2014 for #14902
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_MISF_Rod_Document_Report_Detail extends javax.servlet.http.HttpServlet {


	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		Connection conn = null;
		Statement stmt=null,stmt_1=null,stmt_2=null,stmt_3=null,stmt_4=null,stmt_5=null,stmt_6=null,stmt_7=null;
		CallableStatement callstmt= null;
		java.text.NumberFormat nf= null,nf1= null;
		java.lang.Math a= null;
	
		 ResultSet rs=null,rs1=null,rs2=null,rs3=null,rs4=null,rs5=null,rs6=null,rs7=null;
		 String m_chksql=null;
		
		
		
		try {
			
			//************************************************************	
			//LAKDL_AF_RE_PRO_drill_downs obj =new LAKDL_AF_RE_PRO_drill_downs();
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_username = m_sn_methods.username;
			String m_to_date=null,m_system_date=null;
			String m_application_no=null;
			boolean more,more1,more2,more3,more4,more5,more6,more7;
			String m_full_name=null,m_add1=null,m_add2=null,m_city_name=null,m_nic_no=null,m_veh_reg_no=null;
			String m_sub_category=null,m_sub_cat_code=null,m_sub_cat_desc=null;
			String m_client_code=null;
			String m_pay_date=null,m_cheque_no=null,m_settle_mode=null;
			double m_tot_arrears=0,m_future_rent=0,m_tot_balance=0,m_amount=0;
			String m_due_date=null,m_paid_date=null;
			double m_amount_due=0,m_amount_paid=0;
			double m_con_tot_rent_arr=0,m_con_tot_arr=0,m_con_tot_rest=0,m_con_tot_other_due=0;
			double m_gun_tot_rent_arr=0,m_gun_tot_arr=0,m_gun_tot_rest=0,m_gun_tot_other_due=0;
			double m_com_tot_rent_arr=0,m_com_tot_arr=0,m_com_tot_rest=0,m_com_tot_other_due=0;
			//String m_gun_tot_rest=null;
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
			
			
			stmt=conn.createStatement();
			stmt_1=conn.createStatement();
			stmt_2=conn.createStatement();
			stmt_3=conn.createStatement();
			stmt_4=conn.createStatement();
			stmt_5=conn.createStatement();
			stmt_6=conn.createStatement();
			stmt_7=conn.createStatement();
			
			
			
			
		    rs=stmt.executeQuery(" SELECT 	TO_CHAR(SYSDATE,'DD/MM/YYYY'), "+
				                 "           TO_CHAR(SYSDATE,'DD-MM-YYYY') "+
				                 "          FROM DUAL ");
			
			if(rs.next()){
				
				m_to_date=rs.getString(1);
				m_system_date=rs.getString(2);
			}
			
			
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("main_page")){
				
				String m_finance_no=req.getParameter("contract_no").trim();		
			   // out.println("m_finance_no ="+m_finance_no);
				
				out.println("<HTML><HEAD><TITLE>ROD Document Report</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function print_data(){");
				out.println("			document.getElementById('DIV_PRINT').innerHTML=\"\" ");
				out.println(" 			window.print();");
				out.println(" location.reload(); ");
				out.println("}");
				
				out.println("	function show_transaction_info(m_client_code,m_finance_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
				out.println("    window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("	}");
				
				out.println("</script>"); 
				out.println("</head>");	
				
				
                out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B> RELEASE OF DOCUMENT AS AT : "+m_to_date+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR>");
				
			//	boolean more;
				
				String m_branch_desc = "";
				String m_client_name = "";
				String m_contract_desc = "";
				String m_reg_no = "";
				String m_contract_status = "";
				double m_contract_arrears = 0;
				double m_future_rent_outstand = 0;
				double m_total_bal_payment = 0;
				String m_application_status = "";
				
				String Client_Data=" SELECT FINANCE_NO,             "+
								   "			  NVL(BRANCH_DESC,'-')BRANCH_DESC,      "+
								   "			  NVL(CLIENT_NAME,'-')CLIENT_NAME,      "+
								   "			  NVL(CONTRACT_DESC,'-')CONTRACT_DESC,    "+
								   "			  NVL(VEHICLE_REG_NO,'-')VEHICLE_REG_NO,   "+
								   "			  NVL(CONTRACT_STATUS,'-')CONTRACT_STATUS,  "+
								   //" 			  NVL("+m_schema_name+".AF_CO_NEW_CON_BAL_ARR(FINANCE_NO,CLIENT_CODE,'"+m_to_date+"',NULL),0) TOT_ARR_AMOUNT, "+
								   "  			  NVL(AF_CO_GET_FUTURE_RENTALS_AMT(FINANCE_NO),0) FUTURE_RENT_OUT "+
								   "            FROM "+m_schema_name+".AF_ROD_DOC_HEADING_DETAILS "+
								   "            WHERE FINANCE_NO='"+m_finance_no+"' "+
							       "            AND ENT_USER='"+m_username+"'  "+			
								   " ";
				
					
				out.println("<!--Client_Data="+Client_Data+"-->");
				
				rs1 = stmt_1.executeQuery(Client_Data);
				more1 = rs1.next();
				
			    if(!more1){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				else{
					 m_branch_desc = rs1.getString("BRANCH_DESC");
					 m_client_name = rs1.getString("CLIENT_NAME");
					 m_contract_desc = rs1.getString("CONTRACT_DESC");
					 m_reg_no = rs1.getString("VEHICLE_REG_NO");
					 m_contract_status = rs1.getString("CONTRACT_STATUS");
					 //m_contract_arrears = rs1.getDouble("TOT_ARR_AMOUNT");
					 m_future_rent_outstand = rs1.getDouble("FUTURE_RENT_OUT");
				}
				
				
				Client_Data=" "+
					" SELECT "+
						" NVL(RENTAL_ARR,0) RENTAL_ARR, "+
						" NVL(OTHER_DUE,0) OTHER_DUE, "+
						" APPLICATION_STATUS APPLICATION_STATUS, "+
						" NVL(EXCESS,0) EXCESS "+
						" FROM "+m_schema_name+".AF_ROD_DOC_LEASSEE "+
						" WHERE FINANCE_NO = '"+m_finance_no+"' "+
						" AND ENT_USER='"+m_username+"'  "+ // added by udara 11-10-2018
						" ";
				
				rs1 = stmt_1.executeQuery(Client_Data);
				
				if(rs1.next()){
					m_contract_arrears = rs1.getDouble("RENTAL_ARR") + rs1.getDouble("OTHER_DUE") - rs1.getDouble("EXCESS");
					m_application_status = rs1.getString("APPLICATION_STATUS");
				}
				
				rs1.close();
				stmt_1.close();
				
				if(m_application_status.equals("TERMI") || m_application_status.equals("TERMINATED") || m_application_status.equals("NORM_TERMI")){
					m_future_rent_outstand = 0;
				}
				
				
				m_total_bal_payment = m_contract_arrears + m_future_rent_outstand;
				
				out.println("<div id = \"DIV_PRINT\">");
				out.println("<table  class='table'>");
				out.println("	<tr><td>");
				out.println("			<input type = \"button\" class = \"but_input\" onMouseOut = \"\" onMouseOver = \"\" name = \"BUT_PRINT\" value = \"Print\" onclick = \"print_data();\" align=\"right\" />");
				out.println("	</td></tr>");
				out.println("</table>");
				out.println("</div>");
				
				out.println("<table id=mytable  width=\"90%\" border=\"0\" class=\"table\"  cellspacing=0 > ");
				out.println("<tr>");
				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>");
				out.println("<td width='30%' style='cursor:hand;' onclick=\"show_transaction_info('','"+m_finance_no+"');\"  ><B>&nbsp;Contract No : &nbsp; &nbsp;  </B><U>"+m_finance_no+"</U></td>");
				out.println("<td width='30%'  ><B>Branch : &nbsp; &nbsp;</B> "+m_branch_desc+" </td>");
				out.println("<td width='*%'></td>");  
				out.println("</tr >");	
				out.println("</table >");
				out.println("<br >");	
				out.println("<br >");

				
				out.println("<table id=mytable  width=\"90%\" border=\"0\" class=\"table\"  cellspacing=0 > ");
				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>");
				out.println("<td width='25%' ><b>Name of the Client </b></td>");
				out.println("<td width='1%' >:</td>");
				out.println("<td width='30%'  >"+m_client_name+"</td>");
				out.println("<td width='*%'  >&nbsp;</td></tr>");
				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>");
				out.println("<td width='25%' ><b>Description </b></td>");
				out.println("<td width='1%' >:</td>");
				out.println("<td width='30%'  align='left'>"+m_contract_desc+"</td>");
				out.println("<td width='*%'  >&nbsp;</td></tr>");
				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>");
				out.println("<td width='25%' ><b>Vehicle Reg No </b></td>");
				out.println("<td width='1%' >:</td>");
				out.println("<td width='30%'  align='left'>"+m_reg_no+"</td>");
				out.println("<td width='*%'  >&nbsp;</td></tr>");
				
				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>");
				out.println("<td width='25%' ><b>Contract Status </b></td>");
				out.println("<td width='1%' >:</td>");
				out.println("<td width='30%'  align='left'>"+m_contract_status+"</td>");
				out.println("<td width='*%'  >&nbsp;</td></tr>");
				
				out.println("</table>");
				
                 
				
				out.println("<table id=mytable  width=\"90%\" border=\"0\" class=\"table\"  cellspacing=0 > ");

				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>");
				out.println("<td width='25%' ><b>Total Arrears Amount </b></td>");
				out.println("<td width='1%' >:</td>");
				out.println("<td width='10%'  align='right'  >"+nf.format(m_contract_arrears)+"</td>");
				out.println("<td width='*%'  >&nbsp;</td></tr>");
				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>");
				out.println("<td width='25%' ><b>Future Rental Out </b></td>");
				out.println("<td width='1%' >:</td>");
				out.println("<td width='10%'  align='right' >"+nf.format(m_future_rent_outstand)+"</td>");
				out.println("<td width='*%'  >&nbsp;</td></tr>");
				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>");
				out.println("<td width='25%' >&nbsp;&nbsp;  </td>");
				out.println("<td width='1%' ></td>");
				out.println("<td width='*%'  > </td></tr>");
				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>");
				out.println("<td width='25%' ><b>Total Balance Payment</b></td>");
				out.println("<td width='1%' >:</td>");
				out.println("<td width='10%'  align='right' >"+nf.format(m_total_bal_payment)+"</td>");
				out.println("<td width='*%'>&nbsp;</td></tr>");
                out.println("</table>");
					
				/*
				
				out.println("<table id=mytable  width=\"90%\" border=\"0\" class=\"table\"  cellspacing=0 > ");
				out.println("<tr>");
				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>");
				out.println("<td width='30%' style='cursor:hand;' onclick=\"show_transaction_info('','"+rs1.getString("FINANCE_NO")+"');\"  ><B>&nbsp;Contract No : &nbsp; &nbsp;  </B><U>"+rs1.getString("FINANCE_NO")+"</U></td>");
				out.println("<td width='30%'  ><B>Branch : &nbsp; &nbsp;</B> "+rs1.getString("BRANCH_DESC")+" </td>");
				out.println("<td width='*%'></td>");  
				out.println("</tr >");	
				out.println("</table >");
				out.println("<br >");	
				out.println("<br >");

				
				out.println("<table id=mytable  width=\"90%\" border=\"0\" class=\"table\"  cellspacing=0 > ");
				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>");
				out.println("<td width='25%' ><b>Name of the Client </b></td>");
				out.println("<td width='1%' >:</td>");
				out.println("<td width='30%'  >"+rs1.getString("CLIENT_NAME")+"</td>");
				out.println("<td width='*%'  >&nbsp;</td></tr>");
				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>");
				out.println("<td width='25%' ><b>Description </b></td>");
				out.println("<td width='1%' >:</td>");
				out.println("<td width='30%'  align='left'>"+rs1.getString("CONTRACT_DESC")+"</td>");
				out.println("<td width='*%'  >&nbsp;</td></tr>");
				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>");
				out.println("<td width='25%' ><b>Vehicle Reg No </b></td>");
				out.println("<td width='1%' >:</td>");
				out.println("<td width='30%'  align='left'>"+rs1.getString("VEHICLE_REG_NO")+"</td>");
				out.println("<td width='*%'  >&nbsp;</td></tr>");
				
				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>");
				out.println("<td width='25%' ><b>Contract Status </b></td>");
				out.println("<td width='1%' >:</td>");
				out.println("<td width='30%'  align='left'>"+rs1.getString("CONTRACT_STATUS")+"</td>");
				out.println("<td width='*%'  >&nbsp;</td></tr>");
				
				out.println("</table>");
				
                 
				
				out.println("<table id=mytable  width=\"90%\" border=\"0\" class=\"table\"  cellspacing=0 > ");

				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>");
				out.println("<td width='25%' ><b>Total Arrears Amount </b></td>");
				out.println("<td width='1%' >:</td>");
				out.println("<td width='10%'  align='right'  >"+nf.format(rs1.getDouble("TOT_ARR_AMOUNT"))+"</td>");
				out.println("<td width='*%'  >&nbsp;</td></tr>");
				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>");
				out.println("<td width='25%' ><b>Future Rental Out </b></td>");
				out.println("<td width='1%' >:</td>");
				out.println("<td width='10%'  align='right' >"+nf.format(rs1.getDouble("FUTURE_RENT_OUT"))+"</td>");
				out.println("<td width='*%'  >&nbsp;</td></tr>");
				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>");
				out.println("<td width='25%' >&nbsp;&nbsp;  </td>");
				out.println("<td width='1%' ></td>");
				out.println("<td width='*%'  > </td></tr>");
				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>");
				out.println("<td width='25%' ><b>Total Balance Payment</b></td>");
				out.println("<td width='1%' >:</td>");
				out.println("<td width='10%'  align='right' >"+nf.format(rs1.getDouble("TOT_ARR_AMOUNT")+rs1.getDouble("FUTURE_RENT_OUT"))+"</td>");
				out.println("<td width='*%'>&nbsp;</td></tr>");
                out.println("</table>");
				
				*/
				
				
				
				
				out.println("</br>");
				out.println("</br>");
				out.println("<table border='0' width='90%' class='table'>"); 
			    out.println("<tr><td width='10%'  style='padding-right: 40px'></td>"); //&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    out.println("<td width='25%'><b><u>Payment Details</u></b></td>");
				out.println("<td width='1%'></td>");
				out.println("<td width='30%'></td>");
				out.println("<td width='*%'>&nbsp;</td></tr>");
			    out.println("</table>");
				out.println("</br>");
				out.println("<table border='0' width='90%' class='table'>"); 
			    out.println("<tr><td width='10%'  style='padding-right: 40px'></td>"); //&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				out.println("<td width='25%'><b><u>Payment Withing 30 Days </u></b></td>");
				out.println("<td width='1%'></td>");
				out.println("<td width='30%'></td>");
				out.println("<td width='*%'>&nbsp;</td></tr>");
				
			    out.println("</table>");
				out.println("</br>");
				
				out.println("<table border='0' width='90%' class='table'>"); 
			    out.println("<tr><td width='10%'  style='padding-right: 40px'></td>"); //&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    out.println("<td width='20%'><b>Payment Date</b></td>");
				out.println("<td width='20%'><b>Chq No</b></td>");
				out.println("<td width='20%'><b>Cash</b></td>");
				out.println("<td width='20%'><b>Amount</b></td></tr>");
			    
		        
		        String Payment_Data=" SELECT FINANCE_NO,           "+
									"		  TO_CHAR(PAYMENT_DATE,'DD-MM-YYYY')PAYMENT_DATE,        "+
									"		  NVL(CHEQUE_NO,'-')CHEQUE_NO,           "+
									"		  NVL(SETELMENT_TYPE,'-')SETELMENT_TYPE,      "+
									"		  AMOUNT               "+

									"		FROM "+m_schema_name+".AF_ROD_DOC_PAYMENT_DETAILS "+
									"            WHERE FINANCE_NO='"+m_finance_no+"' "+
									"            AND ENT_USER='"+m_username+"'  "+	
									" ";
				
				out.println("<!--Payment_Data="+Payment_Data+"-->");
				
				rs2 = stmt_2.executeQuery(Payment_Data);
				more2 = rs2.next();
				
               while(more2){
					
				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>"); //&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    out.println("<td width='20%'>"+rs2.getString("PAYMENT_DATE")+"</td>");
				out.println("<td width='20%'>"+rs2.getString("CHEQUE_NO")+"</td>");
				out.println("<td width='20%'>"+rs2.getString("SETELMENT_TYPE")+"</td>");
				out.println("<td width='20%'>"+nf.format(rs2.getDouble("AMOUNT"))+"</td></tr>");

				
			    
			    more2=rs2.next();
			    }
				out.println("</table>");
				out.println("</br>");
				
				rs2.close();
				stmt_2.close();
				
				out.println("<table border='0' width='90%' class='table'>"); 
			    out.println("<tr><td width='10%'  style='padding-right: 40px'></td>"); //&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    out.println("<td width='*%'  ><b><u>Insurance Details</u></b></td></tr>");
			    out.println("</table>");
				out.println("</br>");
				
				out.println("<table border='0' width='90%' class='table'>"); 
			    out.println("<tr border='1' ><td width='10%'  style='padding-right: 40px'></td>"); //&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    out.println("<td width='20%'><b>Due Date</b></td>");
				out.println("<td width='20%'><b>Amount Due</b></td>");
				out.println("<td width='20%'><b>Amount Paid</b></td>");
				out.println("<td width='20%'><b>Paid Date</b></td></tr>");
			   
				
				String Insurance_Data =" SELECT FINANCE_NO,    "+
										"        NVL(TO_CHAR(DUE_DATE,'DD-MM-YYYY'),'-')DUE_DATE,      "+
										"        AMOUNT_DUE,    "+
										"        AMOUNT_PAID,   "+
										"        NVL(TO_CHAR(PAID_DATE,'DD-MM-YYYY'),'-')PAID_DATE     "+
										"   FROM "+m_schema_name+".AF_ROD_DOC_INSURANCE_DETAILS "+
										"         WHERE FINANCE_NO='"+m_finance_no+"' "+
										"            AND ENT_USER='"+m_username+"'  "+	
										" ";
					
				out.println("<!--Insurance_Data="+Insurance_Data+"-->");
				
				rs3 = stmt_3.executeQuery(Insurance_Data);
				more3 = rs3.next();
				
		
				
		
				while(more3){
					
				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>"); //&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    out.println("<td width='*%'>"+rs3.getString("DUE_DATE")+"</td>");
				out.println("<td width='*%'>"+nf.format(rs3.getDouble("AMOUNT_DUE"))+"</td>");
				out.println("<td width='*%'>"+nf.format(rs3.getDouble("AMOUNT_PAID"))+"</td>");
				out.println("<td width='*%'>"+rs3.getString("PAID_DATE")+"</td></tr>");

				more3=rs3.next();	
					
				}
				
				rs3.close();
				stmt_3.close();
				
			    out.println("</table>");
				out.println("</br>");
				
				out.println("</br>");
				out.println("<table border='0' width='90%' class='table'>"); 
			    out.println("<tr ><td width='10%'  style='padding-right: 40px'></td>"); //&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

				out.println("<td width='*%'  ><b><u>All Contracts of The Client</u></b></td></tr>");
			    out.println("</table>");
				out.println("</br>");
				out.println("<table border='0' width='90%' class='table'>"); 
			    out.println("<tr ><td width='8%'  style='padding-right: 40px'></td>"); //&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    out.println("<td width='8%'><b>Contract No</b></td>");
				out.println("<td width='8%'><b>Rental Arr</b></td>");
				out.println("<td width='8%'><b>Ren No</b></td>");
				out.println("<td width='7%'><b>%REC</b></td>");
				out.println("<td width='8%'><b>Other Due</b></td>");
				out.println("<td width='8%'><b>Total Arr</b></td>");
				out.println("<td width='8%'><b>Rest</b></td>");
				out.println("<td width='8%'><b>Status</b></td>");
				out.println("<td width='8%'><b>Veh. No.</b></td></tr>");
				
				String Other_Contract_Date = " "+
											
											"  SELECT FINANCE_NO, "+
												 " RENTAL_ARR, "+
												 " NVL(REN_NO,'-')REN_NO, "+
												 " NVL(RENTAL_REC,0)RENTAL_REC, "+
												 " NVL(OTHER_DUE,0)OTHER_DUE, "+
												 " TOTAL_ARR, "+
												 " REST, "+
												 " CONTRACT_STATUS, "+	
												 " VEHICLE_REG_NO, "+
												 //" NVL("+m_schema_name+".AF_CO_NEW_CON_BAL_ARR(FINANCE_NO,CLIENT_CODE,'"+m_to_date+"',NULL),0)  TOT_ARR_AMOUNT, "+	
												 /*
												 " ( CASE WHEN APPLICATION_STATUS IN ('TERMI','TERMINATED','NORM_TERMI') THEN "+
																	 " 0 "+
																	 " ELSE "+
																	    " NVL("+m_schema_name+".AF_CO_NEW_CON_BAL_ARR(FINANCE_NO,CLIENT_CODE,'"+m_to_date+"',NULL),0)	"+ 
														  " END ) TOT_ARR_AMOUNT, "+
															*/
													
												 " NVL(RENTAL_ARR,0) + NVL(OTHER_DUE,0) - NVL(EXCESS,0) TOT_ARR_AMOUNT, "+
													
												 " NVL(OTHER_DUE_ENHANCE,0) OTHER_DUE_ENHANCE "+
	                                             " FROM "+m_schema_name+".AF_ROD_DOC_LEASSEE "+ 
											 "         WHERE  ENT_USER='"+m_username+"'  "+	
											 "            ORDER BY FINANCE_NO ASC "+	
				                             " ";
				out.println("<!--Other_Contract_Date="+Other_Contract_Date+"-->");
				
				rs4 = stmt_4.executeQuery(Other_Contract_Date);
				more4 = rs4.next();
				
				while(more4){
					
				out.println("<tr><td width='8%'  style='padding-right: 40px'></td>"); //&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    out.println("<td width='8%' style='cursor:hand;' onclick=\"show_transaction_info('','"+rs4.getString("FINANCE_NO")+"');\" ><u>"+rs4.getString("FINANCE_NO")+"</u></td>");
				out.println("<td width='8%'>"+nf.format(rs4.getDouble("RENTAL_ARR"))+"</td>");
				out.println("<td width='8%'>"+rs4.getString("REN_NO")+"</td>");
				out.println("<td width='7%'>"+nf.format(rs4.getDouble("RENTAL_REC"))+"</td>");
				out.println("<td width='8%'>"+nf.format(rs4.getDouble("OTHER_DUE"))+"</td>"); 
				//out.println("<td width='8%'>"+nf.format(rs4.getDouble("TOT_ARR_AMOUNT")+rs4.getDouble("OTHER_DUE_ENHANCE"))+"</td>");
				out.println("<td width='8%'>"+nf.format(rs4.getDouble("TOT_ARR_AMOUNT"))+"</td>");
				out.println("<td width='8%'>"+rs4.getString("REST")+"</td>");
				out.println("<td width='8%'>"+rs4.getString("CONTRACT_STATUS")+"</td>");	
				out.println("<td width='8%'>"+rs4.getString("VEHICLE_REG_NO")+"</td></tr>");
				
				m_con_tot_rent_arr=m_con_tot_rent_arr+rs4.getDouble("RENTAL_ARR");
				//m_con_tot_arr=m_con_tot_arr+rs4.getDouble("TOT_ARR_AMOUNT")+rs4.getDouble("OTHER_DUE_ENHANCE");
				m_con_tot_arr=m_con_tot_arr+rs4.getDouble("TOT_ARR_AMOUNT");
				m_con_tot_other_due=m_con_tot_other_due+rs4.getDouble("OTHER_DUE");
					
				more4 = rs4.next();
				}
				
				rs4.close();
				stmt_4.close();
				
				out.println("<tr ><td width='8%'  style='padding-right: 40px'></td>"); 
			    out.println("<td width='8%'><b>Total</b></td>");
				out.println("<td width='8%'><b>"+nf.format(m_con_tot_rent_arr)+"</b></td>");
				out.println("<td width='8%'><b>&nbsp;</b></td>");
				out.println("<td width='7%'><b>&nbsp;</b></td>");
				out.println("<td width='8%'><b>"+nf.format(m_con_tot_other_due)+"</b></td>");
				out.println("<td width='8%'><b>"+nf.format(m_con_tot_arr)+"</b></td>");
				out.println("<td width='8%'><b>&nbsp;</b></td>");
				out.println("<td width='8%'><b>&nbsp;</b></td>");
				out.println("<td width='8%'><b>&nbsp;</b></td></tr>");
				
			    out.println("</table>");
				out.println("</br>");
				
				out.println("<table border='0' width='90%' class='table'>"); 
			    out.println("<tr><td width='10%'  style='padding-right: 40px'></td>"); //&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    out.println("<td width='*%'  ><b><u>Gurantor Details</u></b></td></tr>");
			    out.println("</table>");
				out.println("</br>");
				out.println("<table border='0' width='90%' class='table'>"); 
			    out.println("<tr ><td width='8%'  style='padding-right: 40px'></td>"); //&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    out.println("<td width='8%'><b>Contract No</b></td>");
				out.println("<td width='8%'><b>Rental Arr</b></td>");
				out.println("<td width='8%'><b>Ren No</b></td>");
				out.println("<td width='7%'><b>%REC</b></td>");
				out.println("<td width='8%'><b>Other Due</b></td>");
				out.println("<td width='8%'><b>Total Arr</b></td>");
				out.println("<td width='8%'><b>Rest</b></td>");
				out.println("<td width='8%'><b>Status</b></td>");
				out.println("<td width='8%'><b>Veh. No.</b></td></tr>");
				
				String Gurantor_Details_Data = " "+
					
											  
											   "   SELECT FINANCE_NO,      "+
											   "		  RENTAL_ARR,    "+
											   "		  NVL(REN_NO,'-')REN_NO,        "+
											   "		  NVL(RENTAL_REC,0)RENTAL_REC,    "+
											   "          NVL(OTHER_DUE,0)OTHER_DUE, "+
											   "		  TOTAL_ARR,     "+
											   "		  REST,          "+
					                           "          CONTRACT_STATUS,   "+
											   "          VEHICLE_REG_NO, "+
											   
												//" NVL("+m_schema_name+".AF_CO_NEW_CON_BAL_ARR(FINANCE_NO,CLIENT_CODE,'"+m_to_date+"',NULL),0)  TOT_ARR_AMOUNT, "+	

												/*
												
												" ( CASE WHEN APPLICATION_STATUS IN ('TERMI','TERMINATED','NORM_TERMI') THEN "+
																	 " 0 "+
																	 " ELSE "+
																	    " NVL("+m_schema_name+".AF_CO_NEW_CON_BAL_ARR(FINANCE_NO,CLIENT_CODE,'"+m_to_date+"',NULL),0)	"+ 
														  " END ) TOT_ARR_AMOUNT, "+
															
															*/
												
												" NVL(RENTAL_ARR,0) + NVL(OTHER_DUE,0) - NVL(EXCESS,0) TOT_ARR_AMOUNT, "+

											   " NVL(OTHER_DUE_ENHANCE,0) OTHER_DUE_ENHANCE "+		

											   " FROM "+m_schema_name+".AF_ROD_DOC_GUARANTOR "+
											   "         WHERE  ENT_USER='"+m_username+"'  "+	
											   "            ORDER BY FINANCE_NO ASC "+		
											   " ";
				
				out.println("<!--Other_Contract_Date="+Gurantor_Details_Data+"-->");
				
				rs5 = stmt_5.executeQuery(Gurantor_Details_Data);
				more5 = rs5.next();
				
				while(more5){
					
				out.println("<tr><td width='8%'  style='padding-right: 40px'></td>"); //&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    out.println("<td width='8%' style='cursor:hand;' onclick=\"show_transaction_info('','"+rs5.getString("FINANCE_NO")+"');\" ><u>"+rs5.getString("FINANCE_NO")+"</u></td>");
				out.println("<td width='8%'>"+nf.format(rs5.getDouble("RENTAL_ARR"))+"</td>");
				out.println("<td width='8%'>"+rs5.getString("REN_NO")+"</td>");
				out.println("<td width='7%'>"+nf.format(rs5.getDouble("RENTAL_REC"))+"</td>");
				out.println("<td width='8%'>"+nf.format(rs5.getDouble("OTHER_DUE"))+"</td>");
				//out.println("<td width='8%'>"+nf.format(rs5.getDouble("TOT_ARR_AMOUNT")+rs5.getDouble("OTHER_DUE_ENHANCE"))+"</td>");
				out.println("<td width='8%'>"+nf.format(rs5.getDouble("TOT_ARR_AMOUNT"))+"</td>");
				out.println("<td width='8%'>"+rs5.getString("REST")+"</td>");
				out.println("<td width='8%'>"+rs5.getString("CONTRACT_STATUS")+"</td>");	
				out.println("<td width='8%'>"+rs5.getString("VEHICLE_REG_NO")+"</td></tr>");
				
				m_gun_tot_rent_arr=m_gun_tot_rent_arr+rs5.getDouble("RENTAL_ARR");
				//m_gun_tot_arr=m_gun_tot_arr+rs5.getDouble("TOT_ARR_AMOUNT")+rs5.getDouble("OTHER_DUE_ENHANCE");
				m_gun_tot_arr=m_gun_tot_arr+rs5.getDouble("TOT_ARR_AMOUNT");
				m_gun_tot_other_due=m_gun_tot_other_due+rs5.getDouble("OTHER_DUE");
				
					
				more5 = rs5.next();
				}
				
				rs5.close();
				stmt_5.close();
				
				out.println("<tr ><td width='8%'  style='padding-right: 40px'></td>"); 
			    out.println("<td width='8%'><b>Total</b></td>");
				out.println("<td width='8%'><b>"+nf.format(m_gun_tot_rent_arr)+"</b></td>");
				out.println("<td width='8%'><b>&nbsp;</b></td>");
				out.println("<td width='7%'><b>&nbsp;</b></td>");
				out.println("<td width='8%'><b>"+nf.format(m_gun_tot_other_due)+"</b></td>");
				out.println("<td width='8%'><b>"+nf.format(m_gun_tot_arr)+"</b></td>");
				out.println("<td width='8%'><b>&nbsp;</b></td>");
				out.println("<td width='8%'><b>&nbsp;</b></td>");
				out.println("<td width='8%'><b>&nbsp;</b></td></tr>");
				
			    out.println("</table>");
				out.println("</br>");
				
				out.println("<table border='0' width='90%' class='table'>"); 
			    out.println("<tr><td width='10%'  style='padding-right: 40px'></td>"); //&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    out.println("<td width='*%'><b><u>Combined Contract</u></b></td></tr>");
			    out.println("</table>");
				out.println("</br>");
				out.println("<table border='0' width='90%' class='table'>"); 
			    out.println("<tr><td width='8%'  style='padding-right: 40px'></td>"); //&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    out.println("<td width='8%'><b>Contract No</b></td>");
				out.println("<td width='8%'><b>Rental Arr</b></td>");
				out.println("<td width='8%'><b>Ren No</b></td>");
				out.println("<td width='7%'><b>%REC</b></td>");
				out.println("<td width='8%'><b>Other Due</b></td>");
				out.println("<td width='8%'><b>Total Arr</b></td>");
				out.println("<td width='8%'><b>Rest</b></td>");
				out.println("<td width='8%'><b>Status</b></td>");
				out.println("<td width='8%'><b>Veh. No</b></td></tr>");
				
				String Combine_contract_Data = " "+
					
											   "  SELECT FINANCE_NO,     "+
											   "	  RENTAL_ARR,       "+
											   "	  NVL(REN_NO,'-')REN_NO,           "+
											   "	  NVL(RENTAL_REC,0)RENTAL_REC,       "+
											   "      NVL(OTHER_DUE,0)OTHER_DUE, "+
											   "	  TOTAL_ARR,        "+
											   "	  REST,             "+
											   "	  CONTRACT_STATUS,   "+	
											   "      VEHICLE_REG_NO, "+
											   
											   //" NVL("+m_schema_name+".AF_CO_NEW_CON_BAL_ARR(FINANCE_NO,CLIENT_CODE,'"+m_to_date+"',NULL),0)  TOT_ARR_AMOUNT, "+	
											   
											   /*
												
											   " ( CASE WHEN APPLICATION_STATUS IN ('TERMI','TERMINATED','NORM_TERMI') THEN "+
																	 " 0 "+
																	 " ELSE "+
																	    " NVL("+m_schema_name+".AF_CO_NEW_CON_BAL_ARR(FINANCE_NO,CLIENT_CODE,'"+m_to_date+"',NULL),0)	"+ 
														  " END ) TOT_ARR_AMOUNT, "+
															
															*/
												
												" NVL(RENTAL_ARR,0) + NVL(OTHER_DUE,0) - NVL(EXCESS,0) TOT_ARR_AMOUNT, "+
												
											   " NVL(OTHER_DUE_ENHANCE,0) OTHER_DUE_ENHANCE "+
												
											   " FROM "+m_schema_name+".AF_ROD_DOC_COMB_CONTRACT "+
											   "         WHERE  ENT_USER='"+m_username+"'  "+	
											   "            ORDER BY FINANCE_NO ASC "+		
				                               " ";
				
				out.println("<!--Combine_contract_Data="+Combine_contract_Data+"-->");
				
				rs6 = stmt_6.executeQuery(Combine_contract_Data);
				more6 = rs6.next();
				
				while(more6){
					
				out.println("<tr><td width='8%'  style='padding-right: 40px'></td>"); //&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    out.println("<td width='8%' style='cursor:hand;' onclick=\"show_transaction_info('','"+rs6.getString("FINANCE_NO")+"');\" ><u>"+rs6.getString("FINANCE_NO")+"</u></td>");
				out.println("<td width='8%'>"+nf.format(rs6.getDouble("RENTAL_ARR"))+"</td>");
				out.println("<td width='8%'>"+rs6.getString("REN_NO")+"</td>");
				out.println("<td width='7%'>"+nf.format(rs6.getDouble("RENTAL_REC"))+"</td>");
				out.println("<td width='8%'>"+nf.format(rs6.getDouble("OTHER_DUE"))+"</td>");
				//out.println("<td width='8%'>"+nf.format(rs6.getDouble("TOT_ARR_AMOUNT")+rs6.getDouble("OTHER_DUE_ENHANCE"))+"</td>");
				out.println("<td width='8%'>"+nf.format(rs6.getDouble("TOT_ARR_AMOUNT"))+"</td>");
				out.println("<td width='8%'>"+rs6.getString("REST")+"</td>");
				out.println("<td width='8%'>"+rs6.getString("CONTRACT_STATUS")+"</td>");	
				out.println("<td width='8%'>"+rs6.getString("VEHICLE_REG_NO")+"</td></tr>");
				
				m_com_tot_rent_arr=m_com_tot_rent_arr+rs6.getDouble("RENTAL_ARR");
				//m_com_tot_arr=m_com_tot_arr+rs6.getDouble("TOT_ARR_AMOUNT")+rs6.getDouble("OTHER_DUE_ENHANCE");
				m_com_tot_arr=m_com_tot_arr+rs6.getDouble("TOT_ARR_AMOUNT");
				m_com_tot_other_due=m_com_tot_other_due+rs6.getDouble("OTHER_DUE");
					
				more6 = rs6.next();
				}
				
				rs6.close();
				stmt_6.close();
				
				out.println("<tr ><td width='8%'  style='padding-right: 40px'></td>"); 
			    out.println("<td width='8%'><b>Total</b></td>");
				out.println("<td width='8%'><b>"+nf.format(m_com_tot_rent_arr)+"</b></td>");
				out.println("<td width='8%'><b>&nbsp;</b></td>");
				out.println("<td width='7%'><b>&nbsp;</b></td>");
				out.println("<td width='8%'><b>"+nf.format(m_com_tot_other_due)+"</b></td>");
				out.println("<td width='8%'><b>"+nf.format(m_com_tot_arr)+"</b></td>");
				out.println("<td width='8%'><b>&nbsp;</b></td>");
				out.println("<td width='8%'><b>&nbsp;</b></td>");
				out.println("<td width='8%'><b>&nbsp;</b></td></tr>");
				
			    out.println("</table>");
				out.println("</br>");
				
				
				out.println("<table border='0' width='90%' class='table'>"); 
			    out.println("<tr><td width='10%'  style='padding-right: 40px'></td>"); //&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    out.println("<td width='*%'><b><u>Additional Security</u></b></td></tr>"); 
			    out.println("</table>");
				out.println("</br>");
				out.println("<table border='0' width='90%' class='table'>"); 
			    out.println("<tr><td width='10%'  style='padding-right: 40px'></td>"); //&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; // 16-12-2019 comment
				out.println("<td width='20%'><b>Facility no</b></td>");
				out.println("<td width='20%'><b>Details</b></td>");
				out.println("<td width='20%'><b>Additional Security Details</b></td>");
				out.println("<td width='20%'><b>Remarks</b></td>"); // added by udara 10-12-2019
				//out.println("<td width='35%'>&nbsp;</td></tr>"); // 16-12-2019 comment
				out.println("<td width='10%'>&nbsp;</td></tr>"); // 16-12-2019 added

				String Security_Data = " SELECT FINANCE_NO,     "+
									   "	  FACILITY_NO,      "+ 
									   "	  NVL(DETAILS,'-')DETAILS,          "+
									   "	  NVL(SECURITY_DETAILS,'-')SECURITY_DETAILS, "+
										"     NVL(REMARKS,'-') REMARKS "+ // added by udara 10-12-2019
									   " FROM "+m_schema_name+".AF_ROD_DOC_CONTRACT_SECURITY "+
									   "         WHERE FINANCE_NO='"+m_finance_no+"' "+	
									   "            AND ENT_USER='"+m_username+"'  "+		
									   "";	
				
				
				out.println("<!--Security_Data="+Security_Data+"-->");
				
				rs7 = stmt_7.executeQuery(Security_Data);
				
				more7 = rs7.next();
				
				while(more7){
					
				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>"); //&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; // 16-12-2019 comment
			    //out.println("<td width='15%'>"+rs7.getString("FACILITY_NO")+"</td>");
				
				//out.println("<td width='15%'>"+rs7.getString("DETAILS")+"</td>");
				
				if(rs7.getString("SECURITY_DETAILS").equals("Running contract")){
					out.println("<td width='20%' style='cursor:hand;' onclick=\"show_transaction_info('','"+rs7.getString("FACILITY_NO")+"');\" ><u>"+rs7.getString("FACILITY_NO")+"</u></td>");
				}
				else if(rs7.getString("SECURITY_DETAILS").equals("Security Vehicle")){
					out.println("<td width='20%'>"+rs7.getString("FACILITY_NO")+"</td>");
				}
				else{
					out.println("<td width='20%'>"+rs7.getString("DETAILS")+"</td>");
				}
				
				if(rs7.getString("SECURITY_DETAILS").equals("Running contract")){
					out.println("<td width='20%' style='cursor:hand;' onclick=\"show_transaction_info('','"+rs7.getString("DETAILS")+"');\" ><u>"+rs7.getString("DETAILS")+"</u></td>");
				}
				else{					
					out.println("<td width='20%'>"+rs7.getString("DETAILS")+"</td>");
				}
				
				out.println("<td width='20%'>"+rs7.getString("SECURITY_DETAILS")+"</td>");	
				
				out.println("<td width='20%'>"+rs7.getString("REMARKS")+"</td>"); // added by udara 10-12-2019
				
				//out.println("<td width='35%'>&nbsp;</td></tr>");
				out.println("<td width='10%'>&nbsp;</td></tr>");
					
				more7 = rs7.next();
				}
				
			    out.println("</table>");
				out.println("</br>");
                out.println("</br>");
				out.println("</br>");
				out.println("</br>");
				out.println("</br>");
				out.println("</br>");
				
				
				out.println("<table border='0' width='90%' class='table'>"); 
				out.println("<tr><td width='10%'  style='padding-right: 70px'></td>"); 
			    out.println("<td width='*%'><b>----------</b></td>");
				out.println("<td width='*%'><b>----------</b></td>");
				out.println("<td width='*%'><b>----------</b></td></tr>");
			    out.println("<tr><td width='10%'  style='padding-right: 70px'></td>"); 
			    out.println("<td width='*%'><b>Done By</b></td>");
				out.println("<td width='*%'><b>Checked By</b></td>");
				out.println("<td width='*%'><b>Approved By</b></td></tr>");

				out.println("</table>");
				out.println("</br>");
				out.println("</br>");
				out.println("</br>");

				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			    out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			    out.println("</body>");
				out.println("</html>");
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

