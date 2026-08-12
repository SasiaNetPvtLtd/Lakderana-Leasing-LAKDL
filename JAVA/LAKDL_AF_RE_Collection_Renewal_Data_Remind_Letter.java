//ID         :
//SCREEN NAME:LAKDL_AF_RE_Collection_Renewal_Data_Remind_Letter
//CREATED BY :Nuwan De Silva	
//DATE/TIME  :30-04-2007
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_RE_Collection_Renewal_Data_Remind_Letter extends javax.servlet.http.HttpServlet { 

ServletOutputStream out = null;
	
	
Connection conn;
Statement stmt;
CallableStatement callstmt1 =null;
java.text.NumberFormat nf;
	
public ResultSet rs;
public ResultSet rs1,rs2,rs3;
	

public String m_chksql,m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_invoice_no,m_client_no,m_no_of_due_date,m_finance_no,m_print,m_inv_type,m_inv_no,m_vat_reg_no,m_vat_reg_date,m_value_date;
public double m_amount_due;
public double m_gross_rent;
public String m_LAKDL_vat_no="";
public String m_vat_precentage="";
	
	
public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
try { 
		
	//	 	BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
		//	reqstr = input.readLine();   	
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_username = m_sn_methods.username;

			int m_count=0;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
			
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			String m_orient_email="";
			
			String m_vehicle_no="";
			String m_renewal_date="";
			
		

			
			
			
					
					
	//	 out.println(reqstr);
						
			m_chksql=req.getParameter("chksql");
			
			
			stmt = conn.createStatement ();
			
			
				
			
				 rs = stmt.executeQuery(" SELECT "+
					    " COMPANY_NAME, "+
					    " ADDRESS1, "+
					    " ADDRESS2, "+
					    " CITY, "+
					    " TEL_NO, "+
					    " FAX_NO,  "+
							" VAT_RATE, "+
							" VAT_REG_NO, "+
							" EMAIL "+
							" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");

							boolean  more = rs.next();		
											
											if(more)
											{
											m_orient_name=rs.getString(1);
											m_orient_add1=rs.getString(2);
											m_orient_add2=rs.getString(3);
											m_orient_city_name=rs.getString(4);
											m_orient_tel_no=rs.getString(5);
											m_orient_fax_no=rs.getString(6);
											m_vat_precentage=rs.getString(7);			
											m_LAKDL_vat_no=rs.getString(8);			
											m_orient_email=rs.getString(9);			
											}
											
										
												
				if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			  }
								
			else if (m_chksql.trim().equals("main_page")) {
						
						String m_invoice_no	  =req.getParameter("invoice_no");		
						String m_client_no	      =req.getParameter("client_no");		
						String m_finance_no	=req.getParameter("finance_no");	
						//String Hid_scr_name	=req.getParameter("Hid_scr_name");	
						
						
						//String m_revenue_license_date	  =req.getParameter("revenue_license_date");		
						//String m_luxury_tax_date	      =req.getParameter("luxury_tax_date");		
						//String m_driving_license_date	=req.getParameter("driving_license_date");		
						
						String  m_print="TRUE";
						String m_asset_type="";//Added by Sandun on 20-08-2008
						String m_asset_desc="";//Added by Sandun on 20-08-2008
						String m_policy_no_1="";
			      String m_ins_date="";
						double m_prepayble=0;
			
			      double m_sum_insured=0;
			      double m_tax_due=0;
			      double m_bonus_precentage=0;
					  int b_flag=0;
						
						String m_renw_date_dd = "";
						String m_renw_date_mm = "";
						String m_renw_date_yy = ""; 
						//***********************************************************************
						 rs2 = stmt.executeQuery(" SELECT "+////Added by Sandun on 20-08-2008
																    " DISTINCT A.ITEM_CAT_CODE "+
																    " FROM "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY A,"+m_schema_name+".AF_CO_MAS_ITEM_CATEGORY B "+
																    " WHERE  A.ITEM_CAT_CODE=B.ITEM_CAT_CODE "+
																    " AND ITEM_SUB_CAT IN( "+
																    " SELECT ITEM_SUB_CAT   "+
																    " FROM "+m_schema_name+".AF_CO_MAS_MODEL   "+
																    " WHERE MODEL_CODE IN "+
																    " (SELECT MODEL_CODE  "+
																    " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
																    " WHERE UPPER(INVOICE_NO)=UPPER('"+m_invoice_no+"') )  "+
																    " ) ");
 
									    more = rs2.next();  
									    if(more)
									    {
									    m_asset_type=rs2.getString(1);
									        
									    }
							
							
						rs3 = stmt.executeQuery(" SELECT "+////Added by Sandun on 20-08-2008
																	  " C.MAKE_DESC||D.MODEL_CODE||'-'||D.DESCRIPTION, "+//1
																	  " C.MAKE_DESC||D.MODEL_CODE||'-'||D.DESCRIPTION ||'-'|| F.DESCRIPTION DESCRIPTION, "+//2
																	  " NVL(UPPER(C.MAKE_DESC),' ' ) || '-'|| NVL(UPPER("+m_schema_name+".AF_CO_GET_MODEL_DESC(B.MODEL_CODE)), ' ') ||'-'|| NVL(UPPER(D.DESCRIPTION),' ') DESCRIPTION, "+//3
																	  " NVL(UPPER(C.MAKE_DESC),' ' ) || '-'|| NVL(UPPER(D.DESCRIPTION),' ') ||'-'|| NVL(UPPER(F.DESCRIPTION),' ' ) DESCRIPTION "+//4
																	  " FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
																	  " "+m_schema_name+".AF_CO_MAS_MAKE C,"+m_schema_name+".AF_CO_MAS_SUB_MODLE D,"+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION E, "+
																	  " "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY F "+
																	  " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
																	  " A.ACTIVE_STATUS='Y' AND "+
																	  " B.INVOICE_NO=UPPER('"+m_invoice_no+"') AND "+
																	  " A.ASSET_ID=B.ASSET_ID AND "+
																	  " (C.MAKE_CODE ,F.ITEM_SUB_CAT ) IN "+ 
																	  " (SELECT  "+
																	  " MAKE_CODE ,ITEM_SUB_CAT "+ 
																	  " FROM "+m_schema_name+".AF_CO_MAS_MODEL "+  
																	  " WHERE "+
																	  " MODEL_CODE IN ( "+ 
																	  " SELECT  "+
																	  " MODEL_CODE "+   
																	  " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
																	  " WHERE INVOICE_NO=B.INVOICE_NO "+
																	  " AND ACTIVE_STATUS='Y' "+
																	  " )) AND "+
																	  " D.SUB_CODE=B.SUB_MODEL_CODE AND "+ 
																	  " UPPER(E.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+ 
																	  " UPPER(E.BRANCH)=UPPER(B.BRANCH_ID)"); 
																		
						 more = rs3.next();  
									    if(more)
									    {
									    m_asset_desc=rs3.getString(3);
									        
									    }	
														
							
						//***********************************************************************
						
						rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YY') FROM DUAL ");
						
						more = rs.next();
						if(more){
						m_Letter_date=rs.getString(1);
						}
						
						/*
						
						rs = stmt.executeQuery (	" SELECT "+
						" POLICY_NO, "+
					  " TO_CHAR(INSURANCE_DATE,'DD-MM-YYYY'), "+
						" SUM_INSURED, "+
						" TAX_DUE, "+
						" BONUS_PRECENTAGE, "+
						" PREMIUM_AVAILABLE "+
						" FROM "+m_schema_name+".AF_RE_RENEWAL_INSUARANCE "+
						" WHERE PRO_INVOICE_NO=('"+m_invoice_no+"') AND  "+
						" FINANCE_NO=('"+m_finance_no+"')     ");*/
						
						
						rs = stmt.executeQuery (" SELECT A.FINANCE_NO, "+//1   //Added By Sandun on 24-10-2008
																		" A.PRO_INVOICE_NO, "+//2
																		" A.POLICY_NO, "+//3
																		" TO_CHAR(A.START_DATE,'DD-MM-YYYY'), "+//4
																		" TO_CHAR(A.END_DATE,'DD-MM-YYYY'), "+//5
																		" A.SUM_INSSURED, "+//6
																		" A.PREMIUM, "+//7
																		" A.INSURED_BY, "+//8
																		" A.INSUR_COM "+//9
																		" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A "+
																		" WHERE A.PRO_INVOICE_NO = UPPER('"+m_invoice_no+"') "+
																		" AND   A.FINANCE_NO     = UPPER('"+m_finance_no+"') ");
						
						String m_renew_date  ="";
						more = rs.next();
						
						if(more){
						b_flag=1;
						m_policy_no_1  = rs.getString(3);
						m_ins_date     = rs.getString(4);
						m_sum_insured  = rs.getDouble(6);
						m_renew_date   = rs.getString(5);
						m_renw_date_dd = rs.getString(5).substring(0,2); 
						m_renw_date_mm = rs.getString(5).substring(3,5);
						m_renw_date_yy = rs.getString(5).substring(6,10);
						//m_tax_due=rs.getDouble(4);
						///m_bonus_precentage=rs.getDouble(5);
             m_prepayble=rs.getDouble(7);
				    }
						
						
						
						
						rs = stmt.executeQuery (	" SELECT "+
						" CLIENT_CODE, "+
						" FULL_NAME, "+
						" ADDRESS1, "+
						" ADDRESS2, "+
						" "+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE) CITY_NAME ,"+
						" NVL(VAT_REG_NO,'-') VAT_REG_NO "+
						" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
						" WHERE CLIENT_CODE='"+m_client_no+"' ");
						
						
						more = rs.next();
						if(more){
						m_c_code=rs.getString(1);
						m_name=rs.getString(2);
						m_add1=rs.getString(3);
						m_add2=rs.getString(4);
						m_city_desc=rs.getString(5);
						m_vat_reg_no=rs.getString(6);
						
						}
						
						
						rs = stmt.executeQuery (" SELECT "+
						" NVL(REG_NO,'-'), "+
						" TO_CHAR(INSURANCE_DATE,'DD-MM-YYYY') INSURANCE_DATE "+
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
						" WHERE INVOICE_NO='"+m_invoice_no+"' ");
						
						more = rs.next();
						if(more){
						m_vehicle_no=rs.getString(1);
						m_renewal_date=rs.getString(2);
						
						}
            

				
			  out.println("<html><head>"); 
				out.println("<title>Invoice Letter</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  	
			
			out.println("<script>");
			
					out.println("function validate_data(){"); 
					out.println("//validations goes here"); 
			
					out.println("if(document.Form1.TXT_NEW_DATE_DD.value==\"\" || document.Form1.TXT_NEW_DATE_MM.value==\"\" || document.Form1.TXT_NEW_DATE_YY.value==\"\" ){  "); 
					out.println("DIV_DATE.style.color='red';");
					out.println("return false;"); 
					out.println("}"); 
					out.println("else if(document.Form1.TXT_POLICY_NO.value==\"\"){  "); 
					out.println("DIV_TXT_POLICY_NO.style.color='red';");
					out.println("return false;"); 
					out.println("}"); 
					out.println("else if(document.Form1.TXT_PRE_PAYABLE.value==\"\"){  "); 
					out.println("DIV_TXT_PRE_PAYABLE.style.color='red';");
					out.println("return false;"); 
					out.println("}"); 
					
					out.println("else if(document.Form1.TXT_SUM_INSUARANCE.value==\"\"){  "); 
					out.println("DIV_TXT_SUM_INSUARANCE.style.color='red';");
					out.println("return false;"); 
					out.println("}"); 
					
						
							
					out.println("else{"); 
					out.println("return true;"); 
					out.println("}"); 
					out.println("}"); 
					
			
			out.println("function save_data(){");			
			out.println("if(validate_data()){");
			out.println("document.Form1.TXT_POLICY_NO.disabled=false;");
			out.println("renew_date = document.Form1.TXT_NEW_DATE_DD.value+'-'+document.Form1.TXT_NEW_DATE_MM.value+'-'+document.Form1.TXT_NEW_DATE_YY.value;");
			out.println("due_tax = document.Form1.TXT_TAX_AMOUNT.value;");
			out.println("clm_bonus = document.Form1.TXT_BONUS.value;");
			out.println("con_person1=document.Form1.txt_contact_name_1.value;");
			out.println("con_person2=document.Form1.txt_contact_name_2.value;");
			out.println("contact_1 = document.Form1.txt_contact_1.value;");
			out.println("contact_2 = document.Form1.txt_contact_2.value;");
			out.println("contact_3 = document.Form1.txt_contact_3.value;");
			//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Renewal_Data_Remind_Letter?chksql=letter&Hid_scr_name="+Hid_scr_name+"&invoice_no="+m_invoice_no+"&revenue_license_date="+m_revenue_license_date+"&luxury_tax_date="+m_luxury_tax_date+"&driving_license_date="+m_driving_license_date+"&finance_no="+m_finance_no+"&vehicle_no="+m_vehicle_no+"&renewal_date=\"+document.Form1.hid_new_date.value+\"&tax=\"+document.Form1.TXT_TAX_AMOUNT.value+\"&bonus=\"+document.Form1.TXT_BONUS.value+\"&vehicle_no=\"+document.Form1.hid_new_date.value+\"&pre_payable=\"+document.Form1.TXT_PRE_PAYABLE.value+\"&sum_insured=\"+document.Form1.TXT_SUM_INSUARANCE.value+\"&policy_no=\"+document.Form1.TXT_POLICY_NO.value+\" \";"); 
		  out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Renewal_Data_Remind_Letter?chksql=letter&con_number1=\"+contact_1+\"&con_number2=\"+contact_2+\"&con_number3=\"+contact_3+\"&con_person1=\"+con_person1+\"&con_person2=\"+con_person2+\"&due_tax=\"+due_tax+\"&clm_bonus=\"+clm_bonus+\"&invoice_no="+m_invoice_no+"&finance_no="+m_finance_no+"&vehicle_no="+m_vehicle_no+"&renewal_date=\"+renew_date+\"&pre_payable=\"+document.Form1.TXT_PRE_PAYABLE.value+\"&sum_insured=\"+document.Form1.TXT_SUM_INSUARANCE.value+\"&policy_no=\"+document.Form1.TXT_POLICY_NO.value+\" \";"); 
		  out.println("alert(m_url);");
			out.println(" window.location.href=m_url;"); 
			out.println("m_table.innerHTML=\"\" ");
			
			out.println("}");
			
			out.println("}");
			
			
			out.println("function check_number(obj,size){");
			out.println("if(obj.value!='')"); 
			out.println("if(isnumberok(obj,size)){"); 
			
			out.println("format_number(obj,size)"); 
			
			
			out.println("}"); 
			out.println("else{");
			out.println("alert('please enter a number');"); 
			out.println("obj.value='';"); 
			out.println("obj.focus();"); 
			out.println("}"); 
			out.println("}"); 


			
			out.println("function add_button(){");
			
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
			
			}
			else
			{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Generate\" onClick=\"save_data()\"></td></tr>';"); 
			
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			}
			
		  out.println("}");
			
			
			
			
			
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			
			out.println("}");
			
								
			out.println("function load_c_date(val) {");
			
		 			
	//	  out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
   		out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			
			
			out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			
			out.println("     document.Form1.TXT_NEW_DATE_DD.value=v_date;");
			out.println("     document.Form1.TXT_NEW_DATE_MM.value=v_month;");
			out.println("     document.Form1.TXT_NEW_DATE_YY.value=val;");
					
			out.println(" document.Form1.hid_new_date.value=document.Form1.TXT_NEW_DATE_DD.value+'-'+document.Form1.TXT_NEW_DATE_MM.value+'-'+document.Form1.TXT_NEW_DATE_YY.value;");
		
			out.println("  }");				
						
		//	out.println("}");
			out.println("}");
			
			
			out.println("function check_date(){ ");
			
				out.println(" if((document.Form1.TXT_NEW_DATE_DD.value !=\"\")&&(document.Form1.TXT_NEW_DATE_MM.value !=\"\")&&(document.Form1.TXT_NEW_DATE_YY.value !=\"\")){");
			out.println("  checkMonthLength(document.Form1.TXT_NEW_DATE_DD,document.Form1.TXT_NEW_DATE_MM,document.Form1.TXT_NEW_DATE_YY);");
			
			out.println(" document.Form1.hid_new_date.value=document.Form1.TXT_NEW_DATE_DD.value+'-'+document.Form1.TXT_NEW_DATE_MM.value+'-'+document.Form1.TXT_NEW_DATE_YY.value;");
		//	out.println("date=v_date+'-'+v_month+'-'+val;");

			out.println(" }");
			
			out.println("}");
			
			
			
			out.println("</script>");
			
						 
				
				out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");
				
				//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
				
				out.println("<body bgcolor='white'><br>");
				
				
				out.println("<form name='Form1'>");
				
				out.println("<input type=hidden name='hid_cal_date' value=\"\"></td>");
				out.println("<input type=hidden name='hid_new_date' value=\"\"></td>");


							
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     out.println("</tr>"); 
			
			   out.println("</table>");
			      
					
			
			
			   out.println("<table align='center' width='100%' class='table'>"); 
			  
				 out.println("<tr><td width='*%' class='rep-body' align='center'>"+m_orient_name.toUpperCase()+"</b></td></tr>");
	  	   out.println("<tr><td width='*%' class='rep-body' align='center'>"+m_orient_add1+", "+m_orient_add2+", "+m_orient_city_name+". Tel : "+m_orient_tel_no+" Fax : "+m_orient_fax_no+" Email : "+m_orient_email+"</b></td></tr>");
		    
			   out.println("</table>");
					
			
			   out.println("<br>"); 
				 out.println("<br>"); 
				 out.println("<br>"); 
			
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\" align='left' class='rep-body' >"+m_Letter_date+"</td>");
		     out.println("</tr>"); 
			   out.println("</table>");
				
				 out.println("<br>"); 
				
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\" align='left' class='rep-body' >"+m_name+"</td>");
				 out.println("</tr>");  
				 out.println("<tr>");  
				 out.println("<td width=\"100%\" align='left' class='rep-body' >"+m_add1+"</td>");
				 out.println("</tr>");  
				 out.println("<tr>");  
				 out.println("<td width=\"100%\" align='left'  class='rep-body'>"+m_add2+"</td>");
				 out.println("</tr>");  
				 out.println("<tr>");  
				 out.println("<td width=\"100%\" align='left' class='rep-body' >"+m_city_desc+"</td>");
		     out.println("</tr>"); 
			   out.println("</table>");
					
				 out.println("<br>"); 
			
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\" align='left' class='rep-body' >Dear Sir/Madam,</td>");
		     out.println("</tr>"); 
			   out.println("</table>");
					
				 out.println("<br>"); 
					
			
				if(m_asset_type.equals("VEHICLE")){////Modified by Sandun on 20-08-2008
				
				out.println("<table align='center' width='100%' class='table'>"); 
			   
				 out.println("<tr>");  
			   out.println("<td width=\"25%\" align='left' class='rep-body' ><DIV id='DIV_DATE'  class=div_input><b>Renewal Date</b></DIV></td>");//"+m_asset_type+"
				 out.println("<td width=\"2%\" align='center' class='rep-body' ><b>:</b></td>");
				if(b_flag==1){
				 out.println("<td width=\"28%\" align=left><input class=\"txt_input5\" type=\"text\" name='TXT_NEW_DATE_DD' maxlength=\"2\" size=\"2\" value=\""+m_renw_date_dd+"\" onblur=\"check_date()\">");
			   out.println("<input class=\"txt_input5\" type=\"text\" name='TXT_NEW_DATE_MM'  maxlength=\"2\" size=\"2\" value=\""+m_renw_date_mm+"\" onblur=\"check_date()\">");
			   out.println("<input class=\"txt_input5\" type=\"text\" name='TXT_NEW_DATE_YY' maxlength=\"4\" size=\"4\" value=\""+m_renw_date_yy+"\" onblur=\"check_date()\"><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");	
			   out.println("</td> ");
					}
				else {
				 out.println("<td width=\"28%\" align=left><input class=\"txt_input5\" type=\"text\" name='TXT_NEW_DATE_DD' maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date()\">");
			   out.println("<input class=\"txt_input5\" type=\"text\" name='TXT_NEW_DATE_MM'  maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date()\">");
			   out.println("<input class=\"txt_input5\" type=\"text\" name='TXT_NEW_DATE_YY' maxlength=\"4\" size=\"4\" value=\"\" onblur=\"check_date()\"><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");	
			   out.println("</td> ");
					
					}

					out.println("<td width='20%'  class='rep-body' ><DIV id='DIV_TXT_POLICY_NO'  class=div_input><b>Policy No.</b></DIV></td>"); 
				// out.println("<td width=\"20%\" align='left' class='rep-body' ><b>Policy No.</b></td>");
				 out.println("<td width=\"2%\" align='center' class='rep-body' ><b>:</b></td>");
				 out.println("<td width=\"23%\" align='left' class='rep-body' >");
				if(b_flag==0){
				out.println("<input class='txt_input' type='text' name='TXT_POLICY_NO' maxlength='20' value=\"\"  size='20' onBlur=\"\" ></td>"); 
				}	
				else
				{
				 out.println("<input class='txt_input' type='text' name='TXT_POLICY_NO' maxlength='20' value="+m_policy_no_1+" disabled size='20' onBlur=\"\" ></td>"); 
				}
					
				

		     out.println("</tr>"); 
					
				 out.println("<tr>");  
					
			   out.println("<td width=\"25%\" align='left' class='rep-body' ><DIV id='DIV_Vehicle'  class=div_input><b>Vehicle No</b></DIV></td>");
				 out.println("<td width=\"2%\" align='center' class='rep-body' ><b>:</b></td>");
				 out.println("<td width=\"28%\" align='left' class='rep-body' ><DIV class=div_input><b>"+m_vehicle_no+"</b></DIV></td>");
				 out.println("<td width=\"20%\" align='left' class='rep-body' ><DIV id='DIV_Agreement'  class=div_input><b>Agreement No</b></DIV></td>");
				 out.println("<td width=\"2%\" align='center' class='rep-body' ><b>:</b></td>");
				 out.println("<td width=\"23%\" align='left' class='rep-body' ><DIV class=div_input><b>"+m_finance_no+"</b></DIV></td>");
		     out.println("</tr>"); 
					
				 out.println("<tr>");  
			   out.println("<td width=\"25%\" align='left' class='rep-body' ><DIV id='DIV_TXT_PRE_PAYABLE'  class=div_input><b>Premium Payable</b></DIV></td>");
				 out.println("<td width=\"2%\" align='center' class='rep-body' ><b>:</b></td>");
				 

					
				if(b_flag==0){
				 out.println("<td width='28%' ><b><input class='txt_input' type='text' name='TXT_PRE_PAYABLE' maxlength='20' size='20' value=\"\" onBlur=\"check_number(this,20)\" STYLE=\"{text-align:right;}\"></b></td>"); 
				}	
				else
				{
				 out.println("<td width='28%' ><b><input class='txt_input' type='text' name='TXT_PRE_PAYABLE' maxlength='20' size='20' value=\""+nf.format(m_prepayble)+"\" onBlur=\"check_number(this,20)\" STYLE=\"{text-align:right;}\"></b></td>"); 
				}



				 out.println("<td width=\"20%\" align='left' class='rep-body' ><DIV id='DIV_TXT_SUM_INSUARANCE'  class=div_input><b>Sum Insured</b></DIV></td>");
				 out.println("<td width=\"2%\" align='center' class='rep-body' ><b>:</b></td>");
				 
				 if(b_flag==0){
				 out.println("<td width=\"23%\" align='left' class='rep-body' >");
				 out.println("<input class='txt_input' type='text' name='TXT_SUM_INSUARANCE' maxlength='20' size='20' onBlur=\"check_number(this,20)\" value=\"\" STYLE=\"{text-align:right;}\" ></td>"); 
				 }	
				 else
				 {
				 out.println("<td width=\"23%\" align='left' class='rep-body' >");
				 out.println("<input class='txt_input' type='text' name='TXT_SUM_INSUARANCE' maxlength='20' size='20' onBlur=\"check_number(this,20)\" value=\""+nf.format(m_sum_insured)+"\" STYLE=\"{text-align:right;}\" ></td>"); 
				 }
					
					out.println("</tr>"); 	
					
			   out.println("</table>");
								
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr><td width='*%' class='rep-body'><b>----------------------------------------------------------------------------------------------------------------------------</b></td></tr>");
			   out.println("</table>");
					
				 String Data="The insurance  cover on the above vehicle is due for renewal on the date specified send the premium to us before the expiry of the insurance cover."	;
				 
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr><td width='*%' class='rep-body'>"+Data+"</td></tr>");
			   out.println("</table>");
				
				 out.println("<br>");
					
				 Data="The premium, prefererably by cash or cheque to us before the renewal date."+
	            "If the renewal date falls on a week-end or on a holiday, please ensure that premium is "+
							"sent on a working day immediately prior to it.";
					
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");
				 out.println("<td width='3%'  class='rep-body'>&nbsp;</td>");	
				 out.println("<td width='2%' class='rep-body' valign='top' ><li></li></td>");	
				 out.println("<td width='*%' class='rep-body'>"+Data+"</td>");	
				 out.println("</tr>");
					
				 Data="The diesel & luxury taxes by cash (If applicable) only cash remittances are acceptable to the insurer. "+
	            "Tax amount due is:";	
					
				 out.println("<tr>");
				 out.println("<td width='3%'  class='rep-body'>&nbsp;</td>");	
				 out.println("<td width='2%' class='rep-body' valign='top' ><li></li></td>");	
				 
				 if(b_flag==0){
				 out.println("<td width='*%' class='rep-body'>"+Data+"");	
				 out.println("<input class='txt_input' type='text' name='TXT_TAX_AMOUNT' maxlength='20' size='20' value=\"\" onBlur=\"check_number(this,20)\" STYLE=\"{text-align:right; width=100px;}\"></td>"); 
				 }	
				 else
				 {
				 out.println("<td width='*%' class='rep-body'>"+Data+"");	
				 out.println("<input class='txt_input' type='text' name='TXT_TAX_AMOUNT' maxlength='20' size='20' value=\""+nf.format(m_tax_due)+"\" onBlur=\"check_number(this,20)\" STYLE=\"{text-align:right; width=100px;}\"></td>"); 
				 }
					
				 
					
				 out.println("</tr>");	
					
			   out.println("</table>");	
					
				 out.println("<br>");
					
				 Data="No Claim Bonus allowed for the ensuing is: ";	
				 
				 out.println("<table align='center' width='100%' class='table'>"); 
			   					
				 out.println("<tr>");
					
				 if(b_flag==0){
				 out.println("<td width='*%' class='rep-body'>"+Data+"");	
				 out.println("<input class='txt_input' type='text' name='TXT_BONUS' maxlength='3' size='20' onBlur=\"check_number(this,20)\" value=\"\" STYLE=\"{text-align:right; width=50px; }\"><b>%</b></td>"); 
				 }	
				 else
				 {
				 out.println("<td width='*%' class='rep-body'>"+Data+"");	
				 out.println("<input class='txt_input' type='text' name='TXT_BONUS' maxlength='3' size='20' onBlur=\"check_number(this,20)\" value=\""+nf.format(m_bonus_precentage)+"\" STYLE=\"{text-align:right; width=50px; }\"><b>%</b></td>"); 
				 }
				 
				 
					
					
				 out.println("</tr>");	
					
			   out.println("</table>");	
					
								
					}
	
	
					else{////Added by Sandun on 20-08-2008
					
			
				 out.println("<table align='center' width='100%' class='table'>"); 
			   
				 out.println("<tr>");  
			   out.println("<td width=\"25%\" align='left' class='rep-body' ><DIV id='DIV_DATE'  class=div_input><b>Renewal Date</b></DIV></td>");
				 out.println("<td width=\"2%\" align='center' class='rep-body' ><b>:</b></td>");
				
				 out.println("<td width=\"28%\" align=left><input class=\"txt_input5\" type=\"text\" name='TXT_NEW_DATE_DD' maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date()\">");
			   out.println("<input class=\"txt_input5\" type=\"text\" name='TXT_NEW_DATE_MM'  maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date()\">");
			   out.println("<input class=\"txt_input5\" type=\"text\" name='TXT_NEW_DATE_YY' maxlength=\"4\" size=\"4\" value=\"\" onblur=\"check_date()\"><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");	
			   out.println("</td> ");

					out.println("<td width='20%'  class='rep-body' ><DIV id='DIV_TXT_POLICY_NO'  class=div_input><b>Policy No.</b></DIV></td>"); 
				 out.println("<td width=\"2%\" align='center' class='rep-body' ><b>:</b></td>");
				 out.println("<td width=\"23%\" align='left' class='rep-body' >");
				if(b_flag==0){
				out.println("<input class='txt_input' type='text' name='TXT_POLICY_NO' maxlength='20' value=\"\"  size='20' onBlur=\"\" ></td>"); 
				}	
				else
				{
				 out.println("<input class='txt_input' type='text' name='TXT_POLICY_NO' maxlength='20' value="+m_policy_no_1+" disabled size='20' onBlur=\"\" ></td>"); 
				}
					
				

		     out.println("</tr>"); 
					
				 out.println("<tr>");  
					
			   out.println("<td width=\"25%\" align='left' class='rep-body' ><DIV id='DIV_Vehicle'  class=div_input><b>Insured Asset</b></DIV></td>");
				 out.println("<td width=\"2%\" align='center' class='rep-body' ><b>:</b></td>");
				 out.println("<td width=\"28%\" align='left' class='rep-body' ><DIV class=div_input><b>"+m_asset_desc+"</b></DIV></td>");
				 out.println("<td width=\"20%\" align='left' class='rep-body' ><DIV id='DIV_Agreement'  class=div_input><b>LA No</b></DIV></td>");
				 out.println("<td width=\"2%\" align='center' class='rep-body' ><b>:</b></td>");
				 out.println("<td width=\"23%\" align='left' class='rep-body' ><DIV class=div_input><b>"+m_finance_no+"</b></DIV></td>");
		     out.println("</tr>"); 
					
				 out.println("<tr>");  
			   out.println("<td width=\"25%\" align='left' class='rep-body' ><DIV id='DIV_TXT_PRE_PAYABLE'  class=div_input><b>Premium Payable</b></DIV></td>");
				 out.println("<td width=\"2%\" align='center' class='rep-body' ><b>:</b></td>");
				 

					
				if(b_flag==0){
				 out.println("<td width='28%' ><b><input class='txt_input' type='text' name='TXT_PRE_PAYABLE' maxlength='20' size='20' value=\"\" onBlur=\"check_number(this,20)\" STYLE=\"{text-align:right;}\"></b></td>"); 
				}	
				else
				{
				 out.println("<td width='28%' ><b><input class='txt_input' type='text' name='TXT_PRE_PAYABLE' maxlength='20' size='20' value=\""+nf.format(m_prepayble)+"\" onBlur=\"check_number(this,20)\" STYLE=\"{text-align:right;}\"></b></td>"); 
				}



				 out.println("<td width=\"20%\" align='left' class='rep-body' ><DIV id='DIV_TXT_SUM_INSUARANCE'  class=div_input><b>Sum Insured</b></DIV></td>");
				 out.println("<td width=\"2%\" align='center' class='rep-body' ><b>:</b></td>");
				 
				 if(b_flag==0){
				 out.println("<td width=\"23%\" align='left' class='rep-body' >");
				 out.println("<input class='txt_input' type='text' name='TXT_SUM_INSUARANCE' maxlength='20' size='20' onBlur=\"check_number(this,20)\" value=\"\" STYLE=\"{text-align:right;}\" ></td>"); 
				 }	
				 else
				 {
				 out.println("<td width=\"23%\" align='left' class='rep-body' >");
				 out.println("<input class='txt_input' type='text' name='TXT_SUM_INSUARANCE' maxlength='20' size='20' onBlur=\"check_number(this,20)\" value=\""+nf.format(m_sum_insured)+"\" STYLE=\"{text-align:right;}\" ></td>"); 
				 }
					
					out.println("</tr>"); 	
					
			   out.println("</table>");
								
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr><td width='*%' class='rep-body'><b>----------------------------------------------------------------------------------------------------------------------------</b></td></tr>");
			   out.println("</table>");
					
				 //String Data="The insurance  cover on the above vehicle is due for renewal on the date specified send the premium to us before the expiry of the insurance cover."	;
					String Data="The insurance cover is due for renewal on the date specified above. Please send "	;
				 
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr><td width='*%' class='rep-body'>"+Data+"</td></tr>");
			   out.println("</table>");
				
				 out.println("<br>");
					
				 Data="The premium, preferably by cash or cheque to <b>us before the renewal date</b>. If the renewal "+
							"date falls on a week-end or on a holiday, please ensure that the premium is sent on a working "+
							"day immediately prior to it.";
					
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");
				 out.println("<td width='3%'  class='rep-body'>&nbsp;</td>");	
				 out.println("<td width='2%' class='rep-body' valign='top' ><li></li></td>");	
				 out.println("<td width='*%' class='rep-body'>"+Data+"</td>");	
				 out.println("</tr>");
					
				 Data="If however the payment is by cheque, please draw the cheque in favoring Lakderana Investments Limited. ";
						//	"Services Corp. Ltd.";	
					
				 out.println("<tr>");
				 out.println("<td width='3%'  class='rep-body'>&nbsp;</td>");	
				 out.println("<td width='2%' class='rep-body' valign='top' ><li></li></td>");	
				 out.println("<td width='*%' class='rep-body'>"+Data+"</td>");
				 out.println("</tr>");	
					
			   out.println("</table>");	
					
				 out.println("<br>");
										
					}
						
				 out.println("<table align='center' width='100%' class='table'>"); 
			   
				  String Data="That the Sum Insured should represent the market value to obtain a Proper indemnity and ";
					
				 out.println("<tr>");
				 out.println("<td width='10%' class='rep-body'><b>IMPORTANT</b></td>");	
				 out.println("<td width='2%' class='rep-body'><b>:</b></td>");	
				 out.println("<td width='2*%' class='rep-body'><b>1.</b></td>");	
				 out.println("<td width='*%' class='rep-body'>"+Data+"</td>");	
				 out.println("</tr>");	
				
				 Data="to ensure that cover obtained by you suits your present requirements.";
					
					
				 out.println("<tr>");
				 out.println("<td width='10%' class='rep-body'>&nbsp;</td>");	
				 out.println("<td width='2%' class='rep-body'><b>:</b></td>");	
				 out.println("<td width='2*%' class='rep-body'><b>2.</b></td>");	
				 out.println("<td width='*%' class='rep-body'>"+Data+"</td>");	
				 out.println("</tr>");		
					
			   out.println("</table>");		
					
				 out.println("<br>");					
					
				 out.println("<table align='center' width='100%' class='table'>"); 
			   
				 Data="If you have any further clarifications please do not hesitate to contact ";
					
				 out.println("<tr>");
				 out.println("<td width='*%' class='rep-body'>"+Data+"</td>");	
				 out.println("</tr>");	
				
					if(m_asset_type.equals("VEHICLE")){
				//Modifyed By Sandun on 02-02-2009
				 //Data="Amila 0777-598203, Priyantha 0773-043012 or 0115-577577. ";
					//Data = "Priyantha on 0773-043021, Sudarshana on 0773-770460, or 0115-577577 ";
										
				// out.println("<tr>");
				// out.println("<td width='10%' class='rep-body'><b>"+Data+"</></td>");	
				 //out.println("</tr>");	
					out.println("<tr>");
				 out.println("<td width='10%' class='rep-body'><input type='text' maxlength='20' class='txt_input' name='txt_contact_name_1'>&nbsp;<input type='text' maxlength='11' class='txt_input' name='txt_contact_1'>,"+
					           "<input type='text' maxlength='20' class='txt_input' name='txt_contact_name_2'>&nbsp;<input type='text' maxlength='11' class='txt_input' name='txt_contact_2'>&nbsp;or&nbsp;<input type='text' maxlength='11' class='txt_input' name='txt_contact_3'></td>");	
				 out.println("</tr>");	
					}
					else{
					
					//Data="Shammi on 0777-598204, Amila 0777-598203 or 0115-577577 ";
					
										
				 out.println("<tr>");
				 out.println("<td width='10%' class='rep-body'><input type='text' maxlength='20' class='txt_input' name='txt_contact_name_1'>&nbsp;<input type='text' maxlength='11' class='txt_input' name='txt_contact_1'>,"+
					           "<input type='text' maxlength='20' class='txt_input' name='txt_contact_name_2'>&nbsp;<input type='text' maxlength='11' class='txt_input' name='txt_contact_2'>&nbsp;or&nbsp;<input type='text' maxlength='11' class='txt_input' name='txt_contact_3'></td>");	
				 out.println("</tr>");	
					}
			   out.println("</table>");			
					
				 out.println("<br>");	
					
				 out.println("<table align='center' width='100%' class='table'>"); 
			   
				 Data="We assure you of our caring services.";
					
				 out.println("<tr>");
				 out.println("<td width='*%' class='rep-body'>"+Data+"</td>");	
				 out.println("</tr>");	
					
				 Data="Thanking You,";	
					
				 out.println("<tr>");
				 out.println("<td width='*%' class='rep-body'>"+Data+"</td>");	
				 out.println("</tr>");	
				 				
			   out.println("</table>");				
					
				 out.println("<br>");	
					
				 out.println("<table align='center' width='100%' class='table'>"); 
			   
				 Data="Yours Fathfully,";
					
				 out.println("<tr>");
				 out.println("<td width='*%' class='rep-body'>"+Data+"</td>");	
				 out.println("</tr>");	
				
				 out.println("<tr>");
				 out.println("<td width='*%' class='rep-body'>"+m_orient_name+"</td>");	
				 out.println("</tr>");
					
				 				
			   out.println("</table>");				
					
				 out.println("<br>");					
					
				 out.println("<table align='center' width='100%' class='table'>"); 
			   
				 Data="-------------------------";
					
				 out.println("<tr>");
				 out.println("<td width='*%' class='rep-body'>"+Data+"</td>");	
				 out.println("</tr>");	
					
				 Data="Damitha Girihagama";
				
				 out.println("<tr>");
				 out.println("<td width='*%' class='rep-body'>"+Data+"</td>");	
				 out.println("</tr>");	
					
				 Data="Senior Manager - Insurance";
					
				out.println("<tr>");
				 out.println("<td width='*%' class='rep-body'>"+Data+"</td>");	
				 out.println("</tr>");	
				
				 //out.println("<tr>");
				 //out.println("<td width='*%' class='rep-body'>"+m_orient_name+"</td>");	
				// out.println("</tr>");		
				 				
			   out.println("</table>");					
					
												
		    out.println("</form></body>");
			
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 

			  out.println("</html>");
						
			}
			
			else if (m_chksql.trim().equals("letter")) {
			
			String m_tax=req.getParameter("due_tax");
			String m_bonus=req.getParameter("clm_bonus");
			String m_new_date = req.getParameter("renewal_date"); 
			String m_policy_no=req.getParameter("policy_no");
			String m_renew_date=req.getParameter("renew_date");
			String m_pre_payable=req.getParameter("pre_payable");
			String m_sum_insured=req.getParameter("sum_insured");
			String m_reg_no=req.getParameter("vehicle_no");
			String m_finance_no=req.getParameter("finance_no");
			String m_invoice_no=req.getParameter("invoice_no");
			//----------------------------------------------------
			String m_con_person1=req.getParameter("con_person1");		
			String m_con_number1=req.getParameter("con_number1");		
			String m_con_person2=req.getParameter("con_person2");		
			String m_con_number2=req.getParameter("con_number2");		
			String m_con_number3=req.getParameter("con_number3");		
			//----------------------------------------------------
			/*
			String m_revenue_license_date	  =req.getParameter("revenue_license_date");		
			String m_luxury_tax_date	      =req.getParameter("luxury_tax_date");		
			String m_driving_license_date	=req.getParameter("driving_license_date");		
			*/
			String m_print="TRUE";
			
			
        //Saving The Data================================================			
			try{
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
	    		 															"AF_RE_SAVE_RENEWAL_INSUARANCE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10);END;");
							
					callstmt1.setString(1 ,m_finance_no);							
				  callstmt1.setString(2 ,m_policy_no);       
					callstmt1.setString(3 ,m_new_date);
					callstmt1.setString(4 ,m_sn_methods.met_unformat_number(m_pre_payable));
					callstmt1.setString(5 ,m_sn_methods.met_unformat_number(m_sum_insured));
					callstmt1.setString(6 ,m_sn_methods.met_unformat_number(m_tax));
					callstmt1.setString(7 ,m_sn_methods.met_unformat_number(m_bonus));
					callstmt1.setString(8 ,m_invoice_no);
					callstmt1.setString(9 ,"PRINT");
					callstmt1.setString(10 ,m_username);							
 				  callstmt1.execute();
						
					}catch (Exception E) {
		  try{conn.rollback();}catch(Exception e){}
			out.println("ERROR:"+E.toString());
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error when Saving');");
			out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
			
	 }	
					
				//=================================================================			
							
				
				
			  out.println("<html><head>"); 
				out.println("<title>Invoice Letter</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  
  	
			
			out.println("<script>");
			
			out.println("function save_data(){");					
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Invoice_Letters?chksql=save_page&invoice_no="+m_invoice_no+"&client_no="+m_client_no+"&scr_name=AF_RE_INVOICE_LETTERS&finance_no="+m_finance_no+"&inv_type="+m_inv_type+"\";"); 
		  out.println("m_table.innerHTML=\"\" ");
			out.println("window.print();");
			out.println("}");
			
			
			out.println("function check_number(obj,size){");
			out.println("if(obj.value!='')"); 
			out.println("if(isnumberok(obj,size)){"); 
			
			out.println("format_number(obj,size)"); 
			
			
			out.println("}"); 
			out.println("else{");
			out.println("alert('please enter a number');"); 
			out.println("obj.value='';"); 
			out.println("obj.focus();"); 
			out.println("}"); 
			out.println("}"); 


			
			out.println("function add_button(){");
			
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
			
			}
			else
			{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			}
			
		 out.println("}");
			
			
			
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			
			out.println("}");
			
								
			out.println("function load_c_date(val) {");
			
		 			
	//	  out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
   		out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			
			
			out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			
			out.println("     document.Form1.TXT_NEW_DATE_DD.value=v_date;");
			out.println("     document.Form1.TXT_NEW_DATE_MM.value=v_month;");
			out.println("     document.Form1.TXT_NEW_DATE_YY.value=val;");
					
			out.println("  }");				
						
		//	out.println("}");
			out.println("}");
			
			
			out.println("function check_date(){ ");
			
				out.println(" if((document.Form1.TXT_NEW_DATE_DD.value !=\"\")&&(document.Form1.TXT_NEW_DATE_MM.value !=\"\")&&(document.Form1.TXT_NEW_DATE_YY.value !=\"\")){");
			out.println("  checkMonthLength(document.Form1.TXT_NEW_DATE_DD,document.Form1.TXT_NEW_DATE_MM,document.Form1.TXT_NEW_DATE_YY);");
			out.println(" }");
			
			out.println("}");
			
			
			
			out.println("</script>");
			
						 
				
				out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");
				
				//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
				
				out.println("<body bgcolor='white'><br>");
				
				
				out.println("<form name='Form1'>");
				
				out.println("<input type=hidden name='hid_cal_date' value=\"\"></td>");

							
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     out.println("</tr>"); 
			
			   out.println("</table>");
			      
					
			
			
			   out.println("<table align='center' width='100%' class='table'>"); 
			  
				 out.println("<tr><td width='*%' class='rep-body' align='center'>"+m_orient_name.toUpperCase()+"</b></td></tr>");
	  	   out.println("<tr><td width='*%' class='rep-body' align='center'>"+m_orient_add1+", "+m_orient_add2+", "+m_orient_city_name+". Tel : "+m_orient_tel_no+" Fax : "+m_orient_fax_no+" Email : "+m_orient_email+"</b></td></tr>");
		    
			   out.println("</table>");
					
			
			   out.println("<br>"); 
				 out.println("<br>"); 
				 out.println("<br>"); 
			
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\" align='left' class='rep-body' >"+m_Letter_date+"</td>");
		     out.println("</tr>"); 
			   out.println("</table>");
				
				 out.println("<br>"); 
				
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\" align='left' class='rep-body' >"+m_name+"</td>");
				 out.println("</tr>");  
				 out.println("<tr>");  
				 out.println("<td width=\"100%\" align='left' class='rep-body' >"+m_add1+"</td>");
				 out.println("</tr>");  
				 out.println("<tr>");  
				 out.println("<td width=\"100%\" align='left'  class='rep-body'>"+m_add2+"</td>");
				 out.println("</tr>");  
				 out.println("<tr>");  
				 out.println("<td width=\"100%\" align='left' class='rep-body' >"+m_city_desc+"</td>");
		     out.println("</tr>"); 
			   out.println("</table>");
					
				 out.println("<br>"); 
			
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\" align='left' class='rep-body' >Dear Sir/Madam,</td>");
		     out.println("</tr>"); 
			   out.println("</table>");
					
				 out.println("<br>"); 
					
					
				 out.println("<table align='center' width='100%' class='table'>"); 
			   
				 out.println("<tr>");  
			   out.println("<td width=\"25%\" align='left' class='rep-body' ><b>Renewal Date</b></td>");
				 out.println("<td width=\"2%\" align='center' class='rep-body' ><b>:</b></td>");
				 out.println("<td width=\"28%\" align='left' class='rep-body' ><b>"+m_new_date+"</b></td>");
				 

				 out.println("<td width=\"20%\" align='left' class='rep-body' ><b>Policy No.</b></td>");
				 out.println("<td width=\"2%\" align='center' class='rep-body' ><b>:</b></td>");
				 out.println("<td width=\"23%\" align='left' class='rep-body' ><b>"+m_policy_no+"</b></td>");

		     out.println("</tr>"); 
					
				 out.println("<tr>");  
			   out.println("<td width=\"25%\" align='left' class='rep-body' ><b>Vehicle No</b></td>");
				 out.println("<td width=\"2%\" align='center' class='rep-body' ><b>:</b></td>");
				 out.println("<td width=\"28%\" align='left' class='rep-body' ><b>"+m_reg_no+"</b></td>");
				 out.println("<td width=\"20%\" align='left' class='rep-body' ><b>Agreement No</b></td>");
				 out.println("<td width=\"2%\" align='center' class='rep-body' ><b>:</b></td>");
				 out.println("<td width=\"23%\" align='left' class='rep-body' ><b>"+m_finance_no+"</b></td>");
		     out.println("</tr>"); 
					
				 out.println("<tr>");  
			   out.println("<td width=\"25%\" align='left' class='rep-body' ><b>Premium Available</b></td>");
				 out.println("<td width=\"2%\" align='center' class='rep-body' ><b>:</b></td>");
				 out.println("<td width=\"28%\" align='left' class='rep-body' ><b>"+m_pre_payable+"</b></td>");

				 out.println("<td width=\"20%\" align='left' class='rep-body' ><b>Sum Insured</b></td>");
				 out.println("<td width=\"2%\" align='center' class='rep-body' ><b>:</b></td>");
				 out.println("<td width=\"28%\" align='left' class='rep-body' ><b>"+m_sum_insured+"</b></td>");
		     out.println("</tr>"); 	
					
			   out.println("</table>");
								
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr><td width='*%' class='rep-body'><b>----------------------------------------------------------------------------------------------------------------------------</b></td></tr>");
			   out.println("</table>");
					
				 String Data="The insurance  cover on the above vehicle is due for renewal on the date specified send the premium to us before the expiry of the insurance cover."	;
				 
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr><td width='*%' class='rep-body'>"+Data+"</td></tr>");
			   out.println("</table>");
				
				 out.println("<br>");
					
				 Data="The premium, prefererably by cash or cheque to us before the renewal date."+
	            "If the renewal date falls on a weed-end or on a holiday, please ensure that premium is "+
							"sent on a working day immediately prior to it.";
					
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");
				 out.println("<td width='3%'  class='rep-body'>&nbsp;</td>");	
				 out.println("<td width='2%' class='rep-body' valign='top' ><li></li></td>");	
				 out.println("<td width='*%' class='rep-body'>"+Data+"</td>");	
				 out.println("</tr>");
					
				 Data="The diesel & luxury taxes by cash (If applicable) only cash remittances are acceptable to the insurer. "+
	            "Tax amount due is:";	
					
				 out.println("<tr>");
				 out.println("<td width='3%'  class='rep-body'>&nbsp;</td>");	
				 out.println("<td width='2%' class='rep-body' valign='top' ><li></li></td>");	
				 out.println("<td width='*%' class='rep-body'>"+Data+"");	
				 //out.println("<input class='txt_input' type='text' name='TXT_TAX_AMOUNT' maxlength='20' size='20' onBlur=\"check_number(this,20)\" STYLE=\"{text-align:right; width=100px;}\"></td>"); 
				 out.println(""+m_tax+"</td>");	
				 out.println("</tr>");	
					
			   out.println("</table>");	
					
				 out.println("<br>");
					
				 Data="No Claim Bonus allowed for the ensuing is: ";	
				 
				 out.println("<table align='center' width='100%' class='table'>"); 
			   					
				 out.println("<tr>");
				 out.println("<td width='*%' class='rep-body'>"+Data+"");	
				 //out.println("<input class='txt_input' type='text' name='TXT_BONUS' maxlength='3' size='20' onBlur=\"check_number(this,20)\" STYLE=\"{text-align:right; width=50px; }\"><b>%</b></td>"); 
				 out.println(" "+m_bonus+"%</td>");	
				 out.println("</tr>");	
					
			   out.println("</table>");	
					
					
				 out.println("<table align='center' width='100%' class='table'>"); 
			   
				 Data="That the Sum Insured should represent the market value to obtain a Proper indemnity and ";
					
				 out.println("<tr>");
				 out.println("<td width='10%' class='rep-body'><b>IMPORTANT</b></td>");	
				 out.println("<td width='2%' class='rep-body'><b>:</b></td>");	
				 out.println("<td width='2*%' class='rep-body'><b>1.</b></td>");	
				 out.println("<td width='*%' class='rep-body'>"+Data+"</td>");	
				 out.println("</tr>");	
				
				 Data="to ensure that cover obtained by you suits your present requirements.";
					
					
				 out.println("<tr>");
				 out.println("<td width='10%' class='rep-body'>&nbsp;</td>");	
				 out.println("<td width='2%' class='rep-body'><b>:</b></td>");	
				 out.println("<td width='2*%' class='rep-body'><b>2.</b></td>");	
				 out.println("<td width='*%' class='rep-body'>"+Data+"</td>");	
				 out.println("</tr>");		
					
			   out.println("</table>");		
					
				 out.println("<br>");					
					
				 out.println("<table align='center' width='100%' class='table'>"); 
			   
				 Data="If you have any further clarifications please do not hesitate to contact ";
					
				 out.println("<tr>");
				 out.println("<td width='*%' class='rep-body'>"+Data+"</td>");	
				 out.println("</tr>");	
				
				// Data="Shammi on 0777-598204, Amila on 0777-598203 or 0115-577577 ";
				Data = " "+m_con_person1+" "+m_con_number1+" or "+m_con_person2+" "+m_con_number3+","+m_con_number3+" " ;
										
				 out.println("<tr>");
				 out.println("<td width='10%' class='rep-body'><b>"+Data+"</></td>");	
				 out.println("</tr>");		
					
			   out.println("</table>");			
					
				 out.println("<br>");	
					
				 out.println("<table align='center' width='100%' class='table'>"); 
			   
				 Data="We assume you of our caring services.";
					
				 out.println("<tr>");
				 out.println("<td width='*%' class='rep-body'>"+Data+"</td>");	
				 out.println("</tr>");	
					
				 Data="Thanking You,";	
					
				 out.println("<tr>");
				 out.println("<td width='*%' class='rep-body'>"+Data+"</td>");	
				 out.println("</tr>");	
				 				
			   out.println("</table>");				
					
				 out.println("<br>");	
					
				 out.println("<table align='center' width='100%' class='table'>"); 
			   
				 Data="Yours Fathfully,";
					
				 out.println("<tr>");
				 out.println("<td width='*%' class='rep-body'>"+Data+"</td>");	
				 out.println("</tr>");	
					
				 out.println("<tr>");
				 out.println("<td width='*%' class='rep-body'>"+m_orient_name+"</td>");	
				 out.println("</tr>");		
				 				
			   out.println("</table>");				
					
				 out.println("<br>");					
					
				 out.println("<table align='center' width='100%' class='table'>"); 
			   
				 Data="-------------------------";
					
				 out.println("<tr>");
				 out.println("<td width='*%' class='rep-body'>"+Data+"</td>");	
				 out.println("</tr>");	
					
				 Data="Damitha Girihagama";
				
				 out.println("<tr>");
				 out.println("<td width='*%' class='rep-body'>"+Data+"</td>");	
				 out.println("</tr>");	
					
				 Data="Senior Manager - Insurance";			
				 
				 out.println("<tr>");
				 out.println("<td width='*%' class='rep-body'>"+Data+"</td>");	
				 out.println("</tr>");	
				 				
			   out.println("</table>");					
					
												
		    out.println("</form></body>");
			
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 

			  out.println("</html>");
			
			
			}
			
			
			
			
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
