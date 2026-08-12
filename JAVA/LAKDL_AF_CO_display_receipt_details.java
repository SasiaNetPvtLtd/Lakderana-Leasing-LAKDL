
//--
//SCREEN NAME: RETURN & REALIZATION - DISPLAY RECEIPT DETAILS
//CREATED BY: M.M. WICRAMASEKARA
//DATE/TIME: 10-10-2006
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CO_display_receipt_details extends javax.servlet.http.HttpServlet { 
	
	Connection conn;
	Statement stmt,stmt1;
  public ResultSet rs,rs1;
	public String m_chksql;
	java.text.NumberFormat nf;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			String m_schema_name=m_sn_methods.schema_name.trim();
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			m_chksql=req.getParameter("chksql");
			String m_app_no = "";
			String m_inq_no = "";
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			
			String m_username=m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html");
			
			ServletOutputStream out = res.getOutputStream(); 
			
			
		
			//========================================================================================================================================================================================================================================================================================
			
			if(m_chksql.equals("pop_receipt_details")){
			
				String m_rec_no = req.getParameter("REC_NO");
				
				String m_string="";
				
				m_string=m_string+"<table class='table' border='1' cellpadding='0' cellspacing='0' width='100%'>"; 
				m_string=m_string+"<tr><td align='left' class='pdn_txtpos2' >RECEIPT DETAILS FOR THE RECEIPT NO: "+m_rec_no+"</td></tr>"; 
				m_string=m_string+"</table>"; 
				m_string=m_string+"<br>"; 
				
				m_string=m_string+"<table align='center' width='100%' class='table' border=\"1\">";
				m_string=m_string+"<tr class='pdn_txtpos2' >";
				//m_string=m_string+"<th width='1%'></th>"; 
				m_string=m_string+"<th width='15%' align='center'><DIV class=div_input>Receipt No</DIV></th>";
				m_string=m_string+"<th width='15%' align='center'><DIV class=div_input>Deposit No</DIV></th>";
				m_string=m_string+"<th width='8%' align='center'><DIV class=div_input>Amount </DIV></th>";
				m_string=m_string+"<th width='10%' align='center'><DIV class=div_input>Balance to be received</DIV></th>"; 
				m_string=m_string+"<th width='10%' align='center'><DIV class=div_input>Allocated Amount</DIV></th>"; 
				m_string=m_string+"<th width='15%' align='center'><DIV class=div_input>Amount Current </DIV></th>"; 
				m_string=m_string+"<th width='15%' align='center'><DIV class=div_input>Balance to be received Current</DIV></th>"; 
				m_string=m_string+"<th width='*%' align='center'><DIV class=div_input>Allocated Amount Current</DIV></th>"; 
			 // m_string=m_string+"<th width='8%' align='center'><DIV class=div_input></DIV></th>"; 
				m_string=m_string+"</tr>";
				
				rs= stmt.executeQuery (" SELECT REC_NO,"+m_schema_name+".AF_CO_GET_DEPOSIT_NO(REC_NO),nvl(REC_AMOUNT,0),nvl(BAL_TOBE_RECEIVE,0),nvl(ALLOCATED_AMOUNT,0),nvl(REC_AMOUNT_CURR,0),"+
    												   " nvl(BAL_TOBE_RECEIVE_CURR,0),nvl(ALLOCATED_AMOUNT_CURR,0)"+
 															 "	FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL "+
 															 "	where rec_no =UPPER('"+m_rec_no+"') ");							
																
				int i=0;
							
				while(rs.next()){
				
					m_string=m_string+"<tr>";
					//m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='15%' align='center' style= cursor:hand; onClick=\"show_settle_receipt_drill('"+rs.getString(1)+"')\" ><DIV class=div_input><u>"+rs.getString(1)+"</u></DIV></td>";
					m_string=m_string+"<td width='15%' align='center' style= cursor:hand; title='Click here to view deposit details ' onclick=\"load_details_deposit('"+rs.getString(2)+"')\" ><DIV class=div_input>"+rs.getString(2)+"</DIV></td>";
					m_string=m_string+"<td width='8%' align='right'  ><DIV class=div_input>"+nf.format(rs.getDouble(3))+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='right'><DIV class=div_input>"+nf.format(rs.getDouble(4))+"</DIV></td>"; 
					m_string=m_string+"<td width='10%' align='right'><DIV class=div_input>"+nf.format(rs.getDouble(5))+"</DIV></td>"; 
					m_string=m_string+"<td width='15%' align='right'><DIV class=div_input>"+nf.format(rs.getDouble(6))+"</DIV></td>"; 
					m_string=m_string+"<td width='15%' align='right'><DIV class=div_input>"+nf.format(rs.getDouble(7))+"</DIV></td>"; 
					m_string=m_string+"<td width='*%' align='right'><DIV class=div_input>"+nf.format(rs.getDouble(8))+"</DIV></td>"; 
					//m_string=m_string+"<td width='14%' align='center'><DIV class=div_input><INPUT class='but_input' TYPE=\"BUTTON\" VALUE=\"VIEW\" NAME=\"VIEW\" onclick=\"display_data('"+rs.getString(1)+"','"+rs.getString(5)+"')\"></DIV></td>"; 
					m_string=m_string+"</tr>";
					i++;
				}
				//m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+i+">";
				m_string=m_string+"</table>";
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Return & Realization - Receipt Details</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("function load_details_deposit(deposit_no){");
				//out.println("alert('dePOSIT nO ** '+deposit_no)");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CO_display_receipt_details?chksql=pop_deposit_details&DEP_NO='+deposit_no;"); 
				out.println("window.open(m_url,'displayWindow4','left=150,top=250,width=800,height=200,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
		 		out.println("}");
				out.println("</SCRIPT>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println(m_string);
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 

				out.println("</html>");
			 			
     }
	//=============================================================================================================================================================================================================================================================================================================
	
	else if(m_chksql.equals("pop_deposit_details")){
			
				String m_dep_no = req.getParameter("DEP_NO");
				
				String m_string="";
				
				m_string=m_string+"<table class='table' border='1' cellpadding='0' cellspacing='0' width='100%'>"; 
				m_string=m_string+"<tr><td align='left' class='pdn_txtpos2' >DEPOSIT DETAILS FOR THE DEPOSIT NO: "+m_dep_no+"</td></tr>"; 
				m_string=m_string+"</table>"; 
				m_string=m_string+"<br>"; 
				
				m_string=m_string+"<table align='center' width='100%' class='table' border=\"1\">";
				m_string=m_string+"<tr class='pdn_txtpos2' >";
				//m_string=m_string+"<th width='1%'></th>"; 
				m_string=m_string+"<th width='20%' align='center'><DIV class=div_input>Deposit No</DIV></th>";
				m_string=m_string+"<th width='15%' align='center'><DIV class=div_input>Deposit Date </DIV></th>";
				m_string=m_string+"<th width='20%' align='center'><DIV class=div_input>Amount</DIV></th>"; 
				m_string=m_string+"<th width='15%' align='center'><DIV class=div_input>Reference</DIV></th>"; 
				m_string=m_string+"<th width='15%' align='center'><DIV class=div_input>Account No </DIV></th>"; 
				m_string=m_string+"<th width='10%' align='center'><DIV class=div_input>Branch Code</DIV></th>"; 
			  // m_string=m_string+"<th width='8%' align='center'><DIV class=div_input></DIV></th>"; 
				m_string=m_string+"</tr>";
												
				rs= stmt.executeQuery (" SELECT a.DIPOSIT_NO,TO_CHAR(a.DIPOSIT_DATE,'DD-MM-YYYY'),NVL(b.AMOUNT,0),NVL(a.REFERENCE,'-'),NVL(a.ACC_NO,'-'),NVL(a.BRANCH_CODE,'-') "+
 															 " FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT A,"+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS B "+
 															 " WHERE A.DIPOSIT_NO=UPPER('"+m_dep_no+"') AND a.DIPOSIT_NO = b.DIPOSIT_NO ");															
				int i=0;
							
				while(rs.next()){
				
					m_string=m_string+"<tr>";
					//m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='10%' align='center'><DIV class=div_input>"+rs.getString(1)+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='center'><DIV class=div_input>"+rs.getString(2)+"</DIV></td>";
					m_string=m_string+"<td width='15%' align='right'><DIV class=div_input>"+nf.format(rs.getDouble(3))+"</DIV></td>";
					m_string=m_string+"<td width='15%' align='center'><DIV class=div_input>"+rs.getString(4)+"</DIV></td>"; 
					m_string=m_string+"<td width='15%' align='center'><DIV class=div_input>"+rs.getString(5)+"</DIV></td>"; 
					m_string=m_string+"<td width='*%' align='center' style= cursor:hand; onClick=\"show_branch_drill('"+rs.getString(6)+"')\" ><DIV class=div_input><u>"+rs.getString(6)+"</u></DIV></td>";  //modofoed nuwan de silva 17-07-07
					//m_string=m_string+"<td width='14%' align='center'><DIV class=div_input><INPUT class='but_input' TYPE=\"BUTTON\" VALUE=\"VIEW\" NAME=\"VIEW\" onclick=\"display_data('"+rs.getString(1)+"','"+rs.getString(5)+"')\"></DIV></td>"; 
					m_string=m_string+"</tr>";
					i++;
				}
				//m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+i+">";
				m_string=m_string+"</table>";
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Return & Realization - Deposit Details</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println(m_string);
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 

				out.println("</html>");
			}			
	 
//=============================================================================================================================================================================================================================================================================================================		
 
			out.flush();
			out.close();
			conn.close();
			this.destroy();
			}
			catch (Exception e) { 
			try { 
		
			}	 
			catch (Exception eti) {}
		
			ByteArrayOutputStream ostr = new ByteArrayOutputStream(); 
			e.printStackTrace(new PrintStream(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString()); 
			out.close();
			
			}
	}
}


