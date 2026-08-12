/*
// header - edit "Data/yourJavaHeader" to customize
// contents - edit "EventHandlers/Java file/onCreate" to customize
//
*/

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : DINETH FOR OFSCL LEASING    DATE:27-11-2008

public class LAKDL_AF_RE_PRO_drill_downs_6 extends javax.servlet.http.HttpServlet {
	
	/*
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt5;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;
    public ServletOutputStream out = null; 
    public ResultSet rs,rs1,rs2,rs3,rs4,rs5;
	public String m_chksql;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		Connection conn=null;
		Statement stmt=null,stmt1=null,stmt2=null,stmt3=null,stmt4=null,stmt5=null;
		CallableStatement callstmt=null;
		java.text.NumberFormat nf=null,nf1=null;
		java.lang.Math a=null;
	     ServletOutputStream out = null; 
	     ResultSet rs=null,rs1=null,rs2=null,rs3=null,rs4=null,rs5=null;
		 String m_chksql=null;
		
		try {
		LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			//**************************************************************					
	    
			String m_html_client_url1=m_sn_methods.html_client_url.trim(); 
			
			
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

			out = res.getOutputStream();
			String url="";
			
			m_chksql=req.getParameter("chksql");
			url=req.getParameter("url");
			
		
			stmt1=conn.createStatement();
			stmt=conn.createStatement();
			stmt2=conn.createStatement();
			stmt3=conn.createStatement();
			stmt4=conn.createStatement();
			stmt5=conn.createStatement();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			
	//-------------------##############--- Client Exposure Drill Start --###############----------------------------------------------------
	
			else if(m_chksql.equals("SHOW_TOTAL_CLIENT_EXPOSURE")){

				String m_string="";				
				String m_client_code=req.getParameter("client_code");
				double exposure_as_client=0;
				double exposure_as_guarantor=0;
				double exposure_as_fact_client=0;
				
				rs=stmt.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_EXPOSURE_AS_CLIENT('"+m_client_code+"') FROM DUAL");
				boolean more=rs.next();
				if(more){
					exposure_as_client=rs.getDouble(1);
				}
				/*String m_client_code_gua="";
				rs=stmt.executeQuery(" SELECT DISTINCT CLIENT_CODE "+
														 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
														 " "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR B "+
														 " WHERE A.APPLICATION_NO=B.APPLICATION_NO "+
														 " AND B.GUARANTOR_CODE='"+m_client_code+"'");
				boolean more1=rs.next();
				double exposure_as_gua_cli=0;
				
				while(more1){
					m_client_code_gua=rs.getString(1);
					rs1=stmt1.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_CLIENT_EXPOSURE('"+m_client_code_gua+"') FROM DUAL");
					if(rs1.next()){
						exposure_as_gua_cli = exposure_as_gua_cli + rs1.getDouble(1);
					}
					more1=rs.next();
				}
				exposure_as_guarantor=exposure_as_gua_cli;*/
				
				rs1=stmt1.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_EXPOSURE_AS_GUAR('"+m_client_code+"') FROM DUAL");
					if(rs1.next()){
						exposure_as_guarantor = rs1.getDouble(1);
					}
				//Exposure as Factoring Client
				//Added by Dineth on 2008-12-31
			  /* remove by waruna 2012-04-20
			    rs1=stmt1.executeQuery(" SELECT NVL(SUM(CREDIT_LIMIT),0) "+
                               " FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
                               " WHERE CLIENT_CODE='"+m_client_code+"' AND FACILITY_STATUS='Y' ");
				  if(rs1.next()){
					  exposure_as_fact_client=rs1.getDouble(1);
				}*/
				//End by Dineth on 2008-12-31
				out.println("<HTML><HEAD><TITLE>Client Exposure Details - Client Code: "+m_client_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">");
				
				
				out.println("function show_exposure_as_client(m_client_code){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_6?chksql=SHOW_EXPOSURE_AS_CLIENT&url="+url+"&client_code=\"+m_client_code+\"\";"); 
			  out.println("window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");		
				
				out.println("function show_exposure_as_guarantor(m_client_code){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_6?chksql=SHOW_EXPOSURE_AS_GUARANTOR&url="+url+"&client_code=\"+m_client_code+\"\";"); 
			  out.println("window.open(m_url,'displayWindow3','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");		
				
				out.println("function show_exposure_as_fact_client(m_client_code){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_6?chksql=SHOW_EXPOSURE_AS_FACT_CLIENT&url="+url+"&client_code=\"+m_client_code+\"\";"); 
			  out.println("window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");		
				
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>");
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Total Client Exposure Details - Client Code: "+m_client_code+"</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				//out.println("<table align='center' width='100%' class='table' >");
				out.println("<table width=\"100%\"  border=\"1\" cellspacing=\"0\" bordercolor='lightgrey' cellpadding=\"0\">");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='50%' class=div_input><b>Exposure As A Client</b></td>");
				out.println("<td width='50%' class=div_input style=\"text-align:right;cursor:hand;cursor-color:blue\" onclick=\"show_exposure_as_client('"+m_client_code+"')\"><U>"+nf.format(exposure_as_client)+"</U></td>");
				//out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='50%' class=div_input><b>Exposure As A Guarantor</b></td>");
				out.println("<td width='50%' class=div_input style=\"text-align:right;cursor:hand;cursor-color:blue\" onclick=\"show_exposure_as_guarantor('"+m_client_code+"')\"><U>"+nf.format(exposure_as_guarantor)+"</U></td>");
				//out.println("<td width='*%'></td>");
				out.println("</tr>");
        //Added by Dineth on 2008-12-31
                /* remove by waruna 2012-04-20
			    out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='50%' class=div_input><b>Exposure As A Factoring Client</b></td>");
				out.println("<td width='50%' class=div_input style=\"text-align:right;cursor:hand;cursor-color:blue\" onclick=\"show_exposure_as_fact_client('"+m_client_code+"')\"><U>"+nf.format(exposure_as_fact_client)+"</U></td>");
				//out.println("<td width='*%'></td>");
				out.println("</tr>");*/
        //End by Dineth on 2008-12-31
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='50%' class=div_input><b>Exposure As A Group</b></td>");
				out.println("<td width='50%' class=div_input style='text-align:right'>0.00</td>");
				//out.println("<td width='*%'></td>");
				out.println("</tr>");
				rs1=stmt1.executeQuery(" SELECT  NVL(SUM(A.CAPITAL_AMOUNT),0) "+  
		                           " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+ 
		                           " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+ 
                               " WHERE UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"') "+ 
                               " AND A.APPLICATION_NO=B.APPLICATION_NO "+     
                               " AND A.INVOICE_NO IS NULL "+ 
		                           " AND B.FINANCE_NO IS NULL ");
				boolean more10=rs1.next();
				double app_amount=0;
				if(more10){
						app_amount=rs1.getDouble(1);
				}
				
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='50%' class=div_input><b>Current Application</b></td>");
				out.println("<td width='50%' class=div_input style='text-align:right'>"+nf.format(app_amount)+"</td>");
				//out.println("<td width='*%'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='50%' class=div_input><b>Total Client Exposure</b></td>");
				out.println("<td width='50%' class=div_input style='text-align:right'><b>"+nf.format(exposure_as_client+exposure_as_guarantor+exposure_as_fact_client+app_amount)+"</b></td>");
				//out.println("<td width='*%'></td>");
				out.println("</tr>");
				
