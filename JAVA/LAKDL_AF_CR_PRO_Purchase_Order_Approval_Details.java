//--
//SCREEN NAME: PURCHASE ORDER APPROVAL RECEIPT
//CREATED BY:NUWAN DE SILVA
//DATE/TIME: 08-11-2006
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_PRO_Purchase_Order_Approval_Details extends javax.servlet.http.HttpServlet { 
	
	Connection conn;
	Statement stmt,stmt2,stmt3;
  public ResultSet rs,rs2,rs3;
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
			stmt2=conn.createStatement();
			stmt3=conn.createStatement();
			String m_username=m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html");
			
			ServletOutputStream out = res.getOutputStream(); 
			
			
		
			//========================================================================================================================================================================================================================================================================================
			
			if(m_chksql.equals("view_details")){
			
				String m_pur_ord_no = req.getParameter("pur_ord_no");
				m_app_no = req.getParameter("app_no");
				String m_string="";
				
			rs2= stmt2.executeQuery (" SELECT "+
      " "+m_schema_name+".AF_CO_GET_VEN_NAME(VENDER_CODE) VENDER_NAME "+
      " FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
      " WHERE UPPER(PURCHASE_ORDER_NO)=UPPER('"+m_pur_ord_no+"') ");
			
			boolean more=rs2.next();
			String m_ven_name=rs2.getString(1).trim();
			
			
					 
			
			rs3= stmt3.executeQuery(" SELECT "+
      " NVL((TO_CHAR(MAX(ISSUED_DATE),'DD-MM-YYYY')),'-') ISSUED_DATE  "+
      " FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
      " WHERE UPPER(PURCHASE_ORDER_NO)=UPPER('"+m_pur_ord_no+"') ");
			
			more=rs3.next();
      String m_date=rs3.getString(1).trim();
			

				
				
				m_string=m_string+"<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"; 
				m_string=m_string+"<tr><td align='left'  ><b>DETAILS FOR THE PURCHASE ORDER NO: "+m_pur_ord_no+"</td></tr>";  //class='pdn_txtpos2'
				m_string=m_string+"</table>"; 
				m_string=m_string+"<br>"; 
				m_string=m_string+"<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"; 
				m_string=m_string+"<tr><td align='left'  ><b>Vendor Name          : "+m_ven_name+"</td></tr>";  //class='pdn_txtpos2'
				m_string=m_string+"</table>"; 
				m_string=m_string+"<br>"; 
				m_string=m_string+"<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"; 
				if(!m_date.equals("-")){
				m_string=m_string+"<tr><td align='left'  ><b>Last Transaction Date: "+m_date+"</td></tr>"; //class='pdn_txtpos2'
				}
				else if(m_date.equals("-")){
				m_string=m_string+"<tr><td align='left'  ><b>New Supplier</td></tr>"; //class='pdn_txtpos2'
				}
				m_string=m_string+"</table>"; 


				
				m_string=m_string+"<br>"; 

				m_string=m_string+"<table align='center' width='100%' class='table' border=\"0\">";
				m_string=m_string+"<tr >";
				m_string=m_string+"<TD WIDTH=\"*%\" align=\"left\"><B><u>Invoice Details</u></B></TD >";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table>";
				m_string=m_string+"<table align='center' width='100%' class='table' border=\"1\" cellspacing='0' bordercolor='black' >";
				m_string=m_string+"<tr  class='pdn_txtpos2' >";//
				m_string=m_string+"<th width='15%' align='left'><DIV class=div_input>Invoice No</DIV></th>";
				m_string=m_string+"<th width='15%' align='left'><DIV class=div_input>Vehicle No</DIV></th>";
				m_string=m_string+"<th width='15%' align='left'><DIV class=div_input>Engine No</DIV></th>";
				m_string=m_string+"<th width='15%' align='left'><DIV class=div_input>Chassis No</DIV></th>"; 
				m_string=m_string+"<th width='10%' align='left'><DIV class=div_input>Model Code</DIV></th>"; 
				m_string=m_string+"<th width='10%' align='left'><DIV class=div_input>Sub Model Code</DIV></th>"; 
				m_string=m_string+"<th width='10%' align='left'><DIV class=div_input>Colour</DIV></th>"; 
				m_string=m_string+"<th width='10%' align='left'><DIV class=div_input>Seating Capacity</DIV></th>"; 
				
				
				//m_string=m_string+"<th width='15%' align='center'><DIV class=div_input>Amount Current </DIV></th>"; 
			//	m_string=m_string+"<th width='15%' align='center'><DIV class=div_input>Balance to be received Current</DIV></th>"; 
				//m_string=m_string+"<th width='*%' align='center'><DIV class=div_input>Allocated Amount Current</DIV></th>"; 
			 // m_string=m_string+"<th width='8%' align='center'><DIV class=div_input></DIV></th>"; 
				m_string=m_string+"</tr>";
				
			/*	rs= stmt.executeQuery (" SELECT REC_NO,"+m_schema_name+".AF_CO_GET_DEPOSIT_NO(REC_NO),nvl(REC_AMOUNT,0),nvl(BAL_TOBE_RECEIVE,0),nvl(ALLOCATED_AMOUNT,0),nvl(REC_AMOUNT_CURR,0),"+
    												   " nvl(BAL_TOBE_RECEIVE_CURR,0),nvl(ALLOCATED_AMOUNT_CURR,0)"+
 															 "	FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL "+
 															 "	where rec_no =UPPER('"+m_pur_ord_no+"') ");							
																
			*/
		
						
																
    rs= stmt.executeQuery (" SELECT "+
    " INVOICE_NO, "+
		" NVL(VEHICLE_NO,'-'), "+
    " NVL(ENGINE_NO,'-'), "+
    " NVL(CHASSIS_NO,'-'), "+
		" NVL(MODEL_CODE,'-'), "+
    " NVL(SUB_MODEL_CODE,'-'), "+
		" NVL(COLOUR,'-'), "+
		" NVL(SEATING_CAPACITY,0)"+
		//" REG_NO "+
      
    " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
    " WHERE INVOICE_NO IN "+
    " ( "+
    " SELECT "+
    " PRO_INVOICE_NO "+
    " FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET "+
    " WHERE UPPER(PURCHASE_ORDER_NO)=UPPER('"+m_pur_ord_no+"') "+
    " ) ORDER BY INVOICE_NO DESC ");
																
				int i=0;
							
				while(rs.next()){
				
					m_string=m_string+"<tr>";
					//m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='15%' align='left' style= cursor:hand; onClick=\"show_proforma_invoice_drill('"+rs.getString(1)+"')\"><DIV class=div_input><u>"+rs.getString(1)+"</u></DIV></td>"; 
					m_string=m_string+"<td width='15%' align='left'><DIV class=div_input>"+rs.getString(2)+"</DIV></td>";
					m_string=m_string+"<td width='15%' align='left'><DIV class=div_input>"+rs.getString(3)+"</DIV></td>";
					m_string=m_string+"<td width='15%' align='left'><DIV class=div_input>"+rs.getString(4)+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='left' style= cursor:hand; onClick=\"show_model_details_drill('"+rs.getString(5)+"')\"><DIV class=div_input><u>"+rs.getString(5)+"</u></DIV></td>"; //modified by nuwan de silva 18-07-07
					m_string=m_string+"<td width='10%' align='left' style= cursor:hand; onClick=\"show_sub_model_details_drill('"+rs.getString(6)+"')\"><DIV class=div_input><u>"+rs.getString(6)+"</u></DIV></td>"; //modified by nuwan de silva 18-07-07
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+rs.getString(7)+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+rs.getString(8)+"</DIV></td>";
			//		m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+rs.getString(9)+"</DIV></td>";
					
					/*m_string=m_string+"<td width='15%' align='left' style= cursor:hand; title='Click here to view deposit details ' onclick=\"load_details_deposit('"+rs.getString(2)+"')\" ><DIV class=div_input>"+rs.getString(2)+"</DIV></td>";
					m_string=m_string+"<td width='8%' align='left'  ><DIV class=div_input>"+nf.format(rs.getDouble(3))+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+nf.format(rs.getDouble(4))+"</DIV></td>"; 
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+nf.format(rs.getDouble(5))+"</DIV></td>"; 
					m_string=m_string+"<td width='15%' align='left'><DIV class=div_input>"+nf.format(rs.getDouble(6))+"</DIV></td>"; 
					m_string=m_string+"<td width='15%' align='left'><DIV class=div_input>"+nf.format(rs.getDouble(7))+"</DIV></td>"; 
					m_string=m_string+"<td width='*%' align='left'><DIV class=div_input>"+nf.format(rs.getDouble(8))+"</DIV></td>"; 
					//m_string=m_string+"<td width='14%' align='center'><DIV class=div_input><INPUT class='but_input' TYPE=\"BUTTON\" VALUE=\"VIEW\" NAME=\"VIEW\" onclick=\"display_data('"+rs.getString(1)+"','"+rs.getString(5)+"')\"></DIV></td>"; 
					
					*/
					
					m_string=m_string+"</tr>";
					i++;
				}
				//m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+i+">";
				m_string=m_string+"</table>";
								
				rs= stmt.executeQuery(" SELECT  "+		
				" B.FOLLOW_UP_NO, "+
				" NVL(B.ENT_REMARKS,'-'), "+
				" NVL("+m_schema_name+".AF_CO_GET_DOC_DESC(DOCUMENT_TYPE),'-'), "+
				" NVL("+m_schema_name+".AF_CO_GET_SCREEN_DISPLAY_NAME(LAST_UPDATED_STAGE,'AF'),'-'), "+
				" NVL(REMARK,'-') ,"+
				" INITCAP(B.STATUS), "+
				" DOCUMENT_TYPE "+
				" FROM   "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT A, "+m_schema_name+".AF_CO_PRO_FOLLOW_UP B "+
				" WHERE  B.FOLLOW_UP_NO=A.REF_NO(+) "+
				//" AND  UPPER(B.ID_NO)=UPPER('"+m_app_no+"') "+ // commented by udara 08-03-2019
				" AND  B.ID_NO=UPPER('"+m_app_no+"') "+ // added by udara 08-03-2019
				" AND  B.STATUS!='INPROGRESS'  ");
				
				more=rs.next();
				if(more){
				
				m_string=m_string+"<br><br>";
				
				m_string=m_string+"<table align='center' width='100%' class='table' border=\"0\">";
				m_string=m_string+"<tr >";
				m_string=m_string+"<TD WIDTH=\"*%\" align=\"left\"><B><u>Documents Status</u></B></TD >";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table>";
				
				m_string=m_string+"<table align='center' width='100%' class='table' border=\"1\" cellspacing='0' bordercolor='black'>";
				m_string=m_string+"<tr class='pdn_txtpos2' >";
				m_string=m_string+"<TD WIDTH=\"12%\" align=\"left\"><B>Follow up No</B></TD >";
				m_string=m_string+"<TD WIDTH=\"5%\" align=\"left\">Type</TD >";
			  m_string=m_string+"<TD WIDTH=\"20%\" align=\"left\">Document</TD >";
			  m_string=m_string+"<TD WIDTH=\"10%\" align=\"left\">Stage Entered</TD >";
			  m_string=m_string+"<TD WIDTH=\"30%\" align=\"left\"><B>Condition</B></TD >";
			  m_string=m_string+"<TD WIDTH=\"10%\" align=\"left\"><B>Status</B></TD >";
				m_string=m_string+"</tr>";

				}

				while(more){
				m_string=m_string+"<tr >";
				m_string=m_string+"<TD WIDTH=\"12%\" align=\"left\">"+rs.getString(1)+"</TD >";
				if(rs.getString(4).equals("-")){
				m_string=m_string+"<TD WIDTH=\"5%\" align=\"left\">-</TD >";
				}
				else{
				m_string=m_string+"<TD WIDTH=\"5%\" align=\"left\">Document</TD >";
				}
				
			  m_string=m_string+"<TD WIDTH=\"20%\" align=\"left\" style= cursor:hand; onClick=\"show_document_drill('"+rs.getString(7)+"')\" ><u>"+rs.getString(3)+"</u></TD >";
			  m_string=m_string+"<TD WIDTH=\"10%\" align=\"left\">"+rs.getString(4)+"</TD >";
			  m_string=m_string+"<TD WIDTH=\"30%\" align=\"left\">"+rs.getString(2)+"</TD >";
			  m_string=m_string+"<TD WIDTH=\"10%\" align=\"left\">"+rs.getString(6)+"</TD >";
				m_string=m_string+"</tr>";
				
				more=rs.next();
				}
				m_string=m_string+"</table>";
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Purchase Order Approval Details</TITLE>"); 
				out.println("</HEAD>"); 
				
				
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 

				out.println(m_string);
				out.println("</html>");
			}			
			
			
 
	//=============================================================================================================================================================================================================================================================================================================
	
			
	 
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


