//Dev. By : Sandun Jayathilake 
//Date    : 07-07-2009

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;

import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_TerminationCalculationReport extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1,nf2;
	public ResultSet rs,rs1,rs2;
	public String m_chksql;
	ServletOutputStream out = null;
	
  public  void service(HttpServletRequest req, HttpServletResponse res)
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
			CallableStatement callstmt1 =null;
	    double m_res_value_1=0;
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US); 
			nf1.setMaximumFractionDigits(4);
		  nf1.setMinimumFractionDigits(4);
			
			nf2 = java.text.NumberFormat.getInstance(Locale.US);   
			nf2.setMinimumFractionDigits(0);
		  nf2.setMaximumFractionDigits(0);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			stmt1= conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					
	    	else if(m_chksql.trim().equals("main_page")){
			
	        String m_Followu_no   = "";//req.getParameter("Followu_no");
          String m_termination_no=req.getParameter("TERMINATION_NO");
					String m_finance_no=req.getParameter("FINANCE_NO");
					String m_termination_type=req.getParameter("TERMINATION_TYPE");
					//Newly Added by Dineth
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
															 " NVL(CLOSURE_IRR,0), "+//25
															 " NVL("+m_schema_name+".AF_CO_GET_APP_RATE('"+m_finance_no+"',''),0), "+//26
															 " INVOICE_NO, "+//27
															 " NVL("+m_schema_name+".AF_CO_GET_UNALLO_REC_CON_AMT(FINANCE_NO),0), "+//28
															 " NVL("+m_schema_name+".AF_CO_GET_ODI_DUE(FINANCE_NO),0)+NVL("+m_schema_name+".AF_CO_CAL_FUTURE_ODI(FINANCE_NO,TO_CHAR(APPLY_DATE,'DD-MM-YYYY')),0), "+//29
															 " NVL("+m_schema_name+".AF_CO_GET_TERMI_AMI(APPLICATION_NO),0) "+//30	
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
				double m_vat_per_1=0;
				double m_gain_loss_1=0;
				double m_net_amount_1=0;
				double m_net_rentals_1=0;
				double m_odi_amount_1=0;
				double m_odi_adjustment_1=0;
				double m_odi_net_1=0,m_sum_sal=0,m_net_rental_amount=0;
				String m_term_type_1="";
				String m_client_name_1="";
				double m_closure_irr_1=0,m_norm_rental=0,m_nibsm=0,m_cap_out=0,m_ami=0;
				double m_lese_rate = 0,m_fin_amount=0,m_unallo_rec=0,m_odi=0,m_rate=0;
				String m_trn_type ="";
				String m_inv_no ="";
				String m_Vehicle_no="";
				String m_Chassis_no="";
				String m_rate_2="";
				String m_lese_rate_1="";
				String m_vat_per_2="";
				String m_sale_value="";		
				int k = 0;
				double rent=0;
				double rpv =0;
				double term=0;
				double tpv =0;
				int m_period =0,m_prequency=0;
				double m_due_net=0,m_due_vat=0,m_due_tot=0,m_due_rent_tot=0,m_due_rent_vat=0,m_due_rent_net=0,m_vat_per=0;
				
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
				 m_lese_rate = rs.getDouble(26);
				 //m_inv_no= rs.getString(27);
				 m_unallo_rec= rs.getDouble(28);
				 m_odi =rs.getDouble(29);
				 m_ami =rs.getDouble(30);
				 m_rate_2=rs.getString(8);
				 m_lese_rate_1 = rs.getString(26);
				 m_vat_per_2=rs.getString(16);
				}
								
		
			
			
			rs = stmt.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_TRAN_TYPE_DESC(A.TRANSACTION_TYPE) "+
													   " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
													   " WHERE A.FINANCE_NO = '"+m_finance_no+"' ");
				
			
			if(rs.next()){
			m_trn_type   = rs.getString(1);
			
			}
				
			
			 rs = stmt.executeQuery( "SELECT NVL(SUM(NET_RENTAL_AMOUNT*(1+("+m_schema_name+".AF_CO_GET_VAT_ON_RENTAL_PRO(TRANSACTION_TYPE,TO_DATE('"+m_apply_date_1+"','DD-MM-YYYY'))/100))),0) "+
			                         "FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
														   "WHERE  A.APPLICATION_NO = B.APPLICATION_NO AND "+
															 "       INVOICE_NO IS NULL AND    "+
														   "       RENTAL_DATE < TO_DATE('"+m_apply_date_1+"','DD-MM-YYYY')  AND "+
														   "      (PRO_INVOICE_NO,a.APPLICATION_NO,PRICING_NO) IN (SELECT INVOICE_NO, APPLICATION_NO,PRICING_NO "+
														   "                                                      FROM   "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
														   "                                                      WHERE  APPLICATION_NO  = (SELECT APPLICATION_NO "+
														   "                                                                                FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
														   "                                                                                WHERE  FINANCE_NO    = '"+m_finance_no+"') AND "+
															 "                                                                                       ACTIVE_STATUS = 'Y' )");
			
			if(rs.next()){
			m_norm_rental = rs.getDouble(1);
			}
			
			
			rs = stmt.executeQuery("  SELECT  SUM(NVL(NIBSM,0)) "+
														 "	FROM    "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
														 "	WHERE   APPLICATION_NO='"+m_application_no_1+"' "+
														 "  AND    (PRO_INVOICE_NO,PRICING_NO) IN (SELECT INVOICE_NO,PRICING_NO "+
														 "	                                       FROM    "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
														 "	                                       WHERE   APPLICATION_NO = '"+m_application_no_1+"' AND  "+
														 "	                                       ACTIVE_STATUS  = 'Y') ");
																		
			
			
			
				if(rs.next()){
			  m_nibsm = rs.getDouble(1);
			  }
			
			
			
			    rs = stmt.executeQuery(" SELECT  SUM(NVL(CAPITAL_AMOUNT,0))    "+      
												         " FROM    "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT a "+
												         " WHERE   (PRO_INVOICE_NO,PRICING_NO) IN (SELECT INVOICE_NO,PRICING_NO "+
												         "                        FROM   "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
												         "                        where  APPLICATION_NO =   '"+m_application_no_1+"' AND "+
												         "                               ACTIVE_STATUS  =   'Y' "+
												         "                       ) AND "+
												         "         AMI_AMOUNT =  0 AND AMI_AMOUNT IS NOT NULL AND "+
												         "         INVOICE_NO IS NULL  AND "+
												         "         APPLICATION_NO = '"+m_application_no_1+"' ");
				
				
				
				if(rs.next()){
			  m_cap_out = rs.getDouble(1);
			  }
				
					
					
					
					rs = stmt.executeQuery( " SELECT  SUM(NVL(CAPITAL_AMOUNT,0)) "+
												          " FROM    "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
												          " WHERE   (PRO_INVOICE_NO,PRICING_NO) IN (SELECT INVOICE_NO,PRICING_NO "+
												          "                       FROM   "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+ 
												          "                       WHERE  APPLICATION_NO = '"+m_application_no_1+"' AND  "+
												          "                              ACTIVE_STATUS  = 'Y' "+
												          "                      ) AND "+
												               
												          " APPLICATION_NO = '"+m_application_no_1+"' ");
																	
					
					if(rs.next()){
			    m_rate = rs.getDouble(1);
			   }
					
			rs = stmt.executeQuery(" SELECT SUM(BALANCE_TO_BE_RECEIVED), "+
							                       "        SUM(BALANCE_TO_BE_RECEIVED-(ROUND((VAT_AMOUNT/TOTAL_AMOUNT)*BALANCE_TO_BE_RECEIVED))), "+
																		 "     	  SUM(ROUND((VAT_AMOUNT/TOTAL_AMOUNT)*BALANCE_TO_BE_RECEIVED)) "+
							                       " FROM   "+m_schema_name+".AF_CO_PRO_INVOICE "+
																		 " WHERE  FINANCE_NO='"+m_finance_no+"' AND ACTIVE_STATUS NOT IN ('CANCEL','C','DB_CAN')  AND TOTAL_AMOUNT<>0 ");
				
				
				if(rs.next()){
				m_due_net =  rs.getDouble(2);
				m_due_vat =  rs.getDouble(3);
				m_due_tot =  rs.getDouble(1);
				}
					
      out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Asset Financing System</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			
			out.println("<SCRIPT language=\"JavaScript\">"); 
      
			out.println("function load_values(){");
			//out.println("document.getElementById('h_term').value = document.Form1.h_term.value;");
			out.println(" cal_term.innerHTML        = format_noobject(parseFloat(unformat_noobject(document.Form1.h_term.value)));");
			out.println(" cal_vat.innerHTML         = format_noobject(parseFloat(unformat_noobject(document.Form1.h_vat.value)));");
			out.println(" cal_gtv.innerHTML         = format_noobject(parseFloat(unformat_noobject(document.Form1.h_gtv.value)));");
			out.println("	salse_pr_net.innerHTML  = format_noobject(parseFloat(unformat_noobject(document.Form1.hid_sn_val.value)));"); 
			out.println(" salse_pr_vat.innerHTML  = format_noobject(parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));"); 
			out.println("	salse_pr_tot.innerHTML  = format_noobject(parseFloat(unformat_noobject(document.Form1.hid_sg_val.value)));");
			out.println(" ter_vat.innerHTML       = format_noobject(parseFloat(unformat_noobject(document.Form1.T_V.value)));"); 
			out.println("	ter_tot.innerHTML       = format_noobject(parseFloat(unformat_noobject(document.Form1.T_S.value)));");
		  out.println("	t_net.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value)));"); 
			out.println(" t_vat.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.T_V.value))+parseFloat(unformat_noobject(document.Form1.DUE_VAT.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_VAT.value))+parseFloat(unformat_noobject(document.Form1.h_vat.value))  + parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));"); 
			out.println("	t_tot.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.DUE_TOT.value))+parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_TOT.value))    +parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value)));");
			
			out.println(" if(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value))<0){");
			out.println("	nt_net.innerHTML='('+format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)))+')';"); 
			out.println(" nt_vat.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.T_V.value))+parseFloat(unformat_noobject(document.Form1.DUE_VAT.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_VAT.value))+parseFloat(unformat_noobject(document.Form1.h_vat.value))  + parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));"); 
			out.println("	nt_tot.innerHTML='('+format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.DUE_TOT.value))+parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_TOT.value))    +parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)))+')';");
		  out.println(" } ");
		  out.println(" else{ ");
			out.println("	nt_net.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.TERM_AMOUNT.value))+parseFloat(unformat_noobject(document.Form1.DUE_NET.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_NET.value))+parseFloat(unformat_noobject(document.Form1.h_term.value)) + parseFloat(unformat_noobject(document.Form1.hid_sn_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)));"); 
			out.println(" nt_vat.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.T_V.value))+parseFloat(unformat_noobject(document.Form1.DUE_VAT.value))   +parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_VAT.value))+parseFloat(unformat_noobject(document.Form1.h_vat.value))  + parseFloat(unformat_noobject(document.Form1.hid_sv_val.value)));"); 
			out.println("	nt_tot.innerHTML=format_noobject(parseFloat(unformat_noobject(document.Form1.ODI_NET.value))+parseFloat(unformat_noobject(document.Form1.T_S.value))+parseFloat(unformat_noobject(document.Form1.DUE_TOT.value))+parseFloat(unformat_noobject(document.Form1.DUE_RENTALS_TOT.value))    +parseFloat(unformat_noobject(document.Form1.h_gtv.value))  + parseFloat(unformat_noobject(document.Form1.hid_sg_val.value))-parseFloat(unformat_noobject(document.Form1.Unallo_Rec.value)));");
		  out.println(" } ");
			
			out.println("	fur_rent.innerHTML=document.Form1.F_R.value;"); 
			out.println("	rent_paid.innerHTML=document.Form1.R_P.value;");
			out.println("	rent_arr.innerHTML=document.Form1.R_A.value;");
			out.println("	rent_tot.innerHTML=document.Form1.R_T.value;");
			out.println("	ter_gain.innerHTML=document.Form1.h_tpv.value;");			
			
			out.println("}");		
			
			out.println("</script>"); 
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_values()\">"); //,get_due_rent_sum(),get_term_vehicles()check_lease('"+m_finance_no+"','"+m_client_code_1+"','"+m_apply_date_1+"')\"
			out.println("<FORM NAME='Form1' method='post'>"); 
						
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
		
			  
			  out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
			  
				
				out.println("<tr class=tr_input>");
				out.println("<td ><b>Termination No</b></td>");
        out.println("<td >"+m_termination_no+"</b></td>");
				out.println("<td ><b>Termination Type</b></td>");
				out.println("<td>"+m_termination_type+"</TD>");
				out.println("</tr>");				
				
				out.println("<tr class=tr_input>");
				out.println("<td ><b>Termination Date</b></td>");
			  out.println("<td >"+m_apply_date_1+"</b></td>");
				out.println("<td ><b>Requested By</b></td>");
				out.println("<td >"+m_requested_by_1+"</b></td>");
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td ><b>Client Code</b></td>");
				out.println("<td >"+m_client_code_1+"</b></td>");
				out.println("<td ><b>Client Name</b></td>");
				out.println("<td >"+m_client_name_1+"</b></td>");
				out.println("</tr>");		
				
				out.println("<tr class=tr_input>");
				out.println("<td ><b>Finance No</b></td>");
				out.println("<td >"+m_finance_no+"</td>");
				out.println("<td ><b>Termination Count</b></td>");
				out.println("<td >"+nf2.format(m_term_count_1)+"</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td ><b>Transaction Type</b></td>");
				out.println("<td >"+m_trn_type+"</td>");
				out.println("<td ><b>Closure IRR</b></td>");
				out.println("<td >"+nf.format(m_closure_irr_1)+"</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td ><b>Termination Rate</b></td>");
				out.println("<td >"+nf2.format(m_rate_1)+"</td>");
   			out.println("<td ><b>Finance Rate</b></td>");
				out.println("<td >"+nf.format(m_lese_rate)+"</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td ><b>Termination Valid Date</b></td>");
				out.println("<td >"+m_ter_validity_date_1+"</td>");
				out.println("<td ><b>Due Amount</b></td>");
				out.println("<td >"+nf.format(m_due_tot)+"</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td ><b>Remark</b></td>");
				out.println("<td >"+m_remarks_1+"</td>");
				out.println("<td ><b>Termination Charge</b></td>");
				out.println("<td >"+nf.format(m_charges_1)+"</td>");
				out.println("</tr>");
				
				
				
				out.println("<tr class=tr_input>");
				out.println("<td ><b>VAT %</b></td>");
				out.println("<td >"+nf.format(m_vat_per_1)+"</td>");
				out.println("<td ><b>Normal Rentals Due up to Termination Date</b></td>");
				out.println("<td >"+nf.format(m_norm_rental)+"</td>");
				out.println("</tr>");
		       
				out.println("<tr class=tr_input>");
				out.println("<td ><b>Amount Finance</b></td>");
				out.println("<td >"+nf.format(m_rate)+"</td>");
				out.println("<td ><b>NIBSM</b></td>");
				out.println("<td >"+nf.format(m_nibsm)+"</td>");
				out.println("</tr>");
		    
				out.println("<tr class=tr_input>");
				out.println("<td><b>AMI</b></td>");
				out.println("<td >"+nf.format(m_ami)+"</td>");
				out.println("</td><input type=\"hidden\" name=\"AMI\">");
				out.println("<td><b>Unallocated Receipt</b></td>");
				out.println("<td >"+nf.format(m_unallo_rec)+"</td>");
  			out.println("</tr>");
		    
		    out.println("<tr class=tr_input>");
				out.println("<td><b>Total Capital Outstanding</b></td>");
				out.println("<td >"+nf.format(m_cap_out)+"</td>");
				out.println("<td><b>%</b></td>");
				out.println("<td >"+nf.format(m_cap_out/m_rate*100)+"</td>");
				out.println("</tr>");
		     
				out.println("<tr class=tr_input>");
				out.println("<td><b>ODI</b></td>");
				out.println("<td >"+nf.format(m_odi)+"</td>");
				out.println("<td><b>ODI Adjustment</b></td>");
				out.println("<td >"+nf.format(m_odi_adjustment_1)+"</td>");
				out.println("</tr>");
		    
				out.println("<tr class=tr_input>");
				out.println("<td><b>ODI Net</b></td>");
				out.println("<td >"+nf.format(m_odi_net_1)+"</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br><br>");
				
				//==============================================================================================================================================
				
				rs = stmt.executeQuery(" SELECT B.CHASSIS_NO,B.REG_NO,VAT_PERCENTAGE,VAT_ON_RENTAL,VAT_APP,B.INVOICE_NO, "+
					                       "        "+m_schema_name+".AF_CO_GET_MODEL_DESC(B.MODEL_CODE),B.APPLICATION_NO "+
																 " FROM   "+m_schema_name+".AF_CO_PRO_APP_PRICING A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
																 " WHERE  A.PRICING_NO     = B.PRICING_NO AND "+
																 "        A.APPLICATION_NO = B.APPLICATION_NO AND "+  
																 "        A.PRO_INVOICE_NO = B.INVOICE_NO AND "+
																 "				B.APPLICATION_NO IN (SELECT APPLICATION_NO "+
																 "                             FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																 "                             WHERE  FINANCE_NO='"+m_finance_no+"') AND "+
																 "        B.ACTIVE_STATUS='Y' ");
					
					                     																					
					out.println("<table class=table border='0' width='100%' >");
					
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td WIDTH=10%>Id No</td>");
					out.println("<td WIDTH=10%>Chassis No</td>");
					out.println("<td WIDTH=10%>Vehicle No</td>");
					out.println("<td WIDTH=10%>Asset Description</td>");
					out.println("<td WIDTH=10%>VAT</td>");
					out.println("<td colspan=6>Sales Price</td>");
					out.println("</tr>");
          int j=0;
					
					
			    while(rs.next()){
					m_Vehicle_no = rs.getString(2);
					m_Chassis_no = rs.getString(1);
					m_inv_no     = rs.getString(6);
				  
					
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
																	 " AND TERMINATION_NO='"+m_termination_no+"'"+ //added by ns on 01-09-2010 
																	 " AND PRO_INVOICE_NO='"+rs.getString(6)+"'");
										
						if(rs2.next()){
						m_sale_value= rs2.getString(1);
						m_sum_sal   = m_sum_sal+rs2.getDouble(1);
						
						}
						out.println("<tr class=tr_input >");
						out.println("<td width=10%>"+rs.getString(6)+"<input type=hidden name=\"INVOICE_NO_"+j+"\"  value=\""+rs.getString(6)+"\"></td>");
						out.println("<td width=10%>"+rs.getString(1)+"<input type=hidden name=\"CHASSIS_NO_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
						out.println("<td width=10%>"+rs.getString(2)+"<input type=hidden name=\"VEHICLE_NO_"+j+"\"  value=\""+rs.getString(2)+"\"></td>");
						out.println("<td width=10%>"+rs.getString(7)+"</td>");
						out.println("<td width=10%>"+rs.getString(3)+"<input type=hidden name=\"VAT_"+j+"\"  value=\""+rs.getString(3)+"\"><input type=hidden name=\"VATR_"+j+"\"  value=\""+rs.getString(4)+"\"></td>");
						if(rs.getString(1)==null){					 
							
							out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\""+m_sale_value+"\" maxlength=\"25\" class=\"txt_input2\" disabled></td>");//Modified by Dineth on 2008-10-31
						 
						if(count>0){
							out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" checked disabled></td>");
							}
							else{
							out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" disabled></td>");
							}
						}else{
						 
						 out.println("<td width=10% align=right><INPUT TYPE=\"text\" NAME=\"sele_val_"+j+"\" value=\""+m_sale_value+"\" maxlength=\"25\" class=\"txt_input2\" disabled ></td>");
							if(count>0){
							out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" checked disabled></td>");
							}
							else{
							out.println("<td width=10%><INPUT TYPE=\"checkbox\" NAME=\"ch_v_"+j+"\" onclick=ch_status(\""+j+"\") value=\"NO\" disabled></td>");
							}
						}
						j=j+1;
						out.println("</tr>");
						
					}
          					
          out.println("<input type=hidden name=hid_vcount value=\""+j+"\"></table>");			
				
				out.println("<br><br>");
				
				//========================================================================================================================================
							
				
				rs = stmt.executeQuery("SELECT NVL(SUM(NET_RENTAL_AMOUNT*(1+("+m_schema_name+".AF_CO_GET_VAT_ON_RENTAL_PRO(TRANSACTION_TYPE,TO_DATE('"+m_apply_date_1+"','DD-MM-YYYY'))/100))),0), "+
			                         "       NVL(SUM(NET_RENTAL_AMOUNT),0), "+
															 "       NVL(SUM(NET_RENTAL_AMOUNT*"+m_schema_name+".AF_CO_GET_VAT_ON_RENTAL_PRO(TRANSACTION_TYPE,TO_DATE('"+m_apply_date_1+"','DD-MM-YYYY'))/100),0) "+
														   "FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
														   "WHERE  A.APPLICATION_NO = B.APPLICATION_NO AND "+
															 "       INVOICE_NO IS NULL AND    "+
														   "       RENTAL_DATE < TO_DATE('"+m_apply_date_1+"','DD-MM-YYYY')  AND "+
														   "      (PRO_INVOICE_NO,a.APPLICATION_NO,PRICING_NO) IN (SELECT INVOICE_NO, APPLICATION_NO,PRICING_NO "+
														   "                                                      FROM   "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
														   "                                                      WHERE  APPLICATION_NO  = (SELECT APPLICATION_NO "+
														   "                                                                                FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
														   "                                                                                WHERE  FINANCE_NO    = '"+m_finance_no+"') AND "+
															 "                                                                                       ACTIVE_STATUS = 'Y' )");
																
				if(rs.next()){
				m_due_rent_net =  rs.getDouble(2);
				m_due_rent_vat =  rs.getDouble(3);
				m_due_rent_tot =  rs.getDouble(1);
				}
				
				out.println("<table class=table border='0' width='100%' >");
				out.println("<tr>");
				out.println("<td width=40%>&nbsp;</td>");
				out.println("<td width=20% align='right'><b>Net Amount</td>");
				out.println("<td width=20% align='right'><b>Vat Amount</td>");
				out.println("<td width=20% align='right'><b>Total Amount</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=40%><b>Due Amount</td>");
				out.println("<td width=20% align='right'>"+nf.format(m_due_net)+"<input type='hidden' name='DUE_NET' value=\""+m_due_net+"\"></td>");
				out.println("<td width=20% align='right'>"+nf.format(m_due_vat)+"<input type='hidden' name='DUE_VAT' value=\""+m_due_vat+"\"></td>");
				out.println("<td width=20% align='right'>"+nf.format(m_due_tot)+"<input type='hidden' name='DUE_TOT' value=\""+m_due_tot+"\"></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=40%><b>Rentals Due up to Termination Date</td>");
				out.println("<td width=20% align='right'>"+nf.format(m_due_rent_net)+"<input type='hidden' name='DUE_RENTALS_NET' value=\""+m_due_rent_net+"\"></td>");
				out.println("<td width=20% align='right'>"+nf.format(m_due_rent_vat)+"<input type='hidden' name='DUE_RENTALS_VAT' value=\""+m_due_rent_vat+"\"></td>");
				out.println("<td width=20% align='right'>"+nf.format(m_due_rent_tot)+"<input type='hidden' name='DUE_RENTALS_TOT' value=\""+m_due_rent_tot+"\"></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=40%><b>Termination Calculation Amount</td>");
				out.println("<td width=20% align='right' ><div id='cal_term'></div></td>");
				out.println("<td width=20% align='right'><div id='cal_vat'></div></td>");
				out.println("<td width=20% align='right'><div id='cal_gtv'></div></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=40%><b>Sales Price</td>");
				out.println("<td width=20% align='right'><div id='salse_pr_net'></div></td>");
				out.println("<td width=20% align='right'><div id='salse_pr_vat'></div></td>");
				out.println("<td width=20% align='right'><div id='salse_pr_tot'></div></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=40%><b>Termination Charges</td>");
				out.println("<td width=20% align='right'>"+nf.format(m_charges_1)+"<input type='hidden' name='TERM_AMOUNT' value=\""+m_charges_1+"\"></td>");
				out.println("<td width=20% align='right'><div id='ter_vat'></div></td>");
				out.println("<td width=20% align='right'><div id='ter_tot'></div></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=40%><b>ODI</td>");
				out.println("<td width=20% align='right'>"+nf.format(m_odi_net_1)+"<input type='hidden' name='ODI_NET' value=\""+m_odi_net_1+"\"></td>");
				out.println("<td width=20% align='right'>"+nf.format(0)+"</td>");
				out.println("<td width=20% align='right'>"+nf.format(m_odi_net_1)+"</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=40%><b>Total Amount</td>");
				out.println("<td width=20% align='right'><b><div id='t_net'></div></td>");
				out.println("<td width=20% align='right'><b><div id='t_vat'></div></td>");
				out.println("<td width=20% align='right'><b><div id='t_tot'></div></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=40%><b>Unallocated Receipts</td>");
				out.println("<td width=20% align='right'>("+nf.format(m_unallo_rec)+")<input type='hidden' name='Unallo_Rec' value=\""+m_unallo_rec+"\"></td>");
				out.println("<td width=20% align='right'>("+nf.format(0)+")</td>");
				out.println("<td width=20% align='right'>("+nf.format(m_unallo_rec)+")</td>");
				out.println("</tr>");
								
				out.println("<tr>");
				out.println("<td width=40%><b>Net Total Amount</td>");
				out.println("<td width=20% align='right'><b><div id='nt_net'></div></td>");
				out.println("<td width=20% align='right'><b><div id='nt_vat'></div></td>");
				out.println("<td width=20% align='right'><b><div id='nt_tot'></div></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=40%>&nbsp;</td>");
				out.println("<td width=20% align='right'>&nbsp;</td>");
				out.println("<td width=20% align='right'><b>&nbsp;</td>");
				out.println("<td width=20% align='right'><b>&nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=40%><b> No of Future Rentals</td>");
				out.println("<td width=20% align='right'><div id='fur_rent'></div></td>");
				out.println("<td width=20% align='right'><b><font color='green'>Termination Gain/Loss</td>");
				out.println("<td width=20% align='right'><b><font color='green'><div id='ter_gain'></div></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=40%><b>No of Rentals Paid</td>");
				out.println("<td width=20% align='right'><div id='rent_paid'></div></td>");
				out.println("<td width=20% align='right'><b>&nbsp;</td>");
				out.println("<td width=20% align='right'><b>&nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=40%><b>No of Rentals Arrears</td>");
				out.println("<td width=20% align='right'><div id='rent_arr'></div></td>");
				out.println("<td width=20% align='right'><b>&nbsp;</td>");
				out.println("<td width=20% align='right'><b>&nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width=40%><b>Total</td>");
				out.println("<td width=20% align='right'><div id='rent_tot'></div></td>");
				out.println("<td width=20% align='right'><b>&nbsp;</td>");
				out.println("<td width=20% align='right'><b>&nbsp;</td>");
				out.println("</tr>");
				
				out.println("</table>");
				
				
				//========================================================================================================================================
				out.println("<br><br>");
            if(j>0){					
						
					out.println("<table class=table border='0' width='100%' >");	
					/*
									rs = stmt.executeQuery (" SELECT PERIOD,PAYMENT_INTERVAL "+
									" FROM   "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
									" WHERE  (PRO_INVOICE_NO,  PRICING_NO )  IN (SELECT INVOICE_NO,PRICING_NO "+
									"                                           FROM   "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
									"                                           WHERE  INVOICE_NO     = '"+m_inv_no+"' AND "+
									"                                                  CHASSIS_NO     = '"+m_Chassis_no+"' AND "+
									"                                                  APPLICATION_NO = '"+m_application_no_1+"' ) ");
														
					
					if(rs.next()){
					m_period    = rs.getInt(1);
					m_prequency = rs.getInt(2);
					}
					
					   rs = stmt.executeQuery( " SELECT   TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'),A.INSTALLMENT_NO,SUM(A.NET_RENTAL_AMOUNT) NET_RENTAL_AMOUNT , A.RENTAL_DATE,A.PERCENTAGE "+
             " FROM    (SELECT     A.INSTALLMENT_NO,SUM(A.NET_RENTAL_AMOUNT) NET_RENTAL_AMOUNT, A.RENTAL_DATE,A.PERCENTAGE "+
             " FROM    "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
             " WHERE   (A.PRICING_NO,A.PRO_INVOICE_NO) IN (SELECT PRICING_NO ,INVOICE_NO "+
             "                                        FROM   "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
             "                                        WHERE  INVOICE_NO      = '"+m_inv_no+"' AND "+
             "                                               CHASSIS_NO     = '"+m_Chassis_no+"' AND "+
             "                                               APPLICATION_NO = '"+m_application_no_1+"' ) AND "+
             "        RENTAL_DATE>=TO_DATE('"+m_apply_date_1+"','DD-MM-YYYY') AND "+
             "        AMI_AMOUNT=0 AND AMI_AMOUNT IS NOT NULL AND "+
             "        INVOICE_NO IS NULL  AND "+
             "        A.APPLICATION_NO = B.APPLICATION_NO AND "+
             "        A.PRICING_NO     = B.PRICING_NO     AND "+
             "        A.PRO_INVOICE_NO = B.PRO_INVOICE_NO AND "+
             "        TO_NUMBER(A.INSTALLMENT_NO) <= '"+m_period+"'-(B.AMI+1)  AND "+
             "        A.APPLICATION_NO = '"+m_application_no_1+"' "+
						 " GROUP BY   A.INSTALLMENT_NO,A.RENTAL_DATE, A.PERCENTAGE "+
							
             " UNION ALL "+
							
             " SELECT  A.INSTALLMENT_NO,SUM(A.NET_RENTAL_AMOUNT) NET_RENTAL_AMOUNT, A.RENTAL_DATE,A.PERCENTAGE "+
             " FROM    "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
             " WHERE   (A.PRICING_NO,A.PRO_INVOICE_NO) IN (SELECT PRICING_NO ,INVOICE_NO "+
             "                                        FROM   "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
             "                                        WHERE  INVOICE_NO     = '"+m_inv_no+"' AND "+
             "                                               CHASSIS_NO     = '"+m_Chassis_no+"' AND "+
             "                                               APPLICATION_NO = '"+m_application_no_1+"' ) AND "+
             "        RENTAL_DATE>=TO_DATE('"+m_apply_date_1+"','DD-MM-YYYY') AND "+
             "        AMI_AMOUNT=0 AND AMI_AMOUNT IS NOT NULL AND "+
             "        INVOICE_NO IS NULL  AND "+
             "        A.APPLICATION_NO = B.APPLICATION_NO AND "+
             "        A.PRICING_NO     = B.PRICING_NO     AND "+
             "        A.PRO_INVOICE_NO = B.PRO_INVOICE_NO AND "+
             "        TO_NUMBER(A.INSTALLMENT_NO) = '"+m_period+"'  AND "+
             "        RESIDUAL_VALUE >0 AND "+
             "        A.APPLICATION_NO = '"+m_application_no_1+"' "+
						 " GROUP BY   A.INSTALLMENT_NO,A.RENTAL_DATE, A.PERCENTAGE "+	
						 "	) A "+
						 " GROUP BY   A.INSTALLMENT_NO,A.RENTAL_DATE, A.PERCENTAGE "+	
             " ORDER BY RENTAL_DATE ");				
					
																							
					
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
      
           int h = 0;
					  rent=0;
					   rpv =0;
					   term=0;
					    tpv =0;
					    double gtv =0;
					  double vat =0;	
							
					    		out.println("<tr class=tr_input  >");
									out.println("<td></td>");
                  out.println("<td ></td>");
                  out.println("<td align=right id=rent></td>");
                  out.println("<td align=right id=rpv ></td>");
									out.println("<td align=right id=term></td>");
									out.println("<td align=right id=tpv></td>");
									out.println("<td align=right id=gtv ></td>");
									out.println("</tr>");
							
							
              while(rs.next()){
							m_net_rental_amount = rs.getDouble(3);
							//---------------------------------------------------
							  rs1 = stmt1.executeQuery (" SELECT '"+m_net_rental_amount+"'*(1 /POWER((('"+m_lese_rate+"' / 100) / '"+m_prequency+"' + 1), '"+h+"')), "+ //PV
													                " '"+m_net_rental_amount+"'*(1 / POWER((('"+m_rate_1+"' / 100) / '"+m_prequency+"' + 1), '"+h+"')), "+ //TERMINATION_AMOUNT
													                " '"+m_net_rental_amount+"'*(1 /POWER((('"+m_rate_1+"' / 100) / '"+m_prequency+"' + 1), '"+h+"'))*(1+'"+m_vat_per_1+"'/100) "+   //TERMINATION_PV
													                " FROM DUAL ");	
																					
											 
							
							//---------------------------------------------------
							
							 while(rs1.next()){
								
                  //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  if(h%2==0){
									out.println("<tr class=tr_input >");
									}else{
									out.println("<tr class=tr_input1 >");
									}
									if(rs.getDouble(3)>=0){ 
									 out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+h+"\"  value=\""+rs.getString(1)+"\"></td>");
                  }else{
									 out.println("<td >NIBSM/AMI<input type=hidden name=\"INSTALL_DATE_"+h+"\"  value=\""+rs.getString(1)+"\"></td>");
									}
									out.println("<td align=right>"+nf1.format(rs.getDouble(5))+"<input type=hidden name=\"PERCENTAGE_"+h+"\"    value=\""+rs.getString(5)+"\"></td>");
                  out.println("<td align=right>"+nf2.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+h+"\"        value=\""+rs.getString(3)+"\"></td>");
                  out.println("<td align=right>"+nf2.format(rs1.getDouble(1))+"<input type=hidden name=\"PV_"+h+"\"            value=\""+rs1.getString(1)+"\"></td>");
                  out.println("<td align=right>"+nf2.format(rs1.getDouble(2))+"<input type=hidden name=\"TERMINATION_A_"+h+"\" value=\""+rs1.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf2.format((rs1.getDouble(2)-rs1.getDouble(1)))+"<input type=hidden name=\"TER_PV_AM_"+h+"\" value=\""+(rs1.getDouble(2)-rs1.getDouble(3))+"\"></td>");
	                out.println("<td align=right>"+nf2.format(rs1.getDouble(3))+"<input type=hidden name=\"TER_PV_AM_"+h+"\"     value=\""+rs1.getString(3)+"\"></td>");
									out.println("");
									out.println("</tr>");
									rent = rent  + rs.getDouble(3);
									rpv  = rpv   + rs1.getDouble(1);
									term = term  + rs1.getDouble(2);
									tpv  = tpv   + (rs1.getDouble(2)-rs1.getDouble(1));
									gtv  = gtv   + rs1.getDouble(3);
									vat  = vat   + rs1.getDouble(3)-rs1.getDouble(2);
									
									  h=h+1;
									
									}
									
			        }
                
			         		
					        out.println("<tr class=tr_input  >");
									out.println("<td><input type=hidden name=hid_count value="+h+">");
									out.println("</td>");
                  out.println("<td ></td>");
                  out.println("<td align=right>"+nf2.format(rent)+"<input type=hidden name=h_rent value="+nf2.format(rent)+"></td>");
									out.println("<td align=right>"+nf2.format(rpv) +"<input type=hidden name=h_rpv  value="+nf2.format(rpv)+"></td>");
									out.println("<td align=right>"+nf2.format(term)+"<input type=hidden name=h_term value="+nf2.format(term)+"></td>");
									out.println("<td align=right>"+nf2.format(tpv) +"<input type=hidden name=h_tpv  value="+nf2.format(tpv)+"></td>");
									out.println("<td align=right>"+nf2.format(gtv) +"<input type=hidden name=h_gtv  value="+nf2.format(gtv)+"><input type=hidden name=h_vat  value="+nf2.format(vat)+"></td>");
									out.println("</tr>");
									
									*/
									
								
									rs = stmt.executeQuery (" SELECT TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'), "+//1
																				  " A.PERCENTAGE, "+ //2
																				  " SUM(A.RENTAL_AMOUNT),"+ //3
																				  " SUM(A.RENTAL_PV) PV, "+ //4
																				  " SUM(A.TERM_AMOUNT), "+ //5
																				  //" SUM(A.TERM_AMOUNT-A.TERM_PV),'', "+ 
																				  //" SUM(A.TERM_PV)*-1 "+
																					" SUM(A.TERM_PV),'', "+
																				  " SUM(A.TERM_PV - A.TERM_AMOUNT) "+ 
																				  " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_DETAILS A  "+
																				  " WHERE  A.TERMINATION_NO    = '"+m_termination_no+"' "+
																				  " GROUP BY A.RENTAL_DATE,A.PERCENTAGE "+
																					" ORDER BY TO_DATE(TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') ");
									
									
																	
																								
					
					out.println("<tr class=tr_input>");
          
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
      
           int h = 0;
					  rent=0;
					   rpv =0;
					   term=0;
					    tpv =0;
					    double gtv =0;
					  double vat =0;	
							
					    		out.println("<tr class=tr_input  >");
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
									 out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+h+"\"  value=\""+rs.getString(1)+"\"></td>");
                  }else{
									 out.println("<td >NIBSM/AMI<input type=hidden name=\"INSTALL_DATE_"+h+"\"  value=\""+rs.getString(1)+"\"></td>");
									}
									out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+h+"\"    value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+h+"\"        value=\""+rs.getString(3)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+h+"\"            value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+h+"\" value=\""+rs.getString(5)+"\"></td>");
                  out.println("<td align=right>"+nf.format((rs.getDouble(5)-rs.getDouble(4)))+"<input type=hidden name=\"TER_PV_AM_"+h+"\" value=\""+(rs.getDouble(5)-rs.getDouble(6))+"\"></td>");
	                out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+h+"\"     value=\""+rs.getString(6)+"\"></td>");
									out.println("");
									out.println("</tr>");
									rent = rent  + rs.getDouble(3);
									rpv  = rpv   + rs.getDouble(4);
									term = term  + rs.getDouble(5);
									tpv  = tpv   + (rs.getDouble(5)-rs.getDouble(4));
									gtv  = gtv   + rs.getDouble(6);
									vat  = vat   + rs.getDouble(8);
									
									  h=h+1;
									
									if(rs.next()){
									  out.println("<tr class=tr_input1 >");
										out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+h+"\"  value=\""+rs.getString(1)+"\"></td>");
	                  out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+h+"\"    value=\""+rs.getString(2)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+h+"\"        value=\""+rs.getString(3)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+h+"\"            value=\""+rs.getString(4)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+h+"\" value=\""+rs.getString(5)+"\"></td>");
	                  out.println("<td align=right>"+nf.format((rs.getDouble(5)-rs.getDouble(4)))+"<input type=hidden name=\"TER_PV_AM_"+h+"\" value=\""+(rs.getDouble(5)-rs.getDouble(6))+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+h+"\"     value=\""+rs.getString(6)+"\"></td>");
										out.println("");
										out.println("</tr>");
										rent = rent  + rs.getDouble(3);
									  rpv  = rpv   + rs.getDouble(4);
									  term = term  + rs.getDouble(5);
									  tpv  = tpv   + (rs.getDouble(5)-rs.getDouble(4));
									  gtv  = gtv   + rs.getDouble(6);
									  vat  = vat   + rs.getDouble(8);
									  h=h+1;
									}
                	
									
			        }
                
			         		
					        out.println("<tr class=tr_input  >");
									out.println("<td><input type=hidden name=hid_count value="+h+">");
									out.println("</td>");
                  out.println("<td ></td>");
                  out.println("<td align=right>"+nf2.format(rent)+"<input type=hidden name=h_rent value="+nf2.format(rent)+"></td>");
									out.println("<td align=right>"+nf2.format(rpv) +"<input type=hidden name=h_rpv  value="+nf2.format(rpv)+"></td>");
									out.println("<td align=right>"+nf2.format(term)+"<input type=hidden name=h_term value="+nf2.format(term)+"></td>");
									out.println("<td align=right>"+nf2.format(tpv) +"<input type=hidden name=h_tpv  value="+nf2.format(tpv)+"></td>");
									out.println("<td align=right>"+nf2.format(gtv) +"<input type=hidden name=h_gtv  value="+nf2.format(gtv)+"><input type=hidden name=h_vat  value="+nf2.format(vat)+"></td>");
									out.println("</tr>");
									
									
					
					        rs = stmt.executeQuery (" SELECT '"+m_sum_sal+"' , "+
																					"        round('"+m_sum_sal+"'*('"+m_vat_per_1+"'/100)), "+
										                      "        '"+m_sum_sal+"'+ round('"+m_sum_sal+"'*('"+m_vat_per_1+"'/100)) "+
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
																	"        round('"+m_charges_1+"'*('"+m_vat_per_1+"'/100)),"+
																	"        '"+m_charges_1+"'+round('"+m_charges_1+"'*('"+m_vat_per_1+"'/100)) "+//,"+
																	//"        "+m_schema_name+".AF_CO_GET_CLOSURE_IRR(FINANCE_NO,'"+m_App_date+"') "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																	"	WHERE  FINANCE_NO= '"+m_finance_no+"'");
					
					
						
					
					if(rs.next()){
					out.println("<tr class=tr_input><input type=hidden name=F_R value=\""+nf1.format(rs.getDouble(1))+"\">");
					out.println("<input type=hidden name=R_P value=\""+nf1.format(rs.getDouble(2))+"\"><input type=hidden name=R_A value=\""+nf1.format(rs.getDouble(3))+"\"><input type=hidden name=R_T value=\""+nf1.format(rs.getDouble(2)+rs.getDouble(3)+rs.getDouble(1))+"\">");
					out.println("<input type=hidden name=T_V value=\""+nf2.format(rs.getDouble(4))+"\"><input type=hidden name=T_S value=\""+nf.format(rs.getDouble(5))+"\"><input type=hidden name=C_IRR value=\"\">");
					}else{
					out.println("<tr class=tr_input><input type=hidden name=F_R value=\"\">");
					out.println("<input type=hidden name=R_P value=\"\"><input type=hidden name=R_A value=\"\">");
					out.println("<input type=hidden name=T_V value=\"\"><input type=hidden name=T_S value=\"\"><input type=hidden name=C_IRR value=\"\">");
					
					}
         // out.println("<td align=right colspan=6><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("</table>");
						
						
						
						
						}
						else{
					rs = stmt.executeQuery ("SELECT TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'), PERCENTAGE, RENTAL_AMOUNT,  "+
					                        "       RENTAL_PV,TERM_AMOUNT, TERM_PV "+
																	"FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION_DETAILS "+
                                  "WHERE  TERMINATION_NO='"+m_termination_no+"' "+
																	"ORDER BY to_date(TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')"); 
																					
					out.println("<table class=table border='0' width='100%' >");
					
					//out.println("<tr class=tr_input>");
          //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          //out.println("<td colspan=6 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          //out.println("</tr>");
					
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='15%' >Installment Date</td>");
					out.println("<td  width='5%'  align=right>Percentage</td>");
          out.println("<td  width='20%' align=right>Rental</td>");
					out.println("<td  width='20%' align=right>P.V.</td>");
					out.println("<td  width='20%' align=right>Termination Amount</td>");
					out.println("<td  width='20%' align=right>P.V.</td>");
					out.println("</tr>");
      
           
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
									out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+k+"\"  value=\""+rs.getString(1)+"\"></td>");
                  out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+k+"\"    value=\""+rs.getString(2)+"\"></td>");
                  out.println("<td align=right>"+nf2.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+k+"\"        value=\""+rs.getString(3)+"\"></td>");
                  out.println("<td align=right>"+nf2.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+k+"\"            value=\""+rs.getString(4)+"\"></td>");
                  out.println("<td align=right>"+nf2.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+k+"\" value=\""+rs.getString(5)+"\"></td>");
                  out.println("<td align=right>"+nf2.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+k+"\"     value=\""+rs.getString(6)+"\"></td>");
									out.println("");
									out.println("</tr>");
									rent = rent  + rs.getDouble(3);
									rpv  = rpv   + rs.getDouble(4);
									term = term  + rs.getDouble(5);
									tpv  = tpv   + rs.getDouble(6);
									k=k+1;
									
									if(rs.next()){
									  out.println("<tr class=tr_input1 >");
										out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+k+"\"  value=\""+rs.getString(1)+"\"></td>");
	                  out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+k+"\"    value=\""+rs.getString(2)+"\"></td>");
	                  out.println("<td align=right>"+nf2.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+k+"\"        value=\""+rs.getString(3)+"\"></td>");
	                  out.println("<td align=right>"+nf2.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+k+"\"            value=\""+rs.getString(4)+"\"></td>");
	                  out.println("<td align=right>"+nf2.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+k+"\" value=\""+rs.getString(5)+"\"></td>");
	                  out.println("<td align=right>"+nf2.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+k+"\"     value=\""+rs.getString(6)+"\"></td>");
										out.println("");
										out.println("</tr>");
										rent = rent  + rs.getDouble(3);
									  rpv  = rpv   + rs.getDouble(4);
									  term = term  + rs.getDouble(5);
									  tpv  = tpv   + rs.getDouble(6);
									  k=k+1;
									}
                	
									
			        }
          
					        out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									out.println("<td><input type=hidden name=hid_count value="+k+"></td>");
                  out.println("<td ></td>");
                  out.println("<td align=right>"+nf2.format(rent)+"<input type=hidden name=h_rent value="+nf2.format(rent)+"></td>");
									out.println("<td align=right>"+nf2.format(rpv) +"<input type=hidden name=h_rpv  value="+nf2.format(rpv)+"></td>");
									out.println("<td align=right>"+nf2.format(term)+"<input type=hidden name=h_term value="+nf2.format(term)+"></td>");
									out.println("<td align=right>"+nf2.format(tpv) +"<input type=hidden name=h_tpv  value="+nf2.format(tpv)+"></td>");
									out.println("</tr>");
					
					
					//out.println("<tr class=tr_input>");
          //out.println("<td align=right colspan=6><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          //out.println("</tr>");
					
					
          out.println("</table>");
				}
							
				
				
				
			out.println("</table>");
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
		
      }
							
			 				
			
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
      //return null;
		}finally{
		  if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(rs1    !=null){try{rs.close();   }catch(Exception e){}}
			if(rs2    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(stmt1  !=null){try{stmt.close(); }catch(Exception e){}}
	    if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}