				out.println("</table>");		
        out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");		
		
			}
			else if(m_chksql.equals("SHOW_EXPOSURE_AS_CLIENT")){
				String m_client_code=req.getParameter("client_code");
				//out.println(m_client_code);
				out.println("<HTML><HEAD><TITLE>Client Exposure Details - Client Code: "+m_client_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">");
				out.println("function show_variable_rentals(m_fin_no){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_6?chksql=SHOW_VARIABLE_RENTALS&url="+url+"&fin_no=\"+m_fin_no+\"\";");
				//out.println("alert(m_url);");
			  out.println("window.open(m_url,'displayWindow6','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");		
				
				out.println("function show_multiple_asset_names(m_fin_no){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_6?chksql=SHOW_MULTIPLE_ASSETS&url="+url+"&fin_no=\"+m_fin_no+\"\";");
				//out.println("alert(m_url);");
			  out.println("window.open(m_url,'displayWindow7','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");		
				
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>");
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Exposure Details As Client - Client Code: "+m_client_code+"</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				rs=stmt.executeQuery(" SELECT NVL(FINANCE_NO,'-'),NVL(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),'-'),TRANSACTION_TYPE, "+
														 " NVL("+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO),'-') "+//Added by Dineth on 09-06-2009
														 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+ 
														 " WHERE CLIENT_CODE='"+m_client_code+"' "+
														 " AND APPLICATION_STATUS IN ('ACTIVATED','NORM_TERMI','LEGAL','TERM_TO','TERMI','TERMINATED') ");//Modified by Dineth on 17-06-2009
				
				boolean more_fin=rs.next();
				String m_sys_date="";
				String m_sys_date_1="";
				String m_fin_no="";
				String m_activated_date="";
				String m_asset_name="";
				double m_asset_cost=0;
				double m_NIL=0;
				double m_over_due_rentals=0;
				double m_ODI=0;
				String m_monthly_rental="";
				int m_tenure=0;
				double m_rentals_paid=0;
				double m_vat_on_rental=0;
				double m_fut_ren=0;
				double m_fut_ren_with_vat=0;
				double m_mon_ren=0;
				String m_trn_type="";
				int count=0;
				double m_asset_cost_1=0;
				double m_rentals_paid_1=0;
				double m_fut_ren_with_vat_1=0;
				double m_NIL_1=0;
				double m_over_due_rentals_1=0;
				double m_ODI_1=0;
				String m_application_status="";//Added by Dineth on 09-06-2009
				if (!more_fin) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				if(more_fin){
				  
				  
				
						out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
					
						out.println("<tr class=pdn_txtpos2>");
				
						out.println("<td width='10%' class=div_input>SI No</td>");
						out.println("<td width='10%' class=div_input>Agmt No</td>");
						out.println("<td width='10%' class=div_input>Agmt Date</td>");
						out.println("<td width='10%' class=div_input>Application Status</td>");//Added by Dineth on 09-06-2009
						out.println("<td width='10%' class=div_input>Asset Module</td>"); 
						out.println("<td width='10%' class=div_input>Asset Cost</td>");
						out.println("<td width='10%' class=div_input>Rentals Paid</td>");
						out.println("<td width='10%' class=div_input>Future Due Rentals+VAT</td>");
						out.println("<td width='10%' class=div_input>NIL</td>");
						out.println("<td width='10%' class=div_input>Overdue Rentals</td>");
						out.println("<td width='10%' class=div_input>ODI & Other Charges</td>");
						out.println("<td width='10%' class=div_input>Monthly Rental</td>");
						out.println("<td width='10%' class=div_input>Tenure(Months)</td>");
						out.println("</tr>");
										
				
						
				    while(more_fin){
							
							m_fin_no=rs.getString(1);
							m_activated_date=rs.getString(2);
							m_trn_type=rs.getString(3);
							m_application_status=rs.getString(4);//Added by Dineth on 09-06-2009
							if(!m_fin_no.trim().equals("-")){
							count++;
							rs1=stmt1.executeQuery(" SELECT COUNT(ASSET_ID) "+
																		 " FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+
																		 " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
                                     " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
																		 " B.FINANCE_NO='"+m_fin_no+"'");
							boolean more_count=rs1.next();
							int asset_count=0;
							if(more_count){
									asset_count=rs1.getInt(1);
									if(asset_count>1){
										m_asset_name="multiple";
									}
									else if(asset_count==1){
									  rs2=stmt2.executeQuery(" SELECT NVL("+m_schema_name+".AF_CO_GET_ASSET_DESC(A.APPLICATION_NO),'-') "+
																		 " FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+
																		 " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
                                     " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
																		 " B.FINANCE_NO='"+m_fin_no+"'");
																			
										boolean more_asset=rs2.next();
										if(more_asset){
											m_asset_name=rs2.getString(1);
										}
										
								  }
							}
							
							//Dineth
							/*rs1=stmt1.executeQuery(" SELECT NVL(SUM(A.COST),0) "+
																		 " FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+
																		 " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
                                     " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
																		 " B.FINANCE_NO='"+m_fin_no+"'");*/
																			
								rs1=stmt1.executeQuery(" SELECT NVL(SUM(A.GROSS_AMOUNT),0) "+
                                       " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING A, "+
																			 " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+ 
                                       " WHERE C.APPLICATION_NO=B.APPLICATION_NO AND C.INVOICE_NO=A.PRO_INVOICE_NO AND "+
																			 //" A.PRICING_STATUS='Y' AND "+
                                       " B.FINANCE_NO='"+m_fin_no+"'");
																			
							boolean more_cost=rs1.next();
							if(more_cost){
							  m_asset_cost=rs1.getDouble(1);
							
							}
							//Added by Dineth on 23-06-2009
							if(m_application_status.trim().equals("LEGAL")){//Added by Dineth on 23-06-2009
						  rs1=stmt1.executeQuery(" SELECT NVL(SUM(BALANCE_TO_BE_RECEIVED),0) "+
                                     " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
                                     " WHERE INVOICE_TYPE='LEGAL_CAP' "+
																		 " AND BALANCE_TO_BE_RECEIVED>0 "+
																		 " AND ACTIVE_STATUS='Y' "+
                                     " AND FINANCE_NO='"+m_fin_no+"' ");
							}else{	
						  //End by Dineth on 23-06-2009
							rs1=stmt1.executeQuery(" SELECT  NVL(SUM(A.CAPITAL_AMOUNT),0) "+  
																		 " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+
																		 " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
                                     " WHERE UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"') "+
                                     " AND A.APPLICATION_NO=B.APPLICATION_NO "+    
                                     " AND A.INVOICE_NO IS NULL "+
																		 " AND B.FINANCE_NO='"+m_fin_no+"'");
							}//Added by Dineth on 23-06-2009
							
							boolean more_nil=rs1.next();
							if(more_nil){
							//Added by Dineth on 23-06-2009
							if(m_application_status.trim().equals("Early Termination")){
							  m_NIL=0.0;
							  }else{
								//End by Dineth on 23-06-2009
							  m_NIL=rs1.getDouble(1);
								}
								
								
							}
							//Added by Dineth on 23-06-2009
							if(m_application_status.trim().equals("LEGAL")){
						  rs1=stmt1.executeQuery(" SELECT NVL(SUM(BALANCE_TO_BE_RECEIVED),0) "+
                                     " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
                                     " WHERE INVOICE_TYPE IN ('LEGAL_CAP','LEGAL_ARR') "+
                                     " AND BALANCE_TO_BE_RECEIVED>0 "+
																		 " AND ACTIVE_STATUS='Y' "+
 																		 " AND FINANCE_NO='"+m_fin_no+"' ");
							}else{	
							//End by Dineth on 23-06-2009
							rs1=stmt1.executeQuery(" SELECT NVL(SUM(A.BALANCE_TO_BE_RECEIVED),0) "+
                                     " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+  
                                     " WHERE A.INVOICE_TYPE='INV_GENER' "+ 
                                     " AND APPLICATION_STATUS ='ACTIVATED' "+
                                     " AND A.CLIENT_CODE=B.CLIENT_CODE "+ 
                                     " AND B.FINANCE_NO =A.FINANCE_NO "+
                                     " AND A.ACTIVE_STATUS='Y' "+  
                                     " AND A.BALANCE_TO_BE_RECEIVED>0 "+ 
                                     " AND UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') "+
																		 " AND B.FINANCE_NO='"+m_fin_no+"'");
							}//Added by Dineth on 23-06-2009
							
							boolean more_over_due_rentals=rs1.next();
							if(more_over_due_rentals){
							  m_over_due_rentals=rs1.getDouble(1);
							}
							//Added by Dineth on 23-06-2009
							if(m_application_status.trim().equals("LEGAL")){
						  rs1=stmt1.executeQuery(" SELECT NVL(SUM(BALANCE_TO_BE_RECEIVED),0) "+
                                     " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
                                     " WHERE INVOICE_TYPE IN ('LEGAL_ODI') "+
                                     " AND BALANCE_TO_BE_RECEIVED>0 "+
																		 " AND ACTIVE_STATUS='Y' "+
 																		 " AND FINANCE_NO='"+m_fin_no+"' ");
							}else{
							//End by Dineth on 23-06-2009
							rs1=stmt1.executeQuery("SELECT NVL(SUM(ODI_BAL_AMOUNT),0) "+   
                                     " FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+  
                                     " WHERE INVOICE_NO IN "+   
                                     " (SELECT "+   
                                     " INVOICE_NO "+   
                                     " FROM "+m_schema_name+".AF_CO_PRO_INVOICE  A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+ 
                                     " WHERE UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') "+   
                                     " AND A.CLIENT_CODE=B.CLIENT_CODE "+  
                                     " AND B.FINANCE_NO=A.FINANCE_NO "+ 
                                     " AND APPLICATION_STATUS ='ACTIVATED' "+
                                     " AND A.ACTIVE_STATUS='Y' AND B.FINANCE_NO='"+m_fin_no+"')");
							
							}//Added by Dineth on 23-06-2009
							
							boolean more_odi=rs1.next();
							if(more_odi){
								m_ODI=rs1.getDouble(1);
							}
							
							
							rs1=stmt1.executeQuery(" SELECT A.PERIOD "+
                  " FROM "+m_schema_name+".AF_MK_PRO_PRICING A, "+
									" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+ 
                  " WHERE A.APP_NO=B.APPLICATION_NO "+ 
									" AND B.FINANCE_NO='"+m_fin_no+"'");
							boolean more_tenure=rs1.next();
							if(more_tenure){
								m_tenure=rs1.getInt(1);
							}
							
							rs1=stmt1.executeQuery(" SELECT COUNT(DISTINCT(A.GRENTAL_AMOUNT)) "+
                                     " FROM "+m_schema_name+".AF_MK_PRO_PRICING_INSTALLMENT A, "+
                                     " "+m_schema_name+".AF_MK_PRO_PRICING B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
                                     " WHERE A.PRICING_NO=B.PRICING_NO "+
                                     " AND B.APP_NO=C.APPLICATION_NO AND "+
                                     " C.FINANCE_NO='"+m_fin_no+"'");
						 boolean more_ins=rs1.next();
						
						 int ins_count=0;
						 if(more_ins){
							 ins_count=rs1.getInt(1);
						 }
						 if(ins_count>1){
							 m_monthly_rental="Variable";
								
						 }
						 else if(ins_count==1){
							rs3=stmt3.executeQuery(" SELECT NVL(A.GRENTAL_AMOUNT,0) "+
                                     " FROM "+m_schema_name+".AF_MK_PRO_PRICING_INSTALLMENT A, "+
                                     " "+m_schema_name+".AF_MK_PRO_PRICING B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
                                     " WHERE A.PRICING_NO=B.PRICING_NO "+
                                     " AND B.APP_NO=C.APPLICATION_NO AND "+
                                     " C.FINANCE_NO='"+m_fin_no+"'");
							boolean more_ren=rs3.next();
							if(more_ren){
							m_monthly_rental=nf.format(rs3.getDouble(1));
							
							
							}
						}
						else{
							m_monthly_rental="-";
						}
						
						rs4=stmt4.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'),TO_CHAR(SYSDATE,'DD-MON-YYYY') FROM DUAL");
						boolean more4=rs4.next();
						if(more4){
						m_sys_date=rs4.getString(1);
						m_sys_date_1=rs4.getString(2);
						}
						
																	
						
						
						rs1=stmt1.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_RENTALS_PAID_AMT('"+m_fin_no+"','"+m_sys_date+"') FROM DUAL");
						
						boolean more_paid_rentals=rs1.next();
						if(more_paid_rentals){
						 m_rentals_paid=rs1.getDouble(1);
							
						}
						
						
						rs1=stmt1.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_VAT_ON_RENTAL_PRO('"+m_trn_type+"','"+m_sys_date_1+"') FROM DUAL");
						
						boolean more_vat_on_rental=rs1.next();
						if(more_vat_on_rental){
							m_vat_on_rental=rs1.getDouble(1);
						}
						rs1=stmt1.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_FUTURE_RENTALS_AMT('"+m_fin_no+"') FROM DUAL");
						
						boolean more_fut_ren=rs1.next();
						if(more_fut_ren){
						  m_fut_ren=rs1.getDouble(1);
						}
						
						
						if(m_application_status.trim().equals("Early Termination") || m_application_status.trim().equals("NORM_TERMI") || m_application_status.trim().equals("Normal Termination") || m_application_status.trim().equals("LEGAL") || m_application_status.trim().equals("TERMI")){//Added by Dineth on 09-06-2009
						m_fut_ren_with_vat=0.0;
						}
						else{//Added by Dineth on 09-06-2009
						
						m_fut_ren_with_vat=m_fut_ren+(m_fut_ren*m_vat_on_rental/100);
						}
						
							out.println("<tr>");
				
						out.println("<td width='10%' class=div_input>"+count+"</td>");
						out.println("<td width='10%' class=div_input>"+m_fin_no+"</td>");
						out.println("<td width='10%' class=div_input>"+m_activated_date+"</td>");
						out.println("<td width='10%' class=div_input>"+m_application_status+"</td>");//Added by Dineth on 09-06-2009
						if(m_asset_name.trim().equals("multiple")){
						out.println("<td width='10%' class=div_input onclick=\"show_multiple_asset_names('"+m_fin_no+"')\" style=\"cursor:hand;cursor-color:blue\"><U>"+m_asset_name+"</U></td>"); 
						}else{
						out.println("<td width='10%' class=div_input>"+m_asset_name+"</td>");
						}
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_asset_cost)+"</td>");
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_rentals_paid)+"</td>");
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_fut_ren_with_vat)+"</td>");
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_NIL)+"</td>");
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_over_due_rentals)+"</td>");
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_ODI)+"</td>");
						if(m_monthly_rental.trim().equals("Variable")){
						out.println("<td width='10%' class=div_input onclick=\"show_variable_rentals('"+m_fin_no+"')\" style=\"cursor:hand;cursor-color:blue;text-align:right\"><U>"+m_monthly_rental+"</U></td>");
						}
						else{
						out.println("<td width='10%' class=div_input style='text-align:right'>"+m_monthly_rental+"</td>");
						}
						out.println("<td width='10%' class=div_input>"+m_tenure+"</td>");
						out.println("</tr>");
						m_asset_cost_1=m_asset_cost_1+m_asset_cost;
						m_rentals_paid_1=m_rentals_paid_1+m_rentals_paid;
						m_fut_ren_with_vat_1=m_fut_ren_with_vat_1+m_fut_ren_with_vat;
						m_NIL_1=m_NIL_1+m_NIL;
						m_over_due_rentals_1=m_over_due_rentals_1+m_over_due_rentals;
						m_ODI_1=m_ODI_1+m_ODI;
						}	
							more_fin=rs.next();
							
						
					}
					
					out.println("<tr>");
				
						out.println("<td width='10%' class=div_input>&nbsp;</td>");
						out.println("<td width='10%' class=div_input>&nbsp;</td>");
						out.println("<td width='10%' class=div_input>&nbsp;</td>");
						out.println("<td width='10%' class=div_input>&nbsp;</td>");//Added by Dineth on 09-06-2009
						out.println("<td width='10%' class=div_input>&nbsp;</td>"); 
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_asset_cost_1)+"</td>");
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_rentals_paid_1)+"</td>");
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_fut_ren_with_vat_1)+"</td>");
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_NIL_1)+"</td>");
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_over_due_rentals_1)+"</td>");
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_ODI_1)+"</td>");
						out.println("<td width='10%' class=div_input>&nbsp;</td>");
						out.println("<td width='10%' class=div_input>&nbsp;</td>");
						out.println("</tr>");
						out.println("</table>");
					  out.println("</form>");
						out.println("</body>");
						out.println("</html>");
						
				}
			}
