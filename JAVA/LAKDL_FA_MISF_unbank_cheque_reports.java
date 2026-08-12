import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
        
// DEVELOP BY : ASHINI FOR OFSCL FACTORING    DATE:15-02.2008

public class LAKDL_FA_MISF_unbank_cheque_reports extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1,stmt2,stmt3,stmt4,stmt5,stmt6,stmt7,stmt8,stmt9;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
  public ResultSet rs1,rs2,rs3,rs4,rs5,rs6,rs7,rs8,rs9;
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
			stmt4=conn.createStatement();
			stmt5=conn.createStatement();
			stmt6=conn.createStatement();
			stmt7=conn.createStatement();
			stmt8=conn.createStatement();
			stmt9=conn.createStatement();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			
			else if(m_chksql.equals("LOAD_UNBANK_REPORT")){ 
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_facility_code=req.getParameter("facility_no");
				
				out.println("<HTML><HEAD><TITLE>Total Return Cheques Send to Client Report</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				
				out.println("<script>");
				
				out.println("function Generate_Letter(m_facility_code,m_client_code,m_date,m_ref_no) {");//Modified by Dineth on 2009-02-19
				out.println("m_url=\""+m_class_url+"/"+m_schema_name+"_FA_OP_Unbank_cheque_letter?chksql=main_page&finance_no=\"+m_facility_code+\"&date=\"+m_date+\"&ref_no=\"+m_ref_no+\"&client=\"+m_client_code+\"&print=FALSE\";");  //Modified by Dineth on 2009-02-19
				out.println(" popupwin=window.open(m_url,'displayWindow2','left=110,top=110,width=750,height=600,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				out.println("}");
				
				out.println("</script>");	


				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B>Return Cheques Send to Client Report for "+m_facility_code+" Period "+m_from_date+" - "+m_to_date+"</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<br>");
				out.println("<p class=pdn_txtpos2><center><u><b>Total Return Cheques Send to Client Report</b></u></center></p>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				
				//Commented by Dineth on 2009-02-19
				/*
				out.println("<td width='15%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
				out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
				out.println("<td width='7%' align=right><DIV class=div_input><b>Chq No</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Bank - Branch</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Amount</b></DIV></td>");
				out.println("<td width='20%' align=right><DIV class=div_input><b>Remark</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Generated User</b></DIV></td>");
				out.println("<td width='13%' align=right><DIV class=div_input><b>Generated Time</b></DIV></td>");
  			out.println("<td width='10%' align=center><DIV class=div_input><b>Letter</b></DIV></td>");
				*/
				//Added by Dineth on 2009-02-19
				out.println("<td width='20%' ><DIV class=div_input><b>Reference No</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input><b>Client No</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Letter Generated Date</b></DIV></td>");
				out.println("<td width='10%' align=center><DIV class=div_input><b>Letter</b></DIV></td>");
				out.println("</tr>");
				//End by Dineth on 2009-02-19
				//Commented by Dineth on 2009-02-19
				/*rs1 = stmt1.executeQuery("SELECT NVL(A.RECEIPT_NO,'-') ,"+ //1
				" NVL(A.PAYER_BRANCH_CODE,'-'), "+ //2
				" NVL(A.CHEQUE_NO,'-'), "+ //3
				" NVL(A.REC_AMOUNT,0), "+ //4
				" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-'),  "+ //5
				" NVL(A.RECEIPT_COMMENTS,'-'), " + //6
				" TO_CHAR(B.ENT_DATE,'DD-MM-YYYY HH24:MI:SS'), " + //7
				" NVL(B.ENT_USER,'-'), " + //8
				" NVL(A.DEBTOR_CODE,'-') " + //9
				" ,NVL(A.FACILITY_NO,'-') " + //10
				" ,NVL(A.CLIENT_CODE,'-') " + //11
				" ,TO_CHAR(B.ENT_DATE,'DD-MM-YYYY') " + //12
				" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A , "+
				"      "+m_schema_name+".FA_OP_PRO_PRINT_UNBANK_CHEQUES B "+
				" WHERE A.RECEIPT_NO=B.RECEIPT_NO AND "+
				" A.FACILITY_NO=B.FACILITY_NO AND  "+
				" A.FACILITY_NO='"+m_facility_code+"'  "+
				//" A.CLIENT_CODE='"+m_client_no+"' "+
	  		" AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
  			" AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				
				" ");

				*/
				
				//Added by Dineth on 2009-02-19
				rs1=stmt1.executeQuery(" SELECT DISTINCT NVL(A.CLIENT_CODE,'-'), "+//1
                               " NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'), "+//2 
                               " NVL(A.FACILITY_NO,'-'), "+//3
                               " TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'), "+//4
															 " NVL(C.REF_NO,'') "+//5
                               " FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A , "+
                               " "+m_schema_name+".FA_OP_PRO_PRINT_UNBANK_CHEQUES B, "+ 
															 " "+m_schema_name+".FA_OP_PRO_RETURN_CHQ_LETTER C "+
                               " WHERE A.RECEIPT_NO=B.RECEIPT_NO AND "+ 
                               " A.FACILITY_NO=B.FACILITY_NO AND "+
															 " A.RECEIPT_NO=C.RECEIPT_NO AND "+
                               " A.FACILITY_NO='"+m_facility_code+"'  "+  
                               " AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
                               " AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
															 " UNION ALL "+
															 " SELECT DISTINCT NVL(A.CLIENT_CODE,'-'), "+//1
                               " NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'), "+//2 
                               " NVL(A.FACILITY_NO,'-'), "+//3
                               " TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'), "+//4
															 " '-' "+//5
															 " FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A , "+
															 "      "+m_schema_name+".FA_OP_PRO_PRINT_UNBANK_CHEQUES B "+
															 " WHERE A.RECEIPT_NO=B.RECEIPT_NO AND "+
															 " A.FACILITY_NO=B.FACILITY_NO AND  "+
															 " A.RECEIPT_NO NOT IN(SELECT RECEIPT_NO FROM "+m_schema_name+".FA_OP_PRO_RETURN_CHQ_LETTER C) AND "+
															 " A.FACILITY_NO='"+m_facility_code+"'  "+
				//" A.CLIENT_CODE='"+m_client_no+"' "+
	  													 " AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
  														 " AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "
																);
				//End by Dineth on 2009-02-19
				int j=1;
				double m_temp1=0;
				double m_temp2=0;
				double m_temp3=0;
				while(rs1.next()){
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=0;
					}
          /*
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_receipt_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(9)+"')\"><u>"+rs1.getString(5)+"</u></td>");
					out.println("<td width='7%' class=div_input align=right >"+rs1.getString(3)+"</td>");
					out.println("<td width='10%' class=div_input align=right >"+rs1.getString(2)+"</td>");
					out.println("<td width='10%' class=div_input align=right >"+nf.format(rs1.getDouble(4))+"</td>");
					out.println("<td width='20%' class=div_input align=right >"+rs1.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input align=right >"+rs1.getString(8)+"</td>");
					out.println("<td width='13%' class=div_input align=right >"+rs1.getString(7)+"</td>");
					out.println("<td width='10%'  align='left'><input type=button class='but_input' name=\"letter_generation_but\" value=\"Letter\" onclick=\"Generate_Letter('"+rs1.getString(10)+"','"+rs1.getString(11)+"','"+rs1.getString(12)+"')\" style=\"{width:80px}\"></td>");
					*/
					out.println("<td width='20%' class=div_input >"+rs1.getString(5)+"</td>");
					out.println("<td width='20%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td width='20%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(1)+"')\"><u>"+rs1.getString(2)+"</u></td>");
					out.println("<td width='20%' class=div_input style='cursor:hand' onClick=\"show_facility('"+rs1.getString(3)+"')\"><u>"+rs1.getString(3)+"</u></td>");
					out.println("<td width='10%' class=div_input style='cursor:hand' >"+rs1.getString(4)+"</td>");
					out.println("<td width='10%'  align='left'><input type=button class='but_input' name=\"letter_generation_but\" value=\"Letter\" onclick=\"Generate_Letter('"+rs1.getString(3)+"','"+rs1.getString(1)+"','"+rs1.getString(4)+"','"+rs1.getString(5)+"')\" style=\"{width:80px}\"></td>");
					out.println("</tr>");
				}
				

				out.println("</table>");

  	 		out.println("</table>");
	  		out.println("<br>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
	    }
			
			else if(m_chksql.equals("LOAD_ALL_UNBANK_REPORT")){				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				
				out.println("<HTML><HEAD><TITLE>Total Return Cheques Send to Client Report</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				
				out.println("<script>");
				
				out.println("function Generate_Letter(m_facility_code,m_client_code,m_date,m_ref_no) {");//Modified by Dineth on 2009-02-19
				// Commented by udara somathilake on 13-10-2010
				// out.println("m_url=\""+m_class_url+"/"+m_schema_name+"_FA_OP_Unbank_cheque_letter?chksql=main_page&finance_no=\"+m_facility_code+\"&date=\"+m_date+\"&client=\"+m_client_code+\"&ref_no=\"+m_ref_no+\"&print=FALSE\";"); //Modified by Dineth on 2009-02-19 
				out.println("m_url=\""+m_class_url+"/"+m_schema_name+"_FA_MISF_Return_cheque_sent_letter?chksql=main_page&finance_no=\"+m_facility_code+\"&date=\"+m_date+\"&client=\"+m_client_code+\"&ref_no=\"+m_ref_no+\"&print=FALSE\";");  // Added by udara somathilake on 13-10-2010
				out.println(" popupwin=window.open(m_url,'displayWindow2','left=110,top=110,width=750,height=600,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				out.println("}");
				
				out.println("</script>");	
				
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B>Return Cheques Send to Client Report for the Period "+m_from_date+" - "+m_to_date+"</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<br>");
				out.println("<p class=pdn_txtpos2><center><u><b>Total Return Cheques Send to Client Report</b></u></center></p>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				//Commented by Dineth on 2009-02-19
				/*out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
				out.println("<td width='15%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
				out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
				out.println("<td width='7%' align=right><DIV class=div_input><b>Chq No</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Bank - Branch</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Amount</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Remark</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Generated User</b></DIV></td>");
				out.println("<td width='13%' align=right><DIV class=div_input><b>Generated Time</b></DIV></td>");
				out.println("<td width='10%' align=center><DIV class=div_input><b>Letter</b></DIV></td>");
				*/
				//Added by Dineth on 2009-02-19
				out.println("<td width='20%' ><DIV class=div_input><b>Reference No</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input><b>Client No</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Letter Generated Date</b></DIV></td>");
				out.println("<td width='10%' align=center><DIV class=div_input><b>Letter</b></DIV></td>");

				//End by Dineth on 2009-02-19
				
				
				
				
				
				
				out.println("</tr>");
			//Commented by Dineth on 2009-02-19
/*					
				rs1 = stmt1.executeQuery("SELECT NVL(A.RECEIPT_NO,'-') ,"+ //1
				" NVL(A.PAYER_BRANCH_CODE,'-'), "+ //2
				" NVL(A.CHEQUE_NO,'-'), "+ //3
				" NVL(A.REC_AMOUNT,0), "+ //4
				" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-'),  "+ //5
				" NVL(A.RECEIPT_COMMENTS,'-'), " + //6
				" TO_CHAR(B.ENT_DATE,'DD-MM-YYYY HH24:MI:SS'), " + //7
				" NVL(B.ENT_USER,'-'), " + //8
				" NVL(A.DEBTOR_CODE,'-') ," + //9
				" NVL(A.FACILITY_NO,'-') " + //10
				" ,NVL(A.CLIENT_CODE,'-') " + //11
				" ,TO_CHAR(B.ENT_DATE,'DD-MM-YYYY') " + //12
				" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A , "+
				"      "+m_schema_name+".FA_OP_PRO_PRINT_UNBANK_CHEQUES B "+
				" WHERE A.RECEIPT_NO=B.RECEIPT_NO AND "+
				" A.FACILITY_NO=B.FACILITY_NO   "+
				//" A.FACILITY_NO='"+m_facility_no+"' AND "+
				//" A.CLIENT_CODE='"+m_client_no+"' "+
	  		" AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
  			" AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				
				" ");
	*/			
				//Added by Dineth on 2009-02-19
								rs1=stmt1.executeQuery(" SELECT DISTINCT NVL(A.CLIENT_CODE,'-'), "+//1
                               " NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'), "+//2 
                               " NVL(A.FACILITY_NO,'-'), "+//3
                               " TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'), "+//4 
															 " NVL(C.REF_NO,'') "+//5
                               " FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A , "+
                               " "+m_schema_name+".FA_OP_PRO_PRINT_UNBANK_CHEQUES B, "+
															 " "+m_schema_name+".FA_OP_PRO_RETURN_CHQ_LETTER C "+
                               " WHERE A.RECEIPT_NO=B.RECEIPT_NO AND "+
															 " A.RECEIPT_NO=C.RECEIPT_NO AND "+
                               " A.FACILITY_NO=B.FACILITY_NO "+  
                              // " A.FACILITY_NO='"+m_facility_code+"'  "+  
                               " AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
                               " AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
															 " UNION ALL "+
																" SELECT DISTINCT NVL(A.CLIENT_CODE,'-'), "+//1
                               " NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'), "+//2 
                               " NVL(A.FACILITY_NO,'-'), "+//3
                               " TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'), "+//4 
															 " '-' "+//5
															 " FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A , "+
															 "      "+m_schema_name+".FA_OP_PRO_PRINT_UNBANK_CHEQUES B "+
															 " WHERE A.RECEIPT_NO=B.RECEIPT_NO AND "+
															 " A.FACILITY_NO=B.FACILITY_NO   "+
															 " AND A.RECEIPT_NO NOT IN(SELECT RECEIPT_NO FROM "+m_schema_name+".FA_OP_PRO_RETURN_CHQ_LETTER C) "+
				//" A.FACILITY_NO='"+m_facility_no+"' AND "+
				//" A.CLIENT_CODE='"+m_client_no+"' "+
	  													" AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
  														" AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "
															);
				
				//End by Dineth on 2009-02-19
				
				int j=1;
				double m_temp1=0;
				double m_temp2=0;
				double m_temp3=0;
				while(rs1.next()){
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=0;
					}
					//m_temp1=m_temp1+rs1.getDouble(2);
					//m_temp2=m_temp2+rs1.getDouble(4);
					//m_temp3=m_temp3+rs1.getDouble(3);
					//Commented by Dineth on 2009-02-19
		/*			out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_facility('"+rs1.getString(10)+"')\"><u>"+rs1.getString(10)+"</u></td>");
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_receipt_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(9)+"')\"><u>"+rs1.getString(5)+"</u></td>");
					out.println("<td width='7%' class=div_input align=right >"+rs1.getString(3)+"</td>");
					out.println("<td width='10%' class=div_input align=right >"+rs1.getString(2)+"</td>");
					out.println("<td width='10%' class=div_input align=right >"+nf.format(rs1.getDouble(4))+"</td>");
					out.println("<td width='10%' class=div_input align=right >"+rs1.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input align=right >"+rs1.getString(8)+"</td>");
					out.println("<td width='13%' class=div_input align=right >"+rs1.getString(7)+"</td>");
					
					out.println("<td width='10%'  align='left'><input type=button class='but_input' name=\"letter_generation_but\" value=\"Letter\" onclick=\"Generate_Letter('"+rs1.getString(10)+"','"+rs1.getString(11)+"','"+rs1.getString(12)+"')\" style=\"{width:80px}\"></td>");
			*/		
			    //Added by Dineth on 2009-02-19
					out.println("<td width='20%' class=div_input>"+rs1.getString(5)+"</td>");
					out.println("<td width='20%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td width='20%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(1)+"')\"><u>"+rs1.getString(2)+"</u></td>");
					out.println("<td width='20%' class=div_input style='cursor:hand' onClick=\"show_facility('"+rs1.getString(3)+"')\"><u>"+rs1.getString(3)+"</u></td>");
					out.println("<td width='10%' class=div_input style='cursor:hand' >"+rs1.getString(4)+"</td>");
					out.println("<td width='10%'  align='left'><input type=button class='but_input' name=\"letter_generation_but\" value=\"Letter\" onclick=\"Generate_Letter('"+rs1.getString(3)+"','"+rs1.getString(1)+"','"+rs1.getString(4)+"','"+rs1.getString(5)+"')\" style=\"{width:80px}\"></td>");//Modified by Dineth on 2009-02-19
					
					
					//End by Dineth on 2009-02-19
					
					
					out.println("</tr>");
				}
				
				/*out.println("<tr>");
				out.println("<td width='10%' class=div_input ><B>Total</B></td>");
				out.println("<td width='10%' class=div_input align=right><B>"+nf.format(m_temp1)+"</B></td>");
				out.println("<td width='10%' class=div_input align=right><B>"+nf.format(m_temp2)+"</B></td>");
				out.println("<td width='10%' class=div_input align=right><B>"+nf.format(m_temp3)+"</B></td>");
				out.println("</tr>");*/
				out.println("</table>");

  	 		out.println("</table>");
	  		out.println("<br>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
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



