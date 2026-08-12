import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
  
// DEVELOP BY : INDITHA FOR OFSCL   DATE:21-09-2006

public class LAKDL_FA_Income_Provision_sql_validations_normal extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1,stmt3;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
  public ResultSet rs,rs1,rs3;
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_username = m_sn_methods.username;
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 


			//**************************************************************	
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
            
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
     	res.setDateHeader("Expires", 0);

			ServletOutputStream out = res.getOutputStream();

			m_chksql=req.getParameter("chksql");
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt3=conn.createStatement();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			
			 	else if(m_chksql.equals("INCOME_SUSPENCE_REPORT")){
				
				String m_start_date=req.getParameter("start_date");
				String m_end_date=req.getParameter("end_date");

				String m_string="";		
				String m_date1="" ,m_date2="",m_date3="";
				String m_month1="" ,m_month2="",m_month3="";
				
				rs1= stmt1.executeQuery( " SELECT  "+
				"         TO_CHAR(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),'DD-MM-YYYY'),                  "+
				"         TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1),'DD-MM-YYYY') ,  "+
				"         TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-2),'DD-MM-YYYY') ,  "+
				"         TO_CHAR(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),'MON') ,                        "+
				"         TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1),'MON') ,         "+
				"         TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-2),'MON')           "+
				"  FROM    DUAL ");
																	
				if(rs1.next()){
				m_date1=rs1.getString(1);
				m_date2=rs1.getString(2);
				m_date3=rs1.getString(3);
				m_month1=rs1.getString(4);
				m_month2=rs1.getString(5);
				m_month3=rs1.getString(6);
				}	
					
				rs= stmt.executeQuery (" SELECT TO_CHAR(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1)),'DD-MON-YYYY'),"+
				" TO_CHAR(LAST_DAY(TO_DATE('"+m_start_date+"','DD-MM-YYYY')),'DD-MON-YYYY') FROM DUAL ");
				
				String m_last_date="";
				String m_current_date="";
				
				if(rs.next()){
				m_last_date=rs.getString(1);
				m_current_date=rs.getString(2);
				}
				
				
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".FA_PRO_PROVISION_RPT(:1,:2,:3); END;");
				callstmt.setString(1,m_start_date);
				callstmt.setString(2,m_end_date);
				callstmt.setString(3,m_username);
				callstmt.execute();
				callstmt.close();
				
									
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Provision for Bad and Doubtful Debts and Income Suspension</TITLE>"); 
				
				out.println("<SCRIPT> "); 
				
				out.println("var chque_status=0;");

				out.println("function validate_suspense_status(obj) {"); 
				out.println("if(obj.checked==true){ "); 
				out.println("obj.value='on' "); 
				out.println("obj.checked=true "); 
				out.println("} "); 
				out.println("else { "); 
				out.println("obj.value='off' "); 
				out.println("obj.checked=false "); 
				out.println("} "); 
				out.println("} "); 
				
			out.println("function validate_data(){"); 
			out.println("		return true;"); 
			out.println("}"); 			  

				
			out.println("function before_submit(){ "); 
			out.println("	if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to run the suspense process\")){ ");
			out.println("			for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("				document.Form1.elements[i].disabled=false;");
			out.println("			}");
			out.println("			if(validate_data()){"); 
			out.println("				document.Form1.action='"+m_class_url+"/"+m_fschema_name+"FA_Save_Income_Susppense_Provision?start_date="+m_start_date+"&end_date="+m_end_date+"';");  
			out.println("				document.Form1.submit();	"); 
			out.println("			}"); 
			out.println("		}"); 
			out.println("	}"); 
			out.println("} "); 
			
			
				out.println("function enable_valuation(obj,val){");
				out.println("if(obj.value=='VAL_YES'){");
				out.println("document.Form1.elements[\"TXT_VALUATION_\"+val].disabled=false;");
				out.println("}");
				out.println("else if(obj.value=='VAL_NO'){");
				out.println("document.Form1.elements[\"TXT_VALUATION_\"+val].disabled=true;");
				out.println("document.Form1.elements[\"TXT_VALUATION_\"+val].value=0;");
				out.println("}");
				out.println("}");
                
                //valuation check goes here				
				out.println("function check_number_val(obj,size,val){");
				out.println("if(obj.value!='')"); 
				out.println("if(isnumberok(obj,size)){"); 
				out.println("calculate_netexposure(obj,val)");
				out.println("}"); 
				out.println("else{");
				out.println("alert('please enter a number');"); 
				out.println("obj.value='';"); 
				out.println("obj.focus();"); 
				out.println("}"); 
				out.println("}"); 
			
			    //net exposure calculation goes here
				out.println("function calculate_netexposure(obj,val){");
				out.println("var m_txt_capital_amt=0;");
				out.println("var m_txt_valuation_amt=0;");
				out.println("var m_net_exposure=0;");

				out.println("m_txt_capital_amt         = document.Form1.elements[\"txt_capital_amt_\"+val].value;");
				out.println("m_txt_valuation_amt       = document.Form1.elements[\"txt_valuation_amt_\"+val].value;");
				out.println("m_net_exposure            = format_noobject(parseFloat(unformat_noobject(m_txt_capital_amt)) - parseFloat(unformat_noobject(m_txt_valuation_amt))); ");
				out.println("document.Form1.elements[\"txt_netexposure_amt_\"+val].value=format_noobject(m_net_exposure); ");
				out.println("calculate_provision_made(obj,val);");
				out.println("}"); 
                
				//amount to be provision will be calculated below section
				out.println("function calculate_provision_made(obj,val){");
				out.println("var m_txt_hidden_provision_rate=0;");
				out.println("var m_txt_netexposure_amt=0;");
				out.println("var m_provision_amount=0;");
				out.println("var m_txt_cumilative_provision=0;");
				out.println("var m_diff=0;");
				out.println("m_txt_hidden_provision_rate    = document.Form1.elements[\"txt_hidden_provision_rate_\"+val].value;");
				out.println("m_txt_netexposure_amt          = document.Form1.elements[\"txt_netexposure_amt_\"+val].value;");
				out.println("m_txt_cumilative_provision     = document.Form1.elements[\"txt_cumilative_provision_\"+val].value;");
				out.println("if (parseFloat(unformat_noobject(m_txt_netexposure_amt)) > 0 ) { ");
				out.println("m_provision_amount           =format_noobject((parseFloat(unformat_noobject(m_txt_netexposure_amt))*parseFloat(unformat_noobject(m_txt_hidden_provision_rate)))/100 ); ");
				out.println("document.Form1.elements[\"txt_provision_amount_\"+val].value=format_noobject(m_provision_amount); ");
				out.println("m_diff=format_noobject(parseFloat(unformat_noobject(m_provision_amount)) - parseFloat(unformat_noobject(m_txt_cumilative_provision))); ");
				out.println("document.Form1.elements[\"txt_difference_\"+val].value=format_noobject(m_diff); ");
				out.println("}");
				out.println("}");
			
			
			out.println("function check_number_val2(obj,size,val){");
			out.println("if(isnumberok(obj,size)){"); 
			out.println("check_number_precent(obj,size);");
			out.println("calculate_provision2(obj,val);");
			out.println("}");
			out.println("}");			
			
			
				
				out.println("function calculate_provision(obj,val){");
				
				out.println("var m_present_cap=0;");
				out.println("var m_valuation_amt=0;");
				out.println("var m_present_cap_provi=0;");
				out.println("var m_provision_rate=0;");
				out.println("var m_provision_amt=0;");
				out.println("var m_provision_old=0;");
				out.println("var m_actual_provision=0;");
				
				out.println("m_present_cap       = document.Form1.elements[\"TXT_PRESENT_CAP_\"+val].value;");
				out.println("m_valuation_amt     = document.Form1.elements[\"TXT_VALUATION_AMT_\"+val].value;");
				//out.println("m_present_cap_provi = document.Form1.elements[\"TXT_PRESENT_CAP_PROVI_\"+val].value;");
				out.println("m_provision_rate    = document.Form1.elements[\"TXT_PROVISION_RATE_\"+val].value;");
				//out.println("m_provision_amt     = document.Form1.elements[\"TXT_PROVISION_AMT_\"+val].value;");
				out.println("m_provision_old     = document.Form1.elements[\"TXT_PROVISION_OLD_\"+val].value;");
				//out.println("m_actual_provision  = document.Form1.elements[\"TXT_ACTUAL_PROVISION_\"+val].value;");
								
				
				out.println("m_present_cap_provi=format_noobject(parseFloat(unformat_noobject(m_present_cap)) - parseFloat(unformat_noobject(m_valuation_amt))); ");
				out.println("document.Form1.elements[\"TXT_PRESENT_CAP_PROVI_\"+val].value=format_noobject(m_present_cap_provi); ");
				
				out.println("m_provision_amt=(parseFloat(unformat_noobject(m_present_cap_provi))*parseFloat(unformat_noobject(m_provision_rate)))/100;");
				out.println("m_provision_amt       = (parseFloat(unformat_noobject(format_noobject(m_present_cap_provi)))*parseFloat(unformat_noobject(m_provision_rate)))/100 ");
				out.println("document.Form1.elements[\"TXT_PROVISION_AMT_\"+val].value    = format_noobject(m_provision_amt); ");
				
				out.println("document.Form1.elements[\"TXT_ACTUAL_PROVISION_\"+val].value = parseFloat(unformat_noobject(m_provision_old)) + m_provision_amt; ");
								
				out.println("}");
				
				out.println("function calculate_provision2(obj,val){");
				
				
				out.println("var m_present_cap=0;");
				out.println("var m_valuation_amt=0;");
				out.println("var m_present_cap_provi=0;");
				out.println("var m_provision_rate=0;");
				out.println("var m_provision_amt=0;");
				out.println("var m_provision_old=0;");
				out.println("var m_actual_provision=0;");
								
				out.println("m_present_cap_provi = document.Form1.elements[\"TXT_PRESENT_CAP_PROVI_\"+val].value;");
				out.println("m_provision_rate    = document.Form1.elements[\"TXT_PROVISION_RATE_\"+val].value;");
				out.println("m_provision_amt     = document.Form1.elements[\"TXT_PROVISION_AMT_\"+val].value;");
				out.println("m_provision_old     = document.Form1.elements[\"TXT_PROVISION_OLD_\"+val].value;");
				//out.println("m_actual_provision  = document.Form1.elements[\"TXT_ACTUAL_PROVISION_\"+val].value;");
								
				
				//out.println("m_present_cap_provi=parseFloat(m_present_cap) - parseFloat(m_valuation_amt); ");
				//out.println("document.Form1.elements[\"TXT_PRESENT_CAP_PROVI_\"+val].value=m_present_cap_provi; ");
				
				out.println("m_provision_amt=(parseFloat(unformat_noobject(m_present_cap_provi))*parseFloat(unformat_noobject(m_provision_rate)))/100;");
				out.println("document.Form1.elements[\"TXT_PROVISION_AMT_\"+val].value=m_provision_amt; ");
				
				out.println("document.Form1.elements[\"TXT_PROVISION_AMT_\"+val].value=parseFloat(unformat_noobject(m_provision_old))-parseFloat(unformat_noobject(m_provision_amt)); ");

				
				
				out.println("}");
				
				out.println("function show_charges_drill(m_facility_no) {");
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Agreement_Register?chksql=load_agreement_regi&rpt_type=\"+m_rpt_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"\";");//MOD BY LALANKA ON 07-11-2009
			  out.println("    popupwin=window.open(m_url,'displayWindow1','left=10,top=110,width=975,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");

				
				out.println("}");
				

        out.println("</SCRIPT> "); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				

       	out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<BR>");  
				out.println("<p><center><b>Provision for Bad and Doubtful Debts and Income Suspension as at "+m_current_date+"</b><center></p>"); 
				out.println("<BR>"); 
				
				
				m_string=m_string+"<table align='center' width='100%' class='table' >";
        m_string=m_string+"<tr>";
    		m_string=m_string+"<td width='30%'><input class='but_input' type='button' name='BUT_RUN_REPORT' value=\"Save\" onClick=\"before_submit()\" style='width:100'></td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table><br>";
       	
				m_string=m_string+"<table width='100%'  border='1' cellspacing='0' cellpadding='0' bordercolor='silver' >";
				m_string=m_string+"<tr bgcolor='silver' >";
				m_string=m_string+"<td width='5%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<td bgcolor='silver' width='64'>Month End </td>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='5%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<td  bgcolor='silver' width='150'>Facility No </td>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='5%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<td  bgcolor='silver' width='150'>Client's Name </td>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='7%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<td  bgcolor='silver' width='78'>Suspended </td>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='6%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<td  bgcolor='silver' width='80'>C/A Balance As suspended </td>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='6%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<td  bgcolor='silver' width='80'>C/A Balance </td>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td colspan='4'><div align='center'>Suspended Income +VAT</div></td>";
				m_string=m_string+"<td width='7%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td  width='92'>Cumilative </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td >Income +VAT </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='5%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<tr bgcolor='silver' >";
				m_string=m_string+"<td  width='81'>Capital </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td >O/S </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='6%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td  width='105'>Valuation of </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td >Security </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='6%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td  width='80'>Net </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td >Expoture </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='6%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td  width='110'>No Of Months from </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td >Suspended Date </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='6%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td  width='57'>Provision </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td >Rate </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='5%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td  width='60'>Provision Amt </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td >to be made </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='1%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td  width='109'>Cumilative provided </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td >Amt As per A/C </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='3%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td  width='148'>Provision to be made or </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td >Reversed in this month </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td >(Over)/Short </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td >Provision for the month </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'> ";
				m_string=m_string+"<td width='7%'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<td bgcolor='silver' width='75'>N.Interest </td>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='8%'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<td  bgcolor='silver' width='80'>O.P.Interest </td>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='6%'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<td  bgcolor='silver' width='92'>Charges </td>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='5%'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<td  bgcolor='silver' width='73'>VAT </td>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"</tr>";
				
				rs1= stmt1.executeQuery(
				" SELECT "+
				" TO_CHAR(PROVISION_DATE,'DD-MM-YYYY'), "+ //1
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE) FULL_NAME , "+ //2
				" A.CLIENT_CODE, "+ //3
				" A.FACILITY_NO, "+ //4
				" TO_CHAR(A.SUS_DATE,'DD-MM-YYYY') SUS_DATE ,"+ //5
				" ROUND("+m_schema_name+".FA_CLIENT_AV_LOAN_BAL_SUS(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(LAST_DAY(ADD_MONTHS(A.SUS_DATE,-1)),'DD-MM-YYYY')),2) SUS_DATE_BAL , "+ //6
				" NVL(B.CUR_ACC_BALANCE,0) ,"+ //7
				" NVL(B.NORMAL_INT_SUS,0) ,"+ //8
				" NVL(B.OVERDUE_INT_SUS,0) , "+ //9
				" NVL(B.CHARGE_INT_SUS,0) ,"+ //10
				" NVL(VAT_SUSPENSE,0) ,  "+ //11
				" NVL(B.NORMAL_INT_SUS,0) + NVL(B.OVERDUE_INT_SUS,0) +  NVL(B.CHARGE_INT_SUS,0) + NVL(VAT_SUSPENSE,0)  ,  "+ //12
				" ROUND(SUS_MONTHS,0) ,"+ //13
				" B.PROVISION_RATE, "+ //14
				" NVL(VALUATION,0) VALUATION, "+ //15
				" B.PROVISION_AMT ,"+ //16
				" NVL(ACTUAL_PROVISION,0),  "+ //17
				" "+m_schema_name+".FA_CO_GET_PROV_OLD(A.FACILITY_NO,'"+m_end_date+"') , "+ //18
				" "+m_schema_name+".FA_CO_GET_INCOME_SUS_OLD(A.FACILITY_NO,'"+m_end_date+"') "+ //19
				" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY  A , "+m_schema_name+".FA_CO_PRO_PROVISION_DETAILS B "+
				" WHERE A.FACILITY_NO=B.FACILITY_NO "+
				" AND   B.PROVISION_DATE=TO_DATE('"+m_end_date+"','DD-MM-YYYY') "+
				" ORDER BY PROVISION_DATE,B.SUS_DATE,A.FACILITY_NO ");
				
				double m_precent_cap=0;
				int    m_sus_months=0;
				int chk_nums=0;
				int j=1;
				double m_prov_amt_init=0;
				double m_diff=0;
				double m_cumulitave_income=0;
				double m_valuation=0;
				
				
				while(rs1.next()){
				
				if(j==0){
				m_string=m_string+"<tr >";//bgcolor=\"#FFFFFF\"
				j=1;
				}
				else{
				m_string=m_string+"<tr >";// bgcolor='silver'
				j=0;
				}
				
				m_valuation         = rs1.getDouble(15);
				m_cumulitave_income = rs1.getDouble(12) +rs1.getDouble(19);
				m_precent_cap       = rs1.getDouble(7) - m_cumulitave_income;
				m_prov_amt_init     = ((m_precent_cap-m_valuation)*rs1.getInt(14))/100;
				m_diff              = m_prov_amt_init - rs1.getDouble(18);
				
				
				
				//m_string=m_string+"<tr>";
				m_string=m_string+"<td>"+rs1.getString(1)+"</td>";
				m_string=m_string+"<td><input type='hidden' name='txt_hidden_facility_no_"+chk_nums+"' value="+rs1.getString(4)+">"+rs1.getString(4)+"</td>";
				m_string=m_string+"<td>"+rs1.getString(2)+"</td>";
				m_string=m_string+"<td>"+rs1.getString(5)+"</td>";
				m_string=m_string+"<td align='right'>"+nf.format(rs1.getDouble(6))+"</td>";
				m_string=m_string+"<td align='right'>"+nf.format(rs1.getDouble(7))+"</td>";
				m_string=m_string+"<td align='right'>"+nf.format(rs1.getDouble(8))+"</td>";
				m_string=m_string+"<td align='right'>"+nf.format(rs1.getDouble(9))+"</td>";
				m_string=m_string+"<td align='right'>"+nf.format(rs1.getDouble(10))+"</td>";
				m_string=m_string+"<td align='right'>"+nf.format(rs1.getDouble(11))+"</td>";
				//m_string=m_string+"<td align='right'>"+nf.format(m_cumulitave_income)+"</td>";
				m_string=m_string+"<td align='right'width='10%' class=div_input><input type='text' class='txt_input' name='txt_cumulitave_income_"+chk_nums+"' value="+nf.format(m_cumulitave_income)+" style=\"{width:100px;text-align:right;}\" disabled ></td>";
				m_string=m_string+"<td align='right'width='10%' class=div_input><input type='text' class='txt_input' name='txt_capital_amt_"+chk_nums+"' value="+nf.format(m_precent_cap)+" style=\"{width:100px;text-align:right;}\" disabled ></td>";
				m_string=m_string+"<td align='right'width='10%' class=div_input><input type='text' class='txt_input' name='txt_valuation_amt_"+chk_nums+"' value="+nf.format(rs1.getDouble(15))+" style=\"{width:100px;text-align:right;}\" onBlur=\"check_number_val(this,22,"+chk_nums+")\"></td>";
				m_string=m_string+"<td align='right'width='10%' class=div_input><input type='text' class='txt_input' name='txt_netexposure_amt_"+chk_nums+"' value="+nf.format(m_precent_cap-m_valuation)+" style=\"{width:100px;text-align:right;}\"  disabled ></td>";
				m_string=m_string+"<td align='right'>"+nf1.format(rs1.getInt(13))+"</td>";
				m_string=m_string+"<td align='right'><input type='hidden' class='txt_input' name='txt_hidden_provision_rate_"+chk_nums+"' value="+nf1.format(rs1.getInt(14))+">"+nf1.format(rs1.getInt(14))+"</td>";
				m_string=m_string+"<td align='right'width='10%' class=div_input><input type='text' class='txt_input' name='txt_provision_amount_"+chk_nums+"' value="+nf.format(m_prov_amt_init)+" style=\"{width:100px;text-align:right;}\" disabled></td>";
				m_string=m_string+"<td align='right'width='10%' class=div_input><input type='text' class='txt_input' name='txt_cumilative_provision_"+chk_nums+"' value="+nf.format(rs1.getDouble(18))+" style=\"{width:100px;text-align:right;}\" disabled ></td>";
				m_string=m_string+"<td align='right'width='10%' class=div_input><input type='text' class='txt_input' name='txt_difference_"+chk_nums+"' value="+nf.format(m_diff)+" style=\"{width:100px;text-align:right;}\" disabled ></td>";
				//m_string=m_string+"<td>&nbsp;</td>";
				m_string=m_string+"</tr>";
				chk_nums=chk_nums+1;      
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";		
				
				out.println(m_string);
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v2.js'></SCRIPT>"); 
		    out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</FORM>"); 
				out.println("</body>"); 
				out.println("</html>"); 

			}
			
				else if(m_chksql.equals("INCOME_SUSPENCE_REPORT_VIEW")){
				
				String m_start_date=req.getParameter("start_date");
				String m_end_date=req.getParameter("end_date");

				String m_string="";		
				String m_date1="" ,m_date2="",m_date3="";
				String m_month1="" ,m_month2="",m_month3="";
				
         rs1= stmt1.executeQuery( " SELECT  TO_CHAR(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),'DD-MM-YYYY'),                  "+
					                        "         TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1),'DD-MM-YYYY') ,  "+
																  "         TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-2),'DD-MM-YYYY') ,  "+
																  "         TO_CHAR(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),'MON') ,                        "+
																	"         TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1),'MON') ,         "+
																	"         TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-2),'MON')           "+
					                       "  FROM    DUAL ");
																	
					if(rs1.next()){
					m_date1=rs1.getString(1);
					m_date2=rs1.getString(2);
					m_date3=rs1.getString(3);
					
					m_month1=rs1.getString(4);
					m_month2=rs1.getString(5);
					m_month3=rs1.getString(6);
					
					}	
					
				rs= stmt.executeQuery (" SELECT TO_CHAR(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1)),'DD-MON-YYYY'),"+
				" TO_CHAR(LAST_DAY(TO_DATE('"+m_start_date+"','DD-MM-YYYY')),'DD-MON-YYYY') FROM DUAL ");
				
				String m_last_date="";
				String m_current_date="";
				
				if(rs.next()){
				m_last_date=rs.getString(1);
				m_current_date=rs.getString(2);
				}
				
									
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Provision for Bad and Doubtful Debts and Income Suspension</TITLE>"); 
				
				out.println("<SCRIPT> "); 
					
				out.println("function show_charges_drill(m_facility_no) {");
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Agreement_Register?chksql=load_agreement_regi&rpt_type=\"+m_rpt_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"\";");//MOD BY LALANKA ON 07-11-2009
			  out.println("    popupwin=window.open(m_url,'displayWindow1','left=10,top=110,width=975,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				
				out.println("}");
				

        out.println("</SCRIPT> "); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				

       	out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<BR>");  
				out.println("<p><center><b>Provision for Bad and Doubtful Debts and Income Suspension as at "+m_current_date+"</b><center></p>"); 
				out.println("<BR>"); 
				
				
				m_string=m_string+"<table width='100%'  border='1' cellspacing='0' cellpadding='0' bordercolor='silver' >";
				m_string=m_string+"<tr bgcolor='silver'  >";//'silver'
				m_string=m_string+"<td width='3%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<td bgcolor='silver' width='44'>Month End </td>";
				m_string=m_string+"</table></td>";
				
				m_string=m_string+"<td width='5%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<td  bgcolor='silver' width='50'>Facility Code </td>";
				m_string=m_string+"</table></td>";
				
				m_string=m_string+"<td width='5%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<td  bgcolor='silver' width='150'>Client's Name </td>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='3%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<td  bgcolor='silver' width='78'>Suspended </td>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='4%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<td  bgcolor='silver' width='80'>C/A Balance As suspended </td>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='4%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<td  bgcolor='silver' width='80'>C/A Balance </td>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td colspan='4'><div align='center'>Suspended Income +VAT</div></td>";
				m_string=m_string+"<td width='7%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td  width='92'>Cumilative </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td >Income +VAT </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='5%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<tr bgcolor='silver' >";
				m_string=m_string+"<td  width='81'>Capital </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td >O/S </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='5%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td  width='105'>Valuation of </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td >Security </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='5%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td  width='80'>Net </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td >Expoture </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='5%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td  width='100'>No Of Months from </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td >Suspended Date </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='5%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td  width='57'>Provision </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td >Rate </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='5%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td  width='60'>Provision Amt </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td >to be made </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='1%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td  width='100'>Cumilative provided </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td >Amt As per A/C </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='3%' rowspan='2'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td  width='100'>Provision to be made or </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td >Reversed in this month </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td >(Over)/Short </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td >Provision for the month </td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<tr bgcolor='silver'> ";
				m_string=m_string+"<td width='5%'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<td bgcolor='silver' width='50'>N.Interest </td>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='5%'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<td  bgcolor='silver' width='50'>O.P.Interest </td>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='5%'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<td  bgcolor='silver' width='50'>Charges </td>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"<td width='5%'><table cellspacing='0' cellpadding='0'>";
				m_string=m_string+"<td  bgcolor='silver' width='50'>VAT </td>";
				m_string=m_string+"</table></td>";
				m_string=m_string+"</tr>";
				
				
				rs1= stmt1.executeQuery(
				" SELECT "+
				" TO_CHAR(PROVISION_DATE,'DD-MM-YYYY'), "+ //1
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE) FULL_NAME , "+ //2
				" A.CLIENT_CODE, "+ //3
				" A.FACILITY_NO, "+ //4
				" TO_CHAR(A.SUS_DATE,'DD-MM-YYYY') SUS_DATE ,"+ //5
				" ROUND("+m_schema_name+".FA_CLIENT_AV_LOAN_BAL_SUS(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(LAST_DAY(ADD_MONTHS(A.SUS_DATE,-1)),'DD-MM-YYYY')),2) SUS_DATE_BAL , "+ //6
				" NVL(B.CUR_ACC_BALANCE,0) ,"+ //7
				" NVL(B.NORMAL_INT_SUS,0) ,"+ //8
				" NVL(B.OVERDUE_INT_SUS,0) , "+ //9
				" NVL(B.CHARGE_INT_SUS,0) ,"+ //10
				" NVL(VAT_SUSPENSE,0) ,  "+ //11
				" NVL(B.NORMAL_INT_SUS,0) + NVL(B.OVERDUE_INT_SUS,0) +  NVL(B.CHARGE_INT_SUS,0) + NVL(VAT_SUSPENSE,0) ,  "+ //12
				" ROUND(SUS_MONTHS,0) ,"+ //13
				" B.PROVISION_RATE, "+ //14
				" NVL(VALUATION,0) VALUATION, "+ //15
				" B.PROVISION_AMT ,"+ //16
				" NVL(ACTUAL_PROVISION,0),  "+ //17
				" "+m_schema_name+".FA_CO_GET_PROV_OLD(A.FACILITY_NO,'"+m_end_date+"') , "+ //18
				" "+m_schema_name+".FA_CO_GET_INCOME_SUS_OLD(A.FACILITY_NO,TO_CHAR(PROVISION_DATE,'DD-MM-YYYY')) "+ //19
				" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY  A , "+m_schema_name+".FA_CO_PRO_PROVISION_DETAILS B "+
				" WHERE A.FACILITY_NO=B.FACILITY_NO "+
				//" AND   B.PROVISION_DATE=TO_DATE('"+m_end_date+"','DD-MM-YYYY') "+
				" ORDER BY PROVISION_DATE,B.SUS_DATE,A.FACILITY_NO ");
				
				double m_precent_cap=0;
				double m_net_exposure=0;
				double m_diff=0;
				int    m_sus_months=0;
				int chk_nums=0;
				int j=1,count=0;
				double m_sub_provision_total=0;
				double m_sub_cumulative_provision=0;
				double m_cumulitave_income=0;
				
				String m_provision_date="";
				boolean  more1=rs1.next();
				
				while(more1){
				count=0;
				m_provision_date=rs1.getString(1);
				if(j==0){	j=1;}
				else{	j=0;
				}
				m_sub_provision_total=0;
				m_sub_cumulative_provision=0;
				
				while(m_provision_date.equals(rs1.getString(1))) {
				m_sus_months=rs1.getInt(13);
				
				if (m_sus_months==3 || m_sus_months==9 || m_sus_months==15) {
				m_string=m_string+"<tr bgcolor=\"#A3B2CC\">";//'silver'
				}
				else if (m_sus_months!=3 || m_sus_months!=9 || m_sus_months!=15) {
								
				if(j==1){
				m_string=m_string+"<tr >";//bgcolor=\"#FFFFFF\"
				}
				else{
				m_string=m_string+"<tr  >";//bgcolor='silver'
				}
				
				}
				
				m_cumulitave_income = rs1.getDouble(12) +rs1.getDouble(19);
				m_precent_cap       = rs1.getDouble(7)  - m_cumulitave_income;
				m_net_exposure      = m_precent_cap     - rs1.getDouble(15);
				m_diff              = rs1.getDouble(16) - rs1.getDouble(17);
								
				if(count==0){
				m_string=m_string+"<td>"+rs1.getString(1)+"</td>";
				}else{
				m_string=m_string+"<td>&nbsp;</td>";
				}
				
				m_string=m_string+"<td>"+rs1.getString(4)+"</td>";
				m_string=m_string+"<td>"+rs1.getString(2)+"</td>";
				m_string=m_string+"<td>"+rs1.getString(5)+"</td>";
				m_string=m_string+"<td align='right'>"+nf.format(rs1.getDouble(6))+"</td>";
				m_string=m_string+"<td align='right'>"+nf.format(rs1.getDouble(7))+"</td>";
				m_string=m_string+"<td align='right'>"+nf.format(rs1.getDouble(8))+"</td>";
				m_string=m_string+"<td align='right'>"+nf.format(rs1.getDouble(9))+"</td>";
				m_string=m_string+"<td align='right'>"+nf.format(rs1.getDouble(10))+"</td>";
				m_string=m_string+"<td align='right'>"+nf.format(rs1.getDouble(11))+"</td>";
				//m_string=m_string+"<td align='right'>"+nf.format(rs1.getDouble(12))+"</td>";
				m_string=m_string+"<td align='right'>"+nf.format(m_cumulitave_income)+"</td>";
				m_string=m_string+"<td align='right'>"+nf.format(m_precent_cap)+"</td>";
				m_string=m_string+"<td align='right'>"+nf.format(rs1.getDouble(15))+"</td>";
				m_string=m_string+"<td align='right'>"+nf.format(m_net_exposure)+"</td>";
				m_string=m_string+"<td align='right'>"+nf1.format(rs1.getInt(13))+"</td>";
				m_string=m_string+"<td align='right'>"+nf1.format(rs1.getInt(14))+"</td>";
				m_string=m_string+"<td align='right'>"+nf.format(rs1.getDouble(16))+"</td>";
				m_string=m_string+"<td align='right'>"+nf.format(rs1.getDouble(17))+"</td>";
				m_string=m_string+"<td align='right'>"+nf.format(m_diff)+"</td>";
				//m_string=m_string+"<td>&nbsp;</td>";
				m_string=m_string+"</tr>";
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				count=1;
				m_sub_provision_total      = m_sub_provision_total+rs1.getDouble(16);
				m_sub_cumulative_provision = m_sub_cumulative_provision+rs1.getDouble(17);
				more1=rs1.next();
				if (!more1){break;}
				}
				
				m_string=m_string+"<tr >";
				m_string=m_string+"<td colspan='16' align='right'  ><b>Total</b></td>";
				m_string=m_string+"<td align='right' bgcolor='silver'><b>"+nf.format(m_sub_provision_total)+"</td>";
				m_string=m_string+"<td align='right' bgcolor='silver'><b>"+nf.format(m_sub_cumulative_provision)+"</td>";
				m_string=m_string+"<td align='right' bgcolor='silver'><b>"+nf.format(m_sub_cumulative_provision-m_sub_provision_total)+"</td>";
				m_string=m_string+"</tr>";
				
				
				
				}
				m_string=m_string+"</table>";		
				out.println(m_string);
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v2.js'></SCRIPT>"); 
		    out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</FORM>"); 
				out.println("</body>"); 
				out.println("</html>"); 

			}

			
			else if(m_chksql.equals("DRILL_OVER_INT")){
				
				
				String m_start_date  =req.getParameter("start_date");
				String m_end_date    =req.getParameter("end_date");
				String m_facility_no =req.getParameter("facility_no");
				String m_string="";

									
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Provision for Bad and Doubtful Debts and Income Suspension</TITLE>"); 
				
				out.println("<SCRIPT> "); 
				
				out.println("var chque_status=0;");

        out.println("</SCRIPT> "); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				

       	out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Trn Date</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>DR/CR</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Amount</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Desc</b></DIV></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				
				int j=1;
				
				
				rs1= stmt.executeQuery(
				" SELECT CLIENT_CODE, FACILITY_CODE, TO_CHAR(TRNDATE,'DD-MM-YYYY') ,DRCR_STATUS,TRNAMOUNT,PROC_DESC "+
				" FROM  "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
				" WHERE TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_start_date+"','DD-MM-YYYY') "+
				" AND   TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_end_date+"','DD-MM-YYYY') "+ 
				" AND   FACILITY_CODE='"+m_facility_no+"' "+
				" AND   PROC_DESC='DAILY INTEREST' "+
				"");
				
				
								
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor='silver' >";
						j=0;
					}
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(3)+"</td>"; 
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(4)+"</td>"; 
					m_string=m_string+"<td width='10%' ><DIV class=div_input>"+nf.format(rs1.getDouble(5))+"</DIV></td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(6)+"</td>"; 
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				
				
				
				
				m_string=m_string+"</table>";
				out.println(m_string);
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
		    out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</FORM>"); 
				out.println("</body>"); 
				out.println("</html>"); 

			}
			
				else if(m_chksql.equals("DRILL_BREAKDOWN")){
				
				
				String m_start_date  =req.getParameter("start_date");
				String m_end_date    =req.getParameter("end_date");
				String m_facility_no =req.getParameter("facility_no");
				String m_string="";

									
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Provision for Bad and Doubtful Debts and Income Suspension</TITLE>"); 
				
				out.println("<SCRIPT> "); 
				
			out.println("function show_normal_int_break_down(m_facility_no){");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_Income_Provision_sql_validations_normal?chksql=DRILL_NORMAL_INT&facility_no=\"+m_facility_no+\"&start_date="+m_start_date+"&end_date="+m_end_date+"\"");	
			out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println("}");
			
			out.println("function show_over_int_break_down(m_facility_no){");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_Income_Provision_sql_validations_normal?chksql=DRILL_OVER_INT&facility_no=\"+m_facility_no+\"&start_date="+m_start_date+"&end_date="+m_end_date+"\"");	
			out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println("}");
			
			out.println("function show_charge_break_down(m_facility_no){");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_Income_Provision_sql_validations_normal?chksql=DRILL_CHARGES&facility_no=\"+m_facility_no+\"&start_date="+m_start_date+"&end_date="+m_end_date+"\"");	
			out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
			out.println("}");

				
				out.println("var chque_status=0;");

        out.println("</SCRIPT> "); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				

       	out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Client Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Normal Interest Suspense</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Over Due Interest Suspense</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Charges Suspense</b></DIV></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				
				int j=1;
				
					rs1= stmt1.executeQuery(
					" SELECT "+
					" A.FACILITY_NO, "+ //1
					" A.CLIENT_CODE, "+ //2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE) FULL_NAME , "+ //3
					" A.CREDIT_LIMIT, "+ //4
					" NVL(B.NORMAL_INT_SUS,0)  ,  "+ //5
					" NVL(B.OVERDUE_INT_SUS,0) ,  "+ //6
					" NVL(B.CHARGE_INT_SUS,0)  ,  "+ //7
					" NVL(B.NORMAL_INT_SUS,0) + NVL(B.OVERDUE_INT_SUS,0) +  NVL(B.CHARGE_INT_SUS,0)   "+ //8
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY  A , "+m_schema_name+".FA_CO_PRO_PROVISION_DETAILS B "+
					" WHERE A.FACILITY_NO=B.FACILITY_NO "+
					" AND   B.PROVISION_DATE=TO_DATE('"+m_start_date+"','DD-MM-YYYY') "+
					" ORDER BY FULL_NAME ");

				
				
								
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor='silver' >";
						j=0;
					}
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(1)+"</td>"; 
					m_string=m_string+"<td width='20%' class=div_input>"+rs1.getString(3)+"</td>"; 
					m_string=m_string+"<td width='10%' style=\"{cursor:hand}\" onClick=show_normal_int_break_down('"+rs1.getString(1)+"') ><DIV class=div_input><u>"+nf.format(rs1.getDouble(5))+"</u></DIV></td>";
					m_string=m_string+"<td width='10%' style=\"{cursor:hand}\" onClick=show_over_int_break_down('"+rs1.getString(1)+"') ><DIV class=div_input><u>"+nf.format(rs1.getDouble(6))+"</u></DIV></td>";
					m_string=m_string+"<td width='10%' style=\"{cursor:hand}\" onClick=show_charge_break_down('"+rs1.getString(1)+"') ><DIV class=div_input><u>"+nf.format(rs1.getDouble(7))+"</u></DIV></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				
				
				
				
				m_string=m_string+"</table>";
				out.println(m_string);
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
		    out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</FORM>"); 
				out.println("</body>"); 
				out.println("</html>"); 

			}
				
				else if(m_chksql.equals("DRILL_NORMAL_INT")){
				
				
				String m_start_date  =req.getParameter("start_date");
				String m_end_date    =req.getParameter("end_date");
				String m_facility_no =req.getParameter("facility_no");
				String m_string="";

									
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Provision for Bad and Doubtful Debts and Income Suspension</TITLE>"); 
				
				out.println("<SCRIPT> "); 
				
				out.println("var chque_status=0;");

        out.println("</SCRIPT> "); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				

       	out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Trn Date</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>DR/CR</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Amount</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Desc</b></DIV></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				
				int j=1;
				
				
				rs1= stmt.executeQuery(
				" SELECT CLIENT_CODE, FACILITY_CODE, TO_CHAR(TRNDATE,'DD-MM-YYYY')  ,DRCR_STATUS,TRNAMOUNT,PROC_DESC "+
				" FROM  "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
				" WHERE TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_start_date+"','DD-MM-YYYY') "+
				" AND   TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_end_date+"','DD-MM-YYYY') "+ 
				" AND   FACILITY_CODE='"+m_facility_no+"' "+
				" AND   PROC_DESC='DAILY INTEREST' "+
				"");
				
				
								
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor='silver' >";
						j=0;
					}
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(3)+"</td>"; 
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(4)+"</td>"; 
					m_string=m_string+"<td width='10%' ><DIV class=div_input>"+nf.format(rs1.getDouble(5))+"</DIV></td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(6)+"</td>"; 
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				
				
				
				
				m_string=m_string+"</table>";
				out.println(m_string);
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
		    out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</FORM>"); 
				out.println("</body>"); 
				out.println("</html>"); 

			}
			

			
			  else if(m_chksql.equals("INCOME_SUSPENCE_REPORT_OLD")){
				
				String m_start_date=req.getParameter("start_date");
				String m_end_date=req.getParameter("end_date");
					
				rs= stmt.executeQuery (" SELECT TO_CHAR(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1)),'DD-MON-YYYY'),"+
				" TO_CHAR(LAST_DAY(TO_DATE('"+m_start_date+"','DD-MM-YYYY')),'DD-MON-YYYY') FROM DUAL ");
				
				String m_last_date="";
				String m_current_date="";
				
				if(rs.next()){
				m_last_date=rs.getString(1);
				m_current_date=rs.getString(2);
				}

				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".FA_PRO_PROVISION_RPT(:1,:2,:3); END;");
				callstmt.setString(1,m_start_date);
				callstmt.setString(2,m_end_date);
				callstmt.setString(3,m_username);
				callstmt.execute();
				callstmt.close();
						
				String m_string="";	
				
				rs= stmt.executeQuery (" SELECT "+
				" A.FACILITY_NO, "+//1
				" A.CLIENT_CODE,"+//2
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE) CLIENT_NAME,"+//3
				" 0 FINANCE_AMT, "+//4
				" 0 INT_SUSPENSE "+//5
				" FROM "+m_schema_name+".FA_CO_PRO_PROVISION_DETAILS A "+
				" WHERE A.PROVISION_DATE=TO_DATE('"+m_start_date+"','DD-MM-YYYY') "+
				" ORDER BY A.FACILITY_NO ");
				
				
				m_string=m_string+"<table align='center' width='100%' class='table'>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td width='1%' ></td>"; 
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV class=div_input>Facility No</DIV></td>";//1
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Client Name</DIV></td>";//2
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Finance Amt</DIV></td>"; //4
				m_string=m_string+"<td width='15%' ></td>"; 
				m_string=m_string+"<td width='15%' ></td>"; 
				m_string=m_string+"<td width='15%' ></td>"; 
				m_string=m_string+"</tr>";
				
				
				int chk_nums=0;
				int j=0;
				while(rs.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor='silver'>";
						j=0;
					}
					
					//double m_net_exposure=rs.getDouble(20) + rs.getDouble(21)+ rs.getDouble(13) - rs.getDouble(10);
					//double m_provision_amt=m_net_exposure*(rs.getDouble(12)/100);
					
					//double m_rental_arrears=rs.getDouble(8);
					
					m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='15%' ><INPUT TYPE='HIDDEN' class='txt_input' NAME='APPLICATION_CODE_"+chk_nums+"' VALUE=\""+rs.getString(1)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='FINNACE_NO_"+chk_nums+"' VALUE=\""+rs.getString(2)+"\">"+rs.getString(2)+"</td>"; //ok
					m_string=m_string+"<td width='15%' ><INPUT TYPE='HIDDEN' class='txt_input' NAME='CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs.getString(3)+"\">"+rs.getString(4)+"</td>"; //ok
					m_string=m_string+"<td width='15%' >"+rs.getString(2)+"</td>"; //ok
					m_string=m_string+"<td width='15%' >"+rs.getString(2)+"</td>"; //ok
					m_string=m_string+"<td width='15%' >"+rs.getString(2)+"</td>"; //ok
					m_string=m_string+"<td width='15%' >"+rs.getString(2)+"</td>"; //ok
				  m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				
				out.println(m_string);
			  }else if(m_chksql.equals("INCOME_SUSPENCE_REPORT_EDIT")){
				
				String m_start_date=req.getParameter("start_date");
				String m_end_date=req.getParameter("end_date");
					
				rs= stmt.executeQuery (" SELECT TO_CHAR(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1)),'DD-MON-YYYY'),"+
				" TO_CHAR(LAST_DAY(TO_DATE('"+m_start_date+"','DD-MM-YYYY')),'DD-MON-YYYY') FROM DUAL ");
				
				String m_last_date="";
				String m_current_date="";
				
				if(rs.next()){
				m_last_date=rs.getString(1);
				m_current_date=rs.getString(2);
				}

				rs= stmt.executeQuery (" SELECT "+
				" A.APPLICATION_NO, "+//1
				" A.FINANCE_NO,"+//2
				" A.CLIENT_CODE,"+//3
				" "+m_schema_name+".AF_CO_GET_APP_NAME(A.APPLICATION_NO) CNAME, "+//4
				" "+m_schema_name+".AF_CO_GET_ASSET_DESC(A.APPLICATION_NO) ASSET_DETAIL, "+//5
				" "+m_schema_name+".AF_CO_GET_APP_FINANCE_AMT(A.APPLICATION_NO) FINANCE_AMT, "+//6
				" A.RENTAL_ARREAS_NO RENTAL_ARR, "+//7
				" A.RENTAL_ARREAS RENTAL_VAL, "+//8
				" A.CAP_OUTSTANDING CAP_OUTS, "+//9
				" "+m_schema_name+".AF_CO_GET_APP_NIBSM(A.APPLICATION_NO) NIBSM, "+//10
				" A.INT_SUSPENCE_AMT INT_SUSPENSE, "+//11
				" A.PROVISION_RATE*100 PROVION_RATE, "+//12
				" "+m_schema_name+".AF_CO_GET_PROV_3M_INT_SUSP(A.APPLICATION_NO,'"+m_start_date+"') INTCOME_SUS_3MONTHS, "+//13
				" "+m_schema_name+".AF_CO_GET_PROV_INT_LAST_MONTH(A.APPLICATION_NO,TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1),'DD-MM-YYYY')) INCOME_SUS_LAST_MONTH, "+//14
				" "+m_schema_name+".AF_CO_GET_PROV_INT_LAST_MONTH(A.APPLICATION_NO,'"+m_start_date+"') INCOME_SUS_CURRENT_MONTH, "+//15
				" "+m_schema_name+".AF_CO_GET_PROVISION_LAST_MONTH(A.APPLICATION_NO,ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1)) PROVISION_PRE_MONTH, "+//16
				" NVL(A.PROVISION_COMMENTS,'-'),"+//17
				" A.ACC_POST_STATUS, "+//18
				" NVL(A.VAT_SUSPENCE_AMT,0) "+//19
				" ,NVL(A.ARREARS_CAPITAL,0) "+//20 // ADDED BY NUWAN DE SILVA
				" ,NVL(A.FUTURE_CAPITAL,0)  "+//21 // ADDED BY NUWAN DE SILVA
				" ,NVL("+m_schema_name+".AF_CO_GET_MONTHS_DIFF(A.APPLICATION_NO,'"+m_end_date+"'),0)  AGREEGATE_AGE "+//22
				" ,"+m_schema_name+".AF_CO_GET_EMP_NAME("+m_schema_name+".AF_GET_COLL_OFFICER(FINANCE_NO)) "+			
				" FROM "+m_schema_name+".AF_CO_PRO_PROVISION_DETAILS A "+
				" WHERE A.PROVISION_DATE=TO_DATE('"+m_start_date+"','DD-MM-YYYY') "+
				//" AND A.APPLICATION_NO='AP20070423-0454'"+
				" ORDER BY A.RENTAL_ARREAS_NO,A.APPLICATION_NO ");
				
				
				String m_string="";	
				
				m_string=m_string+"<table align='center' width='100%' class='table'>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td width='1%' ></td>"; 
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV class=div_input>Finance No</DIV></td>";//1
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Client Name</DIV></td>";//2
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Type of the Asset</DIV></td>"; //3
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Collection Officer</DIV></td>"; //3
				
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Finance Amt</DIV></td>"; //4
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Rental in Arrears No</DIV></td>"; //5
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Aggregate age </DIV></td>"; //
				m_string=m_string+"<td width='15%' style='text-align:center'><DIV  class=div_input>Rental in Arrears Amt</DIV></td>"; //6
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Interest in Suspense "+m_current_date+"</DIV></td>"; //7
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Interest in Suspense "+m_last_date+"</DIV></td>"; //8
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Interest Suspended during the Month</DIV></td>"; //9
        m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Income Susp. in first 3 months</DIV></td>"; //10
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Arrears Capital</DIV></td>"; //11
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Future Capital</DIV></td>"; //12
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>NIBSM/Deposits</DIV></td>"; //13
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Net Exposure</DIV></td>"; //14
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Valuation</DIV></td>"; //15
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Net Exposure After Valuation</DIV></td>"; //16
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Provision Rate</DIV></td>"; //17
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Provision as at "+m_current_date+"</DIV></td>"; //18
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Provision as at "+m_last_date+"</DIV></td>"; //19
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Provision during the Month</DIV></td>"; //20
				m_string=m_string+"<td width='20%' ><DIV class=div_input>VAT susp. Amt</DIV></td>"; //21
				m_string=m_string+"<td width='20%' style='text-align:center'><DIV  class=div_input>Ac. Post Status</DIV></td>"; //22
				//out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Ac. Comments</DIV></td>"); //23
				m_string=m_string+"<td width='*%'></td>";
				m_string=m_string+"</tr>";
				
				/*m_string=m_string+"<table align='center' width='100%' class='table'>";
				m_string=m_string+"<tr bgcolor='silver'>";
				m_string=m_string+"<td width='1%' ></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input>Application No</DIV></td>";//1
				m_string=m_string+"<td width='15%' ><DIV class=div_input>Client Name</DIV></td>";//2
				m_string=m_string+"<td width='15%' ><DIV class=div_input>Type of the Asset</DIV></td>"; //3
				m_string=m_string+"<td width='15%' ><DIV class=div_input>Finance Amt</DIV></td>"; //4
				m_string=m_string+"<td width='15%' ><DIV class=div_input>Rental in Arrears No</DIV></td>"; //5
				m_string=m_string+"<td width='15%' ><DIV class=div_input>Rental in Arrears Amt</DIV></td>"; //6
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Capital Outstanding</DIV></td>"; //7
				m_string=m_string+"<td width='20%' ><DIV class=div_input>NIBSM/Deposits</DIV></td>"; //8
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Valuation</DIV></td>"; //9
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Income Susp. in first 3 months</DIV></td>"; //10
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Net Exposure</DIV></td>"; //11
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Interest in Suspense "+m_last_date+"</DIV></td>"; //12
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Interest in Suspense "+m_current_date+"</DIV></td>"; //13
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Interest Suspended during the Month</DIV></td>"; //14
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Provision Rate</DIV></td>"; //15
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Provision as at "+m_last_date+"</DIV></td>"; //16
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Provision as at "+m_current_date+"</DIV></td>"; //17
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Provision during the Month</DIV></td>"; //18
				m_string=m_string+"<td width='20%' ><DIV class=div_input>VAT susp. Amt</DIV></td>"; //19
				m_string=m_string+"<td width='10%' ></td>"; 
				m_string=m_string+"<td width='*%'></td>";
				m_string=m_string+"</tr>";
				*/

				
				int chk_nums=0;
				int j=0;
				while(rs.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor='silver'>";
						j=0;
					}
					//double m_net_exposure=rs.getDouble(9)-rs.getDouble(10)-0+rs.getDouble(13);
					double m_net_exposure=rs.getDouble(20)+rs.getDouble(21)-rs.getDouble(10)-0+rs.getDouble(13);
					//double m_provision_amt=m_net_exposure*rs.getDouble(12);
					double m_provision_amt=m_net_exposure*(rs.getDouble(12)/100);

					
					
					double m_rental_arrears=rs.getDouble(14)+rs.getDouble(13)+rs.getDouble(20);
					
					m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='15%' ><INPUT TYPE='HIDDEN' class='txt_input' NAME='APPLICATION_CODE_"+chk_nums+"' VALUE=\""+rs.getString(1)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='FINNACE_NO_"+chk_nums+"' VALUE=\""+rs.getString(2)+"\">"+rs.getString(2)+"</td>"; //ok
					m_string=m_string+"<td width='15%' ><INPUT TYPE='HIDDEN' class='txt_input' NAME='CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs.getString(3)+"\">"+rs.getString(4)+"</td>"; //ok
					m_string=m_string+"<td width='15%' >"+rs.getString(5)+"</td>"; //ok
					m_string=m_string+"<td width='15%' >"+rs.getString(23)+"</td>"; //ok
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(6))+"</td>";//ok
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='NO_RENTALS_ARR_"+chk_nums+"' VALUE=\""+rs.getString(7)+"\">"+nf.format(rs.getDouble(7))+"</td>"; //ok
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='NO_AGR_RENTALS_ARR_"+chk_nums+"' VALUE=\""+nf.format(rs.getDouble(7)+ rs.getDouble(22))+"\">"+nf.format(rs.getDouble(7)+ rs.getDouble(22))+"</td>"; //ok //agreegate age
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='AMT_RENTALS_ARR_"+chk_nums+"' VALUE=\""+nf.format(m_rental_arrears)+"\">"+nf.format(m_rental_arrears)+"</td>"; //ok
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(15))+"</td>"; //7
          m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(14))+"</td>"; //8
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(15))+"</td>"; //9
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(13))+"</td>"; //10
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(20))+"</td>"; //11
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(21))+"</td>"; //12
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(10))+"</td>"; //13
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(m_net_exposure)+"</td>";  //14
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(0)+"</td>"; //15
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(0)+"</td>"; //16
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='PROVISION_RATE_"+chk_nums+"' VALUE=\""+nf.format(rs.getDouble(12))+"\">"+nf1.format(rs.getDouble(12))+"%</td>"; //17
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='PROVISION_AMT_"+chk_nums+"' VALUE=\""+nf.format(m_provision_amt)+"\">"+nf.format(m_provision_amt)+"</td>"; //18
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(16))+"</td>"; //19
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(m_provision_amt-rs.getDouble(16))+"</td>"; //20
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(19))+"</td>"; //21
					
					if(rs.getString(18).equals("Y")){
						m_string=m_string+"<td width='9%' class=div_input><INPUT TYPE='HIDDEN' NAME='CHECK_"+chk_nums+"' VALUE=\"N\"><INPUT TYPE='TEXT' class='txt_input' NAME='COMMENT_"+chk_nums+"' VALUE=\""+rs.getString(17)+"\" disabled><INPUT TYPE='CHECKBOX' NAME='CHK_"+chk_nums+"' checked disabled></td>"; //22
					}
					else{
						m_string=m_string+"<td width='9%' class=div_input><INPUT TYPE='HIDDEN' NAME='CHECK_"+chk_nums+"' VALUE=\"Y\"><INPUT TYPE='TEXT' class='txt_input' NAME='COMMENT_"+chk_nums+"' VALUE=\""+rs.getString(17)+"\"><INPUT TYPE='CHECKBOX' NAME='CHK_"+chk_nums+"'></td>";  //22
					}
					
					 //comment by nuwan de silva on 16-09-2008
					/*
					m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='15%' ><INPUT TYPE='HIDDEN' class='txt_input' NAME='APPLICATION_CODE_"+chk_nums+"' VALUE=\""+rs.getString(1)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='FINNACE_NO_"+chk_nums+"' VALUE=\""+rs.getString(2)+"\">"+rs.getString(2)+"</td>"; 
					m_string=m_string+"<td width='15%' ><INPUT TYPE='HIDDEN' class='txt_input' NAME='CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs.getString(3)+"\">"+rs.getString(4)+"</td>"; 
					m_string=m_string+"<td width='15%' >"+rs.getString(5)+"</td>"; 
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(6))+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='NO_RENTALS_ARR_"+chk_nums+"' VALUE=\""+rs.getString(7)+"\">"+rs.getString(7)+"</td>"; 
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='AMT_RENTALS_ARR_"+chk_nums+"' VALUE=\""+nf.format(rs.getDouble(8))+"\">"+nf.format(rs.getDouble(8))+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='CAPITAL_OUTSTANDING_"+chk_nums+"' VALUE=\""+nf.format(rs.getDouble(9))+"\">"+nf.format(rs.getDouble(9))+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(10))+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(0)+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(13))+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(m_net_exposure)+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(14))+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(15))+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='INCOME_SUS_AMT_"+chk_nums+"' VALUE=\""+nf.format(rs.getDouble(15))+"\">"+nf.format(rs.getDouble(15))+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='PROVISION_RATE_"+chk_nums+"' VALUE=\""+nf.format(rs.getDouble(12))+"\">"+nf.format(rs.getDouble(12))+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(16))+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'><INPUT TYPE='HIDDEN' class='txt_input' NAME='PROVISION_AMT_"+chk_nums+"' VALUE=\""+nf.format(m_provision_amt)+"\">"+nf.format(m_provision_amt)+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(m_provision_amt-rs.getDouble(16))+"</td>";
					m_string=m_string+"<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(19))+"</td>";
					
					if(rs.getString(18).equals("Y")){
						m_string=m_string+"<td width='9%' class=div_input><INPUT TYPE='HIDDEN' NAME='CHECK_"+chk_nums+"' VALUE=\"N\"><INPUT TYPE='TEXT' class='txt_input' NAME='COMMENT_"+chk_nums+"' VALUE=\""+rs.getString(17)+"\" disabled><INPUT TYPE='CHECKBOX' NAME='CHK_"+chk_nums+"' checked disabled></td>"; 
					}
					else{
						m_string=m_string+"<td width='9%' class=div_input><INPUT TYPE='HIDDEN' NAME='CHECK_"+chk_nums+"' VALUE=\"Y\"><INPUT TYPE='TEXT' class='txt_input' NAME='COMMENT_"+chk_nums+"' VALUE=\""+rs.getString(17)+"\"><INPUT TYPE='CHECKBOX' NAME='CHK_"+chk_nums+"'></td>"; 
					}
					*/
					
					m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				
				out.println(m_string);
				
			}
			else if(m_chksql.equals("INCOME_SUSPENCE_REPORT_RPT")){
				
				String m_start_date=req.getParameter("start_date");
				String m_end_date=req.getParameter("end_date");
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Provision for Bad and Doubtful Debts and Income Suspension</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			
				rs= stmt.executeQuery (" SELECT TO_CHAR(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1)),'DD-MON-YYYY'),"+
				" TO_CHAR(LAST_DAY(TO_DATE('"+m_start_date+"','DD-MM-YYYY')),'DD-MON-YYYY') FROM DUAL ");
				
				String m_last_date="";
				String m_current_date="";
				
				if(rs.next()){
				m_last_date=rs.getString(1);
				m_current_date=rs.getString(2);
				}
                                   
				rs= stmt.executeQuery (
				//out.println(
				" SELECT "+
				" A.APPLICATION_NO, "+//1
				" A.FINANCE_NO,"+//2
				" A.CLIENT_CODE,"+//3
				" "+m_schema_name+".AF_CO_GET_APP_NAME(A.APPLICATION_NO) CNAME, "+//4
				" "+m_schema_name+".AF_CO_GET_ASSET_DESC(A.APPLICATION_NO) ASSET_DETAIL, "+//5
				" "+m_schema_name+".AF_CO_GET_APP_FINANCE_AMT(A.APPLICATION_NO) FINANCE_AMT, "+//6
				" A.RENTAL_ARREAS_NO RENTAL_ARR, "+//7
				" A.RENTAL_ARREAS RENTAL_VAL, "+//8
				" A.CAP_OUTSTANDING CAP_OUTS, "+//9
				" "+m_schema_name+".AF_CO_GET_APP_NIBSM(A.APPLICATION_NO) NIBSM, "+//10
				" A.INT_SUSPENCE_AMT INT_SUSPENSE, "+//11
				" A.PROVISION_RATE*100 PROVION_RATE, "+//12
				//" "+m_schema_name+".AF_CO_GET_PROV_3M_INT_SUSP(A.APPLICATION_NO,'"+m_start_date+"') INTCOME_SUS_3MONTHS, "+//13
				" "+m_schema_name+".AF_CO_GET_PROV_3M_INT_SUSP(A.APPLICATION_NO,'"+m_start_date+"') INTCOME_SUS_3MONTHS, "+//13
				" "+m_schema_name+".AF_CO_GET_PROV_INT_LAST_MONTH(A.APPLICATION_NO,TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1),'DD-MM-YYYY')) INCOME_SUS_LAST_MONTH, "+//14
				" "+m_schema_name+".AF_CO_GET_PROV_INT_LAST_MONTH(A.APPLICATION_NO,'"+m_start_date+"') INCOME_SUS_CURRENT_MONTH, "+//15
				" "+m_schema_name+".AF_CO_GET_PROVISION_LAST_MONTH(A.APPLICATION_NO,TO_CHAR(ADD_MONTHS(TO_DATE('"+m_start_date+"','DD-MM-YYYY'),-1),'DD-MM-YYYY')) PROVISION_PRE_MONTH, "+//16
				" NVL(A.PROVISION_COMMENTS,'-'),"+//17
				" A.ACC_POST_STATUS, "+//18
				" NVL(A.VAT_SUSPENCE_AMT,0) , "+//19
				" NVL(A.ARREARS_CAPITAL,0) ,"+//20 // ADDED BY NUWAN DE SILVA
				" NVL(A.FUTURE_CAPITAL,0) "+//21 // ADDED BY NUWAN DE SILVA
				" ,NVL("+m_schema_name+".AF_CO_GET_MONTHS_DIFF(A.APPLICATION_NO,'"+m_end_date+"'),0)  AGREEGATE_AGE "+//22
				//" ,NVL("+m_schema_name+".AF_CO_GET_MONTHS_DIFF(A.APPLICATION_NO,'30-05-2008'),0)  AGREEGATE_AGE "+//22
				//",0"+
				" ,"+m_schema_name+".AF_CO_GET_EMP_NAME("+m_schema_name+".AF_GET_COLL_OFFICER(FINANCE_NO)) "+			
				" FROM "+m_schema_name+".AF_CO_PRO_PROVISION_DETAILS A "+
				" WHERE A.PROVISION_DATE=TO_DATE('"+m_start_date+"','DD-MM-YYYY') "+
				//" AND A.APPLICATION_NO='AP20070423-0454'"+
				" ORDER BY A.RENTAL_ARREAS_NO,A.APPLICATION_NO ");
		
				String m_string="";	

				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
				out.println("<BR>");  
				out.println("<p><center><b>Provision for Bad and Doubtful Debts and Income Suspension as at "+m_current_date+"</b><center></p>"); 
				out.println("<BR>"); 
				
				//COMMENT BY NUWAN DE SILVA ON 21-08-2008 ------------------------------------------------------------------
				/*
				out.println("<table align='center' width='100%' class='table' border='1' cellpadding='1' cellspacing='0'>");
				out.println("<tr >");
				out.println("<td width='1%' ></td>"); 
				out.println("<td width='15%' style='text-align:center'><DIV class=div_input>Application No</DIV></td>");//1
				out.println("<td width='15%' style='text-align:center'><DIV class=div_input>Finance No</DIV></td>");//1
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Client Name</DIV></td>");//2
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Type of the Asset</DIV></td>"); //3
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Finance Amt</DIV></td>"); //4
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Rental in Arrears No</DIV></td>"); //5
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Rental in Arrears Amt</DIV></td>"); //6
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Capital Outstanding</DIV></td>"); //7
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>NIBSM/Deposits</DIV></td>"); //8
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Valuation</DIV></td>"); //9
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Income Susp. in first 3 months</DIV></td>"); //10
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Net Exposure</DIV></td>"); //11
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Interest in Suspense "+m_last_date+"</DIV></td>"); //12
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Interest in Suspense "+m_current_date+"</DIV></td>"); //13
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Interest Suspended during the Month</DIV></td>"); //14
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision Rate</DIV></td>"); //15
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision as at "+m_last_date+"</DIV></td>"); //16
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision as at "+m_current_date+"</DIV></td>"); //17
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision during the Month</DIV></td>"); //18
				out.println("<td width='20%' ><DIV class=div_input>VAT susp. Amt</DIV></td>"); //19
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Ac. Post Status</DIV></td>"); //19
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Ac. Comments</DIV></td>"); //20
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				int chk_nums=0;
				int j=0;
				while(rs.next()){
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor='silver'>");
						j=0;
					}
					double m_net_exposure=rs.getDouble(9)-rs.getDouble(10)-0+rs.getDouble(13);
					double m_provision_amt=m_net_exposure*rs.getDouble(12);
					
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(1)+"</td>");
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(4)+"</td>");
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(5)+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(6))+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+rs.getString(7)+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(8))+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(9))+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(10))+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(0)+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(13))+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_net_exposure)+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(14))+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(15))+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(15))+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(12))+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(16))+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_provision_amt)+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_provision_amt-rs.getDouble(16))+"</td>");
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(19))+"</td>");
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(18)+"</td>");
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(17)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
				}
				out.println("</table>");
				
				*/
				
				//END COMMENT BY NUWAN DE SILVA ON 21-08-2008 ------------------------------------------------------------------
				
				//ADDED BY NUWAN DE SILVA ON 21-08-2008 ------------------------------------------------------------------
				out.println("<table align='center' width='100%' class='table' border='1' cellpadding='1' cellspacing='0'>");
				out.println("<tr >");
				out.println("<td width='1%' ></td>"); 
				out.println("<td width='15%' style='text-align:center'><DIV class=div_input>Finance No</DIV></td>");//1
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Client Name</DIV></td>");//2
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Type of the Asset</DIV></td>"); //3
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Collection Officer</DIV></td>"); //3
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Finance Amt</DIV></td>"); //4
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Rental in Arrears No</DIV></td>"); //5
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Aggregate age</DIV></td>"); //5
				out.println("<td width='15%' style='text-align:center'><DIV  class=div_input>Rental in Arrears Amt</DIV></td>"); //6
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Interest in Suspense "+m_current_date+"</DIV></td>"); //7
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Interest in Suspense "+m_last_date+"</DIV></td>"); //8
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Interest Suspended during the Month</DIV></td>"); //9
        out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Income Susp. in first 3 months</DIV></td>"); //10
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Arrears Capital</DIV></td>"); //11
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Future Capital</DIV></td>"); //12
				//out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Capital Outstanding</DIV></td>"); //7
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>NIBSM/Deposits</DIV></td>"); //13
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Net Exposure</DIV></td>"); //14
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Valuation</DIV></td>"); //15
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Net Exposure After Valuation</DIV></td>"); //16
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision Rate</DIV></td>"); //17
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision as at "+m_current_date+"</DIV></td>"); //18
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision as at "+m_last_date+"</DIV></td>"); //19
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Provision during the Month</DIV></td>"); //20
				out.println("<td width='20%' ><DIV class=div_input>VAT susp. Amt</DIV></td>"); //21
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Ac. Post Status</DIV></td>"); //22
				out.println("<td width='20%' style='text-align:center'><DIV  class=div_input>Ac. Comments</DIV></td>"); //23
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				int chk_nums=0;
				int j=0;
				while(rs.next()){
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor='silver'>");
						j=0;
					}
					double m_net_exposure=rs.getDouble(20) + rs.getDouble(21)+ rs.getDouble(13) - rs.getDouble(10);
					//double m_net_exposure=rs.getDouble(9)-rs.getDouble(10)-0+rs.getDouble(13);
					//double m_provision_amt=m_net_exposure*rs.getDouble(12);
					double m_provision_amt=m_net_exposure*(rs.getDouble(12)/100);

					
					double m_rental_arrears=rs.getDouble(8);//rs.getDouble(14)+rs.getDouble(13)+rs.getDouble(20);
					//out.println("rs.getDouble(7)"+rs.getDouble(7));
					//out.println("rs.getDouble(22)"+rs.getString(22));
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='15%' style='text-align:center'>"+rs.getString(1)+"</td>");
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(2)+"</td>"); //1
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(4)+"</td>"); //2
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(5)+"</td>"); //3
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(23)+"</td>"); //3
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(6))+"</td>"); //4
					
					//out.println("<td width='15%' style='text-align:right'>"+rs.getString(7)+"</td>"); //5
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(7))+"</td>"); //5
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(7)+ rs.getDouble(22))+"</td>"); //5 //agregate age
					//out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(8))+"</td>"); //6
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_rental_arrears)+"</td>"); //6 
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(15))+"</td>"); //7
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(14))+"</td>"); //8
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(15)-rs.getDouble(14))+"</td>"); //9
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(13))+"</td>"); //10
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(20))+"</td>"); //11
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(21))+"</td>"); //12
					//out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(9))+"</td>"); //7
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(10))+"</td>"); //13
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_net_exposure)+"</td>"); //14
					out.println("<td width='15%' style='text-align:right'>"+nf.format(0)+"</td>"); //15
					out.println("<td width='15%' style='text-align:right'>"+nf.format(0)+"</td>"); //16
					out.println("<td width='15%' style='text-align:right'>"+nf1.format(rs.getDouble(12))+"%</td>"); //17
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_provision_amt)+"</td>"); //18
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(16))+"</td>"); //19
					out.println("<td width='15%' style='text-align:right'>"+nf.format(m_provision_amt-rs.getDouble(16))+"</td>"); //20
					out.println("<td width='15%' style='text-align:right'>"+nf.format(rs.getDouble(19))+"</td>"); //21
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(18)+"</td>"); //22
					out.println("<td width='15%' style='text-align:center'>"+rs.getString(17)+"</td>"); //23
					out.println("<td width='*%'></td>");
					out.println("</tr>");
				}
				out.println("</table>");
				//END ADDED BY NUWAN DE SILVA ON 21-08-2008 ------------------------------------------------------------------
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
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
				    ServletOutputStream out = res.getOutputStream();
					  out.println(e.toString());
						conn.rollback();
						conn.close();
						out.close();
			
				}catch (Exception eti) {}
			
					ByteArrayOutputStream ostr = new ByteArrayOutputStream();
					e.printStackTrace(new PrintWriter(ostr));
			
					ServletOutputStream out = res.getOutputStream();
					out.println(ostr.toString());
      		out.close();
			
			}
	}
}