//Show client exposure as a guarantor
			else if(m_chksql.equals("SHOW_EXPOSURE_AS_GUARANTOR")){
				String m_client_code=req.getParameter("client_code");
				//out.println(m_client_code);
				String m_cli_gua_code="";
				String m_sys_date="";
				String m_sys_date_1="";
				String m_fin_no="";
				String m_activated_date="";
				String m_application_status="";//Added by Dineth on 09-06-2009
				String m_asset_name="";
				double m_asset_cost=0;
				double m_NIL=0;
				double m_over_due_rentals=0;
				double m_ODI=0;
				String m_monthly_rental="";
				int m_tenure=0;
				double m_rentals_paid=0;
				double m_vat_on_rental=0;
				double m_fut_ren=0;
				double m_fut_ren_with_vat=0;
				double m_mon_ren=0;
				String m_trn_type="";
				int count=0;
				double m_asset_cost_1=0;
				double m_rentals_paid_1=0;
				double m_fut_ren_with_vat_1=0;
				double m_NIL_1=0;
				double m_over_due_rentals_1=0;
				double m_ODI_1=0;				
				
				out.println("<HTML><HEAD><TITLE>Exposure Details As Guarantor - Guarantor Code: "+m_client_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">");
				out.println("function show_variable_rentals(m_fin_no){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_6?chksql=SHOW_VARIABLE_RENTALS&url="+url+"&fin_no=\"+m_fin_no+\"\";");
				out.println("window.open(m_url,'displayWindow6','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");		
				
				out.println("function show_multiple_asset_names(m_fin_no){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_6?chksql=SHOW_MULTIPLE_ASSETS&url="+url+"&fin_no=\"+m_fin_no+\"\";");
				//out.println("alert(m_url);");
			  out.println("window.open(m_url,'displayWindow7','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");		
				
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>");
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Exposure Details As Guarantor - Guarantor Code: "+m_client_code+"</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				rs5=stmt5.executeQuery(" SELECT DISTINCT CLIENT_CODE "+
														 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
														 " "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR B "+
														 " WHERE A.APPLICATION_NO=B.APPLICATION_NO "+
														 " AND B.GUARANTOR_CODE='"+m_client_code+"'");
				
				boolean more_gua=rs5.next();
				
				if (!more_gua) {
				  			out.println("<table align='center' width='100%' class='table' >");
								out.println("<tr>");
								out.println("<td width='1%'></td>"); 
								out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
								out.println("<td width='*%'></td>");
								out.println("</tr>");
								out.println("</table>");
				}
				if(more_gua){
				  
				  
				
						out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
					
						out.println("<tr class=pdn_txtpos2>");
				
						out.println("<td width='10%' class=div_input>SI No</td>");
						out.println("<td width='10%' class=div_input>Agmt No</td>");
						out.println("<td width='10%' class=div_input>Agmt Date</td>");
						out.println("<td width='10%' class=div_input>Application Status</td>");
						out.println("<td width='10%' class=div_input>Asset Module</td>"); 
						out.println("<td width='10%' class=div_input>Asset Cost</td>");
						out.println("<td width='10%' class=div_input>Rentals Paid</td>");
						out.println("<td width='10%' class=div_input>Future Due Rentals+VAT</td>");
						out.println("<td width='10%' class=div_input>NIL</td>");
						out.println("<td width='10%' class=div_input>Overdue Rentals</td>");
						out.println("<td width='10%' class=div_input>ODI & Other Charges</td>");
						out.println("<td width='10%' class=div_input>Monthly Rental</td>");
						out.println("<td width='10%' class=div_input>Tenure(Months)</td>");
						out.println("</tr>");
						
						while(more_gua){
								m_cli_gua_code=rs5.getString(1);
						
						
								rs=stmt.executeQuery(" SELECT NVL(FINANCE_NO,'-'),NVL(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),'-'),TRANSACTION_TYPE, "+
														 				 " NVL("+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO),'-') "+//Added by Dineth on 09-06-2009
																		 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+ 
														 				 " WHERE CLIENT_CODE='"+m_cli_gua_code+"' "+
																		 " AND APPLICATION_STATUS IN ('ACTIVATED','NORM_TERMI','LEGAL','TERM_TO','TERMI','TERMINATED') ");//Modified by Dineth on 17-06-2009
															
								boolean more_fin=rs.next();
								
								
								//finance_no loop
								while(more_fin){
							
										m_fin_no=rs.getString(1);
										m_activated_date=rs.getString(2);
										m_trn_type=rs.getString(3);
										m_application_status=rs.getString(4);//Added by Dineth on 09-06-2009
										if(!m_fin_no.trim().equals("-")){
											count++;
											rs1=stmt1.executeQuery(" SELECT COUNT(ASSET_ID) "+
																		 				 " FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+
																		 				 " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
                                    				 " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
																						 " B.FINANCE_NO='"+m_fin_no+"'");
										boolean more_count=rs1.next();
										int asset_count=0;
										if(more_count){
												asset_count=rs1.getInt(1);
												if(asset_count>1){
													m_asset_name="multiple";
												}
												else if(asset_count==1){
									  			rs2=stmt2.executeQuery(" SELECT NVL("+m_schema_name+".AF_CO_GET_ASSET_DESC(A.APPLICATION_NO),'-') "+
																		 " FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+
																		 " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
                                     " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
																		 " B.FINANCE_NO='"+m_fin_no+"'");
																			
													boolean more_asset=rs2.next();
													if(more_asset){
														m_asset_name=rs2.getString(1);
													}
										
								 			}
									}
							
							//Dineth
							/*rs1=stmt1.executeQuery(" SELECT NVL(SUM(A.COST),0) "+
																		 " FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+
																		 " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
                                     " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
																		 " B.FINANCE_NO='"+m_fin_no+"'");*/
																			
									rs1=stmt1.executeQuery(" SELECT NVL(SUM(A.GROSS_AMOUNT),0) "+
                                       " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING A, "+
																			 " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+ 
                                       " WHERE C.APPLICATION_NO=B.APPLICATION_NO AND C.INVOICE_NO=A.PRO_INVOICE_NO AND "+ //Modified by Dineth on 17-06-2009
																			 //" A.PRICING_STATUS='Y' AND "+
                                       " B.FINANCE_NO='"+m_fin_no+"'");
																			
									boolean more_cost=rs1.next();
									if(more_cost){
							  			m_asset_cost=rs1.getDouble(1);
							
									}
							
							
							//Added by Dineth on 23-06-2009
							if(m_application_status.trim().equals("LEGAL")){//Added by Dineth on 23-06-2009
						  rs1=stmt1.executeQuery(" SELECT NVL(SUM(BALANCE_TO_BE_RECEIVED),0) "+
                                     " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
                                     " WHERE INVOICE_TYPE='LEGAL_CAP' "+
																		 " AND BALANCE_TO_BE_RECEIVED>0 "+
																		 " AND ACTIVE_STATUS='Y' "+
                                     " AND FINANCE_NO='"+m_fin_no+"' ");
							}else{	
						  //End by Dineth on 23-06-2009
							
							
									rs1=stmt1.executeQuery(" SELECT  NVL(SUM(A.CAPITAL_AMOUNT),0) "+  
																		 " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+
																		 " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
                                     " WHERE UPPER(B.CLIENT_CODE)=UPPER('"+m_cli_gua_code+"') "+
                                     " AND A.APPLICATION_NO=B.APPLICATION_NO "+    
                                     " AND A.INVOICE_NO IS NULL "+
																		 " AND B.FINANCE_NO='"+m_fin_no+"'");
							}//Added by Dineth on 23-06-2009
									boolean more_nil=rs1.next();
									if(more_nil){
									//Added by Dineth on 23-06-2009
							if(m_application_status.trim().equals("Early Termination")){
							  m_NIL=0.0;
							  }else{
								//End by Dineth on 23-06-2009
							  	m_NIL=rs1.getDouble(1);
								}
							}
							
							//Added by Dineth on 23-06-2009
							if(m_application_status.trim().equals("LEGAL")){
						  rs1=stmt1.executeQuery(" SELECT NVL(SUM(BALANCE_TO_BE_RECEIVED),0) "+
                                     " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
                                     " WHERE INVOICE_TYPE IN ('LEGAL_CAP','LEGAL_ARR') "+
                                     " AND BALANCE_TO_BE_RECEIVED>0 "+
																		 " AND ACTIVE_STATUS='Y' "+
 																		 " AND FINANCE_NO='"+m_fin_no+"' ");
							}else{	
							//End by Dineth on 23-06-2009

							rs1=stmt1.executeQuery(" SELECT NVL(SUM(A.BALANCE_TO_BE_RECEIVED),0) "+
                                     " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+  
                                     " WHERE A.INVOICE_TYPE='INV_GENER' "+ 
                                     " AND APPLICATION_STATUS ='ACTIVATED' "+
                                     " AND A.CLIENT_CODE=B.CLIENT_CODE "+ 
                                     " AND B.FINANCE_NO =A.FINANCE_NO "+
                                     " AND A.ACTIVE_STATUS='Y' "+  
                                     " AND A.BALANCE_TO_BE_RECEIVED>0 "+ 
                                     " AND UPPER(A.CLIENT_CODE)=UPPER('"+m_cli_gua_code+"') "+
																		 " AND B.FINANCE_NO='"+m_fin_no+"'");

              }//Added by Dineth on 23-06-2009

              boolean more_over_due_rentals=rs1.next();
							if(more_over_due_rentals){
							  m_over_due_rentals=rs1.getDouble(1);
							}
							
							
							//Added by Dineth on 23-06-2009
							if(m_application_status.trim().equals("LEGAL")){
						  rs1=stmt1.executeQuery(" SELECT NVL(SUM(BALANCE_TO_BE_RECEIVED),0) "+
                                     " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
                                     " WHERE INVOICE_TYPE IN ('LEGAL_ODI') "+
                                     " AND BALANCE_TO_BE_RECEIVED>0 "+
																		 " AND ACTIVE_STATUS='Y' "+
 																		 " AND FINANCE_NO='"+m_fin_no+"' ");
							}else{
							//End by Dineth on 23-06-2009
							rs1=stmt1.executeQuery("SELECT NVL(SUM(ODI_BAL_AMOUNT),0) "+   
                                     " FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+  
                                     " WHERE INVOICE_NO IN "+   
                                     " (SELECT "+   
                                     " INVOICE_NO "+   
                                     " FROM "+m_schema_name+".AF_CO_PRO_INVOICE  A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+ 
                                     " WHERE UPPER(A.CLIENT_CODE)=UPPER('"+m_cli_gua_code+"') "+   
                                     " AND A.CLIENT_CODE=B.CLIENT_CODE "+  
                                     " AND B.FINANCE_NO=A.FINANCE_NO "+ 
                                     " AND APPLICATION_STATUS ='ACTIVATED' "+
                                     " AND A.ACTIVE_STATUS='Y' AND B.FINANCE_NO='"+m_fin_no+"')");
							}
							
							
							boolean more_odi=rs1.next();
							if(more_odi){
								m_ODI=rs1.getDouble(1);
							}
							
							
							rs1=stmt1.executeQuery(" SELECT A.PERIOD "+
                  " FROM "+m_schema_name+".AF_MK_PRO_PRICING A, "+
									" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+ 
                  " WHERE A.APP_NO=B.APPLICATION_NO "+ 
									" AND B.FINANCE_NO='"+m_fin_no+"'");
							boolean more_tenure=rs1.next();
							if(more_tenure){
								m_tenure=rs1.getInt(1);
							}
							
							rs1=stmt1.executeQuery(" SELECT COUNT(DISTINCT(A.GRENTAL_AMOUNT)) "+
                                     " FROM "+m_schema_name+".AF_MK_PRO_PRICING_INSTALLMENT A, "+
                                     " "+m_schema_name+".AF_MK_PRO_PRICING B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
                                     " WHERE A.PRICING_NO=B.PRICING_NO "+
                                     " AND B.APP_NO=C.APPLICATION_NO AND "+
                                     " C.FINANCE_NO='"+m_fin_no+"'");
						 boolean more_ins=rs1.next();
						
						 int ins_count=0;
						 if(more_ins){
							 ins_count=rs1.getInt(1);
						 }
						 if(ins_count>1){
							 m_monthly_rental="Variable";
								
						 }
						 else if(ins_count==1){
							rs3=stmt3.executeQuery(" SELECT NVL(A.GRENTAL_AMOUNT,0) "+
                                     " FROM "+m_schema_name+".AF_MK_PRO_PRICING_INSTALLMENT A, "+
                                     " "+m_schema_name+".AF_MK_PRO_PRICING B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
                                     " WHERE A.PRICING_NO=B.PRICING_NO "+
                                     " AND B.APP_NO=C.APPLICATION_NO AND "+
                                     " C.FINANCE_NO='"+m_fin_no+"'");
							boolean more_ren=rs3.next();
							if(more_ren){
							m_monthly_rental=nf.format(rs3.getDouble(1));
							
							
							}
						}
						else{
							m_monthly_rental="-";
						}
						
						rs4=stmt4.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'),TO_CHAR(SYSDATE,'DD-MON-YYYY') FROM DUAL");
						boolean more4=rs4.next();
						if(more4){
						m_sys_date=rs4.getString(1);
						m_sys_date_1=rs4.getString(2);
						}
						
																	
						
						
						rs1=stmt1.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_RENTALS_PAID_AMT('"+m_fin_no+"','"+m_sys_date+"') FROM DUAL");
						
						boolean more_paid_rentals=rs1.next();
						if(more_paid_rentals){
						 m_rentals_paid=rs1.getDouble(1);
							
						}
						
						
						rs1=stmt1.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_VAT_ON_RENTAL_PRO('"+m_trn_type+"','"+m_sys_date_1+"') FROM DUAL");
						
						boolean more_vat_on_rental=rs1.next();
						if(more_vat_on_rental){
							m_vat_on_rental=rs1.getDouble(1);
						}
						rs1=stmt1.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_FUTURE_RENTALS_AMT('"+m_fin_no+"') FROM DUAL");
						
						boolean more_fut_ren=rs1.next();
						if(more_fut_ren){
						  m_fut_ren=rs1.getDouble(1);
						}
						/*if(!m_application_status.trim().equals("Early Termination") || !m_application_status.trim().equals("NORM_TERMI") || !m_application_status.trim().equals("Normal Termination") || !m_application_status.trim().equals("LEGAL")){//Added by Dineth on 09-06-2009
						m_fut_ren_with_vat=m_fut_ren+(m_fut_ren*m_vat_on_rental/100);
						}
						else{
						m_fut_ren_with_vat=0.0;
						}*/
						
						if(m_application_status.trim().equals("Early Termination") || m_application_status.trim().equals("NORM_TERMI") || m_application_status.trim().equals("Normal Termination") || m_application_status.trim().equals("LEGAL") || m_application_status.trim().equals("TERMI")){//Added by Dineth on 09-06-2009
						m_fut_ren_with_vat=0.0;
						}
						else{//Added by Dineth on 09-06-2009
						
						m_fut_ren_with_vat=m_fut_ren+(m_fut_ren*m_vat_on_rental/100);
						}
							
						
							out.println("<tr>");
				
						out.println("<td width='10%' class=div_input>"+count+"</td>");
						out.println("<td width='10%' class=div_input>"+m_fin_no+"</td>");
						out.println("<td width='10%' class=div_input>"+m_activated_date+"</td>");
						out.println("<td width='10%' class=div_input>"+m_application_status+"</td>");//Added by Dineth on 09-06-2009
						if(m_asset_name.trim().equals("multiple")){
						out.println("<td width='10%' class=div_input onclick=\"show_multiple_asset_names('"+m_fin_no+"')\" style=\"cursor:hand;cursor-color:blue\"><U>"+m_asset_name+"</U></td>");
						}
						else{
						out.println("<td width='10%' class=div_input>"+m_asset_name+"</td>");
						}
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_asset_cost)+"</td>");
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_rentals_paid)+"</td>");
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_fut_ren_with_vat)+"</td>");
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_NIL)+"</td>");
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_over_due_rentals)+"</td>");
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_ODI)+"</td>");
						if(m_monthly_rental.trim().equals("Variable")){
						out.println("<td width='10%' class=div_input onclick=\"show_variable_rentals('"+m_fin_no+"')\" style=\"cursor:hand;cursor-color:blue;text-align:right\"><U>"+m_monthly_rental+"</U></td>");
						}
						else{
						out.println("<td width='10%' class=div_input style='text-align:right'>"+m_monthly_rental+"</td>");
						}
						out.println("<td width='10%' class=div_input>"+m_tenure+"</td>");
						out.println("</tr>");
						m_asset_cost_1=m_asset_cost_1+m_asset_cost;
						m_rentals_paid_1=m_rentals_paid_1+m_rentals_paid;
						m_fut_ren_with_vat_1=m_fut_ren_with_vat_1+m_fut_ren_with_vat;
						m_NIL_1=m_NIL_1+m_NIL;
						m_over_due_rentals_1=m_over_due_rentals_1+m_over_due_rentals;
						m_ODI_1=m_ODI_1+m_ODI;
						}	
							more_fin=rs.next();
							
						
					}
								
								
								
								
								//end finance_no loop
						
				
				        more_gua=rs5.next();
						}//end guarantor loop
						out.println("<tr>");
				
						out.println("<td width='10%' class=div_input>&nbsp;</td>");
						out.println("<td width='10%' class=div_input>&nbsp;</td>");
						out.println("<td width='10%' class=div_input>&nbsp;</td>");
						out.println("<td width='10%' class=div_input>&nbsp;</td>");//Added by Dineth on 09-06-2009

						out.println("<td width='10%' class=div_input>&nbsp;</td>"); 
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_asset_cost_1)+"</td>");
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_rentals_paid_1)+"</td>");
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_fut_ren_with_vat_1)+"</td>");
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_NIL_1)+"</td>");
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_over_due_rentals_1)+"</td>");
						out.println("<td width='10%' class=div_input style='text-align:right'>"+nf.format(m_ODI_1)+"</td>");
						out.println("<td width='10%' class=div_input>&nbsp;</td>");
						out.println("<td width='10%' class=div_input>&nbsp;</td>");
						out.println("</tr>");
						out.println("</table>");
					  out.println("</form>");
						out.println("</body>");
						out.println("</html>");
						
				}//end guarantor if
			}//end checksql
			else if(m_chksql.equals("SHOW_VARIABLE_RENTALS")){
					int m_install_no=0;
					double m_grental_amt=0;
					String m_fin_no=req.getParameter("fin_no");
					out.println("<HTML><HEAD><TITLE>Rental Details - Finance No: "+m_fin_no+"</TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<SCRIPT language=\"JavaScript\">");
						
				
					out.println("</SCRIPT>");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>");
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Rental Details - Finance No: "+m_fin_no+"</B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					rs1=stmt1.executeQuery(" SELECT  A.INSTALLMENT_NO,A.GRENTAL_AMOUNT"+  
																		 " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+
																		 " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
                                     " WHERE A.APPLICATION_NO=B.APPLICATION_NO "+    
                                     //" AND A.INVOICE_NO IS NULL "+
																		 " AND B.FINANCE_NO='"+m_fin_no+"'"+
																		 " ORDER BY TO_NUMBER(A.INSTALLMENT_NO) ASC ");
									boolean more_install=rs1.next();
									out.println("<table align='center' class='table' border='1' bordercolor='black' cellspacing='0' >");
									out.println("<tr class=pdn_txtpos2>");
									out.println("<td>Installment Number</td>");
									out.println("<td>Rental Amount</td>");
									out.println("</tr>");
									while(more_install){
							  	m_install_no=rs1.getInt(1);
									m_grental_amt=rs1.getDouble(2);
									out.println("<tr>");
									out.println("<td>"+m_install_no+"</td>");
									out.println("<td>"+nf.format(m_grental_amt)+"</td>");
									out.println("</tr>");
									more_install=rs1.next();
									}
									out.println("</table>");
								  out.println("</form>");
									out.println("</body>");
									out.println("</html>");
							}
			else if(m_chksql.equals("SHOW_MULTIPLE_ASSETS")){
					String m_fin_no=req.getParameter("fin_no");
					out.println("<HTML><HEAD><TITLE>Multiple Asset Details - Finance No: "+m_fin_no+"</TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<SCRIPT language=\"JavaScript\">");
						
				
					out.println("</SCRIPT>");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>");
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Multiple Asset Details - Finance No: "+m_fin_no+"</B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					out.println("<table align='center' class='table' border='1' bordercolor='black' cellspacing='0' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td>Asset No.</td>");
					out.println("<td>Asset Name</td>");
					out.println("</tr>");

					rs1=stmt1.executeQuery(" SELECT NVL("+m_schema_name+".AF_CO_GET_ASSET_DESC(A.APPLICATION_NO),'-') "+
																		 " FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+
																		 " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
                                     " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
																		 " B.FINANCE_NO='"+m_fin_no+"'");
													int count=0;					
													boolean more_assets=rs1.next();
													int m_row_no=0;
													String m_asset_name="";
													while(more_assets){
													  count++;
														m_row_no=count;
														m_asset_name=rs1.getString(1);
														out.println("<tr>");
														out.println("<td>"+m_row_no+"</td>");
														out.println("<td>"+m_asset_name+"</td>");
														out.println("</tr>");
														more_assets=rs1.next();
													}
					out.println("</table>");
					out.println("</form>");
					out.println("</body>");
					out.println("</html>");
			}// end show multiple assets
