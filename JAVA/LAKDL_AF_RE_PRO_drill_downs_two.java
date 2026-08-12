import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

// DEVELOP BY : MAHELA FOR OFSCL LEASING    DATE:23-03-2007

public class LAKDL_AF_RE_PRO_drill_downs_two extends javax.servlet.http.HttpServlet {
	
	/*
	Connection conn;
	Statement stmt,stmt1,stmt3;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
	
	public ResultSet rs,rs1,rs2,rs3;
	public String m_chksql;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
	public  void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{ // synchronized
		
		Connection conn = null;
		Statement stmt= null,stmt1= null,stmt3= null;
		CallableStatement callstmt= null;
		java.text.NumberFormat nf= null,nf1= null;
		
		ResultSet rs= null,rs1= null,rs2= null,rs3= null;
		String m_chksql= null;
		
		DateFormat dateFormat2 = null;
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim();
			String m_servlet_client_url=m_sn_methods.servlet_client_url;//Added by Dineth on 15-04-2009
			String m_client_name=m_sn_methods.client_name;//Added by Dineth on 15-04-2009
			String m_client_t3_port=m_sn_methods.client_t3_port;//Added by Dineth on 15-04-2009
			
			//**************************************************************					
			//**************************************************************					
			//**************************************************************					
			//--MODIFIED By :delanjali----------------------------------------
			//--DATE				: 2007-07-27--------------------------------------
			
			
			dateFormat2 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss aa");
			
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
			stmt3=conn.createStatement();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("SHOW_INVOICE_DRILL")) {
				
				int count = 0;
				String m_string="";								
				String m_invoice_no=req.getParameter("invoice_no");					
				String m_finance_no="";//Added by Dineth on 15-04-2009
				String m_cancel_remark="";
				
				// added by udara 31-05-2019
				int security_count = 0;
				String security_status = "-";
				
				rs= stmt1.executeQuery(" "+
					" SELECT COUNT(*) "+
					" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC "+
					" WHERE REF_DEBIT_NOTE_NO = '"+m_invoice_no+"'	"+				
					" ");
				
				if(rs.next()){
					security_count = rs.getInt(1);
				}
				
				// end by udara 31-05-2019
				
				if(security_count==0) {
				
						rs= stmt1.executeQuery(
							//out.println(
							" SELECT "+
							" A.INVOICE_NO,"+//1
							" NVL(DECODE(A.ACTIVE_STATUS,'Y','Active','C','Cancel','DB_CAN','Cancel'),'-'),"+//2 //'DB_CAN' - Added By Sandun on 03-08-2009
							" NVL(A.GROUP_INV_NO,'-'),"+//3
							" NVL(A.FINANCE_NO,'-'),"+//4
							" NVL(B.RECEIPT_NO,'-'),"+//5
							" NVL(B.ALLOCATION_NO,'-'),"+//6
							" NVL(A.CLIENT_CODE,'-'),"+//7
							" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-'),"+//8
							//allocation details
							" NVL(TO_CHAR(B.ALLOCATED_DATE,'DD-MM-YYYY'),'-'),"+//9
							" NVL(B.INVOICED_AMOUNT,0),"+//10
							" NVL(B.RECEIPT_AMOUNT,0),"+//11
							" NVL(B.REMARKS,'-'),"+//12 allo remarks
							//other
							" NVL(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'-'),"+//13
							" NVL(A.NET_AMOUNT,0),"+//14
							" NVL(A.VAT_AMOUNT,0),"+//15
							" NVL(A.TOTAL_AMOUNT,0),"+//16
							" NVL(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'-'),"+//17
							" NVL(A.SETTELE_AMOUNT,0),"+//18
							" NVL(A.BALANCE_TO_BE_RECEIVED,0),"+//19
							" NVL(A.REMARKS,'-'),"+//20 
							" NVL(A.CURRENCY_CODE,'-'),"+//21 
							" NVL(A.EXCHANGE_RATE,0),"+//22
							" NVL(A.TOTAL_AMOUNT_CURR,0),"+//23
							" NVL(A.SETTEL_AMOUNT_CURR,0),"+//24
							" NVL(A.BALANCE_TO_BE_RECEIVED_CURR,0),"+//25
							" NVL(A.INVOICE_TYPE,'-'),"+//26
							" NVL(C.INVOICE_DESC,'Termination'),"+//27
							" NVL(TO_CHAR(A.PRINTER_DATE,'DD-MM-YYYY'),'-'),"+//28
							" NVL(INITCAP(A.PRINTED_STATUS),'-'),"+//29
							" NVL(INITCAP(A.SETTLED_STATUS),'-'),"+//30
							" NVL(INITCAP(A.ODI_STATUS),'-'),"+//31
							" NVL(TO_CHAR(A.ODI_STOPPED_DATE,'DD-MM-YYYY'),'-'),"+//32
							" NVL(A.TOTAL_ODI_AMOUNT,0),"+//33
							" NVL(A.TOTAL_ODI_SETTLED,0),"+//34
							" NVL(A.ODI_ADJUSTED_AMOUNT,0),"+//35
							" NVL(TO_CHAR(A.ODI_ADJUSTED_DATE,'DD-MM-YYYY'),'-'),"+//36
							" NVL(A.ODI_ADJUSTED_USER,'-'),"+//37
							//adjust details
							" NVL(A.ADJUSTED_USER,'-'),"+//38
							" NVL(TO_CHAR(A.ADJUSTED_DATE,'DD-MM-YYYY'),'-'),"+//39
							" NVL(A.ADJUSTED_AMOUNT,0),"+//40
							" A.ENT_DATE ,"+//41
							" A.ENT_USER "+//42
							" , NVL("+m_schema_name+".AF_CO_GET_CR_DR_REMARKS(A.INVOICE_NO),'-'), "+ //43	 REMARK
							"   NVL("+m_schema_name+".AF_CO_GET_CR_DR_REMARKS_2(A.INVOICE_NO),'-')"+//44 REMARK2 ADDED MILINDA 2014-01-08
							" , NVL( "+m_schema_name+".AF_CO_GET_CR_DR_APPRO_USER(A.INVOICE_NO),'-')"+//45 APPRO USER AND DATE TIME ADDED MILINDA 2014-01-08
							" ,  NVL("+m_schema_name+".AF_CO_GET_CR_DR_ACTIVE_STATUS(A.INVOICE_NO),'-')"+//46 ACTIVE STATUS ADDED MILINDA 2014-01-08
							" ,TO_CHAR(DUE_DATE + (SELECT NVL(CURRENT_PERIOD,0) FROM "+m_schema_name+".AF_CO_MAS_GRACE_PERIOD),'DD-MM-YYYY') DUE_DATE_2 "+ // 47 added by udara 23-04-2014
							" ,NVL(TO_CHAR((SELECT START_DATE FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO),'DD-MM-YYYY'),'-') START_DATE  "+ // 48 added by udara 23-04-2014
		                    " ,NVL(TO_CHAR((SELECT END_DATE FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO),'DD-MM-YYYY'),'-') END_DATE , "+ // 49 added by udara 23-04-2014
							"   NVL("+m_schema_name+".AF_CO_GET_CR_DR_DOCREF(A.INVOICE_NO),'-')"+//50 ADDED BY CJ ON 2014-05-02
							" ,(SELECT NVL(REMARKS,'-') FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO) REMARKS  "+ // 51 added by udara 24-07-2014
							" ,DECODE("+m_schema_name+".AF_CO_GET_INSURENCE_DONE_BY("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)),'LICENSEE','Company','CLIENT','Lessee') "+ // 52 added by udara 25-08-2014 
							
							" ,(SELECT NVL(POLICY_NO,'-') FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO) POLICY_NO  "+ // 53 added by udara 24-11-2014
							" ,(SELECT NVL(DEBIT_NOTE_NO,'-') FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO) DEBIT_NOTE_NO  "+ // 54 added by udara 24-11-2014
							" ,(SELECT COUNT(DEBIT_NOTE_NO) FROM "+m_schema_name+".AF_CO_INS_PREMIUM_CANCEL_LOG WHERE DEBIT_NOTE_NO = A.INVOICE_NO)    "+ // 55 added by udara 24-11-2014
							
							" ,(SELECT NVL(REMARKS,'-') FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_BK WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO AND ROWNUM=1) REMARKS  "+ // 56 added by udara 24-11-2014
							" ,(SELECT NVL(POLICY_NO,'-') FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_BK WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO AND ROWNUM=1) POLICY_NO  "+ // 57 added by udara 24-11-2014
							" ,(SELECT NVL(DEBIT_NOTE_NO,'-') FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_BK WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO AND ROWNUM=1) DEBIT_NOTE_NO  "+ // 58 added by udara 24-11-2014
							" ,(SELECT NVL(INSUR_COM,'-') FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO AND ROWNUM=1) INSUR_COM  "+  //59 add by A S Silva 05-06-2015
							//" , NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(INSUR_COM),'-') "+//60 add by A S Silva 05-06-2015
							" , NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME((SELECT NVL(INSUR_COM,'-') FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO AND ROWNUM=1)),'-') "+//60 add by A S Silva 05-06-2015
							" ,(SELECT NVL(SUM_INSSURED,0) FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO) DEBIT_NOTE_NO  "+ // 61 added by udara 22-10-2015
							" ,(SELECT ROUND(SYSDATE-START_DATE) FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO) DEBIT_NOTE_NO  "+ // 62 added by udara 22-10-2015
							
							" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS B,"+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD C "+
							" WHERE A.INVOICE_NO=B.INVOICE_NO(+)"+
							" AND A.INVOICE_TYPE=C.INVOICE_TYPE_CODE(+)"+
							" AND A.INVOICE_NO='"+m_invoice_no+"'");
						
				}
				else{
					
						security_status = "Security Insurance";
					
						rs= stmt1.executeQuery(
						//out.println(
						" SELECT "+
						" A.INVOICE_NO,"+//1
						" NVL(DECODE(A.ACTIVE_STATUS,'Y','Active','C','Cancel','DB_CAN','Cancel'),'-'),"+//2 //'DB_CAN' - Added By Sandun on 03-08-2009
						" NVL(A.GROUP_INV_NO,'-'),"+//3
						" NVL(A.FINANCE_NO,'-'),"+//4
						" NVL(B.RECEIPT_NO,'-'),"+//5
						" NVL(B.ALLOCATION_NO,'-'),"+//6
						" NVL(A.CLIENT_CODE,'-'),"+//7
						" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-'),"+//8
						//allocation details
						" NVL(TO_CHAR(B.ALLOCATED_DATE,'DD-MM-YYYY'),'-'),"+//9
						" NVL(B.INVOICED_AMOUNT,0),"+//10
						" NVL(B.RECEIPT_AMOUNT,0),"+//11
						" NVL(B.REMARKS,'-'),"+//12 allo remarks
						//other
						" NVL(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'-'),"+//13
						" NVL(A.NET_AMOUNT,0),"+//14
						" NVL(A.VAT_AMOUNT,0),"+//15
						" NVL(A.TOTAL_AMOUNT,0),"+//16
						" NVL(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'-'),"+//17
						" NVL(A.SETTELE_AMOUNT,0),"+//18
						" NVL(A.BALANCE_TO_BE_RECEIVED,0),"+//19
						" NVL(A.REMARKS,'-'),"+//20 
						" NVL(A.CURRENCY_CODE,'-'),"+//21 
						" NVL(A.EXCHANGE_RATE,0),"+//22
						" NVL(A.TOTAL_AMOUNT_CURR,0),"+//23
						" NVL(A.SETTEL_AMOUNT_CURR,0),"+//24
						" NVL(A.BALANCE_TO_BE_RECEIVED_CURR,0),"+//25
						" NVL(A.INVOICE_TYPE,'-'),"+//26
						" NVL(C.INVOICE_DESC,'Termination'),"+//27
						" NVL(TO_CHAR(A.PRINTER_DATE,'DD-MM-YYYY'),'-'),"+//28
						" NVL(INITCAP(A.PRINTED_STATUS),'-'),"+//29
						" NVL(INITCAP(A.SETTLED_STATUS),'-'),"+//30
						" NVL(INITCAP(A.ODI_STATUS),'-'),"+//31
						" NVL(TO_CHAR(A.ODI_STOPPED_DATE,'DD-MM-YYYY'),'-'),"+//32
						" NVL(A.TOTAL_ODI_AMOUNT,0),"+//33
						" NVL(A.TOTAL_ODI_SETTLED,0),"+//34
						" NVL(A.ODI_ADJUSTED_AMOUNT,0),"+//35
						" NVL(TO_CHAR(A.ODI_ADJUSTED_DATE,'DD-MM-YYYY'),'-'),"+//36
						" NVL(A.ODI_ADJUSTED_USER,'-'),"+//37
						//adjust details
						" NVL(A.ADJUSTED_USER,'-'),"+//38
						" NVL(TO_CHAR(A.ADJUSTED_DATE,'DD-MM-YYYY'),'-'),"+//39
						" NVL(A.ADJUSTED_AMOUNT,0),"+//40
						" A.ENT_DATE ,"+//41
						" A.ENT_USER "+//42
						" , NVL("+m_schema_name+".AF_CO_GET_CR_DR_REMARKS(A.INVOICE_NO),'-'), "+ //43	 REMARK
						"   NVL("+m_schema_name+".AF_CO_GET_CR_DR_REMARKS_2(A.INVOICE_NO),'-')"+//44 REMARK2 ADDED MILINDA 2014-01-08
						" , NVL( "+m_schema_name+".AF_CO_GET_CR_DR_APPRO_USER(A.INVOICE_NO),'-')"+//45 APPRO USER AND DATE TIME ADDED MILINDA 2014-01-08
						" ,  NVL("+m_schema_name+".AF_CO_GET_CR_DR_ACTIVE_STATUS(A.INVOICE_NO),'-')"+//46 ACTIVE STATUS ADDED MILINDA 2014-01-08
						" ,TO_CHAR(DUE_DATE + (SELECT NVL(CURRENT_PERIOD,0) FROM "+m_schema_name+".AF_CO_MAS_GRACE_PERIOD),'DD-MM-YYYY') DUE_DATE_2 "+ // 47 added by udara 23-04-2014
						" ,NVL(TO_CHAR((SELECT START_DATE FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO),'DD-MM-YYYY'),'-') START_DATE  "+ // 48 added by udara 23-04-2014
	                    " ,NVL(TO_CHAR((SELECT END_DATE FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO),'DD-MM-YYYY'),'-') END_DATE , "+ // 49 added by udara 23-04-2014
						"   NVL("+m_schema_name+".AF_CO_GET_CR_DR_DOCREF(A.INVOICE_NO),'-')"+//50 ADDED BY CJ ON 2014-05-02
						" ,(SELECT NVL(REMARKS,'-') FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO) REMARKS  "+ // 51 added by udara 24-07-2014
						" ,DECODE("+m_schema_name+".AF_CO_GET_INSURENCE_DONE_BY("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)),'LICENSEE','Company','CLIENT','Lessee') "+ // 52 added by udara 25-08-2014 
						
						" ,(SELECT NVL(POLICY_NO,'-') FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO) POLICY_NO  "+ // 53 added by udara 24-11-2014
						" ,(SELECT NVL(DEBIT_NOTE_NO,'-') FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO) DEBIT_NOTE_NO  "+ // 54 added by udara 24-11-2014
						" ,(SELECT COUNT(DEBIT_NOTE_NO) FROM "+m_schema_name+".AF_CO_INS_PREMIUM_CANCEL_LOG WHERE DEBIT_NOTE_NO = A.INVOICE_NO)    "+ // 55 added by udara 24-11-2014
						
						" ,(SELECT NVL(REMARKS,'-') FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_SEC_BK WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO AND ROWNUM=1) REMARKS  "+ // 56 added by udara 24-11-2014
						" ,(SELECT NVL(POLICY_NO,'-') FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_SEC_BK WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO AND ROWNUM=1) POLICY_NO  "+ // 57 added by udara 24-11-2014
						" ,(SELECT NVL(DEBIT_NOTE_NO,'-') FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_SEC_BK WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO AND ROWNUM=1) DEBIT_NOTE_NO  "+ // 58 added by udara 24-11-2014
						" ,(SELECT NVL(INSUR_COM,'-') FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO AND ROWNUM=1) INSUR_COM  "+  //59 add by A S Silva 05-06-2015
						//" , NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(INSUR_COM),'-') "+//60 add by A S Silva 05-06-2015
						" , NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME((SELECT NVL(INSUR_COM,'-') FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO AND ROWNUM=1)),'-') "+//60 add by A S Silva 05-06-2015
						" ,(SELECT NVL(SUM_INSSURED,0) FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO) DEBIT_NOTE_NO  "+ // 61 added by udara 22-10-2015
						" ,(SELECT ROUND(SYSDATE-START_DATE) FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC WHERE REF_DEBIT_NOTE_NO = A.INVOICE_NO) DEBIT_NOTE_NO  "+ // 62 added by udara 22-10-2015
						
						" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS B,"+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD C "+
						" WHERE A.INVOICE_NO=B.INVOICE_NO(+)"+
						" AND A.INVOICE_TYPE=C.INVOICE_TYPE_CODE(+)"+
						" AND A.INVOICE_NO='"+m_invoice_no+"'");
				}
				
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Invoice Details - Invoice No : "+m_invoice_no+" </TITLE></HEAD>");
				//Added by Dineth on 15-04-2009
				out.println("<SCRIPT>");
				out.println(" function load_pay_details(invoice_no,finance_no){ ");
				out.println(" m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_PRO_drill_downs_two?chksql=pay_detail&finance_no=\"+finance_no+\"&invoice_no=\"+invoice_no+\" \";	");				
				out.println(" window.open(m_url,'displayWindow8','left=110,top=110,width=650,height=400,toolbar=0,location=0,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
				out.println("}");
				
				
				out.println("</SCRIPT>");
				
				//End by Dineth on 15-04-2009
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Invoice Details - Invoice No : "+m_invoice_no+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Invoice No  "+m_invoice_no+"  </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if(more_dir){
					count++;
					m_finance_no=rs.getString(4);//Added by Dineth on 15-04-2009
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Invoie No</b></td>");
					out.println("<td width='50%' class=div_input onClick=\"\" style='cursor:hand' >"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(2)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Group Invoice No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Finance No</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_finance_detail_drill('"+rs.getString(4)+"')><u>"+rs.getString(4)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					/*out.println("<tr>");
                    out.println("<td width='1%'></td>"); 
                    out.println("<td width='30%' class=div_input><b>Receipt No</b></td>");
                    out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_settle_receipt_drill('"+rs.getString(5)+"')><u>"+rs.getString(5)+"</u></td>");
                    out.println("<td width='*%'></td>");
                    out.println("</tr>");*/
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Allocation No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Client Name</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(7)+"')><u>"+rs.getString(8)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					//add by A S Silva
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input ><b>Insurance company</b></td>");
					out.println("<td width='*%'  class=div_input ><u>"+rs.getString(60)+"</u></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input ><b>Remarks</b></td>");
					out.println("<td width='*%'  class=div_input >"+rs.getString(20)+"</td>");
					out.println("</tr>");
					out.println("</table>");
					
					
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Generate Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(41)+"</td>");
					out.println("<td width='20%' class=div_input><b>Generate User</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(42)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Value Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(13)+"</td>");
					out.println("<td width='20%' class=div_input><b>NET Amount</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(14))+"</td>");
					out.println("</tr>");					
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>VAT Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(15))+"</td>");
					out.println("<td width='20%' class=div_input><b>Total Amount</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(16))+"</td>");
					out.println("</tr>");
					
					// commented by udara 23-04-2014
					/*
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Due Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(17)+"</td>");
					out.println("<td width='20%' class=div_input><b>Settled Amount</b></td>"); // Settle Amount - Modified by Chandana on for Ref no.761 on 31/07/2007 
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(18))+"</td>");
					out.println("</tr>");
					*/
					
					
					// added by udara 23-04-2014
					
					if( (rs.getString(20).equals("CHARGES - INSURANCE")) || (rs.getString(26).equals("INSURANCE")) ){
						
							out.println("<tr>");
							out.println("<td width='1%'></td>"); 
							out.println("<td width='30%' class=div_input><b>Due Date</b></td>");
							out.println("<td width='25%' class=div_input>"+rs.getString(17)+"</td>"); // out.println("<td width='25%' class=div_input>"+rs.getString(47)+"</td>");
							out.println("<td width='20%' class=div_input><b>Settled Amount</b></td>"); // Settle Amount - Modified by Chandana on for Ref no.761 on 31/07/2007 
							out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(18))+"</td>");
							out.println("</tr>");
					
							out.println("<tr>");
							out.println("<td width='1%'></td>"); 
							out.println("<td width='30%' class=div_input><b>Start Date</b></td>");
							out.println("<td width='25%' class=div_input> "+rs.getString(48)+" </td>");
							out.println("<td width='20%' class=div_input> </td>");
							out.println("<td width='*%' class=div_input> </td>");
							out.println("</tr>");
							
							out.println("<tr>");
							out.println("<td width='1%'></td>"); 
							out.println("<td width='30%' class=div_input><b>End Date</b></td>");
							out.println("<td width='25%' class=div_input> "+rs.getString(49)+" </td>");
							out.println("<td width='20%' class=div_input> </td>");
							out.println("<td width='*%' class=div_input> </td>");
							out.println("</tr>");
							
							// added by udara 31-05-2019
							out.println("<tr>");
							out.println("<td width='1%'></td>"); 
							out.println("<td width='30%' class=div_input><b>Security Status</b></td>");
							out.println("<td width='25%' class=div_input> "+security_status+" </td>");
							out.println("<td width='20%' class=div_input> </td>");
							out.println("<td width='*%' class=div_input> </td>");
							out.println("</tr>");
							// end by udara 31-05-2019
							
							out.println("<tr>");
							out.println("<td width='1%'></td>"); 
							out.println("<td width='30%' class=div_input><b>Insurance Remarks</b></td>");
							//out.println("<td width='25%' class=div_input> "+rs.getString(51)+" </td>");
							
							if(rs.getString(51)==null || rs.getString(51).equals("null"))
								out.println("<td width='25%' class=div_input> - </td>");
							else
								out.println("<td width='25%' class=div_input> "+rs.getString(51)+" </td>");
							
							out.println("<td width='20%' class=div_input> </td>");
							out.println("<td width='*%' class=div_input> </td>");
							out.println("</tr>");
					
					}
					else{
						
							out.println("<tr>");
							out.println("<td width='1%'></td>"); 
							out.println("<td width='30%' class=div_input><b>Due Date</b></td>");
							out.println("<td width='25%' class=div_input>"+rs.getString(17)+"</td>");
							out.println("<td width='20%' class=div_input><b>Settled Amount</b></td>"); // Settle Amount - Modified by Chandana on for Ref no.761 on 31/07/2007 
							out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(18))+"</td>");
							out.println("</tr>");
							
							out.println("<tr>");
							out.println("<td width='1%'></td>"); 
							out.println("<td width='30%' class=div_input><b>Insurance Remarks</b></td>");
							//out.println("<td width='25%' class=div_input> "+rs.getString(51)+" </td>");
							
							if(rs.getString(51)==null || rs.getString(51).equals("null"))
								out.println("<td width='25%' class=div_input> - </td>");
							else
								out.println("<td width='25%' class=div_input> "+rs.getString(51)+" </td>");
							
							
							out.println("<td width='20%' class=div_input> </td>");
							out.println("<td width='*%' class=div_input> </td>");
							out.println("</tr>");
							
						
					}
					
					// end by udara 23-04-2014
					
					// added by udara 24-11-2014
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Policy No.</b></td>");
					//out.println("<td width='25%' class=div_input> "+rs.getString(53)+" </td>");
					
					if(rs.getInt(55)>0){
						if(rs.getString(57)==null || rs.getString(57).equals("null"))
							out.println("<td width='25%' class=div_input> - </td>");
						else
							out.println("<td width='25%' class=div_input> "+rs.getString(57)+" </td>");
					}
					else{
						if(rs.getString(53)==null || rs.getString(53).equals("null"))
							out.println("<td width='25%' class=div_input> - </td>");
						else
							out.println("<td width='25%' class=div_input> "+rs.getString(53)+" </td>");
					}
				    
					
					
					out.println("<td width='20%' class=div_input> </td>");
					out.println("<td width='*%' class=div_input> </td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Debit Note No.</b></td>");
					//out.println("<td width='25%' class=div_input> "+rs.getString(54)+" </td>");
					if(rs.getInt(55)>0){
						if(rs.getString(58)==null || rs.getString(58).equals("null"))
							out.println("<td width='25%' class=div_input> - </td>");
						else
							out.println("<td width='25%' class=div_input> "+rs.getString(58)+" </td>");
					}
					else{
						if(rs.getString(54)==null || rs.getString(54).equals("null"))
							out.println("<td width='25%' class=div_input> - </td>");
						else
							out.println("<td width='25%' class=div_input> "+rs.getString(54)+" </td>");
					}
					
					out.println("<td width='20%' class=div_input> </td>");
					out.println("<td width='*%' class=div_input> </td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Active Status</b></td>");
					//out.println("<td width='25%' class=div_input> "+rs.getString(55)+" </td>");
					
					if(rs.getInt(55)>0)
						out.println("<td width='25%' class=div_input> Cancelled </td>");
					else
						out.println("<td width='25%' class=div_input> Active </td>");
					
					out.println("<td width='20%' class=div_input> </td>");
					out.println("<td width='*%' class=div_input> </td>");
					out.println("</tr>");
					
					// end by udara 24-11-2014
					
					// added by udara 22-10-2015
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Sum Insured</b></td>");
					
					if(rs.getInt(55)>0)
						out.println("<td width='25%' class=div_input> - </td>");
					else
						out.println("<td width='25%' class=div_input> "+nf.format(rs.getDouble(61))+" </td>");
					
					out.println("<td width='20%' class=div_input> </td>");
					out.println("<td width='*%' class=div_input> </td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>No. of days</b></td>");
					
					if(rs.getInt(55)>0)
						out.println("<td width='25%' class=div_input> - </td>");
					else
						out.println("<td width='25%' class=div_input> "+rs.getInt(62)+" </td>");
					
					out.println("<td width='20%' class=div_input> </td>");
					out.println("<td width='*%' class=div_input> </td>");
					out.println("</tr>");
					// end by udara 22-10-2015
					
					
					// added by udara 25-08-2014
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Insurance Done By</b></td>");
					out.println("<td width='25%' class=div_input> "+rs.getString(52)+" </td>");
					out.println("<td width='20%' class=div_input> </td>");
					out.println("<td width='*%' class=div_input> </td>");
					out.println("</tr>");
					
					// end by udara 25-08-2014
					
					
					
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Balance To Be Received</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(19))+"</td>");
					out.println("<td width='20%' class=div_input><b>Exchange Rate</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(22))+"</td>");
					out.println("</tr>");
					/*
                    out.println("<tr>");
                    out.println("<td width='1%'></td>"); 
                    out.println("<td width='30%' class=div_input><b>Tot. Amount Current</b></td>");
                    out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(23))+"</td>");
                    out.println("<td width='20%' class=div_input><b>Settled Amount Current</b></td>"); // Settle Amount - Modified by Chandana on for Ref no.761 on 31/07/2007
                    out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(24))+"</td>");
                    out.println("</tr>");
                    */
					/*
                    out.println("<tr>");
                    out.println("<td width='1%'></td>"); 
                    out.println("<td width='30%' class=div_input><b>Bal. To Be Received Current</b></td>");
                    out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(25))+"</td>");
                    out.println("<td width='20%' class=div_input><b>Invoice Type</b></td>");
                    out.println("<td width='*%' class=div_input>"+rs.getString(27)+"</td>");
                    out.println("</tr>");
                    */
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Invoice Type</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(27)+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Currency</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(21)+"</td>");
					out.println("<td width='20%' class=div_input>&nbsp;</td>");
					out.println("<td width='*%' class=div_input>&nbsp;</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Printed Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(28)+"</td>");
					out.println("<td width='20%' class=div_input><b>Printed Status</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(29)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Settled Status</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(30)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>ODI Details</u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>ODI Status</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(31)+"</td>");
					out.println("<td width='20%' class=div_input><b>ODI Stopped Date</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(32)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Total ODI Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(33))+"</td>");
					out.println("<td width='20%' class=div_input><b>Total ODI Settle Amount</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(34))+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>ODI Adjusted Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(35))+"</td>");
					out.println("<td width='20%' class=div_input><b>ODJ Adjusted Date</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(36)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>ODI Adjusted User</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(38)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>Adjustment Details</u></b></td>"); // Adjust Details - Modified by Chandana on for Ref no.761 on 31/07/2007
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Adjusted User</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(38)+"</td>");
					out.println("<td width='20%' class=div_input><b>Adjusted Amount</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(40))+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Adjusted Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(39)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Adjusted Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(40))+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>Allocation Details</u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Allocated Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Invoiced Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(10))+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Receipt Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(11))+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Allocation Remarks</b></td>");//Remark - Modified by Chandana on for Ref no.761 on 31/07/2007
					out.println("<td width='25%' class=div_input>"+rs.getString(12)+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Credit Note Remarks</b></td>");//Remark - Modified by Chandana on for Ref no.761 on 31/07/2007
					out.println("<td width='25%' class=div_input>"+rs.getString(43)+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					
					//ADDED MILINDA 2014-01-08
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Credit Note Remarks</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(44)+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					
					// Added by CJ 02-05-2014
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Document Reference Number</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(50)+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					
					//if(rs.getString(46).equals("Y")){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Credit Note Approved User</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(45)+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
				//}else{
					
					//}
					//end milinda
					out.println("</table>");
					more_dir = rs.next();
				}
				
				out.println("<br>");
				rs=stmt1.executeQuery(" SELECT nvl(MOD_USER,'-'),nvl(TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'-') "+
					" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE "+
					" WHERE  INVOICE_NO = '"+m_invoice_no+"' "+
					" AND    ACTIVE_STATUS IN ('C','DB_CAN')" );
				if(rs.next()){//Added By Sandun On 03-08-2009
					
					
					
					rs2= stmt.executeQuery(
						" SELECT "+
						" TO_CHAR(TRN_DATE,'DD-MM-YYYY:HH:MI:SS') TRN_DATE,"+
						" TO_CHAR(ENT_DATE,'DD-MM-YYYY:HH:MI:SS') ENT_DATE, "+
						" ENT_USER, "+
						" CANCELLATION_REMARKS "+
						" FROM  "+m_schema_name+".AF_CO_PRO_CANCEL_DR_NOTE "+
						" WHERE INVOICE_NO='"+m_invoice_no+"'");
					if(rs2.next()){
						m_cancel_remark=rs2.getString(4);
					} 										
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>Cancellation Details</u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Cancelled User</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Cancelled Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Cancelled Remark</b></td>");
					out.println("<td width='25%' class=div_input>"+m_cancel_remark+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				
				
				//Payee Details nuwan de silva on 09-12-2008
				rs=stmt1.executeQuery(
					" SELECT A.RECEIVER,A.TOT_SETTLE_AMOUNT, A.INT_BAL_SETTLE_AMOUNT,A.BAL_TO_BE_PAID  "+
					" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A "+
					" WHERE A.REF_NO='"+m_invoice_no+"' ");
				
				out.println("<br>");
				
				if(rs.next()){
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><u><b>Payee Details</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='12%' class=div_input align='left'><b>Payee Name</b></td>");
					out.println("<td width='12%' class=div_input align='left' ><b>Total Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Settled Amount</b></td>");
					out.println("<td width='20%' class=div_input align='right' ><b>Balance Amount</b></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='12%' class=div_input align='left'  >"+rs.getString(1)+"</td>");
					out.println("<td width='12%' class=div_input align='left'  >"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='15%' class=div_input align='right' STYLE='{cursor:hand;}' onclick=\"load_pay_details('"+m_invoice_no+"','"+m_finance_no+"')\"><u>"+nf.format(rs.getDouble(3))+"</u></td>");//Added by Dineth on 15-04-2009
					out.println("<td width='20%' class=div_input align='right' >"+nf.format(rs.getDouble(4))+"</td>");
					out.println("</tr>");
					out.println("</table>");
					
				}
				
				
				
				
				//Added by Mahela on 30-07-2007
				String Sql_Allocation=" SELECT "+
					"  INVOICE_NO,"+//1
					"  RECEIPT_NO,"+//2
					"  TO_CHAR(ALLOCATED_DATE,'DD-MM-YYYY'),"+//3
					"  RECEIPT_AMOUNT,"+//4
					"  SETTELED_AMOUNT, "+// 5 allo amount from this inv 
					" (SELECT NVL(SUB_REC_NO,'-') FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT WHERE REC_NO = RECEIPT_NO) SUB_REC_NO "+ // 6 added by udara on 30-08-2013
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS "+
					" WHERE INVOICE_NO='"+m_invoice_no+"' ";
				
				rs=stmt1.executeQuery(Sql_Allocation);
				
				boolean  more =rs.next();
				out.println("<br>");
				out.println("<hr color='black'>");
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='80%' class=div_input><b>Invoice Settlement Details</b></td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("</table>");
				
				
				if (!more) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No settlement data for Invoice No  "+m_invoice_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
					
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='12%' class=div_input align='left'><b>Receipt No</b></td>");
					out.println("<td width='12%' class=div_input align='left'><b>Sub Receipt No</b></td>");
					out.println("<td width='12%' class=div_input align='left' ><b>Allocation Date </b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Receipt Amount&nbsp</b></td>");
					out.println("<td width='20%' class=div_input align='right' ><b>Allo Amount from this Inv&nbsp</b></td>");
					//out.println("<td width='15%' class=div_input align='right' ><b>Settled Amount&nbsp</b></td>");
					//out.println("<td width='15%' class=div_input align='right' ><b>Balance Amount&nbsp</b></td>");
					out.println("</tr>");
					out.println("</table>");
					//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='12%' class=div_input align='left' style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='12%' class=div_input align='left'  >"+rs.getString(6)+"</td>"); // added by udara on 30-08-2013
					out.println("<td width='12%' class=div_input align='left'  >"+rs.getString(3)+"</td>");
					out.println("<td width='15%' class=div_input align='right' >"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='20%' class=div_input align='right' >"+nf.format(rs.getDouble(5))+"&nbsp;</td>");
					//out.println("<td width='20%' class=div_input align='right' >"+nf.format(rs.getDouble(6))+"&nbsp;</td>");
					//out.println("<td width='15%' class=div_input align='right' >"+nf.format(rs.getDouble(7))+"&nbsp;</td>");
					//out.println("<td width='15%' class=div_input align='right' >"+nf.format(rs.getDouble(8))+"&nbsp;</td>");
					out.println("</tr>");
					
					more = rs.next();
				}
				
				
				out.println("</table>");
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			
			
			else if(m_chksql.equals("SHOW_PROFORMA_INVOICE_DRILL")) {
				
				int count = 0;
				String m_string="";								
				String m_invoice_no=req.getParameter("invoice_no");					
				
				
				rs= stmt1.executeQuery(
					" SELECT "+
					"  A.INVOICE_NO,"+//1
					"  NVL(DECODE(A.ACTIVE_STATUS,'Y','Active','Cancel'),'-'),"+//2
					"  NVL(A.APPLICATION_NO,'-'),"+//3
					"  NVL(A.ASSET_ID,'-'),"+//4
					"  NVL(A.PRICING_NO,'-'),"+//5
					"  NVL(A.PURCHASE_ORDER_NO,'-'),"+//6
					"  NVL(A.TERMINATION_NO,'-'),"+//7
					//Vehicle Details
					"  NVL(A.VEHICLE_NO,'-'),"+//8
					"  NVL(A.REG_NO,'-'),"+//9
					"  NVL(TO_CHAR(A.REG_DATE,'DD-MM-YYYY'),'-'),"+//10
					"  NVL(A.ENGINE_NO,'-'),"+//11
					"  NVL(A.CHASSIS_NO,'-'),"+//12
					"  NVL(A.MODEL_CODE,'-'),"+//13
					"  NVL(C.DESCRIPTION,'-'),"+//14
					"  NVL(A.SUB_MODEL_CODE,'-'),"+//15
					"  NVL(B.DESCRIPTION,'-'),"+//16
					"  NVL(A.COLOUR,'-'),"+//17
					"  NVL(A.SEATING_CAPACITY,0),"+//18
					"  NVL(DECODE(A.FUEL_CONVERTION_STATUS,'Y','Yes','N','No'),'-'),"+//19
					//Pricing
					"  NVL(A.NET_PRICE,0),"+//20
					"  NVL(A.VAT,0),"+//21
					"  NVL(A.TOTAL_AMOUNT,0),"+//22
					"  NVL(A.VALUE,0),"+//23
					"  NVL(A.CAPITAL_ALLOWANCE,0),"+//24
					"  NVL(A.CURR_CODE,'-'),"+//25
					//To be delivered to
					"  NVL(A.TO_BE_DELIVERD_TO,'-'),"+//26
					"  NVL(A.ADDRESS,'-'),"+//27
					"  NVL(A.CITY_CODE,'-'),"+//28
					"  NVL(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'-'),"+//29
					"  NVL(INITCAP(A.DISTRICT_CODE),'-'),"+//30
					//other	
					"  NVL(TO_CHAR(A.INSURANCE_DATE,'DD-MM-YYYY'),'-'),"+//31
					"  NVL(TO_CHAR(A.REVENUE_LICENSE_DATE,'DD-MM-YYYY'),'-'),"+//32
					"  NVL(TO_CHAR(A.LUXURY_TAX_DATE,'DD-MM-YYYY'),'-'),"+//33
					"  NVL(TO_CHAR(A.DRIVING_LICENSE_DATE,'DD-MM-YYYY'),'-'),"+//34
					"  NVL(A.INVOICE_DOC_NO,'-'),"+//35
					"  NVL(TO_CHAR(A.TERMINATION_DATE,'DD-MM-YYYY'),'-'),"+//36 
					"  NVL(A.CR_BOOK_NO,'-'),"+//37
					"  NVL(TO_CHAR(A.CR_PRINT_DATE,'DD-MM-YYYY'),'-'), "+//38
					//Vendor
					"  NVL(A.VENDOR_CODE,'-'),"+//39
					"  NVL(D.NAME,'-'),"+//40
					"  NVL(A.BRANCH_ID,'-') ,"+//41
					"  "+m_schema_name+".AF_CO_GET_VENDOR_ADDRESS(A.VENDOR_CODE,A.BRANCH_ID), "+ //42
					"  "+m_schema_name+".GET_CONTRACTS(A.APPLICATION_NO,A.REG_NO), "+//43 ADDED MILINDA  2014-03-10 GET OTHER CINTRACTS
					"  NVL(A.EXTRAS_INCLUDED,'-')  "+ //"   NVL("+m_schema_name+".AF_CO_NO_OF_CR_ENTRIES(A.APPLICATION_NO),'-') "+ // 44 added by udara 25-05-2015
					"FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_MAS_SUB_MODLE B,"+m_schema_name+".AF_CO_MAS_MODEL C, "+
					""+m_schema_name+".AF_CO_MAS_VENDORS D "+	
					"WHERE A.SUB_MODEL_CODE=B.SUB_CODE(+) "+
					"AND A.MODEL_CODE=C.MODEL_CODE(+) "+
					"AND A.VENDOR_CODE=D.VENDOR_CODE(+) "+	
					"AND A.INVOICE_NO='"+m_invoice_no+"' ");
				
				
				//======ADDED BY NUWAN DE SILVA 21-05-07================================
				
				
				String Sql_Sub_Charges="SELECT "+
					"  PRICING_NO, "+ //1
					"  SUB_CHAGE_CODE, "+ //2
					"  DESCRIPTION, "+ //3
					"  AMOUNT, "+ //4
					"  CHARGE_TYPE "+ //5
					"  FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
					"  WHERE PRO_INVOICE_NO='"+m_invoice_no+"' AND "+
					"  A.SUB_CHAGE_CODE=B.SUB_TYPE_CODE AND "+
					"  NVL(AMOUNT,0) <> 0 "+
					"  ORDER BY  CHARGE_TYPE ";
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE>Proforma Invoice Details - Invoice No : "+m_invoice_no+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Proforma Invoice Details - Invoice No : "+m_invoice_no+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Invoice No  "+m_invoice_no+"  </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Invoice Number</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(2)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Application No</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_application_detail_drill('"+rs.getString(3)+"')><u>"+rs.getString(3)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Asset ID</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_asset_detail_drill('"+rs.getString(4)+"') ><u>"+rs.getString(4)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Pricing No</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_pricing_drill('"+rs.getString(5)+"')><u>"+rs.getString(5)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Purchase Order No</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_purchase_order_drill('"+rs.getString(6)+"') ><u>"+rs.getString(6)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Termination No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>Vehicle Details</u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Vehicle No</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='20%' class=div_input><b>Registration No</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(9)+" </td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Reg. Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(10)+"</td>");
					out.println("<td width='20%' class=div_input><b>Engine No</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(11)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Chassis No</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(12)+"</td>");
					out.println("<td width='20%' class=div_input><b>Model</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(14)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Sub Model</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(16)+"</td>");
					out.println("<td width='20%' class=div_input><b>Color</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(17)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Seating Capacity</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(18)+"</td>");
					out.println("<td width='20%' class=div_input><b>Fuel Conversion Status</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(19)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Other Contract Numbers with Same Vehical No </b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(43)+"</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>Pricing Details</u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>NET price</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(20))+"</td>");
					out.println("<td width='20%' class=div_input><b>VAT</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(21))+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Total Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(22))+"</td>");
					out.println("<td width='20%' class=div_input><b>Value</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(23))+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Capital Allowance</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(24))+"</td>");
					out.println("<td width='20%' class=div_input><b>Currency</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(25)+"</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>To Be Delivered To</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(26)+"</td>");
					out.println("<td width='20%' class=div_input><b>Address</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(27)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Due Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(29)+"</td>");
					out.println("<td width='20%' class=div_input><b>City Code</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(28)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>District </b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(30)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>Other Details</u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Insurance Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(31)+"</td>");
					out.println("<td width='20%' class=div_input><b>Revenue License Date</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(32)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Luxury Tax Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(33)+"</td>");
					out.println("<td width='20%' class=div_input><b>Driving License Date</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(34)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Invoice Doc. No</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(35)+"</td>");
					out.println("<td width='20%' class=div_input><b>Termination Date</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(36)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>CR Book No</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(44)+"</td>"); // modified by udara 25-05-2015 // out.println("<td width='25%' class=div_input>"+rs.getString(37)+"</td>");
					out.println("<td width='20%' class=div_input><b>CR Print Date</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(38)+"</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>Vendor Details</u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Vendor Code</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(39)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Vendor Name</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(40)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Branch ID</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(41)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Address</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(42)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					more_dir = rs.next();
				}
				out.println("<br>");
				
				//==========ADDED BY NUWAN DE SILVA =================================================
				
				rs= stmt1.executeQuery(Sql_Sub_Charges);
				
				boolean more=rs.next();
				
				if(more){
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>Sub Charges Details</u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<br>");
					
					
				}
				int count_amo=0;
				int count_inv=0;
				String m_charge_type="";
				
				out.println("<table align='center' width='100%' class='table' >");
				
				while(more){
					m_charge_type=rs.getString(5);
					
					if(m_charge_type.equals("AMO")){
						
						if(count_amo==0){
							out.println("<tr>");
							out.println("<td width='1%'>&nbsp;</td>"); 
							out.println("<td width='30%' class=div_input><u><b>Amotise</b></u></td>");
							out.println("<td width='50%' class=div_input>&nbsp;</td>");
							out.println("<td width='*%'>&nbsp;</td>");
							out.println("</tr>");
						}
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>"+rs.getString(3)+"</b></td>");
						out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(4))+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						
						count_amo=1;
						
					}
					
					else if(m_charge_type.equals("INV")){
						
						if(count_inv==0){
							out.println("<tr>");
							out.println("<td width='1%'>&nbsp;</td>"); 
							out.println("<td width='30%' class=div_input><u><b>Up Front</b></u></td>");
							out.println("<td width='50%' class=div_input>&nbsp;</td>");
							out.println("<td width='*%'>&nbsp;</td>");
							out.println("</tr>");
						}
						
						out.println("<tr>");
						out.println("<td width='1%'>&nbsp;</td>"); 
						out.println("<td width='30%' class=div_input><b>"+rs.getString(3)+"</b></td>");
						out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(4))+"</td>");
						out.println("<td width='*%'>&nbsp;</td>");
						out.println("</tr>");
						count_inv=1;
						
					}
					
					
					more=rs.next();
					
				}
				
				out.println("</table>");
				
				//===================================================================================
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			
			
			
			else if(m_chksql.equals("SHOW_ASSET_DETAIL_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_asset_id=req.getParameter("asset_id");					
				
				/*		rs= stmt1.executeQuery(" SELECT "+  //Comment by Chandana on 24/07/2007
                        "  NVL(A.ASSET_ID,'-'),"+//1
                        "  NVL(DECODE(A.STATUS,'N','New','R','Re-Conditioned','U','Used'),'-'),"+//2
                        "  NVL(DECODE(A.ACTIVE_STATUS,'Y','Active','Cancel'),'-'),"+//3
                        "  NVL(A.APPLICATION_NO,'-'),"+//4
                        "  NVL(A.MODEL_CODE,'-'),"+//5
                        "  NVL(B.DESCRIPTION,'-'),"+//6
                        "  NVL(A.SUB_MODEL_CODE,'-'),"+//7
                        "  NVL(C.DESCRIPTION,'-'),"+//8
                        "  NVL(A.QTY,0),"+//9
                        "  NVL(A.COST,0),"+//10
                        "  NVL(DECODE(A.PURPOSE,'P','Personal','B','Business'),'-'),"+//11
                        "  NVL(A.PERIOD,0)"+//12
                        " FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A,"+m_schema_name+".AF_CO_MAS_MODEL B,"+m_schema_name+".AF_CO_MAS_SUB_MODLE C "+
                        " WHERE A.MODEL_CODE=B.MODEL_CODE(+) "+
                        " AND A.SUB_MODEL_CODE=C.SUB_CODE(+) "+
                        " AND A.ASSET_ID='"+m_asset_id+"' "); */
				
				
				
				
				/* Added by Chandana on 24/07/2007 */
				rs= stmt1.executeQuery(" SELECT "+
					" NVL(A.ASSET_ID,'-'), "+//1
					" NVL(DECODE(A.STATUS,'N','New','R','Re-Conditioned','U','Used'),'-'), "+ //2
					" NVL(DECODE(A.ACTIVE_STATUS,'Y','Active','Cancel'),'-'), "+ //3
					" NVL(A.APPLICATION_NO,'-'), "+ //4
					" NVL(A.MODEL_CODE,'-'), "+ //5
					" NVL(B.DESCRIPTION,'-'), "+ //6
					" NVL(A.SUB_MODEL_CODE,'-'), "+ //7
					" NVL(C.DESCRIPTION,'-'), "+ //8
					" NVL(A.QTY,0), "+ //9
					" NVL(A.COST,0), "+ //10
					" NVL(DECODE(A.PURPOSE,'P','Personal','B','Business'),'-'), "+ //11
					" NVL(A.PERIOD,0), "+ //12
					" NVL(D.REG_NO,'-') "+ //13
					" FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A,"+m_schema_name+".AF_CO_MAS_MODEL B, "+
					" "+m_schema_name+".AF_CO_MAS_SUB_MODLE C,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D "+
					" WHERE A.MODEL_CODE=B.MODEL_CODE(+) "+
					" AND A.SUB_MODEL_CODE=C.SUB_CODE(+) "+
					" AND A.ASSET_ID = D.ASSET_ID "+
					" AND A.ASSET_ID='"+m_asset_id+"' "); 
				
				
				
				
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Asset  Details - Asset ID : "+m_asset_id+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Asset  Details - Asset ID : "+m_asset_id+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Asset ID "+m_asset_id+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Asset ID</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Asset Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(2)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Active Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(3)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Application No</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_application_detail_drill('"+rs.getString(4)+"')><u>"+rs.getString(4)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Model</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Sub Model</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Quantity</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(9)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Cost</b></td>");
					out.println("<td width='50%' class=div_input >"+nf.format(rs.getDouble(10))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Purpose</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(11)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Period</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(12)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Registration No</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(13)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_QUOTATION_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_quotation_no=req.getParameter("quotation_no");					
				
				rs= stmt1.executeQuery("  SELECT DISTINCT "+
					" A.QUOTATION_NO,"+ //1
					" NVL(DECODE(B.STATUS,'Y','Active','P','Pending','C','Cancel'),'-'),"+//2
					" NVL(B.INQUIRY_NO,'-'),"+//3
					" NVL(B.APPR_USER,'-'),"+//4
					" NVL(TO_CHAR(B.APPR_DATE,'DD-MM-YYYY'),'-'),"+//5
					//Repeating values 
					" NVL(A.PRICING_NO,'-'),"+//6
					" NVL(A.OPTION_ID,'-'),"+//7
					" NVL(A.QTY,0),"+//8
					" NVL(A.GROSS_AMOUNT,0),"+//9
					" NVL(A.VAT_AMOUNT,0),"+//10
					" NVL(A.GROSS_RENTAL,0),"+//11
					" NVL(A.VAT_RENTAL,0),"+//12
					" NVL(A.RESIDUAL_AMOUNT,0),"+//13
					" NVL(A.PERIOD,0),"+//14
					" NVL(A.CONDITION_OF_ASSET,'-'),"+//15
					" NVL(E.DESCRIPTION,'-'),"+//16
					" NVL(A.MAKE_CODE,'-'),"+//17
					" NVL(D.MAKE_DESC,'-'),"+//18
					" NVL(A.MODEL_CODE,'-'),"+//19
					" NVL(C.DESCRIPTION,'-') "+//20   
					" FROM "+m_schema_name+".AF_MK_PRO_QUOTATION_DET A,"+m_schema_name+".AF_MK_PRO_QUOTATION B, "+
					" "+m_schema_name+".AF_CO_MAS_MODEL C,"+m_schema_name+".AF_CO_MAS_MAKE D,"+m_schema_name+".AF_CO_MAS_CONDITION_OF_ASSET E "+
					" WHERE B.QUOTATION_NO=A.QUOTATION_NO(+) "+
					" AND A.MODEL_CODE=C.MODEL_CODE(+) "+
					" AND A.MAKE_CODE=D.MAKE_CODE(+) "+
					" AND A.CONDITION_OF_ASSET=E.CODE(+) "+
					" AND A.QUOTATION_NO='"+m_quotation_no+"' ");
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Quotation  Details - Quotation No : "+m_quotation_no+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Quotation  Details - Quotation No : "+m_quotation_no+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Quotation No  "+m_quotation_no+"  </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if(more_dir){
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Quotation No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(2)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Inquiry No</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_inquiry_drill('"+rs.getString(3)+"')><u>"+rs.getString(3)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Approved User</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(4)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Approved Date</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(5)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>Pricing Details</u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					
					while(more_dir) {
						count++;
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='*%' class=div_input><b>"+count+".</b></td>");
						out.println("</tr>");
						out.println("</table>");
						//out.println("<br>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Pricing No</b></td>");
						out.println("<td width='25%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_pricing_drill('"+rs.getString(6)+"')><u>"+rs.getString(6)+"</u></td>");
						out.println("<td width='20%' class=div_input><b>Option ID</b></td>");
						out.println("<td width='*%' class=div_input>"+rs.getString(7)+"</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Quantity</b></td>");
						out.println("<td width='25%' class=div_input>"+rs.getString(8)+"</td>");
						out.println("<td width='20%' class=div_input><b>Gross Amount</b></td>");
						out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(9))+"</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>VAT Amount</b></td>");
						out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(10))+"</td>");
						out.println("<td width='20%' class=div_input><b>Gross Rental</b></td>");
						out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(11))+"</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>VAT Rental</b></td>");
						out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(12))+"</td>");
						out.println("<td width='20%' class=div_input><b>Redual Amount</b></td>");
						out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(13))+"</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Period</b></td>");
						out.println("<td width='25%' class=div_input>"+rs.getString(14)+"</td>");
						out.println("<td width='20%' class=div_input></td>");
						out.println("<td width='*%' class=div_input></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<br>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Condition of Asset</b></td>");
						out.println("<td width='25%' class=div_input>"+rs.getString(16)+"</td>");
						out.println("<td width='20%' class=div_input></td>");
						out.println("<td width='*%' class=div_input></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Make</b></td>");
						out.println("<td width='25%' class=div_input>"+rs.getString(18)+"</td>");
						out.println("<td width='20%' class=div_input><b></b></td>");
						out.println("<td width='*%' class=div_input></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Model</b></td>");
						out.println("<td width='25%' class=div_input>"+rs.getString(20)+"</td>");
						out.println("<td width='20%' class=div_input><b></b></td>");
						out.println("<td width='*%' class=div_input></td>");
						out.println("</tr>");
						out.println("</table>");
						more_dir = rs.next();
					}
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_INQUIRY_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_inquiry_no=req.getParameter("inquiry_no");					
				
				
				rs= stmt1.executeQuery(" SELECT "+
					"  A.INQUIRY_CODE,"+//1
					"  NVL(DECODE(A.STATUS,'Y','Entered','REJECT','Rejected','VERIFY2','Verified'),'-'),"+//2
					"  NVL(DECODE(A.INQUIRY_STATUS,'INQUIRY','Inquiry','QOT-SENT','Quotation-Sent','QOT-APP','Quotation-Approved','QOT-CAN','Quotation-Cancelled'),'-'),"+//3
					"  NVL(INITCAP(A.INITIATION_TYPE),'-'),"+//4
					"  NVL(INITCAP(A.CLIENT_CATEGORY),'-'),"+//5
					"  NVL(INITCAP(A.LEGAL_ENTITY),'-'),"+//6
					"  NVL(A.LEAD_SOURCE_CATEGORY,'-'),"+//7
					"  NVL(B.NAME,'-'),"+//8
					"  NVL(A.LEAD_SOURCE_NAME,'-'),"+//9
					//customer details
					"  NVL(A.TITLE,' '),"+//10
					"  NVL(A.CLIENT_NAME,' '),"+//11
					"  NVL(A.CLIENT_LAST_NAME,' '),"+//12
					"  NVL(A.TEL_NO,'-'),"+//13
					"  NVL(A.MOBILE_NO,'-'),"+//14
					"  NVL(A.FAX_NO,'-'),"+//15
					"  NVL(A.ADDRESS,'-'),"+//16
					"  NVL(A.ADDRESS2,'-'),"+//17
					"  NVL(A.CITY_CODE,'-'),"+//18
					"  NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),'-'),"+//19
					"  NVL(A.ID_NO,'-'),"+ //20
					"  NVL(A.CONTACT_PERSON,'-'),"+//21
					//other infor
					"  NVL(A.INTRODUCER,'-'),"+//22
					"  NVL(A.SUB_PRODUCT_CODE,'-'),"+//23
					"  NVL("+m_schema_name+".AF_CO_GET_TRAN_TYPE_DESC(A.SUB_PRODUCT_CODE),'-'),"+//24
					"  NVL(A.TRANSACTION_SUB_TYPE,'-'),"+//25
					"  NVL("+m_schema_name+".AF_CO_GET_TRAN_SUB_TYPE_DESC(A.TRANSACTION_SUB_TYPE),'-'),"+//26
					"  NVL(A.EMAIL,'-'),"+//27
					"  NVL(A.TEAM,'-'),"+//28
					"  NVL("+m_schema_name+".AF_CO_GET_TEAM_DESC(A.TEAM),'-'),"+//29
					"  NVL(A.MK_OFFICER,'-'),"+//30
					"  NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUIRY_CODE),'-'),"+//31
					"  NVL(A.MK_SUPERVISOR,'-'),"+//32
					"  NVL("+m_schema_name+".AF_CO_GET_USER_NAME(A.MK_SUPERVISOR),'-'),"+//33
					"  NVL(A.DIVISION_CODE,'-'),"+//34
					"  NVL("+m_schema_name+".AF_CO_GET_DIVISION_DESC(A.DIVISION_CODE),'-'), "+//35
					" NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER_NAME(A.INQUIRY_CODE),'-') "+ //36 ADDED MILINDSA 2014-01-07
					" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY A,"+m_schema_name+".AF_MK_MAS_LEAD_SOURCE_CAT B "+
					" WHERE A.LEAD_SOURCE_CATEGORY=B.SOURCE_CODE(+) "+
					" AND A.INQUIRY_CODE='"+m_inquiry_no+"' ");
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Inquiry  Details - Inquiry No : "+m_inquiry_no+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Inquiry  Details - Inquiry No : "+m_inquiry_no+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Inquiry No  "+m_inquiry_no+"  </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if(more_dir){
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Inquiry No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(2)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Stage</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(3)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Initiation Type</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Client Category</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(5)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Customer Type</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Lead Source Category</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Lead Source Name</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>Customer Details</u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					//out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Customer Name</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(10)+" "+rs.getString(11)+" "+rs.getString(12)+"</td>");
					out.println("<td width='20%' class=div_input><b>Tel No</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(13)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Mobile No</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(14)+"</td>");
					out.println("<td width='20%' class=div_input><b>Fax No</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(15)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Address</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(16)+","+rs.getString(17)+"</td>");
					out.println("<td width='20%' class=div_input><b>City </b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(19)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>ID No</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(20)+"</td>");
					out.println("<td width='20%' class=div_input><b>Contact Person</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(21)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Email</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(27)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>Other Details</u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Introducer</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(22)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Transaction Type</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(24)+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Transaction Sub Type</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(26)+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Team</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(29)+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Marketing Officer</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(31)+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					//ADDED MILINDA 2014-01-07
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Credit Officer</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(36)+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Marketing Supervisor</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(32)+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Division</b></td>");
					out.println("<td width='25%' class=div_input><b>"+rs.getString(35)+"</b></td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_VALUATION_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_valuation_no=req.getParameter("valuation_no");					
				
				
				
				rs= stmt1.executeQuery(" SELECT "+
					"  A.VALUATION_NO,"+//1
					"  NVL(A.ASSET_ID,'-'),"+//2
					"  NVL(A.APPLICATION_NO,'-'),"+//3
					"  NVL(A.PRO_INVOICE_NO,'-'),"+//4
					"  NVL(A.INVENTORY_NO,'-'),"+//5
					"  NVL(A.SUB_MODEL_CODE,'-'),"+//6
					"  NVL(B.DESCRIPTION,'-'),"+//7
					"  NVL(A.REG_NO,'-'),"+//8
					"  NVL(A.ENGINE_NO,'-'),"+//9
					"  NVL(A.CHASSIS_NO,'-'),"+//10
					"  NVL(A.COLOUR,'-'),"+//11
					"  NVL(A.MODEL_CODE,'-'),"+//12
					"  NVL(C.DESCRIPTION,'-'),"+//13
					"  NVL(A.NOTES,'-'),"+//14
					"  NVL(A.REMARKS,'-'),"+//15
					"  NVL(TO_CHAR(A.VALUATION_DATE,'DD-MM-YYYY'),'-'),"+//16
					"  NVL(A.VALUE,0),"+//17
					"  NVL(A.TYPE_OF_BODY,'-'),"+//18
					"  NVL(TO_CHAR(A.DATE_OF_REG,'DD-MM-YYYY'),'-'),"+//19
					"  NVL(A.METER_READING,0),"+//20
					"  NVL(DECODE(A.ACTIVE_STATUS,'Y','Active','C','Cancel'),'-'),"+//21
					"  NVL(DECODE(A.GENERAL_INDEX,1,'Faultless',2,'Good',3,'Serviceable',4,'Fair',5,'Poor',6,'Unserviceable'),0),"+//22
					"  NVL(A.SEATING_CAPACITY,0),"+//23
					"  NVL(A.NO_OF_CYLINDERS,0),"+//24
					"  NVL(A.VALUER_CODE,'-'),"+//25
					"  NVL(G.FIRST_NAME,'-'),"+//26
					"  NVL(G.LAST_NAME,'-'),"+//27
					"  NVL(A.YEAR_OF_MANUFACTURE,0),"+//28
					"  NVL(A.CONDITION_OF_ASSET,'-'),"+//29
					"  NVL(D.DESCRIPTION,'-'),"+//30
					"  NVL(A.FORCED_SALES_VALUE,0),"+//31
					"  NVL(A.DISP_USER,'-'),"+//32
					"  NVL(TO_CHAR(A.DISP_DATE,'DD-MM-YYYY'),'-'),"+//33
					"  NVL(A.DISP_COMMENTS,'-'),"+//34
					"  NVL(DECODE(A.DISP_STATUS,'Y','Yes','N','No'),'-'),"+//35
					//repeating
					"  NVL(E.FILED_CODE,'-'),"+//36
					"  NVL(F.DESCRIPTION,'-'),"+//37
					"  NVL(E.STATUS,'-'),"+//38
					"  NVL(E.REMARK,'-')"+//39
					" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION A,"+m_schema_name+".AF_CO_MAS_SUB_MODLE B,"+m_schema_name+".AF_CO_MAS_MODEL C, "+
					" "+m_schema_name+".AF_CO_MAS_CONDITION_OF_ASSET D,"+m_schema_name+".AF_CO_PRO_APP_VALUATION_DET E, "+
					" "+m_schema_name+".AF_CO_MAS_FILEDS F,"+m_schema_name+".AF_CO_MAS_VALUERS G "+
					" WHERE A.SUB_MODEL_CODE=B.SUB_CODE(+) "+
					" AND A.MODEL_CODE=C.MODEL_CODE(+) "+
					" AND A.CONDITION_OF_ASSET=D.CODE(+) "+
					" AND A.VALUATION_NO=E.VALUATION_NO(+) "+
					" AND E.FILED_CODE=F.FILED_CODE(+) "+
					" AND A.VALUER_CODE=G.VALUER_CODE(+) "+
					" AND A.VALUATION_NO='"+m_valuation_no+"' ");
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Valuation  Details - Valuation No : "+m_valuation_no+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Valuation  Details - Valuation No : "+m_valuation_no+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Valuation No  "+m_valuation_no+"  </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if(more_dir){
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Valuation No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(21)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Asset ID</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_asset_detail_drill('"+rs.getString(2)+"')><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Application No</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_application_detail_drill('"+rs.getString(3)+"')><u>"+rs.getString(3)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_proforma_invoice_drill('"+rs.getString(4)+"')><u>"+rs.getString(4)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Inventory No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Model</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(13)+"</td>");
					out.println("<td width='20%' class=div_input><b>Sub Model</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Registration No</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='20%' class=div_input><b>Engine No</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Chassis No</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(10)+"</td>");
					out.println("<td width='20%' class=div_input><b>Colour</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(11)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='30%' class=div_input><b>Notes</b></td>"); // commented by udara 25-05-2015
					out.println("<td width='30%' class=div_input><b>No of CR</b></td>"); // added by udara 25-05-2015
					out.println("<td width='25%' class=div_input>"+rs.getString(14)+"</td>");
					out.println("<td width='20%' class=div_input><b>Remarks</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(15)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Valuation Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(16)+"</td>");
					out.println("<td width='20%' class=div_input><b>Value</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(17))+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Type of Body</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(18)+"</td>");
					out.println("<td width='20%' class=div_input><b>Date of Registration</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(19)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Meter Reading</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(20))+"</td>");
					out.println("<td width='20%' class=div_input><b>General Index(Engineer's Summary)</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(22)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Seating Capacity</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(23)+"</td>");
					out.println("<td width='20%' class=div_input><b>Cubic Capacity</b></td>"); //No. of Cylinders
					out.println("<td width='*%' class=div_input>"+rs.getString(24)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Valuer Code</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(25)+"</td>");
					out.println("<td width='20%' class=div_input><b>Valuer Name</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(26)+" "+rs.getString(27)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Year of Manufacture</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(28)+"</td>");
					out.println("<td width='20%' class=div_input><b>Condition of Asset</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(30)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Forced Sales Value</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(31)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Disapproved Status</b></td>");
					out.println("<td width='25%' class=div_input><b>"+rs.getString(35)+"</b></td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Disapproved User</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(32)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Disapproved Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(33)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Disapproved Comment</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(34)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Product Code</b></td>");
					out.println("<td width='25%' class=div_input><b>Product Desc.</b></td>");
					out.println("<td width='20%' class=div_input><b>Product Status</b></td>");
					out.println("<td width='*%' class=div_input><b>Remarks</b></td>");
					out.println("</tr>");
					out.println("</table>");
					
					while(more_dir) {
						count++;
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input>"+rs.getString(36)+"</td>");
						out.println("<td width='25%' class=div_input>"+rs.getString(37)+"</td>");
						out.println("<td width='20%' class=div_input>"+rs.getString(38)+"</td>");
						out.println("<td width='*%' class=div_input>"+rs.getString(39)+"</td>");
						out.println("</tr>");
						out.println("</table>");
						more_dir = rs.next();
					}
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_PURCHASE_ORDER_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_purchase_order_no=req.getParameter("purchase_order_no");					
				
				
				
				rs= stmt1.executeQuery(" SELECT DISTINCT "+
					"  A.PURCHASE_ORDER_NO,"+//1
					"  NVL(DECODE(A.ACTIVE_STATUS,'CANCEL','Cancel','VERIFY','Verified','ENT','Entered'),'-'),"+//2
					"  NVL(DECODE(A.APPROVAL_STATUS,'Y','Yes','N','No'),'-'),"+//3
					"  NVL(A.APPLICATION_NO,'-'),"+//4
					"  NVL(B.ASSET_ID,'-'),"+//5
					"  NVL(A.VENDER_CODE,'-'),"+//6
					"  NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(A.VENDER_CODE),'-'),"+//7
					"  NVL(TO_CHAR(A.ISSUED_DATE,'DD-MM-YYYY'),'-'),"+//8
					"  NVL(TO_CHAR(A.DRIVING_LICENSE_DATE,'DD-MM-YYYY'),'-'),"+//9
					"  NVL(A.TOTAL_NET,0),"+//10
					"  NVL(A.TOTAL_VAT,0),"+//11
					"  NVL(TO_CHAR(A.PURCHASE_ORDER_DATE,'DD-MM-YYYY'),'-'),"+//12
					"  NVL(A.CURR_CODE,'-'),"+//13
					"  NVL(A.EXCHANGE_RATE,0),"+//14
					"  NVL(A.ACC_NO,'-'),"+//15
					"  NVL(A.BRANCH_CODE,'-'),"+//16
					"  NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.BRANCH_CODE),'-'),"+//17
					"  NVL(TO_CHAR(A.PRINTED_DATE,'DD-MM-YYYY'),'-'),"+//18
					"  NVL(A.PRINTED_USER,'-'),"+//19
					"  NVL(TO_CHAR(A.SUPPLIER_APPROVAL_DATE,'DD-MM-YYYY'),'-'),"+//20
					//repeating
					"  NVL(B.PRO_INVOICE_NO,'-'),"+//21
					"  NVL(B.ENGINE_NO,'-'),"+//22
					"  NVL(B.CHASSIS_NO,'-'),"+//23
					"  NVL(C.NET_PRICE,0),"+//24
					"  NVL(C.VAT,0),"+//25
					"  NVL(C.TOTAL_AMOUNT,0) "+//26
					
					" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A "+
					" ,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET B,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
					" WHERE A.PURCHASE_ORDER_NO=B.PURCHASE_ORDER_NO(+)"+
					" AND B.PRO_INVOICE_NO=C.INVOICE_NO(+)"+ //Modified By Nuwan De Silva 29-05-07
					" AND A.PURCHASE_ORDER_NO='"+m_purchase_order_no+"'");
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Purchase Order  Details - Purchase Order No : "+m_purchase_order_no+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Purchase Order  Details - Purchase Order No : "+m_purchase_order_no+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Purchase Order No  "+m_purchase_order_no+"  </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if(more_dir){
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Purchase Order No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(2)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Approval Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(3)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Application No</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_application_detail_drill('"+rs.getString(4)+"')><u>"+rs.getString(4)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					//Modified By Nuwan De Silva 29-05-07
					//out.println("<tr>");
					//out.println("<td width='1%'></td>"); 
					//out.println("<td width='30%' class=div_input><b>Asset ID</b></td>");
					//out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_asset_detail_drill('"+rs.getString(5)+"')><u>"+rs.getString(5)+"</u></td>");
					//out.println("<td width='*%'></td>");
					//out.println("</tr>");
					
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='%' class=div_input><b><u>Vendor Details</u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Vendor Code</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='20%' class=div_input><b>Vendor Name</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Branch ID</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(16)+"</td>");
					out.println("<td width='20%' class=div_input><b>Branch Name</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(17)+"</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Issued Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='20%' class=div_input><b>Driving License Date</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Total Net Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(10))+"</td>");
					out.println("<td width='20%' class=div_input><b>Total VAT</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(11))+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Purchase Order Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(12)+"</td>");
					out.println("<td width='20%' class=div_input><b>Currency</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(13)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Exchange Rate</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(14))+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Account No</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(15)+"</td>");
					out.println("<td width='20%' class=div_input><b>Branch Name</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(17)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Printed Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(18)+"</td>");
					out.println("<td width='20%' class=div_input><b>Printed User</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(19)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Supplier Approval Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(20)+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>Invoice Details</u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='15%' class=div_input><b>Asset ID</b></td>");
					out.println("<td width='12%' class=div_input><b>Engine No</b></td>");
					out.println("<td width='12%' class=div_input><b>Chassis No</b></td>");
					out.println("<td width='15%' align='right' class=div_input><b>Net Amount</b></td>");
					out.println("<td width='15%' align='right' class=div_input><b>VAT Amount</b></td>");
					out.println("<td width='15%' align='right' class=div_input><b>Tot Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
					
					
					while(more_dir) {
						count++;
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_proforma_invoice_drill('"+rs.getString(21)+"')><u>"+rs.getString(21)+"</u></td>");
						out.println("<td width='15%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_asset_detail_drill('"+rs.getString(5)+"')><u>"+rs.getString(5)+"</u></td>");
						out.println("<td width='12%' class=div_input>"+rs.getString(22)+"</td>");
						out.println("<td width='12%' class=div_input>"+rs.getString(23)+"</td>");
						out.println("<td width='15%' align='right' class=div_input>"+nf.format(rs.getDouble(24))+"</td>");
						out.println("<td width='15%' align='right' class=div_input>"+nf.format(rs.getDouble(25))+"</td>");
						out.println("<td width='15%' align='right' class=div_input>"+nf.format(rs.getDouble(26))+"</td>");
						out.println("</tr>");
						out.println("</table>");
						more_dir = rs.next();
					}
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_DEPOSIT_DETAIL_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_deposit_no=req.getParameter("deposit_no");					
				
				
				rs= stmt1.executeQuery(" SELECT "+
					"  A.DIPOSIT_NO,"+//1
					"  NVL(DECODE(A.STATUS,'Y','Yes','N','No'),'-'),"+//2
					"  NVL(TO_CHAR(A.DIPOSIT_DATE,'DD-MM-YYYY'),'-'),"+//3
					"  NVL(A.REFERENCE,'-'),"+//4
					"  NVL(A.BRANCH_CODE,'-'),"+//5
					"  NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(A.BRANCH_CODE),'-'),"+//6
					"  NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.BRANCH_CODE),'-'),"+//7
					"  NVL(A.ACC_NO,'-'),"+//8
					"  NVL(B.RECEIPT_NO,'-'),"+//9
					"  NVL(B.AMOUNT,0) "+//10
					" FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT A,"+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS B "+
					" WHERE A.DIPOSIT_NO=B.DIPOSIT_NO(+) "+
					" AND A.DIPOSIT_NO='"+m_deposit_no+"' ");
				
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Deposit  Details - Deposit No : "+m_deposit_no+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Deposit  Details - Deposit No : "+m_deposit_no+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Deposit No "+m_deposit_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Deposit No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(2)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Deposit Date</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(3)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Reference</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Bank Name</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Branch Name</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Account No</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>Receipt Details</u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Receipt No</b></td>");
					out.println("<td width='20%' align='right' class=div_input ><b>Amount</b></td>");
					out.println("<td width='*%'></td>"); 
					out.println("</tr>");
					out.println("</table>");
					
					while(more_dir) {
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_settle_receipt_drill('"+rs.getString(9)+"')><u>"+rs.getString(9)+"</u></td>");
						out.println("<td width='20%' align='right' class=div_input>"+rs.getString(10)+"</td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>");
						out.println("</table>");
						more_dir = rs.next();
					}
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_DOCUMENT_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_doc_code=req.getParameter("doc_code");				
				
				
				rs= stmt1.executeQuery("SELECT "+
					"  CODE,"+
					"  NVL(DESCRIPTION,'-'),"+
					"  NVL(DECODE(ACTIVE_STATUS,'Y','Yes','N','No'),'-'),"+
					"  NVL(INITCAP(DOC_APP_TYPE),'-')"+
					" FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED "+
					" WHERE CODE='"+m_doc_code+"' ");
				
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Document  Details - Document Code : "+m_doc_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Document  Details - Document Code : "+m_doc_code+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Document Code "+m_doc_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Document Code</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(3)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Description</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Applicable Doc. Type</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_RETURN_DETAIL_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_return_no=req.getParameter("return_no");					
				
				rs= stmt1.executeQuery(" SELECT "+
					"  A.RETURN_NO,"+//1
					"  NVL(DECODE(C.STATUS,'E','Entered','B','Bank','C','Cancel','REC','Receipt','RET','Return'),'-'),"+//2
					"  NVL(DECODE(A.ALLO_RECEIPT_STATUS,'Y','Yes','N','No'),'-'),"+//3
					"  NVL(A.DIPOSIT_NO,'-'),"+//4
					"  NVL(A.RECEIPT_NO,'-'),"+//5
					"  NVL(A.AMOUNT,0),"+//6
					"  NVL(C.BRANCH_CODE,'-'),"+//7
					"  NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(C.BRANCH_CODE),'-'),"+//8
					"  NVL(C.ACC_NO,'-'),"+//9
					"  NVL(A.ALLOCATED_AMOUNT,0),"+//10
					"  NVL(A.BAL_AMOUNT,0),"+//11
					//Repeating
					"  NVL(B.ALLO_RECEIPT_NO,'-'),"+//12
					"  NVL(B.AMOUNT,0),"+//13
					"  NVL(INITCAP(B.STATUS),'-') "+//14
					" FROM "+m_schema_name+".AF_CO_PRO_RETURN_DETAILS A,"+m_schema_name+".AF_CO_PRO_RETURN_REC_ALL_DET B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C "+
					" WHERE A.RETURN_NO=B.RETURN_NO(+) AND A.RECEIPT_NO=C.REC_NO(+) "+
					" AND A.RETURN_NO='"+m_return_no+"' ");
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Return Details - Return No : "+m_return_no+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Return Details - Return No : "+m_return_no+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Return No "+m_return_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Return No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Receipt Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(2)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Allo. Receipt Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(3)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Deposit No</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_deposit_drill('"+rs.getString(4)+"') ><u>"+rs.getString(4)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Receipt No</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_settle_receipt_drill('"+rs.getString(5)+"') ><u>"+rs.getString(5)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Amount</b></td>");
					out.println("<td width='50%' class=div_input >"+nf.format(rs.getDouble(6))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Branch Name</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Account No</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(9)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Allocated Amount</b></td>");
					out.println("<td width='50%' class=div_input >"+nf.format(rs.getDouble(10))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Balance Amount</b></td>");
					out.println("<td width='50%' class=div_input >"+nf.format(rs.getDouble(11))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>Allocation Receipt Details</u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Allo. Receipt No</b></td>");
					out.println("<td width='20%' align='right' class=div_input ><b>Amount</b></td>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Status</b></td>");
					out.println("<td width='*%'></td>"); 
					out.println("</tr>");
					out.println("</table>");
					
					while(more_dir) {
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_settle_receipt_drill('"+rs.getString(12)+"')><u>"+rs.getString(12)+"</u></td>");
						out.println("<td width='20%' align='right' class=div_input>"+nf.format(rs.getDouble(13))+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input>"+rs.getString(14)+"</td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>");
						out.println("</table>");
						more_dir = rs.next();
					}
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_PAYMENT_DRILL")) {
				
				int count = 0;
				String m_string="";								
				String m_payment_no=req.getParameter("payment_no");					
				
		/* Commented by Kanchana on 2015/11/30		
				rs= stmt1.executeQuery(" SELECT "+
					"  PAYMENT_NO,"+//1
					"  NVL(SUS_REF_NO,'-'),"+//2
					"  NVL(DECODE(PROCESS_STATUS,'RE-APP','Payment Requsition','PRINT','Print','CANCEL','Cancel','DISBRS','Disbursed','TEMP','Temp','APPRO1','Approve Level 1','APPRO2','Approve Level 2'),'-'),"+//3
					"  NVL(DECODE(RECON_STATUS,'Y','Yes','N','No'),'-'),"+//4
					"  NVL(CLIENT_CODE,'-'),"+//5
					"  NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-'),"+//6
					"  NVL(SETTLE_MODE,'-'),"+//7
					"  NVL(DECODE(ENTRY_TYPE,'S','Supplier','E','Seizer','V','Vendor'),'-'),"+//8
					"  NVL(PAY_AMOUNT,0),"+//9
					"  NVL(LIC_BRANCH_CODE,'-'),"+//10
					"  NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(LIC_BRANCH_CODE),'-'),"+//11
					"  NVL(LIC_ACC_NO,'-'),"+//12
					"  NVL(PAYEE_BRANCH_CODE,'-'),"+//13
					"  NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYEE_BRANCH_CODE),'-'),"+//14
					"  NVL(PAYEE_ACC_NO,'-'),"+//15
					"  NVL(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'-'),"+//16
					"  NVL(RECON_BY,'-'),"+//17
					"  NVL(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'-'),"+//18
					"  NVL(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'-'),"+//19
					"  NVL(PAYEE_NAME,'-'),"+//20
					"  NVL(PAY_AMOUNT_CURR,0),"+//21
					"  NVL(EXCHANGE_RATE_BANK,0),"+//22
					"  NVL(EXCHANGE_RATE_REP_CURR,0),"+//23
					"  NVL(REC_AMMOUNT_REP_CURR,0),"+//24
					"  NVL(EXCHANGE_GAIN_LOSS,0),"+//25
					"  NVL(COMMENTS,'-'),"+//26
					"  NVL(DISBURSE_TO,'-'),"+//27
					"  NVL(CHEQUE_NO,'-'),"+//28
					"  NVL(TO_CHAR(LETTER_DATE,'DD-MM-YYYY'),'-'), "+//29
					"  NVL(WHT,0),"+//30
					"  NVL(NET_AMOUNT,0), "+//31
					"  NVL(TO_CHAR(DISBURSE_DATE,'DD-MM-YYYY'),'-'), "+//32
					"  NVL(BRAC_VOU_NO,'-'), "+//33 added by prabash on 27-10-2014
					"  NVL(FINANCE_NO,'-') "+ //34 Added by Kanchana on 30/11/2015
					" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
					" WHERE PAYMENT_NO='"+m_payment_no+"' ");
			Commented by Kanchana on 2015/11/30		 */	
		//Modified by Kanchana 
		rs= stmt1.executeQuery(" SELECT "+
					"  C.PAYMENT_NO,"+//1
					"  NVL(C.SUS_REF_NO,'-'),"+//2
					"  NVL(DECODE(C.PROCESS_STATUS,'RE-APP','Payment Requsition','PRINT','Print','CANCEL','Cancel','DISBRS','Disbursed','TEMP','Temp','APPRO1','Approve Level 1','APPRO2','Approve Level 2'),'-'),"+//3
					"  NVL(DECODE(C.RECON_STATUS,'Y','Yes','N','No'),'-'),"+//4
					"  NVL(C.CLIENT_CODE,'-'),"+//5
					"  NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE),'-'),"+//6
					"  NVL(C.SETTLE_MODE,'-'),"+//7
					"  NVL(DECODE(C.ENTRY_TYPE,'S','Supplier','E','Seizer','V','Vendor'),'-'),"+//8
					"  NVL(C.PAY_AMOUNT,0),"+//9
					"  NVL(C.LIC_BRANCH_CODE,'-'),"+//10
					"  NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(C.LIC_BRANCH_CODE),'-'),"+//11
					"  NVL(C.LIC_ACC_NO,'-'),"+//12
					"  NVL(C.PAYEE_BRANCH_CODE,'-'),"+//13
					"  NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(C.PAYEE_BRANCH_CODE),'-'),"+//14
					"  NVL(C.PAYEE_ACC_NO,'-'),"+//15
					"  NVL(TO_CHAR(C.RECON_DATE,'DD-MM-YYYY'),'-'),"+//16
					"  NVL(C.RECON_BY,'-'),"+//17
					"  NVL(TO_CHAR(C.EFF_VALDATE,'DD-MM-YYYY'),'-'),"+//18
					"  NVL(TO_CHAR(C.REALISED_DATE,'DD-MM-YYYY'),'-'),"+//19
					"  NVL(C.PAYEE_NAME,'-'),"+//20
					"  NVL(C.PAY_AMOUNT_CURR,0),"+//21
					"  NVL(C.EXCHANGE_RATE_BANK,0),"+//22
					"  NVL(C.EXCHANGE_RATE_REP_CURR,0),"+//23
					"  NVL(C.REC_AMMOUNT_REP_CURR,0),"+//24
					"  NVL(C.EXCHANGE_GAIN_LOSS,0),"+//25
					"  NVL(C.COMMENTS,'-'),"+//26
					"  NVL(C.DISBURSE_TO,'-'),"+//27
					"  NVL(C.CHEQUE_NO,'-'),"+//28
					"  NVL(TO_CHAR(C.LETTER_DATE,'DD-MM-YYYY'),'-'), "+//29
					"  NVL(C.WHT,0),"+//30
					"  NVL(C.NET_AMOUNT,0), "+//31
					"  NVL(TO_CHAR(C.DISBURSE_DATE,'DD-MM-YYYY'),'-'), "+//32
					"  NVL(C.BRAC_VOU_NO,'-'), "+//33 added by prabash on 27-10-2014
					"  NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(A.REF_NO)),' ') "+ //34 Added by Kanchana on 30/11/2015
					" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A ,"+
					" "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN B, "+
                    " "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT C "+
                    " WHERE A.SUS_REF_NO = B.SUS_REF_NO "+ 
                    " AND C.PAYMENT_NO = B.PAYMENT_NO AND "+ 
					" B.PAYMENT_NO='"+m_payment_no+"' ");
		
		
		
		
		
		
		
		
				
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE>Payment Details - Payment No : "+m_payment_no+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Payment Details - Payment No : "+m_payment_no+" </B></TD></TR>");
				out.println("</TABLE>");
				
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Payment No  "+m_payment_no+"  </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Payment No</b></td>");
					out.println("<td width='50%' class=div_input onClick=\"\" style='cursor:hand' >"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					//---Added by Kanchana on 30/11/2015--------------------
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Finance No</b></td>");
					out.println("<td width='50%' class=div_input onClick=\"\" style='cursor:hand' >"+rs.getString(34)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					//---------------------------------------------------------
					//---Added by Prabash on 27-10-2014--------------------
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Branch Account VC Number</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(33)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					//----------------------------------------------------
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Sus Ref No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Process Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(3)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Recon. Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(4)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Client Name</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(5)+"')><u>"+rs.getString(6)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Settle Mode</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Entry Type</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Pay Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(9))+"</td>");
					out.println("<td width='20%' class=div_input><b>Licensee Branch</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(11)+"</td>");
					out.println("</tr>");
					//-------------------------------Added By Sandun on 31-12-2008----------------------
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>WHT</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(30))+"</td>");
					out.println("<td width='20%' class=div_input>&nbsp;</b></td>");
					out.println("<td width='*%' class=div_input>&nbsp;</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(31))+"</td>");
					out.println("<td width='20%' class=div_input>&nbsp;</b></td>");
					out.println("<td width='*%' class=div_input>&nbsp;</td>");
					out.println("</tr>");
					//-----------------------------------------------------------------------------------
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Licensee Account No</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(12)+"</td>");
					out.println("<td width='20%' class=div_input><b>Recon. Date</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(16)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					//-------Added by Sandun on 21-05-2009---------------
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input>&nbsp;</td>");
					out.println("<td width='20%' class=div_input><b>Disburse Date</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(32)+"</td>");
					out.println("</tr>");
					//------------------------------------------
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Recon. By</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(17)+"</td>");
					out.println("<td width='20%' class=div_input><b>EFF Value Date</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(18)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Realised Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(19)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Payee Name</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(20)+"</td>");
					out.println("<td width='20%' class=div_input><b>Payee Branch</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(14)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Payee Account No</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(15)+"</td>");
					out.println("<td width='20%' class=div_input><b>Pay Amount Curr.</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(21))+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Exchange Rate Bank</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(22))+"</td>");
					out.println("<td width='20%' class=div_input><b>Ex. Rate Reportin Curr.</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(23))+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Reporting Curr. Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(24))+"</td>");
					out.println("<td width='20%' class=div_input><b>Exchange Gain Loss</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(25))+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Comments</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(26)+"</td>");
					out.println("<td width='20%' class=div_input><b>Disburse To</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(27)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Cheque No</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(28)+"</td>");
					out.println("<td width='20%' class=div_input><b>Letter Date</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(29)+"</td>");
					out.println("</tr>");
					out.println("</table>");
					more_dir = rs.next();
				}
				
				
				
				// added by udara 18-03-2015
				out.println("<br> ");
				out.println("<table align='left' width='100%' class='table' border='0'>");
				
				String n_sql = "";
				
				/*
				n_sql = " select "+
								//" PROCESS_STATUS, "+
								
								" NVL(DECODE(PROCESS_STATUS, "  +
								"   'AUTHO', 'Entered', " + 
			                    "   'RE-APP', 'Payment Requsition', " +
			                    "   'RE-APP-REVERSE', 'Payment Requsition Reversal', " +
			                    "   'CANCEL', 'Cancel', " +
			                    "   'DISBRS', 'Disbursed', " +
			                    "   'TEMP', 'Temp', " +
			                    "   'APPRO1','Approve Level 1', " +
			                    "   'APPRO1-REVERSE','Approve Level 1 Reversal', " +
			                    "   'APPRO2', 'Approve Level 2', " +
			                    "   'APPRO2-REVERSE', 'Approve Level 2 Reversal', " +
			                    "   'PRINT', 'Print', " +
			                    "   'PRINT-REVERSE', 'Print Reversal', " +
			                    "   'INVALID') " +
			                    " , '-') PROCESS_STATUS, "+
								
								
								" REQ_USER, TO_CHAR(REQ_DATE,'DD-MM-YYYY HH:MI:SS') "+ 
									" from ( "+
									  " select  "+
									  " PROCESS_STATUS, NVL(MOD_USER,ENT_USER) REQ_USER, NVL(MOD_DATE,ENTDATE) REQ_DATE "+
									  " from "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT_BK "+
									  " where PAYMENT_NO='"+m_payment_no+"' "+
									  " UNION "+
									  " select "+
									  " PROCESS_STATUS, NVL(MOD_USER,ENT_USER) REQ_USER, NVL(MOD_DATE,ENTDATE) "+ 
									  " from "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
									  " where PAYMENT_NO='"+m_payment_no+"' "+
									" ) "+
									" ORDER BY REQ_DATE "+
								" ";
				*/
				
				// commented by udara 10-07-2015
				/*
				n_sql = " SELECT  "+ 
									 " NVL(DECODE(PROCESS_STATUS,  "+ 
				                          " 'AUTHO', 'Other Payments Processing',  "+
				                          " 'ENTER', 'Other Payments Authorization',  "+
				                          " 'Y', 'Other Payments Account Selection',  "+
				                          " 'APPRO1', 'Payment Approval 1',  "+
				                          " 'APPRO2', 'Payment Approval 2',  "+
				                          " 'PRINT', 'Cheque Printing',   "+
				                          " 'DISBRS', 'Cheque Disbursement',  "+
				                          " 'REQ_GEN', 'Payment Requisition Generation', "+
				                          " 'RE-APP', 'Payment Requisition Processing', "+ // " 'REQ_PROC', 'Payment Requisition Processing', "+
											            " 'OTHER')  "+
											          " , '-') APP_STATUS,  "+

						   			" TO_CHAR(ENT_DATE,'DD-MM-YYYY HH:MI:SS') ENT_DATE, "+ 
						   			" ENT_USER FROM ( "+
								       	   " SELECT  PROCESS_STATUS PROCESS_STATUS, MAX(ENT_DATE) ENT_DATE, ENT_USER ENT_USER "+
								       	   " FROM (     "+   
									           " SELECT A.PROCESS_STATUS PROCESS_STATUS,NVL(A.MOD_DATE,A.ENTDATE) ENT_DATE, NVL(A.MOD_USER,A.ENT_USER) ENT_USER "+
									           " FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
									           " WHERE PAYMENT_NO='"+m_payment_no+"'  "+         
									           " UNION          "+
									           " SELECT A.PROCESS_STATUS PROCESS_STATUS,NVL(A.MOD_DATE,A.ENTDATE) ENT_DATE, NVL(A.MOD_USER,A.ENT_USER) ENT_USER "+
									           " FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A "+
									           " WHERE PAYMENT_NO='"+m_payment_no+"' "+
                       						   " UNION "+
						                       " SELECT 'REQ_GEN' PROCESS_STATUS,NVL(A.MOD_DATE,A.ENTDATE) ENT_DATE, NVL(A.MOD_USER,A.ENT_USER) ENT_USER  "+
												           " FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A "+
												           " WHERE PAYMENT_NO='"+m_payment_no+"' "+
						                       " AND A.PROCESS_STATUS = 'RE-APP' "+
						                       " AND A.LIC_BRANCH_CODE IS NULL "+
                       
						                       " UNION ALL "+
						                       
						                       " SELECT 'REQ_PROC' PROCESS_STATUS,NVL(A.MOD_DATE,A.ENTDATE) ENT_DATE, NVL(A.MOD_USER,A.ENT_USER) ENT_USER  "+
												           " FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A  "+
												           " WHERE PAYMENT_NO='"+m_payment_no+"' "+
						                       " AND A.PROCESS_STATUS = 'RE-APP' "+
						                       " AND A.LIC_BRANCH_CODE IS NOT NULL "+
											       " )  "+
											   " GROUP BY PROCESS_STATUS,ENT_DATE,ENT_USER "+
					               " ) "+
					               " ORDER BY ENT_DATE ";
				
				*/
				
				// added by udara 10-07-2015
				/*
				n_sql= " "+
					" SELECT "+
                    " A.REF_NO, "+
                    " A.STAGE,  "+
                    "    NVL(DECODE(APP_STATUS, " +
							"            'RE-APP', 'Payment Requsition Process', " +
							"            'RE-APP-REVERSE', 'Payment Requsition Reversal', " +
							"            'CANCEL', 'Cancel', " +
							"            'TEMP', 'Temp', " +
							"            'APPRO1','Approve Level 1', " +
							"            'APPRO1-REVERSE','Approve Level 1 Reversal', " +
							"            'APPRO2', 'Approve Level 2', " +
							"            'APPRO2-REVERSE', 'Approve Level 2 Reversal', " +
							"            'PRINT', 'Cheque Print', " +
							"            'PRINT-REVERSE', 'PaymentPrint Reversal', " +
							"            'DISBRS', 'Cheque Disburse', " +
							"            'DISBRS-REV', 'Payment Disburse Reversal', " +
							"            'ENTER', 'Other Payment Processing', " +
							"            'Y', 'Other Payment Account Selection', " +
							"            'TM_APP', 'Payment Requsition Generation', " +
							"            'OTHER') " +
							"          , '-') APP_STATUS, " +
                    " A.ENT_USER, "+
                    " TO_CHAR(A.ENT_DATE,'DD-MM-YYYY :HH:MI:SS') "+
                    " FROM   "+m_schema_name+".AF_CR_PRO_APPROVALDETAILS A "+
                    " WHERE  A.REF_NO='"+m_payment_no+"' "+
                    // " ORDER BY A.STAGE " +
                    " ORDER BY A.ENT_DATE " +
                    " ";
				*/
				//out.println(n_sql);
				
				String m_group_payment_no = "";
				
				n_sql = " "+
				" SELECT GROUP_PAYMENT_NO "+
					" FROM AF_RE_PRO_SETTLMENT_PAYMENT  "+
					" WHERE PAYMENT_NO = '"+m_payment_no+"' "+
					" ";

				rs3= stmt3.executeQuery(n_sql);
				
				while(rs3.next()){
					m_group_payment_no = rs3.getString(1);
				}
				
				
				n_sql= " "+
					" SELECT REF_NO, "+
						" STAGE, "+
						" DECODE(STAGE,'29','Payment Requsition Generation',APP_STATUS), "+
						" ENT_USER, "+
						" ENT_DATE_C, "+
						" ENT_DATE "+
						
						" FROM ( "+
						
						     " SELECT  "+
						     " A.REF_NO REF_NO,  "+
						     " A.STAGE STAGE,  "+
						     " NVL(DECODE(APP_STATUS,  "+
									            " 'RE-APP', 'Payment Requsition Process', "+
									            " 'RE-APP-REVERSE', 'Payment Requsition Reversal',  "+
									            //" 'CANCEL', 'Cancel',  "+ // commented by udara 23-07-2015
												" 'CANCEL', 'Payment Deletion',  "+ // added by udara 23-07-2015
									            " 'TEMP', 'Temp',  "+
									            " 'APPRO1','Approve Level 1',  "+
									            " 'APPRO1-REVERSE','Approve Level 1 Reversal', "+
									            " 'APPRO2', 'Approve Level 2', "+
									            " 'APPRO2-REVERSE', 'Approve Level 2 Reversal', "+
									            " 'PRINT', 'Cheque Print', "+
									            " 'PRINT-REVERSE', 'Payment Print Reversal', "+
									            " 'DISBRS', 'Cheque Disburse', "+
									            " 'DISBRS-REV', 'Payment Disburse Reversal', "+
									            //" 'ENTER', 'Other Payment Processing', "+
												" 'ENTER', 'Other Payment Authorization',  "+
												" 'ENTER-REVERSE', 'Other Payment Authorization Reversal',  "+
												" 'AUTHO', 'Other Payment Processing', "+
												" 'AUTHO-REVERSE', 'Other Payment Processing Reversal', "+
									            " 'Y', 'Other Payment Account Selection', "+
												" 'Y-REVERSE', 'Other Payment Account Selection Reversal', "+
									            " 'TM_APP', 'Payment Requsition Generation', "+
												" 'RE-PRINT', 'Re-Print', "+ // added by udara 23-07-2015
									            //" 'OTHER') "+ // commented by udara 23-07-2015
												" APP_STATUS) "+ // added by udara 23-07-2015
									          " , '-') APP_STATUS, "+
						     " A.ENT_USER ENT_USER, "+
						     " TO_CHAR(A.ENT_DATE,'DD-MM-YYYY :HH:MI:SS') ENT_DATE_C, "+
						     " A.ENT_DATE ENT_DATE "+
						     " FROM   "+m_schema_name+".AF_CR_PRO_APPROVALDETAILS A  "+
						     " WHERE  A.REF_NO = '"+m_payment_no+"'  "+
						
						     " UNION "+

						     " SELECT  "+
						     " A.REF_NO REF_NO,  "+
						     " A.STAGE STAGE,   "+
						     " NVL(DECODE(APP_STATUS, "+
									            " 'RE-APP', 'Payment Requsition Process',  "+
									            " 'RE-APP-REVERSE', 'Payment Requsition Reversal',  "+
									            //" 'CANCEL', 'Cancel',  "+ // commented by udara 23-07-2015
												" 'CANCEL', 'Payment Deletion',  "+ // added by udara 23-07-2015
									            " 'TEMP', 'Temp',  "+
									            " 'APPRO1','Approve Level 1', "+
									            " 'APPRO1-REVERSE','Approve Level 1 Reversal', "+
									            " 'APPRO2', 'Approve Level 2', "+
									            " 'APPRO2-REVERSE', 'Approve Level 2 Reversal', "+
									            " 'PRINT', 'Cheque Print', "+
									            " 'PRINT-REVERSE', 'Payment Print Reversal', "+
									            " 'DISBRS', 'Cheque Disburse', "+
									            " 'DISBRS-REV', 'Payment Disburse Reversal', "+
									            //" 'ENTER', 'Other Payment Processing', "+
												" 'ENTER', 'Other Payment Authorization',  "+
												" 'ENTER-REVERSE', 'Other Payment Authorization Reversal',  "+
												" 'AUTHO', 'Other Payment Processing', "+
												" 'AUTHO-REVERSE', 'Other Payment Processing Reversal', "+
									            " 'Y', 'Other Payment Account Selection', "+
												" 'Y-REVERSE', 'Other Payment Account Selection Reversal', "+
									            " 'TM_APP', 'Payment Requsition Generation', "+
												" 'RE-PRINT', 'Re-Print', "+ // added by udara 23-07-2015
									            //" 'OTHER') "+ // commented by udara 23-07-2015
												" APP_STATUS) "+ // added by udara 23-07-2015
									          " , '-') APP_STATUS, "+
						     " A.ENT_USER ENT_USER, "+
						     " TO_CHAR(A.ENT_DATE,'DD-MM-YYYY :HH:MI:SS') ENT_DATE_C, "+
						     " A.ENT_DATE ENT_DATE "+
						     " FROM   "+m_schema_name+".AF_CR_PRO_APPROVALDETAILS A  "+
						     " WHERE  A.REF_NO='"+m_group_payment_no+"'  "+
						     " AND (APP_STATUS <> 'DISBRS' AND APP_STATUS <> 'RE-APP')  "+
						     
						" )   "+
					
					" ORDER BY ENT_DATE ASC   "+
					" ";	
     

				
				
				
				try{
							rs3= stmt3.executeQuery(n_sql);

							int k = 0;
							while(rs3.next()){

								k++;
							
								if (k == 1) {
									out.println("<tr class=\"pdn_txtpos2\">");
									out.println("<td style=\"width: 10px; background-color: white;\">&nbsp</td>");
									out.println("<td style=\"text-align: center; width: 20px;\">No.</td>");
									out.println("<td style=\"text-align: center; width: 200px;\">Action</td>");
									out.println("<td style=\"text-align: center; width: 150px;\">User</td>");
									out.println("<td style=\"text-align: center; width: 200px;\">Date/Time</td>");
									out.println("</tr>");
								}
								
								out.println("<tr>");
								
								out.println("<td>&nbsp</td>");
								out.println("<td style=\"text-align: center;\">" + k + "</td>");
								out.println("<td style=\"text-align: left;\">" + rs3.getString(3) + "</td>");
								out.println("<td style=\"text-align: center;\">" + rs3.getString(4) + "</td>");
								out.println("<td style=\"text-align: center;\">" + rs3.getString(5) + "</td>");
								out.println("</tr>");
	
								
							}
							
							
				
				}
				catch(Exception eee){
					out.println(eee.toString());
				}
				
				out.println("</table>");
				// end by udara 18-03-2015
				
				
				
				/*
                rs= stmt1.executeQuery(" SELECT "+
                    " A.REF_NO, "+
                    " A.STAGE,  "+
                    " NVL(DECODE(APP_STATUS, "  +
                    "   'RE-APP', 'Payment Requsition', " +
                    "   'RE-APP-REVERSE', 'Payment Requsition Reversal', " +
                    "   'CANCEL', 'Cancel', " +
                    "   'DISBRS', 'Disbursed', " +
                    "   'TEMP', 'Temp', " +
                    "   'APPRO1','Approve Level 1', " +
                    "   'APPRO1-REVERSE','Approve Level 1 Reversal', " +
                    "   'APPRO2', 'Approve Level 2', " +
                    "   'APPRO2-REVERSE', 'Approve Level 2 Reversal', " +
                    "   'PRINT', 'Print', " +
                    "   'PRINT-REVERSE', 'Print Reversal', " +
                    "   'INVALID') " +
                    " , '-') PROCESS, "+
                    " A.APP_STATUS, "+
                    " A.ENT_USER, "+
                    " TO_CHAR(A.ENT_DATE,'DD-MM-YYYY :HH:MI:SS') "+
                    " FROM   "+m_schema_name+".AF_CR_PRO_APPROVALDETAILS A "+
                    " WHERE  A.REF_NO='"+m_payment_no+"' "+
                    // " ORDER BY A.STAGE " +
                    " ORDER BY A.ENT_DATE " +
                    " ");
                
                out.println("<br>");
                out.println("<table align='left' width='60%' class='table' border='1'>");
                out.println("<tr>");
                out.println("<td width='20%' class=div_input><b>Process</b></td>");
                out.println("<td width='20%' class=div_input><b>User</b></td>");
                out.println("<td width='20%' class=div_input><b>Date</b></td>");
                out.println("</tr>");
                
                while (rs.next()){
                    out.println("<tr>");
                    out.println("<td width='20%' class=div_input>"+rs.getString(3)+"</td>");
                    out.println("<td width='20%' class=div_input>"+rs.getString(5)+"</td>");
                    out.println("<td width='20%' class=div_input>"+rs.getString(6)+"</td>");
                    out.println("</tr>");
                }
                
                out.println("</table>");
                */
				/*
				out.println("<table class=\"table\" align=\"left\" border=\"0\">");
				// commented by udara 08-05-2015
				
				String sql = " " +
					"   SELECT NVL(DECODE(B.APP_STATUS, " +
					"            'RE-APP', 'Payment Requsition', " +
					"            'RE-APP-REVERSE', 'Payment Requsition Reversal', " +
					"            'CANCEL', 'Cancel', " +
					"            'TEMP', 'Temp', " +
					"            'APPRO1','Approve Level 1', " +
					"            'APPRO1-REVERSE','Approve Level 1 Reversal', " +
					"            'APPRO2', 'Approve Level 2', " +
					"            'APPRO2-REVERSE', 'Approve Level 2 Reversal', " +
					"            'PRINT', 'Payment Print', " +
					"            'PRINT-REVERSE', 'PaymentPrint Reversal', " +
					"            'DISBRS', 'Payment Disburse', " +
					"            'DISBRS-REV', 'Payment Disburse Reversal', " +
					"            'OTHER') " +
					"          , '-') APP_STATUS, " +
					"          NVL(B.ENT_USER, '-') ENT_USER, " +
					"          B.ENT_DATE " +
					"   FROM   " + m_schema_name + ".AF_RE_PRO_SETTLMENT_PAYMENT A, " +
					"          " + m_schema_name + ".AF_CR_PRO_APPROVALDETAILS B " +
					"   WHERE  A.GROUP_PAYMENT_NO = B.REF_NO " +
					"   AND    A.PAYMENT_NO = '" + m_payment_no + "' " +
					"   AND    A.GROUP_PAYMENT_NO IS NOT NULL " +
					"   GROUP BY B.APP_STATUS, " +
					"            B.ENT_USER, " +
					"            B.ENT_DATE " +
					"   ORDER BY B.ENT_DATE ASC " +
					" ";
				*/
				
				/*
				// added by udara 08-05-2015
				String sql = " "+
						" SELECT "+  
						  //" PROCESS_STATUS APP_STATUS, "+ 
							
							"    NVL(DECODE(PROCESS_STATUS, " +
							"            'RE-APP', 'Payment Requsition Process', " +
							"            'RE-APP-REVERSE', 'Payment Requsition Reversal', " +
							"            'CANCEL', 'Cancel', " +
							"            'TEMP', 'Temp', " +
							"            'APPRO1','Approve Level 1', " +
							"            'APPRO1-REVERSE','Approve Level 1 Reversal', " +
							"            'APPRO2', 'Approve Level 2', " +
							"            'APPRO2-REVERSE', 'Approve Level 2 Reversal', " +
							"            'PRINT', 'Cheque Print', " +
							"            'PRINT-REVERSE', 'PaymentPrint Reversal', " +
							"            'DISBRS', 'Cheque Disburse', " +
							"            'DISBRS-REV', 'Payment Disburse Reversal', " +
							"            'ENTER', 'Other Payment Processing', " +
							"            'Y', 'Other Payment Account Selection', " +
							"            'TM_APP', 'Payment Requsition Generation', " +
							"            'OTHER') " +
							"          , '-') APP_STATUS, " +

						  " TO_CHAR(ENT_DATE,'DD-MM-YYYY HH:MI:SS') ENT_DATE, "+ 
						  " ENT_USER FROM ( "+
						      " SELECT PROCESS_STATUS PROCESS_STATUS, MAX(ENT_DATE) ENT_DATE, ENT_USER ENT_USER "+
						      " FROM ( "+       
						          " SELECT A.PROCESS_STATUS PROCESS_STATUS,NVL(A.MOD_DATE,A.ENTDATE) ENT_DATE, NVL(A.MOD_USER,A.ENT_USER) ENT_USER "+
						          " FROM " + m_schema_name + ".AF_RE_PRO_SETTLMENT_PAYMENT A "+
						          " WHERE PAYMENT_NO='" + m_payment_no + "' "+           
						          " UNION "+         
						          " SELECT A.PROCESS_STATUS PROCESS_STATUS,NVL(A.MOD_DATE,A.ENTDATE) ENT_DATE, NVL(A.MOD_USER,A.ENT_USER) ENT_USER "+
						          " FROM " + m_schema_name + ".AF_RE_PRO_SETTLMENT_PAYMENT_BK A "+
						          " WHERE PAYMENT_NO='" + m_payment_no + "' "+      
						      " ) "+
						  " GROUP BY PROCESS_STATUS,ENT_DATE,ENT_USER "+
						" ) "+
						" ";
				
				
				// statement = connection.createStatement();
				rs = stmt1.executeQuery(sql);
				
				int j = 0;
				while (rs.next()) {
					
					j++;
					
					if (j == 1) {
						out.println("<tr class=\"pdn_txtpos2\">");
						out.println("<td style=\"width: 10px; background-color: white;\">&nbsp</td>");
						out.println("<td style=\"text-align: center; width: 20px;\">No.</td>");
						out.println("<td style=\"text-align: center; width: 200px;\">Approval Level</td>");
						out.println("<td style=\"text-align: center; width: 150px;\">Approved User</td>");
						out.println("<td style=\"text-align: center; width: 200px;\">Approved Date / Time</td>");
						out.println("</tr>");
					}
					
					out.println("<tr>");
					
					out.println("<td>&nbsp</td>");
					out.println("<td style=\"text-align: center;\">" + j + "</td>");
					out.println("<td style=\"text-align: center;\">" + rs.getString("APP_STATUS") + "</td>");
					out.println("<td style=\"text-align: center;\">" + rs.getString("ENT_USER") + "</td>");
					out.println("<td style=\"text-align: center;\">" + dateFormat2.format(rs.getTimestamp("ENT_DATE")) + "</td>");
					out.println("</tr>");
					
				}
				// resultSet.close();
				// statement.close();
				
				
				out.println("                                           </td>");
				out.println("                                       </tr>");
				
				
				
				out.println("                                   </table>");
				*/
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_DOCREF_NO_DRILL")){
				
				String m_string="";
				String m_sql="";
				String m_docref_no=req.getParameter("docref_no");
				//String m_trans_code=req.getParameter("trans_code");
				
				
				//-------------------------- DOCREF NO DRILL -------------------------------
				
				
				rs1= stmt1.executeQuery(" SELECT "+
					"  ACC_TYPE_CODE, "+//1
					"  INITCAP(PROC_DESC), "+//2
					"  NVL(CORR_ACC_NO,'-'), "+//3
					"  TO_CHAR(TRNDATE,'DD-MM-YYYY'), "+//4
					"  NVL(TRANSACTION_CODE,'-'), "+//5
					"  DRCR_STATUS, "+//6
					"  NVL(TRNAMOUNT,0) "+//7
					" FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT "+
					" WHERE DOCREFNO='"+m_docref_no+"' ");
				
				
				boolean mflag=true;							
				String client_code = "";
				boolean more = rs1.next();
				double m_cr_tot=0,m_dr_tot=0;	
				
				out.println("<HTML><HEAD><TITLE>Doc Ref Details - DocRefNo - "+m_docref_no+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("	function show_account_type(m_acc_code){");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
				out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
				out.println("	}");
				out.println("	function show_account_entry(m_trans_code){");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACCOUNT_ENTRY_DRILL&trans_code=\"+m_trans_code+\"\";");
				out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
				out.println("	}");
				out.println("</SCRIPT>");
				//out.println("<br>");
				//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B> Doc Ref Details - DocRefNo - "+m_docref_no+" </B></TD></TR>");
				out.println("</TABLE>");
				//out.println("<hr>");	
				out.println("<BR>");	
				
				if(!more){
					out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					out.println("</TABLE>");
				}					
				if(more){
					out.println("<table align='center' width='100%' class='table' >");						
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='12%' ><DIV class=div_input><b>Account Code</b></DIV></td>");
					out.println("<td width='20%' ><DIV class=div_input><b>Description</b></DIV></td>"); 
					out.println("<td width='12%' ><DIV class=div_input><b>Corres. Acc. Code</b></DIV></td>");
					out.println("<td width='12%' ><DIV class=div_input><b>Trans. Date</b></DIV></td>");
					out.println("<td width='12%' ><DIV class=div_input><b>Trans. Code</b></DIV></td>");
					out.println("<td width='12%' align='right'><DIV class=div_input><b>Debit</b></DIV></td>"); 
					out.println("<td width='12%' align='right'><DIV class=div_input><b>Credit</b></DIV></td>"); 
					out.println("</tr>"); 
				}
				
				while(more){
					
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='1%'></td>"); 
					out.println("<td width='12%' class=div_input onClick=\"show_account_type('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");
					out.println("<td width='12%' class=div_input onClick=\"show_account_type('"+rs1.getString(3)+"')\" style='cursor:hand' >"+rs1.getString(3)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs1.getString(4)+"</u></td>");
					out.println("<td width='12%' class=div_input onClick=\"show_account_entry('"+rs1.getString(5)+"')\" style='cursor:hand' ><u>"+rs1.getString(5)+"</u></td>");
					if(rs1.getString(6).equals("DR")){
						m_dr_tot=m_dr_tot+rs1.getDouble(7);
						out.println("<td width='12%' align='right' class=div_input onClick=\"\" >"+nf.format(rs1.getDouble(7))+"</td>");
					}
					else {
						out.println("<td width='12%' align='right' class=div_input onClick=\"\" >0.00</td>");
					}
					if(rs1.getString(6).equals("CR")){
						m_cr_tot=m_cr_tot+rs1.getDouble(7);
						out.println("<td width='12%' align='right' class=div_input onClick=\"\" >"+nf.format(rs1.getDouble(7))+"</td>");
					}
					else {
						out.println("<td width='12%' align='right' class=div_input onClick=\"\" >0.00</td>");
					}
					out.println("</tr>");
					more = rs1.next();
				}	
				
				out.println("</table>");
				out.println("<table align='center' width='100%' class='table' >");						
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='69%' align='center'> <B>Total</B> </td>"); 
				out.println("<td width='12%' align='right'><DIV class=div_input><b>"+nf.format(m_dr_tot)+"</b></DIV></td>"); 
				out.println("<td width='12%' align='right'><DIV class=div_input><b>"+nf.format(m_cr_tot)+"</b></DIV></td>"); 
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
				
			}
			
			
			
			else if(m_chksql.equals("SHOW_REPOSSESION_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_repossession_no=req.getParameter("repossession_no");					
				
				
				
				rs= stmt1.executeQuery(" SELECT "+
					"    REPOSSESSION_NO, "+//1
					"    NVL(DECODE(ACTIVE_STATUS,'Y','Active','Cancel'),'-'), "+//2
					"    NVL(DECODE(VEHICLE_INVENTORY_STATUS,'ENTERED','Entered','Cancel'),'-'), "+//3
					"    FINANCE_NO, "+//4
					"    INVENTORY_NO, "+//5
					"    SEIZER_CODE, "+//6
					"    LETTER_VALIDITY_PERIOD, "+ //7
					"    INVOICE_AMOUNT, "+//8
					"    INVOICE_AMOUNT_CURR, "+ //9
					"    EXCHANGE_RATE, "+//10
					"    TRN_CURR_CODE, "+//11
					"    NVL(TO_CHAR(TRN_DATE,'DD-MM-YYYY'),'-') TRN_DATE, "+//12
					"    NVL(TO_CHAR(REPOSSESSED_DATE,'DD-MM-YYYY'),'-') REPOSSESSED_DATE, "+//13
					"    NVL(TO_CHAR(EFF_VAL_DATE,'DD-MM-YYYY'),'-') EFF_VAL_DATE "+//14
					
					"    FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
					"    WHERE REPOSSESSION_NO=UPPER('"+m_repossession_no+"') ");
				
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE> Repossession  Details - Repossession ID : "+m_repossession_no+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>  Repossession  Details - Repossession ID : "+m_repossession_no+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Repossession ID "+m_repossession_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Repossession ID</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Active Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(2)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Vehivle Inventory Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(3)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Finance No</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_finance_detail_drill('"+rs.getString(4)+"')><u>"+rs.getString(4)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Inventory No</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_inventory_drill('"+rs.getString(5)+"')><u>"+rs.getString(5)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					/*out.println("<tr>");
                    out.println("<td width='1%'></td>"); 
                    out.println("<td width='30%' class=div_input><b>Seizer Code</b></td>");
                    out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_application_detail_drill('"+rs.getString(6)+"')><u>"+rs.getString(6)+"</u></td>");
                    out.println("<td width='*%'></td>");
                    out.println("</tr>");
                    */
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Seizer Code</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Letter Validity Period</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Invoice Amount</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Invoice Amount Currnet</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(9)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Exchange Rate</b></td>");
					out.println("<td width='50%' class=div_input >"+nf.format(rs.getDouble(10))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Currency Code</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(11)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Transaction Date</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(12)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Repossession Date</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(13)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Effective Value Date</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(14)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("</table>");
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			
			
			else if(m_chksql.equals("SHOW_VEHICLE_INVENTORY_DRILL")) {
				
				int count = 0;
				String m_string="";								
				String m_inventory_no=req.getParameter("inventory_no");					
				
				rs= stmt1.executeQuery(" SELECT "+
					"   INVENTORY_NO, "+ //1
					"   NVL(DECODE(ACTIVE_STATUS,'ENT','Entered','Approval'),'-'), "+ //2
					"   DECODE(ADVERTISMENT_STATUS,'GENERATED','Generated','Not Generated'), "+ //3
					"   CLIENT_CODE, "+ //4
					"   CUSTOMER_NAME, "+//5
					"   REPOSSESSION_NO, "+//6
					"   SEIZER_CODE, "+//7
					"   NVL(YARD_CODE,'-'), "+//8
					"   NVL(NO_OF_ADVERTISMENT_GEN,0), "+//9
					"   NVL(COMPLETED_OFFER_NO,'-'), "+//10
					
					//"    OFFER_STATU,
					"   VEHICLE_NO, "+//11
					"   NVL(ASSET_DESCRIPTION,'-'), "+//12
					"   NVL(COMMENTS,'-'),  "+//13
					"   NVL(MILEAGE,0), "+//14
					
					
					"   DECODE(KEY,'Y','Availabe','Not Availabe'), "+//15
					"   DECODE(LICENSE,'Y','Availabe','Not Availabe'), "+ //16
					"   DECODE(INSURANCE,'Y','Availabe','Not Availabe'), "+ //17
					"   DECODE(VEHICLE_ID_CARD,'Y','Availabe','Not Availabe'), "+ //18
					"   DECODE(CASSETTE,'Y','Availabe','Not Availabe'), "+ //19
					"   DECODE(RADIO,'Y','Availabe','Not Availabe'), "+ //20
					"   DECODE(CD_PLAYER,'Y','Availabe','Not Availabe'), "+ //21
					"   DECODE(TOOL_KIT,'Y','Availabe','Not Availabe'), "+//22
					"   DECODE(SPEAR_WHEEL,'Y','Availabe','Not Availabe'), "+ //23
					"   DECODE(JACK,'Y','Availabe','Not Availabe'), "+//24
					"   DECODE(LIGHTER,'Y','Availabe','Not Availabe'), "+//25
					"   DECODE(FUEL_CAP,'Y','Availabe','Not Availabe'), "+//26
					"   DECODE(CARPETS,'Y','Availabe','Not Availabe'), "+//27
					//"   DECODE(WHEEL,'Y','Availabe','Not Availabe'), "+//28
					"   DECODE(WHEEL,'Y','Alloy','N','Cup Set'), "+//28 //mod By Sandun on 02-07-2009
					//"   DECODE(BODY,'Y','Availabe','Not Availabe'), "+//29
					"   DECODE(BODY,'G','Good','S','Scratched','D','Damaged'),"+//29
					"   DECODE(MIRROR,'Y','Availabe','Not Availabe'), "+//30
					"   DECODE(LEFT_SIDE_MIRROR,'Y','Availabe','Not Availabe'), "+//31
					"   DECODE(RIGHT_SIDE_MIRROR,'Y','Availabe','Not Availabe'), "+//32
					"   DECODE(LEFT_SIGNAL_LIGHT_FRONT,'Y','Availabe','Not Availabe'), "+//33
					"   DECODE(RIGHT_SIGNAL_LIGHT_FRONT,'Y','Availabe','Not Availabe'), "+//34
					"   DECODE(LEFT_SIGNAL_LIGHT_REAR,'Y','Availabe','Not Availabe'), "+//35
					"   DECODE(RIGHT_SIGNAL_LIGHT_REAR,'Y','Availabe','Not Availabe'), "+//36
					"   DECODE(POLICE_REPORT,'Y','Availabe','Not Availabe'), "+//37
					"   DECODE(CUSTOMERS_SIGNATURE,'Y','Availabe','Not Availabe'), "+//38
					"   DECODE(SEIZERS_SIGNATURE,'Y','Availabe','Not Availabe'), "+//39
					"   DECODE(RECEIVERS_SIGNATURE,'Y','Availabe','Not Availabe'), "+//40
					"   DECODE(D_KEY,'Y','Availabe','Not Availabe'), "+//41 //Sandun on 02-07-2009
					"   NVL("+m_schema_name+".AF_CO_GET_EMP_NAME(SEIZER_CODE),"+m_schema_name+".AF_CO_GET_SEIZER_NAME(SEIZER_CODE)) "+//42 //Sandun on 02-07-2009
					"   FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
					"   WHERE INVENTORY_NO=UPPER('"+m_inventory_no+"')");
				
				
				
				boolean more_dir = rs.next();
				
				out.println("<HTML><HEAD><TITLE>Inventory Details - Inventory No : "+m_inventory_no+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Inventory Details - Inventory No : "+m_inventory_no+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				if (!more_dir) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Inventory No  "+m_inventory_no+"  </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Inventory No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(2)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Advertistment Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(3)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("</table>");
					
					out.println("<br>");
					
					/*out.println("<table align='center' width='100%' class='table' >");
                    out.println("<tr>");
                    out.println("<td width='1%'></td>"); 
                    out.println("<td width='*%' class=div_input><b><u>Vehicle Details</u></b></td>");
                    out.println("</tr>");
                    out.println("</table>");
                    */
					
					out.println("<table align='center' width='100%' class='table' >");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Client code</b></td>");
					out.println("<td width='25%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(4)+"')><u>"+rs.getString(4)+"</u></td>");
					out.println("<td width='20%' class=div_input><b>Repossession No</b></td>");
					out.println("<td width='*%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_repossession_drill('"+rs.getString(6)+"')><u>"+rs.getString(6)+"</u></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Siezer Code/Officer Code</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='20%' class=div_input><b>Yard Code</b></td>");
					//out.println("<td width='*%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_yard_drill('"+rs.getString(8)+"')><u>"+rs.getString(8)+"</u></td>");
					out.println("</tr>");
					out.println("<tr>");//Added By Sandun on 02-07-2009
					out.println("<td width='1%' ></td>"); 
					out.println("<td width='30%' class=div_input><b>Siezer Name/Officer Name</b></td>");
					out.println("<td width='*%' colspan=2 class=div_input>"+rs.getString(42)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>No of Advertisment Generated</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("<td width='20%' class=div_input><b>Completed Offer No</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(10)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Vehicle No</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(11)+"</td>");
					out.println("<td width='20%' class=div_input>&nbsp;</td>");
					out.println("<td width='*%' class=div_input>&nbsp;</td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<table align='center' width='100%' class='table' >");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Asset Description</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(12)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Comments</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(13)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Milage</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(14)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					
					out.println("</table>");
					
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>Vehicle Details</u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Key</b></td>");
					out.println("<td width='25%' class=div_input>&nbsp;</td>");
					out.println("<td width='20%' class=div_input><b>Licence</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(16)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); //Added By Sandun on 02-07-2009
					out.println("<td width='30%' class=div_input><li><b>Original Key</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(15)+"</td>");
					out.println("<td width='20%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='*%' class=div_input>&nbsp;</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); //Added By Sandun on 02-07-2009
					out.println("<td width='30%' class=div_input><li><b>Duplicate Key</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(41)+"</td>");
					out.println("<td width='20%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='*%' class=div_input>&nbsp;</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Insurance</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(17)+"</td>");
					out.println("<td width='20%' class=div_input><b>Vehicle ID Card</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(18)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Casstte</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(19)+"</td>");
					out.println("<td width='20%' class=div_input><b>Radio</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(20)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>CD Player</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(21)+"</td>");
					out.println("<td width='20%' class=div_input><b>Tool Kit</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(22)+"</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Spare Wheel</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(23)+"</td>");
					out.println("<td width='20%' class=div_input><b>Jack</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(24)+"</td>");
					out.println("</tr>");
					
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Lighter</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(25)+"</td>");
					out.println("<td width='20%' class=div_input><b>Fuel cap</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(26)+"</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Carpets</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(27)+"</td>");
					out.println("<td width='20%' class=div_input><b>Wheel</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(28)+"</td>");
					out.println("</tr>");
					
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Body</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(29)+"</td>");
					out.println("<td width='20%' class=div_input><b>Mirror</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(30)+"</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Left Side Mirror</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(31)+"</td>");
					out.println("<td width='20%' class=div_input><b>Left Side Mirror</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(32)+"</td>");
					out.println("</tr>");
					
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Left Signal Light Front</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(33)+"</td>");
					out.println("<td width='20%' class=div_input><b>Right Signal Light Front</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(34)+"</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Left Signal Light Rear</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(35)+"</td>");
					out.println("<td width='20%' class=div_input><b>Right Signal Light Rear</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(36)+"</td>");
					out.println("</tr>");
					
					
					
					out.println("</table>");
					
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>Other</u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Police Report</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(37)+"</td>");
					out.println("<td width='20%' class=div_input><b>Customer Signature</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(38)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Sizers Signature</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(39)+"</td>");
					out.println("<td width='20%' class=div_input><b>Receivers Signature</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(40)+"</td>");
					out.println("</tr>");
					out.println("</table>");
					
					
					
					more_dir = rs.next();
				}
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			//Added by Dineth on 15-04-2009
			else if(m_chksql.trim().equals("pay_detail")){
				
				String m_invoice_no = req.getParameter("invoice_no");			
				String m_finance_no = req.getParameter("finance_no");			
				double  m_tot=0;
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Payment Details - Invoice No : "+m_invoice_no+"</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">");
				
				out.println("</SCRIPT>");
				
				
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
				out.println("<tr><td align='center'  style='height: 18px'><b>Payment Details</td></tr>"); 
				
				out.println("<tr><td align='left'  style='height: 18px'><b>Finance No : "+m_finance_no+"</td></tr>"); 
				out.println("<tr><td align='left'  style='height: 18px'><b>Invoice No : "+m_invoice_no+"</td></tr>"); 
				
				out.println("</table>");
				out.println("<br>");
				
				rs=stmt.executeQuery( " SELECT D.PAYMENT_NO,"+
					" DECODE(D.PROCESS_STATUS,'APPRO1','Approval-1','APPRO2','Approval-1','AUTHO','Authorized By Division','CANCEL','Cancel','DISBRS','Disbursed','ENTER','Bank Allocation',D.PROCESS_STATUS),"+
					" C.SETTELED_AMOUNT "+
					" FROM "+
					" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B,"+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN C, "+
					" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT D"+ 
					" WHERE B.SUS_REF_NO = C.SUS_REF_NO"+
					"  AND D.PAYMENT_NO = C.PAYMENT_NO"+
					" AND B.REF_NO = '"+m_invoice_no+"' "+
			
					//Start Added By Jithendra 27.04.2016 to get Cleared Payment in settlement details		
					
					" UNION "+
					
					  "SELECT '-','Cleared Amount',CLEAR_AMOUNT "+
                     " FROM "+m_schema_name+".AF_CR_PRO_CLEAR_PAYMENT A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B"+
                     " WHERE A.SUS_PAYMENT_NO=B.SUS_REF_NO"+
                     " AND B.REF_NO='"+m_invoice_no+"' "	);
					
					//End Added by Jithendra 27.04.2016
				
				
				
				
				
				int k=0;
				boolean more=rs.next();
				if(!more){
					out.println("<table align='center' width='100%' class='table' border=0>"); 
					out.println("<tr><td width=100% align='center'><b><font color='red'>No Data Found..!</b></font></td><tr>");
					out.println("</table>");
				}
				if(more){
					out.println("<table align='center' width='100%' class='table' border=0>"); 
					out.println("<tr class=pdn_txtpos2>");
					
					out.println("<td width='1%' align='center'>No</td>");
					out.println("<td width='12%' align='left'>Payment No</td>"); 
					out.println("<td width='10%' align='left'>Status</td>"); 
					out.println("<td width='10%' align='right'>Amount</td>"); 
					out.println("</tr>");
					while(more){
						k=k+1;
						if(k>0 && k%2==1){
							out.println("<tr class=tr_input >");
						}
						else{
							out.println("<tr class=tr_input1 >");
						}
						out.println("<td width='1%' align='center'>"+k+"</td>");
						out.println("<td width='12%' align='left' >"+rs.getString(1)+"</u></td>"); 
						out.println("<td width='10%' align='left'>"+rs.getString(2)+"</td>"); 
						out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(3))+"</td>"); 
						out.println("</tr>");
						m_tot=m_tot+rs.getDouble(3);
						more=rs.next();
						
					}
					out.println("<tr class=tr_input1 style='height:15'>");
					out.println("<td width='13%' colspan=3 align='right'><b>Total</td>");
					out.println("<td width='10%' align='right'><b>"+nf.format(m_tot)+"</td></tr>");
					out.println("</table>");
					
					
					out.println("</form>"); 
					out.println("<script language1.2='javascript' src='"+m_html_client_url+"/validate.js'></script>"); 
					out.println("<script language1.2='javascript' src='"+m_html_client_url+"/leasing_drill_down.js'></script>");
					out.println("</body>"); 
					out.println("</html>"); 
					
				}
			}
			//End by Dineth on 15-04-2009
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


