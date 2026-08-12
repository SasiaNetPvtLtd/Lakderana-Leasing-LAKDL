import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
            
// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
  
public class LAKDL_FA_OP_PRO_sql_client_availability_new_scr_drill_2 extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1,stmt2;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
  public ResultSet rs1,rs2;
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
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

			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
		

			else if(m_chksql.equals("LOAD_CLIENT_AVAILABILITY_DRILL")){

				String m_client_code=req.getParameter("CLIENT_CODE");
				String m_facility_code=req.getParameter("FACILITY_NO");
				String m_option_no=req.getParameter("OPTION_NO");
				
	    if(m_option_no.equals("OPT29")){
					double m_val=0;
					double m_val1=0;

 		    rs1= stmt1.executeQuery("SELECT "+
						" A.POD_REF_NO, "+
						" A.CHEQUE_NO, "+
						" NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-'), "+
						" A.CHEQUE_AMOUNT, "+
						" NVL(TO_CHAR(A.PD_REALISE_DATE,'DD-MM-YYYY'),'-'), "+
						" A.CLIENT_CODE, "+ //6
						" A.FACILITY_NO, "+ //7
						" NVL(A.DEBTOR_CODE,'-'), "+ //8
						" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'), "+ //9
						" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-'), "+ //10
						" NVL(A.CANCEL_COMMENT,' '), "+ //11
						" NVL(A.PRINT_STATUS,'-'),B.REF_NO "+//12 Added by Dineth on 2009-02-19
						" FROM  "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A, "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_LETTER B "+
						" WHERE A.CLIENT_CODE='"+m_client_code+"'  AND A.FACILITY_NO='"+m_facility_code+"' AND A.POD_STATUS='F' "+  //D COMMENT BY NS 21-09-2009
						" AND   B.POD_REF_NO(+)=A.POD_REF_NO "+
						
						//===============COMMENT BY NUWAN DE SILVA 15-07-2009
						//" ORDER BY PD_REALISE_DATE "+ 
					  //Added by Dineth on 2009-02-20
						
						" UNION ALL  "+
					
						" SELECT  "+
						" A.RECEIPT_NO RECEIPT_NO, "+
						" A.CHEQUE_NO CHEQUE_NO, "+
						" NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-') CHEQUE_DATE, "+
						" A.REC_AMOUNT REC_AMOUNT "+
						" ,NVL(TO_CHAR(A.REALISED_DATE,'DD-MM-YYYY'),'-') "+
						" ,A.CLIENT_CODE CLIENT_CODE "+//6
						" ,A.FACILITY_NO FACILITY_NO"+//7
						" ,NVL(DEBTOR_CODE,'-') DEBTOR_CODE, "+//8
						" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'), "+ //9
						" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-'), "+ //10
						" ' ', "+ //11
						" NVL(A.PRINT_STATUS,'-'), "+//12
						" NVL(B.REF_NO,'-')  "+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A ,"+
						"      "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_LETTER B"+
				  	    " WHERE  RECEIPT_NO=B.POD_REF_NO(+) "+
						" AND   A.REC_STATUS='D' "+ //CHANGED BY NS ON 10-08-2011 'C'
						" AND   A.RECON_STATUS IS NULL "+
						" AND CLIENT_CODE ='"+m_client_code+"' "+
						" AND FACILITY_NO ='"+m_facility_code+"' "+
						
						
						"");
						
					//End by Dineth on 2009-02-20	
						
						

					out.println("<HTML><HEAD><TITLE></TITLE>");
					out.println("<SCRIPT language='JavaScript'>");
					out.println(" function Generate_Letter(m_facility_no,m_client_no,m_ref_no){");
					//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PD_Cancel_cheque_letter?finance_no=\"+m_facility_no+\"&client=\"+m_client_no+\"&chksql=main_page\";"); 
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_Cancel_cheque_sent_letter?finance_no=\"+m_facility_no+\"&client=\"+m_client_no+\"&ref_no=\"+m_ref_no+\"&chksql=main_page\";"); 
					

					//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_Unbank_cheque_letter?finance_no="+m_facility_no+"&client="+m_client_no+"&chksql=main_page\";");  //comment by nuwan de silva
					out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=720,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
			
					out.println(" }");
					out.println("</SCRIPT>");
					out.println("</HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					
					
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>TOTAL CANCELLED POD CHEQUES</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Ref No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Cheque Date</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Cheque Amt</b></DIV></td>");
					out.println("<td width='10%' ><b>Realise Date</b></td>");
					out.println("<td width='10%' ><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Comment</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Letter Status</b></DIV></td>");//Added by Dineth on 2009-02-19
					out.println("<td width='15%' ><DIV class=div_input><b>Reference</b></DIV></td>");
					out.println("</tr>");
					
					int j=1;
					
					while(rs1.next()){
						if(j==0){
							out.println("<tr bgcolor=\"#FFFFFF\">");
							j=1;
						}
						else{
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							j=0;
						}
						 m_val1=m_val1+rs1.getDouble(4);
						if(rs1.getString(1).substring(0,2).equals("PD")){
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_pod_cheque_details('"+rs1.getString(1)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						}else if(rs1.getString(1).substring(0,2).equals("FR")){
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_receipt_details('"+rs1.getString(1)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						}
						else{
						out.println("<td width='10%' class=div_input>"+rs1.getString(1)+"</td>");
						}
						out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(5)+"</td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(6)+"')\"><u>"+rs1.getString(9)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(8)+"')\"><u>"+rs1.getString(10)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(11)+"</td>");
						//Added by Dineth on 2009-02-19
						if(rs1.getString(12).equals("N")){
						//out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"Generate_Letter('"+rs1.getString(7)+"','"+rs1.getString(6)+"','"+rs1.getString("REF_NO")+"')\"><u>Pending</u></td>");
						out.println("<td width='15%' class=div_input >Pending</td>");
						}
						else if(rs1.getString(12).equals("Y")){
						//out.println("<td width='15%' class=div_input >Sent</td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"Generate_Letter('"+rs1.getString(7)+"','"+rs1.getString(6)+"','"+rs1.getString("REF_NO")+"')\"><u>Sent</u></td>");
						}
						else{
						out.println("<td width='15%' class=div_input >Pending</td>");
						}
						out.println("<td width='15%' class=div_input >"+rs1.getString(13)+"</td>");
						//End by Dineth on 2009-02-19
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val1)+"</b></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}				
				
		
			}
			else {
			    out.println("Undefined");
			}
			
			stmt1.close();
			stmt2.close();
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