//Show client exposure as a guarantor
    else if(m_chksql.equals("SHOW_EXPOSURE_AS_FACT_CLIENT")){
				String m_client_code=req.getParameter("client_code");
				//out.println(m_client_code);
				out.println("<HTML><HEAD><TITLE>Client Exposure Details - Client Code: "+m_client_code+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>");
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Exposure Details As Factoring Client - Client Code: "+m_client_code+"</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
	      
				rs1=stmt1.executeQuery(" SELECT A.FACILITY_NO,DECODE(A.FACILITY_STATUS,'Y','Active','A','Approval1','A2','Confirmed','C','Disapproved','T','Terminated','N','Inactive','-'),NVL(SUM(A.CREDIT_LIMIT),0), "+
                                   "         (SELECT NVL(SUM(DECODE(B.DRCR_STATUS,'DR',B.TRNAMOUNT,B.TRNAMOUNT*-1)),0) CAMOUNT "+
                                   "          FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_BALANCE B "+
                                   "          WHERE B.FACILITY_CODE=A.FACILITY_NO) "+
                                   " FROM "+  
                                   " "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY A "+
                                   " WHERE A.CLIENT_CODE='"+m_client_code+"' "+
																	 //" AND A.FACILITY_STATUS='Y' "+//Commented by Dineth on 2009-01-16
                                   " GROUP BY A.FACILITY_NO,A.FACILITY_STATUS ");
			  
				boolean more_fact=rs1.next();
				if (!more_fact) {
				  			out.println("<table align='center' width='100%' class='table' >");
								out.println("<tr>");
								out.println("<td width='1%'></td>"); 
								out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
								out.println("<td width='*%'></td>");
								out.println("</tr>");
								out.println("</table>");
				}
				if(more_fact){
				  
				  
				
						out.println("<table align='center' width='80%' class='table' border='1' bordercolor='black' cellspacing='0' >");
					
						out.println("<tr class=pdn_txtpos2>");
				
						out.println("<td width='20%' class=div_input style='text-align:center'>Facility No</td>");
						out.println("<td width='20%' class=div_input style='text-align:center'>Facility Status</td>");
						out.println("<td width='20%' class=div_input style='text-align:center'>Approved Amount</td>");
						out.println("<td width='20%' class=div_input style='text-align:center'>Utilization</td>");
						out.println("</tr>");
						while(more_fact){
						   out.println("<tr>");
						   out.println("<td>"+rs1.getString(1)+"</td>");
							 out.println("<td>"+rs1.getString(2)+"</td>");
							 out.println("<td style='text-align:right'>"+nf.format(rs1.getDouble(3))+"</td>");
							 out.println("<td style='text-align:right'>"+nf.format(rs1.getDouble(4))+"</td>");
							 out.println("</tr>");
						
						more_fact=rs1.next();
						}
						
						out.println("</table>");
				}
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
			}
			else if(m_chksql.equals("SHOW_POD_CHEQUE_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
					
					
					String		Sql_Pod_Cheque_Hand=" SELECT "+ 
           "              NVL(POD_REF_NO,'-'), "+//1
           "              NVL(FINANCE_NO,'-'), "+//2
           "              NVL(CHEQUE_NO,'-'), "+//3
           "              NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'), "+//4
           "              NVL(PAYER_ACC_NO,'-'), "+//5
           "              NVL(PAYER_BRANCH_CODE,'-'), "+//6
           "              NVL(CHEQUE_AMOUNT,0), "+//7
					 "              DECODE(STATUS,'APP','Approved','CAN','Dis Approved','HOL','Hold','INV','Entered','REC','Receipt Generated','WIT','Withdraw',STATUS) "+
           "              FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
           "              WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')  AND STATUS IN ('INV' ,'APP') " ;

					     

			  rs=stmt1.executeQuery(Sql_Pod_Cheque_Hand);
				boolean  more =rs.next();
						
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  /*out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b><u>Post Dated Cheque Details</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					*/
          out.println("<br>");
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>POD Ref No</b></td>");
					out.println("<td width='15%' class=div_input><b>Finance No</b></td>");
					out.println("<td width='12%' class=div_input><b>Cheque No</b></td>");
					out.println("<td width='12%' class=div_input><b>Cheque Date</b></td>");
					out.println("<td width='15%' class=div_input><b>Account No</b></td>");
					out.println("<td width='15%' class=div_input><b>Branch code</b></td>");
					out.println("<td width='10%' class=div_input><b>POD Status</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Amount</b></td>");
					out.println("</tr>");
					//out.println("</table>");
					out.println("<br>");
				}
			//	out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_POD_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_finance_drill('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='12%' class=div_input align='left'>"+rs.getString(3)+"</td>");
					out.println("<td width='12%' class=div_input align='left'>"+rs.getString(4)+"</td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(5)+"</td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(6)+"</td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(8)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(7))+"</td>");
					out.println("</tr>");
					
					more = rs.next();
				}
				
		
				  out.println("</table>");
					
			
			
			}
			else if(m_chksql.equals("SHOW_RENTAL_INFO")){
				
				int count = 0;
				String m_string="";		
				String m_application_no="";
				String m_client_code=req.getParameter("client_code");
				
				double sum_net=0;
				double sum_vat=0;
				double sum_tot=0;
				double sum_set=0;
				double sum_bal=0;
				double tot_due=0;
				
				double tot_net=0;
				double tot_vat=0;
				double tot_tot=0;
				double tot_set=0;
				double tot_bal=0;
				double tot_rental=0;
				
				double sum_odical_amt=0;
				double sum_odibal_amt=0;
				double sum_odiset_amt=0;
				double sum_odiadj_amt=0;
				
				double odi_cal_tot=0;
				double odi_bal_tot=0;
				double odi_set_tot=0;
				double odi_adj_tot=0;
				int b_flag=0;
				double sum_legal_odi_amt=0;
				double sum_legal_odi_amt_tot =0;
				double sum_legal_due = 0;	
				double tot_cr = 0;
				double sum_legal_cr = 0;
					
				String		Sql_Rent=" SELECT "+
							  " APPLICATION_NO, "+
							  " SUM(NET_RENTAL_AMOUNT) NET_RENTAL_AMOUNT "+
							  " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
							  " WHERE APPLICATION_NO IN (SELECT APPLICATION_NO "+
							  "                        FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
								"                          WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
							  "                        APPLICATION_STATUS <>'CANCEL' "+
							  "                           ) "+
							  " GROUP BY   APPLICATION_NO      ";                    
             
                          
			

       

			  rs=stmt1.executeQuery(Sql_Rent);
				boolean  more =rs.next();
						
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' border='0' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input ><b>Finance No</b></td>");
					out.println("<td width='10%' class=div_input align='right'> <b>Net Rental Amount</b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>Invoiced Net</b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>Invoiced VAT</b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>Invoiced Gross</b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>Invoiced Settled</b></td>");
					out.println("<td width='10%'  class=div_input align='right'><b>Legal CR</b></td>");//--------Sandun on 18-03-2009
					out.println("<td width='10%'  class=div_input align='right'><b>Legal Due</b></td>");//-------Sandun on 18-03-2009
					out.println("<td width='10%'  class=div_input align='right'><b>Balance Outstanding</b></td>");
					out.println("<td width='10%'  class=div_input align='right'><b>ODI Calculate Amount</b></td>");					
					out.println("<td width='10%'  class=div_input align='right'><b>ODI Settled Amount</b></td>");					
					out.println("<td width='10%'  class=div_input align='right'><b>ODI Adjusted Amount</b></td>");
					out.println("<td width='10%'  class=div_input align='right'><b>Legal ODI</b></td>");//-Sandun on 18-03-2009
					out.println("<td width='10%'  class=div_input align='right'><b>ODI Balance Amount</b></td>");
					//out.println("<td width='10%'  class=div_input align='right'><b>ODI Settled Amount</b></td>");//Commented by Dineth on 2008-08-25
					
					
					out.println("</tr>");
					//out.println("</table>");
					out.println("<br>");
				}
				//out.println("<table align='center' width='100%' class='table' >");
				while(more){
				sum_net=0;
				sum_vat=0;
				sum_tot=0;
				sum_set=0;
				sum_bal=0;
				
					count++;
					
					          m_application_no=rs.getString(1);
										
										String		Sql_Rent_details=" SELECT "+ 
										"  SUM(NET_AMOUNT), "+
										"  SUM(VAT_AMOUNT), "+
										"  SUM(TOTAL_AMOUNT), "+ 
										"  SUM(SETTELE_AMOUNT), "+
										"  SUM(BALANCE_TO_BE_RECEIVED), "+
										"  FINANCE_NO ,"+
										"  "+m_schema_name+".AF_CO_GET_LEGAL_DUE(FINANCE_NO), "+
										"  "+m_schema_name+".AF_CO_GET_LEGAL_CREDIT(FINANCE_NO) "+
										" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
										" WHERE     ACTIVE_STATUS='Y' AND "+ 
										" FINANCE_NO IN   "+
										" (SELECT "+
										"  FINANCE_NO "+
										"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
										"  WHERE  UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') AND "+
										"         APPLICATION_STATUS<>'CANCEL') GROUP BY FINANCE_NO ";
										
				rs2=stmt.executeQuery(Sql_Rent_details);
				boolean  more2 =rs2.next();						
				String m_finance_no="";
				if(more2){
				sum_net=rs2.getDouble(1);
				sum_vat=rs2.getDouble(2);
				sum_tot=rs2.getDouble(3);
				sum_set=rs2.getDouble(4);
				sum_bal=rs2.getDouble(5);
				m_finance_no=rs2.getString(6);
        sum_legal_due=rs2.getDouble(7);
				sum_legal_cr=rs2.getDouble(8);
				}
				
				
				
//comment by ns on 22-07-2011 remove the 	AF_CO_GET_TERMINATE_ODI function for the tally purpose with the odi report			
/*				String		Sql_OdRent_details= " SELECT SUM(ODI_CAL_AMOUNT+"+m_schema_name+".AF_CO_GET_TERMINATE_ODI('"+m_finance_no+"')),SUM(ODI_BAL_AMOUNT),SUM(ODI_SETTLED_AMOUNT+"+m_schema_name+".AF_CO_GET_TERMINATE_ODI('"+m_finance_no+"')),SUM(ADJUSTED_AMOUNT), "+//AF_CO_GET_TERMINATE_ODI ---------Added By Sandun on 01-07-2009
				                              " "+m_schema_name+".AF_CO_GET_LEGAL_ODI('"+m_finance_no+"')"+
						                           " FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
																			 " WHERE INVOICE_NO IN "+
																			 " (SELECT INVOICE_NO "+
																			 " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
																			 " WHERE FINANCE_NO IN   ( SELECT FINANCE_NO "+
																			 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																			 " WHERE  UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') AND "+    //AP20070122-0319
																			 " APPLICATION_STATUS<>'CANCEL' )) AND ODI_DATE>=TO_DATE('31-03-2008','DD-MM-YYYY') ";
				
	*/
 //added by ns on 22-07-2011
				String		Sql_OdRent_details= " SELECT SUM(ODI_CAL_AMOUNT),SUM(ODI_BAL_AMOUNT),SUM(ODI_SETTLED_AMOUNT),SUM(ADJUSTED_AMOUNT), "+//AF_CO_GET_TERMINATE_ODI ---------Added By Sandun on 01-07-2009
				                              " "+m_schema_name+".AF_CO_GET_LEGAL_ODI('"+m_finance_no+"') ,"+
											  " SUM(+"+m_schema_name+".AF_CO_GET_TERMINATE_ODI('"+ m_finance_no+"'))  TERMI_ODI "+ //added by ns on 25-08-2011
						                           " FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
																			 " WHERE INVOICE_NO IN "+
																			 " (SELECT INVOICE_NO "+
																			 " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
																			 " WHERE FINANCE_NO IN   ( SELECT FINANCE_NO "+
																			 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																			 " WHERE  UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') AND "+    //AP20070122-0319
																			 " APPLICATION_STATUS<>'CANCEL' )) AND ODI_DATE>=TO_DATE('31-03-2008','DD-MM-YYYY') ";
																			 
				 rs2=stmt.executeQuery(Sql_OdRent_details);
				    boolean  more3 =rs2.next();	
				    
						if(more2){
				    sum_odical_amt=rs2.getDouble(1)+ rs2.getDouble("TERMI_ODI"); //added by ns on 25-08-2011
				    sum_odibal_amt=rs2.getDouble(2)-rs2.getDouble(5);
				    sum_odiset_amt=rs2.getDouble(3)+ rs2.getDouble("TERMI_ODI"); //added by ns on 25-08-2011
						sum_odiadj_amt=rs2.getDouble(4);
						sum_legal_odi_amt =rs2.getDouble(5); 
				    }
				
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='10%' class=div_input style= cursor:hand; onclick=show_application_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input style= cursor:hand; onclick=show_finance_drill('"+m_finance_no+"') ><u>"+m_finance_no+"</u></td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"</td>");
					
					out.println("<td width='10%' class=div_input align='right' >"+nf.format(sum_net)+"</td>");
					out.println("<td width='10%' class=div_input align='right' >"+nf.format(sum_vat)+"</td>");
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_rent_invoiced_drill('"+m_application_no+"')><u>"+nf.format(sum_tot)+"</u></td>");
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_rent_settled_drill('"+m_application_no+"')><u>"+nf.format(sum_set)+"</u></td>");
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_legal_credit_invoice_drill('"+m_finance_no+"')><u>"+nf.format(sum_legal_cr)+"</td>");//--------Sandun on 18-03-2009
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_legal_due_invoice_drill('"+m_finance_no+"')><u>"+nf.format(sum_legal_due)+"</td>");//--------Sandun on 18-03-2009			
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_rent_balance_to_be_received_drill('"+m_application_no+"')><u>"+nf.format(sum_bal)+"</u></td>");
          out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_odi_cal_drill('"+m_application_no+"')><u>"+nf.format(sum_odical_amt)+"</u></td>");
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_odi_set_drill('"+m_application_no+"')><u>"+nf.format(sum_odiset_amt)+"</u></td>");
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_odi_cal_drill('"+m_application_no+"')><u>"+nf.format(sum_odiadj_amt)+"</u></td>");
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_odi_bal_drill('"+m_application_no+"')><u>"+nf.format(sum_legal_odi_amt)+"</td>");//--------Sandun on 18-03-2009			
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_odi_bal_drill('"+m_application_no+"')><u>"+nf.format(sum_odibal_amt)+"</u></td>");
					//out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_odi_set_drill('"+m_application_no+"')><u>"+nf.format(sum_odiset_amt)+"</u></td>");// Commented by Dineth on 2008-08-25
					
					out.println("</tr>");
					
					tot_rental=tot_rental+rs.getDouble(2);
					tot_net=tot_net+sum_net;
					tot_vat=tot_vat+sum_vat;
					tot_tot=tot_tot+sum_tot;
					tot_set=tot_set+sum_set;
					tot_bal=tot_bal+sum_bal;
					tot_due+= sum_legal_due;
					tot_cr+= sum_legal_cr;
					
					odi_cal_tot=odi_cal_tot+sum_odical_amt;
					odi_bal_tot=odi_bal_tot+sum_odibal_amt;
					odi_set_tot=odi_set_tot+sum_odiset_amt;
					odi_adj_tot=odi_adj_tot+sum_odiadj_amt;
					sum_legal_odi_amt_tot +=sum_legal_odi_amt;
					
					more = rs.next();
					b_flag=1;
				}
	        //out.println("</table>");
					
					if(b_flag==1){
					//out.println("<table align='center' width='100%' class='table' border='0' >");
		  		out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input align='left' ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>"+nf.format(tot_rental)+"</b></td>");
					out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(tot_net)+"</b></td>"); //tot_vat
					out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(tot_vat)+"</b></td>");//tot_net
					out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(tot_tot)+"</b></td>");
					out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(tot_set)+"</b></td>");
					out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(tot_cr)+"</b></td>");//--------Sandun on 18-03-2009
					out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(tot_due)+"</b></td>");//--------Sandun on 18-03-2009
					out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(tot_bal)+"</b></td>");
					out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(odi_cal_tot)+"</b></td>");
          out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(odi_set_tot)+"</b></td>");
          out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(odi_adj_tot)+"</b></td>");
					out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(sum_legal_odi_amt_tot)+"</b></td>");//--------Sandun on 18-03-2009
			   	out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(odi_bal_tot)+"</b></td>");
				  out.println("</tr>");
			    //out.println("</table>");
					}
		     out.println("</table>");  
				
			}
			else if(m_chksql.equals("SHOW_LEGAL_CREDIT_INVOICE")){
			
			  String m_fin_no = req.getParameter("application_no");
				double m_tot_amt=0;
				
			  out.println("<HTML><HEAD><TITLE>Legal Termination</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>");
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Legal Termination Credit Notes - Finance No : "+m_fin_no+"</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				rs=stmt1.executeQuery(	" SELECT "+
																" DISTINCT A.INVOICE_NO , "+//1
																" TO_CHAR(A.ADJUSTED_DATE,'DD-MM-YYYY') ,"+ //2
																" NVL(SUM(A.ADJUSTED_AMOUNT) ,0) "+//3
																" FROM   "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS A,"+m_schema_name+".AF_CO_PRO_INVOICE  B "+ 
																" WHERE A.INVOICE_NO = B.INVOICE_NO "+
																" AND   B.FINANCE_NO='"+m_fin_no+"'  "+
																" AND   B.ACTIVE_STATUS IN ('Y','DB_CAN')"+
																" AND   A.ACTIVE_STATUS IN ('Y','C')  "+
																" AND   A.ADJUST_TYPE = 'LEGAL_AD'  "+
																" AND   A.ADJUSTED_DATE <=SYSDATE  "+
																" GROUP BY A.INVOICE_NO,A.ADJUSTED_DATE ");
				
				boolean more = rs.next();
				  
					if(!more){
					
					}else{
			  	out.println("<table align='center' width='100%' class='table' border='0' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input ><b>Invoice No</b></td>");					
					out.println("<td width='10%' class=div_input ><b>Adjusted Date</b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>Adjusted Amount </b></td>");
					out.println("<td width='1%'></td>");
					out.println("</tr>");				
					
					while(more){
								
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input >"+rs.getString(1)+"</b></td>");
					out.println("<td width='10%' class=div_input >"+rs.getString(2)+"</b></td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</b></td>");
					out.println("<td width='1%'></td>");
					out.println("</tr>");							
					m_tot_amt +=rs.getDouble(3);					
					
					more = rs.next();
					}	
					
					out.println("<tr>");
					out.println("<td width='1%'>&nbsp;</td>"); 
					out.println("<td width='10%' class=div_input >&nbsp;</td>");
					out.println("<td width='10%' class=div_input ><b>Total</td>");
					out.println("<td width='10%' class=div_input align='right'><b>"+nf.format(m_tot_amt)+"</b></td>");
					out.println("</tr>");			
					
					
					out.println("</table>");
					}
				
				
				
				
				out.println("</BODY>");
				out.println("</HTML>");
				
			
			}
			else if(m_chksql.equals("SHOW_LEGAL_DUE_INVOICE")){
			
			  String m_fin_no = req.getParameter("application_no");
			  double m_tot_bal=0,m_tot_set=0,m_tot_amt=0; 
			  out.println("<HTML><HEAD><TITLE>Legal Termination</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>");
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Legal Termination Due Invoices - Finance No : "+m_fin_no+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				   rs=stmt1.executeQuery(	"  SELECT "+
																	"  INVOICE_NO , "+//1
																	"  TO_CHAR(VALUE_DATE,'DD-MM-YYYY') , "+ //2
																	"  NVL(TOTAL_AMOUNT,0), "+//3
																	"  NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) DESCRIPTION , "+//4
																	"  NVL(SETTELE_AMOUNT,0) ,"+//5
																	"  NVL(BALANCE_TO_BE_RECEIVED,0) "+//6
																	"  FROM "+m_schema_name+".AF_CO_PRO_INVOICE   "+
																	"  WHERE FINANCE_NO='"+m_fin_no+"' AND "+
																	"  ACTIVE_STATUS IN ('Y','DB_CAN')  "+
																	"  AND INVOICE_TYPE IN ('LEGAL_ARR','LEGAL_CAP') "+
																	"  AND VALUE_DATE <=SYSDATE ");
				
				boolean more = rs.next();
				  
					if(!more){
					
					}else{
			  	out.println("<table align='center' width='100%' class='table' border='0' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input ><b>Invoice No</b></td>");					
					out.println("<td width='10%' class=div_input ><b>Value Date</b></td>");
					out.println("<td width='10%' class=div_input ><b>Description</b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>Total Amount </b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>Settled Amount</b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>Balance Amount</b></td>");
					out.println("</tr>");				
					
					while(more){
								
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input >"+rs.getString(1)+"</b></td>");
					out.println("<td width='10%' class=div_input >"+rs.getString(2)+"</b></td>");
					out.println("<td width='10%' class=div_input >"+rs.getString(4)+"</b></td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</b></td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</b></td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"</b></td>");
					out.println("</tr>");							
					m_tot_amt +=rs.getDouble(3);
					m_tot_set +=rs.getDouble(5);
					m_tot_bal +=rs.getDouble(6);
					
					more = rs.next();
					}	
					
					out.println("<tr>");
					out.println("<td width='1%'>&nbsp;</td>"); 
					out.println("<td width='10%' class=div_input >&nbsp;</td>");
					out.println("<td width='10%' class=div_input >&nbsp;</td>");
					out.println("<td width='10%' class=div_input ><b>Total</td>");
					out.println("<td width='10%' class=div_input align='right'><b>"+nf.format(m_tot_amt)+"</b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>"+nf.format(m_tot_set)+"</b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>"+nf.format(m_tot_bal)+"</b></td>");
					out.println("</tr>");			
					
					
					out.println("</table>");
					
				}
				out.println("</BODY>");
				out.println("</HTML>");
			
			}
		}
			catch (Exception e) {
				try {out.println(e.toString());}catch (Exception eti) {}
				try {
						conn.close();
				}catch (Exception eti) {}
			
					ByteArrayOutputStream ostr = new ByteArrayOutputStream();
					e.printStackTrace(new PrintWriter(ostr));
			
					//ServletOutputStream out = res.getOutputStream();
					out.println(ostr.toString());
      		out.close();
			
			}
	}
}
