import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : ASHINI FOR OFSCL FACTORING    DATE:19-10-2007

public class LAKDL_FA_OP_Non_Sales_and_Excess_Funds_Transfer_Data extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
    
  public ResultSet rs1;
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
            
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
     	res.setDateHeader("Expires", 0);

			ServletOutputStream out = res.getOutputStream();

			m_chksql=req.getParameter("chksql");
			stmt1=conn.createStatement();

			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("RECEIPT_DETAILS")){
				String m_facility_no=req.getParameter("facility_no");
				String m_client_code=req.getParameter("client_code");
				String m_receipt_no=req.getParameter("receipt_no");
				String m_chk_status=req.getParameter("chk_status");
				String m_string="";				
				if(m_chk_status.equals("Y")) { 
						rs1= stmt1.executeQuery(" SELECT RECEIPT_NO, "+ //1
						" DECODE (RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)),"+//2 
						" DECODE(SETTLE_MODE,'CHEQUE',SETTLE_MODE ||'-'||CHEQUE_NO,SETTLE_MODE) , "+//3
						" REC_AMOUNT,"+//4
	       		" ALLO_AMOUNT ,"+//5
						" BALANCE_AMOUNT, "+//6
						" TO_CHAR(SYSDATE,'DD-MM-YYYY') "+//7
	  				" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+
						" WHERE FACILITY_NO='"+m_facility_no+"'  "+
						" AND CLIENT_CODE ='"+m_client_code+"' "+
						" AND  RECEIPT_NO ='"+m_receipt_no+"' "+
						" AND BALANCE_AMOUNT > 0 "+
						" AND RECON_STATUS ='Y' "+
						" AND REC_SETT_TYPE ='NR'  "); //AND REC_SETT_TYPE ='NR' ---ADDED BY ASHINI ON 22-02-2008-
				}
				else {
						rs1= stmt1.executeQuery(" SELECT RECEIPT_NO, "+ //1
						" DECODE (RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)),"+//2 
						" DECODE(SETTLE_MODE,'CHEQUE',SETTLE_MODE ||'-'||CHEQUE_NO,SETTLE_MODE) , "+//3
						" REC_AMOUNT,"+//4
	       		" ALLO_AMOUNT ,"+//5
						" BALANCE_AMOUNT, "+//6
						" TO_CHAR(SYSDATE,'DD-MM-YYYY') "+//7
	  				" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+
						" WHERE FACILITY_NO='"+m_facility_no+"'  "+
						" AND CLIENT_CODE ='"+m_client_code+"' "+
						" AND BALANCE_AMOUNT > 0 "+
						" AND RECON_STATUS ='Y'  "+
						" AND REC_SETT_TYPE ='NR'  "); //AND REC_SETT_TYPE ='NR' ---ADDED BY ASHINI ON 22-02-2008-
						
				}			
			
     boolean more2=rs1.next();
			if(more2){
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>"; 
				m_string=m_string+"<td width='10%' class=div_input><b>Receipt No</b></td>";
				m_string=m_string+"<td width='9%' class=div_input><b>Client/Debtor Name</b></td>";
				m_string=m_string+"<td width='5%' class=div_input><b>Settlement Mode</b></td>";
				m_string=m_string+"<td width='9%' class=div_input><b>Receipt Amount</b></td>";
				m_string=m_string+"<td width='9%' class=div_input><b>Settle Amount</b></td>";
				m_string=m_string+"<td width='9%' class=div_input><b>Balance Amount</b></td>";
				m_string=m_string+"<td width='9%' class=div_input><b>Transfer Amount</b></td>";
				m_string=m_string+"<td width='5%' class=div_input><b>Transfer Date</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Source Document</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Comments</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b></b>Select</td>";
				m_string=m_string+"<td width='5%' class=div_input><b>Receipt Settlement Type</b></td>"; //---ADDED BY ASHINI ON 22-02-2008-
				m_string=m_string+"<td width='5%' class=div_input><b></b></td>";
				m_string=m_string+"</tr>";
			}
				int chk_nums=0;
				int j=1;
				while(more2){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width=\"10%\" class=div_input  onClick=\"show_receipt_details('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_REC_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>"+
          "<td width=\"9%\" class=div_input   style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_NAME_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\">"+rs1.getString(2)+"</td>"+					
          "<td width=\"5%\" class=div_input   style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_SETTL_MODE_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\">"+rs1.getString(3)+"</td>"+					
          "<td width=\"9%\" class=div_input   style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_REC_AMOUNT_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(4))+"\">"+nf.format(rs1.getDouble(4))+"</td>"+					
          "<td width=\"9%\" class=div_input   style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_SETT_AMOUNT_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(5))+"\">"+nf.format(rs1.getDouble(5))+"</td>"+					
					"<td width=\"9%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_BAL_AMOUNT_"+chk_nums+"\" disabled value=\""+nf.format(rs1.getDouble(6))+"\" style=\"text-align:right;\" ><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_BALNCE_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(6))+"\"></td>"+
					"<td width=\"9%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_TRAN_AMOUNT_"+chk_nums+"\" value=\"0\" style=\"text-align:right;\" onchange=\"format_num(document.Form1.TXT_TRAN_AMOUNT_"+chk_nums+",4),get_balance("+chk_nums+"),format_num(document.Form1.TXT_BAL_AMOUNT_"+chk_nums+",4)\"  ></td>"+
					"<td width=\"5%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_TRAN_DATE"+chk_nums+"\" maxlength=\"10\"  value=\""+rs1.getString(7)+"\" > </td>"+
					"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_SOURCE_"+chk_nums+"\" maxlength=\"200\"  value=\"\"></td>"+
					"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_COMMENT_"+chk_nums+"\" maxlength=\"200\"></td>"+
					"<td width='10%' class=div_input><select name='TXT_ADJ_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"NS\">Non Sales Funds Transfer</option><OPTION value=\"EX\">Excess Funds Transfer</option></SELECT></td>"+
					"<td width='5%' class=div_input><select name='TXT_RECE_SETT_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"NR\">Normal Receipt</option></SELECT></td>"+ //---ADDED BY ASHINI ON 22-02-2008-
					"<td width=\"5%\"><input type=\"checkbox\" name=\"TXT_REC_OK_"+chk_nums+"\"></td>"+
					"</tr>";
		    	more2=rs1.next();
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			//--------------------------ADDED BY ASHINI ON 22-02-2008----------------------------------------------------------------------
			
			
				else if(m_chksql.equals("REFUNDABLE_RECEIPT_DETAILS")){
				String m_facility_no=req.getParameter("facility_no");
				String m_client_code=req.getParameter("client_code");
				String m_receipt_no=req.getParameter("receipt_no");
				String m_chk_status=req.getParameter("chk_status");
				String m_string="";				
				if(m_chk_status.equals("Y")) { 
						rs1= stmt1.executeQuery(" SELECT RECEIPT_NO, "+ //1
						" DECODE (RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)),"+//2 
						" DECODE(SETTLE_MODE,'CHEQUE',SETTLE_MODE ||'-'||CHEQUE_NO,SETTLE_MODE) , "+//3
						" REC_AMOUNT,"+//4
	       		" ALLO_AMOUNT ,"+//5
						" BALANCE_AMOUNT, "+//6
						" TO_CHAR(SYSDATE,'DD-MM-YYYY') "+//7
	  				" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+
						" WHERE FACILITY_NO='"+m_facility_no+"'  "+
						" AND CLIENT_CODE ='"+m_client_code+"' "+
						" AND  RECEIPT_NO ='"+m_receipt_no+"' "+
						" AND BALANCE_AMOUNT > 0 "+
						" AND RECON_STATUS ='Y' "+
						" AND REC_SETT_TYPE ='RR'  ");
				}
				else {
						rs1= stmt1.executeQuery(" SELECT RECEIPT_NO, "+ //1
						" DECODE (RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)),"+//2 
						" DECODE(SETTLE_MODE,'CHEQUE',SETTLE_MODE ||'-'||CHEQUE_NO,SETTLE_MODE) , "+//3
						" REC_AMOUNT,"+//4
	       		" ALLO_AMOUNT ,"+//5
						" BALANCE_AMOUNT, "+//6
						" TO_CHAR(SYSDATE,'DD-MM-YYYY') "+//7
	  				" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+
						" WHERE FACILITY_NO='"+m_facility_no+"'  "+
						" AND CLIENT_CODE ='"+m_client_code+"' "+
						" AND BALANCE_AMOUNT > 0 "+
						" AND RECON_STATUS ='Y'  "+
						" AND REC_SETT_TYPE ='RR'  ");
						
				}			
			
     boolean more2=rs1.next();
			if(more2){
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>"; 
				m_string=m_string+"<td width='10%' class=div_input><b>Receipt No</b></td>";
				m_string=m_string+"<td width='9%' class=div_input><b>Client/Debtor Name</b></td>";
				m_string=m_string+"<td width='5%' class=div_input><b>Settlement Mode</b></td>";
				m_string=m_string+"<td width='9%' class=div_input><b>Receipt Amount</b></td>";
				m_string=m_string+"<td width='9%' class=div_input><b>Settle Amount</b></td>";
				m_string=m_string+"<td width='9%' class=div_input><b>Balance Amount</b></td>";
				m_string=m_string+"<td width='9%' class=div_input><b>Transfer Amount</b></td>";
				m_string=m_string+"<td width='5%' class=div_input><b>Transfer Date</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Source Document</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Comments</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b></b>Select</td>";
				m_string=m_string+"<td width='5%' class=div_input><b>Receipt Settlement Type</b></td>";
				m_string=m_string+"<td width='5%' class=div_input><b></b></td>";
				m_string=m_string+"</tr>";
			}
				int chk_nums=0;
				int j=1;
				while(more2){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width=\"10%\" class=div_input  onClick=\"show_receipt_details('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_REC_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>"+
          "<td width=\"9%\" class=div_input   style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_NAME_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\">"+rs1.getString(2)+"</td>"+					
          "<td width=\"5%\" class=div_input   style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_SETTL_MODE_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\">"+rs1.getString(3)+"</td>"+					
          "<td width=\"9%\" class=div_input   style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_REC_AMOUNT_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(4))+"\">"+nf.format(rs1.getDouble(4))+"</td>"+					
          "<td width=\"9%\" class=div_input   style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_SETT_AMOUNT_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(5))+"\">"+nf.format(rs1.getDouble(5))+"</td>"+					
					"<td width=\"9%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_BAL_AMOUNT_"+chk_nums+"\" disabled value=\""+nf.format(rs1.getDouble(6))+"\" style=\"text-align:right;\" ><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_BALNCE_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(6))+"\"></td>"+
					"<td width=\"9%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_TRAN_AMOUNT_"+chk_nums+"\" value=\"0\" style=\"text-align:right;\" onchange=\"format_num(document.Form1.TXT_TRAN_AMOUNT_"+chk_nums+",4),get_balance("+chk_nums+"),format_num(document.Form1.TXT_BAL_AMOUNT_"+chk_nums+",4)\"  ></td>"+
					"<td width=\"5%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_TRAN_DATE"+chk_nums+"\" maxlength=\"10\"  value=\""+rs1.getString(7)+"\" > </td>"+
					"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_SOURCE_"+chk_nums+"\" maxlength=\"200\"  value=\"\"></td>"+
					"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_COMMENT_"+chk_nums+"\" maxlength=\"200\"></td>"+
					"<td width='10%' class=div_input><select name='TXT_ADJ_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"NS\">Non Sales Funds Transfer</option><OPTION value=\"EX\">Excess Funds Transfer</option></SELECT></td>"+
					"<td width='5%' class=div_input><select name='TXT_RECE_SETT_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"RR\">Returnable Receipt</option></SELECT></td>"+
					"<td width=\"5%\"><input type=\"checkbox\" name=\"TXT_REC_OK_"+chk_nums+"\"></td>"+
					"</tr>";
		    	more2=rs1.next();
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			
			//---------END MODIFICATIONS DONE BY ASHINI -----------------------------------------------------------------------------------
			
			else if(m_chksql.equals("CHEQUE_RETURN")){
			
				String m_facility_no=req.getParameter("facility_no");
				String m_client_code=req.getParameter("client_code");
				String m_cheque_no=req.getParameter("cheque_no");
				
				String m_string="";			
				
				rs1= stmt1.executeQuery(" SELECT  "+
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DECODE(B.RECEIPT_TYPE,'CS',B.CLIENT_CODE,B.DEBTOR_CODE)),"+//1
				" TO_CHAR(A.REALIZE_DATE,'DD-MM-YYYY'),"+//2
				" A.RECEIPT_NO, "+//3
				" B.REC_AMOUNT, "+//4
				" B.CHEQUE_NO, "+//5
				" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(B.PAYER_BRANCH_CODE), "+//6
				" "+m_schema_name+".FA_GET_RETURN_CHEQUE_AMT(A.RECEIPT_NO), "+//7
				" B.REC_AMOUNT-"+m_schema_name+".FA_GET_RETURN_CHEQUE_AMT(A.RECEIPT_NO), "+//8
				" UPPER(NVL(A.RETURN_COMMENTS,'-')), "+//9
				" TO_CHAR(SYSDATE,'DD-MM-YYYY') "+//10
				" FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B  "+
				" WHERE A.RECEIPT_NO=B.RECEIPT_NO  "+
				" AND B.CLIENT_CODE='"+m_client_code+"' "+
				" AND B.FACILITY_NO='"+m_facility_no+"' "+
				" AND B.REBANK_STATUS IN('N','R') "+
				" AND (B.REC_AMOUNT-"+m_schema_name+".FA_GET_RETURN_CHEQUE_AMT(A.RECEIPT_NO))>0 "+
				" AND B.CHEQUE_NO LIKE '%"+m_cheque_no+"%' "+
				" ORDER BY "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DECODE(B.RECEIPT_TYPE,'CS',B.CLIENT_CODE,B.DEBTOR_CODE)),A.REALIZE_DATE DESC ");

			
     	boolean more2=rs1.next();
			if(more2){
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>"; 
				m_string=m_string+"<td width='15%' class=div_input><b>Receipt No</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Client/Debtor Name</b></td>";
				m_string=m_string+"<td width='5%' class=div_input><b>Cheque No</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Receipt Amount</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Settle Amount</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Balance Amount</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Transfer Amount</b></td>";
				m_string=m_string+"<td width='5%' class=div_input><b>Transfer Date</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Source Document</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Comments</b></td>";
				m_string=m_string+"<td width='5%' class=div_input><b>Select</b></td>";
				m_string=m_string+"</tr>";
			}
				int chk_nums=0;
				int j=1;
				while(more2){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width=\"15%\" class=div_input  onClick=\"show_receipt_details('"+rs1.getString(3)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_REC_NO_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\"><u>"+rs1.getString(3)+"</u></td>"+
          "<td width=\"15%\" class=div_input style='cursor:hand'>"+rs1.getString(1)+"</td>"+					
					"<td width=\"15%\" class=div_input style='cursor:hand'>"+rs1.getString(5)+"</td>"+		
          "<td width=\"10%\" class=div_input style='cursor:hand'>"+nf.format(rs1.getDouble(4))+"</td>"+					
					"<td width=\"10%\" class=div_input style='cursor:hand'>"+nf.format(rs1.getDouble(7))+"</td>"+		
					"<td width=\"10%\" class=div_input style='cursor:hand'>"+nf.format(rs1.getDouble(8))+"</td>"+		
					"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_TRAN_AMOUNT_"+chk_nums+"\" disabled value=\""+nf.format(rs1.getDouble(8))+"\" style=\"text-align:right;\" ></td>"+
					"<td width=\"5%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_TRAN_DATE"+chk_nums+"\" maxlength=\"10\"  value=\""+rs1.getString(10)+"\" > </td>"+
					"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_SOURCE_"+chk_nums+"\" maxlength=\"200\"  value=\"\"></td>"+
					"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_COMMENT_"+chk_nums+"\" maxlength=\"200\"></td>"+
					"<td width='10%' class=div_input><select name='TXT_ADJ_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"CRT\">Cheque Retruns Transfer</option></SELECT></td>"+
					"<td width=\"5%\"><input type=\"checkbox\" name=\"TXT_REC_OK_"+chk_nums+"\"></td>"+
					"</tr>";
		    	more2=rs1.next();
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			
			else if(m_chksql.equals("SALES_AND_EXCESS_FUNDS_TRANSFER_DATA")){
				String m_string="";	
						rs1= stmt1.executeQuery(" SELECT A.FACILITY_NO,  "+//1
																		" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),  "+//2
																		" A.RECEIPT_NO, "+//3
																		" A.TRANSFER_AMOUNT,  "+//4
																		" TO_CHAR(A.TRANSFER_DATE,'DD-MM-YYYY'), "+//5
																		" NVL(A.SOURCE_DOCUMENT,'-'), "+//6
																		" NVL(A.COMMENTS,'-'), "+//7
																		" NVL(DECODE(A.ADJ_TYPE,'NS','Non Sales Funds Transfer','EX','Excess Funds Transfer',A.ADJ_TYPE),'-'), "+//8
																		" NVL(B.REC_AMOUNT,0), "+//9
																		" A.CLIENT_CODE, "+//10
																		" TO_CHAR(SYSDATE,'DD-MM-YYYY'), "+//11
																		" B.SETTLE_MODE, "+//12
																		" B.CHEQUE_NO, "+//13
																		" TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'), "+//14
																		" B.CURR_CODE, "+//15
																		" nvl(B.EXCHANGE_RATE_REP_CURR,0), "+//16
																		" nvl(B.REC_AMOUNT_CURR,0), "+//17
																		" A.REF_NO, "+//18
																		" B.PAYER_BRANCH_CODE, "+ //19
                                    " B.PAYER_ACC_NO "+//20
																		" FROM "+m_schema_name+".FA_OP_PRO_NON_SALE_ADJUSTMENT A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
																		" WHERE A.STATUS = 'E' "+
																		" AND A.RECEIPT_NO = B.RECEIPT_NO "+
																		" ORDER BY A.TRANSFER_DATE,A.FACILITY_NO,A.CLIENT_CODE");
			
			
     boolean more2=rs1.next();
			if(more2){
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>"; 
				m_string=m_string+"<td width='10%' class=div_input><b>Facility No</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Receipt No</b></td>";
				m_string=m_string+"<td width='15%' class=div_input><b>Client/Debtor Name</b></td>";				
				m_string=m_string+"<td width='9%' class=div_input><b>Transfer Date</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Source Document</b></td>";
				m_string=m_string+"<td width='15%' class=div_input><b>Comments</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Receipt Settlement Type</b></td>";
				m_string=m_string+"<td width='9%' class=div_input align='right'><b>Receipt Amount</b></td>";
				m_string=m_string+"<td width='9%' class=div_input align='right'><b>Transfer Amount</b></td>";
				m_string=m_string+"<td width='6%' class=div_input><b></b></td>";
				m_string=m_string+"<td width='5%' class=div_input><b></b></td>";
				m_string=m_string+"</tr>";
			}
				int chk_nums=0;
				int j=1;
				while(more2){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width=\"9%\" class=div_input   >"+rs1.getString(1)+"<INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FAC_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"></td>"+					
					"<td width=\"10%\" class=div_input  onClick=\"show_receipt_details('"+rs1.getString(3)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_REC_NO_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\"><u>"+rs1.getString(3)+"</u></td>"+
          "<td width=\"9%\" class=div_input   >"+rs1.getString(2)+"</td>"+					
          "<td width=\"9%\" class=div_input   >"+rs1.getString(5)+"</td>"+					
					"<td width=\"9%\" class=div_input   >"+rs1.getString(6)+"</td>"+					
					"<td width=\"9%\" class=div_input   >"+rs1.getString(7)+"</td>"+					
					"<td width=\"9%\" class=div_input   >"+rs1.getString(8)+"</td>"+					
					"<td width=\"5%\" class=div_input    align='right' >"+nf.format(rs1.getDouble(9))+"</td>"+					
          "<td width=\"9%\" class=div_input    align='right' >"+nf.format(rs1.getDouble(4))+"<INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_TR_AMT_"+chk_nums+"' VALUE=\""+rs1.getDouble(4)+"\"></td>"+					
					"<td width=\"6%\"><select style='width:82px' name=\"TXT_ACT_"+chk_nums+"\" class='txt_input' ><option value=\"A\" selected>Approve</option><option value=\"C\">Dis Approve</option></select></td>"+
					"<td width=\"5%\"><input type=\"checkbox\" name=\"TXT_REC_OK_"+chk_nums+"\" value='off' onclick=\"chang_status('"+chk_nums+"')\">"+
					"<input type='hidden' name=\"TXT_CLIENT_CODE_"+chk_nums+"\" value=\""+rs1.getString(10)+"\"   >"+
					"<input type='hidden' name=\"TXT_PAY_DATE\"                 value=\""+rs1.getString(11)+"\"   >"+
					"<input type='hidden' name=\"TXT_SET_MODE_"+chk_nums+"\"    value=\""+rs1.getString(12)+"\"   >"+
					"<input type='hidden' name=\"TXT_CHQ_NO_"+chk_nums+"\"      value=\""+rs1.getString(13)+"\"   >"+
					"<input type='hidden' name=\"TXT_CHQ_DATE_"+chk_nums+"\"    value=\""+rs1.getString(14)+"\"   >"+
					"<input type='hidden' name=\"TXT_CUR_CODE_"+chk_nums+"\"    value=\""+rs1.getString(15)+"\"   >"+
					"<input type='hidden' name=\"TXT_EX_RATE_"+chk_nums+"\"     value=\""+rs1.getString(16)+"\"   >"+
					"<input type='hidden' name=\"TXT_AMT_CUR_"+chk_nums+"\"     value=\""+rs1.getString(17)+"\"   >"+
					"<input type='hidden' name=\"TXT_REF_NO_"+chk_nums+"\"      value=\""+rs1.getString(18)+"\"   >"+
					"<input type='hidden' name=\"TXT_PAY_BRNCH_CODE_"+chk_nums+"\"      value=\""+rs1.getString(19)+"\"   >"+
					"<input type='hidden' name=\"TXT_PAY_ACC_CODE_"+chk_nums+"\"      value=\""+rs1.getString(20)+"\"   >"+
					"</td></tr>";
		    	more2=rs1.next();
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
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

