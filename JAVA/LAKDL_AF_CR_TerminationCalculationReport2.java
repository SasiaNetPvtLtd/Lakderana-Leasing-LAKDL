/*
// header - edit "Data/yourJavaHeader" to customize
// contents - edit "EventHandlers/Java file/onCreate" to customize
//
*/

//Created by Dineth Meemanage on 2009-01-20
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;

import oracle.jdbc.driver.*;
public class LAKDL_AF_CR_TerminationCalculationReport2 extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1,nf2;
	public ResultSet rs,rs1,rs2;
	public String m_chksql;
	ServletOutputStream out = null;
	ServletOutputStream out1= null;
	CallableStatement callstmt1 =null;
  public /*synchronized*/ void service(HttpServletRequest req, HttpServletResponse res)
	{
		try {
			
			//************************************************************	
			LAKDL_AF_CO_FU_methods CO_methods = new LAKDL_AF_CO_FU_methods();		
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
      String m_username 						= con_method.username;
			String header_name    = con_method.header_name;
			out = res.getOutputStream();
			//out.println("conn="+conn);
			//Class.forName("oracle.jdbc.driver.OracleDriver");
      //conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
			CallableStatement callstmt1 =null;
	    double m_res_value_1=0;
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  //nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			nf2 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf2.setMinimumFractionDigits(2);
			nf2.setMaximumFractionDigits(2);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			stmt1= conn.createStatement ();
			String m_Followu_no   = "";//req.getParameter("Followu_no");
      String m_termination_no=req.getParameter("TERMINATION_NO");
		  
			String m_termination_type=req.getParameter("TERMINATION_TYPE");
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					
	    	else if(m_chksql.trim().equals("main_page")){
				String m_finance_no=req.getParameter("FINANCE_NO");
				
				
					rs = stmt.executeQuery("SELECT TERMINATION_NO, "+//1
															 	 " NVL(FINANCE_NO,'-'), "+//2
															 " NVL(APPLICATION_NO,'-'), "+//3
															 " NVL(CLIENT_CODE,'-'), "+//4
                               " NVL(TO_CHAR(TERMINATION_VALIDITY_DATE,'DD-MM-YYYY'),'-'), "+//5
                               " NVL(TO_CHAR(APPLY_DATE,'DD-MM-YYYY'),'-'), "+//6
                               " NVL(REQUESTED_BY,'-'), "+//7
                               " NVL(RATE,0), "+//8
                               " NVL(AMOUNT,0), "+//9
                               " NVL(REMARKS,'-'), "+//10
                               " NVL(CHARGES,0), "+//11
                               " NVL(TERMINATION_COUNT,0), "+//12
                               " NVL(DUE_AMOUNT,0), "+//13
                               " NVL(REG_NO,'-'), "+//14
                               " NVL(RESIDUAL_VALUE,0), "+//15
                               " NVL(VAT_PER,0), "+//16
                               " NVL(GAIN_LOSS,0), "+//17
                               " NVL(NET_AMOUNT,0), "+//18
                               " NVL(NET_RENTALS,0), "+//19
															 " NVL(ODI_AMOUNT,0), "+//20
															 " NVL(ODI_ADJUSTMENT,0), "+//21
                               " NVL(ODI_NET,0), "+//22
                               " NVL(TERMINATION_TYPE,'-'), "+//23
															 " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-'), "+//24
															 " NVL(CLOSURE_IRR,0) "+//25
															 " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION "+
                               " WHERE TERMINATION_NO='"+m_termination_no+"'");
				
				String m_application_no_1="";
				String m_client_code_1="";
				String m_ter_validity_date_1="";
				String m_apply_date_1="";
				String m_requested_by_1="";
				double m_rate_1=0;
				double m_amount_1=0;
				String m_remarks_1="";
				double m_charges_1=0;
				double m_term_count_1=0;
				double m_due_amount_1=0;
				String m_reg_no_1="";
				
				//double m_res_value_1=0;
				double m_vat_per_1=0;
				double m_gain_loss_1=0;
				double m_net_amount_1=0;
				double m_net_rentals_1=0;
				double m_odi_amount_1=0;
				double m_odi_adjustment_1=0;
				double m_odi_net_1=0;
				String m_term_type_1="";
				String m_client_name_1="";
				double m_closure_irr_1=0;
				//Added by Dineth on 2009-01-21
				String m_trn_type_1="";
				double m_lease_rate_1=0;
				double m_odi_amt_1=0;
				double m_unallo_rec_1=0;
				double m_due_1=0;
				double m_due_net_1=0;
				double m_due_vat_1=0;
				double term_amount=0;
				double m_capital_amt_1=0;
				double m_nibsm_1=0;
				double m_ami_1=0;
				double m_cap_out_1=0;
				double rent=0;
				double rpv=0;
				double term=0;
				double tpv=0;
				double m_due_ren_1=0;
				double m_due_ren_net_1=0;
				double m_due_ren_vat_1=0;
				//End by Dineth on 2009-01-21
        while(rs.next()){
				 m_application_no_1=rs.getString(3);
				 m_client_code_1=rs.getString(4);
				 m_ter_validity_date_1=rs.getString(5);
				 m_apply_date_1=rs.getString(6);
				 m_requested_by_1=rs.getString(7);
				 m_rate_1=rs.getDouble(8);
				 m_amount_1=rs.getDouble(9);
				 m_remarks_1=rs.getString(10);
				 m_charges_1=rs.getDouble(11);
				 m_term_count_1=rs.getDouble(12);
				 m_due_amount_1=rs.getDouble(13);
				 m_reg_no_1=rs.getString(14);
				 m_res_value_1=rs.getDouble(15);
				 m_vat_per_1=rs.getDouble(16);
				 m_gain_loss_1=rs.getDouble(17);
				 m_net_amount_1=rs.getDouble(18);
				 m_net_rentals_1=rs.getDouble(19);
				 m_odi_amount_1=rs.getDouble(20);
				 m_odi_adjustment_1=rs.getDouble(21);
				 m_odi_net_1=rs.getDouble(22);
				 m_term_type_1=rs.getString(23);
				 m_client_name_1=rs.getString(24);
				 m_closure_irr_1=rs.getDouble(25);
				}
				
				rs = stmt.executeQuery(" SELECT A.APPLICATION_NO, A.CLIENT_CODE, A.INQUARY_NO, "+
																		 "        A.FINANCE_NO,TRANSACTION_TYPE,'', "+//A.INSURANCE_DATE, A.REVENUE_LICENSE_DATE
																		 "        '', '', '', "+//A.LUXURY_TAX_DATE,A.DRIVING_LICENSE_DATE,A.DISTRICT_CODE
																		 "        A.APPLICATION_STATUS, A.CO_APPLICANT, A.FACILITY_NO, "+
																		 "        NVL("+m_schema_name+".AF_CO_GET_ODI_DUE(A.FINANCE_NO),0)+ "+
																		 "        NVL("+m_schema_name+".AF_CO_CAL_FUTURE_ODI(A.FINANCE_NO,'"+m_apply_date_1+"'),0), "+
																		 "        "+m_schema_name+".AF_CO_GET_UNALLO_REC_CON_AMT(A.FINANCE_NO), "+
					  												 "        "+m_schema_name+".AF_CO_GET_APP_RATE('"+m_finance_no+"','') "+//"+m_vehicle_no+"				   	
																		 " FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
																		 " WHERE  FINANCE_NO = UPPER('"+m_finance_no+"') ");//AND A.CLIENT_CODE=UPPER('"+m_client_code+"') ");	
																			
																			
			 if(rs.next()){
				m_trn_type_1=rs.getString(5);
				m_lease_rate_1=rs.getDouble(15);
				m_odi_amt_1=rs.getDouble(13);
        m_unallo_rec_1=rs.getDouble(14);
				
			}		
			
							rs = stmt.executeQuery(" SELECT SUM(BALANCE_TO_BE_RECEIVED), "+
							                       "        SUM(BALANCE_TO_BE_RECEIVED-(ROUND((VAT_AMOUNT/TOTAL_AMOUNT)*BALANCE_TO_BE_RECEIVED))), "+
																		 "     	  SUM(ROUND((VAT_AMOUNT/TOTAL_AMOUNT)*BALANCE_TO_BE_RECEIVED)) "+
							                       " FROM   "+m_schema_name+".AF_CO_PRO_INVOICE "+
																		 " WHERE  FINANCE_NO='"+m_finance_no+"' AND ACTIVE_STATUS='Y' AND TOTAL_AMOUNT<>0 ");

       if(rs.next()){
				if(rs.getString(1)==null){
				   m_due_1=0;
					 m_due_net_1=0;
					 m_due_vat_1=0;
				   }
						else{
					 m_due_1=rs.getDouble(1);
				   m_due_net_1=rs.getDouble(2);
					 m_due_vat_1=rs.getDouble(3);
					}
			}
			
         			rs = stmt.executeQuery(" SELECT COUNT(*) "+
							                       " FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION "+
																		 " WHERE  FINANCE_NO='"+m_finance_no+"' AND REQUESTED_BY='CLIENT' ");
            int term_count_2=0;
						if(rs.next()){
						   term_count_2=rs.getInt(1);
						}
						
						


           rs = stmt.executeQuery(" SELECT AMOUNT "+
				                       " FROM   "+m_schema_name+".AF_CO_MAS_EARLY_TERMI_CHARGE "/*+
															 " WHERE  TERMINATION_TYPE = '"+m_term_type+"' "*/);


					if(rs.next()){
							if(term_count_2>0){
							   term_amount=rs.getDouble(1);
							}
							else{
							   term_amount=0;
							}
					}
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_CR_TEMP_TERM_DETAILS(:1,:2,:3,:4,:5,:6,:7,:8,:9);END;");
				  callstmt1.setString(1 ,m_termination_no);
          callstmt1.setString(2 ,m_finance_no);
          callstmt1.registerOutParameter(3,java.sql.Types.CHAR);
					callstmt1.registerOutParameter(4,java.sql.Types.CHAR);
					callstmt1.registerOutParameter(5,java.sql.Types.CHAR);
					callstmt1.registerOutParameter(6,java.sql.Types.CHAR);
					callstmt1.registerOutParameter(7,java.sql.Types.CHAR);
          callstmt1.registerOutParameter(8,java.sql.Types.CHAR);
          callstmt1.registerOutParameter(9,java.sql.Types.CHAR);
          
 				  
			    callstmt1.execute();
					m_capital_amt_1  = callstmt1.getDouble(3);
					m_nibsm_1        = callstmt1.getDouble(4);
					m_ami_1          = callstmt1.getDouble(5);
					m_cap_out_1      = callstmt1.getDouble(6);
					double m_per=0;
					if (callstmt1.getDouble(3)!=0){
					   m_per = ((callstmt1.getDouble(6))/callstmt1.getDouble(3))*100; 
					}else{
					   m_per = 0;
					}
					
					rs = stmt.executeQuery("SELECT NVL(SUM(GRENTAL_AMOUNT),0),NVL(SUM(NET_RENTAL_AMOUNT),0),NVL(SUM(GRENTAL_AMOUNT-NET_RENTAL_AMOUNT),0) "+
														   "FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
														   "WHERE  INVOICE_NO IS NULL AND    "+
														   "       RENTAL_DATE < TO_DATE('"+m_apply_date_1+"','DD-MM-YYYY')  AND "+
														   "      (PRO_INVOICE_NO, APPLICATION_NO,PRICING_NO) IN (SELECT INVOICE_NO, APPLICATION_NO,PRICING_NO "+
														   "                                                      FROM   "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
														   "                                                      WHERE  APPLICATION_NO  = (SELECT APPLICATION_NO "+
														   "                                                                                FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
														   "                                                                                WHERE  FINANCE_NO = '"+m_finance_no+"'))");
   
					if(rs.next()){
					   m_due_ren_1     = rs.getDouble(1);
						 m_due_ren_net_1 = rs.getDouble(2);
						 m_due_ren_vat_1 = rs.getDouble(3);
			//end by Dineth
				out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Asset Financing System</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
      out.println("var durationID=0;");
			out.println("var timerID;");

			out.println("</script>"); 
			//out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"check_lease('"+m_finance_no+"','"+m_client_code_1+"','"+m_apply_date_1+"')\">"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input type='hidden' name='Hid_scr_name' value='AF_CR_TERMINATION_CAL' > ");
			//out.println("<input type='hidden' name='TXT_SCREEN_NAME' value='AF_CR_TERMINATION_CAL' > ");
			out.println("<INPUT TYPE='Hidden' NAME='hid_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"NEW\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_win_type' VALUE=\"Main\">"); 			
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"NEW\">");
			out.println("<input type=hidden name=\"tot_val\" value=\"0\">");
			out.println("<input type=hidden name=\"hid_opt_val\" value=\"0\">");
			out.println("<input type=hidden name=\"hid_win_opt\" value=\"0\">");
			out.println("<input type=hidden name=\"VEHICLE_NO\" value=\"\">");
			out.println("<input type=hidden name=\"CHASSIS_NO\" value=\"\">");
			out.println("<input type=hidden name=\"INVOICE_NO\" value=\"\">");
			out.println("<input type=hidden name=\"SALE_VALUE\" value=\"\">");
			out.println("<input type=hidden name=\"SUM_SALE_VALUE\" value=\"\">");
			
				
			  out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\" class=table>");
				out.println("<tr>");
				
				out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
				out.println("<td class=\"border_wht\" valign=\"top\"> ");
				out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" class=table cellpadding=\"0\" height=\"100%\">");
				out.println("<tr> ");
				out.println("<td height=\"6%\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
				out.println("</tr>");
				out.println("<tr> ");
				out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
				out.println("</tr>");

				//Newly Added by Dineth
			  
								out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
			  
				
				out.println("<tr class=tr_input>");
				out.println("<td id=TMN><b>Termination No</b></td>");
				out.println("<td> ");
				out.println(m_termination_no);
				//out.println("<input type=button name=rec_help value=Help class=\"but_input\" onclick=\"term_help()\" disabled></td>");
				out.println("</td><input type=\"hidden\" name=\"TERMINATION_NO\" VALUE=\"\">");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td id=fod><b>Termination Type</b></td>");
				out.println("<td>"+m_termination_type+"</TD><input type=\"hidden\" name=\"TERMINATION_TYPE\" value=\"\">");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td id=TMD><b>Termination Date</b></td>");
				out.println("<td> ");
				out.println(m_apply_date_1);
				out.println("</td><input type=\"hidden\" name=\"TER_DATE\" value='"+m_apply_date_1+"'>");
				out.println("<td id=tod><b>Requested By</b></td>");
				out.println("<td>");
				out.println(m_requested_by_1);
				out.println("</td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td id=CLC><b>Client Code</b></td>");
				out.println("<td ID=CLIENT_CODE_1> ");
				out.println(m_client_code_1);
			  out.println("</td><input type=\"hidden\" name=\"CLIENT_CODE\" value=\"\">");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td id=tod><b>Client Name</b></td>");
				out.println("<td ID=CLIENT_NAME_1> ");
				out.println(m_client_name_1);
				out.println("</td><input type=\"hidden\" name=\"CLIENT_NAME\" value=\"\">");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");		

				
				out.println("<tr class=tr_input>");
				out.println("<td id=FNO><b>Finance No</b></td>");
				out.println("<td id=LEASE_NO_1>"+m_finance_no+"</td><input type=\"hidden\" name=\"LEASE_NO\" value='"+m_finance_no+"'><input name=\"APPLICATION_NO\" type=\"hidden\">");
				out.println("<td ><b>Termination Count</b></td>");
				out.println("<td ID=TER_COUNT_1> ");
				out.println(m_term_count_1);
				out.println("</td><input type=\"hidden\" name=\"TER_COUNT\">");
				//out.println("<td ></td>");
				//out.println("<td></td>");
				
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td id=TRNT><b>Transaction Type</b></td>");
				out.println("<td ID=TRN_TYPE_1>"+m_trn_type_1+"</td>");
				
				
				
				out.println("<input name=\"TRN_TYPE\" type=\"HIDDEN\" VALUE=\"\">");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				// Commented by Dineth on 2008-11-13
				/*rs1=stmt1.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_CLOSURE_IRR('"+m_finance_no+"','"+m_apply_date_1+"')"+
															 " FROM DUAL");	
				double m_closure_irr_1=0;
				if(rs1.next()){
				m_closure_irr_1=rs1.getDouble(1);
				}*/
				
				
				
				out.println("<td ><b>Closure IRR</b></td>");
				//out.println("<td><input name=\"CLOSURE_IRR\" type=\"text\" maxlength=\"15\" class=\"txt_input\" Disable STYLE=\"{text-align:right;}>");
				//out.println("</td>");
				out.println("<td >"+nf2.format(m_closure_irr_1)+"</td><input type=\"hidden\" name=\"CLOSURE_IRR\" value="+m_closure_irr_1+">");
				//out.println("<td></td><input name=\"CLOSURE_IRR\"  type=\"text\" maxlength=\"6\"  class=\"txt_input\" disabled onchange=\"\" STYLE=\"{text-align:right;}\">");
				
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td id=TMR><b>Termination Rate</b></td>");
				out.println("<td ID=\"TER_RATE_1\">");
				out.println(nf2.format(m_rate_1));
				out.println("</td><input name=\"TER_RATE\" type=\"hidden\" value="+nf2.format(m_rate_1)+">");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td ><b>Finance Rate</b></td>");
				out.println("<td ID=LEASE_RATE_1>"+nf2.format(m_lease_rate_1)+"</td> ");
				out.println("<input type=\"hidden\" name=\"LEASE_RATE\" value=\"\">");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td id=TMV><b>Termination Valid Date</b></td>");
				out.println("<td>");
				out.println(m_ter_validity_date_1);
				out.println("</td>");
				out.println("<td ><b>Due Amount</b></td>");
				out.println("<td ID=DUE_AMOUNT_1>"+nf2.format(m_due_1)+"</td><input name=\"DUE_AMOUNT\" type=\"hidden\">");
				out.println("<input name=\"DUE_NET\" type=\"hidden\" ><input name=\"DUE_VAT\" type=\"hidden\" >");
				
			  //out.println("</td>");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td ><b>Remark</b></td>");
				out.println("<td ID=REMARK>");
				out.println(m_remarks_1);
				out.println("</td><input type=\"hidden\" name=\"REMARK\">");
				out.println("<td ><b>Termination Charge</b></td>");
				out.println("<td ID=TERM_AMOUNT_1>"+nf2.format(term_amount)+"</td>");
				out.println("<input type=\"hidden\" name=\"TERM_AMOUNT\">");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td ><b>VAT %</b></td>");
				out.println("<td ID=VAT_PER_1>"+nf2.format(m_vat_per_1)+"</td> ");//
				out.println("<input type=\"hidden\" name=\"VAT_PER\">");
				out.println("<td ><b>Normal Rentals Due up to Termination Date</b></td>");
				out.println("<td ID=DUE_RENTALS_1>"+nf2.format(m_due_ren_1)+"</td>");
				out.println("<input name=\"DUE_RENTALS_NET\" type=\"hidden\" ><input name=\"DUE_RENTALS_VAT\" type=\"hidden\" >");
				out.println("<input type=\"hidden\" name=\"DUE_RENTALS\">");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
		       
				//amount finance , NIBSM ,AMI(Amount) , Capital Repayment ,Total Capital already settled, % (Amount Setteled/Financed Amount) 
		    out.println("<tr class=tr_input>");
				out.println("<td ><b>Amount Finance</b></td>");
				out.println("<td ID=AMOUNT_FINANCE_1>"+nf2.format(m_capital_amt_1)+"</td>");
				out.println("<input type=\"hidden\" name=\"AMOUNT_FINANCE\">");
				out.println("<td ><b>NIBSM</b></td>");
				out.println("<td ID=NIBSM_1>"+nf2.format(m_nibsm_1)+"</td>");
				out.println("<input type=\"hidden\" name=\"NIBSM\">");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
		    
				out.println("<tr class=tr_input>");
				out.println("<td><b>AMI</b></td>");
				out.println("<td ID=AMI_1>"+nf2.format(m_ami_1)+"</td>");
				out.println("<input type=\"hidden\" name=\"AMI\"><td>");
				out.println("<b>Unallocated Receipt</b></td>");
				out.println("<td ID=\"Unallo_Rec_1\">"+nf2.format(m_unallo_rec_1)+"</td>");
				//out.println("<input name=\"Unallo_Rec\" type=\"text\" disabled class=\"txt_input\" STYLE=\"{text-align:right;}\">");
				out.println("<input name=\"Unallo_Rec\" type=\"hidden\">");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
		    
		    out.println("<tr class=tr_input>");
				out.println("<td><b>Total Capital Outstanding</b></td>");
				out.println("<td ID=CAP_OUT_1>"+nf2.format(m_cap_out_1)+"</td>");
				out.println("<input type=\"hidden\" name=\"CAP_OUT\">");
				out.println("<td><b>%</b></td>");
				out.println("<td ID=CAP_OUT_PER_1>"+nf2.format(m_per)+"</td>");
				out.println("<input type=\"hidden\" name=\"CAP_OUT_PER\">");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
		     
				out.println("<tr class=tr_input>");
				out.println("<td><b>ODI</b></td>");
				out.println("<td ID=ODI_1>"+nf2.format(m_odi_amt_1)+"</td>");
				out.println("<input type=\"hidden\" name=\"ODI\">");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				out.println("<td><b>ODI Adjustment</b></td>");
				out.println("<td ID=ODI_ADJ_1> ");
				out.println(nf2.format(m_odi_adjustment_1));
				out.println("</td><input type=\"hidden\" name=\"ODI_ADJ\">");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
		    
				out.println("<tr class=tr_input>");
				out.println("<td><b>ODI Net</b></td>");
				out.println("<td ID=ODI_NET_1>"+nf2.format(m_odi_net_1)+"</td>");
				out.println("<input type=\"hidden\" name=\"ODI_NET\">");//out.println("<input name=\"CLIENT_TYPE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" ></td>");
				//Modified by Dineth on 2008-10-21
				out.println("<td>&nbsp;</td>");
				
				
				
				out.println("<td>&nbsp;</td>");
				//End by Dineth on 2008-10-21
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				out.println("<tr><td colspan='4'>");
		    
					
				
				
				rs = stmt.executeQuery(" SELECT B.CHASSIS_NO,B.REG_NO,VAT_PERCENTAGE,VAT_ON_RENTAL,VAT_APP,B.INVOICE_NO, "+
					                       "        "+m_schema_name+".AF_CO_GET_MODEL_DESC(B.MODEL_CODE),B.APPLICATION_NO "+
																 //" NVL((SELECT RESIDUAL_VALUE FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE INVOICE_NO=B.INVOICE_NO),0) "+
																 " FROM   "+m_schema_name+".AF_CO_PRO_APP_PRICING A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
																 " WHERE  A.PRICING_NO     = B.PRICING_NO AND "+
																 "        A.APPLICATION_NO = B.APPLICATION_NO AND "+  
																 "        A.PRO_INVOICE_NO = B.INVOICE_NO AND "+
																 "				B.APPLICATION_NO IN (SELECT APPLICATION_NO "+
																 "                             FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																 "                             WHERE  FINANCE_NO='"+m_finance_no+"') ");
																 //"        B.ACTIVE_STATUS='Y' ");
					
					                     /*"SELECT REG_NO "+
																 "FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
																 "WHERE APPLICATION_NO IN (SELECT APPLICATION_NO "+
																 "                         FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																 "                         WHERE  FINANCE_NO='"+m_Lease_no+"') AND "+
																 "      ACTIVE_STATUS='Y' "); */
								
					out.println("<table class=table border='0' width='100%' >");
					
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td WIDTH=10%>Id No</td>");
					out.println("<td WIDTH=10%>Chassis No</td>");
					out.println("<td WIDTH=10%>Vehicle No</td>");
					out.println("<td WIDTH=10%>Asset Description</td>");
					out.println("<td WIDTH=10%>VAT</td>");
					//Modified by Dineth on 2008-10-24
					//out.println("<td colspan=6>Residual value</td>");
					out.println("<td colspan=6>Sales Price</td>");
					out.println("</tr>");
          int n=0;
					
			    while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
            rs1=stmt1.executeQuery(" SELECT COUNT(TERMINATION_NO) "+
																	 " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES  "+
																	 " WHERE CHASSIS_NO='"+rs.getString(1)+"'"+
																	 " AND VEHICLE_NO='"+rs.getString(2)+"'"+
																	 " AND PRO_INVOICE_NO='"+rs.getString(6)+"'");
																	 
						int count=0;							
						if(rs1.next()){
						count=rs1.getInt(1);
						}
						rs2=stmt1.executeQuery(" SELECT SALE_VALUE FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES  "+
																	 " WHERE CHASSIS_NO='"+rs.getString(1)+"'"+
																	 " AND VEHICLE_NO='"+rs.getString(2)+"'"+
																	 " AND PRO_INVOICE_NO='"+rs.getString(6)+"'");
						String m_sale_value="";						
						if(rs2.next()){
						m_sale_value=rs2.getString(1);
						}
						out.println("<tr class=tr_input >");
						out.println("<td width=10%>"+rs.getString(6)+"<input type=hidden name=\"INVOICE_NO_"+n+"\"  value=\""+rs.getString(6)+"\"></td>");
						out.println("<td width=10%>"+rs.getString(1)+"<input type=hidden name=\"CHASSIS_NO_"+n+"\"  value=\""+rs.getString(1)+"\"></td>");
						out.println("<td width=10%>"+rs.getString(2)+"<input type=hidden name=\"VEHICLE_NO_"+n+"\"  value=\""+rs.getString(2)+"\"></td>");
						out.println("<td width=10%>"+rs.getString(7)+"</td>");
						out.println("<td width=10%>"+rs.getString(3)+"<input type=hidden name=\"VAT_"+n+"\"  value=\""+rs.getString(3)+"\"><input type=hidden name=\"VATR_"+n+"\"  value=\""+rs.getString(4)+"\"></td>");
						if(rs.getString(1)==null){
						 //out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\" ></td>");//remove disabled
							//out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
							out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+n+"\" value=\""+m_sale_value+"\" maxlength=\"25\" class=\"txt_input2\" disabled></td>");//Modified by Dineth on 2008-10-31
						 //out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" ></td>");
						if(count>0){
							out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+n+"\" onclick=ch_status(\""+n+"\") value=\"NO\" checked disabled></td>");
							}
							else{
							out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+n+"\" onclick=ch_status(\""+n+"\") value=\"NO\" disabled></td>");
							}
						}else{
						 //out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\" disabled ></td>");
						 out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+n+"\" value=\""+m_sale_value+"\" maxlength=\"25\" class=\"txt_input2\" disabled ></td>");
							if(count>0){
							out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+n+"\" onclick=ch_status(\""+n+"\") value=\"NO\" checked disabled></td>");
							}
							else{
							out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+n+"\" onclick=ch_status(\""+n+"\") value=\"NO\" disabled></td>");
							}
						}
						n=n+1;
						/*if(rs.next()){
 						  out.println("<td width=10%>"+rs.getString(6)+"<input type=hidden name=\"INVOICE_NO_"+j+"\"  value=\""+rs.getString(6)+"\"></td>");
							out.println("<td width=10%>"+rs.getString(1)+"<input type=hidden name=\"CHASSIS_NO_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
							out.println("<td width=10%>"+rs.getString(2)+"<input type=hidden name=\"VEHICLE_NO_"+j+"\"  value=\""+rs.getString(2)+"\"></td>");
							out.println("<td width=10%>"+rs.getString(3)+"<input type=hidden name=\"VAT_"+j+"\"  value=\""+rs.getString(3)+"\"><input type=hidden name=\"VATR_"+j+"\"  value=\""+rs.getString(4)+"\"></td>");
							if(rs.getString(1)==null){
							 out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
							 out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" disabled></td>");
							}else{
							 out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\"></td>");
							 out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" ></td>");
							}
							j=j+1;
						}else{
						 out.println("<td width=10%></td><td width=10%></td><td width=10%></td><td width=10%></td><td width=10%></td>");
						}*/
						out.println("</tr>");
						
					}
          					
          out.println("<input type=hidden name=hid_vcount value=\""+n+"\"></table>");
					
  	   
					
				
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				//out.println("<td colspan=4><input type=\"button\" name=\"btn_details\" class=\"but_input\" value=\"Details\" onclick=\"befor_cal()\" disabled=true></td>");
				out.println("<td colspan=4><div id=div_time_out></div></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'), PERCENTAGE, RENTAL_AMOUNT,  "+
					                        "       RENTAL_PV,TERM_AMOUNT, TERM_PV "+
																	"FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION_DETAILS "+
                                  "WHERE  TERMINATION_NO='"+m_termination_no+"' "+
																	"ORDER BY to_date(TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')"); 
				
				
				int m=0;
				while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  
									rent = rent  + rs.getDouble(3);
									rpv  = rpv   + rs.getDouble(4);
									term = term  + rs.getDouble(5);
									tpv  = tpv   + rs.getDouble(6);
									m=m+1;
									
									if(rs.next()){
									  
										rent = rent  + rs.getDouble(3);
									  rpv  = rpv   + rs.getDouble(4);
									  term = term  + rs.getDouble(5);
									  tpv  = tpv   + rs.getDouble(6);
									  m=m+1;
									}
                	
									
			        }
          
					String m_Vehicle_no="";
					String m_Chassis_no="";
					String m_Pro_inv_no="";
					String m_sale_val="";
					double sum_sale_val=0;
							rs= stmt.executeQuery(" SELECT VEHICLE_NO, "+
							                      " CHASSIS_NO,"+
																		" PRO_INVOICE_NO, "+
																		" SALE_VALUE "+
                                    " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES "+
                                    " WHERE TERMINATION_NO='"+m_termination_no+"'");
						 while(rs.next()){
							 m_Vehicle_no=rs.getString(1)+"@";
							 m_Chassis_no=rs.getString(2)+"@";
							 m_Pro_inv_no=rs.getString(3)+"@";
							 m_sale_val=nf2.format(rs.getDouble(4))+"@";
							 sum_sale_val=sum_sale_val+rs.getDouble(4);	
						}
						double m_sum_sal=sum_sale_val;
						double m_Term_val=term_amount;
						double m_vat_rate=m_vat_per_1;
						callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_CR_TEMP_TERM_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11);END;");
				  callstmt1.setString(1 ,nf2.format(m_rate_1));
          callstmt1.setString(2 ,m_Vehicle_no);
          callstmt1.setString(3 ,m_finance_no);
					callstmt1.setString(4 ,m_username);
					callstmt1.setString(5 ,m_apply_date_1);
          callstmt1.setString(6 ,m_client_code_1);
 				  callstmt1.setString(7 ,nf2.format(m_lease_rate_1));
 				  callstmt1.setString(8 ,nf2.format(m_vat_per_1));
					callstmt1.setString(9 ,m_sale_val);
					callstmt1.setString(10,m_Chassis_no);
					callstmt1.setString(11,m_Pro_inv_no);
						 				 
 				  //out.println("t5");
			    callstmt1.execute();
				//End by Dineth
									
					
					//af_cr_get_no_future_rental,af_co_get_rentals_paid,af_co_get_no_rentals_arries,m_Term_val
			   
					rs = stmt.executeQuery ("SELECT TO_CHAR(INSTALLMENT_DATE,'DD-MM-YYYY'),PERCENTAGE,  "+
																  "       SUM(RENTAL_AMOUNT), SUM(PV),SUM(TERMINATION_AMOUNT),   "+
																  "       SUM(TERMINATION_PV),INSTALLMENT_NO,"+
																	"       SUM(TERMINATION_PV-TERMINATION_AMOUNT) "+
																  "FROM   "+m_schema_name+".AF_CR_TBD_TERMINATION "+
																  "WHERE  ENT_USER='"+m_username+"' "+
																  "GROUP  BY INSTALLMENT_NO, "+
																  "       INSTALLMENT_DATE, PERCENTAGE "+
																	"ORDER BY TO_DATE(TO_CHAR(INSTALLMENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY'),TO_NUMBER(INSTALLMENT_NO) ");
																					
					/*
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          //out.println("<td colspan=6 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='15%' >Installment Date</td>");
					out.println("<td  width='5%'  align=right>Percentage</td>");
          out.println("<td  width='15%' align=right>Rental</td>");
					out.println("<td  width='15%' align=right>P.V. at Finance Rate</td>");
					out.println("<td  width='15%' align=right>P.V. at Termination Rate</td>");
					out.println("<td  width='15%' align=right>Termination Gain / Loss</td>");
					out.println("<td  width='20%' align=right>Gross Termination</td>");
					out.println("</tr>");
          */
           int j1 = 0;
					 double rent_1=0;
					 double rpv_1 =0;
					 double term_1=0;
					 double tpv_1 =0;
					 double gtv_1 =0;
					 double vat_1 =0;	
							/*
					    		out.println("<tr class=tr_input >");
									out.println("<td></td>");
                  out.println("<td ></td>");
                  out.println("<td align=right id=rent></td>");
                  out.println("<td align=right id=rpv ></td>");
									out.println("<td align=right id=term></td>");
									out.println("<td align=right id=tpv></td>");
									out.println("<td align=right id=gtv ></td>");
									out.println("</tr>");
							*/
							
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  /*out.println("<tr class=tr_input >");
									if(rs.getDouble(3)>=0){ 
									 out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j1+"\"  value=\""+rs.getString(1)+"\"></td>");
                  }else{
									 out.println("<td >NIBSM/AMI<input type=hidden name=\"INSTALL_DATE_"+j1+"\"  value=\""+rs.getString(1)+"\"></td>");
									}
									out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j1+"\"    value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j1+"\"        value=\""+rs.getString(3)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j1+"\"            value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j1+"\" value=\""+rs.getString(5)+"\"></td>");
                  out.println("<td align=right>"+nf.format((rs.getDouble(5)-rs.getDouble(4)))+"<input type=hidden name=\"TER_PV_AM_"+j1+"\" value=\""+(rs.getDouble(5)-rs.getDouble(6))+"\"></td>");
	                out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j1+"\"     value=\""+rs.getString(6)+"\"></td>");
									out.println("");
									out.println("</tr>");*/
									rent_1 = rent_1  + rs.getDouble(3);
									rpv_1  = rpv_1   + rs.getDouble(4);
									term_1 = term_1  + rs.getDouble(5);
									tpv_1  = tpv_1   + (rs.getDouble(5)-rs.getDouble(4));
									gtv_1  = gtv_1   + rs.getDouble(6);
									vat_1  = vat_1   + rs.getDouble(8);
									
									  j1=j1+1;
									
									if(rs.next()){
									  /*out.println("<tr class=tr_input1 >");
										out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j1+"\"  value=\""+rs.getString(1)+"\"></td>");
	                  out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j1+"\"    value=\""+rs.getString(2)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j1+"\"        value=\""+rs.getString(3)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j1+"\"            value=\""+rs.getString(4)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j1+"\" value=\""+rs.getString(5)+"\"></td>");
	                  out.println("<td align=right>"+nf.format((rs.getDouble(5)-rs.getDouble(4)))+"<input type=hidden name=\"TER_PV_AM_"+j1+"\" value=\""+(rs.getDouble(5)-rs.getDouble(6))+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j1+"\"     value=\""+rs.getString(6)+"\"></td>");
										out.println("");
										out.println("</tr>");*/
										rent_1 = rent_1  + rs.getDouble(3);
									  rpv_1  = rpv_1   + rs.getDouble(4);
									  term_1 = term_1  + rs.getDouble(5);
									  tpv_1  = tpv_1   + (rs.getDouble(5)-rs.getDouble(4));
									  gtv_1  = gtv_1   + rs.getDouble(6);
									  vat_1  = vat_1   + rs.getDouble(8);
									  j1=j1+1;
									}
                	
									
			        }
                double h_rent=0;
								double h_rpv=0;
								double h_term=0;
								double h_tpv=0;
								double h_gtv=0;
								double h_vat=0;
								double sn_val=0;
								double sv_val=0;
								double sg_val=0;
								double F_R_Val=0;
								double R_P_Val=0;
								double R_A_Val=0;
								double R_T_Val=0;
								double T_V_Val=0;
								double T_S_Val=0;
								double C_IRR_Val=0;
								h_rent=rent_1;
								h_rpv=rpv_1;
								h_term=term_1;
								h_tpv=tpv_1;
								h_gtv=gtv_1;
			          h_vat=vat_1;
					/*
					        out.println("<tr class=tr_input  >");
									out.println("<td><input type=hidden name=hid_count value="+j1+">");
									out.println("</td>");
                  out.println("<td ></td>");
                  out.println("<td align=right>"+nf.format(rent_1)+"<input type=hidden name=h_rent value="+nf.format(rent_1)+"></td>");
									out.println("<td align=right>"+nf.format(rpv_1) +"<input type=hidden name=h_rpv  value="+nf.format(rpv_1)+"></td>");
									out.println("<td align=right>"+nf.format(term_1)+"<input type=hidden name=h_term value="+nf.format(term_1)+"></td>");
									out.println("<td align=right>"+nf.format(tpv_1) +"<input type=hidden name=h_tpv  value="+nf.format(tpv_1)+"></td>");
									out.println("<td align=right>"+nf.format(gtv_1) +"<input type=hidden name=h_gtv  value="+nf.format(gtv_1)+"><input type=hidden name=h_vat  value="+nf.format(vat_1)+"></td>");
									out.println("</tr>");
					*/
					        rs = stmt.executeQuery (" SELECT '"+m_sum_sal+"' , "+
																					"        round('"+m_sum_sal+"'*('"+m_vat_rate+"'/100)), "+
										                      "        '"+m_sum_sal+"'+ round('"+m_sum_sal+"'*('"+m_vat_rate+"'/100)) "+
																          " FROM DUAL");
			 		/*if(rs.next()){       
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=6><input type=hidden name=hid_sn_val value="+rs.getString(1)+"><input type=hidden name=hid_sv_val value="+rs.getString(2)+"><input type=hidden name=hid_sg_val value="+rs.getString(3)+"></td>");
          out.println("</tr>");
					}else{
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=6><input type=hidden name=hid_sn_val value=0><input type=hidden name=hid_sv_val value=0><input type=hidden name=hid_sg_val value=0></td>");
          out.println("</tr>");
					
					}*/
					
					sn_val=m_sum_sal;
					sv_val=Math.round(m_sum_sal*m_vat_rate/100);
					sg_val=m_sum_sal+Math.round(m_sum_sal*m_vat_rate/100);
					
					rs = stmt.executeQuery (" SELECT "+m_schema_name+".AF_CR_GET_NO_FUTURE_RENTAL(APPLICATION_NO),"+
																	"	       "+m_schema_name+".AF_CO_GET_RENTALS_PAID_TER(FINANCE_NO),"+
																	"	       "+m_schema_name+".AF_CO_GET_NO_RENTALS_ARRIES(FINANCE_NO),"+
																	"        round('"+m_Term_val+"'*('"+m_vat_rate+"'/100)),"+
																	"        '"+m_Term_val+"'+round('"+m_Term_val+"'*('"+m_vat_rate+"'/100)),"+
																	"        "+m_schema_name+".AF_CO_GET_CLOSURE_IRR(FINANCE_NO,'"+m_apply_date_1+"') "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																	"	WHERE  FINANCE_NO= '"+m_finance_no+"'");
					if(rs.next()){
					/*out.println("<tr class=tr_input><input type=hidden name=F_R value=\""+nf1.format(rs.getDouble(1))+"\">");
					out.println("<input type=hidden name=R_P value=\""+nf1.format(rs.getDouble(2))+"\"><input type=hidden name=R_A value=\""+nf1.format(rs.getDouble(3))+"\"><input type=hidden name=R_T value=\""+nf1.format(rs.getDouble(2)+rs.getDouble(3)+rs.getDouble(1))+"\">");
					out.println("<input type=hidden name=T_V value=\""+nf.format(rs.getDouble(4))+"\"><input type=hidden name=T_S value=\""+nf.format(rs.getDouble(5))+"\"><input type=hidden name=C_IRR value=\""+nf1.format(rs.getDouble(6))+"\">");
					*/
					F_R_Val=rs.getDouble(1);
					R_P_Val=rs.getDouble(2);
					R_A_Val=rs.getDouble(3);
					R_T_Val=rs.getDouble(1)+rs.getDouble(2)+rs.getDouble(3);
					T_V_Val=rs.getDouble(4);
					T_S_Val=rs.getDouble(5);
					C_IRR_Val=rs.getDouble(6);
					/*
					}else{
					/*out.println("<tr class=tr_input><input type=hidden name=F_R value=\"\">");
					out.println("<input type=hidden name=R_P value=\"\"><input type=hidden name=R_A value=\"\">");
					out.println("<input type=hidden name=T_V value=\"\"><input type=hidden name=T_S value=\"\"><input type=hidden name=C_IRR value=\"\">");
					
					
					*/
					}
          //out.println("<td align=right colspan=6><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          
				
				
				
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<div id=inv>");
				out.println("<table cellpadding=\"2\" cellspacing=\"2\" border=\"0\" class=table>");
				out.println("<tr>");
				out.println("<td WIDTH=25% id=H1 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td WIDTH=25% id=H2 STYLE=\"{font-weight: bold}\" align=right>Net Amount</td>");
				out.println("<td WIDTH=25% id=H3 STYLE=\"{font-weight: bold}\" align=right>VAT Amount</td>");
				out.println("<td WIDTH=25% id=H4 STYLE=\"{font-weight: bold}\" align=right>Total Amount</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td id=S11 STYLE=\"{font-weight: bold}\">Due Amount</td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=S12 /*STYLE=\"{font-weight: bold}\"*/ align=right>"+nf2.format(m_due_net_1)+"</td>");
				out.println("<td id=S13 /*STYLE=\"{font-weight: bold}\"*/ align=right>"+nf2.format(m_due_vat_1)+"</td>");
				out.println("<td id=S14 /*STYLE=\"{font-weight: bold}\"*/ align=right>"+nf2.format(m_due_1)+"</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td id=S21 STYLE=\"{font-weight: bold}\">Rentals Due up to Termination Date</td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=S22 /*STYLE=\"{font-weight: bold}\"*/ align=right>"+nf2.format(m_due_ren_net_1)+"</td>");
				out.println("<td id=S23 /*STYLE=\"{font-weight: bold}\"*/ align=right>"+nf2.format(m_due_ren_vat_1)+"</td>");
				out.println("<td id=S24 /*STYLE=\"{font-weight: bold}\"*/ align=right>"+nf2.format(m_due_ren_1)+"</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td id=S31 STYLE=\"{font-weight: bold}\">Termination Calculation Amount</td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=S32 /*STYLE=\"{font-weight: bold}\"*/ align=right>"+nf2.format(term)+"</td>");
				out.println("<td id=S33 /*STYLE=\"{font-weight: bold}\"*/ align=right>"+nf2.format(h_vat)+"</td>");
				out.println("<td id=S34 /*STYLE=\"{font-weight: bold}\"*/ align=right>"+nf2.format(h_gtv)+"</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td id=S41 STYLE=\"{font-weight: bold}\">Sales Price</td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=S42 /*STYLE=\"{font-weight: bold}\"*/ align=right>"+nf2.format(sn_val)+"</td>");
				out.println("<td id=S43 /*STYLE=\"{font-weight: bold}\"*/ align=right>"+nf2.format(sv_val)+"</td>");
				out.println("<td id=S44 /*STYLE=\"{font-weight: bold}\"*/ align=right>"+nf2.format(sg_val)+"</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td id=S45 STYLE=\"{font-weight: bold}\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=S46 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("<td id=S47 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("<td id=S48 /*STYLE=\"{font-weight: bold}\"*/ align=right></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td id=S51 STYLE=\"{font-weight: bold}\">Termination Charges</td>");
				out.println("<td id=S52  align=right>"+nf2.format(term_amount)+"</td>");
				out.println("<td id=S53  align=right>"+nf2.format(T_V_Val)+"</td>");
				out.println("<td id=S54  align=right>"+nf2.format(T_S_Val)+"</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td id=S61 STYLE=\"{font-weight: bold}\">ODI</td>");
				out.println("<td id=S62  align=right>"+m_odi_net_1+"</td>");
				out.println("<td id=S63  align=right>0.00</td>");
				out.println("<td id=S64  align=right>"+m_odi_net_1+"</td>");
				out.println("</tr>");
				
				double m_tot_net_1=m_odi_net_1+term_amount+m_due_net_1+m_due_ren_net_1+term+sn_val;
				double m_tot_vat_1=T_V_Val+m_due_vat_1+m_due_ren_vat_1+h_vat+sv_val;
				double m_tot_grv_1=m_odi_net_1+T_S_Val+m_due_1+m_due_ren_1+h_gtv+sg_val;
				
				out.println("<tr>");
				out.println("<td id=T1 STYLE=\"{font-weight: bold}\">Total Amount</td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=T2 STYLE=\"{font-weight: bold}\" align=right>"+nf2.format(m_tot_net_1)+"</td>");
				out.println("<td id=T3 STYLE=\"{font-weight: bold}\" align=right>"+nf2.format(m_tot_vat_1)+"</td>");
				out.println("<td id=T4 STYLE=\"{font-weight: bold}\" align=right>"+nf2.format(m_tot_grv_1)+"</td>");
				out.println("</tr>");
				
				
				//Added by Dineth on 2008-10-01
				
				out.println("<tr>");
				out.println("<td id=S65 STYLE=\"{font-weight: bold}\">Unallocated Receipt</td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=S66 align=right>"+nf2.format(m_unallo_rec_1)+"</td>");
				out.println("<td id=S67 align=right>0.00</td>");
				out.println("<td id=S68 align=right>"+nf2.format(m_unallo_rec_1)+"</td>");
				out.println("</tr>");
				
				double m_net_tot_net=m_tot_net_1-m_unallo_rec_1;
				double m_net_tot_grv=m_tot_grv_1-m_unallo_rec_1;
				out.println("<tr>");
				out.println("<td id=T30 STYLE=\"{font-weight: bold}\">Net Total Amount</td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=T31 STYLE=\"{font-weight: bold}\" align=right>"+nf2.format(m_net_tot_net)+"</td>");
				out.println("<td id=T32 STYLE=\"{font-weight: bold}\" align=right>"+nf2.format(m_tot_vat_1)+"</td>");
				out.println("<td id=T33 STYLE=\"{font-weight: bold}\" align=right>"+nf2.format(m_net_tot_grv)+"</td>");
				out.println("</tr>");
				
				
				//End by Dineth on 2008-10-01
				out.println("<tr>");
				out.println("<td>&nbsp;</td><td></td><td></td><td></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td id=R1 STYLE=\"{font-weight: bold}\">No of Future Rentals</td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=R2  align=right>"+nf2.format(F_R_Val)+"</td>");
				out.println("<td id=R7 STYLE=\"{font-weight: bold;color: green}\" align=right>Termination Gain / Loss</td>");
				out.println("<td id=R8 STYLE=\"{font-weight: bold;color: green}\" align=right>"+nf2.format(h_tpv)+"</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td id=R3  STYLE=\"{font-weight: bold}\">No of Rentals Paid</td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=R4   align=right>"+nf2.format(R_P_Val)+"</td>");
				out.println("<td id=R9  STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("<td id=R10 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td id=R5  STYLE=\"{font-weight: bold}\">No of Rentals Arrears</td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=R6   align=right>"+nf2.format(R_A_Val)+"</td>");
				out.println("<td id=R11 STYLE=\"{font-weight: bold;color: green}\" align=right></td>");
				out.println("<td id=R12 STYLE=\"{font-weight: bold;color: green}\" align=right></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td id=R13 STYLE=\"{font-weight: bold}\">Total</td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td id=R14  align=right>"+R_T_Val+"</td>");
				out.println("<td id=R15 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("<td id=R16 STYLE=\"{font-weight: bold}\" align=right></td>");
				out.println("</tr>");
				
				out.println("</table>");
				out.println("</div>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr><td colspan='4' height='80' valign='top' align='left'>");
				
				rs=stmt.executeQuery(" SELECT NVL(ENT_USER,'-'),NVL(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'-'), "+
						                     " NVL(APPROVED1_BY,'-'),NVL(TO_CHAR(APPROVED1_DATE,'DD-MM-YYYY'),'-'), "+
																 " NVL(APPROVED2_BY,'-'),NVL(TO_CHAR(APPROVED2_DATE,'DD-MM-YYYY'),'-'), "+
																 " NVL(MOD_USER,'-'),NVL(TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'-') "+
																 " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION "+
																 " WHERE TERMINATION_NO='"+m_termination_no+"'");
						boolean more=rs.next();
						if(more){
						out.println("<br>");
						out.println("<table border='0' width='60%' class='table' cellspacing='0' >");
						out.println("<tr>");
						out.println("<td><b><u>Termination Approvals</u></b></td>");
						out.println("</tr>");
						out.println("</table>");
				    out.println("<br>");
						
						out.println("<table border='1' bordercolor='lightgrey' width='60%' class='table' cellspacing='0' >");
						out.println("<tr>");
				
						out.println("<td width='20%' class=div_input><b>Entered by</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(1)+"</td>");
						out.println("<td width='20%' class=div_input><b>Entered Date</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("</tr>");
						
						//Added by Dineth on 2009-02-16
						
						if(rs.getString(3).equals("-") && rs.getString(5).equals("-")){
						out.println("<tr>");
				
						out.println("<td width='20%' class=div_input><b>Approved 1 by</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(7)+"</td>");
						out.println("<td width='20%' class=div_input><b>Approval Date</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(8)+"</td>");
						out.println("</tr>");
			
			
						out.println("<tr>");
				
						out.println("<td width='20%' class=div_input><b>Approved 2 by</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(7)+"</td>");
						out.println("<td width='20%' class=div_input><b>Approval Date</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(8)+"</td>");
						out.println("</tr>");
			
						out.println("<tr>");
				    
				
				    
						out.println("<td width='20%' class=div_input><b>Processed by</b></td>");
						out.println("<td width='10%' class=div_input>-</td>");
						out.println("<td width='20%' class=div_input><b>Processed Date</b></td>");
						out.println("<td width='10%' class=div_input>-</td>");
						out.println("</tr>");
			 
						
						}else{
						
						
						out.println("<tr>");
				
						out.println("<td width='20%' class=div_input><b>Approved 1 by</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(3)+"</td>");
						out.println("<td width='20%' class=div_input><b>Approval Date</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(4)+"</td>");
						out.println("</tr>");
			
			
						out.println("<tr>");
				
						out.println("<td width='20%' class=div_input><b>Approved 2 by</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
						out.println("<td width='20%' class=div_input><b>Approval Date</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(6)+"</td>");
						out.println("</tr>");
						
						out.println("<tr>");
				
						out.println("<td width='20%' class=div_input><b>Processed by</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(7)+"</td>");
						out.println("<td width='20%' class=div_input><b>Processed Date</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(8)+"</td>");
						out.println("</tr>");
						}
						//End by Dineth on 2009-02-16
						out.println("</table>");
						}

				
				
				out.println("</td></tr>");
				out.println("<tr><td colspan='4' valign='top' align='left'>");
				rs=stmt.executeQuery(" SELECT NVL(A.ENT_USER,'-'),NVL(A.ENT_REMARKS,'-'),NVL(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'-'), "+
                             " NVL(A.APPROVED1_BY,'-'),NVL(TO_CHAR(A.APPROVED1_DATE,'DD-MM-YYYY'),'-'),NVL(A.APPROVED1_REMARKS,'-'), "+
                             " NVL(A.APPROVED2_BY,'-'),NVL(TO_CHAR(A.APPROVED2_DATE,'DD-MM-YYYY'),'-'),NVL(A.APPROVED2_REMARKS,'-'),A.MOD_DATE "+
                             " FROM "+m_schema_name+".AF_CO_PRO_ODI_AJUSTMENT_DET A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
                             " WHERE A.INVOICE_NO=B.INVOICE_NO "+
                             " AND B.CLIENT_CODE='"+m_client_code_1+"' "+
                             " AND ROWNUM=1 "+
                             " ORDER BY A.MOD_DATE DESC");
															
				double tot_adjust_amt=0;											
				boolean more5=rs.next();
						if(more5){
						rs1=stmt1.executeQuery(" SELECT C.MOD_DATE,C.ADJ FROM (SELECT A.MOD_DATE,NVL(SUM(A.AJUSTED_AMOUNT),0) ADJ "+
                             " FROM "+m_schema_name+".AF_CO_PRO_ODI_AJUSTMENT_DET A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
                             " WHERE A.INVOICE_NO=B.INVOICE_NO "+
                             " AND B.CLIENT_CODE='"+m_client_code_1+"' "+
                             //" AND ROWNUM=1 "+
														 " GROUP BY A.MOD_DATE "+
                             " ORDER BY A.MOD_DATE DESC) C "+
														 " WHERE ROWNUM=1 ");
						
						boolean more6=rs1.next();
						if(more6){
						tot_adjust_amt=rs1.getDouble(2);
						}
						
						
						
						out.println("<br>");
						out.println("<table border='0' width='90%' class='table' cellspacing='0' >");
						out.println("<tr>");
						out.println("<td><b><u>ODI Approvals-"+tot_adjust_amt+"</u></b></td>");
						out.println("</tr>");
						out.println("</table>");
				    out.println("<br>");
						
						out.println("<table border='1' bordercolor='lightgrey' width='90%' class='table' cellspacing='0' >");
						
						out.println("<tr>");
				
						out.println("<td width='20%' class=div_input><b>Entered by</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(1)+"</td>");
						out.println("<td width='20%' class=div_input><b>Entered Date</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input><b>Remarks</b></td>");
						out.println("<td width='20%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("</tr>");
			
						
						
						
						
						out.println("<tr>");
				
						out.println("<td width='20%' class=div_input><b>Approved 1 by</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(4)+"</td>");
						out.println("<td width='20%' class=div_input><b>Approval Date</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
						out.println("<td width='10%' class=div_input><b>Remarks</b></td>");
						out.println("<td width='20%' class=div_input>"+rs.getString(6)+"</td>");
						out.println("</tr>");
			
			
						out.println("<tr>");
				
						out.println("<td width='20%' class=div_input><b>Approved 2 by</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(7)+"</td>");
						out.println("<td width='20%' class=div_input><b>Approval Date</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(8)+"</td>");
						out.println("<td width='10%' class=div_input><b>Remarks</b></td>");
						out.println("<td width='20%' class=div_input>"+rs.getString(9)+"</td>");
						out.println("</tr>");
						
						
						out.println("</table>");
						}
				
				out.println("</td></tr>");
				
				out.println("<tr><td colspan='4'>");
				
				/*
				out.println("<tr class=tr_input>");
				out.println("<td class=\"pdn_txtpos\" style=\"height: 10px\">");
				out.println("<table cellpadding=\"2\" cellspacing=\"2\" border=\"0\" class=table>");
				out.println("<tr class=tr_input>");
				
				out.println("<td style=\"width: 6px\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
				out.println("<td width=10%>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset_1 value=\"New\" class=mainbut onclick=load_screen_status(\"NEW\"); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");//document.Form1.OPTION_DESC.value
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				*/
				//out.println("<td><input type=button name=edit_1 value=\"Delete\" class=mainbut onclick=load_screen_status(\"DELETE\"); onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				//out.println("<td width=10%>&nbsp;</td>");
				//out.println("<td><input type=button name=delete value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				//out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=cancel value=\"Re-active\" class=mainbut onclick=befor_active(); onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				/*out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=b_submit_1 value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
        out.println("<td>&nbsp;</td>");
				out.println("<td><input class='mainbut' type='button' name='BUT_HELP_MAIN_1' value=\"Help\" onClick=\"help_update()\" disabled>  </td>"); 
			  out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset_1 value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Cancel\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back_1 value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=cal_1 value=\"Calculate\" class=mainbut onclick=befor_cal();></td>");
        
				out.println("</tr></table>");
				out.println("&nbsp;&nbsp;</td>");
				out.println("</tr>");
				*/
				
			
			/*
			rs = stmt.executeQuery ("SELECT TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'), PERCENTAGE, RENTAL_AMOUNT,  "+
					                        "       RENTAL_PV,TERM_AMOUNT, TERM_PV "+
																	"FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION_DETAILS "+
                                  "WHERE  TERMINATION_NO='"+m_termination_no+"' "+
																	"ORDER BY to_date(TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')"); 
																					
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=6 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='15%' >Installment Date</td>");
					out.println("<td  width='5%'  align=right>Percentage</td>");
          out.println("<td  width='20%' align=right>Rental</td>");
					out.println("<td  width='20%' align=right>P.V.</td>");
					out.println("<td  width='20%' align=right>Termination Amount</td>");
					out.println("<td  width='20%' align=right>P.V.</td>");
					out.println("</tr>");
      
           int j = 0;
					 
					    		out.println("<tr class=tr_input  ><b>");
									out.println("<td></td>");
                  out.println("<td ></td>");
                  out.println("<td align=right id=rent><b></b></td>");
                  out.println("<td align=right id=rpv ><b></b></td>");
									out.println("<td align=right id=term><b></b></td>");
									out.println("<td align=right id=tpv ><b></b></td>");
									out.println("</b></tr>");
							
							
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input >");
									out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j+"\"    value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j+"\"        value=\""+rs.getString(3)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j+"\"            value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j+"\"     value=\""+rs.getString(6)+"\"></td>");
									out.println("");
									out.println("</tr>");
									
									j=j+1;
									
									if(rs.next()){
									  out.println("<tr class=tr_input1 >");
										out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
	                  out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j+"\"    value=\""+rs.getString(2)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j+"\"        value=\""+rs.getString(3)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j+"\"            value=\""+rs.getString(4)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j+"\"     value=\""+rs.getString(6)+"\"></td>");
										out.println("");
										out.println("</tr>");
										
									  j=j+1;
									}
                	
									
			        }
          
					        out.println("<tr class=tr_input  >");
									out.println("<td><input type=hidden name=hid_count value="+j+"></td>");
                  out.println("<td ></td>");
                  out.println("<td align=right>"+nf.format(rent)+"<input type=hidden name=h_rent value="+nf.format(rent)+"></td>");
									out.println("<td align=right>"+nf.format(rpv) +"<input type=hidden name=h_rpv  value="+nf.format(rpv)+"></td>");
									out.println("<td align=right>"+nf.format(term)+"<input type=hidden name=h_term value="+nf.format(term)+"></td>");
									out.println("<td align=right>"+nf.format(tpv) +"<input type=hidden name=h_tpv  value="+nf.format(tpv)+"></td>");
									out.println("</tr>");
					
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=6><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          */
					out.println("<table class=table border='0' width='100%' >");
					rs = stmt.executeQuery ("SELECT TO_CHAR(INSTALLMENT_DATE,'DD-MM-YYYY'),PERCENTAGE,  "+
																  "       SUM(RENTAL_AMOUNT), SUM(PV),SUM(TERMINATION_AMOUNT),   "+
																  "       SUM(TERMINATION_PV),INSTALLMENT_NO,"+
																	"       SUM(TERMINATION_PV-TERMINATION_AMOUNT) "+
																  "FROM   "+m_schema_name+".AF_CR_TBD_TERMINATION "+
																  "WHERE  ENT_USER='"+m_username+"' "+
																  "GROUP  BY INSTALLMENT_NO, "+
																  "       INSTALLMENT_DATE, PERCENTAGE "+
																	"ORDER BY TO_DATE(TO_CHAR(INSTALLMENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY'),TO_NUMBER(INSTALLMENT_NO) ");
						
						
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          //out.println("<td colspan=6 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='15%' >Installment Date</td>");
					out.println("<td  width='5%'  align=right>Percentage</td>");
          out.println("<td  width='15%' align=right>Rental</td>");
					out.println("<td  width='15%' align=right>P.V. at Finance Rate</td>");
					out.println("<td  width='15%' align=right>P.V. at Termination Rate</td>");
					out.println("<td  width='15%' align=right>Termination Gain / Loss</td>");
					out.println("<td  width='20%' align=right>Gross Termination</td>");
					out.println("</tr>");
					
					while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input >");
									if(rs.getDouble(3)>=0){ 
									 out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j1+"\"  value=\""+rs.getString(1)+"\"></td>");
                  }else{
									 out.println("<td >NIBSM/AMI<input type=hidden name=\"INSTALL_DATE_"+j1+"\"  value=\""+rs.getString(1)+"\"></td>");
									}
									out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j1+"\"    value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j1+"\"        value=\""+rs.getString(3)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j1+"\"            value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j1+"\" value=\""+rs.getString(5)+"\"></td>");
                  out.println("<td align=right>"+nf.format((rs.getDouble(5)-rs.getDouble(4)))+"<input type=hidden name=\"TER_PV_AM_"+j1+"\" value=\""+(rs.getDouble(5)-rs.getDouble(6))+"\"></td>");
	                out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j1+"\"     value=\""+rs.getString(6)+"\"></td>");
									out.println("");
									out.println("</tr>");
									if(rs.next()){
									  out.println("<tr class=tr_input1 >");
										out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j1+"\"  value=\""+rs.getString(1)+"\"></td>");
	                  out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j1+"\"    value=\""+rs.getString(2)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j1+"\"        value=\""+rs.getString(3)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j1+"\"            value=\""+rs.getString(4)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j1+"\" value=\""+rs.getString(5)+"\"></td>");
	                  out.println("<td align=right>"+nf.format((rs.getDouble(5)-rs.getDouble(4)))+"<input type=hidden name=\"TER_PV_AM_"+j1+"\" value=\""+(rs.getDouble(5)-rs.getDouble(6))+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j1+"\"     value=\""+rs.getString(6)+"\"></td>");
										out.println("");
										out.println("</tr>");
										}
					}
					out.println("</table>");
					out.println("</td></tr>");
				
				
				
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");   
				out.println("<tr class=tr_input> ");
				out.println("<td valign=\"bottom\" height=\"20\"></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<div id=rec><input type=hidden name=hid_count value=0></div>");
				out.println("</td>");
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td class=\"line\" height=\"1\">");
				out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
					out.println("<tr class=tr_input>");
				out.println("<td class=\"pdn_txtpos1 & txt-bodyRed\">");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				
					
          out.println("</table>");
			out.println("</form>"); 
			//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
		      }
      }
				/*else if(m_chksql.trim().equals("get_term_details")){
			       
				String m_finance_no = req.getParameter("finance_no");
				String m_vehicle_no = req.getParameter("veh_no");
			        
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_CR_TEMP_TERMINATION_CAPITAL(:1,:2,:3,:4,:5,:6,:7,:8,:9);END;");
				  callstmt1.setString(1 ,m_username);
          callstmt1.setString(2 ,m_vehicle_no);
          callstmt1.setString(3 ,m_finance_no);
					callstmt1.registerOutParameter(4,java.sql.Types.CHAR);
					callstmt1.registerOutParameter(5,java.sql.Types.CHAR);
					callstmt1.registerOutParameter(6,java.sql.Types.CHAR);
					callstmt1.registerOutParameter(7,java.sql.Types.CHAR);
					callstmt1.registerOutParameter(8,java.sql.Types.CHAR);
          callstmt1.registerOutParameter(9,java.sql.Types.CHAR);

 				  //out.println("t5");
			    callstmt1.execute();
					//out.println("t6");
			   
	      //boolean flag = rs.next();
				out.println("<Root>");
				//for(; flag; flag = rs.next())				{
					out.println("<ITEM>");
					out.println("<CAP_AMT>"+  nf.format(callstmt1.getDouble(4))  + "</CAP_AMT>");
					out.println("<NIBSM>"  +  nf.format(callstmt1.getDouble(5))  + "</NIBSM>");
					out.println("<AMI_AMT>"+  nf.format(callstmt1.getDouble(6))  + "</AMI_AMT>");
					out.println("<CAP_OUT>"+  nf.format(callstmt1.getDouble(7))  + "</CAP_OUT>");
					out.println("<AMI_CAP>"+  nf.format(callstmt1.getDouble(8))  + "</AMI_CAP>");
					
					double m_per = ((callstmt1.getDouble(7))/callstmt1.getDouble(4))*100; 
					out.println("<CAP_PER>"+  nf.format(m_per)  + "</CAP_PER>");
					out.println("<CAP_PER>"+  nf.format(callstmt1.getDouble(9))  + "</CAP_PER>");
					out.println("</ITEM>");
				//}
																	   
				//out.println("</DATA>");
				out.println("</Root>");
      
      }*/
			
			else if(m_chksql.trim().equals("get_Vehicles")){
			
			    String m_Lease_no     = req.getParameter("Lease_no");
  	   
					rs = stmt.executeQuery(" SELECT B.CHASSIS_NO,B.REG_NO,VAT_PERCENTAGE,VAT_ON_RENTAL,VAT_APP,B.INVOICE_NO, "+
					                       "        "+m_schema_name+".AF_CO_GET_MODEL_DESC(B.MODEL_CODE),B.APPLICATION_NO "+
																 //" NVL((SELECT RESIDUAL_VALUE FROM "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE INVOICE_NO=B.INVOICE_NO),0) "+
																 " FROM   "+m_schema_name+".AF_CO_PRO_APP_PRICING A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
																 " WHERE  A.PRICING_NO     = B.PRICING_NO AND "+
																 "        A.APPLICATION_NO = B.APPLICATION_NO AND "+  
																 "        A.PRO_INVOICE_NO = B.INVOICE_NO AND "+
																 "				B.APPLICATION_NO IN (SELECT APPLICATION_NO "+
																 "                             FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																 "                             WHERE  FINANCE_NO='"+m_Lease_no+"') ");
																 //"        B.ACTIVE_STATUS='Y' ");
					
					                     /*"SELECT REG_NO "+
																 "FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
																 "WHERE APPLICATION_NO IN (SELECT APPLICATION_NO "+
																 "                         FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																 "                         WHERE  FINANCE_NO='"+m_Lease_no+"') AND "+
																 "      ACTIVE_STATUS='Y' "); */
									
					out.println("<table class=table border='0' width='100%' >");
					
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td WIDTH=10%>Id No</td>");
					out.println("<td WIDTH=10%>Chassis No</td>");
					out.println("<td WIDTH=10%>Vehicle No</td>");
					out.println("<td WIDTH=10%>Asset Description</td>");
					out.println("<td WIDTH=10%>VAT</td>");
					//Modified by Dineth on 2008-10-24
					//out.println("<td colspan=6>Residual value</td>");
					out.println("<td colspan=6>Sales Price</td>");
					out.println("</tr>");
          int j=0;
					
			    while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
            rs1=stmt1.executeQuery(" SELECT COUNT(TERMINATION_NO) "+
																	 " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES  "+
																	 " WHERE CHASSIS_NO='"+rs.getString(1)+"'"+
																	 " AND VEHICLE_NO='"+rs.getString(2)+"'"+
																	 " AND PRO_INVOICE_NO='"+rs.getString(6)+"'");
																	 
						int count=0;							
						if(rs1.next()){
						count=rs1.getInt(1);
						}
						rs2=stmt1.executeQuery(" SELECT SALE_VALUE FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES  "+
																	 " WHERE CHASSIS_NO='"+rs.getString(1)+"'"+
																	 " AND VEHICLE_NO='"+rs.getString(2)+"'"+
																	 " AND PRO_INVOICE_NO='"+rs.getString(6)+"'");
						String m_sale_value="";						
						if(rs2.next()){
						m_sale_value=rs2.getString(1);
						}
						out.println("<tr class=tr_input >");
						out.println("<td width=10%>"+rs.getString(6)+"<input type=hidden name=\"INVOICE_NO_"+j+"\"  value=\""+rs.getString(6)+"\"></td>");
						out.println("<td width=10%>"+rs.getString(1)+"<input type=hidden name=\"CHASSIS_NO_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
						out.println("<td width=10%>"+rs.getString(2)+"<input type=hidden name=\"VEHICLE_NO_"+j+"\"  value=\""+rs.getString(2)+"\"></td>");
						out.println("<td width=10%>"+rs.getString(7)+"</td>");
						out.println("<td width=10%>"+rs.getString(3)+"<input type=hidden name=\"VAT_"+j+"\"  value=\""+rs.getString(3)+"\"><input type=hidden name=\"VATR_"+j+"\"  value=\""+rs.getString(4)+"\"></td>");
						if(rs.getString(1)==null){
						 //out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\" ></td>");//remove disabled
							//out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
							out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\""+m_sale_value+"\" maxlength=\"25\" class=\"txt_input2\" disabled></td>");//Modified by Dineth on 2008-10-31
						 //out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" ></td>");
						if(count>0){
							out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" checked disabled></td>");
							}
							else{
							out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" disabled></td>");
							}
						}else{
						 //out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\" disabled ></td>");
						 out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\""+m_sale_value+"\" maxlength=\"25\" class=\"txt_input2\" disabled ></td>");
							if(count>0){
							out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" checked disabled></td>");
							}
							else{
							out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" disabled></td>");
							}
						}
						j=j+1;
						/*if(rs.next()){
 						  out.println("<td width=10%>"+rs.getString(6)+"<input type=hidden name=\"INVOICE_NO_"+j+"\"  value=\""+rs.getString(6)+"\"></td>");
							out.println("<td width=10%>"+rs.getString(1)+"<input type=hidden name=\"CHASSIS_NO_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
							out.println("<td width=10%>"+rs.getString(2)+"<input type=hidden name=\"VEHICLE_NO_"+j+"\"  value=\""+rs.getString(2)+"\"></td>");
							out.println("<td width=10%>"+rs.getString(3)+"<input type=hidden name=\"VAT_"+j+"\"  value=\""+rs.getString(3)+"\"><input type=hidden name=\"VATR_"+j+"\"  value=\""+rs.getString(4)+"\"></td>");
							if(rs.getString(1)==null){
							 out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
							 out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" disabled></td>");
							}else{
							 out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\"0\" maxlength=\"25\" class=\"txt_input2\"></td>");
							 out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" ></td>");
							}
							j=j+1;
						}else{
						 out.println("<td width=10%></td><td width=10%></td><td width=10%></td><td width=10%></td><td width=10%></td>");
						}*/
						out.println("</tr>");
						
					}
          					
          out.println("<input type=hidden name=hid_vcount value=\""+j+"\"></table>");
					
    }
    else if(m_chksql.trim().equals("get_Termi_Det")){
			
			    String m_Termination_no    = req.getParameter("Termination_no");
          
			   
					rs = stmt.executeQuery ("SELECT TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'), PERCENTAGE, RENTAL_AMOUNT,  "+
					                        "       RENTAL_PV,TERM_AMOUNT, TERM_PV "+
																	"FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION_DETAILS "+
                                  "WHERE  TERMINATION_NO='"+m_Termination_no+"' "+
																	"ORDER BY to_date(TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')"); 
																					
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=6 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='15%' >Installment Date</td>");
					out.println("<td  width='5%'  align=right>Percentage</td>");
          out.println("<td  width='20%' align=right>Rental</td>");
					out.println("<td  width='20%' align=right>P.V.</td>");
					out.println("<td  width='20%' align=right>Termination Amount</td>");
					out.println("<td  width='20%' align=right>P.V.</td>");
					out.println("</tr>");
      
           int j = 0;
					 double rent=0;
					 double rpv =0;
					 double term=0;
					 double tpv =0;
					    		out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ ><b>");
									out.println("<td></td>");
                  out.println("<td ></td>");
                  out.println("<td align=right id=rent><b></b></td>");
                  out.println("<td align=right id=rpv ><b></b></td>");
									out.println("<td align=right id=term><b></b></td>");
									out.println("<td align=right id=tpv ><b></b></td>");
									out.println("</b></tr>");
							
							
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input >");
									out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j+"\"    value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j+"\"        value=\""+rs.getString(3)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j+"\"            value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j+"\"     value=\""+rs.getString(6)+"\"></td>");
									out.println("");
									out.println("</tr>");
									rent = rent  + rs.getDouble(3);
									rpv  = rpv   + rs.getDouble(4);
									term = term  + rs.getDouble(5);
									tpv  = tpv   + rs.getDouble(6);
									j=j+1;
									
									if(rs.next()){
									  out.println("<tr class=tr_input1 >");
										out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
	                  out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j+"\"    value=\""+rs.getString(2)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j+"\"        value=\""+rs.getString(3)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j+"\"            value=\""+rs.getString(4)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j+"\"     value=\""+rs.getString(6)+"\"></td>");
										out.println("");
										out.println("</tr>");
										rent = rent  + rs.getDouble(3);
									  rpv  = rpv   + rs.getDouble(4);
									  term = term  + rs.getDouble(5);
									  tpv  = tpv   + rs.getDouble(6);
									  j=j+1;
									}
                	
									
			        }
          
					        out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td><input type=hidden name=hid_count value="+j+"></td>");
                  out.println("<td ></td>");
                  out.println("<td align=right>"+nf.format(rent)+"<input type=hidden name=h_rent value="+nf.format(rent)+"></td>");
									out.println("<td align=right>"+nf.format(rpv) +"<input type=hidden name=h_rpv  value="+nf.format(rpv)+"></td>");
									out.println("<td align=right>"+nf.format(term)+"<input type=hidden name=h_term value="+nf.format(term)+"></td>");
									out.println("<td align=right>"+nf.format(tpv) +"<input type=hidden name=h_tpv  value="+nf.format(tpv)+"></td>");
									out.println("</tr>");
					
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=6><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
					
          out.println("</table>");

    }
			
	    	else if(m_chksql.trim().equals("get_Termi_Char")){
			
			    String m_Lease_no     = req.getParameter("Lease_no");
          String m_Vehicle_no   = req.getParameter("Vehicle_no");
          String m_Disco_rate   = req.getParameter("Disco_rate");
          String m_App_date     = req.getParameter("App_Date");
          String m_client       = req.getParameter("Client");
					String m_lease_rate   = req.getParameter("lease_rate");
					String m_vat_rate     = req.getParameter("vat_rate");
          String m_SaleVal      = req.getParameter("SaleVal");
          String m_Chassis_no   = req.getParameter("chas_no");
					String m_Invoice_no   = req.getParameter("invo_no");
					String m_sum_sal      = req.getParameter("sum_sal");
					String m_Term_val     = req.getParameter("Term_val");
					
					
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_CR_TEMP_TERMINATION_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11);END;");
				  callstmt1.setString(1 ,m_Disco_rate);
          callstmt1.setString(2 ,m_Vehicle_no);
          callstmt1.setString(3 ,m_Lease_no);
					callstmt1.setString(4 ,m_username);
					callstmt1.setString(5 ,m_App_date);
          callstmt1.setString(6 ,m_client);
 				  callstmt1.setString(7 ,m_lease_rate);
 				  callstmt1.setString(8 ,m_vat_rate);
					callstmt1.setString(9 ,m_SaleVal);
					callstmt1.setString(10,m_Chassis_no);
					callstmt1.setString(11,m_Invoice_no);
						 				 
 				  //out.println("t5");
			    callstmt1.execute();
					//out.println("t6");
					out.println("<table class=table border='0' width='100%' >");
					
					//af_cr_get_no_future_rental,af_co_get_rentals_paid,af_co_get_no_rentals_arries,m_Term_val
			   
					rs = stmt.executeQuery ("SELECT TO_CHAR(INSTALLMENT_DATE,'DD-MM-YYYY'),PERCENTAGE,  "+
																  "       SUM(RENTAL_AMOUNT), SUM(PV),SUM(TERMINATION_AMOUNT),   "+
																  "       SUM(TERMINATION_PV),INSTALLMENT_NO,"+
																	"       SUM(TERMINATION_PV-TERMINATION_AMOUNT) "+
																  "FROM   "+m_schema_name+".AF_CR_TBD_TERMINATION "+
																  "WHERE  ENT_USER='"+m_username+"' "+
																  "GROUP  BY INSTALLMENT_NO, "+
																  "       INSTALLMENT_DATE, PERCENTAGE "+
																	"ORDER BY TO_DATE(TO_CHAR(INSTALLMENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY'),TO_NUMBER(INSTALLMENT_NO) ");
																					
					
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          //out.println("<td colspan=6 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='15%' >Installment Date</td>");
					out.println("<td  width='5%'  align=right>Percentage</td>");
          out.println("<td  width='15%' align=right>Rental</td>");
					out.println("<td  width='15%' align=right>P.V. at Finance Rate</td>");
					out.println("<td  width='15%' align=right>P.V. at Termination Rate</td>");
					out.println("<td  width='15%' align=right>Termination Gain / Loss</td>");
					out.println("<td  width='20%' align=right>Gross Termination</td>");
					out.println("</tr>");
      
           int j = 0;
					 double rent=0;
					 double rpv =0;
					 double term=0;
					 double tpv =0;
					 double gtv =0;
					 double vat =0;	
							
					    		out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td></td>");
                  out.println("<td ></td>");
                  out.println("<td align=right id=rent></td>");
                  out.println("<td align=right id=rpv ></td>");
									out.println("<td align=right id=term></td>");
									out.println("<td align=right id=tpv></td>");
									out.println("<td align=right id=gtv ></td>");
									out.println("</tr>");
							
							
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input >");
									if(rs.getDouble(3)>=0){ 
									 out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
                  }else{
									 out.println("<td >NIBSM/AMI<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
									}
									out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j+"\"    value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j+"\"        value=\""+rs.getString(3)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j+"\"            value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
                  out.println("<td align=right>"+nf.format((rs.getDouble(5)-rs.getDouble(4)))+"<input type=hidden name=\"TER_PV_AM_"+j+"\" value=\""+(rs.getDouble(5)-rs.getDouble(6))+"\"></td>");
	                out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j+"\"     value=\""+rs.getString(6)+"\"></td>");
									out.println("");
									out.println("</tr>");
									rent = rent  + rs.getDouble(3);
									rpv  = rpv   + rs.getDouble(4);
									term = term  + rs.getDouble(5);
									tpv  = tpv   + (rs.getDouble(5)-rs.getDouble(4));
									gtv  = gtv   + rs.getDouble(6);
									vat  = vat   + rs.getDouble(8);
									
									  j=j+1;
									
									if(rs.next()){
									  out.println("<tr class=tr_input1 >");
										out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
	                  out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j+"\"    value=\""+rs.getString(2)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j+"\"        value=\""+rs.getString(3)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j+"\"            value=\""+rs.getString(4)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
	                  out.println("<td align=right>"+nf.format((rs.getDouble(5)-rs.getDouble(4)))+"<input type=hidden name=\"TER_PV_AM_"+j+"\" value=\""+(rs.getDouble(5)-rs.getDouble(6))+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j+"\"     value=\""+rs.getString(6)+"\"></td>");
										out.println("");
										out.println("</tr>");
										rent = rent  + rs.getDouble(3);
									  rpv  = rpv   + rs.getDouble(4);
									  term = term  + rs.getDouble(5);
									  tpv  = tpv   + (rs.getDouble(5)-rs.getDouble(4));
									  gtv  = gtv   + rs.getDouble(6);
									  vat  = vat   + rs.getDouble(8);
									  j=j+1;
									}
                	
									
			        }
                
			         		
					        out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td><input type=hidden name=hid_count value="+j+">");
									out.println("</td>");
                  out.println("<td ></td>");
                  out.println("<td align=right>"+nf.format(rent)+"<input type=hidden name=h_rent value="+nf.format(rent)+"></td>");
									out.println("<td align=right>"+nf.format(rpv) +"<input type=hidden name=h_rpv  value="+nf.format(rpv)+"></td>");
									out.println("<td align=right>"+nf.format(term)+"<input type=hidden name=h_term value="+nf.format(term)+"></td>");
									out.println("<td align=right>"+nf.format(tpv) +"<input type=hidden name=h_tpv  value="+nf.format(tpv)+"></td>");
									out.println("<td align=right>"+nf.format(gtv) +"<input type=hidden name=h_gtv  value="+nf.format(gtv)+"><input type=hidden name=h_vat  value="+nf.format(vat)+"></td>");
									out.println("</tr>");
					
					        rs = stmt.executeQuery (" SELECT '"+m_sum_sal+"' , "+
																					"        round('"+m_sum_sal+"'*('"+m_vat_rate+"'/100)), "+
										                      "        '"+m_sum_sal+"'+ round('"+m_sum_sal+"'*('"+m_vat_rate+"'/100)) "+
																          " FROM DUAL");
			 		if(rs.next()){       
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=6><input type=hidden name=hid_sn_val value="+rs.getString(1)+"><input type=hidden name=hid_sv_val value="+rs.getString(2)+"><input type=hidden name=hid_sg_val value="+rs.getString(3)+"></td>");
          out.println("</tr>");
					}else{
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=6><input type=hidden name=hid_sn_val value=0><input type=hidden name=hid_sv_val value=0><input type=hidden name=hid_sg_val value=0></td>");
          out.println("</tr>");
					
					}
					
					
					rs = stmt.executeQuery (" SELECT "+m_schema_name+".AF_CR_GET_NO_FUTURE_RENTAL(APPLICATION_NO),"+
																	"	       "+m_schema_name+".AF_CO_GET_RENTALS_PAID_TER(FINANCE_NO),"+
																	"	       "+m_schema_name+".AF_CO_GET_NO_RENTALS_ARRIES(FINANCE_NO),"+
																	"        round('"+m_Term_val+"'*('"+m_vat_rate+"'/100)),"+
																	"        '"+m_Term_val+"'+round('"+m_Term_val+"'*('"+m_vat_rate+"'/100)),"+
																	"        "+m_schema_name+".AF_CO_GET_CLOSURE_IRR(FINANCE_NO,'"+m_App_date+"') "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																	"	WHERE  FINANCE_NO= '"+m_Lease_no+"'");
					if(rs.next()){
					out.println("<tr class=tr_input><input type=hidden name=F_R value=\""+nf1.format(rs.getDouble(1))+"\">");
					out.println("<input type=hidden name=R_P value=\""+nf1.format(rs.getDouble(2))+"\"><input type=hidden name=R_A value=\""+nf1.format(rs.getDouble(3))+"\"><input type=hidden name=R_T value=\""+nf1.format(rs.getDouble(2)+rs.getDouble(3)+rs.getDouble(1))+"\">");
					out.println("<input type=hidden name=T_V value=\""+nf.format(rs.getDouble(4))+"\"><input type=hidden name=T_S value=\""+nf.format(rs.getDouble(5))+"\"><input type=hidden name=C_IRR value=\""+nf1.format(rs.getDouble(6))+"\">");
					}else{
					out.println("<tr class=tr_input><input type=hidden name=F_R value=\"\">");
					out.println("<input type=hidden name=R_P value=\"\"><input type=hidden name=R_A value=\"\">");
					out.println("<input type=hidden name=T_V value=\"\"><input type=hidden name=T_S value=\"\"><input type=hidden name=C_IRR value=\"\">");
					
					}
          //out.println("<td align=right colspan=6><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("</table>");

    }else if(m_chksql.trim().equals("get_invoice_det")){
			
			    String m_client      = req.getParameter("client");
          String m_finance_no  = req.getParameter("finance_no");
				
					rs = stmt.executeQuery (" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
																	"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE, "+
																	"	       A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK, "+
																	"	       EXCHANGE_RATE_REP_CURR,REC_AMMOUNT_REP_CURR, "+
																	"	       EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+ 
																	"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
																	"	WHERE  A.REC_NO = B.REC_NO AND STATUS='P' AND "+
																	"	       BAL_TOBE_RECEIVE>0 AND CLIENT_CODE = '"+m_client+"' ");

					
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='15%' >Receipt No</td>");
					out.println("<td  width='15%' align=right>Receipt Amount</td>");
          out.println("<td  width='15%' align=right>Allocated Amount</td>");
					out.println("<td  width='20%' align=right>Balance Amount</td>");
					out.println("<td  width='35%' align=right>Amount</td>");
					//out.println("<td  width='10%'  ></td>");
					out.println("</tr>");
      
           int j = 0;      					
							
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td >"+rs.getString(1) +"<input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"ALLO_AMOUN_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled>");
									out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
                  //out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
									//out.println("     </td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									out.println("<td></TD>");
									out.println("<td colspan=5 ><div id='inv_"+j+"'><input type=hidden name=hid_invoice_count_"+j+" value=0></div>");
									out.println("</td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									out.println("<td>&nbsp;</TD>");
									out.println("<td colspan=5 >");
									out.println("</td>");
									out.println("</tr>");
                	j=j+1;
									
	         }
          
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");

				
      }
		
		
		else if(m_chksql.trim().equals("get_Receipt")){
			
			    String m_client      = req.getParameter("client");
          
					rs = stmt.executeQuery (" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
																	"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE, "+
																	"	       A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK, "+
																	"	       EXCHANGE_RATE_REP_CURR,REC_AMMOUNT_REP_CURR, "+
																	"	       EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+ 
																	"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
																	"	WHERE  A.REC_NO = B.REC_NO AND STATUS='P' AND "+
																	"	       BAL_TOBE_RECEIVE>0 AND CLIENT_CODE = '"+m_client+"' ");

					
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='15%' >Receipt No</td>");
					out.println("<td  width='15%' align=right>Receipt Amount</td>");
          out.println("<td  width='15%' align=right>Allocated Amount</td>");
					out.println("<td  width='20%' align=right>Balance Amount</td>");
					out.println("<td  width='35%' align=right>Amount</td>");
					//out.println("<td  width='10%'  ></td>");
					out.println("</tr>");
      
           int j = 0;      					
							
              while(rs.next()){
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td >"+rs.getString(1) +"<input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"ALLO_AMOUN_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled>");
									out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
                  //out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
									//out.println("     </td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									out.println("<td></TD>");
									out.println("<td colspan=5 ><div id='inv_"+j+"'><input type=hidden name=hid_invoice_count_"+j+" value=0></div>");
									out.println("</td>");
									out.println("</tr>");
									out.println("<tr class=tr_input>");
									out.println("<td>&nbsp;</TD>");
									out.println("<td colspan=5 >");
									out.println("</td>");
									out.println("</tr>");
                	j=j+1;
									
	         }
          
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");

				
      } 	
			
			else if(m_chksql.equals("SHOW_BALANCES_INFO")){
				
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");

				
	         out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Asset Financing System</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			//out.println("<SCRIPT language=\"JavaScript\">"); 
      out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\">"); 
			 
			/*
			String		Sql_pending_invoice=" SELECT "+
																"    INVOICE_NO, "+
																"    NET_AMOUNT, "+
																"    VAT_AMOUNT, "+
																"    TOTAL_AMOUNT, "+
																"    SETTELE_AMOUNT, "+
																"    BALANCE_TO_BE_RECEIVED, "+ 
																"    TO_CHAR(VALUE_DATE,'DD-MM-YYYY') "+ 
																"   FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
																"   WHERE     ACTIVE_STATUS='Y' AND "+
																"   FINANCE_NO IN   "+
																"   (SELECT "+
																"    FINANCE_NO "+ 
																"    FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																"    WHERE  UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
																"           APPLICATION_STATUS<>'CANCEL' AND BALANCE_TO_BE_RECEIVED>0) ";
           

          

			  rs=stmt1.executeQuery(Sql_pending_invoice);
				boolean  more =rs.next();
						
				
				if (more) {
				
				  out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><u><b>Invoice Pending</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				 
					out.println("<br>");
				
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input ><b>Invoice No</b></td>");
					out.println("<td width='10%' class=div_input ><b>Date</b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>Net</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>VAT</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Gross</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Settled Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Balance Outstanding</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input align='left'>"+rs.getString(7)+"</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"</td>");

					out.println("</tr>");
					
				
					
					more = rs.next();
				}
					out.println("</table>");
				
																

								String		Sql_ODI=" SELECT "+
								 "    ODI_REF_NO, "+
								 "    INVOICE_NO, "+
								 "    TO_DATE(ODI_DATE,'DD-MM-YYYY'), "+
								 "    ODI_CAL_AMOUNT, "+
								 "    ODI_BAL_AMOUNT, "+
								 "    ODI_SETTLED_AMOUNT "+
								 "  FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
								 " WHERE INVOICE_NO IN "+
								 "   (SELECT "+
								 "    INVOICE_NO "+
								 "    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
								 "    WHERE  UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
								 "           ACTIVE_STATUS='Y') "+
								 "   AND    ODI_BAL_AMOUNT=0     ";
     
			  rs=stmt1.executeQuery(Sql_ODI);
				  more =rs.next();

				
				
				if (more) {
				
				  out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><u><b>Over Due Interest Pending</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				 
					out.println("<br>");
				
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input ><b>ODI Ref No</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Invoice No</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>ODI Date</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Balance Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Settled Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"</td>");

					out.println("</tr>");
					
					more = rs.next();
				}
					out.println("</table>");
				
				*/
									String		Sql_Unallocated=" SELECT "+ 
								  "  A.REC_NO, "+
								  "  NVL(A.PAYER_ACC_NO,'-'), "+
								  "  NVL(A.PAYER_BRANCH_CODE,'-'), "+
								  "  A.REC_AMOUNT, "+
								  "  NVL(A.ACC_NO,'-'), "+
								  "  NVL(A.BRANCH_CODE,'-') ,"+
									"  TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') "+ //added by nuwan de silva on 18-09-07
								//  "  STATUS "+
								  "  FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+
									"       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
								  "  WHERE  A.REC_NO=B.REC_NO  AND "+
                  "         B.BAL_TOBE_RECEIVE<>0 AND STATUS <> 'C' "+
									"         AND UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')  ";
     
			    rs=stmt1.executeQuery(Sql_Unallocated);
				  boolean more =rs.next();

				
				
				if (more) {
				
				  out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><u><b>Un Allocated Receipt</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				 
					out.println("<br>");
				
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input ><b>Receipt No</b></td>");
					out.println("<td width='10%' class=div_input ><b>Date</b></td>");
					out.println("<td width='10%' class=div_input ><b>Payer Account No</b></td>");
					out.println("<td width='15%' class=div_input ><b>Payer Branch Code</b></td>");
					out.println("<td width='15%' class=div_input ><b>Account No</b></td>");
					out.println("<td width='15%' class=div_input ><b>Branch Code</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Receipt Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input >"+rs.getString(7)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(3)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(5)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(6)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");

					out.println("</tr>");
					
					more = rs.next();
				}
					out.println("</table>");
				
				
/*				
						String		Sql_Realisation=" SELECT "+
				"    REC_NO,"+
  			"    SETTLE_MODE,"+
				"    NVL(PAYER_ACC_NO,'-'), "+
				"    NVL(PAYER_BRANCH_CODE,'-'),"+
				"    REC_AMOUNT, "+
				"    DECODE(STATUS,'B','Banked','E','Entered') ,"+
				"    TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') "+ //added by nuwan de silva on 18-09-07
				"    FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
				"    WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
				"    SETTLE_MODE <>'CASH'  AND "+
				"    STATUS IN('B','E') ";
 
     
			    rs=stmt1.executeQuery(Sql_Realisation);
				  more =rs.next();

				
				
				if (more) {
				
				 
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><u><b>Receipt Pending Realisation </b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				 
					out.println("<br>");
				
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Receipt</b></td>");
					out.println("<td width='10%' class=div_input ><b>Date</b></td>");
					out.println("<td width='10%' class=div_input><b>Settle Mode</b></td>");
					out.println("<td width='15%' class=div_input><b>Account No</b></td>");
					out.println("<td width='15%' class=div_input><b>Payer Branch Code</b></td>");
					out.println("<td width='15%' class=div_input><b>Status</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Receipt Amount</b></td>");
					out.println("</tr>");
					
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input >"+rs.getString(7)+"</td>");
					out.println("<td width='10%' class=div_input >"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(3)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(4)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(6)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");

					out.println("</tr>");
					
					more = rs.next();
				}
					out.println("</table>");
				
								String		Sql_POD=" SELECT "+
							  "  POD_REF_NO, "+
							 // "  FINANCE_NO, "+
							  "  NVL(CHEQUE_NO,'-'), "+
							  "  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'),  "+
							  "  NVL(PAYER_ACC_NO,'-'), "+
							  "  NVL(PAYER_BRANCH_CODE,'-'), "+
							  "  CHEQUE_AMOUNT "+
							  "  FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
								"  WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
							  "  STATUS='INV'   ";
 
 
     
			    rs=stmt1.executeQuery(Sql_POD);
				  more =rs.next();

				
				
				if (more) {
				
				 
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><u><b>Post Dated Cheque </b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				 
					out.println("<br>");
				
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>POD Ref.No</b></td>");
					out.println("<td width='15%' class=div_input><b>Cheque No</b></td>");
					out.println("<td width='15%' class=div_input><b>Cheque Date</b></td>");
					out.println("<td width='15%' class=div_input><b>Payer Account No</b></td>");
					out.println("<td width='15%' class=div_input ><b>Payer Branch Code</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>cheque Amount</b></td>");
					out.println("</tr>");
					
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_POD_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(3)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(4)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(5)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"</td>");

					out.println("</tr>");
					
					more = rs.next();
				}
					out.println("</table>");
	*/
	        out.println("</body>"); 
					out.println("</HTML>"); 
				

				
				
				}
				
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
      //return null;
		}finally{
		  if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	    if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}
			

