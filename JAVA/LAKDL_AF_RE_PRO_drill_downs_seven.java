

//CREATED BY DINETH ON 22-07-2009
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_RE_PRO_drill_downs_seven extends javax.servlet.http.HttpServlet {
	/*
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt_invoice,stmt3;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;
  CallableStatement callstmt1 =null;
  public ResultSet rs,rs1,rs2,rs_invoice,rs3;
	public String m_chksql;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		Connection conn=null;
	Statement stmt=null,stmt1=null,stmt2=null,stmt_invoice=null,stmt3=null;
	CallableStatement callstmt=null;
	java.text.NumberFormat nf=null,nf1=null;
	java.lang.Math a=null;
  CallableStatement callstmt1 =null;
   ResultSet rs=null,rs1=null,rs2=null,rs_invoice=null,rs3=null;
	 String m_chksql=null;
		
		try {
		
		
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_username=m_sn_methods.username;
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
			stmt_invoice=conn.createStatement();
			stmt3=conn.createStatement();
			stmt2=conn.createStatement();
			
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			
			if(m_chksql.equals("ODI_ALL_INVOICE")){
					String m_fin_no_1=req.getParameter("FINANCE_NO");
					//String m_odi_date=req.getParameter("ODI_DATE");
					String m_odi_month="";
					String m_odi_date1="";
					String m_odi_date2="";
					String m_invoice_no="";
					String m_odi_refno="";
					String m_invoice_no1="";
					int m_count=0;
					String m_inv_no="";
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_SAVE_ODI_DETAILS_NEW(:1,:2,:3);END;");
		
			  	callstmt1.setString(1,m_fin_no_1);	
			  	callstmt1.setString(2,"");
			  	callstmt1.setString(3,m_username);
			  	callstmt1.execute();
			    //out.println("test");
					/*rs=stmt.executeQuery(" SELECT TO_CHAR(TO_DATE('"+m_odi_date+"','DD-MM-YYYY'),'Month YYYY'),'01-'||TO_CHAR(TO_DATE('"+m_odi_date+"','DD-MM-YYYY'),'MM-YYYY') FROM DUAL");
					
					if(rs.next()){
					  m_odi_month=rs.getString(1);
						m_odi_date1=rs.getString(2);
					}
					*/
					
					/*rs=stmt.executeQuery(" SELECT A.INVOICE_NO,D.INVOICE_DESC,NVL(TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY'),'-'),TO_CHAR(TO_DATE("+m_schema_name+".AF_CO_GET_LAST_PAY_DATE_NEW_1(B.FINANCE_NO,TO_CHAR(SYSDATE,'DD/MM/YYYY')),'DD-MM-YYYY'),'DD-MM-YYYY'),NVL("+m_schema_name+".AF_CO_GET_ARR_AMT_INV(B.FINANCE_NO,A.INVOICE_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'),B.CLIENT_CODE),0),NVL(SUM(A.ODI_CAL_AMOUNT),0), "+ 
                                        " NVL(SUM(A.ODI_BAL_AMOUNT),0),NVL(SUM(A.ODI_SETTLED_AMOUNT),0),NVL(SUM(A.ADJUSTED_AMOUNT),0),A.ODI_REF_NO "+ 
                                        " FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A,"+m_schema_name+".AF_CO_PRO_INVOICE B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
																				" "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD D "+
                                        " WHERE A.INVOICE_NO=B.INVOICE_NO AND B.FINANCE_NO=C.FINANCE_NO AND "+ 
                                        " C.FINANCE_NO='"+m_fin_no_1+"' AND TO_DATE(TO_CHAR(A.ODI_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')=TO_DATE('"+m_odi_date+"','DD-MM-YYYY') "+
                                        " AND B.INVOICE_TYPE = D.INVOICE_TYPE_CODE "+
																				" AND A.ODI_BAL_AMOUNT>0 "+
																				" AND TO_DATE('"+m_odi_date1+"','DD-MM-YYYY')<=TO_DATE(TO_CHAR(A.ODI_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																        " AND ADD_MONTHS(TO_DATE('"+m_odi_date1+"','DD-MM-YYYY'),1)>=TO_DATE(TO_CHAR(A.ODI_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																				" GROUP BY A.INVOICE_NO,D.INVOICE_DESC,B.VALUE_DATE,B.FINANCE_NO,A.ODI_REF_NO,B.CLIENT_CODE ");
						*/
						    				
						
						
						rs=stmt.executeQuery(" SELECT DISTINCT INVOICE_NO,INVOICE_TYPE_DESC,TO_CHAR(VALUE_DATE,'DD-MM-YYYY'),NVL(INVOICE_AMT,0),"+
                                 " NVL(INV_SETTLED_AMT,0),NVL(TO_CHAR(INV_SETTLED_DATE,'DD-MM-YYYY'),'-'),NVL(INVOICE_BALANCE,0),NVL(NO_OF_DAYS,0),NVL(ODI_CAL_AMT,0),NVL(ODI_SETTLED_AMT,0), "+
                                 " NVL(ODI_WAVED_OFF,0),NVL(ODI_BALANCE_AMT,0),ENT_USER,ENT_DATE,INV_SETTLED_DATE,VALUE_DATE,LEGAL_ODI "+
																 " FROM "+m_schema_name+".AF_CO_TBD_ODI_DETAILS "+
																 " WHERE ENT_USER='"+m_username+"' ORDER BY VALUE_DATE,INVOICE_NO,INV_SETTLED_DATE ASC ");//VALUE_DATE Added By Sandun on 19-08-2009
																	
           										
																						
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>Od Interest Details(Monthly)</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">"); 
					out.println("function load_rec_details(m_inv_no){ ");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_five?chksql=REC_DETAILS&INVOICE_NO=\"+m_inv_no;");
					out.println("popupwin=window.open(m_url,\"popupwin1\",\"status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1\");");
					out.println(" } ");
				  
					
					out.println("function load_user_details(m_inv_no){ ");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_five?chksql=USER_DETAILS&INVOICE_NO=\"+m_inv_no;");
					out.println("popupwin=window.open(m_url,\"popupwin1\",\"status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1\");");
					out.println(" } ");
				
					out.println("</script>");
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
					out.println("<FORM NAME='Form1' method='post'>");
					out.println("<table align='center' width='100%' class='table' border=0 cellspacing=\"0\" cellpadding=\"1\">");
					out.println("<tr><td class=\"pdn_txtpos2\" style='text-align:center'>Invoice Base</td></tr>");
					out.println("</table>");
					
					int m_count3=0;
					double m_act_odi=0;
					double m_bal_odi=0;
					double m_collected=0;
					double m_waved_off=0;
			    double m_inv_balance=0;
					double m_legal_odi=0;
					double m_temp_act_odi=0;
					double m_temp_bal_odi=0;
					double m_temp_col_odi=0;
					double m_temp_wav_odi=0;
					double m_temp_inv_bal=0;
					double m_temp_leg_odi=0;
					boolean more=rs.next();
					if(!more){
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' border=0>");
					out.println("<tr><td style='text-align:center'><b>No Records</b></td></tr>");
					out.println("</table>");
					
					
					}
					if(more){
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' border=0>");
					out.println("<tr>");
					out.println("<td width='10%' ><b>Invoice No</b></td>");
					out.println("<td width='10%' style='text-align:right'><b>Invoice Type</b></td>");
					out.println("<td width='10%' style='text-align:right'><b>Value Date</b></td>");
					out.println("<td width='10%' style='text-align:right'><b>Invoice Settled Date</b></td>");
					out.println("<td width='10%' style='text-align:right'><b>Invoice Amount</b></td>");
					
					out.println("<td width='10%' style='text-align:right'><b>Invoice Settled Amount</b></td>");
					

					out.println("<td width='10%' style='text-align:right'><b>Invoice Balance</b></td>");
					
					//out.println("<td width='10%' style='text-align:right'><b>Last Payment Date</b></td>");
					out.println("<td width='10%' style='text-align:right'><b>No Of Days</b></td>");
					out.println("<td width='10%' style='text-align:right'><b>ODI Calculate Amount</b></td>");
					
					out.println("<td width='10%' style='text-align:right'><b>ODI Settled Amount</b></td>");
					out.println("<td width='10%' style='text-align:right'><b>ODI Adjusted Amount</b></td>");
					out.println("<td width='10%' style='text-align:right'><b>Legal ODI</b></td>");

					out.println("<td width='10%' style='text-align:right'><b>ODI Balance Amount</b></td>");
					out.println("</tr>");
					while(more){
					m_invoice_no=rs.getString(1);
					//if(m_inv_no.equals(rs.getString(1))){//Commented by Dineth on 23-07-2009
					//  m_count3++;
					
					//}
					/*rs1 = stmt1.executeQuery(" SELECT A.INVOICE_NO,TO_CHAR(A.ODI_DATE,'DD-MM-YYYY') "+
                                 " FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A "+
                                 " WHERE A.INVOICE_NO='"+m_invoice_no+"'"+
																 " AND TO_DATE('"+m_odi_date1+"','DD-MM-YYYY')<=TO_DATE(TO_CHAR(A.ODI_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																 " AND ADD_MONTHS(TO_DATE('"+m_odi_date1+"','DD-MM-YYYY'),1)>=TO_DATE(TO_CHAR(A.ODI_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																 " ORDER BY A.ODI_DATE ASC ");
																	
					boolean more_inv=rs1.next();
					while(more_inv){
					if(m_count==0){
					
					rs2=stmt2.executeQuery(" SELECT NVL(TO_DATE('"+rs1.getString(2)+"','DD-MM-YYYY')-TO_DATE('"+m_odi_date1+"','DD-MM-YYYY'),0) FROM DUAL");
					}
					else{
					if(m_invoice_no!=m_invoice_no1){
					rs2=stmt2.executeQuery(" SELECT NVL(TO_DATE('"+rs1.getString(2)+"','DD-MM-YYYY')-TO_DATE('"+m_odi_date1+"','DD-MM-YYYY'),0) FROM DUAL");
					}else{
					rs2=stmt2.executeQuery(" SELECT NVL(TO_DATE('"+rs1.getString(2)+"','DD-MM-YYYY')-TO_DATE('"+m_odi_date2+"','DD-MM-YYYY'),0) FROM DUAL");
					}
					}
					if(rs2.next()){*/
					if(m_count3>0 && !m_inv_no.equals(rs.getString(1))){
					out.println("<tr>");
					out.println("<td colspan=6 style='text-align:left'><b>"+m_inv_no+" Sub Total</b></td>");
					//out.println("<td width='10%'>&nbsp;</td>");
					//out.println("<td width='10%'>&nbsp;</td>");
					//out.println("<td width='10%'>&nbsp;</td>");
					//out.println("<td width='10%'>&nbsp;</td>");
					//out.println("<td width='10%'>&nbsp;</td>");
					out.println("<td width='10%' style='text-align:right'><b>"+nf.format(m_temp_inv_bal)+"</b></td>");
					//out.println("<td width='10%'>&nbsp;</td>");
					out.println("<td width='10%' style='text-align:right'>&nbsp;</td>");
					out.println("<td width='10%' style='text-align:right'><b>"+nf.format(m_temp_act_odi)+"</b></td>");
					
					out.println("<td width='10%' style='text-align:right'><b>"+nf.format(m_temp_col_odi)+"</b></td>");
					out.println("<td width='10%' style='text-align:right'><b>"+nf.format(m_temp_wav_odi)+"</b></td>");
					out.println("<td width='10%' style='text-align:right'><b>"+nf.format(m_temp_leg_odi)+"</b></td>");
					out.println("<td width='10%' style='text-align:right'><b>"+nf.format(m_temp_bal_odi)+"</b></td>");
					out.println("</tr>");
					out.println("<tr><td colspan='14'><hr></td></tr>");
					
					m_temp_act_odi=0;
					m_temp_bal_odi=0;
					m_temp_col_odi=0;
					m_temp_inv_bal=0;
					m_temp_wav_odi=0;
					m_temp_leg_odi=0;
					m_count3=0;
					}
					
					
					
					
					out.println("<tr>");
					out.println("<td width='10%' STYLE='{cursor:hand;}' onclick=\"show_invoice_drill('"+rs.getString(1)+"');\"><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' style='text-align:right'>"+rs.getString(2)+"</td>");
					out.println("<td width='10%' style='text-align:right'>"+rs.getString(3)+"</td>");
					out.println("<td width='10%' style='text-align:right'>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' style='text-align:right'>"+nf.format(rs.getDouble(4))+"</td>");
					
					out.println("<td width='10%' style='text-align:right'>"+nf.format(rs.getDouble(5))+"</td>");
					
					
					out.println("<td width='10%' style='text-align:right'>"+nf.format(rs.getDouble(7))+"</td>");
					//out.println("<td width='10%' style='text-align:right'>"+rs.getString(4)+"</td>");
					out.println("<td width='10%' style='text-align:right'>"+rs.getString(8)+"</td>");
					out.println("<td width='10%' style='text-align:right;cursor:hand;' >"+nf.format(rs.getDouble(9))+"</td>");
					
					out.println("<td width='10%' style='text-align:right;cursor:hand;' >"+nf.format(rs.getDouble(10))+"</td>");
					out.println("<td width='10%' style='text-align:right'>"+nf.format(rs.getDouble(11))+"</td>");
					out.println("<td width='10%' style='text-align:right'>"+nf.format(rs.getDouble(17))+"</td>");
					out.println("<td width='10%' style='text-align:right'>"+nf.format(rs.getDouble(12))+"</td>");
					out.println("</tr>");
					
					
					
					
					
					m_inv_no=rs.getString(1);
					if(m_count3==0){
					m_temp_act_odi=rs.getDouble(9);
					m_temp_bal_odi=rs.getDouble(12);
					m_temp_col_odi=rs.getDouble(10);
					m_temp_wav_odi=rs.getDouble(11);
					m_temp_inv_bal=rs.getDouble(7);
					m_temp_leg_odi=rs.getDouble(17);
					}else{
					m_temp_act_odi+=rs.getDouble(9);
					m_temp_bal_odi+=rs.getDouble(12);
					m_temp_col_odi+=rs.getDouble(10);
					m_temp_wav_odi+=rs.getDouble(11);
					m_temp_inv_bal+=rs.getDouble(7);
					m_temp_leg_odi+=rs.getDouble(17);
					
					
					}
					
					m_act_odi=m_act_odi+rs.getDouble(9);
					m_bal_odi=m_bal_odi+rs.getDouble(12);
					m_collected=m_collected+rs.getDouble(10);
					m_waved_off=m_waved_off+rs.getDouble(11);
					m_inv_balance=m_inv_balance+rs.getDouble(7);
					m_legal_odi=m_legal_odi+rs.getDouble(17);
					//}
					/*m_odi_date2=rs1.getString(2);
					m_invoice_no1=rs.getString(10);
					m_count++;*/
					//more_inv=rs1.next();
					//}
					
					
					m_count3++;
					more=rs.next();
					if(!more){
					out.println("<tr>");
					out.println("<td colspan=6 style='text-align:left'><b>"+m_inv_no+" Sub Total</b></td>");
					//out.println("<td width='10%'>&nbsp;</td>");
					//out.println("<td width='10%'>&nbsp;</td>");
					//out.println("<td width='10%'>&nbsp;</td>");
					//out.println("<td width='10%'>&nbsp;</td>");
					//out.println("<td width='10%'>&nbsp;</td>");
					out.println("<td width='10%' style='text-align:right'><b>"+nf.format(m_temp_inv_bal)+"</b></td>");
					//out.println("<td width='10%'>&nbsp;</td>");
					out.println("<td width='10%' style='text-align:right'>&nbsp;</td>");
					out.println("<td width='10%' style='text-align:right'><b>"+nf.format(m_temp_act_odi)+"</b></td>");
					
					out.println("<td width='10%' style='text-align:right'><b>"+nf.format(m_temp_col_odi)+"</b></td>");
					out.println("<td width='10%' style='text-align:right'><b>"+nf.format(m_temp_wav_odi)+"</b></td>");
					out.println("<td width='10%' style='text-align:right'><b>"+nf.format(m_temp_leg_odi)+"</b></td>");
					out.println("<td width='10%' style='text-align:right'><b>"+nf.format(m_temp_bal_odi)+"</b></td>");
					out.println("</tr>");
					out.println("<tr><td colspan='14'><hr></td></tr>");
					
					m_temp_act_odi=0;
					m_temp_bal_odi=0;
					m_temp_col_odi=0;
					m_temp_inv_bal=0;
					m_temp_wav_odi=0;
					m_temp_leg_odi=0;
					m_count3=0;
					
					
					
					}
					}
					out.println("<tr>");
					out.println("<td width='10%'>&nbsp;</td>");
					out.println("<td width='10%'>&nbsp;</td>");
					out.println("<td width='10%'>&nbsp;</td>");
					out.println("<td width='10%'>&nbsp;</td>");
					out.println("<td width='10%'>&nbsp;</td>");
					out.println("<td width='10%'>&nbsp;</td>");
					//out.println("<td width='10%' style='text-align:right'><b>"+nf.format(m_inv_balance)+"</b></td>");
					out.println("<td width='10%'>&nbsp;</td>");
					out.println("<td width='10%' style='text-align:right'>&nbsp;</td>");
					out.println("<td width='10%' style='text-align:right'><b>"+nf.format(m_act_odi)+"</b></td>");
					
					out.println("<td width='10%' style='text-align:right'><b>"+nf.format(m_collected)+"</b></td>");
					out.println("<td width='10%' style='text-align:right'><b>"+nf.format(m_waved_off)+"</b></td>");
					out.println("<td width='10%' style='text-align:right'><b>"+nf.format(m_legal_odi)+"</b></td>");
					out.println("<td width='10%' style='text-align:right'><b>"+nf.format(m_bal_odi)+"</b></td>");
					out.println("</tr>");
					
					
					
					
					out.println("</table>");
					}
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
					out.println("</body>"); 
					out.println("</html>"); 
		
			
			
					
		
			
			}

			


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
