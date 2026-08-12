import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
        
// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:09-01-2007

public class LAKDL_AF_MISF_trail_balance_report_v2 extends javax.servlet.http.HttpServlet {
	
	
	
	public  void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{ // synchronized
		
		Connection conn= null;
	    Statement stmt1= null,stmt_cr= null,stmt_dr= null;
	    CallableStatement callstmt= null;
	    java.text.NumberFormat nf= null,nf1= null;
    
        ResultSet rs1,rs;
	    ResultSet rs_cr= null,rs_dr= null;
	    String m_chksql = null;
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim(); 
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
			stmt_cr=conn.createStatement();
			stmt_dr=conn.createStatement();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("LOAD_TRAIL_BAL_REPORT_LEVEL_1")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_div_code=req.getParameter("div_code");
				String m_prod_code=req.getParameter("prod_code");
				String m_acc_type_code=req.getParameter("acc_type_code");
				
				
			//--------------------------TRAIL BALANCE REPORT - FIRST LEVEL -------------------------------
			  
				
						rs1= stmt1.executeQuery(" SELECT DISTINCT "+
							"  A.ACC_TYPE_CODE ACC_TYPE_CODE,"+//1
							"  B.ACC_TYPE_DESC ACC_TYPE_DESC "+//2
							" FROM "+m_schema_name+".TEMP_TRIAL_BAL_SUP A,"+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE B "+
							" WHERE A.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
							"       A.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
							" AND A.ACC_TYPE_CODE=B.ACC_TYPE_CODE "+
							" AND A.DIVISION_CODE LIKE UPPER('"+m_div_code+"%') "+
							" AND A.PRODUCT_CODE LIKE UPPER('"+m_prod_code+"%') "+
							" AND A.ACC_TYPE_CODE LIKE UPPER('"+m_acc_type_code+"%') "+
							" GROUP BY A.ACC_TYPE_CODE,ACC_TYPE_DESC,DRCR_STATUS ");

 						 String m_acc_code="",m_acc_desc="";
			       boolean m_dataflag=false;							
		         boolean mflag=true;							
				 	   String client_code = "";
					   boolean more = rs1.next();
						 double m_cr_tot=0,m_dr_tot=0;	
						 double m_differ=0;	
					
					 out.println("<HTML><HEAD><TITLE>Trial Balance Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 out.println("	function show_account_type(m_acc_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report_v2?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");
					 out.println("	function show_account_entry(m_trans_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report_v2?chksql=LOAD_ACCOUNT_ENTRY_DRILL&trans_code=\"+m_trans_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");
					 out.println("	function show_second_level_drill(m_acc_type_code,m_acc_desc){");
			     out.println(" 	 m_from_date = '"+m_from_date+"'; ");
			     out.println(" 	 m_to_date = '"+m_to_date+"'; ");
					 out.println(" 	 m_div_code = '"+m_div_code+"'; ");	
					 out.println(" 	 m_prod_code = '"+m_prod_code+"'; ");	
			     out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report_v2?chksql=LOAD_TRAIL_BAL_REPORT_LEVEL_2&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&div_code=\"+m_div_code+\"&prod_code=\"+m_prod_code+\"&acc_type_code=\"+m_acc_type_code+\"&acc_desc=\"+m_acc_desc;");	
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=450,width=800');	");
					 out.println("	}");
					 out.println("</SCRIPT>");
					 out.println("<br>");
		 			 out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Trial Balance Report From "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<hr>");	
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
						out.println("<td width='12%' align='right'><DIV class=div_input><b>Debit</b></DIV></td>"); 
						out.println("<td width='12%' align='right' ><DIV class=div_input><b>Credit</b></DIV></td>"); 
						out.println("</tr>"); 
					  m_dataflag=true; 
					}
					
