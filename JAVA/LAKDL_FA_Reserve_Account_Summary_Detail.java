import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
        
// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:09-01-2007

public class LAKDL_FA_Reserve_Account_Summary_Detail extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1,stmt2,stmt3;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
  public ResultSet rs1,rs2,rs3;
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String header_name=m_sn_methods.header_name.trim(); 
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
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			stmt3=conn.createStatement();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			
			
				else if(m_chksql.equals("LOAD_RESERVE_ACCOUNT_MAIN")){ //added by ns on 08-02-2010

				String m_client_code=req.getParameter("client_code");
				String m_facility_no=req.getParameter("facility_no");
				String m_date=req.getParameter("date");
				
				String m_current_date="";
				
				rs2= stmt2.executeQuery("SELECT TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL ");
						
				if(rs2.next()){
				m_current_date=rs2.getString(1);
				}
				
								
				double m_temp1=0;
				double m_temp2=0;
				
				double m_temp1_1=0;
				double m_temp2_1=0;
				
				double m_temp1_2=0;
				double m_temp2_2=0;
				double m_temp2_2_new=0;
				double m_temp1_2_new=0;
				
				out.println("<HTML><HEAD><TITLE>RESERVE ACCOUNT SUMMARY AS AT "+m_date+"</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("function drill_down_sub(m_client,m_facility) {");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_Reserve_Account_Summary_Detail?chksql=LOAD_RESERVE_ACCOUNT_MAIN_DETAIL&to_date="+m_date+"&CLIENT_CODE=\"+m_client+\"&FACILITY_NO=\"+m_facility;");	
				out.println("		window.open(m_url,'popupwin2','status=0,menubar=0,scrollbars=1,height=500,width=700');");
				out.println("}");
				
				//Added by Udara Somathilake on 30-03-2010
				out.println("function load_client_payment(m_client_code,m_facility_no){");
				//out.println("     alert('"+m_client_code+"'+'   '+'"+m_facility_no+"');");
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr?chksql=LOAD_CLIENT_AVAILABILITY&CLIENT_CODE=\"+'"+m_client_code+"'+\"&FACILITY_NO=\"+'"+m_facility_no+"';");	
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_Client_availability?chksql=CHK_CLIENT_AVB&CLIENT_CODE=\"+m_client_code+\"&FACILITY_NO=\"+m_facility_no;");					
				out.println("		popupwin3 = window.open(m_url,\"oBj\",\"width=1024,height=768,scrollbars=2\");");
				out.println("}");
				//End by Udara Somathilake on 30-03-2010
				
				out.println("</script>"); 
				
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<br>");
				out.println("<p class=pdn_txtpos2><center><u><b>RESERVE ACCOUNT SUMMARY AS AT "+m_current_date+"</b></u></center></p>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
				out.println("<td width='10%' align=center><DIV class=div_input><b>Facility No</b></DIV></td>");
				out.println("<td width='8%' align=center><DIV class=div_input><b>Credit Limit</b></DIV></td>");
				out.println("<td width='7%' align=center><DIV class=div_input><b>Sales Ledger Balance</b></DIV></td>");
				out.println("<td width='10%' align=center><DIV class=div_input><b>Current Account Balance</b></DIV></td>");
				out.println("<td width='10%' align=center><DIV class=div_input><b>Interest Rate</b></DIV></td>");
				out.println("<td width='10%' align=center><DIV class=div_input><b>Admin Charge</b></DIV></td>");
				out.println("<td width='10%' align=center><DIV class=div_input><b>Yield</b></DIV></td>");
				out.println("<td width='10%' align=center><DIV class=div_input><b>Credit period</b></DIV></td>");
				out.println("</tr>");
				
				if(m_facility_no.equals("")){
					rs3= stmt3.executeQuery(
					//out.println(
					"SELECT A.FACILITY_NO, "+ //1
						" A.CLIENT_CODE, "+ //2
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+ //3
						" A.CREDIT_LIMIT,"+ //4
						" A.RESERVE_MARGIN,"+ //5
						//" "+m_schema_name+".FA_SALES_LEDGER_BAL_AMT(A.CLIENT_CODE,A.FACILITY_NO,'"+m_current_date+"','"+m_current_date+"','ACT') ,"+ //added by ns 05-02-2010  //6
												" "+m_schema_name+".FA_SALES_LEDGER_BAL_TOT(A.CLIENT_CODE,A.FACILITY_NO,'"+m_current_date+"',NULL) ,"+ //added by ns 05-02-2010

						" "+m_schema_name+".AF_CO_GET_EXPECTED_YIELD(A.FACILITY_NO) ,"+//7
						" INT_RATE, "+ //8
						" "+m_schema_name+".FA_GET_CUR_ACC_BAL_AMT_SUM(A.CLIENT_CODE,A.FACILITY_NO,'"+m_current_date+"'), "+ //9
						" "+m_schema_name+".FA_GET_ADMIN_CHARGE(A.CLIENT_CODE,A.FACILITY_NO), "+ //10
						" A.CREDIT_PERIOD  "+ //11
	  				" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY A "+
						" WHERE A.FACILITY_STATUS='Y' ");
				}
				else{
						rs3= stmt3.executeQuery("SELECT A.FACILITY_NO, "+
						" A.CLIENT_CODE, "+
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
						" A.CREDIT_LIMIT,"+
						" A.RESERVE_MARGIN ,"+
						//" "+m_schema_name+".FA_SALES_LEDGER_BAL_AMT(A.CLIENT_CODE,A.FACILITY_NO,'"+m_current_date+"','"+m_current_date+"','ACT') ,"+ //added by ns 05-02-2010
						" "+m_schema_name+".FA_SALES_LEDGER_BAL_TOT(A.CLIENT_CODE,A.FACILITY_NO,'"+m_current_date+"',NULL) ,"+ //added by ns 05-02-2010
						" "+m_schema_name+".AF_CO_GET_EXPECTED_YIELD(A.FACILITY_NO) ,"+
						" INT_RATE, "+
						" "+m_schema_name+".FA_GET_CUR_ACC_BAL_AMT_SUM(A.CLIENT_CODE,A.FACILITY_NO,'"+m_current_date+"'), "+
						" "+m_schema_name+".FA_GET_ADMIN_CHARGE(A.CLIENT_CODE,A.FACILITY_NO), "+
						" A.CREDIT_PERIOD  "+ //11
						" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY A "+
						" WHERE A.FACILITY_NO='"+m_facility_no+"' "+
	       		" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND A.FACILITY_STATUS='Y' ");
				}
				
				
				
				int j=1;
				double m_sum_sales_bal=0;
				double m_cur_ac_bal=0;
				
				while(rs3.next()){
				
							if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
						}
						else{
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=0;
						}
						
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs3.getString(2)+"')\"><u>"+rs3.getString(3)+"</u></td>");
						//out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_facility('"+rs3.getString(1)+"')\"><u>"+rs3.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"load_client_payment('"+rs3.getString(2)+"','"+rs3.getString(1)+"')\"><u>"+rs3.getString(1)+"</u></td>"); // us
						out.println("<td width='8%' class=div_input align=right style='cursor:hand' onClick=\"drill_down_sub('"+rs3.getString(2)+"','"+rs3.getString(1)+"')\">"+nf.format(rs3.getDouble(4))+"</td>");
						out.println("<td width='7%' class=div_input align=right style='cursor:hand' onClick=\"drill_down_sub('"+rs3.getString(2)+"','"+rs3.getString(1)+"')\">"+nf.format(rs3.getDouble(6))+"</td>");
						out.println("<td width='10%' class=div_input align=right  >"+nf.format(rs3.getDouble(9))+"</td>");
						out.println("<td width='10%' class=div_input align=center >"+rs3.getDouble(8)+"</td>");
						out.println("<td width='10%' class=div_input  align=right >"+nf.format(rs3.getDouble(10))+"</td>");
						out.println("<td width='10%' class=div_input align=center >"+nf.format(rs3.getDouble(7))+"</td>");
						out.println("<td width='10%' class=div_input align=center >"+nf.format(rs3.getDouble(11))+"</td>");
				    
						m_sum_sales_bal+=rs3.getDouble(6);
				    m_cur_ac_bal+=rs3.getDouble(9);
				}
				
				out.println("<tr bgcolor=\"#999966\">");
				out.println("<td width='15%' ></td>");
				out.println("<td width='10%' align=right></td>");
				out.println("<td width='8%' align=right>Total</td>");
				out.println("<td width='7%' align=right><DIV class=div_input><b>"+nf.format(m_sum_sales_bal)+"</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>"+nf.format(m_cur_ac_bal)+"</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input>&nbsp;</td>");
				out.println("<td width='10%' align=right></td>");
				out.println("<td width='10%' align=right></td>");
				out.println("</tr>");
				
				
				
				out.println("</table>");
				out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</HTML>");
				
	    }
			else if(m_chksql.equals("LOAD_RESERVE_ACCOUNT_MAIN_OLD")){ //comment by ns on 08-02-2010

				String m_client_code=req.getParameter("client_code");
				String m_facility_no=req.getParameter("facility_no");
				String m_date=req.getParameter("date");
				
				String m_current_date="";
				
				rs2= stmt2.executeQuery("SELECT TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL ");
						
				if(rs2.next()){
				m_current_date=rs2.getString(1);
				}
				
								
				double m_temp1=0;
				double m_temp2=0;
				
				double m_temp1_1=0;
				double m_temp2_1=0;
				
				double m_temp1_2=0;
				double m_temp2_2=0;
				double m_temp2_2_new=0;
				double m_temp1_2_new=0;
				
				out.println("<HTML><HEAD><TITLE>RESERVE ACCOUNT SUMMARY AS AT "+m_date+"</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("function drill_down_sub(m_client,m_facility) {");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_Reserve_Account_Summary_Detail?chksql=LOAD_RESERVE_ACCOUNT_MAIN_DETAIL&CLIENT_CODE=\"+m_client+\"&FACILITY_NO=\"+m_facility;");	
				out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
				out.println("}");
				/*out.println("function drill_down_sub(m_client,m_facility,m_debtor) {");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr_drill?chksql=DRILL_DOOWN_SUB_1&CLIENT_CODE=\"+m_client+\"&FACILITY_NO=\"+m_facility+\"&DEBTOR_CODE=\"+m_debtor;");	
				out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
				out.println("}");*/
				out.println("</script>"); 
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<br>");
				out.println("<p class=pdn_txtpos2><center><u><b>RESERVE ACCOUNT SUMMARY AS AT "+m_current_date+"</b></u></center></p>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
				out.println("<td width='10%' align=center><DIV class=div_input><b>Facility No</b></DIV></td>");
				out.println("<td width='8%' align=center><DIV class=div_input><b>Credit Limit</b></DIV></td>");
				out.println("<td width='7%' align=center><DIV class=div_input><b>Sales Ledger Balance</b></DIV></td>");
				out.println("<td width='10%' align=center><DIV class=div_input><b>Current Account Balance</b></DIV></td>");
				//out.println("<td width='10%' align=center><DIV class=div_input><b>Interest Rate</b></DIV></td>");
				out.println("<td width='10%' align=center><DIV class=div_input><b>Interest Rate</b></DIV></td>");
				out.println("<td width='10%' align=center><DIV class=div_input><b>Admin Charge</b></DIV></td>");
				out.println("<td width='10%' align=center><DIV class=div_input><b>Yield</b></DIV></td>");
				out.println("<td width='10%' align=center><DIV class=div_input><b>Credit period</b></DIV></td>");
				out.println("</tr>");
				
				if(m_facility_no.equals("")){
					rs3= stmt3.executeQuery("SELECT A.FACILITY_NO, "+
						" A.CLIENT_CODE, "+
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
						" A.CREDIT_LIMIT,"+
						//" A.RESERVE_MARGIN,
						//" "+m_schema_name+".FA_SALES_LEDGER_BAL_AMT_2(A.CLIENT_CODE,A.FACILITY_NO,'"+m_current_date+"') ,"+ //comment by ns 05-02-2010
						" "+m_schema_name+".FA_SALES_LEDGER_BAL_TOT(A.CLIENT_CODE,A.FACILITY_NO,'"+m_current_date+"') ,"+ //added by ns 05-02-2010
						" "+m_schema_name+".FA_FACTORING_MONTH_ACT_YEILD(A.FACILITY_NO,'"+m_current_date+"'), "+
						" INT_RATE, "+
						" "+m_schema_name+".FA_GET_CUR_ACC_BAL_AMT_SUM(A.CLIENT_CODE,A.FACILITY_NO,'"+m_current_date+"'), "+
						" "+m_schema_name+".FA_GET_ADMIN_CHARGE(A.CLIENT_CODE,A.FACILITY_NO), "+
						" A.CREDIT_PERIOD "+
	  				" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY A "+
						" WHERE A.FACILITY_STATUS='Y' ");
				}
				else{
					rs3= stmt3.executeQuery("SELECT A.FACILITY_NO, "+
						" A.CLIENT_CODE, "+
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
						" A.CREDIT_LIMIT,"+
						//" A.RESERVE_MARGIN,
						//" "+m_schema_name+".FA_SALES_LEDGER_BAL_AMT_2(A.CLIENT_CODE,A.FACILITY_NO,'"+m_current_date+"') ,"+
						" "+m_schema_name+".FA_SALES_LEDGER_BAL_TOT(A.CLIENT_CODE,A.FACILITY_NO,'"+m_current_date+"') ,"+ //added by ns 05-02-2010
						" "+m_schema_name+".FA_FACTORING_MONTH_ACT_YEILD(A.FACILITY_NO,'"+m_current_date+"'),"+
						" INT_RATE, "+
						" "+m_schema_name+".FA_GET_CUR_ACC_BAL_AMT_SUM(A.CLIENT_CODE,A.FACILITY_NO,'"+m_current_date+"'), "+
						" "+m_schema_name+".FA_GET_ADMIN_CHARGE(A.CLIENT_CODE,A.FACILITY_NO), "+
						" A.CREDIT_PERIOD "+
						" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY A "+
						" WHERE A.FACILITY_NO='"+m_facility_no+"' "+
	       		" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND A.FACILITY_STATUS='Y' ");
				}
				
				int j=1;
				while(rs3.next()){
								
						rs2= stmt2.executeQuery("SELECT FACILITY_NO,"+//1
								" CLIENT_CODE,"+//2
								" DEBTOR_CODE,"+//3
								" CREDIT_LIMIT,"+//4
								" RESERVE_MARGIN, "+//5
								" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE) "+//6
		         		" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
		     			  " WHERE FACILITY_NO='"+rs3.getString(1)+"' "+
		         		" AND CLIENT_CODE='"+rs3.getString(2)+"' "+
								" ORDER BY CLIENT_CODE,DEBTOR_CODE ");
								
						m_temp1_1=0;
						m_temp2_1=0;
						
						while(rs2.next()){
							m_temp1=0;
							m_temp2=0;
							rs1= stmt1.executeQuery("SELECT NVL(SUM(B.BALANCE_AMOUNT),0) "+//1
								" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
								" WHERE "+
								" A.BATCH_NO=B.BATCH_NO "+
								" AND B.DEBTOR_CODE='"+rs2.getString(3)+"' "+
								" AND A.FACILITY_NO='"+rs3.getString(1)+"' "+
								" AND A.CLIENT_CODE='"+rs3.getString(2)+"' "+
								" AND B.BALANCE_AMOUNT>0 "+
								" AND B.INVOICE_STATUS='CONF' ");
								
								
								
								
							
							while(rs1.next()){
								if(rs2.getDouble(4)<rs1.getDouble(1)){
								m_temp1=m_temp1+(rs2.getDouble(4)*(100-rs2.getDouble(5))/100);
								m_temp2=m_temp2+rs1.getDouble(1);
								}
								else{
								m_temp1=m_temp1+(rs1.getDouble(1)*(100-rs2.getDouble(5))/100);
								m_temp2=m_temp2+rs1.getDouble(1);
								}
							}
							m_temp1_1=m_temp1_1+m_temp1;
							m_temp2_1=m_temp2_1+m_temp2;
						}
						
						m_temp1_2=m_temp1_2+m_temp1_1;//m_temp1_2_new
						m_temp2_2=m_temp2_2+m_temp2_1;
						m_temp2_2_new=m_temp2_2_new+rs3.getDouble(8);
						m_temp1_2_new=m_temp1_2_new+rs3.getDouble(9);//m_temp1_2_new
						
						if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
						}
						else{
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=0;
						}
						
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs3.getString(2)+"')\"><u>"+rs3.getString(3)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_facility('"+rs3.getString(1)+"')\"><u>"+rs3.getString(1)+"</u></td>");
						out.println("<td width='8%' class=div_input align=right style='cursor:hand' onClick=\"drill_down_sub('"+rs3.getString(2)+"','"+rs3.getString(1)+"')\">"+nf.format(rs3.getDouble(4))+"</td>");
						out.println("<td width='7%' class=div_input align=right style='cursor:hand' onClick=\"drill_down_sub('"+rs3.getString(2)+"','"+rs3.getString(1)+"')\">"+nf.format(rs3.getDouble(5))+"</td>");
						out.println("<td width='10%' class=div_input align=right  >"+nf.format(rs3.getDouble(8))+"</td>");
						//out.println("<td width='10%' class=div_input align=right style='cursor:hand' onClick=\"drill_down_sub('"+rs3.getString(2)+"','"+rs3.getString(1)+"')\">"+nf.format(m_temp1_1)+"</td>");
						out.println("<td width='10%' class=div_input align=center >"+rs3.getDouble(7)+"</td>");
						out.println("<td width='10%' class=div_input  align=right >"+nf.format(rs3.getDouble(9))+"</td>");
						out.println("<td width='10%' class=div_input align=center >"+rs3.getString(6)+"</td>");
						out.println("<td width='10%' class=div_input align=center >"+rs3.getString(10)+"</td>");
				}
				
				out.println("<tr bgcolor=\"#999966\">");
				out.println("<td width='15%' ></td>");
				out.println("<td width='10%' align=right></td>");
				out.println("<td width='8%' align=right></td>");
				out.println("<td width='7%' align=right><b>Total</b></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>"+nf.format(m_temp2_2_new)+"</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input></DIV></td>");//<b>"+nf.format(m_temp1_2_new)+"</b>
				out.println("<td width='10%' align=right><DIV class=div_input><b>"+nf.format(m_temp1_2_new)+"</b></DIV></td>");
				out.println("<td width='10%' align=right></td>");
				out.println("<td width='10%' align=right></td>");
				//out.println("<td width='10%' align=right></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</HTML>");
				
	    }
			else if(m_chksql.equals("LOAD_RESERVE_ACCOUNT_MAIN_DETAIL")){

				String m_client_code = req.getParameter("CLIENT_CODE");
				String m_facility_no = req.getParameter("FACILITY_NO");
				String m_date        = req.getParameter("to_date");
				
				String m_current_date="";
				
				rs2= stmt2.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ");
						
				if(rs2.next()){
				m_current_date=rs2.getString(1);
				}
								
				double m_temp1=0;
				double m_temp2=0;
				
				double m_temp1_1=0;
				double m_temp2_1=0;
				
				double m_temp1_2=0;
				double m_temp2_2=0;
				
				out.println("<HTML><HEAD><TITLE>RESERVE ACCOUNT SUMMARY AS AT "+m_current_date+"</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("function drill_down_sub(m_client,m_facility,m_debtor) {");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_Reserve_Account_Summary_Detail?chksql=DRILL_DOOWN_SUB_1&to_date="+m_date+"&CLIENT_CODE=\"+m_client+\"&FACILITY_NO=\"+m_facility+\"&DEBTOR_CODE=\"+m_debtor;");	
				out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
				out.println("}");
				out.println("</script>"); 
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<br>");
				out.println("<p class=pdn_txtpos2><center><u><b>RESERVE ACCOUNT SUMMARY AS AT "+m_current_date+"</b></u></center></p>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Credit Limit</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Reserve Margin(%)</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Payable Amount</b></DIV></td>");
				out.println("</tr>");
				
				int j=1;
				
					rs2= stmt2.executeQuery("SELECT FACILITY_NO,"+//1
							" CLIENT_CODE,"+//2
							" DEBTOR_CODE,"+//3
							" CREDIT_LIMIT,"+//4
							" RESERVE_MARGIN, "+//5
							" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE), "+//6
							" "+m_schema_name+".FA_SALES_LEDGER_BAL_TOT(CLIENT_CODE,FACILITY_NO,'"+m_date+"',DEBTOR_CODE) SALES_LEDGER_BAL "+ 
	         		" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
	     			  " WHERE FACILITY_NO='"+m_facility_no+"' "+
	         		" AND CLIENT_CODE='"+m_client_code+"' "+
							" ORDER BY CLIENT_CODE,DEBTOR_CODE ");
							
					m_temp1_1=0;
					m_temp2_1=0;
						
					while(rs2.next()){
						m_temp1=0;
						m_temp2=0;
					
				/*	rs1= stmt1.executeQuery("SELECT NVL(SUM(B.NET_INVOICE_AMOUNT-"+m_schema_name+".FA_GET_SETTLE_AMOUNTS(B.INVOICE_SEQ_NO,'"+m_date+"')),0)  "+//1 NVL(SUM(B.BALANCE_AMOUNT),0)
							" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
							" WHERE "+
							" A.BATCH_NO=B.BATCH_NO "+
							" AND B.DEBTOR_CODE='"+rs2.getString(3)+"' "+
							" AND A.FACILITY_NO='"+m_facility_no+"' "+
							" AND A.CLIENT_CODE='"+m_client_code+"' "+
							//" AND B.BALANCE_AMOUNT>0 "+
							" AND B.INVOICE_STATUS='CONF' "+
							" AND  TO_DATE(TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							"");
					*/		
							
						
						//while(rs1.next()){
							if(rs2.getDouble("CREDIT_LIMIT")<rs2.getDouble("SALES_LEDGER_BAL")){
							m_temp1=m_temp1+(rs2.getDouble("CREDIT_LIMIT")*(100-rs2.getDouble("RESERVE_MARGIN"))/100);
							m_temp2=m_temp2+rs2.getDouble("SALES_LEDGER_BAL");
							}
							else{
							m_temp1=m_temp1+(rs2.getDouble("SALES_LEDGER_BAL")*(100-rs2.getDouble("RESERVE_MARGIN"))/100);
							m_temp2=m_temp2+rs2.getDouble("SALES_LEDGER_BAL");
							}
					//	}
						if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
						}
						else{
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=0;
						}
						
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs2.getString(3)+"')\"><u>"+rs2.getString(6)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right style='cursor:hand' onClick=\"drill_down_sub('"+m_client_code+"','"+m_facility_no+"','"+rs2.getString(3)+"')\">"+nf.format(rs2.getDouble(4))+"</td>");
						out.println("<td width='10%' class=div_input align=right style='cursor:hand' onClick=\"drill_down_sub('"+m_client_code+"','"+m_facility_no+"','"+rs2.getString(3)+"')\">"+nf.format(rs2.getDouble(5))+"</td>");
						out.println("<td width='10%' class=div_input align=right style='cursor:hand' onClick=\"drill_down_sub('"+m_client_code+"','"+m_facility_no+"','"+rs2.getString(3)+"')\">"+nf.format(m_temp2)+"</td>");
						out.println("<td width='10%' class=div_input align=right style='cursor:hand' onClick=\"drill_down_sub('"+m_client_code+"','"+m_facility_no+"','"+rs2.getString(3)+"')\">"+nf.format(m_temp1)+"</td>");

						m_temp1_1=m_temp1_1+m_temp1;
						m_temp2_1=m_temp2_1+m_temp2;
					}						
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='15%' ></td>");
					out.println("<td width='10%' align=right></td>");
					out.println("<td width='10%' align=right><b>Total</b></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>"+nf.format(m_temp2_1)+"</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>"+nf.format(m_temp1_1)+"</b></DIV></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				
	    }
			else if(m_chksql.equals("DRILL_DOOWN_SUB_1")){
				
				String m_client_code    = req.getParameter("CLIENT_CODE");
				String m_facility_code  = req.getParameter("FACILITY_NO");
				String m_debtor_code    = req.getParameter("DEBTOR_CODE");
				String m_to_date        = req.getParameter("to_date");
				
				rs1= stmt1.executeQuery("SELECT B.BATCH_NO, "+//1
					" B.DEBTOR_CODE, "+//2
					" B.INVOICE_NO, "+//3
					" TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//4
					" B.INVOICE_AMOUNT, "+//5
					//" B.SETTLE_AMOUNT, "+//6
					" NVL("+m_schema_name+".FA_GET_SETTLE_AMOUNTS(B.INVOICE_SEQ_NO,'"+m_to_date+"'),0) SETT_AMOUNT ,"+
					" "+m_schema_name+".FA_GET_ADJUST_AMOUNTS(A.FACILITY_NO,A.BATCH_NO,B.INVOICE_NO,'"+m_to_date+"') ADJ_AMOUNT "+
					//" NVL(B.BALANCE_AMOUNT,0) "+//7
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
					" WHERE "+
					" A.BATCH_NO=B.BATCH_NO "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					" AND B.DEBTOR_CODE='"+m_debtor_code+"' "+
					//" AND B.BALANCE_AMOUNT>0 "+
					" AND B.INVOICE_STATUS='CONF' "+
					" AND    TO_DATE(TO_CHAR(B.APP_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" ORDER BY B.TOLARENCE_END_DATE ");
							
				out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<p class=pdn_txtpos2><center><u><b>INVOICE DETAIL-"+m_debtor_code+"</b></u></center></p>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Tol. End Date</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Net Invoice Amount</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Settle Amount</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Adj Amount</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
				out.println("</tr>");
				
				int j=1;
				
				double m_val_1=0;
				double m_val_2=0;
				double m_val_3=0;
				double m_val_4=0;
				double m_val_bal=0;
				
				while(rs1.next()){
				m_val_4=0;
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=0;
					}
					m_val_1 = m_val_1+rs1.getDouble(5);
					m_val_2 = m_val_2+rs1.getDouble(6);
					m_val_3 = m_val_3+rs1.getDouble(7);
					m_val_4 = (rs1.getDouble(5)-rs1.getDouble(6)) +rs1.getDouble(7);
					m_val_bal = m_val_bal+m_val_4;
					
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(2)+"','"+rs1.getString(3)+"')\"><u>"+rs1.getString(3)+"</u></td>");
					out.println("<td width='10%' class=div_input style='cursor:hand' >"+rs1.getString(4)+"</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble("SETT_AMOUNT"))+"</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble("ADJ_AMOUNT"))+"</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(m_val_4)+"</td>");
					out.println("</tr>");
				}
				out.println("<tr bgcolor=\"#999966\">");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ><b>Total</b></td>");
				out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val_1)+"</b></td>");
				out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val_2)+"</b></td>");
				out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val_3)+"</b></td>");
				out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val_bal)+"</b></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</HTML>");
				
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



