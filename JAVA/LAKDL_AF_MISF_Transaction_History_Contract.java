

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_Transaction_History_Contract extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Statement stmt,stmt1,stmt2;	
	public ResultSet rs,rs1,rs2;
	Connection conn;
	CallableStatement callstmt1 =null;
	java.text.NumberFormat nf;
	java.lang.Math a;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
	
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name=m_sn_methods.schema_name;
			String m_username=m_sn_methods.username;
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			stmt = conn.createStatement ();
			stmt1 = conn.createStatement ();
			stmt2 = conn.createStatement ();
			
			String m_chksql = req.getParameter("chksql");
			
			
			if(m_chksql.equals("SHOW_TRANSACTION_HISTORY_BY_CONTRACT")){
				String m_finance_no=req.getParameter("finance_no");		
				String m_client_code=req.getParameter("client_code");		
				
				

				
				int count = 0;
				String m_string="";		
				String m_orient_name="";
				String m_name="";
				String m_cheque_no="";
				double m_cum_value=0;
				double m_val=0;
				double m_debit=0;
				double m_credit=0;
				
					out.println("<HTML><HEAD><TITLE> Transaction History - Finance No : "+m_finance_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Transaction History - Finance No : "+m_finance_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

					
					//String		Sql_invoice=
					//out.println(
					rs=stmt1.executeQuery(
					" SELECT REF_NO,TO_CHAR(DUE_DATE,'DD-MM-YYYY'),AMOUNT_DR,AMOUNT_CR,TYP,NVL(CHEQUE_NO,'-') CHEQUE_NO,DESCRIPTION,STATUS,NO "+
					" FROM "+
					" ( "+
					//INVOICE GENERATION GOES HERE OK
					"  SELECT "+
					"  INVOICE_NO      REF_NO,    "+ 
					"  VALUE_DATE      DUE_DATE,  "+ 
					"  TOTAL_AMOUNT    AMOUNT_DR,    "+
					"  0               AMOUNT_CR,    "+
					"  'INVOICE'       TYP,       "+
					"  NVL(NULL,'-')   CHEQUE_NO, "+
					"  NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) DESCRIPTION , "+// __ ADDED BY NUWAN DE SILVA ON 05-12-2007
					"  NULL            STATUS ,   "+
					"  NVL("+m_schema_name+".AF_CO_GET_INSTALLMENT_NO(INVOICE_NO),' ')  NO "+
					"  FROM "+m_schema_name+".AF_CO_PRO_INVOICE   "+
					"  WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					"        ACTIVE_STATUS IN ('Y','DB_CAN','C')  "+
					"   AND INVOICE_TYPE NOT IN ('LEGAL_ODI') "+
					" AND VALUE_DATE <=SYSDATE "+  
					
					" UNION "+
					
					//RECEIPT ALLOCATION DETAILS FOR INVOICES GOES HERE OK
					" SELECT "+
					" REC_NO REF_NO,  "+
					" EFF_VALDATE DUE_DATE,  "+
					" DECODE(STATUS,'RET',SUM(SETTELED_AMOUNT),0) AMOUNT_DR,  "+
					" DECODE(STATUS,'RET',0,SUM(SETTELED_AMOUNT)) AMOUNT_CR,  "+
					" 'RECEIPT' TYP,  "+ 
					" CHEQUE_NO  CHEQUE_NO , "+
					" DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc','STD_ORD','Standing Order') DESCRIPTION,  "+
					" STATUS,  "+
					" '' NO  "+
					" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B "+//,"+m_schema_name+".AF_CO_PRO_INVOICE C "+
					" WHERE RECEIPT_NO=REC_NO "+
					
					" AND A.INVOICE_NO IN (SELECT INVOICE_NO FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"                   WHERE FINANCE_NO='"+m_finance_no+"' "+
					
					"                   UNION ALL "+
					"                   SELECT ODI_REF_NO "+
					"                   FROM  "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
					"                   WHERE INVOICE_NO IN (SELECT INVOICE_NO FROM "+m_schema_name+".AF_CO_PRO_INVOICE  "+
					"                   WHERE FINANCE_NO='"+m_finance_no+"' )) "+
					
					" AND EFF_VALDATE <=SYSDATE "+  
					" GROUP BY REC_NO,EFF_VALDATE,CHEQUE_NO,SETTLE_MODE,STATUS "+
					
					"	UNION "+
					//ODI ALLOCATION GOES HERE OK
					" SELECT "+
					" A.INVOICE_NO REF_NO, "+
					" C.ALLOCATED_DATE DUE_DATE, "+
					" C.SETTELED_AMOUNT AMOUNT_DR, "+
					" 0 AMOUNT_CR, "+
					" 'ODI' TYP, "+
					" NULL CHEQUE_NO, "+
					" 'OVER DUE INTERST' DESCRIPTION, "+
					" NULL STATUS, "+
					" 'DR' STYPE "+
					" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A,"+
					"      "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C "+
					" WHERE "+
					"     C.INVOICE_NO=A.ODI_REF_NO "+
					" AND ODI_SETTLED_AMOUNT > 0 "+
					" AND A.INVOICE_NO IN "+
					" ( SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					"            ACTIVE_STATUS IN ('Y','DB_CAN')  "+
					") "+
					" AND DUE_DATE <=SYSDATE "+  
					
					//CREDIT NOTE ALLOCATION GOES HERE OK
					" UNION "+
					" SELECT "+
					" INVOICE_NO REF_NO, "+
					" ADJUSTED_DATE DUE_DATE, "+ 
					" DECODE(CREDIT_TYPE,'DR',ADJUSTED_AMOUNT,'CR',0) AMOUNT_DR, "+
					" DECODE(CREDIT_TYPE,'DR',0,'CR',ADJUSTED_AMOUNT) AMOUNT_CR, "+
					" 'DR/CR' TYP, "+
					" NULL CHEQUE_NO, "+
					" DECODE(CREDIT_TYPE,'CR','CREDIT NOTE','DEBIT NOTE') DESCRIPTION, "+
					" CREDIT_TYPE STATUS, "+
					"     '' NO "+ //7
					" FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS "+
					" WHERE INVOICE_NO IN "+
					" ( SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					"      ACTIVE_STATUS IN ('Y','DB_CAN')  "+	
					" ) "+
					" AND   ACTIVE_STATUS IN ('Y','C') "+
					" AND   ADJUST_TYPE NOT IN ('LEGAL_AD') "+
					" AND ADJUSTED_DATE <=SYSDATE "+  
					
					//--------------------------SANDUN ON 18-03-2009---------------------------------
					
					//CREDIT NOTE LEGAL ADJUSTMENT GOES HERE OK
					" UNION "+
					" SELECT "+
					" INVOICE_NO REF_NO, "+
					" ADJUSTED_DATE DUE_DATE, "+ 
					" ADJUSTED_AMOUNT AMOUNT_DR, "+
					" ADJUSTED_AMOUNT AMOUNT_CR, "+
					" 'DR/CR' TYP, "+
					" NULL CHEQUE_NO, "+
					" DECODE(CREDIT_TYPE,'CR','CREDIT NOTE - LEGAL TERMINATION') DESCRIPTION, "+
					" CREDIT_TYPE STATUS, "+
					"     '' NO "+ //7
					" FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS "+
					" WHERE INVOICE_NO IN "+
					" ( "+
					" SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					"      ACTIVE_STATUS IN ('Y','DB_CAN')  "+	
					" ) "+					
					" AND   ACTIVE_STATUS IN ('Y','C') "+
					" AND   ADJUST_TYPE = 'LEGAL_AD' "+
					" AND ADJUSTED_DATE <=SYSDATE "+ 
					
					
					" UNION"+
					
					//INVOICE REVERSE GOES HERE 
					" SELECT B.INVOICE_NO REF_NO, "+
					" NVL(B.INV_REV_DATE,B.VALUE_DATE) DUE_DATE,  "+
					" 0 AMOUNT_DR,  "+
					" B.TOTAL_AMOUNT AMOUNT_CR,  "+
					" 'INV_REV' TYP, "+
					" '-'  CHEQUE_NO ,   "+
					" 'Invoice Cancel '  DESCRIPTION,     "+
					" '-'  STATUS,   "+ 
					" 'CR' STYPE  "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE "+				  
					" B.FINANCE_NO = '"+m_finance_no+"' "+
					" AND B.ACTIVE_STATUS = 'C' "+
					" AND VALUE_DATE <= SYSDATE "+
					
					//------------------------------------------------------------
					
					" UNION "+
					//RECEIPT CONTRACT LEVEL ALLOCATION GOES HERE 
					" SELECT A.REC_NO REF_NO , "+
					" B.EFF_VALDATE   DUE_DATE ,"+ //				
					" 0 AMOUNT_DR,  "+
					" A.BAL_TOBE_RECEIVE AMOUNT_CR,  "+
					" 'BAL' TYP, "+
					" CHEQUE_NO CHEQUE_NO ,"+
					" NVL(DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc','STD_ORD','Standing Order'),'-') DESCRIPTION,  "+
					" DECODE(STATUS,'RET','RE',STATUS)  STATUS ,"+
					" '' NO "+ //7
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A , "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B "+
					" WHERE A.REC_NO=B.REC_NO "+
					" AND A.FINANCE_NO='"+m_finance_no+"'"+
					" AND A.BAL_TOBE_RECEIVE <> 0 "+
					" AND B.EFF_VALDATE <=SYSDATE "+  
					
					" UNION "+
					
					" SELECT A.REC_NO REF_NO , "+
					" B.EFF_VALDATE   DUE_DATE ,"+ //				
					" A.BAL_TOBE_RECEIVE AMOUNT_DR,  "+
					" 0 AMOUNT_CR,  "+
					"'BAL2' TYP, "+
					" CHEQUE_NO CHEQUE_NO ,"+
					" NVL(DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc','STD_ORD','Standing Order'),'-') DESCRIPTION,  "+
					" DECODE(STATUS,'RET','RE',STATUS)  STATUS ,"+
					" '' NO "+ //7
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A , "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B "+
					" WHERE A.REC_NO=B.REC_NO "+
					" AND A.FINANCE_NO='"+m_finance_no+"'"+
					" AND A.BAL_TOBE_RECEIVE <> 0 "+
					" AND B.EFF_VALDATE <=SYSDATE "+  
					"	AND B.STATUS IN ('RET','C','CAD') "+
										
					
				/*	" UNION "+
					
					"	SELECT    "+
					"	A.REC_NO REF_NO,   "+
					" B.REALISED_DATE DUE_DATE,"+
					"	NVL(A.BAL_TOBE_RECEIVE,0)  AMOUNT_DR,   "+
					"	0  AMOUNT_CR,   "+
					"	'RETURN_DEBIT' TYP,  "+
					"	B.CHEQUE_NO  CHEQUE_NO ,  "+
					"	'CHEQUE RETURN '  DESCRIPTION,    "+
					"	B.STATUS STATUS,   "+
					" 'DR' STYPE "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL A , "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B "+
					" ,"+m_schema_name+".AF_CO_PRO_RETURN_DETAILS C "+
					" WHERE A.REC_NO=B.REC_NO "+
					"	AND   A.REC_NO=C.RECEIPT_NO(+) "+
					" AND A.FINANCE_NO='"+m_finance_no+"'"+
					" AND A.BAL_TOBE_RECEIVE <> 0 "+
					" AND B.EFF_VALDATE <=SYSDATE "+  
					"	AND B.STATUS='RET' "+
					*/
					
					
					" UNION "+
					
					"	SELECT    "+
					"	INVOICE_NO REF_NO,   "+
					"	TRN_DATE DUE_DATE,  "+ 
					"	0              AMOUNT_DR,   "+
					"	NVL(AMOUNT,0)  AMOUNT_CR,   "+
					"	'DEBIT_CANCEL' TYP,  "+
					"	'-'  CHEQUE_NO ,  "+
					"	'Debit Note Cancelation '  DESCRIPTION,    "+
					"	'-'  STATUS,   "+
					" 'CR' STYPE "+
					" FROM "+m_schema_name+".AF_CO_PRO_CANCEL_DR_NOTE "+
					" WHERE FINANCE_NO='"+m_finance_no+"' "+
					" AND   TRN_DATE <=SYSDATE "+  
					
					" UNION "+
					
					"	SELECT    "+
					"	INVOICE_NO REF_NO,   "+
					"	TRN_DATE DUE_DATE,  "+ 
					"	NVL(ADJUSTED_AMOUNT,0)  AMOUNT_DR,   "+
					"	0  AMOUNT_CR,   "+
					"	'CREDIT_CANCEL' TYP,  "+
					"	'-'  CHEQUE_NO ,  "+
					"	'Credit Note Cancelation '  DESCRIPTION,    "+
					"	'-'  STATUS,   "+
					" 'CR' STYPE "+
					" FROM "+m_schema_name+".AF_CO_PRO_CANCEL_CR_NOTE "+
					" WHERE FINANCE_NO='"+m_finance_no+"' "+
					" AND   TRN_DATE <=SYSDATE "+  
					
					
					" ) "+
					
					" ORDER BY DUE_DATE ");

				 // rs=stmt1.executeQuery(Sql_invoice);
					boolean  more_inv =rs.next();
					String m_application_no="";
					m_cum_value=0;
					while(more_inv){
					count++;
					if(count==1){
					out.println("<table align='center' width='100%' class='table'>");
					out.println("<tr>");
					out.println("<td width='*%'align='center' class=div_input><b>"+m_orient_name.toUpperCase()+"</b></td>");
					out.println("</tr>");
					// ------ Modified by Dineth on 29-07-2008
					String sql_col_status  = " SELECT "+ m_schema_name + ".AF_CO_GET_EMP_NAME(COLLECTION_OFFICER),"+
																		" "+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO) "+
																	 " ,APPLICATION_NO "+
																	 ","+m_schema_name+".af_co_get_client_name('"+m_client_code+"') "+

																	 " FROM "+ m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS" +
																	 " WHERE FINANCE_NO = '"+m_finance_no +"'";
					rs2 = stmt2.executeQuery(sql_col_status);
					boolean more2 = rs2.next();
					if(more2){
					out.println("<tr>");
					out.println("<td width='12%' align='left' class=div_input>Client Name</td><td>: "+rs2.getString(4)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='12%' align='left' class=div_input>Collection Officer</td><td>: "+rs2.getString(1)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='12%' align='left' class=div_input>Application Status</td><td>: "+rs2.getString(2)+"</td>");
					out.println("</tr>");
					m_application_no=rs2.getString(3);
					}
					// ------ End by Dineth on 29-07-2008
					out.println("<tr>");
					out.println("<td width='100%'align='center' colspan='2' class=div_input><b>Asset Finance Ledger</b></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<hr color='black'>");
					
					out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' >");
					
					
					out.println("</table>");
					
					out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
					
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>Date</td>");
					out.println("<td width='15%' class=div_input>Doc Ref</td>");
					out.println("<td width='30%' class=div_input>Narration</td>");
					out.println("<td width='10%' class=div_input>Reference No.</td>"); //Cheque No.
					out.println("<td width='10%' class=div_input align='right'>Debit</td>");
					out.println("<td width='10%' class=div_input align='right'>Credit</td>");
					out.println("<td width='10%' class=div_input align='right'>Cum. Value</td>");
					out.println("<td width='4%' class=div_input></td>");
					out.println("</tr>");
					}
					
					
					m_debit   = rs.getDouble(3);
					m_credit  = rs.getDouble(4);
					m_cum_value+=m_debit-m_credit;
					
					if(rs.getString(5).equals("INVOICE") || rs.getString(5).equals("ODI") ){
					//m_cum_value+=m_debit-m_credit;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info_2('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(m_debit)+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					
					else if(rs.getString(5).equals("DR/CR")){
					//m_cum_value+=m_debit-m_credit;
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info_2('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='10%' class=div_input>"+rs.getString(6)+"</td>");
				  if(rs.getString(7).equals("DR") ){
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(m_debit)+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(m_credit)+"</td>");
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
					
					
					else if(rs.getString(5).equals("RECEIPT") || rs.getString(5).equals("INV_REV")  || rs.getString(5).equals("BAL") || rs.getString(5).equals("DEBIT_CANCEL") ){
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(7)+"</td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(6)+"</td>");
					}
					
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(m_credit)+"</td>");
					
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
					
					else if(rs.getString(5).equals("BAL2")  || rs.getString(5).equals("CREDIT_CANCEL") ){
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(7)+"</td>");
					if(rs.getString(6).equals("-") ||rs.getString(6).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(6)+"</td>");
					}
					
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(m_debit)+"</td>");
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
					
					
					
					/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
					/*******COMMENT NS FOR NEW********************************************************************************************
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
					else if(rs.getString(4).equals("RET_CHARGE")){ //added by nuwan de silva on 14-08-07
					m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("RETURN_DEBIT")){ //added by nuwan de silva on 14-08-07
					m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("DEBIT_CANCEL")){ //added by nuwan de silva on 14-08-07
					m_credit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("CREDIT_CANCEL")){ //added by nuwan de silva on 14-08-07
					m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("INV_REV")){ 
					m_credit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("BAL")){
					m_credit=rs.getDouble(3);
					}
					
					m_val=m_debit-m_credit;
					
					if(m_cum_value < 0 && m_credit >0){ //added by nuwan de silva on 10-07-2008 
					m_cum_value=m_cum_value-m_credit;
					}
					else{
					m_cum_value=m_cum_value+m_val;
					}

					//m_cum_value=m_cum_value+m_val;
          					
					if(rs.getString(4).equals("INVOICE")){
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
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
					
				 if(rs.getString(7).equals("RET") ){
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
					
					//if(rs.getString(7).equals("RET") ){
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					//}
					//else
					//{
					//out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					//out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					//}
					
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					
				  m_val=m_debit-m_credit;
          m_cum_value=m_cum_value-m_val;
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
					//if(rs.getString(7).equals("RET") ){
					//out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					//out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					//}
					//else
					//{
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					//}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
          else if(!rs.getString(7).equals("RET") ){
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
					
          //---------------------------------------------------------------------------------------------------
					if(rs.getString(7).equals("CAD")  || rs.getString(7).equals("C")){
					m_debit =0;
					m_credit=0;
					m_debit=rs.getDouble(3);
          				
					m_val=m_debit-m_credit;
					if(m_cum_value>0){  
            m_cum_value=m_cum_value+m_val;
					}else if (m_cum_value<0 ){
					  m_cum_value=m_cum_value-m_val;
					}

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
					}
					//---------------------------------------------------------------------------------------------------
					
					
					}
					//return charges------------
					else if(rs.getString(4).equals("RET_CHARGE")){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_return_detail_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
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
					//--------------------
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
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_payment('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>");
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
					
					
					else if(rs.getString(4).equals("BAL")){
					
					if(rs.getString(7).equals("RET") || rs.getString(7).equals("CAD") || rs.getString(7).equals("C") ){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>Receipt</td>"); //"+rs.getString(6)+"
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					
					//m_val=m_debit;
          //m_cum_value=m_cum_value-m_val;
					m_val=m_debit-m_credit;  // added by nuwan de silva on 10-07-2008
					//added by SH on 15-01-2009 ***********
					if(m_val>0){ 
						if (m_cum_value>0)
						{
						m_cum_value=m_cum_value-m_val;
						}					
						else{
						m_cum_value=m_cum_value+m_val;
						}
					}else{
					//end of addition ******************
						if (m_cum_value<0 && m_val<0)
						{
						m_cum_value=m_cum_value-m_val;
						}
						else if (m_cum_value>=0 && m_val<0)
						{
						m_cum_value=m_cum_value-m_val;
						}
						else{
						m_cum_value=m_cum_value+m_val;
						}
					
					}//added by SH on 15-01-2009 ***********
     
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"  </td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
          }
					else{
					
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
					
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
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
					
					}
					
					else if (rs.getString(4).equals("RETURN_DEBIT")){
     
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"  </td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
         
					}
					
					else if (rs.getString(4).equals("DEBIT_CANCEL")){
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"  </td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
         
					}
					else if (rs.getString(4).equals("INV_REV")){
														
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"  </td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
         
					}
					
					else if (rs.getString(4).equals("CREDIT_CANCEL")){
															
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"  </td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
         
					}

					
					else if(rs.getString(4).equals("DR/CR")){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info_2('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
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
					****************************************************************************************************/
					
					
					more_inv = rs.next();
				}
				out.println("</table>");
    				
				
				
									String		Sql_Unallocated=" SELECT "+ 
								  "  A.REC_NO, "+
								  "  A.REC_AMOUNT, "+
									"  B.allocated_amount, "+
									"  B.bal_tobe_receive, "+
									"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') "+ //added by nuwan de silva on 18-09-07
								  "  FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
								  "  WHERE  A.REC_NO=B.REC_NO  "+
								  "  AND UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') AND  B.BAL_TOBE_RECEIVE > 0  AND A.STATUS NOT IN ('C','CAD','RET') ORDER BY  EFF_VALDATE ";
									
		
			  rs=stmt1.executeQuery(Sql_Unallocated);
				boolean  more =rs.next();
				
				if (more) {
				  out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><u><b>Un Allocated Receipts Details</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				 
					out.println("<br>");
				
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input ><b>Receipt No</b></td>");
					out.println("<td width='10%' class=div_input ><b>Effective Value Date</b></td>");
					out.println("<td width='20%' align='right' class=div_input ><b>Receipt Amount</b></td>");
					out.println("<td width='20%' align='right' class=div_input ><b>Allocated Amount</b></td>");
					out.println("<td width='20%' align='right' class=div_input ><b>Balance To Be Allocated</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double sum_amount=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input >"+rs.getString(5)+"</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"&nbsp;&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"&nbsp;&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"&nbsp;&nbsp;</td>");

					out.println("</tr>");
					sum_amount=sum_amount+rs.getDouble(4);

					more = rs.next();
				}
				
					if(sum_amount >0){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input >&nbsp;</td>");
					out.println("<td width='10%' class=div_input >&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'><b>Total&nbsp;&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'><b>"+nf.format(sum_amount)+"&nbsp;&nbsp;</td>");
					out.println("</tr>");
          }

					out.println("</table>");
					
				//receipt contracl level unallocated receipt details .......................................................
				
									/*String		Sql_Unallocated=" SELECT "+ 
								  "  A.REC_NO, "+
								  "  A.REC_AMOUNT, "+
									"  B.allocated_amount, "+
									"  B.bal_tobe_receive, "+
									"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') "+ //added by nuwan de silva on 18-09-07
								  "  FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
								  "  WHERE  A.REC_NO=B.REC_NO  "+
								  "  AND UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') AND  B.BAL_TOBE_RECEIVE > 0  AND A.STATUS NOT IN ('C','CAD','RET') ORDER BY  EFF_VALDATE ";
									*/
									
										Sql_Unallocated=" SELECT "+ 
									" a.rec_no ,"+
									" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') , "+ 
									" a.finance_no,"+
									" a.rec_amount, "+
									" a.app_rec_amount, "+
									" a.allocated_amount, "+
									" a.bal_tobe_receive "+
									" FROM "+m_schema_name+".af_co_pro_settl_rec_app_bal a ,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B "+
									" WHERE  A.REC_NO=B.REC_NO   "+
									" AND UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"')  "+
									" AND  A.BAL_TOBE_RECEIVE > 0   "+
									" AND B.STATUS NOT IN ('C','CAD','RET')  "+
									" ORDER BY  EFF_VALDATE  ";
									
		
			  rs=stmt1.executeQuery(Sql_Unallocated);
				 more =rs.next();
				
				if (more) {
				  out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><u><b>Un Allocated Receipts Details Contract Level</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				 
					out.println("<br>");
				
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input ><b>Receipt No</b></td>");
					out.println("<td width='15%' class=div_input ><b>Effective Value Date</b></td>");
					out.println("<td width='15%' class=div_input ><b>Contract No</b></td>");
					out.println("<td width='15%' align='right' class=div_input ><b>Receipt Amount</b></td>");
					out.println("<td width='15%' align='right' class=div_input ><b>Allocated Amount To This Contract</b></td>");
					out.println("<td width='15%' align='right' class=div_input ><b>Allocated Amount</b></td>");
					out.println("<td width='15%' align='right' class=div_input ><b>Balance To Be Allocated</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				sum_amount=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(3)+"&nbsp;&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"&nbsp;&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"&nbsp;&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"&nbsp;&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(7))+"&nbsp;&nbsp;</td>");
					out.println("</tr>");
					sum_amount=sum_amount+rs.getDouble(7);

					more = rs.next();
				}
				
					if(sum_amount >0){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input >&nbsp;</td>");
					out.println("<td width='15%' class=div_input >&nbsp;</td>");
					out.println("<td width='15%' class=div_input >&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'><b>Total&nbsp;&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(sum_amount)+"&nbsp;&nbsp;</td>");
					out.println("</tr>");
          }

					out.println("</table>");
					
					
					//================Added by Sandun on 30-07-2008======================================================================
   
		
		
     String		Sql_Pod_Cheque_Hand= " SELECT "+ 
												           "  NVL(POD_REF_NO,'-'), "+//1
												           "  NVL(FINANCE_NO,'-'), "+//2
												           "  NVL(CHEQUE_NO,'-'), "+//3
												           "  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'), "+//4
												           "  NVL(PAYER_ACC_NO,'-'), "+//5
												           "  NVL(PAYER_BRANCH_CODE,'-'), "+//6
												           "  NVL(CHEQUE_AMOUNT,0), "+//7
																	 "  DECODE(STATUS,'APP','Approved','CAN','Dis Approved','HOL','Hold','INV','Entered','REC','Receipt Generated','WIT','Withdraw',STATUS) "+
												           "  FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
												           "  WHERE UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"')  AND STATUS IN ('INV' ,'APP') " ;

					     

			  rs=stmt1.executeQuery(Sql_Pod_Cheque_Hand);
				
				
				boolean  more1=rs.next();
				if(more1){
				
				
				 out.println("<table align='center' width='100%' class='table' >");
				 out.println("<tr>");
				 out.println("<td width='100%' class=div_input><u><b>Post Dated Cheque Details - Finance No: "+m_finance_no+"</b></u></td>");					
				 out.println("<td width='*%'></td>");
				 out.println("</tr>");
				 out.println("</table>");
			/*
        if (!more1) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				*/
					  
          out.println("<br>");
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>POD Ref No</b></td>");
					out.println("<td width='15%' class=div_input><b>Finance No</b></td>");
					out.println("<td width='12%' class=div_input><b>Cheque No</b></td>");
					out.println("<td width='12%' class=div_input><b>Cheque Date</b></td>");
					out.println("<td width='15%' class=div_input><b>Account No</b></td>");
					out.println("<td width='15%' class=div_input><b>Branch code</b></td>");
					out.println("<td width='10%' class=div_input><b>POD Status</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Amount</b></td>");
					out.println("</tr>");
					//out.println("</table>");
					out.println("<br>");
				}
				//out.println("<table align='center' width='100%' class='table' >");
				while(more1){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_POD_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_finance_drill('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='12%' class=div_input align='left'>"+rs.getString(3)+"</td>");
					out.println("<td width='12%' class=div_input align='left'>"+rs.getString(4)+"</td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(5)+"</td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input align='left'>"+rs.getString(8)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(7))+"</td>");
					out.println("</tr>");
					
					more1 = rs.next();
				}
				
	
				  out.println("</table>");
					


//==============================End on 30-07-2008========================================================

				//..........................................................................................................
					
				out.println("<br>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
  			out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='*%' align='center' class=div_input ><B>Client Special Comments</B></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<hr color='black'>");
				out.println("<br>");
				
		    rs=stmt1.executeQuery("SELECT TO_CHAR(ENT_DATE,'DD-MM-YYYY HH24:MI:SS'),COMMENTS,ENT_USER "+
				                      " FROM "+m_schema_name+".AF_CO_MAS_CLIENT_COMMENT "+
															" WHERE CLIENT_CODE ='"+m_client_code+"'  "+
															" AND ( APPLICATION_NO ='"+m_finance_no+"' "+
															" OR    APPLICATION_NO ='"+m_application_no+"' )"+
															" ORDER BY ENT_DATE DESC ");
			
			
			/*rs=stmt1.executeQuery("SELECT TO_CHAR(A.ENT_DATE,'DD-MM-YYYY HH24:MI:SS'),A.COMMENTS,A.ENT_USER "+
				                      " FROM "+m_schema_name+".AF_CO_MAS_CLIENT_COMMENT A , "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  B "+
															" WHERE A.CLIENT_CODE ='"+m_client_code+"' "+
														  " AND A.APPLICATION_NO=B.APPLICATION_NO "+
															" AND (B.FINANCE_NO='"+m_finance_no+"' "+
															" OR  B.FINANCE_NO='"+m_finance_no+"' )"+
															" ORDER BY A.ENT_DATE DESC ");
															
															*/
															
				boolean  more4 =rs.next();			
				
				if(more4){
					out.println("<table align='center' width='100%' class='table' >");
					while(more4){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input valign='top'><p><B>Date: </B>"+rs.getString(1)+" &nbsp <br><B>User: </B>"+rs.getString(3)+"</p></td>");
		 				out.println("<td width='2%'>&nbsp</td>");
						out.println("<td width='70%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr></tr>");
						more4 =rs.next();
					}
					out.println("</table>");
        }
				else{
					out.println("<table align='center' width='100%' class='table' >");
	  			out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' align='center' class=div_input >No Special Comments</td>");
					out.println("</tr>");
					out.println("</table>");
				}
				out.println("<br>");
				out.println("<br>");
				out.println("<table></table>");
				out.println("<br>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
  			out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='*%' align='center' class=div_input ><B>Comments - Collection</B></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<hr color='black'>");
				out.println("<br>");



				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			
			}
			

		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