					while(more){
					  	 
							m_acc_code=rs1.getString(1); 	
							m_acc_desc=rs1.getString(2); 	
							double m_dr_amount=0,m_cr_amount=0;
							
							String m_dr_query ="    SELECT DISTINCT "+
							 									 "  	A.ACC_TYPE_CODE ACC_TYPE_CODE,"+//1
							 									 "  	B.ACC_TYPE_DESC ACC_TYPE_DESC,"+//2
							 									 "  	DECODE(A.DRCR_STATUS,'CR','Credit','DR','Debit') DRCR_STATUS,"+//3
							 									 " 		SUM(A.TRNAMOUNT) TRNAMOUNT    "+//4
																 " FROM "+m_schema_name+".TEMP_TRIAL_BAL_SUP A,"+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE B "+
																 " WHERE A.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
																 " A.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																 " AND A.ACC_TYPE_CODE=B.ACC_TYPE_CODE(+) "+
																 " AND A.DIVISION_CODE LIKE UPPER('"+m_div_code+"%') "+
																 " AND A.PRODUCT_CODE LIKE UPPER('"+m_prod_code+"%') "+
																 " AND A.ACC_TYPE_CODE = '"+m_acc_code+"' "+
																 " AND A.DRCR_STATUS='DR' "+
																 " GROUP BY A.ACC_TYPE_CODE,ACC_TYPE_DESC,DRCR_STATUS ";
							
							rs_dr= stmt_dr.executeQuery(m_dr_query);
							boolean more_dr=rs_dr.next();
							
							if(more_dr){
							m_dr_amount =rs_dr.getDouble(4); 
							}
							else {
							m_dr_amount = 0;
							}
							
							String m_cr_query =" SELECT DISTINCT "+
							 									 " 	 A.ACC_TYPE_CODE ACC_TYPE_CODE,"+//1
							 									 " 	 B.ACC_TYPE_DESC ACC_TYPE_DESC,"+//2
							 									 " 	 DECODE(A.DRCR_STATUS,'CR','Credit','DR','Debit') DRCR_STATUS,"+//3
							 									 " 	 SUM(A.TRNAMOUNT) TRNAMOUNT    "+//4
																 " FROM "+m_schema_name+".TEMP_TRIAL_BAL_SUP A,"+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE B "+
																 " WHERE A.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
																 " A.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																 " AND A.ACC_TYPE_CODE=B.ACC_TYPE_CODE(+) "+
																 " AND A.DIVISION_CODE LIKE UPPER('"+m_div_code+"%') "+
																 " AND A.PRODUCT_CODE LIKE UPPER('"+m_prod_code+"%') "+
																 " AND A.ACC_TYPE_CODE = '"+m_acc_code+"' "+
																 " AND A.DRCR_STATUS='CR' "+
																 " GROUP BY A.ACC_TYPE_CODE,ACC_TYPE_DESC,DRCR_STATUS ";
							
							rs_cr= stmt_cr.executeQuery(m_cr_query);
							boolean more_cr=rs_cr.next();
							
							if(more_cr){
							m_cr_amount = rs_cr.getDouble(4);
							}
							else {
							m_cr_amount = 0;
							}
							
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
								out.println("<td width='1%'></td>"); 
								out.println("<td width='12%' class=div_input onClick=\"show_account_type('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
								out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");

							m_differ = m_dr_amount-m_cr_amount;
								
								//Modified by Mahela on 04-07-2007
								if(m_differ>0){
								m_dr_tot=m_dr_tot+m_differ;
								if(more_dr){ 
								out.println("<td width='12%' align='right' class=div_input onClick=\"show_second_level_drill('"+rs_dr.getString(1)+"','"+rs_dr.getString(2)+"')\" style='cursor:hand' ><u>"+nf.format(m_differ)+"</u></td>");
								}else{
								out.println("<td width='12%' align='right' class=div_input onClick=\"show_second_level_drill('"+rs_cr.getString(1)+"','"+rs_cr.getString(2)+"')\" style='cursor:hand' ><u>"+nf.format(m_differ)+"</u></td>");
								}
								}
								else {
								out.println("<td width='12%' align='right' class=div_input onClick=\"show_second_level_drill('"+m_acc_code+"','"+m_acc_desc+"')\" style='cursor:hand' ><u>0.00 </u></td>");
								//out.println("<td width='12%' align='right' class=div_input onClick=\"show_second_level_drill('"+rs_dr.getString(1)+"','"+rs_dr.getString(2)+"')\" style='cursor:hand' ><u>"+nf.format(m_differ)+"</u></td>");
								}
								if(m_differ<0){
								m_cr_tot=m_cr_tot+(m_differ*-1);
								if(more_cr){ 
								out.println("<td width='12%' align='right' class=div_input onClick=\"show_second_level_drill('"+rs_cr.getString(1)+"','"+rs_cr.getString(2)+"')\" style='cursor:hand' ><u>"+nf.format(m_differ*-1)+"</u></td>");
								}else{
								out.println("<td width='12%' align='right' class=div_input onClick=\"show_second_level_drill('"+rs_dr.getString(1)+"','"+rs_dr.getString(2)+"')\" style='cursor:hand' ><u>"+nf.format(m_differ*-1)+"</u></td>");
								}
								}
								else {
								out.println("<td width='12%' align='right' class=div_input onClick=\"show_second_level_drill('"+m_acc_code+"','"+m_acc_desc+"')\" style='cursor:hand' ><u>0.00 </u></td>");
								//out.println("<td width='12%' align='right' class=div_input onClick=\"show_second_level_drill('"+rs_cr.getString(1)+"','"+rs_cr.getString(2)+"')\" style='cursor:hand' ><u>"+nf.format(m_differ*-1)+"</u></td>");
								}

							out.println("</tr>");
							more = rs1.next();
					}	
					       
      	 		out.println("</table>");
						if(m_dataflag) {	
								out.println("<table align='center' width='100%' class='table' >");						
								out.println("<tr class=pdn_txtpos2>");
								out.println("<td width='33%' align='center'> <B>Total</B> </td>"); 
								out.println("<td width='12%' align='right'><DIV class=div_input><b>"+nf.format(m_dr_tot)+"</b></DIV></td>"); 
								out.println("<td width='12%' align='right' ><DIV class=div_input><b>"+nf.format(m_cr_tot)+"</b></DIV></td>"); 
								out.println("</tr>");
								out.println("</table>");
								out.println("<table align='center' width='100%' class='table' >");						
								out.println("<tr class=pdn_txtpos2>");
								out.println("<td width='33%' align='center' > <B>Difference</B> </td>"); 
								if(m_dr_tot-m_cr_tot >= 0){
								out.println("<td width='12%' align='right'><DIV class=div_input><b>"+nf.format(m_dr_tot-m_cr_tot)+"</b></DIV></td>"); 
								}
								else {
								out.println("<td width='12%' align='right'><DIV class=div_input><b>("+nf.format((m_dr_tot-m_cr_tot)*-1)+")</b></DIV></td>"); 
								}
								out.println("<td width='12%' align='right' ><DIV class=div_input><b>0.00</b></DIV></td>"); 
								out.println("</tr>");
								out.println("</table>");
						}
			  		out.println("<br>"); 
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

			
	    }
			else if(m_chksql.equals("LOAD_TRAIL_BAL_REPORT_LEVEL_2")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_div_code=req.getParameter("div_code");
				String m_prod_code=req.getParameter("prod_code");
				String m_acc_type_code=req.getParameter("acc_type_code");
				String m_acc_desc=req.getParameter("acc_desc");
				//String m_cr_dr_type=req.getParameter("cr_dr_type");
				
			//--------------------------TRAIL BALANCE REPORT - LEVEL 2 -------------------------------
			
			
							rs1= stmt1.executeQuery(" SELECT DISTINCT "+
							  "  NVL(A.TRANSACTION_CODE,'-') TRANSACTION_CODE, "+//1
							  "  B.DESCRIPTION DESCRIPTION, "+//2
  							"	 DECODE(A.DRCR_STATUS,'CR','Credit','DR','Debit') DRCR_STATUS, "+//3
  							"	 SUM(A.TRNAMOUNT) TRNAMOUNT "+//4
							" FROM "+m_schema_name+".TEMP_TRIAL_BAL_SUP A, "+m_schema_name+".CO_FN_MAS_TRANSACTION_CODE B  "+
							" WHERE A.TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
							" A.TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
							" AND A.TRANSACTION_CODE=B.TRANSACTION_CODE(+) "+
							" AND A.DIVISION_CODE LIKE UPPER('"+m_div_code+"%') "+
							" AND A.PRODUCT_CODE LIKE UPPER('"+m_prod_code+"%') "+
							" AND A.ACC_TYPE_CODE LIKE UPPER('"+m_acc_type_code+"%') "+
							//" AND A.DRCR_STATUS='"+m_cr_dr_type+"' "+
							" AND A.ACC_TYPE_CODE='"+m_acc_type_code+"' "+
							" GROUP BY A.TRANSACTION_CODE,DESCRIPTION,DRCR_STATUS ");

			
		         boolean mflag=true;							
				 	   String client_code = "";
					   boolean more = rs1.next();
						 double m_cr_tot=0,m_dr_tot=0;	
					
					 out.println("<HTML><HEAD><TITLE>Trial Balance Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 out.println("	function show_account_type(m_acc_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report_v2?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");
					 out.println("	function show_account_entry(m_trans_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report_v2?chksql=LOAD_ACCOUNT_ENTRY_DRILL&trans_code=\"+m_trans_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");
					 out.println("	function show_third_level_drill(m_trans_code,m_cr_dr_type,m_trans_desc){");
			     out.println(" 	 m_from_date = '"+m_from_date+"'; ");
			     out.println(" 	 m_to_date = '"+m_to_date+"'; ");
					 out.println(" 	 m_div_code = '"+m_div_code+"'; ");	
					 out.println(" 	 m_prod_code = '"+m_prod_code+"'; ");	
					 out.println(" 	 m_acc_type_code = '"+m_acc_type_code+"'; ");		
			     out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report_v2?chksql=LOAD_TRAIL_BAL_REPORT_LEVEL_3&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&div_code=\"+m_div_code+\"&prod_code=\"+m_prod_code+\"&acc_type_code=\"+m_acc_type_code+\"&cr_dr_type=\"+m_cr_dr_type+\"&trans_code=\"+m_trans_code+\"&trans_desc=\"+m_trans_desc;");	
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=450,width=800');	");
					 out.println("	}");	
					 out.println("</SCRIPT>");
						
					 out.println("<br>");
		 			 out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 //out.println("<TR><TD align='Center' ><B> Trial Balance Report </B></TD></TR>");
					 out.println("<TR><TD align='Center' ><B> Trial Balance Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<hr>");	
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> "+m_acc_desc+" </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<hr>");	
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
						out.println("<td width='12%' ><DIV class=div_input><b>Transaction Code</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input><b>Description</b></DIV></td>"); 
						out.println("<td width='12%' align='right'><DIV class=div_input><b>Debit</b></DIV></td>"); 
						out.println("<td width='12%' align='right' ><DIV class=div_input><b>Credit</b></DIV></td>"); 
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
							out.println("<td width='12%' class=div_input onClick=\"show_account_entry('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");
							
							if(rs1.getString(3).equals("Debit")){
							m_dr_tot=m_dr_tot+rs1.getDouble(4);
							out.println("<td width='12%' align='right' class=div_input onClick=\"show_third_level_drill('"+rs1.getString(1)+"','DR','"+rs1.getString(2)+"')\" style='cursor:hand' ><u>"+nf.format(rs1.getDouble(4))+"</u></td>");
							}
							else {
							out.println("<td width='12%' align='right' ><DIV class=div_input>0.00</DIV></td>"); 
							}
							if(rs1.getString(3).equals("Credit")){
							m_cr_tot=m_cr_tot+rs1.getDouble(4);
							out.println("<td width='12%' align='right' class=div_input onClick=\"show_third_level_drill('"+rs1.getString(1)+"','CR','"+rs1.getString(2)+"')\" style='cursor:hand' ><u>"+nf.format(rs1.getDouble(4))+"</u></td>");
							}
							else {
							out.println("<td width='12%' align='right' ><DIV class=div_input>0.00</DIV></td>"); 
							}
							out.println("</tr>");
							more = rs1.next();
							
					}	
					     
      	 		out.println("</table>");
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='33%' align='center'> <B>Total</B> </td>"); 
						out.println("<td width='12%' align='right'><DIV class=div_input><b>"+nf.format(m_dr_tot)+"</b></DIV></td>"); 
						out.println("<td width='12%' align='right' ><DIV class=div_input><b>"+nf.format(m_cr_tot)+"</b></DIV></td>"); 
						out.println("</tr>");
						out.println("</table>");
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='33%' align='center' > <B>Difference</B> </td>"); 
						if(m_dr_tot-m_cr_tot >= 0){
						out.println("<td width='12%' align='right'><DIV class=div_input><b>"+nf.format(m_dr_tot-m_cr_tot)+"</b></DIV></td>"); 
						}
						else {
						out.println("<td width='12%' align='right'><DIV class=div_input><b>("+nf.format((m_dr_tot-m_cr_tot)*-1)+")</b></DIV></td>"); 
						}
						out.println("<td width='12%' align='right' ><DIV class=div_input><b>0.00</b></DIV></td>"); 
						out.println("</tr>");
						out.println("</table>");
			  		out.println("<br>"); 
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

			
	    }	   
			else if(m_chksql.equals("LOAD_TRAIL_BAL_REPORT_LEVEL_3")){
				   
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_div_code=req.getParameter("div_code");
				String m_prod_code=req.getParameter("prod_code");
				String m_acc_type_code=req.getParameter("acc_type_code");
				String m_cr_dr_type=req.getParameter("cr_dr_type");
				String m_trans_code=req.getParameter("trans_code");
				String m_trans_desc=req.getParameter("trans_desc");
				
				
			//--------------------------TRAIL BALANCE REPORT - LEVEL 3 -------------------------------
			
			
							rs1= stmt1.executeQuery(" SELECT "+
							  "  NVL(DOCREFNO,'-') DOCREFNO,"+//1
							  "  INITCAP(NVL(PROC_DESC,'-')) PROC_DESC, "+//2
							  "  TRNAMOUNT, "+//3
								"  TO_CHAR(TRNDATE,'DD-MM-YYYY'), "+//4
						    "  TRANSACTION_CODE, "+//5
						    "  ACC_TYPE_CODE, "+//6
						    "  CORR_ACC_NO "+//7
							" FROM "+m_schema_name+".TEMP_TRIAL_BAL_SUP "+
							" WHERE TRNDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
							" TRNDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
							" AND DIVISION_CODE LIKE UPPER('"+m_div_code+"%') "+
							" AND PRODUCT_CODE LIKE UPPER('"+m_prod_code+"%') "+
							" AND ACC_TYPE_CODE LIKE UPPER('"+m_acc_type_code+"%') "+
							" AND DRCR_STATUS='"+m_cr_dr_type+"' "+
							" AND TRANSACTION_CODE='"+m_trans_code+"' "+
							" AND ACC_TYPE_CODE='"+m_acc_type_code+"' ");

			
		         boolean mflag=true;							
				 	   String client_code = "";
					   boolean more = rs1.next();
						 double m_cr_tot=0,m_dr_tot=0;	
					
					 out.println("<HTML><HEAD><TITLE>Trial Balance Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 out.println("	function show_account_type(m_acc_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report_v2?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");
					 out.println("	function show_account_entry(m_trans_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report_v2?chksql=LOAD_ACCOUNT_ENTRY_DRILL&trans_code=\"+m_trans_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");
					 out.println("</SCRIPT>");
					 out.println("<br>");
		 			 out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 //out.println("<TR><TD align='Center' ><B> Trial Balance Report </B></TD></TR>");
					 out.println("<TR><TD align='Center' ><B> Trial Balance Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");	
					 out.println("</TABLE>");
					 out.println("<hr>");	
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> "+m_trans_desc+" </B></TD></TR>");	
					 out.println("</TABLE>");
					 out.println("<hr>");		
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
								out.println("<td width='12%' ><DIV class=div_input><b>Document Ref No</b></DIV></td>");
								out.println("<td width='20%' ><DIV class=div_input><b>Description</b></DIV></td>"); 
								out.println("<td width='12%' ><DIV class=div_input><b>Trans. Date</b></DIV></td>");
								out.println("<td width='12%' ><DIV class=div_input><b>Trans. Code</b></DIV></td>");
								out.println("<td width='12%' ><DIV class=div_input><b>Acc. Code</b></DIV></td>");
								out.println("<td width='12%' ><DIV class=div_input><b>Corres. Acc. Code</b></DIV></td>");
								out.println("<td width='12%' align='right'><DIV class=div_input><b>Amount</b></DIV></td>"); 
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
									if((rs1.getString(1).substring(0,2)).equals("SR")){
									out.println("<td width='12%' class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
									}
									else if((rs1.getString(1).substring(0,2)).equals("IN")){
									out.println("<td width='12%' class=div_input onClick=\"show_invoice_drill('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
									}
									else if((rs1.getString(1).substring(0,2)).equals("AP")){
									out.println("<td width='12%' class=div_input onClick=\"show_application_detail_drill('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
									}
									else if((rs1.getString(1).substring(0,2)).equals("FI")){
									out.println("<td width='12%' class=div_input onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
									}
									//Added by nuwan de silva==========================
									else if((rs1.getString(1).substring(0,2)).equals("SP")){
									out.println("<td width='12%' class=div_input onClick=\"show_payment_drill('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
									}
									//=====================================================
									else {
									out.println("<td width='12%' class=div_input onClick=\"\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
									}
									out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");
									out.println("<td width='12%' class=div_input onClick=\"\" >"+rs1.getString(4)+"</td>");
									out.println("<td width='12%' class=div_input onClick=\"show_account_entry('"+rs1.getString(5)+"')\" style='cursor:hand' ><u>"+rs1.getString(5)+"</u></td>");
									out.println("<td width='12%' class=div_input onClick=\"show_account_type('"+rs1.getString(6)+"')\" style='cursor:hand' ><u>"+rs1.getString(6)+"</u></td>");
									out.println("<td width='12%' class=div_input onClick=\"show_account_type('"+rs1.getString(7)+"')\" style='cursor:hand' ><u>"+rs1.getString(7)+"</u></td>");
									m_dr_tot=m_dr_tot+rs1.getDouble(3);
									out.println("<td width='12%' align='right' class=div_input >"+nf.format(rs1.getDouble(3))+"</td>");
									out.println("</tr>");
									more = rs1.next();
							}	
					   
      	 		out.println("</table>");
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='81%' align='center'> <B>Total</B> </td>"); 
						out.println("<td width='12%' align='right'><DIV class=div_input><b>"+nf.format(m_dr_tot)+"</b></DIV></td>"); 
						out.println("</tr>");
						out.println("</table>");
			  		out.println("<br>"); 
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

			
	    }
			else if(m_chksql.equals("LOAD_ACC_TYPE_CODE_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_acc_type_code=req.getParameter("acc_type_code");	
					
					
					 rs= stmt1.executeQuery(" SELECT "+
						  "  ACC_TYPE_CODE,"+//1
						  "  NVL(ACC_TYPE_DESC,'-'),"+//2
						  "  NVL(ACC_TYPE_CATEGORY,'-'),"+//3
						  "  NVL(DECODE(STATUS,'Y','Yes','N','No'),'-'),"+//4
						  "  INITCAP(DECODE(DIVISION_CODE,'AF','ASSET FINANCE - LEASING / LOANS','AD','ADMINISTRATION','FA','FACTORING','MISF','MANAGEMENT INFORMATION')) "+//5
						" FROM "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE "+
						" WHERE ACC_TYPE_CODE='"+m_acc_type_code+"' ");

				boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE>Account Type Details - Account Type Code: "+m_acc_type_code+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Account Type Details - Account Type Code: "+m_acc_type_code+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Account Type Code: "+m_acc_type_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Account Type Code</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Description</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Account Type Category</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<tr>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Status</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Division</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
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
			else if(m_chksql.equals("LOAD_ACCOUNT_ENTRY_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_trans_code=req.getParameter("trans_code");	
					
					
						rs= stmt1.executeQuery(" SELECT "+
						  "  NVL(ACCOUNT_CODE,'-'),"+
						  "  NVL(DECODE(DR_CR_STATUS,'DR','Debit','CR','Credit'),'-'),"+
						  "  NVL(DESCRIPTION,'-') "+
						 " FROM "+m_schema_name+".CO_FN_MAS_ACCOUNT_ENTRY "+
						 " WHERE TRASACTION_CODE='"+m_trans_code+"' ");

        boolean mflag=true;							
				boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE>Account Entry Details - Transaction Code: "+m_trans_code+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<SCRIPT language=\"JavaScript\">"); 
					out.println("	function show_account_type(m_acc_code){");
					out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report_v2?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					out.println("	}");
					out.println("	function show_account_entry(m_trans_code){");
					out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report_v2?chksql=LOAD_ACCOUNT_ENTRY_DRILL&trans_code=\"+m_trans_code+\"\";");
					out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					out.println("	}");
					out.println("</SCRIPT>");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Account Entry Details - Transaction Code: "+m_trans_code+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Transaction Code: "+m_trans_code+" </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				if(more_dir){
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Account Code</b></td>");
					out.println("<td width='20%' class=div_input><b>Status</b></td>");
					out.println("<td width='20%' class=div_input><b>Description</b></td>");
					out.println("</tr>");
				}
				
				  
				while(more_dir){
					count++;
					if(mflag){
						out.println("<tr class=tr_input>");
						mflag=false;
					}
					else{
						out.println("<tr class=tr_input1>");
						mflag=true;
					}
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input onClick=\"show_account_type('"+rs.getString(1)+"')\" style='cursor:hand' ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='20%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='20%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("</tr>");
					more_dir = rs.next();
				}
				  out.println("</table>");
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






