import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*; 

// DEVELOP BY : INDITHA FOR MRFL   DATE:21-09-2006

public class LAKDL_AF_Income_Provision_sql_validations_normal extends javax.servlet.http.HttpServlet {
	
	/*
	Connection conn;
	Statement stmt,stmt1,stmt3;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
    public ResultSet rs,rs1,rs3;
	public String m_chksql;
	*/
	
	public  void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{ // synchronized
		
		Connection conn=null;
		Statement stmt=null,stmt1=null,stmt3=null;
		CallableStatement callstmt=null;
		java.text.NumberFormat nf=null,nf1=null;
		
		ResultSet rs=null,rs1=null,rs3=null;
		String m_chksql=null;
		
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_username = m_sn_methods.username;
			
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			//**************************************************************	
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
			res.setDateHeader("Expires", 0);
			
			ServletOutputStream out = res.getOutputStream();
			
			m_chksql=req.getParameter("chksql");
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt3=conn.createStatement();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("INCOME_SUSPENCE_REPORT")){
				
				String m_start_date=req.getParameter("start_date");
				String m_end_date=req.getParameter("end_date");
				
				rs= stmt.executeQuery (" SELECT TO_CHAR(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1)),'DD-MON-YYYY'),"+
					" TO_CHAR(LAST_DAY(TO_DATE('"+m_start_date+"','DD-MM-YYYY')),'DD-MON-YYYY') FROM DUAL ");
				
				String m_last_date="";
				String m_current_date="";
				
				if(rs.next()){
					m_last_date=rs.getString(1);
					m_current_date=rs.getString(2);
				}
				
				
				
				
				try{
					callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_PROVISION_RPT(:1,:2,:3); END;");
					callstmt.setString(1,m_start_date);
					callstmt.setString(2,m_end_date);
					callstmt.setString(3,m_username);
					callstmt.execute();
					callstmt.close();
					out.print("OK"); 
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
				
				
				
				/*String m_string="";	
				
				rs= stmt.executeQuery (" SELECT "+
					" A.APPLICATION_NO, "+//1
					" A.FINANCE_NO,"+//2
					" A.CLIENT_CODE,"+//3
					" "+m_schema_name+".AF_CO_GET_APP_NAME(A.APPLICATION_NO) CNAME, "+//4
					" "+m_schema_name+".AF_CO_GET_ASSET_DESC(A.APPLICATION_NO) ASSET_DETAIL, "+//5
					" "+m_schema_name+".AF_CO_GET_APP_FINANCE_AMT(A.APPLICATION_NO) FINANCE_AMT, "+//6
					" A.RENTAL_ARREAS_NO RENTAL_ARR, "+//7
					" A.RENTAL_ARREAS RENTAL_VAL, "+//8
					" A.CAP_OUTSTANDING CAP_OUTS, "+//9
					//" "+m_schema_name+".AF_CO_GET_APP_NIBSM(A.APPLICATION_NO) NIBSM, "+//10
					" "+m_schema_name+".AF_CO_GET_DEPOSITS(A.APPLICATION_NO) DEPOSITS , "+ //10
					" A.INT_SUSPENCE_AMT INT_SUSPENSE, "+//11
					" A.PROVISION_RATE*100 PROVION_RATE, "+//12
					//" "+m_schema_name+".AF_CO_GET_PROV_3M_INT_SUSP(A.APPLICATION_NO,'"+m_start_date+"') INTCOME_SUS_3MONTHS, "+//13
					//" "+m_schema_name+".AF_CO_GET_PROV_3M_INT_SUSP(A.APPLICATION_NO,'"+m_start_date+"') INTCOME_SUS_3MONTHS, "+//13
					" 0  INTCOME_SUS_3MONTHS,  "+//13
					" "+m_schema_name+".AF_CO_GET_PROV_INT_LAST_MONTH(A.APPLICATION_NO,TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1),'DD-MM-YYYY')) INCOME_SUS_LAST_MONTH, "+//14
					" "+m_schema_name+".AF_CO_GET_PROV_INT_LAST_MONTH(A.APPLICATION_NO,'"+m_start_date+"') INCOME_SUS_CURRENT_MONTH, "+//15
					" "+m_schema_name+".AF_CO_GET_PROVISION_LAST_MONTH(A.APPLICATION_NO,TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1),'DD-MM-YYYY')) PROVISION_PRE_MONTH, "+//16
					" NVL(VAT_SUSPENCE_AMT,0) "+//17
					" ,A.ACC_POST_STATUS "+//18
					" ,NVL(A.VAT_SUSPENCE_AMT,0) "+//19
					" ,NVL(A.ARREARS_CAPITAL,0) "+//20 // ADDED BY NUWAN DE SILVA
					" ,NVL(A.FUTURE_CAPITAL,0)  "+//21 // ADDED BY NUWAN DE SILVA
					" ,NVL("+m_schema_name+".AF_CO_GET_MONTHS_DIFF(A.APPLICATION_NO,'"+m_end_date+"'),0)  AGREEGATE_AGE "+//22
					" ,NVL(A.ARREARS_CAPITAL,0)+NVL(A.FUTURE_CAPITAL,0)+"+m_schema_name+".AF_CO_GET_PROV_3M_INT_SUSP(A.APPLICATION_NO,'"+m_start_date+"')-"+m_schema_name+".AF_CO_GET_DEPOSITS(A.APPLICATION_NO) "+//20 // ADDED BY NUWAN DE SILVA
					" ,"+m_schema_name+".AF_CO_GET_EMP_NAME("+m_schema_name+".AF_GET_COLL_OFFICER(FINANCE_NO)), "+			
					" nvl("+m_schema_name+".AF_CO_GET_ODI_BAL(FINANCE_NO,'"+m_end_date+"'),0)  "+//Added BY Sandun 12-06-2009
					" ,nvl("+m_schema_name+".AF_CO_GET_SEC_VAL(FINANCE_NO,A.APPLICATION_NO),0)  "+//Added BY ns
					//" ,NVL("+m_schema_name+".AF_CO_GET_APP_CHARG_ARR_AMT(A.APPLICATION_NO,,'"+m_end_date+"'),0 ) OTH_CHARGES "+ //added by ns 08-11-2009 //27
					" ,NVL(OTHER_CHARGES,0)"+
					" FROM "+m_schema_name+".AF_CO_PRO_PROVISION_DETAILS A "+
					" WHERE A.PROVISION_DATE=TO_DATE('"+m_start_date+"','DD-MM-YYYY') "+
					" ORDER BY A.RENTAL_ARREAS_NO,A.APPLICATION_NO ");
				
				
				m_string=m_string+"<table align='center' width='100%' class='table'>";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='1%' ></td>"; 
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV class=div_input>Finance No</DIV></td>";//1
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Client Name</DIV></td>";//2
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Type of the Asset</DIV></td>"; //3
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Collection Officer</DIV></td>"; //3
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Finance Amt</DIV></td>"; //4
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Rental in Arrears No</DIV></td>"; //5
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Aggregate age </DIV></td>"; //6
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Rental in Arrears Amt</DIV></td>"; //7
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Interest in Suspense "+m_current_date+"</DIV></td>"; //7
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Interest in Suspense "+m_last_date+"</DIV></td>"; //8
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Interest Suspended during the Month</DIV></td>"; //9
				// m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Income Susp. in first 3 months</DIV></td>"; //10
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Arrears Capital</DIV></td>"; //11
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Other Charges</DIV></td>"; // added by ns 08-11-2009
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Future Capital</DIV></td>"; //12
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>NIBSM/Deposits</DIV></td>"; //13
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Net Exposure</DIV></td>"; //14
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Valuation @ 80%</DIV></td>"; //15
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Net Exposure After Valuation</DIV></td>"; //16
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Provision Rate</DIV></td>"; //17
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Provision as at "+m_current_date+"</DIV></td>"; //18
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Provision as at "+m_last_date+"</DIV></td>"; //19
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Provision during the Month</DIV></td>"; //20
				m_string=m_string+"<td width='20%' ><DIV class=div_input>VAT susp. Amt</DIV></td>"; //21
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>ODI Amount</DIV></td>"); // SJ on 12-06-2009
				//m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Ac. Post Status</DIV></td>"; //22
				//out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Ac. Comments</DIV></td>"); //23
				m_string=m_string+"<td width='10%' ></td>"; 
				m_string=m_string+"<td width='*%'></td>";
				m_string=m_string+"</tr>";
				
				
				
				int chk_nums=0;
				int j=0;
				while(rs.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\">";
						j=0;
					}
					
					//double m_net_exposure=rs.getDouble(9)-rs.getDouble(10)-0+rs.getDouble(13);
					//double m_net_exposure=rs.getDouble(20)+rs.getDouble(21)-rs.getDouble(10)-0+rs.getDouble(13);
					//double m_net_exposure=rs.getDouble(20) + rs.getDouble(21)+ rs.getDouble(13) - rs.getDouble(10);
					
					//Other Charges Added by ns 08-11-2009 rs.getDouble(27)      ARR_CAP+FUTUR_CAP-SECURITIES-OTHER_CHARGES-VALUATION
					double m_net_exposure=rs.getDouble(20) + rs.getDouble(21)+ rs.getDouble(13) - rs.getDouble(10)-rs.getDouble(15) +  rs.getDouble(27) +  rs.getDouble(19);//reduce the valuation- rs.getDouble(26)
					
					//double m_net_exposure=rs.getDouble("RENTAL_VAL") + rs.getDouble("FUTURE_CAPITAL")+ rs.getDouble("OTHER_CHARGES")+ rs.getDouble("VAT_SUSPENCE_AMT") - rs.getDouble("INCOME_SUS_CURRENT_MONTH")  ;
					double m_net_exposure_after_val=rs.getDouble(20) + rs.getDouble(21)+ rs.getDouble(13) - rs.getDouble(10)-rs.getDouble(15) +  rs.getDouble(27) +  rs.getDouble(19)- rs.getDouble(26) ;
					double m_provision_amt=m_net_exposure*(rs.getDouble(12)/100);
					
					double m_rental_arrears=rs.getDouble(8);//rs.getDouble(14)+rs.getDouble(13)+rs.getDouble(20);
					
					m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='15%' ><INPUT TYPE='HIDDEN' class='txt_input' NAME='APPLICATION_CODE_"+chk_nums+"' VALUE=\""+rs.getString(1)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='FINNACE_NO_"+chk_nums+"' VALUE=\""+rs.getString(2)+"\">"+rs.getString(2)+"</td>"; //ok
					m_string=m_string+"<td width='15%' ><INPUT TYPE='HIDDEN' class='txt_input' NAME='CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs.getString(3)+"\">"+rs.getString(4)+"</td>"; //ok
					m_string=m_string+"<td width='15%' >"+rs.getString(5)+"</td>"; //ok
					m_string=m_string+"<td width='15%' >"+rs.getString(24)+"</td>"; //ok //23
					
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(6))+"</td>";//ok
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='NO_RENTALS_ARR_"+chk_nums+"' VALUE=\""+nf.format(rs.getDouble(7))+"\">"+nf.format(rs.getDouble(7))+"</td>"; //ok
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='NO_AGR_RENTALS_ARR_"+chk_nums+"' VALUE=\""+nf.format(rs.getDouble(7)+ rs.getDouble(22))+"\">"+nf.format(rs.getDouble(7)+ rs.getDouble(22))+"</td>"; //ok
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='AMT_RENTALS_ARR_"+chk_nums+"' VALUE=\""+nf.format(m_rental_arrears)+"\">"+nf.format(m_rental_arrears)+"</td>"; //ok
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(15))+"</td>"; //7
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(14))+"</td>"; //8
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(15)-rs.getDouble(14))+"</td>"; //9
					//m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(13))+"</td>"; //10
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(20))+"</td>"; //11
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(27))+"</td>"; //11 //Other Charges added by ns 08-11-2009
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(21))+"</td>"; //12
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(10))+"</td>"; //13
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(m_net_exposure)+"</td>";  //14
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(26))+"</td>"; //15
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(m_net_exposure_after_val)+"</td>"; //16
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='PROVISION_RATE_"+chk_nums+"' VALUE=\""+nf.format(rs.getDouble(12))+"\">"+nf1.format(rs.getDouble(12))+"%</td>"; //17
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='PROVISION_AMT_"+chk_nums+"' VALUE=\""+nf.format(m_provision_amt)+"\">"+nf.format(m_provision_amt)+"</td>"; //18
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(16))+"</td>"; //19
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(m_provision_amt-rs.getDouble(16))+"</td>"; //20
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(19))+"</td>"; //21
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(25))+"</td>"; //22
					m_string=m_string+"<td width='9%' class=div_input><INPUT TYPE='HIDDEN' NAME='CHECK_"+chk_nums+"' VALUE=\"Y\"><INPUT TYPE='TEXT' class='txt_input' NAME='COMMENT_"+chk_nums+"' VALUE=\"\"><INPUT TYPE='CHECKBOX' NAME='CHK_"+chk_nums+"' checked></td>"; 
					m_string=m_string+"<td width='*%'></td>";
					
					
					
					
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";*/
				
				//out.println(m_string);
			}else if(m_chksql.equals("INCOME_SUSPENCE_REPORT_EDIT")){
				
				String m_start_date=req.getParameter("start_date");
				String m_end_date=req.getParameter("end_date");
				
				rs= stmt.executeQuery (" SELECT TO_CHAR(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1)),'DD-MON-YYYY'),"+
					" TO_CHAR(LAST_DAY(TO_DATE('"+m_start_date+"','DD-MM-YYYY')),'DD-MON-YYYY') FROM DUAL ");
				
				String m_last_date="";
				String m_current_date="";
				
				if(rs.next()){
					m_last_date=rs.getString(1);
					m_current_date=rs.getString(2);
				}
				
				rs= stmt.executeQuery (" SELECT "+
					" A.APPLICATION_NO, "+//1
					" A.FINANCE_NO,"+//2
					" A.CLIENT_CODE,"+//3
					" "+m_schema_name+".AF_CO_GET_APP_NAME(A.APPLICATION_NO) CNAME, "+//4
					" "+m_schema_name+".AF_CO_GET_ASSET_DESC(A.APPLICATION_NO) ASSET_DETAIL, "+//5
					" "+m_schema_name+".AF_CO_GET_APP_FINANCE_AMT(A.APPLICATION_NO) FINANCE_AMT, "+//6
					" A.RENTAL_ARREAS_NO RENTAL_ARR, "+//7
					" A.RENTAL_ARREAS RENTAL_VAL, "+//8
					" A.CAP_OUTSTANDING CAP_OUTS, "+//9
					" "+m_schema_name+".AF_CO_GET_APP_NIBSM(A.APPLICATION_NO) NIBSM, "+//10
					" A.INT_SUSPENCE_AMT INT_SUSPENSE, "+//11
					" A.PROVISION_RATE*100 PROVION_RATE, "+//12
					//" "+m_schema_name+".AF_CO_GET_PROV_3M_INT_SUSP(A.APPLICATION_NO,'"+m_start_date+"') INTCOME_SUS_3MONTHS, "+//13
					" 0  INTCOME_SUS_3MONTHS,  "+
					" "+m_schema_name+".AF_CO_GET_PROV_INT_LAST_MONTH(A.APPLICATION_NO,TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1),'DD-MM-YYYY')) INCOME_SUS_LAST_MONTH, "+//14
					" "+m_schema_name+".AF_CO_GET_PROV_INT_LAST_MONTH(A.APPLICATION_NO,'"+m_start_date+"') INCOME_SUS_CURRENT_MONTH, "+//15
					" "+m_schema_name+".AF_CO_GET_PROVISION_LAST_MONTH(A.APPLICATION_NO,ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1)) PROVISION_PRE_MONTH, "+//16
					" NVL(A.PROVISION_COMMENTS,'-'),"+//17
					" A.ACC_POST_STATUS, "+//18
					" NVL(A.VAT_SUSPENCE_AMT,0) "+//19
					" ,NVL(A.ARREARS_CAPITAL,0) "+//20 // ADDED BY NUWAN DE SILVA
					" ,NVL(A.FUTURE_CAPITAL,0)  "+//21 // ADDED BY NUWAN DE SILVA
					" ,NVL("+m_schema_name+".AF_CO_GET_MONTHS_DIFF(A.APPLICATION_NO,'"+m_end_date+"'),0)  AGREEGATE_AGE "+//22
					" ,"+m_schema_name+".AF_CO_GET_EMP_NAME("+m_schema_name+".AF_GET_COLL_OFFICER(FINANCE_NO)), "+		//23	
					" nvl("+m_schema_name+".AF_CO_GET_ODI_BAL(FINANCE_NO,'"+m_end_date+"'),0)  "+//Added BY Sandun 12-06-2009 //24
					" ,nvl("+m_schema_name+".AF_CO_GET_SEC_VAL(FINANCE_NO,A.APPLICATION_NO),0)  "+//Added BY ns //25
					//" ,NVL("+m_schema_name+".AF_CO_GET_APP_CHARG_ARR_AMT(A.APPLICATION_NO,,'"+m_end_date+"'),0 ) OTH_CHARGES "+ //added by ns 08-11-2009 //27
					" ,NVL(OTHER_CHARGES,0)"+
					" FROM "+m_schema_name+".AF_CO_PRO_PROVISION_DETAILS A "+
					" WHERE A.PROVISION_DATE=TO_DATE('"+m_start_date+"','DD-MM-YYYY') "+
					//" AND A.APPLICATION_NO='AP20070423-0454'"+
					" ORDER BY A.RENTAL_ARREAS_NO,A.APPLICATION_NO ");
				
				
				String m_string="";	
				
				m_string=m_string+"<table align='center' width='100%' class='table'>";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='1%' ></td>"; 
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV class=div_input>Finance No</DIV></td>";//1
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Client Name</DIV></td>";//2
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Type of the Asset</DIV></td>"; //3
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Collection Officer</DIV></td>"; //3
				
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Finance Amt</DIV></td>"; //4
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Rental in Arrears No</DIV></td>"; //5
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Aggregate age </DIV></td>"; //
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Rental in Arrears Amt</DIV></td>"; //6
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Interest in Suspense "+m_current_date+"</DIV></td>"; //7
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Interest in Suspense "+m_last_date+"</DIV></td>"; //8
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Interest Suspended during the Month</DIV></td>"; //9
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Income Susp. in first 3 months</DIV></td>"; //10
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Arrears Capital</DIV></td>"; //11
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Other Charges</DIV></td>"; // added by ns 08-11-2009
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Future Capital</DIV></td>"; //12
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>NIBSM/Deposits</DIV></td>"; //13
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Net Exposure</DIV></td>"; //14
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Valuation @ 80%</DIV></td>"; //15
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Net Exposure After Valuation</DIV></td>"; //16
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Provision Rate</DIV></td>"; //17
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Provision as at "+m_current_date+"</DIV></td>"; //18
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Provision as at "+m_last_date+"</DIV></td>"; //19
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Provision during the Month</DIV></td>"; //20
				m_string=m_string+"<td width='20%' ><DIV class=div_input>VAT susp. Amt</DIV></td>"; //21
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>ODI Amount</DIV></td>"); //SJ on 12-06-2009
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Ac. Post Status</DIV></td>"; //22
				//out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Ac. Comments</DIV></td>"); //23
				m_string=m_string+"<td width='*%'></td>";
				m_string=m_string+"</tr>";
				
				/*m_string=m_string+"<table align='center' width='100%' class='table'>";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='1%' ></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input>Application No</DIV></td>";//1
				m_string=m_string+"<td width='15%' ><DIV class=div_input>Client Name</DIV></td>";//2
				m_string=m_string+"<td width='15%' ><DIV class=div_input>Type of the Asset</DIV></td>"; //3
				m_string=m_string+"<td width='15%' ><DIV class=div_input>Finance Amt</DIV></td>"; //4
				m_string=m_string+"<td width='15%' ><DIV class=div_input>Rental in Arrears No</DIV></td>"; //5
				m_string=m_string+"<td width='15%' ><DIV class=div_input>Rental in Arrears Amt</DIV></td>"; //6
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Capital Outstanding</DIV></td>"; //7
				m_string=m_string+"<td width='20%' ><DIV class=div_input>NIBSM/Deposits</DIV></td>"; //8
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Valuation</DIV></td>"; //9
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Income Susp. in first 3 months</DIV></td>"; //10
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Net Exposure</DIV></td>"; //11
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Interest in Suspense "+m_last_date+"</DIV></td>"; //12
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Interest in Suspense "+m_current_date+"</DIV></td>"; //13
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Interest Suspended during the Month</DIV></td>"; //14
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Provision Rate</DIV></td>"; //15
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Provision as at "+m_last_date+"</DIV></td>"; //16
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Provision as at "+m_current_date+"</DIV></td>"; //17
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Provision during the Month</DIV></td>"; //18
				m_string=m_string+"<td width='20%' ><DIV class=div_input>VAT susp. Amt</DIV></td>"; //19
				m_string=m_string+"<td width='10%' ></td>"; 
				m_string=m_string+"<td width='*%'></td>";
				m_string=m_string+"</tr>";
				*/
				
				
				int chk_nums=0;
				int j=0;
				while(rs.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\">";
						j=0;
					}
					//double m_net_exposure=rs.getDouble(9)-rs.getDouble(10)-0+rs.getDouble(13);
					//double m_net_exposure=rs.getDouble(20)+rs.getDouble(21)-rs.getDouble(10)-0+rs.getDouble(13)- rs.getDouble(25) + rs.getDouble(26) ;//reduce the valuation- rs.getDouble(25);
					
					
					
					double m_net_exposure=rs.getDouble(20) + rs.getDouble(21)+ rs.getDouble(13) - rs.getDouble(10) +  rs.getDouble(26) +  rs.getDouble(19);//reduce the valuation- rs.getDouble(26)
					double m_net_exposure_after_val=rs.getDouble(20) + rs.getDouble(21)+ rs.getDouble(13) - rs.getDouble(10) +  rs.getDouble(26) +  rs.getDouble(19)- rs.getDouble(25) ;
					
					
					//double m_provision_amt=m_net_exposure*rs.getDouble(12);
					double m_provision_amt=m_net_exposure*(rs.getDouble(12)/100);
					
					
					
					double m_rental_arrears=rs.getDouble(14)+rs.getDouble(13)+rs.getDouble(20);
					
					m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='15%' ><INPUT TYPE='HIDDEN' class='txt_input' NAME='APPLICATION_CODE_"+chk_nums+"' VALUE=\""+rs.getString(1)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='FINNACE_NO_"+chk_nums+"' VALUE=\""+rs.getString(2)+"\">"+rs.getString(2)+"</td>"; //ok
					m_string=m_string+"<td width='15%' ><INPUT TYPE='HIDDEN' class='txt_input' NAME='CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs.getString(3)+"\">"+rs.getString(4)+"</td>"; //ok
					m_string=m_string+"<td width='15%' >"+rs.getString(5)+"</td>"; //ok
					m_string=m_string+"<td width='15%' >"+rs.getString(23)+"</td>"; //ok
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(6))+"</td>";//ok
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='NO_RENTALS_ARR_"+chk_nums+"' VALUE=\""+rs.getString(7)+"\">"+nf.format(rs.getDouble(7))+"</td>"; //ok
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='NO_AGR_RENTALS_ARR_"+chk_nums+"' VALUE=\""+nf.format(rs.getDouble(7)+ rs.getDouble(22))+"\">"+nf.format(rs.getDouble(7)+ rs.getDouble(22))+"</td>"; //ok //agreegate age
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='AMT_RENTALS_ARR_"+chk_nums+"' VALUE=\""+nf.format(m_rental_arrears)+"\">"+nf.format(m_rental_arrears)+"</td>"; //ok
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(15))+"</td>"; //7
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(14))+"</td>"; //8
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(15))+"</td>"; //9
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(13))+"</td>"; //10
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(20))+"</td>"; //11
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(26))+"</td>"; //11 Other Charges added by ns 08-11-2009
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(21))+"</td>"; //12
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(10))+"</td>"; //13
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(m_net_exposure)+"</td>";  //14
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(25))+"</td>"; //15
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(0)+"</td>"; //16
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='PROVISION_RATE_"+chk_nums+"' VALUE=\""+nf.format(rs.getDouble(12))+"\">"+nf1.format(rs.getDouble(12))+"%</td>"; //17
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='PROVISION_AMT_"+chk_nums+"' VALUE=\""+nf.format(m_provision_amt)+"\">"+nf.format(m_provision_amt)+"</td>"; //18
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(16))+"</td>"; //19
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(m_provision_amt-rs.getDouble(16))+"</td>"; //20
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(19))+"</td>"; //21
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(24))+"</td>"; //22 SJ on 12-06-2009
					if(rs.getString(18).equals("Y")){
						m_string=m_string+"<td width='9%' class=div_input><INPUT TYPE='HIDDEN' NAME='CHECK_"+chk_nums+"' VALUE=\"N\"><INPUT TYPE='TEXT' class='txt_input' NAME='COMMENT_"+chk_nums+"' VALUE=\""+rs.getString(17)+"\" disabled><INPUT TYPE='CHECKBOX' NAME='CHK_"+chk_nums+"' checked disabled></td>"; //22
					}
					else{
						m_string=m_string+"<td width='9%' class=div_input><INPUT TYPE='HIDDEN' NAME='CHECK_"+chk_nums+"' VALUE=\"Y\"><INPUT TYPE='TEXT' class='txt_input' NAME='COMMENT_"+chk_nums+"' VALUE=\""+rs.getString(17)+"\"><INPUT TYPE='CHECKBOX' NAME='CHK_"+chk_nums+"'></td>";  //22
					}
					
					//comment by nuwan de silva on 16-09-2008
					/*
					m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='15%' ><INPUT TYPE='HIDDEN' class='txt_input' NAME='APPLICATION_CODE_"+chk_nums+"' VALUE=\""+rs.getString(1)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='FINNACE_NO_"+chk_nums+"' VALUE=\""+rs.getString(2)+"\">"+rs.getString(2)+"</td>"; 
					m_string=m_string+"<td width='15%' ><INPUT TYPE='HIDDEN' class='txt_input' NAME='CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs.getString(3)+"\">"+rs.getString(4)+"</td>"; 
					m_string=m_string+"<td width='15%' >"+rs.getString(5)+"</td>"; 
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(6))+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='NO_RENTALS_ARR_"+chk_nums+"' VALUE=\""+rs.getString(7)+"\">"+rs.getString(7)+"</td>"; 
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='AMT_RENTALS_ARR_"+chk_nums+"' VALUE=\""+nf.format(rs.getDouble(8))+"\">"+nf.format(rs.getDouble(8))+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='CAPITAL_OUTSTANDING_"+chk_nums+"' VALUE=\""+nf.format(rs.getDouble(9))+"\">"+nf.format(rs.getDouble(9))+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(10))+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(0)+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(13))+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(m_net_exposure)+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(14))+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(15))+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='INCOME_SUS_AMT_"+chk_nums+"' VALUE=\""+nf.format(rs.getDouble(15))+"\">"+nf.format(rs.getDouble(15))+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='PROVISION_RATE_"+chk_nums+"' VALUE=\""+nf.format(rs.getDouble(12))+"\">"+nf.format(rs.getDouble(12))+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(16))+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='PROVISION_AMT_"+chk_nums+"' VALUE=\""+nf.format(m_provision_amt)+"\">"+nf.format(m_provision_amt)+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(m_provision_amt-rs.getDouble(16))+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(19))+"</td>";
					
					if(rs.getString(18).equals("Y")){
						m_string=m_string+"<td width='9%' class=div_input><INPUT TYPE='HIDDEN' NAME='CHECK_"+chk_nums+"' VALUE=\"N\"><INPUT TYPE='TEXT' class='txt_input' NAME='COMMENT_"+chk_nums+"' VALUE=\""+rs.getString(17)+"\" disabled><INPUT TYPE='CHECKBOX' NAME='CHK_"+chk_nums+"' checked disabled></td>"; 
					}
					else{
						m_string=m_string+"<td width='9%' class=div_input><INPUT TYPE='HIDDEN' NAME='CHECK_"+chk_nums+"' VALUE=\"Y\"><INPUT TYPE='TEXT' class='txt_input' NAME='COMMENT_"+chk_nums+"' VALUE=\""+rs.getString(17)+"\"><INPUT TYPE='CHECKBOX' NAME='CHK_"+chk_nums+"'></td>"; 
					}
					*/
					
					m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				
				out.println(m_string);
				
			}
			else if(m_chksql.equals("INCOME_SUSPENCE_REPORT_RPT")){
				
				String m_start_date=req.getParameter("start_date");
				String m_end_date=req.getParameter("end_date");
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Provision for Bad and Doubtful Debts and Income Suspension</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				
				rs= stmt.executeQuery (" SELECT TO_CHAR(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1)),'DD-MON-YYYY'),"+
					" TO_CHAR(LAST_DAY(TO_DATE('"+m_start_date+"','DD-MM-YYYY')),'DD-MON-YYYY') FROM DUAL ");
				
				String m_last_date="";
				String m_current_date="";
				
				if(rs.next()){
					m_last_date=rs.getString(1);
					m_current_date=rs.getString(2);
				}
				
				rs= stmt.executeQuery (
					//out.println(
					" SELECT "+
					" A.APPLICATION_NO, "+//1
					" A.FINANCE_NO,"+//2
					" A.CLIENT_CODE,"+//3
					" "+m_schema_name+".AF_CO_GET_APP_NAME(A.APPLICATION_NO) CNAME, "+//4
					" "+m_schema_name+".AF_CO_GET_ASSET_DESC(A.APPLICATION_NO) ASSET_DETAIL, "+//5
					" "+m_schema_name+".AF_CO_GET_APP_FINANCE_AMT(A.APPLICATION_NO) FINANCE_AMT, "+//6
					" A.RENTAL_ARREAS_NO RENTAL_ARR, "+//7
					" A.RENTAL_ARREAS RENTAL_VAL, "+//8
					" A.CAP_OUTSTANDING CAP_OUTS, "+//9
					" "+m_schema_name+".AF_CO_GET_APP_NIBSM(A.APPLICATION_NO) NIBSM, "+//10
					" A.INT_SUSPENCE_AMT INT_SUSPENSE, "+//11
					" A.PROVISION_RATE*100 PROVION_RATE, "+//12
					//" "+m_schema_name+".AF_CO_GET_PROV_3M_INT_SUSP(A.APPLICATION_NO,'"+m_start_date+"') INTCOME_SUS_3MONTHS, "+//13
					//" "+m_schema_name+".AF_CO_GET_PROV_3M_INT_SUSP(A.APPLICATION_NO,'"+m_start_date+"') INTCOME_SUS_3MONTHS, "+//13
					" 0  INTCOME_SUS_3MONTHS,  "+
					" "+m_schema_name+".AF_CO_GET_PROV_INT_LAST_MONTH(A.APPLICATION_NO,TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1),'DD-MM-YYYY')) INCOME_SUS_LAST_MONTH, "+//14
					" "+m_schema_name+".AF_CO_GET_PROV_INT_LAST_MONTH(A.APPLICATION_NO,'"+m_start_date+"') INCOME_SUS_CURRENT_MONTH, "+//15
					" "+m_schema_name+".AF_CO_GET_PROVISION_LAST_MONTH(A.APPLICATION_NO,TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1),'DD-MM-YYYY')) PROVISION_PRE_MONTH, "+//16
					" NVL(A.PROVISION_COMMENTS,'-'),"+//17
					" A.ACC_POST_STATUS, "+//18
					" NVL(A.VAT_SUSPENCE_AMT,0) VAT_SUSPENCE_AMT , "+//19
					" NVL(A.ARREARS_CAPITAL,0) ARREARS_CAPITAL ,"+//20 // ADDED BY NUWAN DE SILVA
					" NVL(A.FUTURE_CAPITAL,0)  FUTURE_CAPITAL "+//21 // ADDED BY NUWAN DE SILVA
					" ,NVL("+m_schema_name+".AF_CO_GET_MONTHS_DIFF(A.APPLICATION_NO,'"+m_end_date+"'),0)  AGREEGATE_AGE "+//22
					//" ,NVL("+m_schema_name+".AF_CO_GET_MONTHS_DIFF(A.APPLICATION_NO,'30-05-2008'),0)  AGREEGATE_AGE "+//22
					//",0"+
					//" ,"+m_schema_name+".AF_CO_GET_EMP_NAME("+m_schema_name+".AF_GET_COLL_OFFICER(FINANCE_NO)), "+	
					//" ,"+m_schema_name+".AF_CO_GET_NEW_APP_STATUS(A.APPLICATION_NO), "+ // commented by udara 01-04-2016
					" ,'-', "+ // added by udara 01-04-2016
					
					" nvl("+m_schema_name+".AF_CO_GET_ODI_BAL(FINANCE_NO,'"+m_end_date+"'),0)  "+//Added BY Sandun 12-06-2009
					" ,nvl("+m_schema_name+".AF_CO_GET_SEC_VAL(FINANCE_NO,A.APPLICATION_NO),0)   VALUATION "+//Added BY ns
					//" ,NVL("+m_schema_name+".AF_CO_GET_APP_CHARG_ARR_AMT(X.APPLICATION_NO,,'"+m_end_date+"'),0 ) OTH_CHARGES "+ //added by ns 08-11-2009 //26
					" ,NVL(OTHER_CHARGES,0) OTHER_CHARGES "+
					" FROM "+m_schema_name+".AF_CO_PRO_PROVISION_DETAILS A "+
					" WHERE A.PROVISION_DATE=TO_DATE('"+m_start_date+"','DD-MM-YYYY') "+
					//" AND A.APPLICATION_NO='AP20070423-0454'"+
					
					//" AND  (NVL(A.RENTAL_ARREAS_NO,0) + NVL("+m_schema_name+".AF_CO_GET_MONTHS_DIFF(A.APPLICATION_NO,'"+m_end_date+"'),0)) < 6 "+ // added by udara on 01-10-2013
					
					" ORDER BY A.RENTAL_ARREAS_NO,A.APPLICATION_NO ");
				
				String m_string="";	
				
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
				out.println("<BR>");  
				out.println("<p><center><b>Provision for Bad and Doubtful Debts and Income Suspension as at "+m_current_date+"</b><center></p>"); 
				out.println("<BR>"); 
				
				//COMMENT BY NUWAN DE SILVA ON 21-08-2008 ------------------------------------------------------------------
				/*
				out.println("<table align='center' width='100%' class='table' border='1' cellpadding='1' cellspacing='0'>");
				out.println("<tr >");
				out.println("<td width='1%' ></td>"); 
				out.println("<td width='15%' style='text-align:center'><DIV class=div_input>Application No</DIV></td>");//1
				out.println("<td width='15%' style='text-align:center'><DIV class=div_input>Finance No</DIV></td>");//1
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Client Name</DIV></td>");//2
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Type of the Asset</DIV></td>"); //3
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Finance Amt</DIV></td>"); //4
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Rental in Arrears No</DIV></td>"); //5
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Rental in Arrears Amt</DIV></td>"); //6
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Capital Outstanding</DIV></td>"); //7
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>NIBSM/Deposits</DIV></td>"); //8
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Valuation</DIV></td>"); //9
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Income Susp. in first 3 months</DIV></td>"); //10
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Net Exposure</DIV></td>"); //11
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Interest in Suspense "+m_last_date+"</DIV></td>"); //12
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Interest in Suspense "+m_current_date+"</DIV></td>"); //13
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Interest Suspended during the Month</DIV></td>"); //14
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision Rate</DIV></td>"); //15
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision as at "+m_last_date+"</DIV></td>"); //16
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision as at "+m_current_date+"</DIV></td>"); //17
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision during the Month</DIV></td>"); //18
				out.println("<td width='20%' ><DIV class=div_input>VAT susp. Amt</DIV></td>"); //19
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Ac. Post Status</DIV></td>"); //19
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Ac. Comments</DIV></td>"); //20
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				int chk_nums=0;
				int j=0;
				while(rs.next()){
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\">");
						j=0;
					}
					double m_net_exposure=rs.getDouble(9)-rs.getDouble(10)-0+rs.getDouble(13);
					double m_provision_amt=m_net_exposure*rs.getDouble(12);
					
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(1)+"</td>");
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(4)+"</td>");
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(5)+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(6))+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+rs.getString(7)+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(8))+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(9))+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(10))+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(0)+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(13))+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_net_exposure)+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(14))+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(15))+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(15))+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(12))+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(16))+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_provision_amt)+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_provision_amt-rs.getDouble(16))+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(19))+"</td>");
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(18)+"</td>");
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(17)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
				}
				out.println("</table>");
				
				*/
				
				//END COMMENT BY NUWAN DE SILVA ON 21-08-2008 ------------------------------------------------------------------
				
				//ADDED BY NUWAN DE SILVA ON 21-08-2008 ------------------------------------------------------------------
				out.println("<table align='center' width='100%' class='table' border='1' cellpadding='1' cellspacing='0'>");
				out.println("<tr >");
				out.println("<td width='1%' ></td>"); 
				out.println("<td width='15%' style='text-align:center'><DIV class=div_input>Finance No</DIV></td>");//1
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Client Name</DIV></td>");//2
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Type of the Asset</DIV></td>"); //3
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Collection Officer(New Status)</DIV></td>"); //3
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Finance Amt</DIV></td>"); //4
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Rental in Arrears No</DIV></td>"); //5
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Aggregate age</DIV></td>"); //5
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Rental in Arrears Amt</DIV></td>"); //6
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Interest in Suspense "+m_current_date+"</DIV></td>"); //7
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Interest in Suspense "+m_last_date+"</DIV></td>"); //8
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Interest Suspended during the Month</DIV></td>"); //9
				//out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Income Susp. in first 3 months</DIV></td>"); //10
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Arrears Capital</DIV></td>"); //11
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Other Charges</DIV></td>"); //27 added by nuwan de silva on 14-11-2009
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Future Capital</DIV></td>"); //12
				//out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Capital Outstanding</DIV></td>"); //7
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>NIBSM/Deposits</DIV></td>"); //13
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Net Exposure</DIV></td>"); //14
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Valuation @ 80%</DIV></td>"); //15
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Net Exposure After Valuation</DIV></td>"); //16
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision Rate</DIV></td>"); //17
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision as at "+m_current_date+"</DIV></td>"); //18
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision as at "+m_last_date+"</DIV></td>"); //19
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision during the Month</DIV></td>"); //20
				out.println("<td width='20%' ><DIV class=div_input>VAT susp. Amt</DIV></td>"); //21
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Ac. Post Status</DIV></td>"); //22
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Ac. Comments</DIV></td>"); //23
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>ODI Amount</DIV></td>"); //24 SJ on 12-06-2009
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				int chk_nums=0;
				int j=0;
				while(rs.next()){
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\">");
						j=0;
					}
					
					///Rental in Arrears Amt+Other Charges+Future Capital-Interest in Suspense 31-DEC-2013
					double m_net_exposure=rs.getDouble("RENTAL_VAL") + rs.getDouble("FUTURE_CAPITAL")+ rs.getDouble("OTHER_CHARGES")+ rs.getDouble("VAT_SUSPENCE_AMT") - rs.getDouble("INCOME_SUS_CURRENT_MONTH")  ;
					double m_net_exposure_after_val=rs.getDouble("RENTAL_VAL") + rs.getDouble("FUTURE_CAPITAL")+ rs.getDouble("OTHER_CHARGES")+ rs.getDouble("VAT_SUSPENCE_AMT") - rs.getDouble("INCOME_SUS_CURRENT_MONTH") - rs.getDouble("VALUATION") ;
					
					if (m_net_exposure_after_val < 0) {
						m_net_exposure_after_val = 0;
						
					}
					//Added Other charges for net Exposure rs.getDouble(26) Nuwan De Silva on 08-11-2009
					
					//THIS FORMULA COMMENT BY NS ON 08-01-2013
					//double m_net_exposure=rs.getDouble(20) + rs.getDouble(21)+ rs.getDouble(13) - rs.getDouble(10) - rs.getDouble(25) + rs.getDouble(26) ;//reduce the valuation- rs.getDouble(25);
					
					
					
					
					//double m_net_exposure=rs.getDouble(9)-rs.getDouble(10)-0+rs.getDouble(13);
					//double m_provision_amt=m_net_exposure*rs.getDouble(12);
					
					//comment by ns on 23-09-2014 for calculating provision only after valudation
					
					//double m_provision_amt=m_net_exposure*(rs.getDouble(12)/100);
					double m_provision_amt=m_net_exposure_after_val*(rs.getDouble(12)/100);
					
					
					
					double m_rental_arrears=rs.getDouble(8);//rs.getDouble(14)+rs.getDouble(13)+rs.getDouble(20);
					//out.println("rs.getDouble(7)"+rs.getDouble(7));
					//out.println("rs.getDouble(22)"+rs.getString(22));
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='15%' style='text-align:center'>"+rs.getString(1)+"</td>");
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(2)+"</td>"); //1
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(4)+"</td>"); //2
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(5)+"</td>"); //3
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(23)+"</td>"); //3
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(6))+"</td>"); //4
					
					//out.println("<td width='15%' style='text-align:right'>"+rs.getString(7)+"</td>"); //5
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(7))+"</td>"); //5
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(7)+ rs.getDouble(22))+"</td>"); //5 //agregate age
					//out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(8))+"</td>"); //6
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_rental_arrears)+"</td>"); //6 
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(15))+"</td>"); //7
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(14))+"</td>"); //8
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(15)-rs.getDouble(14))+"</td>"); //9
					//temp comment by ns
					//out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(13))+"</td>"); //10
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(20))+"</td>"); //11
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(26))+"</td>"); //11 Other charges Added by Nuwan De Silva 08-11-2009
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(21))+"</td>"); //12
					//out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(9))+"</td>"); //7
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(10))+"</td>"); //13
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_net_exposure)+"</td>"); //14
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(25))+"</td>"); //15
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_net_exposure_after_val)+"</td>"); //16
					out.println("<td width='15%' style='text-align:right'>"+nf1.format(rs.getDouble(12))+"%</td>"); //17
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_provision_amt)+"</td>"); //18
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(16))+"</td>"); //19
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_provision_amt-rs.getDouble(16))+"</td>"); //20
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(19))+"</td>"); //21
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(18)+"</td>"); //22
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(17)+"</td>"); //23
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(24))+"</td>"); //24 SJ on 12-06-2009
					out.println("</tr>");
				}
				out.println("</table>");
				//END ADDED BY NUWAN DE SILVA ON 21-08-2008 ------------------------------------------------------------------
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
			}
			
			
			// =========================== added by udara on 11-10-2013 =========================================================
			
			else if(m_chksql.equals("INCOME_SUSPENCE_REPORT_RPT_SUGGEST")){
				
				String m_start_date=req.getParameter("start_date");
				String m_end_date=req.getParameter("end_date");
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Provision for Bad and Doubtful Debts and Income Suspension</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function change(obj,row) {"); 
				out.println("   if(obj.checked==false){"); 
				out.println("    obj.value=\"0\";"); 
				out.println("    ck=1");
				out.println("   }");
				out.println("   if(obj.checked==true){"); 
				out.println("    obj.value=\"1\";"); 
				out.println("    ck=0");
				out.println("   }");
				//out.println("   alert(obj.name + '   ' + obj.value); ");
				out.println("}"); 
				
				
				out.println("function change_select_all(obj,count) {"); 
				//out.println("   alert(count); ");
				out.println("   if(obj.checked==false){"); 	
				//out.println("      alert('true'); ");
				out.println("      for(i=0; i<count; i++){ ");
				out.println("        document.getElementById('TXT_CHK_SELECT_'+i).checked = false; ");
				out.println("        document.getElementById('TXT_CHK_SELECT_'+i).value = '0'; ");
				out.println("      }");	
				out.println("   }");
				out.println("   else if(obj.checked==true){"); 
				//out.println("      alert('false'); ");
				out.println("      for(i=0; i<count; i++){ ");
				out.println("        document.getElementById('TXT_CHK_SELECT_'+i).checked = true; ");
				out.println("        document.getElementById('TXT_CHK_SELECT_'+i).value = '1'; ");
				out.println("      }");
				out.println("   }");
				out.println("}"); 	
				
				out.println("function window_onload(){ "); 
				
				out.println("} "); 
				
				
				out.println("function save_window(){ "); 
				out.println("	document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_display_Income_provision_suggest_save';");  
				out.println("	document.Form1.submit();	"); 
				out.println("} "); 
				
				
				out.println("</SCRIPT>");
				
				rs= stmt.executeQuery (" SELECT TO_CHAR(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1)),'DD-MON-YYYY'),"+
					" TO_CHAR(LAST_DAY(TO_DATE('"+m_start_date+"','DD-MM-YYYY')),'DD-MON-YYYY') FROM DUAL ");
				
				String m_last_date="";
				String m_current_date="";
				
				if(rs.next()){
					m_last_date=rs.getString(1);
					m_current_date=rs.getString(2);
				}
				
				
				rs= stmt.executeQuery (
					" SELECT "+
					" A.APPLICATION_NO, "+//1
					" A.FINANCE_NO,"+//2
					" A.CLIENT_CODE,"+//3
					" "+m_schema_name+".AF_CO_GET_APP_NAME(A.APPLICATION_NO) CNAME, "+//4
					" "+m_schema_name+".AF_CO_GET_ASSET_DESC(A.APPLICATION_NO) ASSET_DETAIL, "+//5
					" "+m_schema_name+".AF_CO_GET_APP_FINANCE_AMT(A.APPLICATION_NO) FINANCE_AMT, "+//6
					" A.RENTAL_ARREAS_NO RENTAL_ARR, "+//7
					" A.RENTAL_ARREAS RENTAL_VAL, "+//8
					" A.CAP_OUTSTANDING CAP_OUTS, "+//9
					" "+m_schema_name+".AF_CO_GET_APP_NIBSM(A.APPLICATION_NO) NIBSM, "+//10
					" A.INT_SUSPENCE_AMT INT_SUSPENSE, "+//11
					" A.PROVISION_RATE*100 PROVION_RATE, "+//12
					" "+m_schema_name+".AF_CO_GET_PROV_3M_INT_SUSP(A.APPLICATION_NO,'"+m_start_date+"') INTCOME_SUS_3MONTHS, "+//13
					" "+m_schema_name+".AF_CO_GET_PROV_INT_LAST_MONTH(A.APPLICATION_NO,TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1),'DD-MM-YYYY')) INCOME_SUS_LAST_MONTH, "+//14
					" "+m_schema_name+".AF_CO_GET_PROV_INT_LAST_MONTH(A.APPLICATION_NO,'"+m_start_date+"') INCOME_SUS_CURRENT_MONTH, "+//15
					" "+m_schema_name+".AF_CO_GET_PROVISION_LAST_MONTH(A.APPLICATION_NO,TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1),'DD-MM-YYYY')) PROVISION_PRE_MONTH, "+//16
					" NVL(A.PROVISION_COMMENTS,'-'),"+//17
					" A.ACC_POST_STATUS, "+//18
					" NVL(A.VAT_SUSPENCE_AMT,0) VAT_SUSPENCE_AMT, "+//19
					" NVL(A.ARREARS_CAPITAL,0) RENTAL_VAL,"+//20 // ADDED BY NUWAN DE SILVA
					" NVL(A.FUTURE_CAPITAL,0) FUTURE_CAPITAL "+//21 // ADDED BY NUWAN DE SILVA
					" ,NVL("+m_schema_name+".AF_CO_GET_MONTHS_DIFF(A.APPLICATION_NO,'"+m_end_date+"'),0)  AGREEGATE_AGE "+//22
					" ,"+m_schema_name+".AF_CO_GET_EMP_NAME("+m_schema_name+".AF_GET_COLL_OFFICER(FINANCE_NO)), "+			
					" nvl("+m_schema_name+".AF_CO_GET_ODI_BAL(FINANCE_NO,'"+m_end_date+"'),0)  "+//Added BY Sandun 12-06-2009
					" ,nvl("+m_schema_name+".AF_CO_GET_SEC_VAL(FINANCE_NO,A.APPLICATION_NO),0)  VALUATION"+//Added BY ns
					" ,NVL(OTHER_CHARGES,0) OTHER_CHARGES"+
					" FROM "+m_schema_name+".AF_CO_PRO_PROVISION_DETAILS A "+
					" WHERE A.PROVISION_DATE=TO_DATE('"+m_start_date+"','DD-MM-YYYY') "+
					
					//" AND  (NVL(A.RENTAL_ARREAS_NO,0) + NVL("+m_schema_name+".AF_CO_GET_MONTHS_DIFF(A.APPLICATION_NO,'"+m_end_date+"'),0)) < 6 "+ // added by udara on 01-10-2013
					" AND A.FINANCE_NO IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CO_NON_PERFORMING_ADDITION)  "+
					" ORDER BY A.RENTAL_ARREAS_NO,A.APPLICATION_NO ");
				
				String m_string="";	
				
				
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onload='window_onload();' >"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				out.println("<INPUT TYPE='Hidden' NAME='hid_start_date'  VALUE='"+m_start_date+"' >"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_end_date'    VALUE='"+m_end_date+"' >"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_screen_name' VALUE='AF_INCOME_SUS_PROV_SUGGEST' >");
				
				out.println("<BR>");  
				out.println("<p><center><b>Provision for Bad and Doubtful Debts and Income Suspension as at "+m_current_date+"</b><center></p>"); 
				out.println("<BR>"); 
				
				//ADDED BY NUWAN DE SILVA ON 21-08-2008 ------------------------------------------------------------------
				out.println("<table align='center' width='100%' class='table' border='1' cellpadding='1' cellspacing='0'>");
				out.println("<tr >");
				out.println("<td width='1%' ></td>"); 
				out.println("<td width='15%' style='text-align:center'><DIV class=div_input>Finance No</DIV></td>");//1
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Client Name</DIV></td>");//2
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Type of the Asset</DIV></td>"); //3
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Collection Officer</DIV></td>"); //3
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Finance Amt</DIV></td>"); //4
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Rental in Arrears No</DIV></td>"); //5
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Aggregate age</DIV></td>"); //5
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Rental in Arrears Amt</DIV></td>"); //6
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Interest in Suspense "+m_current_date+"</DIV></td>"); //7
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Interest in Suspense "+m_last_date+"</DIV></td>"); //8
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Interest Suspended during the Month</DIV></td>"); //9
				//out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Income Susp. in first 3 months</DIV></td>"); //10
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Arrears Capital</DIV></td>"); //11
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Other Charges</DIV></td>"); //27 added by nuwan de silva on 14-11-2009
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Future Capital</DIV></td>"); //12
				//out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Capital Outstanding</DIV></td>"); //7
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>NIBSM/Deposits</DIV></td>"); //13
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Net Exposure</DIV></td>"); //14
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Valuation @ 80%</DIV></td>"); //15
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Net Exposure After Valuation</DIV></td>"); //16
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision Rate</DIV></td>"); //17
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision as at "+m_current_date+"</DIV></td>"); //18
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision as at "+m_last_date+"</DIV></td>"); //19
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision during the Month</DIV></td>"); //20
				out.println("<td width='20%' ><DIV class=div_input>VAT susp. Amt</DIV></td>"); //21
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Ac. Post Status</DIV></td>"); //22
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Ac. Comments</DIV></td>"); //23
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>ODI Amount</DIV></td>"); //24 SJ on 12-06-2009
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Select</DIV></td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				int chk_nums=0;
				int j=0;
				int counts=0;
				
				while(rs.next()){
					
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\">");
						j=0;
					}
					
					//Added Other charges for net Exposure rs.getDouble(26) Nuwan De Silva on 08-11-2009
					
					//double m_net_exposure=rs.getDouble(20) + rs.getDouble(21)+ rs.getDouble(13) - rs.getDouble(10) - rs.getDouble(25) + rs.getDouble(26) ;//reduce the valuation- rs.getDouble(25);
					
					
					double m_net_exposure=rs.getDouble("RENTAL_VAL") + rs.getDouble("FUTURE_CAPITAL")+ rs.getDouble("OTHER_CHARGES")+ rs.getDouble("VAT_SUSPENCE_AMT") - rs.getDouble("INCOME_SUS_CURRENT_MONTH")  ;
					double m_net_exposure_after_val=rs.getDouble("RENTAL_VAL") + rs.getDouble("FUTURE_CAPITAL")+ rs.getDouble("OTHER_CHARGES")+ rs.getDouble("VAT_SUSPENCE_AMT") - rs.getDouble("INCOME_SUS_CURRENT_MONTH") - rs.getDouble("VALUATION") ;
					
					
					
					//double m_provision_amt=m_net_exposure*(rs.getDouble(12)/100);
					double m_provision_amt=m_net_exposure_after_val*(rs.getDouble(12)/100);
					
					
					double m_rental_arrears=rs.getDouble(8);//rs.getDouble(14)+rs.getDouble(13)+rs.getDouble(20);
					//out.println("rs.getDouble(7)"+rs.getDouble(7));
					//out.println("rs.getDouble(22)"+rs.getString(22));
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='15%' style='text-align:center'>"+rs.getString(1)+"</td>");
					out.println("<td width='15%' style='text-align:center'><INPUT TYPE='Hidden' name=TXT_HID_FIN_"+counts+" id=TXT_HID_FIN_"+counts+" VALUE='"+rs.getString(2)+"' >"+rs.getString(2)+"</td>"); //1
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(4)+"</td>"); //2
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(5)+"</td>"); //3
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(23)+"</td>"); //3
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(6))+"</td>"); //4
					
					//out.println("<td width='15%' style='text-align:right'>"+rs.getString(7)+"</td>"); //5
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(7))+"</td>"); //5
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(7)+ rs.getDouble(22))+"</td>"); //5 //agregate age
					//out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(8))+"</td>"); //6
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_rental_arrears)+"</td>"); //6 
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(15))+"</td>"); //7
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(14))+"</td>"); //8
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(15)-rs.getDouble(14))+"</td>"); //9
					//out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(13))+"</td>"); //10
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(20))+"</td>"); //11
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(26))+"</td>"); //11 Other charges Added by Nuwan De Silva 08-11-2009
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(21))+"</td>"); //12
					//out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(9))+"</td>"); //7
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(10))+"</td>"); //13
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_net_exposure)+"</td>"); //14
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(25))+"</td>"); //15
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_net_exposure_after_val)+"</td>"); //16
					out.println("<td width='15%' style='text-align:right'>"+nf1.format(rs.getDouble(12))+"%</td>"); //17
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_provision_amt)+"</td>"); //18
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(16))+"</td>"); //19
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_provision_amt-rs.getDouble(16))+"</td>"); //20
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(19))+"</td>"); //21
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(18)+"</td>"); //22
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(17)+"</td>"); //23
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(24))+"</td>"); //24 SJ on 12-06-2009
					out.println("<td width='15%' style='text-align:right'> <input type=\"checkbox\" name=TXT_CHK_SELECT_"+counts+" id=TXT_CHK_SELECT_"+counts+" onclick=\"change(this,'"+counts+"');\" value='1' checked > </td>");
					out.println("</tr>");
					
					counts = counts + 1;
				}
				out.println("</table>");
				//END ADDED BY NUWAN DE SILVA ON 21-08-2008 ------------------------------------------------------------------
				
				out.println("<INPUT TYPE='Hidden' NAME='hid_count'  VALUE='"+counts+"' >"); 
				
				out.println("<table align = 'center' width='100%' border=1 >");
				out.println("<tr>");
				out.println("   <td width='70%' > &nbsp; </td>");
				out.println("   <td width='15%' >");
				out.println("       <b>Select All</b> &nbsp; <input type=\"checkbox\" name=TXT_CHK_SELECT_ALL id=TXT_CHK_SELECT_ALL onclick=\"change_select_all(this,'"+counts+"');\" checked > ");
				out.println("   </td>");
				out.println("   <td width='15%' >");
				out.println("       <input type=\"button\" class='mainbut' onClick='save_window()' value=\"Save\" > ");
				out.println("   </td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</FORM>"); 
				out.println("</body>"); 
				out.println("</html>"); 
			}
			
			
			else if(m_chksql.equals("INCOME_SUSPENCE_REPORT_RPT_REVERSE")){
				
				String m_start_date=req.getParameter("start_date");
				String m_end_date=req.getParameter("end_date");
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Provision for Bad and Doubtful Debts and Income Suspension</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				
				
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function change(obj,row) {"); 
				out.println("   if(obj.checked==false){"); 
				out.println("    obj.value=\"0\";"); 
				out.println("    ck=1");
				out.println("   }");
				out.println("   if(obj.checked==true){"); 
				out.println("    obj.value=\"1\";"); 
				out.println("    ck=0");
				out.println("   }");
				//out.println("   alert(obj.name + '   ' + obj.value); ");
				out.println("}"); 
				
				
				out.println("function change_select_all(obj,count) {"); 
				//out.println("   alert(count); ");
				out.println("   if(obj.checked==false){"); 	
				//out.println("      alert('true'); ");
				out.println("      for(i=0; i<count; i++){ ");
				out.println("        document.getElementById('TXT_CHK_SELECT_'+i).checked = false; ");
				out.println("        document.getElementById('TXT_CHK_SELECT_'+i).value = '0'; ");
				out.println("      }");	
				out.println("   }");
				out.println("   else if(obj.checked==true){"); 
				//out.println("      alert('false'); ");
				out.println("      for(i=0; i<count; i++){ ");
				out.println("        document.getElementById('TXT_CHK_SELECT_'+i).checked = true; ");
				out.println("        document.getElementById('TXT_CHK_SELECT_'+i).value = '1'; ");
				out.println("      }");
				out.println("   }");
				out.println("}"); 	
				
				out.println("function window_onload(){ "); 
				
				out.println("} "); 
				
				out.println("function save_window(){ "); 
				out.println("	document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_display_Income_provision_suggest_save';");  
				out.println("	document.Form1.submit();	"); 
				out.println("} "); 
				
				
				out.println("</SCRIPT>");
				
				
				
				rs= stmt.executeQuery (" SELECT TO_CHAR(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1)),'DD-MON-YYYY'),"+
					" TO_CHAR(LAST_DAY(TO_DATE('"+m_start_date+"','DD-MM-YYYY')),'DD-MON-YYYY') FROM DUAL ");
				
				String m_last_date="";
				String m_current_date="";
				
				if(rs.next()){
					m_last_date=rs.getString(1);
					m_current_date=rs.getString(2);
				}
				
				rs= stmt.executeQuery (
					" SELECT "+
					" A.APPLICATION_NO, "+//1
					" A.FINANCE_NO,"+//2
					" A.CLIENT_CODE,"+//3
					" "+m_schema_name+".AF_CO_GET_APP_NAME(A.APPLICATION_NO) CNAME, "+//4
					" "+m_schema_name+".AF_CO_GET_ASSET_DESC(A.APPLICATION_NO) ASSET_DETAIL, "+//5
					" "+m_schema_name+".AF_CO_GET_APP_FINANCE_AMT(A.APPLICATION_NO) FINANCE_AMT, "+//6
					" A.RENTAL_ARREAS_NO RENTAL_ARR, "+//7
					" A.RENTAL_ARREAS RENTAL_VAL, "+//8
					" A.CAP_OUTSTANDING CAP_OUTS, "+//9
					" "+m_schema_name+".AF_CO_GET_APP_NIBSM(A.APPLICATION_NO) NIBSM, "+//10
					" A.INT_SUSPENCE_AMT INT_SUSPENSE, "+//11
					" A.PROVISION_RATE*100 PROVION_RATE, "+//12
					//" "+m_schema_name+".AF_CO_GET_PROV_3M_INT_SUSP(A.APPLICATION_NO,'"+m_start_date+"') INTCOME_SUS_3MONTHS, "+//13
					" 0 INTCOME_SUS_3MONTHS, "+//13
					" "+m_schema_name+".AF_CO_GET_PROV_INT_LAST_MONTH(A.APPLICATION_NO,TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1),'DD-MM-YYYY')) INCOME_SUS_LAST_MONTH, "+//14
					" "+m_schema_name+".AF_CO_GET_PROV_INT_LAST_MONTH(A.APPLICATION_NO,'"+m_start_date+"') INCOME_SUS_CURRENT_MONTH, "+//15
					" "+m_schema_name+".AF_CO_GET_PROVISION_LAST_MONTH(A.APPLICATION_NO,TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1),'DD-MM-YYYY')) PROVISION_PRE_MONTH, "+//16
					" NVL(A.PROVISION_COMMENTS,'-'),"+//17
					" A.ACC_POST_STATUS, "+//18
					" NVL(A.VAT_SUSPENCE_AMT,0) VAT_SUSPENCE_AMT, "+//19
					" NVL(A.ARREARS_CAPITAL,0) RENTAL_VAL,"+//20 // ADDED BY NUWAN DE SILVA
					" NVL(A.FUTURE_CAPITAL,0) FUTURE_CAPITAL"+//21 // ADDED BY NUWAN DE SILVA
					" ,NVL("+m_schema_name+".AF_CO_GET_MONTHS_DIFF(A.APPLICATION_NO,'"+m_end_date+"'),0)  AGREEGATE_AGE "+//22
					" ,"+m_schema_name+".AF_CO_GET_EMP_NAME("+m_schema_name+".AF_GET_COLL_OFFICER(FINANCE_NO)), "+			
					" nvl("+m_schema_name+".AF_CO_GET_ODI_BAL(FINANCE_NO,'"+m_end_date+"'),0)  "+//Added BY Sandun 12-06-2009
					" ,nvl("+m_schema_name+".AF_CO_GET_SEC_VAL(FINANCE_NO,A.APPLICATION_NO),0) VALUATION"+//Added BY ns
					" ,NVL(OTHER_CHARGES,0) OTHER_CHARGES"+
					" FROM "+m_schema_name+".AF_CO_PRO_PROVISION_DETAILS A "+
					" WHERE A.PROVISION_DATE=TO_DATE('"+m_start_date+"','DD-MM-YYYY') "+
					
					//" AND  (NVL(A.RENTAL_ARREAS_NO,0) + NVL("+m_schema_name+".AF_CO_GET_MONTHS_DIFF(A.APPLICATION_NO,'"+m_end_date+"'),0)) < 6 "+ // added by udara on 01-10-2013
					" AND A.FINANCE_NO IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CO_NON_PERFORMING_REVERSAL)  "+
					" ORDER BY A.RENTAL_ARREAS_NO,A.APPLICATION_NO ");
				
				String m_string="";	
				
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onload='window_onload();' >");
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				out.println("<INPUT TYPE='Hidden' NAME='hid_start_date'  VALUE='"+m_start_date+"' >"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_end_date'    VALUE='"+m_end_date+"' >"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_screen_name' VALUE='AF_INCOME_SUS_PROV_REVERSE' >");
				
				
				out.println("<BR>");  
				out.println("<p><center><b>Provision for Bad and Doubtful Debts and Income Suspension as at "+m_current_date+"</b><center></p>"); 
				out.println("<BR>"); 
				
				//ADDED BY NUWAN DE SILVA ON 21-08-2008 ------------------------------------------------------------------
				out.println("<table align='center' width='100%' class='table' border='1' cellpadding='1' cellspacing='0'>");
				out.println("<tr >");
				out.println("<td width='1%' ></td>"); 
				out.println("<td width='15%' style='text-align:center'><DIV class=div_input>Finance No</DIV></td>");//1
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Client Name</DIV></td>");//2
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Type of the Asset</DIV></td>"); //3
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Collection Officer</DIV></td>"); //3
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Finance Amt</DIV></td>"); //4
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Rental in Arrears No</DIV></td>"); //5
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Aggregate age</DIV></td>"); //5
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Rental in Arrears Amt</DIV></td>"); //6
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Interest in Suspense "+m_current_date+"</DIV></td>"); //7
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Interest in Suspense "+m_last_date+"</DIV></td>"); //8
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Interest Suspended during the Month</DIV></td>"); //9
				//out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Income Susp. in first 3 months</DIV></td>"); //10
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Arrears Capital</DIV></td>"); //11
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Other Charges</DIV></td>"); //27 added by nuwan de silva on 14-11-2009
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Future Capital</DIV></td>"); //12
				//out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Capital Outstanding</DIV></td>"); //7
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>NIBSM/Deposits</DIV></td>"); //13
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Net Exposure</DIV></td>"); //14
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Valuation @ 80%</DIV></td>"); //15
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Net Exposure After Valuation</DIV></td>"); //16
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision Rate</DIV></td>"); //17
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision as at "+m_current_date+"</DIV></td>"); //18
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision as at "+m_last_date+"</DIV></td>"); //19
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision during the Month</DIV></td>"); //20
				out.println("<td width='20%' ><DIV class=div_input>VAT susp. Amt</DIV></td>"); //21
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Ac. Post Status</DIV></td>"); //22
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Ac. Comments</DIV></td>"); //23
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>ODI Amount</DIV></td>"); //24 SJ on 12-06-2009
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Select</DIV></td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				int chk_nums=0;
				int j=0;
				int counts = 0;
				while(rs.next()){
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\">");
						j=0;
					}
					
					//Added Other charges for net Exposure rs.getDouble(26) Nuwan De Silva on 08-11-2009
					
					//double m_net_exposure=rs.getDouble(20) + rs.getDouble(21)+ rs.getDouble(13) - rs.getDouble(10) - rs.getDouble(25) + rs.getDouble(26) ;//reduce the valuation- rs.getDouble(25);
					//double m_net_exposure=rs.getDouble(9)-rs.getDouble(10)-0+rs.getDouble(13);
					//double m_provision_amt=m_net_exposure*rs.getDouble(12);
					
					double m_net_exposure=rs.getDouble("RENTAL_VAL") + rs.getDouble("FUTURE_CAPITAL")+ rs.getDouble("OTHER_CHARGES")+ rs.getDouble("VAT_SUSPENCE_AMT") - rs.getDouble("INCOME_SUS_CURRENT_MONTH")  ;
					double m_net_exposure_after_val=rs.getDouble("RENTAL_VAL") + rs.getDouble("FUTURE_CAPITAL")+ rs.getDouble("OTHER_CHARGES")+ rs.getDouble("VAT_SUSPENCE_AMT") - rs.getDouble("INCOME_SUS_CURRENT_MONTH") - rs.getDouble("VALUATION") ;
					
					
					double m_provision_amt=m_net_exposure*(rs.getDouble(12)/100);
					
					
					double m_rental_arrears=rs.getDouble(8);//rs.getDouble(14)+rs.getDouble(13)+rs.getDouble(20);
					//out.println("rs.getDouble(7)"+rs.getDouble(7));
					//out.println("rs.getDouble(22)"+rs.getString(22));
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='15%' style='text-align:center'>"+rs.getString(1)+"</td>");
					out.println("<td width='15%' style='text-align:center'><INPUT TYPE='Hidden' name=TXT_HID_FIN_"+counts+" id=TXT_HID_FIN_"+counts+" VALUE='"+rs.getString(2)+"' >"+rs.getString(2)+"</td>"); //1
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(4)+"</td>"); //2
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(5)+"</td>"); //3
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(23)+"</td>"); //3
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(6))+"</td>"); //4
					
					//out.println("<td width='15%' style='text-align:right'>"+rs.getString(7)+"</td>"); //5
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(7))+"</td>"); //5
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(7)+ rs.getDouble(22))+"</td>"); //5 //agregate age
					//out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(8))+"</td>"); //6
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_rental_arrears)+"</td>"); //6 
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(15))+"</td>"); //7
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(14))+"</td>"); //8
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(15)-rs.getDouble(14))+"</td>"); //9
					//out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(13))+"</td>"); //10
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(20))+"</td>"); //11
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(26))+"</td>"); //11 Other charges Added by Nuwan De Silva 08-11-2009
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(21))+"</td>"); //12
					//out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(9))+"</td>"); //7
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(10))+"</td>"); //13
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_net_exposure)+"</td>"); //14
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(25))+"</td>"); //15
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_net_exposure_after_val)+"</td>"); //16
					out.println("<td width='15%' style='text-align:right'>"+nf1.format(rs.getDouble(12))+"%</td>"); //17
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_provision_amt)+"</td>"); //18
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(16))+"</td>"); //19
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_provision_amt-rs.getDouble(16))+"</td>"); //20
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(19))+"</td>"); //21
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(18)+"</td>"); //22
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(17)+"</td>"); //23
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(24))+"</td>"); //24 SJ on 12-06-2009
					out.println("<td width='15%' style='text-align:right'> <input type=\"checkbox\" name=TXT_CHK_SELECT_"+counts+" id=TXT_CHK_SELECT_"+counts+" onclick=\"change(this,'"+counts+"');\" value='1' checked > </td>");
					out.println("</tr>");
					
					counts = counts + 1;
				}
				out.println("</table>");
				
				out.println("<INPUT TYPE='Hidden' NAME='hid_count'  VALUE='"+counts+"' >"); 
				
				//END ADDED BY NUWAN DE SILVA ON 21-08-2008 ------------------------------------------------------------------
				
				out.println("<table align = 'center' width='100%' border=1 >");
				out.println("<tr>");
				out.println("   <td width='70%' > &nbsp; </td>");
				out.println("   <td width='15%' >");
				out.println("       <b>Select All</b> &nbsp; <input type=\"checkbox\" name=TXT_CHK_SELECT_ALL id=TXT_CHK_SELECT_ALL onclick=\"change_select_all(this,'"+counts+"');\" checked > ");
				out.println("   </td>");
				out.println("   <td width='15%' >");
				out.println("       <input type=\"button\" class='mainbut' onClick='save_window()' value=\"Save\" > ");
				out.println("   </td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</FORM>"); 
				out.println("</body>"); 
				out.println("</html>"); 
			}
			
			else if(m_chksql.equals("INCOME_SUSPENCE_REPORT_RPT_CONFIRMATION")){
				
				String m_start_date=req.getParameter("start_date");
				String m_end_date=req.getParameter("end_date");
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Provision for Bad and Doubtful Debts and Income Suspension</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function change(obj,row) {"); 
				out.println("   if(obj.checked==false){"); 
				out.println("    obj.value=\"0\";"); 
				out.println("    ck=1");
				out.println("   }");
				out.println("   if(obj.checked==true){"); 
				out.println("    obj.value=\"1\";"); 
				out.println("    ck=0");
				out.println("   }");
				//out.println("   alert(obj.name + '   ' + obj.value); ");
				out.println("}"); 
				
				
				out.println("function change_select_all(obj,count) {"); 
				//out.println("   alert(count); ");
				out.println("   if(obj.checked==false){"); 	
				//out.println("      alert('true'); ");
				out.println("      for(i=0; i<count; i++){ ");
				out.println("        document.getElementById('TXT_CHK_SELECT_'+i).checked = false; ");
				out.println("        document.getElementById('TXT_CHK_SELECT_'+i).value = '0'; ");
				out.println("      }");	
				out.println("   }");
				out.println("   else if(obj.checked==true){"); 
				//out.println("      alert('false'); ");
				out.println("      for(i=0; i<count; i++){ ");
				out.println("        document.getElementById('TXT_CHK_SELECT_'+i).checked = true; ");
				out.println("        document.getElementById('TXT_CHK_SELECT_'+i).value = '1'; ");
				out.println("      }");
				out.println("   }");
				out.println("}"); 
				
				out.println("function window_onload(){ "); 
				
				out.println("} "); 
				
				out.println("function save_window(){ "); 
				out.println("	document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_display_Income_provision_conf_save';");  
				out.println("	document.Form1.submit();	"); 
				out.println("} "); 
				
				
				out.println("</SCRIPT>");
				
				
				rs= stmt.executeQuery (" SELECT TO_CHAR(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1)),'DD-MON-YYYY'),"+
					" TO_CHAR(LAST_DAY(TO_DATE('"+m_start_date+"','DD-MM-YYYY')),'DD-MON-YYYY') FROM DUAL ");
				
				String m_last_date="";
				String m_current_date="";
				
				if(rs.next()){
					m_last_date=rs.getString(1);
					m_current_date=rs.getString(2);
				}
				
				rs= stmt.executeQuery (
					
					
					" SELECT "+
					" A.APPLICATION_NO, "+//1
					" A.FINANCE_NO,"+//2
					" A.CLIENT_CODE,"+//3
					" "+m_schema_name+".AF_CO_GET_APP_NAME(A.APPLICATION_NO) CNAME, "+//4
					" "+m_schema_name+".AF_CO_GET_ASSET_DESC(A.APPLICATION_NO) ASSET_DETAIL, "+//5
					" "+m_schema_name+".AF_CO_GET_APP_FINANCE_AMT(A.APPLICATION_NO) FINANCE_AMT, "+//6
					" A.RENTAL_ARREAS_NO RENTAL_ARR, "+//7
					" A.RENTAL_ARREAS RENTAL_VAL, "+//8
					" A.CAP_OUTSTANDING CAP_OUTS, "+//9
					" "+m_schema_name+".AF_CO_GET_APP_NIBSM(A.APPLICATION_NO) NIBSM, "+//10
					" A.INT_SUSPENCE_AMT INT_SUSPENSE, "+//11
					" A.PROVISION_RATE*100 PROVION_RATE, "+//12
					//" "+m_schema_name+".AF_CO_GET_PROV_3M_INT_SUSP(A.APPLICATION_NO,'"+m_start_date+"') INTCOME_SUS_3MONTHS, "+//13
					" 0  INTCOME_SUS_3MONTHS,  "+
					" "+m_schema_name+".AF_CO_GET_PROV_INT_LAST_MONTH(A.APPLICATION_NO,TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1),'DD-MM-YYYY')) INCOME_SUS_LAST_MONTH, "+//14
					" "+m_schema_name+".AF_CO_GET_PROV_INT_LAST_MONTH(A.APPLICATION_NO,'"+m_start_date+"') INCOME_SUS_CURRENT_MONTH, "+//15
					" "+m_schema_name+".AF_CO_GET_PROVISION_LAST_MONTH(A.APPLICATION_NO,TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1),'DD-MM-YYYY')) PROVISION_PRE_MONTH, "+//16
					" NVL(A.PROVISION_COMMENTS,'-'),"+//17
					" A.ACC_POST_STATUS, "+//18
					" NVL(A.VAT_SUSPENCE_AMT,0) VAT_SUSPENCE_AMT, "+//19
					" NVL(A.ARREARS_CAPITAL,0) RENTAL_VAL,"+//20 // ADDED BY NUWAN DE SILVA
					" NVL(A.FUTURE_CAPITAL,0) FUTURE_CAPITAL"+//21 // ADDED BY NUWAN DE SILVA
					" ,NVL("+m_schema_name+".AF_CO_GET_MONTHS_DIFF(A.APPLICATION_NO,'"+m_end_date+"'),0)  AGREEGATE_AGE "+//22
					" ,"+m_schema_name+".AF_CO_GET_EMP_NAME("+m_schema_name+".AF_GET_COLL_OFFICER(FINANCE_NO)), "+			
					" nvl("+m_schema_name+".AF_CO_GET_ODI_BAL(FINANCE_NO,'"+m_end_date+"'),0)  "+//Added BY Sandun 12-06-2009
					" ,nvl("+m_schema_name+".AF_CO_GET_SEC_VAL(FINANCE_NO,A.APPLICATION_NO),0)  VALUATION"+//Added BY ns
					" ,NVL(OTHER_CHARGES,0) OTHER_CHARGES "+
					" FROM "+m_schema_name+".AF_CO_PRO_PROVISION_DETAILS A "+
					" WHERE A.PROVISION_DATE=TO_DATE('"+m_start_date+"','DD-MM-YYYY') "+
					
					//" AND  (NVL(A.RENTAL_ARREAS_NO,0) + NVL("+m_schema_name+".AF_CO_GET_MONTHS_DIFF(A.APPLICATION_NO,'"+m_end_date+"'),0)) < 6 "+ // added by udara on 01-10-2013
					
					" ORDER BY A.RENTAL_ARREAS_NO,A.APPLICATION_NO ");
				
				String m_string="";	
				
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onload='window_onload();' >");
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				out.println("<INPUT TYPE='Hidden' NAME='hid_start_date'  VALUE='"+m_start_date+"' >"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_end_date'    VALUE='"+m_end_date+"' >"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_screen_name' VALUE='AF_INCOME_SUS_PROV_CONFIRM' >");
				
				out.println("<BR>");  
				out.println("<p><center><b>Provision for Bad and Doubtful Debts and Income Suspension as at "+m_current_date+"</b><center></p>"); 
				out.println("<BR>"); 
				
				//ADDED BY NUWAN DE SILVA ON 21-08-2008 ------------------------------------------------------------------
				out.println("<table align='center' width='100%' class='table' border='1' cellpadding='1' cellspacing='0'>");
				out.println("<tr >");
				out.println("<td width='1%' ></td>"); 
				out.println("<td width='15%' style='text-align:center'><DIV class=div_input>Finance No</DIV></td>");//1
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Client Name</DIV></td>");//2
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Type of the Asset</DIV></td>"); //3
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Collection Officer</DIV></td>"); //3
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Finance Amt</DIV></td>"); //4
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Rental in Arrears No</DIV></td>"); //5
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Aggregate age</DIV></td>"); //5
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Rental in Arrears Amt</DIV></td>"); //6
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Interest in Suspense "+m_current_date+"</DIV></td>"); //7
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Interest in Suspense "+m_last_date+"</DIV></td>"); //8
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Interest Suspended during the Month</DIV></td>"); //9
				//out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Income Susp. in first 3 months</DIV></td>"); //10
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Arrears Capital</DIV></td>"); //11
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Other Charges</DIV></td>"); //27 added by nuwan de silva on 14-11-2009
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Future Capital</DIV></td>"); //12
				//out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Capital Outstanding</DIV></td>"); //7
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>NIBSM/Deposits</DIV></td>"); //13
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Net Exposure</DIV></td>"); //14
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Valuation @ 80%</DIV></td>"); //15
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Net Exposure After Valuation</DIV></td>"); //16
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision Rate</DIV></td>"); //17
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision as at "+m_current_date+"</DIV></td>"); //18
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision as at "+m_last_date+"</DIV></td>"); //19
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision during the Month</DIV></td>"); //20
				out.println("<td width='20%' ><DIV class=div_input>VAT susp. Amt</DIV></td>"); //21
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Ac. Post Status</DIV></td>"); //22
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Ac. Comments</DIV></td>"); //23
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>ODI Amount</DIV></td>"); //24 SJ on 12-06-2009
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Select</DIV></td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				int chk_nums=0;
				int j=0;
				int counts = 0;
				while(rs.next()){
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\">");
						j=0;
					}
					
					//Added Other charges for net Exposure rs.getDouble(26) Nuwan De Silva on 08-11-2009
					
					//double m_net_exposure=rs.getDouble(20) + rs.getDouble(21)+ rs.getDouble(13) - rs.getDouble(10) - rs.getDouble(25) + rs.getDouble(26) ;//reduce the valuation- rs.getDouble(25);
					//double m_net_exposure=rs.getDouble(9)-rs.getDouble(10)-0+rs.getDouble(13);
					//double m_provision_amt=m_net_exposure*rs.getDouble(12);
					
					double m_net_exposure=rs.getDouble("RENTAL_VAL") + rs.getDouble("FUTURE_CAPITAL")+ rs.getDouble("OTHER_CHARGES")+ rs.getDouble("VAT_SUSPENCE_AMT") - rs.getDouble("INCOME_SUS_CURRENT_MONTH")  ;
					double m_net_exposure_after_val=rs.getDouble("RENTAL_VAL") + rs.getDouble("FUTURE_CAPITAL")+ rs.getDouble("OTHER_CHARGES")+ rs.getDouble("VAT_SUSPENCE_AMT") - rs.getDouble("INCOME_SUS_CURRENT_MONTH") - rs.getDouble("VALUATION") ;
										
					
					//double m_provision_amt=m_net_exposure*(rs.getDouble(12)/100);
					double m_provision_amt=m_net_exposure_after_val*(rs.getDouble(12)/100);
					
					
					double m_rental_arrears=rs.getDouble(8);//rs.getDouble(14)+rs.getDouble(13)+rs.getDouble(20);
					//out.println("rs.getDouble(7)"+rs.getDouble(7));
					//out.println("rs.getDouble(22)"+rs.getString(22));
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='15%' style='text-align:center'>"+rs.getString(1)+"</td>");
					out.println("<td width='15%' style='text-align:center'><INPUT TYPE='Hidden' name=TXT_HID_FIN_"+counts+" id=TXT_HID_FIN_"+counts+" VALUE='"+rs.getString(2)+"' >"+rs.getString(2)+"</td>"); //1
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(4)+"</td>"); //2
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(5)+"</td>"); //3
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(23)+"</td>"); //3
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(6))+"</td>"); //4
					
					//out.println("<td width='15%' style='text-align:right'>"+rs.getString(7)+"</td>"); //5
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(7))+"</td>"); //5
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(7)+ rs.getDouble(22))+"</td>"); //5 //agregate age
					//out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(8))+"</td>"); //6
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_rental_arrears)+"</td>"); //6 
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(15))+"</td>"); //7
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(14))+"</td>"); //8
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(15)-rs.getDouble(14))+"</td>"); //9
					//out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(13))+"</td>"); //10
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(20))+"</td>"); //11
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(26))+"</td>"); //11 Other charges Added by Nuwan De Silva 08-11-2009
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(21))+"</td>"); //12
					//out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(9))+"</td>"); //7
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(10))+"</td>"); //13
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_net_exposure)+"</td>"); //14
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(25))+"</td>"); //15
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_net_exposure_after_val)+"</td>"); //16
					out.println("<td width='15%' style='text-align:right'>"+nf1.format(rs.getDouble(12))+"%</td>"); //17
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_provision_amt)+"</td>"); //18
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(16))+"</td>"); //19
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_provision_amt-rs.getDouble(16))+"</td>"); //20
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(19))+"</td>"); //21
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(18)+"</td>"); //22
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(17)+"</td>"); //23
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(24))+"</td>"); //24 SJ on 12-06-2009
					out.println("<td width='15%' style='text-align:right'> <input type=\"checkbox\" name=TXT_CHK_SELECT_"+counts+" id=TXT_CHK_SELECT_"+counts+" onclick=\"change(this,'"+counts+"');\" value='1' checked > </td>");
					out.println("</tr>");
					
					counts = counts + 1;
					
				}
				out.println("</table>");
				
				out.println("<INPUT TYPE='Hidden' NAME='hid_count'  VALUE='"+counts+"' >"); 
				
				//END ADDED BY NUWAN DE SILVA ON 21-08-2008 ------------------------------------------------------------------
				
				out.println("<table align = 'center' width='100%' border=1 >");
				out.println("<tr>");
				out.println("   <td width='70%' > &nbsp; </td>");
				out.println("   <td width='15%' >");
				out.println("       <b>Select All</b> &nbsp; <input type=\"checkbox\" name=TXT_CHK_SELECT_ALL id=TXT_CHK_SELECT_ALL onclick=\"change_select_all(this,'"+counts+"');\" checked > ");
				out.println("   </td>");
				out.println("   <td width='15%' >");
				out.println("       <input type=\"button\" class='mainbut' onClick='save_window()' value=\"Save\" > ");
				out.println("   </td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</FORM>"); 
				out.println("</body>"); 
				out.println("</html>"); 
			}
			
			
			// ================================== end by udara on 11-10-2013 ====================================================
			
			
			
			
			else {
				out.println("Undefined");
			}
			
			out.close();
			conn.close();
			this.destroy();
			
			
		}
		catch (Exception e) {
			try {
				ServletOutputStream out = res.getOutputStream();
				out.println(e.toString());
				conn.rollback();
				conn.close();
				out.close();
				
			}catch (Exception eti) {}
			
			ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			e.printStackTrace(new PrintWriter(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString());
			out.close();
			
		}
	}
}

