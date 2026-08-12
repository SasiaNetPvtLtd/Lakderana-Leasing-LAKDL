import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : MAHELA FOR OFSCL LEASING    DATE:31-01-2007
  
public class LAKDL_AF_MAS_rpt_user_approval extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1,nf2;
    
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
      
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			nf2 = java.text.NumberFormat.getInstance(Locale.US);
			nf2.setMinimumFractionDigits(4);
			nf2.setMaximumFractionDigits(4);
			
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
			else if(m_chksql.equals("LOAD_USER_APPROVAL_1")){
				
				String m_string="";				
				
				
				 rs1= stmt1.executeQuery("SELECT  A.USER_ID,"+//1
  				 " A.NAME, "+//2
  				 " B.LOCATION_DESC,"+//3
  				 " DECODE(A.USER_TYPE,'IND','Individual','COR','Corporate','SOL','Sole Proprietorship','PAR','Partnership','LIL','Limited Liability','NGN','Non Governmental'),"+//4
				   " A.EMP_ID,"+//5
  				 " A.DIVISION_CODE,"+//6
  				 " C.DESIGNATION_NAME, "+//7
					//---modified by : delanjali----------------------------------------------------------------------------------
					//---date				 : 2007-07-24----------------------------------------------------------------------------------
						
					 " A.location_code, "+//8
					 " A.DESIGNATION_CODE, "+	//9
					 " InitCap("+m_schema_name+".AF_CO_GET_DIVISION_DESC(A.DIVISION_CODE)), "+//10
           " InitCap("+m_schema_name+".AF_CO_GET_EMP_NAME(A.EMP_ID)) "+//11
					//-------------------------------------------------------------------------------------
					" FROM "+m_schema_name+".CO_CO_MAS_USER A,"+m_schema_name+".AF_CO_MAS_LOCATION B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+

	 				 " WHERE A.LOCATION_CODE=B.LOCATION_CODE AND A.DESIGNATION_CODE=C.DESIGNATION_CODE AND A.ACTIVE_STATUS='M' "+
					 " AND A.USER_ID NOT IN(SELECT USER_ID FROM "+m_schema_name+".CO_CO_MAS_USER_APPROVAL WHERE USER_ID=A.USER_ID AND APPR_STATUS='APPR1') ");


				m_string=m_string+"<table align='center' width='100%' class='table' border=0>";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='8%' ><DIV class=div_input><b>User ID</b></DIV></td>";
				m_string=m_string+"<td width='8%' ><DIV class=div_input><B>User Name</b></DIV></td>";
				m_string=m_string+"<td width='8%' ><DIV class=div_input><b>Location</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>User Type</b></DIV></td>"; 
				//modified by madhawa 2009-10-15 change emp id to Emp Name
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Emp Name</b></DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Division</b></DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Designation</b></DIV></td>";
				m_string=m_string+"<td width='6%' class=div_input>Approve</td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='8%' class=div_input onClick=\"get_user_right('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_USER_ID_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>"; //edit_user('"+rs1.getString(1)+"')
					m_string=m_string+"<td width='8%' class=div_input onClick=\"\" >"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='8%' class=div_input style= \"cursor:hand;cursor-color:blue\" onclick=\"show_location_drill('"+rs1.getString(8)+"')\"><U>"+rs1.getString(3)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input onClick=\"\" >"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='20%' class=div_input style= \"cursor:hand;cursor-color:blue\" onclick=\"show_employee_drill('"+rs1.getString(5)+"')\"><U>"+rs1.getString(11)+"</td><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_EMP_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\">";
					m_string=m_string+"<td width='20%' class=div_input>"+rs1.getString(10)+"</td>";
				  m_string=m_string+"<td width='20%' class=div_input style= \"cursor:hand;cursor-color:blue\" onclick=\"show_designation_drill('"+rs1.getString(9)+"')\"><U>"+rs1.getString(7)+"</td>"; 
					m_string=m_string+"<td width='6%' class=div_input align=center><INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE_"+chk_nums+"' VALUE=\"N\" onClick=\"check_change('"+chk_nums+"')\" ></td>";
					m_string=m_string+"</tr>";
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_USER_APPROVAL_2")){
				
				String m_string="";				
				
				
				 rs1= stmt1.executeQuery("SELECT  A.USER_ID,"+//1
  				 " A.NAME, "+//2
  				 " B.LOCATION_DESC,"+//3
  				 " DECODE(A.USER_TYPE,'IND','Individual','COR','Corporate','SOL','Sole Proprietorship','PAR','Partnership','LIL','Limited Liability','NGN','Non Governmental'),"+//4
				   " nvl(A.EMP_ID,'-'),"+//5
  				 " A.DIVISION_CODE,"+//6
  				 " C.DESIGNATION_NAME, "+//7
					//---modified by : delanjali----------------------------------------------------------------------------------
					//---date				 : 2007-07-24----------------------------------------------------------------------------------
					 " A.location_code, "+//8
					 " A.DESIGNATION_CODE, "+	//9
					 " InitCap("+m_schema_name+".AF_CO_GET_DIVISION_DESC(A.DIVISION_CODE)), "+//10
           " InitCap(nvl(("+m_schema_name+".AF_CO_GET_EMP_NAME(A.EMP_ID)),'-')) "+//11
					//-------------------------------------------------------------------------------------
 				
					" FROM "+m_schema_name+".CO_CO_MAS_USER A,"+m_schema_name+".AF_CO_MAS_LOCATION B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
 				" WHERE A.LOCATION_CODE=B.LOCATION_CODE AND A.DESIGNATION_CODE=C.DESIGNATION_CODE AND A.ACTIVE_STATUS='M' "+
				"	AND A.USER_ID IN(SELECT USER_ID FROM "+m_schema_name+".CO_CO_MAS_USER_APPROVAL WHERE USER_ID=A.USER_ID AND APPR_STATUS='APPR1') ");
				//"	AND A.USER_ID NOT IN(SELECT USER_ID FROM "+m_schema_name+".CO_CO_MAS_USER_APPROVAL WHERE USER_ID=A.USER_ID AND APPR_STATUS='APPR2') ");


				/*m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='12%' ><DIV class=div_input><b>User ID</b></DIV></td>";
				m_string=m_string+"<td width='18%' ><DIV class=div_input><B>User Name</b></DIV></td>";
				m_string=m_string+"<td width='12%' ><DIV class=div_input><b>Location</b></DIV></td>";
				m_string=m_string+"<td width='12%' ><DIV class=div_input><b>User Type</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Emp ID</b></DIV></td>"; 
				m_string=m_string+"<td width='12%' ><DIV class=div_input><b>Division Code</b></DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Designation</b></DIV></td>";
				m_string=m_string+"<td width='*%'></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='12%' class=div_input onClick=\"edit_user('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_USER_ID_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='18%' class=div_input onClick=\"\" >"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='12%' class=div_input onClick=\"\" >"+rs1.getString(3)+"</td>";
					m_string=m_string+"<td width='12%' class=div_input onClick=\"\" >"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_EMP_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\">"+rs1.getString(5)+"</td>";
					m_string=m_string+"<td width='12%' class=div_input>"+rs1.getString(6)+"</td>";
				  m_string=m_string+"<td width='20%' class=div_input>"+rs1.getString(7)+"</td>"; 
					m_string=m_string+"<td width='*%' class=div_input><INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE_"+chk_nums+"' VALUE=\"N\" onClick=\"check_change('"+chk_nums+"')\" ></td>";
					m_string=m_string+"</tr>";*/
				
				m_string=m_string+"<table align='center' width='100%' class='table' border=0>";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='8%' ><DIV class=div_input><b>User ID</b></DIV></td>";
				m_string=m_string+"<td width='8%' ><DIV class=div_input><B>User Name</b></DIV></td>";
				m_string=m_string+"<td width='8%' ><DIV class=div_input><b>Location</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>User Type</b></DIV></td>"; 
				//modified by madhawa 2009-10-15 change emp id to Emp Name
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Emp Name</b></DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Division</b></DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Designation</b></DIV></td>";
				m_string=m_string+"<td width='6%' class=div_inputj>Approve</td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='8%' class=div_input onClick=\"edit_user('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_USER_ID_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='8%' class=div_input onClick=\"\" >"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='8%' class=div_input style= \"cursor:hand;cursor-color:blue\" onclick=\"show_location_drill('"+rs1.getString(8)+"')\"><U>"+rs1.getString(3)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input onClick=\"\" >"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='20%' class=div_input style= \"cursor:hand;cursor-color:blue\" onclick=\"show_employee_drill('"+rs1.getString(5)+"')\"><U>"+rs1.getString(11)+"</td><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_EMP_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\">";
					m_string=m_string+"<td width='20%' class=div_input>"+rs1.getString(10)+"</td>";
				  m_string=m_string+"<td width='20%' class=div_input style= \"cursor:hand;cursor-color:blue\" onclick=\"show_designation_drill('"+rs1.getString(9)+"')\"><U>"+rs1.getString(7)+"</td>"; 
					m_string=m_string+"<td width='6%' class=div_input align=center><INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE_"+chk_nums+"' VALUE=\"N\" onClick=\"check_change('"+chk_nums+"')\" ></td>";
					m_string=m_string+"</tr>";
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			// Used in Change Capital Allowance 
			// OFSCL - Leasing - Accounting & Administration
			else if (m_chksql.trim().equals("LOAD_INVOICE_DETAILS")) {
			
			String m_app_no=req.getParameter("app_no");
			String m_string="";
			
			rs1= stmt1.executeQuery(" SELECT  INVOICE_NO, "+//1
    	" NVL(VEHICLE_NO,'-'), "+//2
    	" NVL(ENGINE_NO,'-'), "+//3
  	  " NVL(CHASSIS_NO,'-'), "+//4
			" NVL(TOTAL_AMOUNT,0), "+//5
    	" NVL(NET_PRICE,0), "+//6
    	" NVL(CAPITAL_ALLOWANCE,0), "+//7
			"	NVL(APPLICATION_NO,'-')"+//8
 			" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
 			" WHERE APPLICATION_NO='"+m_app_no+"' ");


				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='12%' ><DIV class=div_input><b>Invoice No</b></DIV></td>";
				m_string=m_string+"<td width='12%' ><DIV class=div_input><b>Vehicle No</b></DIV></td>";
				m_string=m_string+"<td width='12%' ><DIV class=div_input><b>Engine No</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Chassis No</b></DIV></td>";
				m_string=m_string+"<td width='18%' align='right' ><DIV class=div_input><B>Gross Price</b></DIV></td>";
				m_string=m_string+"<td width='12%' align='right' ><DIV class=div_input><b>Net Price</b></DIV></td>"; 
				m_string=m_string+"<td width='20%' align='right' ><DIV class=div_input><b>Capital Allowance</b></DIV></td>";
				m_string=m_string+"<td width='*%'></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='12%' class=div_input ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_INVOICE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\" disabled ><input class='but_input' type='button' name='BUT_TXT_INVOICE_NO_HELP_"+chk_nums+"' value='Help' onClick=\"help_invoice('"+chk_nums+"','"+rs1.getString(8)+"')\"></td>";
					m_string=m_string+"<td width='12%' class=div_input onClick=\"\" ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_VEHICLE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\" disabled ></td>";
					m_string=m_string+"<td width='12%' class=div_input onClick=\"\" ><INPUT TYPE='TEXT' style='width:100px;' class='txt_input' NAME='TXT_ENGINE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\" disabled ></td>";
					m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_CHASSIS_NO_"+chk_nums+"' VALUE=\""+rs1.getString(4)+"\" disabled ></td>";
					m_string=m_string+"<td width='18%' align='right' class=div_input onClick=\"\" ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_TOT_AMOUNT_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(5))+"\" STYLE=\"{text-align:right;}\" disabled ></td>";
					m_string=m_string+"<td width='12%' align='right' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_NET_PRICE_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(6))+"\" STYLE=\"{text-align:right;}\" disabled ></td>";
				  m_string=m_string+"<td width='20%' align='right' class=div_input><INPUT TYPE='TEXT' style='width:125px;' class='txt_input' NAME='TXT_CAPITAL_ALLOW_"+chk_nums+"' VALUE=\""+nf2.format(rs1.getDouble(7))+"\" onblur=\"format_number2(this,2)\" STYLE=\"{text-align:right;}\" ></td>"; 
					m_string=m_string+"<td width='*%' class=div_input></td>";
					m_string=m_string+"</tr>";
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);

			}
			// Used in Delete Guarantors
			// OFSCL - Leasing - Credit
			else if(m_chksql.equals("LOAD_GUARANTOR_DETAILS")){
				
				String m_string="";				
				String m_app_no=req.getParameter("app_no");	
				
				 rs1= stmt1.executeQuery(" SELECT   A.GUARANTOR_CODE,"+//1
							"  NVL(B.FULL_NAME,'-'),"+//2
  						"  NVL(A.RELATIONSHIP,'-'), "+//3
  						"  NVL(A.PERIOD,0), "+//4
  						"  NVL(A.TEL_NO,'-'), "+//5
  						"  NVL(B.NIC_NO,'-'), "+//6
							"  CLIENT_TYPE, "+//7
							"  NVL(BUSINESS_CERTIFICATE_NO,'-') "+//8
 				" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A,LAKDL.AF_CO_MAS_CLIENT B "+
 				" WHERE A.APPLICATION_NO='"+m_app_no+"' AND A.ACTIVE_STATUS <> 'N'  AND A.GUARANTOR_CODE=B.CLIENT_CODE  ");
					
					
					boolean more = rs1.next();
					
				m_string=m_string+"<table align='center' width='100%' class='table' >";	
				if(more){
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='12%' ><DIV class=div_input><b>Guarantor Code</b></DIV></td>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><B>Guarantor Name</b></DIV></td>";
				m_string=m_string+"<td width='12%' ><DIV class=div_input><b>Relationship</b></DIV></td>";
				m_string=m_string+"<td width='12%' ><DIV class=div_input><b>Period</b></DIV></td>"; 
				m_string=m_string+"<td width='12%' ><DIV class=div_input><b>Tel No</b></DIV></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>NIC No/Business Reg No</b></DIV></td>"; 
				m_string=m_string+"<td width='*%'>Status</td>";
				m_string=m_string+"</tr>";
				}
				
				int chk_nums=0;
				int j=0;
				while(more){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}   
					m_string=m_string+"<td width='12%' class=div_input onClick=\"show_client('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_GUARANTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='20%' class=div_input onClick=\"\" >"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='12%' class=div_input onClick=\"\" >"+rs1.getString(3)+"</td>";
					m_string=m_string+"<td width='12%' class=div_input onClick=\"\" >"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='12%' class=div_input >"+rs1.getString(5)+"</td>";
					if(rs1.getString(7).equals("I")){
					m_string=m_string+"<td width='15%' class=div_input >"+rs1.getString(6)+"</td>";
					}
					else {
					m_string=m_string+"<td width='15%' class=div_input >"+rs1.getString(8)+"</td>";
					}
					m_string=m_string+"<td width='*%' class=div_input ><INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE_"+chk_nums+"' VALUE=\"N\" onClick=\"check_change('"+chk_nums+"')\" ></td>";
					m_string=m_string+"</tr>";
					more = rs1.next();
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

