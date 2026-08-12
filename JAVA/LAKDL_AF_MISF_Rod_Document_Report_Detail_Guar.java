//Created by Minal on 31-12-2014 for #14902
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_MISF_Rod_Document_Report_Detail_Guar extends javax.servlet.http.HttpServlet {


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
				
				String m_finance_no=req.getParameter("client_no").trim();		
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
								   "            WHERE CLIENT_CODE='"+m_finance_no+"' "+
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
				
				
				
				
				out.println("<div id = \"DIV_PRINT\">");
				out.println("<table  class='table'>");
				out.println("	<tr><td>");
				out.println("			<input type = \"button\" class = \"but_input\" onMouseOut = \"\" onMouseOver = \"\" name = \"BUT_PRINT\" value = \"Print\" onclick = \"print_data();\" align=\"right\" />");
				out.println("	</td></tr>");
				out.println("</table>");
				out.println("</div>");
				
				
				out.println("<table id=mytable  width=\"90%\" border=\"0\" class=\"table\"  cellspacing=0 > ");
				
				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>");
				out.println("<td width='25%' ><b>Client Code</b></td>");
				out.println("<td width='1%' >:</td>");
				out.println("<td width='30%'  >"+m_finance_no+"</td>");
				out.println("<td width='*%'  >&nbsp;</td></tr>");
				
				out.println("<tr><td width='10%'  style='padding-right: 40px'></td>");
				out.println("<td width='25%' ><b>Name of the Client </b></td>");
				out.println("<td width='1%' >:</td>");
				out.println("<td width='30%'  >"+m_client_name+"</td>");
				out.println("<td width='*%'  >&nbsp;</td></tr>");
				
				
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

